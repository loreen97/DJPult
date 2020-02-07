package business;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class Track {
	private String title; 
	private String interpret;
	private Mp3File mp3File;
	private String soundFile;
	private Path mPath;
	private long length;

	public Track(String path) {
		this.mPath = Paths.get(path); 
		this.soundFile = path;
		Mp3File mp3File = null;
		try {
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
				this.title = mPath.getFileName().toString();
			}
			if(interpret == null) {
				this.interpret = "Unbekannter Interpret";
			}
		} 
		if(title.endsWith(".mp3")) {
			this.title = title.substring(0, title.length()-4);
			System.out.println(this.title);
		}
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
}