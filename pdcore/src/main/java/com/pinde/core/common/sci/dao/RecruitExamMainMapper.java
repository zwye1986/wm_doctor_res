package com.pinde.core.common.sci.dao;

import com.pinde.core.model.RecruitExamMain;
import com.pinde.core.model.RecruitExamMainExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitExamMainMapper {
    int countByExample(RecruitExamMainExample example);

    int deleteByExample(RecruitExamMainExample example);

    int deleteByPrimaryKey(String mainFlow);

    int insert(RecruitExamMain record);

    int insertSelective(RecruitExamMain record);

    List<RecruitExamMain> selectByExampleWithBLOBs(RecruitExamMainExample example);

    List<RecruitExamMain> selectByExample(RecruitExamMainExample example);

    RecruitExamMain selectByPrimaryKey(String mainFlow);

    int updateByExampleSelective(@Param("record") RecruitExamMain record, @Param("example") RecruitExamMainExample example);

    int updateByExampleWithBLOBs(@Param("record") RecruitExamMain record, @Param("example") RecruitExamMainExample example);

    int updateByExample(@Param("record") RecruitExamMain record, @Param("example") RecruitExamMainExample example);

    int updateByPrimaryKeySelective(RecruitExamMain record);

    int updateByPrimaryKeyWithBLOBs(RecruitExamMain record);

    int updateByPrimaryKey(RecruitExamMain record);
}