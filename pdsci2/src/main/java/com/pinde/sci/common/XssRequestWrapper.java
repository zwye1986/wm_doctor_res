package com.pinde.sci.common;

import com.pinde.core.util.HtmlUtil;
import com.pinde.sci.ctrl.sch.plan.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 跨站脚本请求包装器，将html脚本转译成普通字符串
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

	public XssRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		return cleanXSS(value);
	}

	/**
	 * 转译get入参，防止站点脚本注入
	 */
	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		return cleanXSS(value);
	}

	private String cleanXSS(String value) {
		if(StringUtil.isNotBlank(value)){
			if(value.toLowerCase().contains("prompt") || value.toLowerCase().contains("confirm") || value.toLowerCase().contains("alert") || value.toLowerCase().contains("script") || value.toLowerCase().contains("javascript")){
				value = HtmlUtil.escapehtml(value);
			}
//			value = value.replaceAll("alert", "");
//			value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
//			value = value.replaceAll("script", "");
//			value = HtmlUtil.escapehtml(value);
		}
		return value;
	}

}