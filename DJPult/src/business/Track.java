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
		//Aktuelles Problem: Wenn kein Tag, dann ist der komplette Pfad der Name.
		this.title = title; //Titel ist durch erstellen aus Ordner an dieser Stelle noch
		//mit .mp3 hinten dran also braucht soundfile kein +".mp3"
		//Falls ID3v2 vorhanden wird titel geändert 
		this.soundFile = title;
		Mp3File mp3File = null;
		try {
			mp3File = new Mp3File(title);
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
		if (mp3File != null) {
			this.length = mp3File.getLengthInMilliseconds();
			if (mp3File.hasId3v1Tag()) {
				ID3v2 id3v2Tag = mp3File.getId3v2Tag();
				try {
				this.interpret = id3v2Tag.getArtist();
				this.title = id3v2Tag.getTitle();
				} catch (NullPointerException ez) {
					System.out.println(title + " hat ein unvollstaendiges id3v2Tag.");
				}
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