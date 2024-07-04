package com.pinde.res.ctrl.gydxj;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.PasswordUtil;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.gydxj.IGydxjAppBiz;
import com.pinde.res.enums.stdp.UserStatusEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.util.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/res/gydxj")
public class GydxjAppController {
	private static Logger logger = LoggerFactory.getLogger(GydxjAppController.class);
	@Autowired
	private IGydxjAppBiz appBiz;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/gydxj/500";
    }

	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/gydxj/version";
	}

	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(){
		return "res/gydxj/test";
	}

	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userCode)){
			model.addAttribute("resultId", "3010101");
			model.addAttribute("resultType", "用户名为空");
			return "res/gydxj/login";
		}
		if(StringUtil.isEmpty(userPasswd)){
			model.addAttribute("resultId", "3010102");
			model.addAttribute("resultType", "密码为空");
			return "res/gydxj/login";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.getUserByCode(userCode);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
		}else{
			String passwd = userinfo.getUserPasswd();
			String userFlow = userinfo.getUserFlow();
			//验证密码是否正确或者是否使用超级密码
			if(!PasswordUtil.isRootPass(userPasswd) && !passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(userFlow,userPasswd))){
				model.addAttribute("resultId", "3010104");
				model.addAttribute("resultType", "用户名或密码错误");
				return "res/gydxj/login";
			}
			//验证用户是否锁定,锁定用户不能登录
			String userStatus = userinfo.getStatusId();
			if(UserStatusEnum.Locked.getId().equals(userStatus)){
				model.addAttribute("resultId", "3010105");
				model.addAttribute("resultType", "用户已被锁定");
				return "res/gydxj/login";
			}
			//验证用户是否有角色
			List<SysUserRole> userRoles = appBiz.getSysUserRole(userFlow);
			if(userRoles==null || userRoles.isEmpty()){
				model.addAttribute("resultId", "3010106");
				model.addAttribute("resultType", "用户未赋权");
				return "res/gydxj/login";
			}

			boolean isDoctor = false;
			boolean isTeacher = false;
			boolean isAdmin = false;
			//多角色
			List<Map<String,String>> roles = new ArrayList<>();
			//获取当前配置的医师角色
			String doctorRole = appBiz.getCfgByCode("xjgl_student_role_flow");
			//授课老师角色
			String groupRole = appBiz.getCfgByCode("xjgl_group_role_flow");
			String tutorRole = appBiz.getCfgByCode("xjgl_tutor_role_flow");
			//管理员角色
			String adminRole = appBiz.getCfgByCode("xjgl_school_role_flow");
			//获取最先匹配到的角色,认定该用户的角色为匹配到的角色
			int inx = 0;
			boolean teaFlag = false;
			Map<String,String> mp = null;
			for(SysUserRole sur : userRoles){
				String ur = sur.getRoleFlow();
				if(doctorRole.equals(ur)){
					inx++;
					mp = new HashMap<>();
					mp.put("roleFlow",ur);
					mp.put("roleId","Student");
					mp.put("roleName","医师");
					roles.add(mp);
					if(inx==1){
						isDoctor = true;
						model.addAttribute("roleFlow",ur);
						model.addAttribute("roleId","Student");
						model.addAttribute("roleName","医师");
					}
				}else if(groupRole.equals(ur)||tutorRole.equals(ur)){
					inx++;
					mp = new HashMap<>();
					mp.put("roleFlow",ur);
					mp.put("roleId","Teacher");
					mp.put("roleName","授课老师");
					//授课组和导师都作为授课老师角色只加载一次
					if(!teaFlag){
						roles.add(mp);
						teaFlag = true;
					}
					if(inx==1){
						isTeacher = true;
						model.addAttribute("roleFlow",ur);
						model.addAttribute("roleId","Teacher");
						model.addAttribute("roleName","授课老师");
					}
				}else if(adminRole.equals(ur)){
					inx++;
					mp = new HashMap<>();
					mp.put("roleFlow",ur);
					mp.put("roleId","Admin");
					mp.put("roleName","管理员");
					roles.add(mp);
					if(inx==1){
						isAdmin = true;
						model.addAttribute("roleFlow",ur);
						model.addAttribute("roleId","Admin");
						model.addAttribute("roleName","管理员");
					}
				}
			}
			model.addAttribute("isDoctor", isDoctor);
			model.addAttribute("isTeacher", isTeacher);
			model.addAttribute("isAdmin", isAdmin);
			model.addAttribute("roles", roles);
			//如果是医师需要获取医师的一些培训信息
			if(isDoctor){
				//读取这个用户的医师信息
				ResDoctor doctor = appBiz.readResDoctor(userFlow);
				EduUser eduUser = appBiz.readEduUser(userFlow);
				if(doctor==null || eduUser==null){
					model.addAttribute("resultId", "3010107");
					model.addAttribute("resultType", "登录失败,学员数据出错");
					return "res/gydxj/login";
				}
				model.addAttribute("eduUser", eduUser);
				model.addAttribute("doctor", doctor);
			}else if(isTeacher){

			}else if(isAdmin){
				userFlow=null;
			}else{
				model.addAttribute("resultId", "3010109");
				model.addAttribute("resultType", "登录失败,无权登录");
				return "res/gydxj/login";
			}
			model.addAttribute("userinfo", userinfo);
			PageHelper.startPage(1, 3);
			List<EduTeachingnotice> noticeList = appBiz.searchByRoles(userFlow,"Science");
			model.addAttribute("noticeList",noticeList);
		}
		return "res/gydxj/login";
	}
	@RequestMapping(value={"/noticeList"},method={RequestMethod.POST})
	public String noticeList(String userFlow,String roleId,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if("Admin".equals(roleId)){
			userFlow=null;
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<EduTeachingnotice> noticeList = appBiz.searchByRoles(userFlow,"Science");
		model.addAttribute("noticeList",noticeList);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gydxj/noticeList";
	}
	@RequestMapping(value={"/noticeView"},method={RequestMethod.POST})
	public String noticeView(String infoFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		model.addAttribute("notice", appBiz.findNoticByFlow(infoFlow));
		String httpurl=request.getRequestURL().toString();
		String servletPath=request.getServletPath();
		model.addAttribute("jspUrl",httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/gydxj/noticeDetail.jsp?recordFlow="+infoFlow);
		return "res/gydxj/noticeView";
	}
	@RequestMapping(value={"/showNotice"},method ={ RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String showNotice(String infoFlow){
		Map<String,Object> map=new HashMap<>();
		EduTeachingnotice info = appBiz.findNoticByFlow(infoFlow);
		map.put("info",info);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value={"/changePwd"},method ={RequestMethod.POST})
	public String changePwd(String userFlow,String oldPwd,String newPwd,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		SysUser sysUser =appBiz.readSysUser(userFlow);
		String userPwd = PasswordHelper.encryptPassword(userFlow,oldPwd);
		if(!sysUser.getUserPasswd().equals(userPwd)){
			model.addAttribute("resultId", "3010107");
			model.addAttribute("resultType", "原密码错误");
			return "res/gydxj/changePwd";
		}
		sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), newPwd));
		appBiz.updateUser(sysUser);
		return "res/gydxj/changePwd";
	}
}

