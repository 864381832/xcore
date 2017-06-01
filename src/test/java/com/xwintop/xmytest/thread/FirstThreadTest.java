package com.xwintop.xmytest.thread;

import org.junit.Test;

public class FirstThreadTest extends Thread {
	int i = 0;

	// 重写run方法，run方法的方法体就是现场执行体
	public void run() {
		for (; i < 100; i++) {
			System.out.println(getName() + "  " + i);

		}
	}

	@Test
	public void test() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + "  : " + i);
			if (i == 20) {
				new FirstThreadTest().start();
				new FirstThreadTest().start();
			}
		}
	}

}
