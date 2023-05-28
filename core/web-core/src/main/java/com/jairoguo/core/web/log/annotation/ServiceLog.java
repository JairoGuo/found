package com.jairoguo.core.web.log.annotation;

import java.lang.annotation.*;

/**
 * 用于服务日志记录
 *
 * @author Guo Jinru
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog {
  String value();

  String name() default "";

  String scope() default "";
}
