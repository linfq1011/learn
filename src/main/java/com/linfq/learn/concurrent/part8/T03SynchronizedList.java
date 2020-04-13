package com.linfq.learn.concurrent.part8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * T03SynchronizedList.
 *
 * @author linfq
 * @date 2020/4/13 21:56
 */
public class T03SynchronizedList {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		List<String> synchronizedList = Collections.synchronizedList(list);
	}
}
