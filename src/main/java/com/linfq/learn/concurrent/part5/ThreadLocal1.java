package com.linfq.learn.concurrent.part5;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量.
 *
 * @author linfq
 * @date 2020/4/7 21:58
 */
public class ThreadLocal1 {

	/**
	 * 不写volatile，有可能发生问题
	 */
	volatile static Person p = new Person();

	public static void main(String[] args) {
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(p.name);
		}).start();

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			p.name = "lisi";
		}).start();
	}
}

class Person {
	String name = "zhangsan";
}
