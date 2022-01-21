package demo.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author dana
 * @version 1.0
 * @Description: Buffer类型化和只读
 * @date 2022/1/20
 */
public class NioByteBufferPutGet {

    public static void main(String[] args) {
        
        //创建一个Buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);
        
        //类型化方式放入数据
        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('s');
        buffer.putShort((short) 4);
        
        buffer.flip();
        System.out.println();
        System.out.println(buffer.getShort());
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        
        
        
    }
    
    
}
