package com.linfq.learn.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * Client.
 *
 * @author linfq
 * @date 2020/4/28 22:13
 */
public class Client {

	public static void main(String[] args) {
		new Client().clientStart();
	}

	private void clientStart() {
		EventLoopGroup workers = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(workers)
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						System.out.println("channel initialized!");
						ch.pipeline().addLast(new ClientHandler());
					}
				});

		try {
			System.out.println("start to connect...");
			ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8888).sync();

			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workers.shutdownGracefully();
		}
	}
}

class ClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel is activated");

		ChannelFuture future = ctx.writeAndFlush(Unpooled.copiedBuffer("HelloNetty".getBytes()));
		future.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				System.out.println("msg send");
			}
		});
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			ByteBuf buf = (ByteBuf) msg;
			System.out.println(buf.toString());
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}
}