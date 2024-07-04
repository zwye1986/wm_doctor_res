package com.pinde.res.biz.gzdh.impl;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.*;
import com.pinde.res.biz.gzdh.IGzdhKzrBiz;
import com.pinde.res.dao.jswjw.ext.ResDoctorSchProcessExtMapper;
import com.pinde.res.dao.jswjw.ext.SysUserExtMapper;
import com.pinde.res.enums.hbres.ResRecTypeEnum;
import com.pinde.res.enums.stdp.AbsenceTypeEnum;
import com.pinde.res.enums.stdp.RecStatusEnum;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(rollbackFor=Exception.class)
public class GzdhKzrBizImpl implements IGzdhKzrBiz {

	@Autowired
	private ResDoctorSchProcessExtMapper resDoctorProcessExtMapper;
	@Autowired
	private SysUserExtMapper userExtMapper;
	@Autowired
	private ResDoctorMapper doctorMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private ResScoreMapper scoreMapper;
	@Autowired
	private ResDoctorSchProcessMapper resDoctorProcessMapper;
	@Autowired
	private SchDeptMapper schDeptMapper;
	@Autowired
	private ResSchProcessExpressMapper expressMapper;
	@Autowired
	private ResRecMapper resRecMapper;
	@Autowired
	private SchDoctorAbsenceMapper absenceMapper;
	@Autowired
	private ZseyHrKqMonthMapper zseyHrKqMonthMapper;

	@Override
	public List<Map<String, String>> schDoctorSchProcessHead(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.zseyDoctorSchProcessHead(schArrangeResultMap);
	}
	@Override
	public int schDoctorSchProcessHeadCount(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.schDoctorSchProcessHeadCount(schArrangeResultMap);
	}
	@Override
	public int findNeedAuditAbsences(SysUser user) {
		return resDoctorProcessExtMapper.findNeedAuditAbsences(user);
	}
	@Override
	public List<Map<String, String>> schDoctorSchProcessHeadUserList(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.schDoctorSchProcessHeadUserList(schArrangeResultMap);
	}
	@Override
	public List<Map<String, String>> getAbsenceList(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.getAbsenceList(schArrangeResultMap);
	}

	@Override
	public SchDoctorAbsence readAbsence(String absenceFlow) {
		return absenceMapper.selectByPrimaryKey(absenceFlow);
	}

	@Override
	public Map<String, Object> absenceCountDetail(String startDate, String endDate, String flow) {
		Map<String, Object> map=new HashMap<>();
		List<Map<String,Object>> list= resDoctorProcessExtMapper.absenceCountDetail(startDate,endDate,flow);
		if(list!=null)
		{
			for(Map<String, Object> m:list)
			{
				map.put(String.valueOf(m.get("ABSENCE_TYPE_ID")),m.get("QTY"));
			}
		}
		return map;
	}
	@Override
	public int absenceCount(String startDate, String endDate, String flow) {
		return resDoctorProcessExtMapper.absenceCount(startDate,endDate,flow);
	}

	@Override
	public List<Map<String,Object>> getCycleStudents(String userFlow, String stuName, String sessionNumber, String speId, String schDeptFlow) {
		Map<String, Object> map=new HashMap<>();
		map.put("userFlow",userFlow);
		map.put("stuName",stuName);
		map.put("sessionNumber",sessionNumber);
		map.put("speId",speId);
		map.put("schDeptFlow",schDeptFlow);
		return resDoctorProcessExtMapper.getCycleStudents(map);
	}
	@Override
	public List<Map<String,Object>> docSchDeptList(String userFlow, String doctorFlow) {
		Map<String, Object> map=new HashMap<>();
		map.put("userFlow",userFlow);
		map.put("doctorFlow",doctorFlow);
		return resDoctorProcessExtMapper.docSchDeptList(map);
	}

	@Override
	public List<SchDept> readSchDeptList(String userFlow) {
		return resDoctorProcessExtMapper.readSchDeptList(userFlow);
	}

	@Override
	public int saveAuditAbsence(SchDoctorAbsence absence) throws ParseException {
		if(absence!=null)
		{
			//判断是否需要保存考勤记录并保存
			if((absence.getAbsenceTypeId().equals(AbsenceTypeEnum.Yearleave.getId())&&absence.getIntervalDay().equals("1")
					&&"Y".equals(absence.getTeacherAgreeFlag()))
					||("Y".equals(absence.getManagerAgreeFlag()))){ //年假小于1天通过 或者  管理员通过
				SysUser user =sysUserMapper.selectByPrimaryKey(absence.getDoctorFlow());
				ResDoctor resDoctor = doctorMapper.selectByPrimaryKey(absence.getDoctorFlow());
				//获得两个日期间所有日期
				Map<String,List<String>> dateMap = findDates(absence.getStartDate(), absence.getEndDate());
				//录入考勤信息表
				String monthStartDay = absence.getStartDate().substring(0,7);
				String monthEndDay = absence.getEndDate().substring(0,7);
				List<String> months = TimeUtil.getMonthsByTwoMonth(monthStartDay,monthEndDay);

				AbsenceTypeEnum typeEnum = (AbsenceTypeEnum)(EnumUtil.getById(absence.getAbsenceTypeId(), AbsenceTypeEnum.class));
				String code = typeEnum.getCode();
				if(months!=null&&months.size()>0){
					for(String month:months){
						ZseyHrKqMonth zseyHrKqMonth = searchByKqMonthDate(user.getUserFlow(),month);
						ZseyHrKqMonth hrKqMonth = new ZseyHrKqMonth();
						if(null!=zseyHrKqMonth){
							String monthFlow = zseyHrKqMonth.getMonthFlow();
							hrKqMonth.setMonthFlow(monthFlow);
						}
						hrKqMonth.setResType("2");
						hrKqMonth.setUserFlow(user.getUserFlow());
						hrKqMonth.setUserCode(user.getUserCode());
						hrKqMonth.setUserName(user.getUserName());
						hrKqMonth.setDeptFlow(user.getDeptFlow());
						hrKqMonth.setDeptName(user.getDeptName());
						hrKqMonth.setInDate(resDoctor.getSessionNumber());
						hrKqMonth.setIdNo(user.getIdNo());
						hrKqMonth.setKqDate(month);
						hrKqMonth.setKqDeptFlow(absence.getSchDeptFlow());
						hrKqMonth.setKqDeptName(absence.getSchDeptName());
						List<String> dateList=dateMap.get(month);
						if(dateList!=null) {
							for (String date : dateList) {
								if (month.equals(date.substring(0, 7))) {
									String day = date.substring(8, 10);
									switch (day) {
										case "01":
											hrKqMonth.setM01(code);
											break;
										case "02":
											hrKqMonth.setM02(code);
											break;
										case "03":
											hrKqMonth.setM03(code);
											break;
										case "04":
											hrKqMonth.setM04(code);
											break;
										case "05":
											hrKqMonth.setM05(code);
											break;
										case "06":
											hrKqMonth.setM06(code);
											break;
										case "07":
											hrKqMonth.setM07(code);
											break;
										case "08":
											hrKqMonth.setM08(code);
											break;
										case "09":
											hrKqMonth.setM09(code);
											break;
										case "10":
											hrKqMonth.setM10(code);
											break;
										case "11":
											hrKqMonth.setM11(code);
											break;
										case "12":
											hrKqMonth.setM12(code);
											break;
										case "13":
											hrKqMonth.setM13(code);
											break;
										case "14":
											hrKqMonth.setM14(code);
											break;
										case "15":
											hrKqMonth.setM15(code);
											break;
										case "16":
											hrKqMonth.setM16(code);
											break;
										case "17":
											hrKqMonth.setM17(code);
											break;
										case "18":
											hrKqMonth.setM18(code);
											break;
										case "19":
											hrKqMonth.setM19(code);
											break;
										case "20":
											hrKqMonth.setM20(code);
											break;
										case "21":
											hrKqMonth.setM21(code);
											break;
										case "22":
											hrKqMonth.setM22(code);
											break;
										case "23":
											hrKqMonth.setM23(code);
											break;
										case "24":
											hrKqMonth.setM24(code);
											break;
										case "25":
											hrKqMonth.setM25(code);
											break;
										case "26":
											hrKqMonth.setM26(code);
											break;
										case "27":
											hrKqMonth.setM27(code);
											break;
										case "28":
											hrKqMonth.setM28(code);
											break;
										case "29":
											hrKqMonth.setM29(code);
											break;
										case "30":
											hrKqMonth.setM30(code);
											break;
										case "31":
											hrKqMonth.setM31(code);
											break;
									}
								}
							}
						}
						editZseyHrKqMonth(hrKqMonth,absence.getTeacherFlow());
					}
				}
			}
			return editSchDoctorAbsence(absence);
		}
		return 0;
	}
	@Override
	public int saveAuditRepealAbsence(SchDoctorAbsence absence) throws ParseException {
		if(absence!=null) {
			//实际销假时间比请假结果时间少，需要将报表处理一下
			if (StringUtil.isNotBlank(absence.getRepealAbsenceDay())&&absence.getRepealAbsenceDay().compareTo(absence.getEndDate()) < 0) {
				SysUser user = sysUserMapper.selectByPrimaryKey(absence.getDoctorFlow());
				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				Date date = sdf.parse(absence.getRepealAbsenceDay());
				c.setTime(date);
				c.add(Calendar.DATE, 1);
				//获得两个日期间所有日期
				Map<String, List<String>> dateMap = findDates(sdf.format(c.getTime()), absence.getEndDate());
				//录入考勤信息表
				String monthStartDay = absence.getStartDate().substring(0, 7);
				String monthEndDay = absence.getEndDate().substring(0, 7);
				List<String> months = TimeUtil.getMonthsByTwoMonth(monthStartDay, monthEndDay);
				if (months != null && months.size() > 0) {
					for (String month : months) {
						ZseyHrKqMonth zseyHrKqMonth = searchByKqMonthDate(user.getUserFlow(), month);
						if (null != zseyHrKqMonth) {
							List<String> dateList = dateMap.get(month);
							if (dateList != null) {
								for (String date2 : dateList) {
									if (month.equals(date2.substring(0, 7))) {
										switch (date2.substring(8, 10)) {
											case "01":
												zseyHrKqMonth.setM01("00");
												break;
											case "02":
												zseyHrKqMonth.setM02("00");
												break;
											case "03":
												zseyHrKqMonth.setM03("00");
												break;
											case "04":
												zseyHrKqMonth.setM04("00");
												break;
											case "05":
												zseyHrKqMonth.setM05("00");
												break;
											case "06":
												zseyHrKqMonth.setM06("00");
												break;
											case "07":
												zseyHrKqMonth.setM07("00");
												break;
											case "08":
												zseyHrKqMonth.setM08("00");
												break;
											case "09":
												zseyHrKqMonth.setM09("00");
												break;
											case "10":
												zseyHrKqMonth.setM10("00");
												break;
											case "11":
												zseyHrKqMonth.setM11("00");
												break;
											case "12":
												zseyHrKqMonth.setM12("00");
												break;
											case "13":
												zseyHrKqMonth.setM13("00");
												break;
											case "14":
												zseyHrKqMonth.setM14("00");
												break;
											case "15":
												zseyHrKqMonth.setM15("00");
												break;
											case "16":
												zseyHrKqMonth.setM16("00");
												break;
											case "17":
												zseyHrKqMonth.setM17("00");
												break;
											case "18":
												zseyHrKqMonth.setM18("00");
												break;
											case "19":
												zseyHrKqMonth.setM19("00");
												break;
											case "20":
												zseyHrKqMonth.setM20("00");
												break;
											case "21":
												zseyHrKqMonth.setM21("00");
												break;
											case "22":
												zseyHrKqMonth.setM22("00");
												break;
											case "23":
												zseyHrKqMonth.setM23("00");
												break;
											case "24":
												zseyHrKqMonth.setM24("00");
												break;
											case "25":
												zseyHrKqMonth.setM25("00");
												break;
											case "26":
												zseyHrKqMonth.setM26("00");
												break;
											case "27":
												zseyHrKqMonth.setM27("00");
												break;
											case "28":
												zseyHrKqMonth.setM28("00");
												break;
											case "29":
												zseyHrKqMonth.setM29("00");
												break;
											case "30":
												zseyHrKqMonth.setM30("00");
												break;
											case "31":
												zseyHrKqMonth.setM31("00");
												break;
										}
									}
								}
								editZseyHrKqMonth(zseyHrKqMonth, absence.getTeacherFlow());
							}
						}
					}
				}
			}
			absence.setEndDate(absence.getRepealAbsenceDay());
			absence.setIntervalDay((DateUtil.signDaysBetweenTowDate(absence.getStartDate(),absence.getEndDate())+1)+"");
			return editSchDoctorAbsence(absence);
		}
		return 0;
	}


	private int updateZseyHrKqMonth(ZseyHrKqMonth hrKqMonth,String userFlow) {
		if(hrKqMonth!=null){
			if(StringUtil.isNotBlank(hrKqMonth.getMonthFlow())){
				hrKqMonth.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				hrKqMonth.setModifyUserFlow(userFlow);
				hrKqMonth.setModifyTime(DateUtil.getCurrDateTime());
				return zseyHrKqMonthMapper.updateByPrimaryKey(hrKqMonth);
			}
		}
		return 0;
	}
	private int editZseyHrKqMonth(ZseyHrKqMonth hrKqMonth,String userFlow) {
		if(hrKqMonth!=null){
			if(StringUtil.isNotBlank(hrKqMonth.getMonthFlow())){
				hrKqMonth.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				hrKqMonth.setModifyUserFlow(userFlow);
				hrKqMonth.setModifyTime(DateUtil.getCurrDateTime());
				return zseyHrKqMonthMapper.updateByPrimaryKeySelective(hrKqMonth);
			}else{
				hrKqMonth.setMonthFlow(PkUtil.getUUID());
				hrKqMonth.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				hrKqMonth.setCreateUserFlow(userFlow);
				hrKqMonth.setCreateTime(DateUtil.getCurrDateTime());
				hrKqMonth.setModifyUserFlow(userFlow);
				hrKqMonth.setModifyTime(DateUtil.getCurrDateTime());
				return zseyHrKqMonthMapper.insertSelective(hrKqMonth);
			}
		}
		return 0;
	}

	private int editSchDoctorAbsence(SchDoctorAbsence absence) {
		if(absence!=null){
			if(StringUtil.isNotBlank(absence.getAbsenceFlow())){
				absence.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				absence.setModifyUserFlow(absence.getTeacherFlow());
				absence.setModifyTime(DateUtil.getCurrDateTime());
				return absenceMapper.updateByPrimaryKeySelective(absence);
			}else{
				absence.setAbsenceFlow(PkUtil.getUUID());
				absence.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				absence.setCreateUserFlow(absence.getTeacherFlow());
				absence.setCreateTime(DateUtil.getCurrDateTime());
				absence.setModifyUserFlow(absence.getTeacherFlow());
				absence.setModifyTime(DateUtil.getCurrDateTime());
				return absenceMapper.insertSelective(absence);
			}
		}
		return 0;
	}

	private ZseyHrKqMonth searchByKqMonthDate(String userFlow, String month) {
		ZseyHrKqMonthExample example=new ZseyHrKqMonthExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andUserFlowEqualTo(userFlow).andKqDateEqualTo(month);
		List<ZseyHrKqMonth> list=zseyHrKqMonthMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	public static Map<String,List<String>> findDates(String startDate, String endDate) throws ParseException {
		Map<String, List<String>> map = new HashMap<>();
		if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			//开始时间加1个自然月
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while (startDate.compareTo(endDate) <= 0) {
				String month = startDate.substring(0, 7);
				List<String> list = map.get(month);
				if (list == null) list = new ArrayList<>();
				list.add(startDate);
				map.put(month, list);
				Date date = sdf.parse(startDate);
				c.setTime(date);
				c.add(Calendar.DATE, 1);
				startDate = sdf.format(c.getTime());
			}
		}
		return map;
	}

	public static void main(String []args) throws ParseException {
		Map<String,List<String>> list=findDates("2017-11-01","2017-12-20");
		//System.out.println(JSON.toJSONString(list));
		List<String> months = TimeUtil.getMonthsByTwoMonth("2017-11","2017-12");
		//System.out.println(JSON.toJSONString(months));
	}
	@Override
	public List<SysUser> teacherRoleCheckUser(String deptFlow, String role,String userName, String userFlow) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sysDeptFlow", deptFlow);
		map.put("roleFlow", role);
		map.put("userName", userName);
		map.put("userFlow", userFlow);
		List<SysUser> sysUserList=userExtMapper.teacherRoleCheckUser(map);
		return sysUserList;
	}

	@Override
	public List<ResDoctorSchProcess> searchProcessByDoctorNew(Map<String, Object> paramMap) {
		return resDoctorProcessExtMapper.searchProcessByDoctorNew(paramMap);
	}

	@Override
	public List<SysUser> searchSysUserByuserFlows(List<String> userFlows) {
		if(userFlows != null && !userFlows.isEmpty()){
			SysUserExample sysUserExample = new SysUserExample();
			sysUserExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andUserFlowIn(userFlows);
			return sysUserMapper.selectByExample(sysUserExample);
		}
		return null;
	}

	@Override
	public List<ResDoctor> searchDoctorByuserFlow(List<String> userFlows) {
		ResDoctorExample example = new ResDoctorExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowIn(userFlows);
		return doctorMapper.selectByExample(example);
	}

	@Override
	public Map<String, String> parseRecContent(String content) {
		Map<String,String> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, String>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				Element afterEvaluation = rootElement.element(GlobalConstant.RES_ROLE_SCOPE_MANAGER+ResRecTypeEnum.AfterEvaluation.getId());
				if(afterEvaluation==null){
					afterEvaluation = rootElement.element(GlobalConstant.RES_ROLE_SCOPE_HEAD+ResRecTypeEnum.AfterEvaluation.getId());
				}
				if(afterEvaluation==null){
					afterEvaluation = rootElement.element(GlobalConstant.RES_ROLE_SCOPE_TEACHER+ ResRecTypeEnum.AfterEvaluation.getId());
				}
				List<Element> elements = null;
				if(afterEvaluation!=null){
					elements = afterEvaluation.elements();
				}else{
					elements = rootElement.elements();
				}
				for(Element element : elements){
					List<Node> valueNodes = element.selectNodes("value");
					if(valueNodes != null && !valueNodes.isEmpty()){
						String value = "";
						for(Node node : valueNodes){
							if(StringUtil.isNotBlank(value)){
								value+=",";
							}
							value += node.getText();
						}
						formDataMap.put(element.getName(), value);
					}else {
						String isSelect = element.attributeValue("id");
						if(StringUtil.isNotBlank(isSelect)){
							formDataMap.put(element.getName()+"_id",isSelect);
							formDataMap.put(element.getName(),element.getText());
						}else{
							formDataMap.put(element.getName(), element.getText());
						}
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return formDataMap;
	}

	@Override
	public ResScore getScoreByProcess(String processFlow) {
		if(StringUtil.isNotBlank(processFlow)){
			ResScoreExample example=new ResScoreExample();
			ResScoreExample.Criteria criteria= example.createCriteria();
			criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andProcessFlowEqualTo(processFlow);
			List<ResScore> scores = scoreMapper.selectByExample(example);
			if(scores==null || scores.isEmpty()){
				return null;
			}
			return scores.get(0);
		}
		return null;
	}

	@Override
	public ResDoctorSchProcess read(String processFlow) {
		ResDoctorSchProcess process = null;
		if(StringUtil.isNotBlank(processFlow)){
			process = this.resDoctorProcessMapper.selectByPrimaryKey(processFlow);
		}
		return process;
	}

	@Override
	public SchDept readSchDept(String schDeptFlow) {
		return schDeptMapper.selectByPrimaryKey(schDeptFlow);
	}

	@Override
	public int schProcessStudentDistinctQuery(String deptFlow, String userFlow,String isOpen) {
		return resDoctorProcessExtMapper.hbresSchProcessStudent(deptFlow,userFlow,isOpen);
	}

	@Override
	public void auditDate(String userFlow, String dataFlow) {
		if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(dataFlow)){
			ResSchProcessExpress rec = new ResSchProcessExpress();
			rec.setRecFlow(dataFlow);
			rec.setHeadAuditStatusId(RecStatusEnum.HeadAuditY.getId());
			rec.setHeadAuditStatusName(RecStatusEnum.HeadAuditY.getName());
			rec.setHeadAuditUserFlow(userFlow);
			SysUser user = sysUserMapper.selectByPrimaryKey(userFlow);
			if(user!=null){
				rec.setHeadAuditUserName(user.getUserName());
			}
			rec.setHeadAuditTime(DateUtil.getCurrDateTime());
			expressMapper.updateByPrimaryKeySelective(rec);
		}
	}

	@Override
	public ResDoctorSchProcess searchByResultFlow(String resultFlow) {
		ResDoctorSchProcess process = null;
		if(StringUtil.isNotBlank(resultFlow)){
			ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSchResultFlowEqualTo(resultFlow);
			List<ResDoctorSchProcess> processList = this.resDoctorProcessMapper.selectByExample(example);
			if(processList!=null&&!processList.isEmpty()){
				process = processList.get(0);
			}
		}
		return process;
	}

	@Override
	public ResRec queryResRec(String processFlow, String operUserFlow, String recTypeId) {
		ResRec rec=null;
		ResRecExample example = new ResRecExample();
		ResRecExample.Criteria create=example.createCriteria();
		create.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(processFlow)) {
			create.andProcessFlowEqualTo(processFlow);
		}
		if (StringUtil.isNotBlank(operUserFlow)) {
			create.andOperUserFlowEqualTo(operUserFlow);
		}
		if (StringUtil.isNotBlank(recTypeId)) {
			create.andRecTypeIdEqualTo(recTypeId);
		}
		List<ResRec> list = resRecMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() >0) {
			rec = list.get(0);
		}
		return rec;
	}
}
  