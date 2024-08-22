package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.InxInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InxInfoMapper {
    int countByExample(InxInfoExample example);

    int deleteByExample(InxInfoExample example);

    int deleteByPrimaryKey(String infoFlow);

    int insert(InxInfo record);

    int insertSelective(InxInfo record);

    List<InxInfo> selectByExampleWithBLOBs(InxInfoExample example);

    List<InxInfo> selectByExample(InxInfoExample example);

    InxInfo selectByPrimaryKey(String infoFlow);

    int updateByExampleSelective(@Param("record") InxInfo record, @Param("example") InxInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") InxInfo record, @Param("example") InxInfoExample example);

    int updateByExample(@Param("record") InxInfo record, @Param("example") InxInfoExample example);

    int updateByPrimaryKeySelective(InxInfo record);

    int updateByPrimaryKeyWithBLOBs(InxInfo record);

    int updateByPrimaryKey(InxInfo record);
}