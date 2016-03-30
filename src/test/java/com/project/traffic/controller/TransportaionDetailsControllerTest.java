package com.project.traffic.controller;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

public class TransportaionDetailsControllerTest {

	static TransportaionDetailsController controller ;
	
	@BeforeClass
	public static void setup(){
		controller = new TransportaionDetailsController();
	}
	//@Test
	public void feed(){
		controller.feedData("F:\\kavya\\Project\\data.xls");
	}
	
	@Test
	public void getData() throws IOException{
		
		ChartsController.generatePieChart(controller.generateReportByAirlinesVsFlights(), "Airlines vs No.of flights");
	}
}
