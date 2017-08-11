package com.xwintop.xmytest.kaola;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;

public class HttpGetUtil {
	private static CloseableHttpClient HttpClient = HttpClients.createDefault();
	// private static PoolingHttpClientConnectionManager cm = null;

	public static String getHttpData(String url, String refererUrl, CloseableHttpClient closeableHttpClient) {
		String content = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("Referer", refererUrl);
			// String User_Agent = "Mozilla/5.0 (Windows NT 6.1; WOW64)
			// AppleWebKit/537.36 (KHTML, like Gecko) Chrome/"+(iii++)+"
			// Safari/537.36 Core/1.47.163."+(iii++)+" QQBrowser/9.3.7175.400";
			// httpGet.setHeader("User-Agent", User_Agent);
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				content = EntityUtils.toString(httpEntity);
			}
			httpGet.abort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String getHttpData(String url, String refererUrl) {
		// if(cm == null){
		// cm = new PoolingHttpClientConnectionManager();
		// cm.setMaxTotal(500000);//整个连接池最大连接数
		// cm.setDefaultMaxPerRoute(500);//每路由最大连接数，默认值是2
		// }
		// CloseableHttpClient HttpClient =
		// HttpClients.custom().setConnectionManager(cm).build();
//		if (HttpClient == null) {
//			HttpClient = HttpClients.createDefault();
//		}
		String content = null;
		try {
			// 创建 httpUriRequest 实例
			HttpGet httpGet = new HttpGet(url);
			// System.out.println("uri=" + httpGet.getURI());
			httpGet.addHeader("Referer", refererUrl);
			// 执行 get 请求
			HttpResponse httpResponse = HttpClient.execute(httpGet);
			// 获取响应实体
			HttpEntity httpEntity = httpResponse.getEntity();
			// 打印响应状态
			// System.out.println(httpResponse.getStatusLine());
			if (httpEntity != null) {
				// long length = httpEntity.getContentLength();// 响应内容的长度
				content = EntityUtils.toString(httpEntity);// 响应内容
			}
			// 有些教程里没有下面这行
			httpGet.abort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(content);
		return content;
	}
	
	public static String getHttpDataAsUTF_8(String url, String refererUrl) {
		String content = null;
		try {
			HttpGet httpGet = new HttpGet(url);// 创建 httpUriRequest 实例
			httpGet.addHeader("Referer", refererUrl);
			// 执行 get 请求
			HttpResponse httpResponse = HttpClient.execute(httpGet);
			// 获取响应实体
			HttpEntity httpEntity = httpResponse.getEntity();
			// 打印响应状态
			// System.out.println(httpResponse.getStatusLine());
			if (httpEntity != null) {
				// long length = httpEntity.getContentLength();// 响应内容的长度
				content = EntityUtils.toString(httpEntity,"UTF-8");// 响应内容
			}
			httpGet.abort();// 有些教程里没有这行
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String getHttpDataByPost(String url, String refererUrl, Map<String, String> map) {
		String body = "";
		try {
			// 创建post方式请求对象
			HttpPost httpPost = new HttpPost(url);
			// 装填参数
			JsonObject jsonObject = new JsonObject();
			if (map != null) {
				for (Entry<String, String> entry : map.entrySet()) {
					jsonObject.addProperty(entry.getKey(), entry.getValue());
				}
			}
			StringEntity stringEntity = new StringEntity(jsonObject.toString(),"UTF-8");
			httpPost.setEntity(stringEntity);
			System.out.println("请求地址：" + url);
			System.out.println("请求参数：" + jsonObject.toString());
			// 设置header信息
			// 指定报文头【Content-type】、【User-Agent】
//			httpPost.setHeader("Referer", refererUrl);
			httpPost.setHeader("Content-type", "application/json;charset=utf-8");
//			httpPost.setHeader("Accept", "application/json;charset=utf-8");
//			httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.1708.400 QQBrowser/9.5.9635.400");
			// 执行请求操作，并拿到结果（同步阻塞）
			CloseableHttpResponse response = HttpClient.execute(httpPost);
			// 获取结果实体
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// 按指定编码转换结果实体为String类型
				body = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
			// 释放链接
			response.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(body);
		return body;
	}
	
	// 创建默认的 HttpClient 实例
	public static CloseableHttpClient createHttpClient() {
		return HttpClients.createDefault();
	}

	public static CloseableHttpClient getHttpClient() {
		if (HttpClient == null) {
			HttpClient = HttpClients.createDefault();
		}
		return HttpClient;
	}

	public static void CloseHttpClient() {
		try {
			HttpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void CloseHttpClient(CloseableHttpClient closeableHttpClient) {
		try {
			closeableHttpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
