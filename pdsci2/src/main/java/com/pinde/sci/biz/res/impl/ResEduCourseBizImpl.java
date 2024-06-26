package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResEduCourseBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.EduCourseMapper;
import com.pinde.sci.dao.base.EduCourseScheduleMapper;
import com.pinde.sci.dao.base.EduStudentCourseMapper;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.dao.res.ResEduCourseExtMapper;
import com.pinde.sci.enums.res.ResEduCourseTypeEnum;
import com.pinde.sci.enums.res.ResEduStudyStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.res.EduCourseSearchConditionForm;
import com.pinde.sci.form.res.EduFileForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.EduStudentCourseExample.Criteria;
import com.pinde.sci.model.res.EduCourseExt;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResEduCourseBizImpl implements IResEduCourseBiz {
	@Autowired
	private ResEduCourseExtMapper eduCourseExtMapper;
	@Autowired
	private EduCourseMapper eduCourseMapper;
	@Autowired
	private EduStudentCourseMapper eduStudentCourseMapper;
	@Autowired
	private EduCourseScheduleMapper eduCourseScheduleMapper;
	@Autowired
	private SysLogMapper logMapper;

	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
            return String.valueOf(d);
    }

	@Override
	public List<EduCourse> searchStuCourseList(
			EduCourse eduCourse, SysUser sysUser, String studyStatus) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (eduCourse != null) {
			paramMap.put("eduCourse", eduCourse);
		}
		if (sysUser != null) {
			paramMap.put("sysUser", sysUser);
		}
		if (studyStatus != null) {
			paramMap.put("studyStatus", studyStatus);
		}
		List<EduCourse> stuCourseList = eduCourseExtMapper.searchStuCourseList(paramMap);
		return stuCourseList;
	}

	@Override
	public EduCourseExt searchOneWithChapters(String courseFlow) {
		EduCourseExt courseExt = null;
		if (StringUtil.isNotBlank(courseFlow)) {
			courseExt = this.eduCourseExtMapper.selectOneWithChapters(courseFlow);
		}
		return courseExt;
	}

	@Override
	public EduFileForm saveFile(MultipartFile file, String type) throws Exception {
		if (file.getSize() > 0) {
			String dateString = DateUtil.getCurrDate2();
			String newDir = "";
			if ("reseduCourseVideo".equals(type)) {
				newDir += StringUtil.defaultString(InitConfig.getSysCfg("upload_stream_dir")) + File.separator + type + File.separator + dateString;
			} else {
				newDir += StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + type + File.separator + dateString;
			}
			File fileDir = new File(newDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String filename = PkUtil.getUUID() + fileSuffix;
			File newFile = new File(fileDir, filename);
			file.transferTo(newFile);
			String fileUrl = File.separator + type + File.separator + dateString + File.separator + filename;
			//判断文件是否上传成功
			String url = newDir + File.separator + filename;
			File checkFile = new File(url);
			if (checkFile.isFile()) {
				EduFileForm fileForm = new EduFileForm();
				fileForm.setFileUrl(fileUrl.replace("\\", "/"));
				if ("reseduCourseVideo".equals(type)) {
					Encoder encoder = new Encoder();
					MultimediaInfo m = encoder.getInfo(checkFile);
					BigDecimal ls = BigDecimal.valueOf(m.getDuration()).divide(new BigDecimal(1000));
					BigDecimal minTime = ls.divide(new BigDecimal(60), 0, BigDecimal.ROUND_DOWN);
					BigDecimal secTime = ls.remainder(new BigDecimal(60)).setScale(0, BigDecimal.ROUND_HALF_DOWN);
					String min = String.valueOf(minTime);
					if (min.length() == 1) {
						min = "0" + min;
					}
					String sec = String.valueOf(secTime);
					if (sec.length() == 1) {
						sec = "0" + sec;
					}
					fileForm.setMin(min);
					fileForm.setSec(sec);
				}
				return fileForm;
			}
		}
		return null;
	}

	@Override
	public int editCourse(EduCourse course, MultipartFile file) throws Exception {
		if (file.getSize() > 0) {
			//创建目录
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "eduImages" + File.separator + dateString;
			File fileDir = new File(newDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			//文件名
			String originalFilename = file.getOriginalFilename();
			originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile = new File(fileDir, originalFilename);
			file.transferTo(newFile);

			//删除原图片
			String oldCourseImg = course.getCourseImg();
			if (StringUtil.isNotBlank(oldCourseImg)) {
				try {
					oldCourseImg = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldCourseImg;
					File imgFile = new File(oldCourseImg);
					if (imgFile.exists()) {
						imgFile.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("删除图片失败！");
				}
			}

			String courseImg = "eduImages/" + dateString + "/" + originalFilename;
			course.setCourseImg(courseImg);//图片路径
		}
		String courseTypeId = course.getCourseTypeId();
		if (StringUtil.isNotBlank(courseTypeId)) {
			course.setCourseTypeName(ResEduCourseTypeEnum.getNameById(courseTypeId));
		}
		String courseMajorId = course.getCourseMajorId();
		if (StringUtil.isNotBlank(courseMajorId)) {
			course.setCourseMajorName(DictTypeEnum.CourseMajor.getDictNameById(courseMajorId));
		}
		return saveCourse(course);
	}

	@Override
	public int saveCourse(EduCourse course) {
		if (StringUtil.isNotBlank(course.getCourseFlow())) {
			GeneralMethod.setRecordInfo(course, false);
			return eduCourseMapper.updateByPrimaryKeySelective(course);
		} else {
			course.setCourseFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(course, true);
			return eduCourseMapper.insert(course);
		}
	}

	@Override
	public List<EduCourse> searchTchCourseList(EduCourse eduCourse, SysUser sysUser) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (sysUser != null) {
			paramMap.put("user", sysUser);
		}
		if (eduCourse != null) {
			paramMap.put("course", eduCourse);
		}
		List<EduCourse> stuCourseList = eduCourseExtMapper.searchTchCourseList(paramMap);
		return stuCourseList;
	}

	@Override
	public List<SysUser> searchUserByTch(SysUser sysUser) {
		List<SysUser> stuList = eduCourseExtMapper.searchUserByTch(sysUser);
		return stuList;
	}

	@Override
	public List<EduCourse> searchCourseList(EduCourse course, EduCourseSearchConditionForm form) {
		EduCourseExample example = new EduCourseExample();
		EduCourseExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(course.getCourseName())) {
			criteria.andCourseNameLike("%" + course.getCourseName() + "%");
		}
		if (StringUtil.isNotBlank(course.getCourseCategoryId())) {
			criteria.andCourseCategoryIdEqualTo(course.getCourseCategoryId());
		}
		if (StringUtil.isNotBlank(course.getCreateUserFlow())) {
			criteria.andCreateUserFlowEqualTo(course.getCreateUserFlow());
		}
		if (StringUtil.isNotBlank(course.getCreateUserName())) {
			criteria.andCreateUserNameLike("%" + course.getCreateUserName() + "%");
		}
		if (StringUtil.isNotBlank(course.getDeptFlow())) {
			criteria.andDeptFlowEqualTo(course.getDeptFlow());
		}
		if (form != null) {
			if (StringUtil.isNotBlank(form.getUpLoadTimeStart())) {
				criteria.andUploadTimeGreaterThanOrEqualTo(form.getUpLoadTimeStart());
			}
			if (StringUtil.isNotBlank(form.getUpLoadTimeEnd())) {
				criteria.andUploadTimeLessThanOrEqualTo(form.getUpLoadTimeEnd());
			}
			if (form.getCourseStatusIdList() != null && !form.getCourseStatusIdList().isEmpty()) {
				criteria.andCourseStatusIdIn(form.getCourseStatusIdList());
			}
		}
		example.setOrderByClause("create_time desc,NLSSORT(COURSE_NAME,'NLS_SORT = SCHINESE_PINYIN_M')");
		return eduCourseMapper.selectByExample(example);
	}

	@Override
	public EduCourse readCourse(String courseFlow) {
		if (StringUtil.isNotBlank(courseFlow)) {
			return eduCourseMapper.selectByPrimaryKey(courseFlow);
		}
		return null;
	}

	@Override
	public int deleteCourseImg(String courseFlow) {
		if (StringUtil.isNotBlank(courseFlow)) {
			EduCourse course = readCourse(courseFlow);
			if (course != null) { //修改课程删除
				String img = course.getCourseImg();
				if (StringUtil.isNotBlank(img)) {
					try {
						img = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + img;
						File file = new File(img);
						if (file.exists()) {
							boolean delRestlt = file.delete();
							if (delRestlt) {//删除成功
								course.setCourseImg(null);
								return eduCourseMapper.updateByPrimaryKey(course);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("删除图片失败！");
					}
				}
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int delCourse(String courseFlow) {
		if (StringUtil.isNotBlank(courseFlow)) {
			EduCourse course = readCourse(courseFlow);
			if (course != null) { //修改课程删除
				try {
					String courseImg = course.getCourseImg();
					courseImg = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + courseImg;
					File imgFile = new File(courseImg);
					if (imgFile.exists()) {
						boolean delRestlt = imgFile.delete();
						if (delRestlt) {//删除图片成功

						}
					}
					course.setCourseImg(null);
					course.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
					return eduCourseMapper.updateByPrimaryKey(course);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("删除图片失败！");
				}
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<EduCourseSchedule> searchScheduleList(EduCourseSchedule schedule) {
		EduCourseScheduleExample example = new EduCourseScheduleExample();
		EduCourseScheduleExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (schedule != null) {
			if (StringUtil.isNotBlank(schedule.getUserFlow())) {
				criteria.andUserFlowEqualTo(schedule.getUserFlow());
			}
			if (StringUtil.isNotBlank(schedule.getChapterFlow())) {
				criteria.andChapterFlowEqualTo(schedule.getChapterFlow());
			}
			if (StringUtil.isNotBlank(schedule.getCourseFlow())) {
				criteria.andCourseFlowEqualTo(schedule.getCourseFlow());
			}
			if (StringUtil.isNotBlank(schedule.getStudyStatusId())) {
				criteria.andStudyStatusIdEqualTo(schedule.getStudyStatusId());
			}
			example.setOrderByClause("modify_time desc");
		}
		List<EduCourseSchedule> schList = this.eduCourseScheduleMapper.selectByExample(example);

		return schList;
	}

	@Override
	public int countUserAllCredit(String userFlow) {
		return eduCourseExtMapper.countUserAllCredit(userFlow);
	}

	@Override
	public Map<String, Object> countUserByStudyStatus(String courseFlow) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (ResEduStudyStatusEnum status : ResEduStudyStatusEnum.values()) {
			paramMap.put("courseFlow", courseFlow);
			paramMap.put("studyStatusId", status.getId());
			int count = this.eduCourseExtMapper.countUserByStudyStatus(paramMap);
			resultMap.put(status.getId(), count);
		}
		return resultMap;
	}

	@Override
	public List<EduStudentCourse> searchStudentCourse(EduStudentCourse eduStudentCourse) {
		EduStudentCourseExample example = new EduStudentCourseExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(eduStudentCourse.getUserFlow())) {
			criteria.andUserFlowEqualTo(eduStudentCourse.getUserFlow());
		}
		if (StringUtil.isNotBlank(eduStudentCourse.getCourseFlow())) {
			criteria.andCourseFlowEqualTo(eduStudentCourse.getCourseFlow());
		}
		return eduStudentCourseMapper.selectByExample(example);
	}

	@Override
	public int chooseCourse(String userFlow, String courseFlow) {
		if (StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(courseFlow)) {
			EduStudentCourse eduStudentCourse = new EduStudentCourse();
			eduStudentCourse.setRecordFlow(PkUtil.getUUID());
			eduStudentCourse.setUserFlow(userFlow);
			eduStudentCourse.setCourseFlow(courseFlow);
			eduStudentCourse.setStudyStatusId(ResEduStudyStatusEnum.Underway.getId());
			eduStudentCourse.setStudyStatusName(ResEduStudyStatusEnum.Underway.getName());
			eduStudentCourse.setChooseTime(DateUtil.getCurrDateTime());
			eduStudentCourse.setAchieveCredit(GlobalConstant.FLAG_N);
			GeneralMethod.setRecordInfo(eduStudentCourse, true);
			return this.eduStudentCourseMapper.insertSelective(eduStudentCourse);
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<EduCourse> searchCourseByCondition(String condition) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("condition", condition);
		return this.eduCourseExtMapper.searchCourseByCondition(paramMap);
	}

	@Override
	public String checkRole() {
		List<String> currRoleList = (List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_ROLE_LIST);
		String doctorRoleFlow = InitConfig.getSysCfg("res_doctor_role_flow");
		String tchRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
		String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
		String adminRoleFlow = InitConfig.getSysCfg("res_admin_role_flow");
		String chargeRoleFlow = InitConfig.getSysCfg("res_charge_role_flow");
		if (currRoleList != null && !currRoleList.isEmpty()) {
			if (currRoleList.contains(doctorRoleFlow)) {
				return GlobalConstant.RES_ROLE_SCOPE_DOCTOR;
			} else if (currRoleList.contains(tchRoleFlow)) {
				return GlobalConstant.RES_ROLE_SCOPE_TEACHER;
			} else if (currRoleList.contains(headRoleFlow)) {
				return GlobalConstant.RES_ROLE_SCOPE_HEAD;
			} else if (currRoleList.contains(adminRoleFlow)) {
				return GlobalConstant.RES_ROLE_SCOPE_ADMIN;
			} else if (currRoleList.contains(chargeRoleFlow)) {
				return GlobalConstant.RES_ROLE_SCOPE_GLOBAL;
			} else {
				return "";
			}
		}
		return null;
	}

	@Override
	public List<EduCourse> searchCourseList(EduCourse course) {
		EduCourseExample example = new EduCourseExample();
		EduCourseExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (course != null) {
			if (StringUtil.isNotBlank(course.getCourseName())) {
				criteria.andCourseNameLike("%" + course.getCourseName() + "%");
			}
			if (StringUtil.isNotBlank(course.getCourseTypeId())) {
				criteria.andCourseTypeIdEqualTo(course.getCourseTypeId());
			}
			if (StringUtil.isNotBlank(course.getGradationId())) {
				criteria.andGradationIdEqualTo(course.getGradationId());
			}
			if (StringUtil.isNotBlank(course.getCourseCategoryId())) {
				criteria.andCourseCategoryIdEqualTo(course.getCourseCategoryId());
			}
			if (StringUtil.isNotBlank(course.getCourseTypeId())) {
				criteria.andCourseTypeIdEqualTo(course.getCourseTypeId());
			}
			if (StringUtil.isNotBlank(course.getCourseCode())) {
				criteria.andCourseCodeEqualTo(course.getCourseCode());
			}
			if (StringUtil.isNotBlank(course.getAssumeOrgFlow())) {
				criteria.andAssumeOrgFlowEqualTo(course.getAssumeOrgFlow());
			}
		}
		example.setOrderByClause("COURSE_MAJOR_ID,NLSSORT(COURSE_NAME,'NLS_SORT = SCHINESE_PINYIN_M')");
		return eduCourseMapper.selectByExample(example);
	}

	private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
		// 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
		if (!inS.markSupported()) {
			// 还原流信息
			inS = new PushbackInputStream(inS);
		}
		// EXCEL2003使用的是微软的文件系统
		if (POIFSFileSystem.hasPOIFSHeader(inS)) {
			return new HSSFWorkbook(inS);
		}
		// EXCEL2007使用的是OOM文件格式
		if (POIXMLDocument.hasOOXMLHeader(inS)) {
			// 可以直接传流参数，但是推荐使用OPCPackage容器打开
			return new XSSFWorkbook(OPCPackage.open(inS));
		}
		throw new IOException("不能解析的excel版本");
	}

	private int parseExcel(Workbook wb) {
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
			int row_num = sheet.getLastRowNum();
			//获取表头
			Row titleR = sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			String title = "";
			for (int i = 0; i < cell_num; i++) {
				title = titleR.getCell(i).getStringCellValue();
				colnames.add(title);
			}
			for (int i = 1; i < row_num + 1; i++) {

				Row r = sheet.getRow(i);
				EduCourse course = new EduCourse();
				String courseName;
				String gradationName;
				String courseTypeName;
				String courseCode;
				String courseNameEn;
				String courseUserCount;
				String courseMoudleName;
				String assumeOrgName;
				String courseCredit;
				String coursePeriod;
				String coursePeriodTeach;
				String coursePeriodExper;
				String coursePeriodMachine;
				String coursePeriodOther;


				EduCourse eduCourse = new EduCourse();
				for (int j = 0; j < colnames.size(); j++) {
					String value = "";
					Cell cell = r.getCell(j);
					if (cell != null && StringUtil.isNotBlank(cell.toString().trim())) {
						if (cell.getCellType() == 1) {
							value = cell.getStringCellValue().trim();
						} else {
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					if ("代码".equals(colnames.get(j))) {
						courseCode = value;
						course.setCourseCode(courseCode);
					} else if ("中文名称".equals(colnames.get(j))) {
						courseName = value;
						course.setCourseName(courseName);
					} else if ("授课层次".equals(colnames.get(j))) {
						gradationName = value;
						course.setGradationName(gradationName);
					} else if ("课程类别".equals(colnames.get(j))) {
						courseTypeName = value;
						course.setCourseTypeName(courseTypeName);
					} else if ("英文名称".equals(colnames.get(j))) {
						courseNameEn = value;
						course.setCourseNameEn(courseNameEn);
					} else if ("容纳人数".equals(colnames.get(j))) {
						courseUserCount = value;
						course.setCourseUserCount(courseUserCount);
					} else if ("所属模块".equals(colnames.get(j))) {
						courseMoudleName = value;
						course.setCourseMoudleName(courseMoudleName);
					} else if ("承担单位".equals(colnames.get(j))) {
						assumeOrgName = value;
						course.setAssumeOrgName(assumeOrgName);
					} else if ("学分".equals(colnames.get(j))) {
						courseCredit = value;
						course.setCourseCredit(courseCredit);
					} else if ("总学时".equals(colnames.get(j))) {
						coursePeriod = value;
						course.setCoursePeriod(coursePeriod);
					} else if ("讲授学时".equals(colnames.get(j))) {
						coursePeriodTeach = value;
						course.setCoursePeriodTeach(coursePeriodTeach);
					} else if ("实验学时".equals(colnames.get(j))) {
						coursePeriodExper = value;
						course.setCoursePeriodExper(coursePeriodExper);
					} else if ("上机学时".equals(colnames.get(j))) {
						coursePeriodMachine = value;
						course.setCoursePeriodMachine(coursePeriodMachine);
					} else if ("其它学时".equals(colnames.get(j))) {
						coursePeriodOther = value;
						course.setCoursePeriodOther(coursePeriodOther);
					}
				}
				//执行保存
				eduCourse.setCourseCode(course.getCourseCode());
				List<EduCourse> ecourseList = searchCourseList(eduCourse);
				if (ecourseList != null && !ecourseList.isEmpty()) {
					course.setCourseFlow(ecourseList.get(0).getCourseFlow());
				}
				course.setGradationId(getDictId(course.getGradationName(), "TrainType"));
				course.setCourseTypeId(getDictId(course.getCourseTypeName(), "XjCourseType"));
				course.setCourseMoudleId(getDictId(course.getCourseMoudleName(), "XjCourseMoudle"));
				SysOrg assumeOrg = InitConfig.getSysOrgByName(course.getAssumeOrgName());
				if (assumeOrg != null) {
					course.setAssumeOrgFlow(assumeOrg.getOrgFlow());
				}
				saveCourse(course);
				count++;
			}
		}
		return count;
	}

	public String getDictId(String dictName, String dictTypeId) {
		Map<String, String> dictNameMap = InitConfig.getDictNameMap(dictTypeId);
		return dictNameMap.get(dictName);
	}

	@Override
	public List<EduCourse> readCourse(EduCourse course) {
		EduCourseExample example = new EduCourseExample();
		EduCourseExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(course.getCourseCode())) {
			criteria.andCourseCodeEqualTo(course.getCourseCode());
			return eduCourseMapper.selectByExample(example);
		} else {
			return null;
		}
	}
	
	
	
}
