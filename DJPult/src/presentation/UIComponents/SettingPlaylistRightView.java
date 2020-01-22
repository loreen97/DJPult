package presentation.UIComponents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingPlaylistRightView extends HBox{
	public ListView<String> rightPlaylistView;
	public ObservableList<String> rightPlaylistList;
	public ListView<String> rightSongView;
	public ObservableList<String> rightSongList;
	
	public Button loadFile;
	public Button delete;
	
	public VBox vbox;
	
	public Label playlistLabel;
	public Label songLabel;

	public VBox boxLeft;
	public VBox boxRight;
	
	public SettingPlaylistRightView() {
		rightPlaylistView = new ListView<>(rightPlaylistList);
		rightPlaylistView.setId("settingPlaylist");
		
		rightPlaylistList = FXCollections.observableArrayList("Playlist 1", "Playlist 2","Playlist 3");
		rightPlaylistView.setItems(rightPlaylistList);
		
		rightSongView = new ListView<>(rightSongList);
		rightSongView.setId("settingPlaylist");
		
		rightSongList = FXCollections.observableArrayList("Song 1", "Song 2","Song 3");
		rightSongView.setItems(rightSongList);
		
		
		playlistLabel = new Label("Playlists");
		playlistLabel.setId("labels");
		songLabel = new Label("Songs");
		songLabel.setId("labels");
		
		boxLeft= new VBox();
		boxRight = new VBox();
		
		boxLeft.getChildren().addAll(playlistLabel, rightPlaylistView);
		boxRight.getChildren().addAll(songLabel, rightSongView);
		
		
		
		loadFile = new Button();
		loadFile.setId("loadFile");
		
		delete = new Button();
		delete.setId("delete");
		
		vbox = new VBox();
		vbox.getChildren().addAll(loadFile, delete);
		vbox.setSpacing(205);
		vbox.setPadding(new Insets(15,10,10,10));
		
		this.getChildren().addAll(boxLeft,boxRight, vbox);
		this.setPadding(new Insets(0,10,0,10));
		this.setSpacing(5);
	}
}
