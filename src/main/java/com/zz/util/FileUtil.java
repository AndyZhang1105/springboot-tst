package com.zz.util;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {

    public static String read(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        StringBuffer sb = new StringBuffer();

        if (file.isFile() && file.exists()) { // 判断文件是否存在
            // System.out.println("以字节为单位读取文件内容，一次读多个字节：");
            byte[] bytes = new byte[1024];
            int readLength = 0;
            InputStream in = new FileInputStream(file);

            try {
                // 读入多个字节到字节数组中，read为一次读入的字节数
                while ((readLength = in.read(bytes)) != -1) {
                    String str = new String(bytes, 0, readLength);
                    sb.append(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return sb.toString();
        } else {
            throw new FileNotFoundException("找不到指定的文件，请确认文件路径是否正确");
        }
    }

    public static String readByFileChannel(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();

        RandomAccessFile aFile = new RandomAccessFile(filePath, "r");
        FileChannel inChannel = aFile.getChannel(); // 从一个InputStream OutputStream中获取channel

        // create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int bytesRead = inChannel.read(buf); // read into buffer.
        while(bytesRead != -1) {
            buf.flip(); // make buffer ready for read
            while(buf.hasRemaining()) {
                sb.append((char) buf.get()); // read 1 byte at a time
            }
            buf.clear(); // make buffer ready for writing
            bytesRead = inChannel.read(buf);
        }

        aFile.close();

        return sb.toString();
    }

    public static void write(String filePath, String content, boolean append) throws IOException {
        File file = new File(filePath);

        if(!file.exists()){
            file.createNewFile();
        }

        FileWriter fileWritter = new FileWriter(file, append); // 第二个参数使用true，即进行append file
        fileWritter.write(content);
        fileWritter.close();
    }

    public static void writeByFileChannel(String filePath, String content) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile(filePath, "rw");
        FileChannel fileChannel = aFile.getChannel(); // 从一个InputStream OutputStream中获取channel

        ByteBuffer buf = ByteBuffer.allocate(content.getBytes().length);
        buf.put(content.getBytes());
        while(buf.hasRemaining()) {
            fileChannel.write(buf); // write into file from buffer.
        }

        aFile.close();
    }

    public static String getResourcePath() throws FileNotFoundException {
        return ResourceUtils.getURL("classpath:").getPath();
    }

    public static String getResourcePath(String filename) throws FileNotFoundException {
        return getResourcePath() + filename;
    }

}
