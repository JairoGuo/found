package com.jairoguo.infra.common.data.sort;

import com.jairoguo.infra.common.data.field.FieldFunction;
import com.jairoguo.infra.common.data.field.FieldUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 通用排序
 *
 * @author jairoguo
 */
public class SortRequest {

  /**
   * 通过排序方式和字段名生成通用排序对象
   *
   * @param direction 排序方式
   * @param column 字段名
   * @return Sort
   */
  public static Sort by(SortDirectionEnum direction, String column) {
    Order order = new SortOrder(direction, column);
    List<Order> orders = Collections.singletonList(order);
    SortBody sort = new SortBody(orders);
    sort.builder().add(column, direction);
    return sort;
  }

  /**
   * 通过方法引用方式生成通用排序对象
   *
   * @param direction 排序方式
   * @param field 方法引用
   * @return Sort
   */
  public static <T, R> Sort by(SortDirectionEnum direction, FieldFunction<T, R> field) {
    String fieldString = FieldUtil.getField(field);
    return by(direction, fieldString);
  }

  /**
   * 通过可变参数方式生成通用排序对象
   *
   * @param direction 排序方式
   * @param fields 字段名数组
   * @return Sort
   */
  public static Sort by(SortDirectionEnum direction, String... fields) {
    HashMap<String, SortDirectionEnum> orderMaps = new HashMap<>();
    // 通过字段数组返回排序参数列表
    List<Order> orderList =
        Arrays.stream(fields)
            .map(
                col -> {
                  orderMaps.put(col, direction);
                  return new SortOrder(direction, col);
                })
            .collect(Collectors.toList());
    SortBody sort = new SortBody(orderList);
    sort.builder().add(orderMaps);
    return sort;
  }

  /**
   * 通过可变参数方式生成通用排序对象（方法引用形式）
   *
   * @param direction 排序方式
   * @param columns 方法引用数组
   * @return Sort
   */
  @SafeVarargs
  public static <T, R> Sort by(SortDirectionEnum direction, FieldFunction<T, R>... columns) {
    String[] columnArray = Arrays.stream(columns).map(FieldUtil::getField).toArray(String[]::new);
    return by(direction, columnArray);
  }

  /**
   * 通过排序参数列表生成通用排序对象
   *
   * @param orders 排序参数列表
   * @return Sort
   */
  public static Sort by(List<Order> orders) {
    SortBody sort = new SortBody(orders);
    Map<String, SortDirectionEnum> orderMaps =
        orders.stream().collect(Collectors.toMap(Order::field, Order::direction, (k1, k2) -> k2));
    sort.builder().add(orderMaps);
    return sort;
  }

  /**
   * 通过排序参数对象生成通用排序对象
   *
   * @param order 排序参数对象
   * @return Sort
   */
  public static Sort by(Order order) {

    return by(order.direction(), order.field());
  }

  /**
   * 通过排序参数对象数组生成通用排序对象
   *
   * @param orders 排序参数对象数组
   * @return Sort
   */
  public static Sort by(Order... orders) {
    if (orders.length == 0) {
      return new SortBody();
    }
    HashMap<String, SortDirectionEnum> orderMaps = new HashMap<>();

    List<Order> orderList =
        Arrays.stream(orders)
            .map(
                order -> {
                  String column;
                  SortDirectionEnum direction;
                  column = order.field();
                  direction = order.direction();
                  orderMaps.put(column, direction);
                  return new SortOrder(direction, column);
                })
            .collect(Collectors.toList());

    SortBody sort = new SortBody(orderList);
    sort.builder().add(orderMaps);
    return sort;
  }

  private SortRequest() {}
}
