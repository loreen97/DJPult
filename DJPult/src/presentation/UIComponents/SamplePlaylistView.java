package presentation.UIComponents;

import business.Track;
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

public class SamplePlaylistView extends VBox{
	
	public ListView<Track> sampleListView;
	public ObservableList<Track> sampleList;
	public Label sampleLabel;

	public SamplePlaylistView() {
		sampleListView = new ListView<Track>();
		sampleListView.setId("settingPlaylist");
		
		sampleListView.setCellFactory(new Callback<ListView<Track>, ListCell<Track>>() {
			@Override
			public ListCell<Track> call(ListView<Track> param) {
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