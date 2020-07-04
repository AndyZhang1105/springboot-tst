package com.zz.io.netty.server6_client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SocketClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 20; i++) {
            sendInfo(ctx);
        }

    }

    private void sendInfo(ChannelHandlerContext ctx) {
        UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
        ByteBuf buffer = allocator.buffer(20);

        buffer.writeInt(1604);
        buffer.writeBytes("head".getBytes());
        String longMsgBody = "";
        for (int i = 0; i < 400; i++) {
            longMsgBody = longMsgBody + "body";
        }
        buffer.writeBytes(longMsgBody.getBytes());

        ctx.writeAndFlush(buffer);
    }

}