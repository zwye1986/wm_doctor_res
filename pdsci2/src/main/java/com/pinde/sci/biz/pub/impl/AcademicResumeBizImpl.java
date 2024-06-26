package com.pinde.sci.biz.pub.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IAcademicResumeBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.form.pub.AcademicResumeForm;
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
public class AcademicResumeBizImpl implements IAcademicResumeBiz {
    @Autowired
    private IPubUserResumeBiz pubResumeBiz;

    @Override
    public List<AcademicResumeForm> queryAcademicList(PubUserResume resume) throws Exception {
        List<AcademicResumeForm> academicFormList = null;
        if(null != resume && StringUtil.isNotBlank(resume.getUserResume())){
            Document dom = DocumentHelper.parseText(resume.getUserResume());
            String xpath = "/rusume/academic/record";
            List<Element> recordElements = dom.selectNodes(xpath);
            if(null != recordElements && !recordElements.isEmpty()){
                academicFormList = new ArrayList<AcademicResumeForm>();
                AcademicResumeForm form = null;
                for(Element e : recordElements){
                    form = new AcademicResumeForm();
                    form.setRecordId(e.attributeValue("recordId"));//id
                    form.setStartDate(e.element("startDate").getTextTrim());
                    form.setAcademicName(e.element("academicName").getTextTrim());
                    form.setTitle(e.element("title").getTextTrim());
                    academicFormList.add(form);
                }
            }
        }
        return academicFormList;
    }


    @Override
    public AcademicResumeForm getAcademicResumeByRecordId(String userFlow, String recordId) throws Exception {
        AcademicResumeForm academicForm = null;
        if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(recordId)){
            PubUserResume resume = pubResumeBiz.readPubUserResume(userFlow);
            if(null != resume && StringUtil.isNotBlank(resume.getUserResume())){
                Document dom = DocumentHelper.parseText(resume.getUserResume());
                String xpath = "/rusume/academic/record[@recordId = '"+recordId+"']";
                Element e = (Element) dom.selectSingleNode(xpath);
                if(null != e){
                    academicForm = new AcademicResumeForm();
                    academicForm.setRecordId(e.attributeValue("recordId"));
                    academicForm.setStartDate(e.element("startDate").getTextTrim());
                    academicForm.setAcademicName(e.element("academicName").getTextTrim());
                    academicForm.setTitle(e.element("title").getTextTrim());
                }
            }
        }
        return academicForm;
    }


    @Override
    public int saveAcademicResume(SysUser user, AcademicResumeForm form) throws Exception {
        if(null != user && null != form){
            Document dom ;
            Element root ;
            Element academicElement;

            String startDate = form.getStartDate();
            String academicName = form.getAcademicName();
            String title = form.getTitle();

            PubUserResume resume = pubResumeBiz.readPubUserResume(user.getUserFlow());

            if (null != resume && null != resume.getUserResume()) {//存在个人简历--》修改
                dom = DocumentHelper.parseText(resume.getUserResume());
                root = dom.getRootElement();
                //有无工作经历记录？无则创建一个academic节点
                String xpath = "/rusume/academic";
                academicElement = (Element)dom.selectSingleNode(xpath);
                if(null != academicElement){
                    academicElement = root.element("academic");

                }else{
                    academicElement = root.addElement("academic");
                }

            } else {//新增个人简历
                resume = new PubUserResume();

                dom = DocumentHelper.createDocument();
                root = dom.addElement("rusume");
                academicElement = root.addElement("academic");
            }

            String xpath = "/rusume/academic/record[@recordId = '" + form.getRecordId() + "']";
            Element recordElement = (Element)dom.selectSingleNode(xpath);
            //根据 record id存在 判断新增或修改 个人简历中的学会任职履历

            if(null != recordElement){
                recordElement.addAttribute("recordId",form.getRecordId());
                recordElement.element("startDate").setText(StringUtil.defaultIfEmpty(startDate, ""));
                recordElement.element("academicName").setText(StringUtil.defaultIfEmpty(academicName, ""));
                recordElement.element("title").setText(StringUtil.defaultIfEmpty(title, ""));
            }else{
                String recordId = PkUtil.getUUID();
                recordElement = academicElement.addElement("record").addAttribute("recordId", recordId);
                recordElement.addElement("startDate").setText(StringUtil.defaultIfEmpty(startDate, ""));
                recordElement.addElement("academicName").setText(StringUtil.defaultIfEmpty(academicName, ""));
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
    public int delAcademicResumeByRecordId(String userFlow, String recordId) throws Exception {
        if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(recordId)){
            PubUserResume resume = pubResumeBiz.readPubUserResume(userFlow);
            if(null != resume && StringUtil.isNotBlank(resume.getUserResume())){
                Document dom = DocumentHelper.parseText(resume.getUserResume());
                String xpath = "/rusume/academic/record[@recordId = '"+recordId+"']";
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
