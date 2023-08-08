package com.cj.snippets.controller;

import com.cj.snippets.annotation.SysLogAnnotation;
import com.cj.snippets.common.BaseResponse;
import com.cj.snippets.common.enums.LogTypeEnum;
import com.cj.snippets.common.enums.ErrorCode;
import com.cj.snippets.exception.BusinessException;
import com.cj.snippets.model.dto.CodeSnippetDTO;
import com.cj.snippets.model.entity.CodeSnippet;
import com.cj.snippets.model.vo.CodeSnippetVO;
import com.cj.snippets.service.CodeSnippetService;
import com.cj.snippets.util.CopyUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/snippet")
public class CodeSnippetController {

    @Resource
    private CodeSnippetService codeSnippetService;

    @GetMapping("/getAll")
    @SysLogAnnotation(logType = LogTypeEnum.SELECT)
    public BaseResponse<List<CodeSnippetVO>> getAll() {
        List<CodeSnippet> list = codeSnippetService.list();
        List<CodeSnippetVO> codeSnippetVO = CopyUtil.copyList(list, CodeSnippetVO.class);
        return BaseResponse.success(codeSnippetVO);
    }


    @PostMapping("/add")
    @SysLogAnnotation(logType = LogTypeEnum.ADD)
    public BaseResponse<Object> add(@RequestBody CodeSnippetDTO codeSnippetDTO) {
        CodeSnippet codeSnippet = CopyUtil.copy(codeSnippetDTO, CodeSnippet.class);
        codeSnippetService.save(codeSnippet);
        return BaseResponse.success();
    }


    @PutMapping("/update/{id}")
    @SysLogAnnotation(logType = LogTypeEnum.UPDATE)
    public BaseResponse<Object> update(@PathVariable(value = "id") Long id, @RequestBody CodeSnippetDTO codeSnippetDTO) {
        CodeSnippet has = codeSnippetService.getById(id);
        if (has == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        CodeSnippet codeSnippet = new CodeSnippet();
        codeSnippet.setId(id);
        codeSnippet.setTitle(codeSnippetDTO.getTitle());
        codeSnippet.setSummary(codeSnippetDTO.getSummary());
        codeSnippet.setContent(codeSnippetDTO.getContent());
        codeSnippet.setTypeId(codeSnippetDTO.getTypeId());
        codeSnippet.setUpdateTime(new Date());
        codeSnippetService.updateById(codeSnippet);
        return BaseResponse.success();
    }

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
