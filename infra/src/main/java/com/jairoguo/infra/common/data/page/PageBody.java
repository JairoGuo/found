package com.jairoguo.infra.common.data.page;

import com.jairoguo.infra.util.ConvertUtil;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**
 * @author jairoguo
 */
public class PageBody<T> implements Serializable {

  private final Long total;
  private final Long totalPage;

  private final Integer currentPage;
  private final Integer pageSize;
  private final List<T> data;

  public PageBody(Builder<T> builder) {
    this.total = builder.getTotal();
    this.totalPage = builder.getTotalPage();
    this.currentPage = builder.getCurrentPage();
    this.pageSize = builder.getPageSize();
    this.data = builder.getData();
  }

  public PageBody(
      Long total, Long totalPage, Integer currentPage, Integer pageSize, List<T> data) {
    this.total = total;
    this.totalPage = totalPage;
    this.currentPage = currentPage;
    this.pageSize = pageSize;
    this.data = data;
  }

  public static <T> Builder<T> builder() {
    return new Builder<>();
  }

  static class Builder<R> {
    private Long total;

    private Long totalPage;
    private Integer currentPage;
    private Integer pageSize;
    private List<R> data;

    public Builder() {
      this.total = null;
      this.currentPage = null;
      this.pageSize = null;
      this.data = null;
    }

    public Builder<R> total(Long total) {
      this.total = total;
      return this;
    }

    public Builder<R> totalPage(Long totalPage) {
      this.totalPage = totalPage;
      return this;
    }

    public Builder<R> currentPage(Integer currentPage) {
      this.currentPage = currentPage;
      return this;
    }

    public Builder<R> pageSize(Integer pageSize) {
      this.pageSize = pageSize;
      return this;
    }

    public Builder<R> data(List<R> data) {
      this.data = data;
      return this;
    }

    public PageBody<R> build() {
      return new PageBody<>(this);
    }

    public Long getTotal() {
      return total;
    }

    public Long getTotalPage() {
      return totalPage;
    }

    public Integer getCurrentPage() {
      return currentPage;
    }

    public Integer getPageSize() {
      return pageSize;
    }

    public List<R> getData() {
      return data;
    }
  }

  public <R> PageBody<R> convert(List<R> data) {

    return new PageBody<>(total, totalPage, currentPage, pageSize, data);
  }

  public <R> PageBody<R> convert(Function<T, R> mapper) {
    return new PageBody<>(
        total, totalPage, currentPage, pageSize, ConvertUtil.list(this.data, mapper));
  }
}
