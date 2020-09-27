package com.lz.pattern.bridge;

public abstract class Bag {
	
	protected BagColor color;

	public void setColor(BagColor color) {
		this.color = color;
	}
	
	public abstract String getName();
}
