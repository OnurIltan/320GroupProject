package main;

import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.*;


public class AudioManager {
	AudioStream audio;
	public AudioManager(String s) {
		try{
			InputStream in = new FileInputStream(s);
			audio = new AudioStream(in);
			//startMusic();
		}catch(Exception e){
			//AudioPlayer.player.start(audio);
		}
	}
	public void startMusic() {
		AudioPlayer.player.start(audio);
	}
	public void stopMusic(){
		AudioPlayer.player.stop(audio);
	}
}
