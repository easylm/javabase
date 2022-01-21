package demo.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author dana
 * @version 1.0
 * @Description:
 * @date 2022/1/21
 */
public class NIOServer {

    public static void main(String[] args) throws Exception {

        //创建serversocketchannel -> serversocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到一个selecor对象
        Selector selector = Selector.open();
        //绑定端口666 在服务端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //将serverSocketChannel 注册到 selector 关心事件为op_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //
        System.out.println("注册到selectorKey 数量 = "+selector.keys().size());

        //循环等待客户端链接
        while (true){
            // 我们等待1秒，没有事件就继续
            if(selector.select(1000) ==0){
                //没有事件发生
                System.out.println("服务器等待了1秒，无链接");
                continue;
            }

            /**
             *  如果返回的>0,获取相关的selectorKey集合
             * 1.如果返回的 >0 表示已经获取到关注的事件
             * 2.selector.selectedKeys()
             *
             */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectorKeys 数量 = "+selectionKeys.size());

            // 遍历,
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while(keyIterator.hasNext()){
                // 获取 SelectionKey
                SelectionKey key = keyIterator.next();
                // 根据key 对应的通道做相应的处理
                // 如果是 OP_ACCEPT,有新的客户端连接
                if(key.isAcceptable()){
                    // 该客户端生成一个socketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端链接成功"+socketChannel.hashCode());
                    // 将socketChannel设置为非阻塞
                    socketChannel.configureBlocking(false);
                    // 将socketChannel 注册到selector,关注事件为OP_ACCEPT,同时给socketChannel关联一个buffer
                    socketChannel.register(selector,SelectionKey.OP_ACCEPT, ByteBuffer.allocate(1024));
                    System.out.println("客户端连接后，注册到selectorKey 数量 = "+selector.keys().size());
                }
                // OP_READ
                if(key.isReadable()){
                    //通过key 反向获取到对应的channel
                    SocketChannel channel = (SocketChannel)key.channel();
                    // 获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer)key.attachment();
                    channel.read(buffer);
                    System.out.println("form 客户端:"+new String(buffer.array()));
                }
                // 手动从集合中移除selectorKey，防止重复操作
                keyIterator.remove();
            }
        }
    }
}
