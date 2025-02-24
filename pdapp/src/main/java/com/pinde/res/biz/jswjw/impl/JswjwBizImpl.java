package com.pinde.res.biz.jswjw.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pinde.app.common.GlobalUtil;
import com.pinde.app.common.InitConfig;
import com.pinde.core.common.form.UserResumeExtInfoForm;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.*;
import com.pinde.core.common.sci.dao.*;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.biz.stdp.IResActivityBiz;
import com.pinde.res.biz.stdp.IResSchProcessExpressBiz;
import com.pinde.res.ctrl.jswjw.ActivityImageFileForm;
import com.pinde.res.ctrl.jswjw.ImageFileForm;
import com.pinde.res.dao.jswjw.ext.*;
import com.pinde.res.dao.sctcm120.ext.ResDoctorKqExtMapper;
import com.pinde.core.common.sci.dao.StdpResDoctorExtMapper;
import com.pinde.core.common.sci.dao.TeachingActivityInfoExtMapper;
import com.pinde.sci.util.PicZoom;
import com.pinde.sci.util.WeixinQiYeUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
//@Transactional(rollbackFor = Exception.class)
public class JswjwBizImpl implements IJswjwBiz {

    private static Logger logger = LoggerFactory.getLogger(JswjwBizImpl.class);
    /**
     * 要求标识
     */
    final static private String REQ_NUM = "ReqNum";
    /**
     * 完成数标识
     */
    final static private String FINISHED = "Finished";
    /**
     * 总数标识
     */
    final static private String TOTAL = "Total";
    @Autowired
    private ResScoreMapper scoreMapper;
    @Autowired
    private DoctorAuthMapper doctorAuthMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserExtMapper sysUserExtMapper;
    @Resource
    private ResRecMapper recMapper;
    @Autowired
    private SysUserDeptMapper userDeptMapper;
    @Resource
    private ResRecExtMapper recExtMapper;
    @Resource
    private ResSchProcessExpressMapper expressMapper;
    @Resource
    private DeptTeacherGradeInfoMapper gradeInfoMapper;
    @Resource
    private ResDoctorMapper doctorMapper;
    @Resource
    private DoctorExtMapper doctorExtMapper;
    @Resource
    private SchRotationDeptExtMapper schRotationDeptExtMapper;
    @Resource
    private SchRotationDeptMapper schRotationDeptMapper;
    @Resource
    private SysAppUserInfoMapper appUserInfoMapper;
    @Resource
    private ResStandardDeptPerMapper perMapper;
    @Resource
    private JsresAttendanceDetailMapper jsresAttendanceDetailMapper;
    @Resource
    private JsresAttendanceMapper jsresAttendanceMapper;
    @Resource
    private SchRotationDeptReqMapper schRotationDeptReqMapper;
    @Resource
    private SchRotationDeptReqExtMapper schRotationDeptReqExtMapper;
    @Resource
    private SchArrangeResultMapper resultMapper;
    @Resource
    private SysCfgMapper cfgMapper;
    @Resource
    private SchRotationMapper rotationMapper;
    @Resource
    private SchArrangeResultExtMapper resultExtMapper;
    @Resource
    private SchDoctorDeptMapper doctorDeptMapper;
    @Resource
    private SchDoctorDeptExtMapper doctorDeptExtMapper;
    @Resource
    private SysLogMapper logMapper;
    @Resource
    private ResDoctorSchProcessMapper processMapper;
    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private ResAssessCfgMapper assessCfgMapper;
    @Resource
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private StdpResDoctorExtMapper docExtMapper;
    @Autowired
    private ResDoctorRecruitMapper recruitMapper;
    @Autowired
    private ResLectureRandomScanMapper lectureRandomScanMapper;
    @Autowired
    private ResLectureRandomSignMapper lectureRandomSignMapper;
    @Autowired
    private SysDeptExtMapper sysDeptExtMapper;
    @Autowired
    private SysOrgMapper orgMapper;
    @Autowired
    private SysOrgExtMapper orgExtMapper;
    @Autowired
    private ResLectureScanRegistMapper lectureScanRegistMapper;
    @Autowired
    private ResLectureInfoMapper lectureInfoMapper;
    @Autowired
    private ResLectureInfoExtMapper lectureInfoExtMapper;
    @Autowired
    private ResLectureEvaDetailMapper lectureEvaDetailMapper;
    @Autowired
    private SchRotationDeptReqMapper rotationDeptReqMapper;
    @Autowired
    private JsresPowerCfgMapper jsresPowerCfgMapper;
    @Autowired
    private ResDoctorSchProcessEvalMapper processEvalMapper;
    @Autowired
    private ResDoctorProcessEvalConfigMapper evalConfigMapper;
    @Autowired
    private ResJointOrgMapper resJointOrgMapper;
    @Autowired
    private SysDictMapper sysDictMapper;
    @Autowired
    private IResActivityBiz activityBiz;
    @Autowired
    private DoctorUntiedRecordingMapper recordingMapper;
    @Autowired
    private SysUserRegisterMapper sysUserRegisterMapper;
    @Autowired
    private LectureInfoTargetMapper lectureInfoTargetMapper;
    @Autowired
    private ResLectureEvaDetailExtMapper lectureEvaDetailExtMapper;
    @Autowired
    private JsresDeptConfigMapper deptConfigMapper;
    @Autowired
    private ResTestConfigMapper resTestConfigMapper;
    @Autowired
    private JsresExamSignupMapper jsresExamSignupMapper;
    @Autowired
    private ResDoctorRecruitMapper doctorRecruitMapper;
    @Autowired
    private JsresGraduationApplyMapper graduationApplyMapper;
    @Autowired
    private PubUserResumeMapper userResumpMapper;
    @Autowired
    private SchRotationGroupMapper rotationGroupMapper;
    @Autowired
    private SchRotationDeptMapper rotationDeptMapper;
    @Autowired
    private SchArrangeResultMapper arrangeResultMapper;
    @Autowired
    private ResDoctorSchProcessExtMapper docSchProcessExtMapper;
    @Autowired
    private TempMapper tempMapper;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private SysCfgExtMapper sysCfgExtMapper;
    @Autowired
    private ResDoctorRecruitExtMapper recruitExtMapper;
    @Autowired
    private ResOrgSpeAssignMapper speAssignMapper;
    @Autowired
    private ResOrgSpeMapper resOrgSpeMapper;
    @Autowired
    private ResDocotrDelayTeturnExtMapper resDocotrDelayTeturnExtMapper;
    @Autowired
    private JsResDoctorRecruitExtMapper jsResDoctorRecruitExtMapper;
    @Autowired
    private JsresRecruitDocInfoMapper recruitDocInfoMapper;
    @Autowired
    private ResDocotrDelayTeturnMapper resDocotrDelayTeturnMapper;
    @Autowired
    private ResDoctorOrgHistoryMapper docOrgHistoryMapper;
    @Autowired
    private ResDoctorReductionMapper reductionMapper;
    @Autowired
    private JsResPowerCfgExtMapper jsResPowerCfgExtMapper;
    @Autowired
    private VerificationCodeRecordMapper verificationCodeRecordMapper;
    @Autowired
    private PubFileMapper pubFileMapper;
    @Autowired
    private ResOutOfficeLockMapper outOfficeLockMapper;
    @Autowired
    private ResDoctorSchProcessExtMapper resDoctorProcessExtMapper;
    @Autowired
    private SysOrgExtMapper sysOrgExtMapper;
    @Autowired
    private SysOrgMapper sysOrgMapper;
    @Autowired
    private JsResRecruitDoctorInfoExtMapper doctorRecruitInfoExtMapper;
    @Autowired
    private ResAttendanceExtMapper resAttendenceExtMapper;
    @Autowired
    private ResDoctorKqExtMapper resDoctorKqExtMapper;
    @Autowired
    private SchDeptMapper schDeptMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private TeachingActivityInfoExtMapper activityInfoExtMapper;
    @Autowired
    private JsresGraduationApplyExtMapper graduationApplyExtMapper;
    @Autowired
    private ResDoctorRecruitExtMapper doctorRecruitExtMapper;
    @Autowired
    private TeachingActivityInfoMapper activityInfoMapper;
    @Autowired
    private ResHospSupervSubjectMapper hospSupervSubjectMapper;
    @Autowired
    private JsresDoctorDeptDetailMapper doctorDeptDetailMapper;


    @Override
    public SysUser findByUserCode(String userCode) {
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        criteria.andUserCodeEqualTo(userCode);
        List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
        if (sysUserList.size() > 0) {
            return sysUserList.get(0);
        }
        return null;
    }

    @Override
    public SysUser readSysUser(String userFlow) {
        return sysUserMapper.selectByPrimaryKey(userFlow);
    }

    @Override
    public List<ResRec> searchByUserFlowAndTypeId(String userFlow, String recTypeId) {
        ResRecExample example = new ResRecExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
                .andOperUserFlowEqualTo(userFlow);
        return recMapper.selectByExampleWithBLOBs(example);
    }


    @Override
    public List<SchArrangeResult> searchSchArrangeResult(String userFlow,
                                                         String deptFlow, Integer pageIndex, Integer pageSize) {
        SchRotationDept rotationDept = schRotationDeptMapper.selectByPrimaryKey(deptFlow);
//		System.err.println("pageHeper.startPage");
        if (pageIndex != null && pageSize != null) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        SchArrangeResultExample example = new SchArrangeResultExample();
        example.createCriteria().andDoctorFlowEqualTo(userFlow).andStandardGroupFlowEqualTo(rotationDept.getGroupFlow()).andRotationFlowEqualTo(rotationDept.getRotationFlow())
                .andStandardDeptIdEqualTo(rotationDept.getStandardDeptId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("SCH_START_DATE");
        return resultMapper.selectByExample(example);
    }

    @Override
    public List<SchArrangeResult> getSchArrangeResult(String userFlow, String orgFlow, Integer pageIndex, Integer pageSize, String rotationFlow) {
        if (pageIndex != null && pageSize != null) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        SchArrangeResultExample example = new SchArrangeResultExample();
        SchArrangeResultExample.Criteria criteria = example.createCriteria().andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(rotationFlow)) {
            criteria.andRotationFlowEqualTo(rotationFlow);
        }
        example.setOrderByClause("SCH_START_DATE");
        return resultMapper.selectByExample(example);
    }

    @Override
    public void addSubDept(String userFlow, String deptFlow,
                           String subDeptName, String startDate, String endDate) throws ParseException {
        ResDoctor doctor = doctorMapper.selectByPrimaryKey(userFlow);
        SchRotationDept rotationDept = schRotationDeptMapper.selectByPrimaryKey(deptFlow);
        SchArrangeResult result = new SchArrangeResult();
        result.setResultFlow(PkUtil.getUUID());
        result.setArrangeFlow(result.getResultFlow());
        result.setDoctorFlow(userFlow);
        result.setSessionNumber(doctor.getSessionNumber());
        result.setDoctorName(doctor.getDoctorName());
        result.setBaseAudit("Passed");    //新增的轮转计划默认审核通过
        String currOrgFlow = doctor.getOrgFlow();
        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
            currOrgFlow = doctor.getSecondOrgFlow();
        }
        SysOrg org = orgMapper.selectByPrimaryKey(currOrgFlow);
        result.setOrgFlow(currOrgFlow);
        result.setOrgName(org.getOrgName());
        result.setSchStartDate(startDate);
        result.setSchEndDate(endDate);
        result.setRotationFlow(rotationDept.getRotationFlow());
        result.setSchDeptName(subDeptName);
        result.setStandardDeptId(rotationDept.getStandardDeptId());
        result.setStandardDeptName(rotationDept.getStandardDeptName());
        result.setStandardGroupFlow(rotationDept.getGroupFlow());
        result.setIsRequired(rotationDept.getIsRequired());
        result.setCreateTime(DateUtil.getCurrDateTime());
        result.setCreateUserFlow(userFlow);
        result.setModifyTime(DateUtil.getCurrDateTime());
        result.setModifyUserFlow(userFlow);
        result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        Map<String, String> map = new HashMap<>();
        map.put("startDate", result.getSchStartDate());
        map.put("endDate", result.getSchEndDate());
        Double month = TimeUtil.getMonthsBetween(map);
        String schMonth = String.valueOf(Double.parseDouble(month + ""));
        result.setSchMonth(schMonth);
        resultMapper.insert(result);
    }

    @Override
    public List<SchArrangeResult> checkResultDate(String doctorFlow, String startDate, String endDate) {
        List<SchArrangeResult> result = null;
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && StringUtil.isNotBlank(doctorFlow)) {
            result = checkResultDate(doctorFlow, startDate, endDate, null);
        }
        return result;
    }

    @Override
    public List<SchArrangeResult> checkResultDate(String doctorFlow, String startDate, String endDate, String subDeptFlow) {
        List<SchArrangeResult> result = null;
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && StringUtil.isNotBlank(doctorFlow)) {
            result = checkResultDate(doctorFlow, startDate, endDate, null, null);
        }
        return result;
    }

    @Override
    public List<SchArrangeResult> checkResultDate(String doctorFlow, String startDate, String endDate, String subDeptFlow, String rotationFlow) {
        List<SchArrangeResult> result = null;
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && StringUtil.isNotBlank(doctorFlow)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("doctorFlow", doctorFlow);
            paramMap.put("startDate", startDate);
            paramMap.put("endDate", endDate);
            paramMap.put("subDeptFlow", subDeptFlow);
            paramMap.put("rotationFlow", rotationFlow);
            result = resultExtMapper.checkResultDate(paramMap);
        }
        return result;
    }

    @Override
    public List<SchArrangeResult> searchSchArrangeResults(String doctorFlow, String rotationFlow, String startDate) {
        List<SchArrangeResult> result = null;
        if (StringUtil.isNotBlank(doctorFlow)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("doctorFlow", doctorFlow);
            paramMap.put("rotationFlow", rotationFlow);
            if (StringUtil.isNotEmpty(startDate)) {
                paramMap.put("startDate", startDate);
            }
            result = resultExtMapper.searchSchArrangeResults(paramMap);
        }
        return result;
    }

    @Override
    public List<ResLectureScanRegist> searchIsRegists(String lectureFlow) {
        ResLectureScanRegistExample example = new ResLectureScanRegistExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andLectureFlowEqualTo(lectureFlow).
                andIsRegistEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        return lectureScanRegistMapper.selectByExample(example);
    }

    @Override
    public void modSubDept(String userFlow, String deptFlow,
                           String subDeptFlow, String subDeptName, String startDate,
                           String endDate) throws ParseException {
        SchArrangeResult result = resultMapper.selectByPrimaryKey(subDeptFlow);
        if (result != null) {
            result.setSchDeptName(subDeptName);
            result.setSchStartDate(startDate);
            result.setSchEndDate(endDate);
            Map<String, String> map = new HashMap<>();
            map.put("startDate", result.getSchStartDate());
            map.put("endDate", result.getSchEndDate());
            Double month = TimeUtil.getMonthsBetween(map);
            String schMonth = String.valueOf(Double.parseDouble(month + ""));
            result.setSchMonth(schMonth);
            result.setModifyTime(DateUtil.getCurrDateTime());
            result.setModifyUserFlow(userFlow);

            ResDoctor doctor = doctorMapper.selectByPrimaryKey(result.getDoctorFlow());
            //查看轮转计划排班设置是否开启
            JsresPowerCfg cfg = jsresPowerCfgMapper.selectByPrimaryKey("jsres_" + doctor.getOrgFlow() + "_org_process_scheduling_org");
            if (null != cfg && StringUtil.isNotEmpty(cfg.getCfgValue()) && cfg.getCfgValue().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                result.setBaseAudit("Passing"); //待审核
            }
            resultMapper.updateByPrimaryKeySelective(result);
        }
    }

    @Override
    public void deleteSubDept(String userFlow, String deptFlow,
                              String subDeptFlow) {
        SchArrangeResult result = resultMapper.selectByPrimaryKey(subDeptFlow);
        if (result != null) {
            result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            result.setModifyTime(DateUtil.getCurrDateTime());
            result.setModifyUserFlow(userFlow);
            resultMapper.updateByPrimaryKeySelective(result);

            ResDoctorSchProcess process = getProcessByResultFlow(result.getResultFlow());
            if (process != null) {
                process.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
                process.setModifyTime(DateUtil.getCurrDateTime());
                process.setModifyUserFlow(userFlow);
                processMapper.updateByPrimaryKeySelective(process);
            }

        }
    }

    @Override
    public List<Map<String, Object>> globalProgress(String userFlow, String deptFlow, boolean isChargeOrg) {
        List<Map<String, Object>> globalDataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> enterSubDeptData = null;
        Map<String, Object> mrData = null;
        Map<String, Object> diseaseData = null;
        Map<String, Object> skillData = null;
        Map<String, Object> operationData = null;
//		Map<String,Object> languageTeachingData = null;
        Map<String, Object> activityData = null;
        Map<String, Object> evaluationData = null;
        Map<String, Object> inProcessInfoData = null;
        Map<String, Object> afterEvaluationData = null;
        Map<String, Object> uploadExitFormData = null;

        SchRotationDept rotationDept = schRotationDeptMapper.selectByPrimaryKey(deptFlow);
        if (enterSubDeptData == null) {
            enterSubDeptData = new HashMap<String, Object>();
            enterSubDeptData.put("title", "轮转科室填写");
            enterSubDeptData.put("order", "1");
            enterSubDeptData.put("dataType", "enterSubDept");
            enterSubDeptData.put("label", "0%");
            enterSubDeptData.put("progress", "0");
            // 查询学员轮转计划组总月数及条数
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("doctorFlow", userFlow);
            paramMap.put("standardGroupFlow", rotationDept.getGroupFlow());
            paramMap.put("rotationFlow", rotationDept.getRotationFlow());
            paramMap.put("standardDeptId", rotationDept.getStandardDeptId());
//			SchArrangeResultExample example = new SchArrangeResultExample();
//			example.createCriteria().andDoctorFlowEqualTo(userFlow).andStandardGroupFlowEqualTo(rotationDept.getGroupFlow()).andRotationFlowEqualTo(rotationDept.getRotationFlow())
//					.andStandardDeptIdEqualTo(rotationDept.getStandardDeptId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//			List<SchArrangeResult> resultList = resultMapper.selectByExample(example);
            Map<String, String> schResultMap = resultExtMapper.getDoctorSchArrange(paramMap);
            Float realMonth = Float.valueOf(schResultMap.get("REAL_MONTH"));
//			for(SchArrangeResult result : resultList){
//				if(StringUtil.isNotBlank(result.getSchMonth())){
//					realMonth += Float.parseFloat(result.getSchMonth());
//				}
//			}
            if (Integer.valueOf(schResultMap.get("SCH_COUNT")) == 0) {
                enterSubDeptData.put("canAdd", com.pinde.core.common.GlobalConstant.FLAG_N);
            } else {
                enterSubDeptData.put("canAdd", com.pinde.core.common.GlobalConstant.FLAG_Y);
            }
            Float schMonth = Float.parseFloat(rotationDept.getSchMonth());

            String groupFlow = rotationDept.getGroupFlow();
            String standardDeptId = rotationDept.getStandardDeptId();
            SchDoctorDeptExample docDeptExample = new SchDoctorDeptExample();
            docDeptExample.createCriteria().andStandardDeptIdEqualTo(standardDeptId).andGroupFlowEqualTo(groupFlow)
                    .andDoctorFlowEqualTo(userFlow);
            List<SchDoctorDept> doctorDeptList = doctorDeptMapper.selectByExample(docDeptExample);
            if (doctorDeptList != null && !doctorDeptList.isEmpty()) {
                SchDoctorDept docDept = doctorDeptList.get(0);
                if (docDept != null) {
                    String reductionSchMonth = docDept.getSchMonth();
                    if (StringUtil.isNotBlank(reductionSchMonth)) {
                        schMonth = Float.parseFloat(reductionSchMonth);
                    }
                }
            }

            Float sumPer = realMonth / schMonth;
            enterSubDeptData.put("progress", (sumPer * 100) + "");
            globalDataList.add(0, enterSubDeptData);
        }

        ResDoctor doctor = readResDoctor(userFlow);
        //轮转进度统计
        Map<String, String> recPer = getProcessPer(userFlow, rotationDept.getRotationFlow(), deptFlow);
//		System.err.println(JSON.toJSON(recPer));
        if (recPer == null) {
            recPer = new HashMap<String, String>();
        }
        int count = 0;
        if (inProcessInfoData == null && isChargeOrg) {
            count++;
            inProcessInfoData = new HashMap<String, Object>();
            inProcessInfoData.put("title", "入科教育信息规范");
            inProcessInfoData.put("order", count + 1);
            inProcessInfoData.put("dataType", "inProcessInfoList");
            inProcessInfoData.put("progress", "");
            inProcessInfoData.put("label", "入科教育信息规范");
            globalDataList.add(count, inProcessInfoData);
        }
        if (mrData == null) {
            count++;
            String perKey = deptFlow + RegistryTypeEnum.CaseRegistry.getId();
            String reqKey = perKey + "ReqNum";

            mrData = new HashMap<String, Object>();
            mrData.put("title", "大病历");
            mrData.put("order", count + 1);
            mrData.put("dataType", "mr");
//			mrData.put("label", "0%");
//			mrData.put("progress", "0");
            if (recPer.get(reqKey) == null || "0".equals(recPer.get(reqKey))) {
                mrData.put("progress", "-");
                List<Map<String, Object>> catagoryDataList = categoryProgress("mr", userFlow, deptFlow, doctor.getRotationFlow());
                for (Map<String, Object> catagoryData : catagoryDataList) {
                    if (!catagoryData.get("finishedNum").equals("0")) {
                        mrData.put("progress", "100");
                        break;
                    }
                }
            } else {
                mrData.put("progress", recPer.get(perKey));
            }
            globalDataList.add(count, mrData);
        }
        if (diseaseData == null) {
            count++;
            String perKey = deptFlow + RegistryTypeEnum.DiseaseRegistry.getId();
            String reqKey = perKey + "ReqNum";

            diseaseData = new HashMap<String, Object>();
            diseaseData.put("title", "病种");
            diseaseData.put("order", count + 1);
            diseaseData.put("dataType", "disease");
//			diseaseData.put("label", "0%");
//			diseaseData.put("progress", "0");

            if (recPer.get(reqKey) == null || "0".equals(recPer.get(reqKey))) {
                diseaseData.put("progress", "-");
                List<Map<String, Object>> catagoryDataList = categoryProgress("disease", userFlow, deptFlow, doctor.getRotationFlow());
                for (Map<String, Object> catagoryData : catagoryDataList) {
                    if (!catagoryData.get("finishedNum").equals("0")) {
                        diseaseData.put("progress", "100");
                        break;
                    }
                }
            } else {
                diseaseData.put("progress", recPer.get(perKey));
            }
            globalDataList.add(count, diseaseData);
        }
        if (skillData == null) {
            count++;
            String perKey = deptFlow + RegistryTypeEnum.SkillRegistry.getId();
            String reqKey = perKey + "ReqNum";

            skillData = new HashMap<String, Object>();
            skillData.put("title", "操作技能");
            skillData.put("order", count + 1);
            skillData.put("dataType", "skill");
//			skillData.put("label", "0%");
//			skillData.put("progress", "0");

            if (recPer.get(reqKey) == null || "0".equals(recPer.get(reqKey))) {
                skillData.put("progress", "-");
                List<Map<String, Object>> catagoryDataList = categoryProgress("skill", userFlow, deptFlow, doctor.getRotationFlow());
                for (Map<String, Object> catagoryData : catagoryDataList) {
                    if (!catagoryData.get("finishedNum").equals("0")) {
                        skillData.put("progress", "100");
                        break;
                    }
                }
            } else {
                skillData.put("progress", recPer.get(perKey));
            }
            globalDataList.add(count, skillData);
        }
        if (operationData == null) {
            count++;
            String perKey = deptFlow + RegistryTypeEnum.OperationRegistry.getId();
            String reqKey = perKey + "ReqNum";

            operationData = new HashMap<String, Object>();
            operationData.put("title", "手术");
            operationData.put("order", count + 1);
            operationData.put("dataType", "operation");
//			operationData.put("label", "0%");
//			operationData.put("progress", "0");

            if (recPer.get(reqKey) == null || "0".equals(recPer.get(reqKey))) {
                operationData.put("progress", "-");
                List<Map<String, Object>> catagoryDataList = categoryProgress("operation", userFlow, deptFlow, doctor.getRotationFlow());
                for (Map<String, Object> catagoryData : catagoryDataList) {
                    if (!catagoryData.get("finishedNum").equals("0")) {
                        operationData.put("progress", "100");
                        break;
                    }
                }
            } else {
                operationData.put("progress", recPer.get(perKey));
            }
            globalDataList.add(count, operationData);
        }
		/*if(languageTeachingData==null){
			count++;
			String perKey = deptFlow+RegistryTypeEnum.LanguageTeachingResearch.getId();
			String reqKey = perKey+"ReqNum";

			languageTeachingData = new HashMap<String, Object>();
			languageTeachingData.put("title", "外语、教学与科研要求");
			languageTeachingData.put("order", count+1);
			languageTeachingData.put("dataType", "languageTeaching");
			if(recPer.get(reqKey)==null||"0".equals(recPer.get(reqKey)))
			{
				languageTeachingData.put("progress", "100");
			}else {
				languageTeachingData.put("progress", recPer.get(perKey));
			}
			globalDataList.add(count,languageTeachingData);
		}*/
        if (activityData == null) {
            count++;
            String perKey = deptFlow + RegistryTypeEnum.CampaignRegistry.getId() + "Finished";
            //参加的教学活动
            int activityNum = getJoinActivityNumByDeptFlow(deptFlow, userFlow);
//            activityNum += (recPer.get(perKey) == null ? 0 : Integer.valueOf(recPer.get(perKey))); // 这句删掉，上面的查询已经包含res_rec表的数据
            activityData = new HashMap<String, Object>();
            activityData.put("title", "活动");
            activityData.put("order", count + 1);
            activityData.put("dataType", "activity");
//            activityData.put("progress", recPer.get(perKey)); // 活动已经不从recPer中取了，这句也删掉
            activityData.put("label", "已登记" + activityNum + "条");
            globalDataList.add(count, activityData);
        }
//		if(evaluationData==null && isChargeOrg){
//			count++;
//			evaluationData = new HashMap<String, Object>();
//			evaluationData.put("title", "带教/科室评价");
//			evaluationData.put("order", count+1);
//			evaluationData.put("dataType", "evaluation");
//			evaluationData.put("progress", "");
//			evaluationData.put("label", "请于出科前完成评价");
//			globalDataList.add(count,evaluationData);
//		}
        boolean f = getCkkPower(userFlow);
        if (afterEvaluationData == null && f && !isChargeOrg) {
            count++;
            afterEvaluationData = new HashMap<String, Object>();
            afterEvaluationData.put("title", "出科理论考试");
            afterEvaluationData.put("order", count + 1);
            afterEvaluationData.put("dataType", "evaluationCkk");
//			afterEvaluationData.put("progress", "参加轮转科室的出科考核");
            afterEvaluationData.put("progress", "0");
            afterEvaluationData.put("label", "0%");
            globalDataList.add(count, afterEvaluationData);
        }
        if (uploadExitFormData == null) {
            count++;
            uploadExitFormData = new HashMap<String, Object>();
            uploadExitFormData.put("title", "出科考核表上传");
            uploadExitFormData.put("order", count + 1);
            uploadExitFormData.put("dataType", "uploadExitForm");

            ResSchProcessExpressExample example = new ResSchProcessExpressExample();
            example.createCriteria().andOperUserFlowEqualTo(userFlow).andSchRotationDeptFlowEqualTo(deptFlow)
                    .andRecTypeIdEqualTo(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<ResSchProcessExpress> records = expressMapper.selectByExampleWithBLOBs(example);
            if (records != null && records.size() > 0) {
                try {
                    Document doc = DocumentHelper.parseText(records.get(0).getRecContent());
                    Element rootEle = doc.getRootElement();
                    List<Element> elements = rootEle.elements();
                    ;
                    if (elements != null && elements.size() > 0) {
                        uploadExitFormData.put("label", "已上传");
                    } else {
                        uploadExitFormData.put("label", "未上传");
                    }
                } catch (DocumentException e) {
                    logger.error("", e);
                }
            } else {
                uploadExitFormData.put("label", "未上传");
            }
            uploadExitFormData.put("progress", "0");
            globalDataList.add(count, uploadExitFormData);
        }
        for (Map<String, Object> data : globalDataList) {
            String progress = StringUtil.defaultIfEmpty((String) data.get("progress"), "0");
            String dataType = (String) data.get("dataType");
            if (progress.equals("-")) {
                data.put("progress", "-");
                data.put("label", "-");
            } else {
                int iProgress = (int) Float.parseFloat(progress);
                if (iProgress > 100) {
                    data.put("progress", "100");
                    data.put("label", "100%");
                } else {
                    if (!"uploadExitForm".equals(dataType) && !"activity".equals(dataType) && !"evaluation".equals(dataType) && !"evaluationCkk".equals(dataType) && !"inProcessInfoList".equals(dataType)) {
                        data.put("progress", iProgress);
                        data.put("label", iProgress + "%");
                    } else {
                        data.put("progress", 0);
                    }
                }
            }
        }

        return globalDataList;
    }

    private int getJoinActivityNumByDeptFlow(String deptFlow, String userFlow) {
        return recExtMapper.getJoinActivityNumByDeptFlow(deptFlow, userFlow);
    }

    public List<Map<String, Object>> categoryProgress(String dataType, String userFlow, String deptFlow, String rotationFlow) {
        Map<String, String> finishPerMap = getProcessPer(userFlow, rotationFlow, deptFlow);
        if (finishPerMap == null) {
            finishPerMap = new HashMap<String, String>();
        }

        String recTypeId = "";
        switch (dataType) {
            case "mr":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId();
                break;
            case "disease":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.DiseaseRegistry.getId();
                break;
            case "skill":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId();
                break;
            case "operation":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.OperationRegistry.getId();
                break;
            case "activity":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
                break;
            case "summary":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId();
                break;
            case "languageTeaching":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.LanguageTeachingResearch.getId();
                break;
            default:
                break;
        }


        List<Map<String, Object>> categoryDataList = new ArrayList<Map<String, Object>>();


        if ("mr".equals(dataType) || "activity".equals(dataType) || "summary".equals(dataType) || "languageTeaching".equals(dataType)) {
//			Float needNum = (reqList.size()>0?reqList.get(0).getReqNum().floatValue():0);
//			Integer finishedNum = recList.size();
//			Float progress = 0f;
//			if(needNum != 0){
//				progress = (float) ((finishedNum/needNum) * 100);
//			}

            String needNum = finishPerMap.get(deptFlow + recTypeId + "ReqNum");
            String finishedNum = finishPerMap.get(deptFlow + recTypeId + "Finished");
            String progress = finishPerMap.get(deptFlow + recTypeId);
            needNum = StringUtil.defaultIfEmpty(needNum, "0");
            finishedNum = StringUtil.defaultIfEmpty(finishedNum, "0");
            progress = StringUtil.defaultIfEmpty(progress, "0");
            if ("mr".equals(dataType)) {

                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("cataFlow", dataType);
                dataMap.put("title", "大病历");
                dataMap.put("neededNum", needNum);
                dataMap.put("finishedNum", finishedNum);
                dataMap.put("progress", progress);
                if ("0".equals(needNum)) {
                    dataMap.put("progress", "100");
                }
                dataMap.put("labelsNum", 2);
                categoryDataList.add(dataMap);
            } else if ("languageTeaching".equals(dataType)) {

                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("cataFlow", dataType);
                dataMap.put("title", "外语、教学与科研要求");
                dataMap.put("neededNum", needNum);
                dataMap.put("finishedNum", finishedNum);
                dataMap.put("progress", progress);
                if ("0".equals(needNum)) {
                    dataMap.put("progress", "100");
                }
                dataMap.put("labelsNum", 2);
                categoryDataList.add(dataMap);
            } else if ("activity".equals(dataType)) {
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("cataFlow", dataType);
                dataMap.put("title", "活动");
                dataMap.put("neededNum", needNum);
                dataMap.put("finishedNum", finishedNum);
                dataMap.put("progress", progress);
                if ("0".equals(needNum)) {
                    dataMap.put("progress", "100");
                }
                dataMap.put("labelsNum", 2);
                categoryDataList.add(dataMap);
            } else if ("summary".equals(dataType)) {
                //此处不用，出科小结调用独立方法
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("cataFlow", dataType);
                dataMap.put("title", "出科考核表");
                dataMap.put("neededNum", needNum);
                dataMap.put("finishedNum", finishedNum);
                dataMap.put("progress", progress);
                if ("0".equals(needNum)) {
                    dataMap.put("progress", "100");
                }
                dataMap.put("labelsNum", 2);
                categoryDataList.add(dataMap);
            }
        } else {
//			Map<String,Integer> itemCountMap = new HashMap<String, Integer>();
//			for(ResRec rec : recList ){
//				Integer count = itemCountMap.get(rec.getItemId());
//				if(count == null){
//					count = 0;
//				}
//				itemCountMap.put(rec.getItemId(), ++count);
//			}

            //要求数
            SchRotationDeptReqExample reqExample = new SchRotationDeptReqExample();
            reqExample.createCriteria().andRotationFlowEqualTo(rotationFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andRelRecordFlowEqualTo(deptFlow).andRecTypeIdEqualTo(recTypeId);
            List<SchRotationDeptReq> reqList = schRotationDeptReqMapper.selectByExample(reqExample);

            for (SchRotationDeptReq req : reqList) {
                String itemId = req.getItemId();
                String itemNeedNum = finishPerMap.get(deptFlow + recTypeId + itemId + "ReqNum");
                String itemFinishedNum = finishPerMap.get(deptFlow + recTypeId + itemId + "Finished");
                String itemProgress = finishPerMap.get(deptFlow + recTypeId + itemId);
                itemNeedNum = StringUtil.defaultIfEmpty(itemNeedNum, "0");
                itemFinishedNum = StringUtil.defaultIfEmpty(itemFinishedNum, "0");
                itemProgress = StringUtil.defaultIfEmpty(itemProgress, "0");

                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("cataFlow", req.getItemId());
                dataMap.put("title", req.getItemName());
                dataMap.put("note", req.getReqNote());

//				Float needNum = req.getReqNum().floatValue();
//				Integer finishedNum = itemCountMap.get(req.getItemId())!=null?itemCountMap.get(req.getItemId()):0;
//				Float progress = 0f;
//				if(needNum != 0){
//					progress = (finishedNum/needNum) * 100;
//				}

                dataMap.put("neededNum", itemNeedNum);
                dataMap.put("finishedNum", itemFinishedNum);
                dataMap.put("progress", itemProgress);
                if ("0".equals(itemNeedNum)) {
                    dataMap.put("progress", "100");
                }
                dataMap.put("labelsNum", 2);
                categoryDataList.add(dataMap);
            }
        }
        for (Map<String, Object> data : categoryDataList) {
            String progress = StringUtil.defaultIfEmpty((String) data.get("progress"), "0");
            int iProgress = (int) Float.parseFloat(progress);
            if (iProgress > 100) {
                data.put("progress", "100");
            } else {
                data.put("progress", iProgress);
            }
        }
        return categoryDataList;
    }

    @Override
    public List<Map<String, Object>> categoryProgress(String dataType, String userFlow, String deptFlow, String rotationFlow, Integer pageIndex, Integer pageSize) {
        Map<String, String> finishPerMap = getProcessPer(userFlow, rotationFlow, deptFlow);
        if (finishPerMap == null) {
            finishPerMap = new HashMap<String, String>();
        }

        String recTypeId = "";
        switch (dataType) {
            case "mr":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId();
                break;
            case "disease":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.DiseaseRegistry.getId();
                break;
            case "skill":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId();
                break;
            case "operation":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.OperationRegistry.getId();
                break;
            case "activity":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
                break;
            case "summary":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId();
                break;
            case "languageTeaching":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.LanguageTeachingResearch.getId();
                break;
            default:
                break;
        }


        List<Map<String, Object>> categoryDataList = new ArrayList<Map<String, Object>>();


        if ("mr".equals(dataType) || "activity".equals(dataType) || "summary".equals(dataType) || "languageTeaching".equals(dataType)) {
//			Float needNum = (reqList.size()>0?reqList.get(0).getReqNum().floatValue():0);
//			Integer finishedNum = recList.size();
//			Float progress = 0f;
//			if(needNum != 0){
//				progress = (float) ((finishedNum/needNum) * 100);
//			}

            String needNum = finishPerMap.get(deptFlow + recTypeId + "ReqNum");
            String finishedNum = finishPerMap.get(deptFlow + recTypeId + "Finished");
            String progress = finishPerMap.get(deptFlow + recTypeId);
            needNum = StringUtil.defaultIfEmpty(needNum, "0");
            finishedNum = StringUtil.defaultIfEmpty(finishedNum, "0");
            progress = StringUtil.defaultIfEmpty(progress, "0");
            if ("mr".equals(dataType)) {

                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("cataFlow", dataType);
                dataMap.put("title", "大病历");
                dataMap.put("neededNum", needNum);
                dataMap.put("finishedNum", finishedNum);
                dataMap.put("progress", progress);
                if ("0".equals(needNum)) {
                    dataMap.put("progress", "100");
                }
                dataMap.put("labelsNum", 2);
                categoryDataList.add(dataMap);
            } else if ("languageTeaching".equals(dataType)) {

                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("cataFlow", dataType);
                dataMap.put("title", "外语、教学与科研要求");
                dataMap.put("neededNum", needNum);
                dataMap.put("finishedNum", finishedNum);
                dataMap.put("progress", progress);
                if ("0".equals(needNum)) {
                    dataMap.put("progress", "100");
                }
                dataMap.put("labelsNum", 2);
                categoryDataList.add(dataMap);
            } else if ("activity".equals(dataType)) {
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("cataFlow", dataType);
                dataMap.put("title", "活动");
                dataMap.put("neededNum", needNum);
                dataMap.put("finishedNum", finishedNum);
                dataMap.put("progress", progress);
                if ("0".equals(needNum)) {
                    dataMap.put("progress", "100");
                }
                dataMap.put("labelsNum", 2);
                categoryDataList.add(dataMap);
            } else if ("summary".equals(dataType)) {
                //此处不用，出科小结调用独立方法
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("cataFlow", dataType);
                dataMap.put("title", "出科考核表");
                dataMap.put("neededNum", needNum);
                dataMap.put("finishedNum", finishedNum);
                dataMap.put("progress", progress);
                if ("0".equals(needNum)) {
                    dataMap.put("progress", "100");
                }
                dataMap.put("labelsNum", 2);
                categoryDataList.add(dataMap);
            }
        } else {
//			Map<String,Integer> itemCountMap = new HashMap<String, Integer>();
//			for(ResRec rec : recList ){
//				Integer count = itemCountMap.get(rec.getItemId());
//				if(count == null){
//					count = 0;
//				}
//				itemCountMap.put(rec.getItemId(), ++count);
//			}

            //要求数
            PageHelper.startPage(pageIndex, pageSize);
            SchRotationDeptReqExample reqExample = new SchRotationDeptReqExample();
            reqExample.createCriteria().andRotationFlowEqualTo(rotationFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andRelRecordFlowEqualTo(deptFlow).andRecTypeIdEqualTo(recTypeId);
            List<SchRotationDeptReq> reqList = schRotationDeptReqMapper.selectByExample(reqExample);

            for (SchRotationDeptReq req : reqList) {
                String itemId = req.getItemId();
                String itemNeedNum = finishPerMap.get(deptFlow + recTypeId + itemId + "ReqNum");
                String itemFinishedNum = finishPerMap.get(deptFlow + recTypeId + itemId + "Finished");
                String itemProgress = finishPerMap.get(deptFlow + recTypeId + itemId);
                itemNeedNum = StringUtil.defaultIfEmpty(itemNeedNum, "0");
                itemFinishedNum = StringUtil.defaultIfEmpty(itemFinishedNum, "0");
                itemProgress = StringUtil.defaultIfEmpty(itemProgress, "0");

                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("cataFlow", req.getItemId());
                dataMap.put("title", req.getItemName());
                dataMap.put("note", req.getReqNote());

//				Float needNum = req.getReqNum().floatValue();
//				Integer finishedNum = itemCountMap.get(req.getItemId())!=null?itemCountMap.get(req.getItemId()):0;
//				Float progress = 0f;
//				if(needNum != 0){
//					progress = (finishedNum/needNum) * 100;
//				}

                dataMap.put("neededNum", itemNeedNum);
                dataMap.put("finishedNum", itemFinishedNum);
                dataMap.put("progress", itemProgress);
                if ("0".equals(itemNeedNum)) {
                    dataMap.put("progress", "100");
                }
                dataMap.put("labelsNum", 2);
                categoryDataList.add(dataMap);
            }
        }
        for (Map<String, Object> data : categoryDataList) {
            String progress = StringUtil.defaultIfEmpty((String) data.get("progress"), "0");
            int iProgress = (int) Float.parseFloat(progress);
            if (iProgress > 100) {
                data.put("progress", "100");
            } else {
                data.put("progress", iProgress);
            }
        }
        return categoryDataList;
    }


    public List<Map<String, Object>> _activityProgress(String userFlow, String deptFlow, Integer pageIndex, Integer pageSize) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("cataFlow", Long.MAX_VALUE);
        data.put("title", "各种活动");
        data.put("neededNum", "0");
        data.put("finishedNum", "0");
        data.put("auditedNum", "0");
        data.put("progress", "0");
        data.put("labelsNum", "0");
        dataList.add(data);
        return dataList;
    }

    public List<Map<String, Object>> _summaryProgress(String userFlow, String deptFlow, Integer pageIndex, Integer pageSize) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("cataFlow", Long.MAX_VALUE);
        data.put("title", "出科小结");
        data.put("neededNum", "0");
        data.put("finishedNum", "0");
        data.put("auditedNum", "0");
        data.put("progress", "0");
        data.put("labelsNum", "0");
        dataList.add(data);
        return dataList;
    }

    private Map<String, Object> getJoinActivity(String dataFlow, String userFlow, String deptFlow) {
        List<Map<String, Object>> maps = recExtMapper.getJoinActivity(dataFlow, userFlow, deptFlow);
        if (maps != null && maps.size() > 0) {
            return maps.get(0);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> dataList(String dataType, String userFlow, String deptFlow, String cataFlow) {
        String recTypeId = "";
        switch (dataType) {
            case "mr":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId();
                break;
            case "disease":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.DiseaseRegistry.getId();
                break;
            case "skill":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId();
                break;
            case "operation":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.OperationRegistry.getId();
                break;
            case "activity":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
                break;
            case "summary":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId();
                break;
            case "languageTeaching":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.LanguageTeachingResearch.getId();
                break;
            default:
                break;
        }

        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        if ("activity".equals(dataType)) {
//			List<Map<String,Object>> list = campaignRegistryExtMapper.queryJoinActivityAndEditList(userFlow, recTypeId, deptFlow);
            List<Map<String, Object>> list = recExtMapper.queryJoinActivityAndEditList(userFlow, recTypeId, deptFlow);
            if (list != null) {
                for (Map<String, Object> rec : list) {
                    Map<String, Object> formDataMap = new HashMap<>();
                    if (rec.get("recTypeId") != null && StringUtil.isNotBlank(String.valueOf(rec.get("recTypeId")))) {
                        String recContent = String.valueOf(rec.get("recContent"));
                        formDataMap = parseRecContent(recContent);
                    } else {
                        formDataMap.put("activity_way", rec.get("activityTypeName") == null ? "" : String.valueOf(rec.get("activityTypeName")));
                        formDataMap.put("activity_date", rec.get("startTime") == null ? "" : String.valueOf(rec.get("startTime")));
                        formDataMap.put("activity_period", "1");
                        formDataMap.put("activity_speaker", rec.get("speakerName") == null ? "" : String.valueOf(rec.get("speakerName")));
                        String activity_content = rec.get("activityName") == null ? "" : String.valueOf(rec.get("activityName")) +
                                rec.get("activityRemark") == null ? "" : String.valueOf(rec.get("activityRemark"));
                        formDataMap.put("activity_content", activity_content);
                        formDataMap.put("activity_address", rec.get("activityAddress") == null ? "" : String.valueOf(rec.get("activityAddress")));
                        formDataMap.put("isJoin", com.pinde.core.common.GlobalConstant.FLAG_Y);
                    }
                    formDataMap.put("dataFlow", String.valueOf(rec.get("recFlow")));
                    dataList.add(formDataMap);
                }
            }
        } else {
            ResRecExample recExample = new ResRecExample();
            ResRecExample.Criteria criteria = recExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(userFlow).andRecTypeIdEqualTo(recTypeId).andSchRotationDeptFlowEqualTo(deptFlow);
            if ("disease".equals(dataType) || "skill".equals(dataType) || "operation".equals(dataType) || "languageTeaching".equals(dataType)) {
                criteria.andItemIdEqualTo(cataFlow);
            }
//
            recExample.setOrderByClause("create_time desc,rec_flow");
            List<ResRec> recList = recMapper.selectByExampleWithBLOBs(recExample);
            for (ResRec rec : recList) {
                String recContent = rec.getRecContent();
                Map<String, Object> formDataMap = parseRecContent(recContent);
                formDataMap.put("dataFlow", rec.getRecFlow());

                dataList.add(formDataMap);
            }
        }
        return dataList;
    }

    @Override
    public Map<String, Object> parseRecContent(String content) {
        Map<String, Object> formDataMap = null;
        if (StringUtil.isNotBlank(content)) {
            formDataMap = new HashMap<String, Object>();
            try {
                Document document = DocumentHelper.parseText(content);
                Element rootElement = document.getRootElement();
                List<Element> elements = rootElement.elements();
                ;
                for (Element element : elements) {
                    if ("imageList".equals(element.getName())) {

                        List<Element> images = element.elements();
                        if (images != null) {
                            List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
                            for (Element ele : images) {
                                Map<String, String> map = new HashMap<String, String>();
                                map.put("imageFlow", ele.attributeValue("imageFlow"));
                                map.put("imageUrl", ele.elementText("imageUrl"));
                                map.put("thumbUrl", ele.elementText("thumbUrl"));
                                dataList.add(map);
                            }
                            formDataMap.put(element.getName(), dataList);
                        }
                    } else {
                        List<Node> valueNodes = element.selectNodes("value");
                        if (valueNodes != null && !valueNodes.isEmpty()) {
                            String value = "";
                            for (Node node : valueNodes) {
                                if (StringUtil.isNotBlank(value)) {
                                    value += ",";
                                }
                                value += node.getText();
                            }
                            formDataMap.put(element.getName(), value);
                        } else {
                            if (StringUtil.isNotBlank(element.attributeValue("id"))) {
                                formDataMap.put(element.getName() + "_id", element.attributeValue("id"));
                            }
                            formDataMap.put(element.getName(), element.getText());
                        }
                    }
                }
            } catch (DocumentException e) {
                logger.error("", e);
            }
        }
        return formDataMap;
    }


    @Override
    public List<Map<String, Object>> commReqOptionNameList(String userFlow, String deptFlow, String dataType, String catFlow) {
        String recTypeId = "";
        switch (dataType) {
            case "mr":
                recTypeId = RegistryTypeEnum.CaseRegistry.getId();
                break;
            case "disease":
                recTypeId = RegistryTypeEnum.DiseaseRegistry.getId();
                break;
            case "skill":
                recTypeId = RegistryTypeEnum.SkillRegistry.getId();
                break;
            case "operation":
                recTypeId = RegistryTypeEnum.OperationRegistry.getId();
                break;
            case "activity":
                recTypeId = RegistryTypeEnum.CampaignRegistry.getId();
                break;
            case "summary":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId();
                break;
            default:
                break;
        }
        SchRotationDeptReqExample reqExample = new SchRotationDeptReqExample();
        SchRotationDeptReqExample.Criteria criteria = reqExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).
                andRelRecordFlowEqualTo(deptFlow).andRecTypeIdEqualTo(recTypeId);
        if (StringUtil.isNotBlank(catFlow)) {
            criteria.andItemIdEqualTo(catFlow);
        }
        List<SchRotationDeptReq> reqList = schRotationDeptReqMapper.selectByExample(reqExample);
        List<Map<String, Object>> diseaseDiagNameList = new ArrayList<Map<String, Object>>();
        for (SchRotationDeptReq req : reqList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("itemId", req.getItemId());
            map.put("itemName", req.getItemName());
            diseaseDiagNameList.add(map);
        }
        return diseaseDiagNameList;
    }


    @Override
    public void addData(String dataType, String userFlow, String deptFlow, String cataFlow, Map<String, Object> paramMap, boolean isChargeOrg, String json) {

        //
        String recTypeId = "";
        String recTypeName = "";
        String itemId = "";
        String itemName = "";
        switch (dataType) {
            case "mr":
                recTypeId = RegistryTypeEnum.CaseRegistry.getId();
                recTypeName = RegistryTypeEnum.CaseRegistry.getName();
                itemId = "mr";
                itemName = "mr";
                break;
            case "disease":
                recTypeId = RegistryTypeEnum.DiseaseRegistry.getId();
                recTypeName = RegistryTypeEnum.DiseaseRegistry.getName();
                break;
            case "skill":
                recTypeId = RegistryTypeEnum.SkillRegistry.getId();
                recTypeName = RegistryTypeEnum.SkillRegistry.getName();
                break;
            case "operation":
                recTypeId = RegistryTypeEnum.OperationRegistry.getId();
                recTypeName = RegistryTypeEnum.OperationRegistry.getName();
                break;
            case "languageTeaching":
                recTypeId = RegistryTypeEnum.LanguageTeachingResearch.getId();
                recTypeName = RegistryTypeEnum.LanguageTeachingResearch.getName();
                break;
            case "activity":
                recTypeId = RegistryTypeEnum.CampaignRegistry.getId();
                recTypeName = RegistryTypeEnum.CampaignRegistry.getName();
                itemId = "activity";
                itemName = "activity";
                break;
            default:
                break;
        }

        ResDoctor docote = doctorMapper.selectByPrimaryKey(userFlow);
        ResRec record = new ResRec();
        record.setRecFlow(PkUtil.getUUID());
        record.setOrgFlow(docote.getOrgFlow());
        record.setOrgName(docote.getOrgName());
        record.setOperTime(DateUtil.getCurrDateTime());
        record.setOperUserFlow(userFlow);
        record.setOperUserName(docote.getDoctorName());
        record.setRecTypeId(recTypeId);
        record.setRecTypeName(recTypeName);


        if (StringUtil.isBlank(itemId) && StringUtil.isNotBlank(cataFlow)) {
            record.setItemId(cataFlow);
            //获取item
            SchRotationDeptReqExample reqExample = new SchRotationDeptReqExample();
            reqExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRelRecordFlowEqualTo(deptFlow).andItemIdEqualTo(cataFlow);
            List<SchRotationDeptReq> reqList = schRotationDeptReqMapper.selectByExample(reqExample);
            if (reqList.size() > 0) {
                record.setItemName(reqList.get(0).getItemName());
            }
        } else {
            record.setItemId(itemId);
            record.setItemName(itemName);
        }
        //标准科室主键
        record.setSchRotationDeptFlow(deptFlow);

        Element rootEle = DocumentHelper.createElement(recTypeId);
        for (Entry<String, Object> entry : paramMap.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            Element element = DocumentHelper.createElement(key);
            if ("mr_diagType".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("主要诊断");
                } else if ("2".equals(value)) {
                    element.setText("次要诊断");
                } else if ("3".equals(value)) {
                    element.setText("并行诊断");
                }
            } else if ("skill_operName".equals(entry.getKey())) {
                if ("00000000000000000000000000000000".equals(record.getItemId())) {
                    element.setText(value);
                } else {
                    element.addAttribute("id", record.getItemId());
                    element.setText(record.getItemName());
                }
            } else if ("skill_result".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("是");
                } else if ("0".equals(value)) {
                    element.setText("否");
                }
            } else if ("research_content".equals(entry.getKey())) {
                element.setText(value);
            } else if ("research_num".equals(entry.getKey())) {
                element.setText(value);
            } else if ("operation_operName".equals(entry.getKey())) {
                if ("00000000000000000000000000000000".equals(record.getItemId())) {
                    element.setText(value);
                } else {
                    element.addAttribute("id", record.getItemId());
                    element.setText(record.getItemName());
                }
            } else if ("operation_operRole".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("0".equals(value)) {
                    element.setText("术者");
                } else if ("1".equals(value)) {
                    element.setText("一助");
                } else if ("2".equals(value)) {
                    element.setText("二助");
                }
            } else if ("disease_diagName".equals(entry.getKey())) {
                if ("00000000000000000000000000000000".equals(record.getItemId())) {
                    element.setText(value);
                } else {
                    element.addAttribute("id", record.getItemId());
                    element.setText(record.getItemName());
                }
            } else if ("disease_diagType".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("主要诊断");
                } else if ("2".equals(value)) {
                    element.setText("次要诊断");
                } else if ("3".equals(value)) {
                    element.setText("并行诊断");
                }
            } else if ("disease_isCharge".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("是");
                } else if ("0".equals(value)) {
                    element.setText("否");
                }
            } else if ("disease_isRescue".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("是");
                } else if ("0".equals(value)) {
                    element.setText("否");
                }
            } else if ("disease_caseType".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("0".equals(value)) {
                    element.setText("新收病人");
                } else if ("1".equals(value)) {
                    element.setText("书写规范住院大病例");
                } else if ("2".equals(value)) {
                    element.setText("学习病例");
                }
            } else if ("activity_way".equals(entry.getKey())) {
                element.addAttribute("id", value);
                element.setText(ActivityTypeEnum.getNameById(value));
            } else if ("activity_period".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("1");
                } else if ("2".equals(value)) {
                    element.setText("2");
                } else if ("3".equals(value)) {
                    element.setText("3");
                } else if ("4".equals(value)) {
                    element.setText("4");
                } else if ("5".equals(value)) {
                    element.setText("5");
                }
            } else if ("resultFlow".equals(entry.getKey())) {
                if (isChargeOrg) {
                    SchArrangeResult result = resultMapper.selectByPrimaryKey(value);
                    record.setDeptFlow(result.getDeptFlow());
                    record.setDeptName(result.getDeptName());
                    record.setSchDeptFlow(result.getSchDeptFlow());
                    record.setSchDeptName(result.getSchDeptName());

                    ResDoctorSchProcess process = getProcessByResultFlow(value);
                    if (process != null) {
                        record.setProcessFlow(process.getProcessFlow());
                    }
                }
                element.setText(value);
            } else {
                element.setText(value);
            }
            rootEle.add(element);
        }
        if (StringUtil.isNotBlank(json)) {
            List<HashMap> list = JSON.parseArray(json, HashMap.class);
            if (list != null && list.size() > 0) {
                Element imageList = DocumentHelper.createElement("imageList");
                for (HashMap img : list) {
                    Element imgEle = DocumentHelper.createElement("image");
                    String fileFlow = (String) img.get("imageFlow");
                    imgEle.addAttribute("imageFlow", fileFlow);

                    Element imageUrl = DocumentHelper.createElement("imageUrl");
                    imageUrl.setText((String) img.get("imageUrl"));
                    Element thumbUrl = DocumentHelper.createElement("thumbUrl");
                    thumbUrl.setText((String) img.get("thumbUrl"));
                    imgEle.add(imageUrl);
                    imgEle.add(thumbUrl);
                    imageList.add(imgEle);
                }
                rootEle.add(imageList);
            }
        }
        SysUser user = readSysUser(userFlow);
        String medicineTypeId = "";
        if (user != null) medicineTypeId = user.getMedicineTypeId();
        record.setMedicineType(medicineTypeId);

        record.setRecContent(rootEle.asXML());
        record.setRecForm("jsst");
        record.setRecVersion("2.0");
        record.setCreateTime(DateUtil.getCurrDateTime());
        record.setCreateUserFlow(userFlow);
        record.setModifyTime(DateUtil.getCurrDateTime());
        record.setModifyUserFlow(userFlow);
        record.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//		if ("mr".equals(dataType)) {
//			ResRecCaseRegistry caseRegistry = new ResRecCaseRegistry();
//			BeanUtils.copyProperties(record, caseRegistry);
//			caseRegistryMapper.insert(caseRegistry);
//		}
//		if ("disease".equals(dataType)) {
//			ResRecDiseaseRegistry diseaseRegistry = new ResRecDiseaseRegistry();
//			BeanUtils.copyProperties(record, diseaseRegistry);
//			diseaseRegistryMapper.insert(diseaseRegistry);
//		}
//		if ("skill".equals(dataType)) {
//			ResRecSkillRegistry skillRegistry = new ResRecSkillRegistry();
//			BeanUtils.copyProperties(record, skillRegistry);
//			skillRegistryMapper.insert(skillRegistry);
//		}
//		if ("operation".equals(dataType)) {
//			ResRecOperationRegistry operationRegistry = new ResRecOperationRegistry();
//			BeanUtils.copyProperties(record, operationRegistry);
//			operationRegistryMapper.insert(operationRegistry);
//		}
//		if ("activity".equals(dataType)) {
//			ResRecCampaignRegistry campaignRegistry = new ResRecCampaignRegistry();
//			BeanUtils.copyProperties(record, campaignRegistry);
//			campaignRegistryMapper.insert(campaignRegistry);
//		}
//		if ("languageTeaching".equals(dataType)) {
//			ResRecLanguageRegistry languageRegistry = new ResRecLanguageRegistry();
//			BeanUtils.copyProperties(record, languageRegistry);
//			languageRegistryMapper.insert(languageRegistry);
//		}
        recMapper.insert(record);
        setRotationDeptPer(docote, deptFlow);
        //设置APP使用记录
        addAppUseInfo(userFlow);
    }

    @Override
    public void addData2(String dataType, String userFlow, String deptFlow, String cataFlow, JsResDataExt dataExt, boolean isChargeOrg) {
        String recTypeId = "";
        String recTypeName = "";
        String itemId = "";
        String itemName = "";
        switch (dataType) {
            case "mr":
                recTypeId = RegistryTypeEnum.CaseRegistry.getId();
                recTypeName = RegistryTypeEnum.CaseRegistry.getName();
                itemId = "mr";
                itemName = "mr";
                break;
            case "disease":
                recTypeId = RegistryTypeEnum.DiseaseRegistry.getId();
                recTypeName = RegistryTypeEnum.DiseaseRegistry.getName();
                break;
            case "skill":
                recTypeId = RegistryTypeEnum.SkillRegistry.getId();
                recTypeName = RegistryTypeEnum.SkillRegistry.getName();
                break;
            case "operation":
                recTypeId = RegistryTypeEnum.OperationRegistry.getId();
                recTypeName = RegistryTypeEnum.OperationRegistry.getName();
                break;
            case "activity":
                recTypeId = RegistryTypeEnum.CampaignRegistry.getId();
                recTypeName = RegistryTypeEnum.CampaignRegistry.getName();
                itemId = "activity";
                itemName = "activity";
                break;
            default:
                break;
        }

        ResDoctor docote = doctorMapper.selectByPrimaryKey(userFlow);
        ResRec record = new ResRec();
        record.setRecFlow(PkUtil.getUUID());
        record.setOrgFlow(docote.getOrgFlow());
        record.setOrgName(docote.getOrgName());
        record.setOperTime(DateUtil.getCurrDateTime());
        record.setOperUserFlow(userFlow);
        record.setOperUserName(docote.getDoctorName());
        record.setRecTypeId(recTypeId);
        record.setRecTypeName(recTypeName);


        if (StringUtil.isBlank(itemId) && StringUtil.isNotBlank(cataFlow)) {
            record.setItemId(cataFlow);
            //获取item
            SchRotationDeptReqExample reqExample = new SchRotationDeptReqExample();
            reqExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRelRecordFlowEqualTo(deptFlow).andItemIdEqualTo(cataFlow);
            List<SchRotationDeptReq> reqList = schRotationDeptReqMapper.selectByExample(reqExample);
            if (reqList.size() > 0) {
                record.setItemName(reqList.get(0).getItemName());
            }
        } else {
            record.setItemId(itemId);
            record.setItemName(itemName);
        }
        //标准科室主键
        record.setSchRotationDeptFlow(deptFlow);

        Element rootEle = DocumentHelper.createElement(recTypeId);

        Element userFlowElement = DocumentHelper.createElement("userFlow");
        userFlowElement.setText(dataExt.getUserFlow());
        rootEle.add(userFlowElement);

        Element deptElement = DocumentHelper.createElement("deptFlow");
        deptElement.setText(dataExt.getDeptFlow());
        rootEle.add(deptElement);

        Element cataElement = DocumentHelper.createElement("cataFlow");
        cataElement.setText(dataExt.getCataFlow());
        rootEle.add(cataElement);

        Element dataElement = DocumentHelper.createElement("dataType");
        dataElement.setText(dataExt.getDataType());
        rootEle.add(dataElement);

        if (StringUtil.isNotBlank(dataExt.getResultFlow())) {
            Element resultElement = DocumentHelper.createElement("resultFlow");
            if (isChargeOrg) {
                SchArrangeResult result = resultMapper.selectByPrimaryKey(dataExt.getResultFlow());
                record.setDeptFlow(result.getDeptFlow());
                record.setDeptName(result.getDeptName());
                record.setSchDeptFlow(result.getSchDeptFlow());
                record.setSchDeptName(result.getSchDeptName());

                ResDoctorSchProcess process = getProcessByResultFlow(dataExt.getResultFlow());
                if (process != null) {
                    record.setProcessFlow(process.getProcessFlow());
                }
            }
            resultElement.setText(dataExt.getResultFlow());
            rootEle.add(resultElement);
        }

        if (StringUtil.isNotBlank(dataExt.getDisease_pName())) {
            Element pnameElement = DocumentHelper.createElement("disease_pName");
            pnameElement.setText(dataExt.getDisease_pName());
            rootEle.add(pnameElement);
        }

        if (StringUtil.isNotBlank(dataExt.getMr_diagType())) {
            Element diagTypeElement = DocumentHelper.createElement("mr_diagType");
            diagTypeElement.addAttribute("id", dataExt.getMr_diagType());
            if ("1".equals(dataExt.getMr_diagType())) {
                diagTypeElement.setText("主要诊断");
            } else if ("2".equals(dataExt.getMr_diagType())) {
                diagTypeElement.setText("次要诊断");
            } else if ("3".equals(dataExt.getMr_diagType())) {
                diagTypeElement.setText("并行诊断");
            }
            rootEle.add(diagTypeElement);
        }

        if (StringUtil.isNotBlank(dataExt.getMr_no())) {
            Element mrNoElement = DocumentHelper.createElement("mr_no");
            mrNoElement.setText(dataExt.getMr_no());
            rootEle.add(mrNoElement);
        }

        if (StringUtil.isNotBlank(dataExt.getMr_pName())) {
            Element mrPnameElement = DocumentHelper.createElement("mr_pName");
            mrPnameElement.setText(dataExt.getMr_pName());
            rootEle.add(mrPnameElement);
        }

        if (StringUtil.isNotBlank(dataExt.getDisease_diagName())) {
            Element dDiagelement = DocumentHelper.createElement("disease_diagName");
            if ("00000000000000000000000000000000".equals(record.getItemId())) {
                dDiagelement.setText(dataExt.getDisease_diagName());
            } else {
                dDiagelement.addAttribute("id", record.getItemId());
                dDiagelement.setText(record.getItemName());
            }
            rootEle.add(dDiagelement);
        }

        if (StringUtil.isNotBlank(dataExt.getDisease_diagType())) {
            Element dDiagTypeElement = DocumentHelper.createElement("disease_diagType");
            dDiagTypeElement.addAttribute("id", dataExt.getDisease_diagType());
            if ("1".equals(dataExt.getDisease_diagType())) {
                dDiagTypeElement.setText("主要诊断");
            } else if ("2".equals(dataExt.getDisease_diagType())) {
                dDiagTypeElement.setText("次要诊断");
            } else if ("3".equals(dataExt.getDisease_diagType())) {
                dDiagTypeElement.setText("并行诊断");
            }
            rootEle.add(dDiagTypeElement);
        }

        if (StringUtil.isNotBlank(dataExt.getDisease_isCharge())) {
            Element dIsChargeElement = DocumentHelper.createElement("disease_isCharge");
            dIsChargeElement.addAttribute("id", dataExt.getDisease_isCharge());
            if ("1".equals(dataExt.getDisease_isCharge())) {
                dIsChargeElement.setText("是");
            } else if ("0".equals(dataExt.getDisease_isCharge())) {
                dIsChargeElement.setText("否");
            }
            rootEle.add(dIsChargeElement);
        }

        if (StringUtil.isNotBlank(dataExt.getDisease_isRescue())) {
            Element dIsRescueElement = DocumentHelper.createElement("disease_isRescue");
            dIsRescueElement.addAttribute("id", dataExt.getDisease_isRescue());
            if ("1".equals(dataExt.getDisease_isRescue())) {
                dIsRescueElement.setText("是");
            } else if ("0".equals(dataExt.getDisease_isRescue())) {
                dIsRescueElement.setText("否");
            }
            rootEle.add(dIsRescueElement);
        }

        if (StringUtil.isNotBlank(dataExt.getDisease_mrNo())) {
            Element dMrnoElement = DocumentHelper.createElement("disease_mrNo");
            dMrnoElement.setText(dataExt.getDisease_mrNo());
            rootEle.add(dMrnoElement);
        }

        if (StringUtil.isNotBlank(dataExt.getDisease_pDate())) {
            Element dPdateElement = DocumentHelper.createElement("disease_pDate");
            dPdateElement.setText(dataExt.getDisease_pDate());
            rootEle.add(dPdateElement);
        }

        if (StringUtil.isNotBlank(dataExt.getDisease_caseType())) {
            Element dPdateElement = DocumentHelper.createElement("disease_caseType");
            dPdateElement.setText(dataExt.getDisease_caseType());
            rootEle.add(dPdateElement);
        }

        if (StringUtil.isNotBlank(dataExt.getDisease_treatStep())) {
            Element dTreatStepElement = DocumentHelper.createElement("disease_treatStep");
            dTreatStepElement.setText(dataExt.getDisease_treatStep());
            rootEle.add(dTreatStepElement);
        }

        if (StringUtil.isNotBlank(dataExt.getFail_reason())) {
            Element faileReasonElement = DocumentHelper.createElement("fail_reason");
            faileReasonElement.setText(dataExt.getFail_reason());
            rootEle.add(faileReasonElement);
        }

        if (StringUtil.isNotBlank(dataExt.getSkill_mrNo())) {
            Element sMrNoElement = DocumentHelper.createElement("skill_mrNo");
            sMrNoElement.setText(dataExt.getSkill_mrNo());
            rootEle.add(sMrNoElement);
        }

        if (StringUtil.isNotBlank(dataExt.getSkill_operDate())) {
            Element sOperDateElement = DocumentHelper.createElement("skill_operDate");
            sOperDateElement.setText(dataExt.getSkill_operDate());
            rootEle.add(sOperDateElement);
        }

        if (StringUtil.isNotBlank(dataExt.getSkill_operName())) {
            Element sOperNameElement = DocumentHelper.createElement("skill_operName");
            if ("00000000000000000000000000000000".equals(record.getItemId())) {
                sOperNameElement.setText(dataExt.getSkill_operName());
            } else {
                sOperNameElement.addAttribute("id", record.getItemId());
                sOperNameElement.setText(record.getItemName());
            }
            rootEle.add(sOperNameElement);
        }

        if (StringUtil.isNotBlank(dataExt.getSkill_pName())) {
            Element spNameElement = DocumentHelper.createElement("skill_pName");
            spNameElement.setText(dataExt.getSkill_pName());
            rootEle.add(spNameElement);
        }

        if (StringUtil.isNotBlank(dataExt.getSkill_result())) {
            Element sResultElement = DocumentHelper.createElement("skill_result");
            sResultElement.addAttribute("id", dataExt.getSkill_result());
            if ("1".equals(dataExt.getSkill_result())) {
                sResultElement.setText("是");
            } else if ("0".equals(dataExt.getSkill_result())) {
                sResultElement.setText("否");
            }
            rootEle.add(sResultElement);
        }

        if (StringUtil.isNotBlank(dataExt.getOperation_memo())) {
            Element oMemoElement = DocumentHelper.createElement("operation_memo");
            oMemoElement.setText(dataExt.getOperation_memo());
            rootEle.add(oMemoElement);
        }

        if (StringUtil.isNotBlank(dataExt.getOperation_mrNo())) {
            Element oMrnoElement = DocumentHelper.createElement("operation_mrNo");
            oMrnoElement.setText(dataExt.getOperation_mrNo());
            rootEle.add(oMrnoElement);
        }

        if (StringUtil.isNotBlank(dataExt.getOperation_operDate())) {
            Element oOperDateElement = DocumentHelper.createElement("operation_operDate");
            oOperDateElement.setText(dataExt.getOperation_operDate());
            rootEle.add(oOperDateElement);
        }

        if (StringUtil.isNotBlank(dataExt.getOperation_operName())) {
            Element oOperNameElement = DocumentHelper.createElement("operation_operName");
            if ("00000000000000000000000000000000".equals(record.getItemId())) {
                oOperNameElement.setText(dataExt.getOperation_operName());
            } else {
                oOperNameElement.addAttribute("id", record.getItemId());
                oOperNameElement.setText(record.getItemName());
            }
            rootEle.add(oOperNameElement);
        }

        if (StringUtil.isNotBlank(dataExt.getOperation_operRole())) {
            Element oOperRoleElement = DocumentHelper.createElement("operation_operRole");
            oOperRoleElement.addAttribute("id", dataExt.getOperation_operName());
            if ("0".equals(dataExt.getOperation_operRole())) {
                oOperRoleElement.setText("术者");
            } else if ("1".equals(dataExt.getOperation_operRole())) {
                oOperRoleElement.setText("一助");
            } else if ("2".equals(dataExt.getOperation_operRole())) {
                oOperRoleElement.setText("二助");
            }
            rootEle.add(oOperRoleElement);
        }

        if (StringUtil.isNotBlank(dataExt.getOperation_pName())) {
            Element oOperpNameElement = DocumentHelper.createElement("operation_pName");
            oOperpNameElement.setText(dataExt.getOperation_pName());
            rootEle.add(oOperpNameElement);
        }

        if (StringUtil.isNotBlank(dataExt.getActivity_address())) {
            Element aAddressElement = DocumentHelper.createElement("activity_address");
            aAddressElement.setText(dataExt.getActivity_address());
            rootEle.add(aAddressElement);
        }

        if (StringUtil.isNotBlank(dataExt.getActivity_content())) {
            Element aContentElement = DocumentHelper.createElement("activity_content");
            aContentElement.setText(dataExt.getActivity_content());
            rootEle.add(aContentElement);
        }

        if (StringUtil.isNotBlank(dataExt.getActivity_date())) {
            Element aDateElement = DocumentHelper.createElement("activity_date");
            aDateElement.setText(dataExt.getActivity_date());
            rootEle.add(aDateElement);
        }

        if (StringUtil.isNotBlank(dataExt.getActivity_period())) {
            Element aPeriodElement = DocumentHelper.createElement("activity_period");
            aPeriodElement.addAttribute("id", dataExt.getActivity_period());
            if ("1".equals(dataExt.getActivity_period())) {
                aPeriodElement.setText("1");
            } else if ("2".equals(dataExt.getActivity_period())) {
                aPeriodElement.setText("2");
            } else if ("3".equals(dataExt.getActivity_period())) {
                aPeriodElement.setText("3");
            } else if ("4".equals(dataExt.getActivity_period())) {
                aPeriodElement.setText("4");
            } else if ("5".equals(dataExt.getActivity_period())) {
                aPeriodElement.setText("5");
            }
            rootEle.add(aPeriodElement);
        }

        if (StringUtil.isNotBlank(dataExt.getActivity_speaker())) {
            Element aSpeakerElement = DocumentHelper.createElement("activity_speaker");
            aSpeakerElement.setText(dataExt.getActivity_speaker());
            rootEle.add(aSpeakerElement);
        }

        if (StringUtil.isNotBlank(dataExt.getActivity_way())) {
            Element aWayElement = DocumentHelper.createElement("activity_way");
            aWayElement.addAttribute("id", dataExt.getActivity_way());
            String activityWayName = ActivityTypeEnum.getNameById(dataExt.getActivity_way());
            aWayElement.setText(activityWayName);
//            if ("1".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("教学查房");
//            }
//			else if("2".equals(dataExt.getActivity_way())){
//				aWayElement.setText("疑难病例讨论");
//			}else if("3".equals(dataExt.getActivity_way())){
//				aWayElement.setText("危重病例讨论");
//			}
//            else if ("4".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("临床小讲课");
//            }
//			else if("5".equals(dataExt.getActivity_way())){
//				aWayElement.setText("死亡病例讨论");
//			}
//            else if ("6".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("入轮转科室教育");
//            } else if ("7".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("出科考核");
//            } else if ("8".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("技能培训");
//            } else if ("9".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("教学阅片");
//            } else if ("10".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("门诊教学");
//            } else if ("11".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("教学病例讨论");
//            } else if ("2".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("疑难病例讨论");
//            } else if ("3".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("危重病例讨论");
//            } else if ("5".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("死亡病例讨论");
//            } else if ("12".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("临床操作技能床旁教学");
//            } else if ("13".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("住院病历书写指导教学");
//            } else if ("14".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("手术操作指导教学");
//            } else if ("15".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("影像诊断报告书写指导教学");
//            } else if ("16".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("临床文献研读会");
//            } else if ("17".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("入院教育");
//            } else if ("18".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("入专业基地教育");
//            } else if ("19".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("晨间报告");
//            } else if ("20".equals(dataExt.getActivity_way())) {
//                aWayElement.setText("报告单分析");
//            }
            rootEle.add(aWayElement);
        }

        if (StringUtil.isNotBlank(dataExt.getActivity_way_optionDesc())) {
            Element aOptionDescElement = DocumentHelper.createElement("activity_way_optionDesc");
            aOptionDescElement.setText(dataExt.getActivity_way_optionDesc());
            rootEle.add(aOptionDescElement);
        }

        List<JsResDataFile> jsonList = dataExt.getFiles();
        if (null != jsonList && jsonList.size() > 0) {
            Element imageList = DocumentHelper.createElement("imageList");
            for (JsResDataFile img : jsonList) {
                Element imgEle = DocumentHelper.createElement("image");
                String fileFlow = img.getImageFlow();
                imgEle.addAttribute("imageFlow", fileFlow);

                Element imageUrl = DocumentHelper.createElement("imageUrl");
                imageUrl.setText(img.getUrl());
                Element thumbUrl = DocumentHelper.createElement("thumbUrl");
                thumbUrl.setText(img.getThumbUrl());
                imgEle.add(imageUrl);
                imgEle.add(thumbUrl);
                imageList.add(imgEle);
            }
            rootEle.add(imageList);
        }
        SysUser user = readSysUser(userFlow);
        String medicineTypeId = "";
        if (user != null) medicineTypeId = user.getMedicineTypeId();
        record.setMedicineType(medicineTypeId);

        record.setRecContent(rootEle.asXML());
        record.setRecForm("jsst");
        record.setRecVersion("2.0");
        record.setCreateTime(DateUtil.getCurrDateTime());
        record.setCreateUserFlow(userFlow);
        record.setModifyTime(DateUtil.getCurrDateTime());
        record.setModifyUserFlow(userFlow);
        record.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        recMapper.insert(record);
        setRotationDeptPer(docote, deptFlow);
        //设置APP使用记录
        addAppUseInfo(userFlow);
    }

    private void addAppUseInfo(String userFlow) {
        String useMonth = DateUtil.getCurrMonth();
        String useYear = DateUtil.getYear();
        SysAppUserInfo info = getUserThisMonthUse(useMonth, userFlow);
        if (info == null) {
            info = new SysAppUserInfo();
            info.setInfoFlow(PkUtil.getUUID());
            info.setUseMonth(useMonth);
            info.setUseYear(useYear);
            info.setDocotrFlow(userFlow);
            info.setCreateTime(DateUtil.getCurrDateTime());
            info.setCreateUserFlow(userFlow);
            info.setModifyTime(DateUtil.getCurrDateTime());
            info.setModifyUserFlow(userFlow);
            info.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            appUserInfoMapper.insert(info);
        }
    }

    private SysAppUserInfo getUserThisMonthUse(String useMonth, String userFlow) {
        SysAppUserInfoExample example = new SysAppUserInfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDocotrFlowEqualTo(userFlow).andUseMonthEqualTo(useMonth);
        List<SysAppUserInfo> list = appUserInfoMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    private void setRotationDeptPer(ResDoctor docote, String deptFlow) {
        if (docote != null && StringUtil.isNotBlank(deptFlow)) {
            String allPer = "0";
            String processPer = "0";
            Map<String, String> finishPerMap = getProcessPer(docote.getDoctorFlow(), docote.getRotationFlow(), deptFlow);
            if (finishPerMap != null && StringUtil.isNotBlank(finishPerMap.get(deptFlow))) {
                allPer = finishPerMap.get(deptFlow);
                SchRotationDept dept = schRotationDeptMapper.selectByPrimaryKey(deptFlow);
                String groupFlow = dept.getGroupFlow();
                String standardDeptId = dept.getStandardDeptId();
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("doctorFlow", docote.getDoctorFlow());
                paramMap.put("standardGroupFlow", groupFlow);
                paramMap.put("standardDeptId", standardDeptId);
                paramMap.put("processFlow", deptFlow);
                List<ResRec> recs = searchProssingRec(paramMap);
                Map<String, String> finishPerMap2 = getProcessPer(docote.getDoctorFlow(), docote.getRotationFlow(), deptFlow, 0, recs);
                if (finishPerMap2 != null && StringUtil.isNotBlank(finishPerMap2.get(deptFlow))) {
                    processPer = finishPerMap2.get(deptFlow);
                }
            }
            ResStandardDeptPer per = null;
            ResStandardDeptPerExample example = new ResStandardDeptPerExample();
            example.createCriteria().andDoctorFlowEqualTo(docote.getDoctorFlow()).andSchRotationDeptFlowEqualTo(deptFlow)
                    .andRotationFlowEqualTo(docote.getRotationFlow())
                    .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            example.setOrderByClause("create_time desc");
            List<ResStandardDeptPer> pers = perMapper.selectByExample(example);
            if (pers != null && !pers.isEmpty()) {
                per = pers.get(0);
            }
            if (per == null) {
                per = new ResStandardDeptPer();
                per.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                per.setDoctorFlow(docote.getDoctorFlow());
                per.setSchRotationDeptFlow(deptFlow);
                per.setRotationFlow(docote.getRotationFlow());
                per.setRotationName(docote.getRotationName());
                per.setSessionNumber(docote.getSessionNumber());
            }
            if (StringUtil.isBlank(allPer)) {
                allPer = "0";
            }
            if (StringUtil.isBlank(processPer)) {
                processPer = "0";
            }
            per.setAllPer(allPer);
            per.setProcessPer(processPer);
            if (StringUtil.isNotBlank(per.getPerFlow())) {

                per.setModifyTime(DateUtil.getCurrDateTime());
                per.setModifyUserFlow(docote.getDoctorFlow());
                perMapper.updateByPrimaryKeySelective(per);
            } else {
                per.setPerFlow(PkUtil.getUUID());
                per.setCreateTime(DateUtil.getCurrDateTime());
                per.setCreateUserFlow(docote.getDoctorFlow());
                per.setModifyTime(DateUtil.getCurrDateTime());
                per.setModifyUserFlow(docote.getDoctorFlow());
                per.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                perMapper.insertSelective(per);
            }
        }
    }

    private List<ResRec> searchProssingRec(Map<String, Object> paramMap) {
        return recExtMapper.searchProssingRec(paramMap);
    }

    @Override
    public Map<String, Object> viewData(String dataType, String userFlow, String deptFlow, String dataFlow) {
//		ResRec rec = new ResRec();
//		if ("mr".equals(dataType)) {
//			ResRecCaseRegistry caseRegistry = caseRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(caseRegistry, rec);
//		}
//		if ("disease".equals(dataType)) {
//			ResRecDiseaseRegistry diseaseRegistry = diseaseRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(diseaseRegistry, rec);
//		}
//		if ("skill".equals(dataType)) {
//			ResRecSkillRegistry skillRegistry = skillRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(skillRegistry, rec);
//		}
//		if ("operation".equals(dataType)) {
//			ResRecOperationRegistry operationRegistry = operationRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(operationRegistry, rec);
//		}
//		if ("activity".equals(dataType)) {
//			ResRecCampaignRegistry campaignRegistry = campaignRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(campaignRegistry, rec);
//		}
//		if ("languageTeaching".equals(dataType)) {
//			ResRecLanguageRegistry languageRegistry = languageRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(languageRegistry, rec);
//		}
        ResRec rec = recMapper.selectByPrimaryKey(dataFlow);
        Map<String, Object> formDataMap = new HashMap<>();
        if ("activity".equals(dataType)) {
            if (null==rec  && StringUtil.isNotBlank(dataFlow)) {
                Map<String, Object> activity = getJoinActivity(dataFlow, userFlow, deptFlow);
                if (activity != null) {
                    formDataMap.put("activity_way", activity.get("activityTypeName") == null ? "" : String.valueOf(activity.get("activityTypeName")));
                    formDataMap.put("activity_way_id", activity.get("activityTypeId") == null ? "" : String.valueOf(activity.get("activityTypeId")));
                    formDataMap.put("activity_date", activity.get("startTime") == null ? "" : String.valueOf(activity.get("startTime")));
                    formDataMap.put("activity_period_id", "1");
                    formDataMap.put("activity_speaker", activity.get("speakerName") == null ? "" : String.valueOf(activity.get("speakerName")));
                    String activity_content = activity.get("activityName") == null ? "" : String.valueOf(activity.get("activityName")) + " " +
                            (activity.get("activityRemark") == null ? "" : String.valueOf(activity.get("activityRemark")));
                    formDataMap.put("activity_content", activity_content);
                    formDataMap.put("activity_address", activity.get("activityAddress") == null ? "" : String.valueOf(activity.get("activityAddress")));
                    formDataMap.put("resultFlow", activity.get("resultFlow") == null ? "" : String.valueOf(activity.get("resultFlow")));
                    formDataMap.put("isJoin", com.pinde.core.common.GlobalConstant.FLAG_Y);
                }
            } else {
                String recContent = rec.getRecContent();
                formDataMap = parseRecContent(recContent);
            }
        } else {
            String recContent = rec.getRecContent();
            formDataMap = parseRecContent(recContent);
        }
        return formDataMap;
    }


    @Override
    public void modData(String dataType, String userFlow, String deptFlow, String dataFlow, String cataFlow, Map<String, Object> paramMap, boolean isChargeOrg, String json) {
//		ResRec record = new ResRec();
//		if ("mr".equals(dataType)) {
//			ResRecCaseRegistry caseRegistry = caseRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(caseRegistry, record);
//		}
//		if ("disease".equals(dataType)) {
//			ResRecDiseaseRegistry diseaseRegistry = diseaseRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(diseaseRegistry, record);
//		}
//		if ("skill".equals(dataType)) {
//			ResRecSkillRegistry skillRegistry = skillRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(skillRegistry, record);
//		}
//		if ("operation".equals(dataType)) {
//			ResRecOperationRegistry operationRegistry = operationRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(operationRegistry, record);
//		}
//		if ("activity".equals(dataType)) {
//			ResRecCampaignRegistry campaignRegistry = campaignRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(campaignRegistry, record);
//		}
//		if ("languageTeaching".equals(dataType)) {
//			ResRecLanguageRegistry languageRegistry = languageRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(languageRegistry, record);
//		}
        ResRec record = recMapper.selectByPrimaryKey(dataFlow);
        try {
            Document doc = DocumentHelper.parseText(record.getRecContent());
            Element rootEle = doc.getRootElement();
            for (Entry<String, Object> entry : paramMap.entrySet()) {
                String key = entry.getKey();
                String value = String.valueOf(entry.getValue());
                Element element = rootEle.element(key);
                if (element != null) {
                    if ("mr_diagType".equals(entry.getKey())) {
                        element.addAttribute("id", value);
                        if ("1".equals(value)) {
                            element.setText("主要诊断");
                        } else if ("2".equals(value)) {
                            element.setText("次要诊断");
                        } else if ("3".equals(value)) {
                            element.setText("并行诊断");
                        }
                    } else if ("skill_operName".equals(entry.getKey())) {
                        if ("00000000000000000000000000000000".equals(record.getItemId())) {
                            element.setText(value);
                        }
                    } else if ("skill_result".equals(entry.getKey())) {
                        element.addAttribute("id", value);
                        if ("1".equals(value)) {
                            element.setText("是");
                        } else if ("0".equals(value)) {
                            element.setText("否");
                        }
                    } else if ("operation_operName".equals(entry.getKey())) {
                        if ("00000000000000000000000000000000".equals(record.getItemId())) {
                            element.setText(value);
                        }
                    } else if ("operation_operRole".equals(entry.getKey())) {
                        element.addAttribute("id", value);
                        if ("0".equals(value)) {
                            element.setText("术者");
                        } else if ("1".equals(value)) {
                            element.setText("一助");
                        } else if ("2".equals(value)) {
                            element.setText("二助");
                        }
                    } else if ("disease_diagName".equals(entry.getKey())) {
                        if ("00000000000000000000000000000000".equals(record.getItemId())) {
                            element.setText(value);
                        }
                    } else if ("disease_diagType".equals(entry.getKey())) {
                        element.addAttribute("id", value);
                        if ("1".equals(value)) {
                            element.setText("主要诊断");
                        } else if ("2".equals(value)) {
                            element.setText("次要诊断");
                        } else if ("3".equals(value)) {
                            element.setText("并行诊断");
                        }
                    } else if ("disease_isCharge".equals(entry.getKey())) {
                        element.addAttribute("id", value);
                        if ("1".equals(value)) {
                            element.setText("是");
                        } else if ("0".equals(value)) {
                            element.setText("否");
                        }
                    } else if ("disease_isRescue".equals(entry.getKey())) {
                        element.addAttribute("id", value);
                        if ("1".equals(value)) {
                            element.setText("是");
                        } else if ("0".equals(value)) {
                            element.setText("否");
                        }
                    } else if ("disease_caseType".equals(entry.getKey())) {
                        element.addAttribute("id", value);
                        if ("0".equals(value)) {
                            element.setText("新收病人");
                        } else if ("1".equals(value)) {
                            element.setText("书写规范住院大病例");
                        } else if ("2".equals(value)) {
                            element.setText("学习病例");
                        }
                    } else if ("activity_way".equals(entry.getKey())) {
                        element.addAttribute("id", value);
                        String activityWayName = ActivityTypeEnum.getNameById(value);
                        element.setText(activityWayName);
//                        if ("1".equals(value)) {
//                            element.setText("教学查房");
//                        }
//				else if("2".equals(value)){
//					element.setText("疑难病例讨论");
//				}else if("3".equals(value)){
//					element.setText("危重病例讨论");
//				}
//                        else if ("4".equals(value)) {
//                            element.setText("临床小讲课");
//                        }
//				else if("5".equals(value)){
//					element.setText("死亡病例讨论");
//				}
//                        else if ("6".equals(value)) {
//                            element.setText("入轮转科室教育");
//                        } else if ("7".equals(value)) {
//                            element.setText("出科考核");
//                        } else if ("8".equals(value)) {
//                            element.setText("技能培训");
//                        } else if ("9".equals(value)) {
//                            element.setText("教学阅片");
//                        } else if ("10".equals(value)) {
//                            element.setText("门诊教学");
//                        } else if ("11".equals(value)) {
//                            element.setText("教学病例讨论");
//                        } else if ("2".equals(value)) {
//                            element.setText("疑难病例讨论");
//                        } else if ("3".equals(value)) {
//                            element.setText("危重病例讨论");
//                        } else if ("5".equals(value)) {
//                            element.setText("死亡病例讨论");
//                        } else if ("12".equals(value)) {
//                            element.setText("临床操作技能床旁教学");
//                        } else if ("13".equals(value)) {
//                            element.setText("住院病历书写指导教学");
//                        } else if ("14".equals(value)) {
//                            element.setText("手术操作指导教学");
//                        } else if ("15".equals(value)) {
//                            element.setText("影像诊断报告书写指导教学");
//                        } else if ("16".equals(value)) {
//                            element.setText("临床文献研读会");
//                        } else if ("17".equals(value)) {
//                            element.setText("入院教育");
//                        } else if ("18".equals(value)) {
//                            element.setText("入专业基地教育");
//                        } else if ("19".equals(value)) {
//                            element.setText("晨间报告");
//                        }
                    } else if ("activity_period".equals(entry.getKey())) {
                        element.addAttribute("id", value);
                        if ("1".equals(value)) {
                            element.setText("1");
                        } else if ("2".equals(value)) {
                            element.setText("2");
                        } else if ("3".equals(value)) {
                            element.setText("3");
                        } else if ("4".equals(value)) {
                            element.setText("4");
                        } else if ("5".equals(value)) {
                            element.setText("5");
                        }
                    } else if ("resultFlow".equals(entry.getKey())) {
                        if (isChargeOrg) {
                            SchArrangeResult result = resultMapper.selectByPrimaryKey(value);
                            record.setDeptFlow(result.getDeptFlow());
                            record.setDeptName(result.getDeptName());
                            record.setSchDeptFlow(result.getSchDeptFlow());
                            record.setSchDeptName(result.getSchDeptName());

                            ResDoctorSchProcess process = getProcessByResultFlow(value);
                            if (process != null) {
                                record.setProcessFlow(process.getProcessFlow());
                            }
                        }
                        element.setText(value);
                    } else {
                        element.setText(value);
                    }
                } else {
                    element = DocumentHelper.createElement(key);
                    element.setText(value);
                    if ("resultFlow".equals(entry.getKey())) {
                        SchArrangeResult result = resultMapper.selectByPrimaryKey(value);
                        record.setDeptFlow(result.getDeptFlow());
                        record.setDeptName(result.getDeptName());
                        record.setSchDeptFlow(result.getSchDeptFlow());
                        record.setSchDeptName(result.getSchDeptName());

                        ResDoctorSchProcess process = getProcessByResultFlow(value);
                        if (process != null) {
                            record.setProcessFlow(process.getProcessFlow());
                        }
                    }
                    rootEle.add(element);
                }
            }
            if (StringUtil.isNotBlank(json)) {
                List<HashMap> list = JSON.parseArray(json, HashMap.class);
                if (list != null && list.size() > 0) {
                    Element imageList = DocumentHelper.createElement("imageList");
                    for (HashMap img : list) {
                        Element imgEle = DocumentHelper.createElement("image");
                        String fileFlow = (String) img.get("imageFlow");
                        imgEle.addAttribute("imageFlow", fileFlow);

                        Element imageUrl = DocumentHelper.createElement("imageUrl");
                        imageUrl.setText((String) img.get("imageUrl"));
                        Element thumbUrl = DocumentHelper.createElement("thumbUrl");
                        thumbUrl.setText((String) img.get("thumbUrl"));
                        imgEle.add(imageUrl);
                        imgEle.add(thumbUrl);
                        imageList.add(imgEle);
                    }
                    rootEle.add(imageList);
                }
            }
            record.setRecContent(doc.asXML());
        } catch (DocumentException e) {
            logger.error("", e);
        }
        record.setModifyTime(DateUtil.getCurrDateTime());
        record.setModifyUserFlow(userFlow);
//		if ("mr".equals(dataType)) {
//			ResRecCaseRegistry caseRegistry = new ResRecCaseRegistry();
//			BeanUtils.copyProperties(record, caseRegistry);
//			caseRegistryMapper.updateByPrimaryKeySelective(caseRegistry);
//		}
//		if ("disease".equals(dataType)) {
//			ResRecDiseaseRegistry diseaseRegistry = new ResRecDiseaseRegistry();
//			BeanUtils.copyProperties(record, diseaseRegistry);
//			diseaseRegistryMapper.updateByPrimaryKeySelective(diseaseRegistry);
//		}
//		if ("skill".equals(dataType)) {
//			ResRecSkillRegistry skillRegistry = new ResRecSkillRegistry();
//			BeanUtils.copyProperties(record, skillRegistry);
//			skillRegistryMapper.updateByPrimaryKeySelective(skillRegistry);
//		}
//		if ("operation".equals(dataType)) {
//			ResRecOperationRegistry operationRegistry = new ResRecOperationRegistry();
//			BeanUtils.copyProperties(record, operationRegistry);
//			operationRegistryMapper.updateByPrimaryKeySelective(operationRegistry);
//		}
//		if ("activity".equals(dataType)) {
//			ResRecCampaignRegistry campaignRegistry = new ResRecCampaignRegistry();
//			BeanUtils.copyProperties(record, campaignRegistry);
//			campaignRegistryMapper.updateByPrimaryKeySelective(campaignRegistry);
//		}
//		if ("languageTeaching".equals(dataType)) {
//			ResRecLanguageRegistry languageRegistry = new ResRecLanguageRegistry();
//			BeanUtils.copyProperties(record, languageRegistry);
//			languageRegistryMapper.updateByPrimaryKeySelective(languageRegistry);
//		}
        recMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void modData2(JsResDataExt dataExt, boolean isChargeOrg) {
        ResRec record = recMapper.selectByPrimaryKey(dataExt.getDataFlow());
        try {
            Document doc = DocumentHelper.parseText(record.getRecContent());
            Element rootEle = doc.getRootElement();

            Element userFlowElement = rootEle.element("userFlow");
            if (null != userFlowElement) {
                userFlowElement.setText(dataExt.getUserFlow());
            } else {
                userFlowElement = DocumentHelper.createElement("userFlow");
                userFlowElement.setText(dataExt.getUserFlow());
                rootEle.add(userFlowElement);
            }

            Element deptElement = rootEle.element("deptFlow");
            if (null != deptElement) {
                deptElement.setText(dataExt.getDeptFlow());
            } else {
                deptElement = DocumentHelper.createElement("deptFlow");
                deptElement.setText(dataExt.getDeptFlow());
                rootEle.add(deptElement);
            }

            Element cataElement = rootEle.element("cataFlow");
            if (null != cataElement) {
                cataElement.setText(dataExt.getCataFlow());
            } else {
                cataElement = DocumentHelper.createElement("cataFlow");
                cataElement.setText(dataExt.getCataFlow());
                rootEle.add(cataElement);
            }

            Element dataElement = rootEle.element("dataType");
            if (null != dataElement) {
                dataElement.setText(dataExt.getDataType());
            } else {
                dataElement = DocumentHelper.createElement("dataType");
                dataElement.setText(dataExt.getDataType());
                rootEle.add(dataElement);
            }

            if (StringUtil.isNotBlank(dataExt.getResultFlow())) {
                Element resultElement = rootEle.element("resultFlow");
                if (null != resultElement) {
                    if (isChargeOrg) {
                        SchArrangeResult result = resultMapper.selectByPrimaryKey(dataExt.getResultFlow());
                        record.setDeptFlow(result.getDeptFlow());
                        record.setDeptName(result.getDeptName());
                        record.setSchDeptFlow(result.getSchDeptFlow());
                        record.setSchDeptName(result.getSchDeptName());

                        ResDoctorSchProcess process = getProcessByResultFlow(dataExt.getResultFlow());
                        if (process != null) {
                            record.setProcessFlow(process.getProcessFlow());
                        }
                    }
                    resultElement.setText(dataExt.getResultFlow());
                } else {
                    resultElement = DocumentHelper.createElement("resultFlow");
                    if (isChargeOrg) {
                        SchArrangeResult result = resultMapper.selectByPrimaryKey(dataExt.getResultFlow());
                        record.setDeptFlow(result.getDeptFlow());
                        record.setDeptName(result.getDeptName());
                        record.setSchDeptFlow(result.getSchDeptFlow());
                        record.setSchDeptName(result.getSchDeptName());

                        ResDoctorSchProcess process = getProcessByResultFlow(dataExt.getResultFlow());
                        if (process != null) {
                            record.setProcessFlow(process.getProcessFlow());
                        }
                    }
                    resultElement.setText(dataExt.getResultFlow());
                    rootEle.add(resultElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getDisease_pName())) {
                Element pnameElement = rootEle.element("disease_pName");
                if (null != pnameElement) {
                    pnameElement.setText(dataExt.getDisease_pName());
                } else {
                    pnameElement = DocumentHelper.createElement("disease_pName");
                    pnameElement.setText(dataExt.getDisease_pName());
                    rootEle.add(pnameElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getMr_diagType())) {
                Element diagTypeElement = rootEle.element("mr_diagType");
                if (null != diagTypeElement) {
                    diagTypeElement.addAttribute("id", dataExt.getMr_diagType());
                    if ("1".equals(dataExt.getMr_diagType())) {
                        diagTypeElement.setText("主要诊断");
                    } else if ("2".equals(dataExt.getMr_diagType())) {
                        diagTypeElement.setText("次要诊断");
                    } else if ("3".equals(dataExt.getMr_diagType())) {
                        diagTypeElement.setText("并行诊断");
                    }
                } else {
                    diagTypeElement = DocumentHelper.createElement("mr_diagType");
                    diagTypeElement.addAttribute("id", dataExt.getMr_diagType());
                    if ("1".equals(dataExt.getMr_diagType())) {
                        diagTypeElement.setText("主要诊断");
                    } else if ("2".equals(dataExt.getMr_diagType())) {
                        diagTypeElement.setText("次要诊断");
                    } else if ("3".equals(dataExt.getMr_diagType())) {
                        diagTypeElement.setText("并行诊断");
                    }
                    rootEle.add(diagTypeElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getMr_no())) {
                Element mrNoElement = rootEle.element("mr_no");
                if (null != mrNoElement) {
                    mrNoElement.setText(dataExt.getMr_no());
                } else {
                    mrNoElement = DocumentHelper.createElement("mr_no");
                    mrNoElement.setText(dataExt.getMr_no());
                    rootEle.add(mrNoElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getMr_pName())) {
                Element mrPnameElement = rootEle.element("mr_pName");
                if (null != mrPnameElement) {
                    mrPnameElement.setText(dataExt.getMr_pName());
                } else {
                    mrPnameElement = DocumentHelper.createElement("mr_pName");
                    mrPnameElement.setText(dataExt.getMr_pName());
                    rootEle.add(mrPnameElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getDisease_diagName())) {
                Element dDiagelement = rootEle.element("disease_diagName");
                if (null != dDiagelement) {
                    if ("00000000000000000000000000000000".equals(record.getItemId())) {
                        dDiagelement.setText(dataExt.getDisease_diagName());
                    } else {
                        dDiagelement.addAttribute("id", record.getItemId());
                        dDiagelement.setText(record.getItemName());
                    }
                } else {
                    dDiagelement = DocumentHelper.createElement("disease_diagName");
                    if ("00000000000000000000000000000000".equals(record.getItemId())) {
                        dDiagelement.setText(dataExt.getDisease_diagName());
                    } else {
                        dDiagelement.addAttribute("id", record.getItemId());
                        dDiagelement.setText(record.getItemName());
                    }
                    rootEle.add(dDiagelement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getDisease_diagType())) {
                Element dDiagTypeElement = rootEle.element("disease_diagType");
                if (null != dDiagTypeElement) {
                    dDiagTypeElement.addAttribute("id", dataExt.getDisease_diagType());
                    if ("1".equals(dataExt.getDisease_diagType())) {
                        dDiagTypeElement.setText("主要诊断");
                    } else if ("2".equals(dataExt.getDisease_diagType())) {
                        dDiagTypeElement.setText("次要诊断");
                    } else if ("3".equals(dataExt.getDisease_diagType())) {
                        dDiagTypeElement.setText("并行诊断");
                    }
                } else {
                    dDiagTypeElement = DocumentHelper.createElement("disease_diagType");
                    dDiagTypeElement.addAttribute("id", dataExt.getDisease_diagType());
                    if ("1".equals(dataExt.getDisease_diagType())) {
                        dDiagTypeElement.setText("主要诊断");
                    } else if ("2".equals(dataExt.getDisease_diagType())) {
                        dDiagTypeElement.setText("次要诊断");
                    } else if ("3".equals(dataExt.getDisease_diagType())) {
                        dDiagTypeElement.setText("并行诊断");
                    }
                    rootEle.add(dDiagTypeElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getDisease_isCharge())) {
                Element dIsChargeElement = rootEle.element("disease_isCharge");
                if (null != dIsChargeElement) {
                    dIsChargeElement.addAttribute("id", dataExt.getDisease_isCharge());
                    if ("1".equals(dataExt.getDisease_isCharge())) {
                        dIsChargeElement.setText("是");
                    } else if ("0".equals(dataExt.getDisease_isCharge())) {
                        dIsChargeElement.setText("否");
                    }
                } else {
                    dIsChargeElement = DocumentHelper.createElement("disease_isCharge");
                    dIsChargeElement.addAttribute("id", dataExt.getDisease_isCharge());
                    if ("1".equals(dataExt.getDisease_isCharge())) {
                        dIsChargeElement.setText("是");
                    } else if ("0".equals(dataExt.getDisease_isCharge())) {
                        dIsChargeElement.setText("否");
                    }
                    rootEle.add(dIsChargeElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getDisease_isRescue())) {
                Element dIsRescueElement = rootEle.element("disease_isRescue");
                if (null != dIsRescueElement) {
                    dIsRescueElement.addAttribute("id", dataExt.getDisease_isRescue());
                    if ("1".equals(dataExt.getDisease_isRescue())) {
                        dIsRescueElement.setText("是");
                    } else if ("0".equals(dataExt.getDisease_isRescue())) {
                        dIsRescueElement.setText("否");
                    }
                } else {
                    dIsRescueElement = DocumentHelper.createElement("disease_isRescue");
                    dIsRescueElement.addAttribute("id", dataExt.getDisease_isRescue());
                    if ("1".equals(dataExt.getDisease_isRescue())) {
                        dIsRescueElement.setText("是");
                    } else if ("0".equals(dataExt.getDisease_isRescue())) {
                        dIsRescueElement.setText("否");
                    }
                    rootEle.add(dIsRescueElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getDisease_mrNo())) {
                Element dMrnoElement = rootEle.element("disease_mrNo");
                if (null != dMrnoElement) {
                    dMrnoElement.setText(dataExt.getDisease_mrNo());
                } else {
                    dMrnoElement = DocumentHelper.createElement("disease_mrNo");
                    dMrnoElement.setText(dataExt.getDisease_mrNo());
                    rootEle.add(dMrnoElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getDisease_pDate())) {
                Element dPdateElement = rootEle.element("disease_pDate");
                if (null != dPdateElement) {
                    dPdateElement.setText(dataExt.getDisease_pDate());
                } else {
                    dPdateElement = DocumentHelper.createElement("disease_pDate");
                    dPdateElement.setText(dataExt.getDisease_pDate());
                    rootEle.add(dPdateElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getDisease_treatStep())) {
                Element dTreatStepElement = rootEle.element("disease_treatStep");
                if (null != dTreatStepElement) {
                    dTreatStepElement.setText(dataExt.getDisease_treatStep());
                } else {
                    dTreatStepElement = DocumentHelper.createElement("disease_treatStep");
                    dTreatStepElement.setText(dataExt.getDisease_treatStep());
                    rootEle.add(dTreatStepElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getFail_reason())) {
                Element faileReasonElement = rootEle.element("fail_reason");
                if (null != faileReasonElement) {
                    faileReasonElement.setText(dataExt.getFail_reason());
                } else {
                    faileReasonElement = DocumentHelper.createElement("fail_reason");
                    faileReasonElement.setText(dataExt.getFail_reason());
                    rootEle.add(faileReasonElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getSkill_mrNo())) {
                Element sMrNoElement = rootEle.element("skill_mrNo");
                if (null != sMrNoElement) {
                    sMrNoElement.setText(dataExt.getSkill_mrNo());
                } else {
                    sMrNoElement = DocumentHelper.createElement("skill_mrNo");
                    sMrNoElement.setText(dataExt.getSkill_mrNo());
                    rootEle.add(sMrNoElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getSkill_operDate())) {
                Element sOperDateElement = rootEle.element("skill_operDate");
                if (null != sOperDateElement) {
                    sOperDateElement.setText(dataExt.getSkill_operDate());
                } else {
                    sOperDateElement = DocumentHelper.createElement("skill_operDate");
                    sOperDateElement.setText(dataExt.getSkill_operDate());
                    rootEle.add(sOperDateElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getSkill_operName())) {
                Element sOperNameElement = rootEle.element("skill_operName");
                if (null != sOperNameElement) {
                    if ("00000000000000000000000000000000".equals(record.getItemId())) {
                        sOperNameElement.setText(dataExt.getSkill_operName());
                    } else {
                        sOperNameElement.addAttribute("id", record.getItemId());
                        sOperNameElement.setText(record.getItemName());
                    }
                } else {
                    sOperNameElement = DocumentHelper.createElement("skill_operName");
                    if ("00000000000000000000000000000000".equals(record.getItemId())) {
                        sOperNameElement.setText(dataExt.getSkill_operName());
                    } else {
                        sOperNameElement.addAttribute("id", record.getItemId());
                        sOperNameElement.setText(record.getItemName());
                    }
                    rootEle.add(sOperNameElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getSkill_pName())) {
                Element spNameElement = rootEle.element("skill_pName");
                if (null != spNameElement) {
                    spNameElement.setText(dataExt.getSkill_pName());
                } else {
                    spNameElement = DocumentHelper.createElement("skill_pName");
                    spNameElement.setText(dataExt.getSkill_pName());
                    rootEle.add(spNameElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getSkill_result())) {
                Element sResultElement = rootEle.element("skill_result");
                if (null != sResultElement) {
                    sResultElement.addAttribute("id", dataExt.getSkill_result());
                    if ("1".equals(dataExt.getSkill_result())) {
                        sResultElement.setText("是");
                    } else if ("0".equals(dataExt.getSkill_result())) {
                        sResultElement.setText("否");
                    }
                } else {
                    sResultElement = DocumentHelper.createElement("skill_result");
                    sResultElement.addAttribute("id", dataExt.getSkill_result());
                    if ("1".equals(dataExt.getSkill_result())) {
                        sResultElement.setText("是");
                    } else if ("0".equals(dataExt.getSkill_result())) {
                        sResultElement.setText("否");
                    }
                    rootEle.add(sResultElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getOperation_memo())) {
                Element oMemoElement = rootEle.element("operation_memo");
                if (null != oMemoElement) {
                    oMemoElement.setText(dataExt.getOperation_memo());
                } else {
                    oMemoElement = DocumentHelper.createElement("operation_memo");
                    oMemoElement.setText(dataExt.getOperation_memo());
                    rootEle.add(oMemoElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getOperation_mrNo())) {
                Element oMrnoElement = rootEle.element("operation_mrNo");
                if (null != oMrnoElement) {
                    oMrnoElement.setText(dataExt.getOperation_mrNo());
                } else {
                    oMrnoElement = DocumentHelper.createElement("operation_mrNo");
                    oMrnoElement.setText(dataExt.getOperation_mrNo());
                    rootEle.add(oMrnoElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getOperation_operDate())) {
                Element oOperDateElement = rootEle.element("operation_operDate");
                if (null != oOperDateElement) {
                    oOperDateElement.setText(dataExt.getOperation_operDate());
                } else {
                    oOperDateElement = DocumentHelper.createElement("operation_operDate");
                    oOperDateElement.setText(dataExt.getOperation_operDate());
                    rootEle.add(oOperDateElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getOperation_operName())) {
                Element oOperNameElement = rootEle.element("operation_operName");
                if (null != oOperNameElement) {
                    if ("00000000000000000000000000000000".equals(record.getItemId())) {
                        oOperNameElement.setText(dataExt.getOperation_operName());
                    } else {
                        oOperNameElement.addAttribute("id", record.getItemId());
                        oOperNameElement.setText(record.getItemName());
                    }
                } else {
                    oOperNameElement = DocumentHelper.createElement("operation_operName");
                    if ("00000000000000000000000000000000".equals(record.getItemId())) {
                        oOperNameElement.setText(dataExt.getOperation_operName());
                    } else {
                        oOperNameElement.addAttribute("id", record.getItemId());
                        oOperNameElement.setText(record.getItemName());
                    }
                    rootEle.add(oOperNameElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getOperation_operRole())) {
                Element oOperRoleElement = rootEle.element("operation_operRole");
                if (null != oOperRoleElement) {
                    oOperRoleElement.addAttribute("id", dataExt.getOperation_operName());
                    if ("0".equals(dataExt.getOperation_operRole())) {
                        oOperRoleElement.setText("术者");
                    } else if ("1".equals(dataExt.getOperation_operRole())) {
                        oOperRoleElement.setText("一助");
                    } else if ("2".equals(dataExt.getOperation_operRole())) {
                        oOperRoleElement.setText("二助");
                    }
                } else {
                    oOperRoleElement = DocumentHelper.createElement("operation_operRole");
                    oOperRoleElement.addAttribute("id", dataExt.getOperation_operName());
                    if ("0".equals(dataExt.getOperation_operRole())) {
                        oOperRoleElement.setText("术者");
                    } else if ("1".equals(dataExt.getOperation_operRole())) {
                        oOperRoleElement.setText("一助");
                    } else if ("2".equals(dataExt.getOperation_operRole())) {
                        oOperRoleElement.setText("二助");
                    }
                    rootEle.add(oOperRoleElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getOperation_pName())) {
                Element oOperpNameElement = rootEle.element("operation_pName");
                if (null != oOperpNameElement) {
                    oOperpNameElement.setText(dataExt.getOperation_pName());
                } else {
                    oOperpNameElement = DocumentHelper.createElement("operation_pName");
                    oOperpNameElement.setText(dataExt.getOperation_pName());
                    rootEle.add(oOperpNameElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getActivity_address())) {
                Element aAddressElement = rootEle.element("activity_address");
                if (null != aAddressElement) {
                    aAddressElement.setText(dataExt.getActivity_address());
                } else {
                    aAddressElement = DocumentHelper.createElement("activity_address");
                    aAddressElement.setText(dataExt.getActivity_address());
                    rootEle.add(aAddressElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getActivity_content())) {
                Element aContentElement = rootEle.element("activity_content");
                if (null != aContentElement) {
                    aContentElement.setText(dataExt.getActivity_content());
                } else {
                    aContentElement = DocumentHelper.createElement("activity_content");
                    aContentElement.setText(dataExt.getActivity_content());
                    rootEle.add(aContentElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getActivity_date())) {
                Element aDateElement = rootEle.element("activity_date");
                if (null != aDateElement) {
                    aDateElement.setText(dataExt.getActivity_date());
                } else {
                    aDateElement = DocumentHelper.createElement("activity_date");
                    aDateElement.setText(dataExt.getActivity_date());
                    rootEle.add(aDateElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getActivity_period())) {
                Element aPeriodElement = rootEle.element("activity_period");
                if (null != aPeriodElement) {
                    aPeriodElement.addAttribute("id", dataExt.getActivity_period());
                    if ("1".equals(dataExt.getActivity_period())) {
                        aPeriodElement.setText("1");
                    } else if ("2".equals(dataExt.getActivity_period())) {
                        aPeriodElement.setText("2");
                    } else if ("3".equals(dataExt.getActivity_period())) {
                        aPeriodElement.setText("3");
                    } else if ("4".equals(dataExt.getActivity_period())) {
                        aPeriodElement.setText("4");
                    } else if ("5".equals(dataExt.getActivity_period())) {
                        aPeriodElement.setText("5");
                    }
                } else {
                    aPeriodElement = DocumentHelper.createElement("activity_period");
                    aPeriodElement.addAttribute("id", dataExt.getActivity_period());
                    if ("1".equals(dataExt.getActivity_period())) {
                        aPeriodElement.setText("1");
                    } else if ("2".equals(dataExt.getActivity_period())) {
                        aPeriodElement.setText("2");
                    } else if ("3".equals(dataExt.getActivity_period())) {
                        aPeriodElement.setText("3");
                    } else if ("4".equals(dataExt.getActivity_period())) {
                        aPeriodElement.setText("4");
                    } else if ("5".equals(dataExt.getActivity_period())) {
                        aPeriodElement.setText("5");
                    }
                    rootEle.add(aPeriodElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getActivity_speaker())) {
                Element aSpeakerElement = rootEle.element("activity_speaker");
                if (null != aSpeakerElement) {
                    aSpeakerElement.setText(dataExt.getActivity_speaker());
                } else {
                    aSpeakerElement = DocumentHelper.createElement("activity_speaker");
                    aSpeakerElement.setText(dataExt.getActivity_speaker());
                    rootEle.add(aSpeakerElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getDisease_caseType())) {
                Element aSpeakerElement = rootEle.element("disease_caseType");
                if (null != aSpeakerElement) {
                    aSpeakerElement.setText(dataExt.getDisease_caseType());
                } else {
                    aSpeakerElement = DocumentHelper.createElement("disease_caseType");
                    aSpeakerElement.setText(dataExt.getDisease_caseType());
                    rootEle.add(aSpeakerElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getActivity_way())) {
                Element aWayElement = rootEle.element("activity_way");
                if (null != aWayElement) {
                    aWayElement.addAttribute("id", dataExt.getActivity_way());
                    String activityWayName = ActivityTypeEnum.getNameById(dataExt.getActivity_way());
                    aWayElement.setText(activityWayName);
//                    if ("1".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("教学查房");
//                    } else if ("2".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("疑难病例讨论");
//                    } else if ("3".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("危重病例讨论");
//                    } else if ("4".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("临床小讲课");
//                    } else if ("5".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("死亡病例讨论");
//                    } else if ("6".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("入轮转科室教育");
//                    } else if ("7".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("出科考核");
//                    } else if ("8".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("技能培训");
//                    } else if ("9".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("教学阅片");
//                    } else if ("10".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("门诊教学");
//                    } else if ("11".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("教学病例讨论");
//                    } else if ("12".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("临床操作技能床旁教学");
//                    } else if ("13".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("住院病历书写指导教学");
//                    } else if ("14".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("手术操作指导教学");
//                    } else if ("15".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("影像诊断报告书写指导教学");
//                    } else if ("16".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("临床文献研读会");
//                    } else if ("17".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("入院教育");
//                    } else if ("18".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("入专业基地教育");
//                    } else if ("19".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("晨间报告");
//                    } else if ("20".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("报告单分析");
//                    }
                } else {
                    aWayElement = DocumentHelper.createElement("activity_way");
                    aWayElement.addAttribute("id", dataExt.getActivity_way());
                    String activityWayName = ActivityTypeEnum.getNameById(dataExt.getActivity_way());
                    aWayElement.setText(activityWayName);
//                    if ("1".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("教学查房");
//                    }
//					else if("2".equals(dataExt.getActivity_way())){
//						aWayElement.setText("疑难病例讨论");
//					}else if("3".equals(dataExt.getActivity_way())){
//						aWayElement.setText("危重病例讨论");
//					}
//                    else if ("4".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("临床小讲课");
//                    }
//					else if("5".equals(dataExt.getActivity_way())){
//						aWayElement.setText("死亡病例讨论");
//					}
//                    else if ("6".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("入轮转科室教育");
//                    } else if ("7".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("出科考核");
//                    } else if ("8".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("技能培训");
//                    } else if ("9".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("教学阅片");
//                    } else if ("10".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("门诊教学");
//                    } else if ("11".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("教学病例讨论");
//                    } else if ("12".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("临床操作技能床旁教学");
//                    } else if ("13".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("住院病历书写指导教学");
//                    } else if ("14".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("手术操作指导教学");
//                    } else if ("15".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("影像诊断报告书写指导教学");
//                    } else if ("16".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("临床文献研读会");
//                    } else if ("17".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("入院教育");
//                    } else if ("18".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("入专业基地教育");
//                    } else if ("19".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("晨间报告");
//                    } else if ("20".equals(dataExt.getActivity_way())) {
//                        aWayElement.setText("报告单分析");
//                    }
                    rootEle.add(aWayElement);
                }
            }

            if (StringUtil.isNotBlank(dataExt.getActivity_way_optionDesc())) {
                Element aOptionDescElement = rootEle.element("activity_way_optionDesc");
                if (null != aOptionDescElement) {
                    aOptionDescElement.setText(dataExt.getActivity_way_optionDesc());
                } else {
                    aOptionDescElement = DocumentHelper.createElement("activity_way_optionDesc");
                    aOptionDescElement.setText(dataExt.getActivity_way_optionDesc());
                    rootEle.add(aOptionDescElement);
                }
            }

            List<JsResDataFile> jsonList = dataExt.getFiles();
            if (jsonList != null && jsonList.size() > 0) {
                Element imageList = DocumentHelper.createElement("imageList");
                for (JsResDataFile img : jsonList) {
                    Element imgEle = DocumentHelper.createElement("image");
                    String fileFlow = img.getImageFlow();
                    imgEle.addAttribute("imageFlow", fileFlow);

                    Element imageUrl = DocumentHelper.createElement("imageUrl");
                    imageUrl.setText(img.getUrl());
                    Element thumbUrl = DocumentHelper.createElement("thumbUrl");
                    thumbUrl.setText(img.getThumbUrl());
                    imgEle.add(imageUrl);
                    imgEle.add(thumbUrl);
                    imageList.add(imgEle);
                }
                rootEle.add(imageList);
            }
            record.setRecContent(doc.asXML());
        } catch (DocumentException e) {
            logger.error("", e);
        }
        record.setModifyTime(DateUtil.getCurrDateTime());
        record.setModifyUserFlow(dataExt.getUserFlow());
        recMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void delData(String dataType, String userFlow, String deptFlow, String dataFlow) {
        ResRec record = new ResRec();
        record.setRecFlow(dataFlow);
        record.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        record.setModifyTime(DateUtil.getCurrDateTime());
        record.setModifyUserFlow(userFlow);
//		if ("mr".equals(dataType)) {
//			ResRecCaseRegistry caseRegistry = new ResRecCaseRegistry();
//			BeanUtils.copyProperties(record, caseRegistry);
//			caseRegistryMapper.updateByPrimaryKeySelective(caseRegistry);
//		}
//		if ("disease".equals(dataType)) {
//			ResRecDiseaseRegistry diseaseRegistry = new ResRecDiseaseRegistry();
//			BeanUtils.copyProperties(record, diseaseRegistry);
//			diseaseRegistryMapper.updateByPrimaryKeySelective(diseaseRegistry);
//		}
//		if ("skill".equals(dataType)) {
//			ResRecSkillRegistry skillRegistry = new ResRecSkillRegistry();
//			BeanUtils.copyProperties(record, skillRegistry);
//			skillRegistryMapper.updateByPrimaryKeySelective(skillRegistry);
//		}
//		if ("operation".equals(dataType)) {
//			ResRecOperationRegistry operationRegistry = new ResRecOperationRegistry();
//			BeanUtils.copyProperties(record, operationRegistry);
//			operationRegistryMapper.updateByPrimaryKeySelective(operationRegistry);
//		}
//		if ("activity".equals(dataType)) {
//			ResRecCampaignRegistry campaignRegistry = new ResRecCampaignRegistry();
//			BeanUtils.copyProperties(record, campaignRegistry);
//			campaignRegistryMapper.updateByPrimaryKeySelective(campaignRegistry);
//		}
//		if ("languageTeaching".equals(dataType)) {
//			ResRecLanguageRegistry languageRegistry = new ResRecLanguageRegistry();
//			BeanUtils.copyProperties(record, languageRegistry);
//			languageRegistryMapper.updateByPrimaryKeySelective(languageRegistry);
//		}
        recMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Map<String, String>> viewImage(String userFlow, String deptFlow) {
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        example.createCriteria().andOperUserFlowEqualTo(userFlow).andSchRotationDeptFlowEqualTo(deptFlow)
                .andRecTypeIdEqualTo(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResSchProcessExpress> recList = expressMapper.selectByExampleWithBLOBs(example);
        if (recList.size() > 0) {
            ResSchProcessExpress record = recList.get(0);
            try {

                /*
                 * <AfterSummary>
                 * 	    <image imageFlow=''>
                 * 			<imageUrl/>
                 * 		    <thumbUrl/>
                 * 		</image>
                 * 	...
                 * </AfterSummary>
                 *
                 * */
                Document doc = DocumentHelper.parseText(record.getRecContent());
                Element rootEle = doc.getRootElement();
                List<Element> images = rootEle.elements();
                if (images != null) {
                    for (Element ele : images) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("imageFlow", ele.attributeValue("imageFlow"));
                        map.put("imageUrl", ele.elementText("imageUrl"));
                        map.put("thumbUrl", ele.elementText("thumbUrl"));
                        dataList.add(map);
                    }
                }

            } catch (DocumentException e) {
                logger.error("", e);
            }

        }
        return dataList;
    }

    @Override
    public void addImage(ImageFileForm form) {

        String cfg = getCfgCode("upload_base_dir");
        String dateString = DateUtil.getCurrDate2();
        String newDir = cfg + File.separator + "summary" + File.separator + dateString;
        String fileName = form.getFileName();
        String preffix = PkUtil.getUUID();//fileName.substring(0,fileName.lastIndexOf("."));
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        fileName = preffix + suffix;
        File fileDir = new File(newDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
            String imgDataString = StringUtil.defaultIfEmpty(form.getUploadFileData(), "");
//			String imgDataString = StringUtil.defaultIfEmpty(form.getImageContent(),"");
            byte[] data = null;
            if (StringUtil.isNotBlank(imgDataString)) {
                BASE64Decoder decoder = new BASE64Decoder();
                // Base64解码
                data = decoder.decodeBuffer(imgDataString);
                for (int i = 0; i < data.length; ++i) {
                    if (data[i] < 0) {// 调整异常数据
                        data[i] += 256;
                    }
                }
            }
            if (data == null) {
                data = form.getUploadFile().getBytes();
            }
            String fileFlow = PkUtil.getUUID();
            File imageFile = new File(fileDir, fileName);
            if (form.getUploadFile() != null) {
                form.getUploadFile().transferTo(imageFile);
            } else {
                FileOutputStream fos = new FileOutputStream(imageFile);
                fos.write(data);
                fos.flush();
                fos.close();
            }

            FtpHelperUtil ftpHelperUtil = new FtpHelperUtil();
            String localFilePath = fileDir + File.separator + fileName;
            String ftpDir = "summary" + File.separator + dateString;
            String ftpFileName = fileName;
            ftpHelperUtil.uploadFile(localFilePath, ftpDir, ftpFileName);

            //处理缩略图...
            String thumbFileName = preffix + "_thumb" + suffix;
            File thumbFile = new File(fileDir, thumbFileName);
            FileOutputStream fos = new FileOutputStream(thumbFile);
            //调用PicZoom类的静态方法zoom对原始图像进行缩放。
            BufferedImage buffImg = PicZoom.zoom(data);
            //创建JPEG图像编码器，用于编码内存中的图像数据到JPEG数据输出流。
            JPEGImageEncoder jpgEncoder = JPEGCodec.createJPEGEncoder(fos);
            //编码BufferedImage对象到JPEG数据输出流。
            jpgEncoder.encode(buffImg);
            fos.flush();
            fos.close();

            localFilePath = fileDir + File.separator + thumbFileName;
            ftpDir = "summary" + File.separator + dateString;
            ftpFileName = thumbFileName;
            ftpHelperUtil.uploadFile(localFilePath, ftpDir, ftpFileName);

            //
            ResSchProcessExpressExample example = new ResSchProcessExpressExample();
            example.createCriteria().andOperUserFlowEqualTo(form.getUserFlow()).andSchRotationDeptFlowEqualTo(form.getDeptFlow())
                    .andRecTypeIdEqualTo(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<ResSchProcessExpress> recList = expressMapper.selectByExampleWithBLOBs(example);
            ResSchProcessExpress record = null;
            Document doc = null;
            String urlCfg = getCfgCode("upload_base_url");
            if (recList.size() > 0) {
                record = recList.get(0);
                doc = DocumentHelper.parseText(record.getRecContent());

                Element rootEle = doc.getRootElement();
                Element imgEle = DocumentHelper.createElement("image");

                imgEle.addAttribute("imageFlow", fileFlow);

                Element imageUrl = DocumentHelper.createElement("imageUrl");
                imageUrl.setText(urlCfg + "/summary/" + dateString + "/" + fileName);
                Element thumbUrl = DocumentHelper.createElement("thumbUrl");
                thumbUrl.setText(urlCfg + "/summary/" + dateString + "/" + thumbFileName);

                imgEle.add(imageUrl);
                imgEle.add(thumbUrl);

                rootEle.add(imgEle);
                record.setRecContent(rootEle.asXML());
                expressMapper.updateByPrimaryKeyWithBLOBs(record);
                updateResultHaveAfter(record.getSchRotationDeptFlow(), record.getOperUserFlow(), record.getRecContent());
            } else {
                record = new ResSchProcessExpress();
                record.setRecFlow(PkUtil.getUUID());
                record.setOperUserFlow(form.getUserFlow());
                record.setRecTypeId(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
                record.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                record.setSchRotationDeptFlow(form.getDeptFlow());

                Element rootEle = DocumentHelper.createElement(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());

                Element imgEle = DocumentHelper.createElement("image");
                imgEle.addAttribute("imageFlow", fileFlow);

                Element imageUrl = DocumentHelper.createElement("imageUrl");
                imageUrl.setText(urlCfg + "/summary/" + dateString + "/" + fileName);
                Element thumbUrl = DocumentHelper.createElement("thumbUrl");
                thumbUrl.setText(urlCfg + "/summary/" + dateString + "/" + thumbFileName);


                imgEle.add(imageUrl);
                imgEle.add(thumbUrl);

                rootEle.add(imgEle);

                SysUser user = readSysUser(form.getUserFlow());
                String medicineTypeId = "";
                if (user != null) medicineTypeId = user.getMedicineTypeId();
                record.setMedicineType(medicineTypeId);
                record.setRecContent(rootEle.asXML());
                expressMapper.insertSelective(record);
                //更新schArrangeresult 表中have_after_pic
                updateResultHaveAfter(record.getSchRotationDeptFlow(), record.getOperUserFlow(), record.getRecContent());
            }

        } catch (FileNotFoundException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        } catch (DocumentException e) {
            logger.error("", e);
        }
    }

    @Override
    public void addImage2(ImageFileForm form) {

        String cfg = getCfgCode("upload_base_dir");
        String dateString = DateUtil.getCurrDate2();
        String newDir = cfg + File.separator + "summary" + File.separator + dateString;
        String fileName = form.getFileName();
        String preffix = PkUtil.getUUID();//fileName.substring(0,fileName.lastIndexOf("."));
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        fileName = preffix + suffix;
        File fileDir = new File(newDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
//			String imgDataString = StringUtil.defaultIfEmpty(form.getUploadFileData(),"");
            String imgDataString = StringUtil.defaultIfEmpty(form.getImageContent(), "");
            byte[] data = null;
            if (StringUtil.isNotBlank(imgDataString)) {
                BASE64Decoder decoder = new BASE64Decoder();
                // Base64解码
                data = decoder.decodeBuffer(imgDataString);
                for (int i = 0; i < data.length; ++i) {
                    if (data[i] < 0) {// 调整异常数据
                        data[i] += 256;
                    }
                }
            }
            if (data == null) {
                data = form.getUploadFile().getBytes();
            }
            String fileFlow = PkUtil.getUUID();
            File imageFile = new File(fileDir, fileName);
            if (form.getUploadFile() != null) {
                form.getUploadFile().transferTo(imageFile);
            } else {
                FileOutputStream fos = new FileOutputStream(imageFile);
                fos.write(data);
                fos.flush();
                fos.close();
            }

            FtpHelperUtil ftpHelperUtil = new FtpHelperUtil();
            String localFilePath = fileDir + File.separator + fileName;
            String ftpDir = "summary" + File.separator + dateString;
            String ftpFileName = fileName;
            ftpHelperUtil.uploadFile(localFilePath, ftpDir, ftpFileName);

            //处理缩略图...
            String thumbFileName = preffix + "_thumb" + suffix;
            File thumbFile = new File(fileDir, thumbFileName);
            FileOutputStream fos = new FileOutputStream(thumbFile);
            //调用PicZoom类的静态方法zoom对原始图像进行缩放。
            BufferedImage buffImg = PicZoom.zoom(data);
            //创建JPEG图像编码器，用于编码内存中的图像数据到JPEG数据输出流。
            JPEGImageEncoder jpgEncoder = JPEGCodec.createJPEGEncoder(fos);
            //编码BufferedImage对象到JPEG数据输出流。
            jpgEncoder.encode(buffImg);
            fos.flush();
            fos.close();

            localFilePath = fileDir + File.separator + thumbFileName;
            ftpDir = "summary" + File.separator + dateString;
            ftpFileName = thumbFileName;
            ftpHelperUtil.uploadFile(localFilePath, ftpDir, ftpFileName);

            //
            ResSchProcessExpressExample example = new ResSchProcessExpressExample();
            example.createCriteria().andOperUserFlowEqualTo(form.getUserFlow()).andSchRotationDeptFlowEqualTo(form.getDeptFlow())
                    .andRecTypeIdEqualTo(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<ResSchProcessExpress> recList = expressMapper.selectByExampleWithBLOBs(example);
            ResSchProcessExpress record = null;
            Document doc = null;
            String urlCfg = getCfgCode("upload_base_url");
            if (recList.size() > 0) {
                record = recList.get(0);
                doc = DocumentHelper.parseText(record.getRecContent());

                Element rootEle = doc.getRootElement();
                Element imgEle = DocumentHelper.createElement("image");

                imgEle.addAttribute("imageFlow", fileFlow);

                Element imageUrl = DocumentHelper.createElement("imageUrl");
                imageUrl.setText(urlCfg + "/summary/" + dateString + "/" + fileName);
                Element thumbUrl = DocumentHelper.createElement("thumbUrl");
                thumbUrl.setText(urlCfg + "/summary/" + dateString + "/" + thumbFileName);

                imgEle.add(imageUrl);
                imgEle.add(thumbUrl);

                rootEle.add(imgEle);
                record.setRecContent(rootEle.asXML());
                expressMapper.updateByPrimaryKeyWithBLOBs(record);
                updateResultHaveAfter(record.getSchRotationDeptFlow(), record.getOperUserFlow(), record.getRecContent());
            } else {
                record = new ResSchProcessExpress();
                record.setRecFlow(PkUtil.getUUID());
                record.setOperUserFlow(form.getUserFlow());
                record.setRecTypeId(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
                record.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                record.setSchRotationDeptFlow(form.getDeptFlow());

                Element rootEle = DocumentHelper.createElement(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());

                Element imgEle = DocumentHelper.createElement("image");
                imgEle.addAttribute("imageFlow", fileFlow);

                Element imageUrl = DocumentHelper.createElement("imageUrl");
                imageUrl.setText(urlCfg + "/summary/" + dateString + "/" + fileName);
                Element thumbUrl = DocumentHelper.createElement("thumbUrl");
                thumbUrl.setText(urlCfg + "/summary/" + dateString + "/" + thumbFileName);


                imgEle.add(imageUrl);
                imgEle.add(thumbUrl);

                rootEle.add(imgEle);

                SysUser user = readSysUser(form.getUserFlow());
                String medicineTypeId = "";
                if (user != null) medicineTypeId = user.getMedicineTypeId();
                record.setMedicineType(medicineTypeId);
                record.setRecContent(rootEle.asXML());
                expressMapper.insertSelective(record);
                //更新schArrangeresult 表中have_after_pic
                updateResultHaveAfter(record.getSchRotationDeptFlow(), record.getOperUserFlow(), record.getRecContent());
            }

        } catch (FileNotFoundException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        } catch (DocumentException e) {
            logger.error("", e);
        }
    }

    @Override
    public void addActivityImage(ActivityImageFileForm form, SysUser user) {
        String cfg = getCfgCode("upload_base_dir");
        String dateString = DateUtil.getCurrDate2();
        String newDir = cfg + File.separator + "activityFlie" + File.separator + dateString;
        String fileName = form.getFileName();
        String preffix = PkUtil.getUUID();//fileName.substring(0,fileName.lastIndexOf("."));
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        fileName = preffix + suffix;
        File fileDir = new File(newDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
            String imgDataString = StringUtil.defaultIfEmpty(form.getUploadFileData(), "");
            byte[] data = null;
            if (StringUtil.isNotBlank(imgDataString)) {
                BASE64Decoder decoder = new BASE64Decoder();
                // Base64解码
                data = decoder.decodeBuffer(imgDataString);
                for (int i = 0; i < data.length; ++i) {
                    if (data[i] < 0) {// 调整异常数据
                        data[i] += 256;
                    }
                }
            }
            if (data == null) {
                data = form.getUploadFile().getBytes();
            }
            File imageFile = new File(fileDir, fileName);
            if (form.getUploadFile() != null) {
                form.getUploadFile().transferTo(imageFile);
            } else {
                FileOutputStream fos = new FileOutputStream(imageFile);
                fos.write(data);
                fos.flush();
                fos.close();
            }

            FtpHelperUtil ftpHelperUtil = new FtpHelperUtil();
            String localFilePath = fileDir + File.separator + fileName;
            String ftpDir = "activityFlie" + File.separator + dateString;
            String ftpFileName = fileName;
            ftpHelperUtil.uploadFile(localFilePath, ftpDir, ftpFileName);

            String url = getCfgCode("upload_base_url") + "/activityFlie/" + dateString + "/" + fileName;
            if (StringUtil.isNotBlank(form.getActivityFlow())) {
                TeachingActivityInfo resRec = activityBiz.readActivityInfo(form.getActivityFlow());
                String content = resRec.getImageUrl();
                if (StringUtil.isBlank(content)) {
                    Document dom = DocumentHelper.createDocument();
                    Element root = dom.addElement("ActivityImages");
                    Element elem = root.addElement("image");
                    String imageFlow = PkUtil.getUUID();
                    elem.addAttribute("imageFlow", imageFlow);
                    Element imageUrl = elem.addElement("imageUrl");
                    Element thumbUrl = elem.addElement("thumbUrl");
                    imageUrl.setText(url);
                    thumbUrl.setText(url);
                    content = root.asXML();
                    resRec.setImageUrl(content);
                    activityBiz.saveActivityInfo(resRec, user);
                } else {
                    Document document = DocumentHelper.parseText(content);
                    if (document != null) {
                        Element element = document.getRootElement();
                        Element elem = element.addElement("image");
                        String imageFlow = PkUtil.getUUID();
                        elem.addAttribute("imageFlow", imageFlow);
                        Element imageUrl = elem.addElement("imageUrl");
                        Element thumbUrl = elem.addElement("thumbUrl");
                        imageUrl.setText(url);
                        thumbUrl.setText(url);
                        resRec.setImageUrl(document.asXML());
                        activityBiz.saveActivityInfo(resRec, user);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        } catch (DocumentException e) {
            logger.error("", e);
        }
    }

    @Override
    public void addActivityImage2(ActivityImageFileForm form,SysUser user) {
        String cfg = getCfgCode("upload_base_dir");
        String dateString = DateUtil.getCurrDate2();
        String newDir = cfg+File.separator+"activityFlie"+File.separator +dateString ;
        String fileName = form.getFileName();
        String preffix = PkUtil.getUUID();//fileName.substring(0,fileName.lastIndexOf("."));
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        fileName=preffix+suffix;
        File fileDir = new File(newDir);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        try {
            String imgDataString = StringUtil.defaultIfEmpty(form.getImageContent(),"");
            byte[] data = null;
            if(StringUtil.isNotBlank(imgDataString)){
                BASE64Decoder decoder = new BASE64Decoder();
                // Base64解码
                data = decoder.decodeBuffer(imgDataString);
                for (int i = 0; i < data.length; ++i) {
                    if (data[i] < 0) {// 调整异常数据
                        data[i] += 256;
                    }
                }
            }
            if(data==null){
                data = form.getUploadFile().getBytes();
            }
            File imageFile = new File(fileDir,fileName);
            if(form.getUploadFile()!=null){
                form.getUploadFile().transferTo(imageFile);
            }else {
                FileOutputStream fos = new FileOutputStream(imageFile);
                fos.write(data);
                fos.flush();
                fos.close();
            }

            FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
            String localFilePath=fileDir+File.separator+fileName;
            String ftpDir= "activityFlie"+File.separator +dateString ;
            String ftpFileName=fileName;
            ftpHelperUtil.uploadFile(localFilePath,ftpDir,ftpFileName);

            String url = getCfgCode("upload_base_url")+"/activityFlie/"+dateString+"/"+fileName;
            if(StringUtil.isNotBlank(form.getActivityFlow())){
                TeachingActivityInfo resRec = activityBiz.readActivityInfo(form.getActivityFlow());
                String content =resRec.getImageUrl();
                if(StringUtil.isBlank(content))
                {
                    Document dom = DocumentHelper.createDocument();
                    Element root= dom.addElement("ActivityImages");
                    Element elem=root.addElement("image");
                    String imageFlow=PkUtil.getUUID();
                    elem.addAttribute("imageFlow",imageFlow);
                    Element imageUrl=elem.addElement("imageUrl");
                    Element thumbUrl=elem.addElement("thumbUrl");
                    imageUrl.setText(url);
                    thumbUrl.setText(url);
                    content=root.asXML();
                    resRec.setImageUrl(content);
                    activityBiz.saveActivityInfo(resRec,user);
                }else{
                    Document document=DocumentHelper.parseText(content);
                    if (document!=null) {
                        Element element=document.getRootElement();
                        Element elem=element.addElement("image");
                        String imageFlow=PkUtil.getUUID();
                        elem.addAttribute("imageFlow",imageFlow);
                        Element imageUrl=elem.addElement("imageUrl");
                        Element thumbUrl=elem.addElement("thumbUrl");
                        imageUrl.setText(url);
                        thumbUrl.setText(url);
                        resRec.setImageUrl(document.asXML());
                        activityBiz.saveActivityInfo(resRec,user);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("", e);
        }
        catch (IOException e) {
            logger.error("", e);
        } catch (DocumentException e) {
            logger.error("", e);
        }
    }
    @Override
    public void deleteActivityImage(SysUser user, String activityFlow, String imageFlow) throws DocumentException {
        TeachingActivityInfo activity = activityBiz.readActivityInfo(activityFlow);
        if (StringUtil.isNotBlank(activity.getImageUrl())) {
            String content = activity.getImageUrl();
            Document document = DocumentHelper.parseText(content);
            Element elem = document.getRootElement();
            Node delNode = elem.selectSingleNode("image[@imageFlow='" + imageFlow + "']");
            if (delNode != null) {
                delNode.detach();
                activity.setImageUrl(document.asXML());
                activityBiz.saveActivityInfo(activity, user);
            }
        }
    }

    public void updateResultHaveAfter(String schRotationDeptFlow, String operUserFlow, String recContent) throws DocumentException {
        String haveAfterPic = com.pinde.core.common.GlobalConstant.FLAG_N;
        if (StringUtil.isNotBlank(recContent)) {
            Document document = DocumentHelper.parseText(recContent);
            if (document != null) {
                Element element = document.getRootElement();
                List<Object> elem = element.elements("image");
                if (elem != null && elem.size() > 0) {
                    haveAfterPic = com.pinde.core.common.GlobalConstant.FLAG_Y;
                }
            }
        }
        resultExtMapper.updateResultHaveAfter(haveAfterPic, schRotationDeptFlow, operUserFlow);
    }

    @Override
    public void deleteImage(String userFlow, String deptFlow, String imageFlow) {
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        example.createCriteria().andOperUserFlowEqualTo(userFlow).andSchRotationDeptFlowEqualTo(deptFlow)
                .andRecTypeIdEqualTo(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResSchProcessExpress> recList = expressMapper.selectByExampleWithBLOBs(example);
        if (recList.size() > 0) {
            ResSchProcessExpress record = recList.get(0);
            try {
                Document doc = DocumentHelper.parseText(record.getRecContent());
                Element rootEle = doc.getRootElement();
                Element ele = (Element) rootEle.selectSingleNode("/AfterSummary/image[@imageFlow='" + imageFlow + "']");
                if (ele != null) {
                    rootEle.remove(ele);
                }
                record.setRecContent(doc.asXML());
                expressMapper.updateByPrimaryKeyWithBLOBs(record);
                updateResultHaveAfter(record.getSchRotationDeptFlow(), record.getOperUserFlow(), record.getRecContent());
            } catch (DocumentException e) {
                logger.error("", e);
            }
        }

    }

    @Override
    public int temporaryOutFamily(String processFlow) {
        int num = 0;
        ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow);
        List<ResDoctorSchProcess> processList = processMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(processList)) {
            ResDoctorSchProcess resDoctorSchProcess = processList.get(0);
            resDoctorSchProcess.setTemporaryOut(com.pinde.core.common.GlobalConstant.FLAG_Y);
            resDoctorSchProcess.setTemporaryAuditStatusId("Auditing");
            resDoctorSchProcess.setTemporaryAuditStatusName("待审核");
            resDoctorSchProcess.setModifyTime(DateUtil.getCurrDateTime());
            num = processMapper.updateByPrimaryKeySelective(resDoctorSchProcess);
        }
        return num;
    }

    @Override
    public int temporaryOutAudit(String processFlow, String auditStatus) {
        ResDoctorSchProcess resDoctorSchProcess = new ResDoctorSchProcess();
        resDoctorSchProcess.setProcessFlow(processFlow);
        if ("Passed".equals(auditStatus)) {
            resDoctorSchProcess.setTemporaryAuditStatusId("Passed");
            resDoctorSchProcess.setTemporaryAuditStatusName("审核通过");
        }
        if ("NotPassed".equals(auditStatus)) {
            resDoctorSchProcess.setTemporaryAuditStatusId("NotPassed");
            resDoctorSchProcess.setTemporaryAuditStatusName("审核不通过");
        }
        return processMapper.updateByPrimaryKeySelective(resDoctorSchProcess);

    }

    @Override
    public ResDoctor readResDoctor(String userFlow) {
        return doctorMapper.selectByPrimaryKey(userFlow);
    }

    @Override
    public ResDoctor readResDoctorTwo(String userFlow) {
        return doctorExtMapper.selectByPrimaryKey(userFlow);
    }

    @Override
    public List<Map<String, Object>> searchSchRotationDept(Map<String, Object> paramMap) {
        return schRotationDeptExtMapper.searchSchRotationDept(paramMap);
    }


    @Override
    public String searchByDoctorAndRotationFlow(String doctorFlow, String rotationFlow) {
        return schRotationDeptExtMapper.searchByDoctorAndRotationFlow(doctorFlow, rotationFlow);
    }

    @Override
    public List<SchArrangeResult> searchSchArrangeResultPassing(String doctorFlow, String rotationFlow) {
        SchArrangeResultExample example = new SchArrangeResultExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andDoctorFlowEqualTo(doctorFlow)
                .andRotationFlowEqualTo(rotationFlow).andBaseAuditEqualTo("Passing");
        example.setOrderByClause("CREATE_TIME");
        return resultMapper.selectByExample(example);
    }

    @Override
    public Integer searchSchRotationDeptCount(Map<String, Object> paramMap) {
        return schRotationDeptExtMapper.searchSchRotationDeptCount(paramMap);
    }

    @Override
    public Map<String, SchRotationDept> getRotationDeptMap(String rotationFlow) {
        SchRotationDeptExample example = new SchRotationDeptExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andRotationFlowEqualTo(rotationFlow).andOrgFlowIsNull();
        List<SchRotationDept> rotationDeptList = schRotationDeptMapper.selectByExample(example);
        if (rotationDeptList != null && !rotationDeptList.isEmpty()) {
            Map<String, SchRotationDept> rotationDeptMap = new HashMap<String, SchRotationDept>();
            for (SchRotationDept srd : rotationDeptList) {
                rotationDeptMap.put(srd.getRecordFlow(), srd);
            }
            return rotationDeptMap;
        }
        return null;
    }

    @Override
    public List<SchDoctorDept> searchReductionDept(String doctorFlow, String rotationFlow) {
        SchDoctorDeptExample doctorDeptExample = new SchDoctorDeptExample();
        doctorDeptExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow).andRotationFlowEqualTo(rotationFlow);
        return doctorDeptMapper.selectByExample(doctorDeptExample);
    }

    public List<SchDoctorDept> searchReductionDeptTwo(String doctorFlow, String rotationFlow) {
        SchDoctorDeptExample doctorDeptExample = new SchDoctorDeptExample();
        doctorDeptExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow).andRotationFlowEqualTo(rotationFlow);
        return doctorDeptExtMapper.selectByExampleTwo(doctorDeptExample);
    }

    @Override
    public Map<String, SchDoctorDept> getReductionDeptMap(String doctorFlow, String rotationFlow) {
        Map<String, SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();

        List<SchDoctorDept> doctorDeptList = searchReductionDept(doctorFlow, rotationFlow);
        if (doctorDeptList != null && !doctorDeptList.isEmpty()) {
            doctorDeptMap = new HashMap<String, SchDoctorDept>();
            for (SchDoctorDept sdd : doctorDeptList) {
                String key = sdd.getGroupFlow() + sdd.getStandardDeptId();
                doctorDeptMap.put(key, sdd);
            }
        }
        return doctorDeptMap;
    }

    /**************************百分比算法START********************************/

    @Override
    public Map<String, String> getProcessPer(String doctorFlow, String rotationFlow) {
        return getProcessPer(doctorFlow, rotationFlow, null);
    }

    @Override
    public Map<String, String> getProcessPer(String doctorFlow, String rotationFlow, String relRecordFlow) {
        return getProcessPer(doctorFlow, rotationFlow, relRecordFlow, 0, null);
    }

    @Override
    public Map<String, String> getProcessPer(String doctorFlow, String rotationFlow, String relRecordFlow, int format, List<ResRec> recList) {
        Map<String, String> finishedPer = null;
        if (StringUtil.isNotBlank(doctorFlow) && StringUtil.isNotBlank(rotationFlow)) {
            finishedPer = new HashMap<String, String>();
            //获取学员信息
//			ResDoctor doctor = null;
            SysUser user = readSysUser(doctorFlow);
            if (user == null) {
                return null;
            }
//			if(StringUtil.isNotBlank(doctorFlow)){
//				doctor = readResDoctor(doctorFlow);
//				if(doctor==null){
//					return null;
//				}
//				user = readSysUser(doctorFlow);
//				if(user==null){
//					return null;
//				}
//			}
            //获取所有缩减的调整科室
            List<SchDoctorDept> doctorDeptList = searchReductionDeptTwo(doctorFlow, rotationFlow);

            //获取所有当前方案下的轮转规则
            SchRotationDeptExample rotationDeptExample = new SchRotationDeptExample();
            SchRotationDeptExample.Criteria rotationDeptCriteria = rotationDeptExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andOrgFlowIsNull().andRotationFlowEqualTo(rotationFlow);
            if (StringUtil.isNotBlank(relRecordFlow)) {
                rotationDeptCriteria.andRecordFlowEqualTo(relRecordFlow);
            }

            List<SchRotationDept> rotationDeptList = schRotationDeptExtMapper.selectByExampleTwo(rotationDeptExample);
            List<String> getRecordFlows = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(rotationDeptList)) {
                getRecordFlows = rotationDeptList.stream().map(s -> s.getRecordFlow()).collect(Collectors.toList());
            }


            if (rotationDeptList != null && !rotationDeptList.isEmpty()) {
                //计算缩减要求比例
                Map<String, Float> reqReductionPer = new HashMap<String, Float>();
                if (doctorDeptList != null && !doctorDeptList.isEmpty()) {
                    Map<String, Float> reductionMonthMap = new HashMap<String, Float>();
                    for (SchDoctorDept sdd : doctorDeptList) {
                        String month = sdd.getSchMonth();
                        if (StringUtil.isNotBlank(month)) {
                            Float monthF = Float.parseFloat(month);
                            String key = sdd.getGroupFlow() + sdd.getStandardDeptId();
                            reductionMonthMap.put(key, monthF);
                        }
                    }

                    for (SchRotationDept srd : rotationDeptList) {
                        String standardMonth = srd.getSchMonth();
                        if (StringUtil.isNotBlank(standardMonth)) {
                            String key = srd.getGroupFlow() + srd.getStandardDeptId();
                            Float reductionMonth = reductionMonthMap.get(key);
                            Float standardMonthF = Float.parseFloat(standardMonth);
                            if (reductionMonth != null && reductionMonth != 0f && standardMonthF != 0f) {
                                reqReductionPer.put(srd.getRecordFlow(), reductionMonth / standardMonthF);
                            }
                        }
                    }
                }

                //获取所有当前方案下的要求
                SchRotationDeptReqExample reqExample = new SchRotationDeptReqExample();
                SchRotationDeptReqExample.Criteria reqCriteria = reqExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andRotationFlowEqualTo(rotationFlow);
                if (StringUtil.isNotBlank(relRecordFlow)) {
                    reqCriteria.andRelRecordFlowEqualTo(relRecordFlow);
                }
                List<String> recTypeList = new ArrayList<>();
                for (RegistryTypeEnum regType : RegistryTypeEnum.values()) {
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(GlobalUtil.getSysCfg("res_registry_type_" + regType.getId()))) {
                        if (GlobalUtil.findChineseOrWestern(user.getMedicineTypeId(), regType.getId())) {
                            recTypeList.add(regType.getId());
                        }
                    }
                }
                if (recTypeList.size() > 0) {
                    reqCriteria.andRecTypeIdIn(recTypeList);
                }

                List<SchRotationDeptReq> reqList = schRotationDeptReqExtMapper.selectByExampleTwo(reqExample);

                if (reqList != null && !reqList.isEmpty()) {
                    //分类统计要求
                    Map<String, Float> reqNumMap = new HashMap<String, Float>();
                    Map<String, List<String>> itemIdListMap = new HashMap<String, List<String>>();
                    for (SchRotationDeptReq req : reqList) {
                        String rotationDeptFlow = req.getRelRecordFlow();
                        String recFlowRecType = rotationDeptFlow + req.getRecTypeId();
                        String itemId = req.getItemId();

                        //要求数
                        Float reqNum = 0f;
                        if (req.getReqNum() != null) {
                            reqNum = req.getReqNum().floatValue();

                            Float reductionPer = reqReductionPer.get(rotationDeptFlow);
                            if (reductionPer != null && reductionPer != 0f) {
                                reqNum *= reductionPer;
                                if (reqNum > 0 && reqNum < 1) {
                                    reqNum = 1f;
                                }

                                reqNum = (float) Math.round(reqNum);
                            }
                        }

                        //各规则下的总要求
                        setNumMap(reqNumMap, rotationDeptFlow, reqNum);
                        finishedPer.put(rotationDeptFlow + "ReqNum", formatNum(reqNumMap.get(rotationDeptFlow), 0));

                        //各登记类型下的总要求
                        setNumMap(reqNumMap, recFlowRecType, reqNum);
                        finishedPer.put(recFlowRecType + "ReqNum", formatNum(reqNumMap.get(recFlowRecType), 0));

                        if (StringUtil.isNotBlank(itemId)) {
                            //获取各规则下各登记类型的子项列表
                            if (itemIdListMap.get(recFlowRecType) == null) {
                                List<String> itemNameList = new ArrayList<String>();
                                itemNameList.add(itemId);
                                itemIdListMap.put(recFlowRecType, itemNameList);
                            } else {
                                itemIdListMap.get(recFlowRecType).add(itemId);
                            }

                            String recFlowRecTypeItem = recFlowRecType + itemId;
                            //各子项要求
                            setNumMap(reqNumMap, recFlowRecTypeItem, reqNum);
                            finishedPer.put(recFlowRecTypeItem + "ReqNum", formatNum(reqNumMap.get(recFlowRecTypeItem), 0));
                        }
                    }
                    if (recList == null) {
                        //获取当前学员的所有登记数据
//						ResRecCampaignRegistryExample campaignRegistryExample = new ResRecCampaignRegistryExample();
//						ResRecCampaignRegistryExample.Criteria campaignRegistryCriteria = campaignRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(doctorFlow);
//						ResRecCaseRegistryExample caseRegistryExample = new ResRecCaseRegistryExample();
//						ResRecCaseRegistryExample.Criteria caseRegistryCriteria = caseRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(doctorFlow);
//						ResRecDiseaseRegistryExample diseaseRegistryExample = new ResRecDiseaseRegistryExample();
//						ResRecDiseaseRegistryExample.Criteria diseaseRegistryCriteria = diseaseRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(doctorFlow);
//						ResRecLanguageRegistryExample languageRegistryExample = new ResRecLanguageRegistryExample();
//						ResRecLanguageRegistryExample.Criteria languageRegistryCriteria = languageRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(doctorFlow);
//						ResRecOperationRegistryExample operationRegistryExample = new ResRecOperationRegistryExample();
//						ResRecOperationRegistryExample.Criteria operationRegistryCriteria = operationRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(doctorFlow);
//						ResRecSkillRegistryExample skillRegistryExample = new ResRecSkillRegistryExample();
//						ResRecSkillRegistryExample.Criteria skillRegistryCriteria = skillRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(doctorFlow);
                        ResRecExample recExample = new ResRecExample();
                        ResRecExample.Criteria recCriteria = recExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(doctorFlow);
                        if (StringUtil.isNotBlank(relRecordFlow)) {
//							campaignRegistryCriteria.andSchRotationDeptFlowEqualTo(relRecordFlow);
//							caseRegistryCriteria.andSchRotationDeptFlowEqualTo(relRecordFlow);
//							diseaseRegistryCriteria.andSchRotationDeptFlowEqualTo(relRecordFlow);
//							languageRegistryCriteria.andSchRotationDeptFlowEqualTo(relRecordFlow);
//							operationRegistryCriteria.andSchRotationDeptFlowEqualTo(relRecordFlow);
//							skillRegistryCriteria.andSchRotationDeptFlowEqualTo(relRecordFlow);
                            recCriteria.andSchRotationDeptFlowEqualTo(relRecordFlow);
                        }
//						List<ResRecCampaignRegistry> campaignRegistryList = campaignRegistryMapper.selectByExample(campaignRegistryExample);
//						recList = campaignRegistryList.stream().map(e -> {
//							ResRec resRec = new ResRec();
//							BeanUtils.copyProperties(e, resRec);
//							return resRec;
//						}).collect(Collectors.toList());
//						List<ResRecCaseRegistry> caseRegistryList = caseRegistryMapper.selectByExample(caseRegistryExample);
//						recList.addAll(caseRegistryList.stream().map(e -> {
//							ResRec resRec = new ResRec();
//							BeanUtils.copyProperties(e, resRec);
//							return resRec;
//						}).collect(Collectors.toList()));
//						List<ResRecDiseaseRegistry> diseaseRegistryList = diseaseRegistryMapper.selectByExample(diseaseRegistryExample);
//						recList.addAll(diseaseRegistryList.stream().map(e -> {
//							ResRec resRec = new ResRec();
//							BeanUtils.copyProperties(e, resRec);
//							return resRec;
//						}).collect(Collectors.toList()));
//						List<ResRecLanguageRegistry> languageRegistryList = languageRegistryMapper.selectByExample(languageRegistryExample);
//						recList.addAll(languageRegistryList.stream().map(e -> {
//							ResRec resRec = new ResRec();
//							BeanUtils.copyProperties(e, resRec);
//							return resRec;
//						}).collect(Collectors.toList()));
//						List<ResRecOperationRegistry> operationRegistryList = operationRegistryMapper.selectByExample(operationRegistryExample);
//						recList.addAll(operationRegistryList.stream().map(e -> {
//							ResRec resRec = new ResRec();
//							BeanUtils.copyProperties(e, resRec);
//							return resRec;
//						}).collect(Collectors.toList()));
//						List<ResRecSkillRegistry> skillRegistryList = skillRegistryMapper.selectByExample(skillRegistryExample);
//						recList.addAll(skillRegistryList.stream().map(e -> {
//							ResRec resRec = new ResRec();
//							BeanUtils.copyProperties(e, resRec);
//							return resRec;
//						}).collect(Collectors.toList()));
                        recList = recExtMapper.selectByExampleTwo(recExample);
                    }
                    //分类统计登记记录
                    Map<String, Float> recFinishedMap = new HashMap<String, Float>();
                    List<String> recTypeIdList = new ArrayList<String>();
                    if (recList != null && !recList.isEmpty()) {
                        for (ResRec rec : recList) {
                            String rotationDeptFlow = rec.getSchRotationDeptFlow();
                            String recTypeId = rec.getRecTypeId();
                            String recFlowRecType = rotationDeptFlow + recTypeId;
                            String itemId = rec.getItemId();
                            String recFlowRecTypeItem = recFlowRecType + itemId;

                            //各规则下的总完成数
                            setNumMap(recFinishedMap, rotationDeptFlow, 1f);
                            finishedPer.put(rotationDeptFlow + "Finished", formatNum(recFinishedMap.get(rotationDeptFlow), 0));

                            //各登记类型下的总完成数
                            setNumMap(recFinishedMap, recFlowRecType, 1f);
                            finishedPer.put(recFlowRecType + "Finished", formatNum(recFinishedMap.get(recFlowRecType), 0));

                            if (StringUtil.isNotBlank(itemId)) {
                                //各子项完成数
                                setNumMap(recFinishedMap, recFlowRecTypeItem, 1f);
                                finishedPer.put(recFlowRecTypeItem + "Finished", formatNum(recFinishedMap.get(recFlowRecTypeItem), 0));
                            }

                            //获取用到的登记类型
                            if (!recTypeIdList.contains(recTypeId)) {
                                recTypeIdList.add(recTypeId);
                            }
                        }
                    }

                    //开始计算百分比
//					for(SchRotationDept rotationDept : rotationDeptList){
                    if (CollectionUtils.isNotEmpty(getRecordFlows)) {
                        for (String recordFlow : getRecordFlows) {
//						String recordFlow = rotationDept.getRecordFlow();

                            //总比
                            Float sumPer = 0f;
                            //当前规则要求总数
                            Float reqSum = reqNumMap.get(recordFlow);

                            for (String recTypeId : recTypeList) {
                                //类型总比
                                Float recSumPer = 0f;

                                String recordFlowRecType = recordFlow + recTypeId;

                                //该类型完成总数
                                Float recFinished = recFinishedMap.get(recordFlowRecType);

                                //当前类型所要求总和
                                Float recReqSum = reqNumMap.get(recordFlowRecType);

                                //当前类型占总和的比
                                Float recPer = twoNumDivide(recReqSum, reqSum);

                                //当前类型下的子项列表
                                List<String> itemIds = itemIdListMap.get(recordFlowRecType);
                                if (!RegistryTypeEnum.CampaignRegistry.getId().equals(recTypeId)) {
                                    if (itemIds != null && !itemIds.isEmpty()) {
                                        for (String itemId : itemIds) {
                                            String recordFlowRecTypeItemId = recordFlowRecType + itemId;
                                            //当前子项要求数
                                            Float recItemReqSum = reqNumMap.get(recordFlowRecTypeItemId);

                                            //当前子项所占当前类型的比
                                            Float itemPer = twoNumDivide(recItemReqSum, recReqSum);

                                            //当前子项的完成数
                                            Float itemFinished = recFinishedMap.get(recordFlowRecTypeItemId);

                                            //子项占比
                                            sumPer += ((twoNumDivide(itemFinished, recItemReqSum)) * itemPer * recPer);

                                            recSumPer += ((twoNumDivide(itemFinished, recItemReqSum)) * itemPer);

                                            finishedPer.put(recordFlowRecTypeItemId, formatNum((twoNumDivide(itemFinished, recItemReqSum)) * 100, format));

                                            if ((recItemReqSum == null || recItemReqSum == 0)) {
                                                finishedPer.put(recordFlowRecTypeItemId, "100");
                                            }
                                        }
                                    } else {
                                        float recFinishPer = twoNumDivide(recFinished, recReqSum);
                                        sumPer += (recPer * recFinishPer);

                                        recSumPer += recFinishPer;

                                        if ((recReqSum == null || recReqSum == 0)) {
                                            sumPer += (recPer * 1);
                                            recSumPer += 1;
                                        }
                                    }
                                } else {
                                    float recFinishPer = twoNumDivide(recFinished, recReqSum);
                                    sumPer += (recPer * recFinishPer);

                                    recSumPer += recFinishPer;

                                    if ((recReqSum == null || recReqSum == 0)) {
                                        sumPer += (recPer * 1);
                                        recSumPer += 1;
                                    }
                                }
                                if ((recReqSum == null || recReqSum == 0)) {
                                    recSumPer = 1f;
                                }

                                finishedPer.put(recordFlowRecType, formatNum(recSumPer * 100, format));

                                if ((recReqSum == null || recReqSum == 0)) {
                                    finishedPer.put(recordFlowRecType, "100");
                                }
                            }

                            finishedPer.put(recordFlow, formatNum(sumPer * 100, format));

                            if (reqSum == null || reqSum == 0) {
                                finishedPer.put(recordFlow, "100");
                            }
                        }
                    }
                }
            }
        }
        return finishedPer;
    }

    //格式化数字
    private String formatNum(Float f, int format) {
        if (f != null) {
            BigDecimal b = new BigDecimal(f).setScale(format, BigDecimal.ROUND_HALF_UP);
            return b.toString();
        } else {
            return "0";
        }
    }

    //统计Map
    private void setNumMap(Map<String, Float> numMap, String key, Float num) {
        if (numMap.get(key) == null) {
            numMap.put(key, num);
        } else {
            numMap.put(key, numMap.get(key) + num);
        }
    }

    //两数相除
    private Float twoNumDivide(Float numA, Float numB) {
        Float result = 0f;
        if (numA == null || numA == 0) {
            return result;
        }
        if (numB == null || numB == 0) {
            return result;
        }
        result = (numA + 0f) / (numB + 0f);
        return result > 1 ? 1f : result;
    }

    @Override
    public SchRotation searchRoattion(String docCategroyId, String trainingSpeId) {
        SchRotationExample example = new SchRotationExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andSpeIdEqualTo(trainingSpeId).andPublishFlagEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorCategoryIdEqualTo(docCategroyId);
        example.setOrderByClause("create_time");
        List<SchRotation> list = rotationMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    @Override
    public SchRotation searchAssiGeneralRoattion(String docCategroyId, String trainingSpeId, String sessionNumber) {
        SchRotationExample example = new SchRotationExample();
        SchRotationExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andSpeIdEqualTo(trainingSpeId).andPublishFlagEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorCategoryIdEqualTo(docCategroyId);
        if (sessionNumber.compareTo("2020") < 0) {
            // 2020级以前学员 使用旧方案
            example.setOrderByClause("create_time asc");
        } else {
            example.setOrderByClause("create_time desc");
        }
        List<SchRotation> list = rotationMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * @return void
     * @Author shengl
     * @Description 查询医师权限
     * @Date 2020-12-21
     * @Param [model, userFlow]
     **/
    @Override
    public void getDoctorAuthorityInfo(Model model, String userFlow, String orgFlow) {
        boolean isActivity = false;
        boolean isAttendance = false;
        boolean isCkk = false;
        boolean isPxsc = false;
        boolean appMenu = false;
        if (StringUtil.isNotBlank(userFlow)) {
            JsresPowerCfgExample example = new JsresPowerCfgExample();
            JsresPowerCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            criteria.andCheckStatusIdEqualTo(CheckStatusEnum.Passed.getId());
            criteria.andCfgCodeLike("%" + userFlow);
            List<JsresPowerCfg> cfgList = jsresPowerCfgMapper.selectByExample(example);

            //查询基地是否有时间配置
            SysOrg org = getOrg(orgFlow);
            JsresPowerCfg startCfg = this.readPowerCfg("jsres_payPower_startDate_" + orgFlow);
            JsresPowerCfg endCfg = this.readPowerCfg("jsres_payPower_endDate_" + orgFlow);
            if (null != org && !"Passed".equals(org.getCheckStatusId())) {
                startCfg = null;
                endCfg = null;
            }
            String currDate = DateUtil.getCurrDate();

            if (cfgList != null && cfgList.size() > 0) {
                for (JsresPowerCfg powerCfg : cfgList) {
                    String cfgCode = powerCfg.getCfgCode() == null ? "" : powerCfg.getCfgCode();
                    if (cfgCode.contains("jsres_doctor_activity_")) {
                        if (null != startCfg && null != endCfg) {
                            if (currDate.compareTo(startCfg.getCfgValue()) >= 0 && currDate.compareTo(endCfg.getCfgValue()) <= 0) {
                                // 活动
                                isActivity = true;
                            }
                        } else {
                            // 活动
                            isActivity = true;
                        }
                    } else if (cfgCode.contains("jsres_doctor_attendance_")) {
                        if (null != startCfg && null != endCfg) {
                            if (currDate.compareTo(startCfg.getCfgValue()) >= 0 && currDate.compareTo(endCfg.getCfgValue()) <= 0) {
                                // 考勤
                                isAttendance = true;
                            }
                        } else {
                            // 考勤
                            isAttendance = true;
                        }
                    } else if (cfgCode.contains("jsres_doctor_exam_")) {
                        // 出科考试
                        isCkk = true;
                    } else if (cfgCode.contains("jsres_doctor_manual_")) {
                        //培训手册
                        isPxsc = true;
                    } else if (cfgCode.contains("jsres_doctor_app_menu_")) {
                        if (null != startCfg && null != endCfg) {
                            if (currDate.compareTo(startCfg.getCfgValue()) >= 0 && currDate.compareTo(endCfg.getCfgValue()) <= 0) {
                                // 付费功能
                                appMenu = true;
                            }
                        } else {
                            // 付费功能
                            appMenu = true;
                        }
                    }
                }
            }
        }
        model.addAttribute("isActivity", isActivity);
        model.addAttribute("isAttendance", isAttendance);
        model.addAttribute("isCkk", isCkk);
        model.addAttribute("isPxsc", isPxsc);
        model.addAttribute("appMenu", appMenu);

    }

    @Override
    public Object getDoctorAuthorityInfo2(Map<String, Object> map, String userFlow, String orgFlow) {
        boolean isActivity = false;
        boolean isAttendance = false;
        boolean isCkk = false;
        boolean isPxsc = false;
        boolean appMenu = false;
        if (StringUtil.isNotBlank(userFlow)) {
            JsresPowerCfgExample example = new JsresPowerCfgExample();
            JsresPowerCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            criteria.andCheckStatusIdEqualTo(CheckStatusEnum.Passed.getId());
            criteria.andCfgCodeLike("%" + userFlow);
            List<JsresPowerCfg> cfgList = jsresPowerCfgMapper.selectByExample(example);

            //查询基地是否有时间配置
            SysOrg org = getOrg(orgFlow);
            JsresPowerCfg startCfg = this.readPowerCfg("jsres_payPower_startDate_" + orgFlow);
            JsresPowerCfg endCfg = this.readPowerCfg("jsres_payPower_endDate_" + orgFlow);
            if (null != org && !"Passed".equals(org.getCheckStatusId())) {
                startCfg = null;
                endCfg = null;
            }
            String currDate = DateUtil.getCurrDate();

            if (cfgList != null && cfgList.size() > 0) {
                for (JsresPowerCfg powerCfg : cfgList) {
                    String cfgCode = powerCfg.getCfgCode() == null ? "" : powerCfg.getCfgCode();
                    if (cfgCode.contains("jsres_doctor_activity_")) {
                        if (null != startCfg && null != endCfg) {
                            if (currDate.compareTo(startCfg.getCfgValue()) >= 0 && currDate.compareTo(endCfg.getCfgValue()) <= 0) {
                                // 活动
                                isActivity = true;
                            }
                        } else {
                            // 活动
                            isActivity = true;
                        }
                    } else if (cfgCode.contains("jsres_doctor_attendance_")) {
                        if (null != startCfg && null != endCfg) {
                            if (currDate.compareTo(startCfg.getCfgValue()) >= 0 && currDate.compareTo(endCfg.getCfgValue()) <= 0) {
                                // 考勤
                                isAttendance = true;
                            }
                        } else {
                            // 考勤
                            isAttendance = true;
                        }
                    } else if (cfgCode.contains("jsres_doctor_exam_")) {
                        // 出科考试
                        isCkk = true;
                    } else if (cfgCode.contains("jsres_doctor_manual_")) {
                        //培训手册
                        isPxsc = true;
                    } else if (cfgCode.contains("jsres_doctor_app_menu_")) {
                        if (null != startCfg && null != endCfg) {
                            if (currDate.compareTo(startCfg.getCfgValue()) >= 0 && currDate.compareTo(endCfg.getCfgValue()) <= 0) {
                                // 付费功能
                                appMenu = true;
                            }
                        } else {
                            // 付费功能
                            appMenu = true;
                        }
                    }
                }
            }
        }
        map.put("isActivity", isActivity);
        map.put("isAttendance", isAttendance);
        map.put("isCkk", isCkk);
        map.put("isPxsc", isPxsc);
        map.put("appMenu", appMenu);
        return map;
    }

    /**
     * @return void
     * @Author shengl
     * @Description 查询师资权限
     * @Date 2020-12-22
     * @Param [model, userFlow]
     **/
    @Override
    public void getTeacherAuthorityInfo(Model model, String userFlow, String orgFlow) {
        boolean isActivity = false;
        boolean isAttendance = false;
        boolean jzxxgl = false;
//        boolean pxsjsh = false;
        boolean pxsjsh = true;
        boolean sxpjcx = false;
        boolean zpyjfk = false;
        if (StringUtil.isNotBlank(userFlow)) {
            SysOrg sysOrg = orgMapper.selectByPrimaryKey(orgFlow);
            if (sysOrg != null) {
                // 审核通过
                if (CheckStatusEnum.Passed.getId().equals(sysOrg.getCheckStatusId())) {
                    JsresPowerCfgExample example = new JsresPowerCfgExample();
                    JsresPowerCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    criteria.andCfgValueEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    criteria.andCfgCodeLike("%" + orgFlow);
                    List<JsresPowerCfg> cfgList = jsresPowerCfgMapper.selectByExample(example);
                    if (cfgList != null && cfgList.size() > 0) {
                        for (JsresPowerCfg powerCfg : cfgList) {
                            String cfgCode = powerCfg.getCfgCode() == null ? "" : powerCfg.getCfgCode();
                            if (cfgCode.contains("jsres_hospital_activity_")) {
                                // 活动
                                isActivity = true;
                            } else if (cfgCode.contains("jsres_hospital_xykqcx_")) {
                                // 考勤
                                isAttendance = true;
                            } else if (cfgCode.contains("jsres_hospital_jzxxgl_")) {
                                // 讲座信息
                                jzxxgl = true;
                            } else if (cfgCode.contains("jsres_hospital_pxsjsh_")) {
                                // 出科审核
                                pxsjsh = true;
                            } else if (cfgCode.contains("jsres_hospital_sxpjcx_")) {
                                // 评价查询
                                sxpjcx = true;
                            } else if (cfgCode.contains("jsres_hospital_zpyjfk_")) {
                                // 住培意见反馈
                                zpyjfk = true;
                            }
                        }
                    }
                }
            }
        }
        model.addAttribute("isActivity", isActivity);
        model.addAttribute("isAttendance", isAttendance);
        model.addAttribute("jzxxgl", jzxxgl);
        model.addAttribute("pxsjsh", pxsjsh);
        model.addAttribute("sxpjcx", sxpjcx);
        model.addAttribute("zpyjfk", zpyjfk);
    }

    @Override
    public JsresPowerCfg readPowerCfg(String cfgCode) {
        return jsresPowerCfgMapper.selectByPrimaryKey(cfgCode);
    }

    @Override
    public JsresDeptConfig searchBaseDeptConfig(String orgFlow) {
        JsresDeptConfigExample example = new JsresDeptConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow).andDeptFlowIsNull();
        List<JsresDeptConfig> list = deptConfigMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * @param doctorFlow
     * @param rotationFlow
     * @Department：研发部
     * @Description 查询学员减免信息数量
     * @Author fengxf
     * @Date 2021/4/7
     */
    @Override
    public int countDoctorSchRotationDept(String doctorFlow, String rotationFlow) {
        return schRotationDeptExtMapper.countDoctorSchRotationDept(doctorFlow, rotationFlow);
    }

    @Override
    public JsresDeptConfig searchDeptCfg(String orgFlow, String schDeptFlow) {
        JsresDeptConfigExample example = new JsresDeptConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow).andDeptFlowEqualTo(schDeptFlow);
        List<JsresDeptConfig> list = deptConfigMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void modifyDoctor(ResDoctor doctor) {
        doctor.setModifyTime(DateUtil.getCurrDateTime());
        doctor.setModifyUserFlow(doctor.getDoctorFlow());
        doctorMapper.updateByPrimaryKeySelective(doctor);
    }

    @Override
    public SchRotation readRotation(String rotationFlow) {
        return rotationMapper.selectByPrimaryKey(rotationFlow);
    }

    @Override
    public Map<String, Object> getDoctorExtInfo(String doctorFlow) {
        Map<String, Object> doctorInfo = null;
        if (StringUtil.isNotBlank(doctorFlow)) {
            ResDoctor doctor = readResDoctor(doctorFlow);
            if (doctor != null) {
                doctorInfo = new HashMap<String, Object>();

                String endDate = DateUtil.getCurrDate();
                doctorInfo.put("endDate", endDate);

                doctorInfo.put("doctor", doctor);

                String trainingYears = doctor.getTrainingYears();

                long trainingDays = 365;
                if (TrainYearEnum.OneYear.getId().equals(trainingYears)) {
                    trainingDays *= 1;
                } else if (TrainYearEnum.TwoYear.getId().equals(trainingYears)) {
                    trainingDays *= 2;
                } else if (TrainYearEnum.ThreeYear.getId().equals(trainingYears)) {
                    trainingDays *= 3;
                } else {
                    trainingDays *= 0;
                }
                doctorInfo.put("trainingDays", trainingDays);

                if (StringUtil.isNotBlank(trainingYears)) {
                    trainingYears = TrainYearEnum.getNameById(trainingYears);
                    doctor.setTrainingYears(trainingYears);
                }
                // 查询轮转计划开始时间最早的日期
                String startDate = resultExtMapper.getSchMinStartDate(doctor.getRotationFlow(), doctorFlow);

                ResDoctorRecruitExample example = new ResDoctorRecruitExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andDoctorFlowEqualTo(doctorFlow);
                example.setOrderByClause("CREATE_TIME DESC");
                List<com.pinde.core.model.ResDoctorRecruit> resDoctorRecruits = recruitMapper.selectByExample(example);
                ResDoctorRecruit resDoctorRecruit = null;
                if (resDoctorRecruits != null && resDoctorRecruits.size() > 0) {
                    resDoctorRecruit = resDoctorRecruits.get(0);
                    doctorInfo.put("resDoctorRecruit", resDoctorRecruit);
                }

                long schDays = 0;
                if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
                    doctorInfo.put("startDate", startDate);

                    schDays = DateUtil.signDaysBetweenTowDate(endDate, startDate) + 1;
                    if (schDays < 0) {
                        schDays = 0;
                    }
                } else {
                    if (null != resDoctorRecruit) {
                        startDate = resDoctorRecruit.getRecruitDate();
                        doctorInfo.put("startDate", startDate);
                        if (StringUtil.isNotBlank(startDate)) {
                            schDays = DateUtil.signDaysBetweenTowDate(endDate, startDate) + 1;
                            if (schDays < 0) {
                                schDays = 0;
                            }
                        }
                    }
                }

                doctorInfo.put("schDays", schDays);

                if (schDays != 0 && trainingDays != 0) {
                    double schProgress = ((schDays) / (trainingDays * 1.0)) * 100;
                    BigDecimal b = new BigDecimal(schProgress).setScale(0, BigDecimal.ROUND_HALF_UP);
                    doctorInfo.put("schProgress", b.toString());
                } else {
                    doctorInfo.put("schProgress", 0);
                }
            }
        }
        return doctorInfo;
    }

    @Override
    public boolean saveLoginLog(SysUser user) {
        if (user != null) {
            String userFlow = user.getUserFlow();

            //查询当月是否存在
            String currDateTime = DateUtil.getCurrDateTime();
            String currMonth = currDateTime.substring(0, 6);

            SysLogExample example = new SysLogExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andLogTimeLike(currMonth + "%").andUserFlowEqualTo(userFlow).andOperIdEqualTo("AppLogin");
            List<SysLog> logList = logMapper.selectByExample(example);

            if (logList != null && !logList.isEmpty()) {
                return false;
            }
            SysLog log = new SysLog();
            log.setLogFlow(PkUtil.getUUID());
            log.setUserFlow(userFlow);
            log.setUserName(user.getUserName());
            log.setUserCode(user.getUserCode());
            log.setOrgFlow(user.getOrgFlow());
            log.setOrgName(user.getOrgName());
            log.setWsId(com.pinde.core.common.GlobalConstant.RES_WS_ID);
            log.setLogTime(currDateTime);
            log.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            log.setCreateTime(currDateTime);
            log.setCreateUserFlow(userFlow);
            log.setOperId("AppLogin");
            logMapper.insertSelective(log);

            return true;
        }
        return false;
    }

    //获取系统配置信息
    @Override
    public String getCfgCode(String code) {
        if (StringUtil.isNotBlank(code)) {

            String v = GlobalUtil.getLocalCfgMap().get(code);
            if (StringUtil.isNotBlank(v))
                return v;
            SysCfg cfg = cfgMapper.selectByPrimaryKey(code);
            if (cfg != null) {
                String val = cfg.getCfgValue();
                if (!StringUtil.isNotBlank(val)) {
                    val = cfg.getCfgBigValue();
                }
                return val;
            }
        }
        return null;
    }

    //获取江苏西医系统配置信息
    @Override
    public String getJsResCfgCode(String code) {
        if (StringUtil.isNotBlank(code)) {
            JsresPowerCfg cfg = jsresPowerCfgMapper.selectByPrimaryKey(code);
            if (cfg != null) {
                String val = cfg.getCfgValue();
                return val;
            }
        }
        return null;
    }

    @Override
    public String getJsResCfgCodeNew(String code) {
        if (StringUtil.isNotBlank(code)) {
            JsresPowerCfg cfg = jsresPowerCfgMapper.selectByPrimaryKey(code);
            if (cfg != null) {
                String statusId = cfg.getCheckStatusId();
                if (CheckStatusEnum.Passed.getId().equals(statusId)) {
                    return com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y;
                }
            }
        }
        return null;
    }

    @Override
    public String getJsResCfgAppMenu(String userFlow) {
        if (StringUtil.isNotBlank(userFlow)) {
            JsresPowerCfgExample example = new JsresPowerCfgExample();
            JsresPowerCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            criteria.andCheckStatusIdEqualTo(CheckStatusEnum.Passed.getId());
            ArrayList arrayList = new ArrayList();
            // 付费功能
            arrayList.add("jsres_doctor_app_menu_" + userFlow);
            arrayList.add("jsres_doctor_activity_" + userFlow);
            arrayList.add("jsres_doctor_attendance_" + userFlow);
            criteria.andCfgCodeIn(arrayList);
            int cfgCount = jsresPowerCfgMapper.countByExample(example);
            if (cfgCount > 0) {
                return com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y;
            }
        }
        return null;
    }

    @Override
    public String getJsResCfgCheckByCode(String code) {
        if (StringUtil.isNotBlank(code)) {
            JsresPowerCfgExample example = new JsresPowerCfgExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andCfgCodeEqualTo(code);
            List<JsresPowerCfg> cfgList = jsresPowerCfgMapper.selectByExample(example);
            if (cfgList != null && cfgList.size() > 0) {
                String checkStatusId = cfgList.get(0).getCheckStatusId();
                if (StringUtil.isNotBlank(checkStatusId)) {
                    return checkStatusId;
                }
            }
        }
        return null;
    }

    @Override
    public List<ResDoctorSchProcess> searchSchProcess(String userFlow,
                                                      String deptFlow) {
        ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("SCH_START_DATE DESC");
        List<ResDoctorSchProcess> processList = processMapper.selectByExample(example);
        return processList;
    }

    @Override
    public List<SysDept> searchSysDeptList(String orgFlow, String searchStr) {
//		SysDeptExample example = new SysDeptExample();
//		example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow);
//		example.setOrderByClause("ORDINAL");
//		List<SysDept> currOrgDepts = sysDeptMapper.selectByExample(example);
//		if(currOrgDepts!=null){
//			resultDepts = currOrgDepts;
//		}

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("deptName", searchStr);
        List<SysDept> resultDepts = sysDeptExtMapper.getBaseAndExtDept(paramMap);

        if (resultDepts != null && !resultDepts.isEmpty()) {
            SysOrgExample orgExample = new SysOrgExample();
            orgExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowNotEqualTo(orgFlow);

            List<SysOrg> orgs = orgMapper.selectByExample(orgExample);
            Map<String, SysOrg> orgMap = new HashMap<String, SysOrg>();
            if (orgs != null && !orgs.isEmpty()) {
                for (SysOrg so : orgs) {
                    orgMap.put(so.getOrgFlow(), so);
                }
            }

            for (SysDept dept : resultDepts) {
                SysOrg so = orgMap.get(dept.getOrgFlow());
                if (so != null) {
                    dept.setDeptName(dept.getDeptName() + "[" + so.getOrgName() + "]");
                }
            }
        }
        return resultDepts;
    }

    @Override
    public List<SysUser> searchTeacherList(String sysDeptFlow, String roleFlow, String userName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sysDeptFlow", sysDeptFlow);
        paramMap.put("roleFlow", roleFlow);
        paramMap.put("userName", userName);
        return sysUserExtMapper.searchUserList(paramMap);
    }

    @Override
    public void addSubDept(String userFlow, String deptFlow,
                           String sysDeptFlow, String headFlow, String teacherFlow,
                           String startDate, String endDate) throws ParseException {
        ResDoctor doctor = doctorMapper.selectByPrimaryKey(userFlow);
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(sysDeptFlow);
        SchRotationDept rotationDept = schRotationDeptMapper.selectByPrimaryKey(deptFlow);

        String currOrgFlow = doctor.getOrgFlow();
        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
            currOrgFlow = doctor.getSecondOrgFlow();
        }
        SysOrg org = orgMapper.selectByPrimaryKey(currOrgFlow);
        SchArrangeResult result = new SchArrangeResult();
        result.setResultFlow(PkUtil.getUUID());
        result.setArrangeFlow(result.getResultFlow());
        result.setDoctorFlow(userFlow);
        result.setSessionNumber(doctor.getSessionNumber());
        result.setDoctorName(doctor.getDoctorName());
        result.setOrgFlow(currOrgFlow);
        result.setOrgName(org.getOrgName());
        result.setSchStartDate(startDate);
        result.setSchEndDate(endDate);
        result.setRotationFlow(rotationDept.getRotationFlow());

        result.setDeptFlow(sysDeptFlow);
        result.setDeptName(sysDept.getDeptName());
        result.setSchDeptFlow(sysDeptFlow);

        String sysDeptName = sysDept.getDeptName();
        String deptOrgFlow = sysDept.getOrgFlow();
        if (StringUtil.isNotBlank(deptOrgFlow)) {
            SysOrg so = orgMapper.selectByPrimaryKey(deptOrgFlow);
            if (so != null && !so.getOrgFlow().equals(currOrgFlow)) {
                sysDeptName += ("[" + so.getOrgName() + "]");
            }
        }

        result.setSchDeptName(sysDeptName);
        result.setStandardDeptId(rotationDept.getStandardDeptId());
        result.setStandardDeptName(rotationDept.getStandardDeptName());
        result.setStandardGroupFlow(rotationDept.getGroupFlow());
        result.setIsRequired(rotationDept.getIsRequired());
        result.setCreateTime(DateUtil.getCurrDateTime());
        result.setCreateUserFlow(userFlow);
        result.setModifyTime(DateUtil.getCurrDateTime());
        result.setModifyUserFlow(userFlow);
        result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        Map<String, String> map = new HashMap<>();
        map.put("startDate", result.getSchStartDate());
        map.put("endDate", result.getSchEndDate());
        Double month = TimeUtil.getMonthsBetween(map);
        String schMonth = String.valueOf(Double.parseDouble(month + ""));
        result.setSchMonth(schMonth);
        result.setBaseAudit("Passed");
	/*	//查看轮转计划排班设置是否开启
		JsresPowerCfg cfg = jsresPowerCfgMapper.selectByPrimaryKey("jsres_"+doctor.getOrgFlow()+"_org_process_scheduling");
		if (null ==cfg || StringUtil.isEmpty(cfg.getCfgValue()) || cfg.getCfgValue().equals(com.pinde.core.common.GlobalConstant.FLAG_N) ){
			result.setBaseAudit("user");	//表示是学员自己创建的且轮转计划排班设置未开启
		}else {
			result.setBaseAudit("Passing"); //待审核
		}*/
        resultMapper.insert(result);


        ResDoctorSchProcess process = new ResDoctorSchProcess();
        process.setProcessFlow(PkUtil.getUUID());
        process.setUserFlow(userFlow);
        process.setOrgFlow(org.getOrgFlow());
        process.setOrgName(org.getOrgName());
        process.setDeptFlow(sysDeptFlow);
        process.setDeptName(sysDept.getDeptName());
        process.setSchDeptFlow(sysDeptFlow);
        process.setSchDeptName(sysDept.getDeptName());
        process.setSchResultFlow(result.getResultFlow());
        process.setSchStartDate(startDate);
        process.setSchEndDate(endDate);
        if (StringUtil.isNotEmpty(teacherFlow)) {
            process.setTeacherUserFlow(teacherFlow);
            process.setTeacherUserName(sysUserMapper.selectByPrimaryKey(teacherFlow).getUserName());
        }
        if (StringUtil.isNotEmpty(headFlow)) {
            process.setHeadUserFlow(headFlow);
            process.setHeadUserName(sysUserMapper.selectByPrimaryKey(headFlow).getUserName());
        }
        process.setCreateTime(DateUtil.getCurrDateTime());
        process.setCreateUserFlow(userFlow);
        process.setModifyTime(DateUtil.getCurrDateTime());
        process.setModifyUserFlow(userFlow);
        process.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

        processMapper.insertSelective(process);
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        example.createCriteria().andOperUserFlowEqualTo(userFlow).andSchRotationDeptFlowEqualTo(deptFlow)
                .andRecTypeIdEqualTo(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResSchProcessExpress> recList = expressMapper.selectByExampleWithBLOBs(example);
        String recContent = "";
        if (recList != null && recList.size() > 0) {
            recContent = recList.get(0).getRecContent();
        }
        try {
            updateResultHaveAfter(deptFlow, userFlow, recContent);
        } catch (DocumentException e) {
            logger.error("", e);
        }
    }

    @Override
    public void modSubDept(String userFlow, String deptFlow,
                           String subDeptFlow, String sysDeptFlow, String headFlow, String teacherFlow,
                           String startDate, String endDate) throws ParseException {
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(sysDeptFlow);


        ResDoctor doctor = doctorMapper.selectByPrimaryKey(userFlow);
        String currOrgFlow = doctor.getOrgFlow();
        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
            currOrgFlow = doctor.getSecondOrgFlow();
        }
        String sysDeptName = sysDept.getDeptName();
        String deptOrgFlow = sysDept.getOrgFlow();
        if (StringUtil.isNotBlank(deptOrgFlow)) {
            SysOrg so = orgMapper.selectByPrimaryKey(deptOrgFlow);
            if (so != null && !so.getOrgFlow().equals(currOrgFlow)) {
                sysDeptName += ("[" + so.getOrgName() + "]");
            }
        }
        SchArrangeResult result = resultMapper.selectByPrimaryKey(subDeptFlow);
        if (result != null) {
			/*if (!result.getSchDeptFlow().equals(sysDeptFlow) || !result.getSchStartDate().equals(startDate)
					|| !result.getSchEndDate().equals(endDate)){*/
            //查看轮转计划排班设置是否开启
            JsresPowerCfg cfg = jsresPowerCfgMapper.selectByPrimaryKey("jsres_" + doctor.getOrgFlow() + "_org_process_scheduling_org");
            if (null != cfg && StringUtil.isNotEmpty(cfg.getCfgValue()) && cfg.getCfgValue().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                result.setBaseAudit("Passing"); //待审核
            }
            /*}*/
            result.setDeptFlow(sysDeptFlow);
            result.setDeptName(sysDept.getDeptName());
            result.setSchDeptFlow(sysDeptFlow);
            result.setSchDeptName(sysDeptName);
            result.setSchStartDate(startDate);
            result.setSchEndDate(endDate);
            Map<String, String> map = new HashMap<>();
            map.put("startDate", result.getSchStartDate());
            map.put("endDate", result.getSchEndDate());
            Double month = TimeUtil.getMonthsBetween(map);
            String schMonth = String.valueOf(Double.parseDouble(month + ""));
            result.setSchMonth(schMonth);
            result.setModifyTime(DateUtil.getCurrDateTime());
            result.setModifyUserFlow(userFlow);
            resultMapper.updateByPrimaryKeySelective(result);

            ResDoctorSchProcess process = getProcessByResultFlow(result.getResultFlow());
            if (process != null) {
                process.setDeptFlow(sysDeptFlow);
                process.setDeptName(sysDept.getDeptName());
                process.setSchDeptFlow(sysDeptFlow);
                process.setSchDeptName(sysDeptName);

                process.setSchStartDate(startDate);
                process.setSchEndDate(endDate);
                if (StringUtil.isNotEmpty(teacherFlow)) {
                    process.setTeacherUserFlow(teacherFlow);
                    process.setTeacherUserName(sysUserMapper.selectByPrimaryKey(teacherFlow).getUserName());
                }
                if (StringUtil.isNotEmpty(headFlow)) {
                    process.setHeadUserFlow(headFlow);
                    process.setHeadUserName(sysUserMapper.selectByPrimaryKey(headFlow).getUserName());
                }
                process.setModifyTime(DateUtil.getCurrDateTime());
                process.setModifyUserFlow(userFlow);
                processMapper.updateByPrimaryKeySelective(process);
            } else {
                process = new ResDoctorSchProcess();
                process.setProcessFlow(PkUtil.getUUID());
                process.setUserFlow(userFlow);
                process.setOrgFlow(result.getOrgFlow());
                process.setOrgName(result.getOrgName());
                process.setDeptFlow(sysDeptFlow);
                process.setDeptName(sysDept.getDeptName());
                process.setSchDeptFlow(sysDeptFlow);
                process.setSchDeptName(sysDeptName);
                process.setSchResultFlow(result.getResultFlow());
                process.setSchStartDate(startDate);
                process.setSchEndDate(endDate);
                process.setTeacherUserFlow(teacherFlow);
                process.setTeacherUserName(sysUserMapper.selectByPrimaryKey(teacherFlow).getUserName());
                process.setHeadUserFlow(headFlow);
                process.setHeadUserName(sysUserMapper.selectByPrimaryKey(headFlow).getUserName());
                process.setCreateTime(DateUtil.getCurrDateTime());
                process.setCreateUserFlow(userFlow);
                process.setModifyTime(DateUtil.getCurrDateTime());
                process.setModifyUserFlow(userFlow);
                process.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                processMapper.insertSelective(process);
            }
        }
    }

    @Override
    public ResDoctorSchProcess getProcessByResultFlow(String resultFlow) {
        ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchResultFlowEqualTo(resultFlow);
        List<ResDoctorSchProcess> processList = processMapper.selectByExample(example);
        if (processList.size() > 0) {
            return processList.get(0);
        }
        return null;
    }

    @Override
    public int searchRecCount(String subDeptFlow) {
        ResDoctorSchProcess process = getProcessByResultFlow(subDeptFlow);
        if (process != null) {
            ResRecExample example = new ResRecExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(process.getProcessFlow());
            return recMapper.countByExample(example);
        } else {
            return 0;
        }
    }

    @Override
    public int searchCount(String subDeptFlow, String userFlow) {
        ResDoctorSchProcess process = getProcessByResultFlow(subDeptFlow);
        if (null != process && StringUtil.isNotBlank(process.getCreateUserFlow())) {
            if (userFlow.equals(process.getCreateUserFlow())) {
                return 0;
            } else {
                return 1;
            }

        } else {
            return 1;
        }
    }

    @Override
    public ResRec readResRec(String dataFlow) {
//		ResRec record = new ResRec();
//		ResRecCaseRegistry caseRegistry = caseRegistryMapper.selectByPrimaryKey(dataFlow);
//		if (caseRegistry != null) {
//			BeanUtils.copyProperties(caseRegistry, record);
//		}
//		ResRecDiseaseRegistry diseaseRegistry = diseaseRegistryMapper.selectByPrimaryKey(dataFlow);
//		if (diseaseRegistry != null) {
//			BeanUtils.copyProperties(diseaseRegistry, record);
//		}
//		ResRecSkillRegistry skillRegistry = skillRegistryMapper.selectByPrimaryKey(dataFlow);
//		if (skillRegistry != null) {
//			BeanUtils.copyProperties(skillRegistry, record);
//		}
//		ResRecOperationRegistry operationRegistry = operationRegistryMapper.selectByPrimaryKey(dataFlow);
//		if (operationRegistry != null) {
//			BeanUtils.copyProperties(operationRegistry, record);
//		}
//		ResRecCampaignRegistry campaignRegistry = campaignRegistryMapper.selectByPrimaryKey(dataFlow);
//		if (campaignRegistry != null) {
//			BeanUtils.copyProperties(campaignRegistry, record);
//		}
//		ResRecLanguageRegistry languageRegistry = languageRegistryMapper.selectByPrimaryKey(dataFlow);
//		if (languageRegistry != null) {
//			BeanUtils.copyProperties(languageRegistry, record);
//		}
//		return record;
        return recMapper.selectByPrimaryKey(dataFlow);
    }

    @Override
    public ResRec readResRecByDataType(String dataFlow, String dataType) {
//		ResRec record = new ResRec();
//		if ("mr".equals(dataType)) {
//			ResRecCaseRegistry caseRegistry = caseRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(caseRegistry, record);
//		}
//		if ("disease".equals(dataType)) {
//			ResRecDiseaseRegistry diseaseRegistry = diseaseRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(diseaseRegistry, record);
//		}
//		if ("skill".equals(dataType)) {
//			ResRecSkillRegistry skillRegistry = skillRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(skillRegistry, record);
//		}
//		if ("operation".equals(dataType)) {
//			ResRecOperationRegistry operationRegistry = operationRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(operationRegistry, record);
//		}
//		if ("activity".equals(dataType)) {
//			ResRecCampaignRegistry campaignRegistry = campaignRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(campaignRegistry, record);
//		}
//		if ("languageTeaching".equals(dataType)) {
//			ResRecLanguageRegistry languageRegistry = languageRegistryMapper.selectByPrimaryKey(dataFlow);
//			BeanUtils.copyProperties(languageRegistry, record);
//		}
//		return record;
        return null;
    }

    @Override
    public List<ResRec> searchGrade(String userFlow, String deptFlow) {
        List<String> recTypes = new ArrayList<String>();
        recTypes.add(com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId());
        recTypes.add(com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId());
        ResRecExample example = new ResRecExample();
        example.createCriteria().andOperUserFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchRotationDeptFlowEqualTo(deptFlow)
                .andRecTypeIdIn(recTypes);
        return recMapper.selectByExampleWithBLOBs(example);
    }


    @Override
    public List<ResRec> searchByDoctorFlow(String doctorFlow, String date) {
        ResRecExample example = new ResRecExample();
        ResRecExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if (StringUtil.isNotBlank(doctorFlow)) {
            criteria.andOperUserFlowEqualTo(doctorFlow);
        }
        if (StringUtil.isNotBlank(date)) {
            criteria.andModifyTimeGreaterThan(date);
        }
        return recMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<DeptTeacherGradeInfo> searchNewGrade(String userFlow, String deptFlow) {
        List<String> recTypes = new ArrayList<String>();
        recTypes.add(com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId());
        recTypes.add(com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId());
        DeptTeacherGradeInfoExample example = new DeptTeacherGradeInfoExample();
        example.createCriteria().andOperUserFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchRotationDeptFlowEqualTo(deptFlow)
                .andRecTypeIdIn(recTypes);
        return gradeInfoMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<DeptTeacherGradeInfo> searchAllGrade(String userFlow) {
        List<String> recTypes = new ArrayList<String>();
        recTypes.add(com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId());
        recTypes.add(com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId());
        DeptTeacherGradeInfoExample example = new DeptTeacherGradeInfoExample();
        example.createCriteria().andOperUserFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andRecTypeIdIn(recTypes);
        return gradeInfoMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<DeptTeacherGradeInfo> searchAllGradeTwo(String userFlow) {
        List<String> recTypes = new ArrayList<String>();
        recTypes.add(com.pinde.core.common.enums.ResRecTypeEnum.TeacherAssess.getId());
        recTypes.add(com.pinde.core.common.enums.ResRecTypeEnum.TeacherAssessTwo.getId());
        DeptTeacherGradeInfoExample example = new DeptTeacherGradeInfoExample();
        example.createCriteria().andOperUserFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andRecTypeIdIn(recTypes);
        return gradeInfoMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public ResDoctorSchProcessEvalWithBLOBs getDoctorProcessEval(String processFlow, String evalMonth) {
        ResDoctorSchProcessEvalExample evalExample = new ResDoctorSchProcessEvalExample();
        evalExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andProcessFlowEqualTo(processFlow).andEvalMonthEqualTo(evalMonth);
        List<ResDoctorSchProcessEvalWithBLOBs> list = processEvalMapper.selectByExampleWithBLOBs(evalExample);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public ResDoctorSchProcessEvalWithBLOBs getDoctorProcessEvalByFlow(String recordFlow) {
        return processEvalMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<FromTitle> parseFromXmlForList(String content) {
        if (content != null) {
            try {
                List<FromTitle> titleList = new ArrayList<>();
                Document dom;
                dom = DocumentHelper.parseText(content);
                String titleXpath = "//title";
                List<Element> titleElementList = dom.selectNodes(titleXpath);
                if (titleElementList != null && !titleElementList.isEmpty()) {
                    int titleSum = 0;
                    for (Element te : titleElementList) {
                        FromTitle fromTitle = new FromTitle();
                        fromTitle.setId(te.attributeValue("id"));
                        fromTitle.setName(te.attributeValue("name"));
                        fromTitle.setTypeName(te.attributeValue("typeName"));
                        fromTitle.setOrderNum(te.attributeValue("orderNum"));
                        List<FromItem> items = new ArrayList<>();
                        int score = 0;
                        List<Element> itemElementList = te.elements("item");
                        if (itemElementList != null && !itemElementList.isEmpty()) {
                            for (Element ie : itemElementList) {
                                FromItem item = new FromItem();
                                item.setId(ie.attributeValue("id"));
                                item.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                                item.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                                item.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
                                items.add(item);
                                if (StringUtil.isNotBlank(item.getScore())){
                                    String sc = item.getScore();
                                    if(sc.indexOf('.') != -1){
                                        sc = sc.substring(0, sc.indexOf('.'));
                                    }
                                    score += Integer.valueOf(sc);
                                }


                            }
                        }
                        fromTitle.setScore(score);
                        if (items.size() > 0) {

                            Collections.sort(items, new Comparator<FromItem>() {
                                @Override
                                public int compare(FromItem f1, FromItem f2) {
                                    String order1 = f1.getOrder();
                                    String order2 = f2.getOrder();

                                    if (order1 == null) {
                                        return -1;
                                    } else if (order2 == null) {
                                        return 1;
                                    } else if (order1 != null && order2 != null) {
                                        return order1.compareTo(order2);
                                    }
                                    return 0;
                                }
                            });
                        }
                        fromTitle.setItems(items);
                        titleList.add(fromTitle);
                    }
                }
                return titleList;
            } catch (DocumentException e) {
                //
                return null;
            }
        }
        return null;
    }

    @Override
    public ResDoctorProcessEvalConfig getProcessEvalConfig(String orgFlow) {
        ResDoctorProcessEvalConfigExample example = new ResDoctorProcessEvalConfigExample();
        if (StringUtil.isNotBlank(orgFlow)) {
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andOrgFlowEqualTo(orgFlow);
            List<ResDoctorProcessEvalConfig> list = evalConfigMapper.selectByExampleWithBLOBs(example);
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public ResDoctorProcessEvalConfig getProcessEvalConfigByFlow(String configFlow) {
        return evalConfigMapper.selectByPrimaryKey(configFlow);
    }

    @Override
    public String getScoreXml(HttpServletRequest request) {
        if (request != null) {
            String rootName = "MonthEval";
            Map<String, String[]> paramsMap = request.getParameterMap();
            if (paramsMap != null && !paramsMap.isEmpty()) {
                //创建xmldom对象和根节点
                Document doc = DocumentHelper.createDocument();
                Element root = doc.addElement(rootName);

                for (String key : paramsMap.keySet()) {
                    String[] vs = paramsMap.get(key);
                    String vss = "";
                    if (vs != null && vs.length > 0) {
                        vss = vs[0];
                    }
//					try {
//						if(vss!=null) vss = URLDecoder.decode(vss,"UTF-8") ;
//					} catch (UnsupportedEncodingException e) {
//						 logger.error("",e);
//					}
                    //开始创建xml子节点
                    Element currEle = root.addElement(key);
                    currEle.setText(vss);
                }
                return doc.asXML();
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> parseScoreXml(String content) {
        if (content != null) {
            try {
                Map<String, Object> map = new HashMap<String, Object>();
                Document dom;
                dom = DocumentHelper.parseText(content);
                Element root = dom.getRootElement();
                List<Element> itemsElements = root.elements();
                if (itemsElements != null && itemsElements.size() > 0) {
                    for (Element el : itemsElements) {
                        map.put(String.valueOf(el.attributeValue("id")), el.getText());
                    }
                }
                return map;
            } catch (DocumentException e) {

                return null;
            }
        }
        return null;
    }

    @Override
    public int saveProcessEval(ResDoctorSchProcessEvalWithBLOBs eval, SysUser user) {

        if (eval != null) {
            if (StringUtil.isNotBlank(eval.getRecordFlow())) {//修改
                eval.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                eval.setModifyUserFlow(user.getUserFlow());
                eval.setModifyTime(DateUtil.getCurrDateTime());
                return this.processEvalMapper.updateByPrimaryKeySelective(eval);
            } else {//新增
                eval.setRecordFlow(PkUtil.getUUID());
                eval.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                eval.setCreateUserFlow(user.getUserFlow());
                eval.setCreateTime(DateUtil.getCurrDateTime());
                eval.setModifyUserFlow(user.getUserFlow());
                eval.setModifyTime(DateUtil.getCurrDateTime());
                return this.processEvalMapper.insertSelective(eval);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public SysDept readSysDept(String deptFlow) {
        return sysDeptMapper.selectByPrimaryKey(deptFlow);
    }

    @Override
    public List<SysDept> getHeadDeptList(String userFlow, String deptFlow) {
        return sysDeptExtMapper.getHeadDeptList(userFlow, deptFlow);
    }

    @Override
    public Map<String, String> getGradeMap(List<ResRec> gradeList) {
        Map<String, String> gradeMap = new HashMap<String, String>();
        for (ResRec rec : gradeList) {
            try {
                Document doc = DocumentHelper.parseText(rec.getRecContent());
                String totalScore = StringUtil.defaultIfEmpty(doc.getRootElement().elementText("totalScore"), "未评价");
                gradeMap.put(rec.getDeptFlow() + "_" + rec.getRecTypeId(), totalScore);
            } catch (DocumentException e) {
                logger.error("", e);
            }
        }
        return gradeMap;
    }

    @Override
    public Map<String, String> getNewGradeMap(List<DeptTeacherGradeInfo> gradeList) {
        Map<String, String> gradeMap = new HashMap<String, String>();
        for (DeptTeacherGradeInfo rec : gradeList) {
            try {
                Document doc = DocumentHelper.parseText(rec.getRecContent());
                String totalScore = StringUtil.defaultIfEmpty(doc.getRootElement().elementText("totalScore"), "未评价");
                if (!"未评价".equals(totalScore) && totalScore.contains(".")) {
                    totalScore = totalScore.substring(0, totalScore.indexOf("."));
                }
                gradeMap.put(rec.getProcessFlow() + "_" + rec.getRecTypeId(), totalScore);
                gradeMap.put(rec.getProcessFlow(), rec.getRecFlow());
                gradeMap.put(rec.getProcessFlow() + "TypeId", rec.getRecTypeId());
            } catch (DocumentException e) {
                logger.error("", e);
            }
        }
        return gradeMap;
    }

    @Override
    public ResAssessCfg getGradeTemplate(String assessType) {
        if (StringUtil.isNotBlank(assessType)) {
            ResAssessCfgExample example = new ResAssessCfgExample();
            ResAssessCfgExample.Criteria criteria = example.createCriteria()
                    .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andCfgCodeIdEqualTo(assessType);

            List<ResAssessCfg> assessList = assessCfgMapper.selectByExampleWithBLOBs(example);

            if (assessList != null && !assessList.isEmpty()) {
                return assessList.get(0);
            }
        }
        return null;
    }

    //解析评分模板
    @Override
    public List<Map<String, Object>> parseAssessCfg(String content) {
        if (StringUtil.isNotBlank(content)) {
            try {
                Document dom = DocumentHelper.parseText(content);
                if (dom != null) {
                    Element root = dom.getRootElement();
                    if (root != null) {
                        //获取根节点下的所有title节点
                        List<Element> titles = root.elements();
                        if (titles != null && !titles.isEmpty()) {
                            List<Map<String, Object>> dataMaps = new ArrayList<Map<String, Object>>();
                            for (Element i : titles) {
                                Map<String, Object> dataMap = new HashMap<String, Object>();
                                //获取title对象的所有属性,属性名为key,属性值为value
                                putAttrToMap(i, dataMap);
                                List<Element> items = i.elements();
                                if (items != null && !items.isEmpty()) {
                                    List<Map<String, Object>> itemMaps = new ArrayList<Map<String, Object>>();
                                    for (Element si : items) {
                                        Map<String, Object> itemMap = new HashMap<String, Object>();
                                        //获取节点的所有属性包装进Map
                                        putAttrToMap(si, itemMap);
                                        //以节点名称为key将节点文本包装进map
                                        putTextToMap(si, itemMap);
                                        itemMaps.add(itemMap);
                                    }
                                    dataMap.put("items", itemMaps);
                                }
                                dataMaps.add(dataMap);
                            }
                            return dataMaps;
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        return null;
    }

    //获取节点的所有属性包装进Map
    private <T> void putAttrToMap(Element e, Map<String, T> map) {
        if (e != null && map != null) {
            List<?> attrs = e.attributes();
            if (attrs != null && !attrs.isEmpty()) {
                int attrSize = attrs.size();
                for (int index = 0; index < attrSize; index++) {
                    Attribute attr = (Attribute) attrs.get(index);
                    if (attr != null) {
                        String name = attr.getName();
                        String val = attr.getValue();
                        map.put(name, (T) val);
                    }
                }
            }
        }
    }

    //以节点名称为key将节点文本包装进map
    private <T> void putTextToMap(Element e, Map<String, T> map) {
        if (e != null && map != null) {
            List<Element> es = e.elements();
            if (es != null && !es.isEmpty()) {
                for (Element se : es) {
                    String eleName = se.getName();
                    String eleText = se.getText();
                    map.put(eleName, (T) eleText);
                }
            }
        }
    }

    //解析评分数据
    @Override
    public List<Map<String, String>> parseDocGradeXml(String content) {
        if (StringUtil.isNotBlank(content)) {
            List<Map<String, String>> gradeMaps = parseGradeXml(content);
            if (gradeMaps != null && !gradeMaps.isEmpty()) {
                List<Map<String, String>> gms = new ArrayList<Map<String, String>>();
                //重新包装解析的评分数据
                for (Map<String, String> grameMap : gradeMaps) {
                    String id = grameMap.get("assessId");
                    String score = grameMap.get("score");
                    String lostReason = grameMap.get("lostReason");

                    Map<String, String> gm1 = new HashMap<String, String>();
                    gm1.put("inputId", id + "_score");
                    gm1.put("value", score);

                    Map<String, String> gm2 = new HashMap<String, String>();
                    gm2.put("inputId", id + "_lostReason");
                    gm2.put("value", lostReason);

                    gms.add(gm1);
                    gms.add(gm2);
                }
                return gms;
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> parseGradeXmlNew(String recContent) {
        Map<String, Object> gradeMap = null;
        if (StringUtil.isNotBlank(recContent)) {
            try {
                Document doc = DocumentHelper.parseText(recContent);
                Element root = doc.getRootElement();
                if (root != null) {
                    List<Element> items = root.elements("grade");
                    if (items != null && items.size() > 0) {
                        gradeMap = new HashMap<String, Object>();
                        for (Element e : items) {
                            String assessId = e.attributeValue("assessId");
                            Map<String, String> dataMap = new HashMap<String, String>();
                            gradeMap.put(assessId, dataMap);

                            Element score = e.element("score");
                            if (score != null) {
                                String scoreStr = score.getText();
                                dataMap.put("score", scoreStr);
                            }

                        }

                        Element totalScore = root.element("totalScore");
                        if (totalScore != null) {
                            gradeMap.put("totalScore", totalScore.getText());
                        }
                        Element lostReason = root.element("lostReason");
                        if (lostReason != null) {
                            gradeMap.put("lostReason", lostReason.getText());
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        return gradeMap;
    }

    @Override
    public ResAssessCfg readResAssessCfg(String cfgFlow) {
        if (StringUtil.isNotBlank(cfgFlow)) {
            return assessCfgMapper.selectByPrimaryKey(cfgFlow);
        }
        return null;
    }

    //讲评分数据解析成基础的对象集合格式
    private List<Map<String, String>> parseGradeXml(String content) {
        if (StringUtil.isNotBlank(content)) {
            try {
                Document dom = DocumentHelper.parseText(content);
                if (dom != null) {
                    Element root = dom.getRootElement();
                    if (root != null) {
                        List<Element> grades = root.elements();
                        if (grades != null && !grades.isEmpty()) {
                            List<Map<String, String>> gradeMaps = new ArrayList<Map<String, String>>();
                            for (Element grade : grades) {
                                Map<String, String> gradeMap = new HashMap<String, String>();
                                putAttrToMap(grade, gradeMap);
                                putTextToMap(grade, gradeMap);
                                gradeMaps.add(gradeMap);
                            }
                            return gradeMaps;
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> parseGradeInfoXml(String recContent) {
        Map<String, Object> gradeMap = null;
        if (StringUtil.isNotBlank(recContent)) {
            try {
                Document doc = DocumentHelper.parseText(recContent);
                Element root = doc.getRootElement();
                if (root != null) {
                    List<Element> items = root.elements("grade");
                    if (items != null && items.size() > 0) {
                        gradeMap = new HashMap<String, Object>();
                        for (Element e : items) {
                            String assessId = e.attributeValue("assessId");
                            Map<String, String> dataMap = new HashMap<String, String>();
                            gradeMap.put(assessId, dataMap);

                            Element score = e.element("score");
                            if (score != null) {
                                String scoreStr = score.getText();
                                dataMap.put("score", scoreStr);
                            }
                            Element lostReason = e.element("lostReason");
                            if (lostReason != null) {
                                dataMap.put("lostReason", lostReason.getText());
                            }
                        }

                        Element totalScore = root.element("totalScore");
                        if (totalScore != null) {
                            gradeMap.put("totalScore", totalScore.getText());
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        return gradeMap;
    }

    @Override
    public int saveUserInfo(SysUser user) {
        if (StringUtil.isNotBlank(user.getUserFlow())) {
            user.setModifyTime(DateUtil.getCurrDateTime());
            user.setModifyUserFlow(user.getUserFlow());
            return sysUserMapper.updateByPrimaryKeySelective(user);
        }
        return 0;
    }

    @Override
    public List<SysDept> getOrgDeptList(String orgFlow, String deptName) {
        SysDeptExample example = new SysDeptExample();
        SysDeptExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow);
        if (StringUtil.isNotBlank(deptName)) {
            criteria.andDeptNameLike("%" + deptName + "%");
        }
        return sysDeptMapper.selectByExample(example);
    }

    @Override
    public List<ResJointOrg> searchResJointByJointOrgFlow(String orgFlow) {
        ResJointOrgExample example = new ResJointOrgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andJointOrgFlowEqualTo(orgFlow);
        return resJointOrgMapper.selectByExample(example);
    }

    @Override
    public ResRec getGradeRec(String userFlow, String deptFlow,
                              String processFlow, String recTypeId) {
        ResRecExample example = new ResRecExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
                .andOperUserFlowEqualTo(userFlow).andProcessFlowEqualTo(processFlow).andSchRotationDeptFlowEqualTo(deptFlow);
        List<ResRec> recList = recMapper.selectByExampleWithBLOBs(example);
        if (recList.size() > 0) {
            return recList.get(0);
        }
        return null;
    }

    @Override
    public List<SysDict> getDictListByDictId(String doctorTrainingSpe) {
        if (StringUtil.isNotBlank(doctorTrainingSpe)) {
            SysDictExample example = new SysDictExample();
            example.createCriteria().andDictTypeIdEqualTo(doctorTrainingSpe).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<SysDict> sysDictList = sysDictMapper.selectByExample(example);

            return sysDictList;
        }
        return null;
    }

    @Override
    public List<ResLectureScanRegist> searchIsScan(String lectureFlow) {
        ResLectureScanRegistExample example = new ResLectureScanRegistExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andLectureFlowEqualTo(lectureFlow).
                andIsScanIsNotNull();
        return lectureScanRegistMapper.selectByExample(example);
    }

    @Override
    public Map<String, String> addCaseImage(ImageFileForm form) {
        Map<String, String> imgUrlMap = new HashMap<>();
        String cfg = getCfgCode("upload_base_dir");
        String dateString = DateUtil.getCurrDate2();
        String newDir = cfg + File.separator + "caseImage" + File.separator + dateString;
        String fileName = form.getFileName();
        String preffix = PkUtil.getUUID();//fileName.substring(0,fileName.lastIndexOf("."));
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        fileName = preffix + suffix;
        File fileDir = new File(newDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
            String imgDataString = StringUtil.defaultIfEmpty(form.getUploadFileData(), "");
//			String imgDataString = StringUtil.defaultIfEmpty(form.getImageContent(),"");
            byte[] data = null;
            if (StringUtil.isNotBlank(imgDataString)) {
                BASE64Decoder decoder = new BASE64Decoder();
                // Base64解码
                data = decoder.decodeBuffer(imgDataString);
                for (int i = 0; i < data.length; ++i) {
                    if (data[i] < 0) {// 调整异常数据
                        data[i] += 256;
                    }
                }
            }
            if (data == null) {
                data = form.getUploadFile().getBytes();
            }
            String fileFlow = PkUtil.getUUID();
            File imageFile = new File(fileDir, fileName);
            if (form.getUploadFile() != null) {
                form.getUploadFile().transferTo(imageFile);
            } else {
                FileOutputStream fos = new FileOutputStream(imageFile);
                fos.write(data);
                fos.flush();
                fos.close();
            }

            FtpHelperUtil ftpHelperUtil = new FtpHelperUtil();
            String localFilePath = fileDir + File.separator + fileName;
            String ftpDir = "caseImage" + File.separator + dateString;
            String ftpFileName = fileName;
            ftpHelperUtil.uploadFile(localFilePath, ftpDir, ftpFileName);

            //处理缩略图...
            String thumbFileName = preffix + "_thumb" + suffix;
            File thumbFile = new File(fileDir, thumbFileName);
            FileOutputStream fos = new FileOutputStream(thumbFile);
            //调用PicZoom类的静态方法zoom对原始图像进行缩放。
            BufferedImage buffImg = PicZoom.zoom(data);
            //创建JPEG图像编码器，用于编码内存中的图像数据到JPEG数据输出流。
            JPEGImageEncoder jpgEncoder = JPEGCodec.createJPEGEncoder(fos);
            //编码BufferedImage对象到JPEG数据输出流。
            jpgEncoder.encode(buffImg);
            fos.flush();
            fos.close();

            localFilePath = fileDir + File.separator + thumbFileName;
            ftpDir = "caseImage" + File.separator + dateString;
            ftpFileName = thumbFileName;
            ftpHelperUtil.uploadFile(localFilePath, ftpDir, ftpFileName);

            String urlCfg = getCfgCode("upload_base_url");
            imgUrlMap.put("imageUrl", urlCfg + "/caseImage/" + dateString + "/" + fileName);
            imgUrlMap.put("thumbUrl", urlCfg + "/caseImage/" + dateString + "/" + thumbFileName);

            imgUrlMap.put("imageFlow", fileFlow);
            return imgUrlMap;

        } catch (FileNotFoundException e) {
            logger.error("", e);
            return null;
        } catch (IOException e) {
            logger.error("", e);
            return null;
        }
    }

    @Override
    public Map<String, String> addCaseImage2(ImageFileForm form) {
        Map<String, String> imgUrlMap = new HashMap<>();
        String cfg = getCfgCode("upload_base_dir");
        String dateString = DateUtil.getCurrDate2();
        String newDir = cfg + File.separator + "caseImage" + File.separator + dateString;
        String fileName = form.getFileName();
        String preffix = PkUtil.getUUID();//fileName.substring(0,fileName.lastIndexOf("."));
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        fileName = preffix + suffix;
        File fileDir = new File(newDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
//			String imgDataString = StringUtil.defaultIfEmpty(form.getUploadFileData(),"");
            String imgDataString = StringUtil.defaultIfEmpty(form.getImageContent(), "");
            byte[] data = null;
            if (StringUtil.isNotBlank(imgDataString)) {
                BASE64Decoder decoder = new BASE64Decoder();
                // Base64解码
                data = decoder.decodeBuffer(imgDataString);
                for (int i = 0; i < data.length; ++i) {
                    if (data[i] < 0) {// 调整异常数据
                        data[i] += 256;
                    }
                }
            }
            if (data == null) {
                data = form.getUploadFile().getBytes();
            }
            String fileFlow = PkUtil.getUUID();
            File imageFile = new File(fileDir, fileName);
            if (form.getUploadFile() != null) {
                form.getUploadFile().transferTo(imageFile);
            } else {
                FileOutputStream fos = new FileOutputStream(imageFile);
                fos.write(data);
                fos.flush();
                fos.close();
            }

            FtpHelperUtil ftpHelperUtil = new FtpHelperUtil();
            String localFilePath = fileDir + File.separator + fileName;
            String ftpDir = "caseImage" + File.separator + dateString;
            String ftpFileName = fileName;
            ftpHelperUtil.uploadFile(localFilePath, ftpDir, ftpFileName);

            //处理缩略图...
            String thumbFileName = preffix + "_thumb" + suffix;
            File thumbFile = new File(fileDir, thumbFileName);
            FileOutputStream fos = new FileOutputStream(thumbFile);
            //调用PicZoom类的静态方法zoom对原始图像进行缩放。
            BufferedImage buffImg = PicZoom.zoom(data);
            //创建JPEG图像编码器，用于编码内存中的图像数据到JPEG数据输出流。
            JPEGImageEncoder jpgEncoder = JPEGCodec.createJPEGEncoder(fos);
            //编码BufferedImage对象到JPEG数据输出流。
            jpgEncoder.encode(buffImg);
            fos.flush();
            fos.close();

            localFilePath = fileDir + File.separator + thumbFileName;
            ftpDir = "caseImage" + File.separator + dateString;
            ftpFileName = thumbFileName;
            ftpHelperUtil.uploadFile(localFilePath, ftpDir, ftpFileName);

            String urlCfg = getCfgCode("upload_base_url");
            imgUrlMap.put("imageUrl", urlCfg + "/caseImage/" + dateString + "/" + fileName);
            imgUrlMap.put("thumbUrl", urlCfg + "/caseImage/" + dateString + "/" + thumbFileName);

            imgUrlMap.put("imageFlow", fileFlow);
            return imgUrlMap;

        } catch (FileNotFoundException e) {
            logger.error("", e);
            return null;
        } catch (IOException e) {
            logger.error("", e);
            return null;
        }
    }

    @Override
    public JsresAttendance getJsresAttendanceByFlow(String attendanceFlow) {
        if (StringUtil.isNotBlank(attendanceFlow)) {
            return jsresAttendanceMapper.selectByPrimaryKey(attendanceFlow);
        }
        return null;
    }

    @Override
    public int editJsresAttendance(JsresAttendance attendance) {
        if (StringUtil.isNotBlank(attendance.getAttendanceFlow()))
            return jsresAttendanceMapper.updateByPrimaryKeySelective(attendance);
        else
            return 0;
    }

    @Override
    public void saveGrade(String userFlow, String deptFlow, String subDeptFlow, String assessType, HttpServletRequest request) {
        String recTypeId = "";
        String recTypeName = "";
        if ("TeacherAssess".equals(assessType)) {
            recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
            recTypeName = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getName();
        } else if ("DeptAssess".equals(assessType)) {
            recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
            recTypeName = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getName();
        }

        SchArrangeResult result = resultMapper.selectByPrimaryKey(subDeptFlow);
        ResDoctorSchProcess process = getProcessByResultFlow(result.getResultFlow());
        ResRec rec = getGradeRec(userFlow, deptFlow, process.getProcessFlow(), recTypeId);

        ResAssessCfg assessCfg = getGradeTemplate(assessType);
        String content = "";
        if (assessCfg != null) {
            String gradeContent = assessCfg.getCfgBigValue();
            List<Map<String, Object>> assessMaps = parseAssessCfg(gradeContent);
            if (assessMaps != null && !assessMaps.isEmpty()) {
                content = getGradeXml(assessMaps, request);
            }
        }

        if (rec == null) {
            rec = new ResRec();
            rec.setRecFlow(PkUtil.getUUID());
            rec.setDeptFlow(result.getDeptFlow());
            rec.setDeptName(result.getDeptName());
            rec.setSchDeptFlow(result.getDeptFlow());
            rec.setSchDeptName(result.getDeptName());
            rec.setRecTypeId(recTypeId);
            rec.setRecTypeName(recTypeName);

            SysUser user = readSysUser(userFlow);
            String medicineTypeId = "";
            if (user != null) medicineTypeId = user.getMedicineTypeId();
            rec.setMedicineType(medicineTypeId);

            rec.setRecContent(content);
            ResDoctor doc = readResDoctor(userFlow);
            if (doc != null) {
                rec.setOrgFlow(doc.getOrgFlow());
                rec.setOrgName(doc.getOrgName());
                rec.setOperTime(DateUtil.getCurrDateTime());
                rec.setOperUserFlow(userFlow);
                rec.setOperUserName(doc.getDoctorName());
            }
            rec.setCreateTime(DateUtil.getCurrDateTime());
            rec.setCreateUserFlow(userFlow);
            rec.setModifyTime(DateUtil.getCurrDateTime());
            rec.setModifyUserFlow(userFlow);
            rec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            rec.setProcessFlow(process.getProcessFlow());
            rec.setSchRotationDeptFlow(deptFlow);
            recMapper.insertSelective(rec);
        } else {
            rec.setRecContent(content);
            recMapper.updateByPrimaryKeyWithBLOBs(rec);
        }


    }

    //根据request获取评分表单的xml
    private String getGradeXml(List<Map<String, Object>> assessMaps, HttpServletRequest request) {
        if (request != null) {
            String rootName = "gradeInfo";
            if (assessMaps != null && !assessMaps.isEmpty()) {
                //创建xmldom对象和根节点
                Document doc = DocumentHelper.createDocument();
                Element root = doc.addElement(rootName);

                //计算总分
                Float totalScore = 0f;
                //遍历配置获取用户填写的值
                for (Map<String, Object> map : assessMaps) {
                    if (map != null) {
                        //标题内的子项
                        List<Map<String, Object>> items = (List<Map<String, Object>>) map.get("items");
                        if (items != null && !items.isEmpty()) {
                            for (Map<String, Object> item : items) {
                                String itemId = (String) item.get("id");
                                if (StringUtil.isNotBlank(itemId)) {
                                    Element grade = root.addElement("grade");
                                    grade.addAttribute("assessId", itemId);
                                    Float maxScore = Float.valueOf((String) item.get("score"));
                                    //获取分数并统计
                                    String score = request.getParameter(itemId + "_score");
                                    Element scoreEle = grade.addElement("score");
                                    if (StringUtil.isNotBlank(score)) {
                                        Float scf = Float.valueOf(score);
                                        if (scf > maxScore) {
                                            scf = maxScore;
                                            int m = scf.intValue();
                                            score = String.valueOf(m);
                                        }
                                        scoreEle.setText(score);
                                        totalScore += scf;
                                    }

                                    String lostReason = request.getParameter(itemId + "_lostReason");
                                    Element lostReasonEle = grade.addElement("lostReason");
                                    if (StringUtil.isNotBlank(lostReason)) {
                                        lostReasonEle.setText(lostReason);
                                    }
                                }
                            }
                        }
                    }
                }

                Element totalScoreEle = root.addElement("totalScore");
                totalScoreEle.setText(totalScore.toString());

                return doc.asXML();
            }
        }
        return null;
    }

    //获取用户拥有的角色列表
    @Override
    public List<SysUserRole> getSysUserRole(String userFlow) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andUserFlowEqualTo(userFlow);
        return userRoleMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> getDocListByTeacher(
            Map<String, Object> paramMap) {
        return docExtMapper.getDocListByTeacher(paramMap);
    }

    @Override
    public void auditDate(String userFlow, String dataFlow) {
        ResRec rec = new ResRec();
        rec.setRecFlow(dataFlow);

        rec.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
        rec.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
        rec.setAuditUserFlow(userFlow);
        SysUser user = sysUserMapper.selectByPrimaryKey(userFlow);
        if (user != null) {
            rec.setAuditUserName(user.getUserName());
        }
        rec.setAuditTime(DateUtil.getCurrDateTime());

        recMapper.updateByPrimaryKeySelective(rec);
    }

    @Override
    public void saveSummary(String userFlow, String doctorFlow,
                            String subDeptFlow, Map<String, Object> paramMap) {
        ResDoctorSchProcess process = getProcessByResultFlow(subDeptFlow);

        SysUser user = sysUserMapper.selectByPrimaryKey(userFlow);
        ResDoctor doctor = doctorMapper.selectByPrimaryKey(doctorFlow);

        ResRecExample example = new ResRecExample();
        example.createCriteria().andRecTypeIdEqualTo(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOperUserFlowEqualTo(doctorFlow).andProcessFlowEqualTo(process.getProcessFlow());

        List<ResRec> recList = recMapper.selectByExample(example);
        String recContent = getSummaryXml(paramMap);
        ResRec rec = null;
        if (recList.size() > 0) {
            rec = recList.get(0);
            rec.setRecContent(recContent);
            rec.setModifyUserFlow(userFlow);
            rec.setModifyTime(DateUtil.getCurrDateTime());
            recMapper.updateByPrimaryKeyWithBLOBs(rec);
        } else {
            rec = new ResRec();
            rec.setRecFlow(PkUtil.getUUID());
            rec.setOrgFlow(doctor.getOrgFlow());
            rec.setOrgName(doctor.getOrgName());
            rec.setDeptFlow(process.getDeptFlow());
            rec.setDeptName(process.getDeptName());
            rec.setSchDeptFlow(process.getSchDeptFlow());
            rec.setSchDeptName(process.getSchDeptName());
            rec.setRecTypeId(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
            rec.setRecTypeName(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getName());
            //
            String medicineTypeId = "";
            if (user != null) medicineTypeId = user.getMedicineTypeId();
            rec.setMedicineType(medicineTypeId);
            rec.setRecContent(recContent);

            rec.setOperTime(DateUtil.getCurrDateTime());
            rec.setOperUserFlow(doctor.getDoctorFlow());
            rec.setOperUserName(doctor.getDoctorName());
            //
            rec.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
            rec.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());

            rec.setAuditUserFlow(userFlow);
            rec.setAuditUserName(user.getUserName());

            rec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            rec.setCreateUserFlow(doctorFlow);
            rec.setCreateTime(DateUtil.getCurrDateTime());
            rec.setModifyUserFlow(userFlow);
            rec.setModifyTime(DateUtil.getCurrDateTime());

            rec.setRecVersion("1.0");
            rec.setRecForm("jsst");
            rec.setProcessFlow(process.getProcessFlow());
            rec.setSchRotationDeptFlow("");
            recMapper.insertSelective(rec);
        }
    }

    private String getSummaryXml(Map<String, Object> paramMap) {
        Element rootEle = DocumentHelper.createElement(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
        for (Entry<String, Object> entry : paramMap.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            Element element = DocumentHelper.createElement(key);
            if ("zsgjflfg".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("优");
                } else if ("2".equals(value)) {
                    element.setText("良");
                } else if ("3".equals(value)) {
                    element.setText("中");
                } else if ("4".equals(value)) {
                    element.setText("差");
                }
            } else if ("lxgwzz".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("优");
                } else if ("2".equals(value)) {
                    element.setText("良");
                } else if ("3".equals(value)) {
                    element.setText("中");
                } else if ("4".equals(value)) {
                    element.setText("差");
                }
            } else if ("ybrwzx".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("优");
                } else if ("2".equals(value)) {
                    element.setText("良");
                } else if ("3".equals(value)) {
                    element.setText("中");
                } else if ("4".equals(value)) {
                    element.setText("差");
                }
            } else if ("rjgtbdnl".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("强");
                } else if ("2".equals(value)) {
                    element.setText("较强");
                } else if ("3".equals(value)) {
                    element.setText("一般");
                } else if ("4".equals(value)) {
                    element.setText("差");
                }
            } else if ("tjxzjsxm".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("强");
                } else if ("2".equals(value)) {
                    element.setText("较强");
                } else if ("3".equals(value)) {
                    element.setText("一般");
                } else if ("4".equals(value)) {
                    element.setText("差");
                }
            } else if ("jbllzwcd".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("强");
                } else if ("2".equals(value)) {
                    element.setText("较强");
                } else if ("3".equals(value)) {
                    element.setText("一般");
                } else if ("4".equals(value)) {
                    element.setText("差");
                }
            } else if ("njbjnzwcd".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("强");
                } else if ("2".equals(value)) {
                    element.setText("较强");
                } else if ("3".equals(value)) {
                    element.setText("一般");
                } else if ("4".equals(value)) {
                    element.setText("差");
                }
            } else if ("lcswnl".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("强");
                } else if ("2".equals(value)) {
                    element.setText("较强");
                } else if ("3".equals(value)) {
                    element.setText("一般");
                } else if ("4".equals(value)) {
                    element.setText("差");
                }
            } else if ("lczlnl".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("强");
                } else if ("2".equals(value)) {
                    element.setText("较强");
                } else if ("3".equals(value)) {
                    element.setText("一般");
                } else if ("4".equals(value)) {
                    element.setText("差");
                }
            } else if ("jjclnl".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("强");
                } else if ("2".equals(value)) {
                    element.setText("较强");
                } else if ("3".equals(value)) {
                    element.setText("一般");
                } else if ("4".equals(value)) {
                    element.setText("差");
                }
            } else if ("szkskhxzztpj".equals(entry.getKey())) {
                element.addAttribute("id", value);
                if ("1".equals(value)) {
                    element.setText("通过");
                } else if ("0".equals(value)) {
                    element.setText("不通过");
                }
            } else {
                element.setText(value);
            }
            rootEle.add(element);
        }
        return rootEle.asXML();
    }

    @Override
    public boolean getRecruitStatus(String doctorFlow) {
        if (StringUtil.isBlank(doctorFlow)) {
            return false;
        }

        ResDoctorRecruitExample recruitExample = new ResDoctorRecruitExample();
        recruitExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
                .andDoctorFlowEqualTo(doctorFlow);
        recruitExample.setOrderByClause("CREATE_TIME DESC");

        List<com.pinde.core.model.ResDoctorRecruit> recruits = recruitMapper.selectByExample(recruitExample);
        if (recruits != null && recruits.size() > 0) {
            return BaseStatusEnum.Passed.getId().equals(recruits.get(0).getAuditStatusId());
        }
        return false;
    }

    @Override
    public ResDoctorRecruit getNewRecruit(String doctorFlow) {
        if (!StringUtil.isNotBlank(doctorFlow)) {
            return null;
        }

        ResDoctorRecruitExample recruitExample = new ResDoctorRecruitExample();
        recruitExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
                .andDoctorFlowEqualTo(doctorFlow).andAuditStatusIdEqualTo("Passed");
        recruitExample.setOrderByClause("CREATE_TIME DESC");

        List<com.pinde.core.model.ResDoctorRecruit> recruits = recruitMapper.selectByExample(recruitExample);
        if (recruits == null || recruits.isEmpty()) {
            return null;
        }

        com.pinde.core.model.ResDoctorRecruit recruit = recruits.get(0);
        return recruit;

    }

    @Override
    public ResLectureRandomSign readLectureRandomSign(String randomFlow) {
        return lectureRandomSignMapper.selectByPrimaryKey(randomFlow);
    }

    @Override
    public ResLectureRandomScan readLectureRandomScan(String userFlow, String randomFlow) {
        ResLectureRandomScanExample example = new ResLectureRandomScanExample();
        example.createCriteria().andOperUserFlowEqualTo(userFlow).andRandomFlowEqualTo(randomFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResLectureRandomScan> lectureRandomScans = lectureRandomScanMapper.selectByExample(example);
        if (lectureRandomScans != null && lectureRandomScans.size() > 0) {
            return lectureRandomScans.get(0);
        }
        return null;
    }

    @Override
    public int saveLectureRandomScan(ResLectureRandomScan scan) {
        if (scan != null) {
            if (StringUtil.isNotBlank(scan.getRecordFlow())) {
                scan.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                scan.setModifyUserFlow(scan.getOperUserFlow());
                scan.setModifyTime(DateUtil.getCurrDateTime());
                return lectureRandomScanMapper.updateByPrimaryKeySelective(scan);
            } else {
                scan.setRecordFlow(PkUtil.getUUID());
                scan.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                scan.setCreateUserFlow(scan.getOperUserFlow());
                scan.setCreateTime(DateUtil.getCurrDateTime());
                scan.setModifyUserFlow(scan.getOperUserFlow());
                scan.setModifyTime(DateUtil.getCurrDateTime());
                return lectureRandomScanMapper.insertSelective(scan);
            }
        }
        return 0;
    }

    @Override
    public List<ResLectureInfo> SearchNewLectures(String orgFlow, String roleId, String roleFlow) {
        List<ResLectureInfo> lectureInfos = lectureInfoExtMapper.searchLecturesList(orgFlow, roleId, roleFlow);

        return lectureInfos;
    }

    @Override
    public ResLectureScanRegist searchByUserFlowAndLectureFlow(String userFlow, String lectureFlow) {
        ResLectureScanRegistExample example = new ResLectureScanRegistExample();
        ResLectureScanRegistExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).
                andLectureFlowEqualTo(lectureFlow).andOperUserFlowEqualTo(userFlow);
        List<ResLectureScanRegist> lectureScanRegists = lectureScanRegistMapper.selectByExample(example);
        if (lectureScanRegists != null && lectureScanRegists.size() > 0) {
            return lectureScanRegists.get(0);
        } else {
            return null;
        }

    }

    @Override
    public List<ResLectureScanRegist> searchByUserFLowAndRegist(String userFlow) {
        ResLectureScanRegistExample example = new ResLectureScanRegistExample();
        ResLectureScanRegistExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).
                andOperUserFlowEqualTo(userFlow);
        example.setOrderByClause("CREATE_TIME");
        return lectureScanRegistMapper.selectByExample(example);
    }


    @Override
    public ResLectureInfo read(String lectureFlow) {
        ResLectureInfo lectureInfo = null;
        if (StringUtil.isNotBlank(lectureFlow)) {
            lectureInfo = lectureInfoMapper.selectByPrimaryKey(lectureFlow);
        }
        return lectureInfo;
    }

    @Override
    public List<ResLectureEvaDetail> searchByUserFlowLectureFlow(String userFlow, String lectureFlow) {
        ResLectureEvaDetailExample example = new ResLectureEvaDetailExample();
        if (StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(lectureFlow)) {
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andLectureFlowEqualTo(lectureFlow)
                    .andOperUserFlowEqualTo(userFlow);
        }
        return lectureEvaDetailMapper.selectByExample(example);
    }

    @Override
    public synchronized int editLectureScanRegist(String lectureFlow, SysUser currUser, ResLectureScanRegist regist, ResDoctor doctor) {
        int count = lectureInfoExtMapper.checkRegistNum(lectureFlow);
        if (count <= 0) {

            if (count == 0)
                count = -1;
            return count;
        }
        String userFlow = currUser.getUserFlow();
        ResLectureScanRegist lectureScanRegist = null;
        if (regist == null) {
            lectureScanRegist = new ResLectureScanRegist();
            lectureScanRegist.setRecordFlow(PkUtil.getUUID());
            lectureScanRegist.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            lectureScanRegist.setCreateUserFlow(userFlow);
            lectureScanRegist.setCreateTime(DateUtil.getCurrDateTime());
            lectureScanRegist.setModifyUserFlow(userFlow);
            lectureScanRegist.setModifyTime(DateUtil.getCurrDateTime());
            lectureScanRegist.setLectureFlow(lectureFlow);
            lectureScanRegist.setOperUserFlow(userFlow);
            if (doctor != null) {
                String session = doctor.getSessionNumber();
                String trainingTypeId = doctor.getTrainingTypeId();
                String trainingTypeName = doctor.getTrainingTypeName();
                String trainingSpeId = doctor.getTrainingSpeId();
                String trainingSpeName = doctor.getTrainingSpeName();
                if (StringUtil.isNotBlank(session)) {
                    lectureScanRegist.setSessionNumber(session);
                }
                if (StringUtil.isNotBlank(trainingTypeId)) {
                    lectureScanRegist.setTrainingTypeId(trainingTypeId);
                }
                if (StringUtil.isNotBlank(trainingTypeName)) {
                    lectureScanRegist.setTrainingTypeName(trainingTypeName);
                }
                if (StringUtil.isNotBlank(trainingSpeId)) {
                    lectureScanRegist.setTrainingSpeId(trainingSpeId);
                }
                if (StringUtil.isNotBlank(trainingSpeName)) {
                    lectureScanRegist.setTrainingSpeName(trainingSpeName);
                }
            }

            if (StringUtil.isNotBlank(currUser.getUserName())) {
                lectureScanRegist.setOperUserName(currUser.getUserName());
            }
            lectureScanRegist.setIsRegist(com.pinde.core.common.GlobalConstant.FLAG_Y);
            return lectureScanRegistMapper.insertSelective(lectureScanRegist);
        } else {
            lectureScanRegist = regist;
            lectureScanRegist.setIsRegist(com.pinde.core.common.GlobalConstant.FLAG_Y);
            return lectureScanRegistMapper.updateByPrimaryKey(lectureScanRegist);
        }

    }

    @Override
    public int editResLectureEvaDetail(ResLectureEvaDetail resLectureEvaDetail, String userFlow) {
        if (resLectureEvaDetail != null) {
            if (StringUtil.isNotBlank(resLectureEvaDetail.getRecordFlow())) {
                resLectureEvaDetail.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                resLectureEvaDetail.setModifyUserFlow(userFlow);
                resLectureEvaDetail.setModifyTime(DateUtil.getCurrDateTime());
                return lectureEvaDetailMapper.updateByPrimaryKeySelective(resLectureEvaDetail);
            } else {
                resLectureEvaDetail.setRecordFlow(PkUtil.getUUID());
                resLectureEvaDetail.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                resLectureEvaDetail.setCreateUserFlow(userFlow);
                resLectureEvaDetail.setCreateTime(DateUtil.getCurrDateTime());
                resLectureEvaDetail.setModifyUserFlow(userFlow);
                resLectureEvaDetail.setModifyTime(DateUtil.getCurrDateTime());
                return lectureEvaDetailMapper.insertSelective(resLectureEvaDetail);
            }
        }
        return 0;
    }

    @Override
    public int scanRegist(ResLectureScanRegist regist) {
        if (regist != null) {
            if (StringUtil.isNotBlank(regist.getRecordFlow())) {
                regist.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                regist.setModifyUserFlow(regist.getOperUserFlow());
                regist.setModifyTime(DateUtil.getCurrDateTime());
                return lectureScanRegistMapper.updateByPrimaryKeySelective(regist);
            } else {
                regist.setRecordFlow(PkUtil.getUUID());
                regist.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                regist.setCreateUserFlow(regist.getOperUserFlow());
                regist.setCreateTime(DateUtil.getCurrDateTime());
                regist.setModifyUserFlow(regist.getOperUserFlow());
                regist.setModifyTime(DateUtil.getCurrDateTime());
                return lectureScanRegistMapper.insertSelective(regist);
            }
        }
        return 0;
    }

    @Override
    public int editResRec(ResRec rec, SysUser user, String recTypeId) {
        if (rec != null) {
            if (StringUtil.isNotBlank(rec.getRecFlow())) {//修改
                rec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                rec.setModifyUserFlow(user.getUserFlow());
                rec.setModifyTime(DateUtil.getCurrDateTime());

//				if (recTypeId.equals(RegistryTypeEnum.CampaignRegistry.getId())) {
//					ResRecCampaignRegistry campaignRegistry = new ResRecCampaignRegistry();
//					BeanUtils.copyProperties(rec, campaignRegistry);
//					return campaignRegistryMapper.updateByPrimaryKeySelective(campaignRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.CaseRegistry.getId())) {
//					ResRecCaseRegistry caseRegistry = new ResRecCaseRegistry();
//					BeanUtils.copyProperties(rec, caseRegistry);
//					return caseRegistryMapper.updateByPrimaryKeySelective(caseRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.DiseaseRegistry.getId())) {
//					ResRecDiseaseRegistry diseaseRegistry = new ResRecDiseaseRegistry();
//					BeanUtils.copyProperties(rec, diseaseRegistry);
//					return diseaseRegistryMapper.updateByPrimaryKeySelective(diseaseRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.LanguageTeachingResearch.getId())) {
//					ResRecLanguageRegistry languageRegistry = new ResRecLanguageRegistry();
//					BeanUtils.copyProperties(rec, languageRegistry);
//					return languageRegistryMapper.updateByPrimaryKeySelective(languageRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.OperationRegistry.getId())) {
//					ResRecOperationRegistry operationRegistry = new ResRecOperationRegistry();
//					BeanUtils.copyProperties(rec, operationRegistry);
//					return operationRegistryMapper.updateByPrimaryKeySelective(operationRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.SkillRegistry.getId())) {
//					ResRecSkillRegistry skillRegistry = new ResRecSkillRegistry();
//					BeanUtils.copyProperties(rec, skillRegistry);
//					return skillRegistryMapper.updateByPrimaryKeySelective(skillRegistry);
//				}

                return this.recMapper.updateByPrimaryKeySelective(rec);
            } else {//新增
                rec.setRecFlow(PkUtil.getUUID());
                if (!com.pinde.core.common.enums.ResRecTypeEnum.AnnualTrainForm.getId().equals(rec.getRecTypeId())) {//培训年度
                    rec.setOperTime(DateUtil.getCurrDateTime());
                }
                rec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                rec.setCreateUserFlow(user.getUserFlow());
                rec.setCreateTime(DateUtil.getCurrDateTime());
                rec.setModifyUserFlow(user.getUserFlow());
                rec.setModifyTime(DateUtil.getCurrDateTime());

//				if (recTypeId.equals(RegistryTypeEnum.CampaignRegistry.getId())) {
//					ResRecCampaignRegistry campaignRegistry = new ResRecCampaignRegistry();
//					BeanUtils.copyProperties(rec, campaignRegistry);
//					return campaignRegistryMapper.insertSelective(campaignRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.CaseRegistry.getId())) {
//					ResRecCaseRegistry caseRegistry = new ResRecCaseRegistry();
//					BeanUtils.copyProperties(rec, caseRegistry);
//					return caseRegistryMapper.insertSelective(caseRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.DiseaseRegistry.getId())) {
//					ResRecDiseaseRegistry diseaseRegistry = new ResRecDiseaseRegistry();
//					BeanUtils.copyProperties(rec, diseaseRegistry);
//					return diseaseRegistryMapper.insertSelective(diseaseRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.LanguageTeachingResearch.getId())) {
//					ResRecLanguageRegistry languageRegistry = new ResRecLanguageRegistry();
//					BeanUtils.copyProperties(rec, languageRegistry);
//					return languageRegistryMapper.insertSelective(languageRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.OperationRegistry.getId())) {
//					ResRecOperationRegistry operationRegistry = new ResRecOperationRegistry();
//					BeanUtils.copyProperties(rec, operationRegistry);
//					return operationRegistryMapper.insertSelective(operationRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.SkillRegistry.getId())) {
//					ResRecSkillRegistry skillRegistry = new ResRecSkillRegistry();
//					BeanUtils.copyProperties(rec, skillRegistry);
//					return skillRegistryMapper.insertSelective(skillRegistry);
//				}

                return this.recMapper.insertSelective(rec);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public int editResRec(ResRec rec, SysUser user) {
        if (rec != null) {
            if (StringUtil.isNotBlank(rec.getRecFlow())) {//修改
                rec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                rec.setModifyUserFlow(user.getUserFlow());
                rec.setModifyTime(DateUtil.getCurrDateTime());
                return this.recMapper.updateByPrimaryKeySelective(rec);
            } else {//新增
                rec.setRecFlow(PkUtil.getUUID());
                if (!com.pinde.core.common.enums.ResRecTypeEnum.AnnualTrainForm.getId().equals(rec.getRecTypeId())) {//培训年度
                    rec.setOperTime(DateUtil.getCurrDateTime());
                }
                rec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                rec.setCreateUserFlow(user.getUserFlow());
                rec.setCreateTime(DateUtil.getCurrDateTime());
                rec.setModifyUserFlow(user.getUserFlow());
                rec.setModifyTime(DateUtil.getCurrDateTime());
                return this.recMapper.insertSelective(rec);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }
    /**
     * 百分比及各数据统计计算START
     */

    /**
     * 进一步包装数据,以便调用计算方法
     *
     * @param doctorFlow  医师流水号
     * @param processFlow 计划或方案流水号
     * @param recTypeId   登记类型
     * @param itemId      子项id
     * @return
     */
    @Override
    public Map<String, Object> getRecProgressIn(
            String doctorFlow,
            String processFlow,
            String recTypeId,
            String itemId
    ) {

        if (!StringUtil.isNotBlank(doctorFlow)) {
            return null;
        }

        //读取医师信息以获取方案
        ResDoctor doctor = readResDoctor(doctorFlow);
        if (doctor == null) {
            return null;
        }
        SysUser user = readSysUser(doctorFlow);
        if (user == null) {
            return null;
        }

        //获取方案
        String rotationFlow = doctor.getRotationFlow();
        if (!StringUtil.isNotBlank(rotationFlow)) {
            return null;
        }

        //获取该医师的计划
        List<SchArrangeResultExt> resultExts = getResults(doctorFlow, null, processFlow);
        if (resultExts == null || resultExts.isEmpty()) {
            return null;
        }

        //获取该方案的标准科室
        List<SchRotationDept> depts = null;
        String schRotationDeptFlow = null;

        //process为空的情况下不支持更精确的统计
        if (!StringUtil.isNotBlank(processFlow)) {
            recTypeId = null;
            itemId = null;

            depts = getDept(null, rotationFlow, null);
        } else {
            SchArrangeResultExt resultExt = resultExts.get(0);
            if (resultExt != null) {
                String standardGroupFlow = resultExt.getStandardGroupFlow();
                String standardDeptId = resultExt.getStandardDeptId();

                SchRotationDept dept = searchGroupFlowAndStandardDeptIdQuery(standardGroupFlow, standardDeptId);
                if (dept != null) {
                    depts = new ArrayList<SchRotationDept>();
                    depts.add(dept);

                    schRotationDeptFlow = dept.getRecordFlow();
                }
            }
        }

        if (depts == null) {
            return null;
        }

        //获取所有登记数据类型
        List<String> recTypeIds = new ArrayList<String>();
        if (StringUtil.isNotBlank(recTypeId)) {
            recTypeIds.add(recTypeId);
        } else {
            for (RegistryTypeEnum regType : RegistryTypeEnum.values()) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(GlobalUtil.getSysCfg("res_registry_type_" + regType.getId()))) {
                    if (GlobalUtil.findChineseOrWestern(user.getMedicineTypeId(), regType.getId())) {
                        recTypeIds.add(regType.getId());
                    }
                }
            }
        }
        //统计登记的数据
        List<ResRec> recs = getRecs(doctorFlow, processFlow, recTypeIds, itemId);
        Map<String, Float> recCountMap = getRecCount(recs);

        //选科表数据,西医作为缩减调整
        List<SchDoctorDept> selDepts = getSelDepts(doctorFlow, rotationFlow);

        //要求统计,统计的要求为缩减后的要求,同时拿出子项列表
        List<SchRotationDeptReq> reqs = getReqs(rotationFlow, schRotationDeptFlow, recTypeIds, itemId);
        Map<String, List<String>> itemsMap = new HashMap<String, List<String>>();
        Map<String, Float> reqCountMap = getReqCount(depts, reqs, selDepts, itemsMap);


        return getRecProgress(true, 0, resultExts, recCountMap, reqCountMap, recTypeIds, itemsMap);
    }

    @Override
    public SchRotationDept searchGroupFlowAndStandardDeptIdQuery(
            String groupFlow, String standardDeptId) {
        SchRotationDeptExample example = new SchRotationDeptExample();
        SchRotationDeptExample.Criteria criter = example.createCriteria();
        criter.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(groupFlow)) {
            criter.andGroupFlowEqualTo(groupFlow);
        }
        if (StringUtil.isNotBlank(standardDeptId)) {
            criter.andStandardDeptIdEqualTo(standardDeptId);
        }
        List<SchRotationDept> rotationDeptList = schRotationDeptMapper.selectByExample(example);
        SchRotationDept rotationDept = null;
        if (rotationDeptList != null && rotationDeptList.size() > 0) {
            rotationDept = rotationDeptList.get(0);
        }
        return rotationDept;
    }

    @Override
    public List<JsresAttendanceDetail> getAttendanceDetailList(String nowDay, String userFlow) {
        JsresAttendanceDetailExample example = new JsresAttendanceDetailExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andAttendDateEqualTo(nowDay).andDoctorFlowEqualTo(userFlow);
        example.setOrderByClause("ATTEND_TIME DESC,CREATE_TIME DESC");
        return jsresAttendanceDetailMapper.selectByExample(example);
    }

    @Override
    public JsresAttendance getJsresAttendance(String nowDay, String userFlow) {
        JsresAttendanceExample example = new JsresAttendanceExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andAttendDateEqualTo(nowDay).andDoctorFlowEqualTo(userFlow);
        example.setOrderByClause("create_time desc");
        JsresAttendance bean = null;
        List<JsresAttendance> list = jsresAttendanceMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            bean = list.get(0);
        }
        return bean;
    }

    @Override
    public int addJsresAttendance(JsresAttendance attendance) {
        return jsresAttendanceMapper.insertSelective(attendance);
    }

    @Override
    public int addJsresAttendanceDetail(JsresAttendanceDetail detail) {
        return jsresAttendanceDetailMapper.insertSelective(detail);
    }

    @Override
    public List<Map<String, String>> getHistoryLecture(String userFlow) {
        return lectureInfoExtMapper.getHistoryLecture(userFlow);
    }

    @Override
    public SysOrg getOrg(String orgFlow) {
        return orgMapper.selectByPrimaryKey(orgFlow);
    }

    @Override
    public SysOrg getOrgTwo(String orgFlow) {
        return orgExtMapper.selectByPrimaryKey(orgFlow);
    }

    @Override
    public Map<String, String> getPowerMap(String docFlow) {
        return docExtMapper.getPowerMap(docFlow);
    }

    @Override
    public boolean getCkkPower(String doctorFlow) {
        if (StringUtil.isNotBlank(doctorFlow)) {
            boolean f = false;
            //学员相应的出科考核权限
//			Map<String, String> powerMap = getPowerMap(doctorFlow);
//			ResDoctor doctor=readResDoctor(doctorFlow);
//			if (powerMap != null) {
//				if ("Graduate".equals(doctor.getDoctorTypeId()))//在校专硕
//				{
//					//派送学校过程开通及派送学校出科考开通
//					if (StringUtil.isNotBlank(doctor.getWorkOrgId())) {
//						String isSendFlag = powerMap.get("STU_SEND_SCHOOL_GC");
//						String SendCkk = powerMap.get("STU_SEND_SCHOOL_CKK");
//						if (!com.pinde.core.common.GlobalConstant.FLAG_N.equals(isSendFlag) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(SendCkk)) {
//							f = true;
//						}
//					}
//					//培训基地过程开通，培训基地在校专硕过程开通，培训基地专硕出科考开通
//					if (!f) {
//						String isJDGC = powerMap.get("STU_JD_GC");
//						String isJDZSGC = powerMap.get("STU_JDZS_GC");
//						String isJDZSCKK = powerMap.get("STU_JDZS_CKK");
//						if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isJDGC) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isJDZSGC) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isJDZSCKK)) {
//							f = true;
//						}
//					}
//				} else {
//					//非在校专硕 需要基地开通过程并且出科考也开通
//					String stuJdGc = powerMap.get("STU_JD_GC");
//					String stuJdCkk = powerMap.get("STU_JD_CKK");
//					if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(stuJdGc) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(stuJdCkk)) {
//						f = true;
//					}
//				}
//			}
            ResDoctor doctor = readResDoctor(doctorFlow);
            String ckk = getJsResCfgCode("jsres_doctor_exam_" + doctorFlow);
            if (StringUtil.isNotBlank(ckk) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ckk) && "Passed".equals(doctor.getCheckStatusId())) {
                f = true;
            }
            return f;
        }
        return false;
    }

    @Override
    public boolean getPxscPower(String doctorFlow) {
        if (StringUtil.isNotBlank(doctorFlow)) {
            boolean f = false;
            ResDoctor doctor = readResDoctor(doctorFlow);
            String ckk = getJsResCfgCode("jsres_doctor_manual_" + doctorFlow);
            if (StringUtil.isNotBlank(ckk) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ckk) && "Passed".equals(doctor.getCheckStatusId())) {
                f = true;
            }
            return f;
        }
        return false;
    }

    @Override
    public int saveScore(ResScore score, SysUser user) {
        if (score != null) {
            if (StringUtil.isNotBlank(score.getScoreFlow())) {//修改
                score.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                score.setModifyUserFlow(user.getUserFlow());
                score.setModifyTime(DateUtil.getCurrDateTime());
                return this.scoreMapper.updateByPrimaryKeySelective(score);
            } else {//新增
                score.setScoreFlow(PkUtil.getUUID());
                score.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                score.setCreateUserFlow(user.getUserFlow());
                score.setCreateTime(DateUtil.getCurrDateTime());
                score.setModifyUserFlow(user.getUserFlow());
                score.setModifyTime(DateUtil.getCurrDateTime());
                return this.scoreMapper.insertSelective(score);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public int getDoctorAuth(String userFlow) {
        if (StringUtil.isNotBlank(userFlow)) {
            DoctorAuthExample example = new DoctorAuthExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andOperUserFlowEqualTo(userFlow);
            return doctorAuthMapper.countByExample(example);
        }
        return 0;
    }

    public Map<String, Object> getRecProgress(
            boolean haveNoReqFull,
            int format,
            List<SchArrangeResultExt> resultExts,
            Map<String, Float> recCountMap,
            Map<String, Float> reqCountMap,
            List<String> recTypeIds,
            Map<String, List<String>> itemIdsMap
    ) {
        //格式化参数不可小于0
        format = format < 0 ? 0 : format;

        if (resultExts == null || resultExts.isEmpty()) {
            return null;
        }

        //为各科室计算比例
        Map<String, Object> resultMap = new HashMap<String, Object>();
        for (SchArrangeResultExt sarExt : resultExts) {//可变列表,可由process/result/rotationDept构成
            ResDoctorSchProcess process = sarExt.getProcess();
            if (process == null) {
                continue;
            }

            String resultFlow = sarExt.getResultFlow();
            String processFlow = process.getProcessFlow();

            String standardGroupFlow = sarExt.getStandardGroupFlow();
            String standardDeptId = sarExt.getStandardDeptId();

            //科室总完成数
            Float deptFin = getFdefaultZero(recCountMap, processFlow);
            setVal4PerMap(resultMap, processFlow + FINISHED, deptFin, 0);
            setVal4PerMap(resultMap, resultFlow + FINISHED, deptFin, 0);

            //科室总要求数
            Float deptReq = getFdefaultZero(reqCountMap, standardGroupFlow + standardDeptId);
            setVal4PerMap(resultMap, processFlow + REQ_NUM, deptReq, 0);
            setVal4PerMap(resultMap, resultFlow + REQ_NUM, deptReq, 0);

            //如果没有需要计算的登记类型直接忽略后面计算
            if (recTypeIds == null || recTypeIds.isEmpty()) {
                continue;
            }

            //科室总比例
            Float deptPer = 0f;

            for (String recTypeId : recTypeIds) {//getPerResult
                //类型总比例
                Float typePer = 0f;

                //类型完成数
                Float typeFin = getFdefaultZero(recCountMap, processFlow + recTypeId);
                setVal4PerMap(resultMap, processFlow + recTypeId + FINISHED, typeFin, 0);
                setVal4PerMap(resultMap, resultFlow + recTypeId + FINISHED, typeFin, 0);

                //类型要求
                Float typeReq = getFdefaultZero(reqCountMap, standardGroupFlow + standardDeptId + recTypeId);
                setVal4PerMap(resultMap, processFlow + recTypeId + REQ_NUM, typeReq, 0);
                setVal4PerMap(resultMap, resultFlow + recTypeId + REQ_NUM, typeReq, 0);
                //类型要求占总要求比例
                Float typeReqPer = getPerResult(typeReq, deptReq);

                //该类型的子项列表
                List<String> itemIds = null;
                if (itemIdsMap != null) {
                    itemIds = itemIdsMap.get(standardGroupFlow + standardDeptId + recTypeId);
                }
                //如果没有子项列表则按无子项方法计算
                if (itemIds != null) {
                    for (String itemId : itemIds) {
                        //子项完成数
                        Float itemFin = getFdefaultZero(recCountMap, processFlow + recTypeId + itemId);
                        setVal4PerMap(resultMap, processFlow + recTypeId + itemId + FINISHED, itemFin, 0);
                        setVal4PerMap(resultMap, resultFlow + recTypeId + itemId + FINISHED, itemFin, 0);

                        //子项要求数
                        Float itemReq = getFdefaultZero(reqCountMap, standardGroupFlow + standardDeptId + recTypeId + itemId);
                        setVal4PerMap(resultMap, processFlow + recTypeId + itemId + REQ_NUM, itemReq, 0);
                        setVal4PerMap(resultMap, resultFlow + recTypeId + itemId + REQ_NUM, itemReq, 0);

                        //子项要求占类型要求比例的比例
                        Float itemReqPer = getPerResult(itemReq, typeReq);

                        //子项比例
                        Float itemPer = getPerResult(itemFin, itemReq);
                        if (haveNoReqFull && itemReq == 0 && itemFin > 0) {
                            itemPer = 1f;
                        }
                        setVal4PerMap(resultMap, processFlow + recTypeId + itemId, itemPer * 100, format);
                        setVal4PerMap(resultMap, resultFlow + recTypeId + itemId, itemPer * 100, format);
                        typePer += (itemReqPer * itemPer);
                    }
                } else {
                    if (typeReq > 0) {
                        typePer = getPerResult(typeFin, typeReq);
                    }
                }

                if (haveNoReqFull && typeReq == 0 && typeFin > 0) {
                    typePer = 1f;
                }

                setVal4PerMap(resultMap, processFlow + recTypeId, typePer * 100, format);
                setVal4PerMap(resultMap, resultFlow + recTypeId, typePer * 100, format);

                deptPer += (typePer * typeReqPer);
            }

            if (haveNoReqFull && deptReq == 0 && deptFin > 0) {
                deptPer = 1f;
            }

            setVal4PerMap(resultMap, processFlow, deptPer * 100, format);
            setVal4PerMap(resultMap, resultFlow, deptPer * 100, format);
        }

        return resultMap;
    }
    /**
     * 百分比及各数据统计计算END
     */
    /*********************************************************
     * jsres&product百分比算法,兼容缩减END
     *************************************************************/
    /**
     * get方法实现
     *
     * @param prop 属性名
     * @param obj  对象
     * @return
     * @throws Exception
     */
    private <T> T getMethod(String prop, Object obj) throws Exception {
        Class clazz = obj.getClass();
        T value = null;
        String firstWord = prop.substring(0, 1);
        firstWord = firstWord.toUpperCase();
        String surplusWords = "";
        if (prop.length() > 1) {
            surplusWords = prop.substring(1);
        }
        String methodName = "get" + firstWord + surplusWords;
        Method method = clazz.getMethod(methodName);
        value = (T) method.invoke(obj);
        return value;
    }

    /**
     * 在本对象内获取key
     *
     * @param obj  该对象
     * @param keys 组合key的属性名
     * @return
     */
    private String groupTheKey(Object obj, String[] keys) {
        String key = "";
        for (String s : keys) {
            String val = "";
            try {
                val = getMethod(s, obj);
            } catch (Exception e) {
                logger.error("", e);
            }
            key += val;
        }
        return key;
    }

    /**
     * 以对象本身的属性作为key组织一个map 1-1/N
     *
     * @param isMany 是否是1-n
     * @param objs   数据列表
     * @param keys   以该对象的某个或多个属性作为key
     * @return
     */
    private <T, E> Map<String, T> transListObjToMapsSoM(boolean isMany, List<E> objs, String... keys) {
        if (objs != null && !objs.isEmpty() && keys != null && keys.length > 0) {
            Map<String, T> map = new HashMap<String, T>();
            for (E e : objs) {
                String key = groupTheKey(e, keys);
                if (isMany) {
                    List<E> es = (ArrayList<E>) map.get(key);
                    if (es == null) {
                        es = new ArrayList<E>();
                    }
                    es.add(e);
                    map.put(key, (T) es);
                } else {
                    map.put(key, (T) e);
                }
            }
            return map;
        }
        return null;
    }

    /**
     * 两数相除且不能大于1
     *
     * @param a
     * @param b
     * @return
     */
    private float getPerResult(float a, float b) {
        if (a <= 0) {
            return 0;
        }
        if (b <= 0) {
            return 0;
        }
        float result = a / b;
        if (result > 1) {
            result = 1;
        }
        return result;
    }

    /**
     * 为一个Map<String,Float> 类型的map赋值	同key算和
     *
     * @param numMap 被赋值的map
     * @param key    map的key
     * @param num    map的值
     */
    private void setVal4MapStrFloat(Map<String, Float> numMap, String key, Float num) {
        if (numMap != null) {
            Float v = numMap.get(key);
            if (v == null) {
                numMap.put(key, num);
            } else {
                numMap.put(key, v + num);
            }
        }
    }

    /**
     * 取出map内的数字默认0
     *
     * @param map 存放数据的map
     * @param key 获取数据的key
     * @return
     */
    private Float getFdefaultZero(Map<String, Float> map, String key) {
        Float result = 0f;
        if (map != null) {
            Float v = map.get(key);
            if (v != null) {
                result = v;
            }
        }
        return result;
    }

    /**
     * 把值格式化后塞进map
     *
     * @param perMap 被赋值的Map
     * @param key
     * @param val
     * @param format 小数点后位数
     */
    private void setVal4PerMap(Map<String, Object> perMap, String key, Float val, int format) {
        if (perMap != null) {
            BigDecimal b = new BigDecimal(val).setScale(format, BigDecimal.ROUND_HALF_UP);
            perMap.put(key, b.toString());
        }
    }

    /**
     * resRec数据
     *
     * @param userFlow    用户流水号
     * @param processFlow 轮转过程流水号
     * @param recTypeId   登记类型id
     * @param itemId      登记子项id
     * @return rec列表不含recContent字段
     */
    public List<ResRec> getRecs(String userFlow, String processFlow, List<String> recTypeId, String itemId) {
        int coditionCount = 0;

//		ResRecCampaignRegistryExample campaignRegistryExample = new ResRecCampaignRegistryExample();
//		ResRecCaseRegistryExample caseRegistryExample = new ResRecCaseRegistryExample();
//		ResRecDiseaseRegistryExample diseaseRegistryExample = new ResRecDiseaseRegistryExample();
//		ResRecLanguageRegistryExample languageRegistryExample = new ResRecLanguageRegistryExample();
//		ResRecOperationRegistryExample operationRegistryExample = new ResRecOperationRegistryExample();
//		ResRecSkillRegistryExample skillRegistryExample = new ResRecSkillRegistryExample();
//		ResRecCampaignRegistryExample.Criteria campaignRegistryCriteria = campaignRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//		ResRecCaseRegistryExample.Criteria caseRegistryCriteria = caseRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//		ResRecDiseaseRegistryExample.Criteria diseaseRegistryCriteria = diseaseRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//		ResRecLanguageRegistryExample.Criteria languageRegistryCriteria = languageRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//		ResRecOperationRegistryExample.Criteria operationRegistryCriteria = operationRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//		ResRecSkillRegistryExample.Criteria skillRegistryCriteria = skillRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

        ResRecExample recExample = new ResRecExample();
        ResRecExample.Criteria recCriteria = recExample.createCriteria()
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

        if (StringUtil.isNotBlank(userFlow)) {
            coditionCount++;
//			campaignRegistryCriteria.andOperUserFlowEqualTo(userFlow);
//			caseRegistryCriteria.andOperUserFlowEqualTo(userFlow);
//			diseaseRegistryCriteria.andOperUserFlowEqualTo(userFlow);
//			languageRegistryCriteria.andOperUserFlowEqualTo(userFlow);
//			operationRegistryCriteria.andOperUserFlowEqualTo(userFlow);
//			skillRegistryCriteria.andOperUserFlowEqualTo(userFlow);
            recCriteria.andOperUserFlowEqualTo(userFlow);
        }

        if (StringUtil.isNotBlank(processFlow)) {
            coditionCount++;
//			campaignRegistryCriteria.andProcessFlowEqualTo(processFlow);
//			caseRegistryCriteria.andProcessFlowEqualTo(processFlow);
//			diseaseRegistryCriteria.andProcessFlowEqualTo(processFlow);
//			languageRegistryCriteria.andProcessFlowEqualTo(processFlow);
//			operationRegistryCriteria.andProcessFlowEqualTo(processFlow);
//			skillRegistryCriteria.andProcessFlowEqualTo(processFlow);
            recCriteria.andProcessFlowEqualTo(processFlow);

            if (recTypeId != null && recTypeId.size() > 0) {
                coditionCount++;
//				campaignRegistryCriteria.andRecTypeIdIn(recTypeId);
//				caseRegistryCriteria.andRecTypeIdIn(recTypeId);
//				diseaseRegistryCriteria.andRecTypeIdIn(recTypeId);
//				languageRegistryCriteria.andRecTypeIdIn(recTypeId);
//				operationRegistryCriteria.andRecTypeIdIn(recTypeId);
//				skillRegistryCriteria.andRecTypeIdIn(recTypeId);
                recCriteria.andRecTypeIdIn(recTypeId);
            }

            if (StringUtil.isNotBlank(itemId)) {
                coditionCount++;
//				campaignRegistryCriteria.andItemIdEqualTo(itemId);
//				caseRegistryCriteria.andItemIdEqualTo(itemId);
//				diseaseRegistryCriteria.andItemIdEqualTo(itemId);
//				languageRegistryCriteria.andItemIdEqualTo(itemId);
//				operationRegistryCriteria.andItemIdEqualTo(itemId);
//				skillRegistryCriteria.andItemIdEqualTo(itemId);
                recCriteria.andItemIdEqualTo(itemId);
            }
        }

        if (coditionCount == 0) {
            return null;
        }

//		List<ResRecCampaignRegistry> campaignRegistryList = campaignRegistryMapper.selectByExample(campaignRegistryExample);
//		List<ResRec> resRecList = campaignRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList());
//		List<ResRecCaseRegistry> caseRegistryList = caseRegistryMapper.selectByExample(caseRegistryExample);
//		resRecList.addAll(caseRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecDiseaseRegistry> diseaseRegistryList = diseaseRegistryMapper.selectByExample(diseaseRegistryExample);
//		resRecList.addAll(diseaseRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecLanguageRegistry> languageRegistryList = languageRegistryMapper.selectByExample(languageRegistryExample);
//		resRecList.addAll(languageRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecOperationRegistry> operationRegistryList = operationRegistryMapper.selectByExample(operationRegistryExample);
//		resRecList.addAll(operationRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecSkillRegistry> skillRegistryList = skillRegistryMapper.selectByExample(skillRegistryExample);
//		resRecList.addAll(skillRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		return resRecList;

        return recMapper.selectByExample(recExample);
    }

    /**
     * 要求数据
     *
     * @param rotationFlow  轮转方案流水号
     * @param relRecordFlow 标准科室流水号
     * @param recTypeId     要求类型id
     * @param itemId        要求子项id
     * @return
     */
    public List<SchRotationDeptReq> getReqs(String rotationFlow, String relRecordFlow, List<String> recTypeId, String itemId) {
        int coditionCount = 0;

        SchRotationDeptReqExample reqExample = new SchRotationDeptReqExample();
        SchRotationDeptReqExample.Criteria reqCriteria = reqExample.createCriteria()
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

        if (StringUtil.isNotBlank(rotationFlow)) {
            coditionCount++;
            reqCriteria.andRotationFlowEqualTo(rotationFlow);
        }

        if (StringUtil.isNotBlank(relRecordFlow)) {
            coditionCount++;
            reqCriteria.andRelRecordFlowEqualTo(relRecordFlow);

            if (recTypeId != null && recTypeId.size() > 0) {
                coditionCount++;
                reqCriteria.andRecTypeIdIn(recTypeId);
            }

            if (StringUtil.isNotBlank(itemId)) {
                coditionCount++;
                reqCriteria.andItemIdEqualTo(itemId);
            }
        }

        if (coditionCount == 0) {
            return null;
        }

        return rotationDeptReqMapper.selectByExample(reqExample);
    }

    /**
     * 查询学员的轮转计划和过程信息
     *
     * @param userFlow    学员流水号
     * @param resultFlow  计划流水号
     * @param processFlow 过程流水号
     * @return 至少有传参数
     */
    public List<SchArrangeResultExt> getResults(String userFlow, String resultFlow, String processFlow) {
        int coditionCount = 0;

        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (StringUtil.isNotBlank(userFlow)) {
            coditionCount++;
            paramMap.put("userFlow", userFlow);
        }

        if (StringUtil.isNotBlank(resultFlow)) {
            coditionCount++;
            paramMap.put("resultFlow", resultFlow);
        }

        if (StringUtil.isNotBlank(processFlow)) {
            coditionCount++;
            paramMap.put("processFlow", processFlow);
        }

        if (coditionCount == 0) {
            return null;
        }

        return resultExtMapper.getResults(paramMap);
    }

    /**
     * 获取轮转方案的规则
     *
     * @param orgFlow      是否是标准(机构号)
     * @param rotationFlow 轮转方案流水号
     * @param recordFlow   轮转科室流水号
     * @return
     */
    public List<SchRotationDept> getDept(String orgFlow, String rotationFlow, String recordFlow) {
        int coditionCount = 0;

        SchRotationDeptExample deptExample = new SchRotationDeptExample();
        SchRotationDeptExample.Criteria deptCriteria = deptExample.createCriteria()
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

        if (StringUtil.isNotBlank(orgFlow)) {
            deptCriteria.andOrgFlowEqualTo(orgFlow);
        } else {
            deptCriteria.andOrgFlowIsNull();
        }

        if (StringUtil.isNotBlank(rotationFlow)) {
            coditionCount++;
            deptCriteria.andRotationFlowEqualTo(rotationFlow);
        }

        if (StringUtil.isNotBlank(recordFlow)) {
            coditionCount++;
            deptCriteria.andRecordFlowEqualTo(recordFlow);
        }

        if (coditionCount == 0) {
            return null;
        }

        return schRotationDeptMapper.selectByExample(deptExample);
    }

    /**
     * 暂时作为缩减方案表
     *
     * @param doctorFlow
     * @param rotationFlow
     * @return
     */
    public List<SchDoctorDept> getSelDepts(String doctorFlow, String rotationFlow) {
        int coditionCount = 0;

        SchDoctorDeptExample docDeptExample = new SchDoctorDeptExample();
        SchDoctorDeptExample.Criteria docDeptCriteria = docDeptExample.createCriteria()
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

        if (StringUtil.isNotBlank(doctorFlow)) {
            coditionCount++;
            docDeptCriteria.andDoctorFlowEqualTo(doctorFlow);
        }

        if (StringUtil.isNotBlank(rotationFlow)) {
            coditionCount++;
            docDeptCriteria.andRotationFlowEqualTo(rotationFlow);
        }

        if (coditionCount == 0) {
            return null;
        }

        return doctorDeptMapper.selectByExample(docDeptExample);
    }

    /**
     * 基础数据列表获取方法END
     */

    /**
     * 基本数据包装方法START
     */

    /**
     * 统计各层面的rec数量
     *
     * @param recs rec数据列表
     * @return
     */
    public Map<String, Float> getRecCount(List<ResRec> recs) {
        if (recs != null && !recs.isEmpty()) {
            Map<String, Float> recCountMap = new HashMap<String, Float>();
            for (ResRec rr : recs) {
                if (rr != null) {
                    //计算总完成数
                    setVal4MapStrFloat(recCountMap, TOTAL, 1f);

                    //当前轮转科室的登记数量
                    String processFlow = rr.getProcessFlow();
                    setVal4MapStrFloat(recCountMap, processFlow, 1f);

                    //当前标准科室的登记数量
                    String schRotationDeptFlow = rr.getSchRotationDeptFlow();
                    setVal4MapStrFloat(recCountMap, schRotationDeptFlow, 1f);

                    //登记类型
                    String recTypeId = rr.getRecTypeId();

                    //轮转科室及标准科室下各类型的登记数量
                    setVal4MapStrFloat(recCountMap, processFlow + recTypeId, 1f);
                    setVal4MapStrFloat(recCountMap, schRotationDeptFlow + recTypeId, 1f);

                    //子项id
                    String itemId = rr.getItemId();

                    //轮转科室及标准科室下各子项的登记数量
                    if (StringUtil.isNotBlank(itemId)) {
                        setVal4MapStrFloat(recCountMap, processFlow + recTypeId + itemId, 1f);
                        setVal4MapStrFloat(recCountMap, schRotationDeptFlow + recTypeId + itemId, 1f);
                    }
                }
            }
            return recCountMap;
        }
        return null;
    }

    /**
     * 计算各层面要求的同时组织出各类型下的子项列表
     *
     * @param reqs     要求数据列表
     * @param itemsMap 子项列表Map
     * @return
     */
    public Map<String, Float> getReqCount(List<SchRotationDept> depts, List<SchRotationDeptReq> reqs, List<SchDoctorDept> selDepts, Map<String, List<String>> itemsMap) {
        //验证参数是否可用
        if (depts == null || depts.isEmpty()) {
            return null;
        }
        if (reqs == null || reqs.isEmpty()) {
            return null;
        }

        Map<String, SchDoctorDept> selDeptMap = transListObjToMapsSoM(false, selDepts, "groupFlow", "standardDeptId");

        Map<String, SchRotationDept> deptMap = transListObjToMapsSoM(false, depts, "recordFlow");

        Map<String, Float> reqCountMap = new HashMap<String, Float>();
        for (SchRotationDeptReq req : reqs) {
            if (req != null) {
                BigDecimal reqNumBig = req.getReqNum();
                if (reqNumBig != null) {
                    Float reqNum = reqNumBig.floatValue();

                    //标准科室flow
                    String relRecordFlow = req.getRelRecordFlow();

                    //标准科室
                    SchRotationDept dept = deptMap.get(relRecordFlow);
                    if (dept != null) {
                        //用组合flow和标准科室id去绑定以便于result获取
                        String standardGroupFlow = dept.getGroupFlow();
                        String standardDeptId = dept.getStandardDeptId();

                        //计算缩减
                        if (selDeptMap != null) {
                            SchDoctorDept selDept = selDeptMap.get(standardGroupFlow + standardDeptId);
                            if (selDept != null) {
                                String reqMonth = dept.getSchMonth();
                                String reductionMonth = selDept.getSchMonth();

                                try {
                                    Float reqMonthF = Float.valueOf(reqMonth);
                                    Float reductionMonthF = Float.valueOf(reductionMonth);

                                    if (reqMonthF != 0 && reductionMonthF != 0) {
                                        Float reductionPer = reductionMonthF / reqMonthF;

                                        reqNum *= reductionPer;

                                        if (reqNum > 0 && reqNum < 1) {
                                            reqNum = 1f;
                                        }

                                        reqNum = (float) Math.round(reqNum);
                                    }
                                } catch (Exception e) {
                                    logger.error("", e);
                                }
                            }
                        }

                        //计算总完成数
                        setVal4MapStrFloat(reqCountMap, TOTAL, reqNum);
                        //要求绑定至标准科室
                        setVal4MapStrFloat(reqCountMap, standardGroupFlow + standardDeptId, reqNum);

                        //标准科室下各类型要求数
                        String recTypeId = req.getRecTypeId();
                        setVal4MapStrFloat(reqCountMap, standardGroupFlow + standardDeptId + recTypeId, reqNum);

                        //标准科室下各子项要求数
                        String itemId = req.getItemId();
                        if (StringUtil.isNotBlank(itemId)) {
                            setVal4MapStrFloat(reqCountMap, standardGroupFlow + standardDeptId + recTypeId + itemId, reqNum);

                            //为该标准科室各类型绑定自己的子项
                            if (itemsMap != null) {
                                List<String> items = itemsMap.get(standardGroupFlow + standardDeptId + recTypeId);
                                if (items == null) {
                                    items = new ArrayList<String>();
                                    itemsMap.put(standardGroupFlow + standardDeptId + recTypeId, items);
                                }
                                items.add(itemId);
                            }
                        }
                    }
                }
            }
        }

        return reqCountMap;
    }

    @Resource
    private ResUserBindMacidMapper macidMapper;

    @Override
    public ResUserBindMacid readMacidByUserFlow(String userFlow) {
        return macidMapper.selectByPrimaryKey(userFlow);
    }

    @Override
    public void saveUserMacid(ResUserBindMacid macid) {
        ResUserBindMacid old = readMacidByUserFlow(macid.getUserFlow());
        if (old == null) {
            macid.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            macidMapper.insertSelective(macid);
        } else {
            macidMapper.updateByPrimaryKeySelective(macid);
        }
    }

    @Override
    public ResUserBindMacid readMacidByMacid(String uuid) {
        ResUserBindMacidExample example = new ResUserBindMacidExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andMacIdEqualTo(uuid);
        List<ResUserBindMacid> list = macidMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<ResLectureInfo> checkJoinList(String lectureFlow, String userFlow) {
        return lectureInfoExtMapper.checkJoinList(lectureFlow, userFlow);
    }

    @Override
    public int saveRegist(ResLectureScanRegist regist) {
        if (regist != null) {
            if (StringUtil.isNotBlank(regist.getRecordFlow())) {
                regist.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                regist.setModifyUserFlow(regist.getOperUserFlow());
                regist.setModifyTime(DateUtil.getCurrDateTime());
                return lectureScanRegistMapper.updateByPrimaryKeySelective(regist);
            } else {
                regist.setRecordFlow(PkUtil.getUUID());
                regist.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                regist.setCreateUserFlow(regist.getOperUserFlow());
                regist.setCreateTime(DateUtil.getCurrDateTime());
                regist.setModifyUserFlow(regist.getOperUserFlow());
                regist.setModifyTime(DateUtil.getCurrDateTime());
                return lectureScanRegistMapper.insertSelective(regist);
            }
        }
        return 0;
    }

    @Override
    public String queryRotationPlan(Map<String, Object> map) {
        return resultExtMapper.queryRotationPlan(map);
    }

    @Override
    public ResAssessCfg readCfgAssess(String cfgFlow) {
        return assessCfgMapper.selectByPrimaryKey(cfgFlow);
    }

    @Override
    public List<ResAssessCfg> selectResByExampleWithBLOBs(ResAssessCfg assessCfg) {
        ResAssessCfgExample example = new ResAssessCfgExample();
        ResAssessCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (assessCfg != null) {
            if (StringUtil.isNotBlank(assessCfg.getCfgCodeId())) {
                criteria.andCfgCodeIdEqualTo(assessCfg.getCfgCodeId());
            }
            if (StringUtil.isNotBlank(assessCfg.getFormStatusId())) {
                criteria.andFormStatusIdEqualTo(assessCfg.getFormStatusId());
            }
        }
        example.setOrderByClause("CREATE_TIME");
        return assessCfgMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int queryStudentsFilldDta(Map<String, Object> paramMap) {
        return resultExtMapper.queryStudentsFilldDta(paramMap);
    }

    @Override
    public int updateRecording(DoctorUntiedRecording recording) {
        String recordFlow = PkUtil.getUUID();
        recording.setRecordFlow(recordFlow);
        recording.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        String currDateTime = DateUtil.getCurrDateTime();
        recording.setCreateTime(currDateTime);
        recording.setModifyTime(currDateTime);
        String doctorFlow = recording.getDoctorFlow() == null ? "" : recording.getDoctorFlow();
        recording.setCreateUserFlow(doctorFlow);
        recording.setModifyUserFlow(doctorFlow);
        return recordingMapper.insertSelective(recording);

    }

    @Override
    public List<DoctorUntiedRecording> queryDoctorUnLockDate(Map<String, Object> queryMap) {
        return resultExtMapper.queryDoctorUnLockDate(queryMap);
    }

    @Override
    public int queryStudenLoginLog(SysLog sysLog) {
        SysLogExample example = new SysLogExample();
        SysLogExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (null != sysLog) {
            String operId = sysLog.getOperId();
            if (StringUtil.isNotBlank(operId)) {
                criteria.andOperIdEqualTo(operId);
            }
            String userFlow = sysLog.getUserFlow();
            if (StringUtil.isNotBlank(userFlow)) {
                criteria.andUserFlowEqualTo(userFlow);
            }
        }
        return logMapper.countByExample(example);
    }

    @Override
    public List<SysUser> checkPhoneIsVerify(String userPhone) {
        return sysUserRegisterMapper.checkPhoneIsVerify(userPhone);
    }

    @Override
    public int saveForgetPasswdUser(String userPhone, String code, String codeTime) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserPhoneEqualTo(userPhone).andIsVerifyEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (sysUsers != null && sysUsers.size() > 0) {
            SysUser record = sysUsers.get(0);
            record.setVerifyCode(code);
            record.setVerifyCodeTime(codeTime);
            return sysUserMapper.updateByPrimaryKey(record);
        }
        return 0;
    }

    @Override
    public SysUser selectByUserPhone(String userPhone) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserPhoneEqualTo(userPhone).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (sysUsers != null && sysUsers.size() > 0) {
            List<String> collect = sysUsers.stream().map(sysUser -> sysUser.getVerifyCodeTime()).collect(Collectors.toList());
            String max = Collections.max(collect);
            for (SysUser sysUser : sysUsers) {
                if (StringUtil.isNotBlank(max) && max.equals(sysUser.getVerifyCodeTime())) {
                    return sysUser;
                }
            }
        }
        return null;
    }

    @Override
    public int updateUser(SysUser sysUser) {
        if (StringUtil.isNotBlank(sysUser.getUserFlow())) {
            return sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public List<SysUser> selectByUserPhoneAndIsVerify(String userPhone) {
        return sysUserRegisterMapper.selectByUserPhoneAndIsVerify(userPhone);
    }

    @Override
    public int saveModifyUser(SysUser currentUser) {
        return sysUserMapper.updateByPrimaryKey(currentUser);
    }

    @Override
    public int saveAuthenSuccessUser(SysUser currentUser) {
        currentUser.setIsVerify(com.pinde.core.common.GlobalConstant.FLAG_Y);
        return sysUserMapper.updateByPrimaryKey(currentUser);
    }

    @Override
    public SysUser findByUserPhone(String userCode) {
        return sysUserRegisterMapper.findByUserPhone(userCode);
    }

    @Override
    public SysUser findByIdNo(String idNo) {
        return sysUserRegisterMapper.findByIdNo(idNo);
    }

    @Override
    public SchRotation searchRoattionNew(String docCategroyId, String trainingSpeId) {
        SchRotationExample example = new SchRotationExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andSpeIdEqualTo(trainingSpeId).andPublishFlagEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorCategoryIdEqualTo(docCategroyId).andCreateTimeLessThan("2019");
        example.setOrderByClause("create_time");
        List<SchRotation> list = rotationMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    @Override
    public List<com.pinde.core.model.ResDoctorRecruit> getRecruitList(String userFlow) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(userFlow).andAuditStatusIdEqualTo("Passed");
        example.setOrderByClause("CREATE_TIME DESC");
        return recruitMapper.selectByExample(example);
    }

    @Override
    public List<LectureInfoTarget> searchLectureInfoTargetList(String lectureFlow) {
        LectureInfoTargetExample example = new LectureInfoTargetExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andLectureFlowEqualTo(lectureFlow);
        example.setOrderByClause("ORDINAL");
        return lectureInfoTargetMapper.selectByExample(example);
    }

    @Override
    public List<ResLectureEvaDetail> searchUserEvalList(Map<String, String> param) {
        return lectureEvaDetailExtMapper.searchUserEvalList(param);
    }

    @Override
    public ResLectureScanRegist getLectureScanRegistInfoByFlow(String recordFlow) {
        return lectureScanRegistMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public String saveLectureEval(ResLectureScanRegist scanRegist, Map<String, Object> paramMap) {
        // 更新讲座活动报名签到表评分信息
        int count = lectureScanRegistMapper.updateByPrimaryKeySelective(scanRegist);
        if (count == 0) {
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
        // 保存讲座活动评分信息
        count = lectureEvaDetailExtMapper.saveLectureEval(paramMap);
        if (count == 0) {
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }

    //获取当前城市在当前时间存在的考试
    @Override
    public List<ResTestConfig> findEffective(String currDateTime, String orgCityId) {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andApplyStartTimeLessThanOrEqualTo(currDateTime)
                .andApplyEndTimeGreaterThanOrEqualTo(currDateTime).andCitysIdLike("%" + orgCityId + "%");
        return resTestConfigMapper.selectByExample(example);
    }

    //获取医师的所有补考记录
    @Override
    public List<JsresExamSignup> readDoctorExanSignUps(String doctorFlow) {
        JsresExamSignupExample examSignupExample = new JsresExamSignupExample();
        examSignupExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow);
        examSignupExample.setOrderByClause("CREATE_TIME desc");
        List<JsresExamSignup> recruitList = jsresExamSignupMapper.selectByExample(examSignupExample);
        return recruitList;
    }

    @Override
    public List<com.pinde.core.model.ResDoctorRecruit> searchResDoctorRecruitList(com.pinde.core.model.ResDoctorRecruit recruit, String orderByClause) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(recruit.getDoctorFlow())) {
            criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
        }
        if (StringUtil.isNotBlank(recruit.getCatSpeId())) {
            criteria.andCatSpeIdEqualTo(recruit.getCatSpeId());
        }
        if (StringUtil.isNotBlank(recruit.getAuditStatusId())) {
            criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
        }
        if (StringUtil.isNotBlank(recruit.getRecordStatus())) {
            criteria.andRecordStatusEqualTo(recruit.getRecordStatus());
        }
        if (StringUtil.isNotBlank(orderByClause)) {
            example.setOrderByClause(orderByClause);
        }
        if (StringUtil.isNotBlank(recruit.getRecruitFlow())) {
            criteria.andRecruitFlowEqualTo(recruit.getRecruitFlow());
        }
        if (StringUtil.isNotBlank(recruit.getDoctorStatusId())) {
            criteria.andDoctorStatusIdEqualTo(recruit.getDoctorStatusId());
        }
        if (StringUtil.isNotBlank(recruit.getRecruitYear())) {
            criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
        }
        return doctorRecruitMapper.selectByExample(example);
    }

    @Override
    public List<ResTestConfig> findEffectiveByParam(String currDateTime, String applyTime, String orgCityId) {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andApplyStartTimeLessThanOrEqualTo(applyTime)
                .andApplyEndTimeGreaterThanOrEqualTo(applyTime).andCitysIdLike("%" + orgCityId + "%")
                .andTestEndTimeGreaterThanOrEqualTo(currDateTime);
        return resTestConfigMapper.selectByExample(example);
    }

    @Override
    public JsresGraduationApply searchByRecruitFlow(String recruitFlow, String applyYear) {
        JsresGraduationApplyExample example = new JsresGraduationApplyExample();
        JsresGraduationApplyExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(recruitFlow)) {
            criteria.andRecruitFlowEqualTo(recruitFlow);
        }
        if (StringUtil.isNotBlank(applyYear)) {
            criteria.andApplyYearEqualTo(applyYear);
        }
        List<JsresGraduationApply> applys = graduationApplyMapper.selectByExample(example);
        if (applys != null && applys.size() > 0) {
            return graduationApplyMapper.selectByExample(example).get(0);
        } else {
            return null;
        }
    }

    //获取所有的成绩记录
    @Override
    public List<ResScore> selectAllScore(String userFlow, String recruitFlow) {
        ResScoreExample example = new ResScoreExample();
        ResScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if (StringUtil.isNotBlank(userFlow)) {
            criteria.andDoctorFlowEqualTo(userFlow);
        }
        if (StringUtil.isNotBlank(recruitFlow)) {
            criteria.andRecruitFlowEqualTo(recruitFlow);
        }
        example.setOrderByClause("SCORE_PHASE_ID DESC");
        return scoreMapper.selectByExample(example);
    }

    @Override
    public List<ResScore> selectByRecruitFlowAndScoreType(String recruitFlow, String id) {

        if (!StringUtil.isNotBlank(recruitFlow) && !StringUtil.isNotBlank(id)) {
            return null;
        }

        ResScoreExample example = new ResScoreExample();
        ResScoreExample.Criteria criteria = example.createCriteria();

        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andRecruitFlowEqualTo(recruitFlow)
                .andScoreTypeIdEqualTo(id);
        List<ResScore> scores = scoreMapper.selectByExampleWithBLOBs(example);
        return scores;
    }

    /**
     * 获取个人履历
     */
    @Override
    public PubUserResume readPubUserResume(String userFlow) {
        if (StringUtil.isNotBlank(userFlow)) {
            PubUserResume resume = userResumpMapper.selectByPrimaryKey(userFlow);
            return resume;
        }
        return null;
    }

    @Override
    public <T> T converyToJavaBean(String xml, Class<T> clazz) throws DocumentException {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (Exception e) {
            logger.error("", e);
        }
        if (StringUtil.isNotBlank(xml)) {
            if (t != null) {
                //解析xml返回对象
                Document dom = null;
                try {
                    dom = DocumentHelper.parseText(xml);
                } catch (DocumentException e) {
                    logger.error("", e);
                }
                Element root = dom.getRootElement();
                List<Element> elements = root.elements();
                if (elements != null && !elements.isEmpty()) {
                    for (Element e : elements) {
                        try {
                            setValue(t, e.getName(), e.getText());
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        } finally {
                            continue;
                        }
                    }
                }
            }
        }
        return t;
    }

    private void setValue(Object obj, String attrName, String attrValue) {
        try {
            Class<?> objClass = obj.getClass();
            String firstLetter = attrName.substring(0, 1).toUpperCase();
            String methedName = "set" + firstLetter + attrName.substring(1);
            Method setMethod = objClass.getMethod(methedName, String.class);
            setMethod.invoke(obj, attrValue);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Override
    public List<SysDict> searchDictListByDictTypeId(String dictTypeId) {
        SysDictExample example = new SysDictExample();
        example.createCriteria().andDictTypeIdEqualTo(dictTypeId).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("ORDINAL");
        return this.sysDictMapper.selectByExample(example);

    }

    @Override
    public SchRotation readSchRotation(String rotationFlow) {
        return rotationMapper.selectByPrimaryKey(rotationFlow);
    }

    @Override
    public SchRotation getRotationByRecruit(com.pinde.core.model.ResDoctorRecruit recruit) {
        if (recruit != null) {
            SchRotation rotation = null;

            String trainingType = recruit.getCatSpeId();
            if (com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(trainingType)) {
                trainingType = RecDocCategoryEnum.Doctor.getId();
            }

            String speId = recruit.getSpeId();

            if (StringUtil.isNotBlank(trainingType) && StringUtil.isNotBlank(speId)) {
                SchRotationExample example = new SchRotationExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andSpeIdEqualTo(speId).andPublishFlagEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andDoctorCategoryIdEqualTo(trainingType);
                example.setOrderByClause("create_time desc");

                List<SchRotation> list = rotationMapper.selectByExample(example);
                if (list != null && list.size() > 0) {
                    rotation = list.get(0);
                }
            }

            if (rotation != null) {
                return rotation;
            }
        }
        return null;
    }

    @Override
    public ResDoctor findByFlow(String doctorFlow) {
        return doctorMapper.selectByPrimaryKey(doctorFlow);
    }

    @Override
    public List<SchDoctorDept> searchDoctorDeptForReductionIgnoreStatus(String doctorFlow, String rotationFlow) {
        if (StringUtil.isNotBlank(doctorFlow) && StringUtil.isNotBlank(rotationFlow)) {
            SchDoctorDeptExample example = new SchDoctorDeptExample();
            example.createCriteria().andDoctorFlowEqualTo(doctorFlow).andRotationFlowEqualTo(rotationFlow);
            example.setOrderByClause("ORDINAL");
            return doctorDeptMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<SchRotationGroup> searchSchRotationGroup(String rotationFlow) {
        SchRotationGroupExample example = new SchRotationGroupExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRotationFlowEqualTo(rotationFlow)
                .andOrgFlowIsNull();
        example.setOrderByClause("ORDINAL");
        return rotationGroupMapper.selectByExample(example);
    }

    @Override
    public List<SchRotationDept> searchSchRotationDeptTwo(String rotationFlow) {
        SchRotationDeptExample example = new SchRotationDeptExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRotationFlowEqualTo(rotationFlow)
                .andOrgFlowIsNull();
        example.setOrderByClause("ORDINAL");
        return rotationDeptMapper.selectByExample(example);
    }

    @Override
    public List<SchArrangeResult> searchResultByGroupFlow(String groupFlow,
                                                          String standardDeptId, String doctorFlow) {
        SchArrangeResultExample example = new SchArrangeResultExample();
        SchArrangeResultExample.Criteria exam = example.createCriteria();
        exam.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(groupFlow)) {
            exam.andStandardGroupFlowEqualTo(groupFlow);
        }
        if (StringUtil.isNotBlank(standardDeptId)) {
            exam.andStandardDeptIdEqualTo(standardDeptId);
        }
        if (StringUtil.isNotBlank(doctorFlow)) {
            exam.andDoctorFlowEqualTo(doctorFlow);
        }
        example.setOrderByClause("SCH_START_DATE");
        return arrangeResultMapper.selectByExample(example);
    }

    @Override
    public List<ResSchProcessExpress> searchByUserFlowAndTypeIdTwo(String operUserFlow,
                                                                   String recTypeId) {
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
                .andOperUserFlowEqualTo(operUserFlow);
        return expressMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<JsresDoctorDeptDetail> deptDoctorAllWorkDetailByNow(String recruitFlow, String doctorFlow, String applyYear, String rotationFlow) {
        return resultExtMapper.deptDoctorAllWorkDetailByNow(recruitFlow, doctorFlow, applyYear, rotationFlow);
    }

    @Override
    public Map<String, Object> doctorDeptAvgWorkDetail(String recruitFlow, String applyYear) {
        return docSchProcessExtMapper.doctorDeptAvgWorkDetail(recruitFlow, applyYear);
    }

    @Override
    public ResDoctor readDoctor(String recordFlow) {
        return doctorMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int editGraduationApply2(JsresGraduationApply jsresGraduationApply, String recruitFlow, String changeSpeId, String doctorFlow, String applyYear, Map<String, String> practicingMap, SysUser user, String rotationFlow, String reSubmitFlag) throws DocumentException {
        int i = editGraduationApply(jsresGraduationApply, user);
        if (i == 1) {
            if (StringUtil.isNotBlank(changeSpeId)) {
                ResDoctorRecruitWithBLOBs newRecruit = new ResDoctorRecruitWithBLOBs();
                newRecruit.setRecruitFlow(recruitFlow);
                newRecruit.setChangeSpeId(changeSpeId);
                newRecruit.setChangeSpeName(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getDictNameById(changeSpeId));
                editDoctorRecruit(newRecruit, user);
            }
            //保存提交后的证书信息，百分比，出科考核表
            addOldInfoByApplyYear(applyYear, recruitFlow, doctorFlow, jsresGraduationApply.getApplyFlow(), practicingMap, user, rotationFlow, reSubmitFlag);
        }
        return i;
    }

    public int editGraduationApply(JsresGraduationApply jsresGraduationApply, SysUser user) {
        if (jsresGraduationApply != null) {
            if (StringUtil.isNotBlank(jsresGraduationApply.getApplyFlow())) {//修改
                jsresGraduationApply.setModifyTime(DateUtil.getCurrDateTime2());
                jsresGraduationApply.setModifyUserFlow(user.getUserFlow());
                return this.graduationApplyMapper.updateByPrimaryKeySelective(jsresGraduationApply);
            } else {//新增
                jsresGraduationApply.setApplyFlow(PkUtil.getUUID());
                jsresGraduationApply.setCreateTime(DateUtil.getCurrDateTime2());
                jsresGraduationApply.setCreateUserFlow(user.getUserFlow());
                jsresGraduationApply.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                return this.graduationApplyMapper.insertSelective(jsresGraduationApply);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    public int editDoctorRecruit(ResDoctorRecruitWithBLOBs recruit, SysUser user) {
        if (recruit != null) {
            if (StringUtil.isNotBlank(recruit.getRecruitFlow())) {
                recruit.setModifyTime(DateUtil.getCurrDateTime2());
                recruit.setModifyUserFlow(user.getUserFlow());
                return doctorRecruitMapper.updateByPrimaryKeySelective(recruit);
            } else {
                recruit.setRecruitFlow(PkUtil.getUUID());
                recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                recruit.setCreateTime(DateUtil.getCurrDateTime2());
                recruit.setCreateUserFlow(user.getUserFlow());
                return doctorRecruitMapper.insertSelective(recruit);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    public void addOldInfoByApplyYear(String applyYear, String recruitFlow, String doctorFlow, String applyFlow, Map<String, String> practicingMap, SysUser user, String rotationFlow, String reSubmitFlag) throws DocumentException {
        //学员出科考核表数据抽取
        selectAfter(applyYear, recruitFlow, doctorFlow, user);
        //更新学员相关证书信息
        practicingMap.put("applyFlow", applyFlow);
        practicingMap.put("doctorFlow", doctorFlow);
        tempMapper.updateRecruitAsseInfoByApplyYear2(practicingMap);
        //学员资格审查百分比
        setFourStep(applyYear, recruitFlow, doctorFlow, applyFlow, rotationFlow, reSubmitFlag);
    }

    private void selectAfter(String applyYear, String recruitFlow, String doctorFlow, SysUser user) throws DocumentException {
        List<ResSchProcessExpress> recs = expressBiz.searchByUserFlowAndTypeId(doctorFlow, AfterRecTypeEnum.AfterSummary.getId());
        List<SchRotationDeptAfterWithBLOBs> afters = expressBiz.getAfterByDoctorFlow(doctorFlow, applyYear);
        Map<String, SchRotationDeptAfterWithBLOBs> map = new HashMap();
        if (afters != null && afters.size() > 0) {
            for (SchRotationDeptAfterWithBLOBs a : afters) {
                map.put(a.getSchRotationDeptFlow() + a.getDoctorFlow(), a);
            }
        }
        if (recs != null && recs.size() > 0) {
            for (ResSchProcessExpress rec : recs) {
                SchRotationDeptAfterWithBLOBs after = map.get(rec.getSchRotationDeptFlow() + rec.getOperUserFlow());
                if (after == null)
                    after = new SchRotationDeptAfterWithBLOBs();

                String content = null == rec ? "" : rec.getRecContent();

                StringBuffer imageUrls = new StringBuffer();
                StringBuffer thumbUrls = new StringBuffer();
                if (StringUtil.isNotBlank(content)) {
                    Document doc = DocumentHelper.parseText(content);
                    Element root = doc.getRootElement();
                    List<Element> imageEles = root.elements();
                    if (imageEles != null && imageEles.size() > 0) {
                        boolean isUpdate = false;
                        for (Element image : imageEles) {
                            Element imageUrl = image.element("imageUrl");
                            if (imageUrl != null) {
                                if (imageUrl.getTextTrim().indexOf("http://58.213.112.250:65486") > 0 ||
                                        imageUrl.getTextTrim().indexOf("http://116.62.70.138") > 0)
                                    isUpdate = true;
                                String url = imageUrl.getTextTrim().replace("http://58.213.112.250:65486", "http://js.ezhupei.com")
                                        .replace("http://116.62.70.138", "http://js.ezhupei.com");
                                imageUrls.append(url + ",");
                                imageUrl.setText(url);
                            }
                            Element thumbUrl = image.element("thumbUrl");
                            if (thumbUrl != null) {
                                if (thumbUrl.getTextTrim().indexOf("http://58.213.112.250:65486") > 0 ||
                                        thumbUrl.getTextTrim().indexOf("http://116.62.70.138") > 0)
                                    isUpdate = true;
                                String url = thumbUrl.getTextTrim().replace("http://58.213.112.250:65486", "http://js.ezhupei.com")
                                        .replace("http://116.62.70.138", "http://js.ezhupei.com");
                                thumbUrls.append(url + ",");
                                thumbUrl.setText(url);
                            }
                        }
                        if (isUpdate) {
                            rec.setRecContent(doc.asXML());
                            expressBiz.editResRec(rec, user);
                        }
                    }
                }

                if (StringUtil.isNotBlank(imageUrls.toString()))
                    after.setImageUrls(imageUrls.substring(0, imageUrls.length() - 1));
                else
                    after.setImageUrls("");

                if (StringUtil.isNotBlank(thumbUrls.toString()))
                    after.setThumbUrls(thumbUrls.substring(0, thumbUrls.length() - 1));
                else
                    after.setThumbUrls("");
                after.setApplyYear(applyYear);
                after.setRecordStatus(rec.getRecordStatus());
                after.setModfiyTime(rec.getModifyTime());
                after.setSchRotationDeptFlow(rec.getSchRotationDeptFlow());
                after.setDoctorFlow(rec.getOperUserFlow());
                expressBiz.editAfter(after);
            }
        }
    }

    private void setFourStep(String applyYear, String recruitFlow, String doctorFlow, String applyFlow, String rotationFlow, String reSubmitFlag) {
        //1
        tempMapper.deleteDeptDetailByApplyYear(applyYear, doctorFlow);
        String exeMethodInRedis =  stringRedisTemplate.opsForValue().get(GlobalConstant.exeMethod);
        if(StringUtils.isEmpty(exeMethodInRedis)) {
            exeMethodInRedis = ExeMethod.SQL.getValue();
        }
        if(ExeMethod.JOB.getValue().equals(exeMethodInRedis)) {
            // 非重新提交从计算好的学员比例插入数据
            if(StringUtil.isNotBlank(reSubmitFlag) && GlobalConstant.FLAG_N.equals(reSubmitFlag)){
                tempMapper.insetDeptDetailByStatistics(applyYear, doctorFlow, rotationFlow);
            }else{
                tempMapper.insetDeptDetailByApplyYear(applyYear,doctorFlow,recruitFlow, rotationFlow);
            }
        }else if(ExeMethod.SQL.getValue().equals(exeMethodInRedis)){
            tempMapper.insetDeptDetailByApplyYear(applyYear,doctorFlow,recruitFlow, rotationFlow);
        }else {
            List<JsresDoctorDeptDetail> jsresDoctorDeptDetails = deptDoctorAllWorkDetailByNow_new(recruitFlow, doctorFlow, applyYear, rotationFlow);
            LambdaQueryWrapper<JsresDoctorDeptDetail> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(JsresDoctorDeptDetail::getDoctorFlow,doctorFlow);
            jsresDoctorDeptDetailMapper.delete(queryWrapper);
            if(CollectionUtils.isNotEmpty(jsresDoctorDeptDetails)) jsresDoctorDeptDetailMapper.insert(jsresDoctorDeptDetails);

        }
        //2
        tempMapper.deleteDeptTempByRecruitFlow(recruitFlow);
        tempMapper.updateDeptTempByRecruitFlow(recruitFlow, applyYear);
        //3
        tempMapper.deleteDeptAvgTempByRecruitFlow(recruitFlow);
        tempMapper.updateDeptAvgTempByRecruitFlow(recruitFlow, applyYear);
        //4
        tempMapper.updateRecruitAvgTempByRecruitFlow(recruitFlow, applyFlow);
    }

    @Override
    public ResDoctorRecruit readResDoctorRecruitBySessionNumber(String doctorFlow, String sessionNumber) {

        if (StringUtil.isNotBlank(doctorFlow)) {
            ResDoctorRecruitExample example = new ResDoctorRecruitExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andDoctorFlowEqualTo(doctorFlow).andAuditStatusIdEqualTo(com.pinde.core.common.enums.ResBaseStatusEnum.Passed.getId())
                    .andSessionNumberEqualTo(sessionNumber);
            example.setOrderByClause("MODIFY_TIME DESC");
            List<com.pinde.core.model.ResDoctorRecruit> recruitList = doctorRecruitMapper.selectByExample(example);
            if (recruitList != null && !recruitList.isEmpty()) {
                return recruitList.get(0);
            }
        }
        return null;
    }

    @Override
    public List<JsresExamSignup> getSignUpListByYear(String year, String doctorFlow, String typeId) {
        JsresExamSignupExample examSignupExample = new JsresExamSignupExample();
        examSignupExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow).andSignupYearEqualTo(year).andSignupTypeIdEqualTo(typeId);

        List<JsresExamSignup> recruitList = jsresExamSignupMapper.selectByExample(examSignupExample);

        return recruitList;
    }

    @Override
    public int saveSignUp(JsresExamSignup signup, SysUser user) {
        if (StringUtil.isBlank(signup.getSignupFlow())) {
            signup.setSignupFlow(PkUtil.getGUID());
            signup.setCreateTime(DateUtil.getCurrDateTime());
            signup.setCreateUserFlow(user.getUserFlow());
            signup.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            return jsresExamSignupMapper.insertSelective(signup);
        } else {
            signup.setModifyTime(DateUtil.getCurrDateTime());
            signup.setModifyUserFlow(user.getUserFlow());
            return jsresExamSignupMapper.updateByPrimaryKeySelective(signup);
        }
    }

    @Override
    public int savePubUserResume(SysUser user, PubUserResume resume) {
        //根据用户的流水号判断新增、修改履历
        if (StringUtil.isNotBlank(resume.getUserFlow())) {//修改
            resume.setModifyTime(DateUtil.getCurrDateTime());
            resume.setModifyUserFlow(user.getUserFlow());
            return userResumpMapper.updateByPrimaryKeyWithBLOBs(resume);
        } else {//新增
            if (null != user) {
                resume.setUserFlow(user.getUserFlow());
                resume.setUserName(user.getUserName());
                resume.setCreateTime(DateUtil.getCurrDateTime());
                resume.setCreateUserFlow(user.getUserFlow());
                resume.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                return userResumpMapper.insert(resume);
            } else {
                return com.pinde.core.common.GlobalConstant.ZERO_LINE;
            }
        }
    }

    @Override
    public void saveRegisteManua(String registeManua, String recruitFlow, String applyYear) {

        tempMapper.saveRegisteManua(registeManua, recruitFlow, applyYear);
    }

    @Override
    public int savePublic(ResScore resScore, SysUser user) {
        ResScore old = getScoreByDocFlowAndYearAndType(resScore.getDoctorFlow(), null, resScore.getScoreTypeId());
        if (old != null) resScore.setScoreFlow(old.getScoreFlow());
        return save(resScore, user);
    }

    @Override
    public String convertMapToXml(Map<String, String> map, ResScore resScore) throws Exception {
        String xmlBody = null;
        Document doc = null;
        Element root = null;
        ResScore old = null;
        if (com.pinde.core.common.enums.ResScoreTypeEnum.PublicScore.getId().equals(resScore.getScoreTypeId())) {
            old = getScoreByDocFlowAndYearAndType(resScore.getDoctorFlow(), null, resScore.getScoreTypeId());
        } else {
            old = getScoreByDocFlowAndYearAndType(resScore.getDoctorFlow(), resScore.getScorePhaseId(), resScore.getScoreTypeId());
        }
        String content = null == old ? "" : old.getExtScore();
        if (null != old && StringUtil.isNotBlank(content)) {
            doc = DocumentHelper.parseText(content);
            root = doc.getRootElement();
            Element extScoreInfo = root.element("extScoreInfo");
            if (extScoreInfo != null) {
                List<Element> extInfoAttrEles = extScoreInfo.elements();
                if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                    if (map != null && map.size() > 0) {
                        for (String key : map.keySet()) {
                            Element item = extScoreInfo.element(key);
                            if (item == null) item = extScoreInfo.addElement(key);
                            item.setText(map.get(key));
                        }
                    }
                } else {
                    if (map != null && map.size() > 0) {
                        for (String key : map.keySet()) {
                            Element item = extScoreInfo.addElement(key);
                            item.setText(map.get(key));
                        }
                    }
                }
            } else {
                extScoreInfo = root.addElement("extScoreInfo");
                if (map != null && map.size() > 0) {
                    for (String key : map.keySet()) {
                        Element item = extScoreInfo.addElement(key);
                        item.setText(map.get(key));
                    }
                }
            }
            xmlBody = doc.asXML();
        } else {
            doc = DocumentHelper.createDocument();
            root = doc.addElement("resExtScore");
            Element extScoreInfo = root.addElement("extScoreInfo");
            if (map != null && map.size() > 0) {
                for (String key : map.keySet()) {
                    Element item = extScoreInfo.addElement(key);
                    item.setText(map.get(key));
                }
            }
            xmlBody = doc.asXML();
        }
        return xmlBody;
    }

    public ResScore getScoreByDocFlowAndYearAndType(String doctorFlow, String year, String typeId) {
        if (!StringUtil.isNotBlank(doctorFlow) && !StringUtil.isNotBlank(year)) {
            return null;
        }

        ResScoreExample example = new ResScoreExample();
        ResScoreExample.Criteria criteria = example.createCriteria();

        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow)
                .andScoreTypeIdEqualTo(typeId);
        if (StringUtil.isNotBlank(year)) {
            criteria.andScorePhaseIdEqualTo(year);
        }
        List<ResScore> scores = scoreMapper.selectByExampleWithBLOBs(example);
        if (scores == null || scores.isEmpty()) {
            return null;
        }

        return scores.get(0);
    }

    public int save(ResScore resScore, SysUser user) {
        if (resScore != null) {
            if (StringUtil.isNotBlank(resScore.getScoreFlow())) {
                resScore.setModifyTime(DateUtil.getCurrDateTime());
                resScore.setModifyUserFlow(user.getUserFlow());
                return scoreMapper.updateByPrimaryKeySelective(resScore);
            } else {
                resScore.setScoreFlow(PkUtil.getUUID());
                resScore.setCreateTime(DateUtil.getCurrDateTime());
                resScore.setCreateUserFlow(user.getUserFlow());
                resScore.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                return scoreMapper.insert(resScore);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public ResDoctorRecruit getNewRecruitTwo(String recruitFlow) {
        if (!StringUtil.isNotBlank(recruitFlow)) {
            return null;
        }

        ResDoctorRecruitExample recruitExample = new ResDoctorRecruitExample();
        recruitExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
                .andRecruitFlowEqualTo(recruitFlow).andAuditStatusIdEqualTo("Passed");
        recruitExample.setOrderByClause("CREATE_TIME DESC");

        List<com.pinde.core.model.ResDoctorRecruit> recruits = recruitMapper.selectByExample(recruitExample);
        if (recruits == null || recruits.isEmpty()) {
            return null;
        }

        com.pinde.core.model.ResDoctorRecruit recruit = recruits.get(0);
        return recruit;

    }

    @Override
    public String checkImg(MultipartFile file) {
        List<String> mimeList = new ArrayList<String>();
        SysCfg fileMime = cfgMapper.selectByPrimaryKey("inx_image_support_mime");
        SysCfg fileSuffix = cfgMapper.selectByPrimaryKey("inx_image_support_suffix");
        SysCfg fileSize = cfgMapper.selectByPrimaryKey("inx_image_limit_size");
        if (StringUtil.isNotBlank(StringUtil.defaultString(fileMime.getCfgValue()))) {
            mimeList = Arrays.asList(StringUtil.defaultString(fileMime.getCfgValue()).split(","));
        }
        List<String> suffixList = new ArrayList<String>();
        if (StringUtil.isNotBlank(StringUtil.defaultString(fileSuffix.getCfgValue()))) {
            suffixList = Arrays.asList(StringUtil.defaultString(fileSuffix.getCfgValue().toLowerCase()).split(","));
        }
        String fileType = file.getContentType();//MIME类型;
        String fileName = file.getOriginalFilename();//文件名
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        if (!(mimeList.contains(fileType) && suffixList.contains(suffix.toLowerCase()))) {
            return "请上传 " + fileSuffix.getCfgValue() + "格式的文件";
        }
        long limitSize = Long.parseLong(StringUtil.defaultString(fileSize.getCfgValue()));//图片大小限制
        if (file.getSize() > limitSize * 1024 * 1024) {
            return "文件大小不能超过" + limitSize + "M！";
        }
        return com.pinde.core.common.GlobalConstant.FLAG_Y;//可执行保存
    }

    @Override
    public String checkUserHeader(MultipartFile file) {
        if (file != null) {
            try {
                BufferedImage image = ImageIO.read(file.getInputStream());
                //如果image=null 表示上传的不是图片格式
                if (image != null) {
                    int imageWidth = image.getWidth(); //获取图片宽度，单位px
                    int imageHeight = image.getHeight(); //获取图片高度，单位px

                    if (imageWidth < 413 || imageHeight < 626) {
                        return "上传图片像素不符合要求";// 分辨率不小于413*626
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            List<String> mimeList = new ArrayList<String>();
            if (StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))) {
                mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
            }
            List<String> suffixList = new ArrayList<String>();
            if (StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))) {
                suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
            }

            String fileType = file.getContentType();//MIME类型;
            String fileName = file.getOriginalFilename();//文件名
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            if (!(mimeList.contains(fileType) && suffixList.contains(suffix))) {
                return "只支持上传bmp/gif/jpg/png！";
            }
//			long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
            if (file.getSize() <= 150 * 1024) {
                return "图片大小不得小于150K";
            }
            if (file.getSize() > 300 * 1024) {
                return "文件大小不能超过" + "300K";
            }
        }
        return com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y;
    }

    @Override
    public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName) {
        String path = com.pinde.core.common.GlobalConstant.FLAG_N;
        if (file.getSize() > 0) {
            //创建目录
            SysCfg upload_base_dir = cfgMapper.selectByPrimaryKey("upload_base_dir");
            String dateString = DateUtil.getCurrDate2();
            String newDir = StringUtil.defaultString(upload_base_dir.getCfgValue()) + File.separator + folderName + File.separator + dateString;
            File fileDir = new File(newDir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            //文件名
            String originalFilename = file.getOriginalFilename();
            originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            File newFile = new File(fileDir, originalFilename);
            try {
                file.transferTo(newFile);
            } catch (Exception e) {
                logger.error("", e);
                throw new RuntimeException("保存文件失败！");
            }

            //删除原文件
            if (StringUtil.isNotBlank(oldFolderName)) {
                try {
                    oldFolderName = StringUtil.defaultString(upload_base_dir.getCfgValue()) + File.separator + oldFolderName;
                    File imgFile = new File(oldFolderName);
                    if (imgFile.exists()) {
                        imgFile.delete();
                    }
                } catch (Exception e) {
                    logger.error("", e);
                    throw new RuntimeException("删除文件失败！");
                }
            }
            path = folderName + "/" + dateString + "/" + originalFilename;
            com.pinde.core.util.FtpHelperUtil ftpHelperUtil = new com.pinde.core.util.FtpHelperUtil();
            String localFilePath = fileDir + File.separator + originalFilename;
            String ftpDir = folderName + File.separator + dateString;
            String ftpFileName = originalFilename;

            System.out.println("===============FTP上传开始 ============= localFilePath：" + localFilePath);
            ftpHelperUtil.uploadFile(localFilePath, ftpDir, ftpFileName);
            System.out.println("===============FTP上传开始 ============= localFilePath：" + localFilePath);
        }
        return path;
    }

    @Override
    public String saveFileToDirsNew(String oldFolderName, MultipartFile file, String folderName, String fileType) {
        String path = com.pinde.core.common.GlobalConstant.FLAG_N;
        if (file.getSize() > 0) {
            //创建目录
            SysCfg upload_base_dir = cfgMapper.selectByPrimaryKey("upload_base_dir");
            String dateString = DateUtil.getCurrDate2();
            String newDir = "";
            if (StringUtil.isNotEmpty(fileType)) {
                if ("userImages".equals(fileType)) {
                    newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "userImages" + File.separator + dateString;
                }
            }
            newDir = StringUtil.defaultString(upload_base_dir.getCfgValue()) + File.separator + folderName + File.separator + dateString;
            File fileDir = new File(newDir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            //文件名
            String originalFilename = file.getOriginalFilename();
            originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            File newFile = new File(fileDir, originalFilename);
            try {
                file.transferTo(newFile);
            } catch (Exception e) {
                logger.error("", e);
                throw new RuntimeException("保存文件失败！");
            }

            //删除原文件
            if (StringUtil.isNotBlank(oldFolderName)) {
                try {
                    oldFolderName = StringUtil.defaultString(upload_base_dir.getCfgValue()) + File.separator + oldFolderName;
                    File imgFile = new File(oldFolderName);
                    if (imgFile.exists()) {
                        imgFile.delete();
                    }
                } catch (Exception e) {
                    logger.error("", e);
                    throw new RuntimeException("删除文件失败！");
                }
            }
            path = folderName + "/" + dateString + "/" + originalFilename;
            com.pinde.core.util.FtpHelperUtil ftpHelperUtil = new com.pinde.core.util.FtpHelperUtil();
            String localFilePath = fileDir + File.separator + originalFilename;
            String ftpDir = folderName + File.separator + dateString;
            String ftpFileName = originalFilename;

            System.out.println("===============FTP上传开始 ============= localFilePath：" + localFilePath);
            ftpHelperUtil.uploadFile(localFilePath, ftpDir, ftpFileName);
            System.out.println("===============FTP上传开始 ============= localFilePath：" + localFilePath);
        }
        return path;
    }

    /**
     * @Department：研发部
     * @Description 查询系统配置的特定角色flow信息
     * @Author fengxf
     * @Date 2022/3/3
     */
    @Override
    public Map<String, Object> getUserRoleCfgInfo() {
        return sysCfgExtMapper.getUserRoleCfgInfo();
    }

    /**
     * @param userFlow
     * @Department：研发部
     * @Description 查询用户基本信息
     * @Author fengxf
     * @Date 2022/3/3
     */
    @Override
    public SysUser getUserBaseInfo(String userFlow) {
        return sysUserExtMapper.getUserBaseInfo(userFlow);
    }

    @Override
    public List<SysLog> searchSysLog(SysLog log) {
        SysLogExample example = new SysLogExample();
        SysLogExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if (null != log) {
            if (StringUtil.isNotBlank(log.getUserFlow())) {
                criteria.andUserFlowEqualTo(log.getUserFlow());
            }
            if (StringUtil.isNotBlank(log.getOperId())) {
                criteria.andOperIdEqualTo(log.getOperId());
            }
            if (StringUtil.isNotBlank(log.getLogTime())) {
                criteria.andLogTimeGreaterThan(log.getLogTime());
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return logMapper.selectByExample(example);
    }

    @Override
    public int saveUserInfo2(String userPhone, String userPasswd, String code) {
        SysUser user = new SysUser();
        String uuid = PkUtil.getUUID();
        user.setUserPhone(userPhone);
        user.setIsVerify(com.pinde.core.common.GlobalConstant.FLAG_Y);
        user.setVerifyCode(code);
        user.setVerifyCodeTime(DateUtil.getCurrDateTime2());
        user.setUserFlow(uuid);
        user.setUserCode(userPhone);
        user.setUserPasswd(PasswordHelper.encryptPassword(uuid, userPasswd));
        user.setStatusId(UserStatusEnum.Added.getId());
        user.setStatusDesc(UserStatusEnum.Added.getName());
        user.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        user.setStatusId(UserStatusEnum.Activated.getId());
        user.setStatusDesc(UserStatusEnum.Activated.getName());
        SysCfg cfg = cfgMapper.selectByPrimaryKey("res_doctor_role_flow");
        if (null != cfg && StringUtil.isNotBlank(cfg.getCfgValue())) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserFlow(user.getUserFlow());
            String currWsId = com.pinde.core.common.GlobalConstant.RES_WS_ID;
            userRole.setRecordFlow(PkUtil.getUUID());
            userRole.setWsId(currWsId);
            userRole.setRoleFlow(cfg.getCfgValue());
            userRole.setAuthTime(DateUtil.getCurrDate());
            userRole.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            userRoleMapper.insertSelective(userRole);
        }
        return sysUserMapper.insert(user);
    }

    @Override
    public int saveDoctorInfo(SysUser user, ResDoctor doctor, UserResumeExtInfoForm userResumeExt, String qtCountry) {
        if (StringUtil.isNotBlank(user.getSexId())) {
            user.setSexName(UserSexEnum.getNameById(user.getSexId()));
        } else {
            user.setSexName("");
        }
        if (StringUtil.isNotBlank(user.getUserBirthday())) {
            user.setUserBirthday(user.getUserBirthday());
        } else {
            user.setUserBirthday("");
        }
        // 民族
        if (StringUtil.isNotBlank(user.getNationId())) {
            user.setNationName(UserNationEnum.getNameById(user.getNationId()));
        } else {
            user.setNationName("");
        }
        // 国籍
        String nationalityId = user.getNationalityId() == null ? "" : user.getNationalityId();
        if (StringUtil.isNotBlank(nationalityId)) {
            user.setNationalityName(com.pinde.core.common.enums.DictTypeEnum.Nationality.getDictNameById(nationalityId));
            if (user.getNationalityId().equals("QT")) {
                user.setNationalityName(qtCountry);
            }
        } else {
            user.setNationalityName("");
        }
        //学历
        if (StringUtil.isNotBlank(user.getEducationId())) {
            user.setEducationName(com.pinde.core.common.enums.DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
        } else {
//			user.setEducationName("");
        }
        //学位
        if (StringUtil.isNotBlank(user.getDegreeId())) {
            user.setDegreeName(com.pinde.core.common.enums.DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
        } else {
            user.setDegreeName("");
        }

        //证件类型
        if (StringUtil.isNotBlank(user.getCretTypeId())) {
            user.setCretTypeName(CertificateTypeEnum.getNameById(user.getCretTypeId()));
        } else {
            user.setCretTypeName("");
        }
        if (StringUtil.isNotBlank(user.getIdNo())) {//身份证X大写
            String idNo = user.getIdNo().toUpperCase().trim();
            user.setIdNo(idNo);
        }
        saveUser(user);
        String userFlow = user.getUserFlow();
        //UserResumeExtInfoForm userResumeExt = doctorInfoForm.getUserResumeExt();
        PubUserResume pubUserResume = readPubUserResume(userFlow);
        if (pubUserResume == null) {
            pubUserResume = new PubUserResume();
        }
        String doctorDegreeTypeId = userResumeExt.getDoctorDegreeTypeId();
        if ("1".equals(doctorDegreeTypeId)) {
            userResumeExt.setDoctorDegreeTypeName("专业型");
        } else if ("2".equals(doctorDegreeTypeId)) {
            userResumeExt.setDoctorDegreeTypeName("科学型");
        }
        String masterDegreeTypeId = userResumeExt.getMasterDegreeTypeId();
        if ("1".equals(masterDegreeTypeId)) {
            userResumeExt.setMasterDegreeTypeName("专业型");
        } else if ("2".equals(masterDegreeTypeId)) {
            userResumeExt.setMasterDegreeTypeName("科学型");
        }
        //JavaBean转换成xml
        // 在读院校
        List<SysDict> dictSchoolList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("GraduateSchool");
        String readingSchoolName = userResumeExt.getReadingSchoolName();
        if (StringUtil.isNotBlank(readingSchoolName)) {
            for (SysDict s : dictSchoolList) {
                if (s.getDictName().equals(readingSchoolName)) {
                    userResumeExt.setReadingSchoolId(s.getDictId());
                    break;
                }
            }
        }
        String readingSchoolId = userResumeExt.getReadingSchoolId();
        if (StringUtil.isNotBlank(readingSchoolId)) {
            for (SysDict s : dictSchoolList) {
                if (s.getDictId().equals(readingSchoolId)) {
                    userResumeExt.setReadingSchoolName(s.getDictName());
                    break;
                }
            }
        }
        // 专科毕业院校
        String juniorCollegeSchoolName = userResumeExt.getJuniorCollegeSchoolName();
        if (StringUtil.isNotBlank(juniorCollegeSchoolName)) {
            for (SysDict s : dictSchoolList) {
                if (s.getDictName().equals(juniorCollegeSchoolName)) {
                    userResumeExt.setJuniorCollegeSchoolId(s.getDictId());
                    break;
                }
            }
        }
        // 本科毕业院校
        if (StringUtil.isNotBlank(userResumeExt.getGraduatedName())) {
            for (SysDict s : dictSchoolList) {
                if (s.getDictName().equals(userResumeExt.getGraduatedName())) {
                    userResumeExt.setGraduatedId(s.getDictId());
                    break;
                }
            }
        }
        // 专科毕业院校
        String juniorCollegeSchoolId = userResumeExt.getJuniorCollegeSchoolId();
        if (StringUtil.isNotBlank(juniorCollegeSchoolId)) {
            for (SysDict s : dictSchoolList) {
                if (s.getDictId().equals(juniorCollegeSchoolId)) {
                    userResumeExt.setJuniorCollegeSchoolName(s.getDictName());
                    break;
                }
            }
        }
        // 本科毕业院校
        if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
            for (SysDict s : dictSchoolList) {
                if (s.getDictId().equals(userResumeExt.getGraduatedId())) {
                    userResumeExt.setGraduatedName(s.getDictName());
                    break;
                }
            }
        }
        if (StringUtil.isNotBlank(userResumeExt.getDegreeId())) {//本科学位
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("UserDegree");
            for (SysDict s : dictList) {
                if (s.getDictId().equals(userResumeExt.getDegreeId())) {
                    userResumeExt.setDegreeName(s.getDictName());
                    break;
                }
            }
        }
        if (StringUtil.isNotBlank(userResumeExt.getMasterDegreeId())) {//硕士学位
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("UserDegree");
            for (SysDict s : dictList) {
                if (s.getDictId().equals(userResumeExt.getMasterDegreeId())) {
                    userResumeExt.setMasterDegreeName(s.getDictName());
                    break;
                }
            }
        }
        if (StringUtil.isNotBlank(userResumeExt.getMasterGraSchoolName())) {//硕士毕业院校
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("GraduateSchool");
            for (SysDict s : dictList) {
                if (s.getDictName().equals(userResumeExt.getMasterGraSchoolName())) {
                    userResumeExt.setMasterGraSchoolId(s.getDictId());
                    break;
                }
            }
        }
        if (StringUtil.isNotBlank(userResumeExt.getMasterGraSchoolId())) {//硕士毕业院校
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("GraduateSchool");
            for (SysDict s : dictList) {
                if (s.getDictId().equals(userResumeExt.getMasterGraSchoolId())) {
                    userResumeExt.setMasterGraSchoolName(s.getDictName());
                    break;
                }
            }
        }
        if (StringUtil.isNotBlank(userResumeExt.getDoctorDegreeId())) {//博士学位
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("UserDegree");
            for (SysDict s : dictList) {
                if (s.getDictId().equals(userResumeExt.getDoctorDegreeId())) {
                    userResumeExt.setDoctorDegreeName(s.getDictName());
                    break;
                }
            }
        }
        if (StringUtil.isNotBlank(userResumeExt.getDoctorGraSchoolName())) {//博士毕业院校
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("GraduateSchool");
            for (SysDict s : dictList) {
                if (s.getDictName().equals(userResumeExt.getDoctorGraSchoolName())) {
                    userResumeExt.setDoctorGraSchoolId(s.getDictId());
                    break;
                }
            }
        }
        if (StringUtil.isNotBlank(userResumeExt.getDoctorGraSchoolId())) {//博士毕业院校
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("GraduateSchool");
            for (SysDict s : dictList) {
                if (s.getDictId().equals(userResumeExt.getDoctorGraSchoolId())) {
                    userResumeExt.setDoctorGraSchoolName(s.getDictName());
                    break;
                }
            }
        }
        if (StringUtil.isNotBlank(userResumeExt.getMedicalHeaithOrgId())) {//医疗机构
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("MedicalHeaithOrg");
            for (SysDict s : dictList) {
                if (s.getDictId().equals(userResumeExt.getMedicalHeaithOrgId())) {
                    userResumeExt.setMedicalHeaithOrgName(s.getDictName());
                    break;
                }
            }
        }
        if (StringUtil.isNotBlank(userResumeExt.getHospitalAttrId())) {
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("HospitalAttr");
            for (SysDict s : dictList) {
                if (s.getDictId().equals(userResumeExt.getHospitalAttrId())) {
                    userResumeExt.setHospitalAttrName(s.getDictName());
                    break;
                }
            }
        }
        if (StringUtil.isNotBlank(userResumeExt.getHospitalCategoryId())) {
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("HospitalCategory");
            for (SysDict s : dictList) {
                if (s.getDictId().equals(userResumeExt.getHospitalCategoryId())) {
                    userResumeExt.setHospitalCategoryName(s.getDictName());
                    break;
                }
            }
        }
        if (StringUtil.isNotBlank(userResumeExt.getBasicHealthOrgId())) {
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("BasicHealthOrg");
            for (SysDict s : dictList) {
                if (s.getDictId().equals(userResumeExt.getBasicHealthOrgId())) {
                    userResumeExt.setBasicHealthOrgName(s.getDictName());
                    break;
                }
            }
        }
        if (StringUtil.isNotBlank(userResumeExt.getBaseAttributeId())) {
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("BaseAttribute");
            for (SysDict s : dictList) {
                if (s.getDictId().equals(userResumeExt.getBaseAttributeId())) {
                    userResumeExt.setBaseAttributeName(s.getDictName());
                    break;
                }
            }
        }
        // 毕业专业字典项
        List<SysDict> dictMajorList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("GraduateMajor");
        // 在读专业
        String readingProfession = userResumeExt.getReadingProfession();
        if (StringUtil.isNotBlank(readingProfession)) {
            for (SysDict sysDict : dictMajorList) {
                if (sysDict.getDictName().equals(readingProfession)) {
                    userResumeExt.setReadingProfessionId(sysDict.getDictId());
                    break;
                }
            }
        }
        String readingProfessionId = userResumeExt.getReadingProfessionId();
        if (StringUtil.isNotBlank(readingProfessionId)) {
            for (SysDict sysDict : dictMajorList) {
                if (sysDict.getDictId().equals(readingProfessionId)) {
                    userResumeExt.setReadingProfession(sysDict.getDictName());
                    break;
                }
            }
        }
        // 大专毕业专业
        String juniorCollegeProfession = userResumeExt.getJuniorCollegeProfession();
        if (StringUtil.isNotBlank(juniorCollegeProfession)) {
            for (SysDict sysDict : dictMajorList) {
                if (sysDict.getDictName().equals(juniorCollegeProfession)) {
                    userResumeExt.setJuniorCollegeProfessionId(sysDict.getDictId());
                    break;
                }
            }
        }
        // 本科毕业院校
        String specialized = userResumeExt.getSpecialized();
        if (StringUtil.isNotBlank(specialized)) {
            for (SysDict sysDict : dictMajorList) {
                if (sysDict.getDictName().equals(specialized)) {
                    userResumeExt.setSpecializedId(sysDict.getDictId());
                    break;
                }
            }
        }
        // 大专毕业专业
        String juniorCollegeProfessionId = userResumeExt.getJuniorCollegeProfessionId();
        if (StringUtil.isNotBlank(juniorCollegeProfessionId)) {
            for (SysDict sysDict : dictMajorList) {
                if (sysDict.getDictId().equals(juniorCollegeProfessionId)) {
                    userResumeExt.setJuniorCollegeProfession(sysDict.getDictName());
                    break;
                }
            }
        }
        // 本科毕业院校
        String specializedId = userResumeExt.getSpecializedId();
        if (StringUtil.isNotBlank(specializedId)) {
            for (SysDict sysDict : dictMajorList) {
                if (sysDict.getDictId().equals(specializedId)) {
                    userResumeExt.setSpecialized(sysDict.getDictName());
                    break;
                }
            }
        }
        // 硕士毕业专业
        String masterMajor = userResumeExt.getMasterMajor();
        if (StringUtil.isNotBlank(masterMajor)) {
            for (SysDict sysDict : dictMajorList) {
                if (sysDict.getDictName().equals(masterMajor)) {
                    userResumeExt.setMasterMajorId(sysDict.getDictId());
                    break;
                }
            }

        }
        // 硕士毕业专业
        String masterMajorId = userResumeExt.getMasterMajorId();
        if (StringUtil.isNotBlank(masterMajorId)) {
            for (SysDict sysDict : dictMajorList) {
                if (sysDict.getDictId().equals(masterMajorId)) {
                    userResumeExt.setMasterMajor(sysDict.getDictName());
                    break;
                }
            }

        }
        // 博士毕业专业
        String doctorMajor = userResumeExt.getDoctorMajor();
        if (StringUtil.isNotBlank(doctorMajor)) {
            for (SysDict sysDict : dictMajorList) {
                if (sysDict.getDictName().equals(doctorMajor)) {
                    userResumeExt.setDoctorMajorId(sysDict.getDictId());
                    break;
                }
            }

        }
        // 博士毕业专业
        String doctorMajorId = userResumeExt.getDoctorMajorId();
        if (StringUtil.isNotBlank(doctorMajorId)) {
            for (SysDict sysDict : dictMajorList) {
                if (sysDict.getDictId().equals(doctorMajorId)) {
                    userResumeExt.setDoctorMajor(sysDict.getDictName());
                    break;
                }
            }

        }
        String xmlContent = JaxbUtil.convertToXml(userResumeExt);


        pubUserResume.setUserResume(xmlContent);
        savePubUserResume(user, pubUserResume);
        //毕业院校
        if (StringUtil.isNotBlank(doctor.getGraduatedName())) {
            Map<String, List<SysDict>> sysListDictMap = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap;
            List<SysDict> dictList = sysListDictMap.get("GraduateSchool");
            for (SysDict s : dictList) {
                if (s.getDictName().equals(doctor.getGraduatedName())) {
                    doctor.setGraduatedId(s.getDictId());
                }
            }
        }
//		else{
//			doctor.setGraduatedId("");
//		}
        //人员类型
        if (StringUtil.isNotBlank(doctor.getDoctorTypeId())) {
            doctor.setDoctorTypeName(com.pinde.core.common.enums.ResDocTypeEnum.getNameById(doctor.getDoctorTypeId()));
        } else {
            doctor.setDoctorTypeName("");
        }
        //是否是应届生
        if (StringUtil.isNotBlank(doctor.getIsYearGraduate())) {
            doctor.setIsYearGraduate(doctor.getIsYearGraduate());
        } else {
            doctor.setIsYearGraduate("");
        }
        //单位等级
        if (StringUtil.isNotBlank(doctor.getWorkOrgLevelId())) {
            doctor.setWorkOrgLevelId(doctor.getWorkOrgLevelId());
            doctor.setWorkOrgLevelName(com.pinde.core.common.enums.DictTypeEnum.getDictName(com.pinde.core.common.enums.DictTypeEnum.BaseLevel, doctor.getWorkOrgLevelId()));
        } else {
            doctor.setWorkOrgLevelId("");
        }
        //学位类型
        if (StringUtil.isNotBlank(doctor.getDegreeCategoryId())) {
            doctor.setDegreeCategoryName(com.pinde.core.common.enums.JsResDegreeCategoryEnum.getNameById(doctor.getDegreeCategoryId()));
        } else {
            doctor.setDegreeCategoryName("");
        }
        //派送单位或者学校
        if (StringUtil.isNotBlank(doctor.getWorkOrgName())) {
            if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())) {
                List<SysDict> sysDictList = searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
                if (sysDictList != null && !sysDictList.isEmpty()) {
                    for (SysDict dict : sysDictList) {
                        if (dict.getDictName().equals(doctor.getWorkOrgName())) {
                            doctor.setWorkOrgId(dict.getDictId());
                        }
                    }
                }
            }
            if (com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId()) || com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
                List<SysDict> sysDictList = searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.WorkOrg.getId());
                if (sysDictList != null && !sysDictList.isEmpty()) {
                    for (SysDict dict : sysDictList) {
                        if (dict.getDictName().equals(doctor.getWorkOrgName())) {
                            doctor.setWorkOrgId(dict.getDictId());
                        }
                    }
                }
            }
        }
        //派送单位或者学校
        if (StringUtil.isNotBlank(doctor.getWorkOrgId())) {
            if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())) {
                List<SysDict> sysDictList = searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
                if (sysDictList != null && !sysDictList.isEmpty()) {
                    for (SysDict dict : sysDictList) {
                        if (dict.getDictId().equals(doctor.getWorkOrgId())) {
                            doctor.setWorkOrgName(dict.getDictName());
                        }
                    }
                }
            }
            if (com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId()) || com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
                List<SysDict> sysDictList = searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.WorkOrg.getId());
                if (sysDictList != null && !sysDictList.isEmpty()) {
                    for (SysDict dict : sysDictList) {
                        if (dict.getDictId().equals(doctor.getWorkOrgId())) {
                            doctor.setWorkOrgName(dict.getDictName());
                        }
                    }
                }
            }
        }
        doctor.setDoctorName(user.getUserName());
        String doctorStatusId = doctor.getDoctorStatusId();
        if (StringUtil.isBlank(doctorStatusId)) {
            // 现网bug：学员在任何状态下，编辑信息，状态会被重置未 未提交，增加判断 状态为空 默认为未提交状态
            doctor.setDoctorStatusId("NotSubmit");
            doctor.setDoctorStatusName("未提交");
        }
        return editDoctor(doctor, user);
    }


    public int saveUser(SysUser user) {
        String userFlow = user.getUserFlow();
        if (StringUtil.isNotBlank(userFlow)) {
            user.setModifyTime(DateUtil.getCurrDateTime());
            if (StringUtil.isNotBlank(user.getStatusId())) {
                user.setStatusDesc(UserStatusEnum.getNameById(user.getStatusId()));
            }
            int ret = sysUserMapper.updateByPrimaryKeySelective(user);
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("sys_weixin_qiye_flag"))) {
                //全部同步后saveUser改称update
                user = sysUserMapper.selectByPrimaryKey(userFlow);
                boolean result = false;
                if (StringUtil.isNotBlank(InitConfig.getSysCfg("sys_weixin_qiye_dept_id"))) {
                    result = WeixinQiYeUtil.saveUser(InitConfig.getSysCfg("sys_weixin_qiye_corp_id"), InitConfig.getSysCfg("sys_weixin_qiye_secret"), InitConfig.getSysCfg("sys_weixin_qiye_dept_id"), user);
                } else {
                    if (StringUtil.isNotBlank(user.getDeptFlow())) {
                        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(user.getDeptFlow());
                        String deptCode = null;
                        if (sysDept != null) {
                            deptCode = sysDept.getDeptCode();
                        }
                        result = WeixinQiYeUtil.saveUser(InitConfig.getSysCfg("sys_weixin_qiye_corp_id"), InitConfig.getSysCfg("sys_weixin_qiye_secret"), deptCode, user);
                    }
                }
            }
            return ret;
        } else {
            user.setUserFlow(PkUtil.getUUID());
            user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), PasswordHelper.encryptPassword("pinde", "@teh666")));
            if (StringUtil.isNotBlank(user.getStatusId())) {
                user.setStatusDesc(UserStatusEnum.getNameById(user.getStatusId()));
            } else {
                user.setStatusId(UserStatusEnum.Activated.getId());
                user.setStatusDesc(UserStatusEnum.Activated.getName());
            }
            if (StringUtil.isNotBlank(user.getResTrainingSpeId())) {
                user.setStatusId(UserStatusEnum.Locked.getId());
                user.setStatusDesc(UserStatusEnum.Locked.getName());
            }
            if (StringUtil.isBlank(user.getCretTypeId())) {
                //默认证件类型是身份证
                user.setCretTypeId(CertificateTypeEnum.Shenfenzheng.getId());
                user.setCretTypeName(CertificateTypeEnum.Shenfenzheng.getName());
            } else {
                try {
                    user.setCretTypeName(CertificateTypeEnum.getNameById(user.getCretTypeId()));
                } catch (Exception e) {
                    logger.error("", e);
                } finally {
                    user.setCretTypeId(CertificateTypeEnum.Shenfenzheng.getId());
                    user.setCretTypeName(CertificateTypeEnum.Shenfenzheng.getName());
                }
            }
            user.setCreateTime(DateUtil.getCurrDateTime());

            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("sys_weixin_qiye_flag"))) {
                boolean result = false;
                if (StringUtil.isNotBlank(InitConfig.getSysCfg("sys_weixin_qiye_dept_id"))) {
                    result = WeixinQiYeUtil.createUser(InitConfig.getSysCfg("sys_weixin_qiye_corp_id"), InitConfig.getSysCfg("sys_weixin_qiye_secret"), InitConfig.getSysCfg("sys_weixin_qiye_dept_id"), user);
                } else {
                    if (StringUtil.isNotBlank(user.getDeptFlow())) {
                        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(user.getDeptFlow());
                        String deptCode = null;
                        if (sysDept != null) {
                            deptCode = sysDept.getDeptCode();
                        }
                        result = WeixinQiYeUtil.createUser(InitConfig.getSysCfg("sys_weixin_qiye_corp_id"), InitConfig.getSysCfg("sys_weixin_qiye_secret"), deptCode, user);
                    }
                }
            }
            int ret = sysUserMapper.insert(user);
            return ret;
        }

    }


    public int editDoctor(ResDoctor doctor, SysUser sysUser) {
        if (StringUtil.isNotBlank(doctor.getDoctorFlow())) {
            doctor.setModifyTime(DateUtil.getCurrDateTime());
            return doctorMapper.updateByPrimaryKeySelective(doctor);
        } else {
            if (sysUser != null) {
                doctor.setDoctorFlow(sysUser.getUserFlow());
                doctor.setDoctorName(sysUser.getUserName());
            }
            doctor.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            doctor.setCreateTime(DateUtil.getCurrDateTime());
            return doctorMapper.insert(doctor);
        }
    }

    @Override
    public List<com.pinde.core.model.ResDoctorRecruit> readDoctorRecruits(com.pinde.core.model.ResDoctorRecruit recruit) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(recruit.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(recruit.getOrgFlow());
        }
        if (StringUtil.isNotBlank(recruit.getDoctorFlow())) {
            criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
        }
        if (StringUtil.isNotBlank(recruit.getSpeId())) {
            criteria.andSpeIdEqualTo(recruit.getSpeId());
        }
        if (StringUtil.isNotBlank(recruit.getRecruitYear())) {
            criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
        }
        if (StringUtil.isNotBlank(recruit.getRecruitTypeId())) {
            criteria.andRecruitTypeIdEqualTo(recruit.getRecruitTypeId());
        }
        if (StringUtil.isNotBlank(recruit.getRetestFlag())) {
            criteria.andRetestFlagEqualTo(recruit.getRetestFlag());
        }
        if (StringUtil.isNotBlank(recruit.getRecruitFlag())) {
            criteria.andRecruitFlagEqualTo(recruit.getRecruitFlag());
        }
        if (StringUtil.isNotBlank(recruit.getConfirmFlag())) {
            criteria.andConfirmFlagEqualTo(recruit.getConfirmFlag());
        }
        if (StringUtil.isNotBlank(recruit.getAuditStatusId())) {
            criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
        }
        if (StringUtil.isNotBlank(recruit.getSessionNumber())) {
            criteria.andSessionNumberEqualTo(recruit.getSessionNumber());
        }
        if (StringUtil.isNotBlank(recruit.getGraduationYear())) {
            criteria.andGraduationYearEqualTo(recruit.getGraduationYear());
        }
        return doctorRecruitMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> searchAssignInfoListNew(Map<String, Object> paramMap) {
        return recruitExtMapper.searchAssignInfoListNew(paramMap);
    }

    @Override
    public List<Map<String, String>> searchAssignOrgSpeListNew(Map<String, String> param) {
        return recruitExtMapper.searchAssignOrgSpeListNew(param);
    }


    @Override
    public String doctorSignupFlagNew(String userFlow) {
        SysUser sysUser = readSysUser(userFlow);
        // 查询医师信息
        ResDoctor resDoctor = doctorMapper.selectByPrimaryKey(sysUser.getUserFlow());
        if (null == resDoctor) {
            return "请先完善报考信息";
        }
        // 查询招录信息
        List<com.pinde.core.model.ResDoctorRecruit> doctorRecruitList = recruitExtMapper.getDoctorRecruitInfo(sysUser.getUserFlow());
        if (null != doctorRecruitList && 0 < doctorRecruitList.size()) {
            ResDoctorRecruit doctorRecruit = doctorRecruitList.get(0);
            //有报送记录只能走报送通道
            if (StringUtil.isNotEmpty(doctorRecruit.getSignupWay()) && "DoctorSend".equals(doctorRecruit.getSignupWay())) {
                return "当前在" + doctorRecruit.getOrgName() + "已有报送记录";
            } else {
                if ("Auditing".equals(doctorRecruit.getDoctorStatusId()) || "OrgAuditing".equals(doctorRecruit.getDoctorStatusId()) || ("Passed".equals(doctorRecruit.getDoctorStatusId()))
                        || ("Passed".equals(doctorRecruit.getAuditStatusId()) && "20".equals(resDoctor.getDoctorStatusId()))
                        || "20".equals(doctorRecruit.getDoctorStatusId())) {
                    return "当前在" + doctorRecruit.getOrgName() + "已有报名记录";
                }
            }
        }
        return "";
    }

    @Override
    public List<Map<String, String>> searchDoctorSpe() {
        return doctorExtMapper.searchDoctorSpe();
    }

    @Override
    public ResOrgSpeAssign getResOrgSpeAssignInfo(String assignFlow) {
        return speAssignMapper.selectByPrimaryKey(assignFlow);
    }

    @Override
    public ResDoctorRecruit readResDoctorRecruit(String recruitFlow) {
        if (StringUtil.isNotBlank(recruitFlow)) {
            return doctorRecruitMapper.selectByPrimaryKey(recruitFlow);
        }
        return null;
    }

    @Override
    public List<SysOrg> searchOrgByExample(SysOrgExample example) {
        return orgMapper.selectByExample(example);
    }

    @Override
    public List<ResOrgSpeAssign> searchSpeAssign(String orgFlow,
                                                 String assignYear) {
        ResOrgSpeAssignExample example = new ResOrgSpeAssignExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow).andAssignYearEqualTo(assignYear);
        return speAssignMapper.selectByExample(example);
    }

    @Override
    public int findSignupCount(String doctorFlow, String year) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        example.createCriteria().andDoctorFlowEqualTo(doctorFlow).andRecruitYearEqualTo(year)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
                .andAuditStatusIdNotEqualTo("NotPassed");
        return doctorRecruitMapper.countByExample(example);
    }

    @Override
    public List<ResOrgSpe> searchResOrgSpeList(ResOrgSpe resOrgSpe) {
        ResOrgSpeExample example = new ResOrgSpeExample();
        ResOrgSpeExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(resOrgSpe.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(resOrgSpe.getOrgFlow());
        }
        if (StringUtil.isNotBlank(resOrgSpe.getOrgName())) {
            criteria.andOrgNameLike(resOrgSpe.getOrgName());
        }
        if (StringUtil.isNotBlank(resOrgSpe.getSpeTypeId())) {
            criteria.andSpeTypeIdEqualTo(resOrgSpe.getSpeTypeId());
        }
        if (StringUtil.isNotBlank(resOrgSpe.getSpeId())) {
            criteria.andSpeIdEqualTo(resOrgSpe.getSpeId());
        }
        if (StringUtil.isNotBlank(resOrgSpe.getRecordStatus())) {
            criteria.andRecordStatusEqualTo(resOrgSpe.getRecordStatus());
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return resOrgSpeMapper.selectByExample(example);
    }

    @Override
    public int editDoctor(ResDoctor doctor) {
        if (doctor != null) {
            if (StringUtil.isNotBlank(doctor.getDoctorFlow())) {
                doctor.setModifyTime(DateUtil.getCurrDateTime());
                return doctorMapper.updateByPrimaryKeySelective(doctor);
            } else {
                doctor.setDoctorFlow(PkUtil.getUUID());
                return onlySaveResDoctor(doctor);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    public int onlySaveResDoctor(ResDoctor resDoctor) {
        resDoctor.setCreateTime(DateUtil.getCurrDateTime());
        resDoctor.setSelDeptFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
        resDoctor.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
        return doctorMapper.insertSelective(resDoctor);
    }

    @Override
    public List<ResDocotrDelayTeturn> searchInfo(ResDocotrDelayTeturn resDocotrDelayTeturn, List<String> orgFlowList, List<String> flags
            , List<String> docTypeList) {
        ResDocotrDelayTeturnExample example = new ResDocotrDelayTeturnExample();
        ResDocotrDelayTeturnExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (resDocotrDelayTeturn != null) {
            if (StringUtil.isNotBlank(resDocotrDelayTeturn.getOrgFlow())) {
                criteria.andOrgFlowEqualTo(resDocotrDelayTeturn.getOrgFlow());
            }
            if (StringUtil.isNotBlank(resDocotrDelayTeturn.getOrgName())) {
                criteria.andOrgNameLike("%" + resDocotrDelayTeturn.getOrgName() + "%");
            }
            if (StringUtil.isNotBlank(resDocotrDelayTeturn.getDoctorFlow())) {
                criteria.andDoctorFlowEqualTo(resDocotrDelayTeturn.getDoctorFlow());
            }
            if (StringUtil.isNotBlank(resDocotrDelayTeturn.getDoctorName())) {
                criteria.andDoctorNameLike("%" + resDocotrDelayTeturn.getDoctorName() + "%");
            }
            if (StringUtil.isNotBlank(resDocotrDelayTeturn.getSessionNumber())) {
                criteria.andSessionNumberEqualTo(resDocotrDelayTeturn.getSessionNumber());
            }
            if (StringUtil.isNotBlank(resDocotrDelayTeturn.getTrainingTypeId())) {
                criteria.andTrainingTypeIdEqualTo(resDocotrDelayTeturn.getTrainingTypeId());
            }
            if (StringUtil.isNotBlank(resDocotrDelayTeturn.getTrainingSpeId())) {
                criteria.andTrainingSpeIdEqualTo(resDocotrDelayTeturn.getTrainingSpeId());
            }
            if (StringUtil.isNotBlank(resDocotrDelayTeturn.getTrainingSpeName())) {
                criteria.andTrainingSpeNameLike("%" + resDocotrDelayTeturn.getTrainingSpeName() + "%");
            }
            if (StringUtil.isNotBlank(resDocotrDelayTeturn.getPolicyId())) {
                criteria.andPolicyIdEqualTo(resDocotrDelayTeturn.getPolicyId());
            }
            if (StringUtil.isNotBlank(resDocotrDelayTeturn.getDoctorTypeId())) {
                criteria.andDoctorTypeIdEqualTo(resDocotrDelayTeturn.getDoctorTypeId());
            }
            if (StringUtil.isNotBlank(resDocotrDelayTeturn.getReasonId())) {
                criteria.andReasonIdEqualTo(resDocotrDelayTeturn.getReasonId());
            }
            if (StringUtil.isNotBlank(resDocotrDelayTeturn.getAuditStatusId())) {
                criteria.andAuditStatusIdEqualTo(resDocotrDelayTeturn.getAuditStatusId());
            }
            if (flags != null && flags.size() > 0) {
                criteria.andAuditStatusIdIn(flags);
            }
            if (StringUtil.isNotBlank(resDocotrDelayTeturn.getTypeId())) {
                criteria.andTypeIdEqualTo(resDocotrDelayTeturn.getTypeId());
            }
            if (docTypeList != null && docTypeList.size() > 0) {
                criteria.andDoctorTypeIdIn(docTypeList);
            }
        }
        if (orgFlowList != null && orgFlowList.size() > 0) {
            criteria.andOrgFlowIn(orgFlowList);
        }
        example.setOrderByClause("create_time desc");
        return resDocotrDelayTeturnExtMapper.searchInfoNew(example);
    }

    @Override
    public List<com.pinde.core.model.ResDoctorRecruit> searchRecruitList(String doctorFlow) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow);
        example.setOrderByClause("CREATE_TIME DESC");
        return doctorRecruitMapper.selectByExample(example);
    }

    @Override
    public int saveDoctorRecruit(ResDoctorRecruitWithBLOBs docRecWithBLOBs) {
        if (StringUtil.isNotBlank(docRecWithBLOBs.getRecruitFlow())) {
            docRecWithBLOBs.setModifyTime(DateUtil.getCurrDateTime());
            return doctorRecruitMapper.updateByPrimaryKeySelective(docRecWithBLOBs);
        } else {
            docRecWithBLOBs.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            docRecWithBLOBs.setRecruitFlow(PkUtil.getUUID());
            docRecWithBLOBs.setCreateTime(DateUtil.getCurrDateTime());
            return doctorRecruitMapper.insert(docRecWithBLOBs);
        }
    }

    @Override
    public List<JsResDoctorRecruitExt> resDoctorRecruitExtList3New(ResDoctorRecruit resDoctorRecruit) {
        Map<String, Object> doctorRecruitMap = new HashMap<String, Object>();
        doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
        List<JsResDoctorRecruitExt> doctorRecruitList = jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList3New(doctorRecruitMap);
        return doctorRecruitList;
    }

    @Override
    public List<SchRotation> searchOrgStandardRotation(SchRotation rotation) {
        SchRotationExample example = new SchRotationExample();
        SchRotationExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

        if (StringUtil.isNotBlank(rotation.getDoctorCategoryId())) {
            criteria.andDoctorCategoryIdEqualTo(rotation.getDoctorCategoryId());
        }
        if (StringUtil.isNotBlank(rotation.getSpeId())) {
            criteria.andSpeIdEqualTo(rotation.getSpeId());
        }
        if (StringUtil.isNotBlank(rotation.getPublishFlag())) {
            criteria.andPublishFlagEqualTo(rotation.getPublishFlag());
        }
        if (StringUtil.isNotBlank(rotation.getWorkOrgId())) {
            criteria.andWorkOrgIdEqualTo(rotation.getWorkOrgId());
        }
        if (StringUtil.isNotBlank(rotation.getRotationName())) {
            criteria.andRotationNameLike("%" + rotation.getRotationName() + "%");
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return rotationMapper.selectByExample(example);
    }

    @Override
    public List<SysOrg> searchOrgNotJointOrg(SysOrg sysOrg, List<String> orgLevelList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sysOrg", sysOrg);
        paramMap.put("orgLevelList", orgLevelList);
        return orgExtMapper.searchOrgNotJointOrg(paramMap);
    }

    @Override
    public List<SysOrg> searchOrg(SysOrg sysorg) {
        SysOrgExample example = new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria();//.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(sysorg.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(sysorg.getOrgFlow());
        }
        if (StringUtil.isNotBlank(sysorg.getOrgCode())) {
            criteria.andOrgCodeEqualTo(sysorg.getOrgCode());
        }
        if (StringUtil.isNotBlank(sysorg.getOrgName())) {
            criteria.andOrgNameLike("%" + sysorg.getOrgName() + "%");
        }
        if (StringUtil.isNotBlank(sysorg.getOrgProvId())) {
            criteria.andOrgProvIdEqualTo(sysorg.getOrgProvId());
        }
        if (StringUtil.isNotBlank(sysorg.getRecordStatus())) {
            criteria.andRecordStatusEqualTo(sysorg.getRecordStatus());
        }
        if (StringUtil.isNotBlank(sysorg.getChargeOrgFlow())) {
            criteria.andChargeOrgFlowEqualTo(sysorg.getChargeOrgFlow());
        }
        if (StringUtil.isNotBlank(sysorg.getOrgCityId())) {
            criteria.andOrgCityIdEqualTo(sysorg.getOrgCityId());
        }
        if (StringUtil.isNotBlank(sysorg.getOrgAreaId())) {
            criteria.andOrgAreaIdEqualTo(sysorg.getOrgAreaId());
        }
        if (StringUtil.isNotBlank(sysorg.getOrgTypeId())) {
            criteria.andOrgTypeIdEqualTo(sysorg.getOrgTypeId());
        }
        if (StringUtil.isNotBlank(sysorg.getOrgLevelId())) {
            criteria.andOrgLevelIdEqualTo(sysorg.getOrgLevelId());
        }
        if (StringUtil.isNotBlank(sysorg.getIsSecondFlag())) {
            criteria.andIsSecondFlagEqualTo(sysorg.getIsSecondFlag());
        }
        if (StringUtil.isNotBlank(sysorg.getIsExamOrg())) {
            criteria.andIsExamOrgEqualTo(sysorg.getIsExamOrg());
        }
        example.setOrderByClause(" ORDINAL,ORG_CODE,RECORD_STATUS DESC,ORG_FLOW desc");
        return orgMapper.selectByExample(example);
    }

    @Override
    public List<ResOrgSpe> searchResOrgSpeListNew(ResOrgSpe resOrgSpe, String trainCategoryTypeId, String speFlag) {
        ResOrgSpeExample example = new ResOrgSpeExample();
        ResOrgSpeExample.Criteria criteria = example.createCriteria();
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(speFlag)) {
            criteria.andSpeNameEqualTo("全科");
        }
        if (StringUtil.isNotBlank(trainCategoryTypeId) && !"undefined".equals(trainCategoryTypeId)) {
            criteria.andSpeTypeIdEqualTo(trainCategoryTypeId);
        }
        if (StringUtil.isNotBlank(resOrgSpe.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(resOrgSpe.getOrgFlow());
        }
        if (StringUtil.isNotBlank(resOrgSpe.getOrgName())) {
            criteria.andOrgNameLike(resOrgSpe.getOrgName());
        }
        if (StringUtil.isNotBlank(resOrgSpe.getSpeTypeId())) {
            criteria.andSpeTypeIdEqualTo(resOrgSpe.getSpeTypeId());
        }
        if (StringUtil.isNotBlank(resOrgSpe.getSpeId())) {
            criteria.andSpeIdEqualTo(resOrgSpe.getSpeId());
        }
        if (StringUtil.isNotBlank(resOrgSpe.getRecordStatus())) {
            criteria.andRecordStatusEqualTo(resOrgSpe.getRecordStatus());
        }
        return resOrgSpeMapper.selectByExample(example);

    }

    @Override
    public List<ResJointOrg> searchResJointByOrgFlow(String orgFlow) {
        ResJointOrgExample example = new ResJointOrgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow);
        return resJointOrgMapper.selectByExample(example);
    }

    @Override
    public int editResDoctorRecruit(ResDoctorRecruitWithBLOBs recWithBLOBs, ResDoctorRecruitWithBLOBs prevDocRec) {
        //医师状态
        if (StringUtil.isNotBlank(recWithBLOBs.getDoctorStatusId())) {
            recWithBLOBs.setDoctorStatusName(com.pinde.core.common.enums.DictTypeEnum.DoctorStatus.getDictNameById(recWithBLOBs.getDoctorStatusId()));
        } else {
            recWithBLOBs.setDoctorStatusName("");
        }
        //医师走向
        if (StringUtil.isNotBlank(recWithBLOBs.getDoctorStrikeId())) {
            recWithBLOBs.setDoctorStrikeName(com.pinde.core.common.enums.DictTypeEnum.DoctorStrike.getDictNameById(recWithBLOBs.getDoctorStrikeId()));
        } else {
            recWithBLOBs.setDoctorStrikeName("");
        }
        if (StringUtil.isNotBlank(recWithBLOBs.getCurrDegreeCategoryId())) {
            recWithBLOBs.setCurrDegreeCategoryName(com.pinde.core.common.enums.JsResDegreeCategoryEnum.getNameById(recWithBLOBs.getCurrDegreeCategoryId()));
        }
        recWithBLOBs.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getId());
        recWithBLOBs.setAuditStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getName());
        String doctorFlow = recWithBLOBs.getDoctorFlow();
        //结业审核年份
        int year = 0;
        if (StringUtil.isNotBlank(recWithBLOBs.getSessionNumber()) && StringUtil.isNotBlank(recWithBLOBs.getTrainYear())) {
            int num = 0;
            if (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(recWithBLOBs.getTrainYear())) {
                num = 1;
            }
            if (com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(recWithBLOBs.getTrainYear())) {
                num = 2;
            }
            if (com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId().equals(recWithBLOBs.getTrainYear())) {
                num = 3;
            }
            year = Integer.parseInt(recWithBLOBs.getSessionNumber()) + num;
            recWithBLOBs.setGraduationYear(year + "");
        }
        //其中已审核通过
        ResDoctorRecruit exitRec = new ResDoctorRecruit();
        exitRec.setDoctorFlow(doctorFlow);
        exitRec.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        exitRec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

        //页面字段被去除   默认
        exitRec.setDoctorStatusId("Auditing");
        exitRec.setDoctorStatusName("待审核");

        List<com.pinde.core.model.ResDoctorRecruit> passedRecruitList = searchResDoctorRecruitList(exitRec, "CREATE_TIME");
        boolean firstIsWMSecond = false;//首条是否为二阶段(有自动生成一阶段)
        if (passedRecruitList != null && !passedRecruitList.isEmpty()) {
            for (ResDoctorRecruit rec : passedRecruitList) {
                if (StringUtil.isBlank(rec.getSpeId())) {
                    firstIsWMSecond = true;
                    break;
                }
            }
        }
        //当前为二阶段
        if (com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(recWithBLOBs.getCatSpeId())) {
            if (passedRecruitList.isEmpty()) {//无审核通过
            }
        } else {//非二阶段
            if (passedRecruitList.size() == 1 && firstIsWMSecond) {//首条记录为二阶段  修改为非二阶段  ==>删除自动生成的一阶段
                ResDoctorRecruitWithBLOBs deleWMFirst = new ResDoctorRecruitWithBLOBs();
                deleWMFirst.setRecruitFlow(passedRecruitList.get(0).getRecruitFlow());
                deleWMFirst.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
                saveDoctorRecruit(deleWMFirst);
            }
        }
        //上一阶段：结业证书、证书编号
        if (StringUtil.isNotBlank(prevDocRec.getRecruitFlow())) {
            saveDoctorRecruit(prevDocRec);
        }
        return saveDoctorRecruit(recWithBLOBs);
    }

    @Override
    public JsresRecruitDocInfo readRecruit(String recruitFlow) {
        return recruitDocInfoMapper.selectByPrimaryKey(recruitFlow);
    }

    @Override
    public ResDocotrDelayTeturn findTeturnInfo(String recruitFlow) {
        ResDocotrDelayTeturnExample example = new ResDocotrDelayTeturnExample();
        ResDocotrDelayTeturnExample.Criteria criteria = example.createCriteria()
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andTypeIdEqualTo(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId())
                .andRecruitFlowEqualTo(recruitFlow).andAuditStatusIdEqualTo("2");
        example.setOrderByClause("CREATE_TIME DESC");
        List<ResDocotrDelayTeturn> list = resDocotrDelayTeturnMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<DoctorAuth> searchAuthsByOperUserFlow(String operUserFlow) {
        if (StringUtil.isNotBlank(operUserFlow)) {
            DoctorAuthExample example = new DoctorAuthExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andOperUserFlowEqualTo(operUserFlow);
            List<DoctorAuth> doctorAuths = doctorAuthMapper.selectByExample(example);
            return doctorAuths;
        }
        return null;
    }

    @Override
    public List<ResDoctorOrgHistory> searchWaitingChangeOrgHistoryList(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList) {
        ResDoctorOrgHistoryExample example = new ResDoctorOrgHistoryExample();
        ResDoctorOrgHistoryExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        //Criteria criteria2 =  example.createCriteria();
        if (StringUtil.isNotBlank(docOrgHistory.getDoctorFlow())) {
            criteria.andDoctorFlowEqualTo(docOrgHistory.getDoctorFlow());
        }
        if (changeStatusIdList != null && !changeStatusIdList.isEmpty()) {
            criteria.andChangeStatusIdIn(changeStatusIdList);
        }
        if (StringUtil.isNotBlank(docOrgHistory.getChangeTypeId())) {
            criteria.andChangeTypeIdEqualTo(docOrgHistory.getChangeTypeId());
        }
        if (StringUtil.isNotBlank(docOrgHistory.getChangeTypeName())) {
            criteria.andChangeTypeNameEqualTo(docOrgHistory.getChangeTypeName());
        }
        if (StringUtil.isNotBlank(docOrgHistory.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(docOrgHistory.getOrgFlow());
        }
        if (StringUtil.isNotBlank(docOrgHistory.getHistoryOrgFlow())) {
            criteria.andHistoryOrgFlowEqualTo(docOrgHistory.getHistoryOrgFlow());
        }
        if (StringUtil.isNotBlank(docOrgHistory.getRecruitFlow())) {
            criteria.andRecruitFlowEqualTo(docOrgHistory.getRecruitFlow());
        }
		/*criteria2.andChangeStatusIdIsNull();
		example.or(criteria2);*/
        example.setOrderByClause("CREATE_TIME DESC");
        return docOrgHistoryMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public ResDoctorRecruitWithBLOBs readRecruitNew(String recruitFlow) {
        if (StringUtil.isNotBlank(recruitFlow)) {
            return doctorRecruitMapper.selectByPrimaryKey(recruitFlow);
        }
        return null;
    }

    @Override
    public ResDoctorReduction findReductionByRecruitFlow(String recruitFlow) {
        ResDoctorReductionExample example = new ResDoctorReductionExample();
        ResDoctorReductionExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecruitFlowEqualTo(recruitFlow);
        List<ResDoctorReduction> reductionList = reductionMapper.selectByExample(example);
        if (reductionList != null && reductionList.size() > 0) {
            return reductionList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String selectPowerCfg(Map<String, String> paramMap) {
        List<JsresPowerCfg> cfgList = jsResPowerCfgExtMapper.selectPowerCfg(paramMap);
        if (cfgList != null && cfgList.size() > 0) {
            return com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y;
        }
        return com.pinde.core.common.GlobalConstant.FLAG_N;
    }

    @Override
    public VerificationCodeRecord selectPhone(String phone) {
        VerificationCodeRecordExample example = new VerificationCodeRecordExample();
        if (StringUtil.isNotBlank(phone)) {
            example.createCriteria().andPhoneEqualTo(phone);
        }
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andAppSendEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        List<VerificationCodeRecord> recordList = verificationCodeRecordMapper.selectByExample(example);
        if (recordList != null && recordList.size() > 0) {
            return recordList.get(0);
        }
        return null;
    }

    @Override
    public int deleteRecordFlow(String recordFlow) {
        if (StringUtil.isNotBlank(recordFlow)) {
            VerificationCodeRecordExample example = new VerificationCodeRecordExample();
            example.createCriteria().andRecordFlowEqualTo(recordFlow);
            return verificationCodeRecordMapper.deleteByExample(example);
        }
        return 0;
    }

    @Override
    public int updateRecordFlow(VerificationCodeRecord record) {
        if (StringUtil.isNotBlank(record.getRecordFlow())) {
            VerificationCodeRecordExample example = new VerificationCodeRecordExample();
            example.createCriteria().andRecordFlowEqualTo(record.getRecordFlow());
            return verificationCodeRecordMapper.updateByExample(record, example);
        }
        return 0;
    }

    @Override
    public String searchRotationDeptName(String deptFlow) {
        return schRotationDeptExtMapper.searchRotationDeptName(deptFlow);
    }

    @Override
    public SchRotationDept readSchRotationDept(String deptFlow) {
        return schRotationDeptMapper.selectByPrimaryKey(deptFlow);
    }

    @Override
    public ResDoctorSchProcess readSchProcess(String processFlow) {
        return processMapper.selectByPrimaryKey(processFlow);
    }

    @Override
    public ResDoctorSchProcess readSchProcessByResultFlow(String resultFlow) {
        ResDoctorSchProcessExample resDoctorSchProcessExample = new ResDoctorSchProcessExample();
        resDoctorSchProcessExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andSchResultFlowEqualTo(resultFlow);
        List<ResDoctorSchProcess> resDoctorSchProcesses = processMapper.selectByExample(resDoctorSchProcessExample);
        if (CollectionUtils.isEmpty(resDoctorSchProcesses)) {
            return null;
        }
        return resDoctorSchProcesses.get(0);
    }

    @Override
    public List<ResOutOfficeLock> readResOutOfficeLock(String userFlow, String auditStatus) {
        ResOutOfficeLockExample resOutOfficeLockExample = new ResOutOfficeLockExample();
        ResOutOfficeLockExample.Criteria criteria = resOutOfficeLockExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andUserFlowEqualTo(userFlow);
        if (StringUtil.isNotEmpty(auditStatus)) {
            criteria.andAuditStatusIdEqualTo(auditStatus);
        }
        resOutOfficeLockExample.setOrderByClause("CREATE_TIME DESC");
        return outOfficeLockMapper.selectByExample(resOutOfficeLockExample);
    }

    @Override
    public int deleteLeaveImage(String fileFlow) {
        return pubFileMapper.deleteByPrimaryKey(fileFlow);
    }

    @Override
    public SysUser readSysUserByOpenId(String openId) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(openId)) {
            criteria.andOpenIdEqualTo(openId);
        } else {
            return null;
        }
        List<SysUser> list = sysUserMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public String addHeadImage(ImageFileForm form) {
        String cfg = getCfgCode("upload_base_dir");
        String dateString = DateUtil.getCurrDate2();
        String newDir = cfg + File.separator + "userImages" + File.separator + dateString;
        String fileName = form.getFileName();
        String preffix = PkUtil.getUUID();//fileName.substring(0,fileName.lastIndexOf("."));
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        fileName = preffix + suffix;
        File fileDir = new File(newDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
            String imgDataString = StringUtil.defaultIfEmpty(form.getImageContent(), "");
            byte[] data = null;
            if (StringUtil.isNotBlank(imgDataString)) {
                BASE64Decoder decoder = new BASE64Decoder();
                // Base64解码
                data = decoder.decodeBuffer(imgDataString);
                for (int i = 0; i < data.length; ++i) {
                    if (data[i] < 0) {// 调整异常数据
                        data[i] += 256;
                    }
                }
            }
            if (data == null) {
                data = form.getUploadFile().getBytes();
            }

            File imageFile = new File(fileDir, fileName);
            if (form.getUploadFile() != null) {
                form.getUploadFile().transferTo(imageFile);
            } else {
                FileOutputStream fos = new FileOutputStream(imageFile);
                fos.write(data);
                fos.flush();
                fos.close();
            }

            FtpHelperUtil ftpHelperUtil = new FtpHelperUtil();
            String localFilePath = fileDir + File.separator + fileName;
            String ftpDir = "userImages" + File.separator + dateString;
            String ftpFileName = fileName;
            ftpHelperUtil.uploadFile(localFilePath, ftpDir, ftpFileName);

            return "userImages/" + dateString + "/" + fileName;

        } catch (FileNotFoundException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        }
        return null;
    }

    @Override
    public String addApplicationFile(ImageFileForm form) {
        String cfg = getCfgCode("upload_base_dir");
        String dateString = DateUtil.getCurrDate2();
        String newDir = cfg + File.separator + "jsresImages" + File.separator + "asseApplication" + File.separator + dateString;
        String fileName = form.getFileName();
        String preffix = PkUtil.getUUID();//fileName.substring(0,fileName.lastIndexOf("."));
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        fileName = preffix + suffix;
        File fileDir = new File(newDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
            String imgDataString = StringUtil.defaultIfEmpty(form.getImageContent(), "");
            byte[] data = null;
            if (StringUtil.isNotBlank(imgDataString)) {
                BASE64Decoder decoder = new BASE64Decoder();
                // Base64解码
                data = decoder.decodeBuffer(imgDataString);
                for (int i = 0; i < data.length; ++i) {
                    if (data[i] < 0) {// 调整异常数据
                        data[i] += 256;
                    }
                }
            }
            if (data == null) {
                data = form.getUploadFile().getBytes();
            }

            File imageFile = new File(fileDir, fileName);
            if (form.getUploadFile() != null) {
                form.getUploadFile().transferTo(imageFile);
            } else {
                FileOutputStream fos = new FileOutputStream(imageFile);
                fos.write(data);
                fos.flush();
                fos.close();
            }

            FtpHelperUtil ftpHelperUtil = new FtpHelperUtil();
            String localFilePath = fileDir + File.separator + fileName;
            String ftpDir = "userImages" + File.separator + dateString;
            String ftpFileName = fileName;
            ftpHelperUtil.uploadFile(localFilePath, ftpDir, ftpFileName);

            return "jsresImages" + File.separator + "asseApplication/" + dateString + "/" + fileName;

        } catch (FileNotFoundException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        }
        return null;
    }

    @Override
    public int saveOutLock(String outDate, String userFlow) throws ParseException {
        ResDoctor resDoctor = doctorMapper.selectByPrimaryKey(userFlow);
        ResOutOfficeLock resOutOfficeLock = new ResOutOfficeLock();
        resOutOfficeLock.setRecordFlow(PkUtil.getUUID());
        resOutOfficeLock.setUserFlow(resDoctor.getDoctorFlow());
        resOutOfficeLock.setUserName(resDoctor.getDoctorName());
        resOutOfficeLock.setOrgFlow(resDoctor.getOrgFlow());
        resOutOfficeLock.setOrgName(resDoctor.getOrgName());
        resOutOfficeLock.setTrainingSpeId(resDoctor.getTrainingSpeId());
        resOutOfficeLock.setTrainingSpeName(resDoctor.getTrainingSpeName());
        resOutOfficeLock.setTrainingTypeId(resDoctor.getTrainingTypeId());
        resOutOfficeLock.setTrainingTypeName(resDoctor.getTrainingTypeName());
        resOutOfficeLock.setSessionNumber(resDoctor.getSessionNumber());
        resOutOfficeLock.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        resOutOfficeLock.setCreateUserFlow(userFlow);
        resOutOfficeLock.setCreateTime(DateUtil.getCurrDateTime2());
        resOutOfficeLock.setModifyUserFlow(userFlow);
        resOutOfficeLock.setModifyTime(DateUtil.getCurrDateTime());
        resOutOfficeLock.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getId());
        resOutOfficeLock.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getName());
        String currDate = DateUtil.getCurrDate();
        int num = differentDays(outDate, currDate);
        resOutOfficeLock.setTimeOutStatus("已超时" + num + "天");
        return this.outOfficeLockMapper.insertSelective(resOutOfficeLock);

    }

    public int differentDays(String date1, String date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date3 = sdf.parse(date1);
        Date date4 = sdf.parse(date2);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date3);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date4);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {//同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else {// 不同年
            return day2 - day1;
        }
    }

    @Override
    public List<ResDoctorSchProcess> selectProcessByDoctorNew(Map<String, Object> param) {
        return resDoctorProcessExtMapper.selectProcessByDoctorNew(param);
    }

    @Override
    public SchArrangeResult readSchArrangeResult(String resultFlow) {
        return arrangeResultMapper.selectByPrimaryKey(resultFlow);
    }

    @Override
    public Map<String, Object> parseGradeXmlTwo(String recContent) {
        Map<String, Object> gradeMap = null;
        if (StringUtil.isNotBlank(recContent)) {
            try {
                Document doc = DocumentHelper.parseText(recContent);
                Element root = doc.getRootElement();
                if (root != null) {
                    List<Element> items = root.elements("grade");
                    if (items != null && items.size() > 0) {
                        gradeMap = new HashMap<String, Object>();
                        for (Element e : items) {
                            String assessId = e.attributeValue("assessId");
                            Map<String, String> dataMap = new HashMap<String, String>();
                            gradeMap.put(assessId, dataMap);

                            Element score = e.element("score");
                            if (score != null) {
                                String scoreStr = score.getText();
                                dataMap.put("score", scoreStr);
                            }

                        }

                        Element totalScore = root.element("totalScore");
                        if (totalScore != null) {
                            gradeMap.put("totalScore", totalScore.getText());
                        }
                        Element lostReason = root.element("lostReason");
                        if (lostReason != null) {
                            gradeMap.put("lostReason", lostReason.getText());
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        return gradeMap;
    }

    @Override
    public List<SysUser> searchSysUserByuserFlows(List<String> userFlows) {
        if (userFlows != null && !userFlows.isEmpty()) {
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserFlowIn(userFlows);
            return sysUserMapper.selectByExample(sysUserExample);
        }
        return null;
    }

    @Override
    public List<ResDoctor> searchDoctorByuserFlow(List<String> userFlows) {
        ResDoctorExample example = new ResDoctorExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowIn(userFlows);
        return doctorMapper.selectByExample(example);
    }

//	@Override
//	public List<Map<String, Object>> phyAssDoctorList(Map<String, Object> paramMap) {
//		return phyAssExtMapper.phyAssDoctorList(paramMap);
//	}

    @Override
    public List<SysOrg> searchJointOrgsByOrg(String orgFlow) {
        return sysOrgExtMapper.searchJointOrgsByOrg(orgFlow);
    }

    public static void main(String[] args) {
    }


    @Override
    public String addActivityImage3(ActivityImageFileForm form, SysUser user, String imageXML, String recordFlow) {
        String cfg = getCfgCode("upload_base_dir");
        String dateString = DateUtil.getCurrDate2();
        String newDir = cfg + File.separator + "activityFlie" + File.separator + dateString;
        String fileName = form.getFileName();
        String preffix = PkUtil.getUUID();//fileName.substring(0,fileName.lastIndexOf("."));
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        fileName = preffix + suffix;
        File fileDir = new File(newDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
            String imgDataString = StringUtil.defaultIfEmpty(form.getImageContent(), "");
            byte[] data = null;
            if (StringUtil.isNotBlank(imgDataString)) {
                BASE64Decoder decoder = new BASE64Decoder();
                // Base64解码
                data = decoder.decodeBuffer(imgDataString);
                for (int i = 0; i < data.length; ++i) {
                    if (data[i] < 0) {// 调整异常数据
                        data[i] += 256;
                    }
                }
            }
            if (data == null) {
                data = form.getUploadFile().getBytes();
            }
            File imageFile = new File(fileDir, fileName);
            if (form.getUploadFile() != null) {
                form.getUploadFile().transferTo(imageFile);
            } else {
                FileOutputStream fos = new FileOutputStream(imageFile);
                fos.write(data);
                fos.flush();
                fos.close();
            }

            FtpHelperUtil ftpHelperUtil = new FtpHelperUtil();
            String localFilePath = fileDir + File.separator + fileName;
            String ftpDir = "activityFlie" + File.separator + dateString;
            String ftpFileName = fileName;
            ftpHelperUtil.uploadFile(localFilePath, ftpDir, ftpFileName);

            String url = getCfgCode("upload_base_url") + "/activityFlie/" + dateString + "/" + fileName;
            if (StringUtil.isNotBlank(form.getActivityFlow())) {
                TeachingActivityInfo resRec = activityBiz.readActivityInfo(form.getActivityFlow());
                String content = resRec.getImageUrl();
                if (StringUtil.isBlank(content)) {
                    Document dom = DocumentHelper.createDocument();
                    Element root = dom.addElement("ActivityImages");
                    Element elem = root.addElement("image");
                    String imageFlow = PkUtil.getUUID();
                    elem.addAttribute("imageFlow", imageFlow);
                    Element imageUrl = elem.addElement("imageUrl");
                    Element thumbUrl = elem.addElement("thumbUrl");
                    imageUrl.setText(url);
                    thumbUrl.setText(url);
                    content = root.asXML();
                    resRec.setImageUrl(content);
                    activityBiz.saveActivityInfo(resRec, user);
                    return content;
                } else {
                    Document document = DocumentHelper.parseText(content);
                    if (document != null) {
                        Element element = document.getRootElement();
                        Element elem = element.addElement("image");
                        String imageFlow = PkUtil.getUUID();
                        elem.addAttribute("imageFlow", imageFlow);
                        Element imageUrl = elem.addElement("imageUrl");
                        Element thumbUrl = elem.addElement("thumbUrl");
                        imageUrl.setText(url);
                        thumbUrl.setText(url);
                        resRec.setImageUrl(document.asXML());
                        activityBiz.saveActivityInfo(resRec, user);
                        return resRec.getImageUrl();
                    }
                }
            } else {

                if (StringUtil.isBlank(imageXML)) {
                    Document dom = DocumentHelper.createDocument();
                    Element root = dom.addElement("ActivityImages");
                    Element elem = root.addElement("image");
                    String imageFlow = PkUtil.getUUID();
                    elem.addAttribute("imageFlow", imageFlow);
                    Element imageUrl = elem.addElement("imageUrl");
                    Element thumbUrl = elem.addElement("thumbUrl");
                    imageUrl.setText(url);
                    thumbUrl.setText(url);
                    imageXML = root.asXML();
                } else {
                    Document document = DocumentHelper.parseText(imageXML);
                    if (document != null) {
                        Element element = document.getRootElement();
                        Element elem = element.addElement("image");
                        String imageFlow = PkUtil.getUUID();
                        elem.addAttribute("imageFlow", imageFlow);
                        Element imageUrl = elem.addElement("imageUrl");
                        Element thumbUrl = elem.addElement("thumbUrl");
                        imageUrl.setText(url);
                        thumbUrl.setText(url);
                        imageXML = document.asXML();
                    }
                }
                return imageXML;
            }
        } catch (FileNotFoundException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        } catch (DocumentException e) {
            logger.error("", e);
        }
        return "";
    }


    @Override
    public List<SysUserDept> getUserDept(SysUser user) {
        SysUserDeptExample example = new SysUserDeptExample();
        SysUserDeptExample.Criteria criteria = example.createCriteria().andUserFlowEqualTo(user.getUserFlow())
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(user.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(user.getOrgFlow());
        }
        example.setOrderByClause("ORDINAL");
        return userDeptMapper.selectByExample(example);
    }

    @Override
    public List<String> findDoctorNameByRecTime(Map<String, Object> param) {
        return docSchProcessExtMapper.findDoctorNameByRecTime(param);
    }

    @Override
    public SysOrg readSysOrg(String sysOrgFlow) {
        return sysOrgMapper.selectByPrimaryKey(sysOrgFlow);
    }

    @Override
    public List<Map<String, Object>> zltjOrgLocalListNew(Map<String, Object> param) {
        return doctorRecruitInfoExtMapper.zltjOrgLocalListNew(param);
    }

    @Override
    public List<SysDept> searchDeptByOrg(String orgFlow){
        SysDeptExample example = new SysDeptExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow);
        example.setOrderByClause("ORDINAL");
        return sysDeptMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, String>> attendanceList2(Map<String, Object> map) {

        return resAttendenceExtMapper.attendanceList2(map);
    }
    @Override
    public List<Map<String, String>> attendanceList3(Map<String, Object> map) {

        return resAttendenceExtMapper.attendanceList3(map);
    }

    @Override
    public List<Map<String, Object>> getKqStatistics(Map<String, Object> paramMap) {
        return resDoctorKqExtMapper.getKqStatistics(paramMap);
    }

    @Override
    public List<Map<String, String>> temporaryOutSearch(Map<String, Object> map) {
        return resDoctorProcessExtMapper.temporaryOutSearch(map);
    }

    @Override
    public List<Map<String,Object>> searchDocResultsListNew(Map<String,Object> paramMap){
        return resultExtMapper.searchDocResultsListNew(paramMap);
    }

    @Override
    public List<SchDept> searchSchDeptList(String orgFlow) {
        SchDeptExample example = new SchDeptExample();
        example.createCriteria().andOrgFlowEqualTo(orgFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("ORDINAL ");
        return schDeptMapper.selectByExample(example);
    }

    @Override
    public List<SchArrangeResult> searchArrangeResultByDateAndOrg1(Map<String,Object> map){
        return resultMapper.searchArrangeResultByDateAndOrgByMapNew(map);
    }

    @Override
    public List<SysUserRole> getByUserFlow(String userFlow) {
        SysUserRoleExample example = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserFlowEqualTo(userFlow);
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        return sysUserRoleMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> findActivityList(Map<String, String> param) {
        return activityInfoExtMapper.findActivityList2(param);
    }

    @Override
    public List<Map<String, Object>> readActivityResults(String activityFlow) {
        Map<String,Object> param=new HashMap<>();
        param.put("activityFlow",activityFlow);
        return activityInfoExtMapper.readActivityResults2(param);
    }

    @Override
    public List<Map<String, Object>> getDeptCountActivityStatisticsList(String orgFlow, String deptFlow, String startTime, String endTime,String notNull) {
        return activityInfoExtMapper.getDeptCountActivityStatisticsList(orgFlow,deptFlow,startTime,endTime,notNull);
    }

    @Override
    public Map<String, Object> getDeptActivityStatisticsMap(String deptFlow,String startTime,String endTime) {

        Map<String, Object> map=new HashMap<>();
        List<Map<String,String>> datas=activityInfoExtMapper.getDeptActivityStatisticsMap(deptFlow,startTime,endTime);
        if(datas!=null)
        {
            for(Map<String,String> d:datas)
            {
                map.put(d.get("typeId"),d.get("qty"));
            }
        }
        return map;
    }

    @Override
    public List<Map<String,String>>  schProcessStudentDistinctQuery2(Map<String, Object> map) {
        return resDoctorProcessExtMapper.schProcessStudentDistinctQuery2(map);
    }

    @Override
    public List<Map<String, Object>> searchDoctorDataNew2(Map<String, Object> param) {
        return recruitExtMapper.searchDoctorDataNew2(param);
    }

    @Override
    public List<Map<String, Object>> searchTeacherUserList(Map<String, Object> param) {
        return recruitExtMapper.searchTeacherUserList(param);
    }

    @Override
    public List<Map<String, Object>> searchTeacherAuditList(Map<String, Object> param) {
        return recruitExtMapper.searchTeacherAuditList(param);
    }

    //根据协同基地唯一标识获取协同关系表的所有记录
    @Override
    public List<ResJointOrg> selectByJointOrgFlow(String jointOrgFlow) {
        ResJointOrgExample example = new ResJointOrgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andJointOrgFlowEqualTo(jointOrgFlow);
        return resJointOrgMapper.selectByExample(example);
    }

    @Override
    public List<Map<String,Object>> chargeQueryApplyList2(Map<String, Object> param) {
        return graduationApplyExtMapper.chargeQueryApplyList2(param);
    }

    //获取基地审核时间包含当前时间的所有非关闭的考试
    @Override
    public List<ResTestConfig> findLocalEffective(String currDateTime) {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andLocalAuditStartTimeLessThanOrEqualTo(currDateTime)
                .andLocalAuditEndTimeGreaterThanOrEqualTo(currDateTime);
        return resTestConfigMapper.selectByExample(example);
    }

    //获取市局审核时间包含当前时间的所有非关闭的考试
    @Override
    public List<ResTestConfig> findChargeEffective(String currDateTime) {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andChargeAuditStartTimeLessThanOrEqualTo(currDateTime)
                .andChargeAuditEndTimeGreaterThanOrEqualTo(currDateTime);
        return resTestConfigMapper.selectByExample(example);
    }

    //获取省厅审核时间包含当前时间的所有非关闭的考试
    @Override
    public List<ResTestConfig> findGlobalEffective(String currDateTime) {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andApplyStartTimeLessThanOrEqualTo(currDateTime)
                .andTestEndTimeGreaterThanOrEqualTo(currDateTime);
        return resTestConfigMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> queryExamSignUpList(Map<String, Object> param) {
        return doctorRecruitExtMapper.queryExamSignUpList(param);
    }

    @Override
    public List<SchArrangeResult> searchSchArrangeResultByMap(Map<String, String> paramMap) {
        return resultExtMapper.searchSchArrangeResultByMap(paramMap);
    }

    @Override
    public ResDoctorSchProcess searchByResultFlow(String resultFlow) {
        ResDoctorSchProcess process = null;
        if(StringUtil.isNotBlank(resultFlow)){
            ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchResultFlowEqualTo(resultFlow);
            List<ResDoctorSchProcess> processList = this.processMapper.selectByExample(example);
            if(processList!=null&&!processList.isEmpty()){
                process = processList.get(0);
            }
        }
        return process;
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

    @Override
    public Map<String, Object> readActivity(String activityFlow) {
        return activityInfoExtMapper.readActivity(activityFlow);
    }

    /**
     * 根据主表类型，流水号查找文件
     * @param productType
     * @param productFlow
     * @return
     */
    @Override
    public List<PubFile> findFileByTypeFlow(String productType, String productFlow) {
        PubFileExample example = new PubFileExample();
        if (productFlow != null) {
            PubFileExample.Criteria crieria = example.createCriteria();
            crieria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProductFlowEqualTo(productFlow);
            if (productType != null) {
                crieria.andProductTypeEqualTo(productType);
            }
            example.setOrderByClause("CREATE_TIME");
        }
        return pubFileMapper.selectByExample(example);
    }

    @Override
    public TeachingActivityInfo readActivityInfo(String activityFlow) {
        return activityInfoMapper.selectByPrimaryKey(activityFlow);
    }

    @Override
    public int saveActivity(TeachingActivityInfo info, String userFlow) {
        if (StringUtil.isBlank(info.getActivityFlow())) {
            info.setActivityFlow(PkUtil.getUUID());
            setRecordInfo(info, true, userFlow);
            return activityInfoMapper.insertSelective(info);
        } else {
            ResHospSupervSubject subject = selectHospByActivityFlow(info.getActivityFlow());
            if (null != subject){
                if (!info.getRecordStatus().equals(subject.getRecordStatus())){
                    subject.setRecordStatus(info.getRecordStatus());
                    updateHospSupervisioBySubjectFlow(subject);
                }
            }
            setRecordInfo(info, false, userFlow);
            return activityInfoMapper.updateByPrimaryKeySelective(info);
        }
    }

    public ResHospSupervSubject selectHospByActivityFlow(String activityFlow) {
        ResHospSupervSubjectExample example = new ResHospSupervSubjectExample();
        example.createCriteria().andActivityFlowEqualTo(activityFlow);
        List<ResHospSupervSubject> list = hospSupervSubjectMapper.selectByExample(example);
        if (null !=list && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    public int updateHospSupervisioBySubjectFlow(ResHospSupervSubject resHospSupervSubject) {
        ResHospSupervSubjectExample example = new ResHospSupervSubjectExample();
        ResHospSupervSubjectExample.Criteria criteria = example.createCriteria();
        if (null != resHospSupervSubject.getSubjectFlow()){
            criteria.andSubjectFlowEqualTo(resHospSupervSubject.getSubjectFlow());
            return hospSupervSubjectMapper.updateByExample(resHospSupervSubject,example);
        }
        return 0;
    }

    public static void setRecordInfo(Object obj, boolean isAdd,String userFlow) {
        Class<?> clazz = obj.getClass();
        try {
            if (isAdd) {
                Method setRecordStatusMethod = clazz.getMethod("setRecordStatus", String.class);
                setRecordStatusMethod.invoke(obj, com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                Method setCreateTime = clazz.getMethod("setCreateTime", String.class);
                setCreateTime.invoke(obj, DateUtil.getCurrDateTime());
                Method setCreateUserFlow = clazz.getMethod("setCreateUserFlow", String.class);
                if (userFlow != null) {
                    setCreateUserFlow.invoke(obj, userFlow);
                }
            }
            Method setModifyTime = clazz.getMethod("setModifyTime", String.class);
            setModifyTime.invoke(obj, DateUtil.getCurrDateTime());
            Method setModifyUserFlow = clazz.getMethod("setModifyUserFlow", String.class);
            if (userFlow != null) {
                setModifyUserFlow.invoke(obj, userFlow);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Override
    public ResSchProcessExpress queryResRec(String processFlow, String operUserFlow,
                                            String recTypeId) {
        ResSchProcessExpress rec=null;
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        ResSchProcessExpressExample.Criteria create=example.createCriteria();
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
    public int edit(ResSchProcessExpress express) {
        if(express!=null){
            if(StringUtil.isNotBlank(express.getRecFlow())){//修改
                setRecordInfo(express, false, null);
                return this.expressMapper.updateByPrimaryKeySelective(express);
            }else{//新增
                express.setRecFlow(PkUtil.getUUID());
                if (!com.pinde.core.common.enums.ResRecTypeEnum.AnnualTrainForm.getId().equals(express.getRecTypeId())) {//培训年度
                    express.setOperTime(DateUtil.getCurrDateTime());
                }
                setRecordInfo(express, true, null);
                return this.expressMapper.insertSelective(express);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public List<Map<String, String>> parseImageXml(String content) throws DocumentException {
        Document document=DocumentHelper.parseText(content);
        Element elem=document.getRootElement();
        List<Element> ec = elem.elements();
        List<Map<String, String>> imageList=new ArrayList<Map<String,String>>();
        for (Element element : ec) {
            Map<String,String> imageMap=new HashMap<String, String>();
            String imageFlow=element.attributeValue("imageFlow");
            imageMap.put("imageFlow", imageFlow);
            List<Element> items=element.elements();
            for (Element item : items) {
                String itemName=item.getName();
                String itemText=item.getTextTrim();
                imageMap.put(itemName, itemText);
            }
            imageList.add(imageMap);
        }
        return imageList;
    }

    @Override
    public PubFile readFile(String fileFlow) {
        return pubFileMapper.selectByPrimaryKey(fileFlow);
    }

    @Override
    public List<ResDoctorKq> kqStatisticsDetail(ResDoctorKq kq) {
        return resDoctorKqExtMapper.kqStatisticsDetail(kq);
    }

    @Override
    public Map<String, Object> getDoctorSchInfo(String userFlow) {
        return sysUserMapper.getDoctorSchInfo(userFlow);
    }

    @Override
    public List<String> studentList(String userFlow,String isNow) {

        return resDoctorProcessExtMapper.studentList(userFlow,isNow);
    }

    /**
     * @param processFlow
     * @param doctorFlow
     * @Department：研发部
     * @Description 查询未审核的培训数据数量
     * @Author fengxf
     * @Date 2024/12/25
     */
    @Override
    public int countNotAuditResRec(String processFlow, String doctorFlow) {
        ResRecExample example = new ResRecExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow).andAuditStatusIdIsNull();
        return recMapper.countByExample(example);
    }

    /**
     * @param userPhone
     * @Department：研发部
     * @Description 根据手机号查询用户信息
     * @Author fengxf
     * @Date 2025/1/6
     */
    @Override
    public SysUser getUserByUserPhone(String userPhone) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserPhoneEqualTo(userPhone).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (sysUsers != null && sysUsers.size() > 0) {
            return sysUsers.get(0);
        }
        return null;
    }

    // 查询算好的数据比例信息
    @Override
    public List<JsresDoctorDeptDetail> deptDoctorAllWorkDetail(String rotationFlow, String doctorFlow, String applyYear) {
        JsresDoctorDeptDetailExample example=new JsresDoctorDeptDetailExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow).andRotationFlowEqualTo(rotationFlow).andApplyYearEqualTo(applyYear);
        List<JsresDoctorDeptDetail> list = doctorDeptDetailMapper.selectByExample(example);
        return list;
    }

    /**
     * @param rotationFlow
     * @param doctorFlow
     * @param applyYear
     * @Department：研发部
     * @Description 查询计算好的学员培训数据统计信息
     * @Author fengxf
     * @Date 2025/2/18
     */
    @Override
    public List<JsresDoctorDeptDetail> searchDeptDoctorAllWorkDetailList(String rotationFlow, String doctorFlow, String applyYear) {
        return resultExtMapper.searchDeptDoctorAllWorkDetailList(rotationFlow,doctorFlow,applyYear);
    }

    @Override
    public List<JsresDoctorDeptDetail> deptDoctorAllWorkDetailByNow_new(String recruitFlow, String doctorFlow, String applyYear, String rotationFlow) {
        LambdaQueryWrapper<ResDoctorRecruit> rdrLambdaWrapper = new LambdaQueryWrapper<>();
        rdrLambdaWrapper.eq(ResDoctorRecruit::getDoctorFlow,doctorFlow)
                .eq(ResDoctorRecruit::getRecruitFlow,recruitFlow)
                .eq(ResDoctorRecruit::getRotationFlow,rotationFlow)
                .eq(ResDoctorRecruit::getAuditStatusId,"Passed")
                .eq(ResDoctorRecruit::getRecordStatus,"Y");
        List<ResDoctorRecruit> resDoctorRecruits = resDoctorRecruitMapper.selectList(rdrLambdaWrapper);
        if(CollectionUtils.isEmpty(resDoctorRecruits)) return Collections.emptyList();
        //获取轮转方案下标准科室信息
        LambdaQueryWrapper<SchRotationDept> srdLambdaQueryWrapper = new LambdaQueryWrapper<>();
        srdLambdaQueryWrapper.eq(SchRotationDept::getRotationFlow,rotationFlow)
                .eq(SchRotationDept::getRecordStatus,"Y")
                .isNull(SchRotationDept::getOrgFlow);//只查询轮转方案下的标准科室
        List<SchRotationDept> schRotationDepts = schRotationDeptMapper.selectList(srdLambdaQueryWrapper);
        if(CollectionUtils.isEmpty(schRotationDepts)) return Collections.emptyList();

        LambdaQueryWrapper<SchRotationDeptReq> srdrLambdaQueryWrapper = new LambdaQueryWrapper<>();
        srdrLambdaQueryWrapper.eq(SchRotationDeptReq::getRotationFlow,rotationFlow)
                .eq(SchRotationDeptReq::getRecordStatus,"Y")
                .in(SchRotationDeptReq::getRecTypeId,new String[]{"CaseRegistry", "DiseaseRegistry", "SkillRegistry", "OperationRegistry", "CampaignRegistry"})
                .in(SchRotationDeptReq::getRelRecordFlow,schRotationDepts.stream().map(SchRotationDept::getRecordFlow).collect(Collectors.toSet()));

        List<SchRotationDeptReq> schRotationDeptReqs = schRotationDeptReqMapper.selectList(srdrLambdaQueryWrapper);

        LambdaQueryWrapper<ResRec> resRecLambdaQueryWrapper = new LambdaQueryWrapper<>();
        resRecLambdaQueryWrapper.eq(ResRec::getOperUserFlow,doctorFlow).eq(ResRec::getRecordStatus,"Y")
                .in(ResRec::getSchRotationDeptFlow,schRotationDepts.stream().map(SchRotationDept::getRecordFlow).collect(Collectors.toSet()))
                .in(ResRec::getRecTypeId,new String[]{"CaseRegistry", "DiseaseRegistry", "SkillRegistry", "OperationRegistry", "CampaignRegistry"});

        List<ResRec> resRecs = recMapper.selectList(resRecLambdaQueryWrapper);
        if(CollectionUtils.isEmpty(resRecs)) return Collections.emptyList();

        Map<String, List<ResRec>> collect = resRecs.stream().collect(Collectors.groupingBy(ResRec::getSchRotationDeptFlow));
        Map<String, Integer> rotationDeptInCompleteNumMap = new HashMap<>();
        collect.forEach((key, value) -> {
            LambdaQueryWrapper<ResDoctorSchProcess> rdspLambdaQueryWrapper = new LambdaQueryWrapper<>();
            rdspLambdaQueryWrapper.eq(ResDoctorSchProcess::getUserFlow,doctorFlow)
                    .eq(ResDoctorSchProcess::getRecordStatus,"Y")
                    .in(ResDoctorSchProcess::getProcessFlow, value.stream().map(ResRec::getProcessFlow).collect(Collectors.toSet()));
            List<ResDoctorSchProcess> resDoctorSchProcesses = doctorSchProcessMapper.selectList(rdspLambdaQueryWrapper);
            Map<String, List<ResRec>> processFlowMap = value.stream().filter(resRec -> StringUtils.isNotEmpty(resRec.getProcessFlow())).collect(Collectors.groupingBy(ResRec::getProcessFlow));
            resDoctorSchProcesses.forEach(resDoctorSchProcess -> {
                List<ResRec> rrList = processFlowMap.get(resDoctorSchProcess.getProcessFlow());
                if(CollectionUtils.isEmpty(rrList)){
                    return;
                }

                for (ResRec resRec : rrList) {
                    String startTime = resDoctorSchProcess.getSchStartDate().replace("-", "");
                    String endTime = resDoctorSchProcess.getSchEndDate().replace("-", "");;
                    String operTime = resRec.getOperTime();
                    if(StringUtils.isNotBlank(operTime)){
                        operTime = operTime.substring(0, 8);
                        if(operTime.compareTo(startTime) >= 0 && operTime.compareTo(endTime) <= 0) {
                            int inCompleteNum = rotationDeptInCompleteNumMap.getOrDefault(key, 0);
                            rotationDeptInCompleteNumMap.put(key, inCompleteNum + 1);
//							break;
                        }
                    }
                }
            });

        });

        List<Map<String, String>> icList = selectArrangeResultWithRotationDept(doctorFlow, applyYear + "-05-31");

        Map<String, List<SchRotationDept>> groupDeptMap = schRotationDepts.stream().collect(Collectors.groupingBy(vo -> vo.getGroupFlow() + "," + vo.getStandardDeptId()));
        List<SchDoctorDept> schDoctorDeptList = new ArrayList<>();
        groupDeptMap.forEach((key, value) -> {
            LambdaQueryWrapper<SchDoctorDept> schDoctorDeptLambdaQueryWrapper = new LambdaQueryWrapper<>();
            String[] keys = key.split(",");
            schDoctorDeptLambdaQueryWrapper.eq(SchDoctorDept::getDoctorFlow, doctorFlow)
                    .eq(SchDoctorDept::getRecordStatus, "Y")
                    .eq(SchDoctorDept::getGroupFlow, keys[0])
//					.eq(SchDoctorDept::getOrgFlow, keys[1])
                    .eq(SchDoctorDept::getStandardDeptId, keys[1]);
            List<SchDoctorDept> schDoctorDepts = schDoctorDeptMapper.selectList(schDoctorDeptLambdaQueryWrapper);
            schDoctorDeptList.addAll(schDoctorDepts);
        });

        Map<String, String> isShortMap = new HashMap<>();
        Map<String, List<SchRotationDept>> groupFlowMap = schRotationDepts.stream().collect(Collectors.groupingBy(vo->vo.getGroupFlow()+","+vo.getStandardDeptId()+","+vo.getRotationFlow()));
        groupFlowMap.forEach((key, value) -> {
            LambdaQueryWrapper<SchDoctorDept> schDoctorDeptLambdaQueryWrapper = new LambdaQueryWrapper<>();
            String[] keys = key.split(",");
            schDoctorDeptLambdaQueryWrapper.eq(SchDoctorDept::getGroupFlow, keys[0])
                    .eq(SchDoctorDept::getStandardDeptId, keys[1])
                    .eq(SchDoctorDept::getRecordStatus, "Y")
                    .eq(SchDoctorDept::getRotationFlow, keys[2])
                    .eq(SchDoctorDept::getDoctorFlow, doctorFlow);
            List<SchDoctorDept> schDoctorDepts = schDoctorDeptMapper.selectList(schDoctorDeptLambdaQueryWrapper);
            isShortMap.put(value.get(0).getRotationFlow()+keys[0]+keys[1],
                    schDoctorDepts.size() > 0 ? "Y" : "N");
        });

        Map<String, Integer> oldReqNumMap = new HashMap<>();
        Map<String, List<SchRotationDeptReq>> deptReqMap2 = schRotationDeptReqs.stream().collect(Collectors.groupingBy(vo -> vo.getRotationFlow() + vo.getRelRecordFlow() + vo.getStandardDeptId()));
        deptReqMap2.forEach((key, value) -> {
            BigDecimal reqNumOld = value.stream().filter(vo2 -> vo2.getReqNum() != null).map(vo2 -> vo2.getReqNum()).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
            oldReqNumMap.put(key, reqNumOld.setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
        });

        Map<String, Integer> reqNumMap = new HashMap<>();
        Map<String, List<SchRotationDept>> rotationOrgMap = schRotationDepts.stream().collect(Collectors.groupingBy(vo->vo.getRotationFlow()+","+vo.getGroupFlow()));
        rotationOrgMap.forEach((key, value) -> {
            LambdaQueryWrapper<SchDoctorDept> schDoctorDeptLambdaQueryWrapper = new LambdaQueryWrapper<>();
            String[] keys = key.split(",");
            schDoctorDeptLambdaQueryWrapper.eq(SchDoctorDept::getRecordStatus, "Y")
                    .eq(SchDoctorDept::getRotationFlow, keys[0])
                    .eq(SchDoctorDept::getDoctorFlow, doctorFlow)
                    .eq(SchDoctorDept::getGroupFlow, keys[1]);
            List<SchDoctorDept> schDoctorDepts = schDoctorDeptMapper.selectList(schDoctorDeptLambdaQueryWrapper);
            Map<String, List<SchDoctorDept>> rotationOrgMap2 = schDoctorDepts.stream().collect(Collectors.groupingBy(vo -> vo.getRotationFlow()  ));
            Map<String, List<SchRotationDeptReq>> reqDeptNumMap = schRotationDeptReqs.stream()
                    .collect(Collectors.groupingBy(vo -> vo.getRotationFlow()));
            Map<String, List<SchRotationDept>> rotationOrgDeptMap2 = schRotationDepts.stream().collect(Collectors.groupingBy(vo -> vo.getRotationFlow()  ));
            resDoctorRecruits.stream().forEach(vo -> {
                //减免的情况
                if("DoctorTrainingSpe".equals(vo.getCatSpeId()) && "2015".compareTo(vo.getSessionNumber()) <= 0
                        && (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(vo.getTrainYear()) || com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(vo.getTrainYear()))) {
                    List<SchDoctorDept> list = rotationOrgMap2.get(vo.getRotationFlow()  );
                    if(CollectionUtils.isEmpty(list)) {
                        List<SchRotationDeptReq> schRotationDeptReqs1 = reqDeptNumMap.get(vo.getRotationFlow());
                        Map<String, List<SchRotationDeptReq>> deptReqMap = schRotationDeptReqs1.stream().collect(Collectors.groupingBy(vo2 -> vo2.getRelRecordFlow() + vo2.getStandardDeptId()));
                        deptReqMap.forEach((key2, value2) -> {
                            BigDecimal reqNum = value2.stream().filter(vo2 -> vo2.getReqNum() != null).map(vo2 -> vo2.getReqNum()).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
                            reqNumMap.put(vo.getRecruitFlow() + vo.getRotationFlow() + keys[1] + key2, reqNum.setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
                        });
                    }else {
                        List<SchDoctorDept> list2 = rotationOrgMap2.get(vo.getRotationFlow()  );
                        List<SchRotationDept> list3 = rotationOrgDeptMap2.get(vo.getRotationFlow() );
                        Map<String, List<SchDoctorDept>> map2 = list2.stream().collect(Collectors.groupingBy(vo3 ->vo3.getGroupFlow() + "," +vo3.getStandardDeptId()));
                        Map<String, List<SchRotationDept>> map3 = list3.stream().collect(Collectors.groupingBy(vo3 ->vo3.getRecordFlow() + "," + vo3.getGroupFlow() + "," + vo3.getStandardDeptId()));
                        map3.forEach((key3, value3) -> {
                            String[] key3Arr = key3.split(",");
                            if(map2.containsKey(key3Arr[1] +","+key3Arr[2])) {
                                List<SchDoctorDept> schDoctorDeptList1 = map2.get(key3Arr[1] +","+key3Arr[2]);
                                //减免后的月份
                                Float schMonth = schDoctorDeptList1.stream().filter(vo3 -> vo3.getSchMonth() != null).map(vo3 -> Float.parseFloat(vo3.getSchMonth())).reduce(0f, (vo2, vo3)->vo2+vo3);
                                //减免前的月份
                                Float schMonth2 = value3.stream().filter(vo3 -> vo3.getSchMonth() != null).map(vo3 -> Float.parseFloat(vo3.getSchMonth())).reduce(0f, (vo2, vo3)->vo2+vo3);
                                Map<String, List<SchRotationDeptReq>> rotationReq2 = schRotationDeptReqs.stream().collect(Collectors.groupingBy(vo3 -> vo3.getRotationFlow() + vo3.getRelRecordFlow() + vo3.getStandardDeptId()));
                                List<SchRotationDeptReq> schRotationDeptReqs2 = rotationReq2.get(vo.getRotationFlow() + key3Arr[0] + key3Arr[2]);
                                BigDecimal reqNum;
                                if(CollectionUtils.isEmpty(schRotationDeptReqs2)) reqNum = BigDecimal.ZERO;
                                else reqNum = schRotationDeptReqs2.stream().filter(vo3 -> vo3.getReqNum() != null).map(vo3 -> vo3.getReqNum()).reduce(BigDecimal.ZERO, (vo1, vo2) -> BigDecimal.ZERO.add(vo1).add(vo2));
                                if(schMonth!=null && schMonth2!=null && reqNum != null) {
                                    reqNumMap.put(vo.getRecruitFlow() + vo.getRotationFlow() + keys[1] + key3Arr[0] + key3Arr[2], Math.round(schMonth / schMonth2 * reqNum.floatValue()));
                                }else {
                                    reqNumMap.put(vo.getRecruitFlow() + vo.getRotationFlow() + keys[1] + key3Arr[0] + key3Arr[2], 0);
                                }
                            }else {
                                Map<String, List<SchRotationDeptReq>> rotationReq2 = schRotationDeptReqs.stream().collect(Collectors.groupingBy(vo3 -> vo3.getRotationFlow() + vo3.getRelRecordFlow() + vo3.getStandardDeptId()));
                                List<SchRotationDeptReq> schRotationDeptReqs2 = rotationReq2.get(vo.getRotationFlow() + key3Arr[0] + key3Arr[2]);
                                BigDecimal reqNum;
                                if(CollectionUtils.isEmpty(schRotationDeptReqs2)) reqNum = BigDecimal.ZERO;
                                else reqNum = schRotationDeptReqs2.stream().filter(vo3 -> vo3.getReqNum() != null).map(vo3 -> vo3.getReqNum()).reduce(BigDecimal.ZERO, (vo1, vo2) -> BigDecimal.ZERO.add(vo1).add(vo2));

                                reqNumMap.put(vo.getRecruitFlow() + vo.getRotationFlow() + keys[1] + key3Arr[0] + key3Arr[2], reqNum.intValue());
                            }
                        });

                    }
                }else {
                    List<SchRotationDeptReq> schRotationDeptReqs1 = reqDeptNumMap.get(vo.getRotationFlow());
                    Map<String, List<SchRotationDeptReq>> deptReqMap = schRotationDeptReqs1.stream().collect(Collectors.groupingBy(vo2 -> vo2.getRelRecordFlow() + vo2.getStandardDeptId()));
                    deptReqMap.forEach((key2, value2) -> {
                        BigDecimal reqNum = value2.stream().filter(vo2 -> vo2.getReqNum() != null).map(vo2 -> vo2.getReqNum()).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
                        reqNumMap.put(vo.getRecruitFlow() + vo.getRotationFlow() + keys[1] + key2, reqNum.setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
                    });
                }
            });
        });

        //
        Map<String, List<ResRec>> resRecMap = resRecs.stream().collect(Collectors.groupingBy(vo -> vo.getSchRotationDeptFlow()));
        Map<String, List<Map<String, String>>> icMap = icList.stream().collect(Collectors.groupingBy(map -> map.get("DOCTOR_FLOW") + map.get("ROTATION_FLOW") + map.get("RECORD_FLOW")));
        List<JsresDoctorDeptDetail> jsresDoctorWorkDetails = new ArrayList<>();
        resDoctorRecruits.forEach(rd -> {
            schRotationDepts.forEach(srd -> {
                JsresDoctorDeptDetail jsresDoctorDeptDetail = new JsresDoctorDeptDetail();
                jsresDoctorDeptDetail.setApplyYear(applyYear);
                jsresDoctorDeptDetail.setRecordStatus("Y");
                jsresDoctorDeptDetail.setRecordFlow(UUID.randomUUID().toString());
                jsresDoctorDeptDetail.setRecruitFlow(rd.getRecruitFlow());
                jsresDoctorDeptDetail.setDoctorFlow(rd.getDoctorFlow());
                jsresDoctorDeptDetail.setCatSpeId(rd.getCatSpeId());
                jsresDoctorDeptDetail.setCatSpeName(rd.getCatSpeName());
                jsresDoctorDeptDetail.setSpeId(rd.getSpeId());
                jsresDoctorDeptDetail.setSpeName(rd.getSpeName());
                jsresDoctorDeptDetail.setTrainYear(rd.getTrainYear());
                jsresDoctorDeptDetail.setSessionNumber(rd.getSessionNumber());
                jsresDoctorDeptDetail.setRotationFlow(rd.getRotationFlow());
//				jsresDoctorDeptDetail.setRecordFlow(srd.getRecordFlow());
                jsresDoctorDeptDetail.setSchStandardDeptFlow(srd.getRecordFlow());
                jsresDoctorDeptDetail.setGroupFlow(srd.getGroupFlow());
                jsresDoctorDeptDetail.setStandardDeptId(srd.getStandardDeptId());
                jsresDoctorDeptDetail.setStandardDeptName(srd.getStandardDeptName());
                jsresDoctorDeptDetail.setSchMonth(srd.getSchMonth());
                jsresDoctorDeptDetail.setOrgFlow(rd.getOrgFlow());
                jsresDoctorDeptDetail.setOrgName(rd.getOrgName());
                List<ResRec> resRecs1 = resRecMap.getOrDefault( srd.getRecordFlow(), new ArrayList<>());
                jsresDoctorDeptDetail.setCompleteNum(String.valueOf(resRecs1.size()));
                Integer inCompleteNum = rotationDeptInCompleteNumMap.getOrDefault(srd.getRecordFlow(), 0);
                jsresDoctorDeptDetail.setInCompleteNum(String.valueOf(inCompleteNum));
                jsresDoctorDeptDetail.setOutCompleteNum(String.valueOf(resRecs1.size() - inCompleteNum));
                int auditNum = (int)resRecs1.stream().filter(vo -> StringUtils.isNotEmpty(vo.getAuditStatusId())).count();
                jsresDoctorDeptDetail.setAuditNum(String.valueOf(auditNum));
                jsresDoctorDeptDetail.setIsShort(isShortMap.get(rd.getRotationFlow()+srd.getGroupFlow()+srd.getStandardDeptId()));
                jsresDoctorDeptDetail.setReqNum(String.valueOf(reqNumMap.getOrDefault(rd.getRecruitFlow() + rd.getRotationFlow() + srd.getGroupFlow() + srd.getRecordFlow() + srd.getStandardDeptId(), 0)));
                jsresDoctorDeptDetail.setOldReqNum(String.valueOf(oldReqNumMap.getOrDefault(rd.getRotationFlow() + srd.getRecordFlow() + srd.getStandardDeptId(), 0)));
                List<Map<String, String>> icListTemp = icMap.getOrDefault(rd.getDoctorFlow() + rd.getRotationFlow() + srd.getRecordFlow(), new ArrayList<>());
                jsresDoctorDeptDetail.setIsAdd(icListTemp.size() > 0 ? "Y" : "N");

                // 计算比例

                jsresDoctorDeptDetail.setCompleteBi(getProportion(jsresDoctorDeptDetail.getCompleteNum(), jsresDoctorDeptDetail.getReqNum()));
                jsresDoctorDeptDetail.setInCompleteBi(getProportion(jsresDoctorDeptDetail.getInCompleteNum(), jsresDoctorDeptDetail.getReqNum()));
                jsresDoctorDeptDetail.setOutCompleteBi(getProportion(jsresDoctorDeptDetail.getOutCompleteNum(), jsresDoctorDeptDetail.getReqNum()));
                String auditBi = "-";
                if(StringUtils.isNotEmpty(jsresDoctorDeptDetail.getReqNum()) && !"0".equals(jsresDoctorDeptDetail.getReqNum())) {
                    auditBi = "0";
                    if(StringUtils.isNotEmpty(jsresDoctorDeptDetail.getCompleteNum()) && !"0".equals(jsresDoctorDeptDetail.getCompleteNum())) {
                        float auditNumF = Float.parseFloat(jsresDoctorDeptDetail.getAuditNum());
                        float completeNumF = Float.parseFloat(jsresDoctorDeptDetail.getCompleteNum());
                        auditBi = String.valueOf(Math.round(auditNumF / completeNumF * 100));
                    }
                }
                jsresDoctorDeptDetail.setAuditBi(auditBi);

                jsresDoctorWorkDetails.add(jsresDoctorDeptDetail);
            });
        });

        return jsresDoctorWorkDetails;
    }

    /**
     * 计算两个数字的比例
     *
     * 此方法用于接收两个字符串形式的数字，计算它们相除后的比例，并将结果四舍五入为整数返回
     * 如果输入的数字字符串为空或者无法转换为浮点数，则返回 "-"
     *
     * @param num1 第一个数字字符串，代表分子
     * @param num2 第二个数字字符串，代表分母
     * @return 返回计算出的比例的字符串形式，如果输入不合法则返回 "-"
     */
    private String getProportion(String num1, String num2) {
        // 检查输入的两个数字字符串是否都不为空
        if(StringUtils.isNotEmpty(num1) && StringUtils.isNotEmpty(num2)) {
            // 将数字字符串转换为浮点数
            float num1F = Float.parseFloat(num1);
            float num2F = Float.parseFloat(num2);

            if(num2F == 0f) return "-";

            // 计算比例并四舍五入为整数
            int res = Math.round(num1F / num2F * 100);
            if(res > 100) res = 100;
            // 返回计算结果的字符串形式
            return String.valueOf(res);
        }

        // 如果输入不合法，返回 "-"
        return "-";
    }

    public List<Map<String, String>> selectArrangeResultWithRotationDept(String doctorFlow,String schEndDate) {
        LambdaQueryWrapper<SchArrangeResult> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SchArrangeResult::getDoctorFlow,doctorFlow)
                .le(SchArrangeResult::getSchEndDate,schEndDate);
        List<Map<String, String>> maps = schArrangeResultMapper.selectArrangeResultWithRotationDept(lambdaQueryWrapper);
        return maps;
    }

    @Resource
    private JsresDoctorDeptDetailMapper jsresDoctorDeptDetailMapper;

    @Resource
    private SchArrangeResultMapper schArrangeResultMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private SchDoctorDeptMapper schDoctorDeptMapper;
    @Resource
    private ResDoctorSchProcessMapper doctorSchProcessMapper;
    @Resource
    private ResDoctorRecruitMapper resDoctorRecruitMapper;
}
