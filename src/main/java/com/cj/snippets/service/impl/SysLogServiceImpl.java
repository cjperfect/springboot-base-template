package com.cj.snippets.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.snippets.mapper.SysLogMapper;
import com.cj.snippets.model.entity.SysLog;
import com.cj.snippets.service.SysLogService;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
}
