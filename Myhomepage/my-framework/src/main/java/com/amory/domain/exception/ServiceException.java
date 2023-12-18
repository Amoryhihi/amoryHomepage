package com.amory.domain.exception;

public class ServiceException extends RuntimeException {
    private Integer code;
    public ServiceException(String msg) {
        super(msg);
    }
    public ServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }
}
