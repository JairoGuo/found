package com.jairoguo.infra.util;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.*;

/**
 * @author jairoguo
 */
public class StreamUtil {

  public static <V, K> Map<K, V> toIdentityMap(Collection<V> collection, Function<V, K> key) {
    return toIdentityMap(collection, key, false);
  }

  public static <V, K> Map<K, V> toIdentityMap(
      Collection<V> collection, Function<V, K> key, boolean isParallel) {
    if (CollectionUtil.isEmpty(collection)) {
      return new HashMap<>(0);
    }
    return toMap(
        collection, v -> Optional.ofNullable(v).map(key).get(), Function.identity(), isParallel);
  }

  public static <E, K, V> Map<K, V> toMap(
      Collection<E> collection, Function<E, K> key, Function<E, V> value) {
    return toMap(collection, key, value, false);
  }

  public static <E, K, V> Map<K, V> toMap(
      Collection<E> collection, Function<E, K> key, Function<E, V> value, boolean isParallel) {
    if (CollectionUtil.isEmpty(collection)) {
      return new HashMap<>(0);
    }
    return of(collection, isParallel)
        .collect(HashMap::new, (m, v) -> m.put(key.apply(v), value.apply(v)), HashMap::putAll);
  }

  public static <E, T> List<T> toList(Collection<E> collection, Function<E, T> function) {
    return toList(collection, function, false);
  }

  public static <E, T> List<T> toList(
      Collection<E> collection, Function<E, T> function, Predicate<? super E> filter) {
    if (CollectionUtil.isEmpty(collection)) {
      return new ArrayList<>();
    }
    return of(collection, false)
        .filter(Objects::nonNull)
        .filter(filter)
        .map(function)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  public static <E, T> List<T> toList(
      Collection<E> collection, Function<E, T> function, boolean isParallel) {
    if (CollectionUtil.isEmpty(collection)) {
      return new ArrayList<>();
    }
    return of(collection, isParallel)
        .filter(Objects::nonNull)
        .map(function)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  public static <E, T> List<T> flatList(Collection<E> collection, Function<E, Stream<T>> function) {
    return flatList(collection, function, false);
  }

  /**
   * 将一个流展开转换为列表
   *
   * @param collection 集合
   * @param function 返回流的表达式
   * @param isParallel 是否并行流
   * @return 列表
   * @param <E> 集合元素类型
   * @param <T> 返回列表元素类型
   */
  public static <E, T> List<T> flatList(
      Collection<E> collection, Function<E, Stream<T>> function, boolean isParallel) {
    if (CollectionUtil.isEmpty(collection)) {
      return new ArrayList<>();
    }
    return of(collection, isParallel)
        .filter(Objects::nonNull)
        .flatMap(function)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  /**
   * 获取去重复列表
   *
   * @param collection 集合
   * @param function 获取映射的表达式
   * @return 列表
   * @param <E> 集合元素类型
   * @param <T> 返回列表元素类型
   */
  public static <E, T> List<T> distinctList(Collection<E> collection, Function<E, T> function) {

    return distinctList(collection, function, false);
  }

  public static <E, T> List<T> distinctList(
      Collection<E> collection, Function<E, T> function, boolean isParallel) {
    if (CollectionUtil.isEmpty(collection)) {
      return new ArrayList<>();
    }
    return of(collection, isParallel)
        .filter(Objects::nonNull)
        .map(function)
        .filter(Objects::nonNull)
        .distinct()
        .collect(Collectors.toList());
  }

  public static <E, K> Map<K, List<E>> groupBy(Collection<E> collection, Function<E, K> key) {
    return groupBy(collection, key, false);
  }

  public static <E, K> Map<K, List<E>> groupBy(
      Collection<E> collection, Function<E, K> key, boolean isParallel) {
    if (CollectionUtil.isEmpty(collection)) {
      return new HashMap<>(0);
    }
    return groupBy(collection, key, Collectors.toList(), isParallel);
  }

  public static <E, K, D> Map<K, D> groupBy(
      Collection<E> collection, Function<E, K> key, Collector<E, ?, D> downstream) {
    if (CollectionUtil.isEmpty(collection)) {
      return new HashMap<>(0);
    }
    return groupBy(collection, key, downstream, false);
  }

  public static <E, K, D> Map<K, D> groupBy(
      Collection<E> collection,
      Function<E, K> key,
      Collector<E, ?, D> downstream,
      boolean isParallel) {
    if (CollectionUtil.isEmpty(collection)) {
      return new HashMap<>(0);
    }
    return of(collection, isParallel).collect(Collectors.groupingBy(key, downstream));
  }

  public static <E> DoubleStream mapToDouble(Collection<E> collection, Function<E, Long> key) {
    return map(collection, key).filter(Objects::nonNull).mapToDouble(Long::longValue);
  }

  public static <E> LongStream mapToLong(Collection<E> collection, Function<E, Long> key) {
    return map(collection, key).filter(Objects::nonNull).mapToLong(Long::longValue);
  }

  public static <E> IntStream mapToInt(Collection<E> collection, Function<E, Integer> key) {
    return map(collection, key).filter(Objects::nonNull).mapToInt(Integer::intValue);
  }

  public static <E, K> Stream<K> map(Collection<E> collection, Function<E, K> key) {
    return of(collection, false).filter(Objects::nonNull).map(key);
  }

  /**
   * 获取两个集合的交集
   *
   * <p>两个集合不为null是获取这两个集合的交集，任意为null返回空列表
   *
   * @param collection1 集合1
   * @param collection2 集合2
   * @return 返回集合的交集或空列表
   * @param <E> 集合元素类型
   */
  public static <E> List<E> intersection(Collection<E> collection1, Collection<E> collection2) {
    if (CollectionUtil.allNonNull(collection1, collection1)) {
      return of(collection1, false)
          .distinct()
          .filter(collection2::contains)
          .collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  /**
   * 获取两个集合的差集
   *
   * <p>两个集合不为null是获取这两个集合的差集，任意为null返回空列表
   *
   * @param collection1 集合1
   * @param collection2 集合2
   * @return 返回集合的差集或空列表
   * @param <E> 集合元素类型
   */
  public static <E> List<E> difference(Collection<E> collection1, Collection<E> collection2) {
    if (CollectionUtil.allNonNull(collection1, collection1)) {
      if (collection1.size() > collection2.size()) {
        return of(collection1, false)
            .distinct()
            .filter(item -> !collection2.contains(item))
            .collect(Collectors.toList());
      } else {
        return of(collection2, false)
            .distinct()
            .filter(item -> !collection1.contains(item))
            .collect(Collectors.toList());
      }
    }
    return new ArrayList<>();
  }

  /**
   * 获取两个集合的并集
   *
   * <p>两个集合不为null是获取这两个集合的并集，任意为null返回空列表
   *
   * @param collection1 集合1
   * @param collection2 集合2
   * @return 返回集合的并集或空列表
   * @param <E> 集合元素类型
   */
  public static <E> List<E> union(Collection<E> collection1, Collection<E> collection2) {
    if (CollectionUtil.allNonNull(collection1, collection2)) {
      List<E> result = toList(collection1, Function.identity());
      List<E> data = toList(collection2, Function.identity());
      result.addAll(data);
      return result;
    }
    return new ArrayList<>();
  }

  public static <E> List<E> distinctUnion(Collection<E> collection1, Collection<E> collection2) {
    return distinctList(union(collection1, collection2), Function.identity());
  }

  public static <E1, E2, T> List<T> merge(
      Collection<E1> collection1,
      Collection<E2> collection2,
      BiPredicate<E1, E2> filter,
      BiFunction<E1, E2, T> function) {
    if (CollectionUtil.allNonNull(collection1, collection1)) {
      return of(collection1, false)
          .filter(Objects::nonNull)
          .flatMap(
              col1 ->
                  of(collection2, false)
                      .filter(col2 -> filter.test(col1, col2))
                      .map(col2 -> function.apply(col1, col2)))
          .collect(Collectors.toList());
    }

    return new ArrayList<>();
  }

  @SafeVarargs
  public static <E> List<E> concat(Collection<E>... collection) {
    if (collection.length == 0) {
      return new ArrayList<>();
    }
    List<E> result = new ArrayList<>();
    for (Collection<E> col : collection) {
      result.addAll(toList(col, Function.identity()));
    }
    return result;
  }

  @SafeVarargs
  public static <E> List<E> distinctConcat(Collection<E>... collection) {
    return distinctList(concat(collection), Function.identity());
  }

  @SafeVarargs
  public static <E extends Comparable<? super E>> E max(E... values) {
    return Stream.of(values).filter(Objects::nonNull).max(Comparator.naturalOrder()).orElse(null);
  }

  @SafeVarargs
  public static <E extends Comparable<? super E>> E min(E... values) {
    return Stream.of(values).filter(Objects::nonNull).min(Comparator.naturalOrder()).orElse(null);
  }

  public static <E extends Comparable<? super E>> List<E> sort(Collection<E> collection) {
    return of(collection, false)
        .filter(Objects::nonNull)
        .sorted(Comparator.naturalOrder())
        .collect(Collectors.toList());
  }

  public static <E> Stream<E> nonNullStream(Collection<E> collection) {
    if (CollectionUtil.isEmpty(collection)) {
      return Stream.empty();
    }
    return of(collection, false).filter(Objects::nonNull);
  }

  private static <T> Stream<T> of(Iterable<T> iterable, boolean parallel) {
    if (Objects.isNull(iterable)) {
      throw new IllegalArgumentException("null");
    }

    if (iterable instanceof Collection) {
      if (parallel) {
        return ((Collection<T>) iterable).parallelStream();
      } else {
        return ((Collection<T>) iterable).stream();
      }
    } else {
      return StreamSupport.stream(iterable.spliterator(), parallel);
    }
  }

  private StreamUtil() {}
}
