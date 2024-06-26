package com.pinde.sci.biz.sch.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.CfgBizImpl;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.res.ResDoctorSchProcessExtMapper;
import com.pinde.sci.dao.sch.SchArrangeResultExtMapper;
import com.pinde.sci.dao.sys.SysDeptExtMapper;
import com.pinde.sci.dao.sys.SysUserExtMapper;
import com.pinde.sci.enums.jsres.TrainCategoryEnum;
import com.pinde.sci.enums.res.RecDocCategoryEnum;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.enums.sch.SchArrangeStatusEnum;
import com.pinde.sci.enums.sch.SchArrangeTypeEnum;
import com.pinde.sci.enums.sch.SchStageEnum;
import com.pinde.sci.enums.sch.SchUnitEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.sch.SchArrangeResultForm;
import com.pinde.sci.form.sch.SelectDept;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.SchArrangeResultExample.Criteria;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.collections.CollectionUtils;
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
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
@Transactional(rollbackFor=Exception.class)
public class SchArrangeResultBizImpl implements ISchArrangeResultBiz {

	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private JsresDoctorDeptDetailMapper doctorDeptDetailMapper;
	@Autowired
	private HbresDoctorDeptDetailMapper hbresDoctorDeptDetailMapper;
	@Autowired
	private SchArrangeResultMapper arrangeResultMapper;
	@Autowired
	private ISchArrangeBiz arrangeBiz;
	@Autowired
	private ISchArrangeDoctorBiz arrdocBiz;
	@Autowired
	private ISchArrangeDoctorDeptBiz arrDocDeptBiz;
	@Autowired
	private SchArrangeResultExtMapper resultMapper;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private ISchDoctorDeptBiz doctroDeptBiz;
	@Autowired
	private SchArrangeResultExtMapper resultExtMapper;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ISchRotationGroupBiz groupBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ResDoctorSchProcessMapper doctorSchProcessMapper;
	@Autowired
	private ResRecMapper recMapper;
	@Autowired
	private ResDoctorSchProcessExtMapper docSchProcessExtMapper;
	@Autowired
	private ResOutOfficeLockMapper outsOutOfficeLockMapper;
	@Autowired
	private CfgBizImpl cfgBizImpl;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	protected ICfgBiz cfgBiz;
	@Resource
	private SchArrangeResultMapper schArrangeResultMapper;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private SysUserExtMapper sysUserExtMapper;
	@Autowired
	private ResDoctorRecruitMapper resDoctorRecruitMapper;
	@Autowired
	private ResSchProcessExpressMapper schProcessExpressMapper;
	@Autowired
	private SchRotationMapper rotationMapper;
	@Autowired
	private SysOrgMapper orgMapper;
	@Autowired
	private SysDeptExtMapper sysDeptExtMapper;

	private static Logger logger = LoggerFactory.getLogger(SchArrangeResultBizImpl.class);

	@Override
	public List<SchArrangeResult> searchSchArrangeResult() {
		SchArrangeResultExample example = new SchArrangeResultExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return arrangeResultMapper.selectByExample(example);
	}

	@Override
	public List<SchArrangeResult> searchSchArrangeResultByDoctor(String doctorFlow){
		SchArrangeResultExample example = new SchArrangeResultExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow);
		example.setOrderByClause("SCH_START_DATE");
		return arrangeResultMapper.selectByExample(example);
	}

	@Override
	public List<Map<String, Object>> querySchArrangeResultByDoctor(String doctorFlow) {
		return resultExtMapper.querySchArrangeResultByDoctor(doctorFlow);
	}

	@Override
	public List<SchArrangeResult> searchSchArrangeResultByDoctor4Sort(String doctorFlow){
		Map<String,Object> paramMap = new HashMap<>();
		if(StringUtil.isNotBlank(doctorFlow)){
			paramMap.put("doctorFlow",doctorFlow);
		}
		return resultExtMapper.searchSchArrangeResultByDoctor4Sort(paramMap);
	}

	@Override
	public List<SchArrangeResult> searchProcessByItems(Map<String, Object> paramMap) {
		SchArrangeResultExample example = new SchArrangeResultExample();
		SchArrangeResultExample.Criteria criteria = example.createCriteria();
		if (paramMap.get("recordStatus") != null && ((List<String>) (paramMap.get("recordStatus"))).size() > 0) {
			criteria.andRecordStatusIn((List<String>) (paramMap.get("recordStatus")));
		}
		if (paramMap.get("doctorFlow") != null) {
			criteria.andDoctorFlowEqualTo(paramMap.get("doctorFlow").toString());
		}
		if (paramMap.get("rotationFlow") != null) {
			criteria.andRotationFlowEqualTo(paramMap.get("rotationFlow").toString());
		}
		if (paramMap.get("groupFlow") != null) {
			criteria.andGroupFlowEqualTo(paramMap.get("groupFlow").toString());
		}
		if (paramMap.get("standardDeptId") != null) {
			criteria.andStandardDeptIdEqualTo(paramMap.get("standardDeptId").toString());
		}
		if (paramMap.get("shcDeptFlow") != null) {
			criteria.andSchDeptFlowEqualTo(paramMap.get("shcDeptFlow").toString());
		}
		if (paramMap.get("sessionNumber") != null) {
			criteria.andSessionNumberEqualTo(paramMap.get("sessionNumber").toString());
		}
		example.setOrderByClause("RECORD_STATUS desc,SCH_START_DATE,SCH_DEPT_ORDER");
		return arrangeResultMapper.selectByExample(example);
	}


	@Override
	public SchArrangeResult readSchArrangeResult(String resultFlow) {
		return arrangeResultMapper.selectByPrimaryKey(resultFlow);
	}

	@Override
	public List<SchArrangeResult> readSchArrangeResultByExample(SchArrangeResultExample example) {
		return arrangeResultMapper.selectByExample(example);
	}

	@Override
	public int saveSchArrangeResults(List<SchArrangeResult> resultList) {
		if(resultList!=null && resultList.size()>0){
			for(SchArrangeResult result : resultList){
				saveSchArrangeResult(result);
			}
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int saveSchArrangeResult(SchArrangeResult result) {
		if(result != null){
			if(StringUtil.isNotBlank(result.getResultFlow())){
				return update(result);
			}else{
				result.setResultFlow(PkUtil.getUUID());
				result.setArrangeFlow(result.getResultFlow());//新添加的
				return save(result);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}


	@Override
	public int saveProcessAndResult(ResDoctorSchProcess process,SchArrangeResult result) {
		if(result==null){
			return GlobalConstant.ZERO_LINE;
		}

		if(process==null){
			return GlobalConstant.ZERO_LINE;
		}

		String schDeptFlow = result.getSchDeptFlow();
		if(StringUtil.isNotBlank(schDeptFlow)){
			SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
			if(dept!=null){
				result.setDeptFlow(dept.getDeptFlow());
				result.setDeptName(dept.getDeptName());
				result.setSchDeptName(dept.getSchDeptName());

				process.setDeptFlow(dept.getDeptFlow());
				process.setDeptName(dept.getDeptName());
				process.setSchDeptName(dept.getSchDeptName());
			}
		}

		String startDate = result.getSchStartDate();
		String endDate = result.getSchEndDate();
		process.setSchStartDate(startDate);
		process.setSchEndDate(endDate);
		//轮转计划单位
		String unit = InitConfig.getSysCfg("res_rotation_unit");
		//默认按月计算
		int step = 30;
		if(SchUnitEnum.Week.getId().equals(unit)){
			//如果是周按7天算/没配置或者选择月按30天
			step = 7;
			BigDecimal realMonth = BigDecimal.valueOf(0);
			long realDays = DateUtil.signDaysBetweenTowDate(result.getSchEndDate(),result.getSchStartDate())+1;
			if(realDays!=0){
				//计算实际轮转的月/周数
				double realMonthF = (realDays/(step*1.0));
				realMonth = BigDecimal.valueOf(realMonthF);
				realMonth = realMonth.setScale(1,BigDecimal.ROUND_HALF_UP);
			}
			String schMonth= String.valueOf(realMonth.doubleValue());
			result.setSchMonth(schMonth);
		}else{
			Map<String,String> map= new HashMap<>();
			map.put("startDate",result.getSchStartDate());
			map.put("endDate",result.getSchEndDate());
			Double month = null;
			try {
				month = TimeUtil.getMonthsBetween(map);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String schMonth = String.valueOf(Double.parseDouble(month + ""));
			result.setSchMonth(schMonth);
		}


		String teacherUserFlow = process.getTeacherUserFlow();
		if(StringUtil.isNotBlank(teacherUserFlow)){
			SysUser user = userBiz.findByFlow(teacherUserFlow);
			if(user!=null){
				process.setTeacherUserName(user.getUserName());
				process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
			}
		}else{
			process.setTeacherUserName("");
		}

		String headUserFlow = process.getHeadUserFlow();
		if(StringUtil.isNotBlank(headUserFlow)){
			SysUser user = userBiz.findByFlow(headUserFlow);
			if(user!=null){
				process.setHeadUserName(user.getUserName());
			}
		}else{
			process.setHeadUserName("");
		}

		String standardDeptId = result.getStandardDeptId();
		if(StringUtil.isNotBlank(standardDeptId)){
			String standardDeptName = DictTypeEnum.StandardDept.getDictNameById(standardDeptId);
			result.setStandardDeptName(standardDeptName);
		}

		String resultFlow = result.getResultFlow();

		if(StringUtil.isNotBlank(resultFlow)){
			ResDoctorSchProcess schProcess = processBiz.searchByResultFlow(resultFlow);
			if(schProcess!=null){
				process.setProcessFlow(schProcess.getProcessFlow());
			}else{
				String doctorFlow = result.getDoctorFlow();
				ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
				if(doctor!=null){
					process.setStartDate(process.getSchStartDate());
					process.setEndDate(process.getSchEndDate());
					process.setOrgFlow(doctor.getOrgFlow());
					process.setOrgName(doctor.getOrgName());
				}
				process.setSchFlag(GlobalConstant.FLAG_N);
				process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
				process.setSchResultFlow(resultFlow);
				process.setUserFlow(doctorFlow);
			}
			update(result);
		}else{
			resultFlow = PkUtil.getUUID();

			result.setResultFlow(resultFlow);
			result.setArrangeFlow(resultFlow);

			String doctorFlow = result.getDoctorFlow();
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			result.setDoctorFlow(doctorFlow);
			if(doctor!=null){
				result.setDoctorName(doctor.getDoctorName());
				result.setSessionNumber(doctor.getSessionNumber());

//				result.setRotationFlow(doctor.getRotationFlow());
				SchRotation rotation = rotationBiz.readSchRotation(result.getRotationFlow());
				result.setRotationName(rotation.getRotationName());

				result.setStandardGroupFlow(result.getGroupFlow());
//				//根据方案 查最新一条标准科室
//				List<SchRotationDept> deptList=rotationDeptBiz.searchSchRotationDept(result.getRotationFlow());
//				if(deptList!=null && deptList.size()>0)
//				{
//					result.setStandardDeptId(deptList.get(0).getStandardDeptId());
//					result.setStandardDeptName(deptList.get(0).getStandardDeptName());
//					result.setStandardGroupFlow(deptList.get(0).getGroupFlow());
//				}
//				result.setOrgFlow(doctor.getOrgFlow());
				SysOrg sysOrg = orgBiz.readSysOrg(result.getOrgFlow());
				result.setOrgName(sysOrg.getOrgName());

				//process.setStartDate(DateUtil.getCurrDate());实际开始时间取计划开始时间
				process.setStartDate(process.getSchStartDate());
				process.setEndDate(process.getSchEndDate());
				process.setOrgFlow(result.getOrgFlow());
				process.setOrgName(sysOrg.getOrgName());

				String schFlag = doctor.getSchFlag();
				if(!GlobalConstant.FLAG_Y.equals(schFlag)){
					doctor.setSchFlag(GlobalConstant.FLAG_Y);
					doctor.setSelDeptFlag(GlobalConstant.FLAG_Y);
					doctorBiz.editDoctor(doctor);
				}
			}
			process.setSchFlag(GlobalConstant.FLAG_N);
			process.setIsCurrentFlag(GlobalConstant.FLAG_N);
			process.setSchResultFlow(resultFlow);
			process.setUserFlow(doctorFlow);
			save(result);
		}
		processBiz.edit(process);

		return GlobalConstant.ONE_LINE;
	}

	@Override
	public int saveInDept(SchArrangeResult result, ResDoctorSchProcess process) throws ParseException {
		if(result==null){
			return GlobalConstant.ZERO_LINE;
		}

		if(process==null){
			return GlobalConstant.ZERO_LINE;
		}

		String schDeptFlow = result.getSchDeptFlow();
		if(StringUtil.isNotBlank(schDeptFlow)){
			SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
			if(dept!=null){
				result.setDeptFlow(dept.getDeptFlow());
				result.setDeptName(dept.getDeptName());
				result.setSchDeptName(dept.getSchDeptName());

				process.setDeptFlow(dept.getDeptFlow());
				process.setDeptName(dept.getDeptName());
				process.setSchDeptName(dept.getSchDeptName());
			}
		}

		String startDate = result.getSchStartDate();
		String endDate = result.getSchEndDate();
		process.setSchStartDate(startDate);
		process.setSchEndDate(endDate);//轮转计划单位
		String unit = InitConfig.getSysCfg("res_rotation_unit");
		//默认按月计算
		int step = 30;
		if(SchUnitEnum.Week.getId().equals(unit)){
			//如果是周按7天算/没配置或者选择月按30天
			step = 7;
			BigDecimal realMonth = BigDecimal.valueOf(0);
			long realDays = DateUtil.signDaysBetweenTowDate(result.getSchEndDate(),result.getSchStartDate())+1;
			if(realDays!=0){
				//计算实际轮转的月/周数
				double realMonthF = (realDays/(step*1.0));
				realMonth = BigDecimal.valueOf(realMonthF);
				realMonth = realMonth.setScale(1,BigDecimal.ROUND_HALF_UP);
			}
			String schMonth= String.valueOf(realMonth.doubleValue());
			result.setSchMonth(schMonth);
		}else{
			Map<String,String> map= new HashMap<>();
			map.put("startDate",result.getSchStartDate());
			map.put("endDate",result.getSchEndDate());
			Double month = TimeUtil.getMonthsBetween(map);
			String schMonth = String.valueOf(Double.parseDouble(month + ""));
			result.setSchMonth(schMonth);
		}


		String teacherUserFlow = process.getTeacherUserFlow();
		if(StringUtil.isNotBlank(teacherUserFlow)){
			SysUser user = userBiz.findByFlow(teacherUserFlow);
			if(user!=null){
				process.setTeacherUserName(user.getUserName());
				process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
			}
		}

		String headUserFlow = process.getHeadUserFlow();
		if(StringUtil.isNotBlank(headUserFlow)){
			SysUser user = userBiz.findByFlow(headUserFlow);
			if(user!=null){
				process.setHeadUserName(user.getUserName());
			}
		}

		String standardDeptId = result.getStandardDeptId();
		if(StringUtil.isNotBlank(standardDeptId)){
			String standardDeptName = DictTypeEnum.StandardDept.getDictNameById(standardDeptId);
			result.setStandardDeptName(standardDeptName);
		}

		String resultFlow = result.getResultFlow();

		if(StringUtil.isNotBlank(resultFlow)){
			ResDoctorSchProcess schProcess = processBiz.searchByResultFlow(resultFlow);
			if(schProcess!=null){
				process.setProcessFlow(schProcess.getProcessFlow());
			}else{
				String doctorFlow = result.getDoctorFlow();
				ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
				if(doctor!=null){
					process.setStartDate(process.getSchStartDate());
					process.setEndDate(process.getSchEndDate());
					process.setOrgFlow(doctor.getOrgFlow());
					process.setOrgName(doctor.getOrgName());
				}
				process.setSchFlag(GlobalConstant.FLAG_N);
				process.setSchResultFlow(resultFlow);
				process.setUserFlow(doctorFlow);
			}
			update(result);
		}else{
			resultFlow = PkUtil.getUUID();

			result.setResultFlow(resultFlow);
			result.setArrangeFlow(resultFlow);

			String doctorFlow = result.getDoctorFlow();
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			result.setDoctorFlow(doctorFlow);
			if(doctor!=null){
				result.setDoctorName(doctor.getDoctorName());
				result.setSessionNumber(doctor.getSessionNumber());

				result.setRotationFlow(doctor.getRotationFlow());
				result.setRotationName(doctor.getRotationName());
				//根据方案 查最新一条标准科室
				List<SchRotationDept> deptList=rotationDeptBiz.searchSchRotationDept(result.getRotationFlow());
				if(deptList!=null && deptList.size()>0)
				{
					result.setStandardDeptId(deptList.get(0).getStandardDeptId());
					result.setStandardDeptName(deptList.get(0).getStandardDeptName());
					result.setStandardGroupFlow(deptList.get(0).getGroupFlow());
				}
				result.setOrgFlow(doctor.getOrgFlow());
				result.setOrgName(doctor.getOrgName());

				//process.setStartDate(DateUtil.getCurrDate());实际开始时间取计划开始时间
				process.setStartDate(process.getSchStartDate());
				process.setEndDate(process.getSchEndDate());
				process.setOrgFlow(doctor.getOrgFlow());
				process.setOrgName(doctor.getOrgName());

				String schFlag = doctor.getSchFlag();
				if(!GlobalConstant.FLAG_Y.equals(schFlag)){
					doctor.setSchFlag(GlobalConstant.FLAG_Y);
					doctor.setSelDeptFlag(GlobalConstant.FLAG_Y);
					doctorBiz.editDoctor(doctor);
				}
			}
			process.setSchFlag(GlobalConstant.FLAG_N);
			process.setIsCurrentFlag(GlobalConstant.FLAG_N);
			process.setSchResultFlow(resultFlow);
			process.setUserFlow(doctorFlow);
			save(result);
		}
		processBiz.edit(process);

		return GlobalConstant.ONE_LINE;
	}

	@Override
	public int save(SchArrangeResult result){
		GeneralMethod.setRecordInfo(result, true);
		return arrangeResultMapper.insertSelective(result);
	}

	@Override
	public int update(SchArrangeResult result){
		GeneralMethod.setRecordInfo(result, false);
		return arrangeResultMapper.updateByPrimaryKeySelective(result);
	}
	@Override
	public int editSchArrangeResult(SchArrangeResult result){
		GeneralMethod.setRecordInfo(result, false);
		return arrangeResultMapper.updateByPrimaryKey(result);
	}

	@Override
	public int saveSchArrangeResultForm(SchArrangeResultForm resultForm){
		if(resultForm != null){
			SchArrange arrange = new SchArrange();
			SysUser user = GlobalContext.getCurrentUser();
			arrange.setArrangeFlow(PkUtil.getUUID());
			arrange.setOperTime(DateUtil.getCurrDateTime());
			arrange.setOperUserFlow(user.getUserFlow());
			arrange.setOperUserName(user.getUserName());
			arrange.setOrgFlow(user.getOrgFlow());
			arrange.setOrgName(user.getOrgName());
			arrange.setArrangeTypeId(SchArrangeTypeEnum.Hand.getId());
			arrange.setArrangeTypeName(SchArrangeTypeEnum.Hand.getName());
			arrange.setArrangeStatusId(SchArrangeStatusEnum.Confirm.getId());
			arrange.setArrangeStatusName(SchArrangeStatusEnum.Confirm.getName());
			arrangeBiz.saveArrange(arrange);

			SchArrangeDoctor arrDoc = resultForm.getArrDoc();
			arrDoc.setArrangeFlow(arrange.getArrangeFlow());
			arrDoc.setOrgFlow(user.getOrgFlow());
			arrDoc.setOrgName(user.getOrgName());
			arrdocBiz.saveSchArrangeDoctor(arrDoc);

			List<SchArrangeDoctorDept> arrDocDeptList = resultForm.getArrDocDeptList();
			if(arrDocDeptList != null && arrDocDeptList.size()>0){
				for(SchArrangeDoctorDept arrDocDept : arrDocDeptList){
					arrDocDept.setArrangeFlow(arrange.getArrangeFlow());
					arrDocDept.setOrgFlow(user.getOrgFlow());
					arrDocDept.setOrgName(user.getOrgName());
					arrDocDeptBiz.saveSchArrangeDoctorDept(arrDocDept);
				}
			}

			delArrangeResult(arrDoc.getDoctorFlow());

			List<SchArrangeResult> arrResultList = resultForm.getResultList();
			if(arrResultList != null && arrResultList.size()>0){
				for(SchArrangeResult arrResult : arrResultList){
					arrResult.setArrangeFlow(arrange.getArrangeFlow());
					arrResult.setOrgFlow(user.getOrgFlow());
					arrResult.setOrgName(user.getOrgName());
					saveSchArrangeResult(arrResult);
				}
			}

			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int delArrangeResult(String doctorFlow){
		SchArrangeResultExample example = new SchArrangeResultExample();
		example.createCriteria().andDoctorFlowEqualTo(doctorFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		SchArrangeResult resultTemp = new SchArrangeResult();
		resultTemp.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		return arrangeResultMapper.updateByExampleSelective(resultTemp, example);
	}

	@Override
	public int delArrangeResult(String doctorFlow,boolean reStatus){
		if(reStatus){
			ResDoctor doctor = new ResDoctor();
			doctor.setDoctorFlow(doctorFlow);
			doctor.setSchFlag(GlobalConstant.FLAG_N);
			doctorBiz.editDoctor(doctor);
		}
		return delArrangeResult(doctorFlow);
	}

	@Override
	public List<SchArrangeResult> searchArrangeResultByDateAndOrg(String schStartDate,String schEndDate,String orgFlow){
		SchArrangeResultExample example = new SchArrangeResultExample();
		Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow);
		if(StringUtil.isNotBlank(schStartDate)){
			criteria.andSchEndDateGreaterThanOrEqualTo(schStartDate);
		}
		if(StringUtil.isNotBlank(schEndDate)){
			criteria.andSchStartDateLessThanOrEqualTo(schEndDate);
		}
		return arrangeResultMapper.selectByExample(example);
	}
	@Override
	public List<SchArrangeResult> searchArrangeResultByDateAndOrg1(Map<String,Object> map){
		return resultMapper.searchArrangeResultByDateAndOrgByMapNew(map);
	}

	@Override
	public List<SchArrangeResult> searchArrangeResultByDate(String schStartDate,String doctorFlow){
		SchArrangeResultExample example = new SchArrangeResultExample();
		Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(schStartDate)){
			criteria.andSchEndDateLessThanOrEqualTo(schStartDate);
		}
		if(StringUtil.isNotBlank(doctorFlow)){
			criteria.andDoctorFlowEqualTo(doctorFlow);
		}
		return arrangeResultMapper.selectByExample(example);
	}

	@Override
	public List<SchArrangeResult> searchArrangeResultByDate(String schStartDate,String schEndDate,String doctorName){
		SchArrangeResultExample example = new SchArrangeResultExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andSchEndDateBetween(schStartDate,schEndDate).andDoctorNameEqualTo(doctorName);
		example.setOrderByClause("SCH_END_DATE");
		return arrangeResultMapper.selectByExample(example);
	}

	@Override
	public List<SchArrangeResult> searchArrangeResultByDateAndDoctorFlow(String startDate,String endDate,String docFlow){
		return resultMapper.searchArrangeResultByDateAndDoctorFlow(startDate,endDate,docFlow);
	}

	@Override
	public List<SchArrangeResult> searchArrangeResultByDateAndDoctorFlows(String startDate,String endDate,List<ResDoctor> doctorList,String schDeptFlow){
		if(doctorList==null||doctorList.size()==0){
			return null;
		}
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("startDate",startDate);
		paramMap.put("endDate",endDate);
		paramMap.put("doctorList",doctorList);
		paramMap.put("schDeptFlow",schDeptFlow);
		return resultMapper.searchArrangeResultByDateAndDoctorFlows(paramMap);
	}

	@Override
	public List<Map<String,Object>> searchArrangeResultNotInDates(String startDate,String endDate,String docFlow){
		return resultMapper.searchArrangeResultNotInDates(startDate,endDate,docFlow);
	}

	@Override
	public List<SchArrangeResult> searchCycleArrangeResults(Map<String,Object> paramMap){
		return resultMapper.searchCycleArrangeResults(paramMap);
	}

	@Override
	public List<SchArrangeResult> searchArrangeResultByDoctorFlows(List<String> doctorFlows){
		List<SchArrangeResult> resultList = null;
		if(doctorFlows!=null&&!doctorFlows.isEmpty()){
			SchArrangeResultExample example = new SchArrangeResultExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowIn(doctorFlows);
			example.setOrderByClause("SCH_END_DATE");
			resultList = arrangeResultMapper.selectByExample(example);
		}
		return resultList;
	}

	@Override
	public List<SchArrangeResult> searchArrangeResultByResultFlow(List<String> resultFlows){
		List<SchArrangeResult> resultList = null;
		if(resultFlows!=null&&!resultFlows.isEmpty()){
			SchArrangeResultExample example = new SchArrangeResultExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andResultFlowIn(resultFlows);
			example.setOrderByClause("SCH_END_DATE");
			resultList = arrangeResultMapper.selectByExample(example);
		}
		return resultList;
	}

	@Override
	public List<Map<String,Object>> countResultByUser(List<String> userFlows){
		return resultMapper.countResultByUser(userFlows);
	}

	@Override
	public int saveResultByDoctor(ResDoctor doctor){
		if(doctor!=null){
			String doctorFlow = doctor.getDoctorFlow();

			//清理多余的选科数据
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("doctorFlow",doctorFlow);
			doctroDeptBiz.cleanInvalidSelDept(paramMap);

			String rotationFlow = doctor.getRotationFlow();
			Integer sessionNumber = Integer.parseInt(doctor.getSessionNumber());

			//设置选科标识
			doctor.setSelDeptFlag(GlobalConstant.FLAG_Y);
//			doctor.setSchFlag(GlobalConstant.FLAG_Y);
			doctorBiz.editDoctor(doctor);

			List<SchRotationGroup> groupList = groupBiz.searchOrgGroupOrAll(rotationFlow,doctor.getOrgFlow(),null);
			Map<String,SchRotationGroup> groupMap = new HashMap<String, SchRotationGroup>();
			for(SchRotationGroup group : groupList){
				groupMap.put(group.getGroupFlow(),group);
			}

			//留痕数据
			SchArrange arrange = new SchArrange();
			arrange.setArrangeFlow(PkUtil.getUUID());
			arrange.setOperTime(DateUtil.getCurrDateTime());
			arrange.setOperUserFlow(doctorFlow);
			arrange.setOperUserName(doctor.getDoctorName());
			arrange.setOrgFlow(doctor.getOrgFlow());
			arrange.setOrgName(doctor.getOrgName());
			arrange.setArrangeTypeId(SchArrangeTypeEnum.Hand.getId());
			arrange.setArrangeTypeName(SchArrangeTypeEnum.Hand.getName());
			arrange.setArrangeStatusId(SchArrangeStatusEnum.Confirm.getId());
			arrange.setArrangeStatusName(SchArrangeStatusEnum.Confirm.getName());
			arrangeBiz.saveArrange(arrange);

			SchArrangeDoctor arrDoc = new SchArrangeDoctor();
			arrDoc.setArrangeFlow(arrange.getArrangeFlow());
			arrDoc.setDoctorFlow(doctorFlow);
			arrDoc.setDoctorName(doctor.getDoctorName());
			arrDoc.setRotationFlow(rotationFlow);
			arrDoc.setRotationName(doctor.getRotationName());
			arrDoc.setOrgFlow(doctor.getOrgFlow());
			arrDoc.setOrgName(doctor.getOrgName());
			arrdocBiz.saveSchArrangeDoctor(arrDoc);

			SchArrangeDoctorDept arrDocDept = new SchArrangeDoctorDept();
			arrDocDept.setArrangeFlow(arrange.getArrangeFlow());
			arrDocDept.setDoctorFlow(doctorFlow);
			arrDocDept.setDoctorName(doctor.getDoctorName());
			arrDocDept.setOrgFlow(doctor.getOrgFlow());
			arrDocDept.setOrgName(doctor.getOrgName());
			//=============================================

			SchArrangeResult result = new SchArrangeResult();
			result.setDoctorFlow(doctorFlow);
			result.setDoctorName(doctor.getDoctorName());
			result.setSessionNumber(doctor.getSessionNumber());
			result.setRotationFlow(doctor.getRotationFlow());
			result.setRotationName(doctor.getRotationName());
			result.setOrgFlow(doctor.getOrgFlow());
			result.setOrgName(doctor.getOrgName());

			List<SchRotationDept> mustRotationDeptList = rotationDeptBiz.searchOrgSchRotationDeptMust(rotationFlow,doctor.getOrgFlow());
			if(mustRotationDeptList!=null && mustRotationDeptList.size()>0){
				for(SchRotationDept rotationDept : mustRotationDeptList){
					result.setDeptFlow(rotationDept.getDeptFlow());
					result.setDeptName(rotationDept.getDeptName());
					result.setSchDeptFlow(rotationDept.getSchDeptFlow());
					result.setSchDeptName(rotationDept.getSchDeptName());
					result.setIsRequired(rotationDept.getIsRequired());
					result.setGroupFlow(rotationDept.getGroupFlow());
					result.setStandardDeptId(rotationDept.getStandardDeptId());
					result.setStandardDeptName(rotationDept.getStandardDeptName());
					String standardGroupFlow = null;
					SchRotationGroup group = groupMap.get(rotationDept.getGroupFlow());

					if(group!=null){
						standardGroupFlow = group.getStandardGroupFlow();
					}
					result.setStandardGroupFlow(standardGroupFlow);
					String schMonth = rotationDept.getSchMonth();
					Integer saveCount = 0;
					if(StringUtil.isNotBlank(schMonth) && !"0".equals(schMonth)){
						result.setResultFlow(null);
						result.setArrangeFlow(arrange.getArrangeFlow());
						result.setSchYear(sessionNumber.toString());
						result.setSchMonth(schMonth);
						if(rotationDept.getOrdinal()!=null){
							result.setSchDeptOrder(BigDecimal.valueOf(rotationDept.getOrdinal()));
						}
						saveCount+=saveSchArrangeResult(result);
					}

					if(saveCount<=0){
						result.setResultFlow(null);
						result.setArrangeFlow(arrange.getArrangeFlow());
						result.setSchMonth("0");
						if(rotationDept.getOrdinal()!=null){
							result.setSchDeptOrder(BigDecimal.valueOf(rotationDept.getOrdinal()));
						}
						saveSchArrangeResult(result);
					}

					//留痕数据
					arrDocDept.setArrDocDeptFlow(null);
					arrDocDept.setDeptFlow(rotationDept.getDeptFlow());
					arrDocDept.setDeptName(rotationDept.getDeptName());
					arrDocDept.setSchDeptFlow(rotationDept.getSchDeptFlow());
					arrDocDept.setSchDeptName(rotationDept.getSchDeptName());
					arrDocDept.setGroupFlow(rotationDept.getGroupFlow());
					if (group!=null) {
						arrDocDept.setGroupName(group.getGroupName());
						arrDocDept.setSchStageId(group.getSchStageId());
						arrDocDept.setSchStageName(group.getSchStageName());
					}
					arrDocDept.setSchMonth(schMonth);
					arrDocDept.setStandardGroupFlow(standardGroupFlow);
					arrDocDept.setIsRequired(rotationDept.getIsRequired());
					arrDocDept.setStandardDeptId(rotationDept.getStandardDeptId());
					arrDocDept.setStandardDeptName(rotationDept.getStandardDeptName());
					arrDocDeptBiz.saveSchArrangeDoctorDept(arrDocDept);
					//==================================================
				}
			}

			List<SchDoctorDept> doctorDeptList =  doctroDeptBiz.searchSchDoctorDept(doctorFlow);
			if(doctorDeptList!=null && doctorDeptList.size()>0){
				for(SchDoctorDept doctorDept : doctorDeptList){
					result.setDeptFlow(doctorDept.getDeptFlow());
					result.setDeptName(doctorDept.getDeptName());
					result.setSchDeptFlow(doctorDept.getSchDeptFlow());
					result.setSchDeptName(doctorDept.getSchDeptName());
					result.setIsRequired(GlobalConstant.FLAG_N);
					result.setGroupFlow(doctorDept.getGroupFlow());
					result.setStandardDeptId(doctorDept.getStandardDeptId());
					result.setStandardDeptName(doctorDept.getStandardDeptName());
					String standardGroupFlow = null;
					SchRotationGroup group = groupMap.get(doctorDept.getGroupFlow());
					if(group!=null){
						standardGroupFlow = group.getStandardGroupFlow();
					}
					result.setStandardGroupFlow(standardGroupFlow);

					String schMonth = doctorDept.getSchMonth();

					Integer saveCount = 0;
					if(StringUtil.isNotBlank(schMonth) && !"0".equals(schMonth)){
						result.setResultFlow(null);
						result.setArrangeFlow(arrange.getArrangeFlow());
						result.setSchYear(sessionNumber.toString());
						result.setSchMonth(schMonth);
						if(doctorDept.getOrdinal()!=null){
							result.setSchDeptOrder(BigDecimal.valueOf(doctorDept.getOrdinal()));
						}
						saveCount+=saveSchArrangeResult(result);
					}

					if(saveCount<=0){
						result.setResultFlow(null);
						result.setArrangeFlow(arrange.getArrangeFlow());
						result.setSchMonth("0");
						if(doctorDept.getOrdinal()!=null){
							result.setSchDeptOrder(BigDecimal.valueOf(doctorDept.getOrdinal()));
						}
						saveSchArrangeResult(result);
					}

					//留痕数据
					arrDocDept.setArrDocDeptFlow(null);
					arrDocDept.setDeptFlow(doctorDept.getDeptFlow());
					arrDocDept.setDeptName(doctorDept.getDeptName());
					arrDocDept.setSchDeptFlow(doctorDept.getSchDeptFlow());
					arrDocDept.setSchDeptName(doctorDept.getSchDeptName());
					arrDocDept.setGroupFlow(doctorDept.getGroupFlow());
					arrDocDept.setGroupName(group.getGroupName());
					arrDocDept.setSchMonth(schMonth);
					arrDocDept.setSchStageId(group.getSchStageId());
					arrDocDept.setSchStageName(group.getSchStageName());
					arrDocDept.setStandardGroupFlow(standardGroupFlow);
					arrDocDept.setIsRequired(doctorDept.getIsRequired());
					arrDocDept.setStandardDeptId(doctorDept.getStandardDeptId());
					arrDocDept.setStandardDeptName(doctorDept.getStandardDeptName());
					arrDocDeptBiz.saveSchArrangeDoctorDept(arrDocDept);
					//====================
				}
			}

			//sortResult(doctorFlow);
			sortResultByStage(doctorFlow,doctor.getOrgFlow(),rotationFlow);
		}
		return GlobalConstant.ONE_LINE;
	}

	private void sortResultByStage(String userFlow,String orgFlow,String rotationFlow){
		if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(orgFlow)){
			Map<String,List<SchArrangeResult>> resultMap = null;
			List<SchArrangeResult> resultList = searchSchArrangeResultByDoctor(userFlow);
			if(resultList!=null && resultList.size()>0){
				resultMap = new HashMap<String, List<SchArrangeResult>>();
				for(SchArrangeResult sar : resultList){
					String key = sar.getGroupFlow();
					if(resultMap.get(key)==null){
						List<SchArrangeResult> sarTempList = new ArrayList<SchArrangeResult>();
						sarTempList.add(sar);
						resultMap.put(key,sarTempList);
					}else{
						resultMap.get(key).add(sar);
					}
				}

				Map<String,List<SchRotationGroup>> groupMap = null;
				List<SchRotationGroup> groupList = groupBiz.searchOrgGroupOrAll(rotationFlow,orgFlow,null);
				if(groupList!=null && groupList.size()>0){
					groupMap = new HashMap<String, List<SchRotationGroup>>();
					for(SchRotationGroup srg : groupList){
						String key = srg.getSchStageId();
						if(groupMap.get(key)==null){
							List<SchRotationGroup> srgTempList = new ArrayList<SchRotationGroup>();
							srgTempList.add(srg);
							groupMap.put(key,srgTempList);
						}else{
							groupMap.get(key).add(srg);
						}
					}
				}
				if(resultMap!=null && groupMap!=null){
					int ord = 0 ;
					for(SchStageEnum stage : SchStageEnum.values()){
						String stageId = stage.getId();

						List<SchRotationGroup> srgTempList = groupMap.get(stageId);
						if(srgTempList!=null && srgTempList.size()>0){
							for(SchRotationGroup srg : srgTempList){
								String groupFlow = srg.getGroupFlow();

								List<SchArrangeResult> sarTempList = resultMap.get(groupFlow);
								if(sarTempList!=null && sarTempList.size()>0){
									for(SchArrangeResult sar : sarTempList){
										sar.setSchDeptOrder(BigDecimal.valueOf(ord++));
										saveSchArrangeResult(sar);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 为医师的排班按照标准科室为组的形式排序
	 * @param userFlow
	 */
	private int sortResult(String userFlow){
		return resultExtMapper.sortResult(userFlow);
	}

	@Override
	public Map<String,String> countDateArea(ResDoctor doctor){
		return resultExtMapper.countDateArea(doctor);
	}

	@Override
	public List<Map<String,Object>> countMonthNum(String month,ResDoctor doctor){
		return resultExtMapper.countMonthNum(month,doctor);
	}

	@Override
	public List<SchArrangeResult> searchInMonthResult(String schDeptFlow,String month,ResDoctor doctor){
		return resultExtMapper.searchInMonthResult(schDeptFlow,month,doctor);
	}

	@Override
	public List<SchArrangeResult> willInDoctor(String orgFlow,String userFlow){
		return resultExtMapper.willInDoctor(orgFlow,userFlow);
	}

	@Override
	public List<SchArrangeResult> willInDoctor(Map<String, Object> map) {
		return resultExtMapper.willInDoctor2(map);
	}

	@Override
	public int countRotationUse(String rotationFlow){
		return resultExtMapper.countRotationUse(rotationFlow);
	}

	@Override
	public int countArrangeResultBySchDeptFlow(String month, String schDeptFlow, String sessionNumber) throws ParseException {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(month+"-15");
		c.setTime(date);
		String startDate=sdf.format(c.getTime());
		c.add(Calendar.MONTH, 1);
		String endDate=DateUtil.addDate(sdf.format(c.getTime()), -1);

		return resultExtMapper.countResultBySchDeptFlow(startDate,endDate,schDeptFlow,sessionNumber);
	}

	@Override
	public List<Map<String,Object>> doctorSelectDeptCount(String doctorFlow){
		return resultExtMapper.doctorSelectDeptCount(doctorFlow);
	}

	@Override
	public List<SchArrangeResult> searchResultByDoctor(ResDoctor doctor){
		return resultExtMapper.searchResultByDoctor(doctor);
	}

	@Override
	public int createFreeRosteringResult(ResDoctor doctor){
		if(doctor!=null){
			String orgFlow = doctor.getOrgFlow();
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(orgFlow);
			if(schDeptList!=null && schDeptList.size()>0){
				SysUser operUser = GlobalContext.getCurrentUser();

				SchArrange arrange = new SchArrange();
				arrange.setArrangeFlow(PkUtil.getUUID());
				arrange.setDoctorNum(1);
				arrange.setOperTime(DateUtil.getCurrDateTime());
				arrange.setOperUserFlow(operUser.getUserFlow());
				arrange.setOperUserName(operUser.getUserName());
				arrange.setArrangeTypeId(SchArrangeTypeEnum.Hand.getId());
				arrange.setArrangeTypeName(SchArrangeTypeEnum.Hand.getName());
				arrange.setArrangeStatusId(SchArrangeStatusEnum.Finish.getId());
				arrange.setArrangeStatusName(SchArrangeStatusEnum.Finish.getName());
				arrange.setOrgFlow(doctor.getOrgFlow());
				arrange.setOrgName(doctor.getOrgName());
				arrangeBiz.saveArrange(arrange);

				SchArrangeResult result = new SchArrangeResult();
				result.setArrangeFlow(arrange.getArrangeFlow());
				result.setDoctorFlow(doctor.getDoctorFlow());
				result.setDoctorName(doctor.getDoctorName());
				result.setSessionNumber(doctor.getSessionNumber());
				result.setSchYear("1");
				result.setOrgFlow(doctor.getOrgFlow());
				result.setOrgName(doctor.getOrgName());
				result.setIsRequired(GlobalConstant.FLAG_Y);
				for(SchDept schDept : schDeptList){
					result.setResultFlow(null);
					result.setDeptFlow(schDept.getDeptFlow());
					result.setDeptName(schDept.getDeptName());
					result.setSchDeptFlow(schDept.getSchDeptFlow());
					result.setSchDeptName(schDept.getSchDeptName());
					saveSchArrangeResult(result);
				}

				doctor.setSelDeptFlag(GlobalConstant.FLAG_Y);
				doctorBiz.editDoctor(doctor);

				return GlobalConstant.ONE_LINE;
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<SchArrangeResult> cutAfterResult(List<String> doctorFlows){
		return resultExtMapper.cutAfterResult(doctorFlows);
	}

	@Override
	public int editFreeRostering(String doctorFlow,String[] addSchDeptFlows,String[] delResultFlows){
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			SysUser operUser = GlobalContext.getCurrentUser();

			//重置状态
			doctor.setSchFlag(GlobalConstant.FLAG_N);
			doctor.setSchStatusId("");
			doctor.setSchStatusName("");
			doctorBiz.editDoctor(doctor);

			SchArrange arrange = new SchArrange();
			arrange.setArrangeFlow(PkUtil.getUUID());
			arrange.setDoctorNum(1);
			arrange.setOperTime(DateUtil.getCurrDateTime());
			arrange.setOperUserFlow(operUser.getUserFlow());
			arrange.setOperUserName(operUser.getUserName());
			arrange.setArrangeTypeId(SchArrangeTypeEnum.Hand.getId());
			arrange.setArrangeTypeName(SchArrangeTypeEnum.Hand.getName());
			arrange.setArrangeStatusId(SchArrangeStatusEnum.Finish.getId());
			arrange.setArrangeStatusName(SchArrangeStatusEnum.Finish.getName());
			arrange.setOrgFlow(doctor.getOrgFlow());
			arrange.setOrgName(doctor.getOrgName());
			arrangeBiz.saveArrange(arrange);

			if(doctor!=null){
				if(addSchDeptFlows!=null && addSchDeptFlows.length>0){
					List<String> schDeptFlows = new ArrayList<String>();
					for(String flow : addSchDeptFlows){
						schDeptFlows.add(flow);
					}
					List<SchDept> schDeptList = schDeptBiz.searchDeptByFlows(schDeptFlows);
					if(schDeptList!=null && schDeptList.size()>0){
						SchArrangeResult result = new SchArrangeResult();
						result.setArrangeFlow(arrange.getArrangeFlow());
						result.setDoctorFlow(doctor.getDoctorFlow());
						result.setDoctorName(doctor.getDoctorName());
						result.setSessionNumber(doctor.getSessionNumber());
						result.setSchYear(doctor.getSessionNumber());
						for(SchDept schDept : schDeptList){
							result.setResultFlow(null);
							result.setSchDeptFlow(schDept.getSchDeptFlow());
							result.setSchDeptName(schDept.getSchDeptName());
							result.setDeptFlow(schDept.getDeptFlow());
							result.setDeptName(schDept.getDeptName());
							saveSchArrangeResult(result);
						}
					}
				}

				if(delResultFlows!=null && delResultFlows.length>0){
					SchArrangeResult result = new SchArrangeResult();
					result.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
					for(String flow : delResultFlows){
						result.setResultFlow(flow);
						saveSchArrangeResult(result);
					}
				}
			}
		}

		return GlobalConstant.ONE_LINE;
	}

	@Override
	public int editFreeRostering(String doctorFlow, String groupFlow, String standardDeptId,String standardDeptName, String schDeptFlow) {
		if (StringUtil.isNotBlank(doctorFlow)) {
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			SchRotationGroup group = groupBiz.readSchRotationGroup(groupFlow);

			//重置状态
			doctor.setSchFlag(GlobalConstant.FLAG_N);
			doctor.setSchStatusId("");
			doctor.setSchStatusName("");
			doctorBiz.editDoctor(doctor);


			if (doctor != null) {
				if (StringUtil.isNotBlank(schDeptFlow)) {
					List<String> schDeptFlows = new ArrayList<String>();
					schDeptFlows.add(schDeptFlow);
					List<SchDept> schDeptList = schDeptBiz.searchDeptByFlows(schDeptFlows);
					if (schDeptList != null && schDeptList.size() > 0) {
						SchArrangeResult result = new SchArrangeResult();
						result.setArrangeFlow(doctor.getDoctorFlow());
						result.setDoctorFlow(doctor.getDoctorFlow());
						result.setDoctorName(doctor.getDoctorName());
						result.setSessionNumber(doctor.getSessionNumber());
						result.setSchYear(doctor.getSessionNumber());
						for (SchDept schDept : schDeptList) {
							result.setResultFlow(null);
							result.setSchDeptFlow(schDept.getSchDeptFlow());
							result.setSchDeptName(schDept.getSchDeptName());
							result.setDeptFlow(schDept.getDeptFlow());
							result.setDeptName(schDept.getDeptName());
							result.setRotationFlow(group.getRotationFlow());
							result.setOrgFlow(doctor.getOrgFlow());
							result.setOrgName(doctor.getOrgName());
							if(group.getRotationFlow().equals(doctor.getRotationFlow())){
								result.setRotationName(doctor.getRotationName());
							}else if(group.getRotationFlow().equals(doctor.getSecondRotationFlow())){
								result.setRotationName(doctor.getSecondRotationName());
							}
							result.setStandardDeptId(standardDeptId);
							result.setStandardDeptName(standardDeptName);
							result.setStandardGroupFlow(group.getStandardGroupFlow());
							result.setGroupFlow(groupFlow);
							result.setDeptName(schDept.getDeptName());
							//不是方案配置下的轮转记录
							result.setIsRotation(GlobalConstant.FLAG_N);
							saveSchArrangeResult(result);
						}
					}
				}
			}
		}

		return GlobalConstant.ONE_LINE;
	}

	@Override
	public int saveGroupResult(List<SchArrangeResult> resultList,String groupId,SysUser operUser){
		if(groupId!=null && operUser!=null && resultList!=null && resultList.size()>0){
			//获取组员
			ResDoctor searchDoctor = new ResDoctor();
			searchDoctor.setGroupId(groupId);
			searchDoctor.setOrgFlow(operUser.getOrgFlow());
			searchDoctor.setDoctorCategoryId(RecDocCategoryEnum.Intern.getId());
			List<ResDoctor> doctorList = doctorBiz.searchByDoc(searchDoctor);
			if(doctorList!=null && doctorList.size()>0){
				//留痕数据
				SchArrange arrange = new SchArrange();
				arrange.setArrangeFlow(PkUtil.getUUID());
				arrange.setDoctorNum(doctorList.size());
				arrange.setOperTime(DateUtil.getCurrDateTime());
				arrange.setOperUserFlow(operUser.getUserFlow());
				arrange.setOperUserName(operUser.getUserName());
				arrange.setArrangeTypeId(SchArrangeTypeEnum.Hand.getId());
				arrange.setArrangeTypeName(SchArrangeTypeEnum.Hand.getName());
				arrange.setArrangeStatusId(SchArrangeStatusEnum.Finish.getId());
				arrange.setArrangeStatusName(SchArrangeStatusEnum.Finish.getName());
				arrange.setOrgFlow(operUser.getOrgFlow());
				arrange.setOrgName(operUser.getOrgName());
				arrangeBiz.saveArrange(arrange);

				//获取规则详情
				List<String> recordFlows = new ArrayList<String>();
				for(SchArrangeResult result : resultList){
					recordFlows.add(result.getResultFlow());
				}
				List<SchRotationDept> rotationDeptList = rotationDeptBiz.searchRotationDeptByFlows(recordFlows);
				if(rotationDeptList!=null && rotationDeptList.size()>0){
					Map<String,SchRotationDept> rotationDeptMap = new HashMap<String, SchRotationDept>();
					List<SchDoctorDept> doctorDeptList = new ArrayList<SchDoctorDept>();
					for(SchRotationDept rotationDept : rotationDeptList){
						rotationDeptMap.put(rotationDept.getRecordFlow(),rotationDept);

						//记录选科科室
						if(!GlobalConstant.FLAG_Y.equals(rotationDept.getIsRequired())){
							SchDoctorDept doctorDept = new SchDoctorDept();
							doctorDept.setGroupFlow(rotationDept.getGroupFlow());
							doctorDept.setSchMonth(rotationDept.getSchMonth());
							doctorDept.setSchDeptFlow(rotationDept.getSchDeptFlow());
							doctorDept.setSchDeptName(rotationDept.getSchDeptName());
							doctorDept.setDeptFlow(rotationDept.getDeptFlow());
							doctorDept.setDeptName(rotationDept.getDeptName());
							doctorDept.setOrgFlow(rotationDept.getOrgFlow());
							doctorDept.setOrgName(rotationDept.getOrgName());
							doctorDept.setStandardDeptId(rotationDept.getStandardDeptId());
							doctorDept.setStandardDeptName(rotationDept.getStandardDeptName());
							doctorDept.setIsRequired(rotationDept.getIsRequired());

							doctorDeptList.add(doctorDept);
						}
					}

					Map<String,String> groupMap = null;
					ResDoctor docTemp = doctorList.get(0);
					if(docTemp!=null){
						String rotationFlow = docTemp.getRotationFlow();
						String orgFlow = docTemp.getOrgFlow();
						if(StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(orgFlow)){
							List<SchRotationGroup> groups = groupBiz.searchOrgGroupOrAll(rotationFlow,orgFlow,null);
							if(groups!=null && !groups.isEmpty()){
								groupMap = new HashMap<String, String>();
								for(SchRotationGroup srg : groups){
									groupMap.put(srg.getGroupFlow(),srg.getStandardGroupFlow());
								}
							}
						}
					}

					//为每个组员保存计划
					for(ResDoctor doctor : doctorList){
						int order = 0;

						//为医师创建选科数据
						for(SchDoctorDept doctorDept : doctorDeptList){
							doctorDept.setDoctorFlow(doctor.getDoctorFlow());
							doctorDept.setDoctorName(doctor.getDoctorName());
							doctorDept.setRotationFlow(doctor.getRotationFlow());
							doctorDept.setRotationName(doctor.getRotationName());
							doctorDept.setSessionNumber(doctor.getSessionNumber());

							doctroDeptBiz.save(doctorDept);
						}

						for(SchArrangeResult resultTemp : resultList){
							//为医师创建排班数据
							SchRotationDept rotationDept = rotationDeptMap.get(resultTemp.getResultFlow());
							SchArrangeResult result = new SchArrangeResult();
							result.setArrangeFlow(arrange.getArrangeFlow());
							result.setDoctorFlow(doctor.getDoctorFlow());
							result.setDoctorName(doctor.getDoctorName());
							result.setSessionNumber(doctor.getSessionNumber());
							result.setRotationFlow(doctor.getRotationFlow());
							result.setRotationName(doctor.getRotationName());
							result.setSchDeptOrder(BigDecimal.valueOf(order++));
							result.setDeptFlow(rotationDept.getDeptFlow());
							result.setDeptName(rotationDept.getDeptName());
							result.setSchDeptFlow(rotationDept.getSchDeptFlow());
							result.setSchDeptName(rotationDept.getSchDeptName());
							result.setOrgFlow(doctor.getOrgFlow());
							result.setOrgName(doctor.getOrgName());
							result.setIsRequired(rotationDept.getIsRequired());
							result.setStandardDeptId(rotationDept.getStandardDeptId());
							result.setStandardDeptName(rotationDept.getStandardDeptName());
							String groupFlow = rotationDept.getGroupFlow();
							result.setGroupFlow(groupFlow);
							result.setSchYear(resultTemp.getSchYear());
							result.setSchMonth(resultTemp.getSchMonth());
							result.setSchStartDate(resultTemp.getSchStartDate());
							result.setSchEndDate(resultTemp.getSchEndDate());

							if(groupMap!=null){
								String standardGroupFlow = groupMap.get(groupFlow);
								result.setStandardGroupFlow(standardGroupFlow);
							}

							saveSchArrangeResult(result);

							//更新医师选科排班状态
							doctor.setSchFlag(GlobalConstant.FLAG_Y);
							doctor.setSelDeptFlag(GlobalConstant.FLAG_Y);
							doctorBiz.editDoctor(doctor);
						}
					}

					return GlobalConstant.ONE_LINE;
				}
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<SchArrangeResult> searchResultByGroupFlow(String groupFlow,
			String standardDeptId,String doctorFlow) {
		SchArrangeResultExample example = new SchArrangeResultExample();
		Criteria exam=example.createCriteria();
		exam.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(groupFlow)) {
			exam.andStandardGroupFlowEqualTo(groupFlow);
		}
		if (StringUtil.isNotBlank(standardDeptId)) {
			exam.andStandardDeptIdEqualTo(standardDeptId);
		}
		if (StringUtil.isNotBlank(doctorFlow)) {
			exam.andDoctorFlowEqualTo(doctorFlow);
		}
		example.setOrderByClause("SCH_START_DATE");
		return arrangeResultMapper.selectByExample(example);
	}

	@Override
	public List<SchArrangeResult> searchResultByRotationDepts(List<SchRotationDept> depts,String doctorFlow) {
		List<SchArrangeResult> resultList=new ArrayList<>();
		if(depts!=null&&depts.size()>0)
		{
			SchArrangeResultExample example = new SchArrangeResultExample();
			Criteria exam=example.createCriteria();
			exam.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			for(SchRotationDept srd :depts)
			{

				if (StringUtil.isNotBlank(srd.getGroupFlow())) {
					exam.andStandardGroupFlowEqualTo(srd.getGroupFlow());
				}
				if (StringUtil.isNotBlank(srd.getStandardDeptId())) {
					exam.andStandardDeptIdEqualTo(srd.getStandardDeptId());
				}
				if (StringUtil.isNotBlank(doctorFlow)) {
					exam.andDoctorFlowEqualTo(doctorFlow);
				}
				List<SchArrangeResult> results=arrangeResultMapper.selectByExample(example);
				if(results!=null&&results.size()>0)
				{
					resultList.addAll(results);
				}
			}
		}
		return resultList;
	}

	@Override
	public void updateResultHaveAfter(String haveAfterPic, String schRotationDeptFlow, String operUserFlow) {
		resultExtMapper.updateResultHaveAfter( haveAfterPic,  schRotationDeptFlow, operUserFlow);
	}

	@Override
	public List<Map<String, String>> searchDocResultsByMonth(Map<String, Object> paramMap) {
		return resultExtMapper.searchDocResultsByMonth(paramMap);
	}

	@Override
	public List<SchArrangeResult> searchSchArrangeResultByDoctorAndDate(Map<String, String> paramMap) {
		return resultExtMapper.searchSchArrangeResultByDoctorAndDate(paramMap);
	}
	@Override
	public List<SchArrangeResult> searchSchArrangeResultByMap(Map<String, String> paramMap) {
		return resultExtMapper.searchSchArrangeResultByMap(paramMap);
	}

	@Override
	public List<JsresDoctorDeptDetail> deptDoctorAllWorkDetailByNow(String rotationFlow, String doctorFlow, String applyYear) {
		return resultExtMapper.deptDoctorAllWorkDetailByNow(rotationFlow,doctorFlow,applyYear);
	}

	@Override
	public List<SchArrangeResult> searchArrangeResultByDateAndOrgByMap(Map<String, String> param) {
		return resultExtMapper.searchArrangeResultByDateAndOrgByMap(param);
	}

	@Override
	public List<SysDept> findDeptsByDoctor(String doctorFlow) {
		return resultExtMapper.findDeptsByDoctor(doctorFlow);
	}

	@Override
	public List<HbresDoctorDeptDetail> hbresDoctorSchResults(List<String> resultFlows) {
		if(resultFlows!=null&&resultFlows.size()>0)
			return resultExtMapper.hbresDoctorSchResults(resultFlows);
		return null;
	}

	@Override
	public List<HbresDoctorDeptDetail> hbresDoctorDeptDetails(String doctorFlow, String applyYear) {
		HbresDoctorDeptDetailExample example=new HbresDoctorDeptDetailExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow).andApplyYearEqualTo(applyYear);
		List<HbresDoctorDeptDetail> list=hbresDoctorDeptDetailMapper.selectByExample(example);
		return list;
	}

	@Override
	public Map<String, String> imgUpload(String resultFlow, MultipartFile file, String fileType) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("status", GlobalConstant.OPRE_FAIL_FLAG);
		if(file!=null){
			List<String> mimeList = new ArrayList<String>();
			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
				mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
			}
			List<String> suffixList = new ArrayList<String>();
			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
				suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
			}
			String fileName = file.getOriginalFilename();//文件名
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			if(!(mimeList.contains(file.getContentType())&&suffixList.contains(suffix))){
				map.put("error", GlobalConstant.UPLOAD_IMG_TYPE_ERROR);
				return  map;

			}
			long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
			if (file.getSize() > limitSize * 1024 * 1024) {
				map.put("error", GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize +"M") ;
				return  map;
			}
			try {
				/*创建目录*/
				String dateString = DateUtil.getCurrDate2();
				String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator+fileType+File.separator + dateString ;
				File fileDir = new File(newDir);
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				/*文件名*/
				fileName = file.getOriginalFilename();
				fileName = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
				File newFile = new File(fileDir, fileName);
				file.transferTo(newFile);
				String filePath = "/" + fileType + "/" + dateString + "/" + fileName;
				String url = InitConfig.getSysCfg("upload_base_url")+filePath;
				PubFile pubFile=fileBiz.readProductFile(resultFlow,fileType);
				if(pubFile==null)
					pubFile=new PubFile();
				pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				pubFile.setFilePath(filePath);
				pubFile.setFileName(fileName);
				pubFile.setFileSuffix(suffix);
				pubFile.setProductType(fileType);
				pubFile.setProductFlow(resultFlow);
				int c=fileBiz.editFile(pubFile);
				if(c==1) {
					map.put("url",url);
					map.put("status",GlobalConstant.OPRE_SUCCESSED_FLAG);
				}else{
					map.put("error","上传失败！");
					map.put("status",GlobalConstant.OPRE_FAIL_FLAG);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	@Override
	public List<SchArrangeResult> schArrangeResultQuery(Map<String, Object> map) {
		List<SchArrangeResult> arrangeResultList=resultMapper.schArrangeResultQuery(map);
		return arrangeResultList;
	}

	/*@Override
	public int roundRobinStudents(String doctorFlow, String currDate) {
		int num=resultMapper.roundRobinStudents(doctorFlow,currDate);
		return num;
	}*/

	@Override
	public int checkResultDate(Map<String,Object> paramMap){
		return resultExtMapper.checkResultDate(paramMap);
	}
	@Override
	public int checkScholarDate(Map<String,Object> paramMap){
		return resultExtMapper.checkResultDate(paramMap);
	}

	@Override
	public int checkSelectResult(String doctorFlow, String startDate, String endDate){
		return resultExtMapper.checkSelectResult(doctorFlow,startDate,endDate);
	}
	@Override
	public int getAllSchMonth(String doctorFlow){
		return resultExtMapper.getAllSchMonth(doctorFlow);
	}

	@Override
	public int checkSelectStandardDept(String doctorFlow, String standardDeptId, String groupFlow){
		return resultExtMapper.checkSelectStandardDept(doctorFlow,standardDeptId,groupFlow);
	}
	@Override
	public int countArrangeResultByGroupFlow(String doctorFlow,String groupFlow){
		return resultExtMapper.countArrangeResultByGroupFlow(doctorFlow,groupFlow);
	}

	@Override
	public int countResultByDoctorAndDate(String d, String doctorFlow) throws ParseException {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(d+"-15");
		c.setTime(date);
		String startDate=sdf.format(c.getTime());
		c.add(Calendar.MONTH, 1);
		String endDate=DateUtil.addDate(sdf.format(c.getTime()), -1);
		return resultExtMapper.countResultByDoctorAndDate(startDate,endDate,doctorFlow);
	}

	@Override
	public String saveDoctorSelectDept(List<SelectDept> selectDepts) throws ParseException {
		if(selectDepts!=null&&selectDepts.size()>0)
		{
			String doctorFlow = GlobalContext.getCurrentUser().getUserFlow();	//医师流水号
			ResDoctor doctor=doctorBiz.readDoctor(doctorFlow);
			doctor.setSelDeptFlag(GlobalConstant.FLAG_Y);
			doctor.setSchFlag(GlobalConstant.FLAG_Y);
			doctorBiz.editDoctor(doctor);
			Map<String,String> resultDateAreaMap = getResultCycleDate(doctorFlow);
			String startDate="";
			if(resultDateAreaMap!=null&&StringUtil.isNotBlank(resultDateAreaMap.get("max"))){
				startDate = DateUtil.addDate(resultDateAreaMap.get("max"),1);
			}else{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar   cal_1 = Calendar.getInstance();//获取当前日期
				cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
				cal_1.add(Calendar.MONTH,1);
				startDate= format.format(cal_1.getTime());
			}
			for(SelectDept selectDept:selectDepts)
			{
				SchArrangeResult result=new SchArrangeResult();
				ResDoctorSchProcess process=new ResDoctorSchProcess();

				String resultFlow = PkUtil.getUUID();

				result.setResultFlow(resultFlow);
				result.setArrangeFlow(resultFlow);

				String schDeptFlow = selectDept.getSchDeptFlow();
				SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
				if(dept!=null){
					result.setDeptFlow(dept.getDeptFlow());
					result.setDeptName(dept.getDeptName());
					result.setSchDeptName(dept.getSchDeptName());
					result.setSchDeptFlow(schDeptFlow);

					process.setDeptFlow(dept.getDeptFlow());
					process.setDeptName(dept.getDeptName());
					process.setSchDeptName(dept.getSchDeptName());
					process.setSchDeptFlow(schDeptFlow);
				}

//				String[] schDates=selectDept.getSchDate().split(",");
//				startDate=schDates[0]+"-01";
				//开始时间加1个自然月
				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(startDate);
				c.setTime(date);
				c.add(Calendar.MONTH, Integer.valueOf(selectDept.getSchMonth()));
				String endDate=DateUtil.addDate(sdf.format(c.getTime()), -1);
				result.setSchStartDate(startDate);
				result.setSchEndDate(endDate);
				process.setSchStartDate(startDate);
				process.setSchEndDate(endDate);
				process.setStartDate(startDate);
				process.setEndDate(endDate);
				SchRotationDept schRotationDept=rotationDeptBiz.readSchRotationDept(selectDept.getRecordFlow());
				result.setIsRequired(schRotationDept.getIsRequired());
				String standardDeptId = schRotationDept.getStandardDeptId();
				if(StringUtil.isNotBlank(standardDeptId)){
					String standardDeptName = DictTypeEnum.StandardDept.getDictNameById(standardDeptId);
					result.setStandardDeptName(standardDeptName);
				}
				result.setStandardDeptId(schRotationDept.getStandardDeptId());
				result.setSchMonth(selectDept.getSchMonth());
				result.setDoctorFlow(doctorFlow);
				result.setDoctorName(doctor.getDoctorName());
				result.setSessionNumber(doctor.getSessionNumber());
				result.setStandardGroupFlow(schRotationDept.getGroupFlow());
					SchRotationGroup  group=groupBiz.readSchRotationGroup(result.getStandardGroupFlow());
					if(group!=null) {
						SchRotation rotation = rotationBiz.readSchRotation(group.getRotationFlow());
						if (rotation != null) {
							result.setRotationFlow(rotation.getRotationFlow());
							result.setRotationName(rotation.getRotationName());
						} else {
							result.setRotationFlow(doctor.getRotationFlow());
							result.setRotationName(doctor.getRotationName());
						}
					}else{
						result.setRotationFlow(doctor.getRotationFlow());
						result.setRotationName(doctor.getRotationName());
					}
				result.setOrgFlow(doctor.getOrgFlow());
				result.setOrgName(doctor.getOrgName());
				process.setOrgFlow(doctor.getOrgFlow());
				process.setOrgName(doctor.getOrgName());
				process.setSchFlag(GlobalConstant.FLAG_N);
				process.setIsCurrentFlag(GlobalConstant.FLAG_N);
				process.setSchResultFlow(resultFlow);
				process.setUserFlow(doctorFlow);
				startDate= DateUtil.addDate(endDate,1);
				int re=save(result);
				//+processBiz.edit(process);
				if(re<1)
				{
					throw new RuntimeException("选科保存失败！");
				}
				//startDate=sdf.format(c.getTime());
			}
			return "选科保存成功！";
		}
		return "请选择需要轮转的科室！";
	}

	private Map<String, String> getResultCycleDate(String doctorFlow) {

		return resultExtMapper.getResultCycleDate(doctorFlow);
	}

	@Override
	public int editCustomResult(SchArrangeResult result,ResDoctorSchProcess process) throws ParseException {
		if(result==null){
			return GlobalConstant.ZERO_LINE;
		}

		if(process==null){
			return GlobalConstant.ZERO_LINE;
		}

		String schDeptFlow = result.getSchDeptFlow();
		if(StringUtil.isNotBlank(schDeptFlow)){
			SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
			if(dept!=null){
				result.setDeptFlow(dept.getDeptFlow());
				result.setDeptName(dept.getDeptName());
				result.setSchDeptName(dept.getSchDeptName());

				process.setDeptFlow(dept.getDeptFlow());
				process.setDeptName(dept.getDeptName());
				process.setSchDeptName(dept.getSchDeptName());
			}
		}

		String startDate = result.getSchStartDate();
		String endDate = result.getSchEndDate();
		process.setSchStartDate(startDate);
		process.setSchEndDate(endDate);//轮转计划单位
		String unit = InitConfig.getSysCfg("res_rotation_unit");
		//默认按月计算
		int step = 30;
		if(SchUnitEnum.Week.getId().equals(unit)){
			//如果是周按7天算/没配置或者选择月按30天
			step = 7;
			BigDecimal realMonth = BigDecimal.valueOf(0);
			long realDays = DateUtil.signDaysBetweenTowDate(result.getSchEndDate(),result.getSchStartDate())+1;
			if(realDays!=0){
				//计算实际轮转的月/周数
				double realMonthF = (realDays/(step*1.0));
				realMonth = BigDecimal.valueOf(realMonthF);
				realMonth = realMonth.setScale(1,BigDecimal.ROUND_HALF_UP);
			}
			String schMonth= String.valueOf(realMonth.doubleValue());
			result.setSchMonth(schMonth);
		}else{
			Map<String,String> map= new HashMap<>();
			map.put("startDate",result.getSchStartDate());
			map.put("endDate",result.getSchEndDate());
			Double month = TimeUtil.getMonthsBetween(map);
			String schMonth = String.valueOf(Double.parseDouble(month + ""));
			result.setSchMonth(schMonth);
		}


		String teacherUserFlow = process.getTeacherUserFlow();
		if(StringUtil.isNotBlank(teacherUserFlow)){
			SysUser user = userBiz.findByFlow(teacherUserFlow);
			if(user!=null){
				process.setTeacherUserName(user.getUserName());
			}
		}

		String headUserFlow = process.getHeadUserFlow();
		if(StringUtil.isNotBlank(headUserFlow)){
			SysUser user = userBiz.findByFlow(headUserFlow);
			if(user!=null){
				process.setHeadUserName(user.getUserName());
			}
		}

		String standardDeptId = result.getStandardDeptId();
		if(StringUtil.isNotBlank(standardDeptId)){
			String standardDeptName = DictTypeEnum.StandardDept.getDictNameById(standardDeptId);
			result.setStandardDeptName(standardDeptName);
		}

		String resultFlow = result.getResultFlow();

		if(StringUtil.isNotBlank(resultFlow)){
			ResDoctorSchProcess schProcess = processBiz.searchByResultFlow(resultFlow);
			if(schProcess!=null){
				process.setProcessFlow(schProcess.getProcessFlow());
			}else{
				String doctorFlow = result.getDoctorFlow();
				ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
				if(doctor!=null){
					process.setStartDate(process.getSchStartDate());
					process.setEndDate(process.getSchEndDate());
					process.setOrgFlow(doctor.getOrgFlow());
					process.setOrgName(doctor.getOrgName());
				}
				process.setSchFlag(GlobalConstant.FLAG_N);
				process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
				process.setSchResultFlow(resultFlow);
				process.setUserFlow(doctorFlow);
			}
			update(result);
		}else{
			resultFlow = PkUtil.getUUID();

			result.setResultFlow(resultFlow);
			result.setArrangeFlow(resultFlow);

			String doctorFlow = result.getDoctorFlow();
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			result.setDoctorFlow(doctorFlow);
			if(doctor!=null){
				result.setDoctorName(doctor.getDoctorName());
				result.setSessionNumber(doctor.getSessionNumber());
				SchRotationGroup  group=groupBiz.readSchRotationGroup(result.getStandardGroupFlow());
				if(group!=null) {
					SchRotation rotation = rotationBiz.readSchRotation(group.getRotationFlow());
					if (rotation != null) {
						result.setRotationFlow(rotation.getRotationFlow());
						result.setRotationName(rotation.getRotationName());
					} else {
						result.setRotationFlow(doctor.getRotationFlow());
						result.setRotationName(doctor.getRotationName());
					}
				}else{
					result.setRotationFlow(doctor.getRotationFlow());
					result.setRotationName(doctor.getRotationName());
				}
				result.setOrgFlow(doctor.getOrgFlow());
				result.setOrgName(doctor.getOrgName());

				//process.setStartDate(DateUtil.getCurrDate());实际开始时间取计划开始时间
				process.setStartDate(process.getSchStartDate());
				process.setEndDate(process.getSchEndDate());
				process.setOrgFlow(doctor.getOrgFlow());
				process.setOrgName(doctor.getOrgName());

				String schFlag = doctor.getSchFlag();
				if(!GlobalConstant.FLAG_Y.equals(schFlag)){
					doctor.setSchFlag(GlobalConstant.FLAG_Y);
					doctor.setSelDeptFlag(GlobalConstant.FLAG_Y);
					doctorBiz.editDoctor(doctor);
				}
			}
			process.setSchFlag(GlobalConstant.FLAG_N);
			process.setIsCurrentFlag(GlobalConstant.FLAG_N);
			process.setSchResultFlow(resultFlow);
			process.setUserFlow(doctorFlow);
			save(result);
		}
		processBiz.edit(process);

		return GlobalConstant.ONE_LINE;
	}

	@Override
	public int delResultByResultFlow(String resultFlow) {
		int result = GlobalConstant.ZERO_LINE;
		int processResult =  GlobalConstant.ZERO_LINE;
		SchArrangeResult arrangeResult = readSchArrangeResult(resultFlow);
		arrangeResult.setRecordStatus(GlobalConstant.FLAG_N);
		result = update(arrangeResult);

		ResDoctorSchProcess process = processBiz.searchByResultFlow(resultFlow);
		if(process!=null) {
			process.setRecordStatus(GlobalConstant.FLAG_N);
			processResult = processBiz.edit(process);

			ResRecExample recExample = new ResRecExample();
			com.pinde.sci.model.mo.ResRecExample.Criteria criteria =  recExample.createCriteria();
			criteria.andProcessFlowEqualTo(process.getProcessFlow());
			ResRec rec = new ResRec();
			rec.setRecordStatus(GlobalConstant.FLAG_N);
			recMapper.updateByExampleSelective(rec, recExample);
		}else{
			processResult=1;
		}

		if(result!=GlobalConstant.ZERO_LINE && processResult!=GlobalConstant.ZERO_LINE){
			return GlobalConstant.ONE_LINE;
		}else{
			return GlobalConstant.ZERO_LINE;
		}
	}

	@Override
	public int delInDept(String resultFlows) {
		int count =0;
		String [] flows = resultFlows.split(",");
		for(int i=0;i<flows.length;i++){
			count = delResultByResultFlow(flows[i]);
		}
		return count;
	}

	@Override
	public List<Map<String,Object>> searchDocCycleList(Map<String,Object> paramMap){
		return resultExtMapper.searchDocCycleList(paramMap);
	}

	@Override
	public List<Map<String,Object>> searchDocCycleBaseList(Map<String,Object> paramMap){
		return resultExtMapper.searchDocCycleBaseList(paramMap);
	}

	@Override
	public List<Map<String,Object>> searchDocResultsList(Map<String,Object> paramMap){
		return resultExtMapper.searchDocResultsList(paramMap);
	}
	@Override
	public List<Map<String,Object>> searchDocResultsListNew(Map<String,Object> paramMap){
		return resultExtMapper.searchDocResultsListNew(paramMap);
	}

	@Override
	public List<ResOutOfficeLock> searchDocErrorResultsList(Map<String,Object> paramMap) throws ParseException {
		List<ResOutOfficeLock> resOutOfficeLocks = resultExtMapper.searchDocErrorResultsList(paramMap);
		if(CollectionUtils.isNotEmpty(resOutOfficeLocks)){
			for (ResOutOfficeLock resOutOfficeLock : resOutOfficeLocks) {
				if("Passed".equals(resOutOfficeLock.getAuditStatusId())){
					String modifyTime = resOutOfficeLock.getModifyTime();
					if(StringUtil.isNotEmpty(modifyTime)){
						String currDate = DateUtil.getCurrDate();
						int days = differentDays(modifyTime, currDate)+1;
						if(days >= 5){
							resOutOfficeLock.setTimeOutStatus("已解锁(5/5)");
						}else {
							resOutOfficeLock.setTimeOutStatus("已解锁("+days+"/5)");
						}
					}
				}
			}
		}
		return resOutOfficeLocks;
	}

	@Override
	public List<ResDoctorSchProcess> searchDocErrorResultInfo(String recordFlow,String orgFlow) throws ParseException {
		//基地限制收费用户时间
		JsresPowerCfg outDayRead = jsResPowerCfgBiz.read("out_day_check_" + orgFlow);
		String outDay = "";
		if(null != outDayRead){
			outDay = outDayRead.getCfgValue();
		}
		//后台收费用户限制时间
		String outDayOne = cfgBizImpl.read("res_out_day_one_cfg").getCfgValue();
		//后台免费用户限制时间
		String outDayTwo = cfgBizImpl.read("res_out_day_two_cfg").getCfgValue();
		//查询所有出科的数据 科室 入科时间 状态
		ResOutOfficeLock resOutOfficeLock = outsOutOfficeLockMapper.selectByPrimaryKey(recordFlow);
		String userFlow = resOutOfficeLock.getUserFlow();
		//是否付费用户
		String isChargeOrg = "Y";
		JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_doctor_app_menu_"+userFlow);
		if (null == cfg || GlobalConstant.FLAG_N.equals(cfg.getCfgValue())){
			isChargeOrg = "N";
		}
		List<ResDoctorSchProcess> resDoctorSchProcesses = processBiz.searchProcessByDoctor(userFlow);
		List<ResDoctorSchProcess> outTimeProcesses = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(resDoctorSchProcesses)){
			if(isChargeOrg.equals("Y")){
				for (ResDoctorSchProcess resDoctorSchProcess : resDoctorSchProcesses) {
					if(StringUtil.isNotEmpty(resDoctorSchProcess.getOutDate())){
						//找超时的科室信息 计算超时时间 当前时间 - 出科时间
						String outDate = resDoctorSchProcess.getOutDate();
						String currDate = DateUtil.getCurrDate();
						int days = differentDays(outDate, currDate);
						resDoctorSchProcess.setMonthDate(days + "");
						outTimeProcesses.add(resDoctorSchProcess);
//						if (StringUtil.isNotEmpty(outDay) && !"-1".equals(outDay)) {
//							//超时
//							if (days > Integer.parseInt(outDay)) {
//								int outTime = days - Integer.parseInt(outDay);
//								//借用字段存超时天数
//								resDoctorSchProcess.setMonthDate(outTime + "");
//								outTimeProcesses.add(resDoctorSchProcess);
//							}
//						} else {
//							if (StringUtil.isNotEmpty(outDayOne) && !"-1".equals(outDayOne)) {
//								//超时
//								if (days > Integer.parseInt(outDayOne)) {
//									int outTime = days - Integer.parseInt(outDayOne);
//									//借用字段存超时天数
//									resDoctorSchProcess.setMonthDate(outTime + "");
//									outTimeProcesses.add(resDoctorSchProcess);
//								}
//							}
//						}
					}
				}
			}else{
				for (ResDoctorSchProcess resDoctorSchProcess : resDoctorSchProcesses) {
					//找超时的科室信息 计算超时时间 当前时间 - 出科时间
					if(StringUtil.isNotEmpty(resDoctorSchProcess.getSchEndDate())){
						String outDate = resDoctorSchProcess.getSchEndDate();
						String currDate = DateUtil.getCurrDate();
						int days = differentDays(outDate, currDate);
						if (StringUtil.isNotEmpty(outDayTwo) && !"-1".equals(outDayTwo)) {
							//超时
							if (days > Integer.parseInt(outDayTwo)) {
								int outTime = days - Integer.parseInt(outDayTwo);
								//借用字段存超时天数
								resDoctorSchProcess.setMonthDate(outTime + "");
								resDoctorSchProcess.setOutDate(resDoctorSchProcess.getSchEndDate());
								outTimeProcesses.add(resDoctorSchProcess);
							}
						}
					}
				}
			}
		}
		return outTimeProcesses;
	}

	@Override
	public int saveAuditLockInfo(ResOutOfficeLock outOfficeLock) {
		return outsOutOfficeLockMapper.updateByPrimaryKeySelective(outOfficeLock);
	}

	@Override
	public List<Map<String, Object>> docWorkingSearch(Map<String, Object> paraMp) {
		return docSchProcessExtMapper.docWorkingSearch(paraMp);
	}

	@Override
	public List<Map<String, Object>> docWorkingDetail(Map<String, Object> paraMp) {
		return docSchProcessExtMapper.docWorkingDetail(paraMp);
	}

	@Override
	public List<Map<String, Object>> newDocWorkingDetail(Map<String, Object> parMp) {
		return docSchProcessExtMapper.newDocWorkingDetail(parMp);
	}

	@Override
	public List<Map<String, Object>> searchNewDocCycleList(Map<String, Object> paramMap) {
		return docSchProcessExtMapper.searchDocCycleList(paramMap);
	}

	@Override
	public List<Map<String, Object>> exportCkScore(Map<String, Object> paramMap) {
		return docSchProcessExtMapper.exportCkScore(paramMap);
	}

	@Override
	public Map<String, Object> deptWorkingDetail(Map<String, Object> map) {
		return docSchProcessExtMapper.deptWorkingDetail( map);
	}

	@Override
	public List<SchArrangeResult> searchSchArrangeResultByDoctorAndRotationFlow(String doctorFlow, String rotationFlow) {

		SchArrangeResultExample example = new SchArrangeResultExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow)
				.andRotationFlowEqualTo(rotationFlow);
		example.setOrderByClause("SCH_DEPT_ORDER,SCH_START_DATE");
		return arrangeResultMapper.selectByExample(example);
	}
	@Override
	public List<SchArrangeResult> searchSchArrangeResultIsBByDoctorAndRotationFlow(String doctorFlow, String rotationFlow) {

		SchArrangeResultExample example = new SchArrangeResultExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow)
				.andRotationFlowEqualTo(rotationFlow).andIsStepBEqualTo(GlobalConstant.FLAG_Y);
		example.setOrderByClause("SCH_DEPT_ORDER,SCH_START_DATE");
		return arrangeResultMapper.selectByExample(example);
	}

	@Override
	public List<SchArrangeResult> searchSchArrangeResultByDoctorAndRotationFlowAndStandardId(String doctorFlow, String rotationFlow,String standardDeptId){
		SchArrangeResultExample example = new SchArrangeResultExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow)
				.andRotationFlowEqualTo(rotationFlow).andStandardDeptIdEqualTo(standardDeptId);
		example.setOrderByClause("SCH_DEPT_ORDER,SCH_START_DATE");
		return arrangeResultMapper.selectByExample(example);

	}


	@Override
	public JsresDoctorDeptDetail deptDoctorWorkDetail(String recordFlow, String rotationFlow, String doctorFlow) {
		JsresDoctorDeptDetailExample example=new JsresDoctorDeptDetailExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSchStandardDeptFlowEqualTo(recordFlow)
				.andDoctorFlowEqualTo(doctorFlow).andRotationFlowEqualTo(rotationFlow);
		List<JsresDoctorDeptDetail> list=doctorDeptDetailMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}
	@Override
	public List<JsresDoctorDeptDetail> deptDoctorAllWorkDetail(String rotationFlow, String doctorFlow, String applyYear) {
		JsresDoctorDeptDetailExample example=new JsresDoctorDeptDetailExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow).andRotationFlowEqualTo(rotationFlow).andApplyYearEqualTo(applyYear);
		List<JsresDoctorDeptDetail> list=doctorDeptDetailMapper.selectByExample(example);
		return list;
	}

	@Override
	public void initDocResult(ResDoctor doctor, List<SchRotationDept> schRotationDepts) {
			//将方案中不存在的排班记录删除
			Map<String,String> param=new HashMap<>();
			param.put("secondRotationFlow",doctor.getSecondRotationFlow());
			param.put("rotationFlow",doctor.getRotationFlow());
			param.put("orgFlow",doctor.getOrgFlow());
			param.put("sessionNumber",doctor.getSessionNumber());
			param.put("doctorFlow",doctor.getDoctorFlow());
			resultExtMapper.deleteResultNotInRotation(param);
		int index = 0;
		if(schRotationDepts!=null&&schRotationDepts.size()>0)
		{
			for(SchRotationDept dept:schRotationDepts)
			{
				param = new HashMap<>();
				param.put("orgFlow", doctor.getOrgFlow());
				param.put("sessionNumber", doctor.getSessionNumber());
				param.put("doctorFlow", doctor.getDoctorFlow());
				param.put("rotationFlow", dept.getRotationFlow());
				param.put("schDeptFlow", dept.getSchDeptFlow());
				param.put("standardDeptId", dept.getStandardDeptId());
				SchRotationGroup group = groupBiz.readSchRotationGroup(dept.getGroupFlow());
				param.put("standardGroupFlow", group.getStandardGroupFlow());
				SchArrangeResult result = getResultBySchRotationDept(param);
				boolean isNew=true;
				if(result!=null)
				{
					isNew=false;
				}else{
					result=new SchArrangeResult();
					String resultFlow=PkUtil.getUUID();
					result.setResultFlow(resultFlow);
					result.setArrangeFlow(resultFlow);
					result.setSchMonth(dept.getSchMonth());
					result.setSchDeptFlow(dept.getSchDeptFlow());
					result.setSchDeptName(dept.getSchDeptName());
					result.setDeptFlow(dept.getDeptFlow());
					result.setDeptName(dept.getDeptName());
					result.setOrgFlow(doctor.getOrgFlow());
					result.setOrgName(doctor.getOrgName());
					result.setSessionNumber(doctor.getSessionNumber());
					result.setGroupFlow(dept.getGroupFlow());
					result.setStandardGroupFlow(group.getStandardGroupFlow());
					result.setStandardDeptId(dept.getStandardDeptId());
					result.setStandardDeptName(dept.getStandardDeptName());
					result.setSchYear(doctor.getSessionNumber());
					result.setIsRequired(dept.getIsRequired());
					String rotationFlow = dept.getRotationFlow();
					result.setRotationFlow(rotationFlow);
					if(rotationFlow.equals(doctor.getRotationFlow())){
						result.setRotationName(doctor.getRotationName());
					}else if(rotationFlow.equals(doctor.getSecondRotationFlow())){
						result.setRotationName(doctor.getSecondRotationName());
					}
					result.setIsRotation(GlobalConstant.FLAG_Y);
					result.setDoctorFlow(doctor.getDoctorFlow());
					result.setDoctorName(doctor.getDoctorName());
				}
				if(!GlobalConstant.RECORD_STATUS_D.equals(result.getRecordStatus())){
					result.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				}
				result.setSchDeptOrder(new BigDecimal(index++));
				if(isNew)
				{
					save(result);
				}else{
					update(result);
				}
			}
		}
	}

	@Override
	public int synchronizeProcessByResults(List<SchArrangeResult> resultList) {
		int result = 0;
		if (resultList != null && resultList.size() > 0) {
			for (SchArrangeResult schResult : resultList) {
				String resultFlow = schResult.getResultFlow();
				ResDoctorSchProcess process = processBiz.searchByResultFlow(resultFlow);
				if (process != null) {
					process.setSchStartDate(schResult.getSchStartDate());
					process.setSchEndDate(schResult.getSchEndDate());
					result += doctorSchProcessMapper.updateByPrimaryKeySelective(process);
				}
			}
			return result;
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public SchArrangeResult getResultBySchRotationDept(Map<String, String> param) {
		List<SchArrangeResult> list=resultExtMapper.getResultBySchRotationDept(param);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SchArrangeResult> searchSchArrangeResultBySpeAndDoc(Map<String, Object> paramMap) {
		return resultExtMapper.searchSchArrangeResultBySpeAndDoc(paramMap);
	}

	@Override
	public Map<String, Object> doctorDeptAvgWorkDetail(String recruitFlow, String applyYear) {
		return docSchProcessExtMapper.doctorDeptAvgWorkDetail(recruitFlow,applyYear);
	}

	@Override
	public List<SchArrangeResult> searchNewCycleArrangeResults(Map<String, Object> paramMap) {
		return docSchProcessExtMapper.searchCycleArrangeResults(paramMap);
	}

	/****************************高******校******管******理******员******角******色************************************************/

	@Override
	public List<Map<String,Object>> searchDocCycleListForUni(Map<String,Object> paramMap){
		if(paramMap.get("workOrgId")==null&&paramMap.get("workOrgName")==null){
			return null;
		}
		return resultExtMapper.searchDocCycleListForUni(paramMap);
	}
	@Override
	public List<Map<String,Object>> searchDocResultsListForUni(Map<String,Object> paramMap){
		if(paramMap.get("workOrgId")==null&&paramMap.get("workOrgName")==null){
			return null;
		}
		return resultExtMapper.searchDocResultsListForUni(paramMap);
	}

	@Override
	public List<SchArrangeResult> searchCycleArrangeResultsForUni(Map<String,Object> paramMap){
		if(paramMap.get("workOrgId")==null&&paramMap.get("workOrgName")==null){
			return null;
		}
		return resultMapper.searchCycleArrangeResultsForUni(paramMap);
	}

	@Override
	public List<SchArrangeResult> searchCycleArrangeResultsForUniTwo(Map<String,Object> paramMap){
		if(paramMap.get("workOrgId")==null&&paramMap.get("workOrgName")==null){
			return null;
		}
		return resultMapper.searchCycleArrangeResultsForUniTwo(paramMap);
	}

	@Override
	public List<Map<String, Object>> docWorkingSearchForUni(Map<String, Object> paraMp) {
		if(paraMp.get("workOrgId")==null&&paraMp.get("workOrgName")==null){
			return null;
		}
		return docSchProcessExtMapper.docWorkingSearchForUni(paraMp);
	}
	@Override
	public List<Map<String, Object>> orgTeaAuditInfoForUni(Map<String, Object> paraMp) {
		if(paraMp.get("workOrgId")==null&&paraMp.get("workOrgName")==null){
			return null;
		}
		return docSchProcessExtMapper.orgTeaAuditInfoForUni(paraMp);
	}

	/**
	 * @param parMp
	 * @Department：研发部
	 * @Description 责任导师查询学员工作量
	 * @Author Zjie
	 * @Date 0014, 2021年1月14日
	 */
	@Override
	public List<Map<String, Object>> docWorkingSearchNew(Map<String, Object> parMp) {
		return docSchProcessExtMapper.docWorkingSearchNew(parMp);
	}

	/**
	 * @param paramMap
	 * @Department：研发部
	 * @Description 医师轮转培训查询
	 * @Author Zjie
	 * @Date 0015, 2021年1月14日
	 */
	@Override
	public List<Map<String, Object>> searchDocCycleListNew(Map<String, Object> paramMap) {
		return docSchProcessExtMapper.searchDocCycleListNew(paramMap);
	}

	/**
	 * @param rotationFlow
	 * @param doctorFlow
	 * @Department：研发部
	 * @Description 查询学员轮转月数
	 * @Author fengxf
	 * @Date 2021/4/13
	 */
	@Override
	public float getDoctorSchMonthSumInfo(String rotationFlow, String doctorFlow) {
		return resultExtMapper.getDoctorSchMonthSumInfo(rotationFlow, doctorFlow);
	}

	public int differentDays(String date1,String date2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date3 = sdf.parse(date1);
		Date date4 = sdf.parse(date2);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date3);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date4);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) {//同一年
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
				{
					timeDistance += 366;
				} else    //不是闰年
				{
					timeDistance += 365;
				}
			}

			return timeDistance + (day2 - day1);
		} else {// 不同年
			return day2 - day1;
		}
	}


	@Override
	public List<Map<String, Object>> schedulingSearchDeptList(String orgFlow,String firstDayOfMonth,String lastDayOfMonth, String deptFlow) {
		return resultExtMapper.schedulingSearchDeptList(orgFlow,firstDayOfMonth,lastDayOfMonth,deptFlow);
	}

	@Override
	public List<Map<String, Object>> schedulingSearchDeptList2(String orgFlow,String firstDayOfMonth,String lastDayOfMonth, String deptFlow) {
		return resultExtMapper.schedulingSearchDeptList2(orgFlow,firstDayOfMonth,lastDayOfMonth,deptFlow);
	}

	@Override
	public List<Map<String, Object>> schedulingSearchDeptUserList(String orgFlow, String firstDayOfMonth,String lastDayOfMonth, String deptFlow,String trainingTypeId) {
		return resultExtMapper.schedulingSearchDeptUserList(orgFlow,firstDayOfMonth,lastDayOfMonth,deptFlow,trainingTypeId);
	}

	@Override
	public List<Map<String, Object>> schedulingSearchDoctorList(Map<String, Object> paramMap) {
		return resultExtMapper.schedulingSearchDoctorList(paramMap);
	}

	@Override
	public List<Map<String, Object>> schedulingSearchAuditList(Map<String, Object> paramMap) {
		return resultExtMapper.schedulingSearchAuditList(paramMap);
	}

	@Override
	public Map<String,Object> importSchedulingAuditExcel(MultipartFile file,String rotationFlow,String trainingTypeId) throws Exception {
		InputStream is = file.getInputStream();
		byte[] fileData = new byte[(int) file.getSize()];
		is.read(fileData);
		Workbook wb =  createUserWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
		return parseExcelToScheduing2(wb,rotationFlow,trainingTypeId);
	}



	private static Workbook createUserWorkbook(InputStream inS) throws IOException, InvalidFormatException {
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

	private static String _doubleTrans(double d){
		if((double)Math.round(d) - d == 0.0D){
			return String.valueOf((long)d);
		}else{
			return String.valueOf(d);
		}
	}

	// 读取数据
	private int parseExcelToScheduing(Workbook wb,String rotationFlow) throws ParseException {
		int succCount = 0;   // 导入成功条数

		HashMap<String, SchRotationDept> deptMaps = new HashMap<>();
		List<SchRotationDept> rotationDepts = rotationDeptBiz.searchSchRotationDept(rotationFlow);
		for (SchRotationDept dept : rotationDepts) {
			deptMaps.put(dept.getStandardDeptName(),dept);
		}
		int sheetNum = wb.getNumberOfSheets();
		if (sheetNum > 0) {
			Sheet sheet = wb.getSheetAt(0);
			int row_num = sheet.getLastRowNum();//获取sheet行数，索引0开始
			if (row_num < 1) {
				throw new RuntimeException("没有数据！");
			}
			Row titleR = sheet.getRow(0);//获取表头
			int cell_num = titleR.getLastCellNum();//获取表头单元格数
			List<String> colnames = new ArrayList<>();
			for (int i = 0; i < cell_num; i++) {
				colnames.add(titleR.getCell(i).getStringCellValue());
			}
			//数据行开始遍历
			for (int i = 1; i <= row_num; i++) {
				Row r = sheet.getRow(i);
				String value = "";
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < colnames.size(); j++) {
					Cell cell = r.getCell(j);
					if (null == cell || com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(cell.toString().trim())) {
						continue;
					}
					if (r.getCell((short) j).getCellType() == 1) {
						value = r.getCell((short) j).getStringCellValue();
					} else {
						value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
					}
					if ("学员姓名".equals(colnames.get(j))) {
						map.put("doctorName", value);
					} else if ("身份证号".equals(colnames.get(j))) {
						map.put("idNo", value);
					}else if ("标准科室".equals(colnames.get(j))) {
						map.put("deptName", value);
					}  else if ("轮转科室(科室[基地名称])".equals(colnames.get(j))) {
						map.put("schDeptName", value);
					} else if ("开始时间(yyyyMMdd)".equals(colnames.get(j))) {
						map.put("schStartDate", value);
					} else if ("结束时间(yyyyMMdd)".equals(colnames.get(j))) {
						map.put("schEndDate", value);
					} else if (("科主任".equals(colnames.get(j)))){
						map.put("headName",value);
					} else if (("带教老师".equals(colnames.get(j)))){
						map.put("teacherName",value);
					}
				}
				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("doctorName")))) {
					throw new RuntimeException("导入失败！第" + i + "行，学员姓名为空！");
				}
				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("idNo")))) {
					throw new RuntimeException("导入失败！第" + i + "行，身份证号为空！");
				}
				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("deptName")))) {
					throw new RuntimeException("导入失败！第" + i + "行，标准科室为空！");
				}
				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("schDeptName")))) {
					throw new RuntimeException("导入失败！第" + i + "行，轮转科室为空！");
				}
				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("schStartDate")))) {
					throw new RuntimeException("导入失败！第" + i + "行，开始时间为空！");
				}
				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("schEndDate")))) {
					throw new RuntimeException("导入失败！第" + i + "行，结束时间为空！");
				}
				if (map.get("schStartDate").toString().compareTo(map.get("schEndDate").toString())>0){
					throw new RuntimeException("导入失败！第" + i + "行，轮转开始时间必须早于结束时间！");
				}
				//禅道3299 科主任带教老师非必填
//				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("headName")))) {
//					throw new RuntimeException("导入失败！第" + i + "行，科主任为空！");
//				}
//				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("teacherName")))) {
//					throw new RuntimeException("导入失败！第" + i + "行，带教老师为空！");
//				}
				SysUser user = userBiz.findByIdNo(map.get("idNo").toString());
				if (null == user){
					throw new RuntimeException("导入失败！第" + i + "行，查询的学员信息为空！");
				}
				ResDoctor doctor = doctorBiz.readDoctor(user.getUserFlow());
				ResDoctorRecruitExample example = new ResDoctorRecruitExample();
				example.createCriteria().andRecordStatusEqualTo("Y").andAuditStatusIdEqualTo("Passed").andDoctorFlowEqualTo(doctor.getDoctorFlow());
				example.setOrderByClause("CREATE_TIME DESC");
				List<ResDoctorRecruit> recruitList = resDoctorRecruitMapper.selectByExample(example);
				if (null== recruitList ||  recruitList.size()==0){
					throw new RuntimeException("导入失败！第" + i + "行，系统为分配该学员的培训方案！");
				}
				if (!rotationFlow.equals(recruitList.get(0).getRotationFlow())){
					throw new RuntimeException("导入失败！第" + i + "行，该学员的培训方案不是导入的方案！");
				}

				String orgFlow = "";
				if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
					orgFlow = doctor.getSecondOrgFlow();
				} else {
					orgFlow = doctor.getOrgFlow();
				}
				SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
				List<SysDept> schDeptList = searchSysDeptList(orgFlow, "");
				HashMap<String, SysDept> schDeptMap = new HashMap<>();
				for (SysDept dept : schDeptList) {
					schDeptMap.put(dept.getDeptName(),dept);
				}


				//检测时间是否重叠

				String startDate = DateUtil.transDate(map.get("schStartDate").toString());;
				String endDate = DateUtil.transDate(map.get("schEndDate").toString());
				String headName = "";
				String teacherName = "";
				if(null != map.get("headName")){
					headName = map.get("headName").toString();
				}
				if(null != map.get("teacherName")){
					teacherName = map.get("teacherName").toString();
				}
				String schDeptName = map.get("schDeptName").toString();
				if (schDeptName.contains(sysOrg.getOrgName())){
					schDeptName=schDeptName.substring(0,schDeptName.indexOf("["));
				}
				SysDept sysDept = schDeptMap.get(schDeptName);
				SchRotationDept dept = deptMaps.get(map.get("deptName").toString());
				List<SchArrangeResult> resultList = checkResultDate(doctor.getDoctorFlow(), startDate, endDate, null, doctor.getRotationFlow());
				if (null!=resultList && resultList.size()>0){
					ArrayList<String> list = new ArrayList<>();
					for (SchArrangeResult result : resultList) {
						list.add(result.getResultFlow());
					}
					//基地导入会覆盖之前的数据
					resultExtMapper.updateSchArrangeResultToDel(list);
					resultExtMapper.updateResDoctorSchProcessToDel(list);
				}
				SysCfg jsres_is_process = cfgBiz.read("jsres_is_process");
				if (null != jsres_is_process && GlobalConstant.RECORD_STATUS_Y.equals(jsres_is_process.getCfgValue())){
					// 获取当前入科时间填写系统限制
					CheckRotationTime(doctor.getDoctorFlow(), dept.getRecordFlow(), startDate, endDate, "1", "", dept.getSchMonth(), i);
				}

				//获取当前配置的科主任角色
				SysUser head = null;
				String headRole = cfgBiz.read("res_head_role_flow").getCfgValue();
				if(StringUtil.isNotEmpty(headName)){
					head = searchTeacherList(sysDept.getDeptFlow(), headRole, headName);
					if (null==head){
						throw new RuntimeException("导入失败！第" + i + "行，科主任信息错误！");
					}
				}
				SysUser teacher = null;
				if(StringUtil.isNotEmpty(teacherName)){
					String teacherRole = cfgBiz.read("res_teacher_role_flow").getCfgValue();
					teacher = searchTeacherList(sysDept.getDeptFlow(), teacherRole, teacherName);
					if (null==teacher){
						throw new RuntimeException("导入失败！第" + i + "行，带教老师信息错误！");
					}
				}
				SysOrg org=orgBiz.readSysOrg(orgFlow);
				SchArrangeResult result = new SchArrangeResult();
				result.setResultFlow(PkUtil.getUUID());
				result.setArrangeFlow(result.getResultFlow());
				result.setDoctorFlow(user.getUserFlow());
				result.setSessionNumber(doctor.getSessionNumber());
				result.setDoctorName(doctor.getDoctorName());
				result.setOrgFlow(orgFlow);
				result.setOrgName(org.getOrgName());
				result.setSchStartDate(startDate);
				result.setSchEndDate(endDate);
				result.setRotationFlow(dept.getRotationFlow());

				result.setDeptFlow(sysDept.getDeptFlow());
				result.setDeptName(sysDept.getDeptName());
				result.setSchDeptFlow(sysDept.getDeptFlow());

				String sysDeptName = sysDept.getDeptName();
				String deptOrgFlow = sysDept.getOrgFlow();
				if(StringUtil.isNotBlank(deptOrgFlow)){
					SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
					if(so!=null && !so.getOrgFlow().equals(orgFlow)){
						sysDeptName+=("["+so.getOrgName()+"]");
					}
				}

				result.setSchDeptName(sysDeptName);
				result.setStandardDeptId(dept.getStandardDeptId());
				result.setStandardDeptName(dept.getStandardDeptName());
				result.setStandardGroupFlow(dept.getGroupFlow());
				result.setIsRequired(dept.getIsRequired());
				result.setCreateTime(DateUtil.getCurrDateTime());
				result.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				result.setModifyTime(DateUtil.getCurrDateTime());
				result.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				result.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				Map<String,String> mapTime= new HashMap<>();
				mapTime.put("startDate",result.getSchStartDate());
				mapTime.put("endDate",result.getSchEndDate());
				Double month = TimeUtil.getMonthsBetween(mapTime);
				String schMonth = String.valueOf(Double.parseDouble(month+""));
				result.setSchMonth(schMonth);
				result.setBaseAudit("Passed");
				schArrangeResultMapper.insert(result);


				ResDoctorSchProcess process = new ResDoctorSchProcess();
				process.setProcessFlow(PkUtil.getUUID());
				process.setUserFlow(user.getUserFlow());
				process.setOrgFlow(org.getOrgFlow());
				process.setOrgName(org.getOrgName());
				process.setDeptFlow(sysDept.getDeptFlow());
				process.setDeptName(sysDept.getDeptName());
				process.setSchDeptFlow(sysDept.getDeptFlow());
				process.setSchDeptName(sysDept.getDeptName());
				process.setSchResultFlow(result.getResultFlow());
				process.setSchStartDate(startDate);
				process.setSchEndDate(endDate);
				if(null != teacher){
					process.setTeacherUserFlow(teacher.getUserFlow());
					process.setTeacherUserName(teacher.getUserName());
				}
				if(null != teacher){
					process.setHeadUserFlow(head.getUserFlow());
					process.setHeadUserName(head.getUserName());
				}
				process.setCreateTime(DateUtil.getCurrDateTime());
				process.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				process.setModifyTime(DateUtil.getCurrDateTime());
				process.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				process.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);

				doctorSchProcessMapper.insertSelective(process);
				ResSchProcessExpressExample proExample = new ResSchProcessExpressExample();
				proExample.createCriteria().andOperUserFlowEqualTo(user.getUserFlow()).andSchRotationDeptFlowEqualTo(dept.getRecordFlow())
						.andRecTypeIdEqualTo(ResRecTypeEnum.AfterSummary.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
				List<ResSchProcessExpress> recList = schProcessExpressMapper.selectByExampleWithBLOBs(proExample);
				String recContent="";
				if(recList!=null&&recList.size()>0)
				{
					recContent=recList.get(0).getRecContent();
				}
				try {
					updateResultHaveAfter2(dept.getRecordFlow(),user.getUserFlow(),recContent);
				} catch (DocumentException e) {
					e.printStackTrace();
				}
				succCount++;
			}
		}
		return succCount;
	}

	// 读取数据
	private Map<String,Object> parseExcelToScheduing2(Workbook wb,String rotationFlow,String trainingTypeId) throws ParseException {
		logger.info("开始进入排班！！！！！！！");
		int succCount = 0;   // 导入成功条数
		List<String> titles =new ArrayList<>();
		String msg = "";
		Map<String,Object> resultMap = new HashMap<>();
//		SchRotation rotation = rotationBiz.readSchRotation(rotationFlow);
		//生成当前年月往后推三年
		int number = 3 * 12;
		Calendar calendar = Calendar.getInstance();
		// 创建SimpleDateFormat对象，用于格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		// 循环输出当前年月往后12个月的具体年月
		for (int i = 0; i < number; i++) {
			// 将当前年月格式化为yyyy-MM格式
			String yearMonth = sdf.format(new Date(calendar.getTimeInMillis()));
			titles.add(yearMonth);
			// 将年月递增一个月
			calendar.add(Calendar.MONTH, 1);
		}
		int sheetNum = wb.getNumberOfSheets();
		if (sheetNum > 0) {
			Sheet sheet = wb.getSheetAt(0);
			int row_num = sheet.getLastRowNum();//获取sheet行数，索引0开始
			if (row_num < 1) {
				throw new RuntimeException("没有数据！");
			}
			Row titleR = sheet.getRow(0);//获取表头
			int cell_num = titleR.getLastCellNum();//获取表头单元格数
			List<String> colnames = new ArrayList<>();
			for (int i = 0; i < cell_num; i++) {
				colnames.add(titleR.getCell(i).getStringCellValue());
			}
			//数据行开始遍历
			for (int i = 1; i <= row_num; i++) {
				Row r = sheet.getRow(i);
				String value = "";
				Map<String, Object> map = new HashMap<>();
				List<Map<String, Object>> deptList = new ArrayList<>();
				for (int j = 0; j < colnames.size(); j++) {
					Cell cell = r.getCell(j);
					if (null == cell || com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(cell.toString().trim())) {
						continue;
					}
					if (r.getCell((short) j).getCellType() == 1) {
						value = r.getCell((short) j).getStringCellValue();
					} else {
						value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
					}
					if ("学员姓名".equals(colnames.get(j))) {
						map.put("doctorName", value);
					} else if ("身份证号".equals(colnames.get(j))) {
						map.put("idNo", value);
					} else if ("专业".equals(colnames.get(j))) {
						map.put("speName", value);
					} else if ("年级".equals(colnames.get(j))) {
						map.put("sessionNumber", value);
					} else if ("年限".equals(colnames.get(j))) {
						map.put("trainingYears", value);
					}
					if(CollectionUtils.isNotEmpty(titles) && StringUtil.isNotEmpty(value)){
						for (String title : titles) {
							if(title.equals(colnames.get(j))){
								Map<String, Object> deptMap = new HashMap<>();
								deptMap.put("schDeptName", value);
								// 开始日期为当前月的第一天
								deptMap.put("schStartDate", title+"-01");
								// 结束日期为当前月的最后一天
								String[] split = title.split("-");
								LocalDate lastDay = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 1).with(TemporalAdjusters.lastDayOfMonth());
								deptMap.put("schEndDate", title+"-"+lastDay.getDayOfMonth());
								//根据轮转科室查标准科室 SCH_AND_STANDARD_DEPT_CFG
								logger.info("查询学员信息！！！！！！！");
								SysUser user = userBiz.findByIdNo(map.get("idNo").toString());
								if (null == user){
									continue;
								}
								ResDoctor doctor = doctorBiz.readDoctor(user.getUserFlow());
							/*	if (StringUtil.isEmpty(doctor.getTrainingTypeId()) || !doctor.getTrainingTypeId().equals(trainingTypeId)){
									if (trainingTypeId.equals(TrainCategoryEnum.DoctorTrainingSpe.getId())){
										msg = msg+"第" + i + "行导入失败！该学员不是住院医师！<br>";
									}else {
										msg = msg+"第" + i + "行导入失败！该学员不是助理全科！<br>";
									}
									continue;
								}*/
								String orgFlow = "";
								if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
									orgFlow = doctor.getSecondOrgFlow();
								} else {
									orgFlow = doctor.getOrgFlow();
								}
								logger.info("查询学员科室信息！！！！！！！");
								List<String> deptParamList = sysDeptExtMapper.selectDeptByParamList(orgFlow, value);
								if (null != deptParamList && deptParamList.size()>0){
									if (deptParamList.size()>1){
										msg = msg+"第" + i + "行导入失败！轮转科室名称重复！<br>";
										continue;
									}
									if (deptParamList.size()==1){
										deptMap.put("deptName", deptParamList.get(0));
										deptList.add(deptMap);
									}
								}
							/*	String deptName = sysDeptExtMapper.selectDeptByParam(orgFlow, value);
								deptMap.put("deptName", deptName);
								deptList.add(deptMap);*/
							}
						}
					}
				}
				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("doctorName")))) {
					msg = msg+"第" + i + "行导入失败！学员姓名为空！<br>";
					continue;
				}
				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("idNo")))) {
					msg = msg+"第" + i + "行导入失败！身份证号为空！<br>";
					continue;
				}
				if (map.size()==0){
					continue;
				}
				SysUser user = userBiz.findByIdNo(map.get("idNo").toString());
				if (null == user){
					msg = msg+"第" + i + "行导入失败！查询的学员信息为空！<br>";
					continue;
				}
				logger.info("查询学员医师信息！！！！！！！");
				ResDoctor doctor = doctorBiz.readDoctor(user.getUserFlow());
				ResDoctorRecruitExample example = new ResDoctorRecruitExample();
				example.createCriteria().andRecordStatusEqualTo("Y").andAuditStatusIdEqualTo("Passed").andDoctorFlowEqualTo(doctor.getDoctorFlow());
				example.setOrderByClause("CREATE_TIME DESC");
				logger.info("查询学员志愿信息！！！！！！！");
				List<ResDoctorRecruit> recruitList = resDoctorRecruitMapper.selectByExample(example);
				if (null== recruitList ||  recruitList.size()==0){
					msg = msg+"第" + i + "行导入失败！系统未分配该学员的培训方案！<br>";
					continue;
				}
				if (StringUtil.isNotEmpty(rotationFlow) && !rotationFlow.equals(recruitList.get(0).getRotationFlow())){
					msg = msg+"第" + i + "行导入失败！该学员的培训方案不是导入的方案！<br>";
					continue;
				}
				HashMap<String, SchRotationDept> deptMaps = new HashMap<>();
				logger.info("查询学员轮转信息！！！！！！！");
				List<SchRotationDept> rotationDepts = rotationDeptBiz.searchSchRotationDept(recruitList.get(0).getRotationFlow());
				for (SchRotationDept dept : rotationDepts) {
					deptMaps.put(dept.getStandardDeptName(),dept);
				}

				String orgFlow = "";
				if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
					orgFlow = doctor.getSecondOrgFlow();
				} else {
					orgFlow = doctor.getOrgFlow();
				}
				SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
				List<SysDept> schDeptList = searchSysDeptList(orgFlow, "");
				HashMap<String, SysDept> schDeptMap = new HashMap<>();
				for (SysDept dept : schDeptList) {
					schDeptMap.put(dept.getDeptName(),dept);
				}
				if(CollectionUtils.isNotEmpty(deptList)){
					boolean checkSuccess = false;
					for (Map<String, Object> stringObjectMap : deptList) {
						if(null == stringObjectMap.get("deptName")){
							msg = msg+"第" + i + "行轮转科室导入失败！该培训方案内未找到对应标准科室！<br>";
							continue;
						}
						//检测时间是否重叠
						String startDate = DateUtil.transDate(stringObjectMap.get("schStartDate").toString());;
						String endDate = DateUtil.transDate(stringObjectMap.get("schEndDate").toString());
						String schDeptName = stringObjectMap.get("schDeptName").toString();
						if (schDeptName.contains(sysOrg.getOrgName())){
							schDeptName=schDeptName.substring(0,schDeptName.indexOf("["));
						}
						SysDept sysDept = schDeptMap.get(schDeptName);
						if (null==sysDept){
							msg = msg+"第" + i + "行-"+schDeptName+"  轮转科室导入失败！该培训方案内未找到对应轮转科室！<br>";
							continue;
						}
						SchRotationDept dept = deptMaps.get(stringObjectMap.get("deptName").toString());
						if (null == dept){
							msg = msg+"第" + i + "行-"+schDeptName+"  轮转科室导入失败！该培训方案内未找到对应标准科室！<br>";
							continue;
						}
						List<SchArrangeResult> resultList = checkResultDate(doctor.getDoctorFlow(), startDate, endDate, null, doctor.getRotationFlow());
						if (null != resultList && resultList.size() > 0) {
							ArrayList<String> list = new ArrayList<>();
							for (SchArrangeResult result : resultList) {
								list.add(result.getResultFlow());
							}
							//基地导入会覆盖之前的数据
							resultExtMapper.updateSchArrangeResultToDel(list);
							resultExtMapper.updateResDoctorSchProcessToDel(list);
						}
						SysCfg jsres_is_process = cfgBiz.read("jsres_is_process");

						if (null != jsres_is_process && !GlobalConstant.RECORD_STATUS_Y.equals(jsres_is_process.getCfgValue())) {
							// 获取当前入科时间填写系统限制
							CheckRotationTime(doctor.getDoctorFlow(), dept.getRecordFlow(), startDate, endDate, "1", "", dept.getSchMonth(), i);
						}

						logger.info("查询result信息！！！！！！！");
						SysOrg org = orgBiz.readSysOrg(orgFlow);
						SchArrangeResult result = new SchArrangeResult();
						result.setResultFlow(PkUtil.getUUID());
						result.setArrangeFlow(result.getResultFlow());
						result.setDoctorFlow(user.getUserFlow());
						result.setSessionNumber(map.get("sessionNumber").toString());
						result.setDoctorName(doctor.getDoctorName());
						result.setOrgFlow(orgFlow);
						result.setOrgName(org.getOrgName());
						result.setSchStartDate(startDate);
						result.setSchEndDate(endDate);
						result.setRotationFlow(dept.getRotationFlow());

						logger.info("查询sysDept信息！！！！！！！");
						logger.info("查询sysDept具体信息！！！！！！！"+sysDept.getDeptFlow());
						result.setDeptFlow(sysDept.getDeptFlow());
						result.setDeptName(sysDept.getDeptName());
						result.setSchDeptFlow(sysDept.getDeptFlow());

						String sysDeptName = sysDept.getDeptName();
						String deptOrgFlow = sysDept.getOrgFlow();
						if (StringUtil.isNotBlank(deptOrgFlow)) {
							SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
							if (so != null && !so.getOrgFlow().equals(orgFlow)) {
								sysDeptName += ("[" + so.getOrgName() + "]");
							}
						}

						logger.info("查询result1111信息！！！！！！！");
						result.setSchDeptName(sysDeptName);
						result.setStandardDeptId(dept.getStandardDeptId());
						result.setStandardDeptName(dept.getStandardDeptName());
						result.setStandardGroupFlow(dept.getGroupFlow());
						result.setIsRequired(dept.getIsRequired());
						result.setCreateTime(DateUtil.getCurrDateTime());
						result.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
						result.setModifyTime(DateUtil.getCurrDateTime());
						result.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
						result.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
						Map<String, String> mapTime = new HashMap<>();
						mapTime.put("startDate", result.getSchStartDate());
						mapTime.put("endDate", result.getSchEndDate());
						Double month = TimeUtil.getMonthsBetween(mapTime);
						String schMonth = String.valueOf(Double.parseDouble(month + ""));
						result.setSchMonth(schMonth);
						result.setBaseAudit("Passed");
						schArrangeResultMapper.insert(result);

						logger.info("查询process信息！！！！！！！");
						ResDoctorSchProcess process = new ResDoctorSchProcess();
						process.setProcessFlow(PkUtil.getUUID());
						process.setUserFlow(user.getUserFlow());
						process.setOrgFlow(org.getOrgFlow());
						process.setOrgName(org.getOrgName());
						process.setDeptFlow(sysDept.getDeptFlow());
						process.setDeptName(sysDept.getDeptName());
						process.setSchDeptFlow(sysDept.getDeptFlow());
						process.setSchDeptName(sysDept.getDeptName());
						process.setSchResultFlow(result.getResultFlow());
						process.setSchStartDate(startDate);
						process.setSchEndDate(endDate);
						process.setCreateTime(DateUtil.getCurrDateTime());
						process.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
						process.setModifyTime(DateUtil.getCurrDateTime());
						process.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
						process.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);

						doctorSchProcessMapper.insertSelective(process);

						logger.info("查询proExample信息！！！！！！！");
						ResSchProcessExpressExample proExample = new ResSchProcessExpressExample();
						proExample.createCriteria().andOperUserFlowEqualTo(user.getUserFlow()).andSchRotationDeptFlowEqualTo(dept.getRecordFlow())
								.andRecTypeIdEqualTo(ResRecTypeEnum.AfterSummary.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
						List<ResSchProcessExpress> recList = schProcessExpressMapper.selectByExampleWithBLOBs(proExample);
						String recContent = "";
						if (recList != null && recList.size() > 0) {
							recContent = recList.get(0).getRecContent();
						}
						try {
							updateResultHaveAfter2(dept.getRecordFlow(), user.getUserFlow(), recContent);
						} catch (DocumentException e) {
							e.printStackTrace();
						}
						checkSuccess = true;
					}
					if(checkSuccess){
						succCount++;
					}
				}

			}
		}
		resultMap.put("msg",msg);
		resultMap.put("count",succCount);
		return resultMap;
	}

	public void updateResultHaveAfter2(String schRotationDeptFlow, String operUserFlow, String recContent) throws DocumentException {
		String haveAfterPic="N";
		if(StringUtil.isNotBlank(recContent))
		{
			Document document= DocumentHelper.parseText(recContent);
			if (document!=null) {
				Element element=document.getRootElement();
				List<Object> elem=element.elements("image");
				if(elem!=null&&elem.size()>0)
				{
					haveAfterPic="Y";
				}
			}
		}
		updateResultHaveAfter(haveAfterPic,schRotationDeptFlow,operUserFlow);
	}

	@Override
	public SysUser searchTeacherList(String sysDeptFlow, String roleId, String userName) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sysDeptFlow", sysDeptFlow);
		paramMap.put("roleFlow", roleId);
		paramMap.put("userName", userName);
		List<SysUser> userList = sysUserExtMapper.searchUserList(paramMap);
		if (null!=userList && userList.size()>0){
			return userList.get(0);
		}
		return null;
	}
	//检测时间是否重叠
	@Override
	public List<SchArrangeResult> checkResultDate(String doctorFlow,String startDate,String endDate,String subDeptFlow,String rotationFlow){
		List<SchArrangeResult> result = null;
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && StringUtil.isNotBlank(doctorFlow)){
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("doctorFlow",doctorFlow);
			paramMap.put("startDate",startDate);
			paramMap.put("endDate",endDate);
			paramMap.put("subDeptFlow",subDeptFlow);
			paramMap.put("rotationFlow",rotationFlow);
			result = resultExtMapper.checkResultDateList(paramMap);
		}
		return result;
	}

	@Override
	public List<SchArrangeResult> searchSchArrangeResult(String userFlow, String deptFlow) {
		SchRotationDept rotationDept=rotationDeptBiz.readSchRotationDept(deptFlow);
		SchArrangeResultExample example = new SchArrangeResultExample();
		example.createCriteria().andDoctorFlowEqualTo(userFlow).andStandardGroupFlowEqualTo(rotationDept.getGroupFlow()).andRotationFlowEqualTo(rotationDept.getRotationFlow())
				.andStandardDeptIdEqualTo(rotationDept.getStandardDeptId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("SCH_START_DATE");
		return schArrangeResultMapper.selectByExample(example);
	}

	@Override
	public List<SysDept> searchSysDeptList(String orgFlow, String searchStr) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("deptName",searchStr);
		List<SysDept> resultDepts = sysDeptExtMapper.getBaseAndExtDept(paramMap);

		if(resultDepts!=null && !resultDepts.isEmpty()){
			SysOrgExample orgExample = new SysOrgExample();
			orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgFlowNotEqualTo(orgFlow);

			List<SysOrg> orgs = orgMapper.selectByExample(orgExample);
			Map<String,SysOrg> orgMap = new HashMap<String,SysOrg>();
			if(orgs!=null && !orgs.isEmpty()){
				for(SysOrg so : orgs){
					orgMap.put(so.getOrgFlow(),so);
				}
			}

			for(SysDept dept : resultDepts){
				SysOrg so = orgMap.get(dept.getOrgFlow());
				if(so!=null){
					dept.setDeptName(dept.getDeptName().trim()+"["+so.getOrgName().trim()+"]");
				}
			}
		}
		return resultDepts;
	}

	/**
	 * 填写轮转计划时校验时间是否超过轮转计划规定时间
	 *
	 * @param userFlow
	 * @param deptFlow
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	private void CheckRotationTime(String userFlow, String deptFlow, String startDate, String endDate,  String type,
									  String subDeptFlow, String schMonth,Integer i) throws ParseException {
		if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			//系统设置：科室轮转时间不允许超过规定的月份,校验用户填写时间 Start
			if (StringUtil.isNotBlank(schMonth)) {
				// 学员填写时间间隔自然月
				int month = DateUtil.getMonths(startDate, endDate);
				// 填写时间间隔天数
				int distanceDay = DateUtil.getDistanceDay(startDate, endDate);
				List<SchArrangeResult> resultList = searchSchArrangeResult(userFlow, deptFlow);
				// 第一次填写，月份按自然月计算
				if (resultList == null || resultList.size() == 0) {
					// 用户填写时间超过规定范围
					if ("0.5".equals(schMonth)) {
						if (distanceDay > 15) {
							throw new RuntimeException("导入失败！第" + i + "行，填写时间超过规定的时间范围！");
						}
					} else if (month > Double.parseDouble(schMonth)) {
						throw new RuntimeException("导入失败！第" + i + "行，填写时间超过规定的时间范围！");
					}
				}
				// 该轮转已有超过一条记录，月份限制按31天计算
				else {
					int sumTime = 0;
					for (SchArrangeResult schArrangeResult : resultList) {
						String resultFlow = schArrangeResult.getResultFlow();
						// 已有记录，修改当前唯一一条数据，月份按自然月计算
						if (resultList.size() == 1 && subDeptFlow.equals(resultFlow)) {
							if ("0.5".equals(schMonth)) {
								if (distanceDay > 15) {
									throw new RuntimeException("导入失败！第" + i + "行，填写时间超过规定的时间范围！");
								}
							} else if (month > Double.parseDouble(schMonth)) {
								throw new RuntimeException("导入失败！第" + i + "行，填写时间超过规定的时间范围！");
							}
						}
						String schStartDate = schArrangeResult.getSchStartDate();
						String schEndDate = schArrangeResult.getSchEndDate();
						if (StringUtil.isNotBlank(schStartDate) && StringUtil.isNotBlank(schEndDate)) {
							// 新增或者编辑（编辑排除当前操作记录的起始时间）
							if (("1".equals(type)) || ("2".equals(type) && !subDeptFlow.equals(resultFlow))) {
								sumTime += DateUtil.getDistanceDay(schStartDate, schEndDate);
							}
						}
					}
					// 用户填写时间范围间隔天数 + 已有轮转记录时间总和超过规定的时间范围
					if ("0.5".equals(schMonth)) {
						if (distanceDay > 15) {
							throw new RuntimeException("导入失败！第" + i + "行，填写时间超过规定的时间范围！");
						}
					} else if ((sumTime + distanceDay) > Double.parseDouble(schMonth) * 31) {
						throw new RuntimeException("导入失败！第" + i + "行，填写时间超过规定的时间范围！");
					}
				}
			} else {
				throw new RuntimeException("导入失败！第" + i + "行，未查到轮转月份信息！");
			}
		}
	}
}
