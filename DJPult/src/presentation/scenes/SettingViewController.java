package presentation.scenes;

import application.Main;
import business.MischPult;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import presentation.UIComponents.HeaderView;

public class SettingViewController extends ViewController<Main> {
	
	
	private Button back;
	private Button folder;
	
	private MischPult mischPult;
	private Stage stage;
	
	public SettingViewController(Main application,MischPult mischPult, Stage primaryStage ) {
		super(application);
		
		this.mischPult= mischPult;
		
		rootView = new SettingView();
		
		SettingView view = (SettingView) rootView;
		this.stage = primaryStage;
		
		HeaderView headerView = view.headerView;
		back = headerView.back;
		folder = headerView.folder;
		
		initialize();
	}
	@Override
	public void initialize() {
		back.setOnAction(new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(application!= null) {
					application.switchScene(Scenes.PULT_VIEW);
					
				}
				
			}
			
		});
		folder.setOnAction(new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(application!= null) {
					application.switchScene(Scenes.PLAYLIST_VIEW);
					
				}
				
			}
			
		});
	}

}
