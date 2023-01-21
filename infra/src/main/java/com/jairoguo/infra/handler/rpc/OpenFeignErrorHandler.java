package com.jairoguo.infra.handler.rpc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jairoguo.infra.common.response.CommonResultType;
import com.jairoguo.infra.common.response.ResultBody;
import com.jairoguo.infra.exception.SystemException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

/** Feign异常处理 */
@Component
public class OpenFeignErrorHandler implements ErrorDecoder {
  ObjectMapper objectMapper = new ObjectMapper();

  /**
   * 如果被调用方发生异常（无论时业务异常还是系统异常）都会被统一异常处理器拦截并返回ResultBody，判断响应状态码为500并且返回失败状态来抛出系统异常
   *
   * @param methodKey {@link feign.Feign#configKey} of the java method that invoked the request. ex.
   *     {@code IAM#getUser()}
   * @param response HTTP response where {@link Response#status() status} is greater than or equal
   *     to {@code 300}.
   * @return Exception
   */
  @Override
  public Exception decode(String methodKey, Response response) {

    try {
      String body = Util.toString(response.body().asReader(Charset.defaultCharset()));
      ResultBody<?> resultBody = objectMapper.readValue(body, ResultBody.class);
      if (Boolean.FALSE.equals(resultBody.isSuccess()) && response.status() == 500) {
        return new SystemException(resultBody.getCode(), resultBody.getMsg());
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return new SystemException(CommonResultType.ERROR.getCode(), "Feign client 调用异常");
  }
}
