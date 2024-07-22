package com.pinde.sci.ctrl.inx;


import com.neusoft.education.tp.sso.client.CASReceipt;
import com.neusoft.education.tp.sso.client.filter.CASFilter;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.OperTypeEnum;
import com.pinde.sci.model.mo.SysLog;
import com.pinde.sci.model.mo.SysRole;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/inx/gzykdx")
public class InxGzykdxController extends GeneralController{
	
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private SysLogMapper logMapper;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private ILoginBiz loginBiz;
	
	@RequestMapping(value="index",method={RequestMethod.GET})
	public String indexJsp(Model model, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		CASReceipt receipt = (CASReceipt)session.getAttribute(CASFilter.SSO_FILTER_RECEIPT);
		if(receipt == null){
			System.err.println("你尚未登录！");
			return "redirect:/inx/gzykdx/logout";
		} else {
			System.err.println("用户名：" + receipt.getUserName());
			System.err.println("<br>");
			System.err.println("属性：" + receipt.getUserMap());
			System.err.println("<br>");
			System.err.println("机构：" + receipt.getOrganizationList());
			System.err.println("<br>");
			System.err.println("权限：" + receipt.getPrivilegeList());
			return "/inx/gzykdx/cas";
		}
	}
	@RequestMapping(value="logout",method={RequestMethod.GET})
	public String logout(Model model, HttpServletRequest request, HttpServletResponse response){
		HttpSession httpsession = request.getSession();
		httpsession.invalidate();
		request.getSession().invalidate();
		return "redirect:/inx/gzykdx";
	}
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(Model model, HttpServletRequest request, HttpServletResponse response){
		String errorLoginPage="/inx/gzykdx/error/error";
		HttpSession session = request.getSession();
		CASReceipt receipt = (CASReceipt)session.getAttribute(CASFilter.SSO_FILTER_RECEIPT);
		if(receipt == null){
			System.err.println("你尚未登录！");
			return "/inx/gzykdx/logout";
		} else {
			String userCode=receipt.getUserName();
			SysUser user = userBiz.findByUserCode(userCode);
			if(user==null)
			{
				model.addAttribute("HaveUser","N");
				model.addAttribute("loginErrorMessage","用户信息不存在，请联系系统管理员！");
				return errorLoginPage;
			}else{
				if(UserStatusEnum.Activated.getId().equals(user.getStatusId())){
					//审核通过
					SysUserRole userRole = new SysUserRole();
					userRole.setUserFlow(user.getUserFlow());
					List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
					if(userRoleList==null || userRoleList.size()==0){
						request.getSession().removeAttribute(GlobalConstant.CURRENT_USER);
						model.addAttribute("loginErrorMessage","用户角色未赋权，请联系系统管理员!");
						return errorLoginPage;
					}else {
						SysRole role = roleBiz.read(userRoleList.get(0).getRoleFlow());
						if(role==null){
							request.getSession().removeAttribute(GlobalConstant.CURRENT_USER);
							model.addAttribute("loginErrorMessage","用户角色未赋权，请联系系统管理员!");
							return errorLoginPage;
						}
						//只限制招录系统中的学员及二级机构不走单点登录
						String secondRoleFlow=InitConfig.getSysCfg("gzykdx_second_role_flow");
						String teacherRoleFlow=InitConfig.getSysCfg("gzykdx_teacher_role_flow");
						String doctorRoleFlow=InitConfig.getSysCfg("gzykdx_doctor_role_flow");
							String f = "N";
							for (SysUserRole sysRole : userRoleList) {

								if(StringUtil.isNotBlank(doctorRoleFlow)) {
									if (doctorRoleFlow.equals(sysRole.getRoleFlow())) {
										f = "Y";
										break;
									}
								}
								if(StringUtil.isNotBlank(secondRoleFlow)) {
									if (secondRoleFlow.equals(sysRole.getRoleFlow())) {
										f = "Y";
										break;
									}
								}
							}
							if("Y".equals(f))
							{
								model.addAttribute("loginErrorMessage","你无权限登陆!");
								return errorLoginPage;
							}

					}
				}else {
					model.addAttribute("loginErrorMessage","用户未激活，无法登陆!");
					return errorLoginPage;
				}
				//设置当前用户
				setSessionAttribute(GlobalConstant.CURRENT_USER, user);
				if(GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())){
					return "redirect:/main?time="+new Date();
				}
				//加载系统权限
				loginBiz.loadSysRole(user.getUserFlow());

				//在线用户功能使用
				SessionData sessionData = new SessionData();
				sessionData.setSysUser(user);
				sessionData.setIp(request.getRemoteAddr());
				long now = System.currentTimeMillis();
				String loginTime = new java.sql.Date(now)+"&nbsp;"+new java.sql.Time(now);
				sessionData.setLoginTime(loginTime);
				setSessionAttribute(SessionData.SESSIONDATAID,sessionData);
				//记录日志
				SysLog log = new SysLog();
				//log.setReqTypeId(ReqTypeEnum.GET.getId());
				log.setOperId(OperTypeEnum.LogIn.getId());
				log.setOperName(OperTypeEnum.LogIn.getName());
				log.setLogDesc("登录IP["+request.getRemoteAddr()+"]");
				log.setWsId(GlobalConstant.SYS_WS_ID);
				GeneralMethod.addSysLog(log);
				logMapper.insert(log);
			}
			return "redirect:/main?time="+new Date();
		}
	}
	
}
