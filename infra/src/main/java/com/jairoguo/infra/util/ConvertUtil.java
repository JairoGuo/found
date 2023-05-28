package com.jairoguo.infra.util;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author jairoguo
 */
public class ConvertUtil {
  public static <T, R> List<R> list(List<T> list, Function<T, R> to) {
    return list(list, to, false);
  }

  public static <T, R> List<R> list(List<T> list, Function<T, R> to, boolean isParallel) {
    if (list == null || list.isEmpty()) {
      return Collections.emptyList();
    }

    return StreamUtil.toList(list, item -> to(item, to), isParallel);
  }

  public static <T, R> List<R> list(List<T> list, Supplier<R> to) {
    if (list == null || list.isEmpty()) {
      return Collections.emptyList();
    }

    return list.stream().map(item -> to(item, to)).toList();
  }

  public static <T, R> R to(T o, Function<T, R> to) {
    if (o == null) {
      return null;
    }
    return to.apply(o);
  }

  public static <T, R> R to(T o, Supplier<R> to) {
    if (o == null) {
      return null;
    }
    return to.get();
  }

  private ConvertUtil() {}
}
