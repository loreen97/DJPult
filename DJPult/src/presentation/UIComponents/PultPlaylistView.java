package presentation.UIComponents;

import java.util.LinkedList;
import java.util.List;

import business.Track;
import business.Track;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class PultPlaylistView extends HBox {
	//List<Track> trackList;
	//List<Track> selectTrackList;
	public ListView<Track> trackListViewLeft;
	public ListView<Track> trackListViewRight;
	public ObservableList<Track> trackPlaylistLeft, trackPlaylistRight;

	public PultPlaylistView() {
		//trackList = new LinkedList<Track>();
		//selectTrackList = new LinkedList<Track>();
		trackListViewLeft = new ListView<Track>();
		trackListViewLeft.setId("playlist");
		trackListViewRight = new ListView<Track>();
		trackListViewRight.setId("playlist");
		
		/*for (Track currentTrack : trackList) {
			Track uiTrack = new Track();
			uiTrack.trackModel = currentTrack;
			uiTrack.selected = false;
			selectTrackList.add(uiTrack);
		}*/

		trackListViewLeft.setCellFactory(new Callback<ListView<Track>, ListCell<Track>>() {
			@Override
			public ListCell<Track> call(ListView<Track> param) {
				// TODO Auto-generated method stub
				return new TrackListCell();
			}
		});
		
		trackListViewRight.setCellFactory(new Callback<ListView<Track>,ListCell<Track>>(){
			@Override
			public ListCell<Track> call(ListView<Track> param) {
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