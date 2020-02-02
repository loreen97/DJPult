package presentation.UIComponents;

import business.SelectTrack;
import business.Track;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

public class TrackListCell extends ListCell<Track>{

	private Label title;
	private Label interpret;
	private VBox vbox ;
	
	public TrackListCell() {
		vbox = new VBox();
		title = new Label();
		title.setId("playlist-title");
		interpret = new Label();
		interpret.setId("playlist-interpret");
		vbox.getChildren().addAll(title,interpret);
		
	}
	
	protected void updateItem(Track item, boolean empty) {
		super.updateItem(item, empty);
		
		if(!empty) {
			title.setText(item.getTitle());
			interpret.setText(item.getInterpret());
			
			this.setGraphic(vbox);
		}else {
			this.setGraphic(null);
		}
			
		
	}
}
