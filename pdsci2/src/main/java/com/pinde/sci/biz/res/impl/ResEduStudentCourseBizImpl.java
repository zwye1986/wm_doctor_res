package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResEduStudentCourseBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.EduStudentCourseMapper;
import com.pinde.sci.dao.res.ResEduCourseExtMapper;
import com.pinde.sci.dao.res.ResEduStudentCourseExtMapper;
import com.pinde.sci.enums.res.ResEduCourseCategoryEnum;
import com.pinde.sci.form.res.OneCourseCreditForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.EduStudentCourseExample.Criteria;
import com.pinde.sci.model.res.EduStudentCourseExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResEduStudentCourseBizImpl implements IResEduStudentCourseBiz {
	@Autowired
	private EduStudentCourseMapper studentCourseMapper;
	@Autowired
	private ResEduStudentCourseExtMapper studentCourseExtMapper;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private ResEduCourseExtMapper eduCourseExtMapper;

	@Override
	public int save(EduStudentCourse eduStudentCourse) {
		if (StringUtil.isNotBlank(eduStudentCourse.getRecordFlow())) {
			GeneralMethod.setRecordInfo(eduStudentCourse, false);
			return studentCourseMapper.updateByPrimaryKeySelective(eduStudentCourse);
		} else {
			eduStudentCourse.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(eduStudentCourse, true);
			return studentCourseMapper.insertSelective(eduStudentCourse);

		}
	}

	public int update(EduStudentCourse course) {
		GeneralMethod.setRecordInfo(course, false);
		return studentCourseMapper.updateByPrimaryKeySelective(course);
	}

	@Override
	public Map<String, Object> searchCourseCreditForm(
			String courseFlow) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("courseFlow", courseFlow);
		List<OneCourseCreditForm> fromList = this.studentCourseExtMapper.searchCourseCreditForm(paramMap);
		Map<String, Object> userAndCourseCreditMap = new HashMap<String, Object>();
		if (fromList != null && !fromList.isEmpty()) {
			for (OneCourseCreditForm form : fromList) {
				userAndCourseCreditMap.put(form.getUserFlow(), form.getCourseCredit());
			}
		}
		return userAndCourseCreditMap;
	}

	//查询某学院需要添加的必修课
	public List<EduCourse> searchUserNeedAddCourse(SysUser sysUser, SchDept schDept) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<EduCourse> courseList = null;
		if (schDept != null) {
			if (StringUtil.isNotBlank(schDept.getSchDeptFlow())) {
				paramMap.put("schDeptFlow", schDept.getSchDeptFlow());
			}
			if (StringUtil.isNotBlank(schDept.getDeptFlow())) {
				paramMap.put("deptFlow", schDept.getDeptFlow());
			}
		}
		paramMap.put("courseCategoryId", ResEduCourseCategoryEnum.PrejobTrain.getId());
		if (sysUser != null) {
			paramMap.put("sysUser", sysUser);
			ResDoctor resDoctor = this.resDoctorBiz.readDoctor(sysUser.getUserFlow());
			if (resDoctor != null) {
				paramMap.put("resDoctor", resDoctor);
				courseList = this.eduCourseExtMapper.selectAddCoursesPersonal(paramMap);
			}
		}
		return courseList;
	}

	@Override
	public List<EduStudentCourse> searchStudentCourseList(EduStudentCourse studentCourse) {
		EduStudentCourseExample example = new EduStudentCourseExample();
		Criteria criteria = example.createCriteria();
		addCriteria(studentCourse, criteria);
		return studentCourseMapper.selectByExample(example);
	}

	private void addCriteria(EduStudentCourse studentCourse, Criteria criteria) {
		if (StringUtil.isNotBlank(studentCourse.getCourseFlow())) {
			criteria.andCourseFlowEqualTo(studentCourse.getCourseFlow());
		}
		if (StringUtil.isNotBlank(studentCourse.getUserFlow())) {
			criteria.andUserFlowEqualTo(studentCourse.getUserFlow());
		}
		if (StringUtil.isNotBlank(studentCourse.getCourseTypeId())) {
			criteria.andCourseTypeIdEqualTo(studentCourse.getCourseTypeId());
		}
		if (StringUtil.isNotBlank(studentCourse.getRecordStatus())) {
			criteria.andRecordStatusEqualTo(studentCourse.getRecordStatus());
		}
		if (StringUtil.isNotBlank(studentCourse.getAuditFlag())) {
			criteria.andAuditFlagEqualTo(studentCourse.getAuditFlag());
		}
		if (StringUtil.isNotBlank(studentCourse.getReplenishFlag())) {
			criteria.andReplenishFlagEqualTo(studentCourse.getReplenishFlag());
		}
		if (StringUtil.isNotBlank(studentCourse.getStudentPeriod())) {
			criteria.andStudentPeriodEqualTo(studentCourse.getStudentPeriod());
		}
		if (StringUtil.isNotBlank(studentCourse.getCourseCode())) {
			criteria.andCourseCodeEqualTo(studentCourse.getCourseCode());
		}
		if (StringUtil.isNotBlank(studentCourse.getImpFlow())) {
			criteria.andImpFlowEqualTo(studentCourse.getImpFlow());
		}
	}

	@Override
	public List<EduStudentCourse> searchStudentCourseList(List<String> courseFlowList) {
		EduStudentCourseExample example = new EduStudentCourseExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (courseFlowList != null && courseFlowList.size() > 0) {
			criteria.andCourseFlowIn(courseFlowList);
		}
		return this.studentCourseMapper.selectByExample(example);
	}

	@Override
	public List<EduStudentCourseExt> searchStudentCourse(SysUser sysUser,
														 EduUser eduUser, ResDoctor doctor, EduStudentCourse studentCourse) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", sysUser.getUserName());
		paramMap.put("sid", eduUser.getSid());
		paramMap.put("trainOrgId", eduUser.getTrainOrgId());
		paramMap.put("idNo", sysUser.getIdNo());
		paramMap.put("impFlow", studentCourse.getImpFlow());
		paramMap.put("studentPeriod", studentCourse.getStudentPeriod());
		paramMap.put("gradeTermId", studentCourse.getGradeTermId());
		paramMap.put("courseName", studentCourse.getCourseName());
		paramMap.put("resDoctor", doctor);

		return studentCourseExtMapper.searchStudentCourse(paramMap);
	}
}
