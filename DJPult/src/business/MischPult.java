package business;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Die Mischpult Klasse, dient hauptsächlich zum Aufrufen und
 * Verwalten der zwei Player (Links& Rechts), sowie deren Funktionalitäten
 *  
 * @author Elisabeth Volk, Loreen Bies
 *
 */

public class MischPult extends Observable implements Observer {

	private Player playerLeft;
	private Player playerRight;
	private PlaylistManager manager;
	private Sample sample1, sample2, sample3, sample4, sample5, sample6;
	private HashMap<String, Player> players = new HashMap<String, Player>();
	private HashMap<String, Sample> samples = new  HashMap<String, Sample>();
	private Playlist sampleList;

	public MischPult() {
		this.manager = new PlaylistManager();
		Playlist first = new Playlist("first");
		first.addSingleSong("Bring Mich Nach Hause.mp3");
		first.addSingleSong("500 Hz Tone-SoundBible.com-1963773923.mp3");
		first.addSingleSong("Apache_207.mp3");
		first.addSingleSong("02DreiWorte.mp3");
		this.manager.getAllHashLists().put("first", first);
		this.manager.getAllLists().add(first);
		
		sampleList = new Playlist("Samples");
		
		playerLeft = new Player("links", manager.getAllHashLists().get("first"));
		playerRight = new Player("rechts", manager.getAllHashLists().get("first"));
		players.put(playerLeft.getName(), playerLeft);
		players.put(playerRight.getName(), playerRight);
		
		playerLeft.addObserver(this);
		playerRight.addObserver(this);
		manager.addObserver(this);
		
		sample1 = new Sample("sample1");
		sample2 = new Sample("sample2");
		sample3 = new Sample("sample3");
		sample4 = new Sample("sample4");
		sample5 = new Sample("sample5");
		sample6 = new Sample("sample6");
		
		samples.put(sample1.getName(), sample1);
		samples.put(sample2.getName(), sample2);
		samples.put(sample3.getName(), sample3);
		samples.put(sample4.getName(), sample4);
		samples.put(sample5.getName(), sample5);
		samples.put(sample6.getName(), sample6);
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
	 * @param name Welche Seite, Links oder Rechts
	 * @param value die neue Position im Lied
	 */
	public void changeBySlider(String name, double value) {
		players.get(name).changeBySlider(value);
	}
	
	
	/**
	 * Zum automatischen Skippen bei Lied Ende
	 * @param name Links oder Rechts
	 */
	public void skip(String name) {
		players.get(name).skip();
	}

	/**
	 * Gibt den aktiven Song des gewählten Players zurück
	 * @param name Links oder Rechts
	 * @return aktiver Song
	 */
	public Track getActSong(String name) {
		return players.get(name).getActSong();
	}
	/**
	 * Gibt die aktive Playlist des gewählten Players zurück
	 * @param name Links oder Rechts
	 * @return aktive Playlist
	 */
	public Playlist getAPlaylist(String name) {
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
	
	/**
	 * Gibt die Lautstärke des gewählten Players zurück
	 * @param name Links oder Rechts
	 * @return die aktuelle Lautstärke
	 */
	public double getVolume(String name) {
		return players.get(name).getVolume();
	}
	
	/**
	 * Verändert den boolean Wert "isLooping" des gewählten Players
	 * @param name Links oder Rechts
	 */
	public void loop(String name) {
		players.get(name).loop();
	}
	
	/**
	 * Gibt den linken Player zurück
	 * @return linker Player aus der Player Hasmap
	 */
	public Player getLeftPlayer() {
		return this.players.get("links");
	}
	
	/**
	 * Gibt den rechten Player zurück
	 * @return rechter Player aus der Player Hasmap
	 */
	public Player getRightPlayer() {
		return this.players.get("rechts");
	}

	/**
	 * Verändert die Geschwindigkeit des aktuell gespielten
	 * Liedes eines Players
	 * @param name Links oder Rechts
	 * @param value die neue Geschwindigkeit
	 */
	public void speed(String name, double value) {
		players.get(name).setSpeed(value);
	}

	/**
	 * Gibt den Playlist Manager zurück, den sich der
	 * Nutzbarkeit halber beide Player teilen
	 * @return der PlaylistManager
	 */
	public PlaylistManager getManager() {
		return this.manager;
	}
	
	/**
	 * Setzt eine neue Playlist für den gewählten Player
	 * Kommt von der SecondView 
	 * @param name Links oder Rechts
	 * @param list Die neue Playlist
	 */
	public void setPlaylist(Playlist list, String name) {
		players.get(name).setPlaylist(list);
	}
	
	/**
	 * Gibt Updates von den Playern weiter nach Oben
	 */
	
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}
	
	/**
	 * Gibt ein Sample anhand seines Namens zurück
	 * Aus der Samples HashMap
	 * @param name Name des Samples
	 * @return des gesuchte Sample
	 */
	public Sample getSample(String name) {
		return samples.get(name);
	}
	
	/**
	 * Gibt die komplette Sample Playlist zurück
	 * @return die Samples Playlist
	 */
	public Playlist getSampleList() {
		return this.sampleList;
	}
	
	/**
	 * Gibt den Wert des 1. Tune(Sliders) zurück
	 * @param name Links oder Rechts
	 * @return Wert des 1. Tunes
	 */
	
	public double getTune1(String name) {
		return players.get(name).getTune1();
	}
	
	/**
	 * Gibt den Wert des 2. Tune(Sliders) zurück
	 * @param name Links oder Rechts
	 * @return Wert des 2. Tunes
	 */
	
	public double getTune2(String name) {
		return players.get(name).getTune2();
	}
	
	/**
	 * Gibt den Wert des 3. Tune(Sliders) zurück
	 * @param name Links oder Rechts
	 * @return Wert des 3. Tunes
	 */
	
	public double getTune3(String name) {
		return players.get(name).getTune3();
	}
	
	
}
