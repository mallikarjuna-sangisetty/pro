package com.project.traffic.controller;

import java.applet.Applet;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
 
public class ChartsController  extends Applet{
    
    public static void generatePieChart(DefaultPieDataset dataSet,String message) throws IOException {
        JFreeChart chart = ChartFactory.createPieChart(
                message, dataSet, true, true, false);
 
        renderchartImage(chart);
    }
 
    public static void generateBarChart(DefaultCategoryDataset dataSet,String message,String xAxis,String yAxis) throws IOException {
        
 
        JFreeChart chart = ChartFactory.createBarChart(
        		message, xAxis, yAxis,
                dataSet, PlotOrientation.VERTICAL, false, true, false);
 
        renderchartImage(chart);
    }
    

    public static void generateLineChart(String title,String categoryAxisLabel,String valueAxisLabel,DefaultCategoryDataset  dataset) throws IOException {
        
 
        JFreeChart chart = ChartFactory.createLineChart(title, categoryAxisLabel, valueAxisLabel, dataset, PlotOrientation.VERTICAL, true,true,false);
 
        renderchartImage(chart);
    }
   
    public static void renderchartImage(JFreeChart chart) throws IOException{
    	int width = 640;
        int height = 480; 
        File file = new File( "C:\\temp\\temp"+Calendar.getInstance().getTimeInMillis()+".jpeg" ); 
        file.createNewFile();
        ChartUtilities.saveChartAsJPEG( file , chart , width , height );
        
        try {
            BufferedImage img = ImageIO.read(file);
            ImageIcon icon = new ImageIcon(img);
            JLabel label = new JLabel(icon);
            JOptionPane.showMessageDialog(null, label);
         } catch (IOException e) {
            e.printStackTrace();
         }
    }
}