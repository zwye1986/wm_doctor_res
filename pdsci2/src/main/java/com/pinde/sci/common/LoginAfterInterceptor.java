package com.pinde.sci.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAfterInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(LoginAfterInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception err)
			throws Exception {
		//令牌
//		String token=PkUtil.getUUID();
//		Cookie cookie=new Cookie("csrftoken",token);
//		response.addCookie(cookie);
//		request.getSession().setAttribute("csrftoken", token);

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object obj) throws Exception {
		return true;
	}

}
