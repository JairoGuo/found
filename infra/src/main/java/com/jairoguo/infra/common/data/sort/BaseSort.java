package com.jairoguo.infra.common.data.sort;

import com.jairoguo.infra.common.data.field.FieldFunction;
import com.jairoguo.infra.common.data.field.FieldUtil;

import java.util.List;

/**
 * 对通用排序的部分抽象实现
 *
 * @author jairoguo
 */
public abstract class BaseSort implements SortParam {

  protected List<Order> orders;

  protected SortOrderBuilder builder;

  @Override
  public SortOrderBuilder update(String column, SortDirectionEnum direction) {
    builder.update(column, direction);
    return builder;
  }

  @Override
  public <T, R> SortOrderBuilder update(FieldFunction<T, R> column, SortDirectionEnum direction) {
    String newColumn = FieldUtil.getField(column);
    builder.update(newColumn, direction);
    return builder;
  }

  @Override
  public SortOrderBuilder update(Order order) {
    builder.update(order.field(), order.direction());
    return builder;
  }

  @Override
  public void rebuild() {
    orders = builder.build();
  }

  @Override
  public List<Order> orderList() {
    return orders;
  }
}
