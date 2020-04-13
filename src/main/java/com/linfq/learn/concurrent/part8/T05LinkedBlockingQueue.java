package com.linfq.learn.concurrent.part8;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * T05LinkedBlockingQueue.
 * 阻塞式队列
 *
 * 	BlockingQueue
 * 		LinkedBQ
 * 		ArrayBQ
 * 		TransferQueue
 * 		SynchronusQueue
 * 	DelayQueue执行定时任务
 *
 * @author linfq
 * @date 2020/4/13 22:08
 */
public class T05LinkedBlockingQueue {

	static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

	static Random random = new Random();

	public static void main(String[] args) {
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					// put如果满了，就会等待
					queue.put("a" + i);
					TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "p1").start();

		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				for (;;) {
					try {
						// take如果空了，就会等待
						System.out.println(Thread.currentThread().getName() + " take -" + queue.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "c" + i).start();
		}
	}

}
