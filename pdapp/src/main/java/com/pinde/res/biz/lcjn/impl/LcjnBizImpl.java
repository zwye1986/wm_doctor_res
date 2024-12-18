package com.pinde.res.biz.lcjn.impl;


import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.sci.dao.SysUserMapper;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.lcjn.ILcjnBiz;
import com.pinde.res.dao.jswjw.ext.SysUserExtMapper;
import com.pinde.res.dao.lcjn.ext.LcjnCourseInfoExtMapper;
import com.pinde.sci.dao.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class LcjnBizImpl implements ILcjnBiz{
	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private SysUserExtMapper sysUserExtMapper;
	@Resource
	private SysUserRoleMapper userRoleMapper;
	@Resource
	private SysCfgMapper cfgMapper;
	@Autowired
	private SysDictMapper sysDictMapper;
	@Autowired
	private LcjnCourseInfoExtMapper courseInfoExtMapper;
	@Autowired
	private LcjnCourseInfoMapper courseInfoMapper;
	@Autowired
	private LcjnCourseTimeMapper timeMapper;
	@Autowired
	private LcjnCourseTeaMapper teaMapper;
	@Autowired
	private LcjnCourseSkillMapper skillMapper;
	@Autowired
	private LcjnDoctorCourseMapper doctorCourseMapper;
	@Autowired
	private LcjnDoctorSiginMapper siginMapper;
	@Autowired
	private LcjnCourseEvaluateMapper courseEvaluateMapper;
	@Autowired
	private LcjnCourseEvaluateDetailMapper courseEvaluateDetailMapper;
	@Autowired
	private LcjnTeaEvaluateMapper teaEvaluateMapper;
	@Autowired
	private LcjnTeaEvaluateDetailMapper teaEvaluateDetailMapper;

	@Override
	public SysUser readUserByCode(String userCode) {
		SysUserExample example=new SysUserExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andUserCodeEqualTo(userCode);
		List<SysUser> list=sysUserMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int register(SysUser user) {
		String userFlow= PkUtil.getUUID();
		user.setUserFlow(userFlow);
		user.setUserPasswd(PasswordHelper.encryptPassword(userFlow,user.getUserPasswd()));
		user.setCreateTime(DateUtil.getCurrDateTime());
		user.setCreateUserFlow(userFlow);
		user.setModifyTime(DateUtil.getCurrDateTime());
		user.setModifyUserFlow(userFlow);
        user.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		return sysUserMapper.insertSelective(user);
	}
	//获取用户拥有的角色列表
	@Override
	public List<SysUserRole> getSysUserRole(String userFlow){
		SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andUserFlowEqualTo(userFlow);
		return userRoleMapper.selectByExample(example);
	}
	//获取系统配置信息
	@Override
	public String getCfgCode(String code){
		if(StringUtil.isNotBlank(code)){
			SysCfg cfg = cfgMapper.selectByPrimaryKey(code);
			if(cfg!=null){
				String val = cfg.getCfgValue();
				if(!StringUtil.isNotBlank(val)){
					val = cfg.getCfgBigValue();
				}
				return val;
			}
		}
		return null;
	}

	@Override
	public int addUserRole(SysUserRole userRole) {
		return userRoleMapper.insertSelective(userRole);
	}

	@Override
	public SysUser readUserByFlow(String userFlow) {
		return sysUserMapper.selectByPrimaryKey(userFlow);
	}
	@Override
	public List<SysDict> getDictListByDictId(String doctorTrainingSpe) {
		if(StringUtil.isNotBlank(doctorTrainingSpe))
		{
			SysDictExample example = new SysDictExample();
            example.createCriteria().andDictTypeIdEqualTo(doctorTrainingSpe).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<SysDict> sysDictList = sysDictMapper.selectByExample(example);

			return sysDictList;
		}
		return null;
	}
	@Override
	public List<SysDict> getDictListByDictId(String doctorTrainingSpe,String orgFlow) {
		if(StringUtil.isNotBlank(doctorTrainingSpe)&&StringUtil.isNotBlank(orgFlow))
		{
			SysDictExample example = new SysDictExample();
            example.createCriteria().andDictTypeIdEqualTo(doctorTrainingSpe).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
			.andOrgFlowEqualTo(orgFlow);
			example.setOrderByClause("ordinal asc");
			List<SysDict> sysDictList = sysDictMapper.selectByExample(example);

			return sysDictList;
		}
		return null;
	}

	@Override
	public SysUser readUserByIdNo(String idNo) {
		SysUserExample example=new SysUserExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andIdNoEqualTo(idNo);
		List<SysUser> list=sysUserMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public SysUser readUserByPhone(String userPhone) {
		SysUserExample example=new SysUserExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andUserPhoneEqualTo(userPhone);
		List<SysUser> list=sysUserMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public SysUser readUserByEmail(String userEmail) {
		SysUserExample example=new SysUserExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andUserEmailEqualTo(userEmail);
		List<SysUser> list=sysUserMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int save(SysUser user) {
		if(StringUtil.isNotBlank(user.getUserFlow()))
		{
			String userFlow= user.getUserFlow();
			user.setModifyTime(DateUtil.getCurrDateTime());
			user.setModifyUserFlow(userFlow);
			return sysUserMapper.updateByPrimaryKeySelective(user);
		}else{
			String userFlow= PkUtil.getUUID();
			user.setUserFlow(userFlow);
			user.setUserPasswd(PasswordHelper.encryptPassword(userFlow,user.getUserPasswd()));
			user.setCreateTime(DateUtil.getCurrDateTime());
			user.setCreateUserFlow(userFlow);
			user.setModifyTime(DateUtil.getCurrDateTime());
			user.setModifyUserFlow(userFlow);
            user.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			return sysUserMapper.insertSelective(user);
		}
	}

	@Override
	public List<Map<String,Object>> getLcjnCourseInfo(Map<String,String> params) {
		return courseInfoExtMapper.getLcjnCourseInfo(params);
	}

	@Override
	public List<Map<String,Object>> getLcjnDocCourseInfo(Map<String,String> params) {
		return courseInfoExtMapper.getLcjnDocCourseInfo(params);
	}

	@Override
	public String getCourseMinTrainStartDate(String courseFlow) {
		return courseInfoExtMapper.getCourseMinTrainStartDate(courseFlow);
	}

	@Override
	public List<LcjnDoctorSigin> getSiginListByFlow(String userFlow, String courseFlow) {
		LcjnDoctorSiginExample example=new LcjnDoctorSiginExample();
        example.createCriteria().andCourseFlowEqualTo(courseFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(userFlow);
		List<LcjnDoctorSigin> list=siginMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}

	@Override
	public boolean checkIsEval(String userFlow, String courseFlow) {
		boolean f=false;
		if(!f) {
			//课程评价
			LcjnCourseEvaluateExample example = new LcjnCourseEvaluateExample();
            example.createCriteria().andCourseFlowEqualTo(courseFlow).andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<LcjnCourseEvaluate> list = courseEvaluateMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				f = true;
			}
		}
		if(!f) {
			//老师评价
			LcjnTeaEvaluateExample example = new LcjnTeaEvaluateExample();
            example.createCriteria().andCourseFlowEqualTo(courseFlow).andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<LcjnTeaEvaluate> list = teaEvaluateMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				f = true;
			}
		}
		return f;
	}

	@Override
	public LcjnCourseEvaluate getCourseEvaluate(String userFlow, String courseFlow) {
		//课程评价
		LcjnCourseEvaluateExample example = new LcjnCourseEvaluateExample();
        example.createCriteria().andCourseFlowEqualTo(courseFlow).andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<LcjnCourseEvaluate> list = courseEvaluateMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return  list.get(0);
		}
		return null;
	}

	@Override
	public List<LcjnCourseEvaluateDetail> getCourseEvaluateDetail(String evaluateFlow) {
		LcjnCourseEvaluateDetailExample example = new LcjnCourseEvaluateDetailExample();
        example.createCriteria().andEvaluateFlowEqualTo(evaluateFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<LcjnCourseEvaluateDetail> list = courseEvaluateDetailMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<LcjnTeaEvaluate> getCourseTeaEvalByFlow(String userFlow, String courseFlow) {
		//老师评价
		LcjnTeaEvaluateExample example = new LcjnTeaEvaluateExample();
        example.createCriteria().andCourseFlowEqualTo(courseFlow).andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<LcjnTeaEvaluate> list = teaEvaluateMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<LcjnTeaEvaluateDetail> getCourseTeaEvalDeatil(String teaEvaluateFlow) {
		LcjnTeaEvaluateDetailExample example = new LcjnTeaEvaluateDetailExample();
        example.createCriteria().andTeaEvaluateFlowEqualTo(teaEvaluateFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<LcjnTeaEvaluateDetail> list = teaEvaluateDetailMapper.selectByExample(example);
		return list;
	}


	@Override
	public LcjnDoctorCourse getDoctorCourseByFlow(String recordFlow) {
		return doctorCourseMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public LcjnDoctorCourse getDoctorCourseByCourseAndDocFlow(String userFlow, String courseFlow) {
		LcjnDoctorCourseExample example=new LcjnDoctorCourseExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(userFlow).andCourseFlowEqualTo(courseFlow);
		List<LcjnDoctorCourse> list=doctorCourseMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public LcjnCourseInfo getLcjnCourseInfoByFlow(String courseFlow) {
		return courseInfoMapper.selectByPrimaryKey(courseFlow);
	}

	@Override
	public List<LcjnCourseTime> getCourseTimeByFlow(String courseFlow) {
		LcjnCourseTimeExample example=new LcjnCourseTimeExample();
		example.createCriteria().andCourseFlowEqualTo(courseFlow)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("TRAIN_START_DATE ASC,START_TIME ASC");
		return timeMapper.selectByExample(example);
	}

	@Override
	public List<LcjnCourseTea> getCourseTeaByFlow(String courseFlow) {
		LcjnCourseTeaExample example=new LcjnCourseTeaExample();
		example.createCriteria().andCourseFlowEqualTo(courseFlow)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("CREATE_TIME ASC");
		return teaMapper.selectByExample(example);
	}

	@Override
	public List<LcjnCourseSkill> getCourseSkillByFlow(String courseFlow) {
		LcjnCourseSkillExample example=new LcjnCourseSkillExample();
		example.createCriteria().andCourseFlowEqualTo(courseFlow)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("CREATE_TIME ASC");
		return skillMapper.selectByExample(example);
	}

	@Override
	public int getCourseLastNumByFlow(String courseFlow) {
		if(StringUtil.isBlank(courseFlow))
		{
			return 0;
		}
		return courseInfoExtMapper.getCourseLastNumByFlow(courseFlow);
	}

	@Override
	public int checkTrainTime(String userFlow, String courseFlow) {
		return courseInfoExtMapper.checkTrainTime(userFlow,courseFlow);
	}

	@Override
	public int saveDoctorCourse(LcjnDoctorCourse doctorCourse) {

		String userFlow= doctorCourse.getDoctorFlow();
		if(StringUtil.isNotBlank(doctorCourse.getRecordFlow()))
		{
			doctorCourse.setModifyTime(DateUtil.getCurrDateTime());
			doctorCourse.setModifyUserFlow(userFlow);
			return doctorCourseMapper.updateByPrimaryKeySelective(doctorCourse);
		}else{
			doctorCourse.setRecordFlow(PkUtil.getUUID());
			doctorCourse.setCreateTime(DateUtil.getCurrDateTime());
			doctorCourse.setCreateUserFlow(userFlow);
			doctorCourse.setModifyTime(DateUtil.getCurrDateTime());
			doctorCourse.setModifyUserFlow(userFlow);
            doctorCourse.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			return doctorCourseMapper.insertSelective(doctorCourse);
		}
	}
	@Override
	public int addCourseEval(LcjnCourseEvaluate evaluate) {
		LcjnCourseEvaluate old=courseEvaluateMapper.selectByPrimaryKey(evaluate.getEvaluateFlow());
		String userFlow= evaluate.getDoctorFlow();
		if(old!=null){
			evaluate.setModifyTime(DateUtil.getCurrDateTime());
			evaluate.setModifyUserFlow(userFlow);
			return courseEvaluateMapper.updateByPrimaryKeySelective(evaluate);
		}else {
			evaluate.setCreateTime(DateUtil.getCurrDateTime());
			evaluate.setCreateUserFlow(userFlow);
			evaluate.setModifyTime(DateUtil.getCurrDateTime());
			evaluate.setModifyUserFlow(userFlow);
            evaluate.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			return courseEvaluateMapper.insertSelective(evaluate);
		}
	}

	@Override
	public int addTeacherEvl(LcjnTeaEvaluate evaluate) {
		LcjnTeaEvaluate old=teaEvaluateMapper.selectByPrimaryKey(evaluate.getTeaEvaluateFlow());
		String userFlow= evaluate.getDoctorFlow();
		if(old!=null){
			evaluate.setModifyTime(DateUtil.getCurrDateTime());
			evaluate.setModifyUserFlow(userFlow);
			return teaEvaluateMapper.updateByPrimaryKeySelective(evaluate);
		}else {
			evaluate.setCreateTime(DateUtil.getCurrDateTime());
			evaluate.setCreateUserFlow(userFlow);
			evaluate.setModifyTime(DateUtil.getCurrDateTime());
			evaluate.setModifyUserFlow(userFlow);
            evaluate.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			return teaEvaluateMapper.insertSelective(evaluate);
		}
	}

	@Override
	public LcjnTeaEvaluateDetail getTeaEvaluateDetailByFlow(String teaEvaluateFlow, String dictId) {

		LcjnTeaEvaluateDetailExample example = new LcjnTeaEvaluateDetailExample();
        example.createCriteria().andTeaEvaluateFlowEqualTo(teaEvaluateFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
		.andDictIdEqualTo(dictId);
		List<LcjnTeaEvaluateDetail> list = teaEvaluateDetailMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public LcjnCourseEvaluateDetail getCourseEvaluateDetailByFlow( String evaluateFlow, String dictId){
		LcjnCourseEvaluateDetailExample example = new LcjnCourseEvaluateDetailExample();
        example.createCriteria().andEvaluateFlowEqualTo(evaluateFlow).andDictIdEqualTo(dictId).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<LcjnCourseEvaluateDetail> list = courseEvaluateDetailMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int saveCourseEvalDetail(LcjnCourseEvaluateDetail d, String userFlow) {
		if(StringUtil.isNotBlank(d.getRecordFlow())){
			d.setModifyTime(DateUtil.getCurrDateTime());
			d.setModifyUserFlow(userFlow);
			return courseEvaluateDetailMapper.updateByPrimaryKeySelective(d);
		}else {
			d.setRecordFlow(PkUtil.getUUID());
			d.setCreateTime(DateUtil.getCurrDateTime());
			d.setCreateUserFlow(userFlow);
			d.setModifyTime(DateUtil.getCurrDateTime());
			d.setModifyUserFlow(userFlow);
            d.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			return courseEvaluateDetailMapper.insertSelective(d);
		}
	}
	@Override
	public int saveTeaEvalDetail(LcjnTeaEvaluateDetail d, String userFlow) {
		if(StringUtil.isNotBlank(d.getRecordFlow())){
			d.setModifyTime(DateUtil.getCurrDateTime());
			d.setModifyUserFlow(userFlow);
			return teaEvaluateDetailMapper.updateByPrimaryKeySelective(d);
		}else {
			d.setRecordFlow(PkUtil.getUUID());
			d.setCreateTime(DateUtil.getCurrDateTime());
			d.setCreateUserFlow(userFlow);
			d.setModifyTime(DateUtil.getCurrDateTime());
			d.setModifyUserFlow(userFlow);
            d.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			return teaEvaluateDetailMapper.insertSelective(d);
		}
	}

	@Override
	public LcjnTeaEvaluate getTeaEvalByFlow(String userFlow, String teaFlow, String courseFlow) {
		//老师评价
		LcjnTeaEvaluateExample example = new LcjnTeaEvaluateExample();
		example.createCriteria().andCourseFlowEqualTo(courseFlow).andDoctorFlowEqualTo(userFlow)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
		.andUserFlowEqualTo(teaFlow);
		List<LcjnTeaEvaluate> list = teaEvaluateMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
  