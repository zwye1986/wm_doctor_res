package com.pinde.sci.dao.jsres;

import com.pinde.core.model.JsDoctorInfoLogExt;
import com.pinde.core.model.JsResArchDoctorRecruitExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2017-05-22.
 * 学员信息存档数据访问接口
 */
public interface ResArchiveExtMapper {
	/**
	 *备份学员信息表
	 * @return
	 */
	int saveResDoctorLog(@Param(value = "archiveFlow") String archiveFlow);
	/**
	 *备份用户信息表
	 * @return
	 */
	int saveSysUserLog(@Param(value = "archiveFlow") String archiveFlow);
	/**
	 * 备份招录信息表
	 * @return
	 */
	int saveResDoctorRecruitLog(@Param(value = "archiveFlow") String archiveFlow);

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

	List<JsResArchDoctorRecruitExt> searchJsDoctorRecruitExtList(Map<String, Object> map);

	List<JsDoctorInfoLogExt> searchDoctorInfoExts(Map<String, Object> paramMap);
}
