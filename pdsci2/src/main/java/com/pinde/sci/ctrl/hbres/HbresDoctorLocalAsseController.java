package com.pinde.sci.ctrl.hbres;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbres.IHbResGraduationApplyBiz;
import com.pinde.sci.biz.jsres.IJsResBaseBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.jsres.JsResAuditStatusEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.model.mo.JsresGraduationAttachment;
import com.pinde.sci.model.mo.ResJointOrg;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysUser;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hbres/asseLocal")
public class HbresDoctorLocalAsseController extends GeneralController {
    @Autowired
    private IJsResDoctorBiz jsResDoctorBiz;
    @Autowired
    private IJsResBaseBiz baseBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private OrgBizImpl orgBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @Autowired
    private IHbResGraduationApplyBiz graduationApplyBiz;
    @Autowired
    private IResDoctorRecruitBiz resDoctorRecruitBiz;
    public static final String SCOREYEAR_NOT_FIND="请选择成绩年份";

    public List<String> searchJointOrgList(String flag,String Flow) {
        List<String> jointOrgFlowList=new ArrayList<String>();
        if(GlobalConstant.FLAG_Y.equals(flag)){
            List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(Flow);
            if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                for(ResJointOrg jointOrg:resJointOrgList){
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
        }
        return jointOrgFlowList;
    }
    /**
     * 医院角色结业考核头部标签页
     * @return
     */
    @RequestMapping(value="/asseGraduationForHos")
    public String asseGraduationForHos(Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        //判断是否是协同基地
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currentUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            model.addAttribute("roleFlag", "jointOrg");
            return "hbres/asse/hospital/auditMainForJoint";
        }
        return "hbres/asse/hospital/main";
    }

    /**
     * 待审核筛选条件页
     * @return
     */
    @RequestMapping(value="/waitAuditMain")
    public String waitAuditMain(Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        model.addAttribute("org",currentOrg);
        List<SysOrg>  orgs = new ArrayList<>();
        if(OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId()))
        {
            List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
            orgs.addAll(joinOrgs);
        }
        orgs.add(currentOrg);
        model.addAttribute("orgs", orgs);
        return "hbres/asse/hospital/waitAuditMain";
    }

    /**
     * 待审核列表页
     * @param model
     * @param roleFlag
     * @param currentPage
     * @param request
     * @param orgFlow
     * @param trainingTypeId
     * @param trainingSpeId
     * @param datas
     * @param sessionNumber
     * @param graduationYear
     * @param qualificationMaterialId
     * @param passFlag
     * @param userName
     * @param idNo
     * @param completeBi
     * @param auditBi
     * @return
     */
    @RequestMapping(value="/waitAuditList")
    public String waitAuditList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,
                                String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],
                                String sessionNumber,String graduationYear,String qualificationMaterialId,String passFlag,
                                String userName,String idNo,String completeBi,String auditBi,String applyYear
    ){
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String> orgFlowList = new ArrayList();
        if (StringUtil.isNotBlank(orgFlow)) {
            orgFlowList.add(orgFlow);
        }else {
            if(OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId())) {
                List<SysOrg> joinOrgs = orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
                if (joinOrgs != null && joinOrgs.size() > 0) {
                    for (SysOrg tempOrg : joinOrgs) {
                        orgFlowList.add(tempOrg.getOrgFlow());
                    }
                }
            }
            orgFlowList.add(currentOrg.getOrgFlow());
        }
        param.put("orgFlowList",orgFlowList);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("auditStatusId", JsResAuditStatusEnum.Auditing.getId());
        param.put("applyYear",applyYear);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list = graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        String nowTime = DateUtil.transDateTime(DateUtil.getCurrentTime());
        String startDate = InitConfig.getSysCfg("local_submit_start_time");
        String endDate = InitConfig.getSysCfg("local_submit_end_time");
        String f="N";
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
            if(startDate.compareTo(nowTime) <= 0 && endDate.compareTo(nowTime)>=0)
            {
                f="Y";
            }
        }
        model.addAttribute("f",f);
        model.addAttribute("currentUser",currentUser);
        return "hbres/asse/hospital/waitAuditList";
    }
    @RequestMapping(value="/exportList")
    public void exportList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,HttpServletResponse response,
                           String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],
                           String sessionNumber,String graduationYear,String qualificationMaterialId,String passFlag,
                           String userName,String idNo,String completeBi,String auditBi,String auditStatusId,String isWaitAudit,String applyYear
    ) throws IOException {
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String> orgFlowList = new ArrayList();
        if (StringUtil.isNotBlank(orgFlow)) {
            orgFlowList.add(orgFlow);
        }else {
            if(OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId())) {
                List<SysOrg> joinOrgs = orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
                if (joinOrgs != null && joinOrgs.size() > 0) {
                    for (SysOrg tempOrg : joinOrgs) {
                        orgFlowList.add(tempOrg.getOrgFlow());
                    }
                }
            }
            orgFlowList.add(currentOrg.getOrgFlow());
        }
        param.put("orgFlowList",orgFlowList);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("auditStatusId", auditStatusId);
        param.put("applyYear", applyYear);
        List<Map<String,Object>> list = graduationApplyBiz.chargeQueryListForExport(param);
        graduationApplyBiz.chargeExportList(list,response,isWaitAudit);

    }

    /**
     * 情况总览筛选条件页
     * @return
     */
    @RequestMapping(value="/auditMain")
    public String auditMain(Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        model.addAttribute("org",currentOrg);
        List<SysOrg>  orgs = new ArrayList<>();
        if(OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId()))
        {
            List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
            orgs.addAll(joinOrgs);
        }
        orgs.add(currentOrg);
        model.addAttribute("orgs", orgs);
        return "hbres/asse/hospital/auditMain";
    }

    /**
     * 情况总览列表页
     * @return
     */
    @RequestMapping(value="/auditList")
    public String auditList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,
                            String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],
                            String sessionNumber,String graduationYear,String qualificationMaterialId,String passFlag,
                            String userName,String idNo,String completeBi,String auditBi,String auditStatusId,String applyYear
    ){
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String> orgFlowList = new ArrayList();
        if (StringUtil.isNotBlank(orgFlow)) {
            orgFlowList.add(orgFlow);
        }else {
            if(OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId())) {
                List<SysOrg> joinOrgs = orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
                if (joinOrgs != null && joinOrgs.size() > 0) {
                    for (SysOrg tempOrg : joinOrgs) {
                        orgFlowList.add(tempOrg.getOrgFlow());
                    }
                }
            }
            orgFlowList.add(currentOrg.getOrgFlow());
        }
        param.put("orgFlowList",orgFlowList);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("auditStatusId",auditStatusId);
        param.put("applyYear",applyYear);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        if(StringUtil.isNotBlank(roleFlag)){
            model.addAttribute("roleFlag",roleFlag);
        }
        model.addAttribute("list",list);
        String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
        String startDate=InitConfig.getSysCfg("local_submit_start_time");
        String endDate=InitConfig.getSysCfg("local_submit_end_time");
        String f="N";
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
            if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
            {
                f="Y";
            }
        }
        model.addAttribute("f",f);
        model.addAttribute("currentUser",currentUser);
        return "hbres/asse/hospital/auditList";
    }

    //上传附件页面
    @RequestMapping("/attachmentUpload")
    public String attachmentUpload(Model model){
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow = user.getOrgFlow();
        String year = DateUtil.getYear();
        JsresGraduationAttachment search = new JsresGraduationAttachment();
        search.setOrgFlow(orgFlow);
        search.setGraduationYear(year);
        List<JsresGraduationAttachment> attachmentList = graduationApplyBiz.searchAttachments(search);
        model.addAttribute("attachmentList",attachmentList);
        return "hbres/asse/hospital/attachmentUpload";
    }

    @RequestMapping(value="/imgUpload")
    @ResponseBody
    public Map<String,String> imgUpload(MultipartFile checkFile){
        Map<String, String> map = null;
        if(checkFile!=null && checkFile.getSize() > 0){
            map=graduationApplyBiz.graduationImgUpload(checkFile);
        }
        return map;
    }

    /**
     * 删除文件
     */
    @RequestMapping(value="/imgDelete")
    @ResponseBody
    public String imgDelete(String recordFlow) throws DocumentException{
        JsresGraduationAttachment save = new JsresGraduationAttachment();
        save.setRecordFlow(recordFlow);
        save.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        graduationApplyBiz.editAttachment(save);
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
}
