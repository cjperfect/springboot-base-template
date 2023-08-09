package com.cj.snippets.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.snippets.mapper.SysUserMapper;
import com.cj.snippets.model.entity.SysUser;
import com.cj.snippets.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUser getLoginUser(HttpServletRequest request) {
        return userMapper.selectById(2);
    }
}
