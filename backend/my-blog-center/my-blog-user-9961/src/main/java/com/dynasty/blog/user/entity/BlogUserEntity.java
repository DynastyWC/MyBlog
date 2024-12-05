package com.dynasty.blog.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author dynasty
 * @email study_wc@163.com
 * @date 2023-12-11 22:49:03
 */
@Data
@TableName("blog_user")
public class BlogUserEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 用户id*
   */
  @TableId(value = "user_id", type = IdType.AUTO)
  private Long userId;
  /**
   * 用户姓名*
   */

  @NotEmpty(message = "用户姓名不能为空!")
  private String userName;
  /**
   * 用户手机号*
   */
  @Pattern(regexp = "^1[34578]\\d{9}$", message = "手机号码格式错误")
  @NotEmpty(message = "用户手机号不能为空!")
  private String userPhone;
  /**
   * 用户身份*
   */

  private Integer userRole;
  /**
   * 用户邮箱-登录账号*
   */
  @Email(message = "email不合法!")
  @NotEmpty(message = "用户邮箱不能为空!")
  private String userEmail;
  /**
   * 用户座右铭*
   */
  private String motto;
  /**
   * 用户头像*
   */

  private String icon;
  /**
   * 用户登录密码*
   */
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "密码长度至少8位，包含字母和数字")
  private String userPwd;
  /**
   * 乐观锁
   */
  @TableField(value = "version", fill = FieldFill.INSERT)
  @Version
  private Integer version;
  /**
   * 写入时间
   */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime addTime;
  /**
   * 修改时间
   */

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;
  /**
   * 逻辑删除状态
   */
  @TableLogic()
  @TableField(value = "deleted", fill = FieldFill.INSERT_UPDATE)
  private Integer deleted;
}
