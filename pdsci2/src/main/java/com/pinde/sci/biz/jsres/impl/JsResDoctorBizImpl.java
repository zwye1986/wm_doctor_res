package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.util.*;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.UserBizImpl;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.JsresUserBalcklistMapper;
import com.pinde.sci.dao.base.ResBaseMapper;
import com.pinde.sci.dao.base.ResDoctorProcessEvalConfigMapper;
import com.pinde.sci.dao.base.ResJointOrgMapper;
import com.pinde.sci.dao.jsres.JsResDoctorExtMapper;
import com.pinde.sci.dao.jsres.JsResUserBalckListExtMapper;
import com.pinde.sci.enums.jsres.JsResDegreeCategoryEnum;
import com.pinde.sci.enums.jsres.JsResDocTypeEnum;
import com.pinde.sci.enums.pub.UserNationEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.res.ResDocTypeEnum;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.jsres.BackTrainForm;
import com.pinde.sci.form.jsres.JsresDoctorInfoExt;
import com.pinde.sci.form.jsres.UserResumeExtInfoForm;
import com.pinde.sci.model.jsres.*;
import com.pinde.sci.model.mo.*;
import oracle.sql.CLOB;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Service
@Transactional(rollbackFor=Exception.class)
public class JsResDoctorBizImpl implements IJsResDoctorBiz{

	private static Logger logger = LoggerFactory.getLogger(JsResDoctorBizImpl.class);

	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IUserBiz iUserBiz;
	@Autowired
	private IDictBiz dictBiz;	
	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private ResBaseMapper resBaseMapper;
	@Autowired
	private JsResDoctorExtMapper jsresDoctorExtMapper;
	@Autowired
	private JsresUserBalcklistMapper balcklistMapper;
	@Autowired
	private JsResUserBalckListExtMapper balcklistExtMapper;
	@Autowired
	private ResDoctorProcessEvalConfigMapper evalConfigMapper;
	@Autowired
	private ResJointOrgMapper jointOrgMapper;

	@Override
	public int saveDoctorInfo(SysUser user, ResDoctor doctor, UserResumeExtInfoForm userResumeExt,String qtCountry) {

		SysUser sysUser = userBiz.readSysUser(user.getUserFlow());
		if (null!=sysUser && StringUtil.isNotBlank(sysUser.getTrainingTypeId())){
			doctor.setTrainingTypeId(sysUser.getTrainingTypeId());
			doctor.setTrainingTypeName(sysUser.getTrainingTypeName());
		}

		if(StringUtil.isNotBlank(user.getSexId())){
			user.setSexName(UserSexEnum.getNameById(user.getSexId()));
		}else{
			user.setSexName("");
		}
		if(StringUtil.isNotBlank(user.getUserBirthday())){
			user.setUserBirthday(user.getUserBirthday());
		}else{
			user.setUserBirthday("");
		}
		// 民族
		if(StringUtil.isNotBlank(user.getNationId())){
			user.setNationName(UserNationEnum.getNameById(user.getNationId()));
		}else{
			user.setNationName("");
		}
		// 国籍
		String nationalityId = user.getNationalityId() == null ? "" : user.getNationalityId();
		if(StringUtil.isNotBlank(nationalityId)){
			user.setNationalityName(DictTypeEnum.Nationality.getDictNameById(nationalityId));
			if (user.getNationalityId().equals("QT")) {
				user.setNationalityName(qtCountry);
			}
		}else{
			user.setNationalityName("");
		}
		//学历
		if(StringUtil.isNotBlank(user.getEducationId())){
			user.setEducationName(DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
		}else{
//			user.setEducationName("");
		}
		//学位
		if(StringUtil.isNotBlank(user.getDegreeId())){
			user.setDegreeName(DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
		}else{
			user.setDegreeName("");
		}
		
		//证件类型
		if(StringUtil.isNotBlank(user.getCretTypeId())){
			user.setCretTypeName(CertificateTypeEnum.getNameById(user.getCretTypeId()));
		}else{
			user.setCretTypeName("");
		}
		if(StringUtil.isNotBlank(user.getIdNo())){//身份证X大写
			String idNo = user.getIdNo().toUpperCase().trim();
			user.setIdNo(idNo);
		}
		userBiz.saveUser(user);
		//设置当前用户
		GlobalContext.setSessionAttribute(GlobalConstant.CURRENT_USER, user);
		String userFlow = user.getUserFlow();
		//UserResumeExtInfoForm userResumeExt = doctorInfoForm.getUserResumeExt();
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(userFlow);
		if(pubUserResume == null){
			pubUserResume = new PubUserResume();
		}
		String doctorDegreeTypeId = userResumeExt.getDoctorDegreeTypeId();
		if("1".equals(doctorDegreeTypeId)){
			userResumeExt.setDoctorDegreeTypeName("专业型");
		}else if("2".equals(doctorDegreeTypeId)){
			userResumeExt.setDoctorDegreeTypeName("科学型");
		}
		String masterDegreeTypeId = userResumeExt.getMasterDegreeTypeId();
		if("1".equals(masterDegreeTypeId)){
			userResumeExt.setMasterDegreeTypeName("专业型");
		}else if("2".equals(masterDegreeTypeId)){
			userResumeExt.setMasterDegreeTypeName("科学型");
		}
		//JavaBean转换成xml
        // 在读院校
        List<SysDict> dictSchoolList = DictTypeEnum.sysListDictMap.get("GraduateSchool");
        String readingSchoolName = userResumeExt.getReadingSchoolName();
        if(StringUtil.isNotBlank(readingSchoolName)){
            for(SysDict s:dictSchoolList){
                if (s.getDictName().equals(readingSchoolName)) {
                    userResumeExt.setReadingSchoolId(s.getDictId());
					break;
                }
            }
        }
        // 专科毕业院校
        String juniorCollegeSchoolName = userResumeExt.getJuniorCollegeSchoolName();
        if(StringUtil.isNotBlank(juniorCollegeSchoolName)){
            for(SysDict s:dictSchoolList){
                if (s.getDictName().equals(juniorCollegeSchoolName)) {
                    userResumeExt.setJuniorCollegeSchoolId(s.getDictId());
					break;
                }
            }
        }
        // 本科毕业院校
		if(StringUtil.isNotBlank(userResumeExt.getGraduatedName())){
	    	for(SysDict s:dictSchoolList){
	    		if (s.getDictName().equals(userResumeExt.getGraduatedName())) {
	    			userResumeExt.setGraduatedId(s.getDictId());
					break;
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getDegreeId())){//本科学位
	    	List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("UserDegree");
	    	for(SysDict s:dictList){
	    		if (s.getDictId().equals(userResumeExt.getDegreeId())) {
	    			userResumeExt.setDegreeName(s.getDictName());
					break;
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getMasterDegreeId())){//硕士学位
	    	List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("UserDegree");
	    	for(SysDict s:dictList){
	    		if (s.getDictId().equals(userResumeExt.getMasterDegreeId())) {
	    			userResumeExt.setMasterDegreeName(s.getDictName());
					break;
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getMasterGraSchoolName())){//硕士毕业院校
	    	List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("GraduateSchool");
	    	for(SysDict s:dictList){
	    		if (s.getDictName().equals(userResumeExt.getMasterGraSchoolName())) {
	    			userResumeExt.setMasterGraSchoolId(s.getDictId());
					break;
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getDoctorDegreeId())){//博士学位
	    	List<SysDict> dictList= DictTypeEnum.sysListDictMap.get("UserDegree");
	    	for(SysDict s:dictList){
	    		if (s.getDictId().equals(userResumeExt.getDoctorDegreeId())) {
	    			userResumeExt.setDoctorDegreeName(s.getDictName());
					break;
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getDoctorGraSchoolName())){//博士毕业院校
	    	List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("GraduateSchool");
	    	for(SysDict s:dictList){
	    		if (s.getDictName().equals(userResumeExt.getDoctorGraSchoolName())) {
	    			userResumeExt.setDoctorGraSchoolId(s.getDictId());
					break;
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getMedicalHeaithOrgId())){//医疗机构
	    	List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("MedicalHeaithOrg");
	    	for(SysDict s:dictList){
	    		if (s.getDictId().equals(userResumeExt.getMedicalHeaithOrgId())) {
	    			userResumeExt.setMedicalHeaithOrgName(s.getDictName());
					break;
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getHospitalAttrId())){
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("HospitalAttr");
	    	for(SysDict s:dictList){
	    		if (s.getDictId().equals(userResumeExt.getHospitalAttrId())) {
	    			userResumeExt.setHospitalAttrName(s.getDictName());
					break;
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getHospitalCategoryId())){
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("HospitalCategory");
	    	for(SysDict s:dictList){
	    		if (s.getDictId().equals(userResumeExt.getHospitalCategoryId())) {
	    			userResumeExt.setHospitalCategoryName(s.getDictName());
					break;
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getBasicHealthOrgId())){
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("BasicHealthOrg");
			for(SysDict s:dictList){
				if (s.getDictId().equals(userResumeExt.getBasicHealthOrgId())) {
					userResumeExt.setBasicHealthOrgName(s.getDictName());
					break;
				}
			}
		}
		if(StringUtil.isNotBlank(userResumeExt.getBaseAttributeId())){
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("BaseAttribute");
			for(SysDict s:dictList){
				if (s.getDictId().equals(userResumeExt.getBaseAttributeId())) {
					userResumeExt.setBaseAttributeName(s.getDictName());
					break;
				}
			}
		}
        // 毕业专业字典项
        List<SysDict> dictMajorList = DictTypeEnum.sysListDictMap.get("GraduateMajor");
        // 在读专业
        String readingProfession = userResumeExt.getReadingProfession();
        if(StringUtil.isNotBlank(readingProfession)){
            for(SysDict sysDict :dictMajorList){
                if (sysDict.getDictName().equals(readingProfession)) {
                    userResumeExt.setReadingProfessionId(sysDict.getDictId());
					break;
                }
            }
        }
        // 大专毕业专业
        String juniorCollegeProfession = userResumeExt.getJuniorCollegeProfession();
        if(StringUtil.isNotBlank(juniorCollegeProfession)){
            for(SysDict sysDict :dictMajorList){
                if (sysDict.getDictName().equals(juniorCollegeProfession)) {
                    userResumeExt.setJuniorCollegeProfessionId(sysDict.getDictId());
					break;
                }
            }
        }
        // 本科毕业院校
        String specialized = userResumeExt.getSpecialized();
        if(StringUtil.isNotBlank(specialized)){
            for(SysDict sysDict :dictMajorList){
                if (sysDict.getDictName().equals(specialized)) {
                    userResumeExt.setSpecializedId(sysDict.getDictId());
					break;
                }
            }
        }
        // 硕士毕业专业
        String masterMajor = userResumeExt.getMasterMajor();
        if(StringUtil.isNotBlank(masterMajor)){
            for(SysDict sysDict :dictMajorList){
                if (sysDict.getDictName().equals(masterMajor)) {
                    userResumeExt.setMasterMajorId(sysDict.getDictId());
					break;
                }
            }

        }
        // 博士毕业专业
        String doctorMajor = userResumeExt.getDoctorMajor();
        if(StringUtil.isNotBlank(doctorMajor)){
            for(SysDict sysDict :dictMajorList){
                if (sysDict.getDictName().equals(doctorMajor)) {
                    userResumeExt.setDoctorMajorId(sysDict.getDictId());
                    break;
                }
            }

        }
		String xmlContent = JaxbUtil.convertToXml(userResumeExt);
		
		
		pubUserResume.setUserResume(xmlContent);
		userResumeBiz.savePubUserResume(user, pubUserResume);
		//毕业院校
		if(StringUtil.isNotBlank(doctor.getGraduatedName())){
			Map<String,List<SysDict>>  sysListDictMap = DictTypeEnum.sysListDictMap;
	    	List<SysDict> dictList=sysListDictMap.get("GraduateSchool");
	    	for(SysDict s:dictList){
	    		if (s.getDictName().equals(doctor.getGraduatedName())) {
					doctor.setGraduatedId(s.getDictId());
				}
	    	}
		}
//		else{
//			doctor.setGraduatedId("");
//		}
		//人员类型
		if(StringUtil.isNotBlank(doctor.getDoctorTypeId())){
			doctor.setDoctorTypeName(JsResDocTypeEnum.getNameById(doctor.getDoctorTypeId()));
		}else{
			doctor.setDoctorTypeName("");
		}
		//是否是应届生
		if(StringUtil.isNotBlank(doctor.getIsYearGraduate())){
			doctor.setIsYearGraduate(doctor.getIsYearGraduate());
		}else{
			doctor.setIsYearGraduate("");
		}
		//单位等级
		if(StringUtil.isNotBlank(doctor.getWorkOrgLevelId())){
			doctor.setWorkOrgLevelId(doctor.getWorkOrgLevelId());
			doctor.setWorkOrgLevelName(DictTypeEnum.getDictName(DictTypeEnum.BaseLevel, doctor.getWorkOrgLevelId()));
		}else{
			doctor.setWorkOrgLevelId("");
		}
		//学位类型
		if(StringUtil.isNotBlank(doctor.getDegreeCategoryId())){
			doctor.setDegreeCategoryName(JsResDegreeCategoryEnum.getNameById(doctor.getDegreeCategoryId()));
		}else{
			doctor.setDegreeCategoryName("");
		}
		//派送单位或者学校
		if(StringUtil.isNotBlank(doctor.getWorkOrgName())){
			if(JsResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())){
				List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.SendSchool.getId());
				if(sysDictList!=null && !sysDictList.isEmpty()){
					for(SysDict dict:sysDictList){
						if(dict.getDictName().equals(doctor.getWorkOrgName())){
							doctor.setWorkOrgId(dict.getDictId());
						}
					}
				}
			}
			if(JsResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())||JsResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())){
				List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.WorkOrg.getId());
				if(sysDictList!=null && !sysDictList.isEmpty()){
					for(SysDict dict:sysDictList){
						if(dict.getDictName().equals(doctor.getWorkOrgName())){
							doctor.setWorkOrgId(dict.getDictId());
						}
					}
				}
			}
		}
		doctor.setDoctorName(user.getUserName());
        String doctorStatusId = doctor.getDoctorStatusId();
        if (StringUtil.isBlank(doctorStatusId)) {
            // 现网bug：学员在任何状态下，编辑信息，状态会被重置未 未提交，增加判断 状态为空 默认为未提交状态
            doctor.setDoctorStatusId("NotSubmit");
            doctor.setDoctorStatusName("未提交");
        }
		return resDoctorBiz.editDoctor(doctor, user);
	}

	/**
	 * 字典数据校验
	 */
	/*public void   DictionaryDataCheck(UserResumeExtInfoForm userResumeExt){

		// 毕业专业字典项
		List<SysDict> dictMajorList = DictTypeEnum.sysListDictMap.get("GraduateMajor");
		if (dictMajorList != null && dictMajorList.size() > 0) {
			for (SysDict sysDict : dictMajorList) {
				// 在读专业
				String readingProfession = userResumeExt.getReadingProfession();
				if(StringUtil.isNotBlank(readingProfession)){
					if (sysDict.getDictName().equals(readingProfession)) {
						userResumeExt.setReadingProfessionId(sysDict.getDictId());
					} else {
						userResumeExt.setReadingProfessionId(GlobalConstant.OTHER_GRADUATEMAJOR_CODE);
					}
				}
				// 大专毕业专业
				String juniorCollegeProfession = userResumeExt.getJuniorCollegeProfession();
				if(StringUtil.isNotBlank(juniorCollegeProfession)){
					if (sysDict.getDictName().equals(juniorCollegeProfession)) {
						userResumeExt.setJuniorCollegeProfessionId(sysDict.getDictId());
					} else {
						userResumeExt.setJuniorCollegeProfessionId(GlobalConstant.OTHER_GRADUATEMAJOR_CODE);
					}
				}
                // 本科毕业院校
                String specialized = userResumeExt.getSpecialized();
                if(StringUtil.isNotBlank(specialized)){
                    if (sysDict.getDictName().equals(specialized)) {
                        userResumeExt.setSpecializedId(sysDict.getDictId());
                    } else {
                        userResumeExt.setSpecializedId(GlobalConstant.OTHER_GRADUATEMAJOR_CODE);
                    }
                }
                // 硕士毕业专业
				String masterMajor = userResumeExt.getMasterMajor();
				if(StringUtil.isNotBlank(masterMajor)){
					if (sysDict.getDictName().equals(masterMajor)) {
						userResumeExt.setMasterMajorId(sysDict.getDictId());
					} else {
						userResumeExt.setMasterMajorId(GlobalConstant.OTHER_GRADUATEMAJOR_CODE);
					}
				}
				// 博士毕业专业
				String doctorMajor = userResumeExt.getDoctorMajor();
				if(StringUtil.isNotBlank(doctorMajor)){
					if (sysDict.getDictName().equals(doctorMajor)) {
						userResumeExt.setDoctorMajorId(sysDict.getDictId());
					} else {
						userResumeExt.setDoctorMajorId(GlobalConstant.OTHER_GRADUATEMAJOR_CODE);
					}
				}

			}
		}
		// 毕业院校字典项
		List<SysDict> dictSchoolList = DictTypeEnum.sysListDictMap.get("GraduateSchool");
		if (dictSchoolList != null && dictSchoolList.size() > 0) {
			for (SysDict s : dictSchoolList) {
				// 专科毕业院校
				String juniorCollegeSchoolName = userResumeExt.getJuniorCollegeSchoolName();
				if(StringUtil.isNotBlank(juniorCollegeSchoolName)){
					if (s.getDictName().equals(juniorCollegeSchoolName)) {
						userResumeExt.setGraduatedId(s.getDictId());
					} else {
						userResumeExt.setGraduatedId(GlobalConstant.OTHER_GRADUATE_SCHOOL_CODE);
					}
				}
				// 本科毕业院校
				String graduatedName = userResumeExt.getGraduatedName();
				if(StringUtil.isNotBlank(userResumeExt.getGraduatedName())){
					if (s.getDictName().equals(graduatedName)) {
						userResumeExt.setGraduatedId(s.getDictId());
					} else {
						// 本科毕业院校
						userResumeExt.setGraduatedId(GlobalConstant.OTHER_GRADUATE_SCHOOL_CODE);
					}
				}
				// 硕士毕业院校
				String masterGraSchoolName = userResumeExt.getMasterGraSchoolName();
				if(StringUtil.isNotBlank(masterGraSchoolName)){
					if (s.getDictName().equals(masterGraSchoolName)) {
						userResumeExt.setMasterGraSchoolId(s.getDictId());
					} else {
						userResumeExt.setGraduatedId(GlobalConstant.OTHER_GRADUATE_SCHOOL_CODE);
					}
				}
				// 博士毕业院校
				String doctorGraSchoolName = userResumeExt.getDoctorGraSchoolName();
				if(StringUtil.isNotBlank(doctorGraSchoolName)){
					if (s.getDictName().equals(doctorGraSchoolName)) {
						userResumeExt.setDoctorGraSchoolId(s.getDictId());
					} else {
						userResumeExt.setGraduatedId(GlobalConstant.OTHER_GRADUATE_SCHOOL_CODE);
					}
				}

			}

		}

	}*/

	@Override
	public int saveDoctorInfo2(SysUser user, ResDoctor doctor, UserResumeExtInfoForm userResumeExt) {
		//SysUser user = doctorInfoForm.getUser();
		if(StringUtil.isNotBlank(user.getSexId())){
			user.setSexName(UserSexEnum.getNameById(user.getSexId()));
		}else{
			user.setSexName("");
		}
		if(StringUtil.isNotBlank(user.getUserBirthday())){
			user.setUserBirthday(user.getUserBirthday());
		}else{
			user.setUserBirthday("");
		}
		//民族
		if(StringUtil.isNotBlank(user.getNationId())){
			user.setNationName(UserNationEnum.getNameById(user.getNationId()));
		}else{
			user.setNationName("");
		}
		//学历
		if(StringUtil.isNotBlank(user.getEducationId())){
			user.setEducationName(DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
		}else{
//			user.setEducationName("");
		}
		//学位
		if(StringUtil.isNotBlank(user.getDegreeId())){
			user.setDegreeName(DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
		}else{
			user.setDegreeName("");
		}
		//证件类型
		if(StringUtil.isNotBlank(user.getCretTypeId())){
			user.setCretTypeName(CertificateTypeEnum.getNameById(user.getCretTypeId()));
		}else{
			user.setCretTypeName("");
		}
		if(StringUtil.isNotBlank(user.getIdNo())){//身份证X大写
			String idNo = user.getIdNo().toUpperCase().trim();
			user.setIdNo(idNo);
		}
		userBiz.saveUser(user);
		String userFlow = user.getUserFlow();
		//UserResumeExtInfoForm userResumeExt = doctorInfoForm.getUserResumeExt();
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(userFlow);
		if(pubUserResume == null){
			pubUserResume = new PubUserResume();
		}
		//JavaBean转换成xml
		if(StringUtil.isNotBlank(userResumeExt.getGraduatedName())){//本科毕业院校
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("GraduateSchool");
			for(SysDict s:dictList){
				if (s.getDictName().equals(userResumeExt.getGraduatedName())) {
					userResumeExt.setGraduatedId(s.getDictId());
				}
			}
		}
		if(StringUtil.isNotBlank(userResumeExt.getDegreeId())){//本科学位
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("UserDegree");
			for(SysDict s:dictList){
				if (s.getDictId().equals(userResumeExt.getDegreeId())) {
					userResumeExt.setDegreeName(s.getDictName());
				}
			}
		}
		if(StringUtil.isNotBlank(userResumeExt.getMasterDegreeId())){//硕士学位
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("UserDegree");
			for(SysDict s:dictList){
				if (s.getDictId().equals(userResumeExt.getMasterDegreeId())) {
					userResumeExt.setMasterDegreeName(s.getDictName());
				}
			}
		}
		if(StringUtil.isNotBlank(userResumeExt.getMasterGraSchoolName())){//硕士毕业院校
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("GraduateSchool");
			for(SysDict s:dictList){
				if (s.getDictName().equals(userResumeExt.getMasterGraSchoolName())) {
					userResumeExt.setMasterGraSchoolId(s.getDictId());
				}
			}
		}
		if(StringUtil.isNotBlank(userResumeExt.getDoctorDegreeId())){//博士学位
			List<SysDict> dictList= DictTypeEnum.sysListDictMap.get("UserDegree");
			for(SysDict s:dictList){
				if (s.getDictId().equals(userResumeExt.getDoctorDegreeId())) {
					userResumeExt.setDoctorDegreeName(s.getDictName());
				}
			}
		}
		if(StringUtil.isNotBlank(userResumeExt.getDoctorGraSchoolName())){//博士毕业院校
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("GraduateSchool");
			for(SysDict s:dictList){
				if (s.getDictName().equals(userResumeExt.getDoctorGraSchoolName())) {
					userResumeExt.setDoctorGraSchoolId(s.getDictId());
				}
			}
		}
		if(StringUtil.isNotBlank(userResumeExt.getMedicalHeaithOrgId())){//医疗机构
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("MedicalHeaithOrg");
			for(SysDict s:dictList){
				if (s.getDictId().equals(userResumeExt.getMedicalHeaithOrgId())) {
					userResumeExt.setMedicalHeaithOrgName(s.getDictName());
				}
			}
		}
		if(StringUtil.isNotBlank(userResumeExt.getHospitalAttrId())){
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("HospitalAttr");
			for(SysDict s:dictList){
				if (s.getDictId().equals(userResumeExt.getHospitalAttrId())) {
					userResumeExt.setHospitalAttrName(s.getDictName());
				}
			}
		}
		if(StringUtil.isNotBlank(userResumeExt.getHospitalCategoryId())){
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("HospitalCategory");
			for(SysDict s:dictList){
				if (s.getDictId().equals(userResumeExt.getHospitalCategoryId())) {
					userResumeExt.setHospitalCategoryName(s.getDictName());
				}
			}
		}
		if(StringUtil.isNotBlank(userResumeExt.getBasicHealthOrgId())){
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("BasicHealthOrg");
			for(SysDict s:dictList){
				if (s.getDictId().equals(userResumeExt.getBasicHealthOrgId())) {
					userResumeExt.setBasicHealthOrgName(s.getDictName());
				}
			}
		}
		if(StringUtil.isNotBlank(userResumeExt.getBaseAttributeId())){
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("BaseAttribute");
			for(SysDict s:dictList){
				if (s.getDictId().equals(userResumeExt.getBaseAttributeId())) {
					userResumeExt.setBaseAttributeName(s.getDictName());
				}
			}
		}
		String xmlContent = JaxbUtil.convertToXml(userResumeExt);


		pubUserResume.setUserResume(xmlContent);
		userResumeBiz.savePubUserResume(user, pubUserResume);
		//毕业院校
		if(StringUtil.isNotBlank(doctor.getGraduatedName())){
			Map<String,List<SysDict>>  sysListDictMap = DictTypeEnum.sysListDictMap;
			List<SysDict> dictList=sysListDictMap.get("GraduateSchool");
			for(SysDict s:dictList){
				if (s.getDictName().equals(doctor.getGraduatedName())) {
					doctor.setGraduatedId(s.getDictId());
				}
			}
		}
//		else{
//			doctor.setGraduatedId("");
//		}
		//人员类型
		if(StringUtil.isNotBlank(doctor.getDoctorTypeId())){
			doctor.setDoctorTypeName(JsResDocTypeEnum.getNameById(doctor.getDoctorTypeId()));
		}else{
			doctor.setDoctorTypeName("");
		}
		//是否是应届生
		if(StringUtil.isNotBlank(doctor.getIsYearGraduate())){
			doctor.setIsYearGraduate(doctor.getIsYearGraduate());
		}else{
			doctor.setIsYearGraduate("");
		}
		//单位等级
		if(StringUtil.isNotBlank(doctor.getWorkOrgLevelId())){
			doctor.setWorkOrgLevelId(doctor.getWorkOrgLevelId());
			doctor.setWorkOrgLevelName(DictTypeEnum.getDictName(DictTypeEnum.BaseLevel, doctor.getWorkOrgLevelId()));
		}else{
			doctor.setWorkOrgLevelId("");
		}
		//学位类型
		if(StringUtil.isNotBlank(doctor.getDegreeCategoryId())){
			doctor.setDegreeCategoryName(JsResDegreeCategoryEnum.getNameById(doctor.getDegreeCategoryId()));
		}else{
			doctor.setDegreeCategoryName("");
		}
		//派送单位或者学校
		if(StringUtil.isNotBlank(doctor.getWorkOrgName())){
			if(JsResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())){
				List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.SendSchool.getId());
				if(sysDictList!=null && !sysDictList.isEmpty()){
					for(SysDict dict:sysDictList){
						if(dict.getDictName().equals(doctor.getWorkOrgName())){
							doctor.setWorkOrgId(dict.getDictId());
						}
					}
				}
			}
			if(JsResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())||JsResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())){
				List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.WorkOrg.getId());
				if(sysDictList!=null && !sysDictList.isEmpty()){
					for(SysDict dict:sysDictList){
						if(dict.getDictName().equals(doctor.getWorkOrgName())){
							doctor.setWorkOrgId(dict.getDictId());
						}
					}
				}
			}
		}
		doctor.setDoctorName(user.getUserName());
		return resDoctorBiz.editDoctor(doctor, user);
	}

	@Override
	public String checkImg(MultipartFile file) {
		List<String> mimeList = new ArrayList<String>();
		if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
			mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
		}
		List<String> suffixList = new ArrayList<String>();
		if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
			suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix").toLowerCase()).split(","));
		}
		String fileType = file.getContentType();//MIME类型;
		String fileName = file.getOriginalFilename();//文件名
		String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
		if(!(mimeList.contains(fileType) &&  suffixList.contains(suffix.toLowerCase()))){
			return "请上传 "+InitConfig.getSysCfg("inx_image_support_suffix")+"格式的文件";
		}
		long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
		if(file.getSize() > limitSize*1024*1024){
			return GlobalConstant.UPLOAD_IMG_SIZE_ERROR +limitSize +"M！" ;
		}
		return GlobalConstant.FLAG_Y;//可执行保存
	}

	@Override
	public String checkIdCardImg(MultipartFile file) {
		if(file!=null){
			try{
				BufferedImage image = ImageIO.read(file.getInputStream());
				//如果image=null 表示上传的不是图片格式
				if (image != null) {
					int imageWidth = image.getWidth(); //获取图片宽度，单位px
					int imageHeight = image.getHeight(); //获取图片高度，单位px
					if(imageWidth < 640 || imageHeight < 480){
						return GlobalConstant.FILE_PIXEL_ERROR;
					}
				}
			} catch (IOException e){
				logger.error("JsResDoctorBizImpl checkIdCardImg error:{}",e);
			}
			long limitSize = 300;//图片大小限制300k
			if(file.getSize() > limitSize*1024){
				return GlobalConstant.UPLOAD_IMG_SIZE_ERROR +limitSize +"K！" ;
			}
			// 可执行保存
			return GlobalConstant.FLAG_Y;
		} else {
			return GlobalConstant.FLAG_N;
		}

	}

	@Override
	public String checkFile(MultipartFile file) {
		List<String> mimeList = new ArrayList<String>();
		if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")))){
			mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")).split(","));
		}
		List<String> suffixList = new ArrayList<String>();
		if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix")))){
			suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix").toLowerCase()).split(","));
		}
		String fileType = file.getContentType();//MIME类型;
		String fileName = file.getOriginalFilename();//文件名
		String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
		if(!(mimeList.contains(fileType) &&  suffixList.contains(suffix.toLowerCase()))){
			return "请上传 "+InitConfig.getSysCfg("res_file_support_suffix")+"格式的文件";
		}
//        long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
//        if(file.getSize() > limitSize*1024*1024){
//            return GlobalConstant.UPLOAD_IMG_SIZE_ERROR +limitSize +"M！" ;
//        }
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
				logger.error("保存文件失败！", e);
				throw new RuntimeException("保存文件失败！");
			}
			
			//删除原文件
			if(StringUtil.isNotBlank(oldFolderName)){
				try {
					oldFolderName = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName;
					File imgFile = new File(oldFolderName);
					if(imgFile.exists()){
						imgFile.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("删除文件失败！");
				}
			}
			path = folderName + "/"+dateString+"/"+originalFilename;
			FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
			String localFilePath=fileDir+File.separator+originalFilename;
			String ftpDir= folderName+File.separator +dateString ;
			String ftpFileName=originalFilename;

			System.out.println("===============FTP上传开始 ============= localFilePath："+localFilePath);
			ftpHelperUtil.uploadFile(localFilePath,ftpDir,ftpFileName);
			System.out.println("===============FTP上传开始 ============= localFilePath："+localFilePath);
		}
		return path;
	}

//	@Override
//	public List<ResDoctorExt> searchDoctorExtsList(ResDoctor doctor,SysUser user, SysOrg sysOrg,List<String>jointOrgFlowList) {
//		Map<String, Object> paramMap=new HashMap<String, Object>();
//		paramMap.put("user", user);
//		paramMap.put("doctor", doctor);
//		paramMap.put("sysOrg", sysOrg);
//		paramMap.put("jointOrgFlowList", jointOrgFlowList);
//		List<ResDoctorExt> doctorExtList = doctorExtMapper.searchResDoctorUserPassed(paramMap);
//		return doctorExtList;
//	}


	@Override
	public ResSchProcessExpress searResRecZhuZhi(String formFileName, String recFlow,
												 String operUserFlow, String roleFlag, String processFlow, String recordFlow,
												 String cksh, HttpServletRequest req) {
		operUserFlow = StringUtil.defaultIfEmpty(operUserFlow,GlobalContext.getCurrentUser().getUserFlow());
		SysUser sysUser=iUserBiz.readSysUser(operUserFlow);
		ResDoctorSchProcess resDoctorSchProcess=iResDoctorProcessBiz.read(processFlow);
		String recTypeId=ResRecTypeEnum.getNameById(formFileName);
		String currDate=DateUtil.getCurrDateTime();
//		ResRec rec=new ResRec();
		ResSchProcessExpress rec = new ResSchProcessExpress();
		rec.setRecFlow(recFlow);
		rec.setOrgFlow(sysUser.getOrgFlow());
		rec.setOrgName(sysUser.getOrgName());
		rec.setDeptFlow(resDoctorSchProcess.getDeptFlow());
		rec.setDeptName(resDoctorSchProcess.getDeptName());
		rec.setRecTypeId(formFileName);
		rec.setRecTypeName(recTypeId);
		rec.setRecVersion("2.0");
		rec.setOperTime(currDate);
		rec.setOperUserFlow(operUserFlow);
		rec.setOperUserName(sysUser.getUserName());
		rec.setProcessFlow(processFlow);
		rec.setSchRotationDeptFlow(recordFlow);
		rec.setRecForm("jsst");
		if(StringUtil.isNotBlank(req.getParameter("auditStatusId")))
			rec.setAuditStatusId(req.getParameter("auditStatusId"));
		if(StringUtil.isNotBlank(req.getParameter("headAuditStatusId")))
			rec.setHeadAuditStatusId(req.getParameter("headAuditStatusId"));
		String recContent=getXmlByRequest(req,formFileName);
		rec.setRecContent(recContent);
		if(StringUtil.isBlank(recFlow)) {
			if (GlobalConstant.FLAG_Y.equals(cksh)) {
				rec.setManagerAuditUserFlow("Y");
			}
		}
		return rec;
	}
	//根据request获取这次表单的xml
		private String getXmlByRequest(HttpServletRequest request,String rootName){
			if(request!=null){
				rootName = StringUtil.defaultIfEmpty(rootName,"root");
				Map<String,String[]> paramsMap = request.getParameterMap();
				if(paramsMap!=null && !paramsMap.isEmpty()){
					//创建xmldom对象和根节点
					Document doc = DocumentHelper.createDocument();
					Element root = doc.addElement(rootName);
					
					for(String key : paramsMap.keySet()){
						String[] vs = paramsMap.get(key);
						
						String vss = "";
						
						String idCsVv = request.getParameter(key+"_name");
						
						if(vs!=null && vs.length>0){
							vss = vs[0];
						}
						//开始创建xml子节点
						Element currEle = root.addElement(key);
						if(idCsVv==null){
							currEle.setText(vss);
						}else{
							currEle.addAttribute("id",vss);
							currEle.setText(idCsVv);
						}
					}
					return doc.asXML();
				}
			}
			return null;
		}


		@Override
		public void exportForBack(Map<Object, Object> backInfoMap,HttpServletResponse response,String flag) throws Exception {
			//创建工作簿
			HSSFWorkbook wb = new HSSFWorkbook();  
			// 为工作簿添加sheet  
		    HSSFSheet sheet = wb.createSheet("sheet1"); 
		    //定义将用到的样式 
		    HSSFCellStyle styleCenter = wb.createCellStyle(); //居中 
		    styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    
		    HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		    styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		    styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		    
		    HSSFCellStyle stylevwc = wb.createCellStyle(); //居中 
		    stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		    
		    //列宽自适应
		    HSSFRow rowDep = sheet.createRow(0);
		  
		    String[] titles = new String[]{
					"培训基地",
					"培训届别",
					"学员姓名",
					"培训专业",
					"退培主要原因",
					"相关措施和规定",
					"学员去向",
					"备注(培训基地意见)",
				};
		    HSSFCell cellTitle = null;
		    for(int i = 0 ; i<titles.length ; i++){
		    	cellTitle = rowDep.createCell(i);
		    	cellTitle.setCellValue(titles[i]);  
		    	cellTitle.setCellStyle(styleCenter);
			 }
		    int rownum = 1;
		    List<ResRec> resRecs =(List<ResRec>) backInfoMap.get(GlobalConstant.FLAG_Y);
		    if(resRecs!=null&&!resRecs.isEmpty()){
		    	for(ResRec sd : resRecs){
		    		BackTrainForm form =(BackTrainForm) backInfoMap.get(sd.getRecFlow());
		    		HSSFRow rowDepts= sheet.createRow(rownum);
		    		HSSFCell cell = rowDepts.createCell(0);
		    		cell.setCellValue(sd.getOrgName());
		    		cell.setCellStyle(styleCenter);
		    		HSSFCell cellOne = rowDepts.createCell(1);
		    		cellOne.setCellValue(form.getSessionNumber());
		    		cellOne.setCellStyle(styleCenter);
		    		HSSFCell cellTWo = rowDepts.createCell(2);
		    		cellTWo.setCellValue(sd.getOperUserName());
		    		cellTWo.setCellStyle(styleCenter);
		    		HSSFCell cellThr = rowDepts.createCell(3);
		    		cellThr.setCellValue(form.getTrainSpe());
		    		cellThr.setCellStyle(styleCenter);
		    		HSSFCell cellFou = rowDepts.createCell(4);
		    		String reason="";
		    		if(StringUtil.isNotBlank(form.getReason())){
		    			reason=form.getReasonName()+"("+form.getReason()+")";
		    		}else{
		    			reason=form.getReasonName();
		    		}
		    		cellFou.setCellValue(reason);
		    		cellFou.setCellStyle(styleCenter);
		    		HSSFCell cellFiv = rowDepts.createCell(5);
		    		String policy="";
		    		if(StringUtil.isNotBlank(form.getPolicy())){
		    			policy=form.getPolicyName()+"("+form.getPolicy()+")";
		    		}else{
		    			policy=form.getPolicyName();
		    		}
		    		cellFiv.setCellValue(policy);
		    		cellFiv.setCellStyle(styleCenter);
		    		HSSFCell cellSix = rowDepts.createCell(6);
		    		cellSix.setCellValue(form.getDispositon());
		    		cellSix.setCellStyle(styleCenter);
		    		HSSFCell cellSev = rowDepts.createCell(7);
		    		cellSev.setCellValue(form.getRemark());
		    		cellSev.setCellStyle(styleCenter);
		    		rownum++;
			    	}
		    	}
		    	if(GlobalConstant.FLAG_Y.equals(flag)){
		    		HSSFRow rowDepts= sheet.createRow(rownum);
		    		HSSFCell cell = rowDepts.createCell(0);
		    		String value="";
		    		value="合计退培："+resRecs.size()+" "+"退培比例："+backInfoMap.get(GlobalConstant.FLAG_N);
		    		cell.setCellValue(value);
		    		cell.setCellStyle(styleCenter);
		    		sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,0,7));
		    	}
		    
		    String fileName = "退培信息一览表.xls";
		    fileName = URLEncoder.encode(fileName, "UTF-8");
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); 
		    response.setContentType("application/octet-stream;charset=UTF-8");
		    wb.write(response.getOutputStream());
			
		}

		@Override
		public void exportForBack2(List<ResDocotrDelayTeturn> list,Map<Object, Object> backInfoMap,HttpServletResponse response,String flag) throws Exception {
			//创建工作簿
			HSSFWorkbook wb = new HSSFWorkbook();
			// 为工作簿添加sheet
		    HSSFSheet sheet = wb.createSheet("sheet1");
		    //定义将用到的样式
		    HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		    styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		    HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		    styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		    styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		    HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		    stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		    //列宽自适应
		    HSSFRow rowDep = sheet.createRow(0);

		    String[] titles = new String[]{
					"培训基地",
					"培训届别",
					"学员姓名",
					"培训专业",
					"退培主要原因",
					"相关措施和规定",
					"学员去向",
					"备注(培训基地意见)",
					"省厅审核结果",
					"省厅意见",
				};
		    HSSFCell cellTitle = null;
		    for(int i = 0 ; i<titles.length ; i++){
		    	cellTitle = rowDep.createCell(i);
		    	cellTitle.setCellValue(titles[i]);
		    	cellTitle.setCellStyle(styleCenter);
			 }
		    int rownum = 1;
		    if(list != null && !list.isEmpty()){
		    	for(ResDocotrDelayTeturn sd : list){
		    		HSSFRow rowDepts= sheet.createRow(rownum);
		    		HSSFCell cell = rowDepts.createCell(0);
		    		cell.setCellValue(sd.getOrgName());
		    		cell.setCellStyle(styleCenter);
		    		HSSFCell cellOne = rowDepts.createCell(1);
		    		cellOne.setCellValue(sd.getSessionNumber());
		    		cellOne.setCellStyle(styleCenter);
		    		HSSFCell cellTWo = rowDepts.createCell(2);
		    		cellTWo.setCellValue(sd.getDoctorName());
		    		cellTWo.setCellStyle(styleCenter);
		    		HSSFCell cellThr = rowDepts.createCell(3);
		    		cellThr.setCellValue(sd.getTrainingSpeName());
		    		cellThr.setCellStyle(styleCenter);
		    		HSSFCell cellFou = rowDepts.createCell(4);
		    		String reason="";
		    		if(StringUtil.isNotBlank(sd.getReason())){
		    			reason=sd.getReasonName() + "(" + sd.getReason() + ")";
		    		}else{
		    			reason=sd.getReasonName();
		    		}
		    		cellFou.setCellValue(reason);
		    		cellFou.setCellStyle(styleCenter);
		    		HSSFCell cellFiv = rowDepts.createCell(5);
		    		String policy="";
		    		if(StringUtil.isNotBlank(sd.getPolicy())){
		    			policy=sd.getPolicyName() + "(" + sd.getPolicy() + ")";
		    		}else{
		    			policy=sd.getPolicyName();
		    		}
		    		cellFiv.setCellValue(policy);
		    		cellFiv.setCellStyle(styleCenter);
		    		HSSFCell cellSix = rowDepts.createCell(6);
		    		cellSix.setCellValue(sd.getDispositon());
		    		cellSix.setCellStyle(styleCenter);
		    		HSSFCell cellSev = rowDepts.createCell(7);
		    		cellSev.setCellValue(sd.getRemark());
		    		cellSev.setCellStyle(styleCenter);
					HSSFCell cellEei = rowDepts.createCell(8);
					cellEei.setCellValue(sd.getAuditStatusName());
					cellEei.setCellStyle(styleCenter);
					HSSFCell cellnine = rowDepts.createCell(9);
					String auditOpinion = sd.getAuditOpinion();
					if(!"2".equals(sd.getAuditStatusId())){
						String modifyTime = sd.getModifyTime();
						modifyTime = DateUtil.transDateTime(modifyTime);
						if (StringUtil.isBlank(auditOpinion)){
							auditOpinion = "";
						}
						auditOpinion += " (审核时间："+ modifyTime +")";
					}
					cellnine.setCellValue(auditOpinion);
					cellnine.setCellStyle(styleCenter);
		    		rownum++;
			    	}
		    	}
		    	if(GlobalConstant.FLAG_Y.equals(flag)){
		    		HSSFRow rowDepts= sheet.createRow(rownum);
		    		HSSFCell cell = rowDepts.createCell(0);
		    		String value="";
		    		value="合计退培："+list.size()+" "+"退培比例："+backInfoMap.get(GlobalConstant.FLAG_N);
		    		cell.setCellValue(value);
		    		cell.setCellStyle(styleCenter);
		    		sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,0,7));
		    	}

		    String fileName = "退培信息一览表.xls";
		    fileName = URLEncoder.encode(fileName, "UTF-8");
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		    response.setContentType("application/octet-stream;charset=UTF-8");
		    wb.write(response.getOutputStream());

		}

    @SuppressWarnings("deprecation")
    @Override
    public void exportForDetail(List<JsDoctorInfoExt> doctorInfoExts, HttpServletResponse response) throws Exception {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        //列宽自适应
        HSSFRow rowDep = sheet.createRow(0);//第一行
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));//合并单元格
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(stylevwc);
        cellOne.setCellValue("培训基地");
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 19));//合并单元格
        HSSFCell cell = rowDep.createCell(1);
        cell.setCellStyle(stylevwc);
        cell.setCellValue("学生基本信息");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 20, 35));
        HSSFCell cellTwo = rowDep.createCell(20);
        cellTwo.setCellStyle(styleCenter);
        cellTwo.setCellValue("学历信息");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 36, 46));
        HSSFCell cellThree = rowDep.createCell(36);
        cellThree.setCellStyle(styleCenter);
        cellThree.setCellValue("单位信息");

        HSSFRow rowTwo = sheet.createRow(1);//第二行
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 19, 22));//合并单元格
        HSSFCell cellFour = rowTwo.createCell(19);
        cellFour.setCellStyle(styleCenter);
        cellFour.setCellValue("本科阶段");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 23, 28));//合并单元格
        HSSFCell cellFive = rowTwo.createCell(23);
        cellFive.setCellStyle(styleCenter);
        cellFive.setCellValue("硕士研究生阶段");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 30, 35));//合并单元格
        HSSFCell cellSix = rowTwo.createCell(30);
        cellSix.setCellStyle(styleCenter);
        cellSix.setCellValue("博士研究生阶段");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 36, 37));//合并单元格
        HSSFCell cellSeven = rowTwo.createCell(36);
        cellSeven.setCellStyle(styleCenter);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 38, 40));//合并单元格
        HSSFCell cellEight = rowTwo.createCell(38);
        cellEight.setCellStyle(styleCenter);
        cellEight.setCellValue("在“医疗卫生机构”选择“医院”则填");
        HSSFCell cellNine = rowTwo.createCell(10);
        cellNine.setCellStyle(styleCenter);
        cellNine.setCellValue("在“医疗卫生机构”选择“基层医疗卫生机构”则填");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 42, 44));//合并单元格
        HSSFCell cellTen = rowTwo.createCell(42);
        cellTen.setCellStyle(styleCenter);

        HSSFRow rowThree = sheet.createRow(2);//第三行
        String[] titles = new String[]{
                "基地名称",
                "姓名",
                "性别",
                "年龄",
                "证件类型",
                "证件号",
                "民族",
                "手机号",
                "邮箱",
                "QQ（非必填）",
                "往届/应届",
                "培训专业",
                "是否执业医师",
                "如是执业医师，请填写执业医师资格证号",
                "参培年份",
				"规培年限",
                "实际培训开始时间（****年**月）",
                "培训状态",
                "退培/延期原因",
                "学员类型",
                "毕业学校",
                "毕业年份",
                "毕业专业",
                "学历",
                "是否全科定向生",
                "学位",
                "是否硕士研究生",
                "毕业学校",
                "毕业年份",
                "毕业专业",
                "学位",
                "学位类型",
                "是否博士研究生",
                "毕业学校",
                "毕业年份",
                "毕业专业",
                "学位",
                "学位类型",
                "工作单位（与单位公章对应的官方全称）",
                "医疗卫生机构",
                "医院属性",
                "医院类别",
                "单位性质",
                "基层医疗卫生机构",
                "是否在协同单位培训",
                "协同单位（与单位公章对应的官方全称）",
                "协同单位性质",
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowThree.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 1 * 156);
        }

        int rowNum = 3;
        String[] dataList = null;
        if (doctorInfoExts != null && !doctorInfoExts.isEmpty()) {
            for (int i = 0; i < doctorInfoExts.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行

                JsDoctorInfoExt doctorInfoExt = doctorInfoExts.get(i);
                //培训基地
                String orgName = "";
                String joinName = "";
                String jointFlag = "";
                List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
                Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
                if (jointOrgs != null && !jointOrgs.isEmpty()) {
                    for (Map<String, Object> en : jointOrgs) {
                        Object key = en.get("key");
                        Object value = en.get("value");
                        orgAndJointNameMap.put(key, value);
                    }
                }
                if (orgAndJointNameMap.containsKey(doctorInfoExt.getOrgFlow())) {
                    orgName = (String) orgAndJointNameMap.get(doctorInfoExt.getOrgFlow());
                    joinName = doctorInfoExt.getOrgName();
                    jointFlag = "是";
                } else {
                    jointFlag = "否";
                    orgName = doctorInfoExt.getOrgName();
                }

                String age ="";
				if(doctorInfoExt.getSysUser()!=null&&StringUtil.isNotBlank(doctorInfoExt.getSysUser().getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(doctorInfoExt.getSysUser().getUserBirthday().substring(0, 4))) + "";
				}
                SysUser sysUser = doctorInfoExt.getSysUser();
                ResDoctor doctor = doctorInfoExt.getResDoctor();

                String CretType = "";
                if (!CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
                    CretType = "其他";
                } else {
                    CretType = "身份证";
                }
                String isYearGraduate = "";
                if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
                    isYearGraduate = "应届";
                } else {
                    isYearGraduate = "往届";
                }

                String recruitDate = doctorInfoExt.getRecruitDate().substring(0, 4) + "年" + doctorInfoExt.getRecruitDate().substring(5, 7) + "月";
                //解析xml
                PubUserResume userResume = doctorInfoExt.getUserResume();
                UserResumeExtInfoForm userResumeExt = null;
                userResumeExt = userResumeBiz.converyToJavaBean(userResume.getUserResume(), UserResumeExtInfoForm.class);

                //是否是执业医师和编号
                String qualificationFlag = "";
                if (StringUtil.isNotBlank(userResumeExt.getQualificationMaterialCode())) {
                    qualificationFlag = "是";
                } else {
                    userResumeExt.setQualificationMaterialCode("");
                }
                //研究生
                String masterFlag = "";
                if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
                    userResumeExt.setMasterDegreeName("");
                    userResumeExt.setMasterDegreeTypeName("");
                    userResumeExt.setMasterGraSchoolName("");
                    userResumeExt.setMasterGraTime("");
                    userResumeExt.setMasterMajor("");
                    masterFlag = "否";
                } else {
                    masterFlag = "是";
                }
                //博士
                String doctorFlag = "";
                if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
                    userResumeExt.setDoctorDegreeName("");
                    userResumeExt.setDoctorDegreeTypeName("");
                    userResumeExt.setDoctorGraSchoolName("");
                    userResumeExt.setDoctorGraTime("");
                    userResumeExt.setDoctorMajor("");
                    doctorFlag = "否";
                } else {
                    doctorFlag = "是";
                }
                //工作单位
                String property = "";
				if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())||ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
                    ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
                    if (resBase != null && jointFlag.equals("是")) {
                        property = resBase.getBaseGradeName();
                    }
                    if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"1".equals(userResumeExt.getMedicalHeaithOrgId())) {
                        userResumeExt.setHospitalAttrName("");
                        userResumeExt.setHospitalCategoryName("");
                        userResumeExt.setBaseAttributeName("");
                    }
                    if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || "3".equals(userResumeExt.getMedicalHeaithOrgId())) {
                        userResumeExt.setBasicHealthOrgName("");
                    }

                } else {
                    doctor.setWorkOrgName("");
                    userResumeExt.setMedicalHeaithOrgName("");
                }
                //研究生毕业时间
                String masterGraTime = "";
                if (StringUtil.isNotBlank(userResumeExt.getMasterGraTime())) {
                    masterGraTime = userResumeExt.getMasterGraTime().substring(0, 4);
                }
                //博士毕业时间
                String doctorGraTime = "";
                if (StringUtil.isNotBlank(userResumeExt.getDoctorGraTime())) {
                    doctorGraTime = userResumeExt.getDoctorGraTime().substring(0, 4);
                }
                //本科毕业时间
                String graduationTime = "";
                if (StringUtil.isNotBlank(userResumeExt.getGraduationTime())) {
                    graduationTime = userResumeExt.getGraduationTime().substring(0, 4);
                }
				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				//规培年限
				String trainYear = "";
				trainYear = doctorInfoExt.getTrainYear();
				switch (trainYear){
					case "OneYear":{trainYear="一年";break;}
					case "TwoYear":{trainYear="两年";break;}
					case "ThreeYear":{trainYear="三年";break;}
				}
                dataList = new String[]{
                        orgName,
                        sysUser.getUserName(),
                        sysUser.getSexName(),
                        age,
                        CretType,
                        sysUser.getIdNo(),
                        sysUser.getNationName(),
                        sysUser.getUserPhone(),
                        sysUser.getUserEmail(),
                        sysUser.getUserQq(),
                        isYearGraduate,
                        doctor.getTrainingSpeName(),
                        qualificationFlag,
                        userResumeExt.getQualificationMaterialCode(),
                        doctorInfoExt.getSessionNumber(),
						trainYear,
                        recruitDate,

                        "在培",//医师状态
                        "",

                        doctor.getDoctorTypeName(),

                        userResumeExt.getGraduatedName(),
                        graduationTime,
                        userResumeExt.getSpecialized(),
                        sysUser.getEducationName(),
						isGeneralOrderOrientationTrainee,
                        userResumeExt.getDegreeName(),

                        masterFlag,
                        userResumeExt.getMasterGraSchoolName(),
                        masterGraTime,
                        userResumeExt.getMasterMajor(),
                        userResumeExt.getMasterDegreeName(),
                        userResumeExt.getMasterDegreeTypeName(),

                        doctorFlag,
                        userResumeExt.getDoctorGraSchoolName(),
                        doctorGraTime,
                        userResumeExt.getDoctorMajor(),
                        userResumeExt.getDoctorDegreeName(),
                        userResumeExt.getDoctorDegreeTypeName(),

                        doctor.getWorkOrgName(),
                        userResumeExt.getMedicalHeaithOrgName(),

                        userResumeExt.getHospitalAttrName(),
                        userResumeExt.getHospitalCategoryName(),
                        userResumeExt.getBaseAttributeName(),

                        userResumeExt.getBasicHealthOrgName(),

                        jointFlag,
                        joinName,
                        property
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(dataList[j]);
                }
            }
        }
        String fileName = "学生信息一览表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());

    }

    @SuppressWarnings("deprecation")
    @Override
    public void exportForDetail2(List<JsDoctorInfoExt> doctorInfoExts, String titleYear,HttpServletResponse response) throws Exception {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        //列宽自适应
        HSSFRow rowThree = sheet.createRow(0);//第三行
        String[] titles = new String[]{
				"培训基地",
				"*姓名",
				"*性别",
				"*出生日期（yyyy-mm-dd）",
				"*身份证件类型",
				"#身份证件号码",
				"*国家或地区",
				"*民族",
				"*手机号",
				"邮箱",
				"QQ（非必填）",
				"*往届/应届",
				"*培训专业",
				"*是否通过全国医师资格考试",
				"执业医师资格证号",
				"*招收年度",
				"*实际培训开始时间（yyyy-mm）",
				"*是否为对口支援计划住院医师",
				"#对口支援计划住院医师送出单位（请填全称）",
				"*培训年限核定",
				"*学员类型",
				"*毕业学校（本科）",
				"*毕业年份（本科）",
				"*毕业专业（本科）",
				"*是否全科订单定向学员",
				"*学位（本科）",
				"*是否硕士研究生",
				"#毕业院校（硕士）",
				"#毕业年份（硕士）",
				"#毕业专业（硕士）",
				"#学位（硕士）",
				"#学位类型（硕士）",
				"*是否博士研究生",
				"#毕业院校（博士）",
				"#毕业年份（博士）",
				"#毕业专业（博士）",
				"#学位（博士）",
				"#学位类型（博士）",
				"#工作单位（与单位公章对应的官方全称）",
				"#医院级别",
				"#医院等次",
				"#医疗卫生机构类别",
				"#医疗卫生机构隶属关系",
				"*是否在协同单位培训",
				"#协同单位（与单位公章对应的官方全称）",
				"#协同医院级别",
				"#协同医院等次",
				"#医疗卫生机构类别"
		};
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowThree.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 1 * 156);
        }

        int rowNum = 1;
        String[] dataList = null;
        if (doctorInfoExts != null && !doctorInfoExts.isEmpty()) {
            for (int i = 0; i < doctorInfoExts.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行

                JsDoctorInfoExt doctorInfoExt = doctorInfoExts.get(i);
                //培训基地
                String orgName = "";
                String joinName = "";
                String jointFlag = "";
                List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
                Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
                if (jointOrgs != null && !jointOrgs.isEmpty()) {
                    for (Map<String, Object> en : jointOrgs) {
                        Object key = en.get("key");
                        Object value = en.get("value");
                        orgAndJointNameMap.put(key, value);
                    }
                }
                if (orgAndJointNameMap.containsKey(doctorInfoExt.getOrgFlow())) {
                    orgName = (String) orgAndJointNameMap.get(doctorInfoExt.getOrgFlow());
                    joinName = doctorInfoExt.getOrgName();
                    jointFlag = "是";
                } else {
                    jointFlag = "否";
                    orgName = doctorInfoExt.getOrgName();
                }

                String age ="";
				if(doctorInfoExt.getSysUser()!=null&&StringUtil.isNotBlank(doctorInfoExt.getSysUser().getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(doctorInfoExt.getSysUser().getUserBirthday().substring(0, 4))) + "";
				}
                SysUser sysUser = doctorInfoExt.getSysUser();
                ResDoctor doctor = doctorInfoExt.getResDoctor();

                String CretType = "";
                String area = "";
				String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
				if (CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
					CretType = "居民身份证";
					area="中国大陆";
                }
//				else if (CertificateTypeEnum.Junguanzheng.getId().equals(sysUser.getCretTypeId())) {
//					CretType = "军队证件";
//					area="中国大陆";
//                }
				else if (CertificateTypeEnum.Passport.getId().equals(sysUser.getCretTypeId())) {
					CretType = "护照";
					area="";
                }else if (CertificateTypeEnum.HongKongMacao.getId().equals(cretTypeId)) {
					CretType = "港澳居民来往内地通行证";
					area="";
				}else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "台湾居民来往内地通行证";
					area="";
				}else {
					CretType = "其他";
					area = "其他";
                }
                String isYearGraduate = "";
                if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
                    isYearGraduate = "应届";
                } else {
                    isYearGraduate = "往届";
                }

                String recruitDate = doctorInfoExt.getRecruitDate().substring(0, 4) + "-" + doctorInfoExt.getRecruitDate().substring(5, 7) ;
                //解析xml
                PubUserResume userResume = doctorInfoExt.getUserResume();
                UserResumeExtInfoForm userResumeExt = null;
                userResumeExt = userResumeBiz.converyToJavaBean(userResume.getUserResume(), UserResumeExtInfoForm.class);

                //是否是执业医师和编号
                String qualificationFlag = "";
//				if("176".equals(userResumeExt.getQualificationMaterialId()) || "177".equals(userResumeExt.getQualificationMaterialId())){
//					qualificationFlag = "是";
//				}else{
//					qualificationFlag = "否";
//				}
                if (StringUtil.isNotBlank(userResumeExt.getDoctorPracticingCategoryCode())) {
                    qualificationFlag = "是";
                } else {
					qualificationFlag = "否";
                    userResumeExt.setDoctorPracticingCategoryCode("");
                }
                //研究生
                String masterFlag = "";
                if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
                    userResumeExt.setMasterDegreeName("");
                    userResumeExt.setMasterDegreeTypeName("");
                    userResumeExt.setMasterGraSchoolName("");
                    userResumeExt.setMasterGraTime("");
                    userResumeExt.setMasterMajor("");
                    masterFlag = "否";
                } else {
                    masterFlag = "是";
                }
                //博士
                String doctorFlag = "";
                if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
                    userResumeExt.setDoctorDegreeName("");
                    userResumeExt.setDoctorDegreeTypeName("");
                    userResumeExt.setDoctorGraSchoolName("");
                    userResumeExt.setDoctorGraTime("");
                    userResumeExt.setDoctorMajor("");
                    doctorFlag = "否";
                } else {
                    doctorFlag = "是";
                }
                //工作单位
                String property = "";
				String yyjb="";
				String yydc="";
				String hospitalCateName="";
				String hospitalAttrName="";
                if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())||ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
                    ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
                    if (resBase != null && jointFlag.equals("是")) {
                        property = resBase.getBaseGradeName();
                    }
                    if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"1".equals(userResumeExt.getMedicalHeaithOrgId())) {
                        userResumeExt.setHospitalAttrName("");
                        userResumeExt.setHospitalCategoryName("");
                        userResumeExt.setBaseAttributeName("");
                    }
                    if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"3".equals(userResumeExt.getMedicalHeaithOrgId())) {
                        userResumeExt.setBasicHealthOrgName("");
                    }
					if("1".equals(userResumeExt.getBaseAttributeId()))
					{
						 yyjb="一级";
						 yydc="未定等";
					}
					if("2".equals(userResumeExt.getBaseAttributeId()))
					{
						 yyjb="一级";
						 yydc="未定等";
					}
					if("3".equals(userResumeExt.getBaseAttributeId()))
					{
						 yyjb="三级";
						 yydc="未定等";
					}
					if("4".equals(userResumeExt.getBaseAttributeId()))
					{
						 yyjb="三级";
						 yydc="甲等";
					}
					if("5".equals(userResumeExt.getBaseAttributeId()))
					{
						 yyjb="三级";
						 yydc="乙等";
					}
					if("6".equals(userResumeExt.getBaseAttributeId()))
					{
						 yyjb="未定级";
						 yydc="未定等";
					}
					if("1".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省、自治区、直辖市属";
					}
					if("2".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省辖市（地区、州、盟、直辖市区）属";
					}
					if("3".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="县级市（省辖市区）属";
					}
					if("1".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getHospitalCategoryName();
					}
					if("3".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getBasicHealthOrgName();
					}
                } else {
                    doctor.setWorkOrgName("");
                    userResumeExt.setMedicalHeaithOrgName("");
                }

                //研究生毕业时间
                String masterGraTime = "";
                if (StringUtil.isNotBlank(userResumeExt.getMasterGraTime())) {
                    masterGraTime = userResumeExt.getMasterGraTime().substring(0, 4);
                }
                //博士毕业时间
                String doctorGraTime = "";
                if (StringUtil.isNotBlank(userResumeExt.getDoctorGraTime())) {
                    doctorGraTime = userResumeExt.getDoctorGraTime().substring(0, 4);
                }
                //本科毕业时间
                String graduationTime = "";
                if (StringUtil.isNotBlank(userResumeExt.getGraduationTime())) {
                    graduationTime = userResumeExt.getGraduationTime().substring(0, 4);
                }
				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				//规培年限
				String trainYear = "";
				if(StringUtil.isNotBlank(doctorInfoExt.getTrainYear())){
					trainYear = doctorInfoExt.getTrainYear();
				}
				switch (trainYear){
					case "OneYear":{trainYear="一年";break;}
					case "TwoYear":{trainYear="两年";break;}
					case "ThreeYear":{trainYear="三年";break;}
				}
                dataList = new String[]{
                        orgName,
                        sysUser.getUserName(),
                        sysUser.getSexName(),
                        sysUser.getUserBirthday(),
						CretType,
						sysUser.getIdNo(),
						area,
						sysUser.getNationName(),
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						sysUser.getUserQq(),
                        isYearGraduate,
                        doctor.getTrainingSpeName(),
                        qualificationFlag,
                        userResumeExt.getDoctorPracticingCategoryCode(),
                        doctorInfoExt.getSessionNumber(),
                        recruitDate,
						"否",
						"",
						trainYear,
                        doctor.getDoctorTypeName(),
                        userResumeExt.getGraduatedName(),
                        graduationTime,
                        userResumeExt.getSpecialized(),
						isGeneralOrderOrientationTrainee,
                        userResumeExt.getDegreeName(),
                        masterFlag,
                        userResumeExt.getMasterGraSchoolName(),
                        masterGraTime,
                        userResumeExt.getMasterMajor(),
                        userResumeExt.getMasterDegreeName(),
                        userResumeExt.getMasterDegreeTypeName(),

                        doctorFlag,
                        userResumeExt.getDoctorGraSchoolName(),
                        doctorGraTime,
                        userResumeExt.getDoctorMajor(),
                        userResumeExt.getDoctorDegreeName(),
                        userResumeExt.getDoctorDegreeTypeName(),

                        doctor.getWorkOrgName(),
						yyjb,
						yydc,
						hospitalCateName,
						hospitalAttrName,

                        jointFlag,
                        joinName,
                        "",
                        "",
                        ""
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(dataList[j]);
                }
            }
        }
        String fileName = titleYear+"学生信息一览表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());

    }

	@SuppressWarnings("deprecation")
	@Override
	public void exportForDetailLog(List<JsDoctorInfoLogExt> doctorInfoExts, HttpServletResponse response) throws Exception {
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		//列宽自适应
		HSSFRow rowDep = sheet.createRow(0);//第一行
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));//合并单元格
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(stylevwc);
		cellOne.setCellValue("培训基地");
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 19));//合并单元格
		HSSFCell cell = rowDep.createCell(1);
		cell.setCellStyle(stylevwc);
		cell.setCellValue("学生基本信息");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 20, 35));
		HSSFCell cellTwo = rowDep.createCell(20);
		cellTwo.setCellStyle(styleCenter);
		cellTwo.setCellValue("学历信息");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 36, 46));
		HSSFCell cellThree = rowDep.createCell(36);
		cellThree.setCellStyle(styleCenter);
		cellThree.setCellValue("单位信息");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 19, 22));//合并单元格
		HSSFCell cellFour = rowTwo.createCell(19);
		cellFour.setCellStyle(styleCenter);
		cellFour.setCellValue("本科阶段");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 23, 28));//合并单元格
		HSSFCell cellFive = rowTwo.createCell(23);
		cellFive.setCellStyle(styleCenter);
		cellFive.setCellValue("硕士研究生阶段");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 30, 35));//合并单元格
		HSSFCell cellSix = rowTwo.createCell(30);
		cellSix.setCellStyle(styleCenter);
		cellSix.setCellValue("博士研究生阶段");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 36, 37));//合并单元格
		HSSFCell cellSeven = rowTwo.createCell(36);
		cellSeven.setCellStyle(styleCenter);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 38, 40));//合并单元格
		HSSFCell cellEight = rowTwo.createCell(38);
		cellEight.setCellStyle(styleCenter);
		cellEight.setCellValue("在“医疗卫生机构”选择“医院”则填");
		HSSFCell cellNine = rowTwo.createCell(10);
		cellNine.setCellStyle(styleCenter);
		cellNine.setCellValue("在“医疗卫生机构”选择“基层医疗卫生机构”则填");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 42, 44));//合并单元格
		HSSFCell cellTen = rowTwo.createCell(42);
		cellTen.setCellStyle(styleCenter);

		HSSFRow rowThree = sheet.createRow(2);//第三行
		String[] titles = new String[]{
				"基地名称",
				"姓名",
				"性别",
				"年龄",
				"证件类型",
				"证件号",
				"民族",
				"手机号",
				"邮箱",
				"QQ（非必填）",
				"往届/应届",
				"培训专业",
				"是否执业医师",
				"如是执业医师，请填写执业医师资格证号",
				"参培年份",
				"规培年限",
				"实际培训开始时间（****年**月）",
				"培训状态",
				"退培/延期原因",
				"学员类型",
				"毕业学校",
				"毕业年份",
				"毕业专业",
				"学历",
				"是否全科定向生",
				"学位",
				"是否硕士研究生",
				"毕业学校",
				"毕业年份",
				"毕业专业",
				"学位",
				"学位类型",
				"是否博士研究生",
				"毕业学校",
				"毕业年份",
				"毕业专业",
				"学位",
				"学位类型",
				"工作单位（与单位公章对应的官方全称）",
				"医疗卫生机构",
				"医院属性",
				"医院类别",
				"单位性质",
				"基层医疗卫生机构",
				"是否在协同单位培训",
				"协同单位（与单位公章对应的官方全称）",
				"协同单位性质",
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowThree.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 1 * 156);
		}

		int rowNum = 3;
		String[] dataList = null;
		if (doctorInfoExts != null && !doctorInfoExts.isEmpty()) {
			for (int i = 0; i < doctorInfoExts.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行

				JsDoctorInfoLogExt doctorInfoExt = doctorInfoExts.get(i);
				//培训基地
				String orgName = "";
				String joinName = "";
				String jointFlag = "";
				List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
				Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
				if (jointOrgs != null && !jointOrgs.isEmpty()) {
					for (Map<String, Object> en : jointOrgs) {
						Object key = en.get("key");
						Object value = en.get("value");
						orgAndJointNameMap.put(key, value);
					}
				}
				if (orgAndJointNameMap.containsKey(doctorInfoExt.getOrgFlow())) {
					orgName = (String) orgAndJointNameMap.get(doctorInfoExt.getOrgFlow());
					joinName = doctorInfoExt.getOrgName();
					jointFlag = "是";
				} else {
					jointFlag = "否";
					orgName = doctorInfoExt.getOrgName();
				}

				String age ="";
				if(doctorInfoExt.getSysUser()!=null&&StringUtil.isNotBlank(doctorInfoExt.getSysUser().getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(doctorInfoExt.getSysUser().getUserBirthday().substring(0, 4))) + "";
				}
				SysUserLog sysUser = doctorInfoExt.getSysUser();
				ResDoctorLog doctor = doctorInfoExt.getResDoctor();

				String CretType = "";
				if (!CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
					CretType = "其他";
				} else {
					CretType = "身份证";
				}
				String isYearGraduate = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}

				String recruitDate = doctorInfoExt.getRecruitDate().substring(0, 4) + "年" + doctorInfoExt.getRecruitDate().substring(5, 7) + "月";
				//解析xml
				ResUserResumeLog userResumeExt = doctorInfoExt.getUserResume();


				//是否是执业医师和编号
				String qualificationFlag = "";
				if (StringUtil.isNotBlank(userResumeExt.getQualificationMaterialCode())) {
					qualificationFlag = "是";
				} else {
					userResumeExt.setQualificationMaterialCode("");
				}
				//研究生
				String masterFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
					userResumeExt.setMasterDegreeName("");
					userResumeExt.setMasterDegreeTypeName("");
					userResumeExt.setMasterGraSchoolName("");
					userResumeExt.setMasterGraTime("");
					userResumeExt.setMasterMajor("");
					masterFlag = "否";
				} else {
					masterFlag = "是";
				}
				//博士
				String doctorFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
					userResumeExt.setDoctorDegreeName("");
					userResumeExt.setDoctorDegreeTypeName("");
					userResumeExt.setDoctorGraSchoolName("");
					userResumeExt.setDoctorGraTime("");
					userResumeExt.setDoctorMajor("");
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
				}
				//工作单位
				String property = "";
				if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())||ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
					ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
					if (resBase != null && jointFlag.equals("是")) {
						property = resBase.getBaseGradeName();
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"1".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setHospitalAttrName("");
						userResumeExt.setHospitalCategoryName("");
						userResumeExt.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || "3".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setBasicHealthOrgName("");
					}

				} else {
					doctor.setWorkOrgName("");
					userResumeExt.setMedicalHeaithOrgName("");
				}
				//研究生毕业时间
				String masterGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getMasterGraTime())) {
					masterGraTime = userResumeExt.getMasterGraTime().substring(0, 4);
				}
				//博士毕业时间
				String doctorGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getDoctorGraTime())) {
					doctorGraTime = userResumeExt.getDoctorGraTime().substring(0, 4);
				}
				//本科毕业时间
				String graduationTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getGraduationTime())) {
					graduationTime = userResumeExt.getGraduationTime().substring(0, 4);
				}
				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientation())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientation())) {
					isGeneralOrderOrientationTrainee="否";
				}
				//规培年限
				String trainYear = "";
				trainYear = doctorInfoExt.getTrainYear();
				switch (trainYear){
					case "OneYear":{trainYear="一年";break;}
					case "TwoYear":{trainYear="两年";break;}
					case "ThreeYear":{trainYear="三年";break;}
				}
				dataList = new String[]{
						orgName,
						sysUser.getUserName(),
						sysUser.getSexName(),
						age,
						CretType,
						sysUser.getIdNo(),
						sysUser.getNationName(),
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						sysUser.getUserQq(),
						isYearGraduate,
						doctor.getTrainingSpeName(),
						qualificationFlag,
						userResumeExt.getQualificationMaterialCode(),
						doctorInfoExt.getSessionNumber(),
						trainYear,
						recruitDate,

						"在培",//医师状态
						"",

						doctor.getDoctorTypeName(),

						userResumeExt.getGraduatedName(),
						graduationTime,
						userResumeExt.getSpecialized(),
						sysUser.getEducationName(),
						isGeneralOrderOrientationTrainee,
						userResumeExt.getDegreeName(),

						masterFlag,
						userResumeExt.getMasterGraSchoolName(),
						masterGraTime,
						userResumeExt.getMasterMajor(),
						userResumeExt.getMasterDegreeName(),
						userResumeExt.getMasterDegreeTypeName(),

						doctorFlag,
						userResumeExt.getDoctorGraSchoolName(),
						doctorGraTime,
						userResumeExt.getDoctorMajor(),
						userResumeExt.getDoctorDegreeName(),
						userResumeExt.getDoctorDegreeTypeName(),

						doctor.getWorkOrgName(),
						userResumeExt.getMedicalHeaithOrgName(),

						userResumeExt.getHospitalAttrName(),
						userResumeExt.getHospitalCategoryName(),
						userResumeExt.getBaseAttributeName(),

						userResumeExt.getBasicHealthOrgName(),

						jointFlag,
						joinName,
						property
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		String fileName = "学生信息一览表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());

	}

	@Override
	public List<ResDoctor> searchReductionDoc(Map<String,Object> paramMap){
		return jsresDoctorExtMapper.searchReductionDoc(paramMap);
	}

	@Override
	public List<Map<String,Object>> getDocCountBySession(Map<String, Object> paramMap){
		return jsresDoctorExtMapper.getDocCountBySession(paramMap);
	}
	@Override
	public List<Map<String,Object>> getAssiGeneralDocCountBySession(Map<String, Object> paramMap){
		return jsresDoctorExtMapper.getAssiGeneralDocCountBySession(paramMap);
	}
	@Override
	public List<Map<String,Object>> getAssiGeneralDocLogCountBySession(Map<String, Object> paramMap){
		return jsresDoctorExtMapper.getAssiGeneralDocLogCountBySession(paramMap);
	}
	@Override
	public List<Map<String,Object>> getAssiGeneralDocLogByOrg(Map<String,Object> paramMap){
		return jsresDoctorExtMapper.getAssiGeneralDocLogByOrg(paramMap);
	}
	@Override
	public List<Map<String,Object>> getDocByOrg(Map<String,Object> paramMap){
		return jsresDoctorExtMapper.getDocByOrg(paramMap);
	}

	@Override
	public List<Map<String, Object>> getDocLogCountBySession(Map<String, Object> paramMap) {
		return jsresDoctorExtMapper.getDocLogCountBySession(paramMap);
	}

	@Override
	public List<Map<String, Object>> getDocLogByOrg(Map<String, Object> paramMap) {
		return jsresDoctorExtMapper.getDocLogByOrg(paramMap);
	}

	@Override
	public List<Map<String,Object>> getAssiGeneralDocByOrg(Map<String,Object> paramMap){
		return jsresDoctorExtMapper.getAssiGeneralDocByOrg(paramMap);
	}

	@Override
	public int findWaitPassCountByOrgFlow(int f,List<String> list) {
		if(list.size()>0)
		{
			Map<String,Object> paramMap=new HashMap<String,Object>();
			paramMap.put("f", f);
			paramMap.put("list", list);
			return jsresDoctorExtMapper.findWaitPassCountByOrgFlow(paramMap);
		}
		return 0;
	}

	@Override
	public int findWaitPassCountByOrgFlow2(int f, List<String> list, String trainingTypeId) {
		if(list.size()>0)
		{
			Map<String,Object> paramMap=new HashMap<String,Object>();
			paramMap.put("f", f);
			paramMap.put("list", list);
			paramMap.put("trainingTypeId",trainingTypeId);
			return jsresDoctorExtMapper.findWaitPassCountByOrgFlow(paramMap);
		}
		return 0;
	}

	@Override
	public int joinJsresUserBalckList(ResDocotrDelayTeturn docotrDelayTeturn) {
		String doctorFlow = docotrDelayTeturn.getDoctorFlow();
		if(StringUtil.isNotBlank(doctorFlow)){
			SysUser user = userBiz.readSysUser(doctorFlow);//查询用户信息
			//根据用户的信息去黑名单表中查询
			Map<String,Object> map = new HashMap<>();
			if(StringUtil.isNotBlank(doctorFlow)) {
				map.put("userFlow",doctorFlow);
			}
			if(StringUtil.isNotBlank(user.getUserCode())) {
				map.put("userCode",user.getUserCode());
			}
			if(StringUtil.isNotBlank(user.getUserEmail())) {
				map.put("userEmail",user.getUserEmail());
			}
			if(StringUtil.isNotBlank(user.getIdNo())) {
				map.put("idNo",user.getIdNo());
			}
			if(StringUtil.isNotBlank(user.getUserPhone())) {
				map.put("userPhone",user.getUserPhone());
			}
			List<JsresUserBalcklist> balcklist = balcklistExtMapper.selectBlackUserOfAll(map);
			JsresUserBalcklist userBalck = null;
			if(balcklist!=null&&balcklist.size()>0)
			{
				userBalck=balcklist.get(0);
			}else {
				userBalck=new JsresUserBalcklist();
			}
			userBalck.setUserFlow(doctorFlow);
			userBalck.setUserName(user.getUserName());
			userBalck.setUserCode(user.getUserCode());
			userBalck.setSessionNumber(docotrDelayTeturn.getSessionNumber());
			userBalck.setUserEmail(user.getUserEmail());
			userBalck.setUserPhone(user.getUserPhone());
			userBalck.setIdNo(user.getIdNo());
			userBalck.setOrgFlow(docotrDelayTeturn.getOrgFlow());
			userBalck.setOrgName(docotrDelayTeturn.getOrgName());
			userBalck.setTrainingSpeId(docotrDelayTeturn.getTrainingSpeId());
			userBalck.setTrainingSpeName(docotrDelayTeturn.getTrainingSpeName());
			userBalck.setReason(docotrDelayTeturn.getPolicy());
			userBalck.setAuditStatusId("Auditing");
			userBalck.setAuditStatusName("待审核");
			userBalck.setIsSystem("Y");
			userBalck.setReasonYj("您的信息已被纳入我省医务人员诚信系统，5年内不得进入我省培训基地接受住院医师规范化培训。如有相关疑问，请与相关管理部门联系。");
			userBalck.setOperTypeId("1");
			userBalck.setOperTypeName("自动");
			userBalck.setRecordStatus(GlobalConstant.FLAG_Y);

			if(StringUtil.isNotBlank(userBalck.getRecordFlow()))
			{
				GeneralMethod.setRecordInfo(userBalck, false);
				return balcklistMapper.updateByPrimaryKeySelective(userBalck);
			}else{
				userBalck.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(userBalck, true);
				return this.balcklistMapper.insertSelective(userBalck);
			}

		}
		return GlobalConstant.ZERO_LINE;
	}

	/**
	 * 檢查黑名單中信息
	 * @param map
	 * @return
     */
	@Override
	public List<JsresUserBalcklist> checkBlackList(Map<String,Object> map) {
		List<JsresUserBalcklist> list = balcklistExtMapper.selectBlackUser(map);
		return list;
	}

	@Override
	public JsresUserBalcklist findByIdNo4Black(String idNo) {
		JsresUserBalcklistExample jsresUserBalcklistExample=new JsresUserBalcklistExample();
		JsresUserBalcklistExample.Criteria criteria=jsresUserBalcklistExample.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andIdNoEqualTo(idNo);
		List<JsresUserBalcklist> list = balcklistMapper.selectByExample(jsresUserBalcklistExample);
		if (list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public JsresUserBalcklist findByUserEmail4Black(String userEmail) {
		JsresUserBalcklistExample jsresUserBalcklistExample=new JsresUserBalcklistExample();
		JsresUserBalcklistExample.Criteria criteria=jsresUserBalcklistExample.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserEmailEqualTo(userEmail);
		List<JsresUserBalcklist> list = balcklistMapper.selectByExample(jsresUserBalcklistExample);
		if (list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public JsresUserBalcklist findByUserPhone4Black(String userPhone) {
		JsresUserBalcklistExample jsresUserBalcklistExample=new JsresUserBalcklistExample();
		JsresUserBalcklistExample.Criteria criteria=jsresUserBalcklistExample.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserPhoneEqualTo(userPhone);
		List<JsresUserBalcklist> list = balcklistMapper.selectByExample(jsresUserBalcklistExample);
		if (list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String,Object>> searchGraduates(Map<String, Object> paramMap) {
		return jsresDoctorExtMapper.selectGraduates(paramMap);
	}

	@Override
	public List<Map<String, Object>> searchGraduatesByOrg(Map<String, Object> paramMap) {
		return jsresDoctorExtMapper.selectGraduatesByOrg(paramMap);
	}

	@Override
	public int saveEvalConfig(String configFlow,JsEvalCfgTitleExt title) throws DocumentException{
		if(StringUtil.isBlank(configFlow)){//新增第一个评分项目
			configFlow = PkUtil.getUUID();
			ResDoctorProcessEvalConfig record = new ResDoctorProcessEvalConfig();
			record.setConfigFlow(configFlow);
			record.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			record.setOrgName(GlobalContext.getCurrentUser().getOrgName());
			Document dom = DocumentHelper.createDocument();
			Element root = dom.addElement("evalCfg");
			Element titleElement = root.addElement("title").addAttribute("id", PkUtil.getUUID());
			titleElement.addAttribute("name",title.getName());
			titleElement.addAttribute("orderNum",title.getOrderNum());
			record.setFormCfg(dom.asXML());
			GeneralMethod.setRecordInfo(record,true);
			return evalConfigMapper.insertSelective(record);
		}else{
			ResDoctorProcessEvalConfig existForm = evalConfigMapper.selectByPrimaryKey(configFlow);
			Document dom = DocumentHelper.parseText(existForm.getFormCfg());
			Element root = dom.getRootElement();
			if(StringUtil.isBlank(title.getId())){//新增评分项目，title节点
				Element titleElement = root.addElement("title");
				titleElement.addAttribute("id", PkUtil.getUUID());
				titleElement.addAttribute("name", title.getName());
				titleElement.addAttribute("orderNum",title.getOrderNum());
			}else{//修改评分项目，title节点
				String titleXpath = "//title[@id='"+title.getId()+"']";
				Element titleElement = (Element) dom.selectSingleNode(titleXpath);
				titleElement.addAttribute("name", title.getName());
				titleElement.addAttribute("orderNum",title.getOrderNum());
			}
			existForm.setFormCfg(dom.asXML());
			return editForm(existForm);
		}
	}

	@Override
	public int modifyItem(String configFlow, JsEvalCfgItemExt item) throws DocumentException {
		if(StringUtil.isNotBlank(configFlow)){
			ResDoctorProcessEvalConfig existForm = evalConfigMapper.selectByPrimaryKey(configFlow);
			Document dom = DocumentHelper.parseText(existForm.getFormCfg());
			Element root = dom.getRootElement();
			//修改评分指标，item节点
			String itemXpath = "//item[@id='"+item.getId()+"']";
			Node itemNode = dom.selectSingleNode(itemXpath);
			Node nameNode = itemNode.selectSingleNode("name");
			nameNode.setText(item.getName());
			Node scoreNode = itemNode.selectSingleNode("score");
			scoreNode.setText(item.getScore());
			Node orderNode = itemNode.selectSingleNode("order");
			orderNode.setText(item.getOrder());
			existForm.setFormCfg(dom.asXML());
			return editForm(existForm);
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int saveFormItemList(JsEvalCfgExt form) throws DocumentException {
		List<JsEvalCfgItemExt> itemFormList = form.getItemFormList();
		if(null != itemFormList&& !itemFormList.isEmpty()){
			ResDoctorProcessEvalConfig existForm = evalConfigMapper.selectByPrimaryKey(form.getConfigFlow());
			if(null != existForm){
				Document dom = DocumentHelper.parseText(existForm.getFormCfg());
				for(JsEvalCfgItemExt item : itemFormList){
					String titleId = item.getTitleId();
					String name = item.getName();
					String score = item.getScore();
					String order = item.getOrder();
					String titleXpath = "//title[@id='"+titleId+"']";
					Element titleElement = (Element) dom.selectSingleNode(titleXpath);
					Element itemElement = titleElement.addElement("item");
					itemElement.addAttribute("id", PkUtil.getUUID());
					itemElement.addElement("name").setText(name);
					itemElement.addElement("score").setText(score);
					itemElement.addElement("order").setText(order);
				}
				existForm.setFormCfg(dom.asXML());
				return editForm(existForm);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public ResDoctorProcessEvalConfig readEvalConfig(String orgFlow) {
		if(StringUtil.isNotBlank(orgFlow)){
			ResDoctorProcessEvalConfigExample example = new ResDoctorProcessEvalConfigExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andOrgFlowEqualTo(orgFlow);
			List<ResDoctorProcessEvalConfig> evalConfigList = evalConfigMapper.selectByExampleWithBLOBs(example);
			if(null != evalConfigList && evalConfigList.size() > 0){
				return evalConfigList.get(0);
			}
		}
		return null;
	}

	@Override
	public int deleteTitle(String configFlow, String id) throws DocumentException {
		if(StringUtil.isNotBlank(configFlow) && StringUtil.isNotBlank(id)){
			ResDoctorProcessEvalConfig existForm = evalConfigMapper.selectByPrimaryKey(configFlow);
			if(null != existForm){
				Document dom = DocumentHelper.parseText(existForm.getFormCfg());
				String titleXpath = "//title[@id='"+id+"']";
				Element titleElement = (Element) dom.selectSingleNode(titleXpath);
				titleElement.getParent().remove(titleElement);
				existForm.setFormCfg(dom.asXML());
				return editForm(existForm);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int deleteItem(String configFlow, String id) throws DocumentException {
		if(StringUtil.isNotBlank(configFlow) && StringUtil.isNotBlank(id)){
			ResDoctorProcessEvalConfig existForm = evalConfigMapper.selectByPrimaryKey(configFlow);
			if(null != existForm){
				Document dom = DocumentHelper.parseText(existForm.getFormCfg());
				String itemXpath = "//item[@id='"+id+"']";
				Element itemElement = (Element) dom.selectSingleNode(itemXpath);
				itemElement.getParent().remove(itemElement);
				existForm.setFormCfg(dom.asXML());
				return editForm(existForm);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	public int editForm(ResDoctorProcessEvalConfig form) {
		if (form != null) {
			String configFlow = form.getConfigFlow();
			if(StringUtil.isNotBlank(configFlow)){
				GeneralMethod.setRecordInfo(form,false);
				return evalConfigMapper.updateByPrimaryKeySelective(form);
			}else{
				form.setConfigFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(form,true);
				return evalConfigMapper.insertSelective(form);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}
	@SuppressWarnings("deprecation")
	@Override
	public void exportForRecruitDetail(List<JsRecruitDocInfoExt> doctorInfoExts, String titleYear,HttpServletResponse response) throws Exception {
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		//列宽自适应
		HSSFRow rowThree = sheet.createRow(0);//第三行
		String[] titles = new String[]{
				"培训基地",
				"*姓名",
				"*性别",
				"*出生日期（yyyy-mm-dd）",
				"*身份证件类型",
				"#身份证件号码",
				"*国家或地区",
				"*民族",
				"*手机号",
				"邮箱",
				"QQ（非必填）",
				"*往届/应届",
				"*培训专业",
				"*是否通过全国医师资格考试",
				"执业医师资格证号",
				"*招收年度",
				"*实际培训开始时间（yyyy-mm）",
				"*是否为对口支援计划住院医师",
				"#对口支援计划住院医师送出单位（请填全称）",
				"*培训年限核定",
				"*学员类型",
				"*毕业学校（本科）",
				"*毕业年份（本科）",
				"*毕业专业（本科）",
				"*是否全科订单定向学员",
				"*学位（本科）",
				"*是否硕士研究生",
				"#毕业院校（硕士）",
				"#毕业年份（硕士）",
				"#毕业专业（硕士）",
				"#学位（硕士）",
				"#学位类型（硕士）",
				"*是否博士研究生",
				"#毕业院校（博士）",
				"#毕业年份（博士）",
				"#毕业专业（博士）",
				"#学位（博士）",
				"#学位类型（博士）",
				"#工作单位（与单位公章对应的官方全称）",
				"#医院级别",
				"#医院等次",
				"#医疗卫生机构类别",
				"#医疗卫生机构隶属关系",
				"*是否在协同单位培训",
				"#协同单位（与单位公章对应的官方全称）",
				"#协同医院级别",
				"#协同医院等次",
				"#医疗卫生机构类别"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowThree.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 1 * 156);
		}

		int rowNum = 1;
		String[] dataList = null;
		if (doctorInfoExts != null && !doctorInfoExts.isEmpty()) {
			for (int i = 0; i < doctorInfoExts.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行

				JsRecruitDocInfoExt doctorInfoExt = doctorInfoExts.get(i);
				//培训基地
				String orgName = "";
				String joinName = "";
				String jointFlag = "";
				List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
				Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
				if (jointOrgs != null && !jointOrgs.isEmpty()) {
					for (Map<String, Object> en : jointOrgs) {
						Object key = en.get("key");
						Object value = en.get("value");
						orgAndJointNameMap.put(key, value);
					}
				}
				if (orgAndJointNameMap.containsKey(doctorInfoExt.getOrgFlow())) {
					orgName = (String) orgAndJointNameMap.get(doctorInfoExt.getOrgFlow());
					joinName = doctorInfoExt.getOrgName();
					jointFlag = "是";
				} else {
					jointFlag = "否";
					orgName = doctorInfoExt.getOrgName();
				}

				String age ="";
				if(doctorInfoExt.getSysUser()!=null&&StringUtil.isNotBlank(doctorInfoExt.getSysUser().getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(doctorInfoExt.getSysUser().getUserBirthday().substring(0, 4))) + "";
				}
				SysUser sysUser = doctorInfoExt.getSysUser();
				ResDoctor doctor = doctorInfoExt.getResDoctor();
				JsresRecruitInfo recruitInfo = doctorInfoExt.getRecruitInfo();

				String CretType = "";
				String area = "";
				String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
				if (CertificateTypeEnum.Shenfenzheng.getId().equals(cretTypeId)) {
					CretType = "居民身份证";
					area="中国大陆";
				}
//				else if (CertificateTypeEnum.Junguanzheng.getId().equals(cretTypeId)) {
//					CretType = "军队证件";
//					area="中国大陆";
//				}
				else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "护照";
					area="";
				}else if (CertificateTypeEnum.HongKongMacao.getId().equals(cretTypeId)) {
					CretType = "港澳居民来往内地通行证";
					area="";
				}else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "台湾居民来往内地通行证";
					area="";
				}else {
					CretType = "其他";
					area = "其他";
				}
				String isYearGraduate = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}
                String recruitDate = doctorInfoExt.getRecruitDate() == null ? "" : doctorInfoExt.getRecruitDate();
				if (StringUtil.isNotBlank(recruitDate) && recruitDate.length() >= 7) {
                    recruitDate = recruitDate.substring(0, 4) + "-" + recruitDate.substring(5, 7) ;
				}
				//解析xml
				PubUserResume userResume = doctorInfoExt.getUserResume();
				UserResumeExtInfoForm userResumeExt = null;
				userResumeExt = userResumeBiz.converyToJavaBean(userResume.getUserResume(), UserResumeExtInfoForm.class);
				//是否是执业医师和编号
				String qualificationFlag = "";
				if (StringUtil.isNotBlank(userResumeExt.getQualificationMaterialCode())) {
					qualificationFlag = "是";
				} else {
					qualificationFlag = "否";
					userResumeExt.setQualificationMaterialCode("");
				}
				//研究生
				String masterFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
					userResumeExt.setMasterDegreeName("");
					userResumeExt.setMasterDegreeTypeName("");
					userResumeExt.setMasterGraSchoolName("");
					userResumeExt.setMasterGraTime("");
					userResumeExt.setMasterMajor("");
					masterFlag = "否";
				} else {
					masterFlag = "是";
				}
				//博士
				String doctorFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
					userResumeExt.setDoctorDegreeName("");
					userResumeExt.setDoctorDegreeTypeName("");
					userResumeExt.setDoctorGraSchoolName("");
					userResumeExt.setDoctorGraTime("");
					userResumeExt.setDoctorMajor("");
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
				}
				//工作单位
				String property = "";
				String yyjb="";
				String yydc="";
				String hospitalCateName="";
				String hospitalAttrName="";
				if (ResDocTypeEnum.Company.getId().equals(recruitInfo.getDoctorTypeId())) {
					ResBase resBase = resBaseMapper.selectByPrimaryKey(recruitInfo.getOrgFlow());
					if (resBase != null && jointFlag.equals("是")) {
						property = resBase.getBaseGradeName();
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"1".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setHospitalAttrName("");
						userResumeExt.setHospitalCategoryName("");
						userResumeExt.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"3".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setBasicHealthOrgName("");
					}
					if("1".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("2".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("3".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="未定等";
					}
					if("4".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="甲等";
					}
					if("5".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="乙等";
					}
					if("6".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="未定级";
						yydc="未定等";
					}
					if("1".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省、自治区、直辖市属";
					}
					if("2".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省辖市（地区、州、盟、直辖市区）属";
					}
					if("3".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="县级市（省辖市区）属";
					}
					if("1".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getHospitalCategoryName();
					}
					if("3".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getBasicHealthOrgName();
					}
				} else {
					doctor.setWorkOrgName("");
					userResumeExt.setMedicalHeaithOrgName("");
				}

				//研究生毕业时间
				String masterGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getMasterGraTime())) {
					masterGraTime = userResumeExt.getMasterGraTime().substring(0, 4);
				}
				//博士毕业时间
				String doctorGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getDoctorGraTime())) {
					doctorGraTime = userResumeExt.getDoctorGraTime().substring(0, 4);
				}
				//本科毕业时间
				String graduationTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getGraduationTime())) {
					graduationTime = userResumeExt.getGraduationTime().substring(0, 4);
				}
				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				//规培年限
				String trainYear = "";
				if(StringUtil.isNotBlank(doctorInfoExt.getTrainYear())){
					trainYear = doctorInfoExt.getTrainYear();
				}
				switch (trainYear){
					case "OneYear":{trainYear="一年";break;}
					case "TwoYear":{trainYear="两年";break;}
					case "ThreeYear":{trainYear="三年";break;}
				}
				dataList = new String[]{
						orgName,
						sysUser.getUserName(),
						sysUser.getSexName(),
						sysUser.getUserBirthday(),
						CretType,
						sysUser.getIdNo(),
						area,
						sysUser.getNationName(),
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						sysUser.getUserQq(),
						isYearGraduate,
						recruitInfo.getSpeName(),
						qualificationFlag,
						userResumeExt.getQualificationMaterialCode(),
						doctorInfoExt.getSessionNumber(),
						recruitDate,
						"否",
						"",
						trainYear,
						recruitInfo.getDoctorTypeName(),
						userResumeExt.getGraduatedName(),
						graduationTime,
						userResumeExt.getSpecialized(),
						isGeneralOrderOrientationTrainee,
						userResumeExt.getDegreeName(),
						masterFlag,
						userResumeExt.getMasterGraSchoolName(),
						masterGraTime,
						userResumeExt.getMasterMajor(),
						userResumeExt.getMasterDegreeName(),
						userResumeExt.getMasterDegreeTypeName(),

						doctorFlag,
						userResumeExt.getDoctorGraSchoolName(),
						doctorGraTime,
						userResumeExt.getDoctorMajor(),
						userResumeExt.getDoctorDegreeName(),
						userResumeExt.getDoctorDegreeTypeName(),

						doctor.getWorkOrgName(),
						yyjb,
						yydc,
						hospitalCateName,
						hospitalAttrName,

						jointFlag,
						joinName,
						"",
						"",
						""
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		String fileName = titleYear+"学生信息一览表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());

	}

	@SuppressWarnings("deprecation")
	@Override
	public void exportMessage(List<JsDoctorInfoExt> doctorInfoExts, String titleYear,HttpServletResponse response) throws Exception {
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		//列宽自适应
		HSSFRow rowDep = sheet.createRow(0);//第一行
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 23));//合并单元格
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(stylevwc);
		cellOne.setCellValue("基本信息");

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 24, 128));//合并单元格
		HSSFCell cell = rowDep.createCell(24);
		cell.setCellStyle(stylevwc);
		cell.setCellValue("教育经历");

		sheet.addMergedRegion(new CellRangeAddress(0, 1, 129, 149));
		HSSFCell cellTwo = rowDep.createCell(129);
		cellTwo.setCellStyle(styleCenter);
		cellTwo.setCellValue("住培招收信息");


		HSSFRow rowTwo = sheet.createRow(1);//第二行
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 24, 44));//合并单元格
		HSSFCell cellFour = rowTwo.createCell(24);
		cellFour.setCellStyle(styleCenter);
		cellFour.setCellValue("中专");

		sheet.addMergedRegion(new CellRangeAddress(1, 1, 45, 65));//合并单元格
		HSSFCell cellFive = rowTwo.createCell(45);
		cellFive.setCellStyle(styleCenter);
		cellFive.setCellValue("大专");

		sheet.addMergedRegion(new CellRangeAddress(1, 1, 66, 86));//合并单元格
		HSSFCell cellSix = rowTwo.createCell(66);
		cellSix.setCellStyle(styleCenter);
		cellSix.setCellValue("本科");

		sheet.addMergedRegion(new CellRangeAddress(1, 1, 87, 107));//合并单元格
		cellSix = rowTwo.createCell(87);
		cellSix.setCellStyle(styleCenter);
		cellSix.setCellValue("硕士");

		sheet.addMergedRegion(new CellRangeAddress(1, 1, 108, 128));//合并单元格
		cellSix = rowTwo.createCell(108);
		cellSix.setCellStyle(styleCenter);
		cellSix.setCellValue("博士");


		//列宽自适应
		HSSFRow rowThree = sheet.createRow(2);//第三行
		String[] titles = new String[]{
				"姓名",
				"性别",
				"民族",
				"出生日期",
				"婚姻状况",
				"国家及地区",
				"身份证件类型",
				"身份证号码",
				"户口所在地省行政区划",
				"有效的手机号码",
				"有效的QQ号码",
				"有效的电子邮箱地址",
				"是否通过医师资格考试",
				"通过医师资格考试时间",
				"是否获得医师资格证书",
				"取得医师资格证书时间",
				"医师资格级别",
				"医师资格类别",
				"医师资格证书编码",
				"外语等级考试类型",
				"英语能力",
				"外语能力",
				"外语等级考试证书编号",
				"外语等级考试证书取得时间",
				"是否中专",
				"是否是全日制",
				"是否在读",
				"在读学历",
				"预计毕业时间",
				"在读专业",
				"在读院校",
				"其他在读院校",
				"学历",
				"毕业时间",
				"毕业专业",
				"毕业院校",
				"其他毕业院校",
				"是否获得毕业证书",
				"学历证书编号",
				"学历证书取得时间",
				"是否获得学位证书",
				"学位",
				"学位类型",
				"学位证书编号",
				"学位证书取得时间",
				"是否大专",
				"是否全日制",
				"是否在读",
				"在读学历",
				"预计毕业时间",
				"在读专业",
				"在读院校",
				"其他在读院校",
				"学历",
				"毕业时间",
				"毕业专业",
				"毕业院校",
				"其他毕业院校",
				"是否获得毕业证书",
				"学历证书编号",
				"学历证书取得时间",
				"是否获得学位证书",
				"学位",
				"学位类型",
				"学位证书编号",
				"学位证书取得时间",
				"是否本科",
				"是否全日制",
				"是否在读",
				"在读学历",
				"预计毕业时间",
				"在读专业",
				"在读院校",
				"其他在读院校",
				"学历",
				"毕业时间",
				"毕业专业",
				"毕业院校",
				"其他毕业院校",
				"是否获得毕业证书",
				"学历证书编号",
				"学历证书取得时间",
				"是否获得学位证书",
				"学位",
				"学位类型",
				"学位证书编号",
				"学位证书取得时间",
				"是否硕士研究生",
				"是否全日制",
				"是否在读",
				"在读学历",
				"预计毕业时间",
				"在读专业",
				"在读院校",
				"其他在读院校",
				"学历",
				"毕业时间",
				"毕业专业",
				"毕业院校",
				"其他毕业院校",
				"是否获得毕业证书",
				"学历证书编号",
				"学历证书取得时间",
				"是否获得学位证书",
				"学位",
				"学位类型",
				"学位证书编号",
				"学位证书取得时间",
				"是否博士研究生",
				"是否全日制",
				"是否在读",
				"在读学历",
				"预计毕业时间",
				"在读专业",
				"在读院校",
				"其他在读院校",
				"学历",
				"毕业时间",
				"毕业专业",
				"毕业院校",
				"其他毕业院校",
				"是否获得毕业证书",
				"学历证书编号",
				"学历证书取得时间",
				"是否获得学位证书",
				"学位",
				"学位类型",
				"学位证书编号",
				"学位证书取得时间",
				"培养类型",
				"人员类型",
				"其他人员",
				"工作单位名称",
				"工作单位统一信用代码",
				"往届生/应届生",
				"是否为农村订单定向免费医学毕业生",
				"招收年度",
				"培训所在省行政区划",
				"培训所在培训基地（医院）统一社会信用代码",
				"培训所在培训基地",
				"培训专业",
				"核定的培训年限（年）",
				"培训起止时间",
				"是否为西部支援行动住院医师",
				"西部支援行动住院医师送出省份",
				"西部支援行动住院医师送出单位统一社会信用代码",
				"西部支援行动住院医师送出单位",
				"西部支援熊东住院医师接收省份",
				"西部支援行动住院医师接收省份培训基地（医院）统一社会信用代码",
				"西部支援行动住院医师接收省份培训基地（医院）",
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowThree.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 1 * 156);
		}

		int rowNum = 3;
		String[] dataList = null;
		if (doctorInfoExts != null && !doctorInfoExts.isEmpty()) {
			for (int i = 0; i < doctorInfoExts.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行

				JsDoctorInfoExt doctorInfoExt = doctorInfoExts.get(i);
				//培训基地
				String orgName = "";
				String joinName = "";
				String jointFlag = "";
				List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
				Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
				if (jointOrgs != null && !jointOrgs.isEmpty()) {
					for (Map<String, Object> en : jointOrgs) {
						Object key = en.get("key");
						Object value = en.get("value");
						orgAndJointNameMap.put(key, value);
					}
				}
				if (orgAndJointNameMap.containsKey(doctorInfoExt.getOrgFlow())) {
					orgName = (String) orgAndJointNameMap.get(doctorInfoExt.getOrgFlow());
					joinName = doctorInfoExt.getOrgName();
					jointFlag = "是";
				} else {
					jointFlag = "否";
					orgName = doctorInfoExt.getOrgName();
				}

				String age ="";
				if(doctorInfoExt.getSysUser()!=null&&StringUtil.isNotBlank(doctorInfoExt.getSysUser().getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(doctorInfoExt.getSysUser().getUserBirthday().substring(0, 4))) + "";
				}
				SysUser sysUser = doctorInfoExt.getSysUser();
				ResDoctor doctor = doctorInfoExt.getResDoctor();

				String CretType = "";
				String area = "";
				String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
				if (CertificateTypeEnum.Shenfenzheng.getId().equals(cretTypeId)) {
					CretType = "居民身份证";
					area="中国大陆";
				}
//				else if (CertificateTypeEnum.Junguanzheng.getId().equals(cretTypeId)) {
//					CretType = "军队证件";
//					area="中国大陆";
//				}
				else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "护照";
					area="";
				}else if (CertificateTypeEnum.HongKongMacao.getId().equals(cretTypeId)) {
					CretType = "港澳居民来往内地通行证";
					area="";
				}else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "台湾居民来往内地通行证";
					area="";
				}else {
					CretType = "其他";
					area = "其他";
				}
				String isYearGraduate = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}

				String recruitDate = doctorInfoExt.getRecruitDate() ;
				//解析xml
				PubUserResume userResume = doctorInfoExt.getUserResume();
				UserResumeExtInfoForm userResumeExt = null;
				userResumeExt = userResumeBiz.converyToJavaBean(userResume.getUserResume(), UserResumeExtInfoForm.class);
				//婚姻状况
				String maritalStatus = "";
				if (StringUtil.isNotBlank(userResumeExt.getMaritalStatus())){
					if(userResumeExt.getMaritalStatus().equals("1")){
						maritalStatus = "未婚";
					}else if(userResumeExt.getMaritalStatus().equals("2")){
						maritalStatus = "已婚";
					}else if(userResumeExt.getMaritalStatus().equals("3")){
						maritalStatus = "初婚";
					}else if(userResumeExt.getMaritalStatus().equals("4")){
						maritalStatus = "再婚";
					}else if(userResumeExt.getMaritalStatus().equals("5")){
						maritalStatus = "复婚";
					}else if(userResumeExt.getMaritalStatus().equals("6")){
						maritalStatus = "丧偶";
					}else if(userResumeExt.getMaritalStatus().equals("7")){
						maritalStatus = "离婚";
					}
				}
				//户口所在省行政区划
				String locationOfProvince = "";
				if (StringUtil.isNotBlank(userResumeExt.getLocationOfProvince())){
					if(userResumeExt.getLocationOfProvince().equals("1")){
						locationOfProvince = "北京";
					}else if(userResumeExt.getLocationOfProvince().equals("2")){
						locationOfProvince = "天津";
					}else if(userResumeExt.getLocationOfProvince().equals("3")){
						locationOfProvince = "河北";
					}else if(userResumeExt.getLocationOfProvince().equals("4")){
						locationOfProvince = "山西";
					}else if(userResumeExt.getLocationOfProvince().equals("5")){
						locationOfProvince = "内蒙古";
					}else if(userResumeExt.getLocationOfProvince().equals("6")){
						locationOfProvince = "辽宁";
					}else if(userResumeExt.getLocationOfProvince().equals("7")){
						locationOfProvince = "吉林";
					}else if(userResumeExt.getLocationOfProvince().equals("8")){
						locationOfProvince = "黑龙江";
					}else if(userResumeExt.getLocationOfProvince().equals("9")){
						locationOfProvince = "上海";
					}else if(userResumeExt.getLocationOfProvince().equals("10")){
						locationOfProvince = "江苏";
					}else if(userResumeExt.getLocationOfProvince().equals("11")){
						locationOfProvince = "浙江";
					}else if(userResumeExt.getLocationOfProvince().equals("12")){
						locationOfProvince = "安徽";
					}else if(userResumeExt.getLocationOfProvince().equals("13")){
						locationOfProvince = "福建";
					}else if(userResumeExt.getLocationOfProvince().equals("14")){
						locationOfProvince = "江西";
					}else if(userResumeExt.getLocationOfProvince().equals("15")){
						locationOfProvince = "山东";
					}else if(userResumeExt.getLocationOfProvince().equals("16")){
						locationOfProvince = "河南";
					}else if(userResumeExt.getLocationOfProvince().equals("17")){
						locationOfProvince = "湖北";
					}else if(userResumeExt.getLocationOfProvince().equals("18")){
						locationOfProvince = "湖南";
					}else if(userResumeExt.getLocationOfProvince().equals("19")){
						locationOfProvince = "广东";
					}else if(userResumeExt.getLocationOfProvince().equals("20")){
						locationOfProvince = "广西";
					}else if(userResumeExt.getLocationOfProvince().equals("21")){
						locationOfProvince = "海南";
					}else if(userResumeExt.getLocationOfProvince().equals("22")){
						locationOfProvince = "重庆";
					}else if(userResumeExt.getLocationOfProvince().equals("23")){
						locationOfProvince = "四川";
					}else if(userResumeExt.getLocationOfProvince().equals("24")){
						locationOfProvince = "贵州";
					}else if(userResumeExt.getLocationOfProvince().equals("25")){
						locationOfProvince = "云南";
					}else if(userResumeExt.getLocationOfProvince().equals("26")){
						locationOfProvince = "西藏";
					}else if(userResumeExt.getLocationOfProvince().equals("27")){
						locationOfProvince = "陕西";
					}else if(userResumeExt.getLocationOfProvince().equals("28")){
						locationOfProvince = "甘肃";
					}else if(userResumeExt.getLocationOfProvince().equals("29")){
						locationOfProvince = "青海";
					}else if(userResumeExt.getLocationOfProvince().equals("30")){
						locationOfProvince = "宁夏";
					}else if(userResumeExt.getLocationOfProvince().equals("31")){
						locationOfProvince = "新疆";
					}else if(userResumeExt.getLocationOfProvince().equals("32")){
						locationOfProvince = "兵团";
					}
				}
				//是否通过医师资格考试
				String isPassQualifyingExamination = "";
				if (StringUtil.isNotBlank(userResumeExt.getIsPassQualifyingExamination())) {
					if(userResumeExt.getIsPassQualifyingExamination().equals(GlobalConstant.FLAG_Y)){
						isPassQualifyingExamination = "是";
					}else if(userResumeExt.getIsPassQualifyingExamination().equals(GlobalConstant.FLAG_N)){
						isPassQualifyingExamination = "否";
					}
				}
				//是否获得医师资格证书
				String isHaveQualificationCertificate = "";
				if (StringUtil.isNotBlank(userResumeExt.getIsHaveQualificationCertificate())) {
					if(userResumeExt.getIsHaveQualificationCertificate().equals(GlobalConstant.FLAG_Y)){
						isHaveQualificationCertificate = "是";
					}else if(userResumeExt.getIsHaveQualificationCertificate().equals(GlobalConstant.FLAG_N)){
						isHaveQualificationCertificate = "否";
					}
				}
				/*//是否是执业医师和编号
				String qualificationFlag = "";
				if (StringUtil.isNotBlank(userResumeExt.getQualificationMaterialCode())) {
					qualificationFlag = "是";
				} else {
					qualificationFlag = "否";
					userResumeExt.setQualificationMaterialCode("");
				}*/
				//是否获得毕业证书(本科)
				String isCollegeHaveDiploma = "";
				if (StringUtil.isNotBlank(userResumeExt.getIsCollegeHaveDiploma())) {
					if(userResumeExt.getIsCollegeHaveDiploma().equals(GlobalConstant.FLAG_Y)){
						isCollegeHaveDiploma = "是";
					}else if(userResumeExt.getIsCollegeHaveDiploma().equals(GlobalConstant.FLAG_N)){
						isCollegeHaveDiploma = "否";
					}
				}
				//是否获得学位证书(本科)
				String isCollegeDegreeCertificate = "";
				if (StringUtil.isNotBlank(userResumeExt.getIsCollegeDegreeCertificate())) {
					if(userResumeExt.getIsCollegeDegreeCertificate().equals(GlobalConstant.FLAG_Y)){
						isCollegeDegreeCertificate = "是";
					}else if(userResumeExt.getIsCollegeDegreeCertificate().equals(GlobalConstant.FLAG_N)){
						isCollegeDegreeCertificate = "否";
					}
				}
				//是否获得毕业证书(研究生)
				String isMasterHaveDiploma = "";
				if (StringUtil.isNotBlank(userResumeExt.getIsMasterHaveDiploma())) {
					if(userResumeExt.getIsMasterHaveDiploma().equals(GlobalConstant.FLAG_Y)){
						isMasterHaveDiploma = "是";
					}else if(userResumeExt.getIsMasterHaveDiploma().equals(GlobalConstant.FLAG_N)){
						isMasterHaveDiploma = "否";
					}
				}
				//是否获得学位证书(研究生)
				String isMasterDegreeCertificate = "";
				if (StringUtil.isNotBlank(userResumeExt.getIsMasterDegreeCertificate())) {
					if(userResumeExt.getIsMasterDegreeCertificate().equals(GlobalConstant.FLAG_Y)){
						isMasterDegreeCertificate = "是";
					}else if(userResumeExt.getIsMasterDegreeCertificate().equals(GlobalConstant.FLAG_N)){
						isMasterDegreeCertificate = "否";
					}
				}
				//是否获得毕业证书(博士)
				String isDoctorHaveDiploma = "";
				if (StringUtil.isNotBlank(userResumeExt.getIsDoctorHaveDiploma())) {
					if(userResumeExt.getIsDoctorHaveDiploma().equals(GlobalConstant.FLAG_Y)){
						isDoctorHaveDiploma = "是";
					}else if(userResumeExt.getIsDoctorHaveDiploma().equals(GlobalConstant.FLAG_N)){
						isDoctorHaveDiploma = "否";
					}
				}
				//是否获得学位证书(博士)
				String isDoctorDegreeCertificate = "";
				if (StringUtil.isNotBlank(userResumeExt.getIsDoctorDegreeCertificate())) {
					if(userResumeExt.getIsDoctorDegreeCertificate().equals(GlobalConstant.FLAG_Y)){
						isDoctorDegreeCertificate = "是";
					}else if(userResumeExt.getIsDoctorDegreeCertificate().equals(GlobalConstant.FLAG_N)){
						isDoctorDegreeCertificate = "否";
					}
				}
				//是否为西部支援行动住院医师
				String westernSupportResidents = "";
				if (StringUtil.isNotBlank(userResumeExt.getWesternSupportResidents())) {
					if(userResumeExt.getWesternSupportResidents().equals(GlobalConstant.FLAG_Y)){
						westernSupportResidents = "是";
					}else if(userResumeExt.getWesternSupportResidents().equals(GlobalConstant.FLAG_N)){
						westernSupportResidents = "否";
					}
				}
				//研究生
				String masterFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
					userResumeExt.setMasterDegreeName("");
					userResumeExt.setMasterDegreeTypeName("");
					userResumeExt.setMasterGraSchoolName("");
					userResumeExt.setMasterGraTime("");
					userResumeExt.setMasterMajor("");
					masterFlag = "否";
				} else {
					masterFlag = "是";
				}
				//博士
				String doctorFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
					userResumeExt.setDoctorDegreeName("");
					userResumeExt.setDoctorDegreeTypeName("");
					userResumeExt.setDoctorGraSchoolName("");
					userResumeExt.setDoctorGraTime("");
					userResumeExt.setDoctorMajor("");
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
				}
				//工作单位
				String property = "";
				String yyjb="";
				String yydc="";
				String hospitalCateName="";
				String hospitalAttrName="";
				if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())||ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
					ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
					if (resBase != null && jointFlag.equals("是")) {
						property = resBase.getBaseGradeName();
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"1".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setHospitalAttrName("");
						userResumeExt.setHospitalCategoryName("");
						userResumeExt.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"3".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setBasicHealthOrgName("");
					}
					if("1".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("2".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("3".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="未定等";
					}
					if("4".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="甲等";
					}
					if("5".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="乙等";
					}
					if("6".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="未定级";
						yydc="未定等";
					}
					if("1".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省、自治区、直辖市属";
					}
					if("2".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省辖市（地区、州、盟、直辖市区）属";
					}
					if("3".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="县级市（省辖市区）属";
					}
					if("1".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getHospitalCategoryName();
					}
					if("3".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getBasicHealthOrgName();
					}
				} else {
					doctor.setWorkOrgName("");
					userResumeExt.setMedicalHeaithOrgName("");
				}

				//研究生毕业时间
				String masterGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getMasterGraTime())) {
					masterGraTime = userResumeExt.getMasterGraTime().substring(0, 4);
				}
				//博士毕业时间
				String doctorGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getDoctorGraTime())) {
					doctorGraTime = userResumeExt.getDoctorGraTime().substring(0, 4);
				}
				//本科毕业时间
				String graduationTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getGraduationTime())) {
					graduationTime = userResumeExt.getGraduationTime().substring(0, 4);
				}
				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				//医师资格级别
				String PhysicianQualificationLevel = "";
				if (StringUtil.isNotBlank(userResumeExt.getPhysicianQualificationLevel())){
					if(userResumeExt.getPhysicianQualificationLevel().equals("zyys")){
						PhysicianQualificationLevel = "执业医师";
					}else if(userResumeExt.getPhysicianQualificationLevel().equals("zyzlys")){
						PhysicianQualificationLevel = "执业助理医师";
					}
				}
				//医师资格级别
				String PhysicianQualificationClass = "";
				if (StringUtil.isNotBlank(userResumeExt.getPhysicianQualificationClass())){
					if(userResumeExt.getPhysicianQualificationClass().equals("lc")){
						PhysicianQualificationClass = "临床";
					}else if(userResumeExt.getPhysicianQualificationClass().equals("kq")){
						PhysicianQualificationClass = "口腔";
					}else if(userResumeExt.getPhysicianQualificationClass().equals("ggws")){
						PhysicianQualificationClass = "公共卫生";
					}else if(userResumeExt.getPhysicianQualificationClass().equals("zy")){
						PhysicianQualificationClass = "中医";
					}
				}

				//规培年限
				String trainYear = "";
				if(StringUtil.isNotBlank(doctorInfoExt.getTrainYear())){
					trainYear = doctorInfoExt.getTrainYear();
				}
				String endDate=doctorInfoExt.getRecruitDate();


				switch (trainYear){
					case "OneYear":{trainYear="一年";endDate=DateUtil.addYear(recruitDate,1);break;}
					case "TwoYear":{trainYear="两年";endDate=DateUtil.addYear(recruitDate,2);break;}
					case "ThreeYear":{trainYear="三年";endDate=DateUtil.addYear(recruitDate,3);break;}
				}
				dataList = new String[]{
						sysUser.getUserName(),
						sysUser.getSexName()+"性",
						sysUser.getNationName(),
						sysUser.getUserBirthday(),
						maritalStatus,
						area,
						CretType,
						sysUser.getIdNo(),
						locationOfProvince,
						sysUser.getUserPhone(),
						sysUser.getUserQq(),
						sysUser.getUserEmail(),
						isPassQualifyingExamination,
						userResumeExt.getPassQualifyingExaminationTime(),
						isHaveQualificationCertificate,
						userResumeExt.getHaveQualificationCertificateTime(),
						PhysicianQualificationLevel,
						PhysicianQualificationClass,
						userResumeExt.getDoctorQualificationCertificateCode(),
						"",
						"",
						doctor.getForeignSkills(),
						"",
						"",
						//中专
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						//大专
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						//本科
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						sysUser.getEducationName(),
						userResumeExt.getGraduationTime(),
						userResumeExt.getSpecialized(),
						userResumeExt.getGraduatedName(),
						"",
						isCollegeHaveDiploma,
						userResumeExt.getCollegeDiplomaNo(),
						userResumeExt.getCollegeDiplomaTime(),
						isCollegeDegreeCertificate,
						userResumeExt.getDegreeName(),
						"",
						userResumeExt.getCollegeDegreeNo(),
						userResumeExt.getCollegeDegreeTime(),
						//硕士研究生
						masterFlag,
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						userResumeExt.getMasterGraTime(),
						userResumeExt.getMasterMajor(),
						userResumeExt.getMasterGraSchoolName(),
						"",
						isMasterHaveDiploma,
						userResumeExt.getMasterDiplomaNo(),
						userResumeExt.getMasterDiplomaTime(),
						isMasterDegreeCertificate,
						userResumeExt.getMasterDegreeName(),
						userResumeExt.getMasterDegreeTypeName(),//学位类型
						userResumeExt.getMasterDegreeNo(),
						userResumeExt.getMasterDegreeTime(),
						//博士
						doctorFlag,
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						userResumeExt.getDoctorGraTime(),
						userResumeExt.getDoctorMajor(),
						userResumeExt.getDoctorGraSchoolName(),
						"",
						isDoctorHaveDiploma,
						userResumeExt.getDoctorDiplomaNo(),
						userResumeExt.getDoctorDiplomaTime(),
						isDoctorDegreeCertificate,
						userResumeExt.getDoctorDegreeName(),
						userResumeExt.getDoctorDegreeTypeName(),//学位类型
						userResumeExt.getDoctorDegreeNo(),
						userResumeExt.getDoctorDegreeTime(),
						//西部
						"",
						doctor.getDoctorTypeName(),
						"",
						doctor.getWorkOrgName(),
						userResumeExt.getWorkUniteCreditCode(),
						isYearGraduate,
						isGeneralOrderOrientationTrainee,
						doctor.getSessionNumber(),
						"",
						"",
						doctorInfoExt.getOrgName(),
						doctorInfoExt.getSpeName(),
						trainYear,
						recruitDate+"~"+endDate,
						westernSupportResidents,
						userResumeExt.getWesternSupportResidentsSendProvince(),
						userResumeExt.getWesternSupportResidentsSendWorkUnitCode(),
						userResumeExt.getWesternSupportResidentsSendWorkUnit(),
						userResumeExt.getWesternSupportResidentsReceiveProvince(),
						userResumeExt.getWesternSupportResidentsReceiveHospitalCode(),
						userResumeExt.getWesternSupportResidentsReceiveHospital(),
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		String fileName = "2018国家信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());

	}

	public void exportToXlsx(HttpServletRequest request, HttpServletResponse response, String  fileName,
							  List<JsresDoctorInfoExt> dataMap) throws Exception{

//			String cacheFileName =ifExistTargetCacheFile(cachePath, "", "20191225", ".xlsx");
			//先缓存一份到服务器
//			if(cacheFileName==null) {
			HSSFWorkbook workbook = createWorkbook(dataMap);
			cacheXlsxFileToServer(fileName, workbook);
//				cacheFileName = fileName;
//			}
			//从服务器下载
			directDownloadToServer(request,response,fileName);

	}

	@Override
	public List<ResJointOrg> queryJoinOrg(String orgFlow) {
		ResJointOrgExample example = new ResJointOrgExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andOrgFlowEqualTo(orgFlow);
		return jointOrgMapper.selectByExample(example);
	}

	//生成工作簿并填充数据
	public HSSFWorkbook createWorkbook(List<JsresDoctorInfoExt> doctorInfoExts) throws Exception{
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		//列宽自适应
		HSSFRow rowThree = sheet.createRow(0);//第三行
		String[] titles = new String[]{
				"培训基地",
				"*姓名",
				"*性别",
				"*出生日期（yyyy-mm-dd）",
				"*身份证件类型",
				"#身份证件号码",
				"*国家或地区",
				"*民族",
				"*手机号",
				"邮箱",
				"QQ（非必填）",
				"*往届/应届",
				"*培训专业",
				"*是否通过全国医师资格考试",
				"执业医师资格证号",
				"*招收年度",
				"*实际培训开始时间（yyyy-mm）",
				"*是否为对口支援计划住院医师",
				"#对口支援计划住院医师送出单位（请填全称）",
				"*培训年限核定",
				"*学员类型",
				"*毕业学校（本科）",
				"*毕业年份（本科）",
				"*毕业专业（本科）",
				"*是否全科订单定向学员",
				"*学位（本科）",
				"*是否硕士研究生",
				"#毕业院校（硕士）",
				"#毕业年份（硕士）",
				"#毕业专业（硕士）",
				"#学位（硕士）",
				"#学位类型（硕士）",
				"*是否博士研究生",
				"#毕业院校（博士）",
				"#毕业年份（博士）",
				"#毕业专业（博士）",
				"#学位（博士）",
				"#学位类型（博士）",
				"#工作单位（与单位公章对应的官方全称）",
				"#医院级别",
				"#医院等次",
				"#医疗卫生机构类别",
				"#医疗卫生机构隶属关系",
				"*是否在协同单位培训",
				"#协同单位（与单位公章对应的官方全称）",
				"#协同医院级别",
				"#协同医院等次",
				"#医疗卫生机构类别"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowThree.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 1 * 156);
		}

		int rowNum = 1;
		String[] dataList = null;
		if (doctorInfoExts != null && !doctorInfoExts.isEmpty()) {
			for (int i = 0; i < doctorInfoExts.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行

//				JsRecruitDocInfoExt doctorInfoExt = doctorInfoExts.get(i);
				//培训基地
				String orgName = "";
				String joinName = "";
				String jointFlag = "";
				List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
				Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
				if (jointOrgs != null && !jointOrgs.isEmpty()) {
					for (Map<String, Object> en : jointOrgs) {
						Object key = en.get("key");
						Object value = en.get("value");
						orgAndJointNameMap.put(key, value);
					}
				}
				if (orgAndJointNameMap.containsKey(doctorInfoExts.get(i).getOrgFlow())) {
					orgName = (String) orgAndJointNameMap.get(doctorInfoExts.get(i).getOrgFlow());
					joinName = (String)doctorInfoExts.get(i).getOrgName();
					jointFlag = "是";
				} else {
					jointFlag = "否";
					orgName = (String)doctorInfoExts.get(i).getOrgName();
				}

				String age ="";
				if(StringUtil.isNotBlank((String)doctorInfoExts.get(i).getUserBirthday())) {
					String birthDay = (String)doctorInfoExts.get(i).getUserBirthday();
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(birthDay.substring(0, 4))) + "";
				}
//				SysUser sysUser = doctorInfoExt.getSysUser();
//				ResDoctor doctor = doctorInfoExt.getResDoctor();
//				JsresRecruitInfo recruitInfo = doctorInfoExt.getRecruitInfo();

				String CretType = "";
				String area = "";
				String cretTypeId = doctorInfoExts.get(i).getCretTypeId() == null ? "" : doctorInfoExts.get(i).getCretTypeId();
				if (CertificateTypeEnum.Shenfenzheng.getId().equals(cretTypeId)) {
					CretType = "居民身份证";
					area="中国大陆";
				}
//				else if (CertificateTypeEnum.Junguanzheng.getId().equals(cretTypeId)) {
//					CretType = "军队证件";
//					area="中国大陆";
//				}
				else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "护照";
					area="";
				}else if (CertificateTypeEnum.HongKongMacao.getId().equals(cretTypeId)) {
					CretType = "港澳居民来往内地通行证";
					area="";
				}else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "台湾居民来往内地通行证";
					area="";
				}else {
					CretType = "其他";
					area = "其他";
				}
				String isYearGraduate = "";
				if (GlobalConstant.FLAG_Y.equals(doctorInfoExts.get(i).getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}
				String recruitDate = doctorInfoExts.get(i).getRecruitDate() == null ? "" : doctorInfoExts.get(i).getRecruitDate();
				if (StringUtil.isNotBlank(recruitDate) && recruitDate.length() >= 7) {
					recruitDate = recruitDate.substring(0, 4) + "-" + recruitDate.substring(5, 7) ;
				}
				//解析xml
//				PubUserResume userResume = (PubUserResume) doctorInfoExts.get(i).get("USER_RESUME");
				String xml = doctorInfoExts.get(i).getUserResume();
				UserResumeExtInfoForm userResumeExt = null;
                try {
                    userResumeExt = userResumeBiz.converyToJavaBean(xml, UserResumeExtInfoForm.class);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                if(userResumeExt == null ){
                    userResumeExt = new UserResumeExtInfoForm();
                }
//				userResumeExt = parseExtInfoXml(xml);
				//是否是执业医师和编号`
				String qualificationFlag = "";
				if (GlobalConstant.FLAG_Y .equals(userResumeExt.getIsPassQualifyingExamination())) {
					qualificationFlag = "是";
				} else {
					qualificationFlag = "否";
					userResumeExt.setQualificationMaterialCode("");
				}
				//研究生
				String masterFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
					userResumeExt.setMasterDegreeName("");
					userResumeExt.setMasterDegreeTypeName("");
					userResumeExt.setMasterGraSchoolName("");
					userResumeExt.setMasterGraTime("");
					userResumeExt.setMasterMajor("");
					masterFlag = "否";
				} else {
					masterFlag = "是";
				}
				//博士
				String doctorFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
					userResumeExt.setDoctorDegreeName("");
					userResumeExt.setDoctorDegreeTypeName("");
					userResumeExt.setDoctorGraSchoolName("");
					userResumeExt.setDoctorGraTime("");
					userResumeExt.setDoctorMajor("");
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
				}
				//工作单位
				String property = "";
				String yyjb="";
				String yydc="";
				String hospitalCateName="";
				String hospitalAttrName="";
				String workOrg = (String)doctorInfoExts.get(i).getWorkOrgName();
				if (ResDocTypeEnum.Company.getId().equals(doctorInfoExts.get(i).getDoctorTypeId())) {
					ResBase resBase = resBaseMapper.selectByPrimaryKey((String)doctorInfoExts.get(i).getInfoOrgFlow());
					if (resBase != null && jointFlag.equals("是")) {
						property = resBase.getBaseGradeName();
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"1".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setHospitalAttrName("");
						userResumeExt.setHospitalCategoryName("");
						userResumeExt.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"3".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setBasicHealthOrgName("");
					}
					if("1".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("2".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("3".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="未定等";
					}
					if("4".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="甲等";
					}
					if("5".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="乙等";
					}
					if("6".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="未定级";
						yydc="未定等";
					}
					if("1".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省、自治区、直辖市属";
					}
					if("2".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省辖市（地区、州、盟、直辖市区）属";
					}
					if("3".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="县级市（省辖市区）属";
					}
					if("1".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getHospitalCategoryName();
					}
					if("3".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getBasicHealthOrgName();
					}
				} else {
					workOrg = "";
					userResumeExt.setMedicalHeaithOrgName("");
				}

				//研究生毕业时间
				String masterGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getMasterGraTime())) {
					masterGraTime = userResumeExt.getMasterGraTime().substring(0, 4);
				}
				//博士毕业时间
				String doctorGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getDoctorGraTime())) {
					doctorGraTime = userResumeExt.getDoctorGraTime().substring(0, 4);
				}
				//本科毕业时间
				String graduationTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getGraduationTime())) {
					graduationTime = userResumeExt.getGraduationTime().substring(0, 4);
				}
				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				//规培年限
				String trainYear = "";
				if(StringUtil.isNotBlank((String)doctorInfoExts.get(i).getTrainYear())){
					trainYear = (String)doctorInfoExts.get(i).getTrainYear();
				}
				switch (trainYear){
					case "OneYear":{trainYear="一年";break;}
					case "TwoYear":{trainYear="两年";break;}
					case "ThreeYear":{trainYear="三年";break;}
				}
				dataList = new String[]{
//						orgName,
						(String)doctorInfoExts.get(i).getOrgName(),
						(String)doctorInfoExts.get(i).getUserName(),
						(String)doctorInfoExts.get(i).getSexName(),
						(String)doctorInfoExts.get(i).getUserBirthday(),
						CretType,
						(String)doctorInfoExts.get(i).getIdNo(),
						area,
						(String)doctorInfoExts.get(i).getNationName(),
						(String)doctorInfoExts.get(i).getUserPhone(),
						(String)doctorInfoExts.get(i).getUserEmail(),
						(String)doctorInfoExts.get(i).getUserQq(),
						isYearGraduate,
						(String)doctorInfoExts.get(i).getSpeName(),
						qualificationFlag,
						userResumeExt.getDoctorQualificationCertificateCode(),
						(String)doctorInfoExts.get(i).getSessionNumber(),
						recruitDate,
						"否",
						"",
						trainYear,
						(String)doctorInfoExts.get(i).getDoctorTypeName(),
						userResumeExt.getGraduatedName(),
						graduationTime,
						userResumeExt.getSpecialized(),
						isGeneralOrderOrientationTrainee,
						userResumeExt.getDegreeName(),
						masterFlag,
						userResumeExt.getMasterGraSchoolName(),
						masterGraTime,
						userResumeExt.getMasterMajor(),
						userResumeExt.getMasterDegreeName(),
						userResumeExt.getMasterDegreeTypeName(),

						doctorFlag,
						userResumeExt.getDoctorGraSchoolName(),
						doctorGraTime,
						userResumeExt.getDoctorMajor(),
						userResumeExt.getDoctorDegreeName(),
						userResumeExt.getDoctorDegreeTypeName(),

						workOrg,
						yyjb,
						yydc,
						hospitalCateName,
						hospitalAttrName,

						jointFlag,
						joinName,
						"",
						"",
						""
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		return wb;
	}

	//缓存一份到服务器
	public void cacheXlsxFileToServer(String  fileName,HSSFWorkbook book){
		String cachePath = InitConfig.getSysCfg("upload_base_dir") + File.separator + "doctorRecruitInfo";
		File file = new File(cachePath);
		if (!file.exists()) {
			file.mkdir();
		}
		if (cachePath.endsWith("/")) {
			cachePath += fileName;
		} else {
			cachePath += "/" + fileName;
		}
		File cacheFile = new File(cachePath);
		BufferedOutputStream bufferedOutputStreamCache =null;
		try {
			OutputStream os = new FileOutputStream(cacheFile);
			bufferedOutputStreamCache = new BufferedOutputStream(os);
			book.write(bufferedOutputStreamCache);
			bufferedOutputStreamCache.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(bufferedOutputStreamCache!=null){
					bufferedOutputStreamCache.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//让用户直接从服务器下载已缓存好的文件
	public void  directDownloadToServer(HttpServletRequest request, HttpServletResponse response, String  fileName){
		String cachePath = InitConfig.getSysCfg("upload_base_dir") + "/" + "doctorRecruitInfo";
		if (cachePath.endsWith("/")) {
			cachePath += fileName;
		} else {
			cachePath += "/" + fileName;
		}
		File cacheFile = new File(cachePath);
		BufferedInputStream bins = null;
		BufferedOutputStream bouts =null;
		try {
			// 针对IE或者以IE为内核的浏览器
			String userAgent = request.getHeader("User-Agent");
			if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
				fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			} else {
				// 非IE浏览器的处理：
				fileName = new String(fileName.getBytes("UTF-8"),
						"ISO-8859-1");
			}
			response.setContentType("application/octet-stream");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");

			InputStream ins = new FileInputStream(cacheFile);
			bins = new BufferedInputStream(ins);// 放到缓冲流里面
			OutputStream outs = response.getOutputStream();// 获取文件输出IO流
			bouts = new BufferedOutputStream(outs);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			// 开始向网络传输文件流
			while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
				bouts.write(buffer, 0, bytesRead);
			}
			bouts.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(bins!=null){
					bins.close();
				}
				if(bouts!=null){
					bouts.close();
				}
				cacheFile.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
