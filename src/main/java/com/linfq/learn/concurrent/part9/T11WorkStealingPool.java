package com.linfq.learn.concurrent.part9;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 工作窃取线程池.
 * 每个线程维护一个队列，当一个线程队列空闲会主动拉取其他线程队列.
 * 线程都是守护线程
 *
 * @author linfq
 * @date 2020/4/16 21:01
 */
public class T11WorkStealingPool {

	public static void main(String[] args) throws IOException {
		// 根据CPU核心数，默认启动多少个线程
		ExecutorService service = Executors.newWorkStealingPool();
		System.out.println(Runtime.getRuntime().availableProcessors());

		service.execute(new R(1000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));

		// 由于产生的是精灵线程（守护线程），主线程不阻塞的话，看不到输出结果
		System.in.read();

	}

	static class R implements Runnable {

		int time;

		R(int time) {
			this.time = time;
		}

		@Override
		public void run() {
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(time + " " + Thread.currentThread().getName());
		}
	}

}
