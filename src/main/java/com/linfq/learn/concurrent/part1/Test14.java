package com.linfq.learn.concurrent.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Test14.
 *
 * @author linfq
 * @date 2020/3/20 22:23
 */
public class Test14 {

	AtomicInteger count = new AtomicInteger(0);

	void m() {
		for (int i = 0; i < 10000; i++) {
			// count++
			count.incrementAndGet();
		}
	}


	public static void main(String[] args) {
		Test14 test14 = new Test14();

		List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(test14::m, "thread-" + i));
		}

		threads.forEach(t -> t.start());

		threads.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(test14.count);
	}
}
