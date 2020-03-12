package com.linfq.learn.jvm;

/**
 * T05：线程栈大小，线程并发数量，线程递归调用可以比较深.
 * -Xss128k
 *
 * @author linfq
 * @date 2020/3/11 22:31
 */
public class T05 {
	static int count = 0;

	static void r() {
		count++;
		T05.r();
	}

	public static void main(String[] args) {
		try {
			T05.r();
		} catch (Throwable t) {
			System.out.println(count);
			// StackOverflow
			t.printStackTrace();
		}
	}

}
