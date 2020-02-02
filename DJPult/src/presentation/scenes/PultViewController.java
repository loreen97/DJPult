package presentation.scenes;

import java.io.ByteArrayInputStream;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import application.Main;
import business.MischPult;
import business.Player;
import business.Playlist;
import business.Track;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;
import javafx.util.Duration;
import presentation.UIComponents.ControlView;
import presentation.UIComponents.PultPlaylistView2;
import presentation.UIComponents.SamplesView;
import presentation.UIComponents.TitleView;
import presentation.UIComponents.TuneView;
import presentation.UIComponents.Visualizer;
import presentation.UIComponents.VolumeView;

public class PultViewController extends ViewController<Main> implements Observer {
	// text
	private Button sample1;
	private Button sample2;
	private Button sample3;
	private Button sample4;
	private Button sample5;
	private Button sample6;

	private Button setting;
	private Button playLeft, playRight;
	private Button loopLeft, loopRight;
	private Button eineinhalbLeft, eineinhalbRight;
	private Button halbLeft, halbRight;
	private Button doppeltLeft, doppeltRight;
	private Button normalLeft, normalRight;

	private Stage stage;
	private MischPult mischPult;
	private Player playerLeft, playerRight; // wsl ueberfluessig ODER zum Aufrufen von Methoden?????

	private Slider songSliderLeft, songSliderRight;
	private Label titleLeft, titleRight;
	private Slider volumeLeft, volumeRight;

	private Slider tune1Left;
	private Slider tune2Left;
	private Slider tune3Left;
	private Slider tune1Right;
	private Slider tune2Right;
	private Slider tune3Right;

	private boolean playingLeft, playingRight, pushed;
	private boolean userInteraction;

	ListView<Track> trackListViewLeft;
	ListView<Track> trackListViewRight;

	Visualizer visualizerLeft, visualizerRight;
	Pane paneL;
	Pane paneR;

	public PultViewController(Main application, MischPult mischPult, Stage primaryStage) {
		super(application);

		this.mischPult = mischPult;

		rootView = new PultView();

		PultView view = (PultView) rootView;
		this.stage = primaryStage;

		setting = view.setting;

		ControlView controlViewLeft = view.controlViewLeft;
		playLeft = controlViewLeft.play;
		loopLeft = controlViewLeft.loop;
		eineinhalbLeft = controlViewLeft.eineinhalb;
		halbLeft = controlViewLeft.halb;
		doppeltLeft = controlViewLeft.doppelt;
		normalLeft = controlViewLeft.normal;

		ControlView controlViewRight = view.controlViewRight;
		playRight = controlViewRight.play;
		loopRight = controlViewRight.loop;
		eineinhalbRight = controlViewRight.eineinhalb;
		halbRight = controlViewRight.halb;
		doppeltRight = controlViewRight.doppelt;
		normalRight = controlViewRight.normal;

		SamplesView samplesView = view.samplesView;
		sample1 = samplesView.sample1;
		sample2 = samplesView.sample2;
		sample3 = samplesView.sample3;
		sample4 = samplesView.sample4;
		sample5 = samplesView.sample5;
		sample6 = samplesView.sample6;

		TitleView titleViewLeft = view.titleViewLeft;
		songSliderLeft = titleViewLeft.songSlider;
		titleLeft = titleViewLeft.title;

		TitleView titleViewRight = view.titleViewRight;
		songSliderRight = titleViewRight.songSlider;
		titleRight = titleViewRight.title;

		VolumeView volumeViewLeft = view.volumeViewLeft;
		volumeLeft = volumeViewLeft.volume;

		VolumeView volumeViewRight = view.volumeViewRight;
		volumeRight = volumeViewRight.volume;

		TuneView tuneViewLeft = view.tuneViewLeft;
		tune1Left = tuneViewLeft.tune1;
		tune2Left = tuneViewLeft.tune2;
		tune3Left = tuneViewLeft.tune3;

		TuneView tuneViewRight = view.tuneViewRight;
		tune1Right = tuneViewRight.tune1;
		tune2Right = tuneViewRight.tune2;
		tune3Right = tuneViewRight.tune3;

		playingLeft = false;
		playingRight = false;
		pushed = false;

		visualizerLeft = new Visualizer();
		visualizerRight = new Visualizer();
		Visualizer visualizerLeft = view.visualizerLeft;
		paneL = visualizerLeft.pane;

		Visualizer visualizerRight = view.visualizerRight;
		paneR = visualizerRight.pane;

		PultPlaylistView2 pPV2 = view.pPlaylistView2;
		trackListViewLeft = pPV2.trackListViewLeft;
		trackListViewRight = pPV2.trackListViewRight;

		initialize();
	}

	public void initialize() {

		mischPult.addObserver(this); // Das umschreiben? Pro Player Seite, aber eher nicht

		visualizerLeft.start(30, paneL);
		visualizerRight.start(30, paneR);

		setting.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (application != null) {
					application.switchScene(Scenes.SETTING_VIEW);
				}
			}
		});
		
		//Wenn das einkommentiert: Erste Playlist wird angezeigt
		//trackListViewLeft.setItems(mischPult.getLeftPlayer().getPlaylist().getAllObsTracks());
		//trackListViewRight.setItems(mischPult.getRightPlayer().getPlaylist().getAllObsTracks());
		selectSong("links");

		// Soll irgendwie den Titel als Text annehmen und in der Playlist suchen oder so
		/*
		 * trackListViewLeft.getSelectionModel().selectedItemProperty().addListener(new
		 * ChangeListener<String>() { public void changed(ObservableValue<? extends
		 * String> oV, String oldValue, String newValue) {
		 * 
		 * /*if (mischPult.getLeftPlayer().getMediaPlayer().getStatus().PLAYING) {
		 * mischPult.getLeftPlayer().stop();
		 * 
		 * mischPult.getLeftPlayer().getPlaylist().getTrackByTitle();
		 * mischPult.getLeftPlayer().play();
		 * 
		 * play.getStyleClass().clear(); play.getStyleClass().addAll("control-button",
		 * "pause-button"); playing = true; } });
		 */

		// Song Slider, testweise in methode ausgelagert
		updateSlider("links");

		songSliderLeft.setOnMouseClicked(songSlider("links"));
		songSliderLeft.setOnMouseDragged(songSlider("links"));

		updateSlider("rechts");

		songSliderRight.setOnMouseClicked(songSlider("rechts"));
		songSliderRight.setOnMouseDragged(songSlider("rechts"));

		// Play Song
		playLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!playingLeft) {
					mischPult.play("links");
					playLeft.getStyleClass().clear();
					playLeft.getStyleClass().addAll("control-button", "pause");
					playingLeft = true;
					waves("links");
					// mischPult.getLeftPlayer().getMediaPlayer().setOnEndOfMedia(() -> reset Slider
					// zeug irgendwie aber nicht hier... weil sonst nur wenn play pressed
				} else {
					mischPult.pause("links");
					playLeft.getStyleClass().clear();
					playLeft.getStyleClass().addAll("control-button", "play");
					playingLeft = false;
				}
			}
		});

		playRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!playingRight) {
					mischPult.play("rechts");
					playRight.getStyleClass().clear();
					playRight.getStyleClass().addAll("control-button", "pause");
					playingRight = true;
					waves("rechts");
				} else {
					mischPult.pause("rechts");
					playRight.getStyleClass().clear();
					playRight.getStyleClass().addAll("control-button", "play");
					playingRight = false;
				}
			}
		});

		/*
		 * if(mischPult.getLeftPlayer().getMediaPlayer().getStatus() != Status.PLAYING)
		 * { mischPult.getLeftPlayer().getMediaPlayer().(mischPult.getLeftPlayer().
		 * getMediaPlayer().currentTimeProperty()); }
		 */

		// Samples
		sample1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.playSample("sample1");
			}
		});
		sample2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.playSample("sample2");
			}
		});
		sample3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.playSample("sample3");
			}
		});
		sample4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.playSample("sample4");
			}
		});
		sample5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.playSample("sample5");
			}
		});
		sample6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.playSample("sample6");
			}
		});

		// Loop
		loopLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!pushed) {
					mischPult.loop("links");
					loopLeft.getStyleClass().clear();
					loopLeft.getStyleClass().addAll("control-button", "pushLoop");
					pushed = true;
				} else {
					mischPult.loop("links");
					loopLeft.getStyleClass().clear();
					loopLeft.getStyleClass().addAll("control-button", "loop");
					pushed = false;
				}
			}
		});

		loopRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!pushed) {
					mischPult.loop("rechts");
					loopRight.getStyleClass().clear();
					loopRight.getStyleClass().addAll("control-button", "pushLoop");
					pushed = true;
				} else {
					mischPult.loop("rechts");
					loopRight.getStyleClass().clear();
					loopRight.getStyleClass().addAll("control-button", "loop");
					pushed = false;
				}
			}
		});

		// Tune Slider
		tune1Left.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune1("links", tune1Left.getValue());
		});
		/*
		 * tune1Left.setOnMousePressed(new EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent event) { userInteraction = true;
		 * System.out.println(userInteraction); } }); tune1Left.setOnMouseReleased(new
		 * EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent event) { userInteraction = false;
		 * System.out.println(userInteraction); } });
		 */
		tune1Left.setOnMousePressed(event -> userInteraction = true);
		tune1Left.setOnMouseReleased(event -> userInteraction = false);

		tune2Left.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune2("links", tune2Left.getValue());
		});

		tune2Left.setOnMousePressed(event -> userInteraction = true);
		tune2Left.setOnMouseReleased(event -> userInteraction = false);

		tune3Left.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune3("links", tune3Left.getValue());
		});

		tune3Left.setOnMousePressed(event -> userInteraction = true);
		tune3Left.setOnMouseReleased(event -> userInteraction = false);

		tune1Right.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune1("rechts", tune1Right.getValue());
		});

		tune1Right.setOnMousePressed(event -> userInteraction = true);
		tune1Right.setOnMouseReleased(event -> userInteraction = false);

		tune2Right.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune2("rechts", tune2Right.getValue());
		});

		tune2Right.setOnMousePressed(event -> userInteraction = true);
		tune2Right.setOnMouseReleased(event -> userInteraction = false);

		tune3Right.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune3("rechts", tune3Right.getValue());
		});

		tune3Right.setOnMousePressed(event -> userInteraction = true);
		tune3Right.setOnMouseReleased(event -> userInteraction = false);

		// Geschwindigkeit
		/*
		 * eineinhalbLeft.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent event) { mischPult.speed("links",
		 * 1.5); } });
		 */

		eineinhalbLeft.setOnAction(event -> mischPult.speed("links", 1.5));
		eineinhalbRight.setOnAction(event -> mischPult.speed("rechts", 1.5));

		halbLeft.setOnAction(event -> mischPult.speed("links", .5));
		halbRight.setOnAction(event -> mischPult.speed("rechts", .5));

		doppeltLeft.setOnAction(event -> mischPult.speed("links", 2));
		doppeltRight.setOnAction(event -> mischPult.speed("rechts", 2));

		normalLeft.setOnAction(event -> mischPult.speed("links", 1));
		normalRight.setOnAction(event -> mischPult.speed("rechts", 1));

		// Lautstaerke
		volumeLeft.setValue(mischPult.getVolume("links") * 100);
		volumeLeft.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(javafx.beans.Observable ov) {
				if (volumeLeft.isValueChanging()) {
					mischPult.setVolume("links", volumeLeft.getValue() / 100.0);
				}
			}
		});

		volumeRight.setValue(mischPult.getVolume("rechts") * 100);
		volumeRight.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(javafx.beans.Observable ov) {
				if (volumeRight.isValueChanging()) {
					mischPult.setVolume("rechts", volumeRight.getValue() / 100.0);
				}
			}
		});

		// Aendern der Titel
		changeableGuiElements("links", mischPult.getLeftPlayer().getActSong());
		mischPult.getLeftPlayer().getIndex()
				.addListener(((observable, oldValue, newValue) -> changeableGuiElements("links",
						mischPult.getAPlaylist("links").getTrack((Integer) newValue))));

		changeableGuiElements("rechts", mischPult.getRightPlayer().getActSong());
		mischPult.getRightPlayer().getIndex()
				.addListener(((observable, oldValue, newValue) -> changeableGuiElements("rechts",
						mischPult.getAPlaylist("rechts").getTrack((Integer) newValue))));
	}

	public void selectSong(String name) {
		ListView<Track> v;
		Player m;
		Button b;
		if (name == "links") {
			v = trackListViewLeft;
			m = mischPult.getLeftPlayer();
			b = playLeft;
		} else {
			v = trackListViewRight;
			m = mischPult.getRightPlayer();
			b = playRight;
		}
		v.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2)
				m.getMediaPlayer().stop();
			m.loadFromIndex(v.getSelectionModel().getSelectedIndex());
			m.play();
			b.getStyleClass().clear();
			b.getStyleClass().addAll("control-button", "pause");
			if (name == "links") {
				playingLeft = true;
			} else {
				playingRight = true;
			}
		});
		update(null, "neu");
	}

	// Zum Updaten des titels bei Songwechsel
	public void changeableGuiElements(String name, Track song) {
		if (name == "links") {
			try {
				titleLeft.setText(mischPult.getActSong("links").getTitle());
				songSlider("links");
				// Nur eine optische Änderung:
				songSliderLeft.setValue(0);
			} finally {
				if (song.getTitle() == null) {
					titleLeft.setText("Unbekannter Titel");
				}
			}
		} else if (name == "rechts") {
			try {
				titleRight.setText(mischPult.getActSong("rechts").getTitle());
				songSlider("rechts");
			} finally {
				if (song.getTitle() == null) {
					titleRight.setText("Unbekannter Titel");
				}
			}
		}
	}

	// nutzlos
	private void updateSlider(String name) {
		MediaPlayer m;
		Slider s;
		if (name == "links") {
			m = mischPult.getLeftPlayer().getMediaPlayer();
			s = songSliderLeft;
		} else {
			m = mischPult.getRightPlayer().getMediaPlayer();
			s = songSliderRight;
		}
		// Soll den Slider updaten bzw resetten wenn Lied vorbei
		// Sollte ein update aus dem player bekommen
		m.seek(Duration.ZERO);
		m.currentTimeProperty().addListener((observableValue, oldDuration, newDuration) -> {
			Duration time = m.getCurrentTime();
			Duration total = m.getTotalDuration();
			if (!s.isValueChanging() && total.greaterThan(Duration.ZERO)) {
				s.setValue(time.toMillis() / total.toMillis() * 100);
			}
		});
	}

	// Wahrscheinlich durch die obere Methode ersetzen
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 == "neu") {
			updateSlider("links");
			songSliderLeft.setOnMouseClicked(songSlider("links"));
			songSliderLeft.setOnMouseDragged(songSlider("links"));
			waves("links");
			updateSlider("rechts");
			songSliderRight.setOnMouseClicked(songSlider("rechts"));
			songSliderRight.setOnMouseDragged(songSlider("rechts"));
			waves("rechts");
		} else if(arg1 == "new Playlist"){
			trackListViewLeft.setItems(mischPult.getLeftPlayer().getPlaylist().getAllObsTracks());
		}
		Platform.runLater(() -> {
			// Track track = (Track) arg1;
			titleLeft.setText(mischPult.getActSong("links").getTitle());
			titleRight.setText(mischPult.getActSong("rechts").getTitle());
		});

	}

	// Zum Aendern des Song Progresses anhand des Sliders
	private EventHandler<MouseEvent> songSlider(String name) {
		MediaPlayer m;
		if (name == "links") {
			m = mischPult.getLeftPlayer().getMediaPlayer();
		} else {
			m = mischPult.getRightPlayer().getMediaPlayer();
		}
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (m == null) {
					event.consume();
				} else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED
						|| event.getEventType() == MouseEvent.MOUSE_CLICKED) {
					if (name == "links") {
						m.seek(m.getTotalDuration().multiply(event.getX() / songSliderLeft.getWidth()));
					} else {
						m.seek(m.getTotalDuration().multiply(event.getX() / songSliderRight.getWidth()));
					}
				}
			}
		};
	}

	public void waves(String name) {
		// Resetten, um via click auf Playlist wieder auf Anfang der anzeige zu kommen
		MediaPlayer m;
		Visualizer v;
		Pane p;
		if (name == "rechts") {
			m = mischPult.getRightPlayer().getMediaPlayer();
			v = visualizerRight;
			p = paneR;
		} else {
			m = mischPult.getLeftPlayer().getMediaPlayer();
			v = visualizerLeft;
			p = paneL;
		}
		v.start(30, p);
		Duration duration = m.getTotalDuration();
		Duration ct = m.getCurrentTime();

		m.setAudioSpectrumNumBands(100);
		m.setAudioSpectrumInterval(0.05);
		m.setAudioSpectrumListener((double timestamp, double durations, float[] magnitudes, float[] phases) -> {
			v.wavesUpdate(timestamp, durations, magnitudes, phases, m);
		});
	}
}
