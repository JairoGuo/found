package com.jairoguo.infra.util.asserts;

import com.jairoguo.infra.exception.ValidationException;

import java.util.Objects;

/**
 * @author Guo Jinru
 */
public class AssertUtil {

  public static void is(String value, Check check) {
    is(value, check, null);
  }

  /** 断言value是否为符合指定规则，不符合抛出异常 */
  public static void is(String value, Check check, String msg) {
    if (Boolean.FALSE.equals(check.check(value))) {
      throw new ValidationException(
          String.format("%s: %s", value, Objects.isNull(msg) ? check.getDefaultMsg() : msg));
    }
  }

  private AssertUtil() {}
}
