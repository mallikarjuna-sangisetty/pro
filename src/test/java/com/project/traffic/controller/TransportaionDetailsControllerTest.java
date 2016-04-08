package com.project.traffic.controller;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.project.traffic.constants.QueryConstatns;

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

	//@Test
	public void getData() throws IOException{

		ChartsController.generatePieChart(controller.getData("", -1), "Airlines vs No.of flights");
	}

	//@Test
	public void getDataBarChart() throws IOException{

		ChartsController.generateBarChart(controller.generateReportByAirlinesVsFlightsBarChart(), "Airlines vs No.of flights","Providers","No.of Seats");
	}

	//@Test
	public void getDataLineChart() throws IOException{
		ChartsController.generateLineChart("Line", "Providers", "Travelled", controller.getDataForLineChart(QueryConstatns.YEAR_WISE_TRAVELLED, 2));
	}
}
