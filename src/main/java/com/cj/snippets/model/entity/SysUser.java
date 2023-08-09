package com.cj.snippets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;


@TableName("sys_user")
@Data
public class SysUser {
    private Long id;

    private String email; // 邮箱

    @TableField(value = "user_name")
    private String userName; // 用户名

    @TableField(value = "user_password")
    private String password; // 密码

    @TableField(value = "user_role")
    private String userRole; // 用户角色

    @TableField(value = "user_avatar")
    private String userAvatar; // 用户头像

    private Integer gender; // 性别

    private String ip; // ip

    @TableField(value = "is_valid")
    private String isValid; // 是否激活

    @TableField(value = "create_time")
    private Date createTime; // 创建时间

    @TableField(value = "update_time")
    private Date updateTime; // 修改时间

}
