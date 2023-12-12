package com.dynasty.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dynasty.blog.entity.BlogUserEntity;
import javax.servlet.http.HttpSession;

/**
 * @author dynasty
 * @email study_wc@163.com
 * @date 2023-12-11 22:49:03
 */
public interface BlogUserService extends IService<BlogUserEntity> {
  // 用户注册接口
  Boolean doregister(BlogUserEntity blogUser);

  // 用户登录接口
  String dologin(String userPhone, String userPwd, HttpSession session);

  // 更新用户信息
  boolean updateInfo(BlogUserEntity blogUser);
}
