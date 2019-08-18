package com.linfq.learn.concurrent.part1;

/**
 * 分析一下这个程序的输出.
 * 线程重入
 * synchronized是原子操作.
 *
 * @author linfq
 * @date 2019/8/18 21:59
 */
public class Test05 implements Runnable {

	private int count = 10;

	@Override
	public /*synchronized*/ void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count " + count);
	}

	public static void main(String[] args) {
		Test05 test05 = new Test05();
		for (int i = 0; i < 5; i++) {
			new Thread(test05, "THREAD" + i).start();
		}
	}
}
