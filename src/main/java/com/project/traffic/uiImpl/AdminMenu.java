
package com.project.traffic.uiImpl;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.project.traffic.controller.ChartsController;
import com.project.traffic.controller.TransportaionDetailsController;
import com.project.traffic.enums.MainEnum;
import com.project.traffic.exception.StandardException;
import com.project.traffic.session.AppSession;
import com.project.traffic.util.InputFromConsoleUtil;

public class AdminMenu {
  
    public static enum UserMenuEnum implements MainEnum{
        FEED_DATA(1,"Feed data"),
        GENERATE_REPORTS(2,"Generate Reports"),
        UPLOAD_REPORTS_TO_CLOUD(3,"Upload reports to cloud"),
        MANAGE_USERS(4,"Manage Users"),
        SIGNOUT(5,"SignOut");

        private String message;
        private int value;

        private UserMenuEnum(int value , String message ) {
            this.message = message;
            this.value= value;

        }
        public int value() {
            return value;
        }

        public String getMessage() {
            return message;
        }
    }
    /**
     * Main menu
     * @throws IOException 
     * @throws BusinessLogicException
     */
    public void adminMenu() throws IOException{

        UserMenuEnum choice = null;
        try {
            do{
                choice = InputFromConsoleUtil.enumValues(UserMenuEnum.class);

                switch (choice) {
                case FEED_DATA:
                	String fileName = InputFromConsoleUtil.getString("Feed file path:", true);
                	TransportaionDetailsController tsdc = new TransportaionDetailsController();
                	if(tsdc.feedData(fileName))
                		JOptionPane.showMessageDialog(null,"Successfully data feeding completed.\nTotal Rows :"+AppSession.session.get("count"),"Information",JOptionPane.INFORMATION_MESSAGE);
                	else
                		JOptionPane.showMessageDialog(null,"Data feeding failed.Only \".xls\" files are accepted.","Information",JOptionPane.ERROR_MESSAGE);
                    break;
                case GENERATE_REPORTS:
                	TransportaionDetailsController tsdc1 = new TransportaionDetailsController();
                	ChartsController.generatePieChart(tsdc1.generateReportByAirlinesVsFlights(), "Airlines vs No.of flights");
                    break;
                case UPLOAD_REPORTS_TO_CLOUD:
                    break;
                case MANAGE_USERS:
                    break;
                case SIGNOUT:
                	AppSession.session.clear();
                    break;
                }
            }while(choice != UserMenuEnum.SIGNOUT);
        } catch (StandardException e) {
            System.out.println(e.getMessage());
        }
    }
}
