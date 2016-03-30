package com.project.traffic.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecurityUtil {

	Cipher cipher;
	BASE64Encoder base64encoder = new BASE64Encoder();
	BASE64Decoder base64decoder = new BASE64Decoder();
	SecretKey key;
	public SecurityUtil(){

		try {
			cipher = Cipher.getInstance("DES");
			DESKeySpec keySpec = new DESKeySpec("four+secret-3ee+hprise".getBytes("UTF8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			key = keyFactory.generateSecret(keySpec);
		} catch (Exception e) {
			System.err.println("Some security issues occured please contact admin.");
		}
	}
	public String encryption(String plainText) throws Exception{
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return base64encoder.encode(cipher.doFinal(plainText.getBytes("UTF8")));
	}

	public String decryption(String encryptedText) throws Exception{
		byte[] encrypedPwdBytes = base64decoder.decodeBuffer(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(encrypedPwdBytes));
	}
}
