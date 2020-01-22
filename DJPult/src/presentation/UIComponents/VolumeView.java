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
		

		volume.setMin(-40);
		volume.setMax(50);
		volume.setValue(25);

		volume.setShowTickMarks(true);
		volume.setShowTickLabels(false);

		volume.setMaxSize(10, 320);
		
		this.getChildren().addAll(volume);
	}
	
	
}
