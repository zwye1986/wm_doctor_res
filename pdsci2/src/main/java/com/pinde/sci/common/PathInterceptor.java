package com.pinde.sci.common;

import com.pinde.core.license.PdLicense;
import com.pinde.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class PathInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(PathInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception err)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object obj) throws Exception {
		String getPath = request.getRequestURL().toString();
		String getPath2 = request.getRequestURL().toString();
		if(StringUtil.isNotBlank(request.getQueryString()) ){
			getPath = getPath+"?"+request.getQueryString();
		}
		if(getPath2.indexOf("/path")>=0)
		{
			return true;
		}
		if(getPath.indexOf("../")>=0||getPath.indexOf("./")>=0
				||getPath.indexOf("..\\")>=0||getPath.indexOf(".\\")>=0)
		{
			request.getRequestDispatcher("/path").forward(request, response);
			return false;
		}
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()){
			String val = paramNames.nextElement();
			val = request.getParameter(val);
//			logger.debug("攻击检测: " + val);
			if(val != null){
				String asciiVal = new String(val.getBytes(),"ASCII");
//				if(!checkTS(asciiVal.toLowerCase()))
//				{
//					logger.info("可能含有特殊符号,传入值["+val+"],拒绝服务!");
//					request.getRequestDispatcher("/path").forward(request, response);
//					return false;
//				}
			}
		}
		return true;
	}

	/**
	 * 验证是否含有特殊字符
	 * @param str 验证字符串
	 * @return
	 */
	public static boolean checkTS(String str)
	{
		// 过滤的特殊字符（<>、回车、换行、空格）
		String[] tsStrs = new String[] {"<", ">", "%0d", "%0a", "\r", "\n"};
		int num = 0;
		if (str != null && str.length() > 0)
		{
			for (int i = 0; i < tsStrs.length; i++)
			{
				num = str.indexOf(tsStrs[i]);
				if (num != -1)
				{
					return false;
				}
			}
		}
		return true;
	}
}
