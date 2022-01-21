

##### 通道Channel

serverSocketChannel:在服务端监听新的客户端Socket连接
socketChannel: 网络io通道，负责进行读写操作，Nio把缓冲区的数据写入通道，或者把通道的数据读到缓冲区






#### Selector（选择器）
- Java的Nio，用非阻塞的IO方式，可以用一个线程，处理多个客户端链接，就会使用到选择器

- Selector 能够检测多个注册的通道上是否有事件发生。 多个channel 以事件的方式注册到同一个Selector

-  

1.Netty的IO线程NioEventLoop聚合了Selector选择器，也叫多路复用器，可以同时并发处理成百上千个客户端链接

2.当线程从莫客户端 



