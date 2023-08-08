package com.cj.snippets.exception;

import com.cj.snippets.common.enums.ErrorCode;

public class BusinessException extends RuntimeException {
    private final int code;

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }


    public BusinessException(ErrorCode errorCode) {
        super((errorCode.getMsg()));
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return this.code;
    }


}
