package business;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Observable;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MischPult extends Observable {

	//test
	private static MediaPlayer player;
	private static Media media;

	/*private MediaPlayer[] mediaPlayer;
	private Media[] media;
	private Playlist[] lists; //statt 3 einzelne Playlists
	*/
	
	//evtl fuer jeden sample button einen...
		private Player playerLeft;
		private Player playerRight;
		private Player playerSamples;
		private HashMap<String, Player> players = new HashMap<String, Player>();

	
	public MischPult() {
		
		//if Playlist not null
		playerLeft = new Player("links"); 
		playerRight = new Player("rechts");
		playerSamples = new Player("samples");
		players.put(playerLeft.getName(), playerLeft);
		players.put(playerRight.getName(), playerRight);
		players.put(playerSamples.getName(), playerSamples);

		/**
		 * You should also keep in mind that the AudioClip constructor 
		 * will block the current thread until the sound is completely 
		 * loaded. For this reason, you should consider loading your 
		 * sound effects on a background thread if you are loading a 
		 * lot of files or if the files are large. 
		 * http://what-when-how.com/javafx-2/working-with-audio-clips-using-the-media-classes-javafx-2/
		 */
		
		//test
		media = new Media(Paths.get("BringMichNachHause.mp3").toUri().toString());
		player = new MediaPlayer(media);
	}
	
	
	
	//test
	public void play() {
		player.play();
		setChanged();
		notifyObservers();
	}
	
	//nur als Test eingesetzt
	public void pause() {
		player.pause();
	}
	//TEST
	public void changeBass(double value) {
		players.get("links").bassSlider(value);
	}
	
	
	
	//name als key aus der hashmap um richtigen Player zu aktivieren
		public void play(String name) {
			System.out.println("isn't");
			players.get(name).play();
			System.out.println(name + players.get(name).getName());
			setChanged();
			notifyObservers();
		}
	
		public void pause(String name) {
			players.get(name).pause();
		}

		public void skip(String name) {
			players.get(name).skip();
		}

		public void skipBack(String name) {
			players.get(name).skipBack();
		}

		public Track getActSong(String name) { //Playlist mitgeben um zu wissen welche?
			return players.get(name).getActSong();
		}

		public Playlist getAPlaylist(String name) {//Playlist mitgeben um zu wissen welche?
			return players.get(name).getPlaylist();
		}

		public void setVolume(String name, double val) {
			players.get(name).setVolume(val);
		}

		public double getVolume(String name) {
			return players.get(name).getVolume();
		}
		//test
		public double getVolume() {
			return player.getVolume();
		}
	//Aus Praktikum
	/*public static MediaPlayer getMediaPlayer() {
		return player;
	}
	//Aus Praktikum
	public static MediaPlayer getMediaPlayer(Media media) {
		player = new MediaPlayer(media);
		return player;
	}*/
		
		//test
		public void speed(double value) {
			player.setRate(value);
		}
		
		public void volume(double value) {
			player.setVolume(value);
		}
}
