package io.renren.reflection;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;

/**
 * @Description: 对象反射类，将获得的java对象写入到文件中去
 * @Author: 王朝
 * @Date: 2023/12/30 13:41
 */
public class objectFlection {

  public static void objectSave(Object obj) throws FileNotFoundException, IllegalAccessException {
    //使用new PrintStream(new FileOutStream("文件地址"，开启追加（ture）))
    PrintStream printStream = new PrintStream(new FileOutputStream(
        "F:\\MyPlayGround\\MyBlog\\MyBlog\\backend\\my-blog-center\\renren-generator\\src\\test\\java\\io\\renren\\reflection\\data.txt",
        true));
    //获取文件的class对象使用。getclass（）方法
    Class<?> objClass = obj.getClass();
    //使用.getSimpleName()获取对象名称,打印出来
    printStream.println("---" + objClass.getSimpleName() + "---");
    //获取到对象中的所有字段.getDeclaredFileds()
    Field[] declaredFields = objClass.getDeclaredFields();
    //拿到对象中每一个的字段名的操作对象
    for (Field declaredField : declaredFields) {
      //开启字段的访问权限
      declaredField.setAccessible(true);
      //字段对象.getname获取字段名
      String name = declaredField.getName();
      //获取该字段所传入的值
      String value = declaredField.get(obj) + "";
      printStream.println(name + "=" + value);
    }
///关闭打印流
    printStream.close();
  }
}
