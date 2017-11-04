package com.lz.pattern.commend.example;

public class Keypad {
	
	private Command playCommand;
	private Command pauseCommand;
	private Command stopCommand;
	
	public void play(){
		playCommand.execute();
	}
	
	public void pause(){
		pauseCommand.execute();
	}
	
	public void stop(){
		stopCommand.execute();
	}
	
	public void setPlayCommand(Command playCommand) {
		this.playCommand = playCommand;
	}
	
	public void setPauseCommand(Command pauseCommand) {
		this.pauseCommand = pauseCommand;
	}
	
	public void setStopCommand(Command stopCommand) {
		this.stopCommand = stopCommand;
	}
	
	

}
