package com.pinde.sci.ctrl.res;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IFundsBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 经费管理 基地拨付详情-综合费用管理
 */
@Controller
@RequestMapping("/res/resFunds")
public class ResFundsContorller extends GeneralController {

    @Autowired
    private ICfgBiz jswjwBiz;

    @Autowired
    private IFundsBiz iFundsBiz;

    @Autowired
    private OrgBizImpl orgBiz;
    /**
     * 查询基地拨付详情
     * @param model
     * @return
     */
    @RequestMapping(value="queryBaseAllocationDetails"  )
    public String queryBaseAllocationDetails(Model model, ResBaseFund resBaseFund, HttpServletRequest request, Integer currentPage,
                                             String startDate, String endDate, String role){
        PageHelper.startPage(currentPage, getPageSize(request));
        if("hospital".equals(role)){
            SysUser sysuser = GlobalContext.getCurrentUser();
            resBaseFund.setOrgFlow(sysuser.getOrgFlow());
        }
        List<ResBaseFund> resBaseFunds = iFundsBiz.searchResBaseFund(startDate, endDate, resBaseFund);
        if(resBaseFunds != null && resBaseFunds.size()>0){
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("startDate",startDate);
            paramMap.put("endDate",endDate);
            paramMap.put("orgName",resBaseFund.getOrgName() == null ? "" : resBaseFund.getOrgName());
            List<ResBaseFundDetail> resBaseFundBeans = iFundsBiz.resBaseFundList(paramMap);
            HashMap<String, Object> detailMap = new HashMap<>();
            if(resBaseFundBeans != null && resBaseFundBeans.size()>0){
                for (ResBaseFundDetail resBaseFundDetail: resBaseFundBeans ) {
                    String projectOfBasefundsId = resBaseFundDetail.getProjectOfBasefundsId() + resBaseFundDetail.getMainFlow();
                    String amountOfMoney = resBaseFundDetail.getAmountOfMoney();
                    detailMap.put(projectOfBasefundsId,amountOfMoney);
                }
            }
            model.addAttribute("detailMap", detailMap);
        }
        model.addAttribute("teacherTrainingList", resBaseFunds);
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        model.addAttribute("org",currentOrg);
        List<SysOrg>  orgs = new ArrayList<>();

        SysOrg sysorg = new SysOrg();
        sysorg.setOrgProvId(currentOrg.getOrgProvId());
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysOrg>  joinOrgs = orgBiz.searchOrg(sysorg);

        orgs.addAll(joinOrgs);
        orgs.add(currentOrg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("role", role);
        // 查询是否需要省厅审核
        SysCfg fundingRudit = jswjwBiz.read("funding_Report_audit");
        if(GlobalConstant.FLAG_Y.equals(fundingRudit.getCfgValue())){
            model.addAttribute("fundingRudit","Y");
        } else {
            model.addAttribute("fundingRudit","N");
        }
        return "/res/Funding/baseAllocationDetailsList";
    }


    @RequestMapping(value="queryBaseAllocationDetailsById"  )
    public String addBaseAllocationDetails(Model model, String recordFlow, String auditBase, String role){
        if(StringUtil.isNotBlank(recordFlow)){
            // 基地收支
            ResBaseFund resBaseFunds = iFundsBiz.readResBaseFund(recordFlow);
            // 基地收支详情
            if(resBaseFunds != null){
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("recordFlow",recordFlow);
                List<ResBaseFundDetail> resBaseFundBeans = iFundsBiz.resBaseFundList(paramMap);
                HashMap<String, Object> detailMap = new HashMap<>();
                if(resBaseFundBeans != null && resBaseFundBeans.size()>0){
                    for (ResBaseFundDetail resBaseFundDetail: resBaseFundBeans ) {
                        String projectOfBasefundsId = resBaseFundDetail.getProjectOfBasefundsId();
                        String amountOfMoney = resBaseFundDetail.getAmountOfMoney();
                        detailMap.put(projectOfBasefundsId,amountOfMoney);
                    }
                }
                model.addAttribute("detailMap", detailMap);
            }
            model.addAttribute("resBaseFunds",resBaseFunds);
        }
        // 基地信息
        SysOrg currentOrg = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("org",currentOrg);
        List<SysOrg>  orgs = new ArrayList<>();
        SysOrg sysorg = new SysOrg();
        sysorg.setOrgProvId(currentOrg.getOrgProvId());
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysOrg>  joinOrgs = orgBiz.searchOrg(sysorg);
        orgs.addAll(joinOrgs);
        orgs.add(currentOrg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("role",role);
        // 查询是否需要省厅审核
        SysCfg fundingRudit = jswjwBiz.read("funding_Report_audit");
        if(GlobalConstant.FLAG_Y.equals(fundingRudit.getCfgValue())){
            model.addAttribute("fundingRudit","Y");
        } else {
            model.addAttribute("fundingRudit","N");
        }
        // 审核
        if(StringUtil.isNotBlank(auditBase)){
            return "/res/Funding/auditBaseAllocationDetails";
        }
        return "/res/Funding/addOrEditbaseAllocationDetails";
    }

    /**
     * 审核
     * @param model
     * @param recordFlow
     * @param state
     * @param resBaseFund
     * @return
     */
    @RequestMapping(value="auditBaseAllocationDetails"  )
    @ResponseBody
    public String auditBaseAllocationDetails(Model model, String recordFlow, String state,ResBaseFund resBaseFund){
        if(StringUtil.isNotBlank(recordFlow)){
            if("1".equals(state)){
                resBaseFund.setAuditStatusId("1");
                resBaseFund.setAuditStatusName("审核通过");
            } else {
                resBaseFund.setAuditStatusId("2");
                resBaseFund.setAuditStatusName("审核不通过");
            }
            resBaseFund.setAuditStatusId(state);
            iFundsBiz.editResBaseFund(resBaseFund);
        }
        return "1";
    }

    @RequestMapping(value="saveBaseAllocationDetails" )
    @ResponseBody
    public String saveBaseAllocationDetails(Model model,ResBaseFund resBaseFunds,String resBaseinputs,String role)
    {
        if("1".equals(resBaseFunds.getAuditStatusId())){
            resBaseFunds.setAuditStatusName("审核通过");
        } else{
            resBaseFunds.setAuditStatusId("3");
            resBaseFunds.setAuditStatusName("待审核");
        }

        int addCount =0;
        List<HashMap> resBaseDetaillist = new ArrayList<>();
        // 明細
        if(StringUtil.isNotBlank(resBaseinputs)){
            resBaseDetaillist = JSON.parseArray(resBaseinputs,HashMap.class);
        }
        String newRecordFlow = PkUtil.getUUID();
        String recordFlow = resBaseFunds.getRecordFlow();
        // 新增
        if(!StringUtil.isNotBlank(recordFlow))
        {
            // 基地角色 或者当前登录flow和基地名称
            if("hospital".equals(role)){
                SysUser sysuser = GlobalContext.getCurrentUser();
                resBaseFunds.setOrgFlow(sysuser.getOrgFlow());
                resBaseFunds.setOrgName(sysuser.getOrgName());
            }
            resBaseFunds.setRecordFlow(newRecordFlow);

            addCount = iFundsBiz.addResBaseFund(resBaseFunds);
            if( addCount > 0){
                if(resBaseDetaillist!= null && resBaseDetaillist.size() > 0){
                    for (HashMap map : resBaseDetaillist) {
                        ResBaseFundDetail resBaseFundDetail = new ResBaseFundDetail();
                        String id = (String)map.get("id");
                        String value = (String)map.get("value");
                        if(StringUtil.isNotBlank(id) && StringUtil.isNotBlank(value) ){
                            // 基地收支详情
                            resBaseFundDetail.setProjectOfBasefundsId(id);
                            resBaseFundDetail.setProjectOfBasefundsName(DictTypeEnum.BaseFundingUse.getDictNameById(id));
                            resBaseFundDetail.setMainFlow(newRecordFlow);
                            resBaseFundDetail.setAmountOfMoney(value);
                            iFundsBiz.editResBaseFundDetail(resBaseFundDetail);
                        }
                    }
                }
            }else{
                return "修改失敗！";
            }
        }
        else
        {
            // 編輯
            addCount = iFundsBiz.editResBaseFund(resBaseFunds);
            if(addCount >0){

                // 頁面填寫明細
                if(resBaseDetaillist!= null && resBaseDetaillist.size() > 0){

                    for (HashMap map : resBaseDetaillist) { // 2   JF001   JF002
                        ResBaseFundDetail FundDetail = new ResBaseFundDetail();
                        String id = (String)map.get("id") == null ? "" : (String)map.get("id");
                        String value = (String)map.get("value");
                        if(StringUtil.isNotBlank(id) && StringUtil.isNotBlank(value) ){
                            // 查詢字表信息修改
                            ResBaseFundDetail detail = new ResBaseFundDetail();
                            detail.setProjectOfBasefundsId(id);
                            detail.setMainFlow(recordFlow);
                            // 查詢已有記錄
                            List<ResBaseFundDetail> resBaseFundDetails = iFundsBiz.searchResBaseFundDetail(detail);

                            detail = new ResBaseFundDetail();
                            if(resBaseFundDetails != null && resBaseFundDetails.size() > 0 ){
                                // 有記錄，修改
                                detail.setRecordFlow(resBaseFundDetails.get(0).getRecordFlow());
                            }
                            detail.setMainFlow(recordFlow);
                            detail.setProjectOfBasefundsId(id);
                            detail.setProjectOfBasefundsName(DictTypeEnum.BaseFundingUse.getDictNameById(id));
                            detail.setAmountOfMoney(value);
                            iFundsBiz.editResBaseFundDetail(detail);

                        }
                    }
                }
            }else{
                return "修改失敗！";
            }
        }

        return "1";
    }

    /******************************************************** 经费：综合费用管理 start*********************************************************/

    /**
     * 查询所有记录
     * CosTmanagementList
     */
    @RequestMapping(value="queryCosTmanagementList"  )
    public String queryCosTmanagementList(Model model, ResSyntheticalFund resSyntheticalFund, HttpServletRequest request, Integer currentPage,
                                          String startDate, String endDate, String role){
        PageHelper.startPage(currentPage, getPageSize(request));

        List<ResSyntheticalFund> resSyntheticalFundList = iFundsBiz.searchResSyntheticalFund(startDate, endDate, resSyntheticalFund);

        model.addAttribute("teacherTrainingList", resSyntheticalFundList);
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        model.addAttribute("org",currentOrg);
        List<SysOrg>  orgs = new ArrayList<>();
        SysOrg sysorg = new SysOrg();
        sysorg.setOrgProvId(currentOrg.getOrgProvId());
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysOrg>  joinOrgs = orgBiz.searchOrg(sysorg);
        orgs.addAll(joinOrgs);
        orgs.add(currentOrg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("role", role);
        return "/res/Funding/cosTmanagementList";
    }

    /**
     *  根据id查询综合费用
     * @param model
     * @param recordFlow
     * @param view
     * @param role
     * @return
     */
    @RequestMapping(value="queryCosTmanagementById"  )
    public String queryCosTmanagementById(Model model, String recordFlow, String view, String role){
        if(StringUtil.isNotBlank(recordFlow)){
            // 基地收支
            ResSyntheticalFund resSyntheticalFund = iFundsBiz.readResSyntheticalFund(recordFlow);
            // 基地收支详情
            if(resSyntheticalFund != null){

                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("recordFlow",recordFlow);
                List<ResSyntheticalFundDetail> resSynDetails = iFundsBiz.resResSyntheticalFundDetailList(paramMap);
                HashMap<String, Object> detailMap = new HashMap<>();
                if(resSynDetails != null && resSynDetails.size()>0){
                    for (ResSyntheticalFundDetail SyntheticalDetail: resSynDetails ) {
                        String projectOfBasefundsId = SyntheticalDetail.getProjectOfSyntheticalId() == null ? "" : SyntheticalDetail.getProjectOfSyntheticalId();
                        String amountOfMoney = SyntheticalDetail.getAmountOfMoney() == null ? "" : SyntheticalDetail.getAmountOfMoney();
                        detailMap.put(projectOfBasefundsId,amountOfMoney);
                    }
                }
                model.addAttribute("detailMap", detailMap);
            }
            model.addAttribute("resBaseFunds",resSyntheticalFund);
        }
        // 基地信息
        SysOrg currentOrg = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("org",currentOrg);
        List<SysOrg>  orgs = new ArrayList<>();
        SysOrg sysorg = new SysOrg();
        sysorg.setOrgProvId(currentOrg.getOrgProvId());
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysOrg>  joinOrgs = orgBiz.searchOrg(sysorg);
        orgs.addAll(joinOrgs);
        orgs.add(currentOrg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("role",role);
        model.addAttribute("view",view);

        return "/res/Funding/cosAddEditTmanagemnt";
    }

    /**
     * 编辑或者新增综合费用
     * @param model
     * @param resBaseinputs
     * @param role
     * @return
     */
    @RequestMapping(value="saveSynCosTmanagemnt" )
    @ResponseBody
    public String saveSynCosTmanagemnt(Model model,ResSyntheticalFund resSyntheticalFund,String resBaseinputs,String role)
    {
        int addCount =0;
        List<HashMap> resBaseDetaillist = new ArrayList<>();
        // 明細
        if(StringUtil.isNotBlank(resBaseinputs)){
            resBaseDetaillist = JSON.parseArray(resBaseinputs,HashMap.class);
        }
        String recordFlow = resSyntheticalFund.getRecordFlow();
        // 合计为空默认保存为0
        if(StringUtil.isBlank(resSyntheticalFund.getAmountOfIncome())){
            resSyntheticalFund.setAmountOfIncome("0");
        }
        // 新增
        if(!StringUtil.isNotBlank(recordFlow))
        {
            String newRecordFlow = PkUtil.getUUID();
            resSyntheticalFund.setRecordFlow(newRecordFlow);
            addCount = iFundsBiz.addResSyntheticalFund(resSyntheticalFund);
            if( addCount > 0){
                if(resBaseDetaillist!= null && resBaseDetaillist.size() > 0){
                    for (HashMap map : resBaseDetaillist) {
                        ResSyntheticalFundDetail resSyntheticalFundDetail = new ResSyntheticalFundDetail();
                        String id = (String)map.get("id");
                        String value = (String)map.get("value");
                        if(StringUtil.isNotBlank(id) && StringUtil.isNotBlank(value) ){
                            // 基地收支详情
                            resSyntheticalFundDetail.setProjectOfSyntheticalId(id);
                            resSyntheticalFundDetail.setProjectOfSyntheticalName(DictTypeEnum.SynCostManagement.getDictNameById(id));
                            resSyntheticalFundDetail.setMainFlow(newRecordFlow);
                            resSyntheticalFundDetail.setAmountOfMoney(value);
                            iFundsBiz.editResSyntheticalFundDetail(resSyntheticalFundDetail);
                        }
                    }
                }
            }else{
                return "修改失敗！";
            }
        }
        else
        {
            // 編輯
            addCount = iFundsBiz.editResSyntheticalFund(resSyntheticalFund);
            if(addCount >0){

                // 頁面填寫明細
                if(resBaseDetaillist!= null && resBaseDetaillist.size() > 0){

                    for (HashMap map : resBaseDetaillist) {
                        String id = (String)map.get("id") == null ? "" : (String)map.get("id");
                        String value = (String)map.get("value");
                        if(StringUtil.isNotBlank(id) && StringUtil.isNotBlank(value) ){
                            // 查詢字表信息修改
                            ResSyntheticalFundDetail detail = new ResSyntheticalFundDetail();
                            detail.setProjectOfSyntheticalId(id);
                            detail.setMainFlow(recordFlow);
                            // 查詢已有記錄
                            List<ResSyntheticalFundDetail> resBaseFundDetails = iFundsBiz.searchResSyntheticalFundDetail(detail);

                            detail = new ResSyntheticalFundDetail();
                            if(resBaseFundDetails != null && resBaseFundDetails.size() > 0 ){
                                // 有記錄，修改
                                detail.setRecordFlow(resBaseFundDetails.get(0).getRecordFlow());
                            }
                            detail.setMainFlow(recordFlow);
                            detail.setProjectOfSyntheticalId(id);
                            detail.setProjectOfSyntheticalName(DictTypeEnum.SynCostManagement.getDictNameById(id));
                            detail.setAmountOfMoney(value);
                            iFundsBiz.editResSyntheticalFundDetail(detail);

                        }
                    }
                }
            }else{
                return "修改失敗！";
            }
        }

        return "1";
    }
    /********************************************************经费：综合费用管理 end *********************************************************/

    //省厅经费收支管理列表
    @RequestMapping("/provinceFundList")
    public String provinceFundList(String startDate,String endDate,Model model,HttpServletRequest request,Integer currentPage){
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResProvinceFund> provinceFundList = iFundsBiz.searchResProvinceFund(startDate,endDate);
        List<ResProvinceFundDetail> provinceFundDetailList = iFundsBiz.queryFundDetaiList();
        HashMap<String, Object> map = new HashMap<>();
        if(provinceFundDetailList != null ){
            for (ResProvinceFundDetail fundDetail: provinceFundDetailList) {
                map.put(fundDetail.getMainFlow(),"Y");
            }
        }
        model.addAttribute("provinceFundList",provinceFundList);
        model.addAttribute("detaiMap",map);
        return "/res/Funding/provinceFundList";
    }

    //编辑省厅经费收支管理页面
    @RequestMapping("/editProvinceFund")
    public String editProvinceFund(String recordFlow,Model model){
        ResProvinceFund provinceFund = iFundsBiz.readResProvinceFund(recordFlow);
        model.addAttribute("amountMoney",-1);
        if(StringUtil.isNotBlank(recordFlow)){
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("mainFlow",recordFlow);
            paramMap.put("source",'1');
            List<ResProvinceFundDetail> resProvinceFundDetails = iFundsBiz.searchResProvinceFundDetailMoney(paramMap);
            if(resProvinceFundDetails != null && resProvinceFundDetails.size() > 0){
                BigDecimal amountMoney = new BigDecimal(0.00);
                for (ResProvinceFundDetail fundDetail : resProvinceFundDetails) {
                    String amountOfMoney = fundDetail.getAmountOfMoney() == null ? "" : fundDetail.getAmountOfMoney();
                    if(StringUtil.isNotBlank(amountOfMoney)){
                        BigDecimal number= new BigDecimal(amountOfMoney);
                        amountMoney = amountMoney.add(number) ;
                    }
                }
                model.addAttribute("amountMoney",amountMoney);
            }
        }

        model.addAttribute("provinceFund",provinceFund);
        return "/res/Funding/editProvinceFund";
    }

    //编辑省厅经费收支
    @RequestMapping("/saveProvinceFund")
    @ResponseBody
    public int saveProvinceFund(ResProvinceFund provinceFund){
        String reportDate = provinceFund.getReportDate();
        if(StringUtil.isBlank(reportDate)){
            reportDate= DateUtil.getCurrDate();
            provinceFund.setReportDate(reportDate);
        }
        iFundsBiz.editResProvinceFund(provinceFund);
        return 1;
    }

    //删除省厅经费收支
    @RequestMapping("/delProvinceFund")
    @ResponseBody
    public int delProvinceFund(ResProvinceFund provinceFund){
        provinceFund.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        iFundsBiz.editResProvinceFund(provinceFund);
        return 1;
    }

    //省厅经费收支详情列表
    @RequestMapping("/provinceFundDetailList")
    public String provinceFundDetailList(ResProvinceFundDetail searchDetail,Model model){
        if(searchDetail != null){
            String mainFlow = searchDetail.getMainFlow() == null ? "" : searchDetail.getMainFlow();
            ResProvinceFund provinceFund = iFundsBiz.readResProvinceFund(mainFlow);
            model.addAttribute("provinceFund",provinceFund);
        }
        List<ResProvinceFundDetail> provinceFundDetailList = iFundsBiz.searchResProvinceFundDetail(searchDetail);
        model.addAttribute("provinceFundDetailList",provinceFundDetailList);
        if(provinceFundDetailList!=null&&provinceFundDetailList.size()>0){
            Map<String,Object> detailMap = new HashMap<>();
            for(ResProvinceFundDetail detail:provinceFundDetailList){
                String sourcesOfFundsId = detail.getSourcesOfFundsId();
                String projectOfFundsId = detail.getProjectOfFundsId();
                if(projectOfFundsId==null){
                    projectOfFundsId="";
                }
                detailMap.put(sourcesOfFundsId+projectOfFundsId,detail);
            }
            model.addAttribute("detailMap",detailMap);
        }
        return "/res/Funding/provinceFundDetailList";
    }

    //保存省厅经费收支详情
    @RequestMapping("/saveProvinceFundDetail")
    @ResponseBody
    public int saveProvinceFundDetail(String jsonData){
        iFundsBiz.editResProvinceFundDetailBatch(jsonData);
        return 1;
    }
}
