package com.xwintop.apache.net;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

import com.xwintop.xcore.util.FtpUtil;

import lombok.extern.log4j.Log4j;

@Log4j
public class FtpTest {
	private String url = "127.0.0.1";
	private int port = 21;
	private String filePath = "/";
	private String username = "admin";
	private String password = "admin";

	@Test
	public void testUploadFile() throws Exception {
		String fileName = "test.txt";
		InputStream input = new ByteArrayInputStream(new String("测试").getBytes("UTF-8"));
		boolean flag = FtpUtil.uploadFile(url, port, username, password, filePath, fileName, input);
		log.error("上传ftp结果：" + flag);
	}
	
	@Test
	public void testDownFile() throws Exception {
		String fileName = "test.txt";
		boolean flag = FtpUtil.downFile(url, port, username, password, filePath, fileName, "D:/");
		log.error("下载ftp结果：" + flag);
	}
	
	@Test
	public void testDeleteFile() throws Exception {
		boolean flag = FtpUtil.deleteFile(url, port, username, password, "/test.txt");
		log.info("删除ftp文件结果：" + flag);
	}
	
	@Test
	public void testRemoveDirectory() throws Exception {
		boolean flag = FtpUtil.removeDirectory(url, port, username, password,"/test");
		log.info("删除ftp文件夹结果：" + flag);
	}
}
