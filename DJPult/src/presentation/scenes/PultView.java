package presentation.scenes;


import java.io.File;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.UIComponents.ControlView;
import presentation.UIComponents.SamplesView;
import presentation.UIComponents.PultPlaylistView;
import presentation.UIComponents.TitleView;
import presentation.UIComponents.TuneView;
import presentation.UIComponents.Visualizer;
import presentation.UIComponents.VolumeView;

public class PultView extends BorderPane {
	
	

	SamplesView samplesView;
	TitleView titleViewLeft, titleViewRight;
	ControlView controlViewLeft,controlViewRight;
	TuneView tuneViewLeft, tuneViewRight;
	VolumeView volumeViewLeft, volumeViewRight;
	PultPlaylistView pPlaylistView;
	
	Visualizer visualizerLeft,visualizerRight;
	
	public Button setting;
	
	public Image imgLeft, imgRight;
	public ImageView imgViewLeft, imgViewRight;
	
	public HBox hboxTop,hboxLeftTop,hboxRightTop,hboxCenter,hboxBottom,hboxLeft,hboxRight;
	public VBox boxWavesLeft, boxWavesRight;

	public PultView() throws FileNotFoundException {
		
		
		
		
		samplesView = new SamplesView();
		titleViewLeft = new TitleView();
		titleViewRight = new TitleView();
		controlViewLeft = new ControlView();
		controlViewRight = new ControlView();
		tuneViewLeft = new TuneView();
		tuneViewRight = new TuneView();
		volumeViewLeft = new VolumeView();
		volumeViewRight = new VolumeView();
		pPlaylistView = new PultPlaylistView();
		visualizerLeft = new Visualizer();
		visualizerRight = new Visualizer();
		
		setting = new Button();
		setting.setId("setting");
		
		File imgfile = new File("image/platte.png");
		String pfad = imgfile.getPath();
		
		imgLeft = new Image(pfad);
		imgViewLeft = new ImageView(imgLeft);
		imgViewLeft.setFitHeight(300);
		imgViewLeft.setFitWidth(300);
		
		imgRight = new Image(pfad);
		imgViewRight = new ImageView(imgRight);	
		imgViewRight.setFitHeight(300);
		imgViewRight.setFitWidth(300);
	
		
		this.setTop(HBoxTop());
		this.setCenter(HBoxCenter());
		this.setBottom(HBoxBottom());
		
		this.getStyleClass().add("background");
		
	}

	
	public HBox HBoxBottom() {
		hboxLeft = new HBox();
		hboxLeft.getChildren().addAll(imgViewLeft, volumeViewLeft);
		
		hboxLeft.setSpacing(50);
		
		hboxRight = new HBox();
		hboxRight.getChildren().addAll(volumeViewRight, imgViewRight);
		hboxRight.setSpacing(50);
		
		hboxBottom = new HBox();
		hboxBottom.getChildren().addAll(hboxLeft,samplesView,hboxRight);
		hboxBottom.setAlignment(Pos.CENTER);
		hboxBottom.setSpacing(100);
		hboxBottom.setPadding(new Insets(0,0,15,0));
		return hboxBottom;
	}
	
	public HBox HBoxTop() {
		boxWavesLeft = new VBox();
		boxWavesRight = new VBox();
		
		boxWavesLeft.getChildren().addAll(visualizerLeft,titleViewLeft);
		boxWavesRight.getChildren().addAll(visualizerRight,titleViewRight);
		
		hboxLeftTop = new HBox();
		hboxLeftTop.getChildren().addAll(boxWavesLeft,tuneViewLeft);
		hboxLeftTop.setSpacing(25);
		hboxLeftTop.setPadding(new Insets(0,0,0,10));
		
		hboxRightTop = new HBox();
		hboxRightTop.getChildren().addAll(tuneViewRight,boxWavesRight);
		hboxRightTop.setSpacing(25);
		hboxRightTop.setPadding(new Insets(0,10,0,0));
		
		hboxTop = new HBox();
		hboxTop.getChildren().addAll(hboxLeftTop,pPlaylistView,hboxRightTop);
		hboxTop.setSpacing(25);
		hboxTop.setPadding(new Insets(10,10,0,10));
		hboxTop.setAlignment(Pos.CENTER);
		return hboxTop;
	}

	public HBox HBoxCenter() {
		hboxCenter = new HBox();
		hboxCenter.getChildren().addAll(controlViewLeft,setting,controlViewRight);
		hboxCenter.setAlignment(Pos.CENTER);
		hboxCenter.setSpacing(200);
		hboxCenter.setPadding(new Insets(5,10,5,10));
		return hboxCenter;
	}
}
