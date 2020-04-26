package com.linfq.learn.io.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * T1Server.
 * 阻塞性IO性能非常低
 *
 * @author linfq
 * @date 2020/4/25 9:20
 */
public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket();
		server.bind(new InetSocketAddress("127.0.0.1", 8888));

		while (true) {
			// 阻塞方法
			Socket socket = server.accept();
			new Thread(() -> {
				handle(socket);
			}).start();
		}
	}

	static void handle(Socket socket) {
		try {
			byte[] bytes = new byte[1024];
			// read阻塞
			int len = socket.getInputStream().read(bytes);
			System.out.println(new String(bytes, 0, len));

			// write阻塞
			socket.getOutputStream().write(bytes, 0, len);
			socket.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
