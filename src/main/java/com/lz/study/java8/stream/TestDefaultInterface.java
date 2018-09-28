package com.lz.study.java8.stream;

public class TestDefaultInterface {
	
	public static void main(String[] args) {
		SubClass sc = new SubClass();
		System.out.println(sc.getName());
		
		MyInterface.show();
	}

}
