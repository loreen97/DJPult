package presentation.UIComponents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SamplePlaylistView extends HBox{
	
	public ListView<String> sampleListView;
	public ObservableList<String> sampleList;
	public Button loadFile;
	public Label sampleLabel;
	public VBox vbox, boxLoad;

	public SamplePlaylistView() {
		sampleListView = new ListView<>(sampleList);
		sampleListView.setId("settingPlaylist");
		
		sampleList = FXCollections.observableArrayList("Sample 1", "Sample 2","Sample 3");
		sampleListView.setItems(sampleList);
		
		
		loadFile = new Button();
		loadFile.setId("loadFile");
		
		
		boxLoad = new VBox();
		boxLoad.getChildren().addAll(loadFile);
		boxLoad.setPadding(new Insets(10,0,0,10));
		
		
		sampleLabel = new Label("Samples");
		sampleLabel.setId("labels");
		
		vbox = new VBox();
		vbox.getChildren().addAll(sampleLabel,sampleListView);
		
		this.getChildren().addAll(vbox, boxLoad);
		this.setPadding(new Insets(5,10,10,10));
		this.setSpacing(5);
		
	}
}
