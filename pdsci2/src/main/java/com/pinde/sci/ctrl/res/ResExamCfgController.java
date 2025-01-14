package com.pinde.sci.ctrl.res;


import com.alibaba.fastjson.JSON;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResScoreBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sch.ISchExamCfgBiz;
import com.pinde.sci.biz.sch.ISchExamScoreQueryBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.core.model.SchDept;
import com.pinde.core.model.SchExamArrangement;
import com.pinde.core.model.SchExamDoctorArrangement;
import com.pinde.core.model.SchExamStandardDept;
import com.pinde.core.model.ResDoctorExt;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Controller
@RequestMapping("/res/examCfg")
public class ResExamCfgController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(ResExamCfgController.class);

    @Autowired
    private  ISchExamCfgBiz examCfgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private ISchExamScoreQueryBiz scoreQueryBiz;
    @Autowired
    private ISchDeptBiz schDeptBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @RequestMapping(value="/examArrangMent")
    public String examArrangMent(Model model) {
        return "res/hospital/examCfg/examCfgMain";
    }
    @RequestMapping(value="/edit")
    public String edit(Model model,String arrangeFlow) {
        SchExamArrangement ment=examCfgBiz.readByFlow(arrangeFlow);
        model.addAttribute("ment",ment);
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        model.addAttribute("currentOrg",currentOrg);
        List<SchExamStandardDept> depts=examCfgBiz.readStandardDeptsByFlow(arrangeFlow);
        if(depts!=null&&depts.size()>0)
        {
            List<String> standardList=new ArrayList<>();
            for(SchExamStandardDept dept:depts)
            {
                standardList.add(dept.getStandardDeptId());
            }
            model.addAttribute("standardList",standardList);
        }
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.StandardDept.getId());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        Map<String,String> paramMap = new HashMap();
        paramMap.put("orgFlow",currentUser.getOrgFlow());
        List<SchDept> schDepts = schDeptBiz.searchSchDeptHadRelated(paramMap);
        model.addAttribute("schDepts",schDepts);
        return "res/hospital/examCfg/edit";
    }
    @RequestMapping(value="/list")
    public String list(Model model,Integer currentPage ,HttpServletRequest request,
                       SchExamArrangement schExamArrangement  ){
        SysUser sysuser=GlobalContext.getCurrentUser();
        schExamArrangement.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        schExamArrangement.setOrgFlow(sysuser.getOrgFlow());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<SchExamArrangement> list=examCfgBiz.searchList(schExamArrangement);
        model.addAttribute("list",list);
        return "res/hospital/examCfg/list";
    }
    @RequestMapping(value="/updateArrangement")
    @ResponseBody
    public String updateArrangement(SchExamArrangement schExamArrangement ,String[] standardDeptId ){
        if(StringUtil.isBlank(schExamArrangement.getArrangeFlow())){
            SchExamArrangement tempArrangement = new SchExamArrangement();
            tempArrangement.setOrgFlow(schExamArrangement.getOrgFlow());
            tempArrangement.setSessionNumber(schExamArrangement.getSessionNumber());
            tempArrangement.setTrainingSpeId(schExamArrangement.getTrainingSpeId());
            tempArrangement.setAssessmentYear(schExamArrangement.getAssessmentYear());
            List<SchExamArrangement> list = examCfgBiz.searchList(tempArrangement);
            if(list != null && list.size() > 0){
                return "cannotInsert";
            }
        }
        int result = examCfgBiz.updateArrangement(schExamArrangement, standardDeptId, com.pinde.core.common.GlobalConstant.FLAG_Y);
        if(result == 0)
        {
            return "操作失败！";
        }
        return "操作成功！";
    }
    @RequestMapping(value="/updateCfg")
    @ResponseBody
    public String updateCfg(Model model,  SchExamArrangement schExamArrangement  ){
        //删除时，校验是否已有学生考过试，并有成绩的，无法删除
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(schExamArrangement.getRecordStatus()))
        {
            int checkCount=examCfgBiz.checkHaveExam(schExamArrangement.getArrangeFlow());
            if(checkCount>0)
                return "已有学员参加过考试，无法删除！";
        }
        int result=examCfgBiz.updateCfg(schExamArrangement);
        if(result==0)
        {
            return "操作失败！";
        }
        return "操作成功！";
    }
    /**
     * 学员角色年度理论考试
     * @param model
     * @return
     */
    @RequestMapping(value="/theoreticalExam")
    public String theoreticalExam(Model model) {
        SysUser currentUser = GlobalContext.getCurrentUser();
        ResDoctor resDoctor = doctorBiz.readDoctor(currentUser.getUserFlow());
        if(resDoctor != null){
            model.addAttribute("resDoctor",resDoctor);
            String orgFlow="";
            if(resDoctor!=null) {
                if (StringUtil.isNotBlank(resDoctor.getSecondOrgFlow())) {
                    orgFlow = resDoctor.getSecondOrgFlow();
                } else {
                    orgFlow = resDoctor.getOrgFlow();
                }
            }
            SchExamArrangement schExamArrangement = new SchExamArrangement();
            schExamArrangement.setOrgFlow(orgFlow);
            schExamArrangement.setSessionNumber(resDoctor.getSessionNumber());
//        schExamArrangement.setTrainingTypeId(resDoctor.getTrainingTypeId());
            schExamArrangement.setTrainingSpeId(resDoctor.getTrainingSpeId());
            List<SchExamArrangement> examArrangements = examCfgBiz.searchList(schExamArrangement);
            //查询条件
            Map<String,Object> param = new HashMap<>();
            List<String> userFlows = new ArrayList<>();
            userFlows.add(currentUser.getUserFlow());
            param.put("orgFlow",resDoctor.getOrgFlow());
            param.put("userFlows",userFlows);
            List<SchExamDoctorArrangement> doctorArrangements = scoreQueryBiz.getDoctorArrangements(param);
            if(doctorArrangements != null && doctorArrangements.size() > 0){
                Map<String,SchExamDoctorArrangement> doctorArrangementMap = new HashMap<>();
                for(SchExamDoctorArrangement da : doctorArrangements)
                {
                    doctorArrangementMap.put(da.getArrangeFlow(),da);
                }
                model.addAttribute("daMap",doctorArrangementMap);
            }
            Map<String,Map<String,String>> examLogMaps = null;
            if(examArrangements != null && examArrangements.size() > 0){
                examLogMaps = new HashMap<>();
                for(SchExamArrangement tempExam : examArrangements){
                    Map<String,String> paramMap = new HashMap<>();
                    paramMap.put("arrangeFlow",tempExam.getArrangeFlow());
                    paramMap.put("doctorFlow",resDoctor.getDoctorFlow());
                    List<Map<String,String>> examArrangementMaps = examCfgBiz.searchExamLogByItems(paramMap);
                    if(examArrangementMaps != null && examArrangementMaps.size() > 0){
                        for(Map<String,String> tempMap : examArrangementMaps){
                            Map<String,String> paramTempMap = new HashMap<>();
                            paramTempMap.put("countNum",tempMap.get("COUNTNUM"));
                            paramTempMap.put("maxScore",tempMap.get("MAXSCORE"));
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsOpen()) &&
                                    Integer.parseInt(tempExam.getExamNumber()) > Integer.parseInt(tempMap.get("COUNTNUM"))){
                                paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            }
                            examLogMaps.put(tempMap.get("ARRANGEFLOW"),paramTempMap);
                        }
                    }else {
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsOpen())) {
                            Map<String,String> paramTempMap = new HashMap<>();
                            paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            examLogMaps.put(tempExam.getArrangeFlow(),paramTempMap);
                        }
                    }
                }
                model.addAttribute("examLogMaps",examLogMaps);
            }
            model.addAttribute("examArrangements",examArrangements);
        }
        return "res/doctor/examCfg/examList";
    }
    @RequestMapping(value="/examQueryScore")
    public String examQueryScore(Model model) {
        if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
            return "res/hospital/examQueryScore/main4jszy";
        }
        return "res/hospital/examQueryScore/main";
    }
    @RequestMapping(value="/downloadExamPaper")
    @ResponseBody
    public String downloadExamPaper(Model model,String recordFlow) {
        Map<String,String> map=new HashMap<>();
        String downloadUrl= InitConfig.getSysCfg("res_after_exam_download_url");
        if(StringUtil.isBlank(downloadUrl))
        {
            map.put("result","0");
            map.put("msg","请联系管理员维护试卷下载地址");
            return JSON.toJSONString(map);
        }
        SchExamDoctorArrangement da=scoreQueryBiz.readDoctorArrangementByFlow(recordFlow);

        String dowland="";
        if(da!=null&&StringUtil.isNotBlank(da.getExamDowland()))
            dowland=da.getExamDowland();
        if(StringUtil.isBlank(dowland))
        {
            dowland="";
            if(da==null||StringUtil.isBlank(da.getExamResultId()))
            {
                map.put("result","0");
                map.put("msg","该考试无试卷信息，无法下载！");
                return JSON.toJSONString(map);
            }
            SysUser doc=userBiz.findByFlow(da.getDoctorFlow());
            URL urlfile = null;
            HttpURLConnection httpUrl = null;
            BufferedInputStream bis = null;
            String url=downloadUrl+"/api/examresultword.ashx?Action=ExamResultWord&CardID="+doc.getUserCode()+"&ResultID="+da.getExamResultId();
            try
            {
                urlfile = new URL(url);
                httpUrl = (HttpURLConnection)urlfile.openConnection();
                httpUrl.setRequestProperty("Accept-Charset", "utf-8");
                httpUrl.setRequestProperty("contentType", "utf-8");
                httpUrl.connect();
                bis = new BufferedInputStream(httpUrl.getInputStream());
                int len = 2048;
                byte[] b = new byte[len];
                while ((len = bis.read(b)) != -1)
                {
                    dowland+=new String(b, "UTF-8").trim();
                }
                if(StringUtil.isNotBlank(dowland))
                {
                    dowland=java.net.URLDecoder.decode(dowland, "UTF-8");
                }
            }
            catch (Exception e)
            {
                map.put("result","0");
                map.put("msg","该试卷信息不存在，无法下载！");
                return JSON.toJSONString(map);
            }
            finally
            {
                try {
                    if(bis!=null)
                    {
                        bis.close();
                    }
                    httpUrl.disconnect();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
        if(StringUtil.isBlank(dowland))
        {
            map.put("result","0");
            map.put("msg","该考试无试卷信息，无法下载！");
            return JSON.toJSONString(map);
        }
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        String url=downloadUrl+"/"+dowland;
        try
        {
            urlfile = new URL(url);
            httpUrl = (HttpURLConnection)urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
        }
        catch (Exception e)
        {
            map.put("result","0");
            map.put("msg","该试卷信息不存在，无法下载！");
            return JSON.toJSONString(map);
        }
        finally
        {
            try {
                if(bis!=null)
                {
                    bis.close();
                }
                httpUrl.disconnect();
            } catch (IOException e) {
                logger.error("", e);
            }
        }
        map.put("result","1");
        map.put("url",url);
        return JSON.toJSONString(map);
    }
    /**
     * 下载远程文件并保存到本地
     */

    @RequestMapping(value="/downloadFile")
    public void downloadFile(String url, HttpServletResponse response)
    {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        try
        {
            urlfile = new URL(url);
            httpUrl = (HttpURLConnection)urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            byte[] data= new byte[bis.available()];
            int len = 2048;
            byte[] b = new byte[len];
            String fileName = "年度考核试卷"+url.substring(url.lastIndexOf("."));
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"),"ISO8859-1" ) + "\"");
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/octet-stream;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            while ((len = bis.read(b)) != -1)
            {
                outputStream.write(b, 0, len);
            }
            bis.close();
            httpUrl.disconnect();
            outputStream.flush();
            outputStream.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException("无法下载相应试卷");
        }
        finally
        {
            try
            {
                if(bis!=null)
                    bis.close();
            }
            catch (IOException e)
            {
                logger.error("", e);
            }
        }
    }
    @RequestMapping(value="/scorelist")
    public String scorelist(Model model,Integer currentPage ,HttpServletRequest request,String doctorCategoryId,
                       String trainingSpeId,String sessionNumber,String assessmentYear,String[] doctorTypeIdList,
                       String userName){
        SysUser sysuser=GlobalContext.getCurrentUser();
        if(StringUtil.isBlank(assessmentYear))
            assessmentYear= DateUtil.getYear();
        Integer firstYear=Integer.valueOf(assessmentYear)-2;
        Integer secondYear=Integer.valueOf(assessmentYear)-1;
        List<String> years=new ArrayList<>();
        years.add(String.valueOf(firstYear));
        years.add(String.valueOf(secondYear));
        years.add(assessmentYear);
        model.addAttribute("years",years);
        ResDoctorExt doctor=new ResDoctorExt();
        doctor.setSessionNumber(sessionNumber);
        doctor.setTrainingSpeId(trainingSpeId);
        doctor.setDoctorCategoryId(doctorCategoryId);
        SysUser d=new SysUser();
        d.setUserName(userName);
        doctor.setSysUser(d);
        doctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        //人员类型复选框勾选标识
        Map<String,String> doctorTypeSelectMap = new HashMap<>();
        List<String> doctorTypeIdList1 = Arrays.asList(doctorTypeIdList);
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> dictList = dictBiz.searchDictList(sysDict);
        if(dictList!=null&&dictList.size()>0&&doctorTypeIdList1!=null&&doctorTypeIdList1.size()>0){
            for (SysDict dict:dictList){
                if(doctorTypeIdList1.contains(dict.getDictId())){
                    doctorTypeSelectMap.put(dict.getDictId(),"checked");
                }
            }
        }
        if (doctorTypeIdList1 == null) {
            doctorTypeIdList1 = new ArrayList<>();
            for (SysDict dict : dictList) {
                doctorTypeIdList1.add(dict.getDictId());
                doctorTypeSelectMap.put(dict.getDictId(), "checked");
            }
        }
        model.addAttribute("doctorTypeSelectMap", doctorTypeSelectMap);
        doctor.setDoctorTypeIdList(doctorTypeIdList1);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResDoctorExt> list = doctorBiz.searchDocUser(doctor, "");
        model.addAttribute("list",list);
        if(list!=null&&list.size()>0)
        {
            List<String> userFlows=new ArrayList<>();
            for(ResDoctorExt user:list)
            {
                userFlows.add(user.getDoctorFlow());
            }//查询条件
            Map<String,Object> param=new HashMap<>();
            param.put("orgFlow",sysuser.getOrgFlow());
            param.put("userFlows",userFlows);
            List<SchExamDoctorArrangement> doctorArrangements=scoreQueryBiz.getDoctorArrangements(param);
            if(doctorArrangements!=null&&doctorArrangements.size()>0){
                Map<String,SchExamDoctorArrangement> doctorArrangementMap=new HashMap<>();
                for(SchExamDoctorArrangement da:doctorArrangements)
                {
                    doctorArrangementMap.put(da.getAssessmentYear()+da.getDoctorFlow()+da.getSessionNumber(),da);
                }
                model.addAttribute("daMap",doctorArrangementMap);
            }
        }
        if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
            return "res/hospital/examQueryScore/list4jszy";
        }
        return "res/hospital/examQueryScore/list";
    }
    @RequestMapping(value="/exportInfo")
    public void exportInfo(Model model,HttpServletRequest request,HttpServletResponse response,
                           String doctorCategoryId,String trainingSpeId,String[] doctorTypeIdList,
                           String sessionNumber,String assessmentYear,
                           String userName) throws IOException {

        SysUser sysuser=GlobalContext.getCurrentUser();

        if(StringUtil.isBlank(assessmentYear))
            assessmentYear= DateUtil.getYear();
        Integer firstYear=Integer.valueOf(assessmentYear)-2;
        Integer secondYear=Integer.valueOf(assessmentYear)-1;
        List<String> years=new ArrayList<>();
        years.add(String.valueOf(firstYear));
        years.add(String.valueOf(secondYear));
        years.add(assessmentYear);
        model.addAttribute("years",years);
        ResDoctorExt doctor=new ResDoctorExt();
        doctor.setSessionNumber(sessionNumber);
        doctor.setTrainingSpeId(trainingSpeId);
        doctor.setDoctorCategoryId(doctorCategoryId);
        SysUser d=new SysUser();
        d.setUserName(userName);
        doctor.setSysUser(d);
        doctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        //人员类型复选框勾选标识
        Map<String,String> doctorTypeSelectMap = new HashMap<>();
        List<String> doctorTypeIdList1 = Arrays.asList(doctorTypeIdList);
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> dictList = dictBiz.searchDictList(sysDict);
        if(dictList!=null&&dictList.size()>0&&doctorTypeIdList1!=null&&doctorTypeIdList1.size()>0){
            for (SysDict dict:dictList){
                if(doctorTypeIdList1.contains(dict.getDictId())){
                    doctorTypeSelectMap.put(dict.getDictId(),"checked");
                }
            }
        }
        if (doctorTypeIdList1 == null) {
            doctorTypeIdList1 = new ArrayList<>();
            for (SysDict dict : dictList) {
                doctorTypeIdList1.add(dict.getDictId());
                doctorTypeSelectMap.put(dict.getDictId(), "checked");
            }
        }
        doctor.setDoctorTypeIdList(doctorTypeIdList1);
        List<ResDoctorExt> list = doctorBiz.searchDocUser(doctor, "");
        model.addAttribute("list",list);
        Map<String,SchExamDoctorArrangement> doctorArrangementMap=new HashMap<>();
        if(list!=null&&list.size()>0)
        {
            List<String> userFlows=new ArrayList<>();
            for(ResDoctorExt user:list)
            {
                userFlows.add(user.getDoctorFlow());
            }//查询条件
            Map<String,Object> param=new HashMap<>();
            param.put("orgFlow",sysuser.getOrgFlow());
            param.put("userFlows",userFlows);
            List<SchExamDoctorArrangement> doctorArrangements=scoreQueryBiz.getDoctorArrangements(param);
            if(doctorArrangements!=null&&doctorArrangements.size()>0){
                for(SchExamDoctorArrangement da:doctorArrangements)
                {
                    doctorArrangementMap.put(da.getAssessmentYear()+da.getDoctorFlow()+da.getSessionNumber(),da);
                }
                model.addAttribute("daMap",doctorArrangementMap);
            }
        }
        scoreQueryBiz.exportInfo(list,years,doctorArrangementMap,response);
    }

    @RequestMapping(value="/querySkillScore")
    public String querySkillScore(Model model) {
        if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
            return "res/hospital/examQueryScore/skillMain4jszy";
        }
        return "res/hospital/examQueryScore/skillMain";
    }
    @RequestMapping(value="/skillScorelist")
    public String skillScorelist(Model model,Integer currentPage ,HttpServletRequest request,String doctorCategoryId,
                            String trainingSpeId,String sessionNumber,String assessmentYear,String[] doctorTypeIdList,
                            String userName){
        SysUser sysuser=GlobalContext.getCurrentUser();
        if(StringUtil.isBlank(assessmentYear))
            assessmentYear= DateUtil.getYear();
        Integer firstYear=Integer.valueOf(assessmentYear)-2;
        Integer secondYear=Integer.valueOf(assessmentYear)-1;
        List<String> years=new ArrayList<>();
        years.add(String.valueOf(firstYear));
        years.add(String.valueOf(secondYear));
        years.add(assessmentYear);
        model.addAttribute("years",years);
        ResDoctorExt doctor=new ResDoctorExt();
        doctor.setSessionNumber(sessionNumber);
        doctor.setTrainingSpeId(trainingSpeId);
        doctor.setDoctorCategoryId(doctorCategoryId);
        SysUser d=new SysUser();
        d.setUserName(userName);
        doctor.setSysUser(d);
        doctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        //人员类型复选框勾选标识
        Map<String,String> doctorTypeSelectMap = new HashMap<>();
        List<String> doctorTypeIdList1 = Arrays.asList(doctorTypeIdList);
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> dictList = dictBiz.searchDictList(sysDict);
        if(dictList!=null&&dictList.size()>0&&doctorTypeIdList1!=null&&doctorTypeIdList1.size()>0){
            for (SysDict dict:dictList){
                if(doctorTypeIdList1.contains(dict.getDictId())){
                    doctorTypeSelectMap.put(dict.getDictId(),"checked");
                }
            }
        }
        doctor.setDoctorTypeIdList(doctorTypeIdList1);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResDoctorExt> list = doctorBiz.searchDocUser(doctor, "");
        model.addAttribute("list",list);
        if(list!=null&&list.size()>0)
        {
            List<String> userFlows=new ArrayList<>();
            for(ResDoctorExt user:list)
            {
                userFlows.add(user.getDoctorFlow());
            }//查询条件
            Map<String,Object> param=new HashMap<>();
//            param.put("orgFlow",sysuser.getOrgFlow());
            param.put("userFlows",userFlows);
            List<ResScore> doctorSkillScores=scoreQueryBiz.getDoctorSkillScore(param);
            if(doctorSkillScores!=null&&doctorSkillScores.size()>0){
                Map<String,ResScore> doctorArrangementMap=new HashMap<>();
                for(ResScore da:doctorSkillScores)
                {
                    doctorArrangementMap.put(da.getScorePhaseId()+da.getDoctorFlow(),da);
                }
                model.addAttribute("daMap",doctorArrangementMap);
            }
        }
        if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
            model.addAttribute("jszyFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
            return "res/hospital/examQueryScore/skillList4jszy";
        }
        return "res/hospital/examQueryScore/skillList";
    }

    @RequestMapping(value="/scoreDetail")
    public String scoreDetail(ResScore score,String scoreType,String scoreYear,Model model) throws DocumentException {
        List<ResScore> scorelist=resScoreBiz.selectByExampleWithBLOBs(score.getDoctorFlow());
        //技能成绩
        List<ResScore> skillList=new ArrayList<ResScore>();
        List<ResScore> theoryList=new ArrayList<ResScore>();
        List<ResScore> skillListByYear=new ArrayList<ResScore>();

        if(null!=scorelist&&scorelist.size()>0)
        {
            for(ResScore resScore:scorelist)
            {
                if (com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(scoreYear) && scoreYear.equals(resScore.getScorePhaseId()))
                {
                    skillListByYear.add(resScore);
                } else if (com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()))
                {
                    skillList.add(resScore);
                } else if (com.pinde.core.common.enums.ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId()))
                {
                    theoryList.add(resScore);
                }
            }
        }
        if(skillListByYear.size()>0)
        {
            skillList=skillListByYear;
        }
        Map<String,Map<String,String>> extScoreMap=new HashMap<String,Map<String,String>>();
        for(int i=0;i<skillList.size();i++)
        {
            Map<String,String> extScore=new HashMap<String,String>();
            ResScore resScore=skillList.get(i);
            String content = null==resScore ? "":resScore.getExtScore();
            if(StringUtil.isNotBlank(content)) {
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element extScoreInfo = root.element("extScoreInfo");
                if (extScoreInfo != null) {
                    List<Element> extInfoAttrEles = extScoreInfo.elements();
                    if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                        for (Element attr : extInfoAttrEles) {
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            extScore.put(attrName, attrValue);
                        }
                    }
                }
            }
            extScoreMap.put(resScore.getScoreFlow(),extScore);
        }
        ResDoctor resDoctor=doctorBiz.searchByUserFlow(score.getDoctorFlow());
        SysUser sysUser=userBiz.readSysUser(score.getDoctorFlow());
        model.addAttribute("user",sysUser);
        model.addAttribute("resDoctor",resDoctor);
        model.addAttribute("skillList",skillList);
        model.addAttribute("theoryList",theoryList);
        model.addAttribute("extScoreMap",extScoreMap);
        model.addAttribute("scoreType",scoreType);
        if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
            model.addAttribute("jszyFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
        }
        return "res/hospital/examQueryScore/skillScoreDetail";
    }
    @RequestMapping(value="/importSkillScore")
    public String importSkillScore(){
        return "res/hospital/examQueryScore/importSchoolRoll";
    }

    public static final String SCOREYEAR_NOT_FIND="请选择成绩年份";
    /**
     * 技能成绩导入
     */
    @RequestMapping(value="/importSkillScoreFromExcel")
    @ResponseBody
    public String importSkillScoreFromExcel(MultipartFile file, String scoreYear){
        if(StringUtil.isBlank(scoreYear))
        {
            return SCOREYEAR_NOT_FIND;
        }
        if(file.getSize() > 0){
            try{
                ExcelUtile result = (ExcelUtile) doctorBiz.importSkillScoreFromExcel(file, scoreYear, scoreYear);
                if(null!=result)
                {
                    String code= (String) result.get("code");
                    int count=(Integer) result.get("count");
                    String msg= (String) result.get("msg");
                    if("1".equals(code))
                    {
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
                    }else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
                        }else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                        }
                    }
                }else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                }
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
            }
        }
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
    }
    @RequestMapping(value="/searchDoctorSkillScore")
    public String searchDoctorSkillScore(Model model){
        SysUser user=GlobalContext.getCurrentUser();
        List<String> userFlows=new ArrayList<>();
        userFlows.add(user.getUserFlow());
        Map<String,Object> param=new HashMap<>();
        param.put("userFlows",userFlows);
        List<ResScore> doctorSkillScores=scoreQueryBiz.getDoctorSkillScore(param);
        model.addAttribute("doctorSkillScores",doctorSkillScores);
        model.addAttribute("sysUser",user);
        return "res/doctor/examCfg/doctorSkillScore";
    }
    @RequestMapping(value="/exportSkillScoreInfo")
    public void exportSkillScoreInfo(HttpServletResponse response,
                           String doctorCategoryId,String trainingSpeId,String[] doctorTypeIdList,
                           String sessionNumber,String assessmentYear,
                           String userName) throws IOException {
        SysUser sysuser=GlobalContext.getCurrentUser();
        if(StringUtil.isBlank(assessmentYear))
            assessmentYear= DateUtil.getYear();
        Integer firstYear=Integer.valueOf(assessmentYear)-2;
        Integer secondYear=Integer.valueOf(assessmentYear)-1;
        List<String> years=new ArrayList<>();
        years.add(String.valueOf(firstYear));
        years.add(String.valueOf(secondYear));
        years.add(assessmentYear);
        ResDoctorExt doctor=new ResDoctorExt();
        doctor.setSessionNumber(sessionNumber);
        doctor.setTrainingSpeId(trainingSpeId);
        doctor.setDoctorCategoryId(doctorCategoryId);
        SysUser d=new SysUser();
        d.setUserName(userName);
        doctor.setSysUser(d);
        doctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        //人员类型复选框勾选标识
        Map<String,String> doctorTypeSelectMap = new HashMap<>();
        List<String> doctorTypeIdList1 = Arrays.asList(doctorTypeIdList);
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> dictList = dictBiz.searchDictList(sysDict);
        if(dictList!=null&&dictList.size()>0&&doctorTypeIdList1!=null&&doctorTypeIdList1.size()>0){
            for (SysDict dict:dictList){
                if(doctorTypeIdList1.contains(dict.getDictId())){
                    doctorTypeSelectMap.put(dict.getDictId(),"checked");
                }
            }
        }
        doctor.setDoctorTypeIdList(doctorTypeIdList1);
        List<ResDoctorExt> list = doctorBiz.searchDocUser(doctor, "");
        Map<String,ResScore> doctorArrangementMap=new HashMap<>();
        if(list!=null&&list.size()>0)
        {
            List<String> userFlows=new ArrayList<>();
            for(ResDoctorExt user:list)
            {
                userFlows.add(user.getDoctorFlow());
            }//查询条件
            Map<String,Object> param=new HashMap<>();
//            param.put("orgFlow",sysuser.getOrgFlow());
            param.put("userFlows",userFlows);
            List<ResScore> doctorSkillScores=scoreQueryBiz.getDoctorSkillScore(param);
            if(doctorSkillScores!=null&&doctorSkillScores.size()>0){
                for(ResScore da:doctorSkillScores)
                {
                    doctorArrangementMap.put(da.getScorePhaseId()+da.getDoctorFlow(),da);
                }
            }
        }
        scoreQueryBiz.exportSkillScoreInfo(list,years,doctorArrangementMap,response);
    }
}
