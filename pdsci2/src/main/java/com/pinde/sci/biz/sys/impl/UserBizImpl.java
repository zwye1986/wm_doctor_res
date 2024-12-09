package com.pinde.sci.biz.sys.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.pinde.core.common.GeneralEnum;
import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.pub.UserSexEnum;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.common.enums.sys.CertificateTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.*;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IDiscipleBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResResponsibleTeacherDoctorBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.WeixinQiYeUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.sys.SysUserExtMapper;
import com.pinde.sci.model.mo.JsresUserBalcklist;
import com.pinde.sci.model.mo.JsresUserBalcklistExample;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResResponsibleteacherDoctor;
import com.pinde.sci.model.mo.ResStudentDiscipleTeacher;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysRole;
import com.pinde.sci.model.mo.SysRoleExample;
import com.pinde.sci.model.mo.SysUserRole;
import com.pinde.sci.model.mo.SysUserRoleExample;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
//@Transactional(rollbackFor=Exception.class)
public class UserBizImpl implements IUserBiz {

	private static Logger logger = LoggerFactory.getLogger(UserBizImpl.class);

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper userRoleMapper;
	@Autowired
	private IOrgBiz orgBiz;
	//	@Autowired
//	private IResEduCourseBiz eduCourseBiz;
	@Autowired
	private SysUserExtMapper userExtMapper;
	@Autowired
	private SysDeptMapper sysDeptMapper;
	//	@Autowired
//	private IEduUserBiz eduUserBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private SysUserDeptMapper userDeptMapper;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IDiscipleBiz discipleBiz;
	@Autowired
	private IResResponsibleTeacherDoctorBiz responsibleTeacherDoctorBiz;
	@Autowired
	private SysUserRegisterMapper sysUserRegisterMapper;
	@Autowired
	private JsresUserBalcklistMapper balcklistMapper;
	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;


	@Override
	public List<SysUser> selectByNamesOrIdNo(List<String> userNameList, List<String> idNoList) {
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
				.andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
		if (CollectionUtil.isNotEmpty(userNameList)) {
			criteria.andUserNameIn(userNameList);
		}
		if (CollectionUtil.isNotEmpty(idNoList)) {
			criteria.andIdNoIn(idNoList);
		}
		return sysUserMapper.selectByExample(sysUserExample);
	}

	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
	}

	@Override
	public int deleteUser(String userFlow) {
		SysUser user = new SysUser();
		user.setUserFlow(userFlow);
        user.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		int r1 =  sysUserMapper.updateByPrimaryKeySelective(user);

		SysUserRoleExample example = new SysUserRoleExample();
		com.pinde.sci.model.mo.SysUserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andUserFlowEqualTo(userFlow);
		SysUserRole userRole = new SysUserRole();
        userRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		int r2 = userRoleMapper.updateByExampleSelective(userRole,example);
        if (r1 > com.pinde.core.common.GlobalConstant.ZERO_LINE && r2 > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.ONE_LINE;
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public SysUser readSysUser(String sysUserFlow) {
		return sysUserMapper.selectByPrimaryKey(sysUserFlow);
	}
	@Override
	public SysUser findByIdNoAndCretType(SysUser sysUser)
	{
		SysUserExample sysUserExample=new SysUserExample();
		SysUserExample.Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andIdNoEqualTo(sysUser.getIdNo());
		criteria.andCretTypeIdEqualTo(sysUser.getCretTypeId());
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public List<SysUser> searcherUserByDeptFlow(String deptFlow){
		SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(deptFlow)){
			criteria.andDeptFlowEqualTo(deptFlow);
		}else{
			if(GlobalContext.getCurrentUser()!=null){
			criteria.andDeptFlowIsNull().andOrgFlowEqualTo( GlobalContext.getCurrentUser().getOrgFlow());
			}
		}
		List<SysUser> userList = sysUserMapper.selectByExample(sysUserExample);
		return userList;
	}

	@Override
	public List<SysUser> readSysUserByExample(SysUserExample example) {
		return sysUserMapper.selectByExample(example);
	}

	@Override
	public int addUser(SysUser user) {
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), InitConfig.getInitPassWord()));
		user.setStatusId(UserStatusEnum.Activated.getId());
		user.setStatusDesc(UserStatusEnum.Activated.getName());
		GeneralMethod.setRecordInfo(user, true);
		return sysUserMapper.insert(user);
	}

	@Override
	public int addTeaching(SysUser user) {
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), InitConfig.getInitPassWord()));
		user.setStatusId(UserStatusEnum.Activated.getId());
		user.setStatusDesc(UserStatusEnum.Activated.getName());
		GeneralMethod.setRecordInfo(user, true);

		//添加带教角色
		SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andRoleNameEqualTo("带教老师").andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRole> sysRoles = roleMapper.selectByExample(sysRoleExample);
		if(CollectionUtils.isNotEmpty(sysRoles)){
			SysUserRole insert = new SysUserRole();
			insert.setUserFlow(user.getUserFlow());
			insert.setOrgFlow(user.getOrgFlow());
			insert.setWsId("res");
			insert.setRoleFlow(sysRoles.get(0).getRoleFlow());
            insert.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			insert.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(insert, true);
			userRoleMapper.insert(insert);
		}
		return sysUserMapper.insert(user);

	}

	@Override
	public int insertUser(SysUser user) {
		GeneralMethod.setRecordInfo(user, true);
		return sysUserMapper.insert(user);
	}
	@Override
	public SysUser searcherUserByOrgFlow(String orgFlow){
		SysUserExample sysUserExample = new SysUserExample();
		sysUserExample.createCriteria().andOrgFlowEqualTo(orgFlow);
		List<SysUser> userList = sysUserMapper.selectByExample(sysUserExample);
		SysUser user = null;
		if(userList!=null && userList.size()>0){
			user = userList.get(0);
		}
		return user;
	}

	@Override
	public int saveUser(SysUser user,String roleFlow) {
		if(user != null){
			boolean haveRole = StringUtil.isNotBlank(user.getUserFlow());
			saveUser(user);
			if(!haveRole){
				SysUserRole userRole = new SysUserRole();
				userRole.setUserFlow(user.getUserFlow());
				userRole.setOrgFlow(user.getOrgFlow());
                userRole.setWsId(com.pinde.core.common.GlobalConstant.RES_WS_ID);
				userRole.setRoleFlow(roleFlow);
				userRole.setAuthTime(DateUtil.getCurrDateTime());
				userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				userRoleBiz.saveSysUserRole(userRole);
			}
            return com.pinde.core.common.GlobalConstant.ONE_LINE;
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public int saveUser(SysUser user) {
        String currWsId = (String) GlobalContext.getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		String userFlow = user.getUserFlow();
		if(StringUtil.isNotBlank(userFlow)){
			GeneralMethod.setRecordInfo(user, false);
			if (StringUtil.isNotBlank(user.getStatusId())) {
				user.setStatusDesc(UserStatusEnum.getNameById(user.getStatusId()));
			}
			int ret = sysUserMapper.updateByPrimaryKeySelective(user);
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("sys_weixin_qiye_flag"))) {
				//全部同步后saveUser改称update
				user = sysUserMapper.selectByPrimaryKey(userFlow);
				boolean result = false;
				if(StringUtil.isNotBlank(InitConfig.getSysCfg("sys_weixin_qiye_dept_id"))){
					 result = WeixinQiYeUtil.saveUser(InitConfig.getSysCfg("sys_weixin_qiye_corp_id"), InitConfig.getSysCfg("sys_weixin_qiye_secret"), InitConfig.getSysCfg("sys_weixin_qiye_dept_id"), user);
				}else{
					if(StringUtil.isNotBlank(user.getDeptFlow())){
						SysDept sysDept = sysDeptMapper.selectByPrimaryKey(user.getDeptFlow());
						String deptCode = null;
						if(sysDept!=null){
							deptCode = sysDept.getDeptCode();
						}
						result = WeixinQiYeUtil.saveUser(InitConfig.getSysCfg("sys_weixin_qiye_corp_id"), InitConfig.getSysCfg("sys_weixin_qiye_secret"), deptCode, user);
					}
				}
				logger.debug("wei xin qi ye saveuser is "+result);
			}
			return ret;
		}else{
			user.setUserFlow(PkUtil.getUUID());
			user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), InitConfig.getJxInitPassWord()));
			if (StringUtil.isNotBlank(user.getStatusId())) {
				user.setStatusDesc(UserStatusEnum.getNameById(user.getStatusId()));
			} else {
				user.setStatusId(UserStatusEnum.Activated.getId());
				user.setStatusDesc(UserStatusEnum.Activated.getName());
			}
			if(StringUtil.isNotBlank(user.getResTrainingSpeId())){
				user.setStatusId(UserStatusEnum.Locked.getId());
				user.setStatusDesc(UserStatusEnum.Locked.getName());
			}
			if(StringUtil.isBlank(user.getCretTypeId())){
				//默认证件类型是身份证
				user.setCretTypeId(CertificateTypeEnum.Shenfenzheng.getId());
				user.setCretTypeName(CertificateTypeEnum.Shenfenzheng.getName());
			}else{
				try{
					user.setCretTypeName(CertificateTypeEnum.getNameById(user.getCretTypeId()));
				}catch(Exception e){
                    logger.error("", e);
				}finally{
					user.setCretTypeId(CertificateTypeEnum.Shenfenzheng.getId());
					user.setCretTypeName(CertificateTypeEnum.Shenfenzheng.getName());
				}
			}
			GeneralMethod.setRecordInfo(user, true);

            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("sys_weixin_qiye_flag"))) {
				boolean result = false;
				if(StringUtil.isNotBlank(InitConfig.getSysCfg("sys_weixin_qiye_dept_id"))){
					result = WeixinQiYeUtil.createUser(InitConfig.getSysCfg("sys_weixin_qiye_corp_id"), InitConfig.getSysCfg("sys_weixin_qiye_secret"), InitConfig.getSysCfg("sys_weixin_qiye_dept_id"), user);
				}else{
					if(StringUtil.isNotBlank(user.getDeptFlow())){
						SysDept sysDept = sysDeptMapper.selectByPrimaryKey(user.getDeptFlow());
						String deptCode = null;
						if(sysDept!=null){
							deptCode = sysDept.getDeptCode();
						}
						result = WeixinQiYeUtil.createUser(InitConfig.getSysCfg("sys_weixin_qiye_corp_id"), InitConfig.getSysCfg("sys_weixin_qiye_secret"), deptCode, user);
					}
				}
				logger.debug("wei xin qi ye createUser is "+result);
			}
			int ret = sysUserMapper.insert(user);
			return ret;
		}

	}

	@Override
	public List<SysUser> searchUser(SysUser sysUser) {
		SysUserExample sysUserExample=new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		setUserCriteria(criteria , sysUser);
		sysUserExample.setOrderByClause(" NLSSORT(USER_NAME,'NLS_SORT = SCHINESE_PINYIN_M')");
		if (StringUtil.isNotBlank(sysUser.getUserName())) {
			criteria.andUserNameLike("%"+sysUser.getUserName()+"%");
		}
		if (StringUtil.isNotBlank(sysUser.getUserFlow())) {
			criteria.andUserFlowEqualTo(sysUser.getUserFlow());
		}
		if (StringUtil.isNotBlank(sysUser.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(sysUser.getOrgFlow());
		}
		if (StringUtil.isNotBlank(sysUser.getOrgName())) {
			criteria.andOrgNameLike("%"+sysUser.getOrgName()+"%");
		}
		if (StringUtil.isNotBlank(sysUser.getDeptName())) {
			criteria.andDeptNameLike("%"+sysUser.getDeptName()+"%");
		}
		if (StringUtil.isNotBlank(sysUser.getIdNo())) {
			criteria.andIdNoEqualTo(sysUser.getIdNo());
		}
		if (StringUtil.isNotBlank(sysUser.getUserCode())) {
			criteria.andUserCodeEqualTo(sysUser.getUserCode());
		}
		if (StringUtil.isNotBlank(sysUser.getIsOwnerStu())) {
			criteria.andIsOwnerStuEqualTo(sysUser.getIsOwnerStu());
		}
		return sysUserMapper.selectByExample(sysUserExample);
	}

	@Override
	public List<SysUser> searchUserWithRole(Map<String, Object> paramMap) {
		return userExtMapper.searchUserWithRole(paramMap);
	}

	@Override
	public List<SysUser> searchUserWithRoleByJx(Map<String, Object> paramMap) {
		return userExtMapper.searchUserWithRoleByJx(paramMap);
	}
    @Override
	public List<SysUser> searchUserWithRoleByJx2(Map<String, Object> paramMap) {
		return userExtMapper.searchUserWithRoleByJx2(paramMap);
	}

	@Override
	public List<SysUser> searchUserByRoleAndOrgFlows(String roleFlow,List<String> orgFlows) {
		return userExtMapper.searchUserByRoleAndOrgFlows(roleFlow,orgFlows);
	}

	@Override
	public List<SysUser> searchUserByOrgFlow(SysUser sysUser , List<String> orgFlows) {
		SysUserExample sysUserExample=new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
			criteria.andOrgFlowEqualTo(sysUser.getOrgFlow());
		}else if(orgFlows!=null &&!orgFlows.isEmpty()){
			criteria.andOrgFlowIn(orgFlows);
		}
		setUserCriteria(criteria , sysUser);
		sysUserExample.setOrderByClause(" NLSSORT(USER_NAME,'NLS_SORT = SCHINESE_PINYIN_M')");
		return sysUserMapper.selectByExample(sysUserExample);
	}

	private void setUserCriteria(SysUserExample.Criteria criteria , SysUser sysUser){
		if(StringUtil.isNotBlank(sysUser.getUserName())){
			criteria.andUserNameLike("%"+sysUser.getUserName()+"%");
		}
		if(StringUtil.isNotBlank(sysUser.getSrmExpertFlag())){
			criteria.andSrmExpertFlagEqualTo(sysUser.getSrmExpertFlag());
		}
		if(StringUtil.isNotBlank(sysUser.getIdNo())){
			criteria.andIdNoEqualTo(sysUser.getIdNo());
		}
		if(StringUtil.isNotBlank(sysUser.getUserPhone())){
			criteria.andUserPhoneEqualTo((sysUser.getUserPhone()));
		}
		if(StringUtil.isNotBlank(sysUser.getUserEmail())){
			criteria.andUserEmailEqualTo(sysUser.getUserEmail());
		}
		if(StringUtil.isNotBlank(sysUser.getStatusId())){
			criteria.andStatusIdEqualTo(sysUser.getStatusId());
		}
		if(StringUtil.isNotBlank(sysUser.getDeptFlow())){
			criteria.andDeptFlowEqualTo(sysUser.getDeptFlow());
		}
		if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
			criteria.andOrgFlowEqualTo(sysUser.getOrgFlow());
		}
		if (StringUtil.isNotBlank(sysUser.getOrgName())) {
			criteria.andOrgNameLike("%"+sysUser.getOrgName()+"%");
		}
		if (StringUtil.isNotBlank(sysUser.getUserCode())) {
			criteria.andUserCodeEqualTo(sysUser.getUserCode());
		}
	}

	@Override
	public SysUser findByUserCode(String userCode) {
		SysUserExample sysUserExample=new SysUserExample();
		SysUserExample.Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserCodeEqualTo(userCode);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysUser findByUserCodeAndOrgFlow(String userCode,String orgFlow) {
		SysUserExample sysUserExample=new SysUserExample();
		SysUserExample.Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserCodeEqualTo(userCode).andOrgFlowEqualTo(orgFlow);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysUser findByIdNo(String idNo) {
		SysUserExample sysUserExample=new SysUserExample();
		SysUserExample.Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andIdNoEqualTo(idNo);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysUser findByNotBlackIdNo(String idNo) {
		List<SysUser> sysUserList = userExtMapper.findByNotBlackIdNo(idNo);
		SysUser su = null;
		if(null != sysUserList && sysUserList.size()>0){
			for(SysUser user : sysUserList){
				if(idNo.equals(user.getIdNo())){
					su = user;
					break;
				}
			}
			return su==null?sysUserList.get(0):su;
		}
		return null;
	}

	@Override
	public SysUser findByUserPhone(String userPhone) {
		SysUserExample sysUserExample=new SysUserExample();
		SysUserExample.Criteria criteria=sysUserExample.createCriteria();
//		criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserPhoneEqualTo(userPhone);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysUser findByUserEmail(String userEmail) {
		SysUserExample sysUserExample=new SysUserExample();
		SysUserExample.Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserEmailEqualTo(userEmail);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysUser findByUserName(String userName) {
		SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserNameEqualTo(userName);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size() > 0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysUser findByUserCodeNotSelf(String userFlow,String userCode) {
		SysUserExample sysUserExample=new SysUserExample();
		SysUserExample.Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserCodeEqualTo(userCode);
		criteria.andUserFlowNotEqualTo(userFlow);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysUser findByIdNoNotSelf(String userFlow,String idNo) {
		SysUserExample sysUserExample=new SysUserExample();
		SysUserExample.Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andIdNoEqualTo(idNo);
		criteria.andUserFlowNotEqualTo(userFlow);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysUser findByUserPhoneNotSelf(String userFlow,String userPhone) {
		SysUserExample sysUserExample=new SysUserExample();
		SysUserExample.Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserPhoneEqualTo(userPhone);
		criteria.andUserFlowNotEqualTo(userFlow);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysUser findByUserEmailNotSelf(String userFlow,String userEmail) {
		SysUserExample sysUserExample=new SysUserExample();
		SysUserExample.Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserEmailEqualTo(userEmail);
		criteria.andUserFlowNotEqualTo(userFlow);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public int updateUser(SysUser sysUser) {
		if(StringUtil.isNotBlank(sysUser.getUserFlow())){
			GeneralMethod.setRecordInfo(sysUser, false);
			return sysUserMapper.updateByPrimaryKeySelective(sysUser);
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public void modifyUserByExample(SysUser user) {
		SysUserExample sysUserExample=new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(user.getOrgFlow())){
			criteria.andOrgFlowEqualTo(user.getOrgFlow());
		}
		this.sysUserMapper.updateByExampleSelective(user, sysUserExample);

	}

	@Override
	public List<SysUser> searchSysUserByuserFlows(List<String> userFlows){
		if(userFlows != null && !userFlows.isEmpty()){
			SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserFlowIn(userFlows);
			return sysUserMapper.selectByExample(sysUserExample);
		}
		return null;
	}

	@Override
	public List<SysUser> searchSysUserByuserFlows(List<String> userFlows,String deptFlow){
		if(userFlows != null && !userFlows.isEmpty()){
			SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserFlowIn(userFlows).andDeptFlowEqualTo(deptFlow);
			return sysUserMapper.selectByExample(sysUserExample);
		}
		return null;
	}

	@Override
	public List<SysUser> searchSysUserByOrgFlows(List<String> orgFlows){
		SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowIn(orgFlows);
		return sysUserMapper.selectByExample(sysUserExample);
	}

	@Override
	public void activateUser(SysUser user) {
		user.setStatusId(UserStatusEnum.Activated.getId());
		user.setStatusDesc(UserStatusEnum.Activated.getName());
		user.setUnLockTime(DateUtil.getCurrDateTime());
		saveUser(user);
//		}
	}

	@Override
	public List<SysUser> searchUserListByOrgFlow(Map<String, Object> paramMap) {
		return userExtMapper.searchUserListByOrgFlow(paramMap);
	}

	@Override
	public List<SysUser> findUserByOrgFlowAndRoleFlow(String orgFlow,
			String roleFlow) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(StringUtil.isNotBlank(orgFlow)){
			paramMap.put("orgFlow", orgFlow);
		}
		if(StringUtil.isNotBlank(roleFlow)){
			paramMap.put("roleFlow", roleFlow);
		}
		return this.userExtMapper.selectUserByOrgFlowAndRoleFlow(paramMap);
	}

	@Override
 	public List<SysUser> getUserByRecForUni(Map<String,Object> paramMap) {
		return this.userExtMapper.getUserByRecForUni(paramMap);
	}

	@Override
 	public List<SysUser> getUserByRec(Map<String,Object> paramMap) {
		return this.userExtMapper.getUserByRec(paramMap);
	}

	@Override
	public void edit(SysUser sysUser) {
		if(sysUser!=null){
			this.sysUserMapper.updateByPrimaryKeySelective(sysUser);
		}
	}

//	@Override
//	public List<SysUser> searchUserByStatus(SysUser user) {
//		SysUserExample sysUserExample=new SysUserExample();
//		sysUserExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andStatusIdEqualTo(user.getStatusId());
//		sysUserExample.setOrderByClause(" NLSSORT(USER_NAME,'NLS_SORT = SCHINESE_PINYIN_M')");
//		return sysUserMapper.selectByExample(sysUserExample);
//	}

	@Override
	public List<SysUser> searchUserByMenu(Map<String, Object> paramMap) {
		List<SysUser> userList=this.userExtMapper.selectUserByMenuId(paramMap);
		return userList;
	}

	@Override
	public void sendResetPassEmail(String userEmail,String userFlow) {
		if(StringUtil.isNotBlank(userEmail)){
			String actionId = userFlow;
			String content = InitConfig.getSysCfg("sys_resetpasswd_email_content");
			Map<String,String> dataMap = new HashMap<String,String>();
			dataMap.put("linkUrl", "<a href='"+InitConfig.getSysCfg("sys_resetpasswd_url")+"?sid="+GlobalContext.getSession().getId()+"&actionId="+actionId+"'>"+InitConfig.getSysCfg("sys_resetpasswd_url")+"?sid="+GlobalContext.getSession().getId()+"&actionId="+actionId+"</a>");
			dataMap.put("linkEmail",userEmail);
			try {
				content = VelocityUtil.evaluate(content, dataMap);
			} catch (Exception e) {
                logger.error("", e);
				throw new RuntimeException();
			}
			this.msgBiz.addEmailMsg(userEmail, InitConfig.getSysCfg("sys_resetpasswd_email_title"), content);
		}
	}

	@Override
	public void authUserEmail(SysUser user) {
		if(user != null){
			String actionId = user.getUserFlow();
			String userEmail = user.getUserEmail();
			String content = InitConfig.getSysCfg("user_email_auth_email_content");
			Map<String,String> dataMap = new HashMap<String,String>();
			dataMap.put("linkUrl", "<a href='"+InitConfig.getSysCfg("user_email_auth_url")+"?sid="+GlobalContext.getSession().getId()+"&actionId="+actionId+"'>"+InitConfig.getSysCfg("user_email_auth_url")+"?sid="+GlobalContext.getSession().getId()+"&actionId="+actionId+"</a>");
			dataMap.put("linkEmail",userEmail);
			try {
				content = VelocityUtil.evaluate(content, dataMap);
			} catch (Exception e) {
                logger.error("", e);
				throw new RuntimeException();
			}
			this.msgBiz.addEmailMsg(userEmail, InitConfig.getSysCfg("user_email_auth_email_title"), content);
		}
	}

	@Override
	public void addUserDept(SysUser user,List<String> deptFlows) {
        Map<String, SysUserDept> userDeptMap = new HashMap<String, SysUserDept>();
		List<SysUserDept> userDeptList = getUserDept(user);

		for(SysUserDept userDept : userDeptList){
			userDeptMap.put(userDept.getDeptFlow(), userDept);
		}
		//批量废止
		disUserDept(user);
		//重新激活选中的
		for(String temp : deptFlows){
			if (userDeptMap.containsKey(temp)) {
				SysUserDept userDept = userDeptMap.get(temp);
                userDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				GeneralMethod.setRecordInfo(userDept, false);
				userDeptMapper.updateByPrimaryKey(userDept);
			}else {
				SysUserDept userDept = new SysUserDept();
				userDept.setRecordFlow(PkUtil.getUUID());
				userDept.setUserFlow(user.getUserFlow());
				userDept.setDeptFlow(temp);
				String deptName = InitConfig.getDeptNameByFlow(temp);
				if(!StringUtil.isNotBlank(deptName)){
					SysDept dept = deptBiz.readSysDept(temp);
					if(dept!=null){
						deptName = dept.getDeptName();
					}
				}
				userDept.setDeptName(deptName);
				userDept.setOrgFlow(user.getOrgFlow());
				userDept.setOrgName(StringUtil.defaultString(InitConfig.getOrgNameByFlow(user.getOrgFlow())));
				GeneralMethod.setRecordInfo(userDept, true);
				userDeptMapper.insert(userDept);
			}
		}
	}

	@Override
	public List<SysUserDept> getUserDept(SysUser user) {
		SysUserDeptExample example = new SysUserDeptExample();
        SysUserDeptExample.Criteria criteria = example.createCriteria().andUserFlowEqualTo(user.getUserFlow())
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(user.getOrgFlow())){
			criteria.andOrgFlowEqualTo(user.getOrgFlow());
		}
		example.setOrderByClause("ORDINAL");
		return userDeptMapper.selectByExample(example);
	}

	@Override
	public void disUserDept(SysUser user) {
		if(StringUtil.isNotBlank(user.getOrgFlow())){
			SysUserDeptExample example = new SysUserDeptExample();
            example.createCriteria().andUserFlowEqualTo(user.getUserFlow()).andOrgFlowEqualTo(user.getOrgFlow()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

			SysUserDept delete = new SysUserDept();
            delete.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
			userDeptMapper.updateByExampleSelective(delete,example);
		}
	}

	@Override
	public List<SysUser> searchResManageUser(SysUser user,List<String> roleList,String roleFlow) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("deptFlow", user.getDeptFlow());
		map.put("deptName", user.getDeptName());
		map.put("idNo", user.getIdNo());
		map.put("userPhone", user.getUserPhone());
		map.put("userEmail", user.getUserEmail());
		map.put("userName", user.getUserName());
		map.put("statusId", user.getStatusId());
		map.put("orgFlow", user.getOrgFlow());
		map.put("userFlow",user.getUserFlow());
		map.put("roleFlow",roleFlow);
		map.put("userCode",user.getUserCode());
		if(roleList!=null && roleList.size()>0){
			map.put("roleList", roleList);
		}
		return userExtMapper.searchResManageUser(map);
	}
	@Override
	public List<SysUser> searchResManageUserNotSelf(SysUser user, List<String> roleList, String userFlow, String isSelect, String examTeaRole) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("deptFlow", user.getDeptFlow());
		map.put("deptName", user.getDeptName());
		map.put("idNo", user.getIdNo());
		map.put("userPhone", user.getUserPhone());
		map.put("userEmail", user.getUserEmail());
		map.put("userCode", user.getUserCode());
		map.put("userName", user.getUserName());
		map.put("statusId", user.getStatusId());
		map.put("orgFlow", user.getOrgFlow());
		map.put("isSelect",isSelect);
		map.put("examTeaRole",examTeaRole);
		map.put("moreDept",user.getIsForeign());//用作暂存多科室查询字段
		if(roleList!=null && roleList.size()>0){
			map.put("roleList", roleList);
		}
		if(StringUtil.isNotBlank(userFlow)){
			map.put("userFlow", userFlow);
		}
		List<SysUser> sysUsers = userExtMapper.searchResManageUserNotSelf(map);
		if(CollectionUtils.isNotEmpty(sysUsers)){
			//多科室查询
			for (SysUser sysUser : sysUsers) {
//				if(StringUtil.isEmpty(sysUser.getDeptName())){
					List<SysUserDept> sysUserDepts = searchUserDeptByUser(sysUser.getUserFlow());
					if(CollectionUtils.isNotEmpty(sysUserDepts)){
						List<String> collect = sysUserDepts.stream().map(SysUserDept::getDeptName).collect(Collectors.toList());
						sysUser.setDeptName(collect.toString().replace("[","").replace("]",""));
					}
//				}
			}
		}
		return sysUsers;
	}

	@Override
	public List<SysUser> searchResManageUserNotSelf2(SysUser user, List<String> roleList, String userFlow, String isSelect, String examTeaRole) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("deptFlow", user.getDeptFlow());
		map.put("deptName", user.getDeptName());
		map.put("idNo", user.getIdNo());
		map.put("userPhone", user.getUserPhone());
		map.put("userEmail", user.getUserEmail());
		map.put("userCode", user.getUserCode());
		map.put("userName", user.getUserName());
		map.put("statusId", user.getStatusId());
		map.put("orgFlow", user.getOrgFlow());
		map.put("isSelect",isSelect);
		map.put("examTeaRole",examTeaRole);
		map.put("moreDept",user.getIsForeign());//用作暂存多科室查询字段
		if(roleList!=null && roleList.size()>0){
			map.put("roleList", roleList);
		}
		if(StringUtil.isNotBlank(userFlow)){
			map.put("userFlow", userFlow);
		}
		List<SysUser> sysUsers = userExtMapper.searchResManageUserNotSelf(map);
		return sysUsers;
	}

	@Override
	public List<SysUser> searchResManageUserByModeDept(SysUser user, String moreDept, List<String> roleList,String roleFlow) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("deptFlow", user.getDeptFlow());
		map.put("deptName", user.getDeptName());
		map.put("idNo", user.getIdNo());
		map.put("userPhone", user.getUserPhone());
		map.put("userEmail", user.getUserEmail());
		map.put("userName", user.getUserName());
		map.put("statusId", user.getStatusId());
		map.put("orgFlow", user.getOrgFlow());
		map.put("moreDept",moreDept);
		map.put("userFlow",user.getUserFlow());
		map.put("roleFlow",roleFlow);
		map.put("userCode",user.getUserCode());
		if(roleList!=null && roleList.size()>0){
			map.put("roleList", roleList);
		}
		return userExtMapper.searchResManageUser(map);
	}


	@Override
	public int importUserFromExcel(MultipartFile file) {
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			return parseExcel(wb);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public int importTeachingFromExcel(MultipartFile file,SysUser user) {
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			return parseExcelAndAudit(wb,user);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	/**
	 * 人员管理导入
	 */
	@Override
	public int importPerManageFromExcel(MultipartFile file,String[] roles) {
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			return parseManagerExcel(wb,roles);
		} catch (Exception e) {
            logger.error("", e);
			throw new RuntimeException(e.getMessage());
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (Exception e) {
                    logger.error("", e);
				}
			}
		}
	}

	/**
	 * 师承老师和管理员导入导入
	 */
	@Override
	public int importDiscAndResponFromExcel(MultipartFile file,String type) {
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			return parseDiscAndResponExcel(wb,type);
		} catch (Exception e) {
            logger.error("", e);
			throw new RuntimeException(e.getMessage());
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (Exception e) {
                    logger.error("", e);
				}
			}
		}
	}

	@Override
	public List<SysUser> searchOtherUserByDeptAndRole(String orgFlow, String deptFlow, String roleFlow) {
		return userExtMapper.searchOtherUserByDeptAndRole(orgFlow,deptFlow, roleFlow);
	}

	@Override
	public List<SysUser> readOrgTeas(String orgFlow, String teacherRoleFlow, String headRoleFlow, String secretaryRoleFlow, String trainTeacherRoleFlow) {
		List<String> roles=new ArrayList<>();
		if(StringUtil.isNotBlank(teacherRoleFlow))
			roles.add(teacherRoleFlow);
		if(StringUtil.isNotBlank(headRoleFlow))
			roles.add(headRoleFlow);
		if(StringUtil.isNotBlank(secretaryRoleFlow))
			roles.add(secretaryRoleFlow);
		if(StringUtil.isNotBlank(trainTeacherRoleFlow))
			roles.add(trainTeacherRoleFlow);
		if(roles.size()==0)
			return null;
		return userExtMapper.readOrgTeas(orgFlow,roles);
	}

	@Override
	public List<SysUser> readUserBySpe(String deptFlow, String roleFlow, String resTrainingSpeId, String orgFlow) {
		if(StringUtil.isNotBlank(roleFlow)&&StringUtil.isNotBlank(resTrainingSpeId))
			return userExtMapper.readUserBySpe( deptFlow,  roleFlow,  resTrainingSpeId,  orgFlow);
		return null;
	}


	@Override
	public List<SysUser> selectByUserPhoneAndIsVerify(String userPhone) {
		return sysUserRegisterMapper.selectByUserPhoneAndIsVerify(userPhone);
	}

	@Override
	public int saveRegisterUser(String userPhone,String code,String codeTime) {
		SysUser user = new SysUser();
		user.setUserPhone(userPhone);
        user.setIsVerify(com.pinde.core.common.GlobalConstant.FLAG_N);
		user.setVerifyCode(code);
		user.setVerifyCodeTime(codeTime);
		SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserPhoneEqualTo(user.getUserPhone()).andIsVerifyEqualTo(user.getIsVerify()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
		if(sysUsers != null && sysUsers.size() > 0){
			SysUser record = sysUsers.get(0);
			record.setVerifyCode(code);
			record.setVerifyCodeTime(codeTime);
			return sysUserMapper.updateByPrimaryKey(record);
		}
		user.setUserFlow(PkUtil.getUUID());
		user.setUserCode(userPhone);
		user.setUserPasswd(PkUtil.getUUID());
		user.setStatusId(UserStatusEnum.Added.getId());
		user.setStatusDesc(UserStatusEnum.Added.getName());
		GeneralMethod.setRecordInfo(user, true);
		return sysUserMapper.insert(user);
	}

	@Override
	public SysUser selectByUserPhone(String userPhone) {
		SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserPhoneEqualTo(userPhone).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
		if(sysUsers != null && sysUsers.size() > 0){
			List<String> collect = sysUsers.stream().map(sysUser -> sysUser.getVerifyCodeTime()).collect(Collectors.toList());
			String max = Collections.max(collect);
			for (SysUser sysUser : sysUsers) {
				if(max.equals(sysUser.getVerifyCodeTime())){
					return sysUser;
				}
			}
		}
		return null;
	}

	@Override
	public List<SysUser> checkPhoneIsVerify(String userPhone) {
		return sysUserRegisterMapper.checkPhoneIsVerify(userPhone);
	}

	@Override
	public int saveForgetPasswdUser(String userPhone, String code, String codeTime) {
		SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserPhoneEqualTo(userPhone).andIsVerifyEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
		if(sysUsers != null && sysUsers.size() > 0){
			SysUser record = sysUsers.get(0);
			record.setVerifyCode(code);
			record.setVerifyCodeTime(codeTime);
			return sysUserMapper.updateByPrimaryKey(record);
		}
		return 0;
	}

	@Override
	public int saveAuthenSuccessUser(SysUser currentUser) {
        currentUser.setIsVerify(com.pinde.core.common.GlobalConstant.FLAG_Y);
		return sysUserMapper.updateByPrimaryKey(currentUser);
	}

	@Override
	public int saveModifyUser(SysUser currentUser) {
		return sysUserMapper.updateByPrimaryKey(currentUser);
	}

	@Override
	public SysUser findByUserEmailNew(String userEmail) {
		List<SysUser> sysUserList = sysUserRegisterMapper.selectByUserEmail(userEmail);
		if (sysUserList.size() > 0) {
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysUser findByUserCodeNew(String userEmail) {
		List<SysUser> sysUserList = sysUserRegisterMapper.selectByUserCode(userEmail);
		if (sysUserList.size() > 0) {
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysUser loginByUserPhone(String userCode) {
		return sysUserRegisterMapper.findByUserPhone(userCode);
	}

	@Override
	public int updateTeaSubmit(List<String> userFlowList) {
		return userExtMapper.updateTeaSubmit(userFlowList);
	}

	@Override
	public int updateCheckAll(Map<String, Object> param) {
		return userExtMapper.updateCheckAll(param);
	}

	@Override
	public int updateTeaNotSubmit(List<String> userFlowList) {
		return userExtMapper.updateTeaNotSubmit(userFlowList);
	}

	@Override
	public List<JsresUserBalcklist> selectBlacklistByIdNo(String idNo) {
		JsresUserBalcklistExample example = new JsresUserBalcklistExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andIdNoEqualTo(idNo).andAuditStatusIdEqualTo("Passed");
		return balcklistMapper.selectByExample(example);
	}

	@Override
	public boolean userISRole(String userFlow, String roleFlow) {
		SysUserRoleExample example=new SysUserRoleExample();
		SysUserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andWsIdEqualTo("res");
		if (StringUtil.isNotBlank(userFlow)){
			criteria.andUserFlowEqualTo(userFlow);
		}
		List<SysUserRole> list = sysUserRoleMapper.selectByExample(example);
		if (null!=list && list.size()>0){
			SysUserRole sysUserRole = list.get(0);
			if (StringUtil.isNotBlank(sysUserRole.getRoleFlow()) && sysUserRole.getRoleFlow().equals(roleFlow)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据字典名称获取Id
	 * @param dictName
	 * @param dictTypeId
	 * @return
	 */
	public String getDictId(String dictName,String dictTypeId){
	   Map<String,String> dictNameMap=InitConfig.getDictNameMap(dictTypeId);
	   return dictNameMap.get(dictName);
	}

	/**
	 * 根据枚举名称获取id
	 * @param ts
	 * @param enumName
	 * @return
	 */
	private Object parseEnumId(GeneralEnum[] ts,String enumName){
		Object id = null;
		if(StringUtil.isNotBlank(enumName)){
			for(GeneralEnum e : ts){
				String name = e.getName();
				if(enumName.equals(name)){
					id = e.getId();
					break;
				}
			}
		}
		return id;
	}

	/**
		 * 人员管理导入
		 * @param wb
		 * @return
		 */
	private int parseManagerExcel(Workbook wb,String[] roles){
		SysUser cuurUser=GlobalContext.getCurrentUser();
		String orgFlow = cuurUser.getOrgFlow();
		String orgName = cuurUser.getOrgName();

		int count = 0;
		int sheetNum = wb.getNumberOfSheets();
		if(sheetNum>0){
			List<String> colnames = new ArrayList<String>();
			Sheet sheet;
			try{
				sheet = wb.getSheetAt(0);
			}catch(Exception e){
				sheet = wb.getSheetAt(0);
			}
			int row_num = sheet.getLastRowNum() + 1;
			//获取表头
			Row titleR =  sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			String title = "";
			for(int i = 0 ; i <cell_num; i++){
				Cell cell=titleR.getCell(i);
				if(cell==null)
				{
					throw new RuntimeException("导入失败！第"+ (count+2) +"列，表头不能为空！");
				}else {
					title = cell.getStringCellValue();
				}
				 colnames.add(title);
			}
			for(int i = 1;i <row_num; i++){
				Row r =  sheet.getRow(i);
            	SysUser sysUser = new SysUser();
				for(int j = 0; j < colnames.size(); j++){
					String value = "";
					String currTitle=colnames.get(j);
				    Cell cell = r.getCell(j);
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if (cell.getCellType().getCode() == 1) {
							value = cell.getStringCellValue().trim();
						} else {
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					if(StringUtil.isNotBlank(value)){
						if("科室".equals(currTitle)){
							List<SysDept> sysDeptList = deptBiz.searchDeptByNameAndFlow(value, orgFlow);
							if(sysDeptList!=null && sysDeptList.size()>0){
								SysDept sysDept = sysDeptList.get(0);
								if(sysDept!=null){
									sysUser.setDeptFlow(sysDept.getDeptFlow());
									sysUser.setDeptName(value);
								}
							}
						}else if("登录名".equals(currTitle)) {
							sysUser.setUserCode(value);
						}else if("身份证号".equals(currTitle)){
							sysUser.setIdNo(value);
						}else if("身份证".equals(currTitle)){
							sysUser.setIdNo(value);
						}else if("姓名".equals(currTitle)){
							sysUser.setUserName(value);
						}else if("性别".equals(currTitle)){
							if(StringUtil.isNotBlank(value)){
								sysUser.setSexName(value);
								Object id = parseEnumId(UserSexEnum.values(),value);
								if(id!=null){
									sysUser.setSexId(id.toString());
								}
							}
						}else if("手机号码".equals(currTitle)){
							sysUser.setUserPhone(value);
						}else if("电子邮箱".equals(currTitle)){
							sysUser.setUserEmail(value);
						}else if("出生日期".equals(currTitle)){
							sysUser.setUserBirthday(value);
						}else if("学历".equals(currTitle)){
							sysUser.setEducationName(value);
							if(StringUtil.isBlank(sysUser.getEducationId())){
                                sysUser.setEducationId(getDictId(value, com.pinde.core.common.enums.DictTypeEnum.UserEducation.getId()));
							}
						}else if("学位".equals(currTitle)){
							sysUser.setDegreeName(value);
							if(StringUtil.isBlank(sysUser.getDegreeId())){
                                sysUser.setDegreeId(getDictId(value, com.pinde.core.common.enums.DictTypeEnum.UserDegree.getId()));
							}
						}else if("职称".equals(currTitle)){
                            String titleId = getDictId(value, com.pinde.core.common.enums.DictTypeEnum.UserTitle.getId());
							if(StringUtil.isNotBlank(titleId)){
								sysUser.setTitleName(value);
								sysUser.setTitleId(titleId);
							}
						}else if("职务".equals(currTitle)){
							if(StringUtil.isNotBlank(value)){
								sysUser.setPostName(value);
								if(StringUtil.isBlank(sysUser.getPostId())){
                                    sysUser.setPostId(getDictId(value, com.pinde.core.common.enums.DictTypeEnum.UserPost.getId()));
								}
							}
						}
					}
				}
				if(StringUtil.isBlank(sysUser.getUserCode())){
					if(StringUtil.isBlank(sysUser.getIdNo())){
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，登录名不能为空！");
					}else{
						sysUser.setUserCode(sysUser.getIdNo());
					}
				}
				//执行保存
				SysUser old = findByUserCode(sysUser.getUserCode());
				if(old==null){
					if(StringUtil.isNotBlank(sysUser.getIdNo())){
						old = findByIdNo(sysUser.getIdNo());
						if(old!=null){
							throw new RuntimeException("导入失败！第"+(count+2) +"行，该身份证号已经被注册！");
						}
					}

					if(StringUtil.isNotBlank(sysUser.getUserPhone())){
						old =findByUserPhone(sysUser.getUserPhone());
						if(old!=null){
							throw new RuntimeException("导入失败！第"+(count+2) +"行，该手机号已经被注册！");
						}
					}

					if(StringUtil.isNotBlank(sysUser.getUserEmail())){
						old = findByUserEmail(sysUser.getUserEmail());
						if(old!=null){
							throw new RuntimeException("导入失败！第"+(count+2) +"行,该电子邮箱已经被注册！");
						}
					}
					sysUser.setUserFlow(PkUtil.getUUID());
					sysUser.setOrgFlow(orgFlow);
					sysUser.setOrgName(orgName);
					addUser(sysUser);
				}else {
					sysUser.setUserFlow(old.getUserFlow());
					if(StringUtil.isNotBlank(sysUser.getIdNo())){
						old = findByIdNoNotSelf(sysUser.getUserFlow(),sysUser.getIdNo());
						if(old!=null){
							throw new RuntimeException("导入失败！第"+(count+2) +"行，该身份证号已经被注册！");
						}
					}

					if(StringUtil.isNotBlank(sysUser.getUserPhone())){
						old =findByUserPhoneNotSelf(sysUser.getUserFlow(),sysUser.getUserPhone());
						if(old!=null){
							throw new RuntimeException("导入失败！第"+(count+2) +"行，该手机号已经被注册！");
						}
					}

					if(StringUtil.isNotBlank(sysUser.getUserEmail())){
						old = findByUserEmailNotSelf(sysUser.getUserFlow(),sysUser.getUserEmail());
						if(old!=null){
							throw new RuntimeException("导入失败！第"+(count+2) +"行,该电子邮箱已经被注册！");
						}
					}
					sysUser.setOrgFlow(orgFlow);
					sysUser.setOrgName(orgName);
					sysUserMapper.updateByPrimaryKeySelective(sysUser);
				}

				List<String> oldRoles = new ArrayList<>();
				SysUserRoleExample example0 = new SysUserRoleExample();
                example0.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(sysUser.getUserFlow());
				List<SysUserRole> sysUserRoles = userRoleMapper.selectByExample(example0);
				if(sysUserRoles!=null&&sysUserRoles.size()>0){
					for(SysUserRole sysUserRole:sysUserRoles){
						String roleFlow = sysUserRole.getRoleFlow();
						oldRoles.add(roleFlow);
					}
				}
				if(roles!=null && roles.length>0){
					for(String role:roles){
						if(!oldRoles.contains(role)){
							SysUserRole sysUserRole = new SysUserRole();
							sysUserRole.setOrgFlow(cuurUser.getOrgFlow());
							sysUserRole.setUserFlow(sysUser.getUserFlow());
                            sysUserRole.setWsId(com.pinde.core.common.GlobalConstant.RES_WS_ID);
							sysUserRole.setRoleFlow(role);
							sysUserRole.setAuthTime(DateUtil.getCurrDateTime());
							sysUserRole.setAuthUserFlow(cuurUser.getUserFlow());
							userRoleBiz.saveSysUserRole(sysUserRole);
						}
					}
				}

				SysUserDeptExample example1 = new SysUserDeptExample();
                example1.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(sysUser.getUserFlow());
				List<SysUserDept> sysUserDepts = userDeptMapper.selectByExample(example1);
				if(sysUserDepts!=null&&sysUserDepts.size()>0){
					for(SysUserDept sysUserDept:sysUserDepts){
                        sysUserDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
						userDeptMapper.updateByPrimaryKeySelective(sysUserDept);
					}
				}
				if (StringUtil.isNotBlank(sysUser.getUserFlow()) &&
						StringUtil.isNotBlank(sysUser.getDeptFlow()) &&
						StringUtil.isNotBlank(cuurUser.getOrgFlow())){
					SysUserDept sysUserDept = new SysUserDept();
					sysUserDept.setOrgFlow(cuurUser.getOrgFlow());
					sysUserDept.setOrgName(cuurUser.getOrgName());
					sysUserDept.setUserFlow(sysUser.getUserFlow());
					sysUserDept.setDeptFlow(sysUser.getDeptFlow());
					sysUserDept.setDeptName(sysUser.getDeptName());
					sysUserDept.setRecordFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(sysUserDept, true);
					userDeptMapper.insertSelective(sysUserDept);
				}
				count ++;
			}
		}
		return count;
	}

	private int parseDiscAndResponExcel(Workbook wb, String type) {
		SysUser cuurUser = GlobalContext.getCurrentUser();
		String cuurOrgFlow = cuurUser.getOrgFlow();
		String cuurOrgName = cuurUser.getOrgName();
		List<String> orgFlows=new ArrayList<>();
		orgFlows.add(cuurUser.getOrgFlow());
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			SysOrg org = orgBiz.readSysOrg(cuurUser.getOrgFlow());
			if (org != null) {
                if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
					List<SysOrg> jointOrgs = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
					if (jointOrgs != null) {
						for (SysOrg so : jointOrgs) {
							orgFlows.add(so.getOrgFlow());
						}
					}
				}
			}
		}
		int count = 0;
		int sheetNum = wb.getNumberOfSheets();
		if (sheetNum > 0) {
			List<String> colnames = new ArrayList<String>();
			Sheet sheet;
			try {
				sheet = wb.getSheetAt(0);
			} catch (Exception e) {
				sheet = wb.getSheetAt(0);
			}
			int row_num = sheet.getLastRowNum() + 1;
			//获取表头
			Row titleR = sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			String title = "";
			for (int i = 0; i < cell_num; i++) {
				Cell cell = titleR.getCell(i);
				if (cell == null) {
					throw new RuntimeException("导入失败！第" + (count + 2) + "列，表头不能为空！");
				} else {
					title = cell.getStringCellValue();
				}
				colnames.add(title);
			}
			if(row_num<=1)
			{
				return -1;
			}
			for (int i = 1; i < row_num; i++) {
				Row r = sheet.getRow(i);
				SysUser student = null;
				ResDoctor resDoctor = null;
				SysUser teacher = null;
				ResStudentDiscipleTeacher studentDiscipleTeacher = null;
				ResResponsibleteacherDoctor responsibleteacherDoctor = null;
				for (int j = 0; j < colnames.size(); j++) {
					String value = "";
					String currTitle = colnames.get(j);
					Cell cell = r.getCell(j);
					if (cell != null && StringUtil.isNotBlank(cell.toString().trim())) {
						if (cell.getCellType().getCode() == 1) {
							value = cell.getStringCellValue().trim();
						} else {
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}

					if ("身份证号".equals(currTitle)) {
						if (StringUtil.isNotBlank(value)) {
							student = findByIdNo(value);
							if(student == null){
								throw new RuntimeException("导入失败！第" + (count + 2) + "行，身份证号不存在！");
							}else {
								resDoctor = doctorBiz.readDoctor(student.getUserFlow());
								if(resDoctor == null){
									throw new RuntimeException("导入失败！第" + (count + 2) + "行，该学员不是医师！");
								}
							}
						} else {
							throw new RuntimeException("导入失败！第" + (count + 2) + "行，身份证号不能为空！");
						}
					} else if ("师承老师用户名".equals(currTitle)) {
						if (StringUtil.isNotBlank(value)) {
							teacher = findByUserCodeAndOrgFlows(value, orgFlows);
							if (teacher != null) {
								List<SysUserRole> teacherRoles = userRoleBiz.getByUserFlow(teacher.getUserFlow());
								String roleFlow = InitConfig.getSysCfg("res_disciple_role_flow");
								Boolean isDisciple = false;
								if(StringUtil.isNotBlank(roleFlow)){
									for(SysUserRole tempRole : teacherRoles){
										if(roleFlow.equals(tempRole.getRoleFlow())){
											isDisciple = true;
											continue;
										}
									}
									if (isDisciple) {
										//导入师承老师需要维护res_doctor 和 Res_tudent_Disciple_Teacher 两张表
										resDoctor.setDiscipleTeacherFlow(teacher.getUserFlow());
										resDoctor.setDiscipleTeacherName(teacher.getUserName());
										studentDiscipleTeacher = new ResStudentDiscipleTeacher();
										studentDiscipleTeacher.setDoctorFlow(student.getUserFlow());
										studentDiscipleTeacher.setDoctorName(student.getUserName());
										studentDiscipleTeacher.setTeacherFlow(teacher.getUserFlow());
										studentDiscipleTeacher.setTeacherName(teacher.getUserName());
									} else {
										throw new RuntimeException("导入失败！第" + (count + 2) + "行，请为" + value + "绑定师承老师角色！");
									}
								}else {
									throw new RuntimeException("导入失败！师承老师角色未配置，请联系系统管理员");
								}
							} else {
								throw new RuntimeException("导入失败！第" + (count + 2) + "行，师承老师用户名不存在！");
							}
						} else {
							throw new RuntimeException("导入失败！第" + (count + 2) + "行，师承老师用户名不能为空！");
						}
					}else if ("责任导师用户名".equals(currTitle)) {
						if (StringUtil.isNotBlank(value)) {
							teacher = findByUserCodeAndOrgFlow(value, cuurOrgFlow);
							if (teacher != null) {
								List<SysUserRole> teacherRoles = userRoleBiz.getByUserFlow(teacher.getUserFlow());
								String roleFlow = InitConfig.getSysCfg("res_responsible_teacher_role_flow");
								Boolean isResponsible = false;
								if(StringUtil.isNotBlank(roleFlow)){
									for(SysUserRole tempRole : teacherRoles){
										if(roleFlow.equals(tempRole.getRoleFlow())){
											isResponsible = true;
											continue;
										}
									}
									if (isResponsible) {
										// 责任导师系统可以绑定多个，如果导入的数据，系统中对应的学员有一个责任导师，直接覆盖，
										// 有多个责任导师则覆盖最新的一条，没有责任导师则新建。
										ResResponsibleteacherDoctor responsibleteacher4Search = new ResResponsibleteacherDoctor();
										responsibleteacher4Search.setDoctorFlow(student.getUserFlow());
										responsibleteacher4Search.setOrgFlow(cuurOrgFlow);
										List<ResResponsibleteacherDoctor> responsibleteachers = responsibleTeacherDoctorBiz.search(responsibleteacher4Search);
										if(responsibleteachers != null && responsibleteachers.size() > 0){
											responsibleteacherDoctor = responsibleteachers.get(0);
										}else {
											responsibleteacherDoctor = new ResResponsibleteacherDoctor();
										}
										responsibleteacherDoctor.setOrgFlow(cuurOrgFlow);
										responsibleteacherDoctor.setDoctorFlow(student.getUserFlow());
										responsibleteacherDoctor.setDoctorName(student.getUserName());
										responsibleteacherDoctor.setResponsibleteacherFlow(teacher.getUserFlow());
										responsibleteacherDoctor.setResponsibleteacherName(teacher.getUserName());
									} else {
										throw new RuntimeException("导入失败！第" + (count + 2) + "行，请为" + value + "绑定责任导师角色！");
									}
								}else {
									throw new RuntimeException("导入失败！责任导师角色未配置，请联系系统管理员！");
								}


							} else {
								throw new RuntimeException("导入失败！第" + (count + 2) + "行，责任导师用户名不存在！");
							}
						} else {
							throw new RuntimeException("导入失败！第" + (count + 2) + "行，责任导师用户名不能为空！");
						}
					}
				}
				//执行保存
				if("Disciple".equals(type)){
					if(studentDiscipleTeacher != null && resDoctor != null){
						discipleBiz.saveStudentDiscipleTeacher(studentDiscipleTeacher);
						doctorBiz.editDoctor(resDoctor);
					}else {
						throw new RuntimeException("导入失败！第" + (count + 2) + "行，Excel格式有误，请用系统模板！");
					}
				}else if("Responsible".equals(type)) {
					if(responsibleteacherDoctor != null){
						responsibleTeacherDoctorBiz.edit(responsibleteacherDoctor);
					}else {
						throw new RuntimeException("导入失败！第" + (count + 2) + "行，Excel格式有误，请用系统模板！");
					}
				}
				count++;
			}
		}
		return count;
	}

	private SysUser findByUserCodeAndOrgFlows(String userCode, List<String> orgFlows) {
		SysUserExample sysUserExample=new SysUserExample();
		SysUserExample.Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserCodeEqualTo(userCode).andOrgFlowIn(orgFlows);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
		// 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
		if (!inS.markSupported()) {
			// 还原流信息
			inS = new PushbackInputStream(inS);
		}
//		// EXCEL2003使用的是微软的文件系统
//		if (POIFSFileSystem.hasPOIFSHeader(inS)) {
//			return new HSSFWorkbook(inS);
//		}
//		// EXCEL2007使用的是OOM文件格式
//		if (POIXMLDocument.hasOOXMLHeader(inS)) {
//			// 可以直接传流参数，但是推荐使用OPCPackage容器打开
//			return new XSSFWorkbook(OPCPackage.open(inS));
//		}
		try{
			return WorkbookFactory.create(inS);
		}catch (Exception e) {
			throw new IOException("不能解析的excel版本");
		}
	}

	private int parseExcel(Workbook wb){
		int count = 0;
		int sheetNum = wb.getNumberOfSheets();
		if(sheetNum>0){
			List<String> colnames = new ArrayList<String>();
			Sheet sheet;
			try{
				sheet = wb.getSheetAt(0);
			}catch(Exception e){
				sheet = wb.getSheetAt(0);
			}
			int row_num = sheet.getLastRowNum();
			//获取表头
			Row titleR =  sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			String title = "";
			for(int i = 0 ; i <cell_num; i++){
				title = titleR.getCell(i).getStringCellValue();
				 colnames.add(title);
			}
			for(int i = 1;i <= row_num; i++){
				Row r =  sheet.getRow(i);
            	SysUser sysUser = new SysUser();
            	String userFlow;
            	String userName;
            	String idNo;
            	String userEmail;
            	String userPhone;
            	String orgFlow;
            	String orgName;
            	String deptFlow;
            	String deptName;
            	String postName;
            	String userCode;
				for (int j = 0; j < colnames.size(); j++) {
					String value = "";
				    Cell cell = r.getCell(j);
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if (cell.getCellType().getCode() == 1) {
							value = cell.getStringCellValue().trim();
						} else {
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					/* 用户编号	员工姓名	身份证  邮件电话 	机构编号	机构名称	科室编号	科室名称	职务	 登录名*/
					if("用户编号".equals(colnames.get(j))) {
						userFlow = value;
						sysUser.setUserFlow(userFlow);
					}else if("员工姓名".equals(colnames.get(j))){
						userName = value;
						sysUser.setUserName(userName);
					}else if("身份证".equals(colnames.get(j))){
						idNo = value;
						sysUser.setIdNo(idNo);
					}else if("邮件".equals(colnames.get(j))){
						userEmail = value;
						sysUser.setUserEmail(userEmail);
					}else if("电话".equals(colnames.get(j))){
						userPhone = value;
						sysUser.setUserPhone(userPhone);
//					}else if("机构编号".equals(colnames.get(j))){
//						orgFlow = value;
//						sysUser.setOrgFlow(orgFlow);
					}else if("机构名称".equals(colnames.get(j))){
						orgName = value;
						sysUser.setOrgName(orgName);
					}else if("科室编号".equals(colnames.get(j))){
						deptFlow = value;
						sysUser.setDeptFlow(deptFlow);
					}else if("科室名称".equals(colnames.get(j))){
						deptName = value;
						sysUser.setDeptName(deptName);
					}else if("职务".equals(colnames.get(j))){
						postName = value;
						sysUser.setPostName(postName);
					}else if("登录名".equals(colnames.get(j))){
						userCode = value;
						sysUser.setUserCode(userCode);
					}
				}
				if(StringUtil.isBlank(sysUser.getUserFlow())){
					continue;
				}
				if(StringUtil.isBlank(sysUser.getOrgName())&&StringUtil.isNotBlank(sysUser.getDeptName()))
				{
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，请同时输入机构名称与科室名称！");
				}
				if(StringUtil.isNotBlank(sysUser.getPostName()))
				{
                    sysUser.setPostId(getDictId(sysUser.getPostName(), com.pinde.core.common.enums.DictTypeEnum.UserPost.getId()));
					if(StringUtil.isBlank(sysUser.getPostId()))
					{
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，职务名称与系统字典不匹配！");
					}
				}
				if(StringUtil.isNotBlank(sysUser.getOrgName()))
				{
					SysOrg org=orgBiz.readSysOrgByName(sysUser.getOrgName());
					if(org==null)
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，机构名称输入不正确！");
					sysUser.setOrgFlow(org.getOrgFlow());
				}

				if(StringUtil.isNotBlank(sysUser.getOrgFlow())&&StringUtil.isNotBlank(sysUser.getDeptName()))
				{
					SysDept dept=deptBiz.readSysDeptByName(sysUser.getOrgFlow(),sysUser.getDeptName());
					if(dept==null)
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，【"+sysUser.getDeptName()+"】不属于【"+sysUser.getOrgName()+"】机构！");
					sysUser.setDeptFlow(dept.getDeptFlow());
				}
				if(StringUtil.isBlank(sysUser.getUserCode())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，登录名不能为空！");
				}
				//验证惟一用户登录名
				if(StringUtil.isNotBlank(sysUser.getUserCode())){
					SysUserExample example=new SysUserExample();
                    example.createCriteria().andOrgFlowEqualTo(sysUser.getOrgFlow()).andUserCodeEqualTo(sysUser.getUserCode()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					List<SysUser> sysUserList = sysUserMapper.selectByExample(example);
					if(sysUserList != null && !sysUserList.isEmpty()){
						throw new RuntimeException("导入失败！第"+(count+2) +"行，当前系统已存在登录名为"+sysUser.getUserCode()+"的用户");
					}
				}
				//执行保存
				if(StringUtil.isNotBlank(sysUser.getUserFlow())){
					SysUser exitSysUser = readSysUser(sysUser.getUserFlow());
					if(exitSysUser != null){
						updateUser(sysUser);
					}else{
						addUser(sysUser);
					}
				}else{
					sysUser.setUserFlow(PkUtil.getUUID());
					addUser(sysUser);
				}
				count ++;
			}
		}
		return count;
	}

	private int parseExcelAndAudit(Workbook wb,SysUser user){

		int count = 0;
		int sheetNum = wb.getNumberOfSheets();
		if(sheetNum>0){
			List<String> colnames = new ArrayList<String>();
			Sheet sheet;
			try{
				sheet = wb.getSheetAt(0);
			}catch(Exception e){
				sheet = wb.getSheetAt(0);
			}
			int row_num = sheet.getLastRowNum();
			//获取表头
			Row titleR =  sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			String title = "";
			for(int i = 0 ; i <cell_num; i++){
				title = titleR.getCell(i).getStringCellValue();
				colnames.add(title);
			}
			for(int i = 1;i <= row_num; i++){
				Row r =  sheet.getRow(i);
				SysUser sysUser = new SysUser();
				String userName;
				String userPhone;
				String deptName;
				String userCode;
				for (int j = 0; j < colnames.size(); j++) {
					String value = "";
					Cell cell = r.getCell(j);
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if (cell.getCellType().getCode() == 1) {
							value = cell.getStringCellValue().trim();
						} else {
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					if("姓名".equals(colnames.get(j))){
						userName = value;
						sysUser.setUserName(userName);
					}else if("联系方式".equals(colnames.get(j))){
						userPhone = value;
						sysUser.setUserPhone(userPhone);
					}else if("科室名称".equals(colnames.get(j))){
						deptName = value;
						sysUser.setDeptName(deptName);
					}else if("登录名".equals(colnames.get(j))){
						userCode = value;
						sysUser.setUserCode(userCode);
					}
				}
				//验证惟一用户登录名
				if(StringUtil.isNotBlank(sysUser.getUserCode())){
					SysUserExample example=new SysUserExample();
                    example.createCriteria().andOrgFlowEqualTo(user.getOrgFlow()).andUserCodeEqualTo(sysUser.getUserCode()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					List<SysUser> sysUserList = sysUserMapper.selectByExample(example);
					if(sysUserList != null && !sysUserList.isEmpty()){
						throw new RuntimeException("导入失败！第"+(count+2) +"行，当前系统已存在登录名为"+sysUser.getUserCode()+"的用户");
					}
				}
				//执行保存
				if(StringUtil.isNotBlank(sysUser.getUserFlow())){
					SysUser exitSysUser = readSysUser(sysUser.getUserFlow());
					if(exitSysUser != null){
						updateUser(sysUser);
					}else{
						sysUser.setAuditStatus("待审核");
						sysUser.setOrgFlow(user.getOrgFlow());
						sysUser.setOrgName(user.getOrgName());
						addTeaching(sysUser);
					}
				}else{
					sysUser.setUserFlow(PkUtil.getUUID());
					sysUser.setAuditStatus("待审核");
					sysUser.setOrgFlow(user.getOrgFlow());
					sysUser.setOrgName(user.getOrgName());
					addTeaching(sysUser);
				}
				count ++;
			}
		}
		return count;
	}

	@Override
	public String uploadImg(String userFlow,MultipartFile file) {
		if(file!=null){
			try{
				BufferedImage image = ImageIO.read(file.getInputStream());
				//如果image=null 表示上传的不是图片格式
				if (image != null) {
					int imageWidth = image.getWidth(); //获取图片宽度，单位px
					int imageHeight = image.getHeight(); //获取图片高度，单位px

					if(imageWidth < 413 || imageHeight < 626){
                        return com.pinde.core.common.GlobalConstant.FILE_PIXEL_ERROR;// 分辨率不小于413*626
					}
				}
			} catch (IOException e){
				logger.error("splitUserBizImpl uploadImg error:{}",e);
			}

			List<String> mimeList = new ArrayList<String>();
			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
				mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
			}
			List<String> suffixList = new ArrayList<String>();
			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
				suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
			}

			String fileType = file.getContentType();//MIME类型;
			String fileName = file.getOriginalFilename();//文件名
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			if(!(mimeList.contains(fileType)&&suffixList.contains(suffix))){
                return com.pinde.core.common.GlobalConstant.UPLOAD_IMG_TYPE_ERROR;
			}
//			long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
			if(file.getSize()<=150*1024){
				return "图片大小不得小于150K" ;
			}
			if(file.getSize()>300*1024){
                return com.pinde.core.common.GlobalConstant.UPLOAD_IMG_SIZE_ERROR + "300K";
			}
			try {
				/*创建目录*/
				String dateString = DateUtil.getCurrDate2();
				String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+"userImages"+File.separator + dateString ;
				File fileDir = new File(newDir);
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				/*文件名*/
				fileName = file.getOriginalFilename();
				fileName = PkUtil.getUUID()+fileName.substring(fileName.lastIndexOf("."));
				File newFile = new File(fileDir, fileName);
				file.transferTo(newFile);
				String url = "userImages/"+dateString+"/"+fileName;
				System.out.println("==================头像上传路径:"+newDir);
				if(StringUtil.isNotBlank(userFlow)){
					SysUser user = new SysUser();
					user.setUserFlow(userFlow);
					user.setUserHeadImg(url);
					saveUser(user);
				}
				FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
				String localFilePath=fileDir+File.separator+fileName;
				String ftpDir= "userImages"+File.separator +dateString ;
				String ftpFileName=fileName;
				ftpHelperUtil.uploadFile(localFilePath,ftpDir,ftpFileName);
				return url;
			} catch (Exception e) {
                logger.error("", e);
                return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}
//	@Override
//	public String uploadImgWithWatermark(String userFlow,MultipartFile file) {
//		if(file!=null){
//			List<String> mimeList = new ArrayList<String>();
//			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
//				mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
//			}
//			List<String> suffixList = new ArrayList<String>();
//			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
//				suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
//			}
//
//			String fileType = file.getContentType();//MIME类型;
//			String fileName = file.getOriginalFilename();//文件名
//			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
//			if(!(mimeList.contains(fileType)&&suffixList.contains(suffix))){
//				return com.pinde.core.common.GlobalConstant.UPLOAD_IMG_TYPE_ERROR;
//			}
//			long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
//			if(file.getSize()>limitSize*1024*1024){
//				return com.pinde.core.common.GlobalConstant.UPLOAD_IMG_SIZE_ERROR +limitSize +"M" ;
//			}
//			try {
//				/*创建目录*/
//				String dateString = DateUtil.getCurrDate2();
//				String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+"userImages"+File.separator + dateString ;
//				File fileDir = new File(newDir);
//				if(!fileDir.exists()){
//					fileDir.mkdirs();
//				}
//				/*文件名*/
//				fileName = file.getOriginalFilename();
//				fileName = PkUtil.getUUID()+fileName.substring(fileName.lastIndexOf("."));
//				File waterFile = new File(fileDir, fileName);
//
//				file.transferTo(waterFile);
//
//				//备份原始图片
//				String originalFileName = "original_"+fileName;
//				File originalFile = new File(fileDir, originalFileName);
//				fileChannelCopy(waterFile,originalFile);
//
//				String url = "userImages/"+dateString+"/"+fileName;
//				if(StringUtil.isNotBlank(userFlow)){
//					SysUser user = new SysUser();
//					user.setUserFlow(userFlow);
//					user.setUserHeadImg(url);
//					saveUser(user);
//
//					user = readSysUser(userFlow);
//					if(StringUtil.isNotBlank(user.getIdNo())){
//						ImageUtils.pressText(user.getIdNo(),waterFile,  "TimesRoman",Font.BOLD, 0,14,140,15);
//					}
//				}
//				return "success:"+url;
//			} catch (Exception e) {
//				 logger.error("",e);
//				return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
//			}
//		}
//		return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
//	}
//	public void fileChannelCopy(File s, File t) {
//        FileInputStream fi = null;
//        FileOutputStream fo = null;
//        FileChannel in = null;
//        FileChannel out = null;
//        try {
//
//            fi = new FileInputStream(s);
//
//            fo = new FileOutputStream(t);
//
//            in = fi.getChannel();//得到对应的文件通道
//
//            out = fo.getChannel();//得到对应的文件通道
//
//            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
//
//        } catch (IOException e) {
//
//             logger.error("",e);
//
//        } finally {
//
//            try {
//                fi.close();
//                in.close();
//                fo.close();
//                out.close();
//
//            } catch (IOException e) {
//
//                 logger.error("",e);
//            }
//        }
//    }

	//	@Override
//	public List<SysUserDept> searchUserDeptByDept(String deptFlow){
//		SysUserDeptExample example = new SysUserDeptExample();
//		example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDeptFlowEqualTo(deptFlow);
//		return userDeptMapper.selectByExample(example);
//	}
//
	@Override
	public List<SysUserDept> searchUserDeptByUser(String userFlow){
		SysUserDeptExample example = new SysUserDeptExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(userFlow);
		return userDeptMapper.selectByExample(example);
	}

	@Override
	public List<SysUser> searchUserByDeptAndRole(String deptFlow,String roleFlow){
		return userExtMapper.searchUserByDeptAndRole(deptFlow, roleFlow);
	}

	@Override
	public SysUser findByIdNoAndCretTypeNotSelf(String userFlow, String idNo,
			String cretTypeId) {
		SysUserExample sysUserExample=new SysUserExample();
		SysUserExample.Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andIdNoEqualTo(idNo);
		criteria.andCretTypeIdEqualTo(cretTypeId);
		criteria.andUserFlowNotEqualTo(userFlow);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()==1){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public List<SysUser> searchUserNotInUserFlows(String orgFlow,List<String> userFlows){
		SysUserExample sysUserExample=new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow);
		if(userFlows!=null&&userFlows.size()>0){
			criteria.andUserFlowNotIn(userFlows);
		}
		sysUserExample.setOrderByClause(" NLSSORT(USER_NAME,'NLS_SORT = SCHINESE_PINYIN_M')");
		return sysUserMapper.selectByExample(sysUserExample);
	}
	@Override
	public SysUser findByFlow(String userFlow) {
		return sysUserMapper.selectByPrimaryKey(userFlow);
	}

//	@Override
//	public List<SysUser> searchAfterAuditUser(ResDoctorSchProcess process,SysUser user){
//		return userExtMapper.searchAfterAuditUser(process,user);
//	}

//	@Override
//	public List<SysUser> findUserByRoleFlow(String orgFlow, String roleFlow) {
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		if(StringUtil.isNotBlank(orgFlow)){
//			paramMap.put("orgFlow", orgFlow);
//		}
//		if(StringUtil.isNotBlank(roleFlow)){
//			paramMap.put("roleFlow", roleFlow);
//		}
//		return this.userExtMapper.selectUserByRoleFlow(paramMap);
//	}

//	@Override
//	public List<SysUser> searchUserByUserCode(String userCode) {
//		SysUserExample sysUserExample=new SysUserExample();
//		Criteria criteria=sysUserExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//		if(StringUtil.isNotBlank(userCode)){
//			criteria.andUserCodeEqualTo(userCode);
//		}
//		return sysUserMapper.selectByExample(sysUserExample);
//	}

	@Override
	public List<SysUser> teacherRoleCheckUser(String deptFlow, String role,String doctorName,String userFlow) {
		Map<String,String> map=new HashMap<String, String>();
		map.put("deptFlow", deptFlow);
		map.put("role", role);
		map.put("doctorName", doctorName);
		map.put("userFlow", userFlow);
		List<SysUser> sysUserList=userExtMapper.teacherRoleCheckUser(map);
		return sysUserList;
	}

	@Override
	public List<SysUser> teacherRoleCheckUser2(String deptFlow, String role, String doctorName, String userFlow) {
		Map<String,String> map=new HashMap<String, String>();
		map.put("deptFlow", deptFlow);
		map.put("role", role);
		map.put("doctorName", doctorName);
		map.put("userFlow", userFlow);
		map.put("speAdminAdeptFlow", deptFlow);
		List<SysUser> sysUserList=userExtMapper.teacherRoleCheckUser(map);
		return sysUserList;
	}

	@Override
	public List<SysUser> getUserByOrg(String orgFlow){
		SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andStatusIdEqualTo("Activated");
		if(StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		example.setOrderByClause(" dept_flow desc");
		return sysUserMapper.selectByExample(example);
	}

	@Override
	public List<SysUser> searchSysUserByLikeCode(SysUser sysUser) {
		SysUserExample sysUserExample=new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		sysUserExample.setOrderByClause(" NLSSORT(USER_NAME,'NLS_SORT = SCHINESE_PINYIN_M')");
		if (StringUtil.isNotBlank(sysUser.getIdNo())) {
			criteria.andIdNoLike("%"+sysUser.getIdNo()+"%");
		}
		if(StringUtil.isNotBlank(sysUser.getUserName())){
			criteria.andUserNameLike("%"+sysUser.getUserName()+"%");
		}
		if (StringUtil.isNotBlank(sysUser.getIsOwnerStu())) {
			criteria.andIsOwnerStuEqualTo(sysUser.getIsOwnerStu());
		}
		return sysUserMapper.selectByExample(sysUserExample);
	}

	@Override
	public List<SysUser> getUserByDeptFlows(List<String> deptFlows) {
		if(deptFlows!=null&&!deptFlows.isEmpty())
		{
			SysUserExample sysUserExample=new SysUserExample();
            SysUserExample.Criteria criteria = sysUserExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andStatusIdEqualTo(UserStatusEnum.Activated.getId()).andDeptFlowIn(deptFlows);
			sysUserExample.setOrderByClause(" DEPT_FLOW ,NLSSORT(USER_NAME,'NLS_SORT = SCHINESE_PINYIN_M')");
			return sysUserMapper.selectByExample(sysUserExample);
		}
		return null;
	}

	@Override
	public List<SysUser> searchResManageUserByModeDept2(SysUser user, String moreDept, List<String> roleList, String roleFlow, List<String> orgFlowList) {

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("deptFlow", user.getDeptFlow());
		map.put("deptName", user.getDeptName());
		map.put("idNo", user.getIdNo());
		map.put("userPhone", user.getUserPhone());
		map.put("userEmail", user.getUserEmail());
		map.put("userName", user.getUserName());
		map.put("statusId", user.getStatusId());
		map.put("orgFlow", user.getOrgFlow());
		map.put("moreDept",moreDept);
		map.put("userFlow",user.getUserFlow());
		map.put("roleFlow",roleFlow);
		map.put("userCode",user.getUserCode());
		map.put("certificateLevelId",user.getCertificateLevelId());
        map.put("isAll", com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(roleList!=null && roleList.size()>0){
			map.put("roleList", roleList);
		}
		if(orgFlowList!=null && orgFlowList.size()>0){
			map.put("orgFlowList", orgFlowList);
		}
		return userExtMapper.searchResManageUser(map);
	}

	@Override
	public List<SysUser> searchResManageUser2(SysUser user, List<String> roleList, String roleFlow, List<String> orgFlowList) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("deptFlow", user.getDeptFlow());
		map.put("deptName", user.getDeptName());
		map.put("idNo", user.getIdNo());
		map.put("userPhone", user.getUserPhone());
		map.put("userEmail", user.getUserEmail());
		map.put("userName", user.getUserName());
		map.put("statusId", user.getStatusId());
		map.put("orgFlow", user.getOrgFlow());
		map.put("userFlow",user.getUserFlow());
		map.put("roleFlow",roleFlow);
		map.put("userCode",user.getUserCode());
		map.put("certificateLevelId",user.getCertificateLevelId());
        map.put("isAll", com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(roleList!=null && roleList.size()>0){
			map.put("roleList", roleList);
		}
		if(orgFlowList!=null && orgFlowList.size()>0){
			map.put("orgFlowList", orgFlowList);
		}
		return userExtMapper.searchResManageUser(map);
	}
	@Override
	public List<SysUser> searchRecruitManagers(SysUser user, List<String> roleList) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("deptFlow", user.getDeptFlow());
		map.put("deptName", user.getDeptName());
		map.put("idNo", user.getIdNo());
		map.put("userPhone", user.getUserPhone());
		map.put("userEmail", user.getUserEmail());
		map.put("userName", user.getUserName());
		map.put("statusId", user.getStatusId());
		map.put("orgFlow", user.getOrgFlow());
		map.put("userFlow",user.getUserFlow());
		map.put("userCode",user.getUserCode());
        map.put("isAll", com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(roleList!=null && roleList.size()>0){
			map.put("roleList", roleList);
		}
		return userExtMapper.searchRecruitManagers(map);
	}
	@Override
	public List<SysUser> readDeptTeachAndHead(String deptFlow, String teacherRoleFlow, String headRoleFlow, String trainTeacherRoleFlow) {
		List<String> roles=new ArrayList<>();
		if(StringUtil.isNotBlank(teacherRoleFlow))
			roles.add(teacherRoleFlow);
		if(StringUtil.isNotBlank(headRoleFlow))
			roles.add(headRoleFlow);
		if(StringUtil.isNotBlank(trainTeacherRoleFlow))
			roles.add(trainTeacherRoleFlow);
		if(roles.size()==0)
			return null;
		return userExtMapper.readDeptTeachAndHead(deptFlow,roles);
	}
}
