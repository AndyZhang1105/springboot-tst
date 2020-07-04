package com.zz.io.netty.server5;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class DecoderServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new UserDecoder());
        pipeline.addLast(new UserEncoder());
        pipeline.addLast(new DecoderServerHandler());
    }

}
