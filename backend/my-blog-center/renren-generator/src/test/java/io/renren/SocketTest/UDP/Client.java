package io.renren.SocketTest.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import org.apache.tomcat.util.descriptor.tld.TldRuleSet.Variable;

/**
 * @Description: UDP客户端
 * @Author: 王朝
 * @Date: 2023/12/27 16:20
 */
public class Client {
  public static void main(String[] args) throws Exception {
    DatagramSocket clientSocket = new DatagramSocket();
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("请输入你要发送的消息:");
      String msg = scanner.next();
      byte[] bytes = msg.getBytes();
      if(msg.equals("exit")){
        System.out.println("客户端退出成功！");
        clientSocket.close();
        break;
      }
      DatagramPacket packet = new DatagramPacket(bytes,bytes.length, InetAddress.getLocalHost(),8888);
      clientSocket.send(packet);
    }
  }

}
