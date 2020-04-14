package com.linfq.learn.concurrent.part9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的概念.
 * 启动和销毁线程需要使用大量资源，线程尽量重用
 *
 * @author linfq
 * @date 2020/4/14 22:45
 */
public class T05ThreadPool {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);

		for (int i = 0; i < 6; i++) {
			executorService.execute(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		// 状态：Running
		System.out.println(executorService);

		executorService.shutdown();
		// 是否全部执行完
		System.out.println(executorService.isTerminated());
		// 是否关闭，不一定执行完
		System.out.println(executorService.isShutdown());
		// 状态：Shutting down
		System.out.println(executorService);

		TimeUnit.SECONDS.sleep(5);
		System.out.println(executorService.isTerminated());
		System.out.println(executorService.isShutdown());
		// // 状态：Terminated
		System.out.println(executorService);
	}
}
