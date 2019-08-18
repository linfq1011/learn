package com.linfq.learn.concurrent.part1;

/**
 * synchronized关键字.
 * 对某个对象加锁
 *
 * @author linfq
 * @date 2019/8/18 21:50
 */
public class Test03 {

	private int count = 10;

	/**
	 * 等同于在方法的代码执行时要synchronized(this).
	 */
	public synchronized void m() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count " + count);
	}
}
