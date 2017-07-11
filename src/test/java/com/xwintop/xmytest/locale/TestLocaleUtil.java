package com.xwintop.xmytest.locale;

import java.util.Locale;

import org.junit.Test;

import com.xwintop.xcore.util.LocaleUtil;

public class TestLocaleUtil {

	@Test
	public void testLocale() {
//		 Locale locale = Locale.getDefault();//获取地区:默认
//		Locale locale = Locale.CHINA;
		Locale locale = Locale.US;
		LocaleUtil.initLocaleUtil("locale.Dorian",locale);
		System.out.println(LocaleUtil.getStringByKey("Title"));
	}
}
