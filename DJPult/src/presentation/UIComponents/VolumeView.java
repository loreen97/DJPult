package presentation.UIComponents;

import javafx.geometry.Orientation;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class VolumeView extends HBox{

	public Slider volume;
	
	public VolumeView() {
		volume = new Slider();
		volume.setOrientation(Orientation.VERTICAL);
		volume.setId("volume");
		

		volume.setMin(-10);
		volume.setMax(100);
		volume.setValue(25);
		
		volume.setShowTickMarks(true);
		volume.setShowTickLabels(false);

		volume.setMaxSize(10, 320);
		
		this.getChildren().addAll(volume);
	}
	
	
}
