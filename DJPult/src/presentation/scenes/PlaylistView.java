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

public class PlaylistView extends BorderPane {

	
	public Button back;
	public HBox hboxTop;
	
	
	public Button save;
	public Button loadMP3;
	public Label playlistName;
	public TextField title;
	//public ArrayList<Track> songs;
	public ObservableList<Track> songNames;
	public ListView<Track> songlistView;
	public ObservableList<Track> songList;
	public TextArea textArea;

	public HBox  hbox;
	public VBox vbox;
	
	public PlaylistView() {
		
		
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
		
	    
		hbox = new HBox();
		hbox.getChildren().addAll(playlistName, title, loadMP3,save);
		hbox.setSpacing(30);
		hbox.setAlignment(Pos.CENTER);
		
		vbox = new VBox();
		vbox.getChildren().addAll(hbox, songlistView);
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER);
		
		
		
		this.setTop(back);
		this.setCenter(vbox);
		this.setPadding(new Insets(10));
		
		this.getStyleClass().add("background");
	}
	
}