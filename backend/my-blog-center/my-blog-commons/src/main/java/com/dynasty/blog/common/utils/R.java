package com.dynasty.blog.common.utils;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;

/**
 * @author zgq
 * @description 给前端返回JSON数据的一个实体工具类
 */
public class R extends HashMap<String, Object> {

  private static final long serialVersionUID = 1L;

  // 构造器
  public R() {
    put("code", 200);
    put("msg", "success");
  }

  public static R error() { // jar 包那个httpCore
    return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "服务器未知异常，请联系管理员");
  }

  public static R error(String msg) {
    return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
  }

  public static R error(int code, String msg) {
    R r = new R();
    r.put("code", code);
    r.put("msg", msg);
    return r;
  }

  public static R ok(String msg) {
    R r = new R();
    r.put("msg", msg);
    return r;
  }

  public static R ok(Map<String, Object> map) {
    R r = new R();
    r.putAll(map);
    return r;
  }

  public static R ok() {
    return new R();
  }

  @Override
  public R put(String key, Object value) {
    super.put(key, value);
    return this;
  }
}
