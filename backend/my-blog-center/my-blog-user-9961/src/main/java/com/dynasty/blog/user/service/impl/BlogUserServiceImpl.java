package com.dynasty.blog.user.service.impl;

import static com.dynasty.blog.user.utils.Constant.OSS_USER_ICON;
import static com.dynasty.blog.user.utils.Constant.USER_LOGIN;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dynasty.blog.user.entity.DTO.UserDTO;
import com.dynasty.blog.user.entity.PageBean;
import com.dynasty.blog.user.utils.JwtUtil;
import com.dynasty.blog.user.utils.OssUtils;
import com.dynasty.blog.user.utils.PasswordEncoder;
import com.dynasty.blog.user.dao.BlogUserDao;
import com.dynasty.blog.user.entity.BlogUserEntity;
import com.dynasty.blog.user.service.BlogUserService;
import com.dynasty.blog.user.utils.UserHolder;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
  public boolean doregister(BlogUserEntity blogUser) {
    String userPhone = blogUser.getUserPhone();
    QueryWrapper<BlogUserEntity> Wrapper = new QueryWrapper<>();
    Wrapper.eq("user_phone", userPhone);
    if (count(Wrapper) != 0) {
      return false;
    }
    String userPwd = blogUser.getUserPwd();
    // 将用户信息使用pwd工具类加密
    String encodePwd = PasswordEncoder.encode(userPwd);
    blogUser.setUserPwd(encodePwd);
    // 将用户信息写到数据库
    return save(blogUser);
  }

  /**
   * @author dynasty
   * @description :用户登录逻辑实现
   */

  @Override
  public String dologin(String userPhone, String userPwd, HttpSession session) {
    if (userPhone.isEmpty() || userPwd.isEmpty()) {
      return null;
    }
    // 通过手机号去查用户信息
    BlogUserEntity user = blogUserDao.getByPhone(userPhone);
    if (BeanUtil.isEmpty(user) || user.getDeleted() == 1) {
      return null;
    }
    Integer deleteFlag = user.getDeleted();
    log.info("deleteFlag:{}", deleteFlag);
    // 如果用户信息不存在返回false

    // 解密并校验密码
    String userPwdInDb = user.getUserPwd();
    Boolean matches = PasswordEncoder.matches(userPwdInDb, userPwd);
    if (!matches) {
      return null;
    } else {
      HashMap<String, Object> claims = new HashMap<>();
      claims.put("userId", user.getUserId());
      claims.put("userName", user.getUserName());
      claims.put("userPhone", user.getUserPhone());
      // 生成token
      String token = JwtUtil.getToken(claims);
      // 将token写入redis
      stringRedisTemplate
          .opsForValue()
          .set(USER_LOGIN + user.getUserPhone(), token, 10000L, TimeUnit.MINUTES);
      session.setAttribute("authorization", token);
      log.info("token:{}", token);
      return token;
    }
  }

  /**
   * @param userInfo
   * @return boolean
   * @description 更新用户信息
   */
  @Override
  public boolean updateInfo(Map<String, String> userInfo) {
    log.info("userInfoList:{}", userInfo);
    try {
      UserDTO user = UserHolder.getUser();
      Long userId = user.getUserId();
      userInfo.put("userId", String.valueOf(userId));
      BlogUserEntity blogUserEntity = BeanUtil.mapToBean(userInfo, BlogUserEntity.class, true);
      return updateById(blogUserEntity);
    } catch (Exception e) {
      log.error("Error updating user info", e);
      return false;
    }
  }

  /**
   * @return boolean
   * @description 更新用户密码
   */
  @Override
  public boolean updatePwd(String userPwd) {

    UserDTO user = UserHolder.getUser();
    Long userId = user.getUserId();
    String encodePwd = PasswordEncoder.encode(userPwd);
    boolean updateFlag = blogUserDao.updatePwd(encodePwd, userId);
    if (!updateFlag) {
      return false;
    }
    stringRedisTemplate.delete(USER_LOGIN + user.getUserPhone());
    return true;
  }

  /**
   * @return boolean
   * @description 删除用户信息(逻辑删除)
   */
  @Override
  public boolean deleteUser(Long userId) {
    int deleteFlag = blogUserDao.deleteById(userId);
    if (deleteFlag != 1) {
      return false;
    }
    UserDTO user = UserHolder.getUser();
    stringRedisTemplate.delete(USER_LOGIN + user.getUserPhone());
    return true;
  }

  /**
   * @return boolean
   * @description 获取用户所有信息
   */
  @Override
  public BlogUserEntity getUserInfo() {
    Long userId = UserHolder.getUser().getUserId();
    BlogUserEntity userInfo = getById(userId);
    if (userInfo == null) {
      return null;
    }
    return userInfo;
  }

  /**
   * @param
   * @return ArrayList<BlogUserEntity>
   * @description 获取用户所有信息
   */
  @Override
  public ArrayList<BlogUserEntity> getAllUser() {
    ArrayList<BlogUserEntity> allUser = blogUserDao.getALLUser();
    if (allUser == null) {
      return new ArrayList<>();
    }
    return allUser;
  }

  /**
   * @return PageBean<BlogUserEntity>
   * @description 用户分页查询
   */
  @Override
  public PageBean<BlogUserEntity> usetList(Integer pageNum, Integer pageSize, String userName,
      String userPhone, Integer deleted) {
    //获取pageBean对象，将查询得到的数据填充进去
    PageBean<BlogUserEntity> pageBean = new PageBean<>();
    //使用分页插件pageHelper开启分页查询
    PageHelper.startPage(pageNum, pageSize);
    //调用dap层的动态sql去查询数据
    List<BlogUserEntity> userList = blogUserDao.userList(userName, userPhone, deleted);
    //需要强转到page对象，因为调用dao层拿不到返回的总记录数，但是可以使用pageHelper提供的page对象将其强转，获得查询总记录数，父类拿不到子类的方法
    Page<BlogUserEntity> list = (Page<BlogUserEntity>) userList;
    pageBean.setTotal(list.getTotal());
    pageBean.setAllUserData(list.getResult());
    return pageBean;
  }

  /**
   * @return String
   * @description 文件上传至oss
   */
  @Override
  public String upload(MultipartFile file) {
    String fileUrl;
    try {
      //获取文件的输入流，然后将其写在磁盘中
      //获取文件的原始文件名，目的是做截取，截取到文件的格式 .jpg .png
      String originalFilename = file.getOriginalFilename();
      //获得唯一的文件名进行保存使用.substring(文件名.lastIndexOf("分割对象"))
      assert originalFilename != null;
      String FileName = UUID.randomUUID().toString() + originalFilename.substring(
          originalFilename.lastIndexOf("."));
      //要想上传到指定的容器文件夹需要在上传的文件名前拼接文件名
      String fileEndName = OSS_USER_ICON + FileName;
      //使用oss工具类完成上传，只需要文件名和文件的输入流，可通过file.inputstream来获得
      fileUrl = OssUtils.OssUpload(fileEndName, file.getInputStream());
      //从threadLocal中去获取用户的id，用于接下来的文件信息更新
      Long userId = UserHolder.getUser().getUserId();
      blogUserDao.uploadUrl(fileUrl, userId);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return fileUrl;
  }
  /**
   * @return BlogUserEntity
   * @description 查询用户状态
   */
  @Override
  public UserDTO checkUser(String userPhone) {
    UserDTO userDTO = new UserDTO();
    BlogUserEntity blogUserEntity = blogUserDao.checkUserState(userPhone);
    if(blogUserEntity==null){
      return null;
    }
    BeanUtils.copyProperties(blogUserEntity,userDTO);
    return userDTO;
  }
}
