package business;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Playlist {
	private String title;
	private LinkedList<Track> tracks = new LinkedList<Track>();
	ObservableList<String> songNames;
	
	public Playlist(String name) {
		this.title = name;
		songNames =  FXCollections.observableArrayList(); 
	}
	public Track getTrack(int no) {
		try {
		return this.tracks.get(no);
		} catch(NullPointerException ez) {
			System.out.println("Playlist zu Ende");
		} return null;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public Track getFirst() {
		return tracks.get(0);
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
	
	public void addSingleTrack(Track name) {
		tracks.add(name);
	}
	
	public ObservableList<String> getAllTitles() {
		return songNames;
	}
	
	private void createFromFolder(String dir) {
	
	}
	
	private void readTracks(String title) throws IOException {
		
	}
	
}
