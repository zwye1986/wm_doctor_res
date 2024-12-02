package com.pinde.sci.common;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpContextFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(HttpContextFilter.class);
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		HttpServletResponse httpResponse =(HttpServletResponse) response;
		String getPath = StringUtil.defaultString(httpRequest.getRequestURL().toString());
		if(getPath.contains("/jsres/doctor/") || getPath.contains("/jsres/doctorRecruit/") || getPath.contains("/jsres/recruitDoctorInfo/")){
			// 获取请求是从哪里来的
			String referer = httpRequest.getHeader("referer");
			// 如果是直接输入的地址，或者不是从本网站访问的重定向到本网站的首页
			if (referer == null) {
				if(!getPath.contains("/jsres/doctor/index")){
					throw new RuntimeException("暂无访问权限！");
				}
			}
		}

		if(StringUtil.isNotBlank(httpRequest.getQueryString()) ){
			getPath = getPath+"?"+httpRequest.getQueryString();
		}
		//
		String servletPath = httpRequest.getServletPath();
        httpRequest.setAttribute(com.pinde.core.common.GlobalConstant.PAGE_SERVLET_PATH, servletPath);
		//		logger.debug("HttpContextFilter......{}",getPath);
		GlobalContext.setRequest((HttpServletRequest) request);
		GlobalContext.setResponse((HttpServletResponse) response);
		if("get".equalsIgnoreCase(httpRequest.getMethod())){
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(request.getParameter("isMainFrame"))) {
				String mainFrameSrc = httpRequest.getRequestURL().toString();
				if(StringUtil.isNotBlank(httpRequest.getQueryString()) ){
					mainFrameSrc = mainFrameSrc+"?"+httpRequest.getQueryString()+"&isMainFrame=Y";
				}else{
					mainFrameSrc = mainFrameSrc+"?isMainFrame=Y";
				}
				GlobalContext.getSession().setAttribute("mainFrameSrc", mainFrameSrc);
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
