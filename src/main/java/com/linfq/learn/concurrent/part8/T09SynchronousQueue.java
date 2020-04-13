package com.linfq.learn.concurrent.part8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * T09SynchronousQueue.
 *
 * 一种特殊的TransferQueue，队列容量必须为0
 *
 * @author linfq
 * @date 2020/4/13 22:51
 */
public class T09SynchronousQueue {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue = new SynchronousQueue<>();

		new Thread(() -> {
			try {
				System.out.println(queue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		// 阻塞，等待消费者消费，底层使用transfer，不能放入队列，因为队列必须为0
		queue.put("aaa");
		// 报错
//		queue.add("aaa");
		System.out.println(queue.size());

	}
}
