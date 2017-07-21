/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.jjfx.windows;

import java.awt.AWTException;
import java.awt.Robot;

public class JJKeyBoard {
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

	public static void click(int keyEvent) {
		JJKeyBoard.getRobot().keyPress(keyEvent);
		JJKeyBoard.getRobot().keyRelease(keyEvent);
	}

	public static void Ctrl_A(int delay) {
		JJKeyBoard.getRobot().keyPress(17);
		JJKeyBoard.getRobot().keyPress(65);
		JJKeyBoard.getRobot().setAutoDelay(10);
		JJKeyBoard.getRobot().keyRelease(65);
		JJKeyBoard.getRobot().keyRelease(17);
		JJKeyBoard.getRobot().setAutoDelay(delay);
	}

	public static void Ctrl_C(int delay) {
		JJKeyBoard.getRobot().keyPress(17);
		JJKeyBoard.getRobot().keyPress(67);
		JJKeyBoard.getRobot().setAutoDelay(10);
		JJKeyBoard.getRobot().keyRelease(67);
		JJKeyBoard.getRobot().keyRelease(17);
		JJKeyBoard.getRobot().setAutoDelay(delay);
	}

	public static enum KeyCode {
		A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;

		private KeyCode() {
		}
	}

}