package com.lz.pattern.bridge;

public class HandBag extends Bag {

	@Override
	public String getName() {
		return color.getColor() + "挎包";
	}

}
