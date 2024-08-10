package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResExamRoom;
import com.pinde.sci.model.mo.ResExamRoomExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResExamRoomMapper {
    int countByExample(ResExamRoomExample example);

    int deleteByExample(ResExamRoomExample example);

    int deleteByPrimaryKey(String roomFlow);

    int insert(ResExamRoom record);

    int insertSelective(ResExamRoom record);

    List<ResExamRoom> selectByExample(ResExamRoomExample example);

    ResExamRoom selectByPrimaryKey(String roomFlow);

    int updateByExampleSelective(@Param("record") ResExamRoom record, @Param("example") ResExamRoomExample example);

    int updateByExample(@Param("record") ResExamRoom record, @Param("example") ResExamRoomExample example);

    int updateByPrimaryKeySelective(ResExamRoom record);

    int updateByPrimaryKey(ResExamRoom record);
}