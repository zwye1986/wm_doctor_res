package com.pinde.res.ctrl.nydyjs;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.NfyyGlobalConstant;
import com.pinde.app.common.PasswordUtil;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.CodeUtil;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.njmu.INjmuBiz;
import com.pinde.res.biz.nydyjs.INydyjsAppBiz;
import com.pinde.res.model.njmu.mo.Userinfo;
import com.pinde.sci.model.mo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.*;


@Controller
@RequestMapping("/res/nydyjs")
public class NydyjsAppController {

    private static Logger logger = LoggerFactory.getLogger(NydyjsAppController.class);

    private boolean alert = true;

    @Autowired
    private INjmuBiz njmuBiz;
    @Autowired
    private INydyjsAppBiz nydyjsAppBiz;

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
        logger.error(this.getClass().getCanonicalName() + " some error happened", e);
        return "res/nydyjs/500";
    }

    @RequestMapping(value = {"/remember"}, method = {RequestMethod.GET})
    public String remember(String userFlow, String cySecId, String docFlow, String funcTypeId, String funcFlow, String hosSecId, String roleId, String hosCySecId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userFlow", userFlow);
        session.setAttribute("roleId", roleId);
        session.setAttribute("cySecId", cySecId);
        session.setAttribute("docFlow", docFlow);
        session.setAttribute("hosSecId", hosSecId);
        session.setAttribute("hosCySecId", hosCySecId);
        session.setAttribute("funcTypeId", funcTypeId);
        session.setAttribute("funcFlow", funcFlow);
        return "res/nydyjs/test";
    }

    @RequestMapping(value = {"/version"}, method = {RequestMethod.GET})
    public String version(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
        model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
        return "res/nydyjs/version";
    }

    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        return "res/nydyjs/test";
    }

    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
    public String login(String userCode, String userPasswd, Model model) {
        String result = "res/nydyjs/login";
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userCode)) {
            model.addAttribute("resultId", "30101");
            model.addAttribute("resultType", "用户名为空");
            return result;
        }
        if (StringUtil.isEmpty(userPasswd)) {
            model.addAttribute("resultId", "30102");
            model.addAttribute("resultType", "密码为空");
            return result;
        }
        String userPasswdMd5 = _md5Dnet(userPasswd, userCode);
        Userinfo userinfo = njmuBiz.login(userCode, userPasswdMd5, "6");

        if (userinfo == null) {
            model.addAttribute("resultId", "30103");
            model.addAttribute("resultType", "用户名不存在");
        } else {
            //超级密码
            //logger.debug("----------" + userPasswd);
            //logger.debug("----------" + StringUtil.isEquals("njpdxx", userPasswd));
            if (PasswordUtil.isRootPass(userPasswd)) {
                model.addAttribute("userinfo", userinfo);
            } else if (StringUtil.isEquals(userinfo.getPassword(), userPasswdMd5)) {
                model.addAttribute("userinfo", userinfo);
            } else {
                model.addAttribute("resultId", "30104");
                model.addAttribute("resultType", "用户名或密码错误");
            }
        }
        return result;
    }
    private String _md5Dnet(String pTocrypt, String pKey) {
        String ret = "";
        byte[] data3 = new byte[80];
        try {
            pTocrypt = StringUtil.decodeString(pTocrypt).toUpperCase();
            pKey = StringUtil.decodeString(pKey).toUpperCase();
            byte[] data1 = pTocrypt.getBytes("UTF-8");
            byte[] data2 = pKey.getBytes("UTF-8");
            int d1len = 0;
            int d2len = 0;
            for (int i = 0; i < 80; i++) {
                if ((i % 2) == 0 && d1len < data1.length) {
                    data3[i] = data1[d1len++];
                } else if ((i % 2) == 1 && d2len < data2.length) {
                    data3[i] = data2[d2len++];
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            ret = CodeUtil.md5(new String(data3,"UTF-8")).toUpperCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @RequestMapping(value = {"/index"}, method = {RequestMethod.GET})
    public String index(String userFlow, String roleId, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "30301");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/nydyjs/index";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "30302");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/nydyjs/index";
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "30303");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/nydyjs/index";
        }
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "30304");
            model.addAttribute("resultType", "用户不存在");
            return "res/nydyjs/index";
        }
        int noAuditNumber = nydyjsAppBiz.getNotAuditNum(userFlow);//待审核数据
        int noAppealNumber = nydyjsAppBiz.getAppealNotAuditNum(userFlow);//申诉待审核
        int noAfterSummNumber = nydyjsAppBiz.getAfterSummNotAuditNum(userFlow);//出科小结待审核
        int noAfterEvaNumber = nydyjsAppBiz.getAfterEvaNotAuditNum(userFlow);//出科考核待审核
        int currStudentHe = nydyjsAppBiz.getIsCurrentNum(userFlow);//轮转学员人数
        int studentNum = nydyjsAppBiz.getIsSchNum(userFlow);//已出科学员人数

        model.addAttribute("studentNum", studentNum);
        model.addAttribute("isCurrent", currStudentHe);
        model.addAttribute("noAuditNumber", noAuditNumber);
        model.addAttribute("noAppealNumber", noAppealNumber);
        model.addAttribute("noAfterSummNumber", noAfterSummNumber);
        model.addAttribute("noAfterEvaNumber", noAfterEvaNumber);
        model.addAttribute("user", user);
        return "res/nydyjs/index";
    }

    /**
     * 学员列表
     *
     * @param userFlow
     * @param roleId
     * @param studentName
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = {"/studentList"}, method = {RequestMethod.POST})
    public String studentList(String userFlow, String roleId, String studentName, String sessionNumber, Integer pageIndex, Integer pageSize, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/nydyjs/studentList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "30402");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/nydyjs/studentList";
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "30403");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/nydyjs/studentList";
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "30404");
            model.addAttribute("resultType", "用户不存在");
            return "res/nydyjs/studentList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30405");
            model.addAttribute("resultType", "当前页码为空");
            return "res/nydyjs/studentList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30406");
            model.addAttribute("resultType", "每页条数为空");
            return "res/nydyjs/studentList";
        }
        List<Map<String, String>> resDoctorSchProcess = null;
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("teacherUserFlow", userFlow);
        param.put("userName", studentName);
        param.put("sessionNumber", sessionNumber);
        param.put("startRow", ((pageIndex - 1) * pageSize) + 1);
        param.put("endRow", (pageIndex) * pageSize);
        resDoctorSchProcess = nydyjsAppBiz.getStudentList(param);
        System.err.println(JSON.toJSON(resDoctorSchProcess));
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        model.addAttribute("dataCount", nydyjsAppBiz.getStudentList(param).size());
        model.addAttribute("list", resDoctorSchProcess);
        return "res/nydyjs/studentList";
    }

    /**
     * 数据审核学员列表
     *
     * @param userFlow
     * @param roleId
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = {"/dataStudentList"}, method = {RequestMethod.GET})
    public String dataStudentList(String userFlow, String roleId, Integer pageIndex, Integer pageSize, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "30501");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/nydyjs/dataStudentList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "30502");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/nydyjs/dataStudentList";
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "30503");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/nydyjs/dataStudentList";
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "30504");
            model.addAttribute("resultType", "用户不存在");
            return "res/nydyjs/dataStudentList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30505");
            model.addAttribute("resultType", "当前页码为空");
            return "res/nydyjs/dataStudentList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30506");
            model.addAttribute("resultType", "每页条数为空");
            return "res/nydyjs/dataStudentList";
        }
        List<Map<String, String>> resDoctorSchProcess = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("teacherUserFlow", userFlow);
        param.put("startRow", ((pageIndex - 1) * pageSize) + 1);
        param.put("endRow", (pageIndex) * pageSize);
        resDoctorSchProcess = nydyjsAppBiz.getDataStudentList(param);
        System.err.println(JSON.toJSON(resDoctorSchProcess));
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        model.addAttribute("dataCount", nydyjsAppBiz.getDataStudentList(param).size());
        model.addAttribute("list", resDoctorSchProcess);
        return "res/nydyjs/dataStudentList";
    }

    /**
     * 【病历】或【病种】或【操作】或【手术】或【活动】待审核数量
     *
     * @param userFlow
     * @param roleId
     * @param cySecId
     * @param docFlow
     * @param model
     * @return
     */
    @RequestMapping(value = {"/fiveDataNoAudit"}, method = {RequestMethod.GET})
    public String fiveDataNoAudit(String userFlow, String roleId, String cySecId, String docFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "30901");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/nydyjs/fiveDataNoAudit";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "30902");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/nydyjs/fiveDataNoAudit";
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "30903");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/nydyjs/fiveDataNoAudit";
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "30904");
            model.addAttribute("resultType", "用户不存在");
            return "res/nydyjs/fiveDataNoAudit";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "学员流水号为空");
            return "res/nydyjs/fiveDataNoAudit";
        }
        if (StringUtil.isBlank(cySecId)) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "轮转id为空");
            return "res/nydyjs/fiveDataNoAudit";
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userFlow", userFlow);
        param.put("cySecId", cySecId);
        param.put("docFlow", docFlow);
        param.put("biaoJi", "Y");
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        model.addAttribute("CaseRegistryNoAudit", nydyjsAppBiz.getCaseRegistryList(param).size());//大病历
        model.addAttribute("DiseaseRegistryNoAudit", nydyjsAppBiz.getDiseaseRegistryList(param).size());//病种
        model.addAttribute("OperateSkillNoAudit", nydyjsAppBiz.getOperateSkillList(param).size());//操作
        model.addAttribute("PossSkillNoAudit", nydyjsAppBiz.getPossSkillList(param).size());//手术
        model.addAttribute("ActivityNoAudit", nydyjsAppBiz.getActivityList(param).size());//活动
        return "res/nydyjs/fiveDataNoAudit";
    }

    /**
     * 大病历列表
     *
     * @param userFlow
     * @param roleId
     * @param biaoJi    为y时表示查询所有未审核数据
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = {"/caseRegistryList"}, method = {RequestMethod.GET})
    public String caseRegistryList(String userFlow, String roleId, String biaoJi, String cySecId, String docFlow, Integer pageIndex, Integer pageSize, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "301001");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/nydyjs/caseRegistryList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "301002");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/nydyjs/caseRegistryList";
        }
        if (StringUtil.isBlank(cySecId)) {
            model.addAttribute("resultId", "301003");
            model.addAttribute("resultType", "科室流水号为空");
            return "res/nydyjs/caseRegistryList";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "301004");
            model.addAttribute("resultType", "学员流水号为空");
            return "res/nydyjs/caseRegistryList";
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "301005");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/nydyjs/caseRegistryList";
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "301006");
            model.addAttribute("resultType", "用户不存在");
            return "res/nydyjs/caseRegistryList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "301007");
            model.addAttribute("resultType", "当前页码为空");
            return "res/nydyjs/caseRegistryList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "301008");
            model.addAttribute("resultType", "每页条数为空");
            return "res/nydyjs/caseRegistryList";
        }

        List<Map<String, String>> caseRegistryList = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("cySecId", cySecId);
        param.put("docFlow", docFlow);
        param.put("biaoJi", biaoJi);
        param.put("startRow", ((pageIndex - 1) * pageSize) + 1);
        param.put("endRow", (pageIndex) * pageSize);
        Map<String, String> processMap = nydyjsAppBiz.getCaseRegistryProcess(param);
        caseRegistryList = nydyjsAppBiz.getCaseRegistryList(param);
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        model.addAttribute("dataCount", nydyjsAppBiz.getCaseRegistryList(param).size());
        model.addAttribute("list", caseRegistryList);
        model.addAttribute("processMap", processMap);
        return "res/nydyjs/caseRegistryList";
    }

    /**
     * 病种列表
     *
     * @param userFlow
     * @param roleId
     * @param biaoJi    为y时表示查询所有未审核数据
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = {"/diseaseRegistryList"}, method = {RequestMethod.GET})
    public String diseaseRegistryList(String userFlow, String roleId, String biaoJi, String cySecId, String docFlow, Integer pageIndex, Integer pageSize, Model model) {
        String result = "res/nydyjs/diseaseRegistryList";
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "301101");
            model.addAttribute("resultType", "用户标识符为空");
            return result;
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "301102");
            model.addAttribute("resultType", "用户角色ID为空");
            return result;
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "301103");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return result;
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "301104");
            model.addAttribute("resultType", "用户不存在");
            return result;
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "301105");
            model.addAttribute("resultType", "学员流水号为空");
            return result;
        }
        if (StringUtil.isBlank(cySecId)) {
            model.addAttribute("resultId", "301106");
            model.addAttribute("resultType", "科室流水号为空");
            return result;
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "301107");
            model.addAttribute("resultType", "当前页码为空");
            return result;
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "301108");
            model.addAttribute("resultType", "每页条数为空");
            return result;
        }

        List<Map<String, String>> caseRegistryList = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userFlow", userFlow);
        param.put("cySecId", cySecId);
        param.put("docFlow", docFlow);
        param.put("biaoJi", biaoJi);
        param.put("startRow", ((pageIndex - 1) * pageSize) + 1);
        param.put("endRow", (pageIndex) * pageSize);
        Map<String, String> processMap = nydyjsAppBiz.getDiseaseProcess(param);
        caseRegistryList = nydyjsAppBiz.getDiseaseRegistryList(param);
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        model.addAttribute("dataCount", nydyjsAppBiz.getDiseaseRegistryList(param).size());
        model.addAttribute("list", caseRegistryList);
        model.addAttribute("processMap", processMap);
        return result;
    }

    /**
     * 操作列表
     *
     * @param userFlow
     * @param roleId
     * @param biaoJi    为y时表示查询所有未审核数据
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = {"/operateSkillList"}, method = {RequestMethod.GET})
    public String operateSkillList(String userFlow, String roleId, String biaoJi, String cySecId, String docFlow, Integer pageIndex, Integer pageSize, Model model) {
        String result = "res/nydyjs/operateSkillList";
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "301201");
            model.addAttribute("resultType", "用户标识符为空");
            return result;
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "301202");
            model.addAttribute("resultType", "用户角色ID为空");
            return result;
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "301203");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return result;
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "301204");
            model.addAttribute("resultType", "用户不存在");
            return result;
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "301205");
            model.addAttribute("resultType", "学员流水号为空");
            return result;
        }
        if (StringUtil.isBlank(cySecId)) {
            model.addAttribute("resultId", "301206");
            model.addAttribute("resultType", "科室流水号为空");
            return result;
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "301207");
            model.addAttribute("resultType", "当前页码为空");
            return result;
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "301208");
            model.addAttribute("resultType", "每页条数为空");
            return result;
        }

        List<Map<String, String>> caseRegistryList = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userFlow", userFlow);
        param.put("cySecId", cySecId);
        param.put("docFlow", docFlow);
        param.put("biaoJi", biaoJi);
        param.put("startRow", ((pageIndex - 1) * pageSize) + 1);
        param.put("endRow", (pageIndex) * pageSize);
        Map<String, String> processMap = nydyjsAppBiz.getSkillProcess(param);
        caseRegistryList = nydyjsAppBiz.getOperateSkillList(param);
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        model.addAttribute("dataCount", nydyjsAppBiz.getOperateSkillList(param).size());
        model.addAttribute("list", caseRegistryList);
        model.addAttribute("processMap", processMap);
        return result;
    }

    /**
     * 手术列表
     *
     * @param userFlow
     * @param roleId
     * @param biaoJi    为y时表示查询所有未审核数据
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = {"/possSkillList"}, method = {RequestMethod.GET})
    public String possSkillList(String userFlow, String roleId, String biaoJi, String cySecId, String docFlow, Integer pageIndex, Integer pageSize, Model model) {
        String result = "res/nydyjs/possSkillList";
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "301301");
            model.addAttribute("resultType", "用户标识符为空");
            return result;
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "301302");
            model.addAttribute("resultType", "用户角色ID为空");
            return result;
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "301303");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return result;
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "301304");
            model.addAttribute("resultType", "用户不存在");
            return result;
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "301305");
            model.addAttribute("resultType", "学员流水号为空");
            return result;
        }
        if (StringUtil.isBlank(cySecId)) {
            model.addAttribute("resultId", "301306");
            model.addAttribute("resultType", "科室流水号为空");
            return result;
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "301307");
            model.addAttribute("resultType", "当前页码为空");
            return result;
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "301308");
            model.addAttribute("resultType", "每页条数为空");
            return result;
        }

        List<Map<String, String>> caseRegistryList = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userFlow", userFlow);
        param.put("cySecId", cySecId);
        param.put("docFlow", docFlow);
        param.put("biaoJi", biaoJi);
        param.put("startRow", ((pageIndex - 1) * pageSize) + 1);
        param.put("endRow", (pageIndex) * pageSize);
        Map<String, String> processMap = nydyjsAppBiz.getPossSkillProcess(param);
        caseRegistryList = nydyjsAppBiz.getPossSkillList(param);
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        model.addAttribute("dataCount", nydyjsAppBiz.getPossSkillList(param).size());
        model.addAttribute("list", caseRegistryList);
        model.addAttribute("processMap", processMap);
        return result;
    }

    /**
     * 活动列表
     *
     * @param userFlow
     * @param roleId
     * @param biaoJi    为y时表示查询所有未审核数据
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = {"/activityList"}, method = {RequestMethod.GET})
    public String activityList(String userFlow, String roleId, String biaoJi, String docFlow, Integer pageIndex, Integer pageSize, Model model) {
        String result = "res/nydyjs/activityList";
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "301401");
            model.addAttribute("resultType", "用户标识符为空");
            return result;
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "301402");
            model.addAttribute("resultType", "用户角色ID为空");
            return result;
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "301403");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return result;
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "301404");
            model.addAttribute("resultType", "用户不存在");
            return result;
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "301405");
            model.addAttribute("resultType", "学员流水号为空");
            return result;
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "301406");
            model.addAttribute("resultType", "当前页码为空");
            return result;
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "301407");
            model.addAttribute("resultType", "每页条数为空");
            return result;
        }

        List<Map<String, String>> caseRegistryList = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userFlow", userFlow);
        param.put("docFlow", docFlow);
        param.put("biaoJi", biaoJi);
        param.put("startRow", ((pageIndex - 1) * pageSize) + 1);
        param.put("endRow", (pageIndex) * pageSize);
        caseRegistryList = nydyjsAppBiz.getActivityList(param);
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        model.addAttribute("dataCount", nydyjsAppBiz.getActivityList(param).size());
        model.addAttribute("list", caseRegistryList);
        return result;
    }

    @RequestMapping(value = {"/dataBatchAudit"}, method = {RequestMethod.POST})
    public String dataBatchAudit(String userFlow, String roleId, String type, String cySecId, String docFlow, Model model) {
        String result = "res/nydyjs/success";
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "301601");
            model.addAttribute("resultType", "用户标识符为空");
            return result;
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "301602");
            model.addAttribute("resultType", "用户角色ID为空");
            return result;
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "301603");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return result;
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "301604");
            model.addAttribute("resultType", "用户不存在");
            return result;
        }
        if (StringUtil.isBlank(cySecId)) {
            model.addAttribute("resultId", "301605");
            model.addAttribute("resultType", "科室流水号为空");
            return result;
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "301606");
            model.addAttribute("resultType", "学员流水号为空");
            return result;
        }
        if (StringUtil.isBlank(type)) {
            model.addAttribute("resultId", "301607");
            model.addAttribute("resultType", "批量审核数据类型为空");
            return result;
        }


        List<Map<String, String>> list = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userFlow", userFlow);
        param.put("cySecId", cySecId);
        param.put("docFlow", docFlow);
        param.put("biaoJi", "Y");
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        String classId = "";
        if (type.equals("1")) {
            classId = "1";
            list = nydyjsAppBiz.getCaseRegistryList(param);
        } else if (type.equals("2")) {
            classId = "2";
            list = nydyjsAppBiz.getDiseaseRegistryList(param);
        } else if (type.equals("3")) {
            classId = "3";
            list = nydyjsAppBiz.getOperateSkillList(param);
        } else if (type.equals("4")) {
            classId = "4";
            list = nydyjsAppBiz.getPossSkillList(param);
        }
        param.put("classId", classId);
        int count = 0;
        if (list != null && list.size() > 0) {
            for (Map<String, String> map : list) {
                param.put("fromId", map.get("fromId"));
                param.put("checkStatus", "1");
                param.put("reviewIndex", "9");
                param.put("recId", map.get("recId"));
                param.put("content", "");
                int num = nydyjsAppBiz.dataAudit(param);
                if (num == 3) {
                    count++;
                }
            }
            model.addAttribute("resultType", "批量审核成功:" + count + "条数据");
        } else {
            model.addAttribute("resultType", "无待审核数据");
        }
        return result;
    }

    /**
     * 单条数据审核
     *
     * @param userFlow
     * @param roleId
     * @param recId
     * @param docFlow
     * @param fromId
     * @param classId
     * @param checkStatus
     * @param model
     * @return
     */
    @RequestMapping(value = {"/dataAudit"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String dataAudit(String userFlow, String roleId, String recId, String docFlow,
                            String reviewIndex,String content,
                            String fromId, String classId, String checkStatus, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "301501");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "301502");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/nydyjs/success";
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "301503");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/nydyjs/success";
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "301504");
            model.addAttribute("resultType", "用户不存在");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(recId)) {
            model.addAttribute("resultId", "301505");
            model.addAttribute("resultType", "数据流水号为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(reviewIndex)) {
            model.addAttribute("resultId", "301506");
            model.addAttribute("resultType", "审核意见下拉为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "301507");
            model.addAttribute("resultType", "学员流水号为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(fromId)) {
            model.addAttribute("resultId", "301508");
            model.addAttribute("resultType", "数据流水号为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(classId)) {
            model.addAttribute("resultId", "301509");
            model.addAttribute("resultType", "数据类型为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(checkStatus)) {
            model.addAttribute("resultId", "301510");
            model.addAttribute("resultType", "审核状态为空");
            return "res/nydyjs/success";
        }
        List<String> checkStatuss = new ArrayList<>();
        checkStatuss.add("1");//审核通过
        checkStatuss.add("2");//审核不通过
        if (!checkStatuss.contains(checkStatus)) {
            model.addAttribute("resultId", "301511");
            model.addAttribute("resultType", "审核状态只能是通过或不通过");
            return "res/nydyjs/success";
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userFlow", userFlow);
        param.put("docFlow", docFlow);
        param.put("classId", classId);
        param.put("fromId", fromId);
        param.put("checkStatus", checkStatus);
        param.put("reviewIndex", reviewIndex);
        param.put("content", content);
//        if (checkStatus.equals("1"))//审核通过  评语状态 9：同意
//            param.put("reviewIndex", "9");
//        if (checkStatus.equals("2"))//审核不通过  评语状态 14：不合格
//            param.put("reviewIndex", "14");
        param.put("recId", recId);
        int count = nydyjsAppBiz.dataAudit(param);
        if (count != 3) {
            model.addAttribute("resultId", "301512");
            model.addAttribute("resultType", "审核失败");
        }
        return "res/nydyjs/success";
    }

    @RequestMapping(value = {"/activityBatchAudit"}, method = {RequestMethod.POST})
    public String activityBatchAudit(String userFlow, String roleId, String docFlow, Model model) {
        String result = "res/nydyjs/success";
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "301801");
            model.addAttribute("resultType", "用户标识符为空");
            return result;
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "301802");
            model.addAttribute("resultType", "用户角色ID为空");
            return result;
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "301803");
            model.addAttribute("resultType", "学员流水号为空");
            return result;
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "301804");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return result;
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "301805");
            model.addAttribute("resultType", "用户不存在");
            return result;
        }


        List<Map<String, String>> list = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userFlow", userFlow);
        param.put("docFlow", docFlow);
        param.put("biaoJi", "Y");
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        list = nydyjsAppBiz.getActivityList(param);
        int count = 0;
        if (list != null && list.size() > 0) {
            for (Map<String, String> map : list) {
                param.put("checkStatus", "1");
                param.put("content", "通过");
                param.put("recId", map.get("recId"));
                int num = nydyjsAppBiz.activityAudit(param);
                if (num == 2) {
                    count++;
                }
            }
            model.addAttribute("resultType", "批量审核成功:" + count + "条数据");
        } else {
            model.addAttribute("resultType", "无待审核数据");
        }
        return result;
    }

    /**
     * 活动单个审核
     *
     * @param userFlow
     * @param roleId
     * @param recId
     * @param docFlow
     * @param checkStatus
     * @param model
     * @return
     */
    @RequestMapping(value = {"/activityAudit"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String activityAudit(String userFlow, String roleId, String recId,String content, String docFlow, String checkStatus, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "301701");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "301702");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(recId)) {
            model.addAttribute("resultId", "301703");
            model.addAttribute("resultType", "数据流水号为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "301704");
            model.addAttribute("resultType", "学员流水号为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(checkStatus)) {
            model.addAttribute("resultId", "301705");
            model.addAttribute("resultType", "审核状态为空");
            return "res/nydyjs/success";
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "301706");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/nydyjs/success";
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "301707");
            model.addAttribute("resultType", "用户不存在");
            return "res/nydyjs/success";
        }
        List<String> checkStatuss = new ArrayList<>();
        checkStatuss.add("1");//审核通过
        checkStatuss.add("2");//审核不通过
        if (!checkStatuss.contains(checkStatus)) {
            model.addAttribute("resultId", "301708");
            model.addAttribute("resultType", "审核状态只能是通过或不通过");
            return "res/nydyjs/success";
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userFlow", userFlow);
        param.put("docFlow", docFlow);
        param.put("checkStatus", checkStatus);
        param.put("content", content);
//        if (checkStatus.equals("1"))//审核通过  评语状态 9：同意
//            param.put("content", "通过");
//        if (checkStatus.equals("2"))//审核不通过  评语状态 14：不合格
//            param.put("content", "不通过");
        param.put("recId", recId);
        int count = nydyjsAppBiz.activityAudit(param);
        if (count != 2) {
            model.addAttribute("resultId", "301709");
            model.addAttribute("resultType", "审核失败");
        }
        return "res/nydyjs/success";
    }


    /**
     * 申诉审核学员列表
     *
     * @param userFlow
     * @param roleId
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = {"/appealStudentList"}, method = {RequestMethod.GET})
    public String appealStudentList(String userFlow, String roleId, Integer pageIndex, Integer pageSize, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "30601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/nydyjs/studentList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "30602");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/nydyjs/studentList";
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "30603");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/nydyjs/studentList";
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "30604");
            model.addAttribute("resultType", "用户不存在");
            return "res/nydyjs/studentList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30605");
            model.addAttribute("resultType", "当前页码为空");
            return "res/nydyjs/studentList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30606");
            model.addAttribute("resultType", "每页条数为空");
            return "res/nydyjs/studentList";
        }
        List<Map<String, String>> resDoctorSchProcess = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("teacherUserFlow", userFlow);
        param.put("startRow", ((pageIndex - 1) * pageSize) + 1);
        param.put("endRow", (pageIndex) * pageSize);
        resDoctorSchProcess = nydyjsAppBiz.appealStudentList(param);
        System.err.println(JSON.toJSON(resDoctorSchProcess));
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        model.addAttribute("dataCount", nydyjsAppBiz.appealStudentList(param).size());
        model.addAttribute("list", resDoctorSchProcess);
        return "res/nydyjs/studentList";
    }

    /**
     * 申诉【病种】、【操作】、【手术】数据列表
     *
     * @param userFlow
     * @param roleId
     * @param docFlow
     * @param model
     * @return
     */
    @RequestMapping(value = {"/appealDataList"}, method = {RequestMethod.GET})
    public String appealDataList(String userFlow, String roleId, String dataType, String docFlow,
                                 String hosCySecId,
                                 String biaoJi, Integer pageIndex, Integer pageSize, Model model) {
        String result = "res/nydyjs/appealDataList";
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "301901");
            model.addAttribute("resultType", "用户标识符为空");
            return result;
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "301902");
            model.addAttribute("resultType", "用户角色ID为空");
            return result;
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "301903");
            model.addAttribute("resultType", "学员id为空");
            return result;
        }
        if (StringUtil.isBlank(hosCySecId)) {
            model.addAttribute("resultId", "301904");
            model.addAttribute("resultType", "轮转Id为空");
            return result;
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "301905");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return result;
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "301906");
            model.addAttribute("resultType", "用户不存在");
            return result;
        }
//		List<String> dataTypes=new ArrayList<>();
//		dataTypes.add("1");//病种
//		dataTypes.add("2");//操作
//		dataTypes.add("3");//手术
//		if(StringUtil.isNotBlank(dataType)){
//			if(!dataTypes.contains(dataType))
//			{
//				model.addAttribute("resultId", "3011102");
//				model.addAttribute("resultType", "数据类型只能是病种或操作或手术");
//				return result;
//			}
//		}
        if (pageIndex == null) {
            model.addAttribute("resultId", "301907");
            model.addAttribute("resultType", "当前页码为空");
            return result;
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "301908");
            model.addAttribute("resultType", "每页条数为空");
            return result;
        }
        List<Map<String, String>> list = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userFlow", userFlow);
        //param.put("dataType", dataType);
        param.put("docFlow", docFlow);
        param.put("biaoJi", biaoJi);
        param.put("startRow", ((pageIndex - 1) * pageSize) + 1);
        param.put("endRow", (pageIndex) * pageSize);
        list = nydyjsAppBiz.appealDataList(param);
        if (list != null && list.size() > 0)
            for (Map<String, String> map : list) {
                String type = map.get("type");
                String docFlow2 = map.get("docFlow");
                String explanId = map.get("explanId");
                String appealObjId = map.get("appealObjId");
                Map<String,Object> paramMap=new HashMap<>();
                paramMap.put("docFlow",docFlow2);
                paramMap.put("explanObjId",appealObjId);
                paramMap.put("type",type);
                paramMap.put("hosCySecId",hosCySecId);
                int num = 0;
                int havaAppealNum=0;
                int finishNum=0;
                havaAppealNum = nydyjsAppBiz.havaAppealNum(paramMap);
                if (StringUtil.isNotBlank(type)) {
                    if (type.equals("1")) {//病种需填写总数
                        num = nydyjsAppBiz.getDiseaseRegistryNum(explanId);
                        finishNum = nydyjsAppBiz.getCaRequireFinishNum(paramMap);
                    } else if (type.equals("2")) {//操作需填写总数
                        num = nydyjsAppBiz.getOperateSkillNum(explanId);
                        finishNum = nydyjsAppBiz.getOpRequireFinishNum(paramMap);
                    } else if (type.equals("3")) {//手术需填写总数
                        num = nydyjsAppBiz.getPossSkillNum(explanId);
                        finishNum = nydyjsAppBiz.getOpsRequireFinishNum(paramMap);
                    }
                }
                map.put("needNum", String.valueOf(num));
                map.put("havaAppealNum", String.valueOf(havaAppealNum));
                map.put("finishNum", String.valueOf(finishNum));

            }
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        model.addAttribute("dataCount", nydyjsAppBiz.appealDataList(param).size());
        model.addAttribute("list", list);
        return result;
    }

    /**
     * 申诉审核
     *
     * @param userFlow
     * @param roleId
     * @param recId
     * @param appealNum
     * @param checkStatus
     * @param model
     * @return
     */
    @RequestMapping(value = {"/appealAudit"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String appealAudit(String userFlow, String roleId,String content, String recId, String appealNum, String checkStatus, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "审核成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "302001");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "302002");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(recId)) {
            model.addAttribute("resultId", "302003");
            model.addAttribute("resultType", "数据流水号为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(appealNum)) {
            model.addAttribute("resultId", "302004");
            model.addAttribute("resultType", "申诉数量为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(checkStatus)) {
            model.addAttribute("resultId", "302005");
            model.addAttribute("resultType", "审核状态为空");
            return "res/nydyjs/success";
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "302006");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/nydyjs/success";
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "302007");
            model.addAttribute("resultType", "用户不存在");
            return "res/nydyjs/success";
        }
        List<String> checkStatuss = new ArrayList<>();
        checkStatuss.add("1");//审核通过
        checkStatuss.add("2");//审核不通过
        if (!checkStatuss.contains(checkStatus)) {
            model.addAttribute("resultId", "302008");
            model.addAttribute("resultType", "审核状态只能是通过或不通过");
            return "res/nydyjs/success";
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userFlow", userFlow);
        param.put("appealNum", appealNum);
        param.put("checkStatus", checkStatus);
        param.put("checkResult", content);
        param.put("recId", recId);
        int count = nydyjsAppBiz.appealAudit(param);
        if (count != 1) {
            model.addAttribute("resultId", "302009");
            model.addAttribute("resultType", "审核失败");
        }
        return "res/nydyjs/success";
    }

    /**
     * 申诉批量审核
     *
     * @param userFlow
     * @param roleId
     * @param docFlow
     * @param model
     * @return
     */
    @RequestMapping(value = {"/appealBatchAudit"}, method = {RequestMethod.POST})
    public String appealBatchAudit(String userFlow, String roleId, String docFlow, String dataType, Model model) {
        String result = "res/nydyjs/success";
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "审核成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "302101");
            model.addAttribute("resultType", "用户标识符为空");
            return result;
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "302102");
            model.addAttribute("resultType", "用户角色ID为空");
            return result;
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "302103");
            model.addAttribute("resultType", "学员流水号为空");
            return result;
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "302104");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return result;
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "302105");
            model.addAttribute("resultType", "用户不存在");
            return result;
        }
//		List<String> dataTypes=new ArrayList<>();
//		dataTypes.add("1");//病种
//		dataTypes.add("2");//操作
//		dataTypes.add("3");//手术
//		if(StringUtil.isNotBlank(dataType)){
//			if(!dataTypes.contains(dataType))
//			{
//				model.addAttribute("resultId", "3011102");
//				model.addAttribute("resultType", "数据类型只能是病种或操作或手术");
//				return result;
//			}
//		}

        List<Map<String, String>> list = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userFlow", userFlow);
        //param.put("dataType", dataType);
        param.put("docFlow", docFlow);
        param.put("biaoJi", "Y");
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        list = nydyjsAppBiz.appealDataList(param);
        int count = 0;
        if (list != null && list.size() > 0) {
            for (Map<String, String> map : list) {
                param.put("checkStatus", "1");
                param.put("checkResult", "同意");
                param.put("appealNum", map.get("appealNum"));
                param.put("recId", map.get("explanId"));
                int num = nydyjsAppBiz.appealAudit(param);
                if (num == 1) {
                    count++;
                }
            }
            model.addAttribute("resultType", "批量审核成功:" + count + "条数据");
        } else {
            model.addAttribute("resultType", "无待审核数据");
        }
        return result;
    }

    /**
     * 出科小结审核学员列表
     *
     * @param userFlow
     * @param roleId
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = {"/afterSummeryStudentList"}, method = {RequestMethod.GET})
    public String afterSummeryStudentList(String userFlow, String roleId, String biaoJi, Integer pageIndex, Integer pageSize, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "30701");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/nydyjs/studentList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "30702");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/nydyjs/studentList";
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "30703");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/nydyjs/studentList";
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "30704");
            model.addAttribute("resultType", "用户不存在");
            return "res/nydyjs/studentList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30705");
            model.addAttribute("resultType", "当前页码为空");
            return "res/nydyjs/studentList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30706");
            model.addAttribute("resultType", "每页条数为空");
            return "res/nydyjs/studentList";
        }
        List<Map<String, String>> resDoctorSchProcess = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("teacherUserFlow", userFlow);
        param.put("biaoJi", biaoJi);
        param.put("startRow", ((pageIndex - 1) * pageSize) + 1);
        param.put("endRow", (pageIndex) * pageSize);
        resDoctorSchProcess = nydyjsAppBiz.afterSummeryStudentList(param);
        System.err.println(JSON.toJSON(resDoctorSchProcess));
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        model.addAttribute("dataCount", nydyjsAppBiz.afterSummeryStudentList(param).size());
        model.addAttribute("list", resDoctorSchProcess);
        return "res/nydyjs/studentList";
    }

    @RequestMapping(value = {"/afterDetail"}, method = {RequestMethod.POST})
    public String afterDetail(String userFlow, String roleId, String recId, Model model) {
        String result = "res/nydyjs/afterDetail";
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "审核成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "302201");
            model.addAttribute("resultType", "用户标识符为空");
            return result;
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "302202");
            model.addAttribute("resultType", "用户角色ID为空");
            return result;
        }
        if (StringUtil.isBlank(recId)) {
            model.addAttribute("resultId", "302203");
            model.addAttribute("resultType", "出科小结流水号为空");
            return result;
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "302204");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return result;
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "302205");
            model.addAttribute("resultType", "用户不存在");
            return result;
        }
        Map<String, String> resultData = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userFlow", userFlow);
        param.put("afterId", recId);
        resultData = nydyjsAppBiz.getAfterSummDetail(param);
        model.addAttribute("resultData", resultData);
        return result;
    }

    @RequestMapping(value = {"/afterAudit"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String afterAudit(String userFlow, String roleId, String recId, String content,String headId,String cySecId,String hosSecId, String checkStatus, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "审核成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "302301");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "302302");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(recId)) {
            model.addAttribute("resultId", "302303");
            model.addAttribute("resultType", "数据流水号为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(content)) {
            model.addAttribute("resultId", "302304");
            model.addAttribute("resultType", "请填写评语！");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(checkStatus)) {
            model.addAttribute("resultId", "302305");
            model.addAttribute("resultType", "审核状态为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(headId)) {
            model.addAttribute("resultId", "302306");
            model.addAttribute("resultType", "科主任流水号为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(cySecId)) {
            model.addAttribute("resultId", "302307");
            model.addAttribute("resultType", "规范流水号为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(hosSecId)) {
            model.addAttribute("resultId", "302308");
            model.addAttribute("resultType", "科室流水号为空");
            return "res/nydyjs/success";
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "302309");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/nydyjs/success";
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "302310");
            model.addAttribute("resultType", "用户不存在");
            return "res/nydyjs/success";
        }
        List<String> checkStatuss = new ArrayList<>();
        checkStatuss.add("1");//审核通过
        checkStatuss.add("2");//审核不通过
        if (!checkStatuss.contains(checkStatus)) {
            model.addAttribute("resultId", "302311");
            model.addAttribute("resultType", "审核状态只能是通过或不通过");
            return "res/nydyjs/success";
        }
        Map<String, String> resultData = null;
        Map<String, Object> param2 = new HashMap<String, Object>();
        param2.put("userFlow", userFlow);
        param2.put("afterId", recId);
        resultData = nydyjsAppBiz.getAfterSummDetail(param2);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userFlow", userFlow);
        param.put("content", content);
        param.put("checkStatus", checkStatus);
        param.put("recId", recId);
        if(resultData!=null)
        {
            param2.clear();
            param2.put("id",recId);
            param2.put("tableName","OutSecBrief");
            param2.put("title",resultData.get("schDeptName")+"的"+resultData.get("docName")+"的出科小结");
            param2.put("headId",headId);
            param2.put("docFlow",resultData.get("docFlow"));
            param2.put("docName",resultData.get("docName"));
            param2.put("pbegin",DateUtil.transDateTime(DateUtil.getCurrentTime()));
            param2.put("abegin",DateUtil.transDateTime(DateUtil.getCurrentTime()));
            String url="~/WebSite/TrainItem/OutSecBriefInfo.aspx?CySecID=" + cySecId + "&HosSecID=" + hosSecId + "&UserID=" + resultData.get("docFlow") + "&Type=2";
            param2.put("url",url);
            nydyjsAppBiz.insertWFWORK(param2);
        }
        int count = nydyjsAppBiz.afterAudit(param);
        if (count == 0) {
            model.addAttribute("resultId", "302312");
            model.addAttribute("resultType", "审核失败");
        }
        return "res/nydyjs/success";
    }

//	/**
//	 *
//	 * @param userFlow
//	 * @param roleId
//	 * @param docFlow
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = {"/afterBatchAudit"},method = {RequestMethod.POST})
//	public String afterBatchAudit(String userFlow,String roleId,String docFlow,String dataType,Model model)
//	{
//		String result="res/nydyjs/success";
//		model.addAttribute("resultId", "200");
//		model.addAttribute("resultType", "审核成功");
//		if(StringUtil.isBlank(userFlow)){
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "用户标识符为空");
//			return result;
//		}
//		if(StringUtil.isBlank(roleId)){
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "用户角色ID为空");
//			return result;
//		}
//		if(StringUtil.isBlank(docFlow)){
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "学员流水号为空");
//			return result;
//		}
//		if(!roleId.equals("6")){
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "用户角色ID与角色不符");
//			return result;
//		}
//		//验证用户是否存在
//		Map<String, String> user=nydyjsAppBiz.readUser(userFlow);
//		if(user==null){
//			model.addAttribute("resultId", "3011102");
//			model.addAttribute("resultType", "用户不存在");
//			return result;
//		}
//		List<String> dataTypes=new ArrayList<>();
//		dataTypes.add("1");//病种
//		dataTypes.add("2");//操作
//		dataTypes.add("3");//手术
//		if(StringUtil.isNotBlank(dataType)){
//			if(!dataTypes.contains(dataType))
//			{
//				model.addAttribute("resultId", "3011102");
//				model.addAttribute("resultType", "数据类型只能是病种或操作或手术");
//				return result;
//			}
//		}
//
//		List<Map<String,String>> list=null;
//		Map<String, Object> param=new HashMap<String, Object>();
//		param.put("userFlow", userFlow);
//		param.put("dataType", dataType);
//		param.put("docFlow", docFlow);
//		param.put("biaoJi", "Y");
//		param.put("startRow", 1);
//		param.put("endRow", Integer.MAX_VALUE);
//		list=nydyjsAppBiz.appealDataList(param);
//		int count=0;
//		if(list!=null&&list.size()>0)
//		{
//			for (Map<String,String> map:list )
//			{
//				param.put("checkStatus", "1");
//				param.put("appealNum", map.get("appealNum"));
//				param.put("recId", map.get("recId"));
//				int num=nydyjsAppBiz.appealAudit(param);
//				if(num==1){
//					count++;
//				}
//			}
//			model.addAttribute("resultType", "批量审核成功:"+count+"条数据");
//		}else {
//			model.addAttribute("resultType", "无待审核数据");
//		}
//		return result;
//	}

    /**
     * 出科考核审核学员列表
     *
     * @param userFlow
     * @param roleId
     * @param studentName
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = {"/afterEvaStudentList"}, method = {RequestMethod.GET})
    public String afterEvaStudentList(String userFlow, String roleId, String biaoJi, Integer pageIndex, Integer pageSize, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "30801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/nydyjs/studentList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "30802");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/nydyjs/studentList";
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "30803");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/nydyjs/studentList";
        }
        //验证用户是否存在
        Map<String, String> user = nydyjsAppBiz.readUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "30804");
            model.addAttribute("resultType", "用户不存在");
            return "res/nydyjs/studentList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30805");
            model.addAttribute("resultType", "当前页码为空");
            return "res/nydyjs/studentList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30806");
            model.addAttribute("resultType", "每页条数为空");
            return "res/nydyjs/studentList";
        }
        List<Map<String, String>> resDoctorSchProcess = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("teacherUserFlow", userFlow);
        param.put("biaoJi", biaoJi);
        param.put("startRow", ((pageIndex - 1) * pageSize) + 1);
        param.put("endRow", (pageIndex) * pageSize);
        resDoctorSchProcess = nydyjsAppBiz.afterEvaStudentList(param);
        System.err.println(JSON.toJSON(resDoctorSchProcess));
        param.put("startRow", 1);
        param.put("endRow", Integer.MAX_VALUE);
        model.addAttribute("dataCount", nydyjsAppBiz.afterEvaStudentList(param).size());
        model.addAttribute("list", resDoctorSchProcess);
        return "res/nydyjs/studentList";
    }

    @RequestMapping(value = {"/aboutUs"}, method = {RequestMethod.GET})
    public String aboutUs(String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "302601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/nydyjs/aboutUs";
        }
        //验证用户是否存在
        Map<String, String> userinfo = nydyjsAppBiz.readUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "302602");
            model.addAttribute("resultType", "用户不存在");
            return "res/nydyjs/aboutUs";
        }
        model.addAttribute("userinfo", userinfo);
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String httpurl = httpRequest.getRequestURL().toString();
        String servletPath = httpRequest.getServletPath();
        String hostUrl = httpurl.substring(0, httpurl.indexOf(servletPath)) + "/jsp/res/nydyjs/about/about.jsp";
        String androidimgurl = httpurl.substring(0, httpurl.indexOf(servletPath)) + "/jsp/res/nydyjs/about/about2.jsp";
        model.addAttribute("imgurl", hostUrl);
        model.addAttribute("androidimgurl", androidimgurl);

        return "res/nydyjs/aboutUs";
    }

    @RequestMapping(value = {"/afterEvaDetail"}, method = {RequestMethod.POST})
    public String afterEvaDetail(String userFlow, String roleId,String cySecId ,String hosCySecId ,String docFlow,String afterEvaId, HttpServletRequest request, HttpServletResponse response, Model model) {
        String result = "res/nydyjs/afterEvaDetail";
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "302401");
            model.addAttribute("resultType", "用户标识符为空");
            return result;
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "302402");
            model.addAttribute("resultType", "用户角色ID为空");
            return result;
        }
        if (StringUtil.isBlank(cySecId)) {
            model.addAttribute("resultId", "302403");
            model.addAttribute("resultType", "科室流水号为空");
            return result;
        }
        if (StringUtil.isBlank(hosCySecId)) {
            model.addAttribute("resultId", "302404");
            model.addAttribute("resultType", "hosCySecId流水号为空");
            return result;
        }
        if (StringUtil.isBlank(afterEvaId)) {
            model.addAttribute("resultId", "302405");
            model.addAttribute("resultType", "afterEvaId流水号为空");
            return result;
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "302406");
            model.addAttribute("resultType", "学员流水号为空");
            return result;
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "302407");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return result;
        }
        Map<String, String> userinfo = nydyjsAppBiz.readUser(userFlow);
        model.addAttribute("user",userinfo);
        Map<String, Object> param = new HashMap<>();
        param.put("docFlow", docFlow);
        param.put("cySecId", cySecId);
        param.put("hosCySecId", hosCySecId);
        param.put("afterEvaId", afterEvaId);
        //--病种 要求数，完成数比例
        int caRequireNum = nydyjsAppBiz.getCaRequireNum(hosCySecId);
        int caRequireFinishNum = nydyjsAppBiz.getCaRequireFinishNum(param);
        double caRequireBiLi = this.getFinishPer(caRequireNum, caRequireFinishNum);
        model.addAttribute("caReqNum", caRequireNum);
        model.addAttribute("caReqFinishNum", caRequireFinishNum);
        model.addAttribute("caReqBiLi", caRequireBiLi);
        //--大病历 要求数，完成数比例
        int disRequireNum = nydyjsAppBiz.getDisRequireNum(hosCySecId);
        int disRequireFinishNum = nydyjsAppBiz.getDisRequireFinishNum(param);
        double disRequireBiLi = this.getFinishPer(disRequireNum, disRequireFinishNum);
        model.addAttribute("disReqNum", disRequireNum);
        model.addAttribute("disReqFinishNum", disRequireFinishNum);
        model.addAttribute("disReqBiLi", disRequireBiLi);
        //--操作 要求数，完成数比例
        int opRequireNum = nydyjsAppBiz.getOpRequireNum(hosCySecId);
        int opRequireFinishNum = nydyjsAppBiz.getOpRequireFinishNum(param);
        double opRequireBiLi = this.getFinishPer(opRequireNum, opRequireFinishNum);
        model.addAttribute("opReqNum", opRequireNum);
        model.addAttribute("opReqFinishNum", opRequireFinishNum);
        model.addAttribute("opReqBiLi", opRequireBiLi);
        //--手术 要求数，完成数比例
        int opsRequireNum = nydyjsAppBiz.getOpsRequireNum(hosCySecId);
        int opsRequireFinishNum = nydyjsAppBiz.getOpsRequireFinishNum(param);
        double opsRequireBiLi = this.getFinishPer(opsRequireNum, opsRequireFinishNum);
        model.addAttribute("opsReqNum", opsRequireNum);
        model.addAttribute("opsReqFinishNum", opsRequireFinishNum);
        model.addAttribute("opsReqBiLi", opsRequireBiLi);
        //参加活动次数
        int activityNum = nydyjsAppBiz.getActivityNum(param);
        //填写工作日志天数
        List<Map<String,Object>> dayList=nydyjsAppBiz.getDayTimeList(param);
        int allday=0;
        if(dayList!=null&&dayList.size()>0) {
            for(Map<String, Object> b:dayList)
            {
                b.put("docFlow", docFlow);
                Map<String, Object> dayLogCount = nydyjsAppBiz.getDayLogCount(b);
                int dlC = 0;
                if (dayLogCount != null) {
                    dlC = (int) dayLogCount.get("WorkNum");
                }
                allday+=dlC;
            }
        }
        model.addAttribute("dayLogCount", allday);
        //轮转天数
        Map<String, Object> cycDayCount = nydyjsAppBiz.getCycDayCount(param);
        if (cycDayCount != null) {
            model.addAttribute("cycDayCount", cycDayCount.get("countday"));
        } else {
            model.addAttribute("cycDayCount", 0);
        }

        //各项得分
        Map<String, Object> scoreMap = nydyjsAppBiz.getScoreMap(param);
        if (scoreMap == null)
            scoreMap = new HashMap<>();

        int months = nydyjsAppBiz.getMonths(param);
        model.addAttribute("activityNum", activityNum);
        if(scoreMap.get("CCHD")==null) {
            double hd=0;
            if(months>0&&activityNum>0)
            {
                hd=(double)activityNum/months;
                if(hd>=4){
                    hd=4;
                }
            }
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMaximumFractionDigits(2);
            hd = Double.valueOf(nf.format(hd * 100 / 100));
            scoreMap.put("CCHD",hd);
        }
        //理论成绩
        if(scoreMap.get("LLKS")==null) {
            Map<String, Object> llcj = nydyjsAppBiz.getTheroyScore(afterEvaId);
            double LLKS = 0;
            if (llcj != null) {
                double test = (double) llcj.get("TestResult");
                double makeup = (double) llcj.get("MakeupResult");
                if (test > makeup) {
                    LLKS = test * 0.1;
                    NumberFormat nf = NumberFormat.getNumberInstance();
                    nf.setMaximumFractionDigits(2);
                    LLKS = Double.valueOf(nf.format(LLKS * 100 / 100));
                }
            }
            scoreMap.put("LLKS", LLKS);
        }
        //日志加分项
        //日志得分项
        if(scoreMap.get("RZQQ")==null) {
            double RZQQ = (allday * 0.2 * 100 / 100) > 5 ? 5 : (allday * 0.2 * 100 / 100);
            scoreMap.put("RZQQ", RZQQ);
        }
        if(scoreMap.get("RZJF")==null) {
            double RZQQ = (allday * 0.2 * 100 / 100) > 5 ? 5 : (allday * 0.2 * 100 / 100);
            scoreMap.put("RZJF", RZQQ);
        }
        //病种得分项
        //病种加分项
        double bzjf=0;
        double GLBZ = 0;
        if (caRequireBiLi == 100) {
            GLBZ = 13;
            bzjf=(caRequireFinishNum-caRequireNum)*0.5;
        } else if (caRequireBiLi >= 90) {
            GLBZ = 12;
        } else if (caRequireBiLi >= 80) {
            GLBZ = 10;
        } else if (caRequireBiLi >= 70) {
            GLBZ = 9;
        } else {
            GLBZ = 0;
        }
        if(scoreMap.get("GLBZ")==null) {
            scoreMap.put("GLBZ", GLBZ);
        }
        if(scoreMap.get("BZJF")==null) {
            scoreMap.put("BZJF", bzjf);
        }
        //病历得分项
        double GLBL = 0;
        double bljf=0;
        if (disRequireBiLi == 100) {
            GLBL = 14;
            bljf=(disRequireFinishNum-disRequireNum)*0.5;
        } else if (disRequireBiLi >= 90) {
            GLBL = 13;
        } else if (disRequireBiLi >= 80) {
            GLBL = 11;
        } else if (disRequireBiLi >= 70) {
            GLBL = 10;
        } else {
            GLBL = 0;
        }
        if(scoreMap.get("GLBL")==null) {
            scoreMap.put("GLBL", GLBL);
        }
        if(scoreMap.get("BLJF")==null) {
            scoreMap.put("BLJF", bljf);
        }
        //操作手术得分项
        double req = opRequireNum > opsRequireNum ? opRequireNum : opsRequireNum;
        double bili = opRequireBiLi > opsRequireBiLi ? opRequireBiLi : opsRequireBiLi;
        double CZSS = 0;
        if (bili == 100) {
            CZSS = 14;
        } else if (bili >= 90) {
            CZSS = 13;
        } else if (bili >= 80) {
            CZSS = 11;
        } else if (bili >= 70) {
            CZSS = 10;
        } else {
            CZSS = 0;
        }
        if(scoreMap.get("CZSS")==null) {
            scoreMap.put("CZSS", CZSS);
        }
        //操作手术加分项
        double czjf=0;
        if(opRequireBiLi>=100&&opRequireNum>0){
            czjf+=14/opRequireNum*(opRequireFinishNum-opRequireNum);
        }
        if(opsRequireBiLi>=100&&opsRequireNum>0)
        {
            czjf+=14/opsRequireNum*(opsRequireFinishNum-opsRequireNum);
        }
        if(scoreMap.get("CZJF")==null) {
            scoreMap.put("CZJF", czjf);
        }
        model.addAttribute("scoreMap", scoreMap);
        //判断是否打过考核表
        int count = nydyjsAppBiz.getCheckStatus(param);
        model.addAttribute("count", count);
        return result;
    }

    @RequestMapping(value = {"/saveEva"}, method = {RequestMethod.POST})
    public String saveEva(String userFlow, String roleId, String cySecId,String hosCySecId,String workId,String headId ,String schDeptName, String docFlow, String afterEvaId, HttpServletRequest request, HttpServletResponse response, Model model) {
        String result = "res/nydyjs/success";
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "302501");
            model.addAttribute("resultType", "用户标识符为空");
            return result;
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "302502");
            model.addAttribute("resultType", "用户角色ID为空");
            return result;
        }
        if (StringUtil.isBlank(cySecId)) {
            model.addAttribute("resultId", "302503");
            model.addAttribute("resultType", "科室流水号为空");
            return result;
        }
        if (StringUtil.isBlank(hosCySecId)) {
            model.addAttribute("resultId", "302504");
            model.addAttribute("resultType", "hosCySecId流水号为空");
            return result;
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "302505");
            model.addAttribute("resultType", "学员流水号为空");
            return result;
        }
        if (StringUtil.isBlank(afterEvaId)) {
            model.addAttribute("resultId", "302506");
            model.addAttribute("resultType", "afterEvaId流水号为空");
            return result;
        }
        if (StringUtil.isBlank(headId)) {
            model.addAttribute("resultId", "302507");
            model.addAttribute("resultType", "科主任流水号为空");
            return "res/nydyjs/success";
        }
        if (StringUtil.isBlank(schDeptName)) {
            model.addAttribute("resultId", "302508");
            model.addAttribute("resultType", "轮转科室名为空");
            return "res/nydyjs/success";
        }
        if (!roleId.equals("6")) {
            model.addAttribute("resultId", "302509");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return result;
        }
        Map<String, Object> param = new HashMap<>();
        param.put("workId", workId);
        param.put("docFlow", docFlow);
        param.put("cySecId", cySecId);
        param.put("hosCySecId", hosCySecId);
        param.put("tecId", userFlow);
        param.put("afterEvaId", afterEvaId);
        param.put("TecCheckDate", DateUtil.transDateTime(DateUtil.getCurrDateTime()));
        Map<String, String[]> paramsMap = request.getParameterMap();
        for (String key : paramsMap.keySet()) {
            String[] values = paramsMap.get(key);
            if (values != null && values.length > 0) {
                if(key.equals("TotalScore")){
                    double score=0;
                    if(StringUtil.isNotBlank(values[0])) {
                        double totalScore =Double.valueOf(values[0]);
                                NumberFormat nf = NumberFormat.getNumberInstance();
                        nf.setMaximumFractionDigits(2);
                        score= Double.valueOf(nf.format(totalScore * 100 / 100));
                    }
                    param.put(key, score);
                }else {
                    param.put(key, values[0]);
                }
            } else {
                param.put(key, "");
            }
        }
        //System.out.println(JSON.toJSONString(paramsMap));
        //System.out.println(JSON.toJSONString(param));
        //判断是否打过考核表
        int count = nydyjsAppBiz.getCheckStatus(param);
        int succ = 0;
        if (count > 0) {
            succ = nydyjsAppBiz.updateAfterEva(param);
        } else {
            succ = nydyjsAppBiz.insertAfterEva(param);
        }
        Map<String, Object> scoreMap = nydyjsAppBiz.getScoreMap(param);
        Map<String,Object> param2=new HashMap<>();
        if(scoreMap!=null)
        {
            Map<String, String> doctor = nydyjsAppBiz.readUser(docFlow);
            param2.clear();
            param2.put("id",scoreMap.get("ID"));
            param2.put("tableName","CycleOutSectionRecordItemDetail");
            param2.put("title",schDeptName+"的"+doctor.get("userName")+"的培训轮转出科考核");
            param2.put("headId",headId);
            param2.put("docFlow",docFlow);
            param2.put("docName",doctor.get("userName"));
            param2.put("pbegin",DateUtil.transDateTime(DateUtil.getCurrentTime()));
            param2.put("abegin",DateUtil.transDateTime(DateUtil.getCurrentTime()));
            String url="~/WebSite/Professor/VerifyDataDetails/UniversityOutCycleSection_New.aspx?UserID=" + docFlow + "&HosCySecID=" + hosCySecId + "&DataID=" + scoreMap.get("ID") + "&UCSID=" + afterEvaId + "&Type=1";
            param2.put("url",url);
            nydyjsAppBiz.insertWFWORK(param2);
        }
        if(StringUtil.isNotBlank(workId)) {
            nydyjsAppBiz.deleteWfwork(workId);
        }
        if (succ != 1) {
            model.addAttribute("resultId", "302510");
            model.addAttribute("resultType", "保存失败");
        }
        return result;
    }

    private double getFinishPer(int reqNum, int finishNum) {
        if (reqNum == 0 && finishNum == 0) {
            return 100.00;
        }
        if (reqNum == 0 && finishNum > 0) {
            return 100.00;
        }
        if (reqNum > 0 && finishNum > 0) {
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMaximumFractionDigits(2);
            return  Double.valueOf(nf.format(Double.valueOf(finishNum) / Double.valueOf(reqNum)*10000/100));
        }
        return 0;
    }
}
