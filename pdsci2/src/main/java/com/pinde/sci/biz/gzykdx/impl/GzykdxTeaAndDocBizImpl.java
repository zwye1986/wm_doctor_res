package com.pinde.sci.biz.gzykdx.impl;

import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzykdx.IGzykdxTeaAndDocBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.gzykdx.GzykdxAuditManageExtMapper;
import com.pinde.sci.dao.gzykdx.GzykdxTeacherExtMapper;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.gzykdx.GzykdxTeacherExtInfoForm;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangshuai
 * @Copyright njpdxx.com
 * <p/>
 * 此类主要是实现 XXX 功能
 * @since 2017/3/8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GzykdxTeaAndDocBizImpl implements IGzykdxTeaAndDocBiz{
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private SysOrgMapper sysOrgMapper;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private TeacherTargetApplyMapper targetApplyMapper;
    @Autowired
    private TeacherThesisDetailMapper thesisDetailMapper;
    @Autowired
    private TeacherTargetApplyMapper teacherTargetApplyMapper;
    @Autowired
    private DoctorTeacherRecruitMapper doctorTeacherRecruitMapper;
    @Autowired
    private GzykdxTeacherExtMapper  teacherExtMapper;
    @Autowired
    private DoctorTeacherRecruitBatchMapper doctorTeacherRecruitBatchMapper;
    @Autowired
    private GzykdxAuditManageExtMapper auditManageExtMapper;
    @Autowired
    private PubFileMapper fileMapper;

    @Override
    public List<TeacherTargetApply> selectTargetApplyList(TeacherTargetApply teacherTargetApply) {
        TeacherTargetApplyExample example=new TeacherTargetApplyExample();
        TeacherTargetApplyExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(teacherTargetApply.getUserFlow())){
            criteria.andUserFlowEqualTo(teacherTargetApply.getUserFlow());
        }
        if(StringUtil.isNotBlank(teacherTargetApply.getRecruitYear())){
            criteria.andRecruitYearEqualTo(teacherTargetApply.getRecruitYear());
        }
        if(StringUtil.isNotBlank(teacherTargetApply.getIsAcademic())){
            criteria.andIsAcademicEqualTo(teacherTargetApply.getIsAcademic());
        }
        if(StringUtil.isNotBlank(teacherTargetApply.getIsSpecialized())){
            criteria.andIsSpecializedEqualTo(teacherTargetApply.getIsSpecialized());
        }
        if(StringUtil.isNotBlank(teacherTargetApply.getSpeId())){
            criteria.andSpeIdEqualTo(teacherTargetApply.getSpeId());
        }
        if(StringUtil.isNotBlank(teacherTargetApply.getApplyFlow())){
            criteria.andApplyFlowEqualTo(teacherTargetApply.getApplyFlow());
        }
        return teacherTargetApplyMapper.selectByExample(example);
    }

    @Override
    public int editTeacherTargetApply(TeacherTargetApply teacherTargetApply) {
        TeacherTargetApplyExample example=new TeacherTargetApplyExample();
        TeacherTargetApplyExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(teacherTargetApply.getApplyFlow())){
            criteria.andApplyFlowEqualTo(teacherTargetApply.getApplyFlow());
        }
        return teacherTargetApplyMapper.updateByExampleSelective(teacherTargetApply,example);
    }

    @Override
    public int addTeacherTargetApply(TeacherTargetApply teacherTargetApply) {
        return targetApplyMapper.insertSelective(teacherTargetApply);
    }

    @Override
    public int saveTeacherInfo(SysUser sysUser, GzykdxTeacherExtInfoForm teacherExtInfoForm) {
        if(StringUtil.isNotBlank(sysUser.getSexId())){
            sysUser.setSexName(UserSexEnum.getNameById(sysUser.getSexId()));
        }else{
            sysUser.setSexName("");
        }
        //学历
        if(StringUtil.isNotBlank(sysUser.getEducationId())){
            sysUser.setEducationName(DictTypeEnum.HighestEducation.getDictNameById(sysUser.getEducationId()));
        }else{
            sysUser.setEducationName("");
        }
        //学位
        if(StringUtil.isNotBlank(sysUser.getDegreeId())){
            sysUser.setDegreeName(DictTypeEnum.HighestDegree.getDictNameById(sysUser.getDegreeId()));
        }else{
            sysUser.setDegreeName("");
        }
        //职称
//        if(StringUtil.isNotBlank(sysUser.getTitleId())){
//            sysUser.setTitleName(DictTypeEnum.GzykdxUserTitle.getDictNameById(sysUser.getDegreeId()));
//        }else{
//            sysUser.setTitleName("");
//        }
        userBiz.saveUser(sysUser);
        String userFlow = sysUser.getUserFlow();
        PubUserResume pubUserResume = userResumeBiz.readPubUserResume(userFlow);
        if(pubUserResume == null){
            pubUserResume = new PubUserResume();
        }
        //JavaBean转换成xml
        String xmlContent = JaxbUtil.convertToXml(teacherExtInfoForm);
        pubUserResume.setUserResume(xmlContent);
        return userResumeBiz.savePubUserResume(sysUser, pubUserResume);
    }

    @Override
    public int editTeacherThesisDetail(TeacherThesisDetail teacherThesisDetail) {
        if(StringUtil.isNotBlank(teacherThesisDetail.getRecordFlow())){
            GeneralMethod.setRecordInfo(teacherThesisDetail, false);
            return thesisDetailMapper.updateByPrimaryKeySelective(teacherThesisDetail);
        }else{
            teacherThesisDetail.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(teacherThesisDetail, true);
            return thesisDetailMapper.insertSelective(teacherThesisDetail);
        }
    }

    @Override
    public int editThesisDetail(TeacherThesisDetail teacherThesisDetail, PubFile pubFile) {
        //操作附件
        if(pubFile != null){
            if(StringUtil.isNotBlank(pubFile.getFileFlow())){
                GeneralMethod.setRecordInfo(pubFile, false);
                fileMapper.updateByPrimaryKeySelective(pubFile);
            }
            else{
                pubFile.setFileFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(pubFile, true);
                fileMapper.insert(pubFile);
            }
        }
        teacherThesisDetail.setFileFlow(pubFile.getFileFlow());
        if(StringUtil.isNotBlank(teacherThesisDetail.getRecordFlow())){
            GeneralMethod.setRecordInfo(teacherThesisDetail, false);
            return thesisDetailMapper.updateByPrimaryKeySelective(teacherThesisDetail);
        }else{
            teacherThesisDetail.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(teacherThesisDetail, true);
            return thesisDetailMapper.insertSelective(teacherThesisDetail);
        }
    }

    @Override
    public List<PubFile> selectFileList(String productFlow) {
        PubFileExample example=new PubFileExample();
        PubFileExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(productFlow)){
            criteria.andProductFlowEqualTo(productFlow);
        }
        return fileMapper.selectByExample(example);
    }

    @Override
    public PubFile readPubFlie(String fileFlow) {
        return fileMapper.selectByPrimaryKey(fileFlow);
    }

    @Override
    public List<SysOrg> searchOrgList() {
        SysOrgExample example=new SysOrgExample();
        SysOrgExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andIsSecondFlagEqualTo(GlobalConstant.RECORD_STATUS_Y);
        return sysOrgMapper.selectByExample(example);
    }

    @Override
    public List<TeacherThesisDetail> detailList(TeacherThesisDetail teacherThesisDetail) {
        TeacherThesisDetailExample example=new TeacherThesisDetailExample();
        TeacherThesisDetailExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(teacherThesisDetail.getRecordFlow())){
            criteria.andRecordFlowEqualTo(teacherThesisDetail.getRecordFlow());
        }
        if(StringUtil.isNotBlank(teacherThesisDetail.getApplyFlow())){
            criteria.andApplyFlowEqualTo(teacherThesisDetail.getApplyFlow());
        }
        if(StringUtil.isNotBlank(teacherThesisDetail.getThesisTypeId())){
            criteria.andThesisTypeIdEqualTo(teacherThesisDetail.getThesisTypeId());
        }
        return thesisDetailMapper.selectByExample(example);
    }

    @Override
    public List<DoctorTeacherRecruit> searchDoctorTeacherRecruits(DoctorTeacherRecruit recruit) {
        DoctorTeacherRecruitExample example=new DoctorTeacherRecruitExample();
        DoctorTeacherRecruitExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
        }
        if(StringUtil.isNotBlank(recruit.getRecruitYear())){
            criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
        }
        return doctorTeacherRecruitMapper.selectByExample(example);
    }

    @Override
    public List<DoctorTeacherRecruitBatch> searchDoctorTeacherRecruitBatchs(DoctorTeacherRecruitBatch recruitBatch) {
        DoctorTeacherRecruitBatchExample example=new DoctorTeacherRecruitBatchExample();
        DoctorTeacherRecruitBatchExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(recruitBatch.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(recruitBatch.getDoctorFlow());
        }
        if(StringUtil.isNotBlank(recruitBatch.getRecruitYear())){
            criteria.andRecruitYearEqualTo(recruitBatch.getRecruitYear());
        }
        return doctorTeacherRecruitBatchMapper.selectByExample(example);
    }

    @Override
    public List<TeacherTargetApply> searchTeacherNotFull(TeacherTargetApply apply) {
//        TeacherTargetApply apply=new TeacherTargetApply();
//        apply.setOrgFlow(orgFlow);
//        apply.setRecruitYear(recruitYear);
//        apply.setUserFlow(userFlow);
//        apply.setUserName(userName);
//        apply.setSpeId(speId);
//        apply.setResearchDirectionId(researchDirectionId);
        TeacherTargetApplyExample example=new TeacherTargetApplyExample();
        TeacherTargetApplyExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(apply.getOrgFlow())){
            criteria.andOrgFlowEqualTo(apply.getOrgFlow());
        }
        if(StringUtil.isNotBlank(apply.getRecruitYear())){
            criteria.andRecruitYearEqualTo(apply.getRecruitYear());
        }
        if(StringUtil.isNotBlank(apply.getUserFlow())){
            criteria.andUserFlowEqualTo(apply.getUserFlow());
        }
        if(StringUtil.isNotBlank(apply.getUserName())){
            criteria.andUserNameLike("%"+apply.getUserName()+"%");
        }
        if(StringUtil.isNotBlank(apply.getSpeId())){
            criteria.andSpeIdEqualTo(apply.getSpeId());
        }
        if(StringUtil.isNotBlank(apply.getResearchDirectionId())){
            criteria.andResearchDirectionIdEqualTo(apply.getResearchDirectionId());
        }
        if(StringUtil.isNotBlank(apply.getIsAcademic())){
            criteria.andIsAcademicEqualTo(apply.getIsAcademic());
        }
        if(StringUtil.isNotBlank(apply.getIsSpecialized())){
            criteria.andIsSpecializedEqualTo(apply.getIsSpecialized());
        }
        List<TeacherTargetApply> teacherApplyAll=teacherTargetApplyMapper.selectByExample(example);
        if(teacherApplyAll!=null&&teacherApplyAll.size()>0){
            for (int i=0;i<teacherApplyAll.size();i++) {
                Map<String,String> map=new HashMap<>();
                map.put("recruitYear",teacherApplyAll.get(i).getRecruitYear());
                map.put("userFlow",teacherApplyAll.get(i).getUserFlow());
                int count=teacherExtMapper.countDoctorFromTea(map);
                int teacherNum=0;
                if(StringUtil.isNotBlank(apply.getIsAcademic())&&apply.getIsAcademic().equals(teacherApplyAll.get(i).getIsAcademic())){
                    if(StringUtil.isNotBlank(apply.getAcademicNum())){
                        teacherNum=Integer.parseInt(apply.getAcademicNum());
                    }
                }
                if(StringUtil.isNotBlank(apply.getIsSpecialized())&&apply.getIsSpecialized().equals(teacherApplyAll.get(i).getIsSpecialized())){
                    if(StringUtil.isNotBlank(apply.getSpecializedNum())){
                        teacherNum=Integer.parseInt(apply.getSpecializedNum());
                    }

                }
                if(teacherNum==count){
                    teacherApplyAll.remove(i);
                }
            }
        }
        return teacherApplyAll;
    }

    @Override
    public List<Map<String, String>> searchTeacherNotFullNew(Map<String, String> map) {
        return teacherExtMapper.countTeacherVacancies(map);
    }

    @Override
    public int editDoctorTeacherRecruitBatch(DoctorTeacherRecruitBatch recruitBatch) {
        if(StringUtil.isNotBlank(recruitBatch.getRecordFlow())){
            GeneralMethod.setRecordInfo(recruitBatch, false);
            return doctorTeacherRecruitBatchMapper.updateByPrimaryKeySelective(recruitBatch);
        }else{
            recruitBatch.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(recruitBatch, true);
            return doctorTeacherRecruitBatchMapper.insertSelective(recruitBatch);
        }
    }

    @Override
    public int editDoctorTeacherRecruits(DoctorTeacherRecruit recruit) {
        if(StringUtil.isNotBlank(recruit.getRecordFlow())){
            GeneralMethod.setRecordInfo(recruit, false);
            return doctorTeacherRecruitMapper.updateByPrimaryKeySelective(recruit);
        }else{
            recruit.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(recruit, true);
            return doctorTeacherRecruitMapper.insertSelective(recruit);
        }
    }

    @Override
    public int editRecruitsByUserIdNo(DoctorTeacherRecruit recruit) {
        DoctorTeacherRecruitExample example=new DoctorTeacherRecruitExample();
        DoctorTeacherRecruitExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteria.andUserIdNoEqualTo(recruit.getUserIdNo());
        return doctorTeacherRecruitMapper.updateByExampleSelective(recruit,example);
    }

    @Override
    public List<Map<String, String>> notFullOrglist(String orgFlow,String degreeTypeId,String recruitYear) {
        Map<String, String> map=new HashMap<>();
        map.put("recruitYear",recruitYear);
        map.put("orgFlow",orgFlow);
        map.put("degreeTypeId",degreeTypeId);
        return teacherExtMapper.queryOrgVacancies(map);
    }

    @Override
    public List<Map<String, Object>> selectDoctorAdmissionList(Map<String, String> map) {
        return teacherExtMapper.queryDoctorAdmissionList(map);
    }

    @Override
    public void downGzykdxFile(PubFile file, final HttpServletResponse response) throws Exception{
        /*文件是否存在*/
        Boolean fileExists = false;
        if(file !=null){
            byte[] data=null;
            long dataLength = 0;
            /*如果上传类型为“1”(文件保存在磁盘)，且文件相对路径不为空*/
            if("1".equals(file.getFileUpType()) && StringUtil.isNotBlank(file.getFilePath())){
                /*获取文件物理路径*/
                String filePath = InitConfig.getSysCfg("upload_base_dir") + file.getFilePath();

                File downLoadFile = new File(filePath);
                /*文件是否存在*/
                if(downLoadFile.exists()){
                    InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
                    data = new byte[fis.available()];
                    dataLength = downLoadFile.length();
                    fis.read(data);
                    fis.close();
                    fileExists = true;
                }
            }else {
                data = file.getFileContent();
                if (data != null) {
                    dataLength = data.length;
                    fileExists = true;
                }
            }
            if(fileExists) {
                try {

                    String fileName = file.getFileName();
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                    if (StringUtil.isNotBlank(file.getFileSuffix())) {
                        fileName += "." + file.getFileSuffix();
                    }
                    response.reset();
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                    response.addHeader("Content-Length", "" + dataLength);
                    response.setContentType("application/octet-stream;charset=UTF-8");
                    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                    if (data != null) {
                        outputStream.write(data);
                    }
                    outputStream.flush();
                    outputStream.close();
                }catch (IOException e){
                    fileExists = false;
                }
            }
        }else {
            fileExists = false;
        }
        if(!fileExists){
            /*设置页面编码为UTF-8*/
            response.setHeader("Content-Type","text/html;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,点击返回上一页</a>".getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
            outputStream.flush();
            outputStream.close();
        }
    }

    @Override
    public int delThesisDetail(List<String> recordFlows) {
        int num=0;
        TeacherThesisDetailExample example=new TeacherThesisDetailExample();
        TeacherThesisDetailExample.Criteria criteria =example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(recordFlows!=null&&recordFlows.size()>0){
            criteria.andRecordFlowIn(recordFlows);
            num=thesisDetailMapper.deleteByExample(example);
        }
        return num;
    }
}
