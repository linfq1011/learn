package com.linfq.learn.concurrent.part9;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池原理.
 *
 * @author linfq
 * @date 2020/4/16 21:53
 */
public class T13ThreadPoolExecutor {

	public static void main(String[] args) {
		new ThreadPoolExecutor(
				4,
				8,
				60,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>());
	}
}
