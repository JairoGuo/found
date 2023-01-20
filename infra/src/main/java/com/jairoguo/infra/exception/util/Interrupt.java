package com.jairoguo.infra.exception.util;

import com.jairoguo.infra.exception.BaseException;

/**
 * 中断业务接口
 *
 * @author jairoguo
 */
public interface Interrupt {

    /**
     * 返回BaseException及子类
     *
     * @param code 状态码
     * @param msg 消息
     * @return BaseException
     */
    BaseException getException(String code, String msg);
}
