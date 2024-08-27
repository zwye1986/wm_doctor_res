package com.pinde.sci.ctrl.jsres;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IResLectureRandomSignBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResLectureEvaDetailBiz;
import com.pinde.sci.biz.res.IResLectureInfoBiz;
import com.pinde.sci.biz.res.IResLectureScanRegistBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.sch.SchUnitEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.jsres.ParticipateInfoExt;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsres/lecture")
public class JsResLectureController extends GeneralController {
    @Autowired
    private IResLectureInfoBiz resLectureInfoBiz;
    @Autowired
    private IResLectureEvaDetailBiz resLectureEvaDetailBiz;
    @Autowired
    private IResLectureScanRegistBiz resLectureScanRegistBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IResLectureRandomSignBiz randomSignBiz;


    /**
     * 所有讲座查询
     */
    @RequestMapping("/getLectures")
    public String lectureView(ResLectureInfo resLectureInfo,String lectureTrainStartDate,String lectureTrainEndDate, Model model, Integer currentPage,HttpServletRequest request) {

        PageHelper.startPage(currentPage, getPageSize(request));
        String lectureTrainDate = resLectureInfo.getLectureTrainDate();
        String lectureTeacherName = resLectureInfo.getLectureTeacherName();
        String content = resLectureInfo.getLectureContent();
        String lectureTypeId = resLectureInfo.getLectureTypeId();
        SysUser user=GlobalContext.getCurrentUser();
        String orgFlow = user.getOrgFlow();
        List<ResLectureInfo> lectureInfos = resLectureInfoBiz.SearchByDateContentTeacherNameTypeTwo(orgFlow,lectureTrainStartDate, lectureTrainEndDate,content, lectureTeacherName, lectureTypeId,"");
        if (lectureInfos != null) {
            model.addAttribute("lectureInfos", lectureInfos);
        }
        Map<String, Integer> evaMap = new HashMap<String, Integer>();
        if (lectureInfos != null && lectureInfos.size() > 0) {
            for (ResLectureInfo lectureInfo : lectureInfos) {
                String lectureFlow = lectureInfo.getLectureFlow();
                List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.SearchByLectureFlow(lectureFlow);
                if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
                    double sum = 0, i = 0;
                    for (ResLectureEvaDetail lectureEvaDetail : lectureEvaDetails) {
                        String evaScore = lectureEvaDetail.getEvaScore();
                        double score = Double.parseDouble(evaScore);
                        sum += score;
                        i++;
                    }
                    double result = sum / i;
                    int eva = (int) Math.round(result);
                    evaMap.put(lectureFlow, eva);
                }
            }
            model.addAttribute("evaMap", evaMap);
        }
        return "jsres/hospital/lectureView";
    }

    /*
    * 导出讲座信息
    */
  @RequestMapping(value="/exportLectureList")
    public void exportList(Model model,Integer currentPage,String deptFlow,ResLectureInfo resLectureInfo,
                           String startTime,String endTime, HttpServletRequest request, HttpServletResponse response) throws IOException
  {
      // 讲座日期
      String lectureTrainDate = resLectureInfo.getLectureTrainDate();
      // 主讲人
      String lectureTeacherName = resLectureInfo.getLectureTeacherName();
      // 讲座标题
      String content = resLectureInfo.getLectureContent();
      String lectureTypeId = resLectureInfo.getLectureTypeId();
      SysUser user=GlobalContext.getCurrentUser();
      String orgFlow = user.getOrgFlow();

      // 查詢講座信息
      List<ResLectureInfo> lectureInfos = resLectureInfoBiz.SearchByDateContentTeacherNameType(orgFlow,lectureTrainDate, content, lectureTeacherName, lectureTypeId,"");

      if(lectureInfos.size() >0){
          // 查询所有通知对象
          Map<String, Object> notification = new HashMap<>();
          List<Map<String, Object>> notificationList  = resLectureInfoBiz.queryNotification(orgFlow);
          if(notificationList.size() > 0){
              for (Map<String, Object> map :  notificationList) {
                  String str = (String) map.get("LECTURE_FLOW");
                  notification.put(str,map.get("ROLE_NAME"));
              }
          }
          // 评价打分
          Map<String, Object> assessScoreMap = new HashMap<>();
          List<Map<String, Object>> assessScoreList  = resLectureInfoBiz.queryAssessScoreList(orgFlow);
          if(assessScoreList.size() > 0){
              for (Map<String, Object> map :  assessScoreList) {
                  String str = (String) map.get("LECTURE_FLOW");
                  assessScoreMap.put(str,map.get("SCORE"));
              }
          }
          // 查询所有人员数量信息
          Map<String, Object> participateInfo = new HashMap<>();
          List<ParticipateInfoExt> participateInfoExts = new ArrayList<>();
          participateInfoExts  = resLectureInfoBiz.queryParticipateList(orgFlow);

          // 创建工作簿
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
          HSSFRow rowThree = sheet.createRow(0);//第三行
          String[] titles = new String[]{
                  "讲座标题", "讲座地点", "主讲人", "通知对象", "讲座日期",
                  "开始时间", "结束时间","报名学员", "签到学员",
                  "报名师资", "签到师资", "评价"
          };
          HSSFCell cellTitle = null;
          for (int i = 0; i < titles.length; i++) {
              cellTitle = rowThree.createCell(i);
              cellTitle.setCellValue(titles[i]);
              cellTitle.setCellStyle(styleCenter);
              sheet.setColumnWidth(i, titles.length * 1 * 500);
          }
          int rowNum = 1;
          String[] dataList = null;
          if (lectureInfos != null && !lectureInfos.isEmpty()) {
              for (int i = 0; i < lectureInfos.size(); i++, rowNum++) {
                  HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                  ResLectureInfo doctorInfoExt = lectureInfos.get(i);
                  if(doctorInfoExt!= null){
                      // 讲座编码
                      String lectureFlow = doctorInfoExt.getLectureFlow();
                      // 讲座标题
                      String lectureContent = doctorInfoExt.getLectureContent();
                      // 讲座地点
                      String lectureTrainPlace = doctorInfoExt.getLectureTrainPlace();
                      // 主讲人
                      String lectureTeacherName1 = doctorInfoExt.getLectureTeacherName();
                      // 讲座日期
                      String lectureTrainDate1 = doctorInfoExt.getLectureTrainDate();
                      // 开始时间
                      String lectureStartTime = doctorInfoExt.getLectureStartTime();
                      // 结束时间
                      String lectureEndTime = doctorInfoExt.getLectureEndTime();
                      //已扫码学员
                      String scanCodeStudent = "";
                      //已报名学员
                      String signUpStudent = "";
                      //已扫码教师
                      String scanCodeTeacher = "";
                      //已报名教师
                      String signUpTeacher = "";
                      if(participateInfoExts.size() > 0){
                          for (ParticipateInfoExt participate: participateInfoExts) {
                              if(lectureFlow.equals(participate.getLectureFlow()))
                              {
                                  scanCodeStudent = participate.getScanCodeStudent() == null ? "": participate.getScanCodeStudent();
                                  signUpStudent = participate.getSignUpStudent() == null ? "": participate.getSignUpStudent();
                                  scanCodeTeacher = participate.getScanCodeTeacher() == null ? "": participate.getScanCodeTeacher();
                                  signUpTeacher = participate.getSignUpTeacher() == null ? "": participate.getSignUpTeacher();
                                  break;
                              }
                          }
                      }
                      // 通知对象
                      String Notification = "";
                      if(notification != null ) {
                          Notification = (String)notification.get(lectureFlow);
                      }
                      // 打分 double
                      String assessScore = "";
                      if(assessScoreMap != null ) {
                          Object object = assessScoreMap.get(lectureFlow);
                          if(object != null){
                              double v = Double.parseDouble(String.valueOf(object));
                              assessScore = Math.round(v)+"分";
                          }
                      }
                      dataList = new String[]{
                              lectureContent,
                              lectureTrainPlace,
                              lectureTeacherName1,
                              Notification,
                              lectureTrainDate1,
                              lectureStartTime,
                              lectureEndTime,
                              signUpStudent,scanCodeStudent,signUpTeacher,scanCodeTeacher,
                              assessScore
                      };
                      for (int j = 0; j < titles.length; j++) {
                          HSSFCell cellFirst = rowFour.createCell(j);
                          cellFirst.setCellStyle(styleCenter);
                          cellFirst.setCellValue(dataList[j]);
                      }
                  }

              }
          }
          String fileName = "讲座信息管理.xls";
          fileName = URLEncoder.encode(fileName, "UTF-8");
          response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
          response.setContentType("application/octet-stream;charset=UTF-8");
          wb.write(response.getOutputStream());
      }

    }


    @RequestMapping("/doctorLectureView")
    public String doctorLectureView(){
        return "jsres/doctor/lectureView";
    }
    /**
     * 最新讲座查询
     */
    @RequestMapping("/getNewLectures")
    public String getNewLectures(Model model,String roleId) {
        SysUser currUser = GlobalContext.getCurrentUser();
        String orgFlow = currUser.getOrgFlow();

        //获取当前配置的医师角色
        String doctorRole = InitConfig.getSysCfg("res_doctor_role_flow");
        //获取当前配置的老师角色
        String teacherRole = InitConfig.getSysCfg("res_teacher_role_flow");
        //获取当前配置的科主任角色
        String headRole = InitConfig.getSysCfg("res_head_role_flow");
        //获取当前配置的科秘角色
        String secretaryRole = InitConfig.getSysCfg("res_secretary_role_flow");
        String roleFlow="";
        if("doctor".equals(roleId)) {
            ResDoctor doctor = resDoctorBiz.readDoctor(currUser.getUserFlow());
            if(StringUtil.isNotBlank(doctor.getSecondOrgFlow()))
            {
                orgFlow = doctor.getSecondOrgFlow();
            }else
                orgFlow = doctor.getOrgFlow();
            roleFlow=doctorRole;
        }
        if("teacher".equals(roleId)){
            roleFlow=teacherRole;
        }
        if("head".equals(roleId)) {
            roleFlow=headRole;
        }
        if("secretary".equals(roleId)) {
            roleFlow=secretaryRole;
        }
        String userFlow = currUser.getUserFlow();
        List<ResLectureInfo> lectureInfos = resLectureInfoBiz.searchNewLectures(orgFlow,roleId,roleFlow,null);
        model.addAttribute("lectureInfos",lectureInfos);
        Map<String,ResLectureScanRegist> registMap = new HashMap<>();
        Map<String,Integer> registNumMap = new HashMap<>();
        if(lectureInfos!=null&&lectureInfos.size()>0){
            for(ResLectureInfo lectureInfo:lectureInfos){
                String lectureFlow = lectureInfo.getLectureFlow();
                ResLectureScanRegist lectureScanRegist = resLectureScanRegistBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
                registMap.put(lectureFlow,lectureScanRegist);
                List<ResLectureScanRegist> resLectureScanRegists=resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, null);
                if(lectureScanRegist!=null)
                {
                    registNumMap.put(lectureFlow,resLectureScanRegists.size());
                }else{
                    registNumMap.put(lectureFlow,0);
                }
            }
            model.addAttribute("registMap",registMap);
            model.addAttribute("registNumMap",registNumMap);
        }
        return "jsres/doctor/newLectures";
    }
    /**
     * 历史讲座查询
     */
    @RequestMapping("/getHistoryLectures")
    public String getHistoryLectures(Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        String userFlow = currentUser.getUserFlow();
        List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchByUserFLowAndRegist(userFlow);
        List<ResLectureInfo> lectureInfos = new ArrayList<ResLectureInfo>();
        Map<String,ResLectureEvaDetail> evaDetailMap = new HashMap<>();
        Map<String,Integer> evaMap = new HashMap<>();
        Map<String,String> scanMap = new HashMap<>();
        Map<String,String> scan2Map = new HashMap<>();
        if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
            String currDateTime = DateUtil.getCurrDateTime();
            String currDate = currDateTime.substring(0,4)+"-"+currDateTime.substring(4,6)+"-"+currDateTime.substring(6,8);
            String currTime = currDateTime.substring(8,10)+":"+currDateTime.substring(10,12);
            for(ResLectureScanRegist lectureScanRegist:lectureScanRegists){
                String isScan = lectureScanRegist.getIsScan();
                String isScan2 = lectureScanRegist.getIsScan2();
                String lectureFlow = lectureScanRegist.getLectureFlow();
                ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
                String lectureEndTime = lectureInfo.getLectureEndTime();
                String lectureTrainDate = lectureInfo.getLectureTrainDate();
                //判断是否到评价期限
                String date = lectureInfo.getLectureTrainDate();
                String time = lectureInfo.getLectureEndTime();
                String unitID = lectureInfo.getLectureUnitId();
                String period = lectureInfo.getLectureEvaPeriod();
                String startDate = date.substring(0,4)+date.substring(5,7)+date.substring(8,10)+time.substring(0,2)+time.substring(3,5)+"00";
                int step = 0;
                if(SchUnitEnum.Hour.getId().equals(unitID)){
                    step = Integer.parseInt(period);
                }
                if(SchUnitEnum.Day.getId().equals(unitID)){
                    step = Integer.parseInt(period)*24;
                }
                if(SchUnitEnum.Week.getId().equals(unitID)){
                    step = Integer.parseInt(period)*24*7;
                }
                if(SchUnitEnum.Month.getId().equals(unitID)){
                    step = Integer.parseInt(period)*24*30;
                }
                if(SchUnitEnum.Year.getId().equals(unitID)){
                    step = Integer.parseInt(period)*24*365;
                }
                String endDate = DateUtil.addHour(startDate,step);
                String currentDate = DateUtil.getCurrDateTime();
                int dateFlag = endDate.compareTo(currentDate);
                //判断结束
                if((lectureEndTime.compareTo(currTime)<0&&lectureTrainDate.compareTo(currDate)==0)||(lectureTrainDate.compareTo(currDate)<0)){
                    lectureInfos.add(lectureInfo);
                    if("Y".equals(isScan))
                    {
                        scanMap.put(lectureFlow,"Y");
                    }
                    if("Y".equals(isScan2)) {
                        scan2Map.put(lectureFlow, "Y");
                    }
                    evaMap.put(lectureFlow,dateFlag);
                }
                List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(userFlow,lectureFlow);
                if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
                    ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
                    evaDetailMap.put(lectureFlow,lectureEvaDetail);
                }
            }
        }
        model.addAttribute("scanMap",scanMap);
        model.addAttribute("scan2Map",scan2Map);
        model.addAttribute("evaMap",evaMap);
        model.addAttribute("evaDetailMap",evaDetailMap);
        model.addAttribute("lectureInfos",lectureInfos);
        return "jsres/doctor/historyLectures";
    }
    /**
     * 讲座添加/修改
     */
    @RequestMapping("/lectureDetail")
    public String addLecture(String lectureFlow, Model model) {
        ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
        model.addAttribute("lectureInfo", lectureInfo);
        if(StringUtil.isBlank(lectureFlow)) {
            lectureFlow = PkUtil.getUUID();
            model.addAttribute("signUrl", "func://funcFlow=lectureSignin&lectureFlow=" + lectureFlow);
            model.addAttribute("signOutUrl", "func://funcFlow=lectureOutSignin&lectureFlow=" + lectureFlow);
            model.addAttribute("lectureFlow",lectureFlow);
            model.addAttribute("isNew",true);
        }else{
            model.addAttribute("signUrl", "func://funcFlow=lectureSignin&lectureFlow=" + lectureFlow);
            model.addAttribute("signOutUrl", "func://funcFlow=lectureOutSignin&lectureFlow=" + lectureFlow);
            model.addAttribute("lectureFlow",lectureFlow);
        }
        List<ResLectureInfoRole> roles=resLectureInfoBiz.readLectureRoleList(lectureFlow);
        if(roles!=null) {
            model.addAttribute("roles", roles);
        }
        return "jsres/hospital/lectureAdd";
    }
    /**
     * 打开签到二维码
     */
    @RequestMapping("/signUrl")
    public String signUrl(String lectureFlow,Model model){
        ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
        String signUrl = lectureInfo.getLectureCodeUrl();
        String signOutUrl = lectureInfo.getLectureOutUrl();
        model.addAttribute("orgFlow",lectureInfo.getOrgFlow());
        model.addAttribute("signUrl",signUrl);
        model.addAttribute("signOutUrl",signOutUrl);
        model.addAttribute("lectureInfo",lectureInfo);
        return "jsres/hospital/lectureSign";
    }
    /**
     * 讲座保存
     */
    @RequestMapping("/saveLecture")
    @ResponseBody
    public String saveLecture(ResLectureInfo lectureInfo,String itemId[]){
        SysUser current = GlobalContext.getCurrentUser();
        String orgFlow = current.getOrgFlow();
        lectureInfo.setOrgFlow(orgFlow);
        String lectureTypeId = lectureInfo.getLectureTypeId();
        String lectureTypeName = DictTypeEnum.LectureType.getDictNameById(lectureTypeId);
        lectureInfo.setLectureTypeName(lectureTypeName);
        resLectureInfoBiz.addLectureInfo(lectureInfo,itemId);
        return GlobalConstant.OPRE_SUCCESSED_FLAG;

    }
    /**
     * 讲座删除
     */
    @RequestMapping("/delLecture")
    @ResponseBody
    public String delLecture(String lectureFlow){
        ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
        List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchByLectureFlow(lectureFlow);
        if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
            for(ResLectureScanRegist lectureScanRegist:lectureScanRegists){
                lectureScanRegist.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                resLectureScanRegistBiz.edit(lectureScanRegist);
            }
        }
        lectureInfo.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        resLectureInfoBiz.editLectureInfo(lectureInfo);
        return GlobalConstant.DELETE_SUCCESSED;
    }
    /**
     * 报名讲座
     */
    @RequestMapping("/lectureRegist")
    public synchronized String lectureRegist(String lectureFlow,String roleId,Model model){
        SysUser currUser = GlobalContext.getCurrentUser();
        String userFlow = currUser.getUserFlow();

        ResLectureScanRegist regist=resLectureScanRegistBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
        if(regist!=null&&GlobalConstant.FLAG_Y.equals(regist.getIsRegist()))
        {
            model.addAttribute("isRegiest", "N");
            model.addAttribute("msg", "已经报过名了！！请刷新列表");
            return "jsres/doctor/lectureRegist";
        }
        List<ResLectureScanRegist> resLectureScanRegists=resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, null);
        ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
        if(StringUtil.isBlank(lectureInfo.getLimitNum())||resLectureScanRegists==null||resLectureScanRegists.size()<Integer.valueOf(lectureInfo.getLimitNum())) {
            //

            List<ResLectureInfo> infos=resLectureScanRegistBiz.checkJoinList(lectureFlow,userFlow);
            if(infos!=null&&infos.size()>0)
            {
                ResLectureInfo resLectureInfo=infos.get(0);
                model.addAttribute("isRegiest", "N");
                model.addAttribute("msg", "已报名同一时间【"+resLectureInfo.getLectureContent()+"】，不能报名！");
                return "jsres/doctor/lectureRegist";
            }
           int count= resLectureScanRegistBiz.editLectureScanRegist(lectureFlow);
            if(count<0)
            {
                model.addAttribute("isRegiest", "N");
                model.addAttribute("msg", "该讲座报名人数已满，请刷新列表页面！");
                return "jsres/doctor/lectureRegist";
            }
            if(count==0)
            {
                model.addAttribute("isRegiest", "N");
                model.addAttribute("msg", "报名失败，请刷新列表页面！");
                return "jsres/doctor/lectureRegist";
            }
            String lectureTrainDate = lectureInfo.getLectureTrainDate();
            String lectureStartTime = lectureInfo.getLectureStartTime();
            String year = lectureTrainDate.substring(0, 4);
            model.addAttribute("year", year);
            String month = lectureTrainDate.substring(5, 7);
            model.addAttribute("month", month);
            String day = lectureTrainDate.substring(8, 10);
            model.addAttribute("day", day);
            String hour = lectureStartTime.substring(0, 2);
            model.addAttribute("hour", hour);
            String min = lectureStartTime.substring(3, 5);
            model.addAttribute("min", min);
            model.addAttribute("isRegiest", "Y");
        }else{
            model.addAttribute("isRegiest", "N");
            model.addAttribute("msg", "该讲座报名人数已满，请刷新列表页面！");
        }
        return "jsres/doctor/lectureRegist";
    }

    /**
     * 取消报名讲座
     */
    @RequestMapping("/lectureCannelRegist")
    @ResponseBody
    public synchronized String lectureCannelRegist(String lectureFlow,String recordFlow,String roleId,Model model){
        SysUser currUser = GlobalContext.getCurrentUser();
        String userFlow = currUser.getUserFlow();
       if(StringUtil.isNotBlank(lectureFlow))
        {
            ResLectureScanRegist regist=resLectureScanRegistBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
            if(regist==null)
            {
                return "你未报名，无法取消报名信息！";
            }
            if(StringUtil.isBlank(regist.getIsRegist()))
            {
                return "你未报名，无法取消报名信息！";
            }
            if (!GlobalConstant.FLAG_Y.equals(regist.getIsRegist()))
            {
                return "你已取消报名！";
            }
            if(GlobalConstant.FLAG_Y.equals(regist.getIsScan()))
            {
                return "你已扫码签到，无法取消报名信息！";
            }
            regist.setIsRegist(GlobalConstant.FLAG_N);
            int c=resLectureScanRegistBiz.saveRegist(regist);
            if(c==0)
                return "取消失败！";
            return "取消成功！";
        }else{
            return "请选择需要取消报名的讲座！";
        }
    }

    /**
     * 进入评分页面
     */
    @RequestMapping("/evaluate")
    public String evaluate(String lectureFlow,String flag,Model model){
        model.addAttribute("flag",flag);
        model.addAttribute("lectureFlow",lectureFlow);
        SysUser current = GlobalContext.getCurrentUser();
        String userFlow = current.getUserFlow();
        List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(userFlow,lectureFlow);
        if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0) {
            ResLectureEvaDetail resLectureEvaDetail = lectureEvaDetails.get(0);
            if(resLectureEvaDetail!=null) {
                model.addAttribute("resLectureEvaDetail", resLectureEvaDetail);
            }
        }
        return "jsres/doctor/addEvaluate";
    }

    /**
     * 保存评价
     */
    @RequestMapping("/saveEvaluate")
    @ResponseBody
    public String saveEvaluate(ResLectureEvaDetail resLectureEvaDetail){
        SysUser current = GlobalContext.getCurrentUser();
        String userFlow = current.getUserFlow();
        String userName = current.getUserName();
        if(StringUtil.isNotBlank(userFlow)){
            resLectureEvaDetail.setOperUserFlow(userFlow);
        }
        if(StringUtil.isNotBlank(userName)){
            resLectureEvaDetail.setOperUserName(userName);
        }
        List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(userFlow,resLectureEvaDetail.getLectureFlow());
        if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0) {
            return  "已经评价过讲座信息！请刷新页面后重试！";
        }
        resLectureEvaDetailBiz.editResLectureEvaDetail(resLectureEvaDetail);
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    /**
     * 打开扫码或报名页面
     */
    @RequestMapping("/getEva")
    public String getEva(String lectureFlow,String flag,Model model){
        model.addAttribute("flag",flag);
        model.addAttribute("lectureFlow",lectureFlow);
        List<String> roles=new ArrayList<>();

        ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
        model.addAttribute("lectureInfo",lectureInfo);

        String doctorRoleFlow= InitConfig.getSysCfg("res_doctor_role_flow");
        if (StringUtil.isNotBlank(doctorRoleFlow)) {
            roles.add(doctorRoleFlow);
        }
        List<ResLectureScanRegist> scans = resLectureScanRegistBiz.searchIsScan(lectureFlow,roles);
        model.addAttribute("scans",scans);
        List<ResLectureScanRegist> regists = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow,roles);
        model.addAttribute("regists",regists);

        roles=new ArrayList<>();
       String roleTeacher= InitConfig.getSysCfg("res_teacher_role_flow");
        if (StringUtil.isNotBlank(roleTeacher)) {
            roles.add(roleTeacher);
        }
       String roleHead= InitConfig.getSysCfg("res_head_role_flow");
        if (StringUtil.isNotBlank(roleHead)) {
            roles.add(roleHead);
        }
        String roleSecretary= InitConfig.getSysCfg("res_secretary_role_flow");
        if (StringUtil.isNotBlank(roleSecretary)) {
            roles.add(roleSecretary);
        }
        List<ResLectureScanRegist> scans2 = resLectureScanRegistBiz.searchIsScan(lectureFlow,roles);
        model.addAttribute("scans2",scans2);
        List<ResLectureScanRegist> regists2 = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow,roles);
        model.addAttribute("regists2",regists2);
        return "jsres/doctor/evaView";
    }
    /**
     * 扫码页面
     */
    @RequestMapping("/evaList")
    public String evaList(String lectureFlow,Model model,String flag){
        List<String> roles=new ArrayList<>();

        ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
        model.addAttribute("lectureInfo",lectureInfo);

        //随机签到
        List<ResLectureRandomSign> randomSignList = new ArrayList<>();
        if("Y".equals(flag)) {
            randomSignList = randomSignBiz.searchRandomByLectureFlow(lectureFlow);
        }
        model.addAttribute("randomSignList", randomSignList);
        String doctorRoleFlow= InitConfig.getSysCfg("res_doctor_role_flow");
        if (StringUtil.isNotBlank(doctorRoleFlow)) {
            roles.add(doctorRoleFlow);
        }
        Map<String,ResDoctor> doctorMap=new HashMap<>();
        List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchIsScan(lectureFlow, roles);
        Map<String,ResLectureEvaDetail> evaDetailMap = new HashMap<>();
        Map<String,String> scanMap = new HashMap<>();
        if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
            for(ResLectureScanRegist lectureScanRegist:lectureScanRegists){
                String operUserFlow = lectureScanRegist.getOperUserFlow();
                doctorMap.put(lectureScanRegist.getRecordFlow(),resDoctorBiz.readDoctor(operUserFlow));
                List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow,lectureFlow);
                if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
                    ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
                    evaDetailMap.put(operUserFlow,lectureEvaDetail);
                }
                if(randomSignList!=null && randomSignList.size()>0) {
                    for (int i = 0; i < randomSignList.size(); i++) {
                        List<ResLectureRandomScan> scanList = randomSignBiz.searchRandomScan(operUserFlow, lectureFlow, randomSignList.get(i).getRandomFlow());
                        if (scanList != null && scanList.size() > 0) {
                            scanMap.put(operUserFlow + randomSignList.get(i).getRandomFlow(), DateUtil.transTime(scanList.get(0).getScanTime()));
                        } else {
                            scanMap.put(operUserFlow + randomSignList.get(i).getRandomFlow(), "");
                        }
                    }
                }
            }
            model.addAttribute("lectureScanRegists",lectureScanRegists);
        }
        model.addAttribute("scanMap",scanMap);
        model.addAttribute("evaDetailMap",evaDetailMap);
        model.addAttribute("doctorMap",doctorMap);
        return "jsres/doctor/evaList";
    }

    /**
     * 报名页面
     */
    @RequestMapping("/noRegist")
    public String noRegist(String lectureFlow,Model model){
        List<String> roles=new ArrayList<>();

        ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
        model.addAttribute("lectureInfo",lectureInfo);

        String doctorRoleFlow= InitConfig.getSysCfg("res_doctor_role_flow");
        if (StringUtil.isNotBlank(doctorRoleFlow)) {
            roles.add(doctorRoleFlow);
        }
        Map<String,ResDoctor> doctorMap=new HashMap<>();
        List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, roles);
        if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
            for(ResLectureScanRegist regist:lectureScanRegists)
            {
                doctorMap.put(regist.getRecordFlow(),resDoctorBiz.readDoctor(regist.getOperUserFlow()));
            }
        }
        model.addAttribute("lectureScanRegists",lectureScanRegists);
        model.addAttribute("doctorMap",doctorMap);
        return "jsres/doctor/noRegist";
    }

    /**
     * 扫码页面
     */
    @RequestMapping("/evaList2")
    public String evaList2(String lectureFlow,Model model){
        List<String> roles=new ArrayList<>();

        ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
        model.addAttribute("lectureInfo",lectureInfo);

        String roleTeacher= InitConfig.getSysCfg("res_teacher_role_flow");
        if (StringUtil.isNotBlank(roleTeacher)) {
            roles.add(roleTeacher);
        }
        String roleHead= InitConfig.getSysCfg("res_head_role_flow");
        if (StringUtil.isNotBlank(roleHead)) {
            roles.add(roleHead);
        }
        String roleSecretary= InitConfig.getSysCfg("res_secretary_role_flow");
        if (StringUtil.isNotBlank(roleSecretary)) {
            roles.add(roleSecretary);
        }
        Map<String,SysUser> userMap=new HashMap<>();
        Map<String,String> teaMap=new HashMap<>();
        Map<String,String> headMap=new HashMap<>();
        Map<String,String> secretaryMap=new HashMap<>();
        List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchIsScan(lectureFlow, roles);
        Map<String,ResLectureEvaDetail> evaDetailMap = new HashMap<>();
        if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
            for(ResLectureScanRegist lectureScanRegist:lectureScanRegists){
                String operUserFlow = lectureScanRegist.getOperUserFlow();
                List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow,lectureFlow);
                if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
                    ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
                    evaDetailMap.put(operUserFlow,lectureEvaDetail);
                }

                SysUser user=userBiz.readSysUser(operUserFlow);
                userMap.put(operUserFlow,user);
                List<SysUserRole> userRoles=userRoleBiz.getByUserFlow(user.getUserFlow());
                if(userRoles!=null)
                {
                    for(SysUserRole userRole:userRoles)
                    {
                        if (StringUtil.isNotBlank(roleTeacher)&&roleTeacher.equals(userRole.getRoleFlow())) {
                            teaMap.put(user.getUserFlow(),"Y");
                        }
                        if (StringUtil.isNotBlank(roleHead)&&roleHead.equals(userRole.getRoleFlow())) {
                            headMap.put(user.getUserFlow(),"Y");
                        }
                        if (StringUtil.isNotBlank(roleSecretary) && roleSecretary.equals(userRole.getRoleFlow())) {
                            secretaryMap.put(user.getUserFlow(),"Y");
                        }
                    }
                }
            }
            model.addAttribute("userMap",userMap);
            model.addAttribute("teaMap",teaMap);
            model.addAttribute("headMap",headMap);
            model.addAttribute("secretaryMap",secretaryMap);
            model.addAttribute("lectureScanRegists",lectureScanRegists);
        }
        model.addAttribute("evaDetailMap",evaDetailMap);
        return "jsres/doctor/evaList2";
    }

    /**
     * 报名页面
     */
    @RequestMapping("/noRegist2")
    public String noRegist2(String lectureFlow,Model model){
        List<String> roles=new ArrayList<>();

        ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
        model.addAttribute("lectureInfo",lectureInfo);

        String roleTeacher= InitConfig.getSysCfg("res_teacher_role_flow");
        if (StringUtil.isNotBlank(roleTeacher)) {
            roles.add(roleTeacher);
        }
        String roleHead= InitConfig.getSysCfg("res_head_role_flow");
        if (StringUtil.isNotBlank(roleHead)) {
            roles.add(roleHead);
        }
        String roleSecretary= InitConfig.getSysCfg("res_secretary_role_flow");
        if (StringUtil.isNotBlank(roleSecretary)) {
            roles.add(roleSecretary);
        }
        Map<String,SysUser> userMap=new HashMap<>();
        Map<String,String> teaMap=new HashMap<>();
        Map<String,String> headMap=new HashMap<>();
        Map<String,String> secretaryMap=new HashMap<>();
        List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, roles);
        if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
            model.addAttribute("lectureScanRegists",lectureScanRegists);
            for(ResLectureScanRegist regist:lectureScanRegists)
            {
                SysUser user=userBiz.readSysUser(regist.getOperUserFlow());
                userMap.put(regist.getOperUserFlow(),user);
                List<SysUserRole> userRoles=userRoleBiz.getByUserFlow(user.getUserFlow());
                if(userRoles!=null)
                {
                    for(SysUserRole userRole:userRoles)
                    {
                        if (StringUtil.isNotBlank(roleTeacher)&&roleTeacher.equals(userRole.getRoleFlow())) {
                           teaMap.put(user.getUserFlow(),"Y");
                        }
                        if (StringUtil.isNotBlank(roleHead)&&roleHead.equals(userRole.getRoleFlow())) {
                            headMap.put(user.getUserFlow(),"Y");
                        }
                        if (StringUtil.isNotBlank(roleSecretary) && roleSecretary.equals(userRole.getRoleFlow())) {
                            secretaryMap.put(user.getUserFlow(),"Y");
                        }
                    }
                }

            }
            model.addAttribute("userMap",userMap);
            model.addAttribute("teaMap",teaMap);
            model.addAttribute("headMap",headMap);
            model.addAttribute("secretaryMap",secretaryMap);
        }
        return "jsres/doctor/noRegist2";
    }

    @RequestMapping(value = "/exportInfo")
    public void exportInfo(HttpServletResponse response, String pageType,String lectureFlow) throws Exception {

        String[] head = new String[]{};
        String[] titles = null;
        if ("evaList".equals(pageType)) {
            List<String> roles=new ArrayList<>();

            String doctorRoleFlow= InitConfig.getSysCfg("res_doctor_role_flow");
            if (StringUtil.isNotBlank(doctorRoleFlow)) {
                roles.add(doctorRoleFlow);
            }
            List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchIsScan(lectureFlow, roles);
            Map<String,ResLectureEvaDetail> evaDetailMap = new HashMap<>();
            if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
                for(ResLectureScanRegist lectureScanRegist:lectureScanRegists){
                    String operUserFlow = lectureScanRegist.getOperUserFlow();
                    List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow,lectureFlow);
                    if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
                        ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
                        evaDetailMap.put(operUserFlow,lectureEvaDetail);
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
            HSSFCellStyle styleRight = wb.createCellStyle(); //居中
            styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
            styleRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            // 为工作簿添加sheet
            HSSFSheet sheet = wb.createSheet("sheet1");
            HSSFRow rowOne = sheet.createRow(0);//第一行
            titles = new String[]{
                    "姓名",
                    "签到时间",
                    "签退时间",
                    "培训类型",
                    "培训专业",
                    "人员类型",
                    "年级",
                    "评价内容",
                    "评分内容"
            };
            HSSFCell cellTitle = null;
            for (int i = 0; i < titles.length; i++) {
                cellTitle = rowOne.createCell(i);
                cellTitle.setCellValue(titles[i]);
                cellTitle.setCellStyle(styleCenter);
                sheet.setColumnWidth(i, titles.length * 506);
            }
            int rowNum = 1;
            String[] resultList = null;
            if (lectureScanRegists != null && !lectureScanRegists.isEmpty()) {
                for (int i = 0; i < lectureScanRegists.size(); i++, rowNum++) {
                    HSSFRow rowTwo = sheet.createRow(rowNum);
                    ResLectureEvaDetail detail=evaDetailMap.get(lectureScanRegists.get(i).getOperUserFlow());
                    if(detail==null) detail=new ResLectureEvaDetail();

                            ResDoctor doctor=resDoctorBiz.readDoctor(lectureScanRegists.get(i).getOperUserFlow());
                            if(doctor!=null)
                                lectureScanRegists.get(i).setTrainingTypeId(doctor.getDoctorTypeName());
                    resultList = new String[]{
                            lectureScanRegists.get(i).getOperUserName(),
                            DateUtil.transTime(lectureScanRegists.get(i).getScanTime()),
                            DateUtil.transTime(lectureScanRegists.get(i).getScan2Time()),
                            lectureScanRegists.get(i).getTrainingTypeName(),
                            lectureScanRegists.get(i).getTrainingSpeName(),
                            doctor.getDoctorTypeName(),
                            lectureScanRegists.get(i).getSessionNumber(),
                            detail.getEvaContent(),
                            detail.getEvaScore(),
                    };
                    for (int j = 0; j < titles.length; j++) {
                        HSSFCell cellFirst = rowTwo.createCell(j);
                        cellFirst.setCellStyle(styleCenter);
                        cellFirst.setCellValue(resultList[j]);
                    }
                }
            }
            String fileName = "已扫码学员信息.xls";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            wb.write(response.getOutputStream());
        } else if("noRegist".equals(pageType)){
            List<String> roles=new ArrayList<>();

            String doctorRoleFlow= InitConfig.getSysCfg("res_doctor_role_flow");
            if (StringUtil.isNotBlank(doctorRoleFlow)) {
                roles.add(doctorRoleFlow);
            }
            List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, roles);
            titles = new String[]{
                    "operUserName:姓名",
                    "trainingTypeName:培训类型",
                    "trainingSpeName:培训专业",
                    "trainingTypeId:人员类型",
                    "sessionNumber:年级"
            };
            if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
                for(ResLectureScanRegist regist:lectureScanRegists)
                {
                    ResDoctor doctor=resDoctorBiz.readDoctor(regist.getOperUserFlow());
                    if(doctor!=null)
                    regist.setTrainingTypeId(doctor.getDoctorTypeName());
                }
            }
            String fileName = "已报名学员信息.xls";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            ExcleUtile.exportSimpleExcleByObjs(titles, lectureScanRegists, response.getOutputStream());
        } else if("evaList2".equals(pageType)){
            List<String> roles=new ArrayList<>();

            String roleTeacher= InitConfig.getSysCfg("res_teacher_role_flow");
            if (StringUtil.isNotBlank(roleTeacher)) {
                roles.add(roleTeacher);
            }
            String roleHead= InitConfig.getSysCfg("res_head_role_flow");
            if (StringUtil.isNotBlank(roleHead)) {
                roles.add(roleHead);
            }
            String roleSecretary= InitConfig.getSysCfg("res_secretary_role_flow");
            if (StringUtil.isNotBlank(roleSecretary)) {
                roles.add(roleSecretary);
            }

            List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchIsScan(lectureFlow, roles);
            Map<String,ResLectureEvaDetail> evaDetailMap = new HashMap<>();
            if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
                for(ResLectureScanRegist lectureScanRegist:lectureScanRegists){
                    String operUserFlow = lectureScanRegist.getOperUserFlow();
                    List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow,lectureFlow);
                    if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
                        ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
                        evaDetailMap.put(operUserFlow,lectureEvaDetail);
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
            HSSFCellStyle styleRight = wb.createCellStyle(); //居中
            styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
            styleRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            // 为工作簿添加sheet
            HSSFSheet sheet = wb.createSheet("sheet1");
            HSSFRow rowOne = sheet.createRow(0);//第一行
            titles = new String[]{
                    "姓名",
                    "签到时间",
                    "签退时间",
                    "科室",
                    "是否带教",
                    "是否科主任",
                    "是否教秘",
                    "评价内容",
                    "评分内容"
            };
            HSSFCell cellTitle = null;
            for (int i = 0; i < titles.length; i++) {
                cellTitle = rowOne.createCell(i);
                cellTitle.setCellValue(titles[i]);
                cellTitle.setCellStyle(styleCenter);
                sheet.setColumnWidth(i, titles.length * 506);
            }
            int rowNum = 1;
            String[] resultList = null;
            if (lectureScanRegists != null && !lectureScanRegists.isEmpty()) {
                for (int i = 0; i < lectureScanRegists.size(); i++, rowNum++) {
                    HSSFRow rowTwo = sheet.createRow(rowNum);
                    ResLectureEvaDetail detail=evaDetailMap.get(lectureScanRegists.get(i).getOperUserFlow());
                    if(detail==null) detail=new ResLectureEvaDetail();

                    SysUser user=userBiz.readSysUser(lectureScanRegists.get(i).getOperUserFlow());
                    List<SysUserRole> userRoles=userRoleBiz.getByUserFlow(user.getUserFlow());
                    String isTea="否"  ;
                    String isHead="否";
                    String isSecretary="否";
                    if(userRoles!=null)
                    {
                        for(SysUserRole userRole:userRoles)
                        {
                            if (StringUtil.isNotBlank(roleTeacher)&&roleTeacher.equals(userRole.getRoleFlow())) {
                                isTea="是";
                            }
                            if (StringUtil.isNotBlank(roleHead)&&roleHead.equals(userRole.getRoleFlow())) {
                                isHead="是";
                            }
                            if (StringUtil.isNotBlank(roleSecretary) && roleSecretary.equals(userRole.getRoleFlow())) {
                                isSecretary = "是";
                            }
                        }
                    }
                    resultList = new String[]{
                            user.getUserName(),
                            DateUtil.transTime(lectureScanRegists.get(i).getScanTime()),
                            DateUtil.transTime(lectureScanRegists.get(i).getScan2Time()),
                            user.getDeptName(),
                            isTea,
                            isHead,
                            isSecretary,
                            detail.getEvaContent(),
                            detail.getEvaScore(),
                    };
                    for (int j = 0; j < titles.length; j++) {
                        HSSFCell cellFirst = rowTwo.createCell(j);
                        cellFirst.setCellStyle(styleCenter);
                        cellFirst.setCellValue(resultList[j]);
                    }
                }
            }
            String fileName = "已扫码师资人员信息.xls";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            wb.write(response.getOutputStream());
        } else if("noRegist2".equals(pageType)){
            List<String> roles=new ArrayList<>();

            String roleTeacher= InitConfig.getSysCfg("res_teacher_role_flow");
            if (StringUtil.isNotBlank(roleTeacher)) {
                roles.add(roleTeacher);
            }
            String roleHead= InitConfig.getSysCfg("res_head_role_flow");
            if (StringUtil.isNotBlank(roleHead)) {
                roles.add(roleHead);
            }
            String roleSecretary= InitConfig.getSysCfg("res_secretary_role_flow");
            if (StringUtil.isNotBlank(roleSecretary)) {
                roles.add(roleSecretary);
            }
            List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, roles);
            titles = new String[]{
                    "operUserName:姓名",
                    "trainingSpeName:科室",
                    "isScan:是否带教",
                    "isRegist:是否科主任",
                    "isScan2:是否教秘"
            };
            if(lectureScanRegists!=null&&lectureScanRegists.size()>0) {
                for (int i = 0; i < lectureScanRegists.size(); i++) {
                    SysUser user = userBiz.readSysUser(lectureScanRegists.get(i).getOperUserFlow());
                    List<SysUserRole> userRoles = userRoleBiz.getByUserFlow(user.getUserFlow());
                    String isTea = "否";
                    String isHead = "否";
                    String isSecretary = "否";
                    if (userRoles != null) {
                        for (SysUserRole userRole : userRoles) {
                            if (StringUtil.isNotBlank(roleTeacher) && roleTeacher.equals(userRole.getRoleFlow())) {
                                isTea = "是";
                            }
                            if (StringUtil.isNotBlank(roleHead) && roleHead.equals(userRole.getRoleFlow())) {
                                isHead = "是";
                            }
                            if (StringUtil.isNotBlank(roleSecretary) && roleSecretary.equals(userRole.getRoleFlow())) {
                                isSecretary = "是";
                            }
                        }
                    }
                    lectureScanRegists.get(i).setOperUserName(user.getUserName());
                    lectureScanRegists.get(i).setTrainingSpeName(user.getDeptName());
                    lectureScanRegists.get(i).setIsScan(isTea);
                    lectureScanRegists.get(i).setIsRegist(isHead);
                    lectureScanRegists.get(i).setIsScan2(isSecretary);
                }
            }
            String fileName = "已报名师资人员信息.xls";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            ExcleUtile.exportSimpleExcleByObjs(titles, lectureScanRegists, response.getOutputStream());
        }

    }

    /**
     * 随机签到
     */
    @RequestMapping("/randomSignIn")
    public String randomSignIn(String lectureFlow,Model model){
        List<ResLectureRandomSign> randomSignList = randomSignBiz.searchRandomByLectureFlow(lectureFlow);
        ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
        model.addAttribute("date",lectureInfo.getLectureTrainDate());
        model.addAttribute("lectureFlow",lectureFlow);
        model.addAttribute("randomSignList",randomSignList);
        return "jsres/hospital/randomSignIn";
    }

    /**
     * 随机签到保存
     */
    @RequestMapping("/saveRandomSign")
    @ResponseBody
    public String saveRandomSign(ResLectureRandomSign randomSign){
        if("N".equals(randomSign.getCodeStatusType())){
            randomSign.setCodeStatusName("静态二维码");
        }
        if("Y".equals(randomSign.getCodeStatusType())){
            randomSign.setCodeStatusName("动态二维码");
        }
        int i = randomSignBiz.saveRandom(randomSign);
        if(i>GlobalConstant.ZERO_LINE){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 查看随机签到二维码
     */
    @RequestMapping("/randomSignUrl")
    public String randomSignUrl(String randomFlow,Model model){
        ResLectureRandomSign randomSign = randomSignBiz.read(randomFlow);
        model.addAttribute("signUrl", "func://funcFlow=randomSignIn&randomFlow=" + randomFlow);
        model.addAttribute("randomSign",randomSign);
        return "jsres/hospital/randomSignCode";
    }

    /**
     * 随机扫码页面
     */
    @RequestMapping("/randomEvaList")
    public String randomEvaList(String lectureFlow,Model model){
        List<String> roles=new ArrayList<>();

        ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
        model.addAttribute("lectureInfo",lectureInfo);

        String doctorRoleFlow= InitConfig.getSysCfg("res_doctor_role_flow");
        if (StringUtil.isNotBlank(doctorRoleFlow)) {
            roles.add(doctorRoleFlow);
        }

        List<ResLectureRandomSign> randomSignList = randomSignBiz.searchRandomByLectureFlow(lectureFlow);
        model.addAttribute("randomSignList",randomSignList);

        Map<String,ResDoctor> doctorMap=new HashMap<>();
        List<ResLectureRandomScan> randomScanList = randomSignBiz.searchIsScan(lectureFlow, roles);
//        Map<String,ResLectureEvaDetail> evaDetailMap = new HashMap<>();
        if(randomScanList!=null&&randomScanList.size()>0){
            for(ResLectureRandomScan randomScan:randomScanList){
                String operUserFlow = randomScan.getOperUserFlow();
                doctorMap.put(randomScan.getRecordFlow(),resDoctorBiz.readDoctor(operUserFlow));
//                List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow,lectureFlow);
//                if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
//                    ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
//                    evaDetailMap.put(operUserFlow,lectureEvaDetail);
//                }
            }
            model.addAttribute("randomScanList",randomScanList);
        }
//        model.addAttribute("evaDetailMap",evaDetailMap);
        model.addAttribute("doctorMap",doctorMap);

        return "jsres/doctor/randomEvaList";
    }

    @RequestMapping(value = "/exportInfo2")
    public void exportInfo2(HttpServletResponse response,String pageType,String lectureFlow,String flag) throws Exception {
        String[] head = new String[]{};
        String[] titles = null;
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

        List<String> roles=new ArrayList<>();

        String doctorRoleFlow= InitConfig.getSysCfg("res_doctor_role_flow");
        if (StringUtil.isNotBlank(doctorRoleFlow)) {
            roles.add(doctorRoleFlow);
        }
 //       if(flag.equals("N")) {
        List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchIsScan(lectureFlow, roles);
        Map<String, ResLectureEvaDetail> evaDetailMap = new HashMap<>();
        if (lectureScanRegists != null && lectureScanRegists.size() > 0) {
            for (ResLectureScanRegist lectureScanRegist : lectureScanRegists) {
                String operUserFlow = lectureScanRegist.getOperUserFlow();
                List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow, lectureFlow);
                if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
                    ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
                    evaDetailMap.put(operUserFlow, lectureEvaDetail);
                }
            }
        }
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("已扫码学员");
        HSSFRow rowOne = sheet.createRow(0);//第一行
            /*titles = new String[]{
                    "姓名",
                    "签到时间",
                    "签退时间",
                    "培训类型",
                    "培训专业",
                    "人员类型",
                    "年级",
                    "评价内容",
                    "评分内容"
            };*/
        List<ResLectureRandomSign> randomSigns = randomSignBiz.searchRandomByLectureFlow(lectureFlow);
        List<String> list = new ArrayList<>();
        list.add("姓名");
        list.add("签到时间");
        if(randomSigns!=null && randomSigns.size()>0){
            for (int i = 0; i<randomSigns.size();i++) {
                list.add("随机签到");
            }
        }
        list.add("签退时间");
        list.add("培训类型");
        list.add("培训专业");
        list.add("人员类型");
        list.add("年级");
        list.add("评价内容");
        list.add("评分内容");

        titles = list.toArray(new String[list.size()]);

        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowOne.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 506);
        }
        int rowNum = 1;
        String[] resultList = null;

        if (lectureScanRegists != null && !lectureScanRegists.isEmpty()) {
            for (int i = 0; i < lectureScanRegists.size(); i++, rowNum++) {
                List<String> list5 = new ArrayList<>();
                HSSFRow rowTwo = sheet.createRow(rowNum);
                ResLectureEvaDetail detail = evaDetailMap.get(lectureScanRegists.get(i).getOperUserFlow());
                if (detail == null) detail = new ResLectureEvaDetail();

                ResDoctor doctor = resDoctorBiz.readDoctor(lectureScanRegists.get(i).getOperUserFlow());
                if (doctor != null)
                    lectureScanRegists.get(i).setTrainingTypeId(doctor.getDoctorTypeName());

                list5.add(lectureScanRegists.get(i).getOperUserName());
                list5.add(DateUtil.transTime(lectureScanRegists.get(i).getScanTime()));
                if(randomSigns!=null && randomSigns.size()>0){
                    for(int j=0;j<randomSigns.size();j++){
                        List<ResLectureRandomScan> scanList = randomSignBiz.searchRandomScan(lectureScanRegists.get(i).getOperUserFlow(),lectureFlow,
                                randomSigns.get(j).getRandomFlow());
                        if(scanList!=null && scanList.size()>0){
                            list5.add(DateUtil.transTime(scanList.get(0).getScanTime()));
                        }else{
                            list5.add("");
                        }
                    }
                }
                list5.add(DateUtil.transTime(lectureScanRegists.get(i).getScan2Time()));
                list5.add(lectureScanRegists.get(i).getTrainingTypeName());
                list5.add(lectureScanRegists.get(i).getTrainingSpeName());
                list5.add(doctor.getDoctorTypeName());
                list5.add(lectureScanRegists.get(i).getSessionNumber());
                list5.add(detail.getEvaContent());
                list5.add(detail.getEvaScore());

                resultList = list5.toArray(new String[list5.size()]);

                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowTwo.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(resultList[j]);
                }
            }
        }


        HSSFSheet sheet2 = wb.createSheet("已报名学员");
        HSSFRow rowTwo = sheet2.createRow(0);//第一行
        titles = new String[]{
                "姓名",
                "培训类型",
                "培训专业",
                "人员类型",
                "年级"
        };
        HSSFCell cellTitle3 = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle3 = rowTwo.createCell(i);
            cellTitle3.setCellValue(titles[i]);
            cellTitle3.setCellStyle(styleCenter);
            sheet2.setColumnWidth(i, titles.length * 506);
        }
        List<String> roles2=new ArrayList<>();

        String doctorRoleFlow2= InitConfig.getSysCfg("res_doctor_role_flow");
        if (StringUtil.isNotBlank(doctorRoleFlow2)) {
            roles2.add(doctorRoleFlow2);
        }
        List<ResLectureScanRegist> lectureScanRegists4 = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, roles2);

        String[] resultList2 = null;
        int rowN = 1;
        if(lectureScanRegists4!=null&&lectureScanRegists4.size()>0){
            for(int i=0;i< lectureScanRegists4.size();i++,rowN++)
            {
                HSSFRow rowSix = sheet2.createRow(rowN);
                ResDoctor doctor=resDoctorBiz.readDoctor(lectureScanRegists4.get(i).getOperUserFlow());
                if(doctor!=null)
                    lectureScanRegists4.get(i).setTrainingTypeId(doctor.getDoctorTypeName());
                resultList2 = new String[]{
                        lectureScanRegists4.get(i).getOperUserName(),
                        lectureScanRegists4.get(i).getTrainingTypeName(),
                        lectureScanRegists4.get(i).getTrainingSpeName(),
                        doctor.getDoctorTypeName(),
                        lectureScanRegists4.get(i).getSessionNumber(),
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowSix.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(resultList2[j]);
                }
            }
        }


        List<String> roles3=new ArrayList<>();
        String roleTeacher= InitConfig.getSysCfg("res_teacher_role_flow");
        if (StringUtil.isNotBlank(roleTeacher)) {
            roles3.add(roleTeacher);
        }
        String roleHead= InitConfig.getSysCfg("res_head_role_flow");
        if (StringUtil.isNotBlank(roleHead)) {
            roles3.add(roleHead);
        }
        String roleSecretary= InitConfig.getSysCfg("res_secretary_role_flow");
        if (StringUtil.isNotBlank(roleSecretary)) {
            roles3.add(roleSecretary);
        }

        List<ResLectureScanRegist> lectureScanRegists2 = resLectureScanRegistBiz.searchIsScan(lectureFlow, roles3);
        Map<String,ResLectureEvaDetail> evaDetailMap2 = new HashMap<>();

        if(lectureScanRegists2!=null&&lectureScanRegists2.size()>0){
            for(ResLectureScanRegist lectureScanRegist:lectureScanRegists2){
                String operUserFlow = lectureScanRegist.getOperUserFlow();
                List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow,lectureFlow);
                if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
                    ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
                    evaDetailMap2.put(operUserFlow,lectureEvaDetail);
                }
            }
        }
        // 为工作簿添加sheet
        HSSFSheet sheet3 = wb.createSheet("已扫码师资人员");
        HSSFRow rowThree = sheet3.createRow(0);//第一行
        titles = new String[]{
                "姓名",
                "签到时间",
                "签退时间",
                "科室",
                "是否带教",
                "是否科主任",
                "是否教秘",
                "评价内容",
                "评分内容"
        };
        HSSFCell cellTitle2 = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle2 = rowThree.createCell(i);
            cellTitle2.setCellValue(titles[i]);
            cellTitle2.setCellStyle(styleCenter);
            sheet3.setColumnWidth(i, titles.length * 506);
        }
        int rowNu = 1;
        String[] result = null;
        if (lectureScanRegists2 != null && !lectureScanRegists2.isEmpty()) {
            for (int i = 0; i < lectureScanRegists2.size(); i++, rowNu++) {
                HSSFRow rowFive = sheet3.createRow(rowNu);
                ResLectureEvaDetail detail=evaDetailMap2.get(lectureScanRegists2.get(i).getOperUserFlow());
                if(detail==null) detail=new ResLectureEvaDetail();

                SysUser user=userBiz.readSysUser(lectureScanRegists2.get(i).getOperUserFlow());
                List<SysUserRole> userRoles=userRoleBiz.getByUserFlow(user.getUserFlow());
                String isTea="否"  ;
                String isHead="否";
                String isSecretary="否";
                if(userRoles!=null)
                {
                    for(SysUserRole userRole:userRoles)
                    {
                        if (StringUtil.isNotBlank(roleTeacher)&&roleTeacher.equals(userRole.getRoleFlow())) {
                            isTea="是";
                        }
                        if (StringUtil.isNotBlank(roleHead)&&roleHead.equals(userRole.getRoleFlow())) {
                            isHead="是";
                        }
                        if (StringUtil.isNotBlank(roleSecretary) && roleSecretary.equals(userRole.getRoleFlow())) {
                            isSecretary = "是";
                        }
                    }
                }
                result = new String[]{
                        user.getUserName(),
                        DateUtil.transTime(lectureScanRegists2.get(i).getScanTime()),
                        DateUtil.transTime(lectureScanRegists2.get(i).getScan2Time()),
                        user.getDeptName(),
                        isTea,
                        isHead,
                        isSecretary,
                        detail.getEvaContent(),
                        detail.getEvaScore(),
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFive.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(result[j]);
                }
            }
        }


        HSSFSheet sheet4 = wb.createSheet("已报名师资人员");
        HSSFRow rowFour = sheet4.createRow(0);//第一行
        titles = new String[]{
                "姓名",
                "科室",
                "是否带教",
                "是否科主任",
                "是否教秘"
        };
        HSSFCell cellTitle4 = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle4 = rowFour.createCell(i);
            cellTitle4.setCellValue(titles[i]);
            cellTitle4.setCellStyle(styleCenter);
            sheet4.setColumnWidth(i, titles.length * 506);
        }

        List<String> roles4=new ArrayList<>();

        String roleTeacher2= InitConfig.getSysCfg("res_teacher_role_flow");
        if (StringUtil.isNotBlank(roleTeacher2)) {
            roles4.add(roleTeacher2);
        }
        String roleHead2= InitConfig.getSysCfg("res_head_role_flow");
        if (StringUtil.isNotBlank(roleHead2)) {
            roles4.add(roleHead2);
        }
        String roleSecretary2= InitConfig.getSysCfg("res_secretary_role_flow");
        if (StringUtil.isNotBlank(roleSecretary2)) {
            roles4.add(roleSecretary2);
        }
        List<ResLectureScanRegist> lectureScanRegists3 = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, roles4);
        int row = 1;
        String[] resultList3 = null;
        if(lectureScanRegists3!=null&&lectureScanRegists3.size()>0) {
            for (int i = 0; i < lectureScanRegists3.size(); i++,row++) {
                HSSFRow rowEight = sheet4.createRow(row);
                SysUser user = userBiz.readSysUser(lectureScanRegists3.get(i).getOperUserFlow());
                List<SysUserRole> userRoles = userRoleBiz.getByUserFlow(user.getUserFlow());
                String isTea = "否";
                String isHead = "否";
                String isSecretary = "否";
                if (userRoles != null) {
                    for (SysUserRole userRole : userRoles) {
                        if (StringUtil.isNotBlank(roleTeacher) && roleTeacher.equals(userRole.getRoleFlow())) {
                            isTea = "是";
                        }
                        if (StringUtil.isNotBlank(roleHead) && roleHead.equals(userRole.getRoleFlow())) {
                            isHead = "是";
                        }
                        if (StringUtil.isNotBlank(roleSecretary) && roleSecretary.equals(userRole.getRoleFlow())) {
                            isSecretary = "是";
                        }
                    }
                }
                lectureScanRegists3.get(i).setOperUserName(user.getUserName());
                lectureScanRegists3.get(i).setTrainingSpeName(user.getDeptName());
                lectureScanRegists3.get(i).setIsScan(isTea);
                lectureScanRegists3.get(i).setIsRegist(isHead);
                lectureScanRegists3.get(i).setIsScan2(isSecretary);
                resultList3 = new String[]{
                    lectureScanRegists3.get(i).getOperUserName(),
                    lectureScanRegists3.get(i).getTrainingSpeName(),
                    lectureScanRegists3.get(i).getIsScan(),
                    lectureScanRegists3.get(i).getIsRegist(),
                    lectureScanRegists3.get(i).getIsScan2(),
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowEight.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(resultList3[j]);
                }
            }
        }

        String fileName = "导出信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());

    }
}
