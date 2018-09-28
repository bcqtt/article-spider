package com.lz.study.java8.lambda;

import java.util.function.Function;

public class MyTestJava8 {
	
	public static String setHandler(String str, Function<String, String> fun){
		return fun.apply(str);
	}
	
	public static void main(String[] args) {
		String s = "ABC-2A-28-01-01-01";
		
		String sub = setHandler(s, (str) -> str.substring(0, 12));
		System.out.println(sub);
	}

}
