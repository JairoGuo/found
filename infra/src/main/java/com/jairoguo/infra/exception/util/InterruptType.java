package com.jairoguo.infra.exception.util;

import com.jairoguo.infra.exception.BaseException;
import com.jairoguo.infra.exception.BusinessException;
import com.jairoguo.infra.exception.SystemException;
import com.jairoguo.infra.exception.ValidationException;

/**
 * 中断异常类型
 *
 * <p>获取异常类型的异常实例 业务领域根据自身业务实现Interrupt创建不同异常的枚举类
 *
 * @author jairoguo
 */
public enum InterruptType implements Interrupt {
  BUSINESS {
    @Override
    public BaseException getException(String code, String msg) {
      return new BusinessException(code, msg);
    }
  },
  SYSTEM {
    @Override
    public BaseException getException(String code, String msg) {
      return new SystemException(code, msg);
    }
  }
}
