package com.dynasty.blog.user.DTO;

import lombok.Data;

@Data
public class UserDTO {

  private Long userId;
  private String userPhone;
  private String userName;
  private String icon;
  private Integer deleted;
  private Integer userRole;
}
