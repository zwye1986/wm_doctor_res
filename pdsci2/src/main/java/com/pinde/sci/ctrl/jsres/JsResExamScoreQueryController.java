package com.pinde.sci.ctrl.jsres;


import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResSchExamScoreQueryBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.jsres.JsResDocTypeEnum;
import com.pinde.sci.model.mo.SchExamDoctorArrangement;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsres/examScoreQuery")
public class JsResExamScoreQueryController extends GeneralController {

    @Autowired
    private IJsResSchExamScoreQueryBiz scoreQueryBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IUserBiz userBiz;

    @RequestMapping(value="/queryMain")
    public String queryMain(Model model) {

        return "jsres/hospital/examScoreQuery/queryMain";
    }

    @RequestMapping(value="/queryMainAcc")
    public String queryMainAcc(Model model) {

        return "jsres/hospital/examScoreQuery/queryMainAcc";
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
                    e.printStackTrace();
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
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    }
    @RequestMapping(value="/list")
    public String list(Model model,Integer currentPage ,HttpServletRequest request,
                       String trainingTypeId,String trainingSpeId,
                       String sessionNumber,String assessmentYear,
                       String userName,String datas[]){
        List<String>docTypeList=new ArrayList<String>();
        model.addAttribute("datas", datas);
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }else{
            datas=new String[JsResDocTypeEnum.values().length];
            int i=0;
            for(JsResDocTypeEnum e: JsResDocTypeEnum.values())
            {
                docTypeList.add(e.getId());
                datas[i++]=e.getId();
            }
        }
        SysUser sysuser=GlobalContext.getCurrentUser();
        //查询条件
        Map<String,Object> param=new HashMap<>();
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("userName",userName);
        param.put("orgFlow",sysuser.getOrgFlow());
//        if(StringUtil.isBlank(assessmentYear))
//            assessmentYear= DateUtil.getYear();
//        Integer firstYear=Integer.valueOf(assessmentYear)-2;
//        Integer secondYear=Integer.valueOf(assessmentYear)-1;
//        List<String> years=new ArrayList<>();
//        years.add(String.valueOf(firstYear));
//        years.add(String.valueOf(secondYear));
//        years.add(assessmentYear);
//        model.addAttribute("years",years);
        /**年度为空时，列表根据所选年级展示年级年份+1 ，年级年份+2，年级年份+3。
        若年度查询条件选择具体年度后，列表仅展示该年度**/
        List<String> years = new ArrayList<>();
        if(StringUtil.isBlank(assessmentYear)){
            int firstYear = Integer.parseInt(sessionNumber)+1;
            int secondYear = Integer.parseInt(sessionNumber)+2;
            int thirdYear = Integer.parseInt(sessionNumber)+3;
            years.add(String.valueOf(firstYear));
            years.add(String.valueOf(secondYear));
            years.add(String.valueOf(thirdYear));
        }else{
            years.add(assessmentYear);
        }
        model.addAttribute("years",years);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list=scoreQueryBiz.userList(param);
        model.addAttribute("list",list);
        if(list!=null&&list.size()>0)
        {
            List<String> userFlows=new ArrayList<>();
            for(Map<String,Object> user:list)
            {
                userFlows.add((String) user.get("doctorFlow"));
            }
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

        return "jsres/hospital/examScoreQuery/list";
    }
    @RequestMapping(value="/clearScore")
    @ResponseBody
    public String clearScore(HttpServletRequest request, String trainingTypeId,String trainingSpeId,String sessionNumber,String assessmentYear,String userName,String datas[]){
        List<String>docTypeList=new ArrayList<String>();
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }else{
            datas=new String[JsResDocTypeEnum.values().length];
            int i=0;
            for(JsResDocTypeEnum e: JsResDocTypeEnum.values())
            {
                docTypeList.add(e.getId());
                datas[i++]=e.getId();
            }
        }
        SysUser sysuser=GlobalContext.getCurrentUser();
        //查询条件
        Map<String,Object> param=new HashMap<>();
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("userName",userName);
        param.put("orgFlow",sysuser.getOrgFlow());
        param.put("assessmentYear",assessmentYear);
        int result = 0 ;
        List<Map<String,Object>> list=scoreQueryBiz.userList(param);
        if(list!=null&&list.size()>0)
        {
            List<String> userFlows=new ArrayList<>();
            for(Map<String,Object> user:list)
            {
                userFlows.add((String) user.get("doctorFlow"));
            }
            param.put("userFlows",userFlows);
            result = scoreQueryBiz.clearScore(userFlows,sessionNumber,assessmentYear,sysuser.getOrgFlow());
        }
        return result+"";
    }
    @RequestMapping(value="/exportInfo")
    public void exportInfo(Model model,HttpServletRequest request,HttpServletResponse response,
                       String trainingTypeId,String trainingSpeId,
                       String sessionNumber,String assessmentYear,
                       String userName,String datas[]) throws IOException {
        SysUser sysuser=GlobalContext.getCurrentUser();
        //查询条件
        Map<String,Object> param=new HashMap<>();
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("userName",userName);
        param.put("orgFlow",sysuser.getOrgFlow());
        if(StringUtil.isBlank(assessmentYear))
            assessmentYear= DateUtil.getYear();
//        Integer firstYear=Integer.valueOf(assessmentYear)-2;
//        Integer secondYear=Integer.valueOf(assessmentYear)-1;
        List<String> years=new ArrayList<>();
//        years.add(String.valueOf(firstYear));
//        years.add(String.valueOf(secondYear));
        years.add(assessmentYear);
        model.addAttribute("years",years);
        List<String>docTypeList=new ArrayList<String>();
        model.addAttribute("datas", datas);
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        List<Map<String,Object>> list=scoreQueryBiz.userList(param);
        model.addAttribute("list",list);
        Map<String,SchExamDoctorArrangement> doctorArrangementMap=new HashMap<>();
        if(list!=null&&list.size()>0)
        {
            List<String> userFlows=new ArrayList<>();
            for(Map<String,Object> user:list)
            {
                userFlows.add((String) user.get("doctorFlow"));
            }
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
}
