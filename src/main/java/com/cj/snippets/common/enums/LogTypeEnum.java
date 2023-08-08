package com.cj.snippets.common.enums;

/**
 * 日志操作类型枚举
 */
public enum LogTypeEnum {
    SELECT("查询操作"),
    UPDATE("更新操作"),
    DELETE("删除操作"),
    ADD("新增操作");

    private String type;


    LogTypeEnum(String type) {
        this.type = type;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
