package com.linfq.learn.concurrent.part8;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * http://blog.csdn.net/sunxianghuang/article/details/52221913
 * http://www.educity.cn/java/498061.html
 * 阅读concurrentskiplistmap
 *
 * 1：对于map/set的选择使用
 * HashMap
 * TreeMap
 * LinkedHashMap
 *
 * Hashtable
 * Collections.sychronizedXXX
 *
 * ConcurrentHashMap
 * ConcurrentSkipListMap
 *
 * @author linfq
 * @date 2020/4/8 22:11
 */
public class T01ConcurrentMap {

	public static void main(String[] args) {
//		Map<String, String> map = new Hashtable<>();
//		Map<String, String> map = new HashMap<>();
//		Map<String, String> map = new ConcurrentHashMap<>();
		/**
		 * 跳表，高并发并且排序
		 */
		Map<String, String> map = new ConcurrentSkipListMap<>();


		Random r = new Random();
		Thread[] threads = new Thread[100];
		CountDownLatch latch = new CountDownLatch(threads.length);

		long start = System.currentTimeMillis();

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 10000; j++) {
					map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
				}
				latch.countDown();
			});
		}

		Arrays.asList(threads).forEach(Thread::start);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println(end -start);
	}


}
