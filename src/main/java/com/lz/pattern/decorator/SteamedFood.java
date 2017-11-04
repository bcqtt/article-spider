package com.lz.pattern.decorator;

/**
 * 蒸的
 * Function:
 * @author : laizhiwen
 * Date: 2017年9月29日
 */
public class SteamedFood extends FoodDecoration {
	
	private Food food;
	
	public SteamedFood(Food f){
		this.food = f;
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return getDecoration() + food.getDesc();
	}
	
	private String getDecoration(){
		return "蒸";
	}

}
