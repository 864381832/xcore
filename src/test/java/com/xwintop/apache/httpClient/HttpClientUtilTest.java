package com.xwintop.apache.httpClient;

import org.junit.Test;

import com.xwintop.xcore.util.HttpClientUtil;

public class HttpClientUtilTest {
	private String url = "http://baidu.com";
	@Test
	public void testgetHttpData(){
		HttpClientUtil.getHttpData(url);
	}
}
