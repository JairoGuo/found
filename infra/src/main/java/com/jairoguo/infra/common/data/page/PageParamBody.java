package com.jairoguo.infra.common.data.page;

import com.jairoguo.infra.common.data.sort.SortParam;

/**
 * 通用分页
 *
 * @author Guo Jinru
 */
public class PageParamBody implements PageParam {

  private final Integer currentPage;
  private final Integer pageSize;

  private final SortParam sortParam;

  public PageParamBody(Integer currentPage, Integer pageSize, SortParam sortParam) {
    this.currentPage = currentPage;
    this.pageSize = pageSize;
    this.sortParam = sortParam;
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
  public SortParam getSort() {
    return sortParam;
  }
}
