package com.pinde.sci.ctrl.res;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.impl.ResPersonnelChangeReportImpl;
import com.pinde.sci.biz.res.impl.ResPersonnelStatisticsImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.DateUtil;
import com.pinde.sci.dao.base.PersonnelCollaborativeBaseMapper;
import com.pinde.sci.dao.base.PersonnelStatisticsMapper;
import com.pinde.sci.job.LocalSchdualJob;
import com.pinde.sci.model.mo.PersonnelChangeReport;
import com.pinde.sci.model.mo.PersonnelStatisticsByName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Selection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/jsres/personnelStatistics")
public class ResPersonnelController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(ResPersonnelController.class);
    @Autowired
    private ResPersonnelStatisticsImpl resPersonnelStatisticsImpl;
    @Autowired
    private ResPersonnelChangeReportImpl resPersonnelChangeReport;
    @Autowired
    private PersonnelCollaborativeBaseMapper personnelCollaborativeBaseMapper;

    @RequestMapping(value = "/personnelTab")
    public String personnelTab(){return "/jsres/global/personnelStatistics/personnelTab";}

    /**
     * 人员情况统计查询
     */
    @RequestMapping(value = "/selectPersonnelStatistics")
    public String selectPersonnelStatistics(Model model) {
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        boolean base = resPersonnelStatisticsImpl.isCollaborativelBase(orgFlow);
        String isCollaborativelBase = "";
        //     如果是协同基地，标记为X。 国家基地标记为G
        if (base){
            isCollaborativelBase = "X";
        } else {
            isCollaborativelBase = "G";
        }
        model.addAttribute("isBase",isCollaborativelBase);
        return "/jsres/global/personnelStatistics/personnelStatistics";
    }

    @RequestMapping(value = "getIsBase")
    @ResponseBody
    public String getIsBase(){
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        boolean base = resPersonnelStatisticsImpl.isCollaborativelBase(orgFlow);
        String isCollaborativelBase = "";
        //     如果是协同基地，标记为X。 国家基地标记为G
        if (base){
            isCollaborativelBase = "X";
        } else {
            isCollaborativelBase = "G";
        }
        return isCollaborativelBase;
    }
    public String monthZero(int month){
        String StrMonth="";
          if(month<10){
              StrMonth="0"+month;
          }else{
              StrMonth=""+month;
          }
          return StrMonth;
    }
    @RequestMapping(value = "selectSection")
    @ResponseBody
    public List selectSection(Model model, String isContain,String monthDate){
        logger.info("monthDate------"+monthDate);
        logger.info("isContain------"+isContain);
        /*Calendar cal = Calendar.getInstance();
        int lastmonth = cal.get(Calendar.MONTH) ;
        int month = cal.get(Calendar.MONTH) + 1;
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String lastDateMonth = year + "-" + monthZero(lastmonth);
        String dateMonth = year + "-" + monthZero(month);
        String strDateMonth = year + monthZero(month);*/
        String lastDateMonth = DateUtil.addMonth(monthDate,-1)/*year + "-" + monthZero(lastmonth)*/;
        String dateMonth = monthDate/*year + "-" + monthZero(month)*/;
        String strDateMonth = monthDate.split("-")[0]+monthDate.split("-")[1]/*year + monthZero(month)*/;

        //获取当前角色的所属基地，true协同基地，false国家基地
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        HashMap<String, String> isBase = new HashMap<>();
        //存放所有数据
        List<PersonnelStatisticsByName> selectAll = new ArrayList<>();
        try {
            boolean base = resPersonnelStatisticsImpl.isCollaborativelBase(orgFlow);
            String isCollaborativelBase = "";
            //     如果是协同基地，标记为X。 国家基地标记为G
            if (base){
                isCollaborativelBase = "X";
            } else {
                isCollaborativelBase = "G";
            }
            isBase.put("isBase",isCollaborativelBase);
            model.addAttribute("isBase",isCollaborativelBase);

            if (isCollaborativelBase.equals("G")){
                //获取到所有国家基地的科室人数
                List<PersonnelStatisticsByName> selectSection = resPersonnelStatisticsImpl.selectSection(lastDateMonth, dateMonth, strDateMonth,orgFlow);
                //科室  住院医师
                Map<String,Object> sessionNumbermap=new HashMap<>();
                //科室  在校专硕
                Map<String,Object> sessionNumbermap2=new HashMap<>();

                Integer idSectionNum = 1;
                Integer idGradeNum = selectSection.size()+1;
                Integer idCollaborative = 500;
                Integer lastSum = 0;
                Integer momentSum = 0;
                for (int i = 0; i < selectSection.size(); i++) {
                    //住院医师
                    sessionNumbermap.put("orgFlow",orgFlow);
                    String[] docTypeList={"Company", "CompanyEntrust", "Social"};
                    sessionNumbermap.put("docTypeList",docTypeList);
                    sessionNumbermap.put("monthdate", strDateMonth);//"201910"
                    sessionNumbermap.put("speName", selectSection.get(i).getTrainingSpeName());
                    //在校专硕
                    sessionNumbermap2.put("orgFlow",orgFlow);
                    String[] docTypeList2={"Graduate"};
                    sessionNumbermap2.put("docTypeList",docTypeList2);
                    sessionNumbermap2.put("monthdate", strDateMonth);//"201910"
                    sessionNumbermap2.put("speName", selectSection.get(i).getTrainingSpeName());

                    //设置id
                    selectSection.get(i).setId(idSectionNum);
                    selectSection.get(i).setPid(0);
                    //获取本月在培人数
                    Integer residentNum = selectSection.get(i).getResidentNum();
                    //获取上月在培人数
                    Integer inSchoolNum = selectSection.get(i).getInSchoolNum();
                   /* Integer residentNum = resPersonnelStatisticsImpl.inTheNumOf(sessionNumbermap);
                    Integer inSchoolNum = resPersonnelStatisticsImpl.inTheNumOf(sessionNumbermap2);*/

                    Integer residentRecruitNum = selectSection.get(i).getResidentRecruitNum();
                    Integer inSchoolRecruitNum = selectSection.get(i).getInSchoolRecruitNum();
                    Integer residentExaminedNum = selectSection.get(i).getResidentExaminedNum();
                    Integer inSchoolExaminedNum = selectSection.get(i).getInSchoolExaminedNum();
                    Integer residentGraduatNum = selectSection.get(i).getResidentGraduatNum();
                    Integer inSchoolGraduatNum = selectSection.get(i).getInSchoolGraduatNum();
                    Integer residentReturnNum = selectSection.get(i).getResidentReturnNum();
                    Integer inSchoolReturnNum = selectSection.get(i).getInSchoolReturnNum();
                    Integer residentOutNum = selectSection.get(i).getResidentOutNum();
                    Integer inSchoolOutNum = selectSection.get(i).getInSchoolOutNum();
                    Integer residentInNum = selectSection.get(i).getResidentInNum();
                    Integer inSchoolInNum= selectSection.get(i).getInSchoolInNum();
                    //上月住院医师在培
                    Integer lastResidentNum = selectSection.get(i).getLastResidentNum();
                    //上月在校专硕在培
                    Integer lastInSchoolNum = selectSection.get(i).getLastInSchoolNum();
//                    Integer inSchoolNum = selectSection.get(i).getInSchoolNum();
                    //获取到该科室下所有的年级
                    List<PersonnelStatisticsByName> selectGrade = resPersonnelStatisticsImpl.selectGrade(lastDateMonth, dateMonth, strDateMonth, selectSection.get(i).getTrainingSpeName(),orgFlow);
                    if (selectGrade.size()>0){
                        //遍历年级
                        for (int j = 0; j < selectGrade.size(); j++) {
                            selectGrade.get(j).setTrainingSpeName(selectGrade.get(j).getSessionNumber());
                            sessionNumbermap.put("orgFlow",orgFlow);
                            sessionNumbermap2.put("orgFlow",orgFlow);
                            sessionNumbermap.put("sessionNumber",selectGrade.get(j).getSessionNumber());
                            sessionNumbermap2.put("sessionNumber",selectGrade.get(j).getSessionNumber());
                            Integer sessionResidentNum = resPersonnelStatisticsImpl.inTheNumOf(sessionNumbermap);
                            Integer sessionInSchoolNum = resPersonnelStatisticsImpl.inTheNumOf(sessionNumbermap2);
                            //绑定父id
                            selectGrade.get(j).setPid(idSectionNum);
                            selectGrade.get(j).setId(idGradeNum);
                            //如果点击包含协同
                            if (isContain != null && isContain!="" && "isContain".equals(isContain)){
                                //查出所有的协同基地
                                List<PersonnelStatisticsByName> selectCollaborativeBase = resPersonnelStatisticsImpl.selectCollaborativeBase(lastDateMonth,
                                        dateMonth, strDateMonth, selectSection.get(i).getTrainingSpeName(), selectGrade.get(j).getSessionNumber(), orgFlow);
                                for (int k = 0; k < selectCollaborativeBase.size(); k++) {
                                    List<PersonnelStatisticsByName> collaborativeBaseFlow = personnelCollaborativeBaseMapper.selectCollaborativeBaseFlow(selectCollaborativeBase.get(k).getOrgName());

                                    sessionNumbermap.put("orgFlow",collaborativeBaseFlow.get(0).getOrgFlow());
                                    sessionNumbermap2.put("orgFlow",collaborativeBaseFlow.get(0).getOrgFlow());
                                    Integer orgNameResidentNum = resPersonnelStatisticsImpl.inTheNumOf(sessionNumbermap);
                                    Integer orgNameInSchoolNum = resPersonnelStatisticsImpl.inTheNumOf(sessionNumbermap2);
                                    Integer orgNameLastResidentNum = orgNameResidentNum + selectCollaborativeBase.get(k).getResidentReturnNum() + selectCollaborativeBase.get(k).getResidentRecruitNum()
                                            + selectCollaborativeBase.get(k).getResidentOutNum() + selectCollaborativeBase.get(k).getResidentInNum() + selectCollaborativeBase.get(k).getResidentGraduatNum()
                                            + selectCollaborativeBase.get(k).getResidentExaminedNum();
                                    Integer orgNameLastInSchoolNum = orgNameInSchoolNum + selectCollaborativeBase.get(k).getInSchoolReturnNum() + selectCollaborativeBase.get(k).getInSchoolRecruitNum()
                                            + selectCollaborativeBase.get(k).getInSchoolOutNum() + selectCollaborativeBase.get(k).getInSchoolInNum() + selectCollaborativeBase.get(k).getInSchoolGraduatNum()
                                            + selectCollaborativeBase.get(k).getInSchoolExaminedNum();
                                    lastInSchoolNum = lastInSchoolNum + orgNameLastInSchoolNum;
                                    lastResidentNum = lastResidentNum + orgNameLastResidentNum;
                                    inSchoolNum = inSchoolNum + orgNameInSchoolNum;
                                    residentNum = residentNum + orgNameResidentNum;
                                    residentRecruitNum = residentRecruitNum + selectCollaborativeBase.get(k).getResidentRecruitNum();
                                    inSchoolRecruitNum = inSchoolRecruitNum + selectCollaborativeBase.get(k).getInSchoolRecruitNum();
                                    residentExaminedNum = residentExaminedNum + selectCollaborativeBase.get(k).getResidentExaminedNum();
                                    inSchoolExaminedNum = inSchoolExaminedNum + selectCollaborativeBase.get(k).getInSchoolExaminedNum();
                                    residentGraduatNum = residentGraduatNum + selectCollaborativeBase.get(k).getResidentGraduatNum();
                                    inSchoolGraduatNum = inSchoolGraduatNum + selectCollaborativeBase.get(k).getInSchoolGraduatNum();
                                    residentReturnNum = residentReturnNum + selectCollaborativeBase.get(k).getResidentReturnNum();
                                    inSchoolReturnNum = inSchoolReturnNum + selectCollaborativeBase.get(k).getInSchoolReturnNum();
                                    residentOutNum = residentOutNum + selectCollaborativeBase.get(k).getResidentOutNum();
                                    inSchoolOutNum = inSchoolOutNum + selectCollaborativeBase.get(k).getInSchoolOutNum();
                                    residentInNum = residentInNum + selectCollaborativeBase.get(k).getResidentInNum();
                                    inSchoolInNum = residentReturnNum + selectCollaborativeBase.get(k).getInSchoolInNum();

                                    selectCollaborativeBase.get(k).setLastInSchoolNum(orgNameLastInSchoolNum);
                                    selectCollaborativeBase.get(k).setLastResidentNum(orgNameLastResidentNum);
                                    selectCollaborativeBase.get(k).setResidentNum(orgNameResidentNum);
                                    selectCollaborativeBase.get(k).setInSchoolNum(orgNameInSchoolNum);
                                    selectCollaborativeBase.get(k).setLastSum(orgNameLastInSchoolNum + orgNameLastResidentNum);
                                    selectCollaborativeBase.get(k).setMomentSum(orgNameInSchoolNum + orgNameResidentNum);
                                    selectCollaborativeBase.get(k).setTrainingSpeName(selectCollaborativeBase.get(k).getOrgName());
                                    selectCollaborativeBase.get(k).setPid(idGradeNum);
                                    selectCollaborativeBase.get(k).setId(idCollaborative);
                                    selectAll.add(selectCollaborativeBase.get(k));
                                    idCollaborative++;
                                }
                            }
                            selectGrade.get(j).setInSchoolNum(sessionInSchoolNum);
                            selectGrade.get(j).setResidentNum(sessionResidentNum);
                            Integer sessionLastInSchoolNum = sessionInSchoolNum + selectGrade.get(j).getInSchoolExaminedNum() + selectGrade.get(j).getInSchoolGraduatNum()
                                    + selectGrade.get(j).getInSchoolOutNum() + selectGrade.get(j).getInSchoolOutNum() + selectGrade.get(j).getInSchoolRecruitNum() + selectGrade.get(j).getInSchoolReturnNum();
                            Integer sessionLastResidentNum = sessionResidentNum + selectGrade.get(j).getResidentExaminedNum() + selectGrade.get(j).getResidentGraduatNum()
                                    +selectGrade.get(j).getResidentInNum() + selectGrade.get(j).getResidentOutNum() + selectGrade.get(j).getResidentRecruitNum() + selectGrade.get(j).getResidentReturnNum();
                            selectGrade.get(j).setLastInSchoolNum(sessionLastInSchoolNum);
                            selectGrade.get(j).setLastResidentNum(sessionLastResidentNum);
                            selectGrade.get(j).setLastSum(sessionLastInSchoolNum + sessionLastResidentNum);
                            selectGrade.get(j).setMomentSum(sessionInSchoolNum + sessionResidentNum);
                            selectAll.add(selectGrade.get(j));
                            idGradeNum++;
                        }
                    }
                    selectSection.get(i).setLastInSchoolNum(lastInSchoolNum);
                    selectSection.get(i).setInSchoolNum(inSchoolNum);
                    selectSection.get(i).setLastResidentNum(lastResidentNum);
                    selectSection.get(i).setResidentNum(residentNum);
                    selectSection.get(i).setResidentRecruitNum(residentRecruitNum);
                    selectSection.get(i).setInSchoolRecruitNum(inSchoolRecruitNum);
                    selectSection.get(i).setResidentExaminedNum(residentExaminedNum);
                    selectSection.get(i).setInSchoolExaminedNum(inSchoolExaminedNum);
                    selectSection.get(i).setResidentGraduatNum(residentGraduatNum);
                    selectSection.get(i).setInSchoolGraduatNum(inSchoolGraduatNum);
                    selectSection.get(i).setResidentReturnNum(residentReturnNum);
                    selectSection.get(i).setInSchoolReturnNum(inSchoolReturnNum);
                    selectSection.get(i).setResidentOutNum(residentOutNum);
                    selectSection.get(i).setInSchoolOutNum(inSchoolOutNum);
                    selectSection.get(i).setResidentInNum(residentInNum);
                    selectSection.get(i).setInSchoolInNum(inSchoolInNum);
                    selectSection.get(i).setLastSum(lastInSchoolNum + lastResidentNum);
                    selectSection.get(i).setMomentSum(residentNum + inSchoolNum);
                    idSectionNum++;
                    //添加到集合
                    selectAll.add(selectSection.get(i));
                }
            }
            //协同基地
            if (isCollaborativelBase.equals("X")){
                //科室  住院医师
                Map<String,Object> sessionNumbermap=new HashMap<>();
                //科室  在校专硕
                Map<String,Object> sessionNumbermap2=new HashMap<>();
//                List<String> sessionNumberDistinct=new ArrayList<>();
                //住院医师
                sessionNumbermap.put("orgFlow",orgFlow);
                String[] docTypeList={"Company", "CompanyEntrust", "Social"};
                sessionNumbermap.put("docTypeList",docTypeList);
                //在校专硕
                sessionNumbermap2.put("orgFlow",orgFlow);
                String[] docTypeList2={"Graduate"};
                sessionNumbermap2.put("docTypeList",docTypeList2);

                //获取到所有协同基地的科室人数
                List<PersonnelStatisticsByName> selectSection = resPersonnelStatisticsImpl.selectSection(lastDateMonth, dateMonth, strDateMonth,orgFlow);
                Integer idGradeNum = selectSection.size()+1;
                Integer idSectionNum = 1;
                for (int i = 0; i < selectSection.size(); i++) {
                    sessionNumbermap.put("speName", selectSection.get(i).getTrainingSpeName());
                    sessionNumbermap.put("sessionNumber",null);
                    sessionNumbermap2.put("sessionNumber",null);
                    //获取本月在培人数
                    Integer residentNum = resPersonnelStatisticsImpl.inTheNumOf(sessionNumbermap);
                    Integer inSchoolNum = resPersonnelStatisticsImpl.inTheNumOf(sessionNumbermap2);
                    Integer residentRecruitNum = selectSection.get(i).getResidentRecruitNum();
                    Integer inSchoolRecruitNum = selectSection.get(i).getInSchoolRecruitNum();
                    Integer residentExaminedNum = selectSection.get(i).getResidentExaminedNum();
                    Integer inSchoolExaminedNum = selectSection.get(i).getInSchoolExaminedNum();
                    Integer residentGraduatNum = selectSection.get(i).getResidentGraduatNum();
                    Integer inSchoolGraduatNum = selectSection.get(i).getInSchoolGraduatNum();
                    Integer residentReturnNum = selectSection.get(i).getResidentReturnNum();
                    Integer inSchoolReturnNum = selectSection.get(i).getInSchoolReturnNum();
                    Integer residentOutNum = selectSection.get(i).getResidentOutNum();
                    Integer inSchoolOutNum = selectSection.get(i).getInSchoolOutNum();
                    Integer residentInNum = selectSection.get(i).getResidentInNum();
                    Integer inSchoolInNum= selectSection.get(i).getInSchoolInNum();
                    //上月在校专硕在培
                    Integer lastInSchoolNum = inSchoolNum + inSchoolRecruitNum + inSchoolExaminedNum + inSchoolGraduatNum + inSchoolOutNum + inSchoolReturnNum + inSchoolInNum;
                    //上月住院医师在培
                    Integer lastResidentNum = residentNum + residentRecruitNum + residentExaminedNum + residentGraduatNum + residentReturnNum + residentOutNum + residentInNum;

                    //设置id
                    selectSection.get(i).setId(idSectionNum);
                    selectSection.get(i).setPid(0);
                    //获取到该科室下所有的年级
                    List<PersonnelStatisticsByName> selectGrade = resPersonnelStatisticsImpl.selectGrade(lastDateMonth, dateMonth, strDateMonth, selectSection.get(i).getTrainingSpeName(),orgFlow);
                    if (selectGrade.size()>0){
                        for (int j = 0; j < selectGrade.size(); j++) {
                            sessionNumbermap.put("sessionNumber",selectGrade.get(j).getSessionNumber());
                            sessionNumbermap2.put("sessionNumber",selectGrade.get(j).getSessionNumber());
                            Integer sessionResidentNum = resPersonnelStatisticsImpl.inTheNumOf(sessionNumbermap);
                            Integer sessionInSchoolNum = resPersonnelStatisticsImpl.inTheNumOf(sessionNumbermap2);

                            selectGrade.get(j).setInSchoolNum(sessionInSchoolNum);
                            selectGrade.get(j).setResidentNum(sessionResidentNum);
                            Integer sessionLastInSchoolNum = sessionInSchoolNum + selectGrade.get(j).getInSchoolExaminedNum() + selectGrade.get(j).getInSchoolGraduatNum()
                                    + selectGrade.get(j).getInSchoolOutNum() + selectGrade.get(j).getInSchoolOutNum() + selectGrade.get(j).getInSchoolRecruitNum() + selectGrade.get(j).getInSchoolReturnNum();
                            Integer sessionLastResidentNum = sessionResidentNum + selectGrade.get(j).getResidentExaminedNum() + selectGrade.get(j).getResidentGraduatNum()
                                    +selectGrade.get(j).getResidentInNum() + selectGrade.get(j).getResidentOutNum() + selectGrade.get(j).getResidentRecruitNum() + selectGrade.get(j).getResidentReturnNum();
                            selectGrade.get(j).setLastInSchoolNum(sessionLastInSchoolNum);
                            selectGrade.get(j).setLastResidentNum(sessionLastResidentNum);
                            selectGrade.get(j).setLastSum(sessionLastInSchoolNum + sessionLastResidentNum);
                            selectGrade.get(j).setMomentSum(sessionInSchoolNum + sessionResidentNum);
                            selectAll.add(selectGrade.get(j));
                            idGradeNum++;
                        }
                    }
                    selectSection.get(i).setLastInSchoolNum(lastInSchoolNum);
                    selectSection.get(i).setInSchoolNum(inSchoolNum);
                    selectSection.get(i).setLastResidentNum(lastResidentNum);
                    selectSection.get(i).setResidentNum(residentNum);
                    selectSection.get(i).setLastSum(lastInSchoolNum + lastResidentNum);
                    selectSection.get(i).setMomentSum(residentNum + inSchoolNum);
                    idSectionNum++;
                    selectAll.add(selectSection.get(i));
                }
            }
            return selectAll;
        }catch (RuntimeException e){
            List<Map<String,String>> list=new ArrayList<>();
            Map<String,String> map=new HashMap<>();
            map.put("error",e.getMessage());
            list.add(map);
            return list;
        }
    }

    /**
     * 人员异动报表
     * @return
     */
    @RequestMapping(value = "personnelChangeReport")
    public String personnelChangeReport(Model model){
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        boolean base = resPersonnelStatisticsImpl.isCollaborativelBase(orgFlow);
        String isCollaborativelBase = "";
        //     如果是协同基地，标记为X。 国家基地标记为G
        if (base){
            isCollaborativelBase = "X";
        } else {
            isCollaborativelBase = "G";
        }
        model.addAttribute("isBase",isCollaborativelBase);
        return "/jsres/global/personnelStatistics/PersonnelChangeReport";
    }

    /**
     * 页面展示
     */
    @RequestMapping(value = "personnelChangeSelect")
    @ResponseBody
    public List personnelChangeSelect(Model model ,String isContain2,String monthDate2){
        logger.info("monthDate2------"+monthDate2);
        logger.info("isContain2------"+isContain2);
       /* Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) ;
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String monthDate = year + monthZero(month); */ //上月
        String lastMonthh= DateUtil.addMonth(monthDate2,-1);
        String monthDate = lastMonthh.split("-")[0]+lastMonthh.split("-")[1];/*year + monthZero(month)*/;

        //获取当前角色的所属基地，true协同基地，false国家基地
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        List<PersonnelChangeReport> changeReportList = new ArrayList<>();
        try {
            boolean base = resPersonnelStatisticsImpl.isCollaborativelBase(orgFlow);
            HashMap<String, String> isBase = new HashMap<>();
            String isCollaborativelBase = "";
            //     如果是协同基地，标记为X。 国家基地标记为G
            if (base){
                isCollaborativelBase = "X";
            } else {
                isCollaborativelBase = "G";
            }
            isBase.put("isBase",isCollaborativelBase);
            model.addAttribute("isBase",isCollaborativelBase);
            if ("G".equals(isCollaborativelBase)){
                //获取到科室数据
                List<PersonnelChangeReport> selectSectionByChangeTime = resPersonnelChangeReport.selectSectionByChangeTime(monthDate, orgFlow);
                List<PersonnelChangeReport> selectSectionBySpeChange = resPersonnelChangeReport.selectSectionBySpeChange(monthDate, orgFlow);
                ArrayList<PersonnelChangeReport> changeTimeList = new ArrayList<>();
                for (PersonnelChangeReport changeReport : selectSectionByChangeTime) {
                    changeReport.setProfessionalBase(changeReport.getTrainingSpeName());
                    changeReport.setSpeChangeNum(0);
                    changeTimeList.add(changeReport);
                }
                for (PersonnelChangeReport report : selectSectionBySpeChange) {
                    report.setProfessionalBase(report.getTrainingSpeName());
                    report.setChangeTimeNum(0);
                    changeTimeList.add(report);
                }
                //去除重复的科室
                List<PersonnelChangeReport> newList = getNewList(changeTimeList);
                Integer id = 1;
                Integer sessionId = newList.size()+2;
                for (PersonnelChangeReport report : newList) {
                    ArrayList<PersonnelChangeReport> speChangeList = new ArrayList<>();
                    //设置id
                    report.setId(id);
                    report.setPid(null);
                    Integer changeTimeNum = report.getChangeTimeNum();
                    Integer speChangeNum = report.getSpeChangeNum();
                    List<PersonnelChangeReport> sessionByChangeTime = resPersonnelChangeReport.selectSessionByChangeTime(monthDate, orgFlow, report.getProfessionalBase());
                    List<PersonnelChangeReport> sessionBySpeChange = resPersonnelChangeReport.selectSessionBySpeChange(monthDate, orgFlow, report.getProfessionalBase());
                    for (PersonnelChangeReport changeReport : sessionByChangeTime) {
                        changeReport.setProfessionalBase(changeReport.getSessionNumber());
                        changeReport.setSpeChangeNum(0);
                        changeReport.setPid(id);
                        speChangeList.add(changeReport);
                    }
                    for (PersonnelChangeReport changeReport : sessionBySpeChange) {
                        changeReport.setProfessionalBase(changeReport.getSessionNumber());
                        changeReport.setChangeTimeNum(0);
                        changeReport.setPid(id);
                        speChangeList.add(changeReport);
                    }
                    List<PersonnelChangeReport> newList1 = getNewList(speChangeList);
                    Integer orgId = 3000;
                    for (PersonnelChangeReport changeReport : newList1) {
                        changeReport.setId(sessionId);
                        changeReport.setPid(id);
                        //包含协同
                        if (isContain2!=null && isContain2!="" && "isContain2".equals(isContain2)){
                            ArrayList<PersonnelChangeReport> orgNameList = new ArrayList<>();
                            List<PersonnelChangeReport> orgNameByChangeTime = resPersonnelChangeReport.selectOrgNameByChangeTime(monthDate, orgFlow, report.getProfessionalBase(), changeReport.getProfessionalBase());
                            List<PersonnelChangeReport> orgNameBySpeChange = resPersonnelChangeReport.selectOrgNameBySpeChange(monthDate, orgFlow, report.getProfessionalBase(), changeReport.getProfessionalBase());
                            for (PersonnelChangeReport personnelChangeReport : orgNameByChangeTime) {
                                personnelChangeReport.setProfessionalBase(personnelChangeReport.getOrgName());
                                personnelChangeReport.setSpeChangeNum(0);
                                personnelChangeReport.setPid(sessionId);
                                orgNameList.add(personnelChangeReport);
                            }
                            for (PersonnelChangeReport personnelChangeReport : orgNameBySpeChange) {
                                personnelChangeReport.setProfessionalBase(personnelChangeReport.getOrgName());
                                personnelChangeReport.setChangeTimeNum(0);
                                personnelChangeReport.setPid(sessionId);
                                orgNameList.add(personnelChangeReport);
                            }
                            List<PersonnelChangeReport> orgNameNewList = getNewList(orgNameList);
                            for (PersonnelChangeReport personnelChangeReport : orgNameNewList) {
                                //设置id
                                personnelChangeReport.setPid(sessionId);
                                personnelChangeReport.setId(orgId);
                                //将协同数据与科室数据相加
                                changeTimeNum += personnelChangeReport.getChangeTimeNum();
                                speChangeNum += personnelChangeReport.getSpeChangeNum();
                                changeReportList.add(personnelChangeReport);
                                orgId++;
                            }
                        }
                        changeReportList.add(changeReport);
                        sessionId++;
                    }
                    //将数据重新放入对应对象内
                    report.setChangeTimeNum(changeTimeNum);
                    report.setSpeChangeNum(speChangeNum);
                    changeReportList.add(report);
                    id++;
                }
            }
            //协同基地
            if ("X".equals(isCollaborativelBase)){
                List<PersonnelChangeReport> selectSectionByChangeTime = resPersonnelChangeReport.selectSectionByChangeTime(monthDate, orgFlow);
                List<PersonnelChangeReport> selectSectionBySpeChange = resPersonnelChangeReport.selectSectionBySpeChange(monthDate, orgFlow);
                ArrayList<PersonnelChangeReport> changeTimeList = new ArrayList<>();
                for (PersonnelChangeReport changeReport : selectSectionByChangeTime) {
                    changeReport.setProfessionalBase(changeReport.getTrainingSpeName());
                    changeReport.setSpeChangeNum(0);
                    changeTimeList.add(changeReport);
                }
                for (PersonnelChangeReport report : selectSectionBySpeChange) {
                    report.setProfessionalBase(report.getTrainingSpeName());
                    report.setChangeTimeNum(0);
                    changeTimeList.add(report);
                }
                List<PersonnelChangeReport> newList = getNewList(changeTimeList);
                Integer id = 1;
                Integer sessionId = newList.size()+2;
                for (PersonnelChangeReport report : newList) {
                    ArrayList<PersonnelChangeReport> speChangeList = new ArrayList<>();
                    report.setId(id);
                    report.setPid(null);
                    List<PersonnelChangeReport> sessionByChangeTime = resPersonnelChangeReport.selectSessionByChangeTime(monthDate, orgFlow, report.getProfessionalBase());
                    List<PersonnelChangeReport> sessionBySpeChange = resPersonnelChangeReport.selectSessionBySpeChange(monthDate, orgFlow, report.getProfessionalBase());
                    for (PersonnelChangeReport changeReport : sessionByChangeTime) {
                        changeReport.setProfessionalBase(changeReport.getSessionNumber());
                        changeReport.setSpeChangeNum(0);
                        changeReport.setPid(id);
                        speChangeList.add(changeReport);
                    }
                    for (PersonnelChangeReport changeReport : sessionBySpeChange) {
                        changeReport.setProfessionalBase(changeReport.getSessionNumber());
                        changeReport.setChangeTimeNum(0);
                        changeReport.setPid(id);
                        speChangeList.add(changeReport);
                    }
                    List<PersonnelChangeReport> newList1 = getNewList(speChangeList);
                    for (PersonnelChangeReport changeReport : newList1) {
                        changeReport.setId(sessionId);
                        changeReport.setPid(id);
                        changeReportList.add(changeReport);
                        sessionId++;
                    }
                    changeReportList.add(report);
                    id++;
                }
            }
            return changeReportList;
        }catch (RuntimeException e){
            List<Map<String,String>> list = new ArrayList<>();
            Map<String,String> map = new HashMap<>();
            map.put("error",e.getMessage());
            list.add(map);
            return list;
        }
    }

    /**
     * 去除集合内重复的基地，同时数据相加
     * @param list
     * @return
     */
    private static List<PersonnelChangeReport> getNewList(List<PersonnelChangeReport> list) {
        HashMap<String, PersonnelChangeReport> tempMap = new HashMap<>();
        for (PersonnelChangeReport people : list) {
            String temp = people.getProfessionalBase();//获取专业基地名称
            //containsKey(Object key)该方法判断Map集合中是否包含指定的键名，如果包含返回true，不包含返回false
            //containsValue(Object value)该方法判断Map集合中是否包含指定的键值，如果包含返回true，不包含返回false
            if (tempMap.containsKey(temp)) {
                PersonnelChangeReport p = new PersonnelChangeReport();
                p.setProfessionalBase(temp);
                p.setChangeTimeNum(tempMap.get(temp).getChangeTimeNum() + people.getChangeTimeNum());
                //合并相同专业的SpeChangeNum值
                p.setSpeChangeNum(tempMap.get(temp).getSpeChangeNum() + people.getSpeChangeNum());

                //HashMap不允许key重复，当有key重复时，前面key对应的value值会被覆盖
                tempMap.put(temp, p);
            } else {
                tempMap.put(temp, people);
            }
        }

        //去除重复 编号id 的 list
        List<PersonnelChangeReport> newList = new ArrayList<>();
        for(String temp:tempMap.keySet()){
            newList.add(tempMap.get(temp));
        }
        return newList;

    }
}
