/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.jjfx.windows;

import java.awt.AWTException;
import java.awt.Robot;

public class JJMouse {
	private static Robot mRobot;

	private static Robot getRobot() {
		if (mRobot == null) {
			try {
				mRobot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
		return mRobot;
	}

	public static void clickL(int x, int y, int delay) {
		JJMouse.getRobot().mouseMove(x, y);
		JJMouse.getRobot().mousePress(16);
		JJMouse.getRobot().delay(10);
		JJMouse.getRobot().mouseRelease(16);
		JJMouse.getRobot().delay(delay);
	}

	public static void doubleClickL(int x, int y, int delay) {
		JJMouse.clickL(x, y, 10);
		JJMouse.clickL(x, y, delay);
	}
}