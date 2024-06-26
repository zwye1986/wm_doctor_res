package com.pinde.sci.biz.edu.impl;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.edu.*;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResRecBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.EduStudentCourseMapper;
import com.pinde.sci.dao.edu.EduCourseExtMapper;
import com.pinde.sci.dao.edu.EduStudentCourseExtMapper;
import com.pinde.sci.enums.edu.ChooseCourseStatusEnum;
import com.pinde.sci.enums.edu.EduCourseCategoryEnum;
import com.pinde.sci.enums.edu.EduCourseTypeEnum;
import com.pinde.sci.enums.edu.EduStudyStatusEnum;
import com.pinde.sci.form.edu.OneCourseCreditForm;
import com.pinde.sci.form.edu.StudentCourseNumForm;
import com.pinde.sci.model.edu.EduStudentCourseExt;
import com.pinde.sci.model.edu.EduUserExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.EduStudentCourseExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
@Transactional(rollbackFor = Exception.class)
public class EduStudentCourseBizImpl implements IEduStudentCourseBiz{
	@Autowired
	private EduStudentCourseMapper studentCourseMapper;
	@Autowired
	private EduStudentCourseExtMapper studentCourseExtMapper;
	@Autowired
	private IEduCourseRequireRefBiz requireRefBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IEduUserBiz eduUserBiz;
	@Autowired
	private EduCourseExtMapper eduCourseExtMapper;
	@Autowired
	private IEduCourseBiz courseBiz;
	@Autowired
	private IEduCourseScoreRefBiz scoreRefBiz;
	@Autowired
	private IEduCoursePeriodRefBiz periodRefBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IEduCourseMajorBiz courseMajorBiz;

	@Override
	public int save(EduStudentCourse eduStudentCourse) {
		if(StringUtil.isNotBlank(eduStudentCourse.getRecordFlow())){
			GeneralMethod.setRecordInfo(eduStudentCourse, false);
			return studentCourseMapper.updateByPrimaryKeySelective(eduStudentCourse);
		}else{
			eduStudentCourse.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(eduStudentCourse, true);
			return studentCourseMapper.insertSelective(eduStudentCourse);
			
		}
	}
	public int update(EduStudentCourse course){
		GeneralMethod.setRecordInfo(course, false);
		return studentCourseMapper.updateByPrimaryKeySelective(course);
	}
	@Override
	public Map<String,Map<String, Object>> searchStudentChooseCourses(
			List<EduUserExt> eduUserExtList) {
		Map<String,Map<String, Object>> allCourseByUserMap=new HashMap<String, Map<String,Object>>();
		Map<String, Object> requiredCourseMap=new HashMap<String, Object>();
		Map<String, Object> optionalCourseMap=new HashMap<String, Object>();
		Map<String, Object> publicCourseMap=new HashMap<String, Object>();
		Map<String,Object> allCreditMap=new HashMap<String, Object>();
		Map<String,Object> actualCreditMap=new HashMap<String, Object>();
		if(eduUserExtList!=null && !eduUserExtList.isEmpty()){
			for(EduUserExt eduUserExt:eduUserExtList){
				int allCredit=0;
				int actualCredit=0;
				Map<String,Object> paramMap=new HashMap<String, Object>();
				paramMap.put("userFlow", eduUserExt.getUserFlow());
				paramMap.put("courseTypeId", EduCourseTypeEnum.Required.getId());
				List<EduStudentCourseExt> eduStudentCourseExtRequiredList=this.studentCourseExtMapper.searchEduStudentCourseExtByUserFlow(paramMap);
				paramMap.put("courseTypeId", EduCourseTypeEnum.Optional.getId());
				List<EduStudentCourseExt> eduStudentCourseExtOptionalList=this.studentCourseExtMapper.searchEduStudentCourseExtByUserFlow(paramMap);
				paramMap.put("courseTypeId", EduCourseTypeEnum.Public.getId());
				List<EduStudentCourseExt> eduStudentCourseExtPublicList=this.studentCourseExtMapper.searchEduStudentCourseExtByUserFlow(paramMap);
				requiredCourseMap.put(eduUserExt.getUserFlow(), eduStudentCourseExtRequiredList);
				optionalCourseMap.put(eduUserExt.getUserFlow(), eduStudentCourseExtOptionalList);
				publicCourseMap.put(eduUserExt.getUserFlow(), eduStudentCourseExtPublicList);
			    if(eduStudentCourseExtRequiredList!=null && !eduStudentCourseExtRequiredList.isEmpty()){
			    	for(EduStudentCourseExt eduStudentCourseExt:eduStudentCourseExtRequiredList){
			    		allCredit=allCredit+Integer.parseInt(eduStudentCourseExt.getCourse().getCourseCredit());
			    		if(EduStudyStatusEnum.Finish.getId().equals(eduStudentCourseExt.getStudyStatusId())
			    		  && GlobalConstant.RECORD_STATUS_Y.equals(eduStudentCourseExt.getAchieveCredit())){
			    			actualCredit=actualCredit+Integer.parseInt(eduStudentCourseExt.getCourse().getCourseCredit());
			    		}
			    	}
			    }
			    if(eduStudentCourseExtOptionalList!=null && !eduStudentCourseExtOptionalList.isEmpty()){
			    	for(EduStudentCourseExt eduStudentCourseExt:eduStudentCourseExtOptionalList){
			    		allCredit=allCredit+Integer.parseInt(eduStudentCourseExt.getCourse().getCourseCredit());
			    		if(EduStudyStatusEnum.Finish.getId().equals(eduStudentCourseExt.getStudyStatusId())
			    		  && GlobalConstant.RECORD_STATUS_Y.equals(eduStudentCourseExt.getAchieveCredit())){
			    			actualCredit=actualCredit+Integer.parseInt(eduStudentCourseExt.getCourse().getCourseCredit());
			    		}
			    	}
			    }
			    if(eduStudentCourseExtPublicList!=null && !eduStudentCourseExtPublicList.isEmpty()){
			    	for(EduStudentCourseExt eduStudentCourseExt:eduStudentCourseExtPublicList){
			    		allCredit=allCredit+Integer.parseInt(eduStudentCourseExt.getCourse().getCourseCredit());
			    		if(EduStudyStatusEnum.Finish.getId().equals(eduStudentCourseExt.getStudyStatusId())
			    	      && GlobalConstant.RECORD_STATUS_Y.equals(eduStudentCourseExt.getAchieveCredit())){
			    			actualCredit=actualCredit+Integer.parseInt(eduStudentCourseExt.getCourse().getCourseCredit());
			    		}
			    	}
			    }
			    allCreditMap.put(eduUserExt.getUserFlow(), allCredit);
			    actualCreditMap.put(eduUserExt.getUserFlow(), actualCredit);
			}
		}
		allCourseByUserMap.put("required", requiredCourseMap);
		allCourseByUserMap.put("optional", optionalCourseMap);
		allCourseByUserMap.put("allCreditMap", allCreditMap);
		allCourseByUserMap.put("actualCreditMap", actualCreditMap);
		return allCourseByUserMap;
	}

	@Override
	public Map<String,Object> searchCourseCreditForm(
			String courseFlow) {
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("courseFlow", courseFlow);
		List<OneCourseCreditForm> fromList=this.studentCourseExtMapper.searchCourseCreditForm(paramMap);
		Map<String,Object> userAndCourseCreditMap=new HashMap<String, Object>();
	    if(fromList!=null && !fromList.isEmpty()){
	    	for(OneCourseCreditForm form:fromList){
	    		userAndCourseCreditMap.put(form.getUserFlow(), form.getCourseCredit());
	    	}
	    }
		return userAndCourseCreditMap;
	}

//	@Override
//	public String saveStudentRequiredCourse(SysUser sysUser,String schDeptFlow) {
//		SchDept schDept=this.schDeptBiz.readSchDept(schDeptFlow);
//		ResDoctor resDoctor=null;
//		if(sysUser!=null){
//			if(StringUtil.isNotBlank(sysUser.getUserFlow())){
//				resDoctor=this.resDoctorBiz.readDoctor(sysUser.getUserFlow());
//			}
//		}
//		List<EduCourse> courseList=searchUserNeedAddCourse(sysUser,schDept);
//		//插入到选课表中
//		if(courseList !=null && !courseList.isEmpty()){
//			EduStudentCourse studentCourse=null;
//			for(EduCourse course:courseList){
//				String scoreBl="";
//				String periodBl="";
//				if(resDoctor!=null){
//					//查询该门课是否计学分
//					scoreBl=this.scoreRefBiz.searchScoreJurisdiction(resDoctor.getTrainingSpeId(), sysUser.getUserFlow(), schDeptFlow, course.getCourseFlow());
//					//查询该门课是否计学时
//					periodBl=this.periodRefBiz.searchPeriodJurisdiction(resDoctor.getTrainingSpeId(), sysUser.getUserFlow(), schDeptFlow, course.getCourseFlow());
//				}
//				//插入课程
//					studentCourse=new EduStudentCourse();
//					studentCourse.setUserFlow(sysUser.getUserFlow());
//					studentCourse.setCourseFlow(course.getCourseFlow());
//					studentCourse.setCourseName(course.getCourseName());
//					studentCourse.setStudyStatusId(EduStudyStatusEnum.NotStarted.getId());
//					studentCourse.setStudyStatusName(EduStudyStatusEnum.NotStarted.getName());
//					studentCourse.setCourseTypeId(EduCourseTypeEnum.Required.getId());
//					studentCourse.setCourseTypeName(EduCourseTypeEnum.Required.getName());
//					studentCourse.setChooseTime(DateUtil.getCurrDateTime(DateUtil.defDtPtn02));
//					if(GlobalConstant.FLAG_Y.equals(scoreBl)){
//						studentCourse.setCourseCredit(course.getCourseCredit());
//					}else{
//						studentCourse.setCourseCredit("0");
//					}
//					if(GlobalConstant.FLAG_Y.equals(periodBl)){
//						studentCourse.setCoursePeriod(course.getCoursePeriod());
//					}else{
//						studentCourse.setCoursePeriod("0");
//					}
//					if(schDept!=null){
//						if(StringUtil.isNotBlank(schDept.getSchDeptFlow())){
//							studentCourse.setSchDeptFlow(schDept.getSchDeptFlow());
//							studentCourse.setSchDeptName(schDept.getSchDeptName());
//						}
//					}
//					int result=this.saveCourseAnnualTrain(studentCourse, course, sysUser);
//					if(result!=GlobalConstant.ONE_LINE){
//						return GlobalConstant.SAVE_FAIL;
//					}
//				}
//			}
//		return GlobalConstant.SAVE_SUCCESSED;
//	}
	
	//查询某学院需要添加的必修课
    public List<EduCourse> searchUserNeedAddCourse(SysUser sysUser,SchDept schDept){
    	Map<String,Object> paramMap=new HashMap<String, Object>();
		List<EduCourse> courseList=null;
		if(schDept!=null){
			if(StringUtil.isNotBlank(schDept.getSchDeptFlow())){
				paramMap.put("schDeptFlow", schDept.getSchDeptFlow());
			}
			if(StringUtil.isNotBlank(schDept.getDeptFlow())){
				paramMap.put("deptFlow", schDept.getDeptFlow());
			}
		}
		paramMap.put("courseCategoryId", EduCourseCategoryEnum.PrejobTrain.getId());
	    if(sysUser!=null){
	       paramMap.put("sysUser", sysUser);
	       ResDoctor resDoctor=this.resDoctorBiz.readDoctor(sysUser.getUserFlow());
	    	if(resDoctor!=null){
	    		paramMap.put("resDoctor", resDoctor);
	    		courseList=this.eduCourseExtMapper.selectAddCoursesPersonal(paramMap);
	    	}
	    }
    	return courseList;
    }

//	@Override
//	public List<EduStudentCourseExt> searchStuCourses(
//			EduCourse eduCourse,SysUser sysUser,List<String> studyStatusIdList,List<String> courseTypeIdList) {
//		    Map<String,Object> paramMap=new HashMap<String, Object>();
//		    if(eduCourse!=null){
//		    	paramMap.put("eduCourse", eduCourse);
//		    }
//			if(sysUser!=null){
//				paramMap.put("sysUser", sysUser);
//			}
//			if(studyStatusIdList!=null && !studyStatusIdList.isEmpty()){
//				paramMap.put("studyStatusIdList", studyStatusIdList);
//			}
//			if(courseTypeIdList!=null && !courseTypeIdList.isEmpty()){
//				paramMap.put("courseTypeIdList", courseTypeIdList);
//			}
//		List<EduStudentCourseExt> stuCourseExtList=studentCourseExtMapper.searchEduStudentCourseExt(paramMap);
//		return stuCourseExtList;
//	}

//	@Override
//	public Map<String,Object> searchUserCreditMap(SysUser user,String deptFlow) {
//		Map<String,Object> resultMap=new HashMap<String, Object>();
//		Map<String,Object> paramMap=new HashMap<String, Object>();
//		if(user!=null){
//			paramMap.put("sysUser", user);
//		}
//		paramMap.put("creditFlag", GlobalConstant.FLAG_Y);
//		paramMap.put("deptFlow", deptFlow);
//        //查询学生已获得学分
//	    int achieveCredit=this.studentCourseExtMapper.sumUserCredit(paramMap);
//	    //查询学生未获得学分
//	    paramMap.put("creditFlag", "");
//	    int notAchieveCredit=this.studentCourseExtMapper.sumUserCredit(paramMap);
//	    resultMap.put("achieveCredit", achieveCredit);
//	    resultMap.put("notAchieveCredit", notAchieveCredit);
//		return resultMap;
//	}

//	@Override
//	public List<EduStudentCourse> searchUserNearStudyCourse(SysUser sysUser) {
//        EduStudentCourseExample example=new EduStudentCourseExample();
//        Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//        if(sysUser!=null){
//        	if(StringUtil.isBlank(sysUser.getUserFlow())){
//        		criteria.andUserFlowEqualTo(sysUser.getUserFlow());
//        	}
//        }
//        example.setOrderByClause("modify_time desc");
//		return studentCourseMapper.selectByExample(example);
//	}

//	@Override
//	public List<EduCourseExt> searchFindCoursePersonal(SysUser sysUser,ResDoctor resDoctor,
//			String schDeptFlow, String order, EduCourse eduCourse) {
//
//		Map<String,Object> paramMap=new HashMap<String, Object>();
//		List<EduCourseExt> courseList=null;
//		if(StringUtil.isNotBlank(schDeptFlow)){
//			paramMap.put("schDeptFlow", schDeptFlow);
//		}
//		if(StringUtil.isNotBlank(order)){
//			paramMap.put("order", order);
//		}
//		if(eduCourse!=null){
//			paramMap.put("eduCourse", eduCourse);
//		}
//	    if(sysUser!=null){
//	       paramMap.put("sysUser", sysUser);
//	    	if(resDoctor!=null){
//	    		paramMap.put("resDoctor", resDoctor);
//	    		courseList=this.eduCourseExtMapper.selectFindCoursesPersonal(paramMap);
//	    	}
//	    }
//    	return courseList;
//	}

//	@Override
//	public List<EduCourseExt> searchFindCourseNoDoctor(SysUser sysUser,ResDoctor resDoctor,
//			String schDeptFlow, String checkSchDeptFlow,String isCredit,String isPeriod, EduCourse eduCourse) {
//		Map<String,Object> paramMap=new HashMap<String, Object>();
//		List<EduCourseExt> courseList=null;
//		if(StringUtil.isNotBlank(checkSchDeptFlow)){
//			paramMap.put("checkSchDeptFlow", checkSchDeptFlow);
//		}
//		if(StringUtil.isNotBlank(isCredit)){
//			paramMap.put("isCredit", isCredit);
//		}
//		if(StringUtil.isNotBlank(isPeriod)){
//			paramMap.put("isPeriod", isPeriod);
//		}
//		if(StringUtil.isNotBlank(schDeptFlow)){
//			paramMap.put("schDeptFlow", schDeptFlow);
//		}
//		if(eduCourse!=null){
//			paramMap.put("eduCourse", eduCourse);
//		}
//	    if(sysUser!=null){
//	       paramMap.put("sysUser", sysUser);
//	       paramMap.put("resDoctor", resDoctor);
//	       courseList=this.eduCourseExtMapper.selectFindCoursesPersonal(paramMap);
//	    }
//    	return courseList;
//	}

	@Override
	public List<EduStudentCourse> searchStudentCourseList(EduStudentCourse studentCourse) {
       EduStudentCourseExample example=new EduStudentCourseExample();
       Criteria criteria=example.createCriteria();
       addCriteria(studentCourse, criteria);
       return studentCourseMapper.selectByExample(example);
	}

	private void addCriteria(EduStudentCourse studentCourse, Criteria criteria) {
	   if(StringUtil.isNotBlank(studentCourse.getCourseFlow())){
		   criteria.andCourseFlowEqualTo(studentCourse.getCourseFlow());
	   }
	   if(StringUtil.isNotBlank(studentCourse.getUserFlow())){
		   criteria.andUserFlowEqualTo(studentCourse.getUserFlow());
	   }
	   if(StringUtil.isNotBlank(studentCourse.getCourseTypeId())){
		   criteria.andCourseTypeIdEqualTo(studentCourse.getCourseTypeId());
	   }
	   if(StringUtil.isNotBlank(studentCourse.getRecordStatus())){
		   criteria.andRecordStatusEqualTo(studentCourse.getRecordStatus());
	   }
	   if(StringUtil.isNotBlank(studentCourse.getAuditFlag())){
		   criteria.andAuditFlagEqualTo(studentCourse.getAuditFlag());
	   }
	   if(StringUtil.isNotBlank(studentCourse.getReplenishFlag())){
		   criteria.andReplenishFlagEqualTo(studentCourse.getReplenishFlag());
	   }
	   if(StringUtil.isNotBlank(studentCourse.getStudentPeriod())){
		   criteria.andStudentPeriodEqualTo(studentCourse.getStudentPeriod());
	   }
	   if(StringUtil.isNotBlank(studentCourse.getCourseCode())){
		   criteria.andCourseCodeEqualTo(studentCourse.getCourseCode());
	   }
	   if(StringUtil.isNotBlank(studentCourse.getImpFlow())){
		   criteria.andImpFlowEqualTo(studentCourse.getImpFlow());
	   }
	}
	
	@Override
	public List<EduStudentCourse> searchStudentCourseList(List<String> courseFlowList) {
        EduStudentCourseExample example=new EduStudentCourseExample();
        Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(courseFlowList!=null && courseFlowList.size() >0){
        	 criteria.andCourseFlowIn(courseFlowList);
        }
		return this.studentCourseMapper.selectByExample(example);
	}

//	@Override
//	public Map<String, Map<String, Object>> courseFlowStudentCourseMap(
//			List<EduCourseExt> eduCourseList) {
//		if (eduCourseList!=null&&!eduCourseList.isEmpty()) {
//		List<String> courseFlowList=new ArrayList<String>();
//		Map<String,Map<String,Object>> studentCourseMap=new HashMap<String, Map<String,Object>>();
//		Map<String, Object> sysUserMap=null;
//		for (EduCourse courseFlow : eduCourseList) {
//			if (!courseFlowList.contains(courseFlow.getCourseFlow())) {
//				courseFlowList.add(courseFlow.getCourseFlow());
//				sysUserMap=new HashMap<String, Object>();
//				studentCourseMap.put(courseFlow.getCourseFlow(),sysUserMap);
//			}
//		}
//		List<EduStudentCourse> eduStudentCourseList=searchStudentCourseList(courseFlowList);
//			for (EduStudentCourse eduStudentCourse : eduStudentCourseList) {
//				SysUser sysUser = userBiz.readSysUser(eduStudentCourse.getUserFlow());
//				studentCourseMap.get(eduStudentCourse.getCourseFlow()).put(sysUser.getUserFlow(),eduStudentCourse);
//			}
//		return studentCourseMap;
//		}
//		return null;
//	}

//	@Override
//	public int saveCourseAnnualTrain(EduStudentCourse studentCourse,EduCourse course,
//			SysUser user) {
//		save(studentCourse);
//		HttpServletRequest req=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		req.setAttribute("studyType","业务学习");
//		req.setAttribute("trainContent",course.getCourseName());
//		req.setAttribute("trainDate",DateUtil.transDateTime(studentCourse.getChooseTime(), DateUtil.defDtPtn02, DateUtil.defDtPtn04));
//		req.setAttribute("academicScore",studentCourse.getCourseCredit());
//		req.setAttribute("classHourScore",studentCourse.getCoursePeriod());
//		req.setAttribute("remarks",user.getUserName()+"添加了课程！");
//		return  this.resRecBiz.saveRegistryForm(ResRecTypeEnum.AnnualTrainForm.getId(),"",studentCourse.getSchDeptFlow(),"",req,user.getOrgFlow());
//	}

//	@Override
//	public List<StudentCourseNumForm> studentCourseNumList(
//			Map<String, Object> map) {
//
//		return studentCourseExtMapper.selectStudentCourse(map);
//	}


//	@Override
//	public Map<String, Map<String, Object>> studentCourseListMap(
//			List<String> courseFlowList) {
//
//		Map<String, Map<String, Object>> studentCourseMaps=new HashMap<String, Map<String,Object>>();//返回组装Map
//		Map<String, Object> studentCourseMap=new HashMap<String, Object>();
//		Map<String,Object> StudyMap=new HashMap<String, Object>();//参加学习人数
//		Map<String,Object> CompleteMap=new HashMap<String, Object>();//完成学习人数
//		Map<String,Object> notCompleteMap=new HashMap<String, Object>();//未通过学习人数
//		Map<String,Object> requiredMap=new HashMap<String, Object>();//必修
//		Map<String,Object> completeRequiredMap=new HashMap<String, Object>();//完成必修
//		Map<String,Object> notCompleteRequiredMap=new HashMap<String, Object>();//未完成必修
//		List<String> learningState=new ArrayList<String>();//学习状态
//		List<String> courseTypeList=new ArrayList<String>();//课程类型
//		studentCourseMap.put("courseFlow", courseFlowList);
//		studentCourseNumList(studentCourseMap);
//		//参加学习人数
//		List<StudentCourseNumForm> StudyList=studentCourseNumList(studentCourseMap);
//		for (StudentCourseNumForm studentCourseNumForm : StudyList) {
//			StudyMap.put(studentCourseNumForm.getCourseFlow(), studentCourseNumForm.getNum());
//		}
//		//必修
//		courseTypeList.add(EduCourseTypeEnum.Required.getId());
//		studentCourseMap.put("required", courseTypeList);
//		List<StudentCourseNumForm> requiredList=studentCourseNumList(studentCourseMap);
//		for (StudentCourseNumForm studentCourseNumForm : requiredList) {
//			requiredMap.put(studentCourseNumForm.getCourseFlow(), studentCourseNumForm.getNum());
//		}
//
//		//完成必修
//		learningState.add(EduStudyStatusEnum.Finish.getId());
//		studentCourseMap.put("state", learningState);
//		List<StudentCourseNumForm> completeRequiredList=studentCourseNumList(studentCourseMap);
//
//		for (StudentCourseNumForm studentCourseNumForm : completeRequiredList) {
//			completeRequiredMap.put(studentCourseNumForm.getCourseFlow(), studentCourseNumForm.getNum());
//		}
//		//未完成必修
//		learningState.clear();
//		learningState.add(EduStudyStatusEnum.Underway.getId());
//		learningState.add(EduStudyStatusEnum.NotStarted.getId());
//		studentCourseMap.put("state",learningState);
//		List<StudentCourseNumForm> notCompleteRequiredList=studentCourseNumList(studentCourseMap);
//
//		for (StudentCourseNumForm studentCourseNumForm : notCompleteRequiredList) {
//			notCompleteRequiredMap.put(studentCourseNumForm.getCourseFlow(), studentCourseNumForm.getNum());
//		}
//		//完成学习人数
//		learningState.clear();
//		courseTypeList.clear();
//		learningState.add(EduStudyStatusEnum.Finish.getId());
//		studentCourseMap.put("state", learningState);
//		List<StudentCourseNumForm> CompleteList=studentCourseNumList(studentCourseMap);
//
//		for (StudentCourseNumForm studentCourseNumForm : CompleteList) {
//			CompleteMap.put(studentCourseNumForm.getCourseFlow(), studentCourseNumForm.getNum());
//		}
//		//未通过人数
//		learningState.clear();
//		courseTypeList.clear();
//		learningState.add(EduStudyStatusEnum.Underway.getId());
//		learningState.add(EduStudyStatusEnum.NotStarted.getId());
//		studentCourseMap.put("state", learningState);
//		List<StudentCourseNumForm> notCompleteList =studentCourseNumList(studentCourseMap);
//		for (StudentCourseNumForm studentCourseNumForm : notCompleteList) {
//			notCompleteMap.put(studentCourseNumForm.getCourseFlow(), studentCourseNumForm.getNum());
//		}
//		studentCourseMaps.put("StudyMap", StudyMap);
//		studentCourseMaps.put("CompleteMap", CompleteMap);
//		studentCourseMaps.put("notCompleteMap", notCompleteMap);
//		studentCourseMaps.put("requiredMap", requiredMap);
//		studentCourseMaps.put("completeRequiredMap", completeRequiredMap);
//		studentCourseMaps.put("notCompleteRequiredMap", notCompleteRequiredMap);
//		return studentCourseMaps;
//	}

	@Override
	public int saveStudentCourseByCourseFlowList(String studentPeriod, String userFlow, List<EduStudentCourse> studentCourseList, String replenishFlag) {
		int recordCount = GlobalConstant.ZERO_LINE; //存储记录数
		if(GlobalConstant.FLAG_Y.equals(replenishFlag)){//补选  
			if(studentCourseList != null && !studentCourseList.isEmpty()){
				//查询状态为N的(含非补选)  修改为补选
				EduStudentCourse statusN = new EduStudentCourse();
				statusN.setStudentPeriod(studentPeriod);
				statusN.setUserFlow(userFlow);
				statusN.setRecordStatus(GlobalConstant.FLAG_N);
				List<EduStudentCourse> oldStatusNList = searchStudentCourseList(statusN);
				for(EduStudentCourse esc: studentCourseList){
					esc.setStudentPeriod(studentPeriod);
					esc.setUserFlow(userFlow);
					esc.setReplenishFlag(GlobalConstant.FLAG_Y);
					esc.setChooseTime(DateUtil.getCurrDateTime());
					if(oldStatusNList != null && !oldStatusNList.isEmpty()){
						boolean addFlag = true;
						for(EduStudentCourse oldN : oldStatusNList){
							if(oldN.getCourseFlow().equals(esc.getCourseFlow())){
								addFlag = false;
								oldN.setRecordStatus(GlobalConstant.FLAG_Y);
								oldN.setReplenishFlag(GlobalConstant.FLAG_Y);
								oldN.setChooseTime(DateUtil.getCurrDateTime());
								save(oldN);
								break;
							}
						}
						if(addFlag){//新增
							save(esc);
						}
					}else{//全部新增
						save(esc);
					}
					recordCount ++;
				}
			}
			return recordCount;
		}else{//选课
			EduStudentCourse exitStudentCourse = new EduStudentCourse();
			exitStudentCourse.setStudentPeriod(studentPeriod);
			exitStudentCourse.setUserFlow(userFlow);
			List<EduStudentCourse> exitStudentCourseList = searchStudentCourseList(exitStudentCourse);
			Map<String, EduStudentCourse> deleMap = new HashMap<String, EduStudentCourse>();//记录需要删除的
			List<EduStudentCourse> oldStatusYList = new ArrayList<EduStudentCourse>();//状态为Y
			List<EduStudentCourse> oldStatusNList = new ArrayList<EduStudentCourse>();//状态为N
			if(exitStudentCourseList != null && !exitStudentCourseList.isEmpty()){
				for(EduStudentCourse sc: exitStudentCourseList){
					if(GlobalConstant.RECORD_STATUS_Y.equals(sc.getRecordStatus())){
						deleMap.put(sc.getRecordFlow(), sc );
						oldStatusYList.add(sc);
					}else{
						oldStatusNList.add(sc);
					}
				}
			}
			if(studentCourseList != null && !studentCourseList.isEmpty()){
				for(EduStudentCourse esc: studentCourseList){
					String courseFlow = esc.getCourseFlow();
					boolean addFlag = true;
					if(oldStatusYList!= null && !oldStatusYList.isEmpty()){
						for(EduStudentCourse oldY: oldStatusYList){
							if(courseFlow.equals(oldY.getCourseFlow()) ){
								addFlag = false;
								if(deleMap.size()>0){
									deleMap.remove(oldY.getRecordFlow());//不删除
								}
								break;
							}
						}
					}
					if(addFlag){
						esc.setStudentPeriod(studentPeriod);
						esc.setUserFlow(userFlow);
						esc.setChooseTime(DateUtil.getCurrDateTime());
						if(oldStatusNList != null && !oldStatusNList.isEmpty()){
							for(EduStudentCourse oldN :oldStatusNList){
								if(courseFlow.equals(oldN.getCourseFlow())){
									addFlag = false;
									oldN.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
									oldN.setChooseTime(DateUtil.getCurrDateTime());
									save(oldN);
									break;
								}
							}
							if(addFlag){//新增
								save(esc);
							}
						}else{//新增
							save(esc);
						}
					}
				}
			}
			//删除
			if(deleMap.size()>0){
				for(Entry<String, EduStudentCourse> entry : deleMap.entrySet()){
					EduStudentCourse delEduStudentCourse = entry.getValue();
					delEduStudentCourse.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
					delEduStudentCourse.setAuditFlag(GlobalConstant.RECORD_STATUS_N);
					save(delEduStudentCourse);
				}
			}
			//修改EduUser为已选课状态
			EduUser eduUser = new EduUser();
			eduUser.setUserFlow(userFlow);
			eduUser.setChooseCourseStatusId(ChooseCourseStatusEnum.Choose.getId());
			eduUser.setChooseCourseStatusName(ChooseCourseStatusEnum.Choose.getName());
			return eduUserBiz.saveEduUser(eduUser);
		}
	}

	@Override
	public List<StudentCourseNumForm> searchStudentCourseChooseCount(Map<String, Object> paramMap) {
		return studentCourseExtMapper.searchStudentCourseChooseCount(paramMap);
	}

	@Override
	public int saveCourseMaintain(EduStudentCourse studentCourse) {
		String studentPeriod = studentCourse.getStudentPeriod();
		String userFlow = studentCourse.getUserFlow();
		String courseFlow = studentCourse.getCourseFlow();
		if(StringUtil.isNotBlank(studentPeriod) && StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(courseFlow)){
			EduStudentCourse search = new EduStudentCourse();
			search.setStudentPeriod(studentPeriod);
			search.setUserFlow(userFlow);
			search.setCourseFlow(courseFlow);
			List<EduStudentCourse> studentCourseList = searchStudentCourseList(search);
			if(studentCourseList != null && !studentCourseList.isEmpty()){//修改
				search = studentCourseList.get(0);
				String recordStatus =  studentCourse.getRecordStatus();
				search.setRecordStatus(recordStatus);
				if(GlobalConstant.RECORD_STATUS_Y.equals(recordStatus)){//修改选课时间
					search.setChooseTime(DateUtil.getCurrDateTime());
				}
				//search.setReplenishFlag(GlobalConstant.FLAG_N);//取消补选？
				return save(search);
			}else{//新增
				studentCourse.setChooseTime(DateUtil.getCurrDateTime());
				return save(studentCourse);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}


	@Override
	public Map<String, Object> extractStudentCourseMapByCourseType(String studentPeriod, String userFlow) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		EduStudentCourse searchStudentCourse = new EduStudentCourse();
		searchStudentCourse.setStudentPeriod(studentPeriod);
		searchStudentCourse.setUserFlow(userFlow);
		searchStudentCourse.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<EduStudentCourse> studentCourseList = searchStudentCourseList(searchStudentCourse);
		if(studentCourseList != null && !studentCourseList.isEmpty()){
			Map<String, Map<String, EduStudentCourse>> chooseCourseMap = new HashMap<String, Map<String, EduStudentCourse>>();
			for(EduStudentCourse sc :studentCourseList){
				Map<String,EduStudentCourse> courseMap = chooseCourseMap.get(sc.getCourseTypeId());
				if(courseMap==null){
					courseMap = new HashMap<String, EduStudentCourse>();
				}
				courseMap.put(sc.getCourseFlow(), sc);
				chooseCourseMap.put(sc.getCourseTypeId(), courseMap);
			}
			resultMap.put("chooseCourseMap", chooseCourseMap);
		}
		return resultMap;
	}

//	@Override
//	public List<EduStudentCourseExt> searchStudentCourseExtList(String studentPeriod, String userFlow) {
//		EduStudentCourseExt studentCourseExt = new EduStudentCourseExt();
//		studentCourseExt.setStudentPeriod(studentPeriod);
//		studentCourseExt.setUserFlow(userFlow);
//		return studentCourseExtMapper.searchStudentCourseExtList(studentCourseExt);
//	}

	@Override
	public List<EduStudentCourse> searchStudentCourseListByUserFlowList(List<String> userFlowList) {
		EduStudentCourseExample example=new EduStudentCourseExample();
        Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(userFlowList != null && !userFlowList.isEmpty()){
			criteria.andUserFlowIn(userFlowList);
		}
		return studentCourseMapper.selectByExample(example);
	}

//	@Override
//	public List<StudentCourseNumForm> searchCourseCountByCourseFlowList(String studentPeriod, List<String> courseFlowList) {
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("studentPeriod", studentPeriod);
//		paramMap.put("courseFlowList", courseFlowList);
//		return studentCourseExtMapper.searchCourseCountByCourseFlowList(paramMap);
//	}

//	@Override
//	public int searchEduStudentCourseCount(EduStudentCourse studentCourse) {
//		EduStudentCourseExample example = new EduStudentCourseExample();
//		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		if(StringUtil.isNotBlank(studentCourse.getStudentPeriod())){
//			criteria.andStudentPeriodEqualTo(studentCourse.getStudentPeriod());
//		}
//		if(StringUtil.isNotBlank(studentCourse.getCourseFlow())){
//			criteria.andCourseFlowEqualTo(studentCourse.getCourseFlow());
//		}
//		if(StringUtil.isNotBlank(studentCourse.getUserFlow())){
//			criteria.andUserFlowEqualTo(studentCourse.getUserFlow());
//		}
//		return studentCourseMapper.countByExample(example);
//	}

	@Override
	public List<EduStudentCourseExt> searchStudentCourseExtWithUserList(String planYear, String courseFlow) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("studentPeriod", planYear);
		paramMap.put("courseFlow", courseFlow);
		return studentCourseExtMapper.searchStudentCourseExtWithUserList(paramMap);
	}

	@Override
	public String createQrCodeForGrade(String userFlow) throws Exception {
		    String filePath=StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+"grade";  
		   // String filePath=System.getProperty("user.home")+File.separator+"grade";
	        String fileName = userFlow+".png";
	        File file=new File(filePath+File.separator+fileName);
	        file.mkdirs();
	        String content = InitConfig.getSysCfg("qr_grade_search_url")+"/xjgl/student/resultSun?userFlow="+userFlow;// 内容  
	        int width = 100; // 图像宽度  
	        int height = 100; // 图像高度  
	        String format = "png";// 图像类型
	        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
	        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
	                BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵  
	        Path path = FileSystems.getDefault().getPath(filePath, fileName);  
	        MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像 
	        return fileName;
	}

	@Override
	public String decodeQrCodeForGrade(String userFlow) throws Exception {
		 String filePath = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+"grade"+File.separator;
		//String filePath=System.getProperty("user.home")+File.separator+"grade";
		 String fileName = userFlow+".png";
		 filePath += fileName;
	     BufferedImage image;
	     File imgFile = new File(filePath);
	     if(imgFile.isFile()){
	    	 image = ImageIO.read(imgFile);  
	         LuminanceSource source = new BufferedImageLuminanceSource(image);  
	         Binarizer binarizer = new HybridBinarizer(source);  
	         BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
	         Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
	         hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");  
	         Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码  
	         return result.getText().trim();
	     }
		 return null;
	}

	@Override
	public int deleteCourse(String recordFlow) {
		int result=0;
		if(StringUtil.isNotBlank(recordFlow)){
			 result=studentCourseMapper.deleteByPrimaryKey(recordFlow);
		}
		return result;
	}
	@Override
	public List<EduStudentCourseExt> searchStudentCourse(SysUser sysUser,
			EduUser eduUser,ResDoctor doctor,EduStudentCourse studentCourse) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", sysUser.getUserName());
		paramMap.put("sid", eduUser.getSid());
		paramMap.put("trainOrgId", eduUser.getTrainOrgId());
		paramMap.put("idNo", sysUser.getIdNo());
		paramMap.put("impFlow", studentCourse.getImpFlow());
		paramMap.put("studentPeriod", studentCourse.getStudentPeriod());
		paramMap.put("gradeTermId", studentCourse.getGradeTermId());
		paramMap.put("courseName", studentCourse.getCourseName());
		paramMap.put("resDoctor",doctor);
		
		return studentCourseExtMapper.searchStudentCourse(paramMap);
	}
	@Override
	public int reOpenChooseCourse(String userFlow) {
		EduUser user = eduUserBiz.readEduUser(userFlow);
		user.setChooseCourseStatusId(ChooseCourseStatusEnum.UnChoose.getId());
		user.setChooseCourseStatusName(ChooseCourseStatusEnum.UnChoose.getName());
		return eduUserBiz.saveEduUser(user);
	}

	@Override
	public int editEduStudentCourse(EduStudentCourse studentCourse){
		if(studentCourse!=null){
			if(StringUtil.isNotBlank(studentCourse.getRecordFlow())){
				GeneralMethod.setRecordInfo(studentCourse,false);
				studentCourseMapper.updateByPrimaryKeySelective(studentCourse);
			}else{
				EduStudentCourseExample example = new EduStudentCourseExample();
				example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andStudentPeriodEqualTo(studentCourse.getStudentPeriod())
						.andUserFlowEqualTo(studentCourse.getUserFlow()).andCourseFlowEqualTo(studentCourse.getCourseFlow());
				if(studentCourseMapper.countByExample(example)>0){//某年某学期某学生某课程已存在
					return -1;
				}else{
					studentCourse.setRecordFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(studentCourse,true);
					studentCourseMapper.insertSelective(studentCourse);
				}
			}
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}
}
