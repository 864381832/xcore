package com.xwintop.xcore.JAXB;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;

public class XMLGregorianCalendarTest {
	// 获取当前时间
	@Test
	public void testXMLGregorianCalendar() throws Exception {
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		System.out.println("xgcal=" + xgcal.toString());
	}

	@Test
	public void testXMLGregorianCalendar2() throws Exception {
		XMLGregorianCalendar xgcal = dateToXmlDate(new Date());
		System.out.println("xgcal=" + xgcal.toString());
	}

	/**
	 * 将Date类转换为XMLGregorianCalendar
	 * 
	 * @param date
	 * @return
	 */
	public static XMLGregorianCalendar dateToXmlDate(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return gc;
	}

	/**
	 * 将XMLGregorianCalendar转换为Date
	 * 
	 * @param cal
	 * @return
	 */
	public static Date xmlDate2Date(XMLGregorianCalendar cal) {
		return cal.toGregorianCalendar().getTime();
	}
}
