package com.pinde.res.dao.njmu.base;

import com.pinde.res.model.njmu.mo.Worklog;
import com.pinde.res.model.njmu.mo.WorklogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorklogMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WorkLog
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int countByExample(WorklogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WorkLog
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int deleteByExample(WorklogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WorkLog
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int deleteByPrimaryKey(String workid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WorkLog
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int insert(Worklog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WorkLog
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int insertSelective(Worklog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WorkLog
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	List<Worklog> selectByExample(WorklogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WorkLog
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	Worklog selectByPrimaryKey(String workid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WorkLog
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int updateByExampleSelective(@Param("record") Worklog record,
			@Param("example") WorklogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WorkLog
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int updateByExample(@Param("record") Worklog record,
			@Param("example") WorklogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WorkLog
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int updateByPrimaryKeySelective(Worklog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WorkLog
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int updateByPrimaryKey(Worklog record);
}