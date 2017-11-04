package com.lz.pattern.decorator;

/**
 * 动态给一个对象添加一些额外的职责。
 * Function:
 * @author : laizhiwen
 * Date: 2017年9月29日
 */
public class Client {
	
	public static void main(String[] args) {
		// 测试单纯的食物
		Food f1 = new Chicken();
		System.out.println(f1.getDesc());
		
		System.out.println("---------------------------------------");
		
		// 测试单重修饰的食物
		RoastFood rf = new RoastFood(f1);
		System.out.println(rf.getDesc());
		
		System.out.println("---------------------------------------");
		
		// 测试单重修饰的食物
		SteamedFood sf = new SteamedFood(f1);
		System.out.println(sf.getDesc());
		
		System.out.println("---------------------------------------");
	}

}
