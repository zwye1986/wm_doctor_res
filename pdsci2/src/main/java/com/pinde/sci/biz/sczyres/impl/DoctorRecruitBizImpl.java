package com.pinde.sci.biz.sczyres.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.res.IResRegBiz;
import com.pinde.sci.biz.sczyres.DoctorRecruitBiz;
import com.pinde.sci.biz.sczyres.DoctorSingupBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.res.ResDoctorRecruitExtMapper;
import com.pinde.sci.dao.sczyres.SczyGraduationExtMapper;
import com.pinde.sci.enums.res.RecruitTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorRecruitExt;
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
public class DoctorRecruitBizImpl implements DoctorRecruitBiz{

	@Autowired
	private ResDoctorRecruitMapper doctorRecruitMapper;
	@Autowired
	private ResOrgSpeAssignMapper speAssignMapper;
	@Autowired
	private ResDoctorRecruitExtMapper doctorRecruitExtMapper;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private DoctorSingupBiz doctorSingupBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorRecruitBiz doctorRecruitBiz;
	@Autowired
	private SczyGraduationExtMapper sczyGraduationExtMapper;
	@Autowired
	private ScresRecuritMoreSpeMapper moreSpeMapper;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ResDoctorMapper doctorMapper;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private IResRegBiz resResBiz;

	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
	}

	@Override
	public List<ResDoctorRecruit> findDoctorRecruit(ResDoctorRecruit recruit , String orderByColum , String order) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(recruit.getDoctorFlow())) {
			criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
		}
		if (StringUtil.isNotBlank(recruit.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(recruit.getOrgFlow());
		}
		if (StringUtil.isNotBlank(recruit.getSpeId())) {
			criteria.andSpeIdEqualTo(recruit.getSpeId());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitYear())) {
			criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitFlag())) {
			criteria.andRecruitFlagEqualTo(recruit.getRecruitFlag());
		}
		if (StringUtil.isNotBlank(recruit.getConfirmFlag())) {
			criteria.andConfirmFlagEqualTo(recruit.getConfirmFlag());
		}
		StringBuffer sb = new StringBuffer();
		if(StringUtil.isNotBlank(orderByColum)){
			sb.append(orderByColum);
		}
		if(StringUtil.isNotBlank(order)){
			sb.append(" "+order);
		}
		if(StringUtil.isNotBlank(sb.toString())){
			example.setOrderByClause(sb.toString());	
		}
		return doctorRecruitMapper.selectByExample(example);
	}

	@Override
	public List<ResOrgSpeAssign> findSpeAssign(String orgFlow,
			String assignYear) {
		ResOrgSpeAssignExample example = new ResOrgSpeAssignExample();
		ResOrgSpeAssignExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andAssignYearEqualTo(assignYear);
		if(StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		return this.speAssignMapper.selectByExample(example);
	}

	@Override
	public List<ResOrgSpeAssign> findResOrgSpeAssign(ResOrgSpeAssign orgSpeAssign) {
		ResOrgSpeAssignExample example = new ResOrgSpeAssignExample();
		ResOrgSpeAssignExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(orgSpeAssign.getOrgFlow())){
			criteria.andOrgFlowEqualTo(orgSpeAssign.getOrgFlow());
		}
		if(StringUtil.isNotBlank(orgSpeAssign.getAssignYear())){
			criteria.andAssignYearEqualTo(orgSpeAssign.getAssignYear());
		}
		if(StringUtil.isNotBlank(orgSpeAssign.getSpeId())){
			criteria.andSpeIdEqualTo(orgSpeAssign.getSpeId());
		}
		return this.speAssignMapper.selectByExample(example);
	}

//	@Override
//	public List<ResOrgSpeAssign> findSpes(ResOrgSpeAssign spe) {
//		ResOrgSpeAssignExample example = new ResOrgSpeAssignExample();
//		com.pinde.sci.model.mo.ResOrgSpeAssignExample.Criteria criteria = example.createCriteria();
//		if(StringUtil.isNotBlank(spe.getOrgFlow())){
//			criteria.andOrgFlowEqualTo(spe.getOrgFlow());
//		}
//		if(StringUtil.isNotBlank(spe.getAssignYear())){
//			criteria.andAssignYearEqualTo(spe.getAssignYear());
//		}
//		if(StringUtil.isNotBlank(spe.getSpeId())){
//			criteria.andSpeIdEqualTo(spe.getSpeId());
//		}
//		if(StringUtil.isNotBlank(spe.getRecordStatus())){
//			criteria.andRecordStatusEqualTo(spe.getRecordStatus());
//		}
//		return speAssignMapper.selectByExample(example);
//	}
	
	@Override
	public List<ResDoctorRecruitExt> selectDoctorRecruitExt(Map<String, Object> paramMap) {
		return doctorRecruitExtMapper.selectDoctorRecruitExt(paramMap);
	}
	
	@Override
	public ResDoctorRecruit readResDoctorRecruit(String recruitFlow){
		return this.doctorRecruitMapper.selectByPrimaryKey(recruitFlow);
	}
	
	@Override
	public	int editDoctorRecruit(ResDoctorRecruitWithBLOBs recruit){
		if(recruit!=null){
			if(StringUtil.isNotBlank(recruit.getRecruitFlow())){
				GeneralMethod.setRecordInfo(recruit, false);
				return doctorRecruitMapper.updateByPrimaryKeySelective(recruit);
			}else{
				recruit.setRecruitFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(recruit, true);
				return doctorRecruitMapper.insertSelective(recruit);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int giveUpDoctorRecruit(ResDoctorRecruitWithBLOBs recruit) {
		ResDoctorRecruitWithBLOBs recruit2 = (ResDoctorRecruitWithBLOBs)this.readResDoctorRecruit(recruit.getRecruitFlow());
		if("Y".equals(recruit2.getReturnBackFlag())){//ReturnBackFlag Y意义是表示是计数的学员
			//释放指标
			String regYear = InitConfig.getSysCfg("res_reg_year");
			String catSpeId = recruit2.getCatSpeId();
			String orgFlow = recruit2.getOrgFlow();
			ResOrgSpeAssign searchAssign = new ResOrgSpeAssign();
			searchAssign.setAssignYear(regYear);
			searchAssign.setSpeId(catSpeId);
			searchAssign.setOrgFlow(orgFlow);
			List<ResOrgSpeAssign> resOrgSpeAssigns = findResOrgSpeAssign(searchAssign);
			if(resOrgSpeAssigns!=null&&resOrgSpeAssigns.size()>0){
				ResOrgSpeAssign resOrgSpeAssign = resOrgSpeAssigns.get(0);
				BigDecimal assignReal = resOrgSpeAssign.getAssignReal();
				int intValue = assignReal.intValue()-1;
				if(intValue<=0){
					intValue=0;
				}
				resOrgSpeAssign.setAssignReal(new BigDecimal(intValue));
				speAssignMapper.updateByPrimaryKeySelective(resOrgSpeAssign);
			}
		}
		recruit.setReturnBackFlag("");
		editDoctorRecruit(recruit);
		return 0;
	}

	@Override
	public int recruit(ResDoctorRecruitWithBLOBs recruit,String planFlag,String orgLevel) {
		if("Y".equals(planFlag)){
			recruit.setReturnBackFlag("Y");//实际意义是表示是计数的学员
		}
		ResDoctorRecruitWithBLOBs recruit2 = (ResDoctorRecruitWithBLOBs)this.readResDoctorRecruit(recruit.getRecruitFlow());
		String admitNotice = recruit2.getAdmitNotice();
		if( (orgLevel.equals("Joint")&&GlobalConstant.FLAG_Y.equals(recruit.getAdmitFlag()))	 ||
			(StringUtil.isBlank(recruit2.getAdmitFlag())&&orgLevel.equals("Main")&&GlobalConstant.FLAG_Y.equals(recruit.getRecruitFlag())) ||
			("Y".equals(recruit2.getAdmitFlag()) && orgLevel.equals("Main") && StringUtil.isBlank(recruit2.getReturnBackFlag()) &&
					GlobalConstant.FLAG_Y.equals(recruit.getRecruitFlag()))
				){
			if("Y".equals(planFlag)){
				//计数
				String regYear = InitConfig.getSysCfg("res_reg_year");
				String catSpeId;
				String orgFlow;
				if(StringUtil.isBlank(recruit.getOrgFlow())){
					orgFlow = recruit2.getOrgFlow();
				}else{
					orgFlow = recruit.getOrgFlow();
				}
				if(StringUtil.isBlank(recruit.getCatSpeId())){
					catSpeId = recruit2.getCatSpeId();
				}else {
					catSpeId = recruit.getCatSpeId();
				}
				ResOrgSpeAssign searchAssign = new ResOrgSpeAssign();
				searchAssign.setAssignYear(regYear);
				searchAssign.setSpeId(catSpeId);
				searchAssign.setOrgFlow(orgFlow);
				List<ResOrgSpeAssign> resOrgSpeAssigns = findResOrgSpeAssign(searchAssign);
				if(resOrgSpeAssigns!=null&&resOrgSpeAssigns.size()>0){
					ResOrgSpeAssign resOrgSpeAssign = resOrgSpeAssigns.get(0);
					BigDecimal assignPlan = resOrgSpeAssign.getAssignPlan();
					BigDecimal assignReal = resOrgSpeAssign.getAssignReal();
					if(assignReal!=null){
						int result = assignPlan.compareTo(assignReal);
						if(result<=0){
							return 0;
						}
						int intValue = assignReal.intValue()+1;
						resOrgSpeAssign.setAssignReal(new BigDecimal(intValue));
					}else{
						resOrgSpeAssign.setAssignReal(new BigDecimal(1));
					}
					speAssignMapper.updateByPrimaryKeySelective(resOrgSpeAssign);
				}
			}
		    //将录取通知放到msg中
			if(StringUtil.isNotBlank(admitNotice)){
				this.msgBiz.addSysMsg(recruit.getDoctorFlow(), "录取通知", admitNotice);
			}
			//计算学员结业考核年份
			String doctorFlow = recruit2.getDoctorFlow();
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			String trainYear = recruit.getTrainYear();
			String sessionNumber = doctor.getSessionNumber();
			String graduationYear = String.valueOf(Integer.parseInt(trainYear)+Integer.parseInt(sessionNumber));
			recruit.setSessionNumber(sessionNumber);
			recruit.setGraduationYear(graduationYear);
		}else if("Y".equals(recruit2.getAdmitFlag()) && orgLevel.equals("Main") && "Y".equals(recruit2.getReturnBackFlag()) &&
				GlobalConstant.FLAG_N.equals(recruit.getRecruitFlag())){
			//释放指标
			String regYear = InitConfig.getSysCfg("res_reg_year");
			String catSpeId = recruit2.getCatSpeId();
			String orgFlow = recruit2.getOrgFlow();
			ResOrgSpeAssign searchAssign = new ResOrgSpeAssign();
			searchAssign.setAssignYear(regYear);
			searchAssign.setSpeId(catSpeId);
			searchAssign.setOrgFlow(orgFlow);
			List<ResOrgSpeAssign> resOrgSpeAssigns = findResOrgSpeAssign(searchAssign);
			if(resOrgSpeAssigns!=null&&resOrgSpeAssigns.size()>0){
				ResOrgSpeAssign resOrgSpeAssign = resOrgSpeAssigns.get(0);
				BigDecimal assignReal = resOrgSpeAssign.getAssignReal();
				int intValue = assignReal.intValue()-1;
				if(intValue<=0){
					intValue=0;
				}
				resOrgSpeAssign.setAssignReal(new BigDecimal(intValue));
				speAssignMapper.updateByPrimaryKeySelective(resOrgSpeAssign);
			}
			this.msgBiz.addSysMsg(recruit.getDoctorFlow(), "抱歉,经考核您未被我基地录取", "");
		}else if(GlobalConstant.FLAG_N.equals(recruit.getRecruitFlag())) {
			System.err.println("Y".equals(recruit2.getAdmitFlag()));
			System.err.println(orgLevel.equals("Main"));
			System.err.println("Y".equals(recruit2.getReturnBackFlag()));
			System.err.println(GlobalConstant.FLAG_N.equals(recruit.getRecruitFlag()));
			this.msgBiz.addSysMsg(recruit.getDoctorFlow(), "抱歉,经考核您未被我基地录取", "");
		}
		editDoctorRecruit(recruit);
		return 1;
	}

	@Override
	public int findCountByRecruit(ResDoctorRecruit recruit) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		ResDoctorRecruitExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(recruit.getOrgFlow())){
			criteria.andOrgFlowEqualTo(recruit.getOrgFlow());
		}
		if(StringUtil.isNotBlank(recruit.getCatSpeId())){
            criteria.andCatSpeIdEqualTo(recruit.getCatSpeId());
		}
		if(StringUtil.isNotBlank(recruit.getSpeId())){
			criteria.andSpeIdEqualTo(recruit.getSpeId());
		}
		if(StringUtil.isNotBlank(recruit.getRecruitFlag())){
			criteria.andRecruitFlagEqualTo(recruit.getRecruitFlag());
		}
		if(StringUtil.isNotBlank(recruit.getRecruitYear())){
			criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
		}
		if(StringUtil.isNotBlank(recruit.getConfirmFlag())){
			criteria.andConfirmFlagEqualTo(recruit.getConfirmFlag());
		}
		if(StringUtil.isNotBlank(recruit.getReturnBackFlag())){
			criteria.andReturnBackFlagEqualTo(recruit.getReturnBackFlag());
		}
		return this.doctorRecruitMapper.countByExample(example);
	}

	@Override
	public Map<String, ResDoctorRecruit> findSwapDoctorRecruit(
			List<String> noRecruitDoctors) {
		Map<String , ResDoctorRecruit> resultMap = new HashMap<String, ResDoctorRecruit>();
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y)
		.andRecruitTypeIdEqualTo(RecruitTypeEnum.Swap.getId())
		.andDoctorFlowIn(noRecruitDoctors);
		example.setOrderByClause("CREATE_TIME");
		List<ResDoctorRecruit> swaprecruits = this.doctorRecruitMapper.selectByExample(example);
		for(ResDoctorRecruit swapRecruit:swaprecruits){
			resultMap.put(swapRecruit.getDoctorFlow(), swapRecruit);
		}
		return resultMap;
	}
	
	@Override
	public Map<String, ResDoctorRecruit> findNoRecruitDoctorRecruit(
			List<String> swapRecruitDoctors) {
		Map<String , ResDoctorRecruit> resultMap = new HashMap<String, ResDoctorRecruit>();
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y)
        .andRecruitFlagEqualTo(GlobalConstant.FLAG_N)
		.andDoctorFlowIn(swapRecruitDoctors);
		example.setOrderByClause("CREATE_TIME");
		List<ResDoctorRecruit> swaprecruits = this.doctorRecruitMapper.selectByExample(example);
		for(ResDoctorRecruit swapRecruit:swaprecruits){
			resultMap.put(swapRecruit.getDoctorFlow(), swapRecruit);
		}
		return resultMap;
	}

	@Override
	public void swapRecruit(ResDoctorRecruitWithBLOBs recruit,String oldRecruitFlow) {
		//计算学员结业考核年份
		String doctorFlow = recruit.getDoctorFlow();
		ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
		ResDoctorRecruit oldRecruit = readResDoctorRecruit(oldRecruitFlow);
		recruit.setTrainYear(oldRecruit.getTrainYear());
		recruit.setSessionNumber(oldRecruit.getSessionNumber());
		recruit.setGraduationYear(oldRecruit.getGraduationYear());
		recruit.setRecruitTypeId(RecruitTypeEnum.Swap.getId());
		recruit.setRecruitTypeName(RecruitTypeEnum.Swap.getName());
		doctorSingupBiz.saveRecruit(recruit,null);
		if(StringUtil.isNotBlank(recruit.getAdmitNotice())){
			this.msgBiz.addSysMsg(recruit.getDoctorFlow(), "调剂通知", recruit.getAdmitNotice());
		}
		//将学员状态更改为待审核
		doctor.setOrgFlow(recruit.getOrgFlow());
		doctor.setOrgName(recruit.getOrgName());
		doctor.setTrainingYears(recruit.getTrainYear());
		doctor.setGraduationYear(recruit.getGraduationYear());
		this.doctorMapper.updateByPrimaryKeySelective(doctor);

		SysUser user = new SysUser();
		user.setUserFlow(doctorFlow);
		user.setOrgFlow(recruit.getOrgFlow());
		user.setOrgName(recruit.getOrgName());
		userMapper.updateByPrimaryKeySelective(user);

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
	public int importGrades(MultipartFile file) {
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
				ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
				String idNo;
				String examResult;
				String auditionResult;
				String operResult;
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
					/* 身份证号码	笔试成绩	面试成绩	技能成绩 */
					String regYear = InitConfig.getSysCfg("res_reg_year");
					if("身份证号码".equals(colnames.get(j))) {
						idNo = value;
						if(StringUtil.isBlank(idNo)){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，身份证号码不能为空！");
						}
						SysUser user = userBiz.findByIdNo(idNo);
						if(user==null){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，无此身份证号的学员！");
						}else {
							String userFlow = user.getUserFlow();
							ResDoctorRecruit searchRecruit = new ResDoctorRecruit();
							searchRecruit.setDoctorFlow(userFlow);
							searchRecruit.setRecruitYear(regYear);
							List<ResDoctorRecruit> resDoctorRecruits = doctorRecruitBiz.searchDoctorRecruit(searchRecruit);
							if(resDoctorRecruits!=null&&resDoctorRecruits.size()>0){
								recruit.setRecruitFlow(resDoctorRecruits.get(0).getRecruitFlow());
							}else {
								throw new RuntimeException("导入失败！第"+ (count+2) +"行，此学员无招录记录，不能导入成绩！");
							}
						}
					}else if("笔试成绩".equals(colnames.get(j))){
						examResult = value;
						recruit.setExamResult(new BigDecimal(examResult));
					}else if("面试成绩".equals(colnames.get(j))){
						auditionResult = value;
						recruit.setAuditionResult(new BigDecimal(auditionResult));
					}else if("技能成绩".equals(colnames.get(j))){
						operResult = value;
						recruit.setOperResult(new BigDecimal(operResult));
					}
				}
				//保存
				doctorRecruitBiz.updateDocrecruitByFlow(recruit);
				count ++;
			}
		}
		return count;
	}

	@Override
	public List<Map<String, Object>> searchDoctorRecruitExtList(Map<String, Object> paramMap) {
		return sczyGraduationExtMapper.searchDoctorRecruitExtList(paramMap);
	}
	@Override
	public List<Map<String, Object>> searchDoctorRecruitExtListWithClob(Map<String, Object> paramMap) {
		return sczyGraduationExtMapper.searchDoctorRecruitExtListWithClob(paramMap);
	}

	@Override
	public List<ScresRecuritMoreSpe> searchMoreSpe(ScresRecuritMoreSpe moreSpe) {
		ScresRecuritMoreSpeExample example = new ScresRecuritMoreSpeExample();
		ScresRecuritMoreSpeExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(moreSpe.getRecruitFlow())){
			criteria.andRecruitFlowEqualTo(moreSpe.getRecruitFlow());
		}
		if(StringUtil.isNotBlank(moreSpe.getOrderNum())){
			criteria.andOrderNumEqualTo(moreSpe.getOrderNum());
		}
		example.setOrderByClause("ORDER_NUM");
		return moreSpeMapper.selectByExample(example);
	}

	@Override
	public int editMoreSpe(ScresRecuritMoreSpe moreSpe) {
		if(StringUtil.isBlank(moreSpe.getRecordFlow())){
			moreSpe.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(moreSpe, true);
			return moreSpeMapper.insertSelective(moreSpe);
		}else {
			GeneralMethod.setRecordInfo(moreSpe, false);
			return moreSpeMapper.updateByPrimaryKeySelective(moreSpe);
		}
	}

	@Override
	public int changeReturnBackFlag(String recruitFlow, String flag) {
		ResDoctorRecruitWithBLOBs recruit = (ResDoctorRecruitWithBLOBs)this.readResDoctorRecruit(recruitFlow);
		recruit.setReturnBackFlag(flag);
		String regYear = InitConfig.getSysCfg("res_reg_year");
		String catSpeId = recruit.getCatSpeId();
		String orgFlow = recruit.getOrgFlow();
		if("Y".equals(flag)){
			//计数
			ResOrgSpeAssign searchAssign = new ResOrgSpeAssign();
			searchAssign.setAssignYear(regYear);
			searchAssign.setSpeId(catSpeId);
			searchAssign.setOrgFlow(orgFlow);
			List<ResOrgSpeAssign> resOrgSpeAssigns = findResOrgSpeAssign(searchAssign);
			if(resOrgSpeAssigns!=null&&resOrgSpeAssigns.size()>0){
				ResOrgSpeAssign resOrgSpeAssign = resOrgSpeAssigns.get(0);
				BigDecimal assignPlan = resOrgSpeAssign.getAssignPlan();
				BigDecimal assignReal = resOrgSpeAssign.getAssignReal();
				if(assignReal!=null){
					int result = assignPlan.compareTo(assignReal);
					if(result<=0){
						return -1;
					}
					int intValue = assignReal.intValue()+1;
					resOrgSpeAssign.setAssignReal(new BigDecimal(intValue));
				}else{
					resOrgSpeAssign.setAssignReal(new BigDecimal(1));
				}
				speAssignMapper.updateByPrimaryKeySelective(resOrgSpeAssign);
			}
		}else{
			//释放指标		;
			ResOrgSpeAssign searchAssign = new ResOrgSpeAssign();
			searchAssign.setAssignYear(regYear);
			searchAssign.setSpeId(catSpeId);
			searchAssign.setOrgFlow(orgFlow);
			List<ResOrgSpeAssign> resOrgSpeAssigns = findResOrgSpeAssign(searchAssign);
			if(resOrgSpeAssigns!=null&&resOrgSpeAssigns.size()>0){
				ResOrgSpeAssign resOrgSpeAssign = resOrgSpeAssigns.get(0);
				BigDecimal assignReal = resOrgSpeAssign.getAssignReal();
				int intValue = assignReal.intValue()-1;
				if(intValue<=0){
					intValue=0;
				}
				resOrgSpeAssign.setAssignReal(new BigDecimal(intValue));
				speAssignMapper.updateByPrimaryKeySelective(resOrgSpeAssign);
			}
		}
		return doctorRecruitMapper.updateByPrimaryKeySelective(recruit);
	}
}
