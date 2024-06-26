package com.pinde.sci.ctrl.res;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.jsres.IJsResStatisticBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.res.IResSchProcessExpressBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.DateUtil;
import com.pinde.sci.dao.jsres.MonthlyReportExtMapper;
import com.pinde.sci.dao.jsres.SchdualTaskMapper;
import com.pinde.sci.dao.res.ResDoctorRecruitExtMapper;
import com.pinde.sci.enums.res.AfterRecTypeEnum;
import com.pinde.sci.enums.res.RecStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
 *  @Date: 2020/3/23 14:33
 *  @Description: 省市高校角色 定时任务
 */
@Service
@Component
public class ShengShiUniverstiJob implements TeachingActivityJob{
    private static Logger logger = LoggerFactory.getLogger(ShengShiUniverstiJob.class);
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private MonthlyReportExtMapper monthlyReportExtMapper2;
    @Autowired
    private ResDoctorRecruitExtMapper resDoctorRecruitExtMapper;
    @Autowired
    private SchdualTaskMapper schdualTaskMapper;
    @Autowired
    private IResJointOrgBiz resJointOrgBiz;
    @Autowired
    private IJsResPowerCfgBiz jsResPowerCfgBiz;
    @Autowired
    private ISchArrangeResultBiz schArrangeResultBiz;
    @Autowired
    private IResDoctorProcessBiz resDoctorProcessBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private IJsResStatisticBiz resStatisticBiz;


    /**
     *  @author: yuh
     *  @Date: 2020/3/23 14:33
     *  @Description:省市app情况使用情况统计定时任务
     */
//    @Scheduled(cron="0 0 21 30 * ?")//每月30号晚8点执行
    @Transactional(rollbackFor = Exception.class)
    @Override
    public  void shengshiApp(){
        String monthDate = InitConfig.getSysCfg("res_static_job_month");
        if(StringUtil.isBlank(monthDate)){
            monthDate = getLastMonths(-1); //获取上月  -9
        }
       int kkk= schdualTaskMapper.deletePreviousMonthPCUData(monthDate); //先删除上月的数据，
        logger.info("--------ShengshiGaoxiaoApp--delete-------"+kkk+"----------------------");
        /*SysUser user = GlobalContext.getCurrentUser();*/
       /* List<SysOrg> orgs = new ArrayList<>();*/
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位
        String[] docTypeListDoc ={"Company", "CompanyEntrust", "Social"};
        String[] docTypeListMast ={"Graduate"};
        String[] a = new String[2];
        a[0] = "";
        a[1] = "isContain";
        List<String> roleList = new ArrayList<>();
        roleList.add("global");
        roleList.add("charge");
         roleList.add("university");
  for(int m=0;m<roleList.size();m++) {

              SysOrg searchOrg = new SysOrg();
                         /* searchOrg.setOrgProvId(currOrg.getOrgProvId());
                          if (roleList.get(m).equals(GlobalConstant.USER_LIST_CHARGE)) {
                              searchOrg.setOrgCityId(currOrg.getOrgCityId());
                          }*/
              searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
              searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
              searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
              searchOrg.setOrgProvId("320000");  //江苏省
        //高校角色
         if (GlobalConstant.USER_LIST_UNIVERSITY.equals(roleList.get(m))) {
             gaoxiaoApp(monthDate,a,roleList.get(m)); //  2018-07
               /* List li = resMonthlyReportGlobalControllerClass.appUserInfo_University(monthDate, isContain, orgFlow, orgs, role);*/
               /* return li;*/
          } else {

                for (int count = 0; count < a.length; count++) {

                 //包含协同
                 if ("isContain".equals(a[count])) {
                     List<SysOrg> orgs = new ArrayList<>();

                     Map<String, Object> map = new HashMap<>();
                     Map<String, Object> map1 = new HashMap<>();
                     map.put("monthDate", monthDate);
                     List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
                     for (int i = 0; i < exitOrgs.size(); i++) {
                         List<String> allOrgFlow = new ArrayList<>();
                         List<SysOrg> resJointOrgList = orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
                         for (int j = 0; j < resJointOrgList.size(); j++) {
                             allOrgFlow.add(resJointOrgList.get(j).getOrgFlow());
                         }
                         allOrgFlow.add(exitOrgs.get(i).getOrgFlow());
                         exitOrgs.get(i).setParentOrgFlow("");
                         exitOrgs.get(i).setNo(/*(i+1)+""*/exitOrgs.get(i).getOrgCode());
                         map.put("allOrgFlow", allOrgFlow);
                         map.put("docTypeList", docTypeListDoc);
                         map1.put("allOrgFlow", allOrgFlow);
                         map1.put("docTypeList", docTypeListMast);
                         List<JsResDoctorRecruitExt> docList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(map);
                         List<JsResDoctorRecruitExt> mastlist = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(map1);
                         int allTrain = docList.size() + mastlist.size();//所有在培人数

                         Map<String, Object> paramMap3 = new HashMap<>();
                         paramMap3.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                         paramMap3.put("myDoctorTypeId", docTypeListDoc);
                         paramMap3.put("allOrgFlow", allOrgFlow);
                         Map<String, Object> paramMap4 = new HashMap<>();
                         paramMap4.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                         paramMap4.put("myDoctorTypeId", docTypeListMast);
                         paramMap4.put("allOrgFlow", allOrgFlow);
                         List<String> docAppuse = monthlyReportExtMapper2.getAppUserSum(paramMap3);//
                         List<String> masteAppuse = monthlyReportExtMapper2.getAppUserSum(paramMap4);
                         int allAppuse = docAppuse.size() + masteAppuse.size();  //所有app使用人数
                         //if(list.size()>0){
                         Integer trainDoctorTotal = 0;
                         Integer doctorSum = 0;
                         Integer masterSum = 0;
                         trainDoctorTotal = allTrain;
                         doctorSum = docList.size();
                         masterSum = mastlist.size();

                         exitOrgs.get(i).setTrainDoctorTotal(trainDoctorTotal);
                         exitOrgs.get(i).setDoctorSum(doctorSum);
                         exitOrgs.get(i).setMasterSum(masterSum);
                         String doctorRate = "";
                         String masterRate = "";

                         if (0 == doctorSum) {
                             doctorRate = "0%";
                         }
                         if (0 != doctorSum) {
                             doctorRate = numberFormat.format((float) docAppuse.size() / (float) doctorSum * 100) + "%";
                         }
                         if (0 == masterSum) {
                             masterRate = "0%";
                         }
                         if (0 != masterSum) {
                             masterRate = numberFormat.format((float) masteAppuse.size() / (float) masterSum * 100) + "%";
                         }
                         exitOrgs.get(i).setDoctorRate(doctorRate);
                         exitOrgs.get(i).setMasterRate(masterRate);
                         Integer trainPersonTotal = monthlyReportExtMapper2.getTrainTotal(map);//该基地在培总人数
                         String rate = "";
                         if (0 == trainPersonTotal) {
                             rate = "0%";
                         }
                         if (0 != trainPersonTotal) {
                             rate = numberFormat.format((float) allAppuse / (float) trainPersonTotal * 100) + "%";
                         }
                         exitOrgs.get(i).setRate(rate);

                         exitOrgs.get(i).setIsContain("Y");
                         exitOrgs.get(i).setMonthDate(monthDate);
                         exitOrgs.get(i).setRecordFlow(UUID.randomUUID().toString());
                         exitOrgs.get(i).setRoleFlag(roleList.get(m));
                         exitOrgs.get(i).setProvince(exitOrgs.get(i).getOrgProvId());
                         exitOrgs.get(i).setCity(exitOrgs.get(i).getOrgCityId());

                         List<SysOrg> resJointOrgListT = orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
                         if (resJointOrgListT != null && !resJointOrgListT.isEmpty()) {
                             Map<String, Object> secondMap = new HashMap<>();
                             secondMap.put("monthDate", monthDate);
                             for (int j = 0; j < resJointOrgListT.size(); j++) {
                                 resJointOrgListT.get(j).setParentOrgFlow(exitOrgs.get(i).getOrgFlow());
                                 resJointOrgListT.get(j).setNo(/*(i+1)+"-"+(j+1)*/exitOrgs.get(i).getOrgCode() + "-" + (j + 1));
                                 secondMap.put("orgFlow", resJointOrgListT.get(j).getOrgFlow());

                                 Map<String, Object> paramMapXieTong = new HashMap<>();
                                 Map<String, Object> paramMap2XieTong = new HashMap<>();
                                 paramMapXieTong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                 paramMapXieTong.put("docTypeList", docTypeListDoc);
                                 paramMap2XieTong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                 paramMap2XieTong.put("docTypeList", docTypeListMast);

                                 List<SysOrg> list2 = new ArrayList<>();
                                 SysOrg ms = new SysOrg();
                                 List<JsResDoctorRecruitExt> docListXietong = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMapXieTong);
                                 List<JsResDoctorRecruitExt> mastlistXietong = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2XieTong);
                                 Map<String, Object> paramMap3Xietong = new HashMap<>();
                                 paramMap3Xietong.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                                 paramMap3Xietong.put("myDoctorTypeId", docTypeListDoc);
                                 paramMap3Xietong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                 Map<String, Object> paramMap4Xietong = new HashMap<>();
                                 paramMap4Xietong.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                                 paramMap4Xietong.put("myDoctorTypeId", docTypeListMast);
                                 paramMap4Xietong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                 List<String> docAppuseXietong = monthlyReportExtMapper2.getAppUserSum(paramMap3Xietong);//
                                 List<String> masteAppuseXietong = monthlyReportExtMapper2.getAppUserSum(paramMap4Xietong);
                                 int allAppuseXietong = docAppuseXietong.size() + masteAppuseXietong.size();  //所有app使用人数
                                 ms.setTrainDoctorTotal(docListXietong.size() + mastlistXietong.size());
                                 ms.setDoctorSum(docListXietong.size());
                                 ms.setMasterSum(mastlistXietong.size());
                                 list2.add(ms);

                                 if (list2.size() > 0) {
                                     SysOrg sysOrgT2 = list2.get(0);
                                     resJointOrgListT.get(j).setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
                                     resJointOrgListT.get(j).setDoctorSum(sysOrgT2.getDoctorSum());
                                     resJointOrgListT.get(j).setMasterSum(sysOrgT2.getMasterSum());
                                     String doctorRate2 = "";
                                     String masterRate2 = "";
                                     if (0 == sysOrgT2.getDoctorSum()) {
                                         doctorRate2 = "0%";
                                     }
                                     if (0 != sysOrgT2.getDoctorSum()) {
                                         doctorRate2 = numberFormat.format((float) docAppuseXietong.size() / (float) sysOrgT2.getDoctorSum() * 100) + "%";
                                     }
                                     if (0 == sysOrgT2.getMasterSum()) {
                                         masterRate2 = "0%";
                                     }
                                     if (0 != sysOrgT2.getMasterSum()) {
                                         masterRate2 = numberFormat.format((float) masteAppuseXietong.size() / (float) sysOrgT2.getMasterSum() * 100) + "%";
                                     }
                                     resJointOrgListT.get(j).setDoctorRate(doctorRate2);
                                     resJointOrgListT.get(j).setMasterRate(masterRate2);
                                     Integer trainPersonTotal2 = monthlyReportExtMapper2.getTrainTotal(secondMap);//该基地在培总人数
                                     String rate2 = "";
                                     if (0 == trainPersonTotal2) {
                                         rate2 = "0%";
                                     }
                                     if (0 != trainPersonTotal2) {
                                         rate2 = numberFormat.format((float) allAppuseXietong / (float) trainPersonTotal2 * 100) + "%";
                                     }
                                     resJointOrgListT.get(j).setRate(rate2);

                                     resJointOrgListT.get(j).setIsContain("Y");
                                     resJointOrgListT.get(j).setMonthDate(monthDate);
                                     resJointOrgListT.get(j).setRecordFlow(UUID.randomUUID().toString());
                                     resJointOrgListT.get(j).setRoleFlag(roleList.get(m));
                                     resJointOrgListT.get(j).setProvince(exitOrgs.get(i).getOrgProvId());
                                     resJointOrgListT.get(j).setCity(exitOrgs.get(i).getOrgCityId());
                                 }

                                 orgs.add(resJointOrgListT.get(j));
                             }
                         }
                         orgs.add(exitOrgs.get(i));
                     }
                     if(orgs.size()>0){
                       int kk=  schdualTaskMapper.insertPCUApp(orgs);
                         logger.info("--------ShengshiApp-insert-(isContain)-------"+kk+"----------------------");
                     }
                     //不包含协同
                 } else {
                     List<SysOrg> orgs = new ArrayList<>();

                     Map<String, Object> paramMap = new HashMap<>();
                     Map<String, Object> paramMap2 = new HashMap<>();
                     paramMap.put("monthDate", monthDate);
                     List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
                     for (int i = 0; i < exitOrgs.size(); i++) {
                         exitOrgs.get(i).setParentOrgFlow("");
                         exitOrgs.get(i).setNo(/*(i+1)+""*/exitOrgs.get(i).getOrgCode());
                         String countryOrgFlow = exitOrgs.get(i).getOrgFlow();
                         paramMap.put("orgFlow", countryOrgFlow);
                         paramMap.put("docTypeList", docTypeListDoc);
                         paramMap2.put("orgFlow", countryOrgFlow);
                         paramMap2.put("docTypeList", docTypeListMast);
                         /*List<SysOrg> list =monthlyReportExtMapper2.getAppDatayuh(paramMap);*/
                         List<SysOrg> list = new ArrayList<>();
                         SysOrg sss = new SysOrg();
                         List<JsResDoctorRecruitExt> docList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap);
                         List<JsResDoctorRecruitExt> mastlist = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2);
                         Map<String, Object> paramMap3 = new HashMap<>();
                         paramMap3.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                         paramMap3.put("myDoctorTypeId", docTypeListDoc);
                         paramMap3.put("orgFlow", countryOrgFlow);
                         Map<String, Object> paramMap4 = new HashMap<>();
                         paramMap4.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                         paramMap4.put("myDoctorTypeId", docTypeListMast);
                         paramMap4.put("orgFlow", countryOrgFlow);
                         List<String> docAppuse = monthlyReportExtMapper2.getAppUserSum(paramMap3);//
                         List<String> masteAppuse = monthlyReportExtMapper2.getAppUserSum(paramMap4);
                         int allAppuse = docAppuse.size() + masteAppuse.size();  //所有app使用人数
                         sss.setTrainDoctorTotal(docList.size() + mastlist.size());
                         sss.setDoctorSum(docList.size());
                         sss.setMasterSum(mastlist.size());
                         list.add(sss);

                         if (list.size() > 0) {
                             SysOrg sysOrgT = list.get(0);
                             exitOrgs.get(i).setTrainDoctorTotal(sysOrgT.getTrainDoctorTotal());
                             exitOrgs.get(i).setDoctorSum(sysOrgT.getDoctorSum());
                             exitOrgs.get(i).setMasterSum(sysOrgT.getMasterSum());
                             String doctorRate = "";
                             String masterRate = "";
                             if (sysOrgT.getDoctorSum() == 0) {
                                 doctorRate = "0%";
                             }
                             if (0 != sysOrgT.getDoctorSum()) {
                                 doctorRate = numberFormat.format((float) docAppuse.size() / (float) sysOrgT.getDoctorSum() * 100) + "%";
                             }
                             if (sysOrgT.getMasterSum() == 0) {
                                 masterRate = "0%";
                             }
                             if (0 != sysOrgT.getMasterSum()) {
                                 masterRate = numberFormat.format((float) masteAppuse.size() / (float) sysOrgT.getMasterSum() * 100) + "%";
                             }
                             exitOrgs.get(i).setDoctorRate(doctorRate);
                             exitOrgs.get(i).setMasterRate(masterRate);
                             Integer trainPersonTotal = monthlyReportExtMapper2.getTrainTotal(paramMap);//该基地在培总人数
                             String rate = "";
                             if (trainPersonTotal == 0) {
                                 rate = "0%";
                             }
                             if (0 != trainPersonTotal) {
                                 rate = numberFormat.format((float) allAppuse / (float) trainPersonTotal * 100) + "%";
                             }
                             exitOrgs.get(i).setRate(rate);

                             exitOrgs.get(i).setIsContain("N");
                             exitOrgs.get(i).setMonthDate(monthDate);
                             exitOrgs.get(i).setRecordFlow(UUID.randomUUID().toString());
                             exitOrgs.get(i).setRoleFlag(roleList.get(m));
                             exitOrgs.get(i).setProvince(exitOrgs.get(i).getOrgProvId());
                             exitOrgs.get(i).setCity(exitOrgs.get(i).getOrgCityId());
                         }

                         List<SysOrg> resJointOrgList = orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
                         if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                             for (int j = 0; j < resJointOrgList.size(); j++) {
                                 resJointOrgList.get(j).setParentOrgFlow(exitOrgs.get(i).getOrgFlow());
                                 resJointOrgList.get(j).setNo(/*(i+1)+"-"+(j+1)*/exitOrgs.get(i).getOrgCode() + "-" + (j + 1));
                                 paramMap.put("orgFlow", resJointOrgList.get(j).getOrgFlow());

                                 Map<String, Object> paramMapXieTong = new HashMap<>();
                                 Map<String, Object> paramMap2XieTong = new HashMap<>();
                                 paramMapXieTong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                 paramMapXieTong.put("docTypeList", docTypeListDoc);
                                 paramMap2XieTong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                 paramMap2XieTong.put("docTypeList", docTypeListMast);

                                 List<SysOrg> list2 = new ArrayList<>();
                                 SysOrg ms = new SysOrg();
                                 List<JsResDoctorRecruitExt> docListXietong = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMapXieTong);
                                 List<JsResDoctorRecruitExt> mastlistXietong = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2XieTong);
                                 Map<String, Object> paramMap3Xietong = new HashMap<>();
                                 paramMap3Xietong.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                                 paramMap3Xietong.put("myDoctorTypeId", docTypeListDoc);
                                 paramMap3Xietong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                 Map<String, Object> paramMap4Xietong = new HashMap<>();
                                 paramMap4Xietong.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                                 paramMap4Xietong.put("myDoctorTypeId", docTypeListMast);
                                 paramMap4Xietong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                 List<String> docAppuseXietong = monthlyReportExtMapper2.getAppUserSum(paramMap3Xietong);//
                                 List<String> masteAppuseXietong = monthlyReportExtMapper2.getAppUserSum(paramMap4Xietong);
                                 int allAppuseXietong = docAppuseXietong.size() + masteAppuseXietong.size();  //所有app使用人数
                                 ms.setTrainDoctorTotal(docListXietong.size() + mastlistXietong.size());
                                 ms.setDoctorSum(docListXietong.size());
                                 ms.setMasterSum(mastlistXietong.size());
                                 list2.add(ms);
                                 /*List<SysOrg> list2 =monthlyReportExtMapper2.getAppDatayuh(paramMap);*/
                                 if (list2.size() > 0) {
                                     SysOrg sysOrgT2 = list2.get(0);
                                     resJointOrgList.get(j).setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
                                     resJointOrgList.get(j).setDoctorSum(sysOrgT2.getDoctorSum());
                                     resJointOrgList.get(j).setMasterSum(sysOrgT2.getMasterSum());
                                     String doctorRate2 = "";
                                     String masterRate2 = "";
                                     if (sysOrgT2.getDoctorSum() == 0) {
                                         doctorRate2 = "0%";
                                     }
                                     if (0 != sysOrgT2.getDoctorSum()) {
                                         doctorRate2 = numberFormat.format((float) docAppuseXietong.size() / (float) sysOrgT2.getDoctorSum() * 100) + "%";
                                     }
                                     if (sysOrgT2.getMasterSum() == 0) {
                                         masterRate2 = "0%";
                                     }
                                     if (0 != sysOrgT2.getMasterSum()) {
                                         masterRate2 = numberFormat.format((float) masteAppuseXietong.size() / (float) sysOrgT2.getMasterSum() * 100) + "%";
                                     }
                                     resJointOrgList.get(j).setDoctorRate(doctorRate2);
                                     resJointOrgList.get(j).setMasterRate(masterRate2);
                                     Integer trainPersonTotal2 = monthlyReportExtMapper2.getTrainTotal(paramMap);//该基地在培总人数
                                     String rate2 = "";
                                     if (trainPersonTotal2 == 0) {
                                         rate2 = "0%";
                                     }
                                     if (0 != trainPersonTotal2) {
                                         rate2 = numberFormat.format((float) allAppuseXietong / (float) trainPersonTotal2 * 100) + "%";
                                     }
                                     resJointOrgList.get(j).setRate(rate2);

                                     resJointOrgList.get(j).setIsContain("N");
                                     resJointOrgList.get(j).setMonthDate(monthDate);
                                     resJointOrgList.get(j).setRecordFlow(UUID.randomUUID().toString());
                                     resJointOrgList.get(j).setRoleFlag(roleList.get(m));
                                     resJointOrgList.get(j).setProvince(exitOrgs.get(i).getOrgProvId());
                                     resJointOrgList.get(j).setCity(exitOrgs.get(i).getOrgCityId());
                                 }

                                 orgs.add(resJointOrgList.get(j));
                             }
                         }
                         orgs.add(exitOrgs.get(i));
                     }
                     if(orgs.size()>0){
                         int kk= schdualTaskMapper.insertPCUApp(orgs);
                         logger.info("--------ShengshiApp-insert(notContain)--------"+kk+"----------------------");
                     }

                 }
             }
  }
            //return orgs;
  }

}
    public List<SysOrg> resetOrgAppUserInfo(List<SysOrg> list){
        List<SysOrg> poList=new ArrayList<>();
        List<SysOrg> first=new ArrayList<>();
        List<SysOrg> second=new ArrayList<>();
        for(SysOrg aa:list){
            SysOrg s1=new SysOrg();
            List<ResJointOrg> joinorgInfolist=  resJointOrgBiz.searchResJointByJointOrgFlow(aa.getOrgFlow());
            if(joinorgInfolist.size()==0){
                s1.setParentOrgFlow("");
                s1.setOrgFlow(aa.getOrgFlow());
                s1.setOrgName(aa.getOrgName());
                s1.setOrgCode(aa.getOrgCode());
                s1.setNo(aa.getOrgCode());
                s1.setDoctorFlowsInOrg(aa.getDoctorFlowsInOrg());
                poList.add(s1);
            }else{
                for(ResJointOrg bb:joinorgInfolist){
                    SysOrg s2=new SysOrg();
                    if(isexistAppuserInfo(bb.getOrgFlow(),list)){
                        s2.setOrgFlow(bb.getJointOrgFlow());
                        s2.setOrgName(bb.getJointOrgName());
                        s2.setParentOrgFlow(bb.getOrgFlow());
                        s2.setDoctorFlowsInOrg(aa.getDoctorFlowsInOrg());
                    }else{
                        s2.setOrgFlow(bb.getJointOrgFlow());
                        s2.setOrgName(bb.getJointOrgName());
                        s2.setParentOrgFlow("");
                        s2.setDoctorFlowsInOrg(aa.getDoctorFlowsInOrg());
                    }
                    poList.add(s2);
                }
            }
        }
        if(poList.size()>0){
            for(SysOrg li:poList){
                if("".equals(li.getParentOrgFlow())){
                    first.add(li);
                }
                if(!"".equals(li.getParentOrgFlow())){
                    second.add(li);
                }
            }
            for(int i=0 ;i< first.size();i++){
                //first.get(i).setOrgCode((i+1)+"");
                String orgflow =first.get(i).getOrgFlow();
                int count=0;
                for(int j=0;j<second.size();j++){
                    if(second.get(j).getParentOrgFlow().equals(orgflow)){
                        second.get(j).setOrgCode(first.get(i).getOrgCode()+"-"+(count+1));
                        second.get(j).setNo(first.get(i).getOrgCode()+"-"+(count+1));
                        count++;
                    }
                }
            }
            first.addAll(second);
        }
        return first;
    }
     /**
      *  @author: yuh
      *  @Date: 2020/3/20 15:47
      *  @Description: 高校角色App定时任务方法
      */
    public void  gaoxiaoApp(String monthDate,String [] a,String roleFlag) {
        /*  SysUser user = GlobalContext.getCurrentUser();*/
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位
        String[] docTypeListDoc = {"Company", "CompanyEntrust", "Social"};
        String[] docTypeListMast = {"Graduate"};

        List<SysOrg> appuserInfoUniversitylist = new ArrayList<>();
       /* List<SysOrg> appuserInfoUniversityFirstlist = new ArrayList<>();*/

        List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
for (SysDict dict : sendSchools) {

    Map<String, Object> map1 = new HashMap<>();
    List<SysOrg> orgUniversity = new ArrayList<>();
    List<SysOrg> distinctOrgUniversity = new ArrayList<>();
    List<SysOrg> appuserInfoUniversityFirstlist = new ArrayList<>();
    map1.put("universityName", dict.getDictName());
    List<JsResDoctorRecruitExt> resDoctorRecruitExtsList = monthlyReportExtMapper2.getUserInfoAndOrgNameByUniversity(map1);
    for (JsResDoctorRecruitExt jRR : resDoctorRecruitExtsList) {
        SysOrg s1 = new SysOrg();
        List<String> doctorlistS = new ArrayList<>();
        String orgFlow = jRR.getOrgFlow();
        String orgName = jRR.getOrgName();
        s1.setOrgFlow(orgFlow);
        s1.setOrgName(orgName);
        s1.setOrgCode(jRR.getOrgCode());
        for (JsResDoctorRecruitExt jRR2 : resDoctorRecruitExtsList) {
            if (orgFlow.equals(jRR2.getOrgFlow())) {
                doctorlistS.add(jRR2.getDoctorFlow());
            }
        }
        s1.setDoctorFlowsInOrg(doctorlistS);
        orgUniversity.add(s1);
    }
    Set<SysOrg> personSet = new TreeSet<>((o1, o2) -> o1.getOrgFlow().compareTo(o2.getOrgFlow()));
    personSet.addAll(orgUniversity);
    distinctOrgUniversity = new ArrayList<>(personSet);

    appuserInfoUniversitylist = resetOrgAppUserInfo(distinctOrgUniversity);

    for (SysOrg syso : appuserInfoUniversitylist) {
        if ("".equals(syso.getParentOrgFlow())) {
            appuserInfoUniversityFirstlist.add(syso);
        }
    }
    for (int count = 0; count < a.length; count++) {

    //包含协同
    if ("isContain".equals(a[count])) {
        List<SysOrg> orgs=new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map11 = new HashMap<>();
        map.put("monthDate", monthDate);
        for (int i = 0; i < appuserInfoUniversityFirstlist.size(); i++) {
            List<String> allOrgFlow = new ArrayList<>();
            List<String> allUniversityDoctorsFlow = new ArrayList<>();
            for (SysOrg second : appuserInfoUniversitylist) {
                if (second.getParentOrgFlow().equals(appuserInfoUniversityFirstlist.get(i).getOrgFlow())) {
                    allOrgFlow.add(second.getOrgFlow());
                    allUniversityDoctorsFlow.addAll(second.getDoctorFlowsInOrg());
                }
            }
            allOrgFlow.add(appuserInfoUniversityFirstlist.get(i).getOrgFlow());
            allUniversityDoctorsFlow.addAll(appuserInfoUniversityFirstlist.get(i).getDoctorFlowsInOrg());

            map.put("allOrgFlow", allOrgFlow);
            map.put("docTypeList", docTypeListDoc);
            map.put("universityDoctorFlows", allUniversityDoctorsFlow);
            map11.put("allOrgFlow", allOrgFlow);
            map11.put("docTypeList", docTypeListMast);
            map11.put("universityDoctorFlows", allUniversityDoctorsFlow);
            List<JsResDoctorRecruitExt> docList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(map);
            List<JsResDoctorRecruitExt> mastlist = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(map11);
            int allTrain = docList.size() + mastlist.size();//所有在培人数

            Map<String, Object> paramMap3 = new HashMap<>();
            paramMap3.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
            paramMap3.put("myDoctorTypeId", docTypeListDoc);
            paramMap3.put("allOrgFlow", allOrgFlow);
            paramMap3.put("universityDoctorFlows", allUniversityDoctorsFlow);
            Map<String, Object> paramMap4 = new HashMap<>();
            paramMap4.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
            paramMap4.put("myDoctorTypeId", docTypeListMast);
            paramMap4.put("allOrgFlow", allOrgFlow);
            paramMap4.put("universityDoctorFlows", allUniversityDoctorsFlow);
            List<String> docAppuse = monthlyReportExtMapper2.getAppUserSum(paramMap3);//
            List<String> masteAppuse = monthlyReportExtMapper2.getAppUserSum(paramMap4);
            int allAppuse = docAppuse.size() + masteAppuse.size();  //所有app使用人数

            Integer trainDoctorTotal = 0;
            Integer doctorSum = 0;
            Integer masterSum = 0;
            trainDoctorTotal = allTrain;
            doctorSum = docList.size();
            masterSum = mastlist.size();

            appuserInfoUniversityFirstlist.get(i).setTrainDoctorTotal(trainDoctorTotal);
            appuserInfoUniversityFirstlist.get(i).setDoctorSum(doctorSum);
            appuserInfoUniversityFirstlist.get(i).setMasterSum(masterSum);
            String doctorRate = "";
            String masterRate = "";
            if (0 == doctorSum) {
                doctorRate = "0%";
            }
            if (0 != doctorSum) {
                doctorRate = numberFormat.format((float) docAppuse.size() / (float) doctorSum * 100) + "%";
            }
            if (0 == masterSum) {
                masterRate = "0%";
            }
            if (0 != masterSum) {
                masterRate = numberFormat.format((float) masteAppuse.size() / (float) masterSum * 100) + "%";
            }

            appuserInfoUniversityFirstlist.get(i).setDoctorRate(doctorRate);
            appuserInfoUniversityFirstlist.get(i).setMasterRate(masterRate);
            Integer trainPersonTotal = monthlyReportExtMapper2.getTrainTotal(map);//该基地在培总人数
            String rate = "";
            if (0 == trainPersonTotal) {
                rate = "0%";
            }
            if (0 != trainPersonTotal) {
                rate = numberFormat.format((float) allAppuse / (float) trainPersonTotal * 100) + "%";
            }

            appuserInfoUniversityFirstlist.get(i).setRate(rate);

            appuserInfoUniversityFirstlist.get(i).setIsContain("Y");
            appuserInfoUniversityFirstlist.get(i).setMonthDate(monthDate);
            appuserInfoUniversityFirstlist.get(i).setRecordFlow(UUID.randomUUID().toString());
            appuserInfoUniversityFirstlist.get(i).setCreateTime(DateUtil.getCurrDateTime());
            appuserInfoUniversityFirstlist.get(i).setRoleFlag(roleFlag); //高校角色
            appuserInfoUniversityFirstlist.get(i).setUniversity(dict.getDictFlow()); //高校id
            //}
            for (SysOrg second : appuserInfoUniversitylist) {
                if (second.getParentOrgFlow().equals(appuserInfoUniversityFirstlist.get(i).getOrgFlow())) {
                    Map<String, Object> secondMap = new HashMap<>();
                    secondMap.put("monthDate", monthDate);

                    secondMap.put("orgFlow", second.getOrgFlow());
                    secondMap.put("universityDoctorFlows", second.getDoctorFlowsInOrg());

                    Map<String, Object> paramMapXieTong = new HashMap<>();
                    Map<String, Object> paramMap2XieTong = new HashMap<>();
                    paramMapXieTong.put("orgFlow", second.getOrgFlow());
                    paramMapXieTong.put("docTypeList", docTypeListDoc);
                    paramMapXieTong.put("universityDoctorFlows", allUniversityDoctorsFlow);
                    paramMap2XieTong.put("orgFlow", second.getOrgFlow());
                    paramMap2XieTong.put("docTypeList", docTypeListMast);
                    paramMap2XieTong.put("universityDoctorFlows", allUniversityDoctorsFlow);

                    List<SysOrg> list2 = new ArrayList<>();
                    SysOrg ms = new SysOrg();
                    List<JsResDoctorRecruitExt> docListXietong = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMapXieTong);
                    List<JsResDoctorRecruitExt> mastlistXietong = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2XieTong);
                    Map<String, Object> paramMap3Xietong = new HashMap<>();
                    paramMap3Xietong.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                    paramMap3Xietong.put("myDoctorTypeId", docTypeListDoc);
                    paramMap3Xietong.put("orgFlow", second.getOrgFlow());
                    paramMap3Xietong.put("universityDoctorFlows", allUniversityDoctorsFlow);
                    Map<String, Object> paramMap4Xietong = new HashMap<>();
                    paramMap4Xietong.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                    paramMap4Xietong.put("myDoctorTypeId", docTypeListMast);
                    paramMap4Xietong.put("orgFlow", second.getOrgFlow());
                    paramMap4Xietong.put("universityDoctorFlows", allUniversityDoctorsFlow);
                    List<String> docAppuseXietong = monthlyReportExtMapper2.getAppUserSum(paramMap3Xietong);//
                    List<String> masteAppuseXietong = monthlyReportExtMapper2.getAppUserSum(paramMap4Xietong);
                    int allAppuseXietong = docAppuseXietong.size() + masteAppuseXietong.size();  //所有app使用人数
                    ms.setTrainDoctorTotal(docListXietong.size() + mastlistXietong.size());
                    ms.setDoctorSum(docListXietong.size());
                    ms.setMasterSum(mastlistXietong.size());
                    list2.add(ms);

                    if (list2.size() > 0) {
                        SysOrg sysOrgT2 = list2.get(0);
                        second.setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
                        second.setDoctorSum(sysOrgT2.getDoctorSum());
                        second.setMasterSum(sysOrgT2.getMasterSum());
                        String doctorRate2 = "";
                        String masterRate2 = "";
                        if (0 == sysOrgT2.getDoctorSum()) {
                            doctorRate2 = "0%";
                        }
                        if (0 != sysOrgT2.getDoctorSum()) {
                            doctorRate2 = numberFormat.format((float) docAppuseXietong.size() / (float) sysOrgT2.getDoctorSum() * 100) + "%";
                        }
                        if (0 == sysOrgT2.getMasterSum()) {
                            masterRate2 = "0%";
                        }
                        if (0 != sysOrgT2.getMasterSum()) {
                            masterRate2 = numberFormat.format((float) masteAppuseXietong.size() / (float) sysOrgT2.getMasterSum() * 100) + "%";
                        }

                        second.setDoctorRate(doctorRate2);
                        second.setMasterRate(masterRate2);
                        Integer trainPersonTotal2 = monthlyReportExtMapper2.getTrainTotal(secondMap);//该基地在培总人数
                        String rate2 = "";
                        if (0 == trainPersonTotal2) {
                            rate2 = "0%";
                        }
                        if (0 != trainPersonTotal2) {
                            rate2 = numberFormat.format((float) allAppuseXietong / (float) trainPersonTotal2 * 100) + "%";
                        }

                        second.setRate(rate2);

                        second.setIsContain("Y");
                        second.setMonthDate(monthDate);
                        second.setRecordFlow(UUID.randomUUID().toString());
                        second.setCreateTime(DateUtil.getCurrDateTime());
                        second.setRoleFlag(roleFlag); //高校角色
                        second.setUniversity(dict.getDictFlow()); //高校id
                    }
                    orgs.add(second);
                }
            }
            orgs.add(appuserInfoUniversityFirstlist.get(i));
        }
        if(orgs.size()>0){
          int kk=  schdualTaskMapper.insertPCUApp(orgs);
            logger.info("--------gaoxiaoApp-insert(isContain)--------"+kk+"----------------------");
        }
        //不包含协同
    } else {
        List<SysOrg> orgs=new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap.put("monthDate", monthDate);
        for (int i = 0; i < appuserInfoUniversityFirstlist.size(); i++) {

            String countryOrgFlow = appuserInfoUniversityFirstlist.get(i).getOrgFlow();
            paramMap.put("orgFlow", countryOrgFlow);
            paramMap.put("universityDoctorFlows", appuserInfoUniversityFirstlist.get(i).getDoctorFlowsInOrg());
            paramMap.put("docTypeList", docTypeListDoc);
            paramMap2.put("orgFlow", countryOrgFlow);
            paramMap2.put("universityDoctorFlows", appuserInfoUniversityFirstlist.get(i).getDoctorFlowsInOrg());
            paramMap2.put("docTypeList", docTypeListMast);
            /*List<SysOrg> list =monthlyReportExtMapper2.getAppDatayuh(paramMap);*/
            List<SysOrg> list = new ArrayList<>();
            SysOrg sss = new SysOrg();
            List<JsResDoctorRecruitExt> docList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap);
            List<JsResDoctorRecruitExt> mastlist = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2);
            Map<String, Object> paramMap3 = new HashMap<>();
            paramMap3.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
            paramMap3.put("myDoctorTypeId", docTypeListDoc);
            paramMap3.put("orgFlow", countryOrgFlow);
            paramMap3.put("universityDoctorFlows", appuserInfoUniversityFirstlist.get(i).getDoctorFlowsInOrg());
            Map<String, Object> paramMap4 = new HashMap<>();
            paramMap4.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
            paramMap4.put("myDoctorTypeId", docTypeListMast);
            paramMap4.put("orgFlow", countryOrgFlow);
            paramMap4.put("universityDoctorFlows", appuserInfoUniversityFirstlist.get(i).getDoctorFlowsInOrg());
            List<String> docAppuse = monthlyReportExtMapper2.getAppUserSum(paramMap3);//
            List<String> masteAppuse = monthlyReportExtMapper2.getAppUserSum(paramMap4);
            int allAppuse = docAppuse.size() + masteAppuse.size();  //所有app使用人数
            sss.setTrainDoctorTotal(docList.size() + mastlist.size());
            sss.setDoctorSum(docList.size());
            sss.setMasterSum(mastlist.size());
            list.add(sss);
            if (list.size() > 0) {
                SysOrg sysOrgT = list.get(0);
                appuserInfoUniversityFirstlist.get(i).setTrainDoctorTotal(sysOrgT.getTrainDoctorTotal());
                appuserInfoUniversityFirstlist.get(i).setDoctorSum(sysOrgT.getDoctorSum());
                appuserInfoUniversityFirstlist.get(i).setMasterSum(sysOrgT.getMasterSum());
                String doctorRate = "";
                String masterRate = "";
                if (sysOrgT.getDoctorSum() == 0) {
                    doctorRate = "0%";
                }
                if (0 != sysOrgT.getDoctorSum()) {
                    doctorRate = numberFormat.format((float) docAppuse.size() / (float) sysOrgT.getDoctorSum() * 100) + "%";
                }
                if (sysOrgT.getMasterSum() == 0) {
                    masterRate = "0%";
                }
                if (0 != sysOrgT.getMasterSum()) {
                    masterRate = numberFormat.format((float) masteAppuse.size() / (float) sysOrgT.getMasterSum() * 100) + "%";
                }

                appuserInfoUniversityFirstlist.get(i).setDoctorRate(doctorRate);
                appuserInfoUniversityFirstlist.get(i).setMasterRate(masterRate);
                Integer trainPersonTotal = monthlyReportExtMapper2.getTrainTotal(paramMap);//该基地在培总人数
                String rate = "";
                if (trainPersonTotal == 0) {
                    rate = "0%";
                }
                if (0 != trainPersonTotal) {
                    rate = numberFormat.format((float) allAppuse / (float) trainPersonTotal * 100) + "%";
                }

                appuserInfoUniversityFirstlist.get(i).setRate(rate);

                appuserInfoUniversityFirstlist.get(i).setIsContain("N");
                appuserInfoUniversityFirstlist.get(i).setMonthDate(monthDate);
                appuserInfoUniversityFirstlist.get(i).setRecordFlow(UUID.randomUUID().toString());
                appuserInfoUniversityFirstlist.get(i).setCreateTime(DateUtil.getCurrDateTime());
                appuserInfoUniversityFirstlist.get(i).setRoleFlag(roleFlag); //高校角色
                appuserInfoUniversityFirstlist.get(i).setUniversity(dict.getDictFlow()); //高校id
            }

            for (SysOrg second : appuserInfoUniversitylist) {
                if (second.getParentOrgFlow().equals(appuserInfoUniversityFirstlist.get(i).getOrgFlow())) {

                    paramMap.put("orgFlow", second.getOrgFlow());
                    paramMap.put("universityDoctorFlows", second.getDoctorFlowsInOrg());

                    Map<String, Object> paramMapXieTong = new HashMap<>();
                    Map<String, Object> paramMap2XieTong = new HashMap<>();
                    paramMapXieTong.put("orgFlow", second.getOrgFlow());
                    paramMapXieTong.put("docTypeList", docTypeListDoc);
                    paramMapXieTong.put("universityDoctorFlows", second.getDoctorFlowsInOrg());
                    paramMap2XieTong.put("orgFlow", second.getOrgFlow());
                    paramMap2XieTong.put("docTypeList", docTypeListMast);
                    paramMap2XieTong.put("universityDoctorFlows", second.getDoctorFlowsInOrg());

                    List<SysOrg> list2 = new ArrayList<>();
                    SysOrg ms = new SysOrg();
                    List<JsResDoctorRecruitExt> docListXietong = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMapXieTong);
                    List<JsResDoctorRecruitExt> mastlistXietong = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2XieTong);
                    Map<String, Object> paramMap3Xietong = new HashMap<>();
                    paramMap3Xietong.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                    paramMap3Xietong.put("myDoctorTypeId", docTypeListDoc);
                    paramMap3Xietong.put("orgFlow", second.getOrgFlow());
                    paramMap3Xietong.put("universityDoctorFlows", second.getDoctorFlowsInOrg());
                    Map<String, Object> paramMap4Xietong = new HashMap<>();
                    paramMap4Xietong.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                    paramMap4Xietong.put("myDoctorTypeId", docTypeListMast);
                    paramMap4Xietong.put("orgFlow", second.getOrgFlow());
                    paramMap4Xietong.put("universityDoctorFlows", second.getDoctorFlowsInOrg());
                    List<String> docAppuseXietong = monthlyReportExtMapper2.getAppUserSum(paramMap3Xietong);//
                    List<String> masteAppuseXietong = monthlyReportExtMapper2.getAppUserSum(paramMap4Xietong);
                    int allAppuseXietong = docAppuseXietong.size() + masteAppuseXietong.size();  //所有app使用人数
                    ms.setTrainDoctorTotal(docListXietong.size() + mastlistXietong.size());
                    ms.setDoctorSum(docListXietong.size());
                    ms.setMasterSum(mastlistXietong.size());
                    list2.add(ms);

                    /*List<SysOrg> list2 =monthlyReportExtMapper2.getAppDatayuh(paramMap);*/
                    if (list2.size() > 0) {
                        SysOrg sysOrgT2 = list2.get(0);
                        second.setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
                        second.setDoctorSum(sysOrgT2.getDoctorSum());
                        second.setMasterSum(sysOrgT2.getMasterSum());
                        String doctorRate2 = "";
                        String masterRate2 = "";
                        if (sysOrgT2.getDoctorSum() == 0) {
                            doctorRate2 = "0%";
                        }
                        if (0 != sysOrgT2.getDoctorSum()) {
                            doctorRate2 = numberFormat.format((float) docAppuseXietong.size() / (float) sysOrgT2.getDoctorSum() * 100) + "%";
                        }
                        if (sysOrgT2.getMasterSum() == 0) {
                            masterRate2 = "0%";
                        }
                        if (0 != sysOrgT2.getMasterSum()) {
                            masterRate2 = numberFormat.format((float) masteAppuseXietong.size() / (float) sysOrgT2.getMasterSum() * 100) + "%";
                        }

                        second.setDoctorRate(doctorRate2);
                        second.setMasterRate(masterRate2);
                        Integer trainPersonTotal2 = monthlyReportExtMapper2.getTrainTotal(paramMap);//该基地在培总人数
                        String rate2 = "";
                        if (trainPersonTotal2 == 0) {
                            rate2 = "0%";
                        }
                        if (0 != trainPersonTotal2) {
                            rate2 = numberFormat.format((float) allAppuseXietong / (float) trainPersonTotal2 * 100) + "%";
                        }

                        second.setRate(rate2);

                        second.setIsContain("N");
                        second.setMonthDate(monthDate);
                        second.setRecordFlow(UUID.randomUUID().toString());
                        second.setCreateTime(DateUtil.getCurrDateTime());
                        second.setRoleFlag(roleFlag); //高校角色
                        second.setUniversity(dict.getDictFlow()); //高校id
                    }
                    orgs.add(second);
                }
            }
            orgs.add(appuserInfoUniversityFirstlist.get(i));
        }
        if(orgs.size()>0){
          int kk=  schdualTaskMapper.insertPCUApp(orgs);
            logger.info("--------gaoxiaoApp-insert(notContain)--------"+kk+"----------------------");
        }

    }
}
    }
        /*  return orgs;*/
}
    public boolean isexistAppuserInfo(String orgflow,List<SysOrg> list){
        for(SysOrg doc:list){
            if(orgflow.equals(doc.getOrgFlow())){
                return true;
            }
        }
        return  false;
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


    /**
     *  @author: yuh
     *  @Date: 2020/3/23 14:50
     *  @Description: 省市教学活动定时任务
     */
//    @Scheduled(cron="0 0 18 30 * ?")//每月1号凌晨5点10分执行一次
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void shengshiTeachActive() throws ParseException {
        logger.info("---------Start ShengshiGaoxiaoactive  doing-------");

        String monthDate = InitConfig.getSysCfg("res_static_job_month");
        if(StringUtil.isBlank(monthDate)){
            monthDate = getLastMonths(-1); //获取上月  -9
        }

        schdualTaskMapper.deletepreviousMonthDataPCUTeachActive(monthDate); //先删除上月的数据，
    /*    SysUser user = GlobalContext.getCurrentUser();*/
        String[] isContainArray = new String[2];
        isContainArray[0] = "";
        isContainArray[1] = "isContain";
        List<String> roleList = new ArrayList<>();
        roleList.add("global");
        roleList.add("charge");
        roleList.add("university");
for(int m=0;m<roleList.size();m++) {

           /* List<TeachActiveParamPO> orgInfoList = new ArrayList<>();
            List<SysOrg> orgsList = new ArrayList<>();*/
            SysOrg searchOrg = new SysOrg();

            searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
            searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            searchOrg.setOrgProvId("320000");  //江苏省
            //高校角色
            if (GlobalConstant.USER_LIST_UNIVERSITY.equals(roleList.get(m))) {
                      gaoxiaoTeachActive(monthDate,isContainArray,roleList.get(m)); //"2018-12" monthDate
            }else {
                for(int count=0;count< isContainArray.length;count++){
                //包含协同
                if ("isContain".equals(isContainArray[count])) {
                    List<TeachActiveParamPO> orgInfoList = new ArrayList<>();
                    List<SysOrg> orgsList = new ArrayList<>();
                    List<SysOrg> orgsPrivoncCity = new ArrayList<>();

                    List<SysOrg> countryOrgs = orgBiz.searchOrg(searchOrg);//获取所有江苏省级或者市级国家基地
                    for (int i = 0; i < countryOrgs.size(); i++) {
                        countryOrgs.get(i).setParentOrgFlow("");
                        countryOrgs.get(i).setNo(/*(i+1)+""*/countryOrgs.get(i).getOrgCode());
                        List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(countryOrgs.get(i).getOrgFlow());
                        if (jointOrgList != null && !jointOrgList.isEmpty()) {
                            for (int j = 0; j < jointOrgList.size(); j++) {
                                jointOrgList.get(j).setParentOrgFlow(countryOrgs.get(i).getOrgFlow());
                                jointOrgList.get(j).setNo(/*(i+1)*/countryOrgs.get(i).getOrgCode() + "-" + (j + 1));
                                orgsList.add(jointOrgList.get(j));
                            }
                        }
                        orgsList.add(countryOrgs.get(i));
                        orgsPrivoncCity.add(countryOrgs.get(i));
                    }
                    //对象转化
                    for (int i = 0; i < orgsList.size(); i++) {
                        TeachActiveParamPO t = new TeachActiveParamPO();
                        BeanUtils.copyProperties(orgsList.get(i), t);
                        orgInfoList.add(t);
                    }
                    for (TeachActiveParamPO teachActiveParamPO : orgInfoList) {
                        String orgFlow = teachActiveParamPO.getOrgFlow();
                        Map<String, Object> mymap = new HashMap<>();
                        mymap.put("orgFlow", orgFlow);
                        Integer trainerSumCount = monthlyReportExtMapper2.getTrainerSum(mymap);
                        teachActiveParamPO.setTrainerSum(trainerSumCount); //在培总数

                        List<Map<String, Object>> activeInfoList = monthlyReportExtMapper2.findActivityListyuh(mymap);
                        Integer changci = 0;//场次
                        Integer alljoin = 0;//总参加人次
                        Double shichangTotal = 0.00;//总时长
                        for (Map<String, Object> li : activeInfoList) {
                            String activityFlow = (String) li.get("activityFlow");
                            String scanNum = ((BigDecimal) li.get("scanNum")).toString(); //签字个数
                            if (!"0".equals(scanNum)) {
                                List<TeachingActivityResult> teachingActivityResultList = monthlyReportExtMapper2.getScanTime1AndScanTime2(activityFlow);
                                String yearMonthshichang = maxminTime(teachingActivityResultList);
                                if ("".equals(yearMonthshichang)) {
//                                    throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
                                }else{
                                    String yearmonth = yearMonthshichang.split(",")[0];
                                    String shichang = yearMonthshichang.split(",")[1];
                                    if (monthDate.equals(yearmonth)) {
                                        changci++;
                                        alljoin = alljoin + teachingActivityResultList.size();
                                        shichangTotal = shichangTotal + Double.parseDouble(shichang);
                                    }
                                }
                            }
                        }
                        teachActiveParamPO.setTeachActiveSessionSum(changci);// 教学活动举办场次
                        teachActiveParamPO.setAllJoinSum(alljoin); //总参加人次
                        BigDecimal a = new BigDecimal(alljoin);
                        BigDecimal b = new BigDecimal(changci);
                        Double cdValue = 0.0;
                        if (!changci.equals(0)) {
                            BigDecimal c = a.divide(b, 2, BigDecimal.ROUND_HALF_UP);
                            cdValue = c.doubleValue();
                        }
                        teachActiveParamPO.setAverJoinSum(cdValue);//场均参加人次

                        BigDecimal sct = new BigDecimal(shichangTotal);//总时长
                        Double averSctValue = 0.0;
                        if (!changci.equals(0)) {
                            BigDecimal averSct = sct.divide(b, 2, BigDecimal.ROUND_HALF_UP);
                            averSctValue = averSct.doubleValue();
                        }
                        teachActiveParamPO.setAverDureTime(averSctValue);//场均时长（分钟）
                        teachActiveParamPO.setDureTime(shichangTotal);

                        teachActiveParamPO.setIsContain("Y");
                        teachActiveParamPO.setMonthDate(monthDate);
                        teachActiveParamPO.setRecordFlow(UUID.randomUUID().toString());
                        teachActiveParamPO.setCreateTime(DateUtil.getCurrDateTime());
                        teachActiveParamPO.setRoleFlag(roleList.get(m));
                        if("".equals(teachActiveParamPO.getParentOrgFlow()) || null==teachActiveParamPO.getParentOrgFlow()){
                            teachActiveParamPO.setProvince(teachActiveParamPO.getOrgProvId());
                            teachActiveParamPO.setCity(teachActiveParamPO.getOrgCityId());
                        }else{
                            String privoinc ="";String cityId="";
                            for(SysOrg so:orgsPrivoncCity){
                                if(so.getOrgFlow().equals(teachActiveParamPO.getParentOrgFlow())){
                                    privoinc = so.getOrgProvId();cityId =so.getOrgCityId();
                                    break;
                                }
                            }
                            teachActiveParamPO.setProvince(privoinc);
                            teachActiveParamPO.setCity(cityId);
                        }

                    }
                    //给国家基地赋值包含协同的信息
                    for (TeachActiveParamPO tpo : orgInfoList) {
                        if ("".equals(tpo.getParentOrgFlow())) {
                            Integer zaipeiSum = tpo.getTrainerSum(); //在陪人数
                            Integer jubanchangci = tpo.getTeachActiveSessionSum();
                            Integer zongcanjiarenci = tpo.getAllJoinSum();
                            Double zongshichang = tpo.getDureTime();
                            for (TeachActiveParamPO newTpo2 : orgInfoList) {
                                if (tpo.getOrgFlow().equals(newTpo2.getParentOrgFlow())) {
                                    zaipeiSum = zaipeiSum + newTpo2.getTrainerSum();
                                    jubanchangci = jubanchangci + newTpo2.getTeachActiveSessionSum();
                                    zongcanjiarenci = zongcanjiarenci + newTpo2.getAllJoinSum();
                                    zongshichang = zongshichang + newTpo2.getDureTime();
                                }
                            }
                            tpo.setTrainerSum(zaipeiSum);
                            tpo.setTeachActiveSessionSum(jubanchangci);
                            tpo.setAllJoinSum(zongcanjiarenci);

                            BigDecimal a1 = new BigDecimal(zongcanjiarenci);
                            BigDecimal b1 = new BigDecimal(jubanchangci);
                            Double cdValue = 0.0;
                            if (!jubanchangci.equals(0)) {
                                BigDecimal c = a1.divide(b1, 2, BigDecimal.ROUND_HALF_UP);
                                cdValue = c.doubleValue();
                            }
                            tpo.setAverJoinSum(cdValue);//场均参加人次

                            BigDecimal sct = new BigDecimal(zongshichang);//总时长
                            Double averSctValue = 0.0;
                            if (!jubanchangci.equals(0)) {
                                BigDecimal averSct = sct.divide(b1, 2, BigDecimal.ROUND_HALF_UP);
                                averSctValue = averSct.doubleValue();
                            }
                            tpo.setAverDureTime(averSctValue);//场均时长

                            /*tpo.setIsContain("Y");
                            tpo.setMonthDate(monthDate);
                            tpo.setRecordFlow(UUID.randomUUID().toString());
                            tpo.setCreateTime(DateUtil.getCurrDateTime());
                            tpo.setRoleFlag(roleList.get(m));
                            tpo.setProvince(tpo.getOrgProvId());
                            tpo.setCity(tpo.getOrgCityId());*/
                        }
                    }
                    List<TeachActiveParamPO> newList1 = activitySessionDevideMax(orgInfoList);//求进度条值
                    orgInfoList = newList1;
                    if(orgInfoList.size()>0){
                        schdualTaskMapper.insertPCUTeachActive(orgInfoList);
                    }
         //不包含协同
        } else {
                    List<TeachActiveParamPO> orgInfoList = new ArrayList<>();
                    List<SysOrg> orgsList = new ArrayList<>();
                    List<SysOrg> orgsPrivoncCity = new ArrayList<>();

                    Map<String, Object> map = new HashMap<>();
                    map.put("monthDate", monthDate);
                    List<SysOrg> countryOrgs = orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
                    for (int i = 0; i < countryOrgs.size(); i++) {
                        countryOrgs.get(i).setParentOrgFlow("");
                        countryOrgs.get(i).setNo(/*(i+1)+""*/countryOrgs.get(i).getOrgCode());
                        List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(countryOrgs.get(i).getOrgFlow());
                        if (jointOrgList != null && !jointOrgList.isEmpty()) {
                            for (int j = 0; j < jointOrgList.size(); j++) {
                                jointOrgList.get(j).setParentOrgFlow(countryOrgs.get(i).getOrgFlow());
                                jointOrgList.get(j).setNo(/*(i+1)*/countryOrgs.get(i).getOrgCode() + "-" + (j + 1));

                                orgsList.add(jointOrgList.get(j));
                            }
                        }
                        orgsList.add(countryOrgs.get(i));
                        orgsPrivoncCity.add(countryOrgs.get(i));
                    }
                    //对象转化
                    for (int i = 0; i < orgsList.size(); i++) {
                        TeachActiveParamPO t = new TeachActiveParamPO();
                        BeanUtils.copyProperties(orgsList.get(i), t);
                        orgInfoList.add(t);
                    }
                    //
                    for (TeachActiveParamPO teachActiveParamPO : orgInfoList) {
                        String orgFlow = teachActiveParamPO.getOrgFlow();
                        Map<String, Object> mymap = new HashMap<>();
                        mymap.put("orgFlow", orgFlow);
                        Integer trainerSumCount = monthlyReportExtMapper2.getTrainerSum(mymap);
                        teachActiveParamPO.setTrainerSum(trainerSumCount); //在培总数

                        List<Map<String, Object>> activeInfoList = monthlyReportExtMapper2.findActivityListyuh(mymap);
                        Integer changci = 0;//场次
                        Integer alljoin = 0;//总参加人次
                        Double shichangTotal = 0.00;//总时长
                        for (Map<String, Object> li : activeInfoList) {
                            String activityFlow = (String) li.get("activityFlow");
                            String scanNum = ((BigDecimal) li.get("scanNum")).toString(); //签字个数
                            if (!"0".equals(scanNum)) {
                                List<TeachingActivityResult> teachingActivityResultList = monthlyReportExtMapper2.getScanTime1AndScanTime2(activityFlow);
                                String yearMonthshichang = maxminTime(teachingActivityResultList);
                                if ("".equals(yearMonthshichang)) {
//                                    throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
                                }else{
                                    String yearmonth = yearMonthshichang.split(",")[0];
                                    String shichang = yearMonthshichang.split(",")[1];
                                    if (monthDate.equals(yearmonth)) {
                                        changci++;
                                        alljoin = alljoin + teachingActivityResultList.size();
                                        shichangTotal = shichangTotal + Double.parseDouble(shichang);
                                    }
                                }
                            }
                        }
                        teachActiveParamPO.setTeachActiveSessionSum(changci);// 教学活动举办场次
                        teachActiveParamPO.setAllJoinSum(alljoin); //总参加人次
                        BigDecimal a = new BigDecimal(alljoin);
                        BigDecimal b = new BigDecimal(changci);
                        Double cdValue = 0.0;
                        if (!changci.equals(0)) {
                            BigDecimal c = a.divide(b, 2, BigDecimal.ROUND_HALF_UP);
                            cdValue = c.doubleValue();
                        }
                        teachActiveParamPO.setAverJoinSum(cdValue);//场均参加人次

                        BigDecimal sct = new BigDecimal(shichangTotal);//总时长
                        Double averSctValue = 0.0;
                        if (!changci.equals(0)) {
                            BigDecimal averSct = sct.divide(b, 2, BigDecimal.ROUND_HALF_UP);
                            averSctValue = averSct.doubleValue();
                        }
                        //BigDecimal averSct= sct.divide(b);
                        teachActiveParamPO.setAverDureTime(averSctValue);//场均时长（分钟）
                        teachActiveParamPO.setDureTime(shichangTotal);

                        teachActiveParamPO.setIsContain("N");
                        teachActiveParamPO.setMonthDate(monthDate);
                        teachActiveParamPO.setRecordFlow(UUID.randomUUID().toString());
                        teachActiveParamPO.setCreateTime(DateUtil.getCurrDateTime());
                        teachActiveParamPO.setRoleFlag(roleList.get(m));
                        if("".equals(teachActiveParamPO.getParentOrgFlow()) || null==teachActiveParamPO.getParentOrgFlow()){
                            teachActiveParamPO.setProvince(teachActiveParamPO.getOrgProvId());
                            teachActiveParamPO.setCity(teachActiveParamPO.getOrgCityId());
                        }else{
                            String privoinc ="";String cityId="";
                            for(SysOrg so:orgsPrivoncCity){
                                if(so.getOrgFlow().equals(teachActiveParamPO.getParentOrgFlow())){
                                    privoinc = so.getOrgProvId();cityId =so.getOrgCityId();
                                    break;
                                }
                            }
                            teachActiveParamPO.setProvince(privoinc);
                            teachActiveParamPO.setCity(cityId);
                        }

                    }
                    List<TeachActiveParamPO> newList = activitySessionDevideMax(orgInfoList);//求进度条值
                    orgInfoList = newList;
                    if(orgInfoList.size()>0){
                        schdualTaskMapper.insertPCUTeachActive(orgInfoList);
                    }
                }
            }
      }
   /* return orgInfoList;*/
}
        logger.info("---------End ShengshiGaoxiaoactive  doing-------");
}
    /**
     *  @author: yuh
     *  @Date: 2020/3/23 15:02
     *  @Description: 高校 教学活动
     */
    public void  gaoxiaoTeachActive(String monthDate,String [] isContainArray,String roleFlag) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位

        List<TeachActiveParamPO> teachActiveParamPOUniversitylist=new ArrayList<>();

            List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
  for(SysDict dict:sendSchools){

                Map<String, Object> map = new HashMap<>();
                List<TeachActiveParamPO> orgUniversity = new ArrayList<>();
                List<TeachActiveParamPO> distinctOrgUniversity = new ArrayList<>();
                map.put("universityName", dict.getDictName());
                List<JsResDoctorRecruitExt> resDoctorRecruitExtsList = monthlyReportExtMapper2.getUserInfoAndOrgNameByUniversity(map);
                for (JsResDoctorRecruitExt jRR : resDoctorRecruitExtsList) {
                    TeachActiveParamPO s1 = new TeachActiveParamPO();
                    List<String> doctorlistS = new ArrayList<>();
                    String orgFlow = jRR.getOrgFlow();
                    String orgName = jRR.getOrgName();
                    s1.setOrgFlow(orgFlow);
                    s1.setOrgName(orgName);
                    s1.setOrgCode(jRR.getOrgCode());

                    for (JsResDoctorRecruitExt jRR2 : resDoctorRecruitExtsList) {
                        if (orgFlow.equals(jRR2.getOrgFlow())) {
                            doctorlistS.add(jRR2.getDoctorFlow());
                        }
                    }
                    s1.setDoctorFlowsInOrg(doctorlistS);
                    orgUniversity.add(s1);
                }
                Set<TeachActiveParamPO> personSet = new TreeSet<>((o1, o2) -> o1.getOrgFlow().compareTo(o2.getOrgFlow()));
                personSet.addAll(orgUniversity);
                distinctOrgUniversity = new ArrayList<>(personSet);

                teachActiveParamPOUniversitylist = resetOrgTeachActive(distinctOrgUniversity);

      for (int count = 0; count < isContainArray.length; count++) {

                //包含协同
                if("isContain".equals(isContainArray[count])){
                    for(TeachActiveParamPO teachActiveParamPO:teachActiveParamPOUniversitylist){
                        String orgFlow =teachActiveParamPO.getOrgFlow();
                        Map<String,Object> mymap=new HashMap<>();
                        mymap.put("orgFlow",orgFlow);
                        mymap.put("universityDoctorFlows",teachActiveParamPO.getDoctorFlowsInOrg());
                        Integer trainerSumCount =monthlyReportExtMapper2.getTrainerSum(mymap);
                        teachActiveParamPO.setTrainerSum(trainerSumCount); //在培总数

                        List<Map<String,Object>> activeInfoList=monthlyReportExtMapper2.findActivityListyuh(mymap);
                        Integer changci=0;//场次
                        Integer alljoin=0;//总参加人次
                        Double shichangTotal=0.00;//总时长
                        for(Map<String,Object> li:activeInfoList){
                            String activityFlow  = (String)li.get("activityFlow");
                            String scanNum  = ((BigDecimal)li.get("scanNum")).toString(); //签字个数
                            if(!"0".equals(scanNum)){
                                List<TeachingActivityResult> teachingActivityResultList =monthlyReportExtMapper2.getScanTime1AndScanTime2(activityFlow);
                                //获取新的教学活动结果信息（判断当前学员是否是该高校该基地）
                                teachingActivityResultList = getNewteachingActivityResultList(teachingActivityResultList,teachActiveParamPO.getDoctorFlowsInOrg());
                                String yearMonthshichang=maxminTime(teachingActivityResultList);
                                if("".equals(yearMonthshichang)){
//                                    throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
                                }else{
                                    String yearmonth=yearMonthshichang.split(",")[0];
                                    String shichang=yearMonthshichang.split(",")[1];
                                    if(monthDate.equals(yearmonth)){
                                        changci++;
                                        alljoin=alljoin+teachingActivityResultList.size();
                                        shichangTotal=shichangTotal+Double.parseDouble(shichang);
                                    }
                                }
                            }
                        }
                        teachActiveParamPO.setTeachActiveSessionSum(changci);// 教学活动举办场次
                        teachActiveParamPO.setAllJoinSum(alljoin); //总参加人次
                        BigDecimal a = new BigDecimal(alljoin);
                        BigDecimal b = new BigDecimal(changci);
                        Double cdValue=0.0;
                        if(!changci.equals(0)){
                            BigDecimal c= a.divide(b,2,BigDecimal.ROUND_HALF_UP);
                            cdValue = c.doubleValue();
                        }
                        teachActiveParamPO.setAverJoinSum(cdValue);//场均参加人次

                        BigDecimal sct = new BigDecimal(shichangTotal);//总时长
                        Double averSctValue=0.0;
                        if(!changci.equals(0)){
                            BigDecimal averSct= sct.divide(b,2,BigDecimal.ROUND_HALF_UP);
                            averSctValue =averSct.doubleValue();
                        }
                        teachActiveParamPO.setAverDureTime(averSctValue);//场均时长（分钟）
                        teachActiveParamPO.setDureTime(shichangTotal);

                        teachActiveParamPO.setIsContain("Y");
                        teachActiveParamPO.setMonthDate(monthDate);
                        teachActiveParamPO.setRecordFlow(UUID.randomUUID().toString());
                        teachActiveParamPO.setCreateTime(DateUtil.getCurrDateTime());
                        teachActiveParamPO.setRoleFlag(roleFlag);
                        teachActiveParamPO.setUniversity(dict.getDictFlow());

                    }
                    //给国家基地赋值包含协同的信息
                    for (TeachActiveParamPO tpo:teachActiveParamPOUniversitylist){
                        if("".equals(tpo.getParentOrgFlow())){
                            Integer zaipeiSum=tpo.getTrainerSum(); //在陪人数
                            Integer jubanchangci=tpo.getTeachActiveSessionSum();
                            Integer zongcanjiarenci=tpo.getAllJoinSum();
                            Double zongshichang=tpo.getDureTime();
                            for(TeachActiveParamPO newTpo2:teachActiveParamPOUniversitylist){
                                if(tpo.getOrgFlow().equals(newTpo2.getParentOrgFlow())){
                                    zaipeiSum = zaipeiSum + newTpo2.getTrainerSum();
                                    jubanchangci=jubanchangci+newTpo2.getTeachActiveSessionSum();
                                    zongcanjiarenci=zongcanjiarenci+newTpo2.getAllJoinSum();
                                    zongshichang=zongshichang+newTpo2.getDureTime();
                                }
                            }
                            tpo.setTrainerSum(zaipeiSum);
                            tpo.setTeachActiveSessionSum(jubanchangci);
                            tpo.setAllJoinSum(zongcanjiarenci);

                            BigDecimal a1 = new BigDecimal(zongcanjiarenci);
                            BigDecimal b1 = new BigDecimal(jubanchangci);
                            Double cdValue=0.0;
                            if(!jubanchangci.equals(0)){
                                BigDecimal c= a1.divide(b1,2,BigDecimal.ROUND_HALF_UP);
                                cdValue = c.doubleValue();
                            }
                            tpo.setAverJoinSum(cdValue);//场均参加人次

                            BigDecimal sct = new BigDecimal(zongshichang);//总时长
                            Double averSctValue=0.0;
                            if(!jubanchangci.equals(0)){
                                BigDecimal averSct= sct.divide(b1,2,BigDecimal.ROUND_HALF_UP);
                                averSctValue =averSct.doubleValue();
                            }
                            tpo.setAverDureTime(averSctValue);//场均时长

                            tpo.setIsContain("Y");
                            tpo.setMonthDate(monthDate);
                            tpo.setRecordFlow(UUID.randomUUID().toString());
                            tpo.setCreateTime(DateUtil.getCurrDateTime());
                            tpo.setRoleFlag(roleFlag);
                            tpo.setUniversity(dict.getDictFlow());
                        }
                    }
                    List<TeachActiveParamPO>  newList1=  activitySessionDevideMax( teachActiveParamPOUniversitylist );//求进度条值
                    teachActiveParamPOUniversitylist=newList1;
                    if(teachActiveParamPOUniversitylist.size()>0){
                        schdualTaskMapper.insertPCUTeachActive(teachActiveParamPOUniversitylist);
                    }
           //不包含协同
            }else{
                    //
                    for(TeachActiveParamPO teachActiveParamPO:teachActiveParamPOUniversitylist){
                        String orgFlow =teachActiveParamPO.getOrgFlow();
                        Map<String,Object> mymap=new HashMap<>();
                        mymap.put("orgFlow",orgFlow);
                        mymap.put("universityDoctorFlows",teachActiveParamPO.getDoctorFlowsInOrg());
                        Integer trainerSumCount =monthlyReportExtMapper2.getTrainerSum(mymap);
                        teachActiveParamPO.setTrainerSum(trainerSumCount); //在培总数

                        List<Map<String,Object>> activeInfoList=monthlyReportExtMapper2.findActivityListyuh(mymap);
                        Integer changci=0;//场次
                        Integer alljoin=0;//总参加人次
                        Double shichangTotal=0.00;//总时长
                        for(Map<String,Object> li:activeInfoList){
                            String activityFlow  = (String)li.get("activityFlow");
                            String scanNum  = ((BigDecimal)li.get("scanNum")).toString(); //签字个数
                            if(!"0".equals(scanNum)){
                                List<TeachingActivityResult> teachingActivityResultList =monthlyReportExtMapper2.getScanTime1AndScanTime2(activityFlow);
                                //获取新的教学活动结果信息（判断当前学员是否是该高校该基地）
                                teachingActivityResultList = getNewteachingActivityResultList(teachingActivityResultList,teachActiveParamPO.getDoctorFlowsInOrg());
                                String yearMonthshichang= maxminTime(teachingActivityResultList);
                                if("".equals(yearMonthshichang)){
//                                    throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
                                }else{
                                    String yearmonth=yearMonthshichang.split(",")[0];
                                    String shichang=yearMonthshichang.split(",")[1];
                                    if(monthDate.equals(yearmonth)){
                                        changci++;
                                        alljoin=alljoin+teachingActivityResultList.size();
                                        shichangTotal=shichangTotal+Double.parseDouble(shichang);
                                    }
                                }
                            }
                        }
                        teachActiveParamPO.setTeachActiveSessionSum(changci);// 教学活动举办场次
                        teachActiveParamPO.setAllJoinSum(alljoin); //总参加人次
                        BigDecimal a = new BigDecimal(alljoin);
                        BigDecimal b = new BigDecimal(changci);
                        Double cdValue=0.0;
                        if(!changci.equals(0)){
                            BigDecimal c= a.divide(b,2,BigDecimal.ROUND_HALF_UP);
                            cdValue = c.doubleValue();
                        }
                        //BigDecimal c= a.divide(b);
                        teachActiveParamPO.setAverJoinSum(cdValue);//场均参加人次

                        BigDecimal sct = new BigDecimal(shichangTotal);//总时长
                        Double averSctValue=0.0;
                        if(!changci.equals(0)){
                            BigDecimal averSct= sct.divide(b,2,BigDecimal.ROUND_HALF_UP);
                            averSctValue =averSct.doubleValue();
                        }
                        //BigDecimal averSct= sct.divide(b);
                        teachActiveParamPO.setAverDureTime(averSctValue);//场均时长（分钟）
                        teachActiveParamPO.setDureTime(shichangTotal);

                        teachActiveParamPO.setIsContain("N");
                        teachActiveParamPO.setMonthDate(monthDate);
                        teachActiveParamPO.setRecordFlow(UUID.randomUUID().toString());
                        teachActiveParamPO.setCreateTime(DateUtil.getCurrDateTime());
                        teachActiveParamPO.setRoleFlag(roleFlag);
                        teachActiveParamPO.setUniversity(dict.getDictFlow());
                    }
                    List<TeachActiveParamPO>  newList= activitySessionDevideMax( teachActiveParamPOUniversitylist );//求进度条值
                    teachActiveParamPOUniversitylist=newList;
                    if(teachActiveParamPOUniversitylist.size()>0){
                        schdualTaskMapper.insertPCUTeachActive(teachActiveParamPOUniversitylist);
                    }
                }
           // return teachActiveParamPOUniversitylist;
      }
  }
 }
    //教学活动举办场次和最高值比例
    public List<TeachActiveParamPO> activitySessionDevideMax(List<TeachActiveParamPO> orgInfoList ){
        Integer max=0;
        Integer xitongMax=0;
        for(TeachActiveParamPO t:orgInfoList){
            if("".equals(t.getParentOrgFlow())){
                Integer A =t.getTeachActiveSessionSum();
                if(A>max){
                    max=A;
                }
            }else{
                Integer xitongA =t.getTeachActiveSessionSum();
                if(xitongA>xitongMax){
                    xitongMax=xitongA;
                }
            }

        }
        BigDecimal a = new BigDecimal(max);
        BigDecimal xitonga = new BigDecimal(xitongMax);
        for(TeachActiveParamPO t1:orgInfoList){
            BigDecimal b = new BigDecimal(t1.getTeachActiveSessionSum());
            Double cdValue=0.00;
            if("".equals(t1.getParentOrgFlow())){
                if(!max.equals(0)){
                    BigDecimal c= b.divide(a,2,BigDecimal.ROUND_HALF_UP);
                    cdValue=c.doubleValue();
                }
            }else{
                if(!xitongMax.equals(0)){
                    BigDecimal c= b.divide(xitonga,2,BigDecimal.ROUND_HALF_UP);
                    cdValue=c.doubleValue();
                }
            }
            t1.setAverTeachActiveSessionSum(cdValue*100+"");
        }
        return orgInfoList;
    }
    public String maxminTime( List<TeachingActivityResult> teachingActivityResultList) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM");
        List<Date> time1List=new ArrayList<>();
        List<Date> time2List=new ArrayList<>();
        for(TeachingActivityResult tar:teachingActivityResultList){
            Date scantime1=	sdf.parse(tar.getScanTime());
            Date scantime2=	sdf.parse(tar.getScanTime2());
            time1List.add(scantime1);
            time2List.add(scantime2);
        }
        Date min1 =Collections.min(time1List);
        Date min2 =Collections.min(time2List);
        long diff = min2.getTime() - min1.getTime();//这样得到的差值是毫秒级别
        long minutes = (diff)/(1000* 60);
        String ym1 = sdf2.format(min1);
        String ym2 = sdf2.format(min2);
        if(ym1.equals(ym2)){
            return ym1+","+minutes;
        }else{
            return "";
        }

    }
    /*//获取新的教学活动结果信息（判断当前学员是否是该高校该基地）*/
    public List<TeachingActivityResult> getNewteachingActivityResultList(List<TeachingActivityResult> teachingActivityResultList,List<String>doctorFlowsInOrg){
        List<TeachingActivityResult> list=new ArrayList<>();
        for(TeachingActivityResult t:teachingActivityResultList){
            if(doctorFlowsInOrg.contains(t.getUserFlow())){
                list.add(t);
            }
        }
        return list;
    }
    public List<TeachActiveParamPO> resetOrgTeachActive(List<TeachActiveParamPO> list){
        List<TeachActiveParamPO> poList=new ArrayList<>();
        List<TeachActiveParamPO> first=new ArrayList<>();
        List<TeachActiveParamPO> second=new ArrayList<>();
        for(TeachActiveParamPO aa:list){
            TeachActiveParamPO s1=new TeachActiveParamPO();
//                   List<SysOrg>  jointOrgList= orgBiz.searchJointOrgsByOrg(aa.getOrgFlow());
            List<ResJointOrg> joinorgInfolist=  resJointOrgBiz.searchResJointByJointOrgFlow(aa.getOrgFlow());
            if(joinorgInfolist.size()==0){
                s1.setParentOrgFlow("");
                s1.setOrgFlow(aa.getOrgFlow());
                s1.setOrgName(aa.getOrgName());
                s1.setNo(aa.getOrgCode());
                s1.setDoctorFlowsInOrg(aa.getDoctorFlowsInOrg());
                poList.add(s1);
            }else{
                for(ResJointOrg bb:joinorgInfolist){
                    TeachActiveParamPO s2=new TeachActiveParamPO();
                    if(isexistTeachactive(bb.getOrgFlow(),list)){
                        s2.setOrgFlow(bb.getJointOrgFlow());
                        s2.setOrgName(bb.getJointOrgName());
                        s2.setParentOrgFlow(bb.getOrgFlow());
                        s2.setDoctorFlowsInOrg(aa.getDoctorFlowsInOrg());
                    }else{
                        s2.setOrgFlow(bb.getJointOrgFlow());
                        s2.setOrgName(bb.getJointOrgName());
                        s2.setParentOrgFlow("");
                        s2.setDoctorFlowsInOrg(aa.getDoctorFlowsInOrg());
                    }
                    poList.add(s2);
                }
            }
        }
        if(poList.size()>0){
            for(TeachActiveParamPO li:poList){
                if("".equals(li.getParentOrgFlow())){
                    first.add(li);
                }
                if(!"".equals(li.getParentOrgFlow())){
                    second.add(li);
                }
            }
            for(int i=0 ;i< first.size();i++){
                /* first.get(i).setNo((i+1)+"");*/
                String orgflow =first.get(i).getOrgFlow();
                int count=0;
                for(int j=0;j<second.size();j++){
                    if(second.get(j).getParentOrgFlow().equals(orgflow)){
                        second.get(j).setNo(/*(i+1)*/first.get(i).getNo()+"-"+(count+1));
                        count++;
                    }
                }
            }
            first.addAll(second);
        }
        return first;
    }
    public boolean isexistTeachactive(String orgflow,List<TeachActiveParamPO> list){
        for(TeachActiveParamPO doc:list){
            if(orgflow.equals(doc.getOrgFlow())){
                return true;
            }
        }
        return  false;
    }


    /**
     *  @author: yuh
     *  @Date: 2020/3/24 15:28
     *  @Description: 省市 学员出科情况统计定时任务
     */
//    @Scheduled(cron="0 0 1 1 * ?")//每月1号凌晨1点执行
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void shengshiDoctorOutOffice(){
        logger.info("---------start ShengshiGaoxiao DoctorOutOffice  doing-------");

        String monthDate = InitConfig.getSysCfg("res_static_job_month");
        if(StringUtil.isBlank(monthDate)){
            monthDate = getLastMonths(-1); //获取上月  -9
        }

         schdualTaskMapper.deletePreviousCurrMonthDoctorOutOffice(monthDate);

       /* List<SysOrg> orgs = new ArrayList<>();
        List<DoctorOutOfficeParamPO> doctorOutOfficeParamPOList=new ArrayList<>();*/

        String[] a = new String[2];
        a[0] = "";
        a[1] = "isContain";
        List<String> roleList = new ArrayList<>();
        roleList.add("global");
        roleList.add("charge");
        roleList.add("university");
        List<String> datas =new ArrayList<>();
        datas.add("both");
        datas.add("notGraduate");
        datas.add("graduate");
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位
       /* String docTypeList[]=convert(datas);//人员类型*/
for(int m=0;m<roleList.size();m++){


    //高校角色
    if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleList.get(m))){
            gaoxiaoDoctorOutOffice(monthDate,a,roleList.get(m),datas);
       /* List li = resMonthlyReportGlobalControllerClass.doctorOutOfficeInfo_University(sortFlag,monthDate,isContain, orgs ,doctorOutOfficeParamPOList,docTypeList,role);
        return li;*/
    }else{
        for(int count=0;count<a.length;count++){

            for(String dataTYpe:datas){
                List<SysOrg> orgs = new ArrayList<>();
                List<DoctorOutOfficeParamPO> doctorOutOfficeParamPOList=new ArrayList<>();
                List<SysOrg> orgsPrivoncCity = new ArrayList<>();

                String docTypeList[]=new String[4];
                if ("both".equals(dataTYpe)) {
                    docTypeList[0] = "Company";
                    docTypeList[1] = "CompanyEntrust";
                    docTypeList[2] = "Social";
                    docTypeList[3] = "Graduate";
                } else {
                    if ("notGraduate".equals(dataTYpe)) {
                        docTypeList[0] = "Company";
                        docTypeList[1] = "CompanyEntrust";
                        docTypeList[2] = "Social";
                    } else {
                        docTypeList[0] = "Graduate";
                    }
                }
                SysOrg searchOrg=new SysOrg();

                searchOrg.setOrgProvId("320000"); //江苏省
                searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
                searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                //---start--
                List<SysOrg> countryOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
                for(int i =0;i<countryOrgs.size();i++){
                    countryOrgs.get(i).setParentOrgFlow("");
                    countryOrgs.get(i).setNo(/*(i+1)+""*/countryOrgs.get(i).getOrgCode());
                    List<SysOrg>  jointOrgList= orgBiz.searchJointOrgsByOrg(countryOrgs.get(i).getOrgFlow());
                    if(jointOrgList!=null&&!jointOrgList.isEmpty()){
                        for(int j=0;j<jointOrgList.size();j++){
                            jointOrgList.get(j).setParentOrgFlow(countryOrgs.get(i).getOrgFlow());
                            jointOrgList.get(j).setNo(/*(i+1)*/countryOrgs.get(i).getOrgCode()+"-"+(j+1));
                            orgs.add(jointOrgList.get(j));
                        }
                    }
                    orgs.add(countryOrgs.get(i));
                    orgsPrivoncCity.add(countryOrgs.get(i));
                }
                //对象转化
                for(int i=0;i<orgs.size();i++){
                    DoctorOutOfficeParamPO dooppo=new DoctorOutOfficeParamPO();
                    BeanUtils.copyProperties(orgs.get(i),dooppo);
                    doctorOutOfficeParamPOList.add(dooppo);
                }
               //包含协同
                if("isContain".equals(a[count])){

                    for(DoctorOutOfficeParamPO doctorOutOfficeParamPO:doctorOutOfficeParamPOList){
                        Integer outOfficeSum=0;
                        Integer outOfficeActualSum=0;
                        List<String> doctorFlowLIst=new ArrayList<>(); //存取每月实际出科人的userflow
                        Map<String,Object> outOfficeDoctorMap=new HashMap<>();
                        String orgFlow = doctorOutOfficeParamPO.getOrgFlow();
                        outOfficeDoctorMap.put("orgFlow",orgFlow);
                        outOfficeDoctorMap.put("docTypeList",docTypeList);
                        List<Map<String,Object>> outOfficeDoctorInfoList= monthlyReportExtMapper2.findOutOfficeDoctorInfo(outOfficeDoctorMap);
                        String cksh="N";
                        JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_cksh");
                        if(cfg!=null)
                        {
                            cksh=cfg.getCfgValue();
                        }
                        for(Map<String,Object> mapInfo: outOfficeDoctorInfoList){
                            Map<String,String> doctorMap=new HashMap<>();
                            doctorMap.put("doctorFlow",(String) mapInfo.get("doctorFlow"));
                            List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByMap(doctorMap);
                            Integer outOfficeSumByOnePersion= getMonthOutOfficeSum(monthDate,arrResultList);//1或者0
                            Integer outOfficeActualSumByOnePersion = getMonthActualOutOfficeSum(cksh,monthDate,arrResultList);//1或者0
                            outOfficeSum= outOfficeSum+ outOfficeSumByOnePersion;
                            outOfficeActualSum= outOfficeActualSum+ outOfficeActualSumByOnePersion;
                            if(1==outOfficeActualSumByOnePersion){
                                doctorFlowLIst.add((String) mapInfo.get("doctorFlow"));
                            }
                        }

                        doctorOutOfficeParamPO.setMonthOutOfficeSum(outOfficeSum);//本月应出科人数
                        doctorOutOfficeParamPO.setMonthActualOutOfficeSum(outOfficeActualSum);//本月实际出科人数
                        Integer notOutOfficeSum= outOfficeSum-outOfficeActualSum;
                        doctorOutOfficeParamPO.setMonthNotOutOfficeSum(notOutOfficeSum);//本月未出科人数

                        doctorOutOfficeParamPO.setDoctorFlows(doctorFlowLIst);

                        doctorOutOfficeParamPO.setIsContain("Y");
                        doctorOutOfficeParamPO.setMonthDate(monthDate);
                        doctorOutOfficeParamPO.setRecordFlow(UUID.randomUUID().toString());
                        doctorOutOfficeParamPO.setCreateTime(DateUtil.getCurrDateTime());
                        doctorOutOfficeParamPO.setRoleFlag(roleList.get(m));
                        if("".equals(doctorOutOfficeParamPO.getParentOrgFlow()) || null==doctorOutOfficeParamPO.getParentOrgFlow()){
                            doctorOutOfficeParamPO.setProvince(doctorOutOfficeParamPO.getOrgProvId());
                            doctorOutOfficeParamPO.setCity(doctorOutOfficeParamPO.getOrgCityId());
                        }else{
                            String privoinc ="";String cityId="";
                            for(SysOrg so:orgsPrivoncCity){
                                if(so.getOrgFlow().equals(doctorOutOfficeParamPO.getParentOrgFlow())){
                                    privoinc = so.getOrgProvId();cityId =so.getOrgCityId();
                                    break;
                                }
                            }
                            doctorOutOfficeParamPO.setProvince(privoinc);
                            doctorOutOfficeParamPO.setCity(cityId);
                        }
                        if("both".equals(dataTYpe)){
                            doctorOutOfficeParamPO.setNotGraduate("Y");
                            doctorOutOfficeParamPO.setGraduate("Y");
                        }else {
                            if ("notGraduate".equals(dataTYpe)) {
                                doctorOutOfficeParamPO.setNotGraduate("Y");
                                doctorOutOfficeParamPO.setGraduate("N");
                            } else {
                                doctorOutOfficeParamPO.setNotGraduate("N");
                                doctorOutOfficeParamPO.setGraduate("Y");
                            }
                        }

                    }
                    for(DoctorOutOfficeParamPO dooppoA:doctorOutOfficeParamPOList){
                        String orgFlow =dooppoA.getOrgFlow();
                        Integer outOfficeSum=dooppoA.getMonthOutOfficeSum();
                        Integer outActualOfficeSum=dooppoA.getMonthActualOutOfficeSum();
                        Integer outNotOfficeSum=dooppoA.getMonthNotOutOfficeSum();
                        List<String> doctorFlowsA =   dooppoA.getDoctorFlows(); //
                        for(DoctorOutOfficeParamPO dooppoB:doctorOutOfficeParamPOList){
                            if(orgFlow.equals(dooppoB.getParentOrgFlow())){
                                outOfficeSum =outOfficeSum+ dooppoB.getMonthOutOfficeSum();
                                outActualOfficeSum = outActualOfficeSum + dooppoB.getMonthActualOutOfficeSum();
                                outNotOfficeSum = outNotOfficeSum + dooppoB.getMonthNotOutOfficeSum();
                                List<String> doctorFlowsB=  dooppoB.getDoctorFlows();
                                doctorFlowsA.addAll(doctorFlowsB);
                            }
                        }
                        dooppoA.setMonthOutOfficeSum(outOfficeSum);//本月应出科人数(国家基地+协同)
                        dooppoA.setMonthActualOutOfficeSum(outActualOfficeSum);//本月实际出科人数（国家+协同）
                        dooppoA.setMonthNotOutOfficeSum(outNotOfficeSum);//本月未出科人数（国家+协同）
                        String outOfficeExceptionRate="0%";
                        if(0!=outOfficeSum){
                            outOfficeExceptionRate = numberFormat.format((float) outNotOfficeSum / (float) outOfficeSum * 100)+"%";
                        }
                        dooppoA.setOutOfficeExceptionRate(outOfficeExceptionRate);//出科异常比例（国家+协同）
                        Map<String,Object> outMap=new HashMap<>();
                        outMap.put("orgFlow",orgFlow);
                        outMap.put("docTypeList",docTypeList);
                        outMap.put("doctorFlowList",doctorFlowsA);
                        List<Map<String, Object>> FinishSumAndTotalSumList=new ArrayList<>();
                        if(doctorFlowsA.size()>0){
                            FinishSumAndTotalSumList= monthlyReportExtMapper2.findOutOfficeFinishSumAndTotalSum(outMap);
                        }
                        Integer[] finishAndtotal = getFinishSumAndTotalSum( FinishSumAndTotalSumList);
                        Integer finishSum =finishAndtotal[0];Integer totalSum= finishAndtotal[1];

                        String outOfficeFinishRate="0%";
                        if(0!=totalSum){
                            outOfficeFinishRate= numberFormat.format((float) finishSum / (float) totalSum * 100)+"%";
                        }
                        dooppoA.setOutOfficeDataFinishRate(outOfficeFinishRate);//出科学员数据平均完成率（国家+协同）
                    }

                    if(doctorOutOfficeParamPOList.size()>0){
                        schdualTaskMapper.insertPCUDoctorOutOffice(doctorOutOfficeParamPOList);
                    }
          //不包含协同
           }else{
                    for(DoctorOutOfficeParamPO doctorOutOfficeParamPO:doctorOutOfficeParamPOList){
                        Integer outOfficeSum=0;
                        Integer outOfficeActualSum=0;
                        List<String> doctorFlowLIst=new ArrayList<>(); //装每月实际出科人的userflow
                        Map<String,Object> outOfficeDoctorMap=new HashMap<>();
                        String orgFlow = doctorOutOfficeParamPO.getOrgFlow();
                        outOfficeDoctorMap.put("orgFlow",orgFlow);
                        outOfficeDoctorMap.put("docTypeList",docTypeList);
                        List<Map<String,Object>> outOfficeDoctorInfoList= monthlyReportExtMapper2.findOutOfficeDoctorInfo(outOfficeDoctorMap);
                        String cksh="N";
                        JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_cksh");
                        if(cfg!=null)
                        {
                            cksh=cfg.getCfgValue();
                        }
                        for(Map<String,Object> mapInfo: outOfficeDoctorInfoList){
                            Map<String,String> doctorMap=new HashMap<>();
                            doctorMap.put("doctorFlow",(String) mapInfo.get("doctorFlow"));
                            List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByMap(doctorMap);
                            Integer outOfficeSumByOnePersion= getMonthOutOfficeSum(monthDate,arrResultList);//1或者0
                            Integer outOfficeActualSumByOnePersion = getMonthActualOutOfficeSum(cksh,monthDate,arrResultList);//1或者0
                            outOfficeSum= outOfficeSum+ outOfficeSumByOnePersion;
                            outOfficeActualSum= outOfficeActualSum+ outOfficeActualSumByOnePersion;
                            if(1==outOfficeActualSumByOnePersion){
                                doctorFlowLIst.add((String) mapInfo.get("doctorFlow"));
                            }
                        }
                        outOfficeDoctorMap.put("doctorFlowList",doctorFlowLIst);
                        List<Map<String, Object>> FinishSumAndTotalSumList=new ArrayList<>();
                        if(doctorFlowLIst.size()>0){
                            FinishSumAndTotalSumList= monthlyReportExtMapper2.findOutOfficeFinishSumAndTotalSum(outOfficeDoctorMap);
                        }
                        Integer[] finishAndtotal = getFinishSumAndTotalSum( FinishSumAndTotalSumList);
                        Integer finishSum =finishAndtotal[0];Integer totalSum= finishAndtotal[1];

                        doctorOutOfficeParamPO.setMonthOutOfficeSum(outOfficeSum);//本月应出科人数
                        doctorOutOfficeParamPO.setMonthActualOutOfficeSum(outOfficeActualSum);//本月实际出科人数
                        Integer notOutOfficeSum= outOfficeSum-outOfficeActualSum;
                        doctorOutOfficeParamPO.setMonthNotOutOfficeSum(notOutOfficeSum);//本月未出科人数

                        String outOfficeExceptionRate="0%";
                        if(0!=outOfficeSum){
                            outOfficeExceptionRate = numberFormat.format((float) notOutOfficeSum / (float) outOfficeSum * 100)+"%";
                        }
                        doctorOutOfficeParamPO.setOutOfficeExceptionRate(outOfficeExceptionRate);//出科异常比例
                        String outOfficeFinishRate="0%";
                        if(0!=totalSum){
                            outOfficeFinishRate= numberFormat.format((float) finishSum / (float) totalSum * 100)+"%";
                        }
                        doctorOutOfficeParamPO.setOutOfficeDataFinishRate(outOfficeFinishRate);//出科学员数据平均完成率

                        doctorOutOfficeParamPO.setIsContain("N");
                        doctorOutOfficeParamPO.setMonthDate(monthDate);
                        doctorOutOfficeParamPO.setRecordFlow(UUID.randomUUID().toString());
                        doctorOutOfficeParamPO.setCreateTime(DateUtil.getCurrDateTime());
                        doctorOutOfficeParamPO.setRoleFlag(roleList.get(m));
                        if("".equals(doctorOutOfficeParamPO.getParentOrgFlow()) || null==doctorOutOfficeParamPO.getParentOrgFlow()){
                            doctorOutOfficeParamPO.setProvince(doctorOutOfficeParamPO.getOrgProvId());
                            doctorOutOfficeParamPO.setCity(doctorOutOfficeParamPO.getOrgCityId());
                        }else{
                            String privoinc ="";String cityId="";
                            for(SysOrg so:orgsPrivoncCity){
                                if(so.getOrgFlow().equals(doctorOutOfficeParamPO.getParentOrgFlow())){
                                    privoinc = so.getOrgProvId();cityId =so.getOrgCityId();
                                    break;
                                }
                            }
                            doctorOutOfficeParamPO.setProvince(privoinc);
                            doctorOutOfficeParamPO.setCity(cityId);
                        }
                        if("both".equals(dataTYpe)){
                            doctorOutOfficeParamPO.setNotGraduate("Y");
                            doctorOutOfficeParamPO.setGraduate("Y");
                        }else {
                            if ("notGraduate".equals(dataTYpe)) {
                                doctorOutOfficeParamPO.setNotGraduate("Y");
                                doctorOutOfficeParamPO.setGraduate("N");
                            } else {
                                doctorOutOfficeParamPO.setNotGraduate("N");
                                doctorOutOfficeParamPO.setGraduate("Y");
                            }
                        }
                    }
                    if(doctorOutOfficeParamPOList.size()>0){
                        schdualTaskMapper.insertPCUDoctorOutOffice(doctorOutOfficeParamPOList);
                    }

                }
               /* doctorOutOfficeParamPOList=	outOfficeListSort(sortFlag,doctorOutOfficeParamPOList);*//*排序*/
            /*return doctorOutOfficeParamPOList;*/
            }
        }
    }
 }
        logger.info("---------End ShengshiGaoxiao DoctorOutOffice  doing-------");
}

    /**
     *  @author: yuh
     *  @Date: 2020/3/24 15:29
     *  @Description: 高校 学员出科情况统计定时任务
     */
    public void gaoxiaoDoctorOutOffice(String monthDate,String [] isContainArray,String roleFlag,List<String> datas){
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位

           List<DoctorOutOfficeParamPO> doctorOutOfficeParamPOUniversitylist=new ArrayList<>();

            List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
for(SysDict dict:sendSchools){

    for (int count = 0; count < isContainArray.length; count++) {

            for(String dataTYpe:datas){
                String docTypeList[]=new String[4];
                if ("both".equals(dataTYpe)) {
                    continue;
                   /* docTypeList[0] = "Company";
                    docTypeList[1] = "CompanyEntrust";
                    docTypeList[2] = "Social";
                    docTypeList[3] = "Graduate";*/
                } else {
                    if ("notGraduate".equals(dataTYpe)) {
                        continue;
                       /* docTypeList[0] = "Company";
                        docTypeList[1] = "CompanyEntrust";
                        docTypeList[2] = "Social";*/
                    } else {
                        docTypeList[0] = "Graduate";
                    }
                }

                Map<String, Object> map = new HashMap<>();
                List<DoctorOutOfficeParamPO> orgUniversity = new ArrayList<>();
                List<DoctorOutOfficeParamPO> distinctOrgUniversity = new ArrayList<>();
                map.put("universityName", dict.getDictName());
                List<JsResDoctorRecruitExt> resDoctorRecruitExtsList = monthlyReportExtMapper2.getUserInfoAndOrgNameByUniversity(map);
                for (JsResDoctorRecruitExt jRR : resDoctorRecruitExtsList) {
                    DoctorOutOfficeParamPO s1 = new DoctorOutOfficeParamPO();
                    List<String> doctorlistS = new ArrayList<>();
                    String orgFlow = jRR.getOrgFlow();
                    String orgName = jRR.getOrgName();
                    s1.setOrgFlow(orgFlow);
                    s1.setOrgName(orgName);
                    s1.setOrgCode(jRR.getOrgCode());
                    for (JsResDoctorRecruitExt jRR2 : resDoctorRecruitExtsList) {
                        if (orgFlow.equals(jRR2.getOrgFlow())) {
                            doctorlistS.add(jRR2.getDoctorFlow());
                        }
                    }
                    s1.setDoctorFlowsInOrg(doctorlistS);
                    orgUniversity.add(s1);
                }
                Set<DoctorOutOfficeParamPO> personSet = new TreeSet<>((o1, o2) -> o1.getOrgFlow().compareTo(o2.getOrgFlow()));
                personSet.addAll(orgUniversity);
                distinctOrgUniversity = new ArrayList<>(personSet);

                doctorOutOfficeParamPOUniversitylist = resetOrg(distinctOrgUniversity);


        //包含协同
        if ("isContain".equals(isContainArray[count])) {
            for (DoctorOutOfficeParamPO doctorOutOfficeParamPO : doctorOutOfficeParamPOUniversitylist) {
                Integer outOfficeSum = 0;
                Integer outOfficeActualSum = 0;
                List<String> doctorFlowLIst = new ArrayList<>(); //存取每月实际出科人的userflow
                Map<String, Object> outOfficeDoctorMap = new HashMap<>();
                String orgFlow = doctorOutOfficeParamPO.getOrgFlow();
                outOfficeDoctorMap.put("orgFlow", orgFlow);
                outOfficeDoctorMap.put("docTypeList", docTypeList);
                List<Map<String, Object>> outOfficeDoctorInfoList = monthlyReportExtMapper2.findOutOfficeDoctorInfo(outOfficeDoctorMap);
                outOfficeDoctorInfoList = getUniversityInOrgStudentInfo(doctorOutOfficeParamPO, outOfficeDoctorInfoList);
                String cksh = "N";
                JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_" + orgFlow + "_org_cksh");
                if (cfg != null) {
                    cksh = cfg.getCfgValue();
                }
                for (Map<String, Object> mapInfo : outOfficeDoctorInfoList) {
                    Map<String, String> doctorMap = new HashMap<>();
                    doctorMap.put("doctorFlow", (String) mapInfo.get("doctorFlow"));
                    List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByMap(doctorMap);
                    Integer outOfficeSumByOnePersion = getMonthOutOfficeSum(monthDate, arrResultList);//1或者0
                    Integer outOfficeActualSumByOnePersion = getMonthActualOutOfficeSum(cksh, monthDate, arrResultList);//1或者0
                    outOfficeSum = outOfficeSum + outOfficeSumByOnePersion;
                    outOfficeActualSum = outOfficeActualSum + outOfficeActualSumByOnePersion;
                    if (1 == outOfficeActualSumByOnePersion) {
                        doctorFlowLIst.add((String) mapInfo.get("doctorFlow"));
                    }
                }
                doctorOutOfficeParamPO.setMonthOutOfficeSum(outOfficeSum);//本月应出科人数
                doctorOutOfficeParamPO.setMonthActualOutOfficeSum(outOfficeActualSum);//本月实际出科人数
                Integer notOutOfficeSum = outOfficeSum - outOfficeActualSum;
                doctorOutOfficeParamPO.setMonthNotOutOfficeSum(notOutOfficeSum);//本月未出科人数

                doctorOutOfficeParamPO.setDoctorFlows(doctorFlowLIst);

                doctorOutOfficeParamPO.setIsContain("Y");
                doctorOutOfficeParamPO.setMonthDate(monthDate);
                doctorOutOfficeParamPO.setRecordFlow(UUID.randomUUID().toString());
                doctorOutOfficeParamPO.setCreateTime(DateUtil.getCurrDateTime());
                doctorOutOfficeParamPO.setRoleFlag(roleFlag);
                doctorOutOfficeParamPO.setUniversity(dict.getDictFlow());
                if("both".equals(dataTYpe)){
                    /*doctorOutOfficeParamPO.setNotGraduate("Y");
                    doctorOutOfficeParamPO.setGraduate("Y");*/
                }else {
                    if ("notGraduate".equals(dataTYpe)) {
                        /*doctorOutOfficeParamPO.setNotGraduate("Y");
                        doctorOutOfficeParamPO.setGraduate("N");*/
                    } else {
                       /* doctorOutOfficeParamPO.setNotGraduate("N");*/
                        doctorOutOfficeParamPO.setGraduate("Y");
                    }
                }

            }
            for (DoctorOutOfficeParamPO dooppoA : doctorOutOfficeParamPOUniversitylist) {
                String orgFlow = dooppoA.getOrgFlow();
                Integer outOfficeSum = dooppoA.getMonthOutOfficeSum();
                Integer outActualOfficeSum = dooppoA.getMonthActualOutOfficeSum();
                Integer outNotOfficeSum = dooppoA.getMonthNotOutOfficeSum();
                List<String> doctorFlowsA = dooppoA.getDoctorFlows(); //
                for (DoctorOutOfficeParamPO dooppoB : doctorOutOfficeParamPOUniversitylist) {
                    if (orgFlow.equals(dooppoB.getParentOrgFlow())) {
                        outOfficeSum = outOfficeSum + dooppoB.getMonthOutOfficeSum();
                        outActualOfficeSum = outActualOfficeSum + dooppoB.getMonthActualOutOfficeSum();
                        outNotOfficeSum = outNotOfficeSum + dooppoB.getMonthNotOutOfficeSum();
                        List<String> doctorFlowsB = dooppoB.getDoctorFlows();
                        doctorFlowsA.addAll(doctorFlowsB);
                    }
                }
                dooppoA.setMonthOutOfficeSum(outOfficeSum);//本月应出科人数(国家基地+协同)
                dooppoA.setMonthActualOutOfficeSum(outActualOfficeSum);//本月实际出科人数（国家+协同）
                dooppoA.setMonthNotOutOfficeSum(outNotOfficeSum);//本月未出科人数（国家+协同）
                String outOfficeExceptionRate = "0%";
                if (0 != outOfficeSum) {
                    outOfficeExceptionRate = numberFormat.format((float) outNotOfficeSum / (float) outOfficeSum * 100) + "%";
                }
                dooppoA.setOutOfficeExceptionRate(outOfficeExceptionRate);//出科异常比例（国家+协同）
                Map<String, Object> outMap = new HashMap<>();
                outMap.put("orgFlow", orgFlow);
                outMap.put("docTypeList", docTypeList);
                outMap.put("doctorFlowList", doctorFlowsA);
                List<Map<String, Object>> FinishSumAndTotalSumList = new ArrayList<>();
                if (doctorFlowsA.size() > 0) {
                    FinishSumAndTotalSumList = monthlyReportExtMapper2.findOutOfficeFinishSumAndTotalSum(outMap);
                }
                Integer[] finishAndtotal = getFinishSumAndTotalSum(FinishSumAndTotalSumList);
                Integer finishSum = finishAndtotal[0];
                Integer totalSum = finishAndtotal[1];

                String outOfficeFinishRate = "0%";
                if (0 != totalSum) {
                    outOfficeFinishRate = numberFormat.format((float) finishSum / (float) totalSum * 100) + "%";
                }
                dooppoA.setOutOfficeDataFinishRate(outOfficeFinishRate);//出科学员数据平均完成率（国家+协同）
            }
            if(doctorOutOfficeParamPOUniversitylist.size()>0){
                schdualTaskMapper.insertPCUDoctorOutOffice(doctorOutOfficeParamPOUniversitylist);
            }
            //不包含协同
        } else {

            for (DoctorOutOfficeParamPO doctorOutOfficeParamPO : doctorOutOfficeParamPOUniversitylist) {
                Integer outOfficeSum = 0;
                Integer outOfficeActualSum = 0;
                List<String> doctorFlowLIst = new ArrayList<>(); //装每月实际出科人的userflow
                Map<String, Object> outOfficeDoctorMap = new HashMap<>();
                String orgFlow = doctorOutOfficeParamPO.getOrgFlow();
                outOfficeDoctorMap.put("orgFlow", orgFlow);
                outOfficeDoctorMap.put("docTypeList", docTypeList);
                List<Map<String, Object>> outOfficeDoctorInfoList = monthlyReportExtMapper2.findOutOfficeDoctorInfo(outOfficeDoctorMap);
                outOfficeDoctorInfoList = getUniversityInOrgStudentInfo(doctorOutOfficeParamPO, outOfficeDoctorInfoList);/*获取当前搞笑在该基地的学生*/
                String cksh = "N";
                JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_" + orgFlow + "_org_cksh");
                if (cfg != null) {
                    cksh = cfg.getCfgValue();
                }
                for (Map<String, Object> mapInfo : outOfficeDoctorInfoList) {
                    Map<String, String> doctorMap = new HashMap<>();
                    doctorMap.put("doctorFlow", (String) mapInfo.get("doctorFlow"));
                    List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByMap(doctorMap);
                    Integer outOfficeSumByOnePersion = getMonthOutOfficeSum(monthDate, arrResultList);//1或者0
                    Integer outOfficeActualSumByOnePersion = getMonthActualOutOfficeSum(cksh, monthDate, arrResultList);//1或者0
                    outOfficeSum = outOfficeSum + outOfficeSumByOnePersion;
                    outOfficeActualSum = outOfficeActualSum + outOfficeActualSumByOnePersion;
                    if (1 == outOfficeActualSumByOnePersion) {
                        doctorFlowLIst.add((String) mapInfo.get("doctorFlow"));
                    }
                }
                outOfficeDoctorMap.put("doctorFlowList", doctorFlowLIst);
                List<Map<String, Object>> FinishSumAndTotalSumList = new ArrayList<>();
                if (doctorFlowLIst.size() > 0) {
                    FinishSumAndTotalSumList = monthlyReportExtMapper2.findOutOfficeFinishSumAndTotalSum(outOfficeDoctorMap);
                }
                Integer[] finishAndtotal = getFinishSumAndTotalSum(FinishSumAndTotalSumList);
                Integer finishSum = finishAndtotal[0];
                Integer totalSum = finishAndtotal[1];

                doctorOutOfficeParamPO.setMonthOutOfficeSum(outOfficeSum);//本月应出科人数
                doctorOutOfficeParamPO.setMonthActualOutOfficeSum(outOfficeActualSum);//本月实际出科人数
                Integer notOutOfficeSum = outOfficeSum - outOfficeActualSum;
                doctorOutOfficeParamPO.setMonthNotOutOfficeSum(notOutOfficeSum);//本月未出科人数

                String outOfficeExceptionRate = "0%";
                if (0 != outOfficeSum) {
                    outOfficeExceptionRate = numberFormat.format((float) notOutOfficeSum / (float) outOfficeSum * 100) + "%";
                }
                doctorOutOfficeParamPO.setOutOfficeExceptionRate(outOfficeExceptionRate);//出科异常比例
                String outOfficeFinishRate = "0%";
                if (0 != totalSum) {
                    outOfficeFinishRate = numberFormat.format((float) finishSum / (float) totalSum * 100) + "%";
                }
                doctorOutOfficeParamPO.setOutOfficeDataFinishRate(outOfficeFinishRate);//出科学员数据平均完成率

                        doctorOutOfficeParamPO.setIsContain("N");
                        doctorOutOfficeParamPO.setMonthDate(monthDate);
                        doctorOutOfficeParamPO.setRecordFlow(UUID.randomUUID().toString());
                        doctorOutOfficeParamPO.setCreateTime(DateUtil.getCurrDateTime());
                        doctorOutOfficeParamPO.setRoleFlag(roleFlag);
                        doctorOutOfficeParamPO.setUniversity(dict.getDictFlow());
                        if("both".equals(dataTYpe)){
                           /* doctorOutOfficeParamPO.setNotGraduate("Y");
                            doctorOutOfficeParamPO.setGraduate("Y");*/
                        }else {
                            if ("notGraduate".equals(dataTYpe)) {
                                /*doctorOutOfficeParamPO.setNotGraduate("Y");
                                doctorOutOfficeParamPO.setGraduate("N");*/
                            } else {
                                /*doctorOutOfficeParamPO.setNotGraduate("N");*/
                                doctorOutOfficeParamPO.setGraduate("Y");
                            }
                        }
                    }
                if(doctorOutOfficeParamPOUniversitylist.size()>0){
                    schdualTaskMapper.insertPCUDoctorOutOffice(doctorOutOfficeParamPOUniversitylist);
                }
              }
            }
        }
    }
}
    //获取本月一个人实际出科人数
    public Integer getMonthActualOutOfficeSum(String cksh,String monthDate,List<SchArrangeResult> arrResultList ){
        Integer count=0;
        for(SchArrangeResult schArrangeResult:arrResultList){
            String schEndDateT=schArrangeResult.getSchEndDate();
            String resultFlow = schArrangeResult.getResultFlow();
            if(StringUtil.isNotBlank(schEndDateT)){
                String schEndDate= schEndDateT.substring(0,7);
                if(monthDate.equals(schEndDate)){
                    ResDoctorSchProcess doctorSchProcess = resDoctorProcessBiz.searchByResultFlow(resultFlow);
                    if(doctorSchProcess!=null){
                        String processFlow = doctorSchProcess.getProcessFlow();
                        List<ResSchProcessExpress> resRecs = expressBiz.searchByProcessFlowClob(processFlow);
                        if(resRecs!=null&&resRecs.size()>0){
                            for(ResSchProcessExpress r:resRecs){
                                //出科考核表
                                if(AfterRecTypeEnum.AfterEvaluation.getId().equals(r.getRecTypeId())){
                                    //如果科主任审核
                                    if(cksh.equals("Y")){
                                        //科主任通过
                                        if(RecStatusEnum.HeadAuditY.getId().equals(r.getHeadAuditStatusId())){
                                            count++;
                                        }
                                    }else{
                                        //带教老师通过
                                        if(RecStatusEnum.TeacherAuditY.getId().equals(r.getAuditStatusId())){
                                            count++;
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }


        }
        return count;
    }
    //获取本月一个人应出科人数
    public Integer getMonthOutOfficeSum(String monthDate,List<SchArrangeResult> arrResultList ){
        Integer count=0;
        for(SchArrangeResult schArrangeResult:arrResultList){
            String schEndDateT=schArrangeResult.getSchEndDate();
            if(StringUtil.isNotBlank(schEndDateT)){
                String schEndDate= schEndDateT.substring(0,7);
                if(monthDate.equals(schEndDate)){
                    count++;
                }
            }
        }
        return count;
    }
    //获取本月该实际出科人数的培训数据完成数、要求数
    public Integer[] getFinishSumAndTotalSum(List<Map<String, Object>> FinishSumAndTotalSumList){
        Integer[] array=new Integer[2];
        Integer finishSum=0,totalSum=0;
        for(Map<String,Object> map:FinishSumAndTotalSumList){
            String reqNumStr="0";String completeNumStr="0";
            if(!"".equals(map.get("reqNum"))&& null!=map.get("reqNum") ){
                reqNumStr = (String)map.get("reqNum");
            }
            if(!"".equals(map.get("completeNum"))&& null!=map.get("completeNum") ){
                completeNumStr = (String)map.get("completeNum");
            }
            Integer reqNum = Integer.parseInt(reqNumStr);
            Integer completeNum= Integer.parseInt(completeNumStr);
            finishSum=finishSum+completeNum;
            totalSum=totalSum+reqNum;
        }
        array[0]=finishSum;array[1]=totalSum;
        return array;
    }

    public List<DoctorOutOfficeParamPO> resetOrg(List<DoctorOutOfficeParamPO> list){
        List<DoctorOutOfficeParamPO> poList=new ArrayList<>();
        List<DoctorOutOfficeParamPO> first=new ArrayList<>();
        List<DoctorOutOfficeParamPO> second=new ArrayList<>();
        for(DoctorOutOfficeParamPO aa:list){
            DoctorOutOfficeParamPO s1=new DoctorOutOfficeParamPO();
//                   List<SysOrg>  jointOrgList= orgBiz.searchJointOrgsByOrg(aa.getOrgFlow());
            List<ResJointOrg> joinorgInfolist=  resJointOrgBiz.searchResJointByJointOrgFlow(aa.getOrgFlow());
            if(joinorgInfolist.size()==0){
                s1.setParentOrgFlow("");
                s1.setOrgFlow(aa.getOrgFlow());
                s1.setOrgName(aa.getOrgName());
                s1.setNo(aa.getOrgCode());
                s1.setDoctorFlowsInOrg(aa.getDoctorFlowsInOrg());
                poList.add(s1);
            }else{
                for(ResJointOrg bb:joinorgInfolist){
                    DoctorOutOfficeParamPO s2=new DoctorOutOfficeParamPO();
                    if(isexist(bb.getOrgFlow(),list)){
                        s2.setOrgFlow(bb.getJointOrgFlow());
                        s2.setOrgName(bb.getJointOrgName());
                        s2.setParentOrgFlow(bb.getOrgFlow());
                        s2.setDoctorFlowsInOrg(aa.getDoctorFlowsInOrg());
                    }else{
                        s2.setOrgFlow(bb.getJointOrgFlow());
                        s2.setOrgName(bb.getJointOrgName());
                        s2.setParentOrgFlow("");
                        s2.setDoctorFlowsInOrg(aa.getDoctorFlowsInOrg());
                    }
                    poList.add(s2);
                }
            }
        }
        if(poList.size()>0){
            for(DoctorOutOfficeParamPO li:poList){
                if("".equals(li.getParentOrgFlow())){
                    first.add(li);
                }
                if(!"".equals(li.getParentOrgFlow())){
                    second.add(li);
                }
            }
            for(int i=0 ;i< first.size();i++){
                /*first.get(i).setNo((i+1)+"");*/
                String orgflow =first.get(i).getOrgFlow();
                int count=0;
                for(int j=0;j<second.size();j++){
                    if(second.get(j).getParentOrgFlow().equals(orgflow)){
                        second.get(j).setNo(/*(i+1)*/first.get(i).getNo()+"-"+(count+1));
                        count++;
                    }
                }
            }
            first.addAll(second);
        }
        return first;
    }
    public boolean isexist(String orgflow,List<DoctorOutOfficeParamPO> list){
        for(DoctorOutOfficeParamPO doc:list){
            if(orgflow.equals(doc.getOrgFlow())){
                return true;
            }
        }
        return  false;
    }
    /*获取该高校在该基地的学生*/
    public List<Map<String,Object>> getUniversityInOrgStudentInfo(DoctorOutOfficeParamPO doctorOutOfficeParamPO,List<Map<String,Object>> outOfficeDoctorInfoList){
        List<String> li=doctorOutOfficeParamPO.getDoctorFlowsInOrg();
        List<Map<String,Object>> mapList=new ArrayList<>();
        if(outOfficeDoctorInfoList.size()>0){
            for(Map<String,Object> map:outOfficeDoctorInfoList){
                String doctorFlow= (String) map.get("doctorFlow");
                if(li.contains(doctorFlow)){
                    mapList.add(map);
                }
            }
        }
        return mapList;
    }



    /**
     *  @author: yuh
     *  @Date: 2020/4/23 9:05
     *  @Description: 省市学员轮转查询
     */
//    @Scheduled(cron="0 0 4 1 * ?")//每月1号凌晨5点50分执行一次
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void shengshiDoctorLUNZHUAN_FIND(){
        logger.info("---------start ShengshiGaoxiao LUNZHUAN_FIND  doing-------");

        String monthDate = InitConfig.getSysCfg("res_static_job_month");
        if(StringUtil.isBlank(monthDate)){
            monthDate = getLastMonths(-1); //获取上月  -9
        }

        schdualTaskMapper.deletePreviousCurrMonthDoctorLunzhuanFind(monthDate);

        String[] a = new String[2];
        a[0] = "";
        a[1] = "isContain";
        List<String> roleList = new ArrayList<>();
        roleList.add("global");
        roleList.add("charge");
        roleList.add("university");
        List<String> datas =new ArrayList<>();
        datas.add("both");
        datas.add("notGraduate");
        datas.add("graduate");
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
for(int m=0;m<roleList.size();m++) {
    if(roleList.get(m).equals(GlobalConstant.USER_LIST_UNIVERSITY)){
             gaoxiaoDoctorLUNZhuanFind(monthDate,a,roleList.get(m),datas);  // 2018-09
    }else{

      for(int count=0;count<a.length;count++){

         for(String dataTYpe:datas) {
            /* List<SysOrg> orgs = new ArrayList<>();*/
             List<SysOrg> orgsPrivoncCity = new ArrayList<>();

                String paramList[]=new String[4];
                if ("both".equals(dataTYpe)) {
                    paramList[0] = "Company";
                    paramList[1] = "CompanyEntrust";
                    paramList[2] = "Social";
                    paramList[3] = "Graduate";
                } else {
                    if ("notGraduate".equals(dataTYpe)) {
                        paramList[0] = "Company";
                        paramList[1] = "CompanyEntrust";
                        paramList[2] = "Social";
                    } else {
                        paramList[0] = "Graduate";
                    }
                }
                SysOrg searchOrg = new SysOrg();
                 searchOrg.setOrgProvId("320000"); //江苏省
                 searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                 searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
                 searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);

                List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
                //包含协同
                if ("isContain".equals(a[count])) {
                    List<SysOrg> orgs = new ArrayList<>();
                    Map<String, Object> map = new HashMap<>();
                    for (int i = 0; i < exitOrgs.size(); i++) {
                        List<String> allOrgFlow = new ArrayList<>();
                        List<SysOrg> resJointOrgList = orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
                        for (int j = 0; j < resJointOrgList.size(); j++) {
                            allOrgFlow.add(resJointOrgList.get(j).getOrgFlow());
                        }
                        allOrgFlow.add(exitOrgs.get(i).getOrgFlow());
                        exitOrgs.get(i).setParentOrgFlow("");
                        map.put("allOrgFlow", allOrgFlow);
                        map.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                        map.put("doctorTypeIdList", paramList);
                        List<SysOrg> list = resStatisticBiz.getRotationData(map);
                        if (list.size() > 0) {
                            Integer trainDoctorTotal = 0;
                            Integer fillNum = 0;
                            Integer auditNum = 0;
                            for (SysOrg s : list) {
                                trainDoctorTotal = trainDoctorTotal + s.getTrainDoctorTotal();
                                fillNum = fillNum + s.getFillNum();
                                auditNum = auditNum + s.getAuditNum();
                            }
                            exitOrgs.get(i).setTrainDoctorTotal(trainDoctorTotal);
                            exitOrgs.get(i).setFillNum(fillNum);
                            exitOrgs.get(i).setAuditNum(auditNum);
                            String auditRate = "0%";
                            String avgFillNum = "0";
                            if (null != fillNum && !"".equals(fillNum)) {
                                if (fillNum == 0) {
                                    auditRate = "0%";
                                } else {
                                    auditRate = numberFormat.format((float) auditNum / (float) fillNum * 100) + "%";
                                }
                            }
                            if (null != trainDoctorTotal && !"".equals(trainDoctorTotal)) {
                                if (trainDoctorTotal == 0) {
                                    avgFillNum = "0";
                                } else {
                                    avgFillNum = numberFormat.format((float) fillNum / (float) trainDoctorTotal);
                                }

                            }
                            exitOrgs.get(i).setAuditRate(auditRate);
                            exitOrgs.get(i).setAvgfillNum(avgFillNum);

                            exitOrgs.get(i).setIsContain("Y");
                            exitOrgs.get(i).setMonthDate(monthDate);
                            exitOrgs.get(i).setRecordFlow(UUID.randomUUID().toString());
                            exitOrgs.get(i).setCreateTime(DateUtil.getCurrDateTime());
                            exitOrgs.get(i).setRoleFlag(roleList.get(m));
                                exitOrgs.get(i).setProvince(exitOrgs.get(i).getOrgProvId());
                                exitOrgs.get(i).setCity(exitOrgs.get(i).getOrgCityId());
                            if("both".equals(dataTYpe)){
                                exitOrgs.get(i).setNotGraduate("Y");
                                exitOrgs.get(i).setGraduate("Y");
                            }else {
                                if ("notGraduate".equals(dataTYpe)) {
                                    exitOrgs.get(i).setNotGraduate("Y");
                                    exitOrgs.get(i).setGraduate("N");
                                } else {
                                    exitOrgs.get(i).setNotGraduate("N");
                                    exitOrgs.get(i).setGraduate("Y");
                                }
                            }
                        }else{
                            exitOrgs.get(i).setTrainDoctorTotal(0);
                            exitOrgs.get(i).setFillNum(0);
                            exitOrgs.get(i).setAuditNum(0);
                            exitOrgs.get(i).setAuditRate("0%");
                            exitOrgs.get(i).setAvgfillNum("0");

                            exitOrgs.get(i).setIsContain("Y");
                            exitOrgs.get(i).setMonthDate(monthDate);
                            exitOrgs.get(i).setRecordFlow(UUID.randomUUID().toString());
                            exitOrgs.get(i).setCreateTime(DateUtil.getCurrDateTime());
                            exitOrgs.get(i).setRoleFlag(roleList.get(m));
                            exitOrgs.get(i).setProvince(exitOrgs.get(i).getOrgProvId());
                            exitOrgs.get(i).setCity(exitOrgs.get(i).getOrgCityId());
                            if("both".equals(dataTYpe)){
                                exitOrgs.get(i).setNotGraduate("Y");
                                exitOrgs.get(i).setGraduate("Y");
                            }else {
                                if ("notGraduate".equals(dataTYpe)) {
                                    exitOrgs.get(i).setNotGraduate("Y");
                                    exitOrgs.get(i).setGraduate("N");
                                } else {
                                    exitOrgs.get(i).setNotGraduate("N");
                                    exitOrgs.get(i).setGraduate("Y");
                                }
                            }
                        }
                        List<SysOrg> resJointOrgListT = orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
                        if (resJointOrgListT != null && !resJointOrgListT.isEmpty()) {
                            Map<String, Object> secondMap = new HashMap<>();
                            secondMap.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                            secondMap.put("doctorTypeIdList", paramList);
                            for (int j = 0; j < resJointOrgListT.size(); j++) {
                                resJointOrgListT.get(j).setParentOrgFlow(exitOrgs.get(i).getOrgFlow());
                                resJointOrgListT.get(j).setOrgCode(exitOrgs.get(i).getOrgCode() + "-" + (j + 1));
                                secondMap.put("orgFlow", resJointOrgListT.get(j).getOrgFlow());
                                List<SysOrg> list2 = resStatisticBiz.getRotationData(secondMap);
                                if (list2.size() > 0) {
                                    SysOrg sysOrgT2 = list2.get(0);
                                    resJointOrgListT.get(j).setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
                                    resJointOrgListT.get(j).setFillNum(sysOrgT2.getFillNum());
                                    resJointOrgListT.get(j).setAuditNum(sysOrgT2.getAuditNum());
                                    String auditRate2 = "0%";
                                    String avgFillNum = "0";
                                    if (null != sysOrgT2.getFillNum() && !"".equals(sysOrgT2.getFillNum())) {
                                        if (sysOrgT2.getFillNum() == 0) {
                                            auditRate2 = "0%";
                                        } else {
                                            auditRate2 = numberFormat.format((float) sysOrgT2.getAuditNum() / (float) sysOrgT2.getFillNum() * 100) + "%";
                                        }
                                    }
                                    if (null != sysOrgT2.getTrainDoctorTotal() && !"".equals(sysOrgT2.getTrainDoctorTotal())) {
                                        if (sysOrgT2.getTrainDoctorTotal() == 0) {
                                            avgFillNum = "0";
                                        } else {
                                            avgFillNum = numberFormat.format((float) sysOrgT2.getFillNum() / (float) sysOrgT2.getTrainDoctorTotal());
                                        }
                                    }
                                    resJointOrgListT.get(j).setAuditRate(auditRate2);
                                    resJointOrgListT.get(j).setAvgfillNum(avgFillNum);

                                    resJointOrgListT.get(j).setIsContain("Y");
                                    resJointOrgListT.get(j).setMonthDate(monthDate);
                                    resJointOrgListT.get(j).setRecordFlow(UUID.randomUUID().toString());
                                    resJointOrgListT.get(j).setCreateTime(DateUtil.getCurrDateTime());
                                    resJointOrgListT.get(j).setRoleFlag(roleList.get(m));

                                    resJointOrgListT.get(j).setProvince(exitOrgs.get(i).getOrgProvId());
                                    resJointOrgListT.get(j).setCity(exitOrgs.get(i).getOrgCityId());
                                    if("both".equals(dataTYpe)){
                                        resJointOrgListT.get(j).setNotGraduate("Y");
                                        resJointOrgListT.get(j).setGraduate("Y");
                                    }else {
                                        if ("notGraduate".equals(dataTYpe)) {
                                            resJointOrgListT.get(j).setNotGraduate("Y");
                                            resJointOrgListT.get(j).setGraduate("N");
                                        } else {
                                            resJointOrgListT.get(j).setNotGraduate("N");
                                            resJointOrgListT.get(j).setGraduate("Y");
                                        }
                                    }
                                }else{
                                    resJointOrgListT.get(j).setTrainDoctorTotal(0);
                                    resJointOrgListT.get(j).setFillNum(0);
                                    resJointOrgListT.get(j).setAuditNum(0);
                                    resJointOrgListT.get(j).setAuditRate("0%");
                                    resJointOrgListT.get(j).setAvgfillNum("0");

                                    resJointOrgListT.get(j).setIsContain("Y");
                                    resJointOrgListT.get(j).setMonthDate(monthDate);
                                    resJointOrgListT.get(j).setRecordFlow(UUID.randomUUID().toString());
                                    resJointOrgListT.get(j).setCreateTime(DateUtil.getCurrDateTime());
                                    resJointOrgListT.get(j).setRoleFlag(roleList.get(m));
                                    resJointOrgListT.get(j).setProvince(exitOrgs.get(i).getOrgProvId());
                                    resJointOrgListT.get(j).setCity(exitOrgs.get(i).getOrgCityId());
                                    if("both".equals(dataTYpe)){
                                        resJointOrgListT.get(j).setNotGraduate("Y");
                                        resJointOrgListT.get(j).setGraduate("Y");
                                    }else {
                                        if ("notGraduate".equals(dataTYpe)) {
                                            resJointOrgListT.get(j).setNotGraduate("Y");
                                            resJointOrgListT.get(j).setGraduate("N");
                                        } else {
                                            resJointOrgListT.get(j).setNotGraduate("N");
                                            resJointOrgListT.get(j).setGraduate("Y");
                                        }
                                    }
                                }
                                orgs.add(resJointOrgListT.get(j));
                            }
                        }
                        orgs.add(exitOrgs.get(i));
                    }
                    if(orgs.size()>0){
                        int kkk=schdualTaskMapper.insertPCUDoctorLunzhuanFind(orgs);
                        logger.info("---------ShengshiGaoxiao LUNZHUAN_FIND(iscontain)insert__"+kkk+"-------");
                    }
         //不包含协同
            } else {
                    List<SysOrg> orgs = new ArrayList<>();
                    Map<String, Object> map = new HashMap<>();
                    for (int i = 0; i < exitOrgs.size(); i++) {
                        exitOrgs.get(i).setParentOrgFlow("");
                        String countryOrgFlow = exitOrgs.get(i).getOrgFlow();
                        map.put("orgFlow", countryOrgFlow);
                        map.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                        map.put("doctorTypeIdList", paramList);
                        List<SysOrg> list = resStatisticBiz.getRotationData(map);
                        if (list.size() > 0) {
                            SysOrg sysOrgT = list.get(0);
                            exitOrgs.get(i).setTrainDoctorTotal(sysOrgT.getTrainDoctorTotal());
                            exitOrgs.get(i).setFillNum(sysOrgT.getFillNum());
                            exitOrgs.get(i).setAuditNum(sysOrgT.getAuditNum());
                            String auditRate = "0%";
                            String avgFillNum = "0";
                            if (null != sysOrgT.getFillNum() && !"".equals(sysOrgT.getFillNum())) {
                                if (sysOrgT.getFillNum() == 0) {
                                    auditRate = "0%";
                                } else {
                                    auditRate = numberFormat.format((float) sysOrgT.getAuditNum() / (float) sysOrgT.getFillNum() * 100) + "%";
                                }
                            }
                            if (null != sysOrgT.getTrainDoctorTotal() && !"".equals(sysOrgT.getTrainDoctorTotal())) {
                                if (sysOrgT.getTrainDoctorTotal() == 0) {
                                    avgFillNum = "0";
                                } else {
                                    avgFillNum = numberFormat.format((float) sysOrgT.getFillNum() / (float) sysOrgT.getTrainDoctorTotal());
                                }
                            }
                            exitOrgs.get(i).setAuditRate(auditRate);
                            exitOrgs.get(i).setAvgfillNum(avgFillNum);

                            exitOrgs.get(i).setIsContain("N");
                            exitOrgs.get(i).setMonthDate(monthDate);
                            exitOrgs.get(i).setRecordFlow(UUID.randomUUID().toString());
                            exitOrgs.get(i).setCreateTime(DateUtil.getCurrDateTime());
                            exitOrgs.get(i).setRoleFlag(roleList.get(m));
                            exitOrgs.get(i).setProvince(exitOrgs.get(i).getOrgProvId());
                            exitOrgs.get(i).setCity(exitOrgs.get(i).getOrgCityId());
                            if("both".equals(dataTYpe)){
                                exitOrgs.get(i).setNotGraduate("Y");
                                exitOrgs.get(i).setGraduate("Y");
                            }else {
                                if ("notGraduate".equals(dataTYpe)) {
                                    exitOrgs.get(i).setNotGraduate("Y");
                                    exitOrgs.get(i).setGraduate("N");
                                } else {
                                    exitOrgs.get(i).setNotGraduate("N");
                                    exitOrgs.get(i).setGraduate("Y");
                                }
                            }
                        }else {
                            exitOrgs.get(i).setTrainDoctorTotal(0);
                            exitOrgs.get(i).setFillNum(0);
                            exitOrgs.get(i).setAuditNum(0);
                            exitOrgs.get(i).setAuditRate("0%");
                            exitOrgs.get(i).setAvgfillNum("0");

                            exitOrgs.get(i).setIsContain("N");
                            exitOrgs.get(i).setMonthDate(monthDate);
                            exitOrgs.get(i).setRecordFlow(UUID.randomUUID().toString());
                            exitOrgs.get(i).setCreateTime(DateUtil.getCurrDateTime());
                            exitOrgs.get(i).setRoleFlag(roleList.get(m));
                            exitOrgs.get(i).setProvince(exitOrgs.get(i).getOrgProvId());
                            exitOrgs.get(i).setCity(exitOrgs.get(i).getOrgCityId());
                            if("both".equals(dataTYpe)){
                                exitOrgs.get(i).setNotGraduate("Y");
                                exitOrgs.get(i).setGraduate("Y");
                            }else {
                                if ("notGraduate".equals(dataTYpe)) {
                                    exitOrgs.get(i).setNotGraduate("Y");
                                    exitOrgs.get(i).setGraduate("N");
                                } else {
                                    exitOrgs.get(i).setNotGraduate("N");
                                    exitOrgs.get(i).setGraduate("Y");
                                }
                            }
                        }
                        List<SysOrg> resJointOrgList = orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
                        Map<String, Object> secondMap = new HashMap<>();
                        if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                            for (int j = 0; j < resJointOrgList.size(); j++) {
                                resJointOrgList.get(j).setParentOrgFlow(exitOrgs.get(i).getOrgFlow());
                                resJointOrgList.get(j).setOrgCode(exitOrgs.get(i).getOrgCode() + "-" + (j + 1));
                                secondMap.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                secondMap.put("doctorTypeIdList", paramList);
                                secondMap.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                                List<SysOrg> list2 = resStatisticBiz.getRotationData(secondMap);
                                if (list2.size() > 0) {
                                    SysOrg sysOrgT2 = list2.get(0);
                                    resJointOrgList.get(j).setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
                                    resJointOrgList.get(j).setFillNum(sysOrgT2.getFillNum());
                                    resJointOrgList.get(j).setAuditNum(sysOrgT2.getAuditNum());
                                    String auditRate2 = "0%";
                                    String avgFillNum = "0";
                                    if (null != sysOrgT2.getFillNum() && !"".equals(sysOrgT2.getFillNum())) {
                                        if (sysOrgT2.getFillNum() == 0) {
                                            auditRate2 = "0%";
                                        } else {
                                            auditRate2 = numberFormat.format((float) sysOrgT2.getAuditNum() / (float) sysOrgT2.getFillNum() * 100) + "%";
                                        }
                                    }
                                    if (null != sysOrgT2.getTrainDoctorTotal() && !"".equals(sysOrgT2.getTrainDoctorTotal())) {
                                        if (sysOrgT2.getTrainDoctorTotal() == 0) {
                                            avgFillNum = "0";
                                        } else {
                                            avgFillNum = numberFormat.format((float) sysOrgT2.getFillNum() / (float) sysOrgT2.getTrainDoctorTotal());
                                        }
                                    }
                                    resJointOrgList.get(j).setAuditRate(auditRate2);
                                    resJointOrgList.get(j).setAvgfillNum(avgFillNum);

                                    resJointOrgList.get(j).setIsContain("N");
                                    resJointOrgList.get(j).setMonthDate(monthDate);
                                    resJointOrgList.get(j).setRecordFlow(UUID.randomUUID().toString());
                                    resJointOrgList.get(j).setCreateTime(DateUtil.getCurrDateTime());
                                    resJointOrgList.get(j).setRoleFlag(roleList.get(m));
                                    resJointOrgList.get(j).setProvince(exitOrgs.get(i).getOrgProvId());
                                    resJointOrgList.get(j).setCity(exitOrgs.get(i).getOrgCityId());
                                    if("both".equals(dataTYpe)){
                                        resJointOrgList.get(j).setNotGraduate("Y");
                                        resJointOrgList.get(j).setGraduate("Y");
                                    }else {
                                        if ("notGraduate".equals(dataTYpe)) {
                                            resJointOrgList.get(j).setNotGraduate("Y");
                                            resJointOrgList.get(j).setGraduate("N");
                                        } else {
                                            resJointOrgList.get(j).setNotGraduate("N");
                                            resJointOrgList.get(j).setGraduate("Y");
                                        }
                                    }
                                }else{
                                    resJointOrgList.get(j).setTrainDoctorTotal(0);
                                    resJointOrgList.get(j).setFillNum(0);
                                    resJointOrgList.get(j).setAuditNum(0);
                                    resJointOrgList.get(j).setAuditRate("0%");
                                    resJointOrgList.get(j).setAvgfillNum("0");

                                    resJointOrgList.get(j).setIsContain("N");
                                    resJointOrgList.get(j).setMonthDate(monthDate);
                                    resJointOrgList.get(j).setRecordFlow(UUID.randomUUID().toString());
                                    resJointOrgList.get(j).setCreateTime(DateUtil.getCurrDateTime());
                                    resJointOrgList.get(j).setRoleFlag(roleList.get(m));
                                    resJointOrgList.get(j).setProvince(exitOrgs.get(i).getOrgProvId());
                                    resJointOrgList.get(j).setCity(exitOrgs.get(i).getOrgCityId());
                                    if("both".equals(dataTYpe)){
                                        resJointOrgList.get(j).setNotGraduate("Y");
                                        resJointOrgList.get(j).setGraduate("Y");
                                    }else {
                                        if ("notGraduate".equals(dataTYpe)) {
                                            resJointOrgList.get(j).setNotGraduate("Y");
                                            resJointOrgList.get(j).setGraduate("N");
                                        } else {
                                            resJointOrgList.get(j).setNotGraduate("N");
                                            resJointOrgList.get(j).setGraduate("Y");
                                        }
                                    }
                                }
                                orgs.add(resJointOrgList.get(j));
                            }
                        }
                        orgs.add(exitOrgs.get(i));
                    }
                    if(orgs.size()>0){
                      int kkk=  schdualTaskMapper.insertPCUDoctorLunzhuanFind(orgs);
                        logger.info("---------ShengshiGaoxiao LUNZHUAN_FIND(notcontain)insert__"+kkk+"-------");
                    }
                }
                /*orgs = listSort(orderBy, orgs);
                return orgs;*/
            }
        }
    }

}
        logger.info("---------End ShengshiGaoxiao LUNZHUAN_FIND  doing-------");
}

/**
 *  @author: yuh
 *  @Date: 2020/4/23 9:04
 *  @Description: 高校学员轮转查询
 */
    public void gaoxiaoDoctorLUNZhuanFind(String monthDate, String[] a, String roleFlag, List<String> datas){
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位

        List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
for (SysDict dict : sendSchools) {

    for(int count=0; count<a.length ;count++){
        for(String dataTYpe:datas) {

            List<SysOrg> rotationUniversitylist = new ArrayList<>();
            List<SysOrg> rotationUniversityFirstlist = new ArrayList<>();

            String paramList[]=new String[4];
            if ("both".equals(dataTYpe)) {
                paramList[0] = "Company";
                paramList[1] = "CompanyEntrust";
                paramList[2] = "Social";
                paramList[3] = "Graduate";
            } else {
                if ("notGraduate".equals(dataTYpe)) {
                    paramList[0] = "Company";
                    paramList[1] = "CompanyEntrust";
                    paramList[2] = "Social";
                } else {
                    paramList[0] = "Graduate";
                }
            }

            /*SysOrg searchOrg = new SysOrg();*/

            Map<String, Object> map1 = new HashMap<>();
            List<SysOrg> orgUniversity = new ArrayList<>();
            List<SysOrg> distinctOrgUniversity = new ArrayList<>();
            map1.put("universityName", dict.getDictName());
            List<JsResDoctorRecruitExt> resDoctorRecruitExtsList = monthlyReportExtMapper2.getUserInfoAndOrgNameByUniversity(map1);
            for (JsResDoctorRecruitExt jRR : resDoctorRecruitExtsList) {
                SysOrg s1 = new SysOrg();
                List<String> doctorlistS = new ArrayList<>();
                String orgFlow = jRR.getOrgFlow();
                String orgName = jRR.getOrgName();
                s1.setOrgFlow(orgFlow);
                s1.setOrgName(orgName);
                s1.setOrgCode(jRR.getOrgCode());
                for (JsResDoctorRecruitExt jRR2 : resDoctorRecruitExtsList) {
                    if (orgFlow.equals(jRR2.getOrgFlow())) {
                        doctorlistS.add(jRR2.getDoctorFlow());
                    }
                }
                s1.setDoctorFlowsInOrg(doctorlistS);
                orgUniversity.add(s1);
            }
            Set<SysOrg> personSet = new TreeSet<>((o1, o2) -> o1.getOrgFlow().compareTo(o2.getOrgFlow()));
            personSet.addAll(orgUniversity);
            distinctOrgUniversity = new ArrayList<>(personSet);
            rotationUniversitylist = resetOrgAppUserInfo(distinctOrgUniversity);
            for (SysOrg syso : rotationUniversitylist) {
                if ("".equals(syso.getParentOrgFlow())) {
                    rotationUniversityFirstlist.add(syso);
                }
            }
            //包含协同
            if ("isContain".equals(a[count])) {
                List<SysOrg> orgs=new ArrayList<>();
                Map<String, Object> map = new HashMap<>();
                map.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                map.put("doctorTypeIdList", paramList);
                for (int i = 0; i < rotationUniversityFirstlist.size(); i++) {
                    List<String> allOrgFlow = new ArrayList<>();
                    List<String> allUniversityDoctorsFlow = new ArrayList<>();
                    for (SysOrg second : rotationUniversitylist) {
                        if (second.getParentOrgFlow().equals(rotationUniversityFirstlist.get(i).getOrgFlow())) {
                            allOrgFlow.add(second.getOrgFlow());
                            allUniversityDoctorsFlow.addAll(second.getDoctorFlowsInOrg());
                        }
                    }
                    allOrgFlow.add(rotationUniversityFirstlist.get(i).getOrgFlow());
                    allUniversityDoctorsFlow.addAll(rotationUniversityFirstlist.get(i).getDoctorFlowsInOrg());
                    map.put("allOrgFlow", allOrgFlow);
                    map.put("universityDoctorFlows", allUniversityDoctorsFlow);
                    /*map.put("doctorTypeIdList",paramList);*/
                    List<SysOrg> list = resStatisticBiz.getRotationData(map);
                    if (list.size() > 0) {
                        Integer trainDoctorTotal = 0;
                        Integer fillNum = 0;
                        Integer auditNum = 0;
                        for (SysOrg s : list) {
                            trainDoctorTotal = trainDoctorTotal + s.getTrainDoctorTotal();
                            fillNum = fillNum + s.getFillNum();
                            auditNum = auditNum + s.getAuditNum();
                        }
                        rotationUniversityFirstlist.get(i).setTrainDoctorTotal(trainDoctorTotal);
                        rotationUniversityFirstlist.get(i).setFillNum(fillNum);
                        rotationUniversityFirstlist.get(i).setAuditNum(auditNum);
                        String auditRate = "0%";
                        String avgFillNum = "0";
                        if (null != fillNum && !"".equals(fillNum)) {
                            if (fillNum == 0) {
                                auditRate = "0%";
                            } else {
                                auditRate = numberFormat.format((float) auditNum / (float) fillNum * 100) + "%";
                            }
                        }
                        if (null != trainDoctorTotal && !"".equals(trainDoctorTotal)) {
                            if (trainDoctorTotal == 0) {
                                avgFillNum = "0";
                            } else {
                                avgFillNum = numberFormat.format((float) fillNum / (float) trainDoctorTotal);
                            }
                        }
                        rotationUniversityFirstlist.get(i).setAuditRate(auditRate);
                        rotationUniversityFirstlist.get(i).setAvgfillNum(avgFillNum);

                        rotationUniversityFirstlist.get(i).setIsContain("Y");
                        rotationUniversityFirstlist.get(i).setMonthDate(monthDate);
                        rotationUniversityFirstlist.get(i).setRecordFlow(UUID.randomUUID().toString());
                        rotationUniversityFirstlist.get(i).setCreateTime(DateUtil.getCurrDateTime());
                        rotationUniversityFirstlist.get(i).setRoleFlag(roleFlag);
                        rotationUniversityFirstlist.get(i).setUniversity(dict.getDictFlow());
                        if("both".equals(dataTYpe)){
                            rotationUniversityFirstlist.get(i).setNotGraduate("Y");
                            rotationUniversityFirstlist.get(i).setGraduate("Y");
                        }else {
                            if ("notGraduate".equals(dataTYpe)) {
                        rotationUniversityFirstlist.get(i).setNotGraduate("Y");
                        rotationUniversityFirstlist.get(i).setGraduate("N");
                            } else {
                                rotationUniversityFirstlist.get(i).setNotGraduate("N");
                                rotationUniversityFirstlist.get(i).setGraduate("Y");
                            }
                        }

                    }else{
                        rotationUniversityFirstlist.get(i).setTrainDoctorTotal(0);
                        rotationUniversityFirstlist.get(i).setFillNum(0);
                        rotationUniversityFirstlist.get(i).setAuditNum(0);
                        rotationUniversityFirstlist.get(i).setAuditRate("0%");
                        rotationUniversityFirstlist.get(i).setAvgfillNum("0");

                        rotationUniversityFirstlist.get(i).setIsContain("Y");
                        rotationUniversityFirstlist.get(i).setMonthDate(monthDate);
                        rotationUniversityFirstlist.get(i).setRecordFlow(UUID.randomUUID().toString());
                        rotationUniversityFirstlist.get(i).setCreateTime(DateUtil.getCurrDateTime());
                        rotationUniversityFirstlist.get(i).setRoleFlag(roleFlag);
                        rotationUniversityFirstlist.get(i).setUniversity(dict.getDictFlow());
                        if("both".equals(dataTYpe)){
                            rotationUniversityFirstlist.get(i).setNotGraduate("Y");
                            rotationUniversityFirstlist.get(i).setGraduate("Y");
                        }else {
                            if ("notGraduate".equals(dataTYpe)) {
                                rotationUniversityFirstlist.get(i).setNotGraduate("Y");
                                rotationUniversityFirstlist.get(i).setGraduate("N");
                            } else {
                                rotationUniversityFirstlist.get(i).setNotGraduate("N");
                                rotationUniversityFirstlist.get(i).setGraduate("Y");
                            }
                        }
                    }
                    for (SysOrg second : rotationUniversitylist) {
                        if (second.getParentOrgFlow().equals(rotationUniversityFirstlist.get(i).getOrgFlow())) {
                            Map<String, Object> secondMap = new HashMap<>();
                            secondMap.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                            secondMap.put("orgFlow", second.getOrgFlow());
                            secondMap.put("universityDoctorFlows", second.getDoctorFlowsInOrg());
                            secondMap.put("doctorTypeIdList", paramList);
                            List<SysOrg> list2 = resStatisticBiz.getRotationData(secondMap);
                            if (list2.size() > 0) {
                                SysOrg sysOrgT2 = list2.get(0);
                                second.setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
                                second.setAuditNum(sysOrgT2.getAuditNum());
                                second.setFillNum(sysOrgT2.getFillNum());
                                String auditRate = "0%";
                                String avgFillNum = "0";
                                if (null != second.getFillNum() && !"".equals(second.getFillNum())) {
                                    if (second.getFillNum() == 0) {
                                        auditRate = "0%";
                                    } else {
                                        auditRate = numberFormat.format((float) second.getAuditNum() / (float) second.getFillNum() * 100) + "%";
                                    }
                                }
                                if (null != second.getTrainDoctorTotal() && !"".equals(second.getTrainDoctorTotal())) {
                                    if (second.getTrainDoctorTotal() == 0) {
                                        avgFillNum = "0";
                                    } else {
                                        avgFillNum = numberFormat.format((float) second.getFillNum() / (float) second.getTrainDoctorTotal());
                                    }
                                }
                                second.setAuditRate(auditRate);
                                second.setAvgfillNum(avgFillNum);

                                second.setIsContain("Y");
                                second.setMonthDate(monthDate);
                                second.setRecordFlow(UUID.randomUUID().toString());
                                second.setCreateTime(DateUtil.getCurrDateTime());
                                second.setRoleFlag(roleFlag);
                                second.setUniversity(dict.getDictFlow());
                                if("both".equals(dataTYpe)){
                                    second.setNotGraduate("Y");
                                    second.setGraduate("Y");
                                }else {
                                    if ("notGraduate".equals(dataTYpe)) {
                                        second.setNotGraduate("Y");
                                        second.setGraduate("N");
                                    } else {
                                        second.setNotGraduate("N");
                                        second.setGraduate("Y");
                                    }
                                }
                            }else{
                                second.setTrainDoctorTotal(0);
                                second.setAuditNum(0);
                                second.setFillNum(0);
                                second.setAuditRate("0%");
                                second.setAvgfillNum("0");

                                second.setIsContain("Y");
                                second.setMonthDate(monthDate);
                                second.setRecordFlow(UUID.randomUUID().toString());
                                second.setCreateTime(DateUtil.getCurrDateTime());
                                second.setRoleFlag(roleFlag);
                                second.setUniversity(dict.getDictFlow());
                                if("both".equals(dataTYpe)){
                                    second.setNotGraduate("Y");
                                    second.setGraduate("Y");
                                }else {
                                    if ("notGraduate".equals(dataTYpe)) {
                                        second.setNotGraduate("Y");
                                        second.setGraduate("N");
                                    } else {
                                        second.setNotGraduate("N");
                                        second.setGraduate("Y");
                                    }
                                }
                            }
                            orgs.add(second);
                        }
                    }
                    orgs.add(rotationUniversityFirstlist.get(i));
                }
                if(orgs.size()>0){
                  int  kkk= schdualTaskMapper.insertPCUDoctorLunzhuanFind(orgs);
                    logger.info("---------ShengshiGaoxiao LUNZHUAN_FIND(gaoxiao iscontain)insert__"+kkk+"-------");
                }
       //不包含协同
        } else {
                List<SysOrg> orgs=new ArrayList<>();
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                paramMap.put("doctorTypeIdList", paramList);
                for (int i = 0; i < rotationUniversityFirstlist.size(); i++) {
                    String countryOrgFlow = rotationUniversityFirstlist.get(i).getOrgFlow();
                    paramMap.put("orgFlow", countryOrgFlow);
                    paramMap.put("universityDoctorFlows", rotationUniversityFirstlist.get(i).getDoctorFlowsInOrg());
                    List<SysOrg> list = resStatisticBiz.getRotationData(paramMap);
                    if (list.size() > 0) {
                        SysOrg sysOrgT = list.get(0);
                        rotationUniversityFirstlist.get(i).setTrainDoctorTotal(sysOrgT.getTrainDoctorTotal());
                        rotationUniversityFirstlist.get(i).setFillNum(sysOrgT.getFillNum());
                        rotationUniversityFirstlist.get(i).setAuditNum(sysOrgT.getAuditNum());
                        String auditRate = "0%";
                        String avgFillNum = "0";
                        if (null != sysOrgT.getFillNum() && !"".equals(sysOrgT.getFillNum())) {
                            if (sysOrgT.getFillNum() == 0) {
                                auditRate = "0%";
                            } else {
                                auditRate = numberFormat.format((float) sysOrgT.getAuditNum() / (float) sysOrgT.getFillNum() * 100) + "%";
                            }
                        }
                        if (null != sysOrgT.getTrainDoctorTotal() && !"".equals(sysOrgT.getTrainDoctorTotal())) {
                            if (sysOrgT.getTrainDoctorTotal() == 0) {
                                avgFillNum = "0";
                            } else {
                                avgFillNum = numberFormat.format((float) sysOrgT.getFillNum() / (float) sysOrgT.getTrainDoctorTotal());
                            }
                        }
                        rotationUniversityFirstlist.get(i).setAuditRate(auditRate);
                        rotationUniversityFirstlist.get(i).setAvgfillNum(avgFillNum);

                        rotationUniversityFirstlist.get(i).setIsContain("N");
                        rotationUniversityFirstlist.get(i).setMonthDate(monthDate);
                        rotationUniversityFirstlist.get(i).setRecordFlow(UUID.randomUUID().toString());
                        rotationUniversityFirstlist.get(i).setCreateTime(DateUtil.getCurrDateTime());
                        rotationUniversityFirstlist.get(i).setRoleFlag(roleFlag);
                        rotationUniversityFirstlist.get(i).setUniversity(dict.getDictFlow());
                        if("both".equals(dataTYpe)){
                            rotationUniversityFirstlist.get(i).setNotGraduate("Y");
                            rotationUniversityFirstlist.get(i).setGraduate("Y");
                        }else {
                            if ("notGraduate".equals(dataTYpe)) {
                                rotationUniversityFirstlist.get(i).setNotGraduate("Y");
                                rotationUniversityFirstlist.get(i).setGraduate("N");
                            } else {
                                rotationUniversityFirstlist.get(i).setNotGraduate("N");
                                rotationUniversityFirstlist.get(i).setGraduate("Y");
                            }
                        }
                    }else{
                        rotationUniversityFirstlist.get(i).setTrainDoctorTotal(0);
                        rotationUniversityFirstlist.get(i).setFillNum(0);
                        rotationUniversityFirstlist.get(i).setAuditNum(0);
                        rotationUniversityFirstlist.get(i).setAuditRate("0%");
                        rotationUniversityFirstlist.get(i).setAvgfillNum("0");

                        rotationUniversityFirstlist.get(i).setIsContain("N");
                        rotationUniversityFirstlist.get(i).setMonthDate(monthDate);
                        rotationUniversityFirstlist.get(i).setRecordFlow(UUID.randomUUID().toString());
                        rotationUniversityFirstlist.get(i).setCreateTime(DateUtil.getCurrDateTime());
                        rotationUniversityFirstlist.get(i).setRoleFlag(roleFlag);
                        rotationUniversityFirstlist.get(i).setUniversity(dict.getDictFlow());
                        if("both".equals(dataTYpe)){
                            rotationUniversityFirstlist.get(i).setNotGraduate("Y");
                            rotationUniversityFirstlist.get(i).setGraduate("Y");
                        }else {
                            if ("notGraduate".equals(dataTYpe)) {
                                rotationUniversityFirstlist.get(i).setNotGraduate("Y");
                                rotationUniversityFirstlist.get(i).setGraduate("N");
                            } else {
                                rotationUniversityFirstlist.get(i).setNotGraduate("N");
                                rotationUniversityFirstlist.get(i).setGraduate("Y");
                            }
                        }
                    }

                    for (SysOrg second : rotationUniversitylist) {
                        if (second.getParentOrgFlow().equals(rotationUniversityFirstlist.get(i).getOrgFlow())) {
                            Map<String, Object> paramMap2 = new HashMap<>();
                            paramMap2.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                            paramMap2.put("doctorTypeIdList", paramList);
                            paramMap2.put("orgFlow", second.getOrgFlow());
                            paramMap2.put("universityDoctorFlows", second.getDoctorFlowsInOrg());
                            List<SysOrg> list2 = resStatisticBiz.getRotationData(paramMap2);
                            if (list2.size() > 0) {
                                SysOrg sysOrgT2 = list2.get(0);
                                second.setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
                                second.setFillNum(sysOrgT2.getFillNum());
                                second.setAuditNum(sysOrgT2.getAuditNum());
                                String auditRate = "0%";
                                String avgFillNum = "0";
                                if (null != second.getFillNum() && !"".equals(second.getFillNum())) {
                                    if (second.getFillNum() == 0) {
                                        auditRate = "0%";
                                    } else {
                                        auditRate = numberFormat.format((float) second.getAuditNum() / (float) second.getFillNum() * 100) + "%";
                                    }
                                }
                                if (null != second.getTrainDoctorTotal() && !"".equals(second.getTrainDoctorTotal())) {
                                    if (second.getTrainDoctorTotal() == 0) {
                                        avgFillNum = "0";
                                    } else {
                                        avgFillNum = numberFormat.format((float) second.getFillNum() / (float) second.getTrainDoctorTotal());
                                    }
                                }
                                second.setAuditRate(auditRate);
                                second.setAvgfillNum(avgFillNum);

                                second.setIsContain("N");
                                second.setMonthDate(monthDate);
                                second.setRecordFlow(UUID.randomUUID().toString());
                                second.setCreateTime(DateUtil.getCurrDateTime());
                                second.setRoleFlag(roleFlag);
                                second.setUniversity(dict.getDictFlow());
                                if("both".equals(dataTYpe)){
                                    second.setNotGraduate("Y");
                                    second.setGraduate("Y");
                                }else {
                                    if ("notGraduate".equals(dataTYpe)) {
                                        second.setNotGraduate("Y");
                                        second.setGraduate("N");
                                    } else {
                                        second.setNotGraduate("N");
                                        second.setGraduate("Y");
                                    }
                                }
                            }else{
                                second.setTrainDoctorTotal(0);
                                second.setFillNum(0);
                                second.setAuditNum(0);
                                second.setAuditRate("0%");
                                second.setAvgfillNum("0");

                                second.setIsContain("N");
                                second.setMonthDate(monthDate);
                                second.setRecordFlow(UUID.randomUUID().toString());
                                second.setCreateTime(DateUtil.getCurrDateTime());
                                second.setRoleFlag(roleFlag);
                                second.setUniversity(dict.getDictFlow());
                                if("both".equals(dataTYpe)){
                                    second.setNotGraduate("Y");
                                    second.setGraduate("Y");
                                }else {
                                    if ("notGraduate".equals(dataTYpe)) {
                                        second.setNotGraduate("Y");
                                        second.setGraduate("N");
                                    } else {
                                        second.setNotGraduate("N");
                                        second.setGraduate("Y");
                                    }
                                }
                            }
                            orgs.add(second);
                        }
                    }
                    orgs.add(rotationUniversityFirstlist.get(i));
                }
                if(orgs.size()>0){
                   int kkk= schdualTaskMapper.insertPCUDoctorLunzhuanFind(orgs);
                    logger.info("---------ShengshiGaoxiao LUNZHUAN_FIND(gaoxiao Notcontain)insert__"+kkk+"-------");
                }
            }
        }
    }
              /*  orgs= jsResManageController.listSort(orderBy,orgs);*/
           /* return orgs;*/
}
}



    /* 配置文件cron @Scheduled(cron = "#{configProperties['ppp']}")
    public void update() throws Exception {
        logger.info("[ppp]");
    }*/


    //@Scheduled(cron = "0 */2 * * * ?")//测试2分钟执行一次
   /* @Scheduled(cron="0 50 15 30 * ?")//每月1号凌晨4点10分执行一次
    @Transactional(rollbackFor = Exception.class)*/
    public  void shengshiRenYuanQK(){
        String monthDate = getLastMonths(-1); //获取上月  -9
        int kkk= schdualTaskMapper.deletePreviousMonthPCUData(monthDate); //先删除上月的数据，
        logger.info("--------ShengshiGaoxiaoApp--delete-------"+kkk+"----------------------");
        /*SysUser user = GlobalContext.getCurrentUser();*/
        /* List<SysOrg> orgs = new ArrayList<>();*/
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位
        String[] docTypeListDoc ={"Company", "CompanyEntrust", "Social"};
        String[] docTypeListMast ={"Graduate"};
        String[] a = new String[2];
        a[0] = "";
        a[1] = "isContain";
        List<String> roleList = new ArrayList<>();
        roleList.add("global");
        roleList.add("charge");
        roleList.add("university");
        for(int m=0;m<roleList.size();m++) {

            SysOrg searchOrg = new SysOrg();
                         /* searchOrg.setOrgProvId(currOrg.getOrgProvId());
                          if (roleList.get(m).equals(GlobalConstant.USER_LIST_CHARGE)) {
                              searchOrg.setOrgCityId(currOrg.getOrgCityId());
                          }*/
            searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
            searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            searchOrg.setOrgProvId("320000");  //江苏省
            //高校角色
            if (GlobalConstant.USER_LIST_UNIVERSITY.equals(roleList.get(m))) {
                /* List li = resMonthlyReportGlobalControllerClass.appUserInfo_University(monthDate, isContain, orgFlow, orgs, role);*/
                /* return li;*/
            } else {

                for (int count = 0; count < a.length; count++) {

                    //包含协同
                    if ("isContain".equals(a[count])) {
                        List<SysOrg> orgs = new ArrayList<>();

                        Map<String, Object> map = new HashMap<>();
                        Map<String, Object> map1 = new HashMap<>();
                        map.put("monthDate", monthDate);
                        List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
                        for (int i = 0; i < exitOrgs.size(); i++) {
                            List<String> allOrgFlow = new ArrayList<>();
                            List<SysOrg> resJointOrgList = orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
                            for (int j = 0; j < resJointOrgList.size(); j++) {
                                allOrgFlow.add(resJointOrgList.get(j).getOrgFlow());
                            }
                            allOrgFlow.add(exitOrgs.get(i).getOrgFlow());
                            exitOrgs.get(i).setParentOrgFlow("");
                            exitOrgs.get(i).setNo(/*(i+1)+""*/exitOrgs.get(i).getOrgCode());
                            map.put("allOrgFlow", allOrgFlow);
                            map.put("docTypeList", docTypeListDoc);
                            map1.put("allOrgFlow", allOrgFlow);
                            map1.put("docTypeList", docTypeListMast);
                            List<JsResDoctorRecruitExt> docList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(map);
                            List<JsResDoctorRecruitExt> mastlist = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(map1);
                            int allTrain = docList.size() + mastlist.size();//所有在培人数

                            Map<String, Object> paramMap3 = new HashMap<>();
                            paramMap3.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                            paramMap3.put("myDoctorTypeId", docTypeListDoc);
                            paramMap3.put("allOrgFlow", allOrgFlow);
                            Map<String, Object> paramMap4 = new HashMap<>();
                            paramMap4.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                            paramMap4.put("myDoctorTypeId", docTypeListMast);
                            paramMap4.put("allOrgFlow", allOrgFlow);
                            List<String> docAppuse = monthlyReportExtMapper2.getAppUserSum(paramMap3);//
                            List<String> masteAppuse = monthlyReportExtMapper2.getAppUserSum(paramMap4);
                            int allAppuse = docAppuse.size() + masteAppuse.size();  //所有app使用人数
                            //if(list.size()>0){
                            Integer trainDoctorTotal = 0;
                            Integer doctorSum = 0;
                            Integer masterSum = 0;
                            trainDoctorTotal = allTrain;
                            doctorSum = docList.size();
                            masterSum = mastlist.size();

                            exitOrgs.get(i).setTrainDoctorTotal(trainDoctorTotal);
                            exitOrgs.get(i).setDoctorSum(doctorSum);
                            exitOrgs.get(i).setMasterSum(masterSum);
                            String doctorRate = "";
                            String masterRate = "";

                            if (0 == doctorSum) {
                                doctorRate = "0%";
                            }
                            if (0 != doctorSum) {
                                doctorRate = numberFormat.format((float) docAppuse.size() / (float) doctorSum * 100) + "%";
                            }
                            if (0 == masterSum) {
                                masterRate = "0%";
                            }
                            if (0 != masterSum) {
                                masterRate = numberFormat.format((float) masteAppuse.size() / (float) masterSum * 100) + "%";
                            }
                            exitOrgs.get(i).setDoctorRate(doctorRate);
                            exitOrgs.get(i).setMasterRate(masterRate);
                            Integer trainPersonTotal = monthlyReportExtMapper2.getTrainTotal(map);//该基地在培总人数
                            String rate = "";
                            if (0 == trainPersonTotal) {
                                rate = "0%";
                            }
                            if (0 != trainPersonTotal) {
                                rate = numberFormat.format((float) allAppuse / (float) trainPersonTotal * 100) + "%";
                            }
                            exitOrgs.get(i).setRate(rate);

                            exitOrgs.get(i).setIsContain("Y");
                            exitOrgs.get(i).setMonthDate(monthDate);
                            exitOrgs.get(i).setRecordFlow(UUID.randomUUID().toString());
                            exitOrgs.get(i).setRoleFlag(roleList.get(m));
                            exitOrgs.get(i).setProvince(exitOrgs.get(i).getOrgProvId());
                            exitOrgs.get(i).setCity(exitOrgs.get(i).getOrgCityId());

                            List<SysOrg> resJointOrgListT = orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
                            if (resJointOrgListT != null && !resJointOrgListT.isEmpty()) {
                                Map<String, Object> secondMap = new HashMap<>();
                                secondMap.put("monthDate", monthDate);
                                for (int j = 0; j < resJointOrgListT.size(); j++) {
                                    resJointOrgListT.get(j).setParentOrgFlow(exitOrgs.get(i).getOrgFlow());
                                    resJointOrgListT.get(j).setNo(/*(i+1)+"-"+(j+1)*/exitOrgs.get(i).getOrgCode() + "-" + (j + 1));
                                    secondMap.put("orgFlow", resJointOrgListT.get(j).getOrgFlow());

                                    Map<String, Object> paramMapXieTong = new HashMap<>();
                                    Map<String, Object> paramMap2XieTong = new HashMap<>();
                                    paramMapXieTong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                    paramMapXieTong.put("docTypeList", docTypeListDoc);
                                    paramMap2XieTong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                    paramMap2XieTong.put("docTypeList", docTypeListMast);

                                    List<SysOrg> list2 = new ArrayList<>();
                                    SysOrg ms = new SysOrg();
                                    List<JsResDoctorRecruitExt> docListXietong = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMapXieTong);
                                    List<JsResDoctorRecruitExt> mastlistXietong = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2XieTong);
                                    Map<String, Object> paramMap3Xietong = new HashMap<>();
                                    paramMap3Xietong.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                                    paramMap3Xietong.put("myDoctorTypeId", docTypeListDoc);
                                    paramMap3Xietong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                    Map<String, Object> paramMap4Xietong = new HashMap<>();
                                    paramMap4Xietong.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                                    paramMap4Xietong.put("myDoctorTypeId", docTypeListMast);
                                    paramMap4Xietong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                    List<String> docAppuseXietong = monthlyReportExtMapper2.getAppUserSum(paramMap3Xietong);//
                                    List<String> masteAppuseXietong = monthlyReportExtMapper2.getAppUserSum(paramMap4Xietong);
                                    int allAppuseXietong = docAppuseXietong.size() + masteAppuseXietong.size();  //所有app使用人数
                                    ms.setTrainDoctorTotal(docListXietong.size() + mastlistXietong.size());
                                    ms.setDoctorSum(docListXietong.size());
                                    ms.setMasterSum(mastlistXietong.size());
                                    list2.add(ms);

                                    if (list2.size() > 0) {
                                        SysOrg sysOrgT2 = list2.get(0);
                                        resJointOrgListT.get(j).setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
                                        resJointOrgListT.get(j).setDoctorSum(sysOrgT2.getDoctorSum());
                                        resJointOrgListT.get(j).setMasterSum(sysOrgT2.getMasterSum());
                                        String doctorRate2 = "";
                                        String masterRate2 = "";
                                        if (0 == sysOrgT2.getDoctorSum()) {
                                            doctorRate2 = "0%";
                                        }
                                        if (0 != sysOrgT2.getDoctorSum()) {
                                            doctorRate2 = numberFormat.format((float) docAppuseXietong.size() / (float) sysOrgT2.getDoctorSum() * 100) + "%";
                                        }
                                        if (0 == sysOrgT2.getMasterSum()) {
                                            masterRate2 = "0%";
                                        }
                                        if (0 != sysOrgT2.getMasterSum()) {
                                            masterRate2 = numberFormat.format((float) masteAppuseXietong.size() / (float) sysOrgT2.getMasterSum() * 100) + "%";
                                        }
                                        resJointOrgListT.get(j).setDoctorRate(doctorRate2);
                                        resJointOrgListT.get(j).setMasterRate(masterRate2);
                                        Integer trainPersonTotal2 = monthlyReportExtMapper2.getTrainTotal(secondMap);//该基地在培总人数
                                        String rate2 = "";
                                        if (0 == trainPersonTotal2) {
                                            rate2 = "0%";
                                        }
                                        if (0 != trainPersonTotal2) {
                                            rate2 = numberFormat.format((float) allAppuseXietong / (float) trainPersonTotal2 * 100) + "%";
                                        }
                                        resJointOrgListT.get(j).setRate(rate2);

                                        resJointOrgListT.get(j).setIsContain("Y");
                                        resJointOrgListT.get(j).setMonthDate(monthDate);
                                        resJointOrgListT.get(j).setRecordFlow(UUID.randomUUID().toString());
                                        resJointOrgListT.get(j).setRoleFlag(roleList.get(m));
                                        resJointOrgListT.get(j).setProvince(exitOrgs.get(i).getOrgProvId());
                                        resJointOrgListT.get(j).setCity(exitOrgs.get(i).getOrgCityId());
                                    }

                                    orgs.add(resJointOrgListT.get(j));
                                }
                            }
                            orgs.add(exitOrgs.get(i));
                        }
                        if(orgs.size()>0){
                            int kk=  schdualTaskMapper.insertPCUApp(orgs);
                            logger.info("--------ShengshiApp-insert-(isContain)-------"+kk+"----------------------");
                        }
                        //不包含协同
                    } else {
                        List<SysOrg> orgs = new ArrayList<>();
                        List<PersonStaticExample> staticData = new ArrayList<>();
                        Map<String, Object> paramMap = new HashMap<>();
                        Map<String, Object> paramMap2 = new HashMap<>();
                        paramMap.put("monthDate", monthDate);
                        List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
                        for (int i = 0; i < exitOrgs.size(); i++) {
                            PersonStaticExample staticExample = new PersonStaticExample();
                            BeanUtils.copyProperties(exitOrgs.get(i), staticExample);
                            staticData.add(staticExample);
                        }
                        for (int i = 0; i < staticData.size(); i++) {
                            staticData.get(i).setParentOrgFlow("");
                            staticData.get(i).setNo(/*(i+1)+""*/exitOrgs.get(i).getOrgCode());
                            String countryOrgFlow = staticData.get(i).getOrgFlow();
                            paramMap.put("orgFlow", countryOrgFlow);
                            paramMap.put("docTypeList", docTypeListDoc);
                            paramMap2.put("orgFlow", countryOrgFlow);
                            paramMap2.put("docTypeList", docTypeListMast);
                            /*List<SysOrg> list =monthlyReportExtMapper2.getAppDatayuh(paramMap);*/
                            List<SysOrg> list = new ArrayList<>();
                            SysOrg sss = new SysOrg();
                            List<JsResDoctorRecruitExt> docList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap);
                            List<JsResDoctorRecruitExt> mastlist = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2);
                            Map<String, Object> paramMap3 = new HashMap<>();
                            paramMap3.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                            paramMap3.put("myDoctorTypeId", docTypeListDoc);
                            paramMap3.put("orgFlow", countryOrgFlow);
                            Map<String, Object> paramMap4 = new HashMap<>();
                            paramMap4.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                            paramMap4.put("myDoctorTypeId", docTypeListMast);
                            paramMap4.put("orgFlow", countryOrgFlow);
                            List<String> docAppuse = monthlyReportExtMapper2.getAppUserSum(paramMap3);//
                            List<String> masteAppuse = monthlyReportExtMapper2.getAppUserSum(paramMap4);
                            sss.setTrainDoctorTotal(docList.size() + mastlist.size());
                            sss.setDoctorSum(docList.size());
                            sss.setMasterSum(mastlist.size());
                            list.add(sss);

                            if (list.size() > 0) {
                                SysOrg sysOrgT = list.get(0);
                                exitOrgs.get(i).setTrainDoctorTotal(sysOrgT.getTrainDoctorTotal());
                                exitOrgs.get(i).setDoctorSum(sysOrgT.getDoctorSum());
                                exitOrgs.get(i).setMasterSum(sysOrgT.getMasterSum());

                                Integer trainPersonTotal = monthlyReportExtMapper2.getTrainTotal(paramMap);//该基地在培总人数

                                exitOrgs.get(i).setIsContain("N");
                                exitOrgs.get(i).setMonthDate(monthDate);
                                exitOrgs.get(i).setRecordFlow(UUID.randomUUID().toString());
                                exitOrgs.get(i).setRoleFlag(roleList.get(m));
                                exitOrgs.get(i).setProvince(exitOrgs.get(i).getOrgProvId());
                                exitOrgs.get(i).setCity(exitOrgs.get(i).getOrgCityId());
                            }

                            List<SysOrg> resJointOrgList = orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
                            if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                                for (int j = 0; j < resJointOrgList.size(); j++) {
                                    resJointOrgList.get(j).setParentOrgFlow(exitOrgs.get(i).getOrgFlow());
                                    resJointOrgList.get(j).setNo(/*(i+1)+"-"+(j+1)*/exitOrgs.get(i).getOrgCode() + "-" + (j + 1));
                                    paramMap.put("orgFlow", resJointOrgList.get(j).getOrgFlow());

                                    Map<String, Object> paramMapXieTong = new HashMap<>();
                                    Map<String, Object> paramMap2XieTong = new HashMap<>();
                                    paramMapXieTong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                    paramMapXieTong.put("docTypeList", docTypeListDoc);
                                    paramMap2XieTong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                    paramMap2XieTong.put("docTypeList", docTypeListMast);

                                    List<SysOrg> list2 = new ArrayList<>();
                                    SysOrg ms = new SysOrg();
                                    List<JsResDoctorRecruitExt> docListXietong = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMapXieTong);
                                    List<JsResDoctorRecruitExt> mastlistXietong = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2XieTong);
                                    Map<String, Object> paramMap3Xietong = new HashMap<>();
                                    paramMap3Xietong.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                                    paramMap3Xietong.put("myDoctorTypeId", docTypeListDoc);
                                    paramMap3Xietong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                    Map<String, Object> paramMap4Xietong = new HashMap<>();
                                    paramMap4Xietong.put("monthDate", monthDate.split("-")[0] + monthDate.split("-")[1]);
                                    paramMap4Xietong.put("myDoctorTypeId", docTypeListMast);
                                    paramMap4Xietong.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
                                    List<String> docAppuseXietong = monthlyReportExtMapper2.getAppUserSum(paramMap3Xietong);//
                                    List<String> masteAppuseXietong = monthlyReportExtMapper2.getAppUserSum(paramMap4Xietong);
                                    ms.setTrainDoctorTotal(docListXietong.size() + mastlistXietong.size());
                                    ms.setDoctorSum(docListXietong.size());
                                    ms.setMasterSum(mastlistXietong.size());
                                    list2.add(ms);
                                    /*List<SysOrg> list2 =monthlyReportExtMapper2.getAppDatayuh(paramMap);*/
                                    if (list2.size() > 0) {
                                        SysOrg sysOrgT2 = list2.get(0);
                                        resJointOrgList.get(j).setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
                                        resJointOrgList.get(j).setDoctorSum(sysOrgT2.getDoctorSum());
                                        resJointOrgList.get(j).setMasterSum(sysOrgT2.getMasterSum());

                                        resJointOrgList.get(j).setIsContain("N");
                                        resJointOrgList.get(j).setMonthDate(monthDate);
                                        resJointOrgList.get(j).setRecordFlow(UUID.randomUUID().toString());
                                        resJointOrgList.get(j).setRoleFlag(roleList.get(m));
                                        resJointOrgList.get(j).setProvince(exitOrgs.get(i).getOrgProvId());
                                        resJointOrgList.get(j).setCity(exitOrgs.get(i).getOrgCityId());
                                    }

                                    orgs.add(resJointOrgList.get(j));
                                }
                            }
                            orgs.add(exitOrgs.get(i));
                        }
                        if(orgs.size()>0){
                            int kk= schdualTaskMapper.insertPCUApp(orgs);
                            logger.info("--------ShengshiApp-insert(notContain)--------"+kk+"----------------------");
                        }

                    }
                }
            }
            //return orgs;
        }

    }






}
