package com.dynasty.blog.common.xss;

import com.dynasty.blog.common.exception.CodeEnum;
import com.dynasty.blog.common.exception.CustomException;
import org.apache.commons.lang.StringUtils;

/**
 * @author zgq
 * @description 实现SQL注入过滤功能
 */
public class SQLFilter {

  private SQLFilter() {}

  /**
   * @param str 待验证的字符串
   * @return java.lang.String
   * @description SQL注入过滤
   */
  public static String sqlInject(String str) {
    if (StringUtils.isBlank(str)) {
      return null;
    }
    // 去掉'|"|;|\字符
    str = StringUtils.replace(str, "'", "");
    str = StringUtils.replace(str, "\"", "");
    str = StringUtils.replace(str, ";", "");
    str = StringUtils.replace(str, "\\", "");

    // 转换成小写
    str = str.toLowerCase();

    // 非法字符
    String[] keywords = {
      "master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"
    };

    // 判断是否包含非法字符
    for (String keyword : keywords) {
      if (str.contains(keyword)) {
        throw new CustomException(CodeEnum.CHARACTER_ERROR);
      }
    }
    return str;
  }
}
