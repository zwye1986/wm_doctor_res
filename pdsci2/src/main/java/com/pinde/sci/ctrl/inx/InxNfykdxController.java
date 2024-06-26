package com.pinde.sci.ctrl.inx;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.model.mo.SysCfg;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/inx/nfykdx")
public class InxNfykdxController extends GeneralController{

	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IInxBiz inxBiz;
	
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(Model model){
		//导师注册时间
		SysCfg start=cfgBiz.read("tutor_register_start_time");
		SysCfg end=cfgBiz.read("tutor_register_end_time");
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		return "inx/nfykdx/index";
	}

	/**
	 * 验证身份证
	 */
	@RequestMapping(value="/checkIdNo")
	@ResponseBody
	public String checkIdNo(String idNo){
		if(StringUtil.isNotBlank(idNo)){
			idNo = idNo.trim();
			SysUser user = userBiz.findByIdNo(idNo);
			if (user != null) {
				return GlobalConstant.USER_ID_NO_REPETE;
			}
		}
		return "";
	}

	/**
	 * 导师注册
	 */
	@RequestMapping("/saveRegister")
	public String saveRegister(SysUser registerUser , String verifyCode, Model model){
		//注册信息校验
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCode"));
		String errorMsg = "";
		if (StringUtil.isBlank(verifyCode) || StringUtil.isBlank(sessionValidateCode) || !sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			errorMsg = SpringUtil.getMessage("validateCode.notEquals");
			model.addAttribute("errorMsg", errorMsg);
			return "inx/nfykdx/register";
		}
		//判断用户身份证号是否重复
		SysUser user = userBiz.findByIdNo(registerUser.getIdNo());
		if (user != null) {
			model.addAttribute("errorMsg", GlobalConstant.USER_ID_NO_REPETE);
			return "inx/nfykdx/register";
		}
		String rltStr = inxBiz.saveTutorUser(registerUser);
		if(StringUtil.isNotBlank(rltStr)){
			model.addAttribute("errorMsg", rltStr);
			return "inx/nfykdx/register";
		}
		return "inx/nfykdx/registerSucc";
	}
}
