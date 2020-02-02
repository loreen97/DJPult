package business;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Playlist {
	private String title;
	//ObservableList<String> songNames;
	ObservableList<Track> obsTracks;
	
	public Playlist(String name) {
		this.title = name;
		obsTracks = FXCollections.observableArrayList();
		//songNames =  FXCollections.observableArrayList(); 
	}
	
	public Track getTrack(int no) {
		try {
			return this.obsTracks.get(no);
		} catch(NullPointerException ez) {
			System.out.println("Playlist zu Ende");
		} return null;
	}

	
	public String getTitle() {
		return this.title;
	}
	
	public Playlist getList() {
		return this;
	}
	
	public Track getFirst() {
		return obsTracks.get(0);
	}
	
	public ObservableList<Track> getAllObsTracks() {
		return this.obsTracks;
	}
	
	public int getLength() {
		return obsTracks.size();
	}
	
	public void addSingleSong(String name) {
		Track temp = new Track(name);
		obsTracks.add(temp);
	}
	
	public void addSingleTrack(Track name) {
		obsTracks.add(name);
	}
	
}
