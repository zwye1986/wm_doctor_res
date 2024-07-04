package com.pinde.res.biz.lcjn.impl;


import com.pinde.app.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.biz.lcjn.ILcjnBiz;
import com.pinde.res.ctrl.jswjw.ImageFileForm;
import com.pinde.res.dao.jswjw.ext.*;
import com.pinde.res.dao.lcjn.ext.LcjnCourseInfoExtMapper;
import com.pinde.res.dao.stdp.ext.StdpResDoctorExtMapper;
import com.pinde.res.enums.jswjw.BaseStatusEnum;
import com.pinde.res.enums.jswjw.TrainYearEnum;
import com.pinde.res.enums.stdp.RecStatusEnum;
import com.pinde.res.enums.stdp.RegistryTypeEnum;
import com.pinde.res.enums.stdp.ResRecTypeEnum;
import com.pinde.res.model.lcjn.mo.JsonData;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.SysUserExample.Criteria;
import com.pinde.sci.util.PasswordHelper;
import com.pinde.sci.util.PicZoom;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
@Transactional(rollbackFor=Exception.class)
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
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
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
		user.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		return sysUserMapper.insertSelective(user);
	}
	//获取用户拥有的角色列表
	@Override
	public List<SysUserRole> getSysUserRole(String userFlow){
		SysUserRoleExample example = new SysUserRoleExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
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
			example.createCriteria().andDictTypeIdEqualTo(doctorTrainingSpe).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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
			example.createCriteria().andDictTypeIdEqualTo(doctorTrainingSpe).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
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
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
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
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
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
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
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
			user.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
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
		example.createCriteria().andCourseFlowEqualTo(courseFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
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
			example.createCriteria().andCourseFlowEqualTo(courseFlow).andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			List<LcjnCourseEvaluate> list = courseEvaluateMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				f = true;
			}
		}
		if(!f) {
			//老师评价
			LcjnTeaEvaluateExample example = new LcjnTeaEvaluateExample();
			example.createCriteria().andCourseFlowEqualTo(courseFlow).andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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
		example.createCriteria().andCourseFlowEqualTo(courseFlow).andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<LcjnCourseEvaluate> list = courseEvaluateMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return  list.get(0);
		}
		return null;
	}

	@Override
	public List<LcjnCourseEvaluateDetail> getCourseEvaluateDetail(String evaluateFlow) {
		LcjnCourseEvaluateDetailExample example = new LcjnCourseEvaluateDetailExample();
		example.createCriteria().andEvaluateFlowEqualTo(evaluateFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<LcjnCourseEvaluateDetail> list = courseEvaluateDetailMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<LcjnTeaEvaluate> getCourseTeaEvalByFlow(String userFlow, String courseFlow) {
		//老师评价
		LcjnTeaEvaluateExample example = new LcjnTeaEvaluateExample();
		example.createCriteria().andCourseFlowEqualTo(courseFlow).andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<LcjnTeaEvaluate> list = teaEvaluateMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<LcjnTeaEvaluateDetail> getCourseTeaEvalDeatil(String teaEvaluateFlow) {
		LcjnTeaEvaluateDetailExample example = new LcjnTeaEvaluateDetailExample();
		example.createCriteria().andTeaEvaluateFlowEqualTo(teaEvaluateFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
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
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("TRAIN_START_DATE ASC,START_TIME ASC");
		return timeMapper.selectByExample(example);
	}

	@Override
	public List<LcjnCourseTea> getCourseTeaByFlow(String courseFlow) {
		LcjnCourseTeaExample example=new LcjnCourseTeaExample();
		example.createCriteria().andCourseFlowEqualTo(courseFlow)
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("CREATE_TIME ASC");
		return teaMapper.selectByExample(example);
	}

	@Override
	public List<LcjnCourseSkill> getCourseSkillByFlow(String courseFlow) {
		LcjnCourseSkillExample example=new LcjnCourseSkillExample();
		example.createCriteria().andCourseFlowEqualTo(courseFlow)
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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
			doctorCourse.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
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
			evaluate.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
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
			evaluate.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			return teaEvaluateMapper.insertSelective(evaluate);
		}
	}

	@Override
	public LcjnTeaEvaluateDetail getTeaEvaluateDetailByFlow(String teaEvaluateFlow, String dictId) {

		LcjnTeaEvaluateDetailExample example = new LcjnTeaEvaluateDetailExample();
		example.createCriteria().andTeaEvaluateFlowEqualTo(teaEvaluateFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
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
		example.createCriteria().andEvaluateFlowEqualTo(evaluateFlow).andDictIdEqualTo(dictId).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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
			d.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
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
			d.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			return teaEvaluateDetailMapper.insertSelective(d);
		}
	}

	@Override
	public int saveEvalData(JsonData data, String userFlow, String courseFlow) {
		LcjnCourseInfo info=getLcjnCourseInfoByFlow(courseFlow);
		SysUser user=readUserByFlow(userFlow);
		//课程评价
		LcjnCourseEvaluate evaluate=getCourseEvaluate(userFlow,courseFlow);
		if(evaluate==null)
			evaluate=new LcjnCourseEvaluate();
		String evaluateFlow=evaluate.getEvaluateFlow();
		if(StringUtil.isBlank(evaluateFlow))
		{
			evaluateFlow=PkUtil.getUUID();
		}
		evaluate.setEvaluateFlow(evaluateFlow);
		evaluate.setDoctorFlow(userFlow);
		evaluate.setDoctorName(user.getUserName());
		evaluate.setCourseFlow(courseFlow);
		evaluate.setCourseName(info.getCourseName());
		evaluate.setEvalContent(data.getCourse().getEvalContent());
		addCourseEval(evaluate);
		//课程评价详情
		List<JsonData.CourseBean.CourseEvaListBean> courseEvaList=data.getCourse().getCourseEvaList();
		if(courseEvaList!=null)
		{
			for(JsonData.CourseBean.CourseEvaListBean b:courseEvaList)
			{
				LcjnCourseEvaluateDetail d=getCourseEvaluateDetailByFlow(evaluateFlow,b.getDictId());
				if(d==null)
					d=new LcjnCourseEvaluateDetail();
				d.setEvaluateFlow(evaluateFlow);
				d.setDictId(b.getDictId());
				d.setDictName(b.getDictName());
				d.setEvalScore(b.getEvalScore());
				saveCourseEvalDetail(d,userFlow);
			}
		}
		//教师评价
		List<JsonData.TeasBean> teasBeanList=data.getTeas();
		if(teasBeanList!=null)
		{
			for(JsonData.TeasBean tea:teasBeanList)
			{
				//教师评价
				String teaFlow=tea.getUserFlow();
				LcjnTeaEvaluate teaEvaluate=getTeaEvalByFlow(userFlow,teaFlow,courseFlow);
				if(teaEvaluate==null)
					teaEvaluate=new LcjnTeaEvaluate();

				String teaEvaluateFlow=teaEvaluate.getTeaEvaluateFlow();
				if(StringUtil.isBlank(teaEvaluateFlow))
				{
					teaEvaluateFlow=PkUtil.getUUID();
				}
				teaEvaluate.setTeaEvaluateFlow(teaEvaluateFlow);
				teaEvaluate.setDoctorFlow(userFlow);
				teaEvaluate.setDoctorName(user.getUserName());
				teaEvaluate.setUserFlow(teaFlow);
				teaEvaluate.setUserName(tea.getUserName());
				teaEvaluate.setEvalContent(tea.getEvalContent());
				teaEvaluate.setCourseFlow(courseFlow);
				teaEvaluate.setCourseName(info.getCourseName());
				addTeacherEvl(teaEvaluate);
				//评价详情
				List<JsonData.TeasBean.TeacherEvaListBean> teacherEvaListBeanList=tea.getTeacherEvaList();
				if(teacherEvaListBeanList!=null)
				{
					for(JsonData.TeasBean.TeacherEvaListBean b:teacherEvaListBeanList)
					{
						LcjnTeaEvaluateDetail d=getTeaEvaluateDetailByFlow(teaEvaluateFlow,b.getDictId());
						if(d==null)
							d=new LcjnTeaEvaluateDetail();
						d.setTeaEvaluateFlow(teaEvaluateFlow);
						d.setDictId(b.getDictId());
						d.setDictName(b.getDictName());
						d.setEvalScore(b.getEvalScore());
						saveTeaEvalDetail(d,userFlow);
					}
				}
			}
		}
		return 1;
	}

	@Override
	public int checkThisTimeIsInTrain(String nowTime, String courseFlow) {
		return courseInfoExtMapper.checkThisTimeIsInTrain(nowTime,courseFlow);
	}

	@Override
	public int saveSigin(LcjnDoctorSigin d) {
		String userFlow=d.getDoctorFlow();
		if(StringUtil.isNotBlank(d.getRecordFlow())){
			d.setModifyTime(DateUtil.getCurrDateTime());
			d.setModifyUserFlow(userFlow);
			return siginMapper.updateByPrimaryKeySelective(d);
		}else {
			d.setRecordFlow(PkUtil.getUUID());
			d.setCreateTime(DateUtil.getCurrDateTime());
			d.setCreateUserFlow(userFlow);
			d.setModifyTime(DateUtil.getCurrDateTime());
			d.setModifyUserFlow(userFlow);
			d.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			return siginMapper.insertSelective(d);
		}
	}

	@Override
	public LcjnTeaEvaluate getTeaEvalByFlow(String userFlow, String teaFlow, String courseFlow) {
		//老师评价
		LcjnTeaEvaluateExample example = new LcjnTeaEvaluateExample();
		example.createCriteria().andCourseFlowEqualTo(courseFlow).andDoctorFlowEqualTo(userFlow)
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andUserFlowEqualTo(teaFlow);
		List<LcjnTeaEvaluate> list = teaEvaluateMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
  