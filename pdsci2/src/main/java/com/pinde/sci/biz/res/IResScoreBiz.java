package com.pinde.sci.biz.res;

import com.pinde.core.model.ResScore;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IResScoreBiz {
	/**
	 *	编辑
	 * @param resScore
	 * @return
	 */
	int save(ResScore resScore);

	/**
	 *	根据主键scoreFlow查询单条数据
	 * @param scoreFlow
	 * @return
	 */
	ResScore searchByScoreFlow(String scoreFlow);

	/**
	 * 根据主键doctorFlow查询多条数据
	 * @param doctorFlow
	 * @return
	 */
	List<ResScore> searchByScoreList(String doctorFlow);
	List<ResScore> selectByExampleWithBLOBs(String doctorFlow);
	List<ResScore> selectByParam(String doctorFlow);
	List<ResScore> selectByExampleWithBLOBs2(String doctorFlow,String flag);

	/**
	 * 当前轮转科室是否有分数信息
	 * @param processFlow
	 * @return
	 */
//	boolean haveScore(String processFlow);

	/**
	 * 根据process获取分数
	 * @param processFlow
	 * @return
	 */
	ResScore getScoreByProcess(String processFlow);

	ResScore getMaxScoreByProcess(String processFlow);

	ResScore getScoreByResult(String resultFlow);

	/**
	 * 根据doctorFlow和成绩年份获取分数
	 * @param doctorFlow
	 * @param year
     * @return
     */
	ResScore getScoreByDocFlowAndYearAndType(String doctorFlow,String year,String typeid);

	/**
	 *
	 * @return
     */
	List<Map<String,Object>> getALLScoreByMap(Map map);

	int importScoreFromExcel(MultipartFile file);

	/**
	 *
	 * @param recruitFlow
	 * @param id
     * @return
     */
	List<ResScore> selectByRecruitFlowAndScoreType(String recruitFlow, String id);

	/**
	 * 根据考试类型查询库中最新成绩的年份
	 * @param paramMap
	 * @return
     */
	String findLastYearByScoreTypeId(Map<String, String> paramMap);
	//十堰市太和医院OSCE分数导入
	int importOsceGrades(MultipartFile file);
	//十堰市太和医院理论技能分数导入
	int importTheorySkillGrades(MultipartFile file);
	//查询OSCE列表
	List<Map<String,Object>> searchOsceScoreList(Map<String,Object> paramMap);

    int updateNotAffirm(List<String> scoreFlowList);

	int updateAffirm(List<String> scoreFlowList);
}
