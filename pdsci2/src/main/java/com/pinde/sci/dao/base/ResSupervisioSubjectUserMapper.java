package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResSupervisioSubjectUser;
import com.pinde.sci.model.mo.ResSupervisioSubjectUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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