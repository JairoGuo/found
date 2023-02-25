package com.jairoguo.infra.common.data.field;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 用于实现方法引用传参接口
 *
 * @author jairogoo
 */
@FunctionalInterface
public interface FieldFunction<T, R> extends Function<T, R>, Serializable {}
