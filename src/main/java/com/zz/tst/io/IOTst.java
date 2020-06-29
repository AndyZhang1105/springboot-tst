package com.zz.tst.io;

import com.zz.util.FileUtil;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class IOTst {

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

    @SneakyThrows
    private static void testFileUtil() {
        String filePath = "/Users/andy/tst.txt";
        System.out.println(FileUtil.read(filePath));
        System.out.println(FileUtil.readByFileChannel(filePath));

        String content = "A cat will append to the end of the file.\n";
        FileUtil.write(filePath, content, true);
        FileUtil.writeByFileChannel(filePath, content);
    }

    public static void main(String[] args) throws IOException {
        testFileUtil();
    }
}
