package business;

import java.nio.file.Paths;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Samples {
	private MediaPlayer mediaPlayer;
	private String name;
	private Media media;
	private Track track;
	//private static Duration dur;
	
	public Samples(String name) {
		this.name = name;
		//test
		media = new Media(Paths.get("sms-alert-2-daniel_simon.mp3").toUri().toString());
		mediaPlayer = new MediaPlayer(media);
		//dur = media.getDuration();
	}
	
	public void play() {
		mediaPlayer.play();
		mediaPlayer.seek(Duration.ZERO);
	}
	
	public String getName() {
		return this.name;
	}
	
	public Track getTrack() {
		return this.track;
	}
}
