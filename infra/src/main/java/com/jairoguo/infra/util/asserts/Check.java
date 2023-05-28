package com.jairoguo.infra.util.asserts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Guo Jinru
 */
public interface Check {

  default boolean check(String value) {
    Pattern pattern = Pattern.compile(getRule());
    Matcher matcher = pattern.matcher(value);
    return matcher.find();
  }

  String getRule();

  String getDefaultMsg();
}
