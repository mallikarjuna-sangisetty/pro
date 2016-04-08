
package com.project.traffic.uiImpl;

import java.io.IOException;

import com.project.traffic.constants.ConsoleMessageConstants;
import com.project.traffic.constants.QueryConstatns;
import com.project.traffic.controller.ChartsController;
import com.project.traffic.controller.TransportaionDetailsController;
import com.project.traffic.exception.StandardException;
import com.project.traffic.uiImpl.AdminMenu.DetailedReportEnum;
import com.project.traffic.uiImpl.AdminMenu.ReportMenuEnum;
import com.project.traffic.util.InputFromConsoleUtil;


public class ReportMenu {

    /**
     * Main menu
     * @throws IOException 
     * @throws BusinessLogicException
     */
    public void reportMenu() throws IOException{

        ReportMenuEnum choice = null;
        try {
            do{
                choice = InputFromConsoleUtil.enumValues(ReportMenuEnum.class);
                TransportaionDetailsController tsdc = new TransportaionDetailsController();
                switch (choice) {
                case PROVIDERVSSEATS:
                    ChartsController.generatePieChart(tsdc.getData(QueryConstatns.PROVIDERS_VS_SEATS_PIECHART, -1), "Airlines vs No.of flights");
                    
                    break;
                case PROVIDERSVSTRAVELLED:
                    ChartsController.generatePieChart(tsdc.getData(QueryConstatns.PROVIDERS_VS_BOOKED, -1), "Airlines vs Booked Tickets");
                    break;
                case VALUEDCUSTOMERS:
                    int people = InputFromConsoleUtil.getInteger(ConsoleMessageConstants.TOPCUSTOMERS);
                    ChartsController.generatePieChart(tsdc.getData(QueryConstatns.VALUED_CUSTOMERS,people), "Valued Customers(top 5) vs Booked Tickets");
                    break;
                case MOSTTRAVELLED:
                    int topCount = InputFromConsoleUtil.getInteger(ConsoleMessageConstants.TOPDESTINATIONS);
                    ChartsController.generatePieChart(tsdc.getData(QueryConstatns.MOST_TRAVELLED_DESTINATIONS, topCount), "Most travelled destinations");
                    break;
                case MOSTTRAFFICSOURCE:
                    int top = InputFromConsoleUtil.getInteger(ConsoleMessageConstants.TOPSOURCES);
                    ChartsController.generatePieChart(tsdc.getData(QueryConstatns.MOST_TRAFFIC_SOURCES, top), "Most traffic Airports");
                    break;
                case MOREDETAILEDREPORTS:
                    detailedReportMenu();
                    break;
                case BACK:
                    break;
                }
            }while(choice != ReportMenuEnum.BACK);
        } catch (StandardException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void detailedReportMenu()throws StandardException, IOException{

        DetailedReportEnum choice = null;
        try {
            do{
                choice = InputFromConsoleUtil.enumValues(DetailedReportEnum.class);
                TransportaionDetailsController tsdc = new TransportaionDetailsController();
                switch (choice) {
                case YEARWISE:
                    int top = InputFromConsoleUtil.getInteger(ConsoleMessageConstants.PASTYEARS);
                    //ChartsController.generateLineChart("YEAR WISE REPORT", "Years", "Travelled", tsdc.getData(QueryConstatns.YEAR_WISE_TRAVELLED, top));
                    break;
                case QUARTERWISE:
                    break;
                case MONTHWISE:
                    break;
                case WEEKWISE:
                    break;
                case DAYWISE:
                    break;
                case HOURWISE:
                    break;
                case BACK:
                    break;
                }
            }while(choice != DetailedReportEnum.BACK);
        } catch (StandardException e) {
            System.out.println(e.getMessage());
        }
    
    }
    
}
