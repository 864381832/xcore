package com.xwintop.apache.beanUtils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.xwintop.xcore.base.Bill;

public class TestBeanUtils {
	@Test
	public void testCopyProperties() throws Exception {
		// 1.生成对象
		Bill s1 = new Bill();
		Bill s2 = new Bill();
		// 2.通过set方法赋值
		s1.setId("1");
		s1.setName("VN");

		// 需求：把s1的属性值拷贝到S2中，注意参数的顺序
		BeanUtils.copyProperties(s2, s1);
		System.out.println(s1.getId());
		System.out.println(s2.getId());
	}

	@Test
	public void testCopyMap() throws Exception {
		// 1.生成对象
		Map<String, Object> map = new HashMap<String, Object>();
		// 2.给一些参数
		map.put("id", 2);
		map.put("name", "EZ");

		// 需求：把map的属性值拷贝到S中
		Bill s = new Bill();
		BeanUtils.copyProperties(s, map);
		System.out.println(s.getId());
	}
}
