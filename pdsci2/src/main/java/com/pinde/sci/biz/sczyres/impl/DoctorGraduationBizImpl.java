package com.pinde.sci.biz.sczyres.impl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResRegBiz;
import com.pinde.sci.biz.sczyres.DoctorGraduationBiz;
import com.pinde.sci.biz.sczyres.DoctorRecruitBiz;
import com.pinde.sci.biz.sczyres.DoctorSingupBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.sczyres.SczyGraduationExtMapper;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.form.sczyres.ExtInfoForm;
import com.pinde.sci.form.sczyres.WorkResumeForm;
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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@Service
@Transactional(rollbackFor=Exception.class)
public class DoctorGraduationBizImpl implements DoctorGraduationBiz{

	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private ResDoctorMapper doctorMapper;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ResDoctorRecruitMapper doctorRecruitMapper;
	@Autowired
	private IResRegBiz resResBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private DoctorRecruitBiz recruitBiz;
	@Autowired
	private PubUserResumeMapper resumpMapper;
	@Autowired
	private DoctorSingupBiz doctorSingupBiz;
	@Autowired
	private ScresSchInfoMapper scresSchInfoMapper;
	@Autowired
	private PubFileMapper pubFileMapper;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private ScresGraduationApplyMapper applyMapper;
	@Autowired
	private SczyGraduationExtMapper graduationExtMapper;
	@Autowired
	private ScresGraduationTicketMapper ticketMapper;
	@Autowired
	private SysCfgMapper sysCfgMapper;

	private static final String EXT_INFO_ROOT = "extInfo";
	private static final String EXT_INFO_ELE = "extInfoForm";
	private static final String WORK_RESUME_LIST_ELE = "workResumeList";
	private static final String WORK_RESUME_ELE = "workResumeForm";

	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
	}

	@Override
	public int saveBaseInfo(SysUser user, ResDoctor doctor, ExtInfoForm extInfoForm,String jsonData) {
		userBiz.updateUser(user);
		if(StringUtil.isBlank(doctor.getDoctorFlow())){
			doctor.setDoctorFlow(user.getUserFlow());
			GeneralMethod.setRecordInfo(doctor,true);
			doctorMapper.insertSelective(doctor);
		}
		doctorBiz.editDoctor(doctor);
		//保存额外信息
		PubUserResume resume = resumpMapper.selectByPrimaryKey(user.getUserFlow());
		String content = getXmlFromExtInfo(extInfoForm);
		if(resume==null){
			resume = new PubUserResume();
			resume.setUserFlow(user.getUserFlow());
			resume.setUserName(user.getUserName());
			resume.setUserResume(content);
			GeneralMethod.setRecordInfo(resume, true);
			this.resumpMapper.insertSelective(resume);
		}else{
			resume.setUserResume(content);
			GeneralMethod.setRecordInfo(resume,false);
			this.resumpMapper.updateByPrimaryKeySelective(resume);
		}
		String graduationYear = InitConfig.getSysCfg("res_graduation_year");
		List<Map<String,Object>> list = JSON.parseObject(jsonData,ArrayList.class);
		if(list!=null&&list.size()>0){
			for(Map<String,Object> map:list){
				PubFile searchFlie = new PubFile();
				searchFlie.setFileName(map.get("fileName").toString());
				searchFlie.setProductFlow(user.getUserFlow());
				List<PubFile> search = fileBiz.searchFiles(searchFlie);
				PubFile pubFile = new PubFile();
				pubFile.setFileName(map.get("fileName").toString());
				pubFile.setFilePath(map.get("filePath").toString());
				pubFile.setProductType(graduationYear);
				pubFile.setProductFlow(user.getUserFlow());
				if(search!=null&&search.size()>0){
					pubFile.setFileFlow(map.get("fileFlow").toString());
					pubFile.setRecordStatus("Y");
					GeneralMethod.setRecordInfo(pubFile,false);
					pubFileMapper.updateByPrimaryKey(pubFile);
				}else{
					pubFile.setFileFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(pubFile,true);
					pubFileMapper.insert(pubFile);
				}
			}
		}
		PubFile pubFile = new PubFile();

		return 1;
	}

	@Override
	public int saveSchInfo(String jsonData) {
		Map<String,Object> map = JSON.parseObject(jsonData, Map.class);
		String graduationYear = (String)map.get("graduationYear");
		Map<String,Object> baseInfoMap = (Map<String,Object>)map.get("baseInfo");
		String userFlow = (String)baseInfoMap.get("userFlow");
		String orgFlow = (String)baseInfoMap.get("orgFlow");
		String orgName = (String)baseInfoMap.get("orgName");
		String userName = (String)baseInfoMap.get("userName");
		String sexId = (String)baseInfoMap.get("sexId");
		String sexName = (String)baseInfoMap.get("sexName");
		String idNo = (String)baseInfoMap.get("idNo");
		if(StringUtil.isNotBlank(idNo)){
			SysUser existUser = this.userBiz.findByIdNoNotSelf(userFlow, idNo);
			if(existUser!=null){
				return -1;
			}
		}
		String educationId = (String)baseInfoMap.get("educationId");
		String educationName = (String)baseInfoMap.get("educationName");
		String degreeId = (String)baseInfoMap.get("degreeId");
		String degreeName = (String)baseInfoMap.get("degreeName");
		SysUser user = new SysUser();
		user.setUserFlow(userFlow);
		user.setOrgFlow(orgFlow);
		user.setUserName(userName);
		user.setOrgName(orgName);
		user.setSexId(sexId);
		user.setSexName(sexName);
		user.setIdNo(idNo);
		user.setEducationId(educationId);
		user.setEducationName(educationName);
		user.setDegreeId(degreeId);
		user.setDegreeName(degreeName);
		userBiz.updateUser(user);
		ResDoctor doctor = new ResDoctor();
		doctor.setOrgFlow(orgFlow);
		doctor.setOrgName(orgName);
		doctor.setDoctorName(userName);
		doctor.setDoctorFlow((String)baseInfoMap.get("doctorFlow"));
		doctor.setDoctorTypeId((String)baseInfoMap.get("doctorTypeId"));
		doctor.setDoctorTypeName((String)baseInfoMap.get("doctorTypeName"));
		doctor.setDoctorLicenseNo((String)baseInfoMap.get("doctorLicenseNo"));
		doctor.setTrainingSpeId((String)baseInfoMap.get("trainingSpeId"));
		doctor.setTrainingSpeName((String)baseInfoMap.get("trainingSpeName"));
		if(StringUtil.isBlank(doctor.getDoctorFlow())){
			doctor.setDoctorFlow(user.getUserFlow());
			GeneralMethod.setRecordInfo(doctor,true);
			doctorMapper.insertSelective(doctor);
		}
		doctorBiz.editDoctor(doctor);
		//保存额外信息
		PubUserResume resume = resumpMapper.selectByPrimaryKey(user.getUserFlow());
		if(resume==null){
			resume = new PubUserResume();
			resume.setUserFlow(user.getUserFlow());
			resume.setUserName(user.getUserName());
			ExtInfoForm extInfoForm = new ExtInfoForm();
			extInfoForm.setTrainingStartDate((String)baseInfoMap.get("trainingStartDate"));
			extInfoForm.setTrainingEndDate((String)baseInfoMap.get("trainingEndDate"));
			extInfoForm.setAssistantQualificationCertificateCode((String)baseInfoMap.get("assistantQualificationCertificateCode"));
			String content = getXmlFromExtInfo(extInfoForm);
			resume.setUserResume(content);
			GeneralMethod.setRecordInfo(resume, true);
			this.resumpMapper.insertSelective(resume);
		}else{
			String oldContent = resume.getUserResume();
			ExtInfoForm extInfoForm = doctorSingupBiz.parseExtInfoXml(oldContent);
			extInfoForm.setTrainingStartDate((String)baseInfoMap.get("trainingStartDate"));
			extInfoForm.setTrainingEndDate((String)baseInfoMap.get("trainingEndDate"));
			extInfoForm.setAssistantQualificationCertificateCode((String)baseInfoMap.get("assistantQualificationCertificateCode"));
			String content = getXmlFromExtInfo(extInfoForm);
			resume.setUserResume(content);
			GeneralMethod.setRecordInfo(resume,false);
			this.resumpMapper.updateByPrimaryKeySelective(resume);
		}
		List<Map<String,Object>> schInfoMapList = (List<Map<String,Object>>)map.get("schInfo");
		if(schInfoMapList!=null&&schInfoMapList.size()>0){
			for(Map<String,Object> single:schInfoMapList){
				String deptName = (String)single.get("deptName");
				String startDate = (String)single.get("startDate");
				String endDate = (String)single.get("endDate");
				String speId = (String)single.get("speId");
				String recordFlow = (String)single.get("recordFlow");
				ScresSchInfo scresSchInfo = new ScresSchInfo();
				scresSchInfo.setRecordFlow(recordFlow);
				scresSchInfo.setDoctorFlow(userFlow);
				scresSchInfo.setDeptName(deptName);
				scresSchInfo.setStartDate(startDate);
				scresSchInfo.setEndDate(endDate);
				scresSchInfo.setSpeId(speId);
				scresSchInfo.setGraduationYear(graduationYear);
				saveSingleSchInfo(scresSchInfo);
			}
		}
		return 1;
	}

	/**
	 * 获取额外信息xml
	 * @param extInfo
	 * @return
	 */
	private String getXmlFromExtInfo(ExtInfoForm extInfo){
		String xmlBody = null;
		if(extInfo!=null){
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement(EXT_INFO_ROOT);
			Element extInfoForm = root.addElement(EXT_INFO_ELE);
			Map<String,String> filedMap = getClassFieldMap(extInfo);
			if(filedMap!=null && filedMap.size()>0){
				for(String key : filedMap.keySet()){
					Element item = extInfoForm.addElement(key);
					item.setText(filedMap.get(key));
				}
			}

			List<WorkResumeForm> workResumeList = extInfo.getWorkResumeList();
			if(workResumeList!=null && workResumeList.size()>0){
				Element workResumeListEle = root.addElement(WORK_RESUME_LIST_ELE);
				for(WorkResumeForm resume : workResumeList){
					Element resumeEle = workResumeListEle.addElement(WORK_RESUME_ELE);
					Map<String,String> resumeFiledMap = getClassFieldMap(resume);
					if(resumeFiledMap!=null && resumeFiledMap.size()>0){
						for(String key : resumeFiledMap.keySet()){
							Element item = resumeEle.addElement(key);
							item.setText(resumeFiledMap.get(key));
						}
					}
				}
			}

			xmlBody=doc.asXML();
		}
		return xmlBody;
	}

	private Map<String,String> getClassFieldMap(Object obj){
		Map<String,String> filedMap = null;
		if(obj!=null){
			try{
				filedMap = new HashMap<String, String>();
				String stringClassName = String.class.getSimpleName();
				Class<?> objClass = obj.getClass();
				Field[] fileds = objClass.getDeclaredFields();
				for(Field f : fileds){
					String typeName = f.getType().getSimpleName();
					if(stringClassName.equals(typeName)){
						String attrName = f.getName();
						String firstLetter = attrName.substring(0,1).toUpperCase();
						String methedName = "get"+firstLetter+attrName.substring(1);
						Method getMethod = objClass.getMethod(methedName);
						String value = (String)getMethod.invoke(obj);
						filedMap.put(attrName,StringUtil.defaultString(value));
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return filedMap;
	}

	@Override
	public int saveSingleSchInfo(ScresSchInfo scresSchInfo) {
		String recordFlow = scresSchInfo.getRecordFlow();
		if(StringUtil.isBlank(recordFlow)){
			scresSchInfo.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(scresSchInfo,true);
			return scresSchInfoMapper.insertSelective(scresSchInfo);
		}else {
			GeneralMethod.setRecordInfo(scresSchInfo,false);
			return scresSchInfoMapper.updateByPrimaryKeySelective(scresSchInfo);
		}
	}

	@Override
	public List<ScresSchInfo> searchSchInfo(ScresSchInfo scresSchInfo) {
		ScresSchInfoExample example = new ScresSchInfoExample();
		ScresSchInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(scresSchInfo.getDoctorFlow())){
			criteria.andDoctorFlowEqualTo(scresSchInfo.getDoctorFlow());
		}
		if(StringUtil.isNotBlank(scresSchInfo.getGraduationYear())){
			criteria.andGraduationYearEqualTo(scresSchInfo.getGraduationYear());
		}
		example.setOrderByClause("CREATE_TIME");
		return scresSchInfoMapper.selectByExample(example);
	}

	@Override
	public int saveApply(ScresGraduationApply apply) {
		if(StringUtil.isNotBlank(apply.getRecordFlow())){
			GeneralMethod.setRecordInfo(apply,false);
			return applyMapper.updateByPrimaryKeySelective(apply);
		}else {
			apply.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(apply,true);
			return applyMapper.insertSelective(apply);
		}
	}

	@Override
	public ScresGraduationApply readApply(String recordFlow) {
		if(StringUtil.isNotBlank(recordFlow)){
			return applyMapper.selectByPrimaryKey(recordFlow);
		}
		return null;
	}

	@Override
	public List<ScresGraduationApply> searchApply(ScresGraduationApply apply,List<String> orgFlows) {
		ScresGraduationApplyExample example = new ScresGraduationApplyExample();
		ScresGraduationApplyExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(apply.getGraduationYear())){
			criteria.andGraduationYearEqualTo(apply.getGraduationYear());
		}
		if(StringUtil.isNotBlank(apply.getDoctorFlow())){
			criteria.andDoctorFlowEqualTo(apply.getDoctorFlow());
		}
		if(StringUtil.isNotBlank(apply.getOrgStatusId())){
			criteria.andOrgStatusIdEqualTo(apply.getOrgStatusId());
		}
		if(StringUtil.isNotBlank(apply.getXtOrgStatusId())){
			criteria.andXtOrgStatusIdEqualTo(apply.getXtOrgStatusId());
		}
		if(StringUtil.isNotBlank(apply.getChargeStatusId())){
			criteria.andChargeStatusIdEqualTo(apply.getChargeStatusId());
		}
		if(StringUtil.isNotBlank(apply.getDoctorStatusId())){
			criteria.andDoctorStatusIdEqualTo(apply.getDoctorStatusId());
		}
		if(StringUtil.isNotBlank(apply.getOrgFlow())){
			criteria.andOrgFlowEqualTo(apply.getOrgFlow());
		}
		if(StringUtil.isNotBlank(apply.getTrainingSpeId())){
			criteria.andTrainingSpeIdEqualTo(apply.getTrainingSpeId());
		}
		if(StringUtil.isNotBlank(apply.getDoctorName())){
			criteria.andDoctorNameLike("%"+apply.getDoctorName()+"%");
		}
		if(orgFlows!=null&&orgFlows.size()>0){
			criteria.andOrgFlowIn(orgFlows);
		}
		return applyMapper.selectByExample(example);
	}

	@Override
	public int editApply(ScresGraduationApply apply) {
		if(StringUtil.isNotBlank(apply.getRecordFlow())){
			GeneralMethod.setRecordInfo(apply,false);
			return applyMapper.updateByPrimaryKeySelective(apply);
		}else {
			apply.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(apply,true);
			return applyMapper.insertSelective(apply);
		}
	}

	@Override
	public List<ScresGraduationApply> searchAppliesMain(Map<String, Object> paramMap) {
		return graduationExtMapper.searchAppliesMain(paramMap);
	}

	@Override
	public List<Map<String, Object>> graduationStatistics(Map<String, Object> paramMap) {
		return graduationExtMapper.graduationStatistics(paramMap);
	}

	@Override
	public List<Map<String, Object>> ticketList(Map<String, Object> paramMap) {
		return graduationExtMapper.ticketList(paramMap);
	}

	@Override
	public ScresGraduationTicket readTicket(String recordFlow) {
		return ticketMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public List<ScresGraduationTicket> searchTicket(ScresGraduationTicket scresGraduationTicket) {
		ScresGraduationTicketExample example = new ScresGraduationTicketExample();
		ScresGraduationTicketExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(scresGraduationTicket.getApplyFlow())){
			criteria.andApplyFlowEqualTo(scresGraduationTicket.getApplyFlow());
		}
		if(StringUtil.isNotBlank(scresGraduationTicket.getTicketNumber())){
			criteria.andTicketNumberEqualTo(scresGraduationTicket.getTicketNumber());
		}
		return ticketMapper.selectByExample(example);
	}

	@Override
	public int editTicket(ScresGraduationTicket ticket) {
		if(StringUtil.isNotBlank(ticket.getRecordFlow())){
			GeneralMethod.setRecordInfo(ticket,false);
			return ticketMapper.updateByPrimaryKeySelective(ticket);
		}else {
			ticket.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(ticket,true);
			return ticketMapper.insertSelective(ticket);
		}
	}

	@Override
	public List<ScresGraduationTicket> searchTicketNoRepeat(ScresGraduationTicket ticket) {
		ScresGraduationTicketExample example = new ScresGraduationTicketExample();
		ScresGraduationTicketExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(ticket.getRecordFlow())){
			criteria.andRecordFlowNotEqualTo(ticket.getRecordFlow()).andTicketNumberEqualTo(ticket.getTicketNumber());
		}else {
			criteria.andTicketNumberEqualTo(ticket.getTicketNumber());
		}
		return ticketMapper.selectByExample(example);
	}

	@Override
	public int openPrint(String flag) {
		SysCfg sysCfg = new SysCfg();
		sysCfg.setCfgCode("scres_graduation_printSwith");
		sysCfg.setCfgValue(flag);
		SysCfg oldSysCfg = sysCfgMapper.selectByPrimaryKey("scres_graduation_printSwith");
		if(oldSysCfg!=null){
			GeneralMethod.setRecordInfo(sysCfg,false);
			return sysCfgMapper.updateByPrimaryKeySelective(sysCfg);
		}else{
			GeneralMethod.setRecordInfo(sysCfg,true);
			return sysCfgMapper.insert(sysCfg);
		}
	}

	@Override
	public int changSpeAllow(String datas) {
		SysCfg sysCfg = new SysCfg();
		sysCfg.setCfgCode("scres_spe_allow");
		sysCfg.setCfgValue(datas);
		SysCfg oldSysCfg = sysCfgMapper.selectByPrimaryKey("scres_spe_allow");
		if(oldSysCfg!=null){
			GeneralMethod.setRecordInfo(sysCfg,false);
			return sysCfgMapper.updateByPrimaryKeySelective(sysCfg);
		}else{
			GeneralMethod.setRecordInfo(sysCfg,true);
			return sysCfgMapper.insert(sysCfg);
		}
	}

	@Override
	public int changAllowReduction(String data) {
		SysCfg sysCfg = new SysCfg();
		sysCfg.setCfgCode("scres_allow_reduction");
		sysCfg.setCfgValue(data);
		SysCfg oldSysCfg = sysCfgMapper.selectByPrimaryKey("scres_allow_reduction");
		if(oldSysCfg!=null){
			GeneralMethod.setRecordInfo(sysCfg,false);
			return sysCfgMapper.updateByPrimaryKeySelective(sysCfg);
		}else{
			GeneralMethod.setRecordInfo(sysCfg,true);
			return sysCfgMapper.insert(sysCfg);
		}
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
	@Override
	public int importTickets(MultipartFile file) {
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			return parseExcel(wb);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	private int parseExcel(Workbook wb){
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
			for(int i = 1;i <= row_num; i++){
				Row r =  sheet.getRow(i);
				ScresGraduationTicket ticket = new ScresGraduationTicket();
				String ticketNumber;
				String idNo;
				String theoryExamDate;
				String theoryExamTime1;
				String theoryExamTime1Barch;
				String theoryExamTime2;
				String theoryExamTime2Barch;
				String skillExamDate;
				String skillExamTime1;
				String skillExamTime1Barch;
				String skillExamTime2;
				String skillExamTime2Barch;
				String orgName;
				String orgAddress;
				String theoryOrgName;
				String theoryOrgAddress;
				for (int j = 0; j < colnames.size(); j++) {
					String value = "";
					Cell cell = r.getCell(j);
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if (cell.getCellType() == 1) {
							value = cell.getStringCellValue().trim();
						} else {
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					/* 身份证号码	准考证编号	技能考试日期	技考时间段一	技考一批次	技考时间段二
					技考二批次	理论考试日期	理考时间段一	理考一批次	理考时间段二	理考二批次	考点	考试地址 */
					String graduationYear = InitConfig.getSysCfg("res_graduation_year");
					if("身份证号码".equals(colnames.get(j))) {
						idNo = value;
						if(StringUtil.isBlank(idNo)){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，身份证号码不能为空！");
						}
						ticket.setIdNo(idNo);//
						SysUser user = userBiz.findByIdNo(idNo);
						if(user==null){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，无此身份证号的学员！");
						}else {
							String userFlow = user.getUserFlow();
							ticket.setDoctorFlow(userFlow);//
							ticket.setDoctorName(user.getUserName());//
							ScresGraduationApply searchApply = new ScresGraduationApply();
							searchApply.setChargeStatusId("1");
							searchApply.setDoctorFlow(userFlow);
							searchApply.setGraduationYear(graduationYear);
							List<ScresGraduationApply> applyList = searchApply(searchApply,null);
							if(applyList!=null&&applyList.size()>0){
								ticket.setApplyFlow(applyList.get(0).getRecordFlow());
							}else {
								throw new RuntimeException("导入失败！第"+ (count+2) +"行，此学员无结业申请审核通过记录，不能打印准考证！");
							}
						}
					}else if("准考证编号".equals(colnames.get(j))){
						ticketNumber = value;
						if(StringUtil.isBlank(ticketNumber)){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，准考证编号不能为空！");
						}
						ticket.setTicketNumber(ticketNumber);
					}else if("理论考试日期".equals(colnames.get(j))){
						theoryExamDate = value;
						ticket.setTheoryExamDate(theoryExamDate);
					}else if("技能考试时间3".equals(colnames.get(j))){
						theoryExamTime1 = value;
						ticket.setTheoryExamTime1(theoryExamTime1);
					}else if("技能考试批次3".equals(colnames.get(j))){
						theoryExamTime1Barch = value;
						ticket.setTheoryExamTime1Barch(theoryExamTime1Barch);
					}else if("理考时间段二".equals(colnames.get(j))){
						theoryExamTime2 = value;
						ticket.setTheoryExamTime2(theoryExamTime2);
					}else if("理考二批次".equals(colnames.get(j))){
						theoryExamTime2Barch = value;
						ticket.setTheoryExamTime2Barch(theoryExamTime2Barch);
					}else if("技能考试日期".equals(colnames.get(j))){
						skillExamDate = value;
						ticket.setSkillExamDate(skillExamDate);
					}else if("技能考试时间1".equals(colnames.get(j))){
						skillExamTime1 = value;
						ticket.setSkillExamTime1(skillExamTime1);
					}else if("技能考试批次1".equals(colnames.get(j))){
						skillExamTime1Barch = value;
						ticket.setSkillExamTime1Barch(skillExamTime1Barch);
					}else if("技能考试时间2".equals(colnames.get(j))){
						skillExamTime2 = value;
						ticket.setSkillExamTime2(skillExamTime2);
					}else if("技能考试批次2".equals(colnames.get(j))){
						skillExamTime2Barch = value;
						ticket.setSkillExamTime2Barch(skillExamTime2Barch);
					}else if("技能考试考点".equals(colnames.get(j))){
						orgName = value;
						ticket.setOrgName(orgName);
					}else if("技能考试地址".equals(colnames.get(j))){
						orgAddress = value;
						ticket.setOrgAddress(orgAddress);
					}else if("理论考试考点".equals(colnames.get(j))){
						theoryOrgName = value;
						ticket.setTheoryOrgName(theoryOrgName);
					}else if("理论考试地址".equals(colnames.get(j))){
						theoryOrgAddress = value;
						ticket.setTheoryOrgAddress(theoryOrgAddress);
					}
				}
				//保存
				ScresGraduationTicket searchTicket = new ScresGraduationTicket();
				searchTicket.setApplyFlow(ticket.getApplyFlow());
				List<ScresGraduationTicket> ticketList = searchTicket(searchTicket);
				if(ticketList!=null && ticketList.size()>0){
					String recordFlow = ticketList.get(0).getRecordFlow();
					ticket.setRecordFlow(recordFlow);
					//准考证查重
					List<ScresGraduationTicket> tickets = searchTicketNoRepeat(ticket);
					if(tickets!=null&&tickets.size()>0){
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，准考证编号已存在！");
					}
				}else {
					ScresGraduationTicket ticket1 = new ScresGraduationTicket();
					ticket1.setTicketNumber(ticket.getTicketNumber());
					List<ScresGraduationTicket> ticketList1 = searchTicket(ticket1);
					if(ticketList1!=null&&ticketList1.size()>0){
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，准考证编号已存在！");
					}
				}
				editTicket(ticket);
				count ++;
			}
		}
		return count;
	}

	@Override
	public List<Map<String, Object>> searchDocrtor4Exl(Map<String, Object> paramMap) {
		return graduationExtMapper.searchDocrtor4Exl(paramMap);
	}

	@Override
	public List<ScresGraduationApply> searchApplyMap(Map<String, Object> paramMap) {
		return graduationExtMapper.searchApplyMap(paramMap);
	}
}
