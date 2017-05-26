package com.xwintop.xmytest.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.junit.Test;

/**
 * @author Administrator 测试动态代理
 */
public class TestInvocationHandler {
	@Test
	public void testInvocationHandler() {
		Comparable integer = new Integer(1);
		InvocationHandler invocationHandler = new MyInvocationHandler(integer);
		Comparable userServiceProxy = (Comparable) Proxy.newProxyInstance(integer.getClass().getClassLoader(),
				integer.getClass().getInterfaces(), invocationHandler);
		System.out.println(userServiceProxy.toString());
		// System.out.println(userServiceProxy.equals(2));
	}
}
