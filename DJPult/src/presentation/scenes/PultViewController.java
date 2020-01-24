package presentation.scenes;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentation.UIComponents.ControlView;
import presentation.UIComponents.SamplesView;
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

	private Slider tune1;
	private Slider tune2;
	private Slider tune3;

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
		tune1 = tuneViewLeft.tune1;
		tune2 = tuneViewLeft.tune2;
		tune3 = tuneViewLeft.tune3;

		playingLeft = false;
		playingRight = false;
		pushed = false;

		initialize();
	}

	public void initialize() {

		mischPult.addObserver(this); //Das umschreiben?

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
				if (!playingLeft) {
					mischPult.play("links");
					playLeft.getStyleClass().clear();
					playLeft.getStyleClass().addAll("control-button", "pause");
					playingLeft = true;
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

		//LOOP im Mischpult geht noch nicht
		loopLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!pushed) {
					mischPult.loop("links");
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
		
		//LOOP im Mischpult geht noch nicht
		loopRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!pushed) {
					mischPult.loop("rechts");
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
		
		
		tune1.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune1("links", tune1.getValue());
		});
		tune1.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = true;
				System.out.println(userInteraction);
			}
		});
		tune1.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = false;
				System.out.println(userInteraction);
			}
		});
		
		tune2.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune2("links", tune2.getValue());
		});
		tune2.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = true;
				System.out.println(userInteraction);
			}
		});
		tune2.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = false;
				System.out.println(userInteraction);
			}
		});
		
		tune3.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeTune3("links", tune3.getValue());
		});
		tune3.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = true;
				System.out.println(userInteraction);
			}
		});
		tune3.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = false;
				System.out.println(userInteraction);
			}
		});

		
		songSliderLeft.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeBySlider("links", songSliderLeft.getValue());
		});
		songSliderLeft.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = true;
				System.out.println(userInteraction);
			}
		});
		songSliderLeft.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = false;
				System.out.println(userInteraction);
			}
		});

		songSliderRight.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeBySlider("rechts", songSliderRight.getValue());
		});
		songSliderRight.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = true;
				System.out.println(userInteraction);
			}
		});
		songSliderRight.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				userInteraction = false;
				System.out.println(userInteraction);
			}
		});

		eineinhalbLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed("links",1.5);
			}
		});
		eineinhalbRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed("rechts",1.5);
			}
		});
		
		halbLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed("links",.5);
			}
		});
		halbRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed("rechts",.5);
			}
		});
		
		doppeltLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed("links",2);
			}
		});
		doppeltRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mischPult.speed("rechts",2);
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
				mischPult.speed("rechts",1);
			}
		});
		
		//test
		volumeLeft.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(javafx.beans.Observable ov) {
				if(volumeLeft.isValueChanging()) {
					mischPult.setVolume("links", volumeLeft.getValue() / 100.0);
					//mischPult.volume(volumeLeft.getValue()/100.0);
				}
			}
		});
		
		volumeRight.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(javafx.beans.Observable ov) {
				if(volumeRight.isValueChanging()) {
					mischPult.setVolume("rechts", volumeRight.getValue() / 100.0);
					//mischPult.volume(volumeLeft.getValue()/100.0);
				}
			}
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Platform.runLater(() -> {
			Track track = (Track) arg1;
			//titleLeft.setText(track.getTitle());
			//titleRight.setText(track.getTitle());
		});
	}

}
