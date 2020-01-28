package presentation.UIComponents;

import business.SelectTrack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
/**
 * Die PlaylistLeftView
 * Die mit Cells arbeiten soll
 * Darum SelectTrack in den Listen 
 * @author evolk001
 *
 */
public class SettingPlaylistRightView2 extends HBox {
	public ListView<String> rightPlaylistView;
	public ObservableList<String> rightPlaylistList;
	
	public ListView<SelectTrack> rightSongView;
	public ObservableList<SelectTrack> rightSongList;
	
	public Button check;
	public Button delete;
	
	public VBox vbox;
	
	public Label playlistLabel;
	public Label songLabel;

	public VBox boxLeft;
	public VBox boxRight;
	
	public SettingPlaylistRightView2() {
		rightPlaylistView = new ListView<>(rightPlaylistList);
		rightPlaylistView.setId("settingPlaylist");
		
		rightPlaylistList = FXCollections.observableArrayList("Playlist 1", "Playlist 2","Playlist 3");
		rightPlaylistView.setItems(rightPlaylistList);
		
		rightSongView = new ListView<>(rightSongList);
		rightSongView.setId("settingPlaylist");
		
		rightSongView.setCellFactory(new Callback<ListView<SelectTrack>, ListCell<SelectTrack>>() {
			@Override
			public ListCell<SelectTrack> call(ListView<SelectTrack> param) {
				// TODO Auto-generated method stub
				return new TrackListCell();
			}
		});
		
		
		rightSongList = FXCollections.observableArrayList();
		rightSongView.setItems(rightSongList);
		
		
		playlistLabel = new Label("Playlists");
		playlistLabel.setId("labels");
		songLabel = new Label("Songs");
		songLabel.setId("labels");
		
		boxLeft= new VBox();
		boxRight = new VBox();
		
		boxLeft.getChildren().addAll(playlistLabel, rightPlaylistView);
		boxRight.getChildren().addAll(songLabel, rightSongView);
		
		
		
		check = new Button();
		check.setId("check");
		
		delete = new Button();
		delete.setId("delete");
		
		vbox = new VBox();
		vbox.getChildren().addAll(check, delete);
		vbox.setSpacing(205);
		vbox.setPadding(new Insets(15,10,10,10));
		
		this.getChildren().addAll(boxLeft,boxRight, vbox);
		this.setPadding(new Insets(0,10,0,10));
		this.setSpacing(5);
	}
}
