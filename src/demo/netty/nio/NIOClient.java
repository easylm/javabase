package demo.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author dana
 * @version 1.0
 * @Description:
 * @date 2022/1/21
 */
public class NIOClient {
    public static void main(String[] args) throws Exception{
        // 得到一个通道
        SocketChannel socketChannel = SocketChannel.open();
        // 设置非阻塞
        socketChannel.configureBlocking(false);
        // 提供服务器ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        // 链接服务器
        if(!socketChannel.connect(inetSocketAddress)){
            while(!socketChannel.finishConnect()){
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他工作");
            }
        }
        // 如果链接成功，发送数据
        String str = "hello world";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        // 发送数据,将buffer写入 channel
        socketChannel.write(buffer);
        System.in.read();
    }
}
