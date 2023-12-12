package com.dynasty.blog;

import com.dynasty.blog.utils.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyBlogUser9961ApplicationTests {

  @Test
  void contextLoads() {
    Boolean matches = PasswordEncoder.matches(
        "a7g644u9yyudtzptfpr5@270ea05b66d1ee034ab4b4e20ccbbf80", "123456");
    System.out.println(matches);
  }
}
