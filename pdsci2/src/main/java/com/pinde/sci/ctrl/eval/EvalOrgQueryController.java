package com.pinde.sci.ctrl.eval;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.eval.IEvalCfgBiz;
import com.pinde.sci.biz.eval.IExpertOrgBiz;
import com.pinde.sci.biz.eval.IExpertOrgSpeBiz;
import com.pinde.sci.biz.jsres.IResOrgSpeBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.eval.EvalCfgEnum;
import com.pinde.sci.model.mo.ExpertEvalCfg;
import com.pinde.sci.model.mo.ExpertEvalResult;
import com.pinde.sci.model.mo.SysOrg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/eval/evalOrgQuery")
public class EvalOrgQueryController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IEvalCfgBiz evalCfgBiz;
    @Autowired
    private IExpertOrgBiz evalOrgBiz;
    @Autowired
    private IExpertOrgSpeBiz expertOrgSpeBiz;
    @Autowired
    private IOrgBiz sysOrgBiz;
    @Autowired
    private IResOrgSpeBiz resOrgSpeBiz;

    @RequestMapping(value = "/main")
    public String main(Model model) {
        return "eval/evalOrgQuery/main";
    }

    @RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
    public String list(String orgName,String speId, Integer currentPage, HttpServletRequest request, Model model,String evalYear){
        if(StringUtil.isNotBlank(evalYear)) {
            ExpertEvalCfg evalCfg=evalCfgBiz.findByEvalYearFirst(evalYear);
            model.addAttribute("evalCfg",evalCfg);
            if(evalCfg!=null) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("orgName", orgName);
                paramMap.put("speId", speId);
                paramMap.put("evalYear", evalYear);
                PageHelper.startPage(currentPage, getPageSize(request));
                List<SysOrg> sysList = evalOrgBiz.evalOrgQueryList(paramMap);
                model.addAttribute("sysList", sysList);
            }
        }
        return "eval/evalOrgQuery/list";
    }
    @RequestMapping(value="/showOrgManage",method={RequestMethod.POST,RequestMethod.GET})
    public String showOrgManage(String orgFlow, HttpServletRequest request, Model model,String evalYear){
        ExpertEvalCfg evalCfg=evalCfgBiz.findByEvalYearFirst(evalYear);
        model.addAttribute("evalCfg",evalCfg);
        ExpertEvalCfg manageChildren=evalCfgBiz.getFirstChildByType(evalCfg.getCfgFlow(), EvalCfgEnum.MANAGE.getId());
        if(manageChildren!=null) {
            List<ExpertEvalCfg> cfgList = evalCfgBiz.searchChildrenList(manageChildren.getCfgFlow());
            model.addAttribute("cfgList",cfgList);
        }
        return "eval/evalOrgQuery/showOrgManage";
    }
    @RequestMapping(value="/showOrgManageDetail",method={RequestMethod.POST,RequestMethod.GET})
    public String showOrgManageDetail(String cfgFlow,String orgFlow, HttpServletRequest request, Model model,String evalYear){
        ExpertEvalCfg evalCfg=evalCfgBiz.readByFlow(cfgFlow);
        model.addAttribute("evalCfg",evalCfg);
        if("U".equals(evalCfg.getIsFile()))
        {
            ExpertEvalResult result = evalCfgBiz.getOrgCfgEvalReustl(evalYear, orgFlow, evalCfg.getCfgFlow());
            if (result != null && StringUtil.isNotBlank(result.getEvalContent())) {
                Map<String, Object> formDataMap = evalCfgBiz.parseContent(result.getEvalContent());
                model.addAttribute("formDataMap", formDataMap);
            }

            String name=evalCfg.getFilePath();
            if(name.lastIndexOf(".")>=0)
            {
                name=name.substring(0,name.lastIndexOf("."));
            }
            String jspPath="eval/base/" + name;
            return jspPath;
        }else if("N".equals(evalCfg.getIsFile()))
        {
            List<ExpertEvalCfg> cfgList = evalCfgBiz.searchChildrenList(evalCfg.getCfgFlow());
            model.addAttribute("cfgList",cfgList);
            Map<String,ExpertEvalResult> resultMap=new HashMap<>();
            if(cfgList!=null&&cfgList.size()>0) {
                for (ExpertEvalCfg c : cfgList) {
                    ExpertEvalResult result = evalCfgBiz.getOrgCfgEvalReustl(evalYear, orgFlow, c.getCfgFlow());
                    resultMap.put(c.getCfgFlow(),result);
                }
            }
            model.addAttribute("resultMap",resultMap);
            return "eval/evalOrgQuery/manageList";
        }else if("Y".equals(evalCfg.getIsFile()))
        {
            model.addAttribute("orgFlow",orgFlow);
            model.addAttribute("cfgFlow",cfgFlow);
            model.addAttribute("evalYear",evalYear);
            return "redirect:/eval/evalOrgQuery/downFile";
        }
        return "eval/evalOrgQuery/error";
    }

    @RequestMapping(value = {"/checkHasFile"}, method = {RequestMethod.POST})
    @ResponseBody
    public String checkHasFile(String evalYear, String orgFlow, String cfgFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        ExpertEvalCfg evalCfg = evalCfgBiz.readByFlow(cfgFlow);
        String baseDirs = InitConfig.getSysCfg("upload_base_dir");
        if (evalCfg == null) {
            return "评估指标不存在，请联系管理员！";
        }
        if(!"Y".equals(evalCfg.getIsFile()))
        {
            return "非文件评估指标，无法下载！";
        }
        if (StringUtil.isBlank(baseDirs)) {
            return "文件保存路径未配置，请联系管理员！";
        }
        String filePath = baseDirs + File.separator + "evalFiles";
        ExpertEvalResult result = evalCfgBiz.getOrgCfgEvalReustl(evalYear, orgFlow, evalCfg.getCfgFlow());
        if (result != null) {
            filePath +=File.separator +evalYear+"Result"+File.separator + orgFlow+ File.separator +
                    evalCfg.getFilePath().substring(evalCfg.getFilePath().indexOf(evalYear)+evalYear.length());
        }else{
            return "暂无评估结果文件，无法下载！";
        }
        File f = new File(filePath);
        if (!f.exists()) {
            return "评估结果文件不存在，请联系管理员！";
        }
        return "1";
    }
    @RequestMapping(value = {"/downFile"}, method = {RequestMethod.POST,RequestMethod.GET})
    public void downFile(String evalYear, String orgFlow, String cfgFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        ExpertEvalCfg evalCfg = evalCfgBiz.readByFlow(cfgFlow);
        String baseDirs = InitConfig.getSysCfg("upload_base_dir");
        String filePath = baseDirs + File.separator + "evalFiles";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        ExpertEvalResult result = evalCfgBiz.getOrgCfgEvalReustl(evalYear, orgFlow, evalCfg.getCfgFlow());
        if (result != null) {
            filePath +=File.separator +evalYear+"Result"+File.separator + orgFlow+ File.separator +
                    evalCfg.getFilePath().substring(evalCfg.getFilePath().indexOf(evalYear)+evalYear.length());
        }else{
            filePath += File.separator + evalCfg.getFilePath();
        }
        /*文件是否存在*/
        File downLoadFile = new File(filePath);
        byte[] data = null;
        long dataLength = 0;
		/*文件是否存在*/
        if (downLoadFile.exists()) {
            InputStream fis = new BufferedInputStream(new FileInputStream(downLoadFile));
            data = new byte[fis.available()];
            dataLength = downLoadFile.length();
            fis.read(data);
            fis.close();
        }
        String fileName=evalCfg.getFilePath().substring(evalCfg.getFilePath().lastIndexOf("\\"));
        fileName = new String(fileName.getBytes(), "ISO-8859-1");
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
    }
    @RequestMapping(value="/showOrgClinical",method={RequestMethod.POST,RequestMethod.GET})
    public String showOrgClinical(String orgFlow, HttpServletRequest request, Model model,String evalYear){

        return "eval/evalOrgQuery/showOrgClinicalMain";
    }
    @RequestMapping(value="/showOrgClinicalList",method={RequestMethod.POST,RequestMethod.GET})
    public String showOrgClinicalList(String orgFlow,String speId, HttpServletRequest request, Model model,String evalYear){
        if(StringUtil.isNotBlank(evalYear)&&StringUtil.isNotBlank(orgFlow)) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("orgFlow", orgFlow);
            paramMap.put("speId", speId);
            paramMap.put("evalYear", evalYear);
            List<Map<String,Object>> mapList=evalOrgBiz.showOrgClinicalList(paramMap);
            model.addAttribute("list",mapList);
        }
        return "eval/evalOrgQuery/showOrgClinicalList";
    }
    @RequestMapping(value="/checkClinicalDetail",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String checkClinicalDetail(String orgFlow,String speId, HttpServletRequest request, Model model,String evalYear){
        ExpertEvalCfg cfg=evalCfgBiz.getSpeCfg(evalYear,speId);
        if(cfg==null)
        {
            return "该基地未配置考核指标，无法查看详情！";
        }else{
            return "1";
        }
    }
    @RequestMapping(value="/showClinicalDetail",method={RequestMethod.POST,RequestMethod.GET})
    public String showClinicalDetail(String orgFlow,String speId, HttpServletRequest request, Model model,String evalYear){
        ExpertEvalCfg cfg=evalCfgBiz.getSpeCfg(evalYear,speId);
        if(cfg!=null) {
            List<ExpertEvalCfg> cfgList = evalCfgBiz.searchChildrenList(cfg.getCfgFlow());
            model.addAttribute("cfgList", cfgList);
            Map<String, ExpertEvalResult> resultMap = new HashMap<>();
            if (cfgList != null && cfgList.size() > 0) {
                for (ExpertEvalCfg c : cfgList) {
                    ExpertEvalResult result = evalCfgBiz.getOrgCfgEvalReustl(evalYear, orgFlow, c.getCfgFlow());
                    resultMap.put(c.getCfgFlow(), result);
                }
            }
            model.addAttribute("resultMap", resultMap);
        }
        return "eval/evalOrgQuery/showClinicalDetail";
    }
}