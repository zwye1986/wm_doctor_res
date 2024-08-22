package com.pinde.sci.ctrl.cfg;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.sch.ISchManualBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.JsresPowerCfg;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.sci.model.mo.SysUser;
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
@RequestMapping("/jsres/teacherCfg")
public class JsresTeacherCfgController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(JsresTeacherCfgController.class);
	
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private ISchManualBiz schManualBiz;
	@Autowired
	private IUserBiz userBiz;

	@RequestMapping(value = {"/main" }, method = RequestMethod.GET)
	public String main () throws Exception{
		return "jsres/cfg/teacherCfg/main";
	}
	@RequestMapping(value = {"/userList" })
	public String userList (Model model,Integer currentPage,HttpServletRequest request,
							String orgFlow,String deptFlow,String userName,String userCode,String checkStatusId	) throws Exception{
		Map<String,Object> params=new HashMap<>();
		String roleFlow= InitConfig.getSysCfg("res_teacher_role_flow");
		params.put("roleFlow",roleFlow);
		params.put("orgFlow",orgFlow);
		params.put("deptFlow",deptFlow);
		params.put("userName",userName);
		params.put("userCode",userCode);
		params.put("checkStatusId",checkStatusId);
		if(currentPage==null){
			currentPage=1;
		}
		if(StringUtil.isNotBlank(roleFlow)) {
			PageHelper.startPage(currentPage, getPageSize(request));
			List<Map<String, Object>> list = schManualBiz.teaList(params);
			model.addAttribute("list", list);
		}
		return "jsres/cfg/teacherCfg/userList";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(HttpServletRequest request, String[] userFlows, String recordStatus,String cfg) {
		List<JsresPowerCfg> cfgList = new ArrayList<JsresPowerCfg>();
		for (String userFlow : userFlows) {
			String cfgCode = cfg + userFlow;
			JsresPowerCfg c = new JsresPowerCfg();
			c.setCfgCode(cfgCode);
			c.setCfgValue(recordStatus);
			c.setCfgDesc("是否开放带教app权限");
			c.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			cfgList.add(c);
		}
		int count = jsResPowerCfgBiz.saveList(cfgList);
		if(count > 0){
			if(GlobalConstant.RECORD_STATUS_N.equals(recordStatus)){
				List<String> flowList = Arrays.asList(userFlows);
				if(null != flowList && flowList.size()>0){
					for (String userFlow:flowList) {
						SysUser user = new SysUser();
						user.setUserFlow(userFlow);
						user.setCheckStatusId("");
						user.setCheckStatusName("");
						JsresPowerCfg jpc = jsResPowerCfgBiz.read("jsres_teacher_app_login_"+userFlow);
						if(null != jpc && "Y".equals(jpc.getCfgValue())){
							user.setIsSubmitId("NotSubmit");
							user.setIsSubmitName("未提交");
						}else{
							user.setIsSubmitId("");
							user.setIsSubmitName("");
						}
						userBiz.updateUser(user);
					}
					return GlobalConstant.SAVE_SUCCESSED;
				}
				return GlobalConstant.SAVE_FAIL;
			}
			userBiz.updateTeaNotSubmit(Arrays.asList(userFlows));
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	@RequestMapping(value="/queryDeptListJson")
	@ResponseBody
	public List<SysDept> queryDeptListJson(String orgFlow){
		return queryDeptList(orgFlow);
	}
	private List<SysDept> queryDeptList(String orgFlow) {
		List<SysDept> list = null;
		if(StringUtil.isNotBlank(orgFlow)){
			SysDept sysDept = new SysDept();
			sysDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			sysDept.setOrgFlow(orgFlow);
			list = this.deptBiz.searchDept(sysDept);
		}
		return list;
	}

	@RequestMapping(value="/saveOne",method= RequestMethod.POST)
	@ResponseBody
	public String saveOne(String userFlow,HttpServletRequest request){
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
				SysUser user = new SysUser();
				user.setUserFlow(userFlow);
				user.setCheckStatusId("");
				user.setCheckStatusName("");
				JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_teacher_app_login_"+userFlow);
				if(null != cfg && "Y".equals(cfg.getCfgValue())){
					user.setIsSubmitId("NotSubmit");
					user.setIsSubmitName("未提交");
				}else{
					user.setIsSubmitId("");
					user.setIsSubmitName("");
				}
				userBiz.updateUser(user);
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}
}

