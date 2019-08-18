package com.linfq.learn.concurrent.part1;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 容易产生脏读问题（dirtyRead）
 *
 * @author linfq
 * @date 2019/8/18 22:32
 */
public class Test07 {
	String name;
	double balance;

	public synchronized void set(String name, double balance) {
		this.name = name;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.balance = balance;
	}

	public /*synchronized*/ double getBalance(String name) {
		return this.balance;
	}


	public static void main(String[] args) {
		Test07 test07 = new Test07();
		new Thread(() -> test07.set("zhangsan", 100.0)).start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(test07.getBalance("zhangsan"));

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(test07.getBalance("zhangsan"));
	}
}
