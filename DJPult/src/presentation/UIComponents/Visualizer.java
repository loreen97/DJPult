package presentation.UIComponents;

import static java.lang.Integer.min;

import java.nio.file.Paths;

import business.MischPult;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Visualizer extends Pane {

	public Pane pane;
	Ellipse[] ellipses;

	int numBands;
	double bandHeight, bandWidth, halfBandHeight;


	
	public Visualizer() {
		numBands = 30;

		ellipses = new Ellipse[numBands];
		pane = new Pane();
		bandHeight = 150;
		bandWidth = 5.0;
		halfBandHeight = bandHeight/2;
		pane.setPrefHeight(10);
		pane.setPrefWidth(5);
		pane.setLayoutX(50);
		pane.setLayoutY(-80);
		pane.setPadding(new Insets(0,0,200,0));
		
		this.getChildren().add(pane);
	}

	public void start(Integer numBands, Pane pane) {

		this.numBands = numBands;
		this.pane = pane;

		for (int i = 0; i < numBands; i++) {
			Ellipse ellipse = new Ellipse();
			ellipse.setCenterX(bandWidth / 2 + bandWidth * i);
			ellipse.setCenterY(198.5);
			ellipse.setRadiusX(bandWidth / 2);
			ellipse.setRadiusY(10.0);
			ellipse.setFill(Color.hsb(190, 1.0, 1.0, 1.0));
			ellipse.setOpacity(0.6);
			pane.getChildren().addAll(ellipse);//geht hier nicht mehr weiter
			ellipses[i] = ellipse;
			
		}

	}


	public void wavesUpdate(double timestamp, double duration, float[] magnitudes, float[] phases, MediaPlayer m) {
			Duration ct = m.getCurrentTime();
			double ms = ct.toMillis();
			//update(timestamp, duration, magnitudes, phases);
			if (ellipses == null) {
				return;
			}
			Integer num = min(ellipses.length, magnitudes.length);

			for (int i = 0; i < num; i++) {
				ellipses[i].setRadiusY(((60.0 + magnitudes[i]) / 60.0) * halfBandHeight + 10);
				ellipses[i].setFill(Color.hsb(190 - (magnitudes[i] * -6.0), 1.0, 1.0, 1.0));
				ellipses[i].setOpacity(0.8);
			}
	}
}