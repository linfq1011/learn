package com.linfq.learn.concurrent.part8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * T06ArrayBlockingQueue.
 * 有界队列
 *
 * @author linfq
 * @date 2020/4/13 22:18
 */
public class T06ArrayBlockingQueue {

	static BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			queue.put("a" + i);
		}

		// 如果满了，会阻塞
		queue.put("aaa");
		// 如果满了，会抛出异常
		queue.add("aaa");
		// 如果满了，不会抛出异常，但是添加失败，会有boolean返回值
		queue.offer("aaa");
		// 等待1秒钟，如果无法加入，那就不再添加
		queue.offer("aaa", 1, TimeUnit.SECONDS);

		System.out.println(queue);
	}
}
