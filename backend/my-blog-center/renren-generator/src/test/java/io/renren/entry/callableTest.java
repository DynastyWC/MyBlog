package io.renren.entry;

import java.util.concurrent.Callable;

/**
 * @Description:
 * @Author: 王朝
 * @Date: 2023/12/26 16:53
 */
public class callableTest implements Callable<String> {

  @Override
  public String call() throws Exception {
    return "我是："+Thread.currentThread().getName();
  }
}
