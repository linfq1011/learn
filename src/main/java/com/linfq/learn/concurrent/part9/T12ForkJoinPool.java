package com.linfq.learn.concurrent.part9;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * fork:分叉，join：合并
 *
 * @author linfq
 * @date 2020/4/16 21:16
 */
public class T12ForkJoinPool {

	static int[] nums = new int[1000000];
	static final int MAX_NUM = 50000;
	static Random r = new Random();

	static {
		for (int i = 0; i < nums.length; i++) {
			nums[i] = r.nextInt(100);
		}
		System.out.println(Arrays.stream(nums).sum());
	}

	/**
	 * RecursiveAction没有返回值
	 */
	/*static class AddTask extends RecursiveAction {
		int start, end;

		AddTask(int s, int e) {
			start = s;
			end = e;
		}

		@Override
		public void compute() {
			// 如果计算数量小于5万，不切分线程
			if (end - start <= MAX_NUM) {
				long sum = 0L;
				for (int i = start; i < end; i++) {
					sum += nums[i];
				}
				System.out.println("from:" + start + " to:" + end + " = " + sum);
			} else {
				int middle = start + (end - start) / 2;

				AddTask subTask1 = new AddTask(start, middle);
				AddTask subTask2 = new AddTask(middle, end);
				subTask1.fork();
				subTask2.fork();
			}

		}
	}*/

	/**
	 * RecursiveTask有返回值
	 */
	static class AddTask extends RecursiveTask<Long> {
		int start, end;

		AddTask(int s, int e) {
			start = s;
			end = e;
		}

		@Override
		public Long compute() {
			// 如果计算数量小于5万，不切分线程
			if (end - start <= MAX_NUM) {
				long sum = 0L;
				for (int i = start; i < end; i++) {
					sum += nums[i];
				}
				return sum;
			} else {
				int middle = start + (end - start) / 2;

				AddTask subTask1 = new AddTask(start, middle);
				AddTask subTask2 = new AddTask(middle, end);
				subTask1.fork();
				subTask2.fork();

				return subTask1.join() + subTask2.join();
			}

		}
	}


	public static void main(String[] args) throws IOException {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		AddTask task = new AddTask(0, nums.length);
		forkJoinPool.execute(task);
//		System.in.read();

		long result = task.join();
		System.out.println(result);

	}

}
