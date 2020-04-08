package com.linfq.learn.concurrent.part3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock用来替代synchronized.
 * 本例中由于m1锁定this，只要m1执行完毕的时候，m2才能执行
 * 这里是复习synchronized最原始的语义.
 *
 * 使用reentrantlock可以完成同样的功能
 * 需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 * 使用svn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 *
 * 使用reentrantlock可以进行“尝试锁定”trylock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 *
 * 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出相应，
 * 在一个线程等待锁的过程中，可以被打断
 *
 * @author linfq
 * @date 2020/4/6 20:36
 */
public class ReentrantLock4 {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();

		Thread t1 = new Thread(() -> {
			try {
				lock.lock();
				System.out.println("t1 start");
				TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
				System.out.println("t2 end");
			} catch (InterruptedException e) {
				System.out.println("t1 interrupted!");
			} finally {
				lock.unlock();
			}
		});
		t1.start();

		Thread t2 = new Thread(() -> {
			try {
				// 无法停止，一直等待
//				 lock.lock();
				// 可以对interrupt()方法做出相应
				lock.lockInterruptibly();
				System.out.println("t2 start");
				TimeUnit.SECONDS.sleep(5);
				System.out.println("t2 end");
			} catch (InterruptedException e) {
				System.out.println("t2 interrupted!");
			}
		});
		t2.start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 打断线程2的等待
		t2.interrupt();
	}

}