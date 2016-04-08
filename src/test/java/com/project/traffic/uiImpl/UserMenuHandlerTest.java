package com.project.traffic.uiImpl;

import org.junit.BeforeClass;
import org.junit.Test;

import com.project.traffic.model.User;
import com.project.traffic.session.AppSession;

public class UserMenuHandlerTest {

	static UserMenuHandler handler;
	@BeforeClass
	public static void setup(){
		User user = new User();
		user.setId(14);
		AppSession.session.put("loginUser",user);
		handler = new UserMenuHandler();
	}
	//@Test
	public void handleBooking(){
	
		//handler.handleBooking();
	}
	@Test
	public void cancelTicket(){
		//handler.cancelTicket();
	}
}
