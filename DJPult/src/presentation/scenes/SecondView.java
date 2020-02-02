package presentation.scenes;

import business.MischPult;
import business.Samples;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.UIComponents.HeaderView;
import presentation.UIComponents.SamplePlaylistView;
import presentation.UIComponents.SamplesView;
import presentation.UIComponents.SettingPlaylistLeftView;
import presentation.UIComponents.SettingPlaylistRightView;

public class SecondView extends BorderPane{
	
	/**
	 * Die Übersicht der Settings
	 * Also wo man Playlists auswählen kann
	 * die bereits erstellt wurden
	 * 2. Seite
	 */

	SamplesView samplesView;
	SamplePlaylistView sPlaylistView;
	SettingPlaylistLeftView sPLView; //Cell zeug weil 2 hintendran
	SettingPlaylistRightView sPRView; //same
	HeaderView headerView;
	
	public Button save;
	public VBox vbox;
	public Button loadMP3;
	
	public HBox bottom,center;
	
	
	public SecondView() {
		MischPult mischPult = new MischPult();
		samplesView = new SamplesView();
		sPlaylistView = new SamplePlaylistView();
		
		save= new Button();
		save.setId("save");
	
		loadMP3 = new Button();
		loadMP3.setId("loadFile");
		
		vbox= new VBox();
		vbox.getChildren().addAll(loadMP3, save);
		vbox.setSpacing(20);
		vbox.setPadding(new Insets(0,0,30,0));
		vbox.setAlignment(Pos.BOTTOM_RIGHT);
		
		sPLView = new SettingPlaylistLeftView(mischPult);
		sPRView = new SettingPlaylistRightView(mischPult);
		
		bottom= new HBox();
		bottom.getChildren().addAll(samplesView, sPlaylistView, vbox);
		bottom.setPadding(new Insets(0,0,10,0));
		bottom.setAlignment(Pos.CENTER);
		bottom.setSpacing(30);
		
		center = new HBox();
		center.getChildren().addAll(sPLView,sPRView);
		center.setAlignment(Pos.CENTER);
		center.setPadding(new Insets(10));
		center.setSpacing(70);
		
		headerView = new HeaderView();
		
		this.setTop(headerView);
		this.setCenter(center);
		this.setBottom(bottom);
		
		this.getStyleClass().add("background");
	}
}
