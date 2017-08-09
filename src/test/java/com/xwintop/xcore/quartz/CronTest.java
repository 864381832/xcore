package com.xwintop.xcore.quartz;

import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;
import org.quartz.CronExpression;

/**
 *
 * @author Administrator
 */
public class CronTest {

	/**
	 * Test of main method, of class Main.
	 */
	@Test
	public void testMain() throws Exception {
		CronExpression exp = new CronExpression("0 3/5 3,5,14 1-30 * ?");

		System.out.println("toString: " + exp.toString());
		System.out.println("isValidExpression: " + CronExpression.isValidExpression(exp.getCronExpression()));

		System.out.println("Fields->" + exp.getExpressionSummary());
		System.out.println("<-Fields");

		java.util.Date dd = new java.util.Date();
		for (int i = 1; i < 6; i++) {
			dd = exp.getNextValidTimeAfter(dd);
			System.out.println("getNextValidTimeAfter()" + i + "." + DateFormatUtils.format(dd, "yyyy-MM-dd HH:mm:ss"));
			dd = new java.util.Date(dd.getTime() + 1000);
		}

	}
}
