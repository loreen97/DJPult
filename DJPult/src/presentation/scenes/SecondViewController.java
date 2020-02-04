package presentation.scenes;

import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import application.Main;
import business.MischPult;
import business.Playlist;
import business.Track;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import presentation.UIComponents.HeaderView;
import presentation.UIComponents.SamplePlaylistView;
import presentation.UIComponents.SamplesView;
import presentation.UIComponents.SettingPlaylistLeftView;
import presentation.UIComponents.SettingPlaylistRightView;

public class SecondViewController extends ViewController<Main> implements Observer {

	/**
	 * Die 2. Seite Also zum Auswählen von bereits gespeicherten Playlists zeigt
	 * Links die Playlist Namen und rechts die Namen der Titel der ausgewählten
	 * Playlist Ziel: Mit Cells einrichten
	 */

	private Button back;
	private Button folder;
	private Button checkLeft, checkRight;
	private Button loadMP3;
	private boolean selected, sampleSelected, sampleListSelected;
	private Button save;
	private Button deleteLeft, deleteRight;

	private final FileChooser fChooser;

	private MischPult mischPult;
	private Stage stage;
	private ObservableList<Track> leftSongList;
	private ObservableList<Playlist> leftPlaylistList;
	private ObservableList<Track> rightSongList;
	private ObservableList<Playlist> rightPlaylistList;

	private ListView<Track> leftSongView;
	private ListView<Playlist> leftPlaylistView;
	private ListView<Track> rightSongView;
	private ListView<Playlist> rightPlaylistView;

	private ListView<Track> sampleListView;
	private ObservableList<Track> sampleList;

	public Button sample1;
	public Button sample2;
	public Button sample3;
	public Button sample4;
	public Button sample5;
	public Button sample6;
	
	private int lastClicked = -1;

	public SecondViewController(Main application, MischPult mischPult, Stage primaryStage) {
		super(application);
		// In SettingPlaylistLeft/RightView2 setzen wir die Elemente auch in die Liste,
		// doppelt haelt besser
		this.mischPult = mischPult;

		rootView = new SecondView();

		SecondView view = (SecondView) rootView;
		this.stage = primaryStage;
		loadMP3 = view.loadMP3;
		save = view.save;

		HeaderView headerView = view.headerView;
		back = headerView.back;
		folder = headerView.folder;

		SettingPlaylistLeftView leftView = view.sPLView;
		leftSongView = leftView.leftSongView;
		leftPlaylistView = leftView.leftPlaylistView;
		checkLeft = leftView.check;
		deleteLeft = leftView.delete;

		SettingPlaylistRightView rightView = view.sPRView;
		rightSongView = rightView.rightSongView;
		rightPlaylistView = rightView.rightPlaylistView;
		checkRight = rightView.check;
		deleteRight = rightView.delete;

		SamplePlaylistView sampleView = view.sPlaylistView;
		sampleListView = sampleView.sampleListView;

		SamplesView samplesView = view.samplesView;
		sample1 = samplesView.sample1;
		sample2 = samplesView.sample2;
		sample3 = samplesView.sample3;
		sample4 = samplesView.sample4;
		sample5 = samplesView.sample5;
		sample6 = samplesView.sample6;

		sampleList = FXCollections.observableArrayList();
		leftSongList = FXCollections.observableArrayList();
		leftPlaylistList = FXCollections.observableArrayList();
		rightSongList = FXCollections.observableArrayList();
		rightPlaylistList = FXCollections.observableArrayList();

		selected = false;
		sampleSelected = false;
		sampleListSelected = false;
		fChooser = new FileChooser();
		mischPult.addObserver(this);

		initialize();
	}

	@Override
	public void initialize() {
		// Alles egal so lange wir an Select Track nichts machen
		updatePlaylists();

		leftPlaylistView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Playlist>() {
			@Override
			public void changed(ObservableValue<? extends Playlist> observable, Playlist oldValue, Playlist newValue) {
				selected = true;
				try {
					leftSongView.setItems(mischPult.getManager().getList(newValue).getAllObsTracks());
				} catch (NullPointerException ez) {
					System.out.println("Die Playlist scheint es nicht zu geben! Es konnten keine Lieder geladen werden.");
				}
			}

		});

		rightPlaylistView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Playlist>() {
			@Override
			public void changed(ObservableValue<? extends Playlist> observable, Playlist oldValue, Playlist newValue) {
				selected = true;
				try {
					rightSongView.setItems(mischPult.getManager().getList(newValue).getAllObsTracks());
				} catch (NullPointerException ez) {
					System.out.println("Die Playlist scheint es nicht zu geben! Es konnten keine Lieder geladen werden.");
				}
			}

		});

		// updaten?? in der Playlist
		// nur mal Deko
		deleteLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (selected) {
					mischPult.getManager().deletePlaylist(leftPlaylistView.getSelectionModel().getSelectedItem());
					System.out.println("loeschen");
				}
			}

		});
		// updaten?? in der Playlist
		// nur mal Deko
		deleteRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (selected) {
					mischPult.getManager().deletePlaylist(rightPlaylistView.getSelectionModel().getSelectedItem());
					System.out.println("loeschen");
				}
			}

		});
		checkLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (selected) {
					//System.out.println(leftPlaylistView.getSelectionModel().getSelectedItem().getTitle());
					try {
					mischPult.getLeftPlayer()
							.setPlaylist(leftPlaylistView.getSelectionModel().getSelectedItem().getList());
					} catch (NullPointerException ez) {
						System.out.println("Es ist keine Playlist vorhanden, die geladen werden koennte!");
					}
					// working
				}
			}
		});
		checkRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (selected) {
					try{
					System.out.println(rightPlaylistView.getSelectionModel().getSelectedItem().getTitle());
					} catch (NullPointerException ez) {
						System.out.println("Es ist keine Playlist vorhanden, die geladen werden koennte!");
					}
					try{
						mischPult.getRightPlayer()
							.setPlaylist(rightPlaylistView.getSelectionModel().getSelectedItem().getList());
					} catch (NullPointerException ez) {
						System.out.println("Es ist keine Playlist vorhanden, die geladen werden koennte!");
					}
				}
			}
		});
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (application != null) {
					application.switchScene(Scenes.PULT_VIEW);
				}
			}
		});
		folder.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (application != null) {
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
						mischPult.getSampleList().addSingleTrack(newSong);
						sampleList.add(newSong); // Hatte vorher noch .getTitle ist aber eine Track List
						sampleListView.setItems(sampleList);
					} catch (Exception e) {
						e.printStackTrace(); // evtl catchen einer general exception nicht gut
					}
				}
			}
		});
		
		sample1.setOnAction(event -> {
			lastClicked = 1;
		});
		sample2.setOnAction(event -> {
			lastClicked = 2;
		});
		sample3.setOnAction(event -> {
			lastClicked = 3;
		});
		sample4.setOnAction(event -> {
			lastClicked = 4;
		});
		sample5.setOnAction(event -> {
			lastClicked = 5;
		});
		sample6.setOnAction(event -> {
			lastClicked = 6;
		});

		sampleListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Track>() {
			@Override
			public void changed(ObservableValue<? extends Track> observable, Track oldValue, Track newValue) {
				sampleListSelected = true;
				selectSample(lastClicked, newValue);
			}
		});
		sampleListSelected = false;
	}
	
	
	//Selbes Lied hintereinander weg setzen -> letztgesetztes Sample wird wieder belegt, nicht das neue
	public void selectSample(int num, Track tname) {
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (sampleListSelected && lastClicked > 0) {
					try {
						mischPult.getSample("sample"+num).setSample(tname);
						System.out.println("Sample" +num + " gesetzt!");
					} catch (NullPointerException ez) {
						System.out.println("Bitte erst den Button und dann das Lied auswaehlen!");
					}
					lastClicked = -1;
					
				}
			}
		});
	}
	
	public void deletePlaylist(Playlist list) {
		mischPult.getManager().deletePlaylist(list);
	}
	
	public void updatePlaylists() {
		try {
			leftPlaylistList = mischPult.getManager().getAllLists();
			leftPlaylistView.setItems(leftPlaylistList);

			rightPlaylistList = mischPult.getManager().getAllLists();
			rightPlaylistView.setItems(rightPlaylistList);

			// Und mit bestimmter Playlist aufgerufen werden soll
		} catch (NullPointerException ez) {
			ez.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == "neue playlist") {
			updatePlaylists();
		}
	}

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("Open Playlist");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}
}
