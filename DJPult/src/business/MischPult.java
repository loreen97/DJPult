package business;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Observable;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MischPult extends Observable {
	/*
	 * private MediaPlayer[] mediaPlayer; private Media[] media; private Playlist[]
	 * lists; //statt 3 einzelne Playlists
	 */

	// evtl fuer jeden sample button einen...
	private Player playerLeft;
	private Player playerRight;
	private PlaylistManager manager;
	private Samples sample1, sample2, sample3, sample4, sample5, sample6;
	private HashMap<String, Player> players = new HashMap<String, Player>();
	private HashMap<String, Samples> samples = new  HashMap<String, Samples>();

	public MischPult() {
		this.manager = new PlaylistManager();
		Playlist first = new Playlist("first");
		first.addSingleSong("Apache_207.mp3");
		first.addSingleSong("02DreiWorte.mp3");
		first.addSingleSong("500 Hz Tone-SoundBible.com-1963773923.mp3");
		first.addSingleSong("Bring Mich Nach Hause.mp3");
		this.manager.getAllLists().put("first", first);
		this.manager.getAllNames().add("first");

		
		// if Playlist not null
		playerLeft = new Player("links", manager.getAllLists().get("first"));
		playerRight = new Player("rechts", manager.getAllLists().get("first"));
		players.put(playerLeft.getName(), playerLeft);
		players.put(playerRight.getName(), playerRight);
		
		sample1 = new Samples("sample1");
		sample2 = new Samples("sample2");
		sample3 = new Samples("sample3");
		sample4 = new Samples("sample4");
		sample5 = new Samples("sample5");
		sample6 = new Samples("sample6");
		
		samples.put(sample1.getName(), sample1);
		samples.put(sample2.getName(), sample2);
		samples.put(sample3.getName(), sample3);
		samples.put(sample4.getName(), sample4);
		samples.put(sample5.getName(), sample5);
		samples.put(sample6.getName(), sample6);

		/**
		 * You should also keep in mind that the AudioClip constructor will block the
		 * current thread until the sound is completely loaded. For this reason, you
		 * should consider loading your sound effects on a background thread if you are
		 * loading a lot of files or if the files are large.
		 * http://what-when-how.com/javafx-2/working-with-audio-clips-using-the-media-classes-javafx-2/
		 */
	}
	/**
	 * Zum Abspielen eines Samples
	 * 
	 * @param name der "Name" des Samples, also welches der 6 gespielt wird
	 */
	public void playSample(String name) {
		samples.get(name).play();
	}
	
	/**
	 * Zum Ändern des ersten Tune-Sliders
	 * @param name Welche Seite, Links oder Rechts
	 * @param value der neue Wert
	 */
	public void changeTune1(String name, double value) {
		players.get(name).tune1Slider(value);
	}
	
	/**
	 * Zum Ändern des zweiten Tune-Sliders
	 * @param name Welche Seite, Links oder Rechts
	 * @param value der neue Wert
	 */
	
	public void changeTune2(String name, double value) {
		players.get(name).tune2Slider(value);
	}
	
	/**
	 * Zum Ändern des dritten Tune-Sliders
	 * @param name Welche Seite, Links oder Rechts
	 * @param value der neue Wert
	 */
	
	public void changeTune3(String name, double value) {
		players.get(name).tune3Slider(value);
	}
	
	/**
	 * Zum Ändern des Songfortschritts anhand eines Sliders
	 * Springt also im Lied
	 * @param name Welche Seite, Links oder Rechts
	 * @param value die neue Position im Lied
	 */
	public void changeBySlider(String name, double value) {
		players.get(name).changeBySlider(value);
	}
	
	/**
	 * Methode zum Abspielen eines Liedes
	 * @param name Welche Seite, Links oder Rechts
	 */

	public void play(String name) {
		players.get(name).play();
		setChanged();
		notifyObservers();
	}
	/**
	 * Methode zum Pausieren eines Liedes
	 * @param name
	 */
	public void pause(String name) {
		players.get(name).pause();
	}
	
	/**
	 * Zum skippen, bisher nur wenn Lied zu ende und automatisch
	 * In Zukunft auch anhand der Titel der Songs der aktiven Playlist
	 * TO DO ^^^^
	 * @param name Links oder Rechts
	 */
	public void skip(String name) {
		players.get(name).skip();
	}
	/**
	 * Wahrscheinlich irrelevant da wir keine Skip BUTTONS haben
	 * @param name Links oder Rechts
	 */
	public void skipBack(String name) {
		players.get(name).skipBack();
	}
	/**
	 * Gibt den aktiven Song des gewählten Players zurück
	 * @param name Links oder Rechts
	 * @return aktiver Song
	 */
	public Track getActSong(String name) { // Playlist mitgeben um zu wissen welche?
		return players.get(name).getActSong();
	}
	/**
	 * Gibt die aktive Playlist des gewählten Players zurück
	 * @param name Links oder Rechts
	 * @return aktive Playlist
	 */
	public Playlist getAPlaylist(String name) {// Playlist mitgeben um zu wissen welche?
		return players.get(name).getPlaylist();
	}
	/**
	 * Zum Verändern der Lautstärke
	 * @param name Links oder Rechts
	 * @param val Der neue Wert
	 */
	public void setVolume(String name, double val) {
		players.get(name).setVolume(val);
	}

	public double getVolume(String name) {
		return players.get(name).getVolume();
	}

	public void loop(String name) {
		players.get(name).loop();
	}
	
	public Player getLeftPlayer() {
		return this.players.get("links");
	}
	
	public Player getRightPlayer() {
		return this.players.get("rechts");
	}

	// Aus Praktikum
	/*
	 * public static MediaPlayer getMediaPlayer() { return player; } //Aus Praktikum
	 * public static MediaPlayer getMediaPlayer(Media media) { player = new
	 * MediaPlayer(media); return player; }
	 */

	public void speed(String name, double value) {
		players.get(name).setSpeed(value);
	}

	public PlaylistManager getManager() {
		return this.manager;
	}
	
	/**
	 * Soll über die Settings eine Playlist setzen
	 * @param name
	 */
	//Hier geht noch irgendwas schief
	//Play button geht dann nicht mehr richtig, er resetted nur
	//Slider geht kaputt, the usual
	public void setPlaylist(Playlist name) {
		players.get("links").setPlaylist(name);
	}
	
	//useless
	public MischPult getMischPult() {
		// TODO Auto-generated method stub
		return this;
	}
}
