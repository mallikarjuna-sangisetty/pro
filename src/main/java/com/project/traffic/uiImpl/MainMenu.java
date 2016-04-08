
package com.project.traffic.uiImpl;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.project.traffic.controller.UserController;
import com.project.traffic.enums.MainEnum;
import com.project.traffic.exception.StandardException;
import com.project.traffic.mail.SSLMailUtil;
import com.project.traffic.model.User;
import com.project.traffic.session.AppSession;
import com.project.traffic.util.InputFromConsoleUtil;


public class MainMenu {

	private UserMenu userMenu;
	private AdminMenu adminMenu;
	private UserProfileHandler userProfileHandler;
	private UserController userController;

	private static final Logger LOGGER = Logger.getLogger(MainMenu.class);

	public MainMenu() {
		userMenu = new UserMenu();
		adminMenu = new AdminMenu();
		userProfileHandler = new UserProfileHandler();
		userController = new UserController();
	}


	public static enum HomeMenuEnum implements MainEnum{
		LOGIN(1,"Login"),
		SIGNUP(2,"SignUp"),
		FORGOTPASSWORD(3,"Forgot Password"),
		QUIT(4,"Quit");

		private String message;
		private int value;

		private HomeMenuEnum(int value , String message ) {
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
	public void mainMenu(){

		HomeMenuEnum choice = null;
		System.out
		.println("*************************************************************************************************");
		System.out
		.println("************************************Welcome to Kavya Airways*************************************");
		System.out
		.println("*************************************************************************************************");

		try {
			do{
				choice = InputFromConsoleUtil.enumValues(HomeMenuEnum.class);

				try {
					switch (choice) {
					case LOGIN:
						if(userProfileHandler.validateLogin()){
							User user = (User)AppSession.session.get("loginUser");
							if(user.getStatus() == com.project.traffic.enums.EnumUtils.StatusType.INACTIVE.value())
							{
							    JOptionPane.showMessageDialog(null,"Your account has been deactivated.Please contact admin.","Warning",JOptionPane.ERROR_MESSAGE);
							    break;
							}
							if(user.getUserType() == 1){
								userMenu.userMenu();
							}else{
								adminMenu.adminMenu();
							}
						}else{
							JOptionPane.showMessageDialog(null,"Invalid Username/password.Please try again.","Warning",JOptionPane.ERROR_MESSAGE);
						}
						break;
					case SIGNUP:
						User newUser = userProfileHandler.signup();
						userController.saveUser(newUser);
						if(SSLMailUtil.sendWelcomeMail(newUser))
							JOptionPane.showMessageDialog(null,"Thanks for registering with us.","Information",JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,"We are not able to deliver message to your email.","Information",JOptionPane.ERROR_MESSAGE);
						break;
					case FORGOTPASSWORD:
						User forgotUser = userProfileHandler.forgotPassword();
						if(forgotUser == null){
							JOptionPane.showMessageDialog(null,"You are not registered with us.","Information",JOptionPane.ERROR_MESSAGE);
							break;
						}
						if(SSLMailUtil.sendForgotPasswordMail(forgotUser))
							JOptionPane.showMessageDialog(null,"Please check your mail for password.","Information",JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,"We are not able to deliver message to your email.Please contact admin.","Information",JOptionPane.INFORMATION_MESSAGE);
						break;
					case QUIT:
						System.err.println("***********************************Thanks for visiting***********************************");
						break;
					}
				} catch (Exception e) {
					LOGGER.info("Exception in ",e);
				}
			}while(choice != HomeMenuEnum.QUIT);
		} catch (StandardException e) {
			LOGGER.info("Exception in ",e);
		}
	}

}
