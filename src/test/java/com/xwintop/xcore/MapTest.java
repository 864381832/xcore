package com.xwintop.xcore;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.junit.Test;

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
}
