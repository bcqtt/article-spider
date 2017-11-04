package com.lz.pattern.commend.example;

/**
 * 具体命令角色类,执行play命令
 * Function:
 * @author : laizhiwen
 * Date: 2017年9月30日
 */
public class PlayCommand implements Command {
	
	private AudioPlayer audio;
	
	public PlayCommand(AudioPlayer audio){
		this.audio = audio;
	}

	@Override
	public void execute() {
		audio.play(); //执行play命令
	}

}
