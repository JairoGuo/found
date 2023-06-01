package com.jairoguo.infra.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jairo
 * @since 2023/6/1
 */
public class SplitHandlerUtil {

  public static <T, R> void handler(List<T> values, long limit, Function<List<T>, R> processing) {

    long region = getRegion(values, limit);

    Stream.iterate(0, n -> n + 1)
        .limit(region)
        .forEach(
            i -> {
              List<T> subDataSet =
                  values.stream().skip((long) i * limit).limit(limit).collect(Collectors.toList());

              processing.apply(subDataSet);
            });
  }

  public static <T, R> void handler(List<T> values, int limit, Function<List<T>, R> processing) {

    handler(values, (long) limit, processing);
  }

  private static <T> long getRegion(List<T> values, Long limit) {
    int size = values.size();
    long region = size / limit;

    region += size % limit == 0 ? 0 : 1;
    return region;
  }

  private SplitHandlerUtil() {}
}
