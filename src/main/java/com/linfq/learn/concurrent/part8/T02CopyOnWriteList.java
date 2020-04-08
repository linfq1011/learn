package com.linfq.learn.concurrent.part8;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制容器 copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 *
 * @author linfq
 * @date 2020/4/8 22:29
 */
public class T02CopyOnWriteList {

	public static void main(String[] args) {
		List<String> list =
//				new ArrayList<>(); // 这个会出现并发问题
//				new Vector<>();
				new CopyOnWriteArrayList<>();

		Random r = new Random();
		Thread[] threads = new Thread[100];

		for (int i = 0; i < threads.length; i++) {
			Runnable runnable = () -> {
				for (int j = 0; j < 10000; j++) {
					list.add("a" + r.nextInt(10000));
				}
			};
			threads[i] = new Thread(runnable);
		}

		runAndComputeTime(threads);

		System.out.println(list.size());
	}

	static void runAndComputeTime(Thread[] threads) {
		long start = System.currentTimeMillis();
		Arrays.asList(threads).forEach(Thread::start);
		Arrays.asList(threads).forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
