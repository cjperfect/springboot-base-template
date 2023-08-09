package com.cj.snippets.aop;


import com.cj.snippets.annotation.AuthCheck;
import com.cj.snippets.common.enums.ErrorCode;
import com.cj.snippets.exception.BusinessException;
import com.cj.snippets.model.entity.SysUser;
import com.cj.snippets.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthCheckAspect {

    @Autowired
    private SysUserService userService;


    // 有ProceedingJoinPoint和注解类，有返回值。这样就不用写@Pointcut了
    @Around("@annotation(authCheck)")
    public Object postLogAspect(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        // 获取权限
        String role = authCheck.role();

        // 获取request
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 获取当前登录的用户
        SysUser loginUser = userService.getLoginUser(request);
        if (StringUtils.isNotBlank(role)) {
            String userRole = loginUser.getUserRole();
            System.out.println(userRole);

            if (!role.equals(userRole)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
        return joinPoint.proceed();
    }

}
