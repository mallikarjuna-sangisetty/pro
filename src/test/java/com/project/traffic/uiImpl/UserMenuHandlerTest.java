package com.project.traffic.uiImpl;

import org.junit.BeforeClass;
import org.junit.Test;

import com.project.traffic.model.User;
import com.project.traffic.session.AppSession;

public class UserMenuHandlerTest {

	static UserMenuHandler handler;
	@BeforeClass
	public static void setup(){
		handler = new UserMenuHandler();
	}
	@Test
	public void handleBooking(){
		User user = new User();
		user.setId(3);
		AppSession.session.put("loginUser",user);
		handler.handleBooking();
	}
}
