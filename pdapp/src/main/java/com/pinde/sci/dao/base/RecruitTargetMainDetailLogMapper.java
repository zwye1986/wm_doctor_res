package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.RecruitTargetMainDetailLog;
import com.pinde.sci.model.mo.RecruitTargetMainDetailLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecruitTargetMainDetailLogMapper {
    int countByExample(RecruitTargetMainDetailLogExample example);

    int deleteByExample(RecruitTargetMainDetailLogExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(RecruitTargetMainDetailLog record);

    int insertSelective(RecruitTargetMainDetailLog record);

    List<RecruitTargetMainDetailLog> selectByExample(RecruitTargetMainDetailLogExample example);

    RecruitTargetMainDetailLog selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") RecruitTargetMainDetailLog record, @Param("example") RecruitTargetMainDetailLogExample example);

    int updateByExample(@Param("record") RecruitTargetMainDetailLog record, @Param("example") RecruitTargetMainDetailLogExample example);

    int updateByPrimaryKeySelective(RecruitTargetMainDetailLog record);

    int updateByPrimaryKey(RecruitTargetMainDetailLog record);
}