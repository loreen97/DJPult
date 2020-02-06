package presentation.UIComponents;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TuneView extends HBox {

	public Slider tune1;
	public Slider tune2;
	public Slider tune3;
	
	public Label name1;
	public Label name2;
	public Label name3;

	public VBox left;
	public VBox middle;
	public VBox right;
	
	
	
	public TuneView() {
		tune1 = new Slider();
		tune1.setOrientation(Orientation.VERTICAL);
		tune1.setShowTickMarks(true);
		tune1.setShowTickLabels(false);
		tune1.getStyleClass().add("tune");
		System.out.println("max: " + tune1.getMax());
		tune1.setMin(-24);
		tune1.setMax(12);
		
		tune2 = new Slider();
		tune2.setOrientation(Orientation.VERTICAL);
		tune2.setShowTickMarks(true);
		tune2.setShowTickLabels(false);
		tune2.getStyleClass().add("tune");
		tune2.setMin(-24);
		tune2.setMax(12);
		
		tune3 = new Slider();
		tune3.setOrientation(Orientation.VERTICAL);
		tune3.setShowTickMarks(true);
		tune3.setShowTickLabels(false);
		tune3.getStyleClass().add("tune");
		
		name1 = new Label("Instrument");
		name1.setId("tuneName");
		name2 = new Label("Bass");
		name2.setId("tuneName");
		name3 = new Label("Gesang");
		name3.setId("tuneName");
		
		left = new VBox();
		middle = new VBox();
		right = new VBox();
		
		left.getChildren().addAll(tune1,name1);
		left.setAlignment(Pos.CENTER);
		left.setSpacing(5);
		middle.getChildren().addAll(tune2,name2);
		middle.setAlignment(Pos.CENTER);
		middle.setSpacing(5);
		right.getChildren().addAll(tune3,name3);
		right.setAlignment(Pos.CENTER);
		right.setSpacing(5);
		
		this.getChildren().addAll(left,middle,right);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10));
	}
}
