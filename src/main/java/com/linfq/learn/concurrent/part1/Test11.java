package com.linfq.learn.concurrent.part1;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字，使一个变量在多个线程间可见.
 * A B线程都使用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值。
 *
 * 在下面的代码中，running是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都去读取堆内存，
 * 这样，当主线程修改running的值后，t1线程感知不到，所以不会停止运行
 *
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值
 *
 * 可以阅读这边文章进行更深入的理解
 * https://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 *
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 *
 * @author linfq
 * @date 2020/3/20 21:39
 */
public class Test11 {

	/**
	 * 对比一下有无volatile的情况下，整个程序运行结果的区别
	 */
	/*volatile*/ boolean running = true;

	void m() {
		System.out.println("m start");
		while (running) {

		}
		System.out.println("m end!");
	}

	public static void main(String[] args) {
		Test11 test11 = new Test11();

		new Thread(test11::m, "t1").start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		test11.running = false;
	}


}
