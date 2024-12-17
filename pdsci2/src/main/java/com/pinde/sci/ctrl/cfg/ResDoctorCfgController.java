package com.pinde.sci.ctrl.cfg;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResPowerCfgBiz;
import com.pinde.sci.biz.sch.ISchManualBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.ResPowerCfgMapper;
import com.pinde.sci.model.mo.ResPowerCfg;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/res/doctorCfg")
public class ResDoctorCfgController extends GeneralController {

    private static final Logger logger = LoggerFactory.getLogger(ResDoctorCfgController.class);

    @Autowired
    private ISchManualBiz schManualBiz;

    @Autowired
    private IResPowerCfgBiz resPowerCfgBiz;


    @RequestMapping(value = {"/main" }, method = RequestMethod.GET)
    public String main () throws Exception{
        return "res/cfg/doctor/main";
    }

    /**
     * 查询
     * @param model
     * @param currentPage
     * @param request
     * @param orgFlow
     * @param sessionNumber
     * @param workOrgId
     * @param userName
     * @param idNo
     * @param datas
     * @param doctorCategoryId
     * @param trainingSpeId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/userList" })
    public String userList(Model model, Integer currentPage, HttpServletRequest request, String orgFlow,
                           String sessionNumber, String workOrgId, String userName, String idNo, String userCode, String trainingYears,
                           String[] datas, String doctorCategoryId, String trainingSpeId, String ifOpen, String[] powerTypeId) throws Exception {
        List<String> docTypeList=new ArrayList<>();
        if(datas!=null&&datas.length>0)
        {
            docTypeList.addAll(Arrays.asList(datas));
        }
        Map<String,Object> params=new HashMap<>();

//        //住院医师状态
//        List<SysDict> doctorStatusDicts = com.pinde.core.common.enums.DictTypeEnum.DoctorStatus.getSysDictList();
//        List<String> doctorStatusList = new ArrayList<String>();
//        for (SysDict dict:doctorStatusDicts) {
//            doctorStatusList.add(dict.getDictId());
//        }
//        params.put("doctorStatusList",doctorStatusList);

        params.put("orgFlow",orgFlow);//培训基地
        params.put("sessionNumber",sessionNumber);//年级
        params.put("userName",userName);//姓名
        params.put("idNo",idNo);//证件号码
        params.put("doctorCategoryId",doctorCategoryId);//培训类别
        params.put("trainingSpeId",trainingSpeId);//培训专业
        params.put("workOrgId",workOrgId);//派送学校
        params.put("docTypeList",docTypeList);//人员类型
        params.put("userCode",userCode);//登录名
        params.put("trainingYears",trainingYears);//培养年限
        if(currentPage==null){
            currentPage=1;
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list = new ArrayList<>();
        if(StringUtil.isNotBlank(ifOpen)){
            params.put("ifOpen",ifOpen);
            for(int i=0;i<powerTypeId.length;i++){
                params.put(powerTypeId[i], com.pinde.core.common.GlobalConstant.FLAG_Y);
            }
            list=schManualBiz.userListByPower(params);
        }else {
            list=schManualBiz.userList(params);
        }

        Map<String,Object> timeMap = new HashMap<>();
        Object userFlow;
        for(Map<String,Object>  infoMap:list){
            userFlow = infoMap.get("userFlow");
            String key = "res_doctor_web_" + userFlow;
            ResPowerCfg cfg = resPowerCfgBiz.read(key);
            if(cfg!=null){
                timeMap.put(userFlow.toString(),cfg);
            }
        }
        model.addAttribute("timeMap",timeMap);
        model.addAttribute("list",list);
        return "res/cfg/doctor/userList";
    }

    /**
     * 保存权限
     * @param userFlows
     * @param recordStatus
     * @param cfg
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(String[] userFlows, String recordStatus,String cfg) {
        List<ResPowerCfg> cfgList = new ArrayList<ResPowerCfg>();
        for (String userFlow : userFlows) {
            String cfgCode = cfg + userFlow;
            ResPowerCfg resPowerCfg = new ResPowerCfg();
            resPowerCfg.setCfgCode(cfgCode);
            resPowerCfg.setCfgValue(recordStatus);
            if("res_doctor_web_".equals(cfg)){
                resPowerCfg.setCfgDesc("是否开放学员web登录权限");
            }else if("res_doctor_app_login_".equals(cfg)){
                resPowerCfg.setCfgDesc("是否开放学员app登录权限");
            }else if("res_doctor_exam_".equals(cfg)){
                resPowerCfg.setCfgDesc("是否开放学员出科考试权限");
            }else if("res_doctor_manual_".equals(cfg)){
                resPowerCfg.setCfgDesc("是否开放学员手册");
            }
            resPowerCfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            cfgList.add(resPowerCfg);
        }
        resPowerCfgBiz.saveList(cfgList);
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * 显示权限期间
     * @param cfgCode
     * @param model
     * @return
     */
    @RequestMapping(value="/showModifyDate",method=RequestMethod.GET)
    public String showModifySchDate(String cfgCode,Model model){
//        ResPowerCfg powerCfg = new ResPowerCfg();
        ResPowerCfg powerCfgs = resPowerCfgBiz.read(cfgCode);
//        if(powerCfgs!=null){
            model.addAttribute("powerCfg", powerCfgs);
//        }else{
//            powerCfg.setCfgCode(cfgCode);
//            model.addAttribute("powerCfg", powerCfg);
//        }
        return "res/cfg/doctor/modifyDate";
    }

    /**
     * 保存修改时间
     * @param powerCfg
     * @return
     */
    @RequestMapping(value="/saveDate",method=RequestMethod.POST)
    @ResponseBody
    public String saveDate(ResPowerCfg powerCfg){

        int result = resPowerCfgBiz.updateDate(powerCfg);
        if (result == com.pinde.core.common.GlobalConstant.ONE_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }

    /**
     * 单个保存
     * @param request
     * @return
     */
    @RequestMapping(value="/saveOne",method= RequestMethod.POST)
    @ResponseBody
    public String saveOne(HttpServletRequest request){
        String [] cfgCodes = request.getParameterValues("cfgCode");
        if(cfgCodes!=null){
            List<ResPowerCfg> cfgList = new ArrayList<ResPowerCfg>();
            for(String cfgCode : cfgCodes){
                String cfgValue = request.getParameter(cfgCode);
                String cfgDesc = request.getParameter(cfgCode+"_desc");
                ResPowerCfg cfg = new ResPowerCfg();
                cfg.setCfgCode(cfgCode);
                cfg.setCfgValue(cfgValue);
                cfg.setCfgDesc(cfgDesc);
                cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                cfgList.add(cfg);
            }
            int result = resPowerCfgBiz.saveList(cfgList);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }
    /**
     * 人员权限期限导入
     */
    @RequestMapping(value = "/showImport")
    public String showImport() {
        return "res/cfg/doctor/importPower";
    }

    @RequestMapping(value = "/importPower")
    public
    @ResponseBody
    String importPower(MultipartFile file,String keyPassword) {
        //root密码
        if(!InitPasswordUtil.isRootPass(keyPassword)){
            return "root密码不正确";
        }
        if (file.getSize() > 0) {
            try {
                ExcelUtile result = resPowerCfgBiz.importPowerFromExcel(file);
                if (null != result) {
                    String code = (String) result.get("code");
                    int count = (Integer) result.get("count");
                    String msg = (String) result.get("msg");
                    if ("1".equals(code)) {
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
                    } else {
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
                        } else {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                        }
                    }
                } else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                }
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
            }
        }
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
    }

    @RequestMapping(value = {"/exportPowers"})
    public void schRotationExport(String orgFlow, String sessionNumber, String workOrgId, String userName,
                                  String idNo, String[] datas, String doctorCategoryId, HttpServletResponse response,
                                  HttpServletRequest request, String trainingSpeId, String ifOpen, String[] powerTypeId) throws Exception {
        List<String> docTypeList = new ArrayList<>();
        if (datas != null && datas.length > 0) {
            docTypeList.addAll(Arrays.asList(datas));
        }
        Map<String, Object> params = new HashMap<>();
        params.put("orgFlow", orgFlow);//培训基地
        params.put("sessionNumber", sessionNumber);//年级
        params.put("userName", userName);//姓名
        params.put("idNo", idNo);//证件号码
        params.put("doctorCategoryId", doctorCategoryId);//培训类别
        params.put("trainingSpeId", trainingSpeId);//培训专业
        params.put("workOrgId", workOrgId);//派送学校
        params.put("docTypeList", docTypeList);//人员类型
        List<Map<String,Object>> list = new ArrayList<>();
        if(StringUtil.isNotBlank(ifOpen)){
            params.put("ifOpen",ifOpen);
            for(int i=0;i<powerTypeId.length;i++){
                params.put(powerTypeId[i], com.pinde.core.common.GlobalConstant.FLAG_Y);
            }
            list=schManualBiz.userListByPower(params);
        }else {
            list=schManualBiz.userList(params);
        }
        String[] headLines = null;
        headLines = new String[]{
                "学员权限配置一览表",
        };
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        styleCenter.setWrapText(true);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);
        //列宽自适应
//		sheet.setDefaultColumnWidth((short)50);
        HSSFRow rowDep = sheet.createRow(0);//第一行
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));//合并单元格
        rowDep.setHeightInPoints(20);
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue("学员权限配置一览表");

        HSSFRow rowTwo = sheet.createRow(1);//第二行
        String[] titles = new String[]{
                "序号",
                "培训基地",
                "年级",
                "姓名",
                "证件号码",
                "培训专业",
                "WEB登录权限",
                "APP登录权限",
                "权限期限(WEB/APP)",
                "出科考试权限",
                "培训手册"
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowTwo.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 2 * 256);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));//合并单元格
        //行数
        int rowNum = 2;
        //存放在excel中的行数据
        String[] resultList = null;
        ServletContext application = request.getServletContext();
        Map<String, String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
        //对当前师资信息List循环
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                Map<String, Object> userListMap = list.get(i);
                //权限
                String userFlow = userListMap.get("userFlow").toString();
                ResPowerCfgMapper resPowerCfgMapper = SpringUtil.getBean(ResPowerCfgMapper.class);
                //WEB登录权限
                String webPower = "";
                String webCfgCode = "res_doctor_web_" + userFlow;
                ResPowerCfg webPowerCfg = resPowerCfgMapper.selectByPrimaryKey(webCfgCode);
                if (webPowerCfg == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(webPowerCfg.getCfgValue())) {
                    webPower = "否";
                } else {
                    webPower = "是";
                }
                //APP登录权限
                String appPower = "";
                String appCfgCode = "res_doctor_app_login_" + userFlow;
                ResPowerCfg appPowerCfg = resPowerCfgMapper.selectByPrimaryKey(appCfgCode);
                if (appPowerCfg == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(appPowerCfg.getCfgValue())) {
                    appPower = "否";
                } else {
                    appPower = "是";
                }
                //权限期限(WEB/APP)
                String timePower = "";
                if (webPowerCfg == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(webPowerCfg.getCfgValue())) {
                    timePower = "";
                } else {
                    if(StringUtil.isNotBlank(webPowerCfg.getPowerStartTime()) && StringUtil.isNotBlank(webPowerCfg.getPowerStartTime())){
                        timePower = webPowerCfg.getPowerStartTime() + "至" + webPowerCfg.getPowerEndTime();
                    }
                }
                //出科考试权限
                String ckksPower = "";
                String ckksCfgCode = "res_doctor_ckks_" + userFlow;
                ResPowerCfg ckksPowerCfg = resPowerCfgMapper.selectByPrimaryKey(ckksCfgCode);
                if (ckksPowerCfg == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ckksPowerCfg.getCfgValue())) {
                    ckksPower = "否";
                } else {
                    ckksPower = "是";
                }
                //培训手册
                String pxscPower = "";
                String pxscCfgCode = "res_doctor_pxsc_" + userFlow;
                ResPowerCfg pxscPowerCfg = resPowerCfgMapper.selectByPrimaryKey(pxscCfgCode);
                if (pxscPowerCfg == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(pxscPowerCfg.getCfgValue())) {
                    pxscPower = "否";
                } else {
                    pxscPower = "是";
                }

                //根据医院科室的Flow取出轮转科室List
                resultList = new String[]{
                        i + 1 + "",
                        userListMap.get("orgName") == null ? "" : userListMap.get("orgName").toString(),
                        userListMap.get("sessionNumber") == null ? "" : userListMap.get("sessionNumber").toString(),
                        userListMap.get("userName") == null ? "" : userListMap.get("userName").toString(),
                        userListMap.get("idNo") == null ? "" : userListMap.get("idNo").toString(),
                        userListMap.get("trainingSpeName") == null ? "" : userListMap.get("trainingSpeName").toString(),
                        webPower,
                        appPower,
                        timePower,
                        ckksPower,
                        pxscPower
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
                }
            }
        }
        String fileName = "学员权限配置一览表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }
}
