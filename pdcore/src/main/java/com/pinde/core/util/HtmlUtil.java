package com.pinde.core.util;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @ClassName HtmlUtil
 * @Description html文本处理
 * @Author fengxf
 * @Date 2020/11/19
 */
public class HtmlUtil {

	/**
	 * 特殊字符转义，避免XSS
	 * @param content
	 * @return
	 */
	public static String escapehtml(String content){
		return StringEscapeUtils.escapeHtml4(content);
	}
}