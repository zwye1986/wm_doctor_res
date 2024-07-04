package com.pinde.app.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.pinde.core.http.CharArrayResponseWrapper;
import com.pinde.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

public class HttpContextFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(HttpContextFilter.class);
	private Properties props=System.getProperties();
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		HttpServletResponse httpResponse =(HttpServletResponse) response;

		// 使用CharArrayWrapper包装器进行输出过滤
		CharArrayResponseWrapper wrapper = new CharArrayResponseWrapper(httpResponse);
		chain.doFilter(request, wrapper);
		String resp = wrapper.toString(); // 获取返回结果

		if(StringUtil.defaultString(httpResponse.getContentType()).toLowerCase().contains("json")){
			logger.debug("app "+httpRequest.getMethod()+" request is:"+httpRequest.getRequestURL().toString());
			
			boolean isValid = false;
			try {
				String resp2 = JSON.toJSONString(JSON.parse(resp), SerializerFeature.PrettyFormat);
				resp = resp2;
				isValid = true;
			} catch (Exception e) {
				isValid = false;
			}
			
			if(StringUtil.isEquals(props.getProperty("druid.log.stmt.executableSql"), "true")){
        		String path = StringUtil.defaultString(httpRequest.getServletPath().toString());
        		if(StringUtil.isNotBlank(httpRequest.getQueryString()) ){
        			path = path+"?"+httpRequest.getQueryString();
        		}
            	
            	StringBuffer param = new StringBuffer();
				param.append("path="+path+"\n");
				Enumeration<String> paraNames = httpRequest.getParameterNames();
				for(Enumeration<String> e=paraNames;e.hasMoreElements();){
			       String thisName=e.nextElement().toString();
			       String thisValue= StringUtil.defaultString(httpRequest.getParameter(thisName));
			       param.append(thisName+"="+thisValue+"\n");
				}
				logger.debug("resp is: \n"+param+resp+"\n"+param);
				if(isValid==false){
					logger.debug("app response is not a valid json data ");
				}
			}
			logger.debug("app "+httpRequest.getMethod()+" request is:"+httpRequest.getRequestURL().toString());

		}
		try
		{

			PrintWriter out = httpResponse.getWriter();
			out.write(resp);
			out.close();
		}
		catch(Exception ex)
		{
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
