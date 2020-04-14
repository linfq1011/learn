package com.linfq.learn.concurrent.part9;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 认识ExecutorService，阅读API文档
 * 认识submit方法，提交任务
 * 扩展了executor方法，具有一个返回值
 *
 * @author linfq
 * @date 2020/4/14 22:26
 */
public abstract class T02ExecutorService implements ExecutorService {


	@Override
	public <T> Future<T> submit(Callable<T> task) {
		return null;
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		return null;
	}

	@Override
	public Future<?> submit(Runnable task) {
		return null;
	}

	@Override
	public void execute(Runnable command) {

	}
}
