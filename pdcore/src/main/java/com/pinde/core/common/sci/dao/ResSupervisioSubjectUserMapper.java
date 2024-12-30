package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResSupervisioSubjectUser;
import com.pinde.core.model.ResSupervisioSubjectUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResSupervisioSubjectUserMapper {
    int countByExample(ResSupervisioSubjectUserExample example);

    int deleteByExample(ResSupervisioSubjectUserExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResSupervisioSubjectUser record);

    int insertSelective(ResSupervisioSubjectUser record);

    List<ResSupervisioSubjectUser> selectByExample(ResSupervisioSubjectUserExample example);

    ResSupervisioSubjectUser selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResSupervisioSubjectUser record, @Param("example") ResSupervisioSubjectUserExample example);

    int updateByExample(@Param("record") ResSupervisioSubjectUser record, @Param("example") ResSupervisioSubjectUserExample example);

    int updateByPrimaryKeySelective(ResSupervisioSubjectUser record);

    int updateByPrimaryKey(ResSupervisioSubjectUser record);
}