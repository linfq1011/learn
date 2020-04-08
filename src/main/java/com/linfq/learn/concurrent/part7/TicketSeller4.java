package com.linfq.learn.concurrent.part7;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 *
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 * 就像这个程序，判断size和进行remove必须是一整个的原子操作
 *
 * 使用ConcurrentQueue提高并发性
 *
 * 同步容器类
 *
 * 1：Vector Hashtable ：早期使用synchronized实现
 * 2：ArrayList HashSet ：未考虑多线程安全（未实现同步）
 * 3：HashSet vs Hashtable StringBuilder vs StringBuffer
 * 4：Collections.synchronized***工厂方法使用的也是synchronized
 *
 * 使用早期的同步容器以及Collections.synchronized***方法的不足之处，请阅读：
 * http://blog.csdn.net/itm_hadf/article/details/7506529
 *
 * 使用新的并发容器
 * http://xuganggogo.iteye.com/blog/321630
 *
 * @author linfq
 * @date 2020/4/8 21:45
 */
public class TicketSeller4 {

	static Queue<String> tickets = new ConcurrentLinkedDeque<>();

	static {
		for (int i = 0; i < 10000; i++) {
			tickets.add("票编号：" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (true) {
					String s = tickets.poll();
					if (s == null) {
						break;
					} else {
						System.out.println("销售了--" + s);
					}
				}
			}).start();
		}
	}
}
