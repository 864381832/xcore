package com.xwintop.xmytest.ych_sdk;

import java.util.Map;
import java.util.TreeMap;
import org.junit.Before;
import org.junit.Test;

import com.xwintop.xtest.ych_sdk.YchClient;

public class TestSample {
	private static String appkey = "68756936";
	private static String secret = "VH4UALEU9w09jBtNpQI2";
	private static String topAppkey = "23751881"; // 如果没有TOP appkey
	private static Map<String, String> defaultParam = new TreeMap<String, String>();

	@Before
	public void beforeTest()
    {
		defaultParam.put("appName", "金蝶ECC");
		defaultParam.put("ntime", String.valueOf(System.currentTimeMillis()));
		defaultParam.put("ati", "123456789123456789");
		defaultParam.put("userId", "tester");
//		defaultParam.put("userId", "xufeng1");
		defaultParam.put("userId", "shuixia1");
//		defaultParam.put("userId", "zhezhong");
//		defaultParam.put("userIp", "42.120.74.9");
//		defaultParam.put("userIp", "192.168.1.1");
		defaultParam.put("userIp", "101.95.183.230");
    }
	
	@Test//测试登录日志
	public void testLogin() {
		String url = "http://account.ose.aliyun.com/login";
		TreeMap<String, String> paramMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
    	paramMap.put("userId", defaultParam.get("userId"));
    	paramMap.put("userIp", defaultParam.get("userIp"));
    	paramMap.put("ati", defaultParam.get("ati"));
    	paramMap.put("topAppKey", topAppkey);
    	paramMap.put("appName", defaultParam.get("appName"));
    	paramMap.put("tid", defaultParam.get("userId"));
    	paramMap.put("loginResult", "success");
    	paramMap.put("loginMessage", "附加信息");
		paramMap.put("time", defaultParam.get("ntime"));
		paramMap.put("appKey", appkey);
		YchClient sdk = new YchClient(appkey, secret);
		String rsp = sdk.send(url, paramMap);
		System.out.println(rsp);
	}
	
	@Test//测试风险计算
	public void testComputeRisk() {
		String url = "http://account.ose.aliyun.com/computeRisk";
		TreeMap<String, String> paramMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
    	paramMap.put("userId", defaultParam.get("userId"));
    	paramMap.put("userIp", defaultParam.get("userIp"));
    	paramMap.put("ati", defaultParam.get("ati"));
    	paramMap.put("appId", topAppkey);
    	paramMap.put("appName", defaultParam.get("appName"));
		paramMap.put("time", defaultParam.get("ntime"));
		paramMap.put("appKey", appkey);
		YchClient sdk = new YchClient(appkey, secret);
		String rsp = sdk.send(url, paramMap);
		System.out.println(rsp);
	}
	
	@Test//测试获取二次验证地址
	public void testGetVerifyUrl() {
		String url = "http://account.ose.aliyun.com/getVerifyUrl";
		TreeMap<String, String> paramMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
    	paramMap.put("sessionId", "123456789123456789");
    	paramMap.put("mobile", "18356971618");
    	paramMap.put("redirectURL", "http://116.228.31.181:9001/Mentor/dashboard.jsp");
    	paramMap.put("userId", defaultParam.get("userId"));
    	paramMap.put("userIp", defaultParam.get("userIp"));
    	paramMap.put("ati", defaultParam.get("ati"));
    	paramMap.put("appId", topAppkey);
    	paramMap.put("appName", defaultParam.get("appName"));
		paramMap.put("time", defaultParam.get("ntime"));
		paramMap.put("appKey", appkey);
		YchClient sdk = new YchClient(appkey, secret);
		String rsp = sdk.send(url, paramMap);
		System.out.println(rsp);
	}
	
	@Test//测试验证通过
	public void testIsVerifyPassed() {
		String url = "http://account.ose.aliyun.com/isVerifyPassed";
		TreeMap<String, String> paramMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
		paramMap.put("token", "66B3BE0BB0C6CDC021A992555120A285");
		paramMap.put("time", defaultParam.get("ntime"));
		paramMap.put("appKey", appkey);
		YchClient sdk = new YchClient(appkey, secret);
		String rsp = sdk.send(url, paramMap);
		System.out.println(rsp);
	}
	
	@Test//测试风险重置
	public void testResetRisk() {
		String url = "http://account.ose.aliyun.com/resetRisk";
		TreeMap<String, String> paramMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
		paramMap.put("userId", defaultParam.get("userId"));
    	paramMap.put("userIp", defaultParam.get("userIp"));
    	paramMap.put("ati", defaultParam.get("ati"));
    	paramMap.put("appId", topAppkey);
    	paramMap.put("appName", defaultParam.get("appName"));
		paramMap.put("time", defaultParam.get("ntime"));
		paramMap.put("appKey", appkey);
		YchClient sdk = new YchClient(appkey, secret);
		String rsp = sdk.send(url, paramMap);
		System.out.println(rsp);
	}

}
