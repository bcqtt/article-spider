package com.lz.pattern.decorator.example1;

/**
 * 具体装饰
 */
public class RoastFood extends FoodDecoration {
	
	private Food food;
	
	public RoastFood(Food f){
		this.food = f;
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return getDecoration() + food.getDesc();
	}
	
	private String getDecoration(){
		return "烤";
	}

}
