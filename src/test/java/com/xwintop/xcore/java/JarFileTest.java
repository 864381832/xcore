package com.xwintop.xcore.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.junit.Test;

public class JarFileTest {
	@Test
	public void test() throws Exception {
		JarFile jarFile = new JarFile("libs/xcore.jar");
		System.out.println(jarFile.getName());
		Enumeration<JarEntry> enum1 = jarFile.entries();
		while (enum1.hasMoreElements()) {
			process(enum1.nextElement());
		}
		JarEntry entry = jarFile.getJarEntry("META-INF/maven/com.xwintop/xcore/pom.properties");
//		JarEntry entry = jarFile.getJarEntry("logback.xml");
	    InputStream input = jarFile.getInputStream(entry);
	    process(input);
	    jarFile.close();
	}

	public void process(Object obj) {
		JarEntry entry = (JarEntry) obj;
		String name = entry.getName();
		long size = entry.getSize();
		long compressedSize = entry.getCompressedSize();
		System.out.println(name + " " + size + " " + compressedSize);
	}

	public void process(InputStream input) throws IOException {
		InputStreamReader isr = new InputStreamReader(input);
		BufferedReader reader = new BufferedReader(isr);
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();
	}
}
