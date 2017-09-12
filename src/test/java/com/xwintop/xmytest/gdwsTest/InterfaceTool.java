package com.xwintop.xmytest.gdwsTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings ( { "unchecked", "rawtypes" } )
public class InterfaceTool {
//    private static Gson gson = new Gson();
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	public static Map queryMap(String reqJson, String queryType, Map<String, String> replaceFieldMap) {
        // 是否分页
        boolean isPagination = false;
        Map<String, String> reqMap = gson.fromJson(reqJson,
                new TypeToken<Map<String, String>>() {
                }.getType());
        Map queryMap = new HashMap();
        Map gccQueryMap = new HashMap();
        Map<String, String> infoMap = new HashMap<String, String>();
        infoMap.put("QueryType", queryType);
        Map<String, Integer> pageMap = new HashMap<String, Integer>();
        Map<String, List<Map<String, String>>> criteriaMap = new HashMap<String, List<Map<String,
                String>>>();
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
            }else if("type".equals(entry.getKey()) || "text".equals(entry.getKey())){

            }else {
                Map<String, String> keyMap = new HashMap<String, String>();
                String strKey = entry.getKey();
                if (replaceFieldMap != null
                        && replaceFieldMap.get(strKey) != null
                        && !"".equals(replaceFieldMap.get(strKey))) {
                    strKey = replaceFieldMap.get(strKey);
                }
                keyMap.put("name", strKey);
                //sql条件过滤
                if("('N','Y')".equals(entry.getValue()) || "('N')".equals(entry.getValue())){
                	 keyMap.put("value", entry.getValue());
                }else{
                	keyMap.put("value", StringEscapeUtils.escapeSql(entry.getValue()));//entry.getValue().toUpperCase()
                }
                keyList.add(keyMap);
            }
        }
        // totalCount值为pageSize*(targetPage-1)
        // 如果分页加入分页参数
        if (isPagination) {
            pageMap.put("totalCount", pageMap.get("pageSize").intValue()
                    * (pageMap.get("targetPage").intValue() - 1));
            gccQueryMap.put("Page", pageMap);
        }
        Map sortMap1 = new HashMap();
        Map sortMap2 = new HashMap();
        Map sortMap3 = new HashMap();
        sortMap3.put("type",reqMap.get("type"));
        sortMap2.put("attributes",sortMap3);
        sortMap2.put("text",reqMap.get("text"));
        sortMap1.put("keyName",sortMap2);
        gccQueryMap.put("OrderBy",sortMap1);

        return queryMap;
    }

    /*public static Map rtnMap(String resultStr, List<String> dateFormatList) {
        Map rtnMap = new HashMap();
        rtnMap.put("status", HttpStatus.OK.value());
        Map rtnBodyMap = new HashMap();
        List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
        rtnBodyMap.put("rows", rowsList);
        Map resultMap = gson.fromJson((String) resultStr, new TypeToken<Map>() {
        }.getType());
        if (resultMap.get("GCCResult") != null
                && "0".equals(((Map) (resultMap.get("GCCResult"))).get("@code"))) {
            List setsList = resultMap.get("GCCResult") != null ? (List) ((Map) (resultMap
                    .get("GCCResult"))).get("sets") : null;
            if (setsList != null) {
                for (Object fields : setsList) {
                    Map rowMap = new LinkedHashMap();
                    List<Map<String, String>> fieldsList = (List<Map<String, String>>) ((Map)
							fields)
                            .get("fields");
                    for (Map<String, String> tmpM : fieldsList) {
                        if (dateFormatList != null
                                && dateFormatList.contains(tmpM.get("name"))) {
                            if (tmpM.get("value").indexOf(".") >= 0) {
                                rowMap.put(
                                        tmpM.get("name"),
                                        tmpM.get("value").substring(0,
                                                tmpM.get("value").indexOf(".")));
                            } else {
                                rowMap.put(tmpM.get("name"), tmpM.get("value"));
                            }
                        } else {
                            rowMap.put(tmpM.get("name"), StringUtils.replaceBlank(tmpM.get("value")));
                        }
                    }
                    rowsList.add(rowMap);
                }
            }
            Map totalMap = resultMap.get("GCCResult") != null ? (Map) ((Map) (resultMap
                    .get("GCCResult"))).get("Page") : null;
            if (totalMap != null) {
                rtnBodyMap.put("total", (String) totalMap.get("totalCount"));
            }
        } else {
            rtnMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            rtnMap.put(
                    "message",
                    "gdws error:"
                            + (String) ((Map) (resultMap.get("GCCResult")))
                            .get("@desc"));
        }

        rtnMap.put("data", rtnBodyMap);
        return rtnMap;
    }*/

	public static void returnMap(String resultStr){
		JSONObject mainJSONObject = JSON.parseObject(resultStr);
		if(StringUtils.isNotEmpty(mainJSONObject.getString("GCCResult"))){
			JSONObject GCCResult = mainJSONObject.getJSONObject("GCCResult");
			if(GCCResult.getInteger("@code") == 0){
				JSONArray sets = GCCResult.getJSONArray("sets");
				if(!sets.isEmpty()){
					for(Object jSONObject:sets){
						JSONArray fields = ((JSONObject) jSONObject).getJSONArray("fields");
						int fieldsSize = fields.size();
						if (fieldsSize > 0) {
							for(int i = 0;i<fieldsSize;i++){
//								JSONObject field = fields.getJSONObject(i);
								Map fieldMap = fields.getObject(i, Map.class);
								System.out.println(fieldMap);
							}
						}
					}
				}
			}
		}
	}
	
    public static Map rtnMap(String resultStr, List<String> excludeList) {
        Map rtnMap = new HashMap();
        rtnMap.put("status", HttpStatus.OK.value());
        Map rtnBodyMap = new HashMap();
        List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
        rtnBodyMap.put("rows", rowsList);
        Map resultMap = gson.fromJson((String) resultStr, new TypeToken<Map>() {
        }.getType());
        if (resultMap.get("GCCResult") != null
                && "0".equals(((Map) (resultMap.get("GCCResult"))).get("@code"))) {
            List setsList = resultMap.get("GCCResult") != null ? (List) ((Map) (resultMap
                    .get("GCCResult"))).get("sets") : null;
            if (setsList != null) {
                for (Object fields : setsList) {
                    Map rowMap = new LinkedHashMap();
                    List<Map<String, String>> fieldsList = (List<Map<String, String>>) ((Map)
                            fields)
                            .get("fields");
                    for (Map<String, String> tmpM : fieldsList) {
                        //如果有排除字段,直接跳过进入下一次循环
                        if (Utils.notNull(excludeList) && excludeList.size() > 0
                                && excludeList.contains(tmpM.get("name"))) {
                            continue;
                        } else {
                            //过滤数据中的制表符和换行符
//                            rowMap.put(tmpM.get("name"), StringUtils.replaceBlank(tmpM.get("value")));
                        }
                    }
                    rowsList.add(rowMap);
                }
            }

            Map totalMap = resultMap.get("GCCResult") != null ?
                    (Map) ((Map) (resultMap.get("GCCResult"))).get("Page") : null;

            if (totalMap != null) {
                rtnBodyMap.put("total", (String) totalMap.get("totalCount"));
            }
        } else {
            rtnMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            rtnMap.put(
                    "message",
                    "gdws error:"
                            + (String) ((Map) (resultMap.get("GCCResult")))
                            .get("@desc"));
        }
        rtnMap.put("data", rtnBodyMap);
        return rtnMap;
    }
}
