package com.linfq.learn.concurrent.part4;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 *
 * @author linfq
 * @date 2020/4/6 21:15
 */
public class MyContainer1<T> {

	final private LinkedList<T> list = new LinkedList<>();
	/**
	 * 做多10个元素
	 */
	final private int MAX = 10;
	private int cout = 0;

	public synchronized void put(T t) {
		// 想想为什么用while而不是用if？
		while (list.size() == MAX) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		list.add(t);
		++cout;
		// 通知消费者线程进行消费
		this.notifyAll();
	}

	public synchronized T get() {
		T t = null;
		while (list.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = list.removeFirst();
		cout--;
		// 通知生产者进行生产
		this.notifyAll();
		return t;
	}

	public static void main(String[] args) {
		MyContainer1<String> c = new MyContainer1<>();
		// 启动消费者
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int j = 0; j < 5; j++) {
					System.out.println(c.get());
				}
			}, "c" + i).start();
		}

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 启动生产者线程
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				for (int j = 0; j < 25; j++) {
					c.put(Thread.currentThread().getName() + " " + j);
				}
			}, "p" + i).start();
		}
	}

}
