package com.jairoguo.infra.common.data.query;

import com.jairoguo.infra.common.data.field.FieldFunction;
import com.jairoguo.infra.common.data.sort.SortDirectionEnum;
import com.jairoguo.infra.util.ValueUtil;

import java.util.Collection;
import java.util.Objects;

/**
 * 查询条件构造实现
 *
 * @author jairoguo
 */
public class QueryWrapper<T> implements QueryBuilder<T> {

  protected QueryConditionMapper<T> queryCondition;

  @Override
  public QueryConditionMapper<T> getCondition() {
    return queryCondition;
  }

  @Override
  public <R> QueryBuilder<T> eq(FieldFunction<T, R> field, R value, boolean allowNull) {
    if (!allowNull && Objects.isNull(value)) {
      return this;
    }
    queryCondition.isParamsAdd(field, value);
    return this;
  }

  @Override
  public <R> QueryBuilder<T> ne(FieldFunction<T, R> field, R value, boolean allowNull) {
    if (!allowNull && Objects.isNull(value)) {
      return this;
    }
    queryCondition.neParamsAdd(field, value);
    return this;
  }

  @Override
  public <R> QueryBuilder<T> gt(FieldFunction<T, R> field, R value) {
    ValueUtil.setIfPresent(value, v -> queryCondition.gtParamsAdd(field, v));
    return this;
  }

  @Override
  public <R> QueryBuilder<T> lt(FieldFunction<T, R> field, R value) {
    ValueUtil.setIfPresent(value, v -> queryCondition.ltParamsAdd(field, v));
    return this;
  }

  @Override
  public <R> QueryBuilder<T> gte(FieldFunction<T, R> field, R value) {
    ValueUtil.setIfPresent(value, v -> queryCondition.gteParamsAdd(field, v));
    return this;
  }

  @Override
  public <R> QueryBuilder<T> lte(FieldFunction<T, R> field, R value) {
    ValueUtil.setIfPresent(value, v -> queryCondition.lteParamsAdd(field, v));
    return this;
  }

  @Override
  public <R> QueryBuilder<T> in(FieldFunction<T, R> field, Collection<R> value) {
    ValueUtil.setIfPresent(value, v -> queryCondition.inParamsAdd(field, v));
    return this;
  }

  @Override
  public <R> QueryBuilder<T> like(FieldFunction<T, R> field, String value, LikeModeType mode) {
    ValueUtil.setIfPresent(value, v -> queryCondition.likeParamsAdd(field, mode.getRegex(v)));
    return this;
  }

  @Override
  public <R> QueryBuilder<T> sort(FieldFunction<T, R> field, SortDirectionEnum direction) {
    queryCondition.sortAdd(field, direction);
    return this;
  }

  @Override
  public QueryField<T> fields() {
    return new FieldHandle();
  }

  class FieldHandle implements QueryField<T> {

    @Override
    public <R> QueryField<T> include(FieldFunction<T, R> field) {
      queryCondition.includeFieldAdd(field);
      return this;
    }

    @Override
    public <R> QueryField<T> exclude(FieldFunction<T, R> field) {
      queryCondition.excludeFieldAdd(field);
      return this;
    }
  }

  QueryWrapper() {
    this.queryCondition = new QueryConditionMapper<>();
  }
}
