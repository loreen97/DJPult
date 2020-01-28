package presentation.scenes;

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
import presentation.UIComponents.SamplePlaylistView2;
import presentation.UIComponents.SamplesView;
import presentation.UIComponents.SettingPlaylistLeftView;
import presentation.UIComponents.SettingPlaylistLeftView2;
import presentation.UIComponents.SettingPlaylistRightView;
import presentation.UIComponents.SettingPlaylistRightView2;

public class SettingView extends BorderPane{
	
	/**
	 * Die Übersicht der Settings
	 * Also wo man Playlists auswählen kann
	 * die bereits erstellt wurden
	 * 2. Seite
	 */

	SamplesView samplesView;
	SamplePlaylistView2 sPlaylistView2;
	SettingPlaylistLeftView2 sPLView2; //Cell zeug weil 2 hintendran
	SettingPlaylistRightView2 sPRView2; //same
	HeaderView headerView;
	
	public Button save;
	public ChoiceBox cBox;
	public VBox vbox;
	
	
	public HBox bottom,center;
	
	public SettingView() {
		samplesView = new SamplesView();
		sPlaylistView2 = new SamplePlaylistView2();
		
		save= new Button();
		save.setId("save");
		cBox = new ChoiceBox(FXCollections.observableArrayList("Sample 1","Sample 2","Sample 3","Sample 4","Sample 5","Sample 6"));
		cBox.setId("choiceBox");
		vbox= new VBox();
		vbox.getChildren().addAll(cBox, save);
		vbox.setSpacing(20);
		vbox.setPadding(new Insets(0,0,30,0));
		vbox.setAlignment(Pos.BOTTOM_RIGHT);
		
		sPLView2 = new SettingPlaylistLeftView2();
		sPRView2 = new SettingPlaylistRightView2();
		
		bottom= new HBox();
		bottom.getChildren().addAll(samplesView, sPlaylistView2, vbox);
		bottom.setPadding(new Insets(0,0,10,0));
		bottom.setAlignment(Pos.CENTER);
		bottom.setSpacing(30);
		
		center = new HBox();
		center.getChildren().addAll(sPLView2,sPRView2);
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
