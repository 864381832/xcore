package com.xwintop.xtest.ych_sdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class YchClient {

	private String appkey;
	
	private String secret;
	
	private class TrustAllTrustManager implements X509TrustManager {
		
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
	}
	
	public YchClient(String appkey, String secret) {
		this.appkey = appkey;
		this.secret = secret;
	}
	
	public String send(String url, TreeMap<String, String> params) {	
		if (appkey == null || appkey.length() == 0
			|| secret == null || secret.length() == 0
			|| url == null || url.length() == 0
			|| params == null || params.size() == 0) {
			return "Parameter error.";
		}
		
		Set<String> keys = params.keySet();
		
		for (String key : keys) {
			String value = params.get(key);
			
			if (value == null || value.equalsIgnoreCase("null")) {
				return key + " is null.";
			}
		}
		
		params.put("appKey", appkey);
		
		return doPost(url, params);
	}
	
	private String doPost(String url, TreeMap<String, String> params) {
		
		String queryString = getSignedUrl(secret, params);
		
		if (queryString == null || queryString.length() == 0) {
			return "getSignedUrl error.";
		}
		
		return sendRequest(url, queryString);
	}
	
	private String getSignedUrl(String appSecret, TreeMap<String, String> paramMap) {	
		String sign = getSignature(appSecret, paramMap);
		StringBuilder query = new StringBuilder();
		
		for (Entry<String, String> en : paramMap.entrySet()) {
			query.append(en.getKey());
			query.append("=");
			try {
				if (en.getValue() != null && en.getValue().length() > 0) {
					query.append(URLEncoder.encode(en.getValue(), "UTF-8"));
				}
			} catch (UnsupportedEncodingException e) {
				System.out.println("getSignedUrl throw error:" + e.getMessage());
			}
			query.append("&");
		}
		
		query.append("sign=");
		query.append(sign);
		
		return query.toString();
	}
	
	private String getSignature(String appSecret, TreeMap<String, String> paramMap) {	
		try {
			if (paramMap == null) {
				return "";
			}
			
			StringBuilder combineString = new StringBuilder();
			combineString.append(appSecret);
			Set<Entry<String, String>> entrySet = paramMap.entrySet();
			
			for (Entry<String, String> entry : entrySet) {
				String key = entry.getKey();
				
				if (key != null && key.length() > 0) {					
					combineString.append(key);
					String vaule = entry.getValue();
					
					if (vaule != null && vaule.length() > 0) {
						combineString.append(vaule);
					}
				}
			}
			
			combineString.append(appSecret);

			byte[] bytesOfMessage = combineString.toString().getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			String signature = bytesToHexString(thedigest);
			
			return signature;		
		} catch (Exception e) {
			System.out.println(String.format("generate sign exception:%s",e.getMessage()));
			return "";
		}
	}

	private String bytesToHexString(byte[] src) {	
		try {
			StringBuilder stringBuilder = new StringBuilder("");
			
			if (src == null || src.length <= 0) {
				return null;
			}
			
			for (int i = 0; i < src.length; i++) {
				int v = src[i] & 0xFF;
				String hv = Integer.toHexString(v);
				
				if (hv.length() < 2) {
					stringBuilder.append(0);
				}
				
				stringBuilder.append(hv);
			}
			
			return stringBuilder.toString();		
		} catch (Exception e) {
			return null;
		}
	}
	
	private String sendRequest(String path, String query) {
//		System.out.println(query);
		StringBuilder result = new StringBuilder(); 
		URL url;
		HttpURLConnection conn;
		OutputStreamWriter out = null;
		BufferedReader rd;
		String line;     

		final int connectTimeout = 5000;
		final int readTimeout = 15000;
		
		try {
			url = new URL(path);
			conn = (HttpURLConnection) url.openConnection();
			
			if (conn instanceof HttpsURLConnection) {				
				HttpsURLConnection connHttps = (HttpsURLConnection) conn;
				SSLContext ctx = SSLContext.getInstance("TLSV1.2");
				ctx.init(null, new TrustManager[] { new TrustAllTrustManager() }, new java.security.SecureRandom());
				connHttps.setSSLSocketFactory(ctx.getSocketFactory());
				connHttps.setHostnameVerifier(new HostnameVerifier() {
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
				conn = connHttps;
			}
			
			conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);  
			conn.setRequestProperty("User-Agent", "ych-sdk-java");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(query);
			out.flush();
			
			InputStream urlStream = conn.getInputStream();
			rd = new BufferedReader(new InputStreamReader(urlStream));
			
			while ((line = rd.readLine()) != null) {
			  	result.append(line);
			}
			
			urlStream.close();
			rd.close();
		}
		catch (NoSuchAlgorithmException e) {
			return "NoSuchAlgorithmException";
		}
		catch (Exception e) {
			return e.getMessage();
		}
		finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}	
		}
		
		return result.toString();
	}
}
