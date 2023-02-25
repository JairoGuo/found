package com.jairoguo.infra.common.data.page;

import com.jairoguo.infra.common.data.sort.SortParam;

/**
 * 通用分页接口
 *
 * @author Guo Jinru
 */
public interface PageParam {

  Integer getSize();

  Integer getCurrent();

  SortParam getSort();
}
