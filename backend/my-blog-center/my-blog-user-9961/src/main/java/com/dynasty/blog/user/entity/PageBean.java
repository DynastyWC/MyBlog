package com.dynasty.blog.user.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 接收分页查询结果，包含总记录数和总查询数据
 * @Author: 王朝
 * @Date: 2023/12/16 20:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {

  //分页查询的总记录数
  private Long total;
  //分页查询所查到的所有结果
  private List<T> allUserData;

}
