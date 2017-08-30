package com.xwintop.xcore.java;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class JavaToolsTest {
	public static void executeMain(String name, String content) {
		Class<?> claszz = compile(name, content);
		try {
			Method method = claszz.getMethod("main", String[].class);
			System.out.println(method.getName());
			method.invoke(null, new Object[] { new String[] {} });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final static Class<?> compile(String name, String content) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		StrSrcJavaObject srcObject = new StrSrcJavaObject(name, content);
		Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(srcObject);
		String flag = "-d";
		String outDir = "";
		try {
			File classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
			outDir = classPath.getAbsolutePath() + File.separator;
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		Iterable<String> options = Arrays.asList(flag, outDir);
		CompilationTask task = compiler.getTask(null, fileManager, null, options, null, fileObjects);
		boolean result = task.call();
		if (result == true) {
			System.out.println("Compile it successfully.");
			try {
				return Class.forName(name);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static class StrSrcJavaObject extends SimpleJavaFileObject {
		private String content;

		public StrSrcJavaObject(String name, String content) {
			super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
			this.content = content;
		}

		public CharSequence getCharContent(boolean ignoreEncodingErrors) {
			return content;
		}
	}

	public static void main(String[] args) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("package junit.test;");
		sb.append("public class Test1{");
		sb.append("  public static void main(String[] args){");
		sb.append("    System.out.println(\"ok.\");");
		sb.append("  }");
		sb.append("}");

		executeMain("junit.test.Test1", sb.toString());
	}
}