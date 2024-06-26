package com.pinde.sci.ctrl.zsey;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.xjgl.IXjNoticeBiz;
import com.pinde.sci.biz.zsey.IZseyBaseBiz;
import com.pinde.sci.biz.zsey.IZseyDeptBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/zsey/base")
public class ZseyBaseController extends GeneralController {

    @Autowired
    private IZseyBaseBiz baseBiz;
    @Autowired
    private IZseyDeptBiz zseyDeptBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IXjNoticeBiz noticeBiz;

    /**
     * 培训预约信息记录
     * @param trainBeginDate 开始日期
     * @param trainEndDate 结束日期
     * @param teacherName 任课老师
     * @param auditStatusId 审核状态
     * @param deptName 科室
     * @param currentPage 当前页码
     * @param model 视图
     * @return
     */
    @RequestMapping("/appointList")
    public String appointList(String trainBeginDate, String trainEndDate, String teacherName, String auditStatusId, String deptName, String traineesName, Integer currentPage,Model model){
        Map<String,String> param = new HashMap<>();
        param.put("trainBeginDate",trainBeginDate);
        param.put("trainEndDate",trainEndDate);
        param.put("teacherName",teacherName);
        param.put("auditStatusId",auditStatusId);
        param.put("deptName",deptName);
        param.put("traineesName",traineesName);
        PageHelper.startPage(currentPage,10);
        List<Map<String, Object>> dataList = baseBiz.queryAppointList(param);
        model.addAttribute("dataList",dataList);
        return "zsey/base/appointList";
    }

    /**
     * 审核分配教室编辑页面
     */
    @RequestMapping("/auditAppoint")
    public String auditAppoint(String appointFlow,Model model){
        ZseyAppointMain appoint = zseyDeptBiz.queryAppointByFlow(appointFlow);
        model.addAttribute("appoint",appoint);
        return "zsey/base/auditAppoint";
    }

    /**
     * 验证房间在某时间内是否占用
     */
    @RequestMapping("/checkRoom")
    @ResponseBody
    public int checkRoom(ZseyAppointMain main){
        Map<String, String> param = new HashMap<>();
        param.put("appointFlow",main.getAppointFlow());
        param.put("trainDate",main.getTrainDate());
        param.put("beginTime",main.getBeginTime());
        param.put("endTime",main.getEndTime());
        param.put("trainRoomId",main.getTrainRoomId());
        return baseBiz.checkRoomInUsing(param).size();
    }

    /**
     * 基地管理员分配房间保存操作
     */
    @RequestMapping("/saveAppointRoom")
    @ResponseBody
    public String saveAppointRoom(ZseyAppointMain main){
        int num = baseBiz.saveAppointRoom(main);
        if (num > 0) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 预约学员导出操作
     */
    @RequestMapping(value="/expAppoint", method={RequestMethod.POST,RequestMethod.GET})
    public void expAppoint(String trainBeginDate, String trainEndDate, String teacherName, String auditStatusId, String deptName, String traineesName,HttpServletResponse response) throws Exception {
        Map<String,String> param = new HashMap<>();
        param.put("trainBeginDate",trainBeginDate);
        param.put("trainEndDate",trainEndDate);
        param.put("teacherName",teacherName);
        param.put("auditStatusId",auditStatusId);
        param.put("deptName",deptName);
        param.put("traineesName",traineesName);
        List<Map<String, Object>> appointList = baseBiz.queryAppointList(param);
        if(null != appointList && !appointList.isEmpty()){
            for(Map<String, Object> main : appointList) {
                main.put("endTime",main.get("beginTime")+"~"+main.get("endTime"));//存储时间区域
                main.put("beginTime",DateUtil.getWeekFromDate((String)main.get("trainDate"),"3"));//存储星期值
            }
        }
        String[] titles = new String[]{
                "trainDate:培训日期",
                "beginTime:星期",
                "endTime:培训时间",
                "traineesName:培训对象",
                "traineesSpeName:专业",
                "traineesNumber:学员人数/分组数",
                "projectName:培训项目",
                "deviceStr:培训设备",
                "teacherName:任课老师",
                "teacherPhone:联系方式",
                "deptName:申请科室",
                "applicantName:申请人",
                "applicantPhone:联系方式",
                "trainRoomName:培训教室",
                "auditStatusName:审核状态"
        };
        String fileName = "培训预约信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        exportSimpleExcleByObjs(titles, appointList,new String[]{"2","5","7","9","12"}, response.getOutputStream());
    }

    /**
     * 用户查询
     * @param userCode 用户名
     * @param userName 姓名
     * @param deptFlow 科室flow
     * @param roleFlow 角色flow
     * @param currentPage 页码
     * @param model
     * @return
     */
    @RequestMapping("/accountList")
    public String accountList(String userCode, String userName, String deptFlow, String roleFlow, Integer currentPage,Model model){
        Map<String,String> param = new HashMap<>();
        param.put("userCode",userCode);
        param.put("userName",userName);
        param.put("deptFlow",deptFlow);
        param.put("roleFlow",roleFlow);
        PageHelper.startPage(currentPage,10);
        List<Map<String,Object>> dataList = baseBiz.queryAccountList(param);
        List<SysRole> roleList = baseBiz.queryRoleList();
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        if(StringUtil.isNotBlank(orgFlow)){
            List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
            model.addAttribute("deptList",deptList);
        }
        model.addAttribute("dataList",dataList);
        model.addAttribute("roleList",roleList);
        return "zsey/base/accountList";
    }

    /**
     * 新增/编辑用户信息页面
     * @param userFlow 用户flow
     * @param model
     * @return
     */
    @RequestMapping(value="/editAccount")
    public String editAccount(String userFlow, Model model){
        List<SysRole> roleList = baseBiz.queryRoleList();
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        if(StringUtil.isNotBlank(orgFlow)){
            List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
            model.addAttribute("deptList",deptList);
        }
        model.addAttribute("roleList",roleList);
        if(StringUtil.isNotBlank(userFlow)){
            Map<String,String> param = new HashMap<>();
            param.put("userFlow",userFlow);
            List<Map<String,Object>> dataList = baseBiz.queryAccountList(param);
            model.addAttribute("sysUser",dataList!=null && !dataList.isEmpty()?dataList.get(0):dataList);
        }
        return "zsey/base/editAccount";
    }

    /**
     * 新增/编辑用户信息保存操作
     */
    @RequestMapping("/saveAccount")
    @ResponseBody
    public String saveAccount(SysUser user, SysUserRole role){
        int num = baseBiz.saveAccount(user,role);
        if (num == GlobalConstant.ONE_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }else if(num == -1){
            return "此用户名已存在！";
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 启用/停用老师操作
     */
    @RequestMapping(value="/accountOpt")
    @ResponseBody
    public String accountOpt(String userFlow, String recordStatus) {
        int num = baseBiz.accountOpt(userFlow,recordStatus.equals("Y")?"N":"Y");
        if (num == GlobalConstant.ONE_LINE) {
            return GlobalConstant.OPRE_SUCCESSED;
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 字典管理
     * @param sysDict 字典信息
     * @return
     */
    @RequestMapping(value="/dictList" )
    public ModelAndView selectDictList(SysDict sysDict) {
        ModelAndView mav = new ModelAndView("zsey/base/dictManager");
        if(StringUtil.isNotBlank(sysDict.getDictTypeId())){
            List<SysDict> dictList = dictBiz.searchDictList(sysDict);
            mav.addObject("dictList",dictList);
        }
        return mav;
    }

    /**
     * 新增/编辑字典信息页面
     * @param dictFlow 字典flow
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit(String dictFlow) {
        ModelAndView mav = new ModelAndView("zsey/base/editDict");
        if(StringUtil.isNotBlank(dictFlow)){
            SysDict dict = dictBiz.readDict(dictFlow);
            mav.addObject("dict",dict);
        }
        return mav;
    }

    /**
     * 新增/编辑字典信息保存操作
     * @param dict 字典信息
     * @return
     */
    @RequestMapping(value="/save" , method= RequestMethod.POST)
    public @ResponseBody String saveDict(SysDict dict) {
        if(StringUtil.isBlank(dict.getDictFlow())){
            //新增字典时判断该类型字典代码是否存在
            SysDict sysDict=dictBiz.readDict(dict.getDictTypeId(),dict.getDictId(),dict.getOrgFlow());
            if(null!=sysDict){
                return GlobalConstant.OPRE_FAIL_FLAG;
            }
            dictBiz.addDict(dict);
        }else{
            //修改字典时，字典代码可以与自身相同，可以是新ID，但不能与其他字典相同
            List<SysDict> dictList=dictBiz.searchDictListByDictTypeIdNotIncludeSelf(dict);
            for(SysDict sysDict:dictList){
                if(dict.getDictId().equals(sysDict.getDictId())){
                    return GlobalConstant.OPRE_FAIL_FLAG;
                }
            }
            dictBiz.modeDictAndSubDict(dict);
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }
    @RequestMapping(value="/doRefresh" , method=RequestMethod.GET)
    @ResponseBody
    public String doRefresh(HttpServletRequest request) {
        InitConfig.refreshDict(request.getServletContext());
        return "刷新成功！";
    }

    /**
     * 设备信息查询
     */
    @RequestMapping("/devicesList")
    public String devicesList(String deviceName,Integer currentPage,Model model){
        PageHelper.startPage(currentPage,10);
        List<ZseyDevices> devicesList=baseBiz.queryDevicesList(deviceName);
        model.addAttribute("devicesList",devicesList);
        return "zsey/base/devicesList";
    }

    /**
     * 新增/编辑设备信息页面
     */
     @RequestMapping(value="/editDevices")
    public String editDevices(String deviceFlow, Model model){
        if(StringUtil.isNotBlank(deviceFlow)){
            ZseyDevices devices = baseBiz.queryDevicesByFlow(deviceFlow);
            model.addAttribute("devices",devices);
        }
        return "zsey/base/editDevices";
    }

    /**
     * 新增/编辑设备信息保存操作
     */
    @RequestMapping("/saveDevices")
    @ResponseBody
    public String saveDevices(ZseyDevices devices){
        int num = baseBiz.saveDevices(devices);
        if (num == GlobalConstant.ONE_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }else if(num == -1){
            return "此设备已存在！";
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 设备信息删除操作
     */
    @RequestMapping("/delDevices")
    @ResponseBody
    public String delDevices(String deviceFlow){
        int num = baseBiz.delDevices(deviceFlow);
        if (num == GlobalConstant.ONE_LINE) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 设备信息导入
     */
    @RequestMapping(value="/importDevicesExcel")
    @ResponseBody
    public String importDevicesExcel(MultipartFile file) throws Exception{
        if(file.getSize() > 0){
            try{
                int result = baseBiz.importDevicesExcel(file);
                if(GlobalConstant.ZERO_LINE != result){
                    return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
                }else{
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(Exception re){
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    /**
     * 耗材信息查询
     */
    @RequestMapping("/suppliesList")
    public String suppliesList(String suppliesName,Integer currentPage,Model model){
        PageHelper.startPage(currentPage,10);
        List<ZseySupplies> suppliesList=baseBiz.querySuppliesList(suppliesName);
        model.addAttribute("suppliesList",suppliesList);
        return "zsey/base/suppliesList";
    }

    /**
     * 新增耗材信息保存操作
     */
    @RequestMapping("/saveSupplies")
    @ResponseBody
    public String saveSupplies(ZseySupplies supplies){
        int num = baseBiz.saveSupplies(supplies);
        if (num == GlobalConstant.ONE_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }else if(num == -1){
            return "此耗材已存在！";
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 耗材信息删除操作
     */
    @RequestMapping("/delSupplies")
    @ResponseBody
    public String delSupplies(String suppliesFlow){
        int num = baseBiz.delSupplies(suppliesFlow);
        if (num == GlobalConstant.ONE_LINE) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 耗材信息导入
     */
    @RequestMapping(value="/importSuppliesExcel")
    @ResponseBody
    public String importSuppliesExcel(MultipartFile file) throws Exception{
        if(file.getSize() > 0){
            try{
                int result = baseBiz.importSuppliesExcel(file);
                if(GlobalConstant.ZERO_LINE != result){
                    return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
                }else{
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(Exception re){
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    /**
     * 出入库页面
     */
    @RequestMapping(value="/editSupplies")
    public String editSupplies(String suppliesName,String flag,Model model){
        if("out".equals(flag)){
            String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
            if(StringUtil.isNotBlank(orgFlow)){
                List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
                model.addAttribute("deptList",deptList);
            }
        }
        try {
            model.addAttribute("suppliesName",java.net.URLDecoder.decode(suppliesName,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "zsey/base/inOutSupplies";
    }

    /**
     * 新增耗材批次信息保存操作
     */
    @RequestMapping("/saveBatchSupplies")
    @ResponseBody
    public String saveBatchSupplies(ZseySuppliesBatch batch){
        int num = baseBiz.saveBatchSupplies(batch);
        if(num>0) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 耗材批次明细查询
     */
    @RequestMapping("/batchDetail")
    public String batchDetail(String suppliesFlow,String batchType,Integer currentPage,Model model){
        if(StringUtil.isBlank(batchType)){
            batchType = "IN";
        }
        PageHelper.startPage(currentPage,10);
        List<ZseySuppliesBatch> batchList = baseBiz.queryBatchList(suppliesFlow,batchType);
        model.addAttribute("batchList",batchList);
        return "zsey/base/batchDetail";
    }

    /**
     * 教室使用情况
     */
    @RequestMapping("/roomSituation")
    public String roomSituation(String trainDate,Model model){
        if(StringUtil.isBlank(trainDate)){
            SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            trainDate = dft.format(calendar.getTime());
        }
        model.addAttribute("trainDate",trainDate);
        Map<String,List<Map<String,Object>>> roomMap = new HashMap<>();
        List<Map<String,Object>> appointList = baseBiz.deptAppointList(trainDate);
        if(null != appointList && !appointList.isEmpty()){
            for(Map<String,Object> main : appointList){
                String key = (String)main.get("trainRoomId");
                if(roomMap.get(key)==null){
                    List<Map<String,Object>> list = new ArrayList<>();
                    list.add(main);
                    roomMap.put(key,list);
                }else{
                    roomMap.get(key).add(main);
                }
            }
            model.addAttribute("roomMap",roomMap);
        }
        return "zsey/base/roomSituation";
    }

    /**
     * 教室使用统计
     */
    @RequestMapping("/roomStatistics")
    public String roomStatistics(String trainBeginDate,String trainEndDate,Model model){
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        if(StringUtil.isBlank(trainBeginDate)){
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            trainBeginDate = dft.format(calendar.getTime());;
        }
        if(StringUtil.isBlank(trainEndDate)){
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            trainEndDate = dft.format(calendar.getTime());
        }
        model.addAttribute("trainBeginDate",trainBeginDate);
        model.addAttribute("trainEndDate",trainEndDate);
        List<Map<String, Object>> roomList = baseBiz.roomUseNumList(trainBeginDate,trainEndDate);
        model.addAttribute("roomList",roomList);
        return "zsey/base/roomStatistics";
    }

    /**
     * 教室使用统计导出操作
     */
    @RequestMapping(value="/expRoomUseNum", method={RequestMethod.POST,RequestMethod.GET})
    public void expRoomUseNum(String trainBeginDate, String trainEndDate,HttpServletResponse response) throws Exception {
        List<Map<String, Object>> monthStatistics = baseBiz.monthStatistics(trainBeginDate,trainEndDate);
        String fileName = "教室使用统计.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        baseBiz.exportZdyExcel(trainBeginDate,trainEndDate,monthStatistics,response.getOutputStream());
    }
    private void exportSimpleExcleByObjs(String[] titles,List dataList,String[] colIndex,OutputStream os) throws Exception{
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        List<String> paramIds = new ArrayList<String>();
        HSSFCell cell = null;
       List<String> inxList = Arrays.asList(colIndex);
        for(int i = 0 ; i<titles.length ; i++){
            String[] title = titles[i].split(":");
            cell = row.createCell(i);
            cell.setCellValue(title[1]);
            cell.setCellStyle(style);
            paramIds.add(title[0]);
            int length = title[1].length();
            if(inxList.contains(i+1+"")){
                sheet.setColumnWidth(i, 4*1000);
            }else{
                sheet.setColumnWidth(i, length*800);
            }
        }
        if(dataList!=null){
            for(int i=0; i<dataList.size() ; i++){
                Object item = dataList.get(i);
                row = sheet.createRow(i + 1);
                Object result = null;
                for(int j = 0 ; j <paramIds.size();j++){
                    String paramId = paramIds.get(j);
                    result = getValueByAttrs(paramId,item);
                    row.createCell(j).setCellValue(result.toString().length()>32767?result.toString().substring(0,32767):result.toString());
                }
            }
        }
        wb.write(os);
    }
    private static Object getValueByAttrs(String attrNames,Object obj) throws Exception{
        Object value = "";
        if(StringUtil.isNotBlank(attrNames)){
            String proptyName = "";
            int pIndex = attrNames.indexOf(".");
            if(pIndex>=0){
                proptyName = attrNames.substring(0,pIndex);
            }else{
                proptyName = attrNames;
            }
            if(StringUtil.isNotBlank(proptyName) && obj!=null){
                Class clazz = obj.getClass();
                String firstStr = proptyName.substring(0, 1).toUpperCase();
                String secondStr = proptyName.substring(1);
                Method mt;
                Object result;
                if(proptyName.length() >= 4 && "get(".equals(proptyName.substring(0, 4))){
                    int index = Integer.parseInt(proptyName.split("\\(")[1].split("\\)")[0]);
                    result = null;
                    if(((List) obj).size()>index){
                        result = ((List) obj).get(index);
                    }
                }else if(obj instanceof Map){
                    Map map = (Map) obj;
                    result=((Map) obj).get(proptyName);
                } else{
                    mt = clazz.getMethod("get"+firstStr+secondStr);
                    result = mt.invoke(obj);
                }
                if(result!=null){
                    String stringClassName = String.class.getSimpleName();
                    String inegerClassName = Integer.class.getSimpleName();
                    String bigDecimalClassName = BigDecimal.class.getSimpleName();
                    String valueClassName = result.getClass().getSimpleName();
                    if(stringClassName.equals(valueClassName)||bigDecimalClassName.equals(valueClassName)||inegerClassName.equals(valueClassName)){
                        value = result;
                    }else{
                        String surplusName = attrNames.substring(pIndex+1);
                        value = getValueByAttrs(surplusName,result);
                    }
                }
            }
        }
        return value;
    }

    /**
     * 部门使用统计
     */
    @RequestMapping("/deptStatistics")
    public String deptStatistics(String beginDate,String endDate,String deptFlow,Model model){
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        if(StringUtil.isBlank(beginDate)){
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            beginDate = dft.format(calendar.getTime());;
        }
        if(StringUtil.isBlank(endDate)){
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            endDate = dft.format(calendar.getTime());
        }
        model.addAttribute("beginDate",beginDate);
        model.addAttribute("endDate",endDate);
        Map<String,String> param = new HashMap<>();
        param.put("beginDate",beginDate);
        param.put("endDate",endDate);
        param.put("deptFlow",deptFlow);
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        if(StringUtil.isNotBlank(orgFlow)){
            List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
            model.addAttribute("deptList",deptList);
        }
        Map<String,List<Map<String,Object>>> deptMap = new HashMap<>();
        List<Map<String,Object>> dataList=baseBiz.deptStatisticsList(param);
        if(null != dataList && !dataList.isEmpty()){
            int inx = 0;
            for(Map<String,Object> dept : dataList){
                String key = (String)dept.get("deptFlow");
                int inx2 = Integer.valueOf(dept.get("inx").toString());
                if(inx2 > inx){
                    inx = inx2;
                }
                if(deptMap.get(key)==null){
                    List<Map<String,Object>> list = new ArrayList<>();
                    list.add(dept);
                    deptMap.put(key,list);
                }else{
                    deptMap.get(key).add(dept);
                }
            }
            model.addAttribute("deptMap",deptMap);
            model.addAttribute("inx",inx);//耗材最大数据行
        }
        return "zsey/base/deptStatistics";
    }

    /**
     * 部门使用统计导出操作
     */
    @RequestMapping(value="/deptUseNum", method={RequestMethod.POST,RequestMethod.GET})
    public void deptUseNum(String beginDate, String endDate, String deptFlow, HttpServletResponse response) throws Exception {
        Map<String,String> param = new HashMap<>();
        param.put("beginDate",beginDate);
        param.put("endDate",endDate);
        param.put("deptFlow",deptFlow);
        List<Map<String,Object>> dataList=baseBiz.deptStatisticsList(param);
        String fileName = "部门使用统计.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        baseBiz.exportDeptUseExcel(dataList,response.getOutputStream());
    }

    /**
     * 培训项目管理
     */
    @RequestMapping("/projectList")
    public String projectList(String projectName,Integer currentPage,Model model){
        PageHelper.startPage(currentPage,10);
        List<ZseyProjectMain> projectList=baseBiz.queryProjectList(projectName);
        model.addAttribute("projectList",projectList);
        return "zsey/base/projectList";
    }

    /**
     * 新增培训项目保存操作
     */
    @RequestMapping("/saveProject")
    @ResponseBody
    public String saveProject(ZseyProjectMain project){
        int num = baseBiz.saveProject(project);
        if (num == GlobalConstant.ONE_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }else if(num == -1){
            return "此培训项目已存在！";
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 培训项目删除操作
     */
    @RequestMapping("/delProject")
    @ResponseBody
    public String delProject(String projectFlow){
        int num = baseBiz.delProject(projectFlow);
        if (num == GlobalConstant.ONE_LINE) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 编辑材料配置界面
     */
    @RequestMapping(value="/editProjectConfig")
    public String editProjectConfig(String projectFlow,Model model) {
        if(StringUtil.isNotBlank(projectFlow)){
            ZseyProjectMain project = baseBiz.queryProjectByFlow(projectFlow);
            model.addAttribute("project",project);
            List<ZseyAppointMaterial>  materialList = baseBiz.queryProjectConfig(projectFlow);
            model.addAttribute("materialList",materialList);
        }
        return "zsey/base/editProjectConfig";
    }

    /**
     * 新增培训项目配置保存操作
     */
    @RequestMapping("/saveProjectConfig")
    @ResponseBody
    public String saveSkillConfig(String projectFlow, String projectName, String [] devicesRecordList, String [] devicesFlowList, String [] devicesNameList, String [] devicesNum,
                                  String [] suppliesRecordList, String [] suppliesFlowList, String [] suppliesNameList, String [] suppliesNum){
        Map<String,Object> param = new HashMap<>();
        param.put("projectFlow",projectFlow);
        param.put("projectName",projectName);
        param.put("suppliesRecordList", suppliesRecordList==null?null:Arrays.asList(suppliesRecordList));//新增时为null
        param.put("suppliesFlowList", Arrays.asList(suppliesFlowList));
        param.put("suppliesNameList", Arrays.asList(suppliesNameList));
        param.put("suppliesNumList", Arrays.asList(suppliesNum.length == 0?new String[]{""}:suppliesNum));
        param.put("devicesRecordList", devicesRecordList==null?null:Arrays.asList(devicesRecordList));//新增时为null
        param.put("devicesFlowList", Arrays.asList(devicesFlowList));
        param.put("devicesNameList", Arrays.asList(devicesNameList));
        param.put("devicesNumList", Arrays.asList(devicesNum.length == 0?new String[]{""}:devicesNum));
        int num = baseBiz.saveProjectConfig(param);
        if (num > 0) {
            return GlobalConstant.SAVE_SUCCESSED;
        }else if(num == -1){
            return "该项目已维护过！";
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 所需设备耗材查询
     */
    @RequestMapping(value="/selectMaterial")
    public String selectMaterial(String projectFlow, Model model) {
        List<Map<String, Object>> materialList = zseyDeptBiz.queryAppointMaterialList(null);
        if(StringUtil.isNotBlank(projectFlow)){
            List<String> exitMaterialLst = new ArrayList<>();
            Map<String,String> materialMap = new HashMap<>();
            List<ZseyAppointMaterial> matList = zseyDeptBiz.queryMaterialList(projectFlow);
            if(null != matList && matList.size() > 0){
                for(int i=0; i< matList.size(); i++){
                    exitMaterialLst.add(matList.get(i).getMaterialFlow());
                    materialMap.put(matList.get(i).getMaterialFlow(),matList.get(i).getMaterialNumber());
                }
            }
            model.addAttribute("exitMaterialLst",exitMaterialLst);
            model.addAttribute("materialMap",materialMap);
        }
        model.addAttribute("materialList",materialList);
        return "zsey/base/projectMaterial";
    }

    /**
     * 发布公告/公告查询
     */
    @RequestMapping(value="/announcementList/{roleFlag}",method={RequestMethod.POST,RequestMethod.GET})
    public String announcementList(@PathVariable String roleFlag, Integer currentPage , HttpServletRequest request, Model model){
        PageHelper.startPage(currentPage,getPageSize(request));
        List<EduTeachingnotice> notices = noticeBiz.searchAllNotice();
        model.addAttribute("notices",notices);
        return "zsey/base/announcementList";
    }

    @ResponseBody
    @RequestMapping("/saveNotice")
    public String saveNotice(EduTeachingnotice teachingnotice){
        noticeBiz.editNotice(teachingnotice);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    @RequestMapping("/findNoticeByFlow")
    @ResponseBody
    public Object findNoticeByFlow(String infoFlow){
        return noticeBiz.findNoticByFlow(infoFlow);
    }

    @ResponseBody
    @RequestMapping("/delNotice")
    public String delNotice(String infoFlow){
        this.noticeBiz.delNotice(infoFlow);
        return GlobalConstant.DELETE_SUCCESSED;
    }

    @RequestMapping(value="/noticeView")
    public String message(Model model,String infoFlow) throws Exception{
        model.addAttribute("msg", noticeBiz.findNoticByFlow(infoFlow));
        return "xjgl/plat/message";
    }
}