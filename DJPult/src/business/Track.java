package business;

import java.io.IOException;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class Track {
	private String title;
	private String interpret;
	private Mp3File mp3File;
	private String soundFile;
	private long length;
	
	//fuer Lieder
	public Track(String title) {
		this.title = title;
		this.soundFile = title + ".mp3";
		Mp3File mp3File = null;
		try {
			mp3File = new Mp3File(soundFile);
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
		if (mp3File != null) {
			this.length = mp3File.getLengthInMilliseconds();
			if (mp3File.hasId3v1Tag()) {
				ID3v2 id3v2Tag = mp3File.getId3v2Tag();
				this.interpret = id3v2Tag.getArtist();
				this.title = id3v2Tag.getTitle();
			}
		}
	}

	public Track (String title, String interpret) {
		super();
		this.title= title;
		this.interpret= interpret;
		this.soundFile= null;
	}
	
	public String getTitle() {
		return title;
	}

	public String getSoundFile() {
		return soundFile;
	}
	
	public long getLength() {
		return length;
	}

	public String getInterpret() {
		return interpret;
	}

	public Mp3File getMp3File() {
		return mp3File;
	}
	public String toString() {
		return title;
	}
}