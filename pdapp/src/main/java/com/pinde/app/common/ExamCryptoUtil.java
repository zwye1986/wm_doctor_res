package com.pinde.app.common;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.lang.StringUtils;

import com.pinde.core.util.StringUtil;

public class ExamCryptoUtil {

	public static void main(String[] args) {
		System.err.println(
				decode("WAQF4DPk5sTmjwEEXS+Q8yvRFlC8297h8a3p+VOjqhze0AkC28EB39bY8/FzOFXb81kkZd4xAgUI0Cos8MVyIUR19M7F8d6mDpa3Awh3c6EIFg54VbqM32675JcsxEjECQHVnSV2XN8fe+DmSk59fE/ybLXuQ1x+eLRJOd2ZN4cuxW6f2cnUI10bq+0vzDRM2/Ly7GDpAay7Bfw9ztxas1VnwBcMj6kk4i/qxKRXnXjETmZFmo8UjV0nw/QSBvdp6Xjyi8LB7AKEN9UVijE3TgdNpY36CB0zLxYG6zNlkGiVY5aLMhJGENLc1cKMxRtBfxEV6WejT/n8XpfJsmHquT7rMlY5YXDFj2L0ZHYeTmLYrEkYKTIMshqqUWzWUFxF8f4ajFBRn/kw4pur85uDk3tMrLDu468vJxY2Ep7+LynO5ft6On3y9cY80BER7812PVTm42TTX6E3bZDOv9J8kLd18XoJX6lYOx6c+fpPefTS0FXvkbWTiFznEuRqfWK3HRI3Yhl4qqbf+g30e9vPdCUnHu02YiBdZk2VcaLeilnd0fClDpJ3E8R7/mrQJGlx+FMC6/cuPas6wZugLf+QdLP2f2/KTpvHHZw3n+AuKqdOPF46jeZhaw==",
						"南京品德网络信息技术有限公司"));
	}

	public static String decode(String data, String key) {
		key = StringUtil.defaultString(key);
		data = StringUtil.defaultString(data);
		/// 加密秘钥
		String strKey = SetKey(key, "pdkj123456");
		/// 密文
		String strKeyValue = data;
		/// 原始信息
		if (strKeyValue.endsWith("-")) {
			return Decode1(strKeyValue, strKey, "");
		} else {
			return Decode(strKeyValue, strKey);
		}
	}

	public static String Decode16(String strDecode) {
		String sResult = "";
		for (int i = 0; i < strDecode.length() / 4; i++) {
			sResult += (char) Short.parseShort(strDecode.substring(i * 4, 4));
		}
		return sResult;
	}

	public static String SetKey(String key, String psd) {
		// 将输入的密码变成8位可用密码
		byte[] data2 = computeMd5(key);
		StringBuffer hexString = new StringBuffer();
		// 字节数组转换为 十六进制 数
		for (int i = 0; i < data2.length; i++) {
			String shaHex = Integer.toHexString(data2[i] & 0xFF);
			if (shaHex.length() < 2) {
				hexString.append(0);
			}
			hexString.append(shaHex);
		}
		String sReg = CharToIntStr(hexString.toString());
		return StringUtils.rightPad(sReg, 8, '0').substring(0, 8);
	}

	public static byte[] computeMd5(String k) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 not supported", e);
		}
		md5.reset();
		byte[] keyBytes = null;
		try {
			keyBytes = k.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unknown string :" + k, e);
		}

		md5.update(keyBytes);
		return md5.digest();
	}

	public static String Decode1(String data, String key, String p) {
		String rtn = "";
		data = data.trim();
		data = data.substring(0, data.length() - 1);
		int len = data.length() / 4;
		for (int i = 0; i < len; i++) {
			char c0 = data.charAt(i * 4);
			char c1 = data.charAt(i * 4 + 1);
			char c2 = data.charAt(i * 4 + 2);
			char c3 = data.charAt(i * 4 + 3);
			rtn += Decode16(String.valueOf(c1) + String.valueOf(c2) + String.valueOf(c0) + String.valueOf(c3));
		}
		return rtn;
	}

	public static String Decode(String data, String key) {

		key = StringUtil.defaultString(key);
		data = StringUtil.defaultString(data);

		byte[] byEnc = null;
		byte[] result = null;
		try {
			byte[] byKey = key.getBytes("US-ASCII");
			byte[] byIV = key.getBytes("US-ASCII");
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(byKey);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
			deskey = keyFactory.generateSecret(spec);

			Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(byIV);
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
			result = cipher.doFinal(byEnc, 0, byEnc.length);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return new String(result);
	}

	/// <summary>
	/// 字母转数字串
	/// </summary>
	/// <param name="str"></param>
	/// <returns></returns>
	public static String CharToIntStr(String str) {
		String result = "";
		for (char ch : str.toCharArray()) {
			if (ch >= 'A' && ch <= 'Z')
				result += (int) ch - 65;
			else if (ch >= 'a' && ch <= 'z')
				result += (int) ch - 97;
			else
				result += ch;
		}
		return result;
	}

}
