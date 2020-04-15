package com.linfq.learn.concurrent.part9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 并行计算.
 * 计算从1-40万之间所有的质数
 *
 * @author linfq
 * @date 2020/4/15 22:37
 */
public class T07ParallelComputing {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// 直接计算
		long start = System.currentTimeMillis();
		List<Integer> result = T07ParallelComputing.getPrime(1, 400000);
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		// 并行结算
		final int cpuCoreNum = 4;
		ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);

		// 不建议平均分配，应该递减分配
		MyTask t1 = new MyTask(1, 100000);
		MyTask t2 = new MyTask(100001, 200000);
		MyTask t3 = new MyTask(200001, 300000);
		MyTask t4 = new MyTask(300001, 400000);

		Future<List<Integer>> f1 = service.submit(t1);
		Future<List<Integer>> f2 = service.submit(t2);
		Future<List<Integer>> f3 = service.submit(t3);
		Future<List<Integer>> f4 = service.submit(t4);

		start = System.currentTimeMillis();
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	static class MyTask implements Callable<List<Integer>> {
		int startPos, endPos;

		MyTask(int s, int e) {
			this.startPos = s;
			this.endPos = e;
		}

		@Override
		public List<Integer> call() throws Exception {
			return T07ParallelComputing.getPrime(startPos, endPos);
		}
	}

	/***
	 * 是否是质数
	 *
	 * @param num
	 * @return
	 */
	static boolean isPrime(int num) {
		for (int i = 2; i <= num / 2 ; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取指定范围质数.
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	static List<Integer> getPrime(int start, int end) {
		List<Integer> result = new ArrayList<>();
		for (int i = start; i <= end; i++) {
			if (T07ParallelComputing.isPrime(i)) {
				result.add(i);
			}
		}
		return result;
	}

}
