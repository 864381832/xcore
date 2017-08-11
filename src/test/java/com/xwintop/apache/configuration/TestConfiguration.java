//package com.xwintop.apache.configuration;
//
//import java.util.Arrays;
//
//import org.apache.commons.configuration.CompositeConfiguration;
//import org.apache.commons.configuration.ConfigurationException;
//import org.apache.commons.configuration.HierarchicalINIConfiguration;
//import org.apache.commons.configuration.PropertiesConfiguration;
//import org.apache.commons.configuration.XMLConfiguration;
//import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
//import org.junit.Test;
//
//public class TestConfiguration {
//	@Test
//	// 使用commons-configuration读取properites文件
//	public void readProperties() throws ConfigurationException {
//		PropertiesConfiguration pcfg = new PropertiesConfiguration("apache/configuration/cfg.properties");
//		System.out.println(pcfg.getString("platform.jre"));
//	}
//
//	@Test
//	// 使用commons-configuration读取ini文件
//	public void readFromIni() throws ConfigurationException {
//		HierarchicalINIConfiguration ini = new HierarchicalINIConfiguration("apache/configuration/cfg.ini");
//		System.out.println("2. read configuration from cfg.ini...");
//		System.out.println("os.edition = " + ini.getString("os.edition"));
//	}
//
//	@Test
//	// 使用commons-configuration读取xml文件
//	public void readFromXml() throws ConfigurationException {
//		XMLConfiguration xml = new XMLConfiguration("apache/configuration/cfg.xml");
//		System.out.println("3. read configuration from cfg.xml...");
//
//		// XML配置文件的简单使用（不使用XPath）
//		System.out.println("3.1 simple use..");
//		System.out.println("teacher's name = " + xml.getString("teacher.name"));
//		System.out.println("teacher's age = " + xml.getString("teacher.age"));
//
//		// 使用XPath查询配置文件
//		System.out.println("3.2 use xpath...");
//		xml.setExpressionEngine(new XPathExpressionEngine());
//
//		// 查询姓名为foo的学生的算法课成绩
//		String expr1 = "/student[@name='foo']/score[@course='Algorithm']";
//		System.out.println("foo's algorithm score = " + xml.getString(expr1));
//
//		// 查询所有学生的算法课成绩
//		String expr2 = "/student//score[@course='Algorithm']";
//		System.out.println("all students algorithm's score = " + Arrays.toString(xml.getStringArray(expr2)));
//
//		// 查询姓名为foo的学生的性别
//		String expr3 = "/student[@name='foo']/@gender";
//		System.out.println("foo's gender = " + xml.getString(expr3));
//	}
//
//	@Test
//	// 组合配置
//	public void readFromCompositeConfig() throws ConfigurationException {
//		System.out.println("4. composite all configurations...");
//
//		CompositeConfiguration configurations = new CompositeConfiguration();
//		HierarchicalINIConfiguration ini = new HierarchicalINIConfiguration("apache/configuration/cfg.ini");
//		PropertiesConfiguration props = new PropertiesConfiguration("apache/configuration/cfg.properties");
//		XMLConfiguration xml = new XMLConfiguration("apache/configuration/cfg.xml");
//		xml.setExpressionEngine(new XPathExpressionEngine());
//
//		configurations.addConfiguration(ini);
//		System.out.println("add ini to composite configuration...");
//		configurations.addConfiguration(props);
//		System.out.println("add properties to composite configuration...");
//		configurations.addConfiguration(xml);
//		System.out.println("add xml to composite configuration...");
//
//		System.out.println("platform.jre = " + configurations.getString("platform.jre"));
//		System.out.println("os.edition = " + configurations.getString("os.edition"));
//
//		String expr1 = "/student[@name='foo']/score[@course='Algorithm']";
//		System.out.println("foo's algorithm score = " + configurations.getString(expr1));
//	}
//}
