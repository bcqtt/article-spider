package com.lz.pattern.decorator.example1;

/**
 * 具体构件
 */
public class Chicken extends Food {
	
	public Chicken(){
		desc = "鸡肉";
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return desc;
	}

}
