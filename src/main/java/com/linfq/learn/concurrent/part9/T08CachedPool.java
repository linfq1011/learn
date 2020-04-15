package com.linfq.learn.concurrent.part9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 弹性线程池.
 *
 * @author linfq
 * @date 2020/4/15 23:06
 */
public class T08CachedPool {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println(service);

		for (int i = 0; i < 2; i++) {
			service.execute(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}

		System.out.println(service);

		// 如果空闲超过80秒，会销毁
		TimeUnit.SECONDS.sleep(80);

		System.out.println(service);
	}
}
