package com.pinde.sci.dao.lcjn;

import com.pinde.sci.model.mo.LcjnSkillCfgDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LcjnCostManagerExtMapper {
	//根据COURSEFLOW查出所有该课程所需耗材
	List<LcjnSkillCfgDetail> searchCfgByCourseFlow(@Param(value = "courseFlow")String courseFlow);
}
