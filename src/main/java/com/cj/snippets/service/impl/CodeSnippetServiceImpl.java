package com.cj.snippets.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.snippets.mapper.CodeSnippetMapper;
import com.cj.snippets.model.entity.CodeSnippet;
import com.cj.snippets.service.CodeSnippetService;
import org.springframework.stereotype.Service;

@Service
public class CodeSnippetServiceImpl extends ServiceImpl<CodeSnippetMapper, CodeSnippet> implements CodeSnippetService {
}
