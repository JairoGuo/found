package com.jairoguo.infra.handler.rpc;

import com.jairoguo.infra.util.FeignUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.Objects;

/**
 * Feign拦截处理器
 */
@Component
public class FeignInterceptorHandler implements RequestInterceptor {

  Logger log = LoggerFactory.getLogger(FeignInterceptorHandler.class);

  @Resource Environment environment;

  @Override
  public void apply(RequestTemplate template) {
    log.info("Feign拦截处理 Header(feign_id): {}", template.headers().get(FeignUtil.FEIGN_ID_HEADER));
    if (template.headers().get(FeignUtil.FEIGN_ID_HEADER) == null) {
      template.header(FeignUtil.FEIGN_ID_HEADER, FeignUtil.generateID());
      String applicationName = environment.getProperty("spring.application.name");
      template.header("server_name", applicationName);
    }

    ServletRequestAttributes requestAttributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (requestAttributes != null) {
      HttpServletRequest request = requestAttributes.getRequest();
      Enumeration<String> headerNames = request.getHeaderNames();
      if (Objects.nonNull(headerNames)) {
        while (headerNames.hasMoreElements()) {
          String key = headerNames.nextElement();
          if (!"content-length".equals(key)) {
            String value = request.getHeader(key);
            template.header(key, value);
          }
        }
      }
    }

    String requestTime =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    template.header("request-time", requestTime);
  }
}
