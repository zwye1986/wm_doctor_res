package com.pinde.sci.dao.res;

import com.pinde.core.model.PubUserResume;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.res.ResDoctorExt;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2017-05-22.
 * 学员信息存档数据访问接口
 */
public interface ResHBArchiveExtMapper {
	/**
	 *创建备份学员信息表
	 * @return
	 */
	int createResDoctorLog(Map<String,Object> paramMap);
	/**
	 *备份学员信息表
	 * @return
	 */
	int saveResDoctorLog(Map<String,Object> paramMap);
	/**
	 *创建用户信息表
	 * @return
	 */
	int createSysUserLog(Map<String,Object> paramMap);
	/**
	 *备份用户信息表
	 * @return
	 */
	int saveSysUserLog(Map<String,Object> paramMap);
	/**
	 *创建招录信息表
	 * @return
	 */
	int createResDoctorRecruitLog(Map<String,Object> paramMap);
	/**
	 * 备份招录信息表
	 * @return
	 */
	int saveResDoctorRecruitLog(Map<String,Object> paramMap);
	/**
	 *创建报到信息表
	 * @return
	 */
	int createResRecruitRegisterLog(Map<String,Object> paramMap);
	/**
	 * 备份报到信息表
	 * @return
	 */
	int saveResRecruitRegisterLog(Map<String,Object> paramMap);
	/**
	 *创建大字段信息表
	 * @return
	 */
	int createResumeLog(Map<String,Object> paramMap);
	/**
	 * 备份大字段信息表
	 * @return
	 */
	int saveResumeLog(Map<String,Object> paramMap);

	List<ResDoctorExt> searchArchiveList(Map<String,Object> paramMap);

	//根据USER_FLOW查询存档USER
	SysUser readUserArchive(Map<String,Object> paramMap);
	//根据USER_FLOW查询存档DOCTOR
	ResDoctor readDoctorArchive(Map<String,Object> paramMap);
	//根据USER_FLOW查询存档USERRESUME
	PubUserResume readUserResume(Map<String,Object> paramMap);
}
