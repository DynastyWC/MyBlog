package io.renren.reflection;

/**
 * @Description: 学会
 * @Author: 王朝
 * @Date: 2023/12/30 13:38
 */

public class Student {


  public String getName() {
    return name;
  }

  public String getHobbit() {
    return hobbit;
  }

  public Integer getAge() {
    return age;
  }

  public Student(String name, String hobbit, Integer age) {
    this.name = name;
    this.hobbit = hobbit;
    this.age = age;
  }

  private String name;
  private String hobbit;
  private Integer age;

  public void setName(String name) {
    this.name = name;
  }

  public void setHobbit(String hobbit) {
    this.hobbit = hobbit;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return super.toString();
  }



}
