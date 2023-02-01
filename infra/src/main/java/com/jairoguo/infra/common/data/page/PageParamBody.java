package com.jairoguo.infra.common.data.page;

import com.jairoguo.infra.common.data.sort.Sort;

/**
 * 通用分页
 *
 * @author Guo Jinru
 */
public class PageParamBody implements PageParam {

  private final Integer currentPage;
  private final Integer pageSize;

  private final Sort sort;

  public PageParamBody(Integer currentPage, Integer pageSize, Sort sort) {
    this.currentPage = currentPage;
    this.pageSize = pageSize;
    this.sort = sort;
  }

  @Override
  public Integer getSize() {
    return pageSize;
  }

  @Override
  public Integer getCurrent() {
    return currentPage;
  }

  @Override
  public Sort getSort() {
    return sort;
  }
}
