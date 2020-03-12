package com.linfq.learn.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * T04:内存溢出.
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/jvm.dump -XX:+PrintGCDetails -Xms10M -Xmx10M
 * 小技巧：调优一般把最小最大内存设置一样值，减少申请内存时触发gc等算法
 *
 * @author linfq
 * @date 2020/3/11 22:14
 */
public class T04 {

	public static void main(String[] args) {
		List<Object> list = new ArrayList<>();

		for (int i = 0; i < 10000000; i++) {
			list.add(new byte[1024 * 1024]);
		}
	}
}
