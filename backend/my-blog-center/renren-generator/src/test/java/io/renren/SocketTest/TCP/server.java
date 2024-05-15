package io.renren.SocketTest.TCP;




import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.tomcat.util.descriptor.tld.TldRuleSet.Variable;

/**
 * @Description: 服务端tcp
 * @Author: 王朝
 * @Date: 2023/12/27 17:11
 */
public class server {
  //set集合用于存放在线用户，如果用户存在该集合中，就会接收到别人发送的消息
  private static final HashSet<Socket> socketSet = new HashSet<>();

  public static void main(String[] args) throws IOException {
    System.out.println("---tcp服务端启动---");
    ServerSocket serverSocket = new ServerSocket(8888);
    ExecutorService threadPool = Executors.newFixedThreadPool(3);
    while (true) {
      Socket socket = serverSocket.accept();
      System.out.println("有人上线了："+socket.getRemoteSocketAddress());
      //有人上线的话就将它添加到集合中
      socketSet.add(socket);
      threadPool.execute(new serverSocket(socket));
    }
  }
  public static class serverSocket implements Runnable{
      private final Socket socket;
      public  serverSocket(Socket socket){
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
            sendMsgToAll(msg);
          } catch (IOException e) {
            System.out.println("客户端下线："+socket.getRemoteSocketAddress());
            socketSet.remove(socket);
            dataInputStream.close();
            socket.close();
            break;
          }
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    private void sendMsgToAll(String msg) throws IOException {
      for (Socket socket : socketSet) {
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF(msg);
        dataOutputStream.flush();
      }
    }
  }
}
