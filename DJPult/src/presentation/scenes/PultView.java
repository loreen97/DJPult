package presentation.scenes;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.UIComponents.ControlView;
import presentation.UIComponents.SamplesView;
import presentation.UIComponents.PultPlaylistView2;
import presentation.UIComponents.TitleView;
import presentation.UIComponents.TuneView;
import presentation.UIComponents.VolumeView;
import presentation.UIComponents.WavesView;

public class PultView extends BorderPane {

	SamplesView samplesView;
	TitleView titleViewLeft, titleViewRight;
	ControlView controlViewLeft,controlViewRight;
	TuneView tuneViewLeft, tuneViewRight;
	VolumeView volumeViewLeft, volumeViewRight;
	PultPlaylistView2 pPlaylistView2;
	WavesView wavesViewLeft, wavesViewRight;
	
	public Button setting;
	public Button platteLeft, platteRight;
	
	public HBox hboxTop,hboxLeftTop,hboxRightTop,hboxCenter,hboxBottom,hboxLeft,hboxRight;
	public VBox boxWavesLeft, boxWavesRight;

	public PultView() {
		samplesView = new SamplesView();
		titleViewLeft = new TitleView();
		titleViewRight = new TitleView();
		controlViewLeft = new ControlView();
		controlViewRight = new ControlView();
		tuneViewLeft = new TuneView();
		tuneViewRight = new TuneView();
		volumeViewLeft = new VolumeView();
		volumeViewRight = new VolumeView();
		pPlaylistView2 = new PultPlaylistView2();
		wavesViewLeft = new WavesView();
		wavesViewRight = new WavesView();
		
		setting = new Button();
		setting.setId("setting");

		platteLeft = new Button();
		platteLeft.setId("platte");
		
		platteRight = new Button();
		platteRight.setId("platte");
		
		this.setTop(HBoxTop());
		this.setCenter(HBoxCenter());
		this.setBottom(HBoxBottom());
		
		this.getStyleClass().add("background");
		
	}

	
	public HBox HBoxBottom() {
		hboxLeft = new HBox();
		hboxLeft.getChildren().addAll(platteLeft, volumeViewLeft);
		
		hboxLeft.setSpacing(50);
		
		hboxRight = new HBox();
		hboxRight.getChildren().addAll(volumeViewRight, platteRight);
		hboxRight.setSpacing(50);
		
		hboxBottom = new HBox();
		hboxBottom.getChildren().addAll(hboxLeft,samplesView,hboxRight);
		hboxBottom.setAlignment(Pos.CENTER);
		hboxBottom.setSpacing(100);
		return hboxBottom;
	}
	
	public HBox HBoxTop() {
		boxWavesLeft = new VBox();
		boxWavesRight = new VBox();
		
		boxWavesLeft.getChildren().addAll(wavesViewLeft,titleViewLeft);
		boxWavesRight.getChildren().addAll(wavesViewRight,titleViewRight);
		
		hboxLeftTop = new HBox();
		hboxLeftTop.getChildren().addAll(boxWavesLeft,tuneViewLeft);
		hboxLeftTop.setSpacing(25);
		hboxLeftTop.setPadding(new Insets(0,0,0,10));
		
		hboxRightTop = new HBox();
		hboxRightTop.getChildren().addAll(tuneViewRight,boxWavesRight);
		hboxRightTop.setSpacing(25);
		hboxRightTop.setPadding(new Insets(0,10,0,0));
		
		hboxTop = new HBox();
		hboxTop.getChildren().addAll(hboxLeftTop,pPlaylistView2,hboxRightTop);
		hboxTop.setSpacing(25);
		hboxTop.setPadding(new Insets(10,10,10,10));
		hboxTop.setAlignment(Pos.CENTER);
		return hboxTop;
	}

	public HBox HBoxCenter() {
		hboxCenter = new HBox();
		hboxCenter.getChildren().addAll(controlViewLeft,setting,controlViewRight);
		hboxCenter.setAlignment(Pos.CENTER);
		hboxCenter.setSpacing(200);
		hboxCenter.setPadding(new Insets(10,10,20,10));
		return hboxCenter;
	}
}
