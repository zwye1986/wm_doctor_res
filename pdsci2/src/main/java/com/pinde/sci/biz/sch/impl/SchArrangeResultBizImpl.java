package com.pinde.sci.biz.sch.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSONObject;
import com.pinde.core.common.enums.sch.SchArrangeStatusEnum;
import com.pinde.core.common.enums.sch.SchArrangeTypeEnum;
import com.pinde.core.common.enums.sch.SchStageEnum;
import com.pinde.core.model.SysDict;
import com.google.common.collect.Lists;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.*;
import com.pinde.excelUtils.enums.NumberEngEnum;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.biz.sys.impl.CfgBizImpl;
import com.pinde.sci.common.*;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.res.ResDoctorSchProcessExtMapper;
import com.pinde.sci.dao.sch.SchArrangeResultExtMapper;
import com.pinde.sci.dao.sys.SysDeptExtMapper;
import com.pinde.sci.dao.sys.SysUserExtMapper;
import com.pinde.core.common.enums.RecDocCategoryEnum;
import com.pinde.sci.excelListens.SchedulingAuditCheck;
import com.pinde.sci.excelListens.SchedulingAuditRead;
import com.pinde.sci.excelListens.model.*;
import com.pinde.sci.form.sch.SchArrangeResultForm;
import com.pinde.sci.form.sch.SelectDept;
import com.pinde.sci.model.jsres.*;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.SchArrangeResultExample.Criteria;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
//@Transactional(rollbackFor=Exception.class)
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
//	@Autowired
//	private SchArrangeResultExtMapper resultMapper;
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

	@Autowired
	private IResRecBiz resRecBiz;

	@Autowired
	private ResScoreMapper scoreMapper;

	private static Logger logger = LoggerFactory.getLogger(SchArrangeResultBizImpl.class);

	@Override
	public List<SchArrangeResult> searchSchArrangeResult() {
		SchArrangeResultExample example = new SchArrangeResultExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		return arrangeResultMapper.selectByExample(example);
	}

	@Override
	public List<SchArrangeResult> searchSchArrangeResultByDoctor(String doctorFlow){
		SchArrangeResultExample example = new SchArrangeResultExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow);
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
            return com.pinde.core.common.GlobalConstant.ONE_LINE;
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
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
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}


	@Override
	public int saveProcessAndResult(ResDoctorSchProcess process,SchArrangeResult result) {
		if(result==null){
            return com.pinde.core.common.GlobalConstant.ZERO_LINE;
		}

		if(process==null){
            return com.pinde.core.common.GlobalConstant.ZERO_LINE;
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
        if (com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(unit)) {
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
                process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
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
            String standardDeptName = com.pinde.core.common.enums.DictTypeEnum.StandardDept.getDictNameById(standardDeptId);
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
                process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
                process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
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
                if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(schFlag)) {
                    doctor.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    doctor.setSelDeptFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
					doctorBiz.editDoctor(doctor);
				}
			}
            process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
            process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
			process.setSchResultFlow(resultFlow);
			process.setUserFlow(doctorFlow);
			save(result);
		}
		processBiz.edit(process);

        return com.pinde.core.common.GlobalConstant.ONE_LINE;
	}

	@Override
	public int saveInDept(SchArrangeResult result, ResDoctorSchProcess process) throws ParseException {
		if(result==null){
            return com.pinde.core.common.GlobalConstant.ZERO_LINE;
		}

		if(process==null){
            return com.pinde.core.common.GlobalConstant.ZERO_LINE;
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
        if (com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(unit)) {
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
                process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
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
            String standardDeptName = com.pinde.core.common.enums.DictTypeEnum.StandardDept.getDictNameById(standardDeptId);
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
                process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
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
                if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(schFlag)) {
                    doctor.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    doctor.setSelDeptFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
					doctorBiz.editDoctor(doctor);
				}
			}
            process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
            process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
			process.setSchResultFlow(resultFlow);
			process.setUserFlow(doctorFlow);
			save(result);
		}
		processBiz.edit(process);

        return com.pinde.core.common.GlobalConstant.ONE_LINE;
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

            return com.pinde.core.common.GlobalConstant.ONE_LINE;
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public int delArrangeResult(String doctorFlow){
		SchArrangeResultExample example = new SchArrangeResultExample();
        example.createCriteria().andDoctorFlowEqualTo(doctorFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		SchArrangeResult resultTemp = new SchArrangeResult();
        resultTemp.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		return arrangeResultMapper.updateByExampleSelective(resultTemp, example);
	}

	@Override
	public int delArrangeResult(String doctorFlow,boolean reStatus){
		if(reStatus){
			ResDoctor doctor = new ResDoctor();
			doctor.setDoctorFlow(doctorFlow);
            doctor.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
			doctorBiz.editDoctor(doctor);
		}
		return delArrangeResult(doctorFlow);
	}

	@Override
	public List<SchArrangeResult> searchArrangeResultByDateAndOrg(String schStartDate,String schEndDate,String orgFlow){
		SchArrangeResultExample example = new SchArrangeResultExample();
		Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow);
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
		return resultExtMapper.searchArrangeResultByDateAndOrgByMapNew(map);
	}

	@Override
	public List<SchArrangeResult> searchArrangeResultByDate(String schStartDate,String doctorFlow){
		SchArrangeResultExample example = new SchArrangeResultExample();
		Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
		.andSchEndDateBetween(schStartDate,schEndDate).andDoctorNameEqualTo(doctorName);
		example.setOrderByClause("SCH_END_DATE");
		return arrangeResultMapper.selectByExample(example);
	}

	@Override
	public List<SchArrangeResult> searchArrangeResultByDateAndDoctorFlow(String startDate,String endDate,String docFlow){
		return resultExtMapper.searchArrangeResultByDateAndDoctorFlow(startDate,endDate,docFlow);
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
		return resultExtMapper.searchArrangeResultByDateAndDoctorFlows(paramMap);
	}

	@Override
	public List<Map<String,Object>> searchArrangeResultNotInDates(String startDate,String endDate,String docFlow){
		return resultExtMapper.searchArrangeResultNotInDates(startDate,endDate,docFlow);
	}

	@Override
	public List<SchArrangeResult> searchCycleArrangeResults(Map<String,Object> paramMap){
		return resultExtMapper.searchCycleArrangeResults(paramMap);
	}

	@Override
	public List<SchArrangeResult> searchArrangeResultByDoctorFlows(List<String> doctorFlows){
		List<SchArrangeResult> resultList = null;
		if(doctorFlows!=null&&!doctorFlows.isEmpty()){
			SchArrangeResultExample example = new SchArrangeResultExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowIn(doctorFlows);
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
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andResultFlowIn(resultFlows);
			example.setOrderByClause("SCH_END_DATE");
			resultList = arrangeResultMapper.selectByExample(example);
		}
		return resultList;
	}

	@Override
	public List<Map<String,Object>> countResultByUser(List<String> userFlows){
		return resultExtMapper.countResultByUser(userFlows);
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
            doctor.setSelDeptFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
//			doctor.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
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
                    result.setIsRequired(com.pinde.core.common.GlobalConstant.FLAG_N);
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
        return com.pinde.core.common.GlobalConstant.ONE_LINE;
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
                result.setIsRequired(com.pinde.core.common.GlobalConstant.FLAG_Y);
				for(SchDept schDept : schDeptList){
					result.setResultFlow(null);
					result.setDeptFlow(schDept.getDeptFlow());
					result.setDeptName(schDept.getDeptName());
					result.setSchDeptFlow(schDept.getSchDeptFlow());
					result.setSchDeptName(schDept.getSchDeptName());
					saveSchArrangeResult(result);
				}

                doctor.setSelDeptFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
				doctorBiz.editDoctor(doctor);

                return com.pinde.core.common.GlobalConstant.ONE_LINE;
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
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
            doctor.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
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
                    result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
					for(String flow : delResultFlows){
						result.setResultFlow(flow);
						saveSchArrangeResult(result);
					}
				}
			}
		}

        return com.pinde.core.common.GlobalConstant.ONE_LINE;
	}

	@Override
	public int editFreeRostering(String doctorFlow, String groupFlow, String standardDeptId,String standardDeptName, String schDeptFlow) {
		if (StringUtil.isNotBlank(doctorFlow)) {
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			SchRotationGroup group = groupBiz.readSchRotationGroup(groupFlow);

			//重置状态
            doctor.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
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
                            result.setIsRotation(com.pinde.core.common.GlobalConstant.FLAG_N);
							saveSchArrangeResult(result);
						}
					}
				}
			}
		}

        return com.pinde.core.common.GlobalConstant.ONE_LINE;
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
                        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(rotationDept.getIsRequired())) {
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
                            doctor.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
                            doctor.setSelDeptFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
							doctorBiz.editDoctor(doctor);
						}
					}

                    return com.pinde.core.common.GlobalConstant.ONE_LINE;
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<SchArrangeResult> searchResultByGroupFlow(String groupFlow,
			String standardDeptId,String doctorFlow) {
		SchArrangeResultExample example = new SchArrangeResultExample();
		Criteria exam=example.createCriteria();
        exam.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
            exam.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow).andApplyYearEqualTo(applyYear);
		List<HbresDoctorDeptDetail> list=hbresDoctorDeptDetailMapper.selectByExample(example);
		return list;
	}

	@Override
	public Map<String, String> imgUpload(String resultFlow, MultipartFile file, String fileType) {
		Map<String, String> map=new HashMap<String, String>();
        map.put("status", com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG);
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
                map.put("error", com.pinde.core.common.GlobalConstant.UPLOAD_IMG_TYPE_ERROR);
				return  map;

			}
			long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
			if (file.getSize() > limitSize * 1024 * 1024) {
                map.put("error", com.pinde.core.common.GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize + "M");
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
                pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				pubFile.setFilePath(filePath);
				pubFile.setFileName(fileName);
				pubFile.setFileSuffix(suffix);
				pubFile.setProductType(fileType);
				pubFile.setProductFlow(resultFlow);
				int c=fileBiz.editFile(pubFile);
				if(c==1) {
					map.put("url",url);
                    map.put("status", com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG);
				}else{
					map.put("error","上传失败！");
                    map.put("status", com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	@Override
	public List<SchArrangeResult> schArrangeResultQuery(Map<String, Object> map) {
		List<SchArrangeResult> arrangeResultList=resultExtMapper.schArrangeResultQuery(map);
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
            doctor.setSelDeptFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
            doctor.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
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
                    String standardDeptName = com.pinde.core.common.enums.DictTypeEnum.StandardDept.getDictNameById(standardDeptId);
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
                process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
                process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
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
            return com.pinde.core.common.GlobalConstant.ZERO_LINE;
		}

		if(process==null){
            return com.pinde.core.common.GlobalConstant.ZERO_LINE;
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
        if (com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(unit)) {
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
            String standardDeptName = com.pinde.core.common.enums.DictTypeEnum.StandardDept.getDictNameById(standardDeptId);
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
                process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
                process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
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
                if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(schFlag)) {
                    doctor.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    doctor.setSelDeptFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
					doctorBiz.editDoctor(doctor);
				}
			}
            process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
            process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
			process.setSchResultFlow(resultFlow);
			process.setUserFlow(doctorFlow);
			save(result);
		}
		processBiz.edit(process);

        return com.pinde.core.common.GlobalConstant.ONE_LINE;
	}

	@Override
	public int delResultByResultFlow(String resultFlow) {
        int result = com.pinde.core.common.GlobalConstant.ZERO_LINE;
        int processResult = com.pinde.core.common.GlobalConstant.ZERO_LINE;
		SchArrangeResult arrangeResult = readSchArrangeResult(resultFlow);
        arrangeResult.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
		result = update(arrangeResult);

		ResDoctorSchProcess process = processBiz.searchByResultFlow(resultFlow);
		if(process!=null) {
            process.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
			processResult = processBiz.edit(process);

			ResRecExample recExample = new ResRecExample();
			com.pinde.sci.model.mo.ResRecExample.Criteria criteria =  recExample.createCriteria();
			criteria.andProcessFlowEqualTo(process.getProcessFlow());
			ResRec rec = new ResRec();
            rec.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
			recMapper.updateByExampleSelective(rec, recExample);
		}else{
			processResult=1;
		}

        if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE && processResult != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.ONE_LINE;
		}else{
            return com.pinde.core.common.GlobalConstant.ZERO_LINE;
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
	public Map<String,Map<String, BigDecimal>> getScoreByDoctorIds(List<String> doctorFlowList, String schStartDate, String schEndDate) {
		Map<String,Map<String, BigDecimal>> result = new HashMap<>();
		String ll = "thryScore";
		String jn = "killScore";
		if (CollectionUtil.isEmpty(doctorFlowList)) {
			return result;
		}
		List<ResSchProcessExpress> resSchProcessExpresses = schProcessExpressMapper.listByDoctorList(doctorFlowList, schStartDate, schEndDate);
		List<SchArrangeResult> arrangeResultList = resultExtMapper.selectDoctorSchDate(doctorFlowList, schStartDate, schEndDate);
		Map<String, List<SchArrangeResult>> doctorToArrangeMap = arrangeResultList.stream().collect(Collectors.groupingBy(vo -> vo.getDoctorFlow()));
		List<String> resultFlowList = arrangeResultList.stream().map(vo -> vo.getResultFlow()).collect(Collectors.toList());
		List<ResDoctorSchProcess> processList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(resultFlowList)) {
			Lists.partition(resultFlowList, 1000).forEach(subList -> {
				ResDoctorSchProcessExample processExample = new ResDoctorSchProcessExample();
				processExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andSchResultFlowIn(subList);
				processList.addAll(processBiz.readResDoctorSchProcessByExample(processExample));
			});

		}

		Map<String, ResDoctorSchProcess> processFlowToEntityMap = processList.stream().collect(Collectors.toMap(vo -> vo.getProcessFlow(), vo -> vo, (vo1, vo2) -> vo1));
		Map<String, List<ResDoctorSchProcess>> doctorFlowToEntityMap = processList.stream().collect(Collectors.groupingBy(vo -> vo.getUserFlow()));
		if (CollectionUtil.isEmpty(resSchProcessExpresses)) {
			Map<String, BigDecimal> itemMap = new HashMap<>();
			for (String doctorFlow : doctorFlowList) {
				itemMap = new HashMap<>();
				itemMap.put(ll,new BigDecimal("0"));
				itemMap.put(jn,new BigDecimal("0"));
				List<ResDoctorSchProcess> doctorProcessList = doctorFlowToEntityMap.get(doctorFlow);
				if(CollectionUtils.isNotEmpty(doctorProcessList)) {
					BigDecimal totalScore = BigDecimal.ZERO;
					int count = 0;
					for (ResDoctorSchProcess process : doctorProcessList) {
						if(process.getSchScore() != null) {
							totalScore = totalScore.add(process.getSchScore());
							count++;
						}
					}
					if(count > 0) {
						itemMap.put(ll, totalScore.divide(new BigDecimal(count), 2, BigDecimal.ROUND_HALF_DOWN));
					}
				}
				result.put(doctorFlow,itemMap);
			}
			return result;
		}
		Map<String, List<ResSchProcessExpress>> expMap = resSchProcessExpresses.stream().collect(Collectors.groupingBy(ResSchProcessExpress::getOperUserFlow));
		Map<String, BigDecimal> itemMap = new HashMap<>();
		for (String doctorFlow : doctorFlowList) {
			if (StringUtils.isEmpty(doctorFlow)) {
				continue;
			}
			itemMap = new HashMap<>();
			List<String> useProcessList = new ArrayList<>();
			List<ResSchProcessExpress> itemList = expMap.get(doctorFlow);
			itemMap = new HashMap<>();
			BigDecimal lilunScore = new BigDecimal("0");
			int llCount = 0;
			itemMap.put(ll,new BigDecimal("0"));
			BigDecimal jinengScore = new BigDecimal("0");
			int jnCount = 0;
			itemMap.put(jn,new BigDecimal("0"));
			if (CollectionUtil.isNotEmpty(itemList)) {
				for (ResSchProcessExpress item : itemList) {
					useProcessList.add(item.getProcessFlow());
					String recContent = item.getRecContent();
					if (StringUtils.isEmpty(recContent)) {
						continue;
					}
					Map<String, Object> stringObjectMap = resRecBiz.parseRecContent(recContent);
					if (CollectionUtil.isEmpty(stringObjectMap)) {
						continue;
					}
					//理论成绩
					Object theoreResult = stringObjectMap.get("theoreResult");
					if (ObjectUtil.isNotEmpty(theoreResult)) {
						try{
							BigDecimal bigDecimal = new BigDecimal(String.valueOf(theoreResult));
							lilunScore = lilunScore.add(bigDecimal);
							llCount++;
						}catch (Exception e) {

						}
					}else {
						ResDoctorSchProcess processItem = processFlowToEntityMap.getOrDefault(item.getProcessFlow(), new ResDoctorSchProcess());
						BigDecimal theoryScore = processItem.getSchScore();
						if (theoryScore != null) {
							lilunScore = lilunScore.add(theoryScore);
							llCount++;
						}
					}
					//技能成绩
					Object score = stringObjectMap.get("score");
					if (ObjectUtil.isNotEmpty(score)) {
						try{
							BigDecimal bigDecimal = new BigDecimal(String.valueOf(score));
							jinengScore = jinengScore.add(bigDecimal);
							jnCount++;
						}catch (Exception e) {

						}
					}
				}
			}
			List<ResDoctorSchProcess> doctorProcessList = doctorFlowToEntityMap.get(doctorFlow);
			if(CollectionUtils.isNotEmpty(doctorProcessList)) {
				for (ResDoctorSchProcess process : doctorProcessList) {
					if(!useProcessList.contains(process.getProcessFlow()) && process.getSchScore() != null) {
						lilunScore = lilunScore.add(process.getSchScore());
						llCount++;
					}
				}
			}

			if (llCount>0){
				BigDecimal divide = lilunScore.divide(new BigDecimal(llCount), 2, BigDecimal.ROUND_HALF_DOWN);
				itemMap.put(ll,divide);
			}
			if (jnCount>0){
				BigDecimal divide = jinengScore.divide(new BigDecimal(jnCount), 2, BigDecimal.ROUND_HALF_DOWN);
				itemMap.put(jn,divide);
			}
			if (itemMap.get(ll).compareTo(new BigDecimal("0"))<=0) {
				itemMap.put(ll,new BigDecimal("0"));
			}
			if (itemMap.get(jn).compareTo(new BigDecimal("0"))<=0) {
				itemMap.put(jn,new BigDecimal("0"));
			}
			result.put(doctorFlow,itemMap);
		}
		return result;
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
        String isChargeOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
		JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_doctor_app_menu_"+userFlow);
        if (null == cfg || com.pinde.core.common.GlobalConstant.FLAG_N.equals(cfg.getCfgValue())) {
            isChargeOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
		}
		List<ResDoctorSchProcess> resDoctorSchProcesses = processBiz.searchProcessByDoctor(userFlow);
		List<ResDoctorSchProcess> outTimeProcesses = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(resDoctorSchProcesses)){
            if (isChargeOrg.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
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
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow)
				.andRotationFlowEqualTo(rotationFlow);
		example.setOrderByClause("SCH_DEPT_ORDER,SCH_START_DATE");
		return arrangeResultMapper.selectByExample(example);
	}
	@Override
	public List<SchArrangeResult> searchSchArrangeResultIsBByDoctorAndRotationFlow(String doctorFlow, String rotationFlow) {

		SchArrangeResultExample example = new SchArrangeResultExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow)
                .andRotationFlowEqualTo(rotationFlow).andIsStepBEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		example.setOrderByClause("SCH_DEPT_ORDER,SCH_START_DATE");
		return arrangeResultMapper.selectByExample(example);
	}

	@Override
	public List<SchArrangeResult> searchSchArrangeResultByDoctorAndRotationFlowAndStandardId(String doctorFlow, String rotationFlow,String standardDeptId){
		SchArrangeResultExample example = new SchArrangeResultExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow)
				.andRotationFlowEqualTo(rotationFlow).andStandardDeptIdEqualTo(standardDeptId);
		example.setOrderByClause("SCH_DEPT_ORDER,SCH_START_DATE");
		return arrangeResultMapper.selectByExample(example);

	}


	@Override
	public JsresDoctorDeptDetail deptDoctorWorkDetail(String recordFlow, String rotationFlow, String doctorFlow) {
		JsresDoctorDeptDetailExample example=new JsresDoctorDeptDetailExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchStandardDeptFlowEqualTo(recordFlow)
				.andDoctorFlowEqualTo(doctorFlow).andRotationFlowEqualTo(rotationFlow);
		List<JsresDoctorDeptDetail> list=doctorDeptDetailMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}
	@Override
	public List<JsresDoctorDeptDetail> deptDoctorAllWorkDetail(String rotationFlow, String doctorFlow, String applyYear) {
		JsresDoctorDeptDetailExample example=new JsresDoctorDeptDetailExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
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
                    result.setIsRotation(com.pinde.core.common.GlobalConstant.FLAG_Y);
					result.setDoctorFlow(doctor.getDoctorFlow());
					result.setDoctorName(doctor.getDoctorName());
				}
                if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_D.equals(result.getRecordStatus())) {
                    result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
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
		return resultExtMapper.searchCycleArrangeResultsForUni(paramMap);
	}

	@Override
	public List<SchArrangeResult> searchCycleArrangeResultsForUniTwo(Map<String,Object> paramMap){
		if(paramMap.get("workOrgId")==null&&paramMap.get("workOrgName")==null){
			return null;
		}
		return resultExtMapper.searchCycleArrangeResultsForUniTwo(paramMap);
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

//	@Override
//	public Map<String,Object> importSchedulingAuditExcelCache(MultipartFile file) throws IOException, InvalidFormatException {
//		Map<String, Object> result = new HashMap<>();
//		InputStream is = file.getInputStream();
//		byte[] fileData = new byte[(int) file.getSize()];
//		is.read(fileData);
//		Workbook wb =  createUserWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
//		int sheetNum = wb.getNumberOfSheets();
//		if(sheetNum<=0){
//			return result;
//		}
//		List<String> headList = new ArrayList<>();
//		Sheet sheetAt = wb.getSheetAt(0);
//		Row headRow = sheetAt.getRow(0);
//		short lastCellNum = headRow.getLastCellNum();
//		if (lastCellNum<1) {
//			return result;
//		}
//		for (int i = 0; i < lastCellNum; i++) {
//			String stringCellValue = headRow.getCell(i).getStringCellValue();
//			headList.add(stringCellValue);
//		}
//		result.put("headers",headList);
//		int lastRowNum = sheetAt.getLastRowNum();
//		if (lastRowNum <1) {
//			return result;
//		}
//		List<Map<String, String>> data = new ArrayList<>();
//		Map<String, String> dataItem = new HashMap<>();
//		for (int i = 1; i <= lastRowNum; i++) {
//			Row row = sheetAt.getRow(i);
//			if (null == row) {
//				//忽略空行
//				continue;
//			}
//			dataItem = new HashMap<>();
//			for (int j = 0; j < headList.size(); j++) {
//				String column = headList.get(j);
//				Cell cell = row.getCell(j);
//				if(null != cell){
//					cell.setCellType(CellType.STRING);
//					String stringCellValue = cell.getStringCellValue();
//					if (StringUtil.isBlank(stringCellValue)) {
//						dataItem.put(column,null);
//						continue;
//					}
//					dataItem.put(column,stringCellValue);
//					continue;
//				}
//				dataItem.put(column,null);
//			}
//			data.add(dataItem);
//		}
//		//数据处理
//		if (CollectionUtil.isEmpty(data)) {
//			result.put("data",new ArrayList<>());
//			result.put("flag",false);
//			return result;
//		}
//		List<Map<String, ArrangTdVo>> list = new ArrayList<>();
//		Map<String, ArrangTdVo> rowItem = new HashMap<>();
//		ArrangTdVo item = new ArrangTdVo();
//		for (Map<String, String> datum : data) {
//			rowItem = new HashMap<>();
//			if (CollectionUtil.isEmpty(datum)) {
//				list.add(rowItem);
//				continue;
//			}
//			item = new ArrangTdVo();
//			item.setDisable(true);
//			rowItem.put("recurit",item);
//			for (String key : datum.keySet()) {
//				item = new ArrangTdVo();
//				item.setContext(StringUtils.isEmpty(datum.get(key))? "":datum.get(key));
//				item.setDisable(true);
//				item.setTip("");
//				rowItem.put(key,item);
//			}
//			list.add(rowItem);
//		}
////		Map<String, Object> res = checkData(data);
//		Map<String, Object> res = checkReturnData(list);
//		result.put("data",res.get("data"));
//		result.put("flag",res.get("flag"));
//		return result;
//	}



	@Override
	public Map<String,Object> updateImportData(List<Map<String, ArrangTdVo>> data) {
		//将data转为 List<Map<String, String>> 类型
		Map<String, Object> resp = new HashMap<>();
		resp.put("code",200);
		if (CollectionUtil.isEmpty(data)) {
			resp.put("data",data);
			return resp;
		}
//		List<Map<String, String>> updateData = new ArrayList<>();
//		Map<String, String> itemMap = new HashMap<>();
//		for (Map<String, ArrangTdVo> datum : data) {
//			itemMap = new HashMap<>();
//			if (CollectionUtil.isEmpty(datum)) {
//				continue;
//			}
//			for (String keyName : datum.keySet()) {
//				itemMap.put(keyName,datum.get(keyName).getContext());
//			}
//			updateData.add(itemMap);
//		}
//		Map<String, Object> maps = checkData(updateData);
		resp = checkReturnData(data);
//		if (!(Boolean) resp.get("flag")) {
//			resp.put("code",500);
//			resp.put("msg","存在校验不通过的数据，请调整后提交");
//		}
		return resp;
	}

	@Override
	public Map<String, Object> saveDbImportArrang(List<Map<String, ArrangTdVo>> data) throws ParseException {
		Map<String, Object> resp = new HashMap<>();
		resp.put("code",200);
		resp.put("data",data);
		if (CollectionUtil.isEmpty(data)) {
			return resp;
		}
		List<PbImportDataVo> list = new ArrayList<>();
		PbImportDataVo vo = new PbImportDataVo();
		List<PbImportDataItem> itemDeptList = new ArrayList<>();
		PbImportDataItem deptItem = new PbImportDataItem();
		for (Map<String, ArrangTdVo> item : data) {
			ArrangTdVo arrangTdVo1 = new ArrangTdVo();
			arrangTdVo1.setDisable(true);
			item.put("recurit",arrangTdVo1);
			vo = new PbImportDataVo();
			SysUser user = item.get("user").getUser();
			if (ObjectUtil.isEmpty(user)) {
				ArrangTdVo arrangTdVo = item.get("recurit");
				arrangTdVo.setTip(StringUtils.isEmpty(arrangTdVo.getTip())? "未识别到用户信息<br/>":
						arrangTdVo.getTip()+"未识别到用户信息<br/>");
				item.put("recurit",arrangTdVo);
				continue;
			}
			vo.setUserFlow(user.getUserFlow());
			vo.setUserName(user.getUserName());
			vo.setDoctorFlow(user.getUserFlow());
			vo.setDoctorName(user.getUserName());
			//！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！校验时需要设置这一行数据的方案id
			if (ObjectUtil.isEmpty(item.get("rotationFlow")) || StringUtils.isEmpty(item.get("rotationFlow").getContext())){
				ArrangTdVo arrangTdVo = item.get("recurit");
				arrangTdVo.setTip(StringUtils.isEmpty(arrangTdVo.getTip())? "未识别到用户的轮转方案<br/>":
						arrangTdVo.getTip()+"未识别到用户的轮转方案<br/>");
				item.put("recurit",arrangTdVo);
				continue;
			}
			SchRotation rotationInfo = rotationBiz.readSchRotation(item.get("rotationFlow").getContext());
			if (ObjectUtil.isEmpty(rotationInfo)) {
				ArrangTdVo arrangTdVo = item.get("recurit");
				arrangTdVo.setTip(StringUtils.isEmpty(arrangTdVo.getTip())? "轮转方案不存在<br/>":
						arrangTdVo.getTip()+"轮转方案不存在<br/>");
				item.put("recurit",arrangTdVo);
				continue;
			}
			vo.setRotationFlow(rotationInfo.getRotationFlow());
			vo.setRotationName(rotationInfo.getRotationName());
			vo.setSessionNumber(item.get("sessionNumber").getContext());
			vo.setSchYear(item.get("schYear").getContext());
			if (ObjectUtil.isEmpty(item.get("orgFlow")) || StringUtils.isEmpty(item.get("orgFlow").getContext())){
				ArrangTdVo arrangTdVo = item.get("recurit");
				arrangTdVo.setTip(StringUtils.isEmpty(arrangTdVo.getTip())? "未识别到用户的机构信息<br/>":
						arrangTdVo.getTip()+"未识别到用户的机构信息<br/>");
				item.put("recurit",arrangTdVo);
				continue;
			}
			vo.setOrgFlow(item.get("orgFlow").getContext());
			if (ObjectUtil.isEmpty(item.get("doctorFlow")) || StringUtils.isEmpty(item.get("doctorFlow").getContext())){
				ArrangTdVo arrangTdVo = item.get("recurit");
				arrangTdVo.setTip(StringUtils.isEmpty(arrangTdVo.getTip())? "未识别到用户的医师信息<br/>":
						arrangTdVo.getTip()+"未识别到用户的医师信息<br/>");
				item.put("recurit",arrangTdVo);
				continue;
			}
			vo.setDoctorFlow(item.get("doctorFlow").getContext());
			itemDeptList = new ArrayList<>();
			for (String cloumnName : item.keySet()) {
				if (StringUtils.isEmpty(cloumnName)) {
					continue;
				}
				deptItem = new PbImportDataItem();
				try{
					DateTime parse = cn.hutool.core.date.DateUtil.parse(cloumnName, "yyyy-MM");
					DateTime start = cn.hutool.core.date.DateUtil.beginOfMonth(parse);
					DateTime end = cn.hutool.core.date.DateUtil.endOfMonth(parse);
					String startDate = cn.hutool.core.date.DateUtil.formatDate(start);
					String endDate = cn.hutool.core.date.DateUtil.formatDate(end);
					ArrangTdVo arrangTdVo = item.get(cloumnName);
					String schDeptName = arrangTdVo.getContext();
					String schDeptFlow = arrangTdVo.getDeptFlow();
					//此时schDeptName，schDeptFlow可能是多个，含,的,目前只支持最多两个
					if (schDeptName.contains(",") && schDeptFlow.contains(",")) {
						String[] nameSplit = StringUtils.split(schDeptName, ",");
						String[] flowSplit = StringUtils.split(schDeptFlow, ",");
						deptItem = new PbImportDataItem();
						DateTime halfDate = cn.hutool.core.date.DateUtil.offsetDay(start, 15);
						deptItem.setStartDate(startDate);
						deptItem.setEndDate(cn.hutool.core.date.DateUtil.formatDate(halfDate));
						if (null != flowSplit && flowSplit.length>0) {
							deptItem.setSchDeptFlow(flowSplit[0]);
						}
						if (null != nameSplit && nameSplit.length>0) {
							deptItem.setSchDeptName(nameSplit[0]);
						}
						itemDeptList.add(deptItem);
						//后半月
						deptItem = new PbImportDataItem();
						deptItem.setStartDate(cn.hutool.core.date.DateUtil.formatDate(halfDate));
						deptItem.setEndDate(endDate);
						if (null != flowSplit && flowSplit.length>1) {
							deptItem.setSchDeptFlow(flowSplit[1]);
						}
						if (null != nameSplit && nameSplit.length>1) {
							deptItem.setSchDeptName(nameSplit[1]);
						}
						itemDeptList.add(deptItem);
					}else {
						deptItem = new PbImportDataItem();
						deptItem.setStartDate(startDate);
						deptItem.setEndDate(endDate);
						deptItem.setSchDeptFlow(schDeptFlow);
						deptItem.setSchDeptName(schDeptName);
						itemDeptList.add(deptItem);
					}

				}catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
			vo.setSchDeptList(itemDeptList);
			list.add(vo);
		}
		//数据入库
		boolean b = saveDbImportArrangTool(list);
		if (!b) {
			resp.put("code",500);
		}
		resp.put("data",data);
		return resp;
	}

	@Override
	public Map<String,Object> submitImportData(List<Map<String, ArrangTdVo>> data) {
		Map<String, Object> result = new HashMap<>();
		result.put("code",200);
		if (CollectionUtil.isEmpty(data)) {
			return result;
		}
		List<Map<String, String>> updateData = new ArrayList<>();
		Map<String, String> itemMap = new HashMap<>();
		for (Map<String, ArrangTdVo> datum : data) {
			itemMap = new HashMap<>();
			if (CollectionUtil.isEmpty(datum)) {
				continue;
			}
			for (String keyName : datum.keySet()) {
				itemMap.put(keyName,datum.get(keyName).getContext());
			}
			updateData.add(itemMap);
		}
		Map<String, Object> res = checkData(updateData);
		if (!(Boolean)res.get("flag")) {
			result.put("code",500);
			result.put("msg","数据校验未通过，存在异常数据");
			return result;
		}
		//校验通过的情况
		//开始进行db操作
		return result;
	}


	/**
	 * ~~~~~~~~~溺水的鱼~~~~~~~~
	 * @Author: 吴强
	 * @Date: 2024/9/19 15:29
	 * @Description: 校验导入的数据，同时追加一些页面的信息
	 * value字段：context:内容，统一字符类型
	 *           hide:true/false 是否隐藏
	 *           inputType: 'input'/'select' 只允许调整姓名和轮转科室，input是姓名，select下拉框是科室 span 显示
	 *           color: 色值 正确-'#00B83F' 异常-'#ee0101'
	 *           tip: 异常原因提示
	 *           disable: 是否禁用编辑 true-禁用编辑 false-不禁用编辑
	 */
	private Map<String,Object> checkData(List<Map<String, String>> data){
		Map<String, Object> resp = new HashMap<>();
		resp.put("flag",true);
		resp.put("data",data);
		List<Map<String, ArrangTdVo>> result = new ArrayList<>();
		if (CollectionUtil.isEmpty(data)) {
			resp.put("flag",false);
			return resp;
		}
		Map<String, ArrangTdVo> listItem = new HashMap<>();
		ArrangTdVo item = new ArrangTdVo();
		List<String> userFlowList = new ArrayList<>();
		//提取公共的用于校验数据的查询，减少与数据库的连接创建次数
		//根据姓名查询到的数据
		Map<String, SysUser> userMapByName = new HashMap<>();
		List<String> userNameList = data.stream().map(e -> e.get("学员姓名")).collect(Collectors.toList());
		List<SysUser> sysUsers = userBiz.selectByNamesOrIdNo(userNameList, null);
		if (CollectionUtil.isNotEmpty(sysUsers)) {
			userMapByName = sysUsers.stream().collect(Collectors.toMap(SysUser::getUserName, Function.identity(), (k1, k2) -> k2));
		}
		//根据身份证号查询到数据
		Map<String, SysUser> userMapByIdNo = new HashMap<>();
		List<String> idNoList = data.stream().map(e -> e.get("身份证号")).collect(Collectors.toList());
		List<SysUser> idNOs = userBiz.selectByNamesOrIdNo(null, idNoList);
		if (CollectionUtil.isNotEmpty(idNOs)) {
			userMapByIdNo = idNOs.stream().collect(Collectors.toMap(SysUser::getIdNo, Function.identity(), (k1, k2) -> k2));
			userFlowList = idNOs.stream().map(SysUser::getUserFlow).collect(Collectors.toList());
		}
		//校验学员志愿
		List<ResDoctorRecruit> recruitList = new ArrayList<>();
		Map<String, List<ResDoctorRecruit>> recruitMap = new HashMap<>();
		if (CollectionUtil.isNotEmpty(userFlowList)) {
			ResDoctorRecruitExample example = new ResDoctorRecruitExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
					.andAuditStatusIdEqualTo("Passed")
					.andDoctorFlowIn(userFlowList);
			example.setOrderByClause("CREATE_TIME DESC");
			recruitList = resDoctorRecruitMapper.selectByExample(example);
		}
		if (CollectionUtil.isNotEmpty(recruitList)) {
			recruitMap = recruitList.stream().collect(Collectors.groupingBy(ResDoctorRecruit::getDoctorFlow));
		}

		for (Map<String, String> itemMap : data) {
			//每一行
			listItem = new HashMap<>();
			if (CollectionUtil.isEmpty(itemMap)) {
				//行数据为空
				continue;
			}
			item.setDisable(true);
			listItem.put("recurit",item);
			//学员校验,返回异常信息，空表示正常数据
			boolean checkName = checkDataUserName(listItem, itemMap, userMapByName);
			if (!checkName) {
				resp.put("flag",false);
			}
			//校验身份证号 合规 非空
			boolean checkIdNo = checkDataIdNo(listItem, itemMap, userMapByIdNo, idNoList);
			if (!checkIdNo) {
				resp.put("flag",false);
			}
			//此时可以获取到该条数据的orgFlow了
			String orgFlow = "";
			if (ObjectUtil.isNotEmpty(listItem.get("user"))) {
				if (StringUtils.isNotEmpty(listItem.get("user").getUser().getUserFlow())) {
					ResDoctor doctor = doctorBiz.readDoctor(listItem.get("user").getUser().getUserFlow());
					if (ObjectUtil.isEmpty(doctor)) {
						ArrangTdVo arrangTdVo = listItem.get("recurit");
						arrangTdVo.setTip(StringUtils.isEmpty(arrangTdVo.getTip())? "学员不存在<br/>":
								arrangTdVo.getTip()+"学员不存在<br/>");
						listItem.put("recurit",arrangTdVo);
						ArrangTdVo doctorFlow = new ArrangTdVo();
						doctorFlow.setContext("");
						listItem.put("dcotorFlow",doctorFlow);
						resp.put("flag",false);
					}else {
						if (StringUtils.isNotEmpty(doctor.getSecondOrgFlow())) {
							orgFlow = doctor.getSecondOrgFlow();
						}else {
							orgFlow = doctor.getOrgFlow();
						}
						ArrangTdVo arrangTdVo = new ArrangTdVo();
						arrangTdVo.setContext(doctor.getRotationFlow());
						listItem.put("rotationFlow",arrangTdVo);
						ArrangTdVo arrangTdVo2 = new ArrangTdVo();
						arrangTdVo2.setContext(orgFlow);
						listItem.put("orgFlow",arrangTdVo2);
						ArrangTdVo doctorFlow = new ArrangTdVo();
						doctorFlow.setContext(doctor.getDoctorFlow());
						listItem.put("dcotorFlow",doctorFlow);
					}
				}
			}
			if (StringUtils.isEmpty(orgFlow)) {
				ArrangTdVo arrangTdVo = listItem.get("recurit");
				arrangTdVo.setTip(StringUtils.isEmpty(arrangTdVo.getTip())? "未识别到该学员的机构信息<br/>":
						arrangTdVo.getTip()+"未识别到该学员的机构信息<br/>");
				listItem.put("recurit",arrangTdVo);
				resp.put("flag",false);
			}else {
				listItem.put("orgFlow",new ArrangTdVo(orgFlow));
			}
			//查询系统设置最低的排班轮转时长
			//最短排班时长限制，0表示不限制
			int nimMonth = 0;
			JsresPowerCfg openCfg = jsResPowerCfgBiz.read("process_scheduling_check_" + orgFlow);
			if (ObjectUtil.isNotEmpty(openCfg)) {
				String openVal = openCfg.getCfgValue();
                if (StringUtils.isNotEmpty(openVal) && com.pinde.core.common.GlobalConstant.FLAG_Y.equalsIgnoreCase(openVal)) {
					//限制开启
					JsresPowerCfg minYearNumCfg = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_process_scheduling_time");
					if (ObjectUtil.isNotEmpty(minYearNumCfg)) {
						String cfgValue = minYearNumCfg.getCfgValue();
						if (StringUtils.isNotEmpty(cfgValue)) {
							Integer yearNum = Integer.valueOf(cfgValue);
							nimMonth = yearNum*12;
						}else {
							nimMonth = 12;
						}
					}else {
						nimMonth = 12;
					}
				}
			}
			//校验专业 非空
			boolean checkSpe = checkDataSpe(listItem, itemMap);
			if (!checkSpe) {
				resp.put("flag",false);
			}
			//校验年级 非空
			boolean checkSessionNum = checkDataSessionNum(listItem, itemMap);
			if (!checkSessionNum) {
				resp.put("flag",false);
			}
			//校验年限 非空
			boolean checkYear = checkDataYear(listItem, itemMap);
			if (!checkYear) {
				resp.put("flag",false);
			}
			//校验是否有支援填报信息 recruit
			ArrangTdVo arrangTdVo = listItem.get("user");
			List<SysDept> sysDepts = new ArrayList<>();
			if (null != arrangTdVo) {
				sysDepts = checkDataRecurit(listItem, arrangTdVo.getUser(), recruitMap);
				//一行一个实际可轮转科室的集合
				listItem.put("schDeptList",new ArrangTdVo(sysDepts));
			}
			Map<String, String> deptNameIdMap = new HashMap<>();
			if (CollectionUtil.isNotEmpty(sysDepts)) {
				deptNameIdMap = sysDepts.stream().collect(Collectors.toMap(SysDept::getDeptName, SysDept::getDeptFlow, (k1, k2) -> k2));
			}
			//处理轮转科室的数据
			int importMonNum = 0;
			for (String key : itemMap.keySet()) {
				if (StringUtils.isEmpty(key)) {
					continue;
				}
				try{
					DateTime parse = cn.hutool.core.date.DateUtil.parse(key, "yyyy-MM");
				}catch (Exception e) {
					continue;
				}
				item = new ArrangTdVo();
				item.setInputType("select");
				item.setSchDeptList(sysDepts);
				//key-排班年月日期  val-科室名
				String schDeptName = itemMap.get(key);
				if (StringUtils.isEmpty(schDeptName)) {
					listItem.put(key,item);
					continue;
				}
				String schDeptFlow = deptNameIdMap.get(schDeptName);
				if (StringUtils.isEmpty(schDeptFlow)) {
					//当前导入的科室不是该方案下的轮转科室或者基地管里面没有设置标准科室
					item.setContext(schDeptName);
					item.setTip("科室【"+schDeptName+"】不是该方案下的轮转科室<br/>");
					listItem.put(key,item);
					resp.put("flag",false);
					continue;
				}
				item.setContext(schDeptName);
				item.setDeptFlow(schDeptFlow);
				//正确的也让修改
//				item.setDisable(true);
				listItem.put(key,item);
				importMonNum ++;
			}
			if (nimMonth >0 && (importMonNum<nimMonth)) {
				ArrangTdVo arrangTdVo1 = listItem.get("recurit");
				arrangTdVo1.setTip(StringUtils.isEmpty(arrangTdVo1.getTip())? "当前排班不满足系统配置的最低排班时长【"+nimMonth+" 个月】要求<br/>":
						arrangTdVo1.getTip()+"当前排班不满足系统配置的最低排班时长【"+nimMonth+" 个月】要求<br/>");
				listItem.put("recurit",arrangTdVo1);
				resp.put("flag",false);
			}
			result.add(listItem);
		}
		resp.put("data",result);
		return resp;
	}

	private boolean checkDataUserName(Map<String, ArrangTdVo> listItem,
								   Map<String, String> itemMap,
								   Map<String, SysUser> userMapByName){
		String userName = itemMap.get("学员姓名");
		ArrangTdVo defItem = new ArrangTdVo();
		defItem.setContext(userName);
		defItem.setDisable(true);
		if (StringUtil.isEmpty(userName)) {
			//学员姓名不能为空
			defItem.setTip("学员姓名不能为空<br/>");
			defItem.setInputType("input");
			defItem.setDisable(false);
			listItem.put("学员姓名",defItem);
			itemMap.remove("学员姓名");
			return false;
		}
		//校验学员是否存在
		SysUser user = userMapByName.get(userName);
		if (ObjectUtil.isEmpty(user)) {
			//学员姓名不存在
			defItem.setTip("姓名为【"+userName+"】的学员不存在<br/>");
			defItem.setInputType("input");
			defItem.setDisable(false);
			listItem.put("学员姓名",defItem);
			itemMap.remove("学员姓名");
			return false;
		}
		listItem.put("学员姓名",defItem);
		itemMap.remove("学员姓名");
		//暂存学员信息
		listItem.put("user",new ArrangTdVo(true,user));
		return true;
	}


	private boolean checkDataIdNo(Map<String, ArrangTdVo> listItem,
							   Map<String, String> itemMap,
							   Map<String, SysUser> idNoMap,
							   List<String> idNoList){
		String idNo = itemMap.get("身份证号");
		ArrangTdVo defItem = new ArrangTdVo();
		defItem.setContext(idNo);
		defItem.setDisable(true);
		//判断身份证号是否存在
		if (StringUtil.isEmpty(idNo)) {
			//身份证号不能为空
			defItem.setTip("身份证号不能为空<br/>");
			defItem.setInputType("input");
			defItem.setDisable(false);
			listItem.put("身份证号",defItem);
			itemMap.remove("身份证号");
			return false;
		}
		if (CollectionUtil.isEmpty(idNoList)) {
			defItem.setTip("身份证号不能为空<br/>");
			defItem.setInputType("input");
			defItem.setDisable(false);
			listItem.put("身份证号",defItem);
			itemMap.remove("身份证号");
			return false;
		}
		//判断身份证号是否重复
		Map<String, Long> collect = idNoList.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		if (null != collect.get(idNo) && collect.get(idNo)>1) {
			//身份证号重复
			defItem.setTip("身份证号重复<br/>");
			defItem.setInputType("input");
			defItem.setDisable(false);
			listItem.put("身份证号",defItem);
			itemMap.remove("身份证号");
			return false;
		}
		//校验身份证号是否合法
		boolean validCard = IdcardUtil.isValidCard(idNo);
		if (!validCard) {
			//身份证号输入有误
			defItem.setTip("身份证号不正确<br/>");
			defItem.setInputType("input");
			defItem.setDisable(false);
			listItem.put("身份证号",defItem);
			itemMap.remove("身份证号");
			return false;
		}
		//先过滤一遍
		String userName = listItem.get("学员姓名").getContext();
		if (ObjectUtil.isNotEmpty(listItem.get("user"))) {
			//根据姓名查询到用户
			if (idNo.equalsIgnoreCase(listItem.get("user").getUser().getIdNo())) {
				//身份证号相同后面的就不用校验了
				listItem.put("身份证号",defItem);
				itemMap.remove("身份证号");
				return true;
			}
		}
		//姓名不存在或者同名不同证的情况
		SysUser user = idNoMap.get(idNo);
		if (ObjectUtil.isEmpty(user)) {
			//学员姓名不存在
			defItem.setTip("身份证号为【"+idNo+"】的学员不存在<br/>");
			defItem.setInputType("input");
			defItem.setDisable(false);
			listItem.put("身份证号",defItem);
			itemMap.remove("身份证号");
			return false;
		}
		if (StringUtil.isNotEmpty(userName) &&
		!userName.equalsIgnoreCase(user.getUserName())) {
			//姓名和证件号都能查到，但是姓名和证件号不匹配
			defItem.setTip("身份证号和姓名不匹配<br/>");
			defItem.setInputType("input");
			defItem.setDisable(false);
			listItem.put("身份证号",defItem);
			itemMap.remove("身份证号");
			return false;
		}
		listItem.put("身份证号",defItem);
		itemMap.remove("身份证号");
		ArrangTdVo arrangTdVo = new ArrangTdVo(true,user);
		listItem.put("user",arrangTdVo);
		return true;
	}

	private boolean checkDataSpe(Map<String, ArrangTdVo> listItem,
							   Map<String, String> itemMap){
		String speName = itemMap.get("专业");
		ArrangTdVo defItem = new ArrangTdVo();
		defItem.setContext(speName);
		defItem.setDisable(true);
		if (StringUtil.isEmpty(speName)) {
			//专业不能为空
			defItem.setTip("专业不能为空<br/>");
			listItem.put("专业",defItem);
			itemMap.remove("专业");
			return false;
		}
		listItem.put("专业",defItem);
		itemMap.remove("专业");
		return true;
	}

	private boolean checkDataYear(Map<String, ArrangTdVo> listItem,
							  Map<String, String> itemMap){
		String year = itemMap.get("年限");
		ArrangTdVo defItem = new ArrangTdVo();
		defItem.setContext(year);
		defItem.setDisable(true);
		if (StringUtil.isEmpty(year)) {
			//专业不能为空
			defItem.setTip("年限不能为空<br/>");
			listItem.put("年限",defItem);
			itemMap.remove("年限");
			return false;
		}
		listItem.put("年限",defItem);
		itemMap.remove("年限");
		return true;
	}
	/**
	 * ~~~~~~~~~溺水的鱼~~~~~~~~
	 * @Author: 吴强
	 * @Date: 2024/9/20 15:12
	 * @Description:校验志愿信息，同时如果存在轮转方案，返回轮转方案内的标准科室
	 */
	private List<SysDept> checkDataRecurit(Map<String, ArrangTdVo> listItem,
								  SysUser user,
								  Map<String, List<ResDoctorRecruit>> recruitMap){
		List<SysDept> result = new ArrayList<>();
		ArrangTdVo item = listItem.get("recurit");
		item.setDisable(true);
		if (ObjectUtil.isEmpty(user)) {
			item.setTip(StringUtils.isEmpty(item.getTip())? "系统未分配该学员的培训方案！<br/>":
					item.getTip()+"系统未分配该学员的培训方案！<br/>");
			listItem.put("recurit",item);
			return result;
		}
		String userFlow = user.getUserFlow();
		if (StringUtils.isEmpty(userFlow)) {
			item.setTip(StringUtils.isEmpty(item.getTip())? "系统未分配该学员的培训方案！<br/>":
					item.getTip()+"系统未分配该学员的培训方案！<br/>");
			listItem.put("recurit",item);
			return result;
		}
		if (CollectionUtil.isEmpty(recruitMap)) {
			item.setTip(StringUtils.isEmpty(item.getTip())? "系统未分配该学员的培训方案！<br/>":
					item.getTip()+"系统未分配该学员的培训方案！<br/>");
			listItem.put("recurit",item);
			return result;
		}
		List<ResDoctorRecruit> resDoctorRecruits = recruitMap.get(userFlow);
		if (CollectionUtil.isEmpty(resDoctorRecruits)) {
			item.setTip(StringUtils.isEmpty(item.getTip())? "系统未分配该学员的培训方案！<br/>":
					item.getTip()+"系统未分配该学员的培训方案！<br/>");
			listItem.put("recurit",item);
			return result;
		}
		//存在志愿信息的情况下，判断是否有分配方案
		String rotationFlow = resDoctorRecruits.get(0).getRotationFlow();
		if (StringUtil.isEmpty(rotationFlow)) {
			item.setTip(StringUtils.isEmpty(item.getTip())? "系统未分配该学员的培训方案！<br/>":
					item.getTip()+"系统未分配该学员的培训方案！<br/>");
			listItem.put("recurit",item);
			return result;
		}
		//查询方案中的标准科室
		List<SchRotationDept> bzDeptList = rotationDeptBiz.searchSchRotationDept(rotationFlow);
		if (CollectionUtil.isEmpty(bzDeptList)) {
			item.setTip(StringUtils.isEmpty(item.getTip())? "该轮转方案中暂未配置标准科室！<br/>":
					item.getTip()+"该轮转方案中暂未配置标准科室！<br/>");
			listItem.put("recurit",item);
			return result;
		}
		//根据标准科室查询方案内的轮转科室
		List<String> bzDeptIdList = bzDeptList.stream().map(SchRotationDept::getStandardDeptId).collect(Collectors.toList());
		if (CollectionUtil.isEmpty(bzDeptIdList)) {
			item.setTip(StringUtils.isEmpty(item.getTip())? "该轮转方案中暂未配置标准科室！<br/>":
					item.getTip()+"该轮转方案中暂未配置标准科室！<br/>");
			listItem.put("recurit",item);
			return result;
		}
		listItem.put("recurit",item);
		result = deptBiz.selectByDeptId(rotationFlow,user.getOrgFlow());
		return result;
	}



	private boolean checkDataSessionNum(Map<String, ArrangTdVo> listItem,
							   Map<String, String> itemMap){
		String sessionNum = itemMap.get("年级");
		ArrangTdVo defItem = new ArrangTdVo();
		defItem.setContext(sessionNum);
		defItem.setDisable(true);
		if (StringUtil.isEmpty(sessionNum)) {
			//专业不能为空
			defItem.setTip("年级不能为空<br/>");
			listItem.put("年级",defItem);
			itemMap.remove("年级");
			return false;
		}
		listItem.put("年级",defItem);
		itemMap.remove("年级");
		return true;
	}
	private static Workbook createUserWorkbook(InputStream inS) throws IOException, InvalidFormatException {
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

	private static String _doubleTrans(double d){
		if((double)Math.round(d) - d == 0.0D){
			return String.valueOf((long)d);
		}else{
			return String.valueOf(d);
		}
	}

	// 读取数据
//	private int parseExcelToScheduing(Workbook wb,String rotationFlow) throws ParseException {
//		int succCount = 0;   // 导入成功条数
//
//		HashMap<String, SchRotationDept> deptMaps = new HashMap<>();
//		List<SchRotationDept> rotationDepts = rotationDeptBiz.searchSchRotationDept(rotationFlow);
//		for (SchRotationDept dept : rotationDepts) {
//			deptMaps.put(dept.getStandardDeptName(),dept);
//		}
//		int sheetNum = wb.getNumberOfSheets();
//		if (sheetNum > 0) {
//			Sheet sheet = wb.getSheetAt(0);
//			int row_num = sheet.getLastRowNum();//获取sheet行数，索引0开始
//			if (row_num < 1) {
//				throw new RuntimeException("没有数据！");
//			}
//			Row titleR = sheet.getRow(0);//获取表头
//			int cell_num = titleR.getLastCellNum();//获取表头单元格数
//			List<String> colnames = new ArrayList<>();
//			for (int i = 0; i < cell_num; i++) {
//				colnames.add(titleR.getCell(i).getStringCellValue());
//			}
//			//数据行开始遍历
//			for (int i = 1; i <= row_num; i++) {
//				Row r = sheet.getRow(i);
//				String value = "";
//				Map<String, Object> map = new HashMap<>();
//				for (int j = 0; j < colnames.size(); j++) {
//					Cell cell = r.getCell(j);
//					if (null == cell || com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(cell.toString().trim())) {
//						continue;
//					}
//					if (r.getCell((short) j).getCellType().getCode() == 1) {
//						value = r.getCell((short) j).getStringCellValue();
//					} else {
//						value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
//					}
//					if ("学员姓名".equals(colnames.get(j))) {
//						map.put("doctorName", value);
//					} else if ("身份证号".equals(colnames.get(j))) {
//						map.put("idNo", value);
//					}else if ("标准科室".equals(colnames.get(j))) {
//						map.put("deptName", value);
//					}  else if ("轮转科室(科室[基地名称])".equals(colnames.get(j))) {
//						map.put("schDeptName", value);
//					} else if ("开始时间(yyyyMMdd)".equals(colnames.get(j))) {
//						map.put("schStartDate", value);
//					} else if ("结束时间(yyyyMMdd)".equals(colnames.get(j))) {
//						map.put("schEndDate", value);
//					} else if (("科主任".equals(colnames.get(j)))){
//						map.put("headName",value);
//					} else if (("带教老师".equals(colnames.get(j)))){
//						map.put("teacherName",value);
//					}
//				}
//				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("doctorName")))) {
//					throw new RuntimeException("导入失败！第" + i + "行，学员姓名为空！");
//				}
//				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("idNo")))) {
//					throw new RuntimeException("导入失败！第" + i + "行，身份证号为空！");
//				}
//				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("deptName")))) {
//					throw new RuntimeException("导入失败！第" + i + "行，标准科室为空！");
//				}
//				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("schDeptName")))) {
//					throw new RuntimeException("导入失败！第" + i + "行，轮转科室为空！");
//				}
//				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("schStartDate")))) {
//					throw new RuntimeException("导入失败！第" + i + "行，开始时间为空！");
//				}
//				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("schEndDate")))) {
//					throw new RuntimeException("导入失败！第" + i + "行，结束时间为空！");
//				}
//				if (map.get("schStartDate").toString().compareTo(map.get("schEndDate").toString())>0){
//					throw new RuntimeException("导入失败！第" + i + "行，轮转开始时间必须早于结束时间！");
//				}
//				//禅道3299 科主任带教老师非必填
////				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("headName")))) {
////					throw new RuntimeException("导入失败！第" + i + "行，科主任为空！");
////				}
////				if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("teacherName")))) {
////					throw new RuntimeException("导入失败！第" + i + "行，带教老师为空！");
////				}
//				SysUser user = userBiz.findByIdNo(map.get("idNo").toString());
//				if (null == user){
//					throw new RuntimeException("导入失败！第" + i + "行，查询的学员信息为空！");
//				}
//				ResDoctor doctor = doctorBiz.readDoctor(user.getUserFlow());
//				ResDoctorRecruitExample example = new ResDoctorRecruitExample();
//				example.createCriteria().andRecordStatusEqualTo("Y").andAuditStatusIdEqualTo("Passed").andDoctorFlowEqualTo(doctor.getDoctorFlow());
//				example.setOrderByClause("CREATE_TIME DESC");
//				List<ResDoctorRecruit> recruitList = resDoctorRecruitMapper.selectByExample(example);
//				if (null== recruitList ||  recruitList.size()==0){
//					throw new RuntimeException("导入失败！第" + i + "行，系统为分配该学员的培训方案！");
//				}
//				if (!rotationFlow.equals(recruitList.get(0).getRotationFlow())){
//					throw new RuntimeException("导入失败！第" + i + "行，该学员的培训方案不是导入的方案！");
//				}
//
//				String orgFlow = "";
//				if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
//					orgFlow = doctor.getSecondOrgFlow();
//				} else {
//					orgFlow = doctor.getOrgFlow();
//				}
//				SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
//				List<SysDept> schDeptList = searchSysDeptList(orgFlow, "");
//				HashMap<String, SysDept> schDeptMap = new HashMap<>();
//				for (SysDept dept : schDeptList) {
//					schDeptMap.put(dept.getDeptName(),dept);
//				}
//
//
//				//检测时间是否重叠
//
//				String startDate = DateUtil.transDate(map.get("schStartDate").toString());;
//				String endDate = DateUtil.transDate(map.get("schEndDate").toString());
//				String headName = "";
//				String teacherName = "";
//				if(null != map.get("headName")){
//					headName = map.get("headName").toString();
//				}
//				if(null != map.get("teacherName")){
//					teacherName = map.get("teacherName").toString();
//				}
//				String schDeptName = map.get("schDeptName").toString();
//				if (schDeptName.contains(sysOrg.getOrgName())){
//					schDeptName=schDeptName.substring(0,schDeptName.indexOf("["));
//				}
//				SysDept sysDept = schDeptMap.get(schDeptName);
//				SchRotationDept dept = deptMaps.get(map.get("deptName").toString());
//				List<SchArrangeResult> resultList = checkResultDate(doctor.getDoctorFlow(), startDate, endDate, null, doctor.getRotationFlow());
//				if (null!=resultList && resultList.size()>0){
//					ArrayList<String> list = new ArrayList<>();
//					for (SchArrangeResult result : resultList) {
//						list.add(result.getResultFlow());
//					}
//					//基地导入会覆盖之前的数据
//					resultExtMapper.updateSchArrangeResultToDel(list);
//					resultExtMapper.updateResDoctorSchProcessToDel(list);
//				}
//				SysCfg jsres_is_process = cfgBiz.read("jsres_is_process");
//				if (null != jsres_is_process && GlobalConstant.RECORD_STATUS_Y.equals(jsres_is_process.getCfgValue())){
//					// 获取当前入科时间填写系统限制
//					CheckRotationTime(doctor.getDoctorFlow(), dept.getRecordFlow(), startDate, endDate, "1", "", dept.getSchMonth(), i);
//				}
//
//				//获取当前配置的科主任角色
//				SysUser head = null;
//				String headRole = cfgBiz.read("res_head_role_flow").getCfgValue();
//				if(StringUtil.isNotEmpty(headName)){
//					head = searchTeacherList(sysDept.getDeptFlow(), headRole, headName);
//					if (null==head){
//						throw new RuntimeException("导入失败！第" + i + "行，科主任信息错误！");
//					}
//				}
//				SysUser teacher = null;
//				if(StringUtil.isNotEmpty(teacherName)){
//					String teacherRole = cfgBiz.read("res_teacher_role_flow").getCfgValue();
//					teacher = searchTeacherList(sysDept.getDeptFlow(), teacherRole, teacherName);
//					if (null==teacher){
//						throw new RuntimeException("导入失败！第" + i + "行，带教老师信息错误！");
//					}
//				}
//				SysOrg org=orgBiz.readSysOrg(orgFlow);
//				SchArrangeResult result = new SchArrangeResult();
//				result.setResultFlow(PkUtil.getUUID());
//				result.setArrangeFlow(result.getResultFlow());
//				result.setDoctorFlow(user.getUserFlow());
//				result.setSessionNumber(doctor.getSessionNumber());
//				result.setDoctorName(doctor.getDoctorName());
//				result.setOrgFlow(orgFlow);
//				result.setOrgName(org.getOrgName());
//				result.setSchStartDate(startDate);
//				result.setSchEndDate(endDate);
//				result.setRotationFlow(dept.getRotationFlow());
//
//				result.setDeptFlow(sysDept.getDeptFlow());
//				result.setDeptName(sysDept.getDeptName());
//				result.setSchDeptFlow(sysDept.getDeptFlow());
//
//				String sysDeptName = sysDept.getDeptName();
//				String deptOrgFlow = sysDept.getOrgFlow();
//				if(StringUtil.isNotBlank(deptOrgFlow)){
//					SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
//					if(so!=null && !so.getOrgFlow().equals(orgFlow)){
//						sysDeptName+=("["+so.getOrgName()+"]");
//					}
//				}
//
//				result.setSchDeptName(sysDeptName);
//				result.setStandardDeptId(dept.getStandardDeptId());
//				result.setStandardDeptName(dept.getStandardDeptName());
//				result.setStandardGroupFlow(dept.getGroupFlow());
//				result.setIsRequired(dept.getIsRequired());
//				result.setCreateTime(DateUtil.getCurrDateTime());
//				result.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
//				result.setModifyTime(DateUtil.getCurrDateTime());
//				result.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
//				result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//				Map<String,String> mapTime= new HashMap<>();
//				mapTime.put("startDate",result.getSchStartDate());
//				mapTime.put("endDate",result.getSchEndDate());
//				Double month = TimeUtil.getMonthsBetween(mapTime);
//				String schMonth = String.valueOf(Double.parseDouble(month+""));
//				result.setSchMonth(schMonth);
//				result.setBaseAudit("Passed");
//				schArrangeResultMapper.insert(result);
//
//
//				ResDoctorSchProcess process = new ResDoctorSchProcess();
//				process.setProcessFlow(PkUtil.getUUID());
//				process.setUserFlow(user.getUserFlow());
//				process.setOrgFlow(org.getOrgFlow());
//				process.setOrgName(org.getOrgName());
//				process.setDeptFlow(sysDept.getDeptFlow());
//				process.setDeptName(sysDept.getDeptName());
//				process.setSchDeptFlow(sysDept.getDeptFlow());
//				process.setSchDeptName(sysDept.getDeptName());
//				process.setSchResultFlow(result.getResultFlow());
//				process.setSchStartDate(startDate);
//				process.setSchEndDate(endDate);
//				if(null != teacher){
//					process.setTeacherUserFlow(teacher.getUserFlow());
//					process.setTeacherUserName(teacher.getUserName());
//				}
//				if(null != teacher){
//					process.setHeadUserFlow(head.getUserFlow());
//					process.setHeadUserName(head.getUserName());
//				}
//				process.setCreateTime(DateUtil.getCurrDateTime());
//				process.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
//				process.setModifyTime(DateUtil.getCurrDateTime());
//				process.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
//				process.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
//
//				doctorSchProcessMapper.insertSelective(process);
//				ResSchProcessExpressExample proExample = new ResSchProcessExpressExample();
//				proExample.createCriteria().andOperUserFlowEqualTo(user.getUserFlow()).andSchRotationDeptFlowEqualTo(dept.getRecordFlow())
//						.andRecTypeIdEqualTo(ResRecTypeEnum.AfterSummary.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//				List<ResSchProcessExpress> recList = schProcessExpressMapper.selectByExampleWithBLOBs(proExample);
//				String recContent="";
//				if(recList!=null&&recList.size()>0)
//				{
//					recContent=recList.get(0).getRecContent();
//				}
//				try {
//					updateResultHaveAfter2(dept.getRecordFlow(),user.getUserFlow(),recContent);
//				} catch (DocumentException e) {
//					e.printStackTrace();
//				}
//				succCount++;
//			}
//		}
//		return succCount;
//	}

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
		List<String> successDocIdList = new ArrayList<>();
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
				if(r == null) continue;
				String value = "";
				Map<String, Object> map = new HashMap<>();
				List<Map<String, Object>> deptList = new ArrayList<>();
				for (int j = 0; j < colnames.size(); j++) {
					Cell cell = r.getCell(j);
					if (null == cell || com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(cell.toString().trim())) {
						continue;
					}
					if (r.getCell((short) j).getCellType().getCode() == 1) {
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
									if (trainingTypeId.equals(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId())){
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
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andAuditStatusIdEqualTo("Passed").andDoctorFlowEqualTo(doctor.getDoctorFlow());
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
				//这个是查询机构下的所有的科室
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

                        if (null != jsres_is_process && !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(jsres_is_process.getCfgValue())) {
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
                        result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
                        process.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

						doctorSchProcessMapper.insertSelective(process);

						logger.info("查询proExample信息！！！！！！！");
						ResSchProcessExpressExample proExample = new ResSchProcessExpressExample();
						proExample.createCriteria().andOperUserFlowEqualTo(user.getUserFlow()).andSchRotationDeptFlowEqualTo(dept.getRecordFlow())
                                .andRecTypeIdEqualTo(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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


	//处理后的数据入库保存
	private boolean saveDbImportArrangTool(List<PbImportDataVo> dataList) throws ParseException {
		if (CollectionUtil.isEmpty(dataList))  {
			return true;
		}
		Map<Integer, String> map = new HashMap<>();
		for (int i = 0;i<dataList.size();i++) {
			PbImportDataVo item = dataList.get(i);
			String doctorFlow = item.getDoctorFlow();
			String rotationFlow = item.getRotationFlow();
			String orgFlow = item.getOrgFlow();
			List<String> deptIds = new ArrayList<>();
			Map<String, SysDept> deptMap = new HashMap<>();
			if (CollectionUtil.isNotEmpty(item.getSchDeptList())) {
				deptIds = item.getSchDeptList().stream().map(PbImportDataItem::getSchDeptFlow).collect(Collectors.toList());
				List<SysDept> sysDepts = deptBiz.searchDeptByFlows(deptIds);
				if (CollectionUtil.isNotEmpty(sysDepts)) {
					deptMap = sysDepts.stream().collect(Collectors.toMap(SysDept::getDeptFlow, Function.identity(), (k1, k2) -> k2));
				}
			}
			for (PbImportDataItem deptItem : item.getSchDeptList()) {
				String schDeptFlow = deptItem.getSchDeptFlow();
				if (StringUtils.isEmpty(schDeptFlow)) {
					continue;
				}
				SysDept sysDept = deptMap.get(schDeptFlow);
				if (ObjectUtil.isEmpty(sysDept)) {
					continue;
				}
				SysOrg org = orgBiz.readSysOrg(orgFlow);
				SchArrangeResult result = new SchArrangeResult();
				result.setResultFlow(PkUtil.getUUID());
				result.setArrangeFlow(result.getResultFlow());
				result.setDoctorFlow(doctorFlow);
				result.setSessionNumber(item.getSessionNumber());
				result.setSchYear(item.getSchYear());
				result.setDoctorName(item.getDoctorName());
				result.setOrgFlow(org.getOrgFlow());
				result.setOrgName(org.getOrgName());
				result.setSchStartDate(deptItem.getStartDate());
				result.setSchEndDate(deptItem.getEndDate());
				result.setRotationFlow(rotationFlow);
				result.setRotationName(item.getRotationName());
				result.setDeptFlow(sysDept.getDeptFlow());
				result.setDeptName(sysDept.getDeptName());
				result.setSchDeptFlow(sysDept.getDeptFlow());
				String deptOrgFlow = sysDept.getOrgFlow();
				String sysDeptName = sysDept.getDeptName();
				if (StringUtil.isNotBlank(deptOrgFlow)) {
					SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
					if (so != null && !so.getOrgFlow().equals(orgFlow)) {
						sysDeptName += ("[" + so.getOrgName() + "]");
					}
				}
				result.setSchDeptName(sysDeptName);
				SchAndStandardDeptCfg bzCfg = paiBanImportService.getBzDeptByDeptFlow(sysDept.getDeptFlow());
				if (ObjectUtil.isNotEmpty(bzCfg)) {
					String standardDeptId = bzCfg.getStandardDeptId();
					SchRotationDept rotationInfo = paiBanImportService.getRotationInfo(rotationFlow, standardDeptId);
					if (ObjectUtil.isNotEmpty(rotationInfo)) {
						result.setStandardDeptName(rotationInfo.getStandardDeptName());
						result.setStandardGroupFlow(rotationInfo.getGroupFlow());
						result.setStandardDeptId(rotationInfo.getStandardDeptId());
						result.setIsRequired(rotationInfo.getIsRequired());
					}
				}
				result.setCreateTime(DateUtil.getCurrDateTime());
				result.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				result.setModifyTime(DateUtil.getCurrDateTime());
				result.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				String schStartDate = result.getSchStartDate();
				DateTime startDateTime = cn.hutool.core.date.DateUtil.parseDate(schStartDate);
				String schEndDate = result.getSchEndDate();
				DateTime endDateTime = cn.hutool.core.date.DateUtil.parseDate(schEndDate);
				long dayNum = cn.hutool.core.date.DateUtil.betweenDay(startDateTime, endDateTime, true);
				dayNum = dayNum+1;
				BigDecimal divide = new BigDecimal(dayNum).divide(new BigDecimal("15"), 0, BigDecimal.ROUND_HALF_DOWN);
				BigDecimal multiply = divide.multiply(new BigDecimal("0.5"));
				double v = multiply.doubleValue();
				String schMonth = String.valueOf(Double.parseDouble(v + ""));
				result.setSchMonth(schMonth);
				result.setBaseAudit("Passed");
				//TODO:::111111
				List<SchArrangeResult> resultList = checkResultDate(doctorFlow,
						result.getSchStartDate(),
						result.getSchEndDate(),
						null,
						rotationFlow);
				if (null!=resultList && resultList.size()>0){
					ArrayList<String> list = new ArrayList<>();
					for (SchArrangeResult resultitem : resultList) {
						list.add(resultitem.getResultFlow());
					}
					//基地导入会覆盖之前的数据
					resultExtMapper.updateSchArrangeResultToDel(list);
					resultExtMapper.updateResDoctorSchProcessToDel(list);
				}
				schArrangeResultMapper.insert(result);
				logger.info("查询process信息！！！！！！！");

//				ResDoctorSchProcess process = new ResDoctorSchProcess();
//				process.setProcessFlow(PkUtil.getUUID());
//				process.setUserFlow(item.getUserFlow());
//				process.setOrgFlow(org.getOrgFlow());
//				process.setOrgName(org.getOrgName());
//				process.setDeptFlow(sysDept.getDeptFlow());
//				process.setDeptName(sysDept.getDeptName());
//				process.setSchDeptFlow(sysDept.getDeptFlow());
//				process.setSchDeptName(sysDept.getDeptName());
//				process.setSchResultFlow(result.getResultFlow());
//				process.setSchStartDate(deptItem.getStartDate());
//				process.setSchEndDate(deptItem.getEndDate());
//				process.setCreateTime(DateUtil.getCurrDateTime());
//				process.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
//				process.setModifyTime(DateUtil.getCurrDateTime());
//				process.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
//				process.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//				doctorSchProcessMapper.insertSelective(process);
//				logger.info("查询proExample信息！！！！！！！");
//				ResSchProcessExpressExample proExample = new ResSchProcessExpressExample();
//				proExample.createCriteria().andOperUserFlowEqualTo(item.getUserFlow()).andSchRotationDeptFlowEqualTo(dept.getRecordFlow())
//						.andRecTypeIdEqualTo(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//				List<ResSchProcessExpress> recList = schProcessExpressMapper.selectByExampleWithBLOBs(proExample);
//				String recContent = "";
//				if (recList != null && recList.size() > 0) {
//					recContent = recList.get(0).getRecContent();
//				}
//				try {
//					updateResultHaveAfter2(dept.getRecordFlow(), item.getUserFlow(), recContent);
//				} catch (DocumentException e) {
//					e.printStackTrace();
//				}
			}
		}
		return true;
	}

	public void updateResultHaveAfter2(String schRotationDeptFlow, String operUserFlow, String recContent) throws DocumentException {
        String haveAfterPic = com.pinde.core.common.GlobalConstant.FLAG_N;
		if(StringUtil.isNotBlank(recContent))
		{
			Document document= DocumentHelper.parseText(recContent);
			if (document!=null) {
				Element element=document.getRootElement();
				List<Object> elem=element.elements("image");
				if(elem!=null&&elem.size()>0)
				{
                    haveAfterPic = com.pinde.core.common.GlobalConstant.FLAG_Y;
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
                .andStandardDeptIdEqualTo(rotationDept.getStandardDeptId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
            orgExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowNotEqualTo(orgFlow);

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

	private Map<String, Object> checkReturnData(List<Map<String,ArrangTdVo>> data){
		Map<String, Object> resp = new HashMap<>();
		resp.put("flag",true);
		resp.put("data",data);
		resp.put("code",200);
		if (CollectionUtil.isEmpty(data))  {
			return resp;
		}
		//提取公共的用于校验数据的查询，减少与数据库的连接创建次数
		//根据姓名查询到的数据
		Map<String, SysUser> userMapByName = new HashMap<>();
		List<String> userNameList = data.stream().map(e -> ObjectUtil.isEmpty(e.get("学员姓名"))? "-1":e.get("学员姓名").getContext()).collect(Collectors.toList());
		List<SysUser> sysUsers = userBiz.selectByNamesOrIdNo(userNameList, null);
		if (CollectionUtil.isNotEmpty(sysUsers)) {
			userMapByName = sysUsers.stream().collect(Collectors.toMap(SysUser::getUserName, Function.identity(), (k1, k2) -> k2));
		}
		//根据身份证号查询到数据
		Map<String, SysUser> userMapByIdNo = new HashMap<>();
		List<String> userFlowList = new ArrayList<>();
		List<String> idNoList = data.stream().map(e -> ObjectUtil.isEmpty(e.get("身份证号"))? "-1":e.get("身份证号").getContext()).collect(Collectors.toList());
		Map<String, Long> idNoCount= new HashMap<>();
		if (CollectionUtil.isNotEmpty(idNoList)) {
			//判断身份证号是否重复
			idNoCount = idNoList.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		}
		List<SysUser> idNOs = userBiz.selectByNamesOrIdNo(null, idNoList);
		if (CollectionUtil.isNotEmpty(idNOs)) {
			userMapByIdNo = idNOs.stream().collect(Collectors.toMap(SysUser::getIdNo, Function.identity(), (k1, k2) -> k2));
			userFlowList = idNOs.stream().map(SysUser::getUserFlow).collect(Collectors.toList());
		}
		for (Map<String, ArrangTdVo> item : data) {
			boolean b = checkArrangTdVo(item, userMapByName, userMapByIdNo, idNoCount);
			if (!b) {
				resp.put("flag",false);
				resp.put("code",500);
				resp.put("msg","存在校验不通过的数据，请调整后提交");
			}
		}
		resp.put("data",data);
		return resp;
	}

	@Autowired
	private PaiBanImportService paiBanImportService;
	private boolean checkArrangTdVo(Map<String, ArrangTdVo> item,
									Map<String, SysUser> userMapByName,
									Map<String, SysUser> idNoMap,
									Map<String, Long> idNoCount){
		boolean flag = true;
		ArrangTdVo tipVo = new ArrangTdVo();
		tipVo.setDisable(true);
		item.put("recurit",tipVo);
		SysUser user = new SysUser();
		//=========================校验学员姓名=======================================
		ArrangTdVo userNameInfo = item.get("学员姓名");
		if (ObjectUtil.isEmpty(userNameInfo) || StringUtils.isEmpty(userNameInfo.getContext())) {
			//学员姓名列不能为空，检查一下数据转换是否存在问题
			tipVo = ObjectUtil.isEmpty(tipVo)? new ArrangTdVo():tipVo;
			tipVo.setTip(StringUtils.isEmpty(tipVo.getTip())? "学员姓名不能为空<br/>":
					tipVo.getTip()+"学员姓名不能为空<br/>");
			tipVo.setDisable(false);
			item.put("recurit",tipVo);
			flag = false;
		}
		else {
			boolean nameFlag = checkUserNameItem(userNameInfo, userMapByName);
			//暂存学员信息
			user = userMapByName.get(userNameInfo.getContext());
			item.put("user",new ArrangTdVo(true,user));
			if (!nameFlag) {
				flag = false;
			}
		}
		//=========================身份证信息=======================================
		ArrangTdVo idNoInfo = item.get("身份证号");
		if (ObjectUtil.isEmpty(idNoInfo) || StringUtils.isEmpty(idNoInfo.getContext())) {
			//学员姓名列不能为空，检查一下数据转换是否存在问题
			tipVo = ObjectUtil.isEmpty(tipVo)? new ArrangTdVo():tipVo;
			tipVo.setTip(StringUtils.isEmpty(tipVo.getTip())? "身份证号不能为空<br/>":
					tipVo.getTip()+"身份证号不能为空<br/>");
			tipVo.setDisable(false);
			item.put("recurit",tipVo);
			flag = false;
		}
		else {
			boolean idNOFlag = checkIdNoItem(idNoInfo, idNoMap, idNoCount,userNameInfo.getContext());
			//暂存学员信息
			user = idNoMap.get(idNoInfo.getContext());
			item.put("user",new ArrangTdVo(true,user));
			if (!idNOFlag) {
				flag = false;
			}
		}
		//=========================专业=======================================
		ArrangTdVo spe = item.get("专业");
		if (ObjectUtil.isEmpty(spe) || StringUtils.isEmpty(spe.getContext())) {
			//专业不能为空
			tipVo = ObjectUtil.isEmpty(tipVo)? new ArrangTdVo():tipVo;
			tipVo.setTip(StringUtils.isEmpty(tipVo.getTip())? "专业不能为空<br/>":
					tipVo.getTip()+"; 专业不能为空<br/>");
			tipVo.setDisable(false);
			item.put("recurit",tipVo);
			flag = false;
		}
		//=========================年级=======================================
		ArrangTdVo sessionNumberInfo = item.get("年级");
		if (ObjectUtil.isEmpty(sessionNumberInfo) || StringUtils.isEmpty(sessionNumberInfo.getContext())) {
			//年级不能为空
			tipVo = ObjectUtil.isEmpty(tipVo)? new ArrangTdVo():tipVo;
			tipVo.setTip(StringUtils.isEmpty(tipVo.getTip())? "年级不能为空<br/>":
					tipVo.getTip()+"; 年级不能为空<br/>");
			tipVo.setDisable(false);
			item.put("recurit",tipVo);
			flag = false;
		}else {
			item.put("sessionNumber",sessionNumberInfo);
		}
		//=========================年限=======================================
		ArrangTdVo rotationYearInfo = item.get("年限");
		if (ObjectUtil.isEmpty(rotationYearInfo) || StringUtils.isEmpty(rotationYearInfo.getContext())) {
			//年限不能为空
			tipVo = ObjectUtil.isEmpty(tipVo)? new ArrangTdVo():tipVo;
			tipVo.setTip(StringUtils.isEmpty(tipVo.getTip())? "年限不能为空<br/>":
					tipVo.getTip()+"; 年限不能为空<br/>");
			tipVo.setDisable(false);
			item.put("recurit",tipVo);
			flag = false;
		}else {
			item.put("schYear",rotationYearInfo);
		}
		//=========================校验科室:暂不支持合拼排班的=======================================
		//TODO::还需要可轮转科室列表 和 轮转科室和标准科室对应表同时携带轮转时长sch_rotation_dept
		ResDoctor doctor = new ResDoctor();
		if (ObjectUtil.isNotEmpty(user)) {
			doctor = doctorBiz.readDoctor(user.getUserFlow());
		}
		if (ObjectUtil.isEmpty(doctor)) {
			tipVo = ObjectUtil.isEmpty(tipVo)? new ArrangTdVo():tipVo;
			tipVo.setTip(StringUtils.isEmpty(tipVo.getTip())? "暂无该用户的医师信息<br/>":
					tipVo.getTip()+"暂无该用户的医师信息<br/>");
			tipVo.setDisable(false);
			item.put("recurit",tipVo);
			flag = false;
		}else {
			//填充入库所需的信息
			ArrangTdVo doctorItem = new ArrangTdVo();
			doctorItem.setContext(doctor.getDoctorFlow());
			item.put("doctorFlow",doctorItem);
		}
		if (StringUtils.isEmpty(doctor.getRotationFlow())) {
			tipVo = ObjectUtil.isEmpty(tipVo)? new ArrangTdVo():tipVo;
			tipVo.setTip(StringUtils.isEmpty(tipVo.getTip())? "该医师暂未分配轮转方案<br/>":
					tipVo.getTip()+"该医师暂未分配轮转方案<br/>");
			tipVo.setDisable(false);
			item.put("recurit",tipVo);
		}else {
			//填充入库所需的信息
			ArrangTdVo rotationItem = new ArrangTdVo();
			rotationItem.setContext(doctor.getRotationFlow());
			item.put("rotationFlow",rotationItem);
		}
		//标准科室map
		String orgFlow = "";
		if (StringUtils.isNotEmpty(doctor.getOrgFlow())) {
			orgFlow = doctor.getOrgFlow();
		}else {
			orgFlow = doctor.getSecondOrgFlow();
		}
		if (StringUtils.isNotEmpty(orgFlow)) {
			//填充入库所需的信息
			ArrangTdVo orgItem = new ArrangTdVo();
			orgItem.setContext(orgFlow);
			item.put("orgFlow",orgItem);
		}
		//最短排班时长限制，0表示不限制
		int nimMonth = 0;
		JsresPowerCfg openCfg = jsResPowerCfgBiz.read("process_scheduling_check_" + orgFlow);
		if (ObjectUtil.isNotEmpty(openCfg)) {
			String openVal = openCfg.getCfgValue();
            if (StringUtils.isNotEmpty(openVal) && com.pinde.core.common.GlobalConstant.FLAG_Y.equalsIgnoreCase(openVal)) {
				//限制开启
				JsresPowerCfg minYearNumCfg = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_process_scheduling_time");
				if (ObjectUtil.isNotEmpty(minYearNumCfg)) {
					String cfgValue = minYearNumCfg.getCfgValue();
					if (StringUtils.isNotEmpty(cfgValue)) {
						Integer yearNum = Integer.valueOf(cfgValue);
						nimMonth = yearNum*12;
					}
				}
			}
		}
		//查询方案中的标准科室
//		List<SchRotationDept> bzDeptList = rotationDeptBiz.searchSchRotationDept(doctor.getRotationFlow());
//		Map<String, List<SchRotationDept>> bzGroup = new HashMap<>();
//		if (CollectionUtil.isNotEmpty(bzDeptList)) {
//			bzGroup = bzDeptList.stream().collect(Collectors.groupingBy(SchRotationDept::getStandardDeptId));
//		}
//		//查询其他对我有开放科室记录的机构
//		List<String> deptFlows = deptBiz.relToMeOrgFlow(orgFlow);
//		//查询可轮转的科室
//		List<SchAndStandardDeptCfg> lzDeptList = deptBiz.getSchDeptByBzIds(CollectionUtil.isEmpty(bzGroup)?
//				new ArrayList<>():new ArrayList<>(bzGroup.keySet()), deptFlows,orgFlow);
//		if (CollectionUtil.isNotEmpty(lzDeptList)) {
//			lzDeptList = lzDeptList.stream().distinct().collect(Collectors.toList());
//		}
		List<LzDeptItem> schDeptList = paiBanImportService.getDeptListByRotationId(doctor.getRotationFlow(), orgFlow);
		logger.info("查询出的轮转科室列表：：：：{}",schDeptList.size());
		//定义标准科室和配置的轮转时长配置  标准科室code_i，时长月
		Map<String, Double> peizhiSchTime = new HashMap<>();
		List<SchRotationDept> schRotationDepts = paiBanImportService.bzDeptList(doctor.getRotationFlow());
		if (CollectionUtil.isNotEmpty(schRotationDepts)) {
			for (SchRotationDept bz : schRotationDepts) {
				if (ObjectUtil.isEmpty(bz)) {
					continue;
				}
				if (StringUtils.isEmpty(bz.getStandardDeptId())) {
					continue;
				}
				Double schMon = peizhiSchTime.get(bz.getStandardDeptId());
				if (null == schMon) {
					peizhiSchTime.put(bz.getStandardDeptId(),Double.valueOf("0"));
				}
				if (StringUtils.isEmpty(bz.getSchMonth())) {
					peizhiSchTime.put(bz.getStandardDeptId(),Double.valueOf("0"));
					continue;
				}
				Double v = Double.valueOf(bz.getSchMonth());
				v = null == v? 0:v;
				peizhiSchTime.put(bz.getStandardDeptId(), peizhiSchTime.get(bz.getStandardDeptId())+v);
			}
		}
		Integer pbMonCount = 0;
		//已排标准科室的时长
		Map<String,Double> pbBzTimeMap = new HashMap<>();
		for (String cloumnName : item.keySet()) {
			ArrangTdVo valInfo = item.get(cloumnName);
			//只对表头是‘2020-01’格式的数据进行科室校验
			try{
				DateTime rotationMon = cn.hutool.core.date.DateUtil.parse(cloumnName, "yyyy-MM");
				if (ObjectUtil.isEmpty(rotationMon)) {
					continue;
				}
				if (ObjectUtil.isEmpty(valInfo)) {
					continue;
				}
				valInfo.setPbMonthCount(0);
				valInfo = checkDeptItem(valInfo,schDeptList,orgFlow);
				pbMonCount += valInfo.getPbMonthCount();
				item.put(cloumnName,valInfo);
				if (StringUtils.isNotEmpty(valInfo.getTip())) {
					flag = false;
				}else {
					String bzDeptCodeId = valInfo.getBzDeptCodeId();
					if (StringUtils.isNotEmpty(bzDeptCodeId)) {
						if (bzDeptCodeId.contains(",")) {
							//包含并列关系的
							String[] bzCodeSp = StringUtils.split(bzDeptCodeId,",");
							if (null != bzCodeSp && bzCodeSp.length>0){
								for (int i = 0; i < bzCodeSp.length; i++) {
									String s = bzCodeSp[i];
									if (null == pbBzTimeMap.get(bzDeptCodeId)) {
										pbBzTimeMap.put(bzDeptCodeId,Double.valueOf("0.5"));
									}else {
										pbBzTimeMap.put(bzDeptCodeId,pbBzTimeMap.get(bzDeptCodeId)+Double.valueOf("0.5"));
									}
								}
							}
						}else {
							//不包含并列关系
							if (null == pbBzTimeMap.get(bzDeptCodeId)) {
								pbBzTimeMap.put(bzDeptCodeId,Double.valueOf("1"));
							}else {
								pbBzTimeMap.put(bzDeptCodeId,pbBzTimeMap.get(bzDeptCodeId)+Double.valueOf("1"));
							}
						}
					}
				}
			}catch (Exception e) {
				//非轮转科室列直接跳过
				continue;
			}
		}
		if (nimMonth> 0 && pbMonCount<nimMonth) {
			ArrangTdVo arrangTdVo = item.get("recurit");
			arrangTdVo.setTip(StringUtils.isEmpty(arrangTdVo.getTip())? "排班总时长未满足系统最低排班时长要求！<br/>":
					arrangTdVo.getTip()+"排班总时长未满足系统最低排班时长要求！<br/>");
			item.put("recurit",arrangTdVo);
			flag = false;
		}
		//标准可是轮转时长是否满足
		List<SchRotationDept> bzDeptList = paiBanImportService.bzDeptList(doctor.getRotationFlow());
		boolean b = checkArrangBzTime(peizhiSchTime, bzDeptList, pbBzTimeMap, item);
		if (!b) {
			flag = false;
		}
		return flag;
	}

	private boolean checkArrangBzTime(Map<String, Double> peizhiSchTime,
								   List<SchRotationDept> bzDeptList,
								   Map<String, Double> pbBzTimeMap,
								   Map<String, ArrangTdVo> item) {
		boolean result = true;
		if (CollectionUtil.isEmpty(bzDeptList)) {
			return false;
		}
		if (CollectionUtil.isEmpty(peizhiSchTime)) {
			return false;
		}
		if (CollectionUtil.isEmpty(pbBzTimeMap)) {
			ArrangTdVo tip = item.get("recurit");
			tip.setTip(StringUtils.isEmpty(tip.getTip())? "未识别到符合该方案的排班<br/>":
					tip.getTip()+"未识别到符合该方案的排班<br/>");
			item.put("recurit",tip);
			return false;
		}
		Map<String, String> standDeptName = bzDeptList.stream().collect(Collectors.toMap(SchRotationDept::getStandardDeptId, SchRotationDept::getStandardDeptName, (k1, k2) -> k2));
		Map<String, List<SchRotationDept>> collect = bzDeptList.stream().collect(Collectors.groupingBy(SchRotationDept::getIsRequired));
		//必轮的标准科室
        List<SchRotationDept> blDeptList = collect.get(com.pinde.core.common.GlobalConstant.FLAG_Y);
		//非必轮科室
        List<SchRotationDept> fblDeptList = collect.get(com.pinde.core.common.GlobalConstant.FLAG_N);
		for (String bzCode : pbBzTimeMap.keySet()) {
			if (StringUtils.isEmpty(bzCode)) {
				continue;
			}
			if (bzCode.contains(",")) {
				//这个是一个月排了两个轮转科室的
				String[] split = StringUtils.split(bzCode, ",");
				for (int i = 0; i < split.length; i++) {
					String bzc = split[i];
					//方案中的轮转时长配置-标准科室
					Double v = peizhiSchTime.get(bzc);
					v = null == v? 0:v;
					//实际排班的轮转时长-标准code
					Double lzTime = pbBzTimeMap.get(bzCode);
					lzTime = null == lzTime? Double.valueOf("0"):lzTime;
					v = v-lzTime;
					peizhiSchTime.put(bzCode,v);
					if (v<0) {
						String lzksName = standDeptName.get(split[i]);
						ArrangTdVo tip = item.get("recurit");
						tip.setTip(StringUtils.isEmpty(tip.getTip())? "【"+lzksName+"】科室已超出方案中配置的可轮转时长<br/>":
								tip.getTip()+"【"+lzksName+"】科室已超出方案中配置的可轮转时长<br/>");
						item.put("recurit",tip);
						result = false;
					}
					for (int j = 0; j < blDeptList.size(); j++) {
						if (blDeptList.get(j).getStandardDeptId().equalsIgnoreCase(bzc)) {
							if (v<=0) {
								blDeptList.remove(j);
							}
						}
					}
				}
			}
			else {
				Double v = peizhiSchTime.get(bzCode);
				v = null == v? 0:v;
				//实际排班的轮转时长-标准code
				Double lzTime = pbBzTimeMap.get(bzCode);
				lzTime = null == lzTime? Double.valueOf("0"):lzTime;
				v = v-lzTime;
				peizhiSchTime.put(bzCode,v);
				String lzksName = standDeptName.get(bzCode);
				if (v<0) {
					ArrangTdVo tip = item.get("recurit");
					tip.setTip(StringUtils.isEmpty(tip.getTip())? "【"+lzksName+"】科室已超出方案中配置的可轮转时长<br/>":
							tip.getTip()+"【"+lzksName+"】科室已超出方案中配置的可轮转时长<br/>");
					item.put("recurit",tip);
					result = false;
				}
				for (int j = 0; j < blDeptList.size(); j++) {
					if (blDeptList.get(j).getStandardDeptId().equalsIgnoreCase(bzCode)) {
						if (v<=0) {
							blDeptList.remove(j);
						}
					}
				}
			}
		}
		if (CollectionUtil.isNotEmpty(blDeptList)) {
			//必轮科室没有轮转完
			for (SchRotationDept bl : blDeptList) {
				ArrangTdVo tip = item.get("recurit");
				tip.setTip(StringUtils.isEmpty(tip.getTip())? "标准科室【"+bl.getStandardDeptName()+"】为必轮科室，当前未排满轮转时长<br/>":
						tip.getTip()+"标准科室【"+bl.getStandardDeptName()+"】为必轮科室，当前未排满轮转时长<br/>");
				item.put("recurit",tip);
				result = false;
			}
		}
		return result;
	}

	private boolean checkUserNameItem(ArrangTdVo item,
									  Map<String, SysUser> userMapByName){

		item.setInputType("input");
		item.setDisable(true);
		String userName = item.getContext();
		//校验学员是否存在
		SysUser user = userMapByName.get(userName);
		if (ObjectUtil.isEmpty(user)) {
			//学员姓名不存在
			item.setTip("姓名为【"+userName+"】的学员不存在<br/>");
			item.setInputType("input");
			item.setDisable(false);
			return false;
		}
		return true;
	}

	private boolean checkIdNoItem(ArrangTdVo item,
								  Map<String, SysUser> idNoMap,
								  Map<String, Long> idNoCount,
								  String userName){
		item.setDisable(true);
		item.setInputType("input");
		if (null != idNoCount.get(item.getContext()) && idNoCount.get(item.getContext())>1) {
			//身份证号重复
			item.setTip("身份证号重复<br/>");
			item.setInputType("input");
			item.setDisable(false);
			return false;
		}
		//校验身份证号是否合法
		boolean validCard = IdcardUtil.isValidCard(item.getContext());
		if (!validCard) {
			//身份证号输入有误
			item.setTip("身份证号不正确<br/>");
			item.setInputType("input");
			item.setDisable(false);
			return false;
		}
		//姓名不存在或者同名不同证的情况
		SysUser user = idNoMap.get(item.getContext());
		if (ObjectUtil.isEmpty(user)) {
			//学员姓名不存在
			item.setTip("身份证号为【"+item.getContext()+"】的学员不存在<br/>");
			item.setInputType("input");
			item.setDisable(false);
			return false;
		}
		if (StringUtil.isNotEmpty(userName) &&
				!userName.equalsIgnoreCase(user.getUserName())) {
			//姓名和证件号都能查到，但是姓名和证件号不匹配
			item.setTip("身份证号和姓名不匹配<br/>");
			item.setInputType("input");
			item.setDisable(false);
			return false;
		}
		return true;
	}

	/**
	 * 校验科室
	 * @param lzDeptList :可轮转的科室集合
	 * */
	private ArrangTdVo checkDeptItem(ArrangTdVo item,
									 List<LzDeptItem> lzDeptList,
									 String orgFlow){
		item.setInputType("select");
		item.setTip("");
		item.setDisable(false);
		if (CollectionUtil.isEmpty(lzDeptList)) {
			item.setTip("暂未查询到该方案下可轮转的实际科室！<br/>");
			item.setDisable(false);
			return item;
		}
		if (StringUtils.isEmpty(orgFlow)) {
			item.setTip("该医师暂无机构信息，无法进行排班！<br/>");
			item.setDisable(false);
			return item;
		}
		Map<String, LzDeptItem> lzMap = new HashMap<>();
		List<SysDept> sysDepts = new ArrayList<>();
		SysDept sysDept = new SysDept();
		for (LzDeptItem schAndStandardDeptCfg : lzDeptList) {
			String schDeptName = schAndStandardDeptCfg.getDeptName();
			String orgName = schAndStandardDeptCfg.getOrgName();
			if (StringUtils.isNotEmpty(orgName)) {
				schDeptName = schDeptName+"（"+orgName+"）";
			}
			lzMap.put(schDeptName,schAndStandardDeptCfg);
			sysDept = new SysDept();
			sysDept.setDeptFlow(schAndStandardDeptCfg.getDeptFlow());
			sysDept.setDeptName(schDeptName);
			sysDepts.add(sysDept);
		}
		//设置下拉框选项
		item.setSchDeptList(sysDepts);
		//获取该科室是否在轮转科室内
		String deptName = item.getContext();
		if (StringUtils.isEmpty(deptName)) {
			return item;
		}
		if (deptName.contains(",")) {
			String[] split = deptName.split(",");
			if (null !=  split &&  split.length>0) {
				for (int i = 0; i < split.length; i++) {
					LzDeptItem schAndStandardDeptCfg = lzMap.get(split[i]);
					if (ObjectUtil.isEmpty(schAndStandardDeptCfg)) {
						item.setTip("【"+split[i]+"】科室不是该方案下的轮转科室！<br/>");
						item.setDisable(false);
						return item;
					}
					item.setDeptFlow(StringUtils.isEmpty(item.getDeptFlow())? schAndStandardDeptCfg.getDeptFlow()
							:item.getDeptFlow()+","+schAndStandardDeptCfg.getDeptFlow());
					item.setBzDeptCodeId(StringUtils.isEmpty(item.getBzDeptCodeId())? schAndStandardDeptCfg.getBzDeptCode():
							item.getBzDeptCodeId()+","+schAndStandardDeptCfg.getBzDeptCode());
					item.setBzDeptPbMon(Double.valueOf("0.5"));
				}
			}
			//校验总的轮转时长是否超过最低限制
			item.setPbMonthCount(item.getPbMonthCount()+1);
		}else  {
			LzDeptItem schAndStandardDeptCfg = lzMap.get(deptName);
			if (ObjectUtil.isEmpty(schAndStandardDeptCfg)) {
				item.setTip("【"+deptName+"】科室不是该方案下的轮转科室！<br/>");
				item.setDisable(false);
				return item;
			}
			item.setDeptFlow(schAndStandardDeptCfg.getDeptFlow());
			item.setBzDeptCodeId(schAndStandardDeptCfg.getBzDeptCode());
			item.setBzDeptPbMon(Double.valueOf("1"));
			//校验总的轮转时长是否超过最低限制
			item.setPbMonthCount(item.getPbMonthCount()+1);
		}
		return item;
	}

	@Autowired
	private Environment env;
	@Resource
	private ISchDeptExternalRelBiz deptExternalRelBiz;

	@Override
	public void expertSchTemp(HttpServletRequest request, HttpServletResponse response, String rotationFlow) throws IOException {
//		String modelPath = String.valueOf(env.getProperty("excelModel.path"));
//		String filePath = String.valueOf(env.getProperty("templateFile.path"));
//		modelPath = modelPath+"PbImportModel.xls";
//		URL resource = ResourceLoader.getResource("classpath:excelModel/PbImportModel.xls");
		File file = SpringUtil.getResource("classpath:excelModel/PbImportModel.xls").getFile();
		if (!file.exists()) {
			throw new RuntimeException("未识别到模板文件");
		}
//		System.out.println("文件模板路径1+++++"+file.getPath());
//		System.out.println("文件模板路径：：："+ResourceLoader.getPath("PbImportModel.xls"));
//		filePath = filePath + cn.hutool.core.date.DateUtil.format(new Date(),"yyyyMMDDHHmmss")+"-"+UUID.randomUUID().toString().replaceAll("-","")+".xls";
		//填充模板示例
		Map<String, Object> map = new HashMap<>();
		map.put("userName","例:张三");
		map.put("idNo","例:4312211......");
		map.put("speName","例:内科");
		map.put("sessionNumber","例:2022");
		map.put("trainYear","例:三年");
		//获取当前月份
		for (int i = 0; i < 36; i++) {
			int num = i + 1;
            String key = "M"+ NumberEngEnum.getResult(num);
            DateTime dateTime = cn.hutool.core.date.DateUtil.offsetMonth(new Date(), i);
            String M = cn.hutool.core.date.DateUtil.format(dateTime, "yyyy-MM");
            map.put(key,M);
		}
		//查询所有的专业并处理专业下的所有的科室
//		Map<String, Object> speMap = new HashMap<>();
		Map<String, String> speDeptMap = new HashMap<>();
        List<SysDict> speList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
		List<String> bzDept = new ArrayList<>();
		if (CollectionUtil.isNotEmpty(speList)) {
			//根据专业和年级 年级写死是2023的查询标准科室
			for (int i = 0; i < speList.size(); i++) {
				String replace = StringUtils.replace(speList.get(i).getDictName(), "（", "");
				String replace1 = StringUtils.replace(replace, "）", "");
				String firstLetter = PinyinUtil.getFirstLetter(replace1, "");
				bzDept = new ArrayList<>();
				bzDept = rotationBiz.getBzDeptNameBySpe(speList.get(i).getDictId());
				for (int j = 0; j < 100; j++) {
					String bzDeptKey = "dept_"+firstLetter+"_"+j;
					speDeptMap.put(bzDeptKey,"");
					if (CollectionUtil.isEmpty(bzDept)) {
						continue;
					}
					if (j>=bzDept.size()) {
						continue;
					}
					String deptName = bzDept.get(j);
					speDeptMap.put(bzDeptKey,deptName);
				}
			}
		}

		//查询医院下所有的实际可轮转的科室
		SysDept sysDept = new SysDept();
		sysDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<SysDept> schDeptList = deptBiz.searchDept(sysDept);
		List<SchDeptExternalRel> schDeptExternalRels = deptExternalRelBiz.readSchDeptExtRelByRelOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<JSONObject> schDeptListAll = new ArrayList<>();
		JSONObject schDeptItem = new JSONObject();
		for (SysDept dept : schDeptList) {
			schDeptItem = new JSONObject();
			schDeptItem.put("lunzhuan",dept.getDeptName().trim());
			schDeptListAll.add(schDeptItem);
		}
		//对外开发科室
		if(CollectionUtils.isNotEmpty(schDeptExternalRels)){
			for (SchDeptExternalRel schDeptExternalRel : schDeptExternalRels) {
				schDeptItem = new JSONObject();
				schDeptItem.put("lunzhuan",schDeptExternalRel.getDeptName()+"（"+schDeptExternalRel.getOrgName()+"）");
				schDeptListAll.add(schDeptItem);
			}
		}

		try{
			response.setContentType("application/ms-excel;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			String encodedFileName = null;
			if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				encodedFileName = URLEncoder.encode("排班导入模板.xls", "UTF-8");
			} else {
				encodedFileName = new String("排班导入模板.xls".getBytes("UTF-8"), "ISO8859-1");
			}
			response.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);//设置文件头编码方式和文件名
			ExcelWriter build = EasyExcel.write(response.getOutputStream()).withTemplate(file.getPath()).build();
			//主要的sheet
			WriteSheet sheet1 = EasyExcel.writerSheet("Sheet1").build();
			//辅助的sheet 专业和标准科室
			WriteSheet sheet2 = EasyExcel.writerSheet("Sheet2").build();
			//辅助的sheet 实际科室
			WriteSheet sheet3 = EasyExcel.writerSheet("Sheet3").build();
			build.fill(map,sheet1);
			build.fill(speDeptMap,sheet2);
			build.fill(schDeptListAll,sheet3);
			build.finish();
		}catch (Exception e) {
			throw new RuntimeException("生成模板文件异常");
		}

	}



	private List<JSONObject> updateJSONList(List<JSONObject> data,
											List<String> deptList,
											String keyName){
		if (CollectionUtil.isEmpty(data)) {
			JSONObject item = new JSONObject();
			data.add(item);
		}
		if (CollectionUtil.isEmpty(deptList)) {
			JSONObject item = data.get(0);
			item.put(keyName,"");
			return data;
		}
		for (int i = 0; i < deptList.size(); i++) {
			if (i<data.size()) {
				JSONObject item = data.get(i);
				item.put(keyName,deptList.get(i));
			}else {
				JSONObject item = new JSONObject();
				item.put(keyName,deptList.get(i));
				data.add(item);
			}
		}
		for (JSONObject item : data) {
			for (int i = 0; i < 50; i++) {
				String speDeptKey = "speDept"+i;
				Object o = item.get(speDeptKey);
				if (ObjectUtil.isEmpty(o)) {
					item.put(speDeptKey,"");
				}
			}
		}
		return data;
	}



	/**
	 * ~~~~~~~~~溺水的鱼~~~~~~~~
	 * @Author: 吴强
	 * @Date: 2024/10/26 13:55
	 * @Description: 最新的导入排班的文件解析
	 */
	@Override
	public Map<String, String> importSchedulingAuditExcelCache(MultipartFile file) throws IOException, InvalidFormatException {
		Map<String, String> result = new HashMap<>();
		if (null == file) {
			throw new RuntimeException("导入的文件不能为空");
		}
        String openFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
		JsresPowerCfg openCfg = jsResPowerCfgBiz.read("process_scheduling_check_min_mon_" + GlobalContext.getCurrentUser().getOrgFlow());
		if (ObjectUtil.isNotEmpty(openCfg)) {
            openFlag = StringUtils.isEmpty(openCfg.getCfgValue()) ? com.pinde.core.common.GlobalConstant.FLAG_Y : openCfg.getCfgValue();
		}
		String minMonthNum = "1";
		JsresPowerCfg minMonthCfg = jsResPowerCfgBiz.read("jsres_"+GlobalContext.getCurrentUser().getOrgFlow()+"_org_process_scheduling_time");
		if(ObjectUtil.isNotEmpty(minMonthCfg)) {
			minMonthNum = StringUtils.isEmpty(minMonthCfg.getCfgValue())? "1":minMonthCfg.getCfgValue();
		}
		//查询所有专业对应的标准科室
		Map<String, List<SchRotationDept>> allBzDept = getAllBzDept();
		//查询所有的轮转科室
		List<SysDept> lzDept = getAllLzDept();
		SchedulingAuditRead listen = new SchedulingAuditRead(allBzDept,lzDept);
		EasyExcel.read(file.getInputStream(), listen).sheet().headRowNumber(2).doRead();
		//解析出来的表头
		result.put("head1",JSONUtil.toJsonStr(listen.getOneHeaders()));
		result.put("head2",JSONUtil.toJsonStr(listen.getTwoHeaders()));
		//根据学员姓名、身份证号查询所有的学员信息
		List<SysUser> sysUsers = doctorBiz.listByNameOrIdNo(listen.getExcelUserName(), listen.getExcelIdNo());
		listen.setUserList(sysUsers);
		if (CollectionUtil.isNotEmpty(sysUsers)) {
			List<String> collect = sysUsers.stream().map(SysUser::getUserFlow).collect(Collectors.toList());
			List<ResDoctor> resDoctors = doctorBiz.searchDoctorByuserFlow(collect);
			if(CollectionUtil.isNotEmpty(resDoctors)){
				Map<String, ResDoctor> collect1 = resDoctors.stream().collect(Collectors.toMap(ResDoctor::getDoctorFlow, Function.identity(), (k1, k2) -> k2));
				listen.setDoctorMap(collect1);
			}
		}
		//获取这些学员的历史排班数据
		if (CollectionUtil.isNotEmpty(sysUsers)) {
			Set<String> userIds = sysUsers.stream().map(SysUser::getUserFlow).collect(Collectors.toSet());
			List<SchArrangeResult> schArrangeResults = doctorBiz.listDoctorResult(userIds);
			listen.setStuResultList(schArrangeResults);
		}
		//查询最短排班时间的校验
		listen.getData(false,openFlag,minMonthNum);
		List<PbInfoItem> compareList = listen.getCompareList();
		if (CollectionUtil.isNotEmpty(compareList)) {
			//根据resultFlow集合查询学员提交情况
			Set<String> resultFlows = compareList.stream().map(PbInfoItem::getResultFlow).collect(Collectors.toSet());
			if (CollectionUtil.isNotEmpty(resultFlows)) {
				List<String> resultIds = resultFlows.stream().filter(e -> StringUtils.isNotEmpty(e)).collect(Collectors.toList());
				List<ResRecItem> resRecItems = doctorBiz.resRecCount(resultIds);
				if (CollectionUtil.isNotEmpty(resRecItems)) {
					Map<String, Integer> collect = resRecItems.stream().collect(Collectors.toMap(ResRecItem::getResultFlow, ResRecItem::getCountNum, (k1, k2) -> k2));
					listen.setResRecList(collect);
				}
			}
		}
		List<SchedulingDataModel> data = listen.getData(true,openFlag,minMonthNum);
		result.put("data", JSONUtil.toJsonStr(data));
		return result;
	}


	public Map<String, Object> checkRowData(List<SchedulingDataModel> data) {
		Map<String, Object> result = new HashMap<>();
        String openFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
		JsresPowerCfg openCfg = jsResPowerCfgBiz.read("process_scheduling_check_min_mon_" + GlobalContext.getCurrentUser().getOrgFlow());
		if (ObjectUtil.isNotEmpty(openCfg)) {
            openFlag = StringUtils.isEmpty(openCfg.getCfgValue()) ? com.pinde.core.common.GlobalConstant.FLAG_Y : openCfg.getCfgValue();
		}
		String minMonthNum = "1";
		JsresPowerCfg minMonthCfg = jsResPowerCfgBiz.read("jsres_"+GlobalContext.getCurrentUser().getOrgFlow()+"_org_process_scheduling_time");
		if(ObjectUtil.isNotEmpty(minMonthCfg)) {
			minMonthNum = StringUtils.isEmpty(minMonthCfg.getCfgValue())? "1":minMonthCfg.getCfgValue();
		}
		//查询所有专业对应的标准科室
		Map<String, List<SchRotationDept>> allBzDept = getAllBzDept();
		//查询所有的轮转科室
		List<SysDept> lzDept = getAllLzDept();
		SchedulingAuditCheck entity = new SchedulingAuditCheck(allBzDept,lzDept,data);
		//解析出来的表头
		//根据学员姓名、身份证号查询所有的学员信息
		List<SysUser> sysUsers = doctorBiz.listByNameOrIdNo(entity.getExcelUserName(), entity.getExcelIdNo());
		entity.setUserList(sysUsers);
		if (CollectionUtil.isNotEmpty(sysUsers)) {
			List<String> collect = sysUsers.stream().map(SysUser::getUserFlow).collect(Collectors.toList());
			List<ResDoctor> resDoctors = doctorBiz.searchDoctorByuserFlow(collect);
			if (CollectionUtil.isNotEmpty(resDoctors)) {
				Map<String, ResDoctor> collect1 = resDoctors.stream().collect(Collectors.toMap(ResDoctor::getDoctorFlow, Function.identity(), (k1, k2) -> k2));
				entity.setDoctorMap(collect1);
			}
		}
		//获取这些学员的历史排班数据
		if (CollectionUtil.isNotEmpty(sysUsers)) {
			Set<String> userIds = sysUsers.stream().map(SysUser::getUserFlow).collect(Collectors.toSet());
			List<SchArrangeResult> schArrangeResults = doctorBiz.listDoctorResult(userIds);
			entity.setStuResultList(schArrangeResults);
		}
		entity.getData(false,openFlag,minMonthNum);
		List<PbInfoItem> compareList = entity.getCompareList();
		if (CollectionUtil.isNotEmpty(compareList)) {
			//根据resultFlow集合查询学员提交情况
			Set<String> resultFlows = compareList.stream().map(PbInfoItem::getResultFlow).collect(Collectors.toSet());
			if (CollectionUtil.isNotEmpty(resultFlows)) {
				List<String> resultIds = resultFlows.stream().filter(e -> StringUtils.isNotEmpty(e)).collect(Collectors.toList());
				List<ResRecItem> resRecItems = doctorBiz.resRecCount(resultIds);
				if (CollectionUtil.isNotEmpty(resRecItems)) {
					Map<String, Integer> collect = resRecItems.stream().collect(Collectors.toMap(ResRecItem::getResultFlow, ResRecItem::getCountNum, (k1, k2) -> k2));
					entity.setResRecList(collect);
				}
			}
		}
		result.put("data",entity.getData(true,openFlag,minMonthNum));
		return result;
	}


	/**
	 * ~~~~~~~~~溺水的鱼~~~~~~~~
	 * @Author: 吴强
	 * @Date: 2024/10/30 15:09
	 * @Description: 保存导入数据
	 */
	@Override
	//@Transactional(rollbackFor = Exception.class)
	public Map<String,Object> submitPbImport(List<SchedulingDataModel> data) throws Exception {
		Map<String, Object> result = new HashMap<>();
		result.put("code",200);
		result.put("msg","导入成功");
		if (CollectionUtil.isEmpty(data)) {
			result.put("code",500);
			result.put("msg","暂无导入数据");
			return result;
		}
        String openFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
		JsresPowerCfg openCfg = jsResPowerCfgBiz.read("process_scheduling_check_min_mon_" + GlobalContext.getCurrentUser().getOrgFlow());
		if (ObjectUtil.isNotEmpty(openCfg)) {
            openFlag = StringUtils.isEmpty(openCfg.getCfgValue()) ? com.pinde.core.common.GlobalConstant.FLAG_Y : openCfg.getCfgValue();
		}
		String minMonthNum = "1";
		JsresPowerCfg minMonthCfg = jsResPowerCfgBiz.read("jsres_"+GlobalContext.getCurrentUser().getOrgFlow()+"_org_process_scheduling_time");
		if(ObjectUtil.isNotEmpty(minMonthCfg)) {
			minMonthNum = StringUtils.isEmpty(minMonthCfg.getCfgValue())? "1":minMonthCfg.getCfgValue();
		}
		//查询所有专业对应的标准科室
		Map<String, List<SchRotationDept>> allBzDept = getAllBzDept();
		//查询所有的轮转科室
		List<SysDept> lzDept = getAllLzDept();
		SchedulingAuditCheck entity = new SchedulingAuditCheck(allBzDept,lzDept,data);
		//解析出来的表头
		//根据学员姓名、身份证号查询所有的学员信息
		List<SysUser> sysUsers = doctorBiz.listByNameOrIdNo(entity.getExcelUserName(), entity.getExcelIdNo());
		entity.setUserList(sysUsers);
		if (CollectionUtil.isNotEmpty(sysUsers)) {
			List<String> collect = sysUsers.stream().map(SysUser::getUserFlow).collect(Collectors.toList());
			List<ResDoctor> resDoctors = doctorBiz.searchDoctorByuserFlow(collect);
			if (CollectionUtil.isNotEmpty(resDoctors)) {
				Map<String, ResDoctor> collect1 = resDoctors.stream().collect(Collectors.toMap(ResDoctor::getDoctorFlow, Function.identity(), (k1, k2) -> k2));
				entity.setDoctorMap(collect1);
			}
		}
		//获取这些学员的历史排班数据
		if (CollectionUtil.isNotEmpty(sysUsers)) {
			Set<String> userIds = sysUsers.stream().map(SysUser::getUserFlow).collect(Collectors.toSet());
			List<SchArrangeResult> schArrangeResults = doctorBiz.listDoctorResult(userIds);
			entity.setStuResultList(schArrangeResults);
		}
		entity.getData(false,openFlag,minMonthNum);

		List<PbInfoItem> compareList = entity.getCompareList();
		if (CollectionUtil.isNotEmpty(compareList)) {
			//根据resultFlow集合查询学员提交情况
			Set<String> resultFlows = compareList.stream().map(PbInfoItem::getResultFlow).collect(Collectors.toSet());
			if (CollectionUtil.isNotEmpty(resultFlows)) {
				List<String> resultIds = resultFlows.stream().filter(e -> StringUtils.isNotEmpty(e)).collect(Collectors.toList());
				List<ResRecItem> resRecItems = doctorBiz.resRecCount(resultIds);
				if (CollectionUtil.isNotEmpty(resRecItems)) {
					Map<String, Integer> collect = resRecItems.stream().collect(Collectors.toMap(ResRecItem::getResultFlow, ResRecItem::getCountNum, (k1, k2) -> k2));
					entity.setResRecList(collect);
				}
			}
		}
		//筛选导入的数据
		List<PbInfoItem> collect = compareList.stream().filter(e -> "import".equalsIgnoreCase(e.getType())).collect(Collectors.toList());
		if (CollectionUtil.isEmpty(collect)) {
			result.put("code",500);
			result.put("msg","暂无导入数据");
			return result;
		}
		Map<String, String> checkSchMonMap = new HashMap<>();
		List<PbInfoItem> collect2 = collect.stream().sorted(Comparator.comparing(PbInfoItem::getSchStartDate)).collect(Collectors.toList());
		for (PbInfoItem pbInfoItem : collect2) {
			//遍历导入的数据
			String doctorFlow = pbInfoItem.getDoctorFlow();
			if (StringUtils.isEmpty(doctorFlow)) {
				continue;
			}
            if (pbInfoItem.getRecordStatus().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
				continue;
			}
			//该学员的历史排班数据,从数据库查询历史排班数据
			List<PbInfoItem> dbList = new ArrayList<>();
			Set<String> userIds = new HashSet<>();
			userIds.add(doctorFlow);
			List<SchArrangeResult> schArrangeResults = doctorBiz.listDoctorResult(userIds);
			if (CollectionUtil.isNotEmpty(schArrangeResults)) {
				PbInfoItem resItem = new PbInfoItem();
				for (SchArrangeResult res : schArrangeResults) {
					resItem = new PbInfoItem();
					BeanUtil.copyProperties(res,resItem);
					resItem.setType("db");

					dbList.add(resItem);
				}
			}
			if (CollectionUtil.isEmpty(dbList)) {
				//没有该学员的历史排班记录,直接导入
				savePbWithoutHis(pbInfoItem);
				continue;
			}
			String importDeptFlow = pbInfoItem.getSchDeptFlow();
			DateTime importStart = cn.hutool.core.date.DateUtil.parseDate(pbInfoItem.getSchStartDate());
			DateTime importEnd = cn.hutool.core.date.DateUtil.parseDate(pbInfoItem.getSchEndDate());
			for (PbInfoItem history : dbList) {
				if (StringUtils.isNotEmpty(history.getRecordStatus())
                        && com.pinde.core.common.GlobalConstant.FLAG_N.equals(history.getRecordStatus())) {
					continue;
				}
				String dbSchDeptFlow = history.getSchDeptFlow();
				DateTime hisStart = cn.hutool.core.date.DateUtil.parseDate(history.getSchStartDate());
				DateTime hisEnd = cn.hutool.core.date.DateUtil.parseDate(history.getSchEndDate());
				if (hisStart.compareTo(hisEnd)>=0){
					//删除历史排班
					deletePbResult(history.getResultFlow(),true);
					//移除dbList中的本次历史数据，因为它已经被融合了
                    history.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
					continue;
				}
				//判断是否有提交记录
				Integer resRecLog = entity.getResRecList().get(history.getResultFlow());
				resRecLog = null == resRecLog? 0:resRecLog;
//				(dbSchDeptFlow.equals(importDeptFlow))
				if (true) {
					if (importStart.compareTo(hisStart) == 0 && importEnd.compareTo(hisEnd) == 0) {
						//覆盖
						if (resRecLog>0){
                            pbInfoItem.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
							continue;
						}else {
							pbInfoItem.setResultFlow(history.getResultFlow());
							continue;
						}
					}
					if (importStart.compareTo(hisEnd)==0
							|| importStart.compareTo(cn.hutool.core.date.DateUtil.offsetDay(hisEnd,1))==0) {
						if (dbSchDeptFlow.equals(importDeptFlow)) {
							//本次的开始时间和历史的结束时间衔接，如果科室相同就合并，不相同不处理
							if (resRecLog>0) {
								//有提交记录的就不合并了,调整本次排班数据
								pbInfoItem.setSchStartDate(cn.hutool.core.date.DateUtil.offsetDay(
										cn.hutool.core.date.DateUtil.parseDate(history.getSchEndDate()),
										1).toDateStr());
//								history.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
							}else {
								//导入的开始时间和历史数据的结束时间衔接上
								//导入的开始时间内改为历史的开始时间
								pbInfoItem.setResultFlow(history.getResultFlow());
								pbInfoItem.setSchStartDate(history.getSchStartDate());
								//删除历史排班
								deletePbResult(history.getResultFlow(),true);
								//移除dbList中的本次历史数据，因为它已经被融合了
                                history.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
								continue;
							}
						}
					}
					if (importEnd.compareTo(hisStart)==0
							|| importEnd.compareTo(cn.hutool.core.date.DateUtil.offsetDay(hisStart,-1))==0) {
						//本次排班的结束时间和历史排班的开始时间衔接，如果科室相同就合并
						if (dbSchDeptFlow.equals(importDeptFlow)) {
							if (resRecLog>0){
								//有提交记录的就不合并了,调整本次排班数据
								pbInfoItem.setSchEndDate(cn.hutool.core.date.DateUtil.offsetDay(
										cn.hutool.core.date.DateUtil.parseDate(history.getSchStartDate()),-1).toDateStr());
//								history.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
							}else {
								//导入的尾衔接
								pbInfoItem.setResultFlow(history.getResultFlow());
								//导入的开始时间内改为历史的开始时间
								pbInfoItem.setSchEndDate(history.getSchEndDate());
								//删除历史排班
								deletePbResult(history.getResultFlow(),true);
								//移除dbList中的本次历史数据，因为它已经被融合了
                                history.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
								continue;
							}
						}
					}
					//处理交集
					if (importStart.compareTo(hisStart)>=0 && importStart.compareTo(hisEnd)<0){
						//本次排班的开始部分和历史排班有重合，科室相同就合并，不相同需要覆盖重合部分
						if (dbSchDeptFlow.equals(importDeptFlow)) {
							if (resRecLog>0){
								pbInfoItem.setSchStartDate(cn.hutool.core.date.DateUtil.offsetDay(
										cn.hutool.core.date.DateUtil.parseDate(history.getSchEndDate()),
										1).toDateStr());
//								history.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
							}else {
								pbInfoItem.setResultFlow(history.getResultFlow());
								//首部有交集
								pbInfoItem.setSchStartDate(history.getSchStartDate());
								//删除历史排班
								deletePbResult(history.getResultFlow(),true);
								//移除dbList中的本次历史数据，因为它已经被融合了
                                history.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
								continue;
							}
						}else {
							//科室不相同
							if (resRecLog>0){
								pbInfoItem.setSchStartDate(cn.hutool.core.date.DateUtil.offsetDay(
										cn.hutool.core.date.DateUtil.parseDate(history.getSchEndDate()),
										1).toDateStr());
//								history.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
							}else {
								//首部有交集
//								//更新历史排班
								history.setSchEndDate(cn.hutool.core.date.DateUtil.offsetDay(importStart,-1).toDateStr());
								updateHistoryData(history.getResultFlow(),null,history.getSchEndDate());
								continue;
							}
						}

					}
					//处理交集
					if (importEnd.compareTo(hisStart)>0 && importEnd.compareTo(hisEnd)<=0){
						//本次导入的后半部分和历史排班有交集，科室相同，整合。科室不同覆盖
						if (dbSchDeptFlow.equals(importDeptFlow)) {
							if (resRecLog>0){
								pbInfoItem.setSchEndDate(cn.hutool.core.date.DateUtil.offsetDay(
										cn.hutool.core.date.DateUtil.parseDate(history.getSchStartDate()),-1).toDateStr());
//								history.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
							}else {
								pbInfoItem.setResultFlow(history.getResultFlow());
								//尾部有交集
								pbInfoItem.setSchEndDate(history.getSchEndDate());
								//删除历史排班
								deletePbResult(history.getResultFlow(),true);
								//移除dbList中的本次历史数据，因为它已经被融合了
                                history.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
								continue;
							}
						}else {
							if (resRecLog>0){
								pbInfoItem.setSchEndDate(cn.hutool.core.date.DateUtil.offsetDay(
										cn.hutool.core.date.DateUtil.parseDate(history.getSchStartDate()),-1).toDateStr());
							}else {
								//尾部有交集
								history.setSchStartDate(cn.hutool.core.date.DateUtil.offsetDay(importEnd,1).toDateStr());
								//删除历史排班
								updateHistoryData(history.getResultFlow(),history.getSchStartDate(),null);
								continue;
							}
						}

					}
					//包含历史数据
					if (importStart.compareTo(hisStart)<=0 && importEnd.compareTo(hisEnd)>=0){
						if (dbSchDeptFlow.equals(importDeptFlow)) {
							if (resRecLog>0){
								//被包含的历史排班中存在学员数据
								pbInfoItem.setChaiEnd(cn.hutool.core.date.DateUtil.offsetDay(
										cn.hutool.core.date.DateUtil.parseDate(history.getSchStartDate()),
										-1
								).toDateStr());
								pbInfoItem.setChaiNextStart(cn.hutool.core.date.DateUtil.offsetDay(
										cn.hutool.core.date.DateUtil.parseDate(history.getSchEndDate()),
										1
								).toDateStr());
//								history.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
							}else {
								pbInfoItem.setResultFlow(history.getResultFlow());
								//删除历史排班
								deletePbResult(history.getResultFlow(),true);
								//移除dbList中的本次历史数据，因为它已经被融合了
                                history.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
								continue;
							}
						}else {
							if (resRecLog>0){
								//被包含的历史排班中存在学员数据
								pbInfoItem.setChaiEnd(cn.hutool.core.date.DateUtil.offsetDay(
										cn.hutool.core.date.DateUtil.parseDate(history.getSchStartDate()),
										-1
								).toDateStr());
								pbInfoItem.setChaiNextStart(cn.hutool.core.date.DateUtil.offsetDay(
										cn.hutool.core.date.DateUtil.parseDate(history.getSchEndDate()),
										1
								).toDateStr());
//								history.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
							}else {
								//删除历史排班
								deletePbResult(history.getResultFlow(),true);
								//移除dbList中的本次历史数据，因为它已经被融合了
                                history.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
								continue;
							}
						}

					}
					//被包含
					if (importStart.compareTo(hisStart)>=0 && importEnd.compareTo(hisEnd)<=0){
						if (resRecLog>0){
                            pbInfoItem.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
							continue;
						}
						if (dbSchDeptFlow.equals(importDeptFlow)) {
                            pbInfoItem.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
							continue;
						}
						//不存在提交数据的情况下
						if (!dbSchDeptFlow.equals(importDeptFlow)) {
							//历史数据一拆二
							PbInfoItem newHisTory = new PbInfoItem();
							BeanUtil.copyProperties(history,newHisTory);
							newHisTory.setResultFlow(PkUtil.getUUID());
							history.setSchEndDate(cn.hutool.core.date.DateUtil.offsetDay(importStart,-1).toDateStr());
							updateHistoryData(history.getResultFlow(),history.getSchStartDate(),history.getSchEndDate());
							newHisTory.setSchStartDate(cn.hutool.core.date.DateUtil.offsetDay(importEnd,1).toDateStr());
							dbList.add(newHisTory);
							savePbWithoutHis(newHisTory);
						}

					}
				}
                if (pbInfoItem.getRecordStatus().equalsIgnoreCase(com.pinde.core.common.GlobalConstant.FLAG_Y) &&
				!pbInfoItem.getSchStartDate().equals(pbInfoItem.getSchEndDate())) {
					savePbWithoutHis(pbInfoItem);
				}
			}
			//排完之后可能存在重复的数据
			todoQuChong(doctorFlow,entity.getResRecList());
			//再次检验标准科室轮转时长的限制
			Map<String, String> map = todoCheckSchDate(doctorFlow);
			checkSchMonMap.putAll(map);
//			//根据排班数据，更新process轮转数据
			todoProcessData(doctorFlow);
		}
		if (CollectionUtil.isEmpty(checkSchMonMap)) {
			//导入成功
			return result;
		}
		//是否开启轮转时长校验
		JsresPowerCfg openCfgChek = jsResPowerCfgBiz.read("process_scheduling_check_" + GlobalContext.getCurrentUser().getOrgFlow());
        String checkFlag = com.pinde.core.common.GlobalConstant.FLAG_N;
		if (ObjectUtil.isNotEmpty(openCfgChek)) {
            checkFlag = StringUtils.isEmpty(openCfgChek.getCfgValue()) ? com.pinde.core.common.GlobalConstant.FLAG_N : openCfgChek.getCfgValue();
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(checkFlag)) {
			//开启排班校验
			boolean successFlag = true;
			for (String doctorFlow : checkSchMonMap.keySet()) {
				List<SchedulingDataModel> data1 = entity.getData();
				if (CollectionUtil.isEmpty(data1)) {
					continue;
				}
				for (SchedulingDataModel item : data1) {
					String userId = item.getId();
					if (StringUtils.isEmpty(userId)) {
						continue;
					}
					if (userId.equals(doctorFlow)) {
						String msg = checkSchMonMap.get(doctorFlow);
						if (StringUtils.isNotEmpty(msg)) {
							successFlag = false;
							item.setTip(item.getTip()+msg);
						}
					}
				}
			}
			if (successFlag) {
				return result;
			}
			//存在标准科室轮转时长超出限制的情况，手动回滚事物
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.put("code",500);
			result.put("data",entity.getData());
		}else {
			//不开启轮转时长的校验，导入成功
			return result;
		}
		return result;
	}
	private void todoQuChong(String doctorFlow,Map<String, Integer> resRec){
		if (StringUtils.isEmpty(doctorFlow)) {
			return;
		}
		//获取学员所有的排班
		List<SchArrangeResult> allByDoctorFlow = resultExtMapper.getAllByDoctorFlow(doctorFlow,true);
		if (CollectionUtil.isEmpty(allByDoctorFlow)) {
			return;
		}
		for (SchArrangeResult item : allByDoctorFlow) {
			try{
				String schStartDate = item.getSchStartDate();
				String schEndDate = item.getSchEndDate();
				DateTime dateTime = cn.hutool.core.date.DateUtil.parseDate(schStartDate);
				DateTime dateTime1 = cn.hutool.core.date.DateUtil.parseDate(schEndDate);
				if (dateTime1.compareTo(dateTime)<=0){
					//轮转时间异常的数据
					SchArrangeResult info = arrangeResultMapper.selectByPrimaryKey(item.getResultFlow());
					if (ObjectUtil.isNotEmpty(info)) {
						arrangeResultMapper.deleteByPrimaryKey(info.getResultFlow());
                        item.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
					}
				}
			}catch (Exception e) {
				SchArrangeResult info = arrangeResultMapper.selectByPrimaryKey(item.getResultFlow());
				if (ObjectUtil.isNotEmpty(info)) {
					arrangeResultMapper.deleteByPrimaryKey(info.getResultFlow());
                    item.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
				}
			}
		}
		for (int i = 0; i < allByDoctorFlow.size(); i++) {
			if (i == allByDoctorFlow.size()-1) {
				continue;
			}
			SchArrangeResult item = allByDoctorFlow.get(i);
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(item.getRecordStatus())) {
				continue;
			}
			String schStartDate = item.getSchStartDate();
			String schEndDate = item.getSchEndDate();
			Integer count = resRec.get(item.getResultFlow());
			for (int j = i+1; j < allByDoctorFlow.size(); j++) {
				SchArrangeResult item2 = allByDoctorFlow.get(j);
				String schStartDate2 = item2.getSchStartDate();
				String schEndDate2 = item2.getSchEndDate();
				Integer count2 = resRec.get(item2.getResultFlow());
				if (schStartDate.equals(schStartDate2) && schEndDate.equals(schEndDate2)) {
					//存在排班时间完全一致的情况，判断二者谁有提交数据
					if (null == count || count <=0){
						SchArrangeResult info = arrangeResultMapper.selectByPrimaryKey(item.getResultFlow());
						if (ObjectUtil.isNotEmpty(info)) {
							arrangeResultMapper.deleteByPrimaryKey(info.getResultFlow());
						}
					}else {
						if (null == count2 || count2 <= 0) {
							SchArrangeResult info = arrangeResultMapper.selectByPrimaryKey(item2.getResultFlow());
							if (ObjectUtil.isNotEmpty(info)) {
								arrangeResultMapper.deleteByPrimaryKey(info.getResultFlow());
							}
						}
					}
				}
			}
		}
	}


	private Map<String,String> todoCheckSchDate(String doctorFlow){
		Map<String, String> result = new HashMap<>();
		if (StringUtils.isEmpty(doctorFlow)) {
			result.put(doctorFlow,"学员信息不存在！<br/>");
			return result;
		}
		ResDoctor doctor = doctorBiz.findByFlow(doctorFlow);
		if (ObjectUtil.isEmpty(doctor)) {
			result.put(doctorFlow,"学员信息不存在！<br/>");
			return result;
		}
		//获取学员所有的排班
		List<SchArrangeResult> allByDoctorFlow = resultExtMapper.getAllByDoctorFlow(doctorFlow,true);
		if (CollectionUtil.isEmpty(allByDoctorFlow)) {
			return result;
		}
		Map<String, List<SchArrangeResult>> map = allByDoctorFlow.stream().collect(Collectors.groupingBy(SchArrangeResult::getStandardDeptName));
		List<SchRotationDept> bzDept = rotationBiz.getAllBzDeptListBySpeId(doctor.getTrainingSpeId());
		if (CollectionUtil.isEmpty(bzDept)) {
			result.put(doctorFlow,"【"+doctor.getTrainingSpeName()+"】专业的最新轮转方案中，未查询到相关科室的配置！<br/>");
			return result;
		}
		Map<String, List<SchRotationDept>> bzMap = bzDept.stream().collect(Collectors.groupingBy(SchRotationDept::getStandardDeptName));
		String msg = "";
		for (String bzDeptName : map.keySet()) {
			List<SchArrangeResult> list = map.get(bzDeptName);
			if (CollectionUtil.isEmpty(list)) {
				continue;
			}
			List<SchRotationDept> bzList = bzMap.get(bzDeptName);
			if (CollectionUtil.isEmpty(bzList)) {
				msg = msg + "未查询到标准科室【"+bzDeptName+"】的轮转配置！<br/>";
				continue;
			}
			double bzCount = 0;
			for (SchRotationDept bz : bzList) {
				String schMonth = bz.getSchMonth();
				String schMaxMonth = bz.getSchMaxMonth();
				schMonth = StringUtils.isEmpty(schMonth)? "0":schMonth;
				schMaxMonth = StringUtils.isEmpty(schMaxMonth) ? "0" : schMaxMonth;
				Double min = Double.valueOf(schMonth);
				Double max = Double.valueOf(schMaxMonth);
				min = min<=max? max:min;
				bzCount += min;
			}

			double lzCount = 0;
			for (SchArrangeResult lz : list) {
				String schMonth = lz.getSchMonth();
				schMonth = StringUtils.isEmpty(schMonth)? "0":schMonth;
				lzCount += Double.valueOf(schMonth);
			}
			if (lzCount>bzCount) {
				msg = msg + "标准科室【"+bzDeptName+"】的排班时长已超出方案配置的轮转时长！<br/>";
			}
		}
		result.put(doctorFlow,msg);
		return result;
	}

	private boolean hebingDate(String doctorFlow){
		//合并连续排班并且轮转科室相同的数据
		List<SchArrangeResult> list = resultExtMapper.getAllByDoctorFlow(doctorFlow, true);
		if (CollectionUtil.isEmpty(list)) {
			return false;
		}
		try{
			for (int i = 0; i < list.size(); i++) {
				SchArrangeResult thisItem = list.get(i);
                if (thisItem.getRecordStatus().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
					continue;
				}
				int next = i + 1;
				while (next<list.size()) {
					//判断轮转科室和标准科室是否一致，一致的情况下，把当前的结束时间变更一下，并且删除下一个
					SchArrangeResult nextItem = list.get(next);
					if (!nextItem.getSchDeptFlow().equalsIgnoreCase(thisItem.getSchDeptFlow())) {
						break;
					}
					if (!nextItem.getStandardDeptId().equalsIgnoreCase(thisItem.getStandardDeptId())) {
						break;
					}
					DateTime thisSchEndDate = cn.hutool.core.date.DateUtil.parseDate(thisItem.getSchEndDate());
					DateTime dateTime = cn.hutool.core.date.DateUtil.offsetDay(thisSchEndDate, 1);
					if (!dateTime.toDateStr().equals(nextItem.getSchStartDate())) {
						//日期不连续，不做处理
						break;
					}
					thisItem.setSchEndDate(nextItem.getSchEndDate());
					DateTime startDate = cn.hutool.core.date.DateUtil.parseDate(thisItem.getSchStartDate());
					DateTime endDate = cn.hutool.core.date.DateUtil.parseDate(thisItem.getSchEndDate());
					long l = cn.hutool.core.date.DateUtil.betweenDay(startDate, endDate, false) + 1;
					BigDecimal divide = new BigDecimal(String.valueOf(l)).divide(new BigDecimal("30"), 1, BigDecimal.ROUND_HALF_DOWN);
					thisItem.setSchMonth(divide.toString());
					arrangeResultMapper.updateByPrimaryKeySelective(thisItem);
					arrangeResultMapper.deleteByPrimaryKey(nextItem.getResultFlow());
                    nextItem.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
					next++;
				}
			}
		}catch (Exception e) {
			return false;
		}
		return true;
	}


	private void todoProcessData(String doctorFlow) {
		if (StringUtils.isEmpty(doctorFlow)) {
			return;
		}
		List<SchArrangeResult> resultList = resultExtMapper.getAllByDoctorFlow(doctorFlow, true);
		if (CollectionUtil.isEmpty(resultList)) {
			return;
		}
		Map<String, SchArrangeResult> resultMap = resultList.stream().collect(Collectors.toMap(SchArrangeResult::getResultFlow, Function.identity(), (k1, k2) -> k2));
		List<ResDoctorSchProcess> processList = docSchProcessExtMapper.listByDoctorFlow(doctorFlow);
		if (CollectionUtil.isEmpty(processList)) {
			ResDoctorSchProcess vo = new ResDoctorSchProcess();
			for (SchArrangeResult item : resultList) {
				vo = new ResDoctorSchProcess();
				BeanUtil.copyProperties(item,vo);
				vo.setProcessFlow(PkUtil.getUUID());
				vo.setUserFlow(item.getDoctorFlow());
				vo.setSchResultFlow(item.getResultFlow());
				doctorSchProcessMapper.insertSelective(vo);
			}
			return;
		}

		for (ResDoctorSchProcess item : processList) {
			SchArrangeResult schArrangeResult = resultMap.get(item.getSchResultFlow());
			if (ObjectUtil.isEmpty(schArrangeResult)) {
				//当前轮转数据没有对应的排班
                item.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
				doctorSchProcessMapper.updateByPrimaryKeySelective(item);
				continue;
			}
			//存在对应的排班，检查一下计划轮转时间和排班是否一样
			if (!schArrangeResult.getSchStartDate().equalsIgnoreCase(item.getSchStartDate())) {
				item.setSchStartDate(schArrangeResult.getSchStartDate());
			}
			if (!schArrangeResult.getSchEndDate().equalsIgnoreCase(item.getSchEndDate())) {
				item.setSchEndDate(schArrangeResult.getSchEndDate());
			}
			doctorSchProcessMapper.updateByPrimaryKeySelective(item);
			resultMap.remove(item.getSchResultFlow());
		}

		//检查一下是否有剩余的排班没有分配轮转
		if (CollectionUtil.isEmpty(resultMap)) {
			return;
		}
		ResDoctorSchProcess dbData = new ResDoctorSchProcess();
		for (String resultFlow : resultMap.keySet()) {
			SchArrangeResult schArrangeResult = resultMap.get(resultFlow);
			if (ObjectUtil.isEmpty(schArrangeResult)) {
				continue;
			}
			dbData = new ResDoctorSchProcess();
			BeanUtil.copyProperties(schArrangeResult,dbData);
			dbData.setProcessFlow(PkUtil.getUUID());
			dbData.setUserFlow(schArrangeResult.getDoctorFlow());
			dbData.setSchResultFlow(schArrangeResult.getResultFlow());
			doctorSchProcessMapper.insertSelective(dbData);
		}
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
		ResDoctorSchProcessExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_N)
				.andUserFlowEqualTo(doctorFlow);
		doctorSchProcessMapper.deleteByExample(example);

	}

	private void savePbWithoutHis(PbInfoItem vo){
        if (StringUtils.isNotEmpty(vo.getRecordStatus()) && com.pinde.core.common.GlobalConstant.FLAG_N.equals(vo.getRecordStatus())) {
			return;
		}
		List<PbInfoItem> list = new ArrayList<>();
		if (StringUtils.isNotEmpty(vo.getChaiEnd()) && StringUtils.isNotEmpty(vo.getChaiNextStart())) {
			PbInfoItem v1 = new PbInfoItem();
			PbInfoItem v2 = new PbInfoItem();
			BeanUtil.copyProperties(vo,v1);
			BeanUtil.copyProperties(vo,v2);
			v2.setResultFlow(PkUtil.getUUID());
			v1.setSchEndDate(vo.getChaiEnd());
			list.add(v1);
			v2.setSchStartDate(vo.getChaiNextStart());
			list.add(v2);
		}else {
			list.add(vo);
		}

		for (PbInfoItem item : list) {
			SchArrangeResult result = new SchArrangeResult();
			BeanUtil.copyProperties(item,result);
			//TODO::填充机构信息
			result.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			result.setOrgName(GlobalContext.getCurrentUser().getOrgName());
            result.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
			result.setCreateTime(DateUtil.getCurrDateTime());
			result.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			result.setModifyTime(DateUtil.getCurrDateTime());
			result.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			result.setBaseAudit("Passed");
			//查询是否有标准的方案配置
			List<SchRotationDept> schRotationDepts = rotationDeptBiz.searchSchRotationDept(result.getRotationFlow());
			if (CollectionUtil.isEmpty(schRotationDepts)) {
				return;
			}
			Map<String, List<SchRotationDept>> standMap = schRotationDepts.stream().collect(Collectors.groupingBy(SchRotationDept::getStandardDeptId));
			List<SchRotationDept> schRotationDepts1 = standMap.get(result.getStandardDeptId());
			if (CollectionUtil.isEmpty(schRotationDepts1)) {
				//没有该标准科室的配置，一般不会出现这种情况
				return;
			}
			if (schRotationDepts1.size() ==1) {
				result.setStandardGroupFlow(schRotationDepts1.get(0).getGroupFlow());
				result.setIsRequired(schRotationDepts1.get(0).getIsRequired());
			}
			if (schRotationDepts1.size()>1){
				boolean con = true;
				for (SchRotationDept it : schRotationDepts1) {
					if (!con) {
						continue;
					}
					String standardDeptId = it.getStandardDeptId();
					String groupFlow = it.getGroupFlow();
					String schMonth = StringUtils.isEmpty(it.getSchMonth())? "0":it.getSchMonth();
					Double i = arrangeResultMapper.schMon(result.getDoctorFlow(), result.getRotationFlow(), standardDeptId, groupFlow);
					if (null == i || i<Double.valueOf(schMonth)) {
						result.setStandardGroupFlow(groupFlow);
						result.setIsRequired(it.getIsRequired());
						con = false;
					}
				}
				if (con) {
					result.setStandardGroupFlow(schRotationDepts1.get(schRotationDepts1.size()-1).getGroupFlow());
					result.setIsRequired(schRotationDepts1.get(schRotationDepts1.size()-1).getIsRequired());
				}
			}
			//保存到result表
			SchArrangeResult schArrangeResult = arrangeResultMapper.selectByPrimaryKey(result.getResultFlow());
			boolean flag = false;
			if (ObjectUtil.isEmpty(schArrangeResult)) {
				schArrangeResult = arrangeResultMapper.infoByDeptFlowSchMon(result.getSchDeptFlow(),
						result.getSchStartDate(),
						result.getSchEndDate(),
						result.getDoctorFlow());
				if (ObjectUtil.isEmpty(schArrangeResult)) {
					int i1 = arrangeResultMapper.insertSelective(result);
					flag = i1>0;
				}else {
					result.setResultFlow(schArrangeResult.getResultFlow());
					int i1 = arrangeResultMapper.updateByPrimaryKeySelective(result);
					flag = i1>0;
				}
			}else {
				int i1 = arrangeResultMapper.updateByPrimaryKeySelective(result);
				flag = i1>0;
			}
		}
	}

	private void deletePbResult(String resultFlow,boolean delete){
		if (StringUtils.isEmpty(resultFlow)) {
			return;
		}
		SchArrangeResult schArrangeResult = arrangeResultMapper.selectByPrimaryKey(resultFlow);
		if (ObjectUtil.isEmpty(schArrangeResult)) {
			return;
		}
		//delete是否物理删除
		if (delete) {
			arrangeResultMapper.deleteByPrimaryKey(resultFlow);
		}else {
            schArrangeResult.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
			arrangeResultMapper.updateByPrimaryKeySelective(schArrangeResult);
		}
	}

	private void updateHistoryData(String resultFlow,String schStartDate,String endDate){
		if (StringUtils.isEmpty(resultFlow)) {
			return;
		}
		SchArrangeResult schArrangeResult = arrangeResultMapper.selectByPrimaryKey(resultFlow);
		if (ObjectUtil.isEmpty(schArrangeResult)) {
			return;
		}
		if (StringUtils.isNotEmpty(schStartDate)) {
			schArrangeResult.setSchStartDate(schStartDate);
		}
		if (StringUtils.isNotEmpty(endDate)) {
			schArrangeResult.setSchEndDate(endDate);
		}
		//delete是否物理删除
		arrangeResultMapper.updateByPrimaryKeySelective(schArrangeResult);
	}






	/**
	 * ~~~~~~~~~溺水的鱼~~~~~~~~
	 * @Author: 吴强
	 * @Date: 2024/10/26 17:55
	 * @Description: 查询所有专业的标准科室
	 */
	@Resource
	private RedisTemplate<String,String> redisTemplate;
	private Map<String,List<SchRotationDept>> getAllBzDept(){
		Map<String,List<SchRotationDept>> result = new HashMap<>();
		String val = redisTemplate.opsForValue().get("bzSchDeptList");
		if (StringUtils.isNotEmpty(val)) {
			Map parse = (Map) JSONUtil.parse(val);
			if (CollectionUtil.isNotEmpty(parse)) {
				for (Object key : parse.keySet()) {
					Object mapVal = parse.get((String) key);
					List<SchRotationDept> list = JSONUtil.toList(JSONUtil.toJsonStr(mapVal), SchRotationDept.class);
					result.put((String) key,list);
				}
				return result;
			}
		}
        List<SysDict> speList = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
		if (CollectionUtil.isEmpty(speList)) {
			return result;
		}
		//根据专业和年级 年级写死是2023的查询标准科室
		for (int i = 0; i < speList.size(); i++) {
			List<SchRotationDept> bzDeptNameBySpe = rotationBiz.getAllBzDeptListBySpeId(speList.get(i).getDictId());
			String dictName = speList.get(i).getDictName();
			if (StringUtils.isNotEmpty(dictName)) {
				dictName = StringUtils.replace(dictName,"（","");
				dictName = StringUtils.replace(dictName,"）","");
				dictName = StringUtils.replace(dictName,"(","");
				dictName = StringUtils.replace(dictName,")","");
			}
			result.put(dictName,bzDeptNameBySpe);
		}
		redisTemplate.opsForValue().set("bzSchDeptList",JSONUtil.toJsonStr(result),2, TimeUnit.MINUTES);
		return result;
	}

	private List<SysDept> getAllLzDept(){
		String val = redisTemplate.opsForValue().get("lzSchDeptList");
		if (StringUtils.isNotEmpty(val)) {
			List<SysDept> list = JSONUtil.toList(val, SysDept.class);
			if (CollectionUtil.isNotEmpty(list)) {
				return list;
			}
		}
		List<SysDept> result = new ArrayList<>();
		SysDept sysDept = new SysDept();
		sysDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		result = deptBiz.searchDept(sysDept);
		List<SchDeptExternalRel> schDeptExternalRels = deptExternalRelBiz.readSchDeptExtRelByRelOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		//对外开发科室
		if(CollectionUtils.isNotEmpty(schDeptExternalRels)){
			SysDept selectItem = new SysDept();
			for (SchDeptExternalRel schDeptExternalRel : schDeptExternalRels) {
				selectItem = new SysDept();
				selectItem.setDeptName(schDeptExternalRel.getDeptName()+"（"+schDeptExternalRel.getOrgName()+"）");
				selectItem.setDeptFlow(schDeptExternalRel.getDeptFlow());
				result.add(selectItem);
			}
		}
		redisTemplate.opsForValue().set("lzSchDeptList",JSONUtil.toJsonStr(result),2, TimeUnit.MINUTES);
		return result;
	}
}
