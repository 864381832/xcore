package com.xwintop.xmytest.gdwsTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.log4j.Log4j;

@Log4j
public class TestQueryMap {
	/**
     * 请求接口获取数据
     * @param url       接口url
     * @param queryData 查询json串
     */
	
	@Test
    public void queryFromWS() throws Exception {
		Map map = QueryMapUtil.queryMap("{\"PARAM\":\"1\"}", "DPORTAL_COARRI", null);
		System.out.println(map);
    	String url = "http://192.168.131.243:7001/GDWS/GCCData?WSDL";
//        String queryData = "{\"GCCQuery\":{\"OrderBy\":{\"keyName\":{\"text\":\"\",\"attributes\":{\"type\":\"\"}}},\"Page\":{\"targetPage\":1,\"totalCount\":0,\"pageSize\":10},\"Info\":{\"QueryType\":\"DPORTAL_MONITOR_NEW\"},\"Criteria\":{\"Key\":[{\"name\":\"PARAM\",\"value\":\"223120160820626387\"},{\"name\":\"TRADE_CODE\",\"value\":\"\"},{\"name\":\"OWNER_CODE\",\"value\":\"\"},{\"name\":\"DECL_CODE\",\"value\":\"\"}]}}}";
//        String queryData = "{\"GCCQuery\":{\"OrderBy\":{\"keyName\":{\"text\":\"\",\"attributes\":{\"type\":\"\"}}},\"Page\":{\"targetPage\":1,\"totalCount\":0,\"pageSize\":10},\"Info\":{\"QueryType\":\"DPORTAL_COARRI\"},\"Criteria\":{\"Key\":[{\"name\":\"PARAM\",\"value\":\"COS9\"}]}}}";
    	String queryData = JSON.toJSONString(map);
    	System.out.println(queryData);
    	QueryMapUtil queryMapUtil = new QueryMapUtil();
        queryMapUtil.queryFromWS(url, queryData);
	}

	// /**
	// * 请求接口获取数据
	// *
	// * @param url 接口url
	// * @param queryData 查询json串
	// * @return
	// * @throws Exception
	// */
	// public Map<String, Object> queryFromWS(String url, String queryData)
	// throws Exception {
	// try {
	// long webBeginTime = System.currentTimeMillis();
	// request.setAttribute("web_before_date",webBeginTime);
	// // 构建WebService客户端实例
	// JaxWsDynamicClientFactory clientFactory =
	// JaxWsDynamicClientFactory.newInstance();
	// Client client = clientFactory.createClient(url);
	// // 设置超时单位为毫秒
	// HTTPConduit http = (HTTPConduit) client.getConduit();
	// HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
	// httpClientPolicy.setConnectionTimeout(30000); //连接超时
	// httpClientPolicy.setAllowChunking(false); //取消块编码
	// httpClientPolicy.setReceiveTimeout(30000); //响应超时
	// http.setClient(httpClientPolicy);
	// // 请求获取结果
	// long gdwsBeginTime = System.currentTimeMillis();
	// request.setAttribute("gdws_before_date",gdwsBeginTime);
	// Object[] result = client.invoke("queryData", "", "", "", queryData);
	// long gdwsEndTime = System.currentTimeMillis();
	// request.setAttribute("gdws_after_date",gdwsEndTime);
	// request.setAttribute("gdws_between_timestamp",gdwsEndTime-gdwsBeginTime);
	// log.info
	// ("*********请求gdws，返回结果后，共耗时 ***********："+(gdwsEndTime-gdwsBeginTime)+"
	// 毫秒");
	//
	// if (Utils.notNull(result) && Utils.notNull(result[0])) {
	// String retVal = (String) result[0];
	//// log.info
	//
	// ("调用接口获取业务数据成功,返回结果串:" + retVal);
	// // 获取界面显示数据列表
	// Map<String, Object> dataMap = (HashMap<String, Object>)
	// InterfaceTool.rtnMap(retVal, null);
	// if ("200".equals(dataMap.get("status").toString())) {
	// long webEndTime = System.currentTimeMillis();
	// request.setAttribute("web_after_date",webEndTime);
	// request.setAttribute("web_between_timestamp",webEndTime-webBeginTime);
	// log.info
	//
	// ("*************queryGDWS(包括构建webService客户端层) *************： 接口url="+url+"
	// 共耗时："+(webEndTime-webBeginTime)+" 毫秒");
	// return (HashMap<String, Object>) dataMap.get("data");
	// } else if ("500".equals(dataMap.get("status").toString())) {
	// throw new DataportalException(dataMap.get("message").toString());
	// } else {
	// throw new DataportalException("查询数据接口异常");
	// }
	// } else {
	// throw new DataportalException("GDWS无返回内容,查询数据接口异常");
	// }
	// } catch (DataportalException ex) {
	// throw ex;
	// } catch (Exception ex) {
	// log.error("CXF请求GDWS异常!", ex);
	// throw new DataportalException("CXF请求GDWS异常!");
	// }
	// }

}
