package com.zz.netty.client2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

public class HttpClientInitializer extends ChannelInitializer<SocketChannel> {

    private final boolean ssl;

    public HttpClientInitializer(boolean ssl) {
        this.ssl = ssl;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();

        p.addLast("log", new LoggingHandler(LogLevel.INFO));

//        if (ssl) {
//            SSLEngine engine = SslContextFactory.getClientContext().createSSLEngine();
//            engine.setUseClientMode(true);
//            p.addLast("ssl", new SslHandler(engine));
//        }

        p.addLast("request-encoder", new HttpRequestEncoder());
        p.addLast("response-decoder", new HttpResponseDecoder());

        // Remove the following line if you don't want automatic content decompression.
        p.addLast("inflater", new HttpContentDecompressor());

        //HttpObjectAggregator会把多个消息转换为 一个单一的FullHttpRequest或是FullHttpResponse
        //p.addLast("aggregator", new HttpObjectAggregator(1048576));
        p.addLast("handler", new HttpClientHandler());
    }
}
