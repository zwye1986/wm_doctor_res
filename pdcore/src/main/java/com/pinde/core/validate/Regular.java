package com.pinde.core.validate;

/**
 * 常用正则表达式
 * 
 * @author shadow
 * @email 124010356@qq.com
 * @create 2012.04.28
 */
public interface Regular {

	/**
	 * 数字
	 */
	public static final String DIGITAL = "^[\\-\\+]?(([0-9]+)([\\.,]([0-9]+))?|([\\.,]([0-9]+))?)$";
	public static final String DIGITAL_MESSAGE = "this parameter is only digital";

	/**
	 * 整数
	 */
	public static final String INTEGER = "^[\\-\\+]?\\d+$";
	public static final String INTEGER_MESSAGE = "this parameter is only integer";

	/**
	 * 日期
	 */
	public static final String DATE = "^\\d{4}[\\/\\-](0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])$";
	public static final String DATE_MESSAGE = "this parameter is only date";
	/**
	 * IPV4
	 */
	public static final String IPV4 = "^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$";
	public static final String IPV4_MESSAGE = "this parameter is only ipv4";

	/**
	 * 0-9数字
	 */
	public static final String NUMBER = "^[0-9\\ ]+$";
	public static final String NUMBER_MESSAGE = "this parameter is only number";

	/**
	 * a-Z字母
	 */
	public static final String LETTER = "^[a-zA-Z\\ \']+$";
	public static final String LETTER_MESSAGE = "this parameter is only letter";

	/**
	 * a-Z0-9组合
	 */
	public static final String LETTER_NUMBER = "^[0-9a-zA-Z]+$";
	public static final String LETTER_NUMBER_MESSAGE = "this parameter is only letter and number";

	/**
	 * 邮箱
	 */
	public static final String EMAIL = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
	public static final String EMAIL_MESSAGE = "this parameter is only email";

	/**
	 * 电话号码
	 */
	public static final String PHONE = "^(1[3,5,8,7]{1}[\\d]{9})|(((400)-(\\d{3})-(\\d{4}))|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)$";
	public static final String PHONE_MESSAGE = "this parameter is only phone";

	/**
	 * 网址
	 */
	public static final String URL = "^(https|http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";
	public static final String URL_MESSAGE = "this parameter is only url";

	/**
	 * 中文
	 */
	public static final String CHINESE = "^[\u4E00-\u9FA5]+$";
	public static final String CHINESE_MESSAGE = "this parameter is only chinese";

	/**
	 * QQ号码
	 */
	public static final String QQ = "^[1-9][0-9]{4,11}$";
	public static final String QQ_MESSAGE = "this parameter is only qq";

	/**
	 * 身份证
	 */
	public static final String IDCARD = "(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$)";
	public static final String IDCARD_MESSAGE = "this parameter is only idcard";

	/**
	 * 不允许存在空格
	 */
	public static final String NOTSPACE = "^[^/ ]*$";
	public static final String NOTSPACE_MESSAGE = "this parameter not allowed space";

	/**
	 * 前后不允许存在空格
	 */
	public static final String QHSPACE = "^[^\\s].*[^\\s]$";
	public static final String QHSPACE_MESSAGE = "this parameter not allowed spac";

	/**
	 * 用户名规则
	 */
	public static final String USERNAME = "^[a-zA-Z_][\\w]{2,13}$";
	public static final String USERNAME_MESSAGE = "this parameter is wrongful";
}
