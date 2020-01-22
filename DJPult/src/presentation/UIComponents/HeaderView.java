package presentation.UIComponents;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class HeaderView extends HBox{

	public Button back;
	public Button folder;
	
	public HeaderView() {
		

		back = new Button();
		back.setId("back");
		
		folder = new Button();
		folder.setId("folder");	
		
		this.getChildren().addAll(back,folder);
		this.setSpacing(1270);
		this.setPadding(new Insets(10,10,0,10));
	}
}
