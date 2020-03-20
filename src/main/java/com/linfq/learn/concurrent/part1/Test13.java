package com.linfq.learn.concurrent.part1;

import java.util.ArrayList;
import java.util.List;

/**
 * 对比Test12，可以用synchronized解决，synchronized可以保证可见性和原子性，volatile只能保证可见性.
 *
 * @author linfq
 * @date 2020/3/20 22:19
 */
public class Test13 {

	/*volatile*/ int count = 0;

	synchronized void m() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}

	public static void main(String[] args) {
		Test13 test13 = new Test13();

		List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(test13::m, "thread-" + i));
		}

		threads.forEach(t -> t.start());

		threads.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(test13.count);
	}
}
