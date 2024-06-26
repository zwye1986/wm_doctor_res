package com.pinde.sci.thread;

import com.pinde.sci.ctrl.sys.DictController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class RefreshMemThread extends Thread {

    private static Logger logger= LoggerFactory.getLogger(RefreshMemThread.class);

    private String host;

    private String port;

    HttpServletRequest request;

    @Override
    public void run(){
        logger.debug("RefreshMemThread host="+host+" port="+port+" begin========");
        String url = "http://"+host+":"+port+""+request.getContextPath()+"/sys/dict/doRefresh";
        DictController.doRemoteRefresh(url);
        logger.debug("RefreshMemThread host="+host+" port="+port+" end========");
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
