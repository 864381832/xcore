package com.xwintop.xmytest.email;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.xwintop.xtest.email_sdk.MailSenderInfo;
import com.xwintop.xtest.email_sdk.SimpleMailSender;
import com.xwintop.xtest.util.PasswordCreateUtil;

public class TestEmailSend {
	private static String emailUrl = "18356971618@163.com";

	/**
	 * @Title: sendTest
	 * @Description: 测试发送邮件
	 */
	@Test
	public void sendTest() {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("xwintop@163.com");
		mailInfo.setPassword("xufeng108622");// 您的邮箱密码
		mailInfo.setFromAddress("xwintop@163.com");
		mailInfo.setToAddress(emailUrl);
		mailInfo.setSubject("易莫森密码找回");
		mailInfo.setContent("您的密码已重置，新密码为：" + "123456");
		SimpleMailSender sms = new SimpleMailSender();
		boolean istrue = sms.sendTextMail(mailInfo);
		System.out.println(istrue);
	}

	/**
	 * @Title: sendBatchTest
	 * @Description: 测试批量发送邮件
	 */
	@Test
	public void sendBatchTest() {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.yeah.net");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("yms_reg");
		mailInfo.setPassword("heliushi_yeah");// 您的邮箱密码
		mailInfo.setFromAddress("yms_reg@yeah.net");
		mailInfo.setSubject("易莫森密码找回");
		mailInfo.setContent("您的密码已重置，新密码为：" + "123456");
		List<String> sList = new ArrayList<String>();
		sList.add("18356971618@163.com");
		sList.add("xf108622@163.com");
		SimpleMailSender sms = new SimpleMailSender();
		for (String toAddress : sList) {
			mailInfo.setToAddress(toAddress);
			sms.sendTextMail(mailInfo);
		}
	}

	@Test
	public void testCheckPasswordSecurity() {
		// String password = "Abc12345!";
		String password = "Abc12345[";
		System.out.println(PasswordCreateUtil.checkPasswordSecurity(password));
		boolean is = password.matches("(?=^.{8,}$)(?=.*?\\d)(?=.*[a-z])(?=.*?[A-Z])"
				+ "(?=.*?[~!/@#$%^&*()\\-_=+\\|\\[{}\\];:\'\",<.>/?])(?!.*\\s)[0-9a-zA-Z~!/@#$%^&*()\\-_=+\\|\\[{}\\];:\'\",<.>/?]*$");
		System.out.println(is);
	}

	@Test
	public void testCreatPassword() {
		String phone = "18356971618";
		phone = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		System.out.println(phone);
		DecimalFormat decimalFormat = new DecimalFormat("000000");
		double s = Math.random();
		System.out.println(s);
		System.out.println(decimalFormat.format(s * 1000000));
		// String password = PasswordCreateUtil.createPassword();
		// PasswordCreateUtil.checkPasswordSecurity(password);
		// System.out.println(password);
	}
}
