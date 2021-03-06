package presentation.UIComponents;

import java.util.Observable;
import java.util.Observer;

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
	 * Die PlaylistLeftView
	 * Die mit Cells arbeiten soll
	 * Darum Track in den Listen 
	 * @author evolk001
	 *
	 */
public class SettingPlaylistLeftView extends HBox {
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
	
	private MischPult mischPult;
	
	public SettingPlaylistLeftView(MischPult mischPult) {
		//In SecondViewController setzen wir die Elemente auch in die Liste, doppelt haelt besser
		this.mischPult = mischPult;
		
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
		
		leftPlaylistView.setCellFactory(new Callback<ListView<Playlist>, ListCell<Playlist>>() {
			@Override
			public ListCell<Playlist> call(ListView<Playlist> param) {
				return new PlaylistListCell();
			}
		});
		
		leftPlaylistList = mischPult.getManager().getAllLists();
		leftPlaylistView.setItems(leftPlaylistList);
		
		leftSongList = FXCollections.observableArrayList();
		//Test, muesste so geaendert werden dass es nur Namen anzeigt wenn uasgewaehlt
		//leftSongList = mischPult.getManager().getList("first").getAllObsTracks();
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