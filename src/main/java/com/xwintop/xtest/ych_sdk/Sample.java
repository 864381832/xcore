package com.xwintop.xtest.ych_sdk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Sample 
{
	private static String appkey = "68756936";
	
	private static String secret = "VH4UALEU9w09jBtNpQI2";
	
	private static String topAppkey = "23751881"; // 如果没有TOP appkey，可以不填，样例代码中会使用御城河appkey代替topAppkey
	
	private static Map<String, String> defaultParam = new TreeMap<String, String>();
	
    public static void main( String[] args )
    {
    	if (appkey.isEmpty() || secret.isEmpty()) {
    		System.out.println("appkey or secret is empty.");
    		return;
    	}
    	
    	if (topAppkey.isEmpty()) {
    		topAppkey = appkey;
    	}
    	
    	SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String time = sFormat.format(now);

		defaultParam.put("appName", "我的测试应用");
		defaultParam.put("time", time);
		defaultParam.put("ntime", new Long(now.getTime()).toString());
		defaultParam.put("ati", "123456789123456789");
		defaultParam.put("userId", "tester");
		defaultParam.put("userIp", "42.120.74.9");
		
		// 单条日志上传接口示例
		testLoginMethod();
		// 批量上传接口示例
//		testBatchMethod();
    }
    
    private static void parseResult(String rsponse) {
    	String result = "";	
		String errMsg = "";
		
		if (rsponse != null) {
			JSONObject obj = null;
			
			try {
				obj = (JSONObject) JSON.parse(rsponse);
			} catch (Exception e) {			
			}
			
			if (obj != null) {
				result = obj.getString("result");
				errMsg = obj.getString("errMsg");
			}
		}
		
		if ("success".equals(result) && (errMsg == null || errMsg.isEmpty())) {
			System.out.println("Success.");
		} else {
			System.out.println("Error:" + rsponse);
		}
    }
    
    private static void testLoginMethod() {
    	String url = "http://account.ose.aliyun.com/login";
    	TreeMap<String, String> param = constructLoginParam();		
		YchClient sdk = new YchClient(appkey, secret);
		String rsp = sdk.send(url, param);
		System.out.println(rsp);
		parseResult(rsp);
    }
    
    private static TreeMap<String, String> constructLoginParam() {	
    	TreeMap<String, String> paramMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

		paramMap.put("time", defaultParam.get("ntime"));
		paramMap.put("appKey", appkey);
		paramMap.put("ati", defaultParam.get("ati"));
		paramMap.put("userId", defaultParam.get("userId"));
		paramMap.put("tid", defaultParam.get("userId"));
		paramMap.put("userIp", defaultParam.get("userIp"));
		paramMap.put("topAppKey", topAppkey);
		paramMap.put("appName", defaultParam.get("appName"));
		paramMap.put("loginResult", "success");
		paramMap.put("loginMessage", "附加信息");

		return paramMap;
	}
    
    private static void testBatchMethod() {
    	String url = "http://gw.ose.aliyun.com/event/batchLog/";
		Map<String, String> param = constructOrderParam();
		
		List<Map<String, String>> paramList = new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < 5; i++) {
			paramList.add(param);
		}		
		
		JSONArray jsonObj = (JSONArray) JSONArray.toJSON(paramList);
		TreeMap<String, String> paramNew = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
		paramNew.put("topAppkey", topAppkey);
		paramNew.put("data", jsonObj.toString());
		paramNew.put("method", "order");
		paramNew.put("format", "json");
		paramNew.put("appkey", appkey);
		paramNew.put("time", defaultParam.get("ntime"));
		
		YchClient sdk = new YchClient(appkey, secret);
		String rsp = sdk.send(url, paramNew);
		parseResult(rsp);
    }
    
    private static Map<String, String> constructOrderParam() {
		
		Map<String, String> paramMap = new TreeMap<String, String>();
		
		paramMap.put("time", defaultParam.get("ntime"));
		paramMap.put("ati", defaultParam.get("ati"));
		paramMap.put("userId", defaultParam.get("userId"));
		paramMap.put("tid", defaultParam.get("userId"));
		paramMap.put("userIp", defaultParam.get("userIp"));
		paramMap.put("appName", defaultParam.get("appName"));
		paramMap.put("url", "https://www.taobao.com");
		paramMap.put("tradeIds", "123456789012345;123456789012346;12345678901234");
		paramMap.put("operation", "query");
		
		return paramMap;
	}
}
