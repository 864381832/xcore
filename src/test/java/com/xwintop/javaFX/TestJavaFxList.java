package com.xwintop.javaFX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class TestJavaFxList {
	@Test
	public void testObservableList() {
		List<String> list = new ArrayList<String>();

		ObservableList<String> observableList = FXCollections.observableList(list);
		observableList.addListener(new ListChangeListener() {
			@Override
			public void onChanged(ListChangeListener.Change change) {
				System.out.println("有修改操作!");
			}
		});
		observableList.add("item one");
		list.add("item two");
		System.out.println("Size: " + observableList.size());
	}

	@Test
	public void testObservableMap() {
		Map<String, String> map = new HashMap<String, String>();
		ObservableMap<String, String> observableMap = FXCollections.observableMap(map);
		observableMap.addListener(new MapChangeListener() {
			@Override
			public void onChanged(MapChangeListener.Change change) {
				System.out.println("change! ");
			}
		});
		observableMap.put("key 1", "value 1");
		map.put("key 2", "value 2");
	}

	@Test
	public void testObservableList2() {
		List<String> list = new ArrayList<String>();

		ObservableList<String> observableList = FXCollections.observableList(list);
		observableList.addListener(new ListChangeListener() {
			@Override
			public void onChanged(ListChangeListener.Change change) {
				System.out.println("Detected a change! ");
				while (change.next()) {
					System.out.println("Was added? " + change.wasAdded());
					System.out.println("Was removed? " + change.wasRemoved());
					System.out.println("Was replaced? " + change.wasReplaced());
					System.out.println("Was permutated? " + change.wasPermutated());
				}
			}
		});// => W W w . y Ii B aI. CO M
//		observableList.add("item one");
		list.add("item two");
		System.out.println("Size: " + observableList.size());

	}
}
