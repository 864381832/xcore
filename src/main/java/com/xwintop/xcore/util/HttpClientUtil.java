package com.xwintop.xcore.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import okhttp3.*;
import org.apache.commons.collections.MapUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.CookieStore;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.cookie.Cookie;
//import org.apache.http.impl.client.BasicCookieStore;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
//	private static CloseableHttpClient HttpClient = HttpClients.createDefault();
//	// private static PoolingHttpClientConnectionManager cm = null;
//
//	public static String getHttpData(String url, String refererUrl, CloseableHttpClient closeableHttpClient) {
//		String content = null;
//		try {
//			HttpGet httpGet = new HttpGet(url);
//			httpGet.addHeader("Referer", refererUrl);
//			// String User_Agent = "Mozilla/5.0 (Windows NT 6.1; WOW64)
//			// AppleWebKit/537.36 (KHTML, like Gecko) Chrome/"+(iii++)+"
//			// Safari/537.36 Core/1.47.163."+(iii++)+" QQBrowser/9.3.7175.400";
//			// httpGet.setHeader("User-Agent", User_Agent);
//			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
//			HttpEntity httpEntity = httpResponse.getEntity();
//			if (httpEntity != null) {
//				content = EntityUtils.toString(httpEntity);
//			}
//			httpGet.abort();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return content;
//	}
//
//	public static String getHttpData(String url, Map<String, String> headerMap,
//			CloseableHttpClient closeableHttpClient) {
//		String content = null;
//		try {
//			HttpGet httpGet = new HttpGet(url);
//			headerMap.forEach(new BiConsumer<String, String>() {
//				@Override
//				public void accept(String key, String value) {
//					httpGet.addHeader(key, value);
//				}
//			});
//			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
//			HttpEntity httpEntity = httpResponse.getEntity();
//			if (httpEntity != null) {
//				content = EntityUtils.toString(httpEntity);
//			}
//			httpGet.abort();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return content;
//	}
//
//	public static String getHttpDataByPost(String url, String refererUrl, Map<String, String> map) {
//		// if (HttpClient == null) {
//		// HttpClient = HttpClients.createDefault();
//		// }
//		String body = "";
//		try {
//			// 创建httpclient对象
//			// CloseableHttpClient HttpClient = HttpClients.createDefault();
//			// 创建post方式请求对象
//			HttpPost httpPost = new HttpPost(url);
//			// 装填参数
//			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//			if (map != null) {
//				for (Entry<String, String> entry : map.entrySet()) {
//					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//				}
//			}
//			// 设置参数到请求对象中
//			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
//			// 设置header信息
//			// 指定报文头【Content-type】、【User-Agent】
//			httpPost.setHeader("Referer", refererUrl);
//			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
//			httpPost.setHeader("User-Agent",
//					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.1708.400 QQBrowser/9.5.9635.400");
//			httpPost.setHeader("Cookie",
//					"nocare=s%3AOhOAdc-MYTpR9NCk6T58MYAGtAsR72aR.Qe65uY%2Bx8407Kxdv3yp8J9fy20xCdBgLxgmXIFykCNw; Hm_lvt_6492f43b4d87ee2e840d670ac5336c22=1479543112,1479826360,1479913103,1480165503; Hm_lpvt_6492f43b4d87ee2e840d670ac5336c22=1480166241");
//			// 执行请求操作，并拿到结果（同步阻塞）
//			CloseableHttpResponse response = HttpClient.execute(httpPost);
//			// 获取结果实体
//			HttpEntity entity = response.getEntity();
//			if (entity != null) {
//				// 按指定编码转换结果实体为String类型
//				body = EntityUtils.toString(entity, "UTF-8");
//			}
//			EntityUtils.consume(entity);
//			// 释放链接
//			response.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return body;
//	}
//
//	public static String getHttpDataByPost(String url, Map<String, String> headerMap, Map<String, String> entityMap) {
//		String body = "";
//		try {
//			// 创建httpclient对象
//			// CloseableHttpClient HttpClient = HttpClients.createDefault();
//			// 创建post方式请求对象
//			HttpPost httpPost = new HttpPost(url);
//			// 装填参数
//			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//			if (entityMap != null) {
//				for (Entry<String, String> entry : entityMap.entrySet()) {
//					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//				}
//			}
//			// 设置参数到请求对象中
//			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
//			// 设置header信息
//			// 指定报文头【Content-type】、【User-Agent】
//			headerMap.forEach(new BiConsumer<String, String>() {
//				@Override
//				public void accept(String key, String value) {
//					httpPost.addHeader(key, value);
//				}
//			});
//			// 执行请求操作，并拿到结果（同步阻塞）
//			CloseableHttpResponse response = HttpClient.execute(httpPost);
//			// 获取结果实体
//			HttpEntity entity = response.getEntity();
//			if (entity != null) {
//				// 按指定编码转换结果实体为String类型
//				body = EntityUtils.toString(entity, "UTF-8");
//			}
//			EntityUtils.consume(entity);
//			// 释放链接
//			response.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return body;
//	}
//
//	public static String getHttpData(String url) {
//		return getHttpData(url, null);
//	}
//
//	public static String getHttpData(String url, String refererUrl) {
//		String content = null;
//		try {
//			CookieStore cookieStore = new BasicCookieStore();
//			// CloseableHttpClient HttpClient =
//			// HttpClients.custom().setDefaultCookieStore(cookieStore).build();
//			// 创建 httpUriRequest 实例
//			HttpGet httpGet = new HttpGet(url);
//			httpGet.addHeader("Referer", refererUrl);
//			// 执行 get 请求
//			HttpResponse httpResponse = HttpClient.execute(httpGet);
//			List<Cookie> cookies = cookieStore.getCookies();
//			for (int i = 0; i < cookies.size(); i++) {
//				System.out.println("Local cookie: " + cookies.get(i));
//			}
//			// 获取响应实体
//			HttpEntity httpEntity = httpResponse.getEntity();
//			if (httpEntity != null) {
//				content = EntityUtils.toString(httpEntity);// 响应内容
//			}
//			// 有些教程里没有下面这行
//			httpGet.abort();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return content;
//	}
//
//	public static HttpResponse getHttpResponse(String url, Map<String, String> paramsMap, Map<String, String> headerMap,
//			Map<String, String> cookieMap) {
//		StringBuffer paramsDataBuffer = new StringBuffer();
//		if (MapUtils.isNotEmpty(paramsMap)) {
//			paramsDataBuffer.append("?");
//			paramsMap.forEach(new BiConsumer<String, String>() {
//				@Override
//				public void accept(String key, String value) {
//					paramsDataBuffer.append(key).append("=").append(value).append("&");
//				}
//			});
//			paramsDataBuffer.deleteCharAt(paramsDataBuffer.length() - 1);
//		}
//		if (MapUtils.isNotEmpty(cookieMap)) {
//			StringBuffer paramsCookieBuffer = new StringBuffer();
//			cookieMap.forEach(new BiConsumer<String, String>() {
//				@Override
//				public void accept(String key, String value) {
//					paramsCookieBuffer.append(key).append("=").append(value).append(";");
//				}
//			});
//			paramsCookieBuffer.deleteCharAt(paramsCookieBuffer.length() - 1);
//			headerMap.put("Cookie", paramsCookieBuffer.toString());
//		}
//		url += paramsDataBuffer.toString();
//		return getHttpResponse(url, headerMap);
//	}
//
//	public static String getHttpDataAsUTF_8(String url, String refererUrl) {
//		Map<String, String> headerMap = new HashMap<String, String>();
//		headerMap.put("Referer", refererUrl);
//		return getHttpDataAsUTF_8(url, headerMap);
//	}
//
//	public static String getHttpDataAsUTF_8(String url, Map<String, String> headerMap) {
//		HttpResponse httpResponse = getHttpResponse(url, headerMap);
//		return getHttpDataByHttpResponse(httpResponse);
//	}
//
//	public static String getHttpDataByHttpResponse(HttpResponse httpResponse) {
//		String content = null;
//		// 获取响应实体
//		HttpEntity httpEntity = httpResponse.getEntity();
//		if (httpEntity != null) {
//			try {
//				content = EntityUtils.toString(httpEntity, "UTF-8");// 响应内容
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return content;
//	}
//
//	public static HttpResponse getHttpResponse(String url, Map<String, String> headerMap) {
//		HttpResponse httpResponse = null;
//		try {
//			HttpGet httpGet = new HttpGet(url);// 创建 httpUriRequest 实例
//			headerMap.forEach(new BiConsumer<String, String>() {
//				@Override
//				public void accept(String key, String value) {
//					httpGet.addHeader(key, value);
//				}
//			});
//			httpResponse = HttpClient.execute(httpGet);// 执行 get 请求
//			httpGet.abort();// 有些教程里没有这行
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return httpResponse;
//	}
//
//	// 创建默认的 HttpClient 实例
//	public static CloseableHttpClient createHttpClient() {
//		return HttpClients.createDefault();
//	}
//
//	public static CloseableHttpClient getHttpClient() {
//		if (HttpClient == null) {
//			HttpClient = HttpClients.createDefault();
//		}
//		return HttpClient;
//	}
//
//	public static void CloseHttpClient() {
//		try {
//			HttpClient.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void CloseHttpClient(CloseableHttpClient closeableHttpClient) {
//		try {
//			closeableHttpClient.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public static String getHttpDataAsUTF_8(String url, String refererUrl) {
		try {
			Map<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Referer", refererUrl);
			OkHttpClient client = new OkHttpClient();
			FormBody.Builder builder = new FormBody.Builder();
			Request request = new Request.Builder().url(url).headers(Headers.of(headerMap)).build();
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getHttpDataByPost(String url, String refererUrl, Map<String, String> map) {
		try {
			OkHttpClient client = new OkHttpClient();
			FormBody.Builder builder = new FormBody.Builder();
			map.forEach((String key, String value) -> {
				if(value!=null){
					builder.add(key, value);
				}
			});
			RequestBody body = builder.build();
			Map<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Referer", refererUrl);
			Request request = new Request.Builder().url(url).post(body).headers(Headers.of(headerMap)).build();
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getHttpDataByPost(String url, String refererUrl, String string) {
		return getHttpDataByPost(url,refererUrl,string,"text/x-markdown; charset=utf-8");
	}

	public static String getHttpDataByPost(String url, String refererUrl, String string,String header) {
		try {
			OkHttpClient client = new OkHttpClient();
			RequestBody body = RequestBody.create(MediaType.parse(header),string);
			Map<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Referer", refererUrl);
			Request request = new Request.Builder().url(url).post(body).headers(Headers.of(headerMap)).build();
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void openBrowseURL(String url) {
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.browse(new URI(url));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static void openBrowseURLThrowsException(String url) throws IOException, URISyntaxException {
		Desktop desktop = Desktop.getDesktop();
		desktop.browse(new URI(url));
	}
}
