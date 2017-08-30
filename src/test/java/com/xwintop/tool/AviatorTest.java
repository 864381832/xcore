package com.xwintop.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;

public class AviatorTest {
	@Test
	public void testexecute() {
		Long result = (Long) AviatorEvaluator.execute("1+2+3");
		System.out.println(result);
	}

	@Test
	public void testObject() {
		String yourName = "Michael";
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("yourName", yourName);
		String result = (String) AviatorEvaluator.execute(" 'hello ' + yourName ", env);
		System.out.println(result); // hello Michael

		String name = "dennis";
		result = (String) AviatorEvaluator.exec(" 'hello ' + yourName ", name);
		System.out.println(result);// hello dennis
	}

	@Test
	public void testFunction() {
		String result = "" + AviatorEvaluator.execute("string.length('hello')"); // 5
		System.out.println(result);
		result = "" + AviatorEvaluator.execute("string.contains(\"test\", string.substring('hello', 1, 2))");// true
		System.out.println(result);
		result = (String) AviatorEvaluator.exec("a>0? 'yes':'no'", 1); // yes
		System.out.println(result);
	}

	@Test
	public void testAddFunction() {
		// 注册函数
		AviatorEvaluator.addFunction(new AddFunction());
		System.out.println(AviatorEvaluator.execute("add(1, 2)")); // 3.0
		System.out.println(AviatorEvaluator.execute("add(add(1, 2), 100)")); // 103.0
		AviatorEvaluator.removeFunction("add");// 移除函数
	}

	@Test
	public void testnil() {
		// nil是 Aviator 内置的常量,类似 java 中的null
		AviatorEvaluator.execute("nil == nil"); // true
		AviatorEvaluator.execute(" 3> nil"); // true
		AviatorEvaluator.execute(" true!= nil"); // true
		AviatorEvaluator.execute(" ' '>nil "); // true
		AviatorEvaluator.execute(" a==nil "); // true, a 是 null
	}

	@Test
	public void testExpression() {
		String expression = "a-(b-c)>100";
		// 编译表达式
		Expression compiledExp = AviatorEvaluator.compile(expression);
		// Expression compiledExp =
		// AviatorEvaluator.compile(expression,true);//加缓存
		// AviatorEvaluator.invalidateCache(expression);//使缓存失效
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("a", 100.3);
		env.put("b", 45);
		env.put("c", -199.100);
		// 执行表达式
		Boolean result = (Boolean) compiledExp.execute(env);
		System.out.println(result); // false
	}

	@Test
	public void testList() {
		final List<String> list = new ArrayList<String>();
		list.add("hello");
		list.add(" world");
		final int[] array = new int[3];
		array[0] = 0;
		array[1] = 1;
		array[2] = 3;
		final Map<String, Date> map = new HashMap<String, Date>();
		map.put("date", new Date());
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("list", list);
		env.put("array", array);
		env.put("mmap", map);
		System.out.println(AviatorEvaluator.execute("list[0]+list[1]", env)); // hello
																				// world
		System.out
				.println(AviatorEvaluator.execute("'array[0]+array[1]+array[2]=' + (array[0]+array[1]+array[2])", env)); // array[0]+array[1]+array[2]=4
		System.out.println(AviatorEvaluator.execute("'today is ' + mmap.date ", env));
	}

	@Test
	public void testBigDecimal() {
		Object rt = AviatorEvaluator.exec("9223372036854775807100.356M * 2");
		System.out.println(rt + " " + rt.getClass());// java.math.BigDecimal
		rt = AviatorEvaluator.exec("92233720368547758074+1000");
		System.out.println(rt + " " + rt.getClass()); // java.math.BigInteger
		BigInteger a = new BigInteger(String.valueOf(Long.MAX_VALUE) + String.valueOf(Long.MAX_VALUE));
		BigDecimal b = new BigDecimal("3.2");
		BigDecimal c = new BigDecimal("9999.99999");
		rt = AviatorEvaluator.exec("a+10000000000000000000", a);
		System.out.println(rt + " " + rt.getClass()); // java.math.BigInteger
		rt = AviatorEvaluator.exec("b+c*2", b, c);
		System.out.println(rt + " " + rt.getClass());// java.math.BigDecimal
		rt = AviatorEvaluator.exec("a*b/c", a, b, c);
		System.out.println(rt + " " + rt.getClass()); // java.math.BigDecimal
	}

	@Test
	// 操作集合和数组的 seq 库
	public void testseq() {
		Map<String, Object> env = new HashMap<String, Object>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(20);
		list.add(10);
		env.put("list", list);
		Object result = AviatorEvaluator.execute("count(list)", env);
		System.out.println(result); // 3
		result = AviatorEvaluator.execute("reduce(list,+,0)", env);
		System.out.println(result); // 33
		result = AviatorEvaluator.execute("filter(list,seq.gt(9))", env);
		System.out.println(result); // [10, 20]
		result = AviatorEvaluator.execute("include(list,10)", env);
		System.out.println(result); // true
		result = AviatorEvaluator.execute("sort(list)", env);
		System.out.println(result); // [3, 10, 20]
		AviatorEvaluator.execute("map(list,println)", env);
	}

	@Test
	// 操作集合和数组的 seq 库
	public void testsetOption() throws Exception {
		// 默认 AviatorEvaluator 以执行速度优先
		AviatorEvaluator.setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.EVAL);
		// 你可以修改为编译速度优先,这样不会做编译优化
		AviatorEvaluator.setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.COMPILE);
		// 也可以强制所有浮点数解析为 BigDecimal
		AviatorEvaluator.setOption(Options.ALWAYS_USE_DOUBLE_AS_DECIMAL, true);
		// 默认 Aviator 的计算精度为MathContext.DECIMAL128,你可以自定义精度, 通过
		AviatorEvaluator.setOption(Options.MATH_CONTEXT, MathContext.DECIMAL64);
		// 查看每个表达式生成的字节码
		AviatorEvaluator.setOption(Options.TRACE, true);
		// 默认是打印到标准输出,你可以改变输出指向
		AviatorEvaluator.setTraceOutputStream(new FileOutputStream(new File("aviator.log")));
	}
}

class AddFunction extends AbstractFunction {
	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
		Number left = FunctionUtils.getNumberValue(arg1, env);
		Number right = FunctionUtils.getNumberValue(arg2, env);
		return new AviatorDouble(left.doubleValue() + right.doubleValue());
	}

	public String getName() {
		return "add";
	}
}
