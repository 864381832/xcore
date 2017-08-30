package com.xwintop.tool;

import java.lang.reflect.InvocationTargetException;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.ExpressionEvaluator;
import org.codehaus.janino.ScriptEvaluator;
import org.junit.Test;

/**
 * @ClassName: JaninoTest
 * @Description: Janino编译器测试类
 * @author: xufeng
 * @date: 2017年8月31日 上午1:15:49
 */
public class JaninoTest {
	@Test
	public void testExpressionEvaluator() throws CompileException, InvocationTargetException {
		// Now here's where the story begins...
		ExpressionEvaluator ee = new ExpressionEvaluator();
		// The expression will have two "int" parameters: "a" and "b".
		ee.setParameters(new String[] { "a", "b" }, new Class[] { int.class, int.class });
		// And the expression (i.e. "result") type is also "int".
		ee.setExpressionType(int.class);
		// And now we "cook" (scan, parse, compile and load) the fabulous expression.
		ee.cook("a + b");
		// Eventually we evaluate the expression - and that goes super-fast.
		int result = (Integer) ee.evaluate(new Object[] { 19, 23 });
		System.out.println(result);
	}
	
	@Test
	public void testExpressionEvaluator2() throws CompileException, InvocationTargetException {
		// Compile the expression once; relatively slow.
		ExpressionEvaluator ee = new ExpressionEvaluator(
		    "c > d ? c : d",                     // expression
		    int.class,                           // expressionType
		    new String[] { "c", "d" },           // parameterNames
		    new Class[] { int.class, int.class } // parameterTypes
		);
		 
		// Evaluate it with varying parameter values; very fast.
		Integer res = (Integer) ee.evaluate(
		    new Object[] {          // parameterValues
		        new Integer(10),
		        new Integer(11),
		    }
		);
		System.out.println("res = " + res);
	}
	
	@Test
	public void testScriptEvaluator() throws CompileException, NumberFormatException, InvocationTargetException {
        ScriptEvaluator se =new ScriptEvaluator();
        se.cook(
            ""
            +"static void method1() {\n"
            +"    System.out.println(1);\n"
            +"}\n"
            +"\n"
            +"method1();\n"
            +"method2();\n"
            +"\n"
            +"static void method2() {\n"
            +"    System.out.println(2);\n"
            +"}\n"
        );
        se.evaluate(new Object[0]);
    }
}
