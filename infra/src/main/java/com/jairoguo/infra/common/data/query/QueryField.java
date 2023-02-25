package com.jairoguo.infra.common.data.query;

import com.jairoguo.infra.common.data.field.FieldFunction;

/**
 * 字段包含与排除接口
 *
 * @author jairoguo
 */
public interface QueryField<T> {

  <R> QueryField<T> include(FieldFunction<T, R> field);

  <R> QueryField<T> exclude(FieldFunction<T, R> field);
}
