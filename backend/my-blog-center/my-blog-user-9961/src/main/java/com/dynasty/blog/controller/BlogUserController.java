package com.dynasty.blog.controller;

import com.dynasty.blog.DTO.UserDTO;
import com.dynasty.blog.entity.BlogUserEntity;
import com.dynasty.blog.service.BlogUserService;

import com.dynasty.blog.utils.UserHolder;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dynasty.blog.common.utils.R;

/**
 * @author dynasty
 * @email study_wc@163.com
 * @date 2023-12-11 22:49:03
 */
@Slf4j
@RestController
@RequestMapping("user")
public class BlogUserController {
  @Autowired private BlogUserService blogUserService;

  /**
   * @author dynasty
   * @description 用户注册实现
   */
  @PostMapping("/register")
  public R doRegister(@RequestBody BlogUserEntity blogUser) {
    Boolean registerRes = blogUserService.doregister(blogUser);
    if (!registerRes) {
      return R.error("注册失败！");
    }
    return R.ok("用户信息注册成功！");
  }
  /**
   * @author dynasty
   * @description 用户登录
   */
  @PostMapping("/login")
  public R dologin(String userPhone, String userPwd, HttpSession session) {
    String token = blogUserService.dologin(userPhone, userPwd, session);
    return R.ok(token);
  }

  /**
   * @author dynasty
   * @description 密码更新
   */
  @PostMapping("/updateInfo")
    public R updateInfo(@RequestBody BlogUserEntity blogUser){
    boolean updateFlag = blogUserService.updateInfo(blogUser);
    if (!updateFlag){
      return R.error("信息更新失败，请检查数据格式！");
    }
    return  R.ok("信息更新成功！");
    }

  /**
   * @author dynasty
   * @description 测试专用
   */
  @PostMapping("/getuser")
  public R getuser() {
    UserDTO user = UserHolder.getUser();
    log.info("thread中的user:{}", user);
    return R.ok();
  }
}
