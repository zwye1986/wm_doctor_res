package com.pinde.sci.webservice;

import com.pinde.core.util.SpringUtil;
import com.pinde.sci.common.InitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.xml.ws.Endpoint;
import java.util.ResourceBundle;

@Component
public class InitWebService {
    private final static Logger logger = LoggerFactory.getLogger(InitConfig.class);

    public static void loadWebservice(){
        ResourceBundle resource = ResourceBundle.getBundle("pdsci");
        String zseySwitch = resource.getString("webservice.zsey.switch").trim();

        String indexUrl = InitConfig.getSysCfg("sys_index_url");
        logger.debug("webservice.zsey.switch="+zseySwitch+"  indexUrl=" + indexUrl);
        if(indexUrl != null && indexUrl.equals("/inx/zsey")) {
            if(null != zseySwitch && "Y".equals(zseySwitch)) {
                String publishurl = resource.getString("webservice.zsey.url").trim();
                logger.debug("Load ZSEY webservice Begin...");
                Endpoint.publish(publishurl, SpringUtil.getBean(ZseyDeptEmpService.class));
                logger.debug("Load ZSEY webservice End..." + publishurl);
            }
        }
        //上海中山webservice
        String shzsSwitch = resource.getString("webservice.shzs.switch").trim();
        logger.debug("webservice.shzs.switch="+shzsSwitch);
        if(null != shzsSwitch && "Y".equals(shzsSwitch)) {
            String publishurl = resource.getString("webservice.shzs.url").trim();
            logger.debug("webservice.shzs.url="+publishurl);
            logger.debug("Load SHZS webservice Begin...");
            Endpoint.publish(publishurl, SpringUtil.getBean(ShzsArrangeService.class));
            logger.debug("Load SHZS webservice End..." + publishurl);
        }

    }

    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        logger.debug("Load ZSEY webservice Begin...");
//        Endpoint.publish("http://192.168.252.3:9999/deptempservice", SpringUtil.getBean(ZseyDeptEmpService.class));
        Endpoint.publish("http://192.168.252.3:9999/deptempservice", new ZseyDeptEmpService());
        logger.debug("Load ZSEY webservice End...");
    }
}
