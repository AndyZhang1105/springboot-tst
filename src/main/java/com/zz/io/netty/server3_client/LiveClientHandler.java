package com.zz.io.netty.server3_client;

import com.zz.io.netty.server3.LiveMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

public class LiveClientHandler extends SimpleChannelInboundHandler<LiveMessage> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 3; ++i) {
            String messageToBeSent = "sent from client " + UUID.randomUUID().toString();
            int length = messageToBeSent.getBytes(Charset.forName("utf-8")).length;

            LiveMessage liveMessage = new LiveMessage();
            liveMessage.setType(LiveMessage.TYPE_MESSAGE);
            liveMessage.setLength(length);
            liveMessage.setContent(messageToBeSent);

            ctx.writeAndFlush(liveMessage);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LiveMessage msg) throws Exception {
        System.out.println("客户端接收到的内容：" + msg.getContent());
        System.out.println("客户端接受到的消息数量：" + (++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
