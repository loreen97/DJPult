package business;

import java.nio.file.Paths;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Sample {
	private MediaPlayer mediaPlayer;
	private String name;
	private Media media;
	private Track track;
	
	public Sample(String name) {
		this.name = name;
	}
	
	public void setSample(Track name) {
		if(mediaPlayer != null){
			mediaPlayer.dispose();
		}
		try {
			
		media = new Media(Paths.get(name.getSoundFile()).toUri().toString());
		} catch (NullPointerException ez) {
			ez.printStackTrace();
		}
		mediaPlayer = new MediaPlayer(media);
	}
	
	public void play() {
		try {
		mediaPlayer.play();
		mediaPlayer.seek(Duration.ZERO);
		} catch (NullPointerException ez) {
			System.out.println("Es sind noch keine Samples ausgewaehlt!");
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public Track getTrack() {
		return this.track;
	}
}
