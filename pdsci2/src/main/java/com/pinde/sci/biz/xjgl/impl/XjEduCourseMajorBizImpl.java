package com.pinde.sci.biz.xjgl.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.xjgl.IXjEduCourseMajorBiz;
import com.pinde.sci.biz.xjgl.IXjEduStudentCourseBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.dao.base.EduCourseMajorMapper;
import com.pinde.sci.dao.base.EduCourseMapper;
import com.pinde.sci.dao.base.EduMajorCreditMapper;
import com.pinde.sci.dao.xjgl.XjEduCourseMajorExtMapper;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.xjgl.XjglCourseTypeEnum;
import com.pinde.sci.form.xjgl.XjStudentCourseNumForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.EduCourseMajorExample.Criteria;
import com.pinde.sci.model.xjgl.XjEduCourseMajorExt;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class XjEduCourseMajorBizImpl implements IXjEduCourseMajorBiz {

	@Autowired
	private XjEduCourseMajorExtMapper courseMajorExtMapper;
	@Autowired
	private EduCourseMajorMapper courseMajorMapper;
	@Autowired
	private IXjEduStudentCourseBiz studentCourseBiz;
	@Autowired
	private XjEduCourseBizImpl eduCourseBiz;
	@Autowired
	private EduCourseMapper eduCourseMapper;
	@Autowired
	private EduMajorCreditMapper eduMajorCreditMapper;



//	@Override
//	public List<MajorCourseForm> searchIncludeCourseMajor(EduCourseMajor courseMajor,EduCourse course) {
//		Map<String,Object> paramMap=new HashMap<String, Object>();
//		if(courseMajor!=null){
//			paramMap.put("courseMajor", courseMajor);
//		}
//		if(course!=null){
//			paramMap.put("course", course);
//		}
//		return courseMajorExtMapper.searchIncludeCourseMajor(paramMap);
//	}

	@Override
	public List<EduCourseMajor> searchCourseMajorByCourseFlowList(EduCourseMajor courseMajor, List<String> courseFlowList) {
		EduCourseMajorExample example = new EduCourseMajorExample();
		com.pinde.sci.model.mo.EduCourseMajorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(courseMajor.getMajorId())) {
			criteria.andMajorIdEqualTo(courseMajor.getMajorId());
		}
		if (StringUtil.isNotBlank(courseMajor.getPlanYear())) {
			criteria.andPlanYearEqualTo(courseMajor.getPlanYear());
		}
		if (StringUtil.isNotBlank(courseMajor.getCourseFlow())) {
			criteria.andCourseFlowEqualTo(courseMajor.getCourseFlow());
		}
		if (courseFlowList != null && !courseFlowList.isEmpty()) {
			criteria.andCourseFlowIn(courseFlowList);
		}
		return courseMajorMapper.selectByExample(example);
	}


	@Override
	public int save(EduCourseMajor courseMajor) {
		if(StringUtil.isNotBlank(courseMajor.getRecordFlow())){
			GeneralMethod.setRecordInfo(courseMajor, false);
			return courseMajorMapper.updateByPrimaryKeySelective(courseMajor);
		}else{
			courseMajor.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(courseMajor, true);
			return courseMajorMapper.insert(courseMajor);
		}
	}

	@Override
	public List<EduCourse> searchCourseNotIncludeMajor(String majorId,String assumeOrgFlow,
			String year,String planYear) {
		Map<String,Object> paramMap=new HashMap<String, Object>();
		if(StringUtil.isNotBlank(majorId)){
			paramMap.put("majorId", majorId);
		}
		if(StringUtil.isNotBlank(year)){
			paramMap.put("year", year);
		}
		if(StringUtil.isNotBlank(planYear)){
			paramMap.put("planYear", planYear);
		}
		if(StringUtil.isNotBlank(assumeOrgFlow)){
			paramMap.put("assumeOrgFlow", assumeOrgFlow);
		}
		return courseMajorExtMapper.selectCourseNotIncludeMajor(paramMap);
	}

	@Override
	public List<XjEduCourseMajorExt> searchCourseByMajor(String majorId,
														 String year) {
		Map<String,Object> paramMap=new HashMap<String, Object>();
		if(StringUtil.isNotBlank(majorId)){
			paramMap.put("majorId", majorId);
		}
		if(StringUtil.isNotBlank(year)){
			paramMap.put("year", year);
		}
		return courseMajorExtMapper.selectCourseByMajor(paramMap);
	}

	@Override
	public List<EduCourseMajor> searchCourseMajorListNoStatus(EduCourseMajor courseMajor) {
		EduCourseMajorExample example=new EduCourseMajorExample();
	    Criteria criteria=example.createCriteria();
	    if(StringUtil.isNotBlank(courseMajor.getCourseFlow())){
	    	criteria.andCourseFlowEqualTo(courseMajor.getCourseFlow());
	    }
	    if(StringUtil.isNotBlank(courseMajor.getMajorId())){
	    	criteria.andMajorIdEqualTo(courseMajor.getMajorId());
	    }
	    if(StringUtil.isNotBlank(courseMajor.getPlanYear())){
	    	criteria.andPlanYearEqualTo(courseMajor.getPlanYear());
	    }
	    if(StringUtil.isNotBlank(courseMajor.getRecommendFlag())){
	    	criteria.andRecommendFlagEqualTo(courseMajor.getRecommendFlag());
	    }
		return this.courseMajorMapper.selectByExample(example);
	}
	@Override
	public List<EduCourseMajor> searchCourseMajorList(EduCourseMajor courseMajor) {
		EduCourseMajorExample example=new EduCourseMajorExample();
	    Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
	    if(StringUtil.isNotBlank(courseMajor.getCourseFlow())){
	    	criteria.andCourseFlowEqualTo(courseMajor.getCourseFlow());
	    }
	    if(StringUtil.isNotBlank(courseMajor.getMajorId())){
	    	criteria.andMajorIdEqualTo(courseMajor.getMajorId());
	    }
	    if(StringUtil.isNotBlank(courseMajor.getPlanYear())){
	    	criteria.andPlanYearEqualTo(courseMajor.getPlanYear());
	    }
		return this.courseMajorMapper.selectByExample(example);
	}


	@Override
	public int delete(EduCourseMajor courseMajor) {
		if(courseMajor!=null && StringUtil.isNotBlank(courseMajor.getRecordFlow())){
			courseMajor.setRecommendFlag(GlobalConstant.FLAG_N);
			courseMajor.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			GeneralMethod.setRecordInfo(courseMajor, false);
			return courseMajorMapper.updateByPrimaryKey(courseMajor);
		}
		return GlobalConstant.ZERO_LINE;
	}

	
	@Override
	public List<String> searchRecommendYear(String year, String recommendFlag) {
		Map<String,String> paramMap=new HashMap<String, String>();
		if(StringUtil.isNotBlank(year)){
		    paramMap.put("year", year);
		}
		if(StringUtil.isNotBlank(recommendFlag)){
			paramMap.put("recommendFlag", recommendFlag);
		}
		return courseMajorExtMapper.searchRecommendYear(paramMap);
	}

	@Override
	public int saveRecommendData(EduCourseMajor courseMajor,String targetYear) {
		List<EduCourseMajor> courseMajorList=searchCourseMajorListNoStatus(courseMajor);//查询被引用年份的专业关系
    	if(courseMajorList!=null && !courseMajorList.isEmpty()){
    		for(EduCourseMajor eduCourseMajor:courseMajorList){
    			if(GlobalConstant.RECORD_STATUS_Y.equals(eduCourseMajor.getRecordStatus())){
    				courseMajor.setCourseFlow(eduCourseMajor.getCourseFlow());
        			courseMajor.setMajorId(eduCourseMajor.getMajorId());
        			courseMajor.setPlanYear(targetYear);
        			List<EduCourseMajor> cmList=searchCourseMajorListNoStatus(courseMajor);//查询目标年份存在的是不是存在专业课程关系
        			if(cmList==null || cmList.isEmpty()){
        				eduCourseMajor.setPlanYear(targetYear);
        				eduCourseMajor.setRecommendFlag(GlobalConstant.FLAG_Y);
            			eduCourseMajor.setRecordFlow("");
            			int result=save(eduCourseMajor);
            			if(result!=GlobalConstant.ONE_LINE){
            				return result;
            			}
        			}else if(cmList!=null && !cmList.isEmpty()){
        				if(GlobalConstant.FLAG_N.equals(cmList.get(0).getRecordStatus())){
        					cmList.get(0).setCourseTypeId(eduCourseMajor.getCourseTypeId());
        					cmList.get(0).setCourseTypeName(eduCourseMajor.getCourseTypeName());
        					cmList.get(0).setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        					cmList.get(0).setRecommendFlag(GlobalConstant.FLAG_Y);
        					int result=save(cmList.get(0));
        					if(result!=GlobalConstant.ONE_LINE){
                				return result;
                			}
        				}
        			}
    			}
    		}
    	}
		return GlobalConstant.ONE_LINE;
	}

	@Override
	public int delRecommendData(EduCourseMajor courseMajor) {
	    if(courseMajor!=null){
	    	if(StringUtil.isNotBlank(courseMajor.getPlanYear())){
	    		courseMajor.setRecommendFlag(GlobalConstant.FLAG_Y);
	    		List<EduCourseMajor> courseMajorList=searchCourseMajorListNoStatus(courseMajor);
	    		if(courseMajorList!=null && !courseMajorList.isEmpty()){
	        		for(EduCourseMajor eduCourseMajor:courseMajorList){
	        			int result=delete(eduCourseMajor);
	        			if(result!=GlobalConstant.ONE_LINE){
            				return result;
            			}
	        		}
	    		}
	    	}
	    }
		return GlobalConstant.ONE_LINE;
	}

	@Override
	public Map<String, Object> extractCourseMajorMap(String period, List<XjEduCourseMajorExt> courseMajorExtList) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, List<XjEduCourseMajorExt>> courseTypeMajorMap = new LinkedHashMap<String, List<XjEduCourseMajorExt>>();
		List<String> courseFlowList = new ArrayList<String>();//该专业该培养层次下所有课程流水号
		for (XjEduCourseMajorExt cm : courseMajorExtList) {
			courseFlowList.add(cm.getCourseFlow());
			//组织课程类别map
			String key = cm.getCourseTypeId();
			List<XjEduCourseMajorExt> tempList = courseTypeMajorMap.get(key);
			if (tempList == null) {
				tempList = new ArrayList<XjEduCourseMajorExt>();
			}
			tempList.add(cm);
			courseTypeMajorMap.put(key, tempList);
		}
		resultMap.put("courseTypeMajorMap", courseTypeMajorMap);

		//查询该届学生所有课程已选人数（已选）
		if (courseFlowList != null && !courseFlowList.isEmpty()) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("courseFlowList", courseFlowList);
			paramMap.put("period", period);
			paramMap.put("classNumber","gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))?null:period);//广医大上课人数统计包括往届，南医大则统计本届
			List<XjStudentCourseNumForm> chooseStudentCourseCountList = studentCourseBiz.searchStudentCourseChooseCount(paramMap);
			if (chooseStudentCourseCountList != null && !chooseStudentCourseCountList.isEmpty()) {
				Map<String, String> chooseCountMap = new HashMap<String, String>();
				for (XjStudentCourseNumForm form : chooseStudentCourseCountList) {
					chooseCountMap.put(form.getCourseFlow(), form.getNum());
				}
				resultMap.put("chooseCountMap", chooseCountMap);
			}
		}
		return resultMap;
	}

	@Override
	public List<XjEduCourseMajorExt> searchCourseMajorExtList(String planYear, String majorId, String trainTypeId) {
		XjEduCourseMajorExt courseMajorExt = new XjEduCourseMajorExt();
		courseMajorExt.setPlanYear(planYear);
		courseMajorExt.setMajorId(majorId);
		if (StringUtil.isNotBlank(trainTypeId)) {
			EduCourse course = new EduCourse();
			course.setGradationId(trainTypeId);
			courseMajorExt.setCourse(course);
		}
		String customer= InitConfig.getSysCfg("xjgl_customer");
		if("gzykdx".equals(customer)){
			courseMajorExt.setGyFlag(GlobalConstant.FLAG_Y);
		}
		return courseMajorExtMapper.searchCourseMajorExtList(courseMajorExt);
	}

	@Override
	public List<XjEduCourseMajorExt> searchCourseMajorExtList(String planYear, String majorId, String trainTypeId, String courseName) {
		XjEduCourseMajorExt courseMajorExt = new XjEduCourseMajorExt();
		courseMajorExt.setPlanYear(planYear);
		courseMajorExt.setMajorId(majorId);
		if (StringUtil.isNotBlank(trainTypeId)) {
			EduCourse course = new EduCourse();
			course.setGradationId(trainTypeId);
			courseMajorExt.setCourse(course);
		}
		String customer= InitConfig.getSysCfg("xjgl_customer");
		if("gzykdx".equals(customer)){
			courseMajorExt.setGyFlag(GlobalConstant.FLAG_Y);
		}
		courseMajorExt.setCourseFlow(courseName);//用来筛选课程暂用courseFlow存储
		return courseMajorExtMapper.searchCourseMajorExtList(courseMajorExt);
	}

	@Override
	public List<XjEduCourseMajorExt> searchCourseExtList(String planYear, String userFlow) {
		XjEduCourseMajorExt courseMajorExt = new XjEduCourseMajorExt();
		courseMajorExt.setPlanYear(planYear);
		EduStudentCourse stuCourse = new EduStudentCourse();
		stuCourse.setUserFlow(userFlow);
		courseMajorExt.setStudentCourse(stuCourse);
		return courseMajorExtMapper.searchCourseExtList(courseMajorExt);
	}

	@Override
	public List<XjEduCourseMajorExt> searchReplenishCourseMajorExtList(String planYear, String majorId, String trainTypeId, String userFlow) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("planYear", planYear);
		paramMap.put("majorId", majorId);
		paramMap.put("userFlow", userFlow);
		paramMap.put("gradationId", trainTypeId);
		return courseMajorExtMapper.searchReplenishCourseMajorExtList(paramMap);
	}
	@Override
	public List<EduCourseMajor> onlyCourseMajor(EduCourseMajor courseMajor) {
		EduCourseMajorExample example = new EduCourseMajorExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(courseMajor!=null){
			if(StringUtil.isNotBlank(courseMajor.getMajorId())){
				criteria.andMajorIdEqualTo(courseMajor.getMajorId());
			}
			if(StringUtil.isNotBlank(courseMajor.getCourseFlow())){
				criteria.andCourseFlowEqualTo(courseMajor.getCourseFlow());
			}
			if(StringUtil.isNotBlank(courseMajor.getPlanYear())){
				criteria.andPlanYearEqualTo(courseMajor.getPlanYear());
			}
		}
		return courseMajorMapper.selectByExample(example);

	}

	@Override
	public ExcelUtile importMajorCoursesFromExcel(MultipartFile file) {
		InputStream is = null;
		try{
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
			final String[] keys = {
					"planYear",
					"majorId",
					"majorName",
					"courseCode",
					"courseName",
					"courseTypeName"
			};
			return ExcelUtile.importDataExcel(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>(){
				@Override
				public void operExcelData(ExcelUtile eu) {
					List<HashMap> datas=eu.getExcelDatas();
					int count = 0;
					String code="0";
					for(int i=0;i<datas.size();i++){
						HashMap map = datas.get(i);
						EduCourseMajor courseMajor = new EduCourseMajor();
						courseMajor.setPlanYear((String)map.get("planYear"));
						courseMajor.setMajorId((String)map.get("majorId"));
						courseMajor.setMajorName((String)map.get("majorName"));
						courseMajor.setCourseName((String)map.get("courseName"));
						courseMajor.setCourseTypeName((String)map.get("courseTypeName"));
						courseMajor.setCourseTypeId((String)map.get("courseTypeId"));
						courseMajor.setCourseFlow((String)map.get("courseFlow"));
						courseMajor.setRecordFlow((String)map.get("recordFlow"));
						editEduCourseMajor(courseMajor);
						count++;
					}
					eu.put("code",code);
					eu.put("count",count);
				}
				@Override
				public String checkExcelData(HashMap eduCourseMajor,ExcelUtile eu) {
					int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
					if(StringUtil.isBlank((String)eduCourseMajor.get("planYear"))){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行【年份】为空，请确认后提交！！");
						return ExcelUtile.RETURN;
					}
					if(StringUtil.isBlank((String)eduCourseMajor.get("majorId"))){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行【专业代码】为空，请确认后提交！！");
						return ExcelUtile.RETURN;
					}
					if(StringUtil.isBlank((String)eduCourseMajor.get("courseCode"))){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行【课程代码】为空，请确认后提交！！");
						return ExcelUtile.RETURN;
					}
					if(StringUtil.isBlank((String)eduCourseMajor.get("courseTypeName"))){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行【课程类型】为空，请确认后提交！！");
						return ExcelUtile.RETURN;
					}
 					//执行保存
					String majorName= DictTypeEnum.Major.getDictNameById((String)eduCourseMajor.get("majorId"));
					if(StringUtil.isBlank(majorName)){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行专业代码系统中不存在，请确认后提交！！");
						return ExcelUtile.RETURN;
					}
					eduCourseMajor.put("majorName",majorName);
					EduCourse edCourse = new EduCourse();
					edCourse.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					edCourse.setCourseSession((String)eduCourseMajor.get("planYear"));
					edCourse.setCourseCode((String)eduCourseMajor.get("courseCode"));
					List<EduCourse> courseList = eduCourseBiz.searchCourseList(edCourse);//判断该学年该课程是否存在
					if (courseList != null && courseList.size()>0) {
						eduCourseMajor.put("courseFlow",courseList.get(0).getCourseFlow());
						eduCourseMajor.put("courseName",courseList.get(0).getCourseName());
					}else{
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行该学年该课程代码在系统中不存在, 请确认后提交！！");
						return ExcelUtile.RETURN;
					}
					List<XjEduCourseMajorExt> courseMajors = searchCourseByMajor((String)eduCourseMajor.get("majorId"),(String)eduCourseMajor.get("planYear"));
					for(int i=0;i<courseMajors.size();i++){
						String courseFlow = courseMajors.get(i).getCourse().getCourseFlow();
						if(courseFlow.equals(eduCourseMajor.get("courseFlow"))){//已维护过该专业课程关系，从新覆盖
							eduCourseMajor.put("recordFlow",courseMajors.get(i).getRecordFlow());
							break;
						}
					}
					boolean flag = true;
					if(XjglCourseTypeEnum.Degree.getName().equals(eduCourseMajor.get("courseTypeName"))){
						eduCourseMajor.put("courseTypeId","Degree");
						flag = false;
					}else if(XjglCourseTypeEnum.Optional.getName().equals(eduCourseMajor.get("courseTypeName"))){
						eduCourseMajor.put("courseTypeId","Optional");
						flag = false;
					}else if(XjglCourseTypeEnum.OptionalNeed.getName().equals(eduCourseMajor.get("courseTypeName"))){
						eduCourseMajor.put("courseTypeId","OptionalNeed");
						flag = false;
					}else if(XjglCourseTypeEnum.Public.getName().equals((String)eduCourseMajor.get("courseTypeName"))){
						eduCourseMajor.put("courseTypeId","Public");
						flag = false;
					}else if(XjglCourseTypeEnum.PublicNeed.getName().equals((String)eduCourseMajor.get("courseTypeName"))){
						eduCourseMajor.put("courseTypeId","PublicNeed");
						flag = false;
					}else if("必选课".equals((String)eduCourseMajor.get("courseTypeName"))){
						eduCourseMajor.put("courseTypeId","OptionalNeed");
						eduCourseMajor.put("courseTypeName","专业必选课");
						flag = false;
					}else if("选修课".equals((String)eduCourseMajor.get("courseTypeName"))){
						eduCourseMajor.put("courseTypeId","Public");
						eduCourseMajor.put("courseTypeName","公共选修课");
						flag = false;
					}
					if(flag){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行课程类型系统中不存在，请确认后提交！！");
						return ExcelUtile.RETURN;
					}
					return null;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
	public int editEduCourseMajor(EduCourseMajor courseMajor){
		if(courseMajor!=null){
			if(!StringUtil.isNotBlank(courseMajor.getRecordFlow())){
				courseMajor.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(courseMajor,true);
				return courseMajorMapper.insertSelective(courseMajor);
			}else {
				GeneralMethod.setRecordInfo(courseMajor,false);
				return courseMajorMapper.updateByPrimaryKeySelective(courseMajor);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<EduCourseMajor> searchCourseList(EduCourseMajor courseMajor){
		EduCourseMajorExample example = new EduCourseMajorExample();
		EduCourseMajorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(courseMajor!=null){
			if (StringUtil.isNotBlank(courseMajor.getCourseTypeId())) {
				criteria.andCourseTypeIdEqualTo(courseMajor.getCourseTypeId());
			}
			if (StringUtil.isNotBlank(courseMajor.getPlanYear())) {
				criteria.andPlanYearEqualTo(courseMajor.getPlanYear());
			}
			if (StringUtil.isNotBlank(courseMajor.getMajorId())) {
				criteria.andMajorIdEqualTo(courseMajor.getMajorId());
			}
			if (StringUtil.isNotBlank(courseMajor.getCourseFlow())) {
				criteria.andCourseFlowEqualTo(courseMajor.getCourseFlow());
			}
		}
		return courseMajorMapper.selectByExample(example);
	}

	@Override
	public List<EduCourseMajor> searchCoursesBycourseFlow(String courseFlow) {
		EduCourseMajorExample example = new EduCourseMajorExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(courseFlow)){
			criteria.andCourseFlowEqualTo(courseFlow);
		}
		return courseMajorMapper.selectByExample(example);
	}

	@Override
	public List<EduCourse> searchCourseAllMajor(String majorId, String year) {
//		EduCourseExample example=new EduCourseExample();
//		EduCourseExample.Criteria criteria=example.createCriteria();
//		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		if(StringUtil.isNotBlank(majorId)){
//			criteria.andCourseMajorIdEqualTo(majorId);
//		}
//		if(StringUtil.isNotBlank(year)){
//			criteria.andCourseSessionEqualTo(year);
//		}
//		return eduCourseMapper.selectByExample(example);
		Map<String,Object> paramMap=new HashMap<String, Object>();
		if(StringUtil.isNotBlank(majorId)){
			paramMap.put("majorId", majorId);
		}
		if(StringUtil.isNotBlank(year)){
			paramMap.put("year", year);
		}
		return courseMajorExtMapper.selectCourseAllMajor(paramMap);
	}

	@Override
	public List<EduMajorCredit> searchMajorCreditList(EduMajorCredit eduMajorCredit) {
		EduMajorCreditExample example=new EduMajorCreditExample();
		EduMajorCreditExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(eduMajorCredit.getRecordFlow())){
			criteria.andRecordFlowEqualTo(eduMajorCredit.getRecordFlow());
		}
		if(StringUtil.isNotBlank(eduMajorCredit.getMajorId())){
			criteria.andMajorIdEqualTo(eduMajorCredit.getMajorId());
		}
		if(StringUtil.isNotBlank(eduMajorCredit.getMajorName())){
			criteria.andMajorNameLike("%"+eduMajorCredit.getMajorName()+"%");
		}
		if(StringUtil.isNotBlank(eduMajorCredit.getTrainTypeId())){
			criteria.andTrainTypeIdEqualTo(eduMajorCredit.getTrainTypeId());
		}
		return this.eduMajorCreditMapper.selectByExample(example);
	}

	@Override
	public int deleteMajorCredit(EduMajorCredit eduMajorCredit) {
		if(eduMajorCredit!=null && StringUtil.isNotBlank(eduMajorCredit.getRecordFlow())){
			eduMajorCredit.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			GeneralMethod.setRecordInfo(eduMajorCredit, false);
			return eduMajorCreditMapper.updateByPrimaryKey(eduMajorCredit);
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int saveMajorCredit(EduMajorCredit eduMajorCredit) {
		if(eduMajorCredit!=null){
			if(!StringUtil.isNotBlank(eduMajorCredit.getRecordFlow())){
				eduMajorCredit.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(eduMajorCredit,true);
				return eduMajorCreditMapper.insertSelective(eduMajorCredit);
			}else {
				GeneralMethod.setRecordInfo(eduMajorCredit,false);
				return eduMajorCreditMapper.updateByPrimaryKeySelective(eduMajorCredit);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}
}
