package com.cj.snippets.annotation;

import com.cj.snippets.common.enums.LogTypeEnum;

import java.lang.annotation.*;

@Target(ElementType.METHOD) // 注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) // 注解在哪个阶段执行
@Documented // 生成文档
public @interface SysLogAnnotation {
    LogTypeEnum logType() default LogTypeEnum.SELECT;
}
