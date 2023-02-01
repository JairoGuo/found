package com.jairoguo.infra.common.data.page;

import com.jairoguo.infra.common.data.sort.Order;
import com.jairoguo.infra.common.data.sort.Sort;
import com.jairoguo.infra.common.data.sort.SortRequest;
import com.jairoguo.infra.util.CollectionUtil;

import java.util.List;

/**
 * 分页查询参数
 *
 * <p>使用: 1. Controller请求参数继承此抽象类完成用户端参数传递
 *
 * @author jairoguo
 */
public abstract class BasePageQuery {
  private Integer currentPage;
  private Integer pageSize;
  private List<Order> orders;

  public List<Order> getSortOrders() {
    return orders;
  }

  public Integer getSize() {
    return pageSize;
  }

  public Integer getCurrent() {
    return currentPage;
  }

  /**
   * 将查询参数转换为通用分页对象
   *
   * @return IPage
   */
  public PageParam toPage() {
    if (CollectionUtil.isEmpty(orders)) {
      return PageRequest.of(this.currentPage, this.pageSize);
    } else {
      return PageRequest.of(this.currentPage, this.pageSize, SortRequest.by(this.orders));
    }
  }

  public Sort toSort() {
    if (CollectionUtil.nonEmpty(orders)) {
      return SortRequest.by(this.orders);
    }
    return null;
  }

  public void of(Integer currentPage, Integer pageSize) {
    this.currentPage = currentPage;
    this.pageSize = pageSize;
  }

  public void orders(List<Order> orders) {
    this.orders = orders;
  }
}
