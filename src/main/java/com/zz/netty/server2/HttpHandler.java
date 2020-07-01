package com.zz.netty.server2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private String result = "";

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println("Client channel is registered.");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        System.out.println("服务器收到的数据：" + msg.getClass().getName());
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof FullHttpRequest)) {
            result = "未知请求!";
            send(ctx, result, HttpResponseStatus.BAD_REQUEST);
            return;
        }
        FullHttpRequest httpRequest = (FullHttpRequest) msg;
        try {
            String path = httpRequest.uri();            //获取路径
            String body = getBody(httpRequest);         //获取参数
            HttpMethod method = httpRequest.method();   //获取请求方法

            if (!path.contains("/test")) {
                result = "非法请求!";
                send(ctx, result, HttpResponseStatus.BAD_REQUEST);
                return;
            }

            if (HttpMethod.GET.equals(method)) {
                System.out.println("body:" + body);
                result = "GET请求";
            } else if (HttpMethod.POST.equals(method)) {
                System.out.println("body:" + body);
                result = "POST请求";
            } else if (HttpMethod.PUT.equals(method)) {
                System.out.println("body:" + body);
                result = "PUT请求";
            } else if (HttpMethod.DELETE.equals(method)) {
                System.out.println("body:" + body);
                result = "DELETE请求";
            }
            send(ctx, result, HttpResponseStatus.OK);
        } catch (Exception e) {
            System.out.println("处理请求失败!");
            e.printStackTrace();
        } finally {
            httpRequest.release(); //释放请求
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete.");
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        if(null != cause) cause.printStackTrace();
        if(null != ctx) ctx.close();
    }

    /** * 获取body参数 * @param request * @return */
    private String getBody(FullHttpRequest request) {
        ByteBuf buf = request.content();
        return buf.toString(CharsetUtil.UTF_8);
    }

    // 客户端与服务器建立连接的时候触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }

    // 客户端与服务器关闭连接的时候触发
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress() + "成功断开连接！ ");
        super.channelInactive(ctx);
    }

    /** * 发送的返回值 * @param ctx 返回 * @param context 消息 * @param status 状态 */
    private void send(ChannelHandlerContext ctx, String context, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(context, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

}
