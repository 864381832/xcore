package com.xwintop.xmytest.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Test;

public class AnnotationParsing {
	@Test
	public void testMain() throws Exception {
		for (Method method : AnnotationParsing.class.getClassLoader()
				.loadClass(("com.xwintop.xmytest.annotation.AnnotationExample")).getMethods()) {
			// checks if MethodInfo annotation is present for the method
			if (method.isAnnotationPresent(MethodInfo.class)) {
				try {
					// iterates all the annotations available in the method
					for (Annotation anno : method.getDeclaredAnnotations()) {
						System.out.println("Annotation in Method '" + method + "' : " + anno);
					}
					MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
					if (methodAnno.revision() == 1) {
						System.out.println("Method with revision no 1 = " + method);
					}

				} catch (Throwable ex) {
					ex.printStackTrace();
				}
			}
		}
	}

}
