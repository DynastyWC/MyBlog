package com.dynasty.blog.user.dao;

import com.dynasty.blog.user.entity.TScheduleResultEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 
 * 
 * @author dynasty
 * @email study_wc@163.com
 * @date 2024-06-01 13:06:04
 */
@Mapper
public interface TScheduleResultDao extends BaseMapper<TScheduleResultEntity> {
    @Update(" UPDATE t_schedule_result SET data_set = #{dataSet},data_url=#{dataUrl} WHERE order_num=#{orderNum}")
    void toUpdate(@Param("dataSet") String url, @Param("orderNum") String orderNum,@Param("dataUrl") String dataUrl);
}
