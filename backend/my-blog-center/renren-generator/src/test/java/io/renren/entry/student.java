package io.renren.entry;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 学会类
 * @Author: 王朝
 * @Date: 2023/12/26 16:44
 */
public class student implements Runnable {
  private AtomicInteger count =new AtomicInteger();

  public student(String name, Integer age, String pingpang) {
  }

  public student() {

  }

  @Override
  public synchronized void run() {
      for (int i = 1; i <=10; i++) {
        System.out.println( Thread.currentThread().getName()+":count-->"+count.incrementAndGet());
      }
    }
  }
