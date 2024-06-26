package com.pinde.sci.ctrl.res;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.jsres.IJsResStatisticBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.res.IResMonthlyReportBiz;
import com.pinde.sci.biz.res.IResSchProcessExpressBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.ctrl.jsres.JsResManageController;
import com.pinde.sci.dao.jsres.MonthlyReportExtMapper;
import com.pinde.sci.dao.res.ResMonthlyReportExtMapper;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

/**
 * University Role 类
 */
@Controller
public class ResMonthlyReportGlobalControllerClass extends GeneralController {
    @Autowired
    private IResMonthlyReportBiz monthlyReportBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private ResMonthlyReportExtMapper monthlyReportExtMapper;
    @Autowired
    private IResJointOrgBiz resJointOrgBiz;
    @Autowired
    private MonthlyReportExtMapper monthlyReportExtMapper2;
    @Autowired
    private ISchArrangeResultBiz schArrangeResultBiz;
    @Autowired
    private IJsResPowerCfgBiz jsResPowerCfgBiz;
    @Autowired
    private IResDoctorProcessBiz resDoctorProcessBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private ResMonthlyReportGlobalController resMonthlyReportGlobalController;
    @Autowired
    private IJsResStatisticBiz resStatisticBiz;
    @Autowired
    private JsResManageController jsResManageController;

    /**
     * 学院出科
     * @return
     */
    public   List  doctorOutOfficeInfo_University(String sortFlag,String monthDate,String isContain,List<SysOrg> orgs,List<DoctorOutOfficeParamPO> doctorOutOfficeParamPOList,String[] docTypeList,String role){
        SysUser user = GlobalContext.getCurrentUser();
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位

        List<DoctorOutOfficeParamPO> doctorOutOfficeParamPOUniversitylist=new ArrayList<>();
        try {
            SysOrg searchOrg = new SysOrg();
            SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
            String currOrgName = currOrg.getOrgName();

            Boolean gaoxiaoFlg = false;
            List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
            if (sendSchools != null && sendSchools.size() > 0) {
                for (SysDict dict : sendSchools) {
                    if (currOrgName.equals(dict.getDictName())) {
                        gaoxiaoFlg = true;
                    }
                }
            }
            //如果是SendSchool高校
            if (gaoxiaoFlg) {
                Map<String, Object> map = new HashMap<>();
                List<DoctorOutOfficeParamPO> orgUniversity = new ArrayList<>();
                List<DoctorOutOfficeParamPO> distinctOrgUniversity = new ArrayList<>();
                map.put("universityName", currOrgName);
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

            /*searchOrg.setOrgProvId(currOrg.getOrgProvId());
            if(role.equals(GlobalConstant.USER_LIST_CHARGE)){
                searchOrg.setOrgCityId(currOrg.getOrgCityId());
            }

            searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
            searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            //---start--
            List<SysOrg> countryOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
            for(int i =0;i<countryOrgs.size();i++){
                countryOrgs.get(i).setParentOrgFlow("");
                countryOrgs.get(i).setNo((i+1)+"");
                List<SysOrg>  jointOrgList= orgBiz.searchJointOrgsByOrg(countryOrgs.get(i).getOrgFlow());
                if(jointOrgList!=null&&!jointOrgList.isEmpty()){
                    for(int j=0;j<jointOrgList.size();j++){
                        jointOrgList.get(j).setParentOrgFlow(countryOrgs.get(i).getOrgFlow());
                        jointOrgList.get(j).setNo((i+1)+"-"+(j+1));
                        orgs.add(jointOrgList.get(j));
                    }
                }
                orgs.add(countryOrgs.get(i));
            }
            //对象转化
            for(int i=0;i<orgs.size();i++){
                DoctorOutOfficeParamPO dooppo=new DoctorOutOfficeParamPO();
                BeanUtils.copyProperties(orgs.get(i),dooppo);
                doctorOutOfficeParamPOList.add(dooppo);
            }*/
//包含协同
                if ("isContain".equals(isContain)) {
                    for (DoctorOutOfficeParamPO doctorOutOfficeParamPO : doctorOutOfficeParamPOUniversitylist) {
                        Integer outOfficeSum = 0;
                        Integer outOfficeActualSum = 0;
                        List<String> doctorFlowLIst = new ArrayList<>(); //存取每月实际出科人的userflow
                        Map<String, Object> outOfficeDoctorMap = new HashMap<>();
                        String orgFlow = doctorOutOfficeParamPO.getOrgFlow();
                        outOfficeDoctorMap.put("orgFlow", orgFlow);
                        outOfficeDoctorMap.put("docTypeList", docTypeList);
                        List<Map<String, Object>> outOfficeDoctorInfoList = monthlyReportExtMapper2.findOutOfficeDoctorInfo(outOfficeDoctorMap);
                        outOfficeDoctorInfoList= getUniversityInOrgStudentInfo(doctorOutOfficeParamPO,outOfficeDoctorInfoList);
                        String cksh = "N";
                        JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_" + orgFlow + "_org_cksh");
                        if (cfg != null) {
                            cksh = cfg.getCfgValue();
                        }
                        for (Map<String, Object> mapInfo : outOfficeDoctorInfoList) {
                            Map<String, String> doctorMap = new HashMap<>();
                            doctorMap.put("doctorFlow", (String) mapInfo.get("doctorFlow"));
                            List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByMap(doctorMap);
                            Integer outOfficeSumByOnePersion = resMonthlyReportGlobalController.getMonthOutOfficeSum(monthDate, arrResultList);//1或者0
                            Integer outOfficeActualSumByOnePersion = resMonthlyReportGlobalController.getMonthActualOutOfficeSum(cksh, monthDate, arrResultList);//1或者0
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
                        Integer[] finishAndtotal = resMonthlyReportGlobalController.getFinishSumAndTotalSum(FinishSumAndTotalSumList);
                        Integer finishSum = finishAndtotal[0];
                        Integer totalSum = finishAndtotal[1];

                        String outOfficeFinishRate = "0%";
                        if (0 != totalSum) {
                            outOfficeFinishRate = numberFormat.format((float) finishSum / (float) totalSum * 100) + "%";
                        }
                        dooppoA.setOutOfficeDataFinishRate(outOfficeFinishRate);//出科学员数据平均完成率（国家+协同）
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
                            Integer outOfficeSumByOnePersion = resMonthlyReportGlobalController.getMonthOutOfficeSum(monthDate, arrResultList);//1或者0
                            Integer outOfficeActualSumByOnePersion = resMonthlyReportGlobalController.getMonthActualOutOfficeSum(cksh, monthDate, arrResultList);//1或者0
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
                        Integer[] finishAndtotal = resMonthlyReportGlobalController.getFinishSumAndTotalSum(FinishSumAndTotalSumList);
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
                    }


                }
                doctorOutOfficeParamPOUniversitylist = resMonthlyReportGlobalController.outOfficeListSort(sortFlag, doctorOutOfficeParamPOUniversitylist);/*排序*/
              }else{
                throw new RuntimeException("请联系管理员“派送学校”是否有“"+currOrgName+"”高校");
            }
               return doctorOutOfficeParamPOUniversitylist;
            }catch(RuntimeException e){
                e.printStackTrace();
                List<Map<String, String>> list1 = new ArrayList<>();
                Map<String, String> map = new HashMap<>();
                if (null == e.getMessage()) {
                    map.put("error", "null");
                } else {
                    map.put("error", e.getMessage());
                }
                list1.add(map);
                return list1;
            }

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
     * 教学活动
     */
    public List TeachActiveInfo_University(String monthDate,String isContain , List<TeachActiveParamPO> orgInfoList,String roleFlag){
        SysUser user = GlobalContext.getCurrentUser();
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位

        List<TeachActiveParamPO> teachActiveParamPOUniversitylist=new ArrayList<>();
        try {
            SysOrg searchOrg = new SysOrg();
            SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
            String currOrgName = currOrg.getOrgName();

            Boolean gaoxiaoFlg = false;
            List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
            if (sendSchools != null && sendSchools.size() > 0) {
                for (SysDict dict : sendSchools) {
                    if (currOrgName.equals(dict.getDictName())) {
                        gaoxiaoFlg = true;
                    }
                }
            }
            if(gaoxiaoFlg){
                Map<String, Object> map = new HashMap<>();
                List<TeachActiveParamPO> orgUniversity = new ArrayList<>();
                List<TeachActiveParamPO> distinctOrgUniversity = new ArrayList<>();
                map.put("universityName", currOrgName);
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


            //包含协同
            if("isContain".equals(isContain)){
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
                            String yearMonthshichang=resMonthlyReportGlobalController.maxminTime(teachingActivityResultList);
                            if("".equals(yearMonthshichang)){
                                throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
                            }
                            String yearmonth=yearMonthshichang.split(",")[0];
                            String shichang=yearMonthshichang.split(",")[1];
                            if(monthDate.equals(yearmonth)){
                                changci++;
                                alljoin=alljoin+teachingActivityResultList.size();
                                shichangTotal=shichangTotal+Double.parseDouble(shichang);
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
                    }
                }
                List<TeachActiveParamPO>  newList1=  resMonthlyReportGlobalController.activitySessionDevideMax( teachActiveParamPOUniversitylist );//求进度条值
                teachActiveParamPOUniversitylist=newList1;
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
                            String yearMonthshichang=resMonthlyReportGlobalController.maxminTime(teachingActivityResultList);
                            if("".equals(yearMonthshichang)){
                                throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
                            }
                            String yearmonth=yearMonthshichang.split(",")[0];
                            String shichang=yearMonthshichang.split(",")[1];
                            if(monthDate.equals(yearmonth)){
                                changci++;
                                alljoin=alljoin+teachingActivityResultList.size();
                                shichangTotal=shichangTotal+Double.parseDouble(shichang);
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
                }
                List<TeachActiveParamPO>  newList= resMonthlyReportGlobalController.activitySessionDevideMax( teachActiveParamPOUniversitylist );//求进度条值
                teachActiveParamPOUniversitylist=newList;
            }
            }else{
                throw new RuntimeException("请联系管理员“派送学校”是否有“"+currOrgName+"”高校");
            }
            return teachActiveParamPOUniversitylist;
        }catch (RuntimeException e){
            e.printStackTrace();
            List<Map<String,String>> list=new ArrayList<>();
            Map<String,String> map=new HashMap<>();
            if(e.getMessage()==null){
                map.put("error","null");
            }else{
                map.put("error",e.getMessage());
            }
            list.add(map);
            return list;
        } catch (ParseException e) {
            e.printStackTrace();
            List<Map<String,String>> list=new ArrayList<>();
            Map<String,String> map=new HashMap<>();
            if(e.getMessage()==null){
                map.put("error","null");
            }else{
                map.put("error",e.getMessage());
            }
            list.add(map);
            return list;
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
     * app使用
     * @return
     */
    public List appUserInfo_University(String monthDate,String isContain ,String sortFlag ,List<SysOrg> orgs ,String role){
        SysUser user = GlobalContext.getCurrentUser();
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位
        String[] docTypeListDoc ={"Company", "CompanyEntrust", "Social"};
        String[] docTypeListMast ={"Graduate"};

        List<SysOrg> appuserInfoUniversitylist=new ArrayList<>();
        List<SysOrg> appuserInfoUniversityFirstlist=new ArrayList<>();

        try {
            SysOrg searchOrg = new SysOrg();
            SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
            String currOrgName = currOrg.getOrgName();

            Boolean gaoxiaoFlg = false;
            List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
            if (sendSchools != null && sendSchools.size() > 0) {
                for (SysDict dict : sendSchools) {
                    if (currOrgName.equals(dict.getDictName())) {
                        gaoxiaoFlg = true;
                    }
                }
            }
            if(gaoxiaoFlg) {
                Map<String, Object> map1 = new HashMap<>();
                List<SysOrg> orgUniversity = new ArrayList<>();
                List<SysOrg> distinctOrgUniversity = new ArrayList<>();
                map1.put("universityName", currOrgName);
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

                for(SysOrg syso:appuserInfoUniversitylist){
                    if("".equals(syso.getParentOrgFlow())){
                        appuserInfoUniversityFirstlist.add(syso);
                    }
                }

            //包含协同
            if("isContain".equals(isContain)){

                Map<String,Object> map=new HashMap<>();
                Map<String,Object> map11=new HashMap<>();
                map.put("monthDate",monthDate);
                /*List<SysOrg> exitOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地*/
                for(int i=0;i<appuserInfoUniversityFirstlist.size();i++){
                    List<String> allOrgFlow = new ArrayList<>();
                    List<String> allUniversityDoctorsFlow = new ArrayList<>();
                    for(SysOrg second:appuserInfoUniversitylist){
                        if(second.getParentOrgFlow().equals(appuserInfoUniversityFirstlist.get(i).getOrgFlow())){
                            allOrgFlow.add(second.getOrgFlow());
                            allUniversityDoctorsFlow.addAll(second.getDoctorFlowsInOrg());
                        }
                    }
                    /*List<SysOrg>  resJointOrgList= orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
                    for (int j=0;j<resJointOrgList.size();j++){
                        allOrgFlow.add(resJointOrgList.get(j).getOrgFlow());
                    }*/
                    allOrgFlow.add(appuserInfoUniversityFirstlist.get(i).getOrgFlow());
                    allUniversityDoctorsFlow.addAll(appuserInfoUniversityFirstlist.get(i).getDoctorFlowsInOrg());
                    /*exitOrgs.get(i).setParentOrgFlow("");
                    exitOrgs.get(i).setNo((i+1)+"");*/
                    map.put("allOrgFlow",allOrgFlow);
                    map.put("docTypeList",docTypeListDoc);
                    map.put("universityDoctorFlows",allUniversityDoctorsFlow);
                    map11.put("allOrgFlow",allOrgFlow);
                    map11.put("docTypeList",docTypeListMast);
                    map11.put("universityDoctorFlows",allUniversityDoctorsFlow);
                   /* List<SysOrg> list =monthlyReportExtMapper2.getAppDatayuh(map);*/
                    List<JsResDoctorRecruitExt> docList=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(map);
                    List<JsResDoctorRecruitExt> mastlist=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(map11);
                    int allTrain=docList.size()+mastlist.size();//所有在培人数

                    Map<String,Object> paramMap3 = new HashMap<>();
                    paramMap3.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                    paramMap3.put("myDoctorTypeId",docTypeListDoc);
                    paramMap3.put("allOrgFlow",allOrgFlow);
                    paramMap3.put("universityDoctorFlows",allUniversityDoctorsFlow);
                    Map<String,Object> paramMap4 = new HashMap<>();
                    paramMap4.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                    paramMap4.put("myDoctorTypeId",docTypeListMast);
                    paramMap4.put("allOrgFlow",allOrgFlow);
                    paramMap4.put("universityDoctorFlows",allUniversityDoctorsFlow);
                    List<String> docAppuse= monthlyReportExtMapper2.getAppUserSum(paramMap3);//
                    List<String> masteAppuse= monthlyReportExtMapper2.getAppUserSum(paramMap4);
                    int allAppuse=docAppuse.size()+masteAppuse.size();  //所有app使用人数
                    //if(list.size()>0){
                        Integer trainDoctorTotal=0;
                        Integer doctorSum=0;
                        Integer masterSum=0;
                    trainDoctorTotal=allTrain;
                    doctorSum=docList.size();
                    masterSum=mastlist.size();
                        /*for(SysOrg s:list){
                            trainDoctorTotal=trainDoctorTotal+s.getTrainDoctorTotal();
                            doctorSum=doctorSum+s.getDoctorSum();
                            masterSum=masterSum+s.getMasterSum();
                        }*/
                        appuserInfoUniversityFirstlist.get(i).setTrainDoctorTotal(trainDoctorTotal);
                        appuserInfoUniversityFirstlist.get(i).setDoctorSum(doctorSum);
                        appuserInfoUniversityFirstlist.get(i).setMasterSum(masterSum);
                        String doctorRate="";
                        String masterRate="";
                    if(0==doctorSum){
                        doctorRate="0%";
                    }
                    if(0!=doctorSum){
                        doctorRate = numberFormat.format((float) docAppuse.size() / (float) doctorSum * 100)+"%";
                    }
                    if(0==masterSum){
                        masterRate="0%";
                    }
                    if(0!=masterSum){
                        masterRate = numberFormat.format((float) masteAppuse.size() / (float) masterSum * 100)+"%";
                    }
                       /* if(null!=trainDoctorTotal&& !"".equals(trainDoctorTotal)){
                            doctorRate = numberFormat.format((float) doctorSum / (float) trainDoctorTotal * 100)+"%";
                            masterRate = numberFormat.format((float) masterSum / (float) trainDoctorTotal * 100)+"%";
                        }*/
                        appuserInfoUniversityFirstlist.get(i).setDoctorRate(doctorRate);
                        appuserInfoUniversityFirstlist.get(i).setMasterRate(masterRate);
                        Integer trainPersonTotal = monthlyReportExtMapper2.getTrainTotal(map);//该基地在培总人数
                        String rate="";
                    if(0==trainPersonTotal){
                        rate="0%";
                    }
                    if(0!=trainPersonTotal){
                        rate=numberFormat.format((float) allAppuse / (float)trainPersonTotal  * 100)+"%";
                    }
                       /* if(null!=trainPersonTotal&& !"".equals(trainPersonTotal)){
                            rate=numberFormat.format((float) trainDoctorTotal / (float)trainPersonTotal  * 100)+"%";
                        }*/
                        appuserInfoUniversityFirstlist.get(i).setRate(rate);
                    //}
                    for(SysOrg second:appuserInfoUniversitylist) {
                        if (second.getParentOrgFlow().equals(appuserInfoUniversityFirstlist.get(i).getOrgFlow())) {
                            Map<String, Object> secondMap = new HashMap<>();
                            secondMap.put("monthDate", monthDate);
                            /*for(int j= 0;j<resJointOrgListT.size();j++){*/
                            /*resJointOrgListT.get(j).setParentOrgFlow(exitOrgs.get(i).getOrgFlow());
                            resJointOrgListT.get(j).setNo((i + 1) + "-" + (j + 1));*/
                            secondMap.put("orgFlow", second.getOrgFlow());
                            secondMap.put("universityDoctorFlows", second.getDoctorFlowsInOrg());

                            Map<String,Object> paramMapXieTong=new HashMap<>();
                            Map<String,Object> paramMap2XieTong=new HashMap<>();
                            paramMapXieTong.put("orgFlow",second.getOrgFlow());
                            paramMapXieTong.put("docTypeList",docTypeListDoc);
                            paramMapXieTong.put("universityDoctorFlows",allUniversityDoctorsFlow);
                            paramMap2XieTong.put("orgFlow",second.getOrgFlow());
                            paramMap2XieTong.put("docTypeList",docTypeListMast);
                            paramMap2XieTong.put("universityDoctorFlows",allUniversityDoctorsFlow);

                            List<SysOrg> list2=new ArrayList<>();
                            SysOrg ms=new SysOrg();
                            List<JsResDoctorRecruitExt> docListXietong=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMapXieTong);
                            List<JsResDoctorRecruitExt> mastlistXietong=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2XieTong);
                            Map<String,Object> paramMap3Xietong = new HashMap<>();
                            paramMap3Xietong.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                            paramMap3Xietong.put("myDoctorTypeId",docTypeListDoc);
                            paramMap3Xietong.put("orgFlow",second.getOrgFlow());
                            paramMap3Xietong.put("universityDoctorFlows",allUniversityDoctorsFlow);
                            Map<String,Object> paramMap4Xietong = new HashMap<>();
                            paramMap4Xietong.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                            paramMap4Xietong.put("myDoctorTypeId",docTypeListMast);
                            paramMap4Xietong.put("orgFlow",second.getOrgFlow());
                            paramMap4Xietong.put("universityDoctorFlows",allUniversityDoctorsFlow);
                            List<String> docAppuseXietong= monthlyReportExtMapper2.getAppUserSum(paramMap3Xietong);//
                            List<String> masteAppuseXietong= monthlyReportExtMapper2.getAppUserSum(paramMap4Xietong);
                            int allAppuseXietong=docAppuseXietong.size()+masteAppuseXietong.size();  //所有app使用人数
                            ms.setTrainDoctorTotal(docListXietong.size()+mastlistXietong.size());
                            ms.setDoctorSum(docListXietong.size());
                            ms.setMasterSum(mastlistXietong.size());
                            list2.add(ms);

                           /* List<SysOrg> list2 = monthlyReportExtMapper2.getAppDatayuh(secondMap);*/
                            if (list2.size() > 0) {
                                SysOrg sysOrgT2 = list2.get(0);
                                second.setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
                                second.setDoctorSum(sysOrgT2.getDoctorSum());
                                second.setMasterSum(sysOrgT2.getMasterSum());
                                String doctorRate2 = "";
                                String masterRate2 = "";
                                if(0==sysOrgT2.getDoctorSum()){
                                    doctorRate2="0%";
                                }
                                if(0!=sysOrgT2.getDoctorSum()){
                                    doctorRate2 = numberFormat.format((float) docAppuseXietong.size() / (float) sysOrgT2.getDoctorSum() * 100)+"%";
                                }
                                if(0==sysOrgT2.getMasterSum()){
                                    masterRate2="0%";
                                }
                                if(0!=sysOrgT2.getMasterSum()){
                                    masterRate2 = numberFormat.format((float) masteAppuseXietong.size() / (float) sysOrgT2.getMasterSum() * 100)+"%";
                                }
                               /* if (null != sysOrgT2.getTrainDoctorTotal() && !"".equals(sysOrgT2.getTrainDoctorTotal())) {
                                    doctorRate2 = numberFormat.format((float) sysOrgT2.getDoctorSum() / (float) sysOrgT2.getTrainDoctorTotal() * 100) + "%";
                                    masterRate2 = numberFormat.format((float) sysOrgT2.getMasterSum() / (float) sysOrgT2.getTrainDoctorTotal() * 100) + "%";
                                }*/
                                second.setDoctorRate(doctorRate2);
                                second.setMasterRate(masterRate2);
                                Integer trainPersonTotal2 = monthlyReportExtMapper2.getTrainTotal(secondMap);//该基地在培总人数
                                String rate2 = "";
                                if(0==trainPersonTotal2){
                                    rate2="0%";
                                }
                                if(0!=trainPersonTotal2){
                                    rate2=numberFormat.format((float)allAppuseXietong / (float)trainPersonTotal2  * 100)+"%";
                                }
                                /*if (null != trainPersonTotal2 && !"".equals(trainPersonTotal2)) {
                                    rate2 = numberFormat.format((float) sysOrgT2.getTrainDoctorTotal() / (float) trainPersonTotal2 * 100) + "%";
                                }*/
                                second.setRate(rate2);
                                // }
                            }
                            orgs.add(second);
                        }
                    }
                    orgs.add(appuserInfoUniversityFirstlist.get(i));
                }

                //不包含协同
            }else{
                Map<String,Object> paramMap = new HashMap<>();
                Map<String,Object> paramMap2 = new HashMap<>();
                paramMap.put("monthDate",monthDate);
               /* List<SysOrg> exitOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地*/
                for(int i=0;i<appuserInfoUniversityFirstlist.size();i++){
                   /* exitOrgs.get(i).setParentOrgFlow("");
                    exitOrgs.get(i).setNo((i+1)+"");*/
                    String countryOrgFlow=appuserInfoUniversityFirstlist.get(i).getOrgFlow();
                    paramMap.put("orgFlow",countryOrgFlow);
                    paramMap.put("universityDoctorFlows",appuserInfoUniversityFirstlist.get(i).getDoctorFlowsInOrg());
                    paramMap.put("docTypeList",docTypeListDoc);
                    paramMap2.put("orgFlow",countryOrgFlow);
                    paramMap2.put("universityDoctorFlows",appuserInfoUniversityFirstlist.get(i).getDoctorFlowsInOrg());
                    paramMap2.put("docTypeList",docTypeListMast);
                    /*List<SysOrg> list =monthlyReportExtMapper2.getAppDatayuh(paramMap);*/
                    List<SysOrg> list=new ArrayList<>();
                    SysOrg sss=new SysOrg();
                    List<JsResDoctorRecruitExt> docList=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap);
                    List<JsResDoctorRecruitExt> mastlist=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2);
                    Map<String,Object> paramMap3 = new HashMap<>();
                    paramMap3.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                    paramMap3.put("myDoctorTypeId",docTypeListDoc);
                    paramMap3.put("orgFlow",countryOrgFlow);
                    paramMap3.put("universityDoctorFlows",appuserInfoUniversityFirstlist.get(i).getDoctorFlowsInOrg());
                    Map<String,Object> paramMap4 = new HashMap<>();
                    paramMap4.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                    paramMap4.put("myDoctorTypeId",docTypeListMast);
                    paramMap4.put("orgFlow",countryOrgFlow);
                    paramMap4.put("universityDoctorFlows",appuserInfoUniversityFirstlist.get(i).getDoctorFlowsInOrg());
                    List<String> docAppuse= monthlyReportExtMapper2.getAppUserSum(paramMap3);//
                    List<String> masteAppuse= monthlyReportExtMapper2.getAppUserSum(paramMap4);
                    int allAppuse=docAppuse.size()+masteAppuse.size();  //所有app使用人数
                    sss.setTrainDoctorTotal(docList.size()+mastlist.size());
                    sss.setDoctorSum(docList.size());
                    sss.setMasterSum(mastlist.size());
                    list.add(sss);
                    if(list.size()>0){
                        SysOrg sysOrgT =list.get(0);
                        appuserInfoUniversityFirstlist.get(i).setTrainDoctorTotal(sysOrgT.getTrainDoctorTotal());
                        appuserInfoUniversityFirstlist.get(i).setDoctorSum(sysOrgT.getDoctorSum());
                        appuserInfoUniversityFirstlist.get(i).setMasterSum(sysOrgT.getMasterSum());
                        String doctorRate="";
                        String masterRate="";
                        if(sysOrgT.getDoctorSum()==0){
                            doctorRate="0%";
                        }
                        if(0!=sysOrgT.getDoctorSum()){
                            doctorRate = numberFormat.format((float) docAppuse.size() / (float) sysOrgT.getDoctorSum() * 100)+"%";
                        }
                        if(sysOrgT.getMasterSum()==0){
                            masterRate="0%";
                        }
                        if(0!=sysOrgT.getMasterSum()){
                            masterRate = numberFormat.format((float)masteAppuse.size() / (float) sysOrgT.getMasterSum() * 100)+"%";
                        }
                      /*  if(null!=sysOrgT.getTrainDoctorTotal()&& !"".equals(sysOrgT.getTrainDoctorTotal())){
                            doctorRate = numberFormat.format((float) sysOrgT.getDoctorSum() / (float) sysOrgT.getTrainDoctorTotal() * 100)+"%";
                            masterRate = numberFormat.format((float) sysOrgT.getMasterSum() / (float) sysOrgT.getTrainDoctorTotal() * 100)+"%";
                        }*/
                        appuserInfoUniversityFirstlist.get(i).setDoctorRate(doctorRate);
                        appuserInfoUniversityFirstlist.get(i).setMasterRate(masterRate);
                        Integer trainPersonTotal = monthlyReportExtMapper2.getTrainTotal(paramMap);//该基地在培总人数
                        String rate="";
                        if(trainPersonTotal==0){
                            rate="0%";
                        }
                        if(0!=trainPersonTotal){
                            rate=numberFormat.format((float) allAppuse / (float)trainPersonTotal  * 100)+"%";
                        }
                        /*if(null!=trainPersonTotal&& !"".equals(trainPersonTotal)){
                            rate=numberFormat.format((float) sysOrgT.getTrainDoctorTotal() / (float)trainPersonTotal  * 100)+"%";
                        }*/
                        appuserInfoUniversityFirstlist.get(i).setRate(rate);
                    }

                    for(SysOrg second:appuserInfoUniversitylist){
                        if(second.getParentOrgFlow().equals(appuserInfoUniversityFirstlist.get(i).getOrgFlow())){
                            /*resJointOrgList.get(j).setParentOrgFlow(exitOrgs.get(i).getOrgFlow());
                            resJointOrgList.get(j).setNo((i+1)+"-"+(j+1));*/
                            paramMap.put("orgFlow",second.getOrgFlow());
                            paramMap.put("universityDoctorFlows",second.getDoctorFlowsInOrg());

                            Map<String,Object> paramMapXieTong=new HashMap<>();
                            Map<String,Object> paramMap2XieTong=new HashMap<>();
                            paramMapXieTong.put("orgFlow",second.getOrgFlow());
                            paramMapXieTong.put("docTypeList",docTypeListDoc);
                            paramMapXieTong.put("universityDoctorFlows",second.getDoctorFlowsInOrg());
                            paramMap2XieTong.put("orgFlow",second.getOrgFlow());
                            paramMap2XieTong.put("docTypeList",docTypeListMast);
                            paramMap2XieTong.put("universityDoctorFlows",second.getDoctorFlowsInOrg());

                            List<SysOrg> list2=new ArrayList<>();
                            SysOrg ms=new SysOrg();
                            List<JsResDoctorRecruitExt> docListXietong=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMapXieTong);
                            List<JsResDoctorRecruitExt> mastlistXietong=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2XieTong);
                            Map<String,Object> paramMap3Xietong = new HashMap<>();
                            paramMap3Xietong.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                            paramMap3Xietong.put("myDoctorTypeId",docTypeListDoc);
                            paramMap3Xietong.put("orgFlow",second.getOrgFlow());
                            paramMap3Xietong.put("universityDoctorFlows",second.getDoctorFlowsInOrg());
                            Map<String,Object> paramMap4Xietong = new HashMap<>();
                            paramMap4Xietong.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                            paramMap4Xietong.put("myDoctorTypeId",docTypeListMast);
                            paramMap4Xietong.put("orgFlow",second.getOrgFlow());
                            paramMap4Xietong.put("universityDoctorFlows",second.getDoctorFlowsInOrg());
                            List<String> docAppuseXietong= monthlyReportExtMapper2.getAppUserSum(paramMap3Xietong);//
                            List<String> masteAppuseXietong= monthlyReportExtMapper2.getAppUserSum(paramMap4Xietong);
                            int allAppuseXietong=docAppuseXietong.size()+masteAppuseXietong.size();  //所有app使用人数
                            ms.setTrainDoctorTotal(docListXietong.size()+mastlistXietong.size());
                            ms.setDoctorSum(docListXietong.size());
                            ms.setMasterSum(mastlistXietong.size());
                            list2.add(ms);

                            /*List<SysOrg> list2 =monthlyReportExtMapper2.getAppDatayuh(paramMap);*/
                            if(list2.size()>0){
                                SysOrg sysOrgT2 =list2.get(0);
                                second.setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
                                second.setDoctorSum(sysOrgT2.getDoctorSum());
                                second.setMasterSum(sysOrgT2.getMasterSum());
                                String doctorRate2="";
                                String masterRate2="";
                                if(sysOrgT2.getDoctorSum()==0){
                                    doctorRate2="0%";
                                }
                                if(0!=sysOrgT2.getDoctorSum()){
                                    doctorRate2 = numberFormat.format((float) docAppuseXietong.size() / (float) sysOrgT2.getDoctorSum() * 100)+"%";
                                }
                                if(sysOrgT2.getMasterSum()==0){
                                    masterRate2="0%";
                                }
                                if(0!= sysOrgT2.getMasterSum()){
                                    masterRate2 = numberFormat.format((float) masteAppuseXietong.size()/ (float)  sysOrgT2.getMasterSum()  * 100)+"%";
                                }
                               /* if(null!=sysOrgT2.getTrainDoctorTotal()&& !"".equals(sysOrgT2.getTrainDoctorTotal())){
                                    doctorRate2 = numberFormat.format((float) sysOrgT2.getDoctorSum() / (float) sysOrgT2.getTrainDoctorTotal() * 100)+"%";
                                    masterRate2 = numberFormat.format((float) sysOrgT2.getMasterSum() / (float) sysOrgT2.getTrainDoctorTotal() * 100)+"%";
                                }*/
                                second.setDoctorRate(doctorRate2);
                                second.setMasterRate(masterRate2);
                                Integer trainPersonTotal2 = monthlyReportExtMapper2.getTrainTotal(paramMap);//该基地在培总人数
                                String rate2="";
                                if(trainPersonTotal2==0){
                                    rate2="0%";
                                }
                                if(0!=trainPersonTotal2){
                                    rate2=numberFormat.format((float) allAppuseXietong/ (float)trainPersonTotal2  * 100)+"%";
                                }
                                /*if(null!=trainPersonTotal2&& !"".equals(trainPersonTotal2)){
                                    rate2=numberFormat.format((float) sysOrgT2.getTrainDoctorTotal() / (float)trainPersonTotal2  * 100)+"%";
                                }*/
                                second.setRate(rate2);
                            }
                            orgs.add(second);
                        }
                    }
                    orgs.add(appuserInfoUniversityFirstlist.get(i));
                    //allOrgFlow.add(g.getOrgFlow());
                }
            }
               orgs= resMonthlyReportGlobalController.listSort(sortFlag,orgs);
            }else{
                throw new RuntimeException("请联系管理员“派送学校”是否有“"+currOrgName+"”高校");
            }
            return orgs;
        }catch (RuntimeException e){
            List<Map<String,String>> list=new ArrayList<>();
            Map<String,String> map=new HashMap<>();
            if(e.getMessage()==null){
                map.put("error","null");
            }else{
                map.put("error",e.getMessage());
            }
            list.add(map);
            //model.addAttribute("error",e.getMessage());
            return list;
        }
    }
    /**
     * 学员轮转
     * @return
     */
    public List rotation(String role,String monthDate,String isContain,String orderBy,String inSchool,String resident,List<SysOrg> orgs ){
        SysUser user = GlobalContext.getCurrentUser();
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位
        List<SysOrg> rotationUniversitylist=new ArrayList<>();
        List<SysOrg> rotationUniversityFirstlist=new ArrayList<>();
        List<String> paramList = new ArrayList<>();
        if(StringUtil.isNotBlank(inSchool)){
            paramList.add("Graduate");
        }if(StringUtil.isNotBlank(resident)){
            paramList.add("Company");
            paramList.add("CompanyEntrust");
            paramList.add("Social");
        }
        try {
            SysOrg searchOrg = new SysOrg();
            SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
            String currOrgName = currOrg.getOrgName();
            Boolean gaoxiaoFlg = false;
            List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
            if (sendSchools != null && sendSchools.size() > 0) {
                for (SysDict dict : sendSchools) {
                    if (currOrgName.equals(dict.getDictName())) {
                        gaoxiaoFlg = true;
                    }
                }
            }
            if(gaoxiaoFlg) {
                Map<String, Object> map1 = new HashMap<>();
                List<SysOrg> orgUniversity = new ArrayList<>();
                List<SysOrg> distinctOrgUniversity = new ArrayList<>();
                map1.put("universityName", currOrgName);
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
                for(SysOrg syso:rotationUniversitylist){
                    if("".equals(syso.getParentOrgFlow())){
                        rotationUniversityFirstlist.add(syso);
                    }
                }
                //包含协同
                if("isContain".equals(isContain)){
                    Map<String,Object> map=new HashMap<>();
                    map.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                    map.put("doctorTypeIdList",paramList);
                    for(int i=0;i<rotationUniversityFirstlist.size();i++){
                        List<String> allOrgFlow = new ArrayList<>();
                        List<String> allUniversityDoctorsFlow = new ArrayList<>();
                        for(SysOrg second:rotationUniversitylist){
                            if(second.getParentOrgFlow().equals(rotationUniversityFirstlist.get(i).getOrgFlow())){
                                allOrgFlow.add(second.getOrgFlow());
                                allUniversityDoctorsFlow.addAll(second.getDoctorFlowsInOrg());
                            }
                        }
                        allOrgFlow.add(rotationUniversityFirstlist.get(i).getOrgFlow());
                        allUniversityDoctorsFlow.addAll(rotationUniversityFirstlist.get(i).getDoctorFlowsInOrg());
                        map.put("allOrgFlow",allOrgFlow);
                        map.put("universityDoctorFlows",allUniversityDoctorsFlow);
                        /*map.put("doctorTypeIdList",paramList);*/
                        List<SysOrg> list =resStatisticBiz.getRotationData(map);
                        if(list.size()>0){
                            Integer trainDoctorTotal=0;
                            Integer fillNum=0;
                            Integer auditNum=0;
                            for(SysOrg s:list){
                                trainDoctorTotal=trainDoctorTotal+s.getTrainDoctorTotal();
                                fillNum=fillNum+s.getFillNum();
                                auditNum=auditNum+s.getAuditNum();
                            }
                            rotationUniversityFirstlist.get(i).setTrainDoctorTotal(trainDoctorTotal);
                            rotationUniversityFirstlist.get(i).setFillNum(fillNum);
                            rotationUniversityFirstlist.get(i).setAuditNum(auditNum);
                            String auditRate = "0%";
                            String avgFillNum = "0";
                            if(null!=fillNum && !"".equals(fillNum)){
                                if(fillNum==0){
                                    auditRate="0%";
                                }else{
                                    auditRate = numberFormat.format((float) auditNum / (float) fillNum * 100)+"%";
                                }
                            }
                            if(null!=trainDoctorTotal && !"".equals(trainDoctorTotal)){
                                if(trainDoctorTotal==0){
                                    avgFillNum="0";
                                }else{
                                    avgFillNum = numberFormat.format((float) fillNum / (float) trainDoctorTotal);
                                }
                            }
                            rotationUniversityFirstlist.get(i).setAuditRate(auditRate);
                            rotationUniversityFirstlist.get(i).setAvgfillNum(avgFillNum);
                        }
                        for(SysOrg second:rotationUniversitylist) {
                            if (second.getParentOrgFlow().equals(rotationUniversityFirstlist.get(i).getOrgFlow())) {
                                Map<String, Object> secondMap = new HashMap<>();
                                secondMap.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
                                secondMap.put("orgFlow", second.getOrgFlow());
                                secondMap.put("universityDoctorFlows", second.getDoctorFlowsInOrg());
                                secondMap.put("doctorTypeIdList",paramList);
                                List<SysOrg> list2 = resStatisticBiz.getRotationData(secondMap);
                                if (list2.size() > 0) {
                                    SysOrg sysOrgT2 = list2.get(0);
                                    second.setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
                                    second.setAuditNum(sysOrgT2.getAuditNum());
                                    second.setFillNum(sysOrgT2.getFillNum());
                                    String auditRate = "0%";
                                    String avgFillNum = "0";
                                    if(null!=second.getFillNum() && !"".equals(second.getFillNum())){
                                        if(second.getFillNum()==0){
                                            auditRate = "0%";
                                        }else{
                                            auditRate = numberFormat.format((float) second.getAuditNum() / (float) second.getFillNum() * 100)+"%";
                                        }
                                    }
                                    if(null!=second.getTrainDoctorTotal() && !"".equals(second.getTrainDoctorTotal())){
                                        if(second.getTrainDoctorTotal()==0){
                                            avgFillNum = "0";
                                        }else{
                                            avgFillNum = numberFormat.format((float) second.getFillNum() / (float) second.getTrainDoctorTotal());
                                        }
                                    }
                                    second.setAuditRate(auditRate);
                                    second.setAvgfillNum(avgFillNum);
                                }
                                orgs.add(second);
                            }
                        }
                        orgs.add(rotationUniversityFirstlist.get(i));
                    }
                    //不包含协同
                }else{
                    Map<String,Object> paramMap = new HashMap<>();
                    paramMap.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                    paramMap.put("doctorTypeIdList",paramList);
                    for(int i=0;i<rotationUniversityFirstlist.size();i++){
                        String countryOrgFlow=rotationUniversityFirstlist.get(i).getOrgFlow();
                        paramMap.put("orgFlow",countryOrgFlow);
                        paramMap.put("universityDoctorFlows",rotationUniversityFirstlist.get(i).getDoctorFlowsInOrg());
                        List<SysOrg> list =resStatisticBiz.getRotationData(paramMap);
                        if(list.size()>0){
                            SysOrg sysOrgT =list.get(0);
                            rotationUniversityFirstlist.get(i).setTrainDoctorTotal(sysOrgT.getTrainDoctorTotal());
                            rotationUniversityFirstlist.get(i).setFillNum(sysOrgT.getFillNum());
                            rotationUniversityFirstlist.get(i).setAuditNum(sysOrgT.getAuditNum());
                            String auditRate="0%";
                            String avgFillNum="0";
                            if(null!=sysOrgT.getFillNum()&& !"".equals(sysOrgT.getFillNum())){
                                if(sysOrgT.getFillNum()==0){
                                    auditRate="0%";
                                }else{
                                    auditRate = numberFormat.format((float) sysOrgT.getAuditNum() / (float) sysOrgT.getFillNum() * 100)+"%";
                                }
                            }
                            if(null!=sysOrgT.getTrainDoctorTotal()&& !"".equals(sysOrgT.getTrainDoctorTotal())){
                                if(sysOrgT.getTrainDoctorTotal()==0){
                                    avgFillNum="0";
                                }else{
                                    avgFillNum = numberFormat.format((float) sysOrgT.getFillNum() / (float) sysOrgT.getTrainDoctorTotal());
                                }
                            }
                            rotationUniversityFirstlist.get(i).setAuditRate(auditRate);
                            rotationUniversityFirstlist.get(i).setAvgfillNum(avgFillNum);
                        }


                        for(SysOrg second:rotationUniversitylist){
                            if(second.getParentOrgFlow().equals(rotationUniversityFirstlist.get(i).getOrgFlow())){
                                Map<String,Object> paramMap2 = new HashMap<>();
                                paramMap2.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                                paramMap2.put("doctorTypeIdList",paramList);
                                paramMap2.put("orgFlow",second.getOrgFlow());
                                paramMap2.put("universityDoctorFlows",second.getDoctorFlowsInOrg());
                                List<SysOrg> list2 =resStatisticBiz.getRotationData(paramMap2);
                                if(list2.size()>0){
                                    SysOrg sysOrgT2 =list2.get(0);
                                    second.setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
                                    second.setFillNum(sysOrgT2.getFillNum());
                                    second.setAuditNum(sysOrgT2.getAuditNum());
                                    String auditRate="0%";
                                    String avgFillNum="0";
                                    if(null!=second.getFillNum()&& !"".equals(second.getFillNum())){
                                        if(second.getFillNum() ==0){
                                            auditRate="0%";
                                        }else{
                                            auditRate = numberFormat.format((float) second.getAuditNum() / (float) second.getFillNum() * 100)+"%";
                                        }
                                    }
                                    if(null!=second.getTrainDoctorTotal()&& !"".equals(second.getTrainDoctorTotal())){
                                        if(second.getTrainDoctorTotal()==0){
                                            avgFillNum="0";
                                        }else{
                                            avgFillNum = numberFormat.format((float) second.getFillNum() / (float) second.getTrainDoctorTotal());
                                        }
                                    }
                                    second.setAuditRate(auditRate);
                                    second.setAvgfillNum(avgFillNum);
                                }
                                orgs.add(second);
                            }
                        }
                        orgs.add(rotationUniversityFirstlist.get(i));
                    }
                }
                orgs= jsResManageController.listSort(orderBy,orgs);
            }else{
                throw new RuntimeException("请联系管理员“派送学校”是否有“"+currOrgName+"”高校");
            }
            return orgs;
        }catch (RuntimeException e){
            List<Map<String,String>> list=new ArrayList<>();
            Map<String,String> map=new HashMap<>();
            if(e.getMessage()==null){
                map.put("error","null");
            }else{
                map.put("error",e.getMessage());
            }
            list.add(map);
            return list;
        }
    }


    /**
     *  @author:
     *  @Date: 2020/1/26 11:02
     *  @Description: 高校人员信息
     */
    public List gaoxiaoPersonSattic(String lastMonthdateStr,String monthdateStr,String lastMonthdate,String monthdate, String isContain){
        SysUser user = GlobalContext.getCurrentUser();
        List<PersonStaticExample> staticData = new ArrayList<>();

        List<SysOrg> appuserInfoUniversitylist=new ArrayList<>();
        List<SysOrg> appuserInfoUniversityFirstlist=new ArrayList<>();
        try {
            SysOrg searchOrg = new SysOrg();
            SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
            String currOrgName = currOrg.getOrgName();

            Boolean gaoxiaoFlg = false;
            List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
            if (sendSchools != null && sendSchools.size() > 0) {
                for (SysDict dict : sendSchools) {
                    if (currOrgName.equals(dict.getDictName())) {
                        gaoxiaoFlg = true;
                    }
                }
            }
            if(gaoxiaoFlg){
                Map<String, Object> map1 = new HashMap<>();
                List<SysOrg> orgUniversity = new ArrayList<>();
                List<SysOrg> distinctOrgUniversity = new ArrayList<>();
                map1.put("universityName", currOrgName);
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
               /* for(SysOrg syso:appuserInfoUniversitylist){
                    if("".equals(syso.getParentOrgFlow())){
                        appuserInfoUniversityFirstlist.add(syso);
                    }
                }*/
                //对象转化
                for (int i = 0; i < appuserInfoUniversitylist.size(); i++) {
                    PersonStaticExample staticExample = new PersonStaticExample();
                    BeanUtils.copyProperties(appuserInfoUniversitylist.get(i), staticExample);
                    staticData.add(staticExample);
                }

                //包含协同
                if ("isContain".equals(isContain)) {
                    for (PersonStaticExample personStaticExample : staticData) {
                        Integer lastInSchoolNum = 0;
                        Integer inSchoolNum = 0;
                        Integer lastResidentNum = 0;
                        Integer residentNum = 0;
                        Integer residentRecruitNum = 0;
                        Integer inSchoolRecruitNum = 0;
                        Integer residentExaminedNum = 0;
                        Integer inSchoolExaminedNum = 0;
                        Integer residentGraduatNum = 0;
                        Integer inSchoolGraduatNum = 0;
                        Integer residentReturnNum = 0;
                        Integer inSchoolReturnNum = 0;
                        Integer residentOutNum = 0;
                        Integer inSchoolOutNum = 0;
                        Integer residentInNum = 0;
                        Integer inSchoolInNum = 0;
                        Integer lastBothNum= 0;
                        Integer bothNum= 0;
                        String orgFlow = personStaticExample.getOrgFlow();
                        Map<String, Object> parmMap = new HashMap<>();
                        parmMap.put("orgFlow", orgFlow);
                        parmMap.put("lastMonthdateStr", lastMonthdateStr);//"2019-09"
                        parmMap.put("monthdateStr", monthdateStr);//"2019-10"
                        parmMap.put("lastMonthdate", lastMonthdate);//"201909"
                        parmMap.put("monthdate", monthdate);//"201910"
                        parmMap.put("universityDoctorFlows", personStaticExample.getDoctorFlowsInOrg());
                        List<PersonStaticExample> staticExample = resStatisticBiz.getPersonStaticDataNewyuh(parmMap);
                        Integer residentRecruitCount =resStatisticBiz.residentRecruitCount(parmMap);
                        Integer inSchoolRecruitCount = resStatisticBiz.inSchoolRecruitCount(parmMap);
                        for (PersonStaticExample orgStatic : staticExample) {
                            lastInSchoolNum = orgStatic.getLastInSchoolNum()==null?0:orgStatic.getLastInSchoolNum();
                            inSchoolNum = orgStatic.getInSchoolNum()==null?0:orgStatic.getInSchoolNum();
                            lastResidentNum = orgStatic.getLastResidentNum()==null?0:orgStatic.getLastResidentNum();
                            residentNum = orgStatic.getResidentNum()==null?0:orgStatic.getResidentNum();
                            residentRecruitNum = residentRecruitCount==null?0:residentRecruitCount/*orgStatic.getResidentRecruitNum()*/;
                            inSchoolRecruitNum =inSchoolRecruitCount==null?0:inSchoolRecruitCount /* orgStatic.getInSchoolRecruitNum()*/;
                            residentExaminedNum = orgStatic.getResidentExaminedNum()==null?0:orgStatic.getResidentExaminedNum();
                            inSchoolExaminedNum = orgStatic.getInSchoolExaminedNum()==null?0:orgStatic.getInSchoolExaminedNum();
                            residentGraduatNum = orgStatic.getResidentGraduatNum()==null?0:orgStatic.getResidentGraduatNum();
                            inSchoolGraduatNum = orgStatic.getInSchoolGraduatNum()==null?0:orgStatic.getInSchoolGraduatNum();
                            residentReturnNum = orgStatic.getResidentReturnNum()==null?0:orgStatic.getResidentReturnNum();
                            inSchoolReturnNum = orgStatic.getInSchoolReturnNum()==null?0:orgStatic.getInSchoolReturnNum();
                            residentOutNum = orgStatic.getResidentOutNum()==null?0:orgStatic.getResidentOutNum();
                            inSchoolOutNum = orgStatic.getInSchoolOutNum()==null?0:orgStatic.getInSchoolOutNum();
                            residentInNum = orgStatic.getResidentInNum()==null?0:orgStatic.getResidentInNum();
                            inSchoolInNum = orgStatic.getInSchoolInNum()==null?0:orgStatic.getInSchoolInNum();
                            lastBothNum =orgStatic.getLastBothNum()==null?0:orgStatic.getLastBothNum();
                            bothNum = orgStatic.getBothNum()==null?0:orgStatic.getBothNum();
                        }
                        for (PersonStaticExample example22 : staticData) {
                            if (orgFlow.equals(example22.getParentOrgFlow())) {
                                Map<String, Object> parmMap22 = new HashMap<>();
                                parmMap22.put("orgFlow", example22.getOrgFlow());
                                parmMap22.put("lastMonthdateStr", lastMonthdateStr);//"2019-09"
                                parmMap22.put("monthdateStr", monthdateStr);//"2019-10"
                                parmMap22.put("lastMonthdate", lastMonthdate);//"201909"
                                parmMap22.put("monthdate", monthdate);//"201910"
                                parmMap22.put("universityDoctorFlows", example22.getDoctorFlowsInOrg());
                                List<PersonStaticExample> staticExample22 = resStatisticBiz.getPersonStaticDataNewyuh(parmMap22);
                                Integer residentRecruitCount22 =resStatisticBiz.residentRecruitCount(parmMap22);
                                Integer inSchoolRecruitCount22 = resStatisticBiz.inSchoolRecruitCount(parmMap22);
                                if(staticExample22.size()>0){
                                    PersonStaticExample example=   staticExample22.get(0);

                                lastInSchoolNum = lastInSchoolNum + (example.getLastInSchoolNum()==null?0:example.getLastInSchoolNum());
                                inSchoolNum = inSchoolNum + (example.getInSchoolNum()==null?0:example.getInSchoolNum());
                                lastResidentNum = lastResidentNum + (example.getLastResidentNum()==null?0:example.getLastResidentNum());
                                residentNum = residentNum + (example.getResidentNum()==null?0:example.getResidentNum());
                                residentRecruitNum = residentRecruitNum + (residentRecruitCount22==null?0:residentRecruitCount22)/*example.getResidentRecruitNum()*/;
                                inSchoolRecruitNum = inSchoolRecruitNum + (inSchoolRecruitCount22==null?0:inSchoolRecruitCount22)/*example.getInSchoolRecruitNum()*/;
                                residentExaminedNum = residentExaminedNum + (example.getResidentExaminedNum()==null?0:example.getResidentExaminedNum());
                                inSchoolExaminedNum = inSchoolExaminedNum + (example.getInSchoolExaminedNum()==null?0:example.getInSchoolExaminedNum());
                                residentGraduatNum = residentGraduatNum + (example.getResidentGraduatNum()==null?0:example.getResidentGraduatNum());
                                inSchoolGraduatNum = inSchoolGraduatNum + (example.getInSchoolGraduatNum()==null?0:example.getInSchoolGraduatNum());
                                residentReturnNum = residentReturnNum + (example.getResidentReturnNum()==null?0:example.getResidentReturnNum());
                                inSchoolReturnNum = inSchoolReturnNum + (example.getInSchoolReturnNum()==null?0:example.getInSchoolReturnNum());
                                residentOutNum = residentOutNum + (example.getResidentOutNum()==null?0:example.getResidentOutNum());
                                inSchoolOutNum = inSchoolOutNum + (example.getInSchoolOutNum()==null?0:example.getInSchoolOutNum());
                                residentInNum = residentInNum + (example.getResidentInNum()==null?0:example.getResidentInNum());
                                inSchoolInNum = inSchoolInNum + (example.getInSchoolInNum()==null?0:example.getInSchoolInNum());
                                    lastBothNum = lastBothNum +(example.getLastBothNum()==null?0:example.getLastBothNum());
                                    bothNum =bothNum+(example.getBothNum()==null?0:example.getBothNum());
                                }
                            }
                        }
                        personStaticExample.setLastInSchoolNum(lastInSchoolNum);
                        personStaticExample.setInSchoolNum(inSchoolNum);
                        personStaticExample.setLastResidentNum(lastResidentNum);
                        personStaticExample.setResidentNum(residentNum);
                        personStaticExample.setResidentRecruitNum(residentRecruitNum);
                        personStaticExample.setInSchoolRecruitNum(inSchoolRecruitNum);
                        personStaticExample.setResidentExaminedNum(residentExaminedNum);
                        personStaticExample.setInSchoolExaminedNum(inSchoolExaminedNum);
                        personStaticExample.setResidentGraduatNum(residentGraduatNum);
                        personStaticExample.setInSchoolGraduatNum(inSchoolGraduatNum);
                        personStaticExample.setResidentReturnNum(residentReturnNum);
                        personStaticExample.setInSchoolReturnNum(inSchoolReturnNum);
                        personStaticExample.setResidentOutNum(residentOutNum);
                        personStaticExample.setInSchoolOutNum(inSchoolOutNum);
                        personStaticExample.setResidentInNum(residentInNum);
                        personStaticExample.setInSchoolInNum(inSchoolInNum);
                            personStaticExample.setLastBothNum(lastBothNum);
                            personStaticExample.setBothNum(bothNum);
                    }
                    //不包含协同
                } else {
                    for (PersonStaticExample personStaticExample : staticData) {
                        Integer lastInSchoolNum = 0;
                        Integer inSchoolNum = 0;
                        Integer lastResidentNum = 0;
                        Integer residentNum = 0;
                        Integer residentRecruitNum = 0;
                        Integer inSchoolRecruitNum = 0;
                        Integer residentExaminedNum = 0;
                        Integer inSchoolExaminedNum = 0;
                        Integer residentGraduatNum = 0;
                        Integer inSchoolGraduatNum = 0;
                        Integer residentReturnNum = 0;
                        Integer inSchoolReturnNum = 0;
                        Integer residentOutNum = 0;
                        Integer inSchoolOutNum = 0;
                        Integer residentInNum = 0;
                        Integer inSchoolInNum = 0;
                        Integer lastBothNum= 0;
                        Integer bothNum= 0;
                        String orgFlow = personStaticExample.getOrgFlow();
                        Map<String, Object> parmMap = new HashMap<>();
                        parmMap.put("orgFlow", orgFlow);
                        parmMap.put("lastMonthdateStr", lastMonthdateStr);//"2019-09"
                        parmMap.put("monthdateStr", monthdateStr);//"2019-10"
                        parmMap.put("lastMonthdate", lastMonthdate);//"201909"
                        parmMap.put("monthdate", monthdate);//"201910"
                        parmMap.put("universityDoctorFlows", personStaticExample.getDoctorFlowsInOrg());
                        List<PersonStaticExample> staticExample = resStatisticBiz.getPersonStaticDataNewyuh(parmMap);
                        Integer residentRecruitCount =resStatisticBiz.residentRecruitCount(parmMap);
                        Integer inSchoolRecruitCount = resStatisticBiz.inSchoolRecruitCount(parmMap);
                        for (PersonStaticExample orgStatic : staticExample) {
                            lastInSchoolNum = orgStatic.getLastInSchoolNum();
                            inSchoolNum = orgStatic.getInSchoolNum();
                            lastResidentNum=	orgStatic.getLastResidentNum();
                            residentNum = orgStatic.getResidentNum();
                            residentRecruitNum = residentRecruitCount /*orgStatic.getResidentRecruitNum()*/;
                            inSchoolRecruitNum = inSchoolRecruitCount /*orgStatic.getInSchoolRecruitNum()*/;
                            residentExaminedNum= orgStatic.getResidentExaminedNum();
                            inSchoolExaminedNum=orgStatic.getInSchoolExaminedNum();
                            residentGraduatNum=orgStatic.getResidentGraduatNum();
                            inSchoolReturnNum= orgStatic.getInSchoolReturnNum();
                            residentReturnNum= orgStatic.getResidentReturnNum();
                            inSchoolGraduatNum =orgStatic.getInSchoolGraduatNum();
                            residentOutNum = orgStatic.getResidentOutNum();
                            inSchoolOutNum =orgStatic.getInSchoolOutNum();
                            residentInNum =orgStatic.getResidentInNum();
                            inSchoolInNum =orgStatic.getInSchoolInNum();
                            lastBothNum =orgStatic.getLastBothNum();
                            bothNum=orgStatic.getBothNum();
                        }
                        personStaticExample.setLastInSchoolNum(lastInSchoolNum==null?0:lastInSchoolNum);
                        personStaticExample.setInSchoolNum(inSchoolNum==null?0:inSchoolNum);
                        personStaticExample.setLastResidentNum(lastResidentNum==null?0:lastResidentNum);
                        personStaticExample.setResidentNum(residentNum==null?0:residentNum);
                        personStaticExample.setResidentRecruitNum(residentRecruitNum==null?0:residentRecruitNum);
                        personStaticExample.setInSchoolRecruitNum(inSchoolRecruitNum==null?0:inSchoolRecruitNum);
                        personStaticExample.setResidentExaminedNum(residentExaminedNum==null?0:residentExaminedNum);
                        personStaticExample.setInSchoolExaminedNum(inSchoolExaminedNum==null?0:inSchoolExaminedNum);
                        personStaticExample.setResidentGraduatNum(residentGraduatNum==null?0:residentGraduatNum);
                        personStaticExample.setInSchoolGraduatNum(inSchoolGraduatNum==null?0:inSchoolGraduatNum);
                        personStaticExample.setResidentReturnNum(residentReturnNum==null?0:residentReturnNum);
                        personStaticExample.setInSchoolReturnNum(inSchoolReturnNum==null?0:inSchoolReturnNum);
                        personStaticExample.setResidentOutNum(residentOutNum==null?0:residentOutNum);
                        personStaticExample.setInSchoolOutNum(inSchoolOutNum==null?0:inSchoolOutNum);
                        personStaticExample.setResidentInNum(residentInNum==null?0:residentInNum);
                        personStaticExample.setInSchoolInNum(inSchoolInNum==null?0:inSchoolInNum);
                        personStaticExample.setLastBothNum(lastBothNum==null?0:lastBothNum);
                        personStaticExample.setBothNum(bothNum==null?0:bothNum);
                    }
                }
               return staticData;
            }else{
                throw new RuntimeException("请联系管理员“派送学校”是否有“"+currOrgName+"”高校");
            }
        }catch (RuntimeException e){
            List<Map<String,String>> list=new ArrayList<>();
            Map<String,String> map=new HashMap<>();
            if(e.getMessage()==null){
                map.put("error","null");
            }else{
                map.put("error",e.getMessage());
            }
            list.add(map);
            //model.addAttribute("error",e.getMessage());
            return list;
        }

    }


    public List gaoxiaopersonChange(String lastMonthdate, String isContain2){
        SysUser user = GlobalContext.getCurrentUser();
        List<PersonChangeEntity> staticData = new ArrayList<>();

        List<SysOrg> appuserInfoUniversitylist=new ArrayList<>();
        List<SysOrg> appuserInfoUniversityFirstlist=new ArrayList<>();
        try {
            SysOrg searchOrg = new SysOrg();
            SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
            String currOrgName = currOrg.getOrgName();

            Boolean gaoxiaoFlg = false;
            List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
            if (sendSchools != null && sendSchools.size() > 0) {
                for (SysDict dict : sendSchools) {
                    if (currOrgName.equals(dict.getDictName())) {
                        gaoxiaoFlg = true;
                    }
                }
            }
            if(gaoxiaoFlg){
                Map<String, Object> map1 = new HashMap<>();
                List<SysOrg> orgUniversity = new ArrayList<>();
                List<SysOrg> distinctOrgUniversity = new ArrayList<>();
                map1.put("universityName", currOrgName);
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
                //对象转化
                for (int i = 0; i < appuserInfoUniversitylist.size(); i++) {
                    PersonChangeEntity staticExample = new PersonChangeEntity();
                    BeanUtils.copyProperties(appuserInfoUniversitylist.get(i), staticExample);
                    staticData.add(staticExample);
                }
                Integer changeTimeNum = 0;
                Integer speChangeNum = 0;
//包含协同
                if ("isContain2".equals(isContain2)) {
                    for (PersonChangeEntity personStaticExample : staticData) {
                        String orgFlow = personStaticExample.getOrgFlow();
                        Map<String, Object> parmMap = new HashMap<>();
                        parmMap.put("orgFlow", orgFlow);
                        parmMap.put("monthDate", lastMonthdate);//"201909"
                        parmMap.put("universityDoctorFlows", personStaticExample.getDoctorFlowsInOrg());
                        List<PersonChangeEntity> staticExample = monthlyReportExtMapper2.selectFindChangeSpe(parmMap);
                        if(staticExample.size()>0){
                            for (PersonChangeEntity orgStatic : staticExample) {
                                if(null==orgStatic.getChangeTimeNum() || "".equals(orgStatic.getChangeTimeNum())){
                                    changeTimeNum = 0;
                                }else{
                                    changeTimeNum = orgStatic.getChangeTimeNum();
                                }
                                if(null==orgStatic.getSpeChangeNum() || "".equals(orgStatic.getSpeChangeNum())){
                                    speChangeNum = 0;
                                }else{
                                    speChangeNum = orgStatic.getSpeChangeNum();
                                }
                            }
                        }else{
                            changeTimeNum=0;
                            speChangeNum=0;
                        }
                        for (PersonChangeEntity example22 : staticData) {
                            if (orgFlow.equals(example22.getParentOrgFlow())) {
                                Map<String, Object> parmMap22 = new HashMap<>();
                                parmMap22.put("orgFlow", example22.getOrgFlow());
                                parmMap22.put("monthDate", lastMonthdate);//"201909"
                                parmMap22.put("universityDoctorFlows", example22.getDoctorFlowsInOrg());
                                List<PersonChangeEntity> staticExample22 = monthlyReportExtMapper2.selectFindChangeSpe(parmMap22);
                                if(staticExample22.size()>0){
                                    PersonChangeEntity example=   staticExample22.get(0);
                                    if(null!=example.getChangeTimeNum()){
                                        changeTimeNum = changeTimeNum + example.getChangeTimeNum();
                                    }
                                    if(null!=example.getSpeChangeNum()){
                                        speChangeNum = speChangeNum + example.getSpeChangeNum();
                                    }
                                }
                            }
                        }
                        personStaticExample.setChangeTimeNum(changeTimeNum);
                        personStaticExample.setSpeChangeNum(speChangeNum);

                    }
                    //不包含协同
                } else {
                    for (PersonChangeEntity personStaticExample : staticData) {
                        String orgFlow = personStaticExample.getOrgFlow();
                        Map<String, Object> parmMap = new HashMap<>();
                        parmMap.put("orgFlow", orgFlow);
                        parmMap.put("monthDate", lastMonthdate);//"201909"
                        parmMap.put("universityDoctorFlows", personStaticExample.getDoctorFlowsInOrg());//"201909"
                        List<PersonChangeEntity> staticExample = monthlyReportExtMapper2.selectFindChangeSpe(parmMap);
                        if(staticExample.size()>0){
                            for (PersonChangeEntity orgStatic : staticExample) {
                                if(null==orgStatic.getChangeTimeNum() || "".equals(orgStatic.getChangeTimeNum())){
                                    changeTimeNum = 0;
                                }else{
                                    changeTimeNum = orgStatic.getChangeTimeNum();
                                }
                                if(null==orgStatic.getSpeChangeNum() || "".equals(orgStatic.getSpeChangeNum())){
                                    speChangeNum = 0;
                                }else{
                                    speChangeNum = orgStatic.getSpeChangeNum();
                                }
                            }
                        }else{
                            changeTimeNum=0;
                            speChangeNum=0;
                        }
                        personStaticExample.setChangeTimeNum(changeTimeNum);
                        personStaticExample.setSpeChangeNum(speChangeNum);

                    }
                }
                    return staticData;
                }else{
                     throw new RuntimeException("请联系管理员“派送学校”是否有“"+currOrgName+"”高校");
                 }
        }catch (RuntimeException e){
            List<Map<String,String>> list=new ArrayList<>();
            Map<String,String> map=new HashMap<>();
            if(e.getMessage()==null){
                map.put("error","null");
            }else{
                map.put("error",e.getMessage());
            }
            list.add(map);
            return list;
        }
    }

    public List<SysOrg> resetOrgAppUserInfo(List<SysOrg> list){
        List<SysOrg> poList=new ArrayList<>();
        List<SysOrg> first=new ArrayList<>();
        List<SysOrg> second=new ArrayList<>();
        for(SysOrg aa:list){
            SysOrg s1=new SysOrg();
//                   List<SysOrg>  jointOrgList= orgBiz.searchJointOrgsByOrg(aa.getOrgFlow());
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
    public boolean isexistAppuserInfo(String orgflow,List<SysOrg> list){
        for(SysOrg doc:list){
            if(orgflow.equals(doc.getOrgFlow())){
                return true;
            }
        }
        return  false;
    }
}
