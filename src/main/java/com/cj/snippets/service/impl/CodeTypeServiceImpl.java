package com.cj.snippets.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.snippets.mapper.CodeTypeMapper;
import com.cj.snippets.model.entity.CodeType;
import com.cj.snippets.model.vo.CodeTypeVO;
import com.cj.snippets.service.CodeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeTypeServiceImpl extends ServiceImpl<CodeTypeMapper, CodeType> implements CodeTypeService {

    @Autowired
    private CodeTypeMapper codeTypeMapper;


    @Override
    public List<CodeTypeVO> getAll() {
        return codeTypeMapper.getAll();
    }
}
