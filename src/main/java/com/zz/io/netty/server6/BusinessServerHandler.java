package com.zz.io.netty.server6;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BusinessServerHandler extends ChannelInboundHandlerAdapter {

    private static int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("BusinessServerHandler call count=" + ++count);

        ByteBuf buf = (ByteBuf)msg;
        int length = buf.readInt();
        System.out.println("length: " + length);

        byte[] head = new byte[4];
        buf.readBytes(head);
        String headString = new String(head);
        System.out.println("head: " + headString);

        byte[] body = new byte[length - 8];
        buf.readBytes(body);
        String bodyString = new String(body);
        System.out.println("body: " + bodyString);
    }

}
