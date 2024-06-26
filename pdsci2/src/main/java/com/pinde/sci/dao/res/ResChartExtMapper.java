package com.pinde.sci.dao.res;

import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.mo.SysOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResChartExtMapper {

	List<Map<String,String>> findCountryDocCount(@Param(value = "sessionNumber") String sessionNumber);

	List<Map<String,String>> findProDocCount(@Param(value = "sessionNumber") String sessionNumber);

	List<Map<String,String>> findCountrySpeDocCount(@Param(value = "sessionNumber") String sessionNumber);

	List<Map<String,String>> findProSpeDocCount(@Param(value = "sessionNumber") String sessionNumber);

	List<Map<String,String>> findDocTypeCount(@Param(value = "sessionNumber") String sessionNumber);

	List<Map<String,String>> findOrgTypeCount(@Param(value = "sessionNumber") String sessionNumber);

	List<Map<String,String>> findCityDocCount(@Param(value = "sessionNumber") String sessionNumber);

	List<Map<String,String>> findOrgAssiCount(@Param(value = "sessionNumber") String sessionNumber);

	List<Map<String,String>> findSpeGraduateCount(@Param(value = "sessionNumber") String sessionNumber);

	List<Map<String,String>> findOrgGraduateCount(@Param(value = "sessionNumber") String sessionNumber);

	List<Map<String,String>> doctorNumForUni1(@Param(value = "recruit") ResDoctorRecruit recruit, @Param(value = "orgFlows") List<String> orgFlows, @Param(value = "org") SysOrg org);
	List<Map<String,String>> doctorNumForUni1DaoChu(@Param(value = "recruit") ResDoctorRecruit recruit, @Param(value = "orgFlows") List<String> orgFlows, @Param(value = "org") SysOrg org);
	List<Map<String,String>> doctorNumForUni2(@Param(value = "recruit") ResDoctorRecruit recruit, @Param(value = "orgFlows") List<String> orgFlows, @Param(value = "org") SysOrg org);
	List<Map<String,String>> doctorNumForUni2DaoChu(@Param(value = "recruit") ResDoctorRecruit recruit, @Param(value = "orgFlows") List<String> orgFlows, @Param(value = "org") SysOrg org);

	List<Map<String,String>> registStatistic(Map<String,Object> paramMap);

	List<Map<String,String>> registStatisticSpe(Map<String,Object> paramMap);

	/*List<Map<String,Object>> zlxytj(@Param("sessionNumber") String sessionNumber);

	List<Map<String,Object>> zlxytj2(Map<String, Object> param);

	List<Map<String,Object>> zlxytjJoint(Map<String, Object> param);*/
}
