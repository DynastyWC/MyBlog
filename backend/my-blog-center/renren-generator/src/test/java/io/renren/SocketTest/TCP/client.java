package io.renren.SocketTest.TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 客户端tcp 多发多收、上下线追踪！
 * @Author: 王朝
 * @Date: 2023/12/27 17:08
 */
public class client {

  public static void main(String[] args) throws IOException {
    //使用executors定义线程池,用于异步接受自身发送的消息
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    System.out.println("---tcp客户端启动---");
    //定义tcp客户端指定发送的主机ip和端口号
    Socket socket = new Socket("127.0.0.1", 8888);
    //异步处理自身接收的消息
    executorService.execute(new receiveMsg(socket));
    //从客户端socket获得输出流，稍后往该流写数据
    OutputStream outputStream = socket.getOutputStream();
    //将输出流包装一下为高级的输出流
    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
    //定义一个输入扫描器
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("请输入你的内容：");
      String msg = scanner.next();
      if (msg.equals("exit")) {
        //判断退出
        System.out.println("客户端已关闭！");
        //先退出字符流
        dataOutputStream.close();
        //退出socket管道
        socket.close();
        break;
      }
      //往字节流写数据
      dataOutputStream.writeUTF(msg);
    }
  }
//定义异步处理发送的数据
  public final static class receiveMsg implements Runnable {

    private Socket socket;

    public receiveMsg(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {
      try {
        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        while (true) {
          try {
            String msg = dataInputStream.readUTF();
            System.out.println(msg);
          } catch (IOException e) {
            System.out.println("自己下线了：" + socket.getRemoteSocketAddress());
            dataInputStream.close();
            socket.close();
            break;
          }
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
