package com.pinde.sci.ctrl.osca;


import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.IOscaDoctorScoreBiz;
import com.pinde.sci.biz.osca.ISiteInformationBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.osca.AuditStatusEnum;
import com.pinde.sci.enums.osca.DoctorScoreEnum;
import com.pinde.sci.enums.osca.SignStatusEnum;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/osca/doctorScore")
public class OscaDoctorScoreController extends GeneralController{
    @Autowired
    private IOscaDoctorScoreBiz oscaDoctorScoreBiz;
    @Autowired
    private ISiteInformationBiz siteInformationBiz;
    @Autowired
    private IOrgBiz orgBiz;
    /**
     * 成绩管理查询
     */
    @RequestMapping("/gradeManageList")
    public String gradeManageList(Integer currentPage4, String orgName, String clinicalYear,String clinicalFlow, String clinicalName,String speId,String ticketNumber, String actionTypeId,String gradeDoctorName, String trainCategoryId, String resultId,String subjectFlow,String order,String isLocal,HttpServletRequest request, Model model) throws IOException {
//        OscaOrgSpe oos=new OscaOrgSpe();
//        oos.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//        List<OscaOrgSpe> speList=siteInformationBiz.searchOscaOrgSpeList(oos);
        String currYear = DateUtil.getYear();
        model.addAttribute("currYear",currYear);
        if(StringUtil.isBlank(clinicalYear)){
            clinicalYear=currYear;
        }
        if(speId==null) {
            speId="";
            List<SysDict> speList=DictTypeEnum.CheckSpe.getSysDictList();
            if(speList!=null&&speList.size()>0){
                speId=speList.get(0).getDictId();
            }
            OscaSkillsAssessment oscaSkillsAssessment1=new OscaSkillsAssessment();
            oscaSkillsAssessment1.setSpeId(speId);
            oscaSkillsAssessment1.setIsLocal(GlobalConstant.RECORD_STATUS_Y);
            oscaSkillsAssessment1.setClinicalYear(clinicalYear);
            oscaSkillsAssessment1.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            List<OscaSkillsAssessment> osas=oscaDoctorScoreBiz.selectskillsAssessmentList(oscaSkillsAssessment1);
            if(speId==""){
                osas=new ArrayList<>();
            }
            if(osas!=null&&osas.size()>0){
                clinicalFlow=osas.get(0).getClinicalFlow();
                clinicalName=osas.get(0).getClinicalName();
            }
        }
        String orgFlow="";
        if(GlobalContext.getCurrentUser()!=null){
            orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
        }
        OscaSkillsAssessment oscaSkillsAssessment=new OscaSkillsAssessment();
        oscaSkillsAssessment.setSpeId(speId);
        oscaSkillsAssessment.setClinicalYear(clinicalYear);
        oscaSkillsAssessment.setIsLocal(isLocal==null?GlobalConstant.RECORD_STATUS_Y:isLocal);
        oscaSkillsAssessment.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        List<OscaSkillsAssessment> osaList=oscaDoctorScoreBiz.selectskillsAssessmentList(oscaSkillsAssessment);
        if(speId==""){
            osaList=new ArrayList<>();
        }
        Map<String,Object> param = new HashMap<>();
        param.put("clinicalFlow",clinicalFlow);
        param.put("auditStatusId",AuditStatusEnum.Passed.getId());
        param.put("signStatusId", SignStatusEnum.SignIn.getId());
        param.put("ticketNumber", ticketNumber);
        param.put("gradeDoctorName", gradeDoctorName);
        param.put("trainCategoryId", trainCategoryId);
        param.put("resultId", resultId);
        param.put("order", order);
        param.put("orgName",orgName);
//        if(AssessmentProEnum.HospitalPlan.getId().equals(actionTypeId)){
            param.put("orgFlow",orgFlow);
//        }
        PageHelper.startPage(currentPage4,getPageSize(request));
        List<Map<String,Object>> gradeList = oscaDoctorScoreBiz.queryGradeList(param);
        List<OscaDoctorAssessment> odaList =new ArrayList<>();
        if(clinicalFlow==null){
            gradeList = new ArrayList<>();
        }
        if(clinicalFlow!=null){
            odaList = oscaDoctorScoreBiz.queryDoctorList(clinicalFlow);
        }
        OscaSkillsAssessment osa = oscaDoctorScoreBiz.queryDataByFlow(clinicalFlow);
        subjectFlow="";
        if(osa!=null){
            subjectFlow=osa.getSubjectFlow();
        }
        List<OscaSubjectStation> stationList = oscaDoctorScoreBiz.queryStationList(subjectFlow);
        int passedCount = 0;
        if(null != odaList && odaList.size() > 0){
            for(OscaDoctorAssessment oda : odaList){
                if(DoctorScoreEnum.Passed.getId().equals(oda.getIsPass())){
                    passedCount++;
                }
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        model.addAttribute("percent",(odaList == null || odaList.size() == 0)?"":df.format((double)passedCount * 100 / (double)odaList.size()));
        model.addAttribute("osa",osa);
        model.addAttribute("osaList",osaList);
        model.addAttribute("gradeList",gradeList);
        model.addAttribute("stationList",stationList);
//        model.addAttribute("clinicalName",clinicalName);
        model.addAttribute("clinicalFlow",clinicalFlow);
        model.addAttribute("subjectFlow",subjectFlow);
//        model.addAttribute("speList",speList);
        //当前考点，当前年份，所有专业所有学生总通过率
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("isPass","Passed");
        paramMap.put("year",clinicalYear==null?currYear:clinicalYear);
        paramMap.put("isLocal","N");
        paramMap.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        Map<String,Object> paramMap2 = new HashMap<>();
        paramMap2.put("year",clinicalYear==null?currYear:clinicalYear);
        paramMap2.put("isLocal","N");
        paramMap2.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        paramMap2.put("range","1");
        String allPercent = oscaDoctorScoreBiz.getPassPercent(paramMap,paramMap2);
        model.addAttribute("allPercent",allPercent);
        return "osca/examResult/doctorScoreInfoList";
    }

    /**
     * 培训专业与考核方案的配置关系查询
     */
    @RequestMapping(value="/querySpeRelation")
    @ResponseBody
    public List<OscaSkillsAssessment> querySpeRelation(String speId, String actionTypeId,String clinicalYear,String isLocal,String flag,String orgFlow2){
       if(speId!=""&&speId!=null){
           OscaSkillsAssessment oscaSkillsAssessment=new OscaSkillsAssessment();
           oscaSkillsAssessment.setSpeId(speId);
           oscaSkillsAssessment.setActionTypeId(actionTypeId);
           oscaSkillsAssessment.setClinicalYear(clinicalYear);
           oscaSkillsAssessment.setIsReleased(GlobalConstant.RECORD_STATUS_Y);
           oscaSkillsAssessment.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
           if("city".equals(flag)){
               if(StringUtil.isNotBlank(isLocal)&&GlobalConstant.FLAG_N.equals(isLocal)){
                   oscaSkillsAssessment.setIsGradeReleased(GlobalConstant.IS_EXAM_TEA_N);
               }
               oscaSkillsAssessment.setOrgFlow(orgFlow2);
           }
           oscaSkillsAssessment.setIsLocal(isLocal);
           return oscaDoctorScoreBiz.selectskillsAssessmentList(oscaSkillsAssessment);
       }else {
           return new ArrayList<>();
       }
    }

    /**
     * 学员各站成绩导出操作
     */
    @RequestMapping(value="/exportScoreExcel", method={RequestMethod.POST,RequestMethod.GET})
    public void exportScoreExcel(String orgName, String clinicalYear,String clinicalFlow, String clinicalName,String speId,String ticketNumber, String actionTypeId,String gradeDoctorName, String trainCategoryId, String resultId,String subjectFlow,String order,HttpServletResponse response) throws Exception {
        Map<String,Object> param = new HashMap<>();
        param.put("clinicalFlow",clinicalFlow);
        param.put("auditStatusId",AuditStatusEnum.Passed.getId());
        param.put("signStatusId", SignStatusEnum.SignIn.getId());
        param.put("ticketNumber", ticketNumber);
        param.put("gradeDoctorName", gradeDoctorName);
        param.put("trainCategoryId", trainCategoryId);
        param.put("resultId", resultId);
        param.put("order", order);
        List<Map<String,Object>> gradeList = oscaDoctorScoreBiz.queryGradeList(param);
        OscaSkillsAssessment osa = oscaDoctorScoreBiz.queryDataByFlow(clinicalFlow);
        List<OscaSubjectStation> stationList = oscaDoctorScoreBiz.queryStationList(subjectFlow);
        List<String> titleLst = new ArrayList<>();
        titleLst.add("准考证号");
        titleLst.add("姓名");
        titleLst.add("性别");
        titleLst.add("身份证号");
        titleLst.add("培训基地");
        titleLst.add("所在单位");
        titleLst.add("培训专业");
        titleLst.add("考试阶段");
        if(null != stationList && stationList.size() > 0){
            for(OscaSubjectStation station : stationList){
                titleLst.add(station.getStationName());
            }
        }
        titleLst.add("总分");
        titleLst.add("考试结果");
        String fileName = "学员成绩信息.xls";
        String[] titles = titleLst.toArray(new String[titleLst.size()]);
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("学员成绩信息");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        //列宽自适应
        HSSFRow row = sheet.createRow(0);
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = row.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
        }
        int rownum = 1;
        if (gradeList != null && !gradeList.isEmpty()) {
            for (Map<String, Object> map : gradeList) {
                int scoreSum=0;
                HSSFRow hr = sheet.createRow(rownum);
                HSSFCell cell = hr.createCell(0);
                cell.setCellValue((String) map.get("TICKET_NUMBER"));
                HSSFCell cell1 = hr.createCell(1);
                cell1.setCellValue((String) map.get("DOCTOR_NAME"));
                HSSFCell cell2 = hr.createCell(2);
                cell2.setCellValue((String) map.get("SEX_NAME"));
                HSSFCell cell3= hr.createCell(3);
                cell3.setCellValue((String) map.get("ID_NO"));
                HSSFCell cell4 = hr.createCell(4);
                cell4.setCellValue((String) map.get("ORG_NAME"));
                HSSFCell cell5 = hr.createCell(5);
                cell5.setCellValue((String) map.get("WORK_ORG_NAME"));
                HSSFCell cell6 = hr.createCell(6);
                cell6.setCellValue((String) map.get("SPE_NAME"));
                HSSFCell cell7 = hr.createCell(7);
                cell7.setCellValue((String) map.get("TRAINING_TYPE_NAME"));
                for(int i = 0;i < stationList.size(); i++){
                    if(null != map.get("STATION_NAME") && null != map.get("EXAM_SCORE")){
                        HSSFCell hc = hr.createCell(8+i);
                        String [] scoreArry = ((String) map.get("EXAM_SCORE")).split(",");
                        String [] stationArry = ((String) map.get("STATION_NAME")).split(",");
                        for(int j = 0;j < stationArry.length; j++){
                            if((row.getCell(8+i).toString()).equals(stationArry[j])){
                                hc.setCellValue("*".equals(scoreArry[j])?"":(int)(Double.parseDouble(scoreArry[j])+0.5)+"");
                                if(!"*".equals(scoreArry[j])){
                                    scoreSum=scoreSum+(int)(Double.parseDouble(scoreArry[j])+0.5);
                                }
                                break;
                            }
                        }
                    }
                }
                HSSFCell cellSum = hr.createCell(8+stationList.size());
                cellSum.setCellValue(scoreSum==0?"":scoreSum+"");
                HSSFCell cellLast = hr.createCell(9+stationList.size());
                cellLast.setCellValue((String) map.get("IS_PASS_NAME"));
                rownum++;
            }
        }
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    @RequestMapping(value="/exportAllScore", method={RequestMethod.POST,RequestMethod.GET})
    public void exportAllScore(String clinicalYear,String isLocal,String flag,HttpServletResponse response) throws Exception {
        List<String> orgList=new ArrayList<>();
        //当前地市
        SysUser currentUser = GlobalContext.getCurrentUser();
        String currentUserOrgFlow = currentUser.getOrgFlow();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUserOrgFlow);
        String orgCityId = currentOrg.getOrgCityId();
        //考点：
        SysOrg org = new SysOrg();
        org.setOrgCityId(orgCityId);
        org.setIsExamOrg("Y");
        List<SysOrg> examOrgList = orgBiz.queryAllSysOrg(org);
        if(examOrgList!=null&&examOrgList.size()>0){
            for (SysOrg so:examOrgList) {
                orgList.add(so.getOrgFlow());
            }
        }
        Map<String,Object> param = new HashMap<>();
        param.put("clinicalYear",clinicalYear);
        param.put("isLocal", isLocal);
        if("city".equals(flag)){
            param.put("orgFlowList", orgList);
            param.put("role", "city");
        }else {
            param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        }
        List<Map<String,Object>> gradeList = oscaDoctorScoreBiz.queryAllGradeList(param);
        List<String> titleLst = new ArrayList<>();
        titleLst.add("准考证号");
        titleLst.add("姓名");
        titleLst.add("性别");
        titleLst.add("身份证号");
        titleLst.add("培训基地");
        titleLst.add("所在单位");
        titleLst.add("培训专业");
        titleLst.add("考试阶段");
        titleLst.add("考核名称");
        titleLst.add("第一站");
        titleLst.add("第二站");
        titleLst.add("第三站");
        titleLst.add("第四站");
        titleLst.add("第五站");
        titleLst.add("第六站");
        titleLst.add("第七站");
        titleLst.add("第八站");
        titleLst.add("第九站");
        titleLst.add("总分");
        titleLst.add("考试结果");
        String typeName=GlobalConstant.FLAG_Y.equals(isLocal)?"院内考核":"结业考核";
        String fileName = clinicalYear+typeName+"学员成绩信息.xls";
        String[] titles = titleLst.toArray(new String[titleLst.size()]);
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("学员成绩信息");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        //列宽自适应
        HSSFRow row = sheet.createRow(0);
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = row.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
        }
        int rownum = 1;
        if (gradeList != null && !gradeList.isEmpty()) {
            for (Map<String, Object> map : gradeList) {
                int scoreSum=0;
                HSSFRow hr = sheet.createRow(rownum);
                HSSFCell cell = hr.createCell(0);
                cell.setCellValue((String) map.get("TICKET_NUMBER"));
                HSSFCell cell1 = hr.createCell(1);
                cell1.setCellValue((String) map.get("DOCTOR_NAME"));
                HSSFCell cell2 = hr.createCell(2);
                cell2.setCellValue((String) map.get("SEX_NAME"));
                HSSFCell cell3= hr.createCell(3);
                cell3.setCellValue((String) map.get("ID_NO"));


                HSSFCell cell4 = hr.createCell(4);
                cell4.setCellValue((String) map.get("ORG_NAME"));
                HSSFCell cell5 = hr.createCell(5);
                cell5.setCellValue((String) map.get("WORK_ORG_NAME"));
                HSSFCell cell6 = hr.createCell(6);
                cell6.setCellValue((String) map.get("SPE_NAME"));
                HSSFCell cell7 = hr.createCell(7);
                cell7.setCellValue((String) map.get("TRAINING_TYPE_NAME"));
                HSSFCell cell8 = hr.createCell(8);
                cell8.setCellValue((String) map.get("CLINICAL_NAME"));
                for(int i = 0;i < 9; i++){
                    if(null != map.get("STATION_NAME") && null != map.get("EXAM_SCORE")){
                        HSSFCell hc = hr.createCell(9+i);
                        String [] scoreArry = ((String) map.get("EXAM_SCORE")).split(",");
                        String [] stationArry = ((String) map.get("STATION_NAME")).split(",");
                        for(int j = 0;j < stationArry.length; j++){
                            if((row.getCell(9+i).toString()).equals(stationArry[j])){
                                hc.setCellValue("*".equals(scoreArry[j])?"":(int)(Double.parseDouble(scoreArry[j])+0.5)+"");
                                if(!"*".equals(scoreArry[j])){
                                    scoreSum=scoreSum+(int)(Double.parseDouble(scoreArry[j])+0.5);
                                }
                                break;
                            }
                        }
                    }
                }
                HSSFCell cellSum = hr.createCell(18);
                cellSum.setCellValue(scoreSum==0?"":scoreSum+"");
                HSSFCell cellLast = hr.createCell(19);
                cellLast.setCellValue((String) map.get("IS_PASS_NAME"));
                rownum++;
            }
        }
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }
}
