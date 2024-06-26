package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.RecruitExamRoomInfo;
import com.pinde.sci.model.mo.RecruitExamRoomInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecruitExamRoomInfoMapper {
    int countByExample(RecruitExamRoomInfoExample example);

    int deleteByExample(RecruitExamRoomInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(RecruitExamRoomInfo record);

    int insertSelective(RecruitExamRoomInfo record);

    List<RecruitExamRoomInfo> selectByExample(RecruitExamRoomInfoExample example);

    RecruitExamRoomInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") RecruitExamRoomInfo record, @Param("example") RecruitExamRoomInfoExample example);

    int updateByExample(@Param("record") RecruitExamRoomInfo record, @Param("example") RecruitExamRoomInfoExample example);

    int updateByPrimaryKeySelective(RecruitExamRoomInfo record);

    int updateByPrimaryKey(RecruitExamRoomInfo record);
}