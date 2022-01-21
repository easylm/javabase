package demo.netty.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dana
 * @version 1.0
 * @Description:
 * MappedByteBuffer 可让文件直接在内存(堆外内存)修改，操作系统不需要拷贝一次
 * @date 2022/1/20
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\wwwroot\\netty.txt","rw");

        FileChannel channel = randomAccessFile.getChannel();
        /**
         * 参数1: FileChannel.MapMode.READ_WRITE 读写模式
         * 参数2：0 可以直接修改的起始位置
         * 参数3：5，是映射到内存的大小，即将txt的多少个字节映射到内存
         * 可以直接修改的范围0-5
         */
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        map.put(0,(byte)'H');
        map.put(3,(byte) '9');

        //map.put(5,(byte) '9');// java.lang.IndexOutOfBoundsException

        randomAccessFile.close();
    }

}
