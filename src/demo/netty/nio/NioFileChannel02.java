package demo.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dana
 * @version 1.0
 * @Description: 本地文件读
 * @date 2022/1/20
 */
public class NioFileChannel02 {

    public static void main(String[] args) throws Exception{

        File file = new File("D:\\wwwroot\\netty.txt");

        FileInputStream fileInputStream = new FileInputStream(file);

        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());

        fileChannel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));

        fileInputStream.close();



    }

}
