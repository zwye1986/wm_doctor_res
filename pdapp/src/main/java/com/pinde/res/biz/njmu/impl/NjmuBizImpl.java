package com.pinde.res.biz.njmu.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.pinde.res.dao.nydyjs.ext.UserInfoExtMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.njmu.INjmuBiz;
import com.pinde.res.dao.njmu.base.CasediscussMapper;
import com.pinde.res.dao.njmu.base.DicitemMapper;
import com.pinde.res.dao.njmu.base.OutsecbriefMapper;
import com.pinde.res.dao.njmu.base.RecBighistoryMapper;
import com.pinde.res.dao.njmu.base.RecCaseclassMapper;
import com.pinde.res.dao.njmu.base.RecOperateskillMapper;
import com.pinde.res.dao.njmu.base.RecPosskillMapper;
import com.pinde.res.dao.njmu.base.UserinfoMapper;
import com.pinde.res.dao.njmu.base.UserrealcyclesecMapper;
import com.pinde.res.dao.njmu.base.UserrealtecMapper;
import com.pinde.res.dao.njmu.base.UsersectionmanagerMapper;
import com.pinde.res.dao.njmu.base.WfworkMapper;
import com.pinde.res.dao.njmu.base.WorklogMapper;
import com.pinde.res.dao.njmu.ext.AddDataMapper;
import com.pinde.res.dao.njmu.ext.CanAddSummaryMapper;
import com.pinde.res.dao.njmu.ext.CategoryProgressMapper;
import com.pinde.res.dao.njmu.ext.DataListMapper;
import com.pinde.res.dao.njmu.ext.DeptHeadListMapper;
import com.pinde.res.dao.njmu.ext.DeptListMapper;
import com.pinde.res.dao.njmu.ext.EnterDeptMapper;
import com.pinde.res.dao.njmu.ext.GlobalProgressMapper;
import com.pinde.res.dao.njmu.ext.InputListMapper;
import com.pinde.res.dao.njmu.ext.TeacherListMapper;
import com.pinde.res.enums.njmu.DataStatusEnum;
import com.pinde.res.enums.njmu.WorkLogTypeEnum;
import com.pinde.res.model.njmu.mo.Casediscuss;
import com.pinde.res.model.njmu.mo.Dicitem;
import com.pinde.res.model.njmu.mo.DicitemExample;
import com.pinde.res.model.njmu.mo.Outsecbrief;
import com.pinde.res.model.njmu.mo.OutsecbriefExample;
import com.pinde.res.model.njmu.mo.RecBighistory;
import com.pinde.res.model.njmu.mo.RecCaseclass;
import com.pinde.res.model.njmu.mo.RecOperateskill;
import com.pinde.res.model.njmu.mo.RecPosskill;
import com.pinde.res.model.njmu.mo.Userinfo;
import com.pinde.res.model.njmu.mo.UserinfoExample;
import com.pinde.res.model.njmu.mo.Userrealcyclesec;
import com.pinde.res.model.njmu.mo.UserrealcyclesecExample;
import com.pinde.res.model.njmu.mo.Userrealtec;
import com.pinde.res.model.njmu.mo.Usersectionmanager;
import com.pinde.res.model.njmu.mo.WfworkExample;
import com.pinde.res.model.njmu.mo.Worklog;
import com.pinde.res.model.njmu.mo.WorklogExample;

@Service
@Transactional(rollbackFor=Exception.class)
public class NjmuBizImpl implements INjmuBiz{

	@Resource
	private UserinfoMapper userinfoMapper;
	@Resource
	private DeptListMapper deptListMapper;
	@Resource
	private TeacherListMapper teacherListMapper;
	@Resource
	private DeptHeadListMapper deptHeadListMapper;
	@Resource
	private EnterDeptMapper enterDeptMapper;
	@Resource
	private UserrealcyclesecMapper userrealcyclesecMapper;
	@Resource
	private UserrealtecMapper userrealtecMapper;
	@Resource
	private UsersectionmanagerMapper usersectionmanagerMapper;
	@Resource
	private GlobalProgressMapper globalProgressMapper;
	@Resource
	private CategoryProgressMapper categoryProgressMapper;
	@Resource
	private DataListMapper dataListMapper;
	@Resource
	private AddDataMapper addDataMapper;
	@Resource
	private DicitemMapper dicitemMapper;
	@Resource
	private InputListMapper inputListMapper;
	@Resource
	private RecBighistoryMapper recBighistoryMapper;
	@Resource
	private RecCaseclassMapper recCaseclassMapper;
	@Resource
	private RecOperateskillMapper recOperateskillMapper;
	@Resource
	private RecPosskillMapper recPosskillMapper;
	@Resource
	private CasediscussMapper casediscussMapper;
	@Resource
	private OutsecbriefMapper outsecbriefMapper;
	@Resource
	private WfworkMapper wfworkMapper;
	@Resource
	private CanAddSummaryMapper canAddSummaryMapper;
	@Resource
	private WorklogMapper worklogMapper;
	@Resource
	private UserInfoExtMapper userInfoExtMapper;

	@Override
	public Userinfo login(String userCode,String userPasswd,String roleId) {
		Map<String,String > map=new HashMap<>();
		map.put("userCode",userCode);
		map.put("roleId",roleId);
		List<Userinfo> userList = userInfoExtMapper.seachUser(map);
		if(userList.size()==1){
			return userList.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String,Object>> deptList(String userFlow,Integer pageIndex,Integer pageSize) {
		List<Map<String,Object>> deptList =  deptListMapper.search(userFlow,((pageIndex-1)*pageSize)+1,(pageIndex)*pageSize);
		for(Map<String,Object> data : deptList){
			String progress = StringUtil.defaultIfEmpty((String)data.get("progress"),"0");
			int iProgress = (int)Float.parseFloat(progress);
			if(iProgress>100){
				data.put("progress", "100");
				data.put("label", "100%");
			}else{
				data.put("progress", iProgress);
				data.put("label", iProgress+"%");
			}
		}
		return deptList;
	}

	@Override
	public List<Map<String, Object>> teacherList(String userFlow,String deptFlow, String teacherName, Integer pageIndex,Integer pageSize) {
		return teacherListMapper.search(userFlow,deptFlow,"%"+teacherName+"%",((pageIndex-1)*pageSize)+1,(pageIndex)*pageSize);
	}

	@Override
	public List<Map<String, Object>> deptHeadList(String userFlow,String deptFlow, String deptHeadName, Integer pageIndex,Integer pageSize) {
		return  deptHeadListMapper.search(userFlow,deptFlow,"%"+deptHeadName+"%",((pageIndex-1)*pageSize)+1,(pageIndex)*pageSize);
	}

	@Override
	public int enterCheck(String userFlow, String deptFlow) {
		UserrealcyclesecExample example = new UserrealcyclesecExample();	
		example.createCriteria().andRucsidEqualTo(Integer.parseInt((String)deptFlow));
		return userrealcyclesecMapper.countByExample(example);
	}

	@Override
	public void enterDept(String userFlow,String deptFlow,String teacherFlow,String deptHeadFlow,String startDate,String endDate) {
		Map<String,Object> preData = enterDeptMapper.pre(deptFlow);
		
		Userrealcyclesec urcs = new Userrealcyclesec();
		urcs.setRuserid(Integer.parseInt((String)userFlow));
		if(StringUtil.isNotBlank(startDate)){
			urcs.setRstarttime(DateUtil.parseDate(startDate, DateUtil.defDtPtn04) );
		}else{
			urcs.setRstarttime(DateUtil.parseDate((String)preData.get("startTime"), DateUtil.defDtPtn04) );
		}
		
		if(StringUtil.isNotBlank(endDate)){
			urcs.setRendtime(DateUtil.parseDate(endDate, DateUtil.defDtPtn04) );
		}else{
			urcs.setRendtime(DateUtil.parseDate((String)preData.get("endTime"), DateUtil.defDtPtn04) );
		}
		
		urcs.setRcysecid(Integer.parseInt((String)preData.get("cySecID")));
		urcs.setRhoscysecid(Integer.parseInt((String)preData.get("hosCySecId")));
		urcs.setRucsid(Integer.parseInt((String)deptFlow));
		urcs.setHisstate(0);
		urcs.setLockstate(0);
		userrealcyclesecMapper.insertSelective(urcs);
		
		Userrealtec urt = new Userrealtec();
		urt.setUserdipid(Integer.parseInt((String)userFlow));
		urt.setUsertecid(teacherFlow);
		urt.setHossecid(Integer.parseInt((String)preData.get("hosSecID")));
		urt.setUcsid(Integer.parseInt((String)deptFlow));
		userrealtecMapper.insertSelective(urt );
		
		Usersectionmanager usm = new Usersectionmanager();
		usm.setStudentid(Integer.parseInt((String)userFlow));
		usm.setSectionmanagerid(Integer.parseInt((String)deptHeadFlow));
		usm.setHossecid(Integer.parseInt((String)preData.get("hosSecID")));
		usm.setUcsid(Integer.parseInt((String)deptFlow));
		usersectionmanagerMapper.insertSelective(usm );
		
		enterDeptMapper.addExam(deptFlow);
	}

	@Override
	public List<Map<String,Object>> globalProgress(String userFlow, String deptFlow) {
		List<Map<String,Object>> globalDataList = globalProgressMapper.search(userFlow, deptFlow);
		Map<String,Object> mrData = null;
		Map<String,Object> diseaseData = null;
		Map<String,Object> skillData = null;
		Map<String,Object> operationData = null;
		Map<String,Object> activityData = null;
		Map<String,Object> summaryData = null;
		for(Map<String,Object> data : globalDataList){
			String dataType = (String)data.get("dataType");
			switch (dataType) {
			case "mr":
				mrData = data;
				break;
			case "disease":
				diseaseData = data;
				break;
			case "skill":
				skillData = data;
				break;
			case "operation":
				operationData = data;
				break;
			case "activity":
				activityData = data;
				break;
			case "summary":
				summaryData = data;
				break;
			default:
				break;
			}
		}
		if(mrData==null){
			mrData = new HashMap<String, Object>();
			mrData.put("title", "大病历");
			mrData.put("order", "1");
			mrData.put("dataType", "mr");
			mrData.put("label", "0%");
			mrData.put("progress", "0");
			globalDataList.add(0,mrData);
		}
		if(diseaseData==null){
			diseaseData = new HashMap<String, Object>();
			diseaseData.put("title", "病种");
			diseaseData.put("order", "2");
			diseaseData.put("dataType", "disease");
			diseaseData.put("label", "0%");
			diseaseData.put("progress", "0");
			globalDataList.add(1,diseaseData);
		}
		if(skillData==null){
			skillData = new HashMap<String, Object>();
			skillData.put("title", "操作技能");
			skillData.put("order", "3");
			skillData.put("dataType", "skill");
			skillData.put("label", "0%");
			skillData.put("progress", "0");
			globalDataList.add(2,skillData);
		}
		if(operationData==null){
			operationData = new HashMap<String, Object>();
			operationData.put("title", "手术");
			operationData.put("order", "4");
			operationData.put("dataType", "operation");
			operationData.put("label", "0%");
			operationData.put("progress", "0");
			globalDataList.add(3,operationData);
		}
		if(activityData==null){
			activityData = new HashMap<String, Object>();
			activityData.put("title", "活动");
			activityData.put("order", "5");
			activityData.put("dataType", "activity");
			activityData.put("label", "已登记0条");
			activityData.put("progress", "0");
			globalDataList.add(4,activityData);
		}
		if(summaryData==null){
			summaryData = new HashMap<String, Object>();
			summaryData.put("title", "出科小结");
			summaryData.put("order", "6");
			summaryData.put("dataType", "summary");
			summaryData.put("label", "未提交");
			summaryData.put("progress", "0");
			globalDataList.add(5,summaryData);
		}
		for(Map<String,Object> data : globalDataList){
			String progress = StringUtil.defaultIfEmpty((String)data.get("progress"),"0");
			String dataType = (String)data.get("dataType");
			int iProgress = (int)Float.parseFloat(progress);
			if(iProgress>100){
				data.put("progress", "100");
				data.put("label", "100%");
			}else{
				if(!"summary".equals(dataType)&&!"activity".equals(dataType)){
					data.put("progress", iProgress);
					data.put("label", iProgress+"%");
				}
			}
		}

		return globalDataList;
	}
	
	@Override
	public List<Map<String, Object>> categoryProgress(String dataType,String userFlow, String deptFlow, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> categoryDataList = new ArrayList<Map<String,Object>>();
		switch (dataType) {
		case "mr":
			categoryDataList = categoryProgressMapper.mrProgress(userFlow, deptFlow, ((pageIndex-1)*pageSize)+1,(pageIndex)*pageSize);
			break;
		case "disease":
			categoryDataList =  categoryProgressMapper.diseaseProgress(userFlow, deptFlow, ((pageIndex-1)*pageSize)+1,(pageIndex)*pageSize);
			break;
		case "skill":
			categoryDataList =  categoryProgressMapper.skillProgress(userFlow, deptFlow, ((pageIndex-1)*pageSize)+1,(pageIndex)*pageSize);
			break;
		case "operation":
			categoryDataList =  categoryProgressMapper.operationProgress(userFlow, deptFlow, ((pageIndex-1)*pageSize)+1,(pageIndex)*pageSize);
			break;
		case "activity":
			categoryDataList =  _activityProgress(userFlow, deptFlow, pageIndex, pageSize);
			break;
		case "summary":
			categoryDataList =  _summaryProgress(userFlow, deptFlow, pageIndex, pageSize);
			break;
		default:
			categoryDataList = new ArrayList<Map<String,Object>>();
			break;
		}

		for(Map<String,Object> data : categoryDataList){
			String progress = StringUtil.defaultIfEmpty((String)data.get("progress"),"0");
			int iProgress = (int)Float.parseFloat(progress);
			if(iProgress>100){
				data.put("progress", "100");
			}else{
				data.put("progress", iProgress);
			}
		}
		return categoryDataList;
	}

	public List<Map<String, Object>> _activityProgress(String userFlow, String deptFlow, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("cataFlow", Long.MAX_VALUE);
		data.put("title", "各种活动");
		data.put("neededNum", "0");
		data.put("finishedNum", "0");
		data.put("auditedNum", "0");
		data.put("progress", "0");
		data.put("labelsNum", "0");
		dataList.add(data);
		return dataList;
	}

	public List<Map<String, Object>> _summaryProgress(String userFlow, String deptFlow, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("cataFlow", Long.MAX_VALUE);
		data.put("title", "出科小结");
		data.put("neededNum", "0");
		data.put("finishedNum", "0");
		data.put("auditedNum", "0");
		data.put("progress", "0");
		data.put("labelsNum", "0");
		dataList.add(data);
		return dataList;
	}
	
	@Override
	public List<Map<String, Object>> dataList(String dataType,String userFlow,String deptFlow, String cataFlow, Integer pageIndex,Integer pageSize) {
		switch (dataType) {
		case "mr":
			return dataListMapper.mrDataList(userFlow,deptFlow,cataFlow, ((pageIndex-1)*pageSize)+1,(pageIndex)*pageSize);
		case "disease":
			return dataListMapper.diseaseDataList(userFlow,deptFlow,cataFlow, ((pageIndex-1)*pageSize)+1,(pageIndex)*pageSize);
		case "skill":
			return dataListMapper.skillDataList(userFlow,deptFlow,cataFlow, ((pageIndex-1)*pageSize)+1,(pageIndex)*pageSize);
		case "operation":
			return dataListMapper.operationDataList(userFlow,deptFlow,cataFlow, ((pageIndex-1)*pageSize)+1,(pageIndex)*pageSize);
		case "activity":
			return dataListMapper.activityDataList(userFlow,deptFlow,cataFlow, ((pageIndex-1)*pageSize)+1,(pageIndex)*pageSize);
		case "summary":
			return _summaryDataList(userFlow, deptFlow, cataFlow, pageIndex, pageSize);
		default:
			return null;
		}
	}

	public List<Map<String, Object>> _summaryDataList(String userFlow, String deptFlow, String cataFlow, Integer pageIndex,Integer pageSize) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		OutsecbriefExample example = new OutsecbriefExample();
		example.createCriteria().andUcsidEqualTo(Integer.parseInt(deptFlow));//andUseridEqualTo(Integer.parseInt(userFlow)).
		List<Outsecbrief> outsecbriefList = outsecbriefMapper.selectByExample(example);
		if(outsecbriefList.size()>0){
			Outsecbrief outsecbrief = outsecbriefList.get(0);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			dataMap.put("dataFlow", outsecbrief.getBriefid().toString());
			dataMap.put("summary_content", outsecbrief.getBriefrequrie());
			dataMap.put("teacher_comment", outsecbrief.getSecappraise());
			dataMap.put("deptHead_comment", outsecbrief.getChiefcheckcontent());
//			System.err.println(StringUtil.toString(outsecbrief));
			if(outsecbrief.getCheckstatus()||outsecbrief.getTeccheckdate()!=null){
				dataMap.put("statusId", DataStatusEnum.Audited.getId());
				dataMap.put("statusDesc", DataStatusEnum.Audited.getName());
			}else{
				dataMap.put("statusId", DataStatusEnum.Saved.getId());
				dataMap.put("statusDesc", DataStatusEnum.Saved.getName());
			}
			result.add(dataMap);
		}
		return result;
	}

	@Override
	public List<Dicitem> getDictItemList(Integer dictId) {
		DicitemExample example = new DicitemExample();
		example.createCriteria().andDicidEqualTo(dictId);
		return dicitemMapper.selectByExample(example);
	}

	@Override
	public List<Map<String, Object>> diseaseDiagNameList(String userFlow,String deptFlow) {
		return inputListMapper.diseaseDiagNameList(userFlow,deptFlow);
	}

	@Override
	public List<Map<String, Object>> operationOperNameList(String userFlow,	String deptFlow) {
		return inputListMapper.operationOperNameList(userFlow,deptFlow);
	}

	@Override
	public List<Map<String, Object>> skillOperNameList(String userFlow,	String deptFlow) {
		return inputListMapper.skillOperNameList(userFlow,deptFlow);
	}

	@Override
	public void addData(String dataType,String userFlow, String deptFlow, String cataFlow,Map<String,Object> paramMap) {
		String tableName = "";
		Integer iDataFlow = -1;
		String wfTitle = "";
		String HosSecID = addDataMapper.addActivityDataSub1(paramMap);
		paramMap.put("HosSecID", HosSecID);
		switch (dataType) {
		case "mr":
			RecBighistory recBighistory = _fetchRecBighistory(paramMap, true, null);
			recBighistoryMapper.insertSelective(recBighistory);
			iDataFlow = recBighistory.getRecid();
			wfTitle = "大病历";
			tableName = "Rec_BigHistory";
			break;
		case "disease":
			RecCaseclass recCaseclass = _fetchRecCaseclass(paramMap, true, null);
			recCaseclassMapper.insertSelective(recCaseclass);
			iDataFlow = recCaseclass.getRecid();
			wfTitle = "各病种";
			tableName = "Rec_CaseClass";
			break;
		case "skill":
			RecOperateskill recOperateskill = _fetchRecOperateskill(paramMap, true, null);
			recOperateskillMapper.insertSelective(recOperateskill);
			iDataFlow = recOperateskill.getRecid();
			wfTitle = "操作技能";
			tableName = "Rec_OperateSkill";
			break;
		case "operation":
			RecPosskill recPosskill = _fetchRecPosskill(paramMap, true, null);
			recPosskillMapper.insertSelective(recPosskill);
			iDataFlow = recPosskill.getRecid();
			wfTitle = "手术";
			tableName = "Rec_POSSkill";
			break;
		case "activity":
			String CySecID = addDataMapper.addActivityDataSub2(paramMap);
			paramMap.put("CySecID", CySecID);
			addDataMapper.addActivityData(paramMap);
			iDataFlow = ((BigDecimal)paramMap.get("dataFlow")).intValue();
			wfTitle = "活动类型";
			tableName = "CaseDiscuss";
			break;
		case "summary":
			String HosCySecID = addDataMapper.addSummaryDataSub1(paramMap);
			paramMap.put("HosCySecID", HosCySecID);
			addDataMapper.addSummaryData(paramMap);
			iDataFlow = ((BigDecimal)paramMap.get("dataFlow")).intValue();
			wfTitle = "出科小结";
			tableName = "OutSecBrief";
			break;
		default:
			break;
		}	
		Map<String,Object> wfworkMap = new HashMap<String, Object>();
		wfworkMap.put("userFlow", userFlow);
		wfworkMap.put("deptFlow", deptFlow);
		wfworkMap.put("tableName", tableName);
		wfworkMap.put("wfTitle", wfTitle);
		wfworkMap.put("iDataFlow", iDataFlow.toString());
		
		String hossecname = addDataMapper.addWfworkSub1(wfworkMap);
		String usertecid = addDataMapper.addWfworkSub2(wfworkMap);
		String truename = addDataMapper.addWfworkSub3(wfworkMap);
		
		wfworkMap.put("hossecname", hossecname);
		wfworkMap.put("usertecid", usertecid);
		wfworkMap.put("truename", truename);
		
		addDataMapper.addWfwork(wfworkMap);
		
		if("summary".equals(dataType)){
			wfworkMap.put("tableName", "CycleOutSectionRecordItemDetail");
			wfworkMap.put("wfTitle", "培训轮转出科考核");
			addDataMapper.addWfwork(wfworkMap);
		}
	}

	@Override
	public Map<String, Object> viewData(String dataType, String userFlow,String deptFlow, String dataFlow) {
		int iDataFlow = Integer.parseInt((String)dataFlow);
		Map<String,Object> resultMap = new HashMap<String, Object>();
		switch (dataType) {
		case "mr":
			RecBighistory recBighistory = recBighistoryMapper.selectByPrimaryKey(iDataFlow);
			if(recBighistory==null){
				break;
			}
			resultMap.put("mr_pName", recBighistory.getRecdata1());
			resultMap.put("mr_no", recBighistory.getRecdata2());
			resultMap.put("mr_diagType", recBighistory.getRecdata5());
			break;
		case "disease":
			RecCaseclass recCaseclass = recCaseclassMapper.selectByPrimaryKey(iDataFlow);
			if(recCaseclass==null){
				break;
			}
			resultMap.put("disease_pName", recCaseclass.getRecdata1());
			resultMap.put("disease_mrNo", recCaseclass.getRecdata2());
			resultMap.put("disease_diagName", recCaseclass.getRecdata3());
			resultMap.put("disease_treatStep", recCaseclass.getRecdata4());
			resultMap.put("disease_isCharge", recCaseclass.getRecdata6());
			resultMap.put("disease_isRescue", recCaseclass.getRecdata7());
			resultMap.put("disease_pDate", recCaseclass.getRecdata8());
			resultMap.put("disease_diagType", recCaseclass.getRecdata11());
			break;
		case "skill":
			RecOperateskill recOperateskill = recOperateskillMapper.selectByPrimaryKey(iDataFlow);
			if(recOperateskill==null){
				break;
			}
			resultMap.put("skill_operName", recOperateskill.getRecdata1());
			resultMap.put("skill_operDate", recOperateskill.getRecdata2());
			resultMap.put("skill_pName", recOperateskill.getRecdata3());
			resultMap.put("skill_mrNo", recOperateskill.getRecdata4());
			resultMap.put("skill_result", recOperateskill.getRecdata9());
			resultMap.put("skill_memo", recOperateskill.getRecdata11());
			break;
		case "operation":
			RecPosskill recPosskill = recPosskillMapper.selectByPrimaryKey(iDataFlow);
			if(recPosskill==null){
				break;
			}
			resultMap.put("operation_operName", recPosskill.getRecdata1());
			resultMap.put("operation_operDate", recPosskill.getRecdata2());
			resultMap.put("operation_pName", recPosskill.getRecdata3());
			resultMap.put("operation_mrNo", recPosskill.getRecdata4());
			resultMap.put("operation_memo", recPosskill.getRecdata7());
			resultMap.put("operation_operRole", recPosskill.getRecdata14());
			break;
		case "activity":
			Casediscuss casediscuss = casediscussMapper.selectByPrimaryKey(iDataFlow);
			if(casediscuss==null){
				break;
			}
			resultMap.put("activity_date", DateUtil.formatDate(casediscuss.getCadistime(), DateUtil.defDtPtn04));
			resultMap.put("activity_content", casediscuss.getCadiscontent());
			resultMap.put("activity_way", casediscuss.getCadisplayclass());
			resultMap.put("activity_period", casediscuss.getCadisperiod()==null?"":casediscuss.getCadisperiod().intValue());
			resultMap.put("activity_speaker", casediscuss.getCadismainspeaker());
			break;
		case "summary":
			Outsecbrief outsecbrief = outsecbriefMapper.selectByPrimaryKey(iDataFlow);
			if(outsecbrief==null){
				break;
			}
			resultMap.put("summary_content", outsecbrief.getBriefrequrie());
			break;
		default:
			break;
		}
		return resultMap;
	}
	
	private RecBighistory _fetchRecBighistory(Map<String, Object> paramMap,boolean isAdd,Integer iDataFlow){
		RecBighistory recBighistory = new RecBighistory();
		if(isAdd){
			recBighistory.setUserid(Integer.parseInt((String)paramMap.get("userFlow")));
			recBighistory.setFromid(Integer.parseInt((String)paramMap.get("cataFlow")));
			recBighistory.setHossecid(Integer.parseInt((String)paramMap.get("HosSecID")));
		}else{
			recBighistory.setRecid(iDataFlow);
		}
		recBighistory.setCheckstatus(0);
		recBighistory.setRecdate(new Date());
		recBighistory.setRecdata1(StringUtil.defaultString((String)paramMap.get("mr_pName")));
		recBighistory.setRecdata2(StringUtil.defaultString((String)paramMap.get("mr_no")));
		recBighistory.setRecdata5(StringUtil.defaultString((String)paramMap.get("mr_diagType")));
		return recBighistory;
	}
	private RecCaseclass _fetchRecCaseclass(Map<String, Object> paramMap,boolean isAdd,Integer iDataFlow){
		RecCaseclass recCaseclass = new RecCaseclass();
		if(isAdd){
			recCaseclass.setUserid(Integer.parseInt((String)paramMap.get("userFlow")));
			recCaseclass.setFromid(Integer.parseInt((String)paramMap.get("cataFlow")));
			recCaseclass.setHossecid(Integer.parseInt((String)paramMap.get("HosSecID")));
		}else{
			recCaseclass.setRecid(iDataFlow);
		}
		recCaseclass.setCheckstatus(0);
		recCaseclass.setRecdate(new Date());
		recCaseclass.setRecdata1(StringUtil.defaultString((String)paramMap.get("disease_pName")));
		recCaseclass.setRecdata2(StringUtil.defaultString((String)paramMap.get("disease_mrNo")));
		recCaseclass.setRecdata3(StringUtil.defaultString((String)paramMap.get("disease_diagName")));
		recCaseclass.setRecdata4(StringUtil.defaultString((String)paramMap.get("disease_treatStep")));
		recCaseclass.setRecdata6(StringUtil.defaultString((String)paramMap.get("disease_isCharge")));
		recCaseclass.setRecdata7(StringUtil.defaultString((String)paramMap.get("disease_isRescue")));
		recCaseclass.setRecdata8(StringUtil.defaultString((String)paramMap.get("disease_pDate")));
		recCaseclass.setRecdata11(StringUtil.defaultString((String)paramMap.get("disease_diagType")));
		return recCaseclass;
	}
	
	private RecOperateskill _fetchRecOperateskill(Map<String, Object> paramMap,boolean isAdd,Integer iDataFlow){
		RecOperateskill recOperateskill = new RecOperateskill();
		if(isAdd){
			recOperateskill.setUserid(Integer.parseInt((String)paramMap.get("userFlow")));
			recOperateskill.setFromid(Integer.parseInt((String)paramMap.get("cataFlow")));
			recOperateskill.setHossecid(Integer.parseInt((String)paramMap.get("HosSecID")));
		}else{
			recOperateskill.setRecid(iDataFlow);
		}
		recOperateskill.setCheckstatus(0);
		recOperateskill.setRecdate(new Date());
		recOperateskill.setRecdata1(StringUtil.defaultString((String)paramMap.get("skill_operName")));
		recOperateskill.setRecdata2(StringUtil.defaultString((String)paramMap.get("skill_operDate")));
		recOperateskill.setRecdata3(StringUtil.defaultString((String)paramMap.get("skill_pName")));
		recOperateskill.setRecdata4(StringUtil.defaultString((String)paramMap.get("skill_mrNo")));
		recOperateskill.setRecdata9(StringUtil.defaultString((String)paramMap.get("skill_result")));
		recOperateskill.setRecdata11(StringUtil.defaultString((String)paramMap.get("skill_memo")));
		return recOperateskill;
	}
	
	private RecPosskill _fetchRecPosskill(Map<String, Object> paramMap,boolean isAdd,Integer iDataFlow){
		RecPosskill recPosskill = new RecPosskill();
		if(isAdd){
			recPosskill.setUserid(Integer.parseInt((String)paramMap.get("userFlow")));
			recPosskill.setFromid(Integer.parseInt((String)paramMap.get("cataFlow")));
			recPosskill.setHossecid(Integer.parseInt((String)paramMap.get("HosSecID")));
		}else{
			recPosskill.setRecid(iDataFlow);
		}
		recPosskill.setCheckstatus(0);
		recPosskill.setRecdate(new Date());
		recPosskill.setRecdata1(StringUtil.defaultString((String)paramMap.get("operation_operName")));
		recPosskill.setRecdata2(StringUtil.defaultString((String)paramMap.get("operation_operDate")));
		recPosskill.setRecdata3(StringUtil.defaultString((String)paramMap.get("operation_pName")));
		recPosskill.setRecdata4(StringUtil.defaultString((String)paramMap.get("operation_mrNo")));
		recPosskill.setRecdata7(StringUtil.defaultString((String)paramMap.get("operation_memo")));
		recPosskill.setRecdata14(StringUtil.defaultString((String)paramMap.get("operation_operRole")));
		return recPosskill;
	}

	@Override
	public void modData(String dataType, String userFlow, String deptFlow,String dataFlow,Map<String, Object> paramMap) {
		Integer iDataFlow = Integer.parseInt((String)dataFlow);
		String tableName = "";
		String wfTitle = "";
		Integer checkStatus = 0;
		switch (dataType) {
		case "mr":
			RecBighistory recBighistory = recBighistoryMapper.selectByPrimaryKey(iDataFlow);
			if(recBighistory!=null){
				checkStatus = recBighistory.getCheckstatus();
			}
			recBighistoryMapper.updateByPrimaryKeySelective(_fetchRecBighistory(paramMap, false, iDataFlow));
			tableName = "Rec_BigHistory";
			wfTitle = "大病历";
			break;
		case "disease":
			RecCaseclass recCaseclass = recCaseclassMapper.selectByPrimaryKey(iDataFlow);
			if(recCaseclass!=null){
				checkStatus = recCaseclass.getCheckstatus();
			}
			recCaseclassMapper.updateByPrimaryKeySelective(_fetchRecCaseclass(paramMap, false, iDataFlow));
			tableName = "Rec_CaseClass";
			wfTitle = "各病种";
			break;
		case "skill":
			RecOperateskill recOperateskill = recOperateskillMapper.selectByPrimaryKey(iDataFlow);
			if(recOperateskill!=null){
				checkStatus = recOperateskill.getCheckstatus();
			}
			recOperateskillMapper.updateByPrimaryKeySelective(_fetchRecOperateskill(paramMap, false, iDataFlow));
			tableName = "Rec_OperateSkill";
			wfTitle = "操作技能";
			break;
		case "operation":
			RecPosskill recPosskill = recPosskillMapper.selectByPrimaryKey(iDataFlow);
			if(recPosskill!=null){
				checkStatus = recPosskill.getCheckstatus();
			}
			recPosskillMapper.updateByPrimaryKeySelective(_fetchRecPosskill(paramMap, false, iDataFlow));
			tableName = "Rec_POSSkill";
			wfTitle = "手术";
			break;
		case "activity":
			Casediscuss casediscuss = casediscussMapper.selectByPrimaryKey(iDataFlow);
			if(casediscuss!=null){
				checkStatus = casediscuss.getCheckstatus();
			}
			casediscuss = new Casediscuss();
			casediscuss.setCadisid(iDataFlow);
			casediscuss.setCadistime(DateUtil.parseDate(StringUtil.defaultString((String)paramMap.get("activity_date")),DateUtil.defDtPtn04));
			casediscuss.setCadiscontent(StringUtil.defaultString((String)paramMap.get("activity_content")));
			casediscuss.setCadisplayclass(StringUtil.defaultString((String)paramMap.get("activity_way")));
			casediscuss.setCadisperiod(new BigDecimal(Integer.parseInt((String)StringUtil.defaultIfEmpty((String)paramMap.get("activity_period"),"0"))));
			casediscuss.setCadismainspeaker(StringUtil.defaultString((String)paramMap.get("activity_speaker")));
			casediscussMapper.updateByPrimaryKeySelective(casediscuss);
			tableName = "CaseDiscuss";
			wfTitle = "活动类型";
			break;
		case "summary":
			Outsecbrief outsecbrief = new Outsecbrief();
			outsecbrief.setBriefid(iDataFlow);
			outsecbrief.setBriefrequrie(StringUtil.defaultString((String)paramMap.get("summary_content")));
			outsecbrief.setCheckstatus(false);
			outsecbriefMapper.updateByPrimaryKeySelective(outsecbrief);
			tableName = "OutSecBrief";
			wfTitle = "出科小结";
			break;
		default:
			break;
		}
		
		if(checkStatus!=null&&checkStatus==3){
			Map<String,Object> wfworkMap = new HashMap<String, Object>();
			wfworkMap.put("userFlow", userFlow);
			wfworkMap.put("deptFlow", deptFlow);
			wfworkMap.put("tableName", tableName);
			wfworkMap.put("wfTitle", wfTitle);
			wfworkMap.put("iDataFlow", iDataFlow.toString());
			addDataMapper.addWfwork(wfworkMap);
		}
	}

	@Override
	public void delData(String dataType, String userFlow, String deptFlow,String dataFlow) {
		Integer iDataFlow = Integer.parseInt((String)dataFlow);
		String tableName = "";
		switch (dataType) {
		case "mr":
			tableName = "Rec_BigHistory";
			recBighistoryMapper.deleteByPrimaryKey(iDataFlow);
			break;
		case "disease":
			tableName = "Rec_CaseClass";
			recCaseclassMapper.deleteByPrimaryKey(iDataFlow);
			break;
		case "skill":
			tableName = "Rec_OperateSkill";
			recOperateskillMapper.deleteByPrimaryKey(iDataFlow);
			break;
		case "operation":
			tableName = "Rec_POSSkill";
			recPosskillMapper.deleteByPrimaryKey(iDataFlow);
			break;
		case "activity":
			tableName = "CaseDiscuss";
			casediscussMapper.deleteByPrimaryKey(iDataFlow);
			break;
		case "summary":
			tableName = "OutSecBrief";
			outsecbriefMapper.deleteByPrimaryKey(iDataFlow);
			break;
		default:
			break;
		}
		WfworkExample example = new WfworkExample();
		example.createCriteria().andWfworkTablenameEqualTo(tableName).andWfworkDataidEqualTo(iDataFlow.toString());
		int ret = wfworkMapper.deleteByExample(example);
		System.err.println("删除提醒数据"+ret+"条");
		if("summary".equals(dataType)){
			example = new WfworkExample();
			example.createCriteria().andWfworkTablenameEqualTo("CycleOutSectionRecordItemDetail").andWfworkDataidEqualTo(iDataFlow.toString());
			ret = wfworkMapper.deleteByExample(example);
			System.err.println("删除提醒数据"+ret+"条");
		}
	}

	@Override
	public int canAddSummary(String userFlow, String deptFlow) {
		int evaluateTeacher = canAddSummaryMapper.evaluateTeacher(userFlow, deptFlow);
		if(evaluateTeacher==0){
			return 31405;
		}
		int evaluateDept = canAddSummaryMapper.evaluateDept(userFlow, deptFlow);
		if(evaluateDept==0){
			return 31406;
		}
		int afterDeptExam = canAddSummaryMapper.afterDeptExam(userFlow, deptFlow);
		if(afterDeptExam==0){
			return 31407;
		}
		return 200;
	}

	@Override
	public List<Worklog> workLogList(String userFlow, String startDate,String endDate) {
		Date dStartDate = DateUtil.parseDate(startDate, DateUtil.defDtPtn04);
		Date dEndDate = DateUtil.parseDate(endDate, DateUtil.defDtPtn04);
		WorklogExample example = new WorklogExample();
		example.createCriteria()
			.andWorkUseridEqualTo(Integer.parseInt(userFlow))
			.andWorkTimeGreaterThanOrEqualTo(dStartDate)
			.andWorkTimeLessThanOrEqualTo(dEndDate);
		example.setOrderByClause("work_time");
		List<Worklog> worklogList = worklogMapper.selectByExample(example);
		return worklogList;
	}

	@Override
	public Worklog viewWorkLog(String userFlow, String workDate) {
		Date dWorkDate = DateUtil.parseDate(workDate, DateUtil.defDtPtn04);
		WorklogExample example = new WorklogExample();
		example.createCriteria()
			.andWorkUseridEqualTo(Integer.parseInt(userFlow))
			.andWorkTimeEqualTo(dWorkDate);
//		example.setOrderByClause("work_time");
		List<Worklog> worklogList = worklogMapper.selectByExample(example);
		if(worklogList.size()>0){
			return worklogList.get(0);
		}
		return null;
	}

	@Override
	public void saveWorkLog(String userFlow, String workDate,String workTypeId, String workContent) {
		Date dWorkDate = DateUtil.parseDate(workDate, DateUtil.defDtPtn04);
		WorklogExample example = new WorklogExample();
		example.createCriteria()
			.andWorkUseridEqualTo(Integer.parseInt(userFlow))
			.andWorkTimeEqualTo(dWorkDate);
//		example.setOrderByClause("work_time");
		List<Worklog> worklogList = worklogMapper.selectByExample(example);
		if(worklogList.size()>0){
			Worklog workLog = worklogList.get(0);
			workLog.setWorkUserid(Integer.parseInt(userFlow));
//			workLog.setWorkTime(dWorkDate);
			String workType = WorkLogTypeEnum.getNameById(workTypeId);
			workLog.setWorkType(workType);
			workLog.setWorkContent(workContent);
			worklogMapper.updateByPrimaryKeySelective(workLog);
		}else{
			Worklog workLog = new Worklog();
			workLog.setWorkid(PkUtil.getGUID());
			workLog.setWorkUserid(Integer.parseInt(userFlow));
			workLog.setWorkTime(dWorkDate);
			String workType = WorkLogTypeEnum.getNameById(workTypeId);
			workLog.setWorkType(workType);
			workLog.setWorkContent(workContent);
			worklogMapper.insertSelective(workLog);
		}
	}

	@Override
	public void delWorkLog(String userFlow, String workDate) {
		Date dWorkDate = DateUtil.parseDate(workDate, DateUtil.defDtPtn04);
		WorklogExample example = new WorklogExample();
		example.createCriteria()
			.andWorkUseridEqualTo(Integer.parseInt(userFlow))
			.andWorkTimeEqualTo(dWorkDate);		
		worklogMapper.deleteByExample(example);
	}

}  
  