package com.xwintop.apache.commons;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class FileUtilsTest {
	@Test
	public void getFiles() throws Exception {
		Collection<File> files = FileUtils.listFiles(new File("C:\\Users\\5FDSJ068\\Desktop\\海关二级节点DXP封装XML标准\\海关二级节点DXP封装XML标准"), null, false);
//		for(File file:files){
//			System.out.println(file.getName());
//			System.out.println(FileUtils.readFileToString(file,Charset.forName("UTF-8")));
//		}
		FileUtils.writeStringToFile(new File("C:\\Users\\5FDSJ068\\Desktop\\海关二级节点DXP封装XML标准\\test"), "测",Charset.forName("UTF-8"));
	}
}
