package com.jairoguo.infra.base.mark;

import com.jairoguo.infra.util.ConvertUtil;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 转换
 *
 * @author jairoguo
 */
public interface Convert {

  default <T, R> R to(T o, Function<T, R> mapper) {
    return ConvertUtil.to(o, mapper);
  }

  default <T, R> R to(T o, Supplier<R> mapper) {
    return ConvertUtil.to(o, mapper);
  }
}
