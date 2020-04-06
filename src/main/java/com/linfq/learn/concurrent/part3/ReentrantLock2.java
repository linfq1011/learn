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
 * @author linfq
 * @date 2020/4/6 20:18
 */
public class ReentrantLock2 {

	Lock lock = new ReentrantLock();

	void m1() {
		// synchronized(this)
		lock.lock();
		try {
			for (int i = 0; i < 10; i++) {
				TimeUnit.SECONDS.sleep(1);

				System.out.println(i);
			}
		} catch (InterruptedException e) {
				e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	synchronized void m2() {
		lock.lock();
		System.out.println("m2...");
		lock.unlock();
	}

	public static void main(String[] args) {
		ReentrantLock2 r2 = new ReentrantLock2();
		new Thread(r2::m1).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r2::m2).start();
	}
}
