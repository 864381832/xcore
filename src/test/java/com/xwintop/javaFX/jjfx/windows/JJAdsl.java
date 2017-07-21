/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.jjfx.windows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JJAdsl {
	private static String executeCmd(String strCmd) throws Exception {
		String line;
		Process p = Runtime.getRuntime().exec("cmd /c " + strCmd);
		StringBuilder sbCmd = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GB2312"));
		while ((line = br.readLine()) != null) {
			sbCmd.append(line + "\n");
		}
		return sbCmd.toString();
	}

	public static boolean connect(String adslName, String adslAccount, String adslPwd) throws Exception {
		String adslCmd = "rasdial " + adslName + " " + adslAccount + " " + adslPwd;
		String tempCmd = JJAdsl.executeCmd(adslCmd);
		if (tempCmd.indexOf("已连接") > 0) {
			return true;
		}
		return false;
	}

	public static boolean disconnect(String adslName) throws Exception {
		String cutAdsl = "rasdial " + adslName + " /disconnect";
		String result = JJAdsl.executeCmd(cutAdsl);
		if (result.indexOf("没有连接") != -1) {
			throw new Exception(adslName + "连接不存在");
		}
		return true;
	}

	public static boolean isConnect() {
		boolean connect = false;
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec("ping www.baidu.com");
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			isr.close();
			br.close();
			if (null != sb && !sb.toString().equals("")) {
				connect = sb.toString().indexOf("TTL") > 0;
			}
		} catch (IOException e) {
			connect = false;
		}
		return connect;
	}
}