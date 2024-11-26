package com.pinde.sci.ctrl.jszy;

import com.pinde.core.model.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyResDoctorRecruitBiz;
import com.pinde.sci.biz.jszy.IJszyResOrgSpeBiz;
import com.pinde.sci.biz.jszy.IJszyResRecruitDoctorInfoBiz;
import com.pinde.sci.biz.jszy.IJszyResStatisticBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.jszy.JszyResDocTypeEnum;
import com.pinde.sci.enums.jszy.JszyResDoctorAuditStatusEnum;
import com.pinde.sci.enums.jszy.JszyTrainCategoryEnum;
import com.pinde.core.common.enums.ResDocTypeEnum;
import com.pinde.core.common.enums.ResRecTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.jszy.JszyDoctorInfoExt;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author PPBear
 *
 */
@Controller
@RequestMapping("/jszy/statistic")
public class JszyResStatisticController extends GeneralController {
    /**
     * @author PPBear
     */
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResJointOrgBiz resJointOrgBiz;
    @Autowired
    private IJszyResOrgSpeBiz resOrgSpeBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IJszyResDoctorRecruitBiz doctorRecruitBiz;
    @Autowired
    private IJszyResRecruitDoctorInfoBiz jszyResRecruitDoctorInfoBiz;
    @Autowired
    private ISchRotationDeptBiz schRotationDeptBiz;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private IJszyResStatisticBiz resStatisticBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;

    /**
     * 省级 或国家 基地的医师信息统计
     * @param model
     * @param sessionNumber
     * @param trainTypeId
     * @param orgLevel
     * @param datas
     * @return
     */
    @RequestMapping("/statisticCountryOrg")
    public String statisticCountryOrg(Model model,String sessionNumber,String trainTypeId,String orgLevel,String[] datas){
        Map<Object, Object> totalCountMap=new HashMap<Object,Object>();//保存每家基地的培训记录总数
        Map<Object, Object> joingCountMap=new HashMap<Object,Object>();//保存每家基地的协同基地的每个专业的总数
        Map<Object, Object> orgSpeFlagMap=new HashMap<Object,Object>();//基地专业标志的的map
        List<SysOrg> orgs=new ArrayList<SysOrg>();
        SysUser sysUser = GlobalContext.getCurrentUser();
        SysOrg org4serarch=new SysOrg();
        SysOrg sorg=orgBiz.readSysOrg(sysUser.getOrgFlow());
        org4serarch.setOrgProvId(sorg.getOrgProvId());
        org4serarch.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs=orgBiz.searchAllSysOrg(org4serarch);
        model.addAttribute("orgs", orgs);
        List<String>orgFlowList=null;
        List<String>docTypeList=new ArrayList<String>();
        List<String>jointOrgFlowList=null;
        ResDoctor doctor=new ResDoctor();
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }else{
            docTypeList.add(ResDocTypeEnum.Company.getId());
            docTypeList.add(ResDocTypeEnum.Social.getId());
            docTypeList.add(ResDocTypeEnum.Graduate.getId());
        }
        ResDoctorRecruit recruit=new ResDoctorRecruit();
        recruit.setSessionNumber(sessionNumber);
        recruit.setCatSpeId(trainTypeId);
        recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
        //查询所有国家基地
        List<SysOrg> sysOrgList=new ArrayList<SysOrg>();
        SysOrg org=new SysOrg();
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
            org.setOrgCityId(sorg.getOrgCityId());
        }
        if(StringUtil.isNotBlank(orgLevel)){
            org.setOrgLevelId(orgLevel);
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            sysOrgList=orgBiz.searchAllSysOrg(org);
        }else{
            sysOrgList=resJointOrgBiz.searchCouAndProList(org);
        }
        if(sysOrgList!=null&&!sysOrgList.isEmpty()){
            for(SysOrg o:sysOrgList){
                orgFlowList=new ArrayList<String>();
                jointOrgFlowList=new ArrayList<String>();
                ResOrgSpe resOrgSpe=new ResOrgSpe();
                resOrgSpe.setOrgFlow(o.getOrgFlow());
                resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
                List<ResOrgSpe> orgSpeList=resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
                if(orgSpeList!=null&&!orgSpeList.isEmpty()){//每家主基地的专业
                    for(ResOrgSpe r:orgSpeList){
                        orgSpeFlagMap.put(o.getOrgFlow()+r.getSpeTypeId()+r.getSpeId(),GlobalConstant.FLAG_Y);
                    }
                }
                orgFlowList.add(o.getOrgFlow());//查询每家基地及其协同基地的总数
                List<ResJointOrg>jointOrgList=jointOrgBiz.searchResJointByOrgFlow(o.getOrgFlow());//查询每家基地的协同基地
                if(jointOrgList!=null&&!jointOrgList.isEmpty()){
                    for(ResJointOrg resJointOrg:jointOrgList){
                        jointOrgFlowList.add(resJointOrg.getJointOrgFlow());
                        orgFlowList.add(resJointOrg.getJointOrgFlow());
                    }
                    List<Map<String, Object>>jointCountList=resStatisticBiz.statisticJointCount(recruit, jointOrgFlowList,doctor,docTypeList);
                    if(jointCountList!=null&&!jointCountList.isEmpty()){
                        for(Map<String,Object> en:jointCountList){
                            Object key=o.getOrgFlow()+en.get("key");
                            Object value= en.get("value");
                            joingCountMap.put(key, value);
                        }
                    }
                }
                //每家基地每个专业的医师培训记录总数
                List<Map<String,Object>> doctorCountList=resStatisticBiz.statisticJointCount(recruit,orgFlowList,doctor,docTypeList);
                if(doctorCountList!=null&&!doctorCountList.isEmpty()){
                    for(Map<String,Object> en:doctorCountList){
                        Object key=o.getOrgFlow()+en.get("key");
                        Object value= en.get("value");
                        totalCountMap.put(key, value);
                    }
                }
            }
        }
        model.addAttribute("joingCountMap", joingCountMap);
        model.addAttribute("datas", datas);
        model.addAttribute("totalCountMap", totalCountMap);
        model.addAttribute("orgSpeFlagMap", orgSpeFlagMap);
        model.addAttribute("sysOrgList", sysOrgList);
        return "/jszy/global/statistics/statisticCountryOrg";
    }

    /**
     *
     * @param model
     * @param recruit
     * @param doctor
     * @param orgLevel
     * @param trainTypeId
     * @param datas
     * @return
     */
    @RequestMapping("/statisticJointOrg")
    public String statisticJointOrg(Model model,ResDoctorRecruit recruit ,ResDoctor doctor,String orgLevel,String trainTypeId,String[] datas){
        Map<Object, Object> jointOrgListMap= new HashMap<Object, Object>();//每个符合添加的org的协同基地的map
        Map<Object, Object> jointOrgSpeMap = new HashMap<Object,Object>();//协同基地的spe
        Map<Object, Object> jointOrgDocCountMap = new HashMap<Object,Object>();//协同基地每个soe的总数
        List<SysOrg> sysOrgList=new ArrayList<SysOrg>();//存放所有的符合条件的sysOrg
        List<String> jointOrgFlowList=new ArrayList<String>();//存放所有的协同基地的orglist
        List<String>docTypeList=new ArrayList<String>();
        SysOrg org=new SysOrg();
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        SysOrg ss=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        org.setOrgProvId(ss.getOrgProvId());
        if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
            org.setOrgCityId(ss.getOrgCityId());
        }
        if(StringUtil.isBlank(orgLevel)){
            sysOrgList=resJointOrgBiz.searchCouAndProList(org);
        }else{
            org.setOrgLevelId(orgLevel);
            sysOrgList=orgBiz.searchAllSysOrg(org);
        }
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }else{
            docTypeList.add(ResDocTypeEnum.Company.getId());
            docTypeList.add(ResDocTypeEnum.Social.getId());
            docTypeList.add(ResDocTypeEnum.Graduate.getId());
        }
        recruit.setCatSpeId(trainTypeId);
        recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
        if(sysOrgList!=null&&!sysOrgList.isEmpty()){
            for(SysOrg s:sysOrgList ){//国家基地和省级基地的list
                List<ResJointOrg> jointOrgList=resJointOrgBiz.searchResJointByOrgFlow(s.getOrgFlow());//分别查出每个基地的协同基地
                if(jointOrgList!=null&&!jointOrgList.isEmpty()){
                    int c=0;
                    for(ResJointOrg re:jointOrgList){//循环协同基地
                        String flag="";
                        if(c==0){
                            flag=GlobalConstant.FLAG_Y;
                        }else if(c==1){
                            flag=GlobalConstant.FLAG_N;
                        }else if(c==2){
                            flag=GlobalConstant.FLAG_F;
                        }//标记
                        jointOrgListMap.put(s.getOrgFlow()+flag, re);//以当前的国家基地标记key   Map
                        jointOrgFlowList.add(re.getJointOrgFlow());
                        c++;
                    }
                }
            }
        }
        if(jointOrgFlowList!=null &&!jointOrgFlowList.isEmpty()){
            ResOrgSpe resOrgSpe=new ResOrgSpe();
            for(String s:jointOrgFlowList){
                resOrgSpe.setOrgFlow(s);//添加条件查询每个协同基地的专业
                String type="";
                if(StringUtil.isNotBlank(trainTypeId)){
                    resOrgSpe.setSpeTypeId(trainTypeId);
                }else if(StringUtil.isNotBlank(recruit.getSessionNumber())){
                    type=GlobalConstant.FLAG_Y;
                    if(Integer.parseInt(recruit.getSessionNumber())<2015){
                        //resOrgSpe.setSpeTypeId(JszyTrainCategoryEnum.DoctorTrainingSpe.getId());
                    }else{
                        //resOrgSpe.setSpeTypeId(JszyTrainCategoryEnum.WMFirst.getId());
                    }
                }
                List<ResOrgSpe> speList=resOrgSpeBiz.searchSpeByCondition(resOrgSpe,type);
                jointOrgSpeMap.put(s, speList);

            }
            recruit.setCatSpeId(trainTypeId);
            List<Map<String,Object>> doctorSpeList=resStatisticBiz.statisticDocCouByType(recruit, jointOrgFlowList, doctor,docTypeList);
            for(Map<String,Object> en:doctorSpeList){
                Object key= en.get("key");
                Object value= en.get("value");
                jointOrgDocCountMap.put(key, value);// Map 已协同基地的flow家伙是哪个spe作为key
            }
        }
        model.addAttribute("datas", datas);
        model.addAttribute("jointOrgDocCountMap", jointOrgDocCountMap);
        model.addAttribute("jointOrgListMap", jointOrgListMap);
        model.addAttribute("jointOrgSpeMap", jointOrgSpeMap);
        model.addAttribute("sysOrgList", sysOrgList);
        return "/jszy/global/statistics/statisticJointOrgInfo";
    }
    @RequestMapping("/exportExcel")
    public void exportExcel(String sessionNumber,String trainTypeId,String orgLevel,String[] datas,HttpServletResponse response)throws Exception{
        List<SysOrg> sysOrgList=new ArrayList<SysOrg>();//存放所有的符合条件的sysOrg
        List<SysDict> typeSpeList=new ArrayList<SysDict>();//存放每个培训类别的专业
        Map<Object, Object> totalCountMap=new HashMap<Object,Object>();//保存每家基地的培训记录总数
        Map<Object, Object> joingCountMap=new HashMap<Object,Object>();//保存每家基地的协同基地的每个专业的总数
        Map<Object, Object> orgSpeFlagMap=new HashMap<Object,Object>();//基地专业标志的的map
        Map<Object, Object> zongjiMap=new HashMap<Object, Object>();//统计小计
        List<String>orgFlowList=null;
        List<String>docTypeList=new ArrayList<String>();
        List<String>jointOrgFlowList=null;
        ResDoctor doctor=new ResDoctor();
        if(StringUtil.isNotBlank(trainTypeId)){
            typeSpeList=dictBiz.searchDictListByDictTypeId(trainTypeId);//每个培训类别对应的专业
        }
        SysOrg org=new SysOrg();
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        SysOrg ss=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        org.setOrgProvId(ss.getOrgProvId());
        if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
            org.setOrgCityId(ss.getOrgCityId());
        }
        if(StringUtil.isBlank(orgLevel)){
            sysOrgList=resJointOrgBiz.searchCouAndProList(org);//查询符合要求的基地
        }else{
            org.setOrgLevelId(orgLevel);
            sysOrgList=orgBiz.searchAllSysOrg(org);//查询符合要求的 基地
        }
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        ResDoctorRecruit recruit=new ResDoctorRecruit();
        recruit.setSessionNumber(sessionNumber);
        recruit.setCatSpeId(trainTypeId);
        recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
        if(sysOrgList!=null&&!sysOrgList.isEmpty()){
            for(SysOrg o:sysOrgList){
                orgFlowList=new ArrayList<String>();
                jointOrgFlowList=new ArrayList<String>();
                ResOrgSpe resOrgSpe=new ResOrgSpe();
                resOrgSpe.setOrgFlow(o.getOrgFlow());
                resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
                List<ResOrgSpe> orgSpeList=resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
                if(orgSpeList!=null&&!orgSpeList.isEmpty()){//每家主基地的专业
                    for(ResOrgSpe r:orgSpeList){
                        orgSpeFlagMap.put(o.getOrgFlow()+r.getSpeTypeId()+r.getSpeId(),GlobalConstant.FLAG_Y);
                    }
                }
                orgFlowList.add(o.getOrgFlow());//查询每家基地及其协同基地的总数
                List<ResJointOrg>jointOrgList=jointOrgBiz.searchResJointByOrgFlow(o.getOrgFlow());//查询每家基地的协同基地
                if(jointOrgList!=null&&!jointOrgList.isEmpty()){
                    for(ResJointOrg resJointOrg:jointOrgList){
                        jointOrgFlowList.add(resJointOrg.getJointOrgFlow());
                        orgFlowList.add(resJointOrg.getJointOrgFlow());
                    }
                    List<Map<String, Object>>jointCountList=resStatisticBiz.statisticJointCount(recruit, jointOrgFlowList,doctor,docTypeList);
                    if(jointCountList!=null&&!jointCountList.isEmpty()){
                        for(Map<String,Object> en:jointCountList){
                            Object key=o.getOrgFlow()+en.get("key");
                            Object value= en.get("value");
                            joingCountMap.put(key, value);
                        }
                    }
                }
                //每家基地每个专业的医师培训记录总数
                List<Map<String,Object>> doctorCountList=resStatisticBiz.statisticJointCount(recruit,orgFlowList,doctor,docTypeList);
                if(doctorCountList!=null&&!doctorCountList.isEmpty()){
                    for(Map<String,Object> en:doctorCountList){
                        Object key=o.getOrgFlow()+en.get("key");
                        Object value= en.get("value");
                        totalCountMap.put(key, value);
                    }
                }
            }
        }
        resStatisticBiz.export(sysOrgList, typeSpeList, trainTypeId, totalCountMap, zongjiMap, orgSpeFlagMap, joingCountMap, response);
    }
    @RequestMapping("/statisticsAppUser")
    public String statisticsAppUser(Model model,String orgFlow,String startTime,String endTime,String catSpeId,String sessionNumber){
        Map<Object, Object> percentMap=new HashMap<Object, Object>(); //存有百分比的map
        Map<Object, Object> countMap=new HashMap<Object, Object>(); //存有具体数值的map
        List<String> timeGapMon=new ArrayList<String>();//存放横坐标
        SysUser currUser=GlobalContext.getCurrentUser();
        ResRec resRec=new ResRec();
        ResDoctorRecruit recruit= new ResDoctorRecruit();
        if(StringUtil.isNotBlank(orgFlow)){
            recruit.setOrgFlow(orgFlow);
            resRec.setOrgFlow(orgFlow);
        }else{
            resRec.setOrgFlow(currUser.getOrgFlow());
            recruit.setOrgFlow(currUser.getOrgFlow());
        }
        if(StringUtil.isBlank(sessionNumber)){
            if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
                sessionNumber=InitConfig.getSysCfg("jsres_doctorCount_sessionNumber");
            }
        }
        if(StringUtil.isBlank(startTime)){
            if(StringUtil.isNotBlank(InitConfig.getSysCfg("res_reg_date"))&&StringUtil.isNotBlank(sessionNumber)){
                String endDate = Integer.parseInt(sessionNumber)-1+"-"+InitConfig.getSysCfg("res_reg_date");
                String[] date=DateUtil.addDate(endDate, 1).split("-");
                startTime = date[0]+date[1]+date[2];
            }else{
                startTime="201509";//开始时间
            }
        }
        if(StringUtil.isBlank(endTime)){
            if(StringUtil.isNotBlank(InitConfig.getSysCfg("res_reg_date"))&&StringUtil.isNotBlank(sessionNumber)){
                endTime= DateUtil.getCurrDate2().substring(0, 8);//结束时间
            }
        }
        model.addAttribute("sessionNumber",sessionNumber);
        recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
        if(StringUtil.isNotBlank(sessionNumber)) {
            recruit.setSessionNumber(sessionNumber);
        }
        if(StringUtil.isNotBlank(catSpeId)){
            recruit.setCatSpeId(catSpeId);
        }
        List<String> delTypeList = new ArrayList<String>();
        delTypeList.add(ResRecTypeEnum.Delay.getId());
        delTypeList.add(ResRecTypeEnum.ReturnTraining.getId());
        delTypeList.add(ResRecTypeEnum.DoctorAuth.getId());
        delTypeList.add(ResRecTypeEnum.AfterSummary.getId());
        delTypeList.add(ResRecTypeEnum.AfterEvaluation.getId());
        List<Map<String, Object>> docCountByOrg = resStatisticBiz.statisticDocCountByOrg(recruit,null);//每家医院的人数总数
        List<Map<String, Object>> appCountByOrg = resStatisticBiz.statisticAppCountByOrg(recruit,resRec,endTime,startTime,delTypeList);//统计每家医院实际使用app的人数
        Object orgTotal=0;//每家基地的总数
        if(docCountByOrg!=null&&!docCountByOrg.isEmpty()){
            orgTotal=docCountByOrg.get(0).get("value");
        }
        int year1=Integer.parseInt(startTime.substring(0, 4));
        int year2=Integer.parseInt(endTime.substring(0, 4));
        int  month1=Integer.parseInt(startTime.substring(4, 6));
        int  month2=Integer.parseInt(endTime.substring(4, 6));
//		DecimalFormat df = new DecimalFormat("0.00%");
        if((year2>year1)||(year2==year1&&month2>=month1)){//算出两个时间相差的月份
            for (int i = year1; i <= year2; i++) {
                int monthCount = 12;
                int monthStart = 1;
                if (i == year1) {
                    monthStart = month1;
                    if(year1==year2){
                        if(month1!=month2){
                            monthCount = 12-monthStart;
                        }else{
                            monthCount=1;
                        }
                    }else{
                        monthCount=12-monthStart+1;
                    }
                } else if (i == year2) {
                    monthCount = month2;
                }
                for(int j = 0; j < monthCount; j++){
                    int temp = monthStart+j;
                    String mark="";
                    String flag="";
                    if(temp >=10){
                        mark=i+""+(monthStart+j);
                    }else{
                        mark=i+""+("0"+(monthStart+j));
                    }
                    timeGapMon.add(mark);//存放横坐标
                    for(Map<String, Object> en :appCountByOrg){
                        if(en.get("key").equals(mark)){
                            Object key=en.get("key");
                            Object value=en.get("value");
//					        String r = df.format((Double.parseDouble(value+"")/Double.parseDouble(orgTotal+"")));
                            percentMap.put(key, (Double.parseDouble(value+"")/Double.parseDouble(orgTotal+"")));
                            countMap.put(key, value);
                            flag=GlobalConstant.FLAG_Y;
                        }
                    }
                    if(StringUtil.isBlank(flag)){//查询中没有这条记录
                        Object key= mark;
                        Object value=0;
//						String r= df.format(Double.parseDouble(value+""));
                        percentMap.put(key, Double.parseDouble(value+""));
                        countMap.put(key, value);
                    }
                }

            }
        }
        model.addAttribute("timeGapMon", timeGapMon);
        model.addAttribute("percentMap", percentMap);
        model.addAttribute("countMap", countMap);
        return "/jszy/hospital/statistics/statisticsAppUser";
    }
    @RequestMapping("/statisticsNoAppUser")
    public String statisticsNoAppUser(Model model,String orgFlow,String catSpeId,Integer currentPage,HttpServletRequest request,String startDate,String endDate,String sessionNumber){
        SysUser currUser = GlobalContext.getCurrentUser();
        if(StringUtil.isBlank(orgFlow)){
            orgFlow = currUser.getOrgFlow();
        }
        if(StringUtil.isNotBlank(startDate)){
            String[] time = startDate.split("-");
            startDate=time[0]+time[1];
        }
        if(StringUtil.isNotBlank(endDate)){
            String[] time = endDate.split("-");
            endDate = time[0]+time[1];
        }
        ResRec resRec = new ResRec();
        resRec.setOrgFlow(orgFlow);
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setOrgFlow(orgFlow);
        if(StringUtil.isBlank(sessionNumber)){
            if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
                recruit.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
                sessionNumber=InitConfig.getSysCfg("jsres_doctorCount_sessionNumber");
            }
        }else {
            recruit.setSessionNumber(sessionNumber);
        }
        if(StringUtil.isNotBlank(catSpeId)){
            recruit.setCatSpeId(catSpeId);
        }
        recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
        List<String> delTypeList = new ArrayList<String>();
        delTypeList.add(ResRecTypeEnum.Delay.getId());
        delTypeList.add(ResRecTypeEnum.ReturnTraining.getId());
        delTypeList.add(ResRecTypeEnum.DoctorAuth.getId());
        delTypeList.add(ResRecTypeEnum.AfterSummary.getId());
        delTypeList.add(ResRecTypeEnum.AfterEvaluation.getId());
        PageHelper.startPage(currentPage, getPageSize(request));
        List<JszyDoctorInfoExt> docInfoExtsList =resStatisticBiz.statisticNoAppUser(recruit, resRec, delTypeList,startDate,endDate);
        model.addAttribute("docInfoExtsList", docInfoExtsList);
        return "/jszy/hospital/statistics/noAppUsers";
    }
    @RequestMapping("/statisticsAppUserForOrg")
    public String statisticsAppUserForOrg(Model model,String startTime,String endTime,String catSpeId,String sessionNumber){
        List<SysOrg> sysOrgList =new ArrayList<SysOrg>();//所有国家基地和省级基地
        List<String> jointOrgFlowList=new ArrayList<String>();//存放所有的协同基地的orglist
        List<String> orgFlowList=new ArrayList<String>();//主基地的orglist
        Map<Object, Object> percentMap=new HashMap<Object,Object>();//存放百分比的map
        Map<Object, Object> jointPercentMap=new HashMap<Object,Object>();//xietong存放百分比的map
        Map<Object, Object> jointOrgListMap= new HashMap<Object, Object>();//每个符合添加的org的协同基地的map
        SysOrg org=new SysOrg();
        SysOrg ss=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        org.setOrgProvId(ss.getOrgProvId());
        if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
            org.setOrgCityId(ss.getOrgCityId());
        }
        sysOrgList=resJointOrgBiz.searchCouAndProList(org);//省级基地和国家基地
        if(sysOrgList!=null&&!sysOrgList.isEmpty()){
            for(SysOrg s:sysOrgList ){//国家基地和省级基地的list
                List<ResJointOrg> jointOrgList=resJointOrgBiz.searchResJointByOrgFlow(s.getOrgFlow());//分别查出每个基地的协同基地
                if(jointOrgList!=null&&!jointOrgList.isEmpty()){
                    jointOrgListMap.put(s.getOrgFlow(), jointOrgList);//以当前的国家基地标记key存放一个list   Map
                    for(ResJointOrg re:jointOrgList){//循环协同基地
                        jointOrgFlowList.add(re.getJointOrgFlow());
                    }
                }
                orgFlowList.add(s.getOrgFlow());//存放主基地的orgFlow
            }
        }
//		if(StringUtil.isBlank(startTime)){
//			startTime="201509";
//		}if(StringUtil.isBlank(endTime)){
//			endTime=DateUtil.getCurrDate2().substring(0,6);
//		}
        String month = DateUtil.getCurrDate2().substring(0, 6);
        ResDoctorRecruit recruit=new ResDoctorRecruit();
        if(StringUtil.isBlank(sessionNumber)){
            if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
                recruit.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
                sessionNumber=InitConfig.getSysCfg("jsres_doctorCount_sessionNumber");
            }
        }else {
            recruit.setSessionNumber(sessionNumber);
        }
        model.addAttribute("sessionNumber",sessionNumber);
        if(StringUtil.isNotBlank(catSpeId)){
            recruit.setCatSpeId(catSpeId);
        }
        List<String> delTypeList = new ArrayList<String>();
        delTypeList.add(ResRecTypeEnum.DoctorAuth.getId());
        delTypeList.add(ResRecTypeEnum.Delay.getId());
        delTypeList.add(ResRecTypeEnum.ReturnTraining.getId());
        delTypeList.add(ResRecTypeEnum.AfterSummary.getId());
        delTypeList.add(ResRecTypeEnum.AfterEvaluation.getId());
        recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
        List<Map<String, Object>> docCountByOrg = resStatisticBiz.statisticDocCountByOrgForTime(null, orgFlowList, startTime, endTime,recruit);//主基地每家医院的人数总数
        List<Map<String, Object>> appCountByOrg = resStatisticBiz.statisticRealAppCount(delTypeList,recruit,endTime, startTime, orgFlowList, null,month);//统计主基地每家医院实际使用app的人数
        DecimalFormat df = new DecimalFormat("0.00%");
        if(sysOrgList!=null&&!sysOrgList.isEmpty()){
            for(SysOrg s:sysOrgList){
                double total=0;double real=0;
                for(Map<String, Object> en:docCountByOrg){
                    if(s.getOrgFlow().equals(en.get("key"))){
                        total=Double.parseDouble(en.get("value")+"");
                    }
                }
                for(Map<String, Object> en:appCountByOrg){
                    if(s.getOrgFlow().equals(en.get("key"))){
                        real=Double.parseDouble(en.get("value")+"");
                    }
                }
                double average=real/total;
                if(total==0||average==0||real==0){
                    percentMap.put(s.getOrgFlow(),0+"%");
                }else{
                    percentMap.put(s.getOrgFlow(),df.format(average));
                }
            }
        }
        if(jointOrgFlowList!=null &&!jointOrgFlowList.isEmpty()){//所有的协同基地
            List<Map<String, Object>> jointDocCountByOrg = resStatisticBiz.statisticDocCountByOrgForTime(GlobalConstant.FLAG_Y, orgFlowList, startTime, endTime,recruit);//协同基地基地每家医院的人数总数
            List<Map<String, Object>> JointAppCountByOrg = resStatisticBiz.statisticRealAppCount(delTypeList,recruit,endTime, startTime, orgFlowList, GlobalConstant.FLAG_Y,month);//统计协同基地每家医院实际使用app的人数
            for(String s:jointOrgFlowList){//循环list
                double t=0;double r=0;
                for(Map<String, Object> en:jointDocCountByOrg){
                    if(s.equals(en.get("key"))){
                        t=Double.parseDouble(en.get("value")+"");
                    }
                }
                for(Map<String, Object> en:JointAppCountByOrg){
                    if(s.equals(en.get("key"))){
                        r=Double.parseDouble(en.get("value")+"");
                    }
                }
                double a=r/t;
                if(t==0||a==0){
                    jointPercentMap.put(s,0+"%");
                }else{
                    jointPercentMap.put(s,df.format(a));
                }
            }
        }
        model.addAttribute("sysOrgList", sysOrgList);
        model.addAttribute("percentMap", percentMap);
        model.addAttribute("jointPercentMap", jointPercentMap);
        model.addAttribute("jointOrgListMap", jointOrgListMap);
        return "/jszy/global/statistics/statisticsAppUserForOrg";
    }
    //师资培训统计
    @RequestMapping("/searchTeachTrain")
    public String searchTeachTrain(Model model,ResTeacherTraining teacherTraining,HttpServletRequest request,Integer currentPage){
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResTeacherTraining> teacherTrainingList =resStatisticBiz.searchTeacherInfo(teacherTraining);
        model.addAttribute("teacherTrainingList", teacherTrainingList);
        List<SysOrg> orgs=new ArrayList<SysOrg>();
        SysOrg org=new SysOrg();
        SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs=orgBiz.searchAllSysOrg(org);
        model.addAttribute("orgs", orgs);
        return "/jszy/global/statistics/searchTeachTrain";
    }
    @RequestMapping("/leadTo")
    public String leadTo(Model model){
        return "/jszy/global/statistics/teacherDaoRu";
    }
    @RequestMapping("/importTeacherExcel")
    @ResponseBody
    public Map<String, Object> importTeacherExcel(MultipartFile file){
        Map<String, Object> resultMap = new HashMap<String,Object>();
        if(file.getSize() > 0){
            try{
                resultMap =resStatisticBiz.importTeacherExcel(file);
            }catch(RuntimeException re){
                re.printStackTrace();
            }

        }
        return resultMap;
    }
    @RequestMapping("/deleteInfo")
    @ResponseBody
    public String deleteInfo(String recordFlow){
        if(StringUtil.isNotBlank(recordFlow))
        {
            ResTeacherTraining teacherTraining=resStatisticBiz.searchTeacherInfoByPK(recordFlow);
            if(teacherTraining!=null) {
                teacherTraining.setRecordStatus(GlobalConstant.FLAG_N);
                int count=resStatisticBiz.editTeacherInfo(teacherTraining);
                if(count==GlobalConstant.ONE_LINE) {
                    return GlobalConstant.DELETE_SUCCESSED;
                }

            }
        }
        return GlobalConstant.DELETE_FAIL;
    }
    @RequestMapping("/saveTeacher")
    @ResponseBody
    public String saveTeacher(ResTeacherTraining teacherTraining){
        int count=resStatisticBiz.save(teacherTraining);
        if(count==GlobalConstant.ONE_LINE) {
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPRE_FAIL;
    }
    @RequestMapping("/editInfo")
    public String editInfo(String recordFlow,Model model){
        if(StringUtil.isNotBlank(recordFlow))
        {
            ResTeacherTraining teacherTraining=resStatisticBiz.searchTeacherInfoByPK(recordFlow);
            model.addAttribute("teacher",teacherTraining);
        }
        List<SysOrg> orgs=new ArrayList<SysOrg>();
        SysOrg org=new SysOrg();
        SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs=orgBiz.searchAllSysOrg(org);
        model.addAttribute("orgs", orgs);
        return "/jszy/global/statistics/editTeacherInfo";
    }
    /**
     * 省厅招录统计
     * @param sessionNumber
     * @return
     */
    @RequestMapping(value={"/zlxytj"})
    public String zlxytj(String sessionNumber,Model model,String trainTypeIds[],String[] trainYears){

        List<Map<String,String>> citys=getCityList();
        Map<String,Object> param = new HashMap<>();
        List<String> trainTypeIdList = new ArrayList<String>();
        if (trainTypeIds != null && trainTypeIds.length > 0) {
            for (String s : trainTypeIds) {
                trainTypeIdList.add(s);
            }
        } else {
            trainTypeIds = new String[JszyTrainCategoryEnum.values().length];
            int i = 0;
            for (JszyTrainCategoryEnum e : JszyTrainCategoryEnum.values()) {
                if(GlobalConstant.FLAG_Y.equals(e.getIsView())){
                    trainTypeIdList.add(e.getId());
                    trainTypeIds[i++] = e.getId();
                }
            }
        }
        param.put("trainTypeIdList", trainTypeIdList);
        param.put("sessionNumber", sessionNumber);
        param.put("trainYears", trainYears);
        model.addAttribute("trainTypeIds", trainTypeIds);
        List<Map<String,Object>> list=doctorRecruitBiz.zlxytj(param);
        Map<String,List<String>> citySessionMap=new HashMap<>();
        Map<String,Object> citySessionNumMap=new HashMap<>();
        Map<String,Integer> cityNumMap=new HashMap<>();
        Map<String,Integer> typeNumMap=new HashMap<>();
        if(list!=null)
        {
            for(Map<String,Object> map:list)
            {
                //每个城市有多个届别
                String cityId= (String) map.get("cityId");
                String sessionNumber2= (String) map.get("sessionNumber");
                String typeId= (String) map.get("typeId");
                List<String> citySessionNumbers=citySessionMap.get(cityId);
                if(citySessionNumbers==null)
                    citySessionNumbers=new ArrayList<>();
                if(!citySessionNumbers.contains(sessionNumber2))
                {
                    citySessionNumbers.add(sessionNumber2);
                }
                citySessionMap.put(cityId,citySessionNumbers);

                citySessionNumMap.put(cityId+sessionNumber2+typeId,map.get("num"));

                Integer sum=cityNumMap.get(cityId);
                if(sum==null)
                    sum=0;
                sum+=(Integer) map.get("num");
                cityNumMap.put(cityId,sum);

                Integer sum2=typeNumMap.get(typeId);
                if(sum2==null)
                    sum2=0;
                sum2+=(Integer) map.get("num");
                typeNumMap.put(typeId,sum2);

            }
        }
        model.addAttribute("citySessionMap",citySessionMap);
        model.addAttribute("citySessionNumMap",citySessionNumMap);
        model.addAttribute("cityNumMap",cityNumMap);
        model.addAttribute("typeNumMap",typeNumMap);
        model.addAttribute("trainYears",trainYears);
        model.addAttribute("citys",citys);
        return "jszy/global/statistics/zlxytj";
    }

    @RequestMapping(value={"/zlxytj2"})
    public String zlxytj2(String orgCityId,String joint,String trainTypeId,String datas[],String sessionDatas[],String[] trainYears, Model model){
        List<Map<String,String>> citys=getCityList();
        //默认统计近三年
        List<String> sessionNumbers = new ArrayList<>() ;
        String currentYear = DateUtil.getYear();
        Integer year1 = Integer.parseInt(currentYear) - 1;
        Integer year2 = Integer.parseInt(currentYear) - 2;
        sessionNumbers.add(year2+"");
        sessionNumbers.add(year1+"");
        sessionNumbers.add(currentYear);
        if(sessionDatas == null){
            sessionDatas = new String[1];
            sessionDatas[0]=sessionNumbers.get(1);
        }
        List<String>docTypeList=new ArrayList<String>();
        ResDoctor doctor=new ResDoctor();
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }else{
            datas=new String[JszyResDocTypeEnum.values().length];
            int i=0;
            for(JszyResDocTypeEnum e:JszyResDocTypeEnum.values())
            {
                docTypeList.add(e.getId());
                datas[i++]=e.getId();
            }
        }
        model.addAttribute("datas", datas);
        Map<String,Object> param=new HashMap<>();
        //人员类型
        param.put("docTypeList",docTypeList);
        param.put("joint",joint);
        param.put("sessionNumbers",sessionDatas);
        param.put("trainYears",trainYears);
        param.put("trainTypeId",trainTypeId);
        List<SysOrg> orgs=orgBiz.queryAllSysOrg(new SysOrg());
        Map<String,String> orgNameMap=new HashMap<>();
        Map<String,List<String>> cityOrgMap=new HashMap<>();
        Map<Object, Object> orgSpeFlagMap=new HashMap<Object,Object>();//基地专业标志的的map
        Map<String,List<String>> jointOrgMap=new HashMap<>();
        List<String> jointOrgFlows=new ArrayList<String>();
        Map<String,Integer> typeNumMap=new HashMap<>();
        if(orgs!=null)
        {
            for(SysOrg o:orgs)
            {
                orgNameMap.put(o.getOrgFlow(),o.getOrgName());
                if(OrgLevelEnum.CountryOrg.getId().equals(o.getOrgLevelId()))
                {
                    List<String> orgFlows=cityOrgMap.get(o.getOrgCityId());
                    if(orgFlows==null)
                        orgFlows=new ArrayList<>();
                    if(!orgFlows.contains(o.getOrgFlow()))
                    {
                        orgFlows.add(o.getOrgFlow());
                    }
                    cityOrgMap.put(o.getOrgCityId(),orgFlows);

                    if(GlobalConstant.FLAG_Y.equals(joint))
                    {
                        List<String> jointOrgFlowList=new ArrayList<>();
                        List<SysOrg>jointOrgList=orgBiz.searchJointOrgsByOrg(o.getOrgFlow());//查询每家基地的协同基地
                        if(jointOrgList!=null&&!jointOrgList.isEmpty()){
                            for(SysOrg resJointOrg:jointOrgList){
                                if(!jointOrgFlowList.contains(resJointOrg.getOrgFlow()))
                                {
                                    jointOrgFlowList.add(resJointOrg.getOrgFlow());
                                    jointOrgFlows.add(resJointOrg.getOrgFlow());
                                }
                            }
                        }
                        jointOrgMap.put(o.getOrgFlow(),jointOrgFlowList);
                    }
                }

                ResOrgSpe resOrgSpe=new ResOrgSpe();
                resOrgSpe.setOrgFlow(o.getOrgFlow());
                resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
                List<ResOrgSpe> orgSpeList=resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
                if(orgSpeList!=null&&!orgSpeList.isEmpty()){//每家基地的专业
                    for(ResOrgSpe r:orgSpeList){
                        orgSpeFlagMap.put(o.getOrgFlow()+r.getSpeId(),GlobalConstant.FLAG_Y);
                    }
                }
            }
        }
        model.addAttribute("orgNameMap",orgNameMap);
        model.addAttribute("trainYears",trainYears);
        model.addAttribute("orgSpeFlagMap",orgSpeFlagMap);


        List<Map<String,Object>> list=doctorRecruitBiz.zlxytj2(param);
        Map<String,Object> cityOrgNumMap=new HashMap<>();
        if(list!=null)
        {
            for(Map<String,Object> map:list)
            {
                String orgFlow= (String) map.get("orgFlow");
                String speId= (String) map.get("speId");
                String sessionNumber= (String) map.get("sessionNumber");
                cityOrgNumMap.put(orgFlow+speId+sessionNumber,map.get("num"));

                Integer sum2=typeNumMap.get(speId);
                if(sum2==null)
                    sum2=0;
                sum2+=(Integer) map.get("num");
                typeNumMap.put(speId,sum2);

                if(GlobalConstant.FLAG_Y.equals(joint))
                {
                    param.put("orgFlow",orgFlow);
                    List<Map<String,Object>> list2=doctorRecruitBiz.zlxytjJoint(param);
                    if(list2!=null) {
                        for (Map<String, Object> map2 : list2) {
                            String jointOrgFlow = (String) map2.get("jointOrgFlow");
                            String speId2 = (String) map2.get("speId");
                            String sessionNumber2= (String) map2.get("sessionNumber");
                            cityOrgNumMap.put( jointOrgFlow +speId2+sessionNumber2, map2.get("num"));
                        }
                    }
                }
            }
        }
        List<String> subKeyList = new ArrayList<>();
        subKeyList.add("3501");
        subKeyList.add("3502");
        subKeyList.add("3601");
        subKeyList.add("3602");
        subKeyList.add("6000");
        List<String> orgFlows = new ArrayList<>();
        for (Map city :citys) {
            List<String> cityOrgs = cityOrgMap.get(city.get("cityId"));
            if(cityOrgs == null || cityOrgs.size()== 0) continue;
            for (String o:cityOrgs){
                orgFlows.add(o);
            }
        }

        model.addAttribute("jointOrgMap",jointOrgMap);
        model.addAttribute("typeNumMap",typeNumMap);
        model.addAttribute("orgFlows",orgFlows);
        model.addAttribute("jointOrgFlows",jointOrgFlows);
        model.addAttribute("sessionNumbers",sessionNumbers);
        model.addAttribute("sessionDatas",sessionDatas);
        model.addAttribute("subKeyList",subKeyList);
        model.addAttribute("cityOrgNumMap",cityOrgNumMap);
        return "jszy/global/statistics/zlxytj2";
    }

    @RequestMapping(value={"/zltjSecondSpe"})
    public String zltjSecondSpe(String orgFlow,String speId,String  sessionNumber,String[] datas,String[] trainYears, Model model){
        List<String> docTypeList=new ArrayList<String>();
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }else{
            datas=new String[JszyResDocTypeEnum.values().length];
            int i=0;
            for(JszyResDocTypeEnum e:JszyResDocTypeEnum.values())
            {
                docTypeList.add(e.getId());
                datas[i++]=e.getId();
            }
        }
        model.addAttribute("datas", datas);

        Map<String,Object> param= new HashMap<String,Object>();
        param.put("orgFlow",orgFlow);
        param.put("speId",speId);
        param.put("sessionNumber",sessionNumber);
        param.put("docTypeList",docTypeList);
        param.put("trainYears",trainYears);

        List<Map<String, Object>> secondSpeNum = jszyResRecruitDoctorInfoBiz.zltjSecondSpe(param);
        model.addAttribute("secondSpeNum",secondSpeNum);
        return "jszy/global/statistics/zltjSecondSpe";
    }


    @RequestMapping(value = {"/zlxytj3"})
    public String zlxytj3(String sessionNumber, String orgCityId, String joint, String trainTypeIds[], String TCMAssiGeneral, String orgFlow,
                          Integer currentPage, HttpServletRequest request, String datas[], Model model) {
        //获取城市列表
        List<Map<String,String>> citys=getCityList();
        //组装入参
        Map<String,Object>  param = assembleZlxytj3Param(sessionNumber,orgCityId,joint,trainTypeIds,orgFlow,datas,model);
        List<Map<String, Object>> list = doctorRecruitBiz.zlxytj3(param);
        if (list != null && list.size() > 0) {
            Map<String,String> countInfoMap = new HashMap<>();
            for(Map<String,Object> tempMap : list){
                String jointOrgFlow = tempMap.get("jointOrgFlow").toString();
                String catSpeId = tempMap.get("catSpeId").toString();
                String speId = tempMap.get("speId").toString();
                String secondSpeId = tempMap.get("secondSpeId").toString();
                String count = tempMap.get("num").toString();
                if(JszyTrainCategoryEnum.TCMAssiGeneral.getId().equals(catSpeId)){
                    countInfoMap.put(jointOrgFlow + catSpeId, count);
                }else if(JszyTrainCategoryEnum.TCMGeneral.getId().equals(catSpeId)){
                    //中医全科下经产品确认修改为不含二级专业
                    String tCMGeneralCount = countInfoMap.get(jointOrgFlow + catSpeId);
                    if(tCMGeneralCount == null){
                        countInfoMap.put(jointOrgFlow + catSpeId + speId, count);
                    }else {
                        countInfoMap.put(jointOrgFlow + catSpeId + speId, Integer.parseInt(count) + Integer.parseInt(tCMGeneralCount) + "");
                    }
                }else {
                    countInfoMap.put(jointOrgFlow + catSpeId + speId + secondSpeId, count);
                }
            }
            model.addAttribute("countInfoMap",countInfoMap);
        }
        //查询基地
        SysOrg searchOrg = new SysOrg();
        searchOrg.setOrgProvId("320000");
        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        searchOrg.setRecordStatus(GlobalConstant.FLAG_Y);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysOrg> orgs = orgBiz.searchOrg(searchOrg);
        model.addAttribute("orgs", orgs);
        Map<String, List<String>> jointOrgMap = new HashMap<>();
        if (orgs != null) {
            for (SysOrg o : orgs) {
                if (OrgLevelEnum.CountryOrg.getId().equals(o.getOrgLevelId())) {
                    if (GlobalConstant.FLAG_Y.equals(joint)) {
                        List<String> jointOrgFlowList = new ArrayList<>();
                        List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(o.getOrgFlow());//查询每家基地的协同基地
                        if (jointOrgList != null && !jointOrgList.isEmpty()) {
                            for (SysOrg resJointOrg : jointOrgList) {
                                if (!jointOrgFlowList.contains(resJointOrg.getOrgFlow())) {
                                    jointOrgFlowList.add(resJointOrg.getOrgFlow());
                                }
                            }
                        }
                        jointOrgMap.put(o.getOrgFlow(), jointOrgFlowList);
                    }
                }
            }
        }

        return "jszy/global/statistics/zlxytj3";
    }

    @RequestMapping(value = {"/zlxytj4"})
    public String zlxytj4(String sessionNumber, Integer currentPage, HttpServletRequest request,String datas[] ,Model model) {

        //组装查询入参
        Map<String, Object> param = assembleZlxytj4Param(sessionNumber,datas,model);
        List<Map<String, Object>> list = doctorRecruitBiz.zlxytj4(param);
        if (list != null && list.size() > 0) {
            Map<String, String> countInfoMap = new HashMap<>();
            for (Map<String, Object> tempMap : list) {
                String orgFlow = tempMap.get("jointOrgFlow").toString();
                String catSpeId = tempMap.get("catSpeId").toString();
                String count = tempMap.get("num").toString();
                countInfoMap.put(orgFlow + catSpeId, count);
            }
            model.addAttribute("countInfoMap", countInfoMap);
        }
        //查询基地
        SysOrg searchOrg = new SysOrg();
        searchOrg.setOrgProvId("320000");
        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        searchOrg.setRecordStatus(GlobalConstant.FLAG_Y);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysOrg> orgs = orgBiz.searchOrg(searchOrg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("datas", datas);


        return "jszy/global/statistics/zlxytj4";
    }
    @RequestMapping(value = {"/zlxytj5"})
    public String zlxytj5(String sessionNumber, Integer currentPage, HttpServletRequest request,String datas[] ,String[] trainYears,String cityId,String sorgFlow ,Model model) {

        //组装查询入参
        Map<String, Object> param = assembleZlxytj4Param(sessionNumber,datas,model);
        param.put("cityId",cityId);
        param.put("orgFlow",sorgFlow);
        param.put("trainYears",trainYears);
        List<Map<String, Object>> list = doctorRecruitBiz.zlxytj4(param);
        if (list != null && list.size() > 0) {
            Map<String, String> countInfoMap = new HashMap<>();
            for (Map<String, Object> tempMap : list) {
                String orgFlow = tempMap.get("orgFlow").toString();
                String catSpeId = tempMap.get("catSpeId").toString();
                String count = tempMap.get("num").toString();
                countInfoMap.put(orgFlow + catSpeId, count);
            }
            model.addAttribute("countInfoMap", countInfoMap);
        }
        //查询基地
        SysOrg searchOrg = new SysOrg();
        if(StringUtil.isNotEmpty(cityId)){
            searchOrg.setOrgCityId(cityId);
        }
        if(StringUtil.isNotEmpty(sorgFlow)){
            searchOrg.setOrgFlow(sorgFlow);
        }
        searchOrg.setOrgProvId("320000");
        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        searchOrg.setRecordStatus(GlobalConstant.FLAG_Y);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysOrg> orgs = orgBiz.searchOrg(searchOrg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("datas", datas);
        model.addAttribute("trainYears", trainYears);
        model.addAttribute("cityId",cityId);
        model.addAttribute("orgFlow",sorgFlow);


        return "jszy/global/statistics/zlxytj5";
    }

    /**
     * 省厅招录统计
     * @param sessionNumber
     * @return
     */
    @RequestMapping(value={"/exportZlxytj"})
    public void exportZlxytj(String sessionNumber,HttpServletResponse response,String trainTypeIds[],String[] trainYears) throws IOException {

        List<Map<String,String>> citys=getCityList();

        Map<String,Object> param = new HashMap<>();
        List<String> trainTypeIdList = new ArrayList<String>();
        if (trainTypeIds != null && trainTypeIds.length > 0) {
            for (String s : trainTypeIds) {
                trainTypeIdList.add(s);
            }
        } else {
            trainTypeIds = new String[JszyTrainCategoryEnum.values().length];
            int i = 0;
            for (JszyTrainCategoryEnum e : JszyTrainCategoryEnum.values()) {
                if(GlobalConstant.FLAG_Y.equals(e.getIsView())){
                    trainTypeIdList.add(e.getId());
                    trainTypeIds[i++] = e.getId();
                }
            }
        }
        param.put("trainTypeIdList", trainTypeIdList);
        param.put("sessionNumber", sessionNumber);
        param.put("trainYears", trainYears);
        List<Map<String, Object>> list = doctorRecruitBiz.zlxytj(param);
        Map<String, List<String>> citySessionMap = new HashMap<>();
        Map<String, Object> citySessionNumMap = new HashMap<>();
        Map<String, Integer> cityNumMap = new HashMap<>();
        Map<String, Integer> typeNumMap = new HashMap<>();
        if (list != null) {
            for (Map<String, Object> map : list) {
                //每个城市有多个届别
                String cityId = (String) map.get("cityId");
                String sessionNumber2 = (String) map.get("sessionNumber");
                String typeId = (String) map.get("typeId");
                List<String> citySessionNumbers = citySessionMap.get(cityId);
                if (citySessionNumbers == null)
                    citySessionNumbers = new ArrayList<>();
                if (!citySessionNumbers.contains(sessionNumber2)) {
                    citySessionNumbers.add(sessionNumber2);
                }
                citySessionMap.put(cityId, citySessionNumbers);

                citySessionNumMap.put(cityId + sessionNumber2 + typeId, map.get("num"));

                Integer sum = cityNumMap.get(cityId);
                if (sum == null)
                    sum = 0;
                sum += (Integer) map.get("num");
                cityNumMap.put(cityId, sum);

                Integer sum2 = typeNumMap.get(typeId);
                if (sum2 == null)
                    sum2 = 0;
                sum2 += (Integer) map.get("num");
                typeNumMap.put(typeId, sum2);

            }
        }

        String[] titles = new String[]{
                "地市名称", "年份", "本单位人", "委培单位人", "行业人", "在校专硕", "小计", "合计",
        };
        String fileName = "住院医师招录统计表.xls";
        fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        OutputStream os = response.getOutputStream();
        Workbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        Sheet sheet = wb.createSheet("sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        //HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        CellStyle style = wb.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        style.setFont(font);
        Font fontTwo = wb.createFont();
        fontTwo.setFontHeightInPoints((short) 12);

        CellStyle styleTwo = wb.createCellStyle();
        styleTwo.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
        styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        styleTwo.setFont(fontTwo);
        //

        Map<Integer, Integer> columnWidth = new HashMap<>();
        Row row = sheet.createRow(0);
        Cell cell = null;
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            cell = row.createCell(i);
            cell.setCellValue(title);
            cell.setCellStyle(style);
            //宽度自适应
            int nl = title.toString().getBytes().length;
            if (columnWidth.containsKey(i)) {
                Integer ol = columnWidth.get(i);
                if (ol < nl)
                    columnWidth.put(i, nl);
            } else {
                columnWidth.put(i, nl);
            }
        }
        if (citys != null) {
            Cell rowCell = null;
            int rowNum = 1;
            int xjsum = 0;
            int hjsum = 0;
            for (int i = 0; i < citys.size(); i++) {
                Map<String, String> item = citys.get(i);
                String cityName = item.get("cityName");
                String cityId = item.get("cityId");
                List<String> sessionNumbers = citySessionMap.get(cityId);
                if (sessionNumbers != null && sessionNumbers.size() > 0) {
                    for (int j = 0; j < sessionNumbers.size(); j++) {

                        row = sheet.createRow(rowNum++);
                        String sn = sessionNumbers.get(j);
                        if (j == 0) {
                            rowCell = row.createCell(0);
                            rowCell.setCellStyle(styleTwo);
                            rowCell.setCellValue(cityName);
                            //宽度自适应
                            setColumnWidth(cityName.toString().getBytes().length, 0, columnWidth);
                            sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, sessionNumbers.size() - 1+rowNum - 1, 0, 0));
                        }
                        rowCell = row.createCell(1);
                        rowCell.setCellStyle(styleTwo);
                        rowCell.setCellValue(sn);
                        //宽度自适应
                        setColumnWidth(sn.toString().getBytes().length, 1, columnWidth);
                        int k = 2;
                        int sum = 0;
                        for (JszyResDocTypeEnum e : JszyResDocTypeEnum.values()) {
                            String key = cityId + sn + e.getId();
                            Integer num = (Integer) citySessionNumMap.get(key);
                            if (num == null)
                                num = 0;
                            sum += num;
                            rowCell = row.createCell(k);
                            rowCell.setCellStyle(styleTwo);
                            rowCell.setCellValue(String.valueOf(num));
                            //宽度自适应
                            setColumnWidth(String.valueOf(num).toString().getBytes().length, k, columnWidth);
                            k++;
                        }
                        rowCell = row.createCell(k);
                        rowCell.setCellStyle(styleTwo);
                        rowCell.setCellValue(String.valueOf(sum));
                        xjsum += sum;
                        //宽度自适应
                        setColumnWidth(String.valueOf(sum).toString().getBytes().length, k, columnWidth);
                        if (j == 0) {
                            k++;
                            rowCell = row.createCell(k);
                            rowCell.setCellStyle(styleTwo);
                            rowCell.setCellValue(String.valueOf(cityNumMap.get(cityId)));
                            hjsum += cityNumMap.get(cityId);
                            //宽度自适应
                            setColumnWidth(String.valueOf(cityNumMap.get(cityId)).toString().getBytes().length, k, columnWidth);
                            sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, sessionNumbers.size() - 1+rowNum - 1, k, k));
                        }
                    }
                }
            }

            row = sheet.createRow(rowNum++);
            int k = 0;
            rowCell = row.createCell(k);
            rowCell.setCellStyle(styleTwo);
            rowCell.setCellValue("合计");
            //宽度自适应
            setColumnWidth("合计".toString().getBytes().length, k++, columnWidth);

            rowCell = row.createCell(k);
            rowCell.setCellStyle(styleTwo);
            rowCell.setCellValue(" ");
            //宽度自适应
            setColumnWidth(" ".getBytes().length, k++, columnWidth);
            int sum = 0;
            for (JszyResDocTypeEnum e : JszyResDocTypeEnum.values()) {
                Integer num = (Integer) typeNumMap.get(e.getId());
                if (num == null)
                    num = 0;
                sum += num;
                rowCell = row.createCell(k);
                rowCell.setCellStyle(styleTwo);
                rowCell.setCellValue(String.valueOf(num));
                //宽度自适应
                setColumnWidth(String.valueOf(num).toString().getBytes().length, k, columnWidth);
                k++;
            }
            rowCell = row.createCell(k);
            rowCell.setCellStyle(styleTwo);
            rowCell.setCellValue(String.valueOf(xjsum));
            //宽度自适应
            setColumnWidth(String.valueOf(xjsum).toString().getBytes().length, k, columnWidth);
            k++;
            rowCell = row.createCell(k);
            rowCell.setCellStyle(styleTwo);
            rowCell.setCellValue(String.valueOf(hjsum));
            //宽度自适应
            setColumnWidth(String.valueOf(cityNumMap.get(hjsum)).toString().getBytes().length, k, columnWidth);
            Set<Integer> keys = columnWidth.keySet();
            for (Integer key : keys) {
                int width = columnWidth.get(key);
                sheet.setColumnWidth(key, width * 2 * 256);
            }
        }
        wb.write(os);
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    private void setColumnWidth(int length, int key, Map<Integer, Integer> columnWidth) {
        if(columnWidth.containsKey(key)) {
            Integer ol = columnWidth.get(key);
            if(ol<length)
                columnWidth.put(key,length);
        }else{
            columnWidth.put(key,length);
        }
    }


    @RequestMapping(value={"/exportZlxytj2"})
    public void exportZlxytj2(String[] sessionDatas,String orgCityId,String joint,String datas[], String[] trainYears,HttpServletResponse response,Model model) throws IOException {
        List<Map<String,String>> citys=getCityList();
        //默认统计近三年
        List<String> sessionNumbers = new ArrayList<>() ;
        String currentYear = DateUtil.getYear();
        Integer year1 = Integer.parseInt(currentYear) - 1;
        Integer year2 = Integer.parseInt(currentYear) - 2;
        sessionNumbers.add(year2+"");
        sessionNumbers.add(year1+"");
        sessionNumbers.add(currentYear);
        if(sessionDatas == null){
            sessionDatas = new String[1];
            sessionDatas[0]=sessionNumbers.get(1);
        }
        List<String> docTypeList=new ArrayList<String>();
        ResDoctor doctor=new ResDoctor();
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }else{
            datas=new String[JszyResDocTypeEnum.values().length];
            int i=0;
            for(JszyResDocTypeEnum e:JszyResDocTypeEnum.values())
            {
                docTypeList.add(e.getId());
                datas[i++]=e.getId();
            }
        }
        model.addAttribute("datas", datas);
        Map<String,Object> param=new HashMap<>();
        param.put("docTypeList",docTypeList);
        param.put("trainYears",trainYears);
        param.put("joint",joint);
        param.put("sessionNumbers",sessionDatas);
        List<SysOrg> orgs=orgBiz.queryAllSysOrg(new SysOrg());
        Map<String,String> orgNameMap=new HashMap<>();
        Map<String,List<String>> cityOrgMap=new HashMap<>();
        Map<Object, Object> orgSpeFlagMap=new HashMap<Object,Object>();//基地专业标志的的map
        Map<String,List<String>> jointOrgMap=new HashMap<>();
        List<String> jointOrgFlows=new ArrayList<String>();
        Map<String,Integer> typeNumMap=new HashMap<>();
        if(orgs!=null)
        {
            for(SysOrg o:orgs)
            {
                orgNameMap.put(o.getOrgFlow(),o.getOrgName());
                if(OrgLevelEnum.CountryOrg.getId().equals(o.getOrgLevelId()))
                {
                    List<String> orgFlows=cityOrgMap.get(o.getOrgCityId());
                    if(orgFlows==null)
                        orgFlows=new ArrayList<>();
                    if(!orgFlows.contains(o.getOrgFlow()))
                    {
                        orgFlows.add(o.getOrgFlow());
                    }
                    cityOrgMap.put(o.getOrgCityId(),orgFlows);

                    if(GlobalConstant.FLAG_Y.equals(joint))
                    {
                        List<String> jointOrgFlowList=new ArrayList<>();
                        List<SysOrg>jointOrgList=orgBiz.searchJointOrgsByOrg(o.getOrgFlow());//查询每家基地的协同基地
                        if(jointOrgList!=null&&!jointOrgList.isEmpty()){
                            for(SysOrg resJointOrg:jointOrgList){
                                if(!jointOrgFlowList.contains(resJointOrg.getOrgFlow()))
                                {
                                    jointOrgFlowList.add(resJointOrg.getOrgFlow());
                                }
                            }
                        }
                        jointOrgMap.put(o.getOrgFlow(),jointOrgFlowList);
                    }
                }

                ResOrgSpe resOrgSpe=new ResOrgSpe();
                resOrgSpe.setOrgFlow(o.getOrgFlow());
                resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
                List<ResOrgSpe> orgSpeList=resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
                if(orgSpeList!=null&&!orgSpeList.isEmpty()){//每家基地的专业
                    for(ResOrgSpe r:orgSpeList){
                        orgSpeFlagMap.put(o.getOrgFlow()+r.getSpeTypeId()+r.getSpeId(),GlobalConstant.FLAG_Y);
                    }
                }
            }
        }
        model.addAttribute("orgNameMap",orgNameMap);
        model.addAttribute("orgSpeFlagMap",orgSpeFlagMap);
        model.addAttribute("trainYears",trainYears);

        List<Map<String,Object>> list=doctorRecruitBiz.zlxytj2(param);
        Map<String,Object> cityOrgNumMap=new HashMap<>();
        if(list!=null)
        {
            for(Map<String,Object> map:list)
            {
                //每个城市有多个届别
                String orgFlow= (String) map.get("orgFlow");
                String speId= (String) map.get("speId");
                String sessionNumber= (String) map.get("sessionNumber");
                cityOrgNumMap.put(orgFlow+speId+sessionNumber,map.get("num"));
                Integer sum2=typeNumMap.get(speId);
                if(sum2==null)
                    sum2=0;
                sum2+=(Integer) map.get("num");
                typeNumMap.put(speId,sum2);
            }
        }
        List<String> subKeyList = new ArrayList<>();
        subKeyList.add("3501");
        subKeyList.add("3502");
        subKeyList.add("3601");
        subKeyList.add("3602");
        subKeyList.add("6000");
        List<String> orgFlows = new ArrayList<>();
        for (Map city :citys) {
            List<String> cityOrgs = cityOrgMap.get(city.get("cityId"));
            if(cityOrgs == null || cityOrgs.size()== 0) continue;
            for (String o:cityOrgs){
                orgFlows.add(o);
            }
        }
        Workbook wb = new HSSFWorkbook();
        setZlxytj2Content(wb,cityOrgNumMap,sessionDatas,subKeyList,orgFlows);


        String fileName = "招录统计--基地.xls";
        fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream os = response.getOutputStream();
        wb.write(os);
    }


    @RequestMapping(value="/exportZlxytj3", method={RequestMethod.POST,RequestMethod.GET})
    public void exportZlxytj3(String sessionNumber, String orgCityId, String joint, String trainTypeIds[], String TCMAssiGeneral, String orgFlow,
                              Integer currentPage, HttpServletRequest request, String datas[], Model model,HttpServletResponse response) throws Exception {
        //获取城市列表
        List<Map<String,String>> citys=getCityList();
        //组装入参
        Map<String,Object>  param = assembleZlxytj3Param(sessionNumber,orgCityId,joint,trainTypeIds,orgFlow,datas,model);
        List<Map<String, Object>> list = doctorRecruitBiz.zlxytj3(param);
        // 创建 招录统计-专业
//        HSSFWorkbook wb = createZlxytj3Excel(list);
        String fileName = "招录学员统计表.xls";
        fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        OutputStream os = response.getOutputStream();
        HSSFWorkbook wb = createZlxytj3Excel(joint,list,trainTypeIds,model);
        wb.write(os);
    }

    @RequestMapping(value="/exportZlxytj4", method={RequestMethod.POST,RequestMethod.GET})
    public void exportZlxytj4(String sessionNumber,  HttpServletRequest request,String datas[],String[] trainYears ,String cityId,String sorgFlow ,Model model,HttpServletResponse response) throws Exception {
        String[] headLines ={"招录学员统计"};
        String[] titles = {
                ":序号",
                "orgCityName:地市",
                "orgName:基地名称",
                "chineseMedicine:中医",
                "tCMGeneral:中医全科",
                "tCMAssiGeneral:中医助理全科",
                "sum:小计"
        };
        HSSFWorkbook wb = new HSSFWorkbook();
        //组装查询入参
        Map<String, Object> param = assembleZlxytj4Param(sessionNumber,datas,model);
        param.put("cityId",cityId);
        param.put("orgFlow",sorgFlow);
        param.put("trainYears",trainYears);
        List<Map<String, Object>> list = doctorRecruitBiz.zlxytj4(param);

        Map<String, String> countInfoMap = new HashMap<>();
        if (list != null && list.size() > 0) {
            for (Map<String, Object> tempMap : list) {
                String orgFlow = tempMap.get("orgFlow").toString();
                String catSpeId = tempMap.get("catSpeId").toString();
                String count = tempMap.get("num").toString();
                countInfoMap.put(orgFlow + catSpeId, count);
            }
        }
        //查询基地
        SysOrg searchOrg = new SysOrg();
        searchOrg.setOrgProvId("320000");
        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        searchOrg.setRecordStatus(GlobalConstant.FLAG_Y);
        PageHelper.startPage(1, 1000);
        List<SysOrg> orgs = orgBiz.searchOrg(searchOrg);
        List<Map<String, Object>> dataList =new ArrayList <Map<String, Object>>();
        Map<String, Object> data = null;
        int chineseMedicineSum = 0;
        int tCMGeneralSum = 0;
        int tCMAssiGeneralSum = 0;
        int totalSum = 0;
        for (SysOrg org:orgs) {
            if(StringUtil.isNotEmpty(cityId) && !cityId.equals(org.getOrgCityId())) continue;
            if(StringUtil.isNotEmpty(sorgFlow) && !org.getOrgFlow().equals(sorgFlow)) continue;
            data = new HashMap<String, Object>();
            int chineseMedicineCount = StringUtil.isEmpty(countInfoMap.get(org.getOrgFlow()+"ChineseMedicine"))?0:Integer.parseInt(countInfoMap.get(org.getOrgFlow()+"ChineseMedicine"));
            chineseMedicineSum += chineseMedicineCount;
            int tCMGeneralCount = StringUtil.isEmpty(countInfoMap.get(org.getOrgFlow()+"TCMGeneral"))?0:Integer.parseInt(countInfoMap.get(org.getOrgFlow()+"TCMGeneral"));
            tCMGeneralSum += tCMGeneralCount;
            int tCMAssiGeneralCount = StringUtil.isEmpty(countInfoMap.get(org.getOrgFlow()+"TCMAssiGeneral"))?0:Integer.parseInt(countInfoMap.get(org.getOrgFlow()+"TCMAssiGeneral"));
            tCMAssiGeneralSum += tCMAssiGeneralCount;
            int sum = chineseMedicineCount+tCMGeneralCount+tCMAssiGeneralCount;
            totalSum += sum;
            data.put("orgCityName", org.getOrgCityName());//基地名称
            data.put("orgName", org.getOrgName());//基地名称
            data.put("chineseMedicine",chineseMedicineCount);//中医
            data.put("tCMGeneral",tCMGeneralCount);//中医全科
            data.put("tCMAssiGeneral",tCMAssiGeneralCount);//中医助理全科
            data.put("sum",sum);//小计
            dataList.add(data);
        }
        // 合计
        data = new HashMap<String, Object>();
        data.put("orgName", "合计");//基地名称
        data.put("chineseMedicine",chineseMedicineSum);//中医
        data.put("tCMGeneral",tCMGeneralSum);//中医全科
        data.put("tCMAssiGeneral",tCMAssiGeneralSum);//中医助理全科
        data.put("sum",totalSum);//小计
        dataList.add(data);

        ExcleUtile.createSimpleSheetWithHeadline(headLines,titles,dataList,wb,"招录学员统计");
        String fileName = "招录学员统计.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }


    /**
     * 招录统计-汇总 组装入参
     */
    private Map<String,Object> assembleZlxytj4Param(String sessionNumber,String[] datas,Model model){
        if(StringUtil.isBlank(sessionNumber)){
            int year = 0;
            Calendar cal = Calendar.getInstance();
            int month = cal.get(Calendar.MONTH) + 1;
            if (month < 9) {
                year = cal.get(Calendar.YEAR) - 1;
            } else {
                year = cal.get(Calendar.YEAR);
            }
            sessionNumber = year + "";
            model.addAttribute("sessionNumber", sessionNumber);
        }else {
            model.addAttribute("sessionNumber", sessionNumber);
        }


        Map<String, Object> param = new HashMap<>();

        param.put("sessionNumber", sessionNumber);
        //人员类型
        List<String> docTypeList = new ArrayList<String>();
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }else{
            datas=new String[JszyResDocTypeEnum.values().length];
            int i=0;
            for(JszyResDocTypeEnum e:JszyResDocTypeEnum.values())
            {
                docTypeList.add(e.getId());
                datas[i++]=e.getId();
            }
        }
        param.put("docTypeList", docTypeList);
        //人员类型培训专业
        List<String> trainTypeIdList = new ArrayList<String>();
        for (JszyTrainCategoryEnum e : JszyTrainCategoryEnum.values()) {
            trainTypeIdList.add(e.getId());
        }
        param.put("trainTypeIdList", trainTypeIdList);
        return param;
    }
    /**
     * 招录统计-专业 组装入参
     */
    private Map<String,Object> assembleZlxytj3Param(String sessionNumber, String orgCityId, String joint, String trainTypeIds[],  String orgFlow,
                                                   String datas[], Model model) {
        //默认统计上一年
        if (StringUtil.isBlank(sessionNumber)) {
            String currentYear = DateUtil.getYear();
            Integer lastYear = Integer.parseInt(currentYear) - 1;
            model.addAttribute("sessionNumber", lastYear);
            sessionNumber = lastYear + "";
        }

        List<String> docTypeList = new ArrayList<String>();
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        } else {
            datas = new String[JszyResDocTypeEnum.values().length];
            int i = 0;
            for (JszyResDocTypeEnum e : JszyResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
                datas[i++] = e.getId();
            }
        }
        model.addAttribute("datas", datas);
        Map<String, Object> param = new HashMap<>();
        //人员类型
        param.put("docTypeList", docTypeList);
        //城市代码
        param.put("orgCityId", orgCityId);

        param.put("joint", joint);
        param.put("sessionNumber", sessionNumber);
        List<String> trainTypeIdList = new ArrayList<String>();
        if (trainTypeIds != null && trainTypeIds.length > 0) {
            for (String s : trainTypeIds) {
                trainTypeIdList.add(s);
            }
        } else {
            trainTypeIds = new String[JszyTrainCategoryEnum.values().length];
            int i = 0;
            for (JszyTrainCategoryEnum e : JszyTrainCategoryEnum.values()) {
                if(GlobalConstant.FLAG_Y.equals(e.getIsView())){
                    trainTypeIdList.add(e.getId());
                    trainTypeIds[i++] = e.getId();
                }
            }
        }
        param.put("trainTypeIdList", trainTypeIdList);
        model.addAttribute("trainTypeIds", trainTypeIds);
        param.put("orgFlow", orgFlow);
        return param;
    }

   /*
    * 获取城市列表
    * */
    private List<Map<String,String>> getCityList(){
        List<Map<String,String>> citys=new ArrayList<>();
        Map<String,String> city=new HashMap<>();
        city.put("cityId", "320100");
        city.put("cityName", "南京市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320200");
        city.put("cityName", "无锡市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320300");
        city.put("cityName", "徐州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320400");
        city.put("cityName", "常州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320500");
        city.put("cityName", "苏州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320600");
        city.put("cityName", "南通市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320700");
        city.put("cityName", "连云港市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320800");
        city.put("cityName", "淮安市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320900");
        city.put("cityName", "盐城市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "321000");
        city.put("cityName", "扬州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "321100");
        city.put("cityName", "镇江市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "321200");
        city.put("cityName", "泰州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "321300");
        city.put("cityName", "宿迁市");
        citys.add(city);
        return citys;
    }


    // 创建 HSSFWorkbook
    private HSSFWorkbook createZlxytj3Excel(String joint,List<Map<String,Object>> list,String trainTypeIds[],Model model){
        Map<String,String> countInfoMap = new HashMap<>();
        if (list != null && list.size() > 0) {
            for(Map<String,Object> tempMap : list){
                String jointOrgFlow = tempMap.get("jointOrgFlow").toString();
                String catSpeId = tempMap.get("catSpeId").toString();
                String speId = tempMap.get("speId").toString();
                String secondSpeId = tempMap.get("secondSpeId").toString();
                String count = tempMap.get("num").toString();
                if(JszyTrainCategoryEnum.TCMAssiGeneral.getId().equals(catSpeId)){
                    countInfoMap.put(jointOrgFlow + catSpeId, count);
                }else if(JszyTrainCategoryEnum.TCMGeneral.getId().equals(catSpeId)){
                    //中医全科下经产品确认修改为不含二级专业
                    String tCMGeneralCount = countInfoMap.get(jointOrgFlow + catSpeId);
                    if(tCMGeneralCount == null){
                        countInfoMap.put(jointOrgFlow + catSpeId + speId, count);
                    }else {
                        countInfoMap.put(jointOrgFlow + catSpeId + speId, Integer.parseInt(count) + Integer.parseInt(tCMGeneralCount) + "");
                    }
                }else {
                    countInfoMap.put(jointOrgFlow + catSpeId + speId + secondSpeId, count);
                }
            }
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        setZlxytj3Title(wb,trainTypeIds,countInfoMap);


        return wb;
    }

    private void setZlxytj3Title(Workbook wb ,String trainTypeIds[], Map<String,String> countInfoMap ){
        List<Map<String,Object>> titleList = new ArrayList<Map<String,Object>>();

        List<String> keyList = new ArrayList<String>();
        int titleLines = 0;
        Map<String,Object> titleMap = null;
        List<SysDict> secondTrainingSpeList = DictTypeEnum.SecondTrainingSpe.getSysDictList();

        Set<String> trainTypeIdSet = new HashSet<String>();
        for (String trainTypeId :trainTypeIds){
            trainTypeIdSet.add(trainTypeId);
        }
        //查询基地
        SysOrg searchOrg = new SysOrg();
        searchOrg.setOrgProvId("320000");
        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        searchOrg.setRecordStatus(GlobalConstant.FLAG_Y);
        PageHelper.startPage(1, 1000);
        List<SysOrg> orgs = orgBiz.searchOrg(searchOrg);


        // 1. 中医、中医全科、中医助理全科
        if(trainTypeIdSet.contains("ChineseMedicine")
                &&trainTypeIdSet.contains("TCMGeneral")
                &&trainTypeIdSet.contains("TCMAssiGeneral")){
            titleLines = 3;
            //第一行
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医");
            titleMap.put("addr",new CellRangeAddress(1,1,1,secondTrainingSpeList.size()*2+1));
            titleMap.put("row",1);
            titleMap.put("index",1);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医全科");
            titleMap.put("addr",new CellRangeAddress(1,1,secondTrainingSpeList.size()*2+2,secondTrainingSpeList.size()*2+3));
            titleMap.put("row",1);
            titleMap.put("index",secondTrainingSpeList.size()*2+2);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医助理全科");
            titleMap.put("addr",new CellRangeAddress(1,3,secondTrainingSpeList.size()*2+4,secondTrainingSpeList.size()*2+4));
            titleMap.put("row",1);
            titleMap.put("index",secondTrainingSpeList.size()*2+4);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            //第二行
            titleMap.put("name","中医学");
            titleMap.put("row",2);
            titleMap.put("addr",new CellRangeAddress(2,2,1,secondTrainingSpeList.size()));
            titleMap.put("index",1);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中西医结合（中西医临床医学）");
            titleMap.put("addr",new CellRangeAddress(2,2,secondTrainingSpeList.size()+1,secondTrainingSpeList.size()*2+1));
            titleMap.put("row",2);
            titleMap.put("index",secondTrainingSpeList.size()+1);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医学");
            titleMap.put("addr",new CellRangeAddress(2,3,secondTrainingSpeList.size()*2+2,secondTrainingSpeList.size()*2+2));
            titleMap.put("row",2);
            titleMap.put("index",secondTrainingSpeList.size()*2+2);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中西医结合（中西医临床医学）");
            titleMap.put("addr",new CellRangeAddress(2,3,secondTrainingSpeList.size()*2+3,secondTrainingSpeList.size()*2+3));
            titleMap.put("row",2);
            titleMap.put("index",secondTrainingSpeList.size()*2+3);
            titleList.add(titleMap);
            //第三行
            for (int k = 0 ;k<secondTrainingSpeList.size()*2;k++){
                titleMap = new HashMap<String,Object>();
                titleMap.put("name",secondTrainingSpeList.get(k%secondTrainingSpeList.size()).getDictName());
                titleMap.put("row",3);
                titleMap.put("index",k+1);
                titleList.add(titleMap);
                if(k < secondTrainingSpeList.size()){
                    keyList.add("ChineseMedicine3501"+secondTrainingSpeList.get(k%secondTrainingSpeList.size()).getDictId());
                }else{
                    keyList.add("ChineseMedicine3502"+secondTrainingSpeList.get(k%secondTrainingSpeList.size()).getDictId());
                }
            }
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","无");
            titleMap.put("row",3);
            titleMap.put("index",secondTrainingSpeList.size()*2+1);
            titleList.add(titleMap);
            keyList.add("ChineseMedicine3502wu");
            keyList.add("TCMGeneral3601");
            keyList.add("TCMGeneral3602");
            keyList.add("TCMAssiGeneral");
        }
        // 2. 中医、中医全科
        if(trainTypeIdSet.contains("ChineseMedicine")
                &&trainTypeIdSet.contains("TCMGeneral")
                && ! trainTypeIdSet.contains("TCMAssiGeneral")){
            titleLines = 3;
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医");
            titleMap.put("addr",new CellRangeAddress(1,1,1,secondTrainingSpeList.size()*2+1));
            titleMap.put("row",1);
            titleMap.put("index",1);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医全科");
            titleMap.put("addr",new CellRangeAddress(1,1,secondTrainingSpeList.size()*2+2,secondTrainingSpeList.size()*2+3));
            titleMap.put("row",1);
            titleMap.put("index",secondTrainingSpeList.size()*2+2);
            titleList.add(titleMap);

            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医学");
            titleMap.put("row",2);
            titleMap.put("addr",new CellRangeAddress(2,2,1,secondTrainingSpeList.size()));
            titleMap.put("index",1);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中西医结合（中西医临床医学）");
            titleMap.put("addr",new CellRangeAddress(2,2,secondTrainingSpeList.size()+1,secondTrainingSpeList.size()*2+1));
            titleMap.put("row",2);
            titleMap.put("index",secondTrainingSpeList.size()+1);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医学");
            titleMap.put("addr",new CellRangeAddress(2,3,secondTrainingSpeList.size()*2+2,secondTrainingSpeList.size()*2+2));
            titleMap.put("row",2);
            titleMap.put("index",secondTrainingSpeList.size()*2+2);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中西医结合（中西医临床医学）");
            titleMap.put("addr",new CellRangeAddress(2,3,secondTrainingSpeList.size()*2+3,secondTrainingSpeList.size()*2+3));
            titleMap.put("row",2);
            titleMap.put("index",secondTrainingSpeList.size()*2+3);
            titleList.add(titleMap);

            //第三行
            for (int k = 0 ;k<secondTrainingSpeList.size()*2;k++){
                titleMap = new HashMap<String,Object>();
                titleMap.put("name",secondTrainingSpeList.get(k%secondTrainingSpeList.size()).getDictName());
                titleMap.put("row",3);
                titleMap.put("index",k+1);
                titleList.add(titleMap);
                if(k < secondTrainingSpeList.size()){
                    keyList.add("ChineseMedicine3501"+secondTrainingSpeList.get(k%secondTrainingSpeList.size()).getDictId());
                }else{
                    keyList.add("ChineseMedicine3502"+secondTrainingSpeList.get(k%secondTrainingSpeList.size()).getDictId());
                }
            }
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","无");
            titleMap.put("row",3);
            titleMap.put("index",secondTrainingSpeList.size()*2+1);
            titleList.add(titleMap);
            keyList.add("ChineseMedicine3502wu");
            keyList.add("TCMGeneral3601");
            keyList.add("TCMGeneral3602");
        }
        // 3. 中医、中医助理全科
        if(trainTypeIdSet.contains("ChineseMedicine")
                && ! trainTypeIdSet.contains("TCMGeneral")
                &&trainTypeIdSet.contains("TCMAssiGeneral")){
            titleLines = 3;
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医");
            titleMap.put("addr",new CellRangeAddress(1,1,1,secondTrainingSpeList.size()*2+1));
            titleMap.put("row",1);
            titleMap.put("index",1);
            titleList.add(titleMap);

            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医助理全科");
            titleMap.put("addr",new CellRangeAddress(1,3,secondTrainingSpeList.size()*2+2,secondTrainingSpeList.size()*2+2));
            titleMap.put("row",1);
            titleMap.put("index",secondTrainingSpeList.size()*2+2);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            //第二行
            titleMap.put("name","中医学");
            titleMap.put("row",2);
            titleMap.put("addr",new CellRangeAddress(2,2,1,secondTrainingSpeList.size()));
            titleMap.put("index",1);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中西医结合（中西医临床医学）");
            titleMap.put("addr",new CellRangeAddress(2,2,secondTrainingSpeList.size()+1,secondTrainingSpeList.size()*2+1));
            titleMap.put("row",2);
            titleMap.put("index",secondTrainingSpeList.size()+1);
            titleList.add(titleMap);
            //第三行
            for (int k = 0 ;k<secondTrainingSpeList.size()*2;k++){
                titleMap = new HashMap<String,Object>();
                titleMap.put("name",secondTrainingSpeList.get(k%secondTrainingSpeList.size()).getDictName());
                titleMap.put("row",3);
                titleMap.put("index",k+1);
                titleList.add(titleMap);
                if(k < secondTrainingSpeList.size()){
                    keyList.add("ChineseMedicine3501"+secondTrainingSpeList.get(k%secondTrainingSpeList.size()).getDictId());
                }else{
                    keyList.add("ChineseMedicine3502"+secondTrainingSpeList.get(k%secondTrainingSpeList.size()).getDictId());
                }
            }
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","无");
            titleMap.put("row",3);
            titleMap.put("index",secondTrainingSpeList.size()*2+1);
            titleList.add(titleMap);
            keyList.add("ChineseMedicine3502wu");
            keyList.add("TCMAssiGeneral");
        }
        // 4. 中医全科、中医助理全科
        if( ! trainTypeIdSet.contains("ChineseMedicine")
                &&trainTypeIdSet.contains("TCMGeneral")
                &&trainTypeIdSet.contains("TCMAssiGeneral")){
            titleLines = 2;

            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医全科");
            titleMap.put("addr",new CellRangeAddress(1,1,1,2));
            titleMap.put("row",1);
            titleMap.put("index",1);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医助理全科");
            titleMap.put("addr",new CellRangeAddress(1,2,3,3));
            titleMap.put("row",1);
            titleMap.put("index",3);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医学");
            titleMap.put("row",2);
            titleMap.put("index",1);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中西医结合（中西医临床医学）");
            titleMap.put("row",2);
            titleMap.put("index",2);
            titleList.add(titleMap);
            keyList.add("TCMGeneral3601");
            keyList.add("TCMGeneral3602");
            keyList.add("TCMAssiGeneral");

        }
        // 5. 中医
        if(trainTypeIdSet.contains("ChineseMedicine")
                && ! trainTypeIdSet.contains("TCMGeneral")
                && ! trainTypeIdSet.contains("TCMAssiGeneral")){
            titleLines = 3;
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医");
            titleMap.put("addr",new CellRangeAddress(1,1,1,secondTrainingSpeList.size()*2+1));
            titleMap.put("row",1);
            titleMap.put("index",1);
            titleList.add(titleMap);

            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医学");
            titleMap.put("row",2);
            titleMap.put("addr",new CellRangeAddress(2,2,1,secondTrainingSpeList.size()));
            titleMap.put("index",1);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中西医结合（中西医临床医学）");
            titleMap.put("addr",new CellRangeAddress(2,2,secondTrainingSpeList.size()+1,secondTrainingSpeList.size()*2+1));
            titleMap.put("row",2);
            titleMap.put("index",secondTrainingSpeList.size()+1);
            titleList.add(titleMap);
            //第三行
            for (int k = 0 ;k<secondTrainingSpeList.size()*2;k++){
                titleMap = new HashMap<String,Object>();
                titleMap.put("name",secondTrainingSpeList.get(k%secondTrainingSpeList.size()).getDictName());
                titleMap.put("row",3);
                titleMap.put("index",k+1);
                titleList.add(titleMap);
                if(k < secondTrainingSpeList.size()){
                    keyList.add("ChineseMedicine3501"+secondTrainingSpeList.get(k%secondTrainingSpeList.size()).getDictId());
                }else{
                    keyList.add("ChineseMedicine3502"+secondTrainingSpeList.get(k%secondTrainingSpeList.size()).getDictId());
                }
            }
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","无");
            titleMap.put("row",3);
            titleMap.put("index",secondTrainingSpeList.size()*2+1);
            titleList.add(titleMap);
            keyList.add("ChineseMedicine3502wu");

        }
        // 6. 中医全科
        if( ! trainTypeIdSet.contains("ChineseMedicine")
                &&trainTypeIdSet.contains("TCMGeneral")
                && ! trainTypeIdSet.contains("TCMAssiGeneral")){
            titleLines = 2;

            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医全科");
            titleMap.put("addr",new CellRangeAddress(1,1,1,2));
            titleMap.put("row",1);
            titleMap.put("index",1);
            titleList.add(titleMap);

            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医学");
            titleMap.put("row",2);
            titleMap.put("index",1);
            titleList.add(titleMap);
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中西医结合（中西医临床医学）");
            titleMap.put("row",2);
            titleMap.put("index",2);
            titleList.add(titleMap);
            keyList.add("TCMGeneral3601");
            keyList.add("TCMGeneral3602");

        }
        // 7. 中医助理全科
        if( ! trainTypeIdSet.contains("ChineseMedicine")
                && ! trainTypeIdSet.contains("TCMGeneral")
                &&trainTypeIdSet.contains("TCMAssiGeneral")){
            titleLines = 1;
            titleMap = new HashMap<String,Object>();
            titleMap.put("name","中医助理全科");
            titleMap.put("row",1);
            titleMap.put("index",1);
            titleList.add(titleMap);
            keyList.add("TCMAssiGeneral");
        }

        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        Sheet sheet = wb.createSheet("sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        //HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        CellStyle style = wb.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        style.setFont(font);
        Font fontTwo = wb.createFont();
        fontTwo.setFontHeightInPoints((short) 12);

        CellStyle styleTwo = wb.createCellStyle();
        styleTwo.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
        styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        styleTwo.setFont(fontTwo);
        Cell rowCell = null ;

        Row row = null;
        int len = 0;
        row = sheet.createRow(0);//标题栏
        row = sheet.createRow(1);// 表头第一行
        rowCell = row.createCell(0);
        rowCell.setCellStyle(style);
        rowCell.setCellValue("基地名称");
        sheet.addMergedRegion(new CellRangeAddress(1,titleLines,0,0));
        row = sheet.createRow(2);// 表头第二行
        row = sheet.createRow(3);// 表头第三行
        for (int k = 0 ;k<orgs.size();k++){
            row = sheet.createRow(k+titleLines+1);
            rowCell = row.createCell(0);
            rowCell.setCellStyle(styleTwo);
            rowCell.setCellValue(orgs.get(k).getOrgName());
            int length = String.valueOf(orgs.get(k).getOrgName()).length();
            sheet.setColumnWidth(0, length*1000);
            int sum = 0;
            for(int j=0;j<  keyList.size();j++){
                rowCell = row.createCell(j+1);
                rowCell.setCellStyle(styleTwo);
                String  str =countInfoMap.get(orgs.get(k).getOrgFlow()+keyList.get(j) );
                int value  =    str == null ? 0 :Integer.parseInt(str);
                rowCell.setCellValue(value);
                sum += value;
            }
            len = row.getLastCellNum();
            rowCell = row.createCell(len);
            rowCell.setCellStyle(styleTwo);
            rowCell.setCellValue(sum);
        }

        for(int k=0 ; k < titleList.size();k++){
            titleMap = titleList.get(k);
            row = sheet.getRow((int)titleMap.get("row"));
            rowCell = row.createCell((int)titleMap.get("index"));
            rowCell.setCellStyle(styleTwo);
            rowCell.setCellValue(String.valueOf(titleMap.get("name")));
            int length = String.valueOf(titleMap.get("name")).length();
            sheet.setColumnWidth((int)titleMap.get("index"), length*700);
            if( titleMap.get("addr") != null ){
                sheet.addMergedRegion((CellRangeAddress)titleMap.get("addr"));
            }
        }

        sheet.createFreezePane(1,titleLines+1,1,titleLines+1);

        row = sheet.getRow(0);
        rowCell = row.createCell(0);
        rowCell.setCellValue("招录统计-专业");
        rowCell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,len));
        row = sheet.getRow(1);
        rowCell = row.createCell(len);
        rowCell.setCellValue("总计");
        rowCell.setCellStyle(styleTwo);
        sheet.addMergedRegion(new CellRangeAddress(1,titleLines,len,len));

    }


    private void setZlxytj2Content(Workbook wb , Map<String,Object> countInfoMap,String[] sessionDatas,List keyList,List orgFlows){
        List<Map<String,Object>> titleList = new ArrayList<Map<String,Object>>();
        int titleLines = 2;
        Map<String,Object> titleMap = null;
        List<SysDict> secondTrainingSpeList = DictTypeEnum.SecondTrainingSpe.getSysDictList();
        Set orgFlowSet = new HashSet(orgFlows);
        //查询基地
        SysOrg searchOrg = new SysOrg();
        searchOrg.setOrgProvId("320000");
        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        searchOrg.setRecordStatus(GlobalConstant.FLAG_Y);
        PageHelper.startPage(1, 1000);
        List<SysOrg> orgs = orgBiz.searchOrg(searchOrg);
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        Sheet sheet = wb.createSheet("sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        //HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        CellStyle style = wb.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        style.setFont(font);
        Font fontTwo = wb.createFont();
        fontTwo.setFontHeightInPoints((short) 12);

        CellStyle styleTwo = wb.createCellStyle();
        styleTwo.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
        styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        styleTwo.setFont(fontTwo);
        Cell rowCell = null ;

        Row row = null;
        int len = 0;
        row = sheet.createRow(0);//标题栏
        // 表头第一行
        row = sheet.createRow(1);
        rowCell = row.createCell(0);
        rowCell.setCellStyle(style);
        rowCell.setCellValue("基地名称");
        sheet.addMergedRegion(new CellRangeAddress(1,titleLines,0,0));
        rowCell = row.createCell(1);
        rowCell.setCellStyle(style);
        rowCell.setCellValue("年级");
        sheet.addMergedRegion(new CellRangeAddress(1,titleLines,1,1));
        rowCell = row.createCell(2);
        rowCell.setCellStyle(style);
        rowCell.setCellValue("中医");
        sheet.addMergedRegion(new CellRangeAddress(1,1,2,3));
        rowCell = row.createCell(4);
        rowCell.setCellStyle(style);
        rowCell.setCellValue("中医全科");
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,5));
        rowCell = row.createCell(6);
        rowCell.setCellStyle(style);
        rowCell.setCellValue("中医助理全科");
        sheet.addMergedRegion(new CellRangeAddress(1,titleLines,6,6));
        sheet.setColumnWidth(6, 5*1000);
        rowCell = row.createCell(7);
        rowCell.setCellStyle(style);
        rowCell.setCellValue("合计");
        sheet.addMergedRegion(new CellRangeAddress(1,titleLines,7,7));
//        rowCell = row.createCell(8);
//        rowCell.setCellStyle(style);
//        rowCell.setCellValue("合计");
//        sheet.addMergedRegion(new CellRangeAddress(1,titleLines,8,8));
        // 表头第二行
        row = sheet.createRow(2);
        rowCell = row.createCell(2);
        rowCell.setCellStyle(style);
        rowCell.setCellValue("中医学");
        rowCell = row.createCell(3);
        rowCell.setCellStyle(style);
        rowCell.setCellValue("中西医结合(中西医临床医学)");
        sheet.setColumnWidth(3, 8*1000);
        rowCell = row.createCell(4);
        rowCell.setCellStyle(style);
        rowCell.setCellValue("中医");
        rowCell = row.createCell(5);
        rowCell.setCellStyle(style);
        rowCell.setCellValue("中西医结合(中西医临床医学)");
        sheet.setColumnWidth(5, 8*1000);
        int sum3501 = 0;
        int sum3502 = 0;
        int sum3601 = 0;
        int sum3602 = 0;
        int sum6000 = 0;
        int rowIndex = 3;
        for (int k = 0 ;k<orgs.size();k++){
            if(!orgFlowSet.contains(orgs.get(k).getOrgFlow())) continue;
            int hejiSum = 0;
            for(int i = 0;i<sessionDatas.length;i++){
                int sum = 0;
                row = sheet.createRow(rowIndex);
                rowIndex++;
                rowCell = row.createCell(0);
                rowCell.setCellStyle(styleTwo);
                rowCell.setCellValue(orgs.get(k).getOrgName());
                int length = String.valueOf(orgs.get(k).getOrgName()).length();
                sheet.setColumnWidth(0, length*1000);
                rowCell = row.createCell(1);
                rowCell.setCellStyle(styleTwo);
                rowCell.setCellValue(sessionDatas[i]);
                for(int j=0;j<  keyList.size();j++){
                    rowCell = row.createCell(j+2);
                    rowCell.setCellStyle(styleTwo);
                    String key=orgs.get(k).getOrgFlow()+keyList.get(j) +sessionDatas[i];
                    Object  obj =countInfoMap.get(key);
                    int value  =    obj == null ? 0 :Integer.parseInt(obj+"");
                    rowCell.setCellValue(value);
                    if(j==0) sum3501+=value;
                    if(j==1) sum3502+=value;
                    if(j==2) sum3601+=value;
                    if(j==3) sum3602+=value;
                    if(j==4) sum6000+=value;
                    sum += value;
                }
//                len = row.getLastCellNum();
//                rowCell = row.createCell(len);
//                rowCell.setCellStyle(styleTwo);
//                rowCell.setCellValue(sum);
                hejiSum+=sum;
            }
            row =sheet.getRow(rowIndex-sessionDatas.length);
            len = row.getLastCellNum();
            rowCell = row.createCell(len);
            rowCell.setCellStyle(styleTwo);
            rowCell.setCellValue(hejiSum);
            sheet.addMergedRegion(new CellRangeAddress(rowIndex-sessionDatas.length+1,rowIndex,len,len));
        }
        row = sheet.getRow(0);
        rowCell = row.createCell(0);
        rowCell.setCellValue("招录统计-基地");
        rowCell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,len));

        row = sheet.createRow(rowIndex);
        rowCell = row.createCell(0);
        rowCell.setCellValue("合计");
        rowCell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,1));
        for(int j=0;j<  keyList.size();j++){
            rowCell = row.createCell(j+2);
            rowCell.setCellStyle(styleTwo);
            if(j==0) rowCell.setCellValue(sum3501);
            if(j==1) rowCell.setCellValue(sum3502);
            if(j==2) rowCell.setCellValue(sum3601);
            if(j==3) rowCell.setCellValue(sum3602);
            if(j==4) rowCell.setCellValue(sum6000);
        }
        rowCell = row.createCell(7);
        rowCell.setCellStyle(styleTwo);
        rowCell.setCellValue(sum3501+sum3502+sum3601+sum3602+sum6000);
//        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,7,8));
    }

}
