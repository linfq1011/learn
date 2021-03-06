package com.linfq.learn.concurrent.part1;

/**
 * synchronized关键字.
 * 对某个对象加锁
 *
 * @author linfq
 * @date 2019/8/18 21:45
 */
public class Test01 {

	private int count = 10;

	private final Object o = new Object();

	public void m() {
		// 任何线程要执行下面的代码，必须先拿到o的锁
		synchronized (o) {
			count--;
			System.out.println(Thread.currentThread().getName() + " count " + count);
		}
	}
}
