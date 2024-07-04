package com.pinde.app.common;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;

/**
 * <p/>
 * User: Zhang Kaitao
 * <p/>
 * Date: 14-1-28
 * <p/>
 * Version: 1.0
 */
public class PasswordHelper {

    private static String algorithmName = "md5";
    private static int hashIterations = 2;

    /**
     * 密码加密
     *
     * @param userFlow   用户流水号
     * @param userPasswd 用户填写的密码
     * @return
     */
    public static String encryptPassword(String userFlow, String userPasswd) {

        String newPassword = new SimpleHash(algorithmName, userPasswd, ByteSource.Util.bytes(userFlow), hashIterations).toHex();

        return newPassword;
    }

    /**
     * MD5 加密32位
     *
     * @param encryptStr
     * @return
     */
    public static String encrypt32(String encryptStr) {
        System.out.println("encrypt32:" + encryptStr);
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    public static void main(String[] args) {
        System.out.println(encrypt32("<REQUEST><ORGID>2001</ORGID><ORGPID>105</ORGPID><ORGNAME>院长办公室2</ORGNAME><ORGTYPE>1</ORGTYPE><SPELLCODE>YZB2</SPELLCODE><ORGSHORTNAME>院长办公室2</ORGSHORTNAME><ORGENABLED>1</ORGENABLED><ORGDELETED>0</ORGDELETED><ORGCODE>00102</ORGCODE></REQUEST>9f9a5bc7111a4677bda79f25c7c98efd"));

    }
}
