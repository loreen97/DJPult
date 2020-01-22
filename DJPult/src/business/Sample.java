package business;

import java.io.IOException;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class Sample {
	private String soundFile;
	private Mp3File mp3File;
	private String title;
	
	public Sample(String name) {
		this.title = name;
		this.soundFile = title + ".mp3";
		Mp3File mp3File = null;
		try {
			mp3File = new Mp3File(soundFile);
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
		if (mp3File != null) {
			if (mp3File.hasId3v1Tag()) {
				ID3v2 id3v2Tag = mp3File.getId3v2Tag();
				this.title = id3v2Tag.getTitle();
			}
		}
	}
}
