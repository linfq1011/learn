package com.linfq.learn.concurrent.part1;

import java.util.concurrent.TimeUnit;

/**
 * 子类同步方法调用父类同步方法
 *
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁.
 * 也就是说synchronized获得的锁是可重入的
 * 这里是继承中有可能发生的情形，子类调用父类的同步方法
 *
 * @author linfq
 * @date 2020/3/19 21:52
 */
public class Test09 {

	synchronized void m() {
		System.out.println("m start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m end");
	}

	public static void main(String[] args) {
		new Test09Child().m();
	}
}

class Test09Child extends Test09 {

	@Override
	synchronized  void m() {
		System.out.println("child m start");
		super.m();
	}
}