package com.pinde.sci.ctrl.lcjn;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ClassUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.lcjn.ILcjnDoctorOrderInfoBiz;
import com.pinde.sci.biz.lcjn.ILcjnDoctorSignBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.core.common.enums.LcjnAuditStatusEnum;
import com.pinde.core.common.enums.LcjnDoctorScoreEnum;
import com.pinde.sci.model.mo.LcjnCourseInfo;
import com.pinde.sci.model.mo.LcjnCourseTime;
import com.pinde.sci.model.mo.LcjnDoctorCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/lcjn/lcjnDoctorTrainInfo")
public class LcjnDoctorTrainInfoController extends GeneralController{

    @Autowired
    private ILcjnDoctorSignBiz lcjnDoctorSignBiz;
    @Autowired
    private ILcjnDoctorOrderInfoBiz lcjnDoctorOrderInfoBiz;


    /**
     * 签到管理列表
     * @param userName
     * @param courseFlow
     * @param sign
     * @param currentPage2
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/signList", method = {RequestMethod.GET, RequestMethod.POST})
    public String doctorSignList(String userName, String courseFlow, String sign, Integer currentPage2, HttpServletRequest request, Model model){
        Map<String,String> pramMap=new HashMap<>();
        pramMap.put("userName",userName);
        pramMap.put("courseFlow",courseFlow);
        pramMap.put("sign",sign);
        PageHelper.startPage(currentPage2,getPageSize(request));
        List<Map<String,Object>> doctorSignList=lcjnDoctorSignBiz.selectDoctorSignList(pramMap);
        if(doctorSignList!=null&&doctorSignList.size()>0){
            for (Map<String,Object> mapTemp:doctorSignList) {
                if(StringUtil.isNotBlank((String)mapTemp.get("ST"))){
                    String[] signList=((String) mapTemp.get("ST")).split(",");
                    if(signList!=null&&signList.length>0){
                        Arrays.sort(signList);
                        mapTemp.put("signs",signList);
                        StringBuffer sb = new StringBuffer();
                        for(int i = 0; i < signList.length; i++){
                            sb. append(signList[i]);
                            sb.append(",");
                        }
                        String newST=sb.toString();
                        newST=newST.substring(0,newST.length()-1);
                        mapTemp.put("ST",newST);
                    }
                }
            }
        }
        model.addAttribute("doctorSignList",doctorSignList);
        model.addAttribute("courseFlow",courseFlow);
        return "/lcjn/doctorInfoManage/signManager";
    }
    @RequestMapping(value = "/exportSign")
    public void exportSign(String userName, String courseFlow, String sign,HttpServletResponse response)throws Exception {
        String[] titles;//导出列表头信息
        Map<String,String> pramMap=new HashMap<>();
        pramMap.put("userName",userName);
        pramMap.put("courseFlow",courseFlow);
        pramMap.put("sign",sign);
        List<Map<String,Object>> doctorSignList=lcjnDoctorSignBiz.selectDoctorSignList(pramMap);
        if(doctorSignList!=null&&doctorSignList.size()>0){
            for (Map<String,Object> mapTemp:doctorSignList) {
                if(StringUtil.isNotBlank((String)mapTemp.get("ST"))){
                    String[] signList=((String) mapTemp.get("ST")).split(",");
                    if(signList!=null&&signList.length>0){
                        Arrays.sort(signList);
                        StringBuffer sb = new StringBuffer();
                        for(int i = 0; i < signList.length; i++){
                            sb. append(signList[i]);
                            sb.append(",");
                        }
                        String newST=sb.toString();
                        newST=newST.substring(0,newST.length()-1);
                        mapTemp.put("ST",newST);
                    }
                }
            }
        }
        titles = new String[]{
                "USER_CODE:用户名",
                "USER_NAME:姓名",
                "LCJN_SPE_NAME:培训专业",
                "ORG_NAME:工作单位",
                "DEPT_NAME:所在科室",
                "ST:签到时间"
        };
        ExcleUtile.exportSimpleExcleByObjs(titles, doctorSignList, response.getOutputStream());
        String fileName = "培训签到列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    /**
     * 预约学员信息列表
     * @param courseFlow
     * @param auditStatusId
     * @param currentPage1
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/orderInfoList", method = {RequestMethod.GET, RequestMethod.POST})
    public String doctorOrderInfoList(String courseFlow,String courseName, String auditStatusId, Integer currentPage1, HttpServletRequest request, Model model){
        Map<String,String> pramMap=new HashMap<>();
        pramMap.put("courseFlow",courseFlow);
        pramMap.put("auditStatusId",auditStatusId);
        PageHelper.startPage(currentPage1,getPageSize(request));
        List<Map<String,Object>> doctorOrderInfoList=lcjnDoctorOrderInfoBiz.selectDoctorOrderInfoList(pramMap);
        model.addAttribute("doctorOrderInfoList",doctorOrderInfoList);
        model.addAttribute("courseFlow",courseFlow);
        model.addAttribute("courseName",courseName);
        model.addAttribute("currentPage1",currentPage1);
        return "/lcjn/doctorInfoManage/doctorOrderedInfo";
    }

    /**
     * 预约学员批量审核操作
     * @param jsonData
     * @param reason
     * @return
     */
    @RequestMapping("/auditAppoint")
    @ResponseBody
    public String auditAppoint(String jsonData,String reason){
        Map<String,Object> mp = JSON.parseObject(jsonData,Map.class);
        List<String> recordLst = (List<String>)mp.get("recordLst");
        String auditStatusId = (String)mp.get("auditStatusId");
        int num = 0;
        if(null != recordLst && recordLst.size() > 0){
            for (int i = 0; i < recordLst.size(); i++) {
                num += lcjnDoctorOrderInfoBiz.batchAuditInfo(recordLst.get(i),auditStatusId,reason);
            }
        }
        if (num > 0) {
            return "信息审核成功！";
        }
        return "信息审核失败！";

    }

    /**
     * 展示本院学员列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/showLocalDoctors", method = {RequestMethod.GET, RequestMethod.POST})
    public String showLocalDoctors(String courseFlow,String courseName,Model model)throws IOException{
        List<Map<String,Object>> localDoctors=lcjnDoctorOrderInfoBiz.selectLocalDoctors(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("localDoctors",localDoctors);
        model.addAttribute("courseFlow",courseFlow);
        model.addAttribute("courseName",java.net.URLDecoder.decode(courseName,"UTF-8"));
        return "/lcjn/doctorInfoManage/addDoctor";
    }

    @RequestMapping("/checkOrderNum")
    @ResponseBody
    public String checkOrderNum(String jsonData,Model model) throws IOException{
        Map<String,Object> mp = JSON.parseObject(jsonData,Map.class);
        List<Map<String,String>> doctors = (List<Map<String,String>>)mp.get("datas");
        String courseFlow=(String)mp.get("courseFlow");
        int num=lcjnDoctorOrderInfoBiz.countOrderNum(courseFlow);
        if(null != doctors && doctors.size()>num){
            return com.pinde.core.common.GlobalConstant.RECORD_STATUS_N;
        }
        return com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y;
    }

    /**
     * 查询课程剩余预约人数
     * @param courseFlow
     * @return
     */
    @RequestMapping("/selectOrderNum")
    @ResponseBody
    public String selectOrderNum(String courseFlow){
        int num=lcjnDoctorOrderInfoBiz.countOrderNum(courseFlow);
        if(num==0){
            return com.pinde.core.common.GlobalConstant.RECORD_STATUS_N;
        }
        return com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y;
    }

    /**
     * 管理员添加学员
     * @param userFlow
     * @param userCode
     * @param userName
     * @param courseFlow
     * @param courseName
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("/addLocalDoctors")
    @ResponseBody
    public String addLocalDoctors(String userFlow,String userCode,String userName,String courseFlow,String courseName,Model model) throws IOException{
        courseName = java.net.URLDecoder.decode(courseName,"UTF-8");
        userName=java.net.URLDecoder.decode(userName,"UTF-8");
        String trainStartTime="";
        String trainEndTime="";
        String successMsg="";
        String failMsg="";
        //查询当前课程的培训时间
        List<LcjnCourseTime> lcjnCourseTime=lcjnDoctorOrderInfoBiz.selectCourseTime(courseFlow);
        int count=0;
                //查询时间重叠且状态为通过
                count=lcjnDoctorOrderInfoBiz.countOrderTime(userFlow,courseFlow);
                String msg=userName+" "+userCode;
                List<String> courseFlowList=new ArrayList<>();
                if(lcjnCourseTime!=null&lcjnCourseTime.size()>0){
                    for(int m=0;m<lcjnCourseTime.size();m++){
                        trainStartTime=lcjnCourseTime.get(m).getTrainStartDate()+" "+lcjnCourseTime.get(m).getStartTime();
                        trainEndTime=lcjnCourseTime.get(m).getTrainEndDate()+" "+lcjnCourseTime.get(m).getEndTime();
                        //查询时间重叠且状态为待审核
                        List<String> courseFlowListTemp=lcjnDoctorOrderInfoBiz.selectOrderAndPassing(userFlow,courseFlow,trainStartTime,trainEndTime);
                        if(courseFlowListTemp!=null&&courseFlowListTemp.size()>0){
                            for (String cf:courseFlowListTemp) {
                                if(StringUtil.isNotBlank(cf)){
                                    courseFlowList.add(cf);
                                }
                            }
                        }
                    }
                }
                //根据courseFlow和doctorFlow查找学员课程预约信息
                List<LcjnDoctorCourse> doctorCourseList=lcjnDoctorOrderInfoBiz.selectDoctorCourse(courseFlow,userFlow);
                if(count>0){
                    failMsg="<font style='color: red'>"+msg+",因成功预约培训课程的培训时间与本次课程培训时间有重叠导入失败!</font><br/>";
                }else if(courseFlowList!=null&&courseFlowList.size()>0){
                    //待审核且时间重复预约，置为不通过
                    LcjnDoctorCourse lcjnDoctorCourse=new LcjnDoctorCourse();
                    lcjnDoctorCourse.setDoctorFlow(userFlow);
                    lcjnDoctorCourse.setAuditStatusId(LcjnAuditStatusEnum.UnPassed.getId());
                    lcjnDoctorCourse.setAuditStatusName(LcjnAuditStatusEnum.UnPassed.getName());
                    GeneralMethod.setRecordInfo(lcjnDoctorCourse,false);
                    lcjnDoctorOrderInfoBiz.updateDoctorCourse(lcjnDoctorCourse,courseFlowList);
                    if(doctorCourseList!=null&&doctorCourseList.size()>0){
                        for(int j = 0; j < doctorCourseList.size(); j++){
                            lcjnDoctorCourse=doctorCourseList.get(j);
                            lcjnDoctorCourse.setAuditStatusId(LcjnAuditStatusEnum.Passed.getId());
                            lcjnDoctorCourse.setAuditStatusName(LcjnAuditStatusEnum.Passed.getName());
                            lcjnDoctorCourse.setEnteringStatusId(LcjnDoctorScoreEnum.NotEntered.getId());
                            lcjnDoctorCourse.setEnteringStatusName(LcjnDoctorScoreEnum.NotEntered.getName());
                            GeneralMethod.setRecordInfo(lcjnDoctorCourse,false);
                            lcjnDoctorOrderInfoBiz.updateDoctorCourse(lcjnDoctorCourse,null);
                        }
                    }else {
                        lcjnDoctorCourse=new LcjnDoctorCourse();
                        lcjnDoctorCourse.setRecordFlow(PkUtil.getUUID());
                        lcjnDoctorCourse.setCourseFlow(courseFlow);
                        lcjnDoctorCourse.setCourseName(courseName);
                        lcjnDoctorCourse.setDoctorFlow(userFlow);
                        lcjnDoctorCourse.setDoctorName(userName);
                        lcjnDoctorCourse.setAppointTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                        lcjnDoctorCourse.setAuditStatusId(LcjnAuditStatusEnum.Passed.getId());
                        lcjnDoctorCourse.setAuditStatusName(LcjnAuditStatusEnum.Passed.getName());
                        lcjnDoctorCourse.setEnteringStatusId(LcjnDoctorScoreEnum.NotEntered.getId());
                        lcjnDoctorCourse.setEnteringStatusName(LcjnDoctorScoreEnum.NotEntered.getName());
                        GeneralMethod.setRecordInfo(lcjnDoctorCourse,true);
                        lcjnDoctorOrderInfoBiz.addDoctorCourse(lcjnDoctorCourse);
                    }
                    successMsg="<font style='color: green'>"+msg+",导入成功!</font><br/>";
                }else if(doctorCourseList!=null&&doctorCourseList.size()>0){//当前课程已预约过 做更新操作
                    for(int j = 0; j < doctorCourseList.size(); j++){
                        LcjnDoctorCourse lcjnDoctorCourse=doctorCourseList.get(j);
                        lcjnDoctorCourse.setAuditStatusId(LcjnAuditStatusEnum.Passed.getId());
                        lcjnDoctorCourse.setAuditStatusName(LcjnAuditStatusEnum.Passed.getName());
                        lcjnDoctorCourse.setEnteringStatusId(LcjnDoctorScoreEnum.NotEntered.getId());
                        lcjnDoctorCourse.setEnteringStatusName(LcjnDoctorScoreEnum.NotEntered.getName());
                        GeneralMethod.setRecordInfo(lcjnDoctorCourse,false);
                        lcjnDoctorOrderInfoBiz.updateDoctorCourse(lcjnDoctorCourse,null);
                    }
                    successMsg="<font style='color: green'>"+msg+",导入成功!</font><br/>";
                }else {
                    LcjnDoctorCourse lcjnDoctorCourse=new LcjnDoctorCourse();
                    lcjnDoctorCourse.setRecordFlow(PkUtil.getUUID());
                    lcjnDoctorCourse.setCourseFlow(courseFlow);
                    lcjnDoctorCourse.setCourseName(courseName);
                    lcjnDoctorCourse.setDoctorFlow(userFlow);
                    lcjnDoctorCourse.setDoctorName(userName);
                    lcjnDoctorCourse.setAppointTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    lcjnDoctorCourse.setAuditStatusId(LcjnAuditStatusEnum.Passed.getId());
                    lcjnDoctorCourse.setAuditStatusName(LcjnAuditStatusEnum.Passed.getName());
                    lcjnDoctorCourse.setEnteringStatusId(LcjnDoctorScoreEnum.NotEntered.getId());
                    lcjnDoctorCourse.setEnteringStatusName(LcjnDoctorScoreEnum.NotEntered.getName());
                    GeneralMethod.setRecordInfo(lcjnDoctorCourse,true);
                    lcjnDoctorOrderInfoBiz.addDoctorCourse(lcjnDoctorCourse);
                    successMsg="<font style='color: green'>"+msg+",导入成功!</font><br/>";
                }
        if(StringUtil.isNotBlank(successMsg)){
            return successMsg;
        }
        return failMsg;
    }
//    @RequestMapping("/addLocalDoctors")
//    @ResponseBody
//    public String addLocalDoctors(String jsonData,Model model) throws IOException{
//        Map<String,Object> mp = JSON.parseObject(jsonData,Map.class);
//        List<Map<String,String>> doctors = (List<Map<String,String>>)mp.get("datas");
//        String courseFlow=(String)mp.get("courseFlow");
//        String courseName=(String)mp.get("courseName");
//        courseName = java.net.URLDecoder.decode(courseName,"UTF-8");
//        List<String> successList=new ArrayList<>();
//        List<String> failList=new ArrayList<>();
////        List<String> failNumList=new ArrayList<>();
//        String trainStartTime="";
//        String trainEndTime="";
//        //查询当前课程的培训时间
//        List<LcjnCourseTime> lcjnCourseTime=lcjnDoctorOrderInfoBiz.selectCourseTime(courseFlow);
//        int count=0;
//        if(null != doctors && doctors.size() > 0){
//            for (int i = 0; i < doctors.size(); i++) {
//                String username=java.net.URLDecoder.decode(doctors.get(i).get("userName"),"UTF-8");
//                //查询时间重叠且状态为通过
//                count=lcjnDoctorOrderInfoBiz.countOrderTime(doctors.get(i).get("userFlow"),courseFlow);
//                String msg=username+" "+doctors.get(i).get("userCode");
//                List<String> courseFlowList=new ArrayList<>();
//                if(lcjnCourseTime!=null&lcjnCourseTime.size()>0){
//                    for(int m=0;m<lcjnCourseTime.size();m++){
//                        trainStartTime=lcjnCourseTime.get(m).getTrainStartDate()+" "+lcjnCourseTime.get(m).getStartTime();
//                        trainEndTime=lcjnCourseTime.get(m).getTrainEndDate()+" "+lcjnCourseTime.get(m).getEndTime();
//                        //查询时间重叠且状态为待审核
//                        List<String> courseFlowListTemp=lcjnDoctorOrderInfoBiz.selectOrderAndPassing(doctors.get(i).get("userFlow"),courseFlow,trainStartTime,trainEndTime);
//                        if(courseFlowListTemp!=null&&courseFlowListTemp.size()>0){
//                            for (String cf:courseFlowListTemp) {
//                                if(StringUtil.isNotBlank(cf)){
//                                    courseFlowList.add(cf);
//                                }
//                            }
//                        }
//                    }
//                }
//                //根据courseFlow和doctorFlow查找学员课程预约信息
//                List<LcjnDoctorCourse> doctorCourseList=lcjnDoctorOrderInfoBiz.selectDoctorCourse(courseFlow,doctors.get(i).get("userFlow"));
//                if(count>0){
//                    failList.add(msg);
//                }else if(courseFlowList!=null&&courseFlowList.size()>0){
//                    //待审核且时间重复预约，置为不通过
//                    LcjnDoctorCourse lcjnDoctorCourse=new LcjnDoctorCourse();
//                    lcjnDoctorCourse.setDoctorFlow(doctors.get(i).get("userFlow"));
//                    lcjnDoctorCourse.setAuditStatusId(LcjnAuditStatusEnum.UnPassed.getId());
//                    lcjnDoctorCourse.setAuditStatusName(LcjnAuditStatusEnum.UnPassed.getName());
//                    GeneralMethod.setRecordInfo(lcjnDoctorCourse,false);
//                    lcjnDoctorOrderInfoBiz.updateDoctorCourse(lcjnDoctorCourse,courseFlowList);
//                    if(doctorCourseList!=null&&doctorCourseList.size()>0){
//                        for(int j = 0; j < doctorCourseList.size(); j++){
//                            lcjnDoctorCourse=doctorCourseList.get(j);
//                            lcjnDoctorCourse.setAuditStatusId(LcjnAuditStatusEnum.Passed.getId());
//                            lcjnDoctorCourse.setAuditStatusName(LcjnAuditStatusEnum.Passed.getName());
//                            lcjnDoctorCourse.setEnteringStatusId(LcjnDoctorScoreEnum.NotEntered.getId());
//                            lcjnDoctorCourse.setEnteringStatusName(LcjnDoctorScoreEnum.NotEntered.getName());
//                            GeneralMethod.setRecordInfo(lcjnDoctorCourse,false);
//                            lcjnDoctorOrderInfoBiz.updateDoctorCourse(lcjnDoctorCourse,null);
//                        }
//                    }else {
//                        lcjnDoctorCourse=new LcjnDoctorCourse();
//                        lcjnDoctorCourse.setRecordFlow(PkUtil.getUUID());
//                        lcjnDoctorCourse.setCourseFlow(courseFlow);
//                        lcjnDoctorCourse.setCourseName(courseName);
//                        lcjnDoctorCourse.setDoctorFlow(doctors.get(i).get("userFlow"));
//                        lcjnDoctorCourse.setDoctorName(username);
//                        lcjnDoctorCourse.setAppointTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//                        lcjnDoctorCourse.setAuditStatusId(LcjnAuditStatusEnum.Passed.getId());
//                        lcjnDoctorCourse.setAuditStatusName(LcjnAuditStatusEnum.Passed.getName());
//                        lcjnDoctorCourse.setEnteringStatusId(LcjnDoctorScoreEnum.NotEntered.getId());
//                        lcjnDoctorCourse.setEnteringStatusName(LcjnDoctorScoreEnum.NotEntered.getName());
//                        GeneralMethod.setRecordInfo(lcjnDoctorCourse,true);
//                        lcjnDoctorOrderInfoBiz.addDoctorCourse(lcjnDoctorCourse);
//                    }
//                    successList.add(msg);
//                }else if(doctorCourseList!=null&&doctorCourseList.size()>0){//当前课程已预约过 做更新操作
//                    for(int j = 0; j < doctorCourseList.size(); j++){
//                        LcjnDoctorCourse lcjnDoctorCourse=doctorCourseList.get(j);
//                        lcjnDoctorCourse.setAuditStatusId(LcjnAuditStatusEnum.Passed.getId());
//                        lcjnDoctorCourse.setAuditStatusName(LcjnAuditStatusEnum.Passed.getName());
//                        lcjnDoctorCourse.setEnteringStatusId(LcjnDoctorScoreEnum.NotEntered.getId());
//                        lcjnDoctorCourse.setEnteringStatusName(LcjnDoctorScoreEnum.NotEntered.getName());
//                        GeneralMethod.setRecordInfo(lcjnDoctorCourse,false);
//                        lcjnDoctorOrderInfoBiz.updateDoctorCourse(lcjnDoctorCourse,null);
//                    }
//                    successList.add(msg);
//                }else {
//                    LcjnDoctorCourse lcjnDoctorCourse=new LcjnDoctorCourse();
//                    lcjnDoctorCourse.setRecordFlow(PkUtil.getUUID());
//                    lcjnDoctorCourse.setCourseFlow(courseFlow);
//                    lcjnDoctorCourse.setCourseName(courseName);
//                    lcjnDoctorCourse.setDoctorFlow(doctors.get(i).get("userFlow"));
//                    lcjnDoctorCourse.setDoctorName(username);
//                    lcjnDoctorCourse.setAppointTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//                    lcjnDoctorCourse.setAuditStatusId(LcjnAuditStatusEnum.Passed.getId());
//                    lcjnDoctorCourse.setAuditStatusName(LcjnAuditStatusEnum.Passed.getName());
//                    lcjnDoctorCourse.setEnteringStatusId(LcjnDoctorScoreEnum.NotEntered.getId());
//                    lcjnDoctorCourse.setEnteringStatusName(LcjnDoctorScoreEnum.NotEntered.getName());
//                    GeneralMethod.setRecordInfo(lcjnDoctorCourse,true);
//                    lcjnDoctorOrderInfoBiz.addDoctorCourse(lcjnDoctorCourse);
//                    successList.add(msg);
//                }
//            }
//        }
////        model.addAttribute("failList",failList);
////        model.addAttribute("successList",successList);
//        String successMsg="条学员信息导入成功！<br/>";
//        String failMsg="";
//        String sNum="0";
//        String failDoctor="";
//        if(successList!=null&&successList.size()>0){
//            sNum=successList.size()+"";
//        }
//        successMsg=sNum+successMsg;
//        if(failList!=null&&failList.size()>0){
//            failMsg="条学员信息因成功预约培训课程的培训时间与本次课程培训时间有重叠导入失败。<br/>";
//            sNum=failList.size()+"";
//            for(int i=0;i<failList.size();i++){
//                failDoctor=failDoctor+"<font style='color: red'>"+failList.get(i)+"</font><br/>";
//            }
//            failMsg=sNum+failMsg+failDoctor;
//        }
////        return "/lcjn/doctorInfoManage/importResoult";
//        return successMsg+failMsg;
//    }
    /**
     * 导出学员预约信息
     * @param courseFlow
     * @param auditStatusId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportDoctorInfo")
    public void exportDoctorInfo(String courseFlow,String auditStatusId,HttpServletResponse response)throws Exception {
        Map<String,String> pramMap=new HashMap<>();
        pramMap.put("courseFlow",courseFlow);
        pramMap.put("auditStatusId",auditStatusId);
        List<Map<String,Object>> doctorOrderInfoList=lcjnDoctorOrderInfoBiz.selectDoctorOrderInfoList(pramMap);
        String[] titles;//导出列表头信息
        titles = new String[]{
                "USER_CODE:用户名",
                "USER_NAME:姓名",
                "SEX_NAME:性别",
                "LCJN_SPE_NAME:培训专业",
                "ID_NO:身份证号",
                "ORG_NAME:工作单位",
                "DEPT_NAME:所在科室",
                "USER_PHONE:联系方式",
                "AUDIT_STATUS_NAME:状态"
        };
        ExcleUtile.exportSimpleExcleByObjs(titles, doctorOrderInfoList, response.getOutputStream());
        String fileName = "学员信息列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
    /**
     * 学员成绩列表
     * @param courseFlow
     * @param appointDoctorName
     * @param enteringStatusId
     * @param currentPage3
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/doctorScoreList", method = {RequestMethod.GET, RequestMethod.POST})
    public String doctorScoreList(String courseFlow, String appointDoctorName,String enteringStatusId, Integer currentPage3, HttpServletRequest request, Model model) {
        PageHelper.startPage(currentPage3,getPageSize(request));
        List<Map<String,Object>> doctorScoreList=lcjnDoctorOrderInfoBiz.selectDoctorScoreList(courseFlow,appointDoctorName,enteringStatusId,null);
        model.addAttribute("doctorScoreList",doctorScoreList);
        model.addAttribute("courseFlow",courseFlow);
        model.addAttribute("currentPage3",currentPage3);
        return "/lcjn/doctorInfoManage/scoreManager";
    }

    /**
     * 查询一条学员成绩记录并展示修改页面
     * @param courseFlow
     * @param doctorFlow
     * @return
     */
    @RequestMapping(value = "/showDoctorScore")
    public String showDoctorScore(String courseFlow,String currentPage3, String doctorFlow,Model model){
        List<Map<String,Object>> doctorScoreList=lcjnDoctorOrderInfoBiz.selectDoctorScoreList(courseFlow,null,null,doctorFlow);
        Map<String,Object> doctorScore=new HashMap<>();
        if(doctorScoreList!=null&&doctorScoreList.size()>0){
            doctorScore=doctorScoreList.get(0);
        }
        model.addAttribute("doctorScore",doctorScore);
        model.addAttribute("currentPage3",currentPage3);
        return "/lcjn/doctorInfoManage/editDoctorScore";
    }

    /**
     * 编辑学员成绩
     * @param recordFlow
     * @param examScore
     * @return
     */
    @RequestMapping(value = "/editDoctorScore")
    @ResponseBody
    public String editDoctorScore(String recordFlow,String examScore) throws IOException{
        LcjnDoctorCourse lcjnDoctorCourse=new LcjnDoctorCourse();
        lcjnDoctorCourse.setRecordFlow(recordFlow);
        lcjnDoctorCourse.setExamScore(java.net.URLDecoder.decode(examScore,"UTF-8"));
        lcjnDoctorCourse.setEnteringStatusId(LcjnDoctorScoreEnum.HasBeenEntered.getId());
        lcjnDoctorCourse.setEnteringStatusName(LcjnDoctorScoreEnum.HasBeenEntered.getName());
        GeneralMethod.setRecordInfo(lcjnDoctorCourse,false);
        int num=0;
        num=lcjnDoctorOrderInfoBiz.editDoctorCourse(lcjnDoctorCourse);
        if (num > 0) {
            return "保存成功！";
        }
        return "保存失败！";
    }

    /**
     * 成绩发布
     * @param courseFlow
     * @return
     */
    @RequestMapping(value = "/releaseScore")
    @ResponseBody
    public String releaseScore(String courseFlow){
        LcjnCourseInfo lcjnCourseInfo=new LcjnCourseInfo();
        lcjnCourseInfo.setCourseFlow(courseFlow);
        lcjnCourseInfo.setIsScoreReleased(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        GeneralMethod.setRecordInfo(lcjnCourseInfo,false);
        int num=0;
        num=lcjnDoctorOrderInfoBiz.editCourseInfo(lcjnCourseInfo);
        if (num > 0) {
            return "成绩发布成功！";
        }
        return "成绩发布失败！";
    }

    /**
     * 培训成绩导出
     * @param courseFlow
     * @param doctorName
     * @param enteringStatusId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportScore")
    public void exportScore(String courseFlow, String doctorName,String enteringStatusId,HttpServletResponse response)throws Exception {
        String[] titles;//导出列表头信息
        List<Map<String,Object>> doctorScoreList=lcjnDoctorOrderInfoBiz.selectDoctorScoreList(courseFlow,doctorName,enteringStatusId,null);
        titles = new String[]{
                "USER_CODE:用户名",
                "USER_NAME:姓名",
                "LCJN_SPE_NAME:培训专业",
                "ORG_NAME:工作单位",
                "DEPT_NAME:所在科室",
                "EXAM_SCORE:成绩"
        };
        ExcleUtile.exportSimpleExcleByObjs(titles, doctorScoreList, response.getOutputStream());
        String fileName = "培训成绩列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
    /**
     * 展示导入成绩页面
     * @return
     */
    @RequestMapping(value="/showImportScore")
    public String showImportScore(String courseFlow,Model model){
        model.addAttribute("courseFlow",courseFlow);
        return "/lcjn/doctorInfoManage/importScore";
    }
    /**
     * 导入成绩
     * @param file
     * @return
     */
    @RequestMapping(value="importScore")
    @ResponseBody
    public String importScore(MultipartFile file,String courseFlow){
        if(file.getSize() > 0){
            try{
                lcjnDoctorOrderInfoBiz.importScoreFromExcel(file,courseFlow);
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
            }
        }
        return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED;
    }

    private static Logger logger = LoggerFactory.getLogger(LcjnDoctorTrainInfoController.class);

}
