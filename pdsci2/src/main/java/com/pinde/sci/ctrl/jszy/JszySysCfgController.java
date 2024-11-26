package com.pinde.sci.ctrl.jszy;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.SysCfg;
import com.pinde.sci.thread.RefreshMemThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2018/2/6.
 * 用于江苏西医的系统配置功能和系统配置日志功能
 */
@Controller
@RequestMapping("/jszy/sysCfg")
public class JszySysCfgController extends GeneralController {
    private static Logger logger= LoggerFactory.getLogger(JszySysCfgController.class);

    @Value("#{configProperties['remote.refresh.port']}")
    private String remoteRefreshPort;

    @Value("#{configProperties['remote.refresh.host']}")
    private String remoteRefreshHost;


    @RequestMapping(value = {"/parameterConfigMain"})
    public String main() {
        return "jszy/sysCfg/main";
    }

    @RequestMapping(value = "/edit", method = {RequestMethod.GET})
    public ModelAndView edit() {
        String wsId = (String) getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
        ModelAndView mav = new ModelAndView("jszy/sysCfg/jszyCfg");
        SysCfg cfg = new SysCfg();
        cfg.setWsId(wsId);
        List<SysCfg> sysCfgList = cfgBiz.search(cfg);
        Map<String, String> sysCfgMap = new HashMap<String, String>();
        Map<String, String> sysCfgDescMap = new HashMap<String, String>();
        for (SysCfg sysCfg : sysCfgList) {
            if (StringUtil.isNotBlank(sysCfg.getCfgDesc())) {
                sysCfgDescMap.put(sysCfg.getCfgCode(), sysCfg.getCfgDesc());
            }
            sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgValue());
            if (StringUtil.isNotBlank(sysCfg.getCfgBigValue())) {
                sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgBigValue());
            }
        }
        mav.addObject("sysCfgDescMap", sysCfgDescMap);
        return mav.addObject("sysCfgMap", sysCfgMap);
    }

    @RequestMapping(value="/save",method=RequestMethod.POST)
    @ResponseBody
    public String save(HttpServletRequest request){
        String wsId = (String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
        String [] cfgCodes =request.getParameterValues("cfgCode");
        if(cfgCodes!=null){
            List<SysCfg> sysCfgList = new ArrayList<SysCfg>();
            for(String cfgCode : cfgCodes){
                String sysCfgValue=request.getParameter(cfgCode);
                String sysCfgDesc=request.getParameter(cfgCode+"_desc");
                SysCfg cfg=new SysCfg();
                cfg.setCfgCode(cfgCode);
                cfg.setCfgValue(sysCfgValue);
                cfg.setCfgDesc(sysCfgDesc);
                String reqWsId = request.getParameter(cfgCode+"_ws_id");
                if(StringUtil.isBlank(reqWsId)){
                    reqWsId = wsId;
                }
                cfg.setWsId(reqWsId);
                cfg.setWsName(InitConfig.getWorkStationName(cfg.getWsId()));
                if(StringUtil.isBlank(cfg.getWsName())){
                    cfg.setWsName("全局公用配置");
                }

                String sysCfgBigValue=request.getParameter(cfgCode+"_big_value");
                cfg.setCfgBigValue(sysCfgBigValue);
                cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                sysCfgList.add(cfg);
            }
            cfgBiz.save(sysCfgList);
        }

        //刷新内存
        RefreshMem(request);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return GlobalConstant.SAVE_SUCCESSED;
    }

    private void RefreshMem(HttpServletRequest request){
        if(StringUtil.isNotBlank(remoteRefreshPort)){
            String[] ports = remoteRefreshPort.split(",");
            remoteRefreshHost = StringUtil.isBlank(remoteRefreshHost) ? "127.0.0.1" : remoteRefreshHost;
            for(String port : ports){
                RefreshMemThread thread = new RefreshMemThread();
                thread.setHost(remoteRefreshHost);
                thread.setPort(port);
                thread.setRequest(request);
                thread.start();
            }
        }
    }
}
