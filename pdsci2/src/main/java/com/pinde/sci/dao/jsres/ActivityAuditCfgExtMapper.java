package com.pinde.sci.dao.jsres;

import com.pinde.sci.model.mo.ActivityAuditCfg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ActivityAuditCfgExtMapper {

	/**
	 * @Department：研发部
	 * @Description 查询教学活动审批流程配置信息
	 * @Author fengxf
	 * @Date 2020/2/17
	 */
	List<ActivityAuditCfg> searchActivityAuditCfgList();
/*
*
 * @Param []
 * @Description 根据发起人的角色信息查询审批人的信息
 * @Author zhaomy
 * @Date 16:18 2020-04-24
 * @Department：研发部
 * @return
 **/
List<String> searchAuditByAddRoleFlow(@Param("addRoleFlow") String addRoleFlow);

	/**
	 * @Department：研发部
	 * @Description 查询教学活动发起人角色列表
	 * @Author fengxf
	 * @Date 2020/2/18
	 */
	List<Map<String,String>> searchAddRoleList(@Param("addRoleFlow") String addRoleFlow);

	/**
	 * @Department：研发部
	 * @Description 查询教学活动审批人角色列表
	 * @Author fengxf
	 * @Date 2020/2/18
	 */
	List<Map<String,String>> searchAuditRoleList();

	/**
	 * @Department：研发部
	 * @Description 保存教学活动审批流程信息
	 * @Author fengxf
	 * @Date 2020/2/18
	 */
	int saveActivityCfg(Map<String, Object> paramMap);


	/**
	 * @Param [paramMap]
	 * @Description 保存修改的教学活动审批流程信息
	 * @Author zhaomy
	 * @Date 13:35 2020-04-21
	 * @Department：研发部
	 * @return
	 **/
	int saveUpdateActivityCfg(String recordFlow);


	void deleteActivityCfgRole(@Param("addRoleFlow") String addRoleFlow, @Param("roleList") List<String> roleList);


	/**
	 * @Department：研发部
	 * @Description 查询教学活动审批流程信息
	 * @Author fengxf
	 * @Date 2020/2/18
	 */
	ActivityAuditCfg getActivityCfgInfo(@Param("addRoleFlow") String addRoleFlow);

	/*
	*
	 * @Param [addRoleFlow]
	 * @Description 删除教学活动审批流程信息
	 * @Author zhaomy
	 * @Date 16:56 2020-04-21
	 * @Department：研发部
	 * @return
	 **/
	int delActivityCfgInfo(@Param("addRoleFlow") String addRoleFlow);
}
