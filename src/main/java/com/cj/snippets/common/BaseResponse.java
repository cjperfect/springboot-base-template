package com.cj.snippets.common;

import com.cj.snippets.common.enums.ResponseCodeEnum;
import lombok.Data;

@Data
public class BaseResponse<T> {

    private String code;

    private String msg;

    private T data;


    public static <T> BaseResponse<T> success() {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(ResponseCodeEnum.RC200.getCode());
        baseResponse.setMsg(ResponseCodeEnum.RC200.getMsg());
        return baseResponse;
    }


    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(ResponseCodeEnum.RC200.getCode());
        baseResponse.setMsg(ResponseCodeEnum.RC200.getMsg());
        baseResponse.setData(data);
        return baseResponse;
    }




    public static <T> BaseResponse<T> fail(String code, String msg) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(code);
        baseResponse.setMsg(msg);
        return baseResponse;
    }
}