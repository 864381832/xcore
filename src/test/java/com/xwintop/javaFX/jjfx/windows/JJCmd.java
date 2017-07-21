/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.jjfx.windows;

import java.io.IOException;
import java.util.ArrayList;

public class JJCmd {
	public static void cmd(String cmd) {
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add("cmd.exe");
			list.add("/c");
			list.add(cmd);
			ProcessBuilder pBuilder = new ProcessBuilder(list);
			pBuilder.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void openDir(String programPath) throws IOException {
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add("cmd.exe");
			list.add("/c");
			list.add(" explorer.exe    " + programPath);
			ProcessBuilder pBuilder = new ProcessBuilder(list);
			pBuilder.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void runEXE(String programPath) {
		try {
			String programName = programPath.substring(programPath.lastIndexOf("/") + 1, programPath.lastIndexOf("."));
			ArrayList<String> list = new ArrayList<String>();
			list.add("cmd.exe");
			list.add("/c");
			list.add("start");
			list.add("\"" + programName + "\"");
			list.add("\"" + programPath + "\"");
			ProcessBuilder pBuilder = new ProcessBuilder(list);
			pBuilder.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void killExe(String exeName) {
		String[] cmd = new String[]{"cmd.exe", "/C", "taskkill /f /t /im " + exeName};
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}