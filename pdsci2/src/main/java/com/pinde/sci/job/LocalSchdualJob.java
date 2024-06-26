package com.pinde.sci.job;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sch.impl.SchRotationGroupBizImpl;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.DateUtil;
import com.pinde.sci.ctrl.res.ShengShiUniverstiJob;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.dao.jsres.JsResDoctorRecruitExtMapper;
import com.pinde.sci.dao.jsres.MonthlyReportExtMapper;
import com.pinde.sci.dao.jsres.SchdualTaskMapper;
import com.pinde.sci.dao.res.ResDoctorRecruitExtMapper;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.jsres.AppUseInfoPojoParam;
import com.pinde.sci.model.jsres.DoctorLunZhuanDataMonthReport;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.SysDict;
import com.pinde.sci.model.mo.SysOrg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  @author: yuh
 *  @Date: 2020/3/16 11:06
 *  @Description:  基地角色  定时任务
 */
@Service
@Component
public class LocalSchdualJob implements Job {
    private static Logger logger = LoggerFactory.getLogger(LocalSchdualJob.class);
    @Autowired
    private SysLogMapper logMapper;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IJsResRecruitDoctorInfoBiz recruitDoctorInfoBiz;
    @Autowired
    private INoticeBiz noticeBiz;
    @Autowired
    private IResDoctorRecruitBiz doctorRecruitBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IResGradeBiz resGradeBiz;
    @Autowired
    private IResOrgSpeBiz resOrgSpeBiz;
    @Autowired
    private IJsResDoctorOrgHistoryBiz jsDocOrgHistoryBiz;
    @Autowired
    private IJsResStatisticBiz resStatisticBiz;
    @Autowired
    private ISchRotationDeptBiz rotationDeptBiz;
    @Autowired
    private ISchDoctorDeptBiz doctorDeptBiz;
    @Autowired
    private ISchRotationGroupBiz groupBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private ISchExamCfgBiz schExamCfgBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private ILoginBiz loginBiz;
    @Autowired
    private IJsResRecBiz jsResRecBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private IResAssessCfgBiz assessCfgBiz;
    @Autowired
    private IMsgBiz msgBiz;
    @Autowired
    private ISchDeptExternalRelBiz deptExternalRelBiz;
    @Autowired
    private ISchAndStandardDeptCfgBiz deptCfgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IJsResDoctorBiz jsResDoctorBiz;
    @Autowired
    private IFileBiz pubFileBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IJsResPowerCfgBiz jsResPowerCfgBiz;
    @Autowired
    private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;
    @Autowired
    private MonthlyReportExtMapper monthlyReportExtMapper2;
    @Autowired
    private ResDoctorRecruitExtMapper resDoctorRecruitExtMapper;
    @Autowired
    private ISchArrangeResultBiz schArrangeResultBiz;
    @Autowired
    private SchRotationGroupBizImpl rotationGroupBiz;
    @Autowired
    private SchRotationDeptMapper rotationDeptMapper;
    @Autowired
    private IResDoctorProcessBiz resDoctorProcessBiz;
    @Autowired
    private SchdualTaskMapper schdualTaskMapper;
    @Autowired
    private JsResDoctorRecruitExtMapper jsResDoctorRecruitExtMapper;
    @Autowired
    private ShengShiUniverstiJob shengShiUniverstiJob;

    /**
     *  @Date: 2021/11/22
     *  @Description:  app使用月报数据插入
     */
//    @Scheduled(cron="0 */10 * * * ?")//测试10分钟执行一次
//    @Scheduled(cron="0 10 1 1 * ?")//每月1号凌晨1点10分执行一次 moved
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void localAppInitData() throws ParseException {
        logger.info("----------------------------------Start My Schduled Doing-----------------------------");
        long startTime = System.currentTimeMillis(); //获取开始时间

//        String monthDate = InitConfig.getSysCfg("res_static_job_month");
//        if(StringUtil.isBlank(monthDate)){
//            monthDate = getLastMonths(-1); //获取上月  -9
//        }
        String monthDate = DateUtil.addMonth(DateUtil.getMonth(),-1);
        String createTime = DateUtil.getCurrentTime();
        int myCount= schdualTaskMapper.deleteAllData(monthDate); //先删除上月的数据，
        logger.info("----------------------------------"+myCount+"-----------------------------");
        int num = schdualTaskMapper.insertOrgFlowMonthData(monthDate,createTime);//插入主基地数据(不包含协同基地)
        logger.info("------------------------------插入数据"+num+"条-----------------------------");
//        int num2 = schdualTaskMapper.insertOrgFlowAndJointMonthData(monthDate,createTime);//插入主基地数据(包含协同基地)
//        logger.info("------------------------------插入数据"+num2+"条-----------------------------");
        int num3 = schdualTaskMapper.insertJointMonthData(monthDate,createTime);//插入协同基地数据
        logger.info("------------------------------插入数据"+num3+"条-----------------------------");

    }

    /**
     *  @Date: 2021/12/01
     *  @Description:  学员轮转数据
     */
//    @Scheduled(cron="0 */10 * * * ?")//测试10分钟执行一次
//    @Scheduled(cron="0 10 2 1 * ?")//每月1号凌晨2点10分执行一次 moved
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void localDoctorLunZhuanDataInsertDataNew(){
        logger.info("----------------------------------Start My Schduled Doing-----------------------------");

        String monthDate = DateUtil.addMonth(DateUtil.getMonth(),-1);
        String createTime = DateUtil.getCurrentTime();
        String startDate = DateUtil.getDate(DateUtil.setFirstDayOfMonth(monthDate));//上个月第一天
        String endDate = DateUtil.getDate(DateUtil.setFirstDayOfMonth(DateUtil.getMonth()));//第一天
        int myCount= schdualTaskMapper.deleteLunzhuanAllData(monthDate); //先删除上月的数据，
        logger.info("------------------------------删除数据"+myCount+"条----------------------------");

        int num = schdualTaskMapper.insertLunzhuanAllData(monthDate,createTime,startDate,endDate);//插入主基地总数据(包含住院医师与在校专硕)
        logger.info("------------------------------插入数据"+num+"条-----------------------------");

        int num2 = schdualTaskMapper.insertLunzhuanOrgDoctorAllData(monthDate,createTime,startDate,endDate);//插入主基地住院医师数据
        logger.info("------------------------------插入数据"+num2+"条-----------------------------");

        int num3 = schdualTaskMapper.insertLunzhuanOrgGraduateAllData(monthDate,createTime,startDate,endDate);//插入主基地在校专硕数据
        logger.info("------------------------------插入数据"+num3+"条-----------------------------");

        int num4 = schdualTaskMapper.insertLunzhuanJointOrgAllData(monthDate,createTime,startDate,endDate);//插入协同基地总数据(包含住院医师与在校专硕)
        logger.info("------------------------------插入数据"+num4+"条-----------------------------");

        int num5 = schdualTaskMapper.insertLunzhuanJointOrgDoctorAllData(monthDate,createTime,startDate,endDate);//插入协同基地住院医师数据
        logger.info("------------------------------插入数据"+num5+"条-----------------------------");

        int num6 = schdualTaskMapper.insertLunzhuanJointOrgGraduateAllData(monthDate,createTime,startDate,endDate);//插入协同基地在校专硕数据
        logger.info("------------------------------插入数据"+num6+"条-----------------------------");
    }

    /**
     *  @Date: 2021/11/22
     *  @Description:  教学活动统计数据插入
     */
//    @Scheduled(cron="0 */10 * * * ?")//测试10分钟执行一次
//    @Scheduled(cron="0 30 3 1 * ?")//每月1号凌晨3点30分执行一次 moved
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void localActivityInitData() throws ParseException {
        logger.info("----------------------------------Start My Schduled Doing-----------------------------");
        String monthDate = DateUtil.addMonth(DateUtil.getMonth(),-1);
        String createTime = DateUtil.getCurrentTime();
        String startDate = DateUtil.getDate(DateUtil.setFirstDayOfMonth(monthDate));//上个月第一天
        String endDate = DateUtil.getDate(DateUtil.setFirstDayOfMonth(DateUtil.getMonth()));//第一天
        int myCount= schdualTaskMapper.deleteAllActivityData(monthDate); //先删除上月的数据，
        logger.info("----------------------------------"+myCount+"-----------------------------");

        int num = schdualTaskMapper.insertOrgActivityData(monthDate,createTime,startDate,endDate);//插入主基地数据(包含住院医师与在校专硕)
        logger.info("------------------------------插入数据"+num+"条-----------------------------");

        int num2 = schdualTaskMapper.insertOrgActivityDoctorData(monthDate,createTime,startDate,endDate);//插入主基地住院医师数据
        logger.info("------------------------------插入数据"+num2+"条-----------------------------");

        int num3 = schdualTaskMapper.insertOrgActivityGraduateData(monthDate,createTime,startDate,endDate);//插入主基地在校专硕数据
        logger.info("------------------------------插入数据"+num3+"条-----------------------------");

        int num4 = schdualTaskMapper.insertJointOrgActivityData(monthDate,createTime,startDate,endDate);//插入协同基地数据(包含住院医师与在校专硕)
        logger.info("------------------------------插入数据"+num4+"条-----------------------------");

        int num5 = schdualTaskMapper.insertJointOrgActivityDoctorData(monthDate,createTime,startDate,endDate);//插入主基地住院医师数据
        logger.info("------------------------------插入数据"+num5+"条-----------------------------");

        int num6 = schdualTaskMapper.insertJointOrgActivityGraduateData(monthDate,createTime,startDate,endDate);//插入主基地在校专硕数据
        logger.info("------------------------------插入数据"+num6+"条-----------------------------");

    }

    /**
     *  @Date: 2021/12/7
     *  @Description:  学员出科情况统计数据插入
     */
//    @Scheduled(cron="0 */10 * * * ?")//测试10分钟执行一次
//    @Scheduled(cron="0 30 4 1 * ?")//每月1号凌晨4点30分执行一次 moved
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void localOutDeptData() throws ParseException {
        logger.info("----------------------------------Start My Schduled Doing-----------------------------");
        String monthDate = DateUtil.addMonth(DateUtil.getMonth(),-1);
        String createTime = DateUtil.getCurrentTime();
        String startDate = DateUtil.setFirstDayOfMonth(monthDate);//上个月第一天
        String endDate = DateUtil.setFirstDayOfMonth(DateUtil.getMonth());//第一天
        int myCount= schdualTaskMapper.deleteAllOutDeptData(monthDate); //先删除上月的数据
        logger.info("----------------------------------"+myCount+"-----------------------------");

        int num = schdualTaskMapper.insertOrgOutDeptData(monthDate,createTime,startDate,endDate);//插入基地数据(包含住院医师与在校专硕)
        logger.info("------------------------------插入数据"+num+"条-----------------------------");

        int num2 = schdualTaskMapper.insertOrgOutDeptDoctorData(monthDate,createTime,startDate,endDate);//插入基地数据(住院医师)
        logger.info("------------------------------插入数据"+num2+"条-----------------------------");

        int num3 = schdualTaskMapper.insertOrgOutDeptGraduateData(monthDate,createTime,startDate,endDate);//插入基地数据(在校专硕)
        logger.info("------------------------------插入数据"+num3+"条-----------------------------");
    }

    /**
     *  @author: yuh
     *  @Date: 2020/3/16 11:10
     *  @Description:  基地 app使用月报数据插入
     */
//    @Scheduled(cron="0 */2 * * * ?")//测试2分钟执行一次
//    @Scheduled(cron="0 10 1 1 * ?")//每月1号凌晨1点10分执行一次
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void localApp_InsertData() throws ParseException {
        logger.info("----------------------------------Start My Schduled Doing-----------------------------");
        long startTime = System.currentTimeMillis(); //获取开始时间

        String monthDate = InitConfig.getSysCfg("res_static_job_month");
        if(StringUtil.isBlank(monthDate)){
            monthDate = getLastMonths(-1); //获取上月  -9
        }
        int myCount= schdualTaskMapper.deleteAllPreviousMonthData(monthDate); //先删除上月的数据，
        logger.info("----------------------------------"+myCount+"-----------------------------");
        String[] a = new String[2];
        a[0] = "";
        a[1] = "isContain";

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位

        /*List<AppUseInfoPojoParam> bList = new ArrayList<>();*/
        List<SysOrg> joinorgList = new ArrayList<>();

        SysOrg searchOrg=new SysOrg();
        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
        searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        searchOrg.setOrgProvId("320000");  //江苏省
        List<SysOrg> exitOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
        for(int s=0;s<exitOrgs.size();s++){
            //orgs.add(currOrg);//获取机构和协同机构
            if (OrgLevelEnum.CountryOrg.getId().equals(exitOrgs.get(s).getOrgLevelId())) {
                joinorgList = orgBiz.searchJointOrgsByOrg(exitOrgs.get(s).getOrgFlow());
            }
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("dictTypeId", DictTypeEnum.DoctorTrainingSpe.getId());
            List<SysDict> doctorTrainingSpeList = this.resDoctorRecruitExtMapper.searchTrainSpeList(paramMap);//所有专业
            String[] docTypeListDoc = {"Company", "CompanyEntrust", "Social"};
            String[] docTypeListMast = {"Graduate"};
        //
            for (int count = 0; count < a.length; count++) {

        if ("isContain".equals(a[count])) {
            List<AppUseInfoPojoParam> bList = new ArrayList<>();
            /**第一层专业*/
            for (int i = 0; i < doctorTrainingSpeList.size(); i++) {
                int speDoczaipei = 0;
                int speDocuse = 0;
                int speMastzaipei = 0;
                int speMastuse = 0;
                int speavgzaipei = 0;
                int speavguse = 0;
                /**年级*/
                Map<String, Object> sessionNumbermap = new HashMap<>();
                List<String> sessionNumberDistinct = new ArrayList<>();
                sessionNumbermap.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                String[] docTypeList = {"Company", "CompanyEntrust", "Social", "Graduate"};
                sessionNumbermap.put("docTypeList", docTypeList);
                sessionNumbermap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                List<JsResDoctorRecruitExt> sessionNUmberList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionNumbermap);
                for (JsResDoctorRecruitExt jrs : sessionNUmberList) {
                    if (!sessionNumberDistinct.contains(jrs.getSessionNumber())) {
                        sessionNumberDistinct.add(jrs.getSessionNumber());
                    }
                }
                for (int j = 0; j < sessionNumberDistinct.size(); j++) {
                    int sessionDoczaipei = 0;
                    int sessionDocuse = 0;
                    int sessionMastzaipei = 0;
                    int sessionMastuse = 0;
                    int sessionavgzaipei = 0;
                    int sessionavguse = 0;
                    if (joinorgList.size() > 0) {
                        for (int k = 0; k < joinorgList.size(); k++) {
                            AppUseInfoPojoParam appUseInfoPojoParam3 = new AppUseInfoPojoParam();
                            Map<String, Object> joinOrgMap = new HashMap<>();
                            Map<String, Object> joinOrgMap2 = new HashMap<>();
                            Map<String, Object> joinOrgMap3 = new HashMap<>();
                            joinOrgMap.put("orgFlow", joinorgList.get(k).getOrgFlow());
                            joinOrgMap2.put("orgFlow", joinorgList.get(k).getOrgFlow());
                            joinOrgMap.put("docTypeList", docTypeListDoc);
                            joinOrgMap2.put("docTypeList", docTypeListMast);
                            joinOrgMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                            joinOrgMap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
                            joinOrgMap.put("sessionNumber", sessionNumberDistinct.get(j));
                            joinOrgMap2.put("sessionNumber", sessionNumberDistinct.get(j));
                            joinOrgMap3.put("orgFlow", joinorgList.get(k).getOrgFlow());
                            joinOrgMap3.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
                            List<JsResDoctorRecruitExt> joinorgZaipeiListDoc = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(joinOrgMap);
                            List<JsResDoctorRecruitExt> joinorgZaipeiListMaster = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(joinOrgMap2);
                            int joinorgavgzaipei = joinorgZaipeiListDoc.size() + joinorgZaipeiListMaster.size();
                            List<String> joinorguserFlowList = monthlyReportExtMapper2.getAppUserSum(joinOrgMap3);//该基地下该年月使用app用户id
                            List<JsResDoctorRecruitExt> joinorgappuserDOcList = getUseAppinfo(joinorgZaipeiListDoc, joinorguserFlowList);
                            List<JsResDoctorRecruitExt> joinorgappuserMastList = getUseAppinfo(joinorgZaipeiListMaster, joinorguserFlowList);
                            int joinorgavguse = joinorgappuserDOcList.size() + joinorgappuserMastList.size();

                            String joinorgdoctorRate = "";
                            String joinorgmasterRate = "";
                            String joinorgavgRate = "";
                            if (joinorgZaipeiListDoc.size() == 0) {
                                joinorgdoctorRate = "0%";
                            }
                            if (null != joinorgZaipeiListDoc && !"".equals(joinorgZaipeiListDoc) && joinorgZaipeiListDoc.size() != 0) {
                                joinorgdoctorRate = numberFormat.format((float) joinorgappuserDOcList.size() / (float) joinorgZaipeiListDoc.size() * 100) + "%";
                            }
                            if (joinorgZaipeiListMaster.size() == 0) {
                                joinorgmasterRate = "0%";
                            }
                            if (null != joinorgZaipeiListMaster && !"".equals(joinorgZaipeiListMaster) && joinorgZaipeiListMaster.size() != 0) {
                                joinorgmasterRate = numberFormat.format((float) joinorgappuserMastList.size() / (float) joinorgZaipeiListMaster.size() * 100) + "%";
                            }
                            if (joinorgavgzaipei == 0) {
                                joinorgavgRate = "0%";
                            }
                            if (0 != joinorgavgzaipei) {
                                joinorgavgRate = numberFormat.format((float) joinorgavguse / (float) joinorgavgzaipei * 100) + "%";
                            }
                            //
                            appUseInfoPojoParam3.setId(i + "-" + j + "-" + k);
                            appUseInfoPojoParam3.setPid(i + "-" + j);//
                            appUseInfoPojoParam3.setLevel("3");
                            appUseInfoPojoParam3.setSpeName(joinorgList.get(k).getOrgName());//存协同基地
                            appUseInfoPojoParam3.setDoctorRateTrainSum(joinorgZaipeiListDoc.size() + "");
                            appUseInfoPojoParam3.setMasterRateTrainSum(joinorgZaipeiListMaster.size() + "");
                            appUseInfoPojoParam3.setAvgRateTrainSum(joinorgavgzaipei + "");
                            appUseInfoPojoParam3.setDoctorRateUseSum(joinorgappuserDOcList.size() + "");
                            appUseInfoPojoParam3.setMasterRateUseSum(joinorgappuserMastList.size() + "");
                            appUseInfoPojoParam3.setAvgRateUseSum(joinorgavguse + "");

                            appUseInfoPojoParam3.setDoctorRateRate(joinorgdoctorRate);
                            appUseInfoPojoParam3.setMasterRateRate(joinorgmasterRate);
                            appUseInfoPojoParam3.setAvgRateRate(joinorgavgRate);

                            appUseInfoPojoParam3.setIsContain("Y");
                            appUseInfoPojoParam3.setMonthDate(monthDate);
                            appUseInfoPojoParam3.setOrgFlow(exitOrgs.get(s).getOrgFlow());
                            appUseInfoPojoParam3.setCreateTime(DateUtil.getCurrDateTime());
                            appUseInfoPojoParam3.setRecordFlow(UUID.randomUUID().toString());

                            bList.add(appUseInfoPojoParam3);
                            sessionDoczaipei = sessionDoczaipei + joinorgZaipeiListDoc.size();
                            sessionMastzaipei = sessionMastzaipei + joinorgZaipeiListMaster.size();
                            sessionavgzaipei = sessionavgzaipei + joinorgavgzaipei;
                            sessionDocuse = sessionDocuse + joinorgappuserDOcList.size();
                            sessionMastuse = sessionMastuse + joinorgappuserMastList.size();
                            sessionavguse = sessionavguse + joinorgavguse;
                        }
                    }
                    AppUseInfoPojoParam appUseInfoPojoParam2 = new AppUseInfoPojoParam();
                    Map<String, Object> sessionMap = new HashMap<>();
                    Map<String, Object> sessionMap2 = new HashMap<>();
                    Map<String, Object> sessionMap3 = new HashMap<>();
                    sessionMap.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                    sessionMap2.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                    sessionMap.put("docTypeList", docTypeListDoc);
                    sessionMap2.put("docTypeList", docTypeListMast);
                    sessionMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                    sessionMap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
                    sessionMap.put("sessionNumber", sessionNumberDistinct.get(j));
                    sessionMap2.put("sessionNumber", sessionNumberDistinct.get(j));
                    sessionMap3.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                    sessionMap3.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
                    List<JsResDoctorRecruitExt> sessionNUmberZaipeiListDoc = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionMap);
                    List<JsResDoctorRecruitExt> sessionNUmberZaipeiListMaster = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionMap2);
                    int SessionNumberavgzaipei = sessionNUmberZaipeiListDoc.size() + sessionNUmberZaipeiListMaster.size();
                    List<String> SesionNumberuserFlowList = monthlyReportExtMapper2.getAppUserSum(sessionMap3);//该基地下该年月使用app用户id
                    List<JsResDoctorRecruitExt> SessionNumberappuserDOcList = getUseAppinfo(sessionNUmberZaipeiListDoc, SesionNumberuserFlowList);
                    List<JsResDoctorRecruitExt> SessionNumberappuserMastList = getUseAppinfo(sessionNUmberZaipeiListMaster, SesionNumberuserFlowList);
                    int SessionNumberavguse = SessionNumberappuserDOcList.size() + SessionNumberappuserMastList.size();

                    int sessionDoczaipeiIscontain = sessionNUmberZaipeiListDoc.size() + sessionDoczaipei;
                    int sessionDocuseIscontain = SessionNumberappuserDOcList.size() + sessionDocuse;
                    int sessionMastzaipeiIscontain = sessionNUmberZaipeiListMaster.size() + sessionMastzaipei;
                    int sessionMastuseIscontain = SessionNumberappuserMastList.size() + sessionMastuse;
                    int sessionavgzaipeiIscontain = SessionNumberavgzaipei + sessionavgzaipei;
                    int sessionavguseIscontain = SessionNumberavguse + sessionavguse;

                    String SessionNUmbrdoctorRate = "";
                    String SessionNUmbrmasterRate = "";
                    String SessionNUmbravgRate = "";
                    if (sessionDoczaipeiIscontain == 0) {
                        SessionNUmbrdoctorRate = "0%";
                    }
                    if (sessionDoczaipeiIscontain != 0) {
                        SessionNUmbrdoctorRate = numberFormat.format((float) sessionDocuseIscontain / (float) sessionDoczaipeiIscontain * 100) + "%";
                    }
                    if (sessionMastzaipeiIscontain == 0) {
                        SessionNUmbrmasterRate = "0%";
                    }
                    if (sessionMastzaipeiIscontain != 0) {
                        SessionNUmbrmasterRate = numberFormat.format((float) sessionMastuseIscontain / (float) sessionMastzaipeiIscontain * 100) + "%";
                    }
                    if (sessionavgzaipeiIscontain == 0) {
                        SessionNUmbravgRate = "0%";
                    }
                    if (0 != sessionavgzaipeiIscontain) {
                        SessionNUmbravgRate = numberFormat.format((float) sessionavguseIscontain / (float) sessionavgzaipeiIscontain * 100) + "%";
                    }
                    appUseInfoPojoParam2.setId(i + "-" + j);
                    appUseInfoPojoParam2.setPid(i + "");//
                    appUseInfoPojoParam2.setLevel("2");
                    appUseInfoPojoParam2.setSpeName(sessionNumberDistinct.get(j));//存sessionNumber
                    appUseInfoPojoParam2.setDoctorRateTrainSum(sessionDoczaipeiIscontain + "");
                    appUseInfoPojoParam2.setMasterRateTrainSum(sessionMastzaipeiIscontain + "");
                    appUseInfoPojoParam2.setAvgRateTrainSum(sessionavgzaipeiIscontain + "");
                    appUseInfoPojoParam2.setDoctorRateUseSum(sessionDocuseIscontain + "");
                    appUseInfoPojoParam2.setMasterRateUseSum(sessionMastuseIscontain + "");
                    appUseInfoPojoParam2.setAvgRateUseSum(sessionavguseIscontain + "");

                    appUseInfoPojoParam2.setDoctorRateRate(SessionNUmbrdoctorRate);
                    appUseInfoPojoParam2.setMasterRateRate(SessionNUmbrmasterRate);
                    appUseInfoPojoParam2.setAvgRateRate(SessionNUmbravgRate);

                    appUseInfoPojoParam2.setIsContain("Y");
                    appUseInfoPojoParam2.setMonthDate(monthDate);
                    appUseInfoPojoParam2.setOrgFlow(exitOrgs.get(s).getOrgFlow());
                    appUseInfoPojoParam2.setCreateTime(DateUtil.getCurrDateTime());
                    appUseInfoPojoParam2.setRecordFlow(UUID.randomUUID().toString());

                    bList.add(appUseInfoPojoParam2);
                    speDoczaipei = speDoczaipei + sessionDoczaipeiIscontain;
                    speDocuse = speDocuse + sessionDocuseIscontain;
                    speMastzaipei = speMastzaipei + sessionMastzaipeiIscontain;
                    speMastuse = speMastuse + sessionMastuseIscontain;
                    speavgzaipei = speavgzaipei + sessionavgzaipeiIscontain;
                    speavguse = speavguse + sessionavguseIscontain;

                }
                AppUseInfoPojoParam appUseInfoPojoParam = new AppUseInfoPojoParam();
                Map<String, Object> spemap = new HashMap<>();
                Map<String, Object> spemap2 = new HashMap<>();
                Map<String, Object> spemap3 = new HashMap<>();
                spemap.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                spemap2.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                spemap3.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                spemap3.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
		/*String[] docTypeListDoc ={"Company", "CompanyEntrust", "Social"};
		String[] docTypeListMast ={"Graduate"};*/
                spemap.put("docTypeList", docTypeListDoc);
                spemap2.put("docTypeList", docTypeListMast);
                spemap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                spemap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
                List<JsResDoctorRecruitExt> zaipeiDocList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap);
                List<JsResDoctorRecruitExt> zaipeiMasterList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap2);
                int avgzaipei = zaipeiDocList.size() + zaipeiMasterList.size();
                List<String> userFlowList = monthlyReportExtMapper2.getAppUserSum(spemap3);//该基地下该年月使用app用户id
                List<JsResDoctorRecruitExt> appuserDOcList = getUseAppinfo(zaipeiDocList, userFlowList);
                List<JsResDoctorRecruitExt> appuserMastList = getUseAppinfo(zaipeiMasterList, userFlowList);
                int avguse = appuserDOcList.size() + appuserMastList.size();


                int speDoczaipeiIscontain =/*zaipeiDocList.size()+*/speDoczaipei;
                int speDocuseIscontain =/*appuserDOcList.size()+*/speDocuse;
                int speMastzaipeiIscontain =/*zaipeiMasterList.size()+*/speMastzaipei;
                int speMastuseIscontain =/*appuserMastList.size()+*/speMastuse;
                int speavgzaipeiIscontain =/*avgzaipei+*/speavgzaipei;
                int speavguseIscontain =/*avguse+*/speavguse;

                String doctorRate = "";
                String masterRate = "";
                String avgRate = "";
                if (speDoczaipeiIscontain == 0) {
                    doctorRate = "0%";
                }
                if (speDoczaipeiIscontain != 0) {
                    doctorRate = numberFormat.format((float) speDocuseIscontain / (float) speDoczaipeiIscontain * 100) + "%";
                }
                if (speMastzaipeiIscontain == 0) {
                    masterRate = "0%";
                }
                if (speMastzaipeiIscontain != 0) {
                    masterRate = numberFormat.format((float) speMastuseIscontain / (float) speMastzaipeiIscontain * 100) + "%";
                }
                if (speavgzaipeiIscontain == 0) {
                    avgRate = "0%";
                }
                if (0 != speavgzaipeiIscontain) {
                    avgRate = numberFormat.format((float) speavguseIscontain / (float) speavgzaipeiIscontain * 100) + "%";
                }

                appUseInfoPojoParam.setId(i + "");
                appUseInfoPojoParam.setPid("");
                appUseInfoPojoParam.setLevel("1");
                appUseInfoPojoParam.setSpeName(doctorTrainingSpeList.get(i).getDictName());
                appUseInfoPojoParam.setDoctorRateTrainSum(speDoczaipeiIscontain + "");
                appUseInfoPojoParam.setMasterRateTrainSum(speMastzaipeiIscontain + "");
                appUseInfoPojoParam.setAvgRateTrainSum(speavgzaipeiIscontain + "");
                appUseInfoPojoParam.setDoctorRateUseSum(speDocuseIscontain + "");
                appUseInfoPojoParam.setMasterRateUseSum(speMastuseIscontain + "");
                appUseInfoPojoParam.setAvgRateUseSum(speavguseIscontain + "");

                appUseInfoPojoParam.setDoctorRateRate(doctorRate);
                appUseInfoPojoParam.setMasterRateRate(masterRate);
                appUseInfoPojoParam.setAvgRateRate(avgRate);

                appUseInfoPojoParam.setIsContain("Y");
                appUseInfoPojoParam.setMonthDate(monthDate);
                appUseInfoPojoParam.setOrgFlow(exitOrgs.get(s).getOrgFlow());
                appUseInfoPojoParam.setCreateTime(DateUtil.getCurrDateTime());
                appUseInfoPojoParam.setRecordFlow(UUID.randomUUID().toString());

                bList.add(appUseInfoPojoParam);
            }
            if(bList.size()>0){
               int kk= schdualTaskMapper.insertLocalApp(bList);
                logger.info("-------------------------------LocalApp Insert(isContain)---"+kk+"-----------------------------");
            }

        } else {
            List<AppUseInfoPojoParam> bList = new ArrayList<>();
            /**第一层专业*/
            for (int i = 0; i < doctorTrainingSpeList.size(); i++) {
                AppUseInfoPojoParam appUseInfoPojoParam = new AppUseInfoPojoParam();
                Map<String, Object> spemap = new HashMap<>();
                Map<String, Object> spemap2 = new HashMap<>();
                Map<String, Object> spemap3 = new HashMap<>();
                spemap.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                spemap2.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                spemap3.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                spemap3.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
			/*String[] docTypeListDoc ={"Company", "CompanyEntrust", "Social"};
			String[] docTypeListMast ={"Graduate"};*/
                spemap.put("docTypeList", docTypeListDoc);
                spemap2.put("docTypeList", docTypeListMast);
                spemap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                spemap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
                List<JsResDoctorRecruitExt> zaipeiDocList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap);
                List<JsResDoctorRecruitExt> zaipeiMasterList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap2);
                int avgzaipei = zaipeiDocList.size() + zaipeiMasterList.size();
                List<String> userFlowList = monthlyReportExtMapper2.getAppUserSum(spemap3);//该基地下该年月使用app用户id
                List<JsResDoctorRecruitExt> appuserDOcList = getUseAppinfo(zaipeiDocList, userFlowList);
                List<JsResDoctorRecruitExt> appuserMastList = getUseAppinfo(zaipeiMasterList, userFlowList);
                int avguse = appuserDOcList.size() + appuserMastList.size();

                String doctorRate = "";
                String masterRate = "";
                String avgRate = "";
                if (zaipeiDocList.size() == 0) {
                    doctorRate = "0%";
                }
                if (null != zaipeiDocList && !"".equals(zaipeiDocList) && zaipeiDocList.size() != 0) {
                    doctorRate = numberFormat.format((float) appuserDOcList.size() / (float) zaipeiDocList.size() * 100) + "%";
                }
                if (zaipeiMasterList.size() == 0) {
                    masterRate = "0%";
                }
                if (null != zaipeiMasterList && !"".equals(zaipeiMasterList) && zaipeiMasterList.size() != 0) {
                    masterRate = numberFormat.format((float) appuserMastList.size() / (float) zaipeiMasterList.size() * 100) + "%";
                }
                if (avgzaipei == 0) {
                    avgRate = "0%";
                }
                if (0 != avgzaipei) {
                    avgRate = numberFormat.format((float) avguse / (float) avgzaipei * 100) + "%";
                }

                appUseInfoPojoParam.setId(i + "");
                appUseInfoPojoParam.setPid("");
                appUseInfoPojoParam.setLevel("1");
                appUseInfoPojoParam.setSpeName(doctorTrainingSpeList.get(i).getDictName());
                appUseInfoPojoParam.setDoctorRateTrainSum(zaipeiDocList.size() + "");
                appUseInfoPojoParam.setMasterRateTrainSum(zaipeiMasterList.size() + "");
                appUseInfoPojoParam.setAvgRateTrainSum(avgzaipei + "");
                appUseInfoPojoParam.setDoctorRateUseSum(appuserDOcList.size() + "");
                appUseInfoPojoParam.setMasterRateUseSum(appuserMastList.size() + "");
                appUseInfoPojoParam.setAvgRateUseSum(avguse + "");

                appUseInfoPojoParam.setDoctorRateRate(doctorRate);
                appUseInfoPojoParam.setMasterRateRate(masterRate);
                appUseInfoPojoParam.setAvgRateRate(avgRate);

                appUseInfoPojoParam.setMonthDate(monthDate);
                appUseInfoPojoParam.setIsContain("N");
                appUseInfoPojoParam.setOrgFlow(exitOrgs.get(s).getOrgFlow());
                appUseInfoPojoParam.setCreateTime(DateUtil.getCurrDateTime());
                appUseInfoPojoParam.setRecordFlow(UUID.randomUUID().toString());

                bList.add(appUseInfoPojoParam);
                /**年级*/
                Map<String, Object> sessionNumbermap = new HashMap<>();
                List<String> sessionNumberDistinct = new ArrayList<>();
                sessionNumbermap.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                String[] docTypeList = {"Company", "CompanyEntrust", "Social", "Graduate"};
                sessionNumbermap.put("docTypeList", docTypeList);
                sessionNumbermap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                List<JsResDoctorRecruitExt> sessionNUmberList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionNumbermap);
                for (JsResDoctorRecruitExt jrs : sessionNUmberList) {
                    if (!sessionNumberDistinct.contains(jrs.getSessionNumber())) {
                        sessionNumberDistinct.add(jrs.getSessionNumber());
                    }
                }
                for (int j = 0; j < sessionNumberDistinct.size(); j++) {
                    AppUseInfoPojoParam appUseInfoPojoParam2 = new AppUseInfoPojoParam();
                    Map<String, Object> sessionMap = new HashMap<>();
                    Map<String, Object> sessionMap2 = new HashMap<>();
                    Map<String, Object> sessionMap3 = new HashMap<>();
                    sessionMap.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                    sessionMap2.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                    sessionMap.put("docTypeList", docTypeListDoc);
                    sessionMap2.put("docTypeList", docTypeListMast);
                    sessionMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                    sessionMap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
                    sessionMap.put("sessionNumber", sessionNumberDistinct.get(j));
                    sessionMap2.put("sessionNumber", sessionNumberDistinct.get(j));
                    sessionMap3.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                    sessionMap3.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
                    List<JsResDoctorRecruitExt> sessionNUmberZaipeiListDoc = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionMap);
                    List<JsResDoctorRecruitExt> sessionNUmberZaipeiListMaster = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionMap2);
                    int SessionNumberavgzaipei = sessionNUmberZaipeiListDoc.size() + sessionNUmberZaipeiListMaster.size();
                    List<String> SesionNumberuserFlowList = monthlyReportExtMapper2.getAppUserSum(sessionMap3);//该基地下该年月使用app用户id
                    List<JsResDoctorRecruitExt> SessionNumberappuserDOcList = getUseAppinfo(sessionNUmberZaipeiListDoc, SesionNumberuserFlowList);
                    List<JsResDoctorRecruitExt> SessionNumberappuserMastList = getUseAppinfo(sessionNUmberZaipeiListMaster, SesionNumberuserFlowList);
                    int SessionNumberavguse = SessionNumberappuserDOcList.size() + SessionNumberappuserMastList.size();

                    String SessionNUmbrdoctorRate = "";
                    String SessionNUmbrmasterRate = "";
                    String SessionNUmbravgRate = "";
                    if (sessionNUmberZaipeiListDoc.size() == 0) {
                        SessionNUmbrdoctorRate = "0%";
                    }
                    if (null != sessionNUmberZaipeiListDoc && !"".equals(sessionNUmberZaipeiListDoc) && sessionNUmberZaipeiListDoc.size() != 0) {
                        SessionNUmbrdoctorRate = numberFormat.format((float) SessionNumberappuserDOcList.size() / (float) sessionNUmberZaipeiListDoc.size() * 100) + "%";
                    }
                    if (sessionNUmberZaipeiListMaster.size() == 0) {
                        SessionNUmbrmasterRate = "0%";
                    }
                    if (null != sessionNUmberZaipeiListMaster && !"".equals(sessionNUmberZaipeiListMaster) && sessionNUmberZaipeiListMaster.size() != 0) {
                        SessionNUmbrmasterRate = numberFormat.format((float) SessionNumberappuserMastList.size() / (float) sessionNUmberZaipeiListMaster.size() * 100) + "%";
                    }
                    if (SessionNumberavgzaipei == 0) {
                        SessionNUmbravgRate = "0%";
                    }
                    if (0 != SessionNumberavgzaipei) {
                        SessionNUmbravgRate = numberFormat.format((float) SessionNumberavguse / (float) SessionNumberavgzaipei * 100) + "%";
                    }
                    appUseInfoPojoParam2.setId(i + "-" + j);
                    appUseInfoPojoParam2.setPid(appUseInfoPojoParam.getId());//
                    appUseInfoPojoParam2.setLevel("2");
                    appUseInfoPojoParam2.setSpeName(sessionNumberDistinct.get(j));//存sessionNumber
                    appUseInfoPojoParam2.setDoctorRateTrainSum(sessionNUmberZaipeiListDoc.size() + "");
                    appUseInfoPojoParam2.setMasterRateTrainSum(sessionNUmberZaipeiListMaster.size() + "");
                    appUseInfoPojoParam2.setAvgRateTrainSum(SessionNumberavgzaipei + "");
                    appUseInfoPojoParam2.setDoctorRateUseSum(SessionNumberappuserDOcList.size() + "");
                    appUseInfoPojoParam2.setMasterRateUseSum(SessionNumberappuserMastList.size() + "");
                    appUseInfoPojoParam2.setAvgRateUseSum(SessionNumberavguse + "");

                    appUseInfoPojoParam2.setDoctorRateRate(SessionNUmbrdoctorRate);
                    appUseInfoPojoParam2.setMasterRateRate(SessionNUmbrmasterRate);
                    appUseInfoPojoParam2.setAvgRateRate(SessionNUmbravgRate);

                    appUseInfoPojoParam2.setMonthDate(monthDate);
                    appUseInfoPojoParam2.setIsContain("N");
                    appUseInfoPojoParam2.setOrgFlow(exitOrgs.get(s).getOrgFlow());
                    appUseInfoPojoParam2.setCreateTime(DateUtil.getCurrDateTime());
                    appUseInfoPojoParam2.setRecordFlow(UUID.randomUUID().toString());

                    bList.add(appUseInfoPojoParam2);
                    if (joinorgList.size() > 0) {
                        for (int k = 0; k < joinorgList.size(); k++) {
                            AppUseInfoPojoParam appUseInfoPojoParam3 = new AppUseInfoPojoParam();
                            Map<String, Object> joinOrgMap = new HashMap<>();
                            Map<String, Object> joinOrgMap2 = new HashMap<>();
                            Map<String, Object> joinOrgMap3 = new HashMap<>();
                            joinOrgMap.put("orgFlow", joinorgList.get(k).getOrgFlow());
                            joinOrgMap2.put("orgFlow", joinorgList.get(k).getOrgFlow());
                            joinOrgMap.put("docTypeList", docTypeListDoc);
                            joinOrgMap2.put("docTypeList", docTypeListMast);
                            joinOrgMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                            joinOrgMap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
                            joinOrgMap.put("sessionNumber", sessionNumberDistinct.get(j));
                            joinOrgMap2.put("sessionNumber", sessionNumberDistinct.get(j));
                            joinOrgMap3.put("orgFlow", joinorgList.get(k).getOrgFlow());
                            joinOrgMap3.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
                            List<JsResDoctorRecruitExt> joinorgZaipeiListDoc = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(joinOrgMap);
                            List<JsResDoctorRecruitExt> joinorgZaipeiListMaster = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(joinOrgMap2);
                            int joinorgavgzaipei = joinorgZaipeiListDoc.size() + joinorgZaipeiListMaster.size();
                            List<String> joinorguserFlowList = monthlyReportExtMapper2.getAppUserSum(joinOrgMap3);//该基地下该年月使用app用户id
                            List<JsResDoctorRecruitExt> joinorgappuserDOcList = getUseAppinfo(joinorgZaipeiListDoc, joinorguserFlowList);
                            List<JsResDoctorRecruitExt> joinorgappuserMastList = getUseAppinfo(joinorgZaipeiListMaster, joinorguserFlowList);
                            int joinorgavguse = joinorgappuserDOcList.size() + joinorgappuserMastList.size();

                            String joinorgdoctorRate = "";
                            String joinorgmasterRate = "";
                            String joinorgavgRate = "";
                            if (joinorgZaipeiListDoc.size() == 0) {
                                joinorgdoctorRate = "0%";
                            }
                            if (null != joinorgZaipeiListDoc && !"".equals(joinorgZaipeiListDoc) && joinorgZaipeiListDoc.size() != 0) {
                                joinorgdoctorRate = numberFormat.format((float) joinorgappuserDOcList.size() / (float) joinorgZaipeiListDoc.size() * 100) + "%";
                            }
                            if (joinorgZaipeiListMaster.size() == 0) {
                                joinorgmasterRate = "0%";
                            }
                            if (null != joinorgZaipeiListMaster && !"".equals(joinorgZaipeiListMaster) && joinorgZaipeiListMaster.size() != 0) {
                                joinorgmasterRate = numberFormat.format((float) joinorgappuserMastList.size() / (float) joinorgZaipeiListMaster.size() * 100) + "%";
                            }
                            if (joinorgavgzaipei == 0) {
                                joinorgavgRate = "0%";
                            }
                            if (0 != joinorgavgzaipei) {
                                joinorgavgRate = numberFormat.format((float) joinorgavguse / (float) joinorgavgzaipei * 100) + "%";
                            }
                            //
                            appUseInfoPojoParam3.setId(i + "-" + j + "-" + k);
                            appUseInfoPojoParam3.setPid(appUseInfoPojoParam2.getId());//
                            appUseInfoPojoParam3.setLevel("3");
                            appUseInfoPojoParam3.setSpeName(joinorgList.get(k).getOrgName());//存协同基地
                            appUseInfoPojoParam3.setDoctorRateTrainSum(joinorgZaipeiListDoc.size() + "");
                            appUseInfoPojoParam3.setMasterRateTrainSum(joinorgZaipeiListMaster.size() + "");
                            appUseInfoPojoParam3.setAvgRateTrainSum(joinorgavgzaipei + "");
                            appUseInfoPojoParam3.setDoctorRateUseSum(joinorgappuserDOcList.size() + "");
                            appUseInfoPojoParam3.setMasterRateUseSum(joinorgappuserMastList.size() + "");
                            appUseInfoPojoParam3.setAvgRateUseSum(joinorgavguse + "");

                            appUseInfoPojoParam3.setDoctorRateRate(joinorgdoctorRate);
                            appUseInfoPojoParam3.setMasterRateRate(joinorgmasterRate);
                            appUseInfoPojoParam3.setAvgRateRate(joinorgavgRate);

                            appUseInfoPojoParam3.setMonthDate(monthDate);
                            appUseInfoPojoParam3.setIsContain("N");
                            appUseInfoPojoParam3.setOrgFlow(exitOrgs.get(s).getOrgFlow());
                            appUseInfoPojoParam3.setCreateTime(DateUtil.getCurrDateTime());
                            appUseInfoPojoParam3.setRecordFlow(UUID.randomUUID().toString());


                            bList.add(appUseInfoPojoParam3);
                        }
                    }

                }
            }
            if(bList.size()>0){
               int kk= schdualTaskMapper.insertLocalApp(bList);
                logger.info("-------------------------------LocalApp(notContain) Insert---"+kk+"-----------------------------");
            }

        }

    }
  }
  getOtherMethod(startTime);
}

/**
 *  @author: yuh
 *  @Date: 2020/4/1 16:01
 *  @Description:  4 定时任务
 */
public void getOtherMethod(long startTime) throws ParseException {
    localDoctorLunZhuanDataInsertData();
    shengShiUniverstiJob.shengshiApp();
    shengShiUniverstiJob.shengshiTeachActive();
    shengShiUniverstiJob.shengshiDoctorOutOffice();
    shengShiUniverstiJob.shengshiDoctorLUNZHUAN_FIND();
    long endTime = System.currentTimeMillis(); //获取结束时间
    logger.info("----------------------程序运行时间:" + (endTime - startTime) + "ms----------------------");
    logger.info("----------------------End My Schduled Doing----------------------");

}
    // 获取最近N个月
    public  String getLastMonths(int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, i);
        Date m = c.getTime();
        return sdf.format(m);
    }

    public List<JsResDoctorRecruitExt> getUseAppinfo(List<JsResDoctorRecruitExt> list,List<String> userFlowList){
        List<JsResDoctorRecruitExt> listnew=new ArrayList<>();
        for(JsResDoctorRecruitExt js:list){
            boolean falg=false;
            for(String li:userFlowList){
                if(js.getDoctorFlow().equals(li)){
                    falg=true;
                    break;
                }
            }
            if(falg){
                listnew.add(js);
            }
        }
        return listnew;
    }



    /**
     *  @author: yuh
     *  @Date: 2020/3/17 17:19
     *  @Description: 基地 学员轮转数据月报数据插入
     */
//    @Scheduled(cron="0 10 3 1 * ?")//每月1号凌晨3点10分执行一次
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void localDoctorLunZhuanDataInsertData(){
        String monthDate = InitConfig.getSysCfg("res_static_job_month");
        if(StringUtil.isBlank(monthDate)){
            monthDate = getLastMonths(-1); //获取上月  -9
        }
        schdualTaskMapper.deleteAllPreviousMonthDataByDoctorLunZhuanData(monthDate); //先删除上月的数据，

        String[] a = new String[2];
        a[0] = "";
        a[1] = "isContain";

       /* List<DoctorLunZhuanDataMonthReport> bList=new ArrayList<>();*/
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位

        List<SysOrg> joinorgList = new ArrayList<>();
        SysOrg searchOrg=new SysOrg();
        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
        searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        searchOrg.setOrgProvId("320000");  //江苏省
        List<SysOrg> exitOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
for(int s=0;s<exitOrgs.size();s++){

        //orgs.add(currOrg);//获取机构和协同机构
        if (OrgLevelEnum.CountryOrg.getId().equals(exitOrgs.get(s).getOrgLevelId())) {
            joinorgList = orgBiz.searchJointOrgsByOrg(exitOrgs.get(s).getOrgFlow());
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dictTypeId", DictTypeEnum.DoctorTrainingSpe.getId());
        List<SysDict> doctorTrainingSpeList = this.resDoctorRecruitExtMapper.searchTrainSpeList(paramMap);//所有专业
        List<String> datas =new ArrayList<>();
        datas.add("both");
        datas.add("notGraduate");
        datas.add("graduate");

        /*if (datas.size() == 2) {
            doctype[0] = "Company";
            doctype[1] = "CompanyEntrust";
            doctype[2] = "Social";
            doctype[3] = "Graduate";
        } else {
            if (datas[0].equals("NotGraduate")) {
                doctype[0] = "Company";
                doctype[1] = "CompanyEntrust";
                doctype[2] = "Social";
            } else {
                doctype[0] = "Graduate";
            }
        }*/
    for(int count =0 ;count<a.length;count++) {

        for (int m = 0; m < datas.size(); m++) {
            String[] doctype = new String[4];
            List<DoctorLunZhuanDataMonthReport> bList=new ArrayList<>();
            if ("both".equals(datas.get(m))) {
                doctype[0] = "Company";
                doctype[1] = "CompanyEntrust";
                doctype[2] = "Social";
                doctype[3] = "Graduate";
            } else {
                if ("notGraduate".equals(datas.get(m))) {
                    doctype[0] = "Company";
                    doctype[1] = "CompanyEntrust";
                    doctype[2] = "Social";
                } else {
                    doctype[0] = "Graduate";
                }
            }
        for (int i = 0; i < doctorTrainingSpeList.size(); i++) {
            DoctorLunZhuanDataMonthReport doctorLunZhuanDataMonthReport = new DoctorLunZhuanDataMonthReport();
            Map<String, Object> spemap = new HashMap<>();
            spemap.put("orgFlow", exitOrgs.get(s).getOrgFlow());
            spemap.put("docTypeList", doctype);
            spemap.put("speId", doctorTrainingSpeList.get(i).getDictId());
            List<JsResDoctorRecruitExt> zaipeiList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap);
            spemap.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
            DoctorLunZhuanDataMonthReport dlzdmr = monthlyReportExtMapper2.findDoctorLunZhuanDataMonthReport(spemap);

            doctorLunZhuanDataMonthReport.setId(i + "");
            doctorLunZhuanDataMonthReport.setPid("");
            doctorLunZhuanDataMonthReport.setLevel("1");
            doctorLunZhuanDataMonthReport.setSpeName(doctorTrainingSpeList.get(i).getDictName());
            doctorLunZhuanDataMonthReport.setSpeId(doctorTrainingSpeList.get(i).getDictId());

            doctorLunZhuanDataMonthReport.setTrainSum(zaipeiList.size());
            doctorLunZhuanDataMonthReport.setWriteSum(dlzdmr.getWriteSum());
            doctorLunZhuanDataMonthReport.setDataAuditSum(dlzdmr.getDataAuditSum());
            BigDecimal trainSum = new BigDecimal(zaipeiList.size());
            BigDecimal writeSum = new BigDecimal(dlzdmr.getWriteSum());
            BigDecimal dataAuditSum = new BigDecimal(dlzdmr.getDataAuditSum());
            Double auditScale = 0.00;
            if (dlzdmr.getWriteSum() != 0) {
                BigDecimal c = dataAuditSum.divide(writeSum, 2, BigDecimal.ROUND_HALF_UP);
                auditScale = c.doubleValue();
            }
            doctorLunZhuanDataMonthReport.setAuditScale(auditScale * 100 + "%");
            Double aveWriteSum = 0.00;
            Double aveAuditSum = 0.00;
            if (zaipeiList.size() != 0) {
                BigDecimal c = writeSum.divide(trainSum, 2, BigDecimal.ROUND_HALF_UP);
                aveWriteSum = c.doubleValue();
                BigDecimal aveAuditcount = dataAuditSum.divide(trainSum, 2, BigDecimal.ROUND_HALF_UP);
                aveAuditSum = aveAuditcount.doubleValue();
            }
            doctorLunZhuanDataMonthReport.setAveWriteSum(aveWriteSum);
            Double aveAuditScale = 0.00;
            BigDecimal aveAuditSumBigdecimal = new BigDecimal(aveAuditSum);
            BigDecimal aveWriteSumBigdecimal = new BigDecimal(aveWriteSum);
            if (aveWriteSum != 0.00) {
                BigDecimal c = aveAuditSumBigdecimal.divide(aveWriteSumBigdecimal, 2, BigDecimal.ROUND_HALF_UP);
                aveAuditScale = c.doubleValue();
            }
            doctorLunZhuanDataMonthReport.setAveAuditScale((aveAuditScale * 100) + "%");

            if("".equals(a[count])){
                doctorLunZhuanDataMonthReport.setIsContain("N");
            }else{
                doctorLunZhuanDataMonthReport.setIsContain("Y");
            }
            doctorLunZhuanDataMonthReport.setMonthDate(monthDate);
            doctorLunZhuanDataMonthReport.setOrgFlow(exitOrgs.get(s).getOrgFlow());
            doctorLunZhuanDataMonthReport.setCreateTime(DateUtil.getCurrDateTime());
            doctorLunZhuanDataMonthReport.setRecordFlow(UUID.randomUUID().toString());
            if("both".equals(datas.get(m))){
                doctorLunZhuanDataMonthReport.setNotGraduate("Y");
                doctorLunZhuanDataMonthReport.setGraduate("Y");
            }else {
                if ("notGraduate".equals(datas.get(m))) {
                    doctorLunZhuanDataMonthReport.setNotGraduate("Y");
                    doctorLunZhuanDataMonthReport.setGraduate("N");
                } else {
                    doctorLunZhuanDataMonthReport.setNotGraduate("N");
                    doctorLunZhuanDataMonthReport.setGraduate("Y");
                }
            }


            bList.add(doctorLunZhuanDataMonthReport);
            /**年级*/
            Map<String, Object> sessionNumbermap = new HashMap<>();
            List<String> sessionNumberDistinct = new ArrayList<>();
            sessionNumbermap.put("orgFlow", exitOrgs.get(s).getOrgFlow());
            String[] docTypeList = {"Company", "CompanyEntrust", "Social", "Graduate"};
            sessionNumbermap.put("docTypeList", docTypeList);
            sessionNumbermap.put("speId", doctorTrainingSpeList.get(i).getDictId());
            List<JsResDoctorRecruitExt> sessionNUmberList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionNumbermap);
            for (JsResDoctorRecruitExt jrs : sessionNUmberList) {
                if (!sessionNumberDistinct.contains(jrs.getSessionNumber())) {
                    sessionNumberDistinct.add(jrs.getSessionNumber());
                }
            }
            for (int j = 0; j < sessionNumberDistinct.size(); j++) {
                DoctorLunZhuanDataMonthReport doctorLunZhuanDataMonthReport2 = new DoctorLunZhuanDataMonthReport();
                Map<String, Object> spemap1 = new HashMap<>();
                spemap1.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                spemap1.put("docTypeList", doctype);
                spemap1.put("speId", doctorTrainingSpeList.get(i).getDictId());
                spemap1.put("sessionNumber", sessionNumberDistinct.get(j));
                List<JsResDoctorRecruitExt> zaipeiList1 = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap1);
                spemap1.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                DoctorLunZhuanDataMonthReport dlzdmr1 = monthlyReportExtMapper2.findDoctorLunZhuanDataMonthReport(spemap1);

                doctorLunZhuanDataMonthReport2.setId(i + "-" + j);
                doctorLunZhuanDataMonthReport2.setPid(doctorLunZhuanDataMonthReport.getId());//
                doctorLunZhuanDataMonthReport2.setLevel("2");
                doctorLunZhuanDataMonthReport2.setSpeName(sessionNumberDistinct.get(j));//存sessionNumber

                doctorLunZhuanDataMonthReport2.setTrainSum(zaipeiList1.size());
                doctorLunZhuanDataMonthReport2.setWriteSum(dlzdmr1.getWriteSum());
                doctorLunZhuanDataMonthReport2.setDataAuditSum(dlzdmr1.getDataAuditSum());
                BigDecimal trainSum1 = new BigDecimal(zaipeiList1.size());
                BigDecimal writeSum1 = new BigDecimal(dlzdmr1.getWriteSum());
                BigDecimal dataAuditSum1 = new BigDecimal(dlzdmr1.getDataAuditSum());
                Double auditScale1 = 0.00;
                if (dlzdmr1.getWriteSum() != 0) {
                    BigDecimal c = dataAuditSum1.divide(writeSum1, 2, BigDecimal.ROUND_HALF_UP);
                    auditScale1 = c.doubleValue();
                }
                doctorLunZhuanDataMonthReport2.setAuditScale(auditScale1 * 100 + "%");
                Double aveWriteSum1 = 0.00;
                Double aveAuditSum1 = 0.00;
                if (zaipeiList1.size() != 0) {
                    BigDecimal c = writeSum1.divide(trainSum1, 2, BigDecimal.ROUND_HALF_UP);
                    aveWriteSum1 = c.doubleValue();
                    BigDecimal aveAuditcount = dataAuditSum1.divide(trainSum1, 2, BigDecimal.ROUND_HALF_UP);
                    aveAuditSum1 = aveAuditcount.doubleValue();
                }
                doctorLunZhuanDataMonthReport2.setAveWriteSum(aveWriteSum1);
                Double aveAuditScale1 = 0.00;
                BigDecimal aveAuditSumBigdecimal1 = new BigDecimal(aveAuditSum1);
                BigDecimal aveWriteSumBigdecimal1 = new BigDecimal(aveWriteSum1);
                if (aveWriteSum1 != 0.00) {
                    BigDecimal c = aveAuditSumBigdecimal1.divide(aveWriteSumBigdecimal1, 2, BigDecimal.ROUND_HALF_UP);
                    aveAuditScale1 = c.doubleValue();
                }
                doctorLunZhuanDataMonthReport2.setAveAuditScale((aveAuditScale1 * 100) + "%");

                if("".equals(a[count])){
                    doctorLunZhuanDataMonthReport2.setIsContain("N");
                }else{
                    doctorLunZhuanDataMonthReport2.setIsContain("Y");
                }
                doctorLunZhuanDataMonthReport2.setMonthDate(monthDate);
                doctorLunZhuanDataMonthReport2.setOrgFlow(exitOrgs.get(s).getOrgFlow());
                doctorLunZhuanDataMonthReport2.setCreateTime(DateUtil.getCurrDateTime());
                doctorLunZhuanDataMonthReport2.setRecordFlow(UUID.randomUUID().toString());
                if("both".equals(datas.get(m))){
                    doctorLunZhuanDataMonthReport2.setNotGraduate("Y");
                    doctorLunZhuanDataMonthReport2.setGraduate("Y");
                }else {
                    if ("notGraduate".equals(datas.get(m))) {
                        doctorLunZhuanDataMonthReport2.setNotGraduate("Y");
                        doctorLunZhuanDataMonthReport2.setGraduate("N");
                    } else {
                        doctorLunZhuanDataMonthReport2.setNotGraduate("N");
                        doctorLunZhuanDataMonthReport2.setGraduate("Y");
                    }
                }

                bList.add(doctorLunZhuanDataMonthReport2);
                if (joinorgList.size() > 0) {
                    for (int k = 0; k < joinorgList.size(); k++) {
                        DoctorLunZhuanDataMonthReport doctorLunZhuanDataMonthReport3 = new DoctorLunZhuanDataMonthReport();                        // 第三层协同 入科异常 出科异常 出科考核异常
                        Map<String, Object> spemap2 = new HashMap<>();
                        spemap2.put("orgFlow", joinorgList.get(k).getOrgFlow());
                        spemap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
                        spemap2.put("docTypeList", doctype);
                        spemap2.put("sessionNumber", sessionNumberDistinct.get(j));
                        List<JsResDoctorRecruitExt> zaipeiList2 = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap2);
                        spemap2.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                        DoctorLunZhuanDataMonthReport dlzdmr2 = monthlyReportExtMapper2.findDoctorLunZhuanDataMonthReport(spemap2);

                        doctorLunZhuanDataMonthReport3.setId(i + "-" + j + "-" + k);
                        doctorLunZhuanDataMonthReport3.setPid(doctorLunZhuanDataMonthReport2.getId());//
                        doctorLunZhuanDataMonthReport3.setLevel("3");
                        doctorLunZhuanDataMonthReport3.setSpeName(joinorgList.get(k).getOrgName());//存协同基地

                        doctorLunZhuanDataMonthReport3.setTrainSum(zaipeiList2.size());
                        doctorLunZhuanDataMonthReport3.setWriteSum(dlzdmr2.getWriteSum());
                        doctorLunZhuanDataMonthReport3.setDataAuditSum(dlzdmr2.getDataAuditSum());
                        BigDecimal trainSum2 = new BigDecimal(zaipeiList2.size());
                        BigDecimal writeSum2 = new BigDecimal(dlzdmr2.getWriteSum());
                        BigDecimal dataAuditSum2 = new BigDecimal(dlzdmr2.getDataAuditSum());
                        Double auditScale2 = 0.00;
                        if (dlzdmr2.getWriteSum() != 0) {
                            BigDecimal c = dataAuditSum2.divide(writeSum2, 2, BigDecimal.ROUND_HALF_UP);
                            auditScale2 = c.doubleValue();
                        }
                        doctorLunZhuanDataMonthReport3.setAuditScale(auditScale2 * 100 + "%");
                        Double aveWriteSum2 = 0.00;
                        Double aveAuditSum2 = 0.00;
                        if (zaipeiList2.size() != 0) {
                            BigDecimal c = writeSum2.divide(trainSum2, 2, BigDecimal.ROUND_HALF_UP);
                            aveWriteSum2 = c.doubleValue();
                            BigDecimal aveAuditcount = dataAuditSum2.divide(trainSum2, 2, BigDecimal.ROUND_HALF_UP);
                            aveAuditSum2 = aveAuditcount.doubleValue();
                        }
                        doctorLunZhuanDataMonthReport3.setAveWriteSum(aveWriteSum2);
                        Double aveAuditScale2 = 0.00;
                        BigDecimal aveAuditSumBigdecimal2 = new BigDecimal(aveAuditSum2);
                        BigDecimal aveWriteSumBigdecimal2 = new BigDecimal(aveWriteSum2);
                        if (aveWriteSum2 != 0.00) {
                            BigDecimal c = aveAuditSumBigdecimal2.divide(aveWriteSumBigdecimal2, 2, BigDecimal.ROUND_HALF_UP);
                            aveAuditScale2 = c.doubleValue();
                        }
                        doctorLunZhuanDataMonthReport3.setAveAuditScale((aveAuditScale2 * 100) + "%");

                        if("".equals(a[count])){
                            doctorLunZhuanDataMonthReport3.setIsContain("N");
                        }else{
                            doctorLunZhuanDataMonthReport3.setIsContain("Y");
                        }
                        doctorLunZhuanDataMonthReport3.setMonthDate(monthDate);
                        doctorLunZhuanDataMonthReport3.setOrgFlow(exitOrgs.get(s).getOrgFlow());
                        doctorLunZhuanDataMonthReport3.setCreateTime(DateUtil.getCurrDateTime());
                        doctorLunZhuanDataMonthReport3.setRecordFlow(UUID.randomUUID().toString());
                        if("both".equals(datas.get(m))){
                            doctorLunZhuanDataMonthReport3.setNotGraduate("Y");
                            doctorLunZhuanDataMonthReport3.setGraduate("Y");
                        }else {
                            if ("notGraduate".equals(datas.get(m))) {
                                doctorLunZhuanDataMonthReport3.setNotGraduate("Y");
                                doctorLunZhuanDataMonthReport3.setGraduate("N");
                            } else {
                                doctorLunZhuanDataMonthReport3.setNotGraduate("N");
                                doctorLunZhuanDataMonthReport3.setGraduate("Y");
                            }
                        }

                        bList.add(doctorLunZhuanDataMonthReport3);
                    }
                }

            }
        }
        if ("isContain".equals(a[count])) {
            for (int i = 0; i < bList.size(); i++) {
                if ("2".equals(bList.get(i).getLevel())) {
                    DoctorLunZhuanDataMonthReport jj = getChildrenInfoLocalDataMonthReport("", bList.get(i).getId(), bList);
                    Integer trainSum = bList.get(i).getTrainSum() + jj.getTrainSum();
                    Integer writeSum = bList.get(i).getWriteSum() + jj.getWriteSum();
                    Integer dataAuditSum = bList.get(i).getDataAuditSum() + jj.getDataAuditSum();

                    bList.get(i).setTrainSum(trainSum);
                    bList.get(i).setWriteSum(writeSum);
                    bList.get(i).setDataAuditSum(dataAuditSum);
                    BigDecimal trainSum2 = new BigDecimal(trainSum);
                    BigDecimal writeSum2 = new BigDecimal(writeSum);
                    BigDecimal dataAuditSum2 = new BigDecimal(dataAuditSum);
                    Double auditScale2 = 0.00;
                    if (writeSum != 0) {
                        BigDecimal c = dataAuditSum2.divide(writeSum2, 2, BigDecimal.ROUND_HALF_UP);
                        auditScale2 = c.doubleValue();
                    }
                    bList.get(i).setAuditScale(auditScale2 * 100 + "%");
                    Double aveWriteSum2 = 0.00;
                    Double aveAuditSum2 = 0.00;
                    if (trainSum != 0) {
                        BigDecimal c = writeSum2.divide(trainSum2, 2, BigDecimal.ROUND_HALF_UP);
                        aveWriteSum2 = c.doubleValue();
                        BigDecimal aveAuditcount = dataAuditSum2.divide(trainSum2, 2, BigDecimal.ROUND_HALF_UP);
                        aveAuditSum2 = aveAuditcount.doubleValue();
                    }
                    bList.get(i).setAveWriteSum(aveWriteSum2);
                    Double aveAuditScale2 = 0.00;
                    BigDecimal aveAuditSumBigdecimal2 = new BigDecimal(aveAuditSum2);
                    BigDecimal aveWriteSumBigdecimal2 = new BigDecimal(aveWriteSum2);
                    if (aveWriteSum2 != 0.00) {
                        BigDecimal c = aveAuditSumBigdecimal2.divide(aveWriteSumBigdecimal2, 2, BigDecimal.ROUND_HALF_UP);
                        aveAuditScale2 = c.doubleValue();
                    }
                    bList.get(i).setAveAuditScale((aveAuditScale2 * 100) + "%");
                }
            }
            for (int i = 0; i < bList.size(); i++) {
                if ("1".equals(bList.get(i).getLevel())) {
                    DoctorLunZhuanDataMonthReport jj = getChildrenInfoLocalDataMonthReport("1", bList.get(i).getId(), bList);
                    Integer trainSum = bList.get(i).getTrainSum() + jj.getTrainSum();
                    Integer writeSum = bList.get(i).getWriteSum() + jj.getWriteSum();
                    Integer dataAuditSum = bList.get(i).getDataAuditSum() + jj.getDataAuditSum();

                    bList.get(i).setTrainSum(trainSum);
                    bList.get(i).setWriteSum(writeSum);
                    bList.get(i).setDataAuditSum(dataAuditSum);
                    BigDecimal trainSum2 = new BigDecimal(trainSum);
                    BigDecimal writeSum2 = new BigDecimal(writeSum);
                    BigDecimal dataAuditSum2 = new BigDecimal(dataAuditSum);
                    Double auditScale2 = 0.00;
                    if (writeSum != 0) {
                        BigDecimal c = dataAuditSum2.divide(writeSum2, 2, BigDecimal.ROUND_HALF_UP);
                        auditScale2 = c.doubleValue();
                    }
                    bList.get(i).setAuditScale(auditScale2 * 100 + "%");
                    Double aveWriteSum2 = 0.00;
                    Double aveAuditSum2 = 0.00;
                    if (trainSum != 0) {
                        BigDecimal c = writeSum2.divide(trainSum2, 2, BigDecimal.ROUND_HALF_UP);
                        aveWriteSum2 = c.doubleValue();
                        BigDecimal aveAuditcount = dataAuditSum2.divide(trainSum2, 2, BigDecimal.ROUND_HALF_UP);
                        aveAuditSum2 = aveAuditcount.doubleValue();
                    }
                    bList.get(i).setAveWriteSum(aveWriteSum2);
                    Double aveAuditScale2 = 0.00;
                    BigDecimal aveAuditSumBigdecimal2 = new BigDecimal(aveAuditSum2);
                    BigDecimal aveWriteSumBigdecimal2 = new BigDecimal(aveWriteSum2);
                    if (aveWriteSum2 != 0.00) {
                        BigDecimal c = aveAuditSumBigdecimal2.divide(aveWriteSumBigdecimal2, 2, BigDecimal.ROUND_HALF_UP);
                        aveAuditScale2 = c.doubleValue();
                    }
                    bList.get(i).setAveAuditScale((aveAuditScale2 * 100) + "%");

                }
            }
        }
        if(bList.size()>0){
            schdualTaskMapper.insertLocalDoctorLunzhuanData(bList);
        }

    }
    }
      //  return bList;

}
}


    public DoctorLunZhuanDataMonthReport getChildrenInfoLocalDataMonthReport(String level ,String id,List<DoctorLunZhuanDataMonthReport> blist){
        DoctorLunZhuanDataMonthReport jj=new DoctorLunZhuanDataMonthReport();
        if("".equals(level)){
            Integer trainSum=0;
            Integer writeSum=0;
            Integer dataAuditSum=0;
            for(int i=0;i<blist.size();i++){
                if(id.equals(blist.get(i).getPid())){
                    trainSum = trainSum + blist.get(i).getTrainSum(); //在培人数
                    writeSum = writeSum + blist.get(i).getWriteSum(); //数据填写量
                    dataAuditSum = dataAuditSum + blist.get(i).getDataAuditSum(); //数据审核量
                }
            }
            jj.setTrainSum(trainSum);
            jj.setWriteSum(writeSum);
            jj.setDataAuditSum(dataAuditSum);
        }else{
            Integer trainSum=0;
            Integer writeSum=0;
            Integer dataAuditSum=0;
            for(int i=0;i<blist.size();i++){
                Integer trainSum1=0;
                Integer writeSum1=0;
                Integer dataAuditSum1=0;
                if(id.equals(blist.get(i).getPid())){
                    for(int j=0;j<blist.size();j++){
                        if(blist.get(i).getId().equals(blist.get(j).getPid())){
                            trainSum1 = trainSum1 + blist.get(j).getTrainSum(); //在培人数
                            writeSum1 = writeSum1 + blist.get(j).getWriteSum(); //数据填写量
                            dataAuditSum1=dataAuditSum1+ blist.get(j).getDataAuditSum();//数据审核量
                        }
                    }
                }
                trainSum = trainSum+ trainSum1; //在陪人数
                writeSum = writeSum+ writeSum1; //数据填写量
                dataAuditSum=dataAuditSum+ dataAuditSum1;//数据审核量
            }
            jj.setTrainSum(trainSum);
            jj.setWriteSum(writeSum);
            jj.setDataAuditSum(dataAuditSum);
        }
        return jj;
    }





    /*@Scheduled(cron="0 10 1 1 * ?")//每月1号凌晨1点10分执行一次
    @Transactional(rollbackFor = Exception.class)*/
    public void local() throws ParseException {
        logger.info("----------------------------------Start My Schduled Doing-----------------------------");
        long startTime = System.currentTimeMillis(); //获取开始时间

        String monthDate = getLastMonths(-1); //获取上月  -9
        int myCount= schdualTaskMapper.deleteAllPreviousMonthData(monthDate); //先删除上月的数据，
        logger.info("----------------------------------"+myCount+"-----------------------------");
        String[] a = new String[2];
        a[0] = "";
        a[1] = "isContain";

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位

        /*List<AppUseInfoPojoParam> bList = new ArrayList<>();*/
        List<SysOrg> joinorgList = new ArrayList<>();

        SysOrg searchOrg=new SysOrg();
        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
        searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        searchOrg.setOrgProvId("320000");  //江苏省
        List<SysOrg> exitOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
        for(int s=0;s<exitOrgs.size();s++){


            //orgs.add(currOrg);//获取机构和协同机构
            if (OrgLevelEnum.CountryOrg.getId().equals(exitOrgs.get(s).getOrgLevelId())) {
                joinorgList = orgBiz.searchJointOrgsByOrg(exitOrgs.get(s).getOrgFlow());
            }
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("dictTypeId", DictTypeEnum.DoctorTrainingSpe.getId());
            List<SysDict> doctorTrainingSpeList = this.resDoctorRecruitExtMapper.searchTrainSpeList(paramMap);//所有专业
            String[] docTypeListDoc = {"Company", "CompanyEntrust", "Social"};
            String[] docTypeListMast = {"Graduate"};
            //
            for (int count = 0; count < a.length; count++) {

                if ("isContain".equals(a[count])) {
                    List<AppUseInfoPojoParam> bList = new ArrayList<>();
                    /**第一层专业*/
                    for (int i = 0; i < doctorTrainingSpeList.size(); i++) {
                        int speDoczaipei = 0;
                        int speDocuse = 0;
                        int speMastzaipei = 0;
                        int speMastuse = 0;
                        int speavgzaipei = 0;
                        int speavguse = 0;
                        /**年级*/
                        Map<String, Object> sessionNumbermap = new HashMap<>();
                        List<String> sessionNumberDistinct = new ArrayList<>();
                        sessionNumbermap.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                        String[] docTypeList = {"Company", "CompanyEntrust", "Social", "Graduate"};
                        sessionNumbermap.put("docTypeList", docTypeList);
                        sessionNumbermap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                        List<JsResDoctorRecruitExt> sessionNUmberList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionNumbermap);
                        for (JsResDoctorRecruitExt jrs : sessionNUmberList) {
                            if (!sessionNumberDistinct.contains(jrs.getSessionNumber())) {
                                sessionNumberDistinct.add(jrs.getSessionNumber());
                            }
                        }
                        for (int j = 0; j < sessionNumberDistinct.size(); j++) {
                            int sessionDoczaipei = 0;
                            int sessionDocuse = 0;
                            int sessionMastzaipei = 0;
                            int sessionMastuse = 0;
                            int sessionavgzaipei = 0;
                            int sessionavguse = 0;
                            if (joinorgList.size() > 0) {
                                for (int k = 0; k < joinorgList.size(); k++) {
                                    AppUseInfoPojoParam appUseInfoPojoParam3 = new AppUseInfoPojoParam();
                                    Map<String, Object> joinOrgMap = new HashMap<>();
                                    Map<String, Object> joinOrgMap2 = new HashMap<>();
                                    Map<String, Object> joinOrgMap3 = new HashMap<>();
                                    joinOrgMap.put("orgFlow", joinorgList.get(k).getOrgFlow());
                                    joinOrgMap2.put("orgFlow", joinorgList.get(k).getOrgFlow());
                                    joinOrgMap.put("docTypeList", docTypeListDoc);
                                    joinOrgMap2.put("docTypeList", docTypeListMast);
                                    joinOrgMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                                    joinOrgMap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
                                    joinOrgMap.put("sessionNumber", sessionNumberDistinct.get(j));
                                    joinOrgMap2.put("sessionNumber", sessionNumberDistinct.get(j));
                                    joinOrgMap3.put("orgFlow", joinorgList.get(k).getOrgFlow());
                                    joinOrgMap3.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
                                    List<JsResDoctorRecruitExt> joinorgZaipeiListDoc = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(joinOrgMap);
                                    List<JsResDoctorRecruitExt> joinorgZaipeiListMaster = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(joinOrgMap2);
                                    int joinorgavgzaipei = joinorgZaipeiListDoc.size() + joinorgZaipeiListMaster.size();
                                    List<String> joinorguserFlowList = monthlyReportExtMapper2.getAppUserSum(joinOrgMap3);//该基地下该年月使用app用户id
                                    List<JsResDoctorRecruitExt> joinorgappuserDOcList = getUseAppinfo(joinorgZaipeiListDoc, joinorguserFlowList);
                                    List<JsResDoctorRecruitExt> joinorgappuserMastList = getUseAppinfo(joinorgZaipeiListMaster, joinorguserFlowList);
                                    int joinorgavguse = joinorgappuserDOcList.size() + joinorgappuserMastList.size();

                                    String joinorgdoctorRate = "";
                                    String joinorgmasterRate = "";
                                    String joinorgavgRate = "";
                                    if (joinorgZaipeiListDoc.size() == 0) {
                                        joinorgdoctorRate = "0%";
                                    }
                                    if (null != joinorgZaipeiListDoc && !"".equals(joinorgZaipeiListDoc) && joinorgZaipeiListDoc.size() != 0) {
                                        joinorgdoctorRate = numberFormat.format((float) joinorgappuserDOcList.size() / (float) joinorgZaipeiListDoc.size() * 100) + "%";
                                    }
                                    if (joinorgZaipeiListMaster.size() == 0) {
                                        joinorgmasterRate = "0%";
                                    }
                                    if (null != joinorgZaipeiListMaster && !"".equals(joinorgZaipeiListMaster) && joinorgZaipeiListMaster.size() != 0) {
                                        joinorgmasterRate = numberFormat.format((float) joinorgappuserMastList.size() / (float) joinorgZaipeiListMaster.size() * 100) + "%";
                                    }
                                    if (joinorgavgzaipei == 0) {
                                        joinorgavgRate = "0%";
                                    }
                                    if (0 != joinorgavgzaipei) {
                                        joinorgavgRate = numberFormat.format((float) joinorgavguse / (float) joinorgavgzaipei * 100) + "%";
                                    }
                                    //
                                    appUseInfoPojoParam3.setId(i + "-" + j + "-" + k);
                                    appUseInfoPojoParam3.setPid(i + "-" + j);//
                                    appUseInfoPojoParam3.setLevel("3");
                                    appUseInfoPojoParam3.setSpeName(joinorgList.get(k).getOrgName());//存协同基地
                                    appUseInfoPojoParam3.setDoctorRateTrainSum(joinorgZaipeiListDoc.size() + "");
                                    appUseInfoPojoParam3.setMasterRateTrainSum(joinorgZaipeiListMaster.size() + "");
                                    appUseInfoPojoParam3.setAvgRateTrainSum(joinorgavgzaipei + "");
                                    appUseInfoPojoParam3.setDoctorRateUseSum(joinorgappuserDOcList.size() + "");
                                    appUseInfoPojoParam3.setMasterRateUseSum(joinorgappuserMastList.size() + "");
                                    appUseInfoPojoParam3.setAvgRateUseSum(joinorgavguse + "");

                                    appUseInfoPojoParam3.setDoctorRateRate(joinorgdoctorRate);
                                    appUseInfoPojoParam3.setMasterRateRate(joinorgmasterRate);
                                    appUseInfoPojoParam3.setAvgRateRate(joinorgavgRate);

                                    appUseInfoPojoParam3.setIsContain("Y");
                                    appUseInfoPojoParam3.setMonthDate(monthDate);
                                    appUseInfoPojoParam3.setOrgFlow(exitOrgs.get(s).getOrgFlow());
                                    appUseInfoPojoParam3.setCreateTime(DateUtil.getCurrDateTime());
                                    appUseInfoPojoParam3.setRecordFlow(UUID.randomUUID().toString());

                                    bList.add(appUseInfoPojoParam3);
                                    sessionDoczaipei = sessionDoczaipei + joinorgZaipeiListDoc.size();
                                    sessionMastzaipei = sessionMastzaipei + joinorgZaipeiListMaster.size();
                                    sessionavgzaipei = sessionavgzaipei + joinorgavgzaipei;
                                    sessionDocuse = sessionDocuse + joinorgappuserDOcList.size();
                                    sessionMastuse = sessionMastuse + joinorgappuserMastList.size();
                                    sessionavguse = sessionavguse + joinorgavguse;
                                }
                            }
                            AppUseInfoPojoParam appUseInfoPojoParam2 = new AppUseInfoPojoParam();
                            Map<String, Object> sessionMap = new HashMap<>();
                            Map<String, Object> sessionMap2 = new HashMap<>();
                            Map<String, Object> sessionMap3 = new HashMap<>();
                            sessionMap.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                            sessionMap2.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                            sessionMap.put("docTypeList", docTypeListDoc);
                            sessionMap2.put("docTypeList", docTypeListMast);
                            sessionMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                            sessionMap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
                            sessionMap.put("sessionNumber", sessionNumberDistinct.get(j));
                            sessionMap2.put("sessionNumber", sessionNumberDistinct.get(j));
                            sessionMap3.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                            sessionMap3.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
                            List<JsResDoctorRecruitExt> sessionNUmberZaipeiListDoc = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionMap);
                            List<JsResDoctorRecruitExt> sessionNUmberZaipeiListMaster = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionMap2);
                            int SessionNumberavgzaipei = sessionNUmberZaipeiListDoc.size() + sessionNUmberZaipeiListMaster.size();
                            List<String> SesionNumberuserFlowList = monthlyReportExtMapper2.getAppUserSum(sessionMap3);//该基地下该年月使用app用户id
                            List<JsResDoctorRecruitExt> SessionNumberappuserDOcList = getUseAppinfo(sessionNUmberZaipeiListDoc, SesionNumberuserFlowList);
                            List<JsResDoctorRecruitExt> SessionNumberappuserMastList = getUseAppinfo(sessionNUmberZaipeiListMaster, SesionNumberuserFlowList);
                            int SessionNumberavguse = SessionNumberappuserDOcList.size() + SessionNumberappuserMastList.size();

                            int sessionDoczaipeiIscontain = sessionNUmberZaipeiListDoc.size() + sessionDoczaipei;
                            int sessionDocuseIscontain = SessionNumberappuserDOcList.size() + sessionDocuse;
                            int sessionMastzaipeiIscontain = sessionNUmberZaipeiListMaster.size() + sessionMastzaipei;
                            int sessionMastuseIscontain = SessionNumberappuserMastList.size() + sessionMastuse;
                            int sessionavgzaipeiIscontain = SessionNumberavgzaipei + sessionavgzaipei;
                            int sessionavguseIscontain = SessionNumberavguse + sessionavguse;

                            String SessionNUmbrdoctorRate = "";
                            String SessionNUmbrmasterRate = "";
                            String SessionNUmbravgRate = "";
                            if (sessionDoczaipeiIscontain == 0) {
                                SessionNUmbrdoctorRate = "0%";
                            }
                            if (sessionDoczaipeiIscontain != 0) {
                                SessionNUmbrdoctorRate = numberFormat.format((float) sessionDocuseIscontain / (float) sessionDoczaipeiIscontain * 100) + "%";
                            }
                            if (sessionMastzaipeiIscontain == 0) {
                                SessionNUmbrmasterRate = "0%";
                            }
                            if (sessionMastzaipeiIscontain != 0) {
                                SessionNUmbrmasterRate = numberFormat.format((float) sessionMastuseIscontain / (float) sessionMastzaipeiIscontain * 100) + "%";
                            }
                            if (sessionavgzaipeiIscontain == 0) {
                                SessionNUmbravgRate = "0%";
                            }
                            if (0 != sessionavgzaipeiIscontain) {
                                SessionNUmbravgRate = numberFormat.format((float) sessionavguseIscontain / (float) sessionavgzaipeiIscontain * 100) + "%";
                            }
                            appUseInfoPojoParam2.setId(i + "-" + j);
                            appUseInfoPojoParam2.setPid(i + "");//
                            appUseInfoPojoParam2.setLevel("2");
                            appUseInfoPojoParam2.setSpeName(sessionNumberDistinct.get(j));//存sessionNumber
                            appUseInfoPojoParam2.setDoctorRateTrainSum(sessionDoczaipeiIscontain + "");
                            appUseInfoPojoParam2.setMasterRateTrainSum(sessionMastzaipeiIscontain + "");
                            appUseInfoPojoParam2.setAvgRateTrainSum(sessionavgzaipeiIscontain + "");
                            appUseInfoPojoParam2.setDoctorRateUseSum(sessionDocuseIscontain + "");
                            appUseInfoPojoParam2.setMasterRateUseSum(sessionMastuseIscontain + "");
                            appUseInfoPojoParam2.setAvgRateUseSum(sessionavguseIscontain + "");

                            appUseInfoPojoParam2.setDoctorRateRate(SessionNUmbrdoctorRate);
                            appUseInfoPojoParam2.setMasterRateRate(SessionNUmbrmasterRate);
                            appUseInfoPojoParam2.setAvgRateRate(SessionNUmbravgRate);

                            appUseInfoPojoParam2.setIsContain("Y");
                            appUseInfoPojoParam2.setMonthDate(monthDate);
                            appUseInfoPojoParam2.setOrgFlow(exitOrgs.get(s).getOrgFlow());
                            appUseInfoPojoParam2.setCreateTime(DateUtil.getCurrDateTime());
                            appUseInfoPojoParam2.setRecordFlow(UUID.randomUUID().toString());

                            bList.add(appUseInfoPojoParam2);
                            speDoczaipei = speDoczaipei + sessionDoczaipeiIscontain;
                            speDocuse = speDocuse + sessionDocuseIscontain;
                            speMastzaipei = speMastzaipei + sessionMastzaipeiIscontain;
                            speMastuse = speMastuse + sessionMastuseIscontain;
                            speavgzaipei = speavgzaipei + sessionavgzaipeiIscontain;
                            speavguse = speavguse + sessionavguseIscontain;

                        }
                        AppUseInfoPojoParam appUseInfoPojoParam = new AppUseInfoPojoParam();
                        Map<String, Object> spemap = new HashMap<>();
                        Map<String, Object> spemap2 = new HashMap<>();
                        Map<String, Object> spemap3 = new HashMap<>();
                        spemap.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                        spemap2.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                        spemap3.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                        spemap3.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
		/*String[] docTypeListDoc ={"Company", "CompanyEntrust", "Social"};
		String[] docTypeListMast ={"Graduate"};*/
                        spemap.put("docTypeList", docTypeListDoc);
                        spemap2.put("docTypeList", docTypeListMast);
                        spemap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                        spemap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
                        List<JsResDoctorRecruitExt> zaipeiDocList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap);
                        List<JsResDoctorRecruitExt> zaipeiMasterList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap2);
                        int avgzaipei = zaipeiDocList.size() + zaipeiMasterList.size();
                        List<String> userFlowList = monthlyReportExtMapper2.getAppUserSum(spemap3);//该基地下该年月使用app用户id
                        List<JsResDoctorRecruitExt> appuserDOcList = getUseAppinfo(zaipeiDocList, userFlowList);
                        List<JsResDoctorRecruitExt> appuserMastList = getUseAppinfo(zaipeiMasterList, userFlowList);
                        int avguse = appuserDOcList.size() + appuserMastList.size();


                        int speDoczaipeiIscontain =/*zaipeiDocList.size()+*/speDoczaipei;
                        int speDocuseIscontain =/*appuserDOcList.size()+*/speDocuse;
                        int speMastzaipeiIscontain =/*zaipeiMasterList.size()+*/speMastzaipei;
                        int speMastuseIscontain =/*appuserMastList.size()+*/speMastuse;
                        int speavgzaipeiIscontain =/*avgzaipei+*/speavgzaipei;
                        int speavguseIscontain =/*avguse+*/speavguse;

                        String doctorRate = "";
                        String masterRate = "";
                        String avgRate = "";
                        if (speDoczaipeiIscontain == 0) {
                            doctorRate = "0%";
                        }
                        if (speDoczaipeiIscontain != 0) {
                            doctorRate = numberFormat.format((float) speDocuseIscontain / (float) speDoczaipeiIscontain * 100) + "%";
                        }
                        if (speMastzaipeiIscontain == 0) {
                            masterRate = "0%";
                        }
                        if (speMastzaipeiIscontain != 0) {
                            masterRate = numberFormat.format((float) speMastuseIscontain / (float) speMastzaipeiIscontain * 100) + "%";
                        }
                        if (speavgzaipeiIscontain == 0) {
                            avgRate = "0%";
                        }
                        if (0 != speavgzaipeiIscontain) {
                            avgRate = numberFormat.format((float) speavguseIscontain / (float) speavgzaipeiIscontain * 100) + "%";
                        }

                        appUseInfoPojoParam.setId(i + "");
                        appUseInfoPojoParam.setPid("");
                        appUseInfoPojoParam.setLevel("1");
                        appUseInfoPojoParam.setSpeName(doctorTrainingSpeList.get(i).getDictName());
                        appUseInfoPojoParam.setDoctorRateTrainSum(speDoczaipeiIscontain + "");
                        appUseInfoPojoParam.setMasterRateTrainSum(speMastzaipeiIscontain + "");
                        appUseInfoPojoParam.setAvgRateTrainSum(speavgzaipeiIscontain + "");
                        appUseInfoPojoParam.setDoctorRateUseSum(speDocuseIscontain + "");
                        appUseInfoPojoParam.setMasterRateUseSum(speMastuseIscontain + "");
                        appUseInfoPojoParam.setAvgRateUseSum(speavguseIscontain + "");

                        appUseInfoPojoParam.setDoctorRateRate(doctorRate);
                        appUseInfoPojoParam.setMasterRateRate(masterRate);
                        appUseInfoPojoParam.setAvgRateRate(avgRate);

                        appUseInfoPojoParam.setIsContain("Y");
                        appUseInfoPojoParam.setMonthDate(monthDate);
                        appUseInfoPojoParam.setOrgFlow(exitOrgs.get(s).getOrgFlow());
                        appUseInfoPojoParam.setCreateTime(DateUtil.getCurrDateTime());
                        appUseInfoPojoParam.setRecordFlow(UUID.randomUUID().toString());

                        bList.add(appUseInfoPojoParam);
                    }
                    if(bList.size()>0){
                        int kk= schdualTaskMapper.insertLocalApp(bList);
                        logger.info("-------------------------------LocalApp Insert(isContain)---"+kk+"-----------------------------");
                    }

                } else {
                    List<AppUseInfoPojoParam> bList = new ArrayList<>();
                    /**第一层专业*/
                    for (int i = 0; i < doctorTrainingSpeList.size(); i++) {
                        AppUseInfoPojoParam appUseInfoPojoParam = new AppUseInfoPojoParam();
                        Map<String, Object> spemap = new HashMap<>();
                        Map<String, Object> spemap2 = new HashMap<>();
                        Map<String, Object> spemap3 = new HashMap<>();
                        spemap.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                        spemap2.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                        spemap3.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                        spemap3.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
			/*String[] docTypeListDoc ={"Company", "CompanyEntrust", "Social"};
			String[] docTypeListMast ={"Graduate"};*/
                        spemap.put("docTypeList", docTypeListDoc);
                        spemap2.put("docTypeList", docTypeListMast);
                        spemap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                        spemap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
                        List<JsResDoctorRecruitExt> zaipeiDocList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap);
                        List<JsResDoctorRecruitExt> zaipeiMasterList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap2);
                        int avgzaipei = zaipeiDocList.size() + zaipeiMasterList.size();
                        List<String> userFlowList = monthlyReportExtMapper2.getAppUserSum(spemap3);//该基地下该年月使用app用户id
                        List<JsResDoctorRecruitExt> appuserDOcList = getUseAppinfo(zaipeiDocList, userFlowList);
                        List<JsResDoctorRecruitExt> appuserMastList = getUseAppinfo(zaipeiMasterList, userFlowList);
                        int avguse = appuserDOcList.size() + appuserMastList.size();

                        String doctorRate = "";
                        String masterRate = "";
                        String avgRate = "";
                        if (zaipeiDocList.size() == 0) {
                            doctorRate = "0%";
                        }
                        if (null != zaipeiDocList && !"".equals(zaipeiDocList) && zaipeiDocList.size() != 0) {
                            doctorRate = numberFormat.format((float) appuserDOcList.size() / (float) zaipeiDocList.size() * 100) + "%";
                        }
                        if (zaipeiMasterList.size() == 0) {
                            masterRate = "0%";
                        }
                        if (null != zaipeiMasterList && !"".equals(zaipeiMasterList) && zaipeiMasterList.size() != 0) {
                            masterRate = numberFormat.format((float) appuserMastList.size() / (float) zaipeiMasterList.size() * 100) + "%";
                        }
                        if (avgzaipei == 0) {
                            avgRate = "0%";
                        }
                        if (0 != avgzaipei) {
                            avgRate = numberFormat.format((float) avguse / (float) avgzaipei * 100) + "%";
                        }

                        appUseInfoPojoParam.setId(i + "");
                        appUseInfoPojoParam.setPid("");
                        appUseInfoPojoParam.setLevel("1");
                        appUseInfoPojoParam.setSpeName(doctorTrainingSpeList.get(i).getDictName());
                        appUseInfoPojoParam.setDoctorRateTrainSum(zaipeiDocList.size() + "");
                        appUseInfoPojoParam.setMasterRateTrainSum(zaipeiMasterList.size() + "");
                        appUseInfoPojoParam.setAvgRateTrainSum(avgzaipei + "");
                        appUseInfoPojoParam.setDoctorRateUseSum(appuserDOcList.size() + "");
                        appUseInfoPojoParam.setMasterRateUseSum(appuserMastList.size() + "");
                        appUseInfoPojoParam.setAvgRateUseSum(avguse + "");

                        appUseInfoPojoParam.setDoctorRateRate(doctorRate);
                        appUseInfoPojoParam.setMasterRateRate(masterRate);
                        appUseInfoPojoParam.setAvgRateRate(avgRate);

                        appUseInfoPojoParam.setMonthDate(monthDate);
                        appUseInfoPojoParam.setIsContain("N");
                        appUseInfoPojoParam.setOrgFlow(exitOrgs.get(s).getOrgFlow());
                        appUseInfoPojoParam.setCreateTime(DateUtil.getCurrDateTime());
                        appUseInfoPojoParam.setRecordFlow(UUID.randomUUID().toString());

                        bList.add(appUseInfoPojoParam);
                        /**年级*/
                        Map<String, Object> sessionNumbermap = new HashMap<>();
                        List<String> sessionNumberDistinct = new ArrayList<>();
                        sessionNumbermap.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                        String[] docTypeList = {"Company", "CompanyEntrust", "Social", "Graduate"};
                        sessionNumbermap.put("docTypeList", docTypeList);
                        sessionNumbermap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                        List<JsResDoctorRecruitExt> sessionNUmberList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionNumbermap);
                        for (JsResDoctorRecruitExt jrs : sessionNUmberList) {
                            if (!sessionNumberDistinct.contains(jrs.getSessionNumber())) {
                                sessionNumberDistinct.add(jrs.getSessionNumber());
                            }
                        }
                        for (int j = 0; j < sessionNumberDistinct.size(); j++) {
                            AppUseInfoPojoParam appUseInfoPojoParam2 = new AppUseInfoPojoParam();
                            Map<String, Object> sessionMap = new HashMap<>();
                            Map<String, Object> sessionMap2 = new HashMap<>();
                            Map<String, Object> sessionMap3 = new HashMap<>();
                            sessionMap.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                            sessionMap2.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                            sessionMap.put("docTypeList", docTypeListDoc);
                            sessionMap2.put("docTypeList", docTypeListMast);
                            sessionMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                            sessionMap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
                            sessionMap.put("sessionNumber", sessionNumberDistinct.get(j));
                            sessionMap2.put("sessionNumber", sessionNumberDistinct.get(j));
                            sessionMap3.put("orgFlow", exitOrgs.get(s).getOrgFlow());
                            sessionMap3.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
                            List<JsResDoctorRecruitExt> sessionNUmberZaipeiListDoc = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionMap);
                            List<JsResDoctorRecruitExt> sessionNUmberZaipeiListMaster = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionMap2);
                            int SessionNumberavgzaipei = sessionNUmberZaipeiListDoc.size() + sessionNUmberZaipeiListMaster.size();
                            List<String> SesionNumberuserFlowList = monthlyReportExtMapper2.getAppUserSum(sessionMap3);//该基地下该年月使用app用户id
                            List<JsResDoctorRecruitExt> SessionNumberappuserDOcList = getUseAppinfo(sessionNUmberZaipeiListDoc, SesionNumberuserFlowList);
                            List<JsResDoctorRecruitExt> SessionNumberappuserMastList = getUseAppinfo(sessionNUmberZaipeiListMaster, SesionNumberuserFlowList);
                            int SessionNumberavguse = SessionNumberappuserDOcList.size() + SessionNumberappuserMastList.size();

                            String SessionNUmbrdoctorRate = "";
                            String SessionNUmbrmasterRate = "";
                            String SessionNUmbravgRate = "";
                            if (sessionNUmberZaipeiListDoc.size() == 0) {
                                SessionNUmbrdoctorRate = "0%";
                            }
                            if (null != sessionNUmberZaipeiListDoc && !"".equals(sessionNUmberZaipeiListDoc) && sessionNUmberZaipeiListDoc.size() != 0) {
                                SessionNUmbrdoctorRate = numberFormat.format((float) SessionNumberappuserDOcList.size() / (float) sessionNUmberZaipeiListDoc.size() * 100) + "%";
                            }
                            if (sessionNUmberZaipeiListMaster.size() == 0) {
                                SessionNUmbrmasterRate = "0%";
                            }
                            if (null != sessionNUmberZaipeiListMaster && !"".equals(sessionNUmberZaipeiListMaster) && sessionNUmberZaipeiListMaster.size() != 0) {
                                SessionNUmbrmasterRate = numberFormat.format((float) SessionNumberappuserMastList.size() / (float) sessionNUmberZaipeiListMaster.size() * 100) + "%";
                            }
                            if (SessionNumberavgzaipei == 0) {
                                SessionNUmbravgRate = "0%";
                            }
                            if (0 != SessionNumberavgzaipei) {
                                SessionNUmbravgRate = numberFormat.format((float) SessionNumberavguse / (float) SessionNumberavgzaipei * 100) + "%";
                            }
                            appUseInfoPojoParam2.setId(i + "-" + j);
                            appUseInfoPojoParam2.setPid(appUseInfoPojoParam.getId());//
                            appUseInfoPojoParam2.setLevel("2");
                            appUseInfoPojoParam2.setSpeName(sessionNumberDistinct.get(j));//存sessionNumber
                            appUseInfoPojoParam2.setDoctorRateTrainSum(sessionNUmberZaipeiListDoc.size() + "");
                            appUseInfoPojoParam2.setMasterRateTrainSum(sessionNUmberZaipeiListMaster.size() + "");
                            appUseInfoPojoParam2.setAvgRateTrainSum(SessionNumberavgzaipei + "");
                            appUseInfoPojoParam2.setDoctorRateUseSum(SessionNumberappuserDOcList.size() + "");
                            appUseInfoPojoParam2.setMasterRateUseSum(SessionNumberappuserMastList.size() + "");
                            appUseInfoPojoParam2.setAvgRateUseSum(SessionNumberavguse + "");

                            appUseInfoPojoParam2.setDoctorRateRate(SessionNUmbrdoctorRate);
                            appUseInfoPojoParam2.setMasterRateRate(SessionNUmbrmasterRate);
                            appUseInfoPojoParam2.setAvgRateRate(SessionNUmbravgRate);

                            appUseInfoPojoParam2.setMonthDate(monthDate);
                            appUseInfoPojoParam2.setIsContain("N");
                            appUseInfoPojoParam2.setOrgFlow(exitOrgs.get(s).getOrgFlow());
                            appUseInfoPojoParam2.setCreateTime(DateUtil.getCurrDateTime());
                            appUseInfoPojoParam2.setRecordFlow(UUID.randomUUID().toString());

                            bList.add(appUseInfoPojoParam2);
                            if (joinorgList.size() > 0) {
                                for (int k = 0; k < joinorgList.size(); k++) {
                                    AppUseInfoPojoParam appUseInfoPojoParam3 = new AppUseInfoPojoParam();
                                    Map<String, Object> joinOrgMap = new HashMap<>();
                                    Map<String, Object> joinOrgMap2 = new HashMap<>();
                                    Map<String, Object> joinOrgMap3 = new HashMap<>();
                                    joinOrgMap.put("orgFlow", joinorgList.get(k).getOrgFlow());
                                    joinOrgMap2.put("orgFlow", joinorgList.get(k).getOrgFlow());
                                    joinOrgMap.put("docTypeList", docTypeListDoc);
                                    joinOrgMap2.put("docTypeList", docTypeListMast);
                                    joinOrgMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
                                    joinOrgMap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
                                    joinOrgMap.put("sessionNumber", sessionNumberDistinct.get(j));
                                    joinOrgMap2.put("sessionNumber", sessionNumberDistinct.get(j));
                                    joinOrgMap3.put("orgFlow", joinorgList.get(k).getOrgFlow());
                                    joinOrgMap3.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
                                    List<JsResDoctorRecruitExt> joinorgZaipeiListDoc = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(joinOrgMap);
                                    List<JsResDoctorRecruitExt> joinorgZaipeiListMaster = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(joinOrgMap2);
                                    int joinorgavgzaipei = joinorgZaipeiListDoc.size() + joinorgZaipeiListMaster.size();
                                    List<String> joinorguserFlowList = monthlyReportExtMapper2.getAppUserSum(joinOrgMap3);//该基地下该年月使用app用户id
                                    List<JsResDoctorRecruitExt> joinorgappuserDOcList = getUseAppinfo(joinorgZaipeiListDoc, joinorguserFlowList);
                                    List<JsResDoctorRecruitExt> joinorgappuserMastList = getUseAppinfo(joinorgZaipeiListMaster, joinorguserFlowList);
                                    int joinorgavguse = joinorgappuserDOcList.size() + joinorgappuserMastList.size();

                                    String joinorgdoctorRate = "";
                                    String joinorgmasterRate = "";
                                    String joinorgavgRate = "";
                                    if (joinorgZaipeiListDoc.size() == 0) {
                                        joinorgdoctorRate = "0%";
                                    }
                                    if (null != joinorgZaipeiListDoc && !"".equals(joinorgZaipeiListDoc) && joinorgZaipeiListDoc.size() != 0) {
                                        joinorgdoctorRate = numberFormat.format((float) joinorgappuserDOcList.size() / (float) joinorgZaipeiListDoc.size() * 100) + "%";
                                    }
                                    if (joinorgZaipeiListMaster.size() == 0) {
                                        joinorgmasterRate = "0%";
                                    }
                                    if (null != joinorgZaipeiListMaster && !"".equals(joinorgZaipeiListMaster) && joinorgZaipeiListMaster.size() != 0) {
                                        joinorgmasterRate = numberFormat.format((float) joinorgappuserMastList.size() / (float) joinorgZaipeiListMaster.size() * 100) + "%";
                                    }
                                    if (joinorgavgzaipei == 0) {
                                        joinorgavgRate = "0%";
                                    }
                                    if (0 != joinorgavgzaipei) {
                                        joinorgavgRate = numberFormat.format((float) joinorgavguse / (float) joinorgavgzaipei * 100) + "%";
                                    }
                                    //
                                    appUseInfoPojoParam3.setId(i + "-" + j + "-" + k);
                                    appUseInfoPojoParam3.setPid(appUseInfoPojoParam2.getId());//
                                    appUseInfoPojoParam3.setLevel("3");
                                    appUseInfoPojoParam3.setSpeName(joinorgList.get(k).getOrgName());//存协同基地
                                    appUseInfoPojoParam3.setDoctorRateTrainSum(joinorgZaipeiListDoc.size() + "");
                                    appUseInfoPojoParam3.setMasterRateTrainSum(joinorgZaipeiListMaster.size() + "");
                                    appUseInfoPojoParam3.setAvgRateTrainSum(joinorgavgzaipei + "");
                                    appUseInfoPojoParam3.setDoctorRateUseSum(joinorgappuserDOcList.size() + "");
                                    appUseInfoPojoParam3.setMasterRateUseSum(joinorgappuserMastList.size() + "");
                                    appUseInfoPojoParam3.setAvgRateUseSum(joinorgavguse + "");

                                    appUseInfoPojoParam3.setDoctorRateRate(joinorgdoctorRate);
                                    appUseInfoPojoParam3.setMasterRateRate(joinorgmasterRate);
                                    appUseInfoPojoParam3.setAvgRateRate(joinorgavgRate);

                                    appUseInfoPojoParam3.setMonthDate(monthDate);
                                    appUseInfoPojoParam3.setIsContain("N");
                                    appUseInfoPojoParam3.setOrgFlow(exitOrgs.get(s).getOrgFlow());
                                    appUseInfoPojoParam3.setCreateTime(DateUtil.getCurrDateTime());
                                    appUseInfoPojoParam3.setRecordFlow(UUID.randomUUID().toString());


                                    bList.add(appUseInfoPojoParam3);
                                }
                            }

                        }
                    }
                    if(bList.size()>0){
                        int kk= schdualTaskMapper.insertLocalApp(bList);
                        logger.info("-------------------------------LocalApp(notContain) Insert---"+kk+"-----------------------------");
                    }

                }

            }
        }
    }

    /**
     *  @Date: 2022/08/12
     *  @Description:  月度统计报表
     */
//    @Scheduled(cron="0 */10 * * * ?")//测试10分钟执行一次
//    @Scheduled(cron="0 30 6 1 * ?")//每月1号凌晨6点30分执行一次 moved
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void monthStaticData() throws ParseException {
        logger.info("----------------------------------Start My Schduled Doing-----------------------------");
        String monthDate = DateUtil.addMonth(DateUtil.getMonth(),-1);
        String createTime = DateUtil.getCurrentTime();
        int num = schdualTaskMapper.insertMonthStaticData(monthDate,createTime);//插入出科考核次数，教学活动发布次数，考勤签到次数
        logger.info("------------------------------插入数据"+num+"条-----------------------------");
        String monthDate2 = monthDate.replace("-","");
        int num2 = schdualTaskMapper.insertMonthStaticData2(monthDate,monthDate2,createTime);//插入填写数据量
        logger.info("------------------------------插入数据"+num2+"条-----------------------------");
    }

    /**
     *  @Date: 2022/08/12
     *  @Description:  月度统计报表
     */
//    @Scheduled(cron="0 */10 * * * ?")//测试10分钟执行一次
//   @Scheduled(cron="0 30 3 * * ?") //每天3点30 moved
    @Transactional(rollbackFor = Exception.class)
    public void autoAddData(){
        logger.info("----------------------------------Start My Schduled Doing-----------------------------");
        jsResDoctorRecruitExtMapper.delData();//删除学员填写量
        int num2 = jsResDoctorRecruitExtMapper.autoAddData();//插入学员填写量
        logger.info("------------------------------插入数据"+num2+"条-----------------------------");
    }

    /**
     *  @Date: 2023/06/12
     *  @Description:  学员工作量  数据库已有该定时任务 增加后会导致冲突
     */
//    @Scheduled(cron="0 */5 * * * ?")//测试5分钟执行一次
//    @Scheduled(cron="0 0 5 * * ?") //每天五点
//    @Transactional(rollbackFor = Exception.class)
//    public void jsresDoctorWorkPro(){
//        logger.info("----------------------------------Start  JSRES_DOCTOR_WORK_PRO Doing-----------------------------");
//        long begin = System.currentTimeMillis();
//        jsResDoctorRecruitExtMapper.jsresDoctorWorkPro();//插入学员工作量
//        long end = System.currentTimeMillis();
//        System.out.println("执行完成，共耗时：[" + (end-begin) / 1000 + "]秒");
//        logger.info("------------------------------End  JSRES_DOCTOR_WORK_PRO Doing-----------------------------");
//    }

}

