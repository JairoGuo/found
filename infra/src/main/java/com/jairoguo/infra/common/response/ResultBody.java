package com.jairoguo.infra.common.response;

import java.io.Serializable;

/**
 * 统一响应结构体
 *
 * @author jairoguo
 */
public class ResultBody<T> implements Serializable {
  /** 操作代码 */
  private final String code;
  /** 消息 */
  private final String msg;
  /** 成功状态 */
  private final boolean success;
  /** 返回结构体 */
  private final transient T data;
  /** 提示信息 */
  private final transient Object info;

  public ResultBody() {
    this.code = CommonResultType.OK.getCode();
    this.msg = CommonResultType.OK.getMsg();
    this.success = true;
    this.data = null;
    this.info = null;
  }

  public ResultBody(Builder<T> builder) {
    this.code = builder.code;
    this.msg = builder.msg;
    this.success = builder.success;
    this.data = builder.data;
    this.info = builder.info;
  }

  public static <T> Builder<T> builder() {
    return new Builder<>();
  }

  public static class Builder<R> {

    private String code;
    private String msg;
    private Boolean success;
    private R data;
    private Object info;

    public Builder() {
      this.code = CommonResultType.OK.getCode();
      this.msg = CommonResultType.OK.getMsg();
      this.success = true;
      this.data = null;
      this.info = null;
    }

    public Builder<R> code(String code) {
      this.code = code;
      return this;
    }

    public Builder<R> msg(String msg) {
      this.msg = msg;
      return this;
    }

    public Builder<R> success(Boolean success) {
      this.success = success;
      return this;
    }

    public Builder<R> data(R data) {
      this.data = data;
      return this;
    }

    public Builder<R> info(Object info) {
      this.info = info;
      return this;
    }

    public ResultBody<R> build() {
      return new ResultBody<>(this);
    }
  }

  public String getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public boolean isSuccess() {
    return success;
  }

  public T getData() {
    return data;
  }

  public Object getInfo() {
    return info;
  }
}
