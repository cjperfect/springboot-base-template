package com.cj.snippets.controller;

import com.cj.snippets.annotation.SysLogAnnotation;
import com.cj.snippets.common.BaseResponse;
import com.cj.snippets.common.enums.LogTypeEnum;
import com.cj.snippets.common.enums.ErrorCode;
import com.cj.snippets.common.group.Add;
import com.cj.snippets.common.group.Update;
import com.cj.snippets.exception.BusinessException;
import com.cj.snippets.model.dto.CodeSnippetDTO;
import com.cj.snippets.model.entity.CodeSnippet;
import com.cj.snippets.model.vo.CodeSnippetVO;
import com.cj.snippets.service.CodeSnippetService;
import com.cj.snippets.util.CopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Api(tags = "代码片段Controller")
@RestController
@RequestMapping("/snippet")
public class CodeSnippetController {

    @Resource
    private CodeSnippetService codeSnippetService;

    @ApiOperation(value = "查询所有")
    @GetMapping("/getAll")
    @SysLogAnnotation(logType = LogTypeEnum.SELECT)
    public BaseResponse<List<CodeSnippetVO>> getAll() {
        List<CodeSnippet> list = codeSnippetService.list();
        List<CodeSnippetVO> codeSnippetVO = CopyUtil.copyList(list, CodeSnippetVO.class);
        return BaseResponse.success(codeSnippetVO);
    }

    @ApiOperation(value = "新增代码片段")
    @PostMapping("/add")
    @SysLogAnnotation(logType = LogTypeEnum.ADD)
    public BaseResponse<Object> add(@Validated({Add.class}) @RequestBody CodeSnippetDTO codeSnippetDTO) {
        if (codeSnippetDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        CodeSnippet codeSnippet = CopyUtil.copy(codeSnippetDTO, CodeSnippet.class);
        codeSnippetService.save(codeSnippet);
        return BaseResponse.success();
    }

    @ApiOperation(value = "修改代码片段")
    @PutMapping("/update")
    @SysLogAnnotation(logType = LogTypeEnum.UPDATE)
    public BaseResponse<Object> update(@Validated({Update.class}) @RequestBody CodeSnippetDTO codeSnippetDTO) {
        CodeSnippet has = codeSnippetService.getById(codeSnippetDTO.getId());
        if (has == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        CodeSnippet codeSnippet = CopyUtil.copy(codeSnippetDTO, CodeSnippet.class);
        codeSnippet.setUpdateTime(new Date());
        codeSnippetService.updateById(codeSnippet);
        return BaseResponse.success();
    }

    @ApiOperation(value = "删除代码片段")
    @DeleteMapping("/delete/{id}")
    @SysLogAnnotation(logType = LogTypeEnum.DELETE)
    public BaseResponse<Object> delete(@PathVariable(value = "id") Long id) {
        if (codeSnippetService.removeById(id)) {
            return BaseResponse.success();
        } else {
            return BaseResponse.fail(ErrorCode.NOT_FOUND_ERROR);
        }
    }
}
