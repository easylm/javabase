package demo.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author dana
 * @version 1.0
 * @Description:
 * Scattering:将数据写入到buffer时，可以采用buffer数组，依次写入【分散】
 * Gathering ： 从buffer读取数据时，可以采用buffer数组，依次读
 * @date 2022/1/21
 */
public class ScatteringAndGatheringTest {

    public static void main(String[] args) throws Exception{

        // 使用serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8080);
        //绑定端口
        serverSocketChannel.socket().bind(inetSocketAddress);
        // 创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        // 等客户端链接telnet
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messagelength = 8; // 从客户端接受8个字节
        //循环读取
        while (true){
            int byteRead = 0;
             while(byteRead <= messagelength){
                 long read = socketChannel.read(byteBuffers);
                 byteRead += read; //累计读取的字节数
                 System.out.println("byteRead = " + byteRead);
                 //使用流打印 看看buffer的postion和limit
                 Arrays.asList(byteBuffers).stream().map(buffer -> "postion="+ buffer.position() + ",limit = "+ buffer.limit()).forEach( System.out::println);
             }
             // 将所有的buffer 进行 flip
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());
             // 将数据读出显示到客户端
            long byteWrite = 0;
            while(byteWrite < messagelength){
                long write = socketChannel.write(byteBuffers);
                byteWrite += write;
            }
            // 将所有的buffer进行clear
            Arrays.asList(byteBuffers).forEach( byteBuffer -> {
                byteBuffer.clear();
            });
            System.out.println("byteRead:"+byteRead + "byteWrite="+byteWrite+",messageLength:"+messagelength);
        }
    }
}
