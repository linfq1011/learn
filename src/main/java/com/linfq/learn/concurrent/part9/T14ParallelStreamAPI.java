package com.linfq.learn.concurrent.part9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 并行流式结算.
 *
 * @author linfq
 * @date 2020/4/16 21:59
 */
public class T14ParallelStreamAPI {

	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < 10000; i++) {
			nums.add(1000000 + r.nextInt(1000000));
		}

//		System.out.println(nums);

		long start = System.currentTimeMillis();
		nums.forEach(T14ParallelStreamAPI::isPrime);
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		// 使用parallel stream api
		start = System.currentTimeMillis();
		nums.parallelStream().forEach(T14ParallelStreamAPI::isPrime);
		end = System.currentTimeMillis();

		System.out.println(end - start);
	}

	static boolean isPrime(int num) {
		for (int i = 2; i < num / 2; i++) {
			if (num % 1 == 0) {
				return false;
			}
		}
		return true;
	}
}
