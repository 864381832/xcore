package com.xwintop.xcore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.esotericsoftware.kryo.serializers.MapSerializer;
import com.xwintop.xcore.base.Bill;
 
public class KryoTest2 {
 
    private long time;
 
    @Before
    public void beforeTest() {
        time = System.currentTimeMillis();
    }
 
    @After
    public void afterTest() {
        System.out.println(System.currentTimeMillis() - time);
    }
 
    @Test
    public void testObject() {
    	Bill val = new Bill();
        val.setId(String.valueOf(1));
        val.setName("许峰");
        String a = serializationObject(val);
        Bill newValue = deserializationObject(a, Bill.class);
        Assert.assertEquals(val.getId(), newValue.getId());
    }
 
    private <T extends Serializable> String serializationObject(T obj) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(obj.getClass(), new JavaSerializer());
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output, obj);
        output.flush();
        output.close();
 
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(new Base64().encode(b));
    }
 
    @SuppressWarnings("unchecked")
    private <T extends Serializable> T deserializationObject(String obj,
            Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(clazz, new JavaSerializer());
 
        ByteArrayInputStream bais = new ByteArrayInputStream(
                new Base64().decode(obj));
        Input input = new Input(bais);
        return (T) kryo.readClassAndObject(input);
    }
 
    @SuppressWarnings("unused")
	private <T extends Serializable> String serializationList(List<T> obj,
            Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
 
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return new String(new Base64().encode(b));
    }
 
    @SuppressWarnings({ "unchecked", "unused" })
    private <T extends Serializable> List<T> deserializationList(String obj,
            Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);
 
        ByteArrayInputStream bais = new ByteArrayInputStream(
                new Base64().decode(obj));
        Input input = new Input(bais);
        return (List<T>) kryo.readObject(input, ArrayList.class, serializer);
    }
 
    @SuppressWarnings("unused")
	private <T extends Serializable> String serializationMap(
            Map<String, T> obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        MapSerializer serializer = new MapSerializer();
        serializer.setKeyClass(String.class, new JavaSerializer());
        serializer.setKeysCanBeNull(false);
        serializer.setValueClass(clazz, new JavaSerializer());
        serializer.setValuesCanBeNull(true);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashMap.class, serializer);
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
 
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return new String(new Base64().encode(b));
    }
 
    @SuppressWarnings({ "unchecked", "unused" })
    private <T extends Serializable> Map<String, T> deserializationMap(
            String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        MapSerializer serializer = new MapSerializer();
        serializer.setKeyClass(String.class, new JavaSerializer());
        serializer.setKeysCanBeNull(false);
        serializer.setValueClass(clazz, new JavaSerializer());
        serializer.setValuesCanBeNull(true);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashMap.class, serializer);
 
        ByteArrayInputStream bais = new ByteArrayInputStream(
                new Base64().decode(obj));
        Input input = new Input(bais);
        return (Map<String, T>) kryo.readObject(input, HashMap.class,
                serializer);
    }
 
    public static <T extends Serializable> String serializationSet(Set<T> obj,
            Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashSet.class, serializer);
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
 
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return new String(new Base64().encode(b));
    }
 
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> Set<T> deserializationSet(
            String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashSet.class, serializer);
 
        ByteArrayInputStream bais = new ByteArrayInputStream(
                new Base64().decode(obj));
        Input input = new Input(bais);
        return (Set<T>) kryo.readObject(input, HashSet.class, serializer);
    }
}
