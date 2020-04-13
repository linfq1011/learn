package com.linfq.learn.concurrent.part8;

import java.util.concurrent.LinkedTransferQueue;

/**
 * T08TransferQueue.
 * 如果有消费者等待，直接将消息发送给消费者，不经过队列。
 * 用于实时队列处理（netty）
 *
 * @author linfq
 * @date 2020/4/13 22:45
 */
public class T08TransferQueue {

	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

		// 启动消费者
		new Thread(() -> {
			try {
				System.out.println(queue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		// 如果消费者繁忙，则阻塞
		queue.transfer("aaa");

		// 由于transfer会阻塞，如果找不到消费者则执行不到此处
//		new Thread(() -> {
//			try {
//				System.out.println(queue.take());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}).start();
	}
}
