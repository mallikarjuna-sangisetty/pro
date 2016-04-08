
package com.project.traffic.uiImpl;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import com.project.traffic.constants.ConsoleMessageConstants;
import com.project.traffic.controller.TransportaionDetailsController;
import com.project.traffic.controller.UserController;
import com.project.traffic.enums.MainEnum;
import com.project.traffic.exception.StandardException;
import com.project.traffic.model.User;
import com.project.traffic.persistenceUtil.DataBaseUtil;
import com.project.traffic.session.AppSession;
import com.project.traffic.util.InputFromConsoleUtil;

public class AdminMenu {
  
    private UserController userController;
    public static enum AdminMenuEnum implements MainEnum{
        FEED_DATA(1,"Feed data"),
        GENERATE_REPORTS(2,"Generate Reports"),
        UPLOAD_REPORTS_TO_CLOUD(3,"Upload reports to cloud"),
        MANAGE_USERS(4,"Manage Users"),
        SIGNOUT(5,"SignOut");

        private String message;
        private int value;

        private AdminMenuEnum(int value , String message ) {
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
    
    public static enum UserManageEnum implements MainEnum{
        ACTIVATE(1,"Activate User"),
        DEACTIVATE(2,"Deactivate User"),
        BACK(3,"Back");

        private String message;
        private int value;

        private UserManageEnum(int value , String message ) {
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
    
    public static enum ReportMenuEnum implements MainEnum{
        PROVIDERVSSEATS(1,"Providers vs No.of Seats"),
        PROVIDERSVSTRAVELLED(2,"Providers vs Travelled "),
        VALUEDCUSTOMERS(3,"Most Valued Customers"),
        MOSTTRAVELLED(4,"Most travelled destinations"),
        MOSTTRAFFICSOURCE(5,"High traffic Airports"),
        MOREDETAILEDREPORTS(6,"More Detailed Reports"),
        BACK(7,"Back");

        private String message;
        private int value;

        private ReportMenuEnum(int value , String message ) {
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
    
    public static enum DetailedReportEnum implements MainEnum{
        YEARWISE(1,"Yearly Data"),
        QUARTERWISE(2,"Quarterly Data"),
        MONTHWISE(3,"Monthly Data"),
        WEEKWISE(4,"Weekly Data"),
        DAYWISE(5,"Daily Data"),
        HOURWISE(6,"Hourly Data"),
        BACK(7,"Back");

        private String message;
        private int value;

        private DetailedReportEnum(int value , String message ) {
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

        AdminMenuEnum choice = null;
        try {
            do{
                choice = InputFromConsoleUtil.enumValues(AdminMenuEnum.class);

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
                        ReportMenu reportMenu = new ReportMenu();
                        reportMenu.reportMenu();
                    break;
                case UPLOAD_REPORTS_TO_CLOUD:
                    break;
                case MANAGE_USERS:
                    userController = new UserController();
                    List<User> users = userController.findAllUsers();
                    DataBaseUtil.closeSession();
                    new UserProfileHandler().displayUser(users);
                    int index = InputFromConsoleUtil.getIndex(ConsoleMessageConstants.TAKE_INDEX, users.size());
                    User user = users.get(index);
                    userController.userManageMenu(user);
                    
                    break;
                case SIGNOUT:
                	AppSession.session.clear();
                    break;
                }
            }while(choice != AdminMenuEnum.SIGNOUT);
        } catch (StandardException e) {
            System.out.println(e.getMessage());
        }
    }
}
