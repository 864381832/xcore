package com.xwintop.xcore;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestClass {
	private static int i = 0;
	{
		i++;
		System.out.println("1:" + i);
	}
	static {
		i++;
		System.out.println("2:" + i);
	}

	public TestClass() {
		i++;
		System.out.println("3:" + i);
	}

	public TestClass(int in) {
		i++;
		System.out.println("4:" + i);
	}

	public static void main(String[] args) {
		System.out.println("5:" + i);
		new TestClass();
		System.out.println("6:" + i);
	}
	
	@Test
	public void testClass(){
		Future<String> future = new FutureTask<String>(new Runnable() {
			@Override
			public void run() {
				
			}
		}, null);
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("5:" + i);
		new TestClass();
		System.out.println("6:" + i);
	}
}
