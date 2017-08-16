package com.xwintop.xcore.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
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

	public static String getHttpDataByPost(String url, String refererUrl, Map<String, String> map) {
		// if (HttpClient == null) {
		// HttpClient = HttpClients.createDefault();
		// }
		String body = "";
		try {
			// 创建httpclient对象
			// CloseableHttpClient HttpClient = HttpClients.createDefault();
			// 创建post方式请求对象
			HttpPost httpPost = new HttpPost(url);
			// 装填参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (map != null) {
				for (Entry<String, String> entry : map.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			// 设置参数到请求对象中
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			// 设置header信息
			// 指定报文头【Content-type】、【User-Agent】
			httpPost.setHeader("Referer", refererUrl);
			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
			httpPost.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.1708.400 QQBrowser/9.5.9635.400");
			httpPost.setHeader("Cookie",
					"nocare=s%3AOhOAdc-MYTpR9NCk6T58MYAGtAsR72aR.Qe65uY%2Bx8407Kxdv3yp8J9fy20xCdBgLxgmXIFykCNw; Hm_lvt_6492f43b4d87ee2e840d670ac5336c22=1479543112,1479826360,1479913103,1480165503; Hm_lpvt_6492f43b4d87ee2e840d670ac5336c22=1480166241");
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
		return body;
	}
	
	public static String getHttpData(String url) {
		return getHttpData(url, null);
	}

	public static String getHttpData(String url, String refererUrl) {
		String content = null;
		try {
			// 创建 httpUriRequest 实例
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("Referer", refererUrl);
			// 执行 get 请求
			HttpResponse httpResponse = HttpClient.execute(httpGet);
			// 获取响应实体
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				content = EntityUtils.toString(httpEntity);// 响应内容
			}
			// 有些教程里没有下面这行
			httpGet.abort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String getHttpDataAsUTF_8(String url, String refererUrl) {
		String content = null;
		try {
			HttpGet httpGet = new HttpGet(url);// 创建 httpUriRequest 实例
			httpGet.addHeader("Referer", refererUrl);
			HttpResponse httpResponse = HttpClient.execute(httpGet);// 执行 get 请求
			HttpEntity httpEntity = httpResponse.getEntity();// 获取响应实体
			if (httpEntity != null) {
				// long length = httpEntity.getContentLength();// 响应内容的长度
				content = EntityUtils.toString(httpEntity, "UTF-8");// 响应内容
			}
			httpGet.abort();// 有些教程里没有这行
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
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

	public static void openBrowseURL(String url) {
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.browse(new URI(url));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
