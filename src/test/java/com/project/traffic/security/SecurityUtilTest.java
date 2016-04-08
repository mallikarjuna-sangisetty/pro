package com.project.traffic.security;

import org.junit.Test;

public class SecurityUtilTest {

	@Test
	public void encryption() throws Exception{
		String plainText = "kavya";
		SecurityUtil s = new SecurityUtil();
		String encrypted = s.encryption(plainText);
		System.out.println(encrypted);
		System.out.println(s.decryption(encrypted));
		org.junit.Assert.assertEquals(plainText, s.decryption(encrypted));
		
		plainText = "##";
		encrypted = s.encryption(plainText);
		System.out.println(encrypted);
		System.out.println(s.decryption(encrypted));
		org.junit.Assert.assertEquals(plainText,s.decryption(encrypted));

	}
	
	@Test
	public void add(){
		SecurityUtil s = new SecurityUtil();
		int n = s.add(-2, 3);
		org.junit.Assert.assertEquals(1,n);
	}
}
