package com.pinde.sci.util;

import java.security.MessageDigest;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public class PasswordHelper {

	private static String algorithmName = "md5";
	private static int hashIterations = 2;

	/**
	 * 密码加密
	 * @param userFlow 用户流水号
	 * @param userPasswd 用户填写的密码
	 * @return
	 */
	public static String encryptPassword(String userFlow, String userPasswd) {

		String newPassword = new SimpleHash(algorithmName, userPasswd,ByteSource.Util.bytes(userFlow), hashIterations).toHex();

		return newPassword;
	}
	
	public static String MD5(String s) {
	try {
		byte[] btInput = s.getBytes();
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
		mdInst.update(btInput);
		byte[] md = mdInst.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < md.length; i++) {
			int val = ((int) md[i]) & 0xff;
			if (val < 16)
				sb.append("0");
			sb.append(Integer.toHexString(val));

		}
		return sb.toString().toLowerCase();
	} catch (Exception e) {
		return null;
	}
}
}
