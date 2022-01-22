package demo.netty.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author
 * @desc：
 * @date：2022/1/22 19:08
 */
public class GroupChatClient {

    // 定义相关属性
    /**
     * IP
     */
    private final String HOST = "127.0.0.1";
    /**
     * 端口
     */
    private final int PORT = 6667;
    /**
     * Selector
     */
    private Selector selector;
    /**
     * SocketChannel
     */
    private SocketChannel socketChannel;
    /**
     * 用户
     */
    private String username;

    /**
     * 构造
     */
    public GroupChatClient() throws Exception {

        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", PORT));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username + "is ok ");
    }

    /**
     * 向服务器发送消息
     *
     * @param msg
     */
    public void sendMsg(String msg) {
        msg = username + " 说：" + msg;
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取从服务器回复的消息
     */
    public void readMsg() {

        try {
            int readChannels = selector.select();
            // 有可用通道
            if (readChannels > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        // 得到相关的通道
                        SocketChannel sc = (SocketChannel) key.channel();
                        // 得到一个buffer
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        //读取
                        sc.read(allocate);
                        // 把读到的缓冲区数据转成字符串
                        String s = new String(allocate.array());
                        System.out.println(s.trim());
                    }
                }
            } else {
                System.out.println("没有可用通道");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // 启动客户端
        GroupChatClient chatClient = new GroupChatClient();
        new Thread(){
            @Override
            public void run() {
                while (true){

                    chatClient.readMsg();
                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        // 发送数据给服务器
        Scanner scanner = new Scanner( System.in);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            chatClient.sendMsg(s);
        }



    }



}
