package com.xwintop.xtest.email_sdk;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {

	public MyAuthenticator() {
		userName = null;
		password = null;
	}

	public MyAuthenticator(String username, String password) {
		userName = null;
		this.password = null;
		userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

	String userName;
	String password;
}
