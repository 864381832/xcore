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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.log4j.Log4j;

@Log4j
public class QueryMapUtil {
	private static Gson gson = new Gson();

	public static Map queryMap(String reqJson, String queryType, Map<String, String> replaceFieldMap) {
		// 是否分页
		boolean isPagination = false;
		Map<String, String> reqMap = gson.fromJson(reqJson, new TypeToken<Map<String, String>>() {
		}.getType());
		Map queryMap = new HashMap();
		Map gccQueryMap = new HashMap();
		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("QueryType", queryType);
		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		Map<String, List<Map<String, String>>> criteriaMap = new HashMap<String, List<Map<String, String>>>();
		List<Map<String, String>> keyList = new ArrayList<Map<String, String>>();
		queryMap.put("GCCQuery", gccQueryMap);
		gccQueryMap.put("Info", infoMap);
		gccQueryMap.put("Criteria", criteriaMap);
		criteriaMap.put("Key", keyList);
		for (Map.Entry<String, String> entry : reqMap.entrySet()) {
			if ("page".equals(entry.getKey())) {
				pageMap.put("targetPage", Integer.valueOf(entry.getValue()));
				isPagination = true;
			} else if ("rows".equals(entry.getKey())) {
				pageMap.put("pageSize", Integer.valueOf(entry.getValue()));
				isPagination = true;
			} else if ("type".equals(entry.getKey()) || "text".equals(entry.getKey())) {

			} else {
				Map<String, String> keyMap = new HashMap<String, String>();
				String strKey = entry.getKey();
				if (replaceFieldMap != null && replaceFieldMap.get(strKey) != null
						&& !"".equals(replaceFieldMap.get(strKey))) {
					strKey = replaceFieldMap.get(strKey);
				}
				keyMap.put("name", strKey);
				// sql条件过滤
				if ("('N','Y')".equals(entry.getValue()) || "('N')".equals(entry.getValue())) {
					keyMap.put("value", entry.getValue());
				} else {
					keyMap.put("value", StringEscapeUtils.escapeSql(entry.getValue()));// entry.getValue().toUpperCase()
				}
				keyList.add(keyMap);
			}
		}
		// totalCount值为pageSize*(targetPage-1)
		// 如果分页加入分页参数
		if (isPagination) {
			pageMap.put("totalCount", pageMap.get("pageSize").intValue() * (pageMap.get("targetPage").intValue() - 1));
			gccQueryMap.put("Page", pageMap);
		}
		Map sortMap1 = new HashMap();
		Map sortMap2 = new HashMap();
		Map sortMap3 = new HashMap();
		sortMap3.put("type", reqMap.get("type"));
		sortMap2.put("attributes", sortMap3);
		sortMap2.put("text", reqMap.get("text"));
		sortMap1.put("keyName", sortMap2);
		gccQueryMap.put("OrderBy", sortMap1);

		return queryMap;
	}

	/**
     * 请求接口获取数据
     *
     * @param url       接口url
     * @param queryData 查询json串
     * @return
     * @throws Exception
     */
	
	@Test
    public Map<String, Object> queryFromWS(String url, String queryData) throws Exception {
        try {
        	long webBeginTime = System.currentTimeMillis();
            // 构建WebService客户端实例
            JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
            Client client = clientFactory.createClient(url);
            // 设置超时单位为毫秒
            HTTPConduit http = (HTTPConduit) client.getConduit();
            HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
            httpClientPolicy.setConnectionTimeout(30000);   //连接超时
            httpClientPolicy.setAllowChunking(false);   //取消块编码
            httpClientPolicy.setReceiveTimeout(30000);  //响应超时
            http.setClient(httpClientPolicy);
            // 请求获取结果
            long gdwsBeginTime = System.currentTimeMillis();
            Object[] result = client.invoke("queryData", "", "", "", queryData);
            long gdwsEndTime = System.currentTimeMillis();
            log.info("*********请求gdws，返回结果后，共耗时 ***********："+(gdwsEndTime-gdwsBeginTime)+" 毫秒");
            if (Utils.notNull(result) && Utils.notNull(result[0])) {
                String retVal = (String) result[0];
                log.info("调用接口获取业务数据成功,返回结果串:" + retVal);
                // 获取界面显示数据列表
                Map<String, Object> dataMap = (HashMap<String, Object>) InterfaceTool.rtnMap(retVal, null);
                if ("200".equals(dataMap.get("status").toString())) {
                	long webEndTime = System.currentTimeMillis();
                	log.info ("*************queryGDWS(包括构建webService客户端层) *************： 接口url="+url+" 共耗时："+(webEndTime-webBeginTime)+" 毫秒");
                    return (HashMap<String, Object>) dataMap.get("data");
                } else if ("500".equals(dataMap.get("status").toString())) {
//                    throw new DataportalException(dataMap.get("message").toString());
                } else {
//                    throw new DataportalException("查询数据接口异常");
                }
            } else {
//                throw new DataportalException("GDWS无返回内容,查询数据接口异常");
            }
//        } catch (DataportalException ex) {
//            throw ex;
        } catch (Exception ex) {
            log.error("CXF请求GDWS异常!", ex);
//            throw new DataportalException("CXF请求GDWS异常!");
        }
		return null;
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
