package presentation.scenes;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.UIComponents.ControlView;
import presentation.UIComponents.PultPlaylistView;
import presentation.UIComponents.SamplesView;
import presentation.UIComponents.TitleView;
import presentation.UIComponents.TuneView;
import presentation.UIComponents.Visualizer;
import presentation.UIComponents.VolumeView;

public class SideView extends BorderPane {
	TuneView tuneView;
	boolean reversed;
	
	TitleView titleView;
	ControlView controlView;
	TuneView tuneViewL;
	VolumeView volumeView;
	PultPlaylistView pPlaylistView;
	Visualizer visualizer;
	
	public Image img;
	public ImageView imgView;
	
	public HBox hboxTop,hboxLeftTop,hboxRightTop,hboxCenter,hboxBottom,hboxLeft,hboxRight; //?
	public VBox boxWaves;
	
	public SideView(boolean reversed) {
		super();
		titleView = new TitleView();
		controlView = new ControlView();
		tuneView= new TuneView();
		volumeView = new VolumeView();
		pPlaylistView = new PultPlaylistView();
		visualizer = new Visualizer();
		
		//Samples hier rein?
		this.reversed = reversed;
		if (reversed) {
			//Die Platte, Volume, Slider, Waves, TitleView
			// ...
			//Hier alles was gespiegelt ist
		}
		//Hier alles was nicht gespiegelt ist
		//controlView = ;
		
		File imgfile = new File("image/platte.png");
		String pfad = imgfile.getPath();
		img = new Image(pfad);
		imgView= new ImageView(img);
		imgView.setFitHeight(300);
		imgView.setFitWidth(300);
		
		this.setTop(HBoxTop());
		this.setCenter(HBoxCenter());
		this.setBottom(HBoxBottom());
		
		this.getStyleClass().add("background");
		
	}
	
	private HBox HBoxTop() {
		return hboxTop;
	}
	private HBox HBoxCenter() {
		return hboxCenter;
	}
	private HBox HBoxBottom() {
		return hboxBottom;
}
	
}
