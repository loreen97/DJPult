package presentation.UIComponents;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControlView extends HBox {

	public Button play;
	public Button loop;
	public Button viertel;
	public Button halb;
	public Button normal;
	public Button doppelt;
	public HBox hbox1;
	public HBox hbox2;
	
	public ControlView() {
		play = new Button();
		play.getStyleClass().addAll("control-button","play");
		
		loop = new Button();
		loop.getStyleClass().addAll("control-button","loop");
		
		viertel = new Button("1/4");
		viertel.setId("speedS");
		
		halb = new Button("1/2");
		halb.setId("speedS");
		
		normal = new Button("1");
		normal.setId("speedF");
		
		doppelt = new Button("2");
		doppelt.setId("speedF");
		
		
		
		hbox1 = new HBox();
		hbox1.getChildren().addAll(play, loop);
		hbox1.setSpacing(10);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setPadding(new Insets(0,30,0,0));
		hbox2 = new HBox();
		hbox2.getChildren().addAll(viertel, halb, normal, doppelt);
		hbox2.setSpacing(10);
		hbox2.setAlignment(Pos.CENTER);
		
		this.getChildren().addAll(hbox1, hbox2);
		this.setSpacing(10);
		this.setPadding(new Insets(10,0,10,0));
		this.setAlignment(Pos.CENTER);
	}
}
