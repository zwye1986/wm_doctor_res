package com.pinde.sci.common;

import com.pinde.core.http.CharArrayResponseWrapper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.edc.IEdcAppBiz;
import com.pinde.sci.biz.gcp.IGcpProjBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.enums.edc.AppReqTypeEnum;
import com.pinde.sci.model.mo.EdcAppLog;
import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.SysUser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EdcAppLogFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(EdcAppLogFilter.class);
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		HttpServletResponse httpResponse =(HttpServletResponse) response;
		
		String getPath = StringUtil.defaultString(httpRequest.getRequestURL().toString());
		if(StringUtil.isNotBlank(httpRequest.getQueryString()) ){
			getPath = getPath+"?"+httpRequest.getQueryString();
		}

		if(getPath.contains("/edc/app/reqFunction")&&"post".equalsIgnoreCase(httpRequest.getMethod())){
			// 使用CharArrayWrapper包装器进行输出过滤 
			CharArrayResponseWrapper wrapper = new CharArrayResponseWrapper(httpResponse); 
			chain.doFilter(request, wrapper); 
            String html = wrapper.toString(); // 获取返回结果 
            PrintWriter out = httpResponse.getWriter(); 
            out.write(html); 
            out.close();
            
            String reqCode = request.getParameter("reqCode");
    		String reqParam = request.getParameter("reqParam");
    		String sign =  request.getParameter("sign");
    		
    		EdcAppLog applog = new EdcAppLog();
    		applog.setLogFlow(PkUtil.getUUID());
    		applog.setReqCode(reqCode);
    		applog.setReqCodeName(AppReqTypeEnum.getNameById(reqCode));
    		applog.setReqParam(reqParam);

			IEdcAppBiz appBiz = SpringUtil.getBean(IEdcAppBiz.class);
			IUserBiz userBiz = SpringUtil.getBean(IUserBiz.class);
			IGcpProjBiz projBiz = SpringUtil.getBean(IGcpProjBiz.class);

			String userFlow = _getReqParamString(reqParam, "userFlow");
			String projFlow = _getReqParamString(reqParam, "projFlow");

			if (StringUtil.isNotBlank(userFlow)) {
				SysUser user = userBiz.readSysUser(userFlow);
				applog.setReqUserFlow(userFlow);
    			applog.setReqUserName(user.getUserName());
    		}
    		if(StringUtil.isNotBlank(projFlow)){
    			PubProj proj = projBiz.readProject(projFlow);
    			applog.setReqProjFlow(projFlow);
    			applog.setReqProjName(proj.getProjName());
    		}
    		applog.setResultId(_getReqParamString(html,"resultId"));
    		applog.setResultName(_getReqParamString(html,"resultName"));
    		applog.setResponContent(html);
    		applog.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
    		applog.setSign(sign);
    		applog.setCreateTime(DateUtil.getCurrDateTime());
    		applog.setCreateUserFlow(userFlow);
    		applog.setModifyTime(DateUtil.getCurrDateTime());
    		applog.setModifyUserFlow(userFlow);
    		appBiz.add(applog);
		}else{
			chain.doFilter(request, response);			
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}


	private String _getReqParamString(String reqParam,String paramName){
		Document dom;
		try {
			dom = DocumentHelper.parseText(reqParam);
			 Node node = dom.getRootElement().selectSingleNode(paramName);
			 if(node != null  ){
				 return node.getText();
			 }
		} catch (DocumentException e) {
			
		}
		return "";
	}
}
