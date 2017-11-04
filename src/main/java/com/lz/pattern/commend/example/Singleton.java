package com.lz.pattern.commend.example;

/**
 * 单例模式记录每一个操作的状态变化
 * Function:
 * @author : laizhiwen
 * Date: 2017年9月30日
 */
public class Singleton {
	
	int status = 0; //0：播放，1：暂停，2：停止
	
	private static Singleton instance;
	
	private Singleton(){}
	
	public static Singleton getInstance(){
		if(instance == null){
			instance = new Singleton();
		}
		
		return instance;
	}

}
