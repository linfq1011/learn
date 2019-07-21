package com.linfq.learn.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * WebSocketServer.
 *
 * @author linfq
 * @date 2019/7/21 9:36
 */
public class WebSocketServer {

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup mainGroup = new NioEventLoopGroup();
		EventLoopGroup subGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap server = new ServerBootstrap();
			server.group(mainGroup, subGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new WebSocketServerInitializer());

			ChannelFuture future = server.bind(8088).sync();
			future.channel().closeFuture().sync();
		} finally {
			mainGroup.shutdownGracefully();
			subGroup.shutdownGracefully();
		}
	}
}
