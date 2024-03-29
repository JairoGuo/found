package com.jairoguo.infra.util.asserts;

/**
 * @author Guo Jinru
 */
public enum CheckType implements Check {
  /** URL */
  URL {

    @Override
    public String getRule() {
      return "^(((ht|f)tps?):\\/\\/)?([^!@#$%^&*?.\\s-]([^!@#$%^&*?.\\s]{0,63}[^!@#$%^&*?.\\s])?\\.)+[a-z]{2,6}\\/?";
    }

    @Override
    public String getDefaultMsg() {
      return "不是URL";
    }
  },
  /** 带端口号的URL */
  PORT_URL {
    @Override
    public String getRule() {
      return "^((ht|f)tps?:\\/\\/)?[\\w-]+(\\.[\\w-]+)+:\\d{1,5}\\/?$";
    }

    @Override
    public String getDefaultMsg() {
      return "必须带端口号的URL";
    }
  },
  /** 邮箱 */
  EMAIL {
    @Override
    public String getRule() {
      return "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    }

    @Override
    public String getDefaultMsg() {
      return "非法邮箱格式";
    }
  },
  PASSWORD {
    @Override
    public String getRule() {
      return "^\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*[A-Z])(?=\\S*[a-z])(?=\\S*[!@#$%^&*? ])\\S*$";
    }

    @Override
    public String getDefaultMsg() {
      return "密码强度低";
    }
  },
  CHINESE_CHARACTER {
    @Override
    public String getRule() {
      return "^(?:[\\u3400-\\u4DB5\\u4E00-\\u9FEA\\uFA0E\\uFA0F\\uFA11\\uFA13\\uFA14\\uFA1F\\uFA21\\uFA23\\uFA24\\uFA27-\\uFA29]|[\\uD840-\\uD868\\uD86A-\\uD86C\\uD86F-\\uD872\\uD874-\\uD879][\\uDC00-\\uDFFF]|\\uD869[\\uDC00-\\uDED6\\uDF00-\\uDFFF]|\\uD86D[\\uDC00-\\uDF34\\uDF40-\\uDFFF]|\\uD86E[\\uDC00-\\uDC1D\\uDC20-\\uDFFF]|\\uD873[\\uDC00-\\uDEA1\\uDEB0-\\uDFFF]|\\uD87A[\\uDC00-\\uDFE0])+$";
    }

    @Override
    public String getDefaultMsg() {
      return "不是汉字";
    }
  },
  NUMBER {
    @Override
    public String getRule() {
      return "^\\d+$";
    }

    @Override
    public String getDefaultMsg() {
      return "不是数字";
    }
  }
}
