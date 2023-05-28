package com.jairoguo.core.web.log.annotation;

import java.lang.annotation.*;

/**
 * 用于接口层门面接口日志记录
 *
 * @author Guo Jinru
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FaceLog {
  String value();

  String name() default "";

  String scope() default "";
}
