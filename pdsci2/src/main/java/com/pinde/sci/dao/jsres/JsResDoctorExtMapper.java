package com.pinde.sci.dao.jsres;

import com.pinde.core.model.ResDoctor;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JsResDoctorExtMapper {
	/**
	 * 符合缩减条件的医师
	 * @param paramMap
	 * @return
     */
	List<ResDoctor> searchReductionDoc(Map<String,Object> paramMap);

	/**
	 * 查询该届别下各类型人员数量
	 * @param paramMap
	 * @return
     */
	List<Map<String,Object>> getDocCountBySession(Map<String,Object> paramMap);

	List<Map<String,Object>> getDocLogCountBySession(Map<String,Object> paramMap);

	/**
	 * 根据机构查询所有培训过的学员
	 * @param paramMap
	 * @return
     */
	List<Map<String,Object>> getDocByOrg(Map<String,Object> paramMap);

	List<Map<String,Object>> getDocLogByOrg(Map<String,Object> paramMap);

	List<Map<String,Object>> getALLScoreByMap(Map<String,Object> paramMap);
	Map<String,Object> getOrgCodeAndNum(@Param(value="orgFlow") String orgFlow,@Param(value="year") String year);

	List<ResDoctor> searchResDoctorByMap(Map<String, Object> map);

	int findWaitPassCountByOrgFlow(Map<String, Object> map);

	Map<String,Object> getOrgNumByCityId( @Param(value="orgFlow")String orgFlow,@Param(value="orgCityId") String orgCityId,@Param(value="year") String year,@Param(value="yearbefore") String yearbefore);

	Map<String,Object> getQuanKe(@Param(value="orgCityId") String orgCityId,@Param(value="year") String year,@Param(value="yearbefore") String yearbefore);

	Map<String,Object> getFeiQuanKe(@Param(value="orgCityId") String orgCityId,@Param(value="year") String year,@Param(value="yearbefore") String yearbefore);

	List<Map<String,Object>> getAssiGeneralDocCountBySession(Map<String, Object> paramMap);
	List<Map<String,Object>> getAssiGeneralDocLogCountBySession(Map<String, Object> paramMap);
	List<Map<String,Object>> getAssiGeneralDocByOrg(Map<String, Object> paramMap);
	List<Map<String,Object>> getAssiGeneralDocLogByOrg(Map<String, Object> paramMap);
	List<Map<String,Object>> selectGraduates(Map<String, Object> paramMap);

	List<Map<String,Object>> selectGraduatesByOrg(Map<String, Object> paramMap);

	List<ResDoctor> searchResDoctorByMapForUni(Map<String, Object> map);

	Map<String,String> getPowerMap(@Param(value="docFlow") String doctorFlow);

	Map<String,Object> getDocProcessArea(String userFlow);
}
