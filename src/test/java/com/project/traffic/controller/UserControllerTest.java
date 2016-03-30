package com.project.traffic.controller;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.project.traffic.enums.EnumUtils.GenderEnum;
import com.project.traffic.enums.EnumUtils.StatusType;
import com.project.traffic.enums.EnumUtils.UserType;
import com.project.traffic.model.User;

public class UserControllerTest {

	private static UserController controller;
	private static User user;
	
	@BeforeClass
	public static void setup(){
		controller = new UserController();
		user = new User();
		user.setCreatedDate(new Date().toString());
		user.setGender(GenderEnum.FEMALE);
		//user.setEmail("kavya@gmail.com");
		user.setPassword("pass");
		user.setUserType(UserType.ADMIN.getType());
		user.setStatus(StatusType.ACTIVE.getStatus());
	}
	
	@Test
	public void testSaveUser(){
		controller.saveUser(user);
	}
}
