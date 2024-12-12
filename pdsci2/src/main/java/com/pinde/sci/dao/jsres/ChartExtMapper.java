package com.pinde.sci.dao.jsres;

import com.pinde.core.model.SysOrg;
import com.pinde.sci.model.mo.PersonStaticExample;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChartExtMapper {

	List<Map<String,String>> findCountryDocCount( @Param(value = "sessionNumber")String sessionNumber);

	List<Map<String,String>> findProDocCount( @Param(value = "sessionNumber")String sessionNumber);

	List<Map<String,String>> findCountrySpeDocCount( @Param(value = "sessionNumber")String sessionNumber);

	List<Map<String,String>> findProSpeDocCount( @Param(value = "sessionNumber")String sessionNumber);

	List<Map<String,String>> findDocTypeCount( @Param(value = "sessionNumber")String sessionNumber);

	List<Map<String,String>> findOrgTypeCount( @Param(value = "sessionNumber")String sessionNumber);

	List<Map<String,String>> findCityDocCount( @Param(value = "sessionNumber")String sessionNumber);

	List<Map<String,String>> findOrgAssiCount( @Param(value = "sessionNumber")String sessionNumber);

	List<Map<String,String>> findSpeGraduateCount( @Param(value = "sessionNumber")String sessionNumber);

	List<Map<String,String>> findOrgGraduateCount( @Param(value = "sessionNumber")String sessionNumber);

	List<Map<String,String>> doctorNumForUni1(@Param(value = "recruit")ResDoctorRecruit recruit, @Param(value = "orgFlows") List<String> orgFlows, @Param(value = "org") SysOrg org);
	List<Map<String,String>> doctorNumForUni1DaoChu(@Param(value = "recruit")ResDoctorRecruit recruit, @Param(value = "orgFlows") List<String> orgFlows, @Param(value = "org")SysOrg org);
	List<Map<String,String>> doctorNumForUni2(@Param(value = "recruit")ResDoctorRecruit recruit, @Param(value = "orgFlows") List<String> orgFlows, @Param(value = "org")SysOrg org);
	List<Map<String,String>> doctorNumForUni2DaoChu(@Param(value = "recruit")ResDoctorRecruit recruit, @Param(value = "orgFlows") List<String> orgFlows, @Param(value = "org")SysOrg org);
	//学员轮转数据
	List<SysOrg> getRotationData(Map<String,Object> paramMap);
	//人员统计数据
	List<PersonStaticExample> getPersonStaticData(Map<String,Object> paramMap);
	//人员统计数据new
	List<PersonStaticExample> getPersonStaticDataNEWyuh(Map<String,Object> paramMap);
	Integer residentRecruitCount(Map<String,Object> paramMap);
	Integer inSchoolRecruitCount(Map<String,Object> paramMap);

}
