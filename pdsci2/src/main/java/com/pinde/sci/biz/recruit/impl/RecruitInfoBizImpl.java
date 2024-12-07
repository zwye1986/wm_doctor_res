package com.pinde.sci.biz.recruit.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.impl.InxBizImpl;
import com.pinde.sci.biz.recruit.IRecruitInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoLogBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.dao.base.RecruitInfoMapper;
import com.pinde.sci.dao.recruit.RecruitInfoExtMapper;
import com.pinde.core.common.enums.recruit.RecruitOperEnum;
import com.pinde.core.common.enums.recruit.RecruitStatusEnum;
import com.pinde.sci.model.mo.RecruitInfo;
import com.pinde.sci.model.mo.RecruitInfoExample;
import com.pinde.sci.model.mo.RecruitInfoLog;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.recruit.RecruitInfoExt;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class RecruitInfoBizImpl implements IRecruitInfoBiz {

    @Autowired
    private RecruitInfoMapper recruitInfoMapper;

    @Autowired
    private RecruitInfoExtMapper recruitInfoExtMapper;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IRecruitInfoLogBiz recruitInfoLogBiz;

    private static Logger logger = LoggerFactory.getLogger(RecruitInfoBizImpl.class);


    /**
     * 审批IsPass
     * @param recordStatus
     * @param recruitFlow
     * @return
     */
    @Override
    public String auditWriteExam(String recordStatus, String recruitFlow) {
        RecruitInfo recruitInfo = new RecruitInfo();
        recruitInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        recruitInfo.setExamIsPass(recordStatus);
        recruitInfo.setRecruitFlow(recruitFlow);
        int i = recruitInfoMapper.updateByPrimaryKeySelective(recruitInfo);
        if (i == 1){
            RecruitInfoLog log=new RecruitInfoLog();
            log.setRecruitFlow(recruitInfo.getRecruitFlow());
            log.setOperTypeId(RecruitOperEnum.ExamResult.getId());
            log.setOperTypeName(RecruitOperEnum.ExamResult.getName());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recordStatus)) {
                log.setAuditStatusId(RecruitStatusEnum.Passed.getId());
                log.setAuditStatusName(RecruitStatusEnum.Passed.getName());
            }else{
                log.setAuditStatusId(RecruitStatusEnum.NoPassed.getId());
                log.setAuditStatusName(RecruitStatusEnum.NoPassed.getName());
            }
            recruitInfoLogBiz.saveRecruitLog(log);
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        }else {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
        }
    }

    @Override
    public String auditInterview(String recordStatus, String recruitFlow) {
        RecruitInfo recruitInfo = new RecruitInfo();
        recruitInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        recruitInfo.setRecruitFlow(recruitFlow);
        recruitInfo.setInterviewIsPass(recordStatus);
        int i = recruitInfoMapper.updateByPrimaryKeySelective(recruitInfo);
        if (i == 1){
            RecruitInfoLog log=new RecruitInfoLog();
            log.setRecruitFlow(recruitInfo.getRecruitFlow());
            log.setOperTypeId(RecruitOperEnum.InterViewResult.getId());
            log.setOperTypeName(RecruitOperEnum.InterViewResult.getName());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recordStatus)) {
                log.setAuditStatusId(RecruitStatusEnum.Passed.getId());
                log.setAuditStatusName(RecruitStatusEnum.Passed.getName());
            }else{
                log.setAuditStatusId(RecruitStatusEnum.NoPassed.getId());
                log.setAuditStatusName(RecruitStatusEnum.NoPassed.getName());
            }
            recruitInfoLogBiz.saveRecruitLog(log);
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        }else {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
        }
    }

    @Override
    public String auditAdmit(String recordStatus, String recruitFlow) {
        RecruitInfo recruitInfo = new RecruitInfo();
        recruitInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        recruitInfo.setRecruitFlow(recruitFlow);
        recruitInfo.setAdmitIsPass(recordStatus);
        int i = recruitInfoMapper.updateByPrimaryKeySelective(recruitInfo);
        if (i == 1){
            RecruitInfoLog log=new RecruitInfoLog();
            log.setRecruitFlow(recruitInfo.getRecruitFlow());
            log.setOperTypeId(RecruitOperEnum.AdmitResult.getId());
            log.setOperTypeName(RecruitOperEnum.AdmitResult.getName());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recordStatus)) {
                log.setAuditStatusId(RecruitStatusEnum.Passed.getId());
                log.setAuditStatusName(RecruitStatusEnum.Passed.getName());
            }else{
                log.setAuditStatusId(RecruitStatusEnum.NoPassed.getId());
                log.setAuditStatusName(RecruitStatusEnum.NoPassed.getName());
            }
            recruitInfoLogBiz.saveRecruitLog(log);
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        }else {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
        }
    }


    /**
     * 根据userFlow获取RecruitInfo
     * @param userFlow
     * @return
     */
    @Override
    public RecruitInfo searchRecruitInfoByUserFlowAndRecruitYear(String userFlow,String recruitYear) {
        RecruitInfoExample example = new RecruitInfoExample();
        example.createCriteria().andCreateUserFlowEqualTo(userFlow)
                .andRecruitYearEqualTo(recruitYear)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<RecruitInfo> recruitInfos = recruitInfoMapper.selectByExample(example);
        if (recruitInfos != null && recruitInfos.size() > 0){
            return recruitInfos.get(0);
        }else {
            return null;
        }
    }

    @Override
    public RecruitInfo searchByRecruitFlow(String recruitFlow) {
        RecruitInfo recruitInfo = recruitInfoMapper.selectByPrimaryKey(recruitFlow);
        return recruitInfo;
    }



    @Override
    public synchronized Integer approve(String recruitFlow,String auditContent) {
        RecruitInfo recruitInfo = new RecruitInfo();
        recruitInfo.setRecruitFlow(recruitFlow);
        recruitInfo.setAuditStatusId(RecruitStatusEnum.Passed.getId());
        recruitInfo.setAuditStatusName(RecruitStatusEnum.Passed.getName());
        recruitInfo.setAuditContent(auditContent);
        recruitInfo.setAuditTime(DateUtil.getCurrDateTime());
        GeneralMethod.setRecordInfo(recruitInfo,false);
        if (recruitInfoMapper.updateByPrimaryKeySelective(recruitInfo) == 1){
            recruitInfo.setTicketNumber(setTicketNumber(recruitFlow));
            return recruitInfoMapper.updateByPrimaryKeySelective(recruitInfo);
        }else {
            return 0;
        }
    }

    @Override
    public Integer disapprove(String recruitFlow,String auditContent) {
        RecruitInfo recruitInfo = new RecruitInfo();
        recruitInfo.setRecruitFlow(recruitFlow);
        recruitInfo.setAuditStatusId(RecruitStatusEnum.NoPassed.getId());
        recruitInfo.setAuditStatusName(RecruitStatusEnum.NoPassed.getName());
        recruitInfo.setAuditContent(auditContent);
        recruitInfo.setAuditTime(DateUtil.getCurrDateTime());
        GeneralMethod.setRecordInfo(recruitInfo,false);
        return recruitInfoMapper.updateByPrimaryKeySelective(recruitInfo);
    }

    @Override
    public List<RecruitInfoExt> selectRecruitInfoAboutAudit(HashMap<String, String> map) {
        List<RecruitInfoExt> recruitInfoExts = recruitInfoExtMapper.selectRecruitInfoAboutAudit(map);
        if (recruitInfoExts != null && recruitInfoExts.size() > 0){
            return recruitInfoExts;
        }else {
            return null;
        }
    }

    @Override
    public List<RecruitInfoExt> selectRecruitByUserFlow(String userFlow, String orgFlow) {
        return recruitInfoExtMapper.selectRecruitByUserFlow(userFlow,orgFlow);
    }

    @Override
    public RecruitInfoExt searchRecruitInfoByFlow(String recruitFlow) {
        List<RecruitInfoExt> recruitInfoExts = recruitInfoExtMapper.searchRecruitInfoByFlow(recruitFlow);
        if (recruitInfoExts != null && recruitInfoExts.size() > 0){
            return recruitInfoExts.get(0);
        }
        return null;
    }

    @Override
    public ExcelUtile importExamScore(MultipartFile file, String recruitYear, List<RecruitInfoExt> recruitInfoExts) {
        if(StringUtil.isBlank(recruitYear))
        {
            ExcelUtile eu=	 new ExcelUtile();
            eu.put("count", 0);
            eu.put("code", "1");
            eu.put("msg", "请选择招录年份");
            return eu;
        }
        if(recruitInfoExts==null||recruitInfoExts.size()==0)
        {
            ExcelUtile eu=	 new ExcelUtile();
            eu.put("count", 0);
            eu.put("code", "1");
            eu.put("msg", recruitYear+"年没有笔试通过的人员信息，请勿导入！");
            return eu;
        }
        Map<String,RecruitInfoExt> recruitInfoExtMap=new HashMap<>();
        for(RecruitInfoExt recruitInfoExt:recruitInfoExts)
        {
            recruitInfoExtMap.put(recruitInfoExt.getDoctorFlow(),recruitInfoExt);
        }
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "证件号码:idNo",
                    "成绩:examScore",
                    "考试结果:examResult"
            };
            List<String> list=new ArrayList<>();
            return ExcelUtile.importDataExcel2(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String,Object>> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";
                    for(int i=0;i<datas.size();i++)
                    {
                        RecruitInfo recruitInfo= (RecruitInfo) datas.get(i).get("recruitInfo");
                        if(recruitInfo!=null)
                        {
                            count+=saveRecruitInfo(recruitInfo);
                            RecruitInfoLog log=new RecruitInfoLog();
                            log.setRecruitFlow(recruitInfo.getRecruitFlow());
                            log.setOperTypeId(RecruitOperEnum.ExamResult.getId());
                            log.setOperTypeName(RecruitOperEnum.ExamResult.getName());
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfo.getExamIsPass())) {
                                log.setAuditStatusId(RecruitStatusEnum.Passed.getId());
                                log.setAuditStatusName(RecruitStatusEnum.Passed.getName());
                            }else{
                                log.setAuditStatusId(RecruitStatusEnum.NoPassed.getId());
                                log.setAuditStatusName(RecruitStatusEnum.NoPassed.getName());
                            }
                            recruitInfoLogBiz.saveRecruitLog(log);
                        }
                    }
                    eu.put("code",code);
                    eu.put("count",count);
                }


                @Override
                public String checkExcelData(HashMap data,ExcelUtile eu) {
                    String sheetName=(String)eu.get("SheetName");
                    if(sheetName==null||!"examScore".equals(sheetName))
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "请使用系统提供的导入模板！！");
                        return ExcelUtile.RETURN;
                    }
                    int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
                    RecruitInfoExt recruitInfoExt=null;
                    String examScore="";
                    String examIsPass="";
                    for (Object key1 : data.keySet()) {
                        String key=(String) key1;
                        String value=(String) data.get(key);
                        if("idNo".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行证件号码为空，请确认后提交！！");
                            }
                            SysUser su=userBiz.findByIdNo(value);
                            if(su==null){
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行学员信息不存在，请确认后提交！！");
                            }
                            recruitInfoExt=recruitInfoExtMap.get(su.getUserFlow());
                            if(recruitInfoExt==null)
                            {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行证件号为【"+value+"】的学员未填写招录或招录未审核通过，请确认后提交！！");
                            }
                            RecruitInfoExt recruitInfoExt2=searchRecruitInfoByFlow(recruitInfoExt.getRecruitFlow());
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getInterviewFlag()))
                            {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行学员已发面试通知，无法修改，请确认后提交！！");
                            }
                        }
                        if("examScore".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行成绩为空，请确认后提交！！");
                            }
                            String flay=checkScore(value,rowNum,eu);
                            if(null!=flay)
                            {
                                return ExcelUtile.RETURN;
                            }
                            examScore=value;
                        }
                        if("examResult".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行考试结果为空，请确认后提交！！");
                            }
                            if(!"通过".equals(value)&&!"不通过".equals(value))
                            {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行考试结果只能是【通过】或【不通过】，请确认后提交！！");
                            }

                            if("通过".equals(value))
                            {
                                examIsPass = com.pinde.core.common.GlobalConstant.FLAG_Y;
                            }
                            if("不通过".equals(value))
                            {

                                examIsPass = com.pinde.core.common.GlobalConstant.FLAG_N;
                            }
                        }
                    }
                    recruitInfoExt.setExamScore(examScore);
                    recruitInfoExt.setExamIsPass(examIsPass);
                    RecruitInfo recruitInfo=recruitInfoExt;
                    data.put("recruitInfo",recruitInfo);

                    return null;
                }
            });
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public ExcelUtile saveImportInterviewScore(MultipartFile file, String recruitYear, List<RecruitInfoExt> recruitInfoExts) {
        if(StringUtil.isBlank(recruitYear))
        {
            ExcelUtile eu=	 new ExcelUtile();
            eu.put("count", 0);
            eu.put("code", "1");
            eu.put("msg", "请选择招录年份");
            return eu;
        }
        if(recruitInfoExts==null||recruitInfoExts.size()==0)
        {
            ExcelUtile eu=	 new ExcelUtile();
            eu.put("count", 0);
            eu.put("code", "1");
            eu.put("msg", recruitYear+"年没有审核通过的人员信息，请勿导入！");
            return eu;
        }
        Map<String,RecruitInfoExt> recruitInfoExtMap=new HashMap<>();
        for(RecruitInfoExt recruitInfoExt:recruitInfoExts)
        {
            recruitInfoExtMap.put(recruitInfoExt.getDoctorFlow(),recruitInfoExt);
        }
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "证件号码:idNo",
                    "成绩:interviewScore",
                    "面试结果:interviewResult"
            };
            List<String> list=new ArrayList<>();
            return ExcelUtile.importDataExcel2(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String,Object>> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";
                    for(int i=0;i<datas.size();i++)
                    {
                        RecruitInfo recruitInfo= (RecruitInfo) datas.get(i).get("recruitInfo");
                        if(recruitInfo!=null)
                        {
                            count+=saveRecruitInfo(recruitInfo);
                            RecruitInfoLog log=new RecruitInfoLog();
                            log.setRecruitFlow(recruitInfo.getRecruitFlow());
                            log.setOperTypeId(RecruitOperEnum.InterViewResult.getId());
                            log.setOperTypeName(RecruitOperEnum.InterViewResult.getName());
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfo.getInterviewIsPass())) {
                                log.setAuditStatusId(RecruitStatusEnum.Passed.getId());
                                log.setAuditStatusName(RecruitStatusEnum.Passed.getName());
                            }else{
                                log.setAuditStatusId(RecruitStatusEnum.NoPassed.getId());
                                log.setAuditStatusName(RecruitStatusEnum.NoPassed.getName());
                            }
                            recruitInfoLogBiz.saveRecruitLog(log);
                        }
                    }
                    eu.put("code",code);
                    eu.put("count",count);
                }


                @Override
                public String checkExcelData(HashMap data,ExcelUtile eu) {
                    String sheetName=(String)eu.get("SheetName");
                    if(sheetName==null||!"InterviewScore".equals(sheetName))
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "请使用系统提供的导入模板！！");
                        return ExcelUtile.RETURN;
                    }
                    int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
                    RecruitInfoExt recruitInfoExt=null;
                    String interviewScore="";
                    String interviewIsPass="";
                    for (Object key1 : data.keySet()) {
                        String key=(String) key1;
                        String value=(String) data.get(key);
                        if("idNo".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行证件号码为空，请确认后提交！！");
                            }
                            SysUser su=userBiz.findByIdNo(value);
                            if(su==null){
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行学员信息不存在，请确认后提交！！");
                            }
                            recruitInfoExt=recruitInfoExtMap.get(su.getUserFlow());
                            if(recruitInfoExt==null)
                            {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行证件号为【"+value+"】的学员未填写招录或招录未审核通过，请确认后提交！！");
                            }
                            RecruitInfoExt recruitInfoExt2=searchRecruitInfoByFlow(recruitInfoExt.getRecruitFlow());
                            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getInterviewFlag()))
                            {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行学员未发送面试通知，无法添加成绩，请确认后提交！！");
                            }
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getAdmitFlag()))
                            {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行学员已发录取通知，无法修改，请确认后提交！！");
                            }
                        }
                        if("interviewScore".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行成绩为空，请确认后提交！！");
                            }
                            String flay=checkScore(value,rowNum,eu);
                            if(null!=flay)
                            {
                                return ExcelUtile.RETURN;
                            }
                            interviewScore=value;
                        }
                        if("interviewResult".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行面试结果为空，请确认后提交！！");
                            }
                            if(!"通过".equals(value)&&!"不通过".equals(value))
                            {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行面试结果只能是【通过】或【不通过】，请确认后提交！！");
                            }

                            if("通过".equals(value))
                            {
                                interviewIsPass = com.pinde.core.common.GlobalConstant.FLAG_Y;
                            }
                            if("不通过".equals(value))
                            {
                                interviewIsPass = com.pinde.core.common.GlobalConstant.FLAG_N;
                            }
                        }
                    }
                    recruitInfoExt.setInterviewScore(interviewScore);
                    recruitInfoExt.setInterviewIsPass(interviewIsPass);
                    RecruitInfo recruitInfo=recruitInfoExt;
                    data.put("recruitInfo",recruitInfo);

                    return null;
                }
            });
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public ExcelUtile saveImportAdmitResult(MultipartFile file, String recruitYear, List<RecruitInfoExt> recruitInfoExts) {
        if(StringUtil.isBlank(recruitYear))
        {
            ExcelUtile eu=	 new ExcelUtile();
            eu.put("count", 0);
            eu.put("code", "1");
            eu.put("msg", "请选择招录年份");
            return eu;
        }
        if(recruitInfoExts==null||recruitInfoExts.size()==0)
        {
            ExcelUtile eu=	 new ExcelUtile();
            eu.put("count", 0);
            eu.put("code", "1");
            eu.put("msg", recruitYear+"年没有面试通过的人员信息，请勿导入！");
            return eu;
        }
        Map<String,RecruitInfoExt> recruitInfoExtMap=new HashMap<>();
        for(RecruitInfoExt recruitInfoExt:recruitInfoExts)
        {
            recruitInfoExtMap.put(recruitInfoExt.getDoctorFlow(),recruitInfoExt);
        }
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "证件号码:idNo",
                    "是否报到:admitResult"
            };
            List<String> list=new ArrayList<>();
            return ExcelUtile.importDataExcel2(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String,Object>> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";
                    for(int i=0;i<datas.size();i++)
                    {
                        RecruitInfo recruitInfo= (RecruitInfo) datas.get(i).get("recruitInfo");
                        if(recruitInfo!=null)
                        {
                            count+=saveRecruitInfo(recruitInfo);
                            RecruitInfoLog log=new RecruitInfoLog();
                            log.setRecruitFlow(recruitInfo.getRecruitFlow());
                            log.setOperTypeId(RecruitOperEnum.AdmitResult.getId());
                            log.setOperTypeName(RecruitOperEnum.AdmitResult.getName());
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfo.getAdmitFlag())) {
                                log.setAuditStatusId(RecruitStatusEnum.Passed.getId());
                                log.setAuditStatusName(RecruitStatusEnum.Passed.getName());
                            }else{
                                log.setAuditStatusId(RecruitStatusEnum.NoPassed.getId());
                                log.setAuditStatusName(RecruitStatusEnum.NoPassed.getName());
                            }
                            recruitInfoLogBiz.saveRecruitLog(log);
                        }
                    }
                    eu.put("code",code);
                    eu.put("count",count);
                }


                @Override
                public String checkExcelData(HashMap data,ExcelUtile eu) {
                    String sheetName=(String)eu.get("SheetName");
                    if(sheetName==null||!"admitResult".equals(sheetName))
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "请使用系统提供的导入模板！！");
                        return ExcelUtile.RETURN;
                    }
                    int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
                    RecruitInfoExt recruitInfoExt=null;
                    String admitIsPass="";
                    for (Object key1 : data.keySet()) {
                        String key=(String) key1;
                        String value=(String) data.get(key);
                        if("idNo".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行证件号码为空，请确认后提交！！");
                            }
                            SysUser su=userBiz.findByIdNo(value);
                            if(su==null){
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行学员信息不存在，请确认后提交！！");
                            }
                            recruitInfoExt=recruitInfoExtMap.get(su.getUserFlow());
                            if(recruitInfoExt==null)
                            {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行证件号为【"+value+"】的学员未填写招录或招录未审核通过，请确认后提交！！");
                            }
                            RecruitInfoExt recruitInfoExt2=searchRecruitInfoByFlow(recruitInfoExt.getRecruitFlow());
                            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getAdmitFlag()))
                            {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行学员未发录取通知，无法修改，请确认后提交！！");
                            }
                        }
                        if("admitResult".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行报到结果为空，请确认后提交！！");
                            }
                            if(!"是".equals(value)&&!"否".equals(value))
                            {
                                return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行报到结果只能是【是】或【否】，请确认后提交！！");
                            }

                            if("是".equals(value))
                            {
                                admitIsPass = com.pinde.core.common.GlobalConstant.FLAG_Y;
                            }
                            if("否".equals(value))
                            {
                                admitIsPass = com.pinde.core.common.GlobalConstant.FLAG_N;
                            }
                        }
                    }
                    recruitInfoExt.setAdmitIsPass(admitIsPass);
                    RecruitInfo recruitInfo=recruitInfoExt;
                    data.put("recruitInfo",recruitInfo);

                    return null;
                }
            });
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }

    private String checkScore(String score,int i, ExcelUtile resultMap)
    {
        boolean f = score.matches("^((0|[1-9][0-9]{0,2}+)([.]([0-9]{0,1}+))?)$");
        if (!f) {
            resultMap.put("count", 0);
            resultMap.put("code", "1");
            resultMap.put("msg", "导入文件第" + (i + 1) + "行成绩格式不正确[可以为0，第一个数字不能为0，且最多一位小数]，请确认后提交！！");
            return ExcelUtile.RETURN;
        } else {
            if (Double.valueOf(score) > 100 || Double.valueOf(score) <0) {
                resultMap.put("count", 0);
                resultMap.put("code", "1");
                resultMap.put("msg", "导入文件第" + (i + 1) + "行成绩不得大于100或小于0，请确认后提交！！");
                return ExcelUtile.RETURN;
            }
        }
        return null;
    }

    @Override
    public Integer updateRecruitInfo(RecruitInfo recruitInfo) {
        recruitInfo.setTrainingTypeId("Doctor");
        recruitInfo.setTrainingTypeName("住院医师");
        String trainingSpeName = com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getDictNameById(recruitInfo.getTrainingSpeId());
        recruitInfo.setTrainingSpeName(trainingSpeName);
        if ("01".equals(recruitInfo.getDoctorTypeId())){
            recruitInfo.setDoctorTypeName("单位人");
        }else if ("02".equals(recruitInfo.getDoctorTypeId())){
            recruitInfo.setDoctorTypeName("社会人");
        }
        if ("01".equals(recruitInfo.getWorkOrgLevelId())){
            recruitInfo.setWorkOrgLevelName("三级甲等");
        }else if ("02".equals(recruitInfo.getWorkOrgLevelId())){
            recruitInfo.setWorkOrgLevelName("三级乙等");
        }else if ("03".equals(recruitInfo.getWorkOrgLevelId())){
            recruitInfo.setWorkOrgLevelName("二级甲等");
        }else if ("04".equals(recruitInfo.getWorkOrgLevelId())){
            recruitInfo.setWorkOrgLevelName("二级乙等");
        }
        if ("01".equals(recruitInfo.getMasterdegreeTypeId())){
            recruitInfo.setMasterdegreeTypeName("科学型");
        }else if ("02".equals(recruitInfo.getMasterdegreeTypeId())){
            recruitInfo.setMasterdegreeTypeName("专业型");
        }
        if ("01".equals(recruitInfo.getDoctorDegreeTypeId())){
            recruitInfo.setDoctorDegreeTypeName("科学型");
        }else if ("02".equals(recruitInfo.getDoctorDegreeTypeId())){
            recruitInfo.setDoctorDegreeTypeName("专业型");
        }
        if (recruitInfo.getRecruitFlow() != null && recruitInfo.getRecruitFlow() != ""){
            GeneralMethod.setRecordInfo(recruitInfo,false);
            return recruitInfoMapper.updateByPrimaryKeySelective(recruitInfo);
        }else {
            recruitInfo.setRecruitFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(recruitInfo,true);
            return recruitInfoMapper.insertSelective(recruitInfo);
        }
    }
    @Override
    public int saveRecruitInfo(RecruitInfo recruitInfo) {
        if (StringUtil.isNotBlank(recruitInfo.getRecruitFlow()) ){
            GeneralMethod.setRecordInfo(recruitInfo,false);
            return recruitInfoMapper.updateByPrimaryKeySelective(recruitInfo);
        }else {
            recruitInfo.setRecruitFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(recruitInfo,true);
            return recruitInfoMapper.insertSelective(recruitInfo);
        }
    }

    @Override
    public List<RecruitInfoExt> searchCanExamRecruitInfo(Map<String, String> param) {
        return recruitInfoExtMapper.searchCanExamRecruitInfo(param);
    }

    public String setTicketNumber(String recruitFlow){
        RecruitInfo recruitInfo = searchByRecruitFlow(recruitFlow);
        String recruitYear = recruitInfo.getRecruitYear();
        String trainingSpeId = recruitInfo.getTrainingSpeId();
        HashMap<String, String> map = new HashMap<>();
        map.put("recruitYear",recruitYear);
        map.put("trainingSpeId",trainingSpeId);
        List<String> list = recruitInfoExtMapper.searchTicket(recruitYear,trainingSpeId);
        Integer i = list.indexOf(recruitFlow) + 1;
        return recruitYear+trainingSpeId+StringUtil.leftPad(i.toString(), 4, "0");
    }
}