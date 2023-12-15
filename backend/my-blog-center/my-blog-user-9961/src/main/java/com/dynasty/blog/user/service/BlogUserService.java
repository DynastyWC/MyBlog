package com.dynasty.blog.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dynasty.blog.user.entity.BlogUserEntity;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 * @author dynasty
 * @email study_wc@163.com
 * @date 2023-12-11 22:49:03
 */
public interface BlogUserService extends IService<BlogUserEntity> {

  // 用户注册接口
  boolean doregister(BlogUserEntity blogUser);

  // 用户登录接口
  String dologin(String userPhone, String userPwd, HttpSession session);

  // 更新用户信息
  boolean updateInfo(Map<String, String> userInfo);

  //更新用户密码
  boolean updatePwd(String userPwd);

  //删除用户信息
  boolean deleteUser(Long userId);
  //查询用户信息
  BlogUserEntity getUserInfo();

  //查询所有用户
  ArrayList<BlogUserEntity> getAllUser();


}
