package com.linfq.learn.concurrent.part8;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentQueue.
 * 并发（加锁）队列
 *
 * Queue
 * 	CocurrentLinkedQueue //concurrentArrayQueue
 *
 * @author linfq
 * @date 2020/4/13 21:58
 */
public class T04ConcurrentQueue {

	public static void main(String[] args) {
		// 无界队列
		Queue<String> queue = new ConcurrentLinkedQueue<>();

		for (int i = 0; i < 10; i++) {
			// add有界队列会抛出异常
			queue.offer("a" + i);
		}

		System.out.println(queue);

		System.out.println(queue.size());

		// 取出并删除
		System.out.println(queue.poll());
		System.out.println(queue.size());

		// 取出不删除
		System.out.println(queue.peek());
		System.out.println(queue.size());

		// 双端队列Deque
	}
}
