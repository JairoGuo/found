package com.jairoguo.infra.common.response;

import com.jairoguo.infra.exception.BaseException;
import com.jairoguo.infra.exception.BusinessException;
import com.jairoguo.infra.exception.util.Interrupt;

/**
 * 封装统一响应工具类
 *
 * @author jairoguo
 */
public class Result {

  /**
   * 返回成功，不通过异常
   *
   * @return ResultBody<T>
   * @param <T> 统一响应数据类型
   */
  public static <T> ResultBody<T> success() {
    return new ResultBody<>();
  }

  /**
   * 返回成功，并带有数据，不通过异常
   *
   * @param data 数据体
   * @return ResultBody<T>
   * @param <T> 统一响应数据类型
   */
  public static <T> ResultBody<T> success(T data) {
    return ResultBody.<T>builder().data(data).build();
  }

  /**
   * 返回失败，通过异常返回
   *
   * @param resultType 返回状态类型
   */
  public static void fail(ResultType resultType) {
    throw new BusinessException(resultType.getCode(), resultType.getMsg());
  }

  /**
   * 返回失败，通过异常返回
   *
   * @param msg 返回失败消息
   */
  public static void fail(String msg) {
    throw new BusinessException(CommonResultType.FAIL.getCode(), msg);
  }

  /**
   * 返回失败，通过异常返回
   *
   * @param code 返回失败状态码
   * @param msg 返回失败消息
   */
  public static void fail(String code, String msg) {
    throw new BusinessException(code, msg);
  }

  /**
   * 返回失败，通过抛出中断接口返回的异常类
   *
   * @param msg 返回失败消息
   * @param interrupt 获取需要中断业务的异常类
   */
  public static void fail(String msg, Interrupt interrupt) {
    throw interrupt.getException(CommonResultType.FAIL.getCode(), msg);
  }

  /**
   * 返回失败，失败参数有返回状态类型确定，通过抛出中断接口返回的异常类
   *
   * @param resultType 返回状态类型
   * @param interrupt 获取需要中断业务的异常类
   */
  public static void fail(ResultType resultType, Interrupt interrupt) {
    throw interrupt.getException(resultType.getCode(), resultType.getMsg());
  }

  /**
   * 返回失败，直接将异常返回，不进行抛出
   *
   * @param resultType 返回状态类型
   * @return BaseException
   */
  public static BaseException returnFail(ResultType resultType) {
    return new BusinessException(resultType.getCode(), resultType.getMsg());
  }

  /**
   * 返回失败，直接将异常返回，不进行抛出
   *
   * @param msg 返回失败消息
   * @return BaseException
   */
  public static BaseException returnFail(String msg) {
    return new BusinessException(CommonResultType.FAIL.getCode(), msg);
  }

  /**
   * 返回失败，直接将异常返回，不进行抛出
   *
   * @param code 返回失败状态码
   * @param msg 返回失败消息
   * @return BaseException
   */
  public static BaseException returnFail(String code, String msg) {
    throw new BusinessException(code, msg);
  }

  private Result() {}
}
