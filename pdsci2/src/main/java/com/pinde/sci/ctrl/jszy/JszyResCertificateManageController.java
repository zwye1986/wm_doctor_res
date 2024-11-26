package com.pinde.sci.ctrl.jszy;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyResDoctorRecruitBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.enums.jszy.JszyResTrainYearEnum;
import com.pinde.sci.enums.jszy.JszyTrainCategoryEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.jszy.JszyResDoctorRecruitExt;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.mo.ResJointOrg;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysUser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pdkj on 2017/9/29.
 */
@Controller
@RequestMapping("/jszy/certificateManage")
public class JszyResCertificateManageController extends GeneralController {
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IJszyResDoctorRecruitBiz jszyResDoctorRecruitBiz;
    @Autowired
    private IUserBiz userBiz;

    @RequestMapping("/main")
    public String showMain(Model model, String roleFlag) {
        SysUser sysuser = GlobalContext.getCurrentUser();
        SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
        SysOrg sysorg = new SysOrg();
        sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        sysorg.setOrgProvId(org.getOrgProvId());
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        if (GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
            sysorg.setOrgCityId(org.getOrgCityId());
        }
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            List<SysOrg> orgs = new ArrayList<>();
            orgs.add(org);
            if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
                List<SysOrg> orgs2 = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
                if (null != orgs && orgs2.size() > 0) {
                    orgs.addAll(orgs2);
                }
            }
            model.addAttribute("orgs", orgs);
        } else {

            List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
            model.addAttribute("orgs", orgs);
        }
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("currOrg", org);
        model.addAttribute("user", GlobalContext.getCurrentUser());
        return "jszy/global/certificateManage/main";
    }

    @RequestMapping(value = "/list")
    public String list(Model model, Integer currentPage, String roleFlag, HttpServletRequest request,
                       String orgCityId, String orgFlow, String trainingTypeId, String orgLevel, String TCMAssiGeneral,
                       String trainingSpeId, String sessionNumber, String graduationYear, String idNo,
                       String userName, String graduationCertificateNo, String[] datas, String jointOrgFlag,
                       String workOrgName) {
        Map<String, Object> param = new HashMap<>();

        List<String> docTypeList = new ArrayList<String>();
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        }
        SysUser sysuser = GlobalContext.getCurrentUser();
        SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
        param.put("orgTypeId", OrgTypeEnum.Hospital.getId());
        param.put("orgProvId", org.getOrgProvId());

        List<String> orgFlowList = new ArrayList<String>();

        if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag))//基地
        {
            if (StringUtil.isNotBlank(orgFlow)) {
                orgFlowList.add(orgFlow);
                if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
            } else {
                orgFlowList.add(org.getOrgFlow());
                if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
            }
        } else {
            if (StringUtil.isNotBlank(orgFlow)) {
                orgFlowList.add(orgFlow);
                if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
            } else {
                if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    SysOrg searchOrg = new SysOrg();
                    searchOrg.setOrgProvId(org.getOrgProvId());
                    searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    if (StringUtil.isNotBlank(orgLevel)) {
                        searchOrg.setOrgLevelId(orgLevel);
                    }
                    List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
                    for (SysOrg g : exitOrgs) {
                        List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
                        if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                            for (ResJointOrg jointOrg : resJointOrgList) {
                                orgFlowList.add(jointOrg.getJointOrgFlow());
                            }
                        }
                        orgFlowList.add(g.getOrgFlow());
                    }
                }
            }
            if (!GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                param.put("orgLevel", orgLevel);
            }
            param.put("orgCityId", orgCityId);
        }
        List<String> trainTypeIdList = new ArrayList<String>();
        if (StringUtil.isBlank(trainingTypeId)) {
            for (JszyTrainCategoryEnum e : JszyTrainCategoryEnum.values()) {
                if(GlobalConstant.FLAG_Y.equals(TCMAssiGeneral)){
                    if(GlobalConstant.FLAG_N.equals(e.getIsView())){
                        trainTypeIdList.add(e.getId());
                    }
                }else {
                    if(GlobalConstant.FLAG_Y.equals(e.getIsView())){
                        trainTypeIdList.add(e.getId());
                    }
                }
            }
        } else {
            if(GlobalConstant.FLAG_Y.equals(TCMAssiGeneral)){
                trainTypeIdList.add(JszyTrainCategoryEnum.TCMAssiGeneral.getId());
            }else {
                trainTypeIdList.add(trainingTypeId);
            }
        }
        param.put("docTypeList", docTypeList);
        param.put("trainTypeIdList", trainTypeIdList);
        param.put("orgFlowList", orgFlowList);
        param.put("trainingSpeId", trainingSpeId);
        param.put("sessionNumber", sessionNumber);
        param.put("graduationYear", graduationYear);
        param.put("idNo", idNo);
        param.put("userName", userName);
        param.put("graduationCertificateNo", graduationCertificateNo);
        param.put("workOrgName", workOrgName);

        List<JszyResDoctorRecruitExt> doctorList = null;
        if (StringUtil.isNotBlank(workOrgName)) {
            List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
            if (sendSchools != null && sendSchools.size() > 0) {
                for (SysDict dict : sendSchools) {
                    if (workOrgName.equals(dict.getDictName())) {
                        param.put("workOrgId", dict.getDictId());
                    }
                }
            }
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        doctorList = jszyResDoctorRecruitBiz.searchDoctorCertificateList(param);
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("doctorList", doctorList);
        model.addAttribute("datas", datas);
        return "jszy/global/certificateManage/list";
    }

    /**
     * 导出excel
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/exportCertificateList")
    public void exportDoctor(Model model, String roleFlag, HttpServletRequest request, HttpServletResponse response,
                             String orgCityId, String orgFlow, String trainingTypeId, String orgLevel, String TCMAssiGeneral,
                             String trainingSpeId, String sessionNumber, String graduationYear, String idNo,
                             String userName, String graduationCertificateNo, String[] datas, String jointOrgFlag,
                             String workOrgName) throws Exception {
        Map<String, Object> param = new HashMap<>();

        List<String> docTypeList = new ArrayList<String>();
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        }
        SysUser sysuser = GlobalContext.getCurrentUser();
        SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
        param.put("orgTypeId", OrgTypeEnum.Hospital.getId());
        param.put("orgProvId", org.getOrgProvId());

        List<String> orgFlowList = new ArrayList<String>();
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag))//基地
        {
            if (StringUtil.isNotBlank(orgFlow)) {
                orgFlowList.add(orgFlow);
                if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
            } else {
                orgFlowList.add(org.getOrgFlow());
                if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
            }
        } else {
            if (StringUtil.isNotBlank(orgFlow)) {
                orgFlowList.add(orgFlow);
                if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
            } else {
                if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    SysOrg searchOrg = new SysOrg();
                    searchOrg.setOrgProvId(org.getOrgProvId());
                    searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    if (StringUtil.isNotBlank(orgLevel)) {
                        searchOrg.setOrgLevelId(orgLevel);
                    }
                    List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
                    for (SysOrg g : exitOrgs) {
                        List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
                        if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                            for (ResJointOrg jointOrg : resJointOrgList) {
                                orgFlowList.add(jointOrg.getJointOrgFlow());
                            }
                        }
                        orgFlowList.add(g.getOrgFlow());
                    }
                }
            }
            if (!GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                param.put("orgLevel", orgLevel);
            }
            param.put("orgCityId", orgCityId);
        }
        List<String> trainTypeIdList = new ArrayList<String>();
        if (StringUtil.isBlank(trainingTypeId)) {
            for (JszyTrainCategoryEnum e : JszyTrainCategoryEnum.values()) {
                if(GlobalConstant.FLAG_Y.equals(TCMAssiGeneral)){
                    if(GlobalConstant.FLAG_N.equals(e.getIsView())){
                        trainTypeIdList.add(e.getId());
                    }
                }else {
                    if(GlobalConstant.FLAG_Y.equals(e.getIsView())){
                        trainTypeIdList.add(e.getId());
                    }
                }
            }
        } else {
            if(GlobalConstant.FLAG_Y.equals(TCMAssiGeneral)){
                trainTypeIdList.add(JszyTrainCategoryEnum.TCMAssiGeneral.getId());
            }else {
                trainTypeIdList.add(trainingTypeId);
            }
        }
        param.put("docTypeList", docTypeList);
        param.put("trainTypeIdList", trainTypeIdList);
        param.put("orgFlowList", orgFlowList);
        param.put("trainingSpeId", trainingSpeId);
        param.put("sessionNumber", sessionNumber);
        param.put("graduationYear", graduationYear);
        param.put("idNo", idNo);
        param.put("userName", userName);
        param.put("graduationCertificateNo", graduationCertificateNo);
        param.put("workOrgName", workOrgName);

        List<JszyResDoctorRecruitExt> doctorList = null;
        if (StringUtil.isNotBlank(workOrgName)) {
            List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
            if (sendSchools != null && sendSchools.size() > 0) {
                for (SysDict dict : sendSchools) {
                    if (workOrgName.equals(dict.getDictName())) {
                        param.put("workOrgId", dict.getDictId());
                    }
                }
            }
        }
        doctorList = jszyResDoctorRecruitBiz.searchDoctorCertificateList(param);
        String[] head = new String[]{};
        String[] titles = new String[]{
                "graduationYear:结业年份",
                "sysUser.userName:姓名",
                "sysUser.idNo:证件号码",
                "orgName:培训基地",
                "catSpeName:培训类别",
                "speName:培训专业",
                "sessionNumber:年级",
                "graduationCertificateNo:证书编号",
        };
        String fileName = "学员证书信息.xls";
        fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        //ExcleUtile.exportSimpleExcleWithHeadlin(head, titles, doctorList, response.getOutputStream());
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
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        style.setFont(font);
        Font fontTwo = wb.createFont();
        fontTwo.setFontHeightInPoints((short) 12);

        CellStyle styleTwo = wb.createCellStyle();
        styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        styleTwo.setFont(fontTwo);
        //

        Map<Integer, Integer> columnWidth = new HashMap<>();
        List<String> paramIds = new ArrayList<String>();
        Row row = sheet.createRow(0);
        Cell cell = null;
        for (int i = 0; i < titles.length; i++) {
            String[] title = titles[i].split(":");
            cell = row.createCell(i);
            cell.setCellValue(title[1]);
            cell.setCellStyle(style);
            paramIds.add(title[0]);
            //宽度自适应
            int nl = title[1].toString().getBytes().length;
            if (columnWidth.containsKey(i)) {
                Integer ol = columnWidth.get(i);
                if (ol < nl)
                    columnWidth.put(i, nl);
            } else {
                columnWidth.put(i, nl);
            }
        }
        if (doctorList != null) {
            Cell rowCell = null;
            for (int i = 0; i < doctorList.size(); i++) {
                Object item = doctorList.get(i);
                row = sheet.createRow(1 + i);
                Object result = null;
                for (int j = 0; j < paramIds.size(); j++) {
                    String paramId = paramIds.get(j);
                    if (StringUtil.isBlank(paramId)) {//序号
                        result = i + 1;
                    } else {
                        result = getValueByAttrs(paramId, item);
                    }
                    rowCell = row.createCell(j);
                    rowCell.setCellStyle(styleTwo);
                    rowCell.setCellValue(result.toString());
                    //宽度自适应
                    int nl = result.toString().getBytes().length;
                    if (columnWidth.containsKey(j)) {
                        Integer ol = columnWidth.get(j);
                        if (ol < nl)
                            columnWidth.put(j, nl);
                    } else {
                        columnWidth.put(j, nl);
                    }
                }

            }
        }
        Set<Integer> keys = columnWidth.keySet();
        for (Integer key : keys) {
            int width = columnWidth.get(key);
            sheet.setColumnWidth(key, width * 2 * 256);
        }
        wb.write(os);
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    private static Object getValueByAttrs(String attrNames, Object obj) throws Exception {
        Object value = "";
        if (StringUtil.isNotBlank(attrNames)) {
            String proptyName = "";
            int pIndex = attrNames.indexOf(".");

            if (pIndex >= 0) {
                proptyName = attrNames.substring(0, pIndex);
            } else {
                proptyName = attrNames;
            }

            if (StringUtil.isNotBlank(proptyName) && obj != null) {
                Class clazz = obj.getClass();
                String firstStr = proptyName.substring(0, 1).toUpperCase();
                String secondStr = proptyName.substring(1);
                Method mt;
                Object result;
                if (proptyName.length() >= 4 && "get(".equals(proptyName.substring(0, 4))) {
                    int index = Integer.parseInt(proptyName.split("\\(")[1].split("\\)")[0]);
                    result = null;
                    if (((List) obj).size() > index) {
                        result = ((List) obj).get(index);
                    }
                } else if (obj instanceof Map) {
                    Map map = (Map) obj;
                    result = ((Map) obj).get(proptyName);
                } else {
                    mt = clazz.getMethod("get" + firstStr + secondStr);
                    result = mt.invoke(obj);
                }

                if (result != null) {
                    String stringClassName = String.class.getSimpleName();
                    String inegerClassName = Integer.class.getSimpleName();
                    String bigDecimalClassName = BigDecimal.class.getSimpleName();
                    String valueClassName = result.getClass().getSimpleName();
                    if (stringClassName.equals(valueClassName) || bigDecimalClassName.equals(valueClassName) || inegerClassName.equals(valueClassName)) {
                        value = result;
                    } else {
                        String surplusName = attrNames.substring(pIndex + 1);
                        value = getValueByAttrs(surplusName, result);
                    }
                }
            }
        }
        return value;
    }

    @RequestMapping(value="/importDoctorCertificateNoFromExcel2")
    @ResponseBody
    public String importDoctorCertificateNoFromExcel2(MultipartFile file){

        if(file.getSize() > 0){
            try{
                ExcelUtile result = (ExcelUtile) jszyResDoctorRecruitBiz.importDoctorCertificateNoFromExcel2(file);
                if(null!=result)
                {
                    String code= (String) result.get("code");
                    int count=(Integer) result.get("count");
                    String msg= (String) result.get("msg");
                    if("1".equals(code))
                    {
                        return GlobalConstant.UPLOAD_FAIL+msg;
                    }else{
                        if(GlobalConstant.ZERO_LINE != count){
                            return GlobalConstant.UPLOAD_SUCCESSED + "导入"+count+"条记录！";
                        }else{
                            return GlobalConstant.UPLOAD_FAIL;
                        }
                    }
                }else {
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    @RequestMapping(value = "/showCertificate")
    public String showCertificate(String recruitFlow,Model model) {
        ResDoctorRecruit recruit=jszyResDoctorRecruitBiz.readRecruit(recruitFlow);
        if(recruit!=null&&StringUtil.isNotBlank(recruit.getGraduationCertificateNo())) {
            SysUser sysUser = userBiz.findByFlow(recruit.getDoctorFlow());
            String completeNo = "";
            completeNo = getShowCountryOrProvince(recruit);
            List<ResJointOrg> jointOrgs = jointOrgBiz.searchResJointByJointOrgFlow(recruit.getOrgFlow());
            //是协同基地 显示国家基地
            if (!jointOrgs.isEmpty() && jointOrgs.size() > 0) {
                recruit.setOrgName(jointOrgs.get(0).getOrgName());
            }
            model.addAttribute("completeNo", completeNo);
            model.addAttribute("recruit", recruit);
            model.addAttribute("sysUser", sysUser);

            String endTime="";
            String startTime="";
            //开始时间
            String recruitDate=recruit.getRecruitDate();
            //培养年限
            String trianYear=recruit.getTrainYear();
            String graudationYear=recruit.getGraduationYear();
            if(StringUtil.isNotBlank(recruitDate)&&StringUtil.isNotBlank(graudationYear))
            {
                try {
                    int year=0;
                    year=Integer.valueOf(graudationYear)-Integer.valueOf(recruitDate.substring(0,4));
                    if(year!=0) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE, -1);
                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());
                    }
                } catch (Exception e) {
                    endTime="";
                }
            }
            //如果没有结业考核年份，按照届别与培养年限计算
            if(StringUtil.isNotBlank(recruitDate)&&StringUtil.isNotBlank(trianYear)&&StringUtil.isBlank(endTime))
            {
                int year=0;
                if(trianYear.equals(JszyResTrainYearEnum.OneYear.getId()))
                {
                    year=1;
                }
                if(trianYear.equals(JszyResTrainYearEnum.TwoYear.getId()))
                {
                    year=2;
                }
                if(trianYear.equals(JszyResTrainYearEnum.ThreeYear.getId()))
                {
                    year=3;
                }
                if(year!=0) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE,-1);

                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());
                    } catch (Exception e) {

                    }
                }
            }
            model.addAttribute("completeStartDate",startTime);
            model.addAttribute("completeEndDate",endTime);
        }else{
            model.addAttribute("notHave","Y");
        }
        return "jszy/global/showCertificate/info";
    }

    private String getShowCountryOrProvince(ResDoctorRecruit recruit) {
        String completeNo="";
        String sessionNumber=recruit.getSessionNumber();
        if(StringUtil.isBlank(sessionNumber))
        {
            return "";
        }
        //所有助理全科人员都只生成助理全科证书
        if(recruit.getCatSpeId().equals(JszyTrainCategoryEnum.TCMAssiGeneral.getId()))
        {
            completeNo="AssiGeneral";
        }else {
            int sessionYear = Integer.valueOf(recruit.getSessionNumber());
            //2013年以前全部用江苏省证书
            if (sessionYear <= 2013) {
                //江苏省生成规则待定
                completeNo = getProvinceOrgNo(recruit);
            } else if (sessionYear == 2014) {
                SysOrg org = orgBiz.readSysOrg(recruit.getOrgFlow());
                //只有国家基地使用国家证书
                if (org.getOrgLevelId().equals(OrgLevelEnum.CountryOrg.getId())) {
                    completeNo = "country";
                } else {
                    //江苏省生成规则待定
                    completeNo = getProvinceOrgNo(recruit);
                }
            } else {
                SysOrg org = orgBiz.readSysOrg(recruit.getOrgFlow());
                //国家基地使用国家证书
                if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
                    completeNo = "country";
                } else {
                    List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByJointOrgFlow(org.getOrgFlow());
                    if (jointOrgList != null && jointOrgList.size() > 0) {
                        ResJointOrg resJointOrg = jointOrgList.get(0);
                        SysOrg org2 = orgBiz.readSysOrg(resJointOrg.getOrgFlow());
                        //国家基地的协同基地也使用国家证书
                        if (org2.getOrgLevelId().equals(OrgLevelEnum.CountryOrg.getId())) {
                            completeNo = "country";
                        } else {
                            //江苏省生成规则待定
                            completeNo = getProvinceOrgNo(recruit);
                        }
                    } else {
                        //江苏省生成规则待定
                        completeNo = getProvinceOrgNo(recruit);
                    }
                }
            }
        }
        return completeNo;
    }

    public String getProvinceOrgNo(ResDoctorRecruit resDoctor)
    {
        String no="";
        if(resDoctor.getSpeId().equals("51")||resDoctor.getSpeId().equals("52")||resDoctor.getSpeId().equals("0700"))
        {
            no="provinceAll";
        }
        else{
            //非全科
            no="provinceNoAll";
        }
        return no;
    }
}
