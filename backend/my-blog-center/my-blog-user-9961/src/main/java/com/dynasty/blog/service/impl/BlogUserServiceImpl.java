package com.dynasty.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dynasty.blog.DTO.UserDTO;
import com.dynasty.blog.utils.JwtUtil;
import com.dynasty.blog.utils.PasswordEncoder;
import com.dynasty.blog.dao.BlogUserDao;
import com.dynasty.blog.entity.BlogUserEntity;
import com.dynasty.blog.service.BlogUserService;

import com.dynasty.blog.utils.UserHolder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service("blogUserService")
public class BlogUserServiceImpl extends ServiceImpl<BlogUserDao, BlogUserEntity>
    implements BlogUserService {

  @Resource
  private BlogUserDao blogUserDao;
  @Resource
  private StringRedisTemplate stringRedisTemplate;

  /**
   * @author dynasty
   * @description 用户注册接口实现
   */
  @Override
  public Boolean doregister(BlogUserEntity blogUser) {
    String userPhone = blogUser.getUserPhone();
    QueryWrapper<BlogUserEntity> Wrapper = new QueryWrapper<>();
    Wrapper.eq("user_phone", userPhone);
    if(count(Wrapper)!=0){
      return false;
    }
    String userPwd = blogUser.getUserPwd();
    // 将用户信息使用pwd工具类加密
    String encodePwd = PasswordEncoder.encode(userPwd);
    blogUser.setUserPwd(encodePwd);
    // 将用户信息写到数据库
    boolean saveFlag = save(blogUser);
    if (!saveFlag) {
      return false;
    }
    return true;
  }

  /**
   * @author dynasty
   * @description :用户登录逻辑实现
   */

  @Override
  public String dologin(String userPhone, String userPwd, HttpSession session) {
    if (userPhone.isEmpty() || userPwd.isEmpty()) {
      return "用户和密码不能为空！";
    }
    // 通过手机号去查用户信息
    BlogUserEntity user = blogUserDao.getByPhone(userPhone);
    // 如果用户信息不存在返回false
    if (user == null) {
      return "用户不存在";
    }
    // 解密并校验密码
    String userPwdInDb = user.getUserPwd();
    Boolean matches = PasswordEncoder.matches(userPwdInDb, userPwd);
    if (!matches) {
      return "用户密码不匹配！";
    }
    HashMap<String, Object> claims = new HashMap<>();
    claims.put("userId", user.getUserId());
    claims.put("userName", user.getUserName());
    claims.put("userPhone", user.getUserPhone());
    // 生成token
    String token = JwtUtil.getToken(claims);
    // 将token写入redis
    stringRedisTemplate
        .opsForValue()
        .set("user:login:" + user.getUserPhone(), token, 10000L, TimeUnit.MINUTES);
    session.setAttribute("authorization", token);
    log.info("token:{}", token);
    return token;
  }
/**
 * @param userInfo
 * @return boolean
 * @description 更新用户信息
 */
  @Override
  public boolean updateInfo(Map<String,String> userInfo) {
    log.info("userInfoList:{}", userInfo);
    try {
      UserDTO user = UserHolder.getUser();
      Long userId = user.getUserId();
      String userName = userInfo.get("userName");
      String userEmail = userInfo.get("userEmail");
      String motto = userInfo.get("motto");
      String icon = userInfo.get("icon");
      return blogUserDao.updateInfo(userName, userEmail, icon, motto, userId);
    } catch (Exception e) {
      log.error("Error updating user info", e);
      return false;
    }
  }
/**
 * @param userPwd
 * @return boolean
 * @description 更新用户密码
 */
  @Override
  public boolean updatePwd(String userPwd) {
    UserDTO user = UserHolder.getUser();
    Long userId = user.getUserId();
    String encodePwd = PasswordEncoder.encode(userPwd);
    boolean updateFlag = blogUserDao.updatePwd(encodePwd, userId);
    if(!updateFlag){
      return  false;
    }
    stringRedisTemplate.delete("user:login:" + user.getUserPhone());
    return true;
  }
}
