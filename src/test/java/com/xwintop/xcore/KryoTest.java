package com.xwintop.xcore;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Test;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.xwintop.xcore.base.Bill;

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
		Output output = new Output(new FileOutputStream("file.bin"));
		Bill someObject = new Bill();
		someObject.setName("xufeng");
		kryo.writeObject(output, someObject);
		output.close();
	}

	@Test
	public void testKryoReadObject() throws Exception {
		Kryo kryo = new Kryo();
		Input input = new Input(new FileInputStream("file.bin"));
		Bill someObject = kryo.readObject(input, Bill.class);
		System.out.println(someObject.getName());
		input.close();
	}
}
