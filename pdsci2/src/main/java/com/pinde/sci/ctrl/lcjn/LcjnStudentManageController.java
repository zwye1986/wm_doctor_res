package com.pinde.sci.ctrl.lcjn;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.lcjn.ILcjnStudentBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.model.mo.SysCfg;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lcjn/studentManage")
public class LcjnStudentManageController extends GeneralController {
	@Autowired
	private ILcjnStudentBiz studentBiz;
	@Autowired
	private IUserBiz userBiz;

	@RequestMapping("/list")
	public String list(SysUser user, Integer currentPage, HttpServletRequest request, Model model){
		Map<String,String> paramMap = new HashMap<>();
		SysCfg cfg = cfgBiz.read("lcjn_doctor_role_flow");
		String doctorRole ="";
		if (null != cfg) {
			doctorRole = cfg.getCfgValue();
			if(!StringUtil.isNotBlank(doctorRole)){
				doctorRole = cfg.getCfgBigValue();
			}
		}
//		paramMap.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
		paramMap.put("roleFlow",doctorRole);
		paramMap.put("userCode",user.getUserCode());
		paramMap.put("userName",user.getUserName());
		paramMap.put("isOwnerStu",user.getIsOwnerStu());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SysUser> students = studentBiz.searchStudent(paramMap);
		model.addAttribute("students",students);
		return "lcjn/studentManage/list";
	}


	 //新增、编辑学员信息界面
	@RequestMapping(value="/edit")
	public String editTeaInfo(String userFlow, Model model){
		if(StringUtil.isNotBlank(userFlow)){
			SysUser sysUser = userBiz.readSysUser(userFlow);
			model.addAttribute("sysUser",sysUser);
		}
		return "/lcjn/studentManage/edit";
	}

	//保存学员信息
	@RequestMapping(value="/save")
	@ResponseBody
	public int save(SysUser user,String resetCode){
		if("resetCode".equals(resetCode)){
			user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(),"123456"));
		}
		return studentBiz.editStudent(user);
	}

	//学员信息导入
	@RequestMapping(value="/importStudentExcel")
	@ResponseBody
	public String importStudentExcel(MultipartFile file){
		if(file.getSize() > 0){
			try{
				int result = studentBiz.importStudentExcel(file);
				if(GlobalConstant.ZERO_LINE != result){
					return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
				}else{
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(RuntimeException re) {
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

}