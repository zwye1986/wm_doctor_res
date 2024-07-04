package com.pinde.res.dao.njmu.base;

import com.pinde.res.model.njmu.mo.RecBighistory;
import com.pinde.res.model.njmu.mo.RecBighistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecBighistoryMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.Rec_BigHistory
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int countByExample(RecBighistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.Rec_BigHistory
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int deleteByExample(RecBighistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.Rec_BigHistory
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int deleteByPrimaryKey(Integer recid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.Rec_BigHistory
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int insert(RecBighistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.Rec_BigHistory
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int insertSelective(RecBighistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.Rec_BigHistory
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	List<RecBighistory> selectByExample(RecBighistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.Rec_BigHistory
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	RecBighistory selectByPrimaryKey(Integer recid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.Rec_BigHistory
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int updateByExampleSelective(@Param("record") RecBighistory record,
			@Param("example") RecBighistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.Rec_BigHistory
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int updateByExample(@Param("record") RecBighistory record,
			@Param("example") RecBighistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.Rec_BigHistory
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int updateByPrimaryKeySelective(RecBighistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.Rec_BigHistory
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	int updateByPrimaryKey(RecBighistory record);
}