package com.pinde.sci.ctrl.eval;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.eval.*;
import com.pinde.sci.biz.jsres.IResOrgSpeBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
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
@RequestMapping("/eval/expertEvalOrgSpe")
public class ExpertEvalOrgSpeController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IExpertEvalOrgBiz expertEvalOrgBiz;
    @Autowired
    private IExpertEvalOrgSpeBiz expertEvalOrgSpeBiz;
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
        return "eval/expertEvalOrgSpe/main";
    }

    @RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
    public String list(String speId,String orgName, Integer currentPage, HttpServletRequest request, Model model,String evalYear){
        if(StringUtil.isNotBlank(evalYear)) {
            Map<String,Object> paramMap=new HashMap<>();
            paramMap.put("speId",speId);
            paramMap.put("orgName",orgName);
            paramMap.put("evalYear",evalYear);
            paramMap.put("joinEval","Y");
            PageHelper.startPage(currentPage, getPageSize(request));
            List<Map<String,String>> orgSpeList=expertEvalOrgSpeBiz.expertOrgSpeList(paramMap);
            model.addAttribute("orgSpeList",orgSpeList);

            if(orgSpeList!=null)
            {
                Map<String,List<SysUser>> evalOrgSpeExpertMap=new HashMap<>();
                for(Map<String,String> o:orgSpeList)
                {
                    List<SysUser> orgExperts=expertEvalOrgSpeBiz.getSpeExpertByYearAndOrgFlow(evalYear,o.get("orgFlow"),o.get("speId"));
                    evalOrgSpeExpertMap.put(o.get("orgFlow")+o.get("speId"),orgExperts);
                }
                model.addAttribute("evalOrgSpeExpertMap",evalOrgSpeExpertMap);
            }
        }
        return "eval/expertEvalOrgSpe/list";
    }

    @RequestMapping(value="/addOrgExpert",method={RequestMethod.POST,RequestMethod.GET})
    public String addOrgExpert(String orgFlow, String speId, HttpServletRequest request, Model model,String evalYear){
        if(StringUtil.isNotBlank(evalYear)) {
            SysOrg org=sysOrgBiz.readSysOrg(orgFlow);
            model.addAttribute("org",org);
            String speName= DictTypeEnum.getDictName(DictTypeEnum.DoctorTrainingSpe,speId);
            model.addAttribute("speName",speName);

            Map<String,Object> paramMap=new HashMap<>();
            String roleFlow=InitConfig.getSysCfg("eval_expert_role_flow");
            String wsId = (String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
            paramMap.put("roleFlow",roleFlow);
            paramMap.put("wsId",wsId);
            paramMap.put("statusId", UserStatusEnum.Activated.getId());
            List<SysUser>  userList= userBiz.searchUserWithRole(paramMap);
            model.addAttribute("userList",userList);
            Map<String,ExpertEvalOrgSpe> evalOrgMap=new HashMap<>();
            if(userList!=null)
            {
                List<ExpertEvalOrgSpe> expertEvalOrgs=expertEvalOrgSpeBiz.readByOrgFlowAndYearAndSpeId(evalYear,orgFlow,speId,"");
                if(expertEvalOrgs!=null)
                {
                    for(ExpertEvalOrgSpe evalOrg:expertEvalOrgs)
                    {
                        evalOrgMap.put(evalOrg.getExpertUserFlow(),evalOrg);
                    }
                }
            }
            model.addAttribute("evalOrgMap",evalOrgMap);
        }
        return "eval/expertEvalOrgSpe/addOrgExpert";
    }

    @RequestMapping(value={"/saveEvalOrgSpe"},method=RequestMethod.POST)
    public @ResponseBody String saveEvalOrgSpe(ExpertEvalOrgSpe expertEvalOrgSpe){
        ExpertEvalOrgSpe old=null;
        if(StringUtil.isBlank(expertEvalOrgSpe.getRecordFlow()))
        {
            old=expertEvalOrgSpeBiz.readByFlow(expertEvalOrgSpe.getRecordFlow());
        }
        if(old==null) {
            old=expertEvalOrgSpeBiz.getByUserAndOrgAndYearAndSpe(expertEvalOrgSpe.getExpertUserFlow(),expertEvalOrgSpe.getOrgFlow(),expertEvalOrgSpe.getSpeId(),expertEvalOrgSpe.getEvalYear(),null);
        }
        if(old==null)
        {
            old=new ExpertEvalOrgSpe();
            SysOrg org=sysOrgBiz.readSysOrg(expertEvalOrgSpe.getOrgFlow());
            if(org!=null)
            {
                old.setOrgName(org.getOrgName());
            }
            old.setRecordFlow(expertEvalOrgSpe.getRecordFlow());
            SysUser expert=userBiz.readSysUser(expertEvalOrgSpe.getExpertUserFlow());
            if(expert!=null)
            {
                old.setExpertUserName(expert.getUserName());
            }
            if(StringUtil.isNotBlank(expertEvalOrgSpe.getSpeId()))
            {
                old.setSpeName(DictTypeEnum.getDictName(DictTypeEnum.DoctorTrainingSpe,expertEvalOrgSpe.getSpeId()));
            }
        }
        old.setExpertUserFlow(expertEvalOrgSpe.getExpertUserFlow());
        old.setOrgFlow(expertEvalOrgSpe.getOrgFlow());
        old.setSpeId(expertEvalOrgSpe.getSpeId());
        old.setEvalYear(expertEvalOrgSpe.getEvalYear());
        old.setRecordStatus(expertEvalOrgSpe.getRecordStatus());

        if(GlobalConstant.RECORD_STATUS_N.equals(expertEvalOrgSpe.getRecordStatus()))
        {
            int count=evalOrgBiz.findExpertOrgResult(expertEvalOrgSpe.getEvalYear(),expertEvalOrgSpe.getOrgFlow(),
                    expertEvalOrgSpe.getSpeId(),expertEvalOrgSpe.getExpertUserFlow());
            if(count>0)
            {
                return "已对该专业基地进行评估，无法取消！";
            }
        }
        int result=expertEvalOrgSpeBiz.saveEvalOrgSpe(old);
        if(result==0)
        {
            return GlobalConstant.SAVE_FAIL;
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value={"/saveExpertEvalOrgSpeCfg"},method=RequestMethod.POST)
    public @ResponseBody String saveExpertEvalOrgSpeCfg(String evalYear,String  expertUserFlow,String  speId,String orgFlow,String []cfgFlows,String []recordFlows){
        if(StringUtil.isBlank(evalYear))
        {
            return "请选择评估年份！";
        }
        if(StringUtil.isBlank(orgFlow))
        {
            return "请选择基地！";
        }
        if(StringUtil.isBlank(speId))
        {
            return "请选择专业基地！";
        }
        if(StringUtil.isBlank(expertUserFlow))
        {
            return "请选择专家！";
        }
        if(recordFlows!=null&&recordFlows.length>0)
        {
            for(String recordFlow:recordFlows) {
                int count=expertEvalOrgBiz.checkSpeCfgHasResult(recordFlow,orgFlow,speId);
                if(count>0) {
                    String msg="评估配置已存在评估结果，无法取消！";
                    ExpertEvalOrgSpeCfg expertEvalOrgCfg = expertEvalOrgSpeBiz.readExpertOrgSpeCfgByFlow(recordFlow);
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
        expertEvalOrgSpeBiz.saveExpertEvalOrgSpeCfg(evalYear,expertUserFlow,orgFlow,speId,cfgFlows,recordFlows);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    private void getChildrenList(ExpertEvalCfg cfg, List<ExpertEvalCfg> cfgList, String orgFlow, String expertUserFlow,String speId) {
        if(cfgList!=null&&cfg!=null)
        {

            List<ExpertEvalCfg> childrens=null;
            if(cfg.getLevelId()==null||cfg.getLevelId()!=1) {
                childrens = evalCfgBiz.getChildrenListNotInCfgNotManage(cfg.getCfgFlow(), orgFlow, expertUserFlow,"");
            }else{
                childrens = evalCfgBiz.getChildrenListNotInCfgNotManage(cfg.getCfgFlow(), orgFlow, expertUserFlow,speId);
            }
            if(childrens!=null&&childrens.size()>0)
            {
                for (ExpertEvalCfg c : childrens) {
                    cfgList.add(c);
                    getChildrenList(c, cfgList, orgFlow, expertUserFlow,speId);
                }
            }
        }
    }
    @RequestMapping(value="/showExpertOrgSpeCfg",method={RequestMethod.POST,RequestMethod.GET})
    public String showExpertOrgSpeCfg(String orgFlow,String speId,String expertUserFlow, Model model,String evalYear){
        if(StringUtil.isNotBlank(evalYear))
        {
            List<ExpertEvalCfg>  allList=null;
            ExpertEvalCfg cfg=evalCfgBiz.findByEvalYearFirst(evalYear);
            if(cfg!=null)
            {
                allList= new ArrayList<>();
                allList.add(cfg);
                getChildrenList(cfg, allList,orgFlow,expertUserFlow,speId);
            }
            model.addAttribute("allList",allList);
            Map<String,ExpertEvalOrgSpeCfg> orgCfgMap=new HashMap<>();
            List<ExpertEvalOrgSpeCfg> cfgs=expertEvalOrgSpeBiz.getCfgByUserAndOrgAndYear(expertUserFlow,orgFlow,evalYear,speId,"");
            if(cfgs!=null)
            {
                for(ExpertEvalOrgSpeCfg c:cfgs)
                {
                    orgCfgMap.put(c.getCfgFlow(),c);
                }
            }
            model.addAttribute("orgCfgMap",orgCfgMap);
        }
        return "eval/expertEvalOrgSpe/showExpertOrgSpeCfg";
    }
}