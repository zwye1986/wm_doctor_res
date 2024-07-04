package com.pinde.res.biz.njmu;

import java.util.List;
import java.util.Map;

import com.pinde.res.model.njmu.mo.Dicitem;
import com.pinde.res.model.njmu.mo.Userinfo;
import com.pinde.res.model.njmu.mo.Worklog;

public interface INjmuBiz {

	public Userinfo login(String userCode,String userPasswd,String roleId);
	
	public List<Map<String,Object>> deptList(String userFlow,Integer pageIndex,Integer pageSize);

	public List<Map<String, Object>> teacherList(String userFlow,String deptFlow, String teacherName, Integer pageIndex,Integer pageSize);

	public List<Map<String, Object>> deptHeadList(String userFlow,String deptFlow, String deptHeadName, Integer pageIndex,Integer pageSize);

	public int enterCheck(String userFlow, String deptFlow);
	
	public void enterDept(String userFlow,String deptFlow,String teacherFlow,String deptHeadFlow,String startDate,String endDate);
	
	public List<Map<String,Object>> globalProgress(String userFlow,String deptFlow);
	
	public List<Map<String,Object>> categoryProgress(String dataType,String userFlow,String deptFlow, Integer pageIndex,Integer pageSize);
	
	List<Map<String,Object>> dataList(String dataType,String userFlow,String deptFlow,String cataFlow, Integer pageIndex,Integer pageSize);
	
	public List<Dicitem> getDictItemList(Integer string);

	public List<Map<String, Object>> diseaseDiagNameList(String userFlow,String deptFlow);

	public List<Map<String, Object>> operationOperNameList(String userFlow,	String deptFlow);

	public List<Map<String, Object>> skillOperNameList(String userFlow,String deptFlow);

	public void addData(String dataType,String userFlow, String deptFlow, String cataFlow,Map<String,Object> paramMap);

	public Map<String, Object> viewData(String dataType, String userFlow,String deptFlow, String dataFlow);

	public void delData(String dataType, String userFlow, String deptFlow,String dataFlow);

	public void modData(String dataType, String userFlow, String deptFlow,String dataFlow,Map<String, Object> paramMap);

	public int canAddSummary(String userFlow, String deptFlow);

	public List<Worklog> workLogList(String userFlow, String startDate, String endDate);

	public Worklog viewWorkLog(String userFlow, String workDate);

	public void saveWorkLog(String userFlow, String workDate,String workTypeId, String workContent);

	public void delWorkLog(String userFlow, String workDate);

}  
  