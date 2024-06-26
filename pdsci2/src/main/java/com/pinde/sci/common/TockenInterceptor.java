package com.pinde.sci.common;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TockenInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(TockenInterceptor.class);

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
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object obj) throws Exception {
		String csrftoken = (String)request.getSession().getAttribute("csrftoken");
		if(StringUtil.isBlank(csrftoken)){
			request.getSession().setAttribute("csrftoken", PkUtil.getUUID());
		}
		return true;
	}

}
