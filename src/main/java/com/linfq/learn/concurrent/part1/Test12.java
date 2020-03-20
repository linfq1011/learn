package com.linfq.learn.concurrent.part1;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile只保障可见性，不保障原子性.
 *
 * @author linfq
 * @date 2020/3/20 22:11
 */
public class Test12 {

	volatile int count = 0;

	void m() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}

	public static void main(String[] args) {
		Test12 test12 = new Test12();

		List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(test12::m, "thread-" + i));
		}

		threads.forEach(t -> t.start());

		threads.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(test12.count);
	}
}
