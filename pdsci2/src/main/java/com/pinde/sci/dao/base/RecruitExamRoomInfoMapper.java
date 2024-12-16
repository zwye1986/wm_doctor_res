package com.pinde.sci.dao.base;

import com.pinde.core.model.RecruitExamRoomInfo;
import com.pinde.core.model.RecruitExamRoomInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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