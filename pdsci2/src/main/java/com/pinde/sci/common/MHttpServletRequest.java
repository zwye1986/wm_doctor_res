package com.pinde.sci.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @ClassName MHttpServletRequest
 * @Deacription 参数特殊字符过滤
 * @Author shengl
 * @Date 2020-06-28 14:51
 * @Version 1.0
 **/
public class MHttpServletRequest extends HttpServletRequestWrapper {
    public MHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        // 返回值之前 先进行过滤
        return XssShieldUtil.stripXss(super.getParameter(XssShieldUtil.stripXss(name)));
    }

    @Override
    public String[] getParameterValues(String name) {
        // 返回值之前 先进行过滤
        String[] values = super.getParameterValues(XssShieldUtil.stripXss(name));
        if(values != null){
            for (int i = 0; i < values.length; i++) {
                values[i] = XssShieldUtil.stripXss(values[i]);
            }
        }
        return values;
    }
}
