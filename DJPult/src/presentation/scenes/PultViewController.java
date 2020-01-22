package presentation.scenes;

import application.Main;
import business.MischPult;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentation.UIComponents.ControlView;
import presentation.UIComponents.TuneView;

public class PultViewController extends ViewController<Main>{

	private Button sample1;
	private Button sample2;
	private Button sample3;
	private Button sample4;
	private Button sample5;
	private Button sample6;
	private Button setting;
	private Button play;
	private Button loop;
	private Stage stage;
	private MischPult mischPult;
	
	private Slider tune2;
	
	private boolean playing,pushed;
	//private boolean userInteraction;
	
	public PultViewController(Main application,MischPult mischPult, Stage primaryStage ) {
		super(application);
		
		this.mischPult = mischPult;
		
		rootView = new PultView();
		
		PultView view = (PultView) rootView;
		this.stage = primaryStage;

		setting = view.setting;
		
		ControlView controlViewLeft = view.controlViewLeft;
		play = controlViewLeft.play;
		loop = controlViewLeft.loop;
		
		/*ControlView controlViewRight = view.controlViewRight;
		play = controlViewRight.play;*/
		
		TuneView tuneViewLeft = view.tuneViewLeft;
		tune2 = tuneViewLeft.tune2;
		
		
		playing = false;
		pushed = false;
		
		initialize();
	}
	

	@Override
	public void initialize() {
		
		setting.setOnAction(new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(application!= null) {
					application.switchScene(Scenes.SETTING_VIEW);
					
				}
				
			}
			
		});
		
		play.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(!playing) {
					mischPult.play();
					play.getStyleClass().clear();
					play.getStyleClass().addAll("control-button","pause");
					playing = true;
				}else {
					mischPult.play();
					play.getStyleClass().clear();
					play.getStyleClass().addAll("control-button","play");
					playing = false;
				}
				
			}

		});
		
		
		loop.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(!pushed) {
					//mischPult.loop();
					loop.getStyleClass().clear();
					loop.getStyleClass().addAll("control-button","pushLoop");
					pushed=true;	
				}else {
					
					loop.getStyleClass().clear();
					loop.getStyleClass().addAll("control-button","loop");
					pushed = false;
				}
				
			}

		});
		
		
		//das ist jetzt kopiert aus dem mp3 player. das war für den songslider. 
		//ich kann dir hier nichts genaues vorgeben, weil das angepasst werden muss...
		
		/*tune2.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (userInteraction)
				mischPult.changeBySlider(tune2.getValue());
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
		});*/
	}

}
