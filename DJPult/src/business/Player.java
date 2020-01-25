package business;

import java.nio.file.Paths;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.media.EqualizerBand;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class Player {
	private MediaPlayer mediaPlayer;
	private String name;
	private Media media;
	private Playlist list;
	private double currVolume;
	private static final int BAND_COUNT = 3;
	private static final double Start_FREQ = 250.0;
	private int posInList; 
	private SimpleIntegerProperty index;
	//Evtl in Playlist statt in Players, dumm weil man dann nicht die
	//selbe Playlist doppelt nehmen kann, also hier
	ObservableList<EqualizerBand> bands;
	private boolean isLooping;
	Duration duration;

	public Player(String name) {
		this. name = name;
		posInList = 0;
		this.index = new SimpleIntegerProperty(0);
		isLooping = false;
		this.list = new Playlist("first");
		this.list.addSingleSong("Apache_207.mp3");
		this.list.addSingleSong("02DreiWorte.mp3");
		this.list.addSingleSong("500 Hz Tone-SoundBible.com-1963773923.mp3");
		this.list.addSingleSong("Bring Mich Nach Hause.mp3");
		//if list not null
		//media = new Media(list.getTrack(0).getTitle()); //Nur mit Pfad aufrufbar in Media()
		media = new Media(Paths.get(list.getFirst()).toUri().toString());
		mediaPlayer = new MediaPlayer(media);
		bands=getMediaPlayer().getAudioEqualizer().getBands();
		duration = media.getDuration();
		setBands();
	}
	
	//Tets methode 
	// google books Pro JavaFX 8: A 
	//Definitive Guide to Building Desktop, Mobile, and Embedded ... 
	//oder
	//http://what-when-how.com/javafx-2/playing-audio-using-the-media-classes-javafx-2-part-4/
	public void setBands() {
		//if(name == "links") { //test/ vergleich
		bands=getMediaPlayer().getAudioEqualizer().getBands();
		bands.clear();
		getMediaPlayer().getAudioEqualizer().getBands();
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
		//}
	}
}
	
	public void tune1Slider(double value) {
		bands.get(0).setGain(value);
	}
	
	public void tune2Slider(double value) {
		bands.get(1).setGain(value);
	}
	
	public void tune3Slider(double value) {
		bands.get(2).setGain(value);
	}

	public void play() {
		if (mediaPlayer != null){
			mediaPlayer.stop();
		}
		//setVolume(currVolume);
		getMediaPlayer().play();
		//irgendwie auf dur.ZERO setzen um das Lied noch mal 
		//ueber play spielen zu koennen
		/*if(media.getDuration().equals(dur)) {
			mediaPlayer.seek(dur.ZERO);
		}*/
	}

	public void pause() {
		getMediaPlayer().pause();
	}

	public void skip() {
		if(!isLooping) {
		if (posInList == list.getLength()) {
			posInList = 0; 
		} else {
			posInList++;
		}
		index.set(posInList);
		mediaPlayer.dispose();
		loadSong();
		mediaPlayer.seek(Duration.ZERO);
		mediaPlayer = new MediaPlayer(media);
		play();
		}
		else { //selben Song nochmal laden irgendwie
			mediaPlayer.seek(Duration.ZERO);
			play();
		}
	}

	public void skipBack() {
		getMediaPlayer().stop();
		if (posInList != 0) {
			posInList--;;
		} else {
			posInList = list.getLength();
		}
		index.set(posInList);
		loadSong();
		mediaPlayer.seek(Duration.ZERO);
		play();
	}

	public void loadSong() {
		media = new Media(Paths.get(list.getTrack(posInList).getSoundFile()).toUri().toString());
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
		getMediaPlayer().seek(duration.multiply(slidervalue/100.0));
		//mediaPlayer.cue(songPosition);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setSpeed(double value) {
		getMediaPlayer().setRate(value);
	}
	
	public void loop() { //oder boolean mitgebenm und isLooping = bool;
		if(isLooping) {
			this.isLooping = false;
		} else {
			this.isLooping = true;
		}
	}
	
	public Duration getDuration() {
		return this.duration;
	}
	


	//Sets the number of bands in the audio spectrum. Must be > 2
	public void setAudioSpectrumNumBands(int n) {
		if(n > 2) {
			this.getMediaPlayer().setAudioSpectrumNumBands(n);
		}
	}

	//Sets the value of the audio spectrum notification interval in seconds.
	public void setAudioSpectrumInterval(double value) {
		this.getMediaPlayer().setAudioSpectrumInterval(value);
    }

	//The sensitivity threshold in decibels; must be non-positive.
	public final void setAudioSpectrumThreshold(int value) {
        this.getMediaPlayer().setAudioSpectrumThreshold(value);
    }

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
}
