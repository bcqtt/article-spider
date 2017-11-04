package com.lz.pattern.commend.example;

/**
 * 具体命令角色，执行stop命令
 * Function:
 * @author : laizhiwen
 * Date: 2017年9月30日
 */
public class PauseCommand implements Command {
	
	private AudioPlayer audio;
	
	public PauseCommand(AudioPlayer audio){
		this.audio = audio;
	}

	@Override
	public void execute() {
		audio.pause();  //执行pause命令
		Singleton.getInstance().status=1;
	}

}
