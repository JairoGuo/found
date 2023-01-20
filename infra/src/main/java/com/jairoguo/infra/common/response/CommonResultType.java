package com.jairoguo.infra.common.response;

/**
 * 通用返回类型
 *
 * 业务领域根据自身业务实现ResultType接口创建枚举类型
 *
 * @author jairoguo
 */
public enum CommonResultType implements ResultType {
  OK("1", "操作成功"),
  FAIL("0", "操作失败"),
  ERROR("-1", "错误");

  private final String code;

  private final String msg;

  CommonResultType(String msg, String code) {
    this.msg = msg;
    this.code = code;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getMsg() {
    return msg;
  }
}
