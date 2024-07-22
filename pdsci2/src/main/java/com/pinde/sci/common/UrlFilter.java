package com.pinde.sci.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by www.0001.Ga on 2017-05-09.
 */
public class UrlFilter implements Filter {
	public final static String DEFAULT_URI_ENCODE = "UTF-8";

	private FilterConfig config = null;
	private String encode = null;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		this.encode = config.getInitParameter("DEFAULT_URI_ENCODE");
		if(this.encode == null) {
			this.encode = DEFAULT_URI_ENCODE;
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
						 FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String uri = request.getRequestURI();
		String ch = URLDecoder.decode(uri, encode);
		if(uri.equals(ch)) {
			chain.doFilter(req, res);
			return;
		}
		ch = ch.substring(request.getContextPath().length());
		config.getServletContext().getRequestDispatcher(ch).forward(req, res);
	}

	@Override
	public void destroy() {
		config = null;
	}
}