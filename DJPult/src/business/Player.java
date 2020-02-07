package business;

import java.nio.file.Paths;
import java.util.Observable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.media.EqualizerBand;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Die Klasse für die zwei Player, Links& Rechts
 * Laden zu Beginn automatisch die Playlist "first", die im MischPult erstellt wird
 * @author Elisabeth Volk, Loreen Bies
 *
 */
public class Player extends Observable {
	private MediaPlayer mediaPlayer;
	private String name;
	private Media media;
	private Playlist list;
	private double currVolume;
	private static final int BAND_COUNT = 7;
	private static final int BAND_1 = 0;
	private static final int BAND_2 = 1;
	private static final int BAND_3 = 4;
	private static final double Start_FREQ = 250.0;
	private int posInList;
	private ObservableList<EqualizerBand> bands;
	private boolean isLooping;
	private Duration duration;
	
	

	public Player(String name, Playlist list) {
		this.name = name;
		posInList = 0;
		isLooping = false;
		this.list = list;
		media = new Media(Paths.get(list.getFirst().getSoundFile()).toUri().toString());
		mediaPlayer = new MediaPlayer(media);
		bands = getMediaPlayer().getAudioEqualizer().getBands();
		duration = media.getDuration();
		currVolume = this.getVolume();
		setBands();
	}
	/**
	 * Setzt die Bands, deren Werte über die Tune Slider geändert werden
	 */
	public void setBands() {
		bands = getMediaPlayer().getAudioEqualizer().getBands();
		bands.clear();
		getMediaPlayer().getAudioEqualizer().getBands();
		double min = EqualizerBand.MIN_GAIN;
		double max = EqualizerBand.MAX_GAIN;
		double mid = (max + min) / 2;
		double freq = Start_FREQ;

		for (int i = 0; i < BAND_COUNT; i++) {
			double t = (double) i / (double) (BAND_COUNT - 1) * (2 * Math.PI);
			double scale = 0.4 * (1 + Math.cos(t));
			double gain = min + mid + (mid * scale);
			bands.add(new EqualizerBand(freq, freq / 2, gain));
			freq *= 2;
			System.out.println(i + " " + bands.get(i).getBandwidth());
		}
	}

	public void tune1Slider(double value) {
		bands.get(BAND_1).setGain(value);
	}

	public void tune2Slider(double value) {
		bands.get(BAND_2).setGain(value);
	}

	public void tune3Slider(double value) {
		bands.get(BAND_3).setGain(value);
	}

	public void play() {		
		setVolume(currVolume);
		this.mediaPlayer.play();
		this.mediaPlayer.setOnEndOfMedia(() -> this.skip());
	}

	public void pause() {
		getMediaPlayer().pause();
	}

	public void skip() {
		if (!isLooping) {
			if (posInList == (list.getLength() - 1)) {
				posInList = 0;
			} else {
				posInList++;
			}
			loadSong();
			setVolume(currVolume);
			setBands();
			this.play();
		} else { 
			this.mediaPlayer.seek(Duration.ZERO);
			this.play();
		}
	}

	public void loadSong() {
		try {
			mediaPlayer.dispose();
			mediaPlayer.seek(Duration.ZERO);
			this.media = new Media(Paths.get(list.getTrack(posInList).getSoundFile()).toUri().toString());
			this.mediaPlayer = new MediaPlayer(media);
			setChanged();
			notifyObservers(name);
		} catch (NullPointerException ez) {
			System.out.println("Es gibt noch keinen Media Player oder keinen Song, welchen er spielen könnte!");
		}
	}
	
	public void loadFromIndex(int n) {
		this.posInList = n;
		loadSong();
	}

	public Track getActSong() {
		return list.getTrack(posInList);
	}

	public Playlist getPlaylist() {
		return this.list;
	}

	public void setVolume(double val) {
		currVolume = val;
		getMediaPlayer().setVolume(val);
	}

	public double getVolume() {
		return getMediaPlayer().getVolume();
	}


	public void changeBySlider(double slidervalue) {
		getMediaPlayer().seek(duration.multiply(slidervalue / 100.0));
	}

	public String getName() {
		return this.name;
	}

	public void setSpeed(double value) {
		getMediaPlayer().setRate(value);
	}

	public void loop() { 
		if (isLooping) {
			this.isLooping = false;
		} else {
			this.isLooping = true;
		}
	}

	public void setPlaylist(Playlist name) {
		this.list = name;
		posInList = 0;
		setChanged();
		notifyObservers("new Playlist" + name); 
		System.out.println("Neue Playlist " + name.getTitle() + " geladen.");
		loadSong();	
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public double getTune1() {
		return bands.get(BAND_1).getGain();
	}
	
	public double getTune2() {
		return bands.get(BAND_2).getGain();
	}
	
	public double getTune3() {
		return bands.get(BAND_3).getGain();
	}
}
