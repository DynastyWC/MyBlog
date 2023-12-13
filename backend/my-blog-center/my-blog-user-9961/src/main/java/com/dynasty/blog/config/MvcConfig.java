package com.dynasty.blog.config;

import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: mvcz配置类，使得拦截器生效 @Author: 王朝 @Date: 2023/11/22 16:18
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

  //addInterceptors 添加拦截器
  @Resource StringRedisTemplate stringRedisTemplate;
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 添加拦截器并排除不需要拦截的路径，即不用登录也可以访问的页面
    registry
        .addInterceptor(new userLoginInterceptor(stringRedisTemplate))
        .excludePathPatterns("/user/login", "/user/register")
        .order(Ordered.HIGHEST_PRECEDENCE);;
  }

}



