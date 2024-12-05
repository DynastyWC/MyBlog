package io.renren.SocketTest.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @Description: UDP服务端
 * @Author: 王朝
 * @Date: 2023/12/27 16:21
 */
public class Server {

  public static void main(String[] args) throws Exception {
    System.out.println("---服务端准备准备接收数据---");
    DatagramSocket serverSocket = new DatagramSocket(8888);
    byte[] bytes = new byte[1024*64];
    DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
    while (true) {
      serverSocket.receive(packet);
      int length = packet.getLength();
      String s = new String(bytes, 0, length);
      System.out.println(s);
    }
//    System.out.println(packet.getAddress().getHostAddress());
  }
}
