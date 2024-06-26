package com.pinde.sci.ctrl.gzykdx;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzykdx.IGzykdxAuditManageBiz;
import com.pinde.sci.biz.gzykdx.IGzykdxSchoolAdminBiz;
import com.pinde.sci.biz.gzykdx.IGzykdxTeaAndDocBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.gzykdx.GzykdxAuditStatusEnum;
import com.pinde.sci.enums.gzykdx.GzykdxDegreeTypeEnum;
import com.pinde.sci.form.gzykdx.GzykdxTeacherExtInfoForm;
import com.pinde.sci.model.mo.PubUserResume;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.TeacherTargetApply;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangshuai
 * @Copyright njpdxx.com
 * <p/>
 * 二级单位审核系列操作
 * @since 2017/3/9
 */

@Controller
@RequestMapping("/gzykdx/orgAudit")
public class GzykdxOrgAuditController extends GeneralController {

    @Autowired
    private IGzykdxAuditManageBiz auditManageBiz;
    @Autowired
    private IPubUserResumeBiz pubUserResumeBiz;
    @Autowired
    private IGzykdxTeaAndDocBiz teaAndDocBiz;
    @Autowired
    private IGzykdxSchoolAdminBiz schoolAdminBiz;
    @Autowired
    private IOrgBiz sysOrgBiz;

    @RequestMapping(value="/teacherApplyList" )
    public String teacherApplyList(String recruitYear, String speId, String researchDirectionName, String userName, String auditStatusId,Integer currentPage,HttpServletRequest request, Model model) throws Exception {
        if(StringUtil.isBlank(recruitYear)){
            recruitYear=DateUtil.getYear();
        }
        Map<String,String> map=new HashMap<>();
        map.put("recruitYear",recruitYear);
        map.put("speId",speId);
        map.put("researchDirectionName",researchDirectionName);
        map.put("userName",userName);
        map.put("auditStatusId",auditStatusId);
        map.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> teacherApplyList=auditManageBiz.selectTeacherApplyListOrg(map);
        if(teacherApplyList!=null&&teacherApplyList.size()>0){
            for (Map<String,Object> temp:teacherApplyList) {
                if(temp!=null&& StringUtil.isNotBlank((String)temp.get("USER_FLOW"))){
                    PubUserResume pubUserResume=pubUserResumeBiz.readPubUserResume((String)temp.get("USER_FLOW"));
                    if(pubUserResume!=null){
                        String xmlContent =  pubUserResume.getUserResume();
                        if(StringUtil.isNotBlank(xmlContent)){
                            GzykdxTeacherExtInfoForm extInfo=null;
                            extInfo=pubUserResumeBiz.converyToJavaBean(xmlContent,GzykdxTeacherExtInfoForm.class);
                            temp.put("infoForm",extInfo);
                        }
                    }
                }
            }
        }
        Map<String,String> map1=new HashMap<>();
        map1.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        map1.put("recruitYear",recruitYear);
        List<Map<String,String>> recruitNumList=auditManageBiz.selectRecruitNum(map1);//机构总指标
        map1.put("isAcademic",GlobalConstant.RECORD_STATUS_Y);
        int academicSum=auditManageBiz.selectAcademicRecruitSum(map1);//学术型已用指标
        map1.remove("isAcademic");
        map1.put("isSpecialized",GlobalConstant.RECORD_STATUS_Y);
        int specializedSum=auditManageBiz.selectSpecializedRecruitSum(map1);//专业型已用指标
        if(recruitNumList!=null&&recruitNumList.size()>0){
            model.addAttribute("recruitNum",recruitNumList.get(0));
        }
//        if(academicSum!=null&&academicSum.get(0)!=null){
            model.addAttribute("academicSum",academicSum);
//        }
//        if(specializedSum!=null&&specializedSum.get(0)!=null){
            model.addAttribute("specializedSum",specializedSum);
//        }
        model.addAttribute("teacherApplyList",teacherApplyList);
        model.addAttribute("recruitYear",recruitYear);
        model.addAttribute("currentPage",currentPage);
        return "/gzykdx/secondaryOrg/auditApplysOrg";
    }

    /**
     * 导师申请审核
     * @param applyFlow
     * @param scope
     * @param auditId
     * @return
     */
    @RequestMapping(value="/auditApply" )
    @ResponseBody
    public String auditApply(String applyFlow,String scope,String auditId,String orgAuditAdvice,String schoolAuditAdvice,Integer currentPage, Model model) throws Exception{
        TeacherTargetApply apply=new TeacherTargetApply();
        apply.setApplyFlow(applyFlow);
        apply.setOrgAuditAdvice(java.net.URLDecoder.decode(orgAuditAdvice, "UTF-8"));
        apply.setSchoolAuditAdvice(java.net.URLDecoder.decode(schoolAuditAdvice, "UTF-8"));
        if("org".equals(scope)){
            apply.setOrgUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            apply.setOrgUserName(GlobalContext.getCurrentUser().getUserName());
            apply.setOrgAuditTime(DateUtil.getCurrDate());
            if("Passed".equals(auditId)){
                apply.setAuditStatusId(GzykdxAuditStatusEnum.WaitingForCommitted.getId());
                apply.setAuditStatusName(GzykdxAuditStatusEnum.WaitingForCommitted.getName());
            }
            if("UnPassed".equals(auditId)){
                apply.setAuditStatusId(GzykdxAuditStatusEnum.UnPassed.getId());
                apply.setAuditStatusName(GzykdxAuditStatusEnum.UnPassed.getName());
            }
            if("SendBack".equals(auditId)){
                apply.setAuditStatusId(GzykdxAuditStatusEnum.OrgSendBack.getId());
                apply.setAuditStatusName(GzykdxAuditStatusEnum.OrgSendBack.getName());
            }
        }if("school".equals(scope)){
            apply.setSchoolAuditTime(DateUtil.getCurrDate());
            if("Passed".equals(auditId)){
                apply.setAuditStatusId(GzykdxAuditStatusEnum.SchoolPassed.getId());
                apply.setAuditStatusName(GzykdxAuditStatusEnum.SchoolPassed.getName());
            }
            if("UnPassed".equals(auditId)){
                apply.setAuditStatusId(GzykdxAuditStatusEnum.UnPassed.getId());
                apply.setAuditStatusName(GzykdxAuditStatusEnum.UnPassed.getName());
            }
            if("SendBack".equals(auditId)){
                apply.setIsSubmit("");
                apply.setAuditStatusId(GzykdxAuditStatusEnum.SchoolSendBack.getId());
                apply.setAuditStatusName(GzykdxAuditStatusEnum.SchoolSendBack.getName());
            }
        }
        model.addAttribute("currentPage",currentPage);
        int num=teaAndDocBiz.editTeacherTargetApply(apply);
        if(num>0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 撤销审核、编辑招生人数
     * @param applyFlow
     * @param auditStatusId
     * @param academicNum
     * @param specializedNum
     * @return
     */
    @RequestMapping(value="/editApplyInfo" )
    @ResponseBody
    public String editApplyInfo(String applyFlow,String auditStatusId,String academicNum,String specializedNum){
        TeacherTargetApply apply=new TeacherTargetApply();
        apply.setApplyFlow(applyFlow);
        apply.setAuditStatusId(auditStatusId);
        apply.setAuditStatusName(GzykdxAuditStatusEnum.getNameById(auditStatusId));
        apply.setAcademicNum(academicNum);
        apply.setSpecializedNum(specializedNum);
        apply.setIsSubmit("");
        if(GzykdxAuditStatusEnum.WaitingForOrg.getId().equals(auditStatusId)){
            apply.setAcademicNum("");
            apply.setSpecializedNum("");
        }
        int num=teaAndDocBiz.editTeacherTargetApply(apply);
        if(num>0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 一键提交导师申请
     * @return
     */
    @RequestMapping(value="/commitAll" )
    @ResponseBody
    public String commitAll(){
        String numStr=countApplyNum1(null,DateUtil.getYear(),GlobalContext.getCurrentUser().getOrgFlow(),null);
        if(StringUtil.isNotBlank(numStr)){
            String n1=numStr.split(",")[0];
            String n2=numStr.split(",")[1];
            if((StringUtil.isNotBlank(n1)&&Integer.parseInt(n1)<0)||(StringUtil.isNotBlank(n2)&&Integer.parseInt(n2)<0)){
                return "所编辑的指标总数大于总指标，无法提交！";
            }
        }
        List<String> applyFlows=new ArrayList<>();
        List<TeacherTargetApply> selectApplyList=auditManageBiz.selectApplyList(GlobalContext.getCurrentUser().getOrgFlow(),GzykdxAuditStatusEnum.WaitingForCommitted.getId());
        if(selectApplyList!=null&&selectApplyList.size()>0){
            for (TeacherTargetApply apply:selectApplyList) {
                if(apply!=null){
                    applyFlows.add(apply.getApplyFlow());
                }
            }
        }
        int num=auditManageBiz.commitAllApply(applyFlows);
        if(num>0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return "当前没有审核通过的指标！";
    }

    /**
     * 提交单个导师申请
     * @param applyFlow
     * @return
     */
    @RequestMapping(value="/commitOneApply" )
    @ResponseBody
    public String commitOneApply(String applyFlow){
        String numStr=countApplyNum1(null,DateUtil.getYear(),GlobalContext.getCurrentUser().getOrgFlow(),null);
        if(StringUtil.isNotBlank(numStr)){
            String n1=numStr.split(",")[0];
            String n2=numStr.split(",")[1];
            if((StringUtil.isNotBlank(n1)&&Integer.parseInt(n1)<0)||(StringUtil.isNotBlank(n2)&&Integer.parseInt(n2)<0)){
                return "所编辑的指标总数大于总指标，无法提交！";
            }
        }
        List<String> applyFlows=new ArrayList<>();
        applyFlows.add(applyFlow);
        int num=auditManageBiz.commitAllApply(applyFlows);
        if(num>0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }
    /**
     * 查询对应学位类型剩余指标
     * @param applyFlow
     * @param recruitYear
     * @param orgFlow
     * @param type
     * @return
     */
    @RequestMapping(value="/countApplyNum" )
    @ResponseBody
    public int countApplyNum(String applyFlow,String recruitYear,String orgFlow,String type){
        int surplus=0;
        if(StringUtil.isBlank(orgFlow)){
            orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
        }
        if(StringUtil.isBlank(recruitYear)){
            recruitYear=new SimpleDateFormat("yyyy").format(new Date());
        }
        Map<String,String> map1=new HashMap<>();
        map1.put("orgFlow",orgFlow);
        map1.put("recruitYear",recruitYear);
        List<Map<String,String>> recruitNumList=auditManageBiz.selectRecruitNum(map1);//机构总指标
        map1.put("applyFlow",applyFlow);
        map1.put("flag",GlobalConstant.RECORD_STATUS_Y);
        if("academicNum".equals(type)){
            map1.put("isAcademic",GlobalConstant.RECORD_STATUS_Y);
            int academicSum=auditManageBiz.selectAcademicRecruitSum(map1);//学术型已用指标
            if(recruitNumList!=null&&recruitNumList.size()>0){
                String academicNum=recruitNumList.get(0).get("ACADEMIC_NUM");
                surplus=Integer.parseInt(academicNum)-academicSum;
            }
        }
        if("specializedNum".equals(type)){
            map1.put("isSpecialized",GlobalConstant.RECORD_STATUS_Y);
            int specializedSum=auditManageBiz.selectSpecializedRecruitSum(map1);//专业型已用指标
            if(recruitNumList!=null&&recruitNumList.size()>0){
                String specializedNum=recruitNumList.get(0).get("SPECIALIZED_NUM");
                surplus=Integer.parseInt(specializedNum)-specializedSum;
            }
        }
        return surplus;
    }
    @RequestMapping(value="/countApplyNum1" )
    @ResponseBody
    public String countApplyNum1(String applyFlow,String recruitYear,String orgFlow,String type){
        int surplus=0;
        int surplus1=0;
        if(StringUtil.isBlank(orgFlow)){
            orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
        }
        if(StringUtil.isBlank(recruitYear)){
            recruitYear=new SimpleDateFormat("yyyy").format(new Date());
        }
        Map<String,String> map1=new HashMap<>();
        map1.put("orgFlow",orgFlow);
        map1.put("recruitYear",recruitYear);
        List<Map<String,String>> recruitNumList=auditManageBiz.selectRecruitNum(map1);//机构总指标
        map1.put("applyFlow",applyFlow);
        map1.put("flag",GlobalConstant.RECORD_STATUS_Y);
        map1.put("isAcademic",GlobalConstant.RECORD_STATUS_Y);
        int academicSum=auditManageBiz.selectAcademicRecruitSum(map1);//学术型已用指标
        if(recruitNumList!=null&&recruitNumList.size()>0){
            String academicNum=recruitNumList.get(0).get("ACADEMIC_NUM");
            surplus=Integer.parseInt(academicNum)-academicSum;
        }
        map1.remove("isAcademic");
        map1.put("isSpecialized",GlobalConstant.RECORD_STATUS_Y);
        int specializedSum=auditManageBiz.selectSpecializedRecruitSum(map1);//专业型已用指标
        if(recruitNumList!=null&&recruitNumList.size()>0){
            String specializedNum=recruitNumList.get(0).get("SPECIALIZED_NUM");
            surplus1=Integer.parseInt(specializedNum)-specializedSum;
        }
        return surplus+","+surplus1;
    }
    /**
     * 编辑招生人数
     * @param applyFlow
     * @param type
     * @param val
     * @return
     */
    @RequestMapping(value="/saveIndexNum" )
    @ResponseBody
    public String saveIndexNum(String applyFlow,String type,String val){
        TeacherTargetApply apply=new TeacherTargetApply();
        apply.setApplyFlow(applyFlow);
        if("academicNum".equals(type)){
            apply.setAcademicNum(val);
        }
        if("specializedNum".equals(type)){
           apply.setSpecializedNum(val);
        }
        int num=teaAndDocBiz.editTeacherTargetApply(apply);
        if(num>0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 录取缺额信息查询（二级机构）
     * @param recruitYear
     * @param speId
     * @param researchDirection
     * @param degreeTypeId
     * @param model
     * @return
     */
    @RequestMapping(value="/vacanciesInfoList" )
    public String vacanciesInfoList(String recruitYear, String speId, String researchDirection, String degreeTypeId,Integer currentPage,HttpServletRequest request, Model model){
        if(StringUtil.isBlank(recruitYear)){
            recruitYear= DateUtil.getYear();
        }
        Map<String,String> map=new HashMap<>();
        map.put("recruitYear",recruitYear);
        map.put("speId",speId);
        map.put("researchDirection",researchDirection);
        map.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        map.put("degreeTypeId",degreeTypeId);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> vacanciesInfoList=auditManageBiz.selectVacanciesOrg(map);
        model.addAttribute("vacanciesInfoList",vacanciesInfoList);
        model.addAttribute("recruitYear",recruitYear);
        return "/gzykdx/secondaryOrg/recruitInfoListOrg";
    }

    /**
     * 导师录取详情
     * @param orgFlow
     * @param degreeTypeId
     * @param year
     * @param model
     * @param currentPage
     * @param request
     * @return
     */
    @RequestMapping("/recruitDetail")
    public String recruitDetail(String orgFlow,String degreeTypeId,String year,String researchAreaId,String speId,Model model,
                                Integer currentPage,HttpServletRequest request){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("year",year);
        paramMap.put("orgFlow",orgFlow);
        paramMap.put("degreeTypeId",degreeTypeId);
        paramMap.put("researchAreaId",researchAreaId);
        paramMap.put("speId",speId);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,String>> recruitDetailList = auditManageBiz.teacherRecruitInfo(paramMap);
        model.addAttribute("recruitDetailList",recruitDetailList);
        return "gzykdx/secondaryOrg/recruitDetailOrg";
    }
    //缺额详情
    @RequestMapping("/vacanciesDetail")
    public String vacanciesDetail(String orgFlow,String degreeTypeId,String year,String researchAreaId,String speId,Model model,
                                  Integer currentPage,HttpServletRequest request){
        TeacherTargetApply teacherTargetApply = new TeacherTargetApply();
        teacherTargetApply.setRecruitYear(year);
        teacherTargetApply.setOrgFlow(orgFlow);
        teacherTargetApply.setResearchDirectionId(researchAreaId);
        teacherTargetApply.setSpeId(speId);
        if(GzykdxDegreeTypeEnum.AcademicType.getId().equals(degreeTypeId)){
            teacherTargetApply.setIsAcademic(GlobalConstant.IS_EXAM_TEA_Y);
        }
        if(GzykdxDegreeTypeEnum.ProfessionalType.getId().equals(degreeTypeId)){
            teacherTargetApply.setIsSpecialized(GlobalConstant.IS_EXAM_TEA_Y);
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<TeacherTargetApply> teacherTargetApplyList =  auditManageBiz.teacherApplyList(teacherTargetApply);
        model.addAttribute("teacherTargetApplyList",teacherTargetApplyList);
        model.addAttribute("degreeTypeId",degreeTypeId);
        return "gzykdx/secondaryOrg/vacanciesDetailOrg";
    }

    /**
     * 招录设置
     */
    @RequestMapping(value="/recruitSetting" )
    public String recruitSetting() {
        return "gzykdx/secondaryOrg/recruitSetting";
    }

    /**
     * 导师指标申请列表导出
     * @param recruitYear
     * @param speId
     * @param researchDirectionId
     * @param userName
     * @param auditStatusId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportTeacherApply")
    public void exportTeacherApply(String recruitYear, String speId, String researchDirectionId, String userName, String auditStatusId,HttpServletResponse response)throws Exception {
        if(StringUtil.isBlank(recruitYear)){
            recruitYear=DateUtil.getYear();
        }
        Map<String,String> map=new HashMap<>();
        map.put("recruitYear",recruitYear);
        map.put("speId",speId);
        map.put("researchDirectionId",researchDirectionId);
        map.put("userName",userName);
        map.put("auditStatusId",auditStatusId);
        map.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        List<Map<String,Object>> teacherApplyList=auditManageBiz.selectTeacherApplyListOrg(map);
        if(teacherApplyList!=null&&teacherApplyList.size()>0){
            for (Map<String,Object> temp:teacherApplyList) {
                if(temp!=null&& StringUtil.isNotBlank((String)temp.get("USER_FLOW"))){
                    PubUserResume pubUserResume=pubUserResumeBiz.readPubUserResume((String)temp.get("USER_FLOW"));
                    if(pubUserResume!=null){
                        String xmlContent =  pubUserResume.getUserResume();
                        if(StringUtil.isNotBlank(xmlContent)){
                            GzykdxTeacherExtInfoForm extInfo=null;
                            extInfo=pubUserResumeBiz.converyToJavaBean(xmlContent,GzykdxTeacherExtInfoForm.class);
                            temp.put("infoForm",extInfo);
                        }
                    }
                }
            }
        }

        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("导师指标申请审核列表");
        //列宽自适应
//        sheet.setDefaultColumnWidth((short)50);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));//合并单元格
        HSSFRow rowOne = sheet.createRow(0);//第一行
        rowOne.setHeightInPoints(20);
        HSSFCell cellOne = rowOne.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue("广州医科大学"+recruitYear+"年硕士导师招生申报情况汇总表");
        HSSFRow rowTwo = sheet.createRow(1);//第二行
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));//合并单元格
        HSSFCell cellFive = rowTwo.createCell(0);
        cellFive.setCellStyle(styleLeft);
        cellFive.setCellValue("单位名称（盖章）：");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 7, 13));//合并单元格
        HSSFCell cellEight = rowTwo.createCell(7);
        cellEight.setCellStyle(styleLeft);
        cellEight.setCellValue("填表日期：　  年     月     日");
        HSSFRow rowThree = sheet.createRow(2);//第三行
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 4));//合并单元格
        HSSFCell cellThree = rowThree.createCell(0);
        cellThree.setCellStyle(styleLeft);
        cellThree.setCellValue("填表人：");
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 5, 8));//合并单元格
        HSSFCell cellThree1 = rowThree.createCell(5);
        cellThree1.setCellStyle(styleLeft);
        cellThree1.setCellValue("联系人：");
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 9, 13));//合并单元格
        HSSFCell cellThree2 = rowThree.createCell(9);
        cellThree2.setCellStyle(styleLeft);
        cellThree2.setCellValue("电话：");
        HSSFRow rowFour = sheet.createRow(3);//第四行
        String[] titles = new String[]{
                "序号",
                "导师姓名",
                "申请招生专业",
                "招生专业代码",
                "研究方向",
                "年龄",
                "职称",
                "最高学位",
                "学术学位拟招生人数",
                "专业学位拟招生人数",
                "手机",
                "经费(万元)",
                "状态",
                "备注"
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowFour.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 256);
        }
        int rowNum = 4;
        int academicNum=0;
        int specializedNum=0;
        String[] resultList = null;
        if (teacherApplyList != null && !teacherApplyList.isEmpty()) {
            for (int i = 0; i < teacherApplyList.size(); i++, rowNum++) {
                HSSFRow rowFive = sheet.createRow(rowNum);
                resultList = new String[]{
//                        teacherApplyList.get(i).get("RECRUIT_YEAR")==null?"":teacherApplyList.get(i).get("RECRUIT_YEAR").toString(),
                        i+1+"",
                        teacherApplyList.get(i).get("USER_NAME")==null?"":teacherApplyList.get(i).get("USER_NAME").toString(),
                        teacherApplyList.get(i).get("SPE_NAME")==null?"":teacherApplyList.get(i).get("SPE_NAME").toString(),
                        teacherApplyList.get(i).get("SPE_ID")==null?"":teacherApplyList.get(i).get("SPE_ID").toString(),
                        teacherApplyList.get(i).get("RESEARCH_DIRECTION")==null?"":teacherApplyList.get(i).get("RESEARCH_DIRECTION").toString(),
                        teacherApplyList.get(i).get("infoForm")==null?"":((GzykdxTeacherExtInfoForm)teacherApplyList.get(i).get("infoForm")).getAge(),
                        teacherApplyList.get(i).get("TITLE_NAME")==null?"":teacherApplyList.get(i).get("TITLE_NAME").toString(),
                        teacherApplyList.get(i).get("DEGREE_NAME")==null?"":teacherApplyList.get(i).get("DEGREE_NAME").toString(),
                        teacherApplyList.get(i).get("ACADEMIC_NUM")==null?"":teacherApplyList.get(i).get("ACADEMIC_NUM").toString(),
                        teacherApplyList.get(i).get("SPECIALIZED_NUM")==null?"":teacherApplyList.get(i).get("SPECIALIZED_NUM").toString(),
                        teacherApplyList.get(i).get("USER_PHONE")==null?"":teacherApplyList.get(i).get("USER_PHONE").toString(),
                        teacherApplyList.get(i).get("FUNDS")==null?"":teacherApplyList.get(i).get("FUNDS").toString(),
                        teacherApplyList.get(i).get("AUDIT_STATUS_NAME")==null?"":teacherApplyList.get(i).get("AUDIT_STATUS_NAME").toString(),
                        ""
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFive.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(resultList[j]);
                }
                if(teacherApplyList.get(i).get("ACADEMIC_NUM")!=null){
                    academicNum+=Integer.parseInt(teacherApplyList.get(i).get("ACADEMIC_NUM").toString());
                }
                if(teacherApplyList.get(i).get("SPECIALIZED_NUM")!=null){
                    specializedNum+=Integer.parseInt(teacherApplyList.get(i).get("SPECIALIZED_NUM").toString());
                }
            }
        }
        HSSFRow rowFoot = sheet.createRow(rowNum);
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 7));//合并单元格
        HSSFCell cellTotal1 = rowFoot.createCell(0);
        cellTotal1.setCellStyle(styleCenter);
        cellTotal1.setCellValue("合计招生人数");
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 8, 8));//合并单元格
        HSSFCell cellAcademicNum = rowFoot.createCell(8);
        cellAcademicNum.setCellStyle(styleCenter);
        cellAcademicNum.setCellValue(academicNum);
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 9, 9));//合并单元格
        HSSFCell cellSpecializedNum = rowFoot.createCell(9);
        cellSpecializedNum.setCellStyle(styleCenter);
        cellSpecializedNum.setCellValue(specializedNum);
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 10, 13));//合并单元格
        HSSFCell cellTotalOut = rowFoot.createCell(10);
        cellTotalOut.setCellStyle(styleCenter);
        cellTotalOut.setCellValue("");
        String fileName = "导师指标申请审核列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    /**
     * 导师注册验证码
     * @return
     */
    @RequestMapping(value="/registrationCode" )
    public String registrationCode(Model model){
        String orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
        if(StringUtil.isNotBlank(orgFlow)){
            SysOrg sysOrg=sysOrgBiz.readSysOrg(orgFlow);
            model.addAttribute("sysOrg", sysOrg);
        }
        return "gzykdx/secondaryOrg/registrationCode";
    }
}
