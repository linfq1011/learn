package com.linfq.learn.concurrent.part9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单个线程池.
 * 保证线程顺序执行
 *
 * @author linfq
 * @date 2020/4/15 23:13
 */
public class T09SingleThreadPool {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			final int j = i;
			service.execute(() -> {
				System.out.println(j + " " + Thread.currentThread().getName());
			});
		}
	}
}
