package presentation.scenes;

import business.Playlist;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

public class PlaylistListCell extends ListCell<Playlist>{
	private Label title;
	private VBox vbox ;
	
	public PlaylistListCell() {
		vbox = new VBox();
		title = new Label();
		
		vbox.getChildren().addAll(title);
		
	}
	
	protected void updateItem(Playlist item, boolean empty) {
		super.updateItem(item, empty);
		
		if(!empty) {
			title.setText(item.getTitle());
			
			this.setGraphic(vbox);
		}else {
			this.setGraphic(null);
		}
			
		
	}
}
