package presentation.UIComponents;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class TitleView extends VBox{

	public Label title;
	public Slider songSlider;
	
	
	public TitleView() {
		songSlider = new Slider();
		songSlider.setMin(0);
		songSlider.setMax(100);
		songSlider.setValue(0);
		songSlider.setShowTickMarks(false);
		songSlider.setShowTickLabels(false);
		
		songSlider.setId("slider");
		songSlider.setPadding(new Insets(0,5,0,5));
		
		title = new Label();
		title.setId("title");
		//irgendwas rein einfach
		//again
		
		this.getChildren().addAll(title,songSlider);
		this.setAlignment(Pos.BOTTOM_CENTER);
		this.setPadding(new Insets(0,0,10,0));
	}
	
}
