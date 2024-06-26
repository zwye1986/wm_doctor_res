package com.pinde.sci.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;

/**
 * User: machengshuang
 * Date: 17-10-13
 * Version: 1.0
 */
public class MD5Util {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 字符串MD5加密
     *
     * @param origin 要加密的字符串
     * @return
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(resultString.getBytes("UTF-8"));
            resultString = byteArrayToHexString(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString.toUpperCase();
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }


    public static void main(String[] args) {
		System.out.println(MD5Encode("1441014897881.802kd84kjd38908qsdf"));

    }
}
