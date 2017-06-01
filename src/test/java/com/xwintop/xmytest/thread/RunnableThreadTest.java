package com.xwintop.xmytest.thread;

import org.junit.Test;

public class RunnableThreadTest implements Runnable {

	private int i;

	public void run() {
		for (i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
	}

	@Test
	public void test() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
			if (i == 20) {
				RunnableThreadTest rtt = new RunnableThreadTest();
				new Thread(rtt, "新线程1").start();
				new Thread(rtt, "新线程2").start();
			}
		}

	}

}
