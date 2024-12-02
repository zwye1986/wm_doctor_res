package com.pinde.sci.common;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.model.mo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception err)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		String getPath = request.getRequestURL().toString();
		if(StringUtil.isNotBlank(request.getQueryString()) ){
			getPath = getPath+"?"+request.getQueryString();
		}

		/*
		* 对线上请求的地址做处理
		* 地址可能为：https://js.ezhupei.com/jsres/teacher/index
		* 需要改成为：https://js.ezhupei.com/pdsci/jsres/teacher/index
		* */
		/*int count=0;
		char[] chars = getPath.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if ('/'==chars[i]){
				count=count+1;
				if (count==3 && chars.length>i+5){
					String sub = getPath.substring(i+1, i + 6);
					if (!sub.equals("pdsci")){
						getPath=getPath.substring(0,i+1)+"pdsci/"+getPath.substring(i+1,getPath.length());
						break;
					}
				}
			}
		}*/

		String servletPath = request.getServletPath();
		logger.debug("UserInterceptor handler......{}",getPath);

		if(isStaticResource(servletPath)) {
			return true;
		}

		if("get".equalsIgnoreCase(request.getMethod())){
			request.getSession().setAttribute("goPath", getPath);
		}
        SysUser sysUser = (SysUser) request.getSession().getAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER);
		if (sysUser == null) {
			request.getRequestDispatcher("/timeout").forward(request, response);
			return false;
		}

		if ("zwye1986".equals(sysUser.getUserCode())) {
			return true;
		}else if ("pdkj_yw".equals(sysUser.getUserCode())) {
			return true;
		}

		List<String> myMenus = new ArrayList<>();
//		List<String> allMenus = (List<String>) request.getServletContext().getAttribute("allMenus");


		String doctorAccessAuthority = (String) request.getServletContext().getAttribute("doctorAccessAuthority");//学员
		String teacherAccessAuthority = (String) request.getServletContext().getAttribute("teacherAccessAuthority");//带教老师
		String responsibleTeacherAccessAuthority = (String) request.getServletContext().getAttribute("responsibleTeacherAccessAuthority");//责任导师
		String secretaryAccessAuthority = (String) request.getServletContext().getAttribute("secretaryAccessAuthority");//科秘
		String teachingSecretaryAccessAuthority = (String) request.getServletContext().getAttribute("teachingSecretaryAccessAuthority");//教学秘书
		String headAccessAuthority = (String) request.getServletContext().getAttribute("headAccessAuthority");//科主任
		String schoolAccessAuthority = (String) request.getServletContext().getAttribute("schoolAccessAuthority");//学校
		String universityAccessAuthority = (String) request.getServletContext().getAttribute("universityAccessAuthority");//高校管理员
		String zygljAccessAuthority = (String) request.getServletContext().getAttribute("zygljAccessAuthority");//审核部门之中医管理局
		String bjwAuthority = (String) request.getServletContext().getAttribute("bjwAuthority");//审核部门之毕教委
		String qkzxAuthority = (String) request.getServletContext().getAttribute("qkzxAuthority");//审核部门之全科中心
		String instituteAuthority = (String) request.getServletContext().getAttribute("instituteAuthority");//研究所
		String nurseAuthority = (String) request.getServletContext().getAttribute("nurseAuthority");//护士
		String businessAuthority = (String) request.getServletContext().getAttribute("businessAuthority");//商务角色
		String maintenanceAuthority = (String) request.getServletContext().getAttribute("maintenanceAuthority");//运维角色
		String testAuthority = (String) request.getServletContext().getAttribute("testAuthority");//外省基地管理员角色
		String adminAccessAuthority = (String) request.getServletContext().getAttribute("adminAccessAuthority");//医院管理员//免费医院管理员
		String hospitalSecretaryAccessAuthority = (String) request.getServletContext().getAttribute("hospitalSecretaryAccessAuthority");//医院管理员//免费医院管理员
		String speAdminAccessAuthority = (String) request.getServletContext().getAttribute("speAdminAccessAuthority");//专业基地管理员
		String speAdminSecretaryAccessAuthority = (String) request.getServletContext().getAttribute("speAdminSecretaryAccessAuthority");//专业基地秘书
		String chargeAccessAuthority = (String) request.getServletContext().getAttribute("chargeAccessAuthority");//主管部门（市局）
		String globalAccessAuthority = (String) request.getServletContext().getAttribute("globalAccessAuthority");//省级部门
		String qualityAccessAuthority = (String) request.getServletContext().getAttribute("qualityAccessAuthority");//质控组
		String expertLeaderAccessAuthority = (String) request.getServletContext().getAttribute("expertLeaderAccessAuthority");//督导-专业专家
		String baseExpertAccessAuthority = (String) request.getServletContext().getAttribute("baseExpertAccessAuthority");//督导-专业基地专家
		String managementAccessAuthority = (String) request.getServletContext().getAttribute("managementAccessAuthority");//督导-管理专家
		String hospitalLeaderAccessAuthority = (String) request.getServletContext().getAttribute("hospitalLeaderAccessAuthority");//督导-评分专家  院级
		String phyAssAccessAuthority = (String) request.getServletContext().getAttribute("phyAssAccessAuthority");//师资-医师协会

        List<String> currRoleList = (List<String>) request.getSession().getAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST);
		if (currRoleList != null && currRoleList.size() > 0) {
			for (String roleFlow : currRoleList) {
				if (StringUtil.isNotBlank(roleFlow)) {
					if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//省级部门
						if (StringUtil.isNotBlank(globalAccessAuthority)) {
							myMenus.addAll(Arrays.asList(globalAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_quality_control_role_flow"))) {//医院管理员
						if (StringUtil.isNotBlank(qualityAccessAuthority)) {
							myMenus.addAll(Arrays.asList(qualityAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))) {//医院管理员
						if (StringUtil.isNotBlank(adminAccessAuthority)) {
							myMenus.addAll(Arrays.asList(adminAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_hospital_secretary_role_flow"))) {//医院秘书
						if (StringUtil.isNotBlank(hospitalSecretaryAccessAuthority)) {
							myMenus.addAll(Arrays.asList(hospitalSecretaryAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_professionalBase_admin_role_flow"))) {//专业基地管理员
						if (StringUtil.isNotBlank(speAdminAccessAuthority)) {
							myMenus.addAll(Arrays.asList(speAdminAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_professionalBase_adminSecretary_role_flow"))) {//专业基地秘书
						if (StringUtil.isNotBlank(speAdminSecretaryAccessAuthority)) {
							myMenus.addAll(Arrays.asList(speAdminSecretaryAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
						if (StringUtil.isNotBlank(doctorAccessAuthority)) {
							myMenus.addAll(Arrays.asList(doctorAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_charge_role_flow"))) {//主管部门（市局）
						if (StringUtil.isNotBlank(chargeAccessAuthority)) {
							myMenus.addAll(Arrays.asList(chargeAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_institute_role_flow"))) {//研究所
						if (StringUtil.isNotBlank(instituteAuthority)) {
							myMenus.addAll(Arrays.asList(instituteAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_qkzx_role_flow"))) {//审核部门之全科中心
						if (StringUtil.isNotBlank(qkzxAuthority)) {
							myMenus.addAll(Arrays.asList(qkzxAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_bjw_role_flow"))) {//审核部门之毕教委
						if (StringUtil.isNotBlank(bjwAuthority)) {
							myMenus.addAll(Arrays.asList(bjwAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_zyglj_role_flow"))) {//审核部门之中医管理局
						if (StringUtil.isNotBlank(zygljAccessAuthority)) {
							myMenus.addAll(Arrays.asList(zygljAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_university_role_flow"))
					|| roleFlow.equals(InitConfig.getSysCfg("res_university_son_role_flow"))) {//高校管理员
						if (StringUtil.isNotBlank(universityAccessAuthority)) {
							myMenus.addAll(Arrays.asList(universityAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_school_role_flow"))) {//学校
						if (StringUtil.isNotBlank(schoolAccessAuthority)) {
							myMenus.addAll(Arrays.asList(schoolAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_head_role_flow"))) {//科主任
						if (StringUtil.isNotBlank(headAccessAuthority)) {
							myMenus.addAll(Arrays.asList(headAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_secretary_role_flow"))) {//科秘
						if (StringUtil.isNotBlank(secretaryAccessAuthority)) {
							myMenus.addAll(Arrays.asList(secretaryAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_teaching_secretary_role_flow"))) {//教学秘书
						if (StringUtil.isNotBlank(teachingSecretaryAccessAuthority)) {
							myMenus.addAll(Arrays.asList(teachingSecretaryAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_teacher_role_flow"))) {//带教老师
						if (StringUtil.isNotBlank(teacherAccessAuthority)) {
							myMenus.addAll(Arrays.asList(teacherAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_responsible_teacher_role_flow"))) {//责任老师
						if (StringUtil.isNotBlank(responsibleTeacherAccessAuthority)) {
							myMenus.addAll(Arrays.asList(responsibleTeacherAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_nurse_role_flow"))) { //护士
						if (StringUtil.isNotBlank(nurseAuthority)) {
							myMenus.addAll(Arrays.asList(nurseAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_business_role_flow"))) { //商务一组角色
						if (StringUtil.isNotBlank(businessAuthority)) {
							myMenus.addAll(Arrays.asList(businessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_businessTwo_role_flow"))) { //商务二组角色
						if (StringUtil.isNotBlank(businessAuthority)) {
							myMenus.addAll(Arrays.asList(businessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_maintenance_role_flow"))) { //商务二组角色
						if (StringUtil.isNotBlank(businessAuthority)) {
							myMenus.addAll(Arrays.asList(maintenanceAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_expertLeader_role_flow"))) { //督导 专业专家
						if (StringUtil.isNotBlank(expertLeaderAccessAuthority)) {
							myMenus.addAll(Arrays.asList(expertLeaderAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_management_role_flow"))) { //督导 管理专家
						if (StringUtil.isNotBlank(managementAccessAuthority)) {
							myMenus.addAll(Arrays.asList(managementAccessAuthority.split(",")));
						}
					}else if (roleFlow.equals(InitConfig.getSysCfg("res_baseExpert_role_flow"))) { //督导 基地专业专家
						if (StringUtil.isNotBlank(baseExpertAccessAuthority)) {
							myMenus.addAll(Arrays.asList(baseExpertAccessAuthority.split(",")));
						}
					}else if (roleFlow.equals(InitConfig.getSysCfg("res_hospitalLeader_role_flow"))) { //督导 评分专家
						if (StringUtil.isNotBlank(hospitalLeaderAccessAuthority)) {
							myMenus.addAll(Arrays.asList(hospitalLeaderAccessAuthority.split(",")));
						}
					} else if (roleFlow.equals(InitConfig.getSysCfg("res_phyAss_role_flow"))) { //师资-医师协会
						if (StringUtil.isNotBlank(phyAssAccessAuthority)) {
							myMenus.addAll(Arrays.asList(phyAssAccessAuthority.split(",")));
						}
					}
					// 外省基地管理员角色
					else if (roleFlow.equals(InitConfig.getSysCfg("res_test"))) {
						if (StringUtil.isNotBlank(testAuthority)) {
							myMenus.addAll(Arrays.asList(testAuthority.split(",")));
						}
					}
				}
			}
		}
		if (myMenus.contains(servletPath)) {
			return true;
		}

		for (String menuUrl : myMenus) {
			if (servletPath.startsWith(menuUrl) || menuUrl.startsWith(servletPath)) {
				return true;
			}
		}

		/*boolean isAllow = true;

		if (allMenus.contains(servletPath)) {
			isAllow = false;
		}

		for (String menuUrl : allMenus) {
			if (servletPath.startsWith(menuUrl) || menuUrl.startsWith(servletPath)) {
				isAllow = false;
			}
		}

		if (!isAllow) {
			response.setStatus(403);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print("系统未授权");
			return false;
		}

		return true;*/


		response.setStatus(403);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print("系统未授权");
		return false;
	}

	private boolean isStaticResource(String path) {
		return path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".bmp") || path.endsWith(".gif") || path.endsWith(".js") || path.endsWith(".css");
	}

}
