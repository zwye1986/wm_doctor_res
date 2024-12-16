package com.pinde.sci.biz.res.impl;

import com.pinde.core.model.PubFile;
import com.pinde.core.model.ResDoctorRecruitWithBLOBs;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorDelayTeturnBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ResDocotrDelayTeturnMapper;
import com.pinde.sci.dao.res.ResDocotrDelayTeturnExtMapper;
import com.pinde.sci.model.mo.ResDocotrDelayTeturn;
import com.pinde.sci.model.mo.ResDocotrDelayTeturnExample;
import com.pinde.sci.model.mo.ResDoctor;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by pdkj on 2016/12/23.
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class ResDoctorDelayTeturnBizImpl implements IResDoctorDelayTeturnBiz {

    @Autowired
    private ResDocotrDelayTeturnMapper resDocotrDelayTeturnMapper;
    @Autowired
    private ResDocotrDelayTeturnExtMapper resDocotrDelayTeturnExtMapper;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IResDoctorRecruitBiz recruitBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private ISchRotationBiz rotationBiz;
    @Autowired
    private IMsgBiz msgBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IJsResDoctorBiz jsResDoctorBiz;

    @Override
    public List<ResDocotrDelayTeturn> searchInfo(ResDocotrDelayTeturn resDocotrDelayTeturn, List<String> orgFlowList,List<String> flags
            , List<String> docTypeList) {
        ResDocotrDelayTeturnExample example = new ResDocotrDelayTeturnExample();
        ResDocotrDelayTeturnExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(resDocotrDelayTeturn!=null){
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getOrgFlow())){
                criteria.andOrgFlowEqualTo(resDocotrDelayTeturn.getOrgFlow());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getOrgName())){
                criteria.andOrgNameLike("%"+resDocotrDelayTeturn.getOrgName()+"%");
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getDoctorFlow())){
                criteria.andDoctorFlowEqualTo(resDocotrDelayTeturn.getDoctorFlow());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getDoctorName())){
                criteria.andDoctorNameLike("%"+resDocotrDelayTeturn.getDoctorName()+"%");
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getSessionNumber())){
                criteria.andSessionNumberEqualTo(resDocotrDelayTeturn.getSessionNumber());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getTrainingTypeId())){
                criteria.andTrainingTypeIdEqualTo(resDocotrDelayTeturn.getTrainingTypeId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getTrainingSpeId())){
                criteria.andTrainingSpeIdEqualTo(resDocotrDelayTeturn.getTrainingSpeId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getTrainingSpeName())){
                criteria.andTrainingSpeNameLike("%"+resDocotrDelayTeturn.getTrainingSpeName()+"%");
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getPolicyId())){
                criteria.andPolicyIdEqualTo(resDocotrDelayTeturn.getPolicyId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getDoctorTypeId())){
                criteria.andDoctorTypeIdEqualTo(resDocotrDelayTeturn.getDoctorTypeId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getReasonId())){
                criteria.andReasonIdEqualTo(resDocotrDelayTeturn.getReasonId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getAuditStatusId())){
                criteria.andAuditStatusIdEqualTo(resDocotrDelayTeturn.getAuditStatusId());
            }
            if(flags!=null&&flags.size()>0){
                criteria.andAuditStatusIdIn(flags);
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getTypeId())){
                criteria.andTypeIdEqualTo(resDocotrDelayTeturn.getTypeId());
            }
            if(docTypeList!=null&&docTypeList.size()>0){
                criteria.andDoctorTypeIdIn(docTypeList);
            }
        }
        if(orgFlowList!=null&&orgFlowList.size()>0){
            criteria.andOrgFlowIn(orgFlowList);
        }
        example.setOrderByClause("create_time desc");
        return resDocotrDelayTeturnExtMapper.searchInfoNew(example);
    }

    @Override
    public List<ResDocotrDelayTeturn> searchInfo2(ResDocotrDelayTeturn resDocotrDelayTeturn, List<String> orgFlowList,List<String> flags
            , List<String> docTypeList, List<String> sessionNumbers) {
        ResDocotrDelayTeturnExample example = new ResDocotrDelayTeturnExample();
        ResDocotrDelayTeturnExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(resDocotrDelayTeturn!=null){
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getOrgFlow())){
                criteria.andOrgFlowEqualTo(resDocotrDelayTeturn.getOrgFlow());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getOrgName())){
                criteria.andOrgNameLike("%"+resDocotrDelayTeturn.getOrgName()+"%");
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getDoctorFlow())){
                criteria.andDoctorFlowEqualTo(resDocotrDelayTeturn.getDoctorFlow());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getDoctorName())){
                criteria.andDoctorNameLike("%"+resDocotrDelayTeturn.getDoctorName()+"%");
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getSessionNumber())){
                criteria.andSessionNumberEqualTo(resDocotrDelayTeturn.getSessionNumber());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getTrainingTypeId())){
                criteria.andTrainingTypeIdEqualTo(resDocotrDelayTeturn.getTrainingTypeId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getTrainingSpeId())){
                criteria.andTrainingSpeIdEqualTo(resDocotrDelayTeturn.getTrainingSpeId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getTrainingSpeName())){
                criteria.andTrainingSpeNameLike("%"+resDocotrDelayTeturn.getTrainingSpeName()+"%");
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getPolicyId())){
                criteria.andPolicyIdEqualTo(resDocotrDelayTeturn.getPolicyId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getDoctorTypeId())){
                criteria.andDoctorTypeIdEqualTo(resDocotrDelayTeturn.getDoctorTypeId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getReasonId())){
                criteria.andReasonIdEqualTo(resDocotrDelayTeturn.getReasonId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getAuditStatusId())){
                criteria.andAuditStatusIdEqualTo(resDocotrDelayTeturn.getAuditStatusId());
            }
            if(flags!=null&&flags.size()>0){
                criteria.andAuditStatusIdIn(flags);
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getTypeId())){
                criteria.andTypeIdEqualTo(resDocotrDelayTeturn.getTypeId());
            }
            if(docTypeList!=null&&docTypeList.size()>0){
                criteria.andDoctorTypeIdIn(docTypeList);
            }
        }
        if(orgFlowList!=null&&orgFlowList.size()>0){
            criteria.andOrgFlowIn(orgFlowList);
        }
        if(sessionNumbers!=null&&sessionNumbers.size()>0){
            criteria.andSessionNumberIn(sessionNumbers);
        }
        example.setOrderByClause("create_time desc");
        return resDocotrDelayTeturnExtMapper.searchInfoNew(example);
    }

    @Override
    public List<ResDocotrDelayTeturn> searchInfoNew(ResDocotrDelayTeturn resDocotrDelayTeturn, List<String> orgFlowList,List<String> flags
            , List<String> docTypeList) {
        ResDocotrDelayTeturnExample example = new ResDocotrDelayTeturnExample();
        ResDocotrDelayTeturnExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(resDocotrDelayTeturn!=null){
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getOrgFlow())){
                criteria.andOrgFlowEqualTo(resDocotrDelayTeturn.getOrgFlow());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getOrgName())){
                criteria.andOrgNameLike("%"+resDocotrDelayTeturn.getOrgName()+"%");
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getDoctorFlow())){
                criteria.andDoctorFlowEqualTo(resDocotrDelayTeturn.getDoctorFlow());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getDoctorName())){
                criteria.andDoctorNameLike("%"+resDocotrDelayTeturn.getDoctorName()+"%");
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getSessionNumber())){
                criteria.andSessionNumberEqualTo(resDocotrDelayTeturn.getSessionNumber());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getTrainingTypeId())){
                criteria.andTrainingTypeIdEqualTo(resDocotrDelayTeturn.getTrainingTypeId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getTrainingSpeId())){
                criteria.andTrainingSpeIdEqualTo(resDocotrDelayTeturn.getTrainingSpeId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getTrainingSpeName())){
                criteria.andTrainingSpeNameLike("%"+resDocotrDelayTeturn.getTrainingSpeName()+"%");
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getPolicyId())){
                criteria.andPolicyIdEqualTo(resDocotrDelayTeturn.getPolicyId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getDoctorTypeId())){
                criteria.andDoctorTypeIdEqualTo(resDocotrDelayTeturn.getDoctorTypeId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getReasonId())){
                criteria.andReasonIdEqualTo(resDocotrDelayTeturn.getReasonId());
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getAuditStatusId())){
                criteria.andAuditStatusIdEqualTo(resDocotrDelayTeturn.getAuditStatusId());
            }
            if(flags!=null&&flags.size()>0){
                criteria.andAuditStatusIdIn(flags);
            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getTypeId())){
                criteria.andTypeIdEqualTo(resDocotrDelayTeturn.getTypeId());
            }
            if(docTypeList!=null&&docTypeList.size()>0){
                criteria.andDoctorTypeIdIn(docTypeList);
            }
        }
        if(orgFlowList!=null&&orgFlowList.size()>0){
            criteria.andOrgFlowIn(orgFlowList);
        }
        example.setOrderByClause("create_time desc");
        return resDocotrDelayTeturnExtMapper.searchInfoNew(example);
    }
    @Override
    public List<ResDocotrDelayTeturn> searchInfo(Map<String, Object> map) {
        return resDocotrDelayTeturnExtMapper.searchInfo(map);
    }

    @Override
    public List<ResDocotrDelayTeturn> searchByDoctorFlow(String doctorFlow) {
        if(StringUtil.isNotBlank(doctorFlow)){
            ResDocotrDelayTeturnExample example = new ResDocotrDelayTeturnExample();
            ResDocotrDelayTeturnExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow);
            example.setOrderByClause("CREATE_TIME DESC");
            return resDocotrDelayTeturnMapper.selectByExample(example);
        }else{
            return null;
        }
    }

    @Override
    public List<Map<String, String>> findTrainCharts(List<String> orgFlowList, String sessionNumber, String speName, List<String> docTypeList,String resTrainingSpeId) {
        return resDocotrDelayTeturnExtMapper.findTrainCharts(orgFlowList,sessionNumber,speName,docTypeList,resTrainingSpeId);
    }

    @Override
    public List<Map<String, String>> findTrainCharts2(List<String> orgFlowList, String sessionNumber, String speName, List<String> docTypeList, String resTrainingSpeId, String resTrainingTypeId) {
        return resDocotrDelayTeturnExtMapper.findTrainCharts2(orgFlowList,sessionNumber,speName,docTypeList,resTrainingSpeId,resTrainingTypeId);
    }

    @Override
    public List<Map<String, String>> findJszyTrainCharts(List<String> orgFlowList, String sessionNumber, String speName) {
        return resDocotrDelayTeturnExtMapper.findJszyTrainCharts(orgFlowList,sessionNumber,speName);
    }

    @Override
    public List<ResDocotrDelayTeturn> searchInfoForUni(ResDocotrDelayTeturn docotrDelayTeturn, ResDoctor currdoctor) {
        return resDocotrDelayTeturnExtMapper.searchInfoForUni(docotrDelayTeturn,currdoctor);
    }

    @Override
    public int checkBackTrain(ResDocotrDelayTeturn docotrDelayTeturn, com.pinde.core.model.ResDoctorRecruit recruit)
    {
        //更新数据
        int recResult = resDocotrDelayTeturnMapper.updateByPrimaryKeySelective(docotrDelayTeturn);
        //更新recruit数据 0与1 是西医中的审核状态  GlobalNotPassed与GlobalPassed是中医的审核状态
        if (com.pinde.core.common.enums.ResBaseStatusEnum.GlobalNotPassed.getId().equals(docotrDelayTeturn.getAuditStatusId())
                ||"0".equals(docotrDelayTeturn.getAuditStatusId())){
            recruit.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.Passed.getId());
            recruit.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.Passed.getName());
            recruitBiz.updateDocrecruit(recruit);
            return com.pinde.core.common.GlobalConstant.ONE_LINE;
        }
        if (com.pinde.core.common.enums.ResBaseStatusEnum.GlobalPassed.getId().equals(docotrDelayTeturn.getAuditStatusId())
                ||"1".equals(docotrDelayTeturn.getAuditStatusId())){
            //更新doctor数据
            String doctorFlow = recruit.getDoctorFlow();
            ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
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
            doctor = rotationBiz.updateDocRotation(doctor);
            doctorBiz.editDoctor(doctor, null);
            //每一条培训记录保存一个培训方案
            recruit.setRotationFlow(doctor.getRotationFlow());
            recruit.setRotationName(doctor.getRotationName());
            recruit.setDoctorStatusId("23");
            recruit.setDoctorStatusName("退培");

//            recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getId());
//            recruit.setAuditStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getName());

            recruitBiz.updateDocrecruit(recruit);
            //如果是违约退培就需要将当前退培学员加入黑名单
            if("2".equals(docotrDelayTeturn.getPolicyId()))
            {
                int userblack = jsResDoctorBiz.joinJsresUserBalckList(docotrDelayTeturn);
                if(userblack == 1) {
                    String userFlow = docotrDelayTeturn.getDoctorFlow();
                    if (StringUtil.isNotBlank(userFlow)) {
                        SysUser sysUser = userBiz.readSysUser(userFlow);
                        if (sysUser != null) {
                            sysUser.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
                            userBiz.edit(sysUser);
                        }
                        ResDoctor resDoctor = doctorBiz.searchByUserFlow(userFlow);
                        if (resDoctor != null) {
                            resDoctor.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
                            doctorBiz.editDoctor(resDoctor);
                        }
                    }
                }
            }
            if (recResult != 0) {
                String msgTitle = "培训信息退培结果";
                String msgContent = "您的培训信息已被基地退回";
                msgBiz.addSysMsg(recruit.getDoctorFlow(), msgTitle, msgContent);

                /**
                 * 审核通过时同步修改表状态 res_doctor_sch_process ,sch_arrange_result,res_rec ,res_sch_process_express
                 * 字段 record_status 为N ,标记退培删除'returnDel'   shengl add@2019年6月20日
                 */
                if(StringUtil.isNotBlank(doctorFlow)){
                    rotationBiz.updateResDoctorSchProcessStatus(doctorFlow);
                    rotationBiz.updateSchArrangeResultStatus(doctorFlow);
                    rotationBiz.updateResrecStatus(doctorFlow);
                    rotationBiz.updateResSchProcessExpressStatus(doctorFlow);
                }

                return com.pinde.core.common.GlobalConstant.ONE_LINE;
            } else {
                return com.pinde.core.common.GlobalConstant.ZERO_LINE;
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public ResDocotrDelayTeturn readInfo(String recordFlow) {
        if(StringUtil.isNotBlank(recordFlow)){
            return resDocotrDelayTeturnMapper.selectByPrimaryKey(recordFlow);
        }else {
            return null;
        }
    }

    @Override
    public int edit(ResDocotrDelayTeturn resDocotrDelayTeturn, List<PubFile> pubFiles) {
        if(resDocotrDelayTeturn != null){
            //将recruit改成NotSubmit
            String recruitFlow = resDocotrDelayTeturn.getRecruitFlow();
//            com.pinde.core.model.ResDoctorRecruit recruit = recruitBiz.readResDoctorRecruit(recruitFlow);
//            if(recruit != null){
//                recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getId());
//                recruit.setAuditStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getName());
//                recruitBiz.updateDocrecruit(recruit);
//            }
            if(StringUtil.isNotBlank(resDocotrDelayTeturn.getRecordFlow())){//修改
                GeneralMethod.setRecordInfo(resDocotrDelayTeturn, false);
                if(pubFiles != null && pubFiles.size() > 0){
                    for(PubFile file : pubFiles){
                        file.setFileFlow(PkUtil.getUUID());
                        file.setProductFlow(resDocotrDelayTeturn.getRecordFlow());
                        GeneralMethod.setRecordInfo(file, true);
                        fileBiz.addFile(file);
                    }
                }
                return this.resDocotrDelayTeturnMapper.updateByPrimaryKeySelective(resDocotrDelayTeturn);
            }else{//新增
                resDocotrDelayTeturn.setRecordFlow(PkUtil.getUUID());
                if(pubFiles != null && pubFiles.size() > 0){
                    for(PubFile file : pubFiles){
                        file.setFileFlow(PkUtil.getUUID());
                        file.setProductFlow(resDocotrDelayTeturn.getRecordFlow());
                        GeneralMethod.setRecordInfo(file, true);
                        fileBiz.addFile(file);
                    }
                }
                GeneralMethod.setRecordInfo(resDocotrDelayTeturn, true);
                return this.resDocotrDelayTeturnMapper.insertSelective(resDocotrDelayTeturn);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public int edit(ResDocotrDelayTeturn resDocotrDelayTeturn, PubFile pubFile) {
        if(StringUtil.isNotBlank(resDocotrDelayTeturn.getRecordFlow())){
            GeneralMethod.setRecordInfo(resDocotrDelayTeturn, false);
            int ret = resDocotrDelayTeturnMapper.updateByPrimaryKeySelective(resDocotrDelayTeturn);
            return ret;
        }else{
            String id=PkUtil.getUUID();
            resDocotrDelayTeturn.setRecordFlow(id);
            GeneralMethod.setRecordInfo(resDocotrDelayTeturn, true);
            pubFile.setFileFlow(id);
            pubFile.setProductFlow(id);
            fileBiz.addFile(pubFile);
            int ret = resDocotrDelayTeturnMapper.insertSelective(resDocotrDelayTeturn);
            return ret;
        }
    }

    @Override
    public int editJszy(ResDocotrDelayTeturn resDocotrDelayTeturn, ResDoctorRecruitWithBLOBs recruitWithBLOBs) {

        com.pinde.core.model.ResDoctorRecruit recruit = recruitBiz.readResDoctorRecruit(recruitWithBLOBs.getRecruitFlow());
        ResDoctor doctor = doctorBiz.readDoctor(recruit.getDoctorFlow());
        //更新recruit数据
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getId());
        recruit.setAuditStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getName());
        int recruitResult = recruitBiz.updateDocrecruit(recruit);
        resDocotrDelayTeturn.setSecondSpeId(recruit.getSecondSpeId());
        resDocotrDelayTeturn.setSecondSpeName(recruit.getSecondSpeName());
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
        doctor = rotationBiz.updateDocRotation(doctor);
        doctorBiz.editDoctor(doctor, null);
        int ret =0;

        if(StringUtil.isNotBlank(resDocotrDelayTeturn.getRecordFlow())){
            GeneralMethod.setRecordInfo(resDocotrDelayTeturn, false);
             ret = resDocotrDelayTeturnMapper.updateByPrimaryKeySelective(resDocotrDelayTeturn);
        }else{
            String id=PkUtil.getUUID();
            resDocotrDelayTeturn.setRecordFlow(id);
            GeneralMethod.setRecordInfo(resDocotrDelayTeturn, true);
            ret = resDocotrDelayTeturnMapper.insertSelective(resDocotrDelayTeturn);
        }
        if (ret != 0 && recruitResult != 0) {
            String msgTitle = "培训信息退培结果";
            String msgContent = "您的培训信息已被基地退回";
            msgBiz.addSysMsg(recruit.getDoctorFlow(), msgTitle, msgContent);
            return com.pinde.core.common.GlobalConstant.ONE_LINE;
        } else {
            return com.pinde.core.common.GlobalConstant.ZERO_LINE;
        }
    }

    @Override
    public Integer save(ResDocotrDelayTeturn resDocotrDelayTeturn) {
        if(StringUtil.isNotBlank(resDocotrDelayTeturn.getRecordFlow())){
            GeneralMethod.setRecordInfo(resDocotrDelayTeturn, false);
            int ret = resDocotrDelayTeturnMapper.updateByPrimaryKeySelective(resDocotrDelayTeturn);
            return ret;
        }else{
            resDocotrDelayTeturn.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(resDocotrDelayTeturn, true);
            int ret = resDocotrDelayTeturnMapper.insert(resDocotrDelayTeturn);
            return ret;
        }
    }

    @Override
    public Integer saveSynDoctorStatus(ResDocotrDelayTeturn resDocotrDelayTeturn) {
        int i = 0;
        if(StringUtil.isNotBlank(resDocotrDelayTeturn.getRecordFlow())){
            i = resDocotrDelayTeturnMapper.saveSynDoctorStatus(resDocotrDelayTeturn);
            return i;
        }
        return i;
    }

    @Override
    public void exportForBack(List<ResDocotrDelayTeturn> resRecList, HttpServletResponse response,String flag) throws IOException {
        String[] headLines = null;
        headLines = new String[]{
                "退培信息一览表"
        };
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        styleCenter.setWrapText(true);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);
        //列宽自适应
//		sheet.setDefaultColumnWidth((short)50);
        HSSFRow rowDep = sheet.createRow(0);//第一行
        rowDep.setHeightInPoints(20);
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue("退培信息一览表");

        HSSFRow rowTwo = sheet.createRow(1);//第二行
        String[] titles=null;
        if (flag != null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
            titles = new String[]{
                    "编号",
                    "培训基地",
                    "年级",
                    "学员姓名",
                    "专业",
                    "退培主要原因",
                    "退培类型",
                    "学员去向",
                    "备注(培训基地意见)"
//                    "省厅审核结果",
//                    "省厅审核意见"
            };
        }else {
            titles = new String[]{
                    "编号",
                    "培训基地",
                    "年级",
                    "学员姓名",
                    "专业",
                    "退培主要原因",
                    "退培类型",
                    "学员去向",
                    "备注(培训基地意见)"
            };
        }
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowTwo.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 2 * 256);
        }
        if (flag != null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));//合并单元格
        }else {
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));//合并单元格
        }
        int rowNum = 2;
        int inDocTotal=0;
        int outDocTotal=0;
        String[] resultList = null;
        String[] teacherResultList = null;
        if (resRecList != null && !resRecList.isEmpty()) {
            for (int i = 0; i < resRecList.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                String reason="";
                if(StringUtil.isNotBlank(resRecList.get(i).getReason())){
                    reason=resRecList.get(i).getReasonName()+"("+resRecList.get(i).getReason()+")";
                }else{
                    reason=resRecList.get(i).getReasonName();
                }
                String policy="";
                if(StringUtil.isNotBlank(resRecList.get(i).getPolicy())){
                    policy=resRecList.get(i).getPolicyName()+"("+resRecList.get(i).getPolicy()+")";
                }else{
                    policy=resRecList.get(i).getPolicyName();
                }
                if (flag != null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
                    resultList = new String[]{
                            i+1+"",
                            resRecList.get(i).getOrgName(),
                            resRecList.get(i).getSessionNumber(),
                            resRecList.get(i).getDoctorName(),
                            resRecList.get(i).getTrainingSpeName(),
                            reason,
                            policy,
                            resRecList.get(i).getDispositon(),
                            resRecList.get(i).getRemark()
//                            resRecList.get(i).getAuditStatusName(),
//                            resRecList.get(i).getAuditOpinion()
                    };
                }else {
                    resultList = new String[]{
                            i+1+"",
                            resRecList.get(i).getOrgName(),
                            resRecList.get(i).getSessionNumber(),
                            resRecList.get(i).getDoctorName(),
                            resRecList.get(i).getTrainingSpeName(),
                            reason,
                            policy,
                            resRecList.get(i).getDispositon(),
                            resRecList.get(i).getRemark()
                    };
                }
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
                }

            }
        }
        String fileName = "退培信息一览表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }
    @Override
    public int saveDelayInfo(ResDocotrDelayTeturn docotrDelayTeturn) {
        com.pinde.core.model.ResDoctorRecruit recruit = recruitBiz.readResDoctorRecruit(docotrDelayTeturn.getRecruitFlow());
        ResDoctor resDoctor = doctorBiz.readDoctor(recruit.getDoctorFlow());
        recruit.setGraduationYear(docotrDelayTeturn.getGraduationYear());
        docotrDelayTeturn.setOrgFlow(recruit.getOrgFlow());
        docotrDelayTeturn.setOrgName(recruit.getOrgName());
        docotrDelayTeturn.setDoctorTypeId(resDoctor.getDoctorTypeId());
        docotrDelayTeturn.setDoctorTypeName(resDoctor.getDoctorTypeName());
        docotrDelayTeturn.setDoctorName(resDoctor.getDoctorName());
        docotrDelayTeturn.setTrainingTypeId(resDoctor.getTrainingTypeId());
        docotrDelayTeturn.setTrainingTypeName(resDoctor.getTrainingTypeName());
        docotrDelayTeturn.setDoctorName(resDoctor.getDoctorName());
        docotrDelayTeturn.setTrainingSpeId(resDoctor.getTrainingSpeId());
        docotrDelayTeturn.setTrainingSpeName(resDoctor.getTrainingSpeName());
        String id = PkUtil.getUUID();
        docotrDelayTeturn.setRecordFlow(id);
        GeneralMethod.setRecordInfo(docotrDelayTeturn, true);
        int recResult = resDocotrDelayTeturnMapper.insert(docotrDelayTeturn);
        int recruitResult = recruitBiz.updateDocrecruit(recruit);
        if (recResult != 0 && recruitResult != 0) {
//			String msgTitle = "培训信息退培结果";
//			String msgContent = "您的培训信息已被基地退回";
//			msgBiz.addSysMsg(recruit.getDoctorFlow(), msgTitle , msgContent);
            return com.pinde.core.common.GlobalConstant.ONE_LINE;
        } else {
            return com.pinde.core.common.GlobalConstant.ZERO_LINE;
        }
    }
    @Override
    public int checkDelayInfo(ResDocotrDelayTeturn docotrDelayTeturn) {
        com.pinde.core.model.ResDoctorRecruit recruit = recruitBiz.readResDoctorRecruit(docotrDelayTeturn.getRecruitFlow());
        recruit.setGraduationYear(docotrDelayTeturn.getGraduationYear());
        GeneralMethod.setRecordInfo(docotrDelayTeturn, false);
        int recResult = resDocotrDelayTeturnMapper.updateByPrimaryKeySelective(docotrDelayTeturn);
        int recruitResult = recruitBiz.updateDocrecruit(recruit);
        if (recResult != 0 && recruitResult != 0) {
            String msgTitle = "培训信息延期结果";
            String msgContent = "您的培训信息已被基地延期，延期至" + docotrDelayTeturn.getGraduationYear() + "年。";
            msgBiz.addSysMsg(recruit.getDoctorFlow(), msgTitle, msgContent);
            return com.pinde.core.common.GlobalConstant.ONE_LINE;
        } else {
            return com.pinde.core.common.GlobalConstant.ZERO_LINE;
        }
    }

    @Override
    public List<ResDocotrDelayTeturn> backTrainCheck(ResDocotrDelayTeturn resDocotrDelayTeturn, List<String> orgFlowList,
                                                     List<String> flags, String medicineTypeId,String workOrgId) {
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("b", resDocotrDelayTeturn);
        paramMap.put("orgFlowList", orgFlowList);
        paramMap.put("medicineTypeId", medicineTypeId);
        paramMap.put("docTypeList", null);
        paramMap.put("backTrainStatusIdList", flags);
        paramMap.put("workOrgId", workOrgId);
        return resDocotrDelayTeturnExtMapper.backTrainCheck(paramMap);
    }

    @Override
    public List<ResDocotrDelayTeturn> backTrainCheck(Map<String, Object> map) {
        return resDocotrDelayTeturnExtMapper.backTrainCheck(map);
    }

    @Override
    public ResDocotrDelayTeturn findTeturnInfo(String recruitFlow) {
        ResDocotrDelayTeturnExample example = new ResDocotrDelayTeturnExample();
        ResDocotrDelayTeturnExample.Criteria criteria = example.createCriteria()
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andTypeIdEqualTo(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId())
                .andRecruitFlowEqualTo(recruitFlow).andAuditStatusIdEqualTo("2");
        example.setOrderByClause("CREATE_TIME DESC");
        List<ResDocotrDelayTeturn> list= resDocotrDelayTeturnMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }
    @Override
    public ResDocotrDelayTeturn findDelayInfo(String recruitFlow) {
        ResDocotrDelayTeturnExample example = new ResDocotrDelayTeturnExample();
        ResDocotrDelayTeturnExample.Criteria criteria = example.createCriteria()
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andTypeIdEqualTo(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId())
                .andRecruitFlowEqualTo(recruitFlow);
        example.setOrderByClause("GRADUATION_YEAR DESC");
        List<ResDocotrDelayTeturn> list= resDocotrDelayTeturnMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<ResDocotrDelayTeturn> searchDelayInfo(ResDocotrDelayTeturn delay4Search) {
        ResDocotrDelayTeturnExample example = new ResDocotrDelayTeturnExample();
        ResDocotrDelayTeturnExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(delay4Search != null){
            if(StringUtil.isNotBlank(delay4Search.getDoctorFlow())){
                criteria.andDoctorFlowEqualTo(delay4Search.getDoctorFlow());
            }
            if(StringUtil.isNotBlank(delay4Search.getTypeId())){
                criteria.andTypeIdEqualTo(delay4Search.getTypeId());
            }
            if(StringUtil.isNotBlank(delay4Search.getAuditStatusId())){
                criteria.andAuditStatusIdEqualTo(delay4Search.getAuditStatusId());
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return resDocotrDelayTeturnMapper.selectByExample(example);
    }

    @Override
    public List<Map<String,String>> searchDelayInfoByParamMap(Map<String, Object> paramMap) {
        return resDocotrDelayTeturnExtMapper.searchDelayInfoByParamMap(paramMap);
    }
    @Override
    public List<Map<String,String>> searchBackTrainInfoByParamMap(Map<String, Object> paramMap) {
        return resDocotrDelayTeturnExtMapper.searchBackTrainInfoByParamMap(paramMap);
    }

    @Override
    public String checkFile(MultipartFile file) {
        List<String> mimeList = new ArrayList<String>();
        if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")))){
            mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")).split(","));
        }
        List<String> suffixList = new ArrayList<String>();
        if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix")))){
            suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix").toLowerCase()).split(","));
        }
        String fileType = file.getContentType();//MIME类型;
        String fileName = file.getOriginalFilename();//文件名
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        if(!(mimeList.contains(fileType) &&  suffixList.contains(suffix.toLowerCase()))){
            return "请上传 "+InitConfig.getSysCfg("res_file_support_suffix")+"格式的文件";
        }
        return com.pinde.core.common.GlobalConstant.FLAG_Y;//可执行保存
    }
    @Override
    public String saveCheckFileToDirs(MultipartFile file, String changeRecruitFile, String changeTypeId) {
        String path = com.pinde.core.common.GlobalConstant.FLAG_N;
        if(file.getSize() > 0){
            //创建目录
            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator + changeRecruitFile + File.separator+ changeTypeId;
            File fileDir = new File(newDir);
            if(!fileDir.exists()){
                fileDir.mkdirs();
            }
            //文件名
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            String originalFilename = PkUtil.getGUID()+suffix;
            File newFile = new File(fileDir, originalFilename);
            try {
                file.transferTo(newFile);
            } catch (Exception e) {
                logger.error("", e);
                throw new RuntimeException("保存文件失败！");
            }


            path = changeRecruitFile + "/"+ changeTypeId + "/" + originalFilename;
        }

        return path;
    }

    private static Logger logger = LoggerFactory.getLogger(ResDoctorDelayTeturnBizImpl.class);

}
