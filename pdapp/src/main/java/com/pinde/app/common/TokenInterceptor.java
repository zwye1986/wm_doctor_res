package com.pinde.app.common;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class TokenInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

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
		String token = request.getParameter("token");
		String userFlow = request.getParameter("userFlow");
		if (StringUtil.isBlank(userFlow)) {
			request.setAttribute("resultId","789456");
			request.setAttribute("resultType","惟一标识符不存在");
			String url="/app/token?resultId=789456&resultType=惟一标识符不存在";
//			request.getRequestDispatcher("url").forward(request, response);
//			return false;
		}
		if (StringUtil.isBlank(token)) {
			request.setAttribute("resultId","789456");
			request.setAttribute("resultType","token信息不存在");
			String url="/app/token?resultId=789456&resultType=token信息不存在";
//			request.getRequestDispatcher("url").forward(request, response);
//			return false;
		}
		boolean f= TokenUtil.tokenEquals(userFlow,token);
		if(!f)
		{
			request.setAttribute("resultId","789456");
			request.setAttribute("resultType","token错误");
			String url="/app/token?resultId=789456&resultType=token错误";
//			request.getRequestDispatcher("url").forward(request, response);
//			return false;
		}
		return true;
	}

}
