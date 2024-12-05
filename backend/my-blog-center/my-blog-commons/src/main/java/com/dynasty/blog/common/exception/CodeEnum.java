package com.dynasty.blog.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author zgq
 * @version 1.0
 */
@Getter
@ToString
@AllArgsConstructor
public enum CodeEnum {

  // 自定义异常列举
  TOKEN_NULL(401, "用户未授权!"),
  TOKEN_UNMATCH(4001, "用户匹配!"),
  UNKNOWN_EXCEPTION(40000, "服务器内部异常，请联系管理员"),
  INVALID_EXCEPTION(40001, "参数校验异常"),
  LOGIN_ERROR(40002, "用户未授权"),
  ACCOUNT_NOT_EXIST(40003, "用户账号不存在"),
  MOBILE_ERROR(40004, "手机号码格式错误"),
  BING_ERROR(40005, "参数绑定异常~"),
  PARAMETER_LOSE(40006, "前端传输参数缺失"),
  PWD_ERROR(40007, "新密码格式错误"),
  CHARACTER_ERROR(40008, "包含非法字符"),
  PASSWORD_QUESTION(40009, "新密码和旧密码重复"),
  ACCOUNT_EXIST(40010, "账号已存在"),
  PARENT_NAME_LOSE(40011, "孩子表父母名字未填写无法录入"),
  IDENTIFY_ERROR(40012, "验证码输入错误"),
  PASSWORD_ALTER(40013, "初始密码需要修改，跳转到密码修改页面");

  private final Integer code;
  private final String msg;
}
