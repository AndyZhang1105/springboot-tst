package com.zz.io.netty.server5_client;

import com.zz.io.netty.server5.UserProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

public class DecoderClientHandler extends SimpleChannelInboundHandler<UserProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 3; ++i) {
            String messageToBeSent = "sent from client " + UUID.randomUUID().toString();
            byte[] content = messageToBeSent.getBytes(Charset.forName("utf-8"));
            int length = messageToBeSent.getBytes(Charset.forName("utf-8")).length;

            UserProtocol userProtocol = new UserProtocol();
            userProtocol.setLength(length);
            userProtocol.setContent(content);

            ctx.writeAndFlush(userProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("客户端接收到的内容：" + new String(content, Charset.forName("utf-8")));
        System.out.println("客户端接受到的消息数量：" + (++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
