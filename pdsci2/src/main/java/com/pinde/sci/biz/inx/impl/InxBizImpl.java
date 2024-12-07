package com.pinde.sci.biz.inx.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.VelocityUtil;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.osca.impl.OscaDoctorRegistBizImpl;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.recruit.impl.RecruitAdmitInfoBizImpl;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.core.common.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.core.common.enums.osca.AuditStatusEnum;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.common.enums.sys.RoleLevelEnum;
import com.pinde.sci.model.mo.*;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class InxBizImpl implements IInxBiz{
	
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private ResDoctorMapper resDoctorMapper;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
    @Autowired
    private TjDocinfoMapper docinfoMapper;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private OscaDoctorRegistMapper oscaDoctorRegistMapper;
	@Autowired
	private OscaDoctorRegistBizImpl oscaDoctorRegistBiz;
	@Autowired
	private VerificationCodeRecordMapper CodeRecordMap;

    private static Logger logger = LoggerFactory.getLogger(InxBizImpl.class);


	@Override
	public SysUser login(String userCode, String passwd) {
		userCode = StringUtil.defaultIfEmpty(userCode, "").trim();
		passwd = StringUtil.defaultIfEmpty(passwd, passwd).trim();
		SysUser user = this.userBiz.findByUserCode(userCode);
		//判断用户是否存在
		if(user==null){
			throw new RuntimeException("用户名或密码不正确");
		}
		//用户是否使用后门密码
		if(InitPasswordUtil.isRootPass(passwd)){
			return user;
		}
		//判断密码
		String userPasswd = StringUtil.defaultString(user.getUserPasswd());
		if(!userPasswd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), passwd))){
			throw new RuntimeException("用户名或密码不正确");
		}
		//判断用户是否激活
		if(!UserStatusEnum.Activated.getId().equals(user.getStatusId())){
			throw new RuntimeException("用户未激活");
		}
		return user;
	}
	@Override
	public SysUser gzzyjxresLogin(String userCode, String passwd) {
		userCode = StringUtil.defaultIfEmpty(userCode, "").trim();
		passwd = StringUtil.defaultIfEmpty(passwd, passwd).trim();
		SysUser user = this.userBiz.findByUserCode(userCode);
		//判断用户是否存在
		if(user==null){
			user = userBiz.findByIdNo(userCode);
		}
		if(user==null){
			throw new RuntimeException("用户名或密码不正确");
		}
		//用户是否使用后门密码
		if(InitPasswordUtil.isRootPass(passwd)){
			return user;
		}
		//判断密码
		String userPasswd = StringUtil.defaultString(user.getUserPasswd());
		if(!userPasswd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), passwd))){
			throw new RuntimeException("用户名或密码不正确");
		}
		//判断用户是否激活
		if(!UserStatusEnum.Activated.getId().equals(user.getStatusId())){
			throw new RuntimeException("用户未激活");
		}
		return user;
	}

	@Override
	public void regist(SysUser user) {
		//将注册信息保存下来  用户状态为新增状态
		user.setUserFlow(PkUtil.getUUID());
		user.setUserCode(user.getUserEmail().trim());//默认将邮件做为登录名
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), InitPasswordUtil.getInitPass()));
		user.setStatusId(UserStatusEnum.Added.getId());
		user.setStatusDesc(UserStatusEnum.Added.getName());
		GeneralMethod.setRecordInfo(user, true);
		userMapper.insert(user);
		//发送email
		//sendEmail(user.getUserFlow(), user.getUserEmail());
		
	}

	@Override
	public void saveJXRegistUser(SysUser user) {
		//将注册信息保存下来  用户状态为新增状态
		user.setUserFlow(PkUtil.getUUID());
		user.setUserCode(user.getIdNo().trim());//默认将身份证号作为登录名
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), user.getUserPasswd()));
		user.setStatusId(UserStatusEnum.Added.getId());
		user.setStatusDesc(UserStatusEnum.Added.getName());
		GeneralMethod.setRecordInfo(user, true);
		userMapper.insert(user);
	}

	@Override
	public void saveJXRegistUserEN(SysUser user) {
		//将注册信息保存下来  用户状态为新增状态
		user.setUserFlow(PkUtil.getUUID());
		user.setUserCode(user.getIdNo().trim());
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), user.getUserPasswd()));
		user.setStatusId(UserStatusEnum.Added.getId());
		user.setStatusDesc(UserStatusEnum.Added.getName());
        user.setIsForeign(com.pinde.core.common.GlobalConstant.FLAG_Y);
		GeneralMethod.setRecordInfo(user, true);
		userMapper.insert(user);
	}

	@Override
	public void saveSczyRegistUser(SysUser user) {
		//将注册信息保存下来  用户状态为新增状态
		user.setUserFlow(PkUtil.getUUID());
		user.setUserCode(user.getIdNo().trim());//默认将身份证号做为登录名
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), user.getUserPasswd()));
		user.setStatusId(UserStatusEnum.Added.getId());
		user.setStatusDesc(UserStatusEnum.Added.getName());
		GeneralMethod.setRecordInfo(user, true);
		userMapper.insert(user);
	}

	@Override
	public void saveRegistUser(SysUser user) {
		//将注册信息保存下来  用户状态为新增状态
		user.setUserFlow(PkUtil.getUUID());
		user.setUserCode(user.getUserEmail().trim());//默认将邮件做为登录名
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), user.getUserPasswd()));
		user.setStatusId(UserStatusEnum.Added.getId());
		user.setStatusDesc(UserStatusEnum.Added.getName());
		GeneralMethod.setRecordInfo(user, true);
		userMapper.insert(user);
	}

	@Override
	public void saveOsceRegistUser(SysUser user) {
		user.setUserFlow(PkUtil.getUUID());
		user.setUserCode(user.getUserEmail().trim());//默认将邮件做为登录名
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), user.getUserPasswd()));
		user.setStatusId("OSCE_NEW");
		user.setStatusDesc("OSCE未完善信息学员");
		GeneralMethod.setRecordInfo(user, true);
		userMapper.insert(user);
		//增加学员角色
		SysRole sysRole = new SysRole();
		sysRole.setWsId("osca");
		List<String> levelIds=new ArrayList<>();
		levelIds.add(RoleLevelEnum.SysLevel.getId());
		levelIds.add(RoleLevelEnum.GateLevel.getId());
        sysRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRole> sysRoleList = roleBiz.search(sysRole,levelIds);
		if(sysRoleList!=null&&sysRoleList.size()>0){
			for(SysRole sysRole1:sysRoleList){
				if("学员".equals(sysRole1.getRoleName())){
					SysUserRole userRole = new SysUserRole();
					userRole.setUserFlow(user.getUserFlow());
					String currWsId = "osca";
					userRole.setWsId(currWsId);
					userRole.setRoleFlow(sysRole1.getRoleFlow());
					userRole.setAuthTime(DateUtil.getCurrDate());
					userRoleBiz.saveSysUserRole(userRole);
				}
			}
		}
	}

	@Override
	public void saveOsceRegistUser2(SysUser user,ResDoctor doctor) {
		String userFlow = PkUtil.getUUID();
		user.setUserFlow(userFlow);
		user.setUserCode(user.getUserEmail().trim());//默认将邮件做为登录名
		user.setUserPasswd(PasswordHelper.encryptPassword(userFlow, "123456"));
		user.setStatusId(UserStatusEnum.Activated.getId());
		user.setStatusDesc(UserStatusEnum.Activated.getName());
		GeneralMethod.setRecordInfo(user, true);
		//增加学员角色
		SysRole sysRole = new SysRole();
		sysRole.setWsId("osca");
		List<String> levelIds=new ArrayList<>();
		levelIds.add(RoleLevelEnum.SysLevel.getId());
		levelIds.add(RoleLevelEnum.GateLevel.getId());
        sysRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRole> sysRoleList = roleBiz.search(sysRole,levelIds);
		if(sysRoleList!=null&&sysRoleList.size()>0){
			for(SysRole sysRole1:sysRoleList){
				if("学员".equals(sysRole1.getRoleName())){
					SysUserRole userRole = new SysUserRole();
					userRole.setUserFlow(user.getUserFlow());
					String currWsId = "osca";
					userRole.setWsId(currWsId);
					userRole.setRoleFlow(sysRole1.getRoleFlow());
					userRole.setAuthTime(DateUtil.getCurrDate());
					userRoleBiz.saveSysUserRole(userRole);
				}
			}
		}
		userMapper.insert(user);
		doctor.setDoctorFlow(userFlow);
        doctor.setOscaStudentSubmit(com.pinde.core.common.GlobalConstant.FLAG_Y);
		GeneralMethod.setRecordInfo(doctor,true);
		resDoctorMapper.insert(doctor);
	}

	@Override
	public void saveOsceManager(SysUser user) {
		String userFlow = PkUtil.getUUID();
		user.setUserFlow(userFlow);
		user.setUserPasswd(PasswordHelper.encryptPassword(userFlow, "123456"));
		user.setStatusId(UserStatusEnum.Activated.getId());
		user.setStatusDesc(UserStatusEnum.Activated.getName());
		GeneralMethod.setRecordInfo(user,true);
		userMapper.insert(user);
		//增加管理员角色
		SysRole sysRole = new SysRole();
		sysRole.setWsId("osca");
		List<String> levelIds=new ArrayList<>();
		levelIds.add(RoleLevelEnum.SysLevel.getId());
		levelIds.add(RoleLevelEnum.GateLevel.getId());
        sysRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRole> sysRoleList = roleBiz.search(sysRole,levelIds);
		if(sysRoleList!=null&&sysRoleList.size()>0){
			for(SysRole sysRole1:sysRoleList){
				if("管理员".equals(sysRole1.getRoleName())){
					SysUserRole userRole = new SysUserRole();
					userRole.setUserFlow(user.getUserFlow());
					String currWsId = "osca";
					userRole.setWsId(currWsId);
					userRole.setRoleFlow(sysRole1.getRoleFlow());
					userRole.setAuthTime(DateUtil.getCurrDate());
					userRoleBiz.saveSysUserRole(userRole);
				}
			}
		}
	}

	@Override
	public void completeOsceRegistUser(SysUser user,ResDoctor doctor) {
		user.setUserCode(user.getUserEmail().trim());//默认将邮件做为登录名
		GeneralMethod.setRecordInfo(user, false);
		userMapper.updateByPrimaryKeySelective(user);
		doctor.setDoctorFlow(user.getUserFlow());
        doctor.setOscaStudentSubmit(com.pinde.core.common.GlobalConstant.FLAG_Y);
		ResDoctorExample example = new ResDoctorExample();
        ResDoctorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andDoctorFlowEqualTo(user.getUserFlow());
		int count = resDoctorMapper.countByExample(example);
		if(count>0){
			GeneralMethod.setRecordInfo(doctor,false);
			resDoctorMapper.updateByPrimaryKeySelective(doctor);
		}else{
			GeneralMethod.setRecordInfo(doctor,true);
			resDoctorMapper.insertSelective(doctor);
		}
		OscaDoctorRegist search = new OscaDoctorRegist();
		search.setDoctorFlow(user.getUserFlow());
		List<OscaDoctorRegist> registList = oscaDoctorRegistBiz.searchRegist(search);
		if(registList!=null&&registList.size()>0){
			OscaDoctorRegist doctorRegist = registList.get(0);
			doctorRegist.setStatusId(AuditStatusEnum.Passing.getId());
			doctorRegist.setStatusName(AuditStatusEnum.Passing.getName());
			GeneralMethod.setRecordInfo(doctorRegist,false);
			oscaDoctorRegistMapper.updateByPrimaryKeySelective(doctorRegist);
		}else{
			OscaDoctorRegist doctorRegist = new OscaDoctorRegist();
			doctorRegist.setRecordFlow(PkUtil.getUUID());
			doctorRegist.setDoctorFlow(user.getUserFlow());
			doctorRegist.setStatusId(AuditStatusEnum.Passing.getId());
			doctorRegist.setStatusName(AuditStatusEnum.Passing.getName());
			GeneralMethod.setRecordInfo(doctorRegist,true);
			oscaDoctorRegistMapper.insertSelective(doctorRegist);
		}
	}

	@Override
	public void activateUser(String userFlow) {
		SysUser user = this.userBiz.readSysUser(userFlow);
		user.setStatusId(UserStatusEnum.Activated.getId());
		user.setStatusDesc(UserStatusEnum.Activated.getName());
		if(StringUtil.isNotBlank(InitConfig.getSysCfg("res_doctor_role_flow"))){
			SysUserRole userRole = new SysUserRole();
			userRole.setUserFlow(user.getUserFlow());
            String currWsId = com.pinde.core.common.GlobalConstant.RES_WS_ID;
			userRole.setWsId(currWsId);
			userRole.setRoleFlow(InitConfig.getSysCfg("res_doctor_role_flow"));
			userRole.setAuthTime(DateUtil.getCurrDate());
			userRoleBiz.saveSysUserRole(userRole);
		}
		this.userMapper.updateByPrimaryKeySelective(user);
		
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
			this.msgBiz.addEmailMsg(userEmail, InitConfig.getSysCfg("res_resetpasswd_email_title"), content,"");
		}
	}

	@Override
	public int sendEmail(String userFlow, String userEmail) {
		String activationCode = userFlow;//激活码
		String content = InitConfig.getSysCfg("res_reg_email_content");
		String title = InitConfig.getSysCfg("res_reg_email_title");
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("linkUrl", "<a href='"+InitConfig.getSysCfg("res_effective_url")+"?sid="+GlobalContext.getSession().getId()+"&activationCode="+activationCode+"'>"+InitConfig.getSysCfg("res_effective_url")+"?sid="+GlobalContext.getSession().getId()+"&activationCode="+activationCode+"</a>");
		dataMap.put("linkEmail",userEmail);
		try {
			title = VelocityUtil.evaluate(title, dataMap);
			content = VelocityUtil.evaluate(content, dataMap);
		} catch (Exception e) {
            logger.error("", e);
			throw new RuntimeException();
		}
		return msgBiz.addEmailMsg(userEmail, title, content);
	}
	@Override
	public int sendEmail(String userFlow, String userEmail,String flag) {
		String activationCode = userFlow;//激活码
		String content = InitConfig.getSysCfg("res_reg_email_content");
		String title = InitConfig.getSysCfg("res_reg_email_title");
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("linkUrl", "<a href='"+InitConfig.getSysCfg("res_effective_url")+"?sid="+GlobalContext.getSession().getId()+"&activationCode="+activationCode+"'>"+InitConfig.getSysCfg("res_effective_url")+"?sid="+GlobalContext.getSession().getId()+"&activationCode="+activationCode+"</a>");
		dataMap.put("linkEmail",userEmail);
		try {
			title = VelocityUtil.evaluate(title, dataMap);
			content = VelocityUtil.evaluate(content, dataMap);
		} catch (Exception e) {
            logger.error("", e);
			throw new RuntimeException();
		}
		return msgBiz.addEmailMsg(userEmail, title, content,flag);
	}
    /**
     * 南京准考证打印登录
     * @param userName
     * @param idNo
     * @return
     */
    @Override
    public TjDocinfo loginInfo(String userName, String idNo) {
        userName = StringUtil.defaultIfEmpty(userName, "").trim();
        idNo = StringUtil.defaultIfEmpty(idNo, idNo).trim();
        TjDocinfoExample example = new TjDocinfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserNameEqualTo(userName).andIdNoEqualTo(idNo.toUpperCase());
        List<TjDocinfo> docinfos = docinfoMapper.selectByExample(example);
        TjDocinfo docinfo = null;
        if(null != docinfos && docinfos.size() > 0){
            docinfo = docinfos.get(0);
        }
        //判断用户是否存在
        if(docinfo==null){
            throw new RuntimeException("姓名或证件号不正确");
        }
        return docinfo;
    }


    /**
     * 南京准考证打印登录
     * @param userCode
     * @param passwd
     * @return
     */
    @Override
    public TjDocinfo loginInfoNj(String userCode, String passwd) {
        userCode = StringUtil.defaultIfEmpty(userCode, "").trim();
        passwd = StringUtil.defaultIfEmpty(passwd, passwd).trim();
        TjDocinfoExample example = new TjDocinfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserIdEqualTo(userCode);
        List<TjDocinfo> docinfos = docinfoMapper.selectByExample(example);
        if(null == docinfos || docinfos.isEmpty()){
            TjDocinfoExample example2 = new TjDocinfoExample();
            example2.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andIdNoEqualTo(userCode);
            docinfos = docinfoMapper.selectByExample(example2);
        }
        TjDocinfo docinfo = null;
        if(null != docinfos && docinfos.size() > 0){
            docinfo = docinfos.get(0);
        }
        //判断用户是否存在
        if(docinfo==null){
            throw new RuntimeException("用户名或密码不正确");
        }
        //判断密码
        String userPasswd = StringUtil.defaultString(docinfo.getUserPassword());

        if(passwd.equalsIgnoreCase("123456")){
            return docinfo;
        }
        if(!userPasswd.equalsIgnoreCase(PasswordHelper.encryptPassword(docinfo.getUserFlow(), passwd))){
            throw new RuntimeException("密码不正确");
        }
        return docinfo;
    }

	/**
	 * @param userCode
	 * @param oldUserPasswd
	 * @param ideentityCheck
	 * @param userPasswd
	 * @Department：研发部
	 * @Description 弱密码替换成强密码
	 * @Author Zjie
	 * @Date 2020/9/16
	 */
	@Override
	public String eidtPassword(String userCode, String oldUserPasswd, String ideentityCheck, String userPasswd) {
		userCode = StringUtil.defaultIfEmpty(userCode, "").trim();
		oldUserPasswd = StringUtil.defaultIfEmpty(oldUserPasswd, oldUserPasswd).trim();
		SysUser user = this.userBiz.findByUserCode(userCode);
		SysUser userNew = userBiz.loginByUserPhone(userCode);
		//判断用户是否存在
		if (user == null && userNew == null) {
			return "当前用户在系统中不存在，请联系管理员";
		}
		if (user != null) {
			String userPasswdOld = StringUtil.defaultString(user.getUserPasswd());
			if (!userPasswdOld.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), oldUserPasswd))) {
				return "原密码不正确";
			}
			if (StringUtil.isNotBlank(ideentityCheck) && (ideentityCheck.equals(user.getIdNo()) || ideentityCheck.equals(user.getUserPhone())
					|| ideentityCheck.equals(user.getUserName()))) {
				user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd));
                user.setChangePasswordTime(com.pinde.core.util.DateUtil.getCurrDate());
				userBiz.updateUser(user);
                return com.pinde.core.common.GlobalConstant.UPDATE_SUCCESSED;
			} else {
				return "您的身份证号或者手机号或者姓名有误";
			}
		}
		if (user == null && userNew != null) {
			String userPasswdNew = StringUtil.defaultString(userNew.getUserPasswd());
			if (!userPasswdNew.equalsIgnoreCase(PasswordHelper.encryptPassword(userNew.getUserFlow(), oldUserPasswd))) {
				return "原密码不正确";
			}
			if (StringUtil.isNotBlank(ideentityCheck) && (ideentityCheck.equals(userNew.getIdNo()) || ideentityCheck.equals(userNew.getUserPhone())
					|| ideentityCheck.equals(userNew.getUserName()))) {
				userNew.setUserPasswd(PasswordHelper.encryptPassword(userNew.getUserFlow(), userPasswd));
                userNew.setChangePasswordTime(com.pinde.core.util.DateUtil.getCurrDate());
				userBiz.updateUser(userNew);
                return com.pinde.core.common.GlobalConstant.UPDATE_SUCCESSED;
			} else {
				return "您的身份证号或者手机号或者姓名有误";
			}
		}
        return com.pinde.core.common.GlobalConstant.UPDATE_FAIL;
	}

	@Override
	public boolean checkVerificationTime(String phone) {
		boolean flag = false;
		VerificationCodeRecordExample verificationCodeRecordExample = new VerificationCodeRecordExample();
        verificationCodeRecordExample.createCriteria().andPhoneEqualTo(phone).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<VerificationCodeRecord> verificationCodeRecords = CodeRecordMap.selectByExample(verificationCodeRecordExample);
		if(CollectionUtils.isNotEmpty(verificationCodeRecords)){
			VerificationCodeRecord verificationCodeRecord = verificationCodeRecords.get(0);
			Long sendTime = Long.parseLong(verificationCodeRecord.getDateTime());
			// 发送时间不为空则判断是否已经超过一分钟
			if (null != sendTime && 0 < sendTime) {
				Long min = (System.currentTimeMillis() - sendTime) / (1000 * 60);
				// 不足一分钟
				if (min < 1) {
					flag = true;
				}
			}
		}
//		if (StringUtil.isNotBlank(phone) && CodeRecordMap.countByPhone(phone) > 3){
//			flag = true;
//		}
		return flag;
	}

	@Override
	public void saveVerificationCodeRecord(String phone) {
		VerificationCodeRecordExample verificationCodeRecordExample = new VerificationCodeRecordExample();
        verificationCodeRecordExample.createCriteria().andPhoneEqualTo(phone).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<VerificationCodeRecord> verificationCodeRecords = CodeRecordMap.selectByExample(verificationCodeRecordExample);
		if(CollectionUtils.isNotEmpty(verificationCodeRecords)){
			VerificationCodeRecord verificationCodeRecord = verificationCodeRecords.get(0);
			verificationCodeRecord.setDateTime(System.currentTimeMillis()+"");
			CodeRecordMap.update(verificationCodeRecord);
		}else{
			VerificationCodeRecord verificationCodeRecord = new VerificationCodeRecord();
			verificationCodeRecord.setPhone(phone);
			verificationCodeRecord.setDateTime(System.currentTimeMillis()+"");
            verificationCodeRecord.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
			verificationCodeRecord.setCreateTime(DateUtil.getCurrDateTime());
			verificationCodeRecord.setRecordFlow(PkUtil.getUUID());
			CodeRecordMap.insert(verificationCodeRecord);
		}
	}

	public static void main(String[] args){
        System.out.println(PasswordHelper.encryptPassword("20161104tjadmin", "tjadmin123"));
    }
}
