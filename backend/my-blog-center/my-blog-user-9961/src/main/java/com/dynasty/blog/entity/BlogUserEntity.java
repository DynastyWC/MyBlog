package com.dynasty.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import java.time.LocalDateTime;
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

  /** 用户id* */
  @TableId(value = "id", type = IdType.AUTO)
  private Long userId;
  /** 用户姓名* */
  private String userName;
  /** 用户手机号* */
  private String userPhone;
  /** 用户身份* */
  private Integer userRole;
  /** 用户邮箱-登录账号* */
  private String userEmail;
  /** 用户座右铭* */
  private String motto;
  /** 用户头像* */
  private String icon;
  /** 用户登录密码* */
  private String userPwd;
  /** 乐观锁 */
  @TableField(fill = FieldFill.INSERT)
  @Version
  private Integer version;
  /** 写入时间 */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime addTime;
  /** 修改时间 */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;
  /** 逻辑删除状态 */
  @TableLogic()
  @TableField(fill = FieldFill.INSERT_UPDATE, select = false)
  private Integer deleted;
}
