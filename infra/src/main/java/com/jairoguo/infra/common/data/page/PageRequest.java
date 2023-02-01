package com.jairoguo.infra.common.data.page;

import com.jairoguo.infra.common.data.sort.Sort;
import com.jairoguo.infra.common.data.sort.SortBody;
import com.jairoguo.infra.common.data.sort.SortRequest;

/**
 * 通用分页请求工具
 *
 * @author Guo Jinru
 */
public class PageRequest {

  /**
   * 生成通用分页对象
   *
   * @param currentPage 当前页
   * @param pageSize 页数
   * @return IPage
   */
  public static PageParam of(Integer currentPage, Integer pageSize) {
    return of(currentPage, pageSize, new SortBody());
  }

  /**
   * 生成通用分页对象
   *
   * @param currentPage 当前页
   * @param pageSize 页数
   * @param sort 通用排序对象
   * @return IPage
   */
  public static PageParam of(Integer currentPage, Integer pageSize, Sort sort) {
    return new PageParamBody(currentPage, pageSize, sort);
  }

  /**
   * 生成通用分页对象
   *
   * @param pageQuery 分页查询参数对象
   * @return IPage
   */
  public static PageParam of(BasePageQuery pageQuery) {
    return new PageParamBody(
            pageQuery.getCurrent(),
            pageQuery.getSize(),
        SortRequest.by(pageQuery.getSortOrders()));
  }

  private PageRequest() {}
}
