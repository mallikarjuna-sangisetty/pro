
package com.project.traffic.uiImpl;

import com.project.traffic.enums.MainEnum;
import com.project.traffic.exception.StandardException;
import com.project.traffic.session.AppSession;
import com.project.traffic.util.InputFromConsoleUtil;

public class UserMenu {
  
	private UserMenuHandler userMenuHandler;
	
    public static enum UserMenuEnum implements MainEnum{
        BOOK(1,"Book"),
        CANCEL(2,"Cancel"),
        BOOK_HISTORY(3,"Booking History"),
        SIGNOUT(4,"SignOut");

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
     * @throws BusinessLogicException
     */
    public void userMenu(){
    	userMenuHandler = new UserMenuHandler();
        UserMenuEnum choice = null;
        try {
            do{
                choice = InputFromConsoleUtil.enumValues(UserMenuEnum.class);

                switch (choice) {
                case BOOK:
                	userMenuHandler.handleBooking();
                    break;
                case CANCEL:
                	userMenuHandler.cancelTicket();
                    break;
                case BOOK_HISTORY:
                	userMenuHandler.showBookingHistory();
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
