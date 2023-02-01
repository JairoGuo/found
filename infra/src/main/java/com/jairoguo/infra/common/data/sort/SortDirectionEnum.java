package com.jairoguo.infra.common.data.sort;

import java.util.Locale;

/**
 * 通用排序类型
 *
 * @author jairoguo
 */
public enum SortDirectionEnum {
  ASC,
  DESC;

  public boolean isAscending() {
    return this.equals(ASC);
  }

  public boolean isDescending() {
    return this.equals(DESC);
  }

  public static SortDirectionEnum fromString(String value) {

    try {
      return SortDirectionEnum.valueOf(value.toUpperCase(Locale.US));
    } catch (Exception e) {
      throw new IllegalArgumentException(
          String.format("'%s' 是无效值！必须是 desc或asc（不区分大小写）。", value), e);
    }
  }
}
