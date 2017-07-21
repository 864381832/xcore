/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.jjfx.windows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JJVpn {
	private String account = "cnq853";
	private String pwd = "123";
	private String name = "vpn";

	public JJVpn(String account, String pwd, String name) {
		this.account = account;
		this.pwd = pwd;
		this.name = name;
	}

	private String executeCmd(String cmd) throws IOException {
		Process process = Runtime.getRuntime().exec("cmd /c " + cmd);
		StringBuilder sbCmd = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = "";
		while ((line = br.readLine()) != null) {
			sbCmd.append(line);
		}
		return sbCmd.toString();
	}

	public boolean disconnectVPN() {
		try {
			String cmd = "rasdial " + this.name + " /disconnect";
			String result = this.executeCmd(cmd);
			if (result == null || result.contains("没有连接")) {
				System.out.println("vpn断开失败");
				return false;
			}
			System.out.println("vpn断开成功");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("vpn断开失败");
			return false;
		}
	}

	public boolean connectVPN() {
		try {
			String cmd = "rasdial " + this.name + " " + this.account + " " + this.pwd;
			String result = this.executeCmd(cmd);
			if (result == null) {
				System.out.println("vpn连接失败");
				return false;
			}
			System.out.println("vpn连接成功");
			return true;
		} catch (IOException e) {
			System.out.println("vpn连接失败");
			return false;
		}
	}
}