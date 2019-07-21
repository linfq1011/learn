package com.linfq.learn.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 实现客户端发送一个请求，服务器会返回 hello netty.
 *
 * @author linfq
 * @date 2019/7/21 7:45
 */
public class HellloServer {

	public static void main(String[] args) throws InterruptedException {
		// 定义一对线程组
		// 主线程组，用于接收客户端的连接，但是不作任何处理，跟老板一样，不做事
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 从线程组，老板线程组会把任务丢给他，让手下线程组去做任务
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			// netty服务器的创建，ServerBootstrap是一个启动类
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			// 设置主从线程组
			// 设置nio的双向通道
			// 子处理器，用于处理workerGroup
			serverBootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new HelloServerInitializer());

			// 启动server，并且设置8088为启动的端口号，同事启动方式为同步
			ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();

			// 监听关闭的channel，设置位同步方式
			channelFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}
}
