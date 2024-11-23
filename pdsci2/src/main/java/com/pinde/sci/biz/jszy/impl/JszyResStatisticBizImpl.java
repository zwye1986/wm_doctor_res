package com.pinde.sci.biz.jszy.impl;


import com.pinde.core.entyties.SysDict;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyResDoctorBiz;
import com.pinde.sci.biz.jszy.IJszyResOrgSpeBiz;
import com.pinde.sci.biz.jszy.IJszyResStatisticBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.dao.base.ResBaseMapper;
import com.pinde.sci.dao.base.ResOrgSpeMapper;
import com.pinde.sci.dao.base.ResTeacherTrainingMapper;
import com.pinde.sci.dao.base.SysOrgMapper;
import com.pinde.sci.dao.jszy.JszyResBaseExtMapper;
import com.pinde.sci.dao.jszy.JszyResDoctorRecruitExtMapper;
import com.pinde.sci.dao.res.ResDoctorExtMapper;
import com.pinde.sci.model.jszy.JszyDoctorInfoExt;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JszyResStatisticBizImpl implements IJszyResStatisticBiz {

	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ResBaseMapper resBaseMapper;
	@Autowired
	private ResOrgSpeMapper resOrgSpeMapper;
	@Autowired
	private SysOrgMapper sysOrgMapper;
	@Autowired
	private JszyResBaseExtMapper resBaseExtMapper;
	@Autowired
	private IJszyResOrgSpeBiz resOrgSpeBiz;
	@Autowired
	private IJszyResDoctorBiz jsResDoctorBiz;
	@Autowired
	private JszyResDoctorRecruitExtMapper recruitExtMapper;
	@Autowired
	private ResDoctorExtMapper doctorExtMapper;
	@Autowired
	private ResTeacherTrainingMapper teacherTrainingMapper;
	@Override
	public int statisticCountyOrgCount(SysOrg org) {
		SysOrgExample example=new SysOrgExample();
		SysOrgExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(org.getOrgProvId())){
	    	criteria.andOrgProvIdEqualTo(org.getOrgProvId());
	    }
		if(StringUtil.isNotBlank(org.getOrgLevelId())){
			criteria.andOrgLevelIdEqualTo(org.getOrgLevelId());
		}
		if(StringUtil.isNotBlank(org.getOrgCityId())){
			criteria.andOrgCityIdEqualTo(org.getOrgCityId());
		}
		if(StringUtil.isNotBlank(org.getOrgTypeId())){
			criteria.andOrgTypeIdEqualTo(org.getOrgTypeId());
		}
		return sysOrgMapper.countByExample(example);
	}
	@Override
	public int statisticDoctorCount(ResDoctorRecruit recruit, List<String> cityIdList) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("cityIdList", cityIdList);
		return recruitExtMapper.doctorCount(paramMap);
	}
	@Override
	public int  statisticJointOrgCount(SysOrg org) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		if(org!=null){
			paramMap.put("org", org);
		}
		return recruitExtMapper.joingorgCount(paramMap);
	}
	@Override
	public int statisticYearCondocCount(ResDoctor doctor, List<SysOrg> sysOrgs) {
		List<String> orgFlows=new ArrayList<String>();
		if(sysOrgs!=null&&!sysOrgs.isEmpty()){
			for(SysOrg s:sysOrgs){
				orgFlows.add(s.getOrgFlow());
			}
		}
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("doctor", doctor);
		paramMap.put("orgFlowList", orgFlows);
	return doctorExtMapper.statisticYearCondocCount(paramMap);
	}
	@Override
	public List<Map<String,Object>>  statisticDocCouByType(ResDoctorRecruit recruit, List<String> orgFlowList,ResDoctor doctor,List<String>docTypeList) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("doctor", doctor);
		paramMap.put("orgFlowList", orgFlowList);
		paramMap.put("docTypeList", docTypeList);
		return recruitExtMapper.statisticDocCouByType(paramMap);
	}
	@Override
	public List<Map<String, Object>> statisticJointCount(ResDoctorRecruit recruit, List<String> orgFlowList,ResDoctor doctor, List<String> docTypeList) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("doctor", doctor);
		paramMap.put("recruit", recruit);
		paramMap.put("orgFlowList", orgFlowList);
		paramMap.put("docTypeList", docTypeList);
		return recruitExtMapper.statisticJointCount(paramMap);
	}
	@Override
	public void export(List<SysOrg> sysOrgList,List<SysDict> typeSpeList,String trainTypeId,Map<Object, Object> totalCountMap,Map<Object, Object> zongjiMap,Map<Object, Object> orgSpeFlagMap,Map<Object, Object> joingCountMap,HttpServletResponse response) throws IOException {
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
			    Map<Integer,Integer> colWidthAuto = new HashMap<Integer, Integer>();
			    HSSFRow rowDep = sheet.createRow(0);

			    HSSFCell cel = rowDep.createCell(0);
			    cel.setCellValue("名称");
			    cel.setCellStyle(styleCenter);
			    HSSFCell celTwo = rowDep.createCell(1);
			    celTwo.setCellValue("小计");
			    celTwo.setCellStyle(styleCenter);
			    int colnum = 2;//列
			    int jielie=0;
			    if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			    	for(SysOrg so:sysOrgList){
			    		Integer width = colWidthAuto.get(colnum);
			    		if(width==null){
			    			width = 0;
			    		}
			    		jielie=colnum;
			    		HSSFCell orgCell = rowDep.createCell(colnum);
			    		orgCell.setCellValue(so.getOrgName());
			    		orgCell.setCellStyle(styleCenter);

			    		Integer dateNewWidth = so.getOrgName().getBytes().length*1*256;
			    		width = width<dateNewWidth?dateNewWidth:width;
			    		sheet.setColumnWidth(colnum,width);
			    		colWidthAuto.put(colnum,width);
			    		colnum++;
			    		//算总计
			    		int count=0;
			    		if(typeSpeList!=null&&!typeSpeList.isEmpty()){
		    		    	for(SysDict sd : typeSpeList){
				    		String key = so.getOrgFlow()+trainTypeId+sd.getDictId();//确定唯一key
				    		if(totalCountMap.get(key)!=null&&GlobalConstant.FLAG_Y.equals(orgSpeFlagMap.get(key))){
				    			count+=Integer.parseInt(totalCountMap.get(key)+"");
				    			}
		    		    	}
			    		}
			    		zongjiMap.put(so.getOrgFlow(), count);
			    	}
			    }
			    int rownum = 1;
			    int total=0;
			    if(typeSpeList!=null&&!typeSpeList.isEmpty()){
			    	for(SysDict sd : typeSpeList){
			    		HSSFRow rowDepts= sheet.createRow(rownum);
			    		HSSFCell cell = rowDepts.createCell(0);
			    		cell.setCellValue(sd.getDictName());
			    		cell.setCellStyle(styleCenter);
			    		int lie = 2;
			    		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			    			int xiaojiCount=0;
		    		    	for(SysOrg so:sysOrgList){
		    		    		sheet.setColumnWidth(0, 4500);
		    		    		String key = so.getOrgFlow()+trainTypeId+sd.getDictId();//确定唯一key
		    		    		HSSFCell orgCell = rowDepts.createCell(lie);
		    		    		if(GlobalConstant.FLAG_Y.equals(orgSpeFlagMap.get(key))){
		    		    			if(totalCountMap.get(key)!= null){
		    		    				xiaojiCount+=Integer.parseInt(totalCountMap.get(key)+"");//算小计
		    		    				if(joingCountMap.get(key)!= null){
		    		    					orgCell.setCellValue(totalCountMap.get(key)+"("+joingCountMap.get(key)+")");
		    		    				}else{
		    		    					orgCell.setCellValue(totalCountMap.get(key)+"");
		    		    				}
		    		    			}else{
		    		    				orgCell.setCellValue(0);
		    		    			}
		    		    		}else{
		    		    			orgCell.setCellValue("--");
		    		    		}
		    		    		orgCell.setCellStyle(styleCenter);
		    		    		lie++;
		    		    		}
		    		    	//放小计
				    		HSSFCell orgCell = rowDepts.createCell(1);
				    		orgCell.setCellValue(xiaojiCount);
				    		orgCell.setCellStyle(styleCenter);
				    		total+=xiaojiCount;
				    	}
			    		rownum++;
			    	}
			    	HSSFRow rowDepts= sheet.createRow(rownum);
			    	HSSFCell cell = rowDepts.createCell(0);
			    	cell.setCellValue("总计");
		    		cell.setCellStyle(styleCenter);
		    		HSSFCell orgCell = rowDepts.createCell(1);
		    		orgCell.setCellValue(total);
		    		orgCell.setCellStyle(styleCenter);
		    		int row=2;
		    		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
		    			for(SysOrg so:sysOrgList){
		    				HSSFCell Cell = rowDepts.createCell(row);
		    				Cell.setCellValue(zongjiMap.get(so.getOrgFlow())+"");
		    				Cell.setCellStyle(styleCenter);
		    				row++;
		    			}

		    		}
			    }
			    String fileName = "医师信息统计表.xls";
			    fileName = URLEncoder.encode(fileName, "UTF-8");
			    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			    response.setContentType("application/octet-stream;charset=UTF-8");
			    wb.write(response.getOutputStream());
	}
	@Override
	public List<Map<String, Object>> statisticDocCountByOrg(ResDoctorRecruit recruit,List<String> orgFlowList) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("orgFlowList", orgFlowList);
		return recruitExtMapper.statisticDocCountByOrg(paramMap);
	}
	@Override
	public List<Map<String, Object>> statisticAppCountByOrg(ResDoctorRecruit recruit,ResRec resRec,String endTime,String startTime,List<String> delTypeList) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("resRec", resRec);
		paramMap.put("endTime", endTime);
		paramMap.put("startTime", startTime);
		paramMap.put("delTypeList", delTypeList);
		return recruitExtMapper.statisticAppCountByOrg(paramMap);
	}
	@Override
	public List<Map<String, Object>> statisticRealAppCount(List<String> delTypeList,ResDoctorRecruit recruit,String endTime,String startTime, List<String> orgFlowList, String jointFlag,String month) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("endTime", endTime);
		paramMap.put("delTypeList", delTypeList);
		paramMap.put("startTime", startTime);
		paramMap.put("orgFlowList", orgFlowList);
		paramMap.put("jointFlag", jointFlag);
		paramMap.put("recruit", recruit);
		paramMap.put("month", month);
		return recruitExtMapper.statisticRealAppCount(paramMap);
	}
	@Override
	public List<Map<String, Object>> statisticDocCountByOrgForTime(String jointFlag, List<String> orgFlowList, String startTime,String endTime,ResDoctorRecruit recruit) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("jointFlag", jointFlag);
		paramMap.put("orgFlowList", orgFlowList);
		paramMap.put("endTime", endTime);
		paramMap.put("startTime", startTime);
		paramMap.put("recruit", recruit);
		return recruitExtMapper.statisticDocCountByOrgForTime(paramMap);
	}
	@Override
	public List<JszyDoctorInfoExt> statisticNoAppUser(ResDoctorRecruit recruit, ResRec resRec, List<String> delTypeList, String startDate, String endDate) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("resRec", resRec);
		paramMap.put("delTypeList", delTypeList);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		return recruitExtMapper.statisticNoAppUser(paramMap);
	}
	@Override
	public ExcelUtile importTeacherExcel(MultipartFile file) {
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int)file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			final	String[] row = {
					"certificateNo",
					"orgName",
					"doctorName",
					"sexName",
					"technicalTitle",
					};
			return ExcelUtile.importDataExcel(ResTeacherTraining.class, 1, wb, row, new IExcelUtil<ResTeacherTraining>() {
				@Override
				public void operExcelData(ExcelUtile eu) {
					List<ResTeacherTraining> datas=eu.getExcelDatas();
					int succCount=0;
					int failCount=0;
					if(null!=datas&&datas.size()>0){
						for(ResTeacherTraining teacherTraining:datas)
						{
							if(StringUtil.isBlank(teacherTraining.getTechnicalTitle())&&
											StringUtil.isBlank(teacherTraining.getDoctorName())&&
											StringUtil.isBlank(teacherTraining.getCertificateNo())&&
											StringUtil.isBlank(teacherTraining.getOrgName())&&
											StringUtil.isBlank(teacherTraining.getSexName())
									){
								failCount++;
							}else{
								int result = save(teacherTraining);
								if(GlobalConstant.ZERO_LINE!=result){
									succCount++;
								}else{
									failCount++;
								}
							}
						}
					}
					eu.put("succCount",succCount);
					eu.put("loseCount",failCount);
				}
				@Override
				public String checkExcelData(ResTeacherTraining data, ExcelUtile eu) {
					return  null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
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
	public static String _doubleTrans(double d){
        if((double)Math.round(d) - d == 0.0D)
            return String.valueOf((long)d);
        else
            return String.valueOf(d);
    }
	@Override
	public int save(ResTeacherTraining teacherTraining) {
		if(StringUtil.isNotBlank(teacherTraining.getRecordFlow())){
			GeneralMethod.setRecordInfo(teacherTraining, false);
			return teacherTrainingMapper.updateByPrimaryKeySelective(teacherTraining);
		}else{
			teacherTraining.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(teacherTraining, true);
			return teacherTrainingMapper.insertSelective(teacherTraining);
		}
	}
	@Override
	public List<ResTeacherTraining> searchTeacherInfo(ResTeacherTraining resTeacherTraining) {
		ResTeacherTrainingExample example=new ResTeacherTrainingExample();
		ResTeacherTrainingExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(resTeacherTraining.getDoctorName())){
			criteria.andDoctorNameLike("%"+resTeacherTraining.getDoctorName()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getCertificateNo())){
			criteria.andCertificateNoLike("%"+resTeacherTraining.getCertificateNo()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getOrgName())){
			criteria.andOrgNameLike("%"+resTeacherTraining.getOrgName()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getSexName())){
			criteria.andSexNameEqualTo(resTeacherTraining.getSexName());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getTechnicalTitle())){
			criteria.andTechnicalTitleLike("%"+resTeacherTraining.getTechnicalTitle()+"%");
		}
		return teacherTrainingMapper.selectByExample(example);
	}
	@Override
	public ResTeacherTraining searchTeacherInfoByPK(String recordflow) {
		ResTeacherTrainingExample example=new ResTeacherTrainingExample();
		ResTeacherTrainingExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRecordFlowEqualTo(recordflow);
		List<ResTeacherTraining> list= teacherTrainingMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return  list.get(0);
		}
		return null;
	}

	@Override
	public int editTeacherInfo(ResTeacherTraining teacherTraining) {
		return teacherTrainingMapper.updateByPrimaryKeySelective(teacherTraining);
	}
	@Override
	public Map statisticDoctorCountMap(Map paramMap) {
		return recruitExtMapper.doctorCounMap(paramMap);
	}
	@Override
	public List<Map<String, Object>> getCurrDocDetails(Map<String, Object> paramMap) {
		return recruitExtMapper.getCurrDocDetails(paramMap);
	}
	@Override
	public List<Map<String, Object>> statisticJointCountByOrg(ResDoctorRecruit recruit, List<String> orgFlowList, String trainTypeId, List<String> docTypeList) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("orgFlowList", orgFlowList);
		paramMap.put("docTypeList", docTypeList);
		return recruitExtMapper.statisticJointCountByOrg(paramMap);
	}
}
