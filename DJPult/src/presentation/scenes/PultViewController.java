package presentation.scenes;

import java.io.ByteArrayInputStream;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.InvalidationListener;
import application.Main;
import business.MischPult;
import business.Player;
import business.Track;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import presentation.UIComponents.ControlView;
import presentation.UIComponents.SamplesView;
import presentation.UIComponents.TitleView;
import presentation.UIComponents.TuneView;
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
	private Player playerLeft, playerRight; // wsl ueberfluessig

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

		initialize();
	}

	public void initialize() {

		mischPult.addObserver(this); // Das umschreiben?

		setting.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (application != null) {
					application.switchScene(Scenes.SETTING_VIEW);
				}
			}
		});

		mischPult.getLeftPlayer().getMediaPlayer().currentTimeProperty()
				.addListener((observableValue, oldDuration, newDuration) -> {
					Duration time = mischPult.getLeftPlayer().getMediaPlayer().getCurrentTime();
					Duration total = mischPult.getLeftPlayer().getMediaPlayer().getTotalDuration();
					if (!songSliderLeft.isValueChanging() && total.greaterThan(Duration.ZERO)) {
						songSliderLeft.setValue(time.toMillis() / total.toMillis() * 100);
					}
				});
		
		songSliderLeft.setOnMouseClicked(songSlider("links"));
		songSliderLeft.setOnMouseDragged(songSlider("links"));

		mischPult.getRightPlayer().getMediaPlayer().currentTimeProperty()
				.addListener((observableValue, oldDuration, newDuration) -> {
					Duration time = mischPult.getRightPlayer().getMediaPlayer().getCurrentTime();
					Duration total = mischPult.getRightPlayer().getMediaPlayer().getTotalDuration();
					if (!songSliderRight.isValueChanging() && total.greaterThan(Duration.ZERO)) {
						songSliderRight.setValue(time.toMillis() / total.toMillis() * 100);
					}
				});
		
		songSliderRight.setOnMouseClicked(songSlider("rechts"));
		songSliderRight.setOnMouseDragged(songSlider("rechts"));

		playLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!playingLeft) {
					mischPult.play("links");
					playLeft.getStyleClass().clear();
					playLeft.getStyleClass().addAll("control-button", "pause");
					playingLeft = true;
					mischPult.getLeftPlayer().getMediaPlayer().setOnEndOfMedia(() -> mischPult.skip("links"));
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
					mischPult.getRightPlayer().getMediaPlayer().setOnEndOfMedia(() -> mischPult.skip("rechts"));
				} else {
					mischPult.pause("rechts");
					playRight.getStyleClass().clear();
					playRight.getStyleClass().addAll("control-button", "play");
					playingRight = false;
				}
			}
		});

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

		tune1Left.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune1("links", tune1Left.getValue());
		});
		tune1Left.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = true;
				System.out.println(userInteraction);
			}
		});
		tune1Left.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = false;
				System.out.println(userInteraction);
			}
		});

		tune2Left.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune2("links", tune2Left.getValue());
		});
		tune2Left.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = true;
				System.out.println(userInteraction);
			}
		});
		tune2Left.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = false;
				System.out.println(userInteraction);
			}
		});

		tune3Left.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune3("links", tune3Left.getValue());
		});
		tune3Left.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = true;
				System.out.println(userInteraction);
			}
		});
		tune3Left.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = false;
				System.out.println(userInteraction);
			}
		});

		tune1Right.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune1("rechts", tune1Right.getValue());
		});
		tune1Right.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = true;
				System.out.println(userInteraction);
			}
		});
		tune1Right.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = false;
				System.out.println(userInteraction);
			}
		});

		tune2Right.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune2("rechts", tune2Right.getValue());
		});
		tune2Right.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = true;
				System.out.println(userInteraction);
			}
		});
		tune2Right.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = false;
				System.out.println(userInteraction);
			}
		});

		tune3Right.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune3("rechts", tune3Right.getValue());
		});
		tune3Right.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = true;
				System.out.println(userInteraction);
			}
		});
		tune3Right.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = false;
				System.out.println(userInteraction);
			}
		});

		eineinhalbLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed("links", 1.5);
			}
		});
		eineinhalbRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed("rechts", 1.5);
			}
		});

		halbLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed("links", .5);
			}
		});
		halbRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed("rechts", .5);
			}
		});

		doppeltLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed("links", 2);
			}
		});
		doppeltRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed("rechts", 2);
			}
		});

		normalLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed("links", 1);
			}
		});
		normalRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed("rechts", 1);
			}
		});

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
		
		//Aendern der Titel
		changeableGuiElements("links", mischPult.getLeftPlayer().getActSong());
		mischPult.getLeftPlayer().getIndex()
				.addListener(((observable, oldValue, newValue) -> changeableGuiElements("links",
						mischPult.getAPlaylist("links").getTrack((Integer) newValue))));

		changeableGuiElements("rechts", mischPult.getRightPlayer().getActSong());
		mischPult.getRightPlayer().getIndex()
				.addListener(((observable, oldValue, newValue) -> changeableGuiElements("rechts",
						mischPult.getAPlaylist("rechts").getTrack((Integer) newValue))));
	}
	
	//Zum Updaten des titels bei Songwechsel
	public void changeableGuiElements(String name, Track song) {
		if (name == "links") {
			try {
				titleLeft.setText(mischPult.getActSong("links").getTitle());
				//does nothing:
				songSliderLeft.setValue(0);
				songSlider("links");
			} finally {
				if (song.getTitle() == null) {
					titleLeft.setText("Unbekannter Titel");
				}
			}
		} else if (name == "rechts") {
			try {
				titleRight.setText(mischPult.getActSong("rechts").getTitle());
				//does nothing:
				songSliderRight.setValue(0);
				songSlider("rechts");
			} finally {
				if (song.getTitle() == null) {
					titleRight.setText("Unbekannter Titel");
				}
			}
		}
	}
	
	//Wahrscheinlich durch die obere Methode ersetzen
	@Override
	public void update(Observable arg0, Object arg1) {
		Platform.runLater(() -> {
			// Track track = (Track) arg1;
			titleLeft.setText(mischPult.getActSong("links").getTitle());
			titleRight.setText(mischPult.getActSong("rechts").getTitle());
		});
	}

	//Zum Aendern des Song Progresses anhand des Sliders 
	private EventHandler<MouseEvent> songSlider(String name) {
		MediaPlayer m;
		if(name == "links") {
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
					if(name == "links") {
						m.seek(m.getTotalDuration().multiply(event.getX() / songSliderLeft.getWidth()));
					} else {
						m.seek(m.getTotalDuration().multiply(event.getX() / songSliderRight.getWidth()));
					}
				}
			}
		};
	}
}
