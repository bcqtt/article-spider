package com.lz.pattern.bridge;

public class Wallet extends Bag {

	@Override
	public String getName() {
		return "你买了个" + color.getColor() + "钱包";
	}

}
