package com.pinde.sci.ctrl.sys;

import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IResTestConfigBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SysCfgMapper;
import com.pinde.sci.model.mo.ResTestConfig;
import com.pinde.sci.model.mo.SysCfg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/cfg")
public class CfgController extends GeneralController  {
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private SysCfgMapper sysCfgMapper;
	@Autowired
	private ICfgBiz cfgBiz;
	@Autowired
	private IResTestConfigBiz resTestConfigBiz;
	@Autowired
	private OrgBizImpl orgBiz;
	@RequestMapping(value={"/main"})
	public String main(Model model){	
		return "sys/cfg/main";
	}
	
	@RequestMapping(value="/agreement",method={RequestMethod.GET})
	public ModelAndView agreement(HttpServletRequest request){
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		ModelAndView mav=new ModelAndView("sys/cfg/agreement");
		SysCfg cfg=new SysCfg();
		cfg.setWsId(wsId);
		List<SysCfg> sysCfgList=cfgBiz.search(cfg);
		Map<String, String> sysCfgMap=new HashMap<String, String>();
		for(SysCfg sysCfg:sysCfgList ){
			sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgValue());
			if(StringUtil.isNotBlank(sysCfg.getCfgBigValue())){
				sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgBigValue());				
			}
		}
		return mav.addObject("sysCfgMap",sysCfgMap);	
	}
	
	@RequestMapping(value="/edit",method={RequestMethod.GET})
	public ModelAndView edit(HttpServletRequest request){
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		ModelAndView mav=new ModelAndView("sys/cfg/"+wsId+"Cfg");
		SysCfg cfg=new SysCfg();
		cfg.setWsId(wsId);
		List<SysCfg> sysCfgList=cfgBiz.search(cfg);
		Map<String, String> sysCfgMap=new HashMap<String, String>();
		Map<String, String> sysCfgDescMap=new HashMap<String, String>();
		for(SysCfg sysCfg:sysCfgList ){
			if(StringUtil.isNotBlank(sysCfg.getCfgDesc())){
				sysCfgDescMap.put(sysCfg.getCfgCode(), sysCfg.getCfgDesc());
			}
			sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgValue());
			if(StringUtil.isNotBlank(sysCfg.getCfgBigValue())){
				sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgBigValue());				
			}
		}
		List<ResTestConfig> resTestConfigList = resTestConfigBiz.findAllEffective();
		mav.addObject("resTest", resTestConfigList);
		mav.addObject("sysCfgDescMap",sysCfgDescMap);
		SysOrg sysorg = new SysOrg();
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        sysorg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
		mav.addObject("orgs",orgs);
		return mav.addObject("sysCfgMap",sysCfgMap);	
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpServletRequest request){
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		String [] cfgCodes =request.getParameterValues("cfgCode");
		if(cfgCodes!=null){
			List<SysCfg> sysCfgList = new ArrayList<SysCfg>();
			for(String cfgCode : cfgCodes){
				String[] sysCfgValues=request.getParameterValues(cfgCode);
				String sysCfgValue= "";

				if(sysCfgValues != null && sysCfgValues.length > 1){
					sysCfgValue = StringUtils.join(sysCfgValues,",");
				}else{
					sysCfgValue = request.getParameter(cfgCode);
				}

				String sysCfgDesc = request.getParameter(cfgCode+"_desc");
				SysCfg cfg=new SysCfg();
				cfg.setCfgCode(cfgCode);
				cfg.setCfgValue(sysCfgValue);
				cfg.setCfgDesc(sysCfgDesc);
				String reqWsId = request.getParameter(cfgCode+"_ws_id");
				if(StringUtil.isBlank(reqWsId)){
					reqWsId = wsId;
				}
				cfg.setWsId(reqWsId);
				cfg.setWsName(InitConfig.getWorkStationName(cfg.getWsId()));
				if(StringUtil.isBlank(cfg.getWsName())){
					cfg.setWsName("全局公用配置");
				}

				String sysCfgBigValue=request.getParameter(cfgCode+"_big_value");
				cfg.setCfgBigValue(sysCfgBigValue);
                cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				sysCfgList.add(cfg);
			}
			cfgBiz.save(sysCfgList);
			}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	@RequestMapping(value="/saveOne",method=RequestMethod.POST)
	@ResponseBody
	public String saveOne(HttpServletRequest request){
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		String [] cfgCodes =request.getParameterValues("cfgCode");
		if(cfgCodes!=null){
			List<SysCfg> sysCfgList = new ArrayList<SysCfg>();
			for(String cfgCode : cfgCodes){
				String sysCfgValue=request.getParameter(cfgCode);
				String sysCfgDesc=request.getParameter(cfgCode+"_desc");
				SysCfg cfg=new SysCfg();
				cfg.setCfgCode(cfgCode);
				cfg.setCfgValue(sysCfgValue);
				cfg.setCfgDesc(sysCfgDesc);
				String reqWsId = request.getParameter(cfgCode+"_ws_id");
				if(StringUtil.isBlank(reqWsId)){
					reqWsId = wsId;
				}
				cfg.setWsId(reqWsId);
				cfg.setWsName(InitConfig.getWorkStationName(cfg.getWsId()));
				if(StringUtil.isBlank(cfg.getWsName())){
					cfg.setWsName("全局公用配置");
				}
				
				String sysCfgBigValue=request.getParameter(cfgCode+"_big_value");
				cfg.setCfgBigValue(sysCfgBigValue);
                cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				sysCfgList.add(cfg);
			}
			int result = cfgBiz.saveSysCfgInfo(sysCfgList);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
				SysCfg sysCfg = sysCfgList.get(0);
				ServletContext application = request.getServletContext();
				Map<String,String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
				sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgValue());
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	
	@RequestMapping(value="/testEmail",method={RequestMethod.GET})
	public String testEmail(HttpServletRequest request){
		return "sys/cfg/tool/testEmail";
	}
	
	@RequestMapping(value="/sendEmail",method={RequestMethod.POST})
	@ResponseBody
	public String sendEmail(String receiver,String title,String content){
		msgBiz.addEmailMsg(receiver, title, content);
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	@RequestMapping(value="/getErrorMsg",method={RequestMethod.GET})
	@ResponseBody
	public Integer getErrorMsg(String msgTypeId){
		return 	msgBiz.countErrorMsg(msgTypeId);
	}
	
	@RequestMapping(value={"/modMailPassword"})
	public String modMailPassword(Model model){	
		return "sys/user/modMailPasswd";
	}
	/**
	 * 保存需要审核的专业
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/saveTrainSpe"})
	@ResponseBody
	public String saveTrainSpe(Model model,String result,String cfgCode){
		SysCfg sysCfg=cfgBiz.read(cfgCode);
		if (sysCfg!=null) {
			sysCfg.setCfgBigValue(result);
		}
		List<SysCfg> saveCfgs=new ArrayList<SysCfg>();
		saveCfgs.add(sysCfg);
		cfgBiz.save(saveCfgs);
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	
	@RequestMapping(value={"/saveMailPassword"})
	public String saveMailPassword(String userFlow,String mailPassword,String weixinRemind){
		SysUser user = this.userBiz.readSysUser(userFlow);
		SysCfg cfg = cfgBiz.read(user.getUserEmail());
		if (cfg == null) {
			cfg = new SysCfg();
			cfg.setCfgCode(user.getUserEmail());
			cfg.setCfgValue(mailPassword);
			cfg.setWsId("mail");
			cfg.setCfgDesc("品德邮箱密码");
			GeneralMethod.setRecordInfo(cfg, true);
			cfg.setRecordStatus(weixinRemind);
			sysCfgMapper.insert(cfg);
		} else {
			cfg.setCfgValue(mailPassword);
			cfg.setCfgDesc("品德邮箱密码");
			GeneralMethod.setRecordInfo(cfg, false);
			cfg.setRecordStatus(weixinRemind);
			sysCfgMapper.updateByPrimaryKeySelective(cfg);
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	
	@RequestMapping(value={"/savePageSize"},method=RequestMethod.POST)
	@ResponseBody
	public String savePageSize(HttpServletRequest request){
		cfgBiz.savePageSize(request);
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	/**
	 * 跳转页面
	 * @return
	 */
	@RequestMapping(value="/spePage")
	public String spePage(Model model,String  trainCategoryType){
		model.addAttribute("trainCategoryType", trainCategoryType);
		return "sys/cfg/selectSpe";
	}
	@RequestMapping(value="/speMainPage")
	public String speMainPage(String cfgCode,Model model){
		model.addAttribute("cfgCode", cfgCode);
		return "jsres/selectSpeMain";
	}
	
}
