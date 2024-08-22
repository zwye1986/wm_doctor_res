package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDiscipleNoteInfo;
import com.pinde.sci.model.mo.ResDiscipleNoteInfoExample;
import com.pinde.sci.model.mo.ResDiscipleNoteInfoWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDiscipleNoteInfoMapper {
    int countByExample(ResDiscipleNoteInfoExample example);

    int deleteByExample(ResDiscipleNoteInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDiscipleNoteInfoWithBLOBs record);

    int insertSelective(ResDiscipleNoteInfoWithBLOBs record);

    List<ResDiscipleNoteInfoWithBLOBs> selectByExampleWithBLOBs(ResDiscipleNoteInfoExample example);

    List<ResDiscipleNoteInfo> selectByExample(ResDiscipleNoteInfoExample example);

    ResDiscipleNoteInfoWithBLOBs selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDiscipleNoteInfoWithBLOBs record, @Param("example") ResDiscipleNoteInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ResDiscipleNoteInfoWithBLOBs record, @Param("example") ResDiscipleNoteInfoExample example);

    int updateByExample(@Param("record") ResDiscipleNoteInfo record, @Param("example") ResDiscipleNoteInfoExample example);

    int updateByPrimaryKeySelective(ResDiscipleNoteInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ResDiscipleNoteInfoWithBLOBs record);

    int updateByPrimaryKey(ResDiscipleNoteInfo record);
}