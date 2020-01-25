package business;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistManager {
	private HashMap<String, Playlist> playlists;
	ObservableList<String> playlistNames;
	
	public PlaylistManager() {
		playlists = new HashMap<String, Playlist>();
		playlistNames =  FXCollections.observableArrayList(); //um NullPointer zu vermeiden
	}
	
	public void createPlaylist(String name, ArrayList<Track> songs) {
		//File fir = new File(pfad);
		//String[] list = fir.list();
		Playlist tempList = new Playlist(name);
		for(Track n : songs) {
				tempList.addSingleTrack(n); 
				System.out.println(n.getTitle());
		}
		try {
			playlists.put(name, tempList);
			playlistNames.add(name);
		} catch (NullPointerException ez){
			System.out.println("Something went wrong with the Playlist " + name);
			}
		}
	
	public Playlist getList(String name) {
		return playlists.get(name);
	}
	
	public HashMap<String, Playlist> getAllLists() {
		return playlists;
	}
}
