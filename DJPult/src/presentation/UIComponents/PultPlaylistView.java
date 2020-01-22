package presentation.UIComponents;

import java.util.LinkedList;
import java.util.List;

import business.SelectTrack;
import business.Track;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;


public class PultPlaylistView extends  HBox {
	
	public ListView<String> songlistViewLeft;
	public ObservableList<String> songListLeft;
	public ListView<String> songlistViewRight;
	public ObservableList<String> songListRight;
	public VBox vbox1;
	public VBox vbox2;
	//public MP3Player mp3Player;


	public PultPlaylistView() {

	//	this.mp3Player = new MP3Player(); 

		//songList1 = mp3Player.getActPlaylist().getAllSongNames();
		//songList2 = mp3Player.getActPlaylist().getAllSongNames();
		songlistViewLeft = new ListView<>(songListLeft);
		songlistViewLeft.setId("playlist");
		
		songlistViewRight = new ListView<>(songListRight);
		songlistViewRight.setId("playlist");
		
		
		
		songListLeft = FXCollections.observableArrayList("Titel 1", "Titel 2","Titel 3");
		songlistViewLeft.setItems(songListLeft);
		
		songListRight = FXCollections.observableArrayList("Titel 1", "Titel 2","Titel 3");
		songlistViewRight.setItems(songListRight);
		
		

		vbox1 = new VBox();
		vbox2 = new VBox();

		vbox1.getChildren().addAll(songlistViewLeft);
		vbox2.getChildren().addAll(songlistViewRight);

	
		
		//this.getChildren().addAll(vbox1, vbox2);
		this.getChildren().addAll(songlistViewLeft, songlistViewRight);
		this.setPadding(new Insets(10));
		this.setSpacing(5);
		}

}