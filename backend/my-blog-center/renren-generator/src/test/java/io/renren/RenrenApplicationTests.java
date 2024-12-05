package io.renren;

import io.renren.entry.callableTest;
import io.renren.entry.student;
import io.renren.reflection.Student;
import io.renren.reflection.Teacher;
import io.renren.reflection.objectFlection;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RenrenApplicationTests {

  @Test
  public void contextLoads() {}
  @Test
  public void testStream(){
    ArrayList<String> strings = new ArrayList<>();
    Collections.addAll(strings,"王朝","张无忌","杨丽萍","臭蒜猪","臭蛋蛋");
    List<String> list = strings.stream().filter(s -> s.startsWith("臭")).collect(Collectors.toList());
    System.out.println(list);
    HashMap<String, String> map = new HashMap<>();
    map.put("222", "333");
    map.put("888", "999");
    map.put("000", "111");
    map.forEach((k,v)-> System.out.println(k+"----->"+v));
    Set<String> mapKey = map.keySet();
    Collection<String> mapValues = map.values();
    List<String> collect = mapKey.stream().filter(k -> k.contains("1"))
        .collect(Collectors.toList());
    System.out.println(collect);
    mapValues.stream().filter(v->v.startsWith("9")).forEach(v-> System.out.println(v));
    Set<Entry<String, String>> entries = map.entrySet();
    Stream<Entry<String, String>> stream = entries.stream();
    stream.filter(e->e.getKey().contains("8")||e.getKey().contains("0")).forEach(e-> System.out.println(e));
    System.out.println(entries);
  }
  @Test
  public void threadPollTest(){
    ExecutorService threadPoll  = new ThreadPoolExecutor(3, 5,
        8, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4),
        Executors.defaultThreadFactory(), new AbortPolicy());
    try {
//      student student = new student();
//      threadPoll.execute(student);
//      threadPoll.execute(student);
//      threadPoll.execute(student);
//      threadPoll.execute(student);
//      threadPoll.execute(student);
//      threadPoll.execute(student);
      Future <String> f1 = threadPoll.submit(new callableTest());
      Future <String> f2 = threadPoll.submit(new callableTest());
      Future <String> f3 = threadPoll.submit(new callableTest());
      Future <String> f4 = threadPoll.submit(new callableTest());
      String s1 = f1.get();
      String s2 = f2.get();
      String s3 = f3.get();
      String s4 = f4.get();
      System.out.println(s1);
      System.out.println(s2);
      System.out.println(s3);
      System.out.println(s4);
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      threadPoll.shutdown();

    }


  }
  @Test
  public void testbeiguansuo(){
    Runnable student = new student();
    for (int i = 1; i <=100 ; i++) {
      new Thread(student).start();
    }
  }
  @Test
  public void testFlection() throws FileNotFoundException, IllegalAccessException {
    Student stu = new Student("王朝", "pingpang",24);
    Teacher teacher = new Teacher("杨丽萍", "数学");
    objectFlection.objectSave(stu);
    objectFlection.objectSave(teacher);
  }
}
