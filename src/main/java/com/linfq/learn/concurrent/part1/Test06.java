package com.linfq.learn.concurrent.part1;

/**
 * 同步和非同步方法是否可以同时调用？
 *
 * @author linfq
 * @date 2019/8/18 22:11
 */
public class Test06 {

	public synchronized void m1() {
		System.out.println(Thread.currentThread().getName() + " m1 start...");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " m1 end");
	}

	public void m2() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " m2");
	}

	public static void main(String[] args) {
		Test06 test06 = new Test06();

		new Thread(() -> test06.m1(), "t1").start();
//		new Thread(() -> test06.m2(), "t2").start();
		new Thread(test06::m2, "t1").start();
	}
}
