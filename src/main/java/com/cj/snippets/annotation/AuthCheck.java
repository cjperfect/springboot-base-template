package com.cj.snippets.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD) // 注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) // 注解在哪个阶段执行
@Documented // 生成文档
public @interface AuthCheck {

    // 必须要某种角色才能访问
    String role() default "";
}
