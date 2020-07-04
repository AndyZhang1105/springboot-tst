package com.zz.io.netty.server6;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class SelfDefineDecodeHandler extends ByteToMessageDecoder {

    private static int count = 0;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf bufferIn, List<Object> out) throws Exception {
        if (bufferIn.readableBytes() < 4) {
            return;
        }

        int beginIndex = bufferIn.readerIndex();
        int length = bufferIn.readInt();

        System.out.println("decode call count=" + ++count);
        System.out.println("bufferIn.readableBytes()=" + bufferIn.readableBytes());
        System.out.println("beginIndex=" + beginIndex);

        if (bufferIn.readableBytes() < length) {
            bufferIn.readerIndex(beginIndex);
            return;
        }

        bufferIn.readerIndex(beginIndex + 4 + length);
        ByteBuf otherByteBufRef = bufferIn.slice(beginIndex, 4 + length);
        otherByteBufRef.retain();

        out.add(otherByteBufRef);
    }

}
