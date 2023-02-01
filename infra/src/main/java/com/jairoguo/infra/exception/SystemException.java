package com.jairoguo.infra.exception;

/**
 * @author jairoguo
 */
public class SystemException extends BaseException{
    public SystemException(String code, String msg) {
        super(code, msg);
    }
}
