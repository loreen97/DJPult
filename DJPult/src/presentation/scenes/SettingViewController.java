package presentation.scenes;

import application.Main;
import business.MischPult;
import business.SelectTrack;
import business.Track;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import presentation.UIComponents.HeaderView;
import presentation.UIComponents.SettingPlaylistLeftView2;

public class SettingViewController extends ViewController<Main> {
	
	/**
	 * Die 2. Seite
	 * Also zum Auswählen von bereits gespeicherten Playlists
	 * zeigt Links die Playlist Namen und rechts die
	 * Namen der Titel der ausgewählten Playlist
	 * Ziel: Mit Cells einrichten
	 */
	
	private Button back;
	private Button folder;
	
	private MischPult mischPult;
	private Stage stage;
	private ObservableList<Track> leftSongList;
	
	public SettingViewController(Main application,MischPult mischPult, Stage primaryStage ) {
		super(application);
		
		this.mischPult= mischPult;
		
		rootView = new SettingView();
		
		SettingView view = (SettingView) rootView;
		this.stage = primaryStage;
		
		HeaderView headerView = view.headerView;
		back = headerView.back;
		folder = headerView.folder;
		
		
		SettingPlaylistLeftView2 leftView = view.sPLView2;
		leftSongList = leftView.leftSongList2;
		initialize();
	}
	@Override
	public void initialize() {
		//Alles egal so lange wir an Select Track nichts machen
		try {
			leftSongList.addAll(mischPult.getManager().getList("first").getAllTracks());
		} catch (NullPointerException ez) {
			ez.printStackTrace();
		} 
		
		back.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(application!= null) {
					application.switchScene(Scenes.PULT_VIEW);
				}
			}
		});
		folder.setOnAction(new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(application!= null) {
					application.switchScene(Scenes.PLAYLIST_VIEW);
				}
			}
		});
	}
	
	public void update() {
		Platform.runLater(() -> {
			//Hier wahrscheinlich dann die PlaylistView, also die
			//Übersicht der Titel updaten
			//maybe
		});
	}
}
