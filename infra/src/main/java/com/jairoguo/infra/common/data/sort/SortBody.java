package com.jairoguo.infra.common.data.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 实现通用排序中间功能的实现类
 *
 * @author jairoguo
 */
public class SortBody extends BaseSort {

  public SortBody() {
    this.orders = new ArrayList<>();
    this.builder = new SortOrderBuilder();
    this.builder.orderMaps = new HashMap<>();
  }

  public SortBody(List<Order> orders) {
    builder = new SortOrderBuilder();
    this.orders = orders;
  }

  /**
   * 为对象克隆所使用的构造方法
   *
   * @param orders 排序列表
   * @param builder 属性排序参数
   */
  public SortBody(List<Order> orders, SortOrderBuilder builder) {
    this.orders = new ArrayList<>(orders);
    this.builder = new SortOrderBuilder();
    this.builder.orderMaps = new HashMap<>(builder.orderMaps);
  }

  public SortOrderBuilder builder() {
    return this.builder;
  }

  @Override
  public SortParam cloneSort() {
    return new SortBody(orders, builder);
  }
}
