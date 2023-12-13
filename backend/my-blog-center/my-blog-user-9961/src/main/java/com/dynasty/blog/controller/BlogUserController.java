package com.dynasty.blog.controller;

import com.dynasty.blog.DTO.UserDTO;
import com.dynasty.blog.entity.BlogUserEntity;
import com.dynasty.blog.service.BlogUserService;

import com.dynasty.blog.utils.UserHolder;
import com.kgkt.tust.common.utils.R;
import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
      return R.error("用户已经存在！");
    }
    return R.ok("用户信息注册成功！");
  }
  /**
   * @author dynasty
   * @description 用户登录
   */
  @PostMapping("/login")
  public R dologin(@RequestParam String userPhone, String userPwd, HttpSession session) {
    String token = blogUserService.dologin(userPhone, userPwd, session);
    return R.ok(token);
  }

  /**
   * @author dynasty
   * @description 密码更新
   */
  @PostMapping("/updateInfo")
    public R updateInfo(@RequestBody Map<String,String> userInfo){
    boolean updateFlag = blogUserService.updateInfo(userInfo);
    if (!updateFlag){
      return R.error("信息更新失败，请检查数据格式！");
    }
    return  R.ok("信息更新成功！");
    }

    /**
     * @author dynasty
     * @description  用户密码更新接口
     */

    @PatchMapping("/updatePwd")
    public R updatePwd(@RequestParam  String userPwd){
      log.info("userPwd:{}",userPwd);
      boolean updatePwdFlag = blogUserService.updatePwd(userPwd);
      if(!updatePwdFlag){
        return R.error("密码更新失败，请重试！");
      }
        return  R.ok("密码更新成功，请重新登录！");
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
