package com.pinde.sci.biz.jszy.impl;

import com.pinde.core.common.enums.pub.UserNationEnum;
import com.pinde.core.common.enums.pub.UserSexEnum;
import com.pinde.core.common.enums.sys.CertificateTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyResDoctorBiz;
import com.pinde.sci.biz.jszy.IJszyResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.JsresUserBalcklistMapper;
import com.pinde.sci.dao.base.ResBaseMapper;
import com.pinde.sci.dao.jszy.JszyResDoctorExtMapper;
import com.pinde.sci.dao.jszy.JszyResUserBalckListExtMapper;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.form.jszy.JszyBackTrainForm;
import com.pinde.sci.model.jszy.JszyDoctorInfoExt;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorSchProcess;
import com.pinde.sci.model.mo.ResRec;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.*;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JszyResDoctorBizImpl implements IJszyResDoctorBiz {

    private static final Logger logger = LoggerFactory.getLogger(JszyResDoctorBizImpl.class);

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
    private IJszyResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private ResBaseMapper resBaseMapper;
	@Autowired
	private JszyResDoctorExtMapper jsresDoctorExtMapper;

	@Autowired
	private JsresUserBalcklistMapper balcklistMapper;
	@Autowired
	private JszyResUserBalckListExtMapper balcklistExtMapper;

	@Override
	public int saveDoctorInfo(SysUser user, ResDoctor doctor, BaseUserResumeExtInfoForm userResumeExt) {
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
            user.setEducationName(com.pinde.core.common.enums.DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
		}else{
			//user.setEducationName("");
		}
		//学位
		if(StringUtil.isNotBlank(user.getDegreeId())){
            user.setDegreeName(com.pinde.core.common.enums.DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
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
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
		String userFlow = user.getUserFlow();
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(userFlow);
		if(pubUserResume == null){
			pubUserResume = new PubUserResume();
		}
		//JavaBean转换成xml
		if(StringUtil.isNotBlank(userResumeExt.getGraduatedName())){//本科毕业院校
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("GraduateSchool");
	    	for(SysDict s:dictList){
	    		if (s.getDictName().equals(userResumeExt.getGraduatedName())) {
	    			userResumeExt.setGraduatedId(s.getDictId());
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getDegreeId())){//本科学位
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("UserDegree");
	    	for(SysDict s:dictList){
	    		if (s.getDictId().equals(userResumeExt.getDegreeId())) {
	    			userResumeExt.setDegreeName(s.getDictName());
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getMasterDegreeId())){//硕士学位
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("UserDegree");
	    	for(SysDict s:dictList){
	    		if (s.getDictId().equals(userResumeExt.getMasterDegreeId())) {
	    			userResumeExt.setMasterDegreeName(s.getDictName());
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getMasterGraSchoolName())){//硕士毕业院校
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("GraduateSchool");
	    	for(SysDict s:dictList){
	    		if (s.getDictName().equals(userResumeExt.getMasterGraSchoolName())) {
	    			userResumeExt.setMasterGraSchoolId(s.getDictId());
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getDoctorDegreeId())){//博士学位
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("UserDegree");
	    	for(SysDict s:dictList){
	    		if (s.getDictId().equals(userResumeExt.getDoctorDegreeId())) {
	    			userResumeExt.setDoctorDegreeName(s.getDictName());
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getDoctorGraSchoolName())){//博士毕业院校
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("GraduateSchool");
	    	for(SysDict s:dictList){
	    		if (s.getDictName().equals(userResumeExt.getDoctorGraSchoolName())) {
	    			userResumeExt.setDoctorGraSchoolId(s.getDictId());
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getMedicalHeaithOrgId())){//医疗机构
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("MedicalHeaithOrg");
	    	for(SysDict s:dictList){
	    		if (s.getDictId().equals(userResumeExt.getMedicalHeaithOrgId())) {
	    			userResumeExt.setMedicalHeaithOrgName(s.getDictName());
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getHospitalAttrId())){
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("HospitalAttr");
	    	for(SysDict s:dictList){
	    		if (s.getDictId().equals(userResumeExt.getHospitalAttrId())) {
	    			userResumeExt.setHospitalAttrName(s.getDictName());
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getHospitalCategoryId())){
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("HospitalCategory");
	    	for(SysDict s:dictList){
	    		if (s.getDictId().equals(userResumeExt.getHospitalCategoryId())) {
	    			userResumeExt.setHospitalCategoryName(s.getDictName());
				}
	    	}
		}
		if(StringUtil.isNotBlank(userResumeExt.getBasicHealthOrgId())){
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("BasicHealthOrg");
			for(SysDict s:dictList){
				if (s.getDictId().equals(userResumeExt.getBasicHealthOrgId())) {
					userResumeExt.setBasicHealthOrgName(s.getDictName());
				}
			}
		}
		if(StringUtil.isNotBlank(userResumeExt.getBaseAttributeId())){
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("BaseAttribute");
			for(SysDict s:dictList){
				if (s.getDictId().equals(userResumeExt.getBaseAttributeId())) {
					userResumeExt.setBaseAttributeName(s.getDictName());
				}
			}
		}
		if(StringUtil.isNotBlank(userResumeExt.getBasicHealthOrgLevelId())){
            List<SysDict> dictList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("BasicHealthOrgLevel");
			for(SysDict s:dictList){
				if (s.getDictId().equals(userResumeExt.getBasicHealthOrgLevelId())) {
					userResumeExt.setBasicHealthOrgLevelName(s.getDictName());
				}
			}
		}
		//毕业院校
		if(StringUtil.isNotBlank(doctor.getGraduatedName())){
            Map<String, List<SysDict>> sysListDictMap = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap;
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
            doctor.setDoctorTypeName(com.pinde.core.common.enums.ResDocTypeEnum.getNameById(doctor.getDoctorTypeId()));
            if (com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId()) || com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
				if("1".equals(userResumeExt.getMedicalHeaithOrgId())){
					//基层医疗卫生机构
					userResumeExt.setBasicHealthOrgId("");//基层医疗卫生机构
					userResumeExt.setBasicHealthOrgName("");
					userResumeExt.setBasicHealthOrgLevelId("");//基层医疗卫生机构等级
					userResumeExt.setBasicHealthOrgLevelName("");
				}else if("2".equals(userResumeExt.getMedicalHeaithOrgId()) || "4".equals(userResumeExt.getMedicalHeaithOrgId())){
					//医院相关
					userResumeExt.setHospitalAttrId("");//医院性质
					userResumeExt.setHospitalAttrName("");
					userResumeExt.setHospitalCategoryId("");//医院类别
					userResumeExt.setHospitalCategoryName("");
					userResumeExt.setBaseAttributeId("");//单位等级
					userResumeExt.setBaseAttributeName("");
					//基层医疗卫生机构
					userResumeExt.setBasicHealthOrgId("");//基层医疗卫生机构
					userResumeExt.setBasicHealthOrgName("");
					userResumeExt.setBasicHealthOrgLevelId("");//基层医疗卫生机构等级
					userResumeExt.setBasicHealthOrgLevelName("");
				}else if("3".equals(userResumeExt.getMedicalHeaithOrgId())){
					//医院相关
					userResumeExt.setHospitalCategoryId("");//医院类别
					userResumeExt.setHospitalCategoryName("");
					userResumeExt.setBaseAttributeId("");//单位等级
					userResumeExt.setBaseAttributeName("");
				}
                if (com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())) {
					doctor.setSendCityId("");
					doctor.setSendCityName("");
				}
            } else if (com.pinde.core.common.enums.ResDocTypeEnum.Social.getId().equals(doctor.getDoctorTypeId())) {
				//派送单位（派送学校）相关
				doctor.setWorkOrgId("");
				doctor.setWorkOrgName("");
				doctor.setSendCityId("");
				doctor.setSendCityName("");
				//单位人类型
				doctor.setCompanyType("");
				/************以下医疗卫生机构相关************/
				userResumeExt.setMedicalHeaithOrgId("");
				userResumeExt.setMedicalHeaithOrgName("");
				//医院相关
				userResumeExt.setHospitalAttrId("");//医院性质
				userResumeExt.setHospitalAttrName("");
				userResumeExt.setHospitalCategoryId("");//医院类别
				userResumeExt.setHospitalCategoryName("");
				userResumeExt.setBaseAttributeId("");//单位等级
				userResumeExt.setBaseAttributeName("");
				//基层医疗卫生机构
				userResumeExt.setBasicHealthOrgId("");//基层医疗卫生机构
				userResumeExt.setBasicHealthOrgName("");
				userResumeExt.setBasicHealthOrgLevelId("");//基层医疗卫生机构等级
				userResumeExt.setBasicHealthOrgLevelName("");
				//基层医疗卫生机构相关
				/************以上医疗卫生机构相关************/
            } else if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())) {
				doctor.setSendCityId("");
				doctor.setSendCityName("");
				//单位人类型
				doctor.setCompanyType("");
				/************以下医疗卫生机构相关************/
				userResumeExt.setMedicalHeaithOrgId("");
				userResumeExt.setMedicalHeaithOrgName("");
				//医院相关
				userResumeExt.setHospitalAttrId("");//医院性质
				userResumeExt.setHospitalAttrName("");
				userResumeExt.setHospitalCategoryId("");//医院类别
				userResumeExt.setHospitalCategoryName("");
				userResumeExt.setBaseAttributeId("");//单位等级
				userResumeExt.setBaseAttributeName("");
				//基层医疗卫生机构
				userResumeExt.setBasicHealthOrgId("");//基层医疗卫生机构
				userResumeExt.setBasicHealthOrgName("");
				userResumeExt.setBasicHealthOrgLevelId("");//基层医疗卫生机构等级
				userResumeExt.setBasicHealthOrgLevelName("");
				//基层医疗卫生机构相关
				/************以上医疗卫生机构相关************/
			}
		}else{
			doctor.setDoctorTypeName("");
		}
		//是否是对口支援
        if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(doctor.getIsPartner())) {
			doctor.setSourceProvincesId("");//生源省份
			doctor.setSourceProvincesName("");
		}
		//是否硕士在读
		if("1".equals(userResumeExt.getMasterStatue())){
			userResumeExt.setMasterDegreeId("");
			userResumeExt.setMasterDegreeName("");
			userResumeExt.setMasterDegreeTypeId("");
			userResumeExt.setMasterDegreeTypeName("");
			userResumeExt.setMasterGraTime("");
		}
		//是否博士在读
		if("1".equals(userResumeExt.getDoctorStatue())){
			userResumeExt.setDoctorDegreeId("");
			userResumeExt.setDoctorDegreeName("");
			userResumeExt.setDoctorDegreeTypeId("");
			userResumeExt.setDoctorDegreeTypeName("");
			userResumeExt.setDoctorGraTime("");
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
            doctor.setWorkOrgLevelName(com.pinde.core.common.enums.DictTypeEnum.getDictName(com.pinde.core.common.enums.DictTypeEnum.BaseLevel, doctor.getWorkOrgLevelId()));
		}else{
			doctor.setWorkOrgLevelId("");
		}
		//学位类型
		if(StringUtil.isNotBlank(doctor.getDegreeCategoryId())){
            doctor.setDegreeCategoryName(com.pinde.core.common.enums.JsResDegreeCategoryEnum.getNameById(doctor.getDegreeCategoryId()));
		}else{
			doctor.setDegreeCategoryName("");
		}
		//派送单位或者学校
		if(StringUtil.isNotBlank(doctor.getWorkOrgName())){
            if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())) {
                List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
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
		String xmlContent = JaxbUtil.convertToXml(userResumeExt);
		pubUserResume.setUserResume(xmlContent);
		userResumeBiz.savePubUserResume(user, pubUserResume);
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
            return com.pinde.core.common.GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize + "M！";
		}
        return com.pinde.core.common.GlobalConstant.FLAG_Y;//可执行保存
	}
	
	@Override
	public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName){
        String path = com.pinde.core.common.GlobalConstant.FLAG_N;
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
                logger.error("", e);
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
                    logger.error("", e);
					throw new RuntimeException("删除文件失败！");
				}
			}
			path = folderName + "/"+dateString+"/"+originalFilename;
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
	public ResRec searResRecZhuZhi(String formFileName, String recFlow,
			String operUserFlow, String roleFlag, String processFlow,String recordFlow,
			 HttpServletRequest req) {
		operUserFlow = StringUtil.defaultIfEmpty(operUserFlow,GlobalContext.getCurrentUser().getUserFlow());
		SysUser sysUser=iUserBiz.readSysUser(operUserFlow);
		ResDoctorSchProcess resDoctorSchProcess=iResDoctorProcessBiz.read(processFlow);
        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.getNameById(formFileName);
		String currDate=DateUtil.getCurrDateTime();
		ResRec rec=new ResRec();
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
		String recContent=getXmlByRequest(req,formFileName);
		rec.setRecContent(recContent);
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
	public void exportForBack2(List<ResDocotrDelayTeturn> list,Map<Object, Object> backInfoMap,HttpServletResponse response,String flag) throws Exception {
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

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
				"备注(培训基地意见)"
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
				rownum++;
			}
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
			HSSFRow rowDepts= sheet.createRow(rownum);
			HSSFCell cell = rowDepts.createCell(0);
			String value="";
            value = "合计退培：" + list.size() + " " + "退培比例：" + backInfoMap.get(com.pinde.core.common.GlobalConstant.FLAG_N);
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
		public void exportForBack(Map<Object, Object> backInfoMap,HttpServletResponse response,String flag) throws Exception {
			//创建工作簿
			HSSFWorkbook wb = new HSSFWorkbook();  
			// 为工作簿添加sheet  
		    HSSFSheet sheet = wb.createSheet("sheet1"); 
		    //定义将用到的样式 
		    HSSFCellStyle styleCenter = wb.createCellStyle(); //居中 
		    styleCenter.setAlignment(HorizontalAlignment.CENTER);
		    
		    HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		    styleLeft.setAlignment(HorizontalAlignment.LEFT);
		    styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		    
		    HSSFCellStyle stylevwc = wb.createCellStyle(); //居中 
		    stylevwc.setAlignment(HorizontalAlignment.CENTER);
		    stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
		    
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
            List<ResRec> resRecs = (List<ResRec>) backInfoMap.get(com.pinde.core.common.GlobalConstant.FLAG_Y);
		    if(resRecs!=null&&!resRecs.isEmpty()){
		    	for(ResRec sd : resRecs){
		    		JszyBackTrainForm form =(JszyBackTrainForm) backInfoMap.get(sd.getRecFlow());
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
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
		    		HSSFRow rowDepts= sheet.createRow(rownum);
		    		HSSFCell cell = rowDepts.createCell(0);
		    		String value="";
                value = "合计退培：" + resRecs.size() + " " + "退培比例：" + backInfoMap.get(com.pinde.core.common.GlobalConstant.FLAG_N);
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
    public void exportForDetail(List<JszyDoctorInfoExt> doctorInfoExts, HttpServletResponse response) throws Exception {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setColor(HSSFFont.COLOR_NORMAL);
		font.setBold(true);

        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter2 = wb.createCellStyle(); //居中
        styleCenter2.setAlignment(HorizontalAlignment.CENTER);
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
		styleCenter.setFont(font);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setFont(font);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
		stylevwc.setFont(font);
        //列宽自适应
        HSSFRow rowDep = sheet.createRow(0);//第一行
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));//合并单元格
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(stylevwc);
        cellOne.setCellValue("培训基地");

        sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 23));//合并单元格
        HSSFCell cell = rowDep.createCell(1);
        cell.setCellStyle(stylevwc);
        cell.setCellValue("学员基本信息");

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 24, 39));
        HSSFCell cellTwo = rowDep.createCell(24);
        cellTwo.setCellStyle(styleCenter);
        cellTwo.setCellValue("学历信息");

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 40, 50));
        HSSFCell cellThree = rowDep.createCell(40);
        cellThree.setCellStyle(styleCenter);
        cellThree.setCellValue("单位信息");


        HSSFRow rowTwo = sheet.createRow(1);//第二行
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 24, 27));//合并单元格
        HSSFCell cellFour = rowTwo.createCell(24);
        cellFour.setCellStyle(styleCenter);
        cellFour.setCellValue("本科");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 28, 33));//合并单元格
        HSSFCell cellFive = rowTwo.createCell(28);
        cellFive.setCellStyle(styleCenter);
        cellFive.setCellValue("硕士研究生");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 34, 39));//合并单元格
        HSSFCell cellSix = rowTwo.createCell(34);
        cellSix.setCellStyle(styleCenter);
        cellSix.setCellValue("博士研究生");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 40, 41));//合并单元格
        HSSFCell cellSeven = rowTwo.createCell(40);
        cellSeven.setCellStyle(styleCenter);
		cellSeven.setCellValue("所在单位");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 42, 44));//合并单元格
        HSSFCell cellEight = rowTwo.createCell(42);
        cellEight.setCellStyle(styleCenter);
        cellEight.setCellValue("医院");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 45, 47));//合并单元格
        HSSFCell cellNine = rowTwo.createCell(45);
        cellNine.setCellStyle(styleCenter);
		cellNine.setCellValue("基层医疗卫生机构");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 48, 50));//合并单元格
        HSSFCell cellTen = rowTwo.createCell(48);
        cellTen.setCellStyle(styleCenter);
		cellTen.setCellValue("协同机构");

        HSSFRow rowThree = sheet.createRow(2);//第三行
        String[] titles = new String[]{
                "基地名称",
                "姓名",
                "性别",
                "出生年月",
                "证件类型",
                "证件号",
                "民族",
                "手机号",
                "邮箱",
                "QQ（非必填）",
                "往届/应届",
                "培训专业",
                "对应专业",
                "二级专业",
                "执业医师资格",
                "医师资格证书编号",
                "参培年份",
				"培训状态",
                "实际培训开始时间",
                "培训年限核定",
                "学员类型",
                "订单定向培养",
                "是否是对口支援",
                "生源省份",
                "毕业院校",
                "毕业时间",
                "毕业专业",
                "学位",
                "状态",
                "就读院校",
                "入学时间",
                "所学专业",
                "学位",
                "学位类型",
				"状态",
				"就读院校",
				"入学时间",
				"所学专业",
				"学位",
				"学位类型",
                "名称",
                "医疗卫生机构",
                "性质",
                "类别",
                "等级",
				"性质",
				"类别",
				"等级",
                "在协同单位培训",
                "协同单位名称",
                "协同单位等级",
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowThree.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 156);
        }

        int rowNum = 3;
        String[] dataList = null;
        if (doctorInfoExts != null && !doctorInfoExts.isEmpty()) {
            for (int i = 0; i < doctorInfoExts.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行

                JszyDoctorInfoExt doctorInfoExt = doctorInfoExts.get(i);
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
					age = doctorInfoExt.getSysUser().getUserBirthday().substring(0,7);
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
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
                    isYearGraduate = "应届";
                } else {
                    isYearGraduate = "往届";
                }
				String trainYear="";
				if(StringUtil.isNotBlank(doctor.getTrainingYears()))
				{
					switch (doctor.getTrainingYears())
					{
						case  "1" : trainYear="一年";break;
						case  "2" : trainYear="二年";break;
						case  "3" : trainYear="三年";break;
					}
				}
                String recruitDate = doctorInfoExt.getRecruitDate().substring(0, 4) + "年" + doctorInfoExt.getRecruitDate().substring(5, 7) + "月";
                //解析xml
                PubUserResume userResume = doctorInfoExt.getUserResume();
                BaseUserResumeExtInfoForm userResumeExt = null;
                userResumeExt = userResumeBiz.converyToJavaBean(userResume.getUserResume(), BaseUserResumeExtInfoForm.class);

                //是否是执业医师和编号
                String qualificationFlag = "";
                if (StringUtil.isNotBlank(userResumeExt.getQualificationMaterialCode())) {
                    qualificationFlag = "是";
                } else {
                    userResumeExt.setQualificationMaterialCode("");
                }
                //研究生
                //研究生状态
				String yjszt="";
				if("1".equals(userResumeExt.getMasterStatue()))
				{
					yjszt="在读";
				}
				if("2".equals(userResumeExt.getMasterStatue()))
				{
					yjszt="已毕业";
				}
                //博士状态
				String bszt="";
				if("1".equals(userResumeExt.getDoctorStatue()))
				{
					bszt="在读";
				}
				if("2".equals(userResumeExt.getDoctorStatue()))
				{
					bszt="已毕业";
				}

				//DegreeName
				String degreeName="无";
				if(!"004".equals(userResumeExt.getDegreeId()))
				{
					degreeName="有";
				}
				//DegreeName
				String masterDegreeName="无";
				if(!"004".equals(userResumeExt.getMasterDegreeId()))
				{
					masterDegreeName="有";
				}
				//DegreeName
				String doctorDegreeName="无";
				if(!"004".equals(userResumeExt.getDoctorDegreeId()))
				{
					doctorDegreeName="有";
				}
                String masterFlag = "";
                if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(userResumeExt.getIsMaster())) {
                    userResumeExt.setMasterDegreeName("");
                    userResumeExt.setMasterDegreeTypeName("");
                    userResumeExt.setMasterGraSchoolName("");
                    userResumeExt.setMasterGraTime("");
                    userResumeExt.setMasterMajor("");
					userResumeExt.setMasterStartTime("");
					yjszt="";
					masterDegreeName="";
                    masterFlag = "否";
                } else {
                    masterFlag = "是";
                }
                //博士
                String doctorFlag = "";
                if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(userResumeExt.getIsDoctor())) {
                    userResumeExt.setDoctorDegreeName("");
                    userResumeExt.setDoctorDegreeTypeName("");
                    userResumeExt.setDoctorGraSchoolName("");
                    userResumeExt.setDoctorGraTime("");
                    userResumeExt.setDoctorMajor("");
					userResumeExt.setDoctorStartTime("");
					doctorDegreeName="";
					bszt="";
                    doctorFlag = "否";
                } else {
                    doctorFlag = "是";
                }
				//订单定向培养
				String dingxiang="";
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee()))
				{
					dingxiang="是";
				}
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee()))
				{
					dingxiang="否";
				}
				//是否是对口支援
				String duiKou="";
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(doctor.getIsPartner()))
				{
					duiKou="是";
				}
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(doctor.getIsPartner()))
				{
					duiKou="否";
				}
				//学员类型
				String doctorTypeName=doctor.getDoctorTypeName();
//				if(com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId()))
//				{
//					doctorTypeName="";
//					if("1".equals(doctor.getCompanyType()))
//					{
//						doctorTypeName="本单位人";
//					}
//					if("2".equals(doctor.getCompanyType()))
//					{
//						doctorTypeName="委培单位人";
//					}
//				}
				String getMedicalHeaithOrgName="";
				String getHospitalAttrName="";
				String getHospitalCategoryName="";
				String getBaseAttributeName="";
				String getNextHospitalAttrName="";
				String getBasicHealthOrgName="";
				String getBasicHealthOrgLevelName="";
                //工作单位
                String property = "";
                if (com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId()) || com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
                    ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
                    if (resBase != null && jointFlag.equals("是")) {
                        property = resBase.getBaseGradeName();
                    }
                    if ("1".equals(userResumeExt.getMedicalHeaithOrgId())) {
						getHospitalAttrName=userResumeExt.getHospitalAttrName();
						getHospitalCategoryName=userResumeExt.getHospitalCategoryName();
						getBaseAttributeName=userResumeExt.getBaseAttributeName();
                    }
                    if ("3".equals(userResumeExt.getMedicalHeaithOrgId())) {
						getNextHospitalAttrName=userResumeExt.getHospitalAttrName();
						getBasicHealthOrgName=userResumeExt.getBasicHealthOrgName();
						getBasicHealthOrgLevelName=userResumeExt.getBasicHealthOrgLevelName();
                    }

                }
				String workOrgName=doctor.getWorkOrgName();
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
                        doctor.getTrainingTypeName(),
                        doctor.getTrainingSpeName(),
                        doctor.getSecondSpeName(),
                        qualificationFlag,
                        userResumeExt.getQualificationMaterialCode(),
                        doctorInfoExt.getSessionNumber(),
						doctor.getDoctorStatusName(),//医师状态
                        recruitDate,
						trainYear,
						doctorTypeName,
						dingxiang,
						duiKou,
						doctor.getSourceProvincesName(),

                        userResumeExt.getGraduatedName(),
						userResumeExt.getGraduationTime(),
                        userResumeExt.getSpecialized(),
						degreeName,

                        yjszt,
                        userResumeExt.getMasterGraSchoolName(),
						userResumeExt.getMasterStartTime(),
                        userResumeExt.getMasterMajor(),
						masterDegreeName,
                        userResumeExt.getMasterDegreeTypeName(),

						bszt,
                        userResumeExt.getDoctorGraSchoolName(),
						userResumeExt.getDoctorStartTime(),
                        userResumeExt.getDoctorMajor(),
						doctorDegreeName,
                        userResumeExt.getDoctorDegreeTypeName(),

                        workOrgName,
                        userResumeExt.getMedicalHeaithOrgName(),

                        getHospitalAttrName,
                        getHospitalCategoryName,
                        getBaseAttributeName,

						getNextHospitalAttrName,
						getBasicHealthOrgName,
						getBasicHealthOrgLevelName,

                        jointFlag,
                        joinName,
                        property
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter2);
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
	public List<Map<String,Object>> getDocByOrg(Map<String,Object> paramMap){
		return jsresDoctorExtMapper.getDocByOrg(paramMap);
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
			userBalck.setReasonYj("您的信息已被纳入我省医务人员诚信系统，5年内不得进入我省培训基地接受住院医师规范化培训。如有相关疑问，请与相关管理部门联系。");
			userBalck.setOperTypeId("1");
			userBalck.setOperTypeName("自动");
            userBalck.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);

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
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
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
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserPhoneEqualTo(userPhone);
		List<JsresUserBalcklist> list = balcklistMapper.selectByExample(jsresUserBalcklistExample);
		if (list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
	@Override
	public List<Map<String, Object>> searchGraduatesByOrg(Map<String, Object> paramMap) {
		return jsresDoctorExtMapper.selectGraduatesByOrg(paramMap);
	}
	@Override
	public List<Map<String,Object>> searchGraduates(Map<String, Object> paramMap) {
		return jsresDoctorExtMapper.selectGraduates(paramMap);
	}
}
