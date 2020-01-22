package business;

import java.util.HashMap;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MischPult {
	
	//private static MediaPlayer player;

	/*private MediaPlayer[] mediaPlayer;
	private Media[] media;
	private Playlist[] lists; //statt 3 einzelne Playlists
	*/
	
	//evtl fuer jeden sample button einen...
		private Player player1;
		private Player player2;
		private Player player3;
		private HashMap<String, Player> players = new HashMap<String, Player>();

	
	public MischPult() {
		
		//if Playlist not null
		player1 = new Player("Links"); 
		player2 = new Player("Rechts");
		player3 = new Player("Sample");
		players.put("links", player1);
		players.put("rechts", player2);
		players.put("samples", player3);

		/**
		 * You should also keep in mind that the AudioClip constructor 
		 * will block the current thread until the sound is completely 
		 * loaded. For this reason, you should consider loading your 
		 * sound effects on a background thread if you are loading a 
		 * lot of files or if the files are large. 
		 * http://what-when-how.com/javafx-2/working-with-audio-clips-using-the-media-classes-javafx-2/
		 */
	}
	
	public void play() {
		player1.play();
	}
	
	//name als key aus der hashmap um richtigen Player zu aktivieren
		public void play(String name) {
			players.get(name).play();
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
	
	//Aus Praktikum
	/*public static MediaPlayer getMediaPlayer() {
		return player;
	}
	//Aus Praktikum
	public static MediaPlayer getMediaPlayer(Media media) {
		player = new MediaPlayer(media);
		return player;
	}*/
	
}
