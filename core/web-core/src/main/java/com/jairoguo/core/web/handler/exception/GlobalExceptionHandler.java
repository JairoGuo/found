package com.jairoguo.core.web.handler.exception;

import com.jairoguo.infra.common.response.CommonResultType;
import com.jairoguo.infra.common.response.ResultBody;
import com.jairoguo.infra.exception.BaseException;
import com.jairoguo.infra.exception.BusinessException;
import com.jairoguo.infra.exception.SystemException;
import com.jairoguo.infra.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author jairoguo
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 处理自定义异常，只处理BaseException的子类
   *
   * @param e 异常类
   * @return ResponseEntity<ResultBody<?>>
   */
  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ResultBody<?>> handle(BaseException e, WebRequest request) {

    if (e instanceof BusinessException exception) {
      return new ResponseEntity<>(
          ResultBody.builder()
              .code(CommonResultType.OK.getCode())
              .msg(exception.getMsg())
              .success(true)
              .build(),
          HttpStatus.OK);

    } else if (e instanceof SystemException exception) {
      return new ResponseEntity<>(
          ResultBody.builder()
              .code(CommonResultType.ERROR.getCode())
              .msg(exception.getMsg())
              .success(false)
              .build(),
          HttpStatus.OK);
    } else if (e instanceof ValidationException exception) {
      return new ResponseEntity<>(
          ResultBody.builder()
              .code(CommonResultType.ERROR.getCode())
              .msg(exception.getMsg())
              .success(false)
              .info(exception.getInfo())
              .build(),
          HttpStatus.OK);
    }

    return new ResponseEntity<>(
        ResultBody.builder()
            .code(CommonResultType.ERROR.getCode())
            .msg("发生未知异常")
            .success(false)
            .build(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResultBody<?>> exception(Exception e) {
    e.printStackTrace();
    StackTraceElement stackTraceElement = e.getStackTrace()[0];
    String className = stackTraceElement.getClassName();
    int lineNumber = stackTraceElement.getLineNumber();
    String methodName = stackTraceElement.getMethodName();
    String info =
        String.format("%s#%s:%s", className, methodName, lineNumber);
    return new ResponseEntity<>(
        ResultBody.builder()
            .code(CommonResultType.ERROR.getCode())
            .msg(e.getMessage())
            .success(false)
            .info(info)
            .build(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
