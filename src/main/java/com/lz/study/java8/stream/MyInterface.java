package com.lz.study.java8.stream;

public interface MyInterface {
	
	default String getName(){
		return "呵呵呵";
	}
	
	public static void show(){
		System.out.println("接口中的静态方法");
	}

}
