package com.xwintop.xcore.java;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class JarFileTest {
	@Test
	public void test() throws Exception {
		JarFile jarFile = new JarFile("libs/flow-8.0.jar");
		System.out.println(jarFile.getName());
		Enumeration<JarEntry> enum1 = jarFile.entries();
		while (enum1.hasMoreElements()) {
			process(enum1.nextElement());
		}
		JarEntry entry = jarFile.getJarEntry("META-INF/MANIFEST.MF");
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
		String strings = IOUtils.toString(input, Charset.defaultCharset());
		System.out.println(strings);
	}

	@Test
	public void testLoadClass() throws Exception {
		/* 动态加载指定类 */
		File file = new File("libs/flow-8.0.jar");// 类路径(包文件上一层)
		URL url = file.toURI().toURL();
		@SuppressWarnings("resource")
		ClassLoader loader = new URLClassLoader(new URL[] { url });// 创建类加载器
		// import com.sun.org.apache.bcel.internal.util.ClassLoader;
		// ClassLoader classLoader = new ClassLoader(new String[]{""});//类路径
		Class<?> cls = loader.loadClass("io.datafx.controller.ViewConfiguration");// 加载指定类，注意一定要带上类的包名
		Object obj = cls.newInstance();// 初始化一个实例
		Method method = cls.getMethod("printString", String.class, String.class);// 方法名和对应的参数类型
		Object o = method.invoke(obj, "chen", "leixing");// 调用得到的上边的方法method
		System.out.println(String.valueOf(o));// 输出"chenleixing"

		/* 动态加载指定jar包调用其中某个类的方法 */
		file = new File("D:/test/commons-lang3.jar");// jar包的路径
		url = file.toURI().toURL();
		loader = new URLClassLoader(new URL[] { url });// 创建类加载器
		cls = loader.loadClass("org.apache.commons.lang3.StringUtils");// 加载指定类，注意一定要带上类的包名
		method = cls.getMethod("center", String.class, int.class, String.class);// 方法名和对应的各个参数的类型
		o = method.invoke(null, "chen", Integer.valueOf(10), "0");// 调用得到的上边的方法method(静态方法，第一个参数可以为null)
		System.out.println(String.valueOf(o));// 输出"000chen000","chen"字符串两边各加3个"0"字符串
	}

	/** 
	 * @Title: addJar 
	 * @Description: 添加jar包到系统classPath
	 */
	public void addJar() throws Exception {
		// 系统类库路径
		File libPath = new File("libs/");
		// 获取所有的.jar和.zip文件
		File[] jarFiles = libPath.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".jar") || name.endsWith(".zip");
			}
		});
		if (jarFiles != null) {
			// 从URLClassLoader类中获取类所在文件夹的方法
			// 对于jar文件，可以理解为一个存放class文件的文件夹
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			boolean accessible = method.isAccessible(); // 获取方法的访问权限
			try {
				if (accessible == false) {
					method.setAccessible(true); // 设置方法的访问权限
				}
				// 获取系统类加载器
				URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
				for (File file : jarFiles) {
					URL url = file.toURI().toURL();
					try {
						method.invoke(classLoader, url);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} finally {
				method.setAccessible(accessible);
			}
		}
	}
}
