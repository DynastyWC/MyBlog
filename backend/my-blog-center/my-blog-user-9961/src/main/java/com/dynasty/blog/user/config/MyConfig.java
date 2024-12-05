package com.dynasty.blog.user.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dynasty
 * @description 乐观锁插件配置
 */
@Configuration
@Slf4j
public class MyConfig {

  // 乐观锁插件
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    log.info("进入了Myconfig");
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
    return interceptor;
  }
}
