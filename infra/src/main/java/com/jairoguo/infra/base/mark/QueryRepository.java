package com.jairoguo.infra.base.mark;

import com.jairoguo.infra.common.data.page.PageBody;
import com.jairoguo.infra.common.data.page.PageParam;
import com.jairoguo.infra.common.data.query.QueryBuilder;

import java.util.List;

/**
 * @author jairougo
 */
public interface QueryRepository<A> {

  A findBy(QueryBuilder<A> queryParam);

  List<A> findAllBy(QueryBuilder<A> queryParam);

  PageBody<A> findPageBy(QueryBuilder<A> queryParam, PageParam pageParam);

  Long countBy(QueryBuilder<A> queryParam);
}
