package com.pinde.sci.ctrl.sch.plan.util;

public class StringUtil {


	public static boolean isBlank(String str) {
		int strLen;
		if(str != null && (strLen = str.length()) != 0) {
			for(int i = 0; i < strLen; ++i) {
				if(!Character.isWhitespace(str.charAt(i))) {
					return false;
				}
			}

			return true;
		} else {
			return true;
		}
	}

	public static boolean isNotBlank(String str){
		return !isBlank( str);
	}

	public static boolean isNumeric(String cs) {
		// 判断是否为空，如果为空则返回false
		if (isBlank(cs)) {
			return false;
		}
		// 通过 length() 方法计算cs传入进来的字符串的长度，并将字符串长度存放到sz中
		final int sz = cs.length();
		// 通过字符串长度循环
		for (int i = 0; i < sz; i++) {
			// 判断每一个字符是否为数字，如果其中有一个字符不满足，则返回false
			if (!Character.isDigit(cs.charAt(i))) {
				return false;
			}
		}
		// 验证全部通过则返回true
		return true;
	}

}
