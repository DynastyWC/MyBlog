package com.dynasty.blog.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zgq
 * @description 自定义异常处理类, 统一放到 common模块里供使用
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {

  // 异常枚举类作为其属性
  private CodeEnum codeEnum;
}
