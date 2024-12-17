package com.pinde.sci.dao.base;

import com.pinde.core.model.PubProjProcess;
import com.pinde.core.model.PubProjProcessExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubProjProcessMapper {
    int countByExample(PubProjProcessExample example);

    int deleteByExample(PubProjProcessExample example);

    int deleteByPrimaryKey(String processFlow);

    int insert(PubProjProcess record);

    int insertSelective(PubProjProcess record);

    List<PubProjProcess> selectByExample(PubProjProcessExample example);

    PubProjProcess selectByPrimaryKey(String processFlow);

    int updateByExampleSelective(@Param("record") PubProjProcess record, @Param("example") PubProjProcessExample example);

    int updateByExample(@Param("record") PubProjProcess record, @Param("example") PubProjProcessExample example);

    int updateByPrimaryKeySelective(PubProjProcess record);

    int updateByPrimaryKey(PubProjProcess record);
}