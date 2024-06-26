package com.pinde.sci.biz.pub.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.pub.IWorkResumeBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.form.pub.WorkResumeForm;
import com.pinde.sci.model.mo.PubUserResume;
import com.pinde.sci.model.mo.SysUser;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shij on 2016-03-07.
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class WorkResumeBizImpl implements IWorkResumeBiz {
    @Autowired
    private IPubUserResumeBiz pubResumeBiz;
    /**
     * 加载工作经历列表
     */
    @Override
    public List<WorkResumeForm> queryWorkList(PubUserResume resume) throws Exception {
        List<WorkResumeForm> workFormList = null;
        if(null != resume && StringUtil.isNotBlank(resume.getUserResume())){
            Document dom = DocumentHelper.parseText(resume.getUserResume());
            String xpath = "/rusume/work/record";
            List<Element> recordElements = dom.selectNodes(xpath);
            if(null != recordElements && !recordElements.isEmpty()){
                workFormList = new ArrayList<WorkResumeForm>();
                WorkResumeForm form = null;
                for(Element e : recordElements){
                    form = new WorkResumeForm();
                    form.setRecordId(e.attributeValue("recordId"));//id
                    form.setStartDate(e.element("startDate").getTextTrim());
                    form.setEndDate(e.element("endDate").getTextTrim());
                    form.setOrgName(e.element("orgName").getTextTrim());
                    form.setDepartment(e.element("department").getTextTrim());
                    form.setTitle(e.element("title").getTextTrim());
                    workFormList.add(form);
                }
            }
        }
        return workFormList;
    }


    @Override
    public WorkResumeForm getWorkResumeByRecordId(String userFlow, String recordId) throws Exception {
        WorkResumeForm workForm = null;
        if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(recordId)){
            PubUserResume resume = pubResumeBiz.readPubUserResume(userFlow);
            if(null != resume && StringUtil.isNotBlank(resume.getUserResume())){
                Document dom = DocumentHelper.parseText(resume.getUserResume());
                String xpath = "/rusume/work/record[@recordId = '"+recordId+"']";
                Element e = (Element) dom.selectSingleNode(xpath);
                if(null != e){
                    workForm = new WorkResumeForm();
                    workForm.setRecordId(e.attributeValue("recordId"));
                    workForm.setStartDate(e.element("startDate").getTextTrim());
                    workForm.setEndDate(e.element("endDate").getTextTrim());
                    workForm.setOrgName(e.element("orgName").getTextTrim());
                    workForm.setDepartment(e.element("department").getTextTrim());
                    workForm.setTitle(e.element("title").getTextTrim());
                }
            }
        }
        return workForm;
    }


    @Override
    public int saveWorkResume(SysUser user, WorkResumeForm form) throws Exception {
        if(null != user && null != form){
            Document dom ;
            Element root ;
            Element workElement;

            String startDate = form.getStartDate();
            String endDate = form.getEndDate();
            String orgName = form.getOrgName();
            String department = form.getDepartment();
            String title = form.getTitle();

            PubUserResume resume = pubResumeBiz.readPubUserResume(user.getUserFlow());

            if (null != resume && null != resume.getUserResume()) {//存在个人简历--》修改
                dom = DocumentHelper.parseText(resume.getUserResume());
                root = dom.getRootElement();
                //有无工作经历记录？无则创建一个work节点
                String xpath = "/rusume/work";
                workElement = (Element)dom.selectSingleNode(xpath);
                if(null != workElement){
                    workElement = root.element("work");

                }else{
                    workElement = root.addElement("work");
                }

            } else {//新增个人简历
                resume = new PubUserResume();

                dom = DocumentHelper.createDocument();
                root = dom.addElement("rusume");
                workElement = root.addElement("work");
            }

            String xpath = "/rusume/work/record[@recordId = '" + form.getRecordId() + "']";
            Element recordElement = (Element)dom.selectSingleNode(xpath);
            //根据 record id存在 判断新增或修改 个人简历中的工作履历

            if(null != recordElement){
                recordElement.addAttribute("recordId",form.getRecordId());
                recordElement.element("startDate").setText(StringUtil.defaultIfEmpty(startDate, ""));
                recordElement.element("endDate").setText(StringUtil.defaultIfEmpty(endDate, ""));
                recordElement.element("orgName").setText(StringUtil.defaultIfEmpty(orgName, ""));
                recordElement.element("department").setText(StringUtil.defaultIfEmpty(department, ""));
                recordElement.element("title").setText(StringUtil.defaultIfEmpty(title, ""));
            }else{
                String recordId = PkUtil.getUUID();
                recordElement = workElement.addElement("record").addAttribute("recordId", recordId);
                recordElement.addElement("startDate").setText(StringUtil.defaultIfEmpty(startDate, ""));
                recordElement.addElement("endDate").setText(StringUtil.defaultIfEmpty(endDate, ""));
                recordElement.addElement("orgName").setText(StringUtil.defaultIfEmpty(orgName, ""));
                recordElement.addElement("department").setText(StringUtil.defaultIfEmpty(department, ""));
                recordElement.addElement("title").setText(StringUtil.defaultIfEmpty(title, ""));
            }
            //CLOB
            resume.setUserResume(dom.asXML());


            int result =  pubResumeBiz.savePubUserResume(user, resume);
            if(result != GlobalConstant.ZERO_LINE){
                return GlobalConstant.ONE_LINE;
            }

        }
        return GlobalConstant.ZERO_LINE;
    }


    @Override
    public int delWorkResumeByRecordId(String userFlow, String recordId) throws Exception {
        if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(recordId)){
            PubUserResume resume = pubResumeBiz.readPubUserResume(userFlow);
            if(null != resume && StringUtil.isNotBlank(resume.getUserResume())){
                Document dom = DocumentHelper.parseText(resume.getUserResume());
                String xpath = "/rusume/work/record[@recordId = '"+recordId+"']";

                Element e = (Element) dom.selectSingleNode(xpath);//用xpath查找对象
                if(null != e){
                    Element parentE =  e.getParent();
                    parentE.remove(e);
                }
                resume.setUserResume(dom.asXML());
                int result =  pubResumeBiz.savePubUserResume(null, resume);
                if(result != GlobalConstant.ZERO_LINE){
                    return GlobalConstant.ONE_LINE;
                }
            }
        }
        return GlobalConstant.ZERO_LINE;
    }
}
