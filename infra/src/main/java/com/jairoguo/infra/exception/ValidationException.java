package com.jairoguo.infra.exception;


import com.jairoguo.infra.common.response.CommonResultType;

/**
 * @author jairoguo
 */
public class ValidationException extends BaseException {

  private final Object info;

  public ValidationException(String code, String msg, Object info) {
    super(code, msg);
    this.info = info;
  }

  public ValidationException(String msg) {
    super(CommonResultType.FAIL.getCode(), msg);
    this.info = null;
  }

  public Object getInfo() {
    return info;
  }
}
