package com.lz.pattern.commend.example;

/**
 * 接受者角色，负责执行具体的命名行为
 * Function:
 * @author : laizhiwen
 * Date: 2017年9月30日
 */
public class AudioPlayer {
	
	public void play(){
		if(Singleton.getInstance().status ==1 ){
			System.out.println("继续播放...");
			Singleton.getInstance().status = 0;
		}else if(Singleton.getInstance().status == 2){
			System.out.println("开始播放...");
		}else {
			System.out.println("播放...");
		}
    }
	
	public void pause(){
        System.out.println("暂停...");
    }
	
	public void stop(){
		System.out.println("停止...");
	}

}
