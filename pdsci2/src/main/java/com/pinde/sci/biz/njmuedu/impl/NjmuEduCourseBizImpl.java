package com.pinde.sci.biz.njmuedu.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.njmuedu.INjmuEduCourseBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.EduCourseMapper;
import com.pinde.sci.dao.base.EduCourseScheduleMapper;
import com.pinde.sci.dao.base.EduStudentCourseMapper;
import com.pinde.sci.dao.njmuedu.NjmuEduCourseExtMapper;
import com.pinde.sci.enums.njmuedu.NjmuEduCourseTypeEnum;
import com.pinde.sci.enums.njmuedu.NjmuEduStudyStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.njmuedu.EduFileForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.EduStudentCourseExample.Criteria;
import com.pinde.sci.model.njmuedu.EduCourseExt;
import com.pinde.sci.model.njmuedu.EduUserExt;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class NjmuEduCourseBizImpl implements INjmuEduCourseBiz{

	@Autowired
	private NjmuEduCourseExtMapper eduCourseExtMapper;
	@Autowired
	private EduCourseMapper eduCourseMapper;
	@Autowired
	private EduStudentCourseMapper eduStudentCourseMapper;
	@Autowired
	private EduCourseScheduleMapper eduCourseScheduleMapper;
	
	@Override
	public List<EduCourse> searchStuCourseList(
			EduCourse eduCourse,SysUser sysUser,List<String> studyStatusList) {
		Map<String,Object> paramMap=new HashMap<String, Object>();
		   if(eduCourse!=null){
			   paramMap.put("eduCourse", eduCourse);
		   }
		   if(sysUser!=null){
			   paramMap.put("sysUser", sysUser);
		   }	
		   if(studyStatusList!=null && !studyStatusList.isEmpty()){
			   paramMap.put("studyStatusList", studyStatusList);
		   }
		List<EduCourse> stuCourseList=eduCourseExtMapper.searchStuCourseList(paramMap);
		return stuCourseList;
	}

	@Override
	public EduCourseExt searchOneWithChapters(String courseFlow) {
		EduCourseExt courseExt = null;
		if(StringUtil.isNotBlank(courseFlow)){
			courseExt = this.eduCourseExtMapper.selectOneWithChapters(courseFlow);
		}
		return courseExt;
	}
	
	@Override
	public EduFileForm saveFile(MultipartFile file, String type) throws Exception {
		if(file.getSize() > 0){
			String dateString = DateUtil.getCurrDate2();
			String newDir = "";
			if("njmueduCourseVideo".equals(type)){
				newDir+=StringUtil.defaultString(InitConfig.getSysCfg("upload_stream_dir"))+File.separator+type+File.separator+ dateString;
			}else{
				newDir+=StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+type+File.separator+ dateString;
			}
			File fileDir = new File(newDir);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String filename = PkUtil.getUUID() + fileSuffix;
			File newFile = new File(fileDir, filename); 
			file.transferTo(newFile);
			String fileUrl =File.separator + type + File.separator + dateString + File.separator + filename;
			//判断文件是否上传成功
			String url=newDir+File.separator+filename;
	    	File checkFile=new File(url);
	    	if(checkFile.isFile()){
	    		EduFileForm fileForm=new EduFileForm();
	    		fileForm.setFileUrl(fileUrl.replace("\\", "/"));
	    		if("njmueduCourseVideo".equals(type)){
	    			Encoder encoder = new Encoder();
		    		MultimediaInfo m = encoder.getInfo(checkFile);
		            BigDecimal ls = BigDecimal.valueOf(m.getDuration()).divide(new BigDecimal(1000));
		            BigDecimal minTime=ls.divide(new BigDecimal(60),0,BigDecimal.ROUND_DOWN);
		            BigDecimal secTime=ls.remainder(new BigDecimal(60)).setScale(0,BigDecimal.ROUND_HALF_DOWN);
					String min=String.valueOf(minTime);
					if(min.length()==1){
						min="0"+min;
					}
					String sec=String.valueOf(secTime);
					if(sec.length()==1){
						sec="0"+sec;
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
	public int editCourse(EduCourse course, MultipartFile file) throws Exception{
		if(file.getSize() > 0){
			//创建目录
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+"eduImages"+File.separator+ dateString ;
			File fileDir = new File(newDir);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			//文件名
			String originalFilename = file.getOriginalFilename();
			originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile = new File(fileDir, originalFilename); 
			file.transferTo(newFile);
			
			//删除原图片
			String oldCourseImg = course.getCourseImg();
			if(StringUtil.isNotBlank(oldCourseImg)){
				try {
					oldCourseImg = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldCourseImg;
					File imgFile = new File(oldCourseImg);
					if(imgFile.exists()){
						imgFile.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("删除图片失败！");
				}
			}
			
			String courseImg = "eduImages/"+dateString+"/"+originalFilename;
			course.setCourseImg(courseImg);//图片路径
		}
		String courseTypeId = course.getCourseTypeId();
		if(StringUtil.isNotBlank(courseTypeId)){
			course.setCourseTypeName(NjmuEduCourseTypeEnum.getNameById(courseTypeId));
		}
		String courseMajorId = course.getCourseMajorId();
		if(StringUtil.isNotBlank(courseMajorId)){
			course.setCourseMajorName(DictTypeEnum.NjmuCourseMajor.getDictNameById(courseMajorId));
		}
		return saveCourse(course);
	}
	
	

	@Override
	public int saveCourse(EduCourse course) {
		if(StringUtil.isNotBlank(course.getCourseFlow())){
			GeneralMethod.setRecordInfo(course, false);
			return eduCourseMapper.updateByPrimaryKeySelective(course);
		}else{
			course.setCourseFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(course, true);
			return eduCourseMapper.insert(course);
		}
	}
	
	@Override
	public List<EduCourse> searchTchCourseList(EduCourse eduCourse,SysUser sysUser) {
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("user",sysUser);
		paramMap.put("course", eduCourse);
		List<EduCourse> stuCourseList=eduCourseExtMapper.searchTchCourseList(paramMap);
		return stuCourseList;
	}

	@Override
	public List<SysUser> searchUserByTch(SysUser sysUser) {
		List<SysUser> stuList=eduCourseExtMapper.searchUserByTch(sysUser);
		return stuList;
	}

	@Override
	public List<EduCourse> searchCourseList(EduCourse course) {
		EduCourseExample example = new EduCourseExample();
		com.pinde.sci.model.mo.EduCourseExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(course != null){
			if(StringUtil.isNotBlank(course.getCourseName())){
				criteria.andCourseNameLike("%"+ course.getCourseName()+"%");
			}
			if(StringUtil.isNotBlank(course.getCourseTypeId())){
				criteria.andCourseTypeIdEqualTo(course.getCourseTypeId());
			}
			if(StringUtil.isNotBlank(course.getCourseSpeaker())){
				criteria.andCourseSpeakerEqualTo(course.getCourseSpeaker());
			}
		}
		example.setOrderByClause("COURSE_MAJOR_ID,NLSSORT(COURSE_NAME,'NLS_SORT = SCHINESE_PINYIN_M')");
		return eduCourseMapper.selectByExample(example);
	}

	@Override
	public EduCourse readCourse(String courseFlow) {
		if(StringUtil.isNotBlank(courseFlow)){
			return eduCourseMapper.selectByPrimaryKey(courseFlow);
		}
		return null;
	}


	@Override
	public int deleteCourseImg(String courseFlow) {
		if(StringUtil.isNotBlank(courseFlow)){
			EduCourse course = readCourse(courseFlow);
			if(course != null){ //修改课程删除
				String img = course.getCourseImg();
				if(StringUtil.isNotBlank(img)){
					try {
						img = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))  + File.separator + img;
						File file = new File(img);
						if(file.exists()){
							boolean delRestlt = file.delete();
							if(delRestlt){//删除成功
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
		if(StringUtil.isNotBlank(courseFlow)){
			EduCourse course = readCourse(courseFlow);
			if(course != null){ //修改课程删除
				try {
					String courseImg = course.getCourseImg();
					courseImg = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator  + courseImg;
					File imgFile = new File(courseImg);
					if(imgFile.exists()){
						boolean delRestlt = imgFile.delete();
						if(delRestlt){//删除图片成功
							
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
	public int countUserSelectOneCourse(EduCourse eduCourse) {
		EduStudentCourseExample example=new EduStudentCourseExample();
		Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(eduCourse.getCourseFlow())){
			criteria.andCourseFlowEqualTo(eduCourse.getCourseFlow());
		}
		return eduStudentCourseMapper.countByExample(example);
	}
	@Override
	public List<EduCourse> searchAllCourseList(EduCourse eduCourse,String sort) {
		EduCourseExample example=new EduCourseExample();
		com.pinde.sci.model.mo.EduCourseExample.Criteria criteria=example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(eduCourse.getCourseName())){
			criteria.andCourseNameLike("%"+eduCourse.getCourseName()+"%");
		}
		if(StringUtil.isNotBlank(eduCourse.getCourseMajorId())){
			criteria.andCourseMajorIdEqualTo(eduCourse.getCourseMajorId());
		}
		if(StringUtil.isNotBlank(sort)){
			example.setOrderByClause(sort+" desc");
		}
		return eduCourseMapper.selectByExample(example);
	}

	@Override
	public List<SysUser> userSelectOneCourseList(String courseFlow) {
		List<SysUser> userList=eduCourseExtMapper.searchUserByCourse(courseFlow);
		return userList;
	}
	@Override
	public List<EduCourseSchedule> searchScheduleList(EduCourseSchedule schedule) {
		EduCourseScheduleExample example = new EduCourseScheduleExample();
		com.pinde.sci.model.mo.EduCourseScheduleExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(schedule!=null){
			if(StringUtil.isNotBlank(schedule.getUserFlow())){
				criteria.andUserFlowEqualTo(schedule.getUserFlow());
			}
			if(StringUtil.isNotBlank(schedule.getChapterFlow())){
				criteria.andChapterFlowEqualTo(schedule.getChapterFlow());
			}
			if(StringUtil.isNotBlank(schedule.getCourseFlow())){
				criteria.andCourseFlowEqualTo(schedule.getCourseFlow());
			}
			if(StringUtil.isNotBlank(schedule.getStudyStatusId())){
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
		Map<String, Object> resultMap=new HashMap<String, Object>();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		for (NjmuEduStudyStatusEnum status : NjmuEduStudyStatusEnum.values()) {
			paramMap.put("courseFlow", courseFlow);
			paramMap.put("studyStatusId",status.getId());
			int count=this.eduCourseExtMapper.countUserByStudyStatus(paramMap);
			resultMap.put(status.getId(), count);
		}
		return resultMap;
	}

	@Override
	public List<EduStudentCourse> searchStudentCourse(EduStudentCourse eduStudentCourse) {
		EduStudentCourseExample example = new EduStudentCourseExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(eduStudentCourse.getUserFlow())){
			criteria.andUserFlowEqualTo(eduStudentCourse.getUserFlow());
		}
		if(StringUtil.isNotBlank(eduStudentCourse.getCourseFlow())){
			criteria.andCourseFlowEqualTo(eduStudentCourse.getCourseFlow());
		}
		return eduStudentCourseMapper.selectByExample(example);
	}

	@Override
	public List<EduCourse> searchCourseListByCourseFlowList(List<String> courseFlowList) {
		if(courseFlowList != null && !courseFlowList.isEmpty()){
			EduCourseExample example = new EduCourseExample();
			example.createCriteria().andCourseFlowIn(courseFlowList).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			//example.setOrderByClause("CREATE_TIME DESC");
			return eduCourseMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public int chooseCourse(String userFlow, String courseFlow) {
		if(StringUtil.isNotBlank(userFlow)&&StringUtil.isNotBlank(courseFlow)){
			EduStudentCourse eduStudentCourse = new EduStudentCourse();
			eduStudentCourse.setRecordFlow(PkUtil.getUUID());
			eduStudentCourse.setUserFlow(userFlow);
			eduStudentCourse.setCourseFlow(courseFlow);
			eduStudentCourse.setStudyStatusId(NjmuEduStudyStatusEnum.Underway.getId());
			eduStudentCourse.setStudyStatusName(NjmuEduStudyStatusEnum.Underway.getName());
			eduStudentCourse.setChooseTime(DateUtil.getCurrDateTime());
			eduStudentCourse.setAchieveCredit(GlobalConstant.FLAG_N);
			GeneralMethod.setRecordInfo(eduStudentCourse, true);
			return this.eduStudentCourseMapper.insertSelective(eduStudentCourse);
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<EduCourse> searchCourseByCondition(String condition) {
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("condition", condition);
		return this.eduCourseExtMapper.searchCourseByCondition(paramMap);
	}

	@Override
	public Map<String, Map<String, Object>> searchCourseInfoAndCountByEduUserExt(
			List<EduUserExt> eduUserExtList) {
		Map<String, Map<String, Object>> searchAndCountCourseMap=new HashMap<String, Map<String,Object>>();
		Map<String, Object> searchCourseMap=new HashMap<String, Object>();
		Map<String, Object> countCourseMap=new HashMap<String, Object>();
		if(eduUserExtList!=null && !eduUserExtList.isEmpty()){
		 for(EduUserExt eduUserExt:eduUserExtList){
		  if(eduUserExt!=null){
			 if(eduUserExt.getSysUser()!=null){
				 Map<String,Object> paramMap=new HashMap<String, Object>();
			     paramMap.put("user",eduUserExt.getSysUser());
				 paramMap.put("course", null);
				 List<EduCourse> tchCourseList=eduCourseExtMapper.searchTchCourseList(paramMap);
				 int courseAmount=0;
				 if(tchCourseList!=null && !tchCourseList.isEmpty()){
					 courseAmount=tchCourseList.size();
				 }
				 searchCourseMap.put(eduUserExt.getUserFlow(),tchCourseList);
				 countCourseMap.put(eduUserExt.getUserFlow(), courseAmount);
			 }
		  }				    
		  }
		}
		searchAndCountCourseMap.put("searchCourseMap", searchCourseMap);
		searchAndCountCourseMap.put("countCourseMap", countCourseMap);
		return searchAndCountCourseMap;
	}

//	@Override
//	public List<EduCourseExt> searchTeacherChapterInfo(Map<String, Object> paramMap) {
//		return eduCourseExtMapper.searchTeacherChapterInfo(paramMap);
//	}

	@Override
	public List<EduCourse> searchStudentCreditCourses(String userFlow) {
		List<EduCourse> courseList = null;
		if(StringUtil.isNotBlank(userFlow)){
			courseList = this.eduCourseExtMapper.selectStudentCreditCourses(userFlow);
		}
		return courseList;
	}

	@Override
	public String checkCourseFile(MultipartFile file) {
		List<String> suffixList = new ArrayList<String>();
		if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("njmuedu_courseFile_support_suffix")))){
			suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("njmuedu_courseFile_support_suffix").toLowerCase()).split(","));
		}
		String fileName = file.getOriginalFilename();//文件名
		String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
		if(!suffixList.contains(suffix.toLowerCase())){
			return "请上传 "+InitConfig.getSysCfg("njmuedu_courseFile_support_suffix")+"格式的文件";
		}
		long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("njmuedu_courseFile_limit_size")));//图片大小限制
		if(file.getSize() > limitSize*1024*1024){
			return GlobalConstant.UPLOAD_IMG_SIZE_ERROR +limitSize +"M！" ;
		}
		return GlobalConstant.FLAG_Y;//可执行保存
	}
	
	
	@Override
	public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName){
		String path = GlobalConstant.FLAG_N;
		if(file.getSize() > 0){
			//创建目录
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator + folderName + File.separator+ dateString ;
			File fileDir = new File(newDir);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			//文件名
			String originalFilename = file.getOriginalFilename();
			originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile = new File(fileDir, originalFilename); 
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存文件失败！");
			}
			
			//删除原文件
			if(StringUtil.isNotBlank(oldFolderName)){
				try {
					oldFolderName = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName;
					File oldFile = new File(oldFolderName);
					if(oldFile.exists()){
						oldFile.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("删除文件失败！");
				}
			}
			path = folderName + "/"+dateString+"/"+originalFilename;
		}
		
		return path;
	}

	@Override
	public List<EduCourse> searchTeachCourseList(String userFlow) {
		return eduCourseExtMapper.searchTeachCourseList(userFlow);
	}

	@Override
	public String saveStudentWorkToDirs(String oldFolderName, MultipartFile file, String folderName, String questionFlow) {
		String path = GlobalConstant.FLAG_N;
		if(file.getSize() > 0){
			//创建目录
			//String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator + folderName + File.separator + questionFlow;
			File fileDir = new File(newDir);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			//删除原文件
			if(StringUtil.isNotBlank(oldFolderName)){
				try {
					oldFolderName = newDir + File.separator +  oldFolderName;
					File oldFile = new File(oldFolderName);
					if(oldFile.exists()){
						oldFile.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("删除文件失败！");
				}
			}
			
			//文件名
			String originalFilename = file.getOriginalFilename();
			String userFlow = GlobalContext.getCurrentUser().getUserFlow();//学生流水号做文件名(批量下载时做查询过滤使用)
			originalFilename = userFlow + originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile = new File(fileDir, originalFilename); 
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存文件失败！");
			}
			path = folderName + "/" + questionFlow + "/" + originalFilename;
		}
		
		return path;
	}

	@Override
	public List<EduCourse> findByCourseName(String courseName) {
		EduCourseExample courseExample = new EduCourseExample();
		EduCourseExample.Criteria criteria = courseExample.createCriteria();
		criteria.andCourseNameEqualTo(courseName).andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
		return eduCourseMapper.selectByExample(courseExample);
	}
}
