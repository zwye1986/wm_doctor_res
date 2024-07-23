package com.pinde.core.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by machengshuang on 2017/11/21.
 */
public class ClientIPUtils {
    private static Logger logger = LoggerFactory.getLogger(ClientIPUtils.class);

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        //String ip = request.getHeader("X-real-ip");
//        logger.debug("x-forwarded-for = {}", ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
//            logger.debug("Proxy-Client-IP = {}", ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
//            logger.debug("WL-Proxy-Client-IP = {}", ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
//            logger.debug("RemoteAddr-IP = {}", ip);
        }
        if (StringUtils.isNotBlank(ip)) {
            ip = ip.split(",")[0];
        }
        return ip;
    }
}