package com.jairoguo.infra.exception;
/**
 * @author Jairo
 * @since 2023/7/14
 */
public class LockException extends SystemException {
  public LockException(String msg) {
    super(null, msg);
  }
}
