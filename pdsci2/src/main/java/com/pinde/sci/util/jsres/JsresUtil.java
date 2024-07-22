package com.pinde.sci.util.jsres;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.common.InitConfig;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JsresUtil {

	public static String getTitle(HttpServletRequest request, HttpServletResponse response,ServletContext application) {
		String title= (String) request.getSession().getAttribute("sysTitle");
		if(!StringUtil.isNotBlank(title))
		{
			title= (String) application.getAttribute("sysTitle");
		}

		if(!StringUtil.isNotBlank(title))
		{
			String url= (String) request.getSession().getAttribute("loginUrl");
			if(!StringUtil.isNotBlank(url))
			{
				url= (String) application.getAttribute("loginUrl");
			}
			title= InitConfig.jsResLoginTitleMap.get(url);
		}
		if(!StringUtil.isNotBlank(title))
		{
			title= InitConfig.getSysCfg("sys_title_name");
		}
		return title;
	}
	public static String getUrl(HttpServletRequest request, HttpServletResponse response,ServletContext application) {
		String url= (String) request.getSession().getAttribute("loginUrl");
		if(!StringUtil.isNotBlank(url))
		{
			url= (String) application.getAttribute("loginUrl");
		}
		if(!StringUtil.isNotBlank(url))
		{
			url= "/inx/jsres";
		}
		return url;
	}
	public static String getBannerUrl(HttpServletRequest request, HttpServletResponse response,ServletContext application) {
		Map<String,String> bannerMap=new HashMap<>();
		String getPath = request.getServerName();
		bannerMap.put("www.js.ezhupei.zdxx.com","zdyy");
		bannerMap.put("www.js.ezhupei.cz.com","cz");
		bannerMap.put("localhost","zdyy");
		String bannerUrl="";
		if(StringUtil.isNotBlank(getPath))
		{
			bannerUrl=InitConfig.jsResLoginBannerImgMap.get(getPath);
		}
		if(StringUtil.isNotBlank(bannerUrl))
		{
			bannerUrl="/pdsci/jsp/inx/jsres/orgLogin/banner_text/"+bannerUrl+".png";
		}
		return bannerUrl;
	}
}
