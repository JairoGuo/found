package com.jairoguo.infra.util;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 设置属性值工具
 *
 * <p>节省NULL判断工作，如果为NULL则不进行赋值
 *
 * @author Jairo Guo
 */
public class ValueUtil {

  private static <T> T setValue(T target, T fromValue) {
    if (Objects.nonNull(fromValue) && target.getClass().equals(fromValue.getClass())) {
      target = fromValue;
    }
    return target;
  }

  public static <T> void setIfPresent(T fromValue, T target) {
    setValue(target, fromValue);
  }

  public static void setIfPresent(String fromValue, String target) {
    setValue(target, fromValue);
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

  public static <T> void setIfPresent(T fromValue, Consumer<T> target) {
    if (Objects.nonNull(fromValue)) {
      target.accept(fromValue);
    }
  }

  public static <T> void setIfPresent(Collection<T> fromValue, Consumer<Collection<T>> target) {
    if (CollectionUtil.nonEmpty(fromValue)) {
      target.accept(fromValue);
    }
  }

  public static <T> void handleIfPredicate(boolean predicate, T fromValue, Consumer<T> target) {
    if (predicate) {
      target.accept(fromValue);
    }
  }

  private ValueUtil() {}

}
