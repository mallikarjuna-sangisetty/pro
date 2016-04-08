package com.project.traffic.mail;

import org.junit.Test;

import com.project.traffic.constants.MailConstants;
import com.project.traffic.model.User;
import com.project.traffic.util.FileUtil;

public class SSLMailUtilTest {
	
	@Test
	public void sendWelcomeMail() {
		User user = new User();
		user.setEmail("kavya@gmail.com");
		String body = FileUtil.readFile("bodyContent.html");
		body = body.replaceAll("<tomail>", user.getEmail());
		body = body.replaceAll("<company>", MailConstants.COMPANY);
		body = body.replaceAll("<welcome>", MailConstants.WELCOME_AIRWAYS);
		//body = body.replaceAll("<password>", user.getPassword());
        
		SSLMailUtil.sendMail(user.getEmail(), MailConstants.WELCOME_AIRWAYS, body);
	}
	
	@Test
	public void sendForgotPasswordMail() {
		User user = new User();
		user.setEmail("kavya@gmail.com");
		String body = FileUtil.readFile("passwordRecovery.html");
		body = body.replaceAll("<tomail>", user.getEmail());
		//body = body.replaceAll("<password>", user.getPassword());
        
		SSLMailUtil.sendMail(user.getEmail(), MailConstants.FORGOT_PASSWORD, body);
	}
}