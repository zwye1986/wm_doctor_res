package com.pinde.res.ctrl.eval;


import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.PasswordUtil;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.eval.IEvalAppBiz;
import com.pinde.res.ctrl.upload.FileForm;
import com.pinde.core.commom.enums.UserStatusEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.util.FtpHelperUtil;
import com.pinde.sci.util.PasswordHelper;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/eval")
public class EvalAppController {
    private static Logger logger = LoggerFactory.getLogger(EvalAppController.class);

    @Autowired
    private IEvalAppBiz evalAppBiz;

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
        logger.error(this.getClass().getCanonicalName() + " some error happened", e);
        return "res/eval/500";
    }

    @RequestMapping(value = {"/version"}, method = {RequestMethod.GET})
    public String version(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        return "res/eval/version";
    }


    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test() {
        return "res/eval/test";
    }

    @RequestMapping(value = {"/remember"}, method = {RequestMethod.GET})
    public String remember(String userFlow, String roleId, String evalYear, String orgFlow,String cfgFlow, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userFlow", userFlow);
        session.setAttribute("roleId", roleId);
        session.setAttribute("evalYear", evalYear);
        session.setAttribute("orgFlow", orgFlow);
        session.setAttribute("cfgFlow", cfgFlow);
        return "/res/eval/test";
    }

    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
    public String login(String userCode, String userPasswd, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userCode)) {
            model.addAttribute("resultId", "01000101");
            model.addAttribute("resultType", "用户名为空");
            return "res/eval/login";
        }

        if (StringUtil.isEmpty(userPasswd)) {
            model.addAttribute("resultId", "01000102");
            model.addAttribute("resultType", "密码为空");
            return "res/eval/login";
        }

        //验证用户是否存在
        SysUser userinfo = evalAppBiz.getUserByCode(userCode);
        if (userinfo == null) {
            model.addAttribute("resultId", "01000103");
            model.addAttribute("resultType", "用户不存在");
        } else {
            String passwd = userinfo.getUserPasswd();
            String userFlow = userinfo.getUserFlow();

            //验证密码是否正确或者是否使用超级密码
            if (!PasswordUtil.isRootPass(userPasswd) &&
                    !passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(userFlow, userPasswd))) {
                model.addAttribute("resultId", "01000104");
                model.addAttribute("resultType", "用户名或密码错误");
                return "res/eval/login";
            }

            //验证用户是否锁定,锁定用户不能登录
            String userStatus = userinfo.getStatusId();
            model.addAttribute("userinfo", userinfo);
            if (UserStatusEnum.SysLocked.getId().equals(userStatus)) {
                model.addAttribute("resultId", "01000105");
                model.addAttribute("resultType", "该用户已被锁定");
                return "res/eval/login";
            }

            if (UserStatusEnum.Locked.getId().equals(userStatus)) {
                model.addAttribute("resultId", "01000105");
                model.addAttribute("resultType", "该用户已被停用");
                return "res/eval/login";
            }

            //验证用户是否有角色
            List<SysUserRole> userRoles = evalAppBiz.getSysUserRole(userFlow);
            if (userRoles == null || userRoles.isEmpty()) {
                model.addAttribute("resultId", "01000106");
                model.addAttribute("resultType", "用户未赋权");
                return "res/eval/login";
            }
            boolean isExpert = false;
            //获取当前配置的专家角色
            String expertRole = evalAppBiz.getCfgByCode("eval_expert_role_flow");

            //获取最先匹配到的角色,认定该用户的角色为匹配到的角色
            for (SysUserRole sur : userRoles) {
                String ur = sur.getRoleFlow();
                if (expertRole.equals(ur)) {
                    isExpert = true;
                    model.addAttribute("roleId", "Expert");
                    model.addAttribute("roleName", "专家");
                    break;
                }
            }
            if (!isExpert) {
                model.addAttribute("resultId", "01000107");
                model.addAttribute("resultType", "无权限登录");
                return "res/eval/login";
            }
        }
        return "res/eval/login";
    }

    @RequestMapping(value = {"/index"}, method = {RequestMethod.POST})
    public String index(String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "01000201");
            model.addAttribute("resultType", "用户流水号为空");
            return "res/eval/index";
        }

        String evalYear = evalAppBiz.getCfgByCode("eval_year");
        if (StringUtil.isBlank(evalYear)) {
            evalYear = DateUtil.getYear();
        }
        List<SysOrg> orgs = evalAppBiz.getExpertEvalOrg(evalYear, userFlow);
        model.addAttribute("orgs", orgs);
        model.addAttribute("evalYear", evalYear);
        return "res/eval/index";
    }

    @RequestMapping(value = {"/evalOrg"}, method = {RequestMethod.POST,RequestMethod.GET})
    public String evalOrg(String userFlow, String evalYear, String orgFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "01000301");
            model.addAttribute("resultType", "用户流水号为空");
            return "res/eval/evalOrg";
        }

        if (StringUtil.isEmpty(evalYear)) {
            model.addAttribute("resultId", "01000302");
            model.addAttribute("resultType", "评估年份为空");
            return "res/eval/evalOrg";
        }

        if (StringUtil.isEmpty(orgFlow)) {
            model.addAttribute("resultId", "01000303");
            model.addAttribute("resultType", "基地流水号为空");
            return "res/eval/evalOrg";
        }
        ExpertEvalCfg evalCfg = evalAppBiz.getExpertBaseCfg(evalYear);
        model.addAttribute("evalCfg", evalCfg);
        if (evalCfg == null) {
            model.addAttribute("resultId", "01000304");
            model.addAttribute("resultType", evalYear + "年评估指标未配置，请联系管理员！");
            return "res/eval/evalOrg";
        }
        Map<String, List<ExpertEvalCfg>> childrenMap = new HashMap<>();
        Map<String, ExpertEvalResult> resultMap = new HashMap<>();
        getChildrens(evalCfg, evalYear, userFlow, childrenMap, resultMap, orgFlow);
        model.addAttribute("childrenMap", childrenMap);
        model.addAttribute("resultMap", resultMap);
        return "res/eval/evalOrg";
    }

    @RequestMapping(value = {"/evalOrgFile"}, method = {RequestMethod.POST})
    public String evalOrgFile(String userFlow, String evalYear, String orgFlow, String cfgFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "01000301");
            model.addAttribute("resultType", "用户流水号为空");
            return "res/eval/evalOrgFile";
        }

        if (StringUtil.isEmpty(evalYear)) {
            model.addAttribute("resultId", "01000302");
            model.addAttribute("resultType", "评估年份为空");
            return "res/eval/evalOrgFile";
        }

        if (StringUtil.isEmpty(orgFlow)) {
            model.addAttribute("resultId", "01000303");
            model.addAttribute("resultType", "基地流水号为空");
            return "res/eval/evalOrgFile";
        }
        if (StringUtil.isEmpty(cfgFlow)) {
            model.addAttribute("resultId", "01000404");
            model.addAttribute("resultType", "基地流水号为空");
            return "res/eval/evalOrgFile";
        }
        ExpertEvalCfg evalCfg = evalAppBiz.readCfgByFlow(cfgFlow);
        model.addAttribute("evalCfg", evalCfg);
        if (evalCfg == null) {
            model.addAttribute("resultId", "01000304");
            model.addAttribute("resultType", "评估指标不存在，请联系管理员！");
            return "res/eval/evalOrgFile";
        }
        if(!"Y".equals(evalCfg.getIsFile()))
        {
            model.addAttribute("resultId", "01000304");
            model.addAttribute("resultType", "评估指标请求错误（非文件请求）！");
            return "res/eval/evalOrgUrl";
        }
        String baseDirs = evalAppBiz.getCfgByCode("upload_base_dir");
        if (StringUtil.isBlank(baseDirs)) {
            model.addAttribute("resultId", "01000304");
            model.addAttribute("resultType", "文件保存路径未配置，请联系管理员！");
            return "res/eval/evalOrgFile";
        }
        String filePath = baseDirs + File.separator + "evalFiles";
        ExpertEvalResult result = evalAppBiz.getOrgCfgEvalReustl(evalYear, orgFlow, evalCfg.getCfgFlow());
        model.addAttribute("result",result);
        if (result != null) {
            filePath +=File.separator +evalYear+"Result"+File.separator + orgFlow+ File.separator +
                    evalCfg.getFilePath().substring(evalCfg.getFilePath().indexOf(evalYear)+evalYear.length());
            FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
            String localFilePath=filePath;
            String ftpFileName=localFilePath.substring(localFilePath.lastIndexOf(File.separator)+1);
            String ftpDir=localFilePath.substring(baseDirs.length()+1,localFilePath.lastIndexOf(File.separator));
            ftpHelperUtil.downloadFile(localFilePath,ftpDir,ftpFileName);
        }else{
           filePath += File.separator + evalCfg.getFilePath();
        }
        File f = new File(filePath);
        if (!f.exists()) {
            model.addAttribute("resultId", "01000304");
            model.addAttribute("resultType", "评估指标文件不存在，请联系管理员！");
            return "res/eval/evalOrgFile";
        }
        return "res/eval/evalOrgFile";
    }

    @RequestMapping(value = {"/downFile"}, method = {RequestMethod.POST})
    public synchronized void downFile(String userFlow, String evalYear, String orgFlow, String cfgFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        ExpertEvalCfg evalCfg = evalAppBiz.readCfgByFlow(cfgFlow);
        String baseDirs = evalAppBiz.getCfgByCode("upload_base_dir");
        String filePath = baseDirs + File.separator + "evalFiles";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        ExpertEvalResult result = evalAppBiz.getOrgCfgEvalReustl(evalYear, orgFlow, evalCfg.getCfgFlow());
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
        String fileName=evalCfg.getFilePath().substring(evalCfg.getFilePath().lastIndexOf(File.separator)+1);
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

    @RequestMapping(value={"/addFile"},method={RequestMethod.POST})
    @ResponseBody
    public String addFile(FileForm form,String evalScore,String userFlow, String evalYear, String orgFlow, String cfgFlow,  HttpServletRequest request, HttpServletResponse response, Model model){
        Map<String,String> map=new HashMap<>();
        map.put("resultId", "200");
        map.put("resultType", "交易成功");
        boolean f=true;
        if(StringUtil.isBlank(userFlow))
        {
            f=false;
            map.put("resultId", "31703");
            map.put("resultType", "用户流水号为空！");
        }
        if(StringUtil.isBlank(evalYear))
        {
            f=false;
            map.put("resultId", "31703");
            map.put("resultType", "评估年份为空！");
        }
        if(StringUtil.isBlank(orgFlow))
        {
            f=false;
            map.put("resultId", "31703");
            map.put("resultType", "评估基地流水号为空！");
        }
        if(StringUtil.isBlank(cfgFlow))
        {
            f=false;
            map.put("resultId", "31703");
            map.put("resultType", "配置流水号为空！");
        }
        if(form.getUploadFile()==null){
            f=false;
            map.put("resultId", "31703");
            map.put("resultType", "上传文件不能为空");
        }
        ExpertEvalCfg evalCfg = evalAppBiz.readCfgByFlow(cfgFlow);
        if (evalCfg == null) {
            f=false;
            map.put("resultId", "31703");
            map.put("resultType", "评估指标不存在!");
        }
        String baseDirs = evalAppBiz.getCfgByCode("upload_base_dir");
        if (StringUtil.isBlank(baseDirs)) {
            f=false;
            map.put("resultId", "31703");
            map.put("resultType", "文件保存路径未配置!");
        }
        if(f) {
            int count= evalAppBiz.saveCfgResultByFile(form,evalScore,evalYear,userFlow,orgFlow,cfgFlow);
            if(count==0)
            {
                map.put("resultId", "31703");
                map.put("resultType", "保存失败！!");
            }
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = {"/evalOrgUrl"}, method = {RequestMethod.POST,RequestMethod.GET})
    public String evalOrgUrl(String userFlow, String evalYear, String orgFlow, String cfgFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "01000401");
            model.addAttribute("resultType", "用户流水号为空");
            return "res/eval/evalOrgUrl";
        }

        if (StringUtil.isEmpty(evalYear)) {
            model.addAttribute("resultId", "01000402");
            model.addAttribute("resultType", "评估年份为空");
            return "res/eval/evalOrgUrl";
        }

        if (StringUtil.isEmpty(orgFlow)) {
            model.addAttribute("resultId", "01000403");
            model.addAttribute("resultType", "基地流水号为空");
            return "res/eval/evalOrgUrl";
        }
        if (StringUtil.isEmpty(cfgFlow)) {
            model.addAttribute("resultId", "01000404");
            model.addAttribute("resultType", "评估配置流水号为空");
            return "res/eval/evalOrgUrl";
        }
        ExpertEvalCfg evalCfg = evalAppBiz.readCfgByFlow(cfgFlow);
        model.addAttribute("evalCfg", evalCfg);
        if (evalCfg == null) {
            model.addAttribute("resultId", "01000304");
            model.addAttribute("resultType", "评估指标不存在，请联系管理员！");
            return "res/eval/evalOrgUrl";
        }
        if(!"U".equals(evalCfg.getIsFile()))
        {
            model.addAttribute("resultId", "01000304");
            model.addAttribute("resultType", "评估指标请求错误（非页面请求）！");
            return "res/eval/evalOrgUrl";
        }
        SysUser user=evalAppBiz.readSysUser(userFlow);
        model.addAttribute("user", user);
        ExpertEvalResult result = evalAppBiz.getOrgCfgEvalReustl(evalYear, orgFlow, evalCfg.getCfgFlow());
        if (result != null && StringUtil.isNotBlank(result.getEvalContent())) {
            Map<String, Object> formDataMap = evalAppBiz.parseContent(result.getEvalContent());
            model.addAttribute("formDataMap", formDataMap);
        }
        String name=evalCfg.getFilePath();
        if(name.lastIndexOf(".")>=0)
        {
            name=name.substring(0,name.lastIndexOf("."));
        }

//        String jspPath= URLEncoder.encode("res/eval/base/" + name, "utf-8");
        String jspPath="res/eval/base/" + name;
        return jspPath;
    }
    @RequestMapping(value = {"/saveEvalResult"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public synchronized String saveEvalResult(String jsonData, HttpServletRequest request, HttpServletResponse response, Model model) throws DocumentException {
        EvalResultBean resultBean=null;
        try {
            resultBean=JSON.parseObject(jsonData, EvalResultBean.class);
        }catch (Exception e){
            return "提交数据格式不正确！";
        }
        String userFlow=resultBean.getUserFlow();
        String evalYear=resultBean.getEvalYear();
        String orgFlow=resultBean.getOrgFlow();
        String cfgFlow=resultBean.getCfgFlow();
        if (StringUtil.isEmpty(userFlow)) {
            return "用户流水号为空";
        }
        if (StringUtil.isEmpty(evalYear)) {
            return "评估年份为空";
        }
        if (StringUtil.isEmpty(orgFlow)) {
            return "基地流水号为空";
        }
        if (StringUtil.isEmpty(cfgFlow)) {
            return "评估配置流水号为空";
        }
        ExpertEvalCfg evalCfg = evalAppBiz.readCfgByFlow(cfgFlow);
        if (evalCfg == null) {
            return "评估指标不存在，请联系管理员！";
        }
        int count=evalAppBiz.saveCfgResultNotFile(resultBean,evalYear, userFlow,  orgFlow, cfgFlow);
        if(count==0)
        {
            return GlobalConstant.SAVE_FAIL;
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }
    @RequestMapping(value = {"/saveEvalResult2"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public synchronized String saveEvalResult2(String userFlow, String evalYear, String orgFlow, String cfgFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (StringUtil.isEmpty(userFlow)) {
            return "用户流水号为空";
        }
        if (StringUtil.isEmpty(evalYear)) {
            return "评估年份为空";
        }
        if (StringUtil.isEmpty(orgFlow)) {
            return "基地流水号为空";
        }
        if (StringUtil.isEmpty(cfgFlow)) {
            return "评估配置流水号为空";
        }
        ExpertEvalCfg evalCfg = evalAppBiz.readCfgByFlow(cfgFlow);
        if (evalCfg == null) {
            return "评估指标不存在，请联系管理员！";
        }
        int count=evalAppBiz.saveCfgResultNotFile2(request,evalYear, userFlow,  orgFlow, cfgFlow);
        if(count==0)
        {
            return GlobalConstant.SAVE_FAIL;
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    private void getChildrens(ExpertEvalCfg evalCfg, String evalYear, String userFlow, Map<String, List<ExpertEvalCfg>> childrenMap,
                              Map<String, ExpertEvalResult> resultMap, String orgFlow) {
        if (evalCfg != null) {
            List<ExpertEvalCfg> childrens = evalAppBiz.getExpertCfgChildrens(evalCfg.getCfgFlow(), evalYear, userFlow, orgFlow);
            if (childrens != null) {
                childrenMap.put(evalCfg.getCfgFlow(), childrens);
                ExpertEvalResult result = evalAppBiz.getOrgCfgEvalReustl(evalYear, orgFlow, evalCfg.getCfgFlow());
                if (result != null)
                    resultMap.put(evalCfg.getCfgFlow(), result);
                for (ExpertEvalCfg c : childrens) {
                    getChildrens(c, evalYear, userFlow, childrenMap, resultMap, orgFlow);
                }
            }
        }
    }


}

