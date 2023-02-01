package com.jairoguo.infra.common.data.sort;

import java.util.Objects;

/**
 * 通用排序参数对象
 *
 * @author jairoguo
 */
public record SortOrder(SortDirectionEnum direction, String field) implements Order {

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    SortOrder order = (SortOrder) o;
    return Objects.equals(field, order.field);
  }

  @Override
  public String toString() {
    return "SortOrder{" + "column='" + field + '\'' + ", direction=" + direction + '}';
  }
}
