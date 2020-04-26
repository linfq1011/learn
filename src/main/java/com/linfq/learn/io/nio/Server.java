package com.linfq.learn.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Server.
 * non-block io
 * nio单线程模型
 *
 * @author linfq
 * @date 2020/4/25 9:50
 */
public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.socket().bind(new InetSocketAddress("127.0.0.1", 8888));
		// 非阻塞
		channel.configureBlocking(false);

		System.out.println("server started, listening on" + channel.getLocalAddress());
		Selector selector = Selector.open();
		channel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			// 阻塞方法
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> it = keys.iterator();
			while (it.hasNext()) {
				SelectionKey key = it.next();
				it.remove();
				handler(key);
			}
		}

	}

	private static void handler(SelectionKey key) {
		if (key.isAcceptable()) {
			try {
				ServerSocketChannel channel = (ServerSocketChannel) key.channel();
				SocketChannel socketChannel = channel.accept();
				channel.configureBlocking(false);


				socketChannel.register(key.selector(), SelectionKey.OP_READ);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (key.isReadable()) {
			SocketChannel socketChannel = null;
			try {
				socketChannel = (SocketChannel) key.channel();
				ByteBuffer buffer = ByteBuffer.allocate(512);
				buffer.clear();
				int len = socketChannel.read(buffer);

				if(len != -1) {
					System.out.println(new String(buffer.array(), 0, len));
				}

				ByteBuffer bufferToWrite = ByteBuffer.wrap("HelloClient".getBytes());
				socketChannel.write(bufferToWrite);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (socketChannel != null) {
					try {
						socketChannel.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
