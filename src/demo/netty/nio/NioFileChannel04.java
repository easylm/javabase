package demo.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author dana
 * @version 1.0
 * @Description: 拷贝图片
 * @date 2022/1/20
 */
public class NioFileChannel04 {

    public static void main(String[] args) throws Exception {
        //创建相关流
        FileInputStream fileInputStream = new FileInputStream("D:\\wwwroot\\划线价.png");
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\wwwroot\\划线价12.png");

        // 获取各个流对应的fileChannel
        FileChannel source = fileInputStream.getChannel();
        FileChannel destc = fileOutputStream.getChannel();

        //使用transferForm完成拷贝
        destc.transferFrom(source,0,source.size());

        //关闭流
        source.close();
        destc.close();
        fileInputStream.close();
        fileOutputStream.close();


    }


}
