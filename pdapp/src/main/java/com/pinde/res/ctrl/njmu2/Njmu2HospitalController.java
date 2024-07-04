package com.pinde.res.ctrl.njmu2;


import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.njmu2.INjmu2AppBiz;
import com.pinde.res.biz.njmu2.INjmu2TeacherBiz;
import com.pinde.sci.dao.base.SysUserDeptMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/njmu2/hospital")
public class Njmu2HospitalController {
    private static Logger logger = LoggerFactory.getLogger(Njmu2HospitalController.class);

    @Autowired
    private INjmu2AppBiz njmu2AppBiz;


    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
        logger.error(this.getClass().getCanonicalName() + " some error happened", e);
        return "res/njmu2/500";
    }

    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test() {
        return "res/njmu2/hospital/test";
    }

    @RequestMapping(value = {"/remember"}, method = {RequestMethod.GET})
    public String remember(String userFlow, String doctorFlow, String endDate, String startDate, String schDeptFlow, String funcFlow, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userFlow", userFlow);
        session.setAttribute("schDeptFlow", schDeptFlow);
        session.setAttribute("startDate", startDate);
        session.setAttribute("endDate", endDate);
        session.setAttribute("doctorFlow", doctorFlow);
        session.setAttribute("funcFlow", funcFlow);
        return "/res/njmu2/hospital/test";
    }
    @RequestMapping(value = {"/schDeptList"}, method = {RequestMethod.GET})
    public String schDeptList(String userFlow, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3030101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/njmu2/hospital/schDeptList";
        }
        //验证用户是否存在
        SysUser userinfo = njmu2AppBiz.getUserByFlow(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
        } else {
            List<SchDept> schDeptList = njmu2AppBiz.searchSchDeptList(userinfo.getOrgFlow());
            model.addAttribute("schDeptList", schDeptList);
        }
        return "/res/njmu2/hospital/schDeptList";
    }
    /**
     * 科室轮转查询
     */
    @RequestMapping(value = {"/docSchDept"}, method = RequestMethod.GET)
    public String docSchDept(String userFlow, String searchData,
                             HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3030101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/njmu2/hospital/schDept";
        }
        //验证用户是否存在
        SysUser userinfo = njmu2AppBiz.getUserByFlow(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
        } else {
            Map<String, String> searchMap = null;
            if (StringUtil.isNotBlank(searchData)) {
                try {
                    //为json字符串转码
                    System.err.println(searchData);
                    searchData = new String(searchData.getBytes("ISO8859-1"), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //转换json字符串为map对象
                searchMap = (Map<String, String>) JSON.parse(searchData);
                if (searchMap != null && !searchMap.isEmpty()) {
                    String startDate = searchMap.get("startDate");
                    String endDate = searchMap.get("endDate");
                    String deptFlow = searchMap.get("schDeptFlow");
                    String sessionNumber = searchMap.get("sessionNumber");
                    model.addAttribute("deptFlow", deptFlow);
                    if (StringUtil.isEmpty(startDate)) {
                        model.addAttribute("resultId", "3020304");
                        model.addAttribute("resultType", "开始时间标识符为空");
                        return "res/njmu2/hospital/schDept";
                    }

                    if (StringUtil.isEmpty(endDate)) {
                        model.addAttribute("resultId", "3020305");
                        model.addAttribute("resultType", "结束时间标识符为空");
                        return "res/njmu2/hospital/schDept";
                    }

                    if (endDate.compareTo(startDate) < 0) {
                        model.addAttribute("resultId", "3020308");
                        model.addAttribute("resultType", "结束时间小于开始时间");
                        return "res/njmu2/hospital/schDept";
                    }

                    List<String> titleDate = null;

                    titleDate = DateTimeUtil.getWeeksByTwoDate(startDate,endDate);

                    model.addAttribute("titleDate", titleDate);
                    List<SchArrangeResult> arrResultList = null;
                    List<SchDept> schDeptList = njmu2AppBiz.searchSchDeptList(userinfo.getOrgFlow());
                    arrResultList = njmu2AppBiz.searchArrangeResultByDateAndOrg(startDate, endDate, userinfo.getOrgFlow(),sessionNumber);
                    model.addAttribute("schDeptList", schDeptList);
                    if (arrResultList != null && arrResultList.size() > 0) {
                        Map<String, List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
                        for (SchArrangeResult sar : arrResultList) {
                            String schDeptFlow = sar.getSchDeptFlow();
                            String resultStartDate = sar.getSchStartDate();
                            String resultEndDate = sar.getSchEndDate();
                            if (StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)) {
                                List<String> weeks = DateTimeUtil.getWeeksByTwoDate(resultStartDate,resultEndDate);
                                if(weeks!=null){
                                    for(String week : weeks){
                                        String key = schDeptFlow+week;
                                        if(resultListMap.get(key)==null){
                                            List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
                                            sarList.add(sar);
                                            resultListMap.put(key,sarList);
                                        }else{
                                            resultListMap.get(key).add(sar);
                                        }
                                    }
                                }
                            }
                        }
                        model.addAttribute("resultListMap", resultListMap);
                    }
                }else{
                    model.addAttribute("resultId", "3030201");
                    model.addAttribute("resultType", "查询条件为空");
                }
            }
        }
        return "res/njmu2/hospital/schDept";
    }
    /**
     * 学员轮转详情
     */
    @RequestMapping(value = {"/doctorSchDetail"}, method = RequestMethod.GET)
    public String doctorSchDetail(String doctorFlow,      HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(doctorFlow)) {
            model.addAttribute("resultId", "3030101");
            model.addAttribute("resultType", "学员标识符为空");
            return "res/njmu2/hospital/doctorSchDetail";
        }
        //验证用户是否存在
        SysUser userinfo = njmu2AppBiz.getUserByFlow(doctorFlow);
        ResDoctor doctor = njmu2AppBiz.readResDoctor(doctorFlow);
        model.addAttribute("doctor", doctor);
        model.addAttribute("userinfo", userinfo);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "学员用户不存在");
        }else if(doctor==null){
            model.addAttribute("resultId", "3010107");
            model.addAttribute("resultType", "学员数据出错!");
        } else if(StringUtil.isBlank(userinfo.getOrgFlow()))
        {
            model.addAttribute("resultId", "30101011");
            model.addAttribute("resultType", "学员用户不存在");
        }else
        {
                List<SchDept> schDeptList = njmu2AppBiz.searchSchDeptList(userinfo.getOrgFlow());
                if (schDeptList != null && schDeptList.size() > 0) {
                    Map<String, SchDept> schDeptMap = new HashMap<String, SchDept>();
                    for (SchDept schDept : schDeptList) {
                        schDeptMap.put(schDept.getSchDeptFlow(), schDept);
                    }
                    model.addAttribute("schDeptMap", schDeptMap);
                }

            List<ResDoctorSchProcess> processList = njmu2AppBiz.searchProcessByDoctor(doctorFlow);
            if (processList != null && processList.size() > 0) {
                model.addAttribute("processList", processList);

                Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
                for (ResDoctorSchProcess process : processList) {
                    processMap.put(process.getSchResultFlow(), process);
                }
                model.addAttribute("processMap", processMap);
            }

            List<SchArrangeResult> resultList = njmu2AppBiz.searchSchArrangeResultByDoctor(doctorFlow);
            if (resultList != null && resultList.size() > 0) {
                model.addAttribute("resultList", resultList);
                model.addAttribute("resultFlow", resultList.get(0).getResultFlow());
                model.addAttribute("schDeptFlow", resultList.get(0).getSchDeptFlow());

                Map<String, String> perMap = njmu2AppBiz.getFinishPer(resultList, doctorFlow);
                model.addAttribute("perMap", perMap);
            }

        }
        return "res/njmu2/hospital/doctorSchDetail";
    }

    /**
     * 培训学员查询
     * @param userFlow
     * @param searchData
     * @param pageIndex
     * @param pageSize
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/trainDocList"}, method = {RequestMethod.GET})
    public String trainDocList(String userFlow, String searchData, Integer pageIndex, Integer pageSize, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3030101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/njmu2/admin/trainDocList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/njmu2/admin/trainDocList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/njmu2/admin/trainDocList";
        }
        //验证用户是否存在
        SysUser userinfo = njmu2AppBiz.getUserByFlow(userFlow);
        if (null == userinfo) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
        } else {
            model.addAttribute("pageIndex",pageIndex);
            model.addAttribute("pageSize",pageSize);
            Map<String, String> searchMap = new HashMap<String, String>();
            if (StringUtil.isNotBlank(searchData)) {
                try {
                    searchData = new String(searchData.getBytes("ISO8859-1"), "UTF-8");//为json字符串转码
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                searchMap = (Map<String, String>) JSON.parse(searchData);//转换json字符串为map对象
                ResDoctorExt doctorExt = new ResDoctorExt();
                SysUser user = new SysUser();
                user.setUserName(searchMap.get("userName"));
                doctorExt.setSysUser(user);

                doctorExt.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                doctorExt.setSessionNumber(searchMap.get("sessionNumber"));
                doctorExt.setDoctorCategoryId(searchMap.get("doctorCategoryId"));
                doctorExt.setTrainingSpeId(searchMap.get("trainingSpeId"));
                doctorExt.setOrgFlow(userinfo.getOrgFlow());
                PageHelper.startPage(pageIndex, pageSize);
                List<ResDoctorExt> doctorExtList = njmu2AppBiz.searchDocUser(doctorExt);
                if (doctorExtList != null && doctorExtList.size() > 0) {
                    model.addAttribute("doctorExtList", doctorExtList);

                    List<String> doctorFlows = new ArrayList<String>();
                    for (ResDoctorExt doctorExtTemp : doctorExtList) {
                        doctorFlows.add(doctorExtTemp.getDoctorFlow());
                    }

                    List<Map<String, Object>> resultCountMapList = njmu2AppBiz.countResultByUser(doctorFlows);
                    if (resultCountMapList != null && resultCountMapList.size() > 0) {
                        Map<String, Object> resultCountMap = new HashMap<String, Object>();
                        for (Map<String, Object> map : resultCountMapList) {
                            resultCountMap.put((String) map.get("key"), map.get("value"));
                        }
                        model.addAttribute("resultCountMap", resultCountMap);
                    }
                    List<Map<String, Object>> processCountMapList = njmu2AppBiz.countProcessByUser(doctorFlows);
                    if (processCountMapList != null && processCountMapList.size() > 0) {
                        Map<String, Object> processCountMap = new HashMap<String, Object>();
                        for (Map<String, Object> map : processCountMapList) {
                            processCountMap.put((String) map.get("key"), map.get("value"));
                        }
                        model.addAttribute("processCountMap", processCountMap);
                    }
                }
                //数据数量
                model.addAttribute("dataCount", PageHelper.total);
            }
        }
        return "res/njmu2/hospital/trainDocList";
    }
}

