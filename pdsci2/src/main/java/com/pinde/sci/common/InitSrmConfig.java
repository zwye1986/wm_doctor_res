package com.pinde.sci.common;

import com.pinde.core.jspform.PageGroupParse;
import com.pinde.core.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 系统初始化操作
 *
 * @author shijian
 * @create 2014.04.2
 */
public class InitSrmConfig implements ServletContextListener {

    private final static Logger logger = LoggerFactory.getLogger(InitSrmConfig.class);

    public static void refresh(ServletContext context) {
        logger.debug("开始刷新内存...");

        //加载科研配置

    }

    private static void _loadSrmCfg(ServletContext context) {
        logger.debug("开始加载科研配置文件...");
        try {
            ProjPageCfgParse configMapParse = new ProjPageCfgParse(SpringUtil.getResource("classpath:ProjPageCfg.xml").getFile());
            InitConfig.configMap = configMapParse.parseProductConfig();

            PageGroupParse projInfoParse = new PageGroupParse(SpringUtil.getResource("classpath:" + GlobalConstant.SRM_FORM_CONFIG_PATH + "/ProjInfoPage.xml").getFile());
            InitConfig.projInfoPageMap = projInfoParse.parse();

            PageGroupParse projApplyParse = new PageGroupParse(SpringUtil.getResource("classpath:" + GlobalConstant.SRM_FORM_CONFIG_PATH + "/ProjApplyPage.xml").getFile());
            InitConfig.projApplyPageMap = projApplyParse.parse();

            PageGroupParse projSetUpParse = new PageGroupParse(SpringUtil.getResource("classpath:" + GlobalConstant.SRM_FORM_CONFIG_PATH + "/ProjSetUpPage.xml").getFile());
            InitConfig.projSetUpPageMap = projSetUpParse.parse();

            PageGroupParse projContracParse = new PageGroupParse(SpringUtil.getResource("classpath:" + GlobalConstant.SRM_FORM_CONFIG_PATH + "/ProjContractPage.xml").getFile());
            InitConfig.projContractPageMap = projContracParse.parse();

            PageGroupParse projSchduleParse = new PageGroupParse(SpringUtil.getResource("classpath:" + GlobalConstant.SRM_FORM_CONFIG_PATH + "/ProjSchedulePage.xml").getFile());
            InitConfig.projSchdulePageMap = projSchduleParse.parse();

            PageGroupParse projCompleteParse = new PageGroupParse(SpringUtil.getResource("classpath:" + GlobalConstant.SRM_FORM_CONFIG_PATH + "/ProjCompletePage.xml").getFile());
            InitConfig.projCompletePageMap = projCompleteParse.parse();

            PageGroupParse projDelayParse = new PageGroupParse(SpringUtil.getResource("classpath:" + GlobalConstant.SRM_FORM_CONFIG_PATH + "/ProjDelayPage.xml").getFile());
            InitConfig.projDelayPageMap = projDelayParse.parse();

            PageGroupParse projChangeParse = new PageGroupParse(SpringUtil.getResource("classpath:" + GlobalConstant.SRM_FORM_CONFIG_PATH + "/ProjChangePage.xml").getFile());
            InitConfig.projChangePageMap = projChangeParse.parse();

            PageGroupParse projTerminateParse = new PageGroupParse(SpringUtil.getResource("classpath:" + GlobalConstant.SRM_FORM_CONFIG_PATH + "/ProjTerminatePage.xml").getFile());
            InitConfig.projTerminatePageMap = projTerminateParse.parse();

            PageGroupParse projInfoAidParse = new PageGroupParse(SpringUtil.getResource("classpath:" + GlobalConstant.SRM_FORM_CONFIG_PATH + "/ProjAidPage.xml").getFile());
            InitConfig.projAidPageMap = projInfoAidParse.parse();
        } catch (Exception e) {
            logger.error("科研配置加载失败，不能启动系统...", e);
            throw new RuntimeException("科研配置加载失败，不能启动系统...");
        }
    }

    /**
     * 初始化入口
     */
    public void contextInitialized(ServletContextEvent event) {
        logger.debug("系统初始化...");
        ServletContext context = event.getServletContext();
        //加载科研配置
        _loadSrmCfg(context);
    }

    public void contextDestroyed(ServletContextEvent event) {
    }






}
