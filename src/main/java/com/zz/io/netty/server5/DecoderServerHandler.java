package com.zz.io.netty.server5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

public class DecoderServerHandler extends SimpleChannelInboundHandler<UserProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();
        String contentString = new String(content, Charset.forName("utf-8"));

        System.out.println("服务端接收到的消息：" + contentString);
        System.out.println("服务端接收到的消息数量：" + (++this.count));

        String responseMessage = UUID.randomUUID().toString();
        int responseLength = responseMessage.getBytes("utf-8").length;
        byte[] responseContent = responseMessage.getBytes("utf-8");

        UserProtocol userProtocol = new UserProtocol();
        userProtocol.setLength(responseLength);
        userProtocol.setContent(responseContent);

        ctx.writeAndFlush(userProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
