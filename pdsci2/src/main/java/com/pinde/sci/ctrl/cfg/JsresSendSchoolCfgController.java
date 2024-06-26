package com.pinde.sci.ctrl.cfg;

import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.JsresPowerCfg;
import com.pinde.sci.model.mo.SysDict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/jsres/sendSchoolCfg")
public class JsresSendSchoolCfgController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(JsresSendSchoolCfgController.class);

	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;

	@RequestMapping(value = {"/main" }, method = RequestMethod.GET)
	public String main (SysDict sysDict, Integer currentPage, HttpServletRequest request, Model model) {
		if (sysDict == null) {
			sysDict = new SysDict();
		}
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		sysDict.setDictTypeId(DictTypeEnum.SendSchool.getId());
		sysDict.setDictTypeName(DictTypeEnum.SendSchool.getName());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		model.addAttribute("dictList", dictList);
		return "jsres/cfg/sendSchoolCfg/main";
	}
	@RequestMapping(value = {"/sendSchoolList" })
	public String sendSchoolList (SysDict sysDict, Integer currentPage, HttpServletRequest request, Model model){
		if (sysDict == null) {
			sysDict = new SysDict();
		}
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		sysDict.setDictTypeId(DictTypeEnum.SendSchool.getId());
		sysDict.setDictTypeName(DictTypeEnum.SendSchool.getName());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		model.addAttribute("dictList", dictList);
		return "jsres/cfg/sendSchoolCfg/sendSchoolList";
	}
	@RequestMapping(value = {"/getMenu" })
	public String getMenu (String dictId,  HttpServletRequest request, Model model){
		model.addAttribute("dictId",dictId);
		return "jsres/cfg/sendSchoolCfg/getMenu";
	}

	@RequestMapping(value="/saveOne",method= RequestMethod.POST)
	@ResponseBody
	public String saveOne(String dictFlow,HttpServletRequest request){
		String [] cfgCodes = request.getParameterValues("cfgCode");
		if(cfgCodes!=null){
			List<JsresPowerCfg> cfgList = new ArrayList<JsresPowerCfg>();
			for(String cfgCode : cfgCodes){
				String cfgValue = request.getParameter(cfgCode);
				String cfgDesc = request.getParameter(cfgCode+"_desc");
				JsresPowerCfg cfg = new JsresPowerCfg();
				cfg.setCfgCode(cfgCode);
				cfg.setCfgValue(cfgValue);
				cfg.setCfgDesc(cfgDesc);
				cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				cfgList.add(cfg);
			}
			int result = jsResPowerCfgBiz.saveList(cfgList);
			if(GlobalConstant.ZERO_LINE != result){
				Map<String,Object> map = new HashMap<>();
				map.put("dictFlow",dictFlow);
				JsresPowerCfg cfg = jsResPowerCfgBiz.read(Arrays.asList(cfgCodes).get(0));
				if(null != cfg && "Y".equals(cfg.getCfgValue())){
					map.put("isSubmitId","Y");
				}else{
					map.put("isSubmitId","N");
				}
				dictBiz.updateSchoolSubmit(map);
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value="/updateSchoolSubmit",method= RequestMethod.POST)
	@ResponseBody
	public String updateSchoolSubmit(String[] dictFlows){
		if(null != dictFlows && dictFlows.length>0){
			List<String> dictFlowList = Arrays.asList(dictFlows);
			int count = dictBiz.saveSchoolSubmit(dictFlowList);
			if(count > 0) {
				return GlobalConstant.OPRE_SUCCESSED;
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}
}

