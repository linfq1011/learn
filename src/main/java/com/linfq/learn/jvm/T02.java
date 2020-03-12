package com.linfq.learn.jvm;

/**
 * T02：测试线程本地内存.
 * -XX:+DoEscapeAnalysis -XX:+EliminateAllocations -XX:+UseTLAB -XX:+PrintGCDetails
 *
 * @author linfq
 * @date 2020/3/11 21:50
 */
public class T02 {

	public static void main(String[] args) {
		byte[] b = new byte[1024];
	}
}
