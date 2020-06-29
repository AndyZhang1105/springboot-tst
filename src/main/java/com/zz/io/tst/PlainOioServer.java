package com.zz.io.tst;

import com.zz.util.StringUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

@Service
public class PlainOioServer {

    @SneakyThrows
    public static void main(String[] args) {
        PlainOioServer plainOioServer = new PlainOioServer();
        plainOioServer.server(8090);
    }

    @SneakyThrows
    public void server(int port) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(port);     //1
        System.out.println("----------------服务端执行，開始监听请求----------------");

        try {
            final Socket socket = serverSocket.accept();    //2 监听

            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String info;
            while ((info = bufferedReader.readLine()) != null) {
                System.out.println("我是server端，client请求为：" + info);
            }

            Thread.currentThread().sleep(50000);

            // 关闭资源
            socket.shutdownInput();
            bufferedReader.close();
            inputStream.close();
            socket.close();
            serverSocket.close();

            System.out.println("我是server端，请求关闭");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
