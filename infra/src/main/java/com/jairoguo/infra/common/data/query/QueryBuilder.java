package com.jairoguo.infra.common.data.query;

import com.jairoguo.infra.common.data.field.FieldFunction;
import com.jairoguo.infra.common.data.sort.SortDirectionEnum;

import java.util.Collection;

/**
 * 查询条件构造接口
 *
 * @author jairoguo
 */
public interface QueryBuilder<T> extends QueryCondition<T> {

  <R> QueryBuilder<T> eq(FieldFunction<T, R> field, R value, boolean allowNull);

  default <R> QueryBuilder<T> eq(FieldFunction<T, R> field, R value) {
    return eq(field, value, false);
  }

  <R> QueryBuilder<T> ne(FieldFunction<T, R> field, R value, boolean allowNull);

  default <R> QueryBuilder<T> ne(FieldFunction<T, R> field, R value) {
    return ne(field, value, false);
  }

  <R> QueryBuilder<T> gt(FieldFunction<T, R> field, R value);

  <R> QueryBuilder<T> lt(FieldFunction<T, R> field, R value);

  <R> QueryBuilder<T> gte(FieldFunction<T, R> field, R value);

  <R> QueryBuilder<T> lte(FieldFunction<T, R> field, R value);

  <R> QueryBuilder<T> in(FieldFunction<T, R> field, Collection<R> value);

  <R> QueryBuilder<T> like(FieldFunction<T, R> field, String value, LikeModeType mode);

  default <R> QueryBuilder<T> like(FieldFunction<T, R> field, String value) {
    return like(field, value, LikeModeType.CONTAINING);
  }

  <R> QueryBuilder<T> sort(FieldFunction<T, R> field, SortDirectionEnum direction);

  QueryField<T> fields();
}
