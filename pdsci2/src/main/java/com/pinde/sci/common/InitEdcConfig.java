package com.pinde.sci.common;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.XmlParse;
import com.pinde.sci.biz.irb.IIrbInfoBiz;
import com.pinde.sci.enums.gcp.GcpRecTypeEnum;
import com.pinde.sci.enums.irb.IrbRecTypeEnum;
import com.pinde.sci.model.mo.IrbInfo;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

/**
 * 系统初始化操作
 *
 * @author shijian
 * @create 2014.04.2
 */
public class InitEdcConfig implements ServletContextListener {

    private final static Logger logger = LoggerFactory.getLogger(InitEdcConfig.class);

    public static void refresh(ServletContext context) {
        logger.debug("开始刷新内存...");

        //加载伦理配置
        _loadIrbCfg(context);
        //加载GCP配置
        _loadGcpCfg(context);
        //加载伦理委员会
        _loadIrb(context);

    }

    private static void _loadIrbCfg(ServletContext context) {
        logger.debug("BEGIN LOAD EDC CONFIG FILE...");
        InitConfig.formRequestUtil = new IrbFormRequestUtil();
        try {

            for (GeneralEnum<String> temp : EnumUtil.toList(IrbRecTypeEnum.class)) {
                IrbRecTypeEnum irbRecTypeEnum = (IrbRecTypeEnum) temp;
                if (GlobalConstant.FLAG_N.equals(irbRecTypeEnum.getIsForm())) {
                    continue;
                }
                String formFileName = irbRecTypeEnum.getId();
                XmlParse irbFormXp = new XmlParse(SpringUtil.getResource("classpath:" + GlobalConstant.IRB_FORM_CONFIG_PATH + "/" + formFileName + ".xml").getFile());

                List<Element> productTypeElements = irbFormXp.getRootElement().elements();
                String jspPath = "";
                String viewPath = "";
                for (Element productEle : productTypeElements) {
                    if (productEle.getName().equals("jsp")) {
                        jspPath = productEle.attributeValue("path");
                        viewPath = productEle.attributeValue("view");
                        InitConfig.formRequestUtil.getVersionMap().put(formFileName, productEle.attributeValue("ver"));
                    }
                    List<IrbSingleForm> formList = InitConfig.formRequestUtil.getFormTypeMap().get(formFileName);
                    if (formList == null) {
                        formList = new ArrayList<IrbSingleForm>();
                    }
                    List<Element> pageElements = productEle.elements();
                    for (Element pageEle : pageElements) {
                        IrbSingleForm singleForm = new IrbSingleForm();
                        singleForm.setProductType(productEle.getName());
                        singleForm.setCategory(pageEle.attributeValue("categroy"));
                        singleForm.setVersion(pageEle.attributeValue("ver"));
//							if(StringUtil.isNotBlank(jspPath)){
//								singleForm.setJspPath(MessageFormat.format(jspPath,singleForm.getProductType(),singleForm.getCategory(),singleForm.getVersion()));
//							}
                        singleForm.setJspPath(jspPath);
//							if(StringUtil.isNotBlank(viewPath)){
//								singleForm.setViewPath(MessageFormat.format(jspPath,singleForm.getProductType(),singleForm.getCategory(),singleForm.getVersion()));
//							}
                        singleForm.setViewPath(viewPath);
                        List<Element> itemElements = pageEle.elements();
                        singleForm.setItemList(itemElements);
                            /*解析code节点*/
                        Map<String, Map<String, CodeValues>> itemCodeMap = new HashMap<String, Map<String, CodeValues>>();
                        for (Element el : itemElements) {
                            List<Element> codeElements = el.elements("code");
                            if (codeElements != null && !codeElements.isEmpty()) {
                                Map<String, CodeValues> codeMap = new LinkedHashMap<String, CodeValues>();
                                CodeValues values = null;
                                for (Element cel : codeElements) {
                                    values = new CodeValues();
                                    values.setText(cel.getTextTrim());
                                    Attribute newLineAttr = cel.attribute("newLine");
                                    if (newLineAttr != null) {
                                        values.setNewLine(newLineAttr.getValue());
                                    }
                                    Attribute remarkAttr = cel.attribute("remark");
                                    if (remarkAttr != null) {
                                        values.setRemark(remarkAttr.getValue());
                                    }
                                    codeMap.put(cel.attributeValue("value"), values);
                                }
                                itemCodeMap.put(el.attributeValue("name"), codeMap);
                            }
                        }
                        singleForm.setItemCodeMap(itemCodeMap);

                        formList.add(singleForm);
                        //product_yw_1.0
                        Map<String, IrbSingleForm> singleFormMap = InitConfig.formRequestUtil.getFormMap().get(formFileName);
                        if (singleFormMap == null) {
                            singleFormMap = new HashMap<String, IrbSingleForm>();
                        }
                        singleFormMap.put(singleForm.getProductType() + "_" + singleForm.getCategory() + "_" + singleForm.getVersion(), singleForm);
                        InitConfig.formRequestUtil.getFormMap().put(formFileName, singleFormMap);
                    }
                    InitConfig.formRequestUtil.getFormTypeMap().put(formFileName, formList);
                }
            }
        } catch (Exception e) {
            logger.error("EDC CONFIG FILE LOAD FAIL，不能启动系统...", e);
            throw new RuntimeException("EDC CONFIG FILE LOAD FAIL，不能启动系统...");
        }
    }

    private static void _loadGcpCfg(ServletContext context) {
        logger.debug("BEGIN LOAD GCP CONFIG FILE...");
        InitConfig.gcpFormRequestUtil = new IrbFormRequestUtil();
        try {

            for (GeneralEnum<String> temp : EnumUtil.toList(GcpRecTypeEnum.class)) {
                GcpRecTypeEnum gcpRecTypeEnum = (GcpRecTypeEnum) temp;
                if (GlobalConstant.FLAG_N.equals(gcpRecTypeEnum.getIsForm())) {
                    continue;
                }
                String formFileName = gcpRecTypeEnum.getId();
                XmlParse irbFormXp = new XmlParse(SpringUtil.getResource("classpath:" + GlobalConstant.QC_FORM_CONFIG_PATH + "/" + formFileName + ".xml").getFile());

                List<Element> productTypeElements = irbFormXp.getRootElement().elements();
                String jspPath = "";
                String viewPath = "";
                for (Element productEle : productTypeElements) {
                    if (productEle.getName().equals("jsp")) {
                        jspPath = productEle.attributeValue("path");
                        viewPath = productEle.attributeValue("view");
                        InitConfig.gcpFormRequestUtil.getVersionMap().put(formFileName, productEle.attributeValue("ver"));
                    }
                    List<IrbSingleForm> formList = InitConfig.gcpFormRequestUtil.getFormTypeMap().get(formFileName);
                    if (formList == null) {
                        formList = new ArrayList<IrbSingleForm>();
                    }
                    List<Element> pageElements = productEle.elements();
                    for (Element pageEle : pageElements) {
                        IrbSingleForm singleForm = new IrbSingleForm();
                        singleForm.setProductType(productEle.getName());
                        singleForm.setVersion(pageEle.attributeValue("ver"));
                        singleForm.setJspPath(jspPath);
                        singleForm.setViewPath(viewPath);
                        List<Element> itemElements = pageEle.elements();
                        singleForm.setItemList(itemElements);
							/*解析code节点*/
                        Map<String, Map<String, CodeValues>> itemCodeMap = new HashMap<String, Map<String, CodeValues>>();
                        for (Element el : itemElements) {
                            List<Element> codeElements = el.elements("code");
                            if (codeElements != null && !codeElements.isEmpty()) {
                                Map<String, CodeValues> codeMap = new LinkedHashMap<String, CodeValues>();
                                CodeValues values = null;
                                for (Element cel : codeElements) {
                                    values = new CodeValues();
                                    values.setText(cel.getTextTrim());
                                    Attribute newLineAttr = cel.attribute("newLine");
                                    if (newLineAttr != null) {
                                        values.setNewLine(newLineAttr.getValue());
                                    }
                                    Attribute remarkAttr = cel.attribute("remark");
                                    if (remarkAttr != null) {
                                        values.setRemark(remarkAttr.getValue());
                                    }
                                    codeMap.put(cel.attributeValue("value"), values);
                                }
                                itemCodeMap.put(el.attributeValue("name"), codeMap);
                            }
                        }
                        singleForm.setItemCodeMap(itemCodeMap);

                        formList.add(singleForm);
                        //product_1.0
                        Map<String, IrbSingleForm> singleFormMap = InitConfig.gcpFormRequestUtil.getFormMap().get(formFileName);
                        if (singleFormMap == null) {
                            singleFormMap = new HashMap<String, IrbSingleForm>();
                        }
                        singleFormMap.put(singleForm.getProductType() + "_" + singleForm.getVersion(), singleForm);
                        InitConfig.gcpFormRequestUtil.getFormMap().put(formFileName, singleFormMap);
                    }
                    InitConfig.gcpFormRequestUtil.getFormTypeMap().put(formFileName, formList);
                }
            }
        } catch (Exception e) {
            logger.error("GCP CONFIG FILE LOAD FAIL，不能启动系统...", e);
            throw new RuntimeException("GCP CONFIG FILE LOAD FAIL，不能启动系统...");
        }
    }

    private static void _loadIrb(ServletContext context) {
        Map<String, IrbInfo> irbInfoMap = new HashMap<String, IrbInfo>();
        IIrbInfoBiz irbBiz = SpringUtil.getBean(IIrbInfoBiz.class);
        IrbInfo search = new IrbInfo();
        search.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<IrbInfo> irbInfoList = irbBiz.queryInfo(search);
        for (IrbInfo info : irbInfoList) {
            irbInfoMap.put(info.getRecordFlow(), info);
        }
        context.setAttribute("irbInfoMap", irbInfoMap);
        context.setAttribute("irbInfoList", irbInfoList);
        InitConfig.irbInfoMap = irbInfoMap;
    }

    /**
     * 初始化入口
     */
    public void contextInitialized(ServletContextEvent event) {
        logger.debug("系统初始化...");
        ServletContext context = event.getServletContext();

        //加载伦理配置
        _loadIrbCfg(context);
        //加载GCP配置
        _loadGcpCfg(context);
        //加载伦理委员会
        _loadIrb(context);

    }

    public void contextDestroyed(ServletContextEvent event) {
    }


}
