package com.pinde.sci.dao.res;

import com.pinde.sci.excelListens.model.ResRecItem;
import com.pinde.sci.form.hbres.ResDoctorClobForm;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import com.pinde.sci.model.res.ResDoctorScoreExt;
import com.pinde.sci.model.res.ResExamDoctorExt;
import com.pinde.sci.model.sys.SysUserResDoctorExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ResDoctorExtMapper {
	List<ResDoctorExt> searchResDoctorUser(ResDoctorExt resDoctorExt);

	List<ResDoctorExt> searchDocUserForAnnualCheck(ResDoctorExt resDoctorExt);

    /**
     *
     * @param map
     * @return
     */
    List<ResDoctor> searchResDoctorByAssessment(Map<String, Object> map);

	List<ResDoctorExt> searchResDoctorUserByJx(ResDoctorExt resDoctorExt);

	List<ResDoctorScoreExt> searchResDoctorGrades(ResDoctorScoreExt resDoctorExt);
	
	List<SysUserResDoctorExt> searchSysUserAndResDoctor(Map<String,Object> paramMap);

	/**
	 * 查询指定届数 , 审核通过 , 同时分数在指定分数线上的人数
	 * @param paramMap
	 * @return
	 */
	Integer searchResDoctorUserCount(Map<String , Object> paramMap);
	
	/**
	 * 根据条件 用户表关联学员表和招录表查询
	 * @param paramMap
	 * @return
	 */
	List<ResDoctorExt> searchResDoctorUserRecruit(Map<String , Object> paramMap);
	

	
	/**
	 * 查询不在某场考试的用户
	 * @param paramMap
	 * statusId 状态(审核通过状态)
	 * regYear 注册年份
	 * examFlow 考试流水号
	 * @return
	 */
	List<SysUser> searchNotInExamUser(Map<String , Object> paramMap);
	
	/**
	 * 查询在某场考试某个考点下没有被分配准考证号的用户 关联res_doctor表
	 * key   
	 * examFlow 考试流水号(String)
	 * siteFlow 考点流水号(String)
	 * 排序 按专业 按注册时间正序
	 * 
	 * @param paramMap
	 * @return
	 */
	List<ResExamDoctor> searchNotAllotTicketNumUserInExamAndSite(Map<String , Object> paramMap);
	
	/**
	 * 根据考试学生表关联学员信息表查询
	 * @param examDoctor
	 * @return
	 */
	List<ResExamDoctorExt> searchExamDoctorExt(ResExamDoctorExt examDoctor);
	
	/**
	 * 根据考试学生表关联学员信息表查询
	 * @param paramMap
	 * examDoctor ResExamDoctorExt
	 * key 关键字
	 * @return
	 */
	List<ResExamDoctorExt> searchExamDoctorExtByMap(Map<String , Object> paramMap);
	
	/**
	 * 查询参加某场考试的人数 查询条件有    
	 * examFlow           考试流水号
	 * speId              专业Id
	 * startGrade         起始分
	 * endGrade           结束分
	 * moreThanGrade      大于某个分数
	 * @param paramMap
	 * @return
	 */
	int searchJoinExamDoctorCountByParamMap(Map<String , Object> paramMap);
	
	/**
	 * 查询某场考试某个专业分数的最大值或最小值
	 * @param paramMap
	 * @return
	 */
	int searchMaxOrMinGradeInExam(Map<String , Object> paramMap);

//	int modifyResDoctorRecruit(Map<String , Object> paramMap);
	
	List<Map<String,Object>> searchTrainPlanCount(@Param(value="doctor")ResDoctor doctor,@Param(value="countType")String countType);
	
	int modifyResDoctorRotation(@Param(value="doctor")ResDoctor doctor);

	/**
	 * 批量修改医师状态
	 * @param paramMap
	 * @return
     */
	int modifyResDoctorStatus(Map<String, Object> paramMap);

	Integer searchResRegUserCount(Map<String, Object> paramMap);

	List<ResDoctorExt> searchResRegUserForAudit(Map<String, Object> paramMap);
	
	List<ResDoctorExt> searchDoctorExtForAudit(Map<String, Object> paramMap);
	
	List<Map<String,String>> searGroupRotation(@Param(value="doctor")ResDoctor doctor);

	ResExamDoctor searchExamDoctor(Map<String,String> paramMap);

	/**
	 * 查询登记表
	 * @param paramMap
	 * @return
	 */
	List<ResDoctorExt> searchRegisterList(Map<String, Object> paramMap);

	List<ResDoctor> searchMonthRotationDoctor(@Param(value="schDeptFlow")String schDeptFlow,@Param(value="month")String month);
	
	List<ResDoctor> searchSelDeptDoctor(@Param(value="doctor")ResDoctor doctor);
	
	List<ResDoctor> searchDoctorForChange(@Param(value="doctor")ResDoctor doctor,@Param(value="docOrgHis")ResDoctorOrgHistory docOrgHis);
	
	int updateDocSelFlag(@Param(value="orgFlow")String orgFlow);

	List<ResDoctorExt> searchDocByteacher(Map<String, Object> paramMap);

	List<ResDoctor> searchDocByteacherJx(Map<String, Object> paramMap);

	
	/**
	 * 升级冗余字段
	 * @param doctor
	 * @return
	 */
	int updateRedundancyData(@Param(value="doctor")ResDoctor doctor);

	/**
	 * 按条件计算机构内各专业所占人数
	 * @param orgName
	 * @param doctor
	 * @return
	 */
	List<Map<String,Object>> countDocByOrg(@Param(value="orgName")String orgName,@Param(value="doctor")ResDoctor doctor);
	
	/**
	 * 计算所属组合的医师数
	 * @param doctor
	 * @return
	 */
	List<Map<String,Object>> countGroupDoc(@Param(value="doctor")ResDoctor doctor);

	/**
	 * 查询医师(医师账号管理)
	 * @param paramMap
	 * @return
	 */
	List<ResDoctorExt> searchDoctorAccountList(Map<String, Object> paramMap);
	
	int statisticYearCondocCount(Map<String, Object> paramMap);
	List<Map<String, Object>> schDoctorQuery(@Param(value = "teacherUserFlow") String teacherUserFlow, @Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate, @Param("typeList") List<String> typeList, @Param("workOrgId") String workOrgId);
	List<Map<String, Object>> chTeacherProcessFlowRec(Map<String, Object> paramMap);
	
	List<Map<String, Object>> searchSignInfo(Map<String, Object> paramMap);
	
	/**
	 * 查询用户及签到信息
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> searchSign(Map<String, Object> paramMap);
	List<ResDoctorOrgHistory> searchOrgFlowByhistoryOrgFlow(Map<String, Object> paramMap);

	/**
	 * 学员重报菜单
	 * @param parMp
	 * @return
     */
	List<Map<String, Object>> searchRegStuLst(Map<String, Object> parMp);

	/**
	 * 结业考核学员列表
	 * @param param
	 * @return
     */
	List<ResDoctorExt> searchDocByDiscipleTea(Map<String, Object> param);

	List<ResDoctorExt> searchDocByDiscipleAdmin(Map<String, Object> param);

	List<Map<String,Object>> teaQueryDocDisciple(Map<String, Object> param);

	List<Map<String,Object>> adminQueryDocDisciple(Map<String, Object> param);


	List<Map<String,Object>> searchSysRecords(Map<String, Object> beMap);

    List<Map<String,Object>> searchDocSysRecords(Map<String, Object> beMap);

	/**
     * 此方法用于查已退赔的学员故未将record_status=com.pinde.core.common.GlobalConstant.FLAG_Y考虑在内
	 * @param doctorFlows
	 * @return
     */
	List<ResDoctorExt> searchDoctorExt(List<String> doctorFlows);
	//查询某年所有注册信息完善的医师（为专硕导入成绩使用）
	List<ResDoctorClobForm> searchClobInfoDoc(@Param("year") String year);
	//查询此医师今年所报专业划定分
	Map<String,Object> querySpeLineScore(@Param("idNo") String idNo, @Param("year") String year);
	//查询DOCTORFLOW在范围内的学员（考勤系统用）
	List<ResDoctor> searchDoctor4Kq(Map<String,Object> paramMap);

	List<Map<String,Object>> jszyStudentStatistics(Map<String, Object> param);

	List<Map<String,Object>> jszySpeStatistics(Map<String, Object> param);

	List<Map<String,Object>> showOrgDoctorType(Map<String, Object> param);

	List<ResDoctor> searchDocByteacher2(Map<String, Object> paramMap);

	void insertRecruitDocInfo(@Param("recruitFlow") String recruitFlow);

	void insertRecruitInfo(@Param("recruitFlow") String recruitFlow);

	void insertRecruitDocInfoNotPass(@Param("recruitFlow") String recruitFlow);

	void insertRecruitInfoNotPass(@Param("recruitFlow") String recruitFlow);

	JsresRecruitDocInfoWithBLOBs selectRecruitDocInfo(@Param("recruitFlow") String recruitFlow);

	JsresRecruitInfo selectRecruitInfo(@Param("recruitFlow") String recruitFlow);

	List<ResDoctorExt> searchDoctorExt4Back(List<String> doctorFlows);

	List<ResDoctor> searchResDoctor2(Map<String, Object> param);

	List<ResDoctorExt> getStudents(Map<String, String> params);

	List<Map<String,Object>> searchOsceScoreList(Map<String, Object> paramMap);

	List<Map<String,Object>> globalQueryDocDisciple(Map<String, Object> param);

	/**
	 * @Department：研发部
	 * @Description 查询学员出科成绩和技能成绩
	 * @Author fengxf
	 * @Date 2019/11/12
	 */
	List<Map<String, String>> searchDoctorRecruitResultList(ResDoctorExt resDoctor);

	/**
	 * @Department：研发部
	 * @Description 查询学员出科考试成绩和试卷信息
	 * @Author fengxf
	 * @Date 2019/11/12
	 */
	Map<String,String> getDoctorScoreAndPaper(String processFlow);
	/**
	 * @Department：研发部
	 * @Description 更改开通权限提交
	 * @Author lim
	 * @Date 2020/8/6
	 */
    int updateSubmitAll(Map<String,Object> param);

    int updateCheckAll(Map<String,Object> param);

    int updatePowerCfg(Map<String,Object> param);

	int saveSubmitAll(List<String> userFlowList);

    int updateAffirm(List<String> scoreFlowList);

	int updateNotAffirm(List<String> scoreFlowList);

    List<String> selectDoctorFlow(@Param("scoreFlow") List<String> scoreFlow);

	/**
	 * @Department：研发部
	 * @Description 查询带教学员名单
	 * @Author fengxf
	 * @Date 2020/11/18
	 */
	List<Map<String, String>> searchSchDoctorList(Map<String, Object> paramMap);

	/**
	 * @param resDoctorExt
	 * @param medicineTypeId
	 * @Department：研发部
	 * @Description 查询医师列表
	 * @Author Zjie
	 * @Date 0013, 2021年1月13日
	 */
	List<JsResDoctorRecruitExt> searchResDoctorUserNew(ResDoctorExt resDoctorExt);


	List<Map<String,String>> searchDoctorSpe();

	List<Map<String,Object>> getCountBySessionNumber(List<String> userFlowList);

    List<String> searchRecruitListByOrgFlow(@Param("orgFlow")String orgFlow, @Param("isJointOrg")String isJointOrg);

	List<String> getSchools();

	List<SchArrangeResult> listDoctorResult(@Param("userIds") Set<String> userIds);

	List<ResRecItem> resRecCount(@Param("resultFlowList") List<String> resultFlowList);
}
