package com.pinde.sci.biz.edu;

import com.pinde.sci.model.mo.EduStudyHistory;

import java.util.List;
import java.util.Map;

public interface IEduStudyHistoryBiz {
	/**
	 * 新增或修改
	 * @param history
	 * @return
	 */
	int edit(EduStudyHistory history);
	/**
	 * 保存学习记录
	 * @param operTypeId
	 * @param resourceFlow
	 * @return
	 */
	int save(String operTypeId,String resourceFlow);
	/**
	 * 查询列表
	 * @return
	 */
	List<EduStudyHistory> searchList();
	/**
	 * 查询附加属性
	 * @return
	 */
	Map<String, Object> searchExtData(List<EduStudyHistory> historyList);
}
