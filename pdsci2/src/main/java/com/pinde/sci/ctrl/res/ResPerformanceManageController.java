package com.pinde.sci.ctrl.res;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResActivityBiz;
import com.pinde.sci.biz.res.IResCostCfgDetailBiz;
import com.pinde.sci.biz.res.IResCostCfgMainBiz;
import com.pinde.sci.biz.res.IResPerformanceManageBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.res.FormulaForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResAllowancePayment;
import com.pinde.sci.model.res.ResAllowanceUserExt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/res/performanceManage")
public class ResPerformanceManageController extends GeneralController {

    @Autowired
    private IJsResActivityBiz activityBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private SysOrgExtMapper orgExtMapper;
    @Autowired
    private IResCostCfgMainBiz costCfgMainBiz;
    @Autowired
    private IResCostCfgDetailBiz costCfgDetailBiz;
    @Autowired
    private IResPerformanceManageBiz resPerformanceManageBiz;

    @RequestMapping(value="/main")
    public String main(Model model, String  role, String  orgFlow, HttpServletRequest request){
        SysUser currentUser= GlobalContext.getCurrentUser();
        SysOrg currentOrg=orgBiz.readSysOrg(currentUser.getOrgFlow());
        List<SysOrg> orgs = new ArrayList<>();
        if(GlobalConstant.USER_LIST_GLOBAL.equals(role)){
            //查询所有医院
            SysOrg org = new SysOrg();
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgs = orgBiz.searchOrg(org);
            if(orgs!=null&&orgs.size()>0){
                if(StringUtil.isBlank(orgFlow)){
                    orgFlow=orgs.get(0).getOrgFlow();
                }
            }
            model.addAttribute("orgs",orgs);
        }else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
            //查询高校下所有医院
            String workOrgId = currentOrg.getSendSchoolId();
            orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
            if(orgs!=null&&orgs.size()>0){
                if(StringUtil.isBlank(orgFlow)){
                    orgFlow=orgs.get(0).getOrgFlow();
                }
            }
            model.addAttribute("orgs",orgs);
        }else if(GlobalConstant.USER_LIST_LOCAL.equals(role)){
            orgFlow=currentUser.getOrgFlow();
        }
        List<SysDept> depts=deptBiz.searchDeptByOrg(orgFlow);
        model.addAttribute("depts",depts);
        model.addAttribute("roleFlag",role);
        return "res/activity/performanceManage/main";
    }
    @RequestMapping(value="/list")
    public String list(Model model,Integer currentPage,String orgFlow,String deptFlow,
                       String startTime,String endTime, HttpServletRequest request){
        SysUser curUser=GlobalContext.getCurrentUser();
        SysDept dept=new SysDept();
        dept.setDeptFlow(deptFlow);
        if(StringUtil.isBlank(orgFlow)){
            orgFlow=curUser.getOrgFlow();
        }
        dept.setOrgFlow(orgFlow);
        dept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysDept> deptList = deptBiz.searchDept(dept);

        Map<String,Object> map=new HashMap<>();
        Map<String,Map<String,String>> numMap = new HashMap<>();
        Map<String,String> scoreMap = new HashMap<>();
        List<SysDept> deptList2 = deptBiz.searchDept(dept);
        if(deptList2!=null&&deptList2.size()>0) {

            Map<String,Object> param = new HashMap<>();
            List<String> deptFlows = new ArrayList<>();
            param.put("startTime",startTime);
            param.put("endTime",endTime);

            for(SysDept sysDept:deptList2) {
                map.put(sysDept.getDeptFlow(),activityBiz.getDeptActivityStatisticsMap(sysDept.getDeptFlow(),startTime,endTime));
                deptFlows.add(sysDept.getDeptFlow());
            }

            param.put("deptFlows",deptFlows);

            List<Map<String,Object>> maps = activityBiz.getDeptActivityCountMap(param);
            if(maps != null && maps.size()>0){
                for (Map<String,Object> m : maps) {
                    Map<String,String> result = new HashMap<>();
                    result.put(m.get("B").toString(),m.get("RN").toString());
                    numMap.put(m.get("DEPT_FLOW").toString(),result);
                }
            }
            List<Map<String,Object>> scoreList=activityBiz.readActivityResults2(param);
            if(scoreList != null && scoreList.size()>0){
                for (Map<String,Object> m:scoreList) {
                    scoreMap.put(m.get("DEPT_FLOW").toString(),m.get("RN").toString());
                }
            }
        }
        model.addAttribute("map",map);
        model.addAttribute("numMap",numMap);
        model.addAttribute("scoreMap",scoreMap);
        model.addAttribute("list",deptList);
        return "res/activity/performanceManage/list";
    }

    @RequestMapping(value="/exportList")
    public void exportList(String orgFlow,String deptFlow,
                           String startTime,String endTime, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String[] head = new String[]{};
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFCellStyle styleRight = wb.createCellStyle(); //居中
        styleRight.setAlignment(HorizontalAlignment.RIGHT);
        styleRight.setVerticalAlignment(VerticalAlignment.CENTER);

        SysUser curUser=GlobalContext.getCurrentUser();
        SysDept dept=new SysDept();
        dept.setDeptFlow(deptFlow);
        if(StringUtil.isBlank(orgFlow)){
            orgFlow=curUser.getOrgFlow();
        }
        dept.setOrgFlow(orgFlow);
        dept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDept> deptList = deptBiz.searchDept(dept);
        List<SysDict> dictList= DictTypeEnum.sysListDictMap.get(DictTypeEnum.ActivityType.getId());
        if(dictList==null) {
            dictList = new ArrayList<>();
        }

        List<String> list = new ArrayList<>();
        list.add("科室名称");
        if(dictList != null && dictList.size()>0){
            for (int i = 0; i<dictList.size();i++) {
                list.add(dictList.get(i).getDictName());
            }
        }
        list.add("合计");
        list.add("活动数量排名");
        list.add("活动评分排名");
        String []titles = list.toArray(new String[list.size()]);

        HSSFSheet sheet = wb.createSheet("sheet1");
        HSSFRow row = sheet.createRow(0);//第一行

        HSSFCell cellTitle3 = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle3 = row.createCell(i);
            cellTitle3.setCellValue(titles[i]);
            cellTitle3.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 506);
        }

        String[] resultList = null;
        Map<String,Map<String,String>> numMap = new HashMap<>();
        Map<String,String> scoreMap = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        List<String> deptFlows = new ArrayList<>();

        if(deptList !=null && deptList.size()>0){
            for(int i = 0;i<deptList.size();i++){
                deptFlows.add(deptList.get(i).getDeptFlow());
            }
        }
        param.put("startTime", startTime);
        param.put("endTime", endTime);
        param.put("deptFlows", deptFlows);
        List<Map<String, Object>> maps = activityBiz.getDeptActivityCountMap(param);
        if(maps != null && maps.size()>0){
            for (Map<String,Object> m : maps) {
                Map<String,String> result = new HashMap<>();
                result.put(m.get("B").toString(),m.get("RN").toString());
                numMap.put(m.get("DEPT_FLOW").toString(),result);
            }
        }
        List<Map<String, Object>> scoreList = activityBiz.readActivityResults2(param);
        if(scoreList != null && scoreList.size()>0){
            for (Map<String,Object> m:scoreList) {
                scoreMap.put(m.get("DEPT_FLOW").toString(),m.get("RN").toString());
            }
        }
        if(deptList!=null) {
            if (deptList != null && deptList.size() > 0) {
                int k = 1;
                for (int i = 0; i < deptList.size(); i++) {
                    List<String> list2 = new ArrayList<>();
                    HSSFRow rowTwo = sheet.createRow((int) k++);
                    SysDept sysDept = deptList.get(i);
                    Map<String, Object> map = activityBiz.getDeptActivityStatisticsMap(sysDept.getDeptFlow(), startTime, endTime);

                    deptFlows.add(sysDept.getDeptFlow());

                    list2.add(sysDept.getDeptName());
                    if(map!=null) {
                        for(SysDict dict:dictList) {
                            String result = String.valueOf(map.get(sysDept.getDeptFlow() + dict.getDictId()));
                            if (StringUtil.isBlank(result) || "null".equals(result)) result = "0";
                            list2.add(result);
                        }
                    }
                    Map<String,String> m = numMap.get(sysDept.getDeptFlow());
                    if(m!=null) {
                        Set<String> set = m.keySet();
                        for (String key:set) {
                            list2.add(key);
                            list2.add(m.get(key));
                        }
                    }else{
                        list2.add("0");
                        list2.add("0");
                    }
                    String scoreNum = scoreMap.get(sysDept.getDeptFlow());
                    if(StringUtil.isNotBlank(scoreNum)) {
                        list2.add(scoreNum);
                    }else{
                        list2.add("0");
                    }
                    resultList = list2.toArray(new String[list2.size()]);

                    for (int j = 0; j < titles.length; j++) {
                        HSSFCell cellFirst = rowTwo.createCell(j);
                        cellFirst.setCellStyle(styleCenter);
                        cellFirst.setCellValue(resultList[j]);
                    }
                }

            }
        }
        String fileName = "科室统计.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    @RequestMapping(value="/searchDeptList", method={RequestMethod.GET})
    @ResponseBody
    public List<SysDept> searchDeptList(String orgFlow){
        List<SysDept> depts=new ArrayList<>();
        if(StringUtil.isNotBlank(orgFlow)){
            depts=deptBiz.searchDeptByOrg(orgFlow);
        }
        return depts;
    }

    @RequestMapping(value="/main2")
    public String main2(HttpServletRequest request){
        return "res/activity/performanceManage/main2";
    }

    @RequestMapping("/professionalBaseList")
    public String professionalBaseManagerList(Integer currentPage ,String resTrainingSpeId, String startTime,String orgFlow,
                                              String endTime, Model model,HttpServletRequest request){

        SysUser currUser = GlobalContext.getCurrentUser();
        if(StringUtil.isBlank(orgFlow)){
            orgFlow = currUser.getOrgFlow();
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String,String>> speDeptList = activityBiz.searchSpeDept(orgFlow,resTrainingSpeId);
        model.addAttribute("speDeptList",speDeptList);
        Map<String,String> listMap = new HashMap<>();
        if(speDeptList != null && speDeptList.size()>0){
            for (Map<String,String> sd: speDeptList) {
                listMap.put((String)sd.get("trainingSpeId"),(String)sd.get("trainingSpeName"));
            }
        }
        model.addAttribute("list",listMap);
        Map<String,Map<String,String>> numMap = new HashMap<>();
        Map<String,String> scoreMap = new HashMap<>();

        List<Map<String,String>> speDeptList2 = activityBiz.searchSpeDept(orgFlow,resTrainingSpeId);
        Map<String,Object> map=new HashMap<>();
        if(speDeptList2!=null && speDeptList2.size()>0){

            Map<String,Object> param = new HashMap<>();

            List<String> trainingSpeIds = new ArrayList<>();
            param.put("startTime",startTime);
            param.put("endTime",endTime);

            for (Map<String,String> rtsd:speDeptList2) {
                map.put((String)rtsd.get("trainingSpeId"),activityBiz.getDeptActivityMap((String)rtsd.get("trainingSpeId"),startTime,endTime));
                trainingSpeIds.add((String)rtsd.get("trainingSpeId"));
            }
            param.put("trainingSpeIds",trainingSpeIds);

            List<Map<String,Object>> maps = activityBiz.getActivityCountMap(param);
            if(maps != null && maps.size()>0){
                for (Map<String,Object> m : maps) {
                    Map<String,String> result = new HashMap<>();
                    result.put(m.get("B").toString(),m.get("RN").toString());
                    numMap.put(m.get("TRAINING_SPE_ID").toString(),result);
                }
            }
            List<Map<String,Object>> scoreList = activityBiz.readResults(param);
            if(scoreList != null && scoreList.size()>0){
                for (Map<String,Object> m:scoreList) {
                    scoreMap.put(m.get("trainingSpeId").toString(),m.get("rn").toString());
                }
            }
        }
        model.addAttribute("map",map);
        model.addAttribute("numMap",numMap);
        model.addAttribute("scoreMap",scoreMap);
        return "res/activity/performanceManage/baseList";
    }

    @RequestMapping(value="/baseExportList")
    public void baseExportList(String orgFlow,String resTrainingSpeId,
                           String startTime,String endTime, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String[] head = new String[]{};
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFCellStyle styleRight = wb.createCellStyle(); //居中
        styleRight.setAlignment(HorizontalAlignment.RIGHT);
        styleRight.setVerticalAlignment(VerticalAlignment.CENTER);

        SysUser curUser=GlobalContext.getCurrentUser();
        if(StringUtil.isBlank(orgFlow)){
            orgFlow=curUser.getOrgFlow();
        }
        List<Map<String,String>> speDeptList = activityBiz.searchSpeDept(orgFlow,resTrainingSpeId);
        List<String> list = new ArrayList<>();
        List<SysDict> dictList= DictTypeEnum.sysListDictMap.get(DictTypeEnum.ActivityType.getId());
        if(dictList==null) {
            dictList = new ArrayList<>();
        }
        list.add("专业基地");
        if(dictList != null && dictList.size()>0){
            for (int i = 0; i<dictList.size();i++) {
                list.add(dictList.get(i).getDictName());
            }
        }
        list.add("合计");
        list.add("活动数量排名");
        list.add("活动评分排名");
        String []titles = list.toArray(new String[list.size()]);

        HSSFSheet sheet = wb.createSheet("sheet1");
        HSSFRow row = sheet.createRow(0);//第一行

        HSSFCell cellTitle3 = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle3 = row.createCell(i);
            cellTitle3.setCellValue(titles[i]);
            cellTitle3.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 506);
        }

        String[] resultList = null;
        Map<String,Map<String,String>> numMap = new HashMap<>();
        Map<String,String> scoreMap = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        List<String> trainingSpeIds = new ArrayList<>();

        if(speDeptList !=null && speDeptList.size()>0){
            for(int i = 0;i<speDeptList.size();i++){
                trainingSpeIds.add((String)speDeptList.get(i).get("trainingSpeId"));
            }
        }
        param.put("startTime", startTime);
        param.put("endTime", endTime);
        param.put("trainingSpeIds", trainingSpeIds);

        List<Map<String,Object>> maps = activityBiz.getActivityCountMap(param);
        if(maps != null && maps.size()>0){
            for (Map<String,Object> m : maps) {
                Map<String,String> result = new HashMap<>();
                result.put(m.get("B").toString(),m.get("RN").toString());
                numMap.put(m.get("TRAINING_SPE_ID").toString(),result);
            }
        }
        List<Map<String,Object>> scoreList = activityBiz.readResults(param);
        if(scoreList != null && scoreList.size()>0){
            for (Map<String,Object> m:scoreList) {
                scoreMap.put(m.get("trainingSpeId").toString(),m.get("rn").toString());
            }
        }
        if(speDeptList!=null) {
            if (speDeptList != null && speDeptList.size() > 0) {
                int k = 1;
                for (int i = 0; i < speDeptList.size(); i++) {
                    List<String> list2 = new ArrayList<>();
                    HSSFRow rowTwo = sheet.createRow((int) k++);
                    Map<String,String> mp = speDeptList.get(i);
                    Map<String, Object> map = activityBiz.getDeptActivityMap((String)mp.get("trainingSpeId"), startTime, endTime);

                    list2.add((String)mp.get("trainingSpeName"));
                    if(map!=null) {
                        for(SysDict dict:dictList) {
                            String result = String.valueOf(map.get((String)mp.get("trainingSpeId") + dict.getDictId()));
                            if (StringUtil.isBlank(result) || "null".equals(result)) result = "0";
                            list2.add(result);
                        }
                    }
                    Map<String,String> m = numMap.get((String)mp.get("trainingSpeId"));
                    if(m!=null) {
                        Set<String> set = m.keySet();
                        for (String key:set) {
                            list2.add(key);
                            list2.add(m.get(key));
                        }
                    }else{
                        list2.add("0");
                        list2.add("0");
                    }
                    String scoreNum = scoreMap.get((String)mp.get("trainingSpeId"));
                    if(StringUtil.isNotBlank(scoreNum)) {
                        list2.add(scoreNum);
                    }else{
                        list2.add("0");
                    }
                    resultList = list2.toArray(new String[list2.size()]);

                    for (int j = 0; j < titles.length; j++) {
                        HSSFCell cellFirst = rowTwo.createCell(j);
                        cellFirst.setCellStyle(styleCenter);
                        cellFirst.setCellValue(resultList[j]);
                    }
                }
            }
        }
        String fileName = "专业基地统计.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    @RequestMapping(value="/activityMain")
    public String activityMain(String typeId,Model model,HttpServletRequest request){
        model.addAttribute("typeId",typeId);
        return "res/performanceManage/main";
    }

    /**
     * 公式查询
     */
    @RequestMapping("/ActivityPerformanceSearch")
    public String ActivityPerformanceSearch(Integer currentPage,String startDate,String orgFlow, String endDate,
                                            String typeId,Model model,HttpServletRequest request){
        SysUser user = GlobalContext.getCurrentUser();
        if(StringUtil.isBlank(orgFlow)){
            orgFlow = user.getOrgFlow();
        }
        ResCostCfgMain costCfgMain = new ResCostCfgMain();
        if(StringUtil.isNotBlank(startDate)) {
            costCfgMain.setStartDate(startDate);
        }
        if(StringUtil.isNotBlank(endDate)) {
            costCfgMain.setEndDate(endDate);
        }
        costCfgMain.setOrgFlow(orgFlow);
        if(typeId.equals(DictTypeEnum.ActivityType.getId())) {
            costCfgMain.setTypeId(DictTypeEnum.ActivityType.getId());
        }else if(typeId.equals(DictTypeEnum.LectureType.getId())){
            costCfgMain.setTypeId(DictTypeEnum.LectureType.getId());
        }else{
            costCfgMain.setTypeId(DictTypeEnum.DoctorTrainingSpe.getId());
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResCostCfgMain> costCfgMainList = costCfgMainBiz.search(costCfgMain);
        model.addAttribute("typeId",typeId);
        model.addAttribute("list",costCfgMainList);
        return "res/performanceManage/list";
    }

    /**
     * 查看公式
     */
    @RequestMapping("/searchByMainFlow")
    public String searchByMainFlow(String typeId,Model model,String mainFlow,String itemId,String flag){
        model.addAttribute("typeId",typeId);
        model.addAttribute("flag",flag);
        if(StringUtil.isBlank(mainFlow)){
            return "res/performanceManage/searchFormula";
        }
        ResCostCfgMain costCfgMain = costCfgMainBiz.readCostCfgMain(mainFlow);
        model.addAttribute("costCfgMain",costCfgMain);

        ResCostCfgDetail cfgDetail = new ResCostCfgDetail();
        cfgDetail.setMainFlow(mainFlow);
        if(StringUtil.isNotBlank(itemId)) {
            cfgDetail.setItemId(itemId);
        }
        List<ResCostCfgDetail> resCostCfgDetails = costCfgDetailBiz.search(cfgDetail);
        model.addAttribute("resCostCfgDetails",resCostCfgDetails);

        return "res/performanceManage/searchFormula";
    }

    /**
     *新增公式
     */
    @RequestMapping("/savePerformanceFormula")
    @ResponseBody
    public String savePerformanceFormula(@RequestBody FormulaForm form, HttpServletRequest request){
        int result = 0;
        ResCostCfgMain main = form.getMainFrom();
        Map<String,ResCostCfgDetail> detailMap = form.getDetailMap();

        if(main != null) {
            SysUser user = GlobalContext.getCurrentUser();
            main.setOrgFlow(user.getOrgFlow());
            main.setOrgName(user.getOrgName());
            if(main.getTypeId().equals(DictTypeEnum.ActivityType.getId())) {
                main.setTypeName(DictTypeEnum.ActivityType.getName());
            }else if(main.getTypeId().equals(DictTypeEnum.LectureType.getId())){
                main.setTypeName(DictTypeEnum.LectureType.getName());
            }else{
                main.setTypeName("专业基地");
            }
            if (StringUtil.isNotBlank(main.getMainFlow())) {
                result = costCfgMainBiz.updateMain(main);
            } else {
                result = costCfgMainBiz.saveMain(main);
            }
            if (result > 0) {
                if (detailMap != null && detailMap.size()>0) {
                    for (String key : detailMap.keySet()) {
                        ResCostCfgDetail detail = detailMap.get(key);
                        detail.setOrgFlow(user.getOrgFlow());
                        detail.setOrgName(user.getOrgName());
                        detail.setMainFlow(main.getMainFlow());
                        detail.setStartDate(main.getStartDate());
                        detail.setEndDate(main.getEndDate());
                        costCfgDetailBiz.saveDetail(detail);
                    }
                }
                return GlobalConstant.SAVE_SUCCESSED;
            }
            return "时间段不可重复！";
        }
        return GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping(value="/allowanceMain")
    public String allowanceMain(Model model,String startDate,String endDate,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put(InitConfig.getSysCfg("res_teacher_role_flow"),"带教老师");
        map.put(InitConfig.getSysCfg("res_secretary_role_flow"),"科秘");
        map.put(InitConfig.getSysCfg("res_head_role_flow"),"科主任");
        map.put(InitConfig.getSysCfg("res_professionalBase_admin_role_flow"),"专业基地主任");
        model.addAttribute("roleMap",map);
        if(StringUtil.isBlank(startDate) && StringUtil.isBlank(endDate)) {
            endDate = DateUtil.getCurrDate();
            startDate = DateUtil.addDate(DateUtil.getCurrDate(), -180);
        }
        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate",endDate);
        return "res/performanceManage/allowanceMain";
    }

    /**
     *津贴发放管理
     */
    @RequestMapping("/allowancePayment")
    public String allowancePayment(Model model,String startDate,String endDate,String doctorName,String roleFlow,
                                   Integer currentPage,HttpServletRequest request){
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        List<String> roleFlowList = new ArrayList<>();
        roleFlowList.add(InitConfig.getSysCfg("res_teacher_role_flow"));
        roleFlowList.add(InitConfig.getSysCfg("res_secretary_role_flow"));
        roleFlowList.add(InitConfig.getSysCfg("res_head_role_flow"));
        roleFlowList.add(InitConfig.getSysCfg("res_professionalBase_admin_role_flow"));
        Map<String,Object> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("roleList",roleFlowList);
        param.put("doctorName",doctorName);
        param.put("roleFlow",roleFlow);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResAllowanceUserExt> list = resPerformanceManageBiz.searchUser(param);
        model.addAttribute("userList",list);
        Map<String,Object> pMap = new HashMap<>();
        Map<String,Object> tMap = new HashMap<>();
        Map<String,Object> payMap = new HashMap<>();
        Map<String,Object> speNameMap = new HashMap<>();
        Map<String,Object> atyNumMap = new HashMap<>();
        Map<String,Object> atyPayMap = new HashMap<>();
        Map<String,Object> lMap = new HashMap<>();
        Map<String,Object> lPayMap = new HashMap<>();
        if(list!=null && list.size()>0){
            for (ResAllowanceUserExt userExt:list) {

                String[] roleFlows = userExt.getRoleFlows().split(",");
                List<String> roleList = Arrays.asList(roleFlows);

                Map<String,Object> param2 = new HashMap<>();
                param2.put("startDate",startDate);
                param2.put("endDate",endDate);
                param2.put("userFlow",userExt.getUserFlow());

                Map<String,Object> param3 = new HashMap<>();
                param3.put("startDate",startDate);
                param3.put("endDate",endDate);
                param3.put("userName",userExt.getUserName());
                int pNum = 0;//带教人数
                int tNum = 0;//带教次数
                int pay = 0;//带教补助
                int atyNum = 0;//教学活动次数
                int atyPay = 0;//教学活动补助
                int ltNum = 0;//讲座
                int ltPay = 0;
                String speName = "";
                for(int i = 0;i<roleList.size();i++){
                    if(InitConfig.getSysCfg("res_teacher_role_flow").equals(roleList.get(i))){
                        List<ResAllowancePayment> paymentList = resPerformanceManageBiz.searchAllowancePaymentTea(param2);
                        if(paymentList!=null && paymentList.size()>0){
                            pNum += paymentList.size();
                            for (ResAllowancePayment rap:paymentList) {
                                tNum += Integer.parseInt(rap.getNumberCount());
                                pay += Double.valueOf(rap.getSchMonth())*Double.valueOf(rap.getTeaCost());
                                if(StringUtil.isBlank(speName)) {
                                    speName = rap.getTrainingSpeName();
                                }
                            }
                        }
                        //教学活动
                        Map<String,String> ayMap = resPerformanceManageBiz.searchActivityTea(param2);
                        if(ayMap != null) {
                            atyNum += Integer.parseInt(ayMap.get("countNum"));
                            atyPay += Integer.parseInt(ayMap.get("countNum")) * Integer.parseInt(ayMap.get("teaCost"));
                        }
                        //讲座
                        Map<String,String> ltMap = resPerformanceManageBiz.searchLecture(param3);
                        if(ltMap != null) {
                            ltNum += Integer.parseInt(ltMap.get("countNum"));
                            ltPay += Integer.parseInt(ltMap.get("countNum")) * Integer.parseInt(ltMap.get("teaCost"));
                        }
                        continue;
                    }
                    if(InitConfig.getSysCfg("res_secretary_role_flow").equals(roleList.get(i))){
                        List<ResAllowancePayment> paymentList = resPerformanceManageBiz.searchAllowancePayment(param2);
                        if(paymentList!=null && paymentList.size()>0){
                            pNum += paymentList.size();
                            for (ResAllowancePayment rap:paymentList) {
                                tNum += Integer.parseInt(rap.getNumberCount());
                                pay += Double.valueOf(rap.getSchMonth())*Double.valueOf(rap.getKmCost());
                                if(StringUtil.isBlank(speName)) {
                                    speName = rap.getTrainingSpeName();
                                }
                            }
                        }
                        Map<String,String> ayMap = resPerformanceManageBiz.searchActivity(param2);
                        if(ayMap != null) {
                            atyNum += Integer.parseInt(ayMap.get("countNum"));
                            atyPay += Integer.parseInt(ayMap.get("countNum")) * Integer.parseInt(ayMap.get("kmCost"));
                        }

                        Map<String,String> ltMap = resPerformanceManageBiz.searchLecture(param3);
                        if(ltMap != null) {
                            ltNum += Integer.parseInt(ltMap.get("countNum"));
                            ltPay += Integer.parseInt(ltMap.get("countNum")) * Integer.parseInt(ltMap.get("kmCost"));
                        }
                        continue;
                    }
                    if(InitConfig.getSysCfg("res_head_role_flow").equals(roleList.get(i))){
                        List<ResAllowancePayment> paymentList = resPerformanceManageBiz.searchAllowancePayment(param2);
                        if(paymentList!=null && paymentList.size()>0){
                            pNum += paymentList.size();
                            for (ResAllowancePayment rap:paymentList) {
                                tNum += Integer.parseInt(rap.getNumberCount());
                                pay += Double.valueOf(rap.getSchMonth())*Double.valueOf(rap.getHeadCost());
                                if(StringUtil.isBlank(speName)) {
                                    speName = rap.getTrainingSpeName();
                                }
                            }
                        }
                        Map<String,String> ayMap = resPerformanceManageBiz.searchActivity(param2);
                        if(ayMap != null) {
                            atyNum += Integer.parseInt(ayMap.get("countNum"));
                            atyPay += Integer.parseInt(ayMap.get("countNum")) * Integer.parseInt(ayMap.get("headCost"));
                        }

                        Map<String,String> ltMap = resPerformanceManageBiz.searchLecture(param3);
                        if(ltMap != null) {
                            ltNum += Integer.parseInt(ltMap.get("countNum"));
                            ltPay += Integer.parseInt(ltMap.get("countNum")) * Integer.parseInt(ltMap.get("headCost"));
                        }
                        continue;
                    }
                    if(InitConfig.getSysCfg("res_professionalBase_admin_role_flow").equals(roleList.get(i))){
                        List<ResAllowancePayment> paymentList = resPerformanceManageBiz.searchAllowancePaymentSpe(param2);
                        if(paymentList!=null && paymentList.size()>0){
                            pNum += paymentList.size();
                            for (ResAllowancePayment rap:paymentList) {
                                tNum += Integer.parseInt(rap.getNumberCount());
                                pay += Double.valueOf(rap.getSchMonth())*Double.valueOf(rap.getSpeCost());
                                if(StringUtil.isBlank(speName)) {
                                    speName = rap.getTrainingSpeName();
                                }
                            }
                        }
                        Map<String,String> ayMap = resPerformanceManageBiz.searchActivitySpe(param2);
                        if(ayMap != null) {
                            atyNum += Integer.parseInt(ayMap.get("countNum"));
                            atyPay += Integer.parseInt(ayMap.get("countNum")) * Integer.parseInt(ayMap.get("speCost"));
                        }

                        Map<String,String> ltMap = resPerformanceManageBiz.searchLecture(param3);
                        if(ltMap != null) {
                            ltNum += Integer.parseInt(ltMap.get("countNum"));
                            ltPay += Integer.parseInt(ltMap.get("countNum")) * Integer.parseInt(ltMap.get("speCost"));
                        }
                        continue;
                    }
                }

                pMap.put(userExt.getUserFlow(),pNum);
                tMap.put(userExt.getUserFlow(),tNum);
                payMap.put(userExt.getUserFlow(),pay);
                speNameMap.put(userExt.getUserFlow(),speName);
                atyNumMap.put(userExt.getUserFlow(),atyNum);
                atyPayMap.put(userExt.getUserFlow(),atyPay);
                lMap.put(userExt.getUserFlow(),ltNum);
                lPayMap.put(userExt.getUserFlow(),ltPay);

                model.addAttribute("pNumMap",pMap);
                model.addAttribute("tNumMap",tMap);
                model.addAttribute("payMap",payMap);
                model.addAttribute("speNameMap",speNameMap);
                model.addAttribute("atyNumMap",atyNumMap);
                model.addAttribute("atyPayMap",atyPayMap);
                model.addAttribute("ltNumMap",lMap);
                model.addAttribute("ltPayMap",lPayMap);

            }
        }
        return "res/performanceManage/allowancePayment";
    }

    @RequestMapping(value="/exportPayment")
    public void exportPayment(String startDate,String endDate,String doctorName,String roleFlow, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] head = new String[]{};
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFCellStyle styleRight = wb.createCellStyle(); //居中
        styleRight.setAlignment(HorizontalAlignment.RIGHT);
        styleRight.setVerticalAlignment(VerticalAlignment.CENTER);

        String[] titles = new String[]{
                "姓名",
                "角色",
                "专业基地",
                "总带教人数",
                "总带教次数",
                "教学活动次数",
                "教学活动补助",
                "带教补助",
                "补助合计"
        };
        HSSFSheet sheet = wb.createSheet("sheet1");
        HSSFRow row = sheet.createRow(0);//第一行

        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = row.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 506);
        }


        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        List<String> roleFlowList = new ArrayList<>();
        roleFlowList.add(InitConfig.getSysCfg("res_teacher_role_flow"));
        roleFlowList.add(InitConfig.getSysCfg("res_secretary_role_flow"));
        roleFlowList.add(InitConfig.getSysCfg("res_head_role_flow"));
        roleFlowList.add(InitConfig.getSysCfg("res_professionalBase_admin_role_flow"));
        Map<String, Object> param = new HashMap<>();
        param.put("orgFlow", orgFlow);
        param.put("roleList", roleFlowList);
        param.put("doctorName", doctorName);
        param.put("roleFlow", roleFlow);
        List<ResAllowanceUserExt> list = resPerformanceManageBiz.searchUser(param);
        if (list != null && list.size() > 0) {
            int k = 1;
            for (ResAllowanceUserExt userExt : list) {
                String[] resultList = null;
                HSSFRow rowTwo = sheet.createRow((int) k++);

                String[] roleFlows = userExt.getRoleFlows().split(",");
                List<String> roleList = Arrays.asList(roleFlows);
                Map<String, Object> param2 = new HashMap<>();
                param2.put("startDate", startDate);
                param2.put("endDate", endDate);
                param2.put("userFlow", userExt.getUserFlow());

                Map<String, Object> param3 = new HashMap<>();
                param3.put("startDate", startDate);
                param3.put("endDate", endDate);
                param3.put("userName", userExt.getUserName());
                int pNum = 0;//带教人数
                int tNum = 0;//带教次数
                int pay = 0;//带教补助
                int atyNum = 0;//教学活动次数
                int atyPay = 0;//教学活动补助
                int ltNum = 0;//讲座
                int ltPay = 0;
                String speName = "";
                for (int i = 0; i < roleList.size(); i++) {
                    if (InitConfig.getSysCfg("res_teacher_role_flow").equals(roleList.get(i))) {
                        List<ResAllowancePayment> paymentList = resPerformanceManageBiz.searchAllowancePaymentTea(param2);
                        if (paymentList != null && paymentList.size() > 0) {
                            pNum += paymentList.size();
                            for (ResAllowancePayment rap : paymentList) {
                                tNum += Integer.parseInt(rap.getNumberCount());
                                pay += Double.valueOf(rap.getSchMonth()) * Double.valueOf(rap.getTeaCost());
                                if (StringUtil.isBlank(speName)) {
                                    speName = rap.getTrainingSpeName();
                                }
                            }
                        }
                        //教学活动
                        Map<String, String> ayMap = resPerformanceManageBiz.searchActivityTea(param2);
                        if (ayMap != null) {
                            atyNum += Integer.parseInt(ayMap.get("countNum"));
                            atyPay += Integer.parseInt(ayMap.get("countNum")) * Integer.parseInt(ayMap.get("teaCost"));
                        }
                        //讲座
                        Map<String, String> ltMap = resPerformanceManageBiz.searchLecture(param3);
                        if (ltMap != null) {
                            ltNum += Integer.parseInt(ltMap.get("countNum"));
                            ltPay += Integer.parseInt(ltMap.get("countNum")) * Integer.parseInt(ltMap.get("teaCost"));
                        }
                        continue;
                    }
                    if (InitConfig.getSysCfg("res_secretary_role_flow").equals(roleList.get(i))) {
                        List<ResAllowancePayment> paymentList = resPerformanceManageBiz.searchAllowancePayment(param2);
                        if (paymentList != null && paymentList.size() > 0) {
                            pNum += paymentList.size();
                            for (ResAllowancePayment rap : paymentList) {
                                tNum += Integer.parseInt(rap.getNumberCount());
                                pay += Double.valueOf(rap.getSchMonth()) * Double.valueOf(rap.getKmCost());
                                if (StringUtil.isBlank(speName)) {
                                    speName = rap.getTrainingSpeName();
                                }
                            }
                        }
                        Map<String, String> ayMap = resPerformanceManageBiz.searchActivity(param2);
                        if (ayMap != null) {
                            atyNum += Integer.parseInt(ayMap.get("countNum"));
                            atyPay += Integer.parseInt(ayMap.get("countNum")) * Integer.parseInt(ayMap.get("kmCost"));
                        }

                        Map<String, String> ltMap = resPerformanceManageBiz.searchLecture(param3);
                        if (ltMap != null) {
                            ltNum += Integer.parseInt(ltMap.get("countNum"));
                            ltPay += Integer.parseInt(ltMap.get("countNum")) * Integer.parseInt(ltMap.get("kmCost"));
                        }
                        continue;
                    }
                    if (InitConfig.getSysCfg("res_head_role_flow").equals(roleList.get(i))) {
                        List<ResAllowancePayment> paymentList = resPerformanceManageBiz.searchAllowancePayment(param2);
                        if (paymentList != null && paymentList.size() > 0) {
                            pNum += paymentList.size();
                            for (ResAllowancePayment rap : paymentList) {
                                tNum += Integer.parseInt(rap.getNumberCount());
                                pay += Double.valueOf(rap.getSchMonth()) * Double.valueOf(rap.getHeadCost());
                                if (StringUtil.isBlank(speName)) {
                                    speName = rap.getTrainingSpeName();
                                }
                            }
                        }
                        Map<String, String> ayMap = resPerformanceManageBiz.searchActivity(param2);
                        if (ayMap != null) {
                            atyNum += Integer.parseInt(ayMap.get("countNum"));
                            atyPay += Integer.parseInt(ayMap.get("countNum")) * Integer.parseInt(ayMap.get("headCost"));
                        }

                        Map<String, String> ltMap = resPerformanceManageBiz.searchLecture(param3);
                        if (ltMap != null) {
                            ltNum += Integer.parseInt(ltMap.get("countNum"));
                            ltPay += Integer.parseInt(ltMap.get("countNum")) * Integer.parseInt(ltMap.get("headCost"));
                        }
                        continue;
                    }
                    if (InitConfig.getSysCfg("res_professionalBase_admin_role_flow").equals(roleList.get(i))) {
                        List<ResAllowancePayment> paymentList = resPerformanceManageBiz.searchAllowancePaymentSpe(param2);
                        if (paymentList != null && paymentList.size() > 0) {
                            pNum += paymentList.size();
                            for (ResAllowancePayment rap : paymentList) {
                                tNum += Integer.parseInt(rap.getNumberCount());
                                pay += Double.valueOf(rap.getSchMonth()) * Double.valueOf(rap.getSpeCost());
                                if (StringUtil.isBlank(speName)) {
                                    speName = rap.getTrainingSpeName();
                                }
                            }
                        }
                        Map<String, String> ayMap = resPerformanceManageBiz.searchActivitySpe(param2);
                        if (ayMap != null) {
                            atyNum += Integer.parseInt(ayMap.get("countNum"));
                            atyPay += Integer.parseInt(ayMap.get("countNum")) * Integer.parseInt(ayMap.get("speCost"));
                        }

                        Map<String, String> ltMap = resPerformanceManageBiz.searchLecture(param3);
                        if (ltMap != null) {
                            ltNum += Integer.parseInt(ltMap.get("countNum"));
                            ltPay += Integer.parseInt(ltMap.get("countNum")) * Integer.parseInt(ltMap.get("speCost"));
                        }
                        continue;
                    }
                }
                int countNum = atyNum + ltNum;
                int countPay = atyPay + ltPay;
                int cou = atyPay + ltPay + pay;
                resultList = new String[]{
                        userExt.getUserName(),
                        userExt.getRoleName(),
                        speName,
                        pNum+"",
                        tNum+"",
                        countNum+"",
                        countPay+"",
                        pay+"",
                        cou+""
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowTwo.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(resultList[j]);
                }
            }
        }
        String fileName = "津贴发放.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

}
