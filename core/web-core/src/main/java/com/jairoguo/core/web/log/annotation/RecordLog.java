package com.jairoguo.core.web.log.annotation;

import java.lang.annotation.*;

/**
 * 日志记录
 *
 * @author Guo Jinru
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordLog {
  String value();

  String name() default "";
  String type() default "";

  String scope() default "";
}
