package demo.netty.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author git
 * @version 1.0
 * @Description: 本地文件写
 * @date 2022/1/20
 */
public class NioFileChannel1 {

    public static void main(String[] args) throws Exception{

        String string = "hello";

        // 创建一个输出流 -> channel
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\wwwroot\\netty.txt");
        // 通过fileOutputStream 获取 对应的FileChannel
        FileChannel fileChannel = fileOutputStream.getChannel();

        // 创建一个缓存区
        ByteBuffer byteBuffer =ByteBuffer.allocate(1024);
        // 将string放入byteBuffer
        byteBuffer.put(string.getBytes("UTF-8"));

        // 将byteBuffer进行flip
        byteBuffer.flip();

        //将byteBuffer 数据写入到fileChannel
        fileChannel.write(byteBuffer);

        fileOutputStream.close();



    }



}
