package com.pinde.sci.ctrl.eval;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.eval.IExpertOrgBiz;
import com.pinde.sci.biz.eval.IExpertOrgSpeBiz;
import com.pinde.sci.biz.jsres.IResOrgSpeBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.ExpertOrg;
import com.pinde.sci.model.mo.ExpertOrgSpe;
import com.pinde.sci.model.mo.ResOrgSpe;
import com.pinde.sci.model.mo.SysOrg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/eval/evalOrg")
public class EvalOrgController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IExpertOrgBiz evalOrgBiz;
    @Autowired
    private IExpertOrgSpeBiz expertOrgSpeBiz;
    @Autowired
    private IOrgBiz sysOrgBiz;
    @Autowired
    private IResOrgSpeBiz resOrgSpeBiz;

    @RequestMapping(value = "/main")
    public String main(Model model) {
        return "eval/evalOrg/main";
    }

    @RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
    public String list(SysOrg org,String joinEval, Integer currentPage, HttpServletRequest request, Model model,String evalYear){
        if(StringUtil.isNotBlank(evalYear)) {
            Map<String,Object> paramMap=new HashMap<>();
            paramMap.put("orgName",org.getOrgName());
            paramMap.put("orgFlow",org.getOrgFlow());
            paramMap.put("orgLevelId",org.getOrgLevelId());
            paramMap.put("joinEval",joinEval);
            paramMap.put("evalYear",evalYear);
            PageHelper.startPage(currentPage, getPageSize(request));
            List<SysOrg> sysList = evalOrgBiz.searchOrg(paramMap);
            if(sysList!=null)
            {
                Map<String,ExpertOrg> exportMap=new HashMap<>();
                Map<String,Object> speMap=new HashMap<>();
                for(SysOrg sysOrg:sysList)
                {
                    ExpertOrg expertOrg=evalOrgBiz.getByOrgAndYear(sysOrg.getOrgFlow(),evalYear,null);
                    exportMap.put(sysOrg.getOrgFlow(),expertOrg);
                    speMap.put(sysOrg.getOrgFlow(),evalOrgBiz.getExpertSpeByOrgAndYear(sysOrg.getOrgFlow(),evalYear));
                }
                model.addAttribute("exportMap", exportMap);
                model.addAttribute("speMap", speMap);
            }
            model.addAttribute("sysList", sysList);
        }
        return "eval/evalOrg/list";
    }

    @RequestMapping(value="/showExpertOrgSpe",method={RequestMethod.POST,RequestMethod.GET})
    public String showExpertOrgSpe(String orgFlow, Model model,String evalYear){
        SysOrg org=sysOrgBiz.readSysOrg(orgFlow);
        List<ExpertOrgSpe> orgSpes=expertOrgSpeBiz.readByOrgAndYear(orgFlow,evalYear,null);
        Map<String,ExpertOrgSpe> orgSpeMap=new HashMap<>();
        if(orgSpes!=null)
        {
            for(ExpertOrgSpe expertOrgSpe:orgSpes)
            {
                orgSpeMap.put(expertOrgSpe.getSpeId(),expertOrgSpe);
            }
        }
        model.addAttribute("orgSpeMap",orgSpeMap);
        ResOrgSpe resOrgSpe=new ResOrgSpe();
        resOrgSpe.setOrgFlow(orgFlow);
        resOrgSpe.setSpeTypeId(DictTypeEnum.DoctorTrainingSpe.getId());
        resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
        List<ResOrgSpe> orgSpeList=resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
        model.addAttribute("orgSpeList",orgSpeList);
        model.addAttribute("org",org);
        return "eval/evalOrg/showExpertOrgSpe";
    }

    @RequestMapping(value={"/saveEvalOrg"},method=RequestMethod.POST)
    public @ResponseBody String saveEvalOrg(ExpertOrg expertOrg, String roleFlow){
        ExpertOrg old=null;
        if(StringUtil.isBlank(expertOrg.getRecordFlow()))
        {
            old=evalOrgBiz.readByFlow(expertOrg.getRecordFlow());
        }
        if(old==null) {
            old=evalOrgBiz.getByOrgAndYear(expertOrg.getOrgFlow(),expertOrg.getEvalYear(),null);
        }
        if(old==null)
        {
            old=new ExpertOrg();
            SysOrg org=sysOrgBiz.readSysOrg(expertOrg.getOrgFlow());
            if(org!=null)
            {
                old.setOrgName(org.getOrgName());
            }
            old.setRecordFlow(expertOrg.getRecordFlow());
        }
        old.setOrgFlow(expertOrg.getOrgFlow());
        old.setEvalYear(expertOrg.getEvalYear());
        old.setRecordStatus(expertOrg.getRecordStatus());
        if(GlobalConstant.RECORD_STATUS_N.equals(expertOrg.getRecordStatus()))
        {
            int count=evalOrgBiz.findExpertOrgResult(expertOrg.getEvalYear(),expertOrg.getOrgFlow(),"","");
            if(count>0)
            {
                return "该基地已有评估结果，无法取消！";
            }
        }
        int result=evalOrgBiz.save(old);
        if(result==0)
        {
            return GlobalConstant.SAVE_FAIL;
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }
    @RequestMapping(value={"/saveEvalOrgSpe"},method=RequestMethod.POST)
    public @ResponseBody String saveEvalOrgSpe(ExpertOrgSpe expertOrgSpe){
        ExpertOrgSpe old=null;
        if(StringUtil.isBlank(expertOrgSpe.getRecordFlow()))
        {
            old=expertOrgSpeBiz.readByFlow(expertOrgSpe.getRecordFlow());
        }
        if(old==null) {
            old=expertOrgSpeBiz.getByOrgAndYear(expertOrgSpe.getOrgFlow(),expertOrgSpe.getEvalYear(),expertOrgSpe.getSpeId(),null);
        }
        if(old==null)
        {
            old=new ExpertOrgSpe();
            SysOrg org=sysOrgBiz.readSysOrg(expertOrgSpe.getOrgFlow());
            if(org!=null)
            {
                old.setOrgName(org.getOrgName());
            }
            old.setRecordFlow(expertOrgSpe.getRecordFlow());
        }
        old.setSpeId(expertOrgSpe.getSpeId());
        old.setSpeName(DictTypeEnum.DoctorTrainingSpe.getDictNameById(expertOrgSpe.getSpeId()));
        old.setOrgFlow(expertOrgSpe.getOrgFlow());
        old.setEvalYear(expertOrgSpe.getEvalYear());
        old.setRecordStatus(expertOrgSpe.getRecordStatus());
        if(GlobalConstant.RECORD_STATUS_N.equals(expertOrgSpe.getRecordStatus()))
        {
            int count=evalOrgBiz.findExpertOrgResult(expertOrgSpe.getEvalYear(),expertOrgSpe.getOrgFlow(),expertOrgSpe.getSpeId(), "");
            if(count>0)
            {
                return "该专业基地已有评估结果，无法取消！";
            }
        }
        int result=expertOrgSpeBiz.save(old);
        if(result==0)
        {
            return GlobalConstant.SAVE_FAIL;
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

}