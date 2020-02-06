package presentation.scenes;

import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import application.Main;
import business.MischPult;
import business.Player;
import business.Track;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import presentation.UIComponents.PultPlaylistView;
import presentation.UIComponents.TuneView;
import presentation.UIComponents.Visualizer;
import presentation.UIComponents.VolumeView;

public class SideViewController extends ViewController<Main> implements Observer {
	private Button play;
	private Button einEinHalb, normal, doppelt, loop, halb;

	private Stage stage;
	private MischPult mischPult;
	private Player player;

	private Slider songSlider;
	private Label title;
	private Slider tune1;
	private Slider tune2;
	private Slider tune3;

	private boolean pushed, playing, userInteraction;

	private ListView<Track> trackListView;

	private Visualizer visualizer;
	private Pane pane;
	private RotateTransition rotate;
	private ImageView imgView;

	// private Pane pane;
	public SideViewController(Main application, MischPult mischPult, Stage primaryStage, Player player)
			throws FileNotFoundException {
		super(application);

		this.mischPult = mischPult;
		this.player = player;

		rootView = new PultView();
		PultView view = (PultView) rootView;
		this.stage = primaryStage;
		imgView = view.imgView;

		ControlView controlView = view.controlView;
		play = controlView.play;
		loop = controlView.loop;
		einEinHalb = controlView.einEinHalb;
		halb = controlView.halb;
		doppelt = controlView.doppelt;
		normal = controlView.normal;

		TitleView titleView = view.titleView;
		songSlider = titleView.songSlider;
		title = titleView.title;

		VolumeView volumeView = view.volumeView;
		volume = volumeView.volume;

		TuneView tuneView = view.tuneView;
		tune1 = tuneView.tune1;
		tune2 = tuneView.tune2;
		tune3 = tuneView.tune3;

		playing = false;
		pushed = false;

		visualizer = new Visualizer();
		Visualizer visualizer = view.visualizer;
		pane = visualizer.pane;

		PultPlaylistView pPV = view.pPlaylistView;
		trackListView = pPV.trackListView;

		Duration total = player.getMediaPlayer().getTotalDuration();
		rotate = new RotateTransition(Duration.seconds(total.toSeconds()), imgView);
		rotate.setFromAngle(0);
		rotate.setToAngle(360);
		rotate.setCycleCount(Timeline.INDEFINITE);
		rotate.setRate(2.0);

		initialize();

	}

	@Override
	public void initialize() {
		player.addObserver(this);
		visualizer.start(30, pane);

		selectSong();
		updateSlider();

		songSlider.setOnMouseClicked(songSlider());
		songSlider.setOnMouseDragged(songSlider());

		play.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!playing) {
					mischPult.play(player.getName());
					play.getStyleClass().clear();
					play.getStyleClass().addAll("control-button", "pause");
					playing = true;
					waves();
					rotate.play();
				} else {
					mischPult.pause(player.getName());
					play.getStyleClass().clear();
					play.getStyleClass().addAll("control-button", "play");
					playing = false;
					rotate.pause();
				}
			}
		});

		loop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!pushed) {
					mischPult.loop(player.getName());
					loop.getStyleClass().clear();
					loop.getStyleClass().addAll("control-button", "pushLoop");
					pushed = true;
				} else {
					mischPult.loop(player.getName());
					loop.getStyleClass().clear();
					loop.getStyleClass().addAll("control-button", "loop");
					pushed = false;
				}
			}
		});

		tune1.setValue(mischPult.getTune1(player.getName()));
		tune2.setValue(mischPult.getTune2(player.getName()));
		tune3.setValue(mischPult.getTune3(player.getName()));
		// Tune Slider
		tune1.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune1(player.getName(), tune1.getValue());
		});
		// Alternativ
		/*
		 * tune1.valueProperty().addListener(new ChangeListener<Number>() {
		 * 
		 * @Override public void changed(ObservableValue<? extends Number> arg0, Number
		 * oldValue, Number newValue) { if (player != null) {
		 * player.getMediaPlayer().getAudioEqualizer().getBands().get(0).setGain(
		 * newValue.doubleValue()); } } });
		 */

		tune1.setOnMousePressed(event -> userInteraction = true);
		tune1.setOnMouseReleased(event -> userInteraction = false);

		tune2.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune2(player.getName(), tune2.getValue());
		});

		tune2.setOnMousePressed(event -> userInteraction = true);
		tune2.setOnMouseReleased(event -> userInteraction = false);

		tune3.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune3(player.getName(), tune3.getValue());
		});

		tune3.setOnMousePressed(event -> userInteraction = true);
		tune3.setOnMouseReleased(event -> userInteraction = false);

		einEinHalb.setOnAction(event -> mischPult.speed(player.getName(), 1.5));
		halb.setOnAction(event -> mischPult.speed(player.getName(), .5));
		doppelt.setOnAction(event -> mischPult.speed(player.getName(), 2));
		normal.setOnAction(event -> mischPult.speed(player.getName(), 1));

		volume.setValue(mischPult.getVolume(player.getName()) * 100);
		volume.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(javafx.beans.Observable ov) {
				if (volume.isValueChanging()) {
					player.setVolume(volume.getValue() / 100.0);
				}
			}
		});

		changeableGuiElements(player.getActSong());
		player.getIndex().addListener(((observable, oldValue,
				newValue) -> changeableGuiElements(player.getPlaylist().getTrack((Integer) newValue))));

	}

	public void selectSong() {
		trackListView.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2)
				player.getMediaPlayer().stop();
			player.loadFromIndex(trackListView.getSelectionModel().getSelectedIndex());

			player.play();
			play.getStyleClass().clear();
			play.getStyleClass().addAll("control-button", "pause");
			playing = true;
		});
		update(null, "neu"); //HIER
	}

	public void changeableGuiElements(Track song) {
		try {
			title.setText(mischPult.getActSong(this.player.getName()).getTitle());
			songSlider();
			songSlider.setValue(0);
		} finally {
			if (song.getTitle() == null) {
				title.setText("Unbekannter Titel");
			}
		}
	}

	private void updateSlider() {
		this.player.getMediaPlayer().seek(Duration.ZERO);
		this.player.getMediaPlayer().currentTimeProperty().addListener((observableValue, oldDuration, newDuration) -> {
			Duration time = this.player.getMediaPlayer().getCurrentTime();
			Duration total = this.player.getMediaPlayer().getTotalDuration();
			if (!this.songSlider.isValueChanging() && total.greaterThan(Duration.ZERO)) {
				this.songSlider.setValue(time.toMillis() / total.toMillis() * 100);
			}
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == "neu") {
			songSlider.setValue(0);
			updateSlider();
			songSlider.setOnMouseClicked(songSlider());
			songSlider.setOnMouseDragged(songSlider());
			waves();
			trackListView.setItems(player.getPlaylist().getAllObsTracks());
			rotate.play();
		}
		if (arg == "newlist") {
			trackListView.setItems(player.getPlaylist().getAllObsTracks());
			if (playing) {
				mischPult.getLeftPlayer().pause();
				play.getStyleClass().clear();
				play.getStyleClass().addAll("control-button", "play");
				playing = false;
				rotate.pause();
			} else {
				play.getStyleClass().clear();
				play.getStyleClass().addAll("control-button", "pause");
				playing = true;
				rotate.play();
			}

		}
		Platform.runLater(() -> {
			title.setText(mischPult.getActSong(player.getName()).getTitle());
		});

	}

	// Zum Aendern des Song Progresses anhand des Sliders
	private EventHandler<MouseEvent> songSlider() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (player.getMediaPlayer() == null) {
					event.consume();
				} else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED
						|| event.getEventType() == MouseEvent.MOUSE_CLICKED) {
					player.getMediaPlayer().seek(
							player.getMediaPlayer().getTotalDuration().multiply(event.getX() / songSlider.getWidth()));
				}
			}
		};
	}

	public void waves() {
		player.getMediaPlayer().setAudioSpectrumNumBands(100);
		player.getMediaPlayer().setAudioSpectrumInterval(0.05);
		player.getMediaPlayer()
				.setAudioSpectrumListener((double timestamp, double durations, float[] magnitudes, float[] phases) -> {
					visualizer.wavesUpdate(timestamp, durations, magnitudes, phases, player.getMediaPlayer());
				});
	}*/

}
