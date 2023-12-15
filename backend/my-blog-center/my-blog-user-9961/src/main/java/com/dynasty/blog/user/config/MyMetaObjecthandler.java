package com.dynasty.blog.user.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * @author dynasty
 * @description mybatis-plus数据自动填充
 */
@Component
@Slf4j
public class MyMetaObjecthandler implements MetaObjectHandler {

  /**
   * @param metaObject
   * @description 插入操作时数据自动填充策略
   */
  @Override
  public void insertFill(MetaObject metaObject) {
    log.info("公共字段自动填充【insert】");
    log.info(metaObject.toString());
    metaObject.setValue("addTime", LocalDateTime.now());
    metaObject.setValue("updateTime", LocalDateTime.now());
    metaObject.setValue("version", 1);
    log.info("metaObject:{}", metaObject);
    metaObject.setValue("deleted", 0);
  }
  /**
   * @param metaObject
   * @description 更新数据时数据自动填充策略
   */
  @Override
  public void updateFill(MetaObject metaObject) {
    log.info("公共字段，自动填充【update】");
    log.info(metaObject.toString());
    metaObject.setValue("updateTime", LocalDateTime.now());
  }
}