package com.xwintop.xmytest.date;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;

/**
 * @ClassName: TestDate
 * @Description: 测试获取日期
 * @author: xufeng
 * @date: 2017年6月3日 下午10:15:54
 */
public class TestDate {

	@Test
	public void getTimeByCalendar() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);// 获取年份
		int month = cal.get(Calendar.MONTH);// 获取月份
		int day = cal.get(Calendar.DATE);// 获取日
		int hour = cal.get(Calendar.HOUR);// 小时
		int minute = cal.get(Calendar.MINUTE);// 分
		int second = cal.get(Calendar.SECOND);// 秒
		int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);// 一周的第几天
		System.out.println("现在的时间是：公元" + year + "年" + month + "月" + day + "日      " + hour + "时" + minute + "分" + second
				+ "秒       星期" + WeekOfYear);
	}

	@Test
	public void getTimeByDate() {
		Date date = new Date();
		DateFormat df1 = DateFormat.getDateInstance();// 日期格式，精确到日
		System.out.println(df1.format(date));
		DateFormat df2 = DateFormat.getDateTimeInstance();// 可以精确到时分秒
		System.out.println(df2.format(date));
		DateFormat df3 = DateFormat.getTimeInstance();// 只显示出时分秒
		System.out.println(df3.format(date));
		DateFormat df4 = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL); // 显示日期，周，上下午，时间（精确到秒）
		System.out.println(df4.format(date));
		DateFormat df5 = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG); // 显示日期,上下午，时间（精确到秒）
		System.out.println(df5.format(date));
		DateFormat df6 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT); // 显示日期，上下午,时间（精确到分）
		System.out.println(df6.format(date));
		DateFormat df7 = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM); // 显示日期，时间（精确到分）
		System.out.println(df7.format(date));
	}
}
