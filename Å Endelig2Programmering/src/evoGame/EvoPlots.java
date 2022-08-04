package evoGame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class EvoPlots {
	public XYSeries avgSpeed = new XYSeries("avgSpeedOverTime");
	public XYSeries avgSense = new XYSeries("avgSenseOverTime");  
	public XYSeries speedForCurrentGen = new XYSeries("speedForCurrentGen");  
	public XYSeries senseForCurrentGen = new XYSeries("senseForCurrentGen");  
	public XYSeries populationOverTime = new XYSeries("populationOverTime");  
	
	
	
	public static void showPlot(String title, XYSeries graph, String x, String y) {
		XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(graph);

        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                title, // Chart title
                x, // X-Axis Label
                y, // Y-Axis Label
                dataset // Dataset for the Chart
                );
        
        
        ChartFrame cf = new ChartFrame("asd", scatterPlot);
        
        cf.setVisible(true);
        cf.setSize(500, 300);
	}
	
	

}
