package business;

import java.nio.file.Paths;
import javafx.collections.ObservableList;
import javafx.scene.media.EqualizerBand;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Player {
	private MediaPlayer mediaPlayer;
	private String name;
	private Media media;
	private Playlist list;
	private double currVolume;
	private static final int BAND_COUNT = 3;
	private static final double Start_FREQ = 250.0;
	int posInList; 
	//Evtl in Playlist statt in Players, dumm weil man dann nicht die
	//selbe Playlist doppelt nehmen kann, also hier
	ObservableList<EqualizerBand> bands;
	private boolean isLooping;

	public Player(String name) {
		this. name = name;
		this.list = null;
		posInList = 0;
		isLooping = false;
		//if list not null
		//media = new Media(list.getTrack(0).getTitle()); //Nur mit Pfad aufrufbar in Media()
		media = new Media(Paths.get("BringMichNachHause.mp3").toUri().toString());
		mediaPlayer = new MediaPlayer(media);
		bands=mediaPlayer.getAudioEqualizer().getBands();
		setBands();
	}
	
	//Tets methode 
	// google books Pro JavaFX 8: A 
	//Definitive Guide to Building Desktop, Mobile, and Embedded ... 
	//oder
	//http://what-when-how.com/javafx-2/playing-audio-using-the-media-classes-javafx-2-part-4/
	public void setBands() {
		bands=mediaPlayer.getAudioEqualizer().getBands();
		bands.clear();
		mediaPlayer.getAudioEqualizer().getBands();
		double min = EqualizerBand.MIN_GAIN;
		double max = EqualizerBand.MAX_GAIN;
		double mid = (max - min) / 2;
		double freq = Start_FREQ;
		for(int i = 0; i < BAND_COUNT; i++) {
			double t = (double)i / (double)(BAND_COUNT-1)*(2*Math.PI);
			double scale = 0.4 * (1+Math.cos(t));
			double gain = min + mid + (mid*scale);
			bands.add(new EqualizerBand(freq, freq/2, gain));
			freq *= 2;
		}
	}
	
	public void bassSlider(double value) {
		bands.get(1).setGain(50.5);
	}

	public void play() {
		//setVolume(currVolume);
		mediaPlayer.play();
	}

	public void pause() {
		mediaPlayer.pause();
	}

	public void skip() {
		mediaPlayer.stop();
		if (posInList == list.getLength()) {
			posInList = 0; 
		} else {
			posInList++;
		}
		loadSong();
		play();
	}

	public void skipBack() {
		mediaPlayer.stop();
		if (posInList != 0) {
			posInList--;;
		} else {
			posInList = list.getLength();
		}
		loadSong();
		play();
	}

	public void loadSong() {
		media = new Media(list.getTrack(posInList).getTitle()); //richtig? //Nur mit Pfad aufrufbar in Media()
	}

	public Track getActSong() {
		return list.getTrack(posInList);
	}

	public Playlist getPlaylist() {
		return this.list;
	}

	public void setVolume(double val) {
		currVolume = val;
		mediaPlayer.setVolume(val);
	}

	public double getVolume() {
		return mediaPlayer.getVolume();
	}

	public void changeBySlider(double slidervalue) {
		int songPosition = (int) slidervalue;
		//mediaPlayer.cue(songPosition);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setSpeed(double value) {
		mediaPlayer.setRate(value);
	}
	
	public void loop() {
		//do something
	}
	


	//Sets the number of bands in the audio spectrum. Must be > 2
	public void setAudioSpectrumNumBands(int n) {
		if(n > 2) {
			this.mediaPlayer.setAudioSpectrumNumBands(n);
		}
	}

	//Sets the value of the audio spectrum notification interval in seconds.
	public void setAudioSpectrumInterval(double value) {
		this.mediaPlayer.setAudioSpectrumInterval(value);
    }

	//The sensitivity threshold in decibels; must be non-positive.
	public final void setAudioSpectrumThreshold(int value) {
        this.mediaPlayer.setAudioSpectrumThreshold(value);
    }
}
