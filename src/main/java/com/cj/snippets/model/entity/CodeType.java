package com.cj.snippets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@TableName("code_type")
@Data
public class CodeType {
    private Long id;

    // 分类名称
    @TableField(value = "type_name")
    private String typeName;

    // 父级分类id
    @TableField(value = "p_id")
    private Long pId;

    // 创建时间
    @TableField(value = "create_time")
    private Date createTime;

    // 更新时间
    @TableField(value = "update_time")
    private Date updateTime;

    // 创建用户的id
    @TableField(value = "user_id")
    private Long userId;
}
