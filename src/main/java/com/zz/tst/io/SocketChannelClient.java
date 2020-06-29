package com.zz.tst.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SocketChannelClient {

    public static void main(String[] args) throws Exception {

        // 创建选择器
        Selector selector = Selector.open();

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 8091));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        while (true) {
            int selectInt = selector.select();
            if (selectInt == 0)
                continue;

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isConnectable()) {
                    handleConnect(key);
                }
                if (key.isReadable()) {
                    handleRead(key);
                }
                if (key.isWritable()) {
                    handleWrite(key);
                }
                iterator.remove();
            }
        }

    }

    public static void handleConnect(SelectionKey key) throws Exception {
        SocketChannel channel = (SocketChannel) key.channel();
        if (channel.isConnectionPending()) {
            channel.finishConnect();
        }
        channel.configureBlocking(false);
        channel.register(key.selector(), SelectionKey.OP_READ);

        sendInfo(channel, "客户端测试!");
    }

    public static void handleRead(SelectionKey key) throws Exception {
        SocketChannel channel = (SocketChannel) key.channel();
        String msg = "";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(channel.read(buffer) > 0) {
            buffer.flip();
            while(buffer.hasRemaining()) {
                msg += new String(buffer.get(new byte[buffer.limit()]).array());
            }
            buffer.clear();
        }

        System.out.println("收到服务端消息:" + msg);

        Thread.sleep(5000);

        sendInfo(channel, "Hi, let's start a very funny game.");
    }

    public static void handleWrite(SelectionKey key) throws Exception {
        System.out.println("客户端写数据!");
    }

    public static void sendInfo(SocketChannel clientChannel, String msg) throws Exception {
        // 向服务端发送连接成功信息
        clientChannel.write(ByteBuffer.wrap(msg.getBytes()));
    }

}
