package com.jairoguo.core.web.handler.http;

import com.jairoguo.infra.common.response.Result;
import com.jairoguo.infra.common.response.ResultBody;
import com.jairoguo.infra.common.response.annotation.IgnoreResult;
import com.jairoguo.infra.util.FeignUtil;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author jairoguo
 */
@RestControllerAdvice
public class HttpResponseHandler implements ResponseBodyAdvice<Object> {

  Logger log = LoggerFactory.getLogger(HttpResponseHandler.class);

  @Override
  public boolean supports(
      MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {
    log.info(
        "响应 body:{}; ParameterType: {}; Headers: {}",
        body,
        returnType.getParameterType(),
        request.getHeaders());

    if (Objects.nonNull(body)) {
      if (request.getHeaders().containsKey(FeignUtil.FEIGN_ID_HEADER)) {
        if (body instanceof ResultBody<?> resultBody) {
          return resultBody.getData();
        }
        return body;
      }

      if (body instanceof ResultBody<?>) {
        return body;
      }

      if (returnType.getParameterType().isAssignableFrom(void.class)) {
        return Result.success();
      }

      if (returnType.getParameterType().isAssignableFrom(Void.class)) {
        return Result.success();
      }

      if (body instanceof String) {
        return body;
      }

      if (body.getClass().getAnnotation(IgnoreResult.class) != null) {
        return body;
      }

      return Result.success(body);
    }
    return Result.success();
  }
}
