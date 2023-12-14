package com.dynasty.blog.config;

import cn.hutool.core.bean.BeanUtil;
import com.dynasty.blog.DTO.UserDTO;
import com.dynasty.blog.utils.JwtUtil;
import com.dynasty.blog.utils.UserHolder;
import com.kgkt.tust.common.exception.CodeEnum;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Description: 登录拦截器 @Author: 王朝 @Date: 2023/12/12 13:02
 */
@Slf4j
@Component
public class userLoginInterceptor implements HandlerInterceptor {

  /***
   * 在请求处理之前进行调用(Controller方法调用之前)
   */
  private StringRedisTemplate stringRedisTemplate;
  public userLoginInterceptor(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate = stringRedisTemplate;
  }
  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    try {
      // 如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
      // 如果设置为true时，请求将会继续执行后面的操作
      System.out.println("执行了拦截器的preHandle方法");
      // 获取token
      String token = request.getHeader("authorization");
      log.info("token:{}", token);
      // 判断token是否符合登录要求
      if (token == null) {
        response.getWriter().write(CodeEnum.LOGIN_ERROR.getMsg());
        response.setStatus(CodeEnum.LOGIN_ERROR.getCode());
        return false;
      }
      // 不为空解析token去redis中查找对应的key值
      Map<String, Object> map = JwtUtil.parseToken(token);
      String userPhone = (String) map.get("userPhone");
      log.info("userPhone:{}", userPhone);
      String redisToken = stringRedisTemplate.opsForValue().get("user:login:" + userPhone);
      log.info("redisToken:{}", redisToken);
      // 判断token和redis中的token是否相等
      if (!redisToken.equals(token)) {
        response.getWriter().write(CodeEnum.LOGIN_ERROR.getMsg());
        response.setStatus(CodeEnum.LOGIN_ERROR.getCode());
        return false;
      }
      log.info("map:{}", map);
      // 如果token符合登录要求，就将用户信息保存在threadLocal中
      UserDTO userDTO = BeanUtil.fillBeanWithMap(map, new UserDTO(), false);
      UserHolder.saveUser(userDTO);
      // 通过设置新的过期时间来刷新 key 的过期时间
      stringRedisTemplate.expire("user:login:" + userPhone, 100000L, TimeUnit.MINUTES);
      return true;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
