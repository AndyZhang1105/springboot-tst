package com.zz.io.netty.server3;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class LiveServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new LiveEncoder());
        pipeline.addLast(new LiveDecoder());
        pipeline.addLast(new LiveHandler());
    }

}