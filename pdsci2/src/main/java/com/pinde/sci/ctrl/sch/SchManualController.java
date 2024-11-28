package com.pinde.sci.ctrl.sch;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.SysCfg;
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
@RequestMapping("/sch/manual")
public class SchManualController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(SchManualController.class);
	
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private ISchDeptRelBiz deptRelBiz;
	@Autowired
	private ISchDeptExternalRelBiz deptExtRelBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchManualBiz schManualBiz;

	@RequestMapping(value = {"/main" }, method = RequestMethod.GET)
	public String main (Model model) throws Exception{
		return "sch/manual/main";
	}
	@RequestMapping(value = {"/userList" })
	public String userList (Model model,Integer currentPage,HttpServletRequest request,
							String orgFlow,String sessionNumber,String workOrgId,String userName,String idNo
							,String datas[]	) throws Exception{
		List<String> docTypeList=new ArrayList<>();
		if(datas.length>0)
		{

			docTypeList.addAll(Arrays.asList(datas));
		}
		Map<String,Object> params=new HashMap<>();
		params.put("orgFlow",orgFlow);
		params.put("sessionNumber",sessionNumber);
		params.put("workOrgId",workOrgId);
		params.put("userName",userName);
		params.put("idNo",idNo);
		params.put("docTypeList",docTypeList);
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> list=schManualBiz.userList(params);
		Map<String,String> cfgMap=new HashMap<>();
		for(Map<String,Object> map:list)
		{
			String userFlow= (String) map.get("userFlow");
			String cfgCode = "stu_manual_" + userFlow;
			SysCfg temp = cfgBiz.read(cfgCode);
			if (temp != null) {
				cfgMap.put(temp.getCfgCode(), temp.getCfgValue());
			}
		}
		model.addAttribute("cfgMap",cfgMap);
		model.addAttribute("list",list);
		return "sch/manual/userList";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(HttpServletRequest request, String[] userFlows, String recordStatus) {
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		List<SysCfg> sysCfgList = new ArrayList<SysCfg>();
		for (String userFlow : userFlows) {
			String cfgCode = "stu_manual_" + userFlow;
			SysCfg cfg = new SysCfg();
			cfg.setCfgCode(cfgCode);
			cfg.setCfgValue(recordStatus);
			cfg.setCfgDesc("是否开放学员手册");
			cfg.setWsId(wsId);
			cfg.setWsName(InitConfig.getWorkStationName(cfg.getWsId()));
			if (StringUtil.isBlank(cfg.getWsName())) {
				cfg.setWsName("全局公用配置");
			}
            cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			sysCfgList.add(cfg);
		}
		cfgBiz.save(sysCfgList);
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
}

