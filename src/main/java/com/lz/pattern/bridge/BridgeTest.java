package com.lz.pattern.bridge;

import org.junit.Test;

public class BridgeTest {
	
	@Test
	public void test1( ) {
		BagColor yellow = new Yellow();
		Bag bag = new Wallet();
		bag.setColor(yellow);
		System.out.println(bag.getName());
		
		BagColor red = new Red();
		Bag bag2 = new HandBag();
		bag2.setColor(red);
		bag2.getName();
		System.out.println(bag2.getName());
	}
}
