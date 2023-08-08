package com.cj.snippets.controller;

import com.cj.snippets.common.BaseResponse;
import com.cj.snippets.common.enums.ResponseCodeEnum;
import com.cj.snippets.model.dto.CodeSnippetDTO;
import com.cj.snippets.model.entity.CodeSnippet;
import com.cj.snippets.model.vo.CodeSnippetVO;
import com.cj.snippets.service.CodeSnippetService;
import com.cj.snippets.util.CopyUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/snippet")
public class CodeSnippetController {

    @Resource
    private CodeSnippetService codeSnippetService;

    @GetMapping("/getAll")
    public BaseResponse<List<CodeSnippetVO>> getAll() {
        List<CodeSnippet> list = codeSnippetService.list();
        List<CodeSnippetVO> codeSnippetVO = CopyUtil.copyList(list, CodeSnippetVO.class);
        return BaseResponse.success(codeSnippetVO);
    }


    @PostMapping("/add")
    public BaseResponse<Object> add(@RequestBody CodeSnippetDTO codeSnippetDTO) {
        CodeSnippet codeSnippet = CopyUtil.copy(codeSnippetDTO, CodeSnippet.class);
        codeSnippetService.save(codeSnippet);
        return BaseResponse.success();
    }


    @PutMapping("/update")
    public BaseResponse<Object> update(@RequestBody CodeSnippetDTO codeSnippetDTO) {
        CodeSnippet codeSnippet = CopyUtil.copy(codeSnippetDTO, CodeSnippet.class);
        System.out.println(codeSnippet);
        codeSnippetService.updateById(codeSnippet);
        return BaseResponse.success();
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<Object> delete(@PathVariable(value = "id") Long id) {
        if (codeSnippetService.removeById(id)) {
            return BaseResponse.success();
        } else {
            return BaseResponse.fail(ResponseCodeEnum.RC204.getCode(), "删除失败, id不存在");
        }
    }
}
