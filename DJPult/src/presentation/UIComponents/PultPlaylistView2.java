package presentation.UIComponents;

import java.util.LinkedList;
import java.util.List;

import business.SelectTrack;
import business.Track;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class PultPlaylistView2 extends HBox {
	//List<Track> trackList;
	//List<SelectTrack> selectTrackList;
	ListView<SelectTrack> trackListViewLeft,trackListViewRight;
	ObservableList<SelectTrack> trackPlaylistLeft, trackPlaylistRight;

	public PultPlaylistView2() {
		//trackList = new LinkedList<Track>();
		//selectTrackList = new LinkedList<SelectTrack>();
		trackListViewLeft = new ListView<SelectTrack>();
		trackListViewLeft.setId("playlist");
		trackListViewRight = new ListView<SelectTrack>();
		trackListViewRight.setId("playlist");
		
		/*for (Track currentTrack : trackList) {
			SelectTrack uiTrack = new SelectTrack();
			uiTrack.trackModel = currentTrack;
			uiTrack.selected = false;
			selectTrackList.add(uiTrack);
		}*/

		trackListViewLeft.setCellFactory(new Callback<ListView<SelectTrack>, ListCell<SelectTrack>>() {
			@Override
			public ListCell<SelectTrack> call(ListView<SelectTrack> param) {
				// TODO Auto-generated method stub
				return new TrackListCell();
			}
		});
		
		trackListViewRight.setCellFactory(new Callback<ListView<SelectTrack>,ListCell<SelectTrack>>(){
			@Override
			public ListCell<SelectTrack> call(ListView<SelectTrack> param) {
				// TODO Auto-generated method stub
				return new TrackListCell();
			}
		});
		

		trackPlaylistLeft = FXCollections.observableArrayList();
		trackPlaylistRight = FXCollections.observableArrayList();
		//trackPlaylist.setAll(selectTrackList);
		trackListViewLeft.setItems(trackPlaylistLeft);
		trackListViewRight.setItems(trackPlaylistRight);

		this.getChildren().addAll(trackListViewLeft,trackListViewRight);
		this.setPadding(new Insets(10));
		this.setSpacing(5);
	}
}
