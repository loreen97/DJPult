package application;
	
import java.util.HashMap;

import business.MischPult;
import javafx.application.Application;
import javafx.stage.Stage;
import presentation.scenes.PlaylistViewController;
import presentation.scenes.PultViewController;
import presentation.scenes.Scenes;
import presentation.scenes.SettingViewController;
import presentation.scenes.ViewController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main extends Application {
	
	private Scene scene;
	private Pane currentScene;
	private HashMap<Scenes, Pane> scenes;
	
	private MischPult mischPult;
	
	public void init() {
		mischPult = new MischPult();
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//Aus Praktikum
			//MediaPlayer player = MischPult.getMediaPlayer(new Media(""));
			//player = MischPult.getMediaPlayer();

			ViewController<Main> controller;
			scenes = new HashMap<>();
			
			controller = new PultViewController(this, mischPult,primaryStage);
			scenes.put(Scenes.PULT_VIEW, controller.getRootView());
			
			controller = new SettingViewController(this,mischPult, primaryStage);
			scenes.put(Scenes.SETTING_VIEW, controller.getRootView());
			
			controller = new PlaylistViewController(this, mischPult,primaryStage);
			scenes.put(Scenes.PLAYLIST_VIEW, controller.getRootView());
			
			
			
			Pane root = scenes.get(Scenes.PULT_VIEW);
			scene = new Scene(root,1500,700);		//1500,700  //800,600
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void switchScene(Scenes sceneName) {
		Pane nextScene;
		if(scenes.containsKey(sceneName)) {
			nextScene= scenes.get(sceneName);
			scene.setRoot(nextScene);
			currentScene = nextScene;
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
