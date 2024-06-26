package com.pinde.sci.biz.sch.impl;

import com.pinde.core.util.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sch.ISchDoctorAbsenceBiz;
import com.pinde.sci.biz.sch.IZseyHrKqMonthBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.PubFileMapper;
import com.pinde.sci.dao.base.SchDoctorAbsenceMapper;
import com.pinde.sci.dao.sch.SchDoctorAbsenceExtMapper;
import com.pinde.sci.enums.res.AbsenceTypeEnum;
import com.pinde.sci.enums.res.HBRecDocTypeEnum;
import com.pinde.sci.model.mo.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author tiger
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class SchDoctorAbsenceBizImpl implements ISchDoctorAbsenceBiz {
	@Autowired
	private SchDoctorAbsenceMapper doctorAbsenceMapper;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private SchDoctorAbsenceExtMapper doctorAbsenceExtMapper;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IZseyHrKqMonthBiz zseyHrKqMonthBiz;
	@Autowired
	private PubFileMapper pubFileMapper;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	
	@Override
	public List<SchDoctorAbsence> searchSchDoctorAbsenceByDoctor(String doctorFlow) {
		SchDoctorAbsenceExample example = new SchDoctorAbsenceExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow);
		example.setOrderByClause("START_DATE");
		return doctorAbsenceMapper.selectByExample(example);
	}
	
	@Override
	public List<SchDoctorAbsence> searchSchDoctorAbsenceByOrg(String orgFlow) {
		SchDoctorAbsenceExample example = new SchDoctorAbsenceExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow);
		example.setOrderByClause("START_DATE");
		return doctorAbsenceMapper.selectByExample(example);
	}

	@Override
	public SchDoctorAbsence readSchDoctorAbsence(String absenceFlow) {
		return doctorAbsenceMapper.selectByPrimaryKey(absenceFlow);
	}

	@Override
	public int saveSchDoctorAbsence(SchDoctorAbsence doctorAbsence) {
		if(doctorAbsence != null){
			if(StringUtil.isNotBlank(doctorAbsence.getAbsenceFlow())){
				GeneralMethod.setRecordInfo(doctorAbsence, false);
				return doctorAbsenceMapper.updateByPrimaryKeySelective(doctorAbsence);
			}else{
				doctorAbsence.setAbsenceFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(doctorAbsence, true);
				return doctorAbsenceMapper.insertSelective(doctorAbsence);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int editSchDoctorAbsence(SchDoctorAbsence doctorAbsence, MultipartFile multipartFile) throws ParseException{
		 if(multipartFile!=null && (!multipartFile.isEmpty())){
             PubFile pubFile = new PubFile();

             String originalFileName = multipartFile.getOriginalFilename();
			 String suffix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			 //默认的文件名
			 pubFile.setFileName(originalFileName);
			 //文件后缀名
			 pubFile.setFileSuffix(suffix);
			 pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				 //定义上传路径
				 String dateString = DateUtil.getCurrDate2();
				 String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "absenceFile" + File.separator + dateString;
				 File fileDir = new File(newDir);
				 if (!fileDir.exists()) {
					 fileDir.mkdirs();
				 }
				 //重命名上传后的文件名
				 originalFileName = PkUtil.getUUID() + originalFileName;
				 File newFile = new File(fileDir, originalFileName);
             try {
				 multipartFile.transferTo(newFile);
             } catch (Exception e) {
				 throw new RuntimeException("文件上传异常");
			 }
			 String filePath = File.separator + "absenceFile" + File.separator + dateString + File.separator + originalFileName;
			 pubFile.setFileFlow(PkUtil.getUUID());
			 pubFile.setFilePath(filePath);
			 GeneralMethod.setRecordInfo(pubFile, true);
			 pubFileMapper.insertSelective(pubFile);
			 doctorAbsence.setMakingFile(pubFile.getFileFlow());
		 }
		if(StringUtil.isNotBlank(doctorAbsence.getAbsenceTypeId())){
			doctorAbsence.setAbsenceTypeName(AbsenceTypeEnum.getNameById(doctorAbsence.getAbsenceTypeId()));
		}else{
			doctorAbsence.setAbsenceTypeName("");
		}

		SysUser user = GlobalContext.getCurrentUser();
		ResDoctor resDoctor = resDoctorBiz.readDoctor(user.getUserFlow());
		SysUser currentUser =null;
		ResDoctor currentDoctor = null;
		if(StringUtil.isBlank(doctorAbsence.getDoctorFlow())){
			currentUser = user;
			currentDoctor = resDoctor;
		}else{
			currentUser = userBiz.findByFlow(doctorAbsence.getDoctorFlow());
			currentDoctor = doctorBiz.findByFlow(doctorAbsence.getDoctorFlow());
		}
		//首先查找专业基地管理员
		String trainingSpeId = currentDoctor.getTrainingSpeId();
		String proBaseFlow = InitConfig.getSysCfg("res_professionalBase_admin_role_flow");
		String wsId = (String)GlobalContext.getSession().getAttribute(GlobalConstant.CURRENT_WS_ID);
		Map<String,Object> paramMap = new HashMap<>();
		if(StringUtil.isNotBlank(proBaseFlow)){
			paramMap.put("roleFlow",proBaseFlow);
			paramMap.put("resTrainingSpeId",trainingSpeId);
			paramMap.put("orgFlow",currentUser.getOrgFlow());
			paramMap.put("wsId",wsId);
			List<SysUser> sysUserList = userBiz.searchUserWithRole(paramMap);
			if(sysUserList!=null&&sysUserList.size()>0){
//				doctorAbsence.setHeadFlow(sysUserList.get(0).getUserFlow());
//				doctorAbsence.setHeadName(sysUserList.get(0).getUserName());
			}else {
				throw new RuntimeException("系统内本专业暂未维护专业基地主任信息，请联系继教科管理员！");
			}
		}
		//然后查找医院管理员res_admin_role_flow
//		String adminFlow = InitConfig.getSysCfg("res_admin_role_flow");
//		paramMap.put("roleFlow",adminFlow);
//		paramMap.put("resTrainingSpeId","");
//		List<SysUser> sysUserList2 = userBiz.searchUserWithRole(paramMap);
//		if(sysUserList2!=null&&sysUserList2.size()>0){
//			doctorAbsence.setManagerFlow(sysUserList2.get(0).getUserFlow());
//			doctorAbsence.setManagerName(sysUserList2.get(0).getUserName());
//		}
		//科主任flow和带教flow已经在doctorAbsence里

		if(StringUtil.isNotBlank(doctorAbsence.getAbsenceFlow())&&"Y".equals(doctorAbsence.getIsRegister())){//删除旧考勤记录
			SchDoctorAbsence schDoctorAbsenceOld = readSchDoctorAbsence(doctorAbsence.getAbsenceFlow());
			String oldStartDate = schDoctorAbsenceOld.getStartDate();
			String oldEndDate = schDoctorAbsenceOld.getEndDate();
			//获得两个日期间所有日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dBegin = sdf.parse(oldStartDate);
			Date dEnd = sdf.parse(oldEndDate);
			List<Date> lDate = TimeUtil.findDates(dBegin, dEnd);
			List<String> dateListAll = new ArrayList<>();
			for (Date date : lDate)	{
				dateListAll.add(sdf.format(date));
			}
			//从考勤信息表删除
			String monthStartDay = oldStartDate.substring(0,7);
			String monthEndDay = oldEndDate.substring(0,7);
			List<String> months = TimeUtil.getMonthsByTwoMonth(monthStartDay,monthEndDay);
			if(months!=null&&months.size()>0){
				for(String month:months){
					ZseyHrKqMonth zseyHrKqMonth = zseyHrKqMonthBiz.searchByKqDate(schDoctorAbsenceOld.getDoctorFlow(),month);
					ZseyHrKqMonth hrKqMonth = new ZseyHrKqMonth();
					if(null!=zseyHrKqMonth){
						String monthFlow = zseyHrKqMonth.getMonthFlow();
						hrKqMonth.setMonthFlow(monthFlow);
					}
//					hrKqMonth.setResType("2");
//					hrKqMonth.setUserFlow(user.getUserFlow());
//					hrKqMonth.setUserCode(user.getUserCode());
//					hrKqMonth.setUserName(user.getUserName());
//					hrKqMonth.setDeptFlow(user.getDeptFlow());
//					hrKqMonth.setDeptName(user.getDeptName());
//					hrKqMonth.setInDate(resDoctor.getSessionNumber());
//					hrKqMonth.setIdNo(user.getIdNo());
//					hrKqMonth.setKqDate(month);
//					hrKqMonth.setKqDeptFlow(doctorAbsence.getSchDeptFlow());
//					hrKqMonth.setKqDeptName(doctorAbsence.getSchDeptName());
					for(String date:dateListAll){
						if(month.equals(date.substring(0,7))){
							String day = date.substring(8,10);
							switch (day) {
								case  "01" : hrKqMonth.setM01("");break;
								case  "02" : hrKqMonth.setM02("");break;
								case  "03" : hrKqMonth.setM03("");break;
								case  "04" : hrKqMonth.setM04("");break;
								case  "05" : hrKqMonth.setM05("");break;
								case  "06" : hrKqMonth.setM06("");break;
								case  "07" : hrKqMonth.setM07("");break;
								case  "08" : hrKqMonth.setM08("");break;
								case  "09" : hrKqMonth.setM09("");break;
								case  "10" : hrKqMonth.setM10("");break;
								case  "11" : hrKqMonth.setM11("");break;
								case  "12" : hrKqMonth.setM12("");break;
								case  "13" : hrKqMonth.setM13("");break;
								case  "14" : hrKqMonth.setM14("");break;
								case  "15" : hrKqMonth.setM15("");break;
								case  "16" : hrKqMonth.setM16("");break;
								case  "17" : hrKqMonth.setM17("");break;
								case  "18" : hrKqMonth.setM18("");break;
								case  "19" : hrKqMonth.setM19("");break;
								case  "20" : hrKqMonth.setM20("");break;
								case  "21" : hrKqMonth.setM21("");break;
								case  "22" : hrKqMonth.setM22("");break;
								case  "23" : hrKqMonth.setM23("");break;
								case  "24" : hrKqMonth.setM24("");break;
								case  "25" : hrKqMonth.setM25("");break;
								case  "26" : hrKqMonth.setM26("");break;
								case  "27" : hrKqMonth.setM27("");break;
								case  "28" : hrKqMonth.setM28("");break;
								case  "29" : hrKqMonth.setM29("");break;
								case  "30" : hrKqMonth.setM30("");break;
								case  "31" : hrKqMonth.setM31("");break;
							}
						}
					}
					zseyHrKqMonthBiz.edit(hrKqMonth);
				}
			}
		}

		if(HBRecDocTypeEnum.CompanyEntrust.getId().equals(currentDoctor.getDoctorTypeId())){//委培人流程
			if(AbsenceTypeEnum.Yearleave.getId().equals(doctorAbsence.getAbsenceTypeId())&&Integer.parseInt(doctorAbsence.getIntervalDay())<=1){//如果1天年假 走科主任流程
				doctorAbsence.setHeadAgreeFlag("Y");
				doctorAbsence.setManagerAgreeFlag("Y");
			}else{//其他 走科主任-专业基地管理员-医院管理员流程

			}
		}else if(HBRecDocTypeEnum.Graduate.getId().equals(currentDoctor.getDoctorTypeId()) || "Y".equals(doctorAbsence.getIsRegister())){//在校专硕流程(不用审核)
			doctorAbsence.setTeacherAgreeFlag("Y");
			doctorAbsence.setHeadAgreeFlag("Y");
			doctorAbsence.setManagerAgreeFlag("Y");
			doctorAbsence.setRepealAbsence("Y");
			doctorAbsence.setRepealAbsenceDay("自动过审");
			//获得两个日期间所有日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dBegin = sdf.parse(doctorAbsence.getStartDate());
			Date dEnd = sdf.parse(doctorAbsence.getEndDate());
			List<Date> lDate = TimeUtil.findDates(dBegin, dEnd);
			List<String> dateListAll = new ArrayList<>();
			for (Date date : lDate)	{
				dateListAll.add(sdf.format(date));
			}
			//录入考勤信息表
			String monthStartDay = doctorAbsence.getStartDate().substring(0,7);
			String monthEndDay = doctorAbsence.getEndDate().substring(0,7);
			List<String> months = TimeUtil.getMonthsByTwoMonth(monthStartDay,monthEndDay);
			if(months!=null&&months.size()>0){
				for(String month:months){
					ZseyHrKqMonth zseyHrKqMonth = zseyHrKqMonthBiz.searchByKqDate(currentUser.getUserFlow(),month);
						ZseyHrKqMonth hrKqMonth = new ZseyHrKqMonth();
						if(null!=zseyHrKqMonth){
							String monthFlow = zseyHrKqMonth.getMonthFlow();
							hrKqMonth.setMonthFlow(monthFlow);
						}
						hrKqMonth.setResType("2");
						hrKqMonth.setUserFlow(currentUser.getUserFlow());
						hrKqMonth.setUserCode(currentUser.getUserCode());
						hrKqMonth.setUserName(currentUser.getUserName());
						hrKqMonth.setDeptFlow(currentUser.getDeptFlow());
						hrKqMonth.setDeptName(currentUser.getDeptName());
						hrKqMonth.setInDate(currentDoctor.getSessionNumber());
						hrKqMonth.setIdNo(currentUser.getIdNo());
						hrKqMonth.setKqDate(month);
						hrKqMonth.setKqDeptFlow(doctorAbsence.getSchDeptFlow());
						hrKqMonth.setKqDeptName(doctorAbsence.getSchDeptName());
						for(String date:dateListAll){
							if(month.equals(date.substring(0,7))){
								String day = date.substring(8,10);
								AbsenceTypeEnum typeEnum = (AbsenceTypeEnum)(EnumUtil.getById(doctorAbsence.getAbsenceTypeId(), AbsenceTypeEnum.class));
								String code = typeEnum.getCode();
								switch (day) {
									case  "01" : hrKqMonth.setM01(code);break;
									case  "02" : hrKqMonth.setM02(code);break;
									case  "03" : hrKqMonth.setM03(code);break;
									case  "04" : hrKqMonth.setM04(code);break;
									case  "05" : hrKqMonth.setM05(code);break;
									case  "06" : hrKqMonth.setM06(code);break;
									case  "07" : hrKqMonth.setM07(code);break;
									case  "08" : hrKqMonth.setM08(code);break;
									case  "09" : hrKqMonth.setM09(code);break;
									case  "10" : hrKqMonth.setM10(code);break;
									case  "11" : hrKqMonth.setM11(code);break;
									case  "12" : hrKqMonth.setM12(code);break;
									case  "13" : hrKqMonth.setM13(code);break;
									case  "14" : hrKqMonth.setM14(code);break;
									case  "15" : hrKqMonth.setM15(code);break;
									case  "16" : hrKqMonth.setM16(code);break;
									case  "17" : hrKqMonth.setM17(code);break;
									case  "18" : hrKqMonth.setM18(code);break;
									case  "19" : hrKqMonth.setM19(code);break;
									case  "20" : hrKqMonth.setM20(code);break;
									case  "21" : hrKqMonth.setM21(code);break;
									case  "22" : hrKqMonth.setM22(code);break;
									case  "23" : hrKqMonth.setM23(code);break;
									case  "24" : hrKqMonth.setM24(code);break;
									case  "25" : hrKqMonth.setM25(code);break;
									case  "26" : hrKqMonth.setM26(code);break;
									case  "27" : hrKqMonth.setM27(code);break;
									case  "28" : hrKqMonth.setM28(code);break;
									case  "29" : hrKqMonth.setM29(code);break;
									case  "30" : hrKqMonth.setM30(code);break;
									case  "31" : hrKqMonth.setM31(code);break;
								}
							}
						}
						zseyHrKqMonthBiz.edit(hrKqMonth);

				}
			}
		}

		if (StringUtil.isBlank(doctorAbsence.getAbsenceFlow())) {//新增
			if (StringUtil.isBlank(doctorAbsence.getDoctorFlow())) {//医师新增请假
				SysUser currUser = GlobalContext.getCurrentUser();
				if(StringUtil.isBlank(doctorAbsence.getDoctorFlow())){
					doctorAbsence.setDoctorFlow(currUser.getUserFlow());
					doctorAbsence.setDoctorName(currUser.getUserName());
				}
				if(StringUtil.isBlank(doctorAbsence.getIsRegister())){
					doctorAbsence.setIsRegister(GlobalConstant.FLAG_N);
				}
			}
			
			String schDeptFlow = doctorAbsence.getSchDeptFlow();
			if(StringUtil.isNotBlank(schDeptFlow)){
				SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
				if(dept!=null){
					doctorAbsence.setDeptFlow(dept.getDeptFlow());
					doctorAbsence.setDeptName(dept.getDeptName());
				}
			}

			if(currentDoctor != null){
				doctorAbsence.setSessionNumber(currentDoctor.getSessionNumber());
				doctorAbsence.setTrainingSpeId(currentDoctor.getTrainingSpeId());
				doctorAbsence.setTrainingSpeName(currentDoctor.getTrainingSpeName());
				doctorAbsence.setDoctorCategoryId(currentDoctor.getDoctorCategoryId());
				doctorAbsence.setDoctorCategoryName(currentDoctor.getDoctorCategoryName());
				if(StringUtil.isBlank(doctorAbsence.getRepealAbsence())){
					doctorAbsence.setRepealAbsence(GlobalConstant.RECORD_STATUS_N);
				}
//				doctorAbsence.setOrgFlow(currentDoctor.getOrgFlow());
//				doctorAbsence.setOrgName(currentDoctor.getOrgName());
				return saveSchDoctorAbsence(doctorAbsence);
			}
		} else {//修改
			return saveSchDoctorAbsence(doctorAbsence);
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int editSchDoctorAbsence2(SchDoctorAbsence doctorAbsence, MultipartFile multipartFile) {
		if(!multipartFile.isEmpty()){
			PubFile pubFile = new PubFile();
			String originalFileName = multipartFile.getOriginalFilename();
			String suffix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			//默认的文件名
			pubFile.setFileName(originalFileName);
			//文件后缀名
			pubFile.setFileSuffix(suffix);
			pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			try {
				pubFile.setFileContent(multipartFile.getBytes());
				fileBiz.editFile(pubFile);
				doctorAbsence.setMakingFile(pubFile.getFileFlow());
			} catch (Exception e) {
				throw new RuntimeException("文件上传异常");
			}
		}
		if(StringUtil.isNotBlank(doctorAbsence.getAbsenceTypeId())){
			doctorAbsence.setAbsenceTypeName(AbsenceTypeEnum.getNameById(doctorAbsence.getAbsenceTypeId()));
		}else{
			doctorAbsence.setAbsenceTypeName("");
		}
		if (StringUtil.isBlank(doctorAbsence.getAbsenceFlow())) {//新增
			if (StringUtil.isBlank(doctorAbsence.getDoctorFlow())) {//医师新增请假
				SysUser currUser = GlobalContext.getCurrentUser();
				doctorAbsence.setDoctorFlow(currUser.getUserFlow());
				doctorAbsence.setDoctorName(currUser.getUserName());
				doctorAbsence.setIsRegister(GlobalConstant.FLAG_N);
			}

			String schDeptFlow = doctorAbsence.getSchDeptFlow();
			if(StringUtil.isNotBlank(schDeptFlow)){
				SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
				if(dept!=null){
					doctorAbsence.setDeptFlow(dept.getDeptFlow());
					doctorAbsence.setDeptName(dept.getDeptName());
				}
			}

			String doctorFlow = doctorAbsence.getDoctorFlow();
			ResDoctor resDoctor = resDoctorBiz.readDoctor(doctorFlow);
			if(resDoctor != null){
				doctorAbsence.setSessionNumber(resDoctor.getSessionNumber());
				doctorAbsence.setTrainingSpeId(resDoctor.getTrainingSpeId());
				doctorAbsence.setTrainingSpeName(resDoctor.getTrainingSpeName());
				doctorAbsence.setDoctorCategoryId(resDoctor.getDoctorCategoryId());
				doctorAbsence.setDoctorCategoryName(resDoctor.getDoctorCategoryName());
				doctorAbsence.setRepealAbsence(GlobalConstant.RECORD_STATUS_N);
				doctorAbsence.setOrgFlow(resDoctor.getOrgFlow());
				doctorAbsence.setOrgName(resDoctor.getOrgName());
				return saveSchDoctorAbsence(doctorAbsence);
			}
		} else {//修改
			return saveSchDoctorAbsence(doctorAbsence);
		}
		return GlobalConstant.ZERO_LINE;
	}
	@Override
	public List<SchDoctorAbsence> searchSchDoctorAbsenceList(SchDoctorAbsence doctorAbsence) {
		SchDoctorAbsenceExample example = new SchDoctorAbsenceExample();
		com.pinde.sci.model.mo.SchDoctorAbsenceExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(doctorAbsence.getAbsenceTypeId())){
			criteria.andAbsenceTypeIdEqualTo(doctorAbsence.getAbsenceTypeId());
		}
		if(StringUtil.isNotBlank(doctorAbsence.getDoctorFlow())){
			criteria.andDoctorFlowEqualTo(doctorAbsence.getDoctorFlow());
		}
		if(StringUtil.isNotBlank(doctorAbsence.getDoctorName())){
			criteria.andDoctorNameLike("%" + doctorAbsence.getDoctorName() + "%");
		}
		if(StringUtil.isNotBlank(doctorAbsence.getSchDeptFlow())){
			criteria.andSchDeptFlowEqualTo(doctorAbsence.getSchDeptFlow());
		}
		if(StringUtil.isNotBlank(doctorAbsence.getRepealAbsence())){
			criteria.andRepealAbsenceEqualTo(doctorAbsence.getRepealAbsence());
		}
		if(StringUtil.isNotBlank(doctorAbsence.getStartDate())){
			criteria.andStartDateGreaterThanOrEqualTo(doctorAbsence.getStartDate());
		}
		if(StringUtil.isNotBlank(doctorAbsence.getEndDate())){
			criteria.andEndDateLessThanOrEqualTo(doctorAbsence.getEndDate());
		}
		if(StringUtil.isNotBlank(doctorAbsence.getOrgFlow())){
			criteria.andOrgFlowEqualTo(doctorAbsence.getOrgFlow());
		}
		if(StringUtil.isNotBlank(doctorAbsence.getTeacherFlow())){
			criteria.andTeacherFlowEqualTo(doctorAbsence.getTeacherFlow());
		}
		if(StringUtil.isNotBlank(doctorAbsence.getHeadFlow())){
			criteria.andHeadFlowEqualTo(doctorAbsence.getHeadFlow());
		}
		if(StringUtil.isNotBlank(doctorAbsence.getTrainingSpeId())){
			criteria.andTrainingSpeIdEqualTo(doctorAbsence.getTrainingSpeId());
		}
		if(StringUtil.isNotBlank(doctorAbsence.getSessionNumber())){
			criteria.andSessionNumberEqualTo(doctorAbsence.getSessionNumber());
		}
		if(StringUtil.isNotBlank(doctorAbsence.getIsRegister())){
			criteria.andIsRegisterEqualTo(doctorAbsence.getIsRegister());
		}
		if(StringUtil.isNotBlank(doctorAbsence.getTeachingFlow())){
			criteria.andTeachingFlowEqualTo(doctorAbsence.getTeachingFlow());
		}
		example.setOrderByClause("START_DATE DESC");
		return doctorAbsenceMapper.selectByExample(example);
	}
	
	@Override
	public List<SchDoctorAbsence> searchDoctorAbsence(Map<String , Object> paramMap){
		return doctorAbsenceExtMapper.searchDoctorAbsence(paramMap);
	}
	
	@Override
	public List<Map<String,Object>> countAbsenceDays(List<String> doctorFlows){
		return doctorAbsenceExtMapper.countAbsenceDays(doctorFlows);
	}

	@Override
	public List<SchDoctorAbsence> searchSchDoctorAbsenceByDoctorDept(
			String schDeptFlow, String doctorFlow) {
		SchDoctorAbsenceExample example = new SchDoctorAbsenceExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRepealAbsenceEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow).andSchDeptFlowEqualTo(schDeptFlow);
		return doctorAbsenceMapper.selectByExample(example);
	}

	@Override
	public List<SchDoctorAbsence> getCurrentYearLeaves(String absenceFlow,String currentYear, String doctorFlow) {
		SchDoctorAbsenceExample example = new SchDoctorAbsenceExample();
		SchDoctorAbsenceExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		String startDate = currentYear+"-01-01";
		String endDate = currentYear+"-12-12";
		if(StringUtil.isNotBlank(absenceFlow)){
			criteria.andAbsenceFlowNotEqualTo(absenceFlow);
		}
		criteria.andDoctorFlowEqualTo(doctorFlow);
		criteria.andStartDateGreaterThanOrEqualTo(startDate);
		criteria.andEndDateLessThanOrEqualTo(endDate);
		criteria.andAbsenceTypeIdEqualTo(AbsenceTypeEnum.Yearleave.getId());
		return doctorAbsenceMapper.selectByExample(example);
	}

	@Override
	public int checkDates(String userFlow, String startDate, String endDate, String absenceFlow) {
		return doctorAbsenceExtMapper.checkDates(userFlow,startDate,endDate,absenceFlow);
	}

	@Override
	public int importDict(MultipartFile file) {
		InputStream is = null;
		try {
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
			return parseExcel(wb);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	private static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
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
	private int parseExcel(Workbook wb) throws ParseException {
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
			int row_num = sheet.getLastRowNum()+1;
			if(row_num==1){
				throw new RuntimeException("导入文件内容为空！");
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
			String startDate="";
			String endDate="";
			String reason="";
			SysUser user = null;
			ResDoctor doctor = null;
			for(int i = 1;i <row_num; i++){
				Row r =  sheet.getRow(i);
				for(int j = 0; j < colnames.size(); j++){
					String value = "";
					Cell cell = r.getCell(j);
					if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
						if(cell.getCellType() == 1){
							value = cell.getStringCellValue().trim();
						}else{
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					String currTitle=colnames.get(j);
					if("身份证号码".equals(currTitle)){
						if(StringUtil.isBlank(value)){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，身份证号码不能为空！");
						}
						user = userBiz.findByIdNo(value);
						if(null==user){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，系统中无此学员请核查身份证号码！");
						}
						doctor = doctorBiz.readDoctor(user.getUserFlow());
					}
					String regEx="^\\d{4}-\\d{2}-\\d{2}$";
					if("缺勤开始时间".equals(currTitle)){
						if(StringUtil.isBlank(value)){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，缺勤开始时间不能为空！");
						}
						if(!value.matches(regEx)){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，缺勤开始时间格式不正确！");
						}
						startDate = value;
					}
					if("缺勤结束时间".equals(currTitle)){
						if(StringUtil.isBlank(value)){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，缺勤结束时间不能为空！");
						}
						if(!value.matches(regEx)){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，缺勤结束时间格式不正确！");
						}
						endDate = value;
					}
					if("原因".equals(currTitle)){
						reason = value;
					}
				}
				if(startDate.compareTo(endDate)>0){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，缺勤开始时间不能晚于缺勤结束时间！");
				}
				List<Map<String,Object>> resultMapList = schArrangeResultBiz.searchArrangeResultNotInDates(startDate,endDate,user.getUserFlow());
				if(resultMapList!=null && resultMapList.size()>0){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，部分缺勤时间不能轮转时间范围内！");
				}
				List<SchArrangeResult> schArrangeResults = schArrangeResultBiz.searchArrangeResultByDateAndDoctorFlow(startDate,endDate,user.getUserFlow());
				List<Map<String, Object>> dataList = new ArrayList<>();
				if(schArrangeResults!=null&&schArrangeResults.size()>0){
					int i1 = 0;
					for(SchArrangeResult result:schArrangeResults){
						Map<String,Object> dataMap = new HashMap<>();
						dataMap.put("SCH_DEPT_FLOW",result.getSchDeptFlow());
						dataMap.put("SCH_DEPT_NAME",result.getSchDeptName());
						dataMap.put("startDate",result.getSchStartDate());
						dataMap.put("endDate",result.getSchEndDate());
						if(i1==0){
							result.setSchStartDate(startDate);
						}
						if(i1==schArrangeResults.size()-1){
							result.setSchEndDate(endDate);
						}
						Long intervalDay = DateUtil.signDaysBetweenTowDate(result.getSchStartDate(),result.getSchEndDate());
						dataMap.put("intervalDay",1-intervalDay);
						String resultFlow = result.getResultFlow();
						ResDoctorSchProcess processes = resDoctorProcessBiz.searchByResultFlow(resultFlow);
						String teacherFlow = processes.getHeadUserFlow();
						String teacherName = processes.getHeadUserName();
						dataMap.put("teacherFlow",teacherFlow);
						dataMap.put("teacherName",teacherName);
						dataMap.put("teachingFlow",processes.getTeacherUserFlow());
						dataMap.put("teachingName",processes.getTeacherUserName());
						dataList.add(dataMap);
						i1++;
					}
				}
				int count1=checkDates(GlobalContext.getCurrentUser().getUserFlow(),startDate,endDate,"");
				if(count1>0)
				{
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，与其他考勤记录时间存在重复，请核查！");
				}
				SchDoctorAbsence doctorAbsence = new SchDoctorAbsence();
				doctorAbsence.setAbsenceReson(reason);
				doctorAbsence.setIsRegister(GlobalConstant.RECORD_STATUS_Y);
				doctorAbsence.setAbsenceTypeId(AbsenceTypeEnum.Absenteeism.getId());
				doctorAbsence.setDoctorFlow(user.getUserFlow());
				doctorAbsence.setDoctorName(user.getUserName());
				doctorAbsence.setTrainingSpeId(doctor.getTrainingSpeId());
				doctorAbsence.setSessionNumber(doctor.getSessionNumber());
				String doctorTypeId = doctor.getDoctorTypeId();
				String doctorTypeName = doctor.getDoctorTypeName();
				doctorAbsence.setDoctorTypeId(doctorTypeId);
				doctorAbsence.setDoctorTypeName(doctorTypeName);
				List<Map<String,Object>> mapList = dataList;
				int i2 = 0;
				for(Map<String,Object> map:mapList){
					doctorAbsence.setIntervalDay(map.get("intervalDay").toString());
					doctorAbsence.setSchDeptFlow((String)map.get("SCH_DEPT_FLOW"));
					doctorAbsence.setSchDeptName((String)map.get("SCH_DEPT_NAME"));
					doctorAbsence.setStartDate((String)map.get("startDate"));
					doctorAbsence.setEndDate((String)map.get("endDate"));
					if(i2==0){
						doctorAbsence.setStartDate(startDate);
					}
					if(i2>0){
						doctorAbsence.setAbsenceFlow("");
					}
					if(i2==mapList.size()-1){
						doctorAbsence.setEndDate(endDate);
					}
					doctorAbsence.setTeacherFlow((String)map.get("teacherFlow"));
					doctorAbsence.setTeacherName((String)map.get("teacherName"));
					doctorAbsence.setTeachingFlow((String)map.get("teachingFlow"));
					doctorAbsence.setTeachingName((String)map.get("teachingName"));
					i2++;
					try{
						editSchDoctorAbsence(doctorAbsence,null);
					}catch (RuntimeException re){
						re.printStackTrace();
						throw new RuntimeException("导入失败！第"+ (count+2) +"行"+re.getMessage());
					}
				}
				count ++;
			}
		}
		return count;
	}
}
