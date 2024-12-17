package com.pinde.sci.biz.res;

import com.pinde.core.model.QingpuLectureEvalCfg;
import com.pinde.sci.form.res.ResDeptPlanForm;
import com.pinde.sci.model.mo.SysDeptMonthExamInfo;
import com.pinde.sci.model.mo.SysDeptMonthPlan;
import com.pinde.sci.model.mo.SysDeptMonthPlanItem;
import com.pinde.sci.model.mo.SysDeptMonthPlanItemEval;

import java.util.List;
import java.util.Map;

public interface IResDeptActivityBiz {


	List<Map<String,Object>> searchList(Map<String, Object> param);

	SysDeptMonthPlan readPlan(String planFlow);

	int save(SysDeptMonthPlan payPlan);

	List<SysDeptMonthPlanItem> readPlanItems(String planFlow);

	List<SysDeptMonthExamInfo> readPlanExamInfos(String planFlow);

	SysDeptMonthPlan findPlan(String deptFlow, String planDate, String planTypeId);

	int savePlan(ResDeptPlanForm planForm);

	List<Map<String,Object>> searchStatisticList(Map<String, Object> param);

	int reportPlan(SysDeptMonthPlan plan, Map<String, QingpuLectureEvalCfg> cfgMap);

	SysDeptMonthPlanItem readPlanItem(String itemFlow);

	List<SysDeptMonthPlanItemEval> readPlanItemEvals(String itemFlow);

	SysDeptMonthPlanItemEval readPlanItemEval(String evalFlow);

	SysDeptMonthPlanItemEval readPlanItemEvalByUser(String itemFlow, String userFlow);

	int savePlanItemEval(SysDeptMonthPlanItemEval eval);
}
