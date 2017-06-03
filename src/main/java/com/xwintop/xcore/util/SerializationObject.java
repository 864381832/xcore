package com.xwintop.xcore.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class SerializationObject {
	public static String serializationObject(Object obj) {
		Kryo kryo = new Kryo();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
		kryo.writeObject(output, obj);
		output.close();
		try {
//			String string = new String(baos.toByteArray(),"ISO-8859-1");
			return baos.toString("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
    }
 
	public static <T> T deserializationObject(String obj,Class<T> clazz) {
        Kryo kryo = new Kryo();
        ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(obj.getBytes("ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Input input = new Input(bais);
		return kryo.readObject(input, clazz);
    }
}
