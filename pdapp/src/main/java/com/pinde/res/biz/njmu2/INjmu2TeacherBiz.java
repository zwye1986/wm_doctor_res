package com.pinde.res.biz.njmu2;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pinde.res.model.nfyy.mo.Activity;
import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.sci.model.mo.SchDept;
import com.pinde.sci.model.mo.SysOrg;

public interface INjmu2TeacherBiz {
	/**
	 * 获取该教师的所有学员
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getDocListByTeacher(Map<String, Object> paramMap);

	/**
	 * 审核一条登记数据
	 * @param userFlow
	 * @param dataFlow
	 */
	void auditDate(String userFlow, String dataFlow);

}
