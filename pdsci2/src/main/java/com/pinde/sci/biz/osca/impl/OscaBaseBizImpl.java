package com.pinde.sci.biz.osca.impl;

import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.osca.*;
import com.pinde.core.common.enums.pub.UserSexEnum;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.common.enums.sys.CertificateTypeEnum;
import com.pinde.core.common.sci.dao.SysRoleMapper;
import com.pinde.core.common.sci.dao.SysUserMapper;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.osca.IOscaBaseBiz;
import com.pinde.sci.biz.osca.IOscaDoctorOrderdeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.osca.OscaCheckInfoExtMapper;
import com.pinde.sci.dao.osca.OscaSkillsAssessmentExtMapper;
import com.pinde.sci.dao.sys.SysUserExtMapper;
import com.pinde.core.model.OscaDoctorAssessment;
import com.pinde.core.model.OscaDoctorAssessmentExample;
import com.pinde.core.model.OscaExamDifferScore;
import com.pinde.core.model.OscaExamDifferScoreExample;
import com.pinde.core.model.OscaOrgSpe;
import com.pinde.core.model.OscaOrgSpeExample;
import com.pinde.core.model.OscaSkillDocScore;
import com.pinde.core.model.OscaSkillDocScoreExample;
import com.pinde.core.model.OscaSkillDocStation;
import com.pinde.core.model.OscaSkillDocStationExample;
import com.pinde.core.model.OscaSkillRoom;
import com.pinde.core.model.OscaSkillRoomDoc;
import com.pinde.core.model.OscaSkillRoomDocExample;
import com.pinde.core.model.OscaSkillRoomExample;
import com.pinde.core.model.OscaSkillRoomTea;
import com.pinde.core.model.OscaSkillRoomTeaExample;
import com.pinde.core.model.OscaSkillsAssessment;
import com.pinde.core.model.OscaSkillsAssessmentTime;
import com.pinde.core.model.OscaSkillsAssessmentTimeExample;
import com.pinde.core.model.OscaSubjectMain;
import com.pinde.core.model.OscaSubjectMainExample;
import com.pinde.core.model.OscaSubjectPartScore;
import com.pinde.core.model.OscaSubjectPartScoreExample;
import com.pinde.core.model.OscaSubjectStation;
import com.pinde.core.model.OscaSubjectStationExample;
import com.pinde.core.model.OscaSubjectStationScore;
import com.pinde.core.model.OscaSubjectStationScoreExample;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.SysUserRole;
import com.pinde.core.model.SysUserRoleExample;
import com.pinde.sci.model.osca.OscaCheckInfoExt;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
//@Transactional(rollbackFor = Exception.class)
public class OscaBaseBizImpl implements IOscaBaseBiz{

    private static final Logger logger = LoggerFactory.getLogger(OscaBaseBizImpl.class);


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
    private OscaSkillDocStationMapper docStationMapper;
    @Autowired
    private OscaSkillRoomDocMapper osrdMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserExtMapper userExtMapper;
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
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
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
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IInxBiz iInxBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private ResDoctorSkillMapper doctorSkillMapper;

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
    public int saveCheckInfo(OscaSkillsAssessment osa) {
        if (StringUtil.isNotBlank(osa.getClinicalFlow())) {
            GeneralMethod.setRecordInfo(osa, false);
            return osaMapper.updateByPrimaryKeySelective(osa);
        } else {
            osa.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            osa.setOrgName(GlobalContext.getCurrentUser().getOrgName());
            osa.setIsShow(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);//院内考核成绩或者结业考核成绩 默认不对学员公开
            osa.setIsReleased(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);//默认未发布
            osa.setIsGradeReleased(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            String clinicalFlow = PkUtil.getUUID();
            osa.setClinicalFlow(clinicalFlow);
            osa.setCodeInfo("func://funcFlow=queryQrCode&clinicalFlow=" + clinicalFlow);//扫描调用queryQrCode方法参数clinicalFlow
            GeneralMethod.setRecordInfo(osa, true);
            return osaMapper.insert(osa);
        }
    }

    @Override
    public int saveCheckInfoNew(OscaSkillsAssessment osa, List<OscaSkillsAssessmentTime> timeList) {
        if (StringUtil.isNotBlank(osa.getClinicalFlow())) {
            GeneralMethod.setRecordInfo(osa, false);
            if (timeList!=null&&timeList.size()>0){
                for (OscaSkillsAssessmentTime time:timeList) {
                    String examStartTime=time.getExamStartTime();
                    String examEndTime=time.getExamEndTime();
                    String testNum=time.getTestNumber();
                    if(StringUtil.isBlank(time.getRecrodFlow())){
                        time.setClinicalFlow(osa.getClinicalFlow());
                        time.setRecrodFlow(PkUtil.getUUID());
                        GeneralMethod.setRecordInfo(time, true);
                        timeMapper.insert(time);//插入考核时间表
                        List<String> doctorFlowList=queryDoctorFlowList(osa.getClinicalFlow(),testNum);//某一时间段内容量内学员Flow
                        if(doctorFlowList!=null&&doctorFlowList.size()>0){
                            OscaDoctorAssessment oda=new OscaDoctorAssessment();
                            oda.setExamStartTime(examStartTime);
                            oda.setExamEndTime(examEndTime);
                            OscaDoctorAssessmentExample example=new OscaDoctorAssessmentExample();
                            OscaDoctorAssessmentExample.Criteria criteria=example.createCriteria();
                            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                                    .andDoctorFlowIn(doctorFlowList).andClinicalFlowEqualTo(osa.getClinicalFlow());
                            odaMapper.updateByExampleSelective(oda,example);//为学员添加考核时间
                        }
                    }
                }
            }
            return osaMapper.updateByPrimaryKeySelective(osa);
        } else {
            osa.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            osa.setOrgName(GlobalContext.getCurrentUser().getOrgName());
            osa.setIsShow(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);//院内考核成绩或者结业考核成绩 默认不对学员公开
            osa.setIsReleased(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);//默认未发布
            osa.setIsGradeReleased(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            String clinicalFlow = PkUtil.getUUID();
            osa.setClinicalFlow(clinicalFlow);
            osa.setCodeInfo("func://funcFlow=queryQrCode&clinicalFlow=" + clinicalFlow);//扫描调用queryQrCode方法参数clinicalFlow
            GeneralMethod.setRecordInfo(osa, true);
            return osaMapper.insert(osa);
        }
    }

    @Override
    public List<OscaSubjectMain> querySpeRelation(String speId, String actionTypeId) {
        OscaSubjectMainExample example = new OscaSubjectMainExample();
        OscaSubjectMainExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andIsReleasedEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(speId)){
            criteria.andTrainingSpeIdEqualTo(speId);
        }
        if(AssessmentProEnum.HospitalPlan.getId().equals(actionTypeId)){
            criteria.andActionTypeIdEqualTo(actionTypeId).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
        }else if(AssessmentProEnum.ProvincialPlan.getId().equals(actionTypeId)){
            criteria.andActionTypeIdEqualTo(actionTypeId);
        }
        return osmMapper.selectByExample(example);
    }

    @Override
    public List<OscaOrgSpe> queryInitSpe(String orgFlow) {
        OscaOrgSpeExample example = new OscaOrgSpeExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow);
        return oosMapper.selectByExample(example);
    }

    @Override
    public int releasedInfo(String clinicalFlow) {
        OscaSkillsAssessment old =getOscaSkillsAssessmentByFlow(clinicalFlow);
        OscaSkillsAssessment osa = new OscaSkillsAssessment();
        osa.setIsReleased(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
        osa.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        if (StringUtil.isNotBlank(isReleased) && isReleased.equals(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)) {//已预约学员信息均被删除（此处考核开始时间没到）
            OscaDoctorAssessment oda = new OscaDoctorAssessment();
            oda.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            OscaDoctorAssessmentExample example = new OscaDoctorAssessmentExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andClinicalFlowEqualTo(clinicalFlow);
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
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
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
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andAuditStatusIdEqualTo(AuditStatusEnum.Passed.getId())
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
        int c= odaMapper.updateByPrimaryKeySelective(oda);
        if(c==1)
        {
            oda=getOscaDoctorAssessmentByFlow(recordFlow);
            OscaSkillsAssessment skillsAssessment=getOscaSkillsAssessmentByFlow(oda.getClinicalFlow());
            List<OscaSubjectStation> stations=getOscaSubjectStations(skillsAssessment.getSubjectFlow());
            List<OscaSkillDocStation> docStations=new ArrayList<>();
            for(OscaSubjectStation station:stations)
            {
                OscaSkillDocStation docStation=getDocSkillStation(station.getStationFlow(),oda.getDoctorFlow(),oda.getClinicalFlow());
                if(docStation==null)
                    docStation=new OscaSkillDocStation();
                if (!com.pinde.core.common.enums.ExamStatusEnum.AssessIng.getId().equals(docStation.getExamStatusId()) &&
                        !com.pinde.core.common.enums.ExamStatusEnum.Assessment.getId().equals(docStation.getExamStatusId()))
                {
                    docStation.setClinicalFlow(oda.getClinicalFlow());
                    docStation.setClinicalName(skillsAssessment.getClinicalName());
                    docStation.setStationFlow(station.getStationFlow());
                    docStation.setStationName(station.getStationName());
                    docStation.setDoctorFlow(oda.getDoctorFlow());
                    docStation.setDoctorName(oda.getDoctorName());
                    String date=DateUtil.getCurrDateTime2();
                    docStation.setHoukaoTime(date);
                    docStation.setWaitingTime(date);
                    docStation.setExamStatusId(com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getId());
                    docStation.setExamStatusName(com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getName());
                    docStation.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    docStations.add(docStation);
                }
            }
            saveDocSkillStations(docStations);

        }
        return c;
    }

    private OscaDoctorAssessment getOscaDoctorAssessmentByFlow(String recordFlow) {
        return odaMapper.selectByPrimaryKey(recordFlow);
    }

    public void saveDocSkillStations(List<OscaSkillDocStation> docStations) {
        if(docStations!=null)
        {
            for(OscaSkillDocStation docStation:docStations)
            {
                saveDocStation(docStation);
            }
        }
    }
    private OscaSkillDocStation getDocSkillStation(String stationFlow, String doctorFlow, String clinicalFlow) {
        OscaSkillDocStationExample example=new OscaSkillDocStationExample();

        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andStationFlowEqualTo(stationFlow)
                .andDoctorFlowEqualTo(doctorFlow).andClinicalFlowEqualTo(clinicalFlow);
        List<OscaSkillDocStation> files=docStationMapper.selectByExample(example);
        if(files!=null&&files.size()>0)
        {
            return files.get(0);
        }
        return null;
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
        example.createCriteria().andSubjectFlowEqualTo(subjectFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("ORDINAL");
        return ossMapper.selectByExample(example);
    }

    @Override
    public OscaSkillRoom queryRoomByFlow(String recordFlow) {
        return osrMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<SysUser> queryTeaList(String userName) {
        String examTeaRole= InitConfig.getSysCfg("osca_examtea_role_flow");
        return userExtMapper.queryTeaList(examTeaRole,GlobalContext.getCurrentUser().getOrgFlow());
//        SysUserExample example = new SysUserExample();
//        SysUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
//                .andIsExamTeaEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
//        if(StringUtil.isNotBlank(userName)){
//            criteria.andUserNameLike("%"+userName+"%");
//        }
//        return userMapper.selectByExample(example);
    }

    @Override
    public List<OscaSkillRoomTea> queryRoomTeaList(String recordFlow) {
        OscaSkillRoomTeaExample example = new OscaSkillRoomTeaExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andRoomRecordFlowEqualTo(recordFlow);
        return osrtMapper.selectByExample(example);
    }

    @Override
    public int saveRoom(OscaSkillRoom room,Map<String,Object> map) {
        int num = 0;
        if (StringUtil.isNotBlank(room.getRecordFlow())) {
            if(null != map){
                List<String> teaFlowList = (List<String>)map.get("teaFlowLst");
                Map<String,Object> param = new HashMap<>();
                param.put("clinicalFlow",room.getClinicalFlow());
                param.put("stationFlow",room.getStationFlow());
                param.put("statusId", ScoreStatusEnum.Save.getId());
                param.put("roomRecordFlow",room.getRecordFlow());
                param.put("teaFlowList",teaFlowList);
                if(checkExtMapper.querySignNoSubScoreNum(param) > 0){
                    return -1;//考官存在 已签到（扫描） 的学生
                }
            }
            GeneralMethod.setRecordInfo(room, false);
            num = osrMapper.updateByPrimaryKeySelective(room);
        } else {
            OscaSkillRoomExample example = new OscaSkillRoomExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andClinicalFlowEqualTo(room.getClinicalFlow()).andRoomFlowEqualTo(room.getRoomFlow())
                    .andStationFlowEqualTo(room.getStationFlow());
            int count = osrMapper.countByExample(example);
            if(count > 0){//同考核信息下同考站考场 不可维护多次
                return 0;
            }
            room.setRecordFlow(PkUtil.getUUID());//新增考场流水号
            GeneralMethod.setRecordInfo(room, true);
            num = osrMapper.insert(room);
        }
        if(null != map && num > 0){
            List<String> teaFlowList = (List<String>)map.get("teaFlowLst");
            List<String> teaNameList = (List<String>)map.get("teaNameLst");
            if(null != teaFlowList && teaFlowList.size() > 0){
                OscaSkillRoomTea osrt = new OscaSkillRoomTea();
                osrt.setRoomRecordFlow(room.getRecordFlow());
                osrt.setRoomFlow(room.getRoomFlow());
                osrt.setRoomName(room.getRoomName());
                osrt.setClinicalFlow(room.getClinicalFlow());
                osrt.setClinicalName(room.getClinicalName());
                osrt.setStationFlow(room.getStationFlow());
                osrt.setStationName(room.getStationName());
                //伪删除考核信息下某考场已存在的考官
                OscaSkillRoomTea record = new OscaSkillRoomTea();
                record.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
                OscaSkillRoomTeaExample example = new OscaSkillRoomTeaExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andRoomRecordFlowEqualTo(osrt.getRoomRecordFlow());
                osrtMapper.updateByExampleSelective(record,example);
                GeneralMethod.setRecordInfo(osrt, true);
                for(int i=0;i<teaFlowList.size();i++){
                    osrt.setRecordFlow(PkUtil.getUUID());
                    osrt.setPartnerFlow(teaFlowList.get(i));
                    osrt.setPartnerName(teaNameList.get(i));
                    osrtMapper.insertSelective(osrt);
                }
                room.setRecordFlow("");//room为同一个对象，此处防止下次新增考场时当作编辑考场；而编辑考场只会循环一次，并不会当作下次新增考场
            }
        }
        return num;
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
            osrt.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            OscaSkillRoomTeaExample osrtExample = new OscaSkillRoomTeaExample();
            if(StringUtil.isBlank(recordFlow)){//清空考场
                OscaSkillRoom osr = new OscaSkillRoom();
                osr.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
                OscaSkillRoomExample example = new OscaSkillRoomExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andClinicalFlowEqualTo(clinicalFlow);
                num = osrMapper.updateByExampleSelective(osr,example);
                osrtExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andClinicalFlowEqualTo(clinicalFlow);
            }else{//删除单考场
                OscaSkillRoom osr = new OscaSkillRoom();
                osr.setRecordFlow(recordFlow);
                osr.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
                num = osrMapper.updateByPrimaryKeySelective(osr);
                osrtExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
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
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andClinicalFlowEqualTo(clinicalFlow).andAuditStatusIdEqualTo(AuditStatusEnum.Passed.getId())
                .andSiginStatusIdEqualTo(SignStatusEnum.SignIn.getId());
        return odaMapper.selectByExample(example);
    }

    @Override
    public int isShowOpt(String clinicalFlow, String isShow, String flag) {
        OscaSkillsAssessment osa = new OscaSkillsAssessment();
        osa.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        osa.setClinicalFlow(clinicalFlow);
        if("form".equals(flag)){
            osa.setIsShowFroom(isShow);
        }else{
            if("grade".equals(isShow)){//成绩发布
                osa.setIsGradeReleased(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            }
            else if("school".equals(isShow)){
                osa.setIsGradeReleased("S");
            }
            else if("city".equals(isShow)){
                osa.setIsGradeReleased("C");
            }
            else if("province".equals(isShow)){
                osa.setIsGradeReleased(com.pinde.core.common.GlobalConstant.FLAG_Y);
                osa.setIsShow(com.pinde.core.common.GlobalConstant.FLAG_Y);
            }
            else{//学生能否查看成绩
                osa.setIsShow(isShow);
            }
            if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(isShow)) {
                osa.setIsShowFroom(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            }
        }
        return osaMapper.updateByPrimaryKeySelective(osa);
    }

    @Override
    public Map<String, Object> queryDoctorGrade(Map<String, String> param) {
        return checkExtMapper.queryDoctorGrade(param);
    }

    @Override
    public int saveGrade(OscaSkillRoomDoc osrd) {
        int num = 0;
        if (StringUtil.isNotBlank(osrd.getDocRoomFlow())) {
            GeneralMethod.setRecordInfo(osrd, false);
            return osrdMapper.updateByPrimaryKeySelective(osrd);
        } else {
            osrd.setDocRoomFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(osrd, true);
            return osrdMapper.insert(osrd);
        }
    }

    @Override
    public int updateIsPass(String clinicalFlow, String doctorFlow, String resultId) {
        OscaDoctorAssessment oda = new OscaDoctorAssessment();
        oda.setIsPass(resultId);
        if("Passed".equals(resultId)){
            oda.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_Y);
        }
        oda.setIsPassName(DoctorScoreEnum.getNameById(resultId));
        OscaDoctorAssessmentExample example = new OscaDoctorAssessmentExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andClinicalFlowEqualTo(clinicalFlow).andDoctorFlowEqualTo(doctorFlow);
        return odaMapper.updateByExampleSelective(oda,example);
    }

    @Override
    public int importGradeExcel(MultipartFile file,String clinicalFlow) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseExcel(wb, clinicalFlow);
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
//        return 0;
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
    private int parseExcel(Workbook wb,String clinicalFlow) {
        int count = 0;
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum>0){
            List<String> colnames = new ArrayList<String>();
            Sheet sheet;
            try{
                sheet = wb.getSheetAt(0);
            }catch(Exception e){
                sheet = wb.getSheetAt(0);
            }
            int row_num = sheet.getLastRowNum()+1;
            if(row_num==1){
                throw new RuntimeException("导入文件内容为空！");
            }
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            String title = "";
            for(int i = 0 ; i <cell_num; i++){
                title = titleR.getCell(i).getStringCellValue();
                colnames.add(title);
            }
            List<Map<String,Object>> stationList = checkExtMapper.queryStationByFlow(clinicalFlow);
            for(int i = 1;i <row_num; i++){
                Row r =  sheet.getRow(i);
                String ticketNumber = "";
                String doctorName = "";
                List<String> stationFlow = new ArrayList<>();//存储excel录入成绩的考站流水号
                List<String> stationName = new ArrayList<>();//存储excel录入成绩的考站
                List<String> stationScore = new ArrayList<>();//存储excel录入成绩的考站成绩
                List<String> setStationScore = new ArrayList<>();//存储excel录入成绩的考站设置的成绩
                for(int j = 0; j < colnames.size(); j++){
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
                        if(cell.getCellType().getCode() == 1){
                            value = cell.getStringCellValue().trim();
                        }else{
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    String currTitle=colnames.get(j);
                    if("准考证号".equals(currTitle)){
                        ticketNumber = value;
                    }
                    if("姓名".equals(currTitle)){
                        doctorName = value;
                    }
                    if(j > 5 && (j-6)<stationList.size() && currTitle.equals(stationList.get(j-6).get("STATION_NAME")) && StringUtil.isNotBlank(value)){//只处理有填成绩的站
                        stationFlow.add(stationList.get(j-6).get("STATION_FLOW").toString());
                        stationName.add(stationList.get(j-6).get("STATION_NAME").toString());
                        stationScore.add(value);
                        setStationScore.add(String.valueOf(stationList.get(j-6).get("STATION_SCORE")));
                    }
                }
                if(StringUtil.isBlank(ticketNumber)){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，准考证号不能为空！");
                }
                if(StringUtil.isBlank(doctorName)){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，姓名不能为空！");
                }
                //当前考核信息下通过准考证号及姓名判断此人是否存在
                OscaDoctorAssessmentExample example = new OscaDoctorAssessmentExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andClinicalFlowEqualTo(clinicalFlow).andTicketNumberEqualTo(ticketNumber)
                        .andDoctorNameEqualTo(doctorName);
                List<OscaDoctorAssessment> exitDoctor = odaMapper.selectByExample(example);
                if(null != exitDoctor && exitDoctor.size() > 0){
                    int num = 0;int passCount = 0;
                    if(stationFlow.size() > 0){
                        Map<String,String> param = new HashMap<>();
                        param.put("clinicalFlow",clinicalFlow);
                        param.put("doctorFlow",exitDoctor.get(0).getDoctorFlow());
                        Map<String,Object> doctorMap = checkExtMapper.queryDoctorGrade(param);
                        String stationFlowStr = (String)doctorMap.get("STATION_FLOW");
                        List<String> exitScoreStation = new ArrayList<>();
                        List<String> exitScoreDocRoomFlow = new ArrayList<>();
                        if(null != stationFlowStr && stationFlowStr.split(",").length > 0) {
                            exitScoreStation = Arrays.asList(stationFlowStr.split(","));
                            exitScoreDocRoomFlow = Arrays.asList(((String) doctorMap.get("DOC_ROOM_FLOW")).split(","));
                        }
                        for(int k = 0;k < stationFlow.size(); k++){
                            OscaSkillRoomDoc roomDoc = new OscaSkillRoomDoc();
                            if(exitScoreStation.contains(stationFlow.get(k))){//修改
                                roomDoc.setDocRoomFlow(exitScoreDocRoomFlow.get(exitScoreStation.indexOf(stationFlow.get(k))));
                                roomDoc.setExamScore(stationScore.get(k));
                                roomDoc.setExamSaveScore(stationScore.get(k));
                                roomDoc.setIsAdminAudit(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                            }else{
                                roomDoc.setDoctorFlow(exitDoctor.get(0).getDoctorFlow());
                                roomDoc.setDoctorName(exitDoctor.get(0).getDoctorName());
                                roomDoc.setRoomRecordFlow("Unknown");
                                roomDoc.setRoomFlow("Unknown");
                                roomDoc.setRoomName("Unknown");
                                roomDoc.setClinicalFlow(clinicalFlow);
                                roomDoc.setClinicalName(exitDoctor.get(0).getClinicalName());
                                roomDoc.setStationFlow(stationFlow.get(k));
                                roomDoc.setStationName(stationName.get(k));
                                roomDoc.setExamStatusId(com.pinde.core.common.enums.ExamStatusEnum.Assessment.getId());
                                roomDoc.setExamStatusName(com.pinde.core.common.enums.ExamStatusEnum.Assessment.getName());
                                roomDoc.setExamScore(stationScore.get(k));
                                roomDoc.setExamSaveScore(stationScore.get(k));
                                roomDoc.setIsAdminAudit(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                            }
                            if(Double.valueOf(stationScore.get(k)) > Double.valueOf(setStationScore.get(k))){
                                throw new RuntimeException("导入失败！第"+ (count+2) +"行，导入分数超过考站标准分！");
                            }
                            num += saveGrade(roomDoc);
                            if(stationList.size() > 0 && Double.valueOf(stationScore.get(k)) * 100 / Double.valueOf(setStationScore.get(k)) >= Double.valueOf(stationList.get(0).get("PASS_PER").toString())){//合格，超过考站分的60%
                                passCount ++;
                            }
                        }
                    }
                    if(num <= 0){
                        continue;
                    }
                    updateDoctorAssessment(clinicalFlow,exitDoctor.get(0).getDoctorFlow(),GlobalContext.getCurrentUser());
//                    OscaDoctorAssessment oda = new OscaDoctorAssessment();
//                    if(colnames.size()-6 == passCount){//所有考站全都合格
//                        oda.setIsPass(DoctorScoreEnum.Passed.getId());
//                        oda.setIsPassName(DoctorScoreEnum.Passed.getName());
//                    }else{
//                        oda.setIsPass(DoctorScoreEnum.UnPassed.getId());
//                        oda.setIsPassName(DoctorScoreEnum.UnPassed.getName());
//                    }
//                    odaMapper.updateByExampleSelective(oda,example);
                }else{
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，此学员不存在！");
                }
                count ++;
            }
        }
        return count;
    }

    @Override
    public int importStudents(MultipartFile file,String clinicalFlow,String examStartTime,String examEndTime,String isNewTime) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseExcel2(wb,clinicalFlow,examStartTime,examEndTime,isNewTime);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
    private int parseExcel2(Workbook wb,String clinicalFlow,String examStartTime,String examEndTime,String isNewTime) throws Exception{
        int count = 0;//导入记录数
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum > 0){
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum()+1;
            if(row_num==1){
                throw new Exception("导入文件内容为空！");
            }
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            List<String> colnames = new ArrayList<String>();
            for(int i = 0 ; i <cell_num; i++){
                colnames.add(titleR.getCell(i).getStringCellValue());
            }
            for(int i = 1;i < row_num; i++) {
                SysUser user = new SysUser();//建立bean
                ResDoctor doctor = new ResDoctor();
                doctor.setOscaStudentSubmit(com.pinde.core.common.GlobalConstant.FLAG_Y);
                OscaDoctorAssessment assessment = new OscaDoctorAssessment();

                Row r = sheet.getRow(i);
                for(int j = 0; j < colnames.size(); j++){
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
                        if(cell.getCellType().getCode() == 1){
                            value = cell.getStringCellValue().trim();
                        }else{
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    String currTitle = colnames.get(j);
                    if("姓名".equals(currTitle)){
                        user.setUserName(value);
                    }
                    if("性别".equals(currTitle)){
                        if("男".equals(value)){
                            user.setSexId("Man");
                        }else if("女".equals(value)){
                            user.setSexId("Woman");
                        }
                        user.setSexName(value);
                    }
                    if("证件号码".equals(currTitle)){
                        user.setIdNo(value);
                        user.setUserCode(value);
                    }
                    if("联系方式".equals(currTitle)){
                        user.setUserPhone(value);
                    }
                    if("培训届别".equals(currTitle)){
                        doctor.setSessionNumber(value);
                    }
                    if("培训基地".equals(currTitle)){
                        doctor.setOrgName(value);
                        user.setOrgName(value);
                    }
                }
                String regEx = "^1[3|4|5|6|7|8|9][0-9]\\d{8}$";
                if(StringUtil.isBlank(user.getUserName())){
                    throw new Exception("导入失败！第"+ (count+2) +"行姓名不能为空！");
                }
                if(StringUtil.isBlank(user.getSexName())){
                    throw new Exception("导入失败！第"+ (count+2) +"行性别不能为空！");
                }
                if(StringUtil.isBlank(user.getIdNo())){
                    throw new Exception("导入失败！第"+ (count+2) +"行身份证号不能为空！");
                }
//                String isIDCard1="^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
                String isSessionNumber="^\\d{4}$";
                if(!(doctor.getSessionNumber().matches(isSessionNumber))){
                    throw new Exception("导入失败！第"+ (count+2) +"行届别有误！");
                }
                if(StringUtil.isBlank(user.getUserPhone())){
                    throw new Exception("导入失败！第"+ (count+2) +"行手机号码不能为空！");
                }
                if(!user.getUserPhone().matches(regEx)){
                    throw new Exception("导入失败！第"+ (count+2) +"行手机号码有误！");
                }
                if(StringUtil.isBlank(doctor.getSessionNumber())){
                    throw new Exception("导入失败！第"+ (count+2) +"培训届别不能为空！");
                }
                if(StringUtil.isBlank(doctor.getOrgName())){
                    throw new Exception("导入失败！第"+ (count+2) +"培训基地不能为空！");
                }else {
                    SysOrg org=orgBiz.readSysOrgByName(doctor.getOrgName());
                    if(org==null)
                        throw new RuntimeException("导入失败！第"+ count+2 +"行，机构名称输入不正确！");
                    user.setOrgFlow(org.getOrgFlow());
                    doctor.setDoctorFlow(org.getOrgFlow());
                }

                SysUserExample example = new SysUserExample();
                example.createCriteria().andIdNoEqualTo(user.getIdNo());
                int num = userMapper.countByExample(example);
                if(num <= 0){//新增
                    user.setUserFlow(PkUtil.getUUID());//新增user表
                    user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(),"123456"));
                    user.setIsOsca(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    user.setStatusId(UserStatusEnum.Activated.getId());
                    GeneralMethod.setRecordInfo(user,true);
                    userMapper.insertSelective(user);

                    OscaSkillsAssessment skillsAssessment = queryDataByFlow(clinicalFlow);
                    String speId = skillsAssessment.getSpeId();
                    String speName = skillsAssessment.getSpeName();

                    doctor.setDoctorFlow(user.getUserFlow());//新增resDoctor表
                    doctor.setDoctorName(user.getUserName());
                    doctor.setTrainingSpeId(speId);
                    doctor.setTrainingSpeName(speName);
                    GeneralMethod.setRecordInfo(doctor,true);
                    doctorMapper.insertSelective(doctor);

                    assessment.setRecordFlow(PkUtil.getUUID());//新增OSCA_DOCTOR_ASSESSMENT表
                    assessment.setClinicalFlow(clinicalFlow);
                    assessment.setClinicalName(skillsAssessment.getClinicalName());
                    assessment.setClinicalYear(skillsAssessment.getClinicalYear());
                    assessment.setDoctorFlow(user.getUserFlow());
                    assessment.setDoctorName(user.getUserName());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    assessment.setAppointTime(sdf.format(new Date()));
                    assessment.setAuditStatusId(AuditStatusEnum.Passed.getId());
                    assessment.setAuditStatusName(AuditStatusEnum.Passed.getName());
                    assessment.setIsPass(DoctorScoreEnum.Miss.getId());
                    assessment.setIsPassName(DoctorScoreEnum.Miss.getName());
                    assessment.setExamStartTime(examStartTime);
                    assessment.setExamEndTime(examEndTime);
                    GeneralMethod.setRecordInfo(assessment,true);
                    odaMapper.insertSelective(assessment);
                    auditAppoint(assessment.getRecordFlow(),AuditStatusEnum.Passed.getId(),null);
                }else{
                    SysUser user1 = userBiz.findByIdNo(user.getIdNo());
                    if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(user1.getIsOsca())) {
                        throw new Exception("导入失败！第" + (count + 2) + "行身份证号" + user.getIdNo() + "用户在系统中已存在！");
                    }else{
                        user1.setUserName(user.getUserName());//更新USER
                        user1.setSexName(user.getSexName());
                        user1.setSexId(user.getSexId());
                        user1.setUserPhone(user.getUserPhone());
                        userMapper.updateByPrimaryKeySelective(user1);

                        OscaSkillsAssessment skillsAssessment = queryDataByFlow(clinicalFlow);
                        String speId = skillsAssessment.getSpeId();
                        String speName = skillsAssessment.getSpeName();

                        ResDoctor doctor1 = doctorBiz.readDoctor(user1.getUserFlow());//更新DOCTOR
                        doctor1.setTrainingSpeId(speId);
                        doctor1.setTrainingSpeName(speName);
                        doctor1.setSessionNumber(doctor.getSessionNumber());
                        doctor1.setOrgName(doctor.getOrgName());
                        doctorMapper.updateByPrimaryKeySelective(doctor1);

                        //同一场考试更新，不同场次新增
                        OscaDoctorAssessment doctorAssessment = new OscaDoctorAssessment();
                        doctorAssessment.setDoctorFlow(user1.getUserFlow());
                        doctorAssessment.setClinicalFlow(clinicalFlow);
                        List<OscaDoctorAssessment> doctorAssessments = doctorOrderdeBiz.selectDoctorAssessment(doctorAssessment);
                        if(doctorAssessments.size()>0&&doctorAssessments!=null){//更新OSCA_DOCTOR_ASSESSMENT表
                            assessment.setRecordFlow(doctorAssessments.get(0).getRecordFlow());
                            assessment.setDoctorName(user1.getUserName());
                            assessment.setExamStartTime(examStartTime);
                            assessment.setExamEndTime(examEndTime);
                            GeneralMethod.setRecordInfo(assessment,false);
                            odaMapper.updateByPrimaryKeySelective(assessment);
                        }else{
                            assessment.setRecordFlow(PkUtil.getUUID());//新增OSCA_DOCTOR_ASSESSMENT表
                            assessment.setClinicalFlow(clinicalFlow);
                            assessment.setClinicalName(skillsAssessment.getClinicalName());
                            assessment.setClinicalYear(skillsAssessment.getClinicalYear());
                            assessment.setDoctorFlow(user1.getUserFlow());
                            assessment.setDoctorName(user1.getUserName());
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            assessment.setAppointTime(sdf.format(new Date()));
                            assessment.setAuditStatusId(AuditStatusEnum.Passed.getId());
                            assessment.setAuditStatusName(AuditStatusEnum.Passed.getName());
                            assessment.setIsPass(DoctorScoreEnum.Miss.getId());
                            assessment.setExamStartTime(examStartTime);
                            assessment.setExamEndTime(examEndTime);
                            GeneralMethod.setRecordInfo(assessment,true);
                            odaMapper.insertSelective(assessment);
                            auditAppoint(assessment.getRecordFlow(),AuditStatusEnum.Passed.getId(),null);
                        }
                    }
                }
                count ++;
            }
            //更新OSCA_SKILLS_ASSESSMENT_TIME表
            OscaSkillsAssessmentTime skillsAssessmentTime = new OscaSkillsAssessmentTime();
            if (isNewTime.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                OscaSkillsAssessment skillsAssessment = queryDataByFlow(clinicalFlow);//开始时间不能在预约结束时间之前
                String appointEndTime = skillsAssessment.getAppointEndTime();
                if(appointEndTime.compareTo(examStartTime)>=0){
                    throw new Exception("导入失败！考核时间不能在预约结束时间之前");
                }
                List<OscaSkillsAssessmentTime> oscaSkillsAssessmentTimeList = queryOsaTimeList(clinicalFlow);//考试时间相互不能冲突
                if(oscaSkillsAssessmentTimeList.size()>0&&oscaSkillsAssessmentTimeList!=null){
                    for(OscaSkillsAssessmentTime oscaSkillsAssessmentTime:oscaSkillsAssessmentTimeList){
                        String startTime = oscaSkillsAssessmentTime.getExamStartTime();
                        String endTime = oscaSkillsAssessmentTime.getExamEndTime();
                        if(!
                                ((examEndTime.compareTo(startTime)<0)
                            ||(examStartTime.compareTo(endTime)>0))
                                ){
                            throw new Exception("导入失败！考核时间冲突");
                        }
                    }
                }
                skillsAssessmentTime.setRecrodFlow(PkUtil.getUUID());
                skillsAssessmentTime.setClinicalFlow(clinicalFlow);
                skillsAssessmentTime.setExamStartTime(examStartTime);
                skillsAssessmentTime.setExamEndTime(examEndTime);
                skillsAssessmentTime.setTestNumber(String.valueOf(count));
                GeneralMethod.setRecordInfo(skillsAssessmentTime,true);
                skillsAssessmentTimeMapper.insertSelective(skillsAssessmentTime);
            }else {
                skillsAssessmentTime.setRecrodFlow(isNewTime);
                String oldNumber = readAssessmentTime(isNewTime).getTestNumber();
                int newNumber = Integer.parseInt(oldNumber)+count;
                skillsAssessmentTime.setTestNumber(String.valueOf(newNumber));
                GeneralMethod.setRecordInfo(skillsAssessmentTime,false);
                skillsAssessmentTimeMapper.updateByPrimaryKeySelective(skillsAssessmentTime);
            }
        }
        return count;
    }
    @Override
    public List<OscaSkillDocScore> queryDocFormScore(Map<String, String> param) {
        OscaSkillDocScoreExample example = new OscaSkillDocScoreExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andClinicalFlowEqualTo(param.get("clinicalFlow")).andDoctorFlowEqualTo(param.get("doctorFlow"))
                .andStationFlowEqualTo(param.get("stationFlow")).andStatusIdEqualTo(ScoreStatusEnum.Submit.getId());
        return osdsMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public OscaSkillDocScore queryDocScoreByFlow(String scoreFlow) {
        return osdsMapper.selectByPrimaryKey(scoreFlow);
    }

    @Override
    public int bindDoctorRole(String userFlow) {
        int count = checkExtMapper.registDoctor(userFlow);
        if(count > 0){
            SysRoleExample example = new SysRoleExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andWsIdEqualTo("osca").andRoleNameEqualTo("学员");
            List<SysRole> roleList = sysRoleMapper.selectByExample(example);//查询临床学员角色
            if(null != roleList && roleList.size() > 0 && StringUtil.isNotBlank(roleList.get(0).getRoleFlow())){
                SysUserRoleExample userRoleExample = new SysUserRoleExample();
                userRoleExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andUserFlowEqualTo(userFlow).andWsIdEqualTo("osca").andRoleFlowEqualTo(roleList.get(0).getRoleFlow());
                count = userRoleMapper.countByExample(userRoleExample);//查询此学员在临床是否已绑定角色
                if(count == 0){
                    SysUserRole record = new SysUserRole();
                    record.setRecordFlow(PkUtil.getUUID());
                    record.setUserFlow(userFlow);
                    record.setWsId("osca");
                    record.setRoleFlow(roleList.get(0).getRoleFlow());
                    GeneralMethod.setRecordInfo(record,true);
                    count = userRoleMapper.insertSelective(record);
                }
            }
        }
        return count;
    }

    @Override
    public int isShowFroom(String clinicalFlow, String isShowFroom) {
        OscaSkillsAssessment osa = new OscaSkillsAssessment();
        osa.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
        criteria.andClinicalFlowEqualTo(clinicalFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        return timeMapper.selectByExample(example);
    }

    @Override
    public List<OscaSkillsAssessmentTime> getAssessmentTimes(String clinicalFlow) {
        if(StringUtil.isNotBlank(clinicalFlow)){
            OscaSkillsAssessmentTimeExample example = new OscaSkillsAssessmentTimeExample();
            example.createCriteria().andClinicalFlowEqualTo(clinicalFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
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
                            doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
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
                                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_Y);
                                        doctorAssessment.setIsPass(DoctorScoreEnum.Passed.getId());
                                        doctorAssessment.setIsPassName(DoctorScoreEnum.Passed.getName());
                                        f = true;
                                    }else{
                                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
                                        doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
                                        doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
                                        f=true;
                                    }
                                }else{
                                    doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
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
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
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
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
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
                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
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
                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
                        doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
                        doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
                        f=true;
                    }else if(partCount==partPassCount)
                    {
                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_Y);
                        doctorAssessment.setIsPass(DoctorScoreEnum.Passed.getId());
                        doctorAssessment.setIsPassName(DoctorScoreEnum.Passed.getName());
                        f=true;
                    }else if(partCount>partPassCount)
                    {
                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
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
        criteria.andDoctorFlowEqualTo(doctorFlow).andClinicalFlowEqualTo(clinicalFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
        criteria.andClinicalFlowEqualTo(clinicalFlow).andDoctorFlowEqualTo(doctorFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        return osrdMapper.selectByExample(example);
    }
    public OscaSubjectMain getOscaSubjectMain(String subjectFlow) {
        return osmMapper.selectByPrimaryKey(subjectFlow);
    }
    public List<OscaSubjectStation> getOscaSubjectStations(String subjectFlow) {
        OscaSubjectStationExample station=new OscaSubjectStationExample();
        OscaSubjectStationExample.Criteria criteria=station.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
        station.setOrderByClause(" ORDINAL ASC ");
        return ossMapper.selectByExample(station);
    }
    public int editOscaDoctorAssessment(OscaDoctorAssessment doctorAssessment, SysUser user) {
        if(doctorAssessment!=null){
            if(StringUtil.isNotBlank(doctorAssessment.getRecordFlow())){//修改
                doctorAssessment.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                doctorAssessment.setModifyUserFlow(user.getUserFlow());
                doctorAssessment.setModifyTime(DateUtil.getCurrDateTime());
                return this.odaMapper.updateByPrimaryKeySelective(doctorAssessment);
            }else{//新增
                doctorAssessment.setRecordFlow(PkUtil.getUUID());
                doctorAssessment.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
    public int importUserFromExcel(MultipartFile file) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            return parseExcel3(wb);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
    private int parseExcel3(Workbook wb){
        int count = 0;
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum>0){
            List<String> colnames = new ArrayList<String>();
            Sheet sheet;
            try{
                sheet = wb.getSheetAt(0);
            }catch(Exception e){
                sheet = wb.getSheetAt(0);
            }
            int row_num = sheet.getLastRowNum();
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            String title = "";
            for(int i = 0 ; i <cell_num; i++){
                title = titleR.getCell(i).getStringCellValue();
                colnames.add(title);
            }
            for(int i = 1;i <= row_num; i++){
                Row r =  sheet.getRow(i);
                SysUser sysUser = new SysUser();
                String userName;
                String idNo;
                String userEmail;
                String userPhone;
                String orgFlow;
                String orgName;
                String deptFlow;
                String deptName;
                String postName;
                String userCode;
                String trainingYears="";
                int year = 0;
                ResDoctor resDoctor = new ResDoctor();
                for (int j = 0; j < colnames.size(); j++) {
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
                        if (cell.getCellType().getCode() == 1) {
                            value = cell.getStringCellValue().trim();
                        } else {
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
					/* 用户编号	员工姓名	身份证  邮件电话 	机构编号	机构名称	科室编号	科室名称	职务	 登录名*/
                    if("性别".equals(colnames.get(j))) {
                        sysUser.setSexName(value);
                        for(UserSexEnum e:UserSexEnum.values())
                        {
                            if(e.getName().equals(value))
                            {
                                sysUser.setSexId(e.getId());
                            }
                        }
                        if(StringUtil.isBlank(sysUser.getSexId()))
                        {
                            throw new RuntimeException("导入失败！第"+ count+2 +"行，性别输入错误！");
                        }
                    }else if("姓名".equals(colnames.get(j))){
                        userName = value;
                        sysUser.setUserName(userName);
                        resDoctor.setDoctorName(userName);
                    }else if("证件类型".equals(colnames.get(j))){
                        sysUser.setCretTypeName(value);
                        for(CertificateTypeEnum e:CertificateTypeEnum.values())
                        {
                            if(e.getName().equals(value))
                            {
                                sysUser.setCretTypeId(e.getId());
                            }
                        }
                        if(StringUtil.isBlank(sysUser.getCretTypeId()))
                        {
                            throw new RuntimeException("导入失败！第"+ count+2 +"行，证件类型输入错误！");
                        }
                    }else if("证件号码".equals(colnames.get(j))){
                        idNo = value;
                        sysUser.setIdNo(idNo);
                    }else if("注册邮箱（用户名）".equals(colnames.get(j))){
                        userEmail = value;
                        sysUser.setUserEmail(userEmail);
                        sysUser.setUserCode(userEmail);
                    }else if("手机号码".equals(colnames.get(j))) {
                        userPhone = value;
                        sysUser.setUserPhone(userPhone);
                    }else if("培训类型".equals(colnames.get(j))){
                        resDoctor.setTrainingTypeName(value);
                    }else if("培训专业".equals(colnames.get(j))){
                        resDoctor.setTrainingSpeName(value);
                    }else if("培养年限".equals(colnames.get(j))){
                        switch (value){
                            case "一年":{
                                trainingYears="OneYear";
                                year=1;
                                break;}
                            case "两年":{
                                trainingYears="TwoYear";
                                year=2;
                                break;}
                            case "三年":{
                                year=3;
                                trainingYears="ThreeYear";
                                break;}
                        }
                        resDoctor.setTrainingYears(trainingYears);
                    }else if("年级".equals(colnames.get(j))){
                        resDoctor.setSessionNumber(value);
                    }else if("所在单位".equals(colnames.get(j))){
                        resDoctor.setWorkOrgName(value);
                    }
                }
                int sessionValue = Integer.parseInt(resDoctor.getSessionNumber());
                String graduationYear = String.valueOf(sessionValue+year);
                resDoctor.setGraduationYear(graduationYear);
                if(StringUtil.isBlank(sysUser.getUserName())){
                    throw new RuntimeException("导入失败！第"+ count+2 +"行，姓名不能为空！");
                }
                if(StringUtil.isBlank(resDoctor.getTrainingTypeName())){
                    throw new RuntimeException("导入失败！第"+ count+2 +"行，培训类型不能为空！");
                }
                String trainingTypeId = getDictId(resDoctor.getTrainingTypeName(), com.pinde.core.common.enums.DictTypeEnum.OscaTrainingType.getId());
                resDoctor.setTrainingTypeId(trainingTypeId);
                if(StringUtil.isBlank(resDoctor.getTrainingTypeId())){
                    throw new RuntimeException("导入失败！第"+ count+2 +"行，培训类型有误，请检查名称！");
                }
                if(StringUtil.isBlank(resDoctor.getTrainingSpeName())){
                    throw new RuntimeException("导入失败！第"+ count+2 +"行，培训专业不能为空！");
                }
                SysDict sysDict = new SysDict();
                sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.OscaTrainingType.getId() + "." + trainingTypeId);
                sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                List<SysDict> sysDictList = dictBiz.searchDictList(sysDict);
                boolean speFlag = false;
                if(sysDictList!=null&&sysDictList.size()>0){
                    for(SysDict dict:sysDictList){
                        if(resDoctor.getTrainingSpeName().equals(dict.getDictName())){
                            resDoctor.setTrainingSpeId(dict.getDictId());
                            speFlag = true;
                            break;
                        }
                    }
                }
                if(!speFlag){
                    throw new RuntimeException("导入失败！第"+ count+2 +"行，培训专业有误，请检查名称！");
                }
                if(StringUtil.isBlank(resDoctor.getSessionNumber())){
                    throw new RuntimeException("导入失败！第"+ count+2 +"行，年级不能为空！");
                }
                SysUser currentUser = GlobalContext.getCurrentUser();
                String orgFlow0 = currentUser.getOrgFlow();
                String orgName0 = currentUser.getOrgName();
                sysUser.setOrgFlow(orgFlow0);
                resDoctor.setOrgFlow(orgFlow0);
                sysUser.setOrgName(orgName0);
                resDoctor.setOrgName(orgName0);
                if(StringUtil.isBlank(sysUser.getUserCode())){
                    throw new RuntimeException("导入失败！第"+ count+2 +"行，注册邮箱不能为空！");
                }
                //验证惟一用户登录名
                if(StringUtil.isNotBlank(sysUser.getUserCode())){
                    SysUserExample example=new SysUserExample();
                    example.createCriteria().andOrgFlowEqualTo(sysUser.getOrgFlow()).andUserCodeEqualTo(sysUser.getUserCode()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    List<SysUser> sysUserList = userMapper.selectByExample(example);
                    if(sysUserList != null && !sysUserList.isEmpty()){
                        throw new RuntimeException("导入失败！第"+count+2 +"行，当前系统已存在邮箱（登录名）为"+sysUser.getUserCode()+"的用户");
                    }
                }
                //执行保存
                iInxBiz.saveOsceRegistUser2(sysUser,resDoctor);
                count ++;
            }
        }
        return count;
    }
    /**
     * 根据字典名称获取Id
     * @param dictName
     * @param dictTypeId
     * @return
     */
    public String getDictId(String dictName,String dictTypeId){
        Map<String,String> dictNameMap=InitConfig.getDictNameMap(dictTypeId);
        return dictNameMap.get(dictName);
    }

    @Override
    public List<Map<String, Object>> screenSelect(Map<String, Object> paramMap) {
        return checkExtMapper.screenSelect(paramMap);
    }

    @Override
    public OscaExamDifferScore readDiffierScoreByOrgFlow(String orgFlow) {
        if(StringUtil.isNotBlank(orgFlow)) {
            OscaExamDifferScoreExample example = new OscaExamDifferScoreExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andOrgFlowEqualTo(orgFlow);
            List<OscaExamDifferScore> list=differScoreMapper.selectByExample(example);
            if(list!=null&&list.size()>0)
            {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public int saveExamDiffierScoreCfg(OscaExamDifferScore differScore) {
        int result=0;
        if(differScore!=null){
            if(StringUtil.isBlank(differScore.getCfgFlow())){//新增
                differScore.setCfgFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(differScore,true);
                result = differScoreMapper.insert(differScore);
            }else{//修改
                GeneralMethod.setRecordInfo(differScore,false);
                result = differScoreMapper.updateByPrimaryKeySelective(differScore);
            }
        }
        return result;
    }

    @Override
    public List<OscaSkillRoom> findClinicalStationRooms(String stationFlow, String clinicalFlow, String orgFlow) {
        OscaSkillRoomExample example=new OscaSkillRoomExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andStationFlowEqualTo(stationFlow)
                .andClinicalFlowEqualTo(clinicalFlow);
        example.setOrderByClause("room_flow");
        return osrMapper.selectByExample(example);
    }

    @Override
    public List<OscaSkillDocStation> findStationHouKaoDocs(String clinicalFlow, String stationFlow) {
        return oscaExtMapper.findStationHouKaoDocs(clinicalFlow,stationFlow);
    }

    @Override
    public OscaSkillDocStation getDocStation(String recordFlow) {
        return docStationMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int saveDocStation(OscaSkillDocStation docStation) {
        if(StringUtil.isNotBlank(docStation.getRecordFlow())){
            GeneralMethod.setRecordInfo(docStation,false);
           return docStationMapper.updateByPrimaryKeySelective(docStation);
        }else{
            docStation.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(docStation,true);
            return docStationMapper.insertSelective(docStation);
        }
    }

    @Override
    public OscaSkillDocStation getStationAssessDoc(String clinicalFlow, String stationFlow, String roomFlow) {
        List<OscaSkillDocStation> docStations= oscaExtMapper.getStationAssessDoc(clinicalFlow,stationFlow,roomFlow);
        if(docStations!=null&&docStations.size()>0)
        {
            return docStations.get(0);
        }
        return null;
    }

    @Override
    public OscaSubjectStation readStation(String stationFlow) {
        return ossMapper.selectByPrimaryKey(stationFlow);
    }

    @Override
    public int importStudentsNew(MultipartFile file, String clinicalFlow, String examStartTime, String examEndTime, String isNewTime) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseExcelNew(wb,clinicalFlow,examStartTime,examEndTime,isNewTime);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private int parseExcelNew(Workbook wb,String clinicalFlow,String examStartTime,String examEndTime,String isNewTime) throws Exception{
        int count = 0;//导入记录数
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum > 0){
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum()+1;
            if(row_num==1){
                throw new Exception("导入文件内容为空！");
            }
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            List<String> colnames = new ArrayList<String>();
            for(int i = 0 ; i <cell_num; i++){
                colnames.add(titleR.getCell(i).getStringCellValue());
            }
            for(int i = 1;i < row_num; i++) {
                SysUser user = new SysUser();//建立bean
                ResDoctor doctor = new ResDoctor();
//                doctor.setOscaStudentSubmit(com.pinde.core.common.GlobalConstant.FLAG_Y);
                OscaDoctorAssessment assessment = new OscaDoctorAssessment();

                Row r = sheet.getRow(i);
                for(int j = 0; j < colnames.size(); j++){
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
                        if(cell.getCellType().getCode() == 1){
                            value = cell.getStringCellValue().trim();
                        }else{
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    String currTitle = colnames.get(j);
                    if("姓名".equals(currTitle)){
                        user.setUserName(value);
                    }
                    if("性别".equals(currTitle)){
                        if("男".equals(value)){
                            user.setSexId("Man");
                        }else if("女".equals(value)){
                            user.setSexId("Woman");
                        }
                        user.setSexName(value);
                    }
                    if("证件号码".equals(currTitle)){
                        user.setIdNo(value);
                        user.setUserCode(value);
                    }
                    if("联系方式".equals(currTitle)){
                        user.setUserPhone(value);
                    }
                    if("培训届别".equals(currTitle)){
                        doctor.setSessionNumber(value);
                    }
                    if("培训基地".equals(currTitle)){
                        doctor.setOrgName(value);
                        user.setOrgName(value);
                    }
                }
                String regEx = "^1[3|4|5|6|7|8|9][0-9]\\d{8}$";
                if(StringUtil.isBlank(user.getUserName())){
                    throw new Exception("导入失败！第"+ (count+2) +"行姓名不能为空！");
                }
                if(StringUtil.isBlank(user.getSexName())){
                    throw new Exception("导入失败！第"+ (count+2) +"行性别不能为空！");
                }
                if(StringUtil.isBlank(user.getIdNo())){
                    throw new Exception("导入失败！第"+ (count+2) +"行身份证号不能为空！");
                }
//                String isIDCard1="^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
                String isSessionNumber="^\\d{4}$";
                if(!(doctor.getSessionNumber().matches(isSessionNumber))){
                    throw new Exception("导入失败！第"+ (count+2) +"行届别有误！");
                }
                if(StringUtil.isBlank(user.getUserPhone())){
                    throw new Exception("导入失败！第"+ (count+2) +"行手机号码不能为空！");
                }
                if(!user.getUserPhone().matches(regEx)){
                    throw new Exception("导入失败！第"+ (count+2) +"行手机号码有误！");
                }
                if(StringUtil.isBlank(doctor.getSessionNumber())){
                    throw new Exception("导入失败！第"+ (count+2) +"培训届别不能为空！");
                }
                if(StringUtil.isBlank(doctor.getOrgName())){
                    throw new Exception("导入失败！第"+ (count+2) +"培训基地不能为空！");
                }else {
                    SysOrg org=orgBiz.readSysOrgByName(doctor.getOrgName());
                    if(org==null)
                        throw new RuntimeException("导入失败！第"+ count+2 +"行，机构名称输入不正确！");
                    user.setOrgFlow(org.getOrgFlow());
                    doctor.setDoctorFlow(org.getOrgFlow());
                }
                //判断基地导入学员是否在市局导入学员的名单中
                ResDoctorSkillExample skillExample = new ResDoctorSkillExample();
                skillExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andIdNoEqualTo(user.getIdNo());
                int count2 = doctorSkillMapper.countByExample(skillExample);
                if(count2 <= 0){
                    throw new RuntimeException("导入失败！第"+ count+2 +"行，该学员不在市局导入名单中！");
                }

                SysUserExample example = new SysUserExample();
                example.createCriteria().andIdNoEqualTo(user.getIdNo());
                int num = userMapper.countByExample(example);
                if(num <= 0){//新增
                    user.setUserFlow(PkUtil.getUUID());//新增user表
                    user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(),"123456"));
                    user.setIsOsca(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    user.setStatusId(UserStatusEnum.Activated.getId());
                    GeneralMethod.setRecordInfo(user,true);
                    userMapper.insertSelective(user);

                    OscaSkillsAssessment skillsAssessment = queryDataByFlow(clinicalFlow);
                    String speId = skillsAssessment.getSpeId();
                    String speName = skillsAssessment.getSpeName();

                    doctor.setDoctorFlow(user.getUserFlow());//新增resDoctor表
                    doctor.setDoctorName(user.getUserName());
                    doctor.setTrainingSpeId(speId);
                    doctor.setTrainingSpeName(speName);
                    GeneralMethod.setRecordInfo(doctor,true);
                    doctorMapper.insertSelective(doctor);

                    assessment.setRecordFlow(PkUtil.getUUID());//新增OSCA_DOCTOR_ASSESSMENT表
                    assessment.setClinicalFlow(clinicalFlow);
                    assessment.setClinicalName(skillsAssessment.getClinicalName());
                    assessment.setClinicalYear(skillsAssessment.getClinicalYear());
                    assessment.setDoctorFlow(user.getUserFlow());
                    assessment.setDoctorName(user.getUserName());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    assessment.setAppointTime(sdf.format(new Date()));
                    assessment.setAuditStatusId(AuditStatusEnum.Passed.getId());
                    assessment.setAuditStatusName(AuditStatusEnum.Passed.getName());
                    assessment.setIsPass(DoctorScoreEnum.Miss.getId());
                    assessment.setIsPassName(DoctorScoreEnum.Miss.getName());
                    assessment.setExamStartTime(examStartTime);
                    assessment.setExamEndTime(examEndTime);
                    GeneralMethod.setRecordInfo(assessment,true);
                    odaMapper.insertSelective(assessment);
                    auditAppoint(assessment.getRecordFlow(),AuditStatusEnum.Passed.getId(),null);
                }else{
                    SysUser user1 = userBiz.findByIdNo(user.getIdNo());
//                    if(!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(user1.getIsOsca())) {
//                        throw new Exception("导入失败！第" + (count + 2) + "行身份证号" + user.getIdNo() + "用户在系统中已存在！");
//                    }else{
                        user1.setUserName(user.getUserName());//更新USER
                        user1.setSexName(user.getSexName());
                        user1.setSexId(user.getSexId());
                        user1.setUserPhone(user.getUserPhone());
                        userMapper.updateByPrimaryKeySelective(user1);

                        OscaSkillsAssessment skillsAssessment = queryDataByFlow(clinicalFlow);
                        String speId = skillsAssessment.getSpeId();
                        String speName = skillsAssessment.getSpeName();

                        ResDoctor doctor1 = doctorBiz.readDoctor(user1.getUserFlow());//更新DOCTOR
                        doctor1.setTrainingSpeId(speId);
                        doctor1.setTrainingSpeName(speName);
                        doctor1.setSessionNumber(doctor.getSessionNumber());
                        doctor1.setOrgName(doctor.getOrgName());
                        doctorMapper.updateByPrimaryKeySelective(doctor1);

                        //同一场考试更新，不同场次新增
                        OscaDoctorAssessment doctorAssessment = new OscaDoctorAssessment();
                        doctorAssessment.setDoctorFlow(user1.getUserFlow());
                        doctorAssessment.setClinicalFlow(clinicalFlow);
                        List<OscaDoctorAssessment> doctorAssessments = doctorOrderdeBiz.selectDoctorAssessment(doctorAssessment);
                        if(doctorAssessments.size()>0&&doctorAssessments!=null){//更新OSCA_DOCTOR_ASSESSMENT表
                            assessment.setRecordFlow(doctorAssessments.get(0).getRecordFlow());
                            assessment.setDoctorName(user1.getUserName());
                            assessment.setExamStartTime(examStartTime);
                            assessment.setExamEndTime(examEndTime);
                            GeneralMethod.setRecordInfo(assessment,false);
                            odaMapper.updateByPrimaryKeySelective(assessment);
                        }else{
                            assessment.setRecordFlow(PkUtil.getUUID());//新增OSCA_DOCTOR_ASSESSMENT表
                            assessment.setClinicalFlow(clinicalFlow);
                            assessment.setClinicalName(skillsAssessment.getClinicalName());
                            assessment.setClinicalYear(skillsAssessment.getClinicalYear());
                            assessment.setDoctorFlow(user1.getUserFlow());
                            assessment.setDoctorName(user1.getUserName());
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            assessment.setAppointTime(sdf.format(new Date()));
                            assessment.setAuditStatusId(AuditStatusEnum.Passed.getId());
                            assessment.setAuditStatusName(AuditStatusEnum.Passed.getName());
                            assessment.setIsPass(DoctorScoreEnum.Miss.getId());
                            assessment.setExamStartTime(examStartTime);
                            assessment.setExamEndTime(examEndTime);
                            GeneralMethod.setRecordInfo(assessment,true);
                            odaMapper.insertSelective(assessment);
                            auditAppoint(assessment.getRecordFlow(),AuditStatusEnum.Passed.getId(),null);
                        }
//                    }
                }
                count ++;
            }
            //更新OSCA_SKILLS_ASSESSMENT_TIME表
            OscaSkillsAssessmentTime skillsAssessmentTime = new OscaSkillsAssessmentTime();
            if (isNewTime.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                OscaSkillsAssessment skillsAssessment = queryDataByFlow(clinicalFlow);//开始时间不能在预约结束时间之前
                String appointEndTime = skillsAssessment.getAppointEndTime();
                if(appointEndTime.compareTo(examStartTime)>=0){
                    throw new Exception("导入失败！考核时间不能在预约结束时间之前");
                }
                List<OscaSkillsAssessmentTime> oscaSkillsAssessmentTimeList = queryOsaTimeList(clinicalFlow);//考试时间相互不能冲突
                if(oscaSkillsAssessmentTimeList.size()>0&&oscaSkillsAssessmentTimeList!=null){
                    for(OscaSkillsAssessmentTime oscaSkillsAssessmentTime:oscaSkillsAssessmentTimeList){
                        String startTime = oscaSkillsAssessmentTime.getExamStartTime();
                        String endTime = oscaSkillsAssessmentTime.getExamEndTime();
                        if(!
                                ((examEndTime.compareTo(startTime)<0)
                                        ||(examStartTime.compareTo(endTime)>0))
                                ){
                            throw new Exception("导入失败！考核时间冲突");
                        }
                    }
                }
                skillsAssessmentTime.setRecrodFlow(PkUtil.getUUID());
                skillsAssessmentTime.setClinicalFlow(clinicalFlow);
                skillsAssessmentTime.setExamStartTime(examStartTime);
                skillsAssessmentTime.setExamEndTime(examEndTime);
                skillsAssessmentTime.setTestNumber(String.valueOf(count));
                GeneralMethod.setRecordInfo(skillsAssessmentTime,true);
                skillsAssessmentTimeMapper.insertSelective(skillsAssessmentTime);
            }else {
                skillsAssessmentTime.setRecrodFlow(isNewTime);
                String oldNumber = readAssessmentTime(isNewTime).getTestNumber();
                int newNumber = Integer.parseInt(oldNumber)+count;
                skillsAssessmentTime.setTestNumber(String.valueOf(newNumber));
                GeneralMethod.setRecordInfo(skillsAssessmentTime,false);
                skillsAssessmentTimeMapper.updateByPrimaryKeySelective(skillsAssessmentTime);
            }
        }
        return count;
    }
}

