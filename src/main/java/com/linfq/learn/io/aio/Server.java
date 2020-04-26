package com.linfq.learn.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * asynchronous io.
 * aio单线程模型
 * aio和nio在linux内核下都是使用epoll实现
 *
 * @author linfq
 * @date 2020/4/25 11:19
 */
public class Server {

	public static void main(String[] args) throws Exception {
		final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open()
				.bind(new InetSocketAddress(8888));

		// 观察者模式
		serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
			@Override
			public void completed(AsynchronousSocketChannel client, Object attachment) {
				serverSocketChannel.accept(null, this);
				try {
					System.out.println(client.getRemoteAddress());
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					client.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
						@Override
						public void completed(Integer result, ByteBuffer attachment) {
							attachment.flip();
							System.out.println(new String(attachment.array(), 0, result));
							client.write(ByteBuffer.wrap("HelloClient".getBytes()));
						}

						@Override
						public void failed(Throwable exc, ByteBuffer attachment) {
							exc.printStackTrace();
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void failed(Throwable exc, Object attachment) {
				exc.printStackTrace();

			}
		});

		// accept是非阻塞方法，可以使用CountdownLatch
		while (true) {
			Thread.sleep(1000);
		}
	}
}
