package com.pinde.core.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class PyUtil {

	/**
	 * 获取汉字串拼音首字母，英文字符不变
	 * 
	 * @param chinese
	 *            汉字串
	 * @return 汉语拼音首字母
	 */
	public static String getFirstSpell(String chinese) {
		if(chinese==null){
			return null;
		}
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(
							arr[i], defaultFormat);
					if (temp != null) {
						pybf.append(temp[0].charAt(0));
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString().replaceAll("\\W", "").trim();
	}

	/**
	 * 获取汉字串拼音，英文字符不变
	 * 
	 * @param chinese
	 *            汉字串
	 * @return 汉语拼音
	 */
	public static String getFullSpell(String chinese) {
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i],
							defaultFormat)[0]);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString();
	}

	//将中文转换为英文
	public static String getEname(String name) throws BadHanyuPinyinOutputFormatCombination {
		HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
		pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);    //设置样式
		pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		return PinyinHelper.toHanyuPinyinString(name, pyFormat, "");
	}
	//姓、名 英文第一个字母大写
	public static String getUpEname(String name) throws BadHanyuPinyinOutputFormatCombination {
		char[] strs = name.toCharArray();
		String newname = null;
		if (strs.length == 2) {    //如果姓名只有两个字
			newname = toUpCase(getEname("" + strs[1])) + " " + toUpCase(getEname("" + strs[0]));
		} else if (strs.length == 3) {    //如果姓名有三个字
			newname = toUpCase(getEname("" + strs[1] + strs[2])) + " "+ toUpCase(getEname("" + strs[0]));
		} else if (strs.length == 4) {    //如果姓名有四个字
			newname = toUpCase(getEname("" + strs[2] + strs[3])) + " "+ toUpCase(getEname("" + strs[0] + strs[1]));
		} else {
			newname = toUpCase(getEname(name));
		}

		return newname;
	}
	//首字母大写
	private static String toUpCase(String str) {
		StringBuffer newstr = new StringBuffer();
		newstr.append((str.substring(0, 1)).toUpperCase()).append(
				str.substring(1, str.length()));

		return newstr.toString();
	}
	//转拼音结束

	//姓、名 英文第一个字母大写（中国习惯-姓在前名在后）
	public static String getUpEnameCH(String name) throws BadHanyuPinyinOutputFormatCombination {
		char[] strs = name.toCharArray();
		String newname = null;
		if (strs.length == 2) {    //如果姓名只有两个字
			newname = toUpCase(getEname("" + strs[0])) + " " + toUpCase(getEname("" + strs[1]));
		} else if (strs.length == 3) {    //如果姓名有三个字
			newname = toUpCase(getEname("" + strs[0])) + " "+ toUpCase(getEname("" + strs[1] + strs[2]));
		} else if (strs.length == 4) {    //如果姓名有四个字
			newname = toUpCase(getEname("" + strs[0] + strs[1])) + " "+ toUpCase(getEname("" + strs[2] + strs[3]));
		} else {
			newname = toUpCase(getEname(name));
		}
		return newname;
	}
}
