package com.jairoguo.infra.common.data.query;

import java.util.regex.Pattern;

/**
 * 模糊匹配类型
 *
 * @author jairoguo
 */
public enum LikeModeType {
  /** 正则模式 */
  REGEX,
  /**
   * Like模式 规则: 使用*号代表多个字符 使用?号代表单个字符 示例: "*ab*": 模糊匹配两端字符串 "*a?b*": 模糊匹配两端字符，并且a与b存一一个任意字符 "a?b":
   * 匹配长度为3的字符串
   */
  LIKE,
  /** 包含匹配模式 匹配字符串中的任何符号都将被转义 示例: "ab": 匹配包含ab的字符串，同*ab* "*a?b*": *a?b*会被当作匹配内容 */
  CONTAINING;

  public String getRegex(String source) {
    return switch (this) {
      case LIKE -> source.replace("*", ".*");
      case REGEX -> source;
      case CONTAINING -> String.format(".*%s.*", Pattern.quote(source));
    };
  }
}
