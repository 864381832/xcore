package com.xwintop.xcore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.xwintop.xcore.base.Bill;
import com.xwintop.xcore.base.BillEntry;
import com.xwintop.xcore.util.SerializationObject;

/** 
 * @ClassName: KryoTest 
 * @Description: 序列号工具Kryo测试
 * @author: xufeng
 * @date: 2017年5月29日 下午3:30:45  
 */
public class KryoTest {
	@Test
	public void testKryoWriteObject() throws Exception {
		Kryo kryo = new Kryo();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
//		Output output = new Output(new FileOutputStream("file.bin"));
        List<Bill> list = new ArrayList<Bill>();
		Bill someObject = new Bill();
		someObject.setId("1");
		someObject.setName("xufeng许峰");
		someObject.setAuditor("许峰");
		someObject.setBizDate(new Date());
		list.add(someObject);
		kryo.writeObject(output, list);
		output.close();
		System.out.println(baos.toString("ISO-8859-1"));
		String string = new String(baos.toByteArray(),"ISO-8859-1");
		System.out.println(string);
//		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ByteArrayInputStream bais = new ByteArrayInputStream(string.getBytes("ISO-8859-1"));
		Input input = new Input(bais);
//		Bill someObject2 = kryo.readObject(input, Bill.class);
		List<Bill> someObject2 = kryo.readObject(input, ArrayList.class);
		System.out.println(someObject2.get(0).getId());
	}

	@Test
	public void testKryoReadObject() throws Exception {
		Kryo kryo = new Kryo();
		Input input = new Input(new FileInputStream("file.bin"));
		Bill someObject = kryo.readObject(input, Bill.class);
		System.out.println(someObject.getName());
		input.close();
	}
	
	@Test
	public void testKryoUtil() throws Exception {
		List<Bill> list = new ArrayList<Bill>();
		Bill someObject = new Bill();
		someObject.setName("许峰");
		list.add(someObject);
		
		String string = SerializationObject.serializationObject(list);
		System.out.println(string);
		List<Bill> sBills = SerializationObject.deserializationObject(string, ArrayList.class);
		System.out.println(sBills.get(0).getName());
		
	}
}
