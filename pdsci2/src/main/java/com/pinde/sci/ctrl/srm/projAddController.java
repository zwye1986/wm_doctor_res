package com.pinde.sci.ctrl.srm;


import com.alibaba.fastjson.JSON;
import com.pinde.core.jspform.Page;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.util.JspFormUtil;
import com.pinde.sci.enums.pub.ProjCategroyEnum;
import com.pinde.sci.enums.srm.*;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/srm/proj/add")
public class projAddController extends GeneralController {
    @Autowired
    private IProjRecBiz projRecBiz;
    @Autowired
    private IProjSearchBiz projSeeBiz;
    @Autowired
    private IProjAddBiz projAddBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IAchScoreBiz scoreBiz;
    @Autowired
    private IProjApproveBiz projApproveBiz;
    @Autowired
    private IFundInfoBiz fundInfoBiz;
    @Autowired
    private IProjPageBiz projPageBiz;
    @Autowired
    private IProjMineBiz projMineBiz;
    @Autowired
    private IUserBiz iUserBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IPubProjBiz projBiz;
    @Autowired
    private IProjProcessBiz projProcessBiz;
    @Autowired
    private IFileBiz fileBiz;
    @RequestMapping(value = "/list/{projListScope}/{projCateScope}")
    public String projList2(@PathVariable String projListScope, @PathVariable String projCateScope,
                            PubProj proj,String startYear,String endYear,String defaultTerm,
                            String flag, Integer currentPage, HttpServletRequest request, Model model) {
        setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
        setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, projCateScope);

        SysUser currUser = GlobalContext.getCurrentUser();
        List<PubProj> projList = new ArrayList<PubProj>();
        //手动设置阶段 此时阶段是申报阶段
        //手动设置项目类型
        proj.setProjCategoryId(projCateScope);
        if (GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(projListScope)) {
            proj.setApplyOrgFlow(currUser.getOrgFlow());
        } else if (GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(projListScope)) {
            proj.setChargeOrgFlow(currUser.getOrgFlow());
            List<SysOrg> orgList = this.orgBiz.searchOrgListByChargeOrgFlow(currUser.getOrgFlow());
            model.addAttribute("orgList", orgList);
        } else if (GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(projListScope)) {
            if (StringUtil.isNotBlank(proj.getApplyOrgFlow())) {
                //加载所有同级单位
                List<SysOrg> orgList = this.orgBiz.searchOrgListByChargeOrgFlow(proj.getChargeOrgFlow());
                model.addAttribute("orgList", orgList);
            } else if (StringUtil.isNotBlank(proj.getChargeOrgFlow())) {
                List<SysOrg> orgList = this.orgBiz.searchOrgListByChargeOrgFlow(proj.getChargeOrgFlow());
                model.addAttribute("orgList", orgList);
            }

            List<SysOrg> chargeOrgList = this.orgBiz.searchChargeOrg();
            model.addAttribute("chargeOrgList", chargeOrgList);
        }
        if(StringUtil.isNotBlank(defaultTerm)){//默认条件
            proj.setProjYear(DateUtil.getYear());
            proj.setProjTypeId("jsszyy.ywxm");
            model.addAttribute("defaultTerm",defaultTerm);
        }
        if (StringUtil.isNotBlank(flag)) {
            PageHelper.startPage(currentPage, getPageSize(request));
            projList = projSeeBiz.searchPassApplyProj(proj,startYear,endYear);
            model.addAttribute("projList", projList);
        }
        return "srm/proj/add/list_" + projCateScope;
    }


    /**
     * 添加新的立项
     *
     * @return
     */
    @RequestMapping(value = "/addProjInfo")
    public String addProjInfo(Model model) {
        String projCateScope = (String) getSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE);
        SysUser currUser = GlobalContext.getCurrentUser();
        List<SysDept> depts = deptBiz.searchDeptByOrg(currUser.getOrgFlow());
        model.addAttribute("depts", depts);
        return "srm/proj/add/addInfo_" + projCateScope;
    }


    /**
     * 保存立项信息
     */
    @RequestMapping(value = "/saveProjInfo/{projCategory}")
    public String saveProjInfo(PubProj pubProj, @PathVariable(value = "projCategory") String projCategory, HttpServletRequest request) {
        pubProj.setProjCategoryId(projCategory);
        pubProj.setProjCategoryName(ProjCategroyEnum.getNameById(projCategory));
        if (ProjCategroyEnum.Ky.getId().equals(projCategory)) {
            pubProj.setProjTypeName(DictTypeEnum.ProjType.getDictNameById(pubProj.getProjTypeId()));
        }

        String applyUserFlow = pubProj.getApplyUserFlow();
        SysUser sysUser = iUserBiz.readSysUser(applyUserFlow);
        if (sysUser != null) {
            String applyUserName = sysUser.getUserName();
            pubProj.setApplyUserName(applyUserName);
        }

        String applyDeptFlow = pubProj.getApplyDeptFlow();
        SysDept sysDept = deptBiz.readSysDept(applyDeptFlow);
        if (sysDept != null) {
            String applyDeptName = sysDept.getDeptName();
            pubProj.setApplyDeptName(applyDeptName);
        }

        //保存大字段
        String projTypeId = "gl.info";
        Map<String, String[]> datasMap = JspFormUtil.getParameterMap(request);
        String pageName = datasMap.get("pageName")[0];
        Page page = this.projPageBiz.getPage(ProjRecTypeEnum.Info.getId(), projTypeId, pageName);
        String projInfo = JspFormUtil.updateXmlStr("", page, datasMap);
        pubProj.setProjInfo(projInfo);

        this.projMineBiz.addFormedPubProj(pubProj);

        return "redirect:/srm/proj/add/addProjInfo";
    }

    /**
     * 项目管理--添加立项
     *
     * @param proj
     * @param pageName
     * @param request
     * @param scoreFlow
     * @param fundInfo
     * @return
     */
    @RequestMapping("/addGlSetUp")
    @ResponseBody
    public String saveSetUp(PubProj proj, HttpServletRequest request, String jsondata, SrmProjFundInfo fundInfo) {
        String projCateScope = StringUtil.defaultString((String) getSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE));
        List<PubProjAuthor> authorList = JSON.parseArray(jsondata, PubProjAuthor.class);
        //检查立项编号是否唯一
        List<PubProj> projList = projApproveBiz.getPubProjByProjNo(proj.getProjNo());
        if (projList != null && !projList.isEmpty()) {
            return GlobalConstant.FLAG_N;
        }
        proj.setProjTypeName("院外项目");
        proj.setProjTypeId("jsszyy.ywxm");
        proj.setProjCategoryId(projCateScope);
        proj.setProjCategoryName(ProjCategroyEnum.getNameById(projCateScope));
        proj.setProjStageId(ProjStageEnum.Contract.getId());
        proj.setProjStageName(ProjStageEnum.Contract.getName());
        proj.setProjStatusId(ProjContractStatusEnum.Apply.getId());
        proj.setProjStatusName(ProjContractStatusEnum.Apply.getName());
        String applyUserFlow = proj.getApplyUserFlow();
        SysUser sysUser = iUserBiz.readSysUser(applyUserFlow);
        if (sysUser != null) {
            String applyUserName = sysUser.getUserName();
            proj.setApplyUserName(applyUserName);
            proj.setApplyOrgFlow(sysUser.getOrgFlow());
            proj.setApplyOrgName(sysUser.getOrgName());
        }
        if (StringUtil.isNotBlank(proj.getPlanTypeId())) {
            proj.setPlanTypeName(DictTypeEnum.getDictName(DictTypeEnum.PlanCategory, proj.getPlanTypeId()));
        }
        String applyDeptFlow = proj.getApplyDeptFlow();
        SysDept sysDept = deptBiz.readSysDept(applyDeptFlow);
        if (sysDept != null) {
            String applyDeptName = sysDept.getDeptName();
            proj.setApplyDeptName(applyDeptName);
        }

        String remark = ProcessRemarkEnum.ApproveAgree.getName();
        String sug = (String) request.getParameter("sug");
        PubProjProcess process = new PubProjProcess();
        process.setProjStageId(proj.getProjStageId());
        process.setProjStageName(proj.getProjStageName());
        process.setProjStatusId(proj.getProjStatusId());
        process.setProjStatusName(proj.getProjStatusName());
        process.setProcessRemark(remark);
        process.setAuditContent(sug);
        process.setOperOrgFlow(sysUser.getOrgFlow());
        process.setOperOrgName(sysUser.getOrgName());
        process.setOperUserFlow(proj.getApplyUserFlow());
        process.setOperUserName(proj.getApplyUserName());

		/*添加经费信息*/
        fundInfo.setProjFlow(proj.getProjFlow());
        fundInfo.setBudgetStatusId(AchStatusEnum.Apply.getId());
        fundInfo.setBudgetStatusName(AchStatusEnum.Apply.getName());

        int r = this.projApproveBiz.addSetUp(proj, authorList, fundInfo, process);
        if (r == 1) {
            return GlobalConstant.FLAG_Y;
        }
        return GlobalConstant.FLAG_N;
    }

    /**
     * 江苏省中管理员添加表单信息（院外项目，只需要上传附件）
     * （合同、进展报告、变更申请、中止报告、验收报告）
     *
     * @param recTypeId
     * @param projFlow
     * @return
     */
    @RequestMapping(value = "/addForm")
    public String addForm(String projFlow, String recTypeId, Model model) {
        if (StringUtil.isNotBlank(projFlow) && StringUtil.isNotBlank(recTypeId)) {
            String recTypeName = ProjRecTypeEnum.getNameById(recTypeId);
            if (StringUtil.isNotBlank(recTypeName)) {
                model.addAttribute("recTypeName", recTypeName);
                model.addAttribute("recTypeId", recTypeId);
                model.addAttribute("projFlow", projFlow);
                return "srm/proj/add/addFormFile";
            }
        }
        return "";
    }

    @RequestMapping(value = "/saveFormFile")
    @ResponseBody
    public String saveFormFile(String projFlow, String recTypeId, Model model, HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList = multipartRequest.getFiles("file");
        int result = 0;
        if (StringUtil.isNotBlank(projFlow) && StringUtil.isNotBlank(recTypeId)) {
            result = projAddBiz.addProjForm(fileList,projFlow,recTypeId);
        }
        if(GlobalConstant.ONE_LINE == result){
            return GlobalConstant.OPRE_SUCCESSED;
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 根据部门查询人员列表
     */
    @RequestMapping(value = "/getUsersByDept")
    @ResponseBody
    public List<SysUser> getUsersByDept(String deptFlow) {
        return iUserBiz.searcherUserByDeptFlow(deptFlow);
    }

    /**
     * 重点专科列表
     *
     * @return
     */
    @RequestMapping(value = "/projContractList/{projCateScope}")
    public String projContractList(PubProj proj, @PathVariable String projCateScope, Integer currentPage, HttpServletRequest request, Model model) {
        setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, projCateScope);
        SysUser currUser = GlobalContext.getCurrentUser();
        PageHelper.startPage(currentPage, getPageSize(request));
        proj.setApplyUserFlow(currUser.getUserFlow());
        List<PubProj> projList = this.projMineBiz.searchProjList(proj);
        model.addAttribute("projList", projList);
        return "srm/proj/add/addContractList";
    }

    /**
     * 编辑项目基本信息
     *
     * @return
     */
    @RequestMapping(value = "/addContract")
    public String addContract(String projFlow, Model model) {
        return "srm/proj/add/addContract";
    }

    /**
     * 保存项目基本信息
     *
     * @return
     */
    @RequestMapping(value = "/saveZKProjInfo")
    public String saveZKProjInfo(PubProj proj, Model model, HttpServletRequest request) {
        SysUser currUser = GlobalContext.getCurrentUser();
        proj.setProjTypeName(DictTypeEnum.ProfeType.getDictNameById(proj.getProjTypeId()));
        if (StringUtil.isBlank(proj.getProjFlow())) {
            proj.setProjCategoryId(ProjCategroyEnum.Zk.getId());
            proj.setProjCategoryName(ProjCategroyEnum.Zk.getName());
            //设置项目阶段为合同阶段
            proj.setProjStageId(ProjStageEnum.Contract.getId());
            proj.setProjStageName(ProjStageEnum.Contract.getName());
            //合同填写
            proj.setProjStatusId(ProjContractStatusEnum.Apply.getId());
            proj.setProjStatusName(ProjContractStatusEnum.Apply.getName());

            projMineBiz.addProjInfo(proj);
        } else {
            projBiz.modProject(proj);
        }
        return "redirect:/srm/proj/add/projContractList/zk";
    }
    @RequestMapping("/editRecFiles")
    public String editRecFiles(String projFlow,Model model){
        if(StringUtil.isNotBlank(projFlow)){
            PubProj proj = projBiz.readProject(projFlow);
            model.addAttribute("proj",proj);
            PubProjRec projRec = new PubProjRec();
            projRec.setProjFlow(projFlow);

            List<PubProjRec> recs = this.projRecBiz.searchProjRecWithBlobs(projRec);

            Map<String,Map<String , PubFile>> recFileMap = new HashMap<>();

            //申报书信息（一般只会有一个）
            PubProjRec applyRec = null;
            //合同信息（一般只会有一个）
            PubProjRec contractRec = null;
            //验收申请信息（一般只会有一个）
            PubProjRec completeRec = null;
            //中止申请信息（一般只会有一个）
            PubProjRec terminateRec = null ;
            //进展报告信息（允许有多个）
            List<PubProjRec> scheduleRecList = new ArrayList<>();
            //变更、延期 信息（允许有多个）
            List<PubProjRec> changeRecList = new ArrayList<>();
            for(PubProjRec pubProjRec : recs){
                if(ProjRecTypeEnum.Apply.getId().equals(pubProjRec.getRecTypeId())){
                    String content = pubProjRec.getRecContent();
                    if(StringUtil.isNotBlank(content)){
                        Map<String , Object> resultMap = JspFormUtil.parseXmlStr(content);
                        //获取附件列表
                       // List<String> fileFlows = this.projBiz.getFileFlows(resultMap);
                        Map<String , PubFile> pageFileMap = this.projBiz.getFile(resultMap);
                        recFileMap.put(pubProjRec.getRecFlow(),pageFileMap);
                    }
                    applyRec= pubProjRec;
                }else if(ProjRecTypeEnum.Contract.getId().equals(pubProjRec.getRecTypeId())){
                    String content = pubProjRec.getRecContent();
                    if(StringUtil.isNotBlank(content)){
                        Map<String , Object> resultMap = JspFormUtil.parseXmlStr(content);
                        //获取附件列表
                        //List<String> fileFlows = this.projBiz.getFileFlows(resultMap);
                        Map<String , PubFile> pageFileMap = this.projBiz.getFile(resultMap);
                        recFileMap.put(pubProjRec.getRecFlow(),pageFileMap);
                    }
                    contractRec = pubProjRec;
                }else if(ProjRecTypeEnum.ScheduleReport.getId().equals(pubProjRec.getRecTypeId())){
                    String content = pubProjRec.getRecContent();
                    if(StringUtil.isNotBlank(content)){
                        Map<String , Object> resultMap = JspFormUtil.parseXmlStr(content);
                        //获取附件列表
                      //  List<String> fileFlows = this.projBiz.getFileFlows(resultMap);
                        Map<String , PubFile> pageFileMap = this.projBiz.getFile(resultMap);
                        recFileMap.put(pubProjRec.getRecFlow(),pageFileMap);
                    }
                    scheduleRecList.add(pubProjRec);
                }else if(ProjRecTypeEnum.ChangeReport.getId().equals(pubProjRec.getRecTypeId())){
                    String content = pubProjRec.getRecContent();
                    if(StringUtil.isNotBlank(content)){
                        Map<String , Object> resultMap = JspFormUtil.parseXmlStr(content);
                        //获取附件列表
                      //  List<String> fileFlows = this.projBiz.getFileFlows(resultMap);
                        Map<String , PubFile> pageFileMap = this.projBiz.getFile(resultMap);
                        recFileMap.put(pubProjRec.getRecFlow(),pageFileMap);
                    }
                    changeRecList.add(pubProjRec);
                }else if(ProjRecTypeEnum.CompleteReport.getId().equals(pubProjRec.getRecTypeId())){
                    String content = pubProjRec.getRecContent();
                    if(StringUtil.isNotBlank(content)){
                        Map<String , Object> resultMap = JspFormUtil.parseXmlStr(content);
                        //获取附件列表
                       // List<String> fileFlows = this.projBiz.getFileFlows(resultMap);
                        Map<String , PubFile> pageFileMap = this.projBiz.getFile(resultMap);
                        recFileMap.put(pubProjRec.getRecFlow(),pageFileMap);
                    }
                    completeRec = pubProjRec;
                }else if(ProjRecTypeEnum.TerminateReport.getId().equals(pubProjRec.getRecTypeId())){
                    String content = pubProjRec.getRecContent();
                    if(StringUtil.isNotBlank(content)){
                        Map<String , Object> resultMap = JspFormUtil.parseXmlStr(content);
                        //获取附件列表
                        //List<String> fileFlows = this.projBiz.getFileFlows(resultMap);
                        Map<String , PubFile> pageFileMap = this.projBiz.getFile(resultMap);
                        recFileMap.put(pubProjRec.getRecFlow(),pageFileMap);
                    }
                    terminateRec = pubProjRec;
                }
            }

            model.addAttribute("recFileMap", recFileMap );
            model.addAttribute("applyRec", applyRec );
            model.addAttribute("contractRec", contractRec );
            model.addAttribute("scheduleRecList", scheduleRecList );
            model.addAttribute("changeRecList", changeRecList );
            model.addAttribute("completeRec", completeRec );
            model.addAttribute("terminateRec", terminateRec );

        }
        return "/srm/proj/add/editRecFiles";
    }


    /**
     *
     * @return
     */
    @RequestMapping("/addFiles")
    public String addFiles(String recFlow,Model model){
        if(StringUtil.isNotBlank(recFlow)){
            PubProjRec pubProjRec = this.projRecBiz.readProjRec(recFlow);
            PubProj proj = projBiz.readProject(pubProjRec.getProjFlow());
            model.addAttribute("pubProjRec",pubProjRec);
            model.addAttribute("proj",proj);
        }
        return "/srm/proj/add/addFiles";
    }
}
