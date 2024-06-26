package com.pinde.sci.ctrl.res;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzzyjxres.IScholarSchArrangeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.form.res.ResEvaluationCfgItemForm;
import com.pinde.sci.form.res.ResEvaluationCfgTitleForm;
import com.pinde.sci.model.mo.DeptTeacherGradeInfo;
import com.pinde.sci.model.mo.ResEvaluationCfg;
import com.pinde.sci.model.mo.ResTrainingSpeDept;
import com.pinde.sci.model.mo.SysDept;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author tiger
 *
 */
@Controller
@RequestMapping("/thres/head")
public class ThResHeadController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(ResCfgController.class);

    @Autowired
    private ISchArrangeResultBiz schArrangeResultBiz;
    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Autowired
    private ISchDeptBiz schDeptBiz;
    @Autowired
    private IScholarSchArrangeBiz arrangeBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IResAssessCfgBiz assessCfgBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private IResDoctorProcessBiz resDoctorProcessBiz;
    @Autowired
    private IResGradeBiz resGradeBiz;
    @Autowired
    private IResTrainingSpeDeptBiz trainingSpeDeptBiz;
    @Autowired
    private IResEvaluationCfgBiz evaluationCfgBiz;

    /**
     * 基地双向评分
     *
     * @param deptFlow
     * @param userName
     * @param operStartDate
     * @param operEndDate
     * @param model
     * @return
     */
    @RequestMapping(value = "/thGradeSearch")
    public String thGradeSearch(String role, String gradeRole, String deptFlow, String userName, String deptName,
                                String operStartDate, String operEndDate, String sessionNumber, Integer currentPage, HttpServletRequest request, Model model) {
        if (StringUtil.isNotBlank(operStartDate)) {
            operStartDate = DateUtil.getDate(operStartDate) + "000000";
        }
        if (StringUtil.isNotBlank(operEndDate)) {
            operEndDate = DateUtil.getDate(operEndDate) + "235959";
        }

        //当前用户所在机构
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        //专业基地管理员专业ID
        String trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
        //登记类型
        String recType = "";
        //评分类型
        String cfgCode = "";
        //查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("operStartDate", operStartDate);
        paramMap.put("operEndDate", operEndDate);
        paramMap.put("userName", userName);
        paramMap.put("deptFlow", deptFlow);
        paramMap.put("deptName", deptName);
        paramMap.put("sessionNumber", sessionNumber);

        model.addAttribute("role", role);
        if ("professionalBase".equals(role)) {
            ResTrainingSpeDept search = new ResTrainingSpeDept();//查出当前专业基地的所有科室
            search.setOrgFlow(orgFlow);
            search.setTrainingSpeId(trainingSpeId);
            List<ResTrainingSpeDept> trainingSpeDeptList = trainingSpeDeptBiz.search(search);
            model.addAttribute("depts", trainingSpeDeptList);

            List<String> deptFlows = new ArrayList<>();
            if (trainingSpeDeptList != null && trainingSpeDeptList.size() > 0) {
                for (ResTrainingSpeDept resTrainingSpeDept : trainingSpeDeptList) {
                    String deptFLow = resTrainingSpeDept.getDeptFlow();
                    deptFlows.add(deptFLow);
                }
            }
            paramMap.put("deptFlows", deptFlows);
        } else {
            List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);//查出当前机构的所有科室
            model.addAttribute("depts", deptList);
        }

        List<String> keys = new ArrayList<String>();
        Object waitSort = null;
        Map<String, Object> scoreMap = new HashMap<String, Object>();
        if ("head".equals(gradeRole)) {
            recType = ResRecTypeEnum.DoctorEval.getId();
            paramMap.put("recTypeId", recType);


            PageHelper.startPage(currentPage, getPageSize(request));
            List<SysDept> depts = resGradeBiz.getDeptByRec(paramMap);
            model.addAttribute("datas", depts);
        }
        return "res/teacher/thGradeSearch";
    }

    @RequestMapping(value = {"/thGradeSearchDoc"})
    public String thGradeSearchDoc(
            String gradeRole,
            String keyCode,
            String operStartDate,
            String operEndDate,
            String date,
            String deptFlow,
            Model model
    ) throws DocumentException {
        if (StringUtil.isNotBlank(operStartDate)) {
            operStartDate = DateUtil.getDate(operStartDate) + "000000";
        }
        if (StringUtil.isNotBlank(operEndDate)) {
            operEndDate = DateUtil.getDate(operEndDate) + "235959";
        }

        //当前用户所在机构
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();

        //登记类型
        String recType = "";
        //评分类型
        String cfgCode = "";
        //查询条件
        Map<String, Object> scoreSumMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("sessionNumber", date.equals("null") ? null : date);
        paramMap.put("operStartDate", operStartDate);
        paramMap.put("operEndDate", operEndDate);
        paramMap.put("deptFlow", deptFlow);

        recType = ResRecTypeEnum.DoctorEval.getId();
        paramMap.put("recTypeId", recType);

        ResEvaluationCfg evaluationCfg = evaluationCfgBiz.readResEvaluationCfgByDept(deptFlow);
        String cfgContent="";
        if(evaluationCfg != null){
            cfgContent=evaluationCfg.getCfgBigValue();
        }

        if(StringUtil.isNotBlank(cfgContent)){
            Document dom = DocumentHelper.parseText(cfgContent);
            String titleXpath = "//title";
            List<Element> titleElementList = dom.selectNodes(titleXpath);
            if(titleElementList != null && !titleElementList.isEmpty()){
                List<ResEvaluationCfgTitleForm> titleFormList = new ArrayList<ResEvaluationCfgTitleForm>();
                for(Element te :titleElementList){
                    ResEvaluationCfgTitleForm titleForm = new ResEvaluationCfgTitleForm();
                    titleForm.setId(te.attributeValue("id"));
                    titleForm.setName(te.attributeValue("name"));
                    List<Element> itemElementList = te.elements("item");
                    List<ResEvaluationCfgItemForm> itemFormList = null;
                    if(itemElementList != null && !itemElementList.isEmpty()){
                        itemFormList = new ArrayList<ResEvaluationCfgItemForm>();
                        for(Element ie : itemElementList){
                            ResEvaluationCfgItemForm itemForm = new ResEvaluationCfgItemForm();
                            itemForm.setId(ie.attributeValue("id"));
                            itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                            itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                            itemFormList.add(itemForm);
                        }
                    }
                    titleForm.setItemList(itemFormList);
                    titleFormList.add(titleForm);
                }
                model.addAttribute("titleFormList", titleFormList);
                String q = "";
            }
        }
        //获取评分数据
        List<Map<String, String>> recList = resGradeBiz.getRecContentByProcess(paramMap);
        Map<String,Object> gradesMap=new HashMap<>();
        Map<String,DeptTeacherGradeInfo> gradeInfoMap=new HashMap<>();
        if (recList != null && !recList.isEmpty()) {
            model.addAttribute("recList", recList);
            for (Map<String, String> map : recList) {
                String content = map.get("content");

                Map<String, Object> gradeMap = resRecBiz.parseDocotrGradeXml(content);
                gradesMap.put(map.get("recFlow"),gradeMap);
                DeptTeacherGradeInfo teacherGradeInfo = resGradeBiz.readResGrade(map.get("recFlow"));
                gradeInfoMap.put(map.get("recFlow"),teacherGradeInfo);
            }
        }
        model.addAttribute("gradesMap", gradesMap);
        model.addAttribute("gradeInfoMap", gradeInfoMap);
        return "res/teacher/thGradeSearchDoc";
    }

    @RequestMapping(value={"/thExportGradeSearchDoc"})
    public void exportGradeSearchDoc(
            String gradeRole,
            String keyCode,
            String operStartDate,
            String operEndDate,
            String date,
            String recFlow,
            HttpServletResponse response, HttpServletRequest request
    ) throws Exception {
        boolean isOneFile = false;
        if (StringUtil.isNotBlank(operStartDate)) {
            operStartDate = DateUtil.getDate(operStartDate) + "000000";
        }
        if (StringUtil.isNotBlank(operEndDate)) {
            operEndDate = DateUtil.getDate(operEndDate) + "235959";
        }

        //当前用户所在机构
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();

        //登记类型
        String recType = "";
        //评分类型
        String cfgCode = "";
        //查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("sessionNumber", date.equals("null") ? null : date);
        paramMap.put("operStartDate", operStartDate);
        paramMap.put("operEndDate", operEndDate);
        paramMap.put("deptFlow", keyCode);
        paramMap.put("recFlow", recFlow);
        recType = ResRecTypeEnum.DoctorEval.getId();
        paramMap.put("recTypeId", recType);

        ResEvaluationCfg evaluationCfg = evaluationCfgBiz.readResEvaluationCfgByDept(keyCode);
        String cfgContent = "";
        if (evaluationCfg != null) {
            cfgContent = evaluationCfg.getCfgBigValue();
        }

        List<ResEvaluationCfgTitleForm> titleFormList = new ArrayList<ResEvaluationCfgTitleForm>();
        if (StringUtil.isNotBlank(cfgContent)) {
            Document dom = DocumentHelper.parseText(cfgContent);
            String titleXpath = "//title";
            List<Element> titleElementList = dom.selectNodes(titleXpath);
            if (titleElementList != null && !titleElementList.isEmpty()) {
                for (Element te : titleElementList) {
                    ResEvaluationCfgTitleForm titleForm = new ResEvaluationCfgTitleForm();
                    titleForm.setId(te.attributeValue("id"));
                    titleForm.setName(te.attributeValue("name"));
                    List<Element> itemElementList = te.elements("item");
                    List<ResEvaluationCfgItemForm> itemFormList = null;
                    if (itemElementList != null && !itemElementList.isEmpty()) {
                        itemFormList = new ArrayList<ResEvaluationCfgItemForm>();
                        for (Element ie : itemElementList) {
                            ResEvaluationCfgItemForm itemForm = new ResEvaluationCfgItemForm();
                            itemForm.setId(ie.attributeValue("id"));
                            itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                            itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                            itemFormList.add(itemForm);
                        }
                    }
                    titleForm.setItemList(itemFormList);
                    titleFormList.add(titleForm);
                }
            }
        }
        //获取评分数据
        int i = 0;
        List<Map<String, String>> recList = resGradeBiz.getRecContentByProcess(paramMap);
        Map<String, Object> gradesMap = new HashMap<>();
        if (recList != null && !recList.isEmpty()) {
            if(recList.size() == 1){
                for (Map<String, String> map : recList) {
                    i++;

                    String content = map.get("content");
                    Map<String, Object> gradeMap = resRecBiz.parseDocotrGradeXml(content);
                    gradesMap.put(map.get("recFlow"), gradeMap);
                    DeptTeacherGradeInfo teacherGradeInfo = resGradeBiz.readResGrade(map.get("recFlow"));

                    String activty = "";
                    String jianyi = "";
                    String teach = "";

                    if (gradeMap != null && !gradeMap.isEmpty()) {
                        for (String gk : gradeMap.keySet()) {
                            Object o = gradeMap.get(gk);
                            if (o instanceof Map) {
                            } else {
                                try {
                                    if (gk.equals("activty")) {
                                        activty = (String) gradeMap.get("activty");
                                    } else if (gk.equals("jianyi")) {
                                        jianyi = (String) gradeMap.get("jianyi");
                                    } else if (gk.equals("teach")) {
                                        teach = (String) gradeMap.get("teach");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    StringBuffer teachers = new StringBuffer();
                    if (StringUtil.isNotBlank(teacherGradeInfo.getTeacherNameOne())) {
                        teachers.append(teacherGradeInfo.getTeacherNameOne()).append("、");
                    }
                    if (StringUtil.isNotBlank(teacherGradeInfo.getTeacherNameTwo())) {
                        teachers.append(teacherGradeInfo.getTeacherNameTwo()).append("、");
                    }
                    if (StringUtil.isNotBlank(teacherGradeInfo.getTeacherNameThree())) {
                        teachers.append(teacherGradeInfo.getTeacherNameThree()).append("、");
                    }
                    String teacher = "";
                    if (teachers.length() > 0) {
                        teacher = teachers.substring(0, teachers.length() - 1);
                    }
                    //创建工作簿
                    HSSFWorkbook wb = new HSSFWorkbook();
                    // 为工作簿添加sheet
                    HSSFSheet sheet = wb.createSheet("sheet1");
                    //第一行
                    HSSFRow rowDeptHead = sheet.createRow(0);
                    //第一行第一列
                    HSSFCell cellHead1 = rowDeptHead.createCell(0);
                    cellHead1.setCellValue("轮转科室:" + map.get("deptName"));
                    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));

                    HSSFCell cellHead2 = rowDeptHead.createCell(2);
                    cellHead2.setCellValue("轮转时间:" + map.get("schStartDate") + "至" + map.get("schEndDate"));
                    sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 5));
                    //第二行
                    HSSFRow rowDeptHead1 = sheet.createRow(1);
                    HSSFCell cellHead3 = rowDeptHead1.createCell(0);
                    cellHead3.setCellValue("推荐老师:" + teacher);
                    sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 5));
                    //第三行
                    HSSFRow rowDeptHead2 = sheet.createRow(2);
                    HSSFCell cellHead4 = rowDeptHead2.createCell(1);
                    cellHead4.setCellValue("优");
                    HSSFCell cellHead5 = rowDeptHead2.createCell(2);
                    cellHead5.setCellValue("良");
                    HSSFCell cellHead6 = rowDeptHead2.createCell(3);
                    cellHead6.setCellValue("中");
                    HSSFCell cellHead7 = rowDeptHead2.createCell(4);
                    cellHead7.setCellValue("差");
                    HSSFCell cellHead8 = rowDeptHead2.createCell(5);
                    cellHead8.setCellValue("备注");

                    int rownum = 3;
                    for (ResEvaluationCfgTitleForm resEvaluationCfgTitleForm : titleFormList) {

                        if (resEvaluationCfgTitleForm != null && resEvaluationCfgTitleForm.getItemList() != null) {
                            List<ResEvaluationCfgItemForm> itemFormList = resEvaluationCfgTitleForm.getItemList();
                            for (ResEvaluationCfgItemForm resItemForm : itemFormList) {
                                HSSFRow rowDept = sheet.createRow(rownum++);
                                HSSFCell cell = rowDept.createCell(0);
                                cell.setCellValue(resItemForm.getName());
                                Map<String, String> dataMap = (Map<String, String>) gradeMap.get(resItemForm.getId());
                                if (dataMap != null) {
                                    String score = dataMap.get("score");
                                    String lostReason = dataMap.get("lostReason");
                                    if ("1".equals(score)) {
                                        cell = rowDept.createCell(1);
                                        cell.setCellValue("√");
                                    }
                                    if ("2".equals(score)) {
                                        cell = rowDept.createCell(2);
                                        cell.setCellValue("√");
                                    }
                                    if ("3".equals(score)) {
                                        cell = rowDept.createCell(3);
                                        cell.setCellValue("√");
                                    }
                                    if ("4".equals(score)) {
                                        cell = rowDept.createCell(4);
                                        cell.setCellValue("√");
                                    }
                                    if (StringUtil.isNotBlank(lostReason)) {
                                        cell = rowDept.createCell(5);
                                        cell.setCellValue(lostReason);
                                    }
                                }
                            }
                        }
                    }
                    int num = rownum;
                    HSSFRow row1 = sheet.createRow(num);
                    HSSFCell hc1 = row1.createCell(0);
                    hc1.setCellValue("有无专人带教:");
                    if (teach.equals("yes")) {
                        HSSFCell h1 = row1.createCell(1);
                        h1.setCellValue("是");
                    } else if (teach.equals("no")) {
                        HSSFCell h1 = row1.createCell(1);
                        h1.setCellValue("否");
                    }

                    HSSFRow row2 = sheet.createRow(num + 1);
                    HSSFCell hc2 = row2.createCell(0);
                    hc2.setCellValue("特色教学活动或亮点:");
                    HSSFCell h2 = row2.createCell(1);
                    h2.setCellValue(activty);

                    HSSFRow row3 = sheet.createRow(num + 2);
                    HSSFCell hc3 = row3.createCell(0);
                    hc3.setCellValue("您对本科室整体带教的建议和意见:");
                    HSSFCell h3 = row3.createCell(1);
                    h3.setCellValue(jianyi);

                    String fileName = "学员评价表.xls";
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                    response.setContentType("application/octet-stream;charset=UTF-8");
                    wb.write(response.getOutputStream());
                }
            }else {
                List<String> fileNames = new ArrayList();// 用于存放生成的文件名称s
                for (Map<String, String> map : recList) {
                    i++;
                    //单条结果导出集合
                    List<Map<String, String>> oneInfoList = new ArrayList<>();
                    String content = map.get("content");
                    Map<String, Object> gradeMap = resRecBiz.parseDocotrGradeXml(content);
                    gradesMap.put(map.get("recFlow"), gradeMap);
                    DeptTeacherGradeInfo teacherGradeInfo = resGradeBiz.readResGrade(map.get("recFlow"));

                    String activty = "";
                    String jianyi = "";
                    String teach = "";

                    if (gradeMap != null && !gradeMap.isEmpty()) {
                        for (String gk : gradeMap.keySet()) {
                            Object o = gradeMap.get(gk);
                            if (o instanceof Map) {
                            } else {
                                try {
                                    if (gk.equals("activty")) {
                                        activty = (String) gradeMap.get("activty");
                                    } else if (gk.equals("jianyi")) {
                                        jianyi = (String) gradeMap.get("jianyi");
                                    } else if (gk.equals("teach")) {
                                        teach = (String) gradeMap.get("teach");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    StringBuffer teachers = new StringBuffer();
                    if (StringUtil.isNotBlank(teacherGradeInfo.getTeacherNameOne())) {
                        teachers.append(teacherGradeInfo.getTeacherNameOne()).append("、");
                    }
                    if (StringUtil.isNotBlank(teacherGradeInfo.getTeacherNameTwo())) {
                        teachers.append(teacherGradeInfo.getTeacherNameTwo()).append("、");
                    }
                    if (StringUtil.isNotBlank(teacherGradeInfo.getTeacherNameThree())) {
                        teachers.append(teacherGradeInfo.getTeacherNameThree()).append("、");
                    }
                    String teacher = "";
                    if (teachers.length() > 0) {
                        teacher = teachers.substring(0, teachers.length() - 1);
                    }
                    //创建工作簿
                    HSSFWorkbook wb = new HSSFWorkbook();
                    // 为工作簿添加sheet
                    HSSFSheet sheet = wb.createSheet("sheet1");
                    //第一行
                    HSSFRow rowDeptHead = sheet.createRow(0);
                    //第一行第一列
                    HSSFCell cellHead1 = rowDeptHead.createCell(0);
                    cellHead1.setCellValue("轮转科室:" + map.get("deptName"));
                    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));

                    HSSFCell cellHead2 = rowDeptHead.createCell(2);
                    cellHead2.setCellValue("轮转时间:" + map.get("schStartDate") + "至" + map.get("schEndDate"));
                    sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 5));
                    //第二行
                    HSSFRow rowDeptHead1 = sheet.createRow(1);
                    HSSFCell cellHead3 = rowDeptHead1.createCell(0);
                    cellHead3.setCellValue("推荐老师:" + teacher);
                    sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 5));
                    //第三行
                    HSSFRow rowDeptHead2 = sheet.createRow(2);
                    HSSFCell cellHead4 = rowDeptHead2.createCell(1);
                    cellHead4.setCellValue("优");
                    HSSFCell cellHead5 = rowDeptHead2.createCell(2);
                    cellHead5.setCellValue("良");
                    HSSFCell cellHead6 = rowDeptHead2.createCell(3);
                    cellHead6.setCellValue("中");
                    HSSFCell cellHead7 = rowDeptHead2.createCell(4);
                    cellHead7.setCellValue("差");
                    HSSFCell cellHead8 = rowDeptHead2.createCell(5);
                    cellHead8.setCellValue("备注");

                    int rownum = 3;
                    for (ResEvaluationCfgTitleForm resEvaluationCfgTitleForm : titleFormList) {
                        if (resEvaluationCfgTitleForm != null && resEvaluationCfgTitleForm.getItemList() != null) {
                            List<ResEvaluationCfgItemForm> itemFormList = resEvaluationCfgTitleForm.getItemList();
                            for (ResEvaluationCfgItemForm resItemForm : itemFormList) {
                                HSSFRow rowDept = sheet.createRow(rownum++);
                                HSSFCell cell = rowDept.createCell(0);
                                cell.setCellValue(resItemForm.getName());
                                Map<String, String> dataMap = (Map<String, String>) gradeMap.get(resItemForm.getId());
                                if (dataMap != null) {
                                    String score = dataMap.get("score");
                                    String lostReason = dataMap.get("lostReason");
                                    if ("1".equals(score)) {
                                        cell = rowDept.createCell(1);
                                        cell.setCellValue("√");
                                    }
                                    if ("2".equals(score)) {
                                        cell = rowDept.createCell(2);
                                        cell.setCellValue("√");
                                    }
                                    if ("3".equals(score)) {
                                        cell = rowDept.createCell(3);
                                        cell.setCellValue("√");
                                    }
                                    if ("4".equals(score)) {
                                        cell = rowDept.createCell(4);
                                        cell.setCellValue("√");
                                    }
                                    if (StringUtil.isNotBlank(lostReason)) {
                                        cell = rowDept.createCell(5);
                                        cell.setCellValue(lostReason);
                                    }
                                }
                            }
                        }
                    }
                    int num = rownum;
                    HSSFRow row1 = sheet.createRow(num);
                    HSSFCell hc1 = row1.createCell(0);
                    hc1.setCellValue("有无专人带教:");
                    if (teach.equals("yes")) {
                        HSSFCell h1 = row1.createCell(1);
                        h1.setCellValue("是");
                    } else if (teach.equals("no")) {
                        HSSFCell h1 = row1.createCell(1);
                        h1.setCellValue("否");
                    }

                    HSSFRow row2 = sheet.createRow(num + 1);
                    HSSFCell hc2 = row2.createCell(0);
                    hc2.setCellValue("特色教学活动或亮点:");
                    HSSFCell h2 = row2.createCell(1);
                    h2.setCellValue(activty);

                    HSSFRow row3 = sheet.createRow(num + 2);
                    HSSFCell hc3 = row3.createCell(0);
                    hc3.setCellValue("您对本科室整体带教的建议和意见:");
                    HSSFCell h3 = row3.createCell(1);
                    h3.setCellValue(jianyi);

                    String fileName = map.get("operUserName") + map.get("deptName") + "(" + map.get("schStartDate") + "~" + map.get("schEndDate") + ")";
                    String file = request.getRealPath("/files") + "/" + fileName+ ".xls";
                    File fileTemp = new File(request.getRealPath("/files"));
                    if(!fileTemp.exists()){
                        fileTemp.mkdir();
                    }
                    fileNames.add(file);
                    FileOutputStream o = new FileOutputStream(file);
                    wb.write(o);
                    o.close();

                }

                String fileName = "评价明细导出";
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName+".zip", "UTF-8") + "\"");
                response.setContentType("application/octet-stream;charset=UTF-8");
                OutputStream out= response.getOutputStream();
                File zip = new File(request.getRealPath("/files") + "/" + fileName + ".zip");// 压缩文件

                File srcfile[] = new File[fileNames.size()];
                for (int j = 0, n1 = fileNames.size(); j < n1; j++) {
                    srcfile[j] = new File(fileNames.get(j));
                }
                ZipFiles(srcfile, zip);//压缩文件
                FileInputStream inStream = new FileInputStream(zip);
                byte[] buf = new byte[4096];
                int readLength;
                while (((readLength = inStream.read(buf)) != -1)) {
                    out.write(buf, 0, readLength);
                }
                inStream.close();
                zip.delete();//删除服务器上压缩文件
            }
        }
    }
    //压缩文件
    public static void ZipFiles(java.io.File[] srcfile, java.io.File zipfile) {
        byte[] buf = new byte[1024];
        ZipOutputStream out=null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipfile));
            for (int i = 0; i < srcfile.length; i++) {
                try {
                    FileInputStream in = new FileInputStream(srcfile[i]);
                    out.putNextEntry(new ZipEntry(srcfile[i].getName()));
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    out.closeEntry();
                    in.close();
                    srcfile[i].delete();//删除服务器上文件
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out!=null)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


    /**
     * 汇总
     *
     * @param deptFlow
     * @param userName
     * @param operStartDate
     * @param operEndDate
     * @param model
     * @return
     */
    @RequestMapping(value = "/thGradeSearchAll")
    public String thGradeSearchAll(String role,  String deptFlow, String userName, String deptName,
                                   String operStartDate, String operEndDate,
                                   Integer currentPage, HttpServletRequest request, Model model) {
        if (StringUtil.isBlank(operStartDate)) {
            operStartDate = DateUtil.getFirstDayOfMonth();
        }
        if (StringUtil.isBlank(operEndDate)) {
            operEndDate = DateUtil.getLastDateOfCurrMonth();
        }
        model.addAttribute("operStartDate",operStartDate);
        model.addAttribute("operEndDate",operEndDate);
        //当前用户所在机构
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        //查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("operStartDate", operStartDate);
        paramMap.put("operEndDate", operEndDate);
        paramMap.put("deptFlow", deptFlow);
        paramMap.put("deptName", deptName);
        String recType = ResRecTypeEnum.DoctorEval.getId();
        paramMap.put("recTypeId", recType);

        model.addAttribute("role", role);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String,Object>> datas = resGradeBiz.getDeptDoctorEvalStatic(paramMap);

        model.addAttribute("datas", datas);


        return "res/teacher/thGradeSearchAll";
    }
    @RequestMapping(value = "/thGradeSearchAllDetail")
    public String thGradeSearchAllDetail(String role,  String deptFlow,
                                   String operStartDate, String operEndDate,
                                         HttpServletRequest request, Model model) throws DocumentException {

        ResEvaluationCfg evaluationCfg = evaluationCfgBiz.readResEvaluationCfgByDept(deptFlow);
        String cfgContent="";
        if(evaluationCfg != null){
            cfgContent=evaluationCfg.getCfgBigValue();
        }

        List<ResEvaluationCfgTitleForm> titleFormList = new ArrayList<ResEvaluationCfgTitleForm>();
        if(StringUtil.isNotBlank(cfgContent)){
            Document dom = DocumentHelper.parseText(cfgContent);
            String titleXpath = "//title";
            List<Element> titleElementList = dom.selectNodes(titleXpath);
            if(titleElementList != null && !titleElementList.isEmpty()){
                for(Element te :titleElementList){
                    ResEvaluationCfgTitleForm titleForm = new ResEvaluationCfgTitleForm();
                    titleForm.setId(te.attributeValue("id"));
                    titleForm.setName(te.attributeValue("name"));
                    List<Element> itemElementList = te.elements("item");
                    List<ResEvaluationCfgItemForm> itemFormList = null;
                    if(itemElementList != null && !itemElementList.isEmpty()){
                        itemFormList = new ArrayList<ResEvaluationCfgItemForm>();
                        for(Element ie : itemElementList){
                            ResEvaluationCfgItemForm itemForm = new ResEvaluationCfgItemForm();
                            itemForm.setId(ie.attributeValue("id"));
                            itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                            itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                            itemFormList.add(itemForm);
                        }
                    }
                    titleForm.setItemList(itemFormList);
                    titleFormList.add(titleForm);
                }
                model.addAttribute("titleFormList", titleFormList);
            }
        }
        SysDept dept=deptBiz.readSysDept(deptFlow);
        //当前用户所在机构
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        //查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("operStartDate", operStartDate);
        paramMap.put("operEndDate", operEndDate);
        paramMap.put("deptFlow", deptFlow);
        String recType = ResRecTypeEnum.DoctorEval.getId();
        paramMap.put("recTypeId", recType);

        model.addAttribute("dept", dept);
        List<DeptTeacherGradeInfo> datas = resGradeBiz.getDeptDoctorEvalStaticDetail(paramMap);
        List<String> teachers=new ArrayList<>();
        Map<String,Object> gradesMap=new HashMap<>();
        Map<String,Integer> countMap=new HashMap<>();
        Map<String,String> userMap=new HashMap<>();
        if (datas != null && !datas.isEmpty()) {
            for (DeptTeacherGradeInfo rec : datas) {
                Map<String, Object> gradeMap = resRecBiz.parseDocotrGradeXml(rec.getRecContent());
                if(gradeMap!=null)
                {
                    for (ResEvaluationCfgTitleForm resEvaluationCfgTitleForm : titleFormList) {
                        if(resEvaluationCfgTitleForm!=null&&resEvaluationCfgTitleForm.getItemList()!=null){
                            List<ResEvaluationCfgItemForm> itemFormList = resEvaluationCfgTitleForm.getItemList();
                            for (ResEvaluationCfgItemForm resItemForm : itemFormList){
                                Map<String, String> dataMap= (Map<String, String>) gradeMap.get(resItemForm.getId());
                                if(dataMap!=null)
                                {
                                    String score = dataMap.get("score");
                                    if("1".equals(score))
                                    {
                                        Integer c=countMap.get(resItemForm.getId()+"1");
                                        if(c==null) c=0;
                                        c++;
                                        countMap.put(resItemForm.getId()+"1",c);
                                    }
                                    if("2".equals(score))
                                    {
                                        Integer c=countMap.get(resItemForm.getId()+"2");
                                        if(c==null) c=0;
                                        c++;
                                        countMap.put(resItemForm.getId()+"2",c);
                                    }
                                    if("3".equals(score))
                                    {
                                        Integer c=countMap.get(resItemForm.getId()+"3");
                                        if(c==null) c=0;
                                        c++;
                                        countMap.put(resItemForm.getId()+"3",c);
                                    }
                                    if("4".equals(score))
                                    {
                                        Integer c=countMap.get(resItemForm.getId()+"4");
                                        if(c==null) c=0;
                                        c++;
                                        countMap.put(resItemForm.getId()+"4",c);
                                    }
                                }
                            }
                        }
                    }
                    String teach = (String) gradeMap.get("teach");
                    if(teach.equals("yes")){
                        Integer c=countMap.get("teachyes");
                        if(c==null) c=0;
                        c++;
                        countMap.put("teachyes",c);
                    }else if(teach.equals("no")){
                        Integer c=countMap.get("teachno");
                        if(c==null) c=0;
                        c++;
                        countMap.put("teachno",c);
                    }
                }
                if(StringUtil.isNotBlank(rec.getTeacherFlowOne()))
                {
                    Integer c=countMap.get(rec.getTeacherFlowOne());
                    if(c==null) c=0;
                    c++;
                    countMap.put(rec.getTeacherFlowOne(),c);
                    userMap.put(rec.getTeacherFlowOne(),rec.getTeacherNameOne());
                    if(!teachers.contains(rec.getTeacherFlowOne()))
                    {
                        teachers.add(rec.getTeacherFlowOne());
                    }
                }
                if(StringUtil.isNotBlank(rec.getTeacherFlowTwo()))
                {
                    Integer c=countMap.get(rec.getTeacherFlowTwo());
                    if(c==null) c=0;
                    c++;
                    countMap.put(rec.getTeacherFlowTwo(),c);
                    userMap.put(rec.getTeacherFlowTwo(),rec.getTeacherNameTwo());
                    if(!teachers.contains(rec.getTeacherFlowTwo()))
                    {
                        teachers.add(rec.getTeacherFlowTwo());
                    }
                }
                if(StringUtil.isNotBlank(rec.getTeacherFlowThree()))
                {
                    Integer c=countMap.get(rec.getTeacherFlowThree());
                    if(c==null) c=0;
                    c++;
                    countMap.put(rec.getTeacherFlowThree(),c);
                    userMap.put(rec.getTeacherFlowThree(),rec.getTeacherNameThree());
                    if(!teachers.contains(rec.getTeacherFlowThree()))
                    {
                        teachers.add(rec.getTeacherFlowThree());
                    }
                }
            }
            Collections.sort(teachers,new Comparator<String>() {
                @Override
                public int compare(String u1,String u2) {
                    Integer s1 = countMap.get(u1);
                    Integer s2 = countMap.get(u2);
                    if(s1==null){
                        s1=0;
                    }
                    if(s2==null){
                        s2=0;
                    }
                    Integer result = s2-s1;

                    return result>0?1:result==0?0:-1;
                }

            });
        }
        model.addAttribute("gradesMap", gradesMap);
        model.addAttribute("datas", datas);
        model.addAttribute("userMap", userMap);
        model.addAttribute("countMap", countMap);
        model.addAttribute("teachers", teachers);
        model.addAttribute("evaluationCfg", evaluationCfg);

        return "res/teacher/thGradeSearchAllDetail";
    }
    @RequestMapping(value = "/exportInfoDetail")
    public void exportInfoDetail(String role,  String deptFlow,
                                   String operStartDate, String operEndDate,
                                   HttpServletResponse response,HttpServletRequest request, Model model) throws DocumentException, IOException {

        ResEvaluationCfg evaluationCfg = evaluationCfgBiz.readResEvaluationCfgByDept(deptFlow);
        List<String> teachers=new ArrayList<>();
        Map<String,Object> gradesMap=new HashMap<>();
        Map<String,Integer> countMap=new HashMap<>();
        Map<String,String> userMap=new HashMap<>();
        List<DeptTeacherGradeInfo> datas=null;
        List<ResEvaluationCfgTitleForm> titleFormList = new ArrayList<ResEvaluationCfgTitleForm>();
        SysDept dept = deptBiz.readSysDept(deptFlow);
        String cfgContent="";
        if(evaluationCfg != null) {
            cfgContent = evaluationCfg.getCfgBigValue();
            if (StringUtil.isNotBlank(cfgContent)) {
                Document dom = DocumentHelper.parseText(cfgContent);
                String titleXpath = "//title";
                List<Element> titleElementList = dom.selectNodes(titleXpath);
                if (titleElementList != null && !titleElementList.isEmpty()) {
                    for (Element te : titleElementList) {
                        ResEvaluationCfgTitleForm titleForm = new ResEvaluationCfgTitleForm();
                        titleForm.setId(te.attributeValue("id"));
                        titleForm.setName(te.attributeValue("name"));
                        List<Element> itemElementList = te.elements("item");
                        List<ResEvaluationCfgItemForm> itemFormList = null;
                        if (itemElementList != null && !itemElementList.isEmpty()) {
                            itemFormList = new ArrayList<ResEvaluationCfgItemForm>();
                            for (Element ie : itemElementList) {
                                ResEvaluationCfgItemForm itemForm = new ResEvaluationCfgItemForm();
                                itemForm.setId(ie.attributeValue("id"));
                                itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                                itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                                itemFormList.add(itemForm);
                            }
                        }
                        titleForm.setItemList(itemFormList);
                        titleFormList.add(titleForm);
                    }
                    model.addAttribute("titleFormList", titleFormList);
                }
            }
            //当前用户所在机构
            String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
            //查询条件
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("orgFlow", orgFlow);
            paramMap.put("operStartDate", operStartDate);
            paramMap.put("operEndDate", operEndDate);
            paramMap.put("deptFlow", deptFlow);
            String recType = ResRecTypeEnum.DoctorEval.getId();
            paramMap.put("recTypeId", recType);

            model.addAttribute("dept", dept);
             datas = resGradeBiz.getDeptDoctorEvalStaticDetail(paramMap);
            if (datas != null && !datas.isEmpty()) {
                for (DeptTeacherGradeInfo rec : datas) {
                    Map<String, Object> gradeMap = resRecBiz.parseDocotrGradeXml(rec.getRecContent());
                    if (gradeMap != null) {
                        for (ResEvaluationCfgTitleForm resEvaluationCfgTitleForm : titleFormList) {
                            if (resEvaluationCfgTitleForm != null && resEvaluationCfgTitleForm.getItemList() != null) {
                                List<ResEvaluationCfgItemForm> itemFormList = resEvaluationCfgTitleForm.getItemList();
                                for (ResEvaluationCfgItemForm resItemForm : itemFormList) {
                                    Map<String, String> dataMap = (Map<String, String>) gradeMap.get(resItemForm.getId());
                                    if (dataMap != null) {
                                        String score = dataMap.get("score");
                                        if ("1".equals(score)) {
                                            Integer c = countMap.get(resItemForm.getId() + "1");
                                            if (c == null) c = 0;
                                            c++;
                                            countMap.put(resItemForm.getId() + "1", c);
                                        }
                                        if ("2".equals(score)) {
                                            Integer c = countMap.get(resItemForm.getId() + "2");
                                            if (c == null) c = 0;
                                            c++;
                                            countMap.put(resItemForm.getId() + "2", c);
                                        }
                                        if ("3".equals(score)) {
                                            Integer c = countMap.get(resItemForm.getId() + "3");
                                            if (c == null) c = 0;
                                            c++;
                                            countMap.put(resItemForm.getId() + "3", c);
                                        }
                                        if ("4".equals(score)) {
                                            Integer c = countMap.get(resItemForm.getId() + "4");
                                            if (c == null) c = 0;
                                            c++;
                                            countMap.put(resItemForm.getId() + "4", c);
                                        }
                                    }
                                }
                            }
                        }
                        String teach = (String) gradeMap.get("teach");
                        if (teach.equals("yes")) {
                            Integer c = countMap.get("teachyes");
                            if (c == null) c = 0;
                            c++;
                            countMap.put("teachyes", c);
                        } else if (teach.equals("no")) {
                            Integer c = countMap.get("teachno");
                            if (c == null) c = 0;
                            c++;
                            countMap.put("teachno", c);
                        }
                    }
                    if (StringUtil.isNotBlank(rec.getTeacherFlowOne())) {
                        Integer c = countMap.get(rec.getTeacherFlowOne());
                        if (c == null) c = 0;
                        c++;
                        countMap.put(rec.getTeacherFlowOne(), c);
                        userMap.put(rec.getTeacherFlowOne(), rec.getTeacherNameOne());
                        if (!teachers.contains(rec.getTeacherFlowOne())) {
                            teachers.add(rec.getTeacherFlowOne());
                        }
                    }
                    if (StringUtil.isNotBlank(rec.getTeacherFlowTwo())) {
                        Integer c = countMap.get(rec.getTeacherFlowTwo());
                        if (c == null) c = 0;
                        c++;
                        countMap.put(rec.getTeacherFlowTwo(), c);
                        userMap.put(rec.getTeacherFlowTwo(), rec.getTeacherNameTwo());
                        if (!teachers.contains(rec.getTeacherFlowTwo())) {
                            teachers.add(rec.getTeacherFlowTwo());
                        }
                    }
                    if (StringUtil.isNotBlank(rec.getTeacherFlowThree())) {
                        Integer c = countMap.get(rec.getTeacherFlowThree());
                        if (c == null) c = 0;
                        c++;
                        countMap.put(rec.getTeacherFlowThree(), c);
                        userMap.put(rec.getTeacherFlowThree(), rec.getTeacherNameThree());
                        if (!teachers.contains(rec.getTeacherFlowThree())) {
                            teachers.add(rec.getTeacherFlowThree());
                        }
                    }
                }
                Collections.sort(teachers, new Comparator<String>() {
                    @Override
                    public int compare(String u1, String u2) {
                        Integer s1 = countMap.get(u1);
                        Integer s2 = countMap.get(u2);
                        if (s1 == null) {
                            s1 = 0;
                        }
                        if (s2 == null) {
                            s2 = 0;
                        }
                        Integer result = s2 - s1;

                        return result > 0 ? 1 : result == 0 ? 0 : -1;
                    }

                });
            }
            model.addAttribute("gradesMap", gradesMap);
            model.addAttribute("datas", datas);
            model.addAttribute("userMap", userMap);
            model.addAttribute("countMap", countMap);
            model.addAttribute("teachers", teachers);
            model.addAttribute("evaluationCfg", evaluationCfg);

        }

        createExcle(response,datas,gradesMap,userMap,countMap,
                teachers,evaluationCfg,dept,operStartDate,operEndDate,titleFormList);
    }

    private void createExcle(HttpServletResponse response, List<DeptTeacherGradeInfo> datas, Map<String, Object> gradesMap, Map<String, String> userMap, Map<String, Integer> countMap, List<String> teachers, ResEvaluationCfg evaluationCfg, SysDept dept, String operStartDate, String operEndDate, List<ResEvaluationCfgTitleForm> titleFormList) throws IOException {

        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFCellStyle styleRight = wb.createCellStyle(); //居中
        styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styleRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        int count=0;
        if(datas!=null)
            count=datas.size();
        if(evaluationCfg!=null)
        {
            // 为工作簿添加sheet
            HSSFSheet sheet = wb.createSheet("sheet1");
            HSSFRow rowOne = sheet.createRow(0);//第一行

            HSSFCell cellTitle = rowOne.createCell(0);
            cellTitle.setCellValue(evaluationCfg.getCfgCodeName()+"（分析汇总）");
            cellTitle.setCellStyle(styleCenter);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));//合并单元格

            HSSFRow rowTwo = sheet.createRow(1);//第二行
            HSSFCell cellTitle1 = rowTwo.createCell(0);
            cellTitle1.setCellValue("医院科室："+dept.getDeptName());
            cellTitle1.setCellStyle(styleCenter);

            HSSFCell cellTitle2 = rowTwo.createCell(1);
            cellTitle2.setCellValue("出科时间："+operStartDate+"~"+operEndDate);
            cellTitle2.setCellStyle(styleCenter);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));//合并单元格

            HSSFCell cellTitle3 = rowTwo.createCell(5);
            cellTitle3.setCellValue("份数："+count);
            cellTitle3.setCellStyle(styleCenter);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 8));//合并单元格

            HSSFRow rowThree = sheet.createRow(2);//第三行
            HSSFCell ct1 = rowThree.createCell(0);
            ct1.setCellValue("考评项");
            ct1.setCellStyle(styleCenter);
            HSSFCell ct2 = rowThree.createCell(1);
            ct2.setCellValue("优");
            ct2.setCellStyle(styleCenter);
            HSSFCell ct3 = rowThree.createCell(2);
            ct3.setCellValue("百分比");
            ct3.setCellStyle(styleCenter);
            HSSFCell ct4 = rowThree.createCell(3);
            ct4.setCellValue("良");
            ct4.setCellStyle(styleCenter);
            HSSFCell ct5 = rowThree.createCell(4);
            ct5.setCellValue("百分比");
            ct5.setCellStyle(styleCenter);
            HSSFCell ct6 = rowThree.createCell(5);
            ct6.setCellValue("中");
            ct6.setCellStyle(styleCenter);
            HSSFCell ct7 = rowThree.createCell(6);
            ct7.setCellValue("百分比");
            ct7.setCellStyle(styleCenter);
            HSSFCell ct8 = rowThree.createCell(7);
            ct8.setCellValue("差");
            ct8.setCellStyle(styleCenter);
            HSSFCell ct9 = rowThree.createCell(8);
            ct9.setCellValue("百分比");
            ct9.setCellStyle(styleCenter);
            int rowNum=3;
            for(ResEvaluationCfgTitleForm f:titleFormList)
            {
                if(f.getItemList()!=null)
                {
                    for(ResEvaluationCfgItemForm item:f.getItemList())
                    {
                        HSSFRow row = sheet.createRow(rowNum++);//第三行

                        HSSFCell r1 = row.createCell(0);
                        r1.setCellValue(item.getName());
                        r1.setCellStyle(styleCenter);
                        int c=1;
                        for(int j=1;j<=4;j++)
                        {
                            String key=item.getId()+j;
                            Integer fs=countMap.get(key);
                            if(fs==null)
                                fs=0;

                            HSSFCell cell = row.createCell(c++);
                            cell.setCellValue(fs);
                            cell.setCellStyle(styleCenter);
                            String per=per(fs,count)+"%";
                            cell = row.createCell(c++);
                            cell.setCellValue(per);
                            cell.setCellStyle(styleCenter);
                        }
                    }
                }
            }
            HSSFRow row = sheet.createRow(rowNum++);//第三行

            Integer fs=countMap.get("teachyes");
            Integer fs2=countMap.get("teachno");
            if(fs==null)
                fs=0;
            if(fs2==null)
                fs2=0;
            HSSFCell cell = row.createCell(0);
            cell.setCellValue("有无专人带教");
            cell.setCellStyle(styleCenter);
            cell = row.createCell(1);
            cell.setCellValue("有");
            cell.setCellStyle(styleCenter);
            String per=per(fs,count)+"%";
            cell = row.createCell(2);
            cell.setCellValue(per);
            cell.setCellStyle(styleCenter);
            cell = row.createCell(3);
            cell.setCellValue("无");
            cell.setCellStyle(styleCenter);
            per=per(fs2,count)+"%";
            cell = row.createCell(4);
            cell.setCellValue(per);
            cell.setCellStyle(styleCenter);

            row = sheet.createRow(rowNum++);//第三行
            cell = row.createCell(0);
            cell.setCellValue("带教姓名");
            cell.setCellStyle(styleCenter);
            cell = row.createCell(1);
            cell.setCellValue("推荐优秀带教次数");
            cell.setCellStyle(styleCenter);
            sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 1, 4));//合并单元格
            for(String t:teachers)
            {
                row = sheet.createRow(rowNum++);//第三行
                cell = row.createCell(0);
                cell.setCellValue(userMap.get(t));
                cell.setCellStyle(styleCenter);
                cell = row.createCell(1);
                cell.setCellValue(countMap.get(t)==null?"0":countMap.get(t)+"");
                cell.setCellStyle(styleCenter);
                sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 1, 4));//合并单元格
            }

            String name = evaluationCfg.getCfgCodeName()+"（分析汇总）.xls";
            response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
            response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ExcleUtile.setCookie(response);
            wb.write(response.getOutputStream());
        }else{

            String name = "未配置评分表.xls";
            response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
            response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ExcleUtile.setCookie(response);
            wb.write(response.getOutputStream());
        }
    }

    private static String per(Integer fs, Integer count) {
        if(fs==null||count==null||fs==0||count==0)
            return "0";
        float num= (float)fs/count*100;
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        return df.format(num);//返回的是String类型
    }

    public static void main(String[] args){
        String data = "测试abc123";
//        String zdyKey = "";//自定义key
//        System.out.println(encrypt(data, zdyKey));
//        System.out.println(decrypt(encrypt(data, zdyKey), zdyKey));
        System.out.println(per(null,null));
        System.out.println(per(null,1));
        System.out.println(per(1,null));
        System.out.println(per(1,1));
        //这里的数后面加“D”是表明它是Double类型，否则相除的话取整，无法正常使用
        double percent = 150D / 150D;

        //输出一下，确认你的小数无误
        System.out.println("小数：" + percent);

        //获取格式化对象
        NumberFormat nt = NumberFormat.getPercentInstance();

        //设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(2);

        //最后格式化并输出
        System.out.println("百分数：" + nt.format(percent));
    }
}