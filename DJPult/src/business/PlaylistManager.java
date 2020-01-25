package business;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;

public class PlaylistManager {
	private HashMap<String, Playlist> playlists;
	ObservableList<String> playlistNames;
	
	public PlaylistManager() {
		playlists = new HashMap<String, Playlist>();
	}
	
	public void createPlaylist(String name, ArrayList<Track> songs) {
		//File fir = new File(pfad);
		//String[] list = fir.list();
		Playlist tempList = new Playlist(name);
		for(Track n : songs) {
				tempList.addSingleTrack(n); 
		}
		playlists.put(name, tempList);
		playlistNames.add(name);
	}
}
