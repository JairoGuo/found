package com.jairoguo.infra.util;

import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 设置属性值工具
 *
 * <p>节省NULL判断工作，如果为NULL则不进行赋值
 *
 * @author Jairo Guo
 */
public class ValueUtil {

  private static <T> T setValue(T target, T fromValue) {
    if (Modifier.isFinal(target.getClass().getModifiers())) {
      return target;
    }
    if (Objects.nonNull(fromValue) && target.getClass().equals(fromValue.getClass())) {
      target = fromValue;
    }
    return target;
  }

  public static void setIfPresent(Integer fromValue, Integer target) {
    setValue(target, fromValue);
  }

  public static void setIfPresent(Long fromValue, Long target) {
    setValue(target, fromValue);
  }

  public static void setIfPresent(Double fromValue, Double target) {
    setValue(target, fromValue);
  }

  public static void setIfPresent(Boolean fromValue, Boolean target) {
    setValue(target, fromValue);
  }

  public static <T> void acceptIfPresent(T fromValue, Consumer<T> target) {
    if (Objects.nonNull(fromValue)) {
      target.accept(fromValue);
    }
  }

  public static <T> void acceptIfPredicate(T fromValue, boolean predicate, Consumer<T> target) {
    if (predicate) {
      target.accept(fromValue);
    }
  }

  public static <T> void acceptIfPredicate(
      T fromValue, Predicate<T> predicate, Consumer<T> target) {
    if (predicate.test(fromValue)) {
      target.accept(fromValue);
    }
  }

  private ValueUtil() {}
}
