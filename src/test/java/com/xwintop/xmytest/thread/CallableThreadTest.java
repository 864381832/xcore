package com.xwintop.xmytest.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;

public class CallableThreadTest implements Callable<Integer> {

	@Test
	public void test() {
		CallableThreadTest ctt = new CallableThreadTest();
		FutureTask<Integer> ft = new FutureTask<>(ctt);
//		for (int i = 0; i < 100; i++) {
//			System.out.println(Thread.currentThread().getName() + " 的循环变量i的值" + i);
//			if (i == 20) {
//				new Thread(ft, "有返回值的线程").start();
//			}
//		}
		try {
			System.out.println(System.nanoTime());
			System.out.println(System.nanoTime());
			System.out.println(System.currentTimeMillis());
			System.out.println(System.currentTimeMillis());
			long start = System.currentTimeMillis();
			new Thread(ft, "有返回值的线程").start();
			System.out.println(Thread.currentThread().getName() + "正在等待");
			Thread.sleep(5000);
			System.out.println("子线程的返回值：" + ft.get());
			
			System.out.println("耗时"+(System.currentTimeMillis()-start));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Integer call() throws Exception {
		int i = 0;
		System.out.println(Thread.currentThread().getName() + "正在等待");
		Thread.sleep(5000);
//		for (; i < 100; i++) {
//			System.out.println(Thread.currentThread().getName() + " " + i);
//		}
		return ++i;
	}

}
