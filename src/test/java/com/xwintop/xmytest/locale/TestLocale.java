package com.xwintop.xmytest.locale;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class TestLocale {

	@Test
	public void testLocale() {
//		 Locale locale = Locale.getDefault();//获取地区:默认
		// 获取资源束。如未发现则会抛出MissingResourceException异常
		// "Properties.Dorian"为在Properties下以Dorian为文件名的默认属性文件
		Locale locale = Locale.CHINA;
//		Locale locale = Locale.US;
		ResourceBundle bundle = ResourceBundle.getBundle("locale.Dorian", locale);
		System.out.println(bundle.getString("Title"));
	}

	@Test
	public void testNumberFormat() {
		Locale locale = new Locale("en", "US");
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		double num = 123456.78;
		System.out.println("51423.45 Format:");
		System.out.println(String.format("%s : %s", locale.toString(), format.format(num)));
	}

	@Test
	public void testDateFormat() {
		Date date = new Date();
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("zh", "CN"));
		DateFormat df2 = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("en", "US"));
		System.out.println(df.format(date));
		System.out.println(df2.format(date));
	}

	@Test
	public void testMessageFormat() {
		String pattern1 = "{0}，你好！你于  {1} 消费  {2} 元。";
		String pattern2 = "At {1,time,short} On {1,date,long}，{0} paid {2,number, currency}.";
		Object[] params = { "Jack", new GregorianCalendar().getTime(), 8888 };
		String msg1 = MessageFormat.format(pattern1, params);
		MessageFormat mf = new MessageFormat(pattern2, Locale.US);
		String msg2 = mf.format(params);
		System.out.println(msg1);
		System.out.println(msg2);
	}
}
