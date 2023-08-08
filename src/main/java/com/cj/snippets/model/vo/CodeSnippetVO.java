package com.cj.snippets.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class CodeSnippetVO {

    // 标题
    private String title;

    // 简要
    private String summary;

    // 内容
    private String content;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    // 用户ID
    private Long userId;

    // 分类ID
    private Long TypeId;
}
