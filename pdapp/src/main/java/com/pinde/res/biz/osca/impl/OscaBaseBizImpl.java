package com.pinde.res.biz.osca.impl;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.osca.IOscaAppBiz;
import com.pinde.res.biz.osca.IOscaBaseBiz;
import com.pinde.res.biz.osca.IOscaDoctorOrderdeBiz;
import com.pinde.res.dao.osca.ext.OscaCheckInfoExtMapper;
import com.pinde.res.dao.osca.ext.OscaSkillsAssessmentExtMapper;
import com.pinde.res.enums.osca.*;
import com.pinde.res.model.osca.mo.OscaCheckInfoExt;
import com.pinde.sci.dao.base.*;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class OscaBaseBizImpl implements IOscaBaseBiz {

    @Autowired
    private OscaSkillsAssessmentMapper osaMapper;
    @Autowired
    private OscaSkillsAssessmentExtMapper oscaExtMapper;
    @Autowired
    private OscaSubjectMainMapper osmMapper;
    @Autowired
    private OscaDoctorAssessmentMapper odaMapper;
    @Autowired
    private OscaSubjectStationMapper ossMapper;
    @Autowired
    private OscaSkillRoomMapper osrMapper;
    @Autowired
    private OscaSkillRoomTeaMapper osrtMapper;
    @Autowired
    private OscaSkillRoomDocMapper osrdMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private OscaSkillDocScoreMapper osdsMapper;
    @Autowired
    private OscaOrgSpeMapper oosMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private OscaCheckInfoExtMapper checkExtMapper;
    @Autowired
    private OscaExamDifferScoreMapper differScoreMapper;
    @Autowired
    private ResDoctorMapper doctorMapper;
    @Autowired
    private IOscaAppBiz oscaAppBiz;
    @Autowired
    private IOscaDoctorOrderdeBiz doctorOrderdeBiz;
    @Autowired
    private OscaSkillsAssessmentTimeMapper timeMapper;
    @Autowired
    private OscaSkillsAssessmentTimeMapper skillsAssessmentTimeMapper;
    @Autowired
    private OscaSubjectPartScoreMapper partScoreMapper;
    @Autowired
    private OscaSubjectStationScoreMapper stationScoreMapper;
    @Override
    public int queryAppointCount(String orgFlow) {
        return checkExtMapper.queryAppointCount(orgFlow);
    }
    @Override
    public List<Map<String, Object>> queryGuideInfoList(String orgFlow) {
        return checkExtMapper.queryGuideInfoList(orgFlow);
    }

    @Override
    public List<Map<String,Object>> queryDataList(Map<String, Object> map) {
        return checkExtMapper.queryDataList(map);
    }

    @Override
    public OscaSkillsAssessment queryDataByFlow(String clinicalFlow) {
        return osaMapper.selectByPrimaryKey(clinicalFlow);
    }

    @Override
    public List<OscaSubjectMain> querySpeRelation(String speId, String actionTypeId,String orgFlow) {
        OscaSubjectMainExample example = new OscaSubjectMainExample();
        OscaSubjectMainExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andIsReleasedEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(speId)){
            criteria.andTrainingSpeIdEqualTo(speId);
        }
        if(AssessmentProEnum.HospitalPlan.getId().equals(actionTypeId)){
            criteria.andActionTypeIdEqualTo(actionTypeId).andOrgFlowEqualTo(orgFlow);
        }else if(AssessmentProEnum.ProvincialPlan.getId().equals(actionTypeId)){
            criteria.andActionTypeIdEqualTo(actionTypeId);
        }
        return osmMapper.selectByExample(example);
    }

    @Override
    public List<OscaOrgSpe> queryInitSpe(String orgFlow) {
        OscaOrgSpeExample example = new OscaOrgSpeExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow);
        return oosMapper.selectByExample(example);
    }

    @Override
    public int releasedInfo(String clinicalFlow) {
        OscaSkillsAssessment old =getOscaSkillsAssessmentByFlow(clinicalFlow);
        OscaSkillsAssessment osa = new OscaSkillsAssessment();
        osa.setIsReleased(GlobalConstant.RECORD_STATUS_Y);
        osa.setClinicalFlow(clinicalFlow);
        if(old!=null&&StringUtil.isBlank(old.getSkillOrder()))
        {
            Map<String,String> map=getSkillsAssessmentOrder(old.getClinicalYear());
            if(map!=null&&StringUtil.isNotBlank(map.get("SKILL_ORDER")))
            {
                osa.setSkillOrder(map.get("SKILL_ORDER"));
            }
        }
        return osaMapper.updateByPrimaryKeySelective(osa);
    }

    private Map<String, String> getSkillsAssessmentOrder(String clinicalYear) {
        return  oscaExtMapper.getSkillsAssessmentOrder(clinicalYear);
    }

    @Override
    public int delCheckInfo(String clinicalFlow,String isReleased) {
        OscaSkillsAssessment osa = new OscaSkillsAssessment();
        osa.setClinicalFlow(clinicalFlow);
        osa.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        if(StringUtil.isNotBlank(isReleased) && isReleased.equals(GlobalConstant.RECORD_STATUS_Y)){//已预约学员信息均被删除（此处考核开始时间没到）
            OscaDoctorAssessment oda = new OscaDoctorAssessment();
            oda.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            OscaDoctorAssessmentExample example = new OscaDoctorAssessmentExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andClinicalFlowEqualTo(clinicalFlow);
            odaMapper.updateByExampleSelective(oda,example);
        }
        return osaMapper.updateByPrimaryKeySelective(osa);
    }

    @Override
    public int queryAppointmentCount(String clinicalFlow) {
        List<String> args = new ArrayList<>();
        args.add(AuditStatusEnum.Passing.getId());
        args.add(AuditStatusEnum.Passed.getId());
        OscaDoctorAssessmentExample example = new OscaDoctorAssessmentExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andClinicalFlowEqualTo(clinicalFlow).andAuditStatusIdIn(args);
        return odaMapper.countByExample(example);
    }

    @Override
    public List<OscaCheckInfoExt> queryAppointList(Map<String, String> map) {
        return checkExtMapper.queryAppointList(map);
    }

    @Override
    public int auditAppoint(String recordFlow, String auditStatusId, String reason) {
        OscaDoctorAssessment oda = new OscaDoctorAssessment();
        oda.setRecordFlow(recordFlow);
        oda.setAuditStatusId(auditStatusId);
        oda.setAuditStatusName(AuditStatusEnum.getNameById(auditStatusId));
//        odaMapper.updateByPrimaryKeySelective(oda);
        OscaDoctorAssessment old=odaMapper.selectByPrimaryKey(recordFlow);
        if(auditStatusId.equals(AuditStatusEnum.Passed.getId())&&StringUtil.isBlank(old.getTicketNumber())){
            oda.setSiginStatusId(SignStatusEnum.NoSignIn.getId());//预约审核通过后初始化未签到状态
            oda.setSiginStatusName(SignStatusEnum.NoSignIn.getName());
            //预约审核通过后生产准考证号：年份+当年的4位考核顺序码+专业代码+考点代码+4位顺序码
            OscaCheckInfoExt info = checkExtMapper.queryAppointByFlow(recordFlow);
            String fourNum = info.getAuditPassNum();
            if(info.getAuditPassNum().length() == 1){
                fourNum = "000"+info.getAuditPassNum();
            }else if(info.getAuditPassNum().length() == 2){
                fourNum = "00"+info.getAuditPassNum();
            }else if(info.getAuditPassNum().length() == 3){
                fourNum = "0"+info.getAuditPassNum();
            }
            String ticketNumber = info.getClinicalYear()+info.getSkillAssess().getSkillOrder()+info.getSkillAssess().getSpeId()+info.getSysOrg().getOrgCode()+fourNum;
            String codeInfo = info.getSkillAssess().getCodeInfo()+"&recordFlow="+recordFlow+"&doctorFlow="+info.getDoctorFlow()+"&tickNum="+ticketNumber;
            oda.setTicketNumber(ticketNumber);
            oda.setCodeInfo(codeInfo);
        }
        if(StringUtil.isNotBlank(reason)){
            oda.setReason(reason);
        }
        return odaMapper.updateByPrimaryKeySelective(oda);
    }

    List<OscaDoctorAssessment> searchOda(String clinicalFlow){
        OscaDoctorAssessmentExample example = new OscaDoctorAssessmentExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andAuditStatusIdEqualTo(AuditStatusEnum.Passed.getId())
                .andClinicalFlowEqualTo(clinicalFlow);
        example.setOrderByClause("TICKET_NUMBER");
        return odaMapper.selectByExample(example);
    }

    @Override
    public int changeSign(String recordFlow, String signStatusId) {
        OscaDoctorAssessment oda = new OscaDoctorAssessment();
        oda.setRecordFlow(recordFlow);
        oda.setSiginStatusId(signStatusId);
        oda.setSiginStatusName(SignStatusEnum.getNameById(signStatusId));
        if(SignStatusEnum.SignIn.getId().equals(signStatusId)){//签到后初始状态为待录入
            oda.setIsPass(DoctorScoreEnum.PendingEnter.getId());
            oda.setIsPassName(DoctorScoreEnum.PendingEnter.getName());
            oda.setSiginTime(DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm"));//最新签到时间
        }else{
            oda.setSiginTime("");//置空签到时间
            oda.setIsPass(DoctorScoreEnum.Miss.getId());
            oda.setIsPassName(DoctorScoreEnum.Miss.getName());
        }
        return odaMapper.updateByPrimaryKeySelective(oda);
    }

    @Override
    public List<Map<String, Object>> queryRoomList(String clinicalFlow) {
        return checkExtMapper.queryRoomList(clinicalFlow);
    }

    @Override
    public List<Map<String, Object>> queryScheduleList(Map<String, String> map) {
        return checkExtMapper.queryScheduleList(map);
    }

    @Override
    public List<Map<String, Object>> studentsProcess(Map<String, Object> paramMap) {
        return checkExtMapper.studentsProcess(paramMap);
    }

    @Override
    public List<OscaSubjectStation> queryStationList(String subjectFlow) {
        OscaSubjectStationExample example = new OscaSubjectStationExample();
        example.createCriteria().andSubjectFlowEqualTo(subjectFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("ORDINAL");
        return ossMapper.selectByExample(example);
    }

    @Override
    public OscaSkillRoom queryRoomByFlow(String recordFlow) {
        return osrMapper.selectByPrimaryKey(recordFlow);
    }


    @Override
    public List<OscaSkillRoomTea> queryRoomTeaList(String recordFlow) {
        OscaSkillRoomTeaExample example = new OscaSkillRoomTeaExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andRoomRecordFlowEqualTo(recordFlow);
        return osrtMapper.selectByExample(example);
    }

    @Override
    public int delRoom(String recordFlow,String clinicalFlow) {
        int num = -1;
        String examStartTime="";
        OscaSkillsAssessment osa = osaMapper.selectByPrimaryKey(clinicalFlow);
        String curDate = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
        List<String> timeList=checkExtMapper.queryOsaFirstTime(clinicalFlow);
        if(timeList!=null&&timeList.size()>0){
            examStartTime=timeList.get(0);
        }
        if(StringUtil.isBlank(examStartTime)||(StringUtil.isNotBlank(examStartTime)&&curDate.compareTo(examStartTime) < 0)){
            OscaSkillRoomTea osrt = new OscaSkillRoomTea();
            osrt.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            OscaSkillRoomTeaExample osrtExample = new OscaSkillRoomTeaExample();
            if(StringUtil.isBlank(recordFlow)){//清空考场
                OscaSkillRoom osr = new OscaSkillRoom();
                osr.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                OscaSkillRoomExample example = new OscaSkillRoomExample();
                example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                        .andClinicalFlowEqualTo(clinicalFlow);
                num = osrMapper.updateByExampleSelective(osr,example);
                osrtExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                        .andClinicalFlowEqualTo(clinicalFlow);
            }else{//删除单考场
                OscaSkillRoom osr = new OscaSkillRoom();
                osr.setRecordFlow(recordFlow);
                osr.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                num = osrMapper.updateByPrimaryKeySelective(osr);
                osrtExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                        .andClinicalFlowEqualTo(clinicalFlow).andRoomRecordFlowEqualTo(recordFlow);
            }
            osrtMapper.updateByExampleSelective(osrt,osrtExample);//伪删除考场对应的考官
        }
        return num;
    }

    @Override
    public List<Map<String, Object>> queryGradeList(Map<String, String> map) {
        return checkExtMapper.queryGradeList(map);
    }

    @Override
    public List<OscaDoctorAssessment> queryDoctorList(String clinicalFlow) {
        OscaDoctorAssessmentExample example = new OscaDoctorAssessmentExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andClinicalFlowEqualTo(clinicalFlow).andAuditStatusIdEqualTo(AuditStatusEnum.Passed.getId())
                .andSiginStatusIdEqualTo(SignStatusEnum.SignIn.getId());
        return odaMapper.selectByExample(example);
    }

    @Override
    public int isShowOpt(String clinicalFlow, String isShow, String flag) {
        OscaSkillsAssessment osa = new OscaSkillsAssessment();
        osa.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        osa.setClinicalFlow(clinicalFlow);
        if("form".equals(flag)){
            osa.setIsShowFroom(isShow);
        }else{
            if("grade".equals(isShow)){//成绩发布
                osa.setIsGradeReleased(GlobalConstant.RECORD_STATUS_Y);
            }
            else if("school".equals(isShow)){
                osa.setIsGradeReleased("S");
            }
            else if("city".equals(isShow)){
                osa.setIsGradeReleased("C");
            }
            else if("province".equals(isShow)){
                osa.setIsGradeReleased(GlobalConstant.FLAG_Y);
                osa.setIsShow(GlobalConstant.FLAG_Y);
            }
            else{//学生能否查看成绩
                osa.setIsShow(isShow);
            }
            if(GlobalConstant.RECORD_STATUS_N.equals(isShow)){
                osa.setIsShowFroom(GlobalConstant.RECORD_STATUS_N);
            }
        }
        return osaMapper.updateByPrimaryKeySelective(osa);
    }

    @Override
    public Map<String, Object> queryDoctorGrade(Map<String, String> param) {
        return checkExtMapper.queryDoctorGrade(param);
    }


    @Override
    public int updateIsPass(String clinicalFlow, String doctorFlow, String resultId) {
        OscaDoctorAssessment oda = new OscaDoctorAssessment();
        oda.setIsPass(resultId);
        if("Passed".equals(resultId)){
            oda.setIsSavePass(GlobalConstant.FLAG_Y);
        }
        oda.setIsPassName(DoctorScoreEnum.getNameById(resultId));
        OscaDoctorAssessmentExample example = new OscaDoctorAssessmentExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andClinicalFlowEqualTo(clinicalFlow).andDoctorFlowEqualTo(doctorFlow);
        return odaMapper.updateByExampleSelective(oda,example);
    }

    private static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }
    private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
        // 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
        if (!inS.markSupported()) {
            // 还原流信息
            inS = new PushbackInputStream(inS);
        }
//        // EXCEL2003使用的是微软的文件系统
//        if (POIFSFileSystem.hasPOIFSHeader(inS)) {
//            return new HSSFWorkbook(inS);
//        }
//        // EXCEL2007使用的是OOM文件格式
//        if (POIXMLDocument.hasOOXMLHeader(inS)) {
//            // 可以直接传流参数，但是推荐使用OPCPackage容器打开
//            return new XSSFWorkbook(OPCPackage.open(inS));
//        }
        try{
            return WorkbookFactory.create(inS);
        }catch (Exception e) {
            throw new IOException("不能解析的excel版本");
        }
    }

    @Override
    public List<OscaSkillDocScore> queryDocFormScore(Map<String, String> param) {
        OscaSkillDocScoreExample example = new OscaSkillDocScoreExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andClinicalFlowEqualTo(param.get("clinicalFlow")).andDoctorFlowEqualTo(param.get("doctorFlow"))
                .andStationFlowEqualTo(param.get("stationFlow")).andStatusIdEqualTo(ScoreStatusEnum.Submit.getId());
        return osdsMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public OscaSkillDocScore queryDocScoreByFlow(String scoreFlow) {
        return osdsMapper.selectByPrimaryKey(scoreFlow);
    }


    @Override
    public int isShowFroom(String clinicalFlow, String isShowFroom) {
        OscaSkillsAssessment osa = new OscaSkillsAssessment();
        osa.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        osa.setClinicalFlow(clinicalFlow);
        osa.setIsShowFroom(isShowFroom);
        return osaMapper.updateByPrimaryKeySelective(osa);
    }

    @Override
    public List<Map<String, Object>> queryRoomDoctorList(Map<String, String> map) {
        return checkExtMapper.queryRoomDoctorList(map);
    }
    @Override
    public List<Map<String, Object>> queryRoomDoctorList2(Map<String, Object> paramMap) {
        return checkExtMapper.queryRoomDoctorList2(paramMap);
    }

    @Override
    public List<String> queryDoctorFlowList(String clinicalFlow,String testNum) {
       return checkExtMapper.queryDoctorFlowList(clinicalFlow,testNum);
    }

    @Override
    public List<OscaSkillsAssessmentTime> queryOsaTimeList(String clinicalFlow) {
        OscaSkillsAssessmentTimeExample example=new OscaSkillsAssessmentTimeExample();
        OscaSkillsAssessmentTimeExample.Criteria criteria=example.createCriteria();
        criteria.andClinicalFlowEqualTo(clinicalFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        return timeMapper.selectByExample(example);
    }

    @Override
    public List<OscaSkillsAssessmentTime> getAssessmentTimes(String clinicalFlow) {
        if(StringUtil.isNotBlank(clinicalFlow)){
            OscaSkillsAssessmentTimeExample example = new OscaSkillsAssessmentTimeExample();
            example.createCriteria().andClinicalFlowEqualTo(clinicalFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            return skillsAssessmentTimeMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public OscaSkillsAssessmentTime readAssessmentTime(String recrodFlow) {
        return skillsAssessmentTimeMapper.selectByPrimaryKey(recrodFlow);
    }

    @Override
    public Map<String, String> queryOsaTime(String clinicalFlow) {
        return checkExtMapper.queryOsaTime(clinicalFlow);
    }

    @Override
    public List<String> queryOsaFirstTime(String clinicalFlow) {
        return checkExtMapper.queryOsaFirstTime(clinicalFlow);
    }

    @Override
    public List<Map<String,Object>> screenInfo(Map<String, Object> paramMap) {
        return checkExtMapper.screenInfo(paramMap);
    }
    @Override
    public void updateDoctorAssessment(String clinicalFlow, String doctorFlow,SysUser user) {
        OscaDoctorAssessment doctorAssessment=getOscaDoctorAssessment(clinicalFlow,doctorFlow);
        OscaSkillsAssessment skillAssess=getOscaSkillsAssessmentByFlow(clinicalFlow);
        //学员当前考核信息的所有考站信息
        List<OscaSkillRoomDoc> roomDocs=getOscaSkillRoomDocs(clinicalFlow,doctorFlow);
        boolean f=false;
        if(doctorAssessment!=null&&skillAssess!=null){
            if(roomDocs!=null&&roomDocs.size()>0) {
                OscaSubjectMain main = getOscaSubjectMain(skillAssess.getSubjectFlow());
                //总分
                Integer allScore=main.getAllScore();
                //每一部分的分数
                Map<String,Integer> partPassScoreMap=new HashMap<>();
                List<OscaSubjectPartScore> partScores=getOscaSubjectPartScores(skillAssess.getSubjectFlow());
                if(partScores!=null)
                {
                    for(OscaSubjectPartScore partScore:partScores)
                    {
                        partPassScoreMap.put(partScore.getPartFlow(),partScore.getPartScore());
                    }
                }
                //每一站的合格分
                Map<String,Integer> stationPassScoreMap=new HashMap<>();
                List<OscaSubjectStationScore> stationScores=getOscaSubjectStationScores(skillAssess.getSubjectFlow());
                if(stationScores!=null)
                {
                    for(OscaSubjectStationScore stationScore:stationScores)
                    {
                        stationPassScoreMap.put(stationScore.getStationFlow(),stationScore.getStationScore());
                    }
                }

                //获取考核方案下的站点
                List<OscaSubjectStation> stations = getOscaSubjectStations(skillAssess.getSubjectFlow());

                if (stations != null) {
                    //如果学员所有站点都没有考核完，直接是不通过
                    if (roomDocs.size() < stations.size()) {
                        doctorAssessment.setIsSavePass(GlobalConstant.FLAG_N);
                        doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
                        doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
                        f=true;
                    }else {
                        //每部分的站点数量
                        Map<String, List<OscaSubjectStation>> partMap = new HashMap<>();
                        for (OscaSubjectStation s : stations) {
                            List<OscaSubjectStation> temp = partMap.get(s.getPartFlow());
                            if (temp == null)
                                temp = new ArrayList<>();
                            temp.add(s);
                            partMap.put(s.getPartFlow(), temp);
                        }
                        //考核总分
                        double examAllScore = 0;
                        //考核分数
                        Map<String, Double> examScoreMap = new HashMap<>();
                        for (OscaSkillRoomDoc d : roomDocs) {
                            Double examScore = 0.0;
                            if (StringUtil.isNotBlank(d.getExamScore())) {
                                examScore=Double.valueOf(d.getExamScore());
                            }
                            examAllScore += examScore;
                            examScoreMap.put(d.getStationFlow(), examScore);
                        }
                        //如果合格总分配置了 并且 考核 总分小于合格总分 直接不通过
                        if(allScore!=null&&allScore>examAllScore)
                        {
                            doctorAssessment.setIsSavePass(GlobalConstant.FLAG_N);
                            doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
                            doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
                            f=true;
                        }else {
                            //有几部分
                            int partCount = partMap.size();
                            int partPassCount = 0;
                            if (partCount > 0) {
                                //计算每一部分的总分，考核总分，以及是否通过
                                for (String key : partMap.keySet()) {
                                    List<OscaSubjectStation> temp = partMap.get(key);
                                    double examAll = 0;
                                    for (OscaSubjectStation s : temp) {
                                        Double examScore = examScoreMap.get(s.getStationFlow());
                                        examAll += examScore;
                                    }
                                    Integer partPassScore=partPassScoreMap.get(key);
                                    //如果未配置 或 每一部分部分大于每一部分考核总分 就算合格
                                    if(partPassScore==null||examAll>=partPassScore)
                                    {
                                        partPassCount++;
                                    }
                                }
                                //所有部分都合格 则计算每一站是否都合格
                                if (partCount == partPassCount) {
                                    int stationPassCount=0;
                                    for (OscaSubjectStation s : stations) {
                                        Integer stationPassScore=stationPassScoreMap.get(s.getStationFlow());
                                        Double examScore = examScoreMap.get(s.getStationFlow());
                                        //如果未配置 或 每一部分部分大于每一部分考核总分 就算合格
                                        if(stationPassScore==null||examScore>=stationPassScore)
                                        {
                                            stationPassCount++;
                                        }
                                    }
                                    if(stationPassCount==stations.size()) {
                                        doctorAssessment.setIsSavePass(GlobalConstant.FLAG_Y);
                                        doctorAssessment.setIsPass(DoctorScoreEnum.Passed.getId());
                                        doctorAssessment.setIsPassName(DoctorScoreEnum.Passed.getName());
                                        f = true;
                                    }else{
                                        doctorAssessment.setIsSavePass(GlobalConstant.FLAG_N);
                                        doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
                                        doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
                                        f=true;
                                    }
                                }else{
                                    doctorAssessment.setIsSavePass(GlobalConstant.FLAG_N);
                                    doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
                                    doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
                                    f=true;
                                }
                            }
                        }

                    }
                }
            }
        }
        if(f){
            editOscaDoctorAssessment(doctorAssessment,user);
        }
    }

    private List<OscaSubjectStationScore> getOscaSubjectStationScores(String subjectFlow) {
        if(StringUtil.isNotBlank(subjectFlow))
        {
            OscaSubjectStationScoreExample example=new OscaSubjectStationScoreExample();
            OscaSubjectStationScoreExample.Criteria criteria =example.createCriteria();
            criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
            List<OscaSubjectStationScore> list=stationScoreMapper.selectByExample(example);
            return list;
        }
        return null;
    }

    private List<OscaSubjectPartScore> getOscaSubjectPartScores(String subjectFlow) {
        if(StringUtil.isNotBlank(subjectFlow))
        {
            OscaSubjectPartScoreExample example=new OscaSubjectPartScoreExample();
            OscaSubjectPartScoreExample.Criteria criteria =example.createCriteria();
            criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
            List<OscaSubjectPartScore> list=partScoreMapper.selectByExample(example);
            return list;
        }
        return null;
    }

    public void updateDoctorAssessmentOld(String clinicalFlow, String doctorFlow,SysUser user) {
        OscaDoctorAssessment doctorAssessment=getOscaDoctorAssessment(clinicalFlow,doctorFlow);
        OscaSkillsAssessment skillAssess=getOscaSkillsAssessmentByFlow(clinicalFlow);
        //学员当前考核信息的所有考站信息
        List<OscaSkillRoomDoc> roomDocs=getOscaSkillRoomDocs(clinicalFlow,doctorFlow);
        boolean f=false;
        if(doctorAssessment!=null&&skillAssess!=null){
            if(roomDocs!=null&&roomDocs.size()>0) {
                OscaSubjectMain main = getOscaSubjectMain(skillAssess.getSubjectFlow());
                //根据部分去计算是否通过考核
                //百分比取 main中的百分比
                Integer passPer=main.getPassPer();
                if(passPer==null||passPer==0)
                {
                    passPer=60;
                }
                List<OscaSubjectStation> stations = getOscaSubjectStations(skillAssess.getSubjectFlow());

                if (stations != null) {
                    if (roomDocs.size() < stations.size()) {
                        doctorAssessment.setIsSavePass(GlobalConstant.FLAG_N);
                        doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
                        doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
                        f=true;
                    }
                    //拆分部分 每个部分有几个站 总分
                    Map<String, Object> stationScoreMap = new HashMap<>();
                    Map<String,List<OscaSubjectStation>> partMap=new HashMap<>();
                    for (OscaSubjectStation s : stations) {
                        stationScoreMap.put(s.getStationFlow(), s.getStationScore());
                        List<OscaSubjectStation> temp=partMap.get(s.getPartFlow());
                        if(temp==null)
                            temp=new ArrayList<>();
                        temp.add(s);
                        partMap.put(s.getPartFlow(),temp);
                    }
                    //考核分数
                    Map<String,String> examScoreMap=new HashMap<>();
                    for (OscaSkillRoomDoc d : roomDocs) {
                        examScoreMap.put(d.getStationFlow(), d.getExamScore());
                    }
                    //有几部分
                    int partCount=partMap.size();
                    int partPassCount=0;
                    if(partCount>0){
                        //计算每一部分的总分，考核总分，以及是否通过
                        for(String key:partMap.keySet())
                        {
                            List<OscaSubjectStation> temp=partMap.get(key);
                            double scoreAll=0;
                            double examAll=0;
                            for(OscaSubjectStation s:temp)
                            {
                                int stationScore = (int) stationScoreMap.get(s.getStationFlow());
                                scoreAll+=stationScore;
                                String examScore=examScoreMap.get(s.getStationFlow());
                                if(StringUtil.isNotBlank(examScore))
                                {
                                    double score = Double.valueOf(examScore);
                                    examAll+=score;
                                }
                                else
                                {
                                    examAll+=0;
                                }
                            }
                            if(examAll!=0)
                            {
                                if(examAll>=scoreAll*passPer/100)
                                {
                                    partPassCount++;
                                }
                            }
                        }
                    }
                    //通过的部分为0
                    if(partPassCount==0)
                    {
                        doctorAssessment.setIsSavePass(GlobalConstant.FLAG_N);
                        doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
                        doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
                        f=true;
                    }else if(partCount==partPassCount)
                    {
                        doctorAssessment.setIsSavePass(GlobalConstant.FLAG_Y);
                        doctorAssessment.setIsPass(DoctorScoreEnum.Passed.getId());
                        doctorAssessment.setIsPassName(DoctorScoreEnum.Passed.getName());
                        f=true;
                    }else if(partCount>partPassCount)
                    {
                        doctorAssessment.setIsSavePass(GlobalConstant.FLAG_N);
                        doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
                        doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
                        f=true;
                    }
                }
            }
        }
        if(f){
            editOscaDoctorAssessment(doctorAssessment,user);
        }
    }
    public OscaDoctorAssessment getOscaDoctorAssessment(String clinicalFlow, String doctorFlow) {
        OscaDoctorAssessmentExample example=new OscaDoctorAssessmentExample();
        OscaDoctorAssessmentExample.Criteria criteria=example.createCriteria();
        criteria.andDoctorFlowEqualTo(doctorFlow).andClinicalFlowEqualTo(clinicalFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        List<OscaDoctorAssessment> list=odaMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return  list.get(0);
        }
        return null;
    }
    public OscaSkillsAssessment getOscaSkillsAssessmentByFlow(String clinicalFlow) {
        return osaMapper.selectByPrimaryKey(clinicalFlow);
    }
    public List<OscaSkillRoomDoc> getOscaSkillRoomDocs(String clinicalFlow, String doctorFlow) {
        OscaSkillRoomDocExample example=new OscaSkillRoomDocExample();
        OscaSkillRoomDocExample.Criteria criteria=example.createCriteria();
        criteria.andClinicalFlowEqualTo(clinicalFlow).andDoctorFlowEqualTo(doctorFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        return osrdMapper.selectByExample(example);
    }
    public OscaSubjectMain getOscaSubjectMain(String subjectFlow) {
        return osmMapper.selectByPrimaryKey(subjectFlow);
    }
    public List<OscaSubjectStation> getOscaSubjectStations(String subjectFlow) {
        OscaSubjectStationExample station=new OscaSubjectStationExample();
        OscaSubjectStationExample.Criteria criteria=station.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
        station.setOrderByClause(" ORDINAL ASC ");
        return ossMapper.selectByExample(station);
    }
    public int editOscaDoctorAssessment(OscaDoctorAssessment doctorAssessment, SysUser user) {
        if(doctorAssessment!=null){
            if(StringUtil.isNotBlank(doctorAssessment.getRecordFlow())){//修改
                doctorAssessment.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                doctorAssessment.setModifyUserFlow(user.getUserFlow());
                doctorAssessment.setModifyTime(DateUtil.getCurrDateTime());
                return this.odaMapper.updateByPrimaryKeySelective(doctorAssessment);
            }else{//新增
                doctorAssessment.setRecordFlow(PkUtil.getUUID());
                doctorAssessment.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                doctorAssessment.setCreateUserFlow(user.getUserFlow());
                doctorAssessment.setCreateTime(DateUtil.getCurrDateTime());
                doctorAssessment.setModifyUserFlow(user.getUserFlow());
                doctorAssessment.setModifyTime(DateUtil.getCurrDateTime());
                return this.odaMapper.insertSelective(doctorAssessment);
            }
        }
        return 0;
    }


    @Override
    public List<Map<String, Object>> screenSelect(Map<String, Object> paramMap) {
        return checkExtMapper.screenSelect(paramMap);
    }

    @Override
    public OscaExamDifferScore readDiffierScoreByOrgFlow(String orgFlow) {
        if(StringUtil.isNotBlank(orgFlow)) {
            OscaExamDifferScoreExample example = new OscaExamDifferScoreExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andOrgFlowEqualTo(orgFlow);
            List<OscaExamDifferScore> list=differScoreMapper.selectByExample(example);
            if(list!=null&&list.size()>0)
            {
                return list.get(0);
            }
        }
        return null;
    }

}
