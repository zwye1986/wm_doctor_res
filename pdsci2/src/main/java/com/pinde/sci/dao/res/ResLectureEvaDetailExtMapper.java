package com.pinde.sci.dao.res;

import com.pinde.core.model.ResLectureEvaDetail;

import java.util.List;
import java.util.Map;

public interface ResLectureEvaDetailExtMapper {

    /**
     * @Department：研发部
     * @Description 保存讲座活动评价信息
     * @Author fengxf
     * @Date 2020/2/14
     */
	int saveLectureEval(Map<String, Object> paramMap);

	/**
	 * @Department：研发部
	 * @Description 查询讲座活动用户评价信息
	 * @Author fengxf
	 * @Date 2020/2/14
	 */
	List<ResLectureEvaDetail> searchUserEvalList(Map<String, String> param);
}
