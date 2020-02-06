package business;

import java.nio.file.Paths;
import java.util.Observable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.media.EqualizerBand;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

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
	private SimpleIntegerProperty index;
	// Evtl in Playlist statt in Players, dumm weil man dann nicht die
	// selbe Playlist doppelt nehmen kann, also hier
	private ObservableList<EqualizerBand> bands;
	private boolean isLooping;
	private Duration duration;
	
 

	public Player(String name, Playlist list) {
		this.name = name;
		posInList = 0;
		this.index = new SimpleIntegerProperty(0);
		isLooping = false;
		this.list = list;
		// if list not null
		media = new Media(Paths.get(list.getFirst().getSoundFile()).toUri().toString());
		mediaPlayer = new MediaPlayer(media);
		bands = getMediaPlayer().getAudioEqualizer().getBands();
		duration = media.getDuration();
		currVolume = this.getVolume();
		setBands();
	}

	// Tets methode
	// google books Pro JavaFX 8: A
	// Definitive Guide to Building Desktop, Mobile, and Embedded ...
	// oder
	// http://what-when-how.com/javafx-2/playing-audio-using-the-media-classes-javafx-2-part-4/
	public void setBands() {
		// if(name == "links") { //test/ vergleich
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
		if (mediaPlayer != null) {
			//mediaPlayer.stop(); //Wenn das drin ist geht loop nicht, evtl && looping
		}
		
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
			this.index.set(posInList);
			loadSong();
			setVolume(currVolume);
			setBands();
			/*setChanged();
			notifyObservers("neu");*/
			this.play();

		} else { // selben Song nochmal laden irgendwie
			this.mediaPlayer.seek(Duration.ZERO);
			this.play();
		}
	}

	// überflüssig?
	public void skipBack() {
		getMediaPlayer().stop();
		if (posInList != 0) {
			posInList--;
		} else {
			posInList = list.getLength();
		}
		index.set(posInList);
		loadSong();
		mediaPlayer.seek(Duration.ZERO);
		play();
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

	public SimpleIntegerProperty getIndex() {
		return this.index;
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

	public void loop() { // oder boolean mitgebenm und isLooping = bool;
		if (isLooping) {
			this.isLooping = false;
		} else {
			this.isLooping = true;
		}
	}

	public Duration getDuration() {
		return this.duration;
	}

	public void setPlaylist(Playlist name) {
		this.list = name;
		posInList = 0;
		setChanged();
		notifyObservers("new Playlist" + name);
		System.out.println("Neue Playlist " + name.getTitle() + " geladen.");
		loadSong();	
	}

	// Sets the number of bands in the audio spectrum. Must be > 2
	public void setAudioSpectrumNumBands(int n) {
		if (n > 2) {
			this.getMediaPlayer().setAudioSpectrumNumBands(n);
		}
	}

	// Sets the value of the audio spectrum notification interval in seconds.
	public void setAudioSpectrumInterval(double value) {
		this.getMediaPlayer().setAudioSpectrumInterval(value);
	}

	// The sensitivity threshold in decibels; must be non-positive.
	public final void setAudioSpectrumThreshold(int value) {
		this.getMediaPlayer().setAudioSpectrumThreshold(value);
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
