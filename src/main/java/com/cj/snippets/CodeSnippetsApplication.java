package com.cj.snippets;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cj.snippets.mapper")
public class CodeSnippetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeSnippetsApplication.class, args);
    }

}
