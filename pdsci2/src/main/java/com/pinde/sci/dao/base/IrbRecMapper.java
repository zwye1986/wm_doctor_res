package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.IrbRec;
import com.pinde.sci.model.mo.IrbRecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IrbRecMapper {
    int countByExample(IrbRecExample example);

    int deleteByExample(IrbRecExample example);

    int deleteByPrimaryKey(String recFlow);

    int insert(IrbRec record);

    int insertSelective(IrbRec record);

    List<IrbRec> selectByExampleWithBLOBs(IrbRecExample example);

    List<IrbRec> selectByExample(IrbRecExample example);

    IrbRec selectByPrimaryKey(String recFlow);

    int updateByExampleSelective(@Param("record") IrbRec record, @Param("example") IrbRecExample example);

    int updateByExampleWithBLOBs(@Param("record") IrbRec record, @Param("example") IrbRecExample example);

    int updateByExample(@Param("record") IrbRec record, @Param("example") IrbRecExample example);

    int updateByPrimaryKeySelective(IrbRec record);

    int updateByPrimaryKeyWithBLOBs(IrbRec record);

    int updateByPrimaryKey(IrbRec record);
}