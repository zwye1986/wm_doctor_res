package com.pinde.sci.biz.res.impl;


import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.res.IResScoreBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ResDoctorRecruitMapper;
import com.pinde.sci.dao.base.ResScoreMapper;
import com.pinde.sci.dao.base.SchArrangeResultMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.dao.jsres.JsResDoctorExtMapper;
import com.pinde.sci.dao.res.ResDoctorExtMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.ResScoreExample.Criteria;
import com.pinde.sci.model.res.GradeDetail4ShiYan;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor=Exception.class)
public class ResScoreBizImpl implements IResScoreBiz{
	@Autowired
	private ResScoreMapper scoreMapper;
	@Autowired
	private ResDoctorRecruitMapper recruitMapper;
	@Autowired
	private JsResDoctorExtMapper jsResDoctorExtMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private ResDoctorExtMapper doctorExtMapper;
	@Autowired
	private SchArrangeResultMapper schArrangeResultMapper;
	@Autowired
	private IJsResDoctorRecruitBiz recruitBiz;

	@Override
	public int save(ResScore resScore) {
		if(resScore!=null){
			if(StringUtil.isNotBlank(resScore.getScoreFlow())){
				GeneralMethod.setRecordInfo(resScore, false);
				return scoreMapper.updateByPrimaryKeySelective(resScore);
			}else{
				resScore.setScoreFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(resScore,true);
				return scoreMapper.insert(resScore);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public ResScore searchByScoreFlow(String scoreFlow) {
		return scoreMapper.selectByPrimaryKey(scoreFlow);
	}

	@Override
	public List<ResScore> searchByScoreList(String doctorFlow) {
		ResScoreExample example=new ResScoreExample();
		Criteria criteria= example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(doctorFlow)) {
			criteria.andDoctorFlowEqualTo(doctorFlow);
		}
		return scoreMapper.selectByExample(example);
	}
	@Override
	public List<ResScore> selectByExampleWithBLOBs(String doctorFlow) {
		ResScoreExample example=new ResScoreExample();
		Criteria criteria= example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(doctorFlow)) {
			criteria.andDoctorFlowEqualTo(doctorFlow);
		}
		return scoreMapper.selectByExampleWithBLOBs(example);
	}
	@Override
	public List<ResScore> selectByParam(String doctorFlow) {
		//查技能理论
		List<String> types = new ArrayList<>();
		types.add("TheoryScore");
		types.add("SkillScore");
		ResScoreExample example=new ResScoreExample();
		Criteria criteria= example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andScoreTypeIdIn(types);
		if (StringUtil.isNotBlank(doctorFlow)) {
			criteria.andDoctorFlowEqualTo(doctorFlow);
		}
		return scoreMapper.selectByExampleWithBLOBs(example);
	}
	@Override
	public List<ResScore> selectByExampleWithBLOBs2(String doctorFlow,String flag) {
		ResScoreExample example=new ResScoreExample();
		Criteria criteria= example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(doctorFlow)) {
			criteria.andDoctorFlowEqualTo(doctorFlow);
		}
		if(StringUtil.isNotBlank(flag) && "Y".equals(flag)){
			criteria.andIsAffirmIdEqualTo("Y");
		}
		return scoreMapper.selectByExampleWithBLOBs(example);
	}

//	@Override
//	public boolean haveScore(String processFlow){
//		ResScoreExample example=new ResScoreExample();
//		Criteria criteria= example.createCriteria();
//		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
//		.andProcessFlowEqualTo(processFlow);
//		int count = scoreMapper.countByExample(example);
//		return count>0;
//	}
	
	@Override
	public ResScore getScoreByProcess(String processFlow){
		if(!StringUtil.isNotBlank(processFlow)){
			return null;
		}
		
		ResScoreExample example=new ResScoreExample();
		Criteria criteria= example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andProcessFlowEqualTo(processFlow);
		
		List<ResScore> scores = scoreMapper.selectByExample(example);
		
		if(scores==null || scores.isEmpty()){
			return null;
		}
		
		return scores.get(0);
	}

	@Override
	public ResScore getMaxScoreByProcess(String processFlow){
		if(!StringUtil.isNotBlank(processFlow)){
			return null;
		}

		ResScoreExample example=new ResScoreExample();
		Criteria criteria= example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andProcessFlowEqualTo(processFlow);

		List<ResScore> scores = scoreMapper.selectByExample(example);

		if(scores==null || scores.isEmpty()){
			return null;
		}

		return scores.stream().sorted((s1, s2) -> {
			if(s1.getTheoryScore() == null) return 1;
			else if(s2.getTheoryScore() == null) return -1;
			else return s2.getTheoryScore().compareTo(s1.getTheoryScore());
		}).collect(Collectors.toList()).get(0);
	}

	@Override
	public ResScore getScoreByResult(String resultFlow) {
		if(!StringUtil.isNotBlank(resultFlow)){
			return null;
		}

		ResScoreExample example=new ResScoreExample();
		Criteria criteria= example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andResultFlowEqualTo(resultFlow).andScoreTypeIdEqualTo("SkillUpload");

		List<ResScore> scores = scoreMapper.selectByExample(example);

		if(scores==null || scores.isEmpty()){
			return null;
		}

		return scores.get(0);
	}

	@Override
	public ResScore getScoreByDocFlowAndYearAndType(String doctorFlow, String year,String typeId)
	{
		if(!StringUtil.isNotBlank(doctorFlow)&&!StringUtil.isNotBlank(year)){
			return null;
		}

		ResScoreExample example=new ResScoreExample();
		Criteria criteria= example.createCriteria();

		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow)
		.andScoreTypeIdEqualTo(typeId);
		if(StringUtil.isNotBlank(year))
		{
			criteria.andScorePhaseIdEqualTo(year);
		}
		List<ResScore> scores = scoreMapper.selectByExampleWithBLOBs(example);
		if(scores==null || scores.isEmpty()){
			return null;
		}

		return scores.get(0);
	}

	@Override
	public List<Map<String,Object>> getALLScoreByMap(Map map){
		List<Map<String,Object>> list=jsResDoctorExtMapper.getALLScoreByMap(map);
		return list;
	}

	@Override
	public int importScoreFromExcel(MultipartFile file) {
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

	@Override
	public List<ResScore> selectByRecruitFlowAndScoreType(String recruitFlow, String id) {

		if(!StringUtil.isNotBlank(recruitFlow)&&!StringUtil.isNotBlank(id)){
			return null;
		}

		ResScoreExample example=new ResScoreExample();
		Criteria criteria= example.createCriteria();

		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRecruitFlowEqualTo(recruitFlow)
				.andScoreTypeIdEqualTo(id);
		List<ResScore> scores = scoreMapper.selectByExampleWithBLOBs(example);
		return scores;
	}

	@Override
	public String findLastYearByScoreTypeId(Map<String, String> paramMap) {
		String year = "";
		if(StringUtil.isNotBlank(paramMap.get("scoreTypeId"))){
			ResScoreExample example=new ResScoreExample();
			Criteria criteria= example.createCriteria();
			criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andScoreTypeIdEqualTo(paramMap.get("scoreTypeId"));
			example.setOrderByClause("score_phase_id desc");
			List<ResScore> scores = scoreMapper.selectByExample(example);
			if(scores != null && scores.size() > 0){
				year = scores.get(0).getScorePhaseId();
			}
		}
		return year;
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
			for(int i = 1;i <=row_num; i++){
				Row r =  sheet.getRow(i);
				if(r==null){
					throw new RuntimeException("导入失败！，表中不能有空行！");
				}
				SysUser sysUser = new SysUser();
				ResScore score = new ResScore();
				String userFlow;
				String userCode;
				String octScore;
				String marScore;
				String junScore;
				for (int j = 0; j < colnames.size(); j++) {
					String value = "";
					Cell cell = r.getCell(j);
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if (cell.getCellType().getCode() == 1) {
							value = cell.getStringCellValue().trim();
						} else {
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}

					/* 学号	3月份成绩	6月份成绩  10月份成绩 	*/
					if("学号".equals(colnames.get(j))) {
						userCode = value;
						SysUserExample example = new SysUserExample();
						SysUserExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
						if(StringUtil.isNotBlank(userCode)){
							criteria.andUserCodeEqualTo(userCode);
						}
						List<SysUser> sysUsers =sysUserMapper.selectByExample(example);
						if (sysUsers!=null&&sysUsers.size()==1) {
							sysUser = sysUsers.get(0);
						}
						ResScoreExample resScoreExample = new ResScoreExample();
						Criteria scorecCriteria=resScoreExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
						if(StringUtil.isNotBlank(sysUser.getUserFlow())){
							scorecCriteria.andDoctorFlowEqualTo(sysUser.getUserFlow());
						}
						scorecCriteria.andScoreTypeIdEqualTo("Assessment");
						List<ResScore> scores =scoreMapper.selectByExample(resScoreExample);
						if (scores!=null&&scores.size()>0) {
							score = scores.get(0);
						}else {
							score.setDoctorFlow(sysUser.getUserFlow());
						}
					}else if("3月份成绩".equals(colnames.get(j))){
						marScore = value;
						if(StringUtil.isNotBlank(marScore)) {
							Boolean b = marScore.matches("^((100|[1-9]\\d|\\d)(\\.[0-9]{0,1})?)$");
							if (Double.parseDouble(marScore) > 100) {
								b = false;
							}
							if (b) {
								if (StringUtil.isNotBlank(marScore)) {
									score.setMarScore(BigDecimal.valueOf(Double.parseDouble(marScore)));
								}
							} else {
								int page = count + 2;
								throw new RuntimeException("导入失败！，第" + page + "行，3月份成绩有误请参考说明！");
							}
						}
					}else if("6月份成绩".equals(colnames.get(j))){
						junScore = value;
						if(StringUtil.isNotBlank(junScore)) {
							Boolean b = junScore.matches("^((100|[1-9]\\d|\\d)(\\.[0-9]{0,1})?)$");
							if (b) {
								if (StringUtil.isNotBlank(junScore)) {
									score.setJunScore(BigDecimal.valueOf(Double.parseDouble(junScore)));
								}
							} else {
								int page = count + 2;
								throw new RuntimeException("导入失败！，第" + page + "行，6月份成绩有误请参考说明！");
							}
						}
					}else if("10月份成绩".equals(colnames.get(j))){
						octScore = value;
						if(StringUtil.isNotBlank(octScore)) {
							Boolean b = octScore.matches("^((100|[1-9]\\d|\\d)(\\.[0-9]{0,1})?)$");
							if (b) {
								if (StringUtil.isNotBlank(octScore)) {
									score.setOctScore(BigDecimal.valueOf(Double.parseDouble(octScore)));
								}
							} else {
								int page = count + 2;
								throw new RuntimeException("导入失败！，第" + page + "行，10月份成绩有误请参考说明！");
							}
						}
					}

				}
				if(StringUtil.isBlank(sysUser.getUserFlow())){
					throw new RuntimeException("导入失败！学号有误！");
				}
				//执行保存
				if(StringUtil.isNotBlank(score.getScoreFlow())){
					scoreMapper.updateByPrimaryKeySelective(score);
				}else{
					score.setScoreFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(score, true);
					score.setScoreTypeId("Assessment");
					score.setScoreTypeName("实习生理论考试成绩");
					scoreMapper.insert(score);
				}
				count ++;
			}
		}
		return count;
	}

	private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
		// 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
		if (!inS.markSupported()) {
			// 还原流信息
			inS = new PushbackInputStream(inS);
		}
//		// EXCEL2003使用的是微软的文件系统
//		if (POIFSFileSystem.hasPOIFSHeader(inS)) {
//			return new HSSFWorkbook(inS);
//		}
//		// EXCEL2007使用的是OOM文件格式
//		if (POIXMLDocument.hasOOXMLHeader(inS)) {
//			// 可以直接传流参数，但是推荐使用OPCPackage容器打开
//			return new XSSFWorkbook(OPCPackage.open(inS));
//		}
		try{
			return WorkbookFactory.create(inS);
		}catch (Exception e) {
			throw new IOException("不能解析的excel版本");
		}
	}
	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
	}

	@Override
	public int importOsceGrades(MultipartFile file) {
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			return parseExcel2(wb);
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

	@Override
	public int importTheorySkillGrades(MultipartFile file) {
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			return parseExcel3(wb);
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

	private int parseExcel2(Workbook wb){
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
			for(int i = 1;i <=row_num; i++){
				Row r =  sheet.getRow(i);
				if(r==null){
					throw new RuntimeException("导入失败！，表中不能有空行！");
				}
				SysUser current = GlobalContext.getCurrentUser();
				ResScore score = new ResScore();
				GradeDetail4ShiYan gradeDetail = new GradeDetail4ShiYan();
				String idNo;
				for (int j = 0; j < colnames.size(); j++) {
					String value = "";
					Cell cell = r.getCell(j);
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if (cell.getCellType().getCode() == 1) {
							value = cell.getStringCellValue().trim();
						} else {
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}

					/*身份证 考核年份 考核名称 分站分数 总分 考试结果*/
					if("身份证".equals(colnames.get(j))) {
						idNo = value;
						SysUser currentUser = userBiz.findByIdNo(idNo);
						if(currentUser==null||StringUtil.isBlank(currentUser.getOrgFlow())||(!current.getOrgFlow().equals(currentUser.getOrgFlow()))){
							throw new RuntimeException("导入失败！，第" + (count+2) + "行，学员身份证有误！");
						}else{
							score.setDoctorFlow(currentUser.getUserFlow());
						}
					}else if("考核年份".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							Boolean b = value.matches("^(19|20)\\d{2}$");
							if (b) {
								score.setScoreTypeId(value);
							} else {
								throw new RuntimeException("导入失败！，第" + (count+2) + "行，年份有误！");
							}
						}else{
							throw new RuntimeException("导入失败！，第" + (count+2) + "行，年份不能为空！");
						}
					}else if("考核名称".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							score.setScoreTypeName(value);
						}
					}else if("第一站".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							Boolean b = value.matches("^\\d{1,}$");
							if (b) {
								gradeDetail.setStation1(value);
							} else {
								throw new RuntimeException("导入失败！，第" + (count+2) + "行第一站成绩必须为数字！");
							}
						}
					}else if("第二站".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							Boolean b = value.matches("^\\d{1,}$");
							if (b) {
								gradeDetail.setStation2(value);
							} else {
								throw new RuntimeException("导入失败！，第" + (count+2) + "行第二站成绩必须为数字！");
							}
						}
					}else if("第三站".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							Boolean b = value.matches("^\\d{1,}$");
							if (b) {
								gradeDetail.setStation3(value);
							} else {
								throw new RuntimeException("导入失败！，第" + (count+2) + "行第三站成绩必须为数字！");
							}
						}
					}else if("第四站".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							Boolean b = value.matches("^\\d{1,}$");
							if (b) {
								gradeDetail.setStation4(value);
							} else {
								throw new RuntimeException("导入失败！，第" + (count+2) + "行第四站成绩必须为数字！");
							}
						}
					}else if("第五站".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							Boolean b = value.matches("^\\d{1,}$");
							if (b) {
								gradeDetail.setStation5(value);
							} else {
								throw new RuntimeException("导入失败！，第" + (count+2) + "行第五站成绩必须为数字！");
							}
						}
					}else if("第六站".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							Boolean b = value.matches("^\\d{1,}$");
							if (b) {
								gradeDetail.setStation6(value);
							} else {
								throw new RuntimeException("导入失败！，第" + (count+2) + "行第六站成绩必须为数字！");
							}
						}
					}else if("第七站".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							Boolean b = value.matches("^\\d{1,}$");
							if (b) {
								gradeDetail.setStation7(value);
							} else {
								throw new RuntimeException("导入失败！，第" + (count+2) + "行第七站成绩必须为数字！");
							}
						}
					}else if("第八站".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							Boolean b = value.matches("^\\d{1,}$");
							if (b) {
								gradeDetail.setStation8(value);
							} else {
								throw new RuntimeException("导入失败！，第" + (count+2) + "行成绩必须为数字！");
							}
						}
					}else if("第九站".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							Boolean b = value.matches("^\\d{1,}$");
							if (b) {
								gradeDetail.setStation9(value);
							} else {
								throw new RuntimeException("导入失败！，第" + (count+2) + "行第九站成绩必须为数字！");
							}
						}
					}else if("总分".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							Boolean b = value.matches("^\\d{1,}$");
							if (b) {
								gradeDetail.setTotal(value);
							} else {
								throw new RuntimeException("导入失败！，第" + (count+2) + "行总分必须为数字！");
							}
						}
					}else if("考试结果".equals(colnames.get(j))){
						gradeDetail.setResult(value);
					}
				}
				//执行保存
				ResScoreExample example = new ResScoreExample();
				example.createCriteria().andDoctorFlowEqualTo(score.getDoctorFlow()).andScoreTypeIdEqualTo(score.getScoreTypeId()).
						andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
				List<ResScore> old = scoreMapper.selectByExample(example);
				//转大字段
				String xmlContent = JaxbUtil.convertToXml(gradeDetail);
				score.setExtScore(xmlContent);
				if(old==null||old.size()==0){
					score.setScoreFlow(PkUtil.getUUID());
					score.setResultFlow("osce");
					GeneralMethod.setRecordInfo(score,true);
					scoreMapper.insertSelective(score);
				}else{
					score.setScoreFlow(old.get(0).getScoreFlow());
					GeneralMethod.setRecordInfo(score,false);
					scoreMapper.updateByPrimaryKeySelective(score);
				}
				count ++;
			}
		}
		return count;
	}

	private int parseExcel3(Workbook wb){
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
			for(int i = 1;i <=row_num; i++){
				Row r =  sheet.getRow(i);
				if(r==null){
					throw new RuntimeException("导入失败！，表中不能有空行！");
				}
				SysUser current = GlobalContext.getCurrentUser();
				ResScore score = new ResScore();
				String schStartDate = "";
				String schDeptName = "";
				String idNo;
				for (int j = 0; j < colnames.size(); j++) {
					String value = "";
					Cell cell = r.getCell(j);
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if (cell.getCellType().getCode() == 1) {
							value = cell.getStringCellValue().trim();
						} else {
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}

					/*身份证号 轮转科室 轮转开始时间 理论成绩 技能操作名称 技能成绩 考官1 考官2*/
					if("姓名".equals(colnames.get(j))) {
						if(StringUtil.isNotBlank(value)) {

						}else{
							throw new RuntimeException("导入失败！，第" + (count+2) + "行，姓名不能为空！");
						}
					}else if("身份证号".equals(colnames.get(j))) {
						idNo = value;
						SysUser currentUser = userBiz.findByIdNo(idNo);
						if(currentUser==null||StringUtil.isBlank(currentUser.getOrgFlow())||(!current.getOrgFlow().equals(currentUser.getOrgFlow()))){
							throw new RuntimeException("导入失败！，第" + (count+2) + "行，学员身份证有误！");
						}else{
							score.setDoctorFlow(currentUser.getUserFlow());
						}
					}else if("轮转科室".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							score.setSchDeptName(value);
							schDeptName = value;
						}else{
							throw new RuntimeException("导入失败！，第" + (count+2) + "行，轮转科室不能为空！");
						}
					}else if("轮转开始时间".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							schStartDate=value;
						}else{
							throw new RuntimeException("导入失败！，第" + (count+2) + "行，轮转开始时间不能为空！");
						}
					}else if("理论成绩".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							Boolean b = value.matches("^\\d{1,}$");
							if (b) {
								score.setTheoryScore(new BigDecimal(value));
							} else {
								throw new RuntimeException("导入失败！，第" + (count+2) + "行理论成绩成绩必须为数字！");
							}
						}else{
							throw new RuntimeException("导入失败！，第" + (count+2) + "行，理论成绩不能为空！");
						}
					}else if("技能操作名称".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							score.setScoreResultName(value);
						}else{
							throw new RuntimeException("导入失败！，第" + (count+2) + "行，技能操作名称不能为空！");
						}
					}else if("技能成绩".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							Boolean b = value.matches("^\\d{1,}$");
							if (b) {
								score.setSkillScore(new BigDecimal(value));
							} else {
								throw new RuntimeException("导入失败！，第" + (count+2) + "行技能成绩必须为数字！");
							}
						}else{
							throw new RuntimeException("导入失败！，第" + (count+2) + "行，技能成绩不能为空！");
						}
					}else if("考官1".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							score.setAuditStatusId(value);
						}
					}else if("考官2".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							score.setAuditStatusName(value);
						}
					}else if("考官3".equals(colnames.get(j))){
						if(StringUtil.isNotBlank(value)) {
							score.setScoreResultId(value);
						}
					}
				}
				//查询是否有该排班
				SchArrangeResultExample example = new SchArrangeResultExample();
				example.createCriteria().andDoctorFlowEqualTo(score.getDoctorFlow()).andSchStartDateEqualTo(schStartDate).
					andSchDeptNameEqualTo(schDeptName).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
				List<SchArrangeResult> resultList = schArrangeResultMapper.selectByExample(example);
				if(resultList==null||resultList.size()==0){
					throw new RuntimeException("导入失败！，第" + (count+2) + "行，该学员无此轮转科室记录！");
				}else{
					score.setResultFlow(resultList.get(0).getResultFlow());
					//执行保存
					ResScoreExample example2 = new ResScoreExample();
					example2.createCriteria().andDoctorFlowEqualTo(score.getDoctorFlow()).andResultFlowEqualTo(score.getResultFlow()).
							andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andScoreTypeIdEqualTo("SkillUpload");
					List<ResScore> old = scoreMapper.selectByExample(example2);
					score.setScoreTypeId("SkillUpload");
					if(old==null||old.size()==0){
						score.setScoreFlow(PkUtil.getUUID());
						GeneralMethod.setRecordInfo(score,true);
						scoreMapper.insertSelective(score);
					}else{
						score.setScoreFlow(old.get(0).getScoreFlow());
						GeneralMethod.setRecordInfo(score,false);
						scoreMapper.updateByPrimaryKeySelective(score);
					}
				}
				count ++;
			}
		}
		return count;
	}

	@Override
	public List<Map<String, Object>> searchOsceScoreList(Map<String, Object> paramMap) {
		return doctorExtMapper.searchOsceScoreList(paramMap);
	}

	@Override
	public int updateAffirm(List<String> scoreFlowList) {
		List<String> doctorFlows = doctorExtMapper.selectDoctorFlow(scoreFlowList);
		List<ResDoctorRecruit> doctorRecruits = recruitMapper.selectByDoctorFlow(doctorFlows);
		recruitBiz.createCertificateNo(doctorRecruits);
		return doctorExtMapper.updateAffirm(scoreFlowList);
	}

	@Override
	public int updateNotAffirm(List<String> scoreFlowList) {
		List<String> doctorFlows = doctorExtMapper.selectDoctorFlow(scoreFlowList);
		List<ResDoctorRecruit> doctorRecruits = recruitMapper.selectByDoctorFlow(doctorFlows);
		recruitBiz.createNotCertificateNo(doctorRecruits);
		return doctorExtMapper.updateNotAffirm(scoreFlowList);
	}
}
