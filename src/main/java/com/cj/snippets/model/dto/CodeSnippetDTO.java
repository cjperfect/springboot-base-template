package com.cj.snippets.model.dto;

import com.cj.snippets.common.group.Add;
import com.cj.snippets.common.group.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class CodeSnippetDTO {
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long id;

    // 标题
    @NotBlank(message = "标题不能为空", groups = {Add.class, Update.class})
    private String title;

    // 简要
    private String summary;

    // 内容
    @NotBlank(message = "内容不能为空", groups = {Add.class, Update.class})
    private String content;

    // 用户ID
    @NotNull(message = "用户ID不能为空", groups = {Add.class, Update.class})
    private Long userId;

    // 分类ID
    private Long TypeId;
}
