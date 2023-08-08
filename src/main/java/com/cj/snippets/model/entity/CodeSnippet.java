package com.cj.snippets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@TableName("code_snippet")
@Data
public class CodeSnippet {
    private Long id;

    // 标题
    private String title;

    // 简要
    private String summary;

    // 内容
    private String content;

    // 创建时间
    @TableField(value = "create_time")
    private Date createTime;

    // 更新时间
    @TableField(value = "update_time")
    private Date updateTime;

    // 用户ID
    @TableField(value = "user_id")
    private Long userId;

    // 分类ID
    @TableField(value = "type_id")
    private Long TypeId;
}
