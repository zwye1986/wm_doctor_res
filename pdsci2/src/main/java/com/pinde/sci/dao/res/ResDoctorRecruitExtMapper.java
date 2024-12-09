package com.pinde.sci.dao.res;

import com.pinde.core.model.SysDict;
import com.pinde.sci.model.jsres.OrgSpeListVo;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.mo.ResDoctorRecruitWithBLOBs;
import com.pinde.sci.model.res.ResDoctorRecruitExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResDoctorRecruitExtMapper {

	List<ResDoctorRecruitExt> searchDoctorRecruitWithUserList(Map<String, Object> paramMap);

	List<SysDict> searchTrainSpeList(Map<String, Object> paramMap);

	List<ResDoctorRecruitExt> searchDoctorRecruitExt(
			Map<String, Object> paramMap);

	String searchNoticeDoctorNum(Map<String, Object> paramMap);

	List<ResDoctorRecruitExt> selectDoctorRecruitExt(
			Map<String, Object> paramMap);
	List<ResDoctorRecruitExt> DoctorRecruitExt(
			Map<String, Object> paramMap);

	List<ResDoctorRecruitExt> readDoctorRecruitExt(Map<String, Object> paramMap);
	ResDoctorRecruitWithBLOBs docRecruitClobSearch(Map<String, Object> paraMp);

	List<Map<String, String>> doctorCounMap(Map<String, Object> paramMap);

	List<Map<String,Object>> getCurrDocDetails(Map<String, Object> paramMap);

	List<Map<String,Object>> getcheckDocs(Map<String, Object> paramMap);

	List<ResDoctor> searchDoctorByJd(Map<String, Object> paramMap);

	List<ResDoctor> searchDoctorBySpe(Map<String, Object> paramMap);
	//湖北招录信息统计
	List<Map<String,Object>> queryPlanStatistics(@Param("recruitYear") String recruitYear);
	//湖北各基地招录信息统计
	List<Map<String,Object>> queryOrgStatistics(@Param("recruitYear") String recruitYear);

	List<ResDoctorRecruit> searchDoctorRecruitWithLine(Map<String,Object> paramMap);
	//查询湖北历年招录总数
	List<Map<String,Object>> getHBRecruitInfo(Map<String,Object> paramMap);
	//查询江苏历年招录总数
	List<Map<String,Object>> getJSRecruitInfo(Map<String,Object> paramMap);

	//湖北招录统计(往年)
	List<Map<String,Object>> queryPlanStatisticsBefore(@Param("recruitYear") String recruityYear);
	//湖北各基地招录统计(往年)
	List<Map<String,Object>> queryOrgStatisticsBefore(@Param("recruitYear") String recruityYear);

	List<Map<String,Object>> queryExamSignUpList(Map<String, Object> param);

	/**
	 * @Department：研发部
	 * @Description 查询基地招录计划信息
	 * @Author fengxf
	 * @Date 2020/6/5
	 */
	List<Map<String, Object>> searchAssignInfoList(Map<String, Object> param);

	/**
	 * @Department：研发部
	 * @Description 查询基地和协同基地信息
	 * @Author fengxf
	 * @Date 2020/6/6
	 */
	List<Map<String, String>> searchOrgInfoList(Map<String, String> param);

	/**
	 * @Department：研发部
	 * @Description 查询招生计划专业信息
	 * @Author fengxf
	 * @Date 2020/6/6
	 */
	List<Map<String, String>> searchAssignOrgSpeList(Map<String, String> param);

	List<OrgSpeListVo> searchAssignOrgSpeList2(@Param("orgFlow") String orgFlow, @Param("assignYear") String assignYear);

	/**
	 * @Department：研发部
	 * @Description 保存基地专业信息
	 * @Author fengxf
	 * @Date 2020/6/6
	 */
	int insertOrgSpeAssign(Map<String, String> param);

	/**
	 * @Department：研发部
	 * @Description 作废当前基地招生年份为空的招生计划
	 * @Author fengxf
	 * @Date 2020/6/6
	 */
	int delOrgSpeAssign(Map<String, String> param);

	/**
	 * @Department：研发部
	 * @Description 保存招生计划专业简介信息
	 * @Author fengxf
	 * @Date 2020/6/6
	 */
	int updateAssignSpeDesc(Map<String, String> param);

	/**
	 * @Department：研发部
	 * @Description 更新招生计划信息
	 * @Author fengxf
	 * @Date 2020/6/6
	 */
	int updateAssignInfo(Map<String, String> param);

	/**
	 * @Department：研发部
	 * @Description 查询当前新增的年份基地计划是否已存在
	 * @Author fengxf
	 * @Date 2020/6/7
	 */
	int countAssignInfoByParam(Map<String, Object> param);

	/**
	 * @Department：研发部
	 * @Description 更新招生计划信息
	 * @Author fengxf
	 * @Date 2020/6/7
	 */
	int updateAssignInfoByParam(Map<String, Object> param);

	/**
	 * @Department：研发部
	 * @Description 查询医师招录信息
	 * @Author fengxf
	 * @Date 2020/6/7
	 */
	List<ResDoctorRecruit> getDoctorRecruitInfo(String doctorFlow);

	int updateAssignAudit(Map<String, Object> param);

	int updateOrgSpeByFlow(Map<String, Object> param);

	List<Map<String,Object>> searchAssignInfoListNew(Map<String, Object> paramMap);

    List<Map<String,String>> searchAssignOrgSpeListNew(Map<String, String> param);

    int countSendInfoByParam(Map<String, Object> param);
}
