package presentation.UIComponents;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SamplesView extends HBox {

	public Button sample1;
	public Button sample2;
	public Button sample3;
	public Button sample4;
	public Button sample5;
	public Button sample6;
	
	public VBox vbox1;
	public VBox vbox2;
	
	public SamplesView() {
		sample1 = new Button();
		sample1.getStyleClass().addAll("samples","sample1");
		
		sample2 = new Button();
		sample2.getStyleClass().addAll("samples","sample2");
		
		sample3 = new Button();
		sample3.getStyleClass().addAll("samples","sample3");
		
		sample4 = new Button();
		sample4.getStyleClass().addAll("samples","sample4");
		
		sample5 = new Button();
		sample5.getStyleClass().addAll("samples","sample5");
		
		sample6 = new Button();
		sample6.getStyleClass().addAll("samples","sample6");
		
		vbox1 = new VBox();
		vbox2 = new VBox();
		
		vbox1.getChildren().addAll(sample1,sample2,sample3);
		vbox1.setSpacing(20);
		vbox2.getChildren().addAll(sample4,sample5,sample6);
		vbox2.setSpacing(20);
		
		this.getChildren().addAll(vbox1,vbox2);
		this.setSpacing(30);
		//this.setPadding(new Insets(30,15,15,15));
		this.setAlignment(Pos.CENTER);
		
	}
	
	
}
