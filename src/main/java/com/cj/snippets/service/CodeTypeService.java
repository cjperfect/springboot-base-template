package com.cj.snippets.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cj.snippets.model.entity.CodeType;
import com.cj.snippets.model.vo.CodeTypeVO;

import java.util.List;


public interface CodeTypeService extends IService<CodeType> {
    List<CodeTypeVO> getAll();
}
