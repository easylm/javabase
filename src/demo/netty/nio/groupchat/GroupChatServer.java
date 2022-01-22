package demo.netty.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author dana
 * @version 1.0
 * @Description:
 * @date 2022/1/21
 */
public class GroupChatServer {

    /**
     * 选择器
     */
    private Selector selector;
    /**
     * 监听通道
     */
    private ServerSocketChannel listenChannel;
    /**
     * 端口号
     */
    private static final int PORT = 6667;

    /**
     * 构造器
     * 初始化工作
     */
    public GroupChatServer() {
        try {
            // 得到选择器
            selector = Selector.open();

            listenChannel = ServerSocketChannel.open();
            // 绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            // 设置非阻塞
            listenChannel.configureBlocking(false);
            //listenChannel 注册到selector

            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 监听
     */
    public void listen() {

        try {
            //循环处理
            while (true) {
                int count = selector.select(2000);
                if (count > 0) {
                    // 有事件处理
                    // 遍历得到selectorKey集合
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()) {
                        // 取出selectionKey
                        SelectionKey key = keyIterator.next();
                        // 监听到accept
                        if (key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            // 将sc注册到selector
                            sc.register(selector, SelectionKey.OP_READ);
                            // 提示
                            System.out.println(sc.getRemoteAddress() + "上线了");
                        }
                        // 通道发生读事件，
                        if (key.isReadable()) {
                            // 处理读
                            readData(key);
                        }
                        // 当前的key移除，防止重复处理
                        keyIterator.remove();
                    }

                } else {
                    System.out.println("等待中");
                }
            }
        } catch (Exception e) {

        } finally {

        }
    }

    /**
     * 读取客户端消息
     */
    private void readData(SelectionKey key){
        // 定义一个SocketChannel
        // 取关联的channel
        SocketChannel channel = null;
        try {
           // 得到channel
            channel =(SocketChannel) key.channel();
            //创建buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            int count = channel.read(byteBuffer);
            //根据count的值做处理
            if(count > 0){
                 // 把缓存区的数据转成字符串
                String msg = new String(byteBuffer.array());
                //输出该消息
                System.out.println("form 客户端："+msg);
                // 向其他客户端转发消息
                sendMsgToOtherClient(msg,channel);
            }
        }catch (IOException e){
            try {
                System.out.println(channel.getRemoteAddress()+" 离线了。。");

                // 取消注册
                key.cancel();
                // 关闭通道
                channel.close();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 转发消息给其他接口
     * @param msg
     * @param self
     */
    private void sendMsgToOtherClient(String msg,SocketChannel self) throws IOException{
        System.out.println("服务器转发消息中。。。");
        // 遍历，所有注册到selector上的socketchannel，并排除self
        for (SelectionKey key:selector.selectedKeys()){
            // 通过key，取出对应的socketChannel
            Channel targetChannel = key.channel();
            // 排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != self){
                // 转型
               SocketChannel dest =  (SocketChannel) targetChannel;
               // 将msg 存储到buffer
                ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                // 将buffer数据写入通道
                dest.write(wrap);
            }
        }
    }


    /**
     * 主方法
     * @param args
     */
    public static void main(String[] args) {
        // 创建服务器对象
        GroupChatServer server = new GroupChatServer();
        server.listen();


    }
}
