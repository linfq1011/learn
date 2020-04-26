package com.linfq.learn.io.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * Client.
 *
 * @author linfq
 * @date 2020/4/25 9:30
 */
public class Client {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("127.0.0.1", 8888);
		socket.getOutputStream().write("HelloServer".getBytes());
		socket.getOutputStream().flush();
//		socket.getOutputStream().close();
		System.out.println("write over, waiting for msg back...");
		byte[] bytes = new byte[1024];
		int len = socket.getInputStream().read(bytes);
		System.out.println(new String(bytes, 0, len));
		socket.close();
	}
}
