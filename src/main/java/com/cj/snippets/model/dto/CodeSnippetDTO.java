package com.cj.snippets.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class CodeSnippetDTO {
    private Long id;

    // 标题
    @NotBlank(message = "标题不能为空")
    private String title;

    // 简要
    private String summary;

    // 内容
    @NotBlank(message = "内容不能为空")
    private String content;

    // 用户ID
    private Long userId;

    // 分类ID
    private Long TypeId;
}
