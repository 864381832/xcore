package com.xwintop.tool;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import lombok.Cleanup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@Getter
@Setter
//@AllArgsConstructor // 有参构造函数
@NoArgsConstructor // 无参构造函数
@ToString
@EqualsAndHashCode // 根据类中定义的变量生成hashCode和equals方法
@Data
// 添加@ToString, @EqualsAndHashCode 和 @RequiredArgsConstructor
// 所有变量增加@Getter
// 所有非final类型的变量增加@Setter
@Log
public class LombokTest {
	private String name;
	private int age;
	private List<String> authors;

	@Test
	public void testLombok() throws Exception {
		testNonNullExample(null);
		@Cleanup
		InputStream inputStream = new FileInputStream("D:/testConfig");
		log.info("testLombok");
	}

	public void testNonNullExample(@NonNull String str) {
		System.out.println(str.contains("a"));
	}
}
