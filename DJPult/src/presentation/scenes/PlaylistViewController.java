package presentation.scenes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import application.Main;
import business.MischPult;
import business.Track;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PlaylistViewController extends ViewController<Main> {

	private Stage stage;
	private Button back;
	private Button save, loadMP3;
	private TextField titleInput;

	private ArrayList<Track> songs;
	private ObservableList<Track> songNames;
	private ListView<Track> songlistView;

	private final FileChooser fChooser;
	private TextArea textArea;

	private MischPult mischPult;

	public PlaylistViewController(Main application, MischPult mischPult, Stage primaryStage) {
		super(application);

		this.mischPult = mischPult;

		rootView = new PlaylistView();

		PlaylistView view = (PlaylistView) rootView;
		this.stage = primaryStage;

		back = view.back;
		textArea = view.textArea;
		save = view.save;
		loadMP3 = view.loadMP3;
		titleInput = view.title;

		this.songs = new ArrayList<Track>();
		this.songNames = view.songNames;
		this.songlistView = view.songlistView;

		fChooser = new FileChooser();

		initialize();

	}

	@Override
	public void initialize() {
		back.setOnAction(event -> {
			application.switchScene(Scenes.SETTING_VIEW);

		});

		save.setOnAction(event -> {
			//Entweder Playlist-Liste im pManager erstellen (also pName und Pfad fuer Dateien mitgeben)
			//Oder pList-Liste hier erstellen und an pManager uebergeben (also Name und Liste mit Liedern)
			//mischPult.getPlaylistManager.createPlaylist(title.getText(, pfad));
			//Aktuell geben wir wohl eine ArrayList mit?
			//Wuerde es eher im PManager machen
			if (titleInput.getText() != null && songs.size() > 2) {
				mischPult.getManager().createPlaylist(titleInput.getText(),songs);
				application.switchScene(Scenes.SETTING_VIEW);
				System.out.println("Kreiieren der Playlist war erfolgreich");
			} else {
				System.out.println("Bitte gebe der Playlist einen Titel und mindestens 3 songs");
			}
			mischPult.setPlaylist(mischPult.getManager().getList(titleInput.getText()));
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
							songs.add(newSong);
							songNames.add(newSong); //Hatte vorher noch .getTitle ist aber eine Track List
							songlistView.setItems(songNames);
						
					} catch(Exception e){
						
					}
					/*catch (InvalidDataException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (UnsupportedTagException e) {
						e.printStackTrace();
					}*/
				}
			}
		});
	}

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("Open Playlist");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}
}
