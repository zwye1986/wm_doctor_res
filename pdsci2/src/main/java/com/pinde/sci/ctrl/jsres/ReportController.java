package com.pinde.sci.ctrl.jsres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * ~~~~~~~~~溺水的鱼~~~~~~~~
 *
 * @Author: 吴强
 * @Date: 2024/12/09/8:53
 * @Description:
 */
@Controller
@RequestMapping("/jsres/report")
public class ReportController {

////    public static String reportPageUrl = "http://127.0.0.1:9940/";
//    @Value("${report.page.url}")
//    private String reportPageUrl;
    @Autowired
    private Environment env;
    @GetMapping("/toDept")
    public String toReporstDeptPage(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String session = "";
        if(cookies!=null){
            for(Cookie cookie : cookies){
                if("SESSION".equals(cookie.getName())){
                    session = cookie.getValue();
                }
            }
        }
        String reportPageUrl = String.valueOf(env.getProperty("report.page.url"));
//        String reportPageUrl = environment.getProperty("report.page.url");
        String iframeUrl = reportPageUrl+"departmentReport?isIframe=true&session="+session;
        request.setAttribute("url", iframeUrl);
        request.setAttribute("pageId","dept_iframe");
        return "jsres/report/index";
    }
    @GetMapping("/toDoc")
    public String toReporstDocPage(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String session = "";
        if(cookies!=null){
            for(Cookie cookie : cookies){
                if("SESSION".equals(cookie.getName())){
                    session = cookie.getValue();
                }
            }
        }
//        String reportPageUrl = environment.getProperty("report.page.url");
        String reportPageUrl = String.valueOf(env.getProperty("report.page.url"));
        String iframeUrl = reportPageUrl+"personnelReport?isIframe=true&session="+session;
        request.setAttribute("url", iframeUrl);
        request.setAttribute("pageId","doc_iframe");
        return "jsres/report/index";
    }
}
