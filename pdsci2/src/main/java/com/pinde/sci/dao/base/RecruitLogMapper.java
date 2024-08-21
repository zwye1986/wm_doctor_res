package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.RecruitLog;
import com.pinde.sci.model.mo.RecruitLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitLogMapper {
    int countByExample(RecruitLogExample example);

    int deleteByExample(RecruitLogExample example);

    int deleteByPrimaryKey(String logFlow);

    int insert(RecruitLog record);

    int insertSelective(RecruitLog record);

    List<RecruitLog> selectByExample(RecruitLogExample example);

    RecruitLog selectByPrimaryKey(String logFlow);

    int updateByExampleSelective(@Param("record") RecruitLog record, @Param("example") RecruitLogExample example);

    int updateByExample(@Param("record") RecruitLog record, @Param("example") RecruitLogExample example);

    int updateByPrimaryKeySelective(RecruitLog record);

    int updateByPrimaryKey(RecruitLog record);
}