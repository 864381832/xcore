package com.xwintop.xmytest.kaola;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestKaoLa {
	@Test
	public void testKaoLa() {
//		HttpGetUtil.getHttpData("http://1.202.150.5:9080/as/authentication.do?_t=json&customerId=123&reqData=123&prdGrpId=icLawQuery&prdId=qryPersonalInvest&sign=123", null);
//		HttpGetUtil.getHttpData("http://www.baidu.com", null);
//		HttpGetUtil.getHttpDataByPost("http://www.baidu.com", null,null);
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("idCardCode", "340827199310086931");
		jsonMap.put("customerId", "123");
		jsonMap.put("prdGrpId", "icLawQuery");
		jsonMap.put("prdId", "qryPersonalInvest");
		jsonMap.put("reqData", "123");
		jsonMap.put("sign", "123");
		HttpGetUtil.getHttpDataByPost("http://1.202.150.5:9080/as/authentication.do?_t=json", null,jsonMap);
//		HttpGetUtil.getHttpDataByPost("http://1.202.150.5:9080/as/authentication.do?_t=json", null,jsonMap);
//		HttpGetUtil.getHttpDataByPost("http://www.baidu.com", null,jsonMap);
	}
}
