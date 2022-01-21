package demo.netty.nio.groupchat;

import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
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
    public GroupChatServer(){
        try{
            // 得到选择器
            selector = Selector.open();

            listenChannel= ServerSocketChannel.open();
            // 绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            // 设置非阻塞
            listenChannel.configureBlocking(false);
            //listenChannel 注册到selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // 监听
    public void listen(){

        try{
            //循环处理
            while (true){
                int count = selector.select(2000);
                if (count > 0){
                    // 有事件处理
                    // 遍历得到selectorKey集合
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while(keyIterator.hasNext()){
                        // 取出selectionKey
                        SelectionKey key = keyIterator.next();
                        // 监听到accept
                        if(key.isAcceptable()){
                            SocketChannel sc = listenChannel.accept();
                            // 将sc注册到selector
                            sc.register(selector,SelectionKey.OP_ACCEPT);
                            // 提示
                            System.out.println(sc.getRemoteAddress()+"上线了");
                        }
                        // 通道发生读事件，
                        if(key.isReadable()){
                            // 处理读
                        }
                        keyIterator.remove();
                    }




                }else{
                    System.out.println("等待中");
                }

            }

        }catch (Exception e){

        }finally {

        }


    }


    public static void main(String[] args) {




    }
}
