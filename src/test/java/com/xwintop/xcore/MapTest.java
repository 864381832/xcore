package com.xwintop.xcore;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import com.xwintop.xcore.util.StrUtil;

public class MapTest {
	@Test
	public void testMap() {
		Map<String, String> map = new HashMap<>();
		map.put(null, "a");
		map.put(null, "b");
		map.put("a", "b");
		map.put("a", null);
		map.put(null, null);
		System.out.println(map.size());
		System.out.println(map);

		Map<String, String> mapTable = new Hashtable<>();
		mapTable.put(null, "a");
		mapTable.put(null, "b");
		mapTable.put("a", "b");
		mapTable.put("a", null);
		mapTable.put(null, null);
		System.out.println(mapTable.size());
		System.out.println(mapTable);
	}

	@Test
	public void testField() {
//		getField(Object.class);
		getField2(IhsCombinedPositionsData.class);
	}

	public void getField(Class<?> xclass) {
		Field[] fields = FieldUtils.getAllFields(xclass);
		StringBuffer stringBuffer = new StringBuffer();
		for (Field field : fields) {
			stringBuffer.append("pos.set" + StrUtil.fristToUpCase(field.getName()));
			stringBuffer.append("(ParseUtils.getDoubleFromString(" + field.getName() + "));\n");
		}
		System.out.println(stringBuffer.toString());
	}
	
	public void getField2(Class<?> xclass) {
		Field[] fields = FieldUtils.getAllFields(xclass);
		StringBuffer stringBuffer = new StringBuffer();
		int i = -1;
		for (Field field : fields) {
			stringBuffer.append("stmt.setString(" +i+",ihsCombinedPositionsData.get"+ StrUtil.fristToUpCase(field.getName()));
			stringBuffer.append("());\n");
			i++;
		}
		System.out.println(stringBuffer.toString());
	}
}
