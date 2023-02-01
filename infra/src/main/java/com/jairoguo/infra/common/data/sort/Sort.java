package com.jairoguo.infra.common.data.sort;

import com.jairoguo.infra.common.data.field.FieldFunction;

import java.util.List;

/**
 * 通用排序接口
 *
 * @author jairoguo
 */
public interface Sort {

  /**
   * 获取通用排序参数列表
   *
   * @return List<SortOrder>
   */
  List<Order> orderList();

  /** 克隆排序对象 */
  Sort cloneSort();

  /**
   * 更新字段排序方式
   *
   * @param column 字段名称
   * @param direction 排序方式
   * @return 返回用于排序构造对象
   */
  SortOrderBuilder update(String column, SortDirectionEnum direction);

  /**
   * 通过lambda方法引用更新排序方式
   *
   * @param column lambda方法引用
   * @param direction 排序方式
   * @return 返回用于排序构造对象
   */
  <T, R> SortOrderBuilder update(FieldFunction<T, R> column, SortDirectionEnum direction);

  SortOrderBuilder update(Order order);

  /** 用于重新生成排序参数列表 */
  void rebuild();
}
