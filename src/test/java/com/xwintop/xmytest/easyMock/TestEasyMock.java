package com.xwintop.xmytest.easyMock;

import java.math.BigInteger;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.junit.Test;
import org.junit.runner.RunWith;

//@RunWith attaches a runner with the test class to initialize the test data
@RunWith(EasyMockRunner.class)
public class TestEasyMock {

	// @Mock annotation is used to create the mock object to be injected
	@Mock
	Comparable<BigInteger> comparable;

	@Test
	public void testAdd() {
		// add the behavior of calc service to add two numbers
		EasyMock.expect(comparable.compareTo(BigInteger.ONE)).andReturn(1);
		// activate the mock
		EasyMock.replay(comparable);

		// test the add functionality
//		Assert.assertEquals(mathApplication.compareTo(BigInteger.ONE), 1);
		System.out.println(comparable.compareTo(BigInteger.ONE));
	}
}
