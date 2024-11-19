package com.pinde.sci.biz.res;

import com.pinde.sci.model.jsres.OrgSpeListVo;
import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface IResOrgSpeAssignBiz {

	List<ResOrgSpeAssign> searchSpeAssign(String assignYear);

//	int editSpeAssign(ResOrgSpeAssign speAssign);
	
	/**
	 * 根据机构流水号和年份 查询指定年份某家医院的所有专业
	 * @param orgFlow
	 * @param assignYear
	 * @return
	 */
	List<ResOrgSpeAssign> searchSpeAssign(String orgFlow , String assignYear);
	
	ResOrgSpeAssign searchSpeAssign(String orgFlow , String assignYear , String speId);
	
	/**
	 * 根据主键查询专业
	 * @param flow
	 * @return
	 */
	ResOrgSpeAssign findSpeAssignByFlow(String flow);

	int editSpeAssignUnSelective(ResOrgSpeAssign speAssign);
	//返回基地专业招录计划流水号
	String queryOrgSpePlanFlow(ResOrgSpeAssign speAssign);
	
	/**
	 * 查询指定专业的所有医院招录计划
	 * @param speId
	 * @return
	 */
	List<ResOrgSpeAssign> searchSpeAssignBySpeIdAndYear(String speId , String year);
	//省厅导入招录计划
	Map<String, Object> importExcel(MultipartFile file) throws Exception;
	//基地导入复试成绩单
	Map<String, Object> impOperExcel(MultipartFile file) throws Exception;

	/**
	 * 导入招录笔试成绩
	 * @param file
	 * @return
	 * @throws Exception
     */
	Map<String, Object> impInterviewExcel(MultipartFile file) throws Exception;

	/**
	 * 导入招录操作成绩
	 * @param file
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> impSkillExcel(MultipartFile file) throws Exception;

	Map<String, Object> impGradeExcel(MultipartFile file) throws Exception;

	//查询湖北历年招录总数
	List<Map<String,Object>> getRecruitInfo(Map<String,Object> paramMap);
	//查询江苏招录总数
	List<Map<String,Object>> getJSRecruitInfo(Map<String,Object> paramMap);

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
	 * @Description 查询基地下的住院医师和助理全科的专业信息
	 * @Author fengxf
	 * @Date 2020/6/6
	 */
	List<Map<String, String>> searchAssignOrgSpeList(Map<String, String> param);

	List<OrgSpeListVo> searchAssignOrgSpeList2(String orgFlow,String assignYear);

	/**
	 * @Department：研发部
	 * @Description 保存基地专业信息
	 * @Author fengxf
	 * @Date 2020/6/6
	 */
	int insertOrgSpeAssign(Map<String, String> param);

	/**
	 * @Department：研发部
	 * @Description 保存招生计划专业简介信息
	 * @Author fengxf
	 * @Date 2020/6/6
	 */
	String updateAssignSpeDesc(Map<String, String> param);

	/**
	 * @Department：研发部
	 * @Description 查询招生计划信息
	 * @Author fengxf
	 * @Date 2020/6/6
	 */
	ResOrgSpeAssign getResOrgSpeAssignInfo(String assignFlow);

	/**
	 * @Department：研发部
	 * @Description 批量更新招生计划信息
	 * @Author fengxf
	 * @Date 2020/6/6
	 */
	String updateAssignInfo(Map<String, Object> param);

	/**
	 * @Department：研发部
	 * @Description 基地导入招生计划
	 * @Author fengxf
	 * @Date 2020/6/7
	 */
	int importAssignInfo(Map<String, String> param, MultipartFile file) throws Exception;

	/**
	 * @Department：研发部
	 * @Description 判断学员是否可以报名
	 * @Author fengxf
	 * @Date 2020/6/7
	 */
	String doctorSignupFlag();
	/**
	 * @Department：研发部
	 * @Description 判断学员是否可以报名
	 * @Author fengxf
	 * @Date 2020/6/7
	 */
	String doctorSignupFlag2();

	String updateAssignAudit(Map<String, Object> param);

	int updateOrgSpeByFlow(Map<String, Object> param);

	List<Map<String,Object>> searchAssignInfoListNew(Map<String, Object> paramMap);

	List<JsresDoctorSpe> searchDoctorSpe();

	List<JsresSign> searchSignListByParam(Map<String, Object> param);

	List<JsresSign> searchSignListByOrgFlow(String orgFlow,String useStatusId);

	List<JsresSign> searchSignListByOrgFlowNew(String orgFlow,String useStatusId,String sessionNumber);

	String saveJsresSign(JsresSign sign);

	List<JsresSign> searchSignList(JsresSign sign);

	JsresSign searchSignByPrimaryKey(String signFlow);

	List<JsresSign> searchSignListUnPassed(String orgFlow);

	String doctorSignupFlagNew();

	List<Map<String,String>> searchAssignOrgSpeListNew(Map<String, String> paramMap);

    ResOrgSpe findOrgSpe(String orgFlow, String assignYear, String speId);

	List<ResOrgSpe> findOrgSpeByExample(ResOrgSpeExample example);

    String updateSendInfo(Map<String, Object> param);
}
