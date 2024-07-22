package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.IJsResRecBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.res.IResRecBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.enums.osca.AuditStatusEnum;
import com.pinde.sci.enums.res.ResDoctorAuditStatusEnum;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.form.jsres.BackTrainForm;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.mo.ResDoctorRecruitWithBLOBs;
import com.pinde.sci.model.mo.ResRec;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class JsResRecBizImpl implements IJsResRecBiz {
    @Autowired
    private IResDoctorRecruitBiz recruitBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private ISchRotationBiz rotationBiz;
    @Autowired
    private IMsgBiz msgBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;

    @Override
    public int saveBackTrain(BackTrainForm form, ResDoctorRecruitWithBLOBs recruitWithBLOBs) {
        String reasonId = StringUtil.defaultIfEmpty(form.getReasonId(), "");
        String reasonName = StringUtil.defaultIfEmpty(form.getReasonName(), "");
        String reason = StringUtil.defaultIfEmpty(form.getReason(), "");
        String policyId = StringUtil.defaultIfEmpty(form.getPolicyId(), "");
        String policyName = StringUtil.defaultIfEmpty(form.getPolicyName(), "");
        String policy = StringUtil.defaultIfEmpty(form.getPolicy(), "");
        String remark = StringUtil.defaultIfEmpty(form.getRemark(), "");
        String dispositon = StringUtil.defaultIfEmpty(form.getDispositon(), "");
        Document dom = null;
        Element root = null;
        ResRec resRec = new ResRec();
        ResDoctorRecruit recruit = recruitBiz.readResDoctorRecruit(recruitWithBLOBs.getRecruitFlow());
        ResDoctor doctor = resDoctorBiz.readDoctor(recruit.getDoctorFlow());
        resRec.setOrgFlow(recruit.getOrgFlow());
        resRec.setOrgName(recruit.getOrgName());
        resRec.setOperUserFlow(recruit.getDoctorFlow());
        resRec.setOperUserName(doctor.getDoctorName());
        resRec.setRecTypeId(ResRecTypeEnum.ReturnTraining.getId());
        resRec.setRecTypeName(ResRecTypeEnum.ReturnTraining.getName());
        if (form != null) {
            dom = DocumentHelper.createDocument();
            root = dom.addElement(ResRecTypeEnum.ReturnTraining.getId());
            Element reasonNameElement = root.addElement("reasonName");
            Element policyNameElement = root.addElement("policyName");
            Element reasonElement = root.addElement("reason");
            Element policyElement = root.addElement("policy");
            Element remarkElement = root.addElement("remark");
            Element dispositonElement = root.addElement("dispositon");
            Element sessionNumberElement = root.addElement("sessionNumber");
            Element trainSpeElement = root.addElement("trainSpe");
            reasonNameElement.addAttribute("id", reasonId);
            policyNameElement.addAttribute("id", policyId);
            reasonNameElement.setText(reasonName);
            policyNameElement.setText(policyName);
            remarkElement.setText(remark);
            dispositonElement.setText(dispositon);
            reasonElement.setText(reason);
            policyElement.setText(policy);
            sessionNumberElement.setText(recruit.getSessionNumber());
            trainSpeElement.addAttribute("id", recruit.getSpeId());
            trainSpeElement.setText(recruit.getSpeName());
            resRec.setRecContent(dom.asXML());
        }
        //更新rec数据
        int recResult = resRecBiz.edit(resRec);
        //更新recruit数据
        recruit.setAuditStatusId(ResDoctorAuditStatusEnum.NotSubmit.getId());
        recruit.setAuditStatusName(ResDoctorAuditStatusEnum.NotSubmit.getName());
        //更新doctor数据
        doctor.setOrgFlow("");
        doctor.setOrgName("");
        doctor.setTrainingTypeId("");
        doctor.setTrainingTypeName("");
        doctor.setTrainingSpeId("");
        doctor.setTrainingSpeName("");
        doctor.setSessionNumber("");
        doctor.setDoctorStatusId("");
        doctor.setDoctorStatusName("");
        doctor.setTrainingYears("");
        doctor.setDegreeCategoryId("");
        doctor.setDegreeCategoryName("");
        resDoctorBiz.editDoctor(doctor, null);
        doctor = rotationBiz.updateDocRotation(doctor);
        resDoctorBiz.editDoctor(doctor, null);

        //每一条培训记录保存一个培训方案
        recruit.setRotationFlow(doctor.getRotationFlow());
        recruit.setRotationName(doctor.getRotationName());
        int recruitResult = recruitBiz.updateDocrecruit(recruit);

        if (recResult != 0 && recruitResult != 0) {
            String msgTitle = "培训信息退培结果";
            String msgContent = "您的培训信息已被基地退回";
            msgBiz.addSysMsg(recruit.getDoctorFlow(), msgTitle, msgContent);
            return GlobalConstant.ONE_LINE;
        } else {
            return GlobalConstant.ZERO_LINE;
        }
    }
/**
 * //在res_rec表拆分的时候将该方法注释掉
    @Override
    public int saveBackTrainWithFiles(BackTrainForm form, ResDoctorRecruitWithBLOBs recruitWithBLOBs,List<PubFile> pubFiles) {
        String reasonId = StringUtil.defaultIfEmpty(form.getReasonId(), "");
        String reasonName = StringUtil.defaultIfEmpty(form.getReasonName(), "");
        String reason = StringUtil.defaultIfEmpty(form.getReason(), "");
        String policyId = StringUtil.defaultIfEmpty(form.getPolicyId(), "");
        String policyName = StringUtil.defaultIfEmpty(form.getPolicyName(), "");
        String policy = StringUtil.defaultIfEmpty(form.getPolicy(), "");
        String remark = StringUtil.defaultIfEmpty(form.getRemark(), "");
        String dispositon = StringUtil.defaultIfEmpty(form.getDispositon(), "");
        String auditStatusId = StringUtil.defaultIfEmpty(form.getAuditStatusId(), "");
        String auditStatusName = StringUtil.defaultIfEmpty(form.getAuditStatusName(), "");
        String auditOpinion = StringUtil.defaultIfEmpty(form.getAuditOpinion(), "");
        Document dom = null;
        Element root = null;
        ResRec resRec = new ResRec();
        ResDoctorRecruit recruit = recruitBiz.readResDoctorRecruit(recruitWithBLOBs.getRecruitFlow());
        ResDoctor doctor = resDoctorBiz.readDoctor(recruit.getDoctorFlow());
        resRec.setOrgFlow(recruit.getOrgFlow());
        resRec.setOrgName(recruit.getOrgName());
        resRec.setOperUserFlow(recruit.getDoctorFlow());
        resRec.setOperUserName(doctor.getDoctorName());
        resRec.setRecTypeId(ResRecTypeEnum.ReturnTraining.getId());
        resRec.setRecTypeName(ResRecTypeEnum.ReturnTraining.getName());
        if (form != null) {
            dom = DocumentHelper.createDocument();
            root = dom.addElement(ResRecTypeEnum.ReturnTraining.getId());
            Element reasonNameElement = root.addElement("reasonName");
            Element policyNameElement = root.addElement("policyName");
            Element reasonElement = root.addElement("reason");
            Element policyElement = root.addElement("policy");
            Element remarkElement = root.addElement("remark");
            Element dispositonElement = root.addElement("dispositon");
            Element sessionNumberElement = root.addElement("sessionNumber");
            Element trainSpeElement = root.addElement("trainSpe");
            Element auditStatusElement = root.addElement("auditStatusName");
            Element auditOpinionElement = root.addElement("auditOpinion");
            reasonNameElement.addAttribute("id", reasonId);
            policyNameElement.addAttribute("id", policyId);
            auditStatusElement.addAttribute("id", auditStatusId);
            reasonNameElement.setText(reasonName);
            policyNameElement.setText(policyName);
            remarkElement.setText(remark);
            dispositonElement.setText(dispositon);
            reasonElement.setText(reason);
            policyElement.setText(policy);
            sessionNumberElement.setText(recruit.getSessionNumber());
            trainSpeElement.addAttribute("id", recruit.getSpeId());
            trainSpeElement.setText(recruit.getSpeName());
            auditStatusElement.setText(auditStatusName);
            auditOpinionElement.setText(auditOpinion);
            resRec.setRecContent(dom.asXML());
        }
        //更新rec数据
        int recResult = resRecBiz.editWithFiles(resRec,pubFiles);
        //更新recruit数据
        recruit.setAuditStatusId(ResDoctorAuditStatusEnum.NotSubmit.getId());
        recruit.setAuditStatusName(ResDoctorAuditStatusEnum.NotSubmit.getName());
        int recruitResult = recruitBiz.updateDocrecruit(recruit);
        if (recResult != 0 && recruitResult != 0) {
            return GlobalConstant.ONE_LINE;
        } else {
            return GlobalConstant.ZERO_LINE;
        }
    }
 **/
    @Override
    public Map<Object, Object> paraseBackTrain(List<ResRec> recList, String sessionNumber, String speName, String reasonId,String policyId) throws DocumentException {
        List<ResRec> newRecList = new ArrayList<ResRec>();
        Map<Object, Object> backInfoMap = new HashMap<Object, Object>();
        Map<Object, Object> countMap = new HashMap<Object, Object>();
        Document dom = null;
        BackTrainForm form = null;
        if (recList != null && !recList.isEmpty()) {
            for (ResRec rec : recList) {
                form = new BackTrainForm();
                String content = rec.getRecContent();
                if (StringUtil.isNotBlank(content)) {
                    dom = DocumentHelper.parseText(content);
                    String Xpath = "//" + ResRecTypeEnum.ReturnTraining.getId();
                    Element root = (Element) dom.selectSingleNode(Xpath);
                    if (root != null) {
                        Element reasonNameElement = root.element("reasonName");
                        if (reasonNameElement != null) {
                            form.setReasonId(reasonNameElement.attribute("id").getValue());
                            form.setReasonName(reasonNameElement.getText());
                        }
                        Element policyNameElement = root.element("policyName");
                        if (policyNameElement != null) {
                            form.setPolicyId(policyNameElement.attribute("id").getValue());
                            form.setPolicyName(policyNameElement.getText());
                        }
                        Element reasonElement = root.element("reason");
                        if (reasonElement != null) {
                            form.setReason(reasonElement.getText());
                        }
                        Element policyElement = root.element("policy");
                        if (policyElement != null) {
                            form.setPolicy(policyElement.getText());
                        }
                        Element dispositonElement = root.element("dispositon");
                        if (dispositonElement != null) {
                            form.setDispositon(dispositonElement.getText());
                        }
                        Element remarkElement = root.element("remark");
                        if (remarkElement != null) {
                            form.setRemark(remarkElement.getText());
                        }
                        Element sessionNumberElement = root.element("sessionNumber");
                        if (sessionNumberElement != null) {
                            form.setSessionNumber(sessionNumberElement.getText());
                        }
                        Element trainSpeElement = root.element("trainSpe");
                        if (trainSpeElement != null) {
                            form.setTrainSpe(trainSpeElement.getText());
                        }
                    }
                }
                String result = GlobalConstant.FLAG_Y;
                if (StringUtil.isNotBlank(sessionNumber) && StringUtil.isNotBlank(form.getSessionNumber())) {
                    if (!form.getSessionNumber().equals(sessionNumber)) {
                        result = GlobalConstant.FLAG_N;
                    }
                }
                if (StringUtil.isNotBlank(reasonId) && StringUtil.isNotBlank(form.getReasonId())) {
                    if (!form.getReasonId().equals(reasonId)) {
                        result = GlobalConstant.FLAG_N;
                    }
                }
                if (StringUtil.isNotBlank(policyId) && StringUtil.isNotBlank(form.getPolicyId())) {
                    if (!form.getPolicyId().equals(policyId)) {
                        result = GlobalConstant.FLAG_N;
                    }
                }
                if (StringUtil.isNotBlank(speName) && StringUtil.isNotBlank(form.getTrainSpe())) {
                    if (!form.getTrainSpe().contains(speName)) {
                        result = GlobalConstant.FLAG_N;
                    }
                }
                if (GlobalConstant.FLAG_Y.equals(result)) {
                    String key = rec.getRecFlow();
                    backInfoMap.put(key, form);
                    newRecList.add(rec);
                    countMap.put(rec.getOperUserFlow(), GlobalConstant.FLAG_F);
                }
            }
            backInfoMap.put(GlobalConstant.FLAG_F, countMap);
            backInfoMap.put(GlobalConstant.FLAG_Y, newRecList);
        }
        return backInfoMap;
    }
/**
 * 在res_rec表拆分的时候将该方法注释掉
    @Override
    public int saveDelayInfo(BackTrainForm backTrainForm, ResDoctorRecruitWithBLOBs recruitWithBLOBs) {
        ResRec resRec = new ResRec();
        ResDoctorRecruit recruit = recruitBiz.readResDoctorRecruit(recruitWithBLOBs.getRecruitFlow());
        resRec.setOrgFlow(recruit.getOrgFlow());
        resRec.setOrgName(recruit.getOrgName());
        ResDoctor doctor = resDoctorBiz.readDoctor(recruitWithBLOBs.getDoctorFlow());
        resRec.setOperUserName(doctor.getDoctorName());
        resRec.setRecTypeId(ResRecTypeEnum.Delay.getId());
        resRec.setRecTypeName(ResRecTypeEnum.Delay.getName());
        resRec.setOperUserFlow(recruit.getDoctorFlow());
        Document dom = null;
        Element root = null;
        if (backTrainForm != null) {
            dom = DocumentHelper.createDocument();
            root = dom.addElement(ResRecTypeEnum.Delay.getId());
            Element delayReasonElement = root.addElement("delayReason");
            Element delayPicValueFileElement = root.addElement("delayPicValueFile");
            Element graduationYearElement = root.addElement("graduationYear");
            Element trainTypeElement = root.addElement("trainType");
            Element sessionNumberElement = root.addElement("sessionNumber");
            Element trainSpeElement = root.addElement("trainSpe");
            Element doctorFlowElement = root.addElement("doctorFlow");
            Element recruitFlowElement = root.addElement("recruitFlow");
            doctorFlowElement.setText(recruit.getDoctorFlow());
            recruitFlowElement.setText(recruit.getRecruitFlow());
            delayReasonElement.setText(backTrainForm.getDelayReason());
            delayPicValueFileElement.setText(backTrainForm.getDelayPicValueFile());
            graduationYearElement.setText(backTrainForm.getGraduationYear());
            trainTypeElement.setText(recruit.getCatSpeName());
            sessionNumberElement.setText(recruit.getSessionNumber());
            trainSpeElement.setText(recruit.getSpeName());

            resRec.setRecContent(dom.asXML());
        }
        recruit.setGraduationYear(backTrainForm.getGraduationYear());
        int recResult = resRecBiz.edit(resRec);
        int recruitResult = recruitBiz.updateDocrecruit(recruit);
        if (recResult != 0 && recruitResult != 0) {
//			String msgTitle = "培训信息退培结果";
//			String msgContent = "您的培训信息已被基地退回";
//			msgBiz.addSysMsg(recruit.getDoctorFlow(), msgTitle , msgContent);
            return GlobalConstant.ONE_LINE;
        } else {
            return GlobalConstant.ZERO_LINE;
        }
    }
**/
    @Override
    public Map<String, Object> paraseDelayInfo(List<ResRec> recList) throws DocumentException {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (recList != null && !recList.isEmpty()) {
            BackTrainForm backTrainForm = null;
            for (ResRec rec : recList) {
                backTrainForm = new BackTrainForm();
                String xml = rec.getRecContent();
                backTrainForm = userResumeBiz.converyToJavaBean(xml, BackTrainForm.class);
                String key = rec.getRecFlow();
                dataMap.put(key, backTrainForm);
            }
        }
        return dataMap;
    }

    @Override
    public int checkBackTrain(ResRec resRec, BackTrainForm form,ResDoctorRecruit doctorRecruit) throws DocumentException {
        String content = resRec.getRecContent();
        String auditStatusId = StringUtil.defaultIfEmpty(form.getAuditStatusId(), "");
        String auditStatusName = StringUtil.defaultIfEmpty(form.getAuditStatusName(), "");
        String auditOpinion = StringUtil.defaultIfEmpty(form.getAuditOpinion(), "");
        Document dom = DocumentHelper.parseText(content);
        Element root = dom.getRootElement();
        Element auditStatusElement = root.element("auditStatusName");
        if(auditStatusElement != null){
            Attribute oldAuditStatusId = auditStatusElement.attribute("id");
            oldAuditStatusId.setValue(auditStatusId);
            auditStatusElement.setText(auditStatusName);
        }else{
            auditStatusElement=root.addElement("auditStatusName");
            auditStatusElement.addAttribute("id", auditStatusId);
            auditStatusElement.setText(auditStatusName);
        }
        Element auditOpinionElement = root.element("auditOpinion");
        if(auditOpinionElement != null){
            auditOpinionElement.setText(auditOpinion);
        }else{
            auditOpinionElement=root.addElement("auditOpinion");
            auditOpinionElement.setText(auditOpinion);
        }
        resRec.setRecContent(dom.asXML());
        //更新rec数据
        int recResult = resRecBiz.edit(resRec);
        //更新recruit数据
        if("0".equals(form.getAuditStatusId())){
            doctorRecruit.setAuditStatusId(AuditStatusEnum.Passed.getId());
            doctorRecruit.setAuditStatusName(AuditStatusEnum.Passed.getName());
            recruitBiz.updateDocrecruit(doctorRecruit);
        }
        if("1".equals(form.getAuditStatusId())){
            //更新doctor数据
            ResDoctor doctor = resDoctorBiz.readDoctor(doctorRecruit.getDoctorFlow());
            doctor.setOrgFlow("");
            doctor.setOrgName("");
            doctor.setTrainingTypeId("");
            doctor.setTrainingTypeName("");
            doctor.setTrainingSpeId("");
            doctor.setTrainingSpeName("");
            doctor.setSessionNumber("");
            doctor.setDoctorStatusId("");
            doctor.setDoctorStatusName("");
            doctor.setTrainingYears("");
            doctor.setDegreeCategoryId("");
            doctor.setDegreeCategoryName("");
            resDoctorBiz.editDoctor(doctor, null);
            doctor = rotationBiz.updateDocRotation(doctor);
            resDoctorBiz.editDoctor(doctor, null);
            //每一条培训记录保存一个培训方案
                doctorRecruit.setRotationFlow(doctor.getRotationFlow());
                doctorRecruit.setRotationName(doctor.getRotationName());
            int recruitResult = recruitBiz.updateDocrecruit(doctorRecruit);
            if (recResult != 0) {
                String msgTitle = "培训信息退培结果";
                String msgContent = "您的培训信息已被基地退回";
                msgBiz.addSysMsg(doctorRecruit.getDoctorFlow(), msgTitle, msgContent);
                return GlobalConstant.ONE_LINE;
            } else {
                return GlobalConstant.ZERO_LINE;
            }
        }
        return GlobalConstant.ZERO_LINE;
    }
}
