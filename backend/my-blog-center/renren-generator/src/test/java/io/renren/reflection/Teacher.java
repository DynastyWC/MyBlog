package io.renren.reflection;

/**
 * @Description: 教师类
 * @Author: 王朝
 * @Date: 2023/12/30 13:40
 */
public class Teacher {


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getXueke() {
    return xueke;
  }

  public void setXueke(String xueke) {
    this.xueke = xueke;
  }

  @Override
  public String toString() {
    return "Teacher{" +
        "name='" + name + '\'' +
        ", xueke='" + xueke + '\'' +
        '}';
  }

  public Teacher(String name, String xueke) {
    this.name = name;
    this.xueke = xueke;
  }

  private String name;
 private String xueke;
}
