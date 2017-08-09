package com.xwintop.apache.betwixt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class XML2BeanUtilsTest {
	@Test
	// 测试简单属性
	public void testPsersonBean() throws Exception {
		String xmlString = XML2BeanUtils.bean2XmlString(createPerson());
		System.out.println(xmlString);
		PersonBean person = XML2BeanUtils.xmlSring2Bean(PersonBean.class, xmlString);
		System.out.println(person);
	}

	@Test
	// 测试复杂属性
	public void testUser() throws Exception {
		String xmlString = XML2BeanUtils.bean2XmlString(createUser());
		System.out.println(xmlString);
		User user = XML2BeanUtils.xmlSring2Bean(User.class, xmlString);
		System.out.println(user);
	}

	public PersonBean createPerson() {
		return new PersonBean("name", 23);
	}

	// 创建复杂的用户对象
	public User createUser() {
		User user = new User();
		user.setAge(18);
		user.setUserName("张三");
		user.setHobbyArray(new String[] { "篮球", "足球", "乒乓球", "羽毛球" });
		user.setHobbyList(Arrays.asList(new String[] { "游泳", "蛙游", "蝶泳", "自由泳", "狗刨" }));
		user.setPerson(createPerson());
		Map<String, PersonBean> personMap = new HashMap<String, PersonBean>();
		for (int i = 0; i < 5; i++) {
			personMap.put("person" + i, new PersonBean("person" + i, i));
		}
		user.setPersonMap(personMap);
		return user;
	}
}
