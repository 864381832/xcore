package com.xwintop.xcore;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {
	@Test
	public void testLogger() {
		Logger log = LoggerFactory.getLogger(LogbackTest.class);
		log.info("ceshi");
	}
}
