package com.zz.tst.io;

import com.zz.util.FileUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class IOTst {

    /*
     * Socket 通道
     */
    @lombok.SneakyThrows
    private static void testSocketIO() {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("https://www.cnblogs.com/chinaifae/p/10188858.html",80));

        // 读数据
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buf);

        // 写数据
        String newData = "New String to write to file..." + System.currentTimeMillis();
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        while(buf.hasRemaining()) {
            socketChannel.write(buf);
        }
    }

    private static void testServerSocketIO() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            //do something with socketChannel...
        }

    }

    /*
     * 与Selector一起使用时，Channel必须处于非阻塞模式，这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式(而套接字通道都可以)
     */
    private static void testSelector(Selector selector) {
        Set selectedKeys = selector.selectedKeys();
        Iterator keyIterator = selectedKeys.iterator();
        while(keyIterator.hasNext()) {
            SelectionKey key = (SelectionKey) keyIterator.next();
            if(key.isAcceptable()) {
                // a connection was accepted by a ServerSocketChannel.
            } else if (key.isConnectable()) {
                // a connection was established with a remote server.
            } else if (key.isReadable()) {
                // a channel is ready for reading
            } else if (key.isWritable()) {
                // a channel is ready for writing
            }
            keyIterator.remove(); //注意这里必须手动remove；表明该selectkey我已经处理过了；
        }
    }

    private static void testDatagramChannelIO() throws IOException {
        // Datagram通道(channel 的读写操作与前面的有差异)
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));

        // 读数据
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        channel.receive(buf);

        /*receive()方法会将接收到的数据包内容复制到指定的Buffer. 如果Buffer容不下收到的数据，多出的数据将被丢弃。 */

        // 写数据
        String newData = "New String to write to file..." + System.currentTimeMillis();
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        int bytesSent = channel.send(buf,new InetSocketAddress("jenkov.com",80));
    }

    public static void main(String[] args) throws IOException {
        String filePath = "/Users/andy/tst.txt";
        System.out.println(FileUtil.read(filePath));
        System.out.println(FileUtil.readByFileChannel(filePath));

        String content = "A cat will append to the end of the file.\n";
        FileUtil.write(filePath, content, true);
        FileUtil.writeByFileChannel(filePath, content);



        // TODO:
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("https://www.cnblogs.com/chinaifae/p/10188858.html",80));

        Selector selector = null;
        SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ); /*第二个参数表明Selector监听Channel时对什么事件感兴趣(SelectionKey.OP_CONNECT  SelectionKey.OP_ACCEPT  SelectionKey.OP_READ SelectionKey.OP_WRITE),可以用或操作符将多个兴趣组合一起*/

    }
}
