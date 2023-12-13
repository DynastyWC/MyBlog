package com.dynasty.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dynasty.blog.entity.BlogUserEntity;
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
  @Select("select * from blog_user where user_phone = #{userPhone}")
  BlogUserEntity getByPhone(String userPhone);

  //更新用户信息
  @Update("update blog_user set user_name = #{param1}, user_email = #{param1},  motto = #{param3},icon = #{param4} where user_id = #{param5}")
  boolean updateInfo(@Param("param1") String userName, @Param("param2") String userEmail,
      @Param("param3") String motto, @Param("param4") String icon, @Param("param6") Long userId);

  //更新用户密码
  @Update("update blog_user set user_pwd = #{param1} where user_id = #{param2}")
  boolean updatePwd(@Param("param1") String encodePwd, @Param("param2") Long userId);
}