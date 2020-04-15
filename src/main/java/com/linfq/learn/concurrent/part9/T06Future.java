package com.linfq.learn.concurrent.part9;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Future.
 * 未来的结果
 *
 * @author linfq
 * @date 2020/4/15 22:28
 */
public class T06Future {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureTask<Integer> task = new FutureTask<>(() -> {
			TimeUnit.MILLISECONDS.sleep(500);
			return 10000;
		});

		new Thread(task).start();

		// 阻塞
		System.out.println(task.get());

		//***********************************
		ExecutorService service = Executors.newFixedThreadPool(5);
		Future<Integer> future = service.submit(() -> {
			TimeUnit.MILLISECONDS.sleep(500);
			return 1;
		});
//		System.out.println(future.get());
		System.out.println(future.isDone());
	}

}
