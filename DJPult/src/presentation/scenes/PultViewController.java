package presentation.scenes;

import java.util.Observable;
import java.util.Observer;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentation.UIComponents.ControlView;
import presentation.UIComponents.TitleView;
import presentation.UIComponents.TuneView;
import presentation.UIComponents.VolumeView;

public class PultViewController extends ViewController<Main> implements Observer {

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
	private Player playerLeft, playerRight;

	private Slider songSliderLeft, songSliderRight;
	private Label titleLeft, titleRight;
	private Slider volumeLeft, volumeRight;

	private Slider tune2;

	private boolean playing, pushed;
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
		tune2 = tuneViewLeft.tune2;

		playing = false;
		pushed = false;

		initialize();
	}

	public void initialize() {

		mischPult.addObserver(this);

		setting.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (application != null) {
					application.switchScene(Scenes.SETTING_VIEW);
				}
			}
		});

		playLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!playing) {
					mischPult.play();
					playLeft.getStyleClass().clear();
					playLeft.getStyleClass().addAll("control-button", "pause");
					playing = true;
				} else {
					mischPult.pause();
					playLeft.getStyleClass().clear();
					playLeft.getStyleClass().addAll("control-button", "play");
					playing = false;
				}
			}
		});

		playRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!playing) {
					mischPult.play();
					playRight.getStyleClass().clear();
					playRight.getStyleClass().addAll("control-button", "pause");
					playing = true;
				} else {
					mischPult.pause();
					playRight.getStyleClass().clear();
					playRight.getStyleClass().addAll("control-button", "play");
					playing = false;
				}
			}
		});

		loopLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!pushed) {
					// mischPult.loop();
					loopLeft.getStyleClass().clear();
					loopLeft.getStyleClass().addAll("control-button", "pushLoop");
					pushed = true;
				} else {
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
					// mischPult.loop();
					loopRight.getStyleClass().clear();
					loopRight.getStyleClass().addAll("control-button", "pushLoop");
					pushed = true;
				} else {
					loopRight.getStyleClass().clear();
					loopRight.getStyleClass().addAll("control-button", "loop");
					pushed = false;
				}
			}
		});

		/*
		 * songSliderLeft.valueProperty().addListener((observable, oldValue, newValue)
		 * -> { if (userInteraction)
		 * mischPult.changeBySlider(songSliderLeft.getValue()); });
		 * songSliderLeft.setOnMousePressed(new EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent event) { userInteraction = true;
		 * System.out.println(userInteraction); } });
		 * songSliderLeft.setOnMouseReleased(new EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent event) { userInteraction = false;
		 * System.out.println(userInteraction); } });
		 * 
		 * songSliderRight.valueProperty().addListener((observable, oldValue, newValue)
		 * -> { if (userInteraction)
		 * mischPult.changeBySlider(songSliderRight.getValue()); });
		 * songSliderRight.setOnMousePressed(new EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent event) { userInteraction = true;
		 * System.out.println(userInteraction); } });
		 * songSliderRight.setOnMouseReleased(new EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent event) { userInteraction = false;
		 * System.out.println(userInteraction); } });
		 * 
		 * volumeLeft.valueProperty() .addListener((observable, oldValue, newValue) ->
		 * playerLeft.setVolume(volumeLeft.getValue()));
		 * 
		 * volumeRight.valueProperty() .addListener((observable, oldValue, newValue) ->
		 * playerRight.setVolume(volumeRight.getValue()));
		 */

		eineinhalbLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed(1.5);
			}
		});
		eineinhalbRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed(1.5);
			}
		});
		
		halbLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed(.5);
			}
		});
		halbRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed(.5);
			}
		});
		
		doppeltLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed(2);
			}
		});
		doppeltRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed(2);
			}
		});
		
		normalLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed(1);
			}
		});
		normalRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed(1);
			}
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Platform.runLater(() -> {
			Track track = (Track) arg1;
			titleLeft.setText(track.getTitle());
			titleRight.setText(track.getTitle());
		});
	}

}
