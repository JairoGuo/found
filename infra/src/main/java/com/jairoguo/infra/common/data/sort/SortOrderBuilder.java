package com.jairoguo.infra.common.data.sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用于构建排序字段与排序方式映射关系
 *
 * @author jairoguo
 */
public class SortOrderBuilder {

  protected Map<String, SortDirectionEnum> orderMaps;

  public SortOrderBuilder() {

    this.orderMaps = new HashMap<>();
  }

  public SortOrderBuilder(Map<String, SortDirectionEnum> orderMaps) {
    this.orderMaps = orderMaps;
  }

  public SortOrderBuilder update(String column, SortDirectionEnum direction) {
    add(column, direction);
    return this;
  }

  public SortOrderBuilder add(String column, SortDirectionEnum direction) {
    orderMaps.put(column, direction);
    return this;
  }

  public SortOrderBuilder add(Map<String, SortDirectionEnum> orderMaps) {
    this.orderMaps.putAll(orderMaps);
    return this;
  }

  /**
   * 根据映射关系生成排序参数列表
   *
   * @return List<SortOrder>
   */
  protected List<Order> build() {

    return this.orderMaps.entrySet().stream()
        .map(orderMap -> new SortOrder(orderMap.getValue(), orderMap.getKey()))
        .collect(Collectors.toList());
  }
}
