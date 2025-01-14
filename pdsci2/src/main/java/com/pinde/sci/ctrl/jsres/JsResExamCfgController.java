package com.pinde.sci.ctrl.jsres;


import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.HttpClientUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sch.ISchExamCfgBiz;
import com.pinde.sci.biz.sch.ISchExamScoreQueryBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/jsres/examCfg")
public class JsResExamCfgController extends GeneralController {

    @Autowired
    private  ISchExamCfgBiz examCfgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private ISchExamScoreQueryBiz scoreQueryBiz;

    @RequestMapping(value="/examArrangMent")
    public String examArrangMent(Model model) {
        return "jsres/hospital/examCfg/examCfgMain";
    }

    @RequestMapping(value="/examArrangMentAcc")
    public String examArrangMentAcc(Model model) {
        return "jsres/hospital/examCfg/examCfgMainAcc";
    }

    @RequestMapping(value="/edit")
    public String edit(Model model,String arrangeFlow, String type) {
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
        model.addAttribute("depts",depts);
        model.addAttribute("type", type);
        SysDept dept=new SysDept();
        dept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        dept.setOrgFlow(currentUser.getOrgFlow());
        List<Map<String, String>> list = deptBiz.searchDeptByUnion(dept, com.pinde.core.common.GlobalConstant.FLAG_Y);
        model.addAttribute("all",list);
        if(StringUtil.isBlank(arrangeFlow))
        {
            return "jsres/hospital/examCfg/editAdd";
        }
        return "jsres/hospital/examCfg/edit";
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
        return "jsres/hospital/examCfg/list";
    }
    @RequestMapping(value="/updateArrangement")
    @ResponseBody
    public String updateArrangement(SchExamArrangement schExamArrangement ,String[] standardDeptId ){
        int c=examCfgBiz.checkExists(schExamArrangement);
        if(c>0)
        {
            return "cannotInsert";
        }

        // 当paperFlow不为空时，说明是对接的java版的新考试系统，而不是老的.net的，按新逻辑，住培和考试两边一起更新
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(schExamArrangement.getPaperFlow())) {
            String accessToken = loginAndToken();
            try {
                // 增加考试操作：新增试卷，导入排班关联学员考试
                String paperFlow = examCfgBiz.generateExam(schExamArrangement, schExamArrangement.getTrainingSpeId(), schExamArrangement.getSessionNumber(), accessToken);
                // 生成试卷后再导入排班
                examCfgBiz.generateDoctorExam(schExamArrangement, paperFlow, schExamArrangement.getTrainingSpeId(), schExamArrangement.getSessionNumber(), accessToken, false);
            } catch (RuntimeException e) {
                return e.getMessage();
            }
        }
        int result = examCfgBiz.updateArrangement(schExamArrangement, standardDeptId, com.pinde.core.common.GlobalConstant.FLAG_N);
        if(result==0)
        {
            return "操作失败！";
        }
        return "操作成功！";
    }
    @RequestMapping(value="/updateArrangementAdd")
    @ResponseBody
    public String updateArrangementAdd(SchExamArrangement schExamArrangement ,String[] standardDeptId,String[] itemId,String sessionNumbers){
        if(itemId.length==0)
        {
            return "请选择培训专业！";
        }
        if(sessionNumbers.split(",").length==0)
        {
            return "请选择年级！";
        }
        int c=examCfgBiz.checkExists(schExamArrangement,Arrays.asList(itemId),Arrays.asList(sessionNumbers.split(",")));
        if(c>0)
        {
            return "cannotInsert";
        }
        String accessToken = loginAndToken();
        // 增加考试操作：新增试卷，导入排班关联学员考试
        try {
            for(String trainingSpeId: Arrays.asList(itemId)) {
                for(String sessionNumber: sessionNumbers.split(",")) {
                    schExamArrangement.setPaperFlow(null); // schExamArrangement在更新时会set一下paperFlow，这里给还原，保证是更新而不是插入
                    schExamArrangement.setArrangeFlow(null); // 这里也给设成null,防止新增变成更新
                    String paperFlow = examCfgBiz.generateExam(schExamArrangement, trainingSpeId, sessionNumber, accessToken);

                    if(StringUtils.isEmpty(paperFlow)) {
                        return "生成试卷失败！";
                    }

                    // 生成试卷后再导入排班
                    examCfgBiz.generateDoctorExam(schExamArrangement, paperFlow, trainingSpeId, sessionNumber, accessToken, true);
                    // 下面开新事物，保证可以部分成功，两边状态一致（前面for中成功了，后面for失败了不影响前面成功的）
                    examCfgBiz.updateArrangements(schExamArrangement,standardDeptId,Arrays.asList(trainingSpeId),Arrays.asList(sessionNumber), paperFlow);
                }
            }
        } catch (RuntimeException e) {
            return e.getMessage();
        }

        return "操作成功！";
    }

    private String loginAndToken() {
        String userName = InitConfig.getSysCfg("jsres_yearly_test_username");
        String password = InitConfig.getSysCfg("jsres_yearly_test_password");
        Map<String, String> params = new HashMap<>();
        params.put("username", userName);
        params.put("password", password);
        params.put("reqType", "we_chat");

        String projectName = InitConfig.getSysCfg("jsres_yearly_test_projectname");
        String applicationName = InitConfig.getSysCfg("jsres_yearly_test_applicationname");
        Header[] headers = new Header[]{
                new BasicHeader("projectname", projectName),
                new BasicHeader("applicationname", applicationName)
        };

        String loginUrl = InitConfig.getSysCfg("jsres_yearly_test_login_url");
        HttpClientUtil.HttpResult httpResult = HttpClientUtil.sendPostForm(loginUrl, params, headers, null, "UTF-8");
        String result = httpResult.getStringContent();
        JSONObject resultJson = JSONObject.parseObject(result);
        if(null != resultJson && resultJson.getJSONObject("data") != null) {
            return "bearer" + resultJson.getJSONObject("data").getString("access_token");
        }

        return null;
    }

    @RequestMapping(value="/updateCfg")
    @ResponseBody
    public String updateCfg(Model model,  SchExamArrangement schExamArrangement  ){
        //删除时，校验是否已有学生考过试，并有成绩的，无法删除
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(schExamArrangement.getRecordStatus()))
        {
            int checkCount=examCfgBiz.checkHaveExam(schExamArrangement.getArrangeFlow());
            if(checkCount>0) {
                return "已有学员参加过考试，无法删除！";
            }
            // 与编辑时类似，如果有paperFlow，说明对接的是新的java版的考试系统，要把考试系统的数据同时删除
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(schExamArrangement.getPaperFlow())) {
                try {
                    // 同步对考试系统删除试卷
                    examCfgBiz.deleteExam(schExamArrangement.getPaperFlow(), loginAndToken());
                } catch(Exception e) {
                    return e.getMessage();
                }
            }
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
        schExamArrangement.setTrainingTypeId(resDoctor.getTrainingTypeId());
        schExamArrangement.setTrainingSpeId(resDoctor.getTrainingSpeId());
        List<SchExamArrangement> examArrangements = examCfgBiz.searchList(schExamArrangement);
        //查询条件
        Map<String,Object> param = new HashMap<>();
        List<String> userFlows = new ArrayList<>();
        userFlows.add(currentUser.getUserFlow());
        param.put("orgFlow",orgFlow);
        param.put("userFlows",userFlows);
        List<SchExamDoctorArrangement> doctorArrangements = scoreQueryBiz.getDoctorArrangements(param);
        if(doctorArrangements != null && doctorArrangements.size() > 0){
            Map<String,SchExamDoctorArrangement> doctorArrangementMap = new HashMap<>();
            for(SchExamDoctorArrangement da : doctorArrangements)
            {
                // 判断是否开放成绩查看权限，未开放则将学员成绩以**展示
                SchExamArrangement examArrangement = examCfgBiz.readByFlow(da.getArrangeFlow());
                if (StringUtil.isNotBlank(examArrangement.getIsOpenResult()) && examArrangement.getIsOpenResult().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
                    da.setExamScore(new BigDecimal(-20));
                }
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
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsOpen()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsWeb()) &&
                                Integer.parseInt(tempExam.getExamNumber()) > Integer.parseInt(tempMap.get("COUNTNUM"))){
                            paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_Y);
                        }
                        examLogMaps.put(tempMap.get("ARRANGEFLOW"),paramTempMap);
                    }
                }else {
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsOpen()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsWeb())) {
                        Map<String,String> paramTempMap = new HashMap<>();
                        paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_Y);
                        examLogMaps.put(tempExam.getArrangeFlow(),paramTempMap);
                    }
                }
            }
            model.addAttribute("examLogMaps",examLogMaps);
        }
        model.addAttribute("examArrangements",examArrangements);
        return "jsres/doctor/examCfg/examList";
    }

}
