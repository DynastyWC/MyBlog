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

  @Select("select * from blog_user where user_phone = #{userPhone}")
  BlogUserEntity getByEmail(String userPhone);

      @Update("update blog_user set user_name = #{param1}, user_email = #{param2}, user_pwd = #{param3}, icon = #{param4},motto = #{param5} where user_id = #{param6}")
      boolean updateInfo(@Param("param1") String userName, @Param("param2") String userEmail, @Param("param3") String userPwd, @Param("param4") String icon, @Param("param5") String motto, @Param("param6") Long userId);
}