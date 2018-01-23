package cn.lenchu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
	
	public static String encodePwdWithMD5(String pwd) {
		String newPwd = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bs = md.digest(pwd.getBytes());
			for(byte b : bs) {
				int a = b & 0xff;
				if (a < 16) {
					a = a + 16;
				}
				String hex = Integer.toHexString(a);
				newPwd = newPwd + hex;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return newPwd;
	}

}
