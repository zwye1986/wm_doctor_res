package com.pinde.sci.ctrl.jsres;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.enums.BaseStatusEnum;
import com.pinde.core.model.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.pdf.utils.ObjectUtils;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.IJsResRecruitDoctorInfoBiz;
import com.pinde.sci.biz.jsres.IResMessageBiz;
import com.pinde.sci.biz.jsres.IResOrgSpeBiz;
import com.pinde.sci.biz.portal.IPortalInfoManageBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitResConfig;
import com.pinde.sci.dao.base.SysCfgMapper;
import com.pinde.core.common.enums.RecDocCategoryEnum;
import com.pinde.sci.form.jsres.UserResumeExtInfoForm;
import com.pinde.sci.model.jsres.*;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.util.jsres.ResultUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jsres/message")
public class JsResMessageController extends GeneralController {

    private final static Logger logger = LoggerFactory.getLogger(InitResConfig.class);

    @Autowired
    private IResScoreBiz resScoreBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private ISchRotationDeptBiz rotationDeptBiz;
    @Autowired
    private ISchDoctorDeptBiz doctorDeptBiz;
    @Autowired
    private ISchRotationBiz rotationBiz;
    @Autowired
    private ISchRotationGroupBiz groupBiz;
    @Autowired
    private IJsResRecruitDoctorInfoBiz recruitDoctorInfoBiz;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IResMessageBiz messageBiz;
    @Autowired
    private IResOrgSpeAssignBiz speAssignBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;
    @Autowired
    private ISchRotationBiz schRotationtBiz;
    @Autowired
    private IResOrgSpeBiz resOrgSpeBiz;
    @Autowired
    private IPortalInfoManageBiz infoManageBiz;
    @Autowired
    private SysCfgMapper sysCfgMapper;
    /**
     * 主页面
     */
    @RequestMapping(value="/main")
    public String main(Model model){
        //获取报考记录
        SysUser currUser = GlobalContext.getCurrentUser();
        String doctorFlow = currUser.getUserFlow();
        ResDoctorRecruit recruit =new ResDoctorRecruit();
        recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
        if(recruitList != null && !recruitList.isEmpty()){
            model.addAttribute("recruitList", recruitList);
            for(ResDoctorRecruit rec : recruitList){
                if (com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getId().equals(rec.getAuditStatusId())
                        || com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId().equals(rec.getAuditStatusId())
                        || com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotPassed.getId().equals(rec.getAuditStatusId())) {
                    model.addAttribute("unPassed", true);
                }
            }
        }
        ResDocotrDelayTeturn docotrBackTeturn = new ResDocotrDelayTeturn();
        docotrBackTeturn.setDoctorFlow(doctorFlow);
        docotrBackTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
        List<ResDocotrDelayTeturn>  backList = resDoctorDelayTeturnBiz.searchInfo(docotrBackTeturn,null,null,null);
        model.addAttribute("resRecList", backList);

        ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
        docotrDelayTeturn.setDoctorFlow(doctorFlow);
        docotrDelayTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId());
        List<ResDocotrDelayTeturn>  delayList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn,null,null,null);
        model.addAttribute("delayList", delayList);
        return "jsres/message/main";
    }

    @RequestMapping(value="/messageList")
    public String messageList(Integer currentPage, String messageTitle, Model model, HttpServletRequest request){
        SysUser user = GlobalContext.getCurrentUser();
        PageHelper.startPage(currentPage,getPageSize(request));
        List<ResMessage> messageList =  this.messageBiz.searchMessageByOrg(user.getOrgFlow(),messageTitle);
        model.addAttribute("messageList",messageList);
        return "jsres/message/messageList";
    }

    @RequestMapping("/edit")
    public String edit(String messageFlow,  Model model){
        ResMessage message = this.messageBiz.findMessageByFlow(messageFlow);
        model.addAttribute("message",message);
        String imageBaseUrl = this.infoManageBiz.getImageBaseUrl();
        model.addAttribute("imageBaseUrl", imageBaseUrl);
        return "jsres/message/edit";
    }

    @ResponseBody
    @RequestMapping("/saveMessage")
    public String saveMessage(ResMessage message){
        int i = messageBiz.editMessage(message);
        if(i > 0) {
            return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
    }

    @ResponseBody
    @RequestMapping("/delMessage")
    public String delMessage(String messageFlow){
        this.messageBiz.delMessage(messageFlow);
        return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
    }

    /**
     * @Department：研发部
     * @Description 招录计划列表
     * @Author fengxf
     * @Date 2020/6/6
     */
    @RequestMapping(value="/plan")
    public String plan(){
        return "jsres/message/plan";
    }

    /**
     * @Department：研发部
     * @Description 招录计划列表
     * @Author xieyh
     * @Date 2024-10-30
     */
    @RequestMapping(value="/planList")
    public String planList(String assignYear, Model model,Integer currentPage,HttpServletRequest request){
        SysUser currUser = GlobalContext.getCurrentUser();
        Map<String,Object> paramMap = new HashMap<>();
        List<String> orgFlows = new ArrayList<>();
        paramMap.put("assignYear",assignYear);
//        paramMap.put("orgFlow", currUser.getOrgFlow());
        model.addAttribute("orgFlow",currUser.getOrgFlow());
        orgFlows.add(currUser.getOrgFlow());
        //判断是否是协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        model.addAttribute("isJointOrg", isJointOrg);
        if (isJointOrg.equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
            //查询本基地下协同基地
            List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
            if (null != orgList && orgList.size() > 0) {
                for (SysOrg org : orgList) {
                    orgFlows.add(org.getOrgFlow());
                }
            }
        }
        paramMap.put("orgFlows",orgFlows);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, Object>> resultMapList = speAssignBiz.searchAssignInfoList(paramMap);
        model.addAttribute("resultMapList",resultMapList);
        return "jsres/message/planList";
    }

    /**
     * @Department：研发部
     * @Description 报送计划列表
     * @Author xieyh
     * @Date 2024-10-30
     */
    @RequestMapping(value="/sendList")
    public String sendList(String assignYear, Model model,Integer currentPage,HttpServletRequest request){
        SysUser currUser = GlobalContext.getCurrentUser();
        Map<String,Object> paramMap = new HashMap<>();
        List<String> orgFlows = new ArrayList<>();
        paramMap.put("assignYear",assignYear);
//        paramMap.put("orgFlow", currUser.getOrgFlow());
        model.addAttribute("orgFlow",currUser.getOrgFlow());
        orgFlows.add(currUser.getOrgFlow());
        //判断是否是协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        model.addAttribute("isJointOrg", isJointOrg);
        if (isJointOrg.equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
            //查询本基地下协同基地
            List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
            if (null != orgList && orgList.size() > 0) {
                for (SysOrg org : orgList) {
                    orgFlows.add(org.getOrgFlow());
                }
            }
        }
        paramMap.put("orgFlows",orgFlows);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, Object>> resultMapList = speAssignBiz.searchAssignInfoList(paramMap);
        model.addAttribute("resultMapList",resultMapList);
        for (int i = 0; i < resultMapList.size(); i++) {
            if (ObjectUtils.isBlank(resultMapList.get(i).get("SEND_PLAN_SUM"))) {
                resultMapList.remove(i);
                i--;
            }
        }
        return "jsres/message/sendList";
    }

    /**
     * @Department：研发部
     * @Description 招生计划新增页面
     * @Author fengxf
     * @Date 2020/6/6
     */
    @RequestMapping("/addPlan")
    public String addPlan(String orgFlowEdit,String assignYearEdit,String startTime,String endTime,String flag,Model model) {
        SysUser currUser = GlobalContext.getCurrentUser();
        //判断是否是协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        model.addAttribute("isJointOrg",isJointOrg);
        model.addAttribute("flag",flag);
        Map<String,String> paramMap = new HashMap<>();
        if(StringUtil.isBlank(orgFlowEdit)){
            orgFlowEdit = currUser.getOrgFlow();
        }
        paramMap.put("orgFlow", orgFlowEdit);
        model.addAttribute("orgFlow", orgFlowEdit);
//        List<Map<String, String>> orgList = speAssignBiz.searchOrgInfoList(paramMap);
        SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
        List<SysOrg> orgList = new ArrayList<>();
        orgList.add(org);
        model.addAttribute("orgList", orgList);
        return "jsres/message/addPlan";
    }

    /**
     * @Department：研发部
     * @Description 报送计划新增页面
     * @Author xieyh
     * @Date 2024-10-30
     */
    @RequestMapping("/addSend")
    public String addSend(String orgFlowEdit,String assignYearEdit,String sendStartTime,String sendEndTime,String flag,Model model) {
        SysUser currUser = GlobalContext.getCurrentUser();
        //判断是否是协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        model.addAttribute("isJointOrg",isJointOrg);
        model.addAttribute("flag",flag);
        Map<String,String> paramMap = new HashMap<>();
        if(StringUtil.isBlank(orgFlowEdit)){
            orgFlowEdit = currUser.getOrgFlow();
        }
        paramMap.put("orgFlow", orgFlowEdit);
        model.addAttribute("orgFlow", orgFlowEdit);
//        List<Map<String, String>> orgList = speAssignBiz.searchOrgInfoList(paramMap);
        SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
        List<SysOrg> orgList = new ArrayList<>();
        orgList.add(org);
        model.addAttribute("orgList", orgList);
        return "jsres/message/addSend";
    }

    /**
     * @Department：研发部
     * @Description 显示基地下的专业信息
     * @Author fengxf
     * @Date 2020/6/6
     */
    @RequestMapping("/addPlanSpe")
    public String addPlanSpe(String orgFlow, String assignYear,String flag, Model model) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("assignYear", assignYear);
        List<Map<String, String>> orgSpeListAlready = speAssignBiz.searchAssignOrgSpeListNew(paramMap);
        if(CollectionUtils.isEmpty(orgSpeListAlready) && StringUtil.isNotBlank(assignYear)){
            // 保存基地专业信息到招生计划表
            speAssignBiz.insertOrgSpeAssign(paramMap);
        }
        // 查询主基地住院医师和助理全科专业信息
        List<Map<String, String>> orgSpeList = speAssignBiz.searchAssignOrgSpeListNew(paramMap);
        for (Map<String, String> orgSpe : orgSpeList) {
            ResOrgSpe resOrgSpe = speAssignBiz.findOrgSpe(orgFlow, assignYear, orgSpe.get("SPE_ID"));
            if (resOrgSpe != null) {
                orgSpe.put("STATUS", resOrgSpe.getStatus());
            }
        }
        model.addAttribute("orgSpeList", orgSpeList);
        model.addAttribute("flag",flag);
        model.addAttribute("assignYear",assignYear);
        return "jsres/message/addPlanSpe";
    }

    /**
     * @Department：研发部
     * @Description 显示基地下的专业信息
     * @Author fengxf
     * @Date 2020/6/6
     */
    @RequestMapping("/addSendSpe")
    public String addSendSpe(String orgFlow, String assignYear,String flag, Model model) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("assignYear", assignYear);
        List<Map<String, String>> orgSpeListAlready = speAssignBiz.searchAssignOrgSpeListNew(paramMap);
        if(CollectionUtils.isEmpty(orgSpeListAlready) && StringUtil.isNotBlank(assignYear)){
            // 保存基地专业信息到招生计划表
            speAssignBiz.insertOrgSpeAssign(paramMap);
        }
        // 查询主基地住院医师和助理全科专业信息
        List<Map<String, String>> orgSpeList = speAssignBiz.searchAssignOrgSpeListNew(paramMap);
        for (Map<String, String> orgSpe : orgSpeList) {
            ResOrgSpe resOrgSpe = speAssignBiz.findOrgSpe(orgFlow, assignYear, orgSpe.get("SPE_ID"));
            if (resOrgSpe != null) {
                orgSpe.put("STATUS", resOrgSpe.getStatus());
            }
        }
        model.addAttribute("orgSpeList", orgSpeList);
        model.addAttribute("flag",flag);
        model.addAttribute("assignYear",assignYear);
        return "jsres/message/addSendSpe";
    }

    /**
     * @Department：研发部
     * @Description 招录计划专业简介信息
     * @Author fengxf
     * @Date 2020/6/6
     */
    @RequestMapping("/addPlanSpeDesc")
    public String addPlanSpeDesc(String assignFlow, Model model) {
        // 查询招生计划信息
        ResOrgSpeAssign resOrgSpeAssign = speAssignBiz.getResOrgSpeAssignInfo(assignFlow);
        model.addAttribute("assign", resOrgSpeAssign);
        return "jsres/message/addPlanSpeDesc";
    }

    /**
     * @Department：研发部
     * @Description 专业简介
     * @Author fengxf
     * @Date 2020/6/7
     */
    @RequestMapping("/showPlanSpeDesc")
    public String showPlanSpeDesc(String assignFlow, Model model) {
        // 查询招生计划信息
        ResOrgSpeAssign resOrgSpeAssign = speAssignBiz.getResOrgSpeAssignInfo(assignFlow);
        model.addAttribute("assign", resOrgSpeAssign);
        return "jsres/message/planSpeDescView";
    }

    /**
     * @Department：研发部
     * @Description 保存招生计划信息
     * @Author fengxf
     * @Date 2020/6/6
     */
    @ResponseBody
    @RequestMapping("/saveAssign")
    public String saveAssign(String orgFlow, String assignYear, String assignYearEdit,String isJointOrg,
                             String startTime,String endTime,String assignDataStr) {
        Map<String, Object> param = new HashMap<>();
        param.put("orgFlow", orgFlow);
        param.put("assignYear", assignYear);
        param.put("assignYearEdit", assignYearEdit);
        param.put("startTime", startTime);
        param.put("endTime", endTime);
        param.put("isJointOrg",isJointOrg);
        if(StringUtil.isNotBlank(assignDataStr) && !"[]".equals(assignDataStr)){
            List<Map> assignList = JSON.parseArray(assignDataStr, Map.class);
            param.put("assignList", assignList);
        }else{
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
        return speAssignBiz.updateAssignInfo(param);
    }

    /**
     * @Department：研发部
     * @Description 保存报送计划信息
     * @Author xieyh
     * @Date 2024-10-30
     */
    @ResponseBody
    @RequestMapping("/saveSend")
    public String saveSend(String orgFlow, String assignYear, String assignYearEdit, String isJointOrg,
                             String startTime,String endTime,String assignDataStr) {
        Map<String, Object> param = new HashMap<>();
        param.put("orgFlow", orgFlow);
        param.put("assignYear", assignYear);
        param.put("assignYearEdit", assignYearEdit);
        param.put("sendStartTime", startTime);
        param.put("sendEndTime", endTime);
        param.put("isJointOrg",isJointOrg);
        if(StringUtil.isNotBlank(assignDataStr) && !"[]".equals(assignDataStr)){
            List<Map> assignList = JSON.parseArray(assignDataStr, Map.class);
            param.put("assignList", assignList);
        }else{
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
        return speAssignBiz.updateSendInfo(param);
    }

    @ResponseBody
    @RequestMapping("/saveAudit")
    public String saveAudit(String orgFlow, String assignYear, String auditStatus) {
        Map<String, Object> param = new HashMap<>();
        param.put("orgFlow", orgFlow);
        param.put("assignYear", assignYear);
        param.put("auditStatus", auditStatus);
        String auditStatusName = "审核不通过";
        if("Passed".equals(auditStatus)){
            auditStatusName = "审核通过";
        }
        param.put("auditStatusName", auditStatusName);
        return speAssignBiz.updateAssignAudit(param);
    }

    /**
     * @Department：研发部
     * @Description
     * @Author fengxf
     * @Date 2020/6/6
     */
    @ResponseBody
    @RequestMapping("/saveAssignSpeDesc")
    public String saveAssignSpeDesc(String assignFlow, String speDesc) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("assignFlow", assignFlow);
        paramMap.put("speDesc", speDesc);
        return speAssignBiz.updateAssignSpeDesc(paramMap);
    }

    @RequestMapping(value="/planInfo")
    public String planInfo(String assignYear,String orgFlow,Model model,Integer currentPage,HttpServletRequest request){
        SysOrgExample example = new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        criteria.andOrgTypeIdEqualTo(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        example.setOrderByClause("ORDINAL");
        List<SysOrg> orgList = orgBiz.searchOrgByExample(example);
        model.addAttribute("orgList",orgList);
        model.addAttribute("orgFlow",orgFlow);

        PageHelper.startPage(currentPage,getPageSize(request));
        if(StringUtil.isNotBlank(assignYear)){
            List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(orgFlow,assignYear);
            if(speAssignList!=null && speAssignList.size()>0){
                Map<String,ResOrgSpeAssign> speAssignMap = new HashMap<String,ResOrgSpeAssign>();
                for(ResOrgSpeAssign rosa : speAssignList){
                    speAssignMap.put(rosa.getSpeId(),rosa);
                }
                model.addAttribute("speAssignMap",speAssignMap);
                model.addAttribute("speAssignList",speAssignList);
                model.addAttribute("assignYear",assignYear);
            }
        }
        return "jsres/message/planInfo";
    }


    /**
     * 招录计划
     */
    @RequestMapping(value="/doctorPlan")
    public String doctorPlan(String assignYear , Model model,Integer currentPage,HttpServletRequest request,
                             String orgFlow,String orgCode){
        SysOrg sysorg = new SysOrg();
        sysorg.setOrgProvId("320000");
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs",orgs);
        model.addAttribute("orgCode",orgCode);
        model.addAttribute("assignYear",assignYear);
        Map<String,Object> paramMap = new HashMap<>();
        if(StringUtil.isBlank(assignYear)){
            //默认查询正在进行的招录计划
            assignYear = DateUtil.getYear();
            paramMap.put("currDate",DateUtil.getCurrDate());
        }
        paramMap.put("assignYear",assignYear);
        paramMap.put("orgFlow",orgFlow);
        paramMap.put("orgCode",orgCode);
        PageHelper.startPage(currentPage,getPageSize(request));
//        List<Map<String, Object>> resultMapList = speAssignBiz.searchAssignInfoList(paramMap);
        List<Map<String, Object>> resultMapList = speAssignBiz.searchAssignInfoListNew(paramMap);
        model.addAttribute("resultMapList",resultMapList);
        return "jsres/message/doctorPlan";
    }

    /**
     * 微信端招录计划
     */
    @RequestMapping(value="/doctorPlan2")
    @ResponseBody
    public List<ZljhVo> doctorPlan2(){
        Map<String,Object> paramMap = new HashMap<>();
        List<ZljhVo> zljhVoList = new ArrayList<>();
        List<Map<String, Object>> resultMapList = speAssignBiz.searchAssignInfoList(paramMap);
        for (Map<String,Object> map:resultMapList) {
            ZljhVo zljhVo = new ZljhVo();
            zljhVo.setAssignYear((String) map.get("ASSIGN_YEAR"));
            zljhVo.setOrgFlow((String) map.get("ORG_FLOW"));
            zljhVo.setOrgName((String) map.get("ORG_NAME"));
            zljhVo.setOrgType((String) map.get("ORG_TYPE"));
            zljhVo.setAssignPlanSum(map.get("ASSIGN_PLAN_SUM").toString());
            zljhVoList.add(zljhVo);
        }
        return zljhVoList;
    }

    /**
     * @Department：研发部
     * @Description 展示 所选基地下的所有专业的报名计划列表信息
     * @param
     * @Author XieSH
     * @Date 2020/6/6
     */
    @RequestMapping(value="/doctorPlanInfo")
    public String doctorPlanInfo(String assignYear,String orgFlow,Model model){
        SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("assignYear", assignYear);
        // 查询报考专业信息
//        List<Map<String, String>> orgSpeList = speAssignBiz.searchAssignOrgSpeList(paramMap);
        List<Map<String, String>> orgSpeList = speAssignBiz.searchAssignOrgSpeListNew(paramMap);
        model.addAttribute("orgSpeList", orgSpeList);
        model.addAttribute("sysOrg", sysOrg);
        //判断招录计划是否开始
        String signupFlag = com.pinde.core.common.GlobalConstant.FLAG_N;//不可报名
        if(null != orgSpeList && orgSpeList.size()>0){
            String currDate = DateUtil.getCurrDate();
            Map<String,String> map = orgSpeList.get(0);
            String startTime = map.get("START_TIME");
            String endTime = map.get("END_TIME");
            if(startTime.compareTo(currDate)>0 || currDate.compareTo(endTime)>0){
                model.addAttribute("signupMsg", "招录时间未到！");
                model.addAttribute("signupBtnFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
            }else{
                signupFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(signupFlag)) {
            // 判断学员是否可以报名
            String signupMsg = speAssignBiz.doctorSignupFlagNew();
            if (StringUtil.isNotBlank(signupMsg)) {
                model.addAttribute("signupMsg", signupMsg);
                model.addAttribute("signupBtnFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
            } else {
                model.addAttribute("signupBtnFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
            }
        }
        //2021级 jsres_doctor_spe 只能报全科
        String speFlag = com.pinde.core.common.GlobalConstant.FLAG_N;
        SysUser sysUser = GlobalContext.getCurrentUser();
//        List<JsresDoctorSpe> docSpeList = speAssignBiz.searchDoctorSpe();
//        if(null != docSpeList && docSpeList.size()>0){
//            for (JsresDoctorSpe spe:docSpeList) {
//                if(spe.getIdNo().equals(sysUser.getIdNo())){
//                    speFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
//                    break;
//                }
//            }
//        }
        List<Map<String,String>> docSpeList = resDoctorBiz.searchDoctorSpe();
        if(null != docSpeList && docSpeList.size()>0){
            for (Map<String,String> spe:docSpeList) {
                String idNo = spe.get("ID_NO");
                String doctorTypeId = spe.get("DOCTOR_TYPE_ID");
                if(idNo.equals(sysUser.getIdNo()) && !"Graduate".equals(doctorTypeId)){
                    speFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    break;
                }
            }
        }
        model.addAttribute("speFlag",speFlag);

        List<SysOrg> sysOrgList = orgBiz.searchJointOrgsByOrg(orgFlow);
        model.addAttribute("sysOrgList",sysOrgList);

        return "jsres/message/doctorPlanView";
    }

    @RequestMapping(value="/doctorPlanInfo2")
    @ResponseBody
    public ResultVo doctorPlanInfo2(String assignYear,String orgFlow){
        DoctorPlanInfoVo planInfoVo = new DoctorPlanInfoVo();
        SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("assignYear", assignYear);
        // 查询报考专业信息
        List<OrgSpeListVo> orgSpeList = speAssignBiz.searchAssignOrgSpeList2(orgFlow,assignYear);
        planInfoVo.setOrgSpeList(orgSpeList);
        planInfoVo.setSysOrg(sysOrg);
        // 判断学员是否可以报名
        String signupMsg = speAssignBiz.doctorSignupFlag2();
        if(StringUtil.isNotBlank(signupMsg)){
            planInfoVo.setSignupMsg(signupMsg);
            planInfoVo.setSignupBtnFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
        }else{
            planInfoVo.setSignupBtnFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
        }
        return ResultUtil.exec(true,"OK",planInfoVo);
    }

    /**
     * 医师信息审核
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"/localSignupConfirm"})
    public String localSignupConfirm(Model model) {
        SysUser currUser = GlobalContext.getCurrentUser();
        //判断是否是协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        model.addAttribute("isJointOrg",isJointOrg);
        return "jsres/message/signupConfirm";
    }

    @RequestMapping(value = {"/localSignupConfirmAcc"})
    public String localSignupConfirmAcc(Model model) {
        SysUser currUser = GlobalContext.getCurrentUser();
        //判断是否是协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        model.addAttribute("isJointOrg",isJointOrg);
        return "jsres/message/signupConfirmAcc";
    }

    @RequestMapping(value = "/localSignupConfirmList")
    public String doctorRecruitList(ResDoctorRecruit resDoctorRecruit, SysUser sysUser, Integer currentPage, HttpServletRequest request,
                                    Model model, String datas[]) {
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(resDoctorRecruit.getSessionNumber())) {
            String[] numbers = resDoctorRecruit.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                resDoctorRecruit.setSessionNumber("");
            }
        }
        SysUser currUser = GlobalContext.getCurrentUser();
        resDoctorRecruit.setOrgFlow(currUser.getOrgFlow());
//		resDoctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        } else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
            int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
                datas[i++] = e.getId();
            }
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<JsResDoctorRecruitExt> recruitList = jsResDoctorRecruitBiz.resDoctorRecruitExtList1(resDoctorRecruit, sysUser, null, docTypeList, sessionNumbers);
        model.addAttribute("recruitList", recruitList);
        model.addAttribute("currentDate", DateUtil.getCurrDateTime2());
        return "jsres/recruitList";
    }

    /**
     * @Department：研发部
     * @Description 查询学员报名的报考记录
     * @param
     * @Author weilikai
     * @Date 2020/6/2
     */
    @RequestMapping(value="/doctorRegister")
    public String doctorRegister(String assignYear , String orgFlow ,Integer currentPage, Model model,HttpServletRequest request){
        Map<String,Object> paramMap = new HashMap<>();
        //获取当前登录用户的唯一标识
        String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        //构建查询对象 填充属性 begin
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        PageHelper.startPage(currentPage,getPageSize(request));
        recruit.setDoctorFlow(userFlow);
        recruit.setRecruitYear(assignYear);
        recruit.setOrgFlow(orgFlow);
        //构建查询对象end
        //根据当前用户的 userFlow  查询属于自己的报名信息
        List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "");
        SysCfg sysCfg = sysCfgMapper.selectByPrimaryKey("jsres_is_train");
        if (null !=sysCfg && StringUtil.isNotBlank(sysCfg.getCfgValue())){
            model.addAttribute("jsres_is_train",sysCfg.getCfgValue());
        }
        model.addAttribute("recruitList",recruitList);
        model.addAttribute("userName",GlobalContext.getCurrentUser().getUserName());
        return "jsres/message/doctorRegister";
    }

    /**
     * @Department：研发部
     * @Description 刷新老数据 （是否重培）
     * @Author ww
     * @Date 2021/8/2
     */
    @RequestMapping(value="/resDoctorRecruitRefresh")
    public String resDoctorRecruitRefresh(){
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "");
        for (ResDoctorRecruit resDoctorRecruit : recruitList) {
            if(StringUtil.isEmpty(resDoctorRecruit.getIsRetrain())) {
                resDoctorRecruit.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
                int count = jsResDoctorRecruitBiz.queryCountByDoctFlow(resDoctorRecruit.getDoctorFlow());
                if (count > 1) {
                    resDoctorRecruit.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                }
                jsResDoctorRecruitBiz.resDoctorRecruitRefresh(resDoctorRecruit);
            }
        }
        return "ok";

    }

    /**
     * @Department：研发部
     * @Description 对选中的报到信息 进行确认操作 报到之前先确认 基地是否录取
     * @param {@link null}
     * @Author weilikai
     * @Date 2020/6/2
     */
    @RequestMapping(value="/doRegister")
    @ResponseBody
    public String doRegister(ResDoctorRecruit recruit,String prevRecruitFlow, String prevCompleteFileUrl, String prevCompleteCertNo,Model model){
        SysUser currUser = GlobalContext.getCurrentUser();
        String doctorFlow = currUser.getUserFlow();
        //构建报名信息类的对象 用于操作数据库
//        ResDoctorRecruit recruit = new ResDoctorRecruit();
        ResDoctorRecruitWithBLOBs docRecWithBLOBs = new ResDoctorRecruitWithBLOBs();
        docRecWithBLOBs.setDoctorFlow(doctorFlow);
        docRecWithBLOBs.setRecruitFlow(recruit.getRecruitFlow());
        docRecWithBLOBs.setProveFileUrl(recruit.getProveFileUrl());//减免附件
        if("21".equals(docRecWithBLOBs.getDoctorStatusId())){
            docRecWithBLOBs.setSpecialFileUrl(prevCompleteFileUrl);
            docRecWithBLOBs.setSpecialCertNo(prevCompleteCertNo);
        }

        //确认报到之前，先查询该条记录是否已被基地录取
        List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(docRecWithBLOBs,"");
        boolean recruitFlag = false;
        if(null != recruitList && recruitList.size() >= 0){
            recruitFlag = com.pinde.core.common.GlobalConstant.FLAG_Y.equalsIgnoreCase(recruitList.get(0).getRecruitFlag()) ? true : false;
            if (!recruitFlag) {
                return "需要等待基地确认录取，学员方可确认报到";
            }
            boolean confirmFlag = com.pinde.core.common.GlobalConstant.FLAG_Y.equalsIgnoreCase(recruitList.get(0).getConfirmFlag()) ? true : false;
            if (confirmFlag) {
                return "已经报到过了，无需重复确认";
            }
        }
        //confirmFlag字段 当学员确认报到 置为Y 表示学员已报到
        docRecWithBLOBs.setConfirmFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);

//        //查询专业轮转方案 设置学员轮转方案
//        String catSpeId = recruit.getCatSpeId();
//        SchRotation rotation = new SchRotation();
//        // 此处不能直接使用住院医师类型，助理全科无法查到培训方案
//        rotation.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
//        if(RecDocCategoryEnum.AssiGeneral.getId().equals(catSpeId)){
//            rotation.setDoctorCategoryId(catSpeId);
//        }
//        String speId = recruit.getSpeId();
//        rotation.setSpeId(speId);
//        rotation.setPublishFlag(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//        List<SchRotation> rotationList = schRotationtBiz.searchOrgStandardRotation(rotation);
//        if(null != rotationList && rotationList.size()>0){
//            // 判断如果是全科 使用2020年新培训方案 需求1453
//            if("50".equals(speId)){
//                for (SchRotation schRotation: rotationList ) {
//                    if(schRotation.getRotationName().contains("2020西医助理")){
//                        docRecWithBLOBs.setRotationFlow(schRotation.getRotationFlow());
//                        docRecWithBLOBs.setRotationName(schRotation.getRotationName());
//                        break;
//                    }
//                }
//                if (StringUtil.isBlank(docRecWithBLOBs.getRotationFlow())) {
//                    return "助理全科暂停使用旧方案，请维护助理全科专业2020西医助理全科培训方案"; // 需求1453
//                }
//            }else{
//                docRecWithBLOBs.setRotationFlow(rotationList.get(0).getRotationFlow());
//                docRecWithBLOBs.setRotationName(rotationList.get(0).getRotationName());
//            }
//        }
        docRecWithBLOBs.setCurrDegreeCategoryId(recruit.getCurrDegreeCategoryId());
        docRecWithBLOBs.setRecruitDate(recruit.getRecruitDate());
        docRecWithBLOBs.setSessionNumber(recruit.getSessionNumber());
        docRecWithBLOBs.setTrainYear(recruit.getTrainYear());
        docRecWithBLOBs.setYetTrainYear(recruit.getYetTrainYear());
        docRecWithBLOBs.setDoctorStatusId(recruit.getDoctorStatusId());
        docRecWithBLOBs.setDoctorStrikeId(recruit.getDoctorStrikeId());
        docRecWithBLOBs.setCatSpeId(recruit.getCatSpeId());
        docRecWithBLOBs.setCatSpeName(recruit.getCatSpeName());
        //医师状态
        if(StringUtil.isNotBlank(recruit.getDoctorStatusId())){
            docRecWithBLOBs.setDoctorStatusName(com.pinde.core.common.enums.DictTypeEnum.DoctorStatus.getDictNameById(recruit.getDoctorStatusId()));
        }
        //医师走向
        if(StringUtil.isNotBlank(recruit.getDoctorStrikeId())){
            docRecWithBLOBs.setDoctorStrikeName(com.pinde.core.common.enums.DictTypeEnum.DoctorStrike.getDictNameById(recruit.getDoctorStrikeId()));
        }
        if(StringUtil.isNotBlank(recruit.getCurrDegreeCategoryId())){
            docRecWithBLOBs.setCurrDegreeCategoryName(com.pinde.core.common.enums.JsResDegreeCategoryEnum.getNameById(recruit.getCurrDegreeCategoryId()));
        }

        //结业审核年份
        int year=0;
        if(StringUtil.isNotBlank(docRecWithBLOBs.getSessionNumber())&&StringUtil.isNotBlank(docRecWithBLOBs.getTrainYear())){
            int num=0;
            if (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(docRecWithBLOBs.getTrainYear())) {
                num=1;
            }
            if (com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(docRecWithBLOBs.getTrainYear())) {
                num=2;
            }
            if (com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId().equals(docRecWithBLOBs.getTrainYear())) {
                num=3;
            }
            year = Integer.parseInt(docRecWithBLOBs.getSessionNumber())+num;
            docRecWithBLOBs.setGraduationYear(year+"");
        }
        //报名通道确认报到后进去报到审核
        docRecWithBLOBs.setAuditStatusId("Auditing");
        docRecWithBLOBs.setAuditStatusName("待报到审核");
        if(StringUtil.isNotBlank(recruitList.get(0).getJointOrgFlow())){
            docRecWithBLOBs.setJointOrgAudit("Auditing");
            docRecWithBLOBs.setOrgAudit("Auditing");
        }else{
            docRecWithBLOBs.setJointOrgAudit("Passed");
            docRecWithBLOBs.setOrgAudit("Auditing");
        }
        int result = jsResDoctorRecruitBiz.saveDoctorRecruit(docRecWithBLOBs);
        if(result > 0){
            return "报到成功";
        }
        return "报到失败";
    }

    /**
     * 在招录计划中去报名  学员报名
     * @return
     */
    @RequestMapping("/editDoctorRecruit")
    public String editDoctorRecruit(String recruitFlow, String speId, String orgFlow, String assignYear,
                                    String jointOrgFlows,String jointOrgNames, Model model,HttpServletRequest request) {
        String doctorFlow = GlobalContext.getCurrentUser().getUserFlow();
        ResDoctorRecruit doctorRecruit = null;
        doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
        model.addAttribute("doctorRecruit", doctorRecruit);
        model.addAttribute("addRecord", com.pinde.core.common.GlobalConstant.FLAG_Y);//添加新的培训记录标识
        //根据专业ID speId ,机构流水号 orgFlow 查询对应的名称
        SysOrgExample example = new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        criteria.andOrgFlowEqualTo(orgFlow);
        example.setOrderByClause("ORDINAL");
        List<SysOrg> orgList = orgBiz.searchOrgByExample(example);

        //根据机构ID和年份查询 专业
        List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(orgFlow, assignYear);
        String speName = "";
        if (speAssignList != null && speAssignList.size() > 0) {
            for (ResOrgSpeAssign rosa : speAssignList) {
                if (rosa.getSpeId().equals(speId)) {
                    speName = rosa.getSpeName();
                }
            }
        }
        //根据协同基地ID 查询 主基地与协同基地的关联表，确认当前报考的是否是主基地
        //表示如果查到的为空 表示当前选中的基地是主基地 反之为协同基地
        List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(orgFlow);
        //协同基地ID 名称 是否在协同基地培训
        String jointOrgFlow = "";
        String jointOrgName = "";
        String inJointOrgTrain = "";
        String orgName = "";
        String placeId = "";
        String placeName = "";
        if (joinOrgs.size() == 0) {
            jointOrgFlow = "";
            jointOrgName = "";
            inJointOrgTrain = com.pinde.core.common.GlobalConstant.FLAG_N;
            if (orgList.size() == 0){
                placeId  = "";
                placeName  = "";
            }
            else {
                placeId = orgList.get(0).getOrgCityId();
                placeName = orgList.get(0).getOrgCityName();
                orgFlow = orgList.get(0).getOrgFlow();
                orgName = orgList.get(0).getOrgName();
            }
        } else {
            jointOrgFlow = joinOrgs.get(0).getJointOrgFlow();
            jointOrgName = joinOrgs.get(0).getJointOrgName();
            //从关联表中查询主基地名称与ID
            orgFlow = joinOrgs.get(0).getOrgFlow();
            orgName = joinOrgs.get(0).getOrgName();
            inJointOrgTrain = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }

        model.addAttribute("speId", speId);
        model.addAttribute("speName", speName);
        model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("jointOrgFlow", jointOrgFlow);
        model.addAttribute("jointOrgName", jointOrgName);
        model.addAttribute("inJointOrgTrain", inJointOrgTrain);
        //从关联表中查询主基地名称与ID
        model.addAttribute("orgName",orgName);
        model.addAttribute("placeId", placeId);
        model.addAttribute("placeName", placeName);
        model.addAttribute("assignYear", assignYear);
        //已审核通过
        ResDoctorRecruit passedRec = new ResDoctorRecruit();
        passedRec.setDoctorFlow(doctorFlow);
        passedRec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        passedRec.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        List<ResDoctorRecruit> passedRecruitList = this.jsResDoctorRecruitBiz.searchResDoctorRecruitList(passedRec, "MODIFY_TIME DESC");
        //其中一阶段、住院医师审核通过（选二阶段使用）
        List<ResDoctorRecruit> prevPassedList = new ArrayList<ResDoctorRecruit>();
        if (passedRecruitList != null && !passedRecruitList.isEmpty()) {
            model.addAttribute("passedRecruitList", passedRecruitList);
            //记录审核通过的培训类别(不包含首条为二阶段自动生成的一阶段)
            List<String> catSpeIdList = new ArrayList<String>();
            for (ResDoctorRecruit rec : passedRecruitList) {
                String catSpeId = rec.getCatSpeId();
                if (!catSpeIdList.contains(catSpeId) && StringUtil.isNotBlank(rec.getSpeId())) {
                    catSpeIdList.add(catSpeId);
                }
                if (StringUtil.isBlank(rec.getSpeId())) {//首条为二阶段
                    model.addAttribute("firstRecIsWMSecond", true);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId().equals(rec.getCatSpeId()) || com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(rec.getCatSpeId())) {
                    prevPassedList.add(rec);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(rec.getCatSpeId())) {//二阶段审核通过
                    model.addAttribute("isWMSecondRecPassed", true);
                }
            }
            model.addAttribute("catSpeIdList", catSpeIdList);
        }
        if (prevPassedList != null && !prevPassedList.isEmpty()) {
            model.addAttribute("prevPassedList", prevPassedList);
            if (StringUtil.isNotBlank(recruitFlow)) {//回显上一阶段  结业证书
                model.addAttribute("latestPrevPassed", prevPassedList.get(0));
            }
        }
        model.addAttribute("jointOrgFlow", jointOrgFlows);
        model.addAttribute("jointOrgName", jointOrgNames);
        return "jsres/message/doctorPlanInfoRecruit";

    }

    /**
     * 在微信端招录计划中去报名  学员报名
     * @return
     */
    @RequestMapping("/editDoctorRecruit2")
    @ResponseBody
    public ResultVo editDoctorRecruit2(String recruitFlow, String speId, String orgFlow, String assignYear) {
        DoctorRecruitVo recruitVo = new DoctorRecruitVo();
        String doctorFlow = GlobalContext.getCurrentUser().getUserFlow();
        ResDoctorRecruit doctorRecruit = null;
        doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
        recruitVo.setDoctorRecruit(doctorRecruit);
        recruitVo.setAddRecord(com.pinde.core.common.GlobalConstant.FLAG_Y);//添加新的培训记录标识
        //根据专业ID speId ,机构流水号 orgFlow 查询对应的名称
        SysOrgExample example = new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        criteria.andOrgFlowEqualTo(orgFlow);
        example.setOrderByClause("ORDINAL");
        List<SysOrg> orgList = orgBiz.searchOrgByExample(example);

        //根据机构ID和年份查询 专业
        List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(orgFlow, assignYear);
        String speName = "";
        if (speAssignList != null && speAssignList.size() > 0) {
            for (ResOrgSpeAssign rosa : speAssignList) {
                if (rosa.getSpeId().equals(speId)) {
                    speName = rosa.getSpeName();
                }
            }
        }
        //根据协同基地ID 查询 主基地与协同基地的关联表，确认当前报考的是否是主基地
        //表示如果查到的为空 表示当前选中的基地是主基地 反之为协同基地
        List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(orgFlow);
        //协同基地ID 名称 是否在协同基地培训
        String jointOrgFlow = "";
        String jointOrgName = "";
        String inJointOrgTrain = "";
        String orgName = "";
        String placeId = "";
        String placeName = "";
        if (joinOrgs.size() == 0) {
            jointOrgFlow = "";
            jointOrgName = "";
            inJointOrgTrain = com.pinde.core.common.GlobalConstant.FLAG_N;
            if (orgList.size() == 0){
                placeId  = "";
                placeName  = "";
            }
            else {
                placeId = orgList.get(0).getOrgCityId();
                placeName = orgList.get(0).getOrgCityName();
                orgFlow = orgList.get(0).getOrgFlow();
                orgName = orgList.get(0).getOrgName();
            }
        } else {
            jointOrgFlow = joinOrgs.get(0).getJointOrgFlow();
            jointOrgName = joinOrgs.get(0).getJointOrgName();
            //从关联表中查询主基地名称与ID
            orgFlow = joinOrgs.get(0).getOrgFlow();
            orgName = joinOrgs.get(0).getOrgName();
            inJointOrgTrain = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        recruitVo.setSpeId(speId);
        recruitVo.setSpeName(speName);
        recruitVo.setOrgFlow(orgFlow);
        recruitVo.setJointOrgFlow(jointOrgFlow);
        recruitVo.setJointOrgName(jointOrgName);
        recruitVo.setInJointOrgTrain(inJointOrgTrain);

        //从关联表中查询主基地名称与ID
        recruitVo.setOrgName(orgName);
        recruitVo.setPlaceId(placeId);
        recruitVo.setPlaceName(placeName);
        recruitVo.setAssignYear(assignYear);
        //已审核通过
        ResDoctorRecruit passedRec = new ResDoctorRecruit();
        passedRec.setDoctorFlow(doctorFlow);
        passedRec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        passedRec.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        List<ResDoctorRecruit> passedRecruitList = this.jsResDoctorRecruitBiz.searchResDoctorRecruitList(passedRec, "MODIFY_TIME DESC");
        //其中一阶段、住院医师审核通过（选二阶段使用）
        List<ResDoctorRecruit> prevPassedList = new ArrayList<ResDoctorRecruit>();
        if (passedRecruitList != null && !passedRecruitList.isEmpty()) {
            recruitVo.setPassedRecruitList(passedRecruitList);
            //记录审核通过的培训类别(不包含首条为二阶段自动生成的一阶段)
            List<String> catSpeIdList = new ArrayList<String>();
            for (ResDoctorRecruit rec : passedRecruitList) {
                String catSpeId = rec.getCatSpeId();
                if (!catSpeIdList.contains(catSpeId) && StringUtil.isNotBlank(rec.getSpeId())) {
                    catSpeIdList.add(catSpeId);
                }
                if (StringUtil.isBlank(rec.getSpeId())) {//首条为二阶段
                    recruitVo.setFirstRecIsWMSecond(true);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId().equals(rec.getCatSpeId()) || com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(rec.getCatSpeId())) {
                    prevPassedList.add(rec);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(rec.getCatSpeId())) {//二阶段审核通过
                    recruitVo.setWMSecondRecPassed(true);
                }
            }
            recruitVo.setCatSpeIdList(catSpeIdList);
        }
        if (prevPassedList != null && !prevPassedList.isEmpty()) {
            recruitVo.setPrevPassedList(prevPassedList);
            if (StringUtil.isNotBlank(recruitFlow)) {//回显上一阶段  结业证书
                recruitVo.setLatestPrevPassed(prevPassedList.get(0));
            }
        }
        return ResultUtil.exec(true,"OK",recruitVo);

    }

    /**
     * @Department：研发部
     * @Description 加载基地专业信息
     * @Author Zjie
     * @Date 0027, 2020年11月27日
     */
    @RequestMapping("/searchSpeList")
    @ResponseBody
    public Object searchSpeList(String orgFlow) {
        if (StringUtil.isNotBlank(orgFlow)) {
            ResOrgSpe serach = new ResOrgSpe();
            serach.setOrgFlow(orgFlow);
            serach.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<ResOrgSpe> resBaseList = resOrgSpeBiz.searchResOrgSpeList(serach);
            Map<String, Object> result = new HashMap<String, Object>() {{
                this.put("resBaseList", resBaseList);
            }};
            return result;
        }
        return new HashMap<String, Object>();
    }

    /**
     * 保存报考信息
     * @param docRecWithBLOBs
     * @param prevRecruitFlow
     * @param prevCompleteFileUrl
     * @param prevCompleteCertNo
     * @param model
     * 注：1、二阶段的住培结业证书附件（二阶段报名需一阶段结业，二阶段的住培结业证书附件即一阶段的结业证书附件）
     * 			存放在res_doctor_recruit中complete_file_url中
     * 	   2、二阶段的专培结业证书附件（该条记录的结业附件）
     * 			存放在res_doctor_recruit中special_file_url（该条记录的结业附件）中
     * 	   3、除二阶段以外的住培结业证书附件（该条记录的结业附件）
     * 			存放在res_doctor_recruit中special_file_url（该条记录的结业附件）中
     * @return
     */
    @RequestMapping(value="/saveResDoctorRecruit", method={RequestMethod.POST})
    @ResponseBody
    public String saveResDoctorRecruit(ResDoctorRecruitInfo resDoctorRecruitInfo,ResDoctorRecruitWithBLOBs docRecWithBLOBs, String prevRecruitFlow, String prevCompleteFileUrl, String prevCompleteCertNo, Model model,HttpServletRequest request) throws ParseException, DocumentException {

        if (StringUtil.isEmpty(docRecWithBLOBs.getJointOrgFlow())){ //默认值是请选择
            docRecWithBLOBs.setJointOrgName("");
        }

        SysUser sysUser = GlobalContext.getCurrentUser();
        String doctorFlow = docRecWithBLOBs.getDoctorFlow();
        docRecWithBLOBs.setSignupWay("DoctorSignup");//学员报名方式 （DoctorSend：报送，DoctorSignup:报名）
        //查询学员提交报名次数，最多报名3次
        int signupCount = jsResDoctorRecruitBiz.findSignupCount(doctorFlow,docRecWithBLOBs.getRecruitYear());
        if(signupCount >= 3){
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
        }
        //设置 报名状态是待审核 医师状态待审核 begin
        docRecWithBLOBs.setAuditStatusId(BaseStatusEnum.Auditing.getId());
        docRecWithBLOBs.setAuditStatusName(BaseStatusEnum.Auditing.getName());
        //报名为协同基地 则协同基地审核后主基地审核
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(docRecWithBLOBs.getInJointOrgTrain())) {
            docRecWithBLOBs.setDoctorStatusId(BaseStatusEnum.Auditing.getId());
            docRecWithBLOBs.setDoctorStatusName(BaseStatusEnum.Auditing.getName());
            docRecWithBLOBs.setJointOrgAudit(BaseStatusEnum.Auditing.getId());
        }else {
            docRecWithBLOBs.setDoctorStatusId("OrgAuditing");
            docRecWithBLOBs.setDoctorStatusName("待主基地审核");
            docRecWithBLOBs.setJointOrgAudit(BaseStatusEnum.Passed.getId());
        }
        docRecWithBLOBs.setReviewFlag("OrgAuditing");
        docRecWithBLOBs.setTrainYear("ThreeYear");
        //设置 报名状态是待审核 医师状态待审核 end at 2020.6.7
        if (!com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(docRecWithBLOBs.getCatSpeId())) {
            docRecWithBLOBs.setCompleteFileUrl("");
            docRecWithBLOBs.setCompleteCertNo("");
            if("21".equals(docRecWithBLOBs.getDoctorStatusId())){
                //由于页面字段展示问题现将非二阶段的该条记录的结业附件
                // name = prevCompleteFileUrl 存入special_file_url（该条记录的结业附件）中
                docRecWithBLOBs.setSpecialFileUrl(prevCompleteFileUrl);
                docRecWithBLOBs.setSpecialCertNo(prevCompleteCertNo);
            }else {
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }

            if(StringUtil.isBlank(docRecWithBLOBs.getCatSpeId())){
                ResOrgSpe serach = new ResOrgSpe();
                serach.setOrgFlow(docRecWithBLOBs.getOrgFlow());
                serach.setSpeId(docRecWithBLOBs.getSpeId());
                serach.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                List<ResOrgSpe> resBaseList = resOrgSpeBiz.searchResOrgSpeList(serach);
                if(resBaseList != null && resBaseList.size()>0){
                    ResOrgSpe resOrgSpe = resBaseList.get(0);
                    docRecWithBLOBs.setCatSpeId(resOrgSpe.getSpeTypeId());
                    docRecWithBLOBs.setCatSpeName(com.pinde.core.common.enums.TrainCategoryEnum.getNameById(resOrgSpe.getSpeTypeId()));
                }
            }

        }else {
            docRecWithBLOBs.setCompleteFileUrl(prevCompleteFileUrl);
            docRecWithBLOBs.setCompleteCertNo(prevCompleteCertNo);
            if(!"21".equals(docRecWithBLOBs.getDoctorStatusId())){
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }
        }
        try {
            ResDoctor resDoctor = resDoctorBiz.findByFlow(docRecWithBLOBs.getDoctorFlow());
            resDoctor.setDoctorStatusId("Auditing");
            resDoctor.setDoctorStatusName("报名审核中");
            resDoctorBiz.editDoctor(resDoctor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResDoctor doctor = resDoctorBiz.readDoctor(docRecWithBLOBs.getDoctorFlow());
        docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        //查询是否重培（有退培记录且审核通过为重培） 默认否
        docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
        docotrDelayTeturn.setDoctorFlow(docRecWithBLOBs.getDoctorFlow());
        docotrDelayTeturn.setTypeId("ReturnTraining");
        List<String> flags = new ArrayList<>();
        flags.add("1");
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn,null,flags,null);
        if(resRecList.size()>0){
            docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        }else{
            //非结业记录 判断入培时间 + 培训年限 + 3年  如果没结业 则为重培
            List<ResDoctorRecruit> recruitList = recruitDoctorInfoBiz.searchRecruitList(docRecWithBLOBs.getDoctorFlow());
            if(CollectionUtils.isNotEmpty(recruitList)){
                for (ResDoctorRecruit resDoctorRecruit : recruitList) {
                    //20 在培 21结业
                    int trainYear = 0;
                    if(StringUtil.isNotEmpty(resDoctorRecruit.getDoctorStatusId()) && !"21".equals(resDoctorRecruit.getDoctorStatusId())){
                        //培训年限
                        if(StringUtil.isNotEmpty(resDoctorRecruit.getTrainYear())){
//                    'OneYear' then '一年' 'TwoYear' then '两年' 'ThreeYear' then '三年'
                            if("OneYear".equals(resDoctorRecruit.getTrainYear())){
                                trainYear = 1;
                            }else if("TwoYear".equals(resDoctorRecruit.getTrainYear())){
                                trainYear = 2;
                            }else if("ThreeYear".equals(resDoctorRecruit.getTrainYear())){
                                trainYear = 3;
                            }
                        }
                        if(StringUtil.isNotEmpty(resDoctorRecruit.getRecruitDate())){
                            //培训起始时间
                            String recruitDate = resDoctorRecruit.getRecruitDate();
                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                            Calendar cd=Calendar.getInstance();
                            try{
                                cd.setTime(sdf.parse(recruitDate));
                            }catch(ParseException e){
                                e.printStackTrace();
                            }
                            cd.add(Calendar.YEAR,trainYear+3);//增加n年
                            String format = sdf.format(cd.getTime());
                            String currDate = DateUtil.getCurrDate();

                            //当前时间
                            Date fromDate1 = sdf.parse(currDate);
                            Date toDate1 = sdf.parse(format);
                            Date d1 = new Date(fromDate1.getTime());
                            Date d2 = new Date(toDate1.getTime());
                            int num = d1.compareTo(d2);
                            //条件满足 为重培
                            if(num>0){
                                docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                            }
                        }
                    }
                }
            }
        }
        logger.debug("docRecWithBLOBs:" + JSON.toJSONString(docRecWithBLOBs));
        int result = jsResDoctorRecruitBiz.saveDoctorRecruit(docRecWithBLOBs);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return docRecWithBLOBs.getRecruitFlow();
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }
    /**
     * 保存报考信息微信端
     * @param docRecWithBLOBs
     *
     * 注：1、二阶段的住培结业证书附件（二阶段报名需一阶段结业，二阶段的住培结业证书附件即一阶段的结业证书附件）
     * 			存放在res_doctor_recruit中complete_file_url中
     * 	   2、二阶段的专培结业证书附件（该条记录的结业附件）
     * 			存放在res_doctor_recruit中special_file_url（该条记录的结业附件）中
     * 	   3、除二阶段以外的住培结业证书附件（该条记录的结业附件）
     * 			存放在res_doctor_recruit中special_file_url（该条记录的结业附件）中
     * @return
     */
    @RequestMapping(value="/saveResDoctorRecruit2")
    @ResponseBody
    public String saveResDoctorRecruit2(@RequestBody ResDoctorRecruitWithBLOBs docRecWithBLOBs){

        //设置 报名状态是待审核 医师状态待审核 begin
        docRecWithBLOBs.setAuditStatusId(BaseStatusEnum.Auditing.getId());
        docRecWithBLOBs.setAuditStatusName(BaseStatusEnum.Auditing.getName());
        docRecWithBLOBs.setDoctorStatusId(BaseStatusEnum.Auditing.getId());
        docRecWithBLOBs.setDoctorStatusName(BaseStatusEnum.Auditing.getName());
        docRecWithBLOBs.setTrainYear("ThreeYear");
        //设置 报名状态是待审核 医师状态待审核 end at 2020.6.7
        if (!com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(docRecWithBLOBs.getCatSpeId())) {
            docRecWithBLOBs.setCompleteFileUrl("");
            docRecWithBLOBs.setCompleteCertNo("");
            if("21".equals(docRecWithBLOBs.getDoctorStatusId())){
                //由于页面字段展示问题现将非二阶段的该条记录的结业附件
                // name = prevCompleteFileUrl 存入special_file_url（该条记录的结业附件）中
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }else {
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }

            if(StringUtil.isBlank(docRecWithBLOBs.getCatSpeId())){
                ResOrgSpe serach = new ResOrgSpe();
                serach.setOrgFlow(docRecWithBLOBs.getOrgFlow());
                serach.setSpeId(docRecWithBLOBs.getSpeId());
                serach.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                List<ResOrgSpe> resBaseList = resOrgSpeBiz.searchResOrgSpeList(serach);
                if(resBaseList != null && resBaseList.size()>0){
                    ResOrgSpe resOrgSpe = resBaseList.get(0);
                    docRecWithBLOBs.setCatSpeId(resOrgSpe.getSpeTypeId());
                    docRecWithBLOBs.setCatSpeName(com.pinde.core.common.enums.TrainCategoryEnum.getNameById(resOrgSpe.getSpeTypeId()));
                }
            }

        }else {
            docRecWithBLOBs.setCompleteFileUrl("");
            docRecWithBLOBs.setCompleteCertNo("");
            if(!"21".equals(docRecWithBLOBs.getDoctorStatusId())){
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }
        }
        try {
            ResDoctor resDoctor = resDoctorBiz.findByFlow(docRecWithBLOBs.getDoctorFlow());
            resDoctor.setDoctorStatusId("Auditing");
            resDoctor.setDoctorStatusName("报名审核中");
            resDoctorBiz.editDoctor(resDoctor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int result = jsResDoctorRecruitBiz.saveDoctorRecruit(docRecWithBLOBs);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return "200";
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }
    /**
     * @Department：研发部
     * @Description 在报考记录下 查看详细的报考信息
     * @param {@link null}
     * @Author weilikai
     * @Date 2020/6/2
     */
    @RequestMapping(value="/detailRegister")
    public String detailRegister(String recruitFlow,Model model){
        SysUser currUser = GlobalContext.getCurrentUser();
        String doctorFlow = currUser.getUserFlow();
        //构建报名信息类的对象 用于操作数据库
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        ResDoctorRecruitWithBLOBs docRecWithBLOBs = new ResDoctorRecruitWithBLOBs();
        docRecWithBLOBs.setDoctorFlow(doctorFlow);
        docRecWithBLOBs.setRecruitFlow(recruitFlow);
        List<ResDoctorRecruit> recruits = jsResDoctorRecruitBiz.searchResDoctorRecruitList(docRecWithBLOBs,"");
        model.addAttribute(currUser);
        ResDoctorRecruit resDoctorRecruit = new ResDoctorRecruit();
        if (recruits.size() >= 0){
            resDoctorRecruit = recruits.get(0);
        }
        model.addAttribute("recruit",resDoctorRecruit);
        //查询详细报考信息中的专业排名
//        List<String> sessionNumbers = new ArrayList<String>();
//        sessionNumbers.add(resDoctorRecruit.getSessionNumber());
//        List<JsResDoctorRecruitExt> recruitExtList = jsResDoctorRecruitBiz.resDoctorRecruitExtList3(resDoctorRecruit, currUser, null, null, sessionNumbers,"Total",null);
//        JsResDoctorRecruitExt recruitExt = null;
//        if (recruitExtList.size() > 0){
//            recruitExt = recruitExtList.get(0);
//        }
//        model.addAttribute("recruitExt", recruitExt);
        List<JsResDoctorRecruitExt> recruitExtList = jsResDoctorRecruitBiz.resDoctorRecruitExtList3New(resDoctorRecruit);
        Map<String,String> rankNumMap = new HashMap<>();
        if(null != recruitExtList && recruitExtList.size()>0){
            for (JsResDoctorRecruitExt ext:recruitExtList) {
                rankNumMap.put(ext.getRecruitFlow()+ext.getDoctorFlow(),ext.getRankNum());
            }
        }
        model.addAttribute("rankNumMap", rankNumMap);
        return "jsres/message/doctorRegisterDetail";
    }

    /**
     * @Department：研发部
     * @Description 导入招录计划页面
     * @Author fengxf
     * @Date 2020/6/7
     */
    @RequestMapping("/importPlan")
    public String importPlan() {
        return "jsres/message/importPlan";
    }

    /**
     * @Department：研发部
     * @Description 导入招生计划
     * @Author fengxf
     * @Date 2020/6/7
     */
    @RequestMapping(value={"/importAssignInfo"},method={RequestMethod.POST})
    @ResponseBody
    public String importAssignInfo(String orgFlow, String assignYear, MultipartFile file){
        Map<String, String> param = new HashMap<>();
        param.put("orgFlow", orgFlow);
        param.put("assignYear", assignYear);
        if(file.getSize() > 0){
            int count = 0;
            try {
                count = speAssignBiz.importAssignInfo(param, file);
            } catch (Exception e) {
                logger.error("导入招生计划失败！", e);
                return "导入招生计划失败" + e.getMessage();
            }
            if(count != 0){
                return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
            }
        }
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
    }
    /**
     * @Department：研发部
     * @Description 个人信息打印
     * @param
     * @Author weilk
     * @Date 2020/6/8
     */
    @RequestMapping(value="/printDoc")
    public String printDoc(Model model,String recruitFlow, String doctorFlow, String applyYear,String orgName) throws DocumentException {
        Map<String,Object> printMap=new HashMap<>();
        SysUser currUser = GlobalContext.getCurrentUser();
        ResDoctor resDoctor = resDoctorBiz.readDoctor(currUser.getUserFlow());
        //个人信息 begin
        if(resDoctor!=null){
            if(StringUtil.isNotBlank(resDoctor.getGraduatedId())){
                List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.GraduateSchool.getId());
                if(sysDictList!=null && !sysDictList.isEmpty()){
                    for(SysDict dict:sysDictList){
                        if(dict.getDictId().equals(resDoctor.getGraduatedId())){
                            resDoctor.setGraduatedName(dict.getDictName());
                        }
                    }
                }
            }
            if (StringUtil.isNotBlank(resDoctor.getDoctorTypeId()) && com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(resDoctor.getDoctorTypeId())) {
                if(StringUtil.isNotBlank(resDoctor.getWorkOrgId())){
                    List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
                    if(sysDictList!=null && !sysDictList.isEmpty()){
                        for(SysDict dict:sysDictList){
                            if(dict.getDictId().equals(resDoctor.getWorkOrgId())){
                                resDoctor.setWorkOrgName(dict.getDictName());
                            }
                        }
                    }
                }
            }
        }
        model.addAttribute("user", currUser);
        model.addAttribute("doctor", resDoctor);
        PubUserResume pubUserResume = userResumeBiz.readPubUserResume(currUser.getUserFlow());
        if(pubUserResume != null){
            String xmlContent =  pubUserResume.getUserResume();
            if(StringUtil.isNotBlank(xmlContent)){
                //xml转换成JavaBean
                UserResumeExtInfoForm  userResumeExt=null;
                userResumeExt=userResumeBiz.converyToJavaBean(xmlContent,UserResumeExtInfoForm.class);
                if(userResumeExt!=null){
                    if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
                        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.GraduateSchool.getId());
                        if(sysDictList!=null && !sysDictList.isEmpty()){
                            for(SysDict dict:sysDictList){
                                if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
                                    if(dict.getDictId().equals(userResumeExt.getGraduatedId())){
                                        userResumeExt.setGraduatedName(dict.getDictName());
                                    }
                                }
                            }

                        }
                    }
                    model.addAttribute("userResumeExt", userResumeExt);
                }
            }
        }
        //个人信息 end at 2020.6.8

        //教育信息 begin
        //教育信息 end at 2020.6.8

        //医师资格信息 begin
        //医师资格信息 end at 2020.6.8

        //西部支援情况 begin
        //西部支援情况 end at 2020.6.8

        //报考信息 begin
        //这里注掉的天津的代码.   ResDoctorRecruitInfo 表不存在江苏
        /*ResDoctorRecruit doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
        ResDoctorRecruitInfo resDoctorRecruitInfo = jsResDoctorRecruitBiz.findRecruitInfo(recruitFlow);
        if (doctorRecruit != null){
            printMap.put("recruitJointOrgName",doctorRecruit.getJointOrgName());

        }
        if (resDoctorRecruitInfo != null) {
            printMap.put("recruitOrgName",resDoctorRecruitInfo.getOrgName());
            printMap.put("recruitSpeName",resDoctorRecruitInfo.getSpeName());
            printMap.put("recruitInJointOrgTrain",resDoctorRecruitInfo.getInJointOrgTrain());
            printMap.put("recruitOrgNameTwo",resDoctorRecruitInfo.getOrgNameTwo());
            printMap.put("recruitSpeNameTwo",resDoctorRecruitInfo.getSpeNameTwo());
            printMap.put("recruitInJointOrgTrainTwo",resDoctorRecruitInfo.getInJointOrgTrainTwo());
        }*/
        ResDoctorRecruit doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
        if (doctorRecruit != null){
            printMap.put("recruitOrgName",doctorRecruit.getOrgName());
            printMap.put("recruitSpeName",doctorRecruit.getSpeName());
            printMap.put("recruitInJointOrgTrain",doctorRecruit.getInJointOrgTrain());
            printMap.put("recruitJointOrgName",doctorRecruit.getJointOrgName());

        }
        //报考信息 end at 2020.6.8
        //培训记录
        ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);

        //将需要打印的字段 放入map
        printMap.put("currUser",currUser);
        printMap.put("orgName",orgName);
        model.addAttribute("printMap",printMap);
        return "jsres/message/doctorRegisterInfoPrint";
    }

    /**
     * 基地配置轮转方案
     * @return
     */
    @RequestMapping("/rotationCfg")
    public String rotationCfg(Model model,String sessionNumber, String speId) {

        if (StringUtil.isBlank(sessionNumber)) {
            sessionNumber = DateUtil.getYear();
        }
        model.addAttribute("sessionNumber", sessionNumber);
        model.addAttribute("speId", speId);

        SysUser currentUser = GlobalContext.getCurrentUser();
        assert currentUser != null;
        String orgFlow = currentUser.getOrgFlow();
        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());

        List<HashMap<String, Object>> resultList = new ArrayList<>();
        if (StringUtil.isNotBlank(speId)) {
            getRotationCfgResultMap(sessionNumber, speId, orgFlow, resultList);
        } else {
            for (SysDict sysDict : sysDictList) {
                getRotationCfgResultMap(sessionNumber, sysDict.getDictId(), orgFlow, resultList);
            }
        }

        model.addAttribute("resultList", resultList);

        return "jsres/cfg/rotationCfg/rotationCfg";
    }

    private void getRotationCfgResultMap(String sessionNumber, String speId, String orgFlow, List<HashMap<String, Object>> resultList) {
        HashMap<String, Object> map = new HashMap<>();
        String speName = com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getDictNameById(speId);
        ResOrgRotationCfg rotationCfg = schRotationtBiz.getRotationCfg(orgFlow, speId, sessionNumber);
        List<SchRotation> rotationList = getRotationList(speId);
        if (CollectionUtils.isNotEmpty(rotationList)) {
            for (SchRotation schRotation : rotationList) {
                // 初始化为N，表示均未配置
                schRotation.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
                if (rotationCfg != null && schRotation.getRotationFlow().equals(rotationCfg.getRotationFlow())) {
                    // 借用recordStatus置Y表示已配置
                    schRotation.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                }
            }
        }
        map.put("speId", speId);
        map.put("speName", speName);
        map.put("rotationCfg", rotationCfg);
        map.put("rotationList", rotationList);
        resultList.add(map);
    }

    private List<SchRotation> getRotationList(String speId) {
        SchRotation rotation = new SchRotation();
        rotation.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
        rotation.setSpeId(speId);
        rotation.setPublishFlag(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<SchRotation> rotationList = schRotationtBiz.searchOrgStandardRotation(rotation);
        List<String> versions = rotationList.stream().map(SchRotation::getRotationVersion).sorted((o1, o2) -> o2.hashCode() - o1.hashCode()).collect(Collectors.toList());
        for (int i = 0; i < rotationList.size(); i++) {
            if (!rotationList.get(i).getRotationVersion().equals(versions.get(0))) {
                rotationList.remove(i);
                i--;
            }
        }
        return rotationList;
    }

    @RequestMapping("/saveRotationCfg")
    @ResponseBody
    public String saveRotationCfg(String sessionNumber, String speId, String rotationFlow) {
        SchRotation schRotation = schRotationtBiz.readSchRotation(rotationFlow);

        SysUser currentUser = GlobalContext.getCurrentUser();
        assert currentUser != null;
        String orgFlow = currentUser.getOrgFlow();

        ResOrgRotationCfg rotationCfg = schRotationtBiz.getRotationCfg(orgFlow, speId, sessionNumber);

        // 验证该专业是否已有审核通过的学员，若已存在审核通过的学员，则无法修改
        if (StringUtil.isNotBlank(rotationCfg.getRotationCfgFlow()) && !rotationCfg.getRotationFlow().equals(rotationFlow)) {
            ResDoctorRecruit resDoctorRecruit = new ResDoctorRecruit();
            resDoctorRecruit.setOrgFlow(orgFlow);
            resDoctorRecruit.setSpeId(speId);
            resDoctorRecruit.setAuditStatusId("WaitGlobalPass,Passed");
            resDoctorRecruit.setSessionNumber(sessionNumber);
            List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.readDoctorRecruits(resDoctorRecruit);
            if (CollectionUtils.isNotEmpty(recruitList)) {
                return com.pinde.core.common.GlobalConstant.HAVE_AUDIT_PASS_STUDENT;
            }
        }

        SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
        if (StringUtil.isNotBlank(rotationCfg.getRotationCfgFlow())) {
            rotationCfg.setRotationFlow(rotationFlow);
            rotationCfg.setRotationName(schRotation.getRotationName());
        } else {
            rotationCfg.setRotationFlow(rotationFlow);
            rotationCfg.setRotationName(schRotation.getRotationName());
            rotationCfg.setOrgFlow(orgFlow);
            rotationCfg.setOrgName(sysOrg.getOrgName());
            rotationCfg.setSessionYear(sessionNumber);
            rotationCfg.setSpeId(speId);
            rotationCfg.setSpeName(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getDictNameById(speId));
            rotationCfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        }
        int i = schRotationtBiz.saveRotationCfg(rotationCfg);
        if (i == 1) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }
}
