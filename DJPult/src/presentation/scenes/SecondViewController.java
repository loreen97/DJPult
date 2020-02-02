package presentation.scenes;

import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import application.Main;
import business.MischPult;
import business.Playlist;
import business.SelectTrack;
import business.Track;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import presentation.UIComponents.HeaderView;
import presentation.UIComponents.SamplePlaylistView2;
import presentation.UIComponents.SettingPlaylistLeftView2;

public class SecondViewController extends ViewController<Main> implements Observer{
	
	/**
	 * Die 2. Seite
	 * Also zum Auswählen von bereits gespeicherten Playlists
	 * zeigt Links die Playlist Namen und rechts die
	 * Namen der Titel der ausgewählten Playlist
	 * Ziel: Mit Cells einrichten
	 */
	
	private Button back;
	private Button folder;
	private Button checkLeft;
	private Button loadMP3;
	private boolean selected;
	
	private final FileChooser fChooser;
	
	private MischPult mischPult;
	private Stage stage;
	private ObservableList<Track> leftSongList;
	private ObservableList<Playlist> leftPlaylistList;
	private ObservableList<Track> sampleList;
	
	private ListView<Track> leftSongView;
	private ListView<Playlist> leftPlaylistView;
	private ListView<Track> sampleListView;
	
	
	public SecondViewController(Main application,MischPult mischPult, Stage primaryStage ) {
		super(application);
		//In SettingPlaylistLeft/RightView2 setzen wir die Elemente auch in die Liste, doppelt haelt besser
		this.mischPult= mischPult;
		
		rootView = new SecondView();
		
		SecondView view = (SecondView) rootView;
		this.stage = primaryStage;
		loadMP3 = view.loadMP3;
		
		SettingPlaylistLeftView2 sPLView2 = view.sPLView2;
		checkLeft = sPLView2.check;
		
		HeaderView headerView = view.headerView;
		back = headerView.back;
		folder = headerView.folder;
		
		leftSongList = FXCollections.observableArrayList();
		leftPlaylistList = FXCollections.observableArrayList();
		SettingPlaylistLeftView2 leftView = view.sPLView2;
		leftSongView = leftView.leftSongView;
		leftPlaylistView = leftView.leftPlaylistView;
		
		sampleList = FXCollections.observableArrayList();
		SamplePlaylistView2 sPLV2 = view.sPlaylistView2;
		sampleListView = sPLV2.sampleListView;
		
		selected = false;
		fChooser = new FileChooser();
		mischPult.addObserver(this);
		
		initialize();
	}
	@Override
	public void initialize() {
		//Alles egal so lange wir an Select Track nichts machen
		updatePlaylists();
		
		leftPlaylistView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Playlist>() {
			@Override
			public void changed(ObservableValue<? extends Playlist> observable, Playlist oldValue, Playlist newValue) {
				selected = true;
				try {
					leftSongView.setItems(mischPult.getManager().getList(newValue).getAllObsTracks());
				} catch (NullPointerException ez) {
					ez.printStackTrace();
				}
			}
			
		});
		
		checkLeft.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (selected) {
					System.out.println(leftPlaylistView.getSelectionModel().getSelectedItem().getTitle());
					mischPult.getLeftPlayer().setPlaylist(leftPlaylistView.getSelectionModel().getSelectedItem().getList());
					//working
				}
			}
		});
		
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
		
		loadMP3.setOnAction(event -> {
			configureFileChooser(fChooser);
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MP3 files (*.mp3)", "*.mp3");
			fChooser.getExtensionFilters().clear();
			fChooser.getExtensionFilters().add(extFilter);
			List<File> list = fChooser.showOpenMultipleDialog(stage);
			if (list != null) {
				for (File file : list) {
					try {
							Track newSong = new Track(file.getAbsolutePath());
							mischPult.getManager().getSampleList().addSingleTrack(newSong);
							sampleList.add(newSong); //Hatte vorher noch .getTitle ist aber eine Track List
							sampleListView.setItems(sampleList);
					} catch(Exception e){
						e.printStackTrace(); //evtl catchen einer general exception nicht gut
					}
					/*catch (InvalidDataException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (UnsupportedTagException e) {
						e.printStackTrace();
					}*/
				}
			}});
	}
	
	public void selectPlaylist() {
		
	}
	
	public void updatePlaylists() {
		try {
			leftPlaylistList = mischPult.getManager().getAllNames();
			leftPlaylistView.setItems(leftPlaylistList);
			//Und mit bestimmter Playlist aufgerufen werden soll
		} catch (NullPointerException ez) {
			ez.printStackTrace();
		} 
	}
	@Override
	public void update(Observable o, Object arg) {
		if(arg == "neue playlist") {
			updatePlaylists();
		}
		//Evtl so lösen, also als Observer wenn sich in PlaylistView was ändert
		
	}
	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("Open Playlist");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}
}
