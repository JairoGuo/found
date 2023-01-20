package com.jairoguo.infra.exception;

import java.util.Objects;

/**
 * @author jairoguo
 */
public class ValidationException extends BaseException {

  private final Object info;

  public ValidationException(String code, String msg, Object info) {
    super(code, msg);
    this.info = info;
  }

  public Object getInfo() {
    return info;
  }
}
