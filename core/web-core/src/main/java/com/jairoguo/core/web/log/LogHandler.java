package com.jairoguo.core.web.log;

import com.jairoguo.core.web.log.annotation.FaceLog;
import com.jairoguo.core.web.log.annotation.RecordLog;
import com.jairoguo.core.web.log.annotation.ServiceLog;
import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Guo Jinru
 */
@Aspect
@Component
public class LogHandler {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Pointcut("@annotation(com.jairoguo.core.web.log.annotation.RecordLog)")
  public void recordLogPointCut() {}

  @Pointcut("@annotation(com.jairoguo.core.web.log.annotation.FaceLog)")
  public void faceLogPointCut() {}

  @Pointcut("@annotation(com.jairoguo.core.web.log.annotation.ServiceLog)")
  public void serviceLogPointCut() {}

  @Around("recordLogPointCut()")
  public Object recordLogAround(ProceedingJoinPoint point) throws Throwable {
    return handle(point, RecordLog.class);
  }

  @Around("faceLogPointCut()")
  public Object faceLogAround(ProceedingJoinPoint point) throws Throwable {
    return handle(point, FaceLog.class);
  }

  @Around("serviceLogPointCut()")
  public Object serviceLogAround(ProceedingJoinPoint point) throws Throwable {
    return handle(point, ServiceLog.class);
  }

  public <T> Object handle(ProceedingJoinPoint point, Class<T> annotationClass) throws Throwable {
    MethodSignature signature = (MethodSignature) point.getSignature();
    Method method = signature.getMethod();
    String className = point.getTarget().getClass().getName();
    String classShortName = point.getTarget().getClass().getSimpleName();
    String methodName = signature.getName();
    String operator = null;
    String type = null;
    if (annotationClass.isAssignableFrom(RecordLog.class)) {
      RecordLog recordLog = method.getAnnotation(RecordLog.class);
      operator = getOperator(recordLog.value(), recordLog.name(), methodName);
      type = getType(recordLog.type(), recordLog.scope(), classShortName);

    } else if (annotationClass.isAssignableFrom(FaceLog.class)) {
      FaceLog faceLog = method.getAnnotation(FaceLog.class);
      operator = getOperator(faceLog.value(), faceLog.name(), methodName);
      type = getType("接口", faceLog.scope(), classShortName);

    } else if (annotationClass.isAssignableFrom(ServiceLog.class)) {
      ServiceLog serviceLog = method.getAnnotation(ServiceLog.class);
      operator = getOperator(serviceLog.value(), serviceLog.name(), methodName);
      type = getType("服务", serviceLog.scope(), classShortName);
    }

    long beginTime = System.currentTimeMillis();

    Object returnValue = null;
    Exception ex = null;
    try {
      returnValue = point.proceed();
      return returnValue;
    } catch (Throwable e) {
      ex = (Exception) e;
      throw e;
    } finally {
      long cost = System.currentTimeMillis() - beginTime;
      if (ex != null) {
        log.error(
            "[class: {}] [method: {}] [type: {}] [operator: {}] [cost: {}ms] [params: {}] 发生异常",
            className,
            methodName,
            type,
            operator,
            cost,
            point.getArgs(),
            ex);
      } else {
        log.info(
            "[class: {}] [method: {}] [type: {}] [operator: {}] [cost: {}ms] [params: {}] [return: {}]",
            className,
            methodName,
            type,
            operator,
            cost,
            point.getArgs(),
            returnValue);
      }
    }
  }

  private String getOperator(String value, String name, String defaultValue) {
    if (!value.isEmpty() && !name.isEmpty()) {
      return name;
    }
    if (!value.isEmpty()) {
      return value;
    } else if (!name.isEmpty()) {
      return name;
    } else {
      return defaultValue;
    }
  }

  private String getType(String type, String scope, String defaultValue) {
    if (type.isEmpty()) {
      type = defaultValue;
    }
    if (!scope.isEmpty()) {
      type += String.format(":%s", scope);
    }
    return type;
  }
}
