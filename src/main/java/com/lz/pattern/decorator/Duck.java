package com.lz.pattern.decorator;

public class Duck extends Food {
	
	public Duck(){
		desc = "鸡肉";
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return desc;
	}

}
