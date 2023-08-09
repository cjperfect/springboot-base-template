package com.cj.snippets.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cj.snippets.model.entity.CodeType;
import com.cj.snippets.model.vo.CodeTypeVO;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface CodeTypeMapper extends BaseMapper<CodeType> {
    List<CodeTypeVO> getAll();
}
