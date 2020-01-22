package presentation.UIComponents;

import business.SelectTrack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class SamplePlaylistView2 extends VBox{
	
	public ListView<SelectTrack> sampleListView;
	public ObservableList<SelectTrack> sampleList;
	public Label sampleLabel;

	public SamplePlaylistView2() {
		sampleListView = new ListView<SelectTrack>();
		sampleListView.setId("settingPlaylist");
		
		sampleListView.setCellFactory(new Callback<ListView<SelectTrack>, ListCell<SelectTrack>>() {
			@Override
			public ListCell<SelectTrack> call(ListView<SelectTrack> param) {
				return new TrackListCell();
			}
		});
		
		sampleList = FXCollections.observableArrayList();
		sampleListView.setItems(sampleList);	
		
		
		
		sampleLabel = new Label("Samples");
		sampleLabel.setId("labels");
		
		
		this.getChildren().addAll(sampleLabel,sampleListView);
		this.setPadding(new Insets(0,10,30,10));
		
	}
}
