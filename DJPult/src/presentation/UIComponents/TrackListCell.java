package presentation.UIComponents;

import business.SelectTrack;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

public class TrackListCell extends ListCell<SelectTrack>{

	private Label title;
	private Label interpret;
	private VBox vbox ;
	
	public TrackListCell() {
		vbox = new VBox();
		title = new Label();
		interpret = new Label();
		
		vbox.getChildren().addAll(title,interpret);
		
	}
	
	protected void updateItem(SelectTrack item, boolean empty) {
		super.updateItem(item, empty);
		
		if(!empty) {
			title.setText(item.trackModel.getTitle());
			interpret.setText(item.trackModel.getInterpret());
			
			this.setGraphic(vbox);
		}else {
			this.setGraphic(null);
		}
			
		
	}
}
