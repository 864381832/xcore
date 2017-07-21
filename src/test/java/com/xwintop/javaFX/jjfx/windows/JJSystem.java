/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.jjfx.windows;

import java.util.ArrayList;

public class JJSystem {
	private static final String USER_DIR = "user.dir";
	private static final String USER_NAME = "user.name";
	private static final String USER_HOME = "user.home";
	private static final String JAVA_HOME = "java.home";
	private static final String TEMP_DIR = "java.io.tmpdir";
	private static final String OS_NAME = "os.name";
	private static final String OS_VERSION = "os.version";
	private static final String JAVA_VERSION = "java.version";
	private static final String JAVA_SPECIFICATION_VERSION = "java.specification.version";
	private static final String JAVA_VENDOR = "java.vendor";
	private static final String JAVA_CLASSPATH = "java.class.path";
	private static final String PATH_SEPARATOR = "path.separator";
	private static final String HTTP_PROXY_HOST = "http.proxyHost";
	private static final String HTTP_PROXY_PORT = "http.proxyPort";
	private static final String HTTP_PROXY_USER = "http.proxyUser";
	private static final String HTTP_PROXY_PASSWORD = "http.proxyPassword";
	private static final String FILE_ENCODING = "file.encoding";
	private static final String SUN_BOOT_CLASS_PATH = "sun.boot.class.path";
	private static int javaVersionNumber;
	private static String[] jrePackages;

	public static String[] jrePackages() {
		if (jrePackages == null) {
			JJSystem.buildJrePackages();
		}
		return jrePackages;
	}

	private static void buildJrePackages() {
		ArrayList<String> packages = new ArrayList<String>();
		switch (javaVersionNumber) {
			case 15 :
			case 16 :
			case 17 :
			case 18 : {
				packages.add("com.sun.org.apache");
			}
			case 14 : {
				if (javaVersionNumber == 14) {
					packages.add("org.apache.crimson");
					packages.add("org.apache.xalan");
					packages.add("org.apache.xml");
					packages.add("org.apache.xpath");
				}
				packages.add("org.ietf.jgss");
				packages.add("org.w3c.dom");
				packages.add("org.xml.sax");
			}
			case 13 : {
				packages.add("org.omg");
				packages.add("com.sun.corba");
				packages.add("com.sun.jndi");
				packages.add("com.sun.media");
				packages.add("com.sun.naming");
				packages.add("com.sun.org.omg");
				packages.add("com.sun.rmi");
				packages.add("sunw.io");
				packages.add("sunw.util");
			}
			case 12 : {
				packages.add("com.sun.java");
				packages.add("com.sun.image");
			}
		}
		packages.add("sun");
		packages.add("java");
		packages.add("javax");
		jrePackages = packages.toArray(new String[packages.size()]);
	}

	public static String userDir() {
		return System.getProperty("user.dir");
	}

	public static String userName() {
		return System.getProperty("user.name");
	}

	public static String userHome() {
		return System.getProperty("user.home");
	}

	public static String javaJreHome() {
		return System.getProperty("java.home");
	}

	public static String javaHome() {
		String home = System.getProperty("java.home");
		if (home == null) {
			return null;
		}
		int i = home.lastIndexOf(92);
		int j = home.lastIndexOf(47);
		if (j > i) {
			i = j;
		}
		return home.substring(0, i);
	}

	public static String tempDir() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String osName() {
		return System.getProperty("os.name");
	}

	public static String osVersion() {
		return System.getProperty("os.version");
	}

	public static String javaVersion() {
		return System.getProperty("java.version");
	}

	public static String javaSpecificationVersion() {
		return System.getProperty("java.specification.version");
	}

	public static int javaVersionNumber() {
		return javaVersionNumber;
	}

	public static String javaVendor() {
		return System.getProperty("java.vendor");
	}

	public static boolean isAtLeastJavaVersion(int version) {
		return javaVersionNumber >= version;
	}

	public static boolean isJavaVersion(int version) {
		return javaVersionNumber == version;
	}

	public static String systemClassPath() {
		return System.getProperty("java.class.path");
	}

	public static String pathSeparator() {
		return System.getProperty("path.separator");
	}

	public static String fileEncoding() {
		return System.getProperty("file.encoding");
	}

	public static boolean isHostWindows() {
		return JJSystem.osName().toUpperCase().startsWith("WINDOWS");
	}

	public static boolean isHostLinux() {
		return JJSystem.osName().toUpperCase().startsWith("LINUX");
	}

	public static boolean isHostMac() {
		return JJSystem.osName().toUpperCase().startsWith("MAC OS X");
	}

	public static boolean isHostSolaris() {
		return JJSystem.osName().toUpperCase().startsWith("SUNOS");
	}

	public static boolean isHostAix() {
		return JJSystem.osName().equalsIgnoreCase("AIX");
	}

	public static String getSunBootClassPath() {
		return System.getProperty("sun.boot.class.path");
	}

	public static void setHttpProxy(String host, String port, String username, String password) {
		System.getProperties().put("http.proxyHost", host);
		System.getProperties().put("http.proxyPort", port);
		System.getProperties().put("http.proxyUser", username);
		System.getProperties().put("http.proxyPassword", password);
	}

	public static void setHttpProxy(String host, String port) {
		System.getProperties().put("http.proxyHost", host);
		System.getProperties().put("http.proxyPort", port);
	}

	static {
		try {
			javaVersionNumber = 10;
			Class.forName("java.lang.Void");
			++javaVersionNumber;
			Class.forName("java.lang.ThreadLocal");
			++javaVersionNumber;
			Class.forName("java.lang.StrictMath");
			++javaVersionNumber;
			Class.forName("java.lang.CharSequence");
			++javaVersionNumber;
			Class.forName("java.net.Proxy");
			++javaVersionNumber;
			Class.forName("java.net.CookieStore");
			++javaVersionNumber;
			Class.forName("java.nio.file.FileSystem");
			++javaVersionNumber;
			Class.forName("java.lang.reflect.Executable");
			++javaVersionNumber;
		} catch (Throwable var0) {
			// empty catch block
		}
	}
}