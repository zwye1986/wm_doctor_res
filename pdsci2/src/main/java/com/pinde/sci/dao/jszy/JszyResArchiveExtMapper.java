package com.pinde.sci.dao.jszy;

import com.pinde.sci.model.jszy.JszyDoctorInfoLogExt;
import com.pinde.sci.model.jszy.JszyResArchDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2017-05-22.
 * 学员信息存档数据访问接口
 */
public interface JszyResArchiveExtMapper {

	/**
	 * resDoctor表中所有文件路径首个文件夹
	 * @return
     */
	List<Map<String,String>> resDoctorPathTitle();

	/**
	 * sysUser表中所有文件路径首个文件夹
	 * @return
     */
	List<Map<String,String>> sysUserPathTitle();

	/**
	 * resDoctorRecruit表中所有文件路径首个文件夹
	 * @return
     */
	List<Map<String,String>> resDoctorRecruitPathTitle();

	List<JszyResArchDoctorRecruitExt> searchJsDoctorRecruitExtList(Map<String, Object> map);

	List<JszyDoctorInfoLogExt> searchDoctorInfoExts(Map<String, Object> paramMap);

	/**
	 * 备份学员信息表
	 * 创建res_doctor备份表表结构
	 * @return
	 */
	int createResDoctorLog(Map<String, Object> paramMap);
	/**
	 * 备份学员信息表
	 * 创建res_doctor备份表表约束
	 * @return
	 */
	int createResDoctorLogPK(Map<String, Object> paramMap);
	/**
	 * 备份学员信息表
	 * 插入res_doctor备份表表数据
	 * @return
	 */
	int saveResDoctorLog(Map<String, Object> paramMap);



	/**
	 * 备份用户信息表
	 * 创建sys_user备份表表结构
	 * @return
	 */
	int createSysUserLog(Map<String, Object> paramMap);
	/**
	 * 备份用户信息表
	 * 创建sys_user备份表表约束
	 * @return
	 */
	int createSysUserLogPK(Map<String, Object> paramMap);
	/**
	 * 备份用户信息表
	 * 插入sys_user备份表表数据
	 * @return
	 */
	int saveSysUserLog(Map<String, Object> paramMap);



	/**
	 * 备份招录信息表
	 * 创建res_doctor_recruit备份表表结构
	 * @return
	 */
	int createResDoctorRecruitLog(Map<String, Object> paramMap);
	/**
	 * 备份招录信息表
	 * 创建res_doctor_recruit备份表表约束
	 * @return
	 */
	int createResDoctorRecruitLogPK(Map<String, Object> paramMap);
	/**
	 * 备份招录信息表
	 * 插入res_doctor_recruit备份表表数据
	 * @return
	 */
	int saveResDoctorRecruitLog(Map<String, Object> paramMap);


	/**
	 * 备份人员信息详情表（大字段）
	 * 创建pub_user_resume备份表表结构
	 * @return
	 */
	int createResumeLog(Map<String, Object> paramMap);
	/**
	 * 备份人员信息详情表（大字段）
	 * 创建pub_user_resume备份表表约束
	 * @return
	 */
	int createResumeLogPK(Map<String, Object> paramMap);
	/**
	 * 备份人员信息详情表（大字段）
	 * 插入pub_user_resume备份表表数据
	 * @return
	 */
	int saveResumeLog(Map<String, Object> paramMap);

	/**
	 * 备份学员诚信声明表
	 * 创建pub_user_resume备份表表结构
	 * @return
	 */
	int createAuthLog(Map<String, Object> paramMap);
	/**
	 * 备份学员诚信声明表
	 * 创建doctor_auth备份表表约束
	 * @return
	 */
	int createAuthLogPK(Map<String, Object> paramMap);
	/**
	 * 备份学员诚信声明表
	 * 插入doctor_auth备份表表数据
	 * @return
	 */
	int saveAuthLog(Map<String, Object> paramMap);



	/**
	 * 根据条件查询招录信息
	 * @param paramMap
	 * @return
     */
	List<ResDoctorRecruitWithBLOBs> selectrecruitLogByMapWithBLOBs(Map<String, Object> paramMap);

	/**
	 * 根据条件查询用户信息
	 * @param paramMap
	 * @return
	 */
	List<SysUser> selectUserLogByMap(Map<String, Object> paramMap);

	/**
	 * 根据条件查询人员信息详情表（大字段）
	 * @return
	 */
	List<PubUserResume> selectResumeLogByMap(Map<String, Object> paramMap);

	/**
	 * 根据条件查询学员诚信声明
	 * @return
	 */
	List<DoctorAuth> selectAuthLogByMap(Map<String, Object> paramMap);
}
