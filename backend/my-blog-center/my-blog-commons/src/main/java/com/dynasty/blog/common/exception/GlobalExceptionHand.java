package com.dynasty.blog.common.exception;

import com.dynasty.blog.common.utils.R;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zgq
 * @description 对于整个模块所有异常集中处理的异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHand {

  // 处理所有的异常
  @ExceptionHandler(Exception.class)
  public R exceptionsHand(Exception e) {
    // 判断是否为自定义异常
    if (e instanceof CustomException) {
      CustomException ex = (CustomException) e;
      return R.error(ex.getCodeEnum().getCode(), ex.getCodeEnum().getMsg());
      // 判断是否为校验异常（SR303校验)
    } else if (e instanceof MethodArgumentNotValidException) {
      MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
      return R.error("参数校验异常：" + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
      // 上述两类情况都不符合，就统一返回异常信息：系统内部异常
    } else {
      return R.error(CodeEnum.UNKNOWN_EXCEPTION.getCode(), CodeEnum.UNKNOWN_EXCEPTION.getMsg())
          .put("error", e.getMessage());
    }
  }
}
