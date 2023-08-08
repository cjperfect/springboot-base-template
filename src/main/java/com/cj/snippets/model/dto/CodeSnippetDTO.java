package com.cj.snippets.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;


@Data
public class CodeSnippetDTO {
    private Long id;

    // 标题
    private String title;

    // 简要
    private String summary;

    // 内容
    private String content;

    // 用户ID
    private Long userId;

    // 分类ID
    private Long TypeId;
}
