package com.dynasty.blog.user.controller;

import com.dynasty.blog.user.DTO.UserDTO;
import com.dynasty.blog.user.entity.BlogUserEntity;
import com.dynasty.blog.user.entity.PageBean;
import com.dynasty.blog.user.service.BlogUserService;
import com.dynasty.blog.user.utils.MyBindResultUtils;
import com.kgkt.tust.common.utils.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author dynasty
 * @email study_wc@163.com
 * @date 2023-12-11 22:49:03
 */
@CrossOrigin(origins = "http://localhost:8082", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("/user")
public class BlogUserController {

  @Resource
  private BlogUserService blogUserService;


  /**
   * @author dynasty
   * @description 用户注册实现
   */
  @PostMapping("/register")
  public R doRegister(@RequestBody @Valid BlogUserEntity blogUser, BindingResult bindingResult) {
    List<String> validRes = MyBindResultUtils.dataValid(bindingResult);
    if (validRes != null) {
      return R.error().put("error", validRes);
    }
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
    String loginFlag = blogUserService.dologin(userPhone, userPwd, session);
    if (loginFlag == null) {
      return R.error("用户名或密码错误!");
    }
    return R.ok(loginFlag);
  }

  /**
   * @author dynasty
   * @description 用户信息更新
   */
  @PostMapping("/updateInfo")
  public R updateInfo(@RequestBody @Valid Map<String, String> userInfo,
      BindingResult bindingResult) {
    List<String> validRes = MyBindResultUtils.dataValid(bindingResult);
    if (validRes != null) {
      return R.error().put("error", validRes);
    }
    String version = userInfo.get("version");
    if (version == null) {
      return R.error("数据格式错误!");
    }
    boolean updateFlag = blogUserService.updateInfo(userInfo);
    if (!updateFlag) {
      return R.error("信息更新失败，请检查数据格式！");
    }
    return R.ok("信息更新成功！");
  }
  /**
   * @author dynasty
   * @description 校验用户是否注册
   */
@GetMapping("/checkUser")
public R checkUser(String userPhone){
  UserDTO loginUser = blogUserService.checkUser(userPhone);
  return R.ok().put("loginUser", loginUser);
}
  /**
   * @author dynasty
   * @description 用户密码更新接口
   */

  @PatchMapping("/updatePwd")
  public R updatePwd(@RequestParam @Valid String userPwd) {
    log.info("userPwd:{}", userPwd);
    boolean updatePwdFlag = blogUserService.updatePwd(userPwd);
    if (!updatePwdFlag) {
      return R.error("密码更新失败，请重试！");
    }
    return R.ok("密码更新成功，请重新登录！");
  }

  /**
   * @author dynasty
   * @description 管理员专属接口，逻辑删除用户（禁用）
   */

  @DeleteMapping("/delete")
  public R deleteUser(@RequestParam Long userId) {
    boolean deleteFlag = blogUserService.deleteUser(userId);
    if (!deleteFlag) {
      return R.error("用户信息删除失败！");
    }
    return R.ok("用户信息删除成功！");
  }

  /**
   * @author dynasty
   * @description 查询用户信息用于回显
   */
  @GetMapping("/UserInfo")
  public R getUserInfo() {
    BlogUserEntity userInfo = blogUserService.getUserInfo();
    if (userInfo == null) {
      return R.error("获取用户信息失败！");
    }
    return R.ok().put("userInfo", userInfo);
  }

  /**
   * @author dynasty
   * @description 管理员专属接口，获取所有用户信息
   */
  @GetMapping("/getAllUser")
  public R getUser() {
    ArrayList<BlogUserEntity> allUser = blogUserService.getAllUser();
    if (allUser == null) {
      return R.error("所有用户信息获取失败！");
    }
    return R.ok().put("allUser", allUser);
  }

  /**
   * @author dynasty
   * @description 用户分页查询
   */
  @GetMapping
  public R getAlluser(
      Integer pageNum,
      Integer pageSize,
      @RequestParam(required = false) String userName,
      @RequestParam(required = false) String userPhone,
      @RequestParam(required = false) Integer deleted
  ) {
    PageBean<BlogUserEntity> userList = blogUserService.usetList(pageNum, pageSize,
        userName, userPhone, deleted);
    return R.ok().put("userData", userList);
  }

  /**
   * @author dynasty
   * @description 用户头像上传至oss
   */
  @PostMapping("/upload")
  public R upload(MultipartFile file) {
    if (file == null) {
      return R.error("文件格式错误！");
    }
    String fileUrl = blogUserService.upload(file);
    return R.ok("文件上传成功！").put("fileUrl", fileUrl);
  }

  /**
   * @author dynasty
   * @description 测试专用
   */
  @PostMapping("/test")
  public R getuser(String msg) {
    if (msg == null) {
      return R.error();
    }
    return R.ok();
  }
}
