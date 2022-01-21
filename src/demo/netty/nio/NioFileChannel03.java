package demo.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dana
 * @version 1.0
 * @Description: 把一个文件的内容读取到另一个文件
 * @date 2022/1/20
 */
public class NioFileChannel03 {


    public static void main(String[] args) throws Exception{

        FileInputStream fileInputStream = new FileInputStream("D:\\wwwroot\\netty.txt");

        FileChannel fileChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\wwwroot\\netty1.txt");
        FileChannel fileChannel2 = fileOutputStream.getChannel();
        // 文件大小不知道
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true){ //循环读取

            byteBuffer.clear(); //清空buffer
            int read = fileChannel.read(byteBuffer);
            if (read == -1){ // 表示读取完
                break;
            }
            // 将buffer中的数据写入
            byteBuffer.flip();
            fileChannel2.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
