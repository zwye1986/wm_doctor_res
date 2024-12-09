package com.pinde.sci.biz.inx.impl;

import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.VelocityUtil;
import com.pinde.sci.biz.inx.IInxHbresBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.SysUserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
//@Transactional(rollbackFor=Exception.class)
public class InxHbresBizImpl implements IInxHbresBiz{
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IMsgBiz msgBiz;

	private static Logger logger = LoggerFactory.getLogger(InxHbresBizImpl.class);


	@Override
	public int registerUser(SysUser user) {
		user.setUserFlow(PkUtil.getUUID());
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), user.getUserPasswd()));
		user.setStatusId(UserStatusEnum.Added.getId());
		user.setStatusDesc(UserStatusEnum.Added.getName());
		
		if(StringUtil.isNotBlank(InitConfig.getSysCfg("res_doctor_role_flow"))){
			SysUserRole userRole = new SysUserRole();
			userRole.setUserFlow(user.getUserFlow());
			//userRole.setOrgFlow(user.getOrgFlow());
            String currWsId = com.pinde.core.common.GlobalConstant.RES_WS_ID;
			userRole.setWsId(currWsId);
			userRole.setRoleFlow(InitConfig.getSysCfg("res_doctor_role_flow"));
			userRole.setAuthTime(DateUtil.getCurrDate());
			//userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			userRoleBiz.saveSysUserRole(userRole);
		}
		return userBiz.insertUser(user);	
	}

	@Override
	public void sendEmail(String userEmail,String userFlow) {
		if(StringUtil.isNotBlank(userEmail)){
			String activationCode = userFlow;//激活码
			String content = InitConfig.getSysCfg("res_reg_email_content");
			String title = InitConfig.getSysCfg("res_reg_email_title");
			Map<String,String> dataMap = new HashMap<String,String>();
			dataMap.put("linkUrl", "<a href='"+InitConfig.getSysCfg("res_effective_url")+"?sid="+GlobalContext.getSession().getId()+"&activationCode="+activationCode+"'>"+InitConfig.getSysCfg("res_effective_url")+"?sid="+GlobalContext.getSession().getId()+"&activationCode="+activationCode+"</a>");
			dataMap.put("linkEmail",userEmail);
			try {
//				title = VelocityUtil.evaluate(title, dataMap);
//				content = VelocityUtil.evaluate(content, dataMap);
			} catch (Exception e) {
				logger.error("", e);
				throw new RuntimeException();
			}
			SysUser sysUser = new SysUser();
			sysUser.setUserFlow(userFlow);
			userBiz.updateUser(sysUser);
//			this.msgBiz.addEmailMsg(userEmail, title, content);
		}
	}
	
	@Override
	public void sendResetPassEmail(String userEmail,String userFlow) {
		if(StringUtil.isNotBlank(userEmail)){
			String actionId = userFlow;
			String content = InitConfig.getSysCfg("res_resetpasswd_email_content");
			Map<String,String> dataMap = new HashMap<String,String>();
			dataMap.put("linkUrl", "<a href='"+InitConfig.getSysCfg("res_resetpasswd_url")+"?sid="+GlobalContext.getSession().getId()+"&actionId="+actionId+"'>"+InitConfig.getSysCfg("res_resetpasswd_url")+"?sid="+GlobalContext.getSession().getId()+"&actionId="+actionId+"</a>");
			dataMap.put("linkEmail",userEmail);
			try {
				content = VelocityUtil.evaluate(content, dataMap);
			} catch (Exception e) {
				logger.error("", e);
				throw new RuntimeException();
			}
			this.msgBiz.addEmailMsg(userEmail, InitConfig.getSysCfg("res_resetpasswd_email_title"), content);
		}
	}
}
