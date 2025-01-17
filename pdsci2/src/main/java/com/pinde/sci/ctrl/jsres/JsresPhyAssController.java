package com.pinde.sci.ctrl.jsres;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.sci.dao.PhyAssExtMapper;
import com.pinde.core.common.sci.dao.PubFileMapper;
import com.pinde.core.common.sci.dao.ResTeachPlanDoctorMapper;
import com.pinde.core.common.sci.dao.ResTeachQualifiedPlanMapper;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResPhyAssBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.core.model.ResTeachQualifiedPlanExt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/jsres/phyAss")
public class JsresPhyAssController extends GeneralController {

    @Autowired
    private IJsResPhyAssBiz phyAssBiz;
    @Autowired
    private PhyAssExtMapper phyAssExtMapper;
    @Autowired
    private ResTeachQualifiedPlanMapper planMapper;
    @Autowired
    private IFileBiz pubFileBiz;
    @Autowired
    private PubFileMapper pubFileMapper;
    @Autowired
    private ResTeachPlanDoctorMapper planDoctorMapper;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IDictBiz dictBiz;

    private static Logger logger = LoggerFactory.getLogger(JsresPhyAssController.class);

    //培训计划发布
    @RequestMapping(value = "/plannedReleaseMain")
    public String plannedReleaseMain(Model model,String roleFlag) {
        List<Map<String, Object>> list = phyAssExtMapper.searchAllByTime("");
        model.addAttribute("contentList",list);
        model.addAttribute("roleFlag",roleFlag);
        return "jsres/phyAss/plannedReleaseMain";
    }

    //医师协会-培训计划列表
    @RequestMapping(value = "/plannedReleaseList")
    public String plannedReleaseList(Model model,Integer currentPage,HttpServletRequest request,String planFlow,
                                     String speName,String planStatusId,String enrollStartTime,String enrollEndTime) {
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("planFlow",planFlow);
        paramMap.put("speName",speName);
        paramMap.put("planStatusId",planStatusId);
        paramMap.put("enrollStartTime",enrollStartTime);
        paramMap.put("enrollEndTime",enrollEndTime);
        paramMap.put("nowData", com.pinde.core.util.DateUtil.getCurrDateTime2());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, Object>> list = phyAssExtMapper.searchPlanInfoList(paramMap);
        model.addAttribute("list",list);
        model.addAttribute("nowDate", com.pinde.core.util.DateUtil.getCurrDateTime2());
        return "jsres/phyAss/planedReleaseList";
    }

    @RequestMapping(value = "/checkPlanContent")
    @ResponseBody
    public String checkPlanContent(String planContent) {
        int count = phyAssBiz.checkPlanContent(planContent);    //检查培训计划名称是否重复
        if (count>0){
            return com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        return com.pinde.core.common.GlobalConstant.FLAG_N;
    }

    /**
     * 显示培训计划
     * @param planFlow
     * @return
     */
    @RequestMapping(value = "/showAskContent")
    @ResponseBody
    public String showAskContent(String planFlow) {
        ResTeachQualifiedPlan plan = planMapper.selectByPrimaryKey(planFlow);
        if (null !=plan && StringUtil.isNotBlank(plan.getAskContent())){
            return plan.getAskContent();
        }
        return "";
    }



    //培训计划发布    新增修改页面
    @RequestMapping(value = "/editPlannedReleaseMain")
    public String editPlannedReleaseMain(Model model,String type,String planFlow,String isExpert) {
        model.addAttribute("type",type);
        ResTeachQualifiedPlan plan =new ResTeachQualifiedPlan();
        if (type.equals("edit") || type.equals("show")){
            plan = planMapper.selectByPrimaryKey(planFlow);
            if (null!=plan && StringUtil.isNotBlank(plan.getAskContent())){
                String askContent = plan.getAskContent();
                String replace = askContent.replace("\\\\n", "<br>");
                plan.setAskContent(replace);
            }
            List<ResQualifiedPlanMsg> msgList = phyAssBiz.searchByPlanFlow(planFlow);
            List<PubFile> fileList = pubFileBiz.searchByProductFlow(planFlow);
            model.addAttribute("msgList",msgList);
            model.addAttribute("fileList",fileList);
        }else {
            plan.setIntroduce("为深入推进江苏省住院医师规范化培训(以下简称“住培”）工作，提升住培师资带教水平，结合当前疫情防控要求，" +
                    "2022年度江苏省住院医师规范化培训第十七期师资培训班于线上开班。本次培训活动由江苏卫生健康委员会承办。");
        }
        model.addAttribute("plan",plan);
        model.addAttribute("isExpert",isExpert);
        if (type.equals("show")){
            return "jsres/phyAss/showPlannedRelease";
        }
        return "jsres/phyAss/editPlannedReleaseMain";
    }


    /**
     * 医师协会 修改培训计划
     * @param jsondata
     * @param request
     * @param type
     * @return
     */
    @RequestMapping(value = "/editPlannedRelease")
    @ResponseBody
    public String editPlannedRelease(String jsondata, HttpServletRequest request,String type) throws UnsupportedEncodingException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)(request);
        List<MultipartFile> fileList = multipartRequest.getFiles("files");
        ResTeachQualifiedPlanExt ext = JSON.parseObject(jsondata, ResTeachQualifiedPlanExt.class);
        boolean status = phyAssBiz.savePlannedRelease(ext, fileList, type);
        if (status==true){
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }

    /**
     * 删除文件
     * @param fileFlow
     * @return
     */
    @RequestMapping(value = "/delPlanFileByFlow")
    @ResponseBody
    public String delPlanFileByFlow(String fileFlow) {
        int i = pubFileBiz.deleteAfterFile(fileFlow);
        if (i>0){
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
    }


    /**
     * 医师协会 删除 师资-培训计划
     * @param planFlow
     * @return
     */
    @RequestMapping(value = "/delPhyAss")
    @ResponseBody
    public String delPhyAss(String planFlow) {
        boolean status = phyAssBiz.delPhyAss(planFlow);
        if (status==true){
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
    }


    @RequestMapping(value = "/baseListEntry")
    public String baseListEntry(Model model,Integer currentPage,HttpServletRequest request,String planFlow, String speName,
                                String planStatusId,String enrollStartTime,String enrollEndTime,String type) {
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("planFlow",planFlow);
        paramMap.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        paramMap.put("speName",speName);
        paramMap.put("planStatusId",planStatusId);
        paramMap.put("enrollStartTime",enrollStartTime);
        paramMap.put("enrollEndTime",enrollEndTime);
        paramMap.put("nowData", com.pinde.core.util.DateUtil.getCurrDateTime2());
        paramMap.put("local", com.pinde.core.common.GlobalConstant.FLAG_Y);
        if (StringUtil.isNotBlank(type) && type.equals("import")){
            paramMap.put("importPlan", com.pinde.core.common.GlobalConstant.FLAG_Y);
            PageHelper.startPage(currentPage,getPageSize(request));
            List<Map<String, Object>> list = phyAssExtMapper.searchOrgPlanInfoList(paramMap);
            model.addAttribute("list",list);
            model.addAttribute("nowDate", com.pinde.core.util.DateUtil.getCurrDateTime2());
            return "jsres/phyAss/importMain";
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, Object>> list = phyAssExtMapper.searchOrgPlanInfoList(paramMap);
        model.addAttribute("list",list);
        model.addAttribute("nowDate", com.pinde.core.util.DateUtil.getCurrDateTime2());
        return "jsres/phyAss/baseListEntryList";
    }


    @RequestMapping(value = "/baseListEntryUser")
    public String baseListEntryUser(Model model,String planFlow) {
        ResTeachQualifiedPlan plan = planMapper.selectByPrimaryKey(planFlow);
        List<ResQualifiedPlanMsg> msgList = phyAssBiz.searchByPlanFlow(planFlow);
        int countNum=0;
        for (ResQualifiedPlanMsg msg : msgList) {
            int classNum = Integer.parseInt(msg.getClassNum());
            int peopleNum = Integer.parseInt(msg.getPeopleNum());
            countNum=countNum+classNum*peopleNum;
        }
        model.addAttribute("countNum",countNum);
        model.addAttribute("msgList",msgList);
        model.addAttribute("plan",plan);
        return "jsres/phyAss/baseListEntryUser";
    }

    @RequestMapping(value = "/baseListEntryUserList")
    public String baseListEntryUserList(Model model,String speId,String planFlow) {
        //未选择的人员
        List<Map<String, Object>> userList = phyAssExtMapper.seachUser(GlobalContext.getCurrentUser().getOrgFlow(),planFlow);
        //各专业已选的人员
        List<ResTeachPlanDoctor> doctorList = phyAssBiz.searchPlanDoctorByPlanFlow(planFlow,speId,GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("userList",userList);
        model.addAttribute("speId",speId);
        model.addAttribute("planFlow",planFlow);
        return "jsres/phyAss/baseListEntryUserList";
    }

    @RequestMapping(value = "/saveBaseListEntryUser")
    @ResponseBody
    public String saveBaseListEntryUser(String dataMsg,String speId,String speName,String planFlow) {
        return phyAssBiz.saveBaseListEntryUser(dataMsg, speId, speName, planFlow);
    }

    /**
     * 基地录入人员 - 导入人员的模板
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/expertBaseListEntryUserTemp")
    @ResponseBody
    public void expertBaseListEntryUserTemp(HttpServletResponse response,String planFlow) throws IOException {
        ResTeachQualifiedPlan plan = planMapper.selectByPrimaryKey(planFlow);//培训计划
        List<ResQualifiedPlanMsg> planMsgList = phyAssBiz.searchByPlanFlow(planFlow);//培训计划的专业
        String orgName = GlobalContext.getCurrentUser().getOrgName();//当前基地名称

        HSSFWorkbook wb = new HSSFWorkbook();// excel文件对象
        HSSFSheet sheetlist = wb.createSheet("sheetlist");// 工作表对象
        HSSFCellStyle fontCenterStyle = wb.createCellStyle();	//粗图居中显示
        HSSFFont font = wb.createFont();	//设置字体
        fontCenterStyle.setFont(font);
        fontCenterStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        fontCenterStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFRow row0 = sheetlist.createRow(0);//第一行
        String[] titles0 = new String[]{
                "培训计划",
                "培训专业",
                "基地",
                "登录名",
                "姓名",
                "性别",
                "角色",
                "科室",
                "手机号",
                "身份证号码",
                "电子邮箱"
        };

        HSSFCell cellTitle0 = null;
        for (int i = 0; i < titles0.length; i++) {
            cellTitle0 = row0.createCell(i);
            cellTitle0.setCellValue(titles0[i]);
            cellTitle0.setCellStyle(fontCenterStyle);
            cellTitle0.setCellType(CellType.STRING);
        }

        //设置培训计划的下拉列表内容
        String[] planList= {plan.getPlanContent()};
        DVConstraint planConstraint = DVConstraint.createExplicitListConstraint(planList);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList planRegions = new CellRangeAddressList(1, 500, 0, 0);
        HSSFDataValidation plan_data_validation_list = new HSSFDataValidation(planRegions, planConstraint);
        sheetlist.addValidationData(plan_data_validation_list);

        //设置培训计划专业的下拉列表内容
        String[] speList= new String[planMsgList.size()];
        for (int i = 0; i < planMsgList.size(); i++) {
            speList[i]=planMsgList.get(i).getSpeName();
        }
        DVConstraint speConstraint = DVConstraint.createExplicitListConstraint(speList);
        CellRangeAddressList speRegions = new CellRangeAddressList(1, 500, 1, 1);
        HSSFDataValidation spe_data_validation_list = new HSSFDataValidation(speRegions, speConstraint);
        sheetlist.addValidationData(spe_data_validation_list);

        String[] orgList= {orgName};
        DVConstraint orgConstraint = DVConstraint.createExplicitListConstraint(orgList);
        CellRangeAddressList orgRegions = new CellRangeAddressList(1, 500, 2, 2);
        HSSFDataValidation org_data_validation_list = new HSSFDataValidation(orgRegions, orgConstraint);
        sheetlist.addValidationData(org_data_validation_list);


        String fileName = "人员导入模板.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    @RequestMapping("/importBaseUser")
    public String importBaseUser(Model model,String planFlow) {
        model.addAttribute("planFlow",planFlow);
        return "jsres/phyAss/importBaseUser";
    }

    @RequestMapping(value = {"/importBaseUserExcel"}, method = {RequestMethod.POST})
    @ResponseBody
    public String importBaseUserExcel(MultipartFile file,String planFlow ) {
        if (file.getSize() > 0) {
            int count = 0;
            try {
                count = phyAssBiz.importBaseUserExcel(file, planFlow);
            } catch (Exception e) {
                logger.error("", e);
                return e.getMessage();
            }
            if (count != 0) {
                return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
            }
        }
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
    }



    /**
     * 查询培训计划各专业录入人数
     * @param planFlow
     * @return
     */
    @RequestMapping(value = "/searchSpeNum")
    @ResponseBody
    public String searchSpeNum(String planFlow) {
        List<Map<String, Object>> list = phyAssExtMapper.searchSpeNum(planFlow,GlobalContext.getCurrentUser().getOrgFlow());
        String speMsg = JSON.toJSONString(list);
        return speMsg;
    }


    @RequestMapping(value = "/expertPlan")
    public String expertPlan(HttpServletRequest request, HttpServletResponse response, String planFlow) throws Exception {
        //定义数据容器
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //新建word模板
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        //context获取路径
        ServletContext context = request.getServletContext();
        ResTeachQualifiedPlan plan = planMapper.selectByPrimaryKey(planFlow);
        dataMap.put("planContent",plan.getPlanContent());
        dataMap.put("enrollEndTime",plan.getEnrollEndTime());
        dataMap.put("enrollStartTime",plan.getEnrollStartTime());
        dataMap.put("planStartTime",plan.getPlanStartTime());
        dataMap.put("planEndTime",plan.getPlanEndTime());
        dataMap.put("introduce",plan.getIntroduce());
        if (StringUtil.isNotBlank(plan.getAskContent())){
            String askContent = plan.getAskContent();
            String replace= askContent.replaceAll("<br/>", "\n");
            dataMap.put("askContent",replace);
        }
        List<ResQualifiedPlanMsg> planMsgList = phyAssBiz.searchByPlanFlow(planFlow);

        List<Map<String, String>> doctorMapList = new ArrayList();
        for (int i = 0; i < planMsgList.size(); i++) {
            Map<String, String> doctorMap = new HashMap();
            doctorMap.put("speName", planMsgList.get(i).getSpeName());
            doctorMap.put("classNum", planMsgList.get(i).getClassNum());
            doctorMap.put("peopleNum", planMsgList.get(i).getPeopleNum());
            doctorMap.put("adress", planMsgList.get(i).getAdress());
            doctorMap.put("remark", planMsgList.get(i).getRemark());
            String planTartget="";
            String target = planMsgList.get(i).getPlanTarget();
            if (target.contains("1")){
                planTartget=planTartget+"主任医师、";
            }
            if (target.contains("2")){
                planTartget=planTartget+"副主任医师、";
            }
            if (target.contains("3")){
                planTartget=planTartget+"主治医师、";
            }
            if (target.contains("4")){
                planTartget=planTartget+"住院医师、";
            }
            if (target.contains("5")){
                planTartget=planTartget+"医士";
            }
            doctorMap.put("planTarget", planTartget);
            doctorMapList.add(doctorMap);
        }

        dataMap.put("doctorMapList",doctorMapList);
        //文件名称
        String name = plan.getPlanContent()+".docx";
        String path = "/jsp/jsres/phyAss/plan.docx";//模板
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, "", true);
        if (temeplete != null) {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
        return null;
    }


    /**
     * 基地上报人员
     * @param planFlow
     * @return
     */
    @RequestMapping(value = "/appearUser")
    @ResponseBody
    public String appearUser(String planFlow) {
        phyAssExtMapper.appearUser(planFlow,GlobalContext.getCurrentUser().getOrgFlow());
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 点击人数查询人员信息
     * @param planFlow
     * @return
     */
    @RequestMapping(value = "/searchPlanUserList")
    public String searchPlanUserList(String planFlow,String type,Model model,Integer currentPage,HttpServletRequest request) {
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, Object>> list = phyAssExtMapper.searchPlanUserList(GlobalContext.getCurrentUser().getOrgFlow(), planFlow,type);
        ResTeachQualifiedPlan plan = planMapper.selectByPrimaryKey(planFlow);
        model.addAttribute("plan",plan);
        model.addAttribute("list",list);
        model.addAttribute("planFlow",planFlow);
        model.addAttribute("type",type);
        return "jsres/phyAss/planUserList";
    }

    /**
     * 导出人员信息
     * @param response
     * @param planFlow
     * @param type
     * @throws Exception
     */
    @RequestMapping(value = "/expertPlanUser")
    public void expertPlanUser(HttpServletResponse response,String planFlow,String type) throws Exception {
        List<Map<String, Object>> list = phyAssExtMapper.searchPlanUserList(GlobalContext.getCurrentUser().getOrgFlow(), planFlow,type);
        String[] titles = new String[]{
                "doctorCode:登录名",
                "doctorName:用户名",
                "sexName:性别",
                "speName:培训专业",
                "deptName:科室",
                "doctorRoleName:角色",
                "userPhone:手机号",
                "idNo:身份证号",
                "userEmail:电子邮箱"
        };
        String fileName = "人员信息列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream(), new String[]{"0"});
        response.setContentType("application/octet-stream;charset=UTF-8");
    }


    /**
     *基地 师资信息维护
     * @return
     */
    @RequestMapping(value = "/plannedMaintainMain")
    public String plannedMaintainMain(Model model) {
        List<Map<String, Object>> list = phyAssExtMapper.searchAllByTime("");
        List<SysDept> depts = deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("depts", depts);
        model.addAttribute("contentList",list);
        return "jsres/phyAss/plannedMaintainMain";
    }

    /**
     *基地 师资信息维护列表
     * @return
     */
    @RequestMapping(value = "/plannedMaintainList")
    public String plannedMaintainList(Model model,Integer currentPage,HttpServletRequest request,
                                      String planFlow,String speId,String deptFlow,String doctorName,String type) {
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        paramMap.put("planFlow",planFlow);
        paramMap.put("speId",speId);
        paramMap.put("deptFlow",deptFlow);
        paramMap.put("doctorName",doctorName);
        paramMap.put("type",type);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, Object>> list = phyAssExtMapper.plannedMaintainList(paramMap);
        model.addAttribute("list",list);
        return "jsres/phyAss/plannedMaintainList";
    }


    /**
     * 基地 师资信息维护  编辑人员信息
     * @param model
     * @param recordFlow    人员流水号
     * @param planFlow  培训计划流水号
     * @return
     */
    @RequestMapping(value = "/editPlanUserInfo")
    public String editPlanUserInfo(Model model,String recordFlow,String planFlow) {
        List<Map<String, Object>> contentList = phyAssExtMapper.searchAllByTime(com.pinde.core.util.DateUtil.getCurrDateTime2());
        List<SysDept> depts = deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("recordFlow",recordFlow);
        List<Map<String, Object>> list = phyAssExtMapper.plannedMaintainList(paramMap);
        if (null !=list && !list.isEmpty()){
            Map<String, Object> map = list.get(0);
            model.addAttribute("map",map);
            List<PubFile> filesList = pubFileBiz.findFileByTypeFlow("phyAssUser", (String)list.get(0).get("doctorFlow"));
            if ( null != filesList && filesList.size()>0){
                model.addAttribute("file",filesList.get(0));
            }
        }
        model.addAttribute("depts", depts);
        model.addAttribute("contentList",contentList);
        model.addAttribute("planFlow",planFlow);
        return "jsres/phyAss/editPlanUserInfo";
    }

    @RequestMapping(value = "/deleteCerfiticateImg")
    @ResponseBody
    public String deleteCerfiticateImg(String fileFlow) {
        PubFile pubFile = pubFileMapper.selectByPrimaryKey(fileFlow);
        pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
        int count = pubFileMapper.updateByPrimaryKey(pubFile);
        if (count>0){
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
        }

        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 查询培训计划对应的专业
     * @param planFlow
     * @return
     */
    @RequestMapping(value = "/searchPlanByPlanFlow")
    @ResponseBody
    public String searchPlanByPlanFlow(String planFlow) {
        List<ResQualifiedPlanMsg> planMsgList = phyAssBiz.searchByPlanFlow(planFlow);
        String planMsg = JSON.toJSONString(planMsgList);
        return planMsg;
    }

    /**
     * 基地 师资信息维护  保存人员信息
     * @param userFlow  用户流水号
     * @param planFlow  培训计划
     * @param recordFlow    人员记录流水号
     * @param speId 培训计划对应的专业代码
     * @param speName 培训计划对应的专业名称
     * @param doctorCode    登录名
     * @param doctorName    用户名
     * @param sexId   性别id
     * @param sexName   性别
     * @param deptFlow  科室
     * @param userPhone 手机号
     * @param idNo  身份证号码
     * @param userEmail 邮箱
     * @return
     */
    @RequestMapping(value = "/savePlanUserInfo")
    @ResponseBody
    public String savePlanUserInfo(String userFlow,String planFlow,String recordFlow,String speId,String speName, String doctorCode,HttpServletRequest request,
                                   String doctorName, String sexId, String sexName,String deptFlow,String userPhone,String idNo,String userEmail,String fileFlow) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)(request);
        List<MultipartFile> fileList = multipartRequest.getFiles("uploadFile");
        phyAssBiz.savePubFile(fileList,userFlow,fileFlow);
        //修改培训方案中人员的信息
        int doctorNum = phyAssBiz.savePlanDoctorInfo(planFlow, recordFlow, speId, speName, doctorCode, doctorName);
        //修改用户表基本信息和科室信息
        boolean userStatus = phyAssBiz.saveUser(userFlow, doctorCode, doctorName, sexId, sexName, userPhone, idNo, userEmail, deptFlow);
        if (doctorNum>0 && userStatus==true){
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }


    //医师协会-师资名单确认
    @RequestMapping(value = "/planScoreMain")
    public String planScoreMain(Model model) {
        List<Map<String, Object>> list = phyAssExtMapper.searchAllByTime("");
        model.addAttribute("contentList",list);
        return "jsres/phyAss/planScoreMain";
    }


    //医师协会-师资名单确认列表
    @RequestMapping(value = "/planScoreList")
    public String planScoreList(Model model,Integer currentPage,HttpServletRequest request,String planFlow,
                                     String speName,String enrollStartTime,String enrollEndTime) {
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("planFlow",planFlow);
        paramMap.put("speName",speName);
        paramMap.put("enrollStartTime",enrollStartTime);
        paramMap.put("enrollEndTime",enrollEndTime);
        paramMap.put("nowData", com.pinde.core.util.DateUtil.getCurrDateTime2());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, Object>> list = phyAssExtMapper.searchPlanScoreList(paramMap);
        model.addAttribute("list",list);
        model.addAttribute("nowDate", com.pinde.core.util.DateUtil.getCurrDateTime2());
        return "jsres/phyAss/planScoreList";
    }

    @RequestMapping(value = "/planApperMain")
    public String planApperMain(Model model,String planFlow,String type,String userStatus) {
        model.addAttribute("planFlow", planFlow);
        model.addAttribute("type", type);
        model.addAttribute("userStatus", userStatus);
        SysOrg sysorg = new SysOrg();
        sysorg.setOrgProvId("320000");
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs", orgs);
        return "jsres/phyAss/planApperMain";
    }

    @RequestMapping(value = "/planApperList")
    public String planApperList(Model model,String planFlow,String type,String userStatus, String speId,String orgFlow,
                                String doctorName,Integer currentPage,HttpServletRequest request) {
        model.addAttribute("planFlow", planFlow);
        model.addAttribute("type", type);
        model.addAttribute("userStatus", userStatus);
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("planFlow",planFlow);
        paramMap.put("type",type);
        paramMap.put("userStatus",userStatus);
        paramMap.put("speId",speId);
        paramMap.put("orgFlow",orgFlow);
        paramMap.put("doctorName",doctorName);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, Object>> list = phyAssExtMapper.planApperList(paramMap);
        model.addAttribute("list",list);
        ResTeachQualifiedPlan plan = planMapper.selectByPrimaryKey(planFlow);
        model.addAttribute("plan", plan);
        int apperNum = phyAssBiz.countPlanApperNum(planFlow);
        model.addAttribute("apperNum", apperNum);
        return "jsres/phyAss/planApperList";
    }


    @RequestMapping(value = "/expertPlanApperList")
    public void expertPlanApperList(HttpServletResponse response,String planFlow,String type,String userStatus, String speId,String orgFlow,
                                    String doctorName) throws Exception {

        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("planFlow",planFlow);
        paramMap.put("type",type);
        paramMap.put("userStatus",userStatus);
        paramMap.put("speId",speId);
        paramMap.put("orgFlow",orgFlow);
        paramMap.put("doctorName",doctorName);
        List<Map<String, Object>> list = phyAssExtMapper.planApperList(paramMap);
        String[] titles = new String[]{
                "doctorCode:登录名",
                "doctorName:性别",
                "sexName:姓名",
                "orgName:基地",
                "speName:培训专业",
                "deptName:科室",
                "doctorRoleName:角色",
                "userPhone:手机号",
                "idNo:身份证号",
                "userEmail:电子邮箱"
        };
        String fileName = "人员信息列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream(), new String[]{"0"});
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    @RequestMapping(value = "/operateUser")
    @ResponseBody
    public String operateUser(String recordFlowsStr,String type) {
        if (StringUtil.isNotBlank(recordFlowsStr)){
            String[] recordFlows = recordFlowsStr.split(",");
            List<String> recordFlowList = Arrays.asList(recordFlows);
            int count = phyAssBiz.operateUser(recordFlowList,type);
            if (count > 0){
                return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
    }


    @RequestMapping("/planSendCertidicateMain")
    public String planSendCertidicateMain(String tabTag,Model model) {
        model.addAttribute("tabTag",tabTag);
        return "jsres/phyAss/planSendCertidicateMain";
    }


    //医师协会-师资  证书生成查询页
    @RequestMapping("/phyAssCertificate")
    public String phyAssCertificate(String tabTag,Model model) {
        if (StringUtil.isNotBlank(tabTag) && !tabTag.equals("sealManage")){
            List<Map<String, Object>> list = phyAssExtMapper.searchAllByTime("");
            model.addAttribute("contentList",list);
            SysOrg sysorg = new SysOrg();
            sysorg.setOrgProvId("320000");
            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
            model.addAttribute("orgs", orgs);
        }
        return "jsres/phyAss/"+tabTag;
    }

    //医师协会-师资  证书生成 列表
    @RequestMapping("/phyAssCertificateList")
    public String phyAssCertificateList(Model model,String tabTag,String planFlow,String orgFlow,String speId,String doctorName,String sendCertificateId,
                                        String gradeId,String gainCertificateId,Integer currentPage,HttpServletRequest request) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("planFlow",planFlow);
        paramMap.put("orgFlow",orgFlow);
        paramMap.put("speId",speId);
        paramMap.put("doctorName",doctorName);
        if (StringUtil.isNotBlank(gradeId)){
            paramMap.put("gradeId",gradeId.toString());
        }
        if (StringUtil.isNotBlank(tabTag) && tabTag.equals("certificateSend")){
            gainCertificateId = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        paramMap.put("gainCertificateId",gainCertificateId);
        paramMap.put("sendCertificateId",sendCertificateId);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, Object>> doctorList = phyAssExtMapper.phyAssCertificateList(paramMap);
        model.addAttribute("doctorList", doctorList);
        model.addAttribute("tabTag", tabTag);
        List<SysDict> dictList = dictBiz.searchDictListByDictTypeId("CertificateTermValidity");
        if (null !=dictList && dictList.size()>0){
            model.addAttribute("dayNum", dictList.get(0).getDictId());
        }
        if (StringUtil.isNotBlank(tabTag) && tabTag.equals("certificateSend")){
            return "jsres/phyAss/certificateSendList";
        }
        return "jsres/phyAss/phyAssCertificateList";
    }


    //生成证书-选择师资等级
    @RequestMapping("/chooseGrade")
    public String chooseGrade(Model model,String recordFlow,String type) {
        model.addAttribute("recordFlow", recordFlow);
        model.addAttribute("type", type);
        return "jsres/phyAss/chooseGrade";
    }


    @RequestMapping(value = "/showGrade")
    public String showGrade(String recordFlow,Model model,String type) {
        ResTeachPlanDoctor doctor = planDoctorMapper.selectByPrimaryKey(recordFlow);
        if (StringUtil.isNotBlank(doctor.getSendCertificateTime())){
            doctor.setSendCertificateTime(StringUtil.convertYearNum(doctor.getSendCertificateTime()));
        }
        model.addAttribute("doctor",doctor);
        ResTeachQualifiedPlan plan = planMapper.selectByPrimaryKey(doctor.getPlanFlow());
        plan.setPlanStartTime(StringUtil.convertYearNum(plan.getPlanStartTime()));
        model.addAttribute("plan",plan);
        model.addAttribute("conLen",plan.getPlanContent().length());
        model.addAttribute("nowDate", com.pinde.core.util.DateUtil.getCurrDate());
        String num = phyAssExtMapper.gainPlanCertificateNo(doctor.getPlanFlow());
        String phyNo = com.pinde.core.util.DateUtil.getYear() + "32" + doctor.getSpeId() + num;
        model.addAttribute("phyNo",phyNo);
        model.addAttribute("type",type);
        model.addAttribute("nowDate", StringUtil.convertYearNum(com.pinde.core.util.DateUtil.getCurrDate()));
        String url = InitConfig.getSysCfg("upload_base_url") + "/";
        PubFile file = fileBiz.readProductFile("phyAss","phyAssSeal");
        if(null != file){
            model.addAttribute("filePath",url+file.getFilePath());
        }
        List<PubFile> filesList = pubFileBiz.findFileByTypeFlow("phyAssUser", doctor.getDoctorFlow());
        if ( null != filesList && filesList.size()>0){
            String userFilePath = filesList.get(0).getFilePath();
            String substring = userFilePath.substring(1, userFilePath.length());
            String replace = substring.replace("\\", "/");
            model.addAttribute("userFilePath",url+replace);
        }
        return "jsres/phyAss/infoNew";
    }



    @RequestMapping(value = "/downCertificate")
    public void downCertificate( HttpServletRequest request, HttpServletResponse response,
                                 String recruitFlow, Model model, String img,MultipartFile myfile, String isDown, String fileName) throws Exception {

        String baseDir= InitConfig.getSysCfg("upload_base_dir");
        //定义上传路径
        String dateString = com.pinde.core.util.DateUtil.getCurrDate2();
        String newDir = baseDir+ File.separator + "jsresPhyAssCertificateImg";
        File fileDir = new File(newDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String originalFilename =recruitFlow+ ".png";
        String filePath=fileDir+  File.separator +originalFilename;

        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isDown)) {
            byte[] data = null;
            long dataLength = 0;
            /*文件是否存在*/
            File downLoadFile = new File(filePath);
            /*文件是否存在*/
            if (downLoadFile.exists()) {
                InputStream fis = new BufferedInputStream(new FileInputStream(downLoadFile));
                data = new byte[fis.available()];
                dataLength = downLoadFile.length();
                fis.read(data);
                fis.close();
            }
            fileName = new String(fileName.getBytes("gbk"),"ISO8859-1" );
            try {
                response.reset();
                response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
                response.addHeader("Content-Length", "" + dataLength);
                response.setContentType("application/octet-stream;charset=UTF-8");
                OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                if (data != null) {
                    outputStream.write(data);
                }
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {

            }

        }else{
            File newFile = new File(fileDir, originalFilename);
            myfile.transferTo(newFile);
        }
    }




    @RequestMapping(value = "/createPhyAssGrade")
    @ResponseBody
    public String createPhyAssGrade(String recordFlows,String gradeId,String gradeName) {
        int count=0;
        if (StringUtil.isNotBlank(recordFlows)){
            List<String> list = Arrays.asList(recordFlows.split(","));
            if (null !=list && list.size()>0){
                for (String s : list) {
                    ResTeachPlanDoctor doctor = planDoctorMapper.selectByPrimaryKey(s);
                    String num = phyAssExtMapper.gainPlanCertificateNo(doctor.getPlanFlow());
                    String phyNo = com.pinde.core.util.DateUtil.getYear() + "32" + doctor.getSpeId() + num;
                    doctor.setCertificateNo(phyNo);
                    doctor.setGainCertificateId(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    doctor.setGainCertificateName("已生成");
                    doctor.setGradeId(gradeId);
                    doctor.setGradeName(gradeName);
                    doctor.setCertificateStartTime(com.pinde.core.util.DateUtil.getCurrDate());
                    planDoctorMapper.updateByPrimaryKey(doctor);
                    count++;
                }
            }
        }
        if (count>0){
            return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
    }


    @RequestMapping(value = "/expertCertidicate")
    public void expertCertidicate(HttpServletResponse response,String planFlow,String orgFlow,String speId,String doctorName,
                                  String gradeId,String gradeStatus,String gainCertificateId) throws Exception {

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("planFlow",planFlow);
        paramMap.put("orgFlow",orgFlow);
        paramMap.put("speId",speId);
        paramMap.put("doctorName",doctorName);
        if (StringUtil.isNotBlank(gradeId)){
            paramMap.put("gradeId",gradeId.toString());
        }
        paramMap.put("gradeStatus",gradeStatus);
        paramMap.put("gainCertificateId",gainCertificateId);
        List<Map<String, Object>> list = phyAssExtMapper.phyAssCertificateList(paramMap);
        String[] titles = new String[]{
                "doctorName:姓名",
                "sexName:性别",
                "planContent:培训计划",
                "orgName:基地",
                "speName:培训专业",
                "deptName:所属科室",
                "gradeName:师资等级",
                "certificateStartTime:证书生成日期",
                "gradeStatusName:状态"
        };
        String fileName = "人员信息列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream(), new String[]{"0"});
        response.setContentType("application/octet-stream;charset=UTF-8");
    }


    @RequestMapping(value = "/expertCertidicateSend")
    public void expertCertidicateSend(HttpServletResponse response,String planFlow,String orgFlow,String speId,String doctorName,
                                  String gradeId,String gradeStatus,String sendCertificateId) throws Exception {

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("planFlow",planFlow);
        paramMap.put("orgFlow",orgFlow);
        paramMap.put("speId",speId);
        paramMap.put("doctorName",doctorName);
        if (StringUtil.isNotBlank(gradeId)){
            paramMap.put("gradeId",gradeId.toString());
        }
        paramMap.put("gainCertificateId", com.pinde.core.common.GlobalConstant.FLAG_Y);
        paramMap.put("gradeStatus",gradeStatus);
        paramMap.put("sendCertificateId",sendCertificateId);
        List<Map<String, Object>> list = phyAssExtMapper.phyAssCertificateList(paramMap);
        if (null != list && list.size()>0){
            List<SysDict> dictList = dictBiz.searchDictListByDictTypeId("CertificateTermValidity");
            int dayNum = Integer.parseInt(dictList.get(0).getDictId());
            for (Map<String, Object> map : list) {
                if (StringUtil.isNotBlank((String)map.get("sendcertificateTime"))){
                    String sendcertificateTime = (String)map.get("sendcertificateTime");
                    Long date = com.pinde.core.util.DateUtil.appointDaysBetweenTowDate(sendcertificateTime, dayNum);
                    map.put("endcertificateTime",date.toString());
                }
            }
        }
        String[] titles = new String[]{
                "doctorName:姓名",
                "sexName:性别",
                "planContent:培训计划",
                "orgName:基地",
                "speName:培训专业",
                "deptName:所属科室",
                "gradeName:师资等级",
                "sendcertificateTime:证书发放日期",
                "endcertificateTime:有效期倒计时（天）",
                "sendcertificateName:状态"
        };
        String fileName = "人员信息列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream(), new String[]{"0"});
        response.setContentType("application/octet-stream;charset=UTF-8");
    }


    @RequestMapping(value = "/sendCertificate")
    @ResponseBody
    public String sendCertificate(String recordFlows) {
        int count=0;
        if (StringUtil.isNotBlank(recordFlows)){
            List<String> list = Arrays.asList(recordFlows.split(","));
            if (null !=list && list.size()>0){
                for (String s : list) {
                    ResTeachPlanDoctor doctor = planDoctorMapper.selectByPrimaryKey(s);
                    doctor.setSendCertificateId(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    doctor.setSendCertificateName("已发放");
                    doctor.setSendCertificateTime(com.pinde.core.util.DateUtil.getCurrDate());
                    planDoctorMapper.updateByPrimaryKey(doctor);
                    count++;
                }
            }
        }
        if (count>0){
            return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
    }

    @RequestMapping(value="/sealManage")
    public String sealManage(Model model){
        String userFlow = "phyAss";
        PubFile file = fileBiz.readProductFile(userFlow,"phyAssSeal");
        model.addAttribute("file",file);
        model.addAttribute("userFlow",userFlow);
        return "jsres/phyAss/phyAssSeal";
    }

    @RequestMapping(value={"/uploadSealFile"})
    @ResponseBody
    public String uploadApplicationFile(MultipartFile file, String productFlow) throws Exception{
        if(file!=null && !file.isEmpty()){
            String checkResult = phyAssBiz.checkImg(file);
            String resultPath = "";
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(checkResult)) {
                return checkResult;
            }else{
                resultPath = phyAssBiz.saveFileToDirs("", file, "jsresSeal/phyAssSeal");
                List<PubFile> files = fileBiz.findFileByTypeFlow("phyAssSeal",productFlow);
                PubFile pubFile = null;
                if(files != null && files.size() > 0){
                    pubFile = files.get(0);
                }else {
                    pubFile = new PubFile();
                }
                pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                pubFile.setProductFlow(productFlow);
                pubFile.setFilePath(resultPath);
                pubFile.setFileName(file.getOriginalFilename());
                pubFile.setProductType("phyAssSeal");
                fileBiz.editFile(pubFile);
                return resultPath;
            }
        }
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
    }


    @RequestMapping(value = "/phyAssDoctorListMain")
    public String phyAssDoctorListMain(Model model) {
        List<Map<String, Object>> list = phyAssExtMapper.searchAllByTime("");
        model.addAttribute("contentList",list);
        return "jsres/phyAss/phyAssDoctorListMain";
    }


    @RequestMapping("/phyAssDoctorList")
    public String phyAssDoctorList(Model model,String planFlow,String speId,String enrollStartTime,String enrollEndTime,
                                   Integer currentPage,HttpServletRequest request) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("planFlow",planFlow);
        paramMap.put("speId",speId);
        paramMap.put("gainCertificateId", com.pinde.core.common.GlobalConstant.FLAG_Y);
        paramMap.put("sendCertificateId", com.pinde.core.common.GlobalConstant.FLAG_Y);
        paramMap.put("enrollStartTime",enrollStartTime);
        paramMap.put("enrollEndTime",enrollEndTime);

        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, Object>> doctorList = phyAssExtMapper.phyAssCertificateList(paramMap);
        model.addAttribute("doctorList", doctorList);

        List<SysDict> dictList = dictBiz.searchDictListByDictTypeId("CertificateTermValidity");
        if (null !=dictList && dictList.size()>0){
            model.addAttribute("dayNum", dictList.get(0).getDictId());
        }

        return "jsres/phyAss/phyAssDoctorList";
    }

    @RequestMapping(value = "/planUserOrgMain")
    public String planUserOrgMain(Model model,String roleFlag) {
        List<Map<String, Object>> list = phyAssExtMapper.searchAllByTime("");
        List<SysDept> depts = deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("depts",depts);
        model.addAttribute("contentList",list);
        model.addAttribute("roleFlag",roleFlag);
        return "jsres/phyAss/planUserOrgMain";
    }

    @RequestMapping("/planUserOrgList")
    public String planUserOrgList(Model model,String planFlow,String speId,String deptFlow,String gradeId,String doctorName,
                                   String certificateNo,String startTime,String endTime,
                                  Integer currentPage,HttpServletRequest request) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());

        paramMap.put("planFlow",planFlow);
        paramMap.put("speId",speId);
        paramMap.put("deptFlow",deptFlow);
        paramMap.put("gradeId",gradeId);
        paramMap.put("doctorName",doctorName);
        paramMap.put("certificateNo",certificateNo);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        List<SysDict> dictList = dictBiz.searchDictListByDictTypeId("CertificateTermValidity");
        if (null !=dictList && dictList.size()>0){
            paramMap.put("dayNum",dictList.get(0).getDictId());
            model.addAttribute("dayNum", dictList.get(0).getDictId());
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, Object>> doctorList = phyAssExtMapper.planUserOrgList(paramMap);
        model.addAttribute("doctorList", doctorList);
        return "jsres/phyAss/planUserOrgList";
    }



    @RequestMapping(value = "/expertphyAssDoctorList")
    public void expertphyAssDoctorList(String planFlow,String speId,String enrollStartTime,String enrollEndTime,
                                       HttpServletResponse response) throws Exception {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("planFlow",planFlow);
        paramMap.put("speId",speId);
        paramMap.put("gainCertificateId", com.pinde.core.common.GlobalConstant.FLAG_Y);
        paramMap.put("sendCertificateId", com.pinde.core.common.GlobalConstant.FLAG_Y);
        paramMap.put("enrollStartTime",enrollStartTime);
        paramMap.put("enrollEndTime",enrollEndTime);
        List<Map<String, Object>> list = phyAssExtMapper.phyAssCertificateList(paramMap);
        if (null != list && list.size()>0){
            List<SysDict> dictList = dictBiz.searchDictListByDictTypeId("CertificateTermValidity");
            int dayNum = Integer.parseInt(dictList.get(0).getDictId());
            for (Map<String, Object> map : list) {
                String sendcertificateTime = map.get("sendcertificateTime").toString();
                String endcertificateTime = map.get("endcertificateTime").toString();
                String day = com.pinde.core.util.DateUtil.appointDaysBetweenTowDate(sendcertificateTime, dayNum).toString();
                sendcertificateTime=sendcertificateTime+"~"+endcertificateTime;
                map.put("sendcertificateTime",sendcertificateTime);
                map.put("endcertificateTime",day);
            }
        }
        String[] titles = new String[]{
                "doctorName:姓名",
                "sexName:性别",
                "planContent:培训计划",
                "orgName:基地名称",
                "deptName:科室",
                "speName:培训专业",
                "gradeName:师资等级",
                "gradeName:证书编号",
                "sendcertificateTime:证书有效期",
                "endcertificateTime:有效期倒计时（天）"
        };
        String fileName = "人员信息列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream(), new String[]{"0"});
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    @RequestMapping(value = "/expertPlanUserOrgList")
    public void expertPlanUserOrgList(String planFlow,String speId,String deptFlow,String gradeId,String doctorName,
                                      String certificateNo,String startTime,String endTime,
                                       HttpServletResponse response) throws Exception {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());

        paramMap.put("planFlow",planFlow);
        paramMap.put("speId",speId);
        paramMap.put("deptFlow",deptFlow);
        paramMap.put("gradeId",gradeId);
        paramMap.put("doctorName",doctorName);
        paramMap.put("certificateNo",certificateNo);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        List<SysDict> dictList = dictBiz.searchDictListByDictTypeId("CertificateTermValidity");
        int dayNum = 0;
        if (null !=dictList && dictList.size()>0){
            paramMap.put("dayNum",dictList.get(0).getDictId());
            dayNum=Integer.parseInt(dictList.get(0).getDictId());
        }
        List<Map<String, Object>> list = phyAssExtMapper.planUserOrgList(paramMap);
        if (null != list && list.size()>0){
            for (Map<String, Object> map : list) {
                String sendcertificateTime = map.get("sendcertificateTime").toString();
                String endcertificateTime = map.get("endcertificateTime").toString();
                String day = com.pinde.core.util.DateUtil.appointDaysBetweenTowDate(sendcertificateTime, dayNum).toString();
                sendcertificateTime=sendcertificateTime+"~"+endcertificateTime;
                map.put("sendcertificateTime",sendcertificateTime);
                map.put("endcertificateTime",day);
            }
        }
        String[] titles = new String[]{
                "doctorName:姓名",
                "sexName:性别",
                "planContent:培训计划",
                "speName:培训专业",
                "deptName:科室",
                "gradeName:师资等级",
                "gradeName:证书编号",
                "sendcertificateTime:证书有效期",
                "endcertificateTime:有效期倒计时（天）"
        };
        String fileName = "人员信息列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream(), new String[]{"0"});
        response.setContentType("application/octet-stream;charset=UTF-8");
    }



    @RequestMapping(value = "/planUserMsgMain")
    public String planUserMsgMain(Model model) {
        List<Map<String, Object>> list = phyAssExtMapper.searchAllIntroduce();
        model.addAttribute("contentList",list);
        return "jsres/phyAss/planUserMsgMain";
    }

    @RequestMapping("/planUserMsgList")
    public String planUserMsgList(Model model,String planFlow,String certificateNo,String startTime,String endTime,
                                   Integer currentPage,HttpServletRequest request) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("planFlow",planFlow);
        paramMap.put("certificateNo",certificateNo);
        paramMap.put("gainCertificateId", com.pinde.core.common.GlobalConstant.FLAG_Y);
        paramMap.put("sendCertificateId", com.pinde.core.common.GlobalConstant.FLAG_Y);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        List<SysDict> dictList = dictBiz.searchDictListByDictTypeId("CertificateTermValidity");
        if (null !=dictList && dictList.size()>0){
            model.addAttribute("dayNum", dictList.get(0).getDictId());
            paramMap.put("dayNum",dictList.get(0).getDictId());
        }
        paramMap.put("userFlow",GlobalContext.getCurrentUser().getUserFlow());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, Object>> doctorList = phyAssExtMapper.phyAssDoctorList(paramMap);
        model.addAttribute("doctorList", doctorList);
        return "jsres/phyAss/planUserMsgList";
    }



    @RequestMapping(value = "/expertUserMsg")
    public void expertUserMsg(String planFlow,String certificateNo,String startTime,String endTime,
                                       HttpServletResponse response) throws Exception {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("planFlow",planFlow);
        paramMap.put("certificateNo",certificateNo);
        paramMap.put("gainCertificateId", com.pinde.core.common.GlobalConstant.FLAG_Y);
        paramMap.put("sendCertificateId", com.pinde.core.common.GlobalConstant.FLAG_Y);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        List<SysDict> dictList = dictBiz.searchDictListByDictTypeId("CertificateTermValidity");
        Integer dayNum=0;
        if (null !=dictList && dictList.size()>0){
            paramMap.put("dayNum",dictList.get(0).getDictId());
            dayNum= Integer.parseInt( dictList.get(0).getDictId());
        }
        paramMap.put("userFlow",GlobalContext.getCurrentUser().getUserFlow());

        List<Map<String, Object>> list = phyAssExtMapper.phyAssDoctorList(paramMap);
        if (null != list && list.size()>0){
            for (Map<String, Object> map : list) {
                String planStartTime = map.get("planStartTime").toString();
                String planEndTime = map.get("planEndTime").toString();
                String sendcertificateTime = map.get("sendCertificateTime").toString();
                String endcertificateTime = map.get("endCertificateTime").toString();

                String day = com.pinde.core.util.DateUtil.appointDaysBetweenTowDate(sendcertificateTime, dayNum).toString();
                planStartTime=planStartTime+"~"+planEndTime;
                sendcertificateTime=sendcertificateTime+"~"+endcertificateTime;
                map.put("planStartTime",planStartTime);
                map.put("sendcertificateTime",sendcertificateTime);
                map.put("endcertificateTime",day);
            }
        }
        String[] titles = new String[]{
                "planContent:培训计划",
                "planStartTime:培训日期",
                "certificateNo:证书编号",
                "sendcertificateTime:证书有效期",
                "endcertificateTime:有效期倒计时（天）"
        };
        String fileName = "人员信息列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream(), new String[]{"0"});
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

}
