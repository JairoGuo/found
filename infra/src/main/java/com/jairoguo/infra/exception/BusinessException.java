package com.jairoguo.infra.exception;

/**
 * @author jairoguo
 */
public class BusinessException extends BaseException {

  public BusinessException(String code, String msg) {
    super(code, msg);
  }
}
