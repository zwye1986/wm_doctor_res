package com.pinde.sci.common;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.XmlParse;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统初始化操作
 *
 * @author shijian
 * @create 2014.04.2
 */
public class InitResConfig implements ServletContextListener {

    private final static Logger logger = LoggerFactory.getLogger(InitResConfig.class);

    public static void refresh(ServletContext context) {
        logger.debug("开始刷新内存...");

        //加载住院医师配置
        _loadResCfg(context);

    }

    private static void _loadResCfg(ServletContext context) {
        logger.debug("开始加载 RES FORM DICT...");
        loadResFormDict(context);
        logger.debug("开始加载 RES CONFIG　FILE...");
        InitConfig.resFormRequestUtil = new IrbFormRequestUtil();

        try {
            ResRecTypeEnum resRecTypeEnum = null;
            String formFileName = null;
            XmlParse irbFormXp = null;
            List<Element> productTypeElements = null;
            String jspPath = null;
            List<IrbSingleForm> formList = null;

            String productType = null;
            List<Element> pageElements = null;

            IrbSingleForm singleForm = null;
            List<Element> itemElements = null;
            Map<String, IrbSingleForm> singleFormMap = null;

            //循环表单类型
            for (GeneralEnum<String> temp : EnumUtil.toList(ResRecTypeEnum.class)) {
                resRecTypeEnum = (ResRecTypeEnum) temp;
                if (GlobalConstant.FLAG_N.equals(resRecTypeEnum.getIsForm())) {
                    continue;
                }
                formFileName = resRecTypeEnum.getId(); //登记类型 ID  (如：CaseRegistry...)
                //根据登记类型ID  获了XML文件
                irbFormXp = new XmlParse(SpringUtil.getResource("classpath:" + GlobalConstant.RES_FORM_CONFIG_PATH + "/" + formFileName + ".xml").getFile());

                productTypeElements = irbFormXp.getRootElement().elements();//XML文件根节点下的子节点列表
                jspPath = "";
                formList = null;
                //循环子节点列表
                for (Element productEle : productTypeElements) {
                    productType = productEle.getName();//子节点名
                    //子节点名为jsp的是表单路径
                    if (productType.equals("jsp")) {
                        jspPath = productEle.attributeValue("path");
                        continue;
                    }
                    //当前登记类型表单列表
                    formList = InitConfig.resFormRequestUtil.getFormTypeMap().get(formFileName);
                    if (formList == null) {
                        formList = new ArrayList<IrbSingleForm>();
                    }
                    pageElements = productEle.elements();
                    //赋版本号(key=子节点名即项目名_登记类型，value=版本号)
                    InitConfig.resFormRequestUtil.getVersionMap().put(productType + "_" + formFileName, productEle.attributeValue("ver"));
                    //循环子节点下的page列表
                    for (Element pageEle : pageElements) {
                        singleForm = new IrbSingleForm();
                        singleForm.setProductType(productType);
                        singleForm.setVersion(pageEle.attributeValue("ver"));
                        singleForm.setJspPath(jspPath);
                        itemElements = pageEle.elements();
                        singleForm.setItemList(itemElements);
                        formList.add(singleForm);
                        singleFormMap = InitConfig.resFormRequestUtil.getFormMap().get(formFileName);
                        if (singleFormMap == null) {
                            singleFormMap = new HashMap<String, IrbSingleForm>();
                        }
                        //项目的表单列表 (key=子节点名即项目名_版本号, value=表单)
                        singleFormMap.put(singleForm.getProductType() + "_" + singleForm.getVersion(), singleForm);
                        //登记类型的表单Map列表 (key=登记类型, value=项目的表单列表Map)
                        InitConfig.resFormRequestUtil.getFormMap().put(formFileName, singleFormMap);
                    }
                    //登记类型的表单List列表 (key=登记类型, value=项目的表单列表List)
                    InitConfig.resFormRequestUtil.getFormTypeMap().put(formFileName, formList);
                }
            }
        } catch (Exception e) {
            logger.error("RES CONFIG FILE LOAD FAIL，不能启动系统...", e);
            throw new RuntimeException("RES CONFIG FILE LOAD FAIL，不能启动系统...");
        }
    }

    private static void loadResFormDict(ServletContext context) {
        try {
            XmlParse formXp = new XmlParse(SpringUtil.getResource("classpath:" + GlobalConstant.RES_FORM_CONFIG_PATH + "/formDict.xml").getFile());
            List<Element> formDictElements = formXp.getRootElement().elements();
            List<Map<String, Object>> resFormDictList = new ArrayList<Map<String, Object>>();

            Map<String, Object> dictForm = null;
            String dictId = null;
            String dictDesc = null;
            for (Element dict : formDictElements) {
                dictForm = new HashMap<String, Object>();
                dictId = dict.getName();
                dictDesc = dict.attributeValue("desc");
                dictForm.put("dictId", dictId);
                dictForm.put("dictDesc", dictDesc);
                resFormDictList.add(dictForm);
                List<Element> depts = dict.elements();
                boolean notEmpty = depts!=null && !depts.isEmpty();
                dictForm.put("hasDeptForm",notEmpty);
                if(notEmpty){
                    List<Map<String,Object>> deptList = new ArrayList<Map<String, Object>>();
                    for(Element dept : depts){
                        Map<String,Object> deptMap = new HashMap<String,Object>();
                        String pageFlag = dept.attributeValue("pageFlag").trim();
                        deptMap.put("subPage",pageFlag);
                        String deptCfgName = dept.getText().trim();
                        deptMap.put("subPageName",deptCfgName);
                        deptList.add(deptMap);
                    }
                    dictForm.put("deptForm",deptList);
                }
            }
            context.setAttribute("resFormDictList", resFormDictList);
        } catch (Exception e) {
            logger.error("RES FORM Directory LOAD FAIL，不能启动系统...", e);
            throw new RuntimeException("RES FORM Directory LOAD FAIL，不能启动系统...");
        }
    }

    /**
     * 初始化入口
     */
    public void contextInitialized(ServletContextEvent event) {
        logger.debug("系统初始化...");
        ServletContext context = event.getServletContext();

        //加载住院医师配置
        _loadResCfg(context);

    }

    public void contextDestroyed(ServletContextEvent event) {
    }




}
