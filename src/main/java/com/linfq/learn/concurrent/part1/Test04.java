package com.linfq.learn.concurrent.part1;

/**
 * synchronized关键字.
 * 对某个对象加锁
 *
 * @author linfq
 * @date 2019/8/18 21:54
 */
public class Test04 {

	private static int count = 10;

	/**
	 * 等同于在方法的代码执行时要synchronized(Test04.class).
	 */
	public synchronized static void m() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count " + count);
	}

	public static void mm() {
		// 考虑一下这里写synchronized(this)是否可以？
		synchronized (Test04.class) {
			count--;
		}
	}
}
