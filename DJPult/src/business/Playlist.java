package business;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Playlist {
	private String title;
	private LinkedList<Track> tracks = new LinkedList<Track>();
	
	public Playlist(String name) {
		this.title = name;
	}
	public Track getTrack(int no) {
		return this.tracks.get(no);
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getFirst() {
		return tracks.get(0).getSoundFile();
	}
	
	public List<Track> getAllTracks() {
		return this.tracks;
	}
	
	public int getLength() {
		return tracks.size();
	}
	
	public void addSingleSong(String name) {
		Track temp = new Track(name);
		tracks.add(temp);
	}
	
	private void createFromFolder(String dir) {
	
	}
	
	private void readTracks(String title) throws IOException {
		
	}
	
}
