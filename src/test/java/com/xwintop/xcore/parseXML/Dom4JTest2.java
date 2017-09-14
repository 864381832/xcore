package com.xwintop.xcore.parseXML;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.time.DateUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.xwintop.xcore.base.Bill;

import oracle.net.aso.f;

/**
 * dom4j框架学习： 读取并解析xml
 */
public class Dom4JTest2 {
	@Test
	public void TestDOMReader() throws Exception {
		SAXReader saxReader = new SAXReader();

		Document document = saxReader.read(new File("students.xml"));

		// 获取根元素
		Element root = document.getRootElement();
		System.out.println("Root: " + root.getName());

		// 获取所有子元素
		@SuppressWarnings("unchecked")
		List<Element> childList = root.elements();
		System.out.println("total child count: " + childList.size());

		// 获取特定名称的子元素
		@SuppressWarnings("unchecked")
		List<Element> childList2 = root.elements("hello");
		System.out.println("hello child: " + childList2.size());

		// 获取名字为指定名称的第一个子元素
		Element firstWorldElement = root.element("world");
		// 输出其属性
		System.out.println("first World Attr: " + firstWorldElement.attribute(0).getName() + "="
				+ firstWorldElement.attributeValue("name"));

		System.out.println("迭代输出-----------------------");
		// 迭代输出
		for (@SuppressWarnings("rawtypes")
		Iterator iter = root.elementIterator(); iter.hasNext();) {
			Element e = (Element) iter.next();
			System.out.println(e.attributeValue("name"));

		}

		System.out.println("用DOMReader-----------------------");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		// 注意要用完整类名
		org.w3c.dom.Document document2 = db.parse(new File("students.xml "));

		DOMReader domReader = new DOMReader();

		// 将JAXP的Document转换为dom4j的Document
		Document document3 = domReader.read(document2);

		Element rootElement = document3.getRootElement();

		System.out.println("Root: " + rootElement.getName());

	}
	
	@Test
	public void TestDOMReader2() throws Exception {
//		String date = "2016-05-06T00:57:02.227+01:00";
		String date = "2016-05-06T00:57:02.227+01:00";
		Date date2 = DateUtils.parseDate(date.split("+")[0], "yyyy-MM-dd'T'HH:mm:ss.SSS zz:z");
		System.out.println(date2.toLocaleString());
		Field[] fields = Bill.class.getDeclaredFields();
		for(Field field:fields){
			System.out.println(field.getType().getName());
		}
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File("D://港口信息PortsData.xml"));
		// 获取根元素
		Element root = document.getRootElement();
		System.out.println("Root: " + root.getName());
		// 获取所有子元素
		@SuppressWarnings("unchecked")
		List<Element> childList = root.elements();
		System.out.println("total child count: " + childList.size());
		// 获取特定名称的子元素
		@SuppressWarnings("unchecked")
		List<Element> childList2 = root.elements("PortsData");
		System.out.println("hello child: " + childList2.size());
		for(Element element1:childList2){
			element1.elements().forEach(new Consumer<Element>() {
				@Override
				public void accept(Element element) {
					System.out.println(element.getName()+":"+element.getText());
				}
			});
		}

	}

}
