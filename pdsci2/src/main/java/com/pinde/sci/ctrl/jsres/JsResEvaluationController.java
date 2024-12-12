package com.pinde.sci.ctrl.jsres;


import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResBaseEvaluationBiz;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzuozhou
 *
 */
@Controller
@RequestMapping("/jsres/evaluation")
public class JsResEvaluationController extends GeneralController {

    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IJsResBaseEvaluationBiz JsResBaseEvaluationBiz;
    @Autowired
    private ICfgBiz cfgBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IJsResPowerCfgBiz jsResPowerCfgBiz;
    /**
     * 获取培训基地评估信息
     *
     * @param model
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = "/evaluationInfo")
    public String evaluationInfo(Model model,HttpServletRequest request,String seeFlag,String jointOrg) throws DocumentException {
        SysUser user = GlobalContext.getCurrentUser();
        JsresBaseEvaluation jsresBaseEvaluation = new JsresBaseEvaluation();
        jsresBaseEvaluation.setOrgFlow(user.getOrgFlow());
        List<JsresBaseEvaluation> jsresBaseEvaluationList = new ArrayList<>();
        //根据机构流水号查找评估信息
        jsresBaseEvaluationList = JsResBaseEvaluationBiz.searchBaseEvaluationList(jsresBaseEvaluation);
        if(jsresBaseEvaluationList==null||jsresBaseEvaluationList.size()==0){
            jsresBaseEvaluation.setPlanYear("2019");
            jsresBaseEvaluation.setTitle("江苏省住院医师规范化培训基地评估指标（2019年版）");
            jsresBaseEvaluation.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            JsResBaseEvaluationBiz.saveJsResBaseEvaluation(jsresBaseEvaluation);
        }else{
            jsresBaseEvaluation = jsresBaseEvaluationList.get(0);
        }
        //根据评估信息流水号查找文件信息
        JsresBaseEvaluationFile jsresBaseEvaluationFile = new JsresBaseEvaluationFile();
        jsresBaseEvaluationFile.setEvaluationRecordFlow(jsresBaseEvaluation.getRecordFlow());
        List<JsresBaseEvaluationFile> jsresBaseEvaluationFileList = new ArrayList<>();
        jsresBaseEvaluationFileList = JsResBaseEvaluationBiz.searchBaseEvaluationFileList(jsresBaseEvaluation.getRecordFlow());
        //根据评估信息流水号查找分数信息
        JsresBaseEvaluationScore jsresBaseEvaluationScore = new JsresBaseEvaluationScore();
        jsresBaseEvaluationScore.setEvaluationRecordFlow(jsresBaseEvaluation.getRecordFlow());
        List<JsresBaseEvaluationScore> jsresBaseEvaluationScoreList = new ArrayList<>();
        jsresBaseEvaluationScoreList = JsResBaseEvaluationBiz.searchBaseEvaluationScoreList(jsresBaseEvaluation.getRecordFlow());
        SysOrg org =orgBiz.readSysOrg(user.getOrgFlow());
        if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
            model.addAttribute("countryOrgFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
        }
        //获取是否付费
        SysUser currUser = GlobalContext.getCurrentUser();
        String cfgCode = "jsres_" + currUser.getOrgFlow() + "_guocheng";
        JsresPowerCfg cfg = jsResPowerCfgBiz.read(cfgCode);
        if (org.getOrgLevelId().equals(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId()))
        {
            model.addAttribute("isCountry", com.pinde.core.common.GlobalConstant.FLAG_Y);
        }
        model.addAttribute("sysCfg",cfg);
        model.addAttribute("orgName",jsresBaseEvaluation.getOrgName());
        model.addAttribute("orgFlow",jsresBaseEvaluation.getOrgFlow());
        model.addAttribute("jsresBaseEvaluation",jsresBaseEvaluation);
        model.addAttribute("jsresBaseEvaluationFileList",jsresBaseEvaluationFileList);
        model.addAttribute("jsresBaseEvaluationScoreList",jsresBaseEvaluationScoreList);
        return "jsres/evaluationInfo";
    }

    /**
     * 省厅、市局培训基地评估页面
     * @param model
     * @param type
     * @param currentPage
     * @param request
     * @return
     */
    @RequestMapping("/baseAssessInfo")
    public String baseAssessInfo(Model model,String type,Integer currentPage,HttpServletRequest request){
        SysOrg sysOrg=new SysOrg();
        SysUser currUser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(currUser.getOrgFlow());
        sysOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        sysOrg.setOrgProvId(org.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
            sysOrg.setOrgCityId(org.getOrgCityId());
        }
        sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        if(StringUtil.isNotBlank(type)){
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(type)) {
                sysOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
            } else if (com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId().equals(type)) {
                sysOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId());
            }
        }

        Map<String,Object> beMap=new HashMap<String,Object>();
        beMap.put("orgProvId",sysOrg.getOrgProvId());
        beMap.put("orgCityId",sysOrg.getOrgCityId());
        if (!type.equals(com.pinde.core.common.enums.OrgLevelEnum.GaugeTrainingBase.getId())) {
            beMap.put("orgLevelId",type);
        }
        List<SysOrg> orgs=orgBiz.searchOrg(sysOrg);
        List<String> orgFlows=new ArrayList<>();
        List<String> jointOrgFlows=new ArrayList<>();
        Map<String,String> orgCodeMap = new HashMap<>();
        if(orgs!=null&&orgs.size()>0){
            for(SysOrg so:orgs) {
                if(!orgFlows.contains(so.getOrgFlow())){
                    orgFlows.add(so.getOrgFlow());
                    orgCodeMap.put(so.getOrgFlow(),so.getOrgCode());
                }
                //查询本机构下所有协同基地
                List<ResJointOrg> jointOrgs =null;
                jointOrgs = jointOrgBiz.searchResJointByJointOrgFlow(so.getOrgFlow());
                if(jointOrgs!=null&&jointOrgs.size()>0){
                    for(ResJointOrg rjo:jointOrgs){
                        if(!jointOrgFlows.contains(rjo.getJointOrgFlow())){
                            jointOrgFlows.add(rjo.getJointOrgFlow());
                        }
                    }
                }
                //查询本机构是否为其他基地的协同基地
                List<ResJointOrg> jointOrgs2 =null;
                jointOrgs2 = jointOrgBiz.searchResJointByOrgFlow(so.getOrgFlow());

                if(jointOrgs2!=null&&jointOrgs2.size()>0){
                    for(ResJointOrg rjo:jointOrgs2){
                        if(!jointOrgFlows.contains(rjo.getJointOrgFlow())){
                            jointOrgFlows.add(rjo.getJointOrgFlow());
                        }
                    }
                }
            }
        }
        model.addAttribute("orgCodeMap",orgCodeMap);
        //协同基地
        if (type.equals(com.pinde.core.common.enums.OrgLevelEnum.GaugeTrainingBase.getId())) {
            beMap.put("orgFlows",jointOrgFlows);
        }else {
            //国家基地或省级基地
            beMap.put("orgFlows",orgFlows);
        }
        List<JsresBaseEvaluation> baseEvaluationList=null;
        if (type.equals(com.pinde.core.common.enums.OrgLevelEnum.GaugeTrainingBase.getId()) && (jointOrgFlows == null || jointOrgFlows.size() == 0))
        {
            baseEvaluationList=null;
        }else{
            PageHelper.startPage(currentPage,getPageSize(request));
            baseEvaluationList=JsResBaseEvaluationBiz.searchAllBaseEvaluation(beMap);
        }
        //获取是否付费
        String cfgCode = "jswjw_"+currUser.getOrgFlow()+"_P003";
        SysCfg sysCfg = new SysCfg();
        sysCfg = cfgBiz.read(cfgCode);
        model.addAttribute("sysCfg",sysCfg);
        model.addAttribute("baseEvaluationList",baseEvaluationList);
        model.addAttribute("type",type);
        model.addAttribute("page",currentPage);
        return "jsres/global/hospital/baseEvaluationList";
    }

    @RequestMapping(value = "/joinOrgEvaluationInfo")
    public String joinOrgEvaluationInfo(Model model,Integer currentPage,HttpServletRequest request){
        SysUser user = GlobalContext.getCurrentUser();
        SysOrg org=new SysOrg();
        org=orgBiz.readSysOrg(user.getOrgFlow());
        List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
        List<String> jointOrgFlowList = new ArrayList<>();
        for (ResJointOrg jointOrg:jointOrgs) {
            jointOrgFlowList.add(jointOrg.getJointOrgFlow());
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<JsresBaseEvaluation> baseEvaluationList= JsResBaseEvaluationBiz.searchJointBaseEvaluation(jointOrgFlowList);
        if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
            model.addAttribute("seeFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
        }
        model.addAttribute("baseEvaluationList",baseEvaluationList);
        return "jsres/global/hospital/baseEvaluationList";
    }

    @RequestMapping(value = "/saveEvaluation")
    public @ResponseBody String saveEvaluation(String itemId, String itemName, String score,String selfTotalled){
        SysUser user = GlobalContext.getCurrentUser();
        JsresBaseEvaluation jsresBaseEvaluation = new JsresBaseEvaluation();
        jsresBaseEvaluation.setOrgFlow(user.getOrgFlow());
        jsresBaseEvaluation = JsResBaseEvaluationBiz.searchBaseEvaluationList(jsresBaseEvaluation).get(0);
        jsresBaseEvaluation.setOwnerAllScore(selfTotalled);
        jsresBaseEvaluation.setSpeContent("");
        jsresBaseEvaluation.setOrgName(user.getOrgName());
        jsresBaseEvaluation.setCreateUserFlow(user.getUserFlow());
        JsresBaseEvaluationScore jsresBaseEvaluationScore = new JsresBaseEvaluationScore();
        jsresBaseEvaluationScore.setItemId(itemId);
        jsresBaseEvaluationScore.setEvaluationRecordFlow(jsresBaseEvaluation.getRecordFlow());
        jsresBaseEvaluationScore = JsResBaseEvaluationBiz.searchBaseEvaluationScoreByItemId(jsresBaseEvaluationScore);
        if(jsresBaseEvaluationScore==null){
            jsresBaseEvaluationScore = new JsresBaseEvaluationScore();
            jsresBaseEvaluationScore.setEvaluationRecordFlow(jsresBaseEvaluation.getRecordFlow());
            jsresBaseEvaluationScore.setItemId(itemId);
            jsresBaseEvaluationScore.setItemName(itemName);
            jsresBaseEvaluationScore.setPlanYear("2019");
            jsresBaseEvaluationScore.setOwnerScore(score);
            jsresBaseEvaluationScore.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            jsresBaseEvaluationScore.setCreateUserFlow(user.getUserFlow());
        }else{
            jsresBaseEvaluationScore.setItemId(itemId);
            jsresBaseEvaluationScore.setItemName(itemName);
            jsresBaseEvaluationScore.setOwnerScore(score);
            jsresBaseEvaluationScore.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        }
        JsResBaseEvaluationBiz.saveJsresBaseEvaluationScore(jsresBaseEvaluationScore);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
    }
    /**
     * 详情页展示评估信息
     * @param model
     * @param recordFlow
     * @return
     */
    @RequestMapping("/showBaseAssessInfo")
    public String showBaseAssessInfo(Model model,String recordFlow,String orgFlow,String type,String currentPage){
        SysUser user = GlobalContext.getCurrentUser();
        JsresBaseEvaluationFile jsresBaseEvaluationFile = new JsresBaseEvaluationFile();
        jsresBaseEvaluationFile.setEvaluationRecordFlow(recordFlow);
        List<JsresBaseEvaluationFile> jsresBaseEvaluationFileList = new ArrayList<>();
        List<JsresBaseEvaluationScore> jsresBaseEvaluationScoreList = new ArrayList<>();
        if (recordFlow !=null && recordFlow !=""){
            //根据评估表流水号查评估附件
            jsresBaseEvaluationFileList = JsResBaseEvaluationBiz.searchBaseEvaluationFileList(recordFlow);
            //根据评估表流水号查评估信息
            jsresBaseEvaluationScoreList = JsResBaseEvaluationBiz.searchBaseEvaluationScoreList(recordFlow);
        }
        //根据机构流水号查专家意见建议
        JsresBaseEvaluation jsresBaseEvaluation = new JsresBaseEvaluation();
        jsresBaseEvaluation.setOrgFlow(orgFlow);
        List<JsresBaseEvaluation> jsresBaseEvaluationList = new ArrayList<>();
        jsresBaseEvaluationList = JsResBaseEvaluationBiz.searchBaseEvaluationList(jsresBaseEvaluation);
        if(jsresBaseEvaluationList.size()>0){
            jsresBaseEvaluation = jsresBaseEvaluationList.get(0);
        }
        //获取是否付费
        SysUser currUser=GlobalContext.getCurrentUser();
        String cfgCode = "jswjw_"+currUser.getOrgFlow()+"_P003";
        SysCfg sysCfg = new SysCfg();
        sysCfg = cfgBiz.read(cfgCode);
        model.addAttribute("sysCfg",sysCfg);
        SysOrg org =orgBiz.readSysOrg(orgFlow);
        SysOrg jointOrg =orgBiz.readSysOrg(user.getOrgFlow());
        if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
            model.addAttribute("seeFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
        }
        model.addAttribute("orgName",org.getOrgName());
        model.addAttribute("jsresBaseEvaluation",jsresBaseEvaluation);
        model.addAttribute("jsresBaseEvaluationFileList",jsresBaseEvaluationFileList);
        model.addAttribute("jsresBaseEvaluationScoreList",jsresBaseEvaluationScoreList);
        model.addAttribute("recordFlow",recordFlow);
        model.addAttribute("orgFlow",orgFlow);
        model.addAttribute("type",type);
        model.addAttribute("page",currentPage);
        return "jsres/evaluationInfo";
    }

    /**
     * 保存专家评分，并计算总得分
     * @param model
     * @param recordFlow
     * @param itemId
     * @param speScore
     * @return
     */
    @RequestMapping("/saveExpertScore")
    @ResponseBody
    public String saveExpertScore(Model model,String recordFlow,String itemId,String itemName,String speScore,String orgFlow){
        SysUser user = GlobalContext.getCurrentUser();
        SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
        JsresBaseEvaluation jsresBaseEvaluation = new JsresBaseEvaluation();
        jsresBaseEvaluation.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        jsresBaseEvaluation.setOrgFlow(orgFlow);
        List<JsresBaseEvaluation> jsresBaseEvaluationList = JsResBaseEvaluationBiz.searchBaseEvaluationList(jsresBaseEvaluation);
        if(jsresBaseEvaluationList.size()>0){
            jsresBaseEvaluation = jsresBaseEvaluationList.get(0);
        }else {
            jsresBaseEvaluation.setModifyTime(DateUtil.getCurrentTime());
            jsresBaseEvaluation.setModifyUserFlow(user.getUserFlow());
            jsresBaseEvaluation.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            jsresBaseEvaluation.setOrgFlow(orgFlow);
            jsresBaseEvaluation.setOrgName(sysOrg.getOrgName());
            jsresBaseEvaluation.setPlanYear("2019");
            jsresBaseEvaluation.setTitle("江苏省住院医师规范化培训基地评估指标（2019年版）");
            JsResBaseEvaluationBiz.saveJsResBaseEvaluation(jsresBaseEvaluation);
            jsresBaseEvaluation = JsResBaseEvaluationBiz.searchBaseEvaluationList(jsresBaseEvaluation).get(0);
        }
//        if(recordFlow == "" || recordFlow == null){
//            jsresBaseEvaluation.setModifyTime(DateUtil.getCurrentTime());
//            jsresBaseEvaluation.setModifyUserFlow(user.getUserFlow());
//            jsresBaseEvaluation.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
//            jsresBaseEvaluation.setOrgFlow(orgFlow);
//            jsresBaseEvaluation.setOrgName(sysOrg.getOrgName());
//            jsresBaseEvaluation.setPlanYear("2019");
//            jsresBaseEvaluation.setTitle("江苏省住院医师规范化培训基地评估指标（2019年版）");
//            JsResBaseEvaluationBiz.saveJsResBaseEvaluation(jsresBaseEvaluation);
//            recordFlow = JsResBaseEvaluationBiz.searchBaseEvaluationList(jsresBaseEvaluation).get(0).getRecordFlow();
//        }
        JsresBaseEvaluationScore jsresBaseEvaluationScore = new JsresBaseEvaluationScore();
        jsresBaseEvaluationScore.setItemId(itemId);
        jsresBaseEvaluationScore.setEvaluationRecordFlow(jsresBaseEvaluation.getRecordFlow());
        jsresBaseEvaluationScore = JsResBaseEvaluationBiz.searchBaseEvaluationScoreByItemId(jsresBaseEvaluationScore);
        if(jsresBaseEvaluationScore==null){
            jsresBaseEvaluationScore = new JsresBaseEvaluationScore();
            jsresBaseEvaluationScore.setEvaluationRecordFlow(jsresBaseEvaluation.getRecordFlow());
            jsresBaseEvaluationScore.setItemId(itemId);
            jsresBaseEvaluationScore.setItemName(itemName);
            jsresBaseEvaluationScore.setPlanYear("2019");
            jsresBaseEvaluationScore.setSpeScore(speScore);
            jsresBaseEvaluationScore.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            jsresBaseEvaluationScore.setCreateUserFlow(user.getUserFlow());
            jsresBaseEvaluationScore.setCreateTime(DateUtil.getCurrentTime());
            jsresBaseEvaluationScore.setSpeUserFlow(user.getUserFlow());
            jsresBaseEvaluationScore.setSpeUserName(user.getUserName());
        }else{
            jsresBaseEvaluationScore.setItemId(itemId);
            jsresBaseEvaluationScore.setItemName(itemName);
            jsresBaseEvaluationScore.setSpeScore(speScore);
            jsresBaseEvaluationScore.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            jsresBaseEvaluationScore.setModifyUserFlow(user.getUserFlow());
            jsresBaseEvaluationScore.setModifyTime(DateUtil.getCurrentTime());
            jsresBaseEvaluationScore.setSpeUserFlow(user.getUserFlow());
            jsresBaseEvaluationScore.setSpeUserName(user.getUserName());
        }
        JsResBaseEvaluationBiz.saveJsresBaseEvaluationScore(jsresBaseEvaluationScore);
        //计算专家评分总和
        jsresBaseEvaluationScore = new JsresBaseEvaluationScore();
        jsresBaseEvaluationScore.setEvaluationRecordFlow(jsresBaseEvaluation.getRecordFlow());
        List<JsresBaseEvaluationScore> baseEvaluationScoreList=JsResBaseEvaluationBiz.searchBaseEvaluationScore(jsresBaseEvaluationScore);
        Double speScoreSum=0.0;
        if (baseEvaluationScoreList.size()>0){
            for (JsresBaseEvaluationScore baseScore:baseEvaluationScoreList) {
                Double speScore1=0.0;
                if (baseScore.getSpeScore()!=""&&baseScore.getSpeScore()!=null){
                    speScore1=Double.parseDouble(baseScore.getSpeScore());
                }
                speScoreSum += speScore1;
            }
        }
        model.addAttribute("speScoreSum",speScoreSum);
        //将总分保存到评分主表中
        String speAllScore = "";
        if (speScoreSum != 0.0){
            speAllScore = speScoreSum+"";
        }
        jsresBaseEvaluation.setOrgFlow(orgFlow);
        jsresBaseEvaluation.setPlanYear("2019");
        List<JsresBaseEvaluation> evaluationList=JsResBaseEvaluationBiz.searchBaseEvaluationList(jsresBaseEvaluation);
        String regs = "\\d+(\\.0)";
        if(speAllScore.matches(regs)){
            speAllScore = speAllScore.substring(0,speAllScore.indexOf("."));
        }
        if(evaluationList.size()>0){
            jsresBaseEvaluation=evaluationList.get(0);
            jsresBaseEvaluation.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            jsresBaseEvaluation.setOrgFlow(orgFlow);
            jsresBaseEvaluation.setOrgName(sysOrg.getOrgName());
            jsresBaseEvaluation.setPlanYear("2019");
            jsresBaseEvaluation.setSpeAllScore(speAllScore);
            jsresBaseEvaluation.setModifyTime(DateUtil.getCurrentTime());
            jsresBaseEvaluation.setModifyUserFlow(user.getUserFlow());
        }else{
            jsresBaseEvaluation = new JsresBaseEvaluation();
            jsresBaseEvaluation.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            jsresBaseEvaluation.setOrgFlow(orgFlow);
            jsresBaseEvaluation.setOrgName(sysOrg.getOrgName());
            jsresBaseEvaluation.setPlanYear("2019");
            jsresBaseEvaluation.setSpeAllScore(speAllScore);
            jsresBaseEvaluation.setCreateUserFlow(user.getUserFlow());
            jsresBaseEvaluation.setCreateTime(DateUtil.getCurrentTime());
            jsresBaseEvaluation.setTitle("江苏省住院医师规范化培训基地评估指标（2019年版）");
        }
        JsResBaseEvaluationBiz.saveJsResBaseEvaluation(jsresBaseEvaluation);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
    }

    @RequestMapping("/saveExpertScoreTotal")
    @ResponseBody
    public String saveExpertScoreTotal(Model model,String recordFlow,String planYear,String speUserFlow ){
        SysUser user = GlobalContext.getCurrentUser();
        speUserFlow = user.getUserFlow();
        planYear = DateUtil.getYear();
        JsresBaseEvaluationScore jsresBaseEvaluationScore = new JsresBaseEvaluationScore();
//        jsresBaseEvaluationScore.setSpeUserFlow(speUserFlow);
        jsresBaseEvaluationScore.setPlanYear(planYear);
        jsresBaseEvaluationScore.setEvaluationRecordFlow(recordFlow);
        List<JsresBaseEvaluationScore> baseEvaluationScoreList=JsResBaseEvaluationBiz.searchBaseEvaluationScore(jsresBaseEvaluationScore);
        Double speScoreSum=0.0;
        if (baseEvaluationScoreList.size()>0){
            for (JsresBaseEvaluationScore baseScore:baseEvaluationScoreList) {
                Double speScore=0.0;
                if (baseScore.getSpeScore()!=""&&baseScore.getSpeScore()!=null){
                    speScore=Double.parseDouble(baseScore.getSpeScore());
                }
                speScoreSum += speScore;
            }
        }
        model.addAttribute("speScoreSum",speScoreSum);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 保存上传评分附件
     * @param operType
     * @param uploadFile
     * @param model
     * @return
     */
    @RequestMapping(value="/checkUploadFile",method={RequestMethod.POST})
    public String checkUploadFile(String operType,String itemId, String itemName, MultipartFile uploadFile, Model model) throws UnsupportedEncodingException {
        SysUser user = GlobalContext.getCurrentUser();
        String resultPath = "";
        if(uploadFile!=null && !uploadFile.isEmpty()){
            String fileResult = JsResBaseEvaluationBiz.checkImg(uploadFile);
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(fileResult)) {
                model.addAttribute("fileErrorMsg", fileResult);
            }else{
                resultPath = JsResBaseEvaluationBiz.saveFileToDirs("", uploadFile, "evaluationFile",user.getOrgFlow(),"2019",itemId);
            }
            model.addAttribute("result", fileResult);
        }
        JsresBaseEvaluation jsresBaseEvaluation = new JsresBaseEvaluation();
        jsresBaseEvaluation.setOrgFlow(user.getOrgFlow());
        //根据机构流水号查找评估信息
        List<JsresBaseEvaluation> jsresBaseEvaluationList = JsResBaseEvaluationBiz.searchBaseEvaluationList(jsresBaseEvaluation);
        if(jsresBaseEvaluationList==null||jsresBaseEvaluationList.size()==0){
            jsresBaseEvaluation.setPlanYear("2019");
            jsresBaseEvaluation.setTitle("江苏省住院医师规范化培训基地评估指标（2019年版）");
            jsresBaseEvaluation.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            JsResBaseEvaluationBiz.saveJsResBaseEvaluation(jsresBaseEvaluation);
        }else{
            jsresBaseEvaluation = jsresBaseEvaluationList.get(0);
        }
        JsresBaseEvaluationFile jsresBaseEvaluationFile = new JsresBaseEvaluationFile();
        jsresBaseEvaluationFile.setEvaluationRecordFlow(jsresBaseEvaluation.getRecordFlow());
        jsresBaseEvaluationFile.setItemId(itemId);
//        String tempStr = new String(itemName.getBytes("ISO-8859-1"),"UTF-8");
        String tempStr = java.net.URLDecoder.decode(itemName,"UTF-8");
        jsresBaseEvaluationFile.setItemName(tempStr);
        jsresBaseEvaluationFile.setPlanYear("2019");
        jsresBaseEvaluationFile.setFileName(uploadFile.getOriginalFilename());
        jsresBaseEvaluationFile.setFileUrl(resultPath);
        jsresBaseEvaluationFile.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        jsresBaseEvaluationFile.setCreateUserFlow(user.getUserFlow());
        JsResBaseEvaluationBiz.saveJsresBaseEvaluationFile(jsresBaseEvaluationFile);
        model.addAttribute("file",jsresBaseEvaluationFile);
        return "jsres/hospital/hos/uploadFile";
    }
    /**
     * 跳转至上传扫描件
     * @return
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(Model model,String itemId,String itemName) throws UnsupportedEncodingException {
//        String tempStr = new String(itemName.getBytes("ISO-8859-1"),"UTF-8");
        String tempStr = java.net.URLDecoder.decode(itemName,"UTF-8");
        model.addAttribute("itemId",itemId);
        model.addAttribute("itemName",tempStr);
        return "jsres/hospital/hos/uploadFile";
    }

    /**
     * 下载评分附件
     * @param response
     * @param recordFlow
     * @throws Exception
     */
    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response, String recordFlow)throws Exception {
        JsresBaseEvaluationFile jsresBaseEvaluationFile = JsResBaseEvaluationBiz.searchBaseEvaluationFileByRecordFlow(recordFlow);
        String fileName = jsresBaseEvaluationFile.getFileName();
        String fileUrl=jsresBaseEvaluationFile.getFileUrl();
        String fileDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator +fileUrl ;
        File file = new File(fileDir);
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        FileInputStream is = new FileInputStream(file);
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        byte[] b = new byte[(int) file.length()];
        int n;
        while ((n = is.read(b)) != -1){
            os.write(b, 0, n);
        }
        os.flush();
        is.close();
        os.close();
    }

    /**
     * 显示Main.jsp
     * @param model
     * @return
     */
    @RequestMapping("/showMain")
    public String showMain(Model model){
        SysUser user = GlobalContext.getCurrentUser();
        SysOrg org =orgBiz.readSysOrg(user.getOrgFlow());
        if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
            model.addAttribute("countryOrgFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
        }
        return "/jsres/hospital/hos/evaluationMain";
    }

    /**
     * 删除文件
     * @param recordFlow
     * @return
     */
    @RequestMapping("/removeFile")
    public @ResponseBody String removeFile(String recordFlow) throws DocumentException{
        SysUser user = GlobalContext.getCurrentUser();
        JsresBaseEvaluationFile jsresBaseEvaluationFile = new JsresBaseEvaluationFile();
        jsresBaseEvaluationFile = JsResBaseEvaluationBiz.searchBaseEvaluationFileByRecordFlow(recordFlow);
        jsresBaseEvaluationFile.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
        JsResBaseEvaluationBiz.saveJsresBaseEvaluationFile(jsresBaseEvaluationFile);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
    }



    /**
     * 专家直接打分
     * @param orgFlow
     * @param speScore
     * @return
     */
    @RequestMapping("/insertAllScore")
    @ResponseBody
    public String insertAllScore(String orgFlow,String speScore){
        SysUser user = GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(orgFlow);
        JsresBaseEvaluation jsresBaseEvaluation = new JsresBaseEvaluation();
        jsresBaseEvaluation.setOrgFlow(orgFlow);
        jsresBaseEvaluation.setPlanYear("2019");
        List<JsresBaseEvaluation> evaluationList=JsResBaseEvaluationBiz.searchBaseEvaluationList(jsresBaseEvaluation);
        if(evaluationList.size()>0){
            jsresBaseEvaluation=evaluationList.get(0);
            JsresBaseEvaluationScore jsresBaseEvaluationScore = new JsresBaseEvaluationScore();
            List<JsresBaseEvaluationScore> scoreList = JsResBaseEvaluationBiz.searchBaseEvaluationScoreList(jsresBaseEvaluation.getRecordFlow());
            if(scoreList.size()>0 && jsresBaseEvaluation.getSpeAllScore() != null){
                return com.pinde.core.common.GlobalConstant.TOTAL_SCORE_IN_INFO;
            }else {
                jsresBaseEvaluation.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                jsresBaseEvaluation.setOrgFlow(orgFlow);
                jsresBaseEvaluation.setPlanYear("2019");
                jsresBaseEvaluation.setSpeAllScore(speScore);
                jsresBaseEvaluation.setModifyTime(DateUtil.getCurrentTime());
                jsresBaseEvaluation.setModifyUserFlow(user.getUserFlow());
            }
        }else{
            jsresBaseEvaluation = new JsresBaseEvaluation();
            jsresBaseEvaluation.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            jsresBaseEvaluation.setOrgFlow(orgFlow);
            jsresBaseEvaluation.setOrgName(org.getOrgName());
            jsresBaseEvaluation.setPlanYear("2019");
            jsresBaseEvaluation.setSpeAllScore(speScore);
            jsresBaseEvaluation.setCreateUserFlow(user.getUserFlow());
            jsresBaseEvaluation.setCreateTime(DateUtil.getCurrentTime());
            jsresBaseEvaluation.setTitle("江苏省住院医师规范化培训基地评估指标（2019年版）");
        }
        JsResBaseEvaluationBiz.saveJsResBaseEvaluation(jsresBaseEvaluation);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 保存专家意见建议
     * @param orgFlow
     * @param speContent
     * @return
     */
    @RequestMapping("/saveSpeContent")
    @ResponseBody
    public String saveSpeContent(String orgFlow,String speContent)throws Exception{
//        speContent = new String(speContent.getBytes("ISO-8859-1"),"UTF-8");
        speContent = java.net.URLDecoder.decode(speContent,"UTF-8");
        SysUser user = GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(orgFlow);
        JsresBaseEvaluation jsresBaseEvaluation = new JsresBaseEvaluation();
        jsresBaseEvaluation.setOrgFlow(orgFlow);
        List<JsresBaseEvaluation> jsresBaseEvaluationList= JsResBaseEvaluationBiz.searchBaseEvaluationList(jsresBaseEvaluation);
        if (jsresBaseEvaluationList.size()>0){
            jsresBaseEvaluation=jsresBaseEvaluationList.get(0);
            jsresBaseEvaluation.setModifyTime(DateUtil.getCurrentTime());
            jsresBaseEvaluation.setModifyUserFlow(user.getUserFlow());
            jsresBaseEvaluation.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            jsresBaseEvaluation.setSpeContent(speContent);
        }else{
            jsresBaseEvaluation.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            jsresBaseEvaluation.setOrgFlow(orgFlow);
            jsresBaseEvaluation.setOrgName(org.getOrgName());
            jsresBaseEvaluation.setPlanYear("2019");
            jsresBaseEvaluation.setSpeContent(speContent);
            jsresBaseEvaluation.setCreateUserFlow(user.getUserFlow());
            jsresBaseEvaluation.setCreateTime(DateUtil.getCurrentTime());
            jsresBaseEvaluation.setTitle("江苏省住院医师规范化培训基地评估指标（2019年版）");
        }
        JsResBaseEvaluationBiz.saveJsResBaseEvaluation(jsresBaseEvaluation);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
    }

}