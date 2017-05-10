package com.xwintop.xcore;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xwintop.xcore.util.MyLogger;

public class LogbackTest {
	@Test
	public void testLogger() {
		Logger log = LoggerFactory.getLogger(LogbackTest.class);
		log.info("test");
	}
	
	@Test
	public void testMyLogger() {
		MyLogger log = new MyLogger(LogbackTest.class);
		log.info("testMyLogger");
	}
}
