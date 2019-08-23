package com.neuedu.util;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;

/**
* @ClassName: SinglePlayer
* @Description:内部类
* @author neuedu_jnn
* @date 2019年8月20日 下午5:48:46
*
*/
public class SinglePlayer extends Thread{
	
	private String mp3Name;
	
	public SinglePlayer() {
		
	}
	
	public SinglePlayer(String mp3Name) {
		this.mp3Name = mp3Name;
	}
	
	public void play(String mp3Name) {
		SinglePlayer singlePlayer = new SinglePlayer(mp3Name);
		singlePlayer.start();
	}
	
	@Override
	public void run() {
		InputStream resourceAsStream = SoundPlayer.class.getClassLoader().getResourceAsStream(mp3Name);
		try {
			AdvancedPlayer advancedPlayer = new AdvancedPlayer(resourceAsStream);
			advancedPlayer.play();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
