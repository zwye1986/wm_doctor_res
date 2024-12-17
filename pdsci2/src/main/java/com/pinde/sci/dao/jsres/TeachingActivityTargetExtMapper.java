package com.pinde.sci.dao.jsres;

import com.pinde.core.model.TeachingActivityTarget;
import com.pinde.sci.form.res.ActivityTargetForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachingActivityTargetExtMapper {

	/**
	 * @Department：研发部
	 * @Description 查询教学活动评价指标信息
	 * @Author fengxf
	 * @Date 2020/1/20
	 */
	List<TeachingActivityTarget> searchTargetList(ActivityTargetForm activityTargetForm);

	/**
	 * @Department：研发部
	 * @Description 保存教学活动评价指标信息
	 * @Author fengxf
	 * @Date 2020/1/20
	 */
	int saveTargetDate(List<TeachingActivityTarget> targetList);

	/**
	 * @Department：研发部
	 * @Description 查询教学活动形式ID
	 * @Author fengxf
	 * @Date 2020/1/20
	 */
	String getActivityTypeIdByName(String activityTypeName);

	/**
	 * @Department：研发部
	 * @Description 查询同一指标名称和指标类型的指标数量
	 * @Author fengxf
	 * @Date 2020/1/20
	 */
	int countTargetData(String targetName);

	/**
	 * @Department：研发部
	 * @Description 根据指标类型查询最大的排序码
	 * @Author fengxf
	 * @Date 2020/2/12
	 */
	int getMaxOrdinalByType(String targetType);

	int getMaxOrdinalByTypeNew(@Param("orgFlow") String orgFlow, @Param("activityTypeId") String activityTypeId);
}
