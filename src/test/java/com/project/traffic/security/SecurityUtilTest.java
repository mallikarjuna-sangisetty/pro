package com.project.traffic.security;

import org.junit.Test;

public class SecurityUtilTest {

	@Test
	public void encryption() throws Exception{
		String plainText = "##";
		SecurityUtil s = new SecurityUtil();
		String encrypted = s.encryption(plainText);
		System.out.println(encrypted);
		org.junit.Assert.assertEquals(plainText, s.decryption(encrypted));
		
		plainText = "##";
		encrypted = s.encryption(plainText);
		System.out.println(encrypted);
		org.junit.Assert.assertEquals(plainText,s.decryption(encrypted));

	}
	
	
	
}
