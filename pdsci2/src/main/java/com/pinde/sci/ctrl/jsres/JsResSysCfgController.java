package com.pinde.sci.ctrl.jsres;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.SysCfg;
import com.pinde.sci.model.mo.SysCfgLogWithBLOBs;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2018/2/6.
 * 用于江苏西医的系统配置功能和系统配置日志功能
 */
@Controller
@RequestMapping("/jsres/sysCfg")
public class JsResSysCfgController extends GeneralController {
    private static Logger logger= LoggerFactory.getLogger(JsResSysCfgController.class);

    @Value("#{configProperties['remote.cluster.customer']}")
    private String remoteClusterCustomer;

    @Value("#{configProperties['remote.refresh.hosts']}")
    private String remoteRefreshHosts;

    @Value("#{configProperties['remote.refresh.port']}")
    private String remoteRefreshPort;

    @Value("#{configProperties['remote.refresh.host']}")
    private String remoteRefreshHost;


    @RequestMapping(value = {"/parameterConfigMain"})
    public String main() {
        return "jsres/sysCfg/main";
    }

    @RequestMapping(value = "/edit", method = {RequestMethod.GET})
    public ModelAndView edit() {
        String wsId = (String) getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
        ModelAndView mav = new ModelAndView("jsres/sysCfg/jsresCfg");
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
            String nowTime= DateUtil.getCurrDateTime2();
            List<SysCfg> sysCfgList = new ArrayList<SysCfg>();
            List<SysCfgLogWithBLOBs> sysCfgLogList = new ArrayList<SysCfgLogWithBLOBs>();
            for(String cfgCode : cfgCodes){
                SysCfg oldCfg=cfgBiz.read(cfgCode);
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
                //记录日志
                if(oldCfg==null||!StringUtil.isEquals(oldCfg.getCfgValue(),cfg.getCfgValue())
                        ||!StringUtil.isEquals(oldCfg.getCfgBigValue(),cfg.getCfgBigValue())) {
                    SysCfgLogWithBLOBs cfgLog = new SysCfgLogWithBLOBs();
                    cfgLog.setCfgCode(cfgCode);
                    cfgLog.setCfgValue(sysCfgValue);
                    cfgLog.setCfgDesc(sysCfgDesc);
                    if (StringUtil.isBlank(reqWsId)) {
                        reqWsId = wsId;
                    }
                    cfgLog.setWsId(reqWsId);
                    cfgLog.setWsName(InitConfig.getWorkStationName(cfgLog.getWsId()));
                    if (StringUtil.isBlank(cfgLog.getWsName())) {
                        cfgLog.setWsName("全局公用配置");
                    }
                    cfgLog.setCfgBigValue(sysCfgBigValue);
                    if (oldCfg != null) {
                        cfgLog.setCfgOldValue(oldCfg.getCfgValue());
                        cfgLog.setCfgOldBigValue(oldCfg.getCfgBigValue());
                    }
                    cfgLog.setSaveTime(nowTime);
                    cfgLog.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                    sysCfgLogList.add(cfgLog);
                }
            }
            cfgBiz.save(sysCfgList);
            cfgBiz.saveLog(sysCfgLogList);
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

    private void RefreshMem(HttpServletRequest request) {

        if (StringUtil.isNotBlank(remoteClusterCustomer)) {
            String indexUrl = InitConfig.getSysCfg("sys_index_url");
            String[] custs = remoteClusterCustomer.split(",");
            boolean isJQ = false;

            for (String cust : custs) {
                if (StringUtil.isEquals(cust, indexUrl)) {
                    isJQ = true;
                    break;
                }
            }

            //判断当前系统首页，是否为启用了集群的系统，是则进入集群刷新页面（需配置）
            if (isJQ) {
                if (StringUtil.isNotBlank(remoteRefreshHosts)) {
                    String[] hosts = remoteRefreshHosts.split(",");
                    for (String hostPort : hosts) {
                        String url = "http://" + hostPort + "" + request.getContextPath() + "/sys/dict/doRefresh";
                        doRemoteRefresh(url);
                    }
                }
            } else {
                if (StringUtil.isNotBlank(remoteRefreshPort)) {
                    String[] ports = remoteRefreshPort.split(",");
                    remoteRefreshHost = StringUtil.isBlank(remoteRefreshHost) ? "127.0.0.1" : remoteRefreshHost;
                    for (String port : ports) {
                        String url = "http://" + remoteRefreshHost + ":" + port + "" + request.getContextPath() + "/sys/dict/doRefresh";
                        doRemoteRefresh(url);
                    }
                }
            }
        } else if (StringUtil.isNotBlank(remoteRefreshPort)) {
            String[] ports = remoteRefreshPort.split(",");
            remoteRefreshHost = StringUtil.isBlank(remoteRefreshHost) ? "127.0.0.1" : remoteRefreshHost;
            for (String port : ports) {
                String url = "http://" + remoteRefreshHost + ":" + port + "" + request.getContextPath() + "/sys/dict/doRefresh";
                doRemoteRefresh(url);
            }
        }
    }
    public static String doRemoteRefresh(String url) {
        StringBuilder sb = new StringBuilder();
        InputStream ins = null;
        try {
            HttpClient client = new HttpClient();
            GetMethod method = new GetMethod(url);
            int statusCode = client.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK) {
                ins = method.getResponseBodyAsStream();
                byte[] b = new byte[1024];
                int r_len = 0;
                while ((r_len = ins.read(b)) > 0) {
                    sb.append(new String(b, 0, r_len, method
                            .getResponseCharSet()));
                }
                logger.debug("doRemoteRefresh @ "+url+" statusCode:"+statusCode);
                return sb.toString();
            }else{
                logger.debug("doRemoteRefresh @ "+url+" statusCode:"+statusCode);
                return "刷新远程失败！";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "刷新远程失败！";
        }
    }
}
