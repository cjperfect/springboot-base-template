package com.cj.snippets.controller;

import com.cj.snippets.annotation.SysLogAnnotation;
import com.cj.snippets.common.BaseResponse;
import com.cj.snippets.common.enums.ErrorCode;
import com.cj.snippets.common.enums.LogTypeEnum;
import com.cj.snippets.common.group.Add;
import com.cj.snippets.common.group.Update;
import com.cj.snippets.exception.BusinessException;
import com.cj.snippets.model.dto.CodeTypeDTO;
import com.cj.snippets.model.entity.CodeType;
import com.cj.snippets.model.vo.CodeTypeVO;
import com.cj.snippets.service.CodeTypeService;
import com.cj.snippets.util.CopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "分类Controller")
@RestController
@RequestMapping("/codeType")
public class CodeTypeController {

    @Autowired
    private CodeTypeService codeTypeService;

    @ApiOperation(value = "查询所有")
    @GetMapping("/getAll")
    @SysLogAnnotation(logType = LogTypeEnum.SELECT)
    public BaseResponse<List<CodeTypeVO>> getAll() {
        List<CodeTypeVO> list = codeTypeService.getAll();
        return BaseResponse.success(list);
    }

    @ApiOperation(value = "新增分类")
    @PostMapping("/add")
    @SysLogAnnotation(logType = LogTypeEnum.ADD)
    public BaseResponse<Object> add(@Validated({Add.class}) @RequestBody CodeTypeDTO codeTypeDTO) {
        if (codeTypeDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        CodeType codeType = CopyUtil.copy(codeTypeDTO, CodeType.class);
        codeTypeService.save(codeType);
        return BaseResponse.success();
    }

    @ApiOperation(value = "修改分类")
    @PutMapping("/update")
    @SysLogAnnotation(logType = LogTypeEnum.UPDATE)
    public BaseResponse<Object> update(@Validated({Update.class}) @RequestBody CodeTypeDTO codeTypeDTO) {
        CodeType has = codeTypeService.getById(codeTypeDTO.getId());
        if (has == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        CodeType codeType = CopyUtil.copy(codeTypeDTO, CodeType.class);
        codeTypeService.updateById(codeType);
        return BaseResponse.success();
    }

    @ApiOperation(value = "删除分类")
    @DeleteMapping("/delete/{id}")
    @SysLogAnnotation(logType = LogTypeEnum.DELETE)
    public BaseResponse<Object> delete(@PathVariable(value = "id") Long id) {
        if (codeTypeService.removeById(id)) {
            return BaseResponse.success();
        } else {
            return BaseResponse.fail(ErrorCode.NOT_FOUND_ERROR);
        }
    }
}
