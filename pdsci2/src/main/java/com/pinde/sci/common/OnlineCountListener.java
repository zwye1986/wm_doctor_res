package com.pinde.sci.common;

import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Author:night_du
 * @Date:2022/3/10 10:47
 */
public class OnlineCountListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se) {
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        Integer onlineCountNum = (Integer) context.getAttribute("onlineCountNum");
        if (onlineCountNum == null || onlineCountNum <= 0) {
            onlineCountNum = 0;
        } else {
            onlineCountNum--;
        }
        context.setAttribute("onlineCountNum", onlineCountNum);
    }
}
