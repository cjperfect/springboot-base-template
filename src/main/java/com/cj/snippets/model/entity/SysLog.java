package com.cj.snippets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;


@TableName("sys_log")
@Data
public class SysLog {
    private Long id;

    @TableField(value = "log_type")
    private String logType; // 日志类型

    @TableField(value = "response_code")
    private String responseCode; // 响应状态码

    @TableField(value = "operate_time")
    private Timestamp operateTime; // 操作时间

    @TableField(value = "spend_time")
    private Long spendTime; // 消耗时间

    private String url; // 请求url

    private String body; // 请求体

    private String ip; // ip

    private String query; // 请求参数

    private String exception; // 异常信息

}
