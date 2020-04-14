package com.linfq.learn.concurrent.part9;

import java.util.concurrent.Executor;

/**
 * 认识Executor，用来执行某些任务
 *
 * @author linfq
 * @date 2020/4/14 21:57
 */
public class T01MyExecutor implements Executor {

	public static void main(String[] args) {
		new T01MyExecutor().execute(() -> System.out.println("hello executor"));
	}

	@Override
	public void execute(Runnable command) {
//		new Thread(command).run();
		command.run();
	}
}
