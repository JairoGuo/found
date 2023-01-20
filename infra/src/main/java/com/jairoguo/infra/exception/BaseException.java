package com.jairoguo.infra.exception;

/**
 * @author jairoguo
 */
public abstract class BaseException extends RuntimeException {
    private final String code;
    private final String msg;


    protected BaseException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
