package com.jairoguo.infra.common.data.query;

import com.jairoguo.infra.common.data.field.FieldFunction;
import com.jairoguo.infra.common.data.sort.SortParam;
import com.jairoguo.infra.common.data.sort.SortBody;
import com.jairoguo.infra.common.data.sort.SortDirectionEnum;

import java.util.*;

/**
 * 查询条件关系映射
 *
 * @author jairoguo
 */
public class QueryConditionMapper<T> {
  private Map<FieldFunction<T, ?>, Object> isParams;
  private Map<FieldFunction<T, ?>, Object> neParams;
  private Map<FieldFunction<T, ?>, Object> gtParams;
  private Map<FieldFunction<T, ?>, Object> ltParams;
  private Map<FieldFunction<T, ?>, Object> gteParams;
  private Map<FieldFunction<T, ?>, Object> lteParams;
  private Map<FieldFunction<T, ?>, Object> likeParams;
  private Map<FieldFunction<T, ?>, Collection<?>> inParams;
  private SortParam sortParam;
  private Set<FieldFunction<T, ?>> includeField;
  private Set<FieldFunction<T, ?>> excludeField;

  public <R> void isParamsAdd(FieldFunction<T, R> field, R value) {
    if (isParams == null) {
      isParams = new HashMap<>();
    }
    isParams.put(field, value);
  }

  public <R> void neParamsAdd(FieldFunction<T, R> field, R value) {
    if (neParams == null) {
      neParams = new HashMap<>();
    }
    neParams.put(field, value);
  }

  public <R> void gtParamsAdd(FieldFunction<T, R> field, R value) {
    if (gtParams == null) {
      gtParams = new HashMap<>();
    }
    gtParams.put(field, value);
  }

  public <R> void ltParamsAdd(FieldFunction<T, R> field, R value) {
    if (ltParams == null) {
      ltParams = new HashMap<>();
    }
    ltParams.put(field, value);
  }

  public <R> void gteParamsAdd(FieldFunction<T, R> field, R value) {
    if (gteParams == null) {
      gteParams = new HashMap<>();
    }
    gteParams.put(field, value);
  }

  public <R> void lteParamsAdd(FieldFunction<T, R> field, R value) {
    if (lteParams == null) {
      lteParams = new HashMap<>();
    }
    lteParams.put(field, value);
  }

  public <R> void likeParamsAdd(FieldFunction<T, R> field, String value) {
    if (likeParams == null) {
      likeParams = new HashMap<>();
    }
    likeParams.put(field, value);
  }

  public <R> void inParamsAdd(FieldFunction<T, R> field, Collection<R> value) {
    if (inParams == null) {
      inParams = new HashMap<>();
    }
    inParams.put(field, value);
  }

  public <R> void sortAdd(FieldFunction<T, R> field, SortDirectionEnum direction) {
    if (sortParam == null) {
      sortParam = new SortBody();
    }
    sortParam.update(field, direction);
  }

  public <R> void includeFieldAdd(FieldFunction<T, R> field) {
    if (includeField == null) {
      includeField = new HashSet<>();
    }
    includeField.add(field);
  }

  public <R> void excludeFieldAdd(FieldFunction<T, R> field) {
    if (excludeField == null) {
      excludeField = new HashSet<>();
    }
    excludeField.add(field);
  }

  public Map<FieldFunction<T, ?>, Object> getIsParams() {
    return isParams;
  }

  public Map<FieldFunction<T, ?>, Object> getNeParams() {
    return neParams;
  }

  public Map<FieldFunction<T, ?>, Object> getGtParams() {
    return gtParams;
  }

  public Map<FieldFunction<T, ?>, Object> getLtParams() {
    return ltParams;
  }

  public Map<FieldFunction<T, ?>, Object> getGteParams() {
    return gteParams;
  }

  public Map<FieldFunction<T, ?>, Object> getLteParams() {
    return lteParams;
  }

  public Map<FieldFunction<T, ?>, Object> getLikeParams() {
    return likeParams;
  }

  public Map<FieldFunction<T, ?>, Collection<?>> getInParams() {
    return inParams;
  }

  public SortParam getSort() {
    return sortParam;
  }

  public Set<FieldFunction<T, ?>> getIncludeField() {
    return includeField;
  }

  public Set<FieldFunction<T, ?>> getExcludeField() {
    return excludeField;
  }

  @Override
  public String toString() {
    return "QueryConditionMapper{"
        + "isParams="
        + isParams
        + ", neParams="
        + neParams
        + ", gtParams="
        + gtParams
        + ", ltParams="
        + ltParams
        + ", gteParams="
        + gteParams
        + ", lteParams="
        + lteParams
        + ", likeParams="
        + likeParams
        + ", inParams="
        + inParams
        + ", sort="
        + sortParam
        + ", includeField="
        + includeField
        + ", excludeField="
        + excludeField
        + '}';
  }
}
