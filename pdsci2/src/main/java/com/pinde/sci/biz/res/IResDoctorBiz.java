package com.pinde.sci.biz.res;

import com.pinde.core.model.SysUser;
import com.pinde.sci.excelListens.model.ResRecItem;
import com.pinde.sci.form.hbres.ExtInfoForm;
import com.pinde.sci.form.hbres.ReplenishInfoForm;
import com.pinde.sci.form.hbres.ResDoctorClobForm;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import com.pinde.sci.model.res.ResDoctorScoreExt;
import com.pinde.sci.model.sys.SysUserResDoctorExt;
import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author tiger
 *
 */
public interface IResDoctorBiz {
	List<ResDoctor> searchDoctor();
	ResDoctor readDoctor(String recordFlow);

	List<ResDoctor> readDoctorByExample(ResDoctorExample example);

	List<Map<String, Object>> readDoctorCountBySpe(ResDoctor resDoctor);
	
	int editDoctor(ResDoctor doctor);
	
	int editDoctor(ResDoctor doctor, SysUser sysUser);
	List<ResDoctor> searchByDoc(ResDoctor doctor);
	int editDocUser(ResDoctor doctor, SysUser user);
	List<ResDoctorExt> searchDocUser(ResDoctorExt resDoctorExt, String medicineTypeId);

    /**
     * 专业基地 年度考核成绩查询
     * @param resDoctorExt
     * @return
     */
    List<ResDoctorExt> searchDocUserForAnnualCheck(ResDoctorExt resDoctorExt);
	List<ResDoctorScoreExt> searchDocGrades(ResDoctorScoreExt resDoctorExt);

	/**
	 * 根据用户流水号查找
	 * @param userFlow 用户流水号
	 * @return
	 */
	ResDoctor searchByUserFlow(String userFlow);
	/**
	 * 查询带教老师或科主任
	 * @param resultFlow 轮转计划流水号
	 * @param roleFlow 角色流水号
	 * @return
	 */
	List<SysUser> searchTeacherOrHead(String resultFlow,String roleFlow);
	/**
	 * 保存带教老师和科主任
	 * @param process
	 * @param resultFlow
	 * @param preResultFlow
	 */
	void saveChoose(ResDoctorSchProcess process,String resultFlow,String preResultFlow);
	
	
	List<ResDoctor> searchDoctorByuserFlow(List<String> userFlows);
	int editDocUserFromRegister(ResDoctor doctor, SysUser user, BaseUserResumeExtInfoForm extInfo);
	int editDocUserFromRegister2(ResDoctor doctor, SysUser user, BaseUserResumeExtInfoForm extInfo);
	
	/**
	 * 保存扫描件
	 * @param oldImg
	 * @param file
	 * @param folderName
	 * @return
	 */
	String saveImg(String oldImg, MultipartFile file, String folderName);
	
	String checkFile(MultipartFile file);
	
	Integer searchResDoctorUserCount(Map<String , Object> paramMap);
	//审核通过（批量一键审核通过）
	int auditBatchDoctor(String userFlow);
	List<Map<String, Object>> searchTrainPlanCount(ResDoctor doctor,
			String countType);
	int modifyResDoctorRotation(ResDoctor doctor);
	int submitUserInfo(SysUser user, ResDoctor doctor);
	Integer searchResRegUserCount(Map<String, Object> activatedCountParamMap);
	List<ResDoctorExt> searchRegUser(Map<String, Object> paramMap);
	ResDoctor searchResDoctor(String userFlow, String regYear);
	List<Map<String, String>> searGroupRotation(ResDoctor doctor);
	ResExamDoctor searchExamDoctor(String doctorFlow, String examType,String examYear);
	
	
	/**
	 * 查询注册登记表
	 * @param paramMap
	 * @return
	 */
	List<ResDoctorExt> searchRegisterList(Map<String, Object> paramMap);
	void withdrawDoctor(ResDoctor doctor);
	
	List<ResDoctor> searchMonthRotationDoctor(String schDeptFlow, String month);
	int resultAudit(String orgFlow);
    /**
     * 查询系统所有用户及其住院医师信息
     * @param userDoctorExt
     * @param checkUserFlowList
     * @return
     */
	List<SysUserResDoctorExt> searchSysUserAndResDoctor(
			SysUserResDoctorExt userDoctorExt,List<String> checkUserFlowList,String schDeptFlow);

	List<ResDoctor> searchSelDeptDoctor(ResDoctor doctor);
	List<ResDoctor> searchDoctorForChange(ResDoctor doctor,
			ResDoctorOrgHistory docOrgHis);
	int updateDocSelFlag(String orgFlow);
	List<ResDoctorExt> searchDocByteacher(Map<String, Object> paramMap);

	List<ResDoctor> searchDocByteacherJx(Map<String, Object> paramMap);
	int editDoctorUser(ResDoctor doctor, SysUser user);
	int resetDoctorRecruit(String doctorFlow);
	
	/**
	 * 按条件查找医师,不含自己
	 * @param doctor
	 * @return
	 */
	List<ResDoctor> searchByDocNotSelf(ResDoctor doctor,String doctorFlow);
	
	/**
	 * 升级冗余字段
	 * @param doctor
	 * @return
	 */
	int updateRedundancyData(ResDoctor doctor);

	/**
	 * 按条件计算机构内各专业所占人数
	 * @param orgName
	 * @param doctor
	 * @return
	 */
	List<Map<String, Object>> countDocByOrg(String orgName,
			ResDoctor doctor);
	
	/**
	 * 查询指定机构下符合条件的人数
	 * @param doctor
	 * @return
	 */
	int findDoctorCountInOrg(ResDoctor doctor);
	
	/**
	 * 计算所属组合的医师数
	 * @param doctor
	 * @return
	 */
	List<Map<String, Object>> countGroupDoc(ResDoctor doctor);
	
	/**
	 * 删除集合内医师的所有选科和排班
	 * @param doctorFlowList
	 * @return
	 */
	int clearSelAndRostering(List<String> doctorFlowList);


	/**
	 * only保存
	 * @param resDoctor
	 * @return
	 */
	int onlySaveResDoctor(ResDoctor resDoctor);
	ResDoctor findByFlow(String doctorFlow);
	
	/**
	 * 取有rotationd的doctor
	 * @param doctor
	 * @return
	 */
	List<ResDoctor> searchByDocHaveRotation(ResDoctor doctor);
	
	
	/**
	 * 查询医师(医师账号管理)
	 * @param sysUser
	 * @param sysOrg
	 * @return
	 */
	List<ResDoctorExt> searchDoctorAccountList(SysUser sysUser, SysOrg sysOrg,String baseFlag,String orgFlow,String lockStatus,String trainingSpeId,String trainingTypeId);
	int importStudentMainExcel(MultipartFile file, String orgFlow);
	
	/**
	 * 查询减免学员
	 * @param degreeTypes
	 * @param sessionNumber
	 * @param trainingYears
	 * @param trainType
	 * @param orgFlows
	 * @param doctorName
	 * @return
	 */
	List<ResDoctor> searchReductionDoc(List<String> degreeTypes,
			String sessionNumber, List<String> trainingYears, String trainType,
			List<String> orgFlows, String doctorName);
	int disabledDoctorUser(ResDoctor doctor, SysUser user);

	List<ResSignin> searchSignList(List<String> deptFlows, String signDate);
	List<Map<String, Object>> schDoctorQuery(String teacherUserFlow, String startDate, String endDate, List<String> typeList, String workOrgId);
	List<Map<String, Object>> chTeacherProcessFlowRec(String teacherUserFlow, String startDate, String endDate, List<String> typeList);

	/**
	 * 学员重报菜单
	 * @param parMp
	 * @return
     */
	List<Map<String, Object>> searchRegStuLst(Map<String, Object> parMp);

	/**
	 * 保存医生补填信息
	 * @param form
     */
	void saveInfo(ReplenishInfoForm form);

	/**
	 * 额外信息xml解析为form对象
	 * @param extInfoXml
	 * @return
	 */
	ExtInfoForm parseExtInfoXml(String extInfoXml);

	Map<String,Object>  importCourseFromExcel(MultipartFile file, String year, String scoreYear);
	Map<String,Object>  importSkillScoreFromExcel(MultipartFile file, String year, String scoreYear);
	Map<String,Object>  importPublicScoreFromExcel(MultipartFile file);
	int batchApplyAudit(String[] datas,String roleFlag,String applyType,String reason,String doctorStatusId);

	String getCertificateNo(ResDoctor resDoctor);

	List<ResDoctor> searchResDoctorByMap(Map<String, Object> map);

    /**
     * 查询入培时间2017且培训年限为3；或者人员类型为 外院人员
     * @param map
     * @return
     */
    List<ResDoctor> searchResDoctorByAssessment(Map<String, Object> map);

	/**
	 * 师承指导老师查询自己的学员
	 * @param param
	 * @return
     */
	List<ResDoctorExt> searchDocByDiscipleTea(Map<String, Object> param);


	/**
	 * 医院管理员查询本医院下的学员
	 * @param param
	 * @return
     */
	List<ResDoctorExt> searchDocByDiscipleAdmin(Map<String, Object> param);

	/**
	 * 导师查询所带学员的跟师情况
	 * @param param
	 * @return
     */
	List<Map<String,Object>> teaQueryDocDisciple(Map<String, Object> param);

	/**
	 * 管理员
	 * @param param
	 * @return
     */
	List<Map<String,Object>> adminQueryDocDisciple(Map<String, Object> param);

	/**
	 * 学校查看系统数据
	 * @param beMap
	 * @return
     */
	List<Map<String,Object>> searchSysRecords(Map<String, Object> beMap);

    /**
     * 医院查看系统数据
     * @param beMap
     * @return
     */
    List<Map<String,Object>> searchDocSysRecords(Map<String, Object> beMap);

	String convertMapToXml(Map<String,String> map,ResScore resScore) throws Exception;

	int  savePublic(ResScore resScore);

	List<ResDoctor> searchResDoctorByMapForUni(Map<String, Object> map);

	Map<String,String> getPowerMap(String doctorFlow);

	Map<String,Object> getDocProcessArea(String userFlow);

	boolean getCkkPower(String doctorFlow);

	/**
	 * 湖北中医过程管理国家医师协会导出
	 * @param doctorList
	 * @param response
     */
	void exportForDetail(List<ResDoctorExt> doctorList, HttpServletResponse response) throws DocumentException, IOException;
	/**
	 * 湖北专用过程管理国家医师协会导出
	 */
	void exportForDetail4HB(List<ResDoctorExt> doctorList, Map<String, Object> paramMap, HttpServletResponse response) throws DocumentException, IOException;
	/**
	 * 湖北专用过程管理国家医师协会导出
	 */
	void exportForDetail4HB2017(List<ResDoctorExt> doctorList, HttpServletResponse response) throws DocumentException, IOException;
	/**
	 * 根据doctorFlow查询ResDoctorExt
	 * @param doctorFlows
	 * @return
     */
	List<ResDoctorExt> searchDoctorExt(List<String> doctorFlows);
	/**
	 * 退培用
	 * 根据doctorFlow查询ResDoctorExt
	 * @param doctorFlows
	 * @return
	 */
	List<ResDoctorExt> searchDoctorExt4Back(List<String> doctorFlows);
	//查询某年所有注册信息完善的医师（为专硕导入成绩使用）
	List<ResDoctorClobForm> searchClobInfoDoc(String year);

	void exportForDetail2(List<ResDoctorExt> doctorList, HttpServletResponse response) throws DocumentException, IOException;

	void insertDoctor(ResDoctor doctor);
	//查询DOCTORFLOW在范围内的学员（考勤系统用）
	List<ResDoctor> searchDoctor4Kq(Map<String,Object> paramMap);


	List<ResDoctor> searchDocByteacher2(Map<String, Object> paramMap);

	void insertRecruitDocInfo(String recruitFlow);

	void insertRecruitInfo(String recruitFlow);

	void insertRecruitDocInfoNotPass(String recruitFlow);

	void insertRecruitInfoNotPass(String recruitFlow);

	JsresRecruitDocInfoWithBLOBs selectRecruitDocInfo(String recruitFlow);

	JsresRecruitInfo selectRecruitInfo(String recruitFlow);

	void delJsresRecruitDocInfo(String recruitFlow);

	void delJsresRecruitInfo(String recruitFlow);

	JsresRecruitDocInfoWithBLOBs getRecruitDocInfoBySessionNumber(String doctorFlow, String sessionNumber);

	JsresRecruitInfo getRecruitInfoBysessionNumber(String doctorFlow, String sessionNumber);

	int importStudentExcel(MultipartFile file, String orgFlow);

	int editSchDocUser(ResDoctor doctor, SysUser user);

	void exportSchDocDetail(List<ResDoctorExt> doctorList, HttpServletResponse response) throws Exception;

	int deleteSchDocUser(String userFlow);

	List<ResDoctorExt> getStudents(Map<String, String> params);

	ResUserBindMacid readBindMacidByFlow(String doctorFlow);

	int saveUserMacid(ResUserBindMacid macid);
	/**
	 * 省厅
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> globalQueryDocDisciple(Map<String, Object> param);

	int importStudentMainExcel4jszy(MultipartFile file, String orgFlow, String role);

	/**
	 * @Department：研发部
	 * @Description 导出学员出科成绩和技能成绩
	 * @Author fengxf
	 * @Date 2019/11/12
	 */
	void exportDoctorRecruitResultList(ResDoctorExt resDoctor, HttpServletResponse response, String papersFlag) throws IOException;
	/**
	 * @Department：研发部
	 * @Description 更改开通权限提交
	 * @Author lim
	 * @Date 2020/8/5
	 */
    int updateSubmit(ResDoctor doctor);
	/**
	 * @Department：研发部
	 * @Description 更改开通权限提交
	 * @Author lim
	 * @Date 2020/8/6
	 */
	int updateSubmitAll(Map<String,Object> param);

	int saveSubmitAll(List<String> userFlowList);

    int updateCheckAll(Map<String,Object> param);

    /**
     * @Department：研发部
     * @Description 查询带教学员名单
     * @Author fengxf
     * @Date 2020/11/18
     */
	List<Map<String, String>> searchSchDoctorList(Map<String, Object> paramMap);

	/**
	 * @Department：研发部
	 * @Description 查询医师列表
	 * @Author Zjie
	 * @Date 0013, 2021年1月13日
	 */
	List<JsResDoctorRecruitExt> searchDocUserNew(ResDoctorExt resDoctorExt, String medicineTypeId);


	List<Map<String,String>> searchDoctorSpe();

    List<Map<String,Object>> getCountBySessionNumber(List<String> userFlowList);

    List<String> searchRecruitListByOrgFlow(String orgFlow, String isJointOrg);

    //获取南医大院校列表
	List<String> getSchools();
	/**
	 * ~~~~~~~~~溺水的鱼~~~~~~~~
	 * @Author: 吴强
	 * @Date: 2024/10/28 17:17
	 * @Description:根据姓名集合和身份证集合查询人员信息
	 */
	List<SysUser> listByNameOrIdNo(Set<String> userName, Set<String> idNo);

	List<SchArrangeResult> listDoctorResult(Set<String> userIds);

	List<ResRecItem> resRecCount(List<String> resultFlowList);
}
