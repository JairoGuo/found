package com.jairoguo.infra.common.data.query;
/**
 * 查询条件接口
 *
 * @author jairoguo
 */
public interface QueryCondition<T> {

  QueryConditionMapper<T> getCondition();
}
