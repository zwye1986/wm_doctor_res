package com.pinde.sci.biz.gyxjgl.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gyxjgl.IGyChangeApplyBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.gyxjgl.GyXjEduCourseExtMapper;
import com.pinde.sci.dao.gyxjgl.GyXjEduStdentChangeExtMapper;
import com.pinde.sci.enums.gyxjgl.UserAwardApplyTypeEnum;
import com.pinde.sci.enums.gyxjgl.UserChangeApplyStatusEnum;
import com.pinde.sci.enums.gyxjgl.UserChangeApplyTypeEnum;
import com.pinde.sci.form.gyxjgl.SubmitApplyForm;
import com.pinde.sci.form.gyxjgl.UserChangeApplyForm;
import com.pinde.sci.form.gyxjgl.XjEduUserExtInfoForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.gyxjgl.XjEduStudentChangeExt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class GyChangeApplyBizImpl implements IGyChangeApplyBiz {
	@Autowired
	private EduUserMapper eduUserMapper;
	@Autowired
	private GyXjEduCourseExtMapper eduCourseExtMapper;
	@Autowired
	private EduUserChangeApplyMapper eduUserChangeApplyMapper;
	@Autowired
	private GyXjEduStdentChangeExtMapper eduStdentChangeExtMapper;
	@Autowired
	private ResDoctorMapper doctorMapper;
	@Autowired
	private PubFileMapper pubFileMapper;
	@Autowired
	private JobIntensionInfoMapper intensionMapper;
	@Autowired
	private EmploymentFeedbackMapper feedbackMapper;
	@Autowired
	private GyXjEduUserBizImpl eduUserBizImpl;

	private static Object getValueByAttrs(String attrNames, Object obj) throws Exception {
		Object value = "";
		if (StringUtil.isNotBlank(attrNames)) {
			String proptyName = "";
			int pIndex = attrNames.indexOf(".");

			if (pIndex >= 0) {
				proptyName = attrNames.substring(0, pIndex);
			} else {
				proptyName = attrNames;
			}

			if (StringUtil.isNotBlank(proptyName) && obj != null) {
				Class clazz = obj.getClass();
				String firstStr = proptyName.substring(0, 1).toUpperCase();
				String secondStr = proptyName.substring(1);
				Method mt = clazz.getMethod("get" + firstStr + secondStr);
				Object result = mt.invoke(obj);
				if (result != null) {
					String stringClassName = String.class.getSimpleName();
					String valueClassName = result.getClass().getSimpleName();
					if (stringClassName.equals(valueClassName)) {
						value = result;
					} else {
						String surplusName = attrNames.substring(pIndex + 1);
						value = getValueByAttrs(surplusName, result);
					}
				}
			}
		}
		return value;
	}

	@Override
	public EduUser readEduUser(String userFlow) {
		EduUser user=new EduUser();
		if (StringUtil.isNotBlank(userFlow)) {
			user=eduUserMapper.selectByPrimaryKey(userFlow);
		}
		return user;
	}

	@Override
	public List<EduCourse> searchStuCourseList(Map<String, Object> paramMap) {
		return eduCourseExtMapper.searchStuCourseList(paramMap);
	}

	@Override
	public int saveAndUpdateChangeApplyInfo(SubmitApplyForm form,UserChangeApplyForm applyForm,String userFlow,String recordFlow) {
		if (StringUtil.isNotBlank(recordFlow)) {
			EduUserChangeApply exitUser=eduUserChangeApplyMapper.selectByPrimaryKey(recordFlow);
			if (exitUser!=null) {
				exitUser.setApplyTime(DateUtil.getCurrDate());
				exitUser.setApplyTypeId(applyForm.getApply().getApplyTypeId());
				exitUser.setApplyTypeName(applyForm.getApply().getApplyTypeName());
				String xml=JaxbUtil.convertToXml(form);
				exitUser.setContent(xml);
				GeneralMethod.setRecordInfo(exitUser, false);
				eduUserChangeApplyMapper.updateByPrimaryKeySelective(exitUser);
			}
		}else{
			EduUserChangeApply eduUser=applyForm.getApply();
			eduUser.setApplyTime(DateUtil.getCurrDate());
			eduUser.setApplyTypeName(applyForm.getApply().getApplyTypeName());
			eduUser.setStatusId(UserChangeApplyStatusEnum.Save.getId());
			eduUser.setStatusName(UserChangeApplyStatusEnum.Save.getName());
			if (StringUtil.isNotBlank(userFlow)) {
				eduUser.setUserFlow(userFlow);
			}
			String xml=JaxbUtil.convertToXml(form);
			eduUser.setContent(xml);
			eduUser.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(eduUser, true);
			eduUserChangeApplyMapper.insert(eduUser);
		}
		return GlobalConstant.ONE_LINE;
	}

	@Override
	public List<EduUserChangeApply> searchEduUserChangeApply(EduUserChangeApply user, String flag) {
		EduUserChangeApplyExample example=new EduUserChangeApplyExample();
		EduUserChangeApplyExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
		if (StringUtil.isNotBlank(user.getUserFlow())) {
			criteria.andUserFlowEqualTo(user.getUserFlow());
		}
		if (StringUtil.isNotBlank(user.getApplyTypeId())) {
			criteria.andApplyTypeIdEqualTo(user.getApplyTypeId());
		}
		if (StringUtil.isNotBlank(user.getApplyTypeName())) {
			criteria.andApplyTypeNameEqualTo(user.getApplyTypeName());
		}
		if (StringUtil.isNotBlank(user.getStatusId())) {
			criteria.andStatusIdEqualTo(user.getStatusId());
		}
		if (StringUtil.isNotBlank(user.getStatusName())) {
			criteria.andStatusNameEqualTo(user.getStatusName());
		}
		if (StringUtil.isNotBlank(user.getRecordFlow())) {
			criteria.andRecordFlowEqualTo(user.getRecordFlow());
		}
		if (StringUtil.isNotBlank(user.getApplyTime())) {
			criteria.andApplyTimeEqualTo(user.getApplyTime());
		}
		List<String> applyList = new ArrayList<>();
		if(StringUtil.isBlank(flag)){
			//异动申请
			for(UserChangeApplyTypeEnum en : UserChangeApplyTypeEnum.values()){
				applyList.add(en.getId());
			}
		}else{
			//奖助学金申请
			for(UserAwardApplyTypeEnum en : UserAwardApplyTypeEnum.values()){
				applyList.add(en.getId());
			}
		}
		criteria.andApplyTypeIdIn(applyList);
		example.setOrderByClause("APPLY_TIME desc,CREATE_TIME desc");
		return eduUserChangeApplyMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int updataApplyInfo(EduUserChangeApply user,SubmitApplyForm form) {
		SubmitApplyForm submitApplyForm=JaxbUtil.converyToJavaBean(user.getContent(), SubmitApplyForm.class);
		if (form!=null) {
			submitApplyForm.setAuditContent(form.getAuditContent());
			submitApplyForm.setAuditDate(form.getAuditDate());
			submitApplyForm.setAuditPerson(form.getAuditPerson());
			submitApplyForm.setTeacherSugg(form.getTeacherSugg());
			submitApplyForm.setTrainSugg(form.getTrainSugg());
			submitApplyForm.setPostgraduSchSugg(form.getPostgraduSchSugg());
			submitApplyForm.setSwichTeachSugg(form.getSwichTeachSugg());
			submitApplyForm.setSwitchOrgSugg(form.getSwitchOrgSugg());
			submitApplyForm.setStudySuggg(form.getStudySuggg());
			submitApplyForm.setTrainOrgSugg(form.getTrainOrgSugg());
			if (submitApplyForm!=null) {
				String xml=JaxbUtil.convertToXml(submitApplyForm);
				user.setContent(xml);
			}
		}
		GeneralMethod.setRecordInfo(user, false);
		//更换培养类别、更换导师、更换专业、休学，审核通过后 学籍信息相应变更，休学时间为审核通过时间
		if(UserChangeApplyStatusEnum.Approve.getId().equals(user.getStatusId())){
			EduUser eduUser = null;
			if(UserChangeApplyTypeEnum.ChangeTrainType.getId().equals(user.getApplyTypeId())){
				eduUser = new EduUser();
				eduUser.setUserFlow(user.getUserFlow());
				eduUser.setTrainCategoryId(getDictId(submitApplyForm.getWillTrainType(),"TrainCategory"));
				eduUser.setTrainCategoryName(submitApplyForm.getWillTrainType());
				eduUserMapper.updateByPrimaryKeySelective(eduUser);
			}else if(UserChangeApplyTypeEnum.ChangeTeacher.getId().equals(user.getApplyTypeId())){
				eduUser = new EduUser();
				eduUser.setUserFlow(user.getUserFlow());
				if(StringUtil.isNotBlank(submitApplyForm.getChangeTutorName())){
					List<String> teachers = Arrays.asList(submitApplyForm.getChangeTutorName().split("，"));
					if(teachers.size() > 1){
						eduUser.setFirstTeacher(teachers.get(0));
						eduUser.setSecondTeacher(teachers.get(1));
					}else{
						eduUser.setFirstTeacher(teachers.get(0));
						eduUser.setSecondTeacher("");
					}
				}else{//置空导师
					eduUser.setFirstTeacher("");
					eduUser.setSecondTeacher("");
				}
				eduUserMapper.updateByPrimaryKeySelective(eduUser);
			}else if(UserChangeApplyTypeEnum.ChangeMajor.getId().equals(user.getApplyTypeId())){
				eduUser = new EduUser();
				eduUser.setUserFlow(user.getUserFlow());
				eduUser.setMajorId(submitApplyForm.getSwichmajorId());
				eduUser.setMajorName(submitApplyForm.getSwichmajorName());
				eduUserMapper.updateByPrimaryKeySelective(eduUser);
			}else if(UserChangeApplyTypeEnum.StopStudy.getId().equals(user.getApplyTypeId())){
				EduUser eu = eduUserMapper.selectByPrimaryKey(user.getUserFlow());
				eu.setIsOutOfSchool(GlobalConstant.RECORD_STATUS_Y);
				XjEduUserExtInfoForm eduUserForm = eduUserBizImpl.parseExtInfoXml(eu.getContent());
				String str = user.getModifyTime();
				eduUserForm.setOutOfSchoolDate(str.substring(0,4)+"-"+str.substring(4,6)+"-"+str.substring(6,8));
				eu.setContent(eduUserBizImpl.getXmlFromExtInfo(eduUserForm));
				eduUserMapper.updateByPrimaryKeySelective(eu);
			}
		}
		return eduUserChangeApplyMapper.updateByPrimaryKeyWithBLOBs(user);
	}

	private String getDictId(String dictName, String dictTypeId) {
		Map<String, String> dictNameMap = InitConfig.getDictNameMap(dictTypeId);
		return dictNameMap.get(dictName);
	}

	@Override
	public List<XjEduStudentChangeExt> searchStdentChangeExtsList(Map<String, Object> paramMap) {
		return eduStdentChangeExtMapper.searchStdentChangeExtsList(paramMap);
	}

	@Override
	public EduUserChangeApply readEduUserChangeApply(String recordFlow) {
		return eduUserChangeApplyMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public void exportExcel(String[] headLines, String[] titles,List<?> dataList, OutputStream os) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		//HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFCellStyle styleTwo = wb.createCellStyle();
		styleTwo.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFCellStyle styleThree = wb.createCellStyle();
		styleThree.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		 HSSFFont font =wb.createFont();
		 font.setFontHeightInPoints((short)17);
		 HSSFFont fontTwo =wb.createFont();
		 fontTwo.setFontHeightInPoints((short)12);
		 //
		 int headLength = headLines.length;
		 if(headLines!=null && headLength > 0){
			 for(int i=0; i<headLength ; i++){
				 HSSFRow row = sheet.createRow(i);
				 HSSFCell hSSFCell0 = row.createCell(0);
				 hSSFCell0.setCellValue(headLines[i]);
				 if(i==0){
					 styleTwo.setFont(font);
					 hSSFCell0.setCellStyle(styleTwo);
				 }
				 if(i==1){
					 styleThree.setFont(fontTwo);
					 hSSFCell0.setCellStyle(styleThree);
				 }
				 sheet.addMergedRegion(new CellRangeAddress(i, (short)i, 0, (short)titles.length-1));
			 }
		 }

		List<String> paramIds = new ArrayList<String>();
		HSSFRow row = sheet.createRow(headLength);
		 HSSFCell cell = null;
		 for(int i = 0 ; i<titles.length ; i++){
			 String[] title = titles[i].split(":");
			 cell = row.createCell(i);
			 cell.setCellValue(title[1]);
			 cell.setCellStyle(style);
			 paramIds.add(title[0]);
			 int length = title[1].length();
//			 sheet.setColumnWidth(i, length*1000);
		 }

		if (dataList != null) {
			 HSSFCell rowCell = null;
			 for(int i=0; i<dataList.size() ; i++){
				 Object item = dataList.get(i);
				 row = sheet.createRow(headLength + 1 + i);
				 Object result = null;
				 for(int j = 0 ; j <paramIds.size();j++){
					 String paramId = paramIds.get(j);
					 if(StringUtil.isBlank(paramId)){//序号
						 result = i+1;
					 }else{
						 result = getValueByAttrs(paramId,item);
					 }
					 rowCell=row.createCell(j);
					 rowCell.setCellStyle(style);
					 rowCell.setCellValue(result.toString());
				 }

			 }
		 }
		 for(int i = 0 ; i<titles.length ; i++){
			 sheet.autoSizeColumn(i,true);
		 }
		wb.write(os);

	}

	@Override
	public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName) {
		String path = GlobalConstant.FLAG_N;
		if (file.getSize() > 0) {
			//创建目录
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + folderName + File.separator + dateString;
			File fileDir = new File(newDir);
			if (!fileDir.exists()) {
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
			if (StringUtil.isNotBlank(oldFolderName)) {
				try {
					oldFolderName = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName;
					File imgFile = new File(oldFolderName);
					if (imgFile.exists()) {
						imgFile.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("删除文件失败！");
				}
			}
			path = folderName + "/" + dateString + "/" + originalFilename;
		}

		return path;
	}

	@Override
	public String checkImg(MultipartFile file) {
		List<String> mimeList = new ArrayList<String>();
		if (StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))) {
			mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
		}
		List<String> suffixList = new ArrayList<String>();
		if (StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))) {
			suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix").toLowerCase()).split(","));
		}
		String fileType = file.getContentType();//MIME类型;
		String fileName = file.getOriginalFilename();//文件名
		String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
		if (!(mimeList.contains(fileType) && suffixList.contains(suffix.toLowerCase()))) {
			return "请上传 " + InitConfig.getSysCfg("inx_image_support_suffix") + "格式的文件";
		}
		long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
		if (file.getSize() > limitSize * 1024 * 1024) {
			return GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize + "M！";
		}
		return GlobalConstant.FLAG_Y;//可执行保存
	}

	@Override
	public ResDoctor searchByUserFlow(String userFlow) {
		return doctorMapper.selectByPrimaryKey(userFlow);
	}

	@Override
	public String saveFileToDirs(MultipartFile file, String folderName, String applyTypeId) {
		String path = GlobalConstant.FLAG_N;
		if (file.getSize() > 0) {
			//创建目录
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + folderName + File.separator + applyTypeId;
			File fileDir = new File(newDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			//文件名
			String originalFilename = file.getOriginalFilename();
			File newFile = new File(fileDir, originalFilename);
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存文件失败！");
			}
			path = folderName + "/" + applyTypeId + "/" + originalFilename;
		}
		return path;
	}

	@Override
	public int saveTblFileInfo(SubmitApplyForm form, String applyTypeId, String awardFlag) {
		String memo = GlobalConstant.FLAG_Y.equals(awardFlag)?"学籍奖助学金审核上传表格":"学籍异动审核上传表格";
		if(StringUtil.isNotBlank(applyTypeId)){
			PubFileExample example = new PubFileExample();
			example.createCriteria().andFileTypeEqualTo(applyTypeId)
					.andFileRemarkEqualTo(memo).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			pubFileMapper.deleteByExample(example);//保存某申请类型数据时先做删除
		}
		int count = 0;
		if(null != form && form.getChangeApplyUrlList().size() > 0){
			for (int i = 0; i < form.getChangeApplyUrlList().size(); i++) {
				String filePath = form.getChangeApplyUrlList().get(i);
				PubFile pf = new PubFile();
				pf.setFileFlow(PkUtil.getUUID());
				pf.setFileName(filePath.substring(filePath.lastIndexOf('/')+1));
				pf.setFilePath(filePath);
				pf.setFileRemark(memo);
				pf.setFileType(applyTypeId);
				GeneralMethod.setRecordInfo(pf,true);
				count += pubFileMapper.insert(pf);
			}
		}
		return count;
	}

	@Override
	public List<String> searchUrlList(String applyTypeId,String column) {
		List<String> urlList = new ArrayList<>();
		PubFileExample example = new PubFileExample();
		example.createCriteria().andFileTypeEqualTo(applyTypeId)
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<PubFile> fileList = pubFileMapper.selectByExample(example);
		if(column.equals("filePath")){
			for (PubFile pf: fileList) {
				urlList.add(pf.getFilePath());
			}
		}else{
			for (PubFile pf: fileList) {
				urlList.add(pf.getFileName());
			}
		}
		return urlList;
	}

	@Override
	public int reqTypeCount(EduUserChangeApply apply) {
		EduUserChangeApplyExample example = new EduUserChangeApplyExample();
		example.createCriteria().andApplyTypeIdEqualTo(apply.getApplyTypeId())
				.andStatusIdEqualTo(UserChangeApplyStatusEnum.Submit.getId())
				.andUserFlowEqualTo(apply.getUserFlow()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return eduUserChangeApplyMapper.countByExample(example);
	}

	@Override
	public int saveInstructionInfo(String applyTypeId, String content, String awardFlag) {
		int count = 0;
		String memo = GlobalConstant.FLAG_Y.equals(awardFlag)?"学籍奖助学金审核上传说明":"学籍异动审核上传说明";
		String applyTypeName = GlobalConstant.FLAG_Y.equals(awardFlag)?UserAwardApplyTypeEnum.getNameById(applyTypeId):UserChangeApplyTypeEnum.getNameById(applyTypeId);
		if(StringUtil.isNotBlank(applyTypeId)){
			PubFileExample example = new PubFileExample();
			example.createCriteria().andFileTypeEqualTo(applyTypeId)
					.andFileRemarkEqualTo(memo).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			List<PubFile> pubFiles = pubFileMapper.selectByExample(example);
			if(null != pubFiles && pubFiles.size() > 0){
				PubFile pf = pubFiles.get(0);
				pf.setFileContent(content == null?"".getBytes():content.getBytes());
				count = pubFileMapper.updateByPrimaryKeySelective(pf);
			}else{
				PubFile pf = new PubFile();
				pf.setFileFlow(PkUtil.getUUID());
				pf.setFileName(applyTypeName+"上传说明");
				pf.setFileContent(content.getBytes());
				pf.setFileRemark(memo);
				pf.setFileType(applyTypeId);
				GeneralMethod.setRecordInfo(pf,true);
				count = pubFileMapper.insert(pf);
			}
		}
		return count;
	}

	@Override
	public PubFile searchInstrutionInfo(String applyTypeId, String awardFlag) {
		PubFile pf = null;
		String memo = GlobalConstant.FLAG_Y.equals(awardFlag)?"学籍奖助学金审核上传说明":"学籍异动审核上传说明";
		if(StringUtil.isNotBlank(applyTypeId)){
			PubFileExample example = new PubFileExample();
			example.createCriteria().andFileTypeEqualTo(applyTypeId)
					.andFileRemarkEqualTo(memo).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			List<PubFile> pubFiles = pubFileMapper.selectByExampleWithBLOBs(example);
			if(null != pubFiles && pubFiles.size() > 0){
				pf = pubFiles.get(0);
			}
		}
		return pf;
	}

	@Override
	public List<JobIntensionInfo> jobIntensionList(Map<String, String> map) {
		JobIntensionInfoExample example = new JobIntensionInfoExample();
		JobIntensionInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(map.get("userFlow"))){
			criteria.andUserFlowEqualTo(map.get("userFlow"));
		}
		if(StringUtil.isNotBlank(map.get("userName"))){
			criteria.andUserNameLike("%"+map.get("userName")+"%");
		}
		if(StringUtil.isNotBlank(map.get("majorName"))){
			criteria.andMajorNameLike("%"+map.get("majorName")+"%");
		}
		if(StringUtil.isNotBlank(map.get("sessionNumber"))){
			criteria.andSessionNumberEqualTo(map.get("sessionNumber"));
		}
		return intensionMapper.selectByExample(example);
	}

	@Override
	public JobIntensionInfo queryIntensionByFlow(String recordFlow) {
		return intensionMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public int saveIntesion(JobIntensionInfo info) {
		if(StringUtil.isNotBlank(info.getRecordFlow())){
			GeneralMethod.setRecordInfo(info,false);
			return intensionMapper.updateByPrimaryKeySelective(info);
		}else{
			info.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(info,true);
			return intensionMapper.insertSelective(info);
		}
	}

	@Override
	public int delIntesion(String recordFlow) {
		return intensionMapper.deleteByPrimaryKey(recordFlow);
	}

	@Override
	public List<EmploymentFeedback> feedbackList(Map<String,String> map) {
		EmploymentFeedbackExample example = new EmploymentFeedbackExample();
		EmploymentFeedbackExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(map.get("userName"))){
			criteria.andUserNameLike("%"+map.get("userName")+"%");
		}
		if(StringUtil.isNotBlank(map.get("majorName"))){
			criteria.andMajorNameLike("%"+map.get("majorName")+"%");
		}
		if(StringUtil.isNotBlank(map.get("sessionNumber"))){
			criteria.andSessionNumberEqualTo(map.get("sessionNumber"));
		}
		return feedbackMapper.selectByExample(example);
	}

	@Override
	public EmploymentFeedback queryFeedbackByFlow(String recordFlow) {
		return feedbackMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public int saveFeedback(EmploymentFeedback info) {
		if(StringUtil.isNotBlank(info.getRecordFlow())){
			GeneralMethod.setRecordInfo(info,false);
			return feedbackMapper.updateByPrimaryKeySelective(info);
		}else{
			info.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(info,true);
			return feedbackMapper.insertSelective(info);
		}
	}

	@Override
	public int delFeedback(String recordFlow) {
		return feedbackMapper.deleteByPrimaryKey(recordFlow);
	}
}
