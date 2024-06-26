package com.pinde.sci.biz.edu.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.edu.IEduCourseBiz;
import com.pinde.sci.biz.edu.IEduCourseMajorBiz;
import com.pinde.sci.biz.edu.IEduStudentCourseBiz;
import com.pinde.sci.biz.edu.IEduUserBiz;
import com.pinde.sci.biz.edu.IEduImportRecordBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.RegionUtil;
import com.pinde.sci.dao.base.EduStudentCourseMapper;
import com.pinde.sci.dao.base.EduUserMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.dao.edu.EduCourseMajorExtMapper;
import com.pinde.sci.dao.edu.EduUserExtMapper;
import com.pinde.sci.enums.edu.ImpTypeEnum;
import com.pinde.sci.enums.pub.UserNationEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.res.RecDocCategoryEnum;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.MarriageTypeEnum;
import com.pinde.sci.enums.sys.PoliticsAppearanceEnum;
import com.pinde.sci.form.edu.EduUserExtInfoForm;
import com.pinde.sci.form.edu.EduUserForm;
import com.pinde.sci.model.edu.EduCourseMajorExt;
import com.pinde.sci.model.edu.EduUserExt;
import com.pinde.sci.model.edu.EduUserInfoExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.EduUserExample.Criteria;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.*;
import java.util.regex.Pattern;
@Service
@Transactional(rollbackFor=Exception.class)
public class EduUserBizImpl implements IEduUserBiz {
	@Autowired
	private EduUserMapper eduUserMapper;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private EduUserExtMapper eduUserExtMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private EduCourseMajorExtMapper courseMajorExtMapper;
	@Autowired
	private IEduCourseBiz eduCourseBiz;
	@Autowired
	private IEduStudentCourseBiz eduStudentCourseBiz;
	@Autowired
	private IEduCourseMajorBiz eduCourseMajorBiz;
	@Autowired
	private EduStudentCourseMapper studentCourseMapper;
	@Autowired
	private IEduImportRecordBiz importRecordBiz;

	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
	}
	
	@Override
	public int addEduUser(EduUser eduUser) {
		String userFlow = eduUser.getUserFlow();
		if(StringUtil.isNotBlank(userFlow)){
			EduUser search = readEduUser(userFlow);
			if(search == null){
				GeneralMethod.setRecordInfo(eduUser, true);
				return eduUserMapper.insert(eduUser);
			}else{
				GeneralMethod.setRecordInfo(eduUser, false);
				return eduUserMapper.updateByPrimaryKeySelective(eduUser);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public EduUser readEduUser(String userFlow) {
		if(StringUtil.isNotBlank(userFlow)){
			return eduUserMapper.selectByPrimaryKey(userFlow);
		}
		return null;
	}

	@Override
	public int saveUserAndEduUser(SysUser sysUser, EduUser eduUser) {
		String userCode = sysUser.getUserCode();
		if(StringUtil.isNotBlank(userCode)){
			sysUser.setUserCode(userCode.trim());
		}
		String userPhone =  sysUser.getUserPhone();
		if(StringUtil.isNotBlank(userPhone)){
			sysUser.setUserPhone(userPhone.trim());
		}
		String idNo = sysUser.getIdNo();
		if(StringUtil.isNotBlank(idNo)){
			sysUser.setIdNo(idNo.trim());
		}
		String userEmail = sysUser.getUserEmail();
		if(StringUtil.isNotBlank(userEmail)){
			sysUser.setUserEmail(userEmail.trim());
		}
		String orgFlow = sysUser.getOrgFlow();
		SysOrg sysOrg = null;
		if(StringUtil.isNotBlank(orgFlow)){
			sysOrg = orgBiz.readSysOrg(orgFlow);
			sysUser.setOrgName(sysOrg.getOrgName());
		}
		String majorId = eduUser.getMajorId();
		if(StringUtil.isNotBlank(majorId)){
			eduUser.setMajorName(DictTypeEnum.CourseMajor.getDictNameById(majorId));
		}
		userBiz.saveUser(sysUser);
		eduUser.setUserFlow(sysUser.getUserFlow());
		return addEduUser(eduUser);
	}

	@Override
	public String uploadImg(MultipartFile file) {
		if(file!=null){
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
				return GlobalConstant.UPLOAD_IMG_TYPE_ERROR;
			}
			long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
			if(file.getSize()>limitSize*1024*1024){
				return GlobalConstant.UPLOAD_IMG_SIZE_ERROR +limitSize +"M" ;
			}
			try {
				/*创建目录*/
				String dateString = DateUtil.getCurrDate2();
				String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+"eduImages"+File.separator + dateString ;
				File fileDir = new File(newDir);
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				/*文件名*/
				fileName = file.getOriginalFilename();
				fileName = PkUtil.getUUID()+fileName.substring(fileName.lastIndexOf("."));
				File newFile = new File(fileDir, fileName);
				file.transferTo(newFile);
				return "success:eduImages/"+dateString+"/"+fileName;
			} catch (Exception e) {
				e.printStackTrace();
				return GlobalConstant.UPLOAD_FAIL;
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	@Override
	public List<EduUserExt> searchList(EduUserExt userExt) {
		return this.eduUserExtMapper.selectList(userExt);
	}

	@Override
	public List<EduUserExt> searchEduUserForManage(Map<String, Object> paramMap) {
		return this.eduUserExtMapper.searchEduUserForManage(paramMap);
	}

	@Override
	public List<EduUserExt> searchEduUserForCourseDetail(
			Map<String, Object> paramMap) {
		return this.eduUserExtMapper.searchEduUserForCourseDetail(paramMap);
	}

	@Override
	public int saveUserAndRole(SysUser sysUser, EduUser eduUser,String type) {
		if(StringUtil.isNotBlank(sysUser.getSexId())){
			sysUser.setSexName(UserSexEnum.getNameById(sysUser.getSexId()));
		}
		if(StringUtil.isNotBlank(sysUser.getTitleId())){
			sysUser.setTitleName(DictTypeEnum.UserTitle.getDictNameById(sysUser.getTitleId()));
		}
		if(StringUtil.isNotBlank(sysUser.getEducationId())){
			sysUser.setEducationName(DictTypeEnum.UserEducation.getDictNameById(sysUser.getEducationId()));
		}
		if(StringUtil.isNotBlank(sysUser.getDegreeId())){
			sysUser.setDegreeName(DictTypeEnum.UserDegree.getDictNameById(sysUser.getDegreeId()));
		}
		userBiz.saveUser(sysUser);
		String userFlow = sysUser.getUserFlow();
		eduUser.setUserFlow(userFlow);
		addEduUser(eduUser);
		//角色
		SysUserRole userRole = new SysUserRole();
		userRole.setRoleFlow(PkUtil.getUUID());
		userRole.setUserFlow(userFlow);
		userRole.setWsId(GlobalConstant.EDU_WS_ID);
		//开关获取
		if(GlobalConstant.TEACHER_ROLE.equals(type)){
			userRole.setRoleFlow(InitConfig.getSysCfg("teacher_role_flow"));
		}
		userRole.setAuthTime(DateUtil.getCurrDateTime());
		return userRoleBiz.saveSysUserRole(userRole);

	}

	@Override
	public EduUserExt readEduUserInfo(String userFlow) {
		return eduUserExtMapper.readEduUserInfo(userFlow);
	}

//	@Override
//	public List<EduUserExt> searchEduUserByFlows(List<String> teacherFlowList) {
//		Map<String, Object> paramMap=new HashMap<String, Object>();
//		paramMap.put("teacherFlowList", teacherFlowList);
//		return eduUserExtMapper.searchEduUserList(paramMap);
//	}

	@Override
	public List<EduUserExt> searchEduAndCourseList(
			Map<String, Object> paramMap) {
		return eduUserExtMapper.searchEduAndCourseList(paramMap);
	}
	
	@Override
	public int saveEduUser(EduUser eduUser) {
		if(StringUtil.isNotBlank(eduUser.getUserFlow())){
			GeneralMethod.setRecordInfo(eduUser, false);
			return eduUserMapper.updateByPrimaryKeySelective(eduUser);
		}else{
			eduUser.setUserFlow(PkUtil.getUUID());
			return onlySaveEduUser(eduUser);
		}
	}

	private int onlySaveEduUser(EduUser eduUser){
		GeneralMethod.setRecordInfo(eduUser, true);
		return eduUserMapper.insertSelective(eduUser);
	}

	@Override
	public List<EduUser> search(EduUser eduUser) {
		EduUserExample example=new EduUserExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		 if(StringUtil.isNotBlank(eduUser.getSid())){
			 criteria.andSidEqualTo(eduUser.getSid());
			 return eduUserMapper.selectByExample(example);
		 }else{
			 return null;
		 }
	}

	@Override
	public int save(EduUserForm form) throws Exception{
		if(form!=null){
			String docRole = InitConfig.getSysCfg("res_doctor_role_flow");
			SysUser user=form.getSysUser();
			EduUser eduUser=form.getEduUser();
			ResDoctor resDoctor=form.getResDoctor();
			resDoctor.setDoctorCategoryId(RecDocCategoryEnum.Graduate.getId());
			resDoctor.setDoctorCategoryName(RecDocCategoryEnum.Graduate.getName());

			if(user!=null){
				userBiz.saveUser(user);
				userRoleBiz.saveSysUserRoleNoDel(user.getUserFlow(),docRole);
				if(resDoctor!=null){
					if(StringUtil.isNotBlank(resDoctor.getDoctorFlow())){
						resDoctorBiz.editDoctor(resDoctor);
					}else{
						String userFlow=user.getUserFlow();
						resDoctor.setDoctorFlow(userFlow);
						resDoctorBiz.onlySaveResDoctor(resDoctor);
					}
				}
				if(eduUser!=null){
					EduUserExtInfoForm eduUserExtInfoForm=form.getEduUserExtInfoForm();
					//String content = JaxbUtil.convertToXml(eduUserExtInfoForm);
					String content = convertExtFormToXml(eduUserExtInfoForm, eduUser.getUserFlow());
					eduUser.setContent(content);
					if(StringUtil.isNotBlank(eduUser.getUserFlow())){
						return saveEduUser(eduUser);
					}else{
						String userFlow=user.getUserFlow();
						eduUser.setUserFlow(userFlow);
						return onlySaveEduUser(eduUser);
					}
				}
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public EduUser findByFlow(String userFlow) {
		return eduUserMapper.selectByPrimaryKey(userFlow);
	}

	@Override
	public List<EduUser> searchEduUserBySysUser(Map<String, Object> paramMap){
		return eduUserExtMapper.searchEduUserBySysUser(paramMap);
	}

	@Override
	public List<EduUserInfoExt> searchEduUserInfoExtBySysUser(
			Map<String, Object> paramMap) {
		return eduUserExtMapper.searchEduUserInfoExtBySysUser(paramMap);
	}

	//	@Override
//	public List<EduUser> searchEduByOrg(String orgFlow){
//		EduUserExample example = new EduUserExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(orgFlow);
//		return eduUserMapper.selectByExample(example);
//	}
	@Override
	public int importCourseFromExcel(MultipartFile file) {
		Map<String,String> resultMap=RegionUtil.getRefMap();
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			return parseExcel(wb,resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return 0;
	}

	private boolean isNumeric(String str){
		boolean result =false;
		Pattern pattern = Pattern.compile("[0-9]*.?[0-9]*");
	   if(pattern.matcher(str).matches()){
		   result=true;
	   }
		return result;
	}
	
	private Map<String, Object> parseExcelToGrade(Workbook wb){
		int succCount = 0, loseCount=0;
		String impFlow = null;
		Map<String, Object> returnDataMap=new HashMap<String,Object>();
		List<Integer> loseList=new ArrayList<Integer>();
		int sheetNum = wb.getNumberOfSheets();
		if(sheetNum>0){
			List<String> colnames = new ArrayList<String>();
			Sheet sheet;
			try{
				sheet = wb.getSheetAt(0);
			}catch(Exception e){
				sheet = wb.getSheetAt(0);
			}
			//解决空行问题
			int row_num = sheet.getLastRowNum();
//			int row_num=0;
//			for(int i=1;i<=num;i++){//除title以外的行进行处理
//				Row row =sheet.getRow(i);
//				int row_count=row .getLastCellNum();//获取一行的所有单元格数
//				if(row_count>1){
//					int result=0;
//					for(int j=1;j<=row_count;j++){
//						String data=row.getCell(j).getStringCellValue();
//						if(StringUtil.isNotBlank(data)){
//							result++;
//						}
//					}
//					if(result==row_count){
//						row_num++;
//					}
//				}
//			}
			if(row_num<1){
				throw new RuntimeException("没有数据");
			}
			//获取表头
			Row titleR =  sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			String title = "";
			for(int i = 0 ; i <cell_num; i++){
				title = titleR.getCell(i).getStringCellValue();
				 colnames.add(title);
			}
			for(int i = 1;i <=row_num; i++){//读所有行
				if(StringUtil.isBlank(impFlow)){//导入批次流水号
					impFlow = PkUtil.getUUID();
				}
				Row r =  sheet.getRow(i);
				EduUser eduUser=new EduUser();
				EduCourse course=new EduCourse();
				String flag=GlobalConstant.UPLOAD_SUCCESSED;
				EduStudentCourse updateAndInsertCourse=new EduStudentCourse();
				String sid;//学号
				String userName;//姓名
				String courseCode;//课程代码
				String coursePeriod;
				String courseCredit;
				String courseGrade;
				String courseName;
				String pacificGrade;//平时成绩
				String teamEndGrade;//期末成绩
				for (int j = 0; j < colnames.size(); j++) {//读一行
					String value = "";
				    Cell cell = r.getCell(j);
				    if(cell == null){
				    	continue;
				    }
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if (cell.getCellType() == 1) {
							value = cell.getStringCellValue().trim();
						} else {
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					if("学号".equals(colnames.get(j))){
						sid=value;
						eduUser.setSid(sid);
					}else if("姓名".equals(colnames.get(j))){
						userName=value;
					}else if("课程代码".equals(colnames.get(j))){
						courseCode=value;
						course.setCourseCode(courseCode);
						updateAndInsertCourse.setCourseCode(value);
					}else if("课程名称".equals(colnames.get(j))){
						courseName=value;
						updateAndInsertCourse.setCourseName(value);
					}else if("学时".equals(colnames.get(j))){
						coursePeriod=value;
						updateAndInsertCourse.setCoursePeriod(value);
					}else if("学分".equals(colnames.get(j))){
						courseCredit=value;
						updateAndInsertCourse.setCourseCredit(value);
					}else if("总成绩".equals(colnames.get(j))){
						courseGrade=value;
						if(isNumeric(courseGrade)){
							updateAndInsertCourse.setCourseGrade(courseGrade);
						}else{
					    	List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("XjIsPassed");
					    	for(SysDict dict:dictList){
					    		if(dict.getDictName().equals(courseGrade)){
					    			updateAndInsertCourse.setCourseGrade(dict.getDictId());
					    		}
					    	}
						}
					}else if("平时成绩".equals(colnames.get(j))){
						pacificGrade=value;
						updateAndInsertCourse.setPacificGrade(pacificGrade.trim());
					}else if("期末成绩".equals(colnames.get(j))){
						updateAndInsertCourse.setTeamEndGrade(value.trim());
					}else if("修读性质".equals(colnames.get(j))){
						List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("XjStudyWay");
						for(SysDict dict:dictList){
							if(dict.getDictName().equals(value)){
								updateAndInsertCourse.setStudyWayId(dict.getDictId());
								updateAndInsertCourse.setStudyWayName(dict.getDictName());
							}
						}
					}else if("考核方式".equals(colnames.get(j))){
						List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("XjEvaluationMode");
						for(SysDict dict:dictList){
							if(dict.getDictName().equals(value)){
								updateAndInsertCourse.setAssessTypeId(dict.getDictId());
								updateAndInsertCourse.setAssessTypeName(dict.getDictName());
							}
						}
					}else if("获得学期".equals(colnames.get(j))){
						List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("RecruitSeason");
						for(SysDict dict:dictList){
							if(dict.getDictName().equals(value)){
								updateAndInsertCourse.setGradeTermId(dict.getDictId());
								updateAndInsertCourse.setGradeTermName(dict.getDictName());
							}
						}
					}else if("获得学年".equals(colnames.get(j))){
						updateAndInsertCourse.setStudentPeriod(value);
					}
				}
				//判断课程是否在course表这种存在
				List<EduCourse>eduCoursesList=eduCourseBiz.readCourse(course);
				EduCourse exitCourse=null;
				if(eduCoursesList!=null&&!eduCoursesList.isEmpty()){
					exitCourse=eduCoursesList.get(0);
				}
				if(exitCourse==null){
					if(StringUtil.isBlank(updateAndInsertCourse.getCourseCode())||StringUtil.isBlank(updateAndInsertCourse.getCourseName())){
						flag=GlobalConstant.UPLOAD_FAIL;
					}
				}
				//判断此人是否在eduUser中存在
				List<EduUser> eduUsersList=search(eduUser);
				EduUser user=null;
				if(eduUsersList!=null&&!eduUsersList.isEmpty()){
					user=eduUsersList.get(0);
				}else{
					flag=GlobalConstant.UPLOAD_FAIL;
				}
				if(user!=null){
					//判断课程之间的联系是否存在
				    EduCourseMajor major=new EduCourseMajor();
					major.setMajorId(user.getMajorId());
					major.setPlanYear(user.getPeriod());
					if(exitCourse!=null){
						major.setCourseFlow(exitCourse.getCourseFlow());
					}
					//判断课程之间的联系是否存在
					EduStudentCourse testCourse=new EduStudentCourse();

					List<EduCourseMajor>exitCourseMajors=eduCourseMajorBiz.searchCourseMajorByCourseFlowList(major, null);
						testCourse.setUserFlow(user.getUserFlow());
						if(exitCourse!=null){
							testCourse.setCourseFlow(exitCourse.getCourseFlow());
						}
						testCourse.setCourseCode(updateAndInsertCourse.getCourseCode());
						testCourse.setStudentPeriod(user.getPeriod());
						List<EduStudentCourse> result=eduStudentCourseBiz.searchStudentCourseList(testCourse);
						if(result!=null&&!result.isEmpty()){
							updateAndInsertCourse.setRecordFlow(result.get(0).getRecordFlow());
							updateAndInsertCourse.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
						}
						if(GlobalConstant.UPLOAD_SUCCESSED.equals(flag)){
							if(exitCourse!=null){
								updateAndInsertCourse.setCourseFlow(exitCourse.getCourseFlow());
							}
							updateAndInsertCourse.setUserFlow(user.getUserFlow());
							if(exitCourseMajors!=null&&!exitCourseMajors.isEmpty()){
								updateAndInsertCourse.setCourseTypeId(exitCourseMajors.get(0).getCourseTypeId());
								updateAndInsertCourse.setCourseTypeName(exitCourseMajors.get(0).getCourseTypeName());
							}
							if(exitCourse!=null){
								updateAndInsertCourse.setCourseCredit(exitCourse.getCourseCredit());
								updateAndInsertCourse.setCoursePeriod(exitCourse.getCoursePeriod());
								updateAndInsertCourse.setCourseName(exitCourse.getCourseName());
								updateAndInsertCourse.setCourseNameEn(exitCourse.getCourseNameEn());
								updateAndInsertCourse.setCourseCode(exitCourse.getCourseCode());
							}else{
								if(StringUtil.isNotBlank(updateAndInsertCourse.getCourseCode())){
									updateAndInsertCourse.setCourseFlow(updateAndInsertCourse.getCourseCode());
								}
							}
//							updateAndInsertCourse.setStudentPeriod(user.getPeriod());
							updateAndInsertCourse.setImpFlow(impFlow);
							eduStudentCourseBiz.save(updateAndInsertCourse);
							succCount++;//成功的数
						}
				}
				if(GlobalConstant.UPLOAD_FAIL.equals(flag)){
					if(StringUtil.isBlank(eduUser.getSid())&&StringUtil.isBlank(updateAndInsertCourse.getCourseCode())&&StringUtil.isBlank(updateAndInsertCourse.getCourseName())){

					}else{
						loseCount++;//失败的数
						int hanghao=i+1;
						loseList.add(hanghao);
					}
					continue;
				}
			}
			if(succCount > 0){
				PubImportRecord importRecord = new PubImportRecord();
				importRecord.setImpFlow(impFlow);
				importRecord.setImpTypeId(ImpTypeEnum.EduStudentCourse.getId());
				importRecord.setImpTypeName(ImpTypeEnum.EduStudentCourse.getName());
				importRecord.setImpTime(DateUtil.getCurrDateTime());
				importRecord.setImpNum(String.valueOf(succCount));
				importRecordBiz.addRecord(importRecord);
			}
			returnDataMap.put("succCount", succCount);
			returnDataMap.put("loseCount", loseCount);
			returnDataMap.put("loseList", loseList);
		}
		return returnDataMap;
	}
	
	private int parseExcel(Workbook wb,Map<String,String> resultMap) throws Exception {
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
			if(row_num<1){
				throw new RuntimeException("没有数据");
			}
			for(int i = 1;i <=row_num; i++){
				Row r =  sheet.getRow(i);
            	SysUser sysUser = new SysUser();
            	EduUser eduUser = new EduUser();
            	ResDoctor resDoctor = new ResDoctor();
            	EduUserExtInfoForm euInfo =new EduUserExtInfoForm();
            	EduUserForm eduUserForm=new EduUserForm();
            	eduUserForm.setEduUser(eduUser);
            	eduUserForm.setEduUserExtInfoForm(euInfo);
            	eduUserForm.setSysUser(sysUser);
            	eduUserForm.setResDoctor(resDoctor);
            	String period;//入学年级
            	String className;//班级
            	String sid;//学号
            	String userName;//姓名
            	String nameSpell;//考生姓名拼音
            	String studentCode;//考生编号
            	String cretTypeId;//证件类型码
            	String idNo;//证件号码
            	String userBirthday;//出生日期
            	String nationId;//民族码
            	String sexId;//性别码
            	String maritalId;//婚姻状况id
            	String politicsStatusId;//政治面貌码
            	String admitTypeId;//录取类别码
            	String majorId;//专业Id
            	String majorName;//专业Name
            	String userEmail;//邮箱地址
            	String userPhone;//手机号码
            	String weiXinId;//微信
            	String trainTypeId;//培养层次码
            	String trainOrgName;//学位分委员会
            	String orgCode;//培养单位码
            	String orgName;//培养单位名称
            	String studentSourceId;//考生类别码
            	String awardDegreeCategoryName;//授予学位类别
            	String atSchoolStatusName;//在校状态
            	String recruitSeasonName;//招生季节
            	String schoolRollStatusName;//学籍状态
            	String nativePlaceAreaId;//籍贯
            	String domicilePlaceId;//户口所在地码
            	String domicilePlaceAddress;//户口所在地详细地址
            	String recordLocationId;//档案所在地码
            	String oldFileLocationOrg;//原档案所在单位
            	String postCode;//原档案所在单位
            	String oldOrgName;//入学前工作学习单位
            	String researchDirName;//研究方向
            	String is5plus3;//是否5+3培养模式
            	String firstTeacher;//导师一
            	String graduateTime;//毕业年月
            	String diplomaCode;//毕业证书编号
            	String awardDegreeCertCode;//学位证书编号
            	String foreignLanguageName;//外国语名称
            	String foreignLanguageScore;//外国语名称
            	String politicalTheoryName;//政治科目名称
            	String politicalTheoryScore;//政治科目分数
            	String reexamineScore;//复试
            	String totalPointsScore;//总分
            	String isExceptionalId;//是否破格
            	String isRecommendId;//是否推免生
            	String firstSubjectCode;
            	String firstSubjectName;
            	String firstSubjectScore;
            	String secondSubjectCode;
            	String secondSubjectName;
            	String secondSubjectScore;
            	String trainCategoryName; //培养类型
            	String underGraduateOrgName;//本科毕业单位名称
        		String underDiplomaCode; //本科毕业证书编号
        		String underAwardDegreeOrg;//获取学士学位单位
        		String underMajor;//获学士学位专业名称
        		String underDegreeCertCode;//学士学位证书编号
        		String underStudyForm;//取得本科学历的学习形式
				String underGraduateTime;//本科毕业年月
				String underAwardDegreeTime;//获得学士学位年月
        		String underGraduateMajor;//本科毕业专业名称
        		String masterUnitName;//硕士毕业单位名称
        		String masterDiplomaCode;//硕士毕业证书编号
        		String masterAwardDegreeOrg;//获取硕士学位单位
        		String masterDegreeCertCode;//硕士学位证书编号
        	    String masterStudyForm;//取得硕士学位方式
        		String masterGraduateTime;//硕士毕业年月
        		String masterAwardDegreeTime;//获得硕士学位年月
        		String masterGraduateMajor;//硕士毕业专业名称
        		String code;//最后学位证书编号
        		String reAndPuStatusContent;//入学前奖惩情况
        		String isStay ;//是否住宿

        		Map<String ,SysOrg> orgMap = getOrgByCodeMap();
        		Map<String ,SysOrg> orgNameMap = getOrgByNameMap();
				for (int j = 0; j < colnames.size(); j++) {
					String value = "";
				    Cell cell = r.getCell(j);
				    if(cell == null){
				    	continue;
				    }
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if (cell.getCellType() == 1) {
							value = cell.getStringCellValue().trim();
						} else {
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					if("入学年级".equals(colnames.get(j))){
						period = value;
						eduUser.setPeriod(period);
						resDoctor.setSessionNumber(period);
					}else if("班级".equals(colnames.get(j))){
						className = value;
						eduUser.setClassName(className);
						if(StringUtil.isNotBlank(className)){
							eduUser.setClassId(getDictId(className, "XjClass"));
						}
					}else if("学号".equals(colnames.get(j))){
						sid = value;
						eduUser.setSid(sid);
						sysUser.setUserCode(sid);
						resDoctor.setDoctorCode(sid);
					}else if("姓名".equals(colnames.get(j))){
						userName = value;
						sysUser.setUserName(userName);
						resDoctor.setDoctorName(userName);
					}else if("考生姓名拼音".equals(colnames.get(j))){
						nameSpell = value;
						eduUser.setNameSpell(nameSpell);
					}else if("考生编号".equals(colnames.get(j))){
						studentCode = value;
						eduUser.setStudentCode(studentCode);
					}else if("证件类型码".equals(colnames.get(j))){
						cretTypeId = value;
						sysUser.setCretTypeId(cretTypeId);
						if(StringUtil.isNotBlank(cretTypeId)){
							sysUser.setCretTypeName(CertificateTypeEnum.getNameById(cretTypeId));
						}
					}else if("证件号码".equals(colnames.get(j))){
						idNo = value;
						sysUser.setIdNo(idNo);
					}else if("出生日期".equals(colnames.get(j))){
						userBirthday = value;
						sysUser.setUserBirthday(userBirthday);
					}else if("民族码".equals(colnames.get(j))){
						nationId = value;
						sysUser.setNationId(nationId);
						if(StringUtil.isNotBlank(nationId)){
							sysUser.setNationName(UserNationEnum.getNameById(nationId));
						}
					}else if("婚姻状况".equals(colnames.get(j))){
						maritalId = value;
						sysUser.setMaritalId(maritalId);
						if(StringUtil.isNotBlank(maritalId)){
							sysUser.setMaritalName(MarriageTypeEnum.getNameById(maritalId));
						}
					}else if("政治面貌码".equals(colnames.get(j))){
						politicsStatusId = value;
						sysUser.setPoliticsStatusId(politicsStatusId);
						if(StringUtil.isNotBlank(politicsStatusId)){
							sysUser.setPoliticsStatusName(PoliticsAppearanceEnum.getNameById(politicsStatusId));
						}
					}else if("性别码".equals(colnames.get(j))){
						sexId = value;
						sysUser.setSexId(trainSex(sexId));
						if(StringUtil.isNotBlank(sexId)){
							sysUser.setSexName(UserSexEnum.getNameById(sysUser.getSexId()));
						}
					}else if("电子邮箱".equals(colnames.get(j))){
						userEmail = value;
						sysUser.setUserEmail(userEmail);
					}else if("手机号码".equals(colnames.get(j))){
						userPhone = value;
						sysUser.setUserPhone(userPhone);
					}else if("微信号".equals(colnames.get(j))){
						weiXinId = value;
						sysUser.setWeiXinName(weiXinId);
					}else if("录取类别码".equals(colnames.get(j))){
						admitTypeId = value;
						eduUser.setAdmitTypeId(admitTypeId);
						if(StringUtil.isNotBlank(admitTypeId)){
							eduUser.setAdmitTypeName(DictTypeEnum.AdmitType.getDictNameById(admitTypeId));
						}
					}else if("专业代码".equals(colnames.get(j))){
						majorId = value;
						eduUser.setMajorId(majorId);
						if(StringUtil.isNotBlank(majorId)){
							eduUser.setMajorName(DictTypeEnum.Major.getDictNameById(majorId));
						}
					}else if("专业名称".equals(colnames.get(j))){
						majorName = value;
						if(StringUtil.isNotBlank(majorName)){
							eduUser.setMajorName(majorName);
							if(StringUtil.isBlank(eduUser.getMajorId())){
								eduUser.setMajorId(getDictId(majorName, "Major"));
							}
						}
					}else if("培养层次".equals(colnames.get(j))){
						trainTypeId = value;
						eduUser.setTrainTypeId(trainTypeId);
						if(StringUtil.isNotBlank(trainTypeId)){
							eduUser.setTrainTypeName(DictTypeEnum.TrainType.getDictNameById(trainTypeId));
						}
					}else if("学位分委员会".equals(colnames.get(j))){
						trainOrgName = value;
						eduUser.setTrainOrgName(trainOrgName);
						sysUser.setDeptName(trainOrgName);
						if(StringUtil.isNotBlank(trainOrgName)){
							String deptFlow=getDeptFlow(trainOrgName, GlobalContext.getCurrentUser().getOrgFlow());
							eduUser.setTrainOrgId(deptFlow);
							sysUser.setDeptFlow(deptFlow);
						}
					}else if("培养单位码".equals(colnames.get(j))){
						orgCode = value;
						if(StringUtil.isNotBlank(orgCode)){
							SysOrg org=orgMap.get(orgCode);
							if(org!=null){
								resDoctor.setOrgFlow(org.getOrgFlow());
								resDoctor.setOrgName(org.getOrgName());
							}
						}
					}else if("培养单位".equals(colnames.get(j))){
						orgName = value;
						if(StringUtil.isBlank(resDoctor.getOrgName())){
							resDoctor.setOrgName(orgName);
							SysOrg org = orgNameMap.get(orgName);
							if(org!=null){
								resDoctor.setOrgFlow(org.getOrgFlow());
							}
						}
					}else if("考生类别".equals(colnames.get(j))){
						studentSourceId = value;
						eduUser.setStudentSourceId(studentSourceId);
						if(StringUtil.isNotBlank(studentSourceId)){
							eduUser.setStudentSourceName(DictTypeEnum.StudentSource.getDictNameById(studentSourceId));
						}
					}else if("授予学位类别".equals(colnames.get(j))){
						awardDegreeCategoryName = value;
						eduUser.setAwardDegreeCategoryName(awardDegreeCategoryName);
						if(StringUtil.isNotBlank(awardDegreeCategoryName)){
							eduUser.setAwardDegreeCategoryId(getDictId(awardDegreeCategoryName, "DegreeCategory"));
						}
					}else if("培养类型".equals(colnames.get(j))){
						trainCategoryName = value;
						eduUser.setTrainCategoryId(getDictId(trainCategoryName, "TrainCategory"));
						eduUser.setTrainCategoryName(trainCategoryName);
					}else if("在校状态".equals(colnames.get(j))){
						atSchoolStatusName = value;
						eduUser.setAtSchoolStatusName(atSchoolStatusName);
						if(StringUtil.isNotBlank(atSchoolStatusName)){
							eduUser.setAtSchoolStatusId(getDictId(atSchoolStatusName, "AtSchoolStatus"));
						}
					}else if("学籍状态".equals(colnames.get(j))){
						schoolRollStatusName = value;
						eduUser.setSchoolRollStatusName(schoolRollStatusName);
						if(StringUtil.isNotBlank(schoolRollStatusName)){
							eduUser.setSchoolRollStatusId(getDictId(schoolRollStatusName, "SchoolRollStatus"));
						}
					}else if("招生季节".equals(colnames.get(j))){
						recruitSeasonName = value;
						eduUser.setRecruitSeasonName(recruitSeasonName);
						if(StringUtil.isNotBlank(recruitSeasonName)){
							eduUser.setRecruitSeasonId(getDictId(recruitSeasonName, "RecruitSeason"));
						}
					}else if("籍贯".equals(colnames.get(j))){
						nativePlaceAreaId = value;
						sysUser.setNativePlaceAreaId(nativePlaceAreaId);
						if (resultMap.containsKey(nativePlaceAreaId)) {
							String [] addString=resultMap.get(nativePlaceAreaId).split("_");
							if(addString!=null && addString.length==5){
								sysUser.setNativePlaceAreaName(addString[0]);
								sysUser.setNativePlaceCityId(addString[1]);
								sysUser.setNativePlaceCityName(addString[2]);
								sysUser.setNativePlaceProvId(addString[3]);
								sysUser.setNativePlaceProvName(addString[4]);
							}
						}
					}else if("户口所在地".equals(colnames.get(j))){
						domicilePlaceId = value;
						if (resultMap.containsKey(domicilePlaceId)) {
							String [] addString=resultMap.get(domicilePlaceId).split("_");
							if(addString!=null && addString.length==5){
							  sysUser.setDomicilePlaceId(addString[3]+","+addString[1]+","+domicilePlaceId);
							  sysUser.setDomicilePlaceName(addString[4]+","+addString[2]+","+addString[0]);
							}
						}
					}else if("户口所在地详细地址".equals(colnames.get(j))){
						domicilePlaceAddress = value;
						sysUser.setDomicilePlaceAddress(domicilePlaceAddress);
					}else if("档案所在地码".equals(colnames.get(j))){
						recordLocationId = value;
						if(resultMap.containsKey(recordLocationId)){
							String [] addString=resultMap.get(recordLocationId).split("_");
							if(addString!=null && addString.length==5){
							  eduUser.setRecordLocationId(addString[3]+","+addString[1]+","+recordLocationId);
							  eduUser.setRecordLocationName(addString[4]+","+addString[2]+","+addString[0]);
							}
						}
					}else if("原档案所在单位".equals(colnames.get(j))){
						oldFileLocationOrg = value;
						euInfo.setOldFileLocationOrg(oldFileLocationOrg);
					}else if("邮政编码".equals(colnames.get(j))){
						postCode = value;
						euInfo.setPostCode(postCode);
					}else if("原学习或工作单位".equals(colnames.get(j))){
						oldOrgName = value;
						euInfo.setOldOrgName(oldOrgName);
					}else if("研究方向".equals(colnames.get(j))){
						researchDirName = value;
						eduUser.setResearchDirName(researchDirName);
						resDoctor.setResearchDirection(researchDirName);
					}else if("是否5+3培养模式".equals(colnames.get(j))){
						is5plus3 = value;
						eduUser.setIs5plus3("是".equals(is5plus3)?GlobalConstant.FLAG_Y:GlobalConstant.FLAG_N);
					}else if("导师一".equals(colnames.get(j))){
						firstTeacher = value;
						eduUser.setFirstTeacher(firstTeacher);
					}else if("毕业时间".equals(colnames.get(j))){
						graduateTime = value;
						eduUser.setGraduateTime(graduateTime);
					}else if("毕业证书编号".equals(colnames.get(j))){
						diplomaCode = value;
						eduUser.setDiplomaCode(diplomaCode);
					}else if("学位证书编号".equals(colnames.get(j))){
						awardDegreeCertCode = value;
						eduUser.setAwardDegreeCertCode(awardDegreeCertCode);
					}else if("外国语名称".equals(colnames.get(j))){
						foreignLanguageName = value;
						euInfo.setForeignLanguageName(foreignLanguageName);
					}else if("外国语成绩".equals(colnames.get(j))){
						foreignLanguageScore = value;
						euInfo.setForeignLanguageScore(foreignLanguageScore);
					}else if("政治理论".equals(colnames.get(j))){
						politicalTheoryName = value;
						euInfo.setPoliticalTheoryName(politicalTheoryName);
					}else if("政治理论成绩".equals(colnames.get(j))){
						politicalTheoryScore = value;
						euInfo.setPoliticalTheoryScore(politicalTheoryScore);
					}else if("复试成绩".equals(colnames.get(j))){
						reexamineScore = value;
						euInfo.setReexamineScore(reexamineScore);
					}else if("总分".equals(colnames.get(j))){
						totalPointsScore = value;
						euInfo.setTotalPointsScore(totalPointsScore);
					}else if("是否破格".equals(colnames.get(j))){
						isExceptionalId = value;
						eduUser.setIsExceptionalId(isExceptionalId);
						if(StringUtil.isNotBlank(isExceptionalId)){
							eduUser.setIsExceptionalName(DictTypeEnum.IsExceptional.getDictNameById(isExceptionalId));
						}
					}else if("是否推免生".equals(colnames.get(j))){
						isRecommendId = value;
						eduUser.setIsRecommendId(isRecommendId);
						if(StringUtil.isNotBlank(isRecommendId)){
							eduUser.setIsRecommendName(DictTypeEnum.IsRecommend.getDictNameById(isRecommendId));
						}
					}else if("业务课一码".equals(colnames.get(j))){
						firstSubjectCode = value;
						euInfo.setFirstSubjectCode(firstSubjectCode);
					}else if("业务课一名称".equals(colnames.get(j))){
						firstSubjectName = value;
						euInfo.setFirstSubjectName(firstSubjectName);
					}else if("业务课一成绩".equals(colnames.get(j))){
						firstSubjectScore = value;
						euInfo.setFirstSubjectScore(firstSubjectScore);
					}else if("业务课二码".equals(colnames.get(j))){
						secondSubjectCode = value;
						euInfo.setSecondSubjectCode(secondSubjectCode);
					}else if("业务课二名称".equals(colnames.get(j))){
						secondSubjectName = value;
						euInfo.setSecondSubjectName(secondSubjectName);
					}else if("业务课二成绩".equals(colnames.get(j))){
						secondSubjectScore = value;
						euInfo.setSecondSubjectScore(secondSubjectScore);
					}else if("本科毕业单位名称".equals(colnames.get(j))){
						underGraduateOrgName = value;
						euInfo.setUnderGraduateOrgName(underGraduateOrgName);
					}else if("本科毕业证编号".equals(colnames.get(j))){
						underDiplomaCode = value;
						euInfo.setUnderDiplomaCode(underDiplomaCode);
					}else if("获学士学位单位名称".equals(colnames.get(j))){
						underAwardDegreeOrg = value;
						euInfo.setUnderAwardDegreeOrg(underAwardDegreeOrg);
					}else if("获学士学位专业名称".equals(colnames.get(j))){
						underMajor = value;
						euInfo.setUnderMajor(underMajor);
					}else if("学士学位证书编号".equals(colnames.get(j))){
						underDegreeCertCode = value;
						euInfo.setUnderDegreeCertCode(underDegreeCertCode);
					}else if("最后学位证编号".equals(colnames.get(j))){
						code = value;
						euInfo.setCode(code);
					}else if("入学前奖惩情况".equals(colnames.get(j))){
						reAndPuStatusContent = value;
						euInfo.setReAndPuStatusContent(reAndPuStatusContent);
					}else if("本科毕业年月".equals(colnames.get(j))){
						underGraduateTime = value;
						euInfo.setUnderGraduateTime(underGraduateTime);
					}else if("本科毕业专业名称".equals(colnames.get(j))){
						underGraduateMajor = value;
						euInfo.setUnderGraduateMajor(underGraduateMajor);
					}else if("获得学士学位年月".equals(colnames.get(j))){
						underAwardDegreeTime = value;
						euInfo.setUnderAwardDegreeTime(underAwardDegreeTime);
					}else if("取得本科学历的学习形式".equals(colnames.get(j))){
						underStudyForm = value;
						euInfo.setUnderStudyForm(underStudyForm);
					}else if("硕士毕业单位名称".equals(colnames.get(j))){
						masterUnitName = value;
						euInfo.setMasterUnitName(masterUnitName);
					}else if("硕士毕业年月".equals(colnames.get(j))){
						masterGraduateTime = value;
						euInfo.setMasterGraduateTime(masterGraduateTime);
					}else if("硕士毕业证编号".equals(colnames.get(j))){
						masterDiplomaCode = value;
						euInfo.setMasterDiplomaCode(masterDiplomaCode);
					}else if("硕士毕业专业名称".equals(colnames.get(j))){
						masterGraduateMajor = value;
						euInfo.setMasterGraduateMajor(masterGraduateMajor);
					}else if("硕士学位证编号".equals(colnames.get(j))){
						masterDegreeCertCode = value;
						euInfo.setMasterDegreeCertCode(masterDegreeCertCode);
					}else if("获得硕士学位单位名称".equals(colnames.get(j))){
						masterAwardDegreeOrg = value;
						euInfo.setMasterAwardDegreeOrg(masterAwardDegreeOrg);
					}else if("获得硕士学位方式".equals(colnames.get(j))){
						masterStudyForm = value;
						euInfo.setMasterStudyForm(masterStudyForm);
					}else if("获得硕士学位年月".equals(colnames.get(j))){
						masterAwardDegreeTime = value;
						euInfo.setMasterAwardDegreeTime(masterAwardDegreeTime);
					}else if("是否住宿".equals(colnames.get(j))){
						isStay = value;
						if("是".equals(isStay)){
							euInfo.setIsStay("Y");
						}else if("否".equals(isStay)){
							euInfo.setIsStay("N");
						}

					}
				}
				if(StringUtil.isBlank(sysUser.getUserCode())){
					continue;
				}
				SysUser currUser=GlobalContext.getCurrentUser();
				sysUser.setOrgFlow(currUser.getOrgFlow());
				sysUser.setOrgName(currUser.getOrgName());
				//验证用户登录名
				if(StringUtil.isNotBlank(eduUser.getSid())){
					List<EduUser> eduUserList = search(eduUser);
					if(eduUserList != null && !eduUserList.isEmpty()){
						EduUser exitUser = eduUserList.get(0);
						sysUser.setUserFlow(exitUser.getUserFlow());
						eduUser.setUserFlow(exitUser.getUserFlow());
						resDoctor.setDoctorFlow(exitUser.getUserFlow());
					}
				}
				save(eduUserForm);
				count ++;
			}
		}

		return count;
	}

	private Map<String,SysOrg> getOrgByNameMap() {
		Map<String,SysOrg> orgMap=new HashMap<String, SysOrg>();
		List<SysOrg> orgList=this.orgBiz.queryAllSysOrg(null);
		if(orgList!=null && !orgList.isEmpty()){
			for(SysOrg org:orgList){
				orgMap.put(org.getOrgName(), org);
			}
		}
		return orgMap;
	}

	private Map<String,SysOrg> getOrgByCodeMap(){
		Map<String,SysOrg> orgMap=new HashMap<String, SysOrg>();
		List<SysOrg> orgList=this.orgBiz.queryAllSysOrg(null);
		if(orgList!=null && !orgList.isEmpty()){
			for(SysOrg org:orgList){
				orgMap.put(org.getOrgCode(), org);
			}
		}
		return orgMap;
	}
	
	private String trainSex(String sexId) {
		if("1".equals(sexId)){
			return UserSexEnum.Man.getId();
		}else if("2".equals(sexId)){
			return UserSexEnum.Woman.getId();
		}
		return null;
	}
	
	/**
	 * 根据部门名称获取部门flow
	 * @param deptName
	 * @return
	 */
	public String getDeptFlow(String deptName,String orgFlow){
		SysDept sysDept=new SysDept();
		sysDept.setDeptName(deptName);
		sysDept.setOrgFlow(orgFlow);
		List<SysDept> deptList=this.deptBiz.searchDept(sysDept);
		if(deptList!=null && deptList.size()==1){
			return deptList.get(0).getDeptFlow();
		}
		return "";
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

	//	@Override
//	public List<EduUser> searchEduUserByUserFlowList(List<String> userFlowList) {
//		List<EduUser> eduUserList = null;
//		if(userFlowList != null && !userFlowList.isEmpty()){
//			EduUserExample example = new EduUserExample();
//			Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//			if(userFlowList != null && !userFlowList.isEmpty()){
//				criteria.andUserFlowIn(userFlowList);
//			}
//			eduUserList = eduUserMapper.selectByExample(example);
//		}
//		return eduUserList;
//	}
	@Override
	public EduUser findBySid(String sid) {
		EduUserExample example = new EduUserExample();
		Criteria criteria=example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andSidEqualTo(sid);
		List<EduUser> eduUserList = eduUserMapper.selectByExample(example);
		if(eduUserList.size()>0){
			return eduUserList.get(0);
		}		
		return null;
	}
	@Override
	public EduUser findBySidNotSelf(String userFlow,String sid) {
		EduUserExample example = new EduUserExample();
		Criteria criteria=example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andSidEqualTo(sid);
		criteria.andUserFlowNotEqualTo(userFlow);
		List<EduUser> eduUserList = eduUserMapper.selectByExample(example);
		if(eduUserList.size()>0){
			return eduUserList.get(0);
		}		
		return null;
	}
//	@Override
//	public List<EduUserExt> searchStudentCourseMaintainList(Map<String, Object> paramMap) {
//		return eduUserExtMapper.searchStudentCourseMaintainList(paramMap);
//	}

//	@Override
//	public List<EduUserExt> selectEduUserStudentCourse(SysUser sysUser,EduUser eduUser,EduStudentCourse studentCourse) {
//		Map<String, Object> map=new HashMap<String, Object>();
//		if (sysUser!=null) {
//			if (StringUtil.isNotBlank(sysUser.getIdNo()) || StringUtil.isNotBlank(sysUser.getUserName())) {
//				map.put("sysUser", sysUser);
//			}
//		}
//		if (eduUser!=null) {
//			if (StringUtil.isNotBlank(eduUser.getSid())||StringUtil.isNotBlank(eduUser.getPeriod())) {
//				map.put("eduUser", eduUser);
//			}
//		}
//		if (studentCourse!=null) {
//				map.put("studentCourse", studentCourse);
//		}
//		List<EduUserExt> studentCourseList=eduUserExtMapper.selectEduUserStudentCourse(map);
//		return studentCourseList;
//	}

	@Override
	public List<EduUserExt> searchEduUserCourseExtMajorList(List<String> userFlowList,EduStudentCourse sc) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userFlowList", userFlowList);
		if(sc!=null){
			map.put("studentCourse", sc);
		}
		return eduUserExtMapper.searchEduUserCourseExtMajorList(map);
	}
	@Override
	public List<EduCourseMajorExt> searchEduUserCourseList(String majorId,String period) {
		Map<String, Object> map=new HashMap<String, Object>();
		if (StringUtil.isNotBlank(majorId)) {
			map.put("majorId", majorId);
		}
		if (StringUtil.isNotBlank(period)) {
			map.put("period", period);
		}
		List<EduCourseMajorExt> list=eduUserExtMapper.searchEduUserCourseList(map);
		return list;
	}
	@Override
	public List<EduUserExt> searchEduUserExtList(SysUser sysUser, EduUser eduUser,ResDoctor doctor) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String roleFlow = InitConfig.getSysCfg("res_doctor_role_flow");//获取学员角色流水号
		/*if(StringUtil.isBlank(eduUser.getPeriod())){
			String period = InitConfig.getSysCfg("xjgl_curr_year");//当前届数
			eduUser.setPeriod(period);
		}*/
		paramMap.put("roleFlow", roleFlow);
		paramMap.put("sysUser", sysUser);
		paramMap.put("eduUser", eduUser);
		paramMap.put("resDoctor", doctor);
		
		return eduUserExtMapper.searchEduUserExtList(paramMap);
	}


	@Override
	public String convertExtFormToXml(EduUserExtInfoForm form, String userFlow) throws Exception {
		Document dom = null;
		Element root = null;
		Element heightElement = null;
		Element bloodTypeElement = null;
		Element foreignLanguageLevelElement = null;
		Element computerLevelElement = null;
		Element mandarinLevelElement = null;
		Element specialityElement = null;
		Element firstEducationContentIdElement = null;
		Element firstEducationContentNameElement = null;
		Element underGraduateOrgNameElement = null;
		Element underDiplomaCodeElement = null;
		Element underAwardDegreeOrgElement = null;
		Element underMajorElement = null;
		Element underDegreeCertCodeElement = null;
		Element underStudyFormElement = null;
		Element underGraduateTimeElement = null;
		Element underAwardDegreeTimeElement = null;
		Element underGraduateMajorElement = null;
		Element masterUnitNameElement = null;
		Element masterDiplomaCodeElement = null;
		Element masterAwardDegreeOrgElement = null;
		Element masterDegreeCertCodeElement = null;
		Element masterStudyFormElement = null;
		Element masterGraduateTimeElement = null;
		Element masterAwardDegreeTimeElement = null;
		Element masterGraduateMajorElement = null;
		Element masterGraduateMajorCodeElement = null;
		Element codeElement = null;
		Element reAndPuStatusContentElement = null;
		Element addressElement = null;
		Element oldDomicileElement = null;
		Element nowResideAddressElement = null;
		Element linkManElement = null;
		Element postCodeElement = null;
		Element telephoneElement = null;
		Element oldFileLocationOrgElement = null;
		Element oldFileLocationOrgCodeElement = null;
		Element oldOrgNameElement = null;
		Element statusIdElement = null;
		Element statusNameElement = null;
		Element bearStatusNameElement = null;
		Element joinTimeElement = null;
		Element isRelationIntoElement = null;
		Element accountNumElement = null;
		Element foreignLanguageNameElement = null;
		Element foreignLanguageScoreElement = null;
		Element politicalTheoryNameElement = null;
		Element politicalTheoryScoreElement = null;
		Element firstSubjectCodeElement = null;
		Element firstSubjectNameElement = null;
		Element firstSubjectScoreElement = null;
		Element secondSubjectCodeElement = null;
		Element secondSubjectNameElement = null;
		Element secondSubjectScoreElement = null;
		Element firstAddExamNameElement = null;
		Element firstAddExamScoreElement = null;
		Element secondAddExamNameElement = null;
		Element secondAddExamScoreElement = null;
		Element reexamineScoreElement = null;
		Element totalPointsScoreElement = null;
		Element isDoctorQuaCertElement = null;
		Element codeDoctorQuaCertElement = null;
		Element isMedicalPractitionerElement = null;
		Element codeMedicalPractitionerElement = null;
		Element isStayElement = null;
		Element roomNumStayElement = null;
		Element telephoneStayElement = null;
		Element mateNameElement = null;
		Element mateIdNoElement = null;
		Element directionalOrgNameElement = null;
		Element remarkElement = null;
		Element outOfSchoolDateElement = null;
		Element backToSchoolDateElement = null;
		Element dropOutSchoolDateElement = null;
		Element awardSubjectCategoryElement = null;
		if(StringUtil.isNotBlank(userFlow)){
			EduUser eduUser = readEduUser(userFlow);
			String content = eduUser.getContent();
			if(StringUtil.isNotBlank(content)){
				dom = DocumentHelper.parseText(content);
				root = dom.getRootElement();
				String heightXpath = "//height";
				heightElement = (Element)dom.selectSingleNode(heightXpath);
				String bloodTypeXpath = "//bloodType";
				bloodTypeElement = (Element)dom.selectSingleNode(bloodTypeXpath);
				String foreignLanguageLevelXpath = "//foreignLanguageLevel";
				foreignLanguageLevelElement = (Element)dom.selectSingleNode(foreignLanguageLevelXpath);
				String computerLevelXpath = "//computerLevel";
				computerLevelElement = (Element)dom.selectSingleNode(computerLevelXpath);
				String mandarinLevelXpath = "//mandarinLevel";
				mandarinLevelElement = (Element)dom.selectSingleNode(mandarinLevelXpath);
				String specialityXpath = "//speciality";
				specialityElement = (Element)dom.selectSingleNode(specialityXpath);
				String firstEducationContentIdXpath = "//firstEducationContentId";
				firstEducationContentIdElement = (Element)dom.selectSingleNode(firstEducationContentIdXpath);
				String firstEducationContentNameXpath = "//firstEducationContentName";
				firstEducationContentNameElement = (Element)dom.selectSingleNode(firstEducationContentNameXpath);
				String underGraduateOrgNameXpath = "//underGraduateOrgName";
				underGraduateOrgNameElement = (Element)dom.selectSingleNode(underGraduateOrgNameXpath);
				String underDiplomaCodeXpath = "//underDiplomaCode";
				underDiplomaCodeElement = (Element)dom.selectSingleNode(underDiplomaCodeXpath);
				String underAwardDegreeOrgXpath = "//underAwardDegreeOrg";
				underAwardDegreeOrgElement = (Element)dom.selectSingleNode(underAwardDegreeOrgXpath);
				String underMajorXpath = "//underMajor";
				underMajorElement = (Element)dom.selectSingleNode(underMajorXpath);
				String underDegreeCertCodeXpath = "//underDegreeCertCode";
				underDegreeCertCodeElement = (Element)dom.selectSingleNode(underDegreeCertCodeXpath);
				String underStudyFormXpath = "//underStudyForm";
				underStudyFormElement = (Element)dom.selectSingleNode(underStudyFormXpath);
				String underGraduateTimeXpath = "//underGraduateTime";
				underGraduateTimeElement = (Element)dom.selectSingleNode(underGraduateTimeXpath);
				String underAwardDegreeTimeXpath = "//underAwardDegreeTime";
				underAwardDegreeTimeElement = (Element)dom.selectSingleNode(underAwardDegreeTimeXpath);
				String underGraduateMajorXpath = "//underGraduateMajor";
				underGraduateMajorElement = (Element)dom.selectSingleNode(underGraduateMajorXpath);
				String masterUnitNameXpath = "//masterUnitName";
				masterUnitNameElement = (Element)dom.selectSingleNode(masterUnitNameXpath);
				String masterDiplomaCodeXpath = "//masterDiplomaCode";
				masterDiplomaCodeElement = (Element)dom.selectSingleNode(masterDiplomaCodeXpath);
				String masterAwardDegreeOrgXpath = "//masterAwardDegreeOrg";
				masterAwardDegreeOrgElement = (Element)dom.selectSingleNode(masterAwardDegreeOrgXpath);
				String masterDegreeCertCodeXpath = "//masterDegreeCertCode";
				masterDegreeCertCodeElement = (Element)dom.selectSingleNode(masterDegreeCertCodeXpath);
				String masterStudyFormXpath = "//masterStudyForm";
				masterStudyFormElement = (Element)dom.selectSingleNode(masterStudyFormXpath);
				String masterGraduateTimeXpath = "//masterGraduateTime";
				masterGraduateTimeElement = (Element)dom.selectSingleNode(masterGraduateTimeXpath);
				String masterAwardDegreeTimeXpath = "//masterAwardDegreeTime";
				masterAwardDegreeTimeElement = (Element)dom.selectSingleNode(masterAwardDegreeTimeXpath);
				String masterGraduateMajorXpath = "//masterGraduateMajor";
				masterGraduateMajorElement = (Element)dom.selectSingleNode(masterGraduateMajorXpath);
				String masterGraduateMajorCodeXpath = "//masterGraduateMajorCode";
				masterGraduateMajorCodeElement = (Element)dom.selectSingleNode(masterGraduateMajorCodeXpath);
				String codeXpath = "//code";
				codeElement = (Element)dom.selectSingleNode(codeXpath);
				String reAndPuStatusContentXpath = "//reAndPuStatusContent";
				reAndPuStatusContentElement = (Element)dom.selectSingleNode(reAndPuStatusContentXpath);
				String addressXpath = "//address";
				addressElement = (Element)dom.selectSingleNode(addressXpath);
				String oldDomicileXpath = "//oldDomicile";
				oldDomicileElement = (Element)dom.selectSingleNode(oldDomicileXpath);
				String nowResideAddressXpath = "//nowResideAddress";
				nowResideAddressElement = (Element)dom.selectSingleNode(nowResideAddressXpath);
				String linkManXpath = "//linkMan";
				linkManElement = (Element)dom.selectSingleNode(linkManXpath);
				String postCodeXpath = "//postCode";
				postCodeElement = (Element)dom.selectSingleNode(postCodeXpath);
				String telephoneXpath = "//telephone";
				telephoneElement = (Element)dom.selectSingleNode(telephoneXpath);
				String oldFileLocationOrgXpath = "//oldFileLocationOrg";
				oldFileLocationOrgElement = (Element)dom.selectSingleNode(oldFileLocationOrgXpath);
				String oldFileLocationOrgCodeXpath = "//oldFileLocationOrgCode";
				oldFileLocationOrgCodeElement = (Element)dom.selectSingleNode(oldFileLocationOrgCodeXpath);
				String oldOrgNameXpath = "//oldOrgName";
				oldOrgNameElement = (Element)dom.selectSingleNode(oldOrgNameXpath);
				String statusIdXpath = "//statusId";
				statusIdElement = (Element)dom.selectSingleNode(statusIdXpath);
				String statusNameXpath = "//statusName";
				statusNameElement = (Element)dom.selectSingleNode(statusNameXpath);
				String bearStatusNameXpath = "//bearStatusName";
				bearStatusNameElement = (Element)dom.selectSingleNode(bearStatusNameXpath);
				String joinTimeXpath = "//joinTime";
				joinTimeElement = (Element)dom.selectSingleNode(joinTimeXpath);
				String isRelationIntoXpath = "//isRelationInto";
				isRelationIntoElement = (Element)dom.selectSingleNode(isRelationIntoXpath);
				String accountNumXpath = "//accountNum";
				accountNumElement = (Element)dom.selectSingleNode(accountNumXpath);
				String foreignLanguageNameXpath = "//foreignLanguageName";
				foreignLanguageNameElement = (Element)dom.selectSingleNode(foreignLanguageNameXpath);
				String foreignLanguageScoreXpath = "//foreignLanguageScore";
				foreignLanguageScoreElement = (Element)dom.selectSingleNode(foreignLanguageScoreXpath);
				String politicalTheoryNameXpath = "//politicalTheoryName";
				politicalTheoryNameElement = (Element)dom.selectSingleNode(politicalTheoryNameXpath);
				String politicalTheoryScoreXpath = "//politicalTheoryScore";
				politicalTheoryScoreElement = (Element)dom.selectSingleNode(politicalTheoryScoreXpath);
				String firstSubjectCodeXpath = "//firstSubjectCode";
				firstSubjectCodeElement = (Element)dom.selectSingleNode(firstSubjectCodeXpath);
				String firstSubjectNameXpath = "//firstSubjectName";
				firstSubjectNameElement = (Element)dom.selectSingleNode(firstSubjectNameXpath);
				String firstSubjectScoreXpath = "//firstSubjectScore";
				firstSubjectScoreElement = (Element)dom.selectSingleNode(firstSubjectScoreXpath);
				String secondSubjectCodeXpath = "//secondSubjectCode";
				secondSubjectCodeElement = (Element)dom.selectSingleNode(secondSubjectCodeXpath);
				String secondSubjectNameXpath = "//secondSubjectName";
				secondSubjectNameElement = (Element)dom.selectSingleNode(secondSubjectNameXpath);
				String secondSubjectScoreXpath = "//secondSubjectScore";
				secondSubjectScoreElement = (Element)dom.selectSingleNode(secondSubjectScoreXpath);
				String firstAddExamNameXpath = "//firstAddExamName";
				firstAddExamNameElement = (Element)dom.selectSingleNode(firstAddExamNameXpath);
				String firstAddExamScoreXpath = "//firstAddExamScore";
				firstAddExamScoreElement = (Element)dom.selectSingleNode(firstAddExamScoreXpath);
				String secondAddExamNameXpath = "//secondAddExamName";
				secondAddExamNameElement = (Element)dom.selectSingleNode(secondAddExamNameXpath);
				String secondAddExamScoreXpath = "//secondAddExamScore";
				secondAddExamScoreElement = (Element)dom.selectSingleNode(secondAddExamScoreXpath);
				String reexamineScoreXpath = "//reexamineScore";
				reexamineScoreElement = (Element)dom.selectSingleNode(reexamineScoreXpath);
				String totalPointsScoreXpath = "//totalPointsScore";
				totalPointsScoreElement = (Element)dom.selectSingleNode(totalPointsScoreXpath);
				String isDoctorQuaCertXpath = "//isDoctorQuaCert";
				isDoctorQuaCertElement = (Element)dom.selectSingleNode(isDoctorQuaCertXpath);
				String codeDoctorQuaCertXpath = "//codeDoctorQuaCert";
				codeDoctorQuaCertElement = (Element)dom.selectSingleNode(codeDoctorQuaCertXpath);
				String isMedicalPractitionerXpath = "//isMedicalPractitioner";
				isMedicalPractitionerElement = (Element)dom.selectSingleNode(isMedicalPractitionerXpath);
				String codeMedicalPractitionerXpath = "//codeMedicalPractitioner";
				codeMedicalPractitionerElement = (Element)dom.selectSingleNode(codeMedicalPractitionerXpath);
				String isStayXpath = "//isStay";
				isStayElement = (Element)dom.selectSingleNode(isStayXpath);
				String roomNumStayXpath = "//roomNumStay";
				roomNumStayElement = (Element)dom.selectSingleNode(roomNumStayXpath);
				String telephoneStayXpath = "//telephoneStay";
				telephoneStayElement = (Element)dom.selectSingleNode(telephoneStayXpath);
				String mateNameXpath = "//mateName";
				mateNameElement = (Element)dom.selectSingleNode(mateNameXpath);
				String mateIdNoXpath = "//mateIdNo";
				mateIdNoElement = (Element)dom.selectSingleNode(mateIdNoXpath);
				String directionalOrgNameXpath = "//directionalOrgName";
				directionalOrgNameElement = (Element)dom.selectSingleNode(directionalOrgNameXpath);
				String remarkXpath = "//remark";
				remarkElement = (Element)dom.selectSingleNode(remarkXpath);
				String outOfSchoolDateXpath = "//outOfSchoolDate";
				outOfSchoolDateElement = (Element)dom.selectSingleNode(outOfSchoolDateXpath);
				String backToSchoolDateXpath = "//backToSchoolDate";
				backToSchoolDateElement = (Element)dom.selectSingleNode(backToSchoolDateXpath);
				String dropOutSchoolDateXpath = "//dropOutSchoolDate";
				dropOutSchoolDateElement = (Element)dom.selectSingleNode(dropOutSchoolDateXpath);
				String awardSubjectCategoryXpath = "//awardSubjectCategory";
				awardSubjectCategoryElement = (Element)dom.selectSingleNode(awardSubjectCategoryXpath);
			}
		}else{
			dom = DocumentHelper.createDocument();
		}
		if(root == null){
			root = dom.addElement("eduUserExtInfoForm");
		}
		
		if(heightElement == null){
			heightElement = root.addElement("height");
		}
		heightElement.setText(StringUtil.defaultString(form.getHeight()));
		if(bloodTypeElement == null){
			bloodTypeElement = root.addElement("bloodType");
		}
		bloodTypeElement.setText(StringUtil.defaultString(form.getBloodType()));
		if(foreignLanguageLevelElement == null){
			foreignLanguageLevelElement = root.addElement("foreignLanguageLevel");
		}
		foreignLanguageLevelElement.setText(StringUtil.defaultString(form.getForeignLanguageLevel()));
		if(computerLevelElement == null){
			computerLevelElement = root.addElement("computerLevel");
		}
		computerLevelElement.setText(StringUtil.defaultString(form.getComputerLevel()));
		if(mandarinLevelElement == null){
			mandarinLevelElement = root.addElement("mandarinLevel");
		}
		mandarinLevelElement.setText(StringUtil.defaultString(form.getMandarinLevel()));
		if(specialityElement == null){
			specialityElement = root.addElement("speciality");
		}
		specialityElement.setText(StringUtil.defaultString(form.getSpeciality()));
		if(firstEducationContentIdElement == null){
			firstEducationContentIdElement = root.addElement("firstEducationContentId");
		}
		firstEducationContentIdElement.setText(StringUtil.defaultString(form.getFirstEducationContentId()));
		if(firstEducationContentNameElement == null){
			firstEducationContentNameElement = root.addElement("firstEducationContentName");
		}
		firstEducationContentNameElement.setText(StringUtil.defaultString(form.getFirstEducationContentName()));
		if(underGraduateOrgNameElement == null){
			underGraduateOrgNameElement = root.addElement("underGraduateOrgName");
		}
		underGraduateOrgNameElement.setText(StringUtil.defaultString(form.getUnderGraduateOrgName()));
		if(underDiplomaCodeElement == null){
			underDiplomaCodeElement = root.addElement("underDiplomaCode");
		}
		underDiplomaCodeElement.setText(StringUtil.defaultString(form.getUnderDiplomaCode()));
		if(underAwardDegreeOrgElement == null){
			underAwardDegreeOrgElement = root.addElement("underAwardDegreeOrg");
		}
		underAwardDegreeOrgElement.setText(StringUtil.defaultString(form.getUnderAwardDegreeOrg()));
		if(underMajorElement == null){
			underMajorElement = root.addElement("underMajor");
		}
		underMajorElement.setText(StringUtil.defaultString(form.getUnderMajor()));
		if(underDegreeCertCodeElement == null){
			underDegreeCertCodeElement = root.addElement("underDegreeCertCode");
		}
		underDegreeCertCodeElement.setText(StringUtil.defaultString(form.getUnderDegreeCertCode()));
		if(underStudyFormElement == null){
			underStudyFormElement = root.addElement("underStudyForm");
		}
		underStudyFormElement.setText(StringUtil.defaultString(form.getUnderStudyForm()));
		if(underGraduateTimeElement == null){
			underGraduateTimeElement = root.addElement("underGraduateTime");
		}
		underGraduateTimeElement.setText(StringUtil.defaultString(form.getUnderGraduateTime()));
		if(underAwardDegreeTimeElement == null){
			underAwardDegreeTimeElement = root.addElement("underAwardDegreeTime");
		}
		underAwardDegreeTimeElement.setText(StringUtil.defaultString(form.getUnderAwardDegreeTime()));
		if(underGraduateMajorElement == null){
			underGraduateMajorElement = root.addElement("underGraduateMajor");
		}
		underGraduateMajorElement.setText(StringUtil.defaultString(form.getUnderGraduateMajor()));
		if(masterUnitNameElement == null){
			masterUnitNameElement = root.addElement("masterUnitName");
		}
		masterUnitNameElement.setText(StringUtil.defaultString(form.getMasterUnitName()));
		if(masterDiplomaCodeElement == null){
			masterDiplomaCodeElement = root.addElement("masterDiplomaCode");
		}
		masterDiplomaCodeElement.setText(StringUtil.defaultString(form.getMasterDiplomaCode()));
		if(masterAwardDegreeOrgElement == null){
			masterAwardDegreeOrgElement = root.addElement("masterAwardDegreeOrg");
		}
		masterAwardDegreeOrgElement.setText(StringUtil.defaultString(form.getMasterAwardDegreeOrg()));
		if(masterDegreeCertCodeElement == null){
			masterDegreeCertCodeElement = root.addElement("masterDegreeCertCode");
		}
		masterDegreeCertCodeElement.setText(StringUtil.defaultString(form.getMasterDegreeCertCode()));
		if(masterStudyFormElement == null){
			masterStudyFormElement = root.addElement("masterStudyForm");
		}
		masterStudyFormElement.setText(StringUtil.defaultString(form.getMasterStudyForm()));
		if(masterGraduateTimeElement == null){
			masterGraduateTimeElement = root.addElement("masterGraduateTime");
		}
		masterGraduateTimeElement.setText(StringUtil.defaultString(form.getMasterGraduateTime()));
		if(masterAwardDegreeTimeElement == null){
			masterAwardDegreeTimeElement = root.addElement("masterAwardDegreeTime");
		}
		masterAwardDegreeTimeElement.setText(StringUtil.defaultString(form.getMasterAwardDegreeTime()));
		if(masterGraduateMajorElement == null){
			masterGraduateMajorElement = root.addElement("masterGraduateMajor");
		}
		masterGraduateMajorElement.setText(StringUtil.defaultString(form.getMasterGraduateMajor()));
		if(masterGraduateMajorCodeElement == null){
			masterGraduateMajorCodeElement = root.addElement("masterGraduateMajorCode");
		}
		masterGraduateMajorCodeElement.setText(StringUtil.defaultString(form.getMasterGraduateMajorCode()));
		if(codeElement == null){
			codeElement = root.addElement("code");
		}
		codeElement.setText(StringUtil.defaultString(form.getCode()));
		if(reAndPuStatusContentElement == null){
			reAndPuStatusContentElement = root.addElement("reAndPuStatusContent");
		}
		reAndPuStatusContentElement.setText(StringUtil.defaultString(form.getReAndPuStatusContent()));
		if(addressElement == null){
			addressElement = root.addElement("address");
		}
		addressElement.setText(StringUtil.defaultString(form.getAddress()));
		if(oldDomicileElement == null){
			oldDomicileElement = root.addElement("oldDomicile");
		}
		oldDomicileElement.setText(StringUtil.defaultString(form.getOldDomicile()));
		if(nowResideAddressElement == null){
			nowResideAddressElement = root.addElement("nowResideAddress");
		}
		nowResideAddressElement.setText(StringUtil.defaultString(form.getNowResideAddress()));
		if(linkManElement == null){
			linkManElement = root.addElement("linkMan");
		}
		linkManElement.setText(StringUtil.defaultString(form.getLinkMan()));
		if(postCodeElement == null){
			postCodeElement = root.addElement("postCode");
		}
		postCodeElement.setText(StringUtil.defaultString(form.getPostCode()));
		if(telephoneElement == null){
			telephoneElement = root.addElement("telephone");
		}
		telephoneElement.setText(StringUtil.defaultString(form.getTelephone()));
		if(oldFileLocationOrgElement == null){
			oldFileLocationOrgElement = root.addElement("oldFileLocationOrg");
		}
		oldFileLocationOrgElement.setText(StringUtil.defaultString(form.getOldFileLocationOrg()));
		if(oldFileLocationOrgCodeElement == null){
			oldFileLocationOrgCodeElement = root.addElement("oldFileLocationOrgCode");
		}
		oldFileLocationOrgCodeElement.setText(StringUtil.defaultString(form.getOldFileLocationOrgCode()));
		if(oldOrgNameElement == null){
			oldOrgNameElement = root.addElement("oldOrgName");
		}
		oldOrgNameElement.setText(StringUtil.defaultString(form.getOldOrgName()));
		if(statusIdElement == null){
			statusIdElement = root.addElement("statusId");
		}
		statusIdElement.setText(StringUtil.defaultString(form.getStatusId()));
		if(statusNameElement == null){
			statusNameElement = root.addElement("statusName");
		}
		statusNameElement.setText(StringUtil.defaultString(form.getStatusName()));
		if(bearStatusNameElement == null){
			bearStatusNameElement = root.addElement("bearStatusName");
		}
		bearStatusNameElement.setText(StringUtil.defaultString(form.getBearStatusName()));
		if(joinTimeElement == null){
			joinTimeElement = root.addElement("joinTime");
		}
		joinTimeElement.setText(StringUtil.defaultString(form.getJoinTime()));
		if(isRelationIntoElement == null){
			isRelationIntoElement = root.addElement("isRelationInto");
		}
		isRelationIntoElement.setText(StringUtil.defaultString(form.getIsRelationInto()));
		if(accountNumElement == null){
			accountNumElement = root.addElement("accountNum");
		}
		accountNumElement.setText(StringUtil.defaultString(form.getAccountNum()));
		if(foreignLanguageNameElement == null){
			foreignLanguageNameElement = root.addElement("foreignLanguageName");
		}
		foreignLanguageNameElement.setText(StringUtil.defaultString(form.getForeignLanguageName()));
		if(foreignLanguageScoreElement == null){
			foreignLanguageScoreElement = root.addElement("foreignLanguageScore");
		}
		foreignLanguageScoreElement.setText(StringUtil.defaultString(form.getForeignLanguageScore()));
		if(politicalTheoryNameElement == null){
			politicalTheoryNameElement = root.addElement("politicalTheoryName");
		}
		politicalTheoryNameElement.setText(StringUtil.defaultString(form.getPoliticalTheoryName()));
		if(politicalTheoryScoreElement == null){
			politicalTheoryScoreElement = root.addElement("politicalTheoryScore");
		}
		politicalTheoryScoreElement.setText(StringUtil.defaultString(form.getPoliticalTheoryScore()));
		if(firstSubjectCodeElement == null){
			firstSubjectCodeElement = root.addElement("firstSubjectCode");
		}
		firstSubjectCodeElement.setText(StringUtil.defaultString(form.getFirstSubjectCode()));
		if(firstSubjectNameElement == null){
			firstSubjectNameElement = root.addElement("firstSubjectName");
		}
		firstSubjectNameElement.setText(StringUtil.defaultString(form.getFirstSubjectName()));
		if(firstSubjectScoreElement == null){
			firstSubjectScoreElement = root.addElement("firstSubjectScore");
		}
		firstSubjectScoreElement.setText(StringUtil.defaultString(form.getFirstSubjectScore()));
		if(secondSubjectCodeElement == null){
			secondSubjectCodeElement = root.addElement("secondSubjectCode");
		}
		secondSubjectCodeElement.setText(StringUtil.defaultString(form.getSecondSubjectCode()));
		if(secondSubjectNameElement == null){
			secondSubjectNameElement = root.addElement("secondSubjectName");
		}
		secondSubjectNameElement.setText(StringUtil.defaultString(form.getSecondSubjectName()));
		if(secondSubjectScoreElement == null){
			secondSubjectScoreElement = root.addElement("secondSubjectScore");
		}
		secondSubjectScoreElement.setText(StringUtil.defaultString(form.getSecondSubjectScore()));
		if(firstAddExamNameElement == null){
			firstAddExamNameElement = root.addElement("firstAddExamName");
		}
		firstAddExamNameElement.setText(StringUtil.defaultString(form.getFirstAddExamName()));
		if(firstAddExamScoreElement == null){
			firstAddExamScoreElement = root.addElement("firstAddExamScore");
		}
		firstAddExamScoreElement.setText(StringUtil.defaultString(form.getFirstAddExamScore()));
		if(secondAddExamNameElement == null){
			secondAddExamNameElement = root.addElement("secondAddExamName");
		}
		secondAddExamNameElement.setText(StringUtil.defaultString(form.getSecondAddExamName()));
		if(secondAddExamScoreElement == null){
			secondAddExamScoreElement = root.addElement("secondAddExamScore");
		}
		secondAddExamScoreElement.setText(StringUtil.defaultString(form.getSecondAddExamScore()));
		if(reexamineScoreElement == null){
			reexamineScoreElement = root.addElement("reexamineScore");
		}
		reexamineScoreElement.setText(StringUtil.defaultString(form.getReexamineScore()));
		if(totalPointsScoreElement == null){
			totalPointsScoreElement = root.addElement("totalPointsScore");
		}
		totalPointsScoreElement.setText(StringUtil.defaultString(form.getTotalPointsScore()));
		if(isDoctorQuaCertElement == null){
			isDoctorQuaCertElement = root.addElement("isDoctorQuaCert");
		}
		isDoctorQuaCertElement.setText(StringUtil.defaultString(form.getIsDoctorQuaCert()));
		if(codeDoctorQuaCertElement == null){
			codeDoctorQuaCertElement = root.addElement("codeDoctorQuaCert");
		}
		codeDoctorQuaCertElement.setText(StringUtil.defaultString(form.getCodeDoctorQuaCert()));
		if(isMedicalPractitionerElement == null){
			isMedicalPractitionerElement = root.addElement("isMedicalPractitioner");
		}
		isMedicalPractitionerElement.setText(StringUtil.defaultString(form.getIsMedicalPractitioner()));
		if(codeMedicalPractitionerElement == null){
			codeMedicalPractitionerElement = root.addElement("codeMedicalPractitioner");
		}
		codeMedicalPractitionerElement.setText(StringUtil.defaultString(form.getCodeMedicalPractitioner()));
		if(isStayElement == null){
			isStayElement = root.addElement("isStay");
		}
		isStayElement.setText(StringUtil.defaultString(form.getIsStay()));
		if(roomNumStayElement == null){
			roomNumStayElement = root.addElement("roomNumStay");
		}
		roomNumStayElement.setText(StringUtil.defaultString(form.getRoomNumStay()));
		if(telephoneStayElement == null){
			telephoneStayElement = root.addElement("telephoneStay");
		}
		telephoneStayElement.setText(StringUtil.defaultString(form.getTelephoneStay()));
		if(mateNameElement == null){
			mateNameElement = root.addElement("mateName");
		}
		mateNameElement.setText(StringUtil.defaultString(form.getMateName()));
		if(mateIdNoElement == null){
			mateIdNoElement = root.addElement("mateIdNo");
		}
		mateIdNoElement.setText(StringUtil.defaultString(form.getMateIdNo()));
		if(directionalOrgNameElement == null){
			directionalOrgNameElement = root.addElement("directionalOrgName");
		}
		directionalOrgNameElement.setText(StringUtil.defaultString(form.getDirectionalOrgName()));
		if(remarkElement == null){
			remarkElement = root.addElement("remark");
		}
		remarkElement.setText(StringUtil.defaultString(form.getRemark()));
		if(outOfSchoolDateElement == null){
			outOfSchoolDateElement = root.addElement("outOfSchoolDate");
		}
		outOfSchoolDateElement.setText(StringUtil.defaultString(form.getOutOfSchoolDate()));
		if(backToSchoolDateElement == null){
			backToSchoolDateElement = root.addElement("backToSchoolDate");
		}
		backToSchoolDateElement.setText(StringUtil.defaultString(form.getBackToSchoolDate()));
		if(dropOutSchoolDateElement == null){
			dropOutSchoolDateElement = root.addElement("dropOutSchoolDate");
		}
		dropOutSchoolDateElement.setText(StringUtil.defaultString(form.getDropOutSchoolDate()));
		if(awardSubjectCategoryElement == null){
			awardSubjectCategoryElement = root.addElement("awardSubjectCategory");
		}
		awardSubjectCategoryElement.setText(StringUtil.defaultString(form.getAwardSubjectCategory()));
		String xml = dom.asXML();
		return xml;
	}
	
	@Override
	public EduUserExtInfoForm convertXmlToExtForm(String content) throws Exception {
		EduUserExtInfoForm form = new EduUserExtInfoForm();
		if(StringUtil.isNotBlank(content)){
			Document dom = DocumentHelper.parseText(content);
			//Element root = dom.getRootElement();
			String heightXpath = "//height";
			Node heightNode = dom.selectSingleNode(heightXpath);
			if(heightNode != null){
				form.setHeight(heightNode.getText());
			}
			String bloodTypeXpath = "//bloodType";
			Node bloodTypeNode = dom.selectSingleNode(bloodTypeXpath);
			if(bloodTypeNode != null){
				form.setBloodType(bloodTypeNode.getText());
			}
			String foreignLanguageLevelXpath = "//foreignLanguageLevel";
			Node foreignLanguageLevelNode = dom.selectSingleNode(foreignLanguageLevelXpath);
			if(foreignLanguageLevelNode != null){
				form.setForeignLanguageLevel(foreignLanguageLevelNode.getText());
			}
			String computerLevelXpath = "//computerLevel";
			Node computerLevelNode = dom.selectSingleNode(computerLevelXpath);
			if(computerLevelNode != null){
				form.setComputerLevel(computerLevelNode.getText());
			}
			String mandarinLevelXpath = "//mandarinLevel";
			Node mandarinLevelNode = dom.selectSingleNode(mandarinLevelXpath);
			if(mandarinLevelNode != null){
				form.setMandarinLevel(mandarinLevelNode.getText());
			}
			String specialityXpath = "//speciality";
			Node specialityNode = dom.selectSingleNode(specialityXpath);
			if(specialityNode != null){
				form.setSpeciality(specialityNode.getText());
			}
			String firstEducationContentIdXpath = "//firstEducationContentId";
			Node firstEducationContentIdNode = dom.selectSingleNode(firstEducationContentIdXpath);
			if(firstEducationContentIdNode != null){
				form.setFirstEducationContentId(firstEducationContentIdNode.getText());
			}
			String firstEducationContentNameXpath = "//firstEducationContentName";
			Node firstEducationContentNameNode = dom.selectSingleNode(firstEducationContentNameXpath);
			if(firstEducationContentNameNode != null){
				form.setFirstEducationContentName(firstEducationContentNameNode.getText());
			}
			String underGraduateOrgNameXpath = "//underGraduateOrgName";
			Node underGraduateOrgNameNode = dom.selectSingleNode(underGraduateOrgNameXpath);
			if(underGraduateOrgNameNode != null){
				form.setUnderGraduateOrgName(underGraduateOrgNameNode.getText());
			}
			String underDiplomaCodeXpath = "//underDiplomaCode";
			Node underDiplomaCodeNode = dom.selectSingleNode(underDiplomaCodeXpath);
			if(underDiplomaCodeNode != null){
				form.setUnderDiplomaCode(underDiplomaCodeNode.getText());
			}
			String underAwardDegreeOrgXpath = "//underAwardDegreeOrg";
			Node underAwardDegreeOrgNode = dom.selectSingleNode(underAwardDegreeOrgXpath);
			if(underAwardDegreeOrgNode != null){
				form.setUnderAwardDegreeOrg(underAwardDegreeOrgNode.getText());
			}
			String underMajorXpath = "//underMajor";
			Node underMajorNode = dom.selectSingleNode(underMajorXpath);
			if(underMajorNode != null){
				form.setUnderMajor(underMajorNode.getText());
			}
			String underDegreeCertCodeXpath = "//underDegreeCertCode";
			Node underDegreeCertCodeNode = dom.selectSingleNode(underDegreeCertCodeXpath);
			if(underDegreeCertCodeNode != null){
				form.setUnderDegreeCertCode(underDegreeCertCodeNode.getText());
			}
			String underStudyFormXpath = "//underStudyForm";
			Node underStudyFormNode = dom.selectSingleNode(underStudyFormXpath);
			if(underStudyFormNode != null){
				form.setUnderStudyForm(underStudyFormNode.getText());
			}
			String underGraduateTimeXpath = "//underGraduateTime";
			Node underGraduateTimeNode = dom.selectSingleNode(underGraduateTimeXpath);
			if(underGraduateTimeNode != null){
				form.setUnderGraduateTime(underGraduateTimeNode.getText());
			}
			String underAwardDegreeTimeXpath = "//underAwardDegreeTime";
			Node underAwardDegreeTimeNode = dom.selectSingleNode(underAwardDegreeTimeXpath);
			if(underAwardDegreeTimeNode != null){
				form.setUnderAwardDegreeTime(underAwardDegreeTimeNode.getText());
			}
			String underGraduateMajorXpath = "//underGraduateMajor";
			Node underGraduateMajorNode = dom.selectSingleNode(underGraduateMajorXpath);
			if(underGraduateMajorNode != null){
				form.setUnderGraduateMajor(underGraduateMajorNode.getText());
			}
			String masterUnitNameXpath = "//masterUnitName";
			Node masterUnitNameNode = dom.selectSingleNode(masterUnitNameXpath);
			if(masterUnitNameNode != null){
				form.setMasterUnitName(masterUnitNameNode.getText());
			}
			String masterDiplomaCodeXpath = "//masterDiplomaCode";
			Node masterDiplomaCodeNode = dom.selectSingleNode(masterDiplomaCodeXpath);
			if(masterDiplomaCodeNode != null){
				form.setMasterDiplomaCode(masterDiplomaCodeNode.getText());
			}
			String masterAwardDegreeOrgXpath = "//masterAwardDegreeOrg";
			Node masterAwardDegreeOrgNode = dom.selectSingleNode(masterAwardDegreeOrgXpath);
			if(masterAwardDegreeOrgNode != null){
				form.setMasterAwardDegreeOrg(masterAwardDegreeOrgNode.getText());
			}
			String masterDegreeCertCodeXpath = "//masterDegreeCertCode";
			Node masterDegreeCertCodeNode = dom.selectSingleNode(masterDegreeCertCodeXpath);
			if(masterDegreeCertCodeNode != null){
				form.setMasterDegreeCertCode(masterDegreeCertCodeNode.getText());
			}
			String masterStudyFormXpath = "//masterStudyForm";
			Node masterStudyFormNode = dom.selectSingleNode(masterStudyFormXpath);
			if(masterStudyFormNode != null){
				form.setMasterStudyForm(masterStudyFormNode.getText());
			}
			String masterGraduateTimeXpath = "//masterGraduateTime";
			Node masterGraduateTimeNode = dom.selectSingleNode(masterGraduateTimeXpath);
			if(masterGraduateTimeNode != null){
				form.setMasterGraduateTime(masterGraduateTimeNode.getText());
			}
			String masterAwardDegreeTimeXpath = "//masterAwardDegreeTime";
			Node masterAwardDegreeTimeNode = dom.selectSingleNode(masterAwardDegreeTimeXpath);
			if(masterAwardDegreeTimeNode != null){
				form.setMasterAwardDegreeTime(masterAwardDegreeTimeNode.getText());
			}
			String masterGraduateMajorXpath = "//masterGraduateMajor";
			Node masterGraduateMajorNode = dom.selectSingleNode(masterGraduateMajorXpath);
			if(masterGraduateMajorNode != null){
				form.setMasterGraduateMajor(masterGraduateMajorNode.getText());
			}
			String masterGraduateMajorCodeXpath = "//masterGraduateMajorCode";
			Node masterGraduateMajorCodeNode = dom.selectSingleNode(masterGraduateMajorCodeXpath);
			if(masterGraduateMajorCodeNode != null){
				form.setMasterGraduateMajorCode(masterGraduateMajorCodeNode.getText());
			}
			String codeXpath = "//code";
			Node codeNode = dom.selectSingleNode(codeXpath);
			if(codeNode != null){
				form.setCode(codeNode.getText());
			}
			String reAndPuStatusContentXpath = "//reAndPuStatusContent";
			Node reAndPuStatusContentNode = dom.selectSingleNode(reAndPuStatusContentXpath);
			if(reAndPuStatusContentNode != null){
				form.setReAndPuStatusContent(reAndPuStatusContentNode.getText());
			}
			String addressXpath = "//address";
			Node addressNode = dom.selectSingleNode(addressXpath);
			if(addressNode != null){
				form.setAddress(addressNode.getText());
			}
			String oldDomicileXpath = "//oldDomicile";
			Node oldDomicileNode = dom.selectSingleNode(oldDomicileXpath);
			if(oldDomicileNode != null){
				form.setOldDomicile(oldDomicileNode.getText());
			}
			String nowResideAddressXpath = "//nowResideAddress";
			Node nowResideAddressNode = dom.selectSingleNode(nowResideAddressXpath);
			if(nowResideAddressNode != null){
				form.setNowResideAddress(nowResideAddressNode.getText());
			}
			String linkManXpath = "//linkMan";
			Node linkManNode = dom.selectSingleNode(linkManXpath);
			if(linkManNode != null){
				form.setLinkMan(linkManNode.getText());
			}
			String postCodeXpath = "//postCode";
			Node postCodeNode = dom.selectSingleNode(postCodeXpath);
			if(postCodeNode != null){
				form.setPostCode(postCodeNode.getText());
			}
			String telephoneXpath = "//telephone";
			Node telephoneNode = dom.selectSingleNode(telephoneXpath);
			if(telephoneNode != null){
				form.setTelephone(telephoneNode.getText());
			}
			String oldFileLocationOrgXpath = "//oldFileLocationOrg";
			Node oldFileLocationOrgNode = dom.selectSingleNode(oldFileLocationOrgXpath);
			if(oldFileLocationOrgNode != null){
				form.setOldFileLocationOrg(oldFileLocationOrgNode.getText());
			}
			String oldFileLocationOrgCodeXpath = "//oldFileLocationOrgCode";
			Node oldFileLocationOrgCodeNode = dom.selectSingleNode(oldFileLocationOrgCodeXpath);
			if(oldFileLocationOrgCodeNode != null){
				form.setOldFileLocationOrgCode(oldFileLocationOrgCodeNode.getText());
			}
			String oldOrgNameXpath = "//oldOrgName";
			Node oldOrgNameNode = dom.selectSingleNode(oldOrgNameXpath);
			if(oldOrgNameNode != null){
				form.setOldOrgName(oldOrgNameNode.getText());
			}
			String statusIdXpath = "//statusId";
			Node statusIdNode = dom.selectSingleNode(statusIdXpath);
			if(statusIdNode != null){
				form.setStatusId(statusIdNode.getText());
			}
			String statusNameXpath = "//statusName";
			Node statusNameNode = dom.selectSingleNode(statusNameXpath);
			if(statusNameNode != null){
				form.setStatusName(statusNameNode.getText());
			}
			String bearStatusNameXpath = "//bearStatusName";
			Node bearStatusNameNode = dom.selectSingleNode(bearStatusNameXpath);
			if(bearStatusNameNode != null){
				form.setBearStatusName(bearStatusNameNode.getText());
			}
			String joinTimeXpath = "//joinTime";
			Node joinTimeNode = dom.selectSingleNode(joinTimeXpath);
			if(joinTimeNode != null){
				form.setJoinTime(joinTimeNode.getText());
			}
			String isRelationIntoXpath = "//isRelationInto";
			Node isRelationIntoNode = dom.selectSingleNode(isRelationIntoXpath);
			if(isRelationIntoNode != null){
				form.setIsRelationInto(isRelationIntoNode.getText());
			}
			String accountNumXpath = "//accountNum";
			Node accountNumNode = dom.selectSingleNode(accountNumXpath);
			if(accountNumNode != null){
				form.setAccountNum(accountNumNode.getText());
			}
			String foreignLanguageNameXpath = "//foreignLanguageName";
			Node foreignLanguageNameNode = dom.selectSingleNode(foreignLanguageNameXpath);
			if(foreignLanguageNameNode != null){
				form.setForeignLanguageName(foreignLanguageNameNode.getText());
			}
			String foreignLanguageScoreXpath = "//foreignLanguageScore";
			Node foreignLanguageScoreNode = dom.selectSingleNode(foreignLanguageScoreXpath);
			if(foreignLanguageScoreNode != null){
				form.setForeignLanguageScore(foreignLanguageScoreNode.getText());
			}
			String politicalTheoryNameXpath = "//politicalTheoryName";
			Node politicalTheoryNameNode = dom.selectSingleNode(politicalTheoryNameXpath);
			if(politicalTheoryNameNode != null){
				form.setPoliticalTheoryName(politicalTheoryNameNode.getText());
			}
			String politicalTheoryScoreXpath = "//politicalTheoryScore";
			Node politicalTheoryScoreNode = dom.selectSingleNode(politicalTheoryScoreXpath);
			if(politicalTheoryScoreNode != null){
				form.setPoliticalTheoryScore(politicalTheoryScoreNode.getText());
			}
			String firstSubjectCodeXpath = "//firstSubjectCode";
			Node firstSubjectCodeNode = dom.selectSingleNode(firstSubjectCodeXpath);
			if(firstSubjectCodeNode != null){
				form.setFirstSubjectCode(firstSubjectCodeNode.getText());
			}
			String firstSubjectNameXpath = "//firstSubjectName";
			Node firstSubjectNameNode = dom.selectSingleNode(firstSubjectNameXpath);
			if(firstSubjectNameNode != null){
				form.setFirstSubjectName(firstSubjectNameNode.getText());
			}
			String firstSubjectScoreXpath = "//firstSubjectScore";
			Node firstSubjectScoreNode = dom.selectSingleNode(firstSubjectScoreXpath);
			if(firstSubjectScoreNode != null){
				form.setFirstSubjectScore(firstSubjectScoreNode.getText());
			}
			String secondSubjectCodeXpath = "//secondSubjectCode";
			Node secondSubjectCodeNode = dom.selectSingleNode(secondSubjectCodeXpath);
			if(secondSubjectCodeNode != null){
				form.setSecondSubjectCode(secondSubjectCodeNode.getText());
			}
			String secondSubjectNameXpath = "//secondSubjectName";
			Node secondSubjectNameNode = dom.selectSingleNode(secondSubjectNameXpath);
			if(secondSubjectNameNode != null){
				form.setSecondSubjectName(secondSubjectNameNode.getText());
			}
			String secondSubjectScoreXpath = "//secondSubjectScore";
			Node secondSubjectScoreNode = dom.selectSingleNode(secondSubjectScoreXpath);
			if(secondSubjectScoreNode != null){
				form.setSecondSubjectScore(secondSubjectScoreNode.getText());
			}
			String firstAddExamNameXpath = "//firstAddExamName";
			Node firstAddExamNameNode = dom.selectSingleNode(firstAddExamNameXpath);
			if(firstAddExamNameNode != null){
				form.setFirstAddExamName(firstAddExamNameNode.getText());
			}
			String firstAddExamScoreXpath = "//firstAddExamScore";
			Node firstAddExamScoreNode = dom.selectSingleNode(firstAddExamScoreXpath);
			if(firstAddExamScoreNode != null){
				form.setFirstAddExamScore(firstAddExamScoreNode.getText());
			}
			String secondAddExamNameXpath = "//secondAddExamName";
			Node secondAddExamNameNode = dom.selectSingleNode(secondAddExamNameXpath);
			if(secondAddExamNameNode != null){
				form.setSecondAddExamName(secondAddExamNameNode.getText());
			}
			String secondAddExamScoreXpath = "//secondAddExamScore";
			Node secondAddExamScoreNode = dom.selectSingleNode(secondAddExamScoreXpath);
			if(secondAddExamScoreNode != null){
				form.setSecondAddExamScore(secondAddExamScoreNode.getText());
			}
			String reexamineScoreXpath = "//reexamineScore";
			Node reexamineScoreNode = dom.selectSingleNode(reexamineScoreXpath);
			if(reexamineScoreNode != null){
				form.setReexamineScore(reexamineScoreNode.getText());
			}
			String totalPointsScoreXpath = "//totalPointsScore";
			Node totalPointsScoreNode = dom.selectSingleNode(totalPointsScoreXpath);
			if(totalPointsScoreNode != null){
				form.setTotalPointsScore(totalPointsScoreNode.getText());
			}
			String isDoctorQuaCertXpath = "//isDoctorQuaCert";
			Node isDoctorQuaCertNode = dom.selectSingleNode(isDoctorQuaCertXpath);
			if(isDoctorQuaCertNode != null){
				form.setIsDoctorQuaCert(isDoctorQuaCertNode.getText());
			}
			String codeDoctorQuaCertXpath = "//codeDoctorQuaCert";
			Node codeDoctorQuaCertNode = dom.selectSingleNode(codeDoctorQuaCertXpath);
			if(codeDoctorQuaCertNode != null){
				form.setCodeDoctorQuaCert(codeDoctorQuaCertNode.getText());
			}
			String isMedicalPractitionerXpath = "//isMedicalPractitioner";
			Node isMedicalPractitionerNode = dom.selectSingleNode(isMedicalPractitionerXpath);
			if(isMedicalPractitionerNode != null){
				form.setIsMedicalPractitioner(isMedicalPractitionerNode.getText());
			}
			String codeMedicalPractitionerXpath = "//codeMedicalPractitioner";
			Node codeMedicalPractitionerNode = dom.selectSingleNode(codeMedicalPractitionerXpath);
			if(codeMedicalPractitionerNode != null){
				form.setCodeMedicalPractitioner(codeMedicalPractitionerNode.getText());
			}
			String isStayXpath = "//isStay";
			Node isStayNode = dom.selectSingleNode(isStayXpath);
			if(isStayNode != null){
				form.setIsStay(isStayNode.getText());
			}
			String roomNumStayXpath = "//roomNumStay";
			Node roomNumStayNode = dom.selectSingleNode(roomNumStayXpath);
			if(roomNumStayNode != null){
				form.setRoomNumStay(roomNumStayNode.getText());
			}
			String telephoneStayXpath = "//telephoneStay";
			Node telephoneStayNode = dom.selectSingleNode(telephoneStayXpath);
			if(telephoneStayNode != null){
				form.setTelephoneStay(telephoneStayNode.getText());
			}
			String mateNameXpath = "//mateName";
			Node mateNameNode = dom.selectSingleNode(mateNameXpath);
			if(mateNameNode != null){
				form.setMateName(mateNameNode.getText());
			}
			String mateIdNoXpath = "//mateIdNo";
			Node mateIdNoNode = dom.selectSingleNode(mateIdNoXpath);
			if(mateIdNoNode != null){
				form.setMateIdNo(mateIdNoNode.getText());
			}
			String directionalOrgNameXpath = "//directionalOrgName";
			Node directionalOrgNameNode = dom.selectSingleNode(directionalOrgNameXpath);
			if(directionalOrgNameNode != null){
				form.setDirectionalOrgName(directionalOrgNameNode.getText());
			}
			String remarkXpath = "//remark";
			Node remarkNode = dom.selectSingleNode(remarkXpath);
			if(remarkNode != null){
				form.setRemark(remarkNode.getText());
			}
			String outOfSchoolDateXpath = "//outOfSchoolDate";
			Node outOfSchoolDateNode = dom.selectSingleNode(outOfSchoolDateXpath);
			if(outOfSchoolDateNode != null){
				form.setOutOfSchoolDate(outOfSchoolDateNode.getText());
			}
			String backToSchoolDateXpath = "//backToSchoolDate";
			Node backToSchoolDateNode = dom.selectSingleNode(backToSchoolDateXpath);
			if(backToSchoolDateNode != null){
				form.setBackToSchoolDate(backToSchoolDateNode.getText());
			}
			String dropOutSchoolDateXpath = "//dropOutSchoolDate";
			Node dropOutSchoolDateNode = dom.selectSingleNode(dropOutSchoolDateXpath);
			if(dropOutSchoolDateNode != null){
				form.setDropOutSchoolDate(dropOutSchoolDateNode.getText());
			}
			String awardSubjectCategoryXpath = "//awardSubjectCategory";
			Node awardSubjectCategoryNode = dom.selectSingleNode(awardSubjectCategoryXpath);
			if(awardSubjectCategoryNode != null){
				form.setAwardSubjectCategory(awardSubjectCategoryNode.getText());
			}
		}
		return form;
	}

}

