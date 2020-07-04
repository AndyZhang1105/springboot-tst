package com.zz.io.netty.server3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class LiveDecoder extends ReplayingDecoder<LiveMessage> {

    private LiveMessage message = new LiveMessage();

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte type = in.readByte();
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);

        message.setType(type);
        message.setLength(length);
        message.setContent(new String(content));

        out.add(message);
    }
}