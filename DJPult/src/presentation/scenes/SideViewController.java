package presentation.scenes;

import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import application.Main;
import business.MischPult;
import business.Track;
import javafx.animation.RotateTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import presentation.UIComponents.PultPlaylistView;
import presentation.UIComponents.TuneView;
import presentation.UIComponents.Visualizer;
import presentation.UIComponents.VolumeView;

public class SideViewController extends ViewController<Main> implements Observer{
	private Button play;
	private Button einEinHalb, normal, doppelt, loop, halb;
	
	private Stage stage;
	private MischPult mischPult;
	
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
	
	//private Pane pane;
	public SideViewController (Main application, MischPult mischPult, Stage primaryStage) throws FileNotFoundException {
		super(application);
/*
		this.mischPult = mischPult;

		rootView = new PultView();
		PultView view = (PultView) rootView;
		this.stage = primaryStage;
		imgView= view.imgView;
		
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
		
		*/
	}
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
