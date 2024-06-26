package com.pinde.sci.ctrl.gzykdx;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzykdx.IGzykdxAuditManageBiz;
import com.pinde.sci.biz.gzykdx.IGzykdxSchoolAdminBiz;
import com.pinde.sci.biz.gzykdx.IGzykdxTeaAndDocBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.gzykdx.GzykdxAuditStatusEnum;
import com.pinde.sci.form.gzykdx.GzykdxTeacherExtInfoForm;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
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
 * 此类主要是实现 XXX 功能
 * @since 2017/3/10
 */
@Controller
@RequestMapping("/gzykdx/schoolAudit")
public class GZykdxSchoolAuditController extends GeneralController {

    @Autowired
    private IGzykdxAuditManageBiz auditManageBiz;
    @Autowired
    private IPubUserResumeBiz pubUserResumeBiz;
    @Autowired
    private IGzykdxTeaAndDocBiz teaAndDocBiz;
    @Autowired
    private IGzykdxSchoolAdminBiz schoolAdminBiz;

    @RequestMapping(value="/teacherApplyList" )
    public String teacherApplyList(String orgFlow, String recruitYear, String speId, String researchDirection, String userName, String auditStatusId, Integer currentPage, HttpServletRequest request, Model model) throws Exception {
        List<SysOrg> orgList=teaAndDocBiz.searchOrgList();
        if(StringUtil.isBlank(recruitYear)){
            recruitYear=new SimpleDateFormat("yyyy").format(new Date());
        }
        Map<String,String> map=new HashMap<>();
        map.put("recruitYear",recruitYear);
        map.put("speId",speId);
        map.put("researchDirection",researchDirection);
        map.put("userName",userName);
        map.put("auditStatusId",auditStatusId);
        map.put("orgFlow", orgFlow);
        map.put("isSubmit", GlobalConstant.RECORD_STATUS_Y);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> teacherApplyList=auditManageBiz.selectTeacherTargetApplyList(map);
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
        map1.put("orgFlow",orgFlow);
        map1.put("recruitYear",recruitYear);
        List<Map<String,String>> recruitNumList=auditManageBiz.selectRecruitNum(map1);//机构总指标
        map1.put("isAcademic", GlobalConstant.RECORD_STATUS_Y);
        int academicSum=auditManageBiz.selectAcademicRecruitSum(map1);//学术型已用指标
        map1.remove("isAcademic");
        map1.put("isSpecialized",GlobalConstant.RECORD_STATUS_Y);
        int specializedSum=auditManageBiz.selectSpecializedRecruitSum(map1);//专业型已用指标
        if(recruitNumList!=null&&recruitNumList.size()>0){
            model.addAttribute("recruitNum",recruitNumList.get(0));
        }
        model.addAttribute("academicSum",academicSum);
        model.addAttribute("specializedSum",specializedSum);
        model.addAttribute("teacherApplyList",teacherApplyList);
        model.addAttribute("recruitYear",recruitYear);
        model.addAttribute("orgList",orgList);
        model.addAttribute("currentPage",currentPage);
        return "/gzykdx/school/teacherTargetAudits";
    }

    @RequestMapping(value="/auditAllapply" )
    @ResponseBody
    public String auditAllapply(){
        TeacherTargetApply apply=new TeacherTargetApply();
        apply.setAuditStatusId(GzykdxAuditStatusEnum.SchoolPassed.getId());
        apply.setAuditStatusName(GzykdxAuditStatusEnum.SchoolPassed.getName());
        List<String> applyFlows=new ArrayList<>();
        List<TeacherTargetApply> selectApplyList=auditManageBiz.selectApplyList("",GzykdxAuditStatusEnum.WaitingForSchool.getId());
        if(selectApplyList!=null&&selectApplyList.size()>0){
            for (TeacherTargetApply apply1:selectApplyList) {
                if(apply1!=null){
                    applyFlows.add(apply1.getApplyFlow());
                }
            }
        }
        int num=auditManageBiz.editApplies(applyFlows,apply);
        if(num>0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return "当前没有待审核的指标！";
    }

    /**
     * 导师指标申请列表导出
     * @param orgFlow
     * @param recruitYear
     * @param speId
     * @param researchDirectionId
     * @param userName
     * @param auditStatusId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportTeacherApply")
    public void exportTeacherApply(String orgFlow, String recruitYear, String speId, String researchDirectionId, String userName, String auditStatusId,HttpServletResponse response)throws Exception {
        if(StringUtil.isBlank(recruitYear)){
            recruitYear=new SimpleDateFormat("yyyy").format(new Date());
        }
        Map<String,String> map=new HashMap<>();
        map.put("recruitYear",recruitYear);
        map.put("speId",speId);
        map.put("researchDirectionId",researchDirectionId);
        map.put("userName",userName);
        map.put("auditStatusId",auditStatusId);
        map.put("orgFlow", orgFlow);
        map.put("isSubmit", GlobalConstant.RECORD_STATUS_Y);
        List<Map<String,Object>> teacherApplyList=auditManageBiz.selectTeacherTargetApplyList(map);
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
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(0, 0, 0, 14));//合并单元格
        HSSFRow rowOne = sheet.createRow(0);//第一行
        rowOne.setHeightInPoints(20);
        HSSFCell cellOne = rowOne.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue("广州医科大学"+recruitYear+"年硕士导师招生申报情况汇总表");
        HSSFRow rowTwo = sheet.createRow(1);//第二行
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(1, 1, 0, 7));//合并单元格
        HSSFCell cellFive = rowTwo.createCell(0);
        cellFive.setCellStyle(styleLeft);
        cellFive.setCellValue("单位名称（盖章）：");
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(1, 1, 8, 14));//合并单元格
        HSSFCell cellEight = rowTwo.createCell(8);
        cellEight.setCellStyle(styleLeft);
        cellEight.setCellValue("填表日期：　  年     月     日");
        HSSFRow rowThree = sheet.createRow(2);//第三行
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(2, 2, 0, 5));//合并单元格
        HSSFCell cellThree = rowThree.createCell(0);
        cellThree.setCellStyle(styleLeft);
        cellThree.setCellValue("填表人：");
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(2, 2, 6, 9));//合并单元格
        HSSFCell cellThree1 = rowThree.createCell(6);
        cellThree1.setCellStyle(styleLeft);
        cellThree1.setCellValue("联系人：");
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(2, 2, 10, 14));//合并单元格
        HSSFCell cellThree2 = rowThree.createCell(10);
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
                "经费(万元)",
                "工作机构",
                "手机",
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
                        teacherApplyList.get(i).get("FUNDS")==null?"":teacherApplyList.get(i).get("FUNDS").toString(),
                        teacherApplyList.get(i).get("ORG_NAME")==null?"":teacherApplyList.get(i).get("ORG_NAME").toString(),
                        teacherApplyList.get(i).get("USER_PHONE")==null?"":teacherApplyList.get(i).get("USER_PHONE").toString(),
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
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(rowNum, rowNum, 0, 7));//合并单元格
        HSSFCell cellTotal1 = rowFoot.createCell(0);
        cellTotal1.setCellStyle(styleCenter);
        cellTotal1.setCellValue("合计招生人数");
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(rowNum, rowNum, 8, 8));//合并单元格
        HSSFCell cellAcademicNum = rowFoot.createCell(8);
        cellAcademicNum.setCellStyle(styleCenter);
        cellAcademicNum.setCellValue(academicNum);
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(rowNum, rowNum, 9, 9));//合并单元格
        HSSFCell cellSpecializedNum = rowFoot.createCell(9);
        cellSpecializedNum.setCellStyle(styleCenter);
        cellSpecializedNum.setCellValue(specializedNum);
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(rowNum, rowNum, 10, 14));//合并单元格
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
     * 导出招生目录（学校管理员）
     * @param target
     * @return
     */
    @RequestMapping(value = "/exportRecruitCatalog")
    public void exportRecruitCatalog(TeacherTargetApply target,HttpServletResponse response) throws Exception {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //列宽自适应
        String[] titles;//导出列表头信息
        HSSFRow rowDep = sheet.createRow(0);//第一行
        titles = new String[]{
                "年份",
                "专业代码",
                "专业名称",
                "研究方向",
                "导师",
                "考试科目",
                "导师单位",
                "学术学位拟招生人数",
                "专业学位拟招生人数",
                "学位类型",
                "导师身份证号码",
                "导师性别",
                "导师出生年月",
                "导师年龄",
                "导师手机号",
                "导师最高学历",
                "导师最高学历毕业学校",
                "导师最高学历毕业时间",
                "导师最高学位",
                "导师最高学位获得单位",
                "导师最高学位获得时间",
                "导师职称",
                "导师所在领域工作年限",
                "导师参加工作时间"
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowDep.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 1 * 156);
        }
        List<Map<String,Object>> dataList = schoolAdminBiz.passedTeacherApplyList(target);
        int rowNum = 1;
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                String degreeType="";
                String academic=(String)dataList.get(i).get("IS_ACADEMIC");
                String specialized=(String)dataList.get(i).get("IS_SPECIALIZED");
                if(StringUtil.isBlank(specialized)&&StringUtil.isNotBlank(academic)){
                    degreeType="学术学位";
                }
                if(StringUtil.isNotBlank(specialized)&&StringUtil.isBlank(academic)){
                    degreeType="专业学位";
                }
                if(StringUtil.isNotBlank(specialized)&&StringUtil.isNotBlank(academic)){
                    degreeType="学术/专业学位";
                }

                String xmlContent = (String)dataList.get(i).get("user_resume");
                String age = "";
                String educationSchool = "";
                String educationDate = "";
                String degreeOrg = "";
                String degreeDate = "";
                String workAreaYear = "";
                String workDate = "";
                GzykdxTeacherExtInfoForm teacherExtInfoForm = JaxbUtil.converyToJavaBean(xmlContent,GzykdxTeacherExtInfoForm.class);
                if (teacherExtInfoForm!=null){
                    age = teacherExtInfoForm.getAge();
                    educationSchool = teacherExtInfoForm.getEducationSchool();
                    educationDate = teacherExtInfoForm.getEducationDate();
                    degreeOrg = teacherExtInfoForm.getDegreeOrg();
                    degreeDate = teacherExtInfoForm.getDegreeDate();
                    workAreaYear = teacherExtInfoForm.getWorkAreaYear();
                    workDate = teacherExtInfoForm.getWorkDate();
                }


                titles = new String[]{
                        (String)dataList.get(i).get("RECRUIT_YEAR"),
                        (String)dataList.get(i).get("SPE_ID"),
                        (String)dataList.get(i).get("SPE_NAME"),
                        (String)dataList.get(i).get("RESEARCH_DIRECTION"),
                        (String)dataList.get(i).get("USER_NAME"),
                        (String)dataList.get(i).get("DICT_DESC"),
                        (String)dataList.get(i).get("ORG_NAME"),
                        (String)dataList.get(i).get("ACADEMIC_NUM"),
                        (String)dataList.get(i).get("SPECIALIZED_NUM"),
                        degreeType,
                        (String)dataList.get(i).get("ID_NO"),
                        (String)dataList.get(i).get("SEX_NAME"),
                        (String)dataList.get(i).get("USER_BIRTHDAY"),
                        age,
                        (String)dataList.get(i).get("USER_PHONE"),
                        (String)dataList.get(i).get("EDUCATION_NAME"),
                        educationSchool,
                        educationDate,
                        (String)dataList.get(i).get("DEGREE_NAME"),
                        degreeOrg,
                        degreeDate,
                        (String)dataList.get(i).get("TITLE_NAME"),
                        workAreaYear,
                        workDate
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(titles[j]);
                }
            }
        }
        String fileName = "招生目录发布信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    /**
     * 招生指标计划导出
     * @param rargetFlow
     * @param orgFlow
     */
    @RequestMapping(value = "/exportAllocateDetail")
    public void exportAllocateDetail(String rargetFlow,String orgFlow,HttpServletResponse response)throws Exception {
        RecruitTargetMain recTarget = schoolAdminBiz.queryRecTargetByFlow(rargetFlow);
        List<SysUser> userList = schoolAdminBiz.queryOrgAdminPowerList(orgFlow);
        Map<String,RecruitTargetMainDetail> orgTargetMap = new HashMap<>();
        List<RecruitTargetMainDetail> detailList = schoolAdminBiz.queryRecDetailByFlow(rargetFlow);
        if(null != detailList && detailList.size() >0 ){
            for(RecruitTargetMainDetail detail : detailList){
                if(!orgTargetMap.containsKey(detail.getOrgFlow())){
                    orgTargetMap.put(detail.getOrgFlow(),detail);
                }
            }
        }
        String year="";
        if(recTarget!=null){
            year=recTarget.getRecruitYear();
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
        HSSFSheet sheet = wb.createSheet("招生计划安排表");
        //列宽自适应
//        sheet.setDefaultColumnWidth((short)50);
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(0, 0, 0, 5));//合并单元格
        HSSFRow rowOne = sheet.createRow(0);//第一行
        rowOne.setHeightInPoints(20);
        HSSFCell cellOne = rowOne.createCell(0);
        cellOne.setCellStyle(stylevwc);
        cellOne.setCellValue("硕士生招生计划安排情况表");
        HSSFRow rowTwo = sheet.createRow(1);//第二行
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(1, 2, 0, 0));//合并单元格
        HSSFCell cellFive = rowTwo.createCell(0);
        cellFive.setCellStyle(stylevwc);
        cellFive.setCellValue("序号");
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(1, 2, 1, 1));//合并单元格
        HSSFCell cellEight = rowTwo.createCell(1);
        cellEight.setCellStyle(stylevwc);
        cellEight.setCellValue("单位");
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(1, 1, 2, 4));//合并单元格
        HSSFCell cellEight1 = rowTwo.createCell(2);
        cellEight1.setCellStyle(styleCenter);
        cellEight1.setCellValue(year+"年硕士生招生计划");
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(1, 2, 5, 5));//合并单元格
        HSSFCell cellEight2 = rowTwo.createCell(5);
        cellEight2.setCellStyle(stylevwc);
        cellEight2.setCellValue("备注");
        HSSFRow rowThree = sheet.createRow(2);//第三行
        String[] titles = new String[]{
                "",
                "",
                "学术学位",
                "专业学位",
                "合计",
                ""
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowThree.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 256 *2);
        }
        int rowNum = 3;
        int academicNum=0;
        int specializedNum=0;
        String[] resultList = null;
        if (userList != null && !userList.isEmpty()) {
            for (int i = 0; i < userList.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);
                resultList = new String[]{
                        i+1+"",
                        userList.get(i).getOrgName(),
                        orgTargetMap.get(userList.get(i).getOrgFlow())==null?"":orgTargetMap.get(userList.get(i).getOrgFlow()).getAcademicNum(),
                        orgTargetMap.get(userList.get(i).getOrgFlow())==null?"":orgTargetMap.get(userList.get(i).getOrgFlow()).getSpecializedNum(),
                        orgTargetMap.get(userList.get(i).getOrgFlow())==null?"":orgTargetMap.get(userList.get(i).getOrgFlow()).getAllNum(),
                        ""
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(resultList[j]);
                }
                if(orgTargetMap.get(userList.get(i).getOrgFlow())!=null&&StringUtil.isNotBlank(orgTargetMap.get(userList.get(i).getOrgFlow()).getAcademicNum())){
                    academicNum+=Integer.parseInt(orgTargetMap.get(userList.get(i).getOrgFlow()).getAcademicNum());
                }
                if(orgTargetMap.get(userList.get(i).getOrgFlow())!=null&&StringUtil.isNotBlank(orgTargetMap.get(userList.get(i).getOrgFlow()).getSpecializedNum())){
                    specializedNum+=Integer.parseInt(orgTargetMap.get(userList.get(i).getOrgFlow()).getSpecializedNum());
                }
            }
        }
        HSSFRow rowFoot = sheet.createRow(rowNum);
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(rowNum, rowNum, 0, 1));//合并单元格
        HSSFCell cellTotal1 = rowFoot.createCell(0);
        cellTotal1.setCellStyle(styleCenter);
        cellTotal1.setCellValue("合计");
        HSSFCell cellAcademicNum = rowFoot.createCell(2);
        cellAcademicNum.setCellStyle(styleCenter);
        cellAcademicNum.setCellValue(academicNum);
        HSSFCell cellSpecializedNum = rowFoot.createCell(3);
        cellSpecializedNum.setCellStyle(styleCenter);
        cellSpecializedNum.setCellValue(specializedNum);
        HSSFCell cellTotalOut = rowFoot.createCell(4);
        cellTotalOut.setCellStyle(styleCenter);
        cellTotalOut.setCellValue(academicNum+specializedNum);
        HSSFCell cellTotalOut1 = rowFoot.createCell(5);
        cellTotalOut1.setCellStyle(styleCenter);
        cellTotalOut1.setCellValue("");
        String fileName = "招生计划安排情况表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }
}
