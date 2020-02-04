package business;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistManager extends Observable {
	private HashMap<String, Playlist> hashlists;
	ObservableList<Playlist> playlists;

	public PlaylistManager() {
		hashlists = new HashMap<String, Playlist>();
		playlists = FXCollections.observableArrayList(); // um NullPointer zu vermeiden
	}

	public void createPlaylist(String name, ArrayList<Track> songs) {
		Playlist tempList = new Playlist(name);
		for (Track n : songs) {
			tempList.addSingleTrack(n);
		}
		try {
			this.hashlists.put(name, tempList);
			this.playlists.add(tempList);
		} catch (NullPointerException ez) {
			System.out.println("Something went wrong with the Playlist " + name);
		}

		setChanged();
		notifyObservers("neue playlist");
	}
	//wird aktuell benoetigt um alle Tracks zu bekommen
	public Playlist getList(Playlist newValue) {
		return hashlists.get(newValue.getTitle());
	}
	//Test
	public Playlist getListByString(String newValue) {
		return hashlists.get(newValue);
	}

	public HashMap<String, Playlist> getAllHashLists() {
		return hashlists;
	}

	public ObservableList<Playlist> getAllLists() {
		return this.playlists;
	}

	public Playlist getSampleList() {
		try {
			return hashlists.get("Samples");
		} catch (NullPointerException ez) {
			ez.printStackTrace();
		}
		return null;
	}

	//funktioniert noch nicht
	public void deletePlaylist(Playlist name) {
		//test
		try {
		hashlists.remove(name.getTitle());
		playlists.remove(name);
		} catch (NullPointerException ez) {
			System.out.println("Es ist keine Playlist vorhanden, die geloescht werden koennte!");
		}

	}
}
