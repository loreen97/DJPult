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
	/**
	 * Die PlaylistLeftView
	 * Die mit Cells arbeiten soll
	 * Darum Track in den Listen 
	 * @author evolk001
	 *
	 */
public class SettingPlaylistLeftView2 extends HBox {
	public ListView<Playlist> leftPlaylistView;
	public ObservableList<Playlist> leftPlaylistList;
	
	
	public ListView<Track> leftSongView;
	public ObservableList<Track> leftSongList;
	
	public Button check;
	public Button delete;
	
	public VBox vbox;
	
	public Label playlistLabel;
	public Label songLabel;

	public VBox boxLeft;
	public VBox boxRight;
	
	public SettingPlaylistLeftView2(MischPult mischPult) {
		//evtl uebergeben
		
		leftPlaylistView = new ListView<Playlist>(leftPlaylistList);
		leftPlaylistView.setId("settingPlaylist");
		
		leftSongView = new ListView<Track>();
		leftSongView.setId("settingPlaylist");
		
		
		leftSongView.setCellFactory(new Callback<ListView<Track>, ListCell<Track>>() {
			@Override
			public ListCell<Track> call(ListView<Track> param) {
			
				return new TrackListCell();
			}
		});
		
		leftPlaylistList = mischPult.getManager().getAllNames();

		leftPlaylistView.setItems(leftPlaylistList);
		
		leftSongList = FXCollections.observableArrayList();
		//Test
		leftSongList = mischPult.getManager().getList("first").getAllObsTracks();
		leftSongView.setItems(leftSongList);
		
		playlistLabel = new Label("Playlists");
		playlistLabel.setId("labels");
		songLabel = new Label("Songs");
		songLabel.setId("labels");
		
		boxLeft= new VBox();
		boxRight = new VBox();
		
		boxLeft.getChildren().addAll(playlistLabel, leftPlaylistView);
		boxRight.getChildren().addAll(songLabel, leftSongView);
		
		
		
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