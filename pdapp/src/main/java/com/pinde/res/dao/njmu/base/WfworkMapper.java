package com.pinde.res.dao.njmu.base;

import com.pinde.res.model.njmu.mo.Wfwork;
import com.pinde.res.model.njmu.mo.WfworkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WfworkMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFWORK
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int countByExample(WfworkExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFWORK
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int deleteByExample(WfworkExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFWORK
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int deleteByPrimaryKey(Integer wfworkId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFWORK
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int insert(Wfwork record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFWORK
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int insertSelective(Wfwork record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFWORK
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	List<Wfwork> selectByExample(WfworkExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFWORK
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	Wfwork selectByPrimaryKey(Integer wfworkId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFWORK
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int updateByExampleSelective(@Param("record") Wfwork record,
			@Param("example") WfworkExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFWORK
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int updateByExample(@Param("record") Wfwork record,
			@Param("example") WfworkExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFWORK
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int updateByPrimaryKeySelective(Wfwork record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFWORK
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int updateByPrimaryKey(Wfwork record);
}