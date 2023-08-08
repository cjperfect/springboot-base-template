package com.cj.snippets.aspect;


import com.cj.snippets.annotation.SysLogAnnotation;
import com.cj.snippets.common.BaseResponse;
import com.cj.snippets.common.enums.ResponseCodeEnum;
import com.cj.snippets.model.entity.SysLog;
import com.cj.snippets.service.SysLogService;
import com.cj.snippets.util.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;

@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;


    // 设置切点
    @Pointcut("@annotation(com.cj.snippets.annotation.SysLogAnnotation)")
    private void pointCut() {
    }


    // 设置接口开始时间和消耗时间
    private Object setSpendTime(ProceedingJoinPoint pjp, SysLog sysLog) throws Throwable {

        // 记录接口访问时间
        long startTime = System.currentTimeMillis();

        // 调用目标接口
        Object result = pjp.proceed(pjp.getArgs());

        // 计算消耗时间 (s)
        long spendTime = (System.currentTimeMillis() - startTime) / 1000;

        sysLog.setOperateTime(new Timestamp(startTime)); // 设置开始时间
        sysLog.setSpendTime(spendTime); // 设置消耗时间

        // 返回结果
        return result;
    }

    // 设置响应状态码
    private void setResponseCode(SysLog sysLog, Object result) {
        // 判断返回体类型是否为BaseResponse
        if (result instanceof BaseResponse) {
            sysLog.setResponseCode(((BaseResponse<?>) result).getCode());
        }
    }


    // 设置请求体
    private void setRequestBody(HttpServletRequest request, SysLog sysLog) throws IOException {
        // ContentCachingRequestWrapper这个类读取请求体
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();

        sysLog.setBody(requestBody);

    }

    // 设置ip地址
    private void setIpAddress(HttpServletRequest request, SysLog sysLog) throws Exception {
        if (request != null) {
            try {
                sysLog.setIp(IPUtils.getIpAddr(request));
            } catch (Exception e) {
                sysLog.setIp(null);
            }
        }

    }

    // 设置操作类型
    private void setLogType(ProceedingJoinPoint pjp, SysLog sysLog) {

        MethodSignature signature = (MethodSignature) pjp.getSignature();

        // 获取切入点所在的方法
        Method method = signature.getMethod();

        SysLogAnnotation annotation = method.getAnnotation(SysLogAnnotation.class);

        sysLog.setLogType(annotation.logType().getType());

    }

    // 创建操作日志
    private SysLog createOperateLog(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        SysLog sysLog = new SysLog();
        sysLog.setQuery(request.getQueryString()); // 设置query参数

        this.setRequestBody(request, sysLog); // 设置请求体
        this.setIpAddress(request, sysLog); // 设置ip地址
        this.setLogType(pjp, sysLog); // 设置操作类型
        sysLog.setUrl(request.getServletPath()); // 设置请求url


        return sysLog;
    }

    //    @Around("execution(public * com.sysLog.project.controller.*.*(..))") // 针对所有controller下面所有方法
    @Around("pointCut()") // 只针对切点的才会执行
    public Object postLogAspect(ProceedingJoinPoint pjp) throws Throwable {

        SysLog sysLog = this.createOperateLog(pjp); // 创建一个操作日志
        Object result = this.setSpendTime(pjp, sysLog); // 设置消耗时间
        this.setResponseCode(sysLog, result);

        if (sysLog.getResponseCode() != ResponseCodeEnum.RC200.getCode()) {
            sysLog.setException(((BaseResponse<?>) result).getMsg());
        }

        sysLogService.save(sysLog);

        return result;
    }

}
