package com.zz.io.netty.server2_client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HttpClientHandler extends SimpleChannelInboundHandler<HttpObject> {

    public void messageReceived(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;

            System.out.println("STATUS: " + response.getStatus());
            System.out.println("VERSION: " + response.getProtocolVersion());
            System.out.println();

            if (!response.headers().isEmpty()) {
                for (String name : response.headers().names()) {
                    for (String value : response.headers().getAll(name)) {
                        System.out.println("HEADER: " + name + " = " + value);
                    }
                }
                System.out.println();
            }

            if (HttpHeaders.isTransferEncodingChunked(response)) {
                System.out.println("CHUNKED CONTENT {");
            } else {
                System.out.println("CONTENT {");
            }
        }
        if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;

            System.out.print(content.content().toString(CharsetUtil.UTF_8));
            System.out.flush();

            if (content instanceof LastHttpContent) {
                System.out.println("} END OF CONTENT");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        messageReceived(ctx, msg);
    }

}
