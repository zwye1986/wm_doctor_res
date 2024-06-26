package com.pinde.core.validate;


/**
 * JAVA 正则验证数据
 * 
 * @author shadow
 * @email 124010356@qq.com
 * @create 2012.04.28
 */
public class ValidatorImpl implements Validator {

	public boolean isNumber(String str) {
		return str
				.matches("^[\\-\\+]?(([0-9]+)([\\.,]([0-9]+))?|([\\.,]([0-9]+))?)$");
	}

	public boolean isInteger(String str) {
		return str.matches("^[\\-\\+]?\\d+$");
	}

	public boolean isDate(String str) {
		return str
				.matches("^\\d{4}[\\/\\-](0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])$");
	}

	public boolean isIpv4(String str) {
		return str
				.matches("^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$");
	}

	public boolean isOnlyNumberSp(String str) {
		return str.matches("^[0-9\\ ]+$");
	}

	public boolean isOnlyLetterSp(String str) {
		return str.matches("^[a-zA-Z\\ \']+$");
	}

	public boolean isOnlyLetterNumber(String str) {
		return str.matches("^[0-9a-zA-Z]+$");
	}

	public boolean isEmail(String str) {
		return str
				.matches("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");
	}

	public boolean isPhone(String str) {
		return str
				.matches("^(1[3,5,8,7]{1}[\\d]{9})|(((400)-(\\d{3})-(\\d{4}))|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)$");
	}

	public boolean isUrl(String str) {
		return str
				.matches("^(https|http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$");
	}

	public boolean isChinese(String str) {
		return str.matches("^[\u4E00-\u9FA5]+$");
	}

	public boolean isNull(String str) {
		str = str.trim();
		return str == null || str.equals("") ? false : true;
	}

	public boolean isMinSize(String str, int len) {
		str = str.trim();
		return str.length() < len ? false : true;
	}

	public boolean isMaxSize(String str, int len) {
		str = str.trim();
		return str.length() > len ? false : true;
	}

	public boolean isMinValue(String str, int val) {
		try {
			int value = Integer.parseInt(str);
			return value < val ? false : true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isMaxValue(String str, int val) {
		try {
			int value = Integer.parseInt(str);
			return value > val ? false : true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isUsername(String str) {
		return str.matches("^[a-zA-Z_][\\w]{2,13}$");
	}

	public boolean isQQ(String str) {
		return str.matches("^[1-9][0-9]{4,11}$");
	}

	public boolean isIDCard(String str) {
		return str
				.matches("(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$)");
	}

	public boolean isNOSpace(String str) {
		return str.matches("^[^/ ]*$");
	}

	public String removeNOSpace(String str) {
		return str.replaceAll("[\\s/ ]*", "");
	}

	public String removeQHOSpace(String str) {
		return str.trim();
	}

	public boolean isQHSpace(String str) {
		return str.matches("^[^\\s].*[^\\s]$");
	}

	public boolean matches(String string, String regex) {
		return string.matches(regex);
	}

	public boolean isNoSpLetter(String str) {
		return str.matches("^[\\w\u4e00-\u9fa5]+$");
	}
	
}
