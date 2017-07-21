/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.jjfx.windows;

import java.util.Scanner;

public class JJDevice {
	public static String getCpuDevice() {
		try {
			Process process = Runtime.getRuntime().exec(new String[]{"wmic", "cpu", "get", "ProcessorId"});
			process.getOutputStream().close();
			Scanner sc = new Scanner(process.getInputStream());
//			String property = sc.next();
			String serial = sc.next();
			sc.close();
			return serial;
		} catch (Exception e) {
			return null;
		}
	}
}