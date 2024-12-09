package com.pinde.sci.biz.inx.impl;

import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IGateUserManageBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.dao.base.TjDocinfoMapper;
import com.pinde.sci.dao.sys.SysUserExtMapper;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class GateUserManageBizImpl implements IGateUserManageBiz {

	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysUserExtMapper userExtMapper;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
    @Autowired
    private TjDocinfoMapper docinfoMapper;

	@Override
	public List<Map<String, Object>> userList(Map<String, Object> params) {
		return userExtMapper.getGateUserList(params);
	}

	@Override
	public Map<String, Object> getUserRoles(String userFlow) {
		return userExtMapper.getUserRoles(userFlow);
	}

	@Override
	public void saveUser(SysUser user, String[] roleFlows) {
		if(StringUtil.isNotBlank(user.getUserFlow()))
		{
			GeneralMethod.setRecordInfo(user,false);
			userMapper.updateByPrimaryKeySelective(user);
		}else{
			user.setUserFlow(PkUtil.getUUID());
			user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), InitPasswordUtil.getInitPass()));
			user.setStatusId(UserStatusEnum.Activated.getId());
			GeneralMethod.setRecordInfo(user,true);
			userMapper.insertSelective(user);
		}
		userExtMapper.deleteGateRole(user,roleFlows);
		for(String roleFlow:roleFlows)
		{	SysUserRole userRole=null;
			if(StringUtil.isNotBlank(user.getUserFlow())) {
				userRole=userRoleBiz.readUserRole(user.getUserFlow(), roleFlow);
			}
			if(userRole==null)
				userRole=new SysUserRole();
            userRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			userRole.setUserFlow(user.getUserFlow());
			userRole.setRoleFlow(roleFlow);
            userRole.setWsId(com.pinde.core.common.GlobalConstant.RES_WS_ID);
			userRole.setAuthTime(DateUtil.getCurrDateTime());
			userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			userRoleBiz.saveSysUserRole(userRole);
		}
	}
}
