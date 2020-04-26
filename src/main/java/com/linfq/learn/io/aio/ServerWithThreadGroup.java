package com.linfq.learn.io.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * aio多线程模式.
 *
 * @author linfq
 * @date 2020/4/26 21:45
 */
public class ServerWithThreadGroup {

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);

		// 中文测试
		final AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open(threadGroup)
				.bind(new InetSocketAddress(8888));

		serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
			@Override
			public void completed(AsynchronousSocketChannel client, Object attachment) {
				serverChannel.accept(null, this);
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
				} catch (Exception e) {
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
