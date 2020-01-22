package presentation.UIComponents;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;

public class WavesView extends HBox {

	public NumberAxis xAxis;
	public NumberAxis yAxis;
	public AreaChart<Number, Number> areaChart;

	public WavesView() {

		xAxis = new NumberAxis(1, 12, 1);
		yAxis = new NumberAxis();
		areaChart = new AreaChart<Number, Number>(xAxis, yAxis);

		XYChart.Series<Number, Number> songWave = new XYChart.Series<Number, Number>();

		songWave.getData().add(new XYChart.Data<Number, Number>(1, 0));
		songWave.getData().add(new XYChart.Data<Number, Number>(3, 800));
		songWave.getData().add(new XYChart.Data<Number, Number>(4, 1500));
		songWave.getData().add(new XYChart.Data<Number, Number>(5, 900));
		songWave.getData().add(new XYChart.Data<Number, Number>(7, 500));
		songWave.getData().add(new XYChart.Data<Number, Number>(8, 100));
		songWave.getData().add(new XYChart.Data<Number, Number>(10, 1500));
		songWave.getData().add(new XYChart.Data<Number, Number>(12, 0));

		areaChart.getData().addAll(songWave);
		areaChart.setId("areaChart");

		areaChart.setCreateSymbols(false);
		areaChart.setHorizontalGridLinesVisible(false);
		areaChart.setVerticalGridLinesVisible(false);
		areaChart.setLegendVisible(false);
		areaChart.setAlternativeRowFillVisible(false);
		areaChart.setHorizontalZeroLineVisible(false);
		areaChart.setVerticalZeroLineVisible(false);
		
		
		xAxis.setTickLabelsVisible(false);
		xAxis.setTickMarkVisible(false);
		yAxis.setTickLabelsVisible(false);
		yAxis.setTickMarkVisible(false);
		xAxis.setVisible(false);
		yAxis.setVisible(false);
		xAxis.setMinorTickVisible(false);
		yAxis.setMinorTickVisible(false);
		
		
		this.getChildren().addAll(areaChart);

	}

}
