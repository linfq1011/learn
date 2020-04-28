package com.linfq.learn.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * HelloNetty.
 *
 * @author linfq
 * @date 2020/4/28 21:45
 */
public class HelloNetty {

	public static void main(String[] args) {
		new NettyServer(8888).serverStart();
	}
}

/**
 * netty服务端.
 */
class NettyServer {

	int port;

	public NettyServer(int port) {
		this.port = port;
	}

	public void serverStart() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap serverBootstrap = new ServerBootstrap();

		serverBootstrap.group(bossGroup, workerGroup)
				// 通道类型
				.channel(NioServerSocketChannel.class)
				// 当客户端接入执行监听器
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new Handler());
					}
				});

		try {
			ChannelFuture future = serverBootstrap.bind(port).sync();

			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
}

/**
 * 处理器
 */
class Handler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		super.channelRead(ctx, msg);
		System.out.println("server: channel read");
		ByteBuf buf = (ByteBuf) msg;

		System.out.println(buf.toString(CharsetUtil.UTF_8));

		ctx.writeAndFlush(msg);

		ctx.close();

//		buf.release();
	}

	/**
	 * 处理异常
	 *
	 * @param ctx
	 * @param cause
	 * @throws Exception
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
		ctx.close();
	}
}