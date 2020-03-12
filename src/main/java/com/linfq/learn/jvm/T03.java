package com.linfq.learn.jvm;

/**
 * T03.
 *
 * @author linfq
 * @date 2020/3/11 22:05
 */
public class T03 {

	public static void main(String[] args) {

		T03.printMemoryInfo();

		byte[] b = new byte[1024 * 1024];
		System.out.println("---------");

		T03.printMemoryInfo();
	}


	static void printMemoryInfo() {
		System.out.println("total:" + Runtime.getRuntime().totalMemory());
		System.out.println("free :" + Runtime.getRuntime().freeMemory());
	}

}
