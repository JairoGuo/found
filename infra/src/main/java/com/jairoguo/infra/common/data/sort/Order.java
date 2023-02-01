package com.jairoguo.infra.common.data.sort;

/**
 * @author jairoguo
 */
public interface Order {

    String field();

    /** 获取排序类型 */
    SortDirectionEnum direction();
}
