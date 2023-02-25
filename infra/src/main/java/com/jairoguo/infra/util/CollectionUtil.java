package com.jairoguo.infra.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 集合判断工具
 *
 * @author jairoguo
 */
public class CollectionUtil {
  /**
   * 集合为空
   *
   * @param collection 集合
   * @return collection为空返回true; collection不为空返回false;
   */
  public static boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  /**
   * 集合不为空
   *
   * @param collection 集合
   * @return collection不为空返回true; collection为空返回false;
   */
  public static boolean nonEmpty(Collection<?> collection) {
    return collection != null && !collection.isEmpty();
  }

  /**
   * Map为空
   *
   * @param map Map
   * @return map为空返回true; map不为空返回false;
   */
  public static boolean isEmpty(Map<?, ?> map) {
    return map == null || map.isEmpty();
  }

  /**
   * Map不为空
   *
   * @param map Map
   * @return map不为空返回true; map为空返回false;
   */
  public static boolean nonEmpty(Map<?, ?> map) {
    return map != null && !map.isEmpty();
  }

  /**
   * 判断所有对象全部不为null
   *
   * @param objects 对象参数
   * @return 所有对象全部不为null返回true，否则false
   */
  public static boolean allNonNull(Object... objects) {
    return Arrays.stream(objects).allMatch(Objects::nonNull);
  }

  /**
   * 判断任意对象不为null
   *
   * @param objects 对象参数
   * @return 任意对象全部不为null返回true，否则false
   */
  public static boolean anyNonNull(Object... objects) {
    return Arrays.stream(objects).anyMatch(Objects::nonNull);
  }

  /**
   * 判断全部对象为null
   *
   * @param objects 对象参数
   * @return 所有对象null返回true，否则false
   */
  public static boolean anyNull(Object... objects) {
    return Arrays.stream(objects).anyMatch(Objects::isNull);
  }

  /**
   * 判断任意对象为null
   *
   * @param objects 对象参数
   * @return 任意对象为null返回true，否则false
   */
  public static boolean allNull(Object... objects) {
    return Arrays.stream(objects).allMatch(Objects::isNull);
  }
  private CollectionUtil() {}
}
