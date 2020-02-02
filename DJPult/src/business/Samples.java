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
		//media = new Media(Paths.get("sms-alert-2-daniel_simon.mp3").toUri().toString());
		//mediaPlayer = new MediaPlayer(media);
	}
	
	public void setSample(String name) {
		mediaPlayer.dispose();
		media = new Media(Paths.get(name).toUri().toString());
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
