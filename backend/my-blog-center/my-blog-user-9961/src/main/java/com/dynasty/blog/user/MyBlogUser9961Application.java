package com.dynasty.blog.user;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dynasty.blog.user.dao")
public class MyBlogUser9961Application {
  public static void main(String[] args) {
    SpringApplication.run(MyBlogUser9961Application.class, args);
    // TODO 用户头像上传与下载
  }

}
