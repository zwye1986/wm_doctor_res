package com.pinde.sci.ctrl.res;


import com.alibaba.fastjson.JSON;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.ZipUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.res.IExamResultsBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResScoreBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/exam/paper")
public class ResExamPaperDownController extends GeneralController {

    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Autowired
    private IResScoreBiz scoreBiz;
    @Autowired
    private IExamResultsBiz examResultsBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private ISchArrangeResultBiz schArrangeResultBiz;
    @RequestMapping(value="/downloadCkkPaper")
    @ResponseBody
    public String downloadCkkPaper(Model model,String processFlow) {
        Map<String,String> map=new HashMap<>();
        String downloadUrl= InitConfig.getSysCfg("res_after_exam_download_url");
        if(StringUtil.isBlank(downloadUrl))
        {
            map.put("result","0");
            map.put("msg","请联系管理员维护试卷下载地址");
            return JSON.toJSONString(map);
        }
        ResDoctorSchProcess process=processBiz.read(processFlow);

        if(process==null) {

            map.put("result","0");
            map.put("msg","学员轮转记录不存在！");
            return JSON.toJSONString(map);
        }
        ResScore score=scoreBiz.getScoreByProcess(processFlow);
        if(score==null||score.getTheoryScore()==null)
        {
            map.put("result","0");
            map.put("msg","该学员还未参加出科考试！");
            return JSON.toJSONString(map);
        }
        ExamResults result=examResultsBiz.getByProcessFlowAndScore(processFlow,score.getTheoryScore());
        if(result==null)
        {
            map.put("result","0");
            map.put("msg","该学员还未参加出科考试！");
            return JSON.toJSONString(map);
        }
        String dowland="";
        if(result!=null&&StringUtil.isNotBlank(result.getWordUrl())){
            dowland=result.getWordUrl();
        }
        if(StringUtil.isBlank(dowland))
        {
            dowland="";
            if(result==null||StringUtil.isBlank(result.getResultsId()))
            {
                map.put("result","0");
                map.put("msg","该考试无试卷信息，无法下载！");
                return JSON.toJSONString(map);
            }
            SysUser user = userBiz.readSysUser(process.getUserFlow());
            URL urlfile = null;
            HttpURLConnection httpUrl = null;
            BufferedInputStream bis = null;
            String url=downloadUrl+"/api/examresultword.ashx?Action=ExamResultWord&CardID="+user.getUserCode()+"&ResultID="+result.getResultsId();
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
                e.printStackTrace();
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
        String url=downloadUrl+dowland;
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

    @RequestMapping(value="/downloadCkkPaperBatch")
    public void downloadCkkPaperBatch(String doctorFlow, String roleId,HttpServletResponse response) throws IOException {
        //1.获取临时文件夹
        String tempFolder = System.getProperty("java.io.tmpdir") + File.separator + PkUtil.getUUID();
        //2.获取考核试卷下载链接列表
        if(StringUtil.isBlank(doctorFlow)) return;
            Map<String, String> pMap = new HashMap<>();
            if ("kzr".equals(roleId)) {
                SysUser currentUser = GlobalContext.getCurrentUser();
                pMap.put("deptFlow", currentUser.getDeptFlow());
                pMap.put("kzrFlow", currentUser.getUserFlow());
                pMap.put("doctorFlow", doctorFlow);
            } else {
                pMap.put("doctorFlow", doctorFlow);
            }
        List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByMap(pMap);
        Map<String,String> downloadUrls = getDownloadUrls(arrResultList);
        //3.根据下载链接，下载试卷放入临时文件夹
        SysUser user=userBiz.readSysUser(doctorFlow);
        String dest = tempFolder + File.separator +"papers"+File.separator + user.getUserName();
        downloadPapersByUrl(downloadUrls, dest);
        //4.生成成绩excel放入临时文件夹
        dest = tempFolder + File.separator +"papers";
        createScoreExcel(doctorFlow,roleId,dest);
        //5.压缩文件夹
        File directory =new File(tempFolder + File.separator +"papers");
        File zipFlie =new File(tempFolder+ File.separator + user.getUserName()+".zip");
        String zipFolderName = "";
        ZipUtil.makeDirectoryToZip(directory,zipFlie,zipFolderName,7);

        //6.输出
        BufferedInputStream bis =  new BufferedInputStream(new FileInputStream(zipFlie));
        byte[] data= new byte[bis.available()];
        int len = 2048;
        byte[] b = new byte[len];
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(zipFlie.getName().getBytes("gbk"),"ISO8859-1" ) + "\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        while ((len = bis.read(b)) != -1)
        {
            outputStream.write(b, 0, len);
        }
        bis.close();
        outputStream.flush();
        outputStream.close();
    }
    /**
     * 下载远程文件并保存到本地
     */

    @RequestMapping(value="/downloadCkkFile")
    public void downloadCkkFile(String url,String processFlow, HttpServletResponse response)
    {

        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        try
        {
            ResDoctorSchProcess process=processBiz.read(processFlow);
            urlfile = new URL(url);
            httpUrl = (HttpURLConnection)urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            byte[] data= new byte[bis.available()];
            int len = 2048;
            byte[] b = new byte[len];
            String fileName = "出科考核试卷"+url.substring(url.lastIndexOf("."));
            if(process!=null)
            {
                SysUser user=userBiz.readSysUser(process.getUserFlow());
                fileName=process.getSchDeptName()+"("+process.getSchStartDate()+"~"+process.getSchEndDate()+")出科考核试卷"+url.substring(url.lastIndexOf("."));
                if(user!=null)
                    fileName=user.getUserName()+fileName;
            }
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"),"ISO8859-1" ) + "\"");
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/octet-stream;charset=UTF-8");
            outputStream = new BufferedOutputStream(response.getOutputStream());
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
    public String list(Model model,String processFlow) {

        List<ExamResults> results=examResultsBiz.getByProcessFlow(processFlow);
        model.addAttribute("results",results);
        return "/res/exam/paper/list";
    }

    @RequestMapping(value="/list2")
    public String list2(Model model,String processFlow) {
        List<ExamResults> results=examResultsBiz.getByProcessFlow(processFlow);
        model.addAttribute("results",results);
        return "/res/exam/paper/list2";
    }
    @RequestMapping(value="/showErrorInfo")
    public String showErrorInfo(Model model,String processFlow,String resultId) {
        String errorPage = "res/edu/student/errorView";

        if(!StringUtil.isNotBlank(resultId)){
            model.addAttribute("errorMeg","当前考试试卷ID为空！");
            return errorPage;
        }

        //考试地址
        String testUrl = InitConfig.getSysCfg("res_after_wrong_exam_url_cfg");
        if(!StringUtil.isNotBlank(testUrl)){
            model.addAttribute("errorMeg","请联系管理员维护错题查看地址！");
            return errorPage;
        }

        ExamResults results=examResultsBiz.getByFlow(resultId);

        if(results==null) {
            model.addAttribute("errorMeg","当前考试记录信息获取失败！");
            return errorPage;
        }
        ResDoctorSchProcess process=processBiz.read(processFlow);

        if(process==null) {
            model.addAttribute("errorMeg","当前轮转信息获取失败！");
            return errorPage;
        }
        //当前用户
        SysUser user = userBiz.readSysUser(process.getUserFlow());
        model.addAttribute("RequestType","pc");
        model.addAttribute("ProcessFlow",process.getProcessFlow());
        model.addAttribute("SoluID",results.getSoluId());
        model.addAttribute("ResultID",resultId);
        return "redirect:"+testUrl;
    }

    /**
     * 获取下载链接列表
     * @param arrResultList
     * @return
     */
    private Map<String,String> getDownloadUrls(  List<SchArrangeResult>  arrResultList ) {
        String downloadUrl = InitConfig.getSysCfg("res_after_exam_download_url");
        Map<String, String> urlMap = new HashMap<String, String>();

        String url = "";
        String fileName = "";
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        try {
            for(SchArrangeResult schArrangeResult:arrResultList){
                String resultFlow = schArrangeResult.getResultFlow();
                ResDoctorSchProcess process = processBiz.searchByResultFlow(resultFlow);
                if (process == null) continue;// 轮转记录不存在
                String processFlow = process.getProcessFlow();
                ResScore score = scoreBiz.getScoreByProcess(processFlow);
                if (score == null || score.getTheoryScore() == null) continue;//该学员还未参加出科考试
                ExamResults result = examResultsBiz.getByProcessFlowAndScore(processFlow, score.getTheoryScore());
                if (result == null) continue;//该学员还未参加出科考试
                String dowland = "";
                if (result != null && StringUtil.isNotBlank(result.getWordUrl())) {
                    dowland = result.getWordUrl();
                }
                SysUser user = userBiz.readSysUser(process.getUserFlow());
                if (StringUtil.isBlank(dowland)) {
                    if (result == null || StringUtil.isBlank(result.getResultsId())) continue;//该考试无试卷信息
                    url = downloadUrl + "/api/examresultword.ashx?Action=ExamResultWord&CardID=" + user.getUserCode() + "&ResultID=" + result.getResultsId();
                    urlfile = new URL(url);
                    httpUrl = (HttpURLConnection) urlfile.openConnection();
                    httpUrl.setRequestProperty("Accept-Charset", "utf-8");
                    httpUrl.setRequestProperty("contentType", "utf-8");
                    httpUrl.connect();
                    bis = new BufferedInputStream(httpUrl.getInputStream());
                    int len = 2048;
                    byte[] b = new byte[len];
                    while ((len = bis.read(b)) != -1) {
                        dowland += new String(b, "UTF-8").trim();
                    }
                    if(StringUtil.isNotBlank(dowland))
                    {
                        dowland=java.net.URLDecoder.decode(dowland, "UTF-8");
                    }
                }
                if(StringUtil.isNotBlank(dowland))
                {
                    url = downloadUrl + "/" + dowland;
                    fileName = user.getUserName() +"_"+ process.getDeptName() +"_"+ process.getSchStartDate()+"_"+process.getSchEndDate()+".doc";
                    urlMap.put(url, fileName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if(httpUrl !=null){
                    httpUrl.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return urlMap;
    }

    /**
     * 根据url列表下载试卷到指定文件夹
     * @param urlMap
     * @param dest
     * @return
     */
    private void downloadPapersByUrl(Map<String,String> urlMap, String dest) {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            for (String url : urlMap.keySet()) {
                String fileName = urlMap.get(url);
                urlfile = new URL(url);
                httpUrl = (HttpURLConnection) urlfile.openConnection();
                httpUrl.connect();
                bis = new BufferedInputStream(httpUrl.getInputStream());
                file = new File(dest + File.separator + fileName);
                File fileParent = file.getParentFile();
                if (!fileParent.exists()) {
                    fileParent.mkdirs();
                }
                file.createNewFile();
                fos = new FileOutputStream(file);
                int len = 2048;
                byte[] b = new byte[len];
                while ((len = bis.read(b)) != -1) {
                    fos.write(b, 0, len);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                    fos.close();
                }
                if(httpUrl !=null){
                    httpUrl.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void createScoreExcel(String doctorFlow, String roleId,String dest) throws UnsupportedEncodingException {
        HSSFWorkbook wb = jsResDoctorRecruitBiz.createCycleResultsByDoc(doctorFlow, roleId);
        FileOutputStream fos = null;
        String fileName = "学员出科记录表.xls";
        try {
            File file= new File(dest + File.separator + fileName);
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            file.createNewFile();
            fos = new FileOutputStream(file);
            wb.write(fos);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
