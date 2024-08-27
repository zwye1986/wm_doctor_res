package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.RecruitExamRoom;
import com.pinde.sci.model.mo.RecruitExamRoomExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitExamRoomMapper {
    int countByExample(RecruitExamRoomExample example);

    int deleteByExample(RecruitExamRoomExample example);

    int deleteByPrimaryKey(String roomFlow);

    int insert(RecruitExamRoom record);

    int insertSelective(RecruitExamRoom record);

    List<RecruitExamRoom> selectByExample(RecruitExamRoomExample example);

    RecruitExamRoom selectByPrimaryKey(String roomFlow);

    int updateByExampleSelective(@Param("record") RecruitExamRoom record, @Param("example") RecruitExamRoomExample example);

    int updateByExample(@Param("record") RecruitExamRoom record, @Param("example") RecruitExamRoomExample example);

    int updateByPrimaryKeySelective(RecruitExamRoom record);

    int updateByPrimaryKey(RecruitExamRoom record);

    List<String> searchAllExamRoomNameByOrgFlow(String orgFlow);
}