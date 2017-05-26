package com.xwintop.xmytest.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	private Object target;

	MyInvocationHandler() {
		super();
	}

	MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
		System.out.println("++++++before " + method.getName() + "++++++");
		Object result = null;
		if ("toString".equals(method.getName())) {
			result = method.invoke(target, args);
			System.out.println("toString");
		} else {
			result = method.invoke(target, args);
			System.out.println("厕所");
		}
		System.out.println("++++++after " + method.getName() + "++++++");
		return result;
	}

}
