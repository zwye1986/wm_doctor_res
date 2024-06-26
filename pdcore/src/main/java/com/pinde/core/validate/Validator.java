package com.pinde.core.validate;

/**
 * 服务器端校验器
 * 
 * @author shadow
 * @email 124010356@qq.com
 * @create 2012.04.28
 */
public interface Validator extends Regular {

	/**
	 * 判断是否数字
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isNumber(String str);

	/**
	 * 判断是否整型
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isInteger(String str);

	/**
	 * 判断是否日期
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isDate(String str);

	/**
	 * 判断是否IPV4
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isIpv4(String str);

	/**
	 * 判断是否为纯数字
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isOnlyNumberSp(String str);

	/**
	 * 判断是否纯字母
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isOnlyLetterSp(String str);

	/**
	 * 判断是否纯字母数字组合
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isOnlyLetterNumber(String str);

	/**
	 * 判断是否邮箱
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isEmail(String str);

	/**
	 * 判断是否电话号码
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isPhone(String str);

	/**
	 * 判断是否URL
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isUrl(String str);

	/**
	 * 判断是否中文
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isChinese(String str);

	/**
	 * 判断是否为空
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isNull(String str);

	/**
	 * 判断是否最小长度
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isMinSize(String str, int len);

	/**
	 * 判断是否最大长度
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isMaxSize(String str, int len);

	/**
	 * 判断是否最小值
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isMinValue(String str, int val);

	/**
	 * 判断是否最大值
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isMaxValue(String str, int val);

	/**
	 * 判断用户名是否合法(开头只能为字母或下划线,长度3-13)
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isUsername(String str);

	/**
	 * 判断是否QQ号码
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isQQ(String str);

	/**
	 * 判断是否合法身份证号码
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isIDCard(String str);

	/**
	 * 判断是否没有空格
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isNOSpace(String str);

	/**
	 * 去除所有空格
	 * 
	 * @param str
	 * @return String
	 */
	public String removeNOSpace(String str);

	/**
	 * 去除前后空格
	 * 
	 * @param str
	 * @return String
	 */
	public String removeQHOSpace(String str);

	/**
	 * 判断前后是否有空格
	 * 
	 * @param str
	 * @return Boolean
	 */
	public boolean isQHSpace(String str);
	
	/**
	 * 判断是否存在特殊字符
	 * @param str
	 * @return Boolean
	 */
	public boolean isNoSpLetter(String str);
	
	/**
	 * 自定义正则校验
	 * @param string
	 * @param regex
	 * @return Boolean
	 */
	public boolean matches(String string, String regex);

}
