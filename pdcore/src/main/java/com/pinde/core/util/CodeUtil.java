package com.pinde.core.util;

import java.io.Reader;
import java.security.MessageDigest;
import java.sql.Clob;

public class CodeUtil {

	public static String sha1(String inStr) {
		String outStr = null;
		try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(inStr.getBytes("UTF-8"));
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
		} catch (Exception nsae) {
			nsae.printStackTrace();
		}
		return outStr;
	}
	
	public static String md5(String inStr) {
		String outStr = null;
		try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(inStr.getBytes("UTF-8"));
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
		} catch (Exception nsae) {
			nsae.printStackTrace();
		}
		return outStr;
	}
	
	public static String md5(Clob clob) {
		String str = clobToString(clob);
		str = str==null?"":str;
		return md5(str);
	}
	
	public static String md5(Clob clob1,Clob clob2,Clob clob3) {
		String str1 = clobToString(clob1);
		str1 = str1==null?"":str1;
		str1 = str1.trim();
		
		String str2 = clobToString(clob2);
		str2 = str2==null?"":str2;
		str2 = str2.trim();
		
		String str3 = clobToString(clob3);
		str3 = str3==null?"":str3;
		str3 = str3.trim();
		
		return md5(str1+str2+str3);
	}
	
	/**
	 * 将Clob转成String ,静态方法
	 * 
	 * @param clob
	 *            字段
	 * @return 内容字串，如果出现错误，返回 null
	 */
	public static String clobToString(Clob clob) {
		if (clob == null)
			return null;
		StringBuffer sb = new StringBuffer();
		Reader clobStream = null;
		try {
			clobStream = clob.getCharacterStream();
			char[] b = new char[60000];// 每次获取60K
			int i = 0;
			while ((i = clobStream.read(b)) != -1) {
				sb.append(b, 0, i);
			}
		} catch (Exception ex) {
			sb = null;
		} finally {
			try {
				if (clobStream != null) {
					clobStream.close();
				}
			} catch (Exception e) {
			}
		}
		if (sb == null)
			return null;
		else
			return sb.toString();
	}
	
	public static void main(String [] args){
		System.err.print(md5("暑湿困阻中焦证的主要症状是？A"));
	}

}
