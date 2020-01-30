package business;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class Track {
	private String title; //hat aktuell noch .mp3 hintendran
	private String interpret;
	private Mp3File mp3File;
	private String soundFile;
	private Path mPath;
	private long length;
	
	//fuer Lieder
	//evtl einen split ("-") um Interpret von Titel zu trennen
	public Track(String path) {
		//Aktuelles Problem: Wenn kein Tag, dann ist der komplette Pfad der Name.
		this.mPath = Paths.get(path); //Titel ist durch erstellen aus Ordner an dieser Stelle noch
		//mit .mp3 hinten dran also braucht soundfile kein +".mp3"
		//Falls ID3v2 vorhanden wird titel geändert 
		this.soundFile = path;
		Mp3File mp3File = null;
		try {
			//erwartet einen Pfad aber  als String
			mp3File = new Mp3File(path);
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
					this.title = mPath.getFileName().toString();
					System.out.println(this.title + " hat ein unvollstaendiges id3v2Tag.");
				}
			} else {
				//Um beim Erstellen einer Playlist nicht den 
				//gesamten Pfad als Namen zu haben
				//funktioniert solala
				this.title = mPath.getFileName().toString();
			}
			if(interpret == null) {
				this.interpret = "Unbekannter Interpret";
			}
			//wegene abs. Path haben die Titel wsl den Pfad als namen
			//fixen? //Sollte fixed sein
			//In PlaylistViewControl
			//Wenn keine Tags enthält name noch mit .mp3 am ende fixen
		} 
		if(title.endsWith(".mp3")) {
			this.title = title.substring(0, title.length()-4);
			System.out.println(this.title);
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