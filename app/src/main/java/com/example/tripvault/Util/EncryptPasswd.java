package com.example.tripvault.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPasswd {

	public static byte[] encryptMD5 (byte [] data) throws Exception{
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(data);
		return messageDigest.digest();

	}


}
