package com.lz.pattern.commend.example;

public class People {
	public static void main(String[] args) {
		//创建接收者对象
        AudioPlayer audioPlayer = new AudioPlayer();
        //创建命令对象
        PlayCommand playCommand = new PlayCommand(audioPlayer);
        PauseCommand pauseCommand = new PauseCommand(audioPlayer);
        StopCommand stopCommand = new StopCommand(audioPlayer);
        
        Keypad keypad = new Keypad();
        keypad.setPlayCommand(playCommand);
        keypad.setPauseCommand(pauseCommand);
        keypad.setStopCommand(stopCommand);
        keypad.play();
        keypad.pause();
        keypad.play();
        keypad.stop();
        keypad.play();
        
	}

}
