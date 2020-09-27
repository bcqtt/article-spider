package com.lz.pattern.bridge;

public class HandBag extends Bag {

	@Override
	public String getName() {
		return "你买了个" + color.getColor() + "挎包";
	}

}
