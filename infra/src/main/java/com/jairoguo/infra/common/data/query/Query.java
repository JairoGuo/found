package com.jairoguo.infra.common.data.query;

import java.util.Objects;
/**
 * 通用查询工具
 *
 * @author jairoguo
 */
public class Query {

  public static <T> QueryBuilder<T> query(Class<T> type) {
    Objects.requireNonNull(type);
    return new QueryWrapper<>();
  }

  public static <T> QueryBuilder<T> query() {
    return new QueryWrapper<>();
  }
  private Query() {}
}
