package com.cj.snippets.common;

import com.cj.snippets.common.enums.ErrorCode;
import lombok.Data;

@Data
public class BaseResponse<T> {

    private int code;

    private String msg;

    private T data;


    public static <T> BaseResponse<T> success() {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(ErrorCode.SUCCESS.getCode());
        baseResponse.setMsg(ErrorCode.SUCCESS.getMsg());
        return baseResponse;
    }


    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(ErrorCode.SUCCESS.getCode());
        baseResponse.setMsg(ErrorCode.SUCCESS.getMsg());
        baseResponse.setData(data);
        return baseResponse;
    }


    public static <T> BaseResponse<T> fail(ErrorCode errorCode) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(errorCode.getCode());
        baseResponse.setMsg(errorCode.getMsg());
        return baseResponse;
    }

    public static <T> BaseResponse<T> fail(int code, String msg) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(code);
        baseResponse.setMsg(msg);
        return baseResponse;
    }
}