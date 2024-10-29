package com.pinde.app.common.interceptor;

import com.pinde.app.common.GlobalConstant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class UserLoginInterceptor implements HandlerInterceptor {
    /**
     * 判断session中有无用户信息，没有用户信息跳转到登陆页
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        Map<String, String[]> map = httpServletRequest.getParameterMap();
        for (String key : map.keySet()) {
            String[] values = httpServletRequest.getParameterValues(key);
            for (String value : values) {
                if (value.contains("'") || value.contains("%") || value.contains("=") || value.contains("(") || value.contains(")") || value.contains("*")) {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = httpServletResponse.getWriter();
                    writer.print("{\"resultId\":\"401\",\"resultType\":\"参数" + key + "存在不合法字符，请确认后再次请求！\"}");
                    writer.flush();
                    return false;
                }
            }
        }

//        String servletPath = httpServletRequest.getServletPath();
//        if(servletPath.contains("/wx/")) {
//            return true;
//        }

        String sysUser = (String) httpServletRequest.getSession().getAttribute(GlobalConstant.CURR_USER);
        if (sysUser == null) {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter writer = httpServletResponse.getWriter();
            writer.print("{\"resultId\":\"000001\",\"resultType\":\"用户登陆信息为空，请重新登陆！\"}");
            writer.flush();
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
