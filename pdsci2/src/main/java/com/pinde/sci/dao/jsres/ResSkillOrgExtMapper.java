package com.pinde.sci.dao.jsres;

import com.pinde.sci.model.mo.ResSkillConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResSkillOrgExtMapper {

	int delSkillOrgBySkillFlow(@Param("skillFlow") String skillFlow);

	int insertAllOrg(Map<String, Object> map);

	List<ResSkillConfig> searchSkillConfig(@Param("skillStartTime") String skillStartTime, @Param("skillEndTime") String skillEndTime,@Param("skillFlow") String skillFlow);
}
