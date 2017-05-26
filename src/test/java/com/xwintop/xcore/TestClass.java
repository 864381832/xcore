package com.xwintop.xcore;

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
		System.out.println("5:" + i);
		new TestClass();
		System.out.println("6:" + i);
	}
}
