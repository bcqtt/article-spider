package com.lz.pattern.commend.example;

/**
 * 具体命令角色类,执行stop命令
 * Function:
 * @author : laizhiwen
 * Date: 2017年9月30日
 */
public class StopCommand implements Command {
	
	private AudioPlayer audio;
	
	public StopCommand(AudioPlayer audio){
		this.audio = audio;
	}

	@Override
	public void execute() {
		audio.stop(); //执行stop命令
	}

}
