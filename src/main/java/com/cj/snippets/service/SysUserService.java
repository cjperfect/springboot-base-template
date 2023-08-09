package com.cj.snippets.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cj.snippets.model.entity.SysLog;
import com.cj.snippets.model.entity.SysUser;

import javax.servlet.http.HttpServletRequest;

public interface SysUserService extends IService<SysUser> {

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    SysUser getLoginUser(HttpServletRequest request);
}
