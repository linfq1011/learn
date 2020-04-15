package com.linfq.learn.concurrent.part9;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时执行线程池.
 *
 * @author linfq
 * @date 2020/4/15 23:17
 */
public class T10ScheduledPool {

	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
		// 固定频率执行任务
		service.scheduleAtFixedRate(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}, 0, 500, TimeUnit.MILLISECONDS);
	}
}
