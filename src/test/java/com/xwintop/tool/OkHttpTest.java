package com.xwintop.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpTest {
	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
	private final OkHttpClient client = new OkHttpClient();

	
	@Test
	public void test() throws Exception {
		String[] urlStrings = "http://127.0.0.1:8020/xTool/vueTest/index.html".split("/");
		String[] sUrlStrings = "/lib/jbase64.js".split("\\.\\./");
		String str = StringUtils.join(urlStrings,"/",0,urlStrings.length-sUrlStrings.length);
		System.out.println(str);
		System.out.println(str+"/"+sUrlStrings[sUrlStrings.length-1]);
	}
	
	@Test
	// 同步GET
	public void testGet() throws Exception {
//		Request request = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();
		Request request = new Request.Builder().url("http://127.0.0.1:8020/xTool/vueTest/index.html").build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);
		Headers responseHeaders = response.headers();
		for (int i = 0; i < responseHeaders.size(); i++) {
			System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
		}
		ResponseBody responseBody = response.body();
		System.out.println(responseBody.string());
	}

	@Test
	// 异步GET
	public void testGet2() throws Exception {
		Request request = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (!response.isSuccessful())
					throw new IOException("Unexpected code " + response);
				Headers responseHeaders = response.headers();
				for (int i = 0; i < responseHeaders.size(); i++) {
					System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
				}
				System.out.println(response.body().string());
			}
		});
	}

	@Test
//	Post方式提交String
	public void testPost() throws Exception {
		String postBody = "" + "Releases\n" + "--------\n" + "\n" + " * _1.0_ May 6, 2013\n"
				+ " * _1.1_ June 15, 2013\n" + " * _1.2_ August 11, 2013\n";
		Request request = new Request.Builder().url("https://api.github.com/markdown/raw")
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody)).build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);
		System.out.println(response.body().string());
	}
	
	@Test
//	Post方式提交String
	public void testPost3() throws Exception {
//		String postBody = "{\"desc\":[\"鲜、冷牛肉d\"]}";
//		String postBody = "{\"desc\":[]}";
		String postBody = "{\"desc\":[\"\"]}";
		Request request = new Request.Builder().url("http://192.168.5.79:8788/predict")
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody)).build();
		Response response = client.newCall(request).execute();
//		if (!response.isSuccessful())
//			throw new IOException("Unexpected code " + response);
		System.out.println(response.body().string());
	}
	
	@Test
	// Post方式提交String
	public void testPost2() throws Exception {
		RequestBody body = new FormBody.Builder().add("desc", "你好")// 添加键值对
				.build();
		Request request = new Request.Builder().url("http://192.168.5.79:8788/predict")
				.addHeader("Content-Type", "application/json").post(body).build();
		Response response = client.newCall(request).execute();
//		if (!response.isSuccessful())
//			throw new IOException("Unexpected code " + response);
		System.out.println(response.body().string());
	}
	
	@Test
	//post方式提交文件
	public void testPostFile() throws Exception {
	    File file = new File("README.md");
	    Request request = new Request.Builder()
	        .url("https://api.github.com/markdown/raw")
	        .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
	        .build();
	    Response response = client.newCall(request).execute();
	    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
	    System.out.println(response.body().string());
	}

}
