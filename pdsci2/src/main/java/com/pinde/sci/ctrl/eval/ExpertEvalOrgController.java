package com.pinde.sci.ctrl.eval;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.eval.IEvalCfgBiz;
import com.pinde.sci.biz.eval.IExpertEvalOrgBiz;
import com.pinde.sci.biz.eval.IExpertOrgBiz;
import com.pinde.sci.biz.eval.IExpertOrgSpeBiz;
import com.pinde.sci.biz.jsres.IResOrgSpeBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/eval/expertEvalOrg")
public class ExpertEvalOrgController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IExpertEvalOrgBiz expertEvalOrgBiz;
    @Autowired
    private IExpertOrgBiz evalOrgBiz;
    @Autowired
    private IExpertOrgSpeBiz expertOrgSpeBiz;
    @Autowired
    private IOrgBiz sysOrgBiz;
    @Autowired
    private IResOrgSpeBiz resOrgSpeBiz;
    @Autowired
    private IEvalCfgBiz evalCfgBiz;

    @RequestMapping(value = "/main")
    public String main(Model model) {
        return "eval/expertEvalOrg/main";
    }

    @RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
    public String list(String orgName, Integer currentPage, HttpServletRequest request, Model model,String evalYear){
        if(StringUtil.isNotBlank(evalYear)) {
            Map<String,Object> paramMap=new HashMap<>();
            paramMap.put("evalYear",evalYear);
            paramMap.put("orgName",orgName);
            paramMap.put("joinEval","Y");
            PageHelper.startPage(currentPage, getPageSize(request));
            List<SysOrg> orgs=evalOrgBiz.searchOrg(paramMap);
            model.addAttribute("orgs",orgs);
            if(orgs!=null)
            {
                Map<String,List<SysUser>> evalOrgExpertMap=new HashMap<>();
                for(SysOrg o:orgs)
                {
                    List<SysUser> orgExperts=expertEvalOrgBiz.getOrgExpertByYearAndOrgFlow(evalYear,o.getOrgFlow());
                    evalOrgExpertMap.put(o.getOrgFlow(),orgExperts);
                }
                model.addAttribute("evalOrgExpertMap",evalOrgExpertMap);
            }
        }
        return "eval/expertEvalOrg/list";
    }
    @RequestMapping(value="/addOrgExpert",method={RequestMethod.POST,RequestMethod.GET})
    public String addOrgExpert(String orgFlow, Integer currentPage, HttpServletRequest request, Model model,String evalYear){
        if(StringUtil.isNotBlank(evalYear)) {
            SysOrg org=sysOrgBiz.readSysOrg(orgFlow);
            model.addAttribute("org",org);
            Map<String,Object> paramMap=new HashMap<>();
            String roleFlow=InitConfig.getSysCfg("eval_expert_role_flow");
            String wsId = (String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
            paramMap.put("roleFlow",roleFlow);
            paramMap.put("wsId",wsId);
            paramMap.put("statusId", UserStatusEnum.Activated.getId());
            List<SysUser>  userList= userBiz.searchUserWithRole(paramMap);
            model.addAttribute("userList",userList);
            Map<String,ExpertEvalOrg> evalOrgMap=new HashMap<>();
            if(userList!=null)
            {
                List<ExpertEvalOrg> expertEvalOrgs=expertEvalOrgBiz.readByOrgFlowAndYear(orgFlow,evalYear,"");
                if(expertEvalOrgs!=null)
                {
                    for(ExpertEvalOrg evalOrg:expertEvalOrgs)
                    {
                        evalOrgMap.put(evalOrg.getExpertUserFlow(),evalOrg);
                    }
                }
            }
            model.addAttribute("evalOrgMap",evalOrgMap);
        }
        return "eval/expertEvalOrg/addOrgExpert";
    }

    @RequestMapping(value={"/saveEvalOrg"},method=RequestMethod.POST)
    public @ResponseBody String saveEvalOrg(ExpertEvalOrg expertEvalOrg){
        ExpertEvalOrg old=null;
        if(StringUtil.isBlank(expertEvalOrg.getRecordFlow()))
        {
            old=expertEvalOrgBiz.readByFlow(expertEvalOrg.getRecordFlow());
        }
        if(old==null) {
            old=expertEvalOrgBiz.getByUserAndOrgAndYear(expertEvalOrg.getExpertUserFlow(),expertEvalOrg.getOrgFlow(),expertEvalOrg.getEvalYear(),null);
        }
        if(old==null)
        {
            old=new ExpertEvalOrg();
            SysOrg org=sysOrgBiz.readSysOrg(expertEvalOrg.getOrgFlow());
            if(org!=null)
            {
                old.setOrgName(org.getOrgName());
            }
            old.setRecordFlow(expertEvalOrg.getRecordFlow());
            SysUser expert=userBiz.readSysUser(expertEvalOrg.getExpertUserFlow());
            if(expert!=null)
            {
                old.setExpertUserName(expert.getUserName());
            }
        }
        old.setExpertUserFlow(expertEvalOrg.getExpertUserFlow());
        old.setOrgFlow(expertEvalOrg.getOrgFlow());
        old.setEvalYear(expertEvalOrg.getEvalYear());
        old.setRecordStatus(expertEvalOrg.getRecordStatus());

        if(GlobalConstant.RECORD_STATUS_N.equals(expertEvalOrg.getRecordStatus()))
        {
            int count=evalOrgBiz.findExpertOrgResult(expertEvalOrg.getEvalYear(),expertEvalOrg.getOrgFlow(),"", expertEvalOrg.getExpertUserFlow());
            if(count>0)
            {
                return "已对该基地进行评估，无法取消！";
            }
        }
        int result=expertEvalOrgBiz.saveEvalOrg(old);
        if(result==0)
        {
            return GlobalConstant.SAVE_FAIL;
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }
    @RequestMapping(value={"/saveExpertEvalOrgCfg"},method=RequestMethod.POST)
    public @ResponseBody String saveExpertEvalOrgCfg(String evalYear,String  expertUserFlow,String orgFlow,String []cfgFlows,String []recordFlows){
        if(StringUtil.isBlank(evalYear))
       {
           return "请选择评估年份！";
       }
        if(StringUtil.isBlank(orgFlow))
       {
           return "请选择参与评估的基地！";
       }
        if(StringUtil.isBlank(expertUserFlow))
       {
           return "请选择专家！";
       }
        if(recordFlows!=null&&recordFlows.length>0)
        {
            for(String recordFlow:recordFlows) {
                int count=expertEvalOrgBiz.checkCfgHasResult(recordFlow);
                if(count>0) {
                    String msg="评估配置已存在评估结果，无法取消！";
                    ExpertEvalOrgCfg expertEvalOrgCfg = expertEvalOrgBiz.readExpertOrgCfgByFlow(recordFlow);
                    if(expertEvalOrgCfg!=null)
                    {
                        ExpertEvalCfg evalCfg=evalCfgBiz.readByFlow(expertEvalOrgCfg.getCfgFlow());
                        if(evalCfg!=null&&StringUtil.isNotBlank(evalCfg.getCfgName()))
                        {
                            msg="【"+evalCfg.getCfgName()+"】"+msg;
                        }
                    }
                    return msg;
                }
            }
        }
        expertEvalOrgBiz.saveExpertEvalOrgCfg(evalYear,expertUserFlow,orgFlow,cfgFlows,recordFlows);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    private void getChildrenList(ExpertEvalCfg cfg, List<ExpertEvalCfg> cfgList, String orgFlow, String expertUserFlow) {
        if(cfgList!=null&&cfg!=null)
        {
            List<ExpertEvalCfg> childrens=evalCfgBiz.getChildrenListNotInCfg(cfg.getCfgFlow(),orgFlow,expertUserFlow);
            if(childrens!=null&&childrens.size()>0)
            {
                for (ExpertEvalCfg c : childrens) {
                    cfgList.add(c);
                    getChildrenList(c, cfgList, orgFlow, expertUserFlow);
                }
            }
        }
    }
    @RequestMapping(value="/showExpertOrgCfg",method={RequestMethod.POST,RequestMethod.GET})
    public String showExpertOrgCfg(String orgFlow,String expertUserFlow, Model model,String evalYear){
        if(StringUtil.isNotBlank(evalYear))
        {
            List<ExpertEvalCfg>  allList=null;
            ExpertEvalCfg cfg=evalCfgBiz.findByEvalYearFirst(evalYear);
            if(cfg!=null)
            {
                allList= new ArrayList<>();
                allList.add(cfg);
                getChildrenList(cfg, allList,orgFlow,expertUserFlow);
            }
            model.addAttribute("allList",allList);
            Map<String,ExpertEvalOrgCfg> orgCfgMap=new HashMap<>();
            List<ExpertEvalOrgCfg> cfgs=expertEvalOrgBiz.getCfgByUserAndOrgAndYear(expertUserFlow,orgFlow,evalYear,"");
            if(cfgs!=null)
            {
                for(ExpertEvalOrgCfg c:cfgs)
                {
                    orgCfgMap.put(c.getCfgFlow(),c);
                }
            }
            model.addAttribute("orgCfgMap",orgCfgMap);
        }
        return "eval/expertEvalOrg/showExpertOrgCfg";
    }
}