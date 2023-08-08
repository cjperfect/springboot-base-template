package com.cj.snippets.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cj.snippets.model.entity.CodeSnippet;
import com.cj.snippets.model.entity.SysLog;
import org.springframework.stereotype.Component;


@Component
public interface CodeSnippetMapper extends BaseMapper<CodeSnippet> {
}
