package com.pinde.sci.ctrl.gyxjgl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.edu.IEduCourseBiz;
import com.pinde.sci.biz.edu.IEduStudentCourseBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.gyxjgl.IGyXjEduUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.EduLogMapper;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.gyxjgl.XjEduUserExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author PPBear
 *
 */
@Controller
@RequestMapping("/gyxjgl/student")
public class GyXjglStudentGradeController extends GeneralController{
	private static Logger logger = LoggerFactory.getLogger(GyXjglUserCenterController.class);
	
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IGyXjEduUserBiz eduUserBiz;
	@Autowired
	private IEduStudentCourseBiz eduStudentCourseBiz;
	@Autowired
	private IEduCourseBiz eduCourseBiz;
	@Autowired
	private EduLogMapper logMapper;

	@RequestMapping(value="/resultSun")
	public String resultSun(Model model, String userFlow,String majorId,String period,String courseFlow){
		XjEduUserExt XjglUserExt = null;
		EduUser eduuser=eduUserBiz.readEduUser(userFlow);
		SysUser sysuser=userBiz.readSysUser(userFlow);
		ResDoctor doctor = eduUserBiz.findDoctorByFlow(userFlow);
		List<String> userFlowList = new ArrayList<String>();
		userFlowList.add(userFlow);
		List<XjEduUserExt> XjglUserExts = eduUserBiz.searchEduUserCourseExtMajorList(userFlowList, null);
		if (XjglUserExts != null && !XjglUserExts.isEmpty()) {
			XjglUserExt = XjglUserExts.get(0);
		}
//		List<EduCourseMajorExt> currCourseMajorlist=eduUserBiz.searchEduUserCourseSunList(majorId,period);
		model.addAttribute("studentCourseMajor", XjglUserExt);
		model.addAttribute("doctor",doctor);
		model.addAttribute("eduuser",eduuser);
		model.addAttribute("sysuser",sysuser);
		return "gyxjgl/plat/checkGrade";
		
	}

	@RequestMapping(value="/addGrade",method={RequestMethod.GET})
	public String addGrade(String sid, Model model) throws Exception{
		if(StringUtil.isNotBlank(sid)){
			Map<String, Object> map = eduUserBiz.queryStuInfo(sid);
				model.addAttribute("user",map);
		}
		return "gyxjgl/plat/courseScoreAdd";
	}

	@RequestMapping(value="/saveOneGrade")
	@ResponseBody
	public int saveOneGrade(EduStudentCourse eduStudentCourse,String sid){
		EduLog eduLog = new EduLog();//修改日志对象
		SysUser user=new SysUser();
		String gradeTermName = DictTypeEnum.GyRecruitSeason.getDictNameById(eduStudentCourse.getGradeTermId());
		eduStudentCourse.setGradeTermName(gradeTermName);
		EduUser eduuser = eduUserBiz.findBySid(sid);
		if(null != eduuser){
			String userFlow = eduuser.getUserFlow();
			user=userBiz.readSysUser(eduuser.getUserFlow());
			eduStudentCourse.setUserFlow(userFlow);
			String studyWayName = DictTypeEnum.GyXjStudyWay.getDictNameById(eduStudentCourse.getStudyWayId());
			eduStudentCourse.setStudyWayName(studyWayName);
			String assTypeName = DictTypeEnum.GyXjEvaluationMode.getDictNameById(eduStudentCourse.getAssessTypeId());
			eduStudentCourse.setAssessTypeName(assTypeName);
			String courseFlow = eduStudentCourse.getCourseFlow();
			EduCourse course = eduCourseBiz.readCourse(courseFlow);
			String courseCode = course.getCourseCode();
			if(StringUtil.isBlank(courseCode)){//课程代码必须维护，否则影响导入的数据
				return -3;
			}
			String courseName = course.getCourseName();
			String courseCredit = course.getCourseCredit();
			String couresPeriod = course.getCoursePeriod();
			eduStudentCourse.setCourseCode(courseCode);
			eduStudentCourse.setCourseName(courseName);
			eduStudentCourse.setCourseCredit(courseCredit);
			eduStudentCourse.setCoursePeriod(couresPeriod);
			if(StringUtil.isNotBlank(eduStudentCourse.getCourseGrade())){
				List<SysDict> dictList = DictTypeEnum.sysListDictMap.get("GyXjIsPassed");
				boolean flag = true;
				for (SysDict dict : dictList) {
					if (dict.getDictId().equals(eduStudentCourse.getCourseGrade())) {
						eduStudentCourse.setCourseGrade(dict.getDictId());
						flag = false;
						break;
					}
				}
				if(flag){
					DecimalFormat df=new DecimalFormat("0.0");
					eduStudentCourse.setCourseGrade(df.format(Double.parseDouble(eduStudentCourse.getCourseGrade())));
				}
			}
			eduLog.setUserFlow(user.getUserFlow());//用户流水号
			eduLog.setChangeTime(DateUtil.getCurrentTime());//修改时间
			eduLog.setChangeUserFlow(GlobalContext.getCurrentUser().getUserFlow());//修改账户流水号
			eduLog.setChangeUserCode(GlobalContext.getCurrentUser().getUserCode());//修改账户登录名
			eduLog.setWsId(GlobalContext.getCurrentWsId());//当前工作站
			eduLog.setWsName(InitConfig.getWorkStationName(GlobalContext.getCurrentWsId()));
			eduLog.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			eduLog.setCreateTime(DateUtil.getCurrentTime());
			eduLog.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			eduLog.setChangeItem("成绩管理");
			eduLog.setLogDesc(eduStudentCourse.getCourseName());
			eduLog.setUserName(user.getUserName());
			eduLog.setStudyId(eduuser.getSid());
			eduLog.setGradeNumber(eduuser.getPeriod());
			eduLog.setLogFlow(PkUtil.getUUID());
			eduLog.setNewData(eduStudentCourse.getCourseGrade());
			logMapper.insertSelective(eduLog);
			return eduStudentCourseBiz.editEduStudentCourse(eduStudentCourse);
		}
		return -2;
	}
}