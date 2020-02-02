package presentation.scenes;

import business.Track;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ThirdView extends BorderPane {

	
	public Button back;
	public HBox hboxTop;
	
	
	public Button save;
	public Button loadMP3;
	public Label playlistName;
	public TextField title;
	public Label infoLabel;
	public VBox info;
	//public ArrayList<Track> songs;
	public ObservableList<Track> songNames;
	public ListView<Track> songlistView;
	public ObservableList<Track> songList;
	public TextArea textArea;

	public HBox  hbox;
	public VBox vbox;
	
	/**
	 * Die 3. Seite, also zum Erstellen von neuen Playlists
	 * Nur die Box mit den Song Titeln sowie der Button zum Öffnen
	 * des System Explorers
	 */
	public ThirdView() {
		
		back = new Button();
		back.setId("back");
		
		save = new Button();
		save.setId("save");
		loadMP3 = new Button();
		loadMP3.setId("loadFile");
		playlistName = new Label("Enter Playlistname");
		playlistName.setId("playlistLabel");

		title = new TextField();
		//songs = new ArrayList<Track>();
		songNames = FXCollections.observableArrayList();
		songlistView = new ListView<Track>(songList);
		songlistView.setId("createPlaylist");
		songList = null;

		textArea = new TextArea();
	    textArea.setMinHeight(70);
		
	    info = new VBox();
		infoLabel = new Label("Bitte gebe der Playlist einen Titel \n und mindestens 3 Songs. ");
		infoLabel.setId("info");
		infoLabel.setOpacity(0);
		info.getChildren().addAll(infoLabel);
		info.setAlignment(Pos.CENTER);
		info.setPadding(new Insets(0, 80, 0, 0));
		
		
		hbox = new HBox();
		hbox.getChildren().addAll(playlistName, title, loadMP3,save);
		hbox.setSpacing(30);
		hbox.setAlignment(Pos.CENTER);
		
		vbox = new VBox();
		vbox.getChildren().addAll(hbox, songlistView);
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(0, 0, 0, 380));
		
		this.setTop(back);
		this.setCenter(vbox);
		this.setRight(info);
		this.setPadding(new Insets(10));
		
		this.getStyleClass().add("background");
	}
	
}
