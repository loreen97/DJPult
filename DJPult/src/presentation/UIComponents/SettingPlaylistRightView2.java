package presentation.UIComponents;

import business.MischPult;
import business.Playlist;
import business.Track;
import business.Track;
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
import presentation.scenes.PlaylistListCell;
	/**
	 * Die PlaylistRightView
	 * Die mit Cells arbeiten soll
	 * Darum Track in den Listen 
	 * @author evolk001
	 *
	 */
public class SettingPlaylistRightView2 extends HBox {
	public ListView<Playlist> rightPlaylistView;
	public ObservableList<Playlist> rightPlaylistList;
	
	
	public ListView<Track> rightSongView;
	public ObservableList<Track> rightSongList;
	
	public Button check;
	public Button delete;
	
	public VBox vbox;
	
	public Label playlistLabel;
	public Label songLabel;

	public VBox boxLeft;
	public VBox boxRight;
	
	public SettingPlaylistRightView2(MischPult mischPult) {
		//evtl uebergeben
		
		rightPlaylistView = new ListView<>(rightPlaylistList);
		rightPlaylistView.setId("settingPlaylist");
		
		rightSongView = new ListView<Track>();
		rightSongView.setId("settingPlaylist");
		
		
		rightSongView.setCellFactory(new Callback<ListView<Track>, ListCell<Track>>() {
			@Override
			public ListCell<Track> call(ListView<Track> param) {
			
				return new TrackListCell();
			}
		});
		
		rightPlaylistView.setCellFactory(new Callback<ListView<Playlist>, ListCell<Playlist>>() {
			@Override
			public ListCell<Playlist> call(ListView<Playlist> param) {
				return new PlaylistListCell();
			}
		});
		
		rightPlaylistList = mischPult.getManager().getAllNames();
		rightPlaylistView.setItems(rightPlaylistList);
		
		rightSongList = FXCollections.observableArrayList();
		//Test
		//rightSongList = mischPult.getManager().getList("first").getAllObsTracks();
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