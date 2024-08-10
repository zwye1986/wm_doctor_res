package com.pinde.sci.biz.hbres.impl;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbres.ExamManageBiz;
import com.pinde.sci.biz.hbres.GradeManageBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.res.ResDoctorExtMapper;
import com.pinde.sci.enums.res.RecruitTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.hbres.ResDoctorClobForm;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.GradeBorderlineStatistics;
import com.pinde.sci.model.res.GradeStep;
import com.pinde.sci.model.res.GradeStepStatistics;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class GradeManageBizImpl implements GradeManageBiz{

	private static Logger logger = LoggerFactory.getLogger(GradeManageBizImpl.class);
	@Autowired
	private ResGradeBorderlineMapper gradeBorderlineMapper;
	@Autowired
	private ResDoctorRecruitMapper doctorRecruitMapper;
	@Autowired
	private ResDoctorExtMapper doctorExtMapper;
	@Autowired
	private ResExamDoctorMapper examDoctorMapper;
	@Autowired
	private ExamManageBiz examManageBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private ResExamMapper resExamMapper;
	@Autowired
	private ResGradeBorderlineOrgMapper gradeBorderlineOrgMapper;
	@Autowired
	private IOrgBiz orgBiz;

	public static Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
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

	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
	}

	@Override
	public ResDoctorRecruit findResDoctorRecruitByDoctorFlow(String doctorFlow) {
		return this.doctorRecruitMapper.selectByPrimaryKey(doctorFlow);
	}

	@Override
	public void inputDoctorGrade(String examFlow , String doctorFlow, String examResult) {
		ResExamDoctor record = new ResExamDoctor();
		record.setExamResult(new BigDecimal(examResult));
		ResExamDoctorExample example = new ResExamDoctorExample();
		example.createCriteria()
		.andExamFlowEqualTo(examFlow)
		.andDoctorFlowEqualTo(doctorFlow)
		.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		GeneralMethod.setRecordInfo(record, false);
		this.examDoctorMapper.updateByExampleSelective(record , example);
	}

	@Override
	public void submitRecruit(ResDoctorRecruitWithBLOBs doctorRecruit) {
		doctorRecruit.setRecruitFlow(PkUtil.getUUID());
		doctorRecruit.setRecruitDate(DateUtil.getCurrDate());
		doctorRecruit.setRetestFlag(GlobalConstant.FLAG_N);
		doctorRecruit.setAdmitFlag(GlobalConstant.FLAG_N);
		doctorRecruit.setRecruitTypeId(RecruitTypeEnum.Fill.getId());
		doctorRecruit.setRecruitTypeName(RecruitTypeEnum.Fill.getName());
		String year = InitConfig.getSysCfg("res_reg_year");
		if(StringUtil.isBlank(year)){
			throw new RuntimeException("后台系统没有设置报名年份");
		}
		doctorRecruit.setRecruitYear(year);
		GeneralMethod.setRecordInfo(doctorRecruit, true);
		this.doctorRecruitMapper.insertSelective(doctorRecruit);
	}

	@Override
	public void importExcel(String examFlow , MultipartFile file) throws InvalidFormatException, IOException {
		InputStream is = file.getInputStream();
		byte[] fileData = new byte[(int) file.getSize()];
		is.read(fileData);
		Workbook wb =  createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
	    parseExcel(examFlow,wb);
	}

	@Override
	public void importGrade(String examFlow, String grade) throws Exception {
		ResExam re = examManageBiz.findExamByFlow(examFlow);
		String year = InitConfig.getSysCfg("res_reg_year");
		if(null != re && null != re.getExamYear()){
			year = re.getExamYear();
		}
		List<ResDoctorClobForm> docLst = resDoctorBiz.searchClobInfoDoc(year);//某届注册信息完善的所有医师
		List<ResDoctorClobForm> newDocLst = new ArrayList<>();//某届的 研究生考试成绩>=过分线且不参加全省住培考试 则不要 参加笔试
		if(null != docLst && docLst.size() > 0){
			for(ResDoctorClobForm rd : docLst){
				try {
					BaseUserResumeExtInfoForm extInfo = userResumeBiz.converyToJavaBean(rd.getUserResume(), BaseUserResumeExtInfoForm.class);
					//研究生考试成绩>=过分线且不参加全省住培考试
					Double masterScore = StringUtil.isBlank(extInfo.getMasterExamResult())?0:Double.valueOf(extInfo.getMasterExamResult());//研究生考试成绩
					Double lineScore = StringUtil.isBlank(InitConfig.getSysCfg("res_master_score_on"))?0:Double.valueOf(InitConfig.getSysCfg("res_master_score_on"));//后台过分线
					if(masterScore>=lineScore && GlobalConstant.FLAG_N.equals(extInfo.getIsJoinTest())){
						newDocLst.add(rd);
					}
				} catch (Exception e) {
					continue;
				}
			}
		}
		if(newDocLst.size() > 0){
			for(ResDoctorClobForm rd : newDocLst){
				ResExamDoctor examDoctor = new ResExamDoctor();
				examDoctor.setExamResult(new BigDecimal(grade));
				ResExamDoctorExample example = new ResExamDoctorExample();//组织sql语句
				example.createCriteria()
						.andExamFlowEqualTo(examFlow)
						.andDoctorFlowEqualTo(rd.getDoctorFlow());
				int updSuccCount = examDoctorMapper.updateByExampleSelective(examDoctor,example);
				if(updSuccCount == 0){
					examDoctor.setRecordFlow(PkUtil.getUUID());
					examDoctor.setExamFlow(examFlow);
					examDoctor.setDoctorFlow(rd.getDoctorFlow());
					examDoctor.setDoctorName(rd.getDoctorName());
					examDoctor.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					GeneralMethod.setRecordInfo(examDoctor, true);
					updSuccCount = this.examDoctorMapper.insert(examDoctor);
				}
				//更新志愿表笔试成绩
				if(updSuccCount > 0){
					ResDoctorRecruitWithBLOBs record = new ResDoctorRecruitWithBLOBs();
					record.setExamResult(examDoctor.getExamResult());
					ResDoctorRecruitExample example2 = new ResDoctorRecruitExample();
					example2.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
							.andDoctorFlowEqualTo(rd.getDoctorFlow()).andRecruitYearEqualTo(year);
					doctorRecruitMapper.updateByExampleSelective(record,example2);
				}
			}
		}
	}
	private void parseExcel(String examFlow, Workbook wb)  {
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
			for(int i = 0; i <= row_num; i++){
				Row r = sheet.getRow(i);
				if(null == r.getCell(0) || null == r.getCell(1) || null == r.getCell(2) || null == r.getCell(3)){
					continue;
				}
				int cell_num = r.getLastCellNum();
				if(i == 0){
					for (int j = 0; j < cell_num; j++) {
						String value = "";
						if (r.getCell((short) j).getCellType() == 1) {
							value = r.getCell((short) j).getStringCellValue();
						} else {
							value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
						}
						colnames.add(value);
					}
                }else {
                	ResExamDoctor examDoctor = new ResExamDoctor();
                	String name="";
					String ticketNum = "";
					String idNo = "";
					Double testScore = 0.0;
					boolean flag = false;
					for (int j = 0; j < cell_num; j++) {
						String value = "";
						if (r.getCell((short) j).getCellType() == 1) {
							value = r.getCell((short) j).getStringCellValue();
						} else {
							value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
						}

						if("姓名".equals(colnames.get(j))) {
							if (value == null
									|| value.trim()
											.length() == 0) {
								flag = true;
								break;
							}
							name = value;
						}else if("准考证号".equals(colnames.get(j))){
							if (value == null
									|| value.trim()
											.length() == 0) {
								flag = true;
								break;
							}
							ticketNum = value;
						}else if("考试成绩".equals(colnames.get(j))) {
							examDoctor.setExamResult(new BigDecimal(value));
							testScore = Double.valueOf(value);
						}else if("身份证号".equals(colnames.get(j))){
							idNo = value;
						}else if("是否参加全省住培招录笔试".equals(colnames.get(j)) && GlobalConstant.FLAG_Y.equals(value)){//研究生考试成绩>=后台设置的分数线且参加全省住培招录笔试
							//Double lineScore = StringUtil.isBlank(InitConfig.getSysCfg("res_master_score_on"))?0:Double.valueOf(InitConfig.getSysCfg("res_master_score_on"));
							Double score = 0.0;
							Map<String,Object> speLineScore = doctorExtMapper.querySpeLineScore(idNo,InitConfig.getSysCfg("res_reg_year"));
							if(null != speLineScore){
								score = Double.valueOf(speLineScore.get("GRADE_BORDERLINE").toString());
							}
							if(testScore >= score){
								examDoctor.setExamResult(new BigDecimal(testScore));
							}else{
								examDoctor.setExamResult(new BigDecimal(score));
							}
						}
				    	if (flag) {
							break;
						}
					}
					GeneralMethod.setRecordInfo(examDoctor, false);
		    		ResExamDoctorExample examDoctorExample = new ResExamDoctorExample();
		    		examDoctorExample.createCriteria()
		    		.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		    		.andExamFlowEqualTo(examFlow)
		    		.andDoctorNameEqualTo(name)
		    		.andTicketNumEqualTo(ticketNum);
					int count =this.examDoctorMapper.updateByExampleSelective(examDoctor, examDoctorExample);
					//更新志愿表笔试成绩
					if(count > 0){
						List<ResExamDoctor> exitExamDoctorList = this.examDoctorMapper.selectByExample(examDoctorExample);
						List<String> doctorFlowList = new ArrayList<>();
						for(int k=0;k<exitExamDoctorList.size();k++){
							doctorFlowList.add(exitExamDoctorList.get(k).getDoctorFlow());
						}
						ResExam resExam = resExamMapper.selectByPrimaryKey(examFlow);
						ResDoctorRecruitWithBLOBs record = new ResDoctorRecruitWithBLOBs();
						record.setExamResult(examDoctor.getExamResult());
						ResDoctorRecruitExample example = new ResDoctorRecruitExample();
						example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
								.andDoctorFlowIn(doctorFlowList).andRecruitYearEqualTo(resExam.getExamYear());
						doctorRecruitMapper.updateByExampleSelective(record,example);
					}
				}
			}
		}
	}

//	@Override
//	public ResDoctorRecruit findResDoctorRecruit(String year, String doctorFlow) {
//		List<ResDoctorRecruit> recruits = findResDoctorRecruits(year , doctorFlow);
//		if(recruits.size()>0){
//			return recruits.get(0);
//		}
//		return null;
//	}

	@Override
	public List<ResGradeBorderline> findGradeBorderlineByExamFlow(
			String examFlow) {
		ResGradeBorderlineExample example = new ResGradeBorderlineExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andExamFlowEqualTo(examFlow);
		return gradeBorderlineMapper.selectByExample(example);
	}
	
	
	public ResGradeBorderline findResGradeBorderlineByExamFlowAndSpeId(String examFlow , String speId){
		ResGradeBorderlineExample example = new ResGradeBorderlineExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andExamFlowEqualTo(examFlow)
		.andSpeIdEqualTo(speId);
		List<ResGradeBorderline> borderlines = gradeBorderlineMapper.selectByExample(example);
		if(borderlines.size()==1){
			return borderlines.get(0);
		}
		return null;
	}
	
	@Override
	public Integer getMoreThanGradeDoctorCountInExamForSpe(String examFlow,
			String speId, String moreThanGrade) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("examFlow", examFlow);
		paramMap.put("speId", speId);
		paramMap.put("moreThanGrade", moreThanGrade);
		return this.doctorExtMapper.searchJoinExamDoctorCountByParamMap(paramMap);
	}

	@Override
	public Map<String, GradeBorderlineStatistics> getGradeBorderlineStatistics(
			String examFlow) {
		Map<String , GradeBorderlineStatistics> resultMap = null;
		List<ResGradeBorderline> gradeBorderlines = findGradeBorderlineByExamFlow(examFlow);
		List<SysDict> spes = DictTypeEnum.sysListDictMap.get(DictTypeEnum.GraduateMajor.getId());
		if(spes!=null && !spes.isEmpty()){
			resultMap = new HashMap<String, GradeBorderlineStatistics>();
			for(SysDict dict : spes){
				String speId = dict.getDictId();
				GradeBorderlineStatistics gradeBorderlineStatistics = new GradeBorderlineStatistics();
				//设置总人数
			    Integer doctorCountInExamForSpe = this.examManageBiz.getDoctorCountInExamForSpe(examFlow, speId);
			    gradeBorderlineStatistics.setSum(doctorCountInExamForSpe);
				for(ResGradeBorderline gradeBorderline : gradeBorderlines){
					if(speId.equals(gradeBorderline.getSpeId())){
					    //设置分数线信息
						gradeBorderlineStatistics.setGradeBorderline(gradeBorderline);
						String gradeline = gradeBorderline.getGradeBorderline();
						if(StringUtil.isNotBlank(gradeline)){
							//设置过线人数
							Integer passDoctorCount = getMoreThanGradeDoctorCountInExamForSpe(examFlow , speId , gradeline);
							gradeBorderlineStatistics.setPassCount(passDoctorCount);
						}
						
					}
				}
				resultMap.put(speId, gradeBorderlineStatistics);
			}
		}
		return resultMap;
	}

	@Override
	public void addGradeBorderLine(ResGradeBorderline borderline) {
		if(StringUtil.isBlank(borderline.getBorderlineFlow())){
			borderline.setBorderlineFlow(PkUtil.getUUID());
			if(StringUtil.isBlank(borderline.getGradeStep())){
				borderline.setGradeStep(GradeManageBiz.defaultGradeStep.toString());//分数间隔默认为5
			}
			GeneralMethod.setRecordInfo(borderline, true);
			this.gradeBorderlineMapper.insertSelective(borderline);
		}else{
			this.gradeBorderlineMapper.updateByPrimaryKeySelective(borderline);

			//基地分数线
			ResGradeBorderline resGradeBorderline = gradeBorderlineMapper.selectByPrimaryKey(borderline.getBorderlineFlow());
			if("Y".equals(borderline.getPublishFlag())){
				SysOrgExample example = new SysOrgExample();//所有基地
				SysOrgExample.Criteria criteria =  example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
						.andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
				List<SysOrg> orgList = orgBiz.searchOrgByExample(example);
				if(orgList!=null&&orgList.size()>0){
					for(SysOrg org:orgList){
						ResGradeBorderlineOrg saveBorderlineOrg = new ResGradeBorderlineOrg();
						saveBorderlineOrg.setOrgFlow(org.getOrgFlow());
						saveBorderlineOrg.setOrgName(org.getOrgName());
						saveBorderlineOrg.setBorderlineFlow(resGradeBorderline.getBorderlineFlow());
						saveBorderlineOrg.setSpeId(resGradeBorderline.getSpeId());
						saveBorderlineOrg.setSpeName(resGradeBorderline.getSpeName());
						saveBorderlineOrg.setGradeBorderlineOrg(resGradeBorderline.getGradeBorderline());
						editBorderlineOrg(saveBorderlineOrg);
					}
				}
			}
//			//基地分数线
//			ResGradeBorderlineOrg searchBorderlineOrg = new ResGradeBorderlineOrg();
//			searchBorderlineOrg.setBorderlineFlow(borderline.getBorderlineFlow());
//			List<ResGradeBorderlineOrg> gradeBorderlineOrgList = searchBorderlineOrg(searchBorderlineOrg);
//			if(gradeBorderlineOrgList!=null && gradeBorderlineOrgList.size()>0){
//				for(ResGradeBorderlineOrg gradeBorderlineOrg:gradeBorderlineOrgList){
//					gradeBorderlineOrg.setGradeBorderlineOrg(borderline.getGradeBorderline());
//					editBorderlineOrg(gradeBorderlineOrg);
//				}
//			}
		}
	}

	@Override
	public List<GradeStep> crateGradeSteps(Integer max, Integer min,
			Integer step) {
		
		if((max-min)<0){
			throw new RuntimeException("异常");
		}
		List<GradeStep> gradeSteps = new ArrayList<GradeStep>();
		if(step==null || step==0){
			step = GradeManageBiz.defaultGradeStep;//默认步长
		}
		int segment = (max-min)/step;
		if(((max-min)%step)>0){
			segment = segment + 1;
		}
		int tmp = min;
		for(int i = 0 ; i<segment;i++){
			int start = tmp;
			int end = tmp+step;
			GradeStep gradeStep = new GradeStep();
			gradeStep.setStartGrade(start);
			gradeStep.setEndGrade(end);
			gradeSteps.add(gradeStep);
			tmp = end+1;
			if(end>=max){
				end = max;
				gradeStep.setEndGrade(end);
				break;
			}
			
		}
		return gradeSteps;
	}
	
	@Override
	public Map<String , GradeStepStatistics> getGradeSteps(String examFlow){
		Map<String , GradeStepStatistics> resultMap = new HashMap<String, GradeStepStatistics>();
		List<ResGradeBorderline> gradeBorderlines = findGradeBorderlineByExamFlow(examFlow);
		List<SysDict> spes = DictTypeEnum.sysListDictMap.get(DictTypeEnum.GraduateMajor.getId());
		if(spes!=null && !spes.isEmpty()){
			for(SysDict dict : spes){
				String speId = dict.getDictId();
				GradeStepStatistics gradeStepStatistics = new GradeStepStatistics();
				Integer step = null;
				for(ResGradeBorderline gradeborderline : gradeBorderlines){
					if(speId.equals(gradeborderline.getSpeId())){
						step = Integer.parseInt(gradeborderline.getGradeStep());
						gradeStepStatistics.setGradeBorderline(gradeborderline);
					}
				}
				List<GradeStep> gradeSteps = getGradeStep(examFlow , speId , step);
				if(gradeSteps!=null){
					gradeStepStatistics.setGradeSteps(gradeSteps);
					resultMap.put(speId, gradeStepStatistics);
				}
				
			}
		}
		return resultMap;
	}

	@Override
	public List<GradeStep> getGradeStep(String examFlow, String speId,
			Integer step) {
		Map<String, Object> gradeParamMap = new HashMap<String, Object>();
		gradeParamMap.put("examFlow", examFlow);
		gradeParamMap.put("speId", speId);
		gradeParamMap.put("max", GlobalConstant.FLAG_Y);
		Integer maxGrade = this.doctorExtMapper.searchMaxOrMinGradeInExam(gradeParamMap);
		if(maxGrade==0){
			return null;
		}
		gradeParamMap.put("max", GlobalConstant.FLAG_N);
		gradeParamMap.put("min", GlobalConstant.FLAG_Y);
		Integer minGrade = this.doctorExtMapper.searchMaxOrMinGradeInExam(gradeParamMap);
		List<GradeStep> gradeSteps = crateGradeSteps(maxGrade, minGrade, step);
		for(GradeStep gradeStep : gradeSteps){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("examFlow", examFlow);
			paramMap.put("speId", speId);
			paramMap.put("startGrade", gradeStep.getStartGrade());
			paramMap.put("endGrade", gradeStep.getEndGrade());
			Integer docCount = this.doctorExtMapper.searchJoinExamDoctorCountByParamMap(paramMap);
			gradeStep.setCount(docCount);
		}
		return gradeSteps;
	}

	@Override
	public BigDecimal findGradeBorderlineByExamFlowAndSpe(String examFlow,
			String speId) {
		ResGradeBorderlineExample example = new ResGradeBorderlineExample();
		example.createCriteria()
		.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andPublishFlagEqualTo(GlobalConstant.FLAG_Y)
		.andExamFlowEqualTo(examFlow)
		.andSpeIdEqualTo(speId);
		List<ResGradeBorderline> gradeLines = this.gradeBorderlineMapper.selectByExample(example);
		if(gradeLines.size()==1){
			ResGradeBorderline gradeBorderline = gradeLines.get(0);
			if(StringUtil.isNotBlank(gradeBorderline.getGradeBorderline())){
				return new BigDecimal(gradeBorderline.getGradeBorderline());
			}
		}
		return null;
	}
	

	public void modGradeBorderlineStep(ResGradeBorderline borderline){
		if(borderline!=null && StringUtil.isNotBlank(borderline.getGradeStep())){
			ResGradeBorderline record = new ResGradeBorderline();
			record.setGradeStep(borderline.getGradeStep());
			ResGradeBorderlineExample example = new ResGradeBorderlineExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
			.andExamFlowEqualTo(borderline.getExamFlow())
			.andSpeIdEqualTo(borderline.getSpeId());
			this.gradeBorderlineMapper.updateByExampleSelective(record, example);
		}
		
	}

	@Override
	public int editBorderlineOrg(ResGradeBorderlineOrg gradeBorderlineOrg) {
		if(StringUtil.isNotBlank(gradeBorderlineOrg.getRecordFlow())){
			GeneralMethod.setRecordInfo(gradeBorderlineOrg,false);
			return gradeBorderlineOrgMapper.updateByPrimaryKeySelective(gradeBorderlineOrg);
		}else{
			gradeBorderlineOrg.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(gradeBorderlineOrg,true);
			return gradeBorderlineOrgMapper.insertSelective(gradeBorderlineOrg);
		}
	}

	@Override
	public List<ResGradeBorderlineOrg> searchBorderlineOrg(ResGradeBorderlineOrg gradeBorderlineOrg) {
		ResGradeBorderlineOrgExample example = new ResGradeBorderlineOrgExample();
		ResGradeBorderlineOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(gradeBorderlineOrg.getBorderlineFlow())){
			criteria.andBorderlineFlowEqualTo(gradeBorderlineOrg.getBorderlineFlow());
		}
		if(StringUtil.isNotBlank(gradeBorderlineOrg.getOrgFlow())){
			criteria.andOrgFlowEqualTo(gradeBorderlineOrg.getOrgFlow());
		}
		if(StringUtil.isNotBlank(gradeBorderlineOrg.getSpeId())){
			criteria.andSpeIdEqualTo(gradeBorderlineOrg.getSpeId());
		}
		return gradeBorderlineOrgMapper.selectByExample(example);
	}
}
