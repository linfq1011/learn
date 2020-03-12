package com.linfq.learn.jvm;

/**
 * T01.
 * -XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:-UseTLAB -XX:+PrintGC
 * -XX:+DoEscapeAnalysis -XX:+EliminateAllocations -XX:+UseTLAB -XX:+PrintGC
 * 关闭逃逸分析，关闭标量替换，关闭线程本地内存，打开gc信息
 * @author linfq
 * @date 2020/3/11 21:13
 */
public class T01 {

	class User {
		int id;
		String name;

		User(int id, String name) {
			this.id = id;
			this.name = name;
		}
	}

	void alloc(int i) {
		new User(i, "name" + i);
	}

	public static void main(String[] args) {
		T01 t01 = new T01();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			t01.alloc(i);
		}
		long end = System.currentTimeMillis();
		System.out.println(end -start);
	}
}
