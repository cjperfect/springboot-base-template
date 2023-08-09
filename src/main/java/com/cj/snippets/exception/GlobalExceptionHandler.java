package com.cj.snippets.exception;

import com.cj.snippets.common.BaseResponse;
import com.cj.snippets.common.enums.ErrorCode;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 处理业务异常
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<String> BusinessException(BusinessException e) {
        System.out.println(e.getCode() + e.getMessage());
        return BaseResponse.fail(e.getCode(), e.getMessage());
    }

    // 处理运行时异常
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<String> RuntimeException(RuntimeException e) {

        return BaseResponse.success(e.getMessage());
    }

    // 处理请求参数异常
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public BaseResponse<String> returnExceptionValidError(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();

        List<String> list = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            //获取到所有错误
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                String msg = error.getDefaultMessage();
                list.add(msg);
            }
        }
        return BaseResponse.fail(ErrorCode.PARAMS_ERROR.getCode(), String.join(", ", list));
    }
}
