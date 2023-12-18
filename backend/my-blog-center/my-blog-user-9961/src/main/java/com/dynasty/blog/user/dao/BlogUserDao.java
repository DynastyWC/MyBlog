package com.dynasty.blog.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dynasty.blog.user.DTO.UserDTO;
import com.dynasty.blog.user.entity.BlogUserEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


/**
 * @author dynasty
 * @email study_wc@163.com
 * @date 2023-12-11 22:49:03
 */

@Mapper
public interface BlogUserDao extends BaseMapper<BlogUserEntity> {


  //查询手机号是否被注册
  @Select("select * from blog_user where user_phone = #{param1}")
  BlogUserEntity getByPhone(@Param("param1") String userPhone);

  //更新用户信息(已使用mybatis内置方法)
  @Update("update blog_user set user_name = #{param1}, user_email = #{param1},  motto = #{param3},icon = #{param4} where user_id = #{param5}")
  boolean updateInfo(@Param("param1") String userName, @Param("param2") String userEmail,
      @Param("param3") String motto, @Param("param4") String icon, @Param("param6") Long userId);

  //更新用户密码
  @Update("update blog_user set user_pwd = #{param1} where user_id = #{param2}")
  boolean updatePwd(@Param("param1") String encodePwd, @Param("param2") Long userId);

  //逻辑删除(已使用mybatis内置方法)
  @Delete("delete from blog_user  where user_id = #{param1}")
  boolean deleteUser(@Param("param1") Long userId);

  //登录校验用户是否处于禁用状态
  @Select("select * from blog_user where user_phone = #{param1}")
  BlogUserEntity checkUserState(@Param("param1") String userPhone);

  //查询所有用户信息
  @Select("SELECT  * FROM blog_user ")
  ArrayList<BlogUserEntity> getALLUser();

  //用户分页查询接口
  List<BlogUserEntity> userList(@Param("param1") String userName, @Param("param2") String userPhone,
      @Param("param3") Integer deleted);

  //更新用户头像地址
  @Update("update blog_user set icon = #{param1} where user_id = #{param2}")
  void uploadUrl(@Param("param1") String fileUrl, @Param("param2") Long userId);
}