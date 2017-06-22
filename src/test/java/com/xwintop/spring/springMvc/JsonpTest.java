package com.xwintop.spring.springMvc;

import org.junit.Test;
import org.springframework.http.converter.json.MappingJacksonValue;

public class JsonpTest {

	@Test
	public void callbackObject(){
		String callback = "run";
		Object value = new Object();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(value);
		mappingJacksonValue.setJsonpFunction(callback);
		System.out.println(mappingJacksonValue.toString());
	}
	
	public Object callbackObject(String callback){
		Object value = new Object();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(value);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}
