package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.RecruitExamMain;
import com.pinde.sci.model.mo.RecruitExamMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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