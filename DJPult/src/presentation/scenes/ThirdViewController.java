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
import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ThirdViewController extends ViewController<Main>{
	
	/**
	 * Controlle für die 3. Seite
	 * Zum Erstellen neuer Playlists
	 */
	
	private Stage stage;
	private Button back;
	private Button save, loadMP3;
	private TextField titleInput;
	private Label infoLabel;
	
	private ArrayList<Track> songs;
	private ObservableList<Track> songNames;
	private ListView<Track> songlistView;

	private final FileChooser fChooser;
	private TextArea textArea;

	private MischPult mischPult;

	public ThirdViewController(Main application, MischPult mischPult, Stage primaryStage) {
		super(application);

		this.mischPult = mischPult;

		rootView = new ThirdView();

		ThirdView view = (ThirdView) rootView;
		this.stage = primaryStage;

		back = view.back;
		textArea = view.textArea;
		save = view.save;
		loadMP3 = view.loadMP3;
		titleInput = view.title;
		infoLabel = view.infoLabel;

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
			titleInput.clear();
			infoLabel.setOpacity(0);
		});

		save.setOnAction(event -> {

			if (titleInput.getText() != null && songs.size() > 2) {
				mischPult.getManager().createPlaylist(titleInput.getText(),songs);
				application.switchScene(Scenes.SETTING_VIEW);
				titleInput.clear();
				infoLabel.setOpacity(0);
				System.out.println("Kreieren der Playlist war erfolgreich");
			} else {
				infoLabel.setOpacity(1);
			}
			//mischPult.setPlaylist(mischPult.getManager().getList(titleInput.getText()), "links"); //Test, soll ja eig nicht automatisch setzen 
			setChanged();
			notifyObservers("Neue Playlist");
			songs.clear();
			songNames.removeAll(songNames); 
		});

		loadMP3.setOnAction(event -> {
			configureFileChooser(fChooser);
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MP3 files (*.mp3)", "*.mp3");
			fChooser.getExtensionFilters().clear();
			fChooser.getExtensionFilters().add(extFilter);
			List<File> list = fChooser.showOpenMultipleDialog(stage);
			if (list != null) {
				for (File file : list) {
					try {	//wegene abs. Path haben die Titel wsl den Pfad als namen
							//fixen? 
							Track newSong = new Track(file.getAbsolutePath());
							songs.add(newSong);
							songNames.add(newSong); //Hatte vorher noch .getTitle ist aber eine Track List
							songlistView.setItems(songNames);
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
			}
			//super klasse ViewController extended Observable hierfuer
		});
	}

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("Open Playlist");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}
}
