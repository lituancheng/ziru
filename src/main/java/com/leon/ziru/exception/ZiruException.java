package com.leon.ziru.exception;

public class ZiruException extends RuntimeException {

    private Integer errorCode;
    private String errMessage;

    public ZiruException(Integer errorCode, String errMessage) {
        this.errorCode = errorCode;
        this.errMessage = errMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrMessage() {
        return errMessage;
    }
}
