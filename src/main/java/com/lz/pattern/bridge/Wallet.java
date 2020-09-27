package com.lz.pattern.bridge;

public class Wallet extends Bag {

	@Override
	public String getName() {
		return color.getColor() + "钱包";
	}

}
