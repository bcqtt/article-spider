package com.lz.pattern.decorator.example1;

/**
 * 具体构件
 */
public class Duck extends Food {
	
	public Duck(){
		desc = "鸭肉";
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return desc;
	}

}
