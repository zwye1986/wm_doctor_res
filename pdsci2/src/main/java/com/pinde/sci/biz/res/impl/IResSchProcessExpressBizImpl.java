package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.enums.GlobalRecTypeEnum;
import com.pinde.core.common.enums.JszyTCMPracticEnum;
import com.pinde.core.common.enums.RecStatusEnum;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResRecBiz;
import com.pinde.sci.biz.res.IResSchProcessExpressBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.IrbSingleForm;
import com.pinde.sci.common.util.JspFormUtil;
import com.pinde.sci.dao.base.ResDoctorSchProcessMapper;
import com.pinde.sci.dao.base.ResSchProcessExpressMapper;
import com.pinde.sci.dao.res.ResRecExtMapper;
import com.pinde.sci.model.mo.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
//@Transactional(rollbackFor=Exception.class)
public class IResSchProcessExpressBizImpl implements IResSchProcessExpressBiz {

    @Autowired
    ResSchProcessExpressMapper expressMapper;
    @Autowired
    ResRecExtMapper resRecExtMapper;
    @Autowired
    ResDoctorSchProcessMapper resDoctorProcessMapper;
    @Autowired
    private IResDoctorProcessBiz processBiz;

    @Autowired
    private IResDoctorBiz resDoctorBiz;

    @Autowired
    private ISchDeptBiz schDeptBiz;

    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private ISchRotationDeptBiz schRotationDeptBiz;

    @Override
    public List<ResSchProcessExpress> searchRecByProcessWithBLOBs(List<String> recTypeIds,String processFlow){
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecTypeIdIn(recTypeIds)
                .andProcessFlowEqualTo(processFlow);
        example.setOrderByClause("OPER_TIME");
        return expressMapper.selectByExampleWithBLOBs(example);
    }
    @Override
    public List<ResSchProcessExpress> searchByUserFlowAndTypeId(String operUserFlow,
                                                  String recTypeId) {
        ResSchProcessExpressExample example=new ResSchProcessExpressExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
                .andOperUserFlowEqualTo(operUserFlow);
        return expressMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<ResSchProcessExpress> searchRecByProcess(String processFlow,String doctorFlow) {
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
        example.setOrderByClause("OPER_TIME");
        return expressMapper.selectByExample(example);
    }


    @Override
    public List<ResSchProcessExpress> searchResRecWithBLOBsByRotationDeptFlow(String recTypeId,
                                                                String recordFlow, String operUserFlow) {
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andRecTypeIdEqualTo(recTypeId)
                .andSchRotationDeptFlowEqualTo(recordFlow)
                .andOperUserFlowEqualTo(operUserFlow);
        return expressMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int editAndOut(ResSchProcessExpress rec,ResDoctorSchProcess process){
        if(process!=null){
            resDoctorProcessMapper.updateByPrimaryKeySelective(process);
        }
        return edit(rec);
    }

    @Override
    public List<ResSchProcessExpress> searchAfterAuditRec(ResDoctorSchProcess process, SysUser user, List<String> recTypeIds, Map<String, String> roleFlagMap) {
        return resRecExtMapper.searchAfterAuditRecNew(process,user,recTypeIds,roleFlagMap);
    }
    @Override
    public List<ResSchProcessExpress> searchByProcessFlowAndRecTypeIdClob(String processFlow,String recTypeId){
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        if(StringUtil.isNotBlank(processFlow)&&StringUtil.isNotBlank(recTypeId)){
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);
        }
        return expressMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<ResSchProcessExpress> searchRecByProcess(String recTypeId,String rotationDeptFlow,String operUserFlow){
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
                .andSchRotationDeptFlowEqualTo(rotationDeptFlow).andOperUserFlowEqualTo(operUserFlow);
        example.setOrderByClause("OPER_TIME");
        return expressMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public ResSchProcessExpress readResExpress(String recFlow) {
        ResSchProcessExpress rec = null;
        if(StringUtil.isNotBlank(recFlow)){
            rec = expressMapper.selectByPrimaryKey(recFlow);
        }
        return rec;
    }

    @Override
    public List<ResSchProcessExpress> searchFinishRec(List<String> recTypeIds,String operUserFlow){
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecTypeIdIn(recTypeIds)
                .andOperUserFlowEqualTo(operUserFlow);//.andAuditStatusIdEqualTo(RecStatusEnum.TeacherAuditY.getId());
        example.setOrderByClause("OPER_TIME");
        return expressMapper.selectByExample(example);
    }

    @Override
    public List<ResSchProcessExpress> searchByRecAndProcess(String recTypeId, String schDeptFlow, String operUserFlow, String processFlow) {
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
                .andSchDeptFlowEqualTo(schDeptFlow).andOperUserFlowEqualTo(operUserFlow).andProcessFlowEqualTo(processFlow);
        example.setOrderByClause("OPER_TIME");
        return expressMapper.selectByExample(example);
    }

    @Override
    public List<ResSchProcessExpress> searchByRecAndProcess(String recTypeId, String processFlow) {
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
                .andProcessFlowEqualTo(processFlow);
        example.setOrderByClause("OPER_TIME");
        return expressMapper.selectByExampleWithBLOBs(example);
    }

    //读取一条登记信息
    @Override
    public ResSchProcessExpress getExpressByRecTypeNoStatus(String processFlow, String recTypeId){
        if(StringUtil.isNotBlank(processFlow) && StringUtil.isNotBlank(recTypeId)){
            ResSchProcessExpressExample example = new ResSchProcessExpressExample();
            example.createCriteria().andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);
            List<ResSchProcessExpress> recs = expressMapper.selectByExampleWithBLOBs(example);
            if(recs!=null && !recs.isEmpty()){
                return recs.get(0);
            }
        }
        return null;
    }


    private String replaceNodeValue(String content,String nodeName,String value,String itemId){
        String resultContent = null;
        try {
            Document doc = DocumentHelper.parseText(content);
            Element rootEle = doc.getRootElement();
            Element nade = rootEle.element(nodeName);
            if(nade!=null){
                if (!com.pinde.core.common.GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(itemId)) {
                    nade.addAttribute("id",itemId);
                }
                nade.setText(value);
            }
            resultContent = doc.asXML();
        } catch (DocumentException e) {
            logger.error("", e);
        }
        return resultContent;
    }

    /**
     * 解析request对象
     * 如果该对象中有描述文件的就上传 ， 返回的文件流水号重新添加到map中
     * @param request
     * @return
     */
    public static Map<String , String[]> getParameterMap(HttpServletRequest request,String fromName,String paramName){

        String paramFileName=paramName+"_FileName";
        boolean isMultipart = JspFormUtil.isMultipart(request);
        if(isMultipart){
            Map<String , String[]> dataMap = new HashMap<String, String[]>();
            try {
                //转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                List<MultipartFile> files=multiRequest.getFiles(paramName);
                if(files!=null&&files.size()>0) {
                    for(MultipartFile file:files)
                    {
                        if (!file.isEmpty()) {
                            String fileName = file.getOriginalFilename();
                            String fileFlow ="";
                            if (fileName != null) {
                                fileFlow = PkUtil.getUUID();
                                PubFile pubFile = new PubFile();
                                pubFile.setFileFlow(fileFlow);
                                pubFile.setFileName(fileName);
                                pubFile.setFileRemark(paramName);
                                pubFile.setFileSuffix("");
                                pubFile.setFileUpType("1");
                                String dateString = DateUtil.getCurrDate2();
                                String filePath = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator+fromName+File.separator+dateString;
                                pubFile.setCreateTime(DateUtil.getCurrentTime());
                                File fileDir = new File(filePath);
                                if(!fileDir.exists()){
                                    fileDir.mkdirs();
                                }
                                String saveFileName = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
                                File newFile = new File(fileDir,saveFileName);
                                file.transferTo(newFile);
                                String uploadFile = File.separator+fromName+File.separator+dateString+File.separator+saveFileName;
                                pubFile.setFilePath(uploadFile);
                                pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                                GeneralMethod.setRecordInfo(pubFile, false);
                                IFileBiz fileBiz = SpringUtil.getBean(IFileBiz.class);
                                fileBiz.addFile(pubFile);
                            } else {
                                fileFlow="";
                                fileName="";
                            }
                            if(StringUtil.isNotBlank(fileFlow)) {
                                if (!dataMap.containsKey(paramName)) {
                                    dataMap.put(paramName, new String[]{fileFlow});
                                } else {
                                    String[] values = dataMap.get(paramName);
                                    List<String> valueList = new ArrayList<String>();
                                    for (String temp : values) {
                                        valueList.add(temp);
                                    }
                                    valueList.add(fileFlow);
                                    String[] newValues = new String[valueList.size()];
                                    dataMap.put(paramName, valueList.toArray(newValues));
                                }
                            }
                            if(StringUtil.isNotBlank(fileName)) {
                                if (!dataMap.containsKey(paramFileName)) {
                                    dataMap.put(paramFileName, new String[]{fileName});
                                } else {
                                    String[] values = dataMap.get(paramFileName);
                                    List<String> valueList = new ArrayList<String>();
                                    for (String temp : values) {
                                        valueList.add(temp);
                                    }
                                    valueList.add(fileName);
                                    String[] newValues = new String[valueList.size()];
                                    dataMap.put(paramFileName, valueList.toArray(newValues));
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                logger.error("", e);
                throw new RuntimeException(e);
            }
            return dataMap;
        }else{
            Map<String , String[]> dataMap = request.getParameterMap();
            return dataMap;
        }
    }

    //为rootEle组织内容
    private Element getContentEle(List<Element> list,HttpServletRequest req,Element rootEle){
        if(list !=null && list.size()>0 && rootEle!=null){
            for(Element itemEle : list){
                String multiple = itemEle.attributeValue("multiple");
                //xml中的节点是否是文件
                String isFile = itemEle.attributeValue("isFile");
                boolean isMultipart = JspFormUtil.isMultipart(req);
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isFile) && isMultipart)
                {
                    String name = itemEle.attributeValue("name");
                    Map<String , String[]> dataMap=getParameterMap(req,rootEle.getName(),name);
                    Element element = DocumentHelper.createElement(name);
                    Element elementFlow = DocumentHelper.createElement(name+"_Flow");
                    Element elementName = DocumentHelper.createElement(name+"_FileName");
                    String []flows=dataMap.get(name);
                    String flow="";
                    if (flows != null && flows.length > 0) {
                        flow = StringUtils.join(flows, ",");
                    }
                    elementFlow.setText(flow);
                    rootEle.add(elementFlow);
                    String []names=dataMap.get(name+"_FileName");
                    String fn="";
                    if (names != null && names.length > 0) {
                        fn = StringUtils.join(names, ",");
                    }
                    element.setText(fn);
                    element.addAttribute("flow",flow);
                    rootEle.add(element);
                }else {
                    if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(multiple)) {
                        String name = itemEle.attributeValue("name");

                        String isSelect = itemEle.attributeValue("select");

                        String value = "";

                        Element element = DocumentHelper.createElement(name);

                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isSelect)) {
                            String[] values = req.getParameterValues(name);

                            if (values != null && values.length > 0) {
                                value = StringUtils.join(values, ",");
                            }

                            element.addAttribute("id", value);

                            String[] valueNames = req.getParameterValues(name + "_name");

                            if (valueNames != null && valueNames.length > 0) {
                                value = StringUtils.join(valueNames, ",");
                            }
                        } else {
                            value = req.getParameter(name);
                        }

                        if (StringUtil.isNotBlank(value)) {
                            element.setText(value);
                        }
                        rootEle.add(element);
                    } else {
                        String[] values = req.getParameterValues(itemEle.attributeValue("name"));
                        Element element = DocumentHelper.createElement(itemEle.attributeValue("name"));
                        if (values != null && values.length > 0) {
                            for (String value : values) {
                                Element valueEle = DocumentHelper.createElement("value");
                                if (StringUtil.isNotBlank(value)) {
                                    valueEle.setText(value);
                                }
                                element.add(valueEle);
                            }
                        }
                        rootEle.add(element);
                    }
                }
            }
        }
        return rootEle;
    }

    /**
     * 获取匹配的itemGroup name的item索引
     *
     * @param pattern
     * @param datasMap
     * @return
     */
    private static int getMatchDataIndex(String pattern, Map<String, String[]> datasMap) {
        int count = 0;
        Set<String> keys = datasMap.keySet();
        for (String key : keys) {
            if (key.startsWith(pattern + "_")) {
                String[] values = datasMap.get(key);
                count = values.length;
                break;
            }
        }
        return count;
    }

    /**
     * 创建item节点 , 并根据索引设置值
     *
     * @param itemList
     * @param ele
     * @param datasMap
     * @param index
     */
    private static void iteratorItem(List<Element> itemList, Element ele, Map<String, String[]> datasMap, int index) {
        if (itemList != null && itemList.size() > 0) {
            for (Element item : itemList) {
                Element itemEle = ele.addElement("item");
                String name = item.attributeValue("name");
                itemEle.addAttribute("name", name);
                String remark = item.attributeValue("remark");
                itemEle.addAttribute("remark", remark);
                String[] values = datasMap.get(name);
                String multiple = item.attributeValue("multiple");
                itemEle.addAttribute("multiple", multiple);
                Element valEle = itemEle.addElement("value");
                if (values != null && values.length >= index + 1) {
                    valEle.setText(values[index]);
                }
            }
        }
    }

    private String getRecContent(String formName,IrbSingleForm singleForm,HttpServletRequest req){
        Map<String , String[]> dataMap = JspFormUtil.getParameterMap(req);
        Element rootEle = DocumentHelper.createElement(formName);

        List<Element> itemList = singleForm.getItemList();
        if(itemList !=null && itemList.size()>0){
            for(Element itemEle : itemList){
                if ("itemGroup".equals(itemEle.getName())) {	//itemGroup
                    String igName = itemEle.attributeValue("name");
                    String modelStyle = itemEle.attributeValue("modelStyle");
                    if("add".equals(modelStyle)){//平滑式
                        int count = getMatchDataIndex(igName, dataMap);
                        for (int i = 0; i < count; i++) {
                            Element itemGroupEle = rootEle.addElement("itemGroup");
                            itemGroupEle.addAttribute("name", igName);

                            String remark = itemEle.attributeValue("remark");

                            if(StringUtil.isNotBlank(remark)){
                                itemGroupEle.addAttribute("remark", remark);
                            }
                            List<Element> igItemList = itemEle.selectNodes("item");
                            iteratorItem(igItemList , itemGroupEle, dataMap , i);
                        }
                    }
                } else {//item
                    String multiple = itemEle.attributeValue("multiple");
                    if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(multiple) || StringUtil.isBlank(multiple)) {
                        String name = itemEle.attributeValue("name");
                        String value = "";
                        if (dataMap.get(name) != null && dataMap.get(name).length > 0) {
                            value = dataMap.get(name)[0];
                        }
                        Element element = rootEle.addElement(itemEle.attributeValue("name"));

                        if (StringUtil.isNotBlank(value)) {
                            element.setText(value);
                        }
                    } else {
                        String[] values = dataMap.get(itemEle.attributeValue("name"));
                        Element element = rootEle.addElement(itemEle.attributeValue("name"));
                        if (values != null && values.length > 0) {
                            for (String value : values) {
                                Element valueEle = element.addElement("value");
                                if (StringUtil.isNotBlank(value)) {
                                    valueEle.setText(value);
                                }
                            }
                        }
                    }
                }
            }
        }

        return rootEle.asXML();
    }

    private String getRecContent(String formName,List<Element> list,HttpServletRequest req,String recContent){
        Element rootEle = null;
        if(StringUtil.isNotBlank(recContent)){
            try {
                rootEle = DocumentHelper.parseText(recContent).getRootElement();
            } catch (DocumentException e) {
                logger.error("", e);
            }
        }else{
            rootEle = DocumentHelper.createElement(formName);
        }

        if(list!=null && list.size()>0){
            for(Element e : list){
                String tagName = e.getName();
                String recordFlow = req.getParameter("recordFlow");

                if("itemGroup".equals(tagName)){
                    if(recordFlow!=null){
                        String delFlag = req.getParameter("delFlag");
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(delFlag)) {
                            Node delNode = rootEle.selectSingleNode("itemGroup[@recordFlow='"+recordFlow+"']");
                            delNode.detach();
                        }else{
                            Element group = null;
                            if(StringUtil.isNotBlank(recordFlow)){
                                group = (Element)rootEle.selectSingleNode("itemGroup[@recordFlow='"+recordFlow+"']");
                                group.setText("");
                                group = getContentEle(e.elements(),req,group);
                            }else{
                                group = DocumentHelper.createElement("itemGroup");
                                group.addAttribute("recordFlow",PkUtil.getUUID());
                                group.addAttribute("name",e.attributeValue("name"));
//							group.addAttribute("title",e.attributeValue("remark"));
                                group = getContentEle(e.elements(),req,group);
                                rootEle.add(group);
                            }

                        }
                    }
                }else{
                    if(recordFlow==null){
                        String nodeName = e.attributeValue("name");
                        Element itemEle = (Element)rootEle.selectSingleNode(nodeName);
                        if(itemEle!=null){
                            itemEle.detach();
                        }
                        String multiple = e.attributeValue("multiple");
                        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(multiple)) {
                            String value = req.getParameter(nodeName);
                            Element element = DocumentHelper.createElement(nodeName);
                            if (StringUtil.isNotBlank(value)) {
                                element.setText(value);
                            }
                            rootEle.add(element);
                        }else {
                            String[] values = req.getParameterValues(nodeName);
                            Element element = DocumentHelper.createElement(nodeName);
                            if(values != null && values.length > 0) {
                                for (String value : values) {
                                    Element valueEle = DocumentHelper.createElement("value");
                                    if (StringUtil.isNotBlank(value)) {
                                        valueEle.setText(value);
                                    }
                                    element.add(valueEle);
                                }
                            }
                            rootEle.add(element);
                        }
                    }
                }
            }
        }
        return rootEle.asXML();
    }

    @Override
    public String getRecContent(String formName,List<Element> list,HttpServletRequest req) {
        Element rootEle = DocumentHelper.createElement(formName);
        rootEle = getContentEle(list,req,rootEle);
        return rootEle.asXML();
    }

    @Override
    public List<ResSchProcessExpress> searchResRecExpressWithBLOBs(String recTypeId,
                                                                   String processFlow) {
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andRecTypeIdEqualTo(recTypeId)
                .andProcessFlowEqualTo(processFlow);
        example.setOrderByClause("AUDIT_STATUS_ID");
        return expressMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int edit(ResSchProcessExpress express) {
        if(express!=null){
            if(StringUtil.isNotBlank(express.getRecFlow())){//修改
                GeneralMethod.setRecordInfo(express, false);
                return this.expressMapper.updateByPrimaryKeySelective(express);
            }else{//新增
                express.setRecFlow(PkUtil.getUUID());
                if (!com.pinde.core.common.enums.ResRecTypeEnum.AnnualTrainForm.getId().equals(express.getRecTypeId())) {//培训年度
                    express.setOperTime(DateUtil.getCurrDateTime());
                }
                GeneralMethod.setRecordInfo(express, true);
                return this.expressMapper.insertSelective(express);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }
    /**
     *  保存表单
     * @param formFileName 登记类型
     * @param recFlow 登记表流水号
     * @param schDeptFlow 轮转科室流水号
     * @param rotationFlow 轮转方案流水号
     * @param req
     * @param orgFlow
     * @param medicineTypeId
     * @return
     */
    @Override
    public int saveRegistryForm(String formFileName, String recFlow, String schDeptFlow, String rotationFlow, HttpServletRequest req, String orgFlow, String medicineTypeId){
        //当前操作的用户
        SysUser currUser = GlobalContext.getCurrentUser();
        //当前时间
        String currTime = DateUtil.getCurrDateTime();

        if(StringUtil.isNotBlank(formFileName)){
            String productType = StringUtil.defaultIfEmpty(InitConfig.getSysCfg("res_form_category_"+rotationFlow),InitConfig.getSysCfg("res_form_category"));//与对应开关保持一致
            productType = StringUtil.defaultIfEmpty(productType, com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT);

            String currVer = InitConfig.resFormRequestUtil.getVersionMap().get(productType+"_"+formFileName);
            if(StringUtil.isBlank(currVer)){
                currVer = InitConfig.resFormRequestUtil.getVersionMap().get(com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT + "_" + formFileName);
            }
            if(StringUtil.isBlank(currVer)){
                currVer = com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT_VER;
            }
            String type="";
            //获取过程flow
            String processFlow = req.getParameter("processFlow");
            //读取过程数据
            ResDoctorSchProcess process = processBiz.read(processFlow);
            if(process!=null) {
                SchRotationDept dept=schRotationDeptBiz.readStandardRotationDept(process.getSchResultFlow());
                if(dept!=null)
                {
                    if(JszyTCMPracticEnum.BasicPractice.getId().equals(dept.getPracticOrTheory()))
                    {
                        type="B";
                    }
                }
            }
            Map<String,IrbSingleForm> singleFormMap = InitConfig.resFormRequestUtil.getFormMap().get(formFileName);
            IrbSingleForm singleForm = singleFormMap.get(productType+"_"+currVer+type);
            if(singleForm == null){
                singleForm = singleFormMap.get(com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT + "_" + currVer + type);
            }
            if(singleForm == null){
                throw new RuntimeException("未发现表单 模版类型:" + productType + ",表单类型:" + com.pinde.core.common.enums.ResRecTypeEnum.getNameById(formFileName) + ",版本号:" + currVer);
            }

            if(singleForm != null){

                //是否存在总分
                String totalScore = req.getParameter("totalScore");
                //如果存在总分回写进过程表
                Double score = new Double(0);
                if(process!=null){
                    boolean toUpdate = false;
                    //是否出科，是则更新出科标记为Y，更新当前轮转标记为N，更新实际轮转结束日期为当前日期
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(req.getParameter("isAgree"))) {
                        process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
                        process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
//						process.setEndDate(DateUtil.getCurrDate());
                        toUpdate = true;
                    }
                    //存在总分则更新过程表的出科成绩
                    if(StringUtil.isNotBlank(totalScore) && !"undefined".equals(totalScore)){
                        score = Double.parseDouble(totalScore);
                        process.setSchScore(BigDecimal.valueOf(score));
                        toUpdate = true;
                    }
                    //江苏中医 学生出科考核表，如果学员无考试权限则出科考核表的总分为其出科的分数
                    String operUserFlow = req.getParameter("operUserFlow");
                    if(StringUtil.isBlank(operUserFlow)){
                        operUserFlow=GlobalContext.getCurrentUser().getUserFlow();
                    }
                    String totalScore2 = req.getParameter("theoryAssessment");
                    boolean f=resDoctorBiz.getCkkPower(operUserFlow);
                    if(!f){
                        //存在总分则更新过程表的出科成绩
                        if(StringUtil.isNotBlank(totalScore2)){
                            score = Double.parseDouble(totalScore2);
                            if(productType.equals("gzzyyy")){
                                process.setSchScore(BigDecimal.valueOf((score/20)*100));
                            }else{
                                process.setSchScore(BigDecimal.valueOf((score/25)*100));
                            }
                            toUpdate = true;
                        }
                    }
                    if(toUpdate){
                        processBiz.edit(process);
                    }
                }
                //读取一条表单记录
                ResSchProcessExpress express = readResExpress(recFlow);
                if (express == null){
                    express = new ResSchProcessExpress();

                    express.setMedicineType(medicineTypeId);
                    //表单类型
                    express.setRecTypeId(formFileName);
                    express.setRecTypeName(com.pinde.core.common.enums.ResRecTypeEnum.valueOf(formFileName).getTypeName());
                    //表单版本
                    express.setRecVersion(currVer);
                    express.setRecForm(productType);

                    //表单学员 设置表单的用户和机构信息
                    String operUserFlow = req.getParameter("operUserFlow");
                    if(StringUtil.isNotBlank(operUserFlow)){
                        ResDoctor doc = resDoctorBiz.readDoctor(operUserFlow);
                        express.setOperUserFlow(doc.getDoctorFlow());
                        express.setOperUserName(doc.getDoctorName());
                        if(!StringUtil.isNotBlank(express.getOrgFlow())){
                            express.setOrgFlow(doc.getOrgFlow());
                            express.setOrgName(doc.getOrgName());
                        }
                    }else{
                        express.setOperUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                        express.setOperUserName(GlobalContext.getCurrentUser().getUserName());
                        if(!StringUtil.isNotBlank(express.getOrgFlow())){
                            express.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
                            express.setOrgName(GlobalContext.getCurrentUser().getOrgName());
                        }
                    }

                    //记录该表单在哪个过程中产生的
                    express.setProcessFlow(processFlow);
                }

                //轮转科室信息
                SchDept schDept = schDeptBiz.readSchDept(schDeptFlow);
                express.setSchDeptFlow(schDeptFlow);
                if(schDept!=null){
                    express.setSchDeptName(schDept.getSchDeptName());
                    express.setOrgFlow(schDept.getOrgFlow());
                    express.setOrgName(schDept.getOrgName());
                    express.setDeptFlow(schDept.getDeptFlow());
                    express.setDeptName(schDept.getDeptName());
                }
                String recContent = "";
                //根据表单类型组织大字段
                if (GlobalRecTypeEnum.Ethics.getId().equals(express.getRecTypeId()) || //医德医风
                        GlobalRecTypeEnum.Document.getId().equals(express.getRecTypeId()) || //医学文案
                        GlobalRecTypeEnum.NursingSkills.getId().equals(express.getRecTypeId()) || //护理操作技能
                        GlobalRecTypeEnum.ClinicalEnglish.getId().equals(express.getRecTypeId()) || //临床英语应用
                        GlobalRecTypeEnum.Appraisal.getId().equals(express.getRecTypeId())|| //实习总鉴定
                        GlobalRecTypeEnum.CourseScore.getId().equals(express.getRecTypeId())) { //实习成绩单
                    recContent = getRecContent(formFileName, singleForm, req);
                } else if (com.pinde.core.common.enums.ResRecTypeEnum.TeachRegistry.getId().equals(express.getRecTypeId())) { //教学登记
                    recContent = getRecContent(formFileName, singleForm.getItemList(), req,express.getRecContent());
                } else if (com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(express.getRecTypeId())) { //出科考核表
                    String roleFlag = req.getParameter("roleFlag");
                    recContent = getEvaluationContent(
                            formFileName,
                            singleForm.getItemList(),
                            req,roleFlag,
                            express.getRecContent()
                    );
                }else{
                    recContent = getRecContent(formFileName, singleForm.getItemList(), req);
                }

                //老师审核状态
                if(req.getParameter("auditStatusId")!=null){
                    express.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
                    express.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
                    express.setAuditTime(currTime);
                    express.setAuditUserFlow(currUser.getUserFlow());
                    express.setAuditUserName(currUser.getUserName());
                }

                //科主任审核状态
                if(req.getParameter("headAuditStatusId")!=null){
                    express.setHeadAuditStatusId(RecStatusEnum.HeadAuditY.getId());
                    express.setHeadAuditStatusName(RecStatusEnum.HeadAuditY.getName());
                    express.setHeadAuditTime(currTime);
                    express.setHeadAuditUserFlow(currUser.getUserFlow());
                    express.setHeadAuditUserName(currUser.getUserName());
                }

                //基地主任审核状态
                if(req.getParameter("managerAuditStatusId")!=null){
                    express.setManagerAuditStatusId(RecStatusEnum.ManagerAuditY.getId());
                    express.setManagerAuditStatusName(RecStatusEnum.ManagerAuditY.getName());
                    express.setManagerAuditTime(currTime);
                    express.setManagerAuditUserFlow(currUser.getUserFlow());
                    express.setManagerAuditUserName(currUser.getUserName());
                }

                //医院管理员审核状态
                if(req.getParameter("adminAuditStatusId")!=null){
                    express.setAdminAuditStatusId(RecStatusEnum.AdminAuditY.getId());
                    express.setAdminAuditStatusName(RecStatusEnum.AdminAuditY.getName());
                    express.setAdminAuditTime(currTime);
                    express.setAdminAuditUserFlow(currUser.getUserFlow());
                    express.setAdminAuditUserName(currUser.getUserName());
                }

                //培训年度
                if (com.pinde.core.common.enums.ResRecTypeEnum.AnnualTrainForm.getId().equals(express.getRecTypeId())) {
                    express.setOperTime(req.getParameter("trainDate"));
                }

                //更新大字段内容
                express.setRecContent(recContent);

                String useContent = recContent;

                String regItem = req.getParameter("regItem");
                String xmlItemName = req.getParameter("xmlItemName");
                if(StringUtil.isBlank(express.getRecFlow())){
                    String[] itemIds = req.getParameterValues("itemId");
                    String[] itemNames = req.getParameterValues("itemName");
                    if(itemIds!=null && itemIds.length>0){
                        for(int i = 0 ; i<itemIds.length ; i++){
                            String itemId = itemIds[i];
                            String itemName = null;
                            if(itemNames!=null && i<itemNames.length){
                                itemName = itemNames[i];
                            }else{
                                itemName = "";
                            }

                            express.setItemName(itemName);

                            if(StringUtil.isNotBlank(xmlItemName)){
                                if (com.pinde.core.common.GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(itemId)) {
                                    itemName = regItem;
                                }
                                recContent = replaceNodeValue(useContent,xmlItemName,itemName,itemId);
                                express.setRecContent(recContent);
                            }
                            express.setRecFlow(null);
                            express.setItemId(itemId);
                            edit(express);
                        }
                    }else{
                        edit(express);
                    }
                }else{
                    String itemId = req.getParameter("itemId");
                    String itemName = req.getParameter("itemName");
                    express.setItemName(itemName);
                    if(StringUtil.isNotBlank(xmlItemName)){
                        if (com.pinde.core.common.GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(itemId)) {
                            itemName = regItem;
                        }
                        recContent = replaceNodeValue(useContent,xmlItemName,itemName,itemId);
                        express.setRecContent(recContent);
                    }
                    express.setItemId(itemId);
                    edit(express);
                }

                return com.pinde.core.common.GlobalConstant.ONE_LINE;
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public List<ResSchProcessExpress> queryResRecList(List<String> processFlowList, List<String> operUserFlowList,String recTypeId) {
        if(CollectionUtils.isEmpty(processFlowList) || CollectionUtils.isEmpty(operUserFlowList) || processFlowList.size() != operUserFlowList.size()) {
            return Collections.emptyList();
        }

        return expressMapper.queryResRecList(processFlowList, operUserFlowList, recTypeId);
    }

    @Override
    public ResSchProcessExpress queryResRec(String processFlow, String operUserFlow,
                              String recTypeId) {
        ResSchProcessExpress rec=null;
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        com.pinde.sci.model.mo.ResSchProcessExpressExample.Criteria create=example.createCriteria();
        create.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(processFlow)) {
            create.andSchRotationDeptFlowEqualTo(processFlow);
        }
        if (StringUtil.isNotBlank(operUserFlow)) {
            create.andOperUserFlowEqualTo(operUserFlow);
        }
        if (StringUtil.isNotBlank(recTypeId)) {
            create.andRecTypeIdEqualTo(recTypeId);
        }
        List<ResSchProcessExpress> list = expressMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() >0) {
            rec = list.get(0);
        }
        return rec;
    }
    @Override
    public ResSchProcessExpress queryResRecByProcessAndType(String processFlow,String recTypeId) {
        ResSchProcessExpress rec=null;
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        com.pinde.sci.model.mo.ResSchProcessExpressExample.Criteria create=example.createCriteria();
        create.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(processFlow)) {
            create.andProcessFlowEqualTo(processFlow);
        }
        if (StringUtil.isNotBlank(recTypeId)) {
            create.andRecTypeIdEqualTo(recTypeId);
        }
        List<ResSchProcessExpress> list = expressMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() >0) {
            rec = list.get(0);
        }
        return rec;
    }
    /**
     * 根据Item信息，获取表单内容
     * @param list
     * @param req
     * @param rootEle
     * @return
     */
    private String getContent(List<Element> list,HttpServletRequest req,Element rootEle){
        if(list !=null && list.size()>0 && rootEle!=null){
            for(Element itemEle : list){
                String multiple = itemEle.attributeValue("multiple");
                if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(multiple)) {
                    String name = itemEle.attributeValue("name");
                    String isSelect = itemEle.attributeValue("select");

                    String value = req.getParameter(name);

                    Element element = DocumentHelper.createElement(itemEle.attributeValue("name"));

                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isSelect)) {
                        String valueName = req.getParameter(name+"_name");
                        if(StringUtil.isBlank(value)){
                            valueName = (String) req.getAttribute(name+"_name");
                        }

                        element.addAttribute("id",value);

                        value = valueName;
                    }

                    if (StringUtil.isNotBlank(value)) {
                        element.setText(value);
                    }
                    rootEle.add(element);
                }else {
                    String[] values = req.getParameterValues(itemEle.attributeValue("name"));
                    Element element = DocumentHelper.createElement(itemEle.attributeValue("name"));
                    if(values != null && values.length > 0) {
                        for (String value : values) {
                            Element valueEle = DocumentHelper.createElement("value");
                            if (StringUtil.isNotBlank(value)) {
                                valueEle.setText(value);
                            }
                            element.add(valueEle);
                        }
                    }
                    rootEle.add(element);
                }
            }
        }
        return rootEle.asXML();
    }
    /**
     * 获取表单内容 [出科考核表]
     * @param formName 登记类型
     * @param list 表单内容信息
     * @param req
     * @param roleFlag
     * @param oldContent
     * @return
     */
    private String getEvaluationContent(String formName, List<Element> list, HttpServletRequest req, String roleFlag, String oldContent){
        String content = null;
        try {
            Document doc = null;
            Element root = null;
            Element roleNode = null;
            if(StringUtil.isNotBlank(oldContent)){
                doc = DocumentHelper.parseText(oldContent);
                root = doc.getRootElement();
                roleNode = root.element(roleFlag + com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
                if(roleNode != null){
                    roleNode.detach();
                }
            }else{
                doc = DocumentHelper.createDocument();
                root = doc.addElement(formName);
            }
            roleNode = DocumentHelper.createElement(roleFlag + com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
            getContent(list,req,roleNode);
            root.add(roleNode);
            content = root.asXML();
        } catch (DocumentException e) {
            logger.error("", e);
        }
        return content;
    }

    @Override
    public List<ResSchProcessExpress> getAllAfterSummary(String id) {
        if(StringUtil.isNotBlank(id))
        {
            ResSchProcessExpressExample example=new ResSchProcessExpressExample();
            example.createCriteria().andRecTypeIdEqualTo(id).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
            example.setOrderByClause("create_time asc");
            return expressMapper.selectByExampleWithBLOBs(example);
        }
        return null;
    }

    @Override
    public List<ResSchProcessExpress> searchByProcessFlowClob(String processFlow) {

        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        if(StringUtil.isNotBlank(processFlow)){
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow);
            return expressMapper.selectByExampleWithBLOBs(example);
        }
        return  null;
    }

    private static Logger logger = LoggerFactory.getLogger(IResSchProcessExpressBizImpl.class);

}
